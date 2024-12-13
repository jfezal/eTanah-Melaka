/*
 * Author : Aizuddin
 * Usage  : For perserahan SM
 */
package etanah.view.daftar.validator;

import com.google.inject.Inject;
import etanah.dao.HakmilikWarisDAO;
import etanah.dao.KodFlagPihakDAO;
import etanah.dao.KodStatusMohonPihakDAO;
import etanah.dao.PenggunaDAO;
import etanah.dao.PermohonanAtasPerserahanDAO;
import etanah.dao.PermohonanDAO;
import etanah.dao.PihakDAO;
import etanah.model.Hakmilik;
import etanah.model.HakmilikPermohonan;
import etanah.model.HakmilikPihakBerkepentingan;
import etanah.model.HakmilikUrusan;
import etanah.model.HakmilikWaris;
import etanah.model.InfoAudit;
import etanah.model.KodFlagPihak;
import etanah.model.KodJenisPihakBerkepentingan;
import etanah.model.KodStatusMohonPihak;
import etanah.model.Pemohon;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.model.PermohonanAtasPerserahan;
import etanah.model.PermohonanPihak;
import etanah.model.PermohonanPihakHubungan;
import etanah.model.Pihak;
import etanah.service.common.HakmilikPihakKepentinganService;
import etanah.service.common.HakmilikUrusanService;
import etanah.service.common.PermohonanAtasPerserahanService;
import etanah.service.common.PermohonanPihakService;
import etanah.service.common.PermohonanService;
import etanah.service.daftar.BatalNotaService;
import etanah.service.daftar.PendaftaranSuratKuasaService;
import etanah.service.daftar.SyarikatMCLModule;
import etanah.workflow.StageContext;
import etanah.workflow.StageListener;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.math.fraction.Fraction;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class SyarikatMCLValidator implements StageListener {

    //Injector
    @Inject
    PendaftaranSuratKuasaService pdfSuratKuasaService;
    @Inject
    BatalNotaService batalNotaService;
    @Inject
    PenggunaDAO penggunaDAO;
    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    KodFlagPihakDAO kodFlagPihakDAO;
    @Inject
    PihakDAO pihakDAO;
    @Inject
    HakmilikWarisDAO hakmilikWarisDAO;
    @Inject
    KodStatusMohonPihakDAO kodStatusMohonPihakDAO;
    @Inject
    PermohonanAtasPerserahanDAO permohonanAtasPerserahanDAO;
    @Inject
    HakmilikUrusanService hakmilikService;
    @Inject
    PermohonanPihakService permohonanPihakService;
    @Inject
    PermohonanService permohonanService;
    @Inject
    PermohonanAtasPerserahanService permohonanAtasPerserahanService;
    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;
    @Inject
    HakmilikPihakKepentinganService hakmilikPihakKepentinganService;
    //Variable
    private static final Logger LOG = Logger.getLogger(SyarikatMCLValidator.class);
    private Pengguna pengguna;

    @Override
    public boolean beforeStart(StageContext context) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean beforeGenerateReports(StageContext ctx) {
        return true;

    }

    @Override
    public void onGenerateReports(StageContext context) {
    }

    @Override
    public String beforeComplete(StageContext ctx, String proposedOutcome) {
        LOG.debug("@BeforeComplete [ start ]");

        Permohonan permohonan = ctx.getPermohonan();
        List<Permohonan> senaraiPermohonan = new ArrayList<Permohonan>();
        if (permohonan != null) {
            String idKump = permohonan.getIdKumpulan();
            if (StringUtils.isNotBlank(idKump)) {
                senaraiPermohonan = permohonanService.getPermohonanByIdKump(idKump);
            } else {
                senaraiPermohonan.add(permohonan);
            }
        } else {
            ctx.addMessage("ID Permohonan tidak dikenali.");
            return null;
        }

        Transaction tx = sessionProvider.get().beginTransaction();
        tx.begin();

        for (Permohonan mohon : senaraiPermohonan) {
            if (mohon.getKeputusan() == null) {
                ctx.addMessage("Sila masukan keputusan untuk perserahan [" + mohon.getIdPermohonan() + "]");
                return null;
            }
            if (mohon.getKeputusan().getKod().equals("D")) {
                List<HakmilikPermohonan> senaraiHakmilikTerlibat = mohon.getSenaraiHakmilik();
                for (HakmilikPermohonan hp : senaraiHakmilikTerlibat) {
                    if (!ctx.isByPass()) {
                        Date d = mohon.getInfoAudit().getTarikhMasuk();
                        if (permohonanService.isPrevUrusanNotComplete(hp.getHakmilik().getIdHakmilik(), d)) {
                            ctx.addMessage("Terdapat urusan sebelum yang masih belum selesai.");
                            return null;
                        }
                    }
                }

                String kodUrusan = mohon.getKodUrusan().getKod();
                pengguna = ctx.getPengguna();
                InfoAudit info = new InfoAudit();
                info.setDimasukOleh(pengguna);
                info.setTarikhMasuk(new java.util.Date());
                SyarikatMCLModule sm = SyarikatMCLModule.valueOf(kodUrusan);
                int urusan = sm.getCode();
                LOG.debug("Kod urusan = " + kodUrusan + "[" + urusan + "]");

                try {
                    switch (urusan) {
                        case 1: {
                            aktifPihakSM(mohon, info);
                            break;
                        }
                        case 2: {
                            daftarUrusan(mohon, info);
                            daftarHakmilikPihak(mohon, info);
                            aktifPihakSM(mohon, info);
                            break;
                        }
                        case 3: {
                            batalUrusan(mohon, info);
                            deaktifPihakSM(mohon, info);
                            break;
                        }

                    }
                } catch (Exception ex) {
                    tx.rollback();
                    Throwable t = ex;
                    // getting the root cause
                    while (t.getCause() != null) {
                        t = t.getCause();
                    }
                    t.printStackTrace();
                    ctx.addMessage("Pendaftaran perserahan " + mohon.getIdPermohonan()
                            + " Tidak Berjaya.Sila Hubungi Pentadbir Sistem.\r[ " + ex.toString() + " ]");
                    return null;
                }

            }
        }
        LOG.info("commiting transaction..");
        tx.commit();
        LOG.debug("@BeforeComplete [ success ]");
        ctx.addMessage(" : Pendaftaran Urusan Berjaya.");
        return proposedOutcome;
    }

    @Override
    public void afterComplete(StageContext context) {
    }

    private void daftarUrusan(Permohonan permohonan, InfoAudit info) throws Exception {
        LOG.info(":::::DAFTAR HAKMILIK URUSAN SM::::::");
        List<HakmilikPermohonan> hakmilikList = permohonan.getSenaraiHakmilik();

        List<HakmilikUrusan> hakmilikUrusanList = new ArrayList<HakmilikUrusan>();
        for (HakmilikPermohonan hakmilikPermohonan : hakmilikList) {
            if (hakmilikPermohonan == null || hakmilikPermohonan.getHakmilik() == null) {
                continue;
            }
            HakmilikUrusan hakmilikUrusan = new HakmilikUrusan();
            hakmilikUrusan.setInfoAudit(info);
            hakmilikUrusan.setDaerah(hakmilikPermohonan.getHakmilik().getDaerah());
            hakmilikUrusan.setIdPerserahan(permohonan.getIdPermohonan());
            hakmilikUrusan.setCawangan(hakmilikPermohonan.getHakmilik().getCawangan());
            hakmilikUrusan.setHakmilik(hakmilikPermohonan.getHakmilik());
            hakmilikUrusan.setKodUrusan(permohonan.getKodUrusan());
            hakmilikUrusan.setLuasTerlibat(hakmilikPermohonan.getLuasTerlibat());
            hakmilikUrusan.setTarikhDaftar(permohonan.getInfoAudit().getTarikhMasuk()); //tarikh perserahan
            hakmilikUrusan.setKodUnitLuas(hakmilikPermohonan.getHakmilik().getKodUnitLuas());
            hakmilikUrusan.setAktif('Y');
            hakmilikUrusan.setFolderDokumen(permohonan.getFolderDokumen());

            hakmilikUrusanList.add(hakmilikUrusan);
        }
        hakmilikService.saveOrUpdate(hakmilikUrusanList);
        LOG.info(":::::SELESAI DAFTAR HAKMILIK URUSAN SM::::::");
    }

    private void daftarHakmilikPihak(Permohonan permohonan, InfoAudit info) throws Exception {
        LOG.info("UpdatePihak :: start");
        List<HakmilikPermohonan> li = permohonan.getSenaraiHakmilik();
        List<Pemohon> pemohon = permohonan.getSenaraiPemohon();

        List<PermohonanPihak> list = permohonan.getSenaraiPihak();
        List<HakmilikPihakBerkepentingan> hakmilikPihakBerkepentinganList = new ArrayList<HakmilikPihakBerkepentingan>();
        List<Map<String, Object>> senaraiMap = new ArrayList<Map<String, Object>>();

        Map<String, Object> map = null;
        HakmilikPihakBerkepentingan PA = null;

        for (HakmilikPermohonan hp : li) {
            if (hp == null) {
                continue;
            }

            Hakmilik hk = hp.getHakmilik();
            HakmilikUrusan hu =
                    hakmilikService.findByIdPerserahanIdHakmilik(permohonan.getIdPermohonan(), hk.getIdHakmilik());
            if (hu == null) {
                continue;
            }

            // this is new feature
            // to cater MH
            // to insert all pemohon to hakmilik_pihak
            KodJenisPihakBerkepentingan jenis = null;
            Pihak pihakKongsi = null;
            PA = new HakmilikPihakBerkepentingan();

            for (PermohonanPihak p : list) {
                if (p == null || p.getJenis().getKod().equals("WAR")) {
                    continue;
                }
                if (p.getHakmilik() == null
                        || !p.getHakmilik().getIdHakmilik().equals(hk.getIdHakmilik())) {
                    continue;
                }


                Pihak pihak = p.getPihak();
                HakmilikPihakBerkepentingan hpk = hakmilikPihakKepentinganService.findHakmilikPihakByIdPihak(pihak, hk);
                //merge if pihak already exist
                //if not create new pihak
                if (hpk == null) {
                    LOG.debug("::pihak tidak wujud::");
                    hpk = new HakmilikPihakBerkepentingan();
                    hpk.setJenis(p.getJenis());
                    hpk.setPihak(pihak);
                    hpk.setHakmilik(hk);
                    hpk.setCawangan(hk.getCawangan());
                    hpk.setAktif('Y');
                    hpk.setPerserahan(hu);
                    //TODO :: insert kaveat perserahan
//                    hpk.setPermohonan(permohonan);
                    hpk.setInfoAudit(info);
                    hpk.setSyerPembilang(p.getSyerPembilang());
                    hpk.setSyerPenyebut(p.getSyerPenyebut());

                } else {
                    LOG.debug("::pihak wujud::");
                    Fraction nf = Fraction.ZERO;
                    Fraction af = Fraction.ZERO;
                    //if tuan tanah is pemohon + penerima syer
                    boolean flag = false;
                    for (Pemohon pemohon1 : pemohon) {
                        if (pemohon1.getPihak().getIdPihak() == pihak.getIdPihak()) {
                            flag = true;
                            break;
                        }
                    }
                    if (flag) {
                        //to make current pihak become new pihak
                        af = new Fraction(p.getSyerPembilang(), p.getSyerPenyebut());
                        hpk = new HakmilikPihakBerkepentingan();
                        hpk.setPihak(pihak);
                        hpk.setHakmilik(hk);
                        hpk.setCawangan(hk.getCawangan());
                        hpk.setAktif('Y');
                        hpk.setPerserahan(hu);
                    } else {
                        int c1 = hpk.getSyerPembilang(); //syer pembilang asal
                        int c2 = hpk.getSyerPenyebut();//syer penyebut asal
                        int n1 = p.getSyerPembilang(); //syer pembilang mohon
                        int n2 = p.getSyerPenyebut(); //syer penyebut mohon
                        // add current fraction with new fraction
                        //TODO : check for big fraction
                        Fraction cf = new Fraction(c1, c2);
                        if (n1 != 0 && n2 != 0) {
                            nf = new Fraction(n1, n2);
                        }
                        af = cf.add(nf);
                    }

                    hpk.setSyerPembilang(af.getNumerator());
                    hpk.setSyerPenyebut(af.getDenominator());
                    hpk.setJenis(p.getJenis());
                    info = hpk.getInfoAudit();
                    info.setDiKemaskiniOleh(info.getDimasukOleh());
                    info.setTarikhKemaskini(new java.util.Date());
                    hpk.setInfoAudit(info);
                }
                if (permohonan.getKodUrusan().getKod().startsWith("KV")) {
                    hpk.setKaveatAktif('Y');
                } else {
                    hpk.setKaveatAktif('T');
                }

                if (hpk.getCawangan() == null) {
                    hpk.setCawangan(hk.getCawangan());
                }

                if (p.getSyerBersama() == 'Y') {
                    if (pihakKongsi == null) {
                        pihakKongsi = p.getPihak();
                    }
                    hpk.setPihakKongsiBersama(pihakKongsi);
                }
                hpk.setNama(pihak.getNama());
                hpk.setJenisPengenalan(pihak.getJenisPengenalan());
                hpk.setNoPengenalan(pihak.getNoPengenalan());
                hpk.setAlamat1(pihak.getAlamat1());
                hpk.setAlamat2(pihak.getAlamat2());
                hpk.setAlamat3(pihak.getAlamat3());
                hpk.setAlamat4(pihak.getAlamat4());
                hpk.setPoskod(pihak.getPoskod());
                hpk.setNegeri(pihak.getNegeri());

                if (hpk.getJenis().getKod().equals("PA")) {
                    map = new HashMap<String, Object>();
                    LOG.debug("jenis = " + hpk.getJenis().getKod());
                    PA = hakmilikPihakKepentinganService.save(hpk);
                    LOG.debug("id mohon pihak = " + p.getIdPermohonanPihak());
                    map.put(String.valueOf(p.getIdPermohonanPihak()), hpk);
                    senaraiMap.add(map);
                } else {
                    hakmilikPihakBerkepentinganList.add(hpk);
                }
            }

            LOG.debug("size = " + senaraiMap.size());
            info.setDiKemaskiniOleh(pengguna);
            info.setTarikhKemaskini(new java.util.Date());
            hakmilikPihakKepentinganService.save(hakmilikPihakBerkepentinganList, pemohon, hk, permohonan, info);
        }

        for (PermohonanPihak pp : list) {
            if (pp == null || pp.getSenaraiHubungan().isEmpty()) {
                continue;
            }
            LOG.debug("pp.id mohon pihak =" + pp.getIdPermohonanPihak());

            for (Map<String, Object> map2 : senaraiMap) {
                if (map2 == null) {
                    continue;
                }
                PA = (HakmilikPihakBerkepentingan) map2.get(String.valueOf(pp.getIdPermohonanPihak()));
                if (PA != null) {
                    break;
                }
            }

            LOG.debug(PA != null ? "not null" : "null");

            List<PermohonanPihakHubungan> senaraiWaris = pp.getSenaraiHubungan();
            for (PermohonanPihakHubungan pph : senaraiWaris) {
                LOG.debug("senarai waris..");
                HakmilikWaris hw = new HakmilikWaris();
                hw.setInfoAudit(info);
                hw.setJenisPengenalan(pph.getJenisPengenalan());
                hw.setPemegangAmanah(PA);
                hw.setNama(pph.getNama());
                hw.setNoPengenalan(pph.getNoPengenalan());
                hw.setSyerPembilang(pph.getSyerPembilang());
                hw.setSyerPenyebut(pph.getSyerPenyebut());
                hw.setWargaNegara(pph.getWargaNegara());
                hw.setNegeri(pph.getNegeri());
                hw.setCawangan(pph.getCawangan());
                hakmilikWarisDAO.save(hw);
            }
        }
        LOG.info("UpdatePihak :: finish");
    }

    private void aktifPihakSM(Permohonan permohonan, InfoAudit info) throws Exception {
        LOG.info(":::::AKTIFKAN STATUS PIHAK SM::::::");
        LOG.info("==ID Permohonan==" + permohonan.getIdPermohonan());
        LOG.info("==info==" + info);
        List<PermohonanPihak> listPermohonanPihak = permohonan.getSenaraiPihak();
        if (listPermohonanPihak != null) {
            for (PermohonanPihak pp : listPermohonanPihak) {
                //Aktifkan permohonan pihak
                pp.setInfoAudit(info);
                KodStatusMohonPihak kod = kodStatusMohonPihakDAO.findById("SMA");
                pp.setStatusMohonPihak(kod);
                permohonanPihakService.saveOrUpdate(pp);
                //Pihak aktifkan
                Long idPihak = pp.getPihak().getIdPihak();
                LOG.info("==idPihak==" + idPihak);
                Pihak pk = pihakDAO.findById(idPihak);
                pk.setInfoAudit(info);
                if (permohonan.getKodUrusan().getKod().equals("SMB")) {
                    pk.setNoSyarikatMcl(permohonan.getIdPermohonan());
                }
                KodFlagPihak status = kodFlagPihakDAO.findById("AK");
                pk.setKodFlagPihak(status);
                pihakDAO.saveOrUpdate(pk);
            }
        }
        LOG.info(":::::SELESAI AKTIFKAN STATUS PIHAK SM::::::");
    }

    private void deaktifPihakSM(Permohonan permohonan, InfoAudit info) throws Exception {
        LOG.info(":::::DEAKTIFKAN STATUS PIHAK SM::::::");
        List<PermohonanPihak> listPermohonanPihak = permohonan.getSenaraiPihak();
        if (listPermohonanPihak != null) {
            for (PermohonanPihak pp : listPermohonanPihak) {
                Long idPihak = pp.getPihak().getIdPihak();
                LOG.info("==idPihak==" + idPihak);
                Pihak pk = pihakDAO.findById(idPihak);
                pk.setInfoAudit(info);
                KodFlagPihak status = kodFlagPihakDAO.findById("TK");
                pk.setKodFlagPihak(status);
                pihakDAO.saveOrUpdate(pk);
            }
        }
        LOG.info(":::::SELESAI DEAKTIFKAN STATUS PIHAK SM::::::");
    }

    private void batalUrusan(Permohonan permohonan, InfoAudit info) throws Exception {
        LOG.info(":::::BATAL URUSAN SM::::::");
        //Batal Urusan
        String idMohon = permohonan.getIdPermohonan();
        PermohonanAtasPerserahan pap = permohonanAtasPerserahanService.findMohonAtasUrusanByIDPermohonan(idMohon);
        Permohonan mohonBatal = pap.getPermohonanBaru();
        mohonBatal.setInfoAudit(info);
        mohonBatal.setStatus("TK");
        permohonanDAO.saveOrUpdate(mohonBatal);

        //Batal Pihak
        List<PermohonanPihak> listPihak = permohonanPihakService.findPermohonanPihakByIdPermohonan(idMohon);
        for (PermohonanPihak pp : listPihak) {
            pp.setInfoAudit(info);
            KodStatusMohonPihak kod = kodStatusMohonPihakDAO.findById("SMT");
            pp.setStatusMohonPihak(kod);
            permohonanPihakService.saveOrUpdate(pp);
        }
        LOG.info(":::::SELESAI BATAL URUSAN SM::::::");
    }

    @Override
    public void afterPushBack(StageContext context) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public String beforePushBack(StageContext context, String proposedOutcome) {
        proposedOutcome = "back";
        return proposedOutcome;
    }
}
