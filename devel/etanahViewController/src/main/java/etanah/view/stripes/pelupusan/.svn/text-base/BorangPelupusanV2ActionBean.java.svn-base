/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.stripes.pelupusan;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.dao.KodTuntutDAO;
import etanah.dao.PermohonanTuntutanKosDAO;
import etanah.model.InfoAudit;
import etanah.model.KodTuntut;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.model.PermohonanTuntutanKos;
import etanah.model.HakmilikPermohonan;
import etanah.service.BPelService;
import etanah.view.etanahActionBeanContext;
import etanah.view.stripes.pelupusan.disClass.DisBorangController;
import etanah.view.stripes.pelupusan.disClass.DisLaporanTanahService;
import etanah.view.stripes.pelupusan.disClass.DisPermohonanPage;
import java.math.BigDecimal;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpSession;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;
import oracle.bpel.services.workflow.task.model.Task;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.hibernate.Session;

/**
 *
 * @author afham
 */
@UrlBinding("/pelupusan/borangV2")
public class BorangPelupusanV2ActionBean extends AbleActionBean {

    @Inject
    DisLaporanTanahService disLaporanTanahService;
    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;
    @Inject
    etanah.Configuration conf;
    @Inject
    KodTuntutDAO kodTuntutDAO;
    @Inject
    PermohonanTuntutanKosDAO permohonanTuntutanKosDAO;
    private static final Logger LOG = Logger.getLogger(BorangPelupusanV2ActionBean.class);
    private static final boolean IS_LOG_DEBUG = LOG.isDebugEnabled();
    private PermohonanTuntutanKos permohonanTuntutanKos;
    private Permohonan permohonan;
    private Pengguna peng;
    private HakmilikPermohonan hakmilikPermohonan;
    private DisBorangController disBorangController;
    etanahActionBeanContext ctx;
    private List<PermohonanTuntutanKos> listPermohonanTuntutanKos;
    private List<KodTuntut> listKodTuntut;
    private List<HakmilikPermohonan> senaraiHakmilikPermohonan;
    private String idPermohonan;
    private String idTuntutanKos;
    private String kodNegeri;
    private String stageId;
    private int sizeKod;
    private BigDecimal total;

    @DefaultHandler
    public Resolution viewTambahBorang() {
        HttpSession httpSession = getContext().getRequest().getSession();
        disBorangController = new DisBorangController();
        disBorangController = disBorangController.editBorang();
        httpSession.setAttribute("disBorangController", disBorangController);
        return new JSP(DisPermohonanPage.getBORANG_MAIN_PAGE()).addParameter("tab", "true");
    }

    public Resolution viewOnlyBorang() {
        HttpSession httpSession = getContext().getRequest().getSession();
        disBorangController = new DisBorangController();
        disBorangController = disBorangController.viewBorang();
        httpSession.setAttribute("disBorangController", disBorangController);
        return new JSP(DisPermohonanPage.getBORANG_MAIN_PAGE()).addParameter("tab", "true");
    }

    @Before(stages = {LifecycleStage.BindingAndValidation}, on = {"!reload", "!simpanKeputusan"})
    public void rehydrate() {
        LOG.info("In rehydrate");
        ctx = (etanahActionBeanContext) getContext();
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        String taskId = (String) getContext().getRequest().getSession().getAttribute("taskId");
        Pengguna pguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        stageId = stageId(taskId, pguna);
        kodNegeri = disLaporanTanahService.getConf().getProperty("kodNegeri");
        peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);


        /**
         * Senarai Hakmilik
         */
        if (idPermohonan != null) {

            permohonan = new Permohonan();
            permohonan = (Permohonan) disLaporanTanahService.findObject(permohonan, new String[]{idPermohonan}, 0);

            listPermohonanTuntutanKos = permohonan.getSenaraiTuntutanKos();
            senaraiHakmilikPermohonan = permohonan.getSenaraiHakmilik();

            if (!listPermohonanTuntutanKos.isEmpty()) {
                total = BigDecimal.ZERO;
                for (PermohonanTuntutanKos ptk : listPermohonanTuntutanKos) {
                    total = total.add(ptk.getAmaunTuntutan());
                }
            }


        }

    }

    public Resolution openFrame() {
        ctx = (etanahActionBeanContext) getContext();
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        Pengguna pguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        String forwardJSP = new String();
        String type = ctx.getRequest().getParameter("type");
        kodNegeri = disLaporanTanahService.getConf().getProperty("kodNegeri");
        permohonan = new Permohonan();
        permohonan = (Permohonan) disLaporanTanahService.findObject(permohonan, new String[]{idPermohonan}, 0);

        if (StringUtils.isNotBlank(type)) {
            /*
             * FORWARD TO DIFFERENT POPUP
             */
            forwardJSP = refreshData(type);
        }
        return new JSP(forwardJSP).addParameter("popup", Boolean.TRUE);
    }

    public String stageId(String taskId, Pengguna pguna) {
        BPelService service = new BPelService();
        String stageId = null;
        if (StringUtils.isNotBlank(taskId)) {
            Task t = null;
            t = service.getTaskFromBPel(taskId, pguna);
            stageId = t.getSystemAttributes().getStage();
        }
        return stageId;
    }

    public String refreshData(String type) {
        String forwardJSP = new String();
        String kodDok = new String();
        int typeNum = type.equals("tBorang") ? 1
                : type.equals("main") ? 2
                : 0;

        switch (typeNum) {
            case 1:
                listPermohonanTuntutanKos = permohonan.getSenaraiTuntutanKos();
                if (!listPermohonanTuntutanKos.isEmpty()) {
                    total = BigDecimal.ZERO;
                    for (PermohonanTuntutanKos ptk : listPermohonanTuntutanKos) {
                        total = total.add(ptk.getAmaunTuntutan());
                    }
                }
                forwardJSP = DisPermohonanPage.getBORANG_EDIT_PAGE();
                break;
            case 2:
                rehydrate();
                disBorangController = (DisBorangController) getContext().getRequest().getSession().getAttribute("disBorangController");
                forwardJSP = DisPermohonanPage.getBORANG_MAIN_PAGE();
                break;
        }
        return forwardJSP;
    }

    public Resolution deleteRow() throws ParseException {

        String idTuntutanKos = getContext().getRequest().getParameter("idTuntutanKos");
        String tName = getContext().getRequest().getParameter("tName");
        String typeSyor = getContext().getRequest().getParameter("typeName");
        String forwardJSP = new String();

        if (idTuntutanKos != null && tName != null) {
            forwardJSP = refreshData(disLaporanTanahService.delObject(tName, new String[]{idTuntutanKos}, typeSyor));
            addSimpleMessage("Maklumat Berjaya Dihapuskan");
        }
        return new JSP("/WEB-INF/jsp/" + forwardJSP).addParameter("popup", "true");
//        return new JSP(DisPermohonanPage.getDEPO_EDIT_PAGE()).addParameter("tab", "true");
    }

    public Resolution showFormPopUp() {
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = (Permohonan) disLaporanTanahService.findObject(permohonan, new String[]{idPermohonan}, 0);
        listKodTuntut = new ArrayList<KodTuntut>();
        List<KodTuntut> listKodTuntutTemp = new ArrayList<KodTuntut>();
        List<PermohonanTuntutanKos> listMohonTT = new ArrayList<PermohonanTuntutanKos>();
        listKodTuntutTemp = disLaporanTanahService.getPelupusanService().findKodTuntutPelupusan();
        if (permohonan != null) {
            listMohonTT = permohonan.getSenaraiTuntutanKos();
            senaraiHakmilikPermohonan = permohonan.getSenaraiHakmilik();
            LOG.info(listMohonTT.size());
        }
        if (listMohonTT.size() > 0) {
            if (listKodTuntutTemp.size() > 0) {
                for (KodTuntut kt : listKodTuntutTemp) {
                    boolean checkExist = true;
                    for (PermohonanTuntutanKos ptk : listMohonTT) {
                        if (kt.getKod().equalsIgnoreCase(ptk.getKodTuntut().getKod())) {
                            checkExist = false;
                            break;
                        }
                    }
                    if (checkExist) {
                        listKodTuntut.add(kt);
                    }
                }
            }
        }
        if (permohonan.getKodUrusan().getKod().equals("PLPT") ||permohonan.getKodUrusan().getKod().equals("BMBT") || permohonan.getKodUrusan().getKod().equals("PTBTC") || permohonan.getKodUrusan().getKod().equals("PTBTS") || permohonan.getKodUrusan().getKod().equals("LSTP")) {
            if (listMohonTT.size() >= 0) {
                if (listKodTuntutTemp.size() >= 0) {
                    for (KodTuntut kt : listKodTuntutTemp) {
                        boolean checkExist = true;
                        for (PermohonanTuntutanKos ptk : listMohonTT) {
                            if (kt.getKod().equalsIgnoreCase(ptk.getKodTuntut().getKod())) {
                                checkExist = false;
                                break;
                            }
                        }
                        if (checkExist) {
                            listKodTuntut.add(kt);
                        }
                    }
                }
            }
        }
        if (listKodTuntut.size() > 0) {

            sizeKod = listKodTuntut.size();
        }
        if (senaraiHakmilikPermohonan.size() > 0) {
            hakmilikPermohonan = senaraiHakmilikPermohonan.get(0);
        }


        return new JSP(DisPermohonanPage.getBORANG_ADD_PAGE()).addParameter("popup", "true");
    }

    public Resolution showFormKemaskiniNilai() {
        idTuntutanKos = getContext().getRequest().getParameter("idTuntutanKos");

        permohonanTuntutanKos = disLaporanTanahService.getPermohonanTuntutanKosDAO().findById(Long.valueOf(idTuntutanKos));

        return new JSP(DisPermohonanPage.getBORANG_EDIT_PRICE_PAGE()).addParameter("popup", "true");
    }

    public Resolution kemaskiniAmaun() {
        peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        idTuntutanKos = getContext().getRequest().getParameter("idTuntutanKos");
        String harga = getContext().getRequest().getParameter("harga");
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = (Permohonan) disLaporanTanahService.findObject(permohonan, new String[]{idPermohonan}, 0);
        permohonanTuntutanKos = disLaporanTanahService.getPermohonanTuntutanKosDAO().findById(Long.valueOf(idTuntutanKos));
        if (permohonanTuntutanKos != null) {
            permohonanTuntutanKos.setInfoAudit(disLaporanTanahService.findAudit(permohonanTuntutanKos, "update", peng));
            permohonanTuntutanKos.setPermohonan(permohonan);
            permohonanTuntutanKos.setCawangan(permohonan.getCawangan());
            double a = Double.parseDouble(harga);
            permohonanTuntutanKos.setAmaunTuntutan(BigDecimal.valueOf(a));
            disLaporanTanahService.getPlpservice().simpanPermohonanTuntutanKos(permohonanTuntutanKos);
        }
        rehydrate();
        return new JSP(DisPermohonanPage.getBORANG_EDIT_PAGE()).addParameter("popup", "true");
    }

    public Resolution simpanKodTuntut() {
        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = (Permohonan) disLaporanTanahService.findObject(permohonan, new String[]{idPermohonan}, 0);
        if (permohonan != null) {
            senaraiHakmilikPermohonan = permohonan.getSenaraiHakmilik();
        }
        if (senaraiHakmilikPermohonan.size() > 0) {
            hakmilikPermohonan = senaraiHakmilikPermohonan.get(0);
        }
        PermohonanTuntutanKos mohonTuntutKos = new PermohonanTuntutanKos();
        String item = getContext().getRequest().getParameter("item");
        String harga = getContext().getRequest().getParameter("harga");
        String[] listKodTuntut = item.split(",");
        String[] listHarga = harga.split(",");
        LOG.info("Size Kod :" + listKodTuntut.length);
        LOG.info("Size Harga :" + listHarga.length);
        InfoAudit ia = new InfoAudit();
        ia.setDimasukOleh(peng);
        ia.setTarikhMasuk(new java.util.Date());
        for (int i = 0; i < listKodTuntut.length; i++) {

            if ((!listKodTuntut[i].equals("T")) && (!listHarga[i].equals("T"))) {
                mohonTuntutKos = new PermohonanTuntutanKos();
                mohonTuntutKos.setInfoAudit(disLaporanTanahService.findAudit(mohonTuntutKos, "new", peng));
                mohonTuntutKos.setPermohonan(permohonan);
                mohonTuntutKos.setCawangan(permohonan.getCawangan());
                mohonTuntutKos.setItem(disLaporanTanahService.getKodTuntutDAO().findById(listKodTuntut[i]).getNama());
                if (hakmilikPermohonan != null) {
                    mohonTuntutKos.setHakmilikPermohonan(hakmilikPermohonan);
                }
                double a = Double.parseDouble(listHarga[i]);
                mohonTuntutKos.setAmaunTuntutan(BigDecimal.valueOf(a));
                mohonTuntutKos.setKodTuntut(disLaporanTanahService.getKodTuntutDAO().findById(listKodTuntut[i]));
                mohonTuntutKos.setKodTransaksi(disLaporanTanahService.getKodTuntutDAO().findById(listKodTuntut[i]).getKodKewTrans());

                disLaporanTanahService.getPlpservice().simpanPermohonanTuntutanKos(mohonTuntutKos);
            }
        }
        rehydrate();
        return new JSP(DisPermohonanPage.getBORANG_ADD_PAGE()).addParameter("popup", "true");
    }

    public PermohonanTuntutanKos getPermohonanTuntutanKos() {
        return permohonanTuntutanKos;
    }

    public void setPermohonanTuntutanKos(PermohonanTuntutanKos permohonanTuntutanKos) {
        this.permohonanTuntutanKos = permohonanTuntutanKos;
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

    public List<PermohonanTuntutanKos> getListPermohonanTuntutanKos() {
        return listPermohonanTuntutanKos;
    }

    public void setListPermohonanTuntutanKos(List<PermohonanTuntutanKos> listPermohonanTuntutanKos) {
        this.listPermohonanTuntutanKos = listPermohonanTuntutanKos;
    }

    public List<KodTuntut> getListKodTuntut() {
        return listKodTuntut;
    }

    public void setListKodTuntut(List<KodTuntut> listKodTuntut) {
        this.listKodTuntut = listKodTuntut;
    }

    public String getIdPermohonan() {
        return idPermohonan;
    }

    public void setIdPermohonan(String idPermohonan) {
        this.idPermohonan = idPermohonan;
    }

    public String getIdTuntutanKos() {
        return idTuntutanKos;
    }

    public void setIdTuntutanKos(String idTuntutanKos) {
        this.idTuntutanKos = idTuntutanKos;
    }

    public String getKodNegeri() {
        return kodNegeri;
    }

    public void setKodNegeri(String kodNegeri) {
        this.kodNegeri = kodNegeri;
    }

    public String getStageId() {
        return stageId;
    }

    public void setStageId(String stageId) {
        this.stageId = stageId;
    }

    public DisBorangController getDisBorangController() {
        return disBorangController;
    }

    public void setDisBorangController(DisBorangController disBorangController) {
        this.disBorangController = disBorangController;
    }

    public int getSizeKod() {
        return sizeKod;
    }

    public void setSizeKod(int sizeKod) {
        this.sizeKod = sizeKod;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public HakmilikPermohonan getHakmilikPermohonan() {
        return hakmilikPermohonan;
    }

    public void setHakmilikPermohonan(HakmilikPermohonan hakmilikPermohonan) {
        this.hakmilikPermohonan = hakmilikPermohonan;
    }

    public List<HakmilikPermohonan> getSenaraiHakmilikPermohonan() {
        return senaraiHakmilikPermohonan;
    }

    public void setSenaraiHakmilikPermohonan(List<HakmilikPermohonan> senaraiHakmilikPermohonan) {
        this.senaraiHakmilikPermohonan = senaraiHakmilikPermohonan;
    }
}
