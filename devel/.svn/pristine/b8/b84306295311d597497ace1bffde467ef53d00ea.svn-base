/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.etapp.ws;

import com.google.inject.Inject;
import com.google.inject.Injector;
import com.wideplay.warp.persist.Transactional;
import etanah.dao.EtappBorangEDAO;
import etanah.dao.EtappBorangFDAO;
import etanah.dao.EtappBorangHDAO;
import etanah.dao.EtappHakmilikDAO;
import etanah.dao.EtappPerintahDAO;
import etanah.dao.HakmilikDAO;
import etanah.dao.KodJenisPengenalanDAO;
import etanah.dao.PenggunaDAO;
import etanah.model.CarianHakmilik;
import etanah.model.HakmilikPihakBerkepentingan;
import etanah.model.HakmilikUrusan;
import etanah.model.InfoAudit;
import etanah.model.KodJenisPengenalan;
import etanah.model.Pengguna;
import etanah.model.etapp.EtappBorangE;
import etanah.model.etapp.EtappBorangF;
import etanah.model.etapp.EtappBorangH;
import etanah.model.etapp.EtappHakmilik;
import etanah.model.etapp.EtappPerintah;
import etanah.service.common.HakmilikPihakKepentinganService;
import etanah.service.common.HakmilikUrusanService;
import etanah.view.etanahContextListener;
import etanah.view.etapp.ws.form.MaklumatHakmilikMyEtaPP;
import etanah.view.etapp.ws.form.PemilikEtaPP;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author faidzal
 */
public class PesakaService {

    private static Injector injector = etanahContextListener.getInjector();
    @Inject
    HakmilikDAO hakmilikDAO;
    @Inject
    HakmilikPihakKepentinganService hakmilikPihak;
    @Inject
    etanah.Configuration conf;
    @Inject
    EtappPerintahDAO etappPerintahDAO;
    @Inject
    EtappHakmilikDAO etappHakmilikDAO;
    @Inject
    EtappBorangEDAO etappBorangEDAO;
    @Inject
    EtappBorangFDAO etappBorangFDAO;
    @Inject
    EtappBorangHDAO etappBorangHDAO;
    @Inject
    PenggunaDAO penggunaDAO;
    @Inject
    KodJenisPengenalanDAO jenisPengenalanDAO;
    @Inject
    HakmilikUrusanService hakmilikUrusanService;
    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

    public MaklumatHakmilikMyEtaPP getMaklumatAsasHakmilik(String noResit, String idHakmilik) {
        MaklumatHakmilikMyEtaPP ha = new MaklumatHakmilikMyEtaPP();
        CarianHakmilik carian = findCarianByNoresit(noResit, idHakmilik);

        ha = setHakmilik(hakmilikDAO.findById(carian.getIdHakmilik()));
        ha = setUrusanGadaian(ha);
        ha = setUrusanKaveat(ha);
        ha = setUrusanPajakan(ha);
        ha.setTarikhResit(sdf.format(carian.getInfoAudit().getTarikhMasuk()));
        for (HakmilikPihakBerkepentingan hp : hakmilikPihak.findPihakActiveByHakmilik(hakmilikDAO.findById(carian.getIdHakmilik()))) {
            PemilikEtaPP pe = new PemilikEtaPP();
            pe.setNamaPemilikEtaPP(hp.getPihak().getNama());
            pe.setIdJenisPengenalanEtaPP(hp.getPihak().getJenisPengenalan().getKodMyEtapp());
            pe.setJenisPBEtaPP(hp.getJenis().getKod());
            pe.setNoPengenalanEtaPP(hp.getPihak().getNoPengenalan());
            pe.setBAEtaPP(String.valueOf(hp.getJumlahPembilang()));
            pe.setBBEtaPP(String.valueOf(hp.getJumlahPenyebut()));
            ha.getListPemilikEtaPP().add(pe);
        }
        return ha;
    }

    public CarianHakmilik findCarianByNoresit(String noResit, String idHakmilik) {

        String query = "SELECT ch FROM etanah.model.CarianHakmilik ch"
                + " where ch.permohonanCarian.resit.idDokumenKewangan = :noResit "
                + "and ch.idHakmilik = :idHakmilik";
        Session session = injector.getProvider(Session.class).get();
        Query q = session.createQuery(query);
        q.setString("noResit", noResit);
        q.setString("idHakmilik", idHakmilik);
        return (CarianHakmilik) q.uniqueResult();
    }

    public KodJenisPengenalan findKodJenisPengenalan(String kod) {

        String query = "SELECT kj FROM etanah.model.KodJenisPengenalan kj"
                + " where kj.kodMyEtapp = :kod";
        Session session = injector.getProvider(Session.class).get();
        Query q = session.createQuery(query);

        q.setString("kod", kod);
        return (KodJenisPengenalan) q.uniqueResult();
    }

    private MaklumatHakmilikMyEtaPP setHakmilik(etanah.model.Hakmilik hakmilik) {
        MaklumatHakmilikMyEtaPP ha = new MaklumatHakmilikMyEtaPP();
        ha.setCatatanEtaPP("");
        ha.setIdDaerahEtaPP(hakmilik.getBandarPekanMukim() != null ? hakmilik.getBandarPekanMukim().getDaerah().getKod() : null);
        ha.setIdHakmilikEtaPP(hakmilik.getIdHakmilik());
        ha.setIdJenisHakmilikEtaPP(hakmilik.getKodHakmilik() != null ? hakmilik.getKodHakmilik().getKod() : "");
        ha.setIdKategoriEtaPP(hakmilik.getKategoriTanah() != null ? hakmilik.getKategoriTanah().getKatgMyETapp() : "");
        ha.setIdLuasEtaPP(hakmilik.getKodUnitLuas() != null ? hakmilik.getKodUnitLuas().getKodMyeTapp() : "");
        ha.setIdMukimEtaPP(hakmilik.getBandarPekanMukim() != null ? hakmilik.getBandarPekanMukim().getbandarPekanMukim() : null);
        ha.setIdNegeriEtaPP(conf.getKodNegeri());
       ha.setIdSeksyenEtapp(hakmilik.getSeksyen()!=null?hakmilik.getSeksyen().getSeksyen():null);
        ha.setJenisTanahEtaPP(hakmilik.getKegunaanTanah() != null ? hakmilik.getKegunaanTanah().getKodMyEtapp() : null);
//        ha.setJenisTanah("4");
        ha.setLuasEtaPP(String.valueOf(hakmilik.getLuas()));
        ha.setNoHakmilikEtaPP(hakmilik.getNoHakmilik());
        ha.setNoPTEtaPP(hakmilik.getNoLot());
        ha.setIdKodLot(hakmilik.getLot().getKod());
        ha.setSekatanEtaPP(hakmilik.getSekatanKepentingan() != null ? hakmilik.getSekatanKepentingan().getSekatan() : "");
        ha.setStatusPemilikanEtaPP(hakmilik.getPegangan()+"");
        ha.setSyaratNyataEtaPP(hakmilik.getSyaratNyata() != null ? hakmilik.getSyaratNyata().getSyarat() : "");
        ha.setTanggunganEtaPP("");
        ha.setNoSyitPiawaiEtaPP(hakmilik.getNoLitho());
        return ha;
    }

    public String simpanPerintah(PesakaDataSet1 pesakaDataSet) {
        String ss = null;
        Session s = sessionProvider.get();
        Transaction tx = null;
        EtappPerintah etPerintah = etappPerintahDAO.findById(pesakaDataSet.getNoFail());
        if (etPerintah != null) {
            deleteEtperintah(etPerintah);
            etPerintah = null;
            tx = s.beginTransaction();
            etPerintah = new EtappPerintah();
        } else {

            tx = s.beginTransaction();
            etPerintah = new EtappPerintah();
        }
        etPerintah.setAlamatBicara1(pesakaDataSet.getAlamatBicara1());
        etPerintah.setAlamatBicara2(pesakaDataSet.getAlamatBicara2());
        etPerintah.setAlamatBicara3(pesakaDataSet.getAlamatBicara3());
        etPerintah.setAlamatPemohon1(pesakaDataSet.getAlamatPemohon1());
        etPerintah.setAlamatPemohon2(pesakaDataSet.getAlamatPemohon2());
        etPerintah.setAlamatPemohon3(pesakaDataSet.getAlamatPemohon3());
        etPerintah.setNamaPemohon(pesakaDataSet.getNamaPemohon());
        etPerintah.setNamasimati(pesakaDataSet.getNamasimati());
        etPerintah.setNegeriBicara(pesakaDataSet.getNegeriBicara());
        etPerintah.setNoFail(pesakaDataSet.getNoFail());
        etPerintah.setNoKpPemohon(pesakaDataSet.getNoKpPemohon());
        etPerintah.setNoKpSimati(pesakaDataSet.getNoKpSimati());
        etPerintah.setNoSijilMati(pesakaDataSet.getNoSijilMati());
        etPerintah.setPegawaiBicara(pesakaDataSet.getPegawaiBicara());
        etPerintah.setPoskodBicara(pesakaDataSet.getPoskodBicara());
        etPerintah.setPoskodPemohon(pesakaDataSet.getPoskodPemohon());
        etPerintah.setTarikhMati(pesakaDataSet.getTarikhMati());
        etPerintah.setTarikhPerintah(pesakaDataSet.getTarikhPerintah());
        etPerintah.setTmpBicara(pesakaDataSet.getTmpBicara());
        etPerintah.setInfoAudit(setInfoAudit());
        etPerintah = saveEtappPerintah(etPerintah);
        if (pesakaDataSet.getHakmilikDataSet() != null) {
            for (int i = 0; i < pesakaDataSet.getHakmilikDataSet().length; i++) {
                EtappHakmilik hak = new EtappHakmilik();
                hak.setBasimati(pesakaDataSet.getHakmilikDataSet()[i].getBasimati());
                hak.setBbsimati(pesakaDataSet.getHakmilikDataSet()[i].getBbsimati());
                hak.setJenisHakmilik(pesakaDataSet.getHakmilikDataSet()[i].getJenisHakmilik());
                hak.setHakmilik(hakmilikDAO.findById(pesakaDataSet.getHakmilikDataSet()[i].getIdHakmilik()));
                hak.setKodLuas(pesakaDataSet.getHakmilikDataSet()[i].getKodLuas());
                hak.setKodPembahagian(pesakaDataSet.getHakmilikDataSet()[i].getKodPembahagian());
                hak.setLuas(pesakaDataSet.getHakmilikDataSet()[i].getLuas());
                hak.setNoHakmilik(pesakaDataSet.getHakmilikDataSet()[i].getNoHakmilik());
                hak.setEtappPerintah(etPerintah);
                hak.setInfoAudit(setInfoAudit());
                hak = saveEtappHakmilik(hak);
                if (pesakaDataSet.getHakmilikDataSet()[i].getListEtappBorangE() != null) {
                    for (int q = 0; q < pesakaDataSet.getHakmilikDataSet()[i].getListEtappBorangE().length; q++) {
                        EtappBorangE be = new EtappBorangE();
                        be.setBasimati(pesakaDataSet.getHakmilikDataSet()[i].getListEtappBorangE()[q].getBasimati());
                        be.setBbsimati(pesakaDataSet.getHakmilikDataSet()[i].getListEtappBorangE()[q].getBbsimati());
                        be.setEtappHakmilik(hak);
                        be.setJenisPihakKepentingan(pesakaDataSet.getHakmilikDataSet()[i].getListEtappBorangE()[q].getJenisPihakKepentingan());
                        be.setNamaWaris(pesakaDataSet.getHakmilikDataSet()[i].getListEtappBorangE()[q].getNamaWaris());
                        KodJenisPengenalan jenis = findKodJenisPengenalan(pesakaDataSet.getHakmilikDataSet()[i].getListEtappBorangE()[q].getJenisPengenalan());
                        //jenisPengenalanDAO.findById(pesakaDataSet.getHakmilikDataSet()[i].getListEtappBorangE()[q].getJenisPengenalan());
                        be.setJenisPengenalan(jenis != null ? jenis.getKod() : "");
                        be.setNoKpWaris(pesakaDataSet.getHakmilikDataSet()[i].getListEtappBorangE()[q].getNoKpWaris());
//                        be.set
                        be.setAlamat1(pesakaDataSet.getHakmilikDataSet()[i].getListEtappBorangE()[q].getAlamat1());
                        be.setAlamat2(pesakaDataSet.getHakmilikDataSet()[i].getListEtappBorangE()[q].getAlamat2());
                        be.setAlamat3(pesakaDataSet.getHakmilikDataSet()[i].getListEtappBorangE()[q].getAlamat3());
                        be.setBandar(pesakaDataSet.getHakmilikDataSet()[i].getListEtappBorangE()[q].getBandar());
                        be.setPoskod(pesakaDataSet.getHakmilikDataSet()[i].getListEtappBorangE()[q].getPoskod());
                        be.setNegeri(pesakaDataSet.getHakmilikDataSet()[i].getListEtappBorangE()[q].getNegeri());
                        be.setInfoAudit(setInfoAudit());
                        be = saveEtappBorangE(be);
                        if (pesakaDataSet.getHakmilikDataSet()[i].getListEtappBorangE()[q].getListEtappBorangH() != null) {
                            for (int h = 0; h < pesakaDataSet.getHakmilikDataSet()[i].getListEtappBorangE()[q].getListEtappBorangH().length; h++) {
                                EtappBorangH bh = new EtappBorangH();
                                bh.setEtappHakmilik(hak);
                                bh.setInfoAudit(setInfoAudit());
                                bh.setNamaWaris(pesakaDataSet.getHakmilikDataSet()[i].getListEtappBorangE()[q].getListEtappBorangH()[h].getNamaWaris());
                                bh.setNoKpWaris(pesakaDataSet.getHakmilikDataSet()[i].getListEtappBorangE()[q].getListEtappBorangH()[h].getNoKpWaris());
                                KodJenisPengenalan jenis1 = findKodJenisPengenalan(pesakaDataSet.getHakmilikDataSet()[i].getListEtappBorangE()[q].getListEtappBorangH()[h].getJenisPengenalan());

                                bh.setJenisPengenalan(jenis1 != null ? jenis1.getKod() : "");
                                bh.setJenisPihakKepentingan(pesakaDataSet.getHakmilikDataSet()[i].getListEtappBorangE()[q].getListEtappBorangH()[h].getJenisPihakKepentingan());
                                bh.setAlamat1(pesakaDataSet.getHakmilikDataSet()[i].getListEtappBorangE()[q].getListEtappBorangH()[h].getAlamat1());
                                bh.setAlamat2(pesakaDataSet.getHakmilikDataSet()[i].getListEtappBorangE()[q].getListEtappBorangH()[h].getAlamat2());
                                bh.setAlamat3(pesakaDataSet.getHakmilikDataSet()[i].getListEtappBorangE()[q].getListEtappBorangH()[h].getAlamat3());
                                bh.setBandar(pesakaDataSet.getHakmilikDataSet()[i].getListEtappBorangE()[q].getListEtappBorangH()[h].getBandar());
                                bh.setPoskod(pesakaDataSet.getHakmilikDataSet()[i].getListEtappBorangE()[q].getListEtappBorangH()[h].getPoskod());
                                bh.setNegeri(pesakaDataSet.getHakmilikDataSet()[i].getListEtappBorangE()[q].getListEtappBorangH()[h].getNegeri());
                                bh.setEtappBorangE(be);
                                bh = saveEtappBorangH(bh);
                            }
                        }
//                        if (pesakaDataSet.getHakmilikDataSet()[i].getListEtappBorangE().getListEtappBorangH() != null) {
//                            for (int h = 0; h < pesakaDataSet.getHakmilikDataSet()[i].getListEtappBorangH().length; h++) {
//                                EtappBorangH bh = new EtappBorangH();
//                                bh.setEtappHakmilik(hak);
//                                bh.setInfoAudit(setInfoAudit());
//                                bh.setNamaWaris(pesakaDataSet.getHakmilikDataSet()[i].getListEtappBorangH()[h].getNamaWaris());
//                                bh.setNoKpWaris(pesakaDataSet.getHakmilikDataSet()[i].getListEtappBorangH()[h].getNoKpWaris());
//                                bh = saveEtappBorangH(bh);
//                            }
//                        }
                    }
                }
                if (pesakaDataSet.getHakmilikDataSet()[i].getListEtappBorangF() != null) {
                    for (int r = 0; r < pesakaDataSet.getHakmilikDataSet()[i].getListEtappBorangF().length; r++) {
                        EtappBorangF bf = new EtappBorangF();
                        bf.setNamaWaris(pesakaDataSet.getHakmilikDataSet()[i].getListEtappBorangF()[r].getNamaWaris());
                        bf.setNoKpWaris(pesakaDataSet.getHakmilikDataSet()[i].getListEtappBorangF()[r].getNoKpWaris());
                        KodJenisPengenalan jenis2 = findKodJenisPengenalan(pesakaDataSet.getHakmilikDataSet()[i].getListEtappBorangF()[r].getJenisPengenalan());
                        bf.setJenisPengenalan(jenis2 != null ? jenis2.getKod() : "");
                        bf.setJenisPihakKepentingan(pesakaDataSet.getHakmilikDataSet()[i].getListEtappBorangF()[r].getJenisPihakKepentingan());
                        bf.setEtappHakmilik(hak);
                        bf.setInfoAudit(setInfoAudit());
                        bf.setAlamat1(pesakaDataSet.getHakmilikDataSet()[i].getListEtappBorangF()[r].getAlamat1());
                        bf.setAlamat2(pesakaDataSet.getHakmilikDataSet()[i].getListEtappBorangF()[r].getAlamat2());
                        bf.setAlamat3(pesakaDataSet.getHakmilikDataSet()[i].getListEtappBorangF()[r].getAlamat3());
                        bf.setBandar(pesakaDataSet.getHakmilikDataSet()[i].getListEtappBorangF()[r].getBandar());
                        bf.setPoskod(pesakaDataSet.getHakmilikDataSet()[i].getListEtappBorangF()[r].getPoskod());
                        bf.setNegeri(pesakaDataSet.getHakmilikDataSet()[i].getListEtappBorangF()[r].getNegeri());
                        bf = saveEtappBorangF(bf);
                    }
                }

            }
        }
        tx.commit();
        return ss;
    }

    public InfoAudit setInfoAudit() {
        InfoAudit ia = new InfoAudit();
        Pengguna peng = penggunaDAO.findById("portal");
        ia.setDimasukOleh(peng);
        ia.setTarikhMasuk(new Date());

        return ia;
    }

    @Transactional
    public EtappPerintah saveEtappPerintah(EtappPerintah e) {
        return etappPerintahDAO.saveOrUpdate(e);
    }

    @Transactional
    public EtappHakmilik saveEtappHakmilik(EtappHakmilik e) {
        return etappHakmilikDAO.saveOrUpdate(e);
    }

    @Transactional
    public EtappBorangE saveEtappBorangE(EtappBorangE e) {
        return etappBorangEDAO.saveOrUpdate(e);
    }

    @Transactional
    public EtappBorangF saveEtappBorangF(EtappBorangF e) {
        return etappBorangFDAO.saveOrUpdate(e);
    }

    @Transactional
    public EtappBorangH saveEtappBorangH(EtappBorangH e) {
        return etappBorangHDAO.saveOrUpdate(e);
    }

    private MaklumatHakmilikMyEtaPP setUrusanGadaian(MaklumatHakmilikMyEtaPP ha) {
        String idPerserahanGadaian = "";
        String urusanGadaian = "";
        List<HakmilikUrusan> listHakmilikUrusan = hakmilikUrusanService.findUrusanGadaian(ha.getIdHakmilikEtaPP());
        for (HakmilikUrusan hu : listHakmilikUrusan) {
            if (idPerserahanGadaian.equals("")) {
                idPerserahanGadaian = hu.getIdPerserahan();
                urusanGadaian = hu.getKodUrusan().getNama();
            } else {
                idPerserahanGadaian = idPerserahanGadaian + ", " + hu.getIdPerserahan();
                urusanGadaian = urusanGadaian + ", " + hu.getKodUrusan().getNama();
            }
        }
        ha.setNO_PERSERAHAN_GADAIANEtaPP(idPerserahanGadaian);
        ha.setTanggunganEtaPP(urusanGadaian);
        return ha;
    }

    private MaklumatHakmilikMyEtaPP setUrusanKaveat(MaklumatHakmilikMyEtaPP ha) {
        String idPerserahanKaveat = "";
        String urusanKaveat = "";
        List<HakmilikUrusan> listHakmilikUrusan = hakmilikUrusanService.findUrusanKaveat(ha.getIdHakmilikEtaPP());
        for (HakmilikUrusan hu : listHakmilikUrusan) {
            if (idPerserahanKaveat.equals("")) {
                idPerserahanKaveat = hu.getIdPerserahan();
                urusanKaveat = hu.getKodUrusan().getNama();
            } else {
                idPerserahanKaveat = idPerserahanKaveat + ", " + hu.getIdPerserahan();
                urusanKaveat = urusanKaveat + ", " + hu.getKodUrusan().getNama();
            }
        }
        ha.setNO_PERSERAHAN_KAVEATEtaPP(idPerserahanKaveat);
        ha.setKAVEATEtaPP(urusanKaveat);
        return ha;
    }

    private MaklumatHakmilikMyEtaPP setUrusanPajakan(MaklumatHakmilikMyEtaPP ha) {
        String idPerserahanPajakan = "";
        String urusanPajakan = "";
        List<HakmilikUrusan> listHakmilikUrusan = hakmilikUrusanService.findUrusanPajakan(ha.getIdHakmilikEtaPP());
        for (HakmilikUrusan hu : listHakmilikUrusan) {
            if (idPerserahanPajakan.equals("")) {
                idPerserahanPajakan = hu.getIdPerserahan();
                urusanPajakan = hu.getKodUrusan().getNama();
            } else {
                idPerserahanPajakan = idPerserahanPajakan + ", " + hu.getIdPerserahan();
                urusanPajakan = urusanPajakan + ", " + hu.getKodUrusan().getNama();
            }
        }
        ha.setNO_PERSERAHAN_PAJAKANEtaPP(idPerserahanPajakan);
        ha.setPAJAKANEtaPP(urusanPajakan);
        return ha;
    }

    @Transactional
    private void deleteEtperintah(EtappPerintah etPerintah) {
        Session s = sessionProvider.get();
        Transaction tx = s.beginTransaction();
        for (EtappHakmilik et : etPerintah.getListEtappHakmilik()) {
            for (EtappBorangH bh : et.getListEtappBorangH()) {
                etappBorangHDAO.delete(bh);
            }
            for (EtappBorangE be : et.getListEtappBorangE()) {
                etappBorangEDAO.delete(be);
            }
            for (EtappBorangF bf : et.getListEtappBorangF()) {
                etappBorangFDAO.delete(bf);
            }

            etappHakmilikDAO.delete(et);
        }
        etappPerintahDAO.delete(etPerintah);
        tx.commit();
    }
}
