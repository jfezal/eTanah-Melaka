/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.stripes.lelong;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.dao.KodJenisPengenalanDAO;
import etanah.dao.KodNegeriDAO;
import etanah.dao.PeguamDAO;
import etanah.dao.PermohonanDAO;
import etanah.dao.PermohonanPeguamDAO;
import etanah.model.InfoAudit;
import etanah.model.KodCawangan;
import etanah.model.KodJenisPengenalan;
import etanah.model.KodNegeri;
import etanah.model.Peguam;
import etanah.model.Pembida;
import etanah.model.Pengguna;
import etanah.model.Penyerah;
import etanah.model.Permohonan;
import etanah.model.PermohonanPeguam;
import etanah.service.common.EnkuiriService;
import etanah.service.common.LelongService;
import etanah.view.etanahActionBeanContext;
import etanah.view.kaunter.RequestPeguamInfo;
import java.util.ArrayList;
import java.util.List;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.StreamingResolution;
import net.sourceforge.stripes.action.UrlBinding;
import org.apache.log4j.Logger;
import org.apache.commons.lang.StringUtils;
import org.hibernate.Session;

/**
 *
 * @author Administrator
 */
@UrlBinding("/lelong/peguam")
public class UtilitiPilihPeguamActionBean extends AbleActionBean {

    @Inject
    private PermohonanDAO permohonanDAO;
    @Inject
    private PeguamDAO peguamDAO;
    @Inject
    private PermohonanPeguamDAO mohonpeguamDAO;
    @Inject
    KodNegeriDAO kodNegeriDAO;
    @Inject
    KodJenisPengenalanDAO kodJenisPengenalanDAO;
    @Inject
    EnkuiriService ES;
    @Inject
    LelongService LS;
    private static final Logger LOG = Logger.getLogger(UtilitiPilihPeguamActionBean.class);
    private Permohonan permohonan;
    private PermohonanPeguam permohonanPeguam;
    private String idPermohonan;
    private Peguam peguam;
    private List<Permohonan> senaraiPermohonan;
    private List<Penyerah> senaraiPenyerah;
    private List<Peguam> senaraiPeguam;
    private String jenisPenyerah;
    private String namaPenyerah;
    private List<PermohonanPeguam> peguamList;
    @Inject
    private RequestPeguamInfo pi;
    private String idPenyerah;
    private String idPeguam;
    private String penyerahNama;
    private String penyerahAlamat1;
    private String penyerahAlamat2;
    private String penyerahAlamat3;
    private String penyerahAlamat4;
    private String penyerahPoskod;
    private String penyerahNegeri;
    private String penyerahNoPengenalan;
    private String penyerahJenisPengenalan;
    private String penyerahNoTelefon;
    private String penyerahEmail;
    private String kodPenyerah;
    private String kodNegeri;
    private String kodJenis;
    private boolean isPopup = false;
    private boolean form = false;
    private boolean flag = false;
    private List<PermohonanPeguam> senaraimohonP;
    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;

    @DefaultHandler
    public Resolution insertIdPermohonan() {
        return new ForwardResolution("/WEB-INF/jsp/lelong/UtilitiPilihPeguam.jsp");
    }

    public Resolution find() {
        permohonan = permohonanDAO.findById(idPermohonan);
        if (permohonan != null) {

            LOG.info("idPermohonan: " + permohonan.getIdPermohonan());
            senaraiPermohonan = new ArrayList<Permohonan>();
            senaraiPermohonan.add(permohonan);

            List<PermohonanPeguam> listmp = ES.listPeguamY(idPermohonan);
            if (!listmp.isEmpty()) {
                LOG.info("peguam dalam mohon ni:" + listmp.size());
                peguamList = ES.listPeguamAK(permohonan.getIdPermohonan());
            }
        }

        getContext().getRequest().setAttribute("idPermohonan", idPermohonan);
        return new ForwardResolution("/WEB-INF/jsp/lelong/UtilitiPilihPeguam.jsp");
    }
    
    public Resolution find2() {
        String id = getContext().getRequest().getParameter("idPermohonan");
        permohonan = permohonanDAO.findById(id);
        if (permohonan != null) {

            LOG.info("idPermohonan: " + permohonan.getIdPermohonan());
            senaraiPermohonan = new ArrayList<Permohonan>();
            senaraiPermohonan.add(permohonan);

            List<PermohonanPeguam> listmp = ES.listPeguamY(idPermohonan);
            if (!listmp.isEmpty()) {
                LOG.info("peguam dalam mohon ni:" + listmp.size());
                peguamList = ES.listPeguamAK(permohonan.getIdPermohonan());
            }
        }

        getContext().getRequest().setAttribute("idPermohonan", idPermohonan);
        return new ForwardResolution("/WEB-INF/jsp/lelong/UtilitiPilihPeguam.jsp").addParameter("popup", "true");
    }
    
    public Resolution reloadPage(){
        find2();
        return new ForwardResolution("/WEB-INF/jsp/lelong/tukarpeguam.jsp").addParameter("popup", "true");
    }

    public Resolution tukarPeguam() {
        LOG.info("idPermohonan : " + idPermohonan);
        getContext().getRequest().setAttribute("popup", "true");
        return new ForwardResolution("/WEB-INF/jsp/lelong/tukarpeguam.jsp").addParameter("popup", "true");
    }

    public Resolution tambahPeguam() {
        String id = getContext().getRequest().getParameter("id");
        getContext().getRequest().setAttribute("id", id);
        LOG.info("idPermohonan :" + idPermohonan);
        return new ForwardResolution("/WEB-INF/jsp/lelong/tambahPeguamBaru.jsp").addParameter("popup", "true");
    }

    public Resolution showForm() {
        String popup = getContext().getRequest().getParameter("popup");
        if (StringUtils.isNotBlank(popup)) {
            isPopup = true;
        }

        if (isPopup) {
            getContext().getRequest().setAttribute("popup", "true");
            return new ForwardResolution("/WEB-INF/jsp/lelong/tukarpeguam.jsp").addParameter("popup", "true");
        } else {
            return new ForwardResolution("/WEB-INF/jsp/lelong/tukarpeguam.jsp");
        }
    }
    
       public Resolution doCheckIC() {
        LOG.info("------check duplicate ic---------");

        String noPengenalan = getContext().getRequest().getParameter("icno");
        LOG.info("*****AjaxValidateIC.doCheckIC :" + getContext().getRequest().getParameter("icno"));
        String results = "0";
        peguam= LS.checkPeguam(noPengenalan);
        if(peguam!=null){
            results="1";
        }
        LOG.debug("results : " + results);
        return new StreamingResolution("text/plain", results);
    }

    public Resolution showFormPopup() {
        getContext().getRequest().setAttribute("popup", "true");
        return new ForwardResolution("/WEB-INF/jsp/lelong/tukarpeguam.jsp").addParameter("popup", "true");

    }

    public Resolution searchPenyerah() {
        LOG.info("::start cari penyerah::");
        findPenyerah();
        return showFormPopup();
    }

    public void findPenyerah() {
        if (StringUtils.isBlank(namaPenyerah) && StringUtils.isBlank(idPenyerah)) {
            senaraiPenyerah = pi.findAll();
        } else {
            senaraiPenyerah = pi.findPenyerahByParam(getContext().getRequest().getParameterMap());
//            {
        }
        if (senaraiPenyerah != null) {
            if (senaraiPenyerah.isEmpty()) {
                form = true;
            }
        }

        senaraiPeguam = new ArrayList<Peguam>();
        senaraiPeguam = peguamDAO.findAll();
        LOG.info("peguam.size() : " + senaraiPeguam.size());
        LOG.info("idPermohonan : " + idPermohonan);
    }
//        }

//    public void findPenyerah() {
//        if (StringUtils.isBlank(namaPenyerah) && StringUtils.isBlank(idPenyerah)) {
//            LOG.info("1");
//            senaraiPenyerah = pi.findAll();
//            LOG.info("2");
//        } else {
//            LOG.info("3");
//            senaraiPenyerah = pi.findPenyerahByParam(getContext().getRequest().getParameterMap());
//        }
//
//        if (senaraiPenyerah.isEmpty()) {
//            form = true;
//        }
//
////        senaraiPeguam = new ArrayList<Peguam>();
////        senaraiPeguam = peguamDAO.findAll();
////        LOG.info("peguam.size() : " + senaraiPeguam.size());
////        LOG.info("idPermohonan : " + idPermohonan);
////        return new ForwardResolution("/WEB-INF/jsp/lelong/tukarpeguam.jsp").addParameter("popup", "true");
//    }
    
    public Resolution reset() {
        LOG.info("---nk reset---");
        permohonan = new Permohonan();
        idPermohonan = null;
        return new ForwardResolution("/WEB-INF/jsp/lelong/UtilitiPilihPeguam.jsp");
    }
    
    public Resolution simpanPeguam() {
        LOG.info("simpan peguam baru "+idPermohonan);

        permohonan = permohonanDAO.findById(idPermohonan);
        if (permohonan != null) {

            LOG.info("idPermohonan: " + permohonan.getIdPermohonan());
            senaraiPermohonan = new ArrayList<Permohonan>();
            senaraiPermohonan.add(permohonan);

        }

        idPenyerah = getContext().getRequest().getParameter("idPenyerah");
        getContext().getRequest().setAttribute("idPenyerah", idPenyerah);
        LOG.info("----id Peguam ---- :" + idPenyerah);

        Peguam peguam1 = peguamDAO.findById(Long.valueOf(idPenyerah));
        LOG.info("id Peguam :" + peguam1.getIdPeguam());
        if (idPenyerah == null) {
            addSimpleError("Sila Pilih Peguam");
            return new ForwardResolution("/WEB-INF/jsp/lelong/tukarpeguam.jsp");
        }
        LOG.info("---id Peguam--- :" + idPenyerah);
        Peguam peguam2 = peguamDAO.findById(Long.parseLong(idPenyerah));
        LOG.info("Peguam :" + peguam2.getIdPeguam());


        etanahActionBeanContext ctx = new etanahActionBeanContext();
        ctx = (etanahActionBeanContext) getContext();
        Pengguna pengguna = ctx.getUser();

        permohonan = permohonanDAO.findById(idPermohonan);
        PermohonanPeguam pp = new PermohonanPeguam();

        InfoAudit ia = new InfoAudit();
        ia.setDimasukOleh(pengguna);
        ia.setTarikhMasuk(new java.util.Date());
        ia.setDiKemaskiniOleh(pengguna);
        ia.setTarikhKemaskini(new java.util.Date());
        pp.setInfoAudit(ia);


        senaraimohonP = ES.listPeguamAK(permohonan.getIdPermohonan());

        for (PermohonanPeguam p : senaraimohonP) {
            if (p.getAktif().equals("Y")) {
                p.setAktif("T");
                ES.saveOrUpdate(p);
            }
        }

        KodCawangan kodc = pengguna.getKodCawangan();
        pp.setCawangan(kodc);

        pp.setIdPermohonan(permohonan);
        pp.setPeguam(peguam2);
        pp.setAktif("Y");

        ES.saveOrUpdate(pp);

        addSimpleMessage("Maklumat telah berjaya disimpan..");
        senaraiMohonPeguam();
        return new ForwardResolution("/WEB-INF/jsp/lelong/displayPeguam.jsp");
    }

    public Resolution simpanTambahPeguam() {
        LOG.info("tambah peguam baru");
        LOG.info("idPermohonan : " + idPermohonan);

        permohonan = permohonanDAO.findById(idPermohonan);

        etanahActionBeanContext ctx = new etanahActionBeanContext();
        ctx = (etanahActionBeanContext) getContext();
        Pengguna pengguna = ctx.getUser();

        InfoAudit ia = new InfoAudit();
        ia.setDimasukOleh(pengguna);
        ia.setTarikhMasuk(new java.util.Date());
        ia.setDiKemaskiniOleh(pengguna);
        ia.setTarikhKemaskini(new java.util.Date());
        peguam.setInfoAudit(ia);

        KodNegeri kod = kodNegeriDAO.findById(kodNegeri);
        kod.setKod(kodNegeri);

        peguam.setNegeri(kod);
        KodCawangan kodc = pengguna.getKodCawangan();
        peguam.setCawangan(kodc);

        KodJenisPengenalan kodJP = kodJenisPengenalanDAO.findById(kodJenis);
        peguam.setJenisPengenalan(kodJP);
        peguam.setAktif('Y');
        ES.saveOrUpdatePeguam(peguam);

        LOG.info("tambah dalam mohon peguam");

        InfoAudit info = new InfoAudit();
        PermohonanPeguam pp = new PermohonanPeguam();
        info.setTarikhMasuk(new java.util.Date());
        info.setDimasukOleh(pengguna);
        info.setTarikhKemaskini(new java.util.Date());
        info.setDiKemaskiniOleh(pengguna);
        pp.setCawangan(kodc);
        pp.setInfoAudit(info);
        pp.setIdPermohonan(permohonan);
        pp.setPeguam(peguam);
        pp.setAktif("Y");
        ES.saveOrUpdate(pp);

        addSimpleMessage("Maklumat telah berjaya disimpan.");

        return new ForwardResolution("/WEB-INF/jsp/lelong/tambahPeguamBaru.jsp");
    }

    public Resolution senaraiMohonPeguam() {
        LOG.info("-----display----");
        permohonan = permohonanDAO.findById(idPermohonan);
        permohonanPeguam = mohonpeguamDAO.findById(Long.parseLong(idPenyerah));

        if (permohonan != null) {

            idPermohonan = getContext().getRequest().getParameter("idPermohonan");
            idPenyerah = getContext().getRequest().getParameter("idPenyerah");
            getContext().getRequest().setAttribute("idPenyerah", idPenyerah);
            getContext().getRequest().setAttribute("idPermohonan", idPermohonan);
            LOG.info("----id Permohonan--- : " + idPermohonan);
            LOG.info("----id Peguam ---- :" + idPenyerah);

            PermohonanPeguam mohonPeguam = mohonpeguamDAO.findById(Long.valueOf(idPenyerah));
            peguamList = ES.listPeguamAK(permohonan.getIdPermohonan());
            LOG.info("id Peguam :" + mohonPeguam);
            LOG.info("peguamLIsttttt : " + peguamList.size());
        }
        return new ForwardResolution("/WEB-INF/jsp/lelong/displayPeguam.jsp");
    }

    public String getIdPermohonan() {
        return idPermohonan;


    }

    public void setIdPermohonan(String idPermohonan) {
        this.idPermohonan = idPermohonan;


    }

    public Permohonan getPermohonan() {
        return permohonan;


    }

    public void setPermohonan(Permohonan permohonan) {
        this.permohonan = permohonan;


    }

    public List<Permohonan> getSenaraiPermohonan() {
        return senaraiPermohonan;
    }

    public void setSenaraiPermohonan(List<Permohonan> senaraiPermohonan) {
        this.senaraiPermohonan = senaraiPermohonan;
    }

    public boolean isIsPopup() {
        return isPopup;
    }

    public void setIsPopup(boolean isPopup) {
        this.isPopup = isPopup;
    }

    public boolean isForm() {
        return form;
    }

    public void setForm(boolean form) {
        this.form = form;
    }

    public String getKodPenyerah() {
        return kodPenyerah;
    }

    public void setKodPenyerah(String kodPenyerah) {
        this.kodPenyerah = kodPenyerah;
    }

    public List<Penyerah> getSenaraiPenyerah() {
        return senaraiPenyerah;


    }

    public void setSenaraiPenyerah(List<Penyerah> senaraiPenyerah) {
        this.senaraiPenyerah = senaraiPenyerah;


    }

    public String getJenisPenyerah() {
        return jenisPenyerah;


    }

    public void setJenisPenyerah(String jenisPenyerah) {
        this.jenisPenyerah = jenisPenyerah;


    }

    public String getNamaPenyerah() {
        return namaPenyerah;


    }

    public void setNamaPenyerah(String namaPenyerah) {
        this.namaPenyerah = namaPenyerah;


    }

    public String getIdPenyerah() {
        return idPenyerah;


    }

    public void setIdPenyerah(String idPenyerah) {
        this.idPenyerah = idPenyerah;


    }

    public String getPenyerahAlamat1() {
        return penyerahAlamat1;


    }

    public void setPenyerahAlamat1(String penyerahAlamat1) {
        this.penyerahAlamat1 = penyerahAlamat1;


    }

    public String getPenyerahAlamat2() {
        return penyerahAlamat2;


    }

    public void setPenyerahAlamat2(String penyerahAlamat2) {
        this.penyerahAlamat2 = penyerahAlamat2;


    }

    public String getPenyerahAlamat3() {
        return penyerahAlamat3;


    }

    public void setPenyerahAlamat3(String penyerahAlamat3) {
        this.penyerahAlamat3 = penyerahAlamat3;


    }

    public String getPenyerahAlamat4() {
        return penyerahAlamat4;


    }

    public void setPenyerahAlamat4(String penyerahAlamat4) {
        this.penyerahAlamat4 = penyerahAlamat4;


    }

    public String getPenyerahJenisPengenalan() {
        return penyerahJenisPengenalan;


    }

    public void setPenyerahJenisPengenalan(String penyerahJenisPengenalan) {
        this.penyerahJenisPengenalan = penyerahJenisPengenalan;


    }

    public String getPenyerahNama() {
        return penyerahNama;


    }

    public void setPenyerahNama(String penyerahNama) {
        this.penyerahNama = penyerahNama;


    }

    public String getPenyerahNegeri() {
        return penyerahNegeri;


    }

    public void setPenyerahNegeri(String penyerahNegeri) {
        this.penyerahNegeri = penyerahNegeri;


    }

    public String getPenyerahNoPengenalan() {
        return penyerahNoPengenalan;


    }

    public void setPenyerahNoPengenalan(String penyerahNoPengenalan) {
        this.penyerahNoPengenalan = penyerahNoPengenalan;


    }

    public String getPenyerahPoskod() {
        return penyerahPoskod;


    }

    public void setPenyerahPoskod(String penyerahPoskod) {
        this.penyerahPoskod = penyerahPoskod;


    }

    public String getPenyerahEmail() {
        return penyerahEmail;


    }

    public void setPenyerahEmail(String penyerahEmail) {
        this.penyerahEmail = penyerahEmail;


    }

    public String getPenyerahNoTelefon() {
        return penyerahNoTelefon;


    }

    public void setPenyerahNoTelefon(String penyerahNoTelefon) {
        this.penyerahNoTelefon = penyerahNoTelefon;


    }

    public List<PermohonanPeguam> getPeguamList() {
        return peguamList;


    }

    public void setPeguamList(List<PermohonanPeguam> peguamList) {
        this.peguamList = peguamList;

    }

    public String getIdPeguam() {
        return idPeguam;
    }

    public void setIdPeguam(String idPeguam) {
        this.idPeguam = idPeguam;
    }

    public List<Peguam> getSenaraiPeguam() {
        return senaraiPeguam;
    }

    public void setSenaraiPeguam(List<Peguam> senaraiPeguam) {
        this.senaraiPeguam = senaraiPeguam;
    }

    public Peguam getPeguam() {
        return peguam;
    }

    public void setPeguam(Peguam peguam) {
        this.peguam = peguam;
    }

    public String getKodNegeri() {
        return kodNegeri;
    }

    public void setKodNegeri(String kodNegeri) {
        this.kodNegeri = kodNegeri;
    }

    public PermohonanPeguam getPermohonanPeguam() {
        return permohonanPeguam;
    }

    public void setPermohonanPeguam(PermohonanPeguam permohonanPeguam) {
        this.permohonanPeguam = permohonanPeguam;
    }

    public String getKodJenis() {
        return kodJenis;
    }

    public void setKodJenis(String kodJenis) {
        this.kodJenis = kodJenis;
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public List<PermohonanPeguam> getSenaraimohonP() {
        return senaraimohonP;
    }

    public void setSenaraimohonP(List<PermohonanPeguam> senaraimohonP) {
        this.senaraimohonP = senaraimohonP;
    }
}
