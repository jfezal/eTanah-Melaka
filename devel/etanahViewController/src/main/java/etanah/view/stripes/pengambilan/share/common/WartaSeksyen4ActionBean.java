/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.stripes.pengambilan.share.common;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import com.google.inject.Provider;
import etanah.dao.InfoWartaDAO;
import etanah.dao.KodDokumenDAO;
import etanah.dao.KodNotisDAO;
import etanah.dao.PemohonDAO;
import etanah.dao.PermohonanDAO;
import etanah.dao.PihakDAO;
import etanah.model.Hakmilik;
import etanah.model.HakmilikPermohonan;
import etanah.model.HakmilikPihakBerkepentingan;
import etanah.model.InfoAudit;
import etanah.model.KodBandarPekanMukim;
import etanah.model.KodCawangan;
import etanah.model.KodDaerah;
import etanah.model.KodDokumen;
import etanah.model.KodNotis;
import etanah.model.KodRizab;
import etanah.model.KodSeksyen;
import etanah.model.KodUrusan;
import etanah.model.Notis;
import etanah.model.Pemohon;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.model.PermohonanPihak;
import etanah.model.PermohonanRujukanLuar;
import etanah.model.Pihak;
import etanah.model.StatusTanahLepasPengambilan;
import etanah.model.TanahRizabPermohonan;
import etanah.model.ambil.InfoWarta;
import etanah.ref.pengambilan.sek4.RefPeringkat;
import etanah.service.CalcTax;
import etanah.service.ConsentPtdService;
import etanah.service.EnforceService;
import etanah.service.acq.integrationflow.MyeTappIntegrationFlowService;
import etanah.service.acq.integrationflow.Sek4IntegrationFlowService;
import etanah.service.acq.integrationflow.Sek8aIntegrationFlowService;
import etanah.service.ambil.InfoWartaService;
//import etanah.service.ambil.infoWartaService;
import etanah.service.common.HakmilikPermohonanService;
import etanah.service.common.HakmilikPihakKepentinganService;
import etanah.service.common.PemohonService;
import etanah.service.common.PermohonanPihakService;
import etanah.service.common.PermohonanRujukanLuarService;
import etanah.view.etanahActionBeanContext;
import etanah.view.stripes.pembangunan.tugasan.TugasanActionBean;
import etanah.view.stripes.tugasanutiliti.TugasanMMKActionBean;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.RedirectResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.StreamingResolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;
import org.apache.commons.lang.StringUtils;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author zipzap
 */
@UrlBinding("/pengambilan/common/wartaSeksyen4")
public class WartaSeksyen4ActionBean extends AbleActionBean {

    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    PemohonDAO pemohonDAO;
    @Inject
    PihakDAO pihakDAO;
    @Inject
    InfoWartaDAO infoWartaDAO;
    @Inject
    ConsentPtdService conService;
    @Inject
    PemohonService pemohonService;
    @Inject
    EnforceService enforceService;
    @Inject
    InfoWartaService infoWartaService;
    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;
    @Inject
    CalcTax calcTax;
    @Inject
    Sek4IntegrationFlowService sek4IntegrationFlowService;
    @Inject
    KodDokumenDAO kodDokumenDAO;
    Permohonan permohonan = new Permohonan();
    Pihak pihak = new Pihak();
    private String[] arrayCheckBox;
    private String idPermohonan;
    private List<Pihak> listTuanTanah;
    private List<Pemohon> listPemohon;
    private String display;
    private List<HakmilikPihakBerkepentingan> senaraiHakmilikPihak;
    @Inject
    HakmilikPermohonanService hakmilikpermohonanservice;
    @Inject
    PermohonanPihakService permohonanPihakService;
    @Inject
    HakmilikPihakKepentinganService hakmilikPihakKepentinganService;
    @Inject
    PermohonanRujukanLuarService service;
    @Inject
    Sek8aIntegrationFlowService sek8aIntegrationFlowService;
        @Inject
        MyeTappIntegrationFlowService myeTappIntegrationFlowService;
    private PermohonanRujukanLuar permohonanRujukanLuar;
    private List<HakmilikPermohonan> hakmilikPermohonanList2;
    private List<HakmilikPermohonan> hakmilikPermohonanList;
    private List<HakmilikPihakBerkepentingan> pihakKepentinganList;
    private List<HakmilikPihakBerkepentingan> listTuanTanahNew;
    private List<PermohonanPihak> mohonPihakList;
    private List<Pemohon> pemohonList;
    private List<HakmilikPihakBerkepentingan> pemilik;
    private List<PermohonanRujukanLuar> mrlList;
    private InfoWarta infoWarta;
    private String fail;
    private String failJKPTG;
    private String Warta;
    private String noWarta;
    private String keteranganWarta;
    private String keterangan;
    private String trhWarta;
    private Date trhWarta1;
    private Pengguna pengguna;
    private KodDokumen kodDokumen;
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

    @DefaultHandler
    public Resolution showForm() {

        return new JSP("/pengambilan/APT/warta_Sek_4.jsp").addParameter("tab", "true");
//        E:\Etanah\devel\etanahViewController\src\main\webapp\WEB-INF\jsp\pengambilan\APT\maklumat_asasAPT.jsp
    }

    public Resolution simpanWarta() throws ParseException {

        pengguna = (Pengguna) context.getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        if (failJKPTG.equals(null)) {
            failJKPTG = (String) getContext().getRequest().getParameter(failJKPTG);
        }
        if (keteranganWarta.equals(null)) {
            keteranganWarta = (String) getContext().getRequest().getParameter(keteranganWarta);
        }
        if (noWarta.equals(null)) {
            noWarta = (String) getContext().getRequest().getParameter(noWarta);
        }
        Warta = (String) getContext().getRequest().getParameter(Warta);

        InfoAudit ia = new InfoAudit();
        ia.setDimasukOleh(pengguna);
        ia.setTarikhMasuk(new java.util.Date());
        List<InfoWarta> listInfoWarta = infoWartaService.findByIdPermohonan(idPermohonan);
        Permohonan permohonan = permohonanDAO.findById(idPermohonan);
        if (permohonan.getKodUrusan().getKod().equals("SEK4")) {
            kodDokumen = kodDokumenDAO.findById("A");
        } else {
            kodDokumen = kodDokumenDAO.findById("D");
        }
        if (listInfoWarta.size() > 0) {
            for (InfoWarta warta : listInfoWarta) {
                warta.setPermohonan(permohonan);
                warta.setNoWarta(noWarta);
                warta.setKeteranganWarta(keteranganWarta);
                warta.setTrhWarta(sdf.parse(trhWarta));
                warta.setFailJKPTG(failJKPTG);
                warta.setNotis(kodDokumen);
                warta.setInfoAudit(ia);
                infoWartaService.simpanInfoWarta(warta);
                addSimpleMessage("Kemaskini Warta Telah Berjaya");
            }
        } else {
            InfoWarta wartaNew = new InfoWarta();
            wartaNew.setPermohonan(permohonan);
            wartaNew.setNoWarta(noWarta);
            wartaNew.setKeteranganWarta(keteranganWarta);
            wartaNew.setTrhWarta(sdf.parse(trhWarta));
            wartaNew.setFailJKPTG(failJKPTG);
            wartaNew.setNotis(kodDokumen);
            wartaNew.setInfoAudit(ia);
//            infoWartaDAO.save(wartaNew);
            infoWartaService.simpanInfoWarta(wartaNew);
            addSimpleMessage("Kemaskini Warta Telah Berjaya");
        }

        return showForm();
    }

    public Resolution hantar() throws IOException {
        //idPermohonan = getContext().getRequest().getParameter("idPermohonan");
        Pengguna pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);

        idPermohonan = (String) getContext().getRequest().getParameter("idPermohonan");
        permohonan = permohonanDAO.findById(idPermohonan);
        RefPeringkat ref = new RefPeringkat();

        if (permohonan.getKodUrusan().getKod().equals("SEK4")) {
            sek4IntegrationFlowService.completeTask(ref.SEMAK_WARTA, permohonan, pengguna);
        } else if (permohonan.getKodUrusan().getKod().equals("PWSL")) {
            etanah.ref.pengambilan.sek8a.RefPeringkat ref8a = new etanah.ref.pengambilan.sek8a.RefPeringkat();
            sek8aIntegrationFlowService.completeTask(ref8a.TAMBAHAN_PAMPASAN_LBHN_LUAS, permohonan, pengguna);
        }else if (permohonan.getKodUrusan().getKod().equals("ESK4")) {
            etanah.ref.pengambilan.myetapp.RefPeringkat refe = new etanah.ref.pengambilan.myetapp.RefPeringkat();
            myeTappIntegrationFlowService.completeTask(refe.SEK4_SEMAK_WARTA, permohonan, pengguna, permohonan.getCawangan().getDaerah());
        } else {
            etanah.ref.pengambilan.sek8a.RefPeringkat ref8a = new etanah.ref.pengambilan.sek8a.RefPeringkat();
            sek8aIntegrationFlowService.completeTask(ref8a.PINDAAN_KEPUTUSAN, permohonan, pengguna);
        }

        return new RedirectResolution(TugasanActionBean.class);
    }

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        Pengguna pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);

        List<InfoWarta> listInfoWarta = infoWartaService.findByIdPermohonan(idPermohonan);
        if (listInfoWarta.size() > 0) {
            infoWarta = listInfoWarta.get(0);
            failJKPTG = infoWarta.getFailJKPTG();
            keteranganWarta = infoWarta.getKeteranganWarta();
            noWarta = infoWarta.getNoWarta();
//<<<<<<< .mine
//            trhWarta = infoWarta.getTrhWarta() + "";
//=======
            trhWarta = infoWarta.getTrhWarta() != null ? sdf.format(infoWarta.getTrhWarta()) : "";
//            trhWarta = trhWarta1.toString();
//>>>>>>> .r3702

        }

    }

    public PermohonanDAO getPermohonanDAO() {
        return permohonanDAO;
    }

    public void setPermohonanDAO(PermohonanDAO permohonanDAO) {
        this.permohonanDAO = permohonanDAO;
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

    public ConsentPtdService getConService() {
        return conService;
    }

    public void setConService(ConsentPtdService conService) {
        this.conService = conService;
    }

    public PemohonService getPemohonService() {
        return pemohonService;
    }

    public void setPemohonService(PemohonService pemohonService) {
        this.pemohonService = pemohonService;
    }

    public EnforceService getEnforceService() {
        return enforceService;
    }

    public void setEnforceService(EnforceService enforceService) {
        this.enforceService = enforceService;
    }

    public Provider<Session> getSessionProvider() {
        return sessionProvider;
    }

    public void setSessionProvider(Provider<Session> sessionProvider) {
        this.sessionProvider = sessionProvider;
    }

    public CalcTax getCalcTax() {
        return calcTax;
    }

    public void setCalcTax(CalcTax calcTax) {
        this.calcTax = calcTax;
    }

    public Permohonan getPermohonan() {
        return permohonan;
    }

    public void setPermohonan(Permohonan permohonan) {
        this.permohonan = permohonan;
    }

    public Pihak getPihak() {
        return pihak;
    }

    public void setPihak(Pihak pihak) {
        this.pihak = pihak;
    }

    public String[] getArrayCheckBox() {
        return arrayCheckBox;
    }

    public void setArrayCheckBox(String[] arrayCheckBox) {
        this.arrayCheckBox = arrayCheckBox;
    }

    public String getIdPermohonan() {
        return idPermohonan;
    }

    public void setIdPermohonan(String idPermohonan) {
        this.idPermohonan = idPermohonan;
    }

    public List<Pihak> getListTuanTanah() {
        return listTuanTanah;
    }

    public void setListTuanTanah(List<Pihak> listTuanTanah) {
        this.listTuanTanah = listTuanTanah;
    }

    public List<Pemohon> getListPemohon() {
        return listPemohon;
    }

    public void setListPemohon(List<Pemohon> listPemohon) {
        this.listPemohon = listPemohon;
    }

    public String getDisplay() {
        return display;
    }

    public void setDisplay(String display) {
        this.display = display;
    }

    public List<HakmilikPihakBerkepentingan> getSenaraiHakmilikPihak() {
        return senaraiHakmilikPihak;
    }

    public void setSenaraiHakmilikPihak(List<HakmilikPihakBerkepentingan> senaraiHakmilikPihak) {
        this.senaraiHakmilikPihak = senaraiHakmilikPihak;
    }

    public HakmilikPermohonanService getHakmilikpermohonanservice() {
        return hakmilikpermohonanservice;
    }

    public void setHakmilikpermohonanservice(HakmilikPermohonanService hakmilikpermohonanservice) {
        this.hakmilikpermohonanservice = hakmilikpermohonanservice;
    }

    public PermohonanPihakService getPermohonanPihakService() {
        return permohonanPihakService;
    }

    public void setPermohonanPihakService(PermohonanPihakService permohonanPihakService) {
        this.permohonanPihakService = permohonanPihakService;
    }

    public HakmilikPihakKepentinganService getHakmilikPihakKepentinganService() {
        return hakmilikPihakKepentinganService;
    }

    public void setHakmilikPihakKepentinganService(HakmilikPihakKepentinganService hakmilikPihakKepentinganService) {
        this.hakmilikPihakKepentinganService = hakmilikPihakKepentinganService;
    }

    public PermohonanRujukanLuarService getService() {
        return service;
    }

    public void setService(PermohonanRujukanLuarService service) {
        this.service = service;
    }

    public PermohonanRujukanLuar getPermohonanRujukanLuar() {
        return permohonanRujukanLuar;
    }

    public void setPermohonanRujukanLuar(PermohonanRujukanLuar permohonanRujukanLuar) {
        this.permohonanRujukanLuar = permohonanRujukanLuar;
    }

    public List<HakmilikPermohonan> getHakmilikPermohonanList2() {
        return hakmilikPermohonanList2;
    }

    public void setHakmilikPermohonanList2(List<HakmilikPermohonan> hakmilikPermohonanList2) {
        this.hakmilikPermohonanList2 = hakmilikPermohonanList2;
    }

    public List<HakmilikPermohonan> getHakmilikPermohonanList() {
        return hakmilikPermohonanList;
    }

    public void setHakmilikPermohonanList(List<HakmilikPermohonan> hakmilikPermohonanList) {
        this.hakmilikPermohonanList = hakmilikPermohonanList;
    }

    public List<HakmilikPihakBerkepentingan> getPihakKepentinganList() {
        return pihakKepentinganList;
    }

    public void setPihakKepentinganList(List<HakmilikPihakBerkepentingan> pihakKepentinganList) {
        this.pihakKepentinganList = pihakKepentinganList;
    }

    public List<HakmilikPihakBerkepentingan> getListTuanTanahNew() {
        return listTuanTanahNew;
    }

    public void setListTuanTanahNew(List<HakmilikPihakBerkepentingan> listTuanTanahNew) {
        this.listTuanTanahNew = listTuanTanahNew;
    }

    public List<PermohonanPihak> getMohonPihakList() {
        return mohonPihakList;
    }

    public void setMohonPihakList(List<PermohonanPihak> mohonPihakList) {
        this.mohonPihakList = mohonPihakList;
    }

    public List<Pemohon> getPemohonList() {
        return pemohonList;
    }

    public void setPemohonList(List<Pemohon> pemohonList) {
        this.pemohonList = pemohonList;
    }

    public List<HakmilikPihakBerkepentingan> getPemilik() {
        return pemilik;
    }

    public void setPemilik(List<HakmilikPihakBerkepentingan> pemilik) {
        this.pemilik = pemilik;
    }

    public List<PermohonanRujukanLuar> getMrlList() {
        return mrlList;
    }

    public void setMrlList(List<PermohonanRujukanLuar> mrlList) {
        this.mrlList = mrlList;
    }

    public SimpleDateFormat getSdf() {
        return sdf;
    }

    public void setSdf(SimpleDateFormat sdf) {
        this.sdf = sdf;
    }

    public InfoWartaDAO getInfoWartaDAO() {
        return infoWartaDAO;
    }

    public void setInfoWartaDAO(InfoWartaDAO infoWartaDAO) {
        this.infoWartaDAO = infoWartaDAO;
    }

    public InfoWartaService getInfoWartaService() {
        return infoWartaService;
    }

    public void setInfoWartaService(InfoWartaService infoWartaService) {
        this.infoWartaService = infoWartaService;
    }

    public InfoWarta getInfoWarta() {
        return infoWarta;
    }

    public void setInfoWarta(InfoWarta infoWarta) {
        this.infoWarta = infoWarta;
    }

    public String getFail() {
        return fail;
    }

    public void setFail(String fail) {
        this.fail = fail;
    }

    public String getWarta() {
        return Warta;
    }

    public void setWarta(String Warta) {
        this.Warta = Warta;
    }

    public String getFailJKPTG() {
        return failJKPTG;
    }

    public void setFailJKPTG(String failJKPTG) {
        this.failJKPTG = failJKPTG;
    }

    public String getKeteranganWarta() {
        return keteranganWarta;
    }

    public void setKeteranganWarta(String keteranganWarta) {
        this.keteranganWarta = keteranganWarta;
    }

    public String getNoWarta() {
        return noWarta;
    }

    public void setNoWarta(String noWarta) {
        this.noWarta = noWarta;
    }

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }

    public String getTrhWarta() {
        return trhWarta;
    }

    public void setTrhWarta(String trhWarta) {
        this.trhWarta = trhWarta;
    }

    public Pengguna getPengguna() {
        return pengguna;
    }

    public void setPengguna(Pengguna pengguna) {
        this.pengguna = pengguna;
    }
}
