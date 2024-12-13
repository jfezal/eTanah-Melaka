/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.strata;

import able.stripes.JSP;
import com.google.inject.Inject;
import able.stripes.AbleActionBean;
import etanah.dao.*;
import etanah.model.*;
import etanah.service.StrataPtService;
import etanah.view.BasicTabActionBean;
import etanah.view.etanahActionBeanContext;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import etanah.model.InfoAudit;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.RedirectResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author faidzal
 */
@UrlBinding("/strata/projek")
public class MaklumatProjekActionBean extends BasicTabActionBean {

    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    KodKegunaanBangunanDAO kodKegunaanBangunanDAO;
    @Inject
    KodKategoriBangunanDAO kodKategoriBangunanDAO;
    @Inject
    KodKegunaanPetakDAO kodKegunaanPetakDAO;
    @Inject
    PermohonanStrataDAO mohonStrataDAO;
    @Inject
    PermohonanSkimDAO permohonanSkimDAO;
    @Inject
    StrataPtService strService;
    @Inject
    CommonService comm;
    @Inject
    private KodTujuanUkurDAO kodTujuanUkurDAO;
    @Inject
    PemohonDAO pemohonDAO;
    @Inject
    private etanah.Configuration conf;
    private PermohonanStrata mohonStrata;
    private Permohonan permohonan;
    private PermohonanSkim mhnSkim;
    private HakmilikPermohonan hp;
    private Dokumen dokumen;
    private char kosRendah;
    protected Pemohon pemohon;
    private List<PermohonanBangunan> pBangunanL;
    private List<PermohonanBangunan> mhnBngn;
    private List<Hakmilik> senaraiHakmilik;
    private List<HakmilikPermohonan> senaraiHakmilikPermohonan;
    private List<Pemohon> senaraiPemohon;
    private List<PermohonanStrata> senaraiPermohonanStrata;
    private String negeri;
    private String noSkim;
    private String diukur_oleh;
    private String pengukur_noic;
    private String no_ruj_ljt;
    private String no_ruj_jubl;
    private String no_fail_ukursemula;
    private String no_fail_ukur;
    private String tarikh_siap;
    private String tarikh_terima_sijil_akuan;
    private String idsblm;
    private String kodKegunaanBangunan;
    private String kodKegunaanPetak;
    private static final Logger LOG = Logger.getLogger(MaklumatProjekActionBean.class);
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
//    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss aaa");

    public PermohonanStrataDAO getMohonStrataDAO() {
        return mohonStrataDAO;
    }

    public void setMohonStrataDAO(PermohonanStrataDAO mohonStrataDAO) {
        this.mohonStrataDAO = mohonStrataDAO;
    }

    //--- assessor
    public char getKosRendah() {
        return kosRendah;
    }

    public void setKosRendah(char kosRendah) {
        this.kosRendah = kosRendah;
    }

    public Pemohon getPemohon() {
        return pemohon;
    }

    public void setPemohon(Pemohon pemohon) {
        this.pemohon = pemohon;
    }

    public PermohonanStrata getMohonStrata() {
        return mohonStrata;
    }

    public void setMohonStrata(PermohonanStrata mohonStrata) {
        this.mohonStrata = mohonStrata;
    }

    public Permohonan getPermohonan() {
        return permohonan;
    }

    public void setPermohonan(Permohonan permohonan) {
        this.permohonan = permohonan;
    }

    public PermohonanDAO getPermohonanDAO() {
        return permohonanDAO;
    }

    public void setPermohonanDAO(PermohonanDAO permohonanDAO) {
        this.permohonanDAO = permohonanDAO;
    }

    public List<PermohonanBangunan> getpBangunanL() {
        return pBangunanL;
    }

    public void setpBangunanL(List<PermohonanBangunan> pBangunanL) {
        this.pBangunanL = pBangunanL;
    }

    public String getNegeri() {
        return negeri;
    }

    public void setNegeri(String negeri) {
        this.negeri = negeri;
    }

    public Dokumen getDokumen() {
        return dokumen;
    }

    public void setDokumen(Dokumen dokumen) {
        this.dokumen = dokumen;
    }

    public List<PermohonanBangunan> getMhnBngn() {
        return mhnBngn;
    }

    public void setMhnBngn(List<PermohonanBangunan> mhnBngn) {
        this.mhnBngn = mhnBngn;
    }

    public HakmilikPermohonan getHp() {
        return hp;
    }

    public void setHp(HakmilikPermohonan hp) {
        this.hp = hp;
    }

    public PermohonanSkim getMhnSkim() {
        return mhnSkim;
    }

    public void setMhnSkim(PermohonanSkim mhnSkim) {
        this.mhnSkim = mhnSkim;
    }

    public String getTarikh_siap() {
        return tarikh_siap;
    }

    public void setTarikh_siap(String tarikh_siap) {
        this.tarikh_siap = tarikh_siap;
    }

    public String getTarikh_terima_sijil_akuan() {
        return tarikh_terima_sijil_akuan;
    }

    public void setTarikh_terima_sijil_akuan(String tarikh_terima_sijil_akuan) {
        this.tarikh_terima_sijil_akuan = tarikh_terima_sijil_akuan;
    }

    public List<Hakmilik> getSenaraiHakmilik() {
        return senaraiHakmilik;
    }

    public void setSenaraiHakmilik(List<Hakmilik> senaraiHakmilik) {
        this.senaraiHakmilik = senaraiHakmilik;
    }

    public List<HakmilikPermohonan> getSenaraiHakmilikPermohonan() {
        return senaraiHakmilikPermohonan;
    }

    public void setSenaraiHakmilikPermohonan(List<HakmilikPermohonan> senaraiHakmilikPermohonan) {
        this.senaraiHakmilikPermohonan = senaraiHakmilikPermohonan;
    }

    public List<Pemohon> getSenaraiPemohon() {
        return senaraiPemohon;
    }

    public void setSenaraiPemohon(List<Pemohon> senaraiPemohon) {
        this.senaraiPemohon = senaraiPemohon;
    }

    public String getIdsblm() {
        return idsblm;
    }

    public void setIdsblm(String idsblm) {
        this.idsblm = idsblm;
    }

    public String getDiukur_oleh() {
        return diukur_oleh;
    }

    public void setDiukur_oleh(String diukur_oleh) {
        this.diukur_oleh = diukur_oleh;
    }

    public String getNoSkim() {
        return noSkim;
    }

    public void setNoSkim(String noSkim) {
        this.noSkim = noSkim;
    }

    public String getNo_fail_ukur() {
        return no_fail_ukur;
    }

    public void setNo_fail_ukur(String no_fail_ukur) {
        this.no_fail_ukur = no_fail_ukur;
    }

    public String getNo_fail_ukursemula() {
        return no_fail_ukursemula;
    }

    public void setNo_fail_ukursemula(String no_fail_ukursemula) {
        this.no_fail_ukursemula = no_fail_ukursemula;
    }

    public String getNo_ruj_jubl() {
        return no_ruj_jubl;
    }

    public void setNo_ruj_jubl(String no_ruj_jubl) {
        this.no_ruj_jubl = no_ruj_jubl;
    }

    public String getNo_ruj_ljt() {
        return no_ruj_ljt;
    }

    public void setNo_ruj_ljt(String no_ruj_ljt) {
        this.no_ruj_ljt = no_ruj_ljt;
    }

    public String getPengukur_noic() {
        return pengukur_noic;
    }

    public void setPengukur_noic(String pengukur_noic) {
        this.pengukur_noic = pengukur_noic;
    }

    public String getKodKegunaanBangunan() {
        return kodKegunaanBangunan;
    }

    public void setKodKegunaanBangunan(String kodKegunaanBangunan) {
        this.kodKegunaanBangunan = kodKegunaanBangunan;
    }

    public String getKodKegunaanPetak() {
        return kodKegunaanPetak;
    }

    public void setKodKegunaanPetak(String kodKegunaanPetak) {
        this.kodKegunaanPetak = kodKegunaanPetak;
    }

//--- end assessor
    @DefaultHandler
    public Resolution showForm() {

        String msg = getContext().getRequest().getParameter("message");
        String error = getContext().getRequest().getParameter("error");
        if (StringUtils.isNotBlank(msg)) {
            addSimpleMessage(msg);
        }

        if (StringUtils.isNotBlank(error)) {
            addSimpleError(error);
        }
//        if (pemohon == null) {
//            error = "Sila isikan maklumat pemohon terlebih dahulu";
//            addSimpleError(error);
//            return new RedirectResolution(MaklumatPemohonanActionBean.class, "showForm").addParameter("error", error).addParameter("message", msg);
//
//        } else {
        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        return new JSP("strata/pbbm/maklumat_projek.jsp").addParameter("tab", "true");
//        }
    }

//    public Resolution showForm2() {
//        String msg = getContext().getRequest().getParameter("message");
//        String error = getContext().getRequest().getParameter("error");
//        if (StringUtils.isNotBlank(msg)) {
//            addSimpleMessage(msg);
//        }
//
//        if (StringUtils.isNotBlank(error)) {
//            addSimpleError(error);
//        }
//        getContext().getRequest().setAttribute("disable", Boolean.TRUE);
//        return new JSP("strata/pbbm/maklumat_projek.jsp").addParameter("tab", "true");
//    }
    public Resolution showForm2() {
        String msg = getContext().getRequest().getParameter("message");
        String error = getContext().getRequest().getParameter("error");
//        if (!((permohonan.getKodUrusan().getKod().equals("PHPP") || permohonan.getKodUrusan().getKod().equals("PHPC")) && conf.getProperty("kodNegeri").equals("04"))) {
        if (StringUtils.isNotBlank(msg)) {
            addSimpleMessage(msg);
        }

        if (StringUtils.isNotBlank(error)) {
            addSimpleError(error);
        }
        getContext().getRequest().setAttribute("disable", Boolean.TRUE);
//        }
        return new JSP("strata/pbbm/maklumat_projek.jsp").addParameter("tab", "true");
    }

    public Resolution test() {
        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);

        InfoAudit infoAudit = new InfoAudit();
        infoAudit.setDimasukOleh(peng);
        infoAudit.setTarikhMasuk(new java.util.Date());

        comm.generateHakmilikStrata(infoAudit, permohonan, peng);

        return new JSP("strata/pbbm/maklumat_projek.jsp").addParameter("tab", "true");
    }

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        negeri = conf.getProperty("kodNegeri");

        if (idPermohonan != null) {
            permohonan = permohonanDAO.findById(idPermohonan);
        }
        if (permohonan.getPermohonanSebelum() != null) {
            permohonan = permohonan.getPermohonanSebelum();
        }
        dokumen = strService.findDok(idPermohonan, "JPP");
        mhnBngn = strService.checkMhnBangunanExist(permohonan.getIdPermohonan());
        hp = strService.findMohonHakmilik(idPermohonan);
        mhnSkim = strService.findIDSkimByIDMH(hp.getId());

        if (mhnSkim != null) {
            noSkim = mhnSkim.getNoSkim();
            diukur_oleh = mhnSkim.getDiukur();
            pengukur_noic = mhnSkim.getNoKpPengukur();
            no_ruj_ljt = mhnSkim.getNoFailUkur();
            no_ruj_jubl = mhnSkim.getNoRujJubl();
            no_fail_ukursemula = mhnSkim.getNoFailUkurSemula();
            no_fail_ukur = mhnSkim.getNoFailPt();
            if (mhnSkim.getTrhSiap() != null) {
                tarikh_siap = sdf.format(mhnSkim.getTrhSiap());
            }
            if (mhnSkim.getTrhLulusCf() != null) {
                tarikh_terima_sijil_akuan = sdf.format(mhnSkim.getTrhLulusCf());
            }

        }

        String name[] = {"permohonan"};
        Object obj[] = {permohonan};
//        List listPemohon = pemohonDAO.findByEqualCriterias(name, obj, null);
        List<Pemohon> listPemohon = strService.findByPermohonan(idPermohonan);
        if (listPemohon.size() > 0) {
            pemohon = (Pemohon) listPemohon.get(0);
        }
        if (!((permohonan.getKodUrusan().getKod().equals("PHPP") || permohonan.getKodUrusan().getKod().equals("PHPC")) && conf.getProperty("kodNegeri").equals("04"))) {
            mohonStrata = strService.findID(permohonan.getIdPermohonan());
        }
        List<Projek> sifus = strService.findSifus(permohonan.getSenaraiHakmilik().get(0).getHakmilik().getIdHakmilik(), null, "Y");
        for (Projek sfus : sifus) {
            if (mohonStrata == null) {
                mohonStrata = new PermohonanStrata();
                InfoAudit infoAudit = new InfoAudit();
                infoAudit.setDimasukOleh(peng);
                infoAudit.setTarikhMasuk(new java.util.Date());
                mohonStrata.setNoBuku(sfus.getMaksimumUnit());
                mohonStrata.setTarikhSiap(sfus.getTarikhLulus());
                mohonStrata.setInfoAudit(infoAudit);
                mohonStrata.setPermohonan(permohonan);
                mohonStrata.setNama("");
                mohonStrata.setCawangan(permohonan.getCawangan());
                mohonStrata.setPemilikNama("");
                strService.simpanPemilik(mohonStrata);
            }
        }

        if (mohonStrata != null) {
            kosRendah = mohonStrata.getKosRendah();
        }

        if (mhnSkim != null) {
            if (mhnSkim.getTrhSiap() != null) {
//            tarikh_siap = mhnSkim.getTrhSiap().toString();
                tarikh_siap = sdf.format(mhnSkim.getTrhSiap());
            }
            if (mhnSkim.getTrhLulusCf() != null) {
                tarikh_terima_sijil_akuan = sdf.format(mhnSkim.getTrhLulusCf());
            }
        }

        senaraiHakmilikPermohonan = strService.findIdHakmilikSortAsc(idPermohonan);
        if ((permohonan.getKodUrusan().getKod().equals("PHPP") || permohonan.getKodUrusan().getKod().equals("PHPC")) && conf.getProperty("kodNegeri").equals("04")) {
            senaraiPermohonanStrata = strService.findIDMS(idPermohonan);
            if (senaraiPermohonanStrata.isEmpty()) {
                if (!senaraiHakmilikPermohonan.isEmpty()) {
                    String hm = senaraiHakmilikPermohonan.get(0).getHakmilik().getIdHakmilik();
                    List<HakmilikPermohonan> hp = strService.findIdPBBSByIdHakmilik(hm);
                    if (!hp.isEmpty()) {
                        for (HakmilikPermohonan hp2 : hp) {
                            String idmohon = hp2.getPermohonan().getIdPermohonan();
                            Permohonan mohon = strService.findPermohonanSblm(idmohon);
                            if (mohon.getKodUrusan().getKod().equals("PBBS") || mohon.getKodUrusan().getKod().equals("PBBD")) {
                                idsblm = mohon.getIdPermohonan();
                                PermohonanStrata mhnStr = strService.findID(idsblm);
                                HakmilikPermohonan hp3 = strService.findidMhcore(idsblm, senaraiHakmilikPermohonan.get(0).getHakmilik().getIdHakmilikInduk());

                                if (mhnStr != null) {
                                    mohonStrata = strService.findID(idsblm);
                                    kosRendah = mohonStrata.getKosRendah();
                                }

                                if (hp3 != null) {
                                    PermohonanSkim mSkim = strService.findIDSkimByIDMH(hp3.getId());
                                    if (mSkim != null) {
                                        noSkim = mSkim.getNoSkim();
                                        diukur_oleh = mSkim.getDiukur();
                                        pengukur_noic = mSkim.getNoKpPengukur();
                                        no_ruj_ljt = mSkim.getNoFailUkur();
                                        no_ruj_jubl = mSkim.getNoRujJubl();
                                        no_fail_ukursemula = mSkim.getNoFailUkurSemula();
                                        no_fail_ukur = mSkim.getNoFailPt();
                                        if (mSkim.getTrhSiap() != null) {
                                            tarikh_siap = sdf.format(mSkim.getTrhSiap());
                                        }
                                        if (mSkim.getTrhLulusCf() != null) {
                                            tarikh_terima_sijil_akuan = sdf.format(mSkim.getTrhLulusCf());
                                        }

                                    }
                                }
                            }
                        }
                    }
                } else {
                    addSimpleError("Sila pastikan maklumat hakmilik petak adalah betul.");
                }
            } else {
                mohonStrata = strService.findID(idPermohonan);
                kosRendah = senaraiPermohonanStrata.get(0).getKosRendah();

                PermohonanSkim mSkim2 = strService.findIDSkimByIDMH(permohonan.getSenaraiHakmilik().get(0).getId());
                if (mSkim2 != null) {
                    noSkim = mSkim2.getNoSkim();
                    diukur_oleh = mSkim2.getDiukur();
                    pengukur_noic = mSkim2.getNoKpPengukur();
                    no_ruj_ljt = mSkim2.getNoFailUkur();
                    no_ruj_jubl = mSkim2.getNoRujJubl();
                    no_fail_ukursemula = mSkim2.getNoFailUkurSemula();
                    no_fail_ukur = mSkim2.getNoFailPt();
                    if (mSkim2.getTrhSiap() != null) {
                        tarikh_siap = sdf.format(mSkim2.getTrhSiap());
                    }
                    if (mSkim2.getTrhLulusCf() != null) {
                        tarikh_terima_sijil_akuan = sdf.format(mSkim2.getTrhLulusCf());
                    }
                    if (permohonan.getKodUrusan().getKod().equals("PHPP") || permohonan.getKodUrusan().getKod().equals("PHPC")){
                        PermohonanBangunan ab = strService.findALLID(idPermohonan);
                        List<BangunanTingkat> ab2 = strService.findByIDBangunan3(ab.getIdBangunan());
                        List<BangunanPetak> ab3 = strService.findByIDPetak2(ab2.get(0).getIdTingkat());
                        kodKegunaanBangunan = ab.getKodKegunaanBangunan().getKod();
                        kodKegunaanPetak = ab3.get(0).getKegunaan().getKod();
                    } else {
                        PermohonanBangunan ab = strService.findALLID(idPermohonan);
                        BangunanTingkat ab2 = strService.findByIDBangunan2(ab.getIdBangunan());
                        List<BangunanPetak> ab3 = strService.findByIDPetak2(ab2.getIdTingkat());
                        kodKegunaanBangunan = ab.getKodKegunaanBangunan().getKod();
                        kodKegunaanPetak = ab3.get(0).getKegunaan().getKod(); 
                    }
                    

                }
            }
            getContext().getRequest().setAttribute("manual", Boolean.TRUE);
        }
    }

    public Resolution simpan() {
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        String error = "";
        String msg = "";
        //pBangunanL = strService.findByIDPermohonan(idPermohonan);
        pBangunanL = strService.getSenaraiMohonBangunan(idPermohonan);
        Dokumen d = strService.findDok(idPermohonan, "JPP");
        if (d != null) {
            if (d.getNamaFizikal() != null) {
                if (pBangunanL.size() > 0) {
                    if (mohonStrata.getNama() == null) {
                        error = "Sila isikan maklumat yang mandatori";
                        return new RedirectResolution(MaklumatProjekActionBean.class, "showForm").addParameter("error", error).addParameter("message", msg);
                    } else {
                        LOG.info("------pemohon------" + pemohon);
                        if (pemohon != null) {
                            mohonStrata.setPemilikNama(pemohon.getPihak().getNama());
                            mohonStrata.setPemilikAlamat1(pemohon.getPihak().getAlamat1());
                            mohonStrata.setPemilikAlamat2(pemohon.getPihak().getAlamat2());
                            mohonStrata.setPemilikAlamat3(pemohon.getPihak().getAlamat3());
                            mohonStrata.setPemilikAlamat4(pemohon.getPihak().getAlamat4());
                            mohonStrata.setCawangan(peng.getKodCawangan());
                            mohonStrata.setPermohonan(permohonan);
                            mohonStrata.setInfoAudit(strService.getInfo(peng));
                            mohonStrata.setNegeri(pemohon.getPihak().getNegeri());
                            mohonStrata.setKosRendah(kosRendah);
                            strService.simpanPemilik(mohonStrata);
                            
                            //PermohonanBangunan permohonanBangunan = strService.findALLID(idPermohonan);                            
                            //PermohonanSkim idSkim = strService.findIDSkimByIDMH(permohonan.getSenaraiHakmilik().get(0).getId());
                            //permohonanBangunan.setPermohonanSkim(permohonanSkimDAO.findById(idSkim.getIdSkim()));
                            //LOG.info("------ID SKIM------" + permohonanBangunan);
                            //strService.simpanIdSkim(permohonanBangunan);
                            
                            List<PermohonanBangunan> senaraimb = strService.findALLIDBngn(idPermohonan);
                            if (!senaraimb.isEmpty()) {
                                for (PermohonanBangunan mb : senaraimb) {
                                    PermohonanSkim idSkim = strService.findIDSkimByIDMH(permohonan.getSenaraiHakmilik().get(0).getId());
                                    mb.setPermohonanSkim(permohonanSkimDAO.findById(idSkim.getIdSkim()));
                                    LOG.info("------ID SKIM------" + mb);
                                    strService.simpanIdSkim(mb);
                                }
                            }
                            
                            msg = "Maklumat telah berjaya disimpan";
                        } else {
                            mohonStrata.setKosRendah(kosRendah);
                            strService.simpanPemilik(mohonStrata);
                            msg = "Maklumat telah berjaya disimpan.";
                        }
                        return new RedirectResolution(MaklumatProjekActionBean.class, "showForm").addParameter("error", error).addParameter("message", msg);
                    }
                } else {
                    error = "Sila klik tab 'Dokumen' untuk memuatnaik fail XML Jadual Petak (JPP).";
                    return new RedirectResolution(MaklumatProjekActionBean.class, "showForm").addParameter("error", error).addParameter("message", msg);
                }
            } else if (d.getNamaFizikal() == null) {
                error = "Sila klik tab 'Dokumen' untuk memuatnaik Fail xml Jadual Petak (JPP).";
                return new RedirectResolution(MaklumatProjekActionBean.class, "showForm").addParameter("error", error).addParameter("message", msg);
            }
        } else {
            error = "Sila klik tab 'Dokumen' untuk memuatnaik FAIL XML Jadual Petak (JPP).";
            return new RedirectResolution(MaklumatProjekActionBean.class, "showForm").addParameter("error", error).addParameter("message", msg);
        }
        return new RedirectResolution(MaklumatProjekActionBean.class, "showForm").addParameter("error", error).addParameter("message", msg);
    }

    public Resolution simpan2() throws ParseException {

        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        noSkim = (String) getContext().getRequest().getParameter("noSkim");
        diukur_oleh = (String) getContext().getRequest().getParameter("diukur_oleh");
        pengukur_noic = (String) getContext().getRequest().getParameter("pengukur_noic");
        no_ruj_ljt = (String) getContext().getRequest().getParameter("no_ruj_ljt");
        no_ruj_jubl = (String) getContext().getRequest().getParameter("no_ruj_jubl");
        no_fail_ukursemula = (String) getContext().getRequest().getParameter("no_fail_ukursemula");
        no_fail_ukur = (String) getContext().getRequest().getParameter("no_fail_ukur");
        String mohonStr = (String) getContext().getRequest().getParameter("mohonStrata.nama");
        kodKegunaanBangunan = (String) getContext().getRequest().getParameter("kodKegunaanBangunan");
        kodKegunaanPetak = (String) getContext().getRequest().getParameter("kodKegunaanPetak");
//        String tarikh_siap = (String) getContext().getRequest().getParameter("tarikh_siap");
        tarikh_siap = getContext().getRequest().getParameter("tarikh_siap");
        tarikh_terima_sijil_akuan = (String) getContext().getRequest().getParameter("tarikh_terima_sijil_akuan");
        idsblm = (String) getContext().getRequest().getParameter("idsblm");
        String noBuku = (String) getContext().getRequest().getParameter("mohonStrata.noBuku");
        String tarikhSiap = (String) getContext().getRequest().getParameter("mohonStrata.tarikhSiap");
        String cfNoSijil = (String) getContext().getRequest().getParameter("mohonStrata.cfNoSijil");
        String cfTarikhKeluar = (String) getContext().getRequest().getParameter("mohonStrata.cfTarikhKeluar");
        String mcNoSijil = (String) getContext().getRequest().getParameter("mohonStrata.mcNoSijil");
        String mcTrhSijil = (String) getContext().getRequest().getParameter("mohonStrata.mcTrhSijil");
        String mcNoRuj = (String) getContext().getRequest().getParameter("mohonStrata.mcNoRuj");
        String mcTrhRuj = (String) getContext().getRequest().getParameter("mohonStrata.mcTrhRuj");
        LOG.info("id sebelum--" + idsblm);
        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        String error = "";
        String msg = "";
        LOG.info("noSkim--- " + noSkim);
        LOG.info("diukur_oleh--- " + diukur_oleh);
        LOG.info("tarikh_siap--- " + tarikh_siap);
        //pBangunanL = strService.findByIDPermohonan(idPermohonan);
        pBangunanL = strService.getSenaraiMohonBangunan(idPermohonan);
        Permohonan mohon = strService.findPermohonanSblm(idPermohonan);
        Dokumen d = strService.findDok(idPermohonan, "JPP");
        mhnBngn = strService.checkMhnBangunanExist(idPermohonan);
        hp = strService.findMohonHakmilik(idPermohonan);
        mhnSkim = strService.findIDSkimByIDMH(hp.getId());
        PermohonanStrata mhnStrata = strService.findID(idPermohonan);

        InfoAudit infoAudit = new InfoAudit();
        infoAudit.setDimasukOleh(peng);
        infoAudit.setTarikhMasuk(new java.util.Date());
        InfoAudit infoAudit1 = new InfoAudit();
        infoAudit1.setDiKemaskiniOleh(peng);
        infoAudit1.setTarikhKemaskini(new java.util.Date());

        senaraiHakmilikPermohonan = strService.findIdHakmilikSortAsc(idPermohonan);

        if ((mohon.getKodUrusan().getKod().equals("PHPP") || mohon.getKodUrusan().getKod().equals("PHPC")) && conf.getProperty("kodNegeri").equals("04")) {
            senaraiPemohon = strService.findByPermohonan(idPermohonan);
            if (mohonStrata == null) {
                error = "Sila isikan maklumat yang mandatori";
                return new RedirectResolution(MaklumatProjekActionBean.class, "showForm").addParameter("error", error).addParameter("message", msg);
            } else {
                senaraiPermohonanStrata = strService.findIDMS(idPermohonan);
                PermohonanSkim senaraiPermohonanSkim = strService.findIDSkimByIDMH(senaraiHakmilikPermohonan.get(0).getId());
                if (senaraiPermohonanStrata.isEmpty()) {
                    PermohonanStrata mhnStr = new PermohonanStrata();
                    mhnStr.setNama(mohonStr);
                    mhnStr.setNamaSkim(noSkim);
                    mhnStr.setPemilikNama(senaraiPemohon.get(0).getPihak().getNama());
                    mhnStr.setPemilikAlamat1(senaraiPemohon.get(0).getPihak().getAlamat1());
                    mhnStr.setPemilikAlamat2(senaraiPemohon.get(0).getPihak().getAlamat2());
                    mhnStr.setPemilikAlamat3(senaraiPemohon.get(0).getPihak().getAlamat3());
                    mhnStr.setPemilikAlamat4(senaraiPemohon.get(0).getPihak().getAlamat4());
                    mhnStr.setCawangan(peng.getKodCawangan());
                    mhnStr.setPermohonan(permohonan);
                    mhnStr.setInfoAudit(strService.getInfo(peng));
                    mhnStr.setNegeri(senaraiPemohon.get(0).getPihak().getNegeri());
                    mhnStr.setKosRendah(kosRendah);
                    mhnStr.setInfoAudit(infoAudit);

                    mhnStr.setNoBuku(noBuku);
                    if (tarikhSiap != null) {
                        mhnStr.setTarikhSiap(sdf.parse(tarikhSiap));
                    }
                    //mhnStr.setTarikhSiap(sdf.parse(tarikhSiap));
                    mhnStr.setCfNoSijil(cfNoSijil);
                    if (cfTarikhKeluar != null) {
                        mhnStr.setCfTarikhKeluar(sdf.parse(cfTarikhKeluar));
                    }
                    //mhnStr.setCfTarikhKeluar(sdf.parse(cfTarikhKeluar));
                    mhnStr.setMcNoSijil(mcNoSijil);
                    if (mcTrhSijil != null) {
                        mhnStr.setMcTrhSijil(sdf.parse(mcTrhSijil));
                    }
                    //mhnStr.setMcTrhSijil(sdf.parse(mcTrhSijil));
                    mhnStr.setMcNoRuj(mcNoRuj);
                    if (mcTrhRuj != null) {
                        mhnStr.setMcTrhRuj(sdf.parse(mcTrhRuj));
                    }
                    //mhnStr.setMcTrhRuj(sdf.parse(mcTrhRuj));
                    strService.simpanPemilik(mhnStr);
                    msg = "Maklumat telah berjaya disimpan.";
//                    for (Pemohon p : senaraiPemohon) {
//                        PermohonanStrata mhnStr = new PermohonanStrata();
//                        mhnStr.setNama(mohonStr);
//                        mhnStr.setPemilikNama(p.getPihak().getNama());
//                        mhnStr.setPemilikAlamat1(p.getPihak().getAlamat1());
//                        mhnStr.setPemilikAlamat2(p.getPihak().getAlamat2());
//                        mhnStr.setPemilikAlamat3(p.getPihak().getAlamat3());
//                        mhnStr.setPemilikAlamat4(p.getPihak().getAlamat4());
//                        mhnStr.setCawangan(peng.getKodCawangan());
//                        mhnStr.setPermohonan(permohonan);
//                        mhnStr.setInfoAudit(strService.getInfo(peng));
//                        mhnStr.setNegeri(p.getPihak().getNegeri());
//                        mhnStr.setKosRendah(kosRendah);
//                        strService.simpanPemilik(mhnStr);
//                        msg = "Maklumat telah berjaya disimpan.";
//                    }
                } else {
                    for (PermohonanStrata pemhnStr : senaraiPermohonanStrata) {
                        pemhnStr.setKosRendah(kosRendah);
                        pemhnStr.setNoBuku(noBuku);
                        pemhnStr.setTarikhSiap(sdf.parse(tarikhSiap));
                        pemhnStr.setCfNoSijil(cfNoSijil);
                        pemhnStr.setCfTarikhKeluar(sdf.parse(cfTarikhKeluar));
                        pemhnStr.setMcNoSijil(mcNoSijil);
                        pemhnStr.setMcTrhSijil(sdf.parse(mcTrhSijil));
                        pemhnStr.setMcNoRuj(mcNoRuj);
                        pemhnStr.setMcTrhRuj(sdf.parse(mcTrhRuj));
                        pemhnStr.setInfoAudit(infoAudit1);
                        strService.simpanPemilik(pemhnStr);
                        msg = "Maklumat telah berjaya disimpan.";
                    }
//                    if (!senaraiHakmilikPermohonan.isEmpty()) {
//                        String hm = senaraiHakmilikPermohonan.get(0).getHakmilik().getIdHakmilik();
//                        List<HakmilikPermohonan> hp = strService.findIdPBBSByIdHakmilik(hm);
//                        if (!hp.isEmpty()) {
//                            for (HakmilikPermohonan hp2 : hp) {
//                                String idmohon = hp2.getPermohonan().getIdPermohonan();
//                                Permohonan mohon2 = strService.findPermohonanSblm(idmohon);
//                                if (mohon.getKodUrusan().getKod().equals("PBBS") || mohon.getKodUrusan().getKod().equals("PBBD")) {
//                                    idsblm = mohon.getIdPermohonan();
//                                    PermohonanStrata mhnStr = strService.findID(idsblm);
//                                    if (mhnStr != null) {
//                                        mohonStrata = strService.findID(idsblm);
//                                        kosRendah = mohonStrata.getKosRendah();
//                                    }
//                                }
//                            }
//                        }
//                    }

                }
                if (senaraiPermohonanSkim == null) {
                    PermohonanSkim mhnSkim = new PermohonanSkim();
                    mhnSkim.setCawangan(permohonan.getCawangan());
                    mhnSkim.setHakmilikPermohonan(senaraiHakmilikPermohonan.get(0));
                    mhnSkim.setNoSkim(noSkim);
                    mhnSkim.setDiukur(diukur_oleh);
                    mhnSkim.setNoKpPengukur(pengukur_noic);
                    mhnSkim.setNoFailUkur(no_ruj_ljt);
                    mhnSkim.setNoRujJubl(no_ruj_jubl);
                    mhnSkim.setNoFailUkurSemula(no_fail_ukursemula);
                    mhnSkim.setNoFailPt(no_fail_ukur);
                    if (tarikh_siap != null) {
                        mhnSkim.setTrhSiap(sdf.parse(tarikh_siap));
                        mhnSkim.setTrhLulusCf(sdf.parse(tarikh_terima_sijil_akuan));
                    }
                    if (mohon.getKodUrusan().getKod().equals("PHPP")) {
                        mhnSkim.setKodTujuanUkur(kodTujuanUkurDAO.findById("17"));//
                    }
                    if (mohon.getKodUrusan().getKod().equals("PHPC")) {
                        mhnSkim.setKodTujuanUkur(kodTujuanUkurDAO.findById("39"));//
                    }
                    mhnSkim.setInfoAudit(infoAudit);
                    strService.saveSkim(mhnSkim);

                    List<PermohonanBangunan> senaraimb = strService.findALLIDBngn(idPermohonan);
                    for (PermohonanBangunan mb : senaraimb) {
                        mb.setPermohonanSkim(mhnSkim);
                        strService.save(mb);
                    }

                } else {
                    senaraiPermohonanSkim.setNoSkim(noSkim);
                    senaraiPermohonanSkim.setDiukur(diukur_oleh);
                    senaraiPermohonanSkim.setNoKpPengukur(pengukur_noic);
                    senaraiPermohonanSkim.setNoFailUkur(no_ruj_ljt);
                    senaraiPermohonanSkim.setNoRujJubl(no_ruj_jubl);
                    senaraiPermohonanSkim.setNoFailUkurSemula(no_fail_ukursemula);
                    senaraiPermohonanSkim.setNoFailPt(no_fail_ukur);
                    if (tarikh_siap != null) {
                        senaraiPermohonanSkim.setTrhSiap(sdf.parse(tarikh_siap));
                        senaraiPermohonanSkim.setTrhLulusCf(sdf.parse(tarikh_terima_sijil_akuan));
                    }
                    senaraiPermohonanSkim.setInfoAudit(infoAudit1);
                    strService.saveSkim(senaraiPermohonanSkim);
                }

                if (!senaraiHakmilikPermohonan.isEmpty()) {
                    PermohonanSkim mohonSkim = strService.findIDSkimByIDMH(senaraiHakmilikPermohonan.get(0).getId());
                    PermohonanBangunan ab = strService.findALLID(idPermohonan);
//                    PermohonanBangunan sblm = strService.findALLID(idsblm);
//                    BangunanTingkat btsblm = strService.findbyBngnTgkt(sblm.getIdBangunan());
//                    BangunanPetak bpsblm = strService.findPetakByIdBngnIdTinketNama2(btsblm.getTingkat());
                    if (ab == null) {
                        PermohonanBangunan mhnBngn = new PermohonanBangunan();
                        mhnBngn.setCawangan(permohonan.getCawangan());
                        mhnBngn.setPermohonan(permohonan);
                        mhnBngn.setNama("M1");
                        mhnBngn.setNamaLain("manual");
                        mhnBngn.setKodKategoriBangunan(kodKategoriBangunanDAO.findById("B"));
                        mhnBngn.setInfoAudit(infoAudit);
                        mhnBngn.setPermohonanSkim(mohonSkim);
                        int hk = 0;
                        for (HakmilikPermohonan hp1 : senaraiHakmilikPermohonan) {
                            hk = hk + hp1.getHakmilik().getUnitSyer().intValue();
                            mhnBngn.setSyerBlok(hk);
                        }
                        mhnBngn.setBilanganTingkat(1);
                        if (kodKegunaanBangunan != null) {
                            mhnBngn.setKodKegunaanBangunan(kodKegunaanBangunanDAO.findById(kodKegunaanBangunan));
                        }
                        mhnBngn.setPermohonanSkim(mohonSkim);
                        if (permohonan.getKodUrusan().getKod().equals("PHPP")) {
                            mhnBngn.setBilanganPetak(1);
                        } else {
                            if (permohonan.getBilanganPermohonan() <= 2) {
                                mhnBngn.setBilanganPetak(2);
                            } else {
                                mhnBngn.setBilanganPetak(permohonan.getBilanganPermohonan());
                            }

                        }
                        strService.save(mhnBngn);

                        BangunanTingkat bngnTgt = new BangunanTingkat();
                        bngnTgt.setBangunan(mhnBngn);
                        bngnTgt.setInfoAudit(infoAudit);
                        bngnTgt.setNama("1");
                        bngnTgt.setTingkat(1);
                        if (permohonan.getKodUrusan().getKod().equals("PHPP")) {
                            bngnTgt.setBilanganPetak(1);
                            bngnTgt.setIdTingkat(1);
                        } else {
                            bngnTgt.setBilanganPetak(1);
                            bngnTgt.setIdTingkat(1);
                        }
                        strService.save(bngnTgt);

                        if (permohonan.getKodUrusan().getKod().equals("PHPP")) {
                            BangunanPetak bngnPetak = new BangunanPetak();
                            bngnPetak.setIdPetak(1);
                            bngnPetak.setInfoAudit(infoAudit);
                            bngnPetak.setTingkat(bngnTgt);
                            if (kodKegunaanPetak != null) {
                                bngnPetak.setKegunaan(kodKegunaanPetakDAO.findById(kodKegunaanPetak));
                            }
                            bngnPetak.setNama("1");
                            int luas = 0;
                            int syer = 0;
                            for (HakmilikPermohonan hp4 : senaraiHakmilikPermohonan) {
                                luas = luas + hp4.getLuasTerlibat().intValue();
                                bngnPetak.setLuas(BigDecimal.valueOf(luas));
                                syer = syer + hp4.getHakmilik().getUnitSyer().intValue();
                                bngnPetak.setSyer(syer);
                            }
                            strService.save(bngnPetak);

                        } else {
                            for (int i = 1; i <= permohonan.getBilanganPermohonan(); i++) {
                                String x = Integer.toString(i);
                                BangunanPetak bngnPetak = new BangunanPetak();
                                bngnPetak.setIdPetak(i);
                                bngnPetak.setInfoAudit(infoAudit);
                                bngnPetak.setTingkat(bngnTgt);
                                bngnPetak.setNama(x);
                                if (kodKegunaanPetak != null) {
                                    bngnPetak.setKegunaan(kodKegunaanPetakDAO.findById(kodKegunaanPetak));
                                }

                                BigDecimal luas = BigDecimal.ZERO;
                                BigDecimal syer = BigDecimal.ZERO;

                                for (HakmilikPermohonan hp4 : senaraiHakmilikPermohonan) {
                                    luas = luas.add(hp4.getLuasTerlibat());
                                    syer = syer.add(hp4.getHakmilik().getUnitSyer());
                                }

                                int pemilik = permohonan.getBilanganPermohonan();
                                if (permohonan.getBilanganPermohonan() <= 2) {
                                    luas = (luas.divide(BigDecimal.valueOf(2)));
                                    syer = (syer.divide(BigDecimal.valueOf(2)));
                                } else {
                                    luas = (luas.divide(BigDecimal.valueOf(pemilik)));
                                    syer = (syer.divide(BigDecimal.valueOf(pemilik)));
                                }
                                LOG.info(luas);
                                LOG.info(syer);
                                luas = luas.setScale(1, BigDecimal.ROUND_UP);
                                syer = syer.setScale(1, BigDecimal.ROUND_UP);
                                bngnPetak.setLuas(luas);
                                bngnPetak.setSyer(syer.intValue());
                                strService.save(bngnPetak);
                            }
                        }

                    }
                }

            }
        } else {
            if (mohonStrata.getNama() == null) {
                error = "Sila isikan maklumat yang mandatori";
                return new RedirectResolution(MaklumatProjekActionBean.class, "showForm").addParameter("error", error).addParameter("message", msg);
            } else {
                LOG.info("------pemohon------" + pemohon);
                if (!mhnBngn.isEmpty()) {
                    if (pemohon != null) {
                        mohonStrata.setPemilikNama(pemohon.getPihak().getNama());
                        mohonStrata.setPemilikAlamat1(pemohon.getPihak().getAlamat1());
                        mohonStrata.setPemilikAlamat2(pemohon.getPihak().getAlamat2());
                        mohonStrata.setPemilikAlamat3(pemohon.getPihak().getAlamat3());
                        mohonStrata.setPemilikAlamat4(pemohon.getPihak().getAlamat4());
                        mohonStrata.setCawangan(peng.getKodCawangan());
                        mohonStrata.setPermohonan(permohonan);
                        mohonStrata.setInfoAudit(strService.getInfo(peng));
                        mohonStrata.setNegeri(pemohon.getPihak().getNegeri());
                        mohonStrata.setKosRendah(kosRendah);
                        mohonStrata.setNamaSkim(noSkim);
                        mohonStrata.setNoBuku(noBuku);
                        mohonStrata.setTarikhSiap(sdf.parse(tarikhSiap));
                        mohonStrata.setCfNoSijil(cfNoSijil);
                        mohonStrata.setCfTarikhKeluar(sdf.parse(cfTarikhKeluar));
                        mohonStrata.setMcNoSijil(mcNoSijil);
                        mohonStrata.setMcTrhSijil(sdf.parse(mcTrhSijil));
                        mohonStrata.setMcNoRuj(mcNoRuj);
                        mohonStrata.setMcTrhRuj(sdf.parse(mcTrhRuj));
                        strService.simpanPemilik(mohonStrata);

                        if (mhnSkim == null) {
                            List<PermohonanBangunan> mhnBngn = strService.checkMhnBangunanExist(idPermohonan);
                            PermohonanSkim mohonSkim = new PermohonanSkim();
                            if (!mhnBngn.isEmpty()) {
                                int jumPetak = 0;
                                int aks = 0;
                                int syer = 0;
                                for (PermohonanBangunan mhnBangunan : mhnBngn) {
                                    jumPetak = jumPetak + mhnBangunan.getBilanganPetak();
                                    syer = syer + mhnBangunan.getSyerBlok();
                                    List<BangunanPetakAksesori> PetakAKS = strService.findByIDBgnn(String.valueOf(mhnBangunan.getIdBangunan()));
                                    aks = aks + PetakAKS.size();
                                }
                                mohonSkim.setJumlahUnitSyer(Long.valueOf(syer));
                                mohonSkim.setBilAksr(Long.valueOf(aks));
                                mohonSkim.setBilPetak(Long.valueOf(jumPetak));
                            }

//                        mohonSkim.setBadanPengurusan(mc);
                            mohonSkim.setCawangan(peng.getKodCawangan());
                            mohonSkim.setHakmilikPermohonan(hp);
                            mohonSkim.setInfoAudit(infoAudit);
//                        mohonSkim.setNamaPemaju();
                            mohonSkim.setNamaProjek(mohonStrata.getNama());
                            mohonSkim.setDiukur(diukur_oleh);
                            mohonSkim.setNoKpPengukur(pengukur_noic);
                            mohonSkim.setNoFailUkur(no_ruj_ljt);
                            mohonSkim.setNoSkim(noSkim);
                            mohonSkim.setNoRujJubl(no_ruj_jubl);
                            mohonSkim.setNoFailUkurSemula(no_fail_ukursemula);
                            mohonSkim.setNoFailPt(no_fail_ukur);
                            if (mohon.getKodUrusan().getKod().equals("PHPP")) {
                                mohonSkim.setKodTujuanUkur(kodTujuanUkurDAO.findById("17"));//
                            }
                            if (mohon.getKodUrusan().getKod().equals("PHPC")) {
                                mohonSkim.setKodTujuanUkur(kodTujuanUkurDAO.findById("39"));//
                            } else {
                                mohonSkim.setKodTujuanUkur(kodTujuanUkurDAO.findById("18"));//
                            }
//                        Date sdf1 = Date.parse(tarikh_siap);
                            if (tarikh_siap != null) {
                                mohonSkim.setTrhSiap(sdf.parse(tarikh_siap));
                                mohonSkim.setTrhLulusCf(sdf.parse(tarikh_terima_sijil_akuan));
                            }
                            mohonSkim = strService.saveSkim(mohonSkim);
                            List<PermohonanBangunan> senaraimb = strService.findALLIDBngn(idPermohonan);
                            for (PermohonanBangunan mb : senaraimb) {
                                mb.setPermohonanSkim(mohonSkim);
                                strService.save(mb);
                            }
                        } else {
                            mhnSkim.setDiukur(diukur_oleh);
                            mhnSkim.setNoKpPengukur(pengukur_noic);
                            mhnSkim.setNoFailUkur(no_ruj_ljt);
//                        mohonSkim.setNoSkim(getIntegerValue1(skim));
                            mhnSkim.setNoRujJubl(no_ruj_jubl);
                            mhnSkim.setNoFailUkurSemula(no_fail_ukursemula);
                            mhnSkim.setNoFailPt(no_fail_ukur);
                            mhnSkim.setNoSkim(noSkim);
                            if (tarikh_siap != null) {
                                mhnSkim.setTrhSiap(sdf.parse(tarikh_siap));
                                mhnSkim.setTrhLulusCf(sdf.parse(tarikh_terima_sijil_akuan));
                            }
                            mhnSkim.setInfoAudit(infoAudit1);
//                            mhnSkim = strService.saveSkim(mhnSkim);
                            mhnSkim = strService.saveSkim(mhnSkim);
//                        permohonanSkimDAO.saveOrUpdate(mhnSkim);
                            List<PermohonanBangunan> senaraimb = strService.findALLIDBngn(idPermohonan);
                            for (PermohonanBangunan mb : senaraimb) {
                                mb.setPermohonanSkim(mhnSkim);
                                strService.save(mb);
                            }
                        }

                        msg = "Maklumat telah berjaya disimpan.";
                    } else {
                        error = "Sila isikan maklumat pemohon";
                        return new RedirectResolution(MaklumatProjekActionBean.class, "showForm").addParameter("error", error).addParameter("message", msg);
                    }
                } else {
                    error = "Sila isikan maklumat jadual petak di tab Jadual Petak XML atau Jadual Petak Manual";
                    return new RedirectResolution(MaklumatProjekActionBean.class, "showForm").addParameter("error", error).addParameter("message", msg);
                }
            }
        }

        return new RedirectResolution(MaklumatProjekActionBean.class, "showForm").addParameter("error", error).addParameter("message", msg);
    }
}
