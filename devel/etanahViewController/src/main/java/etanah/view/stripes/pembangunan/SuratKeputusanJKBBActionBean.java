package etanah.view.stripes.pembangunan;

/**
 *
 * @author NageswaraRao
 */
import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.dao.PermohonanKertasDAO;
import etanah.dao.PermohonanKertasKandunganDAO;
import etanah.dao.HakmilikDAO;
import etanah.dao.KodDokumenDAO;
import etanah.dao.PemohonDAO;
import etanah.dao.PermohonanDAO;
import etanah.dao.LaporanTanahDAO;
import etanah.model.PermohonanKertas;
import etanah.model.PermohonanKertasKandungan;
import etanah.model.Hakmilik;
import etanah.model.HakmilikPermohonan;
import etanah.model.HakmilikPihakBerkepentingan;
import etanah.model.InfoAudit;
import etanah.model.KodDokumen;
import etanah.model.Pemohon;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.model.PermohonanPihak;
import etanah.model.Pihak;
import etanah.model.PihakPengarah;
import etanah.model.LaporanTanah;
import etanah.model.PermohonanKertas;
import etanah.service.ConsentPtdService;
import etanah.service.PembangunanService;
import etanah.view.etanahActionBeanContext;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;
import etanah.service.daftar.PembetulanService;
import net.sourceforge.stripes.action.RedirectResolution;
import etanah.model.ambil.HakmilikPerbicaraan;
import etanah.service.BPelService;
import etanah.service.common.HakmilikPermohonanService;
import java.util.StringTokenizer;
import org.apache.log4j.Logger;
import org.apache.commons.lang.StringUtils;
import oracle.bpel.services.workflow.task.model.Task;

@UrlBinding("/pembangunan/melaka/surat_keputusan_JKBB_PPTONLY")
public class SuratKeputusanJKBBActionBean extends AbleActionBean {

    private static final Logger LOG = Logger.getLogger(KeputusanMesyuaratJKBBActionBean.class);
    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    HakmilikDAO hakmilikDAO;
    @Inject
    PemohonDAO pemohonDAO;
    @Inject
    ConsentPtdService conService;
    @Inject
    PembangunanService devService;
    @Inject
    LaporanTanahDAO laporanTanahDAO;
    @Inject
    PermohonanKertasDAO permohonanKertasDAO;
    @Inject
    PermohonanKertasKandunganDAO permohonanKertasKandunganDAO;
    @Inject
    KodDokumenDAO kodDokumenDAO;
    @Inject
    PembangunanUtility pu;
    private HakmilikPerbicaraan hakmilikPerbicaraan;
    private Permohonan permohonan;
    private Hakmilik hakmilik;
    private Pemohon pemohon;
    private PermohonanPihak penerima;
    private HakmilikPihakBerkepentingan hakmilikPihakBerkepentingan;
    private PermohonanKertasKandungan kertasK;
    private LaporanTanah laporanTanah;
    private String stageId;
    private Pengguna peng;
    String tarikhDaftar;
    String tarikhMesyuarat;
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    private KodDokumen kd;
    private List<PermohonanKertasKandungan> senaraiKandungan;
    private int rowCount;
    private List<PihakPengarah> listPengarah;
    private Pihak pihak;
    private List<Pemohon> listPemohon;
    private List<HakmilikPermohonan> hakmilikPermohonanList;
    private boolean btn = true;
    private HakmilikPermohonan hakmilikPermohonan;
    private String tajuk;
    private PermohonanKertas pk;
    private String nama;
    private String nama1;   
    private String namaFOrTajuk;
    private List<Pihak> uniquePemohonList2;

    @DefaultHandler
    public Resolution showForm() {
        LOG.info("------------showForm-------------");
        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        return new JSP("pembangunan/melaka/surat_keputusan_JKBB.jsp").addParameter("tab", "true");
    }

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {
        LOG.info("------------rehydrate-------------");
        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = permohonanDAO.findById(idPermohonan);
        peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        String lokasi = " ";
        boolean flag = true;
        if (!(permohonan.getSenaraiKertas().isEmpty())) {
            for (int i = 0; i < permohonan.getSenaraiKertas().size(); i++) {
                PermohonanKertas kertasP = new PermohonanKertas();
                kertasP = permohonan.getSenaraiKertas().get(i);
                if (kertasP.getKodDokumen().getKod().equals("SSBMS")) {

                    senaraiKandungan = new ArrayList<PermohonanKertasKandungan>();
                    senaraiKandungan = devService.findSenaraiKertasKandunganByIdKertas(kertasP.getIdKertas());
                    rowCount = senaraiKandungan.size();
                    LOG.info("------------rehydrate------SSBMS----rowCount---:" + rowCount);
                    flag = false;
                }
            }
        }

        if ((permohonan.getSenaraiKertas().isEmpty()) || flag) {

            permohonan = permohonanDAO.findById(idPermohonan);
            HakmilikPermohonan hp = new HakmilikPermohonan();
            hp = permohonan.getSenaraiHakmilik().get(0);
            hakmilik = hakmilikDAO.findById(hp.getHakmilik().getIdHakmilik());

            /* Get pemohonList based on IdPermohonan  */
            listPemohon = new ArrayList<Pemohon>();
            listPemohon = devService.findPemohonByIdPermohonan(idPermohonan);

            if (idPermohonan != null) {
                Permohonan p = permohonanDAO.findById(idPermohonan);
                hakmilikPermohonanList = p.getSenaraiHakmilik();
            }

            for (int w = 0; w < hakmilikPermohonanList.size(); w++) {
                hp = permohonan.getSenaraiHakmilik().get(w);
                hakmilik = hakmilikDAO.findById(hp.getHakmilik().getIdHakmilik());
                StringBuffer kodBPM = new StringBuffer();
                StringBuffer daerah = new StringBuffer();

                // make first letter Caps in each word
                if (hakmilik.getBandarPekanMukim() != null) {
                    String str1 = hakmilik.getBandarPekanMukim().getNama();
                    StringTokenizer st1 = new StringTokenizer(str1);
                    while (st1.hasMoreTokens()) {
                        String t1 = st1.nextToken();
                        t1 = Character.toUpperCase(t1.charAt(0)) + t1.toLowerCase().substring(1);
                        kodBPM.append(t1 + " ");
                    }
                }
                // make first letter Caps in each word
                if (hakmilik.getDaerah() != null) {
                    String str1 = hakmilik.getDaerah().getNama();
                    StringTokenizer st1 = new StringTokenizer(str1);
                    while (st1.hasMoreTokens()) {
                        String t1 = st1.nextToken();
                        t1 = Character.toUpperCase(t1.charAt(0)) + t1.toLowerCase().substring(1);
                        daerah.append(t1 + " ");
                    }
                }

                String noHakmilik = hakmilik.getNoHakmilik().replaceAll("^0*", "");
                String noLot = hakmilik.getNoLot().replaceAll("^0*", "");

                if (w == 0) {
                    lokasi = hakmilik.getKodHakmilik().getKod() + " " + noHakmilik + " " + hakmilik.getLot().getNama() + " " + noLot + " seluas " + hakmilik.getLuas() + " " + hakmilik.getKodUnitLuas().getNama() + ", " + kodBPM + ", Daerah " + daerah;
                }

                if (w > 0) {
                    if (w <= ((hakmilikPermohonanList.size() + 2) - 4)) {
                        lokasi = lokasi + ", " + hakmilik.getKodHakmilik().getKod() + " " + noHakmilik + " " + hakmilik.getLot().getNama() + " " + noLot + " seluas " + hakmilik.getLuas() + " " + hakmilik.getKodUnitLuas().getNama() + ", " + kodBPM + ", Daerah " + daerah + ", ";
                    } else if (w == (hakmilikPermohonanList.size() - 1)) {
                        lokasi = lokasi + " dan " + hakmilik.getKodHakmilik().getKod() + " " + noHakmilik + " " + hakmilik.getLot().getNama() + " " + noLot + " seluas " + hakmilik.getLuas() + " " + hakmilik.getKodUnitLuas().getNama() + ", " + kodBPM + ", Daerah " + daerah;
                    }
                }
            }


            nama = " ";
            for (int j = 0; j < listPemohon.size(); j++) {
                Pemohon pm = new Pemohon();
                pm = listPemohon.get(j);

                if (j == 0) {
                    nama = pm.getPihak().getNama();
                }
                if (j > 0) {
                    if (j <= ((listPemohon.size() + 2) - 4)) {
                        nama = nama + ", " + pm.getPihak().getNama() + ", ";
                    } else if (j == (listPemohon.size() - 1)) {
                        nama = nama + " dan " + pm.getPihak().getNama();
                    }
                }
            }
            nama = pu.convertStringtoInitCap(nama);

//            String tajuk = permohonan.getKodUrusan().getNama() + " Oleh " + nama + " Di Atas Hakmilik " + lokasi + ".";
//            senaraiKandungan = new ArrayList<PermohonanKertasKandungan>();
//            PermohonanKertasKandungan kertasK2 = new PermohonanKertasKandungan();
//            kertasK2.setKandungan(tajuk);
//            senaraiKandungan.add(kertasK2);
        }
//        rowCount = senaraiKandungan.size();

        //tajuk
        uniquePemohonList2 = devService.findUniquePemohonByIdPermohonan2(idPermohonan);
        for (int j = 0; j < uniquePemohonList2.size(); j++) {
            Pihak pm = new Pihak();
            pm = uniquePemohonList2.get(j);

            if (j == 0) {
                nama1 = pm.getNama();
            }
        }
            if (uniquePemohonList2.size() > 0) {
                int size = uniquePemohonList2.size() - 1;
                LOG.info("---Saiz---" + size);
                
                nama1 = nama1 + " dan " + size + " yang lain";
        }

        namaFOrTajuk = pu.convertStringtoInitCap(nama1);
        pk = devService.findPermohonanKertasByIdMohonAndKodDokumen(idPermohonan, "SSBMS");
        if (pk != null) {
            if (pk.getTajuk() != null) {
                tajuk = pk.getTajuk();
            } else {
                tajuk = permohonan.getSebab() + " oleh " + namaFOrTajuk;
            }
        } else {
            tajuk = permohonan.getSebab() + " oleh " + namaFOrTajuk;
        }
    }

    public Resolution simpan() {

        LOG.info("------------simpan-------------");
        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = permohonanDAO.findById(idPermohonan);

        Pengguna pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        InfoAudit infoAudit = new InfoAudit();
        kd = kodDokumenDAO.findById("SSBMS");

        PermohonanKertas permohonanKertas = null;
        senaraiKandungan = new ArrayList<PermohonanKertasKandungan>();

        if (!(permohonan.getSenaraiKertas().isEmpty())) {
            for (int i = 0; i < permohonan.getSenaraiKertas().size(); i++) {
                PermohonanKertas kertasP = new PermohonanKertas();
                kertasP = permohonan.getSenaraiKertas().get(i);
                if (kertasP.getKodDokumen().getKod().equals("SSBMS")) {
                    permohonanKertas = new PermohonanKertas();
                    permohonanKertas = permohonan.getSenaraiKertas().get(i);
                }
            }
        }

        if (permohonanKertas != null) {
            PermohonanKertas kertasP = new PermohonanKertas();
            kertasP = permohonanKertasDAO.findById(permohonanKertas.getIdKertas());
            senaraiKandungan = devService.findSenaraiKertasKandunganByIdKertas(permohonanKertas.getIdKertas());
            infoAudit = permohonanKertas.getInfoAudit();
            infoAudit.setDiKemaskiniOleh(pengguna);
            infoAudit.setTarikhKemaskini(new java.util.Date());
            kertasP.setInfoAudit(infoAudit);
            kertasP.setTajuk(tajuk);
            devService.simpanPermohonanKertas(kertasP);
        } else {
            permohonanKertas = new PermohonanKertas();
            infoAudit.setDimasukOleh(pengguna);
            infoAudit.setTarikhMasuk(new java.util.Date());
            permohonanKertas.setInfoAudit(infoAudit);
            permohonanKertas.setCawangan(pengguna.getKodCawangan());
            permohonanKertas.setPermohonan(permohonan);
            permohonanKertas.setTajuk(tajuk);
            permohonanKertas.setKodDokumen(kd);
            devService.simpanPermohonanKertas(permohonanKertas);
        }


        int kira = Integer.parseInt(getContext().getRequest().getParameter("rowCount"));
        for (int i = 1; i <= kira; i++) {
            InfoAudit iaP = new InfoAudit();
            kertasK = new PermohonanKertasKandungan();
            if (senaraiKandungan.size() != 0 && i <= senaraiKandungan.size()) {
                Long id = senaraiKandungan.get(i - 1).getIdKandungan();
                if (id != null) {
                    kertasK = permohonanKertasKandunganDAO.findById(id);
                    iaP = kertasK.getInfoAudit();
                    iaP.setTarikhKemaskini(new java.util.Date());
                    iaP.setDiKemaskiniOleh(pengguna);
                }
            } else {
                iaP.setTarikhMasuk(new java.util.Date());
                iaP.setDimasukOleh(pengguna);
            }
            kertasK.setKertas(permohonanKertas);
            kertasK.setBil(i);
            String kandungan = getContext().getRequest().getParameter("penyediaan" + i);
            if (kandungan == null || kandungan.equals("")) {
                kandungan = permohonan.getSebab();
                //kandungan = "TIADA DATA.";
            }
            kertasK.setKandungan(kandungan);
            kertasK.setCawangan(permohonan.getCawangan());
            kertasK.setInfoAudit(iaP);
            devService.simpanPermohonanKertasKandungan(kertasK);
        }
        senaraiKandungan = new ArrayList<PermohonanKertasKandungan>();
        senaraiKandungan = devService.findSenaraiKertasKandunganByIdKertas(permohonanKertas.getIdKertas());
        rowCount = senaraiKandungan.size();

        addSimpleMessage("Maklumat telah berjaya disimpan.");
        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        return new ForwardResolution("/WEB-INF/jsp/pembangunan/melaka/surat_keputusan_JKBB.jsp").addParameter("tab", "true");
    }

    public Resolution deleteSingle() {
        Pengguna pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        String idKandungan = getContext().getRequest().getParameter("idKandungan");
        PermohonanKertasKandungan permohonanKertasKandungan1 = new PermohonanKertasKandungan();
        try {
            permohonanKertasKandungan1 = permohonanKertasKandunganDAO.findById(Long.parseLong(idKandungan));
        } catch (Exception e) {
            LOG.debug("Hapus empty record");
            e.printStackTrace();
        }
        if (permohonanKertasKandungan1 != null) {
            devService.deleteKertasKandungan(permohonanKertasKandungan1);
            LOG.debug("Record deleted Successfully...");
        }
        addSimpleMessage("Maklumat telah berjaya dihapus.");
//        rehydrate();

        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
//        return new ForwardResolution("/WEB-INF/jsp/pembangunan/melaka/surat_keputusan_JKBB.jsp").addParameter("tab", "true");


        return new RedirectResolution(SuratKeputusanJKBBActionBean.class, "locate");
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

    public boolean isBtn() {
        return btn;
    }

    public void setBtn(boolean btn) {
        this.btn = btn;
    }

    public Pengguna getPeng() {
        return peng;
    }

    public void setPeng(Pengguna peng) {
        this.peng = peng;
    }

    // newly added code
    public HakmilikPerbicaraan getHakmilikPerbicaraan() {
        return hakmilikPerbicaraan;
    }

    public void setHakmilikPerbicaraan(HakmilikPerbicaraan hakmilikPerbicaraan) {
        this.hakmilikPerbicaraan = hakmilikPerbicaraan;
    }

    public HakmilikPermohonan getHakmilikPermohonan() {
        return hakmilikPermohonan;
    }

    public void setHakmilikPermohonan(HakmilikPermohonan hakmilikPermohonan) {
        this.hakmilikPermohonan = hakmilikPermohonan;
    }

    public int getRowCount() {
        return rowCount;
    }

    public void setRowCount(int rowCount) {
        this.rowCount = rowCount;
    }

    public List<PermohonanKertasKandungan> getSenaraiKandungan() {
        return senaraiKandungan;
    }

    public void setSenaraiKandungan(List<PermohonanKertasKandungan> senaraiKandungan) {
        this.senaraiKandungan = senaraiKandungan;
    }

    public String getTajuk() {
        return tajuk;
    }

    public void setTajuk(String tajuk) {
        this.tajuk = tajuk;
    }

    public PermohonanKertas getPk() {
        return pk;
    }

    public void setPk(PermohonanKertas pk) {
        this.pk = pk;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getNamaFOrTajuk() {
        return namaFOrTajuk;
    }

    public void setNamaFOrTajuk(String namaFOrTajuk) {
        this.namaFOrTajuk = namaFOrTajuk;
    }

    public List<Pihak> getUniquePemohonList2() {
        return uniquePemohonList2;
    }

    public void setUniquePemohonList2(List<Pihak> uniquePemohonList2) {
        this.uniquePemohonList2 = uniquePemohonList2;
    }

    public String getNama1() {
        return nama1;
    }

    public void setNama1(String nama1) {
        this.nama1 = nama1;
    }
    
    
}
