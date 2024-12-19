package etanah.view.stripes.pelupusan;

/*
 * Document : MaklumatTanahV2ActionBean.java Created on : Jun 14, 2012, 11:45 AM
 * Author : Aizuddin Orogenic Group Description: Maklumat Tanah MPJLB MLCRG
 */
import etanah.view.stripes.pelupusan.*;
import java.text.ParseException;
import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.dao.HakmilikDAO;
import etanah.dao.HakmilikPermohonanDAO;
import etanah.dao.KodBandarPekanMukimDAO;
import etanah.dao.KodDaerahDAO;
import etanah.dao.KodItemPermitDAO;
import etanah.dao.KodKategoriTanahDAO;
import etanah.dao.KodLotDAO;
import etanah.dao.KodRizabDAO;
import etanah.dao.KodSeksyenDAO;
import etanah.dao.KodSyaratNyataDAO;
import etanah.dao.KodUOMDAO;
import net.sourceforge.stripes.action.StreamingResolution;
import etanah.dao.PermohonanDAO;
import etanah.model.Hakmilik;
import etanah.model.HakmilikPermohonan;
import etanah.model.InfoAudit;
import etanah.model.KodBandarPekanMukim;
import etanah.model.KodDaerah;
import etanah.model.KodItemPermit;
import etanah.model.KodKategoriTanah;
import etanah.model.KodKegunaanTanah;
import etanah.model.KodLot;
import etanah.model.KodSekatanKepentingan;
import etanah.model.KodSeksyen;
import etanah.model.KodSyaratNyata;
import etanah.model.KodUOM;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.model.PermohonanLaporanPelan;
import etanah.model.PermohonanPermitItem;
import etanah.model.TanahRizabPermohonan;
import etanah.service.LaporanPelukisPelanService;
import etanah.service.PelupusanService;
import etanah.service.RegService;
import etanah.view.ListUtil;
import etanah.view.etanahActionBeanContext;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.HttpCache;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;
import org.apache.log4j.Logger;
import etanah.service.common.HakmilikService;
import etanah.view.stripes.pelupusan.disClass.DisLaporanTanahService;
import java.math.BigDecimal;
import java.util.ArrayList;
import net.sourceforge.stripes.action.RedirectResolution;
import org.hibernate.Session;
import org.apache.commons.lang.StringUtils;
import org.hibernate.Query;

@UrlBinding("/pelupusan/maklumat_tanahPbrz")
public class MaklumatTanahPbrzActionBean extends AbleActionBean {

    private static Logger logger = Logger.getLogger(MaklumatTanahPbrzActionBean.class);
    private static String kodDaerahStatic;
    @Inject
    DisLaporanTanahService disLaporanTanahService;
    @Inject
    LaporanPelukisPelanService laporanPelukisPelanService;
    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    HakmilikPermohonanDAO hakmilikPermohonanDAO;
    @Inject
    PelupusanService pelupusanService;
    @Inject
    ListUtil listUtil;
    @Inject
    HakmilikDAO hakmilikDAO;
    @Inject
    KodItemPermitDAO kodItemPermitDAO;
    @Inject
    HakmilikService hakmilikService;
    @Inject
    RegService regService;
    @Inject
    KodKategoriTanahDAO kodKategoriTanahDAO;
    @Inject
    KodDaerahDAO kodDaerahDAO;
    @Inject
    KodBandarPekanMukimDAO kodBandarPekanMukimDAO;
    @Inject
    KodUOMDAO kodUOMDAO;
    @Inject
    KodRizabDAO kodRizabDAO;
    @Inject
    KodLotDAO kodLotDAO;
    @Inject
    KodSeksyenDAO kodSeksyenDAO;
    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;
    @Inject
    etanah.Configuration conf;
    @Inject
    KodSyaratNyataDAO kodSyaratNyataDAO;
    @Inject
    PelupusanUtiliti pelUtiliti;
    private PermohonanLaporanPelan permohonanLaporanPelan;
    private Permohonan permohonan;
    private PermohonanPermitItem ppi;
    private KodBandarPekanMukim kodBPM;
    private KodUOM kodUL;
    private KodUOM kodUJ;
    private Pengguna pengguna;
    private String tarikhAwalDaftarGeran;
    private int tempohPajakan;
    private HakmilikPermohonan hakmilikPermohonan;
    private Hakmilik hakmilik;
    private String kodGunaTanah;
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    private String kodDaerah;
    private List<KodBandarPekanMukim> senaraiKodBPM;
    private List<HakmilikPermohonan> hakmilikPermohonanList;
    private List<KodItemPermit> senaraiKodItemPermit;
    private List<KodKegunaanTanah> listGT;
    private String idHakmilik;
    private KodLot kdLot;
    private String katTanahPilihan;
    private List<KodKategoriTanah> senaraiKodKatTanah;
    private List<KodKegunaanTanah> senaraiKodKegunaanTanahs;
    private KodKategoriTanah kategoriTanah;
    private List<KodSeksyen> senaraiKodSeksyen;
    private List<KodBandarPekanMukim> senaraiKodBPMLMCRGPJLB;
    private TanahRizabPermohonan mohonTrizab;
    private String kodBpm = new String();
    private String kodSek;
    private String negeri;
    private String noLitho;
    private String tandaBlok;
    private String kegunaTanah;
    private String luasDipohon;
    private String tanahR;
    private String daerah;
    private String jarakDariNama;
    private String keluasanUOM;
    private KodSeksyen kodSeksyen;
    private KodKegunaanTanah kegunaanTanah;
    private KodUOM kodKediamanUOM;
    private boolean forSeksyen;
    private boolean forBPM;
    private String index;
    private List<KodSekatanKepentingan> listKodSekatan;
    private List<KodSyaratNyata> listKodSyaratNyata;

    @DefaultHandler
    public Resolution showForm() {
        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        return new JSP("pelupusan/maklumat_tanah_dipohon_pbrz.jsp").addParameter("tab", "true");
    }

    public Resolution showForm3() {
        getContext().getRequest().setAttribute("edit", Boolean.TRUE);

        return new JSP("pelupusan/maklumat_tanah_dipohon_pbrz.jsp").addParameter("tab", "true");
    }

    public Resolution showForm2() {
        getContext().getRequest().setAttribute("view", Boolean.TRUE);
        return new JSP("pelupusan/maklumat_tanah_dipohon_pbrz.jsp").addParameter("tab", "true");
    }

    @HttpCache(allow = false)
    public Resolution showForm4() {
        idHakmilik = (String) getContext().getRequest().getParameter("idHakmilik");
        return new JSP("common/paparan_maklumat_hakmilik_permohonan_terperinci.jsp").addParameter("tab", "true");
    }

    public Resolution carianIndependent() {
        noLitho = getContext().getRequest().getParameter("noLitho");
        katTanahPilihan = getContext().getRequest().getParameter("katTanahPilihan");
        kodBpm = getContext().getRequest().getParameter("mpb");
        String daerahDIS = (String) getContext().getRequest().getSession().getAttribute("daerahDIS");

        if (permohonan.getKodUrusan().getKod().equals("LMCRG") || permohonan.getKodUrusan().getKod().equals("PJLB")) {
            if (StringUtils.isEmpty(daerahDIS)) {
                getContext().getRequest().getSession().setAttribute("daerahDIS", this.getKodDaerah());
            }
        }
        String tempat = getContext().getRequest().getParameter("tempat");
        String Luas = getContext().getRequest().getParameter("Luas");
        String kULuas = getContext().getRequest().getParameter("kULuas");
        String kodlot = getContext().getRequest().getParameter("kodlot");
        String noLot = getContext().getRequest().getParameter("noLot");
        String Jarak = getContext().getRequest().getParameter("Jarak");
        String uJarak = getContext().getRequest().getParameter("uJarak");
        String jarakDari = getContext().getRequest().getParameter("jarakDari");
        String kodSeksyen = getContext().getRequest().getParameter("kodSeksyen");

        if (kodBpm != null && !kodBpm.isEmpty()) {
            hakmilikPermohonan.setBandarPekanMukimBaru(kodBandarPekanMukimDAO.findById(Integer.valueOf(kodBpm)));
            int test = Integer.parseInt(kodBpm);
            KodBandarPekanMukim kodB = kodBandarPekanMukimDAO.findById(test);
            checkSeksyen(kodB);
        }
        if (tempat != null && !tempat.isEmpty()) {
            hakmilikPermohonan.setLokasi(tempat);
        }
        if (Luas != null && !Luas.isEmpty()) {
            hakmilikPermohonan.setLuasTerlibat(new BigDecimal(Luas));
        }
        if (kULuas != null && !kULuas.isEmpty()) {
            hakmilikPermohonan.setKodUnitLuas(kodUOMDAO.findById(kULuas));
        }
        if (kodlot != null && !kodlot.isEmpty()) {
            hakmilikPermohonan.setLot(kodLotDAO.findById(kodlot));
        }
        if (noLot != null && !noLot.isEmpty()) {
            hakmilikPermohonan.setNoLot(noLot);
        }
        if (Jarak != null && !Jarak.isEmpty()) {
            hakmilikPermohonan.setJarak(Jarak);
        }
        if (uJarak != null && !uJarak.isEmpty()) {
            hakmilikPermohonan.setUnitJarak(kodUOMDAO.findById(uJarak));
        }
        if (jarakDari != null && !jarakDari.isEmpty()) {
            hakmilikPermohonan.setJarakDari(jarakDari);
        }
        if (katTanahPilihan != null && !katTanahPilihan.isEmpty()) {
            hakmilikPermohonan.setKategoriTanahBaru(kodKategoriTanahDAO.findById(katTanahPilihan));
        }
        if (kodSeksyen != null && !kodSeksyen.isEmpty()) {
            hakmilikPermohonan.setKodSeksyen(pelupusanService.findByKodSeksyen(Integer.parseInt(kodSeksyen)));
        }

        rehydrate();
        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        return new JSP("pelupusan/maklumat_tanah_dipohon_pbrz.jsp").addParameter("tab", "true");
    }

    public Resolution carianIndependentMukim() {

        kodDaerah = getContext().getRequest().getParameter("kd");
        getContext().getRequest().getSession().setAttribute("daerahDIS", kodDaerah);
        String kodDaerahTest = (String) getContext().getRequest().getSession().getAttribute("daerahDIS");

        noLitho = getContext().getRequest().getParameter("noLitho");
        katTanahPilihan = getContext().getRequest().getParameter("katTanahPilihan");

        String tempat = getContext().getRequest().getParameter("tempat");
        String Luas = getContext().getRequest().getParameter("Luas");
        String kULuas = getContext().getRequest().getParameter("kULuas");
        String kodlot = getContext().getRequest().getParameter("kodlot");
        String noLot = getContext().getRequest().getParameter("noLot");
        String Jarak = getContext().getRequest().getParameter("Jarak");
        String uJarak = getContext().getRequest().getParameter("uJarak");
        String jarakDari = getContext().getRequest().getParameter("jarakDari");
        String kodSeksyen = getContext().getRequest().getParameter("kodSeksyen");

        if (kodDaerah != null && !kodDaerah.isEmpty()) {
            checkBPM(kodDaerah);
        }
        if (tempat != null && !tempat.isEmpty()) {
            hakmilikPermohonan.setLokasi(tempat);
        }
        if (Luas != null && !Luas.isEmpty()) {
            hakmilikPermohonan.setLuasTerlibat(new BigDecimal(Luas));
        }
        if (kULuas != null && !kULuas.isEmpty()) {
            hakmilikPermohonan.setKodUnitLuas(kodUOMDAO.findById(kULuas));
        }
        if (kodlot != null && !kodlot.isEmpty()) {
            hakmilikPermohonan.setLot(kodLotDAO.findById(kodlot));
        }
        if (noLot != null && !noLot.isEmpty()) {
            hakmilikPermohonan.setNoLot(noLot);
        }
        if (Jarak != null && !Jarak.isEmpty()) {
            hakmilikPermohonan.setJarak(Jarak);
        }
        if (uJarak != null && !uJarak.isEmpty()) {
            hakmilikPermohonan.setUnitJarak(kodUOMDAO.findById(uJarak));
        }
        if (jarakDari != null && !jarakDari.isEmpty()) {
            hakmilikPermohonan.setJarakDari(jarakDari);
        }
        if (katTanahPilihan != null && !katTanahPilihan.isEmpty()) {
            hakmilikPermohonan.setKategoriTanahBaru(kodKategoriTanahDAO.findById(katTanahPilihan));
        }
        if (kodSeksyen != null && !kodSeksyen.isEmpty()) {
            hakmilikPermohonan.setKodSeksyen(pelupusanService.findByKodSeksyen(Integer.parseInt(kodSeksyen)));
        }

        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        return new JSP("pelupusan/maklumat_tanah_dipohon_pbrz.jsp").addParameter("tab", "true");
    }

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {
        pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        negeri = conf.getProperty("kodNegeri");
        logger.debug("IdPermohonan :" + idPermohonan);

        //one idPermohonan to one idHakmilik
        if (idPermohonan != null) {

            hakmilikPermohonan = pelupusanService.findByIdPermohonan(idPermohonan);


            permohonan = permohonanDAO.findById(idPermohonan);
            permohonan.getSebab();




            if (hakmilikPermohonan != null) {
                jarakDariNama = !StringUtils.isBlank(hakmilikPermohonan.getJarakDariNama()) ? pelUtiliti.convertStringtoInitCap(hakmilikPermohonan.getJarakDariNama()) : new String();
                if (hakmilikPermohonan.getKodUnitLuas() != null) {
                    keluasanUOM = hakmilikPermohonan.getKodUnitLuas().getKod();
                }
                if (!StringUtils.isBlank(hakmilikPermohonan.getNoUnitPetak())) {
                    tandaBlok = hakmilikPermohonan.getNoUnitPetak();
                }
            }
            if (hakmilikPermohonan != null && hakmilikPermohonan.getBandarPekanMukimBaru() != null) {
                kodBpm = String.valueOf(hakmilikPermohonan.getBandarPekanMukimBaru().getKod());
                forBPM = true;
            }
            if (hakmilikPermohonan != null && hakmilikPermohonan.getKategoriTanahBaru() != null) {
                katTanahPilihan = hakmilikPermohonan.getKategoriTanahBaru().getKod();
            }
            if (hakmilikPermohonan != null && hakmilikPermohonan.getKodSeksyen() != null) {
                forSeksyen = true;
            }
            //logger.info("----------------------------hakmilikPermohonan"+hakmilikPermohonan.getKodSeksyen());
            logger.info("----------------------------permohonan" + permohonan.getSebab());



            if (hakmilikPermohonan == null) {
                hakmilikPermohonan = new HakmilikPermohonan();
                InfoAudit info = new InfoAudit();
                info.setDimasukOleh(pengguna);
                info.setTarikhMasuk(new java.util.Date());
                hakmilikPermohonan.setInfoAudit(info);
                hakmilikPermohonan.setPermohonan(permohonan);
                pelupusanService.simpanHakmilikPermohonan(hakmilikPermohonan);
                getContext().getRequest().setAttribute("hakmilik", Boolean.FALSE);
            } else {
                if (hakmilikPermohonan.getHakmilik() == null) {
                    System.out.println("Hakmilik Tiada");
                    getContext().getRequest().setAttribute("hakmilik", Boolean.FALSE);
                } else {
                    System.out.println("Hakmilik Ada");
                    getContext().getRequest().setAttribute("hakmilik", Boolean.TRUE);
                }
            }

            hakmilikPermohonanList = permohonan.getSenaraiHakmilik();

            kodDaerah = "01";


            // get senaraiKodBPM by kodDaerah
            logger.debug("cari bpm utk kod daerah :" + kodDaerah);
            senaraiKodBPM = listUtil.getSenaraiKodBPMByDaerah(kodDaerah);
            logger.debug("senaraiKodBPM :" + senaraiKodBPM.size());
            ;

            mohonTrizab = pelupusanService.findTanahRizabByIdPermohonan(idPermohonan);
            if (mohonTrizab != null) {
                if (mohonTrizab.getRizab() != null) {
                    tanahR = String.valueOf(mohonTrizab.getRizab().getKod());
                }
            }
        }

    }

    public Resolution simpan() throws ParseException {
        logger.debug("---------------------------start simpan");
        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        String catatan = getContext().getRequest().getParameter("permohonan.catatan");
        String sebab = getContext().getRequest().getParameter("permohonan.sebab");
        String luasTerlibat = getContext().getRequest().getParameter("hakmilikPermohonan.luasTerlibat");

//        String kod2 = getContext().getRequest().getParameter("kodUnitLuas");
        String kod2 = keluasanUOM;
        permohonan = permohonanDAO.findById(idPermohonan);
        boolean validateluas = false;
        if (permohonan.getKodUrusan().getKod().equals("LMCRG")) {
            validateluas = false;
            permohonan.setCatatan(catatan);
            permohonan.setSebab(sebab);
            pelupusanService.simpanPermohonan(permohonan);
        }
        if (permohonan.getKodUrusan().getKod().equals("PPRU")) {
            validateluas = false;
            pelupusanService.simpanPermohonan(permohonan);
        }

        if (negeri.equals("04")) {
            if (permohonan.getKodUrusan().getKod().equals("PPTR") || permohonan.getKodUrusan().getKod().equals("PBRZ")) {
                String val1 = getContext().getRequest().getParameter("bandarPekanMukimBaru.kod");
                String val2 = getContext().getRequest().getParameter("mohonTrizab.luasTerlibat");
                String val3 = getContext().getRequest().getParameter("hakmilikPermohonan.tempohPajakan");
                String val4 = getContext().getRequest().getParameter("permohonan.sebab");
                String val5 = getContext().getRequest().getParameter("mohonTrizab.namaPenjaga");

                int checkError = 0;
                if (StringUtils.isEmpty(val1)) {
                    addSimpleError("*Sila Nyatakan Mukim/Pekan/Bandar");
                    checkError = checkError + 1;
                }
                if (StringUtils.isEmpty(val2)) {
                    addSimpleError("*Sila Nyatakan luas Dipohon");
                    checkError = checkError + 1;
                }
                if (StringUtils.isEmpty(tanahR)) {
                    addSimpleError("*Sila Nyatakan Jenis Rizab");
                    checkError = checkError + 1;
                }
                if (StringUtils.isEmpty(val3)) {
                    addSimpleError("*Sila Nyatakan Tempoh Pajakan ");
                    checkError = checkError + 1;
                }
                if (StringUtils.isEmpty(val4)) {
                    addSimpleError("*Sila Nyatakan Tujuan");
                    checkError = checkError + 1;
                }
                if (StringUtils.isEmpty(val5)) {
                    addSimpleError("Sila Masukkan Nama Pengawal");
                    checkError = checkError + 1;
                }
                validateluas = checkError > 0 ? true : false;
            }

        }
        if (negeri.equals("05")) {
            if (permohonan.getKodUrusan().getKod().equals("PPTR")) {
                String val1 = getContext().getRequest().getParameter("bandarPekanMukimBaru.kod");
                String val2 = getContext().getRequest().getParameter("mohonTrizab.luasTerlibat");
                String val3 = getContext().getRequest().getParameter("hakmilikPermohonan.tempohPajakan");
                String val4 = getContext().getRequest().getParameter("permohonan.sebab");

                int checkError = 0;
                if (StringUtils.isEmpty(val1)) {
                    addSimpleError("*Sila Nyatakan Mukim/Pekan/Bandar");
                    checkError = checkError + 1;
                }
                if (StringUtils.isEmpty(val2)) {
                    addSimpleError("*Sila Nyatakan luas Dipohon");
                    checkError = checkError + 1;
                }
                if (StringUtils.isEmpty(tanahR)) {
                    addSimpleError("*Sila Nyatakan Jenis Rizab");
                    checkError = checkError + 1;
                }
                if (StringUtils.isEmpty(val3)) {
                    addSimpleError("*Sila Nyatakan Tempoh Pajakan ");
                    checkError = checkError + 1;
                }
                if (StringUtils.isEmpty(val4)) {
                    addSimpleError("*Sila Nyatakan Tujuan");
                    checkError = checkError + 1;
                }
                validateluas = checkError > 0 ? true : false;
            }
        }

        if (!validateluas) {
            if (permohonan != null) {
                if (permohonan.getKodUrusan().getKod().equals("PRMP")) {
                    if (!StringUtils.isBlank(kodGunaTanah)) {
                        PermohonanPermitItem pmi = new PermohonanPermitItem();
                        pmi = pelupusanService.findByIdMohonPermitItem(idPermohonan);
                        InfoAudit ia = new InfoAudit();
                        if (pmi != null) {
                            ia = pmi.getInfoAudit();
                            ia.setTarikhKemaskini(new java.util.Date());
                            ia.setDiKemaskiniOleh(peng);
                        } else {
                            pmi = new PermohonanPermitItem();
                            ia = new InfoAudit();
                            ia.setTarikhMasuk(new java.util.Date());
                            ia.setDimasukOleh(peng);
                        }
                        pmi.setInfoAudit(ia);
                        pmi.setKodCawangan(permohonan.getCawangan());
                        pmi.setPermohonan(permohonan);
                        pmi.setKodItemPermit(kodItemPermitDAO.findById(kodGunaTanah));
                        pelupusanService.saveOrUpdate(pmi);
                    } else {
                        addSimpleError("Sila Nyatakan Kegunaan Tanah");
                        return null;
                    }
                }
            }

            if (negeri.equals("05")) {
                permohonanLaporanPelan = pelupusanService.findByIdPermohonanPelan(idPermohonan);
                InfoAudit info = new InfoAudit();
                if (permohonanLaporanPelan == null) {
                    info = new InfoAudit();
                    permohonanLaporanPelan = new PermohonanLaporanPelan();
                    info.setDimasukOleh(peng);
                    info.setTarikhMasuk(new java.util.Date());
                    permohonanLaporanPelan.setInfoAudit(info);
                    permohonanLaporanPelan.setPermohonan(permohonan);
                    permohonanLaporanPelan.setCawangan(permohonan.getCawangan());
                    permohonanLaporanPelan.setNoLitho(noLitho);
                    if (hakmilikPermohonan != null) {
                        permohonanLaporanPelan.setHakmilikPermohonan(hakmilikPermohonan);
                    }
                    laporanPelukisPelanService.saveOrUpdatePermohonanLaporanPelan(permohonanLaporanPelan);
                } else {
                    info = permohonanLaporanPelan.getInfoAudit();
                    info.setDiKemaskiniOleh(peng);
                    info.setTarikhKemaskini(new java.util.Date());
                    permohonanLaporanPelan.setInfoAudit(info);
                    permohonanLaporanPelan.setNoLitho(noLitho);
                    laporanPelukisPelanService.saveOrUpdatePermohonanLaporanPelan(permohonanLaporanPelan);
                }
            }
            if (negeri.equals("04") && permohonan.getKodUrusan().getKod().equals("LMCRG")) {
                permohonanLaporanPelan = pelupusanService.findByIdPermohonanPelan(idPermohonan);
                InfoAudit info = new InfoAudit();
                if (permohonanLaporanPelan == null) {
                    info = new InfoAudit();
                    permohonanLaporanPelan = new PermohonanLaporanPelan();
                    info.setDimasukOleh(peng);
                    info.setTarikhMasuk(new java.util.Date());
                    permohonanLaporanPelan.setInfoAudit(info);
                    permohonanLaporanPelan.setPermohonan(permohonan);
                    permohonanLaporanPelan.setCawangan(permohonan.getCawangan());
                    permohonanLaporanPelan.setNoLitho(noLitho);
                    if (hakmilikPermohonan != null) {
                        permohonanLaporanPelan.setHakmilikPermohonan(hakmilikPermohonan);
                    } else {
                        hakmilikPermohonan = new HakmilikPermohonan();
                        hakmilikPermohonan = pelupusanService.findByIdPermohonan(idPermohonan);
                        permohonanLaporanPelan.setHakmilikPermohonan(hakmilikPermohonan);
                    }

//                    permohonanLaporanPelan.setCatatan(tandaBlok);
                    //hakmilikPermohonanList.add(hakmilikPermohonan);
                    laporanPelukisPelanService.saveOrUpdatePermohonanLaporanPelan(permohonanLaporanPelan);
                } else {
                    info = permohonanLaporanPelan.getInfoAudit();
                    info.setDiKemaskiniOleh(peng);
                    info.setTarikhKemaskini(new java.util.Date());
                    permohonanLaporanPelan.setInfoAudit(info);
                    permohonanLaporanPelan.setNoLitho(noLitho);
//                     permohonanLaporanPelan.setCatatan(tandaBlok);
                    laporanPelukisPelanService.saveOrUpdatePermohonanLaporanPelan(permohonanLaporanPelan);
                }
            }

            kegunaanTanah = new KodKegunaanTanah();
            String kod5 = getContext().getRequest().getParameter("kodGunaTanah");
            if (kod5 != null) {
                kegunaanTanah.setKod(kod5);
                hakmilikPermohonan.setKodKegunaanTanah(kegunaanTanah);

            }

            kodSeksyen = new KodSeksyen();
            String kod4 = getContext().getRequest().getParameter("kodSeksyen.kod");
            if (kod4 != null && !kod4.equals("")) {
                kodSeksyen.setKod(Integer.parseInt(kod4));
                hakmilikPermohonan.setKodSeksyen(kodSeksyen);
                logger.debug("-------------kodSeksyen" + kodSeksyen);
            } else {
                hakmilikPermohonan.setKodSeksyen(null);
            }


            //if got other object
            kodBPM = new KodBandarPekanMukim();
            String kod1 = getContext().getRequest().getParameter("bandarPekanMukimBaru.kod");
            if (kod1 != null) {
                kodBPM.setKod(Integer.parseInt(kod1));
                hakmilikPermohonan.setBandarPekanMukimBaru(kodBPM);
            }


            String lokasi = getContext().getRequest().getParameter("hakmilikPermohonan.lokasi");
            hakmilikPermohonan.setLokasi(lokasi);

            //to set idpermohonan
            hakmilikPermohonan.setPermohonan(permohonan);

            kodUL = new KodUOM();

            if (StringUtils.isNotBlank(kod2)) {
                kodUL.setKod(kod2);
                hakmilikPermohonan.setKodUnitLuas(kodUL);
            }

            if (negeri.equals("05")) {
                kdLot = new KodLot();
                String kodLot = getContext().getRequest().getParameter("kodLot.kod");
                if (StringUtils.isNotBlank(kodLot)) {
                    kdLot.setKod(kodLot);
                    hakmilikPermohonan.setLot(kdLot);
                }

                String noLot = getContext().getRequest().getParameter("hakmilikPermohonan.noLot");
                if (StringUtils.isNotBlank(noLot)) {
                    hakmilikPermohonan.setNoLot(noLot);
                }
            }
            if (negeri.equals("04")) {
                if (!permohonan.getKodUrusan().getKod().equals("PPTR")) {
                    String noLot = getContext().getRequest().getParameter("hakmilikPermohonan.noLot");
                    String kodLot = getContext().getRequest().getParameter("kodLot.kod");
                    kdLot = new KodLot();
                    if (StringUtils.isNotBlank(noLot)) {

                        kdLot.setKod(kodLot);
                        hakmilikPermohonan.setLot(kdLot);
                        hakmilikPermohonan.setNoLot(noLot);
                    } else {
                        hakmilikPermohonan.setLot(null);
                    }
                    String jarak = getContext().getRequest().getParameter("hakmilikPermohonan.jarak");
                    hakmilikPermohonan.setJarak(jarak);

                    kodUJ = new KodUOM();
                    String kod3 = getContext().getRequest().getParameter("unitJarak.kod");

                    kodUJ.setKod(kod3);
                    hakmilikPermohonan.setUnitJarak(kodUJ);

                    String jarakDari = getContext().getRequest().getParameter("hakmilikPermohonan.jarakDari");
                    hakmilikPermohonan.setJarakDari(jarakDari);

                    kodKediamanUOM = new KodUOM();
                    String kodK = getContext().getRequest().getParameter("jarakDariKediamanUom.kod");
                    kodKediamanUOM.setKod(kodK);
                    hakmilikPermohonan.setJarakDariKediamanUom(kodKediamanUOM);
                }
            }

            if (permohonan.getKodUrusan().getKod().equalsIgnoreCase("PBMT") || permohonan.getKodUrusan().getKod().equalsIgnoreCase("PTBTS")) {
                String katgTanah = getContext().getRequest().getParameter("kategoriTanahBaru.kod");
                KodKategoriTanah k = new KodKategoriTanah();
                if (katgTanah != null) {
                    k.setKod(katgTanah);
                    hakmilikPermohonan.setKategoriTanahBaru(k);
                }
            }
            InfoAudit ia = hakmilikPermohonan.getInfoAudit();
            if (ia == null) {
                ia = new InfoAudit();
                ia.setDimasukOleh(peng);
                ia.setTarikhMasuk(new Date());
                hakmilikPermohonan.setInfoAudit(ia);
            } else {
                ia.setDiKemaskiniOleh(peng);
                ia.setTarikhKemaskini(new java.util.Date());
                hakmilikPermohonan.setInfoAudit(ia);
            }

            if (permohonan.getKodUrusan().getKod().equalsIgnoreCase("PLPS") || permohonan.getKodUrusan().getKod().equalsIgnoreCase("PLPTD") || permohonan.getKodUrusan().getKod().equalsIgnoreCase("RLPS") || permohonan.getKodUrusan().getKod().equalsIgnoreCase("RLPSK") || permohonan.getKodUrusan().getKod().equalsIgnoreCase("LPSP")) {
                String kodPermit = getContext().getRequest().getParameter("kodPermit.kod");
                KodItemPermit kitem = new KodItemPermit();
                kitem.setKod(kodPermit);
                ia = new InfoAudit();
                ia.setDimasukOleh(peng);
                ia.setTarikhMasuk(new Date());
                PermohonanPermitItem ppi = pelupusanService.findByIdMohonPermitItem(idPermohonan);
                if (ppi != null) {

                    ppi.setPermohonan(permohonan);
                    ppi.setInfoAudit(ia);
                    ppi.setKodCawangan(peng.getKodCawangan());
                    ppi.setKodItemPermit(kitem);
                    pelupusanService.saveOrUpdate(ppi);

                } else {
                    ppi = new PermohonanPermitItem();
                    ppi.setPermohonan(permohonan);
                    ppi.setInfoAudit(ia);
                    ppi.setKodCawangan(peng.getKodCawangan());
                    ppi.setKodItemPermit(kitem);
                    pelupusanService.saveOrUpdate(ppi);
                }
            }

            if (!StringUtils.isEmpty(tandaBlok)) {
                hakmilikPermohonan.setNoUnitPetak(tandaBlok);
            }
            pelupusanService.simpanHakmilikPermohonan(hakmilikPermohonan);
            logger.debug("tess1 :" + hakmilikPermohonan.getId());
            if (hakmilikPermohonan != null && hakmilikPermohonan.getBandarPekanMukimBaru() != null) {
                kodBpm = String.valueOf(hakmilikPermohonan.getBandarPekanMukimBaru().getKod());
            }
            if (hakmilikPermohonan != null && hakmilikPermohonan.getKategoriTanahBaru() != null) {
                katTanahPilihan = hakmilikPermohonan.getKategoriTanahBaru().getKod();
            }

            if (permohonan.getKodUrusan().getKod().equals("PPTR")) {
                TanahRizabPermohonan mohonTrizabTemp = new TanahRizabPermohonan();
                mohonTrizabTemp = pelupusanService.findTanahRizabByIdPermohonan(idPermohonan);
                ia = new InfoAudit();
                if (mohonTrizabTemp != null) {
                    ia = mohonTrizabTemp.getInfoAudit();
                    ia.setDiKemaskiniOleh(peng);
                    ia.setTarikhKemaskini(new Date());
                } else {
                    ia = new InfoAudit();
                    ia.setDimasukOleh(peng);
                    ia.setTarikhMasuk(new Date());
                    mohonTrizabTemp = new TanahRizabPermohonan();
                    mohonTrizabTemp.setInfoAudit(ia);
                    mohonTrizabTemp.setPermohonan(permohonan);
                    mohonTrizabTemp.setCawangan(permohonan.getCawangan());
                    mohonTrizabTemp.setDaerah(permohonan.getCawangan().getDaerah());
                    mohonTrizabTemp.setBandarPekanMukim(hakmilikPermohonan.getBandarPekanMukimBaru());
                    mohonTrizabTemp.setNoLot(hakmilikPermohonan.getNoLot());
                    mohonTrizabTemp.setAktif('Y');
                }
                mohonTrizabTemp.setLuasTerlibat(mohonTrizab.getLuasTerlibat());
                mohonTrizabTemp.setNoLot(" ");

                if (!StringUtils.isEmpty(tanahR)) {
                    mohonTrizabTemp.setRizab(kodRizabDAO.findById(Integer.parseInt(tanahR)));
                    pelupusanService.simpanTanahRizabPermohonan(mohonTrizabTemp);
                }
            }

            addSimpleMessage("Maklumat telah berjaya disimpan.");
        }

        rehydrate();
        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        return new JSP("pelupusan/maklumat_tanah_dipohon_pbrz.jsp").addParameter("tab", "true");

    }

    public Resolution simpanHakmilikPBRZMLK() {
        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        permohonan = permohonanDAO.findById(idPermohonan);
        String val1 = getContext().getRequest().getParameter("bandarPekanMukimBaru.kod");
        String seksyen = getContext().getRequest().getParameter("kodSeksyen.kod");
        String lokasi = getContext().getRequest().getParameter("hakmilikPermohonan.lokasi");
        String lot = getContext().getRequest().getParameter("kodLot.kod");
        String noLot = getContext().getRequest().getParameter("hakmilikPermohonan.noLot");
        keluasanUOM = getContext().getRequest().getParameter("keluasanUOM");
        String namaPenjaga = getContext().getRequest().getParameter("mohonTrizab.namaPenjaga");

        if (permohonan != null) {
            if (permohonan.getKodUrusan().getKod().equals("PBRZ")) { //Tambah else if utk PBRZ
                TanahRizabPermohonan mohonTrizabTemp = new TanahRizabPermohonan();
                mohonTrizabTemp = pelupusanService.findTanahRizabByIdPermohonan(idPermohonan);
                InfoAudit ia = new InfoAudit();
                if (mohonTrizabTemp != null) {
                    ia = mohonTrizabTemp.getInfoAudit();
                    ia.setDiKemaskiniOleh(peng);
                    ia.setTarikhKemaskini(new Date());
                } else {
                    ia = new InfoAudit();
                    ia.setDimasukOleh(peng);
                    ia.setTarikhMasuk(new Date());
                    mohonTrizabTemp = new TanahRizabPermohonan();
                    mohonTrizabTemp.setInfoAudit(ia);
                    mohonTrizabTemp.setPermohonan(permohonan);
                    mohonTrizabTemp.setCawangan(permohonan.getCawangan());
                    mohonTrizabTemp.setDaerah(permohonan.getCawangan().getDaerah());
                    if (hakmilikPermohonan.getHakmilik() != null) {
                        mohonTrizabTemp.setBandarPekanMukim(hakmilikPermohonan.getHakmilik().getBandarPekanMukim());
                        mohonTrizabTemp.setNoLot(hakmilikPermohonan.getHakmilik().getNoLot());
                        mohonTrizabTemp.setNoHakmilik(hakmilikPermohonan.getHakmilik().getNoHakmilik());

                    }
                    mohonTrizabTemp.setAktif('Y');
                    mohonTrizabTemp.setNamaPenjaga(mohonTrizab.getNamaPenjaga());
                }
                mohonTrizabTemp.setLuasTerlibat(mohonTrizab.getLuasTerlibat());
                String kod2 = keluasanUOM;
                if (StringUtils.isNotBlank(kod2)) {
                    kodUL = new KodUOM();
                    kodUL.setKod(kod2);
                    mohonTrizabTemp.setKodUnitLuas(kodUL);
                }
                if (hakmilikPermohonan.getHakmilik() == null) {
                    KodBandarPekanMukim kodBandarPekanMukim = new KodBandarPekanMukim();
                    if (!StringUtils.isEmpty(val1)) {
                        kodBandarPekanMukim = new KodBandarPekanMukim();
                        kodBandarPekanMukim = kodBandarPekanMukimDAO.findById(Integer.parseInt(val1));
                        mohonTrizabTemp.setBandarPekanMukim(kodBandarPekanMukim);
                    }
                    if (!StringUtils.isEmpty(hakmilikPermohonan.getNoLot())) {
                        mohonTrizabTemp.setNoLot(hakmilikPermohonan.getNoLot());
                    }
                }

                if (!StringUtils.isEmpty(tanahR)) {
                    mohonTrizabTemp.setRizab(kodRizabDAO.findById(Integer.parseInt(tanahR)));
                    pelupusanService.simpanTanahRizabPermohonan(mohonTrizabTemp);
                }
            }
        }


        /*
         * HAKMILIKPERMOHONAN
         */
        hakmilikPermohonan = new HakmilikPermohonan();
        hakmilikPermohonan = pelupusanService.findByIdPermohonan(idPermohonan);

        if (hakmilikPermohonan != null) {
            hakmilikPermohonan.setInfoAudit(disLaporanTanahService.findAudit(hakmilikPermohonan, "update", peng));
            KodBandarPekanMukim kodBandarPekanMukim = new KodBandarPekanMukim();
            if (!StringUtils.isEmpty(val1)) {
                kodBandarPekanMukim = new KodBandarPekanMukim();
                kodBandarPekanMukim = kodBandarPekanMukimDAO.findById(Integer.parseInt(val1));
                hakmilikPermohonan.setBandarPekanMukimBaru(kodBandarPekanMukim);
            }
        } else {
            hakmilikPermohonan = new HakmilikPermohonan();
            hakmilikPermohonan.setInfoAudit(disLaporanTanahService.findAudit(hakmilikPermohonan, "new", peng));
        }

        if (!StringUtils.isEmpty(seksyen)) {
            KodSeksyen kodSeksyen1 = new KodSeksyen();
            kodSeksyen1 = kodSeksyenDAO.findById(Integer.parseInt(seksyen));
            hakmilikPermohonan.setKodSeksyen(kodSeksyen1);
        }
        if (!StringUtils.isEmpty(lokasi)) {
            hakmilikPermohonan.setLokasi(lokasi);
        }
        if (!StringUtils.isEmpty(lot)) {
            KodLot kodLotPBRZ = new KodLot();
            kodLotPBRZ = kodLotDAO.findById(lot);
            hakmilikPermohonan.setLot(kodLotPBRZ);
        }
        if (!StringUtils.isEmpty(noLot)) {
            hakmilikPermohonan.setNoLot(noLot);
        }
        if (!StringUtils.isEmpty(keluasanUOM)) {
            KodUOM kodUOMPBRZ = new KodUOM();
            kodUOMPBRZ = kodUOMDAO.findById(keluasanUOM);
            hakmilikPermohonan.setKodUnitLuas(kodUOMPBRZ);
        }
        pelupusanService.simpanHakmilikPermohonan(hakmilikPermohonan);

        addSimpleMessage("Maklumat telah berjaya disimpan.");
        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        return new JSP("pelupusan/maklumat_tanah_dipohon_pbrz.jsp").addParameter("tab", "true");
    }

    public Resolution simpanHakmilik() {
        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        permohonan = permohonanDAO.findById(idPermohonan);

        if (permohonan != null) {
            if (permohonan.getKodUrusan().getKod().equals("PRMP")) {
                if (!StringUtils.isBlank(kodGunaTanah)) {
                    PermohonanPermitItem pmi = new PermohonanPermitItem();
                    pmi = pelupusanService.findByIdMohonPermitItem(idPermohonan);
                    InfoAudit ia = new InfoAudit();
                    if (pmi != null) {
                        ia = pmi.getInfoAudit();
                        ia.setTarikhKemaskini(new java.util.Date());
                        ia.setDiKemaskiniOleh(peng);
                    } else {
                        pmi = new PermohonanPermitItem();
                        ia = new InfoAudit();
                        ia.setTarikhMasuk(new java.util.Date());
                        ia.setDimasukOleh(peng);
                    }
                    pmi.setInfoAudit(ia);
                    pmi.setKodCawangan(permohonan.getCawangan());
                    pmi.setPermohonan(permohonan);
                    pmi.setKodItemPermit(kodItemPermitDAO.findById(kodGunaTanah));
                    pelupusanService.saveOrUpdate(pmi);
                } else {
                    addSimpleError("Sila Nyatakan Kegunaan Tanah");
                    return new JSP("pelupusan/maklumat_tanah_dipohon_pbrz.jsp").addParameter("tab", "true");
                }
            } else if (permohonan.getKodUrusan().getKod().equals("PBRZ")) { //Tambah else if utk PBRZ
                TanahRizabPermohonan mohonTrizabTemp = new TanahRizabPermohonan();
                mohonTrizabTemp = pelupusanService.findTanahRizabByIdPermohonan(idPermohonan);
                InfoAudit ia = new InfoAudit();
                if (mohonTrizabTemp != null) {
                    ia = mohonTrizabTemp.getInfoAudit();
                    ia.setDiKemaskiniOleh(peng);
                    ia.setTarikhKemaskini(new Date());
                } else {
                    ia = new InfoAudit();
                    ia.setDimasukOleh(peng);
                    ia.setTarikhMasuk(new Date());
                    mohonTrizabTemp = new TanahRizabPermohonan();
                    mohonTrizabTemp.setInfoAudit(ia);
                    mohonTrizabTemp.setPermohonan(permohonan);
                    mohonTrizabTemp.setCawangan(permohonan.getCawangan());
                    mohonTrizabTemp.setDaerah(permohonan.getCawangan().getDaerah());
                    if (hakmilikPermohonan.getHakmilik() != null) {
                        mohonTrizabTemp.setBandarPekanMukim(hakmilikPermohonan.getHakmilik().getBandarPekanMukim());
                        mohonTrizabTemp.setNoLot(hakmilikPermohonan.getHakmilik().getNoLot());
                        mohonTrizabTemp.setNoHakmilik(hakmilikPermohonan.getHakmilik().getNoHakmilik());
                    }
                    mohonTrizabTemp.setAktif('Y');
                }
                mohonTrizabTemp.setLuasTerlibat(mohonTrizab.getLuasTerlibat());
                String kod2 = keluasanUOM;
                if (StringUtils.isNotBlank(kod2)) {
                    kodUL = new KodUOM();
                    kodUL.setKod(kod2);
                    mohonTrizabTemp.setKodUnitLuas(kodUL);
                }
                if (hakmilikPermohonan.getHakmilik() == null) {
                    mohonTrizabTemp.setNoLot(" ");
                }

                if (!StringUtils.isEmpty(tanahR)) {
                    mohonTrizabTemp.setRizab(kodRizabDAO.findById(Integer.parseInt(tanahR)));
                    pelupusanService.simpanTanahRizabPermohonan(mohonTrizabTemp);
                }
            }
        }
        for (int i = 0; i < hakmilikPermohonanList.size(); i++) {
            HakmilikPermohonan hmp = new HakmilikPermohonan();
            hmp = hakmilikPermohonanList.get(i);

            hakmilikService.saveHakmilik(hmp.getHakmilik());
        }
        String[] tname = {"permohonan"};
        Object[] tvalue = {permohonan};
        List senaraiHakMilik = hakmilikPermohonanDAO.findByEqualCriterias(tname, tvalue, null);
        InfoAudit infoAudit = new InfoAudit();
        infoAudit.setDimasukOleh(peng);
        infoAudit.setTarikhMasuk(new java.util.Date());
        infoAudit.setDiKemaskiniOleh(peng);
        if (senaraiHakMilik != null) {
            hakmilikPermohonan = (HakmilikPermohonan) senaraiHakMilik.get(0);
            hakmilik = hakmilikDAO.findById(hakmilikPermohonan.getHakmilik().getIdHakmilik());
            infoAudit.setTarikhKemaskini(new java.util.Date());
            infoAudit.setTarikhMasuk(hakmilikPermohonan.getInfoAudit().getTarikhMasuk());
            hakmilikPermohonan.setInfoAudit(infoAudit);
            hakmilikPermohonan.setBandarPekanMukimBaru(hakmilik.getBandarPekanMukim());
            hakmilikPermohonan.setJenisPenggunaanTanah(hakmilik.getKategoriTanah());
            hakmilikPermohonan.setLot(hakmilik.getLot());
            hakmilikPermohonan.setNoLot(hakmilik.getNoLot());
            hakmilikPermohonan.setLuasTerlibat(hakmilik.getLuas());
            hakmilikPermohonan.setKodUnitLuas(hakmilik.getKodUnitLuas());
            hakmilikPermohonan.setSyaratNyata(hakmilik.getSyaratNyata());
            hakmilikPermohonan.setSekatanKepentingan(hakmilik.getSekatanKepentingan());
            pelupusanService.simpanHakmilikPermohonan(hakmilikPermohonan);
        }

        addSimpleMessage("Maklumat telah berjaya disimpan.");
        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        return new JSP("pelupusan/maklumat_tanah_dipohon_pbrz.jsp").addParameter("tab", "true");
    }

    public boolean tambahValidations() {
        boolean flag = false;

        if (hakmilikPermohonan.getBandarPekanMukimBaru() == null) {
            flag = true;
            addSimpleError(" Sila Pilih Pekan/Bandar/Mukim ");
        } else if (hakmilikPermohonan.getLokasi() == null) {
            flag = true;
            addSimpleError(" Sila Masukkan Tempat");
        } else if (permohonan.getSebab() == null) {
            flag = true;
            addSimpleError(" Sila Masukkan Sebab  ");
        } else if (mohonTrizab.getNamaPenjaga() == null) {
            flag = true;
            addSimpleError("Sila Masukkan nama pengawal");
        }

        return flag;
    }

    public void checkSeksyen(KodBandarPekanMukim kod) {
        try {
            String query = "Select u from etanah.model.KodSeksyen u where u.kodBandarPekanMukim.kod = :kod ";
            Session session = sessionProvider.get();
            Query q = session.createQuery(query);
            q.setInteger("kod", kod.getKod());
            if (q.list().size() > 0) {
                forSeksyen = true;
                forBPM = true;
            } else {
                forSeksyen = false;
                forBPM = true;
                hakmilikPermohonan.setKodSeksyen(null);
            }

        } finally {
        }
    }

    public void checkBPM(String kod) {
//        logger.info("THIS IS KODDAERAH->"+kod);
        try {
            String query = "Select u from etanah.model.KodBandarPekanMukim u where u.daerah.kod = :kod ";
            Session session = sessionProvider.get();
            Query q = session.createQuery(query);
            q.setString("kod", kod);
            if (q.list().size() > 0) {
                forBPM = true;
                forSeksyen = false;
            } else {
                forBPM = false;
                forSeksyen = false;
                hakmilikPermohonan.setBandarPekanMukimBaru(null);
            }

        } finally {
        }
    }

    public Resolution search() {
        index = getContext().getRequest().getParameter("index");
        return new JSP("pelupusan/searchSyaratNyata_lptn.jsp").addParameter("popup", "true");
    }

    public HakmilikPermohonan getHakmilikPermohonan() {
        return hakmilikPermohonan;
    }

    public void setHakmilikPermohonan(HakmilikPermohonan hakmilikPermohonan) {
        this.hakmilikPermohonan = hakmilikPermohonan;
    }

    public static Logger getLogger() {
        return logger;
    }

    public static void setLogger(Logger logger) {
        MaklumatTanahPbrzActionBean.logger = logger;
    }

    public Pengguna getPengguna() {
        return pengguna;
    }

    public void setPengguna(Pengguna pengguna) {
        this.pengguna = pengguna;
    }

    public Permohonan getPermohonan() {
        return permohonan;
    }

    public void setPermohonan(Permohonan permohonan) {
        this.permohonan = permohonan;
    }

    public KodBandarPekanMukim getKodBPM() {
        return kodBPM;
    }

    public void setKodBPM(KodBandarPekanMukim kodBPM) {
        this.kodBPM = kodBPM;
    }

    public KodUOM getKodUJ() {
        return kodUJ;
    }

    public void setKodUJ(KodUOM kodUJ) {
        this.kodUJ = kodUJ;
    }

    public KodUOM getKodUL() {
        return kodUL;
    }

    public void setKodUL(KodUOM kodUL) {
        this.kodUL = kodUL;
    }

    public String getTarikhAwalDaftarGeran() {
        return tarikhAwalDaftarGeran;
    }

    public void setTarikhAwalDaftarGeran(String tarikhAwalDaftarGeran) {
        this.tarikhAwalDaftarGeran = tarikhAwalDaftarGeran;
    }

    public int getTempohPajakan() {
        return tempohPajakan;
    }

    public void setTempohPajakan(int tempohPajakan) {
        this.tempohPajakan = tempohPajakan;
    }

    public void setSenaraiKodBPM(List<KodBandarPekanMukim> senaraiKodBPM) {
        this.senaraiKodBPM = senaraiKodBPM;
    }

    public List<KodBandarPekanMukim> getSenaraiKodBPM() {
        return senaraiKodBPM;
    }

    public String getKodDaerah() {
        return kodDaerah;
    }

    public void setKodDaerah(String kodDaerah) {
        this.kodDaerah = kodDaerah;
    }

    public Hakmilik getHakmilik() {
        return hakmilik;
    }

    public void setHakmilik(Hakmilik hakmilik) {
        this.hakmilik = hakmilik;
    }

    public List<HakmilikPermohonan> getHakmilikPermohonanList() {
        return hakmilikPermohonanList;
    }

    public void setHakmilikPermohonanList(List<HakmilikPermohonan> hakmilikPermohonanList) {
        this.hakmilikPermohonanList = hakmilikPermohonanList;
    }

    public List<KodItemPermit> getSenaraiKodItemPermit() {
        return senaraiKodItemPermit;
    }

    public void setSenaraiKodItemPermit(List<KodItemPermit> senaraiKodItemPermit) {
        this.senaraiKodItemPermit = senaraiKodItemPermit;
    }

    public PermohonanPermitItem getPpi() {
        return ppi;
    }

    public void setPpi(PermohonanPermitItem ppi) {
        this.ppi = ppi;
    }

    public List<KodKegunaanTanah> getListGT() {
        return listGT;
    }

    public void setListGT(List<KodKegunaanTanah> listGT) {
        this.listGT = listGT;
    }

    public List<KodKegunaanTanah> getSenaraiKodKegunaanTanahs() {
        if (StringUtils.isNotBlank(katTanahPilihan)) {
            return pelupusanService.getSenaraiKegunaanTanah(katTanahPilihan);
        }
        return new ArrayList<KodKegunaanTanah>();
    }

    public void setSenaraiKodKegunaanTanahs(List<KodKegunaanTanah> senaraiKodKegunaanTanahs) {
        this.senaraiKodKegunaanTanahs = senaraiKodKegunaanTanahs;
    }

    public String getKatTanahPilihan() {
        return katTanahPilihan;
    }

    public void setKatTanahPilihan(String katTanahPilihan) {
        this.katTanahPilihan = katTanahPilihan;
    }

    public List<KodSeksyen> getSenaraiKodSeksyen() {
//        logger.info("THIS IS KODBPM ->"+kodBpm);
        if (StringUtils.isNotBlank(kodBpm)) {
            return pelupusanService.getSenaraiKodSeksyen(kodBpm);
        }
        return new ArrayList<KodSeksyen>();
    }

    public void setSenaraiKodSeksyen(List<KodSeksyen> senaraiKodSeksyen) {
        this.senaraiKodSeksyen = senaraiKodSeksyen;
    }

    public List<KodBandarPekanMukim> getSenaraiKodBPMLMCRGPJLB() {
        if (StringUtils.isNotBlank(kodDaerah)) {
            return pelupusanService.getSenaraiKodBPM(kodDaerah);
        }
        return new ArrayList<KodBandarPekanMukim>();
    }

    public void setSenaraiKodBPMLMCRGPJLB(List<KodBandarPekanMukim> senaraiKodBPMLMCRGPJLB) {
        this.senaraiKodBPMLMCRGPJLB = senaraiKodBPMLMCRGPJLB;
    }

    public String getKodBpm() {
        return kodBpm;
    }

    public void setKodBpm(String kodBpm) {
        this.kodBpm = kodBpm;
    }

    public String getKodGunaTanah() {
        return kodGunaTanah;
    }

    public void setKodGunaTanah(String kodGunaTanah) {
        this.kodGunaTanah = kodGunaTanah;
    }

    public KodUOM getKodKediamanUOM() {
        return kodKediamanUOM;
    }

    public void setKodKediamanUOM(KodUOM kodKediamanUOM) {
        this.kodKediamanUOM = kodKediamanUOM;
    }

    public String getNegeri() {
        return negeri;
    }

    public void setNegeri(String negeri) {
        this.negeri = negeri;
    }

    public boolean isForSeksyen() {
        return forSeksyen;
    }

    public void setForSeksyen(boolean forSeksyen) {
        this.forSeksyen = forSeksyen;
    }

    public String getNoLitho() {
        return noLitho;
    }

    public void setNoLitho(String noLitho) {
        this.noLitho = noLitho;
    }

    public String getKegunaTanah() {
        return kegunaTanah;
    }

    public void setKegunaTanah(String kegunaTanah) {
        this.kegunaTanah = kegunaTanah;
    }

    public String getLuasDipohon() {
        return luasDipohon;
    }

    public void setLuasDipohon(String luasDipohon) {
        this.luasDipohon = luasDipohon;
    }

    public String getIndex() {
        return index;
    }

    public void setIndex(String index) {
        this.index = index;
    }

    public List<KodSekatanKepentingan> getListKodSekatan() {
        return listKodSekatan;
    }

    public void setListKodSekatan(List<KodSekatanKepentingan> listKodSekatan) {
        this.listKodSekatan = listKodSekatan;
    }

    public List<KodSyaratNyata> getListKodSyaratNyata() {
        return listKodSyaratNyata;
    }

    public void setListKodSyaratNyata(List<KodSyaratNyata> listKodSyaratNyata) {
        this.listKodSyaratNyata = listKodSyaratNyata;
    }

    public String getTandaBlok() {
        return tandaBlok;
    }

    public void setTandaBlok(String tandaBlok) {
        this.tandaBlok = tandaBlok;
    }

    public boolean isForBPM() {
        return forBPM;
    }

    public void setForBPM(boolean forBPM) {
        this.forBPM = forBPM;
    }

    public static String getKodDaerahStatic() {
        return kodDaerahStatic;
    }

    public static void setKodDaerahStatic(String kodDaerahStatic) {
        MaklumatTanahPbrzActionBean.kodDaerahStatic = kodDaerahStatic;
    }

    public TanahRizabPermohonan getMohonTrizab() {
        return mohonTrizab;
    }

    public void setMohonTrizab(TanahRizabPermohonan mohonTrizab) {
        this.mohonTrizab = mohonTrizab;
    }

    public String getTanahR() {
        return tanahR;
    }

    public void setTanahR(String tanahR) {
        this.tanahR = tanahR;
    }

    public String getDaerah() {
        return daerah;
    }

    public void setDaerah(String daerah) {
        this.daerah = daerah;
    }

    public String getJarakDariNama() {
        return jarakDariNama;
    }

    public void setJarakDariNama(String jarakDariNama) {
        this.jarakDariNama = jarakDariNama;
    }

    public String getKeluasanUOM() {
        return keluasanUOM;
    }

    public void setKeluasanUOM(String keluasanUOM) {
        this.keluasanUOM = keluasanUOM;
    }
}
