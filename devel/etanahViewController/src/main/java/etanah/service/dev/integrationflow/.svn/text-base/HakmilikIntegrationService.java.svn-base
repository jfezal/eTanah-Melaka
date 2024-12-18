/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.service.dev.integrationflow;

import com.google.inject.Inject;
import etanah.dao.HakmilikAsalPermohonanDAO;
import etanah.dao.HakmilikDAO;
import etanah.dao.HakmilikPermohonanB1DAO;
import etanah.dao.HakmilikPermohonanDAO;
import etanah.dao.HakmilikPihakBerkepentinganDAO;
import etanah.dao.HakmilikSebelumDAO;
import etanah.dao.KodBandarPekanMukimDAO;
import etanah.dao.KodCawanganDAO;
import etanah.dao.KodDaerahDAO;
import etanah.dao.KodHakmilikDAO;
import etanah.dao.PermohonanDAO;
import etanah.dao.PermohonanPlotKpsnDAO;
import etanah.model.Hakmilik;
import etanah.model.HakmilikAsalPermohonan;
import etanah.model.HakmilikPermohonan;
import etanah.model.HakmilikPermohonanB1;
import etanah.model.HakmilikPihakBerkepentingan;
import etanah.model.InfoAudit;
import etanah.model.KodBandarPekanMukim;
import etanah.model.KodCawangan;
import etanah.model.KodDaerah;
import etanah.model.KodHakmilik;
import etanah.model.KodKategoriTanah;
import etanah.model.KodKegunaanTanah;
import etanah.model.KodLot;
import etanah.model.NoPt;
import etanah.model.Permohonan;
import etanah.model.PermohonanPlotKpsn;
import etanah.model.PermohonanPlotPelan;
import etanah.sequence.GeneratorIdHakmilik;
import etanah.service.common.PelanB1Service;
import etanah.view.pembangunan.b1b2.validator.HakmilikCopyBean;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.beans.BeanUtils;

/**
 *
 * @author john
 */
public class HakmilikIntegrationService {

    private static final Logger LOG = Logger.getLogger(HakmilikIntegrationService.class);

    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;
    @Inject
    PelanB1Service pelanB1Service;
    @Inject
    GeneratorIdHakmilik generatorIdHakmilik;
    @Inject
    KodCawanganDAO kodCawanganDAO;
    @Inject
    KodHakmilikDAO kodHakmilikDAO;
    @Inject
    KodDaerahDAO kodDaerahDAO;
    @Inject
    KodBandarPekanMukimDAO kodBandarPekanMukimDAO;
    @Inject
    HakmilikDAO hakmilikDAO;
    @Inject
    HakmilikPermohonanDAO hakmilikPermohonanDAO;
    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    HakmilikPihakBerkepentinganDAO hakmilikPihakBerkepentinganDAO;
    @Inject
    PermohonanPlotKpsnDAO permohonanPlotKpsnDAO;
    @Inject
    HakmilikPermohonanB1DAO hakmilikPermohonanB1DAO;
    @Inject
            HakmilikAsalPermohonanDAO hakmilikAsalPermohonanDAO;
    @Inject
            HakmilikSebelumDAO hakmilikSebelumDAO;
    InfoAudit ia = new InfoAudit();

    public String janaHakmilik(RekodHakmilikBaru rekod, String idPermohonan, String idPermohonanPendaftaran, InfoAudit ia
    ) {
        this.ia = ia;
        Session s = sessionProvider.get();
        Transaction tx = s.beginTransaction();
        try {
            int pajakan = rekod.getPajakan();
            KodHakmilik kodHakmilik = kodHakmilikDAO.findById(rekod.getKodHakmilik());
            KodCawangan kodCaw = rekod.getCawangan();
            KodBandarPekanMukim kodBandarPekanMukim = kodBandarPekanMukimDAO.findById(rekod.getBpm());
            KodDaerah daerah = kodDaerahDAO.findById(rekod.getDaerah());
            List<PermohonanPlotPelan> listPlotPelan = findByIdPermohonan(idPermohonan);
            for (PermohonanPlotPelan pp : listPlotPelan) {
                List<NoPt> listNoPt = findByPlotPelan(pp.getIdPlot());
                KodKegunaanTanah gunaTanah = pp.getKegunaanTanah();
                KodKategoriTanah katTanah = pp.getKategoriTanah();
                for (NoPt pt : listNoPt) {
                    Hakmilik hmb2 = new Hakmilik();
                    hmb2.setKodHakmilik(kodHakmilik);
                    hmb2.setDaerah(daerah);
                    hmb2.setBandarPekanMukim(kodBandarPekanMukim);
                    hmb2.setCawangan(kodCaw);
                    hmb2.setTempohPegangan(pajakan);
                    hmb2.setIdHakmilik(generatorIdHakmilik.generate("04", kodCaw, hmb2));
                    String noHakmilik = hmb2.getIdHakmilik().substring(hmb2.getIdHakmilik().length() - 8);

                    hmb2.setNoHakmilik(noHakmilik);
                    KodLot kl = new KodLot();
                    kl.setKod("3");
                    hmb2.setLot(kl);
                    hmb2.setNoLot(formatNoLotIkutPendaftaran(String.valueOf(pt.getNoPt())));
                    hmb2.setLuas(pt.getLuasDilulus());
                    hmb2.setKodUnitLuas(pt.getKodUOM());
                    hmb2.setKategoriTanah(katTanah);
                    hmb2.setKegunaanTanah(gunaTanah);
                    hmb2.setPegangan(pajakan == 0 ? 'S' : 'P');
                    hmb2.setInfoAudit(ia);
                    hakmilikDAO.save(hmb2);
                    setPemilikPM(hmb2, idPermohonan);
                    setPemilikPA(hmb2, idPermohonan);
                    setMohonHakmilik(hmb2, idPermohonanPendaftaran);
                    setMohonHakmilikB1(hmb2, idPermohonan);
//                
                }
            }
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
            Throwable t = e;
            // getting the root cause
            while (t.getCause() != null) {
                t = t.getCause();
            }
            t.printStackTrace();
            LOG.error(t);
            throw new RuntimeException(t);
        }
        return idPermohonanPendaftaran;
    }

    public String janaHakmilikKekal(RekodHakmilikBaru rekod, String idPermohonan, String idPermohonanPendaftaran, String idHakmilik, InfoAudit ia) {
        this.ia = ia;
        Session s = sessionProvider.get();
        Transaction tx = s.beginTransaction();
        try {
            int pajakan = rekod.getPajakan();
            KodHakmilik kodHakmilik = kodHakmilikDAO.findById(rekod.getKodHakmilik());
            KodCawangan kodCaw = rekod.getCawangan();
            KodBandarPekanMukim kodBandarPekanMukim = kodBandarPekanMukimDAO.findById(rekod.getBpm());
            KodDaerah daerah = kodDaerahDAO.findById(rekod.getDaerah());
            HakmilikPermohonanB1 b1 = pelanB1Service.findHakmilikB1ByIdHakmilik(idHakmilik, idPermohonan);
            HakmilikCopyBean bean = new HakmilikCopyBean();
            Hakmilik hmb2 = new Hakmilik();
            BeanUtils.copyProperties(b1.getHakmilik(), bean);
            BeanUtils.copyProperties(bean, hmb2);
            hmb2.setKodHakmilik(kodHakmilik);
            hmb2.setDaerah(daerah);
            hmb2.setBandarPekanMukim(kodBandarPekanMukim);
            hmb2.setCawangan(kodCaw);
            hmb2.setTempohPegangan(pajakan);
            hmb2.setIdHakmilik(generatorIdHakmilik.generate("04", kodCaw, hmb2));
            String noHakmilik = hmb2.getIdHakmilik().substring(hmb2.getIdHakmilik().length() - 8);
            hmb2.setNoHakmilik(noHakmilik);

            hmb2.setLuas(b1.getLuasTerlibat());
            hmb2.setKodUnitLuas(b1.getKodUnitLuas());
            hmb2.setNoLot(formatNoLotIkutPendaftaran(b1.getNoLot()));
            KodLot kl = new KodLot();
            kl.setKod("2");
            hmb2.setLot(kl);
            hmb2.setNoPelan(b1.getNoPelanAkui());

            hmb2.setSekatanKepentingan(b1.getHakmilik().getSekatanKepentingan());
            hmb2.setSyaratNyata(b1.getHakmilik().getSyaratNyata());
            hmb2.setLotBumiputera(b1.getHakmilik().getLotBumiputera());
            hmb2.setLokasi(b1.getHakmilik().getLokasi());
            hmb2.setKategoriTanah(b1.getHakmilik().getKategoriTanah());
            hmb2.setKegunaanTanah(b1.getHakmilik().getKegunaanTanah());
            hmb2.setKodTanah(b1.getHakmilik().getKodTanah());
            hmb2.setMaklumatAtasTanah(b1.getHakmilik().getMaklumatAtasTanah());
            hmb2.setPbt(b1.getHakmilik().getPbt());
            hmb2.setPegangan(b1.getHakmilik().getPegangan());
            hmb2.setTarikhDaftarAsal(b1.getHakmilik().getTarikhDaftarAsal());
            hmb2.setTarikhLuput(b1.getHakmilik().getTarikhLuput());
            hmb2.setTempohPegangan(b1.getHakmilik().getTempohPegangan());
            hakmilikDAO.save(hmb2);
            HakmilikPermohonan hp = setMohonHakmilik(hmb2, idPermohonanPendaftaran);
            List<String> l = new ArrayList<String>();
            l.add(b1.getHakmilik().getIdHakmilik());
            List<String> list = new ArrayList<String>();
            list.add("PA");
            list.add("PM");
            list.add("GD");
            setMohonHakmilikAsal(b1.getHakmilik(), hp);
//            setMohonHakmilikSblm(b1.getHakmilik(),hp);
            setPihakPB(findHakmilikPihakBerkepentinganByIdHakmilik(l, list), hmb2);

            tx.commit();

        } catch (Exception e) {
            tx.rollback();
            Throwable t = e;
            // getting the root cause
            while (t.getCause() != null) {
                t = t.getCause();
            }
            t.printStackTrace();
            LOG.error(t);
            throw new RuntimeException(t);
        }
        return idPermohonanPendaftaran;

    }

    public String formatNoLotIkutPendaftaran(String noLot) {
        NumberFormat formatter = new DecimalFormat("0000000");
        String returnString = new String();
        if (StringUtils.isNotBlank(noLot)) {
            returnString = formatter.format(Integer.parseInt(noLot));
        }
        return returnString;
    }

    private void setPemilikPA(Hakmilik hmb2, String idPermohonan) {
        List<String> list = new ArrayList<String>();
        list.add("PA");
        setPihakPB(findHakmilikPihakBerkepentinganByIdHakmilik(findListHakmilik(idPermohonan), list), hmb2);
    }

    private void setPemilikPM(Hakmilik hmb2, String idPermohonan) {
        List<String> list = new ArrayList<String>();
        list.add("PM");
        list.add("CP");
        setPihakPB(findHakmilikPihakBerkepentinganByIdHakmilik(findListHakmilik(idPermohonan), list), hmb2);
    }

    private HakmilikPermohonan setMohonHakmilik(Hakmilik hmb2, String idPermohonanPendaftaran) {
        HakmilikPermohonan mohonHakmilik = new HakmilikPermohonan();
        Permohonan mohon = permohonanDAO.findById(idPermohonanPendaftaran);
        mohonHakmilik.setPermohonan(mohon);
        mohonHakmilik.setBandarPekanMukimBaru(hmb2.getBandarPekanMukim());
        mohonHakmilik.setCawangan(hmb2.getCawangan());
        mohonHakmilik.setHakmilik(hmb2);
        mohonHakmilik.setJenisPenggunaanTanah(hmb2.getKategoriTanah());
        mohonHakmilik.setTempohPajakan(hmb2.getTempohPegangan());
        mohonHakmilik.setKategoriTanahBaru(hmb2.getKategoriTanah());
        mohonHakmilik.setKodHakmilik(hmb2.getKodHakmilik());
        mohonHakmilik.setLuasBaru(hmb2.getLuas());
        mohonHakmilik.setLot(hmb2.getLot());
        mohonHakmilik.setLuasDiluluskan(hmb2.getLuas());
        mohonHakmilik.setLuasLulusUom(mohonHakmilik.getLuasLulusUom());
        mohonHakmilik.setNoLot(hmb2.getNoLot());
        mohonHakmilik.setPegangan(hmb2.getPegangan().toString());
        mohonHakmilik.setSekatanKepentingan(hmb2.getSekatanKepentingan());
        mohonHakmilik.setSyaratNyata(hmb2.getSyaratNyata());
        mohonHakmilik.setInfoAudit(ia);
        return hakmilikPermohonanDAO.saveOrUpdate(mohonHakmilik);
    }

    public void setPihakPB(List<HakmilikPihakBerkepentingan> senaraiHakmilikPihak, Hakmilik hakmilikBaru) {

        for (HakmilikPihakBerkepentingan hpk : senaraiHakmilikPihak) {
            if (hpk == null || hpk.getAktif() == 'T') {
                continue;
            }
            HakmilikPihakBerkepentingan hpb = new HakmilikPihakBerkepentingan();
            hpb.setHakmilik(hakmilikBaru);
            hpb.setCawangan(hakmilikBaru.getCawangan());
            hpb.setPihakCawangan(hpk.getPihakCawangan());
            hpb.setJenis(hpk.getJenis());
            hpb.setSyerPembilang(hpk.getSyerPembilang());
            hpb.setSyerPenyebut(hpk.getSyerPenyebut());
            hpb.setJumlahPembilang(hpk.getJumlahPembilang());
            hpb.setJumlahPenyebut(hpk.getJumlahPenyebut());
            hpb.setPerserahan(hpk.getPerserahan());
            hpb.setPerserahanKaveat(hpk.getPerserahanKaveat());
            hpb.setKaveatAktif(hpk.getKaveatAktif());
            hpb.setAktif(hpk.getAktif());
            hpb.setPihak(hpk.getPihak());

            hpb.setNama(hpk.getNama());
            hpb.setJenisPengenalan(hpk.getJenisPengenalan());
            hpb.setNoPengenalan(hpk.getNoPengenalan());
            hpb.setAlamat1(hpk.getAlamat1());
            hpb.setAlamat2(hpk.getAlamat2());
            hpb.setAlamat3(hpk.getAlamat3());
            hpb.setAlamat4(hpk.getAlamat4());
            hpb.setPoskod(hpk.getPoskod());
            hpb.setNegeri(hpk.getNegeri());
            hpb.setWargaNegara(hpk.getWargaNegara());
            hpb.setAlamatSurat(hpk.getAlamatSurat());
            hpb.setPenubuhanSyarikat(hpk.getPenubuhanSyarikat());
            //hpb.setSenaraiWaris(hpk.getSenaraiWaris());

            hpb.setInfoAudit(ia);
            hakmilikPihakBerkepentinganDAO.save(hpb);
        }
    }

    public List<HakmilikPihakBerkepentingan> findHakmilikPihakBerkepentinganByIdHakmilik(List listIdHakmilik, List list) {
        String query = "Select h from etanah.model.HakmilikPihakBerkepentingan h where h.hakmilik.idHakmilik in (:listIdHakmilik) "
                + "and h.aktif='Y' ";
        if (list != null && list.size() > 0) {
            query += " and h.jenis.kod in (:listKod)";
        }
        Query q = sessionProvider.get().createQuery(query).setParameterList("listIdHakmilik", listIdHakmilik);
        if (list != null && list.size() > 0) {
            q.setParameterList("listKod", list);
        }

        return q.list();
    }

    private List<PermohonanPlotPelan> findByIdPermohonan(String idPermohonan) {
        String query = "SELECT b FROM etanah.model.PermohonanPlotPelan b where "
                + "b.permohonan.idPermohonan = :idPermohonan "
                + "and b.pemilikan.kod = 'H'";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        return q.list();
    }

    private List<NoPt> findByPlotPelan(long idPlot) {
        String query = "SELECT b FROM etanah.model.NoPt b where "
                + "b.permohonanPlotPelan.idPlot = :idPlot ";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setLong("idPlot", idPlot);
        return q.list();
    }

    private List<String> findListHakmilik(String idPermohonan) {
        String query = "SELECT b.hakmilik.idHakmilik  FROM etanah.model.HakmilikPermohonan b where "
                + "b.permohonan.idPermohonan = :idPermohonan ";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        return q.list();
    }

    public RekodHakmilikBaru findRekodHakmilikBaruSementaraByIdPermohonan(Long idPlotKpsn) {
        PermohonanPlotKpsn kpsn = permohonanPlotKpsnDAO.findById(idPlotKpsn);
        RekodHakmilikBaru baru = new RekodHakmilikBaru();
        baru.setBpm(kpsn.getBpm().getKod());
        baru.setCawangan(kpsn.getCawangan());
        baru.setDaerah(kpsn.getDaerah().getKod());
        baru.setKodHakmilik(kpsn.getKodHakmilikSementara().getKod());
        baru.setPajakan(kpsn.getTempohPajakan());
        if (kpsn.getSeksyen() != null) {
            baru.setSeksyen(kpsn.getSeksyen().getSeksyen());
        }
        return baru;
    }

    public List findIdPlotByIdPermohonan(String idPermohonan) {
        String query = "SELECT b FROM etanah.model.PermohonanPlotPelan b where "
                + "b.permohonan.idPermohonan = :idPermohonan ";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idPermohonan", idPermohonan);

        return q.list();
    }

    private void setMohonHakmilikB1(Hakmilik hmb2, String idPermohonan) {
        HakmilikPermohonanB1 mohonHakmilikB1 = new HakmilikPermohonanB1();
        Permohonan p = permohonanDAO.findById(idPermohonan);
        mohonHakmilikB1.setHakmilik(hmb2);
        mohonHakmilikB1.setPermohonan(p);
        hakmilikPermohonanB1DAO.save(mohonHakmilikB1);
    }

    public RekodHakmilikBaru findRekodHakmilikBaruKekalByIdPermohonan(long idPlotKpsn) {
        PermohonanPlotKpsn kpsn = permohonanPlotKpsnDAO.findById(idPlotKpsn);
        RekodHakmilikBaru baru = new RekodHakmilikBaru();
        baru.setBpm(kpsn.getBpm().getKod());
        baru.setCawangan(kpsn.getCawangan());
        baru.setDaerah(kpsn.getDaerah().getKod());
        baru.setKodHakmilik(kpsn.getKodHakmilikTetap().getKod());
        baru.setPajakan(kpsn.getTempohPajakan());
        if (kpsn.getSeksyen() != null) {
            baru.setSeksyen(kpsn.getSeksyen().getSeksyen());
        }
        return baru;
    }

    public List<HakmilikPermohonan> findListHakmilikLanjutTempoh(String idPermohonan) {
String query = "SELECT b FROM etanah.model.HakmilikPermohonan b where "
                + "b.permohonan.idPermohonan = :idPermohonan and b.lanjutTempoh ='Y'";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idPermohonan", idPermohonan);

        return q.list();
    }

    private void setMohonHakmilikAsal(Hakmilik hakmilik, HakmilikPermohonan hp) {

        HakmilikAsalPermohonan hap = new HakmilikAsalPermohonan();
        hap.setHakmilik(hp.getHakmilik());
        hap.setHakmilikPermohonan(hp);
        hap.setHakmilikSejarah(hakmilik.getIdHakmilik());
        hap.setInfoAudit(ia);
        hakmilikAsalPermohonanDAO.save(hap);    }

    private void setMohonHakmilikSblm(Hakmilik hakmilik, HakmilikPermohonan hp) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
