/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.stripes.pengambilan;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.Configuration;
import etanah.dao.HakmilikDAO;
import etanah.dao.HakmilikPermohonanDAO;
import etanah.dao.KandunganFolderDAO;
import etanah.dao.KodAgensiDAO;
import etanah.dao.KodDokumenDAO;
import etanah.dao.KodKategoriAgensiDAO;
import etanah.dao.KodRujukanDAO;
import etanah.dao.PermohonanDAO;
import etanah.dao.PermohonanRujukanLuarDAO;
import etanah.dao.PermohonanRujukanLuarDokumenDAO;
import etanah.dao.PermohonanRujukanLuarSalinanDAO;
import etanah.model.Dokumen;
import etanah.model.FolderDokumen;
import etanah.model.Hakmilik;
import etanah.model.HakmilikPermohonan;
import etanah.model.InfoAudit;
import etanah.model.KandunganFolder;
import etanah.model.KodAgensi;
import etanah.model.KodDUN;
import etanah.model.KodRujukan;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.model.PermohonanRujukanLuar;
import etanah.model.PermohonanRujukanLuarDokumen;
import etanah.model.PermohonanRujukanLuarSalinan;
import etanah.model.PermohonanTandatanganDokumen;
import etanah.service.PengambilanService;
import etanah.service.RegService;
import etanah.service.common.EnforcementService;
import etanah.service.common.PermohonanService;
import etanah.view.etanahActionBeanContext;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Vector;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.RedirectResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;
import org.apache.log4j.Logger;
import org.apache.commons.lang.StringUtils;

/**
 *
 * @author Murali
 */
@UrlBinding("/pengambilan/sedia_jabatan")
public class JabatanTeknikalPengambilanActionBean extends AbleActionBean {

    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    HakmilikPermohonanDAO hakmilikPermohonanDAO;
    @Inject
    HakmilikDAO hakmilikDAO;
    @Inject
    PengambilanService pengambilanService;
    @Inject
    KodDokumenDAO kodDokumenDAO;
    @Inject
    PermohonanRujukanLuarDAO permohonanRujukanLuarDAO;
    @Inject
    KodAgensiDAO kodAgensiDAO;
    @Inject
    KodRujukanDAO kodRujukanDAO;
    @Inject
    private etanah.Configuration conf;
    @Inject
    RegService regService;
    @Inject
    PermohonanService permohonanService;
    @Inject
    KodKategoriAgensiDAO kodKategoriAgensiDAO;
    @Inject
    EnforcementService enforcementService;
    @Inject
    PermohonanRujukanLuarSalinanDAO permohonanRujukanLuarSalinanDAO;
    @Inject
    PermohonanRujukanLuarDokumenDAO permohonanRujukanLuarDokumenDAO;
    @Inject
    KandunganFolderDAO kandunganFolderDAO;
    String idHakmilik;
    String idMh;
    private Permohonan permohonan;
    private Hakmilik hakmilik;
    private List<HakmilikPermohonan> hakmilikPermohonanList;
    private List<Pengguna> penggunaList;
    private static final Logger logger = Logger.getLogger(JabatanTeknikalPengambilanActionBean.class);
    private PermohonanTandatanganDokumen tandatanganDokumenTemp;
    private PermohonanTandatanganDokumen tandatanganDokumen;
    private String ditundatangan;
    private HakmilikPermohonan hakmilikPermohonan;
    private String index;
    private PermohonanRujukanLuar permohonanRujukanLuar;
    private static int day;
    private String kNegeri;
    private int sizeKod;
    private List<KodAgensi> listKodAgensi;
    private String kod;
    private String kodAgensiNama;
    private KodAgensi kodAgensi;
    private Vector namaKawasan = new Vector();
    private List<KodAgensi> listKodAgensiAdun;
    private List<KodDUN> listAdun = new ArrayList<KodDUN>();
    private List<KandunganFolder> listKandunganFolder;
    private List<PermohonanRujukanLuarDokumen> prsd;
    private String idMohonRujLuar;
    private String idPermohonan;
    private List<FolderDokumen> listFolderDokumen;
    private FolderDokumen folderDokumen;
    private String kodKatgAgensi;
    private String kodRujukan;
    private String idRujukan;
    private PermohonanRujukanLuar pprl;
    private List<PermohonanRujukanLuarSalinan> prs;
    private Date tarikhHantar;
    private List<PermohonanRujukanLuar> senaraiRujukanLuar;
    private String recordCount;
    private List<PermohonanRujukanLuarDokumen> senaraiRujukanDok;
    private PermohonanRujukanLuar mohonRujLuar;
    private String idSalinan;
    private PermohonanRujukanLuarSalinan mohonRujukLuarSalinan;
    private String idDokumen;
    private PermohonanRujukanLuarDokumen pdok;
    private String mandatori;
    private String catatan;
    private Date tarikhJangkaTerima;
    private int jangkaMasa_hari;
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    private String idHak;

    @DefaultHandler
    public Resolution showForm() {
        return new JSP("pengambilan/negerisembilan/haklalulalangpersendirian/jabatan_teknikal_Pengambilan.jsp").addParameter("tab", "true");
    }

    public Resolution refreshPage() {
        rehydrate();
        return showForm();
    }

    public Resolution edit() {
        if (kod == null) {
            kod = getContext().getRequest().getParameter("kod");
        }
        logger.info(kod);
        idRujukan = getContext().getRequest().getParameter("idRujukan");
        logger.info("Id Rujukan : " + idRujukan);

        Long test = Long.parseLong(idRujukan);
        pprl = permohonanRujukanLuarDAO.findById(test);
        prs = pengambilanService.findByIdMohon3(pprl.getIdRujukan());
        prsd = pengambilanService.findByIdMohonForMRLDok(pprl.getIdRujukan());
        if (pprl != null) {
            setTarikhHantar(pprl.getTarikhDisampai());
            setTarikhJangkaTerima(pprl.getTarikhJangkaTerima());
            setJangkaMasa_hari(pprl.getTempohHari());
            setCatatan(pprl.getCatatan());
            setMandatori(pprl.getUlasanMandatori());
        }
        return new JSP("pengambilan/negerisembilan/haklalulalangpersendirian/edit_jabatan_teknikal.jsp").addParameter("popup", "true");
    }

    public Resolution simpanKodSalinan() {
        logger.info("----------simpanKodSalinan-----------::");

        logger.info("---------Saving in Permohonan RujukanLuar Salinan-----------::");
        String kodMohonRujLuar = getContext().getRequest().getParameter("radio");
        kodRujukan = getContext().getRequest().getParameter("kod");
        logger.info("-------------kod----------- :" + kodMohonRujLuar);
        logger.info("-------------kodRujukan---------: " + kodRujukan);
        idRujukan = getContext().getRequest().getParameter("idRujukan");
        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        InfoAudit info = new InfoAudit();
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        logger.info("----------idPermohonan-----------::" + idPermohonan);
        if (idPermohonan != null) {
            logger.info("----------idPermohonan not null-----------::");
            info.setDimasukOleh(peng);
            info.setTarikhMasuk(new java.util.Date());

            if (StringUtils.isNotBlank(kodMohonRujLuar)) {
                PermohonanRujukanLuarSalinan prs = pengambilanService.findSalinanByIdRujukanAndKodAgensi(idRujukan, kodMohonRujLuar);
                logger.info("----------Permohonan RujukanLuar Salinan-----------::" + prs);

                if (prs != null) {
                    logger.info("----------Permohonan RujukanLuar Salinan not null-----------::");
                    prs.setCawangan(permohonan.getCawangan());
                    prs.setInfoAudit(info);
                    KodAgensi ks = kodAgensiDAO.findById(kodMohonRujLuar);
                    Long a = Long.parseLong(idRujukan);
                    prs.setPermohonanRujukanLuar(permohonanRujukanLuarDAO.findById(a));
                    pengambilanService.saveOrUpdate(prs);

                } else {
                    logger.info("----------Permohonan RujukanLuar Salinan is null-----------::");
                    PermohonanRujukanLuarSalinan prs2 = new PermohonanRujukanLuarSalinan();
                    prs2.setCawangan(permohonan.getCawangan());
                    prs2.setInfoAudit(info);
                    KodAgensi ks = kodAgensiDAO.findById(kodMohonRujLuar);
                    prs2.setKodAgensi(ks);
                    Long a = Long.parseLong(idRujukan);
                    prs2.setPermohonanRujukanLuar(permohonanRujukanLuarDAO.findById(a));
                    pengambilanService.saveOrUpdate(prs2);
                }
            }
        }
        addSimpleMessage("Maklumat telah berjaya disimpan");
        kod = getContext().getRequest().getParameter("kodAsal");
        return new JSP("pengambilan/negerisembilan/haklalulalangpersendirian/edit_jabatan_teknikal.jsp").addParameter("popup", "true");
    }

    public Resolution editTest() {
        kod = getContext().getRequest().getParameter("kod");
        logger.info("idPermohonan :" + idPermohonan + " KodAgensi:" + kod);
        pprl = pengambilanService.getSenaraiRujLuarByIDPermohonanAgensi(idPermohonan, kod);
        if (pprl != null) {
//            setTarikhHantar(pprl.getTarikhDisampai());
//            setTarikhJangkaTerima(pprl.getTarikhJangkaTerima());
//            setCatatan(pprl.getCatatan());
        }
        return new JSP("pengambilan/negerisembilan/haklalulalangpersendirian/edit_jabatan_teknikal.jsp").addParameter("tab", "true");
    }

    public Resolution searchData1() {
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = permohonanDAO.findById(idPermohonan);
        for (int i = 0; i < senaraiRujukanLuar.size(); i++) {
        }

        return new JSP("pengambilan/negerisembilan/haklalulalangpersendirian/pilih_agensi.jsp").addParameter("popup", "true");
    }
    // click on edit button

    public Resolution showEditJabatanTeknikal() {
        String idRujukan;
        idRujukan = getContext().getRequest().getParameter("idRujukan");
        mohonRujLuar = new PermohonanRujukanLuar();
        if (idRujukan != null) {
            mohonRujLuar = permohonanRujukanLuarDAO.findById(Long.parseLong(idRujukan));
        }
        return new JSP("pengambilan/negerisembilan/haklalulalangpersendirian/edit_jabatan_teknikal.jsp").addParameter("popup", "true");
    }

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {
        logger.info("--------------rehydrate--------------::");

        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        idHakmilik = (String) getContext().getRequest().getParameter("idHakmilik");
        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        permohonan = permohonanDAO.findById(idPermohonan);
        kNegeri = conf.getProperty("kodNegeri");
        Permohonan p = permohonanDAO.findById(idPermohonan);
        logger.info("--------------Permohonan--------------::" + p);

        if (p != null) {
            if (p.getKodUrusan().getKod().equals("LMCRG")) {
                penggunaList = pengambilanService.getSenaraiPenggunaPTG(p.getCawangan());
                logger.info("--------------penggunaList if = LMCRG--------------::" + penggunaList);
            } else {
                penggunaList = pengambilanService.getSenaraiPengguna(p.getCawangan());
                logger.info("--------------penggunaList if Not LMCRG--------------::" + penggunaList);
            }
        }
        hakmilikPermohonanList = p.getSenaraiHakmilik();
        logger.info("--------------hakmilikPermohonanList--------------::" + hakmilikPermohonanList);
    }

    public Resolution simpanTandatangan() {
        logger.info("--------------simpan Tandatangan--------------::");

        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        idHakmilik = (String) getContext().getRequest().getParameter("idHakmilik");
        logger.info("--------------idHakmilik--------------::" + idHakmilik);
        idMh = (String) getContext().getRequest().getParameter("idMh");
        logger.info("--------------idMh--------------::" + idMh);

        hakmilikPermohonan = pengambilanService.findByIdHakmilikIdPermohonan(idPermohonan, idHakmilik);
        logger.info("--------------hakmilikPermohonan--------------::" + hakmilikPermohonan);
        InfoAudit ia = new InfoAudit();
        PermohonanTandatanganDokumen tandatanganDokumen1 = pengambilanService.findPermohonanDokTTByIdPermohonan(idPermohonan, "SUA");
//        PermohonanTandatanganDokumen tandatanganDokumen2 = pengambilanService.findPermohonanDokTTByIdPermohonan(idPermohonan, "SUT");

        ditundatangan = getContext().getRequest().getParameter("ditundatangan");
        logger.info("------ditundatangan------::" + ditundatangan);
        if (ditundatangan != null) {
            logger.info("------ditundatangan not null------::");
            logger.info("------Saving in Mohon_Dok_TT for Kod_dok = SUA------::");
            if (tandatanganDokumen1 != null) {
                ia = tandatanganDokumen1.getInfoAudit();
                ia.setDiKemaskiniOleh(peng);
                ia.setTarikhKemaskini(new java.util.Date());
            } else {
                tandatanganDokumen1 = new PermohonanTandatanganDokumen();
                tandatanganDokumen1.setCawangan(permohonan.getCawangan());
                tandatanganDokumen1.setPermohonan(permohonan);
                tandatanganDokumen1.setKodDokumen(kodDokumenDAO.findById("SUA"));
                ia.setDimasukOleh(peng);
                ia.setTarikhMasuk(new java.util.Date());
            }
            tandatanganDokumen1.setInfoAudit(ia);
            tandatanganDokumen1.setDiTandatangan(ditundatangan);
            tandatanganDokumen1.setHakmilikPermohonan(hakmilikPermohonan);
            pengambilanService.simpanPermohonanDokTT(tandatanganDokumen1);

//            logger.info("------Saving in Mohon_Dok_TT for Kod_dok = SUT------::");
//            if (tandatanganDokumen2 != null) {
//                ia = tandatanganDokumen2.getInfoAudit();
//                ia.setDiKemaskiniOleh(peng);
//                ia.setTarikhKemaskini(new java.util.Date());
//            } else {
//                tandatanganDokumen2 = new PermohonanTandatanganDokumen();
//                tandatanganDokumen2.setCawangan(permohonan.getCawangan());
//                tandatanganDokumen2.setPermohonan(permohonan);
//                tandatanganDokumen2.setKodDokumen(kodDokumenDAO.findById("SUT"));
//                ia.setDimasukOleh(peng);
//                ia.setTarikhMasuk(new java.util.Date());
//            }
//            tandatanganDokumen2.setInfoAudit(ia);
//            tandatanganDokumen2.setDiTandatangan(ditundatangan);
//            tandatanganDokumen1.setHakmilikPermohonan(hakmilikPermohonan);
//            pengambilanService.simpanPermohonanDokTT(tandatanganDokumen2);

            logger.info("------Saving in Mohon_Dok------::");
        }

        addSimpleMessage("Rekod tandatangan telah dimasukkan");
        return new RedirectResolution(JabatanTeknikalPengambilanActionBean.class, "showForm");
    }

    public Resolution rujuluarDetails() {
        logger.info("------rujuluarDetails------::");

        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");

        idHakmilik = (String) getContext().getRequest().getParameter("idHakmilik");
        idMh = (String) getContext().getRequest().getParameter("idMh");
        logger.info("------idHakmilik------::" + idHakmilik);
        logger.info("------idMh------::" + idMh);

        PermohonanTandatanganDokumen tandatanganDokumen1 = pengambilanService.findPermohonanDokTTByIdMohonIdMh(idPermohonan, idMh);
        logger.info("----------------tandatanganDokumen1-------------------" + tandatanganDokumen1);
        if(tandatanganDokumen1 != null){
        ditundatangan = tandatanganDokumen1.getDiTandatangan();
        logger.info("------ditundatangan------::" + ditundatangan);
        }else{
            
        }

        if (idPermohonan != null) {
            logger.info("---------idPermohonan--------- : " + idPermohonan);
            senaraiRujukanLuar = pengambilanService.getSenaraiRujLuarByIDPermohonanHakmilikJab(idPermohonan, idHakmilik);
            logger.info("---------senaraiRujukanLuar--------- : " + senaraiRujukanLuar);
            if (senaraiRujukanLuar != null) {
                logger.info("---------senaraiRujukanLuar Not null--------- : " + senaraiRujukanLuar);
                recordCount = String.valueOf(senaraiRujukanLuar.size());
                for (PermohonanRujukanLuar prl : senaraiRujukanLuar) {
                    senaraiRujukanDok = pengambilanService.findListDokumenRujukan(String.valueOf(prl.getIdRujukan()));
                    logger.info("---------senaraiRujukanDok--------- : " + senaraiRujukanDok);
                }
            }
        }
        return new JSP("pengambilan/negerisembilan/haklalulalangpersendirian/jabatan_teknikal_Pengambilan.jsp").addParameter("tab", "true");
    }

//    public Resolution rujuluarDetails() {
//        logger.info("------rujuluarDetails------::");
//
//        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
//
//        idHakmilik = (String) getContext().getRequest().getParameter("idHakmilik");
//        idMh = (String) getContext().getRequest().getParameter("idMh");
//        logger.info("------idHakmilik------::" + idHakmilik);
//        logger.info("------idMh------::" + idMh);
//
//        List<PermohonanTandatanganDokumen> ttDokList = pengambilanService.findPermohonanDokTTByIdPermohonanrehy(idPermohonan);
//        logger.info("----------------ttDokList-------------------" + ttDokList.size());
//        if (ttDokList.size() > 0) {
//            tandatanganDokumen = ttDokList.get(0);
//            ditundatangan = tandatanganDokumen.getDiTandatangan();
//            logger.info("----------------ditundatangan-------------------" + ditundatangan);
//        }
//        logger.info("----------------tandatanganDokumen-------------------" + tandatanganDokumen);
//
//        if (idPermohonan != null) {
//            logger.info("---------idPermohonan--------- : " + idPermohonan);
//            senaraiRujukanLuar = pengambilanService.getSenaraiRujLuarByIDPermohonanAgensi(idPermohonan);
//            logger.info("---------senaraiRujukanLuar--------- : " + senaraiRujukanLuar);
//            if (senaraiRujukanLuar != null) {
//                logger.info("---------senaraiRujukanLuar Not null--------- : " + senaraiRujukanLuar);
//                recordCount = String.valueOf(senaraiRujukanLuar.size());
//                for (PermohonanRujukanLuar prl : senaraiRujukanLuar) {
//                    senaraiRujukanDok = pengambilanService.findListDokumenRujukan(String.valueOf(prl.getIdRujukan()));
//                    logger.info("---------senaraiRujukanDok--------- : " + senaraiRujukanDok);
//                }
//            }
//        }
//        return new JSP("pengambilan/negerisembilan/haklalulalangpersendirian/jabatan_teknikal_Pengambilan.jsp").addParameter("tab", "true");
//    }
    // Click on Cari button in Home Page to display SearchPage
    public Resolution kodAgensiPopup() {
        index = getContext().getRequest().getParameter("index");
        idHak = getContext().getRequest().getParameter("idHakmilik");
        logger.info("------------kodAgensiPopup----idHakmilik-------------" + idHak);
        getContext().getRequest().setAttribute("agensi", Boolean.TRUE);
        return new JSP("pengambilan/negerisembilan/haklalulalangpersendirian/pilih_agensi.jsp").addParameter("popup", "true");
    }

    public Resolution kodAgensiPopup2() {
        index = getContext().getRequest().getParameter("index");
        getContext().getRequest().setAttribute("salinan", Boolean.TRUE);
        return new JSP("pengambilan/negerisembilan/haklalulalangpersendirian/pilih_salinan_agensi.jsp").addParameter("popup", "true");
    }
    /*
     * TO POP UP MOHONRUJLUARDOK
     */

    public Resolution kodAgensiPopup3() {
        index = getContext().getRequest().getParameter("index");
        getContext().getRequest().setAttribute("salinan", Boolean.TRUE);
        getContext().getRequest().setAttribute("kodMohon_ruj_luar", index);
        idMohonRujLuar = index;
        return new JSP("pengambilan/negerisembilan/haklalulalangpersendirian/pilih_dokumen_serta.jsp").addParameter("popup", "true");
    }

    public Resolution simpanAgensi() {
        logger.info("--------Simpan in Agency-------::");

        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        String item = getContext().getRequest().getParameter("item");
        logger.info("--------item-------::" + item);
        String[] listAgensi = item.split(",");
        logger.info("--------Size-------::" + listAgensi.length);
        idHak = (String) getContext().getRequest().getParameter("idHakmilik");
        logger.info("--------idHak-------::" + idHak);
        hakmilik = hakmilikDAO.findById(idHak);
        logger.info("--------hakmilik-------::" + hakmilik);
        InfoAudit ia = new InfoAudit();
        ia.setDimasukOleh(peng);
        ia.setTarikhMasuk(new java.util.Date());
        for (int i = 0; i < listAgensi.length; i++) {
        logger.info("--------masuk loop-------::");
        
            if (!listAgensi[i].equals("T")) {
                logger.info("--------!listAgensi[i].equals(T)-------::");
                PermohonanRujukanLuar mohonRujLuar = new PermohonanRujukanLuar();
                mohonRujLuar.setInfoAudit(ia);
                mohonRujLuar.setCawangan(permohonan.getCawangan());
                mohonRujLuar.setPermohonan(permohonan);
                logger.info("---------listAgensi---------::" + listAgensi[i]);
                KodAgensi agen = kodAgensiDAO.findById(listAgensi[i]);
                KodRujukan kodRujukan = kodRujukanDAO.findById("FL");
                mohonRujLuar.setAgensi(agen);
                mohonRujLuar.setNamaAgensi(agen.getNama());
                mohonRujLuar.setKodRujukan(kodRujukan);
                mohonRujLuar.setTempohHari(14);
                mohonRujLuar.setTarikhRujukan(new java.util.Date());
                mohonRujLuar.setTarikhDisampai(new java.util.Date());
                mohonRujLuar.setTarikhJangkaTerima(getDateAfterDays(14));
                mohonRujLuar.setUlasanMandatori("T");
                mohonRujLuar.setHakmilik(hakmilik);
                pengambilanService.saveOrUpdateMRL(mohonRujLuar);
            }
            else{
                 logger.info("--------listAgensi[i].equals(T)-------::");}
        }
        return new ForwardResolution("/WEB-INF/jsp/pengambilan/negerisembilan/haklalulalangpersendirian/jabatan_teknikal_Pengambilan.jsp").addParameter("tab", "true");
    }

    public Resolution simpanMohonRujLuarDokumen() {
        logger.info("-----------simpanMohonRujLuarDokumen----------::");

        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        String item = getContext().getRequest().getParameter("item");
        logger.info("-------item----- :" + item);
        String idMohRujLuar = getContext().getRequest().getParameter("idMohRujLuar");
        logger.info("-------idMohRujLuar----- :" + idMohRujLuar);
        String[] listDok = item.split(",");
        logger.info("-------Size----- :" + listDok.length);
        InfoAudit ia = new InfoAudit();
        ia.setDimasukOleh(peng);
        ia.setTarikhMasuk(new java.util.Date());
        for (int i = 0; i < listDok.length; i++) {

            if (!listDok[i].equals("T")) {
                /*
                 * CHECKING dokumen at Folder_Dok table
                 */
                KandunganFolder fd = new KandunganFolder();
                if (!StringUtils.isBlank(listDok[i])) {
                    fd = kandunganFolderDAO.findById(Long.valueOf(listDok[i]));
                }
                if (fd != null) {
                    PermohonanRujukanLuarDokumen mohonRujLuarDok = new PermohonanRujukanLuarDokumen();
                    mohonRujLuarDok.setInfoAudit(ia);
                    mohonRujLuarDok.setCawangan(permohonan.getCawangan());
                    logger.info("------THIS IS CAWANGAN-------- =" + permohonan.getCawangan());
                    mohonRujLuarDok.setPermohonanRujukanLuar(permohonanRujukanLuarDAO.findById(Long.valueOf(idMohRujLuar)));
                    logger.info("-------THIS IS MOHON RUJUKAN LUAR------- =" + permohonanRujukanLuarDAO.findById(Long.valueOf(idMohRujLuar)));
                    mohonRujLuarDok.setDokumen(fd.getDokumen());
                    logger.info("------THIS IS DOKUMEN -------=" + fd.getDokumen().getKodDokumen().getKod());
                    pengambilanService.simpanPermohonanRujLuarDokumen(mohonRujLuarDok);
                }
                /*
                 * END
                 */
            }
        }
        addSimpleMessage("Maklumat telah berjaya disimpan");
        kod = getContext().getRequest().getParameter("kodAsal");
        return new JSP("pengambilan/negerisembilan/haklalulalangpersendirian/jabatan_teknikal_Pengambilan.jsp").addParameter("popup", "true");
    }

    public Resolution simpanDokumen() {

        logger.info("-----------simpanDokumen----------::");

        String[] param = getContext().getRequest().getParameterValues("idDokumen");
        idRujukan = getContext().getRequest().getParameter("idRujukan");
        for (String idDokumen : param) {

            logger.info("-----idDokumen---::" + idDokumen);
            InfoAudit ia = new InfoAudit();
            idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
            Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
            permohonanRujukanLuar = pengambilanService.findByIdRujukan(Long.valueOf(idRujukan));
            PermohonanRujukanLuarDokumen pdok = pengambilanService.findDokumen(idDokumen, idRujukan);
            logger.info("-----PermohonanRujukanLuarDokumen---::" + pdok);
            if (pdok == null) {
                logger.info("-----PermohonanRujukanLuarDokumen is null---::");
                PermohonanRujukanLuarDokumen pe = new PermohonanRujukanLuarDokumen();
                ia.setDimasukOleh(peng);
                ia.setTarikhMasuk(new java.util.Date());
                pe.setInfoAudit(ia);
                pe.setCawangan(peng.getKodCawangan());
                Dokumen kd = new Dokumen();
                kd.setIdDokumen(Long.valueOf(idDokumen));
                pe.setDokumen(kd);
                logger.info("----------getdokumen--------::" + kd.getIdDokumen());
                pe.setHaluan("H");
                pe.setPermohonanRujukanLuar(permohonanRujukanLuar);
                pengambilanService.simpanDokumen(pe);
            } else {
                addSimpleError("Dokumen  ini telah diHantar");
            }
        }
        addSimpleMessage("Maklumat telah berjaya disimpan.");

        return new RedirectResolution(JabatanTeknikalPengambilanActionBean.class, "addDokumen");
    }

    public Resolution simpanAdun() {
        logger.info("--------Simpan in Aduan-------::");

        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        String item = getContext().getRequest().getParameter("item");
        String[] listAgensi = item.split(",");
        logger.info("---------Size---------::" + listAgensi.length);
        InfoAudit ia = new InfoAudit();
        ia.setDimasukOleh(peng);
        ia.setTarikhMasuk(new java.util.Date());
        for (int i = 0; i < listAgensi.length; i++) {

            if (!listAgensi[i].equals("T")) {
                PermohonanRujukanLuar mohonRujLuar = new PermohonanRujukanLuar();
                mohonRujLuar.setInfoAudit(ia);
                mohonRujLuar.setCawangan(permohonan.getCawangan());
                mohonRujLuar.setPermohonan(permohonan);
                logger.info("---------listAgensi-------::" + listAgensi[i]);
                KodAgensi agen = kodAgensiDAO.findById(listAgensi[i]);
                KodRujukan kodRujukan = kodRujukanDAO.findById("FL");
                mohonRujLuar.setAgensi(agen);
                mohonRujLuar.setNamaAgensi(agen.getNama());
                mohonRujLuar.setKodRujukan(kodRujukan);
                mohonRujLuar.setTempohHari(14);
                mohonRujLuar.setTarikhRujukan(new java.util.Date());
                mohonRujLuar.setTarikhDisampai(new java.util.Date());
                mohonRujLuar.setTarikhJangkaTerima(getDateAfterDays(14));
                mohonRujLuar.setUlasanMandatori("T");
                pengambilanService.saveOrUpdateMRL(mohonRujLuar);
            }
        }
        return new ForwardResolution("/WEB-INF/jsp/pengambilan/negerisembilan/haklalulalangpersendirian/jabatan_teknikal_Pengambilan.jsp").addParameter("tab", "true");
    }

    // Click on Cari button in SearchPage to search based on criteria
    public Resolution cariKodAgensi() {
        listKodAgensi = new ArrayList<KodAgensi>();

        if (kod != null) {
            listKodAgensi = pengambilanService.searchKodAgensiLupus(kod, kodAgensiNama, conf.getProperty("kodNegeri"));
            if (listKodAgensi.size() < 1) {
                kodAgensi = new KodAgensi();
                kodAgensi.setKod(kod);
                getContext().getRequest().setAttribute("agensi", Boolean.TRUE);
                return new JSP("pengambilan/negerisembilan/haklalulalangpersendirian/tambah_agensi.jsp").addParameter("popup", "true");
            } else {
                getContext().getRequest().setAttribute("agensi", Boolean.TRUE);
                return new JSP("pengambilan/negerisembilan/haklalulalangpersendirian/pilih_agensi.jsp").addParameter("popup", "true");
            }
        } else {
            listKodAgensi = regService.searchKodAgensiLupus("%", kodAgensiNama, conf.getProperty("kodNegeri"));
            System.out.println("listKodSyaratNyata.size : " + listKodAgensi.size());
            sizeKod = listKodAgensi.size();
            logger.debug("---------size agensi list---------:" + sizeKod);
            getContext().getRequest().setAttribute("agensi", Boolean.TRUE);
            return new JSP("pengambilan/negerisembilan/haklalulalangpersendirian/pilih_agensi.jsp").addParameter("popup", "true");
        }
    }

    public Resolution cariKodAgensiADN() {
        listKodAgensiAdun = new ArrayList<KodAgensi>();

        if (kod != null) {
            listKodAgensiAdun = pengambilanService.searchKodAgensiADN(kod, kodAgensiNama, conf.getProperty("kodNegeri"));
            if (listKodAgensiAdun.size() < 1) {
                kodAgensi = new KodAgensi();
                kodAgensi.setKod(kod);
                getContext().getRequest().setAttribute("agensi", Boolean.TRUE);
                return new JSP("pengambilan/negerisembilan/haklalulalangpersendirian/tambah_agensi.jsp").addParameter("popup", "true");
            } else {
                getContext().getRequest().setAttribute("agensi", Boolean.TRUE);
                for (KodAgensi kod : listKodAgensiAdun) {
                    KodDUN kod1 = pengambilanService.findKodDUNByAgensi(kod.getKod());
                    listAdun.add(kod1);
                    namaKawasan.add(kod1.getNama());
                }
                kodAgensiNama = null;
                return new JSP("pengambilan/negerisembilan/haklalulalangpersendirian/pilih_agensi.jsp").addParameter("popup", "true");
            }
        } else {
            listKodAgensiAdun = pengambilanService.searchKodAgensiADN("%", kodAgensiNama, conf.getProperty("kodNegeri"));
            logger.debug("---------listKodSyaratNyata.size-------- : " + listKodAgensiAdun.size());
            sizeKod = listKodAgensiAdun.size();
            logger.debug("-----------size agensi list----------: " + sizeKod);

            getContext().getRequest().setAttribute("agensi", Boolean.TRUE);
            for (KodAgensi kod : listKodAgensiAdun) {
                KodDUN kod1 = pengambilanService.findKodDUNByAgensi(kod.getKod());
                listAdun.add(kod1);
                namaKawasan.add(kod1.getNama());
            }
            kodAgensiNama = null;
            return new JSP("pengambilan/negerisembilan/haklalulalangpersendirian/pilih_agensi.jsp").addParameter("popup", "true");
        }
    }

    public Resolution cariKodAgensi2() {
        listKodAgensi = new ArrayList<KodAgensi>();

        if (kod != null) {
            listKodAgensi = regService.searchKodAgensiLupus(kod, kodAgensiNama, conf.getProperty("kodNegeri")); //modify by m.fairulfaisal........................... filter by kod_katg_agensi
            if (listKodAgensi.size() < 1) {
                kodAgensi = new KodAgensi();
                kodAgensi.setKod(kod);
                return new JSP("pengambilan/negerisembilan/haklalulalangpersendirian/tambah_agensi.jsp").addParameter("popup", "true");
            }
        } else {
            listKodAgensi = regService.searchKodAgensiLupus("%", kodAgensiNama, conf.getProperty("kodNegeri"));
            System.out.println("listKodSyaratNyata.size : " + listKodAgensi.size());
            if (listKodAgensi.size() < 1) {
                kodAgensi = new KodAgensi();
                kodAgensi.setKod(kod);
                return new JSP("pengambilan/negerisembilan/haklalulalangpersendirian/tambah_agensi.jsp").addParameter("popup", "true");
            }
        }
        getContext().getRequest().setAttribute("salinan", Boolean.TRUE);
        return new JSP("pengambilan/negerisembilan/haklalulalangpersendirian/pilih_salinan_agensi.jsp").addParameter("popup", "true");
    }
    /*
     * CARIAN POPUP DOKUMEN
     */

    public Resolution cariKodAgensi3() {
        listKandunganFolder = new ArrayList<KandunganFolder>();
        List<PermohonanRujukanLuarDokumen> listmohonRujLuarDokTemp = new ArrayList<PermohonanRujukanLuarDokumen>();
        listmohonRujLuarDokTemp = !StringUtils.isBlank(idMohonRujLuar) ? pengambilanService.findByIdMohonForMRLDok(Long.valueOf(idMohonRujLuar)) : new ArrayList<PermohonanRujukanLuarDokumen>();
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = permohonanService.findById(idPermohonan);
        folderDokumen = permohonan.getFolderDokumen();
        List<KandunganFolder> listKandunganFolderTemp = folderDokumen.getSenaraiKandungan();

        if (kod != null) {

            if (idPermohonan != null) {
                if (folderDokumen == null) {
                    addSimpleError("Tiada folder di dalam permohonan ini");
                } else {
                    if (listKandunganFolderTemp.size() > 0) {
                        if (!listmohonRujLuarDokTemp.isEmpty()) {
                            for (KandunganFolder kf : listKandunganFolderTemp) {
                                boolean checkExist = false;
                                for (PermohonanRujukanLuarDokumen prld : listmohonRujLuarDokTemp) {
                                    logger.info(prld.getDokumen().getKodDokumen().getKod());
                                    logger.info(kf.getDokumen().getKodDokumen().getKod());
                                    if (prld.getDokumen().getKodDokumen().getKod().equalsIgnoreCase(kf.getDokumen().getKodDokumen().getKod())) {
                                        checkExist = true;
                                        break;
                                    }
                                }
                                logger.info(checkExist);
                                if (!checkExist) {
                                    listKandunganFolder.add(kf);
                                }
                            }
                        } else {
                            addSimpleMessage("Tiada Maklumat");
                        }
                    } else {
                        addSimpleMessage("Tiada Maklumat");
                    }
//
                }
            }
        } else {

            if (idPermohonan != null) {
                if (folderDokumen == null) {
                    addSimpleError("Tiada folder di dalam permohonan ini");
                } else {

                    if (listKandunganFolderTemp.size() > 0) {
                        if (!listmohonRujLuarDokTemp.isEmpty()) {
                            for (KandunganFolder kf : listKandunganFolderTemp) {
                                boolean checkExist = false;
                                for (PermohonanRujukanLuarDokumen prld : listmohonRujLuarDokTemp) {
                                    logger.info(prld.getDokumen().getKodDokumen().getKod());
                                    logger.info(kf.getDokumen().getKodDokumen().getKod());
                                    if (prld.getDokumen().getKodDokumen().getKod().equalsIgnoreCase(kf.getDokumen().getKodDokumen().getKod())) {
                                        checkExist = true;
                                        break;
                                    }
                                }
                                logger.info(checkExist);
                                if (!checkExist) {
                                    listKandunganFolder.add(kf);
                                }
                            }
                        } else {
                            listKandunganFolder = folderDokumen.getSenaraiKandungan();
                        }
                    } else {
                        addSimpleMessage("Tiada Maklumat");
                    }
                }
            }
        }
        sizeKod = listKandunganFolder.size();
        getContext().getRequest().setAttribute("salinan", Boolean.TRUE);
        return new JSP("pengambilan/negerisembilan/haklalulalangpersendirian/pilih_dokumen_serta.jsp").addParameter("popup", "true");
    }

    // Click on Cari button in SearchPage to search based on criteria
    public Resolution cariKodAgensiJTK() {
        listKodAgensi = new ArrayList<KodAgensi>();
        List<KodAgensi> listKodAgensiTemp = new ArrayList<KodAgensi>();
        List<PermohonanRujukanLuar> listMohonRujLuar = new ArrayList<PermohonanRujukanLuar>();
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        if (!StringUtils.isBlank(idPermohonan)) {
            listMohonRujLuar = pengambilanService.findPermohonanRujLuarByIdPermohonanNADUN(idPermohonan, "JTK");
        }

        if (kod != null) {
            listKodAgensiTemp = pengambilanService.searchKodAgensiJTK(kod, kodAgensiNama, conf.getProperty("kodNegeri"));
            if (listMohonRujLuar.size() > 0) {
                if (listKodAgensiTemp.size() > 0) {
                    for (KodAgensi ka : listKodAgensiTemp) {
                        boolean checkExist = true;
//                        for (PermohonanRujukanLuar prl : listMohonRujLuar) {
//                            if (ka.getKod().equalsIgnoreCase(prl.getAgensi().getKod())) {
//                                checkExist = false;
//                                break;
//                            }
//                        }
                        if (checkExist) {
                            listKodAgensi.add(ka);
                        }
                    }
                }
            } else {
                if (listKodAgensiTemp.size() < 1) {
                    kodAgensi = new KodAgensi();
                    kodAgensi.setKod(kod);
                    for (KodAgensi ka : listKodAgensiTemp) {
                        listKodAgensi.add(ka);
                    }
                    getContext().getRequest().setAttribute("agensi", Boolean.TRUE);
                    return new JSP("pengambilan/negerisembilan/haklalulalangpersendirian/tambah_agensi.jsp").addParameter("popup", "true");
                } else {
                    for (KodAgensi ka : listKodAgensiTemp) {
                        listKodAgensi.add(ka);
                    }
                    getContext().getRequest().setAttribute("agensi", Boolean.TRUE);
                    kodAgensiNama = null;
                    return new JSP("pengambilan/negerisembilan/haklalulalangpersendirian/pilih_agensi.jsp").addParameter("popup", "true");
                }
            }

        } else {
            listKodAgensiTemp = pengambilanService.searchKodAgensiJTK("%", kodAgensiNama, conf.getProperty("kodNegeri"));
            if (listMohonRujLuar.size() > 0) {
                if (listKodAgensiTemp.size() > 0) {
                    for (KodAgensi ka : listKodAgensiTemp) {
                        boolean checkExist = true;
//                        for (PermohonanRujukanLuar prl : listMohonRujLuar) {
//                            if (ka.getKod().equalsIgnoreCase(prl.getAgensi().getKod())) {
//                                checkExist = false;
//                                break;
//                            }
//                        }
                        if (checkExist) {
                            listKodAgensi.add(ka);
                        }
                    }
                }
            } else {
                for (KodAgensi ka : listKodAgensiTemp) {
                    listKodAgensi.add(ka);
                }
            }

            System.out.println("--------listKodSyaratNyata.size ---------: " + listKodAgensiTemp.size());

            sizeKod = listKodAgensi.size();
            logger.debug("size agensi list: " + sizeKod);

            getContext().getRequest().setAttribute("agensi", Boolean.TRUE);
            kodAgensiNama = null;
            return new JSP("pengambilan/negerisembilan/haklalulalangpersendirian/pilih_agensi.jsp").addParameter("popup", "true");
        }
        return null;
    }

    // Save KodAgensi details
    public Resolution simpanKodAgensi() {
        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        InfoAudit info = new InfoAudit();

        if (idPermohonan != null) {
            info.setDimasukOleh(peng);
            info.setTarikhMasuk(new java.util.Date());
            kodAgensi.setInfoAudit(info);
//            kodAgensi.setKodKementerian(1); //TODO: list kod_kementerian
            kodAgensi.setKategoriAgensi(kodKategoriAgensiDAO.findById(kodKatgAgensi));
            kodAgensi.setAktif('Y');
            logger.info("---------kodAgensi--------" + kodAgensi);
            regService.simpanKodAgensi(kodAgensi);

        }
        addSimpleMessage("Maklumat telah berjaya disimpan");
        return new JSP("pengambilan/negerisembilan/haklalulalangpersendirian/tambah_agensi.jsp").addParameter("popup", "true");
    }

    // Click on Hapus button in HomePage
    public Resolution deleteRujukan() {
        String idRujukan;
        idRujukan = getContext().getRequest().getParameter("idRujukan");
        String idDokTt;
        try {
            if (idRujukan != null) {
                permohonanRujukanLuar = permohonanRujukanLuarDAO.findById(Long.parseLong(idRujukan));
                List<PermohonanRujukanLuarDokumen> listMohonRujLuarDok = new ArrayList<PermohonanRujukanLuarDokumen>();
                List<PermohonanRujukanLuarSalinan> listMohonRujLuarSal = new ArrayList<PermohonanRujukanLuarSalinan>();
                listMohonRujLuarDok = pengambilanService.findListDokumenRujukan(idRujukan);
                listMohonRujLuarSal = pengambilanService.findListSalinanRujukan(idRujukan);
                for (PermohonanRujukanLuarDokumen mohRujLuarDok : listMohonRujLuarDok) {
                    pengambilanService.deleteDokumen1(mohRujLuarDok);
                }
                for (PermohonanRujukanLuarSalinan mohRujLuarSal : listMohonRujLuarSal) {
                    pengambilanService.deleteSalinan(mohRujLuarSal);
                }
                enforcementService.deleteMesy(permohonanRujukanLuar);
                addSimpleMessage("Rekod dihapuskan berjaya");
            }
        } catch (Exception e) {
            e.printStackTrace();
            addSimpleError("kesalahan dalam menghapuskan rekod");
        }
        rehydrate();
        return showForm();
    }

    public Resolution deleteRujukanSalinan() {

        kod = getContext().getRequest().getParameter("idRujukan"); //Kod agensi
        idSalinan = getContext().getRequest().getParameter("idSalinan"); // Kod mohon ruj salinan
        logger.info(idRujukan + " & " + idSalinan);
        Long id = Long.parseLong(idSalinan);
        mohonRujukLuarSalinan = permohonanRujukanLuarSalinanDAO.findById(id);
        logger.info(mohonRujukLuarSalinan.getKodAgensi().getKod());
        try {
            if (mohonRujukLuarSalinan != null) {
                pengambilanService.deleteSalinan(mohonRujukLuarSalinan);
            }
        } catch (Exception e) {
            e.printStackTrace();
            addSimpleError("kesalahan dalam menghapuskan rekod");
        }
        addSimpleMessage("Rekod berjaya dihapuskan");
        return new ForwardResolution("/WEB-INF/jsp/pengambilan/negerisembilan/haklalulalangpersendirian/edit_jabatan_teknikal.jsp").addParameter("popup", "true");
    }
    /*
     * MOHON RUJUK LUAR DOKUMEN
     */

    public Resolution deleteRujukanDokumen() {

        idDokumen = getContext().getRequest().getParameter("idDokumen"); // Kod mohon ruj salinan
        Long id = Long.parseLong(idDokumen);
        pdok = permohonanRujukanLuarDokumenDAO.findById(id);
        try {
            if (pdok != null) {
                pengambilanService.deleteDokumen1(pdok);
            }
        } catch (Exception e) {
            e.printStackTrace();
            addSimpleError("kesalahan dalam menghapuskan rekod");
        }
        addSimpleMessage("Rekod berjaya dihapuskan");
        return new ForwardResolution("/WEB-INF/jsp/pengambilan/negerisembilan/haklalulalangpersendirian/edit_jabatan_teknikal.jsp").addParameter("popup", "true");
    }

    public Resolution kemaskini() {
        idRujukan = getContext().getRequest().getParameter("idRujukan");
        Long id = Long.parseLong(idRujukan);
        logger.info("mandatori : " + mandatori);
        PermohonanRujukanLuar permohonanRujukanLuar = pengambilanService.findByIdRujukan(id);
        if (permohonanRujukanLuar != null) {
            permohonanRujukanLuar.setCatatan(catatan);
            permohonanRujukanLuar.setTarikhDisampai(tarikhHantar);
            permohonanRujukanLuar.setTarikhJangkaTerima(tarikhJangkaTerima);
            permohonanRujukanLuar.setTempohHari(jangkaMasa_hari);
            if (mandatori != null) {
                permohonanRujukanLuar.setUlasanMandatori("Y");
            } else {
                permohonanRujukanLuar.setUlasanMandatori("T");
            }
        }
        pengambilanService.saveOrUpdateMRL(permohonanRujukanLuar);
        addSimpleMessage("Rekod telah dikemaskini");
        return edit();
    }

    /**
     * Get the date java.util.Date object for days after current date
     *
     * @param days
     * @return
     */
    public static Date getDateAfterDays(int days) {
        Calendar cal = Calendar.getInstance();
        day = Calendar.DAY_OF_WEEK;
        if (day == Calendar.SUNDAY) {
            cal.add(Calendar.DATE, 18);
        } else if (day == Calendar.SATURDAY) {
            cal.add(Calendar.DATE, 19);
        } else {
            cal.add(Calendar.DATE, 20);// +days
        }
        return cal.getTime();
    }

    public List<HakmilikPermohonan> getHakmilikPermohonanList() {
        return hakmilikPermohonanList;
    }

    public void setHakmilikPermohonanList(List<HakmilikPermohonan> hakmilikPermohonanList) {
        this.hakmilikPermohonanList = hakmilikPermohonanList;
    }

    public String getIdHakmilik() {
        return idHakmilik;
    }

    public void setIdHakmilik(String idHakmilik) {
        this.idHakmilik = idHakmilik;
    }

    public Permohonan getPermohonan() {
        return permohonan;
    }

    public void setPermohonan(Permohonan permohonan) {
        this.permohonan = permohonan;
    }

    public Hakmilik getHakmilik() {
        return hakmilik;
    }

    public void setHakmilik(Hakmilik hakmilik) {
        this.hakmilik = hakmilik;
    }

    public List<Pengguna> getPenggunaList() {
        return penggunaList;
    }

    public void setPenggunaList(List<Pengguna> penggunaList) {
        this.penggunaList = penggunaList;
    }

    public String getDitundatangan() {
        return ditundatangan;
    }

    public void setDitundatangan(String ditundatangan) {
        this.ditundatangan = ditundatangan;
    }

    public PermohonanTandatanganDokumen getTandatanganDokumen() {
        return tandatanganDokumen;
    }

    public void setTandatanganDokumen(PermohonanTandatanganDokumen tandatanganDokumen) {
        this.tandatanganDokumen = tandatanganDokumen;
    }

    public PermohonanTandatanganDokumen getTandatanganDokumenTemp() {
        return tandatanganDokumenTemp;
    }

    public void setTandatanganDokumenTemp(PermohonanTandatanganDokumen tandatanganDokumenTemp) {
        this.tandatanganDokumenTemp = tandatanganDokumenTemp;
    }

    public HakmilikPermohonan getHakmilikPermohonan() {
        return hakmilikPermohonan;
    }

    public void setHakmilikPermohonan(HakmilikPermohonan hakmilikPermohonan) {
        this.hakmilikPermohonan = hakmilikPermohonan;
    }

    public Configuration getConf() {
        return conf;
    }

    public void setConf(Configuration conf) {
        this.conf = conf;
    }

    public static int getDay() {
        return day;
    }

    public static void setDay(int day) {
        JabatanTeknikalPengambilanActionBean.day = day;
    }

    public String getIdMh() {
        return idMh;
    }

    public void setIdMh(String idMh) {
        this.idMh = idMh;
    }

    public String getIndex() {
        return index;
    }

    public void setIndex(String index) {
        this.index = index;
    }

    public String getkNegeri() {
        return kNegeri;
    }

    public void setkNegeri(String kNegeri) {
        this.kNegeri = kNegeri;
    }

    public FolderDokumen getFolderDokumen() {
        return folderDokumen;
    }

    public void setFolderDokumen(FolderDokumen folderDokumen) {
        this.folderDokumen = folderDokumen;
    }

    public String getIdMohonRujLuar() {
        return idMohonRujLuar;
    }

    public void setIdMohonRujLuar(String idMohonRujLuar) {
        this.idMohonRujLuar = idMohonRujLuar;
    }

    public String getIdPermohonan() {
        return idPermohonan;
    }

    public void setIdPermohonan(String idPermohonan) {
        this.idPermohonan = idPermohonan;
    }

    public KodAgensi getKodAgensi() {
        return kodAgensi;
    }

    public void setKodAgensi(KodAgensi kodAgensi) {
        this.kodAgensi = kodAgensi;
    }

    public String getKodAgensiNama() {
        return kodAgensiNama;
    }

    public void setKodAgensiNama(String kodAgensiNama) {
        this.kodAgensiNama = kodAgensiNama;
    }

    public List<KodDUN> getListAdun() {
        return listAdun;
    }

    public void setListAdun(List<KodDUN> listAdun) {
        this.listAdun = listAdun;
    }

    public List<FolderDokumen> getListFolderDokumen() {
        return listFolderDokumen;
    }

    public void setListFolderDokumen(List<FolderDokumen> listFolderDokumen) {
        this.listFolderDokumen = listFolderDokumen;
    }

    public List<KandunganFolder> getListKandunganFolder() {
        return listKandunganFolder;
    }

    public void setListKandunganFolder(List<KandunganFolder> listKandunganFolder) {
        this.listKandunganFolder = listKandunganFolder;
    }

    public List<KodAgensi> getListKodAgensi() {
        return listKodAgensi;
    }

    public void setListKodAgensi(List<KodAgensi> listKodAgensi) {
        this.listKodAgensi = listKodAgensi;
    }

    public List<KodAgensi> getListKodAgensiAdun() {
        return listKodAgensiAdun;
    }

    public void setListKodAgensiAdun(List<KodAgensi> listKodAgensiAdun) {
        this.listKodAgensiAdun = listKodAgensiAdun;
    }

    public Vector getNamaKawasan() {
        return namaKawasan;
    }

    public void setNamaKawasan(Vector namaKawasan) {
        this.namaKawasan = namaKawasan;
    }

    public PermohonanRujukanLuar getPermohonanRujukanLuar() {
        return permohonanRujukanLuar;
    }

    public void setPermohonanRujukanLuar(PermohonanRujukanLuar permohonanRujukanLuar) {
        this.permohonanRujukanLuar = permohonanRujukanLuar;
    }

    public List<PermohonanRujukanLuarDokumen> getPrsd() {
        return prsd;
    }

    public void setPrsd(List<PermohonanRujukanLuarDokumen> prsd) {
        this.prsd = prsd;
    }

    public int getSizeKod() {
        return sizeKod;
    }

    public void setSizeKod(int sizeKod) {
        this.sizeKod = sizeKod;
    }

    public String getIdRujukan() {
        return idRujukan;
    }

    public void setIdRujukan(String idRujukan) {
        this.idRujukan = idRujukan;
    }

    public String getKodKatgAgensi() {
        return kodKatgAgensi;
    }

    public void setKodKatgAgensi(String kodKatgAgensi) {
        this.kodKatgAgensi = kodKatgAgensi;
    }

    public String getKodRujukan() {
        return kodRujukan;
    }

    public void setKodRujukan(String kodRujukan) {
        this.kodRujukan = kodRujukan;
    }

    public PermohonanRujukanLuar getPprl() {
        return pprl;
    }

    public void setPprl(PermohonanRujukanLuar pprl) {
        this.pprl = pprl;
    }

    public List<PermohonanRujukanLuarSalinan> getPrs() {
        return prs;
    }

    public void setPrs(List<PermohonanRujukanLuarSalinan> prs) {
        this.prs = prs;
    }

    public String getKod() {
        return kod;
    }

    public void setKod(String kod) {
        this.kod = kod;
    }

    public PermohonanRujukanLuar getMohonRujLuar() {
        return mohonRujLuar;
    }

    public void setMohonRujLuar(PermohonanRujukanLuar mohonRujLuar) {
        this.mohonRujLuar = mohonRujLuar;
    }

    public String getRecordCount() {
        return recordCount;
    }

    public void setRecordCount(String recordCount) {
        this.recordCount = recordCount;
    }

    public List<PermohonanRujukanLuar> getSenaraiRujukanLuar() {
        return senaraiRujukanLuar;
    }

    public void setSenaraiRujukanLuar(List<PermohonanRujukanLuar> senaraiRujukanLuar) {
        this.senaraiRujukanLuar = senaraiRujukanLuar;
    }

    public Date getTarikhHantar() {
        return tarikhHantar;
    }

    public void setTarikhHantar(Date tarikhHantar) {
        this.tarikhHantar = tarikhHantar;
    }

    public List<PermohonanRujukanLuarDokumen> getSenaraiRujukanDok() {
        return senaraiRujukanDok;
    }

    public void setSenaraiRujukanDok(List<PermohonanRujukanLuarDokumen> senaraiRujukanDok) {
        this.senaraiRujukanDok = senaraiRujukanDok;
    }

    public String getCatatan() {
        return catatan;
    }

    public void setCatatan(String catatan) {
        this.catatan = catatan;
    }

    public String getIdDokumen() {
        return idDokumen;
    }

    public void setIdDokumen(String idDokumen) {
        this.idDokumen = idDokumen;
    }

    public String getIdSalinan() {
        return idSalinan;
    }

    public void setIdSalinan(String idSalinan) {
        this.idSalinan = idSalinan;
    }

    public int getJangkaMasa_hari() {
        return jangkaMasa_hari;
    }

    public void setJangkaMasa_hari(int jangkaMasa_hari) {
        this.jangkaMasa_hari = jangkaMasa_hari;
    }

    public String getMandatori() {
        return mandatori;
    }

    public void setMandatori(String mandatori) {
        this.mandatori = mandatori;
    }

    public PermohonanRujukanLuarSalinan getMohonRujukLuarSalinan() {
        return mohonRujukLuarSalinan;
    }

    public void setMohonRujukLuarSalinan(PermohonanRujukanLuarSalinan mohonRujukLuarSalinan) {
        this.mohonRujukLuarSalinan = mohonRujukLuarSalinan;
    }

    public PermohonanRujukanLuarDokumen getPdok() {
        return pdok;
    }

    public void setPdok(PermohonanRujukanLuarDokumen pdok) {
        this.pdok = pdok;
    }

    public Date getTarikhJangkaTerima() {
        return tarikhJangkaTerima;
    }

    public void setTarikhJangkaTerima(Date tarikhJangkaTerima) {
        this.tarikhJangkaTerima = tarikhJangkaTerima;
    }

    public SimpleDateFormat getSdf() {
        return sdf;
    }

    public void setSdf(SimpleDateFormat sdf) {
        this.sdf = sdf;
    }

    public String getIdHak() {
        return idHak;
    }

    public void setIdHak(String idHak) {
        this.idHak = idHak;
    }
}
