/**
 *
 * @modify by Siti Fariza Hanim (20 May 2010)
 */
package etanah.view.stripes.pelupusan;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import java.math.BigDecimal;
import etanah.dao.HakmilikDAO;
import etanah.dao.HakmilikPihakBerkepentinganDAO;
import etanah.dao.KodBangsaDAO;
import etanah.dao.PihakPengarahDAO;
import etanah.dao.KodNegeriDAO;
import etanah.dao.PemohonDAO;
import etanah.dao.PihakCawanganDAO;
import etanah.dao.PihakDAO;
import etanah.dao.PemohonHubunganDAO;
import etanah.dao.PermohonanDAO;
import etanah.model.Hakmilik;
import etanah.model.HakmilikPihakBerkepentingan;
import etanah.model.InfoAudit;
import etanah.model.KodBangsa;
import etanah.model.KodCawangan;
import etanah.model.Pemohon;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.model.PermohonanPihak;
import etanah.model.Pihak;
import etanah.model.PihakCawangan;
import etanah.service.common.PemohonService;
import etanah.service.common.PermohonanService;
import etanah.service.common.PihakService;
import etanah.view.etanahActionBeanContext;

import java.util.List;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.RedirectResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.StreamingResolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;
import org.apache.log4j.Logger;

import etanah.model.KodNegeri;
import etanah.model.PemohonHubungan;
import etanah.model.PihakPengarah;
import etanah.service.PelupusanService;
import etanah.service.common.CawanganService;
import etanah.view.ListUtil;
import java.util.ArrayList;
import org.hibernate.Session;
import org.hibernate.Transaction;
import java.text.SimpleDateFormat;
import java.text.ParseException;


@UrlBinding("/pelupusan/maklumat_pemohon")
public class MaklumatPemohonActionBean extends AbleActionBean {

    @Inject
    KodNegeriDAO kodNegeriDAO;
    @Inject
    KodBangsaDAO kodBangsaDAO;
    @Inject
    PihakService pihakService;
    @Inject
    PermohonanService permohonanService;
    @Inject
    PemohonService pemohonService;
    @Inject
    CawanganService cawanganService;
    @Inject
    PihakDAO pihakDAO;
    @Inject
    PihakPengarahDAO pihakPengarahDAO;
    @Inject
    PihakCawanganDAO pihakCawanganDAO;
    @Inject
    PelupusanService pelupusanService;
    @Inject
    PemohonHubunganDAO pemohonHubunganDAO;
    @Inject
    PemohonDAO pemohonDAO;
    @Inject
    HakmilikDAO hakmilikDAO;
    @Inject
    HakmilikPihakBerkepentinganDAO hakmilikPihakBerkepentinganDAO;
    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;
    @Inject
    ListUtil listUtil;
    @Inject
    PermohonanDAO permohonanDAO ;
    private String warnaKP;
    private String wargaNegara;
    private Pihak pihak;
    private String kodJantina;
    private String negeriKelahiran;
    private String suratAlamat1;
    private String suratAlamat2;
    private String suratAlamat3;
    private String suratAlamat4;
    private String noTelefon1;
    private String noTelefon2;
    private String institusiAlamat1;
    private String institusiAlamat2;
    private String institusiAlamat3;
    private String institusiAlamat4;
    private String tangungan;
    private String modalBerbayar;
    private String tarikhMeninggalDunia;
    private PihakCawangan pihakCawangan;
    private PemohonHubungan pemohonHubungan;
    private PermohonanPihak permohonanPihak;
    private List<Pemohon> pemohonList;
    private List<PemohonHubungan> pemohonHubunganList;
    private List<PemohonHubungan> pemohonHubunganList1;
    private List<PemohonHubungan> pemohonHubunganList2;
    private List<PihakCawangan> cawanganList;
    private List<KodBangsa> senaraiKodBangsa;
   // private List<KodJenisPengenalan> senaraiJenisPengenalan;
    private ArrayList hakmilikList = new ArrayList();
    private String idPermohonan;
    private Permohonan p;
    private Pengguna pguna;
    private Pemohon pemohon;
    private String idHubungan;
    private String tarikhLahir;
    private String nama;
    private String kaitan;
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    boolean cariFlag;
    boolean tiadaDataFlag = false;
    boolean tambahCawFlag;
    private String idCawangan;
    private String noPengenalan;
    private String tempatLahir;
    private String umur;
    private BigDecimal gaji;
    private String pekerjaan;
    private KodNegeri kodNegeri;
    private KodNegeri kodNegeri2;
    private String alamat1;
    private String alamat2;
    private String alamat3;
    private String alamat4;
    private String poskod;
    private String negeri;
    private String institusi;
    private String pendapatan;
    private String statusKahwin;
//    private Integer tempohMastautin;
    private String institusiPoskod;
    private String institusiNegeri;
    private PihakPengarah pihakPengarah;
    private Permohonan permohonan;
    private Pihak pihak1;
    private String idPengarah;
    private String idPihak;
    private String kod;
    private List<PihakPengarah> pihakPengarahList;
    private String kodBangsa;
    private String kodNegeri1;
    private String suratNegeri;
    private static final Logger LOG = Logger.getLogger(MaklumatPemohonActionBean.class);
    @DefaultHandler
    public Resolution showForm() {
        return new JSP("pelupusan/maklumat_pemohon.jsp").addParameter("tab", "true");
    }
    public Resolution refreshpage()
    {
        rehydrate();
        //return new RedirectResolution(MaklumatTambahanActionBean.class, "locate");
        return new JSP("pelupusan/maklumat_pemohon.jsp").addParameter("popup", "true");
    }

    public Resolution showTambahPemohon() {
        return new JSP("pelupusan/tambah_pemohon.jsp").addParameter("popup", "true");
    }

    public Resolution showTambahMaklumatIsteri() {
        return new JSP("pelupusan/maklumat_isteri.jsp").addParameter("popup", "true");
    }

    public Resolution showMaklumatIbuBapa() {
        return new JSP("pelupusan/maklumat_ibubapa.jsp").addParameter("popup", "true");
    }

    public Resolution showMaklumatAnak() {
        return new JSP("pelupusan/maklumat_anak.jsp").addParameter("popup", "true");
    }

    public Resolution showEditPemohon() {
        String idPihak = getContext().getRequest().getParameter("idPihak");
        pihak = pihakDAO.findById(Long.valueOf(idPihak));
        pemohon = pelupusanService.findPemohonByPermohonanPihak(p, pihak);
        kodBangsa = pihak.getBangsa().getKod();
        kodNegeri1 = pihak.getNegeri().getKod();
        System.out.println("-------kodNegeri1-----"+kodNegeri1);
        suratNegeri = pihak.getSuratNegeri().getKod();
        System.out.println("-------suratNegeri-----"+suratNegeri);
        if (pihak.getJenisPengenalan().getKod().equals("B") || pihak.getJenisPengenalan().getKod().equals("L") || pihak.getJenisPengenalan().getKod().equals("T") || pihak.getJenisPengenalan().getKod().equals("I"))
//        negeriKelahiran = pemohon.getInstitusiNegeri().getKod();
        senaraiKodBangsa = listUtil.getSenaraiKodBangsaByJenisPengenalan(pihak.getJenisPengenalan().getKod());
        return new JSP("pelupusan/edit_pemohon.jsp").addParameter("popup", "true");
    }


    public Resolution simpanEditPemohon()
    {

        if((pihak.getJenisPengenalan().getKod().endsWith("B"))||(pihak.getJenisPengenalan().getKod().endsWith("L"))||(pihak.getJenisPengenalan().getKod().endsWith("P"))||(pihak.getJenisPengenalan().getKod().endsWith("T"))||(pihak.getJenisPengenalan().getKod().endsWith("I"))||(pihak.getJenisPengenalan().getKod().endsWith("F"))|| (pihak.getJenisPengenalan().getKod().endsWith("O"))||(pihak.getJenisPengenalan().getKod().endsWith("N")))
        {
            if(edittambahValidations())
            {
                        return new JSP("pelupusan/edit_pemohon.jsp").addParameter("popup", "true");
            }
        }else
        {
             if(edittambahValidations2())
            {
                        return new JSP("pelupusan/edit_pemohon.jsp").addParameter("popup", "true");
            }
        }
            Pengguna pg = (Pengguna) context.getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
            InfoAudit infoAudit = new InfoAudit();
            infoAudit.setDiKemaskiniOleh(pg);
            infoAudit.setTarikhKemaskini(new java.util.Date());
            Pihak pihakTemp = pihakDAO.findById(pihak.getIdPihak());
            KodBangsa kb=new KodBangsa();
            kb = kodBangsaDAO.findById(kodBangsa);
            pihakTemp.setBangsa(kb);
            pihakTemp.setNegeri(kodNegeriDAO.findById(kodNegeri1));
            pihakTemp.setSuratNegeri(kodNegeriDAO.findById(suratNegeri));
            pihakTemp.setNama(pihak.getNama());
            pihakTemp.setSuratAlamat1(pihak.getSuratAlamat1());
            pihakTemp.setSuratAlamat2(pihak.getSuratAlamat2());
            pihakTemp.setSuratAlamat3(pihak.getSuratAlamat3());
            pihakTemp.setSuratAlamat4(pihak.getSuratAlamat4());
            pihakTemp.setSuratPoskod(pihak.getSuratPoskod());
            if(pihak.getSuratNegeri()!=null)
            {
                KodNegeri kn = kodNegeriDAO.findById(pihak.getSuratNegeri().getKod());
                pihakTemp.setSuratNegeri(kn);
            }
            if(pihak.getNegeri()!= null)
              System.out.println("*****************************");
            Pemohon pmohon = pelupusanService.findPemohonByPermohonanPihak(p, pihakTemp);
            infoAudit = new InfoAudit();
            infoAudit = pmohon.getInfoAudit();
            infoAudit.setDiKemaskiniOleh(pg);
            infoAudit.setTarikhKemaskini(new java.util.Date());
            pmohon.setInfoAudit(infoAudit);
            pmohon.setKaitan(pemohon.getKaitan());
            if (pihakTemp.getJenisPengenalan().getKod().equals("B") || pihakTemp.getJenisPengenalan().getKod().equals("L") || pihakTemp.getJenisPengenalan().getKod().equals("T") || pihakTemp.getJenisPengenalan().getKod().equals("I")) {
                pmohon.setStatusKahwin(pemohon.getStatusKahwin());
                pmohon.setPekerjaan(pemohon.getPekerjaan());
                pmohon.setUmur(pemohon.getUmur());
                pmohon.setPendapatan(pemohon.getPendapatan());
                pmohon.setTanggungan(pemohon.getTanggungan());
//                KodNegeri kn3 = kodNegeriDAO.findById(negeriKelahiran);
                pmohon.setInstitusiNegeri(pemohon.getInstitusiNegeri());
            }
           pelupusanService.simpanPihakPemohon(pihakTemp, pemohon);
         rehydrate();
        getContext().getRequest().setAttribute("flag", Boolean.TRUE);
        return new JSP("pelupusan/edit_pemohon.jsp").addParameter("tab","true");
    }


    public Resolution showMaklumatAhliLembaga() {
        pihakPengarah = new PihakPengarah();
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        pemohon = pelupusanService.findPemohonByIdPemohon(idPermohonan);
        permohonan = permohonanService.findById(idPermohonan);

        if (pemohon != null) {
            pihak1 = pihakService.findById(String.valueOf(pemohon.getPihak().getIdPihak()));
        }

        return new JSP("pelupusan/tambah_maklumat_ahli_lembaga_pengarah.jsp").addParameter("tab", "true");
    }
    public Resolution showEditlembaga()
    {
        String idPengarah = getContext().getRequest().getParameter("idPengarah");
        pihakPengarah = pihakPengarahDAO.findById(Long.parseLong(idPengarah));

        kod = "";
        if (pihakPengarah.getPihak().getNegeri().getKod() != null) {
            kod = pihakPengarah.getPihak().getNegeri().getKod();
            getContext().getRequest().getSession().setAttribute("kod1", kod);
        }
        System.out.println("-----------public Resolution showEditlembaga()---------");
        return new JSP("pelupusan/edit_ahli_lembaga_pengarah.jsp").addParameter("popup", "true");
    }

//    public Resolution showMaklumatAhliLembaga() {
//        return new JSP("pelupusan/tambah_maklumat_ahli_lembaga_pengarah.jsp").addParameter("popup", "true");
//    }

    public Resolution simpaneditLembaga() {
        Pengguna pg = (Pengguna) context.getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        InfoAudit infoAudit = new InfoAudit();
        infoAudit.setDiKemaskiniOleh(pg);
        infoAudit.setTarikhKemaskini(new java.util.Date());
        if (validateEditAhilLembagaPengarah())
        {
           return new JSP("pelupusan/edit_ahli_lembaga_pengarah.jsp").addParameter("tab", "true");
        }
        PihakPengarah pihakPengarahTemp = pihakPengarahDAO.findById(pihakPengarah.getIdPengarah());
        Pihak pihakTemp = pihakDAO.findById(pihakPengarah.getPihak().getIdPihak());
        pihakPengarahTemp.setNama(pihakPengarah.getNama());
        pihakPengarahTemp.setNoPengenalan(pihakPengarah.getNoPengenalan());
        pihakTemp.setAlamat1(pihakPengarah.getPihak().getAlamat1());
        pihakTemp.setAlamat2(pihakPengarah.getPihak().getAlamat2());
        pihakTemp.setAlamat3(pihakPengarah.getPihak().getAlamat3());
        pihakTemp.setAlamat4(pihakPengarah.getPihak().getAlamat4());
        pihakTemp.setPoskod(pihakPengarah.getPihak().getPoskod());
        pihakService.saveOrUpdate(pihakPengarahTemp, pihakTemp);
        rehydrate();
        getContext().getRequest().setAttribute("flag", Boolean.TRUE);
        return new JSP("pelupusan/edit_ahli_lembaga_pengarah.jsp").addParameter("tab", "true");
    }

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {

        pguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        p = permohonanDAO.findById(idPermohonan) ;
        idPihak = (String) getContext().getRequest().getSession().getAttribute("idPihak");
        idPengarah = (String) getContext().getRequest().getSession().getAttribute("idPengarah");     
        
        if (p != null) {
            idPermohonan = p.getIdPermohonan();
            pemohonList = pelupusanService.findPemohonByIdPermohonan(idPermohonan) ;
            pemohon = pelupusanService.findPemohonByIdPemohon(idPermohonan) ;
            
               
            if (pemohon != null) {
                pihak = pemohon.getPihak();
            }

            if (pemohon != null) {
                pemohonHubunganList = pelupusanService.findHubunganByIDPemohon(pemohon.getIdPemohon());
                pemohonHubunganList1 = pelupusanService.findHubunganByIDPemohonBAPA(pemohon.getIdPemohon());
                pemohonHubunganList2 = pelupusanService.findHubunganByIDANAK(pemohon.getIdPemohon());

                if (pemohon.getPihak() != null) {
                    pihakPengarahList = pihakService.findPengarahByIDPihak(pemohon.getPihak().getIdPihak());
                }

            }
           
            if (!pemohonList.isEmpty()) {
                hakmilikList.clear();
                for (int i = 0; i < pemohonList.size(); i++) {
                    Pihak phk = new Pihak();
                    phk = pemohonList.get(i).getPihak();
                    String[] tname = {"pihak"};
                    Object[] model = {phk};
                    List<HakmilikPihakBerkepentingan> pbList;
                    pbList = hakmilikPihakBerkepentinganDAO.findByEqualCriterias(tname, model, null);
                    if (!pbList.isEmpty()) {
                        for (HakmilikPihakBerkepentingan hpb : pbList) {
                            Hakmilik hak = new Hakmilik();
                            hak = hakmilikDAO.findById(hpb.getHakmilik().getIdHakmilik());
                            hakmilikList.add(hak);
                        }
                    }
                }
            }

        }
        if ((pemohonList == null) || (pemohonList != null && pemohonList.size() == 0)) {
            getContext().getRequest().setAttribute("showTambah", Boolean.TRUE);
        }
    }
    

    public Resolution cariPihakPemohon() {

        if (pihak != null) {
            if (pihak.getJenisPengenalan() != null && pihak.getNoPengenalan() != null) {

                Pihak pihakSearch = new Pihak();
                pihakSearch = pihakService.findPihak(pihak.getJenisPengenalan().getKod(), pihak.getNoPengenalan());

                boolean duplicateFlag = false;

                if (pihakSearch != null) {

                    idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
                    p = permohonanService.findById(idPermohonan);

                    if (!(p.getSenaraiPemohon().isEmpty())) {
                        for (Pemohon pem : p.getSenaraiPemohon()) {
                            if (pem.getPihak().getIdPihak() == pihakSearch.getIdPihak()) {
                                duplicateFlag = true;
                                break;
                            }
                        }
                    }
                }

                if (duplicateFlag == false) {
                    if (pihakSearch != null) {
                        pihak = pihakSearch;
                        cawanganList = pihak.getSenaraiCawangan();
                        cariFlag = true;
                        tiadaDataFlag = false;
                    } else {
                        addSimpleMessage("Tiada Data. Sila Masukkan Maklumat Baru.");
                        cariFlag = true;
                        tiadaDataFlag = true;
                    }
                    senaraiKodBangsa = listUtil.getSenaraiKodBangsaByJenisPengenalan(pihak.getJenisPengenalan().getKod());
                } else {
                    pihak = new Pihak();
                    addSimpleError("Maklumat Ini Telah Dipilih.");
                }
            } else {
                if (pihak.getJenisPengenalan() == null) {
                    addSimpleError("Sila Masukkan Jenis Pengenalan.");
                } else if (pihak.getNoPengenalan() == null) {
                    addSimpleError("Sila Masukkan  Nombor Pengenalan.");
                }
            }

        } else {

            addSimpleError("Sila Masukkan Jenis Pengenalan Dan Nombor Pengenalan.");
        }

        return new JSP("pelupusan/tambah_pemohon.jsp").addParameter("popup", "true");
    }


    public Resolution cariSuamiIsteriPemohon() {
        cariPemohon();
         return new JSP("pelupusan/maklumat_isteri.jsp").addParameter("popup", "true");
    }

    public Resolution cariIbubapaPemohon() {
        cariPemohon();
         return new JSP("pelupusan/maklumat_ibubapa.jsp").addParameter("popup", "true");
    }


    public void cariPemohon() {
        if (pemohonHubungan != null) {
            if (pemohonHubungan.getJenisPengenalan() != null && pemohonHubungan.getNoPengenalan() != null) {

                PemohonHubungan pemohonHubunganSearch = new PemohonHubungan();
                pemohonHubunganSearch = pelupusanService.findByPengenalan(pemohonHubungan.getJenisPengenalan().getKod(), pemohonHubungan.getNoPengenalan());
                 System.out.println("-------pemohonHubungan-----"+pemohonHubungan.getNoPengenalan());
                boolean duplicateFlag = false;

                if (pemohonHubunganSearch != null) {

                    idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
                    p = permohonanService.findById(idPermohonan);

                    if (!(p.getSenaraiPemohon().isEmpty())) {
                        for (Pemohon pem : p.getSenaraiPemohon()) {
                            if (pem.getPihak().getIdPihak() == pemohonHubunganSearch.getIdHubungan()) {
                                duplicateFlag = true;
                                break;
                            }
                        }
                    }
                }

                if (duplicateFlag == false) {
                    if (pemohonHubunganSearch != null) {
                        pemohonHubungan = pemohonHubunganSearch;
                        cawanganList = pihak.getSenaraiCawangan();
                        cariFlag = true;
                        tiadaDataFlag = false;
                    } else {
                        addSimpleMessage("Tiada Data. Sila Masukkan Maklumat Baru.");
                        cariFlag = true;
                        tiadaDataFlag = true;
                    }
                    senaraiKodBangsa = listUtil.getSenaraiKodBangsaByJenisPengenalan(pemohonHubungan.getJenisPengenalan().getKod());
                } else {
                    pemohonHubungan = new PemohonHubungan();
                    addSimpleError("Maklumat Ini Telah Dipilih.");
                }
            } else {
                if (pemohonHubungan.getJenisPengenalan() == null) {
                    addSimpleError("Sila Masukkan Jenis Pengenalan.");
                } else if (pemohonHubungan.getNoPengenalan() == null) {
                    addSimpleError("Sila Masukkan  Nombor Pengenalan.");
                }
            }

        } else {

            addSimpleError("Sila Masukkan Jenis Pengenalan Dan Nombor Pengenalan.");
        }

       // return new JSP("pelupusan/maklumat_isteri.jsp").addParameter("popup", "true");
       //  return new JSP("pelupusan/maklumat_ibubapa.jsp").addParameter("popup", "true");
    }

   public Resolution simpanPemohonPopup() throws ParseException {
        cariFlag = true;
        tiadaDataFlag = true;
        senaraiKodBangsa = listUtil.getSenaraiKodBangsaByJenisPengenalan(pihak.getJenisPengenalan().getKod());
        if((pihak.getJenisPengenalan().getKod().endsWith("B"))||(pihak.getJenisPengenalan().getKod().endsWith("L"))||(pihak.getJenisPengenalan().getKod().endsWith("P"))||(pihak.getJenisPengenalan().getKod().endsWith("T"))||(pihak.getJenisPengenalan().getKod().endsWith("I"))||(pihak.getJenisPengenalan().getKod().endsWith("F"))|| (pihak.getJenisPengenalan().getKod().endsWith("O"))||(pihak.getJenisPengenalan().getKod().endsWith("N")))
        {
            if(tambahValidations())
            {
                        return new JSP("pelupusan/tambah_pemohon.jsp").addParameter("popup", "true");
            }
        }else
        {
             if(tambahValidations2())
            {

                        return new JSP("pelupusan/tambah_pemohon.jsp").addParameter("popup", "true");
            }
        }
        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        p = permohonanService.findById(idPermohonan);
        Pengguna pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        InfoAudit infoAudit = new InfoAudit();

         Pihak pihakTemp=new Pihak();
         String idPihak = (String) getContext().getRequest().getParameter("pihak.idPihak");
         System.out.println("----idPihak-------"+idPihak);
        if(idPihak!=null && !idPihak.equals("0")){
            System.out.println("--if--idPihak-------"+idPihak);
            pihakTemp = pihakDAO.findById(Long.parseLong(idPihak));
            infoAudit = pihakTemp.getInfoAudit();
            infoAudit.setDiKemaskiniOleh(pengguna);
            infoAudit.setTarikhKemaskini(new java.util.Date());
        }else{
             System.out.println("--else--idPihak-------"+idPihak);
            infoAudit.setDimasukOleh(pengguna);
            infoAudit.setTarikhMasuk(new java.util.Date());
        }


        if (pihak.getBangsa() != null) {
            if (pihak.getBangsa().getKod().toString().length() > 4) {
                pihak.setBangsa(null);
            }
        }

        pihakTemp.setInfoAudit(infoAudit);
        Pemohon pmohon = new Pemohon();
        pmohon.setPermohonan(p);
        pmohon.setPihak(pihakTemp);
        pmohon.setInfoAudit(infoAudit);
        pmohon.setCawangan(p.getCawangan());
        if((pihak.getJenisPengenalan().getKod().endsWith("B"))||(pihak.getJenisPengenalan().getKod().endsWith("L"))||(pihak.getJenisPengenalan().getKod().endsWith("P"))||(pihak.getJenisPengenalan().getKod().endsWith("T"))||(pihak.getJenisPengenalan().getKod().endsWith("I"))||(pihak.getJenisPengenalan().getKod().endsWith("F"))|| (pihak.getJenisPengenalan().getKod().endsWith("O"))||(pihak.getJenisPengenalan().getKod().endsWith("N")))
        pmohon.setStatusKahwin(pemohon.getStatusKahwin());

        if (idCawangan != null) {
            pihakCawangan = new PihakCawangan();
            pihakCawangan = pihakCawanganDAO.findById(Long.valueOf(idCawangan));
            pmohon.setPihakCawangan(pihakCawangan);

        }

        if (pmohon != null) {

            pihakTemp.setNama(pihak.getNama());
            pihakTemp.setJenisPengenalan(pihak.getJenisPengenalan());
            pihakTemp.setNoPengenalan(pihak.getNoPengenalan());
            pihakTemp.setKodJantina(pihak.getKodJantina());
            pihakTemp.setBangsa(pihak.getBangsa());
            pihakTemp.setNoTelefon1(pihak.getNoTelefon1());
            pihakTemp.setNoTelefon2(pihak.getNoTelefon2());
            pihakTemp.setNoTelefonBimbit(pihak.getNoTelefonBimbit());
            pihakTemp.setWargaNegara(pihak.getWargaNegara());

            pihakTemp.setAlamat1(pihak.getAlamat1());
            pihakTemp.setAlamat2(pihak.getAlamat2());
            pihakTemp.setAlamat3(pihak.getAlamat3());
            pihakTemp.setAlamat4(pihak.getAlamat4());
            pihakTemp.setPoskod(pihak.getPoskod());
            pihakTemp.setNegeri(pihak.getNegeri());
            pihakTemp.setSuratAlamat1(pihak.getSuratAlamat1());
            pihakTemp.setSuratAlamat2(pihak.getSuratAlamat2());
            pihakTemp.setSuratAlamat3(pihak.getSuratAlamat3());
            pihakTemp.setSuratAlamat4(pihak.getSuratAlamat4());
            pihakTemp.setSuratPoskod(pihak.getSuratPoskod());
            pihakTemp.setSuratNegeri(pihak.getSuratNegeri());
            pihakTemp.setNoTelefon1(pihak.getNoTelefon1());
            pihakTemp.setNoTelefon2(pihak.getNoTelefon2());
            pihakTemp.setTarikhLahir(pihak.getTarikhLahir());
            pihakTemp.setTempatLahir(pihak.getTempatLahir());
           if((pihak.getJenisPengenalan().getKod().endsWith("B"))||(pihak.getJenisPengenalan().getKod().endsWith("L"))||(pihak.getJenisPengenalan().getKod().endsWith("P"))||(pihak.getJenisPengenalan().getKod().endsWith("T"))||(pihak.getJenisPengenalan().getKod().endsWith("I"))||(pihak.getJenisPengenalan().getKod().endsWith("F"))|| (pihak.getJenisPengenalan().getKod().endsWith("O"))||(pihak.getJenisPengenalan().getKod().endsWith("N")))
           {
                pmohon.setPekerjaan(pemohon.getPekerjaan());
                pmohon.setUmur(pemohon.getUmur());
                pmohon.setPendapatan(pemohon.getPendapatan());
//                pmohon.setTempohMastautin(pemohon.getTempohMastautin());
                pmohon.setTanggungan(pemohon.getTanggungan());
                pmohon.setInstitusi(pemohon.getInstitusi());
                pmohon.setInstitusiAlamat1(pemohon.getInstitusiAlamat1());
                pmohon.setInstitusiAlamat2(pemohon.getInstitusiAlamat2());
                pmohon.setInstitusiAlamat3(pemohon.getInstitusiAlamat3());
                pmohon.setInstitusiAlamat4(pemohon.getInstitusiAlamat4());
                pmohon.setInstitusiPoskod(pemohon.getInstitusiPoskod());
                pmohon.setInstitusiNegeri(pemohon.getInstitusiNegeri());
            }

            if (tarikhLahir != null) {
                try {
                    pihakTemp.setTarikhLahir(sdf.parse(tarikhLahir));
                } catch (ParseException ex) {
                    LOG.error("exception: " + ex.getMessage());
                    throw ex;
                }
            }
            pelupusanService.simpanPihakPemohon(pihakTemp, pmohon);
            rehydrate();
            getContext().getRequest().setAttribute("flag", Boolean.TRUE);

         return new JSP("pelupusan/tambah_pemohon.jsp").addParameter("tab", "true");
        }

        return new StreamingResolution("text/plain", "1");
    }

   public boolean tambahValidations()
    {
        boolean flag=false;

        if(pihak.getNama() == null || pihak.getNama().equals(""))
        {
            flag=true;
            addSimpleError(" Sila Masukkan Nama ");
        }
        else if(pihak.getBangsa()== null)
        {
            flag=true;
            addSimpleError(" Sila Pilih Bangsa / Jenis Syarikat :");
        }else if(pihak.getWargaNegara() == null)
        {
            flag=true;
            addSimpleError(" Sila Pilih Kewarganegaraan  ");
        }else if(pemohon.getUmur() == null )
           {
            flag=true;
            addSimpleError("Sila Masukkan Umur ");
            }else if(tarikhLahir == null || tarikhLahir.equals("") )
            {
            flag=true;
            addSimpleError("Sila Masukkan Tarikh Lahir ");
            }else if(pihak.getTempatLahir() == null || pihak.getTempatLahir().equals("") )
            {
            flag=true;
            addSimpleError("Sila Masukkan TempatLahir");
            }else if(pihak.getKodJantina() == null || pihak.getKodJantina().equals(""))
            {
            flag=true;
            addSimpleError("Sila Pilih Jantina ");
            }
//            else if(pihak.getNegeriKelahiran() == null)
//            {
//            flag=true;
//            addSimpleError("Sila Pilih Anak Tempatan ");
//            }
//         else if(pemohon.getTempohMastautin() == null)
//            {
//            flag=true;
//            addSimpleError("Sila Masukkan Tempoh tinggal di Melaka ");
//        }
   else if(pemohon.getPekerjaan() == null || pemohon.getPekerjaan().equals(""))
        {
            flag=true;
            addSimpleError("Sila Masukkan Pekerjaan ");
        }else if(pemohon.getPendapatan() == null)
        {
            flag=true;
            addSimpleError("Sila Masukkan Pendapatan Bulanan (RM)");
        }else if(pemohon.getStatusKahwin() == null )
        {
            flag=true;
            addSimpleError("Sila Masukkan Status Perkahwinan ");
        }else if(pemohon.getTanggungan()==null )
        {
            flag=true;
            addSimpleError("Sila Masukkan Tanggungan ");
        }
        else if(pihak.getAlamat1()==null|| pihak.getAlamat1().equals(""))
        {
            flag=true;
            addSimpleError("Sila Masukkan Alamat Berdaftar");
        }else if(pihak.getPoskod() == null ||pihak.getPoskod().equals("") )
        {
            flag=true;
            addSimpleError("Sila Pilih Poskod ");
        }else if(pihak.getNegeri().getKod() == null || pihak.getNegeri().getKod().equals("0"))
        {
            flag=true;
            addSimpleError("Sila Pilih Negeri ");
        }else if(pihak.getSuratAlamat1() ==null || pihak.getSuratAlamat1().equals("0"))
        {
            flag=true;
            addSimpleError("Sila Masukkan Alamat Surat-Menyurat ");
        }else if(pihak.getSuratPoskod() ==null || pihak.getSuratPoskod().equals(""))
        {
            flag=true;
            addSimpleError("Sila Pilih Poskod ");
        }else if(pihak.getSuratNegeri().getKod() ==null || pihak.getSuratNegeri().getKod().equals("0"))
        {
            flag=true;
            addSimpleError("Sila Pilih Negeri ");
        }else if(pihak.getNoTelefon1() ==null ||pihak.getNoTelefon1().equals(""))
        {
            flag=true;
            addSimpleError(" Sila Masukkan No. Telefon  ");
        }
        return flag;
    }

 public boolean edittambahValidations()
    {
        boolean flag=false;

        if(pihak.getNama() == null || pihak.getNama().equals(""))
        {
            flag=true;
            addSimpleError(" Sila Masukkan Nama ");
        }
        else if(kodBangsa== null)
        {
            flag=true;
            addSimpleError(" Sila Pilih Bangsa / Jenis Syarikat :");
        }else if(pihak.getWargaNegara() == null)
        {
            flag=true;
            addSimpleError(" Sila Pilih Kewarganegaraan  ");
        }else if(pemohon.getUmur() == null )
           {
            flag=true;
            addSimpleError("Sila Masukkan Umur ");
            }else if(tarikhLahir == null || tarikhLahir.equals("") )
            {
            flag=true;
            addSimpleError("Sila Masukkan Tarikh Lahir ");
            }else if(pihak.getTempatLahir() == null || pihak.getTempatLahir().equals("") )
            {
            flag=true;
            addSimpleError("Sila Masukkan Tempat Lahir");
            }else if(pihak.getKodJantina() == null || pihak.getKodJantina().equals(""))
            {
            flag=true;
            addSimpleError("Sila Pilih Jantina ");
            }
//            else if(pihak.getNegeriKelahiran() == null)
//            {
//            flag=true;
//            addSimpleError("Sila Pilih Anak Tempatan ");
//            }
//         else if(pemohon.getTempohMastautin() == null)
//            {
//            flag=true;
//            addSimpleError("Sila Masukkan Tempoh tinggal di Melaka ");
//        }
         else if(pemohon.getPekerjaan() == null || pemohon.getPekerjaan().equals(""))
        {
            flag=true;
            addSimpleError("Sila Masukkan Pekerjaan ");
        }else if(pemohon.getPendapatan() == null)
        {
            flag=true;
            addSimpleError("Sila Masukkan Pendapatan Bulanan (RM)");
        }else if(pemohon.getStatusKahwin() == null )
        {
            flag=true;
            addSimpleError("Sila Masukkan Status Perkahwinan ");
        }else if(pemohon.getTanggungan()==null )
        {
            flag=true;
            addSimpleError("Sila Masukkan Tanggungan ");
        }
        else if(pihak.getAlamat1()==null|| pihak.getAlamat1().equals(""))
        {
            flag=true;
            addSimpleError("Sila Masukkan Alamat Berdaftar");
        }else if(pihak.getPoskod() == null ||pihak.getPoskod().equals("") )
        {
            flag=true;
            addSimpleError("Sila Pilih Poskod ");
        }else if(kodNegeri1 == null )
        {
            flag=true;
            addSimpleError("Sila Pilih Negeri ");
        }else if(pihak.getSuratAlamat1() ==null || pihak.getSuratAlamat1().equals("0"))
        {
            flag=true;
            addSimpleError("Sila Masukkan Alamat Surat-Menyurat ");
        }else if(pihak.getSuratPoskod() ==null || pihak.getSuratPoskod().equals(""))
        {
            flag=true;
            addSimpleError("Sila Pilih Poskod ");
        }else if(suratNegeri ==null )
        {
            flag=true;
            addSimpleError("Sila Pilih Negeri ");
        }else if(pihak.getNoTelefon1() ==null ||pihak.getNoTelefon1().equals(""))
        {
            flag=true;
            addSimpleError(" Sila Masukkan No. Telefon  ");
        }
        return flag;
    }



   public boolean tambahValidations2()
    {
        boolean flag=false;

        if(pihak.getNama() == null || pihak.getNama().equals(""))
        {
            flag=true;
            addSimpleError(" Sila Masukkan Nama ");
        }/*else if(pihak.getWarnaKP() == null || pihak.getWarnaKP().equals("0"))
        {
            flag=true;
            addSimpleError(" Sila Pilih Warna Nombor Kad Pengenalan ");
        }*/
        else if(pihak.getBangsa()== null)
        {
            flag=true;
            addSimpleError(" Sila Pilih Bangsa / Jenis Syarikat :");
        }else if(pihak.getWargaNegara().getKod() == null || pihak.getWargaNegara().getKod().equals("0"))
        {
            flag=true;
            addSimpleError(" Sila Pilih Kewarganegaraan  ");
        }else if(pihak.getModalBerbayar() == null)
        {
             flag=true;
            addSimpleError("Sila Masukkan Modal Berbayar (RM) ");
        }
        else if(pihak.getAlamat1()==null|| pihak.getAlamat1().equals(""))
        {
            flag=true;
            addSimpleError("Sila Masukkan Alamat Berdaftar");
        }else if(pihak.getPoskod() == null ||pihak.getPoskod().equals("") )
        {
            flag=true;
            addSimpleError("Sila Pilih Poskod ");
        }else if(pihak.getSuratNegeri().getKod() ==null || pihak.getSuratNegeri().getKod().equals("0"))
        {
            flag=true;
            addSimpleError("Sila Pilih Negeri ");
        }else if(pihak.getSuratAlamat1() ==null || pihak.getSuratAlamat1().equals("0"))
        {
            flag=true;
            addSimpleError("Sila Masukkan Alamat Surat-Menyurat ");
        }else if(pihak.getSuratPoskod() ==null || pihak.getSuratPoskod().equals(""))
        {
            flag=true;
            addSimpleError("Sila Pilih Poskod ");
        }else if(pihak.getSuratNegeri().getKod() ==null || pihak.getSuratNegeri().getKod().equals("Sila Pilih"))
        {
            flag=true;
            addSimpleError("Sila Pilih Negeri ");
        }else if(pihak.getNoTelefon1() ==null ||pihak.getNoTelefon1().equals(""))
        {
            flag=true;
            addSimpleError(" Sila Masukkan No. Telefon  ");
        }
        return flag;
    }


   public boolean edittambahValidations2()
    {
        boolean flag=false;

        if(pihak.getNama() == null || pihak.getNama().equals(""))
        {
            flag=true;
            addSimpleError(" Sila Masukkan Nama ");

        }/*else if(pihak.getWarnaKP() == null || pihak.getWarnaKP().equals("0"))
        {
            flag=true;
            addSimpleError(" Sila Pilih Warna Nombor Kad Pengenalan ");
        }*/
       else if(kodBangsa== null)
        {
            flag=true;
            addSimpleError(" Sila Pilih Bangsa / Jenis Syarikat :");
        }else if(pihak.getWargaNegara().getKod() == null || pihak.getWargaNegara().getKod().equals("0"))
        {
            flag=true;
            addSimpleError(" Sila Pilih Kewarganegaraan  ");
        }else if(pihak.getModalBerbayar() == null)
        {
             flag=true;
            addSimpleError("Sila Masukkan Modal Berbayar (RM) ");
        }
        else if(pihak.getAlamat1()==null|| pihak.getAlamat1().equals(""))
        {
            flag=true;
            addSimpleError("Sila Masukkan Alamat Berdaftar");
        }else if(pihak.getPoskod() == null ||pihak.getPoskod().equals("") )
        {
            flag=true;
            addSimpleError("Sila Pilih Poskod ");
        }else if(kodNegeri1 == null )
        {
            flag=true;
            addSimpleError("Sila Pilih Negeri ");
        }else if(pihak.getSuratAlamat1() ==null || pihak.getSuratAlamat1().equals("0"))
        {
            flag=true;
            addSimpleError("Sila Masukkan Alamat Surat-Menyurat ");
        }else if(pihak.getSuratPoskod() ==null || pihak.getSuratPoskod().equals(""))
        {
            flag=true;
            addSimpleError("Sila Pilih Poskod ");
        }else if(pihak.getSuratNegeri().getKod() ==null || pihak.getSuratNegeri().getKod().equals("Sila Pilih"))
        {
            flag=true;
            addSimpleError("Sila Pilih Negeri ");
        }else if(pihak.getNoTelefon1() ==null ||pihak.getNoTelefon1().equals(""))
        {
            flag=true;
            addSimpleError(" Sila Masukkan No. Telefon  ");
        }
        return flag;
    }

    public Resolution tambahCawanganPemohon() {

        if (pihak.getNama() == null) {
            addSimpleError("Sila masukkan data yang bertanda *.");
            cariFlag = true;
            tiadaDataFlag = true;
        } else {
            if (!(pihakDAO.exists(pihak.getIdPihak()))) {
                Transaction tx = sessionProvider.get().getTransaction();
                tx.begin();
                try {
                    if (pihak.getBangsa() != null) {
                        if (pihak.getBangsa().getKod().toString().length() > 4) {
                            pihak.setBangsa(null);
                        }
                    }

                    if (pihak.getSuku() != null) {
                        if (pihak.getSuku().getKod().toString().length() > 4) {
                            pihak.setSuku(null);
                        }
                    }

                    InfoAudit info = new InfoAudit();
                    info.setDimasukOleh(pguna);
                    info.setTarikhMasuk(new java.util.Date());
                    pihak.setInfoAudit(info);
                    pihakService.saveOrUpdate(pihak);

                } catch (Exception ex) {
                    tx.rollback();
                }
                tx.commit();
            }
            tambahCawFlag = true;
        }
        return new JSP("pelupusan/tambah_pemohon.jsp").addParameter("popup", "true");
    }

    public Resolution simpanCawanganPemohon() {

        InfoAudit info = new InfoAudit();
        info.setDimasukOleh(pguna);
        info.setTarikhMasuk(new java.util.Date());
        etanahActionBeanContext ctx = new etanahActionBeanContext();
        ctx = (etanahActionBeanContext) getContext();

        Pengguna pengguna = ctx.getUser();
        KodCawangan caw = ctx.getKodCawangan();


        pihakCawangan.setIbupejabat(pihak);
        pihakCawangan.setInfoAudit(info);


        pihakService.saveOrUpdatePihakCawangan(pihakCawangan);

        cariFlag = true;

        String id = String.valueOf(pihak.getIdPihak());

        if (cariFlag) {
            return new RedirectResolution("/pelupusan/maklumat_pemohon?getTambahCawanganPemohon&idPihak=" + id).addParameter("popup", "true");
        }

        return new StreamingResolution("text/plain", "1");
    }

    public Resolution getTambahCawanganPemohon() {
        String idPihak = getContext().getRequest().getParameter("idPihak");
        cariFlag = true;
        pihak = new Pihak();
        pihak = pihakDAO.findById(Long.parseLong(idPihak));
        cawanganList = pihak.getSenaraiCawangan();
        return new JSP("pelupusan/tambah_pemohon.jsp").addParameter("popup", "true");
    }

    public Resolution editCawanganPemohon() {
        String idCaw = getContext().getRequest().getParameter("idCawangan");
        tambahCawFlag = true;
        pihakCawangan = new PihakCawangan();
        pihakCawangan = cawanganService.findById(idCaw);
        pihak = new Pihak();
        pihak = pihakDAO.findById(pihakCawangan.getIbupejabat().getIdPihak());
        return new JSP("pelupusan/tambah_pemohon.jsp").addParameter("popup", "true");
    }

    public Resolution deletePemohon() {

        String idPemohon = getContext().getRequest().getParameter("idPemohon");
        if (idPemohon != null) {
            Pemohon pmohon = pemohonService.findById(idPemohon);
            if (pmohon != null) {
                int PemohonHubunganCount=pelupusanService.deletePemohonHubunganByIdPemohon(pmohon.getIdPemohon());

                pemohonService.delete(pmohon);
                pemohonList = pelupusanService.findPemohonByIdPermohonan(idPermohonan);
            }
        }
        rehydrate();
        return showForm();
    }


    public Resolution deletePemohonHubungan() {

        String idHBGN = getContext().getRequest().getParameter("idHBGN");
        if (idHBGN != null) {
            PemohonHubungan pHubungan = pelupusanService.findByIdHBGN(idHBGN);
            if (pHubungan != null) {
                pelupusanService.deletePemohonHbgn(pHubungan);
            }
        }
        rehydrate();
        return showForm();
    }

    public Resolution deletePihakPengarah() {
        String idPengarah = getContext().getRequest().getParameter("idPengarah");
        if (idPengarah != null) {
            PihakPengarah pihakPengarah1 = pihakPengarahDAO.findById(Long.parseLong(idPengarah));
            if (pihakPengarah1 != null) {
                pihakService.deletePihakPengarah(pihakPengarah1);
            }
        }
        rehydrate();
        return showForm();
    }

    public Resolution deleteCawanganPemohon() {

        String idCaw = getContext().getRequest().getParameter("idCawangan");
        cariFlag = true;

        if (idCaw != null) {

            PihakCawangan pihakCaw = cawanganService.findById(idCaw);
            Long id = pihakCaw.getIbupejabat().getIdPihak();

            if (pihakCaw != null) {
                cawanganService.delete(pihakCaw);
                pihak = new Pihak();
                pihak = pihakDAO.findById(id);
                if (pihak.getSenaraiCawangan() != null) {
                    cawanganList = pihak.getSenaraiCawangan();
                }
            }
        }
        return new JSP("pelupusan/tambah_pemohon.jsp").addParameter("popup", "true");
    }

    public Resolution backCawanganPemohon() {

        pihak = pihakService.findPihak(pihak.getJenisPengenalan().getKod(), pihak.getNoPengenalan());
        cawanganList = pihak.getSenaraiCawangan();
        senaraiKodBangsa = listUtil.getSenaraiKodBangsaByJenisPengenalan(pihak.getJenisPengenalan().getKod());
        cariFlag = true;
        tiadaDataFlag = false;
        return new JSP("pelupusan/tambah_pemohon.jsp").addParameter("popup", "true");
    }




    public Resolution showEditPemohon2()
    {
        String idHubungan = getContext().getRequest().getParameter("idHubungan");
        pemohonHubungan = pemohonHubunganDAO.findById(Long.parseLong(idHubungan));

        pihak = pihakDAO.findById(Long.valueOf(pemohon.getPihak().getIdPihak()));

        senaraiKodBangsa = listUtil.getSenaraiKodBangsaByJenisPengenalan(pihak.getJenisPengenalan().getKod());
//        pemohon = pelupusanService.findPemohonByPermohonanPihak(p, pihak);
//        senaraiKodBangsa = listUtil.getSenaraiKodBangsaByJenisPengenalan(pihak.getJenisPengenalan().getKod());

        return new JSP("pelupusan/edit_pemohonIsteri.jsp").addParameter("popup", "true");
    }

 public Resolution test() throws ParseException {

        return new JSP("pelupusan/edit_pemohonIsteri.jsp").addParameter("popup", "true");
 }

    public Resolution simpanIsteri() throws ParseException
    {


       pguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");

        String idPemohon = getContext().getRequest().getParameter("pemohon.idPemohon");
        pemohon = pelupusanService.findPemohonByIdPemohon(idPermohonan);

        idHubungan = getContext().getRequest().getParameter("pemohonHubungan.idHubungan");

        String isteri=getContext().getRequest().getParameter("isteri");


        if(isteriValidations())
        {

            if(isteri.equals("maklumatisteri"))
            {
                cariFlag = true;
                tiadaDataFlag = true;
                return new JSP("pelupusan/maklumat_isteri.jsp").addParameter("popup", "true");
            }
            else
            {
                cariFlag = true;
                tiadaDataFlag = true;
                return new JSP("pelupusan/edit_pemohonIsteri.jsp").addParameter("popup", "true");
            }

        }
        if(idHubungan!=null){
            pemohonHubungan = pemohonHubunganDAO.findById(Long.parseLong(idHubungan));
        }

        if (pemohonHubungan == null) {
            pemohonHubungan = new PemohonHubungan();
        }

        InfoAudit info = new InfoAudit();
        info.setDimasukOleh(pguna);
        info.setTarikhMasuk(new java.util.Date());
        etanahActionBeanContext ctx = new etanahActionBeanContext();
        ctx = (etanahActionBeanContext) getContext();
        KodCawangan caw = ctx.getKodCawangan();
        if(pemohonHubungan.getJenisPengenalan()!= null)
        senaraiKodBangsa = listUtil.getSenaraiKodBangsaByJenisPengenalan(pemohonHubungan.getJenisPengenalan().getKod());
        pemohonHubungan.setPemohon(pemohon);
        pemohonHubungan.setInfoAudit(info);
        pemohonHubungan.setCawangan(caw);

        kaitan = getContext().getRequest().getParameter("pemohonHubungan.kaitan");
        pemohonHubungan.setKaitan(kaitan);

        nama = getContext().getRequest().getParameter("pemohonHubungan.nama");
        pemohonHubungan.setNama(nama);

        tarikhLahir = getContext().getRequest().getParameter("tarikhLahir");
        if(tarikhLahir!=null)
        pemohonHubungan.setTarikhLahir(sdf.parse(tarikhLahir));

        umur = getContext().getRequest().getParameter("pemohonHubungan.umur");
        pemohonHubungan.setUmur(Integer.parseInt(umur));

        pekerjaan = getContext().getRequest().getParameter("pemohonHubungan.pekerjaan");
        pemohonHubungan.setPekerjaan(pekerjaan);

        institusi = getContext().getRequest().getParameter("pemohonHubungan.institusi");
        pemohonHubungan.setInstitusi(institusi);
        institusiAlamat1 = getContext().getRequest().getParameter("pemohonHubungan.institusiAlamat1");
        pemohonHubungan.setInstitusiAlamat1(institusiAlamat1);
        institusiAlamat2 = getContext().getRequest().getParameter("pemohonHubungan.institusiAlamat2");
        pemohonHubungan.setInstitusiAlamat2(institusiAlamat2);
        institusiAlamat3 = getContext().getRequest().getParameter("pemohonHubungan.institusiAlamat3");
        pemohonHubungan.setInstitusiAlamat3(institusiAlamat3);
        institusiAlamat4 = getContext().getRequest().getParameter("pemohonHubungan.institusiAlamat4");
        pemohonHubungan.setInstitusiAlamat4(institusiAlamat4);
        institusiPoskod = getContext().getRequest().getParameter("pemohonHubungan.institusiPoskod");
        pemohonHubungan.setInstitusiPoskod(institusiPoskod);

        pemohonHubungan.setInstitusiNegeri(pemohonHubungan.getInstitusiNegeri());
        alamat1 = getContext().getRequest().getParameter("pemohonHubungan.alamat1");
        pemohonHubungan.setAlamat1(alamat1);
        alamat2 = getContext().getRequest().getParameter("pemohonHubungan.alamat2");
        pemohonHubungan.setAlamat2(alamat2);
        alamat3 = getContext().getRequest().getParameter("pemohonHubungan.alamat3");
        pemohonHubungan.setAlamat3(alamat3);
        alamat4 = getContext().getRequest().getParameter("pemohonHubungan.alamat4");
        pemohonHubungan.setAlamat4(alamat4);
        poskod = getContext().getRequest().getParameter("pemohonHubungan.poskod");
        pemohonHubungan.setPoskod(poskod);
//        negeri = getContext().getRequest().getParameter("pemohonHubungan.negeri");
        pemohonHubungan.setNegeri(pemohonHubungan.getNegeri());
        tempatLahir = getContext().getRequest().getParameter("pemohonHubungan.tempatLahir");
        pemohonHubungan.setTempatLahir(tempatLahir);
        pelupusanService.simpanPemohonHbgn(pemohonHubungan);
        rehydrate();
        getContext().getRequest().setAttribute("flag", Boolean.TRUE);
        if(isteri.equals("maklumatistire"))
        {
           return new JSP("pelupusan/maklumat_isteri.jsp").addParameter("popup", "true");
        }else
        {
            return new JSP("pelupusan/edit_pemohonIsteri.jsp").addParameter("popup", "true");
        }
    }

     public boolean isteriValidations()
    {
        boolean flag=false;
        if(pemohonHubungan.getNama() == null || pemohonHubungan.getNama().equals(""))
        {
            flag=true;
            addSimpleError("Sila Masukkan Nama  ");
        }else if(pemohonHubungan.getBangsa() == null)
        {
            flag=true;
            addSimpleError("Sila Pilih Bangsa");
        }
//        else if(pemohonHubungan.getWarganegara() == null)
//        {
//            flag=true;
//            addSimpleError("Sila Pilih Kewarganegaraan ");
////        }

        else if(pemohonHubungan.getUmur() == null )
        {
            flag=true;
            addSimpleError("Sila Masukkan Umur");
        }else if(pemohonHubungan.getTempatLahir() == null || pemohonHubungan.getTempatLahir().equals("") )
        {
            flag=true;
            addSimpleError("Sila Masukkan Tempat Lahir");
        }else if(pemohonHubungan.getKaitan() == null)
        {
            flag=true;
            addSimpleError("Sila Pilih Kaitan");
        }else if(pemohonHubungan.getPekerjaan() == null || pemohonHubungan.getPekerjaan().equals(""))
        {
            flag=true;
            addSimpleError("Sila Masukkan Pekerjaan ");
        }else if(pemohonHubungan.getGaji() == null)
        {
            flag=true;
            addSimpleError("Sila Masukkan Pendapatan Bulanan (RM) ");
        }else if(pemohonHubungan.getAlamat1() == null || pemohonHubungan.getAlamat1().equals(""))
        {
             flag=true;
            addSimpleError("Sila Masukkan Alamat Berdaftar ");
        }else if(pemohonHubungan.getPoskod() == null ||pemohonHubungan.getPoskod().equals("") )
        {
            flag=true;
            addSimpleError("Sila Pilih Poskod ");
        }
        else if(pemohonHubungan.getNegeri() == null  )
        {
            flag=true;
            addSimpleError("Sila Pilih Negeri ");
        }
        /*else if(pemohonHubungan.getInstitusi() == null ||pemohonHubungan.getInstitusi().equals("") )
        {
            flag=true;
            addSimpleError("Sila Masukkan Nama/Majikan   ");
        }else if(pemohonHubungan.getInstitusiAlamat1()==null || pemohonHubungan.getInstitusiAlamat1().equals(""))
        {
            flag=true;
            addSimpleError("Sila Masukkan Alamat Majikan   ");
        }
       /*else if(pemohonHubungan.getInstitusiPoskod() == null ||pemohonHubungan.getPoskod().equals("") )
        {
            flag=true;
            addSimpleError("Poskod :field is requires");
        }else if(pemohonHubungan.getInstitusiNegeri().getKod() == null || pemohonHubungan.getInstitusiNegeri().getKod().equals("0") )
        {
            flag=true;
            addSimpleError("Nageri :field is requires");
        } */
        return flag;
    }
     public Resolution showEditPemohon3()
     {
        String idHubungan = getContext().getRequest().getParameter("idHubungan");
        pemohonHubungan = pemohonHubunganDAO.findById(Long.parseLong(idHubungan));
        pihak = pihakDAO.findById(Long.valueOf(pemohon.getPihak().getIdPihak()));
        senaraiKodBangsa = listUtil.getSenaraiKodBangsaByJenisPengenalan(pihak.getJenisPengenalan().getKod());

//        pemohon = pelupusanService.findPemohonByPermohonanPihak(p, pihak);
       //senaraiKodBangsa = listUtil.getSenaraiKodBangsaByJenisPengenalan(pihak.getJenisPengenalan().getKod());
        return new JSP("pelupusan/edit_pemohonIbubapa.jsp").addParameter("popup", "true");
    }


 public Resolution simpanMaklumatIbubapa() throws ParseException {

        pguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");

        String idPemohon = getContext().getRequest().getParameter("pemohon.idPemohon");
        pemohon = pelupusanService.findPemohonByIdPemohon(idPermohonan);

        idHubungan = getContext().getRequest().getParameter("pemohonHubungan.idHubungan");
        System.out.println("--------idHubungan---****-----" + idHubungan);

        PemohonHubungan pemohonHubunganTemp=new PemohonHubungan();
        if(idHubungan!=null){
            pemohonHubunganTemp = pemohonHubunganDAO.findById(Long.parseLong(idHubungan));
        }
        String ibubapa=getContext().getRequest().getParameter("ibubapa");

        if(ibubapaValidations())
        {

            cariFlag = true;
            tiadaDataFlag = true;
            if(ibubapa.equals("maklumat-ibubapa"))
            {

                return new JSP("pelupusan/maklumat_ibubapa.jsp").addParameter("popup", "true");
            }
            else
            {

                return new JSP("pelupusan/edit_pemohonIbubapa.jsp").addParameter("popup", "true");
            }
        }

//        if (pemohonHubungan == null) {
//            pemohonHubungan = new PemohonHubungan();
//        }



        InfoAudit info = new InfoAudit();
        info.setDimasukOleh(pguna);
        info.setTarikhMasuk(new java.util.Date());
        etanahActionBeanContext ctx = new etanahActionBeanContext();
        ctx = (etanahActionBeanContext) getContext();
        KodCawangan caw = ctx.getKodCawangan();
        // kodNegeri = new KodNegeri();
        if(ibubapa.equals("maklumat-ibubapa"))
        senaraiKodBangsa = listUtil.getSenaraiKodBangsaByJenisPengenalan(pemohonHubungan.getJenisPengenalan().getKod());

        pemohonHubunganTemp.setPemohon(pemohon);
        pemohonHubunganTemp.setInfoAudit(info);
        pemohonHubunganTemp.setCawangan(caw);



        if(pemohonHubungan.getJenisPengenalan()!=null )
        senaraiKodBangsa = listUtil.getSenaraiKodBangsaByJenisPengenalan(pemohonHubungan.getJenisPengenalan().getKod());

        kaitan = getContext().getRequest().getParameter("pemohonHubungan.kaitan");
        pemohonHubunganTemp.setKaitan(kaitan);

        nama = getContext().getRequest().getParameter("pemohonHubungan.nama");
        pemohonHubunganTemp.setNama(nama);

        pemohonHubunganTemp.setTarikhLahir(pemohonHubungan.getTarikhLahir());
        noPengenalan = getContext().getRequest().getParameter("pemohonHubungan.noPengenalan");
        pemohonHubunganTemp.setNoPengenalan(noPengenalan);
        umur = getContext().getRequest().getParameter("pemohonHubungan.umur");
        if(umur!=null)
        pemohonHubunganTemp.setUmur(Integer.parseInt(umur));

        pemohonHubunganTemp.setBangsa(pemohonHubungan.getBangsa());
        java.math.BigDecimal s = (BigDecimal)pemohonHubungan.getGaji();
        pemohonHubunganTemp.setGaji(s);

        pekerjaan = getContext().getRequest().getParameter("pemohonHubungan.pekerjaan");
        pemohonHubunganTemp.setPekerjaan(pekerjaan);

        institusi = getContext().getRequest().getParameter("pemohonHubungan.institusi");
        pemohonHubunganTemp.setInstitusi(institusi);
        institusiAlamat1 = getContext().getRequest().getParameter("pemohonHubungan.institusiAlamat1");
        pemohonHubunganTemp.setInstitusiAlamat1(institusiAlamat1);
        institusiAlamat2 = getContext().getRequest().getParameter("pemohonHubungan.institusiAlamat2");
        pemohonHubunganTemp.setInstitusiAlamat2(institusiAlamat2);
        institusiAlamat3 = getContext().getRequest().getParameter("pemohonHubungan.institusiAlamat3");
        pemohonHubunganTemp.setInstitusiAlamat3(institusiAlamat3);
        institusiAlamat4 = getContext().getRequest().getParameter("pemohonHubungan.institusiAlamat4");
        pemohonHubunganTemp.setInstitusiAlamat4(institusiAlamat4);
        institusiPoskod = getContext().getRequest().getParameter("pemohonHubungan.institusiPoskod");
        pemohonHubunganTemp.setInstitusiPoskod(institusiPoskod);
        pemohonHubunganTemp.setInstitusiNegeri(pemohonHubungan.getInstitusiNegeri());
        alamat1 = getContext().getRequest().getParameter("pemohonHubungan.alamat1");
        pemohonHubunganTemp.setAlamat1(alamat1);
        alamat2 = getContext().getRequest().getParameter("pemohonHubungan.alamat2");
        pemohonHubunganTemp.setAlamat2(alamat2);
        alamat3 = getContext().getRequest().getParameter("pemohonHubungan.alamat3");
        pemohonHubunganTemp.setAlamat3(alamat3);
        alamat4 = getContext().getRequest().getParameter("pemohonHubungan.alamat4");
        pemohonHubunganTemp.setAlamat4(alamat4);
        poskod = getContext().getRequest().getParameter("pemohonHubungan.poskod");
        pemohonHubunganTemp.setPoskod(poskod);
        pemohonHubunganTemp.setTarikhMati(pemohonHubungan.getTarikhMati());
        pemohonHubunganTemp.setNegeri(pemohonHubungan.getNegeri());
        tempatLahir = getContext().getRequest().getParameter("pemohonHubungan.tempatLahir");
        pemohonHubunganTemp.setTempatLahir(tempatLahir);
        pelupusanService.simpanPemohonHbgn(pemohonHubunganTemp);
        rehydrate();
        getContext().getRequest().setAttribute("flag", Boolean.TRUE);

        if(ibubapa.equals("maklumat-ibubapa"))
        {
            return new JSP("pelupusan/maklumat_ibubapa.jsp").addParameter("tab", "true");
        }else
        {
            return new JSP("pelupusan/edit_pemohonIbubapa.jsp").addParameter("tab", "true");
        }
    }

 public boolean ibubapaValidations()
    {
        boolean flag=false;
//        flag=false;

        if(pemohonHubungan.getNama() == null || pemohonHubungan.getNama().equals(""))
        {
            flag=true;
            addSimpleError("Sila Masukkan Nama ");
        }else if(pemohonHubungan.getBangsa() == null)
        {
            flag=true;
            addSimpleError("Sila Pilih  Bangsa ");
        }
        else if(pemohonHubungan.getUmur() == null )
        {
            flag=true;
            addSimpleError("Sila Masukkan Umur ");
        }else if(pemohonHubungan.getTempatLahir() == null || pemohonHubungan.getTempatLahir().equals("") )
        {
            flag=true;
            addSimpleError("Sila Masukkan Tempat Lahir");
        }else if(pemohonHubungan.getKaitan() == null || pemohonHubungan.getKaitan().equals(""))
        {
            flag=true;
            addSimpleError("Sila Pilih Kaitan ");
        }
        /*else if(pemohonHubungan.getPekerjaan() == null || pemohonHubungan.getPekerjaan().equals(""))
        {
            flag=true;
            addSimpleError("Sila Masukkan Pekerjaan ");
        }*/
         else if(pemohonHubungan.getGaji() == null)
        {
            flag=true;
            addSimpleError("Sila Masukkan Pendapatan Bulanan (RM) ");
        }
         /*else if(pemohonHubungan.getInstitusi() == null ||pemohonHubungan.getInstitusi().equals("") )
        {
            flag=true;
            addSimpleError("Sila Masukkan Nama/Majikan   ");
        }else if(pemohonHubungan.getInstitusiAlamat1()==null || pemohonHubungan.getInstitusiAlamat1().equals(""))
        {
            flag=true;
            addSimpleError("Sila Masukkan Alamat Majikan   ");
        }
      /*  else if(pemohonHubungan.getInstitusiPoskod() == null ||pemohonHubungan.getPoskod().equals("") )
        {
            flag=true;
            addSimpleError("Poskod :field is requires");
        }
        else if(pemohonHubungan.getPoskod() == null ||pemohonHubungan.getPoskod().equals("") )
        {
            flag=true;
            addSimpleError("Poskod :field is requires");
        } */
      /*   else if(pemohonHubungan.getNegeri().getKod() == null ||pemohonHubungan.getNegeri().getKod().equals("0") )
        {
            flag=true;
            addSimpleError("Sila Pilih Nageri ");
        }else if(pemohonHubungan.getAlamat1() == null ||pemohonHubungan.getAlamat1().equals(""))
        {
            flag=true;
            addSimpleError("Sila Masukkan Alamat Terakhir 1");
        }*?

       /*else if(pemohonHubungan.getInstitusiPoskod() == null ||pemohonHubungan.getPoskod().equals("") )
        {
            flag=true;
            addSimpleError("Poskod :field is requires");
        }else if(pemohonHubungan.getInstitusiNegeri().getKod() == null || pemohonHubungan.getInstitusiNegeri().getKod().equals("0") )
        {
            flag=true;
            addSimpleError("Nageri :field is requires");
        } */
        return flag;
    }


   public Resolution showEditPemohon4() {
        String idHubungan = getContext().getRequest().getParameter("idHubungan");
        pemohonHubungan = pemohonHubunganDAO.findById(Long.parseLong(idHubungan));
        pihak = pihakDAO.findById(Long.valueOf(pemohon.getPihak().getIdPihak()));
        senaraiKodBangsa = listUtil.getSenaraiKodBangsaByJenisPengenalan(pihak.getJenisPengenalan().getKod());

//        pemohon = pelupusanService.findPemohonByPermohonanPihak(p, pihak);
       //senaraiKodBangsa = listUtil.getSenaraiKodBangsaByJenisPengenalan(pihak.getJenisPengenalan().getKod());
        return new JSP("pelupusan/edit_pemohonAnak.jsp").addParameter("popup", "true");
    }

  public Resolution simpanAnak()
  {
    idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
    pemohon = pelupusanService.findPemohonByIdPemohon(idPermohonan);
    p = permohonanService.findById(idPermohonan);
    if (pemohonHubungan != null)
    {
        InfoAudit info = new InfoAudit();
        info.setDimasukOleh(pguna);
        info.setTarikhMasuk(new java.util.Date());
        pemohonHubungan.setKaitan("ANAK");
        pemohonHubungan.setPemohon(pemohon);
        pemohonHubungan.setInfoAudit(info);
        pemohonHubungan.setCawangan(p.getCawangan());
        if(anakValidations())
        {
            return new JSP("pelupusan/maklumat_anak.jsp").addParameter("popup", "true");
         }
         pelupusanService.simpanPemohonHbgn(pemohonHubungan);
       }
       rehydrate();
       getContext().getRequest().setAttribute("flag", Boolean.TRUE);
       return new JSP("pelupusan/maklumat_anak.jsp").addParameter("popup", "true");
  }
  public boolean anakValidations()
  {
    boolean flag = false;
    if(pemohonHubungan.getNama() == null || pemohonHubungan.getNama().equals(""))
        {
            flag=true;
            addSimpleError(" Sila Masukkan Nama  ");
        }else if(pemohonHubungan.getKodJantina() == null || pemohonHubungan.getKodJantina().endsWith("0"))
        {
            flag=true;
            addSimpleError(" Sila Pilih Jantina ");
        }else if(pemohonHubungan.getUmur() == null )
        {
            flag=true;
            addSimpleError(" Sila Masukkan Umur ");
        }
    return flag;
  }
 public Resolution simpanEditAnak()
 {
     idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
     pemohon = pelupusanService.findPemohonByIdPemohon(idPermohonan);
     p = permohonanService.findById(idPermohonan);

     if (pemohonHubungan != null)
     {
        InfoAudit infoAudit = new InfoAudit();
        infoAudit.setDimasukOleh(pguna);
        pemohonHubungan.setKaitan("ANAK");
        pemohonHubungan.setPemohon(pemohon);
        pemohonHubungan.setInfoAudit(infoAudit);
        pemohonHubungan.setCawangan(p.getCawangan());
        infoAudit.setTarikhKemaskini(new java.util.Date());
        if(anakValidations())
        {
            return new JSP("pelupusan/edit_pemohonAnak.jsp").addParameter("popup", "true");
        }
        PemohonHubungan pemohonHubunganTemp = pemohonHubunganDAO.findById(pemohonHubungan.getIdHubungan());

        pemohonHubunganTemp.setKodJantina(pemohonHubungan.getKodJantina());
        pemohonHubunganTemp.setNama(pemohonHubungan.getNama());
        pemohonHubunganTemp.setUmur(pemohonHubungan.getUmur());
        pemohonHubunganTemp.setInstitusi(pemohonHubungan.getInstitusi());
        pemohonService.saveOrUpdateHbgn(pemohonHubunganTemp);
    }
    rehydrate();
    getContext().getRequest().setAttribute("flag", Boolean.TRUE);
    return new JSP("pelupusan/edit_pemohonAnak.jsp").addParameter("tab", "true");
}

 public Resolution simpanMaklumatAhliLembaga() {
        if (validateAhilLembangaPengarah()) {
//            getContext().getRequest().setAttribute("flag", Boolean.TRUE);
            return new JSP("pelupusan/tambah_maklumat_ahli_lembaga_pengarah.jsp").addParameter("tab", "true");
        }

        if (pihakPengarah != null) {
            InfoAudit info = new InfoAudit();
            info.setDimasukOleh(pguna);
            info.setTarikhMasuk(new java.util.Date());

            pihakPengarah.setPihak(pemohon.getPihak());
            pihakPengarah.setInfoAudit(info);
            pihakPengarah.setAktif('Y');

            Pihak pihakTemp = pihakDAO.findById(pihak1.getIdPihak());

            pihakTemp.setAlamat1(pihak1.getAlamat1());
            pihakTemp.setAlamat2(pihak1.getAlamat2());
            pihakTemp.setAlamat3(pihak1.getAlamat3());
            pihakTemp.setAlamat4(pihak1.getAlamat4());
            pihakTemp.setPoskod(pihak1.getPoskod());
            pihakTemp.setNegeri(pihak1.getNegeri());
            pihakPengarah.setPihak(pihakTemp);

            pihakService.simpanPemohonHbgn(pihakPengarah, pihakTemp);
        }
        rehydrate();
        getContext().getRequest().setAttribute("flag", Boolean.TRUE);
        return new JSP("pelupusan/tambah_maklumat_ahli_lembaga_pengarah.jsp").addParameter("tab", "true");
    }


 public boolean validateAhilLembangaPengarah() {
        boolean flag = false;
        System.out.println("nama" + pihakPengarah.getNama());

        if ((pihakPengarah.getNama() == null)) {
            flag = true;
            addSimpleError("Sila Masukkan Nama");
        } else if (pihakPengarah.getNoPengenalan() == null) {
            flag = true;
            addSimpleError("Sila Masukkan NoPengenalan");
        } else if ((pihak1.getAlamat1() == null)) {
            flag = true;
            addSimpleError("Sila Masukkan Alamat");
        } else if (pihak1.getPoskod() == null) {
            flag = true;
            addSimpleError("Sila Masukkan Poskod");
        } else if (pihak1.getNegeri()== null) {
            flag = true;
            addSimpleError("Sila Pilih Negeri");
        }
        /*else if (pihakPengarah.getJumlahSaham() == null) {
            flag = true;
            addSimpleError("Sila Masukkan JumlahSaham");
        }*/
        return flag;
    }

    public boolean validateEditAhilLembagaPengarah() {
        boolean flag = false;
        if ((pihakPengarah.getNama() == null) || (pihakPengarah.getNama().trim().equals(""))) {
            flag = true;
            addSimpleError("Sila Masukkan Nama");
        } else if ((pihakPengarah.getPihak().getAlamat1() == null) || (pihakPengarah.getPihak().getAlamat1().trim().equals(""))) {
            flag = true;
            addSimpleError("Sila Masukkan Alamat");
        } else if (pihakPengarah.getPihak().getPoskod() == null) {
            flag = true;
            addSimpleError("Sila Masukkan Poskod");
        }/* else if (pihak1.getNegeri().getKod().equals("0")) {
            flag = true;
            addSimpleError("Sila Pilih Negeri");
        }
        /*else if (kod.equals("0"))
        {
            flag = true;
            addSimpleError("Sila Pilih Negeri");
        }
        /*else if (pihakPengarah.getJumlahSaham() == null) {
            flag = true;
            addSimpleError("Sila Masukkan JumlahSaham");
        }*/
        return flag;

    }


    public String getWarnaKP() {
        return warnaKP;
    }

    public void setWarnaKP(String warnaKP) {
        this.warnaKP = warnaKP;
    }

    public String getwargaNegara()
    {
        return wargaNegara;
    }

    public void setwargaNegara(String wargaNegara)
    {
        this.wargaNegara = wargaNegara;
    }

    public String getkodJantina()
    {
        return kodJantina;
    }

    public void setkodJantina(String kodJantina)
    {
        this.kodJantina = kodJantina;
    }

    public String getnegeriKelahiran()
    {
        return negeriKelahiran;
    }

    public void setnegeriKelahiran(String negeriKelahiran)
    {
        this.negeriKelahiran = negeriKelahiran;
    }

    public String getalamat1()
    {
    return alamat1;
    }

    public String getalamat2()
    {
    return alamat2;
    }
    public String getalamat3()
    {
    return alamat3;
    }
    public String getalamat4()
    {
    return alamat4;
    }

    public String getSuratAlamat1() {
        return suratAlamat1;
    }

    public void setSuratAlamat1(String suratAlamat1) {
        this.suratAlamat1 = suratAlamat1;
    }

    public String getSuratAlamat2() {
        return suratAlamat2;
    }

    public void setSuratAlamat2(String suratAlamat2) {
        this.suratAlamat2 = suratAlamat2;
    }

    public String getSuratAlamat3() {
        return suratAlamat3;
    }

    public void setSuratAlamat3(String suratAlamat3) {
        this.suratAlamat3 = suratAlamat3;
    }

    public String getSuratAlamat4() {
        return suratAlamat4;
    }

    public void setSuratAlamat4(String suratAlamat4) {
        this.suratAlamat4 = suratAlamat4;
    }

    public String getNoTelefon1() {
        return noTelefon1;
    }

    public void setNoTelefon1(String noTelefon1) {
        this.noTelefon1 = noTelefon1;
    }

    public String getNoTelefon2() {
        return noTelefon2;
    }

    public void setNoTelefon2(String noTelefon2) {
        this.noTelefon2 = noTelefon2;
    }

    public String getPendapatan() {
        return pendapatan;
    }

    public void setPendapatan(String pendapatan) {
        this.pendapatan = pendapatan;
    }

    public String getStatusKahwin() {
        return statusKahwin;
    }

    public void setStatusKahwin(String statusKahwin) {
        this.statusKahwin = statusKahwin;
    }

    public String getTangungan() {
        return tangungan;
    }

    public void setTangungan(String tangungan) {
        this.tangungan = tangungan;
    }




            public Permohonan getP() {
        return p;
    }

    public void setP(Permohonan p) {
        this.p = p;
    }

    public Pihak getPihak() {
        return pihak;
    }

    public void setPihak(Pihak pihak) {
        this.pihak = pihak;
    }

    public PermohonanPihak getPermohonanPihak() {
        return permohonanPihak;
    }

    public void setPermohonanPihak(PermohonanPihak permohonanPihak) {
        this.permohonanPihak = permohonanPihak;
    }

    public String getIdPermohonan() {
        return idPermohonan;
    }

    public void setIdPermohonan(String idPermohonan) {
        this.idPermohonan = idPermohonan;
    }

    public Pengguna getPguna() {
        return pguna;
    }

    public void setPguna(Pengguna pguna) {
        this.pguna = pguna;
    }

    public List<Pemohon> getPemohonList() {
        return pemohonList;
    }

    public void setPemohonList(List<Pemohon> pemohonList) {
        this.pemohonList = pemohonList;
    }

    public boolean isCariFlag() {
        return cariFlag;
    }

    public void setCariFlag(boolean cariFlag) {
        this.cariFlag = cariFlag;
    }

    public boolean isTiadaDataFlag() {
        return tiadaDataFlag;
    }

    public void setTiadaDataFlag(boolean tiadaDataFlag) {
        this.tiadaDataFlag = tiadaDataFlag;
    }

    public boolean isTambahCawFlag() {
        return tambahCawFlag;
    }

    public void setTambahCawFlag(boolean tambahCawFlag) {
        this.tambahCawFlag = tambahCawFlag;
    }

    public PihakCawangan getPihakCawangan() {
        return pihakCawangan;
    }

    public void setPihakCawangan(PihakCawangan pihakCawangan) {
        this.pihakCawangan = pihakCawangan;
    }

    public String getIdCawangan() {
        return idCawangan;
    }

    public void setIdCawangan(String idCawangan) {
        this.idCawangan = idCawangan;
    }

    public List<PihakCawangan> getCawanganList() {
        return cawanganList;
    }

    public void setCawanganList(List<PihakCawangan> cawanganList) {
        this.cawanganList = cawanganList;
    }

    public ArrayList getHakmilikList() {
        return hakmilikList;
    }

    public void setHakmilikList(ArrayList hakmilikList) {
        this.hakmilikList = hakmilikList;
    }

    public List<KodBangsa> getSenaraiKodBangsa() {
        return senaraiKodBangsa;
    }

    public void setSenaraiKodBangsa(List<KodBangsa> senaraiKodBangsa) {
        this.senaraiKodBangsa = senaraiKodBangsa;
    }

    public String getTarikhLahir() {
        return tarikhLahir;
    }

    public void setTarikhLahir(String tarikhLahir) {
        this.tarikhLahir = tarikhLahir;
    }

    public Pemohon getPemohon() {
        return pemohon;
    }

    public void setPemohon(Pemohon pemohon) {
        this.pemohon = pemohon;
    }


    public PemohonHubungan getPemohonHubungan() {
        return pemohonHubungan;
    }

    public void setPemohonHubungan(PemohonHubungan pemohonHubungan) {
        this.pemohonHubungan = pemohonHubungan;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getKaitan() {
        return kaitan;
    }

    public void setKaitan(String kaitan) {
        this.kaitan = kaitan;
    }

    public String getIdHubungan() {
        return idHubungan;
    }

    public void setIdHubungan(String idHubungan) {
        this.idHubungan = idHubungan;
    }

    public String getNoPengenalan() {
        return noPengenalan;
    }

    public void setNoPengenalan(String noPengenalan) {
        this.noPengenalan = noPengenalan;
    }

    public String getUmur() {
        return umur;
    }

    public void setUmur(String umur) {
        this.umur = umur;
    }

    public String getTempatLahir() {
        return tempatLahir;
    }

    public void setTempatLahir(String tempatLahir) {
        this.tempatLahir = tempatLahir;
    }

    public String getPekerjaan() {
        return pekerjaan;
    }

    public void setPekerjaan(String pekerjaan) {
        this.pekerjaan = pekerjaan;
    }

    public BigDecimal getGaji() {
        return gaji;
    }

    public void setGaji(BigDecimal gaji) {
        this.gaji = gaji;
    }

    public String getInstitusi() {
        return institusi;
    }

    public void setInstitusi(String institusi) {
        this.institusi = institusi;
    }

    public String getInstitusiAlamat1() {
        return institusiAlamat1;
    }

    public void setInstitusiAlamat1(String institusiAlamat1) {
        this.institusiAlamat1 = institusiAlamat1;
    }

    public String getInstitusiAlamat2() {
        return institusiAlamat2;
    }

    public void setInstitusiAlamat2(String institusiAlamat2) {
        this.institusiAlamat2 = institusiAlamat2;
    }

    public String getInstitusiAlamat3() {
        return institusiAlamat3;
    }

    public void setInstitusiAlamat3(String institusiAlamat3) {
        this.institusiAlamat3 = institusiAlamat3;
    }

    public String getInstitusiAlamat4() {
        return institusiAlamat4;
    }

    public void setInstitusiAlamat4(String institusiAlamat4) {
        this.institusiAlamat4 = institusiAlamat4;
    }

    public String getInstitusiNegeri() {
        return institusiNegeri;
    }

    public void setInstitusiNegeri(String institusiNegeri) {
        this.institusiNegeri = institusiNegeri;
    }

    public String getInstitusiPoskod() {
        return institusiPoskod;
    }

    public void setInstitusiPoskod(String institusiPoskod) {
        this.institusiPoskod = institusiPoskod;
    }

    public KodNegeri getKodNegeri() {
        return kodNegeri;
    }

    public void setKodNegeri(KodNegeri kodNegeri) {
        this.kodNegeri = kodNegeri;
    }

    public List<PemohonHubungan> getPemohonHubunganList() {
        return pemohonHubunganList;
    }

    public void setPemohonHubunganList(List<PemohonHubungan> pemohonHubunganList) {
        this.pemohonHubunganList = pemohonHubunganList;
    }

    public List<PemohonHubungan> getPemohonHubunganList1() {
        return pemohonHubunganList1;
    }

    public void setPemohonHubunganList1(List<PemohonHubungan> pemohonHubunganList1) {
        this.pemohonHubunganList1 = pemohonHubunganList1;
    }

    public List<PemohonHubungan> getPemohonHubunganList2() {
        return pemohonHubunganList2;
    }

    public void setPemohonHubunganList2(List<PemohonHubungan> pemohonHubunganList2) {
        this.pemohonHubunganList2 = pemohonHubunganList2;
    }


    public String getAlamat1() {
        return alamat1;
    }

    public void setAlamat1(String alamat1) {
        this.alamat1 = alamat1;
    }

    public String getAlamat2() {
        return alamat2;
    }

    public void setAlamat2(String alamat2) {
        this.alamat2 = alamat2;
    }

    public String getAlamat3() {
        return alamat3;
    }

    public void setAlamat3(String alamat3) {
        this.alamat3 = alamat3;
    }

    public String getAlamat4() {
        return alamat4;
    }

    public void setAlamat4(String alamat4) {
        this.alamat4 = alamat4;
    }

    public KodNegeri getKodNegeri2() {
        return kodNegeri2;
    }

    public void setKodNegeri2(KodNegeri kodNegeri2) {
        this.kodNegeri2 = kodNegeri2;
    }

    public String getNegeri() {
        return negeri;
    }

    public void setNegeri(String negeri) {
        this.negeri = negeri;
    }

    public String getPoskod() {
        return poskod;
    }

    public void setPoskod(String poskod) {
        this.poskod = poskod;
    }

//    public Integer getTempohMastautin() {
//        return tempohMastautin;
//    }
//
//    public void setTempohMastautin(Integer tempohMastautin) {
//        this.tempohMastautin = tempohMastautin;
//    }

    public String getTarikhMeninggalDunia() {
        return tarikhMeninggalDunia;
    }

    public void setTarikhMeninggalDunia(String tarikhMeninggalDunia) {
        this.tarikhMeninggalDunia = tarikhMeninggalDunia;
    }

    public String getIdPengarah() {
        return idPengarah;
    }

    public void setIdPengarah(String idPengarah) {
        this.idPengarah = idPengarah;
    }

    public String getIdPihak() {
        return idPihak;
    }

    public void setIdPihak(String idPihak) {
        this.idPihak = idPihak;
    }

    public String getKodJantina() {
        return kodJantina;
    }

    public void setKodJantina(String kodJantina) {
        this.kodJantina = kodJantina;
    }

    public Pihak getPihak1() {
        return pihak1;
    }

    public void setPihak1(Pihak pihak1) {
        this.pihak1 = pihak1;
    }

    public PihakPengarah getPihakPengarah() {
        return pihakPengarah;
    }

    public void setPihakPengarah(PihakPengarah pihakPengarah) {
        this.pihakPengarah = pihakPengarah;
    }

    public String getWargaNegara() {
        return wargaNegara;
    }

    public void setWargaNegara(String wargaNegara) {
        this.wargaNegara = wargaNegara;
    }

    public List<PihakPengarah> getPihakPengarahList() {
        return pihakPengarahList;
    }

    public void setPihakPengarahList(List<PihakPengarah> pihakPengarahList) {
        this.pihakPengarahList = pihakPengarahList;
    }
    public String getModalBerbayar() {
        return modalBerbayar;
    }

    public void setModalBerbayar(String modalBerbayar) {
        this.modalBerbayar = modalBerbayar;
    }

    public String getKodBangsa() {
        return kodBangsa;
    }

    public void setKodBangsa(String kodBangsa) {
        this.kodBangsa = kodBangsa;
    }

    public String getKodNegeri1() {
        return kodNegeri1;
    }

    public void setKodNegeri1(String kodNegeri1) {
        this.kodNegeri1 = kodNegeri1;
    }

    public String getNegeriKelahiran() {
        return negeriKelahiran;
    }

    public void setNegeriKelahiran(String negeriKelahiran) {
        this.negeriKelahiran = negeriKelahiran;
    }

    public String getSuratNegeri() {
        return suratNegeri;
    }

    public void setSuratNegeri(String suratNegeri) {
        this.suratNegeri = suratNegeri;
    }
 }