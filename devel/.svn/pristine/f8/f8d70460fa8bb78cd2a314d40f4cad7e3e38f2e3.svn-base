/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.stripes.lelong;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import org.apache.commons.lang.StringUtils;
import com.google.inject.Inject;
import etanah.Configuration;
import etanah.dao.JuruLelongDAO;
import etanah.dao.KodKeputusanDAO;
import etanah.dao.PermohonanDAO;
import etanah.dao.SytJuruLelongDAO;
import etanah.model.Enkuiri;
import etanah.model.FasaPermohonan;
import etanah.model.FasaPermohonanLog;
import etanah.model.InfoAudit;
import etanah.model.JuruLelong;
import etanah.model.KodKeputusan;
import etanah.model.Lelongan;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.model.PermohonanAsal;
import etanah.model.SytJuruLelong;
import etanah.service.common.LelongService;
import etanah.view.etanahActionBeanContext;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.StreamingResolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;
import org.apache.log4j.Logger;

/**
 *
 * @author mdizzat.mashrom
 */
@UrlBinding("/lelong/Memasukkan_Maklumat_JurulelongBerlesen")
public class MemasukkanMaklumatJurulelongBerlesenActionBean extends AbleActionBean {

    @Inject
    SytJuruLelongDAO sytJuruLelongDAO;
    @Inject
    JuruLelongDAO jurulelongDAO;
    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    LelongService lelongService;
    @Inject
    KodKeputusanDAO kodKeputusanDAO;
    @Inject
    etanah.Configuration conf;
    private Permohonan permohonan;
    private Enkuiri enkuiri;
    private Lelongan lelongan;
    private String idPermohonan;
    private List<SytJuruLelong> list;
    private List<JuruLelong> listJurulelong;
    private List<Lelongan> lelonganList = new ArrayList<Lelongan>();
    private List<Lelongan> lelonganList2;
    private List<Lelongan> lelonganList3;
    private List<Lelongan> lelonganListJLB;
    private List<FasaPermohonan> mohonFasa;
    private boolean display = false;
    private boolean flag = false;
    private boolean button = false;
    private boolean show = true;
    private Pengguna pengguna;
    private static final Logger LOG = Logger.getLogger(MemasukkanMaklumatJurulelongBerlesenActionBean.class);
    private boolean mlk = true;
    private boolean jual = true;
    private String perihal;
    private boolean stageJurulelongN9 = true;

    @DefaultHandler
    public Resolution showForm() {
        LOG.info("----showform-----");
        show = false;
        FasaPermohonan mohonFasa = lelongService.findFasaPermohonanTangguh(idPermohonan);
        if (mohonFasa.getKeputusan() == null) {
            LOG.info("----null-----");
            addSimpleError("Keputusan Belum Dibuat.Sila Pilih Keputusan Di Tab Keputusan.");
            button = true;
            return new JSP("lelong/senarai_jurulelong_berlesen.jsp").addParameter("tab", "true");
        } else {
            if (mohonFasa.getKeputusan().getKod().equals("L")) {
                LOG.info("---L---");
                List<Lelongan> list2 = null;
                if (permohonan.getKodUrusan().getKod().equals("PPTL")) {
                    list2 = lelongService.listLelonganAK(permohonan.getPermohonanSebelum().getIdPermohonan());
                } else {
                    list2 = lelongService.listLelonganAK(idPermohonan);
                }
                if (!list2.isEmpty()) {
                    String tarikh = "";
                    for (Lelongan ll : list2) {
                        if (ll.getTarikhLelong() == null) {
                            tarikh = "null";
                        }
                    }
                    LOG.info("tarikh : " + tarikh);
                    if (tarikh.equals("null")) {
                        addSimpleError("Tarikh Lelongan Belum Dibuat.Sila Pilih Tarikh Lelongan Di Tab Maklumat Keputusan.");
                        button = true;
                        return new JSP("lelong/senarai_jurulelong_berlesen.jsp").addParameter("tab", "true");
                    }

                    List<FasaPermohonan> mohonFasa2 = lelongService.getPermonanFasa2(idPermohonan);
                    FasaPermohonan ff3 = null;
                    if (!mohonFasa2.isEmpty()) {
                        ff3 = mohonFasa2.get(0);
                    }
                    if (ff3 == null) {
                        return new JSP("lelong/tggh_kpsn_lantik_jurulelong.jsp").addParameter("tab", "true");
                    }
                    if (ff3.getKeputusan() == null) {
                        return new JSP("lelong/tggh_kpsn_lantik_jurulelong.jsp").addParameter("tab", "true");
                    } else {
                        button = false;
                        if (ff3.getKeputusan().getKod().equals("PH")) {
                            LOG.info("-----Pentadbir Tanah-------");
                            for (Lelongan ll : lelonganList) {
                                ll.setSytJuruLelong(null);
                                lelongService.saveOrUpdate(ll);
                            }
                            flag = false;
                            rehydrate();
                            return new JSP("lelong/senarai_jurulelong_berlesen.jsp").addParameter("tab", "true");
                        }
                        if (ff3.getKeputusan().getKod().equals("JL")) {
                            LOG.info("------Jurulelong------");
                            flag = true;
                            return new JSP("lelong/senarai_jurulelong_berlesen.jsp").addParameter("tab", "true");
                        }
                    }
                } else {
                    addSimpleError("Tarikh Lelongan Belum Dibuat.Sila Pilih Tarikh Lelongan Di Tab Maklumat Keputusan.");
                    button = true;
                    return new JSP("lelong/senarai_jurulelong_berlesen.jsp").addParameter("tab", "true");
                }
            }
            if (mohonFasa.getKeputusan().getKod().equals("T")) {
                LOG.info("-----Tolak-------");
                addSimpleMessage("Keputusan Adalah Tolak.Sila Klik Butang Selesai...");
                button = true;
                return new JSP("lelong/senarai_jurulelong_berlesen.jsp").addParameter("tab", "true");
            }
        }
        return new JSP("lelong/senarai_jurulelong_berlesen.jsp").addParameter("tab", "true");
    }

    public Resolution showForm4() {
        LOG.info("----showForm4-----");
        show = true;    
        //FasaPermohonan mohonFasa = lelongService.findFasaPermohonanSemakPembida(idPermohonan);
        
        List<FasaPermohonan> mohonFasaList = lelongService.findListFasaPermohonanSemakPembida(idPermohonan);
         FasaPermohonan mohonFasa = null;
            if (!mohonFasaList.isEmpty()) {
                mohonFasa = mohonFasaList.get(0);
            }
        if ("05".equals(conf.getProperty("kodNegeri"))) {
            //negeri9
            mlk = false;
        }
        if (mohonFasa != null && mohonFasa.getKeputusan() == null) {
            LOG.info("----null-----");
            addSimpleError("Keputusan Belum Dibuat.Sila Pilih Keputusan Di Tab Keputusan.");
            button = true;
            return new JSP("lelong/senarai_jurulelong_berlesen.jsp").addParameter("tab", "true");
        } else {

            if (mohonFasa.getKeputusan().getKod().equals("AA")) {
                LOG.info("------Jurulelong------");
                List<Lelongan> lel = null;
                if (permohonan.getKodUrusan().getKod().equals("PPTL")) {
                    lel = lelongService.listLelonganAK(permohonan.getPermohonanSebelum().getIdPermohonan());
                } else {
                    lel = lelongService.listLelonganAK(idPermohonan);
                }
                if (lel.isEmpty()) {
                    addSimpleError("Sila Masukkan Tarikh Lelongan Di Tab Maklumat Keputusan.");
                    button = true;
                    return new JSP("lelong/senarai_jurulelong_berlesen.jsp").addParameter("tab", "true");
                } else {
                    for (Lelongan ll : lel) {
                        if (ll.getTarikhLelong() == null) {
                            addSimpleError("Sila Masukkan Tarikh Lelongan Di Tab Maklumat Keputusan.");
                            button = true;
                            return new JSP("lelong/senarai_jurulelong_berlesen.jsp").addParameter("tab", "true");
                        }
                    }
                }
                button = false;
                flag = true;
                List<FasaPermohonan> senaraifasamohon = lelongService.getPermonanFasa2(idPermohonan);
                LOG.info("senaraifasamohon : " + senaraifasamohon.size());
                FasaPermohonan fas = null;
                InfoAudit ia = new InfoAudit();
                ia.setDimasukOleh(pengguna);
                ia.setTarikhMasuk(new java.util.Date());
                ia.setDiKemaskiniOleh(pengguna);
                ia.setTarikhKemaskini(new java.util.Date());
                if (!senaraifasamohon.isEmpty()) {
                    fas = senaraifasamohon.get(0);
                    fas.setInfoAudit(ia);
                } else {
                    fas = new FasaPermohonan();
                    fas.setInfoAudit(ia);
                    fas.setPermohonan(permohonan);
                    fas.setIdAliran("semak16HLantikJurulelong");
                    fas.setCawangan(pengguna.getKodCawangan());
                    fas.setIdPengguna(pengguna.getIdPengguna());
                }
                KodKeputusan kos = kodKeputusanDAO.findById("JL");
                fas.setKeputusan(kos);
                fas.setTarikhHantar(new Date());
                fas.setTarikhKeputusan(new Date());
                lelongService.saveUpdate2(fas);
                return new JSP("lelong/senarai_jurulelong_berlesen.jsp").addParameter("tab", "true");
            }
            if (mohonFasa.getKeputusan().getKod().equals("LS")) {
                LOG.info("-----Pentadbir Tanah-------");
                List<Lelongan> lel = null;
                if (permohonan.getKodUrusan().getKod().equals("PPTL")) {
                    lel = lelongService.listLelonganAK(permohonan.getPermohonanSebelum().getIdPermohonan());
                } else {
                    lel = lelongService.listLelonganAK(idPermohonan);
                }
                if (lel.isEmpty()) {
                    addSimpleError("Sila Masukkan Tarikh Lelongan Di Tab Maklumat Keputusan.");
                    button = true;
                    return new JSP("lelong/senarai_jurulelong_berlesen.jsp").addParameter("tab", "true");
                } else {
                    for (Lelongan ll : lel) {
                        if (ll.getTarikhLelong() == null) {
                            addSimpleError("Sila Masukkan Tarikh Lelongan Di Tab Maklumat Keputusan.");
                            button = true;
                            return new JSP("lelong/senarai_jurulelong_berlesen.jsp").addParameter("tab", "true");
                        }
                    }
                }
                button = false;
                for (Lelongan ll : lelonganList) {
                    ll.setSytJuruLelong(null);
                    lelongService.saveOrUpdate(ll);
                }
                flag = false;

                List<FasaPermohonan> senaraifasamohon = lelongService.getPermonanFasa2(idPermohonan);
                LOG.info("senaraifasamohon : " + senaraifasamohon.size());
                FasaPermohonan fas = null;
                InfoAudit ia = new InfoAudit();
                ia.setDimasukOleh(pengguna);
                ia.setTarikhMasuk(new java.util.Date());
                ia.setDiKemaskiniOleh(pengguna);
                ia.setTarikhKemaskini(new java.util.Date());
                if (!senaraifasamohon.isEmpty()) {
                    fas = senaraifasamohon.get(0);
                    fas.setInfoAudit(ia);
                } else {
                    fas = new FasaPermohonan();
                    fas.setInfoAudit(ia);
                    fas.setPermohonan(permohonan);
                    fas.setIdAliran("semak16HLantikJurulelong");
                    fas.setCawangan(pengguna.getKodCawangan());
                    fas.setIdPengguna(pengguna.getIdPengguna());
                }
                KodKeputusan kos = kodKeputusanDAO.findById("PH");
                fas.setKeputusan(kos);
                fas.setTarikhHantar(new Date());
                fas.setTarikhKeputusan(new Date());
                lelongService.saveUpdate2(fas);
                rehydrate();
                return new JSP("lelong/senarai_jurulelong_berlesen.jsp").addParameter("tab", "true");
            }
            if (mohonFasa.getKeputusan().getKod().equals("RM")) {
                LOG.info("-----Rujuk Mahkahmah-------");
                addSimpleMessage("Keputusan Adalah Rujuk Mahkahmah.Sila Klik Butang Selesai...");
                button = true;
                return new JSP("lelong/senarai_jurulelong_berlesen.jsp").addParameter("tab", "true");
            }
        }
        return new JSP("lelong/senarai_jurulelong_berlesen.jsp").addParameter("tab", "true");
    }

    public Resolution showForm3() {
        show = true;
        FasaPermohonan fasa = lelongService.findFasaPermohonanRekodBidaan(idPermohonan);
        if (fasa != null) {
            lelongService.delete(fasa);
        }
        display = true;
        List<FasaPermohonan> mohonFasa = lelongService.getPermonanFasa2(idPermohonan);
        FasaPermohonan ff = mohonFasa.get(0);

        if (ff.getKeputusan() == null) {
            LOG.info("----null-----");
            addSimpleError("Keputusan Belum Dibuat.Sila Pilih Keputusan Di Tab Keputusan.");
            button = true;
            return new JSP("lelong/senarai_jurulelong_berlesen.jsp").addParameter("tab", "true");
        } else {
            button = false;
            if (ff.getKeputusan().getKod().equals("PH")) {
                LOG.info("-----Pentadbir Tanah-------");
                for (Lelongan ll : lelonganList) {
                    ll.setSytJuruLelong(null);
                    lelongService.saveOrUpdate(ll);
                }
                flag = false;
                jual = true;
                rehydrate();
                return new JSP("lelong/senarai_jurulelong_berlesen.jsp").addParameter("tab", "true");
            }
            if (ff.getKeputusan().getKod().equals("JL")) {
                LOG.info("------Jurulelong------");
                flag = true;
                return new JSP("lelong/senarai_jurulelong_berlesen.jsp").addParameter("tab", "true");
            }
        }
        return new JSP("lelong/senarai_jurulelong_berlesen.jsp").addParameter("tab", "true");
    }

    public Resolution showFormPerihalTanah() {
        show = true;
//        FasaPermohonan fasa = lelongService.findFasaPermohonanRekodBidaan(idPermohonan);
//        if (fasa != null) {
//            lelongService.delete(fasa);
//        }
        FasaPermohonan fasaPermohonan = lelongService.findFasaPermohonanRekodBidaan(permohonan.getIdPermohonan());
        if (fasaPermohonan != null) {
            FasaPermohonanLog fasaPermohonanLog = lelongService.findFasaPermohonanLog(fasaPermohonan);
            if (fasaPermohonanLog != null) {
                lelongService.deletetest(fasaPermohonanLog, fasaPermohonan);
            }
        }
        display = true;
        if (permohonan.getPermohonanSebelum() == null) {
            mohonFasa = lelongService.getPermonanFasa2(idPermohonan);
        } else {
            mohonFasa = lelongService.getPermonanFasa2(permohonan.getPermohonanSebelum().getIdPermohonan());
        }
        FasaPermohonan ff = mohonFasa.get(0);

        if (ff.getKeputusan() == null) {
            LOG.info("----null-----");
            addSimpleError("Keputusan Belum Dibuat.Sila Pilih Keputusan Di Tab Keputusan.");
            button = true;
            return new JSP("lelong/senarai_jurulelong_berlesen_perihal_tanah_MLK.jsp").addParameter("tab", "true");
        } else {
            button = false;
            if (ff.getKeputusan().getKod().equals("PH")) {
                LOG.info("-----Pentadbir Tanah-------");
                for (Lelongan ll : lelonganList) {
                    ll.setSytJuruLelong(null);
                    lelongService.saveOrUpdate(ll);
                }

                flag = false;
                rehydrate();
                return new JSP("lelong/senarai_jurulelong_berlesen_perihal_tanah_MLK.jsp").addParameter("tab", "true");
            }
            if (ff.getKeputusan().getKod().equals("JL")) {
                LOG.info("------Jurulelong------");
                flag = true;
                return new JSP("lelong/senarai_jurulelong_berlesen_perihal_tanah_MLK.jsp").addParameter("tab", "true");
            }
        }
        return new JSP("lelong/senarai_jurulelong_berlesen_perihal_tanah_MLK.jsp").addParameter("tab", "true");
    }

    public Resolution showForm2() {
        LOG.info("----showform2-----");
        show = true;
        stageJurulelongN9 = false;
        List<FasaPermohonan> mohonFasa = lelongService.getPermonanFasa2(idPermohonan);
        FasaPermohonan ff = mohonFasa.get(0);
        if ("05".equals(conf.getProperty("kodNegeri"))) {
            //negeri9
            mlk = false;
        }
        if (ff.getKeputusan() == null) {
            LOG.info("----null-----");
            addSimpleError("Keputusan Belum Dibuat.Sila Pilih Keputusan Di Tab Keputusan.");
            button = true;
            return new JSP("lelong/senarai_jurulelong_berlesen.jsp").addParameter("tab", "true");
        } else {
            button = false;
            if (ff.getKeputusan().getKod().equals("PH")) {
                LOG.info("-----Pentadbir Tanah-------");
                for (Lelongan ll : lelonganList) {
                    ll.setSytJuruLelong(null);
                    lelongService.saveOrUpdate(ll);
                }
                flag = false;
                rehydrate();
                return new JSP("lelong/senarai_jurulelong_berlesen.jsp").addParameter("tab", "true");
            }
            if (ff.getKeputusan().getKod().equals("JL")) {
                LOG.info("------Jurulelong------");

                flag = true;
                return new JSP("lelong/senarai_jurulelong_berlesen.jsp").addParameter("tab", "true");
            }
        }

        return new JSP("lelong/senarai_jurulelong_berlesen.jsp").addParameter("tab", "true");
    }

    public Resolution showForm2PPTL() {
        LOG.info("----showform2PPTL-----");
        show = true;
        stageJurulelongN9 = false;

        FasaPermohonan ff = null;

        if ("05".equals(conf.getProperty("kodNegeri"))) {
            ff = lelongService.findFasaKpsnTangguh(idPermohonan);

        } else {
            ff = lelongService.findlantikanPelelong(idPermohonan);

        }
        if ("05".equals(conf.getProperty("kodNegeri"))) {
            //negeri9
            mlk = false;
        }
        if (ff.getKeputusan() == null) {
            LOG.info("----null-----");
            addSimpleError("Keputusan Belum Dibuat.Sila Pilih Keputusan Di Tab Keputusan.");
            button = true;
            return new JSP("lelong/senarai_jurulelong_berlesen.jsp").addParameter("tab", "true");
        } else {
            button = false;
            if (ff.getKeputusan().getKod().equals("PH")) {
                LOG.info("-----Pentadbir Tanah-------");
                for (Lelongan ll : lelonganList) {
                    ll.setSytJuruLelong(null);
                    lelongService.saveOrUpdate(ll);
                }
                flag = false;
                rehydrate();
                return new JSP("lelong/senarai_jurulelong_berlesen.jsp").addParameter("tab", "true");
            }
            if (ff.getKeputusan().getKod().equals("JL")) {
                LOG.info("------Jurulelong------");

                flag = true;
                return new JSP("lelong/senarai_jurulelong_berlesen.jsp").addParameter("tab", "true");
            }
        }



        return new JSP("lelong/senarai_jurulelong_berlesen.jsp").addParameter("tab", "true");
    }

    public Resolution showFormStageJurulelong() {
        LOG.info("----showformStageJurulelong-----");
        show = true;
        stageJurulelongN9 = true;
        List<FasaPermohonan> mohonFasa = lelongService.getPermonanFasa2(idPermohonan);
        FasaPermohonan ff = mohonFasa.get(0);
        if ("05".equals(conf.getProperty("kodNegeri"))) {
            //negeri9
            mlk = false;
        }
        if (ff.getKeputusan() == null) {
            LOG.info("----null-----");
            addSimpleError("Keputusan Belum Dibuat.Sila Pilih Keputusan Di Tab Keputusan.");
            button = true;
            return new JSP("lelong/senarai_jurulelong_berlesen.jsp").addParameter("tab", "true");
        } else {
            button = false;
            if (ff.getKeputusan().getKod().equals("PH")) {
                LOG.info("-----Pentadbir Tanah-------");
                for (Lelongan ll : lelonganList) {
                    ll.setSytJuruLelong(null);
                    lelongService.saveOrUpdate(ll);
                }
                flag = false;
                rehydrate();
                return new JSP("lelong/senarai_jurulelong_berlesen.jsp").addParameter("tab", "true");
            }
            if (ff.getKeputusan().getKod().equals("JL")) {
                LOG.info("------Jurulelong------");

                flag = true;
                return new JSP("lelong/senarai_jurulelong_berlesen.jsp").addParameter("tab", "true");
            }
        }

        return new JSP("lelong/senarai_jurulelong_berlesen.jsp").addParameter("tab", "true");
    }

    public Resolution showFormStageJurulelongPPTL() {
        LOG.info("----showformStageJurulelongPPTL-----");
        show = true;
        stageJurulelongN9 = true;
        FasaPermohonan ff = lelongService.findlantikanPelelong(idPermohonan);
        if ("05".equals(conf.getProperty("kodNegeri"))) {
            //negeri9
            mlk = false;
        }
        if (ff.getKeputusan() == null) {
            LOG.info("----null-----");
            addSimpleError("Keputusan Belum Dibuat.Sila Pilih Keputusan Di Tab Keputusan.");
            button = true;
            return new JSP("lelong/senarai_jurulelong_berlesen.jsp").addParameter("tab", "true");
        } else {
            button = false;
            if (ff.getKeputusan().getKod().equals("PH")) {
                LOG.info("-----Pentadbir Tanah-------");
                for (Lelongan ll : lelonganList) {
                    ll.setSytJuruLelong(null);
                    lelongService.saveOrUpdate(ll);
                }
                flag = false;
                rehydrate();
                return new JSP("lelong/senarai_jurulelong_berlesen.jsp").addParameter("tab", "true");
            }
            if (ff.getKeputusan().getKod().equals("JL")) {
                LOG.info("------Jurulelong------");

                flag = true;
                return new JSP("lelong/senarai_jurulelong_berlesen.jsp").addParameter("tab", "true");
            }
        }

        return new JSP("lelong/senarai_jurulelong_berlesen.jsp").addParameter("tab", "true");
    }

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {
        LOG.info("rehydrate start");
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);

        //added by syazwan(21/10/2013)
        //show sejarah jurulelong
        lelonganListJLB = lelongService.listLelonganTB(idPermohonan);
            
        if (idPermohonan != null) {
            permohonan = permohonanDAO.findById(idPermohonan);

            if (permohonan.getPermohonanSebelum() == null) {
                enkuiri = lelongService.findEnkuiri(idPermohonan);
            } else {
                List<PermohonanAsal> list2 = lelongService.listMohonAsal2(permohonan.getPermohonanSebelum().getIdPermohonan());
                if (list2.isEmpty()) {
                    enkuiri = lelongService.findEnkuiri(permohonan.getPermohonanSebelum().getIdPermohonan());
                    if (enkuiri == null) {
                        List<Enkuiri> list3 = lelongService.getAllDesc(permohonan.getPermohonanSebelum().getIdPermohonan());
                        if (!list3.isEmpty()) {
                            enkuiri = list3.get(0);
                        }
                    }
                } else {
                    enkuiri = lelongService.findEnkuiri(list2.get(0).getIdPermohonanAsal().getIdPermohonan());
                    if (enkuiri == null) {
                        List<Enkuiri> list3 = lelongService.getAllDesc(list2.get(0).getIdPermohonanAsal().getIdPermohonan());
                        if (!list3.isEmpty()) {
                            enkuiri = list3.get(0);
                        }
                    }
                }
            }

            if (permohonan.getKodUrusan().getKod().equals("PPTL")) {
                lelonganList = lelongService.listLelonganAK(permohonan.getPermohonanSebelum().getIdPermohonan());
            } else {
                lelonganList = lelongService.listLelonganAK(idPermohonan);
            }



            if ("05".equals(conf.getProperty("kodNegeri"))) {
                mlk = false;
                lelonganList2 = new ArrayList<Lelongan>();
                if (!lelonganList.isEmpty()) {
                    lelonganList2.add(lelonganList.get(0));
                    lelongan = lelonganList2.get(0);
                    if (lelongan.getSytJuruLelong() != null) {
                        flag = true;
                    } else {
                        flag = false;
                    }
                    LOG.info("lelonganList.size() : " + lelonganList2.size());
                }
            } else if ("04".equals(conf.getProperty("kodNegeri"))) {
                mlk = true;
                lelonganList3 = new ArrayList<Lelongan>();
                if (!lelonganList.isEmpty()) {
                    lelonganList3.add(lelonganList.get(0));
                    lelongan = lelonganList3.get(0);
                    if (lelongan.getJurulelong() != null) {
                        flag = true;
                    } else {
                        flag = false;
                    }
                    LOG.info("lelonganList3.size() : " + lelonganList3.size());
                }
            }


            if ("04".equals(conf.getProperty("kodNegeri"))) {
                if (enkuiri.getCaraLelong().equals("B")) {
                    if (StringUtils.isNotEmpty(enkuiri.getPerihalTanah())) {
                        jual = true;
                        perihal = enkuiri.getPerihalTanah();
                    } else {
                        jual = false;
                    }
                    getContext().getRequest().setAttribute("B", Boolean.TRUE);
                    getContext().getRequest().setAttribute("A", Boolean.FALSE);

                }
                if (enkuiri.getCaraLelong().equals("A")) {
                    getContext().getRequest().setAttribute("A", Boolean.TRUE);
                    getContext().getRequest().setAttribute("B", Boolean.FALSE);
                    for (Lelongan ll : lelonganList) {
                        if (StringUtils.isNotEmpty(ll.getPerihalTanah())) {
                            jual = true;
                        } else {
                            jual = false;
                        }
                    }
                }
            }
        }
        if ("05".equals(conf.getProperty("kodNegeri"))) {
            //negeri9
            mlk = false;
            for (Lelongan ll : lelonganList) {
                if (ll.getSytJuruLelong() == null) {
                    getContext().getRequest().setAttribute("jurulelong", Boolean.FALSE);
                    break;
                } else {
                    getContext().getRequest().setAttribute("jurulelong", Boolean.TRUE);
                    break;
                }
            }
        }
    }

    public Resolution batal() {
        if ("05".equals(conf.getProperty("kodNegeri"))) {
            for (Lelongan ll : lelonganList) {
                ll.setSytJuruLelong(null);
                lelongService.saveOrUpdate(ll);
            }
        } else if ("04".equals(conf.getProperty("kodNegeri"))) {
            for (Lelongan ll : lelonganList) {
                ll.setJurulelong(null);
                lelongService.saveOrUpdate(ll);
            }
        }
        rehydrate();
        flag = true;
        return new JSP("lelong/senarai_jurulelong_berlesen.jsp").addParameter("tab", "true");
    }

    public Resolution perlantikkan_jurulelong_berlesen() {
        return new JSP("lelong/senarai_jurulelong_berlesen.jsp").addParameter("tab", "true");
    }

    public Resolution pilihPopup() {
        return new JSP("lelong/senarai_pelelong_berlesen.jsp").addParameter("popup", "true").addParameter("showForm", "false");
    }

    public Resolution getSenaraiJurulelongBerlesan() {
        //list utk pilih jurulelong

        if ("05".equals(conf.getProperty("kodNegeri"))) {
            mlk = false;
            list = new ArrayList<SytJuruLelong>();
            list = sytJuruLelongDAO.findAll();
            LOG.info("list.size() : " + list.size());
            return new JSP("lelong/senarai_pelelong_berlesen.jsp").addParameter("popup", "true");
        } else {
            if ("04".equals(conf.getProperty("kodNegeri"))) {
                mlk = true;
                listJurulelong = new ArrayList<JuruLelong>();
//                listJurulelong = jurulelongDAO.findAll();
                listJurulelong = lelongService.findJuruLelong();
                LOG.info("listJurulelong.size() : " + listJurulelong.size());
                return new JSP("lelong/senarai_pelelong_berlesen.jsp").addParameter("popup", "true");
            }
            return new JSP("lelong/senarai_pelelong_berlesen.jsp").addParameter("popup", "true");
        }
    }

    public Resolution refreshPage2() {
        LOG.info("refresh..");
        flag = true;
        rehydrate();
        addSimpleMessage("Maklumat telah berjaya disimpan..");
        return new JSP("lelong/senarai_jurulelong_berlesen.jsp").addParameter("tab", "true");
    }

    public Resolution simpanPemohon() {
        //lantik jurulelong
        LOG.info("------simpan-------");
        String idjlb = getContext().getRequest().getParameter("jurulel");
        LOG.info("----------idjlb :" + idjlb);
        if (idjlb == null) {
            addSimpleError("Sila Pilih Jurulelong");
            return new JSP("lelong/senarai_jurulelong_berlesen.jsp").addParameter("tab", "true");
        }
        LOG.info("idjlb : " + idjlb);
        SytJuruLelong juru = sytJuruLelongDAO.findById(Long.parseLong(idjlb));
        LOG.info("JuruLelong : " + juru.getIdSytJlb());
        if (idjlb != null) {
            for (Lelongan ll : lelonganList) {
                ll.setSytJuruLelong(juru);
                lelongService.saveOrUpdate(ll);
                setFlag(true);
            }
        }
        if ("05".equals(conf.getProperty("kodNegeri"))) {
            //negeri9
            mlk = false;
        }
        rehydrate();
        getContext().getRequest().setAttribute("jurulelong", Boolean.TRUE);
        addSimpleMessage("Maklumat telah berjaya disimpan..");
        return new JSP("lelong/senarai_jurulelong_berlesen.jsp").addParameter("tab", "true");
    }

    //for melaka only
    public Resolution simpanPemohonMelaka() {
        //lantik jurulelong
        LOG.info("------simpan-------");
        String idjlb = getContext().getRequest().getParameter("jurulel");
        LOG.info("----------idjlb :" + idjlb);
        if (idjlb == null) {
            addSimpleError("Sila Pilih Jurulelong");
            return new JSP("lelong/senarai_jurulelong_berlesen.jsp").addParameter("tab", "true");
        }
        LOG.info("idjlb : " + idjlb);
        JuruLelong jurulelong = jurulelongDAO.findById(Long.parseLong(idjlb));
        LOG.info("JuruLelong : " + jurulelong.getIdJlb());
        if (idjlb != null) {
            for (Lelongan ll : lelonganList) {
                ll.setJurulelong(jurulelong);
                lelongService.saveOrUpdate(ll);
                setFlag(true);
            }
        }
        if ("05".equals(conf.getProperty("kodNegeri"))) {
            //negeri9
            mlk = false;
        }
        rehydrate();
        getContext().getRequest().setAttribute("jurulelong", Boolean.TRUE);
        addSimpleMessage("Maklumat telah berjaya disimpan..");
        return new JSP("lelong/senarai_jurulelong_berlesen.jsp").addParameter("tab", "true");
    }

    public Resolution kembali() {

        FasaPermohonan senaraifasamohon = null;

//        List<FasaPermohonan> senaraifasamohon = lelongService.getPermonanFasa2(idPermohonan);
//        FasaPermohonan fas = senaraifasamohon.get(0);
//        fas.setKeputusan(null);
//        fas.setUlasan(null);
//        lelongService.saveUpdate2(fas);

        if ("05".equals(conf.getProperty("kodNegeri"))) {
            senaraifasamohon = lelongService.findFasaKpsnTangguh(idPermohonan);

        } else {
            senaraifasamohon = lelongService.findlantikanPelelong(idPermohonan);

        }

        senaraifasamohon.setKeputusan(null);
        senaraifasamohon.setUlasan(null);
        lelongService.saveUpdate2(senaraifasamohon);
        rehydrate();
        showForm();
        return new JSP("/lelong/tggh_kpsn_lantik_jurulelong.jsp").addParameter("tab", "true");
    }

    public Resolution simpanKpsn() {
        List<FasaPermohonan> senaraifasamohon = lelongService.getPermonanFasa2(idPermohonan);
        LOG.info("senaraifasamohon : " + senaraifasamohon.size());
        FasaPermohonan fas = null;
        InfoAudit ia = new InfoAudit();
        ia.setDimasukOleh(pengguna);
        ia.setTarikhMasuk(new java.util.Date());
        ia.setDiKemaskiniOleh(pengguna);
        ia.setTarikhKemaskini(new java.util.Date());
        if (!senaraifasamohon.isEmpty()) {
            fas = senaraifasamohon.get(0);
            fas.setInfoAudit(ia);
        } else {
            fas = new FasaPermohonan();
            fas.setInfoAudit(ia);
            fas.setPermohonan(permohonan);
            fas.setIdAliran("semak16HLantikJurulelong");
            fas.setCawangan(pengguna.getKodCawangan());
            fas.setIdPengguna(pengguna.getIdPengguna());
        }
        String kpsn = getContext().getRequest().getParameter("kpsn");
        String ulasan = getContext().getRequest().getParameter("ulasan");
        LOG.info("kpsn : " + kpsn);
        LOG.info("ulasan : " + ulasan);
        KodKeputusan kos = kodKeputusanDAO.findById(kpsn);
        fas.setKeputusan(kos);
        if (ulasan != null) {
            fas.setUlasan(ulasan);
        }
        fas.setTarikhHantar(new Date());
        fas.setTarikhKeputusan(new Date());
        lelongService.saveUpdate2(fas);
        rehydrate();
        showForm();
        return new JSP("/lelong/senarai_jurulelong_berlesen.jsp").addParameter("tab", "true");
    }

    public Resolution simpanKpsnPPTL() {
        FasaPermohonan fas = lelongService.findFasaKpsnTangguh(idPermohonan);
        InfoAudit ia = new InfoAudit();
        ia.setDimasukOleh(pengguna);
        ia.setTarikhMasuk(new java.util.Date());
        ia.setDiKemaskiniOleh(pengguna);
        ia.setTarikhKemaskini(new java.util.Date());
        fas.setInfoAudit(ia);
        fas.setPermohonan(permohonan);

        String kpsn = getContext().getRequest().getParameter("kpsn");
        String ulasan = getContext().getRequest().getParameter("ulasan");
        LOG.info("kpsn : " + kpsn);
        LOG.info("ulasan : " + ulasan);
        KodKeputusan kos = kodKeputusanDAO.findById(kpsn);
        fas.setKeputusan(kos);
        if (ulasan != null) {
            fas.setUlasan(ulasan);
        }
        fas.setTarikhHantar(new Date());
        fas.setTarikhKeputusan(new Date());
        lelongService.saveUpdate2(fas);
        rehydrate();
        showForm2PPTL();
        return new JSP("/lelong/senarai_jurulelong_berlesen.jsp").addParameter("tab", "true");
    }

    public Resolution simpanPerihal() {
        if (enkuiri.getCaraLelong().equals("B")) {
            enkuiri.setPerihalTanah(perihal);
            lelongService.saveOrUpdate(enkuiri);
        }
        if (enkuiri.getCaraLelong().equals("A")) {
            for (Lelongan ll : lelonganList) {
                lelongService.saveOrUpdate(ll);
            }
        }
        jual = true;
        showFormPerihalTanah();
        rehydrate();
        addSimpleMessage("Maklumat telah berjaya disimpan..");
        return new JSP("/lelong/senarai_jurulelong_berlesen_perihal_tanah_MLK.jsp").addParameter("tab", "true");
    }

    public Resolution genReport() {
        LOG.info("genReport :: start");
        LOG.info("generate report start.");
        String msg = "Laporan telah dijana. Sila buat semakan sebelum cetakan.";

        try {
            String gen = "LLGSrtLantikanJurulelong_MLK.rdf";
            String code = "LEL";
            LOG.info("genReportFromXML");
            lelongService.reportGen(permohonan, gen, code, pengguna);
        } catch (Exception ex) {
            return new StreamingResolution("text/plain", "Dokumen Tidak Dapat Dijana." + ex.getMessage());
        }
        LOG.info("genReport :: finish");
        return new StreamingResolution("text/plain", msg);
    }

    public Resolution genReportJual() {
        LOG.info("genReport :: start");
        LOG.info("generate report start.");
        String msg = "Laporan telah dijana. Sila buat semakan sebelum cetakan.";

        try {
            String gen = "LLGIsytiharJual_MLK.rdf";
            String code = "PJ";
            LOG.info("genReportFromXML");
            lelongService.reportGen(permohonan, gen, code, pengguna);
        } catch (Exception ex) {
            return new StreamingResolution("text/plain", "Dokumen Tidak Dapat Dijana." + ex.getMessage());
        }
        LOG.info("genReport :: finish");
        return new StreamingResolution("text/plain", msg);
    }

    public Resolution genReport3() {
        LOG.info("genReport :: start");
        LOG.info("generate report start.");
        String msg = "Laporan telah dijana. Sila buat semakan sebelum cetakan.";

        try {
            String gen = "LLGNotisLelonganAwam_NS.rdf";
            String code = "NL";
            LOG.info("genReportFromXML");
            lelongService.reportGen(permohonan, gen, code, pengguna);
        } catch (Exception ex) {
            return new StreamingResolution("text/plain", "Dokumen Tidak Dapat Dijana." + ex.getMessage());
        }
        LOG.info("genReport :: finish");
        return new StreamingResolution("text/plain", msg);
    }

    public Resolution genReport2() {
        LOG.info("genReport :: start");
        LOG.info("generate report start.");
        String msg = "Laporan telah dijana. Sila buat semakan sebelum cetakan.";

        try {
            String gen = "LLGSrtLantikanJurulelong_NS.rdf";
            String code = "LEL";
            LOG.info("genReportFromXML");
            lelongService.reportGen(permohonan, gen, code, pengguna);

            gen = "LLGNotisLelonganAwam_NS.rdf";
            code = "NL";
            LOG.info("genReportFromXML");
            lelongService.reportGen(permohonan, gen, code, pengguna);

        } catch (Exception ex) {
            return new StreamingResolution("text/plain", "Dokumen Tidak Dapat Dijana." + ex.getMessage());
        }
        LOG.info("genReport :: finish");
        return new StreamingResolution("text/plain", msg);
    }

    public List<SytJuruLelong> getList() {
        return list;
    }

    public void setList(List<SytJuruLelong> list) {
        this.list = list;
    }

    public Permohonan getPermohonan() {
        return permohonan;
    }

    public void setPermohonan(Permohonan permohonan) {
        this.permohonan = permohonan;
    }

    public Enkuiri getEnkuiri() {
        return enkuiri;
    }

    public void setEnkuiri(Enkuiri enkuiri) {
        this.enkuiri = enkuiri;
    }

    public Lelongan getLelongan() {
        return lelongan;
    }

    public void setLelongan(Lelongan lelongan) {
        this.lelongan = lelongan;
    }

    public List<Lelongan> getLelonganList() {
        return lelonganList;
    }

    public void setLelonganList(List<Lelongan> lelonganList) {
        this.lelonganList = lelonganList;
    }

    public String getIdPermohonan() {
        return idPermohonan;
    }

    public void setIdPermohonan(String idPermohonan) {
        this.idPermohonan = idPermohonan;
    }

    public boolean isDisplay() {
        return display;
    }

    public void setDisplay(boolean display) {
        this.display = display;
    }

    public List<Lelongan> getLelonganList2() {
        return lelonganList2;
    }

    public void setLelonganList2(List<Lelongan> lelonganList2) {
        this.lelonganList2 = lelonganList2;
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public boolean isButton() {
        return button;
    }

    public void setButton(boolean button) {
        this.button = button;
    }

    public LelongService getLelongService() {
        return lelongService;
    }

    public void setLelongService(LelongService lelongService) {
        this.lelongService = lelongService;
    }

    public Pengguna getPengguna() {
        return pengguna;
    }

    public void setPengguna(Pengguna pengguna) {
        this.pengguna = pengguna;
    }

    public Configuration getConf() {
        return conf;
    }

    public void setConf(Configuration conf) {
        this.conf = conf;
    }

    public boolean isShow() {
        return show;
    }

    public void setShow(boolean show) {
        this.show = show;
    }

    public boolean isMlk() {
        return mlk;
    }

    public void setMlk(boolean mlk) {
        this.mlk = mlk;
    }

    public boolean isJual() {
        return jual;
    }

    public void setJual(boolean jual) {
        this.jual = jual;
    }

    public String getPerihal() {
        return perihal;
    }

    public void setPerihal(String perihal) {
        this.perihal = perihal;
    }

    public List<JuruLelong> getListJurulelong() {
        return listJurulelong;
    }

    public void setListJurulelong(List<JuruLelong> listJurulelong) {
        this.listJurulelong = listJurulelong;
    }

    public List<Lelongan> getLelonganList3() {
        return lelonganList3;
    }

    public void setLelonganList3(List<Lelongan> lelonganList3) {
        this.lelonganList3 = lelonganList3;
    }
    
    public List<Lelongan> getLelonganListJLB() {
        return lelonganListJLB;
    }

    public void setLelonganListJLB(List<Lelongan> lelonganListJLB) {
        this.lelonganListJLB = lelonganListJLB;
    }

    public boolean isStageJurulelongN9() {
        return stageJurulelongN9;
    }

    public void setStageJurulelongN9(boolean stageJurulelongN9) {
        this.stageJurulelongN9 = stageJurulelongN9;
    }

    public List<FasaPermohonan> getMohonFasa() {
        return mohonFasa;
    }

    public void setMohonFasa(List<FasaPermohonan> mohonFasa) {
        this.mohonFasa = mohonFasa;
    }
}
