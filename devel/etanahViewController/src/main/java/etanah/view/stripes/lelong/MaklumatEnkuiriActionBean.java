/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.stripes.lelong;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.dao.KodStatusEnkuiriDAO;
import etanah.dao.KodCaraPenghantaranDAO;
import etanah.dao.PermohonanDAO;
import etanah.model.Enkuiri;
import etanah.model.FasaPermohonan;
import etanah.model.FolderDokumen;
import etanah.model.InfoAudit;
import etanah.model.KandunganFolder;
import etanah.model.KodCawangan;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.model.PermohonanAsal;
import etanah.model.KodCaraPenghantaran;
import etanah.model.Notis;
import etanah.service.common.EnkuiriService;
import etanah.service.common.LelongService;
import etanah.view.etanahActionBeanContext;
import java.text.SimpleDateFormat;
import java.util.List;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;
import org.apache.log4j.Logger;

/**
 *
 * @author mazurahayati.yusop
 */
@UrlBinding("/lelong/maklumat_enkuiri")
public class MaklumatEnkuiriActionBean extends AbleActionBean {

    private static final Logger LOG = Logger.getLogger(MaklumatEnkuiriActionBean.class);
    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    KodCaraPenghantaranDAO kodCarahantarDAO;
    @Inject
    EnkuiriService enService;
    @Inject
    LelongService lelongService;
    @Inject
    KodStatusEnkuiriDAO kodStatusEnkuiriDAO;
    @Inject
    CalenderManager manager;
    @Inject
    private etanah.Configuration conf;
    private Permohonan permohonan;
    private FasaPermohonan mohonfasa;
    private FasaPermohonan mohonfasa1;
    private KandunganFolder kandunganFolder;
    private Notis notiss;
    private List<Notis> listNotis;
    private Enkuiri enkuiri;
    private List<Enkuiri> senaraiEnkuiri3;
    private String tarikhEnkuiri;
    private String jam;
    private String minit;
    private String ampm;
    private String idPermohonan;
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm aaa");
    private Pengguna pengguna;
    private List<CalenderLelong> listCalender;
    private List<CalenderLelong> listCalender2;
    private boolean view = false;
    private String tarikhPermohonan;
    private String mohonFasa;
    private String mohonFasa1;

    @DefaultHandler
    public Resolution showForm() {
        return new JSP("lelong/maklumat_tetap_enkuiri.jsp").addParameter("tab", "true");
    }

    public Resolution showForm2() {
        return new JSP("lelong/tetap_enkuiri.jsp").addParameter("tab", "true");
    }

    public Resolution showForm2A() {
        LOG.info("showForm");
        return new JSP("lelong/tetap_enkuiri.jsp").addParameter("tab", "true");
    }

    public Resolution showForm3() {
        //calender
        listCalender = manager.getALLEnkuri(permohonan.getCawangan().getKod());
//        listCalender2 = manager.getALLLelongan(permohonan.getCawangan().getKod());
        return new JSP("lelong/calender_lelong.jsp").addParameter("popup", "true");
    }

    public Resolution showForm4() {
        view = true;
        return new JSP("lelong/maklumat_tetap_enkuiri.jsp").addParameter("tab", "true");
    }

    public Resolution showForm5() {
        view = true;
        return new JSP("lelong/view_keputusan_siasatan.jsp").addParameter("tab", "true");
    }

    public Resolution showForm6() {
        view = true;
        return new JSP("lelong/view_keputusan_tangguh.jsp").addParameter("tab", "true");
    }

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {
        LOG.info("rehydrate start");
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);

        if (idPermohonan != null) {
            permohonan = permohonanDAO.findById(idPermohonan);
            tarikhPermohonan = sdf.format(permohonan.getInfoAudit().getTarikhMasuk()).substring(0, 10);

            if (permohonan.getPermohonanSebelum() == null) {
                enkuiri = lelongService.findEnkuiri(idPermohonan);
                senaraiEnkuiri3 = lelongService.getEnkuiriNotAK(idPermohonan);
            } else {
                List<PermohonanAsal> list = lelongService.listMohonAsal2(permohonan.getPermohonanSebelum().getIdPermohonan());
                if (list.isEmpty()) {
                    enkuiri = lelongService.findEnkuiri(permohonan.getPermohonanSebelum().getIdPermohonan());
                    if (enkuiri == null) {
                        List<Enkuiri> list3 = lelongService.getAllDesc(permohonan.getPermohonanSebelum().getIdPermohonan());
                        if (!list3.isEmpty()) {
                            enkuiri = list3.get(0);
                        }
                    }
                    senaraiEnkuiri3 = lelongService.getEnkuiriNotAK(permohonan.getPermohonanSebelum().getIdPermohonan());
                } else {
                    enkuiri = lelongService.findEnkuiri(list.get(0).getIdPermohonanAsal().getIdPermohonan());
                    if (enkuiri == null) {
                        List<Enkuiri> list3 = lelongService.getAllDesc(list.get(0).getIdPermohonanAsal().getIdPermohonan());
                        if (!list3.isEmpty()) {
                            enkuiri = list3.get(0);
                        }
                    }
                    senaraiEnkuiri3 = lelongService.getEnkuiriNotAK(list.get(0).getIdPermohonanAsal().getIdPermohonan());

                }
            }

            if (enkuiri != null) {
                LOG.info("senaraiEnkuiri : " + enkuiri.getIdEnkuiri());
                if (enkuiri.getTarikhEnkuiri() != null) {
                    try {
                        tarikhEnkuiri = sdf.format(enkuiri.getTarikhEnkuiri()).substring(0, 10);
                        jam = sdf.format(enkuiri.getTarikhEnkuiri()).substring(11, 13);
                        minit = sdf.format(enkuiri.getTarikhEnkuiri()).substring(14, 16);
                        ampm = sdf.format(enkuiri.getTarikhEnkuiri()).substring(17, 19);
                    } catch (Exception ex) {
                        LOG.error(ex);
                    }
                }
            }
//            mohonfasa1 = lelongService.findFasaPermohonanSemakPermohonan(idPermohonan);
            List<FasaPermohonan> senaraiMohonFasa = lelongService.findFasaPermohonanSemakPermohonanList(idPermohonan);
            if (senaraiMohonFasa.size() > 0){
                mohonfasa1 = senaraiMohonFasa.get(0);
            }
            LOG.info("mohonfasa1 : " + mohonfasa1);
            mohonfasa = lelongService.findFasaKpsnTangguh(idPermohonan);

        }
        if ("04".equals(conf.getProperty("kodNegeri"))) {
            LOG.info("+++ kodNegeri = " + conf.getProperty("kodNegeri"));
            listNotis =lelongService.getNotisSSLList(permohonan.getIdPermohonan());
//            notiss = lelongService.getNotisSSL(permohonan.getIdPermohonan());
        }
    }

    public Resolution simpanEnkuiri() {
        LOG.info("---simpanEnkuiri---");
        if (enkuiri == null) {
            LOG.info("----nulll-------");
            addSimpleError("Sila Pilih Jenis Gadaian Dan Penggadai/Pemegang Gadaian Di Tab Maklumat Tanah/Pihak Berkepentingan");
            return new JSP("lelong/maklumat_tetap_enkuiri.jsp").addParameter("tab", "true");
        }
        InfoAudit ia = new InfoAudit();
        KodCawangan kc = pengguna.getKodCawangan();
        ia.setDimasukOleh(pengguna);
        ia.setTarikhMasuk(new java.util.Date());
        ia.setDiKemaskiniOleh(pengguna);
        ia.setTarikhKemaskini(new java.util.Date());
        String kodCaraPenghantaran = (String) getContext().getRequest().getParameter("kodCaraPenghantaran");

        LOG.info("permohonan :" + permohonan.getIdPermohonan());
        LOG.info("kodCaraPenghantaran :" + kodCaraPenghantaran);
        enkuiri.setPermohonan(permohonan);
        enkuiri.setCawangan(kc);
        enkuiri.setInfoAudit(ia);
        enkuiri.setStatus(kodStatusEnkuiriDAO.findById("AK"));
        LOG.info("tarikhEnkuiri : " + getContext().getRequest().getParameter("tarikhEnkuiri"));
        LOG.info("tarikhEnkuiri : " + tarikhEnkuiri);
        tarikhEnkuiri = tarikhEnkuiri + " " + jam + ":" + " " + minit + " " + ampm;
        LOG.debug("tarikhEnkuiri :" + tarikhEnkuiri);

        try {
            enkuiri.setTarikhEnkuiri(sdf.parse(tarikhEnkuiri));
        } catch (Exception ex) {
            LOG.error(ex);
        }

        enService.save(enkuiri);
        tarikhEnkuiri = sdf.format(enkuiri.getTarikhEnkuiri()).substring(0, 10);

        if (kodCaraPenghantaran != null) {
            kandunganFolder = lelongService.getListDokumenSSL(permohonan.getFolderDokumen().getFolderId());
            KodCaraPenghantaran kodCarahantar = new KodCaraPenghantaran();
            kodCarahantar = kodCarahantarDAO.findById(kodCaraPenghantaran);
            if (kandunganFolder != null) {

               notiss = lelongService.getNotisSSL(permohonan.getIdPermohonan());
                if (notiss == null) {

                    Notis notis = new Notis();
                    notis.setKodCaraPenghantaran(kodCarahantar);
                    notis.setPermohonan(permohonan);
                    notis.setInfoAudit(ia);
                    notis.setJabatan(permohonan.getKodUrusan().getJabatan());
                    notis.setDokumenNotis(kandunganFolder.getDokumen());
                    lelongService.saveOrUpdate(notis);
                } else {
                   notiss.setKodCaraPenghantaran(kodCarahantar);
                   notiss.setInfoAudit(ia);
                   lelongService.saveOrUpdate(notiss);
                }
            }
        }

        rehydrate();
        addSimpleMessage("Maklumat telah berjaya disimpan.");
        return new JSP("lelong/maklumat_tetap_enkuiri.jsp").addParameter("tab", "true");
    }

    public Resolution simpanEnkuiri2() {
        LOG.info("---simpanEnkuiri---");
        InfoAudit ia = new InfoAudit();
        KodCawangan kc = pengguna.getKodCawangan();
        ia.setDimasukOleh(pengguna);
        ia.setTarikhMasuk(new java.util.Date());
        ia.setDiKemaskiniOleh(pengguna);
        ia.setTarikhKemaskini(new java.util.Date());

        LOG.info("permohonan :" + permohonan.getIdPermohonan());
        enkuiri.setPermohonan(permohonan);
        enkuiri.setCawangan(kc);
        enkuiri.setInfoAudit(ia);

        enkuiri.setStatus(kodStatusEnkuiriDAO.findById("AK"));
        LOG.info("tarikhEnkuiri : " + getContext().getRequest().getParameter("tarikhEnkuiri"));
        LOG.info("tarikhEnkuiri : " + tarikhEnkuiri);
        tarikhEnkuiri = tarikhEnkuiri + " " + jam + ":" + " " + minit + " " + ampm;
        LOG.debug("tarikhEnkuiri :" + tarikhEnkuiri);

        try {
            enkuiri.setTarikhEnkuiri(sdf.parse(tarikhEnkuiri));
        } catch (Exception ex) {
            LOG.error(ex);
        }

        enService.save(enkuiri);
        tarikhEnkuiri = sdf.format(enkuiri.getTarikhEnkuiri()).substring(0, 10);

        LOG.info("genReport :: start");
        LOG.info("generate report start.");
        LOG.info("genReportFromXML");
        String gen = "LLGSuratSiasatan_NS.rdf";
        String code = "SSL";
        lelongService.reportGen(permohonan, gen, code, pengguna);

        FolderDokumen fd = permohonan.getFolderDokumen();
        List<KandunganFolder> listKD = lelongService.getListDokumenTG(fd.getFolderId());
        if (listKD.size() != 0) {
            gen = "LLGSuratTangguh_NS.rdf";
            code = "TG";
            lelongService.reportGen(permohonan, gen, code, pengguna);
        }
        LOG.info("genReport :: finish");

        rehydrate();
        addSimpleMessage("Maklumat telah berjaya disimpan.");
        return new JSP("lelong/maklumat_tetap_enkuiri.jsp").addParameter("tab", "true");
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

    public String getTarikhEnkuiri() {
        return tarikhEnkuiri;
    }

    public void setTarikhEnkuiri(String tarikhEnkuiri) {
        this.tarikhEnkuiri = tarikhEnkuiri;
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

    public String getIdPermohonan() {
        return idPermohonan;
    }

    public void setIdPermohonan(String idPermohonan) {
        this.idPermohonan = idPermohonan;
    }

    public List<Enkuiri> getSenaraiEnkuiri3() {
        return senaraiEnkuiri3;
    }

    public void setSenaraiEnkuiri3(List<Enkuiri> senaraiEnkuiri3) {
        this.senaraiEnkuiri3 = senaraiEnkuiri3;
    }

    public Pengguna getPengguna() {
        return pengguna;
    }

    public void setPengguna(Pengguna pengguna) {
        this.pengguna = pengguna;
    }

    public List<CalenderLelong> getListCalender() {
        return listCalender;
    }

    public void setListCalender(List<CalenderLelong> listCalender) {
        this.listCalender = listCalender;
    }

    public List<Notis> getListNotis() {
        return listNotis;
    }

    public void setListNotis(List<Notis> listNotis) {
        this.listNotis = listNotis;
    }
    
    

    public List<CalenderLelong> getListCalender2() {
        return listCalender2;
    }

    public void setListCalender2(List<CalenderLelong> listCalender2) {
        this.listCalender2 = listCalender2;
    }

    public boolean isView() {
        return view;
    }

    public void setView(boolean view) {
        this.view = view;
    }

    public String getTarikhPermohonan() {
        return tarikhPermohonan;
    }

    public void setTarikhPermohonan(String tarikhPermohonan) {
        this.tarikhPermohonan = tarikhPermohonan;
    }

    public FasaPermohonan getMohonfasa() {
        return mohonfasa;
    }

    public void setMohonfasa(FasaPermohonan mohonfasa) {
        this.mohonfasa = mohonfasa;
    }

    public String getMohonFasa() {
        return mohonFasa;
    }

    public void setMohonFasa(String mohonFasa) {
        this.mohonFasa = mohonFasa;
    }

    /**
     * @return the mohonfasa1
     */
    public FasaPermohonan getMohonfasa1() {
        return mohonfasa1;
    }

    /**
     * @param mohonfasa1 the mohonfasa1 to set
     */
    public void setMohonfasa1(FasaPermohonan mohonfasa1) {
        this.mohonfasa1 = mohonfasa1;
    }

    /**
     * @return the mohonFasa1
     */
    public String getMohonFasa1() {
        return mohonFasa1;
    }

    /**
     * @param mohonFasa1 the mohonFasa1 to set
     */
    public void setMohonFasa1(String mohonFasa1) {
        this.mohonFasa1 = mohonFasa1;
    }

    public Notis getNotiss() {
        return notiss;
    }

    public void setNotiss(Notis notiss) {
        this.notiss = notiss;
    }
}
