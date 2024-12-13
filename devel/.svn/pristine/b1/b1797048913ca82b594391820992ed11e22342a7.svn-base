/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.penguatkuasaan;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import etanah.dao.OperasiAgensiDAO;
import etanah.dao.PermohonanDAO;
import etanah.model.Permohonan;
import etanah.model.InfoAudit;
import etanah.model.KodCawangan;

import etanah.model.Pengguna;
import etanah.model.PermohonanLaporanUlasan;
import java.text.ParseException;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import etanah.service.common.EnforcementService;
import java.text.SimpleDateFormat;
import java.util.List;
import net.sourceforge.stripes.action.RedirectResolution;
import etanah.view.etanahActionBeanContext;
import java.util.Date;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.google.inject.Inject;
import etanah.dao.KodAgensiDAO;
import etanah.dao.KodUOMDAO;
import etanah.dao.OperasiPenguatkuasaanDAO;
import etanah.model.AduanOrangKenaSyak;
import etanah.model.KodAgensi;
import etanah.model.KodUOM;
import etanah.model.OperasiAgensi;
import etanah.model.OperasiPenguatkuasaan;
import etanah.service.BPelService;
import etanah.service.EnforceService;
import oracle.bpel.services.workflow.task.model.Task;

/**
 *
 * @author farah.shafilla
 */
@UrlBinding("/penguatkuasaan/maklumat_agensi")
public class MaklumatAgensiActionBean extends AbleActionBean {
    
    private static Logger logger = Logger.getLogger(MaklumatAgensiActionBean.class);
    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;
    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    OperasiAgensiDAO operasiAgensiDAO;
    @Inject
    OperasiPenguatkuasaanDAO operasiPenguatkuasaanDAO;
    @Inject
    EnforcementService enforcementService;
    @Inject
    EnforceService enforceService;
    @Inject
    private etanah.Configuration conf;
    @Inject
    KodAgensiDAO kodAgensiDAO;
    @Inject
    KodUOMDAO kodUOMDAO;
    private Permohonan permohonan;
    private Pengguna pengguna;
    private KodCawangan cawangan;
    private KodAgensi agensi;
    private OperasiPenguatkuasaan operasiPenguatkuasaan;
    private OperasiAgensi operasiAgensi;
    private String idPermohonan;
    private String idOperasi;
    private String idOperasiAgensi;
    private String tarikhBerkumpul;
    private String tarikhOperasi;
    private String jam;
    private String minit;
    private String saat;
    private String ampm;
    private String lokasi;
    private String nama;
    private String noTel;
    private String email;
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss aaa");
    private List<OperasiAgensi> senaraiOperasiAgensi;
    private String alamatAgensi;
    private String tajukSurat;
    private PermohonanLaporanUlasan permohonanLaporanUlasan;
    private String ulasanMesyuarat;
    private String stageId;
    private String taskId;
    private List<OperasiPenguatkuasaan> listOp;
    private List<AduanOrangKenaSyak> senaraiOksIp;
    private Boolean statusIP = Boolean.FALSE;
    private String rowCount;
    private String kodAgensi;
    private String tempatBerkumpul;
    private Long idAgensi;
    private String kodNegeri;
    private boolean hideTab; //Hafifi : for N9 only

    @DefaultHandler
    public Resolution showForm() {
        if (("04".equals(conf.getProperty("kodNegeri"))) && permohonan.getKodUrusan().getKod().equalsIgnoreCase("425")) {
            if (stageId.equalsIgnoreCase("sedia_kpsn_mesyuarat")) { // for keputusan mesyuarat
                logger.info("------------showForm1 : kpsn_mesyuarat for Melaka--------------");
                getContext().getRequest().setAttribute("kpsn_mesyuarat", Boolean.TRUE);
            }
        } else {
            logger.info("------------showForm1 : edit surat bantuan agensi--------------");
            getContext().getRequest().setAttribute("edit", Boolean.TRUE);
            
        }
        return new JSP("penguatkuasaan/surat_bantuan_agensi.jsp").addParameter("tab", "true");
    }
    
    public Resolution showForm2() {
        getContext().getRequest().setAttribute("view", Boolean.TRUE);
        return new JSP("penguatkuasaan/surat_bantuan_agensi.jsp").addParameter("tab", "true");
    }
    
    public Resolution showForm3() {
        logger.info("-----------showForm3----------------");
        getContext().getRequest().setAttribute("kpsn_mesyuarat", Boolean.TRUE);
        return new JSP("penguatkuasaan/surat_bantuan_agensi.jsp").addParameter("tab", "true");
    }
    
    public Resolution showForm4() {
        logger.info("-----------showForm4----------------");
        getContext().getRequest().setAttribute("view_kpsn_mesyuarat", Boolean.TRUE);
        return new JSP("penguatkuasaan/surat_bantuan_agensi.jsp").addParameter("tab", "true");
    }
    
    public Resolution showForm5() {
        logger.info("-----------showForm5----------------");
        getContext().getRequest().setAttribute("addKpsnMesyuarat", Boolean.TRUE);
        return new JSP("penguatkuasaan/surat_bantuan_agensi.jsp").addParameter("tab", "true");
    }
    
    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {
        logger.info("-----------rehydrate----------------");
        pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        taskId = (String) getContext().getRequest().getSession().getAttribute("taskId");
        permohonan = permohonanDAO.findById(idPermohonan);
        kodNegeri = conf.getProperty("kodNegeri");
        hideTab = false;
        
        BPelService service = new BPelService();
        
        if (StringUtils.isNotBlank(taskId)) {
            Task t = null;
            t = service.getTaskFromBPel(taskId, pengguna);
            stageId = t.getSystemAttributes().getStage();
            System.out.println("-------------stageId: BPEL ON ----" + stageId);
        } else {
            stageId = "jalan_operasi1";
            System.out.println("-------------stageId: BPEL OFF ----" + stageId);
        }
        
        if (idPermohonan != null) {
            if ("04".equals(conf.getProperty("kodNegeri"))) {
                if (permohonan.getKodUrusan().getKod().equalsIgnoreCase("425") || permohonan.getKodUrusan().getKod().equalsIgnoreCase("425A") || permohonan.getKodUrusan().getKod().equalsIgnoreCase("426") || permohonan.getKodUrusan().getKod().equalsIgnoreCase("999")) {
                    listOp = enforceService.findListLaporanOperasi(idPermohonan);
                    System.out.println("listOp size : " + listOp.size());
                    if (listOp.size() != 0) {
                    } else {
                        if (permohonan.getPermohonanSebelum() != null) {
                            listOp = enforceService.findListLaporanOperasi(permohonan.getPermohonanSebelum().getIdPermohonan());
                            
                            senaraiOksIp = enforceService.getListAduanOrangkenaSyak(idPermohonan);
                            logger.info("size senaraiOksIp : " + senaraiOksIp.size());
                            
                            if (senaraiOksIp.size() != 0) {
                                Long idOpIP = senaraiOksIp.get(0).getOperasiPenguatkuasaan().getIdOperasi();
                                logger.info("id operasi : " + idOpIP);
                                listOp = enforcementService.findListLaporanOperasi(idOpIP);
                                statusIP = true;
                                
                            }
                        }
                    }
                } else {
                    //for Melaka, only pass idPermohonan
                    operasiPenguatkuasaan = enforceService.findOperasiPenguatkuasaanByIdpermohonan(idPermohonan);
                }
            } else {
                //for N9, pass idPermohonan & kategoriTindakan
                operasiPenguatkuasaan = enforceService.findOperasiPenguatkuasaanByIdpermohonan(idPermohonan, "S");
            }
            if (operasiPenguatkuasaan != null) {
                if (operasiPenguatkuasaan.getTarikhBerkumpul() != null) {
                    tarikhBerkumpul = sdf.format(operasiPenguatkuasaan.getTarikhBerkumpul()).substring(0, 10);
                    jam = sdf.format(operasiPenguatkuasaan.getTarikhBerkumpul()).substring(11, 13);
                    minit = sdf.format(operasiPenguatkuasaan.getTarikhBerkumpul()).substring(14, 16);
                    saat = "00";
                    ampm = sdf.format(operasiPenguatkuasaan.getTarikhBerkumpul()).substring(20, 22);
                }
                senaraiOperasiAgensi = enforcementService.findOperasiByIDOpeasi(operasiPenguatkuasaan.getIdOperasi());

                //ulasan at mohon_lapor_ulas
                permohonanLaporanUlasan = enforceService.findKeputusanAduanByIdPermohonanTujuan(idPermohonan, "KPSN_MESYT");//KPSN_MESYT = Keputusan Mesyuarat
                System.out.println("permohonanLaporanUlasan (KPSN_MESYT) : " + permohonanLaporanUlasan);
                if (permohonanLaporanUlasan != null) {
                    ulasanMesyuarat = permohonanLaporanUlasan.getUlasan();
                    logger.info("-----------permohonanLaporanUlasan---------------- : " + permohonanLaporanUlasan.getUlasan());
                }
                
                if (kodNegeri.equalsIgnoreCase("05")) {
                    if (operasiPenguatkuasaan != null) {
                        if (operasiPenguatkuasaan.getTarikhBerkumpul() != null) {
                            if (operasiPenguatkuasaan.getTarikhBerkumpul().after(new Date())) {
                                hideTab = true;
                            } else {
                                hideTab = false;
                            }
                        } else {
                            hideTab = false;
                        }
                        
                        System.out.println(hideTab);
                    }
                }
            }
        }
    }
    
    public Resolution refreshpage() {
        rehydrate();
        return new RedirectResolution(MaklumatAgensiActionBean.class, "locate");
    }
    
    public Resolution simpan() throws ParseException {
        
        Session s = sessionProvider.get();
        Transaction tx = s.beginTransaction();
        InfoAudit ia = new InfoAudit();
        
        if (operasiPenguatkuasaan == null) {
            operasiPenguatkuasaan = new OperasiPenguatkuasaan();
            ia.setTarikhMasuk(new java.util.Date());
            ia.setDimasukOleh(pengguna);
        } else {
            ia = operasiPenguatkuasaan.getInfoAudit();
            if (ia == null) {
                ia = new InfoAudit();
                ia.setTarikhMasuk(new java.util.Date());
                ia.setDimasukOleh(pengguna);
            } else {
                ia.setTarikhKemaskini(new java.util.Date());
                ia.setDiKemaskiniOleh(pengguna);
            }
        }
        cawangan = permohonan.getCawangan();
        operasiPenguatkuasaan.setCawangan(cawangan);
        operasiPenguatkuasaan.setPermohonan(permohonan);
        tarikhBerkumpul = tarikhBerkumpul + " " + jam + ":" + minit + ":00 " + ampm;
        operasiPenguatkuasaan.setTarikhBerkumpul(sdf.parse(tarikhBerkumpul));
        operasiPenguatkuasaan.setInfoAudit(ia);
        if ("05".equals(conf.getProperty("kodNegeri"))) {
            //for N9, need to save into column kategoriTindakan
            operasiPenguatkuasaan.setKategoriTindakan("S"); //S = siasatan . Added by latifah.iskak 6/9/2011
            operasiPenguatkuasaan.setJenisTangkapan(kodUOMDAO.findById("OG"));
        }
        operasiPenguatkuasaanDAO.saveOrUpdate(operasiPenguatkuasaan);
        
        
        operasiAgensi = new OperasiAgensi();
        
        operasiAgensi.setOperasi(operasiPenguatkuasaan);
        operasiAgensi.setCawangan(cawangan);
        
        agensi = kodAgensiDAO.findById(agensi.getKod());
        operasiAgensi.setAgensi(agensi);
        
        StringBuilder sb = new StringBuilder();
        
        sb.append(enforceService.constructAgensiAddress(agensi.getKod()));
        
        operasiAgensi.setAlamatAgensi(sb.toString());
        operasiAgensi.setAlamatAgensi(agensi.getAlamat1() + agensi.getAlamat2() + agensi.getAlamat3() + agensi.getPoskod() + agensi.getAlamat4());
        
        if (agensi.getEmel() != null) {
            operasiAgensi.setEmailHubungan(agensi.getEmel());
        }
        
        if (agensi.getNoTelefon1() != null) {
            operasiAgensi.setNoTelefonHubungan(agensi.getNoTelefon1());
        }
        
        
        ia.setTarikhMasuk(new Date());
        ia.setDimasukOleh(pengguna);
        operasiAgensi.setInfoAudit(ia);
        operasiAgensiDAO.saveOrUpdate(operasiAgensi);
        
        tx.commit();
        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        addSimpleMessage("Maklumat telah berjaya disimpan.");
        //return new JSP("penguatkuasaan/surat_bantuan_agensi.jsp").addParameter("tab", "true");
        return new RedirectResolution(MaklumatAgensiActionBean.class, "locate");
        
    }
    
    public Resolution simpanAgensi() throws ParseException {
        logger.info("----------------simpanAgensi-----------------");
        
        Session s = sessionProvider.get();
        Transaction tx = s.beginTransaction();
        InfoAudit ia = new InfoAudit();
        
        if (operasiPenguatkuasaan == null) {
            operasiPenguatkuasaan = new OperasiPenguatkuasaan();
            ia.setTarikhMasuk(new java.util.Date());
            ia.setDimasukOleh(pengguna);
        } else {
            ia = operasiPenguatkuasaan.getInfoAudit();
            ia.setTarikhKemaskini(new java.util.Date());
            ia.setDiKemaskiniOleh(pengguna);
        }
        cawangan = permohonan.getCawangan();
        operasiPenguatkuasaan.setCawangan(cawangan);
        operasiPenguatkuasaan.setPermohonan(permohonan);
        tarikhBerkumpul = tarikhBerkumpul + " " + jam + ":" + minit + ":00 " + ampm;
        operasiPenguatkuasaan.setTarikhBerkumpul(sdf.parse(tarikhBerkumpul));
        operasiPenguatkuasaan.setInfoAudit(ia);
        if ("05".equals(conf.getProperty("kodNegeri"))) {
            //for N9, need to save into column kategoriTindakan
            operasiPenguatkuasaan.setKategoriTindakan("S"); //S = siasatan . Added by latifah.iskak 6/9/2011
        }
        operasiPenguatkuasaanDAO.saveOrUpdate(operasiPenguatkuasaan);
        
        permohonanLaporanUlasan = enforceService.findKeputusanAduanByIdPermohonanTujuan(idPermohonan, "KPSN_MESYT");//KPSN_MESYT = Keputusan Mesyuarat
        if (permohonanLaporanUlasan == null) {
            System.out.println("new mohon_lapor_ulas");
            permohonanLaporanUlasan = new PermohonanLaporanUlasan();
            ia.setDimasukOleh(pengguna);
            ia.setTarikhMasuk(new Date());
        } else {
            System.out.println("old mohon_lapor_ulas");
            ia = permohonanLaporanUlasan.getInfoAudit();
            ia.setDiKemaskiniOleh(pengguna);
            ia.setTarikhKemaskini(new java.util.Date());
        }
        System.out.println("ulasan mesyuarat : " + ulasanMesyuarat);
        permohonanLaporanUlasan.setUlasan(ulasanMesyuarat);
        permohonanLaporanUlasan.setJenisUlasan("KPSN_MESYT");
        System.out.println("pengguna.getKodCawangan() : " + pengguna.getKodCawangan());
        permohonanLaporanUlasan.setCawangan(pengguna.getKodCawangan());
        permohonanLaporanUlasan.setPermohonan(permohonan);
        permohonanLaporanUlasan.setInfoAudit(ia);
        
        enforceService.simpanRingkasanKes(permohonanLaporanUlasan);
        
        operasiAgensi = new OperasiAgensi();
        
        operasiAgensi.setOperasi(operasiPenguatkuasaan);
        operasiAgensi.setCawangan(cawangan);
        operasiAgensi.setAgensi(agensi);
        
        ia.setTarikhMasuk(new Date());
        ia.setDimasukOleh(pengguna);
        operasiAgensi.setInfoAudit(ia);
        operasiAgensiDAO.saveOrUpdate(operasiAgensi);
        
        tx.commit();
        getContext().getRequest().setAttribute("kpsn_mesyuarat", Boolean.TRUE);
        addSimpleMessage("Maklumat agensi telah berjaya ditambah.");
        return new RedirectResolution(MaklumatAgensiActionBean.class, "locate");
    }
    
    public Resolution edit() {
        System.out.println("edit");
        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        //idBarang = getContext().getRequest().getParameter("idBarang");
        for (int i = 1; i <= senaraiOperasiAgensi.size(); i++) {
            if (i <= senaraiOperasiAgensi.size()) {
                Long id = senaraiOperasiAgensi.get(i - 1).getIdOperasiAgensi();
                if (id != null) {
                    operasiAgensi = operasiAgensiDAO.findById(id);
                }
            }
            
            nama = getContext().getRequest().getParameter("nama" + i);
            operasiAgensi.setNamaHubungan(nama);
            noTel = getContext().getRequest().getParameter("noTel" + i);
            operasiAgensi.setNoTelefonHubungan(noTel);
            email = getContext().getRequest().getParameter("email" + i);
            operasiAgensi.setEmailHubungan(email);

            //logger.debug(stageId+"dddddd");

            InfoAudit ia = operasiAgensi.getInfoAudit();
            ia.setDiKemaskiniOleh(peng);
            ia.setTarikhKemaskini(new java.util.Date());
            operasiAgensi.setInfoAudit(ia);
            enforceService.updateOperasiAgensi(operasiAgensi);
        }
        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        addSimpleMessage("Maklumat telah berjaya dikemaskini.");
        return new JSP("penguatkuasaan/surat_bantuan_agensi.jsp").addParameter("popup", "true");
    }
    
    public Resolution deleteSingle() {
        logger.info("-----------deleteSingle----------------");
        idOperasiAgensi = getContext().getRequest().getParameter("idOperasiAgensi");
        operasiAgensi = enforcementService.findOperasiByIdOPAgensi(Long.parseLong(idOperasiAgensi));
        
        if (operasiAgensi != null) {
            enforcementService.deleteOperasiAgensi(operasiAgensi);
        }
        return new RedirectResolution(MaklumatAgensiActionBean.class, "locate");
    }
    
    public Resolution simpanSurat() throws ParseException {
        logger.debug("start simpan");
        Session s = sessionProvider.get();
        Transaction tx = s.beginTransaction();
        
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = permohonanDAO.findById(idPermohonan);
        cawangan = permohonan.getCawangan();
        
        if (operasiPenguatkuasaan == null) {
            operasiPenguatkuasaan = new OperasiPenguatkuasaan();
        }
        
        
        operasiPenguatkuasaan.setPermohonan(permohonan);
        operasiPenguatkuasaan.setCawangan(cawangan);
        tarikhBerkumpul = tarikhBerkumpul + " " + jam + ":" + minit + ":00 " + ampm;
        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        InfoAudit ia = operasiPenguatkuasaan.getInfoAudit();
        if (ia == null) {
            ia = new InfoAudit();
            ia.setDimasukOleh(peng);
            ia.setTarikhMasuk(new Date());
            operasiPenguatkuasaan.setInfoAudit(ia);
        } else {
            ia.setDiKemaskiniOleh(peng);
            ia.setTarikhKemaskini(new java.util.Date());
        }
        operasiPenguatkuasaan.setTarikhBerkumpul(sdf.parse(tarikhBerkumpul));
        
        if ("05".equalsIgnoreCase(conf.getProperty("kodNegeri"))) {
            if (operasiPenguatkuasaan.getJenisTangkapan() == null) {
                operasiPenguatkuasaan.setJenisTangkapan(kodUOMDAO.findById("OG"));
            }
            
            if (operasiPenguatkuasaan.getKategoriTindakan() == null) {
                operasiPenguatkuasaan.setKategoriTindakan("S");
            }
        }
        
        enforcementService.simpanOperasi(operasiPenguatkuasaan);
        
        if (operasiPenguatkuasaan.getTarikhBerkumpul().after(new Date())) {
            hideTab = true;
        }
        
        if (senaraiOperasiAgensi != null) {
            for (int i = 1; i <= senaraiOperasiAgensi.size(); i++) {
                if (i <= senaraiOperasiAgensi.size()) {
                    Long id = senaraiOperasiAgensi.get(i - 1).getIdOperasiAgensi();
                    if (id != null) {
                        operasiAgensi = operasiAgensiDAO.findById(id);
                    }
                }
                
                nama = getContext().getRequest().getParameter("nama" + i);
                operasiAgensi.setNamaHubungan(nama);
                noTel = getContext().getRequest().getParameter("noTel" + i);
                operasiAgensi.setNoTelefonHubungan(noTel);
                email = getContext().getRequest().getParameter("email" + i);
                operasiAgensi.setEmailHubungan(email);
                alamatAgensi = getContext().getRequest().getParameter("alamatAgensi" + i);
                System.out.println("alamat b4 save : " + alamatAgensi);
                operasiAgensi.setAlamatAgensi(alamatAgensi);
                tajukSurat = getContext().getRequest().getParameter("tajukSurat" + i);
                operasiAgensi.setCatatan(tajukSurat);
                System.out.println("alamat agensi : " + operasiAgensi.getAlamatAgensi());
                
                
                ia = operasiAgensi.getInfoAudit();
                ia.setDiKemaskiniOleh(peng);
                ia.setTarikhKemaskini(new java.util.Date());
                operasiAgensi.setInfoAudit(ia);
                enforceService.updateOperasiAgensi(operasiAgensi);
            }
        }
        
        addSimpleMessage("Maklumat telah berjaya disimpan.");
        tx.commit();
        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        return new JSP("penguatkuasaan/surat_bantuan_agensi.jsp").addParameter("tab", "true");
    }
    
    public Resolution simpanKeputusanMesyuarat() throws ParseException {
        logger.debug("--------------simpanKeputusanMesyuarat-----------------");
        
        if (operasiPenguatkuasaan == null) {
            operasiPenguatkuasaan = new OperasiPenguatkuasaan();
        }
        operasiPenguatkuasaan.setPermohonan(permohonan);
        operasiPenguatkuasaan.setCawangan(permohonan.getCawangan());
        tarikhBerkumpul = tarikhBerkumpul + " " + jam + ":" + minit + ":00 " + ampm;
        
        InfoAudit ia = new InfoAudit();
        if (ia == null) {
            ia.setDimasukOleh(pengguna);
            ia.setTarikhMasuk(new Date());
            
        } else {
            ia = operasiPenguatkuasaan.getInfoAudit();
            ia.setDiKemaskiniOleh(pengguna);
            ia.setTarikhKemaskini(new java.util.Date());
        }
        operasiPenguatkuasaan.setInfoAudit(ia);
        operasiPenguatkuasaan.setTarikhBerkumpul(sdf.parse(tarikhBerkumpul));
        
        enforcementService.simpanOperasi(operasiPenguatkuasaan);
        
        permohonanLaporanUlasan = enforceService.findKeputusanAduanByIdPermohonanTujuan(idPermohonan, "KPSN_MESYT");//KPSN_MESYT = Keputusan Mesyuarat
        if (permohonanLaporanUlasan == null) {
            System.out.println("new mohon_lapor_ulas");
            permohonanLaporanUlasan = new PermohonanLaporanUlasan();
            ia.setDimasukOleh(pengguna);
            ia.setTarikhMasuk(new Date());
        } else {
            System.out.println("old mohon_lapor_ulas");
            ia = permohonanLaporanUlasan.getInfoAudit();
            ia.setDiKemaskiniOleh(pengguna);
            ia.setTarikhKemaskini(new java.util.Date());
        }
        System.out.println("ulasan mesyuarat : " + ulasanMesyuarat);
        permohonanLaporanUlasan.setUlasan(ulasanMesyuarat);
        permohonanLaporanUlasan.setJenisUlasan("KPSN_MESYT");
        System.out.println("pengguna.getKodCawangan() : " + pengguna.getKodCawangan());
        permohonanLaporanUlasan.setCawangan(pengguna.getKodCawangan());
        permohonanLaporanUlasan.setPermohonan(permohonan);
        permohonanLaporanUlasan.setInfoAudit(ia);
        
        enforceService.simpanRingkasanKes(permohonanLaporanUlasan);
        
        for (int i = 1; i <= senaraiOperasiAgensi.size(); i++) {
            if (i <= senaraiOperasiAgensi.size()) {
                Long id = senaraiOperasiAgensi.get(i - 1).getIdOperasiAgensi();
                if (id != null) {
                    operasiAgensi = operasiAgensiDAO.findById(id);
                }
            }
            
            nama = getContext().getRequest().getParameter("nama" + i);
            operasiAgensi.setNamaHubungan(nama);
            noTel = getContext().getRequest().getParameter("noTel" + i);
            operasiAgensi.setNoTelefonHubungan(noTel);
            email = getContext().getRequest().getParameter("email" + i);
            operasiAgensi.setEmailHubungan(email);
            alamatAgensi = getContext().getRequest().getParameter("alamatAgensi" + i);
            tajukSurat = getContext().getRequest().getParameter("tajukSurat" + i);
            System.out.println("alamat b4 save : " + alamatAgensi);
            operasiAgensi.setAlamatAgensi(alamatAgensi);
            operasiAgensi.setCatatan(tajukSurat);
            System.out.println("alamat agensi : " + operasiAgensi.getAlamatAgensi());
            
            
            ia = operasiAgensi.getInfoAudit();
            ia.setDiKemaskiniOleh(pengguna);
            ia.setTarikhKemaskini(new java.util.Date());
            operasiAgensi.setInfoAudit(ia);
            enforceService.updateOperasiAgensi(operasiAgensi);
        }
        
        tarikhBerkumpul = sdf.format(operasiPenguatkuasaan.getTarikhBerkumpul()).substring(0, 10);
        addSimpleMessage("Maklumat telah berjaya disimpan.");
        getContext().getRequest().setAttribute("kpsn_mesyuarat", Boolean.TRUE);
        return new JSP("penguatkuasaan/surat_bantuan_agensi.jsp").addParameter("tab", "true");
    }
    
    public Resolution addRecord() {
        logger.info("--------------addRecord--------------");
        idOperasi = getContext().getRequest().getParameter("idOp");
        logger.info("::addRecord - id OP : " + idOperasi);
        
        operasiPenguatkuasaan = enforceService.findByIdOp(Long.parseLong(idOperasi));
        if (operasiPenguatkuasaan != null) {
            senaraiOperasiAgensi = enforcementService.findOperasiByIDOpeasi(operasiPenguatkuasaan.getIdOperasi());
            tempatBerkumpul = operasiPenguatkuasaan.getTempatBerkumpul();
            if (operasiPenguatkuasaan.getTarikhBerkumpul() != null) {
                tarikhBerkumpul = sdf.format(operasiPenguatkuasaan.getTarikhBerkumpul()).substring(0, 10);
                jam = sdf.format(operasiPenguatkuasaan.getTarikhBerkumpul()).substring(11, 13);
                minit = sdf.format(operasiPenguatkuasaan.getTarikhBerkumpul()).substring(14, 16);
                saat = "00";
                ampm = sdf.format(operasiPenguatkuasaan.getTarikhBerkumpul()).substring(20, 22);
            }
        }
        
        permohonanLaporanUlasan = enforceService.findKeputusanAduanByIdPermohonanTujuan(idPermohonan, "KPSN_MESYT");//KPSN_MESYT = Keputusan Mesyuarat
        if (permohonanLaporanUlasan != null) {
            ulasanMesyuarat = permohonanLaporanUlasan.getUlasan();
        }
        
        rowCount = String.valueOf(senaraiOperasiAgensi.size());
        
        return new JSP("penguatkuasaan/popup_tambah_keputusan_mesyuarat.jsp").addParameter("popup", "true");
    }
    
    public Resolution simpanKpsnMesyuarat() throws Exception {
        logger.info("--------------simpanKpsnMesyuarat--------------");
        idOperasi = getContext().getRequest().getParameter("idOp");
        logger.info("::addRecord - id OP : " + idOperasi);
        InfoAudit ia = new InfoAudit();
        operasiPenguatkuasaan = enforceService.findByIdOp(Long.parseLong(idOperasi));
        if (operasiPenguatkuasaan != null) {
            ia = operasiPenguatkuasaan.getInfoAudit();
            ia.setDiKemaskiniOleh(pengguna);
            ia.setTarikhKemaskini(new java.util.Date());
            
            operasiPenguatkuasaan.setTempatBerkumpul(tempatBerkumpul);
            
            if (StringUtils.isNotBlank(tarikhBerkumpul) && StringUtils.isNotBlank(jam) && StringUtils.isNotBlank(minit) && StringUtils.isNotBlank(ampm)) {
                tarikhBerkumpul = tarikhBerkumpul + " " + jam + ":" + minit + ":00 " + ampm;
                logger.info("tarikhBerkumpul :" + tarikhBerkumpul);
            }
            
            if (StringUtils.isNotBlank(tarikhBerkumpul)) {
                operasiPenguatkuasaan.setTarikhBerkumpul(sdf.parse(tarikhBerkumpul));
            }
            enforceService.simpanOperasiPenguatkuasaan(operasiPenguatkuasaan);
        }
        
        permohonanLaporanUlasan = enforceService.findKeputusanAduanByIdPermohonanTujuan(idPermohonan, "KPSN_MESYT");//KPSN_MESYT = Keputusan Mesyuarat
        if (permohonanLaporanUlasan == null) {
            System.out.println("new mohon_lapor_ulas");
            permohonanLaporanUlasan = new PermohonanLaporanUlasan();
            ia.setDimasukOleh(pengguna);
            ia.setTarikhMasuk(new Date());
        } else {
            System.out.println("old mohon_lapor_ulas");
            ia = permohonanLaporanUlasan.getInfoAudit();
            ia.setDiKemaskiniOleh(pengguna);
            ia.setTarikhKemaskini(new java.util.Date());
        }
        System.out.println("ulasan mesyuarat : " + ulasanMesyuarat);
        permohonanLaporanUlasan.setUlasan(ulasanMesyuarat);
        permohonanLaporanUlasan.setJenisUlasan("KPSN_MESYT");
        permohonanLaporanUlasan.setCawangan(pengguna.getKodCawangan());
        permohonanLaporanUlasan.setPermohonan(permohonan);
        permohonanLaporanUlasan.setInfoAudit(ia);
        
        enforceService.simpanRingkasanKes(permohonanLaporanUlasan);
        
        senaraiOperasiAgensi = enforcementService.findOperasiByIDOpeasi(operasiPenguatkuasaan.getIdOperasi());
        if (StringUtils.isNotBlank(rowCount)) {
            for (int i = 0; i < Integer.parseInt(rowCount); i++) {
                if (senaraiOperasiAgensi.size() != 0 && i < senaraiOperasiAgensi.size()) {
                    Long id = senaraiOperasiAgensi.get(i).getIdOperasiAgensi();
                    if (id != null) {
                        operasiAgensi = operasiAgensiDAO.findById(id);
                        ia = operasiAgensi.getInfoAudit();
                        ia.setDiKemaskiniOleh(pengguna);
                        ia.setTarikhKemaskini(new java.util.Date());
                    } else {
                        System.out.println("create new operasiAgensi if list not empty");
                        operasiAgensi = new OperasiAgensi();
                        ia.setDimasukOleh(pengguna);
                        ia.setTarikhMasuk(new java.util.Date());
                    }
                } else {
                    System.out.println("create new operasiAgensi if list empty");
                    operasiAgensi = new OperasiAgensi();
                    ia.setDimasukOleh(pengguna);
                    ia.setTarikhMasuk(new java.util.Date());
                }
                
                kodAgensi = getContext().getRequest().getParameter("kodAgensi" + i);
                operasiAgensi.setAgensi(kodAgensiDAO.findById(kodAgensi));
                nama = getContext().getRequest().getParameter("nama" + i);
                operasiAgensi.setNamaHubungan(nama);
                noTel = getContext().getRequest().getParameter("noTel" + i);
                operasiAgensi.setNoTelefonHubungan(noTel);
                email = getContext().getRequest().getParameter("email" + i);
                operasiAgensi.setEmailHubungan(email);
                alamatAgensi = getContext().getRequest().getParameter("alamatAgensi" + i);
                System.out.println("alamat b4 save : " + alamatAgensi);
                operasiAgensi.setAlamatAgensi(alamatAgensi);
                tajukSurat = getContext().getRequest().getParameter("tajukSurat" + i);
                operasiAgensi.setCatatan(tajukSurat);
                System.out.println("alamat agensi : " + operasiAgensi.getAlamatAgensi());
                
                
                operasiAgensi.setInfoAudit(ia);
                operasiAgensi.setCawangan(pengguna.getKodCawangan());
                operasiAgensi.setOperasi(operasiPenguatkuasaan);
                enforceService.updateOperasiAgensi(operasiAgensi);
                
            }
        }
        
        getContext().getRequest().setAttribute("addKpsnMesyuarat", Boolean.TRUE);
        return new JSP("penguatkuasaan/surat_bantuan_agensi.jsp").addParameter("tab", "true");
    }
    
    public Resolution refreshpageNotis() {
        getContext().getRequest().setAttribute("addKpsnMesyuarat", Boolean.TRUE);
        rehydrate();
        return new JSP("penguatkuasaan/surat_bantuan_agensi.jsp").addParameter("tab", "true");
    }
    
    public Resolution deleteAgensi() {
        logger.info("-----------deleteAgensi----------------");
        idOperasiAgensi = getContext().getRequest().getParameter("idOperasiAgensi");
        operasiAgensi = enforcementService.findOperasiByIdOPAgensi(Long.parseLong(idOperasiAgensi));
        
        if (operasiAgensi != null) {
            enforcementService.deleteOperasiAgensi(operasiAgensi);
        }
        
        return refreshpageNotis();
//        return new RedirectResolution(MaklumatAgensiActionBean.class, "locate");
    }
    
    public Resolution viewMaklumatAgensi() {
        logger.info("--------------viewMaklumatAgensi--------------");
        idAgensi = Long.parseLong(getContext().getRequest().getParameter("idAgensi"));
        logger.info("::view record - id Agensi : " + idAgensi);
        
        operasiAgensi = operasiAgensiDAO.findById(idAgensi);
        getContext().getRequest().setAttribute("viewMaklumatAgensi", Boolean.TRUE);
        return new JSP("penguatkuasaan/popup_tambah_keputusan_mesyuarat.jsp").addParameter("popup", "true");
    }
    
    public KodCawangan getCawangan() {
        return cawangan;
    }
    
    public void setCawangan(KodCawangan cawangan) {
        this.cawangan = cawangan;
    }
    
    public String getIdPermohonan() {
        return idPermohonan;
    }
    
    public void setIdPermohonan(String idPermohonan) {
        this.idPermohonan = idPermohonan;
    }
    
    public OperasiPenguatkuasaan getOperasiPenguatkuasaan() {
        return operasiPenguatkuasaan;
    }
    
    public void setOperasiPenguatkuasaan(OperasiPenguatkuasaan operasiPenguatkuasaan) {
        this.operasiPenguatkuasaan = operasiPenguatkuasaan;
    }
    
    public Pengguna getPengguna() {
        return pengguna;
    }
    
    public void setPengguna(Pengguna pengguna) {
        this.pengguna = pengguna;
    }
    
    public Permohonan getPermohonan() {
        return permohonan;
    }
    
    public void setPermohonan(Permohonan permohonan) {
        this.permohonan = permohonan;
    }
    
    public OperasiAgensi getOperasiAgensi() {
        return operasiAgensi;
    }
    
    public void setOperasiAgensi(OperasiAgensi operasiAgensi) {
        this.operasiAgensi = operasiAgensi;
    }
    
    public KodAgensi getAgensi() {
        return agensi;
    }
    
    public void setAgensi(KodAgensi agensi) {
        this.agensi = agensi;
    }
    
    public String getIdOperasi() {
        return idOperasi;
    }
    
    public void setIdOperasi(String idOperasi) {
        this.idOperasi = idOperasi;
    }
    
    public List<OperasiAgensi> getSenaraiOperasiAgensi() {
        return senaraiOperasiAgensi;
    }
    
    public void setSenaraiOperasiAgensi(List<OperasiAgensi> senaraiOperasiAgensi) {
        this.senaraiOperasiAgensi = senaraiOperasiAgensi;
    }
    
    public String getTarikhBerkumpul() {
        return tarikhBerkumpul;
    }
    
    public void setTarikhBerkumpul(String tarikhBerkumpul) {
        this.tarikhBerkumpul = tarikhBerkumpul;
    }
    
    public String getAmpm() {
        return ampm;
    }
    
    public void setAmpm(String ampm) {
        this.ampm = ampm;
    }
    
    public String getJam() {
        return jam;
    }
    
    public void setJam(String jam) {
        this.jam = jam;
    }
    
    public String getMinit() {
        return minit;
    }
    
    public void setMinit(String minit) {
        this.minit = minit;
    }
    
    public String getSaat() {
        return saat;
    }
    
    public void setSaat(String saat) {
        this.saat = saat;
    }
    
    public String getIdOperasiAgensi() {
        return idOperasiAgensi;
    }
    
    public void setIdOperasiAgensi(String idOperasiAgensi) {
        this.idOperasiAgensi = idOperasiAgensi;
    }
    
    public String getLokasi() {
        return lokasi;
    }
    
    public void setLokasi(String lokasi) {
        this.lokasi = lokasi;
    }
    
    public String getTarikhOperasi() {
        return tarikhOperasi;
    }
    
    public void setTarikhOperasi(String tarikhOperasi) {
        this.tarikhOperasi = tarikhOperasi;
    }
    
    public String getNama() {
        return nama;
    }
    
    public void setNama(String nama) {
        this.nama = nama;
    }
    
    public String getNoTel() {
        return noTel;
    }
    
    public void setNoTel(String noTel) {
        this.noTel = noTel;
    }
    
    public String getEmail() {
        return email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    
    public String getAlamatAgensi() {
        return alamatAgensi;
    }
    
    public void setAlamatAgensi(String alamatAgensi) {
        this.alamatAgensi = alamatAgensi;
    }
    
    public PermohonanLaporanUlasan getPermohonanLaporanUlasan() {
        return permohonanLaporanUlasan;
    }
    
    public void setPermohonanLaporanUlasan(PermohonanLaporanUlasan permohonanLaporanUlasan) {
        this.permohonanLaporanUlasan = permohonanLaporanUlasan;
    }
    
    public String getUlasanMesyuarat() {
        return ulasanMesyuarat;
    }
    
    public void setUlasanMesyuarat(String ulasanMesyuarat) {
        this.ulasanMesyuarat = ulasanMesyuarat;
    }
    
    public String getStageId() {
        return stageId;
    }
    
    public void setStageId(String stageId) {
        this.stageId = stageId;
    }
    
    public String getTaskId() {
        return taskId;
    }
    
    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }
    
    public List<OperasiPenguatkuasaan> getListOp() {
        return listOp;
    }
    
    public void setListOp(List<OperasiPenguatkuasaan> listOp) {
        this.listOp = listOp;
    }
    
    public List<AduanOrangKenaSyak> getSenaraiOksIp() {
        return senaraiOksIp;
    }
    
    public void setSenaraiOksIp(List<AduanOrangKenaSyak> senaraiOksIp) {
        this.senaraiOksIp = senaraiOksIp;
    }
    
    public Boolean getStatusIP() {
        return statusIP;
    }
    
    public void setStatusIP(Boolean statusIP) {
        this.statusIP = statusIP;
    }
    
    public String getRowCount() {
        return rowCount;
    }
    
    public void setRowCount(String rowCount) {
        this.rowCount = rowCount;
    }
    
    public String getKodAgensi() {
        return kodAgensi;
    }
    
    public void setKodAgensi(String kodAgensi) {
        this.kodAgensi = kodAgensi;
    }
    
    public String getTempatBerkumpul() {
        return tempatBerkumpul;
    }
    
    public void setTempatBerkumpul(String tempatBerkumpul) {
        this.tempatBerkumpul = tempatBerkumpul;
    }
    
    public Long getIdAgensi() {
        return idAgensi;
    }
    
    public void setIdAgensi(Long idAgensi) {
        this.idAgensi = idAgensi;
    }
    
    public String getKodNegeri() {
        return kodNegeri;
    }
    
    public void setKodNegeri(String kodNegeri) {
        this.kodNegeri = kodNegeri;
    }
    
    public String getTajukSurat() {
        return tajukSurat;
    }
    
    public void setTajukSurat(String tajukSurat) {
        this.tajukSurat = tajukSurat;
    }
    
    public boolean isHideTab() {
        return hideTab;
    }
    
    public void setHideTab(boolean hideTab) {
        this.hideTab = hideTab;
    }
}
