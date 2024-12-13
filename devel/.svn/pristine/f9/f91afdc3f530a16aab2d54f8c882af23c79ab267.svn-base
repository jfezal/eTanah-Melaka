package etanah.view.stripes.pengambilan;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.dao.PermohonanKertasDAO;
import etanah.dao.PermohonanKertasKandunganDAO;
import etanah.dao.HakmilikDAO;
import etanah.dao.HakmilikPermohonanDAO;
import etanah.dao.PemohonDAO;
import etanah.dao.PermohonanDAO;
import etanah.dao.LaporanTanahDAO;
import etanah.dao.PermohonanRujukanLuarDAO;
import etanah.model.PermohonanKertas;
import etanah.model.PermohonanKertasKandungan;
import etanah.model.Hakmilik;
import etanah.model.HakmilikPermohonan;
import etanah.model.HakmilikPihakBerkepentingan;
import etanah.model.InfoAudit;
import etanah.model.KodCawangan;
import etanah.model.KodDokumen;
import etanah.model.Pemohon;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.model.PermohonanPihak;
import etanah.model.Pihak;
import etanah.model.PihakPengarah;
import etanah.model.LaporanTanah;
import etanah.model.PermohonanRujukanLuar;
import etanah.service.ConsentPtdService;
import etanah.service.PembangunanService;
import etanah.service.PendudukanSementaraMMKNService;
import etanah.service.PengambilanService;
import etanah.view.etanahActionBeanContext;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.RedirectResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;
import org.apache.commons.lang.WordUtils;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author Rajesh
 */
@UrlBinding("/pengambilan/ulasan_mmk_NS")
public class UlasanMMKNSPendudukanSementaraActionBean extends AbleActionBean {

    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;
    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    HakmilikDAO hakmilikDAO;
    @Inject
    PemohonDAO pemohonDAO;
    @Inject
    PendudukanSementaraMMKNService pendudukanSementaraMMKNService;
    @Inject
    LaporanTanahDAO laporanTanahDAO;
    @Inject
    PermohonanKertasDAO permohonanKertasDAO;
    @Inject
    PermohonanKertasKandunganDAO permohonanKertasKandunganDAO;
    @Inject
    HakmilikPermohonanDAO hakmilikPermohonanDAO;
    @Inject
    PermohonanRujukanLuarDAO permohonanRujukanLuarDAO;
    private Permohonan permohonan;
    private Hakmilik hakmilik;
    private Pemohon pemohon;
    private PermohonanPihak penerima;
    private HakmilikPihakBerkepentingan hakmilikPihakBerkepentingan;
    private PermohonanKertas permohonanKertas;
    private PermohonanKertasKandungan kertasK;
    private LaporanTanah laporanTanah;
    private String stageId;
    private Pengguna peng;
    private String huraianPentadbir;
    private String huraianPengarah;
    private String syorPentadbir;
    private String syorPengarah;
    private String syorPtg;
    private String tarikhDaftar;
    private String tarikhMesyuarat;
    private String tarikhPermohonan;
    private String kedudukanTnh;
    private String keadaanTnh;
    private String jenisTanaman;
    private String bilBangunan;
    private String tujuan;
    private String pembangunanDicadang;
    private List<PihakPengarah> listPengarah;
    private Pihak pihak;
    private List<Pemohon> listPemohon;
    private List<HakmilikPermohonan> hakmilikPermohonanList;
    private String lokasi;
    private String kmno;
    private PermohonanKertasKandungan headingObj;
    private PermohonanKertasKandungan tujuanObj;
    private PermohonanKertasKandungan syorPengarahObj;
    private PermohonanKertasKandungan syorPentadbirObj;
    private PermohonanRujukanLuar permohonanRujukanLuar;
    private String kmnoObj;
    private PermohonanKertasKandungan tarikhMesyuaratObj;
    private PermohonanKertasKandungan permohonanKertasKandungan1;
    private PermohonanKertasKandungan permohonanKertasKandungan4;
    private PermohonanKertasKandungan permohonanKertasKandungan5;
    private PermohonanKertasKandungan permohonanKertasKandungan6;
    private List<PermohonanKertasKandungan> senaraiKertasKandungan4;
    private List<PermohonanKertasKandungan> senaraiKertasKandungan5;
    private List<PermohonanKertasKandungan> senaraiKertasKandungan6;
    private String idKandungan;
    private String kandungan;
    private String heading;
    private int count5 = 0;
    private int count6 = 0;
    String str[] = {"", "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z"};
    private List senaraiSyorPentadbir[] = new ArrayList[10];
    private List senaraiSyorPengarah[] = new ArrayList[10];
//    private final String tajuk = "KERTAS PERMOHONAN PENDUDUKAN SEMENTARA NEGERI SEMBILAN";
    private SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

    //ptg
    //@DefaultHandler
    public Resolution showForm1() {
        getContext().getRequest().setAttribute("ptg", Boolean.TRUE);
        return new JSP("pengambilan/negerisembilan/pendudukansementara/ulasan_mmk.jsp").addParameter("tab", "true");
    }

    //ptPengambilanPtg
    public Resolution showForm2() {
        getContext().getRequest().setAttribute("ptPengambilanPtg", Boolean.TRUE);
        return new JSP("pengambilan/negerisembilan/pendudukansementara/ulasan_mmk.jsp").addParameter("tab", "true");
    }

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {

        for (int k = 1; k <= count5; k++) {
            senaraiSyorPentadbir[k] = new ArrayList<PermohonanKertasKandungan>();
        }
        count5 = 0;
        for (int k = 1; k <= count6; k++) {
            senaraiSyorPengarah[k] = new ArrayList<PermohonanKertasKandungan>();
        }
        count6 = 0;

        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = permohonanDAO.findById(idPermohonan);

        HakmilikPermohonan hp = new HakmilikPermohonan();

        peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        String[] tname = {"permohonan"};
        Object[] model = {permohonan};

        listPemohon = pemohonDAO.findByEqualCriterias(tname, model, null);
        hakmilikPermohonanList = hakmilikPermohonanDAO.findByEqualCriterias(tname, model, null);
//        for (Pemohon pemohon : listPemohon) {
//            listPengarah = pemohon.getPihak().getSenaraiPengarah();
//        }

        String pemohonPihakNama = " ";
        if (!(listPemohon.isEmpty())) {
            pemohon = listPemohon.get(0);
            pemohonPihakNama = pemohon.getPihak().getNama();
        }

        List<PermohonanPihak> listPenerima;
        listPenerima = permohonan.getSenaraiPihak();

        if (!(listPenerima.isEmpty())) {
            penerima = listPenerima.get(0);
        }

        if (!hakmilikPermohonanList.isEmpty()) {

            for (int w = 0; w < hakmilikPermohonanList.size(); w++) {
                hp = permohonan.getSenaraiHakmilik().get(w);

                if (hp.getHakmilik() != null) {
                    hakmilik = hakmilikDAO.findById(hp.getHakmilik().getIdHakmilik());
                    laporanTanah = laporanTanahDAO.findById(Long.MIN_VALUE);

                    String BandarPekanMukim = WordUtils.capitalizeFully(hakmilik.getBandarPekanMukim().getNama());
                    String NamaDaerah = WordUtils.capitalizeFully(hakmilik.getDaerah().getNama());

                    if (w == 0) {
                     //   lokasi = hakmilik.getLot().getNama().toUpperCase() + " " + hakmilik.getNoLot() + " " + hakmilik.getKodHakmilik().getKod().toUpperCase() + " " + hakmilik.getNoHakmilik()  + ", " + BandarPekanMukim + ", Daerah " + NamaDaerah + " ";
                         lokasi = hakmilik.getLot().getNama().toUpperCase() + " " + hakmilik.getNoLot() + " " + hakmilik.getKodHakmilik().getKod().toUpperCase() + " " + hakmilik.getNoHakmilik()  + " ";
                    }

                    if (w > 0) {
                        if (w <= ((hakmilikPermohonanList.size() + 2) - 4)) {
                          //  lokasi = lokasi + hakmilik.getLot().getNama().toUpperCase() + " " + hakmilik.getNoLot() + " " + hakmilik.getKodHakmilik().getKod().toUpperCase() + " " + hakmilik.getNoHakmilik() + ", " + BandarPekanMukim + ", Daerah " + NamaDaerah + " ";
                             lokasi = lokasi + hakmilik.getLot().getNama().toUpperCase() + " " + hakmilik.getNoLot() + " " + hakmilik.getKodHakmilik().getKod().toUpperCase() + " " + hakmilik.getNoHakmilik() + " ";
                        } else if (w == (hakmilikPermohonanList.size() - 1)) {
                            lokasi = lokasi + " dan " + hakmilik.getLot().getNama().toUpperCase() + " " + hakmilik.getNoLot() + " " + hakmilik.getKodHakmilik().getKod().toUpperCase() + " " + hakmilik.getNoHakmilik() + ",DI " + BandarPekanMukim + ", Daerah " + NamaDaerah + " ";
                        }
                    }

                }
            }
        }

        tujuan = "Kertas ini bertujuan untuk mendapat kelulusan Majlis Mesyuarat Kerajaan Negeri Sembilan Darul Khusus, terhadap permohonan " + permohonan.getKodUrusan().getNama() + " di " + lokasi;

        String gunaTanah = "";
        for (HakmilikPermohonan hakmilikPermohonan : hakmilikPermohonanList) {
            if (hakmilikPermohonan.getHakmilik().getKegunaanTanah() != null) {
                gunaTanah = permohonan.getSebab();
            }
        }


        String temp = "PERMOHONAN DARIPADA " + pemohonPihakNama + " UNTUK " + permohonan.getKodUrusan().getNama() + " BAGI "
                + lokasi + " UNTUK TUJUAN " + permohonan.getSebab();
        heading = temp.toUpperCase();

        if (idPermohonan != null) {

            permohonanKertas = pendudukanSementaraMMKNService.getPermohonanKertasByIdPermohonan(idPermohonan);

            if (permohonanKertas != null) {//
//                kmnoObj = pendudukanSementaraMMKNService.findbil1ByIdKertas(permohonanKertas.getIdKertas(), 10);
//                if(kmnoObj != null)
//                    kmno = kmnoObj.getKandungan().toLowerCase();
//                tarikhMesyuaratObj = pendudukanSementaraMMKNService.findbil1ByIdKertas(permohonanKertas.getIdKertas(), 11);
//                if(tarikhMesyuaratObj != null)
//                    tarikhMesyuarat = tarikhMesyuaratObj.getKandungan().toLowerCase();

                kmno = permohonanKertas.getTajuk();
                if (permohonanKertas.getTarikhSidang() != null) {
                    tarikhMesyuarat = dateFormat.format(permohonanKertas.getTarikhSidang());
                }

                headingObj = pendudukanSementaraMMKNService.findbil1ByIdKertas(permohonanKertas.getIdKertas(), 12);
                tujuanObj = pendudukanSementaraMMKNService.findbil1ByIdKertas(permohonanKertas.getIdKertas(), 1);
                if (tujuanObj != null) {
                    tujuan = tujuanObj.getKandungan();
                }
                senaraiKertasKandungan4 = pendudukanSementaraMMKNService.findByKertas(permohonanKertas.getIdKertas(), 4);
//                senaraiKertasKandungan5 = pendudukanSementaraMMKNService.findByKertas(permohonanKertas.getIdKertas(), 5);
                syorPentadbirObj = pendudukanSementaraMMKNService.findbil1ByIdKertas(permohonanKertas.getIdKertas(), 5);
                senaraiKertasKandungan6 = pendudukanSementaraMMKNService.findByKertas(permohonanKertas.getIdKertas(), 6);
                syorPengarahObj = pendudukanSementaraMMKNService.findbil1ByIdKertas(permohonanKertas.getIdKertas(), 7);
                if (syorPengarahObj != null) {
                    syorPengarah = syorPengarahObj.getKandungan();
                }
                if (syorPentadbirObj != null) {
                    syorPentadbir = syorPentadbirObj.getKandungan();
                }

//                List<PermohonanKertasKandungan> kandList5 = new ArrayList<PermohonanKertasKandungan>();
//                PermohonanKertasKandungan maxKertasKand5 = new PermohonanKertasKandungan();
//                kandList5 = pendudukanSementaraMMKNService.findByKertasDesc(permohonanKertas.getIdKertas(), 5);
//                if (kandList5 != null && !kandList5.isEmpty()) {
//                    maxKertasKand5 = kandList5.get(0);
//                    if (maxKertasKand5 != null) {
//                        String subtajuk = maxKertasKand5.getSubtajuk();
//                        String str = subtajuk.substring(2, 3);
//                        int tableCount = Integer.parseInt(str);
//                        count5 = tableCount;
//                        for (int k = 1; k <= tableCount; k++) {
//                            senaraiSyorPentadbir[k] = new ArrayList<PermohonanKertasKandungan>();
//                            String subtajuk1 = "5." + k;
//                            senaraiSyorPentadbir[k] = pendudukanSementaraMMKNService.findByKertasSubTajuk(permohonanKertas.getIdKertas(), 5, subtajuk1);
//                        }
//                    }
//                }//if

                List<PermohonanKertasKandungan> kandList6 = new ArrayList<PermohonanKertasKandungan>();
                PermohonanKertasKandungan maxKertasKand6 = new PermohonanKertasKandungan();
                kandList6 = pendudukanSementaraMMKNService.findByKertasDesc(permohonanKertas.getIdKertas(), 6);
                if (kandList6 != null && !kandList6.isEmpty()) {
                    maxKertasKand6 = kandList6.get(0);
                    if (maxKertasKand6 != null) {
                        String subtajuk = maxKertasKand6.getSubtajuk();
                        String str = subtajuk.substring(2, 3);
                        int tableCount = Integer.parseInt(str);
                        count6 = tableCount;
                        for (int k = 1; k <= tableCount; k++) {
                            senaraiSyorPengarah[k] = new ArrayList<PermohonanKertasKandungan>();
                            String subtajuk1 = "6." + k;
                            senaraiSyorPengarah[k] = pendudukanSementaraMMKNService.findByKertasSubTajuk(permohonanKertas.getIdKertas(), 6, subtajuk1);
                        }
                    }
                }//if

            }
        }
    }

    public Resolution simpan() throws ParseException {

        Pengguna pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = permohonanDAO.findById(idPermohonan);

        Session s = sessionProvider.get();
        Transaction tx = s.beginTransaction();

        InfoAudit infoAudit = new InfoAudit();

        if (permohonanKertas == null) {
            permohonanKertas = new PermohonanKertas();
            infoAudit.setDimasukOleh(pengguna);
            infoAudit.setTarikhMasuk(new java.util.Date());
        } else {
            infoAudit = permohonanKertas.getInfoAudit();
            infoAudit.setDiKemaskiniOleh(pengguna);
            infoAudit.setTarikhKemaskini(new java.util.Date());
        }

        KodDokumen doc = new KodDokumen();
        doc.setKod("MMKN");
        KodCawangan cawangan = permohonan.getCawangan();
        permohonanKertas.setInfoAudit(infoAudit);
        permohonanKertas.setPermohonan(permohonan);
        permohonanKertas.setCawangan(cawangan);
        permohonanKertas.setKodDokumen(doc);
        if (kmno == null) {
            kmno = "Tiada Data";
        }
        permohonanKertas.setTajuk(kmno);
//        tarikhMesyuarat = getContext().getRequest().getParameter("tarikhMesyuarat");
        if (tarikhMesyuarat != null) {
            Date tarikhSidang = dateFormat.parse(tarikhMesyuarat);
            permohonanKertas.setTarikhSidang(tarikhSidang);
        }
        pendudukanSementaraMMKNService.simpanPermohonanKertas(permohonanKertas);




        if (getContext().getRequest().getParameter("tujuan") != null) {
            infoAudit = new InfoAudit();
            if (tujuanObj == null) {
                tujuanObj = new PermohonanKertasKandungan();
                infoAudit.setDimasukOleh(pengguna);
                infoAudit.setTarikhMasuk(new java.util.Date());
            } else {
                infoAudit = tujuanObj.getInfoAudit();
                infoAudit.setDiKemaskiniOleh(pengguna);
                infoAudit.setTarikhKemaskini(new java.util.Date());
            }
            tujuanObj.setKertas(permohonanKertas);
            tujuanObj.setBil(1);
            kandungan = getContext().getRequest().getParameter("tujuan");
            tujuanObj.setKandungan(kandungan);
            cawangan = permohonan.getCawangan();
            tujuanObj.setCawangan(cawangan);
            tujuanObj.setInfoAudit(infoAudit);
            permohonanKertasKandunganDAO.saveOrUpdate(tujuanObj);
        }

        senaraiKertasKandungan4 = pendudukanSementaraMMKNService.findByKertas(permohonanKertas.getIdKertas(), 4);

        int kira4 = Integer.parseInt(getContext().getRequest().getParameter("rowCount4"));
        for (int k = 1; k <= kira4; k++) {
            infoAudit = new InfoAudit();
            if (senaraiKertasKandungan4.size() != 0 && k <= senaraiKertasKandungan4.size()) {
                Long id = senaraiKertasKandungan4.get(k - 1).getIdKandungan();
                if (id != null) {
                    permohonanKertasKandungan4 = pendudukanSementaraMMKNService.findkandunganByIdKandungan(id);
                    infoAudit = permohonanKertasKandungan4.getInfoAudit();
                    infoAudit.setDiKemaskiniOleh(pengguna);
                    infoAudit.setTarikhKemaskini(new java.util.Date());
                }
            } else {
                permohonanKertasKandungan4 = new PermohonanKertasKandungan();
                infoAudit.setDimasukOleh(pengguna);
                infoAudit.setTarikhMasuk(new java.util.Date());
            }
            permohonanKertasKandungan4.setKertas(permohonanKertas);
            permohonanKertasKandungan4.setBil(4);
            kandungan = getContext().getRequest().getParameter("kandungan4" + k);
            permohonanKertasKandungan4.setKandungan(kandungan);
            permohonanKertasKandungan4.setCawangan(cawangan);
            permohonanKertasKandungan4.setSubtajuk("4." + k);
            permohonanKertasKandungan4.setInfoAudit(infoAudit);
            permohonanKertasKandunganDAO.saveOrUpdate(permohonanKertasKandungan4);
        }

//        senaraiKertasKandungan5 = pendudukanSementaraMMKNService.findByKertas(permohonanKertas.getIdKertas(), 5);
//
//        int kira5 = Integer.parseInt(getContext().getRequest().getParameter("rowCount5"));
//        for (int k = 1; k <= kira5; k++) {
//            infoAudit = new InfoAudit();
//            if (senaraiKertasKandungan5.size() != 0 && k <= senaraiKertasKandungan5.size()) {
//                Long id = senaraiKertasKandungan5.get(k - 1).getIdKandungan();
//                if (id != null) {
//                    permohonanKertasKandungan5 = pendudukanSementaraMMKNService.findkandunganByIdKandungan(id);
//                    infoAudit = permohonanKertasKandungan5.getInfoAudit();
//                    infoAudit.setDiKemaskiniOleh(pengguna);
//                    infoAudit.setTarikhKemaskini(new java.util.Date());
//                }
//            } else {
//                permohonanKertasKandungan5 = new PermohonanKertasKandungan();
//                infoAudit.setDimasukOleh(pengguna);
//                infoAudit.setTarikhMasuk(new java.util.Date());
//            }
//            permohonanKertasKandungan5.setKertas(permohonanKertas);
//            permohonanKertasKandungan5.setBil(5);
//            kandungan = getContext().getRequest().getParameter("kandungan5" + k);
//            permohonanKertasKandungan5.setKandungan(kandungan);
//            permohonanKertasKandungan5.setCawangan(cawangan);
//            permohonanKertasKandungan5.setSubtajuk("5." + k);
//            permohonanKertasKandungan5.setInfoAudit(infoAudit);
//            permohonanKertasKandunganDAO.saveOrUpdate(permohonanKertasKandungan5);
//        }

        senaraiKertasKandungan6 = pendudukanSementaraMMKNService.findByKertas(permohonanKertas.getIdKertas(), 6);

        int kira6 = Integer.parseInt(getContext().getRequest().getParameter("rowCount6"));
        for (int k = 1; k <= kira6; k++) {
            infoAudit = new InfoAudit();
            if (senaraiKertasKandungan6.size() != 0 && k <= senaraiKertasKandungan6.size()) {
                Long id = senaraiKertasKandungan6.get(k - 1).getIdKandungan();
                if (id != null) {
                    permohonanKertasKandungan6 = pendudukanSementaraMMKNService.findkandunganByIdKandungan(id);
                    infoAudit = permohonanKertasKandungan6.getInfoAudit();
                    infoAudit.setDiKemaskiniOleh(pengguna);
                    infoAudit.setTarikhKemaskini(new java.util.Date());
                }
            } else {
                permohonanKertasKandungan6 = new PermohonanKertasKandungan();
                infoAudit.setDimasukOleh(pengguna);
                infoAudit.setTarikhMasuk(new java.util.Date());
            }
            permohonanKertasKandungan6.setKertas(permohonanKertas);
            permohonanKertasKandungan6.setBil(6);
            kandungan = getContext().getRequest().getParameter("kandungan6" + k);
            permohonanKertasKandungan6.setKandungan(kandungan);
            permohonanKertasKandungan6.setCawangan(cawangan);
            permohonanKertasKandungan6.setSubtajuk("6." + k);
            permohonanKertasKandungan6.setInfoAudit(infoAudit);
            permohonanKertasKandunganDAO.saveOrUpdate(permohonanKertasKandungan6);
        }

//        count5 = Integer.parseInt(getContext().getRequest().getParameter("count5"));
//        for (int i = 1; i <= count5; i++) {
//            String subtajuk = "5." + i;
//            senaraiKertasKandungan5 = pendudukanSementaraMMKNService.findByKertasSubTajuk(permohonanKertas.getIdKertas(), 5, subtajuk);
//            int rowCount5 = Integer.parseInt(getContext().getRequest().getParameter("count5" + i));
//            for (int k = 1; k <= rowCount5; k++) {
//                infoAudit = new InfoAudit();
//                if (senaraiKertasKandungan5 != null && senaraiKertasKandungan5.size() != 0 && k <= senaraiKertasKandungan5.size()) {
//                    Long id3 = senaraiKertasKandungan5.get(k - 1).getIdKandungan();
//                    if (id3 != null) {
//                        permohonanKertasKandungan5 = pendudukanSementaraMMKNService.findkandunganByIdKandungan(id3);
//                        infoAudit = permohonanKertasKandungan5.getInfoAudit();
//                        infoAudit.setDiKemaskiniOleh(pengguna);
//                        infoAudit.setTarikhKemaskini(new java.util.Date());
//                    }
//                } else {
//                    permohonanKertasKandungan5 = new PermohonanKertasKandungan();
//                    infoAudit.setDimasukOleh(pengguna);
//                    infoAudit.setTarikhMasuk(new java.util.Date());
//                }
//
//                permohonanKertasKandungan5.setKertas(permohonanKertas);
//                permohonanKertasKandungan5.setBil(5);
//                kandungan = getContext().getRequest().getParameter("syorPentadbir" + i + k);
//                if (kandungan == null) {
//                    permohonanKertasKandungan5.setKandungan("Tiada");
//                } else {
//                    permohonanKertasKandungan5.setKandungan(kandungan);
//                }
//                permohonanKertasKandungan5.setCawangan(cawangan);
//                permohonanKertasKandungan5.setSubtajuk("5." + i + "." + str[k - 1]);
//                permohonanKertasKandungan5.setInfoAudit(infoAudit);
//                permohonanKertasKandunganDAO.saveOrUpdate(permohonanKertasKandungan5);
//            }
//        }//if table count

        if (getContext().getRequest().getParameter("syorPentadbir") != null) {
            infoAudit = new InfoAudit();
            if (syorPentadbirObj == null) {
                syorPentadbirObj = new PermohonanKertasKandungan();
                infoAudit.setDimasukOleh(pengguna);
                infoAudit.setTarikhMasuk(new java.util.Date());
            } else {
                infoAudit = syorPentadbirObj.getInfoAudit();
                infoAudit.setDiKemaskiniOleh(pengguna);
                infoAudit.setTarikhKemaskini(new java.util.Date());
            }
            syorPentadbirObj.setKertas(permohonanKertas);
            syorPentadbirObj.setBil(5);
            kandungan = getContext().getRequest().getParameter("syorPentadbir");
            if (kandungan == null) {
                syorPentadbirObj.setKandungan("Tiada Data");
            } else {
                syorPentadbirObj.setKandungan(kandungan);
            }
//            syorPengarahObj.setKandungan(kandungan);
            cawangan = permohonan.getCawangan();
            syorPentadbirObj.setCawangan(cawangan);
            syorPentadbirObj.setInfoAudit(infoAudit);
            permohonanKertasKandunganDAO.saveOrUpdate(syorPentadbirObj);
        }

        if (getContext().getRequest().getParameter("syorPengarah") != null) {
            infoAudit = new InfoAudit();
            if (syorPengarahObj == null) {
                syorPengarahObj = new PermohonanKertasKandungan();
                infoAudit.setDimasukOleh(pengguna);
                infoAudit.setTarikhMasuk(new java.util.Date());
            } else {
                infoAudit = syorPengarahObj.getInfoAudit();
                infoAudit.setDiKemaskiniOleh(pengguna);
                infoAudit.setTarikhKemaskini(new java.util.Date());
            }
            syorPengarahObj.setKertas(permohonanKertas);
            syorPengarahObj.setBil(7);
            kandungan = getContext().getRequest().getParameter("syorPengarah");
            if (kandungan == null) {
                syorPengarahObj.setKandungan("Tiada Data");
            } else {
                syorPengarahObj.setKandungan(kandungan);
            }
//            syorPengarahObj.setKandungan(kandungan);
            cawangan = permohonan.getCawangan();
            syorPengarahObj.setCawangan(cawangan);
            syorPengarahObj.setInfoAudit(infoAudit);
            permohonanKertasKandunganDAO.saveOrUpdate(syorPengarahObj);
        }

//        count6 = Integer.parseInt(getContext().getRequest().getParameter("count6"));
//        for (int i = 1; i <= count6; i++) {
//            String subtajuk = "6." + i;
//            senaraiKertasKandungan6 = pendudukanSementaraMMKNService.findByKertasSubTajuk(permohonanKertas.getIdKertas(), 6, subtajuk);
//            int rowCount6 = Integer.parseInt(getContext().getRequest().getParameter("count6" + i));
//            for (int k = 1; k <= rowCount6; k++) {
//                if (senaraiKertasKandungan6 != null && senaraiKertasKandungan6.size() != 0 && k <= senaraiKertasKandungan6.size()) {
//                    Long id3 = senaraiKertasKandungan6.get(k - 1).getIdKandungan();
//                    if (id3 != null) {
//                        permohonanKertasKandungan6 = pendudukanSementaraMMKNService.findkandunganByIdKandungan(id3);
//                        infoAudit = permohonanKertasKandungan6.getInfoAudit();
//                        infoAudit.setDiKemaskiniOleh(pengguna);
//                        infoAudit.setTarikhKemaskini(new java.util.Date());
//                    }
//                } else {
//                    permohonanKertasKandungan6 = new PermohonanKertasKandungan();
//                    infoAudit.setDimasukOleh(pengguna);
//                    infoAudit.setTarikhMasuk(new java.util.Date());
//                }
//
//                permohonanKertasKandungan6.setKertas(permohonanKertas);
//                permohonanKertasKandungan6.setBil(6);
//                kandungan = getContext().getRequest().getParameter("syorPengarah" + i + k);
//                if (kandungan == null) {
//                    permohonanKertasKandungan6.setKandungan("Tiada");
//                } else {
//                    permohonanKertasKandungan6.setKandungan(kandungan);
//                }
//
//                permohonanKertasKandungan6.setCawangan(cawangan);
//                permohonanKertasKandungan6.setSubtajuk("6." + i + "." + str[k - 1]);
//                permohonanKertasKandungan6.setInfoAudit(infoAudit);
//                permohonanKertasKandunganDAO.saveOrUpdate(permohonanKertasKandungan6);
//            }
//        }//if table count

//        if (getContext().getRequest().getParameter("kmno") != null) {
//            infoAudit = new InfoAudit();
//            if (kmnoObj == null) {
//                kmnoObj = new PermohonanKertasKandungan();
//                infoAudit.setDimasukOleh(pengguna);
//                infoAudit.setTarikhMasuk(new java.util.Date());
//            }else {
//                infoAudit = kmnoObj.getInfoAudit();
//                infoAudit.setDiKemaskiniOleh(pengguna);
//                infoAudit.setTarikhKemaskini(new java.util.Date());
//            }
//            kmnoObj.setKertas(permohonanKertas);
//            kmnoObj.setBil(10);
//            kandungan = getContext().getRequest().getParameter("kmno");
//            kmnoObj.setKandungan(kandungan);
//            cawangan = permohonan.getCawangan();
//            kmnoObj.setCawangan(cawangan);
//            kmnoObj.setInfoAudit(infoAudit);
//            permohonanKertasKandunganDAO.saveOrUpdate(kmnoObj);
//        }

//        if (getContext().getRequest().getParameter("kmno") != null) {
//            infoAudit = new InfoAudit();
//            if (permohonanRujukanLuar == null) {
//                infoAudit.setDimasukOleh(pengguna);
//                infoAudit.setTarikhMasuk(new java.util.Date());
//            }else {
//                infoAudit = permohonanRujukanLuar.getInfoAudit();
//                infoAudit.setDiKemaskiniOleh(pengguna);
//                infoAudit.setTarikhKemaskini(new java.util.Date());
//            }
//            permohonanRujukanLuar.setNoSidang(kmno);
//            cawangan = permohonan.getCawangan();
//            permohonanRujukanLuar.setCawangan(cawangan);
//            permohonanRujukanLuar.setInfoAudit(infoAudit);
//            permohonanRujukanLuarDAO.saveOrUpdate(permohonanRujukanLuar);
//        }

//        if (getContext().getRequest().getParameter("tarikhMesyuarat") != null) {
//            infoAudit = new InfoAudit();
//            if (tarikhMesyuaratObj == null) {
//                tarikhMesyuaratObj = new PermohonanKertasKandungan();
//                infoAudit.setDimasukOleh(pengguna);
//                infoAudit.setTarikhMasuk(new java.util.Date());
//            }else {
//                infoAudit = tarikhMesyuaratObj.getInfoAudit();
//                infoAudit.setDiKemaskiniOleh(pengguna);
//                infoAudit.setTarikhKemaskini(new java.util.Date());
//            }
//            tarikhMesyuaratObj.setKertas(permohonanKertas);
//            tarikhMesyuaratObj.setBil(11);
//            kandungan = getContext().getRequest().getParameter("tarikhMesyuarat");
//            tarikhMesyuaratObj.setKandungan(kandungan);
//            cawangan = permohonan.getCawangan();
//            tarikhMesyuaratObj.setCawangan(cawangan);
//            tarikhMesyuaratObj.setInfoAudit(infoAudit);
//            permohonanKertasKandunganDAO.saveOrUpdate(tarikhMesyuaratObj);
//        }

        if (getContext().getRequest().getParameter("heading") != null) {
            infoAudit = new InfoAudit();
            if (headingObj == null) {
                headingObj = new PermohonanKertasKandungan();
                infoAudit.setDimasukOleh(pengguna);
                infoAudit.setTarikhMasuk(new java.util.Date());
            } else {
                infoAudit = headingObj.getInfoAudit();
                infoAudit.setDiKemaskiniOleh(pengguna);
                infoAudit.setTarikhKemaskini(new java.util.Date());
            }
            headingObj.setKertas(permohonanKertas);
            headingObj.setBil(12);
            kandungan = getContext().getRequest().getParameter("heading");
            headingObj.setKandungan(kandungan);
            cawangan = permohonan.getCawangan();
            headingObj.setCawangan(cawangan);
            headingObj.setInfoAudit(infoAudit);
            permohonanKertasKandunganDAO.saveOrUpdate(headingObj);
        }

        tx.commit();
        rehydrate();
        addSimpleMessage("Maklumat telah berjaya disimpan.");
        String ptg = getContext().getRequest().getParameter("ptg");
        if (ptg.equals("true")) {
            getContext().getRequest().setAttribute("ptg", Boolean.TRUE);
        }
        String ptPengambilanPtg = getContext().getRequest().getParameter("ptPengambilanPtg");
        if (ptPengambilanPtg.equals("true")) {
            getContext().getRequest().setAttribute("ptPengambilanPtg", Boolean.TRUE);
        }
        return new JSP("pengambilan/negerisembilan/pendudukansementara/ulasan_mmk.jsp").addParameter("tab", "true");


//        addSimpleMessage("Maklumat telah berjaya disimpan.");
//        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
//        return new RedirectResolution(UlasanMMKNSPendudukanSementaraActionBean.class, "locate");
    }

    public Resolution deleteSingle() {
        idKandungan = getContext().getRequest().getParameter("idKandungan");
        try {
            permohonanKertasKandungan1 = pendudukanSementaraMMKNService.findkandunganByIdKandungan(Long.parseLong(idKandungan));
        } catch (Exception e) {
        }
        if (permohonanKertasKandungan1 != null) {
            pendudukanSementaraMMKNService.deleteKertasKandungan(permohonanKertasKandungan1);
        }

        rehydrate();
        addSimpleMessage("Maklumat telah berjaya dihapus.");
        String ptg = getContext().getRequest().getParameter("ptg");
        if (ptg.equals("true")) {
            getContext().getRequest().setAttribute("ptg", Boolean.TRUE);
        }
        String ptPengambilanPtg = getContext().getRequest().getParameter("ptPengambilanPtg");
        if (ptPengambilanPtg.equals("true")) {
            getContext().getRequest().setAttribute("ptPengambilanPtg", Boolean.TRUE);
        }
        return new JSP("pengambilan/negerisembilan/pendudukansementara/ulasan_mmk.jsp").addParameter("tab", "true");
    }

    public Resolution deleteTable() {

        int bil = Integer.parseInt(getContext().getRequest().getParameter("bil"));

        List<PermohonanKertasKandungan> kandList = pendudukanSementaraMMKNService.findByKertasDesc(permohonanKertas.getIdKertas(), bil);

        if (kandList != null && !kandList.isEmpty()) {
            PermohonanKertasKandungan maxKertasKand = kandList.get(0);
            if (maxKertasKand != null) {
                String subtajuk = maxKertasKand.getSubtajuk();
                String str = subtajuk.substring(2, 3);
                String subtajuk1 = bil + "." + str;
                List<PermohonanKertasKandungan> kandList1 = pendudukanSementaraMMKNService.findByKertasSubTajuk(permohonanKertas.getIdKertas(), bil, subtajuk1);
                for (PermohonanKertasKandungan kand : kandList1) {
                    pendudukanSementaraMMKNService.deleteKertasKandungan(kand);
                }
            }
        }

        rehydrate();
        addSimpleMessage("Maklumat telah berjaya dihapus.");
        String ptg = getContext().getRequest().getParameter("ptg");
        if (ptg.equals("true")) {
            getContext().getRequest().setAttribute("ptg", Boolean.TRUE);
        }
        String ptPengambilanPtg = getContext().getRequest().getParameter("ptPengambilanPtg");
        if (ptPengambilanPtg.equals("true")) {
            getContext().getRequest().setAttribute("ptPengambilanPtg", Boolean.TRUE);
        }
        return new JSP("pengambilan/negerisembilan/pendudukansementara/ulasan_mmk.jsp").addParameter("tab", "true");

    }

    public Hakmilik getHakmilik() {
        return hakmilik;
    }

    public void setHakmilik(Hakmilik hakmilik) {
        this.hakmilik = hakmilik;
    }

    public Pemohon getPemohon() {
        return pemohon;
    }

    public void setPemohon(Pemohon pemohon) {
        this.pemohon = pemohon;
    }

    public String getTarikhDaftar() {
        return tarikhDaftar;
    }

    public void setTarikhDaftar(String tarikhDaftar) {
        this.tarikhDaftar = tarikhDaftar;
    }

    public String getSyorPengarah() {
        return syorPengarah;
    }

    public void setSyorPengarah(String syorPengarah) {
        this.syorPengarah = syorPengarah;
    }

    public String getSyorPtg() {
        return syorPtg;
    }

    public void setSyorPtg(String syorPtg) {
        this.syorPtg = syorPtg;
    }

    public String getHuraianPentadbir() {
        return huraianPentadbir;
    }

    public void setHuraianPentadbir(String huraianPentadbir) {
        this.huraianPentadbir = huraianPentadbir;
    }

    public String getSyorPentadbir() {
        return syorPentadbir;
    }

    public void setSyorPentadbir(String syorPentadbir) {
        this.syorPentadbir = syorPentadbir;
    }

    public String getTarikhMesyuarat() {
        return tarikhMesyuarat;
    }

    public void setTarikhMesyuarat(String tarikhMesyuarat) {
        this.tarikhMesyuarat = tarikhMesyuarat;
    }

    public HakmilikPihakBerkepentingan getHakmilikPihakBerkepentingan() {
        return hakmilikPihakBerkepentingan;
    }

    public void setHakmilikPihakBerkepentingan(HakmilikPihakBerkepentingan hakmilikPihakBerkepentingan) {
        this.hakmilikPihakBerkepentingan = hakmilikPihakBerkepentingan;
    }

    public PermohonanPihak getPenerima() {
        return penerima;
    }

    public void setPenerima(PermohonanPihak penerima) {
        this.penerima = penerima;
    }

    public String getStageId() {
        return stageId;
    }

    public void setStageId(String stageId) {
        this.stageId = stageId;
    }

    public String getTarikhPermohonan() {
        return tarikhPermohonan;
    }

    public void setTarikhPermohonan(String tarikhPermohonan) {
        this.tarikhPermohonan = tarikhPermohonan;
    }

    public String getKedudukanTnh() {
        return kedudukanTnh;
    }

    public void setKedudukanTnh(String kedudukanTnh) {
        this.kedudukanTnh = kedudukanTnh;
    }

    public String getKeadaanTnh() {
        return keadaanTnh;
    }

    public void setKeadaanTnh(String keadaanTnh) {
        this.keadaanTnh = keadaanTnh;
    }

    public String getJenisTanaman() {
        return jenisTanaman;
    }

    public void setJenisTanaman(String jenisTanaman) {
        this.jenisTanaman = jenisTanaman;
    }

    public String getBilBangunan() {
        return bilBangunan;
    }

    public void setBilBangunan(String bilBangunan) {
        this.bilBangunan = bilBangunan;
    }

    public Pihak getPihak() {
        return pihak;
    }

    public void setPihak(Pihak pihak) {
        this.pihak = pihak;
    }

    public List<PihakPengarah> getListPengarah() {
        return listPengarah;
    }

    public void setPh(List<PihakPengarah> listPengarah) {
        this.listPengarah = listPengarah;
    }

    public List<Pemohon> getListPemohon() {
        return listPemohon;
    }

    public void setListPmohon(List<Pemohon> listPemohon) {
        this.listPemohon = listPemohon;
    }

    public PermohonanKertasKandungan getKertasK() {
        return kertasK;
    }

    public void setKertasK(PermohonanKertasKandungan kertasK) {
        this.kertasK = kertasK;
    }

    public String getTujuan() {
        return tujuan;
    }

    public void setTujuan(String tujuan) {
        this.tujuan = tujuan;
    }

    public String getPembangunanDicadang() {
        return pembangunanDicadang;
    }

    public void setPembangunanDicadang(String pembangunanDicadang) {
        this.pembangunanDicadang = pembangunanDicadang;
    }

    public LaporanTanah getLaporanTanah() {
        return laporanTanah;
    }

    public void setLaporanTanah(LaporanTanah laporanTanah) {
        this.laporanTanah = laporanTanah;
    }

    public List<HakmilikPermohonan> getHakmilikPermohonanList() {
        return hakmilikPermohonanList;
    }

    public void setHakmilikPermohonanList(List<HakmilikPermohonan> hakmilikPermohonanList) {
        this.hakmilikPermohonanList = hakmilikPermohonanList;
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

    public String getLokasi() {
        return lokasi;
    }

    public void setLokasi(String lokasi) {
        this.lokasi = lokasi;
    }

    public String getKmno() {
        return kmno;
    }

    public void setKmno(String kmno) {
        this.kmno = kmno;
    }

    public int getCount6() {
        return count6;
    }

    public void setCount6(int count6) {
        this.count6 = count6;
    }

    public List[] getSenaraiSyorPengarah() {
        return senaraiSyorPengarah;
    }

    public void setSenaraiSyorPengarah(List[] senaraiSyorPengarah) {
        this.senaraiSyorPengarah = senaraiSyorPengarah;
    }

    public int getCount5() {
        return count5;
    }

    public void setCount5(int count5) {
        this.count5 = count5;
    }

    public List[] getSenaraiSyorPentadbir() {
        return senaraiSyorPentadbir;
    }

    public void setSenaraiSyorPentadbir(List[] senaraiSyorPentadbir) {
        this.senaraiSyorPentadbir = senaraiSyorPentadbir;
    }

    public PermohonanKertasKandungan getSyorPengarahObj() {
        return syorPengarahObj;
    }

    public void setSyorPengarahObj(PermohonanKertasKandungan syorPengarahObj) {
        this.syorPengarahObj = syorPengarahObj;
    }

    public String getIdKandungan() {
        return idKandungan;
    }

    public void setIdKandungan(String idKandungan) {
        this.idKandungan = idKandungan;
    }

    public String getKandungan() {
        return kandungan;
    }

    public void setKandungan(String kandungan) {
        this.kandungan = kandungan;
    }

    public String getKmnoObj() {
        return kmnoObj;
    }

    public void setKmnoObj(String kmnoObj) {
        this.kmnoObj = kmnoObj;
    }

    public PermohonanKertas getPermohonanKertas() {
        return permohonanKertas;
    }

    public void setPermohonanKertas(PermohonanKertas permohonanKertas) {
        this.permohonanKertas = permohonanKertas;
    }

    public PermohonanKertasKandungan getPermohonanKertasKandungan1() {
        return permohonanKertasKandungan1;
    }

    public void setPermohonanKertasKandungan1(PermohonanKertasKandungan permohonanKertasKandungan1) {
        this.permohonanKertasKandungan1 = permohonanKertasKandungan1;
    }

    public PermohonanKertasKandungan getPermohonanKertasKandungan5() {
        return permohonanKertasKandungan5;
    }

    public void setPermohonanKertasKandungan5(PermohonanKertasKandungan permohonanKertasKandungan5) {
        this.permohonanKertasKandungan5 = permohonanKertasKandungan5;
    }

    public PermohonanKertasKandungan getPermohonanKertasKandungan4() {
        return permohonanKertasKandungan4;
    }

    public void setPermohonanKertasKandungan4(PermohonanKertasKandungan permohonanKertasKandungan4) {
        this.permohonanKertasKandungan4 = permohonanKertasKandungan4;
    }

    public PermohonanKertasKandungan getPermohonanKertasKandungan6() {
        return permohonanKertasKandungan6;
    }

    public void setPermohonanKertasKandungan6(PermohonanKertasKandungan permohonanKertasKandungan6) {
        this.permohonanKertasKandungan6 = permohonanKertasKandungan6;
    }

    public List<PermohonanKertasKandungan> getSenaraiKertasKandungan5() {
        return senaraiKertasKandungan5;
    }

    public void setSenaraiKertasKandungan5(List<PermohonanKertasKandungan> senaraiKertasKandungan5) {
        this.senaraiKertasKandungan5 = senaraiKertasKandungan5;
    }

    public List<PermohonanKertasKandungan> getSenaraiKertasKandungan4() {
        return senaraiKertasKandungan4;
    }

    public void setSenaraiKertasKandungan4(List<PermohonanKertasKandungan> senaraiKertasKandungan4) {
        this.senaraiKertasKandungan4 = senaraiKertasKandungan4;
    }

    public List<PermohonanKertasKandungan> getSenaraiKertasKandungan6() {
        return senaraiKertasKandungan6;
    }

    public void setSenaraiKertasKandungan6(List<PermohonanKertasKandungan> senaraiKertasKandungan6) {
        this.senaraiKertasKandungan6 = senaraiKertasKandungan6;
    }

    public String[] getStr() {
        return str;
    }

    public void setStr(String[] str) {
        this.str = str;
    }

    public PermohonanKertasKandungan getTarikhMesyuaratObj() {
        return tarikhMesyuaratObj;
    }

    public void setTarikhMesyuaratObj(PermohonanKertasKandungan tarikhMesyuaratObj) {
        this.tarikhMesyuaratObj = tarikhMesyuaratObj;
    }

    public PermohonanKertasKandungan getTujuanObj() {
        return tujuanObj;
    }

    public void setTujuanObj(PermohonanKertasKandungan tujuanObj) {
        this.tujuanObj = tujuanObj;
    }

    public String getHeading() {
        return heading;
    }

    public void setHeading(String heading) {
        this.heading = heading;
    }

    public PermohonanKertasKandungan getHeadingObj() {
        return headingObj;
    }

    public void setHeadingObj(PermohonanKertasKandungan headingObj) {
        this.headingObj = headingObj;
    }

    public PermohonanRujukanLuar getPermohonanRujukanLuar() {
        return permohonanRujukanLuar;
    }

    public void setPermohonanRujukanLuar(PermohonanRujukanLuar permohonanRujukanLuar) {
        this.permohonanRujukanLuar = permohonanRujukanLuar;
    }

    public String getHuraianPengarah() {
        return huraianPengarah;
    }

    public void setHuraianPengarah(String huraianPengarah) {
        this.huraianPengarah = huraianPengarah;
    }

    public PermohonanKertasKandungan getSyorPentadbirObj() {
        return syorPentadbirObj;
    }

    public void setSyorPentadbirObj(PermohonanKertasKandungan syorPentadbirObj) {
        this.syorPentadbirObj = syorPentadbirObj;
    }
}
