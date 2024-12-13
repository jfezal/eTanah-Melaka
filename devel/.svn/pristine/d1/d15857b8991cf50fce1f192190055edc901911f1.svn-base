/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.stripes.pengambilan;

/**
 *
 * @author massita
 */
import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.dao.PermohonanDAO;
import etanah.model.HakmilikPermohonan;
import etanah.model.Permohonan;
import etanah.model.Pengguna;
import etanah.model.InfoAudit;
import etanah.model.Hakmilik;
import etanah.model.KodUOM;
import etanah.dao.KodUOMDAO;
import etanah.dao.HakmilikDAO;
import etanah.service.common.HakmilikService;
import etanah.service.common.HakmilikPermohonanService;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.RedirectResolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;
import net.sourceforge.stripes.action.ForwardResolution;
import etanah.model.HakmilikPihakBerkepentingan;
import java.math.BigDecimal;
import etanah.service.CalcTax;
import etanah.service.PermohonanSupayaBantahanService;
import etanah.model.TanahRizabPermohonan;
import etanah.service.LaporanPelukisPelanService;
import etanah.model.KodRizab;
import etanah.model.KodDaerah;
import etanah.model.KodBandarPekanMukim;
import etanah.dao.TanahRizabPermohonanDAO;
import etanah.model.PermohonanPihak;

//------------------------------------------//
import etanah.service.JupemService;
import org.apache.commons.lang.StringUtils;
import etanah.service.BPelService;
import oracle.bpel.services.workflow.task.model.Task;
import etanah.model.FasaPermohonan;
import etanah.model.IntegrasiPermohonanButir;
import etanah.dao.IntegrasiPermohonanDAO;
import etanah.model.IntegrasiPermohonan;
import etanah.model.KodUrusan;
//import etanah.dao.IntegrasiDAO;
import etanah.view.etanahActionBeanContext;
import etanah.model.Dokumen;
import java.io.*;
import java.util.*;
import etanah.model.Integrasi;
import etanah.service.*;
import etanah.service.common.LelongService;
import etanah.view.pengambilan.validator.suspenPBBorangKValidator;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Query;

//------------------------------------------//
@UrlBinding("/pengambilan/maklumat_hakmilikpengambilanTerdahulu")
public class MaklumatHakmilikTerdahuluActionBean extends AbleActionBean {

    @Inject
    PermohonanDAO permohonanDAO;
//    @Inject
//    Permohonan permohonan;
    @Inject
    HakmilikPermohonanService hakmilikpermohonanservice;
    @Inject
    LaporanPelukisPelanService serviceTanah;
    @Inject
    HakmilikService hakmilikService;
    @Inject
    PermohonanSupayaBantahanService permohonanSupayaBantahanService;
    String idHakmilik;
    @Inject
    Hakmilik hakmilik;
    @Inject
    KodUOM KodOUM;
    @Inject
    HakmilikDAO hakmilikDAO;
    @Inject
    CalcTax calcTax;
    @Inject
    KodUOMDAO kodUOMDAO;
    @Inject
    TanahRizabPermohonanDAO tanahrizabPermohonanDAO;
    @Inject
    PengambilanService1 pengambilanService1;
    @Inject
    LelongService lelongService;
    private Permohonan permohonan;
    private List<Hakmilik> list;
    private List<HakmilikPermohonan> hakmilikPermohonanList;
    private List<HakmilikPihakBerkepentingan> pihakKepentinganList;
    private List<String> luasTerlibat = new ArrayList<String>();
    private List<String> kodUnitLuas = new ArrayList<String>();
    private List<TanahRizabPermohonan> senaraiTanahAA;
    private TanahRizabPermohonan rizab;
    private List<TanahRizabPermohonan> tanahRizabList;
    private HakmilikPermohonan hakmilikPermohonan;
    private PermohonanPihak mp;
    private int size = 0;
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
    private KodDaerah daerah;
    private String koddaerahbpm;
    private String kodDaerah;
    private String idSblm;
    private List<KodBandarPekanMukim> senaraiBPM;
    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;
    private static final Logger LOG = Logger.getLogger(MaklumatHakmilikTerdahuluActionBean.class);


    @DefaultHandler
    public Resolution showForm() {

        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        Permohonan p = permohonanDAO.findById(idPermohonan);

//        permohonanSupayaBantahanService.simpanPermohonanPihak(p, peng);


        if (idPermohonan != null) {
            permohonan = permohonanDAO.findById(idPermohonan);
            if (permohonan != null) {
                permohonanSupayaBantahanService.simpanPermohonanPihak(p, peng);
            }
        }



        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        return new JSP("pengambilan/maklumat_hakmilik_pengambilanTerdahulu.jsp").addParameter("tab", "true");
    }
    //@HttpCache(allow=false)

    public Resolution showForm2() {
//        idHakmilik = (String) getContext().getRequest().getParameter("idHakmilik");
//        return new JSP("common/paparan_maklumat_hakmilik_permohonan_terperinci.jsp").addParameter("tab", "true");

        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
         Permohonan p = permohonanDAO.findById(idPermohonan);

         if(p.getPermohonanSebelum() != null)
            idSblm = p.getPermohonanSebelum().getIdPermohonan();
        if(idSblm != null){
            getContext().getRequest().setAttribute("IdSblmIsNull", Boolean.TRUE);
        }else{
            addSimpleError("Haraf maaf sila masukkan id Permohonan Terdahulu");
            getContext().getRequest().setAttribute("IdSblmIsNull", Boolean.FALSE);
        }

        getContext().getRequest().setAttribute("!edit", Boolean.TRUE);
        return new JSP("pengambilan/maklumat_hakmilik_pengambilanTerdahulu.jsp").addParameter("tab", "true");
    }

    public Resolution showForm3() {
        return new JSP("pengambilan/maklumat_tanah_rizab.jsp").addParameter("tab", "true");
    }

    public Resolution showForm4() {
        return new JSP("pengambilan/AkuanDeposit.jsp").addParameter("tab", "true");
    }

    public Resolution hakMilikPopup() {
        return new JSP("pengambilan/kemasukan_hakmilik.jsp").addParameter("popup", "true").addParameter("showForm", "false");
    }

    public Resolution PengawalPopup() {
        return new JSP("pengambilan/kemasukan_pengawal_pegawai.jsp").addParameter("popup", "true").addParameter("showForm", "false");
    }

    public Resolution popup() {
        if (idHakmilik != null) {
            hakmilik = hakmilikDAO.findById(idHakmilik);
        }
        return new JSP("pengambilan/negerisembilan/penarikanbalik/maklumat_asas_tanah_pengambilanTerdahulu.jsp").addParameter("popup", "true").addParameter("showForm", "false");
    }

    public Resolution tanahKRPopup() {
        rizab = new TanahRizabPermohonan();
        return new JSP("pengambilan/kemasukan_tanah_kerajaanRizab.jsp").addParameter("popup", "true").addParameter("showForm", "true");
    }

    public Resolution tanahTDKPopup() {
        return new JSP("pengambilan/kemasukan_tanah_tdk.jsp").addParameter("popup", "true").addParameter("showForm", "false");
    }

    public Resolution tanahAAPopup() {
        return new JSP("pengambilan/Tanah_AA.jsp").addParameter("popup", "true").addParameter("showForm", "false");
    }

    public Resolution showEdittanahKR() {
        String idTanahRizabPermohonan = getContext().getRequest().getParameter("idTanahRizabPermohonan");
        rizab = tanahrizabPermohonanDAO.findById(Long.parseLong(idTanahRizabPermohonan));
//        rizab = rizab.getRizab();
//        cawangan = rizab.getCawangan();
//        daerah = rizab.getDaerah();
//        bandarPekanMukim = rizab.getBandarPekanMukim();
//        noLot = rizab.getNoLot();
//        noWarta = rizab.getNoWarta();
//        tarikhWarta = rizab.getTarikhWarta();
        return new JSP("pengambilan/kemasukan_tanah_kerajaanRizab.jsp").addParameter("popup", "true");
    }

    public Resolution showEdittanahAA() {
        String idTanahRizabPermohonan = getContext().getRequest().getParameter("idTanahRizabPermohonan");
        rizab = tanahrizabPermohonanDAO.findById(Long.parseLong(idTanahRizabPermohonan));
//        rizab = rizab.getRizab();
//        cawangan = rizab.getCawangan();
//        daerah = rizab.getDaerah();
//        bandarPekanMukim = rizab.getBandarPekanMukim();
//        noLot = rizab.getNoLot();
//        noWarta = rizab.getNoWarta();
//        tarikhWarta = rizab.getTarikhWarta();
        return new JSP("pengambilan/Tanah_AA.jsp").addParameter("popup", "true");
    }

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {
        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        idHakmilik = (String) getContext().getRequest().getParameter("idHakmilik");
        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        permohonan = permohonanDAO.findById(idPermohonan);

        if (permohonan.getPermohonanSebelum() != null) {
            String idSblm = permohonan.getPermohonanSebelum().getIdPermohonan();

            if (idSblm != null) {
                Permohonan Sebelum = permohonanDAO.findById(idSblm);
                Permohonan current = permohonanDAO.findById(idPermohonan);
//                hakmilikPermohonanList = current.getSenaraiHakmilik();
                hakmilikPermohonanList = Sebelum.getSenaraiHakmilik();
                tanahRizabList = hakmilikpermohonanservice.findIdHakmilikTanahXAA(idPermohonan);
                senaraiTanahAA = hakmilikpermohonanservice.findIdHakmilikTanahAA(idPermohonan);
                System.out.println(senaraiTanahAA.size());
                System.out.println(tanahRizabList);
//                 FasaPermohonan fasaPermohonan = pengambilanService1.findFasaPermohonan(idPermohonan);
//                  LOG.info("-------fasaPermohonan : " + fasaPermohonan.getKeputusan().getKod());
//                  LOG.info("-------id Sebelum : " + Sebelum.getIdPermohonan());
                  
         
//                       if (fasaPermohonan.getKeputusan().getKod().equals("8L")) {
//                                LOG.info("--------Lulus!!!-------");
//                                permohonan = permohonanDAO.findById(Sebelum.getIdPermohonan());
//                                permohonan.setStatus("TS");
//                                lelongService.saveOrUpdate(permohonan);
//                            }
//                       if (fasaPermohonan.getKeputusan().getKod().equals("8B")) {
//                                LOG.info("--------Tolak!!!-------");
//                                permohonan = permohonanDAO.findById(Sebelum.getIdPermohonan());
//                                permohonan.setStatus("BP");
//                                lelongService.saveOrUpdate(permohonan);
//                            }
            
            
            
            
                for (int i = 0; i < hakmilikPermohonanList.size(); i++) {


                    try {
                        BigDecimal luas = hakmilikPermohonanList.get(i).getLuasTerlibat();
                        String name = hakmilikPermohonanList.get(i).getKodUnitLuas().getKod();
                        if (hakmilikPermohonanList.get(i).getLuasTerlibat() == null) {
                            luasTerlibat.add("");
                        } else {
                            luasTerlibat.add(luas.toString());
                        }
                        if (hakmilikPermohonanList.get(i).getLuasTerlibat() != null) {
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

                    } catch (Exception e) {
                    }

                    try {
                        String name = hakmilikPermohonanList.get(i).getKodUnitLuas().getKod();
                        kodUnitLuas.add(name);
                    } catch (Exception e) {
                        kodUnitLuas.add("");
                    }
                }
            }
            
            
                 
            
            
            
            
            
        }
    }

    public Resolution simpanHakmilik() {
        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        Permohonan p = permohonanDAO.findById(idPermohonan);
        String kod = null;
//        JOptionPane.showConfirmDialog(null, hakmilikPermohonanList.size());
        if (luasTerlibat.isEmpty()) {
            addSimpleError("Sila Masukkan Luas yang diambil");

        } else {

            for (int i = 0; i < hakmilikPermohonanList.size(); i++) {
                HakmilikPermohonan hmp = hakmilikPermohonanList.get(i);

                try {
                    if (i < luasTerlibat.size()) {
                        if (luasTerlibat.get(i) != null) {
                            hmp.setLuasTerlibat(new BigDecimal(luasTerlibat.get(i)));
                        }
                        KodUOM kodUOM = hmp.getHakmilik().getKodUnitLuas();
                        hmp.setKodUnitLuas(kodUOM);
                    }
                } catch (Exception e) {
                    addSimpleError("Invalid Data");
                }
                hakmilikpermohonanservice.saveSingleHakmilikPermohonan(hmp);
                permohonanSupayaBantahanService.simpanPermohonanPihak(p, peng);

                rehydrate();
            }
        }

        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        addSimpleMessage("Maklumat telah berjaya disimpan.");
        return new RedirectResolution(MaklumatHakmilikTerdahuluActionBean.class, "locate");
//        return new ForwardResolution("/WEB-INF/jsp/pengambilan/maklumat_hakmilik_pengambilanTerdahulu.jsp").addParameter("tab", "true");
    }

    public Resolution deleteSingle() {
        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        Permohonan permohonan = permohonanDAO.findById(idPermohonan);
        InfoAudit ia = new InfoAudit();
        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        HakmilikPermohonan hmp = new HakmilikPermohonan();
        String id = getContext().getRequest().getParameter("id");
        hmp = hakmilikService.findHakmilikByIdHakmilik(Long.parseLong(id));

        if (hmp != null) {
//                JOptionPane.showMessageDialog(null, id);


            ia = peng.getInfoAudit();
            ia.setDiKemaskiniOleh(peng);
            ia.setTarikhKemaskini(new java.util.Date());
            hmp.setInfoAudit(ia);
            hmp.setHakmilik(hakmilik);
            hmp.setPermohonan(permohonan);
            hakmilikPermohonanList.remove(hmp);
            hakmilikService.deletehakmilikpermohonan(hakmilikPermohonanList);
        }
        return new RedirectResolution(MaklumatHakmilikTerdahuluActionBean.class);
    }

    public Resolution searchHakmilik() {

        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        String idH = getContext().getRequest().getParameter("idH");
        Permohonan permohonan = permohonanDAO.findById(idPermohonan);
        hakmilik = hakmilikService.findById(idH);
//        JOptionPane.showMessageDialog(null, hakmilik.getNoHakmilik());
        if (idPermohonan != null) {
            InfoAudit info = peng.getInfoAudit();
            info.setDimasukOleh(peng);
            info.setTarikhMasuk(new java.util.Date());
            HakmilikPermohonan hmp = new HakmilikPermohonan();
            hmp.setInfoAudit(info);
            hmp.setPermohonan(permohonan);
            hmp.setHakmilik(hakmilik);
            hakmilikPermohonanList.add(hmp);
            hakmilikService.saveOrUpdatehakmilikpermohonan(hmp);
        }
        addSimpleMessage("Maklumat telah berjaya disimpan");
        return new ForwardResolution("/WEB-INF/jsp/pengambilan/maklumat_hakmilik_pengambilanTerdahulu.jsp").addParameter("tab", "true");


    }

    public Resolution refreshpage() {
        rehydrate();
        return new RedirectResolution(MaklumatHakmilikTerdahuluActionBean.class, "locate");
    }

    public Resolution deleteTanahRizab() {
        InfoAudit ia = new InfoAudit();
        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        rizab = new TanahRizabPermohonan();
        String idtanahRizabPermohonan = getContext().getRequest().getParameter("idTanahRizabPermohonan");
        rizab = serviceTanah.findTanahRizabByIdTanahRizab(Long.parseLong(idtanahRizabPermohonan));

        if (rizab != null) {
            ia = peng.getInfoAudit();
            ia.setDiKemaskiniOleh(peng);
            ia.setTarikhKemaskini(new java.util.Date());
            rizab.setInfoAudit(ia);
            rizab.setPermohonan(permohonan);
            rizab.setCawangan(permohonan.getCawangan());
            //tanahrizabpermohonan.setCatatan(catatan);
            serviceTanah.deleteAll(rizab);
        }
        return new RedirectResolution(MaklumatHakmilikTerdahuluActionBean.class, "locate");
    }

    public Resolution simpan() {
        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        idHakmilik = (String) getContext().getRequest().getParameter("idHakmilik");

        if (idPermohonan != null) {
            Permohonan p = permohonanDAO.findById(idPermohonan);
            HakmilikPermohonan hmp = new HakmilikPermohonan();
            hmp.getHakmilik();
            hakmilikService.simpanAmbil(hakmilik, p);
            addSimpleMessage("Maklumat telah berjaya disimpan.");

        }
        return new ForwardResolution("/WEB-INF/jsp/pengambilan/maklumat_hakmilik_pengambilanTerdahulu.jsp").addParameter("tab", "true");
    }

    public Resolution simpanPengawal() {
        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        InfoAudit info = new InfoAudit();

        Permohonan permohonan = permohonanDAO.findById(idPermohonan);

        if (idPermohonan != null) {
            System.out.println("masuk sini");
            rizab = new TanahRizabPermohonan();
            bandarPekanMukim = new KodBandarPekanMukim();
            info.setDimasukOleh(peng);
            info.setTarikhMasuk(new java.util.Date());

            rizab.setInfoAudit(info);
            rizab.setPermohonan(permohonan);
            rizab.setNamaPenjaga(namajaga);
            rizab.setJagaTel(jagaTel);
            rizab.setJagaFax(jagaFax);
            rizab.setJagaEmail(jagaEmail);
            rizab.setBandarPekanMukim(bandarPekanMukim);
            serviceTanah.saveOrUpdatetanahRizab(rizab);
            addSimpleMessage("Maklumat telah berjaya disimpan");
        } else {
            System.out.println("masuk sini");
            rizab = new TanahRizabPermohonan();
            info.setDiKemaskiniOleh(peng);
            info.setTarikhKemaskini(new java.util.Date());

            rizab.setInfoAudit(info);
            rizab.setPermohonan(permohonan);
            rizab.setNamaPenjaga(namajaga);
            rizab.setJagaTel(jagaTel);
            rizab.setJagaFax(jagaFax);
            rizab.setJagaEmail(jagaEmail);
            serviceTanah.saveOrUpdatetanahRizab(rizab);
            addSimpleMessage("Maklumat telah berjaya disimpan");
        }

        return new ForwardResolution("/WEB-INF/jsp/pengambilan/maklumat_hakmilik_pengambilanTerdahulu.jsp").addParameter("tab", "true");
    }

    public Resolution simpanTanahTDK() {
        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        InfoAudit info = new InfoAudit();

        Permohonan permohonan = permohonanDAO.findById(idPermohonan);

        if (idPermohonan != null) {
            System.out.println("masuk sini");
            System.out.println(bandarPekanMukim);
            System.out.println(noLot);
            rizab = new TanahRizabPermohonan();
            info.setDimasukOleh(peng);
            info.setTarikhMasuk(new java.util.Date());

            rizab.setInfoAudit(info);
            rizab.setPermohonan(permohonan);
            rizab.setRizab(kodRizab);
            rizab.setNoLot(noLot);
            rizab.setLuasTerlibat(new BigDecimal(luasAmbil));
            rizab.setBandarPekanMukim(bandarPekanMukim);
            rizab.setCawangan(peng.getKodCawangan());
            rizab.setDaerah(daerah);
//            rizab.setJagaFax(luas);
            rizab.setAdaCukai(adaCukai);
            rizab.setCukai(new BigDecimal(cukai));
            serviceTanah.saveOrUpdatetanahRizab(rizab);
            addSimpleMessage("Maklumat telah berjaya disimpan");
        } else {
            System.out.println("masuk sana");
            rizab = new TanahRizabPermohonan();
            info.setDiKemaskiniOleh(peng);
            info.setTarikhKemaskini(new java.util.Date());

            rizab.setInfoAudit(info);
            rizab.setPermohonan(permohonan);
            rizab.setRizab(kodRizab);
//            rizab.setNoLot(noLot);
            rizab.setLuasTerlibat(new BigDecimal(luasAmbil));
            rizab.setBandarPekanMukim(bandarPekanMukim);
            rizab.setCawangan(peng.getKodCawangan());
            rizab.setDaerah(daerah);
//            rizab.setJagaFax(luas);
            rizab.setAdaCukai(adaCukai);
            rizab.setCukai(new BigDecimal(cukai));
            serviceTanah.saveOrUpdatetanahRizab(rizab);
            addSimpleMessage("Maklumat telah berjaya disimpan");
        }

        return new ForwardResolution("/WEB-INF/jsp/pengambilan/maklumat_hakmilik_pengambilanTerdahulu.jsp").addParameter("tab", "true");
    }

    public Resolution penyukatanBPM() {
        String kodDaerah = getKodDaerah();
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
        return new ForwardResolution("/WEB-INF/jsp/pengambilan/kemasukan_tanah_kerajaanRizab.jsp").addParameter("popup", "true");

    }

    public Resolution penyukatanBPMAA() {
        String kodDaerah = getKodDaerah();
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
        return new ForwardResolution("/WEB-INF/jsp/pengambilan/Tanah_AA.jsp").addParameter("popup", "true");

    }

    public Resolution simpanTanahRizab() {
        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = permohonanDAO.findById(idPermohonan);
        InfoAudit info = new InfoAudit();
        info.setDimasukOleh(peng);
        info.setTarikhMasuk(new java.util.Date());
        rizab.setInfoAudit(info);
        rizab.setPermohonan(permohonan);
        rizab.setCawangan(permohonan.getCawangan());
        serviceTanah.save(rizab);
        addSimpleMessage("Maklumat telah berjaya disimpan");
        return new ForwardResolution("/WEB-INF/jsp/pengambilan/maklumat_hakmilik_pengambilanTerdahulu.jsp").addParameter("tab", "true");
    }

    public Resolution simpanTanahAA() {
        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = permohonanDAO.findById(idPermohonan);
        InfoAudit info = new InfoAudit();
        info.setDimasukOleh(peng);
        info.setTarikhMasuk(new java.util.Date());
        rizab.setInfoAudit(info);
        rizab.setPermohonan(permohonan);
        rizab.setCawangan(permohonan.getCawangan());
        KodRizab kd = new KodRizab();
        kd.setKod(88);
        rizab.setRizab(kd);
        serviceTanah.save(rizab);
        addSimpleMessage("Maklumat telah berjaya disimpan");
        return new ForwardResolution("/WEB-INF/jsp/pengambilan/maklumat_hakmilik_pengambilanTerdahulu.jsp").addParameter("tab", "true");
    }

    public List<Hakmilik> getList() {
        return list;
    }

    public void setList(List<Hakmilik> list) {
        this.list = list;
    }

    public Hakmilik getHakmilik() {
        return hakmilik;
    }

    public void setHakmilik(Hakmilik hakmilik) {
        this.hakmilik = hakmilik;
    }

    public String getIdHakmilik() {
        return idHakmilik;
    }

    public void setIdHakmilik(String idHakmilik) {
        this.idHakmilik = idHakmilik;
    }

    public List<HakmilikPermohonan> getHakmilikPermohonanList() {
        return hakmilikPermohonanList;
    }

    public void setHakmilikPermohonanList(List<HakmilikPermohonan> hakmilikPermohonanList) {
        this.hakmilikPermohonanList = hakmilikPermohonanList;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

//    public String[] getAtasTanah() {
//        return luasTerlibat;
//    }
//
//    public void setAtasTanah(String[] luasTerlibat) {
//        this.luasTerlibat = luasTerlibat;
//    }
    public HakmilikPermohonan getHakmilikPermohonan() {
        return hakmilikPermohonan;
    }

    public void setHakmilikPermohonan(HakmilikPermohonan hakmilikPermohonan) {
        this.hakmilikPermohonan = hakmilikPermohonan;
    }

    public String getNoHakmilik() {
        return noHakmilik;
    }

    public void setNoHakmilik(String noHakmilik) {
        this.noHakmilik = noHakmilik;
    }

    public List<HakmilikPihakBerkepentingan> getPihakKepentinganList() {
        return pihakKepentinganList;
    }

    public void setPihakKepentinganList(List<HakmilikPihakBerkepentingan> pihakKepentinganList) {
        this.pihakKepentinganList = pihakKepentinganList;
    }

    public List<String> getLuasTerlibat() {
        return luasTerlibat;
    }

    public void setLuasTerlibat(List<String> luasTerlibat) {
        this.luasTerlibat = luasTerlibat;
    }

    public String[] getListluasTerlibat() {
        return listluasTerlibat;
    }

    public void setListluasTerlibat(String[] listluasTerlibat) {
        this.listluasTerlibat = listluasTerlibat;
    }

//        public List<String> getLuasdiambil() {
//        return luasdiambil;
//    }
//
//    public void setLuasdiambil(List<String> luasdiambil) {
//        this.luasdiambil = luasdiambil;
//    }
    public List<String> getKodUnitLuas() {
        return kodUnitLuas;
    }

    public void setKodUnitLuas(List<String> kodUnitLuas) {
        this.kodUnitLuas = kodUnitLuas;
    }

    public KodUOM getKodOUM() {
        return KodOUM;
    }

    public void setKodOUM(KodUOM KodOUM) {
        this.KodOUM = KodOUM;
    }

    public TanahRizabPermohonan getRizab() {
        return rizab;
    }

    public void setRizab(TanahRizabPermohonan rizab) {
        this.rizab = rizab;
    }

    public String getKodDaerah() {
        return kodDaerah;
    }

    public void setKodDaerah(String kodDaerah) {
        this.kodDaerah = kodDaerah;
    }

    public Permohonan getPermohonan() {
        return permohonan;
    }

    public void setPermohonan(Permohonan permohonan) {
        this.permohonan = permohonan;
    }

    public String getIdSblm() {
        return idSblm;
    }

    public void setIdSblm(String idSblm) {
        this.idSblm = idSblm;
    }

    public List<KodBandarPekanMukim> getSenaraiBPM() {
        return senaraiBPM;
    }

    public void setSenaraiBPM(List<KodBandarPekanMukim> senaraiBPM) {
        this.senaraiBPM = senaraiBPM;
    }

    public String getKoddaerahbpm() {
        return koddaerahbpm;
    }

    public void setKoddaerahbpm(String koddaerahbpm) {
        this.koddaerahbpm = koddaerahbpm;
    }

    public List<TanahRizabPermohonan> getTanahRizabList() {
        return tanahRizabList;
    }

    public void setTanahRizabList(List<TanahRizabPermohonan> tanahRizabList) {
        this.tanahRizabList = tanahRizabList;
    }

    public String getNoLot() {
        return noLot;
    }

    public void setNoLot(String noLot) {
        this.noLot = noLot;
    }

    public KodDaerah getDaerah() {
        return daerah;
    }

    public void setDaerah(KodDaerah daerah) {
        this.daerah = daerah;
    }

    public String getadaCukai() {
        return adaCukai;
    }

    public void setAdaCukai(String adaCukai) {
        this.adaCukai = adaCukai;
    }

    public String getcukai() {
        return cukai;
    }

    public void setCukai(String cukai) {
        this.cukai = cukai;
    }

    public String getLuasAmbil() {
        return luasAmbil;
    }

    public void setLuasAmbil(String luasAmbil) {
        this.luasAmbil = luasAmbil;
    }

    public KodRizab getKodRizab() {
        return kodRizab;
    }

    public void setKodRizab(KodRizab kodRizab) {
        this.kodRizab = kodRizab;
    }

    public KodBandarPekanMukim getBandarPekanMukim() {
        return bandarPekanMukim;
    }

    public void setBandarPekanMukim(KodBandarPekanMukim bandarPekanMukim) {
        this.bandarPekanMukim = bandarPekanMukim;
    }

    public String getJagaEmail() {
        return jagaEmail;
    }

    public void setJagaEmail(String jagaEmail) {
        this.jagaEmail = jagaEmail;
    }

    public String getJagaFax() {
        return jagaFax;
    }

    public void setJagaFax(String jagaFax) {
        this.jagaFax = jagaFax;
    }

    public String getJagaTel() {
        return jagaTel;
    }

    public void setJagaTel(String jagaTel) {
        this.jagaTel = jagaTel;
    }

    public String getNamajaga() {
        return namajaga;
    }

    public void setNamajaga(String namajaga) {
        this.namajaga = namajaga;
    }

    public BigDecimal getConvH() {
        return convH;
    }

    public void setConvH(BigDecimal convH) {
        this.convH = convH;
    }

    public BigDecimal getAmountMH() {
        return amountMH;
    }

    public void setAmountMH(BigDecimal amountMH) {
        this.amountMH = amountMH;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public BigDecimal getconvLuas() {
        return convLuas;
    }

    public void setconvLuas(BigDecimal convLuas) {
        this.convLuas = convLuas;
    }

    public BigDecimal gettotalLuas() {
        return totalLuas;
    }

    public void setTotalLuas(BigDecimal totalLuas) {
        this.totalLuas = totalLuas;
    }

    public PermohonanPihak getMp() {
        return mp;
    }

    public void setMp(PermohonanPihak mp) {
        this.mp = mp;
    }
    

    public List<TanahRizabPermohonan> getSenaraiTanahAA() {
        return senaraiTanahAA;
    }

    public void setSenaraiTanahAA(List<TanahRizabPermohonan> senaraiTanahAA) {
        this.senaraiTanahAA = senaraiTanahAA;
    }

}
