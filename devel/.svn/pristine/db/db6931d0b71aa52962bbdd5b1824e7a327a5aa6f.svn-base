/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package etanah.view.stripes.pengambilan;
import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.dao.HakmilikDAO;
import etanah.dao.PermohonanDAO;
import etanah.dao.PermohonanPihakDAO;
import etanah.model.Dokumen;
import etanah.model.Notis;
import etanah.model.Hakmilik;
import etanah.model.HakmilikPermohonan;
import etanah.model.InfoAudit;
import etanah.model.KodCawangan;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.model.PermohonanPihak;
import etanah.model.FasaPermohonan;
import etanah.model.ambil.HakmilikPerbicaraan;
import etanah.model.ambil.Penilaian;
import etanah.model.ambil.PerbicaraanKehadiran;
import etanah.service.PengambilanService;
import etanah.service.common.NotisPenerimaanService;
import etanah.service.common.PermohonanPihakService;
import etanah.service.common.FasaPermohonanService;
import etanah.service.common.NotisService;
import etanah.service.daftar.PembetulanService;
import etanah.view.etanahActionBeanContext;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Calendar;
import java.util.logging.Level;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.RedirectResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;
import org.apache.log4j.Logger;


/**
 *
 * @author Admin
 */
@UrlBinding("/pengambilan/keputusanBorangM")
public class KeputusanBorangMActionBean extends AbleActionBean {
    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    PermohonanPihakDAO permohonanPihakDAO;
    @Inject
    HakmilikDAO hakmilikDAO;
    @Inject
    private PembetulanService pembetulanService;
    @Inject
    PengambilanService pengambilanService;
    @Inject
    private PermohonanPihakService permohonanPihakService;
    @Inject
    private FasaPermohonanService fasaPermohonanService;
    @Inject
    NotisPenerimaanService notisPenerimaanService;
    @Inject
    private NotisService notisService;
//    @Inject
//    private HakmilikPermohonanService hakmilikPermohonanService;
    private Hakmilik hakmilik;
    private HakmilikPerbicaraan hakmilikPerbicaraan;
    private PerbicaraanKehadiran perbicaraanKehadiran;
    private PermohonanPihak permohonanPihak;
    private PermohonanPihak permohonanPihak2;
    private String idPermohonan;
    private String idHakmilik;
    private String idPihak;
    private String idDokumen;
    private String tajuk;
    private String tarikhNotis;
    private String kodStatusTerima;
    private String tarikhHantar;
    private String tarikhTampal;
    private List<PermohonanPihak> permohonanPihakList;
    private List<PerbicaraanKehadiran> perbicaraanKehadiranList;
    private List<HakmilikPermohonan> hakmilikPermohonanList;
    DateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    private Permohonan permohonan;
    private KodCawangan cawangan;
    private HakmilikPermohonan hakmilikPermohonan;
    private Penilaian penilaian;
    private FasaPermohonan kedesakan;
    private Notis borangNotis;
    private Dokumen idDokumenH;
    private Dokumen idDokumenI;
    private String kodKedesakan;
    private String tanahItem;
    private String bngnItem;
    private String lainItem;
    private String idMP;
    private long dokumenH;
    private long dokumenI;
    private Date tarikhTerimaH;
    private Date tarikhTerimaI;
    private String tempohH;
    private String tempohI;
    private int tempohImonth;
    private int tempohIyear;
    private BigDecimal tanahAmaun;
    private BigDecimal bngnAmaun;
    private BigDecimal lainAmaun;
    private BigDecimal itemNilaianTanah;
    private BigDecimal itemNilaianBngn;
    private BigDecimal itemNilaianLain;
    private BigDecimal jumlah;
    private Date calendar;
    private List<Penilaian> penilaianTanahList;
    private List<Penilaian> penilaianBngnList;
    private List<Penilaian> penilaianLainList;
    private List<Notis> borangNotisList;
    private long idPermohonanPihak;
    private String pecahSyarat;
    private String keputusanUlasan;
    private static final Logger LOG = Logger.getLogger(KeputusanBorangMActionBean.class);
//    private List<PerbicaraanKehadiran> hakmilikPerbicaraanList;

    @DefaultHandler
    public Resolution showForm() {
        LOG.info("showForm");
        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        Permohonan p = permohonanDAO.findById(idPermohonan);
        LOG.info("idPermohonan " + idPermohonan);
        if (idPermohonan != null) {

            // Permohonan HAkmilik Kedesakan
            FasaPermohonan fasaPermohonan = null;
            try {
                // bila split kedesakan
                fasaPermohonan = fasaPermohonanService.getKodKedesakan(idPermohonan, "DE");
                System.out.println("fasa " + fasaPermohonan.getIdAliran());
                if (fasaPermohonan != null) {
                    // listkan ade kedesakan
                    hakmilikPermohonanList = pengambilanService.findbyIdHakmilikAdaKedesakan(idPermohonan);
                }
            } catch (Exception h) {

                hakmilikPermohonanList = pengambilanService.findbyIdHakmilikTiadaKedesakan(idPermohonan);
                System.out.println("Error fasa Permohonan " + h);
            }
        }
        notisPenerimaanService.simpanPermohonanPihak(p, peng);
        return new JSP("pengambilan/KeputusanBorangM.jsp").addParameter("tab", "true");
    }

    public Resolution hakmilikDetails() {
        LOG.info("hakmilikDetails");
        idHakmilik = (String) getContext().getRequest().getSession().getAttribute("idHakmilik");
        LOG.info("idHakmilik " + idHakmilik);

        hakmilik = hakmilikDAO.findById(idHakmilik);
        try {
            idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
            LOG.info("idPermohonan " + idPermohonan);
            hakmilikPermohonan = pembetulanService.findByIdHakmilikIdPermohonan(idPermohonan, idHakmilik);

            long idMH = hakmilikPermohonan.getId();
            System.out.println("idMH " + idMH);

            hakmilikPerbicaraan = notisPenerimaanService.getHakmilikPerbicaraanByidMH(idMH);
            System.out.println("-------hakmilikDetails.hakmilikPerbicaraan-----------" + hakmilikPerbicaraan);
            LOG.info("hakmilikPerbicaraan.getIdPerbicaraan() " + hakmilikPerbicaraan.getIdPerbicaraan());

//            long idPihak = Long.parseLong(getContext().getRequest().getParameter("idPihak"));
//            LOG.info("idPihak " + idPihak);
////            idPihak =
//            List<PermohonanPihak> permohonanPihak2 = notisPenerimaanService.getSenaraiPmohonPihakByIdHakmilik2(idPermohonan, hakmilik.getIdHakmilik());
////            permohonanPihak = notisPenerimaanService.getSenaraiPmohonPihakByIdHakmilik(idPermohonan, hakmilik.getIdHakmilik());
//            idPermohonanPihak = permohonanPihak.getIdPermohonanPihak();
//            LOG.info("id mohon pihak :" + idPermohonanPihak);
//            perbicaraanKehadiran = notisPenerimaanService.getPerbicaraanKehadiranbyidMPidBicara(idPermohonanPihak, hakmilikPerbicaraan.getIdPerbicaraan());
            perbicaraanKehadiranList = notisPenerimaanService.getPerbicaraanKehadiranbyidMPidBicaraList(hakmilikPerbicaraan.getIdPerbicaraan());


//            System.out.println("ID hadir Perbicaraan " + perbicaraanKehadiran.getIdKehadiran());
        } catch (Exception h) {
            LOG.info("Error Bicara " + h);
        }

        return new JSP("pengambilan/KeputusanBorangM.jsp").addParameter("tab", "true");
    }

    public Resolution pihakDetails() throws ParseException {

        LOG.info("pihak Details");
        Pengguna pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        idHakmilik = (String) getContext().getRequest().getSession().getAttribute("idHakmilik");
        hakmilik = hakmilikDAO.findById(idHakmilik);
        getContext().getRequest().getSession().removeAttribute("permohonanPihak");

        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        char c = idPermohonan.charAt(0);
        System.out.println("ID Permohonan " + idPermohonan);

        long idPihak = Long.parseLong(getContext().getRequest().getParameter("idPihak"));
        long idMP = Long.parseLong(getContext().getRequest().getParameter("idMP"));

        System.out.println("-------pihakDetails.idPihak-----------" + idPihak);
        if (idPermohonan != null) {
            permohonan = permohonanDAO.findById(idPermohonan);
            cawangan = permohonan.getCawangan();
        }

        hakmilikPermohonan = pembetulanService.findByIdHakmilikIdPermohonan(idPermohonan, idHakmilik);
        long idMH = hakmilikPermohonan.getId();
        System.out.println("-------pihakDetails.idMH-----------" + idMH);
        hakmilikPerbicaraan = notisPenerimaanService.getHakmilikPerbicaraanByidMH(idMH);
//        hakmilikPerbicaraan = notisPenerimaanService.getHakmilikPerbicaraanByidMH(idMH);
        perbicaraanKehadiranList = notisPenerimaanService.getPerbicaraanKehadiranbyidMPidBicaraList(hakmilikPerbicaraan.getIdPerbicaraan());
        if (hakmilikPerbicaraan == null) {
            hakmilikPerbicaraan = new HakmilikPerbicaraan();
            InfoAudit infoAudit = new InfoAudit();
            infoAudit.setDimasukOleh(pengguna);
            infoAudit.setTarikhMasuk(new java.util.Date());
            hakmilikPerbicaraan.setCawangan(cawangan);
            hakmilikPerbicaraan.setHakmilikPermohonan(hakmilikPermohonan);
            hakmilikPerbicaraan.setInfoAudit(infoAudit);
            hakmilikPerbicaraan.setBatalRizab('T');
            hakmilikPerbicaraan.setKawasanPBT('T');
            hakmilikPerbicaraan.setPelanPembangunan('T');
            hakmilikPerbicaraan = notisPenerimaanService.saveOrUpdateHakmilikPerbicaraan(hakmilikPerbicaraan);
        }

        itemNilaianTanah = BigDecimal.ZERO;
        itemNilaianBngn = BigDecimal.ZERO;
        itemNilaianLain = BigDecimal.ZERO;
        jumlah = BigDecimal.ZERO;

        idPihak = Long.parseLong(getContext().getRequest().getParameter("idPihak"));
        System.out.println("------pihakDetails.idPihak---------" + idPihak);
        System.out.println("------pihakDetails.hakmilik.getIdHakmilik()---------" + hakmilik.getIdHakmilik());
//        List<PermohonanPihak> permohonanPihakList = notisPenerimaanService.getSenaraiPmohonPihakByIdHakmilikIdPihak2(idPermohonan, hakmilik.getIdHakmilik(), idPihak);
        permohonanPihak = permohonanPihakService.getSenaraiMohonPihakByIdPihakIdHakmilik2(idPihak, idHakmilik, idPermohonan, idMP);

        if (permohonanPihak == null) {

            addSimpleError("Tiada Data");
        } else {
            // for (PermohonanPihak permohonanPihakp : permohonanPihakList) {
            //     i++;
            //    permohonanPihak = permohonanPihakp.getPermohonan().getSenaraiPihak().get(i);
            idPermohonanPihak = permohonanPihak.getIdPermohonanPihak();
            System.out.println("------pihakDetails.idPermohonanPihak---------" + idPermohonanPihak);
            System.out.println("------pihakDetails.hakmilikPerbicaraan.getIdPerbicaraan()---------" + hakmilikPerbicaraan.getIdPerbicaraan());
            perbicaraanKehadiran = notisPenerimaanService.getPerbicaraanKehadiranbyidMPidBicara(idPermohonanPihak, hakmilikPerbicaraan.getIdPerbicaraan());
            System.out.println("------pihakDetails.perbicaraanKehadiran---------" + perbicaraanKehadiran);
            LOG.info("pihakDetails.Perbicaaran Kehadiran () " + perbicaraanKehadiran.getNama());
            if (perbicaraanKehadiran != null) {
                System.out.println("------pihakDetails.perbicaraanKehadiran.getIdKehadiran()---------" + perbicaraanKehadiran.getIdKehadiran());
                keputusanUlasan=perbicaraanKehadiran.getPenilaiUlasan();
                pecahSyarat=perbicaraanKehadiran.getPenuntut();
                penilaianTanahList = notisPenerimaanService.getPenilaianTanahByidHadir(perbicaraanKehadiran.getIdKehadiran());
                penilaianBngnList = notisPenerimaanService.getPenilaianBngnByidHadir(perbicaraanKehadiran.getIdKehadiran());
                penilaianLainList = notisPenerimaanService.getPenilaianLainByidHadir(perbicaraanKehadiran.getIdKehadiran());
                for (Penilaian penilaian : penilaianTanahList) {
                    itemNilaianTanah = itemNilaianTanah.add(penilaian.getAmaun());
                }
                for (Penilaian penilaian : penilaianBngnList) {
                    itemNilaianBngn = itemNilaianBngn.add(penilaian.getAmaun());
                }
                for (Penilaian penilaian : penilaianLainList) {
                    itemNilaianLain = itemNilaianLain.add(penilaian.getAmaun());
                }
                jumlah = jumlah.add(itemNilaianTanah).add(itemNilaianBngn).add(itemNilaianLain);
            }
            // }

        }
        // double tempohHmonth=  Double.parseDouble(string month);
        Calendar cal = Calendar.getInstance();
        calendar = cal.getTime();
        int currentYear = cal.get(Calendar.YEAR);
        System.out.println("--CurrentYear" + currentYear);
        int currentMonth = cal.get(Calendar.MONTH);
        System.out.println("--CurrentMonnth--" + currentMonth);
        kedesakan = fasaPermohonanService.getKodKedesakan(idPermohonan, "DE");
        // if (kedesakan != null) {
        //  if (kedesakan.getKeputusan().getKod().equalsIgnoreCase("DE")) {
        // kodKedesakan = kedesakan.getKeputusan().getKod();
        borangNotisList = notisService.getNotisBorang(idPermohonan);
        for (int i = 0; i < borangNotisList.size(); i++) {
            borangNotis = borangNotisList.get(i);
            Calendar cal2 = Calendar.getInstance();
            if (borangNotis.getKodNotis().getKod().equalsIgnoreCase("NBH")) {
                idDokumenH = notisService.getNotisBorangByidDokumen(borangNotis.getDokumenNotis().getIdDokumen());
                System.out.println("--------idDokumenH-------" + idDokumenH.getIdDokumen());
                dokumenH = idDokumenH.getIdDokumen();
                System.out.println("--------dokumenH-------" + dokumenH);
                System.out.println("--------tarikhTerimaH---------" + borangNotis.getTarikhTerima());
                tarikhTerimaH = borangNotis.getTarikhTerima();
                cal2.setTime(borangNotis.getTarikhTerima());
                System.out.println(cal2);
                int lastMonth = cal2.get(Calendar.MONTH);
                System.out.println("--lastMonth--" + lastMonth);
                System.out.println("--currentMonth--" + currentMonth);
                int lastYear = cal2.get(Calendar.YEAR);
                System.out.println("--lastYear--" + lastYear);
                System.out.println("--lastYear--" + currentYear);

                if (lastMonth > currentMonth) {
                    int year = currentYear - lastYear;
                    int month = lastMonth - currentMonth;


                    if (month != 0) {
                        tempohH = month + " bulan " + year + " tahun";

                    } else {
                        tempohH = year + " tahun";

                    }

                } else if (currentMonth > lastMonth) {
                    int year = currentYear - lastYear;
                    int month = currentMonth - lastMonth;

                    if (month != 0) {
                        tempohH = month + " bulan " + year + " tahun";

                    } else {
                        tempohH = year + " tahun";

                    }

                }

                System.out.println("--------Tarikh Terima H-----------" + tarikhTerimaH);
            } else if (borangNotis.getKodNotis().getKod().equalsIgnoreCase("NBI")) {
                idDokumenI = notisService.getNotisBorangByidDokumen(borangNotis.getDokumenNotis().getIdDokumen());
                System.out.println("--------idDokumenI-------" + idDokumenI.getIdDokumen());
                dokumenI = idDokumenI.getIdDokumen();
                System.out.println("--------dokumenI-------" + dokumenI);
                System.out.println("--------tarikhTerimaI---------" + borangNotis.getTarikhTerima());

                try {
                    tarikhTerimaI = borangNotis.getTarikhTerima();
                    cal2.setTime(borangNotis.getTarikhTerima());
                    int lastMonth = cal2.get(Calendar.MONTH);
                    int lastYear = cal2.get(Calendar.YEAR);
                    if (lastMonth > currentMonth) {
                        int year = currentYear - lastYear;
                        int month = lastMonth - currentMonth;
                        if (month != 0) {
                            tempohI = month + " bulan " + year + " tahun";
                            tempohImonth = month;
                            tempohIyear = year;
                        } else {
                            tempohI = year + " tahun";
                            tempohIyear = year;
                        }
                    } else if (currentMonth > lastMonth) {
                        int year = currentYear - lastYear;
                        int month = currentMonth - lastMonth;
                        if (month != 0) {
                            tempohI = month + " bulan " + year + " tahun";
                            tempohImonth = month;
                            tempohIyear = year;
                        } else {
                            tempohI = year + " tahun";
                            tempohIyear = year;
                        }
                    }
                } catch (Exception v) {
                }
                System.out.println("--------Tarikh Terima I-----------" + tarikhTerimaI);
            }
            //  }
            //  }
        }


        getContext().getRequest().setAttribute("showDetails", Boolean.TRUE);
        return new JSP("pengambilan/KeputusanBorangM.jsp").addParameter("tab", "true");
    }

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        idHakmilik = (String) getContext().getRequest().getParameter("idHakmilik");

        if (idHakmilik != null) {
            getContext().getRequest().getSession().setAttribute("idHakmilik", idHakmilik);

        }

        if (idPermohonan != null) {
            Permohonan permohonan = permohonanDAO.findById(idPermohonan);
            if (permohonan != null) {
                //  hakmilikPermohonanList = permohonan.getSenaraiHakmilik();
                FasaPermohonanService fasaPermohonanService = null;

                // Permohonan HAkmilik Kedesakan
                FasaPermohonan fasaPermohonan = null;
                try {
                    // bila split kedesakan
                    fasaPermohonan = fasaPermohonanService.getKodKedesakan(idPermohonan, "DE");
                    // listkan ade kedesakan
                    hakmilikPermohonanList = pengambilanService.findbyIdHakmilikAdaKedesakan(idPermohonan);
                } catch (Exception h) {

                    try {
                        /// semua desak
                        hakmilikPermohonanList = pengambilanService.findbyIdHakmilikAdaKedesakan(idPermohonan);
                        if (hakmilikPermohonanList.size() == 0) {
                            /// permohonan hakmilik tiada kedesakan
                            hakmilikPermohonanList = pengambilanService.findbyIdHakmilikTiadaKedesakan(idPermohonan);
                        }
                    } catch (Exception x) {
                        System.out.println("Error fasa Permohonan " + h);
                    }
                }
            }

        }


    }

    public Resolution refreshpage() {
        rehydrate();
        return new RedirectResolution(RekodPenerimaanBayaranPampasan31aActionBean.class, "locate");
    }

    public Resolution simpanTanah() {
        Pengguna pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        hakmilikPermohonan = pembetulanService.findByIdHakmilikIdPermohonan(idPermohonan, idHakmilik);
        long idMH = hakmilikPermohonan.getId();
        if (idPermohonan != null) {
            permohonan = permohonanDAO.findById(idPermohonan);
            cawangan = permohonan.getCawangan();
        }
        InfoAudit infoAudit = new InfoAudit();
        infoAudit.setDimasukOleh(pengguna);
        infoAudit.setTarikhMasuk(new java.util.Date());

        hakmilikPerbicaraan = notisPenerimaanService.getHakmilikPerbicaraanByidMH(idMH);
        if (hakmilikPerbicaraan == null) {
            hakmilikPerbicaraan = new HakmilikPerbicaraan();
            hakmilikPerbicaraan.setCawangan(cawangan);
            hakmilikPerbicaraan.setHakmilikPermohonan(hakmilikPermohonan);
            hakmilikPerbicaraan.setInfoAudit(infoAudit);
            hakmilikPerbicaraan.setBatalRizab('T');
            hakmilikPerbicaraan.setKawasanPBT('T');
            hakmilikPerbicaraan.setPelanPembangunan('T');
            hakmilikPerbicaraan = notisPenerimaanService.saveOrUpdateHakmilikPerbicaraan(hakmilikPerbicaraan);
        }else{
            hakmilikPerbicaraan.setPecahSyarat(pecahSyarat);
            notisPenerimaanService.saveOrUpdateHakmilikPerbicaraan(hakmilikPerbicaraan);
        }
        permohonanPihak = permohonanPihakDAO.findById(idPermohonanPihak);

        if (permohonanPihak != null) {
            perbicaraanKehadiran = notisPenerimaanService.getPerbicaraanKehadiranbyidMPidBicara(idPermohonanPihak, hakmilikPerbicaraan.getIdPerbicaraan());
            if (perbicaraanKehadiran == null) {
                perbicaraanKehadiran = new PerbicaraanKehadiran();
                perbicaraanKehadiran.setPerbicaraan(hakmilikPerbicaraan);
                perbicaraanKehadiran.setCawangan(cawangan);
                perbicaraanKehadiran.setInfoAudit(infoAudit);
                perbicaraanKehadiran.setPihak(permohonanPihak);
                perbicaraanKehadiran.setPenilaiUlasan(keputusanUlasan);
                perbicaraanKehadiran.setPenuntut(pecahSyarat);
                notisPenerimaanService.saveOrUpdatePerbicaraanKehadiran(perbicaraanKehadiran);

//                BigDecimal defValue=BigDecimal.
//                BigDecimal nilai=hakmilikPerbicaraan.getKeputusanNilai();
//                BigDecimal penyebut=new BigDecimal(permohonanPihak.getSyerPenyebut());
//                BigDecimal pembilang=new BigDecimal(permohonanPihak.getSyerPembilang());
//                BigDecimal syer=penyebut.divide(pembilang);
//                BigDecimal jumlahPampasan=nilai.multiply(syer);



                penilaian = new Penilaian();
                penilaian.setCawangan(cawangan);
                penilaian.setKehadiran(perbicaraanKehadiran);
                penilaian.setInfoAudit(infoAudit);
                penilaian.setItem("Keputusan Mahkamah");
                penilaian.setAmaun(tanahAmaun);
                penilaian.setJenis('T');

//                penilaian.setPeratus(new BigDecimal(0.00));
                notisPenerimaanService.saveOrUpdatePenilaian(penilaian);

            } else {
                perbicaraanKehadiran.setPenilaiUlasan(keputusanUlasan);
                perbicaraanKehadiran.setPenuntut(pecahSyarat);
                notisPenerimaanService.saveOrUpdatePerbicaraanKehadiran(perbicaraanKehadiran);
                if(tanahAmaun!=null){
                penilaian = new Penilaian();
                penilaian.setCawangan(cawangan);
                penilaian.setKehadiran(perbicaraanKehadiran);
                penilaian.setInfoAudit(infoAudit);
                penilaian.setItem("Keputusan Mahkamah");
                penilaian.setAmaun(tanahAmaun);
                penilaian.setJenis('T');
//                penilaian.setPeratus(new BigDecimal(0.00));
                notisPenerimaanService.saveOrUpdatePenilaian(penilaian);
                }
            }
            tanahItem = "";
            tanahAmaun = BigDecimal.ZERO;
        }


        idHakmilik = (String) getContext().getRequest().getSession().getAttribute("idHakmilik");
        hakmilik = hakmilikDAO.findById(idHakmilik);

        itemNilaianTanah = BigDecimal.ZERO;
        itemNilaianBngn = BigDecimal.ZERO;
        itemNilaianLain = BigDecimal.ZERO;
        jumlah = BigDecimal.ZERO;

        if (permohonanPihak == null) {

            addSimpleError("Tiada Data");
        } else {

            idPermohonanPihak = permohonanPihak.getIdPermohonanPihak();
            perbicaraanKehadiran = notisPenerimaanService.getPerbicaraanKehadiranbyidMPidBicara(idPermohonanPihak, hakmilikPerbicaraan.getIdPerbicaraan());
            if (perbicaraanKehadiran != null) {
                penilaianTanahList = notisPenerimaanService.getPenilaianTanahByidHadir(perbicaraanKehadiran.getIdKehadiran());
                penilaianBngnList = notisPenerimaanService.getPenilaianBngnByidHadir(perbicaraanKehadiran.getIdKehadiran());
                penilaianLainList = notisPenerimaanService.getPenilaianLainByidHadir(perbicaraanKehadiran.getIdKehadiran());
                for (Penilaian penilaian : penilaianTanahList) {
                    itemNilaianTanah = itemNilaianTanah.add(penilaian.getAmaun());
                }
                for (Penilaian penilaian : penilaianBngnList) {
                    itemNilaianBngn = itemNilaianBngn.add(penilaian.getAmaun());
                }
                for (Penilaian penilaian : penilaianLainList) {
                    itemNilaianLain = itemNilaianLain.add(penilaian.getAmaun());
                }
                jumlah = jumlah.add(itemNilaianTanah).add(itemNilaianBngn).add(itemNilaianLain);
            }

        }
        getContext().getRequest().setAttribute("showDetails", Boolean.TRUE);

        return new JSP("pengambilan/KeputusanBorangM.jsp").addParameter("tab", "true");
    }

    public Resolution simpanBngn() {
        Pengguna pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        hakmilikPermohonan = pembetulanService.findByIdHakmilikIdPermohonan(idPermohonan, idHakmilik);
        long idMH = hakmilikPermohonan.getId();
        if (idPermohonan != null) {
            permohonan = permohonanDAO.findById(idPermohonan);
            cawangan = permohonan.getCawangan();
        }

        InfoAudit infoAudit = new InfoAudit();
        infoAudit.setDimasukOleh(pengguna);
        infoAudit.setTarikhMasuk(new java.util.Date());

        hakmilikPerbicaraan = notisPenerimaanService.getHakmilikPerbicaraanByidMH(idMH);
        if (hakmilikPerbicaraan == null) {
            hakmilikPerbicaraan = new HakmilikPerbicaraan();
            hakmilikPerbicaraan.setCawangan(cawangan);
            hakmilikPerbicaraan.setHakmilikPermohonan(hakmilikPermohonan);
            hakmilikPerbicaraan.setInfoAudit(infoAudit);
            hakmilikPerbicaraan.setBatalRizab('T');
            hakmilikPerbicaraan.setKawasanPBT('T');
            hakmilikPerbicaraan.setPelanPembangunan('T');
            hakmilikPerbicaraan = notisPenerimaanService.saveOrUpdateHakmilikPerbicaraan(hakmilikPerbicaraan);
        }
        permohonanPihak = permohonanPihakDAO.findById(idPermohonanPihak);

        if (permohonanPihak != null) {
            perbicaraanKehadiran = notisPenerimaanService.getPerbicaraanKehadiranbyidMPidBicara(idPermohonanPihak, hakmilikPerbicaraan.getIdPerbicaraan());
            if (perbicaraanKehadiran == null) {
                perbicaraanKehadiran = new PerbicaraanKehadiran();
                perbicaraanKehadiran.setPerbicaraan(hakmilikPerbicaraan);
                perbicaraanKehadiran.setCawangan(cawangan);
                perbicaraanKehadiran.setInfoAudit(infoAudit);
                perbicaraanKehadiran.setPihak(permohonanPihak);
                notisPenerimaanService.saveOrUpdatePerbicaraanKehadiran(perbicaraanKehadiran);

                penilaian = new Penilaian();
                penilaian.setCawangan(cawangan);
                penilaian.setKehadiran(perbicaraanKehadiran);
                penilaian.setInfoAudit(infoAudit);
                penilaian.setItem(bngnItem);
                penilaian.setAmaun(bngnAmaun);
                penilaian.setJenis('B');
//                penilaian.setPeratus(new BigDecimal(0.00));
                notisPenerimaanService.saveOrUpdatePenilaian(penilaian);

            } else {

                penilaian = new Penilaian();
                penilaian.setCawangan(cawangan);
                penilaian.setKehadiran(perbicaraanKehadiran);
                penilaian.setInfoAudit(infoAudit);
                penilaian.setItem(bngnItem);
                penilaian.setAmaun(bngnAmaun);
                penilaian.setJenis('B');
//                penilaian.setPeratus(new BigDecimal(0.00));
                notisPenerimaanService.saveOrUpdatePenilaian(penilaian);
            }
            bngnItem = "";
            bngnAmaun = BigDecimal.ZERO;
        }

        idHakmilik = (String) getContext().getRequest().getSession().getAttribute("idHakmilik");
        hakmilik = hakmilikDAO.findById(idHakmilik);

        itemNilaianTanah = BigDecimal.ZERO;
        itemNilaianBngn = BigDecimal.ZERO;
        itemNilaianLain = BigDecimal.ZERO;
        jumlah = BigDecimal.ZERO;

        if (permohonanPihak == null) {

            addSimpleError("Tiada Data");
        } else {

            idPermohonanPihak = permohonanPihak.getIdPermohonanPihak();
            perbicaraanKehadiran = notisPenerimaanService.getPerbicaraanKehadiranbyidMPidBicara(idPermohonanPihak, hakmilikPerbicaraan.getIdPerbicaraan());
            if (perbicaraanKehadiran != null) {
                penilaianTanahList = notisPenerimaanService.getPenilaianTanahByidHadir(perbicaraanKehadiran.getIdKehadiran());
                penilaianBngnList = notisPenerimaanService.getPenilaianBngnByidHadir(perbicaraanKehadiran.getIdKehadiran());
                penilaianLainList = notisPenerimaanService.getPenilaianLainByidHadir(perbicaraanKehadiran.getIdKehadiran());
                for (Penilaian penilaian : penilaianTanahList) {
                    itemNilaianTanah = itemNilaianTanah.add(penilaian.getAmaun());
                }
                for (Penilaian penilaian : penilaianBngnList) {
                    itemNilaianBngn = itemNilaianBngn.add(penilaian.getAmaun());
                }
                for (Penilaian penilaian : penilaianLainList) {
                    itemNilaianLain = itemNilaianLain.add(penilaian.getAmaun());
                }
                jumlah = jumlah.add(itemNilaianTanah).add(itemNilaianBngn).add(itemNilaianLain);
            }

        }
        getContext().getRequest().setAttribute("showDetails", Boolean.TRUE);

        return new JSP("pengambilan/KeputusanBorangM.jsp").addParameter("tab", "true");
    }

    public Resolution simpanLain() {
        Pengguna pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        hakmilikPermohonan = pembetulanService.findByIdHakmilikIdPermohonan(idPermohonan, idHakmilik);
        long idMH = hakmilikPermohonan.getId();
        if (idPermohonan != null) {
            permohonan = permohonanDAO.findById(idPermohonan);
            cawangan = permohonan.getCawangan();
        }
        InfoAudit infoAudit = new InfoAudit();
        infoAudit.setDimasukOleh(pengguna);
        infoAudit.setTarikhMasuk(new java.util.Date());
        hakmilikPerbicaraan = notisPenerimaanService.getHakmilikPerbicaraanByidMH(idMH);
        if (hakmilikPerbicaraan == null) {
            hakmilikPerbicaraan = new HakmilikPerbicaraan();
            hakmilikPerbicaraan.setCawangan(cawangan);
            hakmilikPerbicaraan.setHakmilikPermohonan(hakmilikPermohonan);
            hakmilikPerbicaraan.setInfoAudit(infoAudit);
            hakmilikPerbicaraan.setBatalRizab('T');
            hakmilikPerbicaraan.setKawasanPBT('T');
            hakmilikPerbicaraan.setPelanPembangunan('T');
            hakmilikPerbicaraan = notisPenerimaanService.saveOrUpdateHakmilikPerbicaraan(hakmilikPerbicaraan);
        }
        permohonanPihak = permohonanPihakDAO.findById(idPermohonanPihak);

        if (permohonanPihak != null) {
            perbicaraanKehadiran = notisPenerimaanService.getPerbicaraanKehadiranbyidMPidBicara(idPermohonanPihak, hakmilikPerbicaraan.getIdPerbicaraan());
            if (perbicaraanKehadiran == null) {
                perbicaraanKehadiran = new PerbicaraanKehadiran();
                perbicaraanKehadiran.setPerbicaraan(hakmilikPerbicaraan);
                perbicaraanKehadiran.setCawangan(cawangan);
                perbicaraanKehadiran.setInfoAudit(infoAudit);
                perbicaraanKehadiran.setPihak(permohonanPihak);
                notisPenerimaanService.saveOrUpdatePerbicaraanKehadiran(perbicaraanKehadiran);

                penilaian = new Penilaian();
                penilaian.setCawangan(cawangan);
                penilaian.setKehadiran(perbicaraanKehadiran);
                penilaian.setInfoAudit(infoAudit);
                penilaian.setItem(lainItem);
                penilaian.setAmaun(lainAmaun);
                penilaian.setJenis('L');
//                penilaian.setPeratus(new BigDecimal(0.00));
                notisPenerimaanService.saveOrUpdatePenilaian(penilaian);

            } else {

                penilaian = new Penilaian();
                penilaian.setCawangan(cawangan);
                penilaian.setKehadiran(perbicaraanKehadiran);
                penilaian.setInfoAudit(infoAudit);
                penilaian.setItem(lainItem);
                penilaian.setAmaun(lainAmaun);
                penilaian.setJenis('L');
//                penilaian.setPeratus(new BigDecimal(0.00));
                notisPenerimaanService.saveOrUpdatePenilaian(penilaian);
            }
            lainItem = "";
            lainAmaun = BigDecimal.ZERO;
        }

        idHakmilik = (String) getContext().getRequest().getSession().getAttribute("idHakmilik");
        hakmilik = hakmilikDAO.findById(idHakmilik);

        itemNilaianTanah = BigDecimal.ZERO;
        itemNilaianBngn = BigDecimal.ZERO;
        itemNilaianLain = BigDecimal.ZERO;
        jumlah = BigDecimal.ZERO;

        if (permohonanPihak == null) {

            addSimpleError("Tiada Data");
        } else {

            idPermohonanPihak = permohonanPihak.getIdPermohonanPihak();
            perbicaraanKehadiran = notisPenerimaanService.getPerbicaraanKehadiranbyidMPidBicara(idPermohonanPihak, hakmilikPerbicaraan.getIdPerbicaraan());
            if (perbicaraanKehadiran != null) {
                penilaianTanahList = notisPenerimaanService.getPenilaianTanahByidHadir(perbicaraanKehadiran.getIdKehadiran());
                penilaianBngnList = notisPenerimaanService.getPenilaianBngnByidHadir(perbicaraanKehadiran.getIdKehadiran());
                penilaianLainList = notisPenerimaanService.getPenilaianLainByidHadir(perbicaraanKehadiran.getIdKehadiran());
                for (Penilaian penilaian : penilaianTanahList) {
                    itemNilaianTanah = itemNilaianTanah.add(penilaian.getAmaun());
                }
                for (Penilaian penilaian : penilaianBngnList) {
                    itemNilaianBngn = itemNilaianBngn.add(penilaian.getAmaun());
                }
                for (Penilaian penilaian : penilaianLainList) {
                    itemNilaianLain = itemNilaianLain.add(penilaian.getAmaun());
                }
                jumlah = jumlah.add(itemNilaianTanah).add(itemNilaianBngn).add(itemNilaianLain);
            }

        }
        getContext().getRequest().setAttribute("showDetails", Boolean.TRUE);

        penilaianTanahList = notisPenerimaanService.getPenilaianTanahByidHadir(perbicaraanKehadiran.getIdKehadiran());
        return new JSP("pengambilan/KeputusanBorangM.jsp").addParameter("tab", "true");
    }

    public Resolution deleteNilai() {
        InfoAudit ia = new InfoAudit();
        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);

        penilaian = new Penilaian();
        String idNilai = getContext().getRequest().getParameter("idPenilaian");
        penilaian = notisPenerimaanService.getPenilaianBngnByidNilaian(Long.parseLong(idNilai));
        System.out.println("permohonanRujukanLuarValue" + idNilai);
        if (penilaian != null) {
            notisPenerimaanService.deleteAllNilai(penilaian);
        }
//        rehydrate();
        getContext().getRequest().getSession().setAttribute("idHakmilik", idHakmilik);
        getContext().getRequest().setAttribute("showDetails", Boolean.TRUE);
        return new JSP("pengambilan/KeputusanBorangM.jsp").addParameter("tab", "true");
    }

    public List<PermohonanPihak> getPermohonanPihakList() {
        return permohonanPihakList;
    }

    public void setPermohonanPihakList(List<PermohonanPihak> permohonanPihakList) {
        this.permohonanPihakList = permohonanPihakList;
    }

    public List<HakmilikPermohonan> getHakmilikPermohonanList() {
        return hakmilikPermohonanList;
    }

    public void setHakmilikPermohonanList(List<HakmilikPermohonan> hakmilikPermohonanList) {
        this.hakmilikPermohonanList = hakmilikPermohonanList;
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

    public String getIdDokumen() {
        return idDokumen;
    }

    public void setIdDokumen(String idDokumen) {
        this.idDokumen = idDokumen;
    }

    public String getIdPihak() {
        return idPihak;
    }

    public void setIdPihak(String idPihak) {
        this.idPihak = idPihak;
    }

    public String getKodStatusTerima() {
        return kodStatusTerima;
    }

    public void setKodStatusTerima(String kodStatusTerima) {
        this.kodStatusTerima = kodStatusTerima;
    }

    public String getTajuk() {
        return tajuk;
    }

    public void setTajuk(String tajuk) {
        this.tajuk = tajuk;
    }

    public String getTarikhHantar() {
        return tarikhHantar;
    }

    public void setTarikhHantar(String tarikhHantar) {
        this.tarikhHantar = tarikhHantar;
    }

    public String getTarikhNotis() {
        return tarikhNotis;
    }

    public void setTarikhNotis(String tarikhNotis) {
        this.tarikhNotis = tarikhNotis;
    }

    public String getTarikhTampal() {
        return tarikhTampal;
    }

    public void setTarikhTampal(String tarikhTampal) {
        this.tarikhTampal = tarikhTampal;
    }

    public HakmilikPermohonan getHakmilikPermohonan() {
        return hakmilikPermohonan;
    }

    public void setHakmilikPermohonan(HakmilikPermohonan hakmilikPermohonan) {
        this.hakmilikPermohonan = hakmilikPermohonan;
    }

    public HakmilikPerbicaraan getHakmilikPerbicaraan() {
        return hakmilikPerbicaraan;
    }

    public void setHakmilikPerbicaraan(HakmilikPerbicaraan hakmilikPerbicaraan) {
        this.hakmilikPerbicaraan = hakmilikPerbicaraan;
    }

    public PermohonanPihak getPermohonanPihak() {
        return permohonanPihak;
    }

    public void setPermohonanPihak(PermohonanPihak permohonanPihak) {
        this.permohonanPihak = permohonanPihak;
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

    public Penilaian getPenilaian() {
        return penilaian;
    }

    public void setPenilaian(Penilaian penilaian) {
        this.penilaian = penilaian;
    }

    public PerbicaraanKehadiran getPerbicaraanKehadiran() {
        return perbicaraanKehadiran;
    }

    public void setPerbicaraanKehadiran(PerbicaraanKehadiran perbicaraanKehadiran) {
        this.perbicaraanKehadiran = perbicaraanKehadiran;
    }

    public BigDecimal getTanahAmaun() {
        return tanahAmaun;
    }

    public void setTanahAmaun(BigDecimal tanahAmaun) {
        this.tanahAmaun = tanahAmaun;
    }

    public String getTanahItem() {
        return tanahItem;
    }

    public void setTanahItem(String tanahItem) {
        this.tanahItem = tanahItem;
    }

    public List<Penilaian> getPenilaianTanahList() {
        return penilaianTanahList;
    }

    public void setPenilaianTanahList(List<Penilaian> penilaianTanahList) {
        this.penilaianTanahList = penilaianTanahList;
    }

    public long getIdPermohonanPihak() {
        return idPermohonanPihak;
    }

    public void setIdPermohonanPihak(long idPermohonanPihak) {
        this.idPermohonanPihak = idPermohonanPihak;
    }

    public BigDecimal getBngnAmaun() {
        return bngnAmaun;
    }

    public void setBngnAmaun(BigDecimal bngnAmaun) {
        this.bngnAmaun = bngnAmaun;
    }

    public String getBngnItem() {
        return bngnItem;
    }

    public void setBngnItem(String bngnItem) {
        this.bngnItem = bngnItem;
    }

    public BigDecimal getLainAmaun() {
        return lainAmaun;
    }

    public void setLainAmaun(BigDecimal lainAmaun) {
        this.lainAmaun = lainAmaun;
    }

    public String getLainItem() {
        return lainItem;
    }

    public void setLainItem(String lainItem) {
        this.lainItem = lainItem;
    }

    public List<Penilaian> getPenilaianBngnList() {
        return penilaianBngnList;
    }

    public void setPenilaianBngnList(List<Penilaian> penilaianBngnList) {
        this.penilaianBngnList = penilaianBngnList;
    }

    public List<Penilaian> getPenilaianLainList() {
        return penilaianLainList;
    }

    public void setPenilaianLainList(List<Penilaian> penilaianLainList) {
        this.penilaianLainList = penilaianLainList;
    }

    public BigDecimal getItemNilaianBngn() {
        return itemNilaianBngn;
    }

    public void setItemNilaianBngn(BigDecimal itemNilaianBngn) {
        this.itemNilaianBngn = itemNilaianBngn;
    }

    public BigDecimal getItemNilaianLain() {
        return itemNilaianLain;
    }

    public void setItemNilaianLain(BigDecimal itemNilaianLain) {
        this.itemNilaianLain = itemNilaianLain;
    }

    public BigDecimal getItemNilaianTanah() {
        return itemNilaianTanah;
    }

    public void setItemNilaianTanah(BigDecimal itemNilaianTanah) {
        this.itemNilaianTanah = itemNilaianTanah;
    }

    public BigDecimal getJumlah() {
        return jumlah;
    }

    public void setJumlah(BigDecimal jumlah) {
        this.jumlah = jumlah;
    }

    public FasaPermohonan getKedesakan() {
        return kedesakan;
    }

    public void setKedesakan(FasaPermohonan kedesakan) {
        this.kedesakan = kedesakan;
    }

    public String getKodKedesakan() {
        return kodKedesakan;
    }

    public void setKodKedesakan(String kodKedesakan) {
        this.kodKedesakan = kodKedesakan;
    }

    public long getDokumenH() {
        return dokumenH;
    }

    public void setDokumenH(long dokumenH) {
        this.dokumenH = dokumenH;
    }

    public long getDokumenI() {
        return dokumenI;
    }

    public void setDokumenI(long dokumenI) {
        this.dokumenI = dokumenI;
    }

    public Date getTarikhTerimaH() {
        return tarikhTerimaH;
    }

    public void setTarikhTerimaH(Date tarikhTerimaH) {
        this.tarikhTerimaH = tarikhTerimaH;
    }

    public Date getTarikhTerimaI() {
        return tarikhTerimaI;
    }

    public void setTarikhTerimaI(Date tarikhTerimaI) {
        this.tarikhTerimaI = tarikhTerimaI;
    }

    public Notis getBorangNotis() {
        return borangNotis;
    }

    public int getTempohImonth() {
        return tempohImonth;
    }

    public void setTempohImonth(int tempohImonth) {
        this.tempohImonth = tempohImonth;

    }

    public int getTempohIyear() {
        return tempohIyear;
    }

    public void setTempohIyear(int tempohIyear) {
        this.tempohIyear = tempohIyear;

    }

    public void setBorangNotis(Notis borangNotis) {
        this.borangNotis = borangNotis;
    }

    public List<Notis> getBorangNotisList() {
        return borangNotisList;
    }

    public void setBorangNotisList(List<Notis> borangNotisList) {
        this.borangNotisList = borangNotisList;
    }

    /**
     * @return the calendar
     */
    public Date getCalendar() {
        return calendar;
    }

    /**
     * @param calendar the calendar to set
     */
    public void setCalendar(Date calendar) {
        this.calendar = calendar;
    }

    /**
     * @return the TempohH
     */
    public String getTempohH() {
        return tempohH;
    }

    /**
     * @param TempohH the TempohH to set
     */
    public void setTempohH(String tempohH) {
        this.tempohH = tempohH;
    }

    /**
     * @return the tempohI
     */
    public String getTempohI() {
        return tempohI;
    }

    /**
     * @param tempohI the tempohI to set
     */
    public void setTempohI(String tempohI) {
        this.tempohI = tempohI;
    }

    /**
     * @return the perbicaraanKehadiranList
     */
    public List<PerbicaraanKehadiran> getPerbicaraanKehadiranList() {
        return perbicaraanKehadiranList;
    }

    public String getIdMP() {
        return idMP;
    }

    public void setIdMP(String idMP) {
        this.idMP = idMP;
    }

    /**
     * @param perbicaraanKehadiranList the perbicaraanKehadiranList to set
     */
    public void setPerbicaraanKehadiranList(List<PerbicaraanKehadiran> perbicaraanKehadiranList) {
        this.perbicaraanKehadiranList = perbicaraanKehadiranList;
    }
    public String getPecahSyarat() {
        return pecahSyarat;
    }

    public void setPecahSyarat(String pecahSyarat) {
        this.pecahSyarat = pecahSyarat;
    }
    public String getKeputusanUlasan() {
        return keputusanUlasan;
    }

    public void setKeputusanUlasan(String keputusanUlasan) {
        this.keputusanUlasan = keputusanUlasan;
    }
}
