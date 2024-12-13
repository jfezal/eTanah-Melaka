/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.strata;

import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.dao.KodJenisPengenalanDAO;
import etanah.dao.KodNegeriDAO;
import etanah.model.PermohonanStrata;
import etanah.dao.PemohonDAO;
import etanah.dao.PihakDAO;
import etanah.dao.PermohonanDAO;
import etanah.dao.HakmilikDAO;
import etanah.dao.PermohonanStrataDAO;
import etanah.model.Hakmilik;
import etanah.model.HakmilikPermohonan;
import etanah.model.InfoAudit;
import etanah.model.KodCawangan;
import etanah.model.KodJenisPengenalan;
import etanah.model.KodNegeri;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.model.Pemohon;
import etanah.model.Pihak;
import etanah.model.strata.BadanPengurusan;
import etanah.sequence.GeneratorNoSijilMC;
import etanah.service.StrataPtService;
import java.text.ParseException;
import net.sourceforge.stripes.action.UrlBinding;
import etanah.view.BasicTabActionBean;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.controller.LifecycleStage;
import org.apache.commons.lang.StringUtils;
import etanah.view.etanahActionBeanContext;
import java.util.List;
import net.sourceforge.stripes.action.RedirectResolution;
import org.apache.log4j.Logger;
import etanah.model.FasaPermohonan;
import java.util.Date;

/**
 *
 * @author wan.fairul
 */
@UrlBinding("/strata/sijil_perbadanan")
public class SijilPerbadananActionBean extends BasicTabActionBean {

    @Inject
    PihakDAO pihakDAO;
    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    PermohonanStrataDAO permohonanStrataDAO;
    @Inject
    HakmilikDAO hakmilikDAO;
    @Inject
    StrataPtService strService;
    @Inject
    GeneratorNoSijilMC generatorNoSijilMC;
    @Inject
    KodNegeriDAO kodNegeriDAO;
    @Inject
    PemohonDAO pemohonDAO;
    @Inject
    KodJenisPengenalanDAO kodJenisPengenalanDAO;
    @Inject
    etanah.Configuration conf;
    private Permohonan permohonan;
    FasaPermohonan mohonFasa;
    private BadanPengurusan mc;
    private Pihak pihak;
    private String pengurusanNama;
    private String pengurusanNoPengenalan;
    private String pengurusanAlamat1;
    private String pengurusanAlamat2;
    private String pengurusanAlamat3;
    private String pengurusanAlamat4;
    private String pengurusanPoskod;
    private String pengurusanJenisPengenalan;
    private String pengurusanKodNegeri;
    private String pengurusanTarikhSijil;
    private Pemohon pemohon;
    private String pengurusanNoRujukan;
    private String pengurusanTarikhRujukan;
    private static final Logger LOG = Logger.getLogger(SijilPerbadananActionBean.class);
    private HakmilikPermohonan hakmilikPermohonan;
    private HakmilikPermohonan hakmilikPermohonan1;
    private List<Hakmilik> hakmilik;
    private List<BadanPengurusan> maxKos;
    private String newKos;
    private String newKos1;
    private String newKos2;
    private String newKos3;

    public String getPengurusanNoPengenalan() {
        return pengurusanNoPengenalan;
    }

    public void setPengurusanNoPengenalan(String pengurusanNoPengenalan) {
        this.pengurusanNoPengenalan = pengurusanNoPengenalan;
    }

    public Pemohon getPemohon() {
        return pemohon;
    }

    public void setPemohon(Pemohon pemohon) {
        this.pemohon = pemohon;
    }

    public PemohonDAO getPemohonDAO() {
        return pemohonDAO;
    }

    public void setPemohonDAO(PemohonDAO pemohonDAO) {
        this.pemohonDAO = pemohonDAO;
    }

    public PihakDAO getPihakDAO() {
        return pihakDAO;
    }

    public void setPihakDAO(PihakDAO pihakDAO) {
        this.pihakDAO = pihakDAO;
    }

    public BadanPengurusan getMc() {
        return mc;
    }

    public void setMc(BadanPengurusan mc) {
        this.mc = mc;
    }

    public String getPengurusanAlamat1() {
        return pengurusanAlamat1;
    }

    public void setPengurusanAlamat1(String pengurusanAlamat1) {
        this.pengurusanAlamat1 = pengurusanAlamat1;
    }

    public String getPengurusanAlamat2() {
        return pengurusanAlamat2;
    }

    public void setPengurusanAlamat2(String pengurusanAlamat2) {
        this.pengurusanAlamat2 = pengurusanAlamat2;
    }

    public String getPengurusanAlamat3() {
        return pengurusanAlamat3;
    }

    public void setPengurusanAlamat3(String pengurusanAlamat3) {
        this.pengurusanAlamat3 = pengurusanAlamat3;
    }

    public String getPengurusanAlamat4() {
        return pengurusanAlamat4;
    }

    public void setPengurusanAlamat4(String pengurusanAlamat4) {
        this.pengurusanAlamat4 = pengurusanAlamat4;
    }

    public String getPengurusanNama() {
        return pengurusanNama;
    }

    public void setPengurusanNama(String pengurusanNama) {
        this.pengurusanNama = pengurusanNama;
    }

    public String getPengurusanNoRujukan() {
        return pengurusanNoRujukan;
    }

    public void setPengurusanNoRujukan(String pengurusanNoRujukan) {
        this.pengurusanNoRujukan = pengurusanNoRujukan;
    }

    public String getPengurusanPoskod() {
        return pengurusanPoskod;
    }

    public void setPengurusanPoskod(String pengurusanPoskod) {
        this.pengurusanPoskod = pengurusanPoskod;
    }

    public FasaPermohonan getMohonFasa() {
        return mohonFasa;
    }

    public void setMohonFasa(FasaPermohonan mohonFasa) {
        this.mohonFasa = mohonFasa;
    }

    public String getPengurusanTarikhRujukan() {
        return pengurusanTarikhRujukan;
    }

    public void setPengurusanTarikhRujukan(String pengurusanTarikhRujukan) {
        this.pengurusanTarikhRujukan = pengurusanTarikhRujukan;
    }

    public String getPengurusanTarikhSijil() {
        return pengurusanTarikhSijil;
    }

    public void setPengurusanTarikhSijil(String pengurusanTarikhSijil) {
        this.pengurusanTarikhSijil = pengurusanTarikhSijil;
    }

    public String getPengurusanJenisPengenalan() {
        return pengurusanJenisPengenalan;
    }

    public void setPengurusanJenisPengenalan(String pengurusanJenisPengenalan) {
        this.pengurusanJenisPengenalan = pengurusanJenisPengenalan;
    }

    public String getPengurusanKodNegeri() {
        return pengurusanKodNegeri;
    }

    public void setPengurusanKodNegeri(String pengurusanKodNegeri) {
        this.pengurusanKodNegeri = pengurusanKodNegeri;
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

    public StrataPtService getStrService() {
        return strService;
    }

    public void setStrService(StrataPtService strService) {
        this.strService = strService;
    }

    public HakmilikPermohonan getHakmilikPermohonan1() {
        return hakmilikPermohonan1;
    }

    public void setHakmilikPermohonan1(HakmilikPermohonan hakmilikPermohonan1) {
        this.hakmilikPermohonan1 = hakmilikPermohonan1;
    }

    public List<Hakmilik> getHakmilik() {
        return hakmilik;
    }

    public void setHakmilik(List<Hakmilik> hakmilik) {
        this.hakmilik = hakmilik;
    }

    public List<BadanPengurusan> getMaxKos() {
        return maxKos;
    }

    public void setMaxKos(List<BadanPengurusan> maxKos) {
        this.maxKos = maxKos;
    }

    public String getNewKos() {
        return newKos;
    }

    public void setNewKos(String newKos) {
        this.newKos = newKos;
    }

    public String getNewKos1() {
        return newKos1;
    }

    public void setNewKos1(String newKos1) {
        this.newKos1 = newKos1;
    }

    public String getNewKos2() {
        return newKos2;
    }

    public void setNewKos2(String newKos2) {
        this.newKos2 = newKos2;
    }

    public String getNewKos3() {
        return newKos3;
    }

    public void setNewKos3(String newKos3) {
        this.newKos3 = newKos3;
    }

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
        return new JSP("strata/Sijil_perbadanan/draf_sijil.jsp").addParameter("tab", "true");
    }

    public Resolution showForm2() {
        String msg = getContext().getRequest().getParameter("message");
        String error = getContext().getRequest().getParameter("error");
        if (StringUtils.isNotBlank(msg)) {
            addSimpleMessage(msg);
        }
        if (StringUtils.isNotBlank(error)) {
            addSimpleError(error);
        }
        return new JSP("strata/Sijil_perbadanan/papar_draf_sijil.jsp").addParameter("tab", "true");
    }

    public Resolution resetForm() {

        /* new request : if Isi Semula = delete pemohon */
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");

        if (idPermohonan != null) {
            mc = strService.findBdn(idPermohonan);
            permohonan = permohonanDAO.findById(idPermohonan);


            try {
                LOG.info("::DELETE sijil badan pengurusan::");
                strService.deletedrafsijil(idPermohonan);
                LOG.info("::DELETE sijil pihak::");
                strService.deletedrafsijilpihak(mc.getPihak().getIdPihak());


                if (permohonan != null) {
                    try {

                        LOG.info("::DELETE sijil perbadanan::");
//                        strService.deletedrafsijil(permohonan.getIdPermohonan());
                        strService.deletedrafsijil(idPermohonan);

                        LOG.info("::DELETE PIHAK::");
                        strService.deletedrafsijilpihak(mc.getPihak().getIdPihak());

                        addSimpleMessage("Maklumat Draf Sijil Perbadanan Pengurusan telah berjaya dipadamkan.");
                    } catch (Exception e) {
                        LOG.error(e);
                    }
                }

            } catch (Exception e) {
                LOG.error(e);
                addSimpleError("Terdapat masalah teknikal semasa memadam rekod.");
            }

        }

        return new RedirectResolution(SijilPerbadananActionBean.class, "showForm");
    }

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");

        if (conf.getProperty("kodNegeri").equals("05")) {
            if (idPermohonan != null) {
                permohonan = permohonanDAO.findById(idPermohonan);
                mc = strService.findBdn(idPermohonan);
                if (mc != null) {
                    pengurusanNama = mc.getPihak().getNama();
                    pengurusanNoPengenalan = mc.getPihak().getNoPengenalan();
                    pengurusanAlamat1 = mc.getPihak().getAlamat1();
                    pengurusanAlamat2 = mc.getPihak().getAlamat2();
                    pengurusanAlamat3 = mc.getPihak().getAlamat3();
                    pengurusanAlamat4 = mc.getPihak().getAlamat4();
                    pengurusanPoskod = mc.getPihak().getPoskod();
                    if (mc.getPihak().getJenisPengenalan() != null) {
                        pengurusanJenisPengenalan = mc.getPihak().getJenisPengenalan().getKod();
                    }
                    pengurusanKodNegeri = mc.getPihak().getNegeri().getKod();
                    pengurusanTarikhSijil = strService.formatSDF(mc.getPengurusanTarikhSijil());
                    pengurusanNoRujukan = mc.getPengurusanNoRujukan();
//                    pengurusanTarikhRujukan = strService.formatSDF(mc.getPengurusanTarikhRujukan());

                } else {
                    LOG.info("----Dapatkan alamat dari IDHakmilik Induk----");
                    hakmilikPermohonan1 = strService.findMohonHakmilik(idPermohonan);
                    hakmilik = strService.findIdHakmilikByIdHakmilikInduk(hakmilikPermohonan1.getHakmilik().getIdHakmilik());
                    LOG.info("----hakmilik----:" + hakmilik);
                    if (hakmilik.get(0).getBadanPengurusan() != null) {
                        mc = strService.findBdnByIdBdn(hakmilik.get(0).getBadanPengurusan().getIdBadan());
                        LOG.info("----mc Id----" + mc.getIdBadan());
                        if (mc != null) {
                            if (mc.getPihak().getNama() != null) {
                                pengurusanNama = mc.getPihak().getNama();
                            }
                            if (mc.getPihak().getNoPengenalan() != null) {
                                pengurusanNoPengenalan = mc.getPihak().getNoPengenalan();
                            }
                            if (mc.getPihak().getAlamat1() != null) {
                                pengurusanAlamat1 = mc.getPihak().getAlamat1();
                            }
                            if (mc.getPihak().getAlamat2() != null) {
                                pengurusanAlamat2 = mc.getPihak().getAlamat2();
                            }
                            if (mc.getPihak().getAlamat3() != null) {
                                pengurusanAlamat3 = mc.getPihak().getAlamat3();
                            }
                            if (mc.getPihak().getAlamat4() != null) {
                                pengurusanAlamat4 = mc.getPihak().getAlamat4();
                            }
                            if (mc.getPihak().getPoskod() != null) {
                                pengurusanPoskod = mc.getPihak().getPoskod();
                            }
                            if (mc.getPihak().getJenisPengenalan() != null) {
                                pengurusanJenisPengenalan = mc.getPihak().getJenisPengenalan().getKod();
                            }
                            if (mc.getPihak().getNegeri() != null) {
                                pengurusanKodNegeri = mc.getPihak().getNegeri().getKod();
                            }
                            if (mc.getPengurusanTarikhSijil() != null) {
                                pengurusanTarikhSijil = strService.formatSDF(hakmilikPermohonan1.getHakmilik().getTarikhDaftar());
                            }
                            pengurusanNoRujukan = mc.getPermohonan().getIdPermohonan();
//                            pengurusanTarikhRujukan = strService.formatSDF(new java.util.Date());

                        }
                    }

                }
            }
        }


        if (conf.getProperty("kodNegeri").equals("04")) {
            if (idPermohonan != null) {
                permohonan = permohonanDAO.findById(idPermohonan);
                mc = strService.findBdn(idPermohonan);
                if (mc != null) {
                    pengurusanNama = mc.getPihak().getNama();
                    pengurusanNoPengenalan = mc.getPihak().getNoPengenalan();
                    pengurusanAlamat1 = mc.getPihak().getAlamat1();
                    pengurusanAlamat2 = mc.getPihak().getAlamat2();
                    pengurusanAlamat3 = mc.getPihak().getAlamat3();
                    pengurusanAlamat4 = mc.getPihak().getAlamat4();
                    pengurusanPoskod = mc.getPihak().getPoskod();
                    if (mc.getPihak().getJenisPengenalan() != null) {
                        pengurusanJenisPengenalan = mc.getPihak().getJenisPengenalan().getKod();
                    }
                    
                    if (mc.getPihak().getNegeri() != null) {
                        pengurusanKodNegeri = mc.getPihak().getNegeri().getKod();
                    } 
                    pengurusanTarikhSijil = strService.formatSDF(mc.getPengurusanTarikhSijil());
                    pengurusanNoRujukan = mc.getPengurusanNoRujukan();
//                    pengurusanTarikhRujukan = strService.formatSDF(mc.getPengurusanTarikhRujukan());

                } else {
                    LOG.info("----get data from IDStrata----");
                    hakmilikPermohonan1 = strService.findMohonHakmilik(idPermohonan);
                    hakmilik = strService.findIdHakmilikByIdHakmilikInduk(hakmilikPermohonan1.getHakmilik().getIdHakmilik());
                    LOG.info("----hakmilik----:" + hakmilik);
                    if (hakmilik.get(0).getBadanPengurusan() != null) {
                        mc = strService.findBdnByIdBdn(hakmilik.get(0).getBadanPengurusan().getIdBadan());
                        BadanPengurusan bdnUrus = strService.findBdn(idPermohonan);
                        LOG.info("----mc Id----" + mc.getIdBadan());
                        if (mc != null) {
                            if (bdnUrus != null) {
                                if (mc.getPihak().getNama() != null) {
                                    pengurusanNama = mc.getPihak().getNama();
                                }
                                if (mc.getPihak().getNoPengenalan() != null) {
                                    pengurusanNoPengenalan = mc.getPihak().getNoPengenalan();
                                }
                                if (mc.getPihak().getAlamat1() != null) {
                                    pengurusanAlamat1 = mc.getPihak().getAlamat1();
                                }
                                if (mc.getPihak().getAlamat2() != null) {
                                    pengurusanAlamat2 = mc.getPihak().getAlamat2();
                                }
                                if (mc.getPihak().getAlamat3() != null) {
                                    pengurusanAlamat3 = mc.getPihak().getAlamat3();
                                }
                                if (mc.getPihak().getAlamat4() != null) {
                                    pengurusanAlamat4 = mc.getPihak().getAlamat4();
                                }
                                if (mc.getPihak().getPoskod() != null) {
                                    pengurusanPoskod = mc.getPihak().getPoskod();
                                }
                                if (mc.getPihak().getJenisPengenalan() != null) {
                                    pengurusanJenisPengenalan = mc.getPihak().getJenisPengenalan().getKod();
                                }
                                if (mc.getPihak().getNegeri() != null) {
                                    pengurusanKodNegeri = mc.getPihak().getNegeri().getKod();
                                }
                                if (mc.getPengurusanTarikhSijil() != null) {
                                    pengurusanTarikhSijil = strService.formatSDF(hakmilikPermohonan1.getHakmilik().getTarikhDaftar());
                                }
                                pengurusanNoRujukan = mc.getPermohonan().getIdPermohonan();
//                                pengurusanTarikhRujukan = strService.formatSDF(new java.util.Date());
                            }
                            else {
                                Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
                                InfoAudit infoAudit = new InfoAudit();
                                infoAudit.setDimasukOleh(peng);
                                infoAudit.setTarikhMasuk(new java.util.Date());

                                BadanPengurusan bdnUrusBaru = new BadanPengurusan();
                                bdnUrusBaru.setCawangan(permohonan.getCawangan());
                                bdnUrusBaru.setPermohonan(permohonan);
                                bdnUrusBaru.setPihak(mc.getPihak());
                                bdnUrusBaru.setInfoAudit(infoAudit);
//                                bdnUrusBaru.setPengurusanTarikhRujukan(new java.util.Date());
                                bdnUrusBaru.setPengurusanTarikhSijil(hakmilikPermohonan1.getHakmilik().getTarikhDaftar());
                                strService.simpanSijil(bdnUrusBaru);

                                LOG.info("bdnUrusBaru--"+bdnUrusBaru.getIdBadan());

                                for(Hakmilik hm : hakmilik){
                                    hm.setBadanPengurusan(bdnUrusBaru);
//                                    LOG.info("hm--"+hm.getBadanPengurusan().getIdBadan());
//                                    hakmilikDAO.saveOrUpdate(hm);
                                    strService.simpanhakmilik(hm);
                                }

//                                strService.UpdatedIdBdn(bdnUrusBaru.getIdBadan(), hakmilikPermohonan1.getHakmilik().getIdHakmilikInduk());

                                mc = strService.findBdnByIdBdn(hakmilik.get(0).getBadanPengurusan().getIdBadan());
                                pengurusanNama = mc.getPihak().getNama();
                                pengurusanNoPengenalan = mc.getPihak().getNoPengenalan();
                                pengurusanAlamat1 = mc.getPihak().getAlamat1();
                                pengurusanAlamat2 = mc.getPihak().getAlamat2();
                                pengurusanAlamat3 = mc.getPihak().getAlamat3();
                                pengurusanAlamat4 = mc.getPihak().getAlamat4();
                                pengurusanPoskod = mc.getPihak().getPoskod();
                                if (mc.getPihak().getJenisPengenalan() != null) {
                                    pengurusanJenisPengenalan = mc.getPihak().getJenisPengenalan().getKod();
                                }
                                if (mc.getPihak().getNegeri() != null) {
                                    pengurusanKodNegeri = mc.getPihak().getNegeri().getKod();
                                }
                                pengurusanTarikhSijil = strService.formatSDF(hakmilikPermohonan1.getHakmilik().getTarikhDaftar());

                                if (mc.getPermohonan() != null) {
                                    pengurusanNoRujukan = mc.getPermohonan().getIdPermohonan();
                                }
//                                pengurusanTarikhRujukan = strService.formatSDF(new java.util.Date());
                            }

                        }
                    }

                }
            }
        }
    }

    public Resolution simpandraf() throws ParseException {
        Pihak pihak = null;
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");

        if (idPermohonan != null) {
            permohonan = permohonanDAO.findById(idPermohonan);
        }
        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        InfoAudit infoAudit = new InfoAudit();
        infoAudit.setDimasukOleh(peng);
        infoAudit.setTarikhMasuk(new java.util.Date());
        
        InfoAudit ia2 = new InfoAudit();
        ia2.setDiKemaskiniOleh(peng);
        ia2.setTarikhKemaskini(new Date());
        String error = "";
        String msg = "";

        LOG.info("pengurusanKodNegeri" + pengurusanKodNegeri);
        KodJenisPengenalan kjp = new KodJenisPengenalan();
        kjp = kodJenisPengenalanDAO.findById("S");
        KodCawangan kodCawangan = new KodCawangan();
        kodCawangan.setKod(permohonan.getCawangan().getKod());

        //mc = strService.findBdn(idPermohonan);
        hakmilikPermohonan1 = strService.findMohonHakmilik(idPermohonan);
        hakmilik = strService.findIdHakmilikByIdHakmilikInduk(hakmilikPermohonan1.getHakmilik().getIdHakmilik());
        mc = strService.findBdnByIdBdn(hakmilik.get(0).getBadanPengurusan().getIdBadan());
        if (mc != null) {
            mc.setInfoAudit(ia2);
        } else {
            mc = new BadanPengurusan();
            mc.setInfoAudit(infoAudit);
            mc.setPermohonan(permohonan);
        }
        if (mc.getPihak() != null) {
            pihak = mc.getPihak();
            pihak.setInfoAudit(ia2);
            pihak = setPihak(pihak);
        } else {
            pihak = new Pihak();
            pihak.setJenisPengenalan(kjp);
            pihak.setInfoAudit(infoAudit);
            pihak = setPihak(pihak);
        }
        pihak = strService.savePihak(pihak);
        mc.setPermohonan(permohonan);
        mc.setPengurusanNoRujukan(pengurusanNoRujukan);

//        if (StringUtils.isNotBlank(pengurusanTarikhRujukan)) {
//            mc.setPengurusanTarikhRujukan(strService.formatSDF(pengurusanTarikhRujukan));
//
//        }
        if (StringUtils.isNotBlank(pengurusanTarikhSijil)) {
            mc.setPengurusanTarikhSijil(strService.formatSDF(pengurusanTarikhSijil));

        }
        mc.setCawangan(kodCawangan);
        if (StringUtils.isEmpty(mc.getPengurusanNoSijil())) {

            String kodNegeri = conf.getProperty("kodNegeri");
            idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
            String year = (String) getContext().getRequest().getParameter("tahun");
            permohonan = permohonanDAO.findById(idPermohonan);
            stageId = "kemasukansediasijil";
            mohonFasa = strService.findMohonFasa(idPermohonan, stageId);

            if (kodNegeri.equals("04")) {
//                if (mohonFasa != null) {
//                    LOG.info("++++++KOD CAW +++++" + permohonan.getCawangan().getKod());
//                    String kod = permohonan.getCawangan().getKod();
//                    LOG.info("++++++year +++++" +year);
//                    maxKos = strService.getHighestNoSiri(kod, year);
//                    LOG.info("++++++maxKosssss +++++" + maxKos.size());
//
//                    if (maxKos.isEmpty()) {
//                        int IntMaxKos = 1;
//                        String nosijil = Integer.toString(IntMaxKos);
//                        LOG.info("nosijil"+nosijil);
//                        mc.setPengurusanNoSijil(nosijil);
//                    } else {
//                        newKos2 = maxKos.get(0).getPengurusanNoSijil();
//
//                        int IntMaxKos = Integer.parseInt(newKos2) + 1;
//                        String nosijil = Integer.toString(IntMaxKos);
//                        mc.setPengurusanNoSijil(nosijil);
//                        LOG.info("nosijil"+nosijil);
//                    }
//
//                }
            } else {
//                mc.setPengurusanNoSijil(generatorNoSijilMC.generateSijil());
            }

        }
        mc.setPihak(pihak);
        strService.simpanSijil(mc);
        msg = "Maklumat telah berjaya disimpan.";
        return new RedirectResolution(SijilPerbadananActionBean.class, "showForm").addParameter("error", error).addParameter("message", msg);
    }

    public Resolution updateDraf() throws ParseException {
        Pihak pihak = null;
        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        InfoAudit infoAudit = new InfoAudit();
        infoAudit.setDimasukOleh(peng);
        infoAudit.setTarikhMasuk(new java.util.Date());
        
        InfoAudit ia2 = new InfoAudit();
        ia2.setDiKemaskiniOleh(peng);
        ia2.setTarikhKemaskini(new java.util.Date());
        String error = "";
        String msg = "";

        if (mc == null) {
            error = "Sila isikan maklumat sijil yang mandatori";


            return new RedirectResolution(SijilPerbadananActionBean.class, "showForm").addParameter("error", error).addParameter("message", msg);
        } else {
            error = "Sila isikan maklumat sijil yang mandatori";
            KodNegeri kodNegeri = new KodNegeri();
            kodNegeri.setKod(pengurusanKodNegeri);
            KodJenisPengenalan kjp = new KodJenisPengenalan();
            kjp.setKod(pengurusanJenisPengenalan);
            KodCawangan kodCawangan = new KodCawangan();
            kodCawangan.setKod(permohonan.getCawangan().getKod());
            mc.setPermohonan(permohonan);

            mc = strService.findBdn(idPermohonan);
            if (mc.getPihak() != null) {
                pihak = mc.getPihak();
                pihak.setInfoAudit(ia2);
                pihak = setPihak(pihak);
            } else {
                pihak = new Pihak();
                pihak = setPihak(pihak);
                pihak.setInfoAudit(infoAudit);
            }
            pihak = strService.savePihak(pihak);

            mc.setPengurusanNoRujukan(pengurusanNoRujukan);
//            if (StringUtils.isNotBlank(pengurusanTarikhRujukan)) {
//                mc.setPengurusanTarikhRujukan(strService.formatSDF(pengurusanTarikhRujukan));
//
//            }
            if (StringUtils.isNotBlank(pengurusanTarikhSijil)) {
                mc.setPengurusanTarikhSijil(strService.formatSDF(pengurusanTarikhSijil));

            }
            mc.setPihak(pihak);
            mc.setInfoAudit(ia2);
            mc.setCawangan(kodCawangan);
            strService.simpanSijil(mc);
            msg = "Maklumat telah berjaya disimpan.";


        }
        return new RedirectResolution(SijilPerbadananActionBean.class, "showForm").addParameter("error", error).addParameter("message", msg);

    }

    public Pihak setPihak(Pihak pihak) {
        pihak.setNama(pengurusanNama);
        pihak.setNoPengenalan(pengurusanNoPengenalan);
        pihak.setAlamat1(pengurusanAlamat1);
        pihak.setAlamat2(pengurusanAlamat2);
        pihak.setAlamat3(pengurusanAlamat3);
        pihak.setAlamat4(pengurusanAlamat4);
        pihak.setPoskod(pengurusanPoskod);
//        pihak.setNoPengenalan(pengurusan);
        if (StringUtils.isEmpty(pengurusanNama)) {
            addSimpleError("Sila Masukkan Nama.");
        }
        if (StringUtils.isEmpty(pengurusanAlamat1)) {
            addSimpleError("Sila Masukkan Alamat.");
        }
        if (StringUtils.isEmpty(pengurusanJenisPengenalan)) {
        } else {
            KodJenisPengenalan kjp = new KodJenisPengenalan() {
            };
            kjp.setKod(pengurusanJenisPengenalan);
            pihak.setJenisPengenalan(kjp);
        }
        if (StringUtils.isEmpty(pengurusanKodNegeri)) {
            addSimpleError("Sila Masukkan Kod Negeri.");
        } else {
            KodNegeri kodNegeri = new KodNegeri() {
            };
            kodNegeri.setKod(pengurusanKodNegeri);
            pihak.setNegeri(kodNegeri);
        }
        return pihak;
    }
}
