/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.stripes.pengambilan.share.common;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import com.google.inject.Provider;
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
import etanah.model.KodRizab;
import etanah.model.KodSeksyen;
import etanah.model.KodUrusan;
import etanah.model.Pemohon;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.model.PermohonanPihak;
import etanah.model.PermohonanRujukanLuar;
import etanah.model.Pihak;
import etanah.model.StatusTanahLepasPengambilan;
import etanah.model.TanahRizabPermohonan;
import etanah.model.ambil.PermohonanPengambilan;
import etanah.model.ambil.PermohonanPengambilanHakmilik;
import etanah.service.CalcTax;
import etanah.service.ConsentPtdService;
import etanah.service.EnforceService;
import etanah.service.ambil.PengambilanAPTService;
import etanah.service.common.HakmilikPermohonanService;
import etanah.service.common.HakmilikPihakKepentinganService;
import etanah.service.common.PemohonService;
import etanah.service.common.PermohonanPihakService;
import etanah.service.common.PermohonanRujukanLuarService;
import etanah.view.etanahActionBeanContext;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
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
@UrlBinding("/pengambilan/common/summaryPermohonan")
public class summaryPermohonan extends AbleActionBean {

    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    PemohonDAO pemohonDAO;
    @Inject
    PihakDAO pihakDAO;
    @Inject
    ConsentPtdService conService;
    @Inject
    PemohonService pemohonService;
    @Inject
    EnforceService enforceService;
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
    PermohonanPihakService permohonanPihakService;
    @Inject
    HakmilikPihakKepentinganService hakmilikPihakKepentinganService;
    @Inject
    PermohonanRujukanLuarService service;
    @Inject
    PengambilanAPTService pengambilanAPTService;
    private PermohonanRujukanLuar permohonanRujukanLuar;
    private List<HakmilikPermohonan> hakmilikPermohonanList2;
    private List<HakmilikPermohonan> hakmilikPermohonanList;
    private List<HakmilikPihakBerkepentingan> pihakKepentinganList;
    private List<HakmilikPihakBerkepentingan> listTuanTanahNew;
    private List<PermohonanPihak> mohonPihakList;
    private List<Pemohon> pemohonList;
    private List<HakmilikPihakBerkepentingan> pemilik;
    private List<PermohonanRujukanLuar> mrlList;
    private int size = 0;
    private String[] syer1;
    private String[] syer2;
    private TanahRizabPermohonan rizab;
    private List<TanahRizabPermohonan> tanahRizabList;
    private HakmilikPermohonan hakmilikPermohonan;
    private PermohonanPihak mp;
//    private int size = 0;
    private String[] listluasTerlibat;
    private String noHakmilik;
    private KodUrusan mohonKodUrusan;
    private BigDecimal convLuas;
//    private BigDecimal totalLuas;
    private BigDecimal totalLuas = new BigDecimal(0);
    private BigDecimal amount = new BigDecimal(0);
    private BigDecimal amountMH = new BigDecimal(0);
    private BigDecimal convH = new BigDecimal(0);
    private String namajaga;
    private String jagaTel;
    private String jagaFax;
    private String jagaEmail;
    private KodBandarPekanMukim bandarPekanMukim;
    private KodRizab kodRizab;
    private String noLot;
    private String luasAmbil;
    private String adaCukai;
    private String cukai;
    private String pegangan;
    private String penarikBalikan;
    private String bpm;
    private KodDaerah daerah;
    private String koddaerahbpm;
    private String kodDaerah;
    private String kodBandarPekanMukim;
    private List<KodBandarPekanMukim> senaraiBPM;
    private List<KodBandarPekanMukim> listBandarPekanMukim;
//    private Permohonan permohonan;
    static String staticKodBandarPekanMukim;
    private KodCawangan cawangan;
    private String idTanahRizabPermohonan;
    private String flagPBA = "0";
    private String no = "1";
    private String kodLot;
    private List<KodSeksyen> listKodSeksyen;
    private String kodSeksyen;
    private String kodCawangan;
    private List<Hakmilik> list;
    private List<HakmilikPermohonan> hakmilikPermohonanListNew;
//    private List<HakmilikPihakBerkepentingan> pihakKepentinganList;
    private List<String> luasTerlibat = new ArrayList<String>();
    private List<String> luasTerlibat1 = new ArrayList<String>();
    private List<String> kodUnitLuas = new ArrayList<String>();
    private List<TanahRizabPermohonan> senaraiTanahAA;
    private List<KodBandarPekanMukim> senaraiKodBPM;
    private BigDecimal luasT;
    private String kodUnit;
    private int bandarPekanMukimBaru;
    private String penyerahNoPengenalan;
    private String pNoPengenalan;
    private String penyerahNama;
    private String penyerahAlamat1;
    private String penyerahAlamat2;
    private String penyerahAlamat3;
    private String penyerahAlamat4;
    private String penyerahPoskod;
    private String penyerahNegeri;
    private String penyerahNegeri1;
    private String penyerahNoTelefon;
    private String penyerahEmail;
    private String penyerahJenisPengenalan1;
    private String penyerahKod;
    private String kodPenyerah;
    private String hakmilikSambungan;
    private String tanahRizab;
    private String tanahKerajaan;
    private String noRujukan;
    private String tarikhRujukan;
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    private PermohonanPengambilanHakmilik permohonanPengambilanHakmilik;
    private PermohonanPengambilan permohonanPengambilan;
    private List<PermohonanPengambilanHakmilik> listpermohonanPengambilan;
    private List<PermohonanPengambilanHakmilik> listPermohonanPengambilan;
    private List<MaklumatTanahPengambilanForm> listmaklumatTanahPengambilanForms;
    private String namaUrusan;

    @DefaultHandler
    public Resolution showForm() {
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = permohonanDAO.findById(idPermohonan);
        if(permohonan.getKodUrusan().getKod().equals("SEK4A")){
            return new JSP("/pengambilan/APT/summaryAduanPage.jsp").addParameter("tab", "true");
        }else{
            return new JSP("/pengambilan/APT/summaryPage.jsp").addParameter("tab", "true");
        }
       // return new JSP("/pengambilan/APT/summaryPage.jsp").addParameter("tab", "true");
//        E:\Etanah\devel\etanahViewController\src\main\webapp\WEB-INF\jsp\pengambilan\APT\maklumat_asasAPT.jsp
    }

    public Resolution showForm2() {
        display = "display:none;";
        return new JSP("common/maklumat_pemohon.jsp").addParameter("tab", "true");
    }

    public Resolution showEditPemohon() {
        String idPihak = getContext().getRequest().getParameter("idPihak");

        pihak = pihakDAO.findById(Long.valueOf(idPihak));

        return new JSP("common/edit_pemohon2.jsp").addParameter("popup", "true");
    }

    public Resolution popup() {

        return new JSP("common/pilih_pemohon.jsp").addParameter("tab", "true");

    }

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");

        permohonanPengambilan = pengambilanAPTService.findPermohonanPengambilanByIdMH(idPermohonan);
        hakmilikPermohonanList = hakmilikpermohonanservice.findByIdPermohonanOnlyHakmilikAmbil(idPermohonan);
        listmaklumatTanahPengambilanForms = new ArrayList<MaklumatTanahPengambilanForm>();
        permohonan = permohonanDAO.findById(idPermohonan);
        String txnCode = permohonan.getKodUrusan().getKod();
        if (txnCode.equals("831")) {
            if (permohonanPengambilan.getKatPengambilan().equals("1")) {
                namaUrusan = permohonan.getKodUrusan().getNama() + " (A)";
            } else if (permohonanPengambilan.getKatPengambilan().equals("2")) {
                namaUrusan = permohonan.getKodUrusan().getNama() + " (B)";
            } else {
                namaUrusan = permohonan.getKodUrusan().getNama() + " (C)";
            }
        }

        if (hakmilikPermohonanList.size() > 0) {
            for (HakmilikPermohonan hp : hakmilikPermohonanList) {
                MaklumatTanahPengambilanForm f = new MaklumatTanahPengambilanForm();
                f.setMohonHakmilik(hp);
                listmaklumatTanahPengambilanForms.add(f);
                permohonanPengambilanHakmilik = pengambilanAPTService.findPermohonanPengambilanByIdMH(hp.getId());
                if (permohonanPengambilan != null) {
                    if (permohonanPengambilanHakmilik != null) {
                        f.setLuasAmbil(permohonanPengambilanHakmilik.getLuasAmbil());
                    }

                }
            }
        }

        permohonanPengambilan = pengambilanAPTService.findPermohonanPengambilanByIdMH(idPermohonan);
        pihakKepentinganList = new ArrayList<HakmilikPihakBerkepentingan>();
        listTuanTanahNew = new ArrayList<HakmilikPihakBerkepentingan>();
        try {
            if (idPermohonan != null) {
                listTuanTanah = new LinkedList<Pihak>();
                permohonan = permohonanDAO.findById(idPermohonan);
                List<HakmilikPermohonan> l = permohonan.getSenaraiHakmilik();
                for (HakmilikPermohonan hp : l) {
                    if (hp.getHakmilik() != null) {
                        Hakmilik hk = hp.getHakmilik();
                        List<HakmilikPihakBerkepentingan> lhpk = hk.getSenaraiPihakBerkepentingan();
                        for (HakmilikPihakBerkepentingan hpk : lhpk) {
                            Pihak phk = hpk.getPihak();
                            listTuanTanah.add(phk);
                        }

                    }
//                    senaraiHakmilikPihak = new ArrayList<HakmilikPihakBerkepentingan>();
//                    senaraiHakmilikPihak = lhpk;
                }
                hakmilikPermohonanList2 = hakmilikpermohonanservice.findByIdPermohonanOnly(idPermohonan);
                for (HakmilikPermohonan hakmilikPermohonan : hakmilikPermohonanList2) {

                    Hakmilik hak = hakmilikPermohonan.getHakmilik();

                    List<HakmilikPihakBerkepentingan> listHakPihak;
                    if (hak != null) {
                        listHakPihak = hak.getSenaraiPihakBerkepentingan();
                        pihakKepentinganList.addAll(listHakPihak);
                        System.out.println("List Tuan Tanah" + pihakKepentinganList);
                        mohonPihakList = permohonanPihakService.getSenaraiPmohonPihak(idPermohonan);
                        pemohonList = pemohonService.findPemohonByIdPermohonan(idPermohonan);
                        pemilik = hakmilikPihakKepentinganService.findPihakActiveByHakmilik(hak);
                        String kodPihak = "PM";

                        listTuanTanahNew = hakmilikPihakKepentinganService.findHakmilikPihakByKod(hak, kodPihak);
                        System.out.println("List Tuan Tanah" + listTuanTanah);
                        size = listHakPihak.size();
                    }
                }

                if (mohonPihakList != null) {
                    syer1 = new String[mohonPihakList.size()];
                    syer2 = new String[mohonPihakList.size()];
                    for (int i = 0; i < mohonPihakList.size(); i++) {
                        PermohonanPihak pp = mohonPihakList.get(i);
                        syer1[i] = String.valueOf(pp.getSyerPembilang());
                        syer2[i] = String.valueOf(pp.getSyerPenyebut());
                    }
                }
                rehydrateNext(idPermohonan);
            }
            rehydrateNext(idPermohonan);
            String[] tname = {"permohonan"};
            Object[] model = {permohonan};

            listPemohon = pemohonDAO.findByEqualCriterias(tname, model, null);

            ArrayList listDelete = new ArrayList();

            for (int i = 0; i < listPemohon.size(); i++) {

                for (int j = 0; j < listTuanTanah.size(); j++) {

                    if (listPemohon.get(i).getPihak().equals(listTuanTanah.get(j))) {

                        listDelete.add(listPemohon.get(i).getPihak());

                    }
                }
            }

            int count = 0;

            for (int i = 0; i < listDelete.size(); i++) {

                listTuanTanah.remove(listPemohon.get(i).getPihak());
                count++;
            }
        } catch (Exception ex) {
        }
    }

    public void rehydrateNext(String idMohon) {

        permohonan = permohonanDAO.findById(idMohon);

        if (idPermohonan != null) {
            permohonan = permohonanDAO.findById(idPermohonan);
            cawangan = permohonan.getCawangan();
            kodDaerah = permohonan.getCawangan().getDaerah().getNama();
            String bandarPekanMukim = cawangan.getDaerah().getKod();
            if (StringUtils.isNotBlank(bandarPekanMukim)) {
                setListBandarPekanMukim(enforceService.getSenaraiKodBPMByCawangan(bandarPekanMukim));
                penyukatanBPM();
            }
            penyukatanBPM();
        }

        if (idPermohonan != null) {

            if (rizab != null) {
                bandarPekanMukim = rizab.getBandarPekanMukim();
            }
            amountMH = BigDecimal.ZERO;
            convH = BigDecimal.ZERO;
            amount = BigDecimal.ZERO;
            totalLuas = BigDecimal.ZERO;
            System.out.println("amountMH" + amountMH);
            System.out.println("convH" + convH);
            System.out.println("flag pba >> " + flagPBA);
            if (flagPBA == "1") {
                hakmilikPermohonanListNew = hakmilikpermohonanservice.findIDMHByIDMohonPBA(idPermohonan); //permohonan.getSenaraiHakmilik();
            } else {
                hakmilikPermohonanListNew = hakmilikpermohonanservice.findIDMHByIDMohon(idPermohonan); //permohonan.getSenaraiHakmilik();
            }
            tanahRizabList = hakmilikpermohonanservice.findIdHakmilikTanahXAA(idPermohonan);
            senaraiTanahAA = hakmilikpermohonanservice.findIdHakmilikTanahAA(idPermohonan);

            for (int i = 0; i < hakmilikPermohonanListNew.size(); i++) {
                System.out.println("masuk sini brape kali");
                System.out.println("hakmilikPermohonanListNew.size():" + hakmilikPermohonanListNew.size());
                try {
                    BigDecimal luas = hakmilikPermohonanListNew.get(i).getLuasTerlibat();
                    String cekbx = hakmilikPermohonanListNew.get(i).getPegangan();
                    if (hakmilikPermohonanListNew.get(i).getHakmilik() != null) {
                        String name = hakmilikPermohonanListNew.get(i).getHakmilik().getKodUnitLuas().getKod();
                        System.out.println("---- hakmilikPermohonanListNew.get(i).getPegangan() ---- " + i + " " + hakmilikPermohonanListNew.get(i).getPegangan());

                        if (hakmilikPermohonanListNew.get(i).getLuasTerlibat() == null) {
                            luasTerlibat.add("");
                        } else if (hakmilikPermohonanListNew.get(i).getLuasTerlibat() != null) {
                            luasTerlibat.add(luas.toString());
                            if (name.equals("H")) {
                                System.out.println("Hektar");
                                System.out.println(luasTerlibat.get(i));
                                BigDecimal luasHektar = new BigDecimal(luasTerlibat.get(i));
                                convLuas = calcTax.toMeter(name, luasHektar);
                                amount = amount.add(convLuas);

                            }
                            if (name.equals("M")) {
                                System.out.println("Meter Persegi");
                                System.out.println(luasTerlibat.get(i));
                                totalLuas = totalLuas.add(new BigDecimal(luasTerlibat.get(i)));
                            }

                            amountMH = totalLuas.add(amount);
                            convH = calcTax.toHectare("M", amountMH);
                            System.out.println(convH);
                        }
                    } else {
                        String name = hakmilikPermohonanListNew.get(i).getKodUnitLuasBaru().getKod();
                        System.out.println("---- hakmilikPermohonanListNew.get(i).getPegangan() ---- " + i + " " + hakmilikPermohonanListNew.get(i).getPegangan());

                        if (hakmilikPermohonanListNew.get(i).getLuasTerlibat() == null) {
                            luasTerlibat.add("");
                        } else if (hakmilikPermohonanListNew.get(i).getLuasTerlibat() != null) {
                            luasTerlibat.add(luas.toString());
                            if (name.equals("H")) {
                                System.out.println("Hektar");
                                System.out.println(luasTerlibat.get(i));
                                BigDecimal luasHektar = new BigDecimal(luasTerlibat.get(i));
                                convLuas = calcTax.toMeter(name, luasHektar);
                                amount = amount.add(convLuas);

                            }
                            if (name.equals("M")) {
                                System.out.println("Meter Persegi");
                                System.out.println(luasTerlibat.get(i));
                                totalLuas = totalLuas.add(new BigDecimal(luasTerlibat.get(i)));
                            }

                            amountMH = totalLuas.add(amount);
                            convH = calcTax.toHectare("M", amountMH);
                            System.out.println(convH);
                        }
                    }

                } catch (Exception e) {
                }

                try {
                    String name = hakmilikPermohonanListNew.get(i).getKodUnitLuas().getKod();
                    kodUnitLuas.add(name);
                    penyukatanBPM();
                } catch (Exception e) {
                    kodUnitLuas.add("");
                }
            }

            try {
                kodPenyerah = permohonan.getKodPenyerah().getNama();
                penyerahKod = permohonan.getKodPenyerah().getKod();
            } catch (Exception j) {
            }

            if (permohonan.getPenyerahJenisPengenalan() != null) {
                penyerahJenisPengenalan1 = permohonan.getPenyerahJenisPengenalan().getNama();
            }

            penyerahNoPengenalan = permohonan.getPenyerahNoPengenalan();
            pNoPengenalan = permohonan.getPenyerahNoPengenalan();
            penyerahNama = permohonan.getPenyerahNama();
            penyerahAlamat1 = permohonan.getPenyerahAlamat1();
            penyerahAlamat2 = permohonan.getPenyerahAlamat2();
            penyerahAlamat3 = permohonan.getPenyerahAlamat3();
            penyerahAlamat4 = permohonan.getPenyerahAlamat4();
            penyerahPoskod = permohonan.getPenyerahPoskod();
            penyerahNegeri = permohonan.getPenyerahNegeri().getKod();
            penyerahNegeri1 = permohonan.getPenyerahNegeri().getKod();
            penyerahNoTelefon = permohonan.getPenyerahNoTelefon1();
            penyerahEmail = permohonan.getPenyerahEmail();

            mrlList = service.findByidPermohonanList(idPermohonan);
            permohonanRujukanLuar = service.findByidPermohonanNoRujsurat(idPermohonan);
            System.out.println("Mohon rujukan luar " + mrlList.size());
            if (mrlList.size() != 0) {
                if (mrlList.get(0).getNoRujukan() != null) {
                    noRujukan = permohonanRujukanLuar.getNoRujukan();
                    System.out.println("no ruj " + permohonanRujukanLuar.getNoRujukan());
                } else {
                    noRujukan = "TIDAK DINYATAKAN";
                }

                if (mrlList.get(0).getTarikhRujukan() != null) {
                    tarikhRujukan = sdf.format(permohonanRujukanLuar.getTarikhRujukan()).substring(0, 10);
                } else {
                    tarikhRujukan = "TIDAK DINYATAKAN";
                }

            } else {
                permohonanRujukanLuar = new PermohonanRujukanLuar();
                noRujukan = "";
            }

            StatusTanahLepasPengambilan statusTanahLepasPengambilan = service.findMohonLPSAmbilByIdMohonKodLPS(idPermohonan, "HS");
            if (statusTanahLepasPengambilan != null) {
                hakmilikSambungan = statusTanahLepasPengambilan.getKodStatusTanahLepasPengambilan().getKod();
            }
            StatusTanahLepasPengambilan statusTanahLepasPengambilan1 = service.findMohonLPSAmbilByIdMohonKodLPS(idPermohonan, "TR");
            if (statusTanahLepasPengambilan1 != null) {
                tanahRizab = statusTanahLepasPengambilan1.getKodStatusTanahLepasPengambilan().getKod();
            }
            StatusTanahLepasPengambilan statusTanahLepasPengambilan2 = service.findMohonLPSAmbilByIdMohonKodLPS(idPermohonan, "TK");
            if (statusTanahLepasPengambilan2 != null) {
                tanahKerajaan = statusTanahLepasPengambilan2.getKodStatusTanahLepasPengambilan().getKod();
            }
        }
    }

    public Resolution penyukatanBPM() {
        String kodDaerah = getKodDaerah();
//        String kodBandarPekanMukim= getContext().getRequest().getParameter("bandarPekanMukim");
        String kodBandarPekanMukim = getKodBandarPekanMukim();
//        String bandarPekanMukim = getKodBandarPekanMukim();
//        bandarPekanMukim = getBandarPekanMukim();
//        logger.info("------kodDaerah------ " + kodDaerah);
//        logger.info("------kodBandarPekanMukim------ " + kodBandarPekanMukim);
        String sql = null;
        Session s = sessionProvider.get();
        Query q = null;
        if (kodDaerah == null || kodDaerah.equals("")) {
            sql = "select bpm from KodBandarPekanMukim bpm ";
            q = s.createQuery(sql);
        } else {
            sql = "select bpm from KodBandarPekanMukim bpm where bpm.daerah.kod = :kod ";
            q = s.createQuery(sql);
            q.setString("kod", kodDaerah);
        }

        senaraiBPM = q.list();
//        logger.info("senaraiBPM.size() : " + senaraiBPM.size());
        return new ForwardResolution("/WEB-INF/jsp/pengambilan/kemasukan_tanah_kerajaanRizab.jsp").addParameter("popup", "true");

    }

    public Resolution simpanPemohon() {

        Pengguna p = (Pengguna) context.getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);

        InfoAudit info = p.getInfoAudit();

        info.setDimasukOleh(p);
        info.setTarikhMasuk(new java.util.Date());

        String idPihak = getContext().getRequest().getParameter("idPihak");

        Pihak phk = pihakDAO.findById(Long.valueOf(idPihak));
        Pemohon pemohon = new Pemohon();
        pemohon.setPermohonan(permohonan);
        pemohon.setPihak(phk);
        pemohon.setCawangan(p.getKodCawangan());
        pemohon.setInfoAudit(info);

        pemohon = conService.simpanPemohon(pemohon);

        String[] tname = {"permohonan"};
        Object[] model = {permohonan};

        listPemohon = pemohonDAO.findByEqualCriterias(tname, model, null);
        return new StreamingResolution("text/plain", String.valueOf(pemohon.getIdPemohon()));

    }

    public Resolution deletePemohon() {

        String idPemohon = getContext().getRequest().getParameter("idPemohon");
        if (idPemohon != null) {
            Pemohon pemohon = pemohonService.findById(idPemohon);
            if (pemohon != null) {
                pemohonService.delete(pemohon);
                listPemohon = pemohonService.findPemohonByIdPermohonan(idPermohonan);
            }
        }

        return new JSP("common/maklumat_pemohon.jsp").addParameter("tab", "true");
    }

    public Resolution refreshpage() {
        rehydrate();
        return new JSP("common/maklumat_pemohon.jsp").addParameter("tab", "true");
    }

    public Resolution simpanPihak() {
        Pengguna pg = (Pengguna) context.getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        InfoAudit ia = new InfoAudit();
        ia.setDiKemaskiniOleh(pg);
        ia.setTarikhKemaskini(new java.util.Date());
        Pihak p = pihakDAO.findById(pihak.getIdPihak());
        p.setSuratAlamat1(pihak.getSuratAlamat1());
        p.setSuratAlamat2(pihak.getSuratAlamat2());
        p.setSuratAlamat3(pihak.getSuratAlamat3());
        p.setSuratAlamat4(pihak.getSuratAlamat4());
        p.setSuratPoskod(pihak.getSuratPoskod());
        p.setSuratNegeri(pihak.getSuratNegeri());
        p.setNoTelefon1(pihak.getNoTelefon1());
        p.setEmail(pihak.getEmail());
        ia.setDimasukOleh(p.getInfoAudit().getDimasukOleh());
        ia.setTarikhMasuk(p.getInfoAudit().getTarikhMasuk());
        p.setInfoAudit(ia);
        conService.simpanPihak(p);
        return refreshpage();
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

    public String[] getArrayCheckBox() {
        return arrayCheckBox;
    }

    public void setArrayCheckBox(String[] arrayCheckBox) {
        this.arrayCheckBox = arrayCheckBox;
    }

    public Pihak getPihak() {
        return pihak;
    }

    public void setPihak(Pihak pihak) {
        this.pihak = pihak;
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

    public Permohonan getPermohonan() {
        return permohonan;
    }

    public void setPermohonan(Permohonan permohonan) {
        this.permohonan = permohonan;
    }

    public String getIdPermohonan() {
        return idPermohonan;
    }

    public void setIdPermohonan(String idPermohonan) {
        this.idPermohonan = idPermohonan;
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

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public String[] getSyer1() {
        return syer1;
    }

    public void setSyer1(String[] syer1) {
        this.syer1 = syer1;
    }

    public String[] getSyer2() {
        return syer2;
    }

    public void setSyer2(String[] syer2) {
        this.syer2 = syer2;
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

    public TanahRizabPermohonan getRizab() {
        return rizab;
    }

    public void setRizab(TanahRizabPermohonan rizab) {
        this.rizab = rizab;
    }

    public List<TanahRizabPermohonan> getTanahRizabList() {
        return tanahRizabList;
    }

    public void setTanahRizabList(List<TanahRizabPermohonan> tanahRizabList) {
        this.tanahRizabList = tanahRizabList;
    }

    public HakmilikPermohonan getHakmilikPermohonan() {
        return hakmilikPermohonan;
    }

    public void setHakmilikPermohonan(HakmilikPermohonan hakmilikPermohonan) {
        this.hakmilikPermohonan = hakmilikPermohonan;
    }

    public PermohonanPihak getMp() {
        return mp;
    }

    public void setMp(PermohonanPihak mp) {
        this.mp = mp;
    }

    public String[] getListluasTerlibat() {
        return listluasTerlibat;
    }

    public void setListluasTerlibat(String[] listluasTerlibat) {
        this.listluasTerlibat = listluasTerlibat;
    }

    public String getNoHakmilik() {
        return noHakmilik;
    }

    public void setNoHakmilik(String noHakmilik) {
        this.noHakmilik = noHakmilik;
    }

    public KodUrusan getMohonKodUrusan() {
        return mohonKodUrusan;
    }

    public void setMohonKodUrusan(KodUrusan mohonKodUrusan) {
        this.mohonKodUrusan = mohonKodUrusan;
    }

    public BigDecimal getConvLuas() {
        return convLuas;
    }

    public void setConvLuas(BigDecimal convLuas) {
        this.convLuas = convLuas;
    }

    public BigDecimal getTotalLuas() {
        return totalLuas;
    }

    public void setTotalLuas(BigDecimal totalLuas) {
        this.totalLuas = totalLuas;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public BigDecimal getAmountMH() {
        return amountMH;
    }

    public void setAmountMH(BigDecimal amountMH) {
        this.amountMH = amountMH;
    }

    public BigDecimal getConvH() {
        return convH;
    }

    public void setConvH(BigDecimal convH) {
        this.convH = convH;
    }

    public String getNamajaga() {
        return namajaga;
    }

    public void setNamajaga(String namajaga) {
        this.namajaga = namajaga;
    }

    public String getJagaTel() {
        return jagaTel;
    }

    public void setJagaTel(String jagaTel) {
        this.jagaTel = jagaTel;
    }

    public String getJagaFax() {
        return jagaFax;
    }

    public void setJagaFax(String jagaFax) {
        this.jagaFax = jagaFax;
    }

    public String getJagaEmail() {
        return jagaEmail;
    }

    public void setJagaEmail(String jagaEmail) {
        this.jagaEmail = jagaEmail;
    }

    public KodBandarPekanMukim getBandarPekanMukim() {
        return bandarPekanMukim;
    }

    public void setBandarPekanMukim(KodBandarPekanMukim bandarPekanMukim) {
        this.bandarPekanMukim = bandarPekanMukim;
    }

    public KodRizab getKodRizab() {
        return kodRizab;
    }

    public void setKodRizab(KodRizab kodRizab) {
        this.kodRizab = kodRizab;
    }

    public String getNoLot() {
        return noLot;
    }

    public void setNoLot(String noLot) {
        this.noLot = noLot;
    }

    public String getLuasAmbil() {
        return luasAmbil;
    }

    public void setLuasAmbil(String luasAmbil) {
        this.luasAmbil = luasAmbil;
    }

    public String getAdaCukai() {
        return adaCukai;
    }

    public void setAdaCukai(String adaCukai) {
        this.adaCukai = adaCukai;
    }

    public String getCukai() {
        return cukai;
    }

    public void setCukai(String cukai) {
        this.cukai = cukai;
    }

    public String getPegangan() {
        return pegangan;
    }

    public void setPegangan(String pegangan) {
        this.pegangan = pegangan;
    }

    public String getPenarikBalikan() {
        return penarikBalikan;
    }

    public void setPenarikBalikan(String penarikBalikan) {
        this.penarikBalikan = penarikBalikan;
    }

    public String getBpm() {
        return bpm;
    }

    public void setBpm(String bpm) {
        this.bpm = bpm;
    }

    public KodDaerah getDaerah() {
        return daerah;
    }

    public void setDaerah(KodDaerah daerah) {
        this.daerah = daerah;
    }

    public String getKoddaerahbpm() {
        return koddaerahbpm;
    }

    public void setKoddaerahbpm(String koddaerahbpm) {
        this.koddaerahbpm = koddaerahbpm;
    }

    public String getKodDaerah() {
        return kodDaerah;
    }

    public void setKodDaerah(String kodDaerah) {
        this.kodDaerah = kodDaerah;
    }

    public String getKodBandarPekanMukim() {
        return kodBandarPekanMukim;
    }

    public void setKodBandarPekanMukim(String kodBandarPekanMukim) {
        this.kodBandarPekanMukim = kodBandarPekanMukim;
    }

    public List<KodBandarPekanMukim> getSenaraiBPM() {
        return senaraiBPM;
    }

    public void setSenaraiBPM(List<KodBandarPekanMukim> senaraiBPM) {
        this.senaraiBPM = senaraiBPM;
    }

    public List<KodBandarPekanMukim> getListBandarPekanMukim() {
        return listBandarPekanMukim;
    }

    public void setListBandarPekanMukim(List<KodBandarPekanMukim> listBandarPekanMukim) {
        this.listBandarPekanMukim = listBandarPekanMukim;
    }

    public static String getStaticKodBandarPekanMukim() {
        return staticKodBandarPekanMukim;
    }

    public static void setStaticKodBandarPekanMukim(String staticKodBandarPekanMukim) {
        summaryPermohonan.staticKodBandarPekanMukim = staticKodBandarPekanMukim;
    }

    public KodCawangan getCawangan() {
        return cawangan;
    }

    public void setCawangan(KodCawangan cawangan) {
        this.cawangan = cawangan;
    }

    public String getIdTanahRizabPermohonan() {
        return idTanahRizabPermohonan;
    }

    public void setIdTanahRizabPermohonan(String idTanahRizabPermohonan) {
        this.idTanahRizabPermohonan = idTanahRizabPermohonan;
    }

    public String getFlagPBA() {
        return flagPBA;
    }

    public void setFlagPBA(String flagPBA) {
        this.flagPBA = flagPBA;
    }

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }

    public String getKodLot() {
        return kodLot;
    }

    public void setKodLot(String kodLot) {
        this.kodLot = kodLot;
    }

    public List<KodSeksyen> getListKodSeksyen() {
        return listKodSeksyen;
    }

    public void setListKodSeksyen(List<KodSeksyen> listKodSeksyen) {
        this.listKodSeksyen = listKodSeksyen;
    }

    public String getKodSeksyen() {
        return kodSeksyen;
    }

    public void setKodSeksyen(String kodSeksyen) {
        this.kodSeksyen = kodSeksyen;
    }

    public String getKodCawangan() {
        return kodCawangan;
    }

    public void setKodCawangan(String kodCawangan) {
        this.kodCawangan = kodCawangan;
    }

    public List<Hakmilik> getList() {
        return list;
    }

    public void setList(List<Hakmilik> list) {
        this.list = list;
    }

    public List<String> getLuasTerlibat() {
        return luasTerlibat;
    }

    public void setLuasTerlibat(List<String> luasTerlibat) {
        this.luasTerlibat = luasTerlibat;
    }

    public List<String> getLuasTerlibat1() {
        return luasTerlibat1;
    }

    public void setLuasTerlibat1(List<String> luasTerlibat1) {
        this.luasTerlibat1 = luasTerlibat1;
    }

    public List<String> getKodUnitLuas() {
        return kodUnitLuas;
    }

    public void setKodUnitLuas(List<String> kodUnitLuas) {
        this.kodUnitLuas = kodUnitLuas;
    }

    public List<TanahRizabPermohonan> getSenaraiTanahAA() {
        return senaraiTanahAA;
    }

    public void setSenaraiTanahAA(List<TanahRizabPermohonan> senaraiTanahAA) {
        this.senaraiTanahAA = senaraiTanahAA;
    }

    public List<KodBandarPekanMukim> getSenaraiKodBPM() {
        return senaraiKodBPM;
    }

    public void setSenaraiKodBPM(List<KodBandarPekanMukim> senaraiKodBPM) {
        this.senaraiKodBPM = senaraiKodBPM;
    }

    public BigDecimal getLuasT() {
        return luasT;
    }

    public void setLuasT(BigDecimal luasT) {
        this.luasT = luasT;
    }

    public String getKodUnit() {
        return kodUnit;
    }

    public void setKodUnit(String kodUnit) {
        this.kodUnit = kodUnit;
    }

    public int getBandarPekanMukimBaru() {
        return bandarPekanMukimBaru;
    }

    public void setBandarPekanMukimBaru(int bandarPekanMukimBaru) {
        this.bandarPekanMukimBaru = bandarPekanMukimBaru;
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

    public List<PermohonanRujukanLuar> getMrlList() {
        return mrlList;
    }

    public void setMrlList(List<PermohonanRujukanLuar> mrlList) {
        this.mrlList = mrlList;
    }

    public List<HakmilikPermohonan> getHakmilikPermohonanListNew() {
        return hakmilikPermohonanListNew;
    }

    public void setHakmilikPermohonanListNew(List<HakmilikPermohonan> hakmilikPermohonanListNew) {
        this.hakmilikPermohonanListNew = hakmilikPermohonanListNew;
    }

    public String getPenyerahNoPengenalan() {
        return penyerahNoPengenalan;
    }

    public void setPenyerahNoPengenalan(String penyerahNoPengenalan) {
        this.penyerahNoPengenalan = penyerahNoPengenalan;
    }

    public String getpNoPengenalan() {
        return pNoPengenalan;
    }

    public void setpNoPengenalan(String pNoPengenalan) {
        this.pNoPengenalan = pNoPengenalan;
    }

    public String getPenyerahNama() {
        return penyerahNama;
    }

    public void setPenyerahNama(String penyerahNama) {
        this.penyerahNama = penyerahNama;
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

    public String getPenyerahPoskod() {
        return penyerahPoskod;
    }

    public void setPenyerahPoskod(String penyerahPoskod) {
        this.penyerahPoskod = penyerahPoskod;
    }

    public String getPenyerahNegeri() {
        return penyerahNegeri;
    }

    public void setPenyerahNegeri(String penyerahNegeri) {
        this.penyerahNegeri = penyerahNegeri;
    }

    public String getPenyerahNegeri1() {
        return penyerahNegeri1;
    }

    public void setPenyerahNegeri1(String penyerahNegeri1) {
        this.penyerahNegeri1 = penyerahNegeri1;
    }

    public String getPenyerahNoTelefon() {
        return penyerahNoTelefon;
    }

    public void setPenyerahNoTelefon(String penyerahNoTelefon) {
        this.penyerahNoTelefon = penyerahNoTelefon;
    }

    public String getPenyerahEmail() {
        return penyerahEmail;
    }

    public void setPenyerahEmail(String penyerahEmail) {
        this.penyerahEmail = penyerahEmail;
    }

    public String getPenyerahJenisPengenalan1() {
        return penyerahJenisPengenalan1;
    }

    public void setPenyerahJenisPengenalan1(String penyerahJenisPengenalan1) {
        this.penyerahJenisPengenalan1 = penyerahJenisPengenalan1;
    }

    public String getPenyerahKod() {
        return penyerahKod;
    }

    public void setPenyerahKod(String penyerahKod) {
        this.penyerahKod = penyerahKod;
    }

    public String getKodPenyerah() {
        return kodPenyerah;
    }

    public void setKodPenyerah(String kodPenyerah) {
        this.kodPenyerah = kodPenyerah;
    }

    public String getHakmilikSambungan() {
        return hakmilikSambungan;
    }

    public void setHakmilikSambungan(String hakmilikSambungan) {
        this.hakmilikSambungan = hakmilikSambungan;
    }

    public String getTanahRizab() {
        return tanahRizab;
    }

    public void setTanahRizab(String tanahRizab) {
        this.tanahRizab = tanahRizab;
    }

    public String getTanahKerajaan() {
        return tanahKerajaan;
    }

    public void setTanahKerajaan(String tanahKerajaan) {
        this.tanahKerajaan = tanahKerajaan;
    }

    public String getNoRujukan() {
        return noRujukan;
    }

    public void setNoRujukan(String noRujukan) {
        this.noRujukan = noRujukan;
    }

    public String getTarikhRujukan() {
        return tarikhRujukan;
    }

    public void setTarikhRujukan(String tarikhRujukan) {
        this.tarikhRujukan = tarikhRujukan;
    }

    public SimpleDateFormat getSdf() {
        return sdf;
    }

    public void setSdf(SimpleDateFormat sdf) {
        this.sdf = sdf;
    }

    public PengambilanAPTService getPengambilanAPTService() {
        return pengambilanAPTService;
    }

    public void setPengambilanAPTService(PengambilanAPTService pengambilanAPTService) {
        this.pengambilanAPTService = pengambilanAPTService;
    }

    public PermohonanPengambilanHakmilik getPermohonanPengambilanHakmilik() {
        return permohonanPengambilanHakmilik;
    }

    public void setPermohonanPengambilanHakmilik(PermohonanPengambilanHakmilik permohonanPengambilanHakmilik) {
        this.permohonanPengambilanHakmilik = permohonanPengambilanHakmilik;
    }

    public PermohonanPengambilan getPermohonanPengambilan() {
        return permohonanPengambilan;
    }

    public void setPermohonanPengambilan(PermohonanPengambilan permohonanPengambilan) {
        this.permohonanPengambilan = permohonanPengambilan;
    }

    public List<PermohonanPengambilanHakmilik> getListpermohonanPengambilan() {
        return listpermohonanPengambilan;
    }

    public void setListpermohonanPengambilan(List<PermohonanPengambilanHakmilik> listpermohonanPengambilan) {
        this.listpermohonanPengambilan = listpermohonanPengambilan;
    }

    public List<PermohonanPengambilanHakmilik> getListPermohonanPengambilan() {
        return listPermohonanPengambilan;
    }

    public void setListPermohonanPengambilan(List<PermohonanPengambilanHakmilik> listPermohonanPengambilan) {
        this.listPermohonanPengambilan = listPermohonanPengambilan;
    }

    public List<MaklumatTanahPengambilanForm> getListmaklumatTanahPengambilanForms() {
        return listmaklumatTanahPengambilanForms;
    }

    public void setListmaklumatTanahPengambilanForms(List<MaklumatTanahPengambilanForm> listmaklumatTanahPengambilanForms) {
        this.listmaklumatTanahPengambilanForms = listmaklumatTanahPengambilanForms;
    }

    public String getNamaUrusan() {
        return namaUrusan;
    }

    public void setNamaUrusan(String namaUrusan) {
        this.namaUrusan = namaUrusan;
    }

}
