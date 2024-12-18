
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.stripes.pengambilan;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.dao.PermohonanKertasDAO;
import etanah.dao.PermohonanKertasKandunganDAO;
import etanah.dao.KodDokumenDAO;
import etanah.dao.HakmilikDAO;
import etanah.dao.PemohonDAO;
import etanah.dao.PermohonanDAO;
import etanah.dao.LaporanTanahDAO;
import etanah.model.PermohonanKertas;
import etanah.model.PermohonanKertasKandungan;
import etanah.model.Hakmilik;
import etanah.model.HakmilikPermohonan;
import etanah.model.InfoAudit;
import etanah.model.Pemohon;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.model.Pihak;
import etanah.model.LaporanTanah;
import etanah.model.FasaPermohonan;
import etanah.model.HakmilikUrusan;
import etanah.model.KodDokumen;
import etanah.model.PermohonanLaporanCerun;
import etanah.service.ConsentPtdService;
import etanah.service.PembangunanService;
import etanah.service.PelupusanService;
import etanah.view.etanahActionBeanContext;
import etanah.view.stripes.pembangunan.PembangunanUtility;
import java.util.ArrayList;
import java.util.List;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;
import net.sourceforge.stripes.action.ForwardResolution;
import org.apache.log4j.Logger;

@UrlBinding("/pengambilan/RingkasanMMKNACQ")
public class RisalatMMKNActionBean extends AbleActionBean {

    private static final Logger LOG = Logger.getLogger(RisalatMMKNActionBean.class);
    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    HakmilikDAO hakmilikDAO;
    @Inject
    KodDokumenDAO kodDokumenDAO;
    @Inject
    PemohonDAO pemohonDAO;
    @Inject
    ConsentPtdService conService;
    @Inject
    PembangunanService devService;
    @Inject
    PelupusanService disService;
    @Inject
    LaporanTanahDAO laporanTanahDAO;
    @Inject
    PermohonanKertasDAO permohonanKertasDAO;
    @Inject
    PermohonanKertasKandunganDAO permohonanKertasKandunganDAO;
    @Inject
    PembangunanUtility pembangunanUtility;
    private Permohonan permohonan;
    private List<PermohonanLaporanCerun> plp;
    private Hakmilik hakmilik;
    private Pemohon pemohon;
    private PermohonanKertasKandungan kertasK;
    private PermohonanKertasKandungan kand;
    private PermohonanKertasKandungan pkk;
    private PermohonanKertas mohonKertas;
    private LaporanTanah laporanTanah;
    private String stageId;
    private Pengguna peng;
    private Pihak pihak;
    private List<Pemohon> listPemohon;
    private List<HakmilikPermohonan> hakmilikPermohonanList;
    private String perakuanptg;
    private String tajuk;
    private String nama;
    private String lokasi;
    private String pejTanah;
    private boolean btn = true;
    private String keadaantanah;
    private String lokasitanah;
    private String keputusandahulu;
    private String ulasan;
    private String ulasan1 = "TIADA DATA.";
    private String ulasan2 = "TIADA DATA.";
    private String ulasan3 = "TIADA DATA.";
    private String norisalat;
    private String asas;
    private FasaPermohonan fp;
    private List<Pihak> uniquePemohonList2;
    private KodDokumen kd;
    private String ulasanPtd;
    private String keputusanTerdahulu;
    private String ulasanYB;

    @DefaultHandler
    public Resolution showForm() {
        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        return new JSP("pengambilan/risalatMMKN.jsp").addParameter("tab", "true");
    }

    public Resolution showForm2() {
        return new JSP("pengambilan/risalatMMKN.jsp").addParameter("tab", "true");
    }

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {

        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = permohonanDAO.findById(idPermohonan);

        HakmilikPermohonan hp = new HakmilikPermohonan();
        hakmilikPermohonanList = permohonan.getSenaraiHakmilik();

        peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);

        uniquePemohonList2 = disService.findUniquePemohonByIdPermohonan2(idPermohonan);

        mohonKertas = disService.findPermohonanKertasByIdPermohonanNKodDokumen(permohonan.getIdPermohonan(), "MMKN");
        if (mohonKertas != null) {
            pkk = disService.findByBilNIdKertas(1, mohonKertas.getIdKertas());

//            perakuanptg = kand.getKandungan();
        }

        for (int j = 0; j < uniquePemohonList2.size(); j++) {
            Pihak p = new Pihak();
            p = uniquePemohonList2.get(j);

            if (j == 0) {
                nama = p.getNama();
            }
//            if(j > 0){
//                if(j <= ((uniquePemohonList2.size() + 2)- 4)){
//                    nama = nama + ", " + p.getNama() + ", ";
//                } else if(j == (uniquePemohonList2.size() - 1)){
//                    nama = nama + " dan " + p.getNama();
//                }
//            }
        }

        nama = pembangunanUtility.convertStringtoInitCap(nama);
        LOG.info("nama : " + nama);

//        listPemohon = new ArrayList<Pemohon>();
//        listPemohon = devService.findPemohonByIdPermohonan(idPermohonan);
//        
//        if (!(listPemohon.isEmpty())) {
//            pemohon = listPemohon.get(0);
//        }
//        
//        LOG.info("------listPemohon Size------:"+listPemohon.size());
//        for(int j = 0;j < listPemohon.size(); j++){
//            Pemohon pm = new Pemohon();
//            pm = listPemohon.get(j);
//
//            if(j == 0){
//                nama = pm.getPihak().getNama();
//            }
//            if(j > 0){
//                if(j <= ((listPemohon.size() + 2)- 4)){
//                    nama = nama + ", " + pm.getPihak().getNama() + ", ";
//                } else if(j == (listPemohon.size() - 1)){
//                    nama = nama + " dan " + pm.getPihak().getNama();
//                }
//            }
//        }
//        nama=pembangunanUtility.convertStringtoInitCap(nama);
//        LOG.info("------Pemohon Nama------:"+nama);



        if (!(permohonan.getKodUrusan().getKod().equals("PBMT")) && !(permohonan.getKodUrusan().getKod().equals("PPTR")) && !(permohonan.getKodUrusan().getKod().equals("PBGSA")) && !(permohonan.getKodUrusan().getKod().equals("LPSP")) && !(permohonan.getKodUrusan().getKod().equals("PLPS"))) {
            for (int w = 0; w < hakmilikPermohonanList.size(); w++) {
                hp = permohonan.getSenaraiHakmilik().get(w);
                hakmilik = hakmilikDAO.findById(hp.getHakmilik().getIdHakmilik());

                if (w == 0) {
                    lokasi = hakmilik.getLot().getNama() + " " + hakmilik.getNoLot() + ", " + hakmilik.getBandarPekanMukim().getNama() + ", Daerah " + hakmilik.getDaerah().getNama();
                }

                if (w > 0) {
                    if (w <= ((hakmilikPermohonanList.size() + 2) - 4)) {
                        lokasi = lokasi + ", " + hakmilik.getLot().getNama() + " " + hakmilik.getNoLot() + ", " + hakmilik.getBandarPekanMukim().getNama() + ", Daerah " + hakmilik.getDaerah().getNama() + ", ";
                    } else if (w == (hakmilikPermohonanList.size() - 1)) {
                        lokasi = lokasi + " dan " + hakmilik.getLot().getNama() + " " + hakmilik.getNoLot() + ", " + hakmilik.getBandarPekanMukim().getNama() + ", Daerah " + hakmilik.getDaerah().getNama();
                    }
                }
            }
        }

        if (!(permohonan.getKodUrusan().getKod().equals("PBGSA")) && !(permohonan.getKodUrusan().getKod().equals("PBMT"))) {
            for (int w = 0; w < hakmilikPermohonanList.size(); w++) {
                hp = permohonan.getSenaraiHakmilik().get(w);
                hakmilik = hakmilikDAO.findById(hp.getHakmilik().getIdHakmilik());

                if (w == 0) {
                    lokasi = hakmilik.getLot().getNama() + " " + hakmilik.getNoLot() + ", " + hakmilik.getBandarPekanMukim().getNama() + ", Daerah " + hakmilik.getDaerah().getNama();
                }

                if (w > 0) {
                    if (w <= ((hakmilikPermohonanList.size() + 2) - 4)) {
                        lokasi = lokasi + ", " + hakmilik.getLot().getNama() + " " + hakmilik.getNoLot() + ", " + hakmilik.getBandarPekanMukim().getNama() + ", Daerah " + hakmilik.getDaerah().getNama() + ", ";
                    } else if (w == (hakmilikPermohonanList.size() - 1)) {
                        lokasi = lokasi + " dan " + hakmilik.getLot().getNama() + " " + hakmilik.getNoLot() + ", " + hakmilik.getBandarPekanMukim().getNama() + ", Daerah " + hakmilik.getDaerah().getNama();
                    }
                }
            }
        }

        //keputusanTerdahulu
        if (permohonan.getPermohonanSebelum() != null) {
            if (permohonan.getPermohonanSebelum().getKeputusan().getKod() != null) {
                keputusanTerdahulu = permohonan.getPermohonanSebelum().getKeputusan().getNama();
            } else {
                keputusanTerdahulu = " - ";
            }
        } else {
            keputusanTerdahulu = " - ";
        }

        //ulasanPtd
        ulasanPtd = "Diperakukan Lulus";
//        if (permohonan.getKodUrusan().getKod().equals("PBMT") || permohonan.getKodUrusan().getKod().equals("PPTR") || permohonan.getKodUrusan().getKod().equals("LPSP") || permohonan.getKodUrusan().getKod().equals("PLPS")) {
//            fp = devService.findFasaPermohonanByIdAliran(idPermohonan, "laporan_tanah");
//            if (fp.getKeputusan() != null) {
//                if (fp.getKeputusan().getKod().equalsIgnoreCase("SL")) {
//                    ulasanPtd = "Diperakukan Lulus";
//                } else if (fp.getKeputusan().getKod().equalsIgnoreCase("ST")) {
//                    ulasanPtd = "Tidak Diperakukan";
//                } else {
//                    ulasanPtd = " - ";
//                }
//            } else {
//                ulasanPtd = " - ";
//            }
//        }
//        else if (permohonan.getKodUrusan().getKod().equals("PBGSA") ) {
//            fp = devService.findFasaPermohonanByIdAliran(idPermohonan, "03laporan_tanah");
//            if (fp.getKeputusan() != null) {
//                if (fp.getKeputusan().getKod().equalsIgnoreCase("SL")) {
//                    ulasanPtd = "Diperakukan Lulus";
//                } else if (fp.getKeputusan().getKod().equalsIgnoreCase("ST")) {
//                    ulasanPtd = "Tidak Diperakukan";
//                } else {
//                    ulasanPtd = " - ";
//                }
//            } else {
//                ulasanPtd = " - ";
//            }
//        }
//        else {
//            fp = devService.findFasaPermohonanByIdAliran(idPermohonan, "peraku_ptd");
//            if (fp.getKeputusan() != null) {
//                if (fp.getKeputusan().getKod().equalsIgnoreCase("SL")) {
//                    ulasanPtd = "Diperakukan Lulus";
//                } else if (fp.getKeputusan().getKod().equalsIgnoreCase("ST")) {
//                    ulasanPtd = "Tidak Diperakukan";
//                } else {
//                    ulasanPtd = " - ";
//                }
//            } else {
//                ulasanPtd = " - ";
//            }
//        }

        //ulasanPTG - tarik dari kertas mmkn
        if (permohonan.getKodUrusan().getKod().equals("PLPS")) {
            mohonKertas = disService.findPermohonanKertasByIdPermohonanNKodDokumen(permohonan.getIdPermohonan(), "RMN");
            if (mohonKertas != null) {
                kand = disService.findByBilNIdKertas(6, mohonKertas.getIdKertas());

                perakuanptg = kand.getKandungan();
            }

        }


        if (!permohonan.getKodUrusan().getKod().equalsIgnoreCase("831A")) {

            //keadaanTanah
            String idMohonSblm = permohonan.getPermohonanSebelum().getIdPermohonan();
            LOG.info("------Id Permohonan Sblm------:" + idMohonSblm);

            if (idMohonSblm != null) {
                plp = devService.findLaporCerunListByIdPermohonan(idMohonSblm);
                for (int j = 0; j < plp.size(); j++) {
                    PermohonanLaporanCerun pml = new PermohonanLaporanCerun();
                    pml = plp.get(j);

                    if (j == 0) {
                        keadaantanah = pml.getKodCerunanTanah().getNama();
                    }
                    if (j > 0) {
                        if (j <= ((plp.size() + 2) - 4)) {
                            keadaantanah = keadaantanah + ", " + pml.getKodCerunanTanah().getNama() + ", ";
                        } else if (j == (plp.size() - 1)) {
                            keadaantanah = keadaantanah + " dan " + pml.getKodCerunanTanah().getNama();
                        }
                    }
                    LOG.info("------Keadaan Tanah------:" + keadaantanah);
                }
            } else {
                keadaantanah = "";
            }

            //LokasiTanah
            laporanTanah = devService.findLaporanTanahByIdPermohonan(idMohonSblm);
            if (laporanTanah != null) {
                if (laporanTanah.getKedudukanTanah() != null) {
                    lokasitanah = laporanTanah.getKedudukanTanah();
                } else {
                    lokasitanah = "";
                }
            } else {
                lokasitanah = "";
            }

        }

        if (!(permohonan.getSenaraiKertas().isEmpty())) {

            for (int i = 0; i < permohonan.getSenaraiKertas().size(); i++) {

                PermohonanKertas kertasP = new PermohonanKertas();
                kertasP = permohonan.getSenaraiKertas().get(i);

                if (kertasP.getTajuk().equals("RINGKASAN MMKN")) {
                    for (int j = 0; j < kertasP.getSenaraiKandungan().size(); j++) {

                        kertasK = kertasP.getSenaraiKandungan().get(j);
                        if (kertasK.getBil() == 1) {
                            norisalat = kertasK.getKandungan();
                        } else if (kertasK.getBil() == 2) {
                            lokasitanah = kertasK.getKandungan();
                        } else if (kertasK.getBil() == 3) {
                            keadaantanah = kertasK.getKandungan();
                        } //                        else if (kertasK.getBil() == 4) {
                        //                            keputusandahulu = kertasK.getKandungan();
                        //                        } else if (kertasK.getBil() == 5) {
                        //                            asas = kertasK.getKandungan();
                        //                        }
                        else if (kertasK.getBil() == 4) {
                            perakuanptg = kertasK.getKandungan();
                        } //                        else if (kertasK.getBil() == 7) {
                        //                            keputusanTerdahulu = kertasK.getKandungan();
                        //                        }
                        else if (kertasK.getBil() == 5) {
                            ulasanPtd = kertasK.getKandungan();
                        } else if (kertasK.getBil() == 6) {
                            ulasanYB = kertasK.getKandungan();
                        }

//                        if (kertasK.getBil() == 2) {
//                            norisalat = kertasK.getKandungan();
//                        } else if (kertasK.getBil() == 3) {
//                            lokasitanah = kertasK.getKandungan();
//                        } else if (kertasK.getBil() == 4) {
//                            keadaantanah = kertasK.getKandungan();
//                        } else if (kertasK.getBil() == 5) {
//                            keputusandahulu = kertasK.getKandungan();
//                        } else if (kertasK.getBil() == 6) {
//                            asas = kertasK.getKandungan();
//                        } else if (kertasK.getBil() == 7) {
//                            perakuanptg = kertasK.getKandungan();
//                        }
                    }
                }
            }
        } else {

            if (peng.getKodCawangan().getDaerah().getKod().equals("01")) {
                pejTanah = "Melaka Tengah";
            }
            if (peng.getKodCawangan().getDaerah().getKod().equals("02")) {
                pejTanah = "Jasin";
            }
            if (peng.getKodCawangan().getDaerah().getKod().equals("03")) {
                pejTanah = "Alor Gajah";
            }
        }
    }

    public Resolution simpan() {

        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = permohonanDAO.findById(idPermohonan);

        Pengguna pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        InfoAudit infoAudit = new InfoAudit();

        PermohonanKertas permohonanKertas = new PermohonanKertas();

        if (kertasK != null) {
            permohonanKertas = permohonanKertasDAO.findById(kertasK.getKertas().getIdKertas());
            infoAudit = permohonanKertas.getInfoAudit();
            infoAudit.setDiKemaskiniOleh(pengguna);
            infoAudit.setTarikhKemaskini(new java.util.Date());

        } else {
            infoAudit.setDimasukOleh(pengguna);
            infoAudit.setTarikhMasuk(new java.util.Date());

        }

        ArrayList listUlasan = new ArrayList();
        ArrayList listSubtajuk = new ArrayList();

//        if (tajuk == null || tajuk.equals("")) {
//            tajuk = " - ";
//        }
        if (norisalat == null || norisalat.equals("")) {
            norisalat = " - ";
        }
        if (lokasitanah == null || lokasitanah.equals("")) {
            lokasitanah = " - ";
        }
        if (keadaantanah == null || keadaantanah.equals("")) {
            keadaantanah = " - ";
        }
//        if (keputusandahulu == null || keputusandahulu.equals("")) {
//            keputusandahulu = " - ";
//        }
//        if (asas == null || asas.equals("")) {
//            asas = " - ";
//        }
        if (perakuanptg == null || perakuanptg.equals("")) {
            perakuanptg = " - ";
        }
//        if (keputusanTerdahulu == null || keputusanTerdahulu.equals("")) {
//            keputusanTerdahulu = " - ";
//        }
        if (ulasanPtd == null || ulasanPtd.equals("")) {
            ulasanPtd = " - ";
        }

        if (ulasanYB == null || ulasanYB.equals("")) {
            ulasanYB = " - ";
        }

//        listUlasan.add(tajuk);
        listUlasan.add(norisalat);
        listUlasan.add(lokasitanah);
        listUlasan.add(keadaantanah);
//        listUlasan.add(keputusandahulu);
//        listUlasan.add(asas);
        listUlasan.add(perakuanptg);
//        listUlasan.add(keputusanTerdahulu);
        listUlasan.add(ulasanPtd);
        listUlasan.add(ulasanYB);

//        listSubtajuk.add("");
        listSubtajuk.add("");
        listSubtajuk.add("");
        listSubtajuk.add("");
//        listSubtajuk.add("");
//        listSubtajuk.add("");
        listSubtajuk.add("");
//        listSubtajuk.add("");
        listSubtajuk.add("");
        listSubtajuk.add("");


        if (kertasK != null) {

            if (!kertasK.getKandungan().isEmpty()) {

                for (int j = 0; j < permohonanKertas.getSenaraiKandungan().size(); j++) {

                    PermohonanKertasKandungan kertasKandungan = new PermohonanKertasKandungan();
                    kertasKandungan = permohonanKertas.getSenaraiKandungan().get(j);



                    if (kertasKandungan.getBil() == 1) {
                        kertasKandungan.setKandungan(norisalat);
                    } else if (kertasKandungan.getBil() == 2) {
                        kertasKandungan.setKandungan(lokasitanah);
                    } else if (kertasKandungan.getBil() == 3) {
                        kertasKandungan.setKandungan(keadaantanah);
                    } //                    else if (kertasKandungan.getBil() == 4) {
                    //                        kertasKandungan.setKandungan(keputusandahulu);
                    //                    } else if (kertasKandungan.getBil() == 5) {
                    //                        kertasKandungan.setKandungan(asas);
                    //                    }
                    else if (kertasKandungan.getBil() == 4) {
                        kertasKandungan.setKandungan(perakuanptg);
                    } //                    else if (kertasKandungan.getBil() == 7) {
                    //                        kertasKandungan.setKandungan(keputusanTerdahulu);
                    //                    }
                    else if (kertasKandungan.getBil() == 5) {
                        kertasKandungan.setKandungan(ulasanPtd);
                    } else if (kertasKandungan.getBil() == 6) {
                        kertasKandungan.setKandungan(ulasanYB);
                    }

                    permohonanKertas.setInfoAudit(infoAudit);
                    permohonanKertas.setCawangan(pengguna.getKodCawangan());
                    permohonanKertas.setPermohonan(permohonan);
                    permohonanKertas.setTajuk("RINGKASAN MMKN");
                    kd = kodDokumenDAO.findById("KMN");
                    permohonanKertas.setKodDokumen(kd);
                    kertasKandungan.setInfoAudit(infoAudit);
                    devService.simpanPermohonanKertas(permohonanKertas);
                    devService.simpanPermohonanKertasKandungan(kertasKandungan);
                }
            }

        } else {


            for (int i = 0; i < listUlasan.size(); i++) {

                String data = (String) listUlasan.get(i);
                String subtajuk = (String) listSubtajuk.get(i);

                PermohonanKertasKandungan kk = new PermohonanKertasKandungan();
                permohonanKertas.setInfoAudit(infoAudit);
                permohonanKertas.setCawangan(pengguna.getKodCawangan());
                permohonanKertas.setPermohonan(permohonan);
                kd = kodDokumenDAO.findById("KMN");
                permohonanKertas.setKodDokumen(kd);
                permohonanKertas.setTajuk("RINGKASAN MMKN");
                kk.setKertas(permohonanKertas);
                kk.setBil(i + 1);
                kk.setInfoAudit(infoAudit);
                kk.setKandungan(data);
                kk.setSubtajuk(subtajuk);
                kk.setCawangan(pengguna.getKodCawangan());
                devService.simpanPermohonanKertas(permohonanKertas);
                devService.simpanPermohonanKertasKandungan(kk);
            }
        }

        btn = false;
        addSimpleMessage("Maklumat telah berjaya disimpan.");

        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        return new ForwardResolution("/WEB-INF/jsp/pelupusan/risalatMMKN.jsp").addParameter("tab", "true");
    }

    public Hakmilik getHakmilik() {
        return hakmilik;
    }

    public void setHakmilik(Hakmilik hakmilik) {
        this.hakmilik = hakmilik;
    }

    public Pemohon getPemohon() {
        return pemohon;
    }

    public void setPemohon(Pemohon pemohon) {
        this.pemohon = pemohon;
    }

    public String getStageId() {
        return stageId;
    }

    public void setStageId(String stageId) {
        this.stageId = stageId;
    }

    public Pihak getPihak() {
        return pihak;
    }

    public void setPihak(Pihak pihak) {
        this.pihak = pihak;
    }

    public List<Pemohon> getListPemohon() {
        return listPemohon;
    }

    public void setListPmohon(List<Pemohon> listPemohon) {
        this.listPemohon = listPemohon;
    }

    public PermohonanKertasKandungan getKertasK() {
        return kertasK;
    }

    public void setKertasK(PermohonanKertasKandungan kertasK) {
        this.kertasK = kertasK;
    }

    public LaporanTanah getLaporanTanah() {
        return laporanTanah;
    }

    public void setLaporanTanah(LaporanTanah laporanTanah) {
        this.laporanTanah = laporanTanah;
    }

    public List<HakmilikPermohonan> getHakmilikPermohonanList() {
        return hakmilikPermohonanList;
    }

    public void setHakmilikPermohonanList(List<HakmilikPermohonan> hakmilikPermohonanList) {
        this.hakmilikPermohonanList = hakmilikPermohonanList;
    }

    public Permohonan getPermohonan() {
        return permohonan;
    }

    public void setPermohonan(Permohonan permohonan) {
        this.permohonan = permohonan;
    }

    public Pengguna getPeng() {
        return peng;
    }

    public void setPeng(Pengguna peng) {
        this.peng = peng;
    }

    public String getLokasi() {
        return lokasi;
    }

    public void setLokasi(String lokasi) {
        this.lokasi = lokasi;
    }

    public String getTajuk() {
        return tajuk;
    }

    public void setTajuk(String tajuk) {
        this.tajuk = tajuk;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getPejTanah() {
        return pejTanah;
    }

    public void setPejTanah(String pejTanah) {
        this.pejTanah = pejTanah;
    }

    public boolean isBtn() {
        return btn;
    }

    public void setBtn(boolean btn) {
        this.btn = btn;
    }

    public String getKeadaantanah() {
        return keadaantanah;
    }

    public void setKeadaantanah(String keadaantanah) {
        this.keadaantanah = keadaantanah;
    }

    public String getKeputusandahulu() {
        return keputusandahulu;
    }

    public void setKeputusandahulu(String keputusandahulu) {
        this.keputusandahulu = keputusandahulu;
    }

    public String getLokasitanah() {
        return lokasitanah;
    }

    public void setLokasitanah(String lokasitanah) {
        this.lokasitanah = lokasitanah;
    }

    public String getUlasan() {
        return ulasan;
    }

    public void setUlasan(String ulasan) {
        this.ulasan = ulasan;
    }

    public String getUlasan1() {
        return ulasan1;
    }

    public void setUlasan1(String ulasan1) {
        this.ulasan1 = ulasan1;
    }

    public String getUlasan2() {
        return ulasan2;
    }

    public void setUlasan2(String ulasan2) {
        this.ulasan2 = ulasan2;
    }

    public String getNorisalat() {
        return norisalat;
    }

    public void setNorisalat(String norisalat) {
        this.norisalat = norisalat;
    }

    public String getPerakuanptg() {
        return perakuanptg;
    }

    public void setPerakuanptg(String perakuanptg) {
        this.perakuanptg = perakuanptg;
    }

    public String getAsas() {
        return asas;
    }

    public void setAsas(String asas) {
        this.asas = asas;
    }

    public String getUlasan3() {
        return ulasan3;
    }

    public void setUlasan3(String ulasan3) {
        this.ulasan3 = ulasan3;
    }

    public FasaPermohonan getFp() {
        return fp;
    }

    public void setFp(FasaPermohonan fp) {
        this.fp = fp;
    }

    public List<Pihak> getUniquePemohonList2() {
        return uniquePemohonList2;
    }

    public void setUniquePemohonList2(List<Pihak> uniquePemohonList2) {
        this.uniquePemohonList2 = uniquePemohonList2;
    }

    public KodDokumen getKd() {
        return kd;
    }

    public void setKd(KodDokumen kd) {
        this.kd = kd;
    }

    public List<PermohonanLaporanCerun> getPlp() {
        return plp;
    }

    public void setPlp(List<PermohonanLaporanCerun> plp) {
        this.plp = plp;
    }

    public String getUlasanPtd() {
        return ulasanPtd;
    }

    public void setUlasanPtd(String ulasanPtd) {
        this.ulasanPtd = ulasanPtd;
    }

    public String getKeputusanTerdahulu() {
        return keputusanTerdahulu;
    }

    public void setKeputusanTerdahulu(String keputusanTerdahulu) {
        this.keputusanTerdahulu = keputusanTerdahulu;
    }

    public PermohonanKertas getMohonKertas() {
        return mohonKertas;
    }

    public void setMohonKertas(PermohonanKertas mohonKertas) {
        this.mohonKertas = mohonKertas;
    }

    public PermohonanKertasKandungan getKand() {
        return kand;
    }

    public void setKand(PermohonanKertasKandungan kand) {
        this.kand = kand;
    }

    public PermohonanKertasKandungan getPkk() {
        return pkk;
    }

    public void setPkk(PermohonanKertasKandungan pkk) {
        this.pkk = pkk;
    }

    public String getUlasanYB() {
        return ulasanYB;
    }

    public void setUlasanYB(String ulasanYB) {
        this.ulasanYB = ulasanYB;
    }
}
