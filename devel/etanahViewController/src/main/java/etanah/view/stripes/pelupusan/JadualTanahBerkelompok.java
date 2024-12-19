/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.stripes.pelupusan;

/**
 *
 * @author Admin
 */
import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.model.Permohonan;
import etanah.model.Hakmilik;
import etanah.model.HakmilikPermohonan;
import etanah.model.PermohonanTuntutanKos;
import etanah.model.Pengguna;
import etanah.dao.PermohonanDAO;
import etanah.dao.HakmilikDAO;
import etanah.dao.HakmilikPermohonanDAO;
import etanah.dao.PermohonanTuntutanKosDAO;
import etanah.model.HakmilikUrusan;
import etanah.model.InfoAudit;
import etanah.model.KodCawangan;
import etanah.model.LaporanTanah;
import etanah.service.BPelService;
import etanah.service.LaporanTanahService;
import etanah.service.PelupusanService;
import etanah.view.etanahActionBeanContext;
import etanah.view.stripes.pelupusan.disClass.DisLaporanTanahService;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.RedirectResolution;
import net.sourceforge.stripes.controller.LifecycleStage;
import oracle.bpel.services.workflow.task.model.Task;
import org.apache.log4j.Logger;

@UrlBinding("/pelupusan/jadualTanahKelompok")
public class JadualTanahBerkelompok extends AbleActionBean {

    private String idPermohonan;
    private Permohonan permohonan;
    private Permohonan permohonanSebelum;
    private Permohonan permohonanForGSA;
    private Hakmilik hakmilik;
    private HakmilikPermohonan hakmilikPermohonan;
    private List<HakmilikPermohonan> ListMohonHakmilik;
    private List<HakmilikPermohonan> hakmilikPermohonanListPermohonanGSA;
    private List<HakmilikPermohonan> hakmilikPermohonanListPenamatanGSA;
    private String idPermohonanDigunakan;
    private String idHakmilikDigunakan;
    private String sebabPermohonan;
    private String pilihCarian;
    private LaporanTanah laporanTanah;
    private String stageId;
    private Pengguna pengguna;
    private int sizehakmilikPermohonanListPermohonanGSA;
    private static final Logger LOG = Logger.getLogger(JadualTanahBerkelompok.class);
    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    HakmilikDAO hakmilikDAO;
    @Inject
    PelupusanService pelupusanService;
    @Inject
    HakmilikPermohonanDAO hakmilikPermohonanDAO;
    @Inject
    DisLaporanTanahService disLaporanTanahService;

    @DefaultHandler
    public Resolution showForm() {
        return new JSP("pelupusan/gsa/jadual_tanah_kelompok.jsp").addParameter("tab", "true");
    }

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {

        String taskId = (String) getContext().getRequest().getSession().getAttribute("taskId");
        pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
//        stageId = stageId(taskId, pengguna);
        stageId = "01Kemasukan";
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = permohonanDAO.findById(idPermohonan);

        List<HakmilikPermohonan> hakmilikPermohonanTemp = null;
        if (permohonan != null) {
            if (permohonan.getPermohonanSebelum() != null) {
                permohonanSebelum = permohonan.getPermohonanSebelum();
                hakmilikPermohonanListPermohonanGSA = pelupusanService.getHakmilikPermohonan(permohonanSebelum.getIdPermohonan());
                hakmilikPermohonanTemp = pelupusanService.getHakmilikPermohonan(permohonanSebelum.getIdPermohonan());
                sizehakmilikPermohonanListPermohonanGSA = hakmilikPermohonanListPermohonanGSA.size();
                getContext().getRequest().setAttribute("cari", Boolean.FALSE);
                getContext().getRequest().setAttribute("gsa", Boolean.TRUE);
            } else {
                getContext().getRequest().setAttribute("cari", Boolean.TRUE);
            }

//            HakmilikPermohonan hp = pelupusanService.findByIdPermohonan(idPermohonan);
            hakmilikPermohonan = pelupusanService.findByIdPermohonan(idPermohonan);
            if (hakmilikPermohonan == null) {
                InfoAudit ia = new InfoAudit();
                HakmilikPermohonan hakM = new HakmilikPermohonan();
                hakM.setPermohonan(permohonan);
                hakM.setInfoAudit(ia);
                ia.setDimasukOleh(pengguna);
                ia.setTarikhMasuk(new java.util.Date());
                hakM.setInfoAudit(ia);
                hakM.setCawangan(permohonan.getCawangan());
                pelupusanService.saveOrUpdate(hakM);
            }
            hakmilikPermohonan = pelupusanService.findByIdPermohonan(idPermohonan);
            if (hakmilikPermohonan.getHakmilik() != null) {
                hakmilikPermohonanListPermohonanGSA = new ArrayList<HakmilikPermohonan>();
                permohonanForGSA = pelupusanService.findPermohonanBySebabForGSAIdMohon(idPermohonan);
                for (HakmilikPermohonan mohonHM : permohonanForGSA.getSenaraiHakmilik()) {
                    hakmilikPermohonanListPermohonanGSA.add(mohonHM);
                    idPermohonanDigunakan = permohonanForGSA.getIdPermohonan();
                    break;
                }
                hakmilikPermohonanListPenamatanGSA = pelupusanService.getHakmilikPermohonan(idPermohonan);


                if (hakmilikPermohonanListPenamatanGSA.size() > 0 && !hakmilikPermohonanListPenamatanGSA.isEmpty()) {

                    if (hakmilikPermohonanTemp != null) {
                        for (HakmilikPermohonan hpPBGSA : hakmilikPermohonanTemp) {
                            boolean checkExist = false;
                            for (HakmilikPermohonan hpPTGSA : hakmilikPermohonanListPenamatanGSA) {
                                if (hpPBGSA.getHakmilik() == hpPTGSA.getHakmilik()) {
                                    checkExist = true;
                                }
                            }
                            if (checkExist) {
                                hakmilikPermohonanListPermohonanGSA.remove(hpPBGSA);
                            }
                        }
                    }
                }

                sizehakmilikPermohonanListPermohonanGSA = hakmilikPermohonanListPermohonanGSA.size();
                getContext().getRequest().setAttribute("cari", Boolean.FALSE);
                getContext().getRequest().setAttribute("gsa", Boolean.TRUE);
            } else {
                getContext().getRequest().setAttribute("cari", Boolean.TRUE);
                getContext().getRequest().setAttribute("gsa", Boolean.FALSE);
            }

//            if (hakmilikPermohonanListPermohonanGSA != null) {
//                sizehakmilikPermohonanListPermohonanGSA = hakmilikPermohonanListPermohonanGSA.size();
//            }

        }
    }

    public Resolution carianPermohonan() {
        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        LOG.info("peng " + peng);
        String checkValue = getContext().getRequest().getParameter("pilihCarian");
        if (checkValue.equals("id")) {
            idPermohonanDigunakan = getContext().getRequest().getParameter("idPermohonanDigunakan");
            permohonanForGSA = permohonanDAO.findById(idPermohonanDigunakan);
            if (permohonanForGSA != null) {
                if (permohonanForGSA.getKodUrusan().getKod().equalsIgnoreCase("PBGSA")) {
                    hakmilikPermohonanListPermohonanGSA = pelupusanService.getHakmilikPermohonan(idPermohonanDigunakan);
                    sizehakmilikPermohonanListPermohonanGSA = hakmilikPermohonanListPermohonanGSA.size();
                    getContext().getRequest().setAttribute("gsa", Boolean.TRUE);
                    addSimpleMessage("Permohonan Dijumpai");
                } else if (permohonanForGSA.getKodUrusan().getKod().equalsIgnoreCase("PWGSA")) {
                    hakmilikPermohonanListPermohonanGSA = pelupusanService.getHakmilikPermohonan(idPermohonanDigunakan);
                    sizehakmilikPermohonanListPermohonanGSA = hakmilikPermohonanListPermohonanGSA.size();
                    getContext().getRequest().setAttribute("gsa", Boolean.TRUE);
                    addSimpleMessage("Permohonan Dijumpai");
                } else {
                    addSimpleError("Id Permohonan ini bukan urusan Permohonan Berkelompok GSA");
                }
            }
        } else if (checkValue.equals("sbb")) {
            sebabPermohonan = getContext().getRequest().getParameter("sebab");
            permohonanForGSA = pelupusanService.findPermohonanBySebabForGSA(sebabPermohonan);
            if (permohonanForGSA != null) {
                if (permohonanForGSA.getKodUrusan().getKod().equalsIgnoreCase("PBGSA")) {
                    idPermohonanDigunakan = permohonanForGSA.getIdPermohonan();
                    hakmilikPermohonanListPermohonanGSA = pelupusanService.getHakmilikPermohonan(permohonanForGSA.getIdPermohonan());
                    sizehakmilikPermohonanListPermohonanGSA = hakmilikPermohonanListPermohonanGSA.size();
                    getContext().getRequest().setAttribute("gsa", Boolean.TRUE);
                    addSimpleMessage("Permohonan Dijumpai");
                } else if (permohonanForGSA.getKodUrusan().getKod().equalsIgnoreCase("PWGSA")) {
                    idPermohonanDigunakan = permohonanForGSA.getIdPermohonan();
                    hakmilikPermohonanListPermohonanGSA = pelupusanService.getHakmilikPermohonan(permohonanForGSA.getIdPermohonan());
                    sizehakmilikPermohonanListPermohonanGSA = hakmilikPermohonanListPermohonanGSA.size();
                    getContext().getRequest().setAttribute("gsa", Boolean.TRUE);
                    addSimpleMessage("Permohonan Dijumpai");
                } else {
                    addSimpleError("Id Permohonan ini bukan urusan Permohonan Berkelompok GSA");
                }
            }
        } else if (checkValue.equals("hakmilik")) {
            idHakmilikDigunakan = getContext().getRequest().getParameter("idHakmilikDigunakan");
            idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
            permohonan = permohonanDAO.findById(idPermohonan);
            HakmilikUrusan hakmilikUrusan = new HakmilikUrusan();
            hakmilikUrusan = (HakmilikUrusan) disLaporanTanahService.findObject(hakmilikUrusan, new String[]{idHakmilikDigunakan, "IGSA"}, 0);
//            if (hakmilikUrusan != null) {
//                permohonanForGSA = pelupusanService.findPermohonanBySebabForGSA(hakmilikUrusan.getNoSidang());
            permohonanForGSA = pelupusanService.findPermohonanBySebabForGSAIdMohon(idPermohonan);
            if (permohonanForGSA != null) {
                if (permohonanForGSA.getKodUrusan().getKod().equalsIgnoreCase("PBGSA")) {
                    hakmilikPermohonanListPermohonanGSA = new ArrayList<HakmilikPermohonan>();
                    for (HakmilikPermohonan mohonHM : permohonanForGSA.getSenaraiHakmilik()) {
                        if (mohonHM.getHakmilik().getIdHakmilik().equals(idHakmilikDigunakan)) {
                            hakmilikPermohonanListPermohonanGSA.add(mohonHM);
                            idPermohonanDigunakan = permohonanForGSA.getIdPermohonan();
                            break;
                        }
                    }
                    sizehakmilikPermohonanListPermohonanGSA = hakmilikPermohonanListPermohonanGSA.size();
                    getContext().getRequest().setAttribute("gsa", Boolean.TRUE);
                    addSimpleMessage("Id Hakmilik Dijumpai");
                } else if (permohonanForGSA.getKodUrusan().getKod().equalsIgnoreCase("PWGSA")) {
                    hakmilikPermohonanListPermohonanGSA = new ArrayList<HakmilikPermohonan>();
                    for (HakmilikPermohonan mohonHM : permohonanForGSA.getSenaraiHakmilik()) {
                        if (mohonHM.getHakmilik().getIdHakmilik().equals(idHakmilikDigunakan)) {
                            hakmilikPermohonanListPermohonanGSA.add(mohonHM);
                            idPermohonanDigunakan = permohonanForGSA.getIdPermohonan();
                            break;
                        }
                    }
                    sizehakmilikPermohonanListPermohonanGSA = hakmilikPermohonanListPermohonanGSA.size();
                    getContext().getRequest().setAttribute("gsa", Boolean.TRUE);
                    addSimpleMessage("Id Hakmilik Dijumpai");
                } else if (permohonanForGSA.getKodUrusan().getKod().equalsIgnoreCase("PTGSA")) {
                    hakmilikPermohonanListPermohonanGSA = new ArrayList<HakmilikPermohonan>();
                    Hakmilik h = hakmilikDAO.findById(idHakmilikDigunakan);
                    if (permohonanForGSA.getSenaraiHakmilik() != null) {
                        hakmilikPermohonan = pelupusanService.findByIdPermohonan(idPermohonan);
                        InfoAudit ia = hakmilikPermohonan.getInfoAudit();
                        ia.setDiKemaskiniOleh(peng);
                        ia.setTarikhKemaskini(new java.util.Date());
                        hakmilikPermohonan.setInfoAudit(ia);
                        hakmilikPermohonan.setHakmilik(h);
                        pelupusanService.saveOrUpdate(hakmilikPermohonan);

                    }
                    for (HakmilikPermohonan mohonHM : permohonanForGSA.getSenaraiHakmilik()) {
                        if (mohonHM.getHakmilik().getIdHakmilik().equals(idHakmilikDigunakan)) {
                            hakmilikPermohonanListPermohonanGSA.add(mohonHM);
                            idPermohonanDigunakan = permohonanForGSA.getIdPermohonan();
                            break;
                        }
                    }
                    sizehakmilikPermohonanListPermohonanGSA = hakmilikPermohonanListPermohonanGSA.size();
                    getContext().getRequest().setAttribute("gsa", Boolean.TRUE);
                    addSimpleMessage("Id Hakmilik Dijumpai");
                } else {
                    addSimpleError("Id Hakmilik ini bukan urusan Permohonan Berkelompok GSA");
                }
            }
//            } else {
//                addSimpleError("Id Hakmilik ini bukan urusan Permohonan Berkelompok GSA. Sila pastikan Hakmilik ini sudah memohon urusan Pengisytiharan Tanah Berkelompok");
//            }

        } else {
            addSimpleError("Permohonan tidak dijumpai");
        }
        return new JSP("pelupusan/gsa/jadual_tanah_kelompok.jsp").addParameter("tab", "true");
    }

    public Resolution pilihTanah() {
        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        String idPermohonanSebelum = getContext().getRequest().getParameter("idPermohonanDigunakan");
        LOG.info("Id Mohon " + idPermohonanSebelum);
        if (idPermohonanSebelum != null) {
            permohonanSebelum = permohonanDAO.findById(idPermohonanSebelum);
            if (permohonanSebelum != null) {
                InfoAudit ia = permohonan.getInfoAudit();
                ia.setDiKemaskiniOleh(peng);
                ia.setTarikhKemaskini(new java.util.Date());
                permohonan.setInfoAudit(ia);
                permohonan.setPermohonanSebelum(permohonanSebelum);
                pelupusanService.simpanPermohonan(permohonan);
            }
        }
        String item = getContext().getRequest().getParameter("item");
        String[] listHakmilik = item.split(",");
        LOG.info("Size :" + listHakmilik.length);
        InfoAudit ia = new InfoAudit();
        ia.setDimasukOleh(peng);
        ia.setTarikhMasuk(new java.util.Date());
//        for (int i = 0; i < listHakmilik.length; i++) {
//
//            if (!listHakmilik[i].equals("T")) {
//                HakmilikPermohonan hakmilikPermohonan = new HakmilikPermohonan();
//                hakmilikPermohonan.setInfoAudit(ia);
//                hakmilikPermohonan.setPermohonan(permohonan);
//                Hakmilik hakmilik = hakmilikDAO.findById(listHakmilik[i]);
//                hakmilikPermohonan.setHakmilik(hakmilik);
//                pelupusanService.simpanHakmilikPermohonan(hakmilikPermohonan);
//            }
//        }
        rehydrate();
//        refreshPage();
        addSimpleMessage("Maklumat telah disimpan");
        //return new ForwardResolution("/WEB-INF/jsp/pelupusan/gsa/jadual_tanah_kelompok.jsp").addParameter("tab", "true");
//       return new RedirectResolution("/WEB-INF/jsp/pelupusan/jadual_tanah_kelompok.jsp").addParameter("tab", "true");
        return new JSP("pelupusan/gsa/jadual_tanah_kelompok.jsp").addParameter("tab", "true");
    }

    public Resolution carianTanahSemula() {
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        permohonan = permohonanDAO.findById(idPermohonan);
        hakmilikPermohonanListPermohonanGSA = new ArrayList<HakmilikPermohonan>();
        if (permohonan != null) {
            permohonanSebelum = permohonan.getPermohonanSebelum();
            if (permohonanSebelum != null) {
                InfoAudit ia = permohonan.getInfoAudit();
                ia.setDiKemaskiniOleh(peng);
                ia.setTarikhKemaskini(new java.util.Date());
                permohonan.setPermohonanSebelum(null);
                permohonan.setInfoAudit(ia);
                pelupusanService.simpanPermohonan(permohonan);
            }
            List<HakmilikPermohonan> hakmilikPermohonanTemp = pelupusanService.getHakmilikPermohonan(idPermohonan);
            if (!hakmilikPermohonanTemp.isEmpty()) {
                for (HakmilikPermohonan hp : hakmilikPermohonanTemp) {
                    laporanTanah = new LaporanTanah();
                    laporanTanah = pelupusanService.findLaporanTanahByIdMH(hp.getId());
                    if (laporanTanah != null) {
                        InfoAudit ia = laporanTanah.getInfoAudit();
                        ia.setDiKemaskiniOleh(peng);
                        ia.setTarikhKemaskini(new java.util.Date());
                        laporanTanah.setHakmilikPermohonan(null);
                        laporanTanah.setInfoAudit(ia);
                        pelupusanService.simpanLaporanTanah(laporanTanah);
                    }
//                    pelupusanService.deletePermohanHakmilik(hp);
                    InfoAudit ia = hp.getInfoAudit();
                    ia = peng.getInfoAudit();
                    ia.setDiKemaskiniOleh(peng);
                    ia.setTarikhKemaskini(new java.util.Date());
                    hp.setInfoAudit(ia);
                    pelupusanService.deleteHakmilikPermohonanByIdMHExecute(hp.getId());
                }
            }
        }
        rehydrate();
        return new JSP("pelupusan/gsa/jadual_tanah_kelompok.jsp").addParameter("tab", "true");
    }

    public Resolution deleteRow() {
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        String idhakmilikPermohan = getContext().getRequest().getParameter("idHakmilikPermohan");
        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        HakmilikPermohonan hp = hakmilikPermohonanDAO.findById(new Long(idhakmilikPermohan));
        ListMohonHakmilik = pelupusanService.getHakmilikPermohonanID(hp.getId());
        if (hp != null) {
            laporanTanah = new LaporanTanah();
            laporanTanah = pelupusanService.findLaporanTanahByIdMH(hp.getId());
            if (laporanTanah != null) {
                InfoAudit ia = laporanTanah.getInfoAudit();
                ia.setDiKemaskiniOleh(peng);
                ia.setTarikhKemaskini(new java.util.Date());
                laporanTanah.setHakmilikPermohonan(null);
                laporanTanah.setInfoAudit(ia);
                pelupusanService.simpanLaporanTanah(laporanTanah);
            }
            InfoAudit ia = hp.getInfoAudit();
            ia = peng.getInfoAudit();
            ia.setDiKemaskiniOleh(peng);
            ia.setTarikhKemaskini(new java.util.Date());
            hp.setInfoAudit(ia);
            pelupusanService.deleteHakmilikPermohonanByIdMHExecute(hp.getId());
        }

        rehydrate();
        addSimpleMessage("rekod telah dipadam");
        return new JSP("pelupusan/gsa/jadual_tanah_kelompok.jsp").addParameter("tab", "true");
    }

    public String stageId(String taskId, Pengguna pengguna) {
        BPelService service = new BPelService();
        stageId = null;
        if (org.apache.commons.lang.StringUtils.isNotBlank(taskId)) {
            Task t = null;
            t = service.getTaskFromBPel(taskId, pengguna);
//            stageId = t.getSystemAttributes().getStage();
            stageId = "01Kemasukan";
        }
        return stageId;
    }

    public String getStageId() {
        return stageId;
    }

    public void setStageId(String stageId) {
        this.stageId = stageId;
    }

    public Resolution popHakmilik() {
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        String idHakmilik = getContext().getRequest().getParameter("idHakmilik");
        hakmilik = hakmilikDAO.findById(idHakmilik);

        return new JSP("pelupusan/gsa/maklumat_hakmilik.jsp").addParameter("popup", "true");
    }

    public Resolution refreshPage() {
        LOG.info("START REFRESH");
        rehydrate();
        LOG.info("END REFRESH");
        return showForm();
    }

    public List<HakmilikPermohonan> getHakmilikPermohonanListPenamatanGSA() {
        return hakmilikPermohonanListPenamatanGSA;
    }

    public void setHakmilikPermohonanListPenamatanGSA(List<HakmilikPermohonan> hakmilikPermohonanListPenamatanGSA) {
        this.hakmilikPermohonanListPenamatanGSA = hakmilikPermohonanListPenamatanGSA;
    }

    public List<HakmilikPermohonan> getHakmilikPermohonanListPermohonanGSA() {
        return hakmilikPermohonanListPermohonanGSA;
    }

    public void setHakmilikPermohonanListPermohonanGSA(List<HakmilikPermohonan> hakmilikPermohonanListPermohonanGSA) {
        this.hakmilikPermohonanListPermohonanGSA = hakmilikPermohonanListPermohonanGSA;
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

    public Permohonan getPermohonanSebelum() {
        return permohonanSebelum;
    }

    public void setPermohonanSebelum(Permohonan permohonanSebelum) {
        this.permohonanSebelum = permohonanSebelum;
    }

    public String getIdPermohonanDigunakan() {
        return idPermohonanDigunakan;
    }

    public void setIdPermohonanDigunakan(String idPermohonanDigunakan) {
        this.idPermohonanDigunakan = idPermohonanDigunakan;
    }

    public String getPilihCarian() {
        return pilihCarian;
    }

    public void setPilihCarian(String pilihCarian) {
        this.pilihCarian = pilihCarian;
    }

    public Permohonan getPermohonanForGSA() {
        return permohonanForGSA;
    }

    public void setPermohonanForGSA(Permohonan permohonanForGSA) {
        this.permohonanForGSA = permohonanForGSA;
    }

    public String getSebabPermohonan() {
        return sebabPermohonan;
    }

    public void setSebabPermohonan(String sebabPermohonan) {
        this.sebabPermohonan = sebabPermohonan;
    }

    public int getSizehakmilikPermohonanListPermohonanGSA() {
        return sizehakmilikPermohonanListPermohonanGSA;
    }

    public void setSizehakmilikPermohonanListPermohonanGSA(int sizehakmilikPermohonanListPermohonanGSA) {
        this.sizehakmilikPermohonanListPermohonanGSA = sizehakmilikPermohonanListPermohonanGSA;
    }

    public Hakmilik getHakmilik() {
        return hakmilik;
    }

    public void setHakmilik(Hakmilik hakmilik) {
        this.hakmilik = hakmilik;
    }

    public LaporanTanah getLaporanTanah() {
        return laporanTanah;
    }

    public void setLaporanTanah(LaporanTanah laporanTanah) {
        this.laporanTanah = laporanTanah;
    }

    public String getIdHakmilikDigunakan() {
        return idHakmilikDigunakan;
    }

    public void setIdHakmilikDigunakan(String idHakmilikDigunakan) {
        this.idHakmilikDigunakan = idHakmilikDigunakan;
    }

    public HakmilikPermohonan getHakmilikPermohonan() {
        return hakmilikPermohonan;
    }

    public void setHakmilikPermohonan(HakmilikPermohonan hakmilikPermohonan) {
        this.hakmilikPermohonan = hakmilikPermohonan;
    }

    public List<HakmilikPermohonan> getListMohonHakmilik() {
        return ListMohonHakmilik;
    }

    public void setListMohonHakmilik(List<HakmilikPermohonan> ListMohonHakmilik) {
        this.ListMohonHakmilik = ListMohonHakmilik;
    }
}
