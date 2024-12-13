/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.strata;

import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.dao.AduanLokasiDAO;
import etanah.dao.AduanStrataDAO;
import etanah.dao.DokumenDAO;
import etanah.dao.FolderDokumenDAO;
import etanah.dao.HakmilikDAO;
import etanah.dao.HakmilikSebelumDAO;
import etanah.dao.KodDokumenDAO;
import etanah.dao.KodNegeriDAO;
import etanah.dao.KodPBTDAO;
import etanah.dao.KodUrusanDAO;
import etanah.dao.PermohonanDAO;
import etanah.model.AduanStrata;
import etanah.model.Dokumen;
import etanah.model.Hakmilik;
import etanah.model.HakmilikPermohonan;
import etanah.model.HakmilikPihakBerkepentingan;
import etanah.model.HakmilikSebelum;
import etanah.model.InfoAudit;
import etanah.model.KodCawangan;
import etanah.model.KodPBT;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.model.Pihak;
import etanah.model.SiasatanAduanImej;
import etanah.service.StrataPtService;
import etanah.service.common.HakmilikPihakKepentinganService;
import etanah.service.common.HakmilikService;
import etanah.service.common.PihakService;
import etanah.view.BasicTabActionBean;
import etanah.view.ListUtil;
import etanah.view.etanahActionBeanContext;
import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.FileBean;
import net.sourceforge.stripes.action.RedirectResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import etanah.model.KodUrusan;

/**
 *
 * @author zadhirul.farihim
 */
@UrlBinding("/strata/penguatkuasaan_strata")
public class LaporanSiasatKuatkuasaStrataActionBean extends BasicTabActionBean {

    @Inject
    private HakmilikDAO hakmilikDAO;
    @Inject
    private PermohonanDAO permohonanDAO;
    @Inject
    private StrataPtService strService;
    @Inject
    private ListUtil listUtil;
    @Inject
    private KodUrusanDAO kodUrusanDAO;
    @Inject
    private AduanLokasiDAO aduanLokasiDAO;
    @Inject
    private KodNegeriDAO kodNegeriDAO;
    @Inject
    private FolderDokumenDAO folderDokumenDAO;
    @Inject
    private com.google.inject.Provider<Session> sessionProvider;
    @Inject
    private KodDokumenDAO kodDokumenDAO;
    @Inject
    private AduanStrataDAO aduanStrataDAO;
    @Inject
    private KodPBTDAO kodPBTDAO;
    @Inject
    private DokumenDAO dokumenDAO;
    @Inject
    private HakmilikSebelumDAO hakmlikSebelumDAO;
    @Inject
    private etanah.Configuration conf;
    @Inject
    private CommonService comm;
    @Inject
    private HakmilikPihakKepentinganService hakmilikPihakKepentinganService;
    @Inject
    private PihakService PihakService;
    @Inject
    private HakmilikService hakmilikService;
    private Permohonan permohonan;
    private KodCawangan kodCawangan;
    private AduanStrata aduanStrata;
    private Hakmilik hakmilik;
    private HakmilikPermohonan hakmilikPermohonan;
    private HakmilikPihakBerkepentingan hmPihak;
    private Hakmilik hakmilikMuktamad;
    private static final Logger LOG = Logger.getLogger(LaporanSiasatKuatkuasaStrataActionBean.class);
    private String noCF;
    private String tarikhCF;
    private String noRujukanPelanBngn;
    private String kawPBT;
    private String noLot;
    private String lokasi;
    private String alamat1;
    private String alamat2;
    private String alamat3;
    private String alamat4;
    private String poskod;
    private String negeri;
    private String idHakmilik;
    private String namaPenjual;
    private String namaPemaju;
    private String namaPembeli;
    private String idHakmilikTanah;
    private String tarikhTandatangan;
    private String kodStatusHakmilik;
    private String ulasan;
    private String tarikhSiasat;
    private String kenderaanSiasat;
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    private Dokumen dokumen;
    private FileBean fileToBeUpload;
    private SiasatanAduanImej imejSiasatan = new SiasatanAduanImej();
    private String catatan = "";
    private List<SiasatanAduanImej> senaraiImejSiasatan = new ArrayList<SiasatanAduanImej>();
    private List<HakmilikPihakBerkepentingan> listHakmilikPihak;
    private Pihak pihak;
    private String idPihak;
    private List<Hakmilik> listHakmilikSambungan;
    private List<Permohonan> listMohonPecahSempadan;
    private String idHakmilikMuktamad;
    private String kodUrusan;
    private Integer noHakmilik;
    private Integer noLotHakmilik;
    

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        LOG.info("----Rehydrate-----");
        LOG.info("----idPermohonan : " + idPermohonan + " -----");

        if (idPermohonan != null) {
            permohonan = permohonanDAO.findById(idPermohonan);
            kodUrusan = permohonan.getKodUrusan().getKod();
            aduanStrata = strService.findAduanStrataIdMohon(idPermohonan);

            if (aduanStrata != null) {
                LOG.debug("Maklumat aduan strata Found. id_aduan_strata: " + aduanStrata.getIdAduanStrata());

                if (aduanStrata.getHakmilik() != null) {
                    idHakmilik = aduanStrata.getHakmilik().getIdHakmilik();
                }
                if (idHakmilik != null) {
                    hakmilik = hakmilikDAO.findById(idHakmilik);
                }
                if (hakmilik != null) {
                    kodStatusHakmilik = hakmilik.getKodStatusHakmilik().getKod();
                    listHakmilikPihak = hakmilikPihakKepentinganService.findPertanyaanHmkActiveByHakmilik(hakmilik);
                }

                noCF = aduanStrata.getCfNoSijil();
                if (aduanStrata.getCfTarikhKeluar() != null) {
                    tarikhCF = sdf.format(aduanStrata.getCfTarikhKeluar());
                }
                if (aduanStrata.getTarikhTandatanganPerjanjianJualBeli() != null) {
                    tarikhTandatangan = sdf.format(aduanStrata.getTarikhTandatanganPerjanjianJualBeli());
                }

            }
//
//            if (!permohonan.getSenaraiHakmilik().isEmpty()) {
//                LOG.info("senarai Mohon_Hakmilik not empty");
//                idHakmilik = permohonan.getSenaraiHakmilik().get(0).getHakmilik().getIdHakmilik();
//                hakmilik = hakmilikDAO.findById(idHakmilik);
//
//                if (hakmilik.getKodStatusHakmilik() != null) {
//                    kodStatusHakmilik = hakmilik.getKodStatusHakmilik().getKod();
//                }
//
//                listHakmilikPihak = hakmilikPihakKepentinganService.findHakmilikPihakByKod((permohonan.getSenaraiHakmilik().get(0)).getHakmilik(), "PM");
//
//            }
            senaraiImejSiasatan = strService.findSiasatanImejByIdPermohonan(idPermohonan);

        }

    }

    @DefaultHandler
    public Resolution showAduan() {

        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = permohonanDAO.findById(idPermohonan);
        aduanStrata = strService.findAduanStrataIdMohon(idPermohonan);
        if (aduanStrata != null) {
            LOG.debug("Maklumat aduan strata Found. id_aduan_strata: " + aduanStrata.getIdAduanStrata());

            if (aduanStrata.getTarikhSiasatan() != null) {
                tarikhSiasat = sdf.format(aduanStrata.getTarikhSiasatan());
            }

            if (aduanStrata.getCfNoSijil() != null) {
                noCF = aduanStrata.getCfNoSijil();
            }
            if (aduanStrata.getCfTarikhKeluar() != null) {
                tarikhCF = sdf.format(aduanStrata.getCfTarikhKeluar());
                LOG.info("Tarikh CF = " + tarikhCF);
            }
            noRujukanPelanBngn = aduanStrata.getNoRujukanPelanBangunan();
            if (aduanStrata.getKodPbt() != null) {
                kawPBT = aduanStrata.getKodPbt().getKod();
                LOG.info("Kawasan PBT = " + kawPBT);
            }

            kenderaanSiasat = aduanStrata.getKenderaanSiasatan();
            lokasi = aduanStrata.getLokasi();
            alamat1 = aduanStrata.getAlamat1();
            alamat2 = aduanStrata.getAlamat2();
            alamat3 = aduanStrata.getAlamat3();
            alamat4 = aduanStrata.getAlamat4();
            poskod = aduanStrata.getPoskod();
            if (aduanStrata.getKodNegeri() != null) {
                negeri = aduanStrata.getKodNegeri().getKod();
                LOG.info("alamt negeri = " + negeri);
            }
        }

        return new JSP("strata/kuatkuasa/aduan.jsp").addParameter("tab", "true");
    }

    public Resolution isiSemula(){
        LOG.info("isi semula start here");
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        aduanStrata = strService.findAduanStrataIdMohon(idPermohonan);
        if(aduanStrata != null){
            LOG.info("delete operation here");
            //strService.deleteAduanStrata(idPermohonan);
            aduanStrata.setKodNegeri(null);
            aduanStrata.setPoskod("");
            aduanStrata.setAlamat4("");
            aduanStrata.setAlamat3("");
            aduanStrata.setAlamat2("");
            aduanStrata.setAlamat1("");
            aduanStrata.setLokasi("");
            aduanStrata.setCfNoSijil(null);
            aduanStrata.setCfTarikhKeluar(null);
            aduanStrata.setKodPbt(null);
            aduanStrata.setNoRujukanPelanBangunan("");
            aduanStrata.setKenderaanSiasatan("");
            aduanStrata.setTarikhSiasatan(null);
            strService.simpanAduanStrata(aduanStrata);
        }
        
        LOG.info("isi semula end here");
        return new RedirectResolution(LaporanSiasatKuatkuasaStrataActionBean.class, "showAduan");
    }
    
    public Resolution resetForm() {
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = permohonanDAO.findById(idPermohonan);
        Pengguna pguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        InfoAudit ia = new InfoAudit();
        kodCawangan = strService.getkodCawangan(pguna.getKodCawangan().getKod());

        AduanStrata aduan = strService.findAduanStrataIdMohon(idPermohonan);
        aduan.setNamaPemaju(null);
        aduan.setNamaPembeli(null);
        aduan.setTarikhTandatanganPerjanjianJualBeli(null);
        LOG.info("===IN SAVING ADUAN_STRATA===");
        try {
            strService.simpanAduanStrata(aduan);
            //addSimpleMessage("Maklumat pemaju dan pembeli telah berjaya disimpan.");
            LOG.info("::BERJAYA DIKOSONGKAN:: id_aduan_strata = " + aduan.getIdAduanStrata());
        } catch (Exception e) {
            LOG.error("::GAGAL DIKOSONGKAN:: error = " + e);
            addSimpleError("Maklumat gagal dikosongkan. " + e);
        }



        return new RedirectResolution(LaporanSiasatKuatkuasaStrataActionBean.class, "showHakmilik");
    }

    public Resolution simpanAduan() throws ParseException {

        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = permohonanDAO.findById(idPermohonan);
        Pengguna pguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        InfoAudit ia = new InfoAudit();
        kodCawangan = strService.getkodCawangan(pguna.getKodCawangan().getKod());
        if (permohonan.getPermohonanSebelum() == null) {
            hakmilikPermohonan = strService.findMohonHakmilik(idPermohonan);
        } else if(permohonan.getPermohonanSebelum() != null){
            hakmilikPermohonan = strService.findMohonHakmilik(permohonan.getPermohonanSebelum().getIdPermohonan());
        }
        AduanStrata aduan = strService.findAduanStrataIdMohon(idPermohonan);
        if (aduan != null) {
            LOG.debug("Maklumat aduan strata Found. id_aduan_strata: " + aduan.getIdAduanStrata());
            ia = aduan.getInfoAudit();
            ia.setDiKemaskiniOleh(pguna);
            ia.setTarikhKemaskini(new java.util.Date());
        } else {
            LOG.info("Maklumat gagal dijumpai. Create new AduanStrata()");
            aduan = new AduanStrata();
            ia.setDimasukOleh(pguna);
            ia.setTarikhMasuk(new java.util.Date());
        }
        aduan.setCfNoSijil(noCF);
        aduan.setNoRujukanPelanBangunan(noRujukanPelanBngn);
        aduan.setKenderaanSiasatan(kenderaanSiasat);
        if (tarikhSiasat != null) {
            aduan.setTarikhSiasatan(sdf.parse(tarikhSiasat));
        }
        if (tarikhCF != null) {
            aduan.setCfTarikhKeluar(sdf.parse(tarikhCF));
        }
        if (kawPBT != null) {
            KodPBT kodPBT = kodPBTDAO.findById(kawPBT);
            LOG.debug("KOD PBT : " + kodPBT);
            aduan.setKodPbt(kodPBT);
        } else {
            LOG.info("else : KawPBT is null");
            aduan.setKodPbt(null);
        }
        aduan.setLokasi(lokasi);
        aduan.setAlamat1(alamat1);
        aduan.setAlamat2(alamat2);
        aduan.setAlamat3(alamat3);
        aduan.setAlamat4(alamat4);
        aduan.setPoskod(poskod);
        if (negeri != null && !negeri.equals("")) {
            aduan.setKodNegeri(kodNegeriDAO.findById(negeri));
        } else {
            LOG.info("else : Kod negeri null");
            aduan.setKodNegeri(null);
        }
        aduan.setInfoAudit(ia);
        aduan.setCawangan(kodCawangan);
        aduan.setPermohonan(permohonan);

        if (permohonan.getKodUrusan().getKod().equals("P14A") || permohonan.getKodUrusan().getKod().equals("P22B")) {
            aduan.setHakmilik(hakmilikPermohonan.getHakmilik());
        }


        LOG.info("hakmilikPermohonan :  " + hakmilikPermohonan.getHakmilik().getNoHakmilik());
        LOG.info("===IN SAVING ADUAN_STRATA===");
        try {
            strService.simpanAduanStrata(aduan);
            addSimpleMessage("Maklumat aduan telah berjaya disimpan.");
            LOG.info("::SIMPAN BERJAYA:: id_aduan_strata = " + aduan.getIdAduanStrata());
        } catch (Exception e) {
            LOG.error("::GAGAL DISIMPAN:: error = " + e);
            addSimpleError("Maklumat gagal disimpan. " + e);
        }
        return new RedirectResolution(LaporanSiasatKuatkuasaStrataActionBean.class, "showAduan");
    }

    public Resolution showHakmilik() {
        LOG.info("inside : Resolution showHakmilik");
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");

        if (idPermohonan != null) {
            permohonan = permohonanDAO.findById(idPermohonan);
            


            aduanStrata = strService.findAduanStrataIdMohon(idPermohonan);
            

            
            
            if (aduanStrata != null) {
                noHakmilik = Integer.parseInt(aduanStrata.getHakmilik().getNoHakmilik());
                noLotHakmilik = Integer.parseInt(aduanStrata.getHakmilik().getNoLot()); 

                LOG.debug("Maklumat aduan strata Found. id_aduan_strata: " + aduanStrata.getIdAduanStrata());
//                idHakmilik = permohonan.getSenaraiHakmilik().get(0).getHakmilik().getIdHakmilik();

                if (aduanStrata.getHakmilik() != null) {
                    idHakmilik = aduanStrata.getHakmilik().getIdHakmilik();                  
                }
                if (idHakmilik != null) {
                    hakmilik = hakmilikDAO.findById(idHakmilik);
                }

                /**
                 * Get the Hakmilik sambungan for given idHakmilik
                 */
                listHakmilikSambungan = hakmilikService.getHakmilikSambungan(idHakmilik);
                if (!listHakmilikSambungan.isEmpty()) {
                    LOG.info("Hakmilik Sambunagn : " + listHakmilikSambungan);
                }


                if (hakmilik != null && hakmilik.getKodStatusHakmilik() != null) {
                    LOG.info("KOD STATUS HAKMILIK = " + hakmilik.getKodStatusHakmilik().getKod());
                    if (hakmilik.getKodStatusHakmilik().getKod().equals("B")) {
                        kodStatusHakmilik = "B";
                    } else {
                        kodStatusHakmilik = "D";
                    }

                }


                /**
                 * Get the Permohonan based on Idhakmilik in HakmilikPermohonan
                 * contain KodUrusan PPCS
                 */
                listMohonPecahSempadan = strService.findPermohonanPecahSempadan(idPermohonan);
                if (!listMohonPecahSempadan.isEmpty()) {
                    LOG.info("Permohonan PPCS : " + listMohonPecahSempadan);
                }

                namaPenjual = aduanStrata.getNamaPenjual();
                namaPemaju = aduanStrata.getNamaPemaju();
                namaPembeli = aduanStrata.getNamaPembeli();
                if (aduanStrata.getHakmilik() != null) {
                    idHakmilikTanah = aduanStrata.getHakmilik().getIdHakmilik();
                }
                if (aduanStrata.getTarikhTandatanganPerjanjianJualBeli() != null) {
                    tarikhTandatangan = sdf.format(aduanStrata.getTarikhTandatanganPerjanjianJualBeli());
                }

            }
        }

        return new JSP("strata/kuatkuasa/kemasukan_maklumat_hakmilik.jsp").addParameter("tab", "true");
    }

    public Resolution maklumatPemilikDetail() {
        idPihak = (String) getContext().getRequest().getParameter("idPihak");
        LOG.info("ID PIHAK = " + idPihak);
        hmPihak = PihakService.getMaklumatByidHMidPhk(hakmilik , Long.valueOf(idPihak));
        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        return new JSP("strata/kuatkuasa/maklumat_terperinci_pemilik.jsp").addParameter("tab", "true");
    }

    public Resolution maklumatHakmilikMuktamad() {
        idHakmilikMuktamad = (String) getContext().getRequest().getParameter("idHakmilikMuktamad");
        if (idHakmilikMuktamad != null) {
            LOG.info("ID HAKMILIK MUKTAMAD = " + idHakmilikMuktamad);
            hakmilikMuktamad = hakmilikDAO.findById(idHakmilikMuktamad);
        }
        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        return new JSP("strata/kuatkuasa/papar_hakmilik_muktamad.jsp").addParameter("tab", "true");
    }

    public Resolution simpanMaklumatAduanHakmilik() throws ParseException {
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = permohonanDAO.findById(idPermohonan);
        Pengguna pguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        InfoAudit ia = new InfoAudit();
        kodCawangan = strService.getkodCawangan(pguna.getKodCawangan().getKod());

        AduanStrata aduan = strService.findAduanStrataIdMohon(idPermohonan);
//        Hakmilik hm = hakmilikDAO.findById(idHakmilikTanah);
        if (aduan != null) {
            ia = aduan.getInfoAudit();
            ia.setDiKemaskiniOleh(pguna);
            ia.setTarikhKemaskini(new java.util.Date());


        } else {
            aduan = new AduanStrata();
            ia.setDimasukOleh(pguna);
            ia.setTarikhMasuk(new java.util.Date());

        }
        aduan.setNamaPenjual(namaPenjual);
        aduan.setNamaPemaju(namaPemaju);
        aduan.setNamaPembeli(namaPembeli);
//        aduan.setHakmilik(hm);
        if (tarikhTandatangan != null) {
            aduan.setTarikhTandatanganPerjanjianJualBeli(sdf.parse(tarikhTandatangan));
        }
        aduan.setInfoAudit(ia);
        aduan.setCawangan(kodCawangan);
        aduan.setPermohonan(permohonan);
        LOG.info("===IN SAVING ADUAN_STRATA===");
        try {
            strService.simpanAduanStrata(aduan);
            addSimpleMessage("Maklumat pemaju dan pembeli telah berjaya disimpan.");
            LOG.info("::SIMPAN BERJAYA:: id_aduan_strata = " + aduan.getIdAduanStrata());
        } catch (Exception e) {
            LOG.error("::GAGAL DISIMPAN:: error = " + e);
            addSimpleError("Maklumat gagal disimpan. " + e);
        }



        return new RedirectResolution(LaporanSiasatKuatkuasaStrataActionBean.class, "showHakmilik");
    }

    public Resolution showImej() {
        LOG.info("inside : Resolution showImej");
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = permohonanDAO.findById(idPermohonan);

        senaraiImejSiasatan = strService.findSiasatanImejByIdPermohonan(idPermohonan);

        return new JSP("strata/kuatkuasa/muatnaik_imej.jsp").addParameter("tab", "true");
    }

    // refresh main browser when popup save image
    public Resolution showRefresh() {
        return new JSP("strata/kuatkuasa/muatnaik_imej.jsp").addParameter("tab", "true");
    }

    public Resolution muatNaikPopup() {
        String idPihak = getContext().getRequest().getParameter("idPihak");
        return new JSP("strata/kuatkuasa/muatnaik_popup.jsp").addParameter("popup", "true");
    }

    //added for delete images in laporan_tanah
    public Resolution hapusImej() {

        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = permohonanDAO.findById(idPermohonan);
        Long folderId = 0L;
        if (permohonan.getFolderDokumen() != null) {
            folderId = permohonan.getFolderDokumen().getFolderId();
        }

        LOG.info("---------------idPermohonan:--------" + idPermohonan);
        Long dokumenId = Long.parseLong(getContext().getRequest().getParameter("idDokumen"));
        LOG.info("---------------idDokumen:--------" + dokumenId);
        dokumen = dokumenDAO.findById(dokumenId);
        String docPath = conf.getProperty("document.path");
        File file = new File(docPath + dokumen.getNamaFizikal());
        file.delete();

        strService.deleteSiasatanAduanImejByIdDokumen(dokumenId);
        strService.deleteKandunganFolderByIdDokumen(folderId, dokumenId);
        strService.deleteDokumenCapaianByIdDokumen(dokumenId);
        strService.deleteAll2(dokumen);
        LOG.info("---------------Deleted idDokumen is::--------" + dokumenId);
        String msg = "Imej telah berjaya dipadamkan.";
        addSimpleMessage(msg);
        return new RedirectResolution(LaporanSiasatKuatkuasaStrataActionBean.class, "showImej");
    }

    public Resolution muatNaik() throws Exception {

        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        Pengguna pguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);

        String error = "";
        String msg = "";

        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        InfoAudit ia = new InfoAudit();
        ia.setTarikhMasuk(new Date());
        ia.setDimasukOleh(pguna);


        String dokumenPath = conf.getProperty("document.path");
        permohonan = permohonanDAO.findById(idPermohonan);
        LOG.info("INI ADALAH FOLDER DOKUMEN" + permohonan.getFolderDokumen().getFolderId());

        dokumenPath = dokumenPath + (dokumenPath.endsWith(File.separator) ? "" : File.separator) + permohonan.getFolderDokumen().getFolderId();
        if (fileToBeUpload == null) {
            addSimpleError("Sila pilih fail imej untuk dimuatnaik.");
            return new JSP("strata/kuatkuasa/muatnaik_popup.jsp").addParameter("popup", "true");
        } else if (!(fileToBeUpload.getFileName().endsWith(".jpg") || fileToBeUpload.getFileName().endsWith(".JPG") || fileToBeUpload.getFileName().endsWith(".bmp") || fileToBeUpload.getFileName().endsWith(".png") || fileToBeUpload.getFileName().endsWith(".gif"))) {
            LOG.info("----------------fileToBeUpload---else-----------:" + fileToBeUpload.getFileName());
            addSimpleError("Sila pilih fail imej dalam format *.jpg, *.bmp, *.png, *.gif untuk dimuatnaik.");
            return new JSP("strata/kuatkuasa/muatnaik_popup.jsp").addParameter("popup", "true");
        }
        LOG.info("----------------fileToBeUpload--------------:" + fileToBeUpload.getFileName());

        Dokumen doc = comm.saveDokumen(dokumenPath, fileToBeUpload, permohonan.getFolderDokumen().getFolderId(), ia, permohonan);

        /* model already updated by encik suhairi*/
        imejSiasatan.setPermohonan(permohonan);
        imejSiasatan.setInfoAudit(ia);
        imejSiasatan.setCatatan(catatan);
        imejSiasatan.setDokumen(doc);
        imejSiasatan.setCawangan(peng.getKodCawangan());
        LOG.info("===IN SAVING IMEJ===");
        strService.simpanSiasatanAduanImej(imejSiasatan);


        LOG.info("simpanMuatNaik::finish");
        addSimpleMessage("Muat naik fail berjaya.");
        rehydrate();
        return new JSP("strata/kuatkuasa/muatnaik_popup.jsp").addParameter("popup", "true");

    }

    public Resolution showUlasan() {
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = permohonanDAO.findById(idPermohonan);
        aduanStrata = strService.findAduanStrataIdMohon(idPermohonan);
        if (aduanStrata != null) {
            LOG.debug("Maklumat aduan strata Found. id_aduan_strata: " + aduanStrata.getIdAduanStrata());
            if (aduanStrata.getUlasan() != null) {
                ulasan = aduanStrata.getUlasan();
                LOG.debug("ULASAN = " + ulasan);
            } else {
                LOG.info("else : ulasan is null----");
            }
        }
        return new JSP("strata/kuatkuasa/kemasukan_ulasan.jsp").addParameter("tab", "true");
    }

    public Resolution simpanUlasan() {
        LOG.info("---------- simpan ulasan ------------");
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = permohonanDAO.findById(idPermohonan);
        Pengguna pguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        InfoAudit ia = new InfoAudit();
        kodCawangan = strService.getkodCawangan(pguna.getKodCawangan().getKod());

        AduanStrata aduan = strService.findAduanStrataIdMohon(idPermohonan);
        if (aduan != null) {
            LOG.debug("Maklumat aduan strata Found. id_aduan_strata: " + aduan.getIdAduanStrata());
            ia = aduan.getInfoAudit();
            ia.setDiKemaskiniOleh(pguna);
            ia.setTarikhKemaskini(new java.util.Date());
        } else {
            LOG.info("Maklumat gagal dijumpai. Create new AduanStrata()");
            aduan = new AduanStrata();
            ia.setDimasukOleh(pguna);
            ia.setTarikhMasuk(new java.util.Date());
        }
        LOG.debug("ULASAN = " + ulasan);
        aduan.setUlasan(ulasan);
        aduan.setInfoAudit(ia);
        aduan.setCawangan(kodCawangan);
        aduan.setPermohonan(permohonan);
        LOG.info("===IN SAVING ULASAN===");
        try {
            strService.simpanAduanStrata(aduan);
            addSimpleMessage("Ulasan telah berjaya disimpan.");
            LOG.info("::SIMPAN BERJAYA:: id_aduan_strata = " + aduan.getIdAduanStrata());
        } catch (Exception e) {
            LOG.error("::GAGAL DISIMPAN:: error = " + e);
            addSimpleError("Maklumat gagal disimpan. " + e);
        }

        return new RedirectResolution(LaporanSiasatKuatkuasaStrataActionBean.class, "showUlasan");

    }

    public Resolution isiSemulaUlasan(){
        LOG.info("isi semula ulasan start here");
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        AduanStrata aduan = strService.findAduanStrataIdMohon(idPermohonan);
        
        if(aduan != null){
            aduan.setUlasan("");
            strService.simpanAduanStrata(aduan);
        }
        
        LOG.info("isi semula ulasan end here");
        return new RedirectResolution(LaporanSiasatKuatkuasaStrataActionBean.class, "showUlasan");
    }
    public String getAlamat1() {
        return alamat1;
    }

    public void setAlamat1(String alamat1) {
        this.alamat1 = alamat1;
    }

    public String getAlamat2() {
        return alamat2;
    }

    public void setAlamat2(String alamat2) {
        this.alamat2 = alamat2;
    }

    public String getAlamat3() {
        return alamat3;
    }

    public void setAlamat3(String alamat3) {
        this.alamat3 = alamat3;
    }

    public String getAlamat4() {
        return alamat4;
    }

    public void setAlamat4(String alamat4) {
        this.alamat4 = alamat4;
    }

    public String getKawPBT() {
        return kawPBT;
    }

    public void setKawPBT(String kawPBT) {
        this.kawPBT = kawPBT;
    }

    public String getLokasi() {
        return lokasi;
    }

    public void setLokasi(String lokasi) {
        this.lokasi = lokasi;
    }

    public String getNegeri() {
        return negeri;
    }

    public void setNegeri(String negeri) {
        this.negeri = negeri;
    }

    public String getNoCF() {
        return noCF;
    }

    public void setNoCF(String noCF) {
        this.noCF = noCF;
    }

    public String getNoLot() {
        return noLot;
    }

    public void setNoLot(String noLot) {
        this.noLot = noLot;
    }

    public String getNoRujukanPelanBngn() {
        return noRujukanPelanBngn;
    }

    public void setNoRujukanPelanBngn(String noRujukanPelanBngn) {
        this.noRujukanPelanBngn = noRujukanPelanBngn;
    }

    public String getPoskod() {
        return poskod;
    }

    public void setPoskod(String poskod) {
        this.poskod = poskod;
    }

    public String getTarikhCF() {
        return tarikhCF;
    }

    public void setTarikhCF(String tarikhCF) {
        this.tarikhCF = tarikhCF;
    }

    public Hakmilik getHakmilik() {
        return hakmilik;
    }

    public void setHakmilik(Hakmilik hakmilik) {
        this.hakmilik = hakmilik;
    }

    public String getIdHakmilik() {
        return idHakmilik;
    }

    public void setIdHakmilik(String idHakmilik) {
        this.idHakmilik = idHakmilik;
    }

    public String getNamaPemaju() {
        return namaPemaju;
    }

    public void setNamaPemaju(String namaPemaju) {
        this.namaPemaju = namaPemaju;
    }

    public Integer getNoHakmilik() {
        return noHakmilik;
    }

    public void setNoHakmilik(Integer noHakmilik) {
        this.noHakmilik = noHakmilik;
    }

    public Integer getNoLotHakmilik() {
        return noLotHakmilik;
    }

    public void setNoLotHakmilik(Integer noLotHakmilik) {
        this.noLotHakmilik = noLotHakmilik;
    }

    public String getNamaPembeli() {
        return namaPembeli;
    }

    public void setNamaPembeli(String namaPembeli) {
        this.namaPembeli = namaPembeli;
    }

    public String getNamaPenjual() {
        return namaPenjual;
    }

    public void setNamaPenjual(String namaPenjual) {
        this.namaPenjual = namaPenjual;
    }

    public String getIdHakmilikTanah() {
        return idHakmilikTanah;
    }

    public void setIdHakmilikTanah(String idHakmilikTanah) {
        this.idHakmilikTanah = idHakmilikTanah;
    }

    public String getTarikhTandatangan() {
        return tarikhTandatangan;
    }

    public void setTarikhTandatangan(String tarikhTandatangan) {
        this.tarikhTandatangan = tarikhTandatangan;
    }

    public String getKodStatusHakmilik() {
        return kodStatusHakmilik;
    }

    public void setKodStatusHakmilik(String kodStatusHakmilik) {
        this.kodStatusHakmilik = kodStatusHakmilik;
    }

    public String getUlasan() {
        return ulasan;
    }

    public void setUlasan(String ulasan) {
        this.ulasan = ulasan;
    }

    public String getCatatan() {
        return catatan;
    }

    public void setCatatan(String catatan) {
        this.catatan = catatan;
    }

    public List<SiasatanAduanImej> getSenaraiImejSiasatan() {
        return senaraiImejSiasatan;
    }

    public void setSenaraiImejSiasatan(List<SiasatanAduanImej> senaraiImejSiasatan) {
        this.senaraiImejSiasatan = senaraiImejSiasatan;
    }

    public List<HakmilikPihakBerkepentingan> getListHakmilikPihak() {
        return listHakmilikPihak;
    }

    public void setListHakmilikPihak(List<HakmilikPihakBerkepentingan> listHakmilikPihak) {
        this.listHakmilikPihak = listHakmilikPihak;
    }

    public Pihak getPihak() {
        return pihak;
    }

    public void setPihak(Pihak pihak) {
        this.pihak = pihak;
    }

    public String getIdPihak() {
        return idPihak;
    }

    public void setIdPihak(String idPihak) {
        this.idPihak = idPihak;
    }

    public List<Hakmilik> getListHakmilikSambungan() {
        return listHakmilikSambungan;
    }

    public void setListHakmilikSambungan(List<Hakmilik> listHakmilikSambungan) {
        this.listHakmilikSambungan = listHakmilikSambungan;
    }

    public FileBean getFileToBeUpload() {
        return fileToBeUpload;
    }

    public void setFileToBeUpload(FileBean fileToBeUpload) {
        this.fileToBeUpload = fileToBeUpload;
    }

    public List<Permohonan> getListMohonPecahSempadan() {
        return listMohonPecahSempadan;
    }

    public void setListMohonPecahSempadan(List<Permohonan> listMohonPecahSempadan) {
        this.listMohonPecahSempadan = listMohonPecahSempadan;
    }

    public String getKenderaanSiasat() {
        return kenderaanSiasat;
    }

    public void setKenderaanSiasat(String kenderaanSiasat) {
        this.kenderaanSiasat = kenderaanSiasat;
    }

    public String getTarikhSiasat() {
        return tarikhSiasat;
    }

    public void setTarikhSiasat(String tarikhSiasat) {
        this.tarikhSiasat = tarikhSiasat;
    }

    public String getIdHakmilikMuktamad() {
        return idHakmilikMuktamad;
    }

    public void setIdHakmilikMuktamad(String idHakmilikMuktamad) {
        this.idHakmilikMuktamad = idHakmilikMuktamad;
    }

    public Hakmilik getHakmilikMuktamad() {
        return hakmilikMuktamad;
    }

    public void setHakmilikMuktamad(Hakmilik hakmilikMuktamad) {
        this.hakmilikMuktamad = hakmilikMuktamad;
    }

    public String getKodUrusan() {
        return kodUrusan;
    }

    public void setKodUrusan(String kodUrusan) {
        this.kodUrusan = kodUrusan;
    }

    /**
     * @return the hmPihak
     */
    public HakmilikPihakBerkepentingan getHmPihak() {
        return hmPihak;
    }

    /**
     * @param hmPihak the hmPihak to set
     */
    public void setHmPihak(HakmilikPihakBerkepentingan hmPihak) {
        this.hmPihak = hmPihak;
    }
}
