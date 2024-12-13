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
import etanah.dao.PemohonDAO;
import etanah.dao.PermohonanDAO;
import etanah.dao.PihakDAO;
import etanah.model.HakmilikPermohonan;
import etanah.model.HakmilikPihakBerkepentingan;
import etanah.model.InfoAudit;
import etanah.model.Pemohon;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.model.PermohonanPihak;
import etanah.model.PermohonanRujukanLuar;
import etanah.model.Pihak;
import etanah.model.ambil.BorangPerHakmilik;
import etanah.model.ambil.InfoWarta;
import etanah.model.ambil.TampalBorangHakmilik;
import etanah.service.CalcTax;
import etanah.service.ConsentPtdService;
import etanah.service.EnforceService;
import etanah.service.ambil.BorangPerHakmilikService;
//import etanah.service.ambil.tampalBorangPerHakmilikService;
import etanah.service.ambil.TampalHakmilikService;
//import etanah.service.ambil.infoWartaService;
import etanah.service.common.HakmilikPermohonanService;
import etanah.service.common.HakmilikPihakKepentinganService;
import etanah.service.common.PemohonService;
import etanah.service.common.PermohonanPihakService;
import etanah.service.common.PermohonanRujukanLuarService;
import etanah.view.etanahActionBeanContext;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.RedirectResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;
import org.hibernate.Session;

/**
 *
 * @author zipzap
 */
@UrlBinding("/pengambilan/common/borangA")
public class NotisBorangAActionBean extends AbleActionBean {

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
//    @Inject
//    infoWartaService infoWartaService;
    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;
    @Inject
    CalcTax calcTax;
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
    TampalHakmilikService tampalHakmilikService;
    @Inject
    BorangPerHakmilikService borangPerhakmilikService;
//    @Inject
//    HakmilikPermohonanService hakmilikpermohonanservice;
    @Inject
    PermohonanPihakService permohonanPihakService;
    @Inject
    HakmilikPihakKepentinganService hakmilikPihakKepentinganService;
    @Inject
    PermohonanRujukanLuarService service;
    private PermohonanRujukanLuar permohonanRujukanLuar;
    private List<HakmilikPermohonan> hakmilikPermohonanList2;
    private List<BorangPerHakmilik> BorangPerHakmilikList;
    private List<TampalBorangHakmilik> tampalBorangHakmilikList;
    private List<TampalBorangHakmilik> tampalBorangHakmilikList2;
    private List<HakmilikPermohonan> hakmilikPermohonanList;
    private List<HakmilikPermohonanForm> listHakmilikPermohonanForm;
    private List<HakmilikPihakBerkepentingan> pihakKepentinganList;
    private List<HakmilikPihakBerkepentingan> listTuanTanahNew;
    private List<PermohonanPihak> mohonPihakList;
    private List<Pemohon> pemohonList;
    private List<HakmilikPihakBerkepentingan> pemilik;
    private List<PermohonanRujukanLuar> mrlList;
    private InfoWarta infoWarta;
    private HakmilikPermohonan hakmilikPermohonan;
    private TampalBorangHakmilik tampalBorangHakmilik;
    private BorangPerHakmilik borangPerHakmilik;
    private String fail;
    private String catatan;
    private String tmptTampal;
    private String Penampal;
    private String trhTampal;
    private String keterangan;
    private String idMohonHakmilik;
    private String idBorang;
    private Integer listTotal;
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

    @DefaultHandler
    public Resolution showForm() {


        return new JSP("/pengambilan/APT/notis_borangA.jsp").addParameter("tab", "true");
//        E:\Etanah\devel\etanahViewController\src\main\webapp\WEB-INF\jsp\pengambilan\APT\maklumat_asasAPT.jsp
    }

    public Resolution kemaskiniBorangA() {

        if (idMohonHakmilik == null) {
            idMohonHakmilik = (String) getContext().getRequest().getParameter("idMH");
        }


        hakmilikPermohonan = hakmilikpermohonanservice.findHakmilikPermohonanbyidMH(Long.valueOf(idMohonHakmilik));
        hakmilikPermohonanList.add(hakmilikPermohonan);
        BorangPerHakmilikList = borangPerhakmilikService.findBorangPerhakmilikByIdMH(String.valueOf(idMohonHakmilik));
        if (BorangPerHakmilikList.size() > 0) {
            for (BorangPerHakmilik borangPerHM : BorangPerHakmilikList) {
                tampalBorangHakmilikList2 = tampalHakmilikService.findBorangTampalById(borangPerHM.getId());
            }
        }
        rehydrate();

        return new JSP("/pengambilan/APT/tempat_tampalBorangA.jsp").addParameter("popup", "true").addParameter("showForm", "false");
    }

    public Resolution hapusRecord() throws ParseException {

        if (idMohonHakmilik == null) {
            idMohonHakmilik = (String) getContext().getRequest().getParameter("idMohonHakmilik");
        }
        idBorang = (String) getContext().getRequest().getParameter("idBorang");
        tampalBorangHakmilik = tampalHakmilikService.findBorangById(idBorang);

        tampalHakmilikService.deleteTampalBorangHakmilik(tampalBorangHakmilik);

        addSimpleMessage("Maklumat telah berjaya DiBuang");
        rehydrate();
//        return new RedirectResolution(notisBorangAActionBean.class, "kemaskiniBorangA").addParameter("popup", "true").addParameter("idMohonHakmilik", idMohonHakmilik);
        return kemaskiniBorangA();

    }

    public Resolution simpanBorang() throws ParseException {

        if (idMohonHakmilik == null) {
            idMohonHakmilik = (String) getContext().getRequest().getParameter("idMH");
        }

        BorangPerHakmilikList = borangPerhakmilikService.findBorangPerhakmilikByIdMH(idMohonHakmilik);

        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        if (tmptTampal == null) {
            tmptTampal = (String) getContext().getRequest().getParameter(tmptTampal);
        }
        if (Penampal == null) {
            Penampal = (String) getContext().getRequest().getParameter(Penampal);
        }
        if (trhTampal == null) {
            trhTampal = (String) getContext().getRequest().getParameter(trhTampal);
        }
        if (catatan == null) {
            catatan = (String) getContext().getRequest().getParameter(catatan);
        }
        InfoAudit ia = new InfoAudit();
        ia.setDimasukOleh(peng);
        ia.setTarikhMasuk(new java.util.Date());

        tampalBorangHakmilik = new TampalBorangHakmilik();
        tampalBorangHakmilik.setCatatan(display);
        tampalBorangHakmilik.setTarikhTampal(sdf.parse(trhTampal));
        tampalBorangHakmilik.setTempatTampal(tmptTampal);
        tampalBorangHakmilik.setPenghantarNotis(tmptTampal);
        tampalBorangHakmilik.setCatatan(catatan);
        tampalBorangHakmilik.setInfoAudit(ia);
        tampalBorangHakmilik.setBorangPerHakmilik(BorangPerHakmilikList.get(0));
        tampalHakmilikService.simpanTampalBorangHakmilik(tampalBorangHakmilik);




        addSimpleMessage("Maklumat telah berjaya disimpan.");
        rehydrate();
        return new RedirectResolution(NotisBorangAActionBean.class, "kemaskiniBorangA").addParameter("popup", "true").addParameter("idMohonHakmilik", idMohonHakmilik);
//        return new JSP("/pengambilan/APT/tempat_tampalBorangA.jsp").addParameter("popup", "true");
//        return showForm();
    }

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        Pengguna pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);

        List<TampalBorangHakmilik> listTampalBorangPerHakmiliks = null;
        listHakmilikPermohonanForm = new ArrayList<HakmilikPermohonanForm>();
        hakmilikPermohonanList = hakmilikpermohonanservice.findByIdPermohonanOnly(idPermohonan);
        if (hakmilikPermohonanList.size() > 0) {
            for (HakmilikPermohonan hp : hakmilikPermohonanList) {
                HakmilikPermohonanForm f = new HakmilikPermohonanForm();
                f.setMohonHakmilik(hp);
                listHakmilikPermohonanForm.add(f);

                BorangPerHakmilikList = borangPerhakmilikService.findBorangPerhakmilikByIdMH(String.valueOf(hp.getId()));
                if (BorangPerHakmilikList.size() > 0) {
                    for (BorangPerHakmilik borangPerHM : BorangPerHakmilikList) {
                        tampalBorangHakmilikList = tampalHakmilikService.findBorangTampalById(borangPerHM.getId());
//                        f.setCatatan(catatan);
//                        f.setPenghantarNotis(catatan);
//                        f.setTarikhTampal(trhTampal);
//                        f.setTempatTampal(tmptTampal);
                        f.setTotal(tampalBorangHakmilikList.size());

                    }
                    listTotal = f.getTotal();
                }
            }
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

//    public infoWartaService getInfoWartaService() {
//        return infoWartaService;
//    }
//
//    public void setInfoWartaService(infoWartaService infoWartaService) {
//        this.infoWartaService = infoWartaService;
//    }
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

    public String getCatatan() {
        return catatan;
    }

    public void setCatatan(String catatan) {
        this.catatan = catatan;
    }

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }

    public TampalHakmilikService getTampalHakmilikService() {
        return tampalHakmilikService;
    }

    public void setTampalHakmilikService(TampalHakmilikService tampalHakmilikService) {
        this.tampalHakmilikService = tampalHakmilikService;
    }

    public BorangPerHakmilikService getBorangPerhakmilikService() {
        return borangPerhakmilikService;
    }

    public void setBorangPerhakmilikService(BorangPerHakmilikService borangPerhakmilikService) {
        this.borangPerhakmilikService = borangPerhakmilikService;
    }

    public List<BorangPerHakmilik> getBorangPerHakmilikList() {
        return BorangPerHakmilikList;
    }

    public void setBorangPerHakmilikList(List<BorangPerHakmilik> BorangPerHakmilikList) {
        this.BorangPerHakmilikList = BorangPerHakmilikList;
    }

    public List<TampalBorangHakmilik> getTampalBorangHakmilikList() {
        return tampalBorangHakmilikList;
    }

    public void setTampalBorangHakmilikList(List<TampalBorangHakmilik> tampalBorangHakmilikList) {
        this.tampalBorangHakmilikList = tampalBorangHakmilikList;
    }

    public List<TampalBorangHakmilik> getTampalBorangHakmilikList2() {
        return tampalBorangHakmilikList2;
    }

    public void setTampalBorangHakmilikList2(List<TampalBorangHakmilik> tampalBorangHakmilikList2) {
        this.tampalBorangHakmilikList2 = tampalBorangHakmilikList2;
    }

    public TampalBorangHakmilik getTampalBorangHakmilik() {
        return tampalBorangHakmilik;
    }

    public void setTampalBorangHakmilik(TampalBorangHakmilik tampalBorangHakmilik) {
        this.tampalBorangHakmilik = tampalBorangHakmilik;
    }

    public String getTmptTampal() {
        return tmptTampal;
    }

    public void setTmptTampal(String tmptTampal) {
        this.tmptTampal = tmptTampal;
    }

    public String getPenampal() {
        return Penampal;
    }

    public void setPenampal(String Penampal) {
        this.Penampal = Penampal;
    }

    public String getTrhTampal() {
        return trhTampal;
    }

    public void setTrhTampal(String trhTampal) {
        this.trhTampal = trhTampal;
    }

    public List<HakmilikPermohonanForm> getListHakmilikPermohonanForm() {
        return listHakmilikPermohonanForm;
    }

    public void setListHakmilikPermohonanForm(List<HakmilikPermohonanForm> listHakmilikPermohonanForm) {
        this.listHakmilikPermohonanForm = listHakmilikPermohonanForm;
    }

    public HakmilikPermohonan getHakmilikPermohonan() {
        return hakmilikPermohonan;
    }

    public void setHakmilikPermohonan(HakmilikPermohonan hakmilikPermohonan) {
        this.hakmilikPermohonan = hakmilikPermohonan;
    }

    public String getIdMohonHakmilik() {
        return idMohonHakmilik;
    }

    public void setIdMohonHakmilik(String idMohonHakmilik) {
        this.idMohonHakmilik = idMohonHakmilik;
    }

    public BorangPerHakmilik getBorangPerHakmilik() {
        return borangPerHakmilik;
    }

    public void setBorangPerHakmilik(BorangPerHakmilik borangPerHakmilik) {
        this.borangPerHakmilik = borangPerHakmilik;
    }

    public String getIdBorang() {
        return idBorang;
    }

    public void setIdBorang(String idBorang) {
        this.idBorang = idBorang;
    }

    public Integer getListTotal() {
        return listTotal;
    }

    public void setListTotal(Integer listTotal) {
        this.listTotal = listTotal;
    }
}
