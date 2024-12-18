/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
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
import etanah.dao.PermohonanBahanBatuanDAO;
import etanah.dao.PermohonanDAO;
import etanah.model.Hakmilik;
import etanah.model.HakmilikPermohonan;
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
import etanah.model.PermohonanBahanBatuan;
import etanah.model.PermohonanKelompok;
import etanah.model.PermohonanPihakPendeposit;
import etanah.model.PihakAlamatTamb;
import etanah.model.PihakPengarah;
import etanah.service.BPelService;
import etanah.service.PelupusanService;
import etanah.service.common.CawanganService;
import etanah.view.ListUtil;
import etanah.view.stripes.pelupusan.disClass.DisLaporanTanahService;
import etanah.view.stripes.pelupusan.disClass.DisPemohonController;
import etanah.view.stripes.pelupusan.disClass.DisPermohonanPage;
import java.util.ArrayList;
import org.hibernate.Session;
import org.hibernate.Transaction;
import java.text.SimpleDateFormat;
import java.text.ParseException;
import javax.servlet.http.HttpSession;
import oracle.bpel.services.workflow.task.model.Task;
import org.apache.commons.lang.StringUtils;

/**
 *
 * @author afham
 */
@UrlBinding("/pelupusan/maklumat_pemohonV2")
public class MaklumatPemohonV2ActionBean extends AbleActionBean {
    //Injector (Google Guice)

    @Inject
    DisLaporanTanahService disLaporanTanahService;
    @Inject
    ListUtil listUtil;
    @Inject
    PermohonanBahanBatuanDAO permohonanBahanBatuanDAO;
    @Inject
    PelupusanService pelupusanService;
    //Object
    private PermohonanBahanBatuan permohonanBahanBatuan;
    private DisPemohonController disPemohonController;
    private Pemohon pemohon;
    private Pihak pihak;
    private PihakAlamatTamb pihakAlamatTambahan;
    private PemohonHubungan pemohonHubungan;
    private PihakPengarah pihakPengarah;
    private Permohonan permohonan;
    private PermohonanKelompok permohonanKelompok;
    private static final Logger LOG = Logger.getLogger(MaklumatPemohonV2ActionBean.class);
    private static final boolean IS_LOG_DEBUG = LOG.isDebugEnabled();
    etanahActionBeanContext ctx;
    //Object List
    private List<Pemohon> listPemohon;
    private List<PermohonanKelompok> senaraiKelompok;
    private List<PemohonHubungan> listHubunganIbuBapa;
    private List<PemohonHubungan> listHubunganAnak;
    private List<PemohonHubungan> listHubunganSuamiIsteri;
    private List<PihakPengarah> listPengarah;
    private List<HakmilikPermohonan> senaraiHakmilik;
    private List<HakmilikPihakBerkepentingan> senaraiTuanTanah;
    private List<Hakmilik> listHakmilik;
    private List<KodBangsa> senaraiKodBangsa;
    private List<Hakmilik> listHakmilikPasangan; //Jika suami isteri ada hakmilik di dalam negeri
    //Variable with Datatype
    private String idPermohonan;
    private String kodNegeri;
    private String stageId;
    private String idPihak;
    private String idPemohon;
    private String idPemohonHubungan;
    private String add1;
    private int sizeKod;
    String tarikhLahir;
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

    @DefaultHandler
    public Resolution editPemohon() {
        HttpSession httpSession = getContext().getRequest().getSession();
        disPemohonController = new DisPemohonController();
        disPemohonController = disPemohonController.editPemohon();
        httpSession.setAttribute("disPemohonController", disPemohonController);
        return new JSP(DisPermohonanPage.getPEMOHON_MAIN_PAGE()).addParameter("tab", "true");
    }

    public Resolution viewPemohon() {
        HttpSession httpSession = getContext().getRequest().getSession();
        disPemohonController = new DisPemohonController();
        disPemohonController = disPemohonController.viewPemohon();
        httpSession.setAttribute("disPemohonController", disPemohonController);
        return new JSP(DisPermohonanPage.getPEMOHON_MAIN_PAGE()).addParameter("tab", "true");
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

    public Resolution deleteRow() throws ParseException {

        String idPemohon = getContext().getRequest().getParameter("idPemohon");
        String tName = getContext().getRequest().getParameter("tName");
        String typeSyor = getContext().getRequest().getParameter("typeName");
        String forwardJSP = new String();

        if (idPemohon != null && tName != null) {
            forwardJSP = refreshData(disLaporanTanahService.delObject(tName, new String[]{idPemohon}, typeSyor));
            addSimpleMessage("Maklumat Berjaya Dihapuskan");
        }
        rehydrate();
//      return new JSP("/WEB-INF/jsp/" + forwardJSP).addParameter("popup", "true");        
        return new JSP(forwardJSP).addParameter("popup", "true");
    }

    public Resolution simpanPemilik() {
        Pengguna pguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = new Permohonan();
        permohonan = (Permohonan) disLaporanTanahService.findObject(permohonan, new String[]{idPermohonan}, 0);
        String item = (String) getContext().getRequest().getParameter("idPihak");

        String[] listPihakTemp = item.split(",");
        for (int i = 0; i < listPihakTemp.length; i++) {

            if (!listPihakTemp[i].equals("T")) {
                idPihak = listPihakTemp[i];

                InfoAudit ia = new InfoAudit();
                ia.setDimasukOleh(pguna);
                ia.setTarikhMasuk(new java.util.Date());
                pihak = disLaporanTanahService.getPelupusanService().findByIdPihak(idPihak);
                if (pihak != null) {
                    pemohon = new Pemohon();
                    pemohon.setPermohonan(permohonan);
                    pemohon.setPihak(pihak);
                    pemohon.setCawangan(permohonan.getCawangan());
                    pemohon.setInfoAudit(ia);
                    ia = pihak.getInfoAudit();
                    ia.setDiKemaskiniOleh(pguna);
                    ia.setTarikhKemaskini(new java.util.Date());
                    pihak.setInfoAudit(ia);
                    disLaporanTanahService.getPelupusanService().simpanPemohon(pemohon);
                }
            }
        }


        return new JSP(DisPermohonanPage.getPEMOHON_EDIT()).addParameter("popup", "true");
    }

    public boolean PemohonValidation(String kodUrusan) {
        boolean flag = false;
        String sub = idPermohonan.substring(0, 2);
        if (pihak.getJenisPengenalan().getKod().equals("U") || pihak.getJenisPengenalan().getKod().equals("D") || pihak.getJenisPengenalan().getKod().equals("S")) {
            if ((pihak.getNama() == null) || (pihak.getNama().trim().equals(""))) {
                flag = true;
                addSimpleError("Sila Masukkan Nama Syarikat");
            }
            if ((pihak.getNama() == null) || (pihak.getNama().trim().equals("0"))) {
                flag = true;
                addSimpleError("Sila Masukkan Nama");
            }
            if (pihak.getJenisPengenalan().getKod().equals("B")) {
                if ((pihak.getWarnaKP() == null) || (pihak.getWarnaKP().trim().equals("0"))) {
                    flag = true;
                    addSimpleError("Sila Masukkan Warna Kad Pengenalan");
                }
            } else if (pihak.getBangsa() == null) {
                flag = true;
                addSimpleError("Sila Pilih Jenis Syarikat");
//            } else if (pihak.getTarikhLahir() == null) {
//                flag = true;
//                addSimpleError("Sila Masukkan Tarikh Penubuhan");
            } else if ((pihak.getAlamat1() == null) || (pihak.getAlamat1().trim().equals(""))) {
                flag = true;
                addSimpleError("Sila Masukkan Alamat");
            } else if (pihak.getPoskod() == null) {
                flag = true;
                addSimpleError("Sila Masukkan Poskod");
            } else if (pihak.getNegeri().getKod().equals("0")) {
                flag = true;
                addSimpleError("Sila Pilih Negeri");

            } else if ((pihakAlamatTambahan.getAlamatKetiga1() == null) || (pihakAlamatTambahan.getAlamatKetiga1().trim().equals(""))) {
                flag = true;
                addSimpleError("Sila Masukkan Surat Alamat");
            } else if (pihakAlamatTambahan.getAlamatKetigaPoskod() == null) {
                flag = true;
                addSimpleError("Sila Masukkan Surat Poskod");
            } else if (pihakAlamatTambahan.getAlamatKetigaNegeri().getKod().equals("0")) {
                flag = true;
                addSimpleError("Sila Pilih Surat Negeri");

            }
        } else if ((pihak.getJenisPengenalan().getKod().equals("B")) || (pihak.getJenisPengenalan().getKod().equals("N")) || (pihak.getJenisPengenalan().getKod().equals("L")) || (pihak.getJenisPengenalan().getKod().equals("T"))) {
//             || (pihak.getJenisPengenalan().getKod().equals("I"))
            if ((pihak.getNama() == null) || (pihak.getNama().trim().equals(""))) {
                flag = true;
                addSimpleError("Sila Masukkan Nama");
            } else if (pihak.getBangsa() == null) {
                flag = true;
                addSimpleError("Sila Pilih Bangsa");
            }
            if ((pihak.getWarnaKP() == null) || (pihak.getWarnaKP().trim().equals("0"))) {
                flag = true;
                addSimpleError("Sila Masukkan Warna Kad Pengenalan");
            } else if (pihak.getKodJantina() == null) {
                flag = true;
                addSimpleError("Sila Pilih Jantina");
            } else if (pemohon.getUmur() == null) {
                flag = true;
                addSimpleError("Sila Masukkan Umur");
            } else if (pihak.getWargaNegara().getKod().equals("0")) {
                flag = true;
                addSimpleError("Sila Masukkan Warganegara");
            } else if ((pihak.getAlamat1() == null) || (pihak.getAlamat1().trim().equals(""))) {
                flag = true;
                addSimpleError("Sila Masukkan Alamat");
            } else if (pihak.getPoskod() == null) {
                flag = true;
                addSimpleError("Sila Masukkan Poskod");
            } else if (pihak.getNegeri().getKod().equals("0")) {
                flag = true;
                addSimpleError("Sila Pilih Negeri");

            } else if ((pihakAlamatTambahan.getAlamatKetiga1() == null) || (pihakAlamatTambahan.getAlamatKetiga1().trim().equals(""))) {
                flag = true;
                addSimpleError("Sila Masukkan Surat Alamat");
            } else if (pihakAlamatTambahan.getAlamatKetigaPoskod() == null) {
                flag = true;
                addSimpleError("Sila Masukkan Surat Poskod");
            } else if (pihakAlamatTambahan.getAlamatKetigaNegeri().getKod().equals("0")) {
                flag = true;
                addSimpleError("Sila Pilih Surat Negeri");

            }
//            if (!kodUrusan.equals("PRMP")) {
//                if ((pemohon.getPekerjaan() != null)) {
//                    if (pemohon.getPendapatan() == null) {
//                        flag = true;
//                        addSimpleError("Sila Masukkan Pendapatan Jika Pekerjaan Ada");
//                    }
////                     }
//                } else if (pemohon.getStatusKahwin().equals("0")) {
//                    flag = true;
//                    addSimpleError("Sila Masukkan Status Perkahwinan");
//                } else if (pemohon.getTanggungan() == null) {
//                    flag = true;
//                    addSimpleError("Sila Masukkan Tanggungan");
//                } else if (sub.equals("04")) {
//                    if (pihak.getAnakTempatan() == null) {
//                        flag = true;
//                        addSimpleError("Sila Pilih Anak Tempatan");
//                    } else if (pemohon.getTempohMastautin() == null) {
//                        flag = true;
//                        addSimpleError("Sila Masukkan Tempoh tinggal di Melaka");
//                    }
//                }
//            }
//

        } else if ((pihak.getJenisPengenalan().getKod().equals("P"))) {
            if ((pihak.getNama() == null) || (pihak.getNama().trim().equals(""))) {
                flag = true;
                addSimpleError("Sila Masukkan Nama");
            } else if (pihak.getBangsa() == null) {
                flag = true;
                addSimpleError("Sila Pilih Bangsa");
            }
            if (pihak.getKodJantina() == null) {
                flag = true;
                addSimpleError("Sila Pilih Jantina");
            } else if (pemohon.getUmur() == null) {
                flag = true;
                addSimpleError("Sila Masukkan Umur");
            } else if (pihak.getWargaNegara().getKod().equals("0")) {
                flag = true;
                addSimpleError("Sila Masukkan Warganegara");
            } else if ((pihak.getAlamat1() == null) || (pihak.getAlamat1().trim().equals(""))) {
                flag = true;
                addSimpleError("Sila Masukkan Alamat");
            } else if (pihak.getPoskod() == null) {
                flag = true;
                addSimpleError("Sila Masukkan Poskod");
            } else if (pihak.getNegeri().getKod().equals("0")) {
                flag = true;
                addSimpleError("Sila Pilih Negeri");

            } else if ((pihakAlamatTambahan.getAlamatKetiga1() == null) || (pihakAlamatTambahan.getAlamatKetiga1().trim().equals(""))) {
                flag = true;
                addSimpleError("Sila Masukkan Surat Alamat");
            } else if (pihakAlamatTambahan.getAlamatKetigaPoskod() == null) {
                flag = true;
                addSimpleError("Sila Masukkan Surat Poskod");
            } else if (pihakAlamatTambahan.getAlamatKetigaNegeri().getKod().equals("0")) {
                flag = true;
                addSimpleError("Sila Pilih Surat Negeri");

            }
        }

        return flag;
    }

    public Resolution simpanPemohon() throws ParseException {
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = new Permohonan();
        permohonan = (Permohonan) disLaporanTanahService.findObject(permohonan, new String[]{idPermohonan}, 0);

        senaraiKodBangsa = listUtil.getSenaraiKodBangsaByJenisPengenalan(pihak.getJenisPengenalan().getKod());
        LOG.info("THIS IS KODURUSAN:" + permohonan.getKodUrusan().getKod());
        if (PemohonValidation(permohonan.getKodUrusan().getKod())) {
//            getContext().getRequest().setAttribute("flag", Boolean.TRUE);
            return new JSP(DisPermohonanPage.getTAMBAH_PEMOHON()).addParameter("popup", "true");
        }

        Pengguna pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        InfoAudit infoAudit = new InfoAudit();

        Pihak pihakTemp = new Pihak();
        PihakAlamatTamb pihakAlamatTamb = new PihakAlamatTamb();
        String idPihak = (String) getContext().getRequest().getParameter("pihak.idPihak");
        if (idPihak != null && !idPihak.equals("0")) {
            pihakTemp = disLaporanTanahService.getPihakDAO().findById(Long.parseLong(idPihak));
            if (pihakTemp.getSenaraiAlamatTamb().size() > 0) {
                pihakAlamatTamb = pihakTemp.getSenaraiAlamatTamb().get(0);
            }
            infoAudit = pihakTemp.getInfoAudit();
            infoAudit.setDiKemaskiniOleh(pengguna);
            infoAudit.setTarikhKemaskini(new java.util.Date());
        } else {
            infoAudit.setDimasukOleh(pengguna);
            infoAudit.setTarikhMasuk(new java.util.Date());
        }

        if (pihak.getBangsa() != null) {
            if (pihak.getBangsa().getKod().toString().length() > 4) {
                pihak.setBangsa(null);
            }
        }

        pihakTemp.setInfoAudit(infoAudit);
        pihakAlamatTamb.setInfoAudit(infoAudit);
        Pemohon pmohon = disLaporanTanahService.getPelupusanService().findPemohonByPermohonanPihak(permohonan, pihakTemp);
        if (pmohon != null) {
            infoAudit = pmohon.getInfoAudit();
            infoAudit.setDiKemaskiniOleh(pengguna);
            infoAudit.setTarikhKemaskini(new java.util.Date());
        } else {
            pmohon = new Pemohon();
        }
        pmohon.setPermohonan(permohonan);
        pmohon.setPihak(pihakTemp);
        pmohon.setInfoAudit(infoAudit);
        pmohon.setCawangan(permohonan.getCawangan());

        if (pmohon != null) {

            pihakTemp.setNama(pihak.getNama());
            pihakTemp.setJenisPengenalan(pihak.getJenisPengenalan());
            pihakTemp.setNoPengenalan(pihak.getNoPengenalan());
            pihakTemp.setKodJantina(pihak.getKodJantina());

            String sub = idPermohonan.substring(0, 2);
            if (sub.equals("04")) {
                pihakTemp.setAnakTempatan(pihak.getAnakTempatan());
            }
            pihakTemp.setBangsa(pihak.getBangsa());
            pihakTemp.setNoTelefon1(pihak.getNoTelefon1());
            pihakTemp.setNoTelefon2(pihak.getNoTelefon2());
            pihakTemp.setNoTelefonBimbit(pihak.getNoTelefon1());
            pihakTemp.setWargaNegara(pihak.getWargaNegara());
            pihakTemp.setAlamat1(pihak.getAlamat1());
            pihakTemp.setAlamat2(pihak.getAlamat2());
            pihakTemp.setAlamat3(pihak.getAlamat3());
            pihakTemp.setAlamat4(pihak.getAlamat4());
            pihakTemp.setPoskod(pihak.getPoskod());
            pihakTemp.setNegeri(pihak.getNegeri());
            pihakTemp.setSuratAlamat1(pihakAlamatTambahan.getAlamatKetiga1());
            pihakTemp.setSuratAlamat2(pihakAlamatTambahan.getAlamatKetiga2());
            pihakTemp.setSuratAlamat3(pihakAlamatTambahan.getAlamatKetiga3());
            pihakTemp.setSuratAlamat4(pihakAlamatTambahan.getAlamatKetiga4());
            pihakTemp.setSuratPoskod(pihakAlamatTambahan.getAlamatKetigaPoskod());
            pihakTemp.setSuratNegeri(pihakAlamatTambahan.getAlamatKetigaNegeri());
            pihakAlamatTamb.setAlamatKetiga1(pihakAlamatTambahan.getAlamatKetiga1());
            pihakAlamatTamb.setAlamatKetiga2(pihakAlamatTambahan.getAlamatKetiga2());
            pihakAlamatTamb.setAlamatKetiga3(pihakAlamatTambahan.getAlamatKetiga3());
            pihakAlamatTamb.setAlamatKetiga4(pihakAlamatTambahan.getAlamatKetiga4());
            pihakAlamatTamb.setAlamatKetigaPoskod(pihakAlamatTambahan.getAlamatKetigaPoskod());
            pihakAlamatTamb.setAlamatKetigaNegeri(pihakAlamatTambahan.getAlamatKetigaNegeri());
            pihakAlamatTamb.setPihak(pihakTemp);
            pihakTemp.setNoTelefon1(pihak.getNoTelefon1());
            pihakTemp.setNoTelefon2(pihak.getNoTelefon2());
            pihakTemp.setEmail(pihak.getEmail());
            pihakTemp.setTarikhLahir(pihak.getTarikhLahir());
            if (StringUtils.isNotBlank(pihak.getTempatLahir())) {
                pihakTemp.setTempatLahir(pihak.getTempatLahir());
            } else {
                pihakTemp.setTempatLahir("TIADA");
            }

            if ((pihak.getJenisPengenalan().getKod().equals("B"))) {
                pihakTemp.setWarnaKP(pihak.getWarnaKP());
            }

            if ((pihak.getJenisPengenalan().getKod().equals("B")) || (pihak.getJenisPengenalan().getKod().equals("L")) || (pihak.getJenisPengenalan().getKod().equals("P")) || (pihak.getJenisPengenalan().getKod().equals("T")) || (pihak.getJenisPengenalan().getKod().equals("I")) || (pihak.getJenisPengenalan().getKod().equals("F")) || (pihak.getJenisPengenalan().getKod().equals("O")) || (pihak.getJenisPengenalan().getKod().equals("N"))) {
                if (pemohon.getPekerjaan() == null) {
//                    String tB = "Tidak Bekerja" ;
                    pmohon.setPekerjaan("TIDAK BEKERJA");
                    pmohon.setPendapatan(BigDecimal.ZERO);
                } else {
                    pmohon.setPekerjaan(pemohon.getPekerjaan());
                    pmohon.setPendapatan(pemohon.getPendapatan());
                }
                pmohon.setUmur(pemohon.getUmur());

                if (sub.equals("04")) {
                    pmohon.setTempohMastautin(pemohon.getTempohMastautin());
                }
                pmohon.setTanggungan(pemohon.getTanggungan());

            }

            if (pihakTemp.getJenisPengenalan().getKod().equals("U") || pihakTemp.getJenisPengenalan().getKod().equals("D") || pihakTemp.getJenisPengenalan().getKod().equals("S") || pihakTemp.getJenisPengenalan().getKod().equals("0")) {
            } else {
                pmohon.setStatusKahwin(pemohon.getStatusKahwin());
            }
            if (tarikhLahir != null) {
                try {
                    pihakTemp.setTarikhLahir(sdf.parse(tarikhLahir));
                } catch (ParseException ex) {
                    LOG.error("exception: " + ex.getMessage());
                    throw ex;
                }
            }
            disLaporanTanahService.getPelupusanService().simpanPihakPemohonDanAlamat(pihakTemp, pmohon, pihakAlamatTamb);

        }
        listPemohon = new ArrayList<Pemohon>();
        permohonan = (Permohonan) disLaporanTanahService.findObject(permohonan, new String[]{idPermohonan}, 0);
        listPemohon = permohonan.getSenaraiPemohon();
        senaraiKelompok = disLaporanTanahService.getPelupusanService().findMohonKelompokByIdMohonInduk(idPermohonan);
//        senaraiKelompok = permohonan.getSenaraiPihak();
        LOG.info("senaraiKelompok.size():" + senaraiKelompok.size());
        LOG.info("listPemohon.size():" + listPemohon.size());

//        rehydrate();
        return new JSP(DisPermohonanPage.getPEMOHON_EDIT()).addParameter("popup", "true");
    }

    public Resolution cariPihakPemohon() {
        kodNegeri = disLaporanTanahService.getConf().getProperty("kodNegeri");
        if (pihak != null) {
            if (pihak.getJenisPengenalan() != null && pihak.getNoPengenalan() != null) {

                Pihak pihakSearch = new Pihak();
                pihakSearch = disLaporanTanahService.getPelupusanService().findPihak(pihak.getJenisPengenalan().getKod(), pihak.getNoPengenalan());

                boolean duplicateFlag = false;

                if (pihakSearch != null) {

                    idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
                    permohonan = disLaporanTanahService.getPelupusanService().findById(idPermohonan);

                    if (!(permohonan.getSenaraiPemohon().isEmpty())) {
                        for (Pemohon pem : permohonan.getSenaraiPemohon()) {
                            if (pem.getPihak() != null) {
                                if (pem.getPihak().getIdPihak() == pihakSearch.getIdPihak()) {
                                    duplicateFlag = true;
                                    break;
                                }
                            }

                        }
                    }
                }

                if (duplicateFlag == false) {
                    if (pihakSearch != null) {
                        pihak = pihakSearch;
//                        cawanganList = pihak.getSenaraiCawangan();
                        addSimpleMessage("Maklumat Dijumpai");
                        if (pihak.getSenaraiAlamatTamb().size() > 0) {
                            pihakAlamatTambahan = pihak.getSenaraiAlamatTamb().get(0);
                        }
                    } else {
                        addSimpleMessage("Tiada Data. Sila Masukkan Maklumat Baru.");
                    }
                    senaraiKodBangsa = listUtil.getSenaraiKodBangsaByJenisPengenalan(pihak.getJenisPengenalan().getKod());

                } else {
                    pihak = new Pihak();
                    addSimpleError("Maklumat Ini Telah Dipilih.");
                    senaraiHakmilik = new ArrayList<HakmilikPermohonan>();
                    listHakmilik = new ArrayList<Hakmilik>();
                    senaraiHakmilik = permohonan.getSenaraiHakmilik();
                    if (senaraiHakmilik.size() > 0) {
                        for (HakmilikPermohonan hp : senaraiHakmilik) {
                            if (hp.getHakmilik() != null) {
                                listHakmilik.add(hp.getHakmilik());
                            }
                        }
                    }
                    return new JSP(DisPermohonanPage.getCARIAN_PEMOHON()).addParameter("popup", "true");
                }
                pihak.setWargaNegara(disLaporanTanahService.getKodWarganegaraDAO().findById("MAL"));
            }
//            pemohon = new Pemohon() ;
        }

        return new JSP(DisPermohonanPage.getTAMBAH_PEMOHON()).addParameter("popup", "true");
    }

    public Resolution tambahTidakBerkenaan() {
        kodNegeri = disLaporanTanahService.getConf().getProperty("kodNegeri");
        if (pihak != null) {
            if (pihak.getJenisPengenalan() != null) {
                addSimpleMessage("Maklumat Baru");
            }
        }

        return new JSP(DisPermohonanPage.getTAMBAH_PEMOHON()).addParameter("popup", "true");
    }

    public Resolution showFormPopUp() {
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = new Permohonan();
        permohonan = (Permohonan) disLaporanTanahService.findObject(permohonan, new String[]{idPermohonan}, 0);
        pihak = new Pihak();
        senaraiHakmilik = new ArrayList<HakmilikPermohonan>();
        listHakmilik = new ArrayList<Hakmilik>();
        senaraiHakmilik = permohonan.getSenaraiHakmilik();
        if (senaraiHakmilik.size() > 0) {
            for (HakmilikPermohonan hp : senaraiHakmilik) {
                if (hp.getHakmilik() != null) {
                    listHakmilik.add(hp.getHakmilik());
                }
            }
        }
        return new JSP(DisPermohonanPage.getCARIAN_PEMOHON()).addParameter("popup", "true");
    }

    public Resolution showFormKemaskiniPemohon() {
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = new Permohonan();
        permohonan = (Permohonan) disLaporanTanahService.findObject(permohonan, new String[]{idPermohonan}, 0);
        String idPemohon = getContext().getRequest().getParameter("idPemohon");
        String idPihak = getContext().getRequest().getParameter("idPihak");
        pihak = disLaporanTanahService.getPihakDAO().findById(Long.valueOf(idPihak));
        if (pihak != null) {
            pemohon = disLaporanTanahService.getPelupusanService().findPemohonByPermohonanPihak(permohonan, pihak);
            if (pihak.getJenisPengenalan() != null) {
                senaraiKodBangsa = listUtil.getSenaraiKodBangsaByJenisPengenalan(pihak.getJenisPengenalan().getKod());
            }

            if (pihak.getSenaraiAlamatTamb().size() > 0) {
                pihakAlamatTambahan = pihak.getSenaraiAlamatTamb().get(0);
            }
        }

        return new JSP(DisPermohonanPage.getTAMBAH_PEMOHON()).addParameter("popup", "true");
    }

    public Resolution refreshpage() {
        return new JSP(refreshData(ctx.getRequest().getParameter("type"))).addParameter("popup", Boolean.TRUE);
    }

    public Resolution showPemilik() {
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = new Permohonan();
        permohonan = (Permohonan) disLaporanTanahService.findObject(permohonan, new String[]{idPermohonan}, 0);
        senaraiHakmilik = new ArrayList<HakmilikPermohonan>();
        senaraiHakmilik = permohonan.getSenaraiHakmilik();
        List<Hakmilik> senaraiHakmilikTemp = new ArrayList<Hakmilik>();
        senaraiTuanTanah = new ArrayList<HakmilikPihakBerkepentingan>();
        if (senaraiHakmilik.size() > 0) {
            for (HakmilikPermohonan hp : senaraiHakmilik) {
                if (hp.getHakmilik() != null) {
                    senaraiHakmilikTemp.add(hp.getHakmilik());
                }
            }

            if (senaraiHakmilikTemp.size() > 0) {
                for (Hakmilik hm : senaraiHakmilikTemp) {
                    if (hm.getSenaraiPihakBerkepentingan().size() > 0) {
                        for (HakmilikPihakBerkepentingan hpk : hm.getSenaraiPihakBerkepentingan()) {
                            senaraiTuanTanah.add(hpk);
                        }
                    }
                }
            }

            if (senaraiTuanTanah.size() > 0) {
                sizeKod = senaraiTuanTanah.size();
            }
        }
        return new JSP(DisPermohonanPage.getPILIH_PEMILIK()).addParameter("popup", "true");
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
        listPemohon = permohonan.getSenaraiPemohon();
        senaraiKelompok = disLaporanTanahService.getPelupusanService().findMohonKelompokByIdMohonInduk(idPermohonan);

        if (StringUtils.isNotBlank(type)) {
            /*
             * FORWARD TO DIFFERENT POPUP
             */
            forwardJSP = refreshData(type);
        }
        return new JSP(forwardJSP).addParameter("popup", Boolean.TRUE);
    }

    public String refreshData(String type) {
        String forwardJSP = new String();
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = new Permohonan();
        permohonan = (Permohonan) disLaporanTanahService.findObject(permohonan, new String[]{idPermohonan}, 0);
        int typeNum = type.equals("tPemohon") ? 1
                : type.equals("mKeluarga") ? 2
                : type.equals("mPengarah") ? 3
                : type.equals("main") ? 5
                : 0;

        switch (typeNum) {
            case 1:
                listPemohon = permohonan.getSenaraiPemohon();
                senaraiKelompok = disLaporanTanahService.getPelupusanService().findMohonKelompokByIdMohonInduk(idPermohonan);
                forwardJSP = DisPermohonanPage.getPEMOHON_EDIT();
                break;
            case 2:
                ctx = (etanahActionBeanContext) getContext();
                idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
                permohonan = new Permohonan();
                permohonan = (Permohonan) disLaporanTanahService.findObject(permohonan, new String[]{idPermohonan}, 0);
                idPemohon = ctx.getRequest().getParameter("idPemohon");
                if (StringUtils.isNotBlank(idPemohon)) {
                    pemohon = new Pemohon();
                    pemohon = (Pemohon) disLaporanTanahService.findObject(pemohon, new String[]{idPemohon}, 1);
                    if (pemohon != null) {
                        listHubunganSuamiIsteri = disLaporanTanahService.getPelupusanService().findHubunganByIDPemohon(pemohon.getIdPemohon());
                        listHubunganIbuBapa = disLaporanTanahService.getPelupusanService().findHubunganByIDPemohonBAPA(pemohon.getIdPemohon());
                        listHubunganAnak = disLaporanTanahService.getPelupusanService().findHubunganByIDANAK(pemohon.getIdPemohon());
                    }
                }
                forwardJSP = DisPermohonanPage.getKELUARGA_EDIT();
                break;
            case 3:
                ctx = (etanahActionBeanContext) getContext();
                idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
                permohonan = new Permohonan();
                permohonan = (Permohonan) disLaporanTanahService.findObject(permohonan, new String[]{idPermohonan}, 0);
                idPemohon = ctx.getRequest().getParameter("idPemohon");
                if (StringUtils.isNotBlank(idPemohon)) {
                    pemohon = new Pemohon();
                    pemohon = (Pemohon) disLaporanTanahService.findObject(pemohon, new String[]{idPemohon}, 1);
                    if (pemohon != null) {
                        if (pemohon.getPihak() != null) {
                            listPengarah = disLaporanTanahService.getPelupusanService().findPengarahByIDPihak(pemohon.getPihak().getIdPihak());
                        }
                    }
                }
                forwardJSP = DisPermohonanPage.getPENGARAH_EDIT();
                break;
            case 5:
                rehydrate();
                disPemohonController = (DisPemohonController) getContext().getRequest().getSession().getAttribute("disPemohonController");
                forwardJSP = DisPermohonanPage.getPEMOHON_MAIN_PAGE();
                break;
        }
        return forwardJSP;
    }

    @Before(stages = {LifecycleStage.BindingAndValidation}, on = {"!reload", "!simpanPemilik", "!deleteRow", "!showFormTambahPemohonHubungan", "!showFormKemaskiniPemohonHubungan", "!simpanPemohon"})
    public void rehydrate() {
        LOG.info("In rehydrate");
        ctx = (etanahActionBeanContext) getContext();
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        String taskId = (String) getContext().getRequest().getSession().getAttribute("taskId");
        Pengguna pguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        stageId = stageId(taskId, pguna);
        kodNegeri = disLaporanTanahService.getConf().getProperty("kodNegeri");
        permohonanBahanBatuan = pelupusanService.findByIdMBB(idPermohonan);
        if (idPermohonan != null) {

            int syx = 0;
            permohonan = new Permohonan();
            permohonan = (Permohonan) disLaporanTanahService.findObject(permohonan, new String[]{idPermohonan}, 0);
            if (permohonan.getKodUrusan().getKod().equals("RLPS")) {
                permohonan = (Permohonan) disLaporanTanahService.findObject(permohonan, new String[]{permohonan.getPermohonanSebelum().getIdPermohonan()}, 0);
            }
            /*
             * SENARAI PEMOHON
             */
            listPemohon = permohonan.getSenaraiPemohon();
            senaraiKelompok = disLaporanTanahService.getPelupusanService().findMohonKelompokByIdMohonInduk(idPermohonan);
            LOG.info("senaraiKelompok.size():" + senaraiKelompok.size());
            LOG.info("listPemohon.size():" + listPemohon.size());

        }

    }

    /*
     * KELUARGA
     */
    public Resolution showFormMaklumatKeluarga() {
        ctx = (etanahActionBeanContext) getContext();
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = new Permohonan();
        permohonan = (Permohonan) disLaporanTanahService.findObject(permohonan, new String[]{idPermohonan}, 0);
        idPemohon = ctx.getRequest().getParameter("idPemohon");
        if (StringUtils.isNotBlank(idPemohon)) {
            pemohon = new Pemohon();
            pemohon = (Pemohon) disLaporanTanahService.findObject(pemohon, new String[]{idPemohon}, 1);
            if (pemohon != null) {
                listHubunganSuamiIsteri = disLaporanTanahService.getPelupusanService().findHubunganByIDPemohon(pemohon.getIdPemohon());
                listHubunganIbuBapa = disLaporanTanahService.getPelupusanService().findHubunganByIDPemohonBAPA(pemohon.getIdPemohon());
                listHubunganAnak = disLaporanTanahService.getPelupusanService().findHubunganByIDANAK(pemohon.getIdPemohon());
            }
        }

        return new JSP(DisPermohonanPage.getKELUARGA_EDIT()).addParameter("popup", "true");
    }

    public Resolution viewFormMaklumatKeluarga() {
        ctx = (etanahActionBeanContext) getContext();
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = new Permohonan();
        permohonan = (Permohonan) disLaporanTanahService.findObject(permohonan, new String[]{idPermohonan}, 0);
        idPemohon = ctx.getRequest().getParameter("idPemohon");
        if (StringUtils.isNotBlank(idPemohon)) {
            pemohon = new Pemohon();
            pemohon = (Pemohon) disLaporanTanahService.findObject(pemohon, new String[]{idPemohon}, 1);
            if (pemohon != null) {
                listHubunganSuamiIsteri = disLaporanTanahService.getPelupusanService().findHubunganByIDPemohon(pemohon.getIdPemohon());
                listHubunganIbuBapa = disLaporanTanahService.getPelupusanService().findHubunganByIDPemohonBAPA(pemohon.getIdPemohon());
                listHubunganAnak = disLaporanTanahService.getPelupusanService().findHubunganByIDANAK(pemohon.getIdPemohon());
            }
        }

        return new JSP(DisPermohonanPage.getKELUARGA_VIEW()).addParameter("popup", "true");
    }

    /*
     * PENGARAH
     */
    public Resolution showFormMaklumatPengarah() {
        ctx = (etanahActionBeanContext) getContext();
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = new Permohonan();
        permohonan = (Permohonan) disLaporanTanahService.findObject(permohonan, new String[]{idPermohonan}, 0);
        idPemohon = ctx.getRequest().getParameter("idPemohon");
        if (StringUtils.isNotBlank(idPemohon)) {
            pemohon = new Pemohon();
            pemohon = (Pemohon) disLaporanTanahService.findObject(pemohon, new String[]{idPemohon}, 1);
            if (pemohon != null) {
                if (pemohon.getPihak() != null) {
                    listPengarah = disLaporanTanahService.getPelupusanService().findPengarahByIDPihak(pemohon.getPihak().getIdPihak());
                }
            }
        }

        return new JSP(DisPermohonanPage.getPENGARAH_EDIT()).addParameter("popup", "true");
    }

    public Resolution viewFormMaklumatPengarah() {
        ctx = (etanahActionBeanContext) getContext();
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = new Permohonan();
        permohonan = (Permohonan) disLaporanTanahService.findObject(permohonan, new String[]{idPermohonan}, 0);
        idPemohon = ctx.getRequest().getParameter("idPemohon");
        if (StringUtils.isNotBlank(idPemohon)) {
            pemohon = new Pemohon();
            pemohon = (Pemohon) disLaporanTanahService.findObject(pemohon, new String[]{idPemohon}, 1);
            if (pemohon != null) {
                if (pemohon.getPihak() != null) {
                    listPengarah = disLaporanTanahService.getPelupusanService().findPengarahByIDPihak(pemohon.getPihak().getIdPihak());
                }
            }
        }

        return new JSP(DisPermohonanPage.getPENGARAH_VIEW()).addParameter("popup", "true");
    }

    public Resolution viewFormMaklumatPemohon() {
        ctx = (etanahActionBeanContext) getContext();
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = new Permohonan();
        permohonan = (Permohonan) disLaporanTanahService.findObject(permohonan, new String[]{idPermohonan}, 0);
        idPemohon = ctx.getRequest().getParameter("idPemohon");
        if (StringUtils.isNotBlank(idPemohon)) {
            pemohon = new Pemohon();
            pemohon = (Pemohon) disLaporanTanahService.findObject(pemohon, new String[]{idPemohon}, 1);
            if (pemohon != null) {
                if (pemohon.getPihak() != null) {
                    pihak = pemohon.getPihak();
                    if (pihak.getSenaraiAlamatTamb().size() > 0) {
                        pihakAlamatTambahan = pihak.getSenaraiAlamatTamb().get(0);
                    }
                }
            }
        }

        return new JSP(DisPermohonanPage.getPEMOHON_VIEW()).addParameter("popup", "true");
    }

    /*
     * TANAH MILIK PEMOHON
     */
    public Resolution viewFormMaklumatTanahMilik() {
        ctx = (etanahActionBeanContext) getContext();
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = new Permohonan();
        permohonan = (Permohonan) disLaporanTanahService.findObject(permohonan, new String[]{idPermohonan}, 0);
        idPemohon = ctx.getRequest().getParameter("idPemohon");
        String idPihak = ctx.getRequest().getParameter("idPihak");
        String status = ctx.getRequest().getParameter("status");
        if (StringUtils.isNotBlank(idPemohon)) {
            pemohon = new Pemohon();
            pemohon = (Pemohon) disLaporanTanahService.findObject(pemohon, new String[]{idPemohon}, 1);
            if (pemohon != null) {
                if (pemohon.getPihak() != null) {
                    List<HakmilikPihakBerkepentingan> pbList = new ArrayList<HakmilikPihakBerkepentingan>();
                    listHakmilik = new ArrayList<Hakmilik>();
                    pbList = disLaporanTanahService.getPelupusanService().findHakmilikPihak(pemohon.getPihak().getIdPihak());
                    LOG.info("THIS IS PBLIST->" + pbList.size());
                    if (!pbList.isEmpty()) {
                        for (HakmilikPihakBerkepentingan hpb : pbList) {
//                            Hakmilik hak = new Hakmilik();
//                            hak = disLaporanTanahService.getHakmilikDAO().findById(hpb.getHakmilik().getIdHakmilik());
//                            if(hak != null){
                            if (hpb.getHakmilik() != null) {
                                listHakmilik.add(hpb.getHakmilik());
                            }
                        }
                    }
                }
                //New feature - utk kes suami isteri yg ada tanah kt dlm negeri tersebut - User Gemas minta
                listHubunganSuamiIsteri = disLaporanTanahService.getPelupusanService().findHubunganByIDPemohon(pemohon.getIdPemohon());
                if (!listHubunganSuamiIsteri.isEmpty()) {
                    PemohonHubungan ph = new PemohonHubungan();
                    ph = listHubunganSuamiIsteri.get(0);
                    List<HakmilikPihakBerkepentingan> pbListPasangan = new ArrayList<HakmilikPihakBerkepentingan>();
                    listHakmilikPasangan = new ArrayList<Hakmilik>();
                    pbListPasangan = disLaporanTanahService.getPelupusanService().findHakmilikPihakByNoPengenalan(ph.getNoPengenalan());
                    LOG.info("THIS IS PBLIST PASANGAN->" + pbListPasangan.size());
                    if (!pbListPasangan.isEmpty()) {
                        for (HakmilikPihakBerkepentingan hpb : pbListPasangan) {
//                            Hakmilik hak = new Hakmilik();
//                            hak = disLaporanTanahService.getHakmilikDAO().findById(hpb.getHakmilik().getIdHakmilik());
//                            if(hak != null){
                            if (hpb.getHakmilik() != null) {
                                listHakmilikPasangan.add(hpb.getHakmilik());
                            }
                        }
                    }
                }
            }
        }
        if (status.equals("edit")) {
            getContext().getRequest().setAttribute("view", Boolean.FALSE);
        } else {
            getContext().getRequest().setAttribute("view", Boolean.TRUE);
        }

        return new JSP(DisPermohonanPage.getTANAH_MILIK_VIEW()).addParameter("popup", "true");
    }

    public Resolution showFormTambahPemohonHubungan() {
        ctx = (etanahActionBeanContext) getContext();
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = new Permohonan();
        permohonan = (Permohonan) disLaporanTanahService.findObject(permohonan, new String[]{idPermohonan}, 0);
        idPemohon = ctx.getRequest().getParameter("idPemohon");
        if (StringUtils.isNotBlank(idPemohon)) {
            pemohon = new Pemohon();
            pemohon = (Pemohon) disLaporanTanahService.findObject(pemohon, new String[]{idPemohon}, 1);
            if (pemohon != null) {
                if (pemohon.getPihak() != null) {
                    pihak = pemohon.getPihak();
                }
            }
        }
        String type = ctx.getRequest().getParameter("type");
        String forwardJSP = new String();
        int typeNum = type.equals("tSuamiIsteri") ? 1
                : type.equals("tIbuBapa") ? 2
                : type.equals("tAnak") ? 3
                : type.equals("tPengarah") ? 4
                : 0;

        switch (typeNum) {
            case 1:
                pemohonHubungan = new PemohonHubungan();
                forwardJSP = DisPermohonanPage.getTAMBAH_SUAMI_ISTERI();
                break;
            case 2:
                pemohonHubungan = new PemohonHubungan();
                forwardJSP = DisPermohonanPage.getTAMBAH_IBUBAPA();
                break;
            case 3:
                pemohonHubungan = new PemohonHubungan();
                forwardJSP = DisPermohonanPage.getTAMBAH_ANAK();
                break;
            case 4:
                pihakPengarah = new PihakPengarah();
                forwardJSP = DisPermohonanPage.getTAMBAH_PENGARAH();
                break;
        }
        getContext().getRequest().setAttribute("status", "new");
        return new JSP(forwardJSP).addParameter("popup", "true");
    }

    public Resolution showFormKemaskiniPemohonHubungan() {
        ctx = (etanahActionBeanContext) getContext();
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = new Permohonan();
        permohonan = (Permohonan) disLaporanTanahService.findObject(permohonan, new String[]{idPermohonan}, 0);
        idPemohon = ctx.getRequest().getParameter("idPemohon");
        String type = ctx.getRequest().getParameter("type");
        idPemohonHubungan = ctx.getRequest().getParameter("idPemohonHubungan");
        String forwardJSP = new String();
        if (StringUtils.isNotBlank(idPemohon)) {
            pemohon = new Pemohon();
            pemohon = (Pemohon) disLaporanTanahService.findObject(pemohon, new String[]{idPemohon}, 1);
            if (pemohon != null) {
                if (pemohon.getPihak() != null) {
                    pihak = pemohon.getPihak();
                }
            }
        }
        int typeNum = type.equals("tSuamiIsteri") ? 1
                : type.equals("tIbuBapa") ? 2
                : type.equals("tAnak") ? 3
                : type.equals("tPengarah") ? 4
                : 0;

        switch (typeNum) {
            case 1:
                if (StringUtils.isNotBlank(idPemohonHubungan)) {
                    pemohonHubungan = new PemohonHubungan();
                    pemohonHubungan = (PemohonHubungan) disLaporanTanahService.findObject(pemohonHubungan, new String[]{idPemohonHubungan}, 2);
                }
                forwardJSP = DisPermohonanPage.getTAMBAH_SUAMI_ISTERI();
                break;
            case 2:
                if (StringUtils.isNotBlank(idPemohonHubungan)) {
                    pemohonHubungan = new PemohonHubungan();
                    pemohonHubungan = (PemohonHubungan) disLaporanTanahService.findObject(pemohonHubungan, new String[]{idPemohonHubungan}, 2);
                }
                forwardJSP = DisPermohonanPage.getTAMBAH_IBUBAPA();
                break;
            case 3:
                if (StringUtils.isNotBlank(idPemohonHubungan)) {
                    pemohonHubungan = new PemohonHubungan();
                    pemohonHubungan = (PemohonHubungan) disLaporanTanahService.findObject(pemohonHubungan, new String[]{idPemohonHubungan}, 2);
                }
                forwardJSP = DisPermohonanPage.getTAMBAH_ANAK();
                break;
            case 4:
                if (StringUtils.isNotBlank(idPemohonHubungan)) {
                    pihakPengarah = new PihakPengarah();
                    pihakPengarah = (PihakPengarah) disLaporanTanahService.findObject(pihakPengarah, new String[]{idPemohonHubungan}, 0);
                }
                forwardJSP = DisPermohonanPage.getTAMBAH_PENGARAH();
                break;
        }
        getContext().getRequest().setAttribute("status", "update");
        return new JSP(forwardJSP).addParameter("popup", "true");
    }

    public Resolution simpanPemohonHubungan() throws ParseException {
        ctx = (etanahActionBeanContext) getContext();
        Pengguna pguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = new Permohonan();
        permohonan = (Permohonan) disLaporanTanahService.findObject(permohonan, new String[]{idPermohonan}, 0);
        idPemohon = ctx.getRequest().getParameter("idPemohon");
        String type = ctx.getRequest().getParameter("type");
        String status = ctx.getRequest().getParameter("status");
        if (type.equals("tSuamiIsteri")) {
            if (pemohonHubunganSuamiIsteriValidation(permohonan.getKodUrusan().getKod())) {
                return new JSP(DisPermohonanPage.getTAMBAH_SUAMI_ISTERI()).addParameter("popup", "true");
            }
        } else if (type.equals("tIbuBapa")) {
            if (pemohonHubunganIbuBapaValidation(permohonan.getKodUrusan().getKod())) {
                return new JSP(DisPermohonanPage.getTAMBAH_IBUBAPA()).addParameter("popup", "true");
            }
        } else if (type.equals("tAnak")) {
            if (pemohonHubunganAnakValidation(permohonan.getKodUrusan().getKod())) {
                return new JSP(DisPermohonanPage.getTAMBAH_ANAK()).addParameter("popup", "true");
            }
        } else if (type.equals("tPengarah")) {
            if (pihakPengarahValidation(permohonan.getKodUrusan().getKod())) {
                return new JSP(DisPermohonanPage.getTAMBAH_PENGARAH()).addParameter("popup", "true");
            }
        }
        String forwardJSP = new String();
        if (StringUtils.isNotBlank(idPemohon)) {
            pemohon = new Pemohon();
            pemohon = (Pemohon) disLaporanTanahService.findObject(pemohon, new String[]{idPemohon}, 1);
            if (pemohon != null) {
                if (type.equals("tPengarah")) {
                    if (status.equals("new")) {
                        if (pihakPengarah != null) {
                            InfoAudit info = new InfoAudit();
                            info.setDimasukOleh(pguna);
                            info.setTarikhMasuk(new java.util.Date());

                            pihakPengarah.setPihak(pemohon.getPihak());
                            pihakPengarah.setInfoAudit(info);
                            pihakPengarah.setAktif('Y');
                            Pihak pihakTemp = pemohon.getPihak();
                            PihakPengarah pihakPengarahTemp = new PihakPengarah();
                            pihakPengarahTemp.setPihak(pihakTemp);
                            pihakPengarahTemp.setInfoAudit(info);
                            pihakPengarahTemp.setAktif('Y');
                            pihakPengarahTemp.setNama(pihakPengarah.getNama());
                            pihakPengarahTemp.setJenisPengenalan(pihakPengarah.getJenisPengenalan());
                            pihakPengarahTemp.setNoPengenalan(pihakPengarah.getNoPengenalan());
                            pihakPengarahTemp.setJumlahSaham(pihakPengarah.getJumlahSaham());
                            pihakPengarahTemp.setAlamat1(pihakPengarah.getAlamat1());
                            pihakPengarahTemp.setAlamat2(pihakPengarah.getAlamat2());
                            pihakPengarahTemp.setAlamat3(pihakPengarah.getAlamat3());
                            pihakPengarahTemp.setAlamat4(pihakPengarah.getAlamat4());
                            pihakPengarahTemp.setPoskod(pihakPengarah.getPoskod());
                            pihakPengarahTemp.setKodNegeri(pihakPengarah.getKodNegeri());
                            if ((add1 != null) && (!add1.equals(""))) {
                                pihakPengarahTemp.setAlamat1(pihakTemp.getAlamat1());
                                pihakPengarahTemp.setAlamat2(pihakTemp.getAlamat2());
                                pihakPengarahTemp.setAlamat3(pihakTemp.getAlamat3());
                                pihakPengarahTemp.setAlamat4(pihakTemp.getAlamat4());
                                pihakPengarahTemp.setPoskod(pihakTemp.getPoskod());
                                pihakPengarahTemp.setKodNegeri(pihakTemp.getNegeri());
                            }
                            disLaporanTanahService.getPelupusanService().simpanPemohonHbgn(pihakPengarahTemp, pihakTemp);
                        }
                    } else {
                        idPemohonHubungan = ctx.getRequest().getParameter("idPemohonHubungan");
                        if (pihakPengarah != null) {
                            InfoAudit info = new InfoAudit();
                            info.setDiKemaskiniOleh(pguna);
                            info.setTarikhKemaskini(new java.util.Date());
                            PihakPengarah pihakPengarahTemp = new PihakPengarah();
                            Pihak pihakTemp = pemohon.getPihak();
                            pihakPengarahTemp = (PihakPengarah) disLaporanTanahService.findObject(pihakPengarahTemp, new String[]{idPemohonHubungan}, 0);
                            pihakPengarahTemp.setPihak(pihakTemp);
                            pihakPengarahTemp.setInfoAudit(info);
                            pihakPengarahTemp.setNama(pihakPengarah.getNama());
                            pihakPengarahTemp.setJenisPengenalan(pihakPengarah.getJenisPengenalan());
                            pihakPengarahTemp.setNoPengenalan(pihakPengarah.getNoPengenalan());
                            pihakPengarahTemp.setJumlahSaham(pihakPengarah.getJumlahSaham());
                            pihakPengarahTemp.setAlamat1(pihakPengarah.getAlamat1());
                            pihakPengarahTemp.setAlamat2(pihakPengarah.getAlamat2());
                            pihakPengarahTemp.setAlamat3(pihakPengarah.getAlamat3());
                            pihakPengarahTemp.setAlamat4(pihakPengarah.getAlamat4());
                            pihakPengarahTemp.setPoskod(pihakPengarah.getPoskod());

                            KodNegeri kn = disLaporanTanahService.getKodNegeriDAO().findById(pihakPengarah.getKodNegeri().getKod());
                            pihakPengarahTemp.setKodNegeri(kn);
                            disLaporanTanahService.getPelupusanService().saveOrUpdate(pihakPengarahTemp, pihakTemp);
                        }
                    }
                    listPengarah = disLaporanTanahService.getPelupusanService().findPengarahByIDPihak(pemohon.getPihak().getIdPihak());
                } else {
                    if (status.equals("new")) {
                        if (pemohonHubungan != null) {
                            if (tarikhLahir != null) {
                                try {
                                    pemohonHubungan.setTarikhLahir(sdf.parse(tarikhLahir));
                                } catch (ParseException ex) {
                                    LOG.error("exception: " + ex.getMessage());
                                }
                            }
                            InfoAudit info = new InfoAudit();
                            info.setDimasukOleh(pguna);
                            info.setTarikhMasuk(new java.util.Date());
                            PemohonHubungan pemohonHubunganTemp = new PemohonHubungan();

                            pemohonHubunganTemp.setPemohon(pemohon);
                            if (type.equals("tAnak")) {
                                pemohonHubunganTemp.setKaitan("ANAK");
                                Pihak pihak1 = new Pihak();
                                pihak1 = pemohon.getPihak();
                                pemohonHubunganTemp.setNama(pemohonHubungan.getNama());


                                if (pemohonHubungan.getNoPengenalan() == null) {
                                    pemohonHubunganTemp.setNoPengenalan("000000000000");
                                } else if (pemohonHubungan.getNoPengenalan() != null) {
                                    pemohonHubunganTemp.setNoPengenalan(pemohonHubungan.getNoPengenalan());
                                }

                                if (pemohonHubungan.getUmur() == null) {
                                    pemohonHubunganTemp.setUmur(0);
                                } else if (pemohonHubungan.getUmur() != null) {
                                    pemohonHubunganTemp.setUmur(pemohonHubungan.getUmur());
                                }

                                pemohonHubunganTemp.setKodJantina(pemohonHubungan.getKodJantina());
                                pemohonHubunganTemp.setInstitusi(pemohonHubungan.getInstitusi());
                                pemohonHubunganTemp.setAlamat1(pihak1.getAlamat1());
                                pemohonHubunganTemp.setAlamat2(pihak1.getAlamat2());
                                pemohonHubunganTemp.setAlamat3(pihak1.getAlamat3());
                                pemohonHubunganTemp.setAlamat4(pihak1.getAlamat4());
                                pemohonHubunganTemp.setPoskod(pihak1.getPoskod());
                                pemohonHubunganTemp.setNegeri(pihak1.getNegeri());
                                pemohonHubunganTemp.setGaji(BigDecimal.ZERO);
                            } else {
                                pemohonHubunganTemp.setNama(pemohonHubungan.getNama());
                                pemohonHubunganTemp.setJenisPengenalan(pemohonHubungan.getJenisPengenalan());

                                if (pemohonHubungan.getNoPengenalan() == null) {
                                    pemohonHubunganTemp.setNoPengenalan("000000000000");
                                } else if (pemohonHubungan.getNoPengenalan() != null) {
                                    pemohonHubunganTemp.setNoPengenalan(pemohonHubungan.getNoPengenalan());
                                }

                                pemohonHubunganTemp.setTarikhLahir(pemohonHubungan.getTarikhLahir());
                                pemohonHubunganTemp.setTempatLahir(pemohonHubungan.getTempatLahir());
                                pemohonHubunganTemp.setPekerjaan(pemohonHubungan.getPekerjaan());
                                pemohonHubunganTemp.setUmur(pemohonHubungan.getUmur());
                                pemohonHubunganTemp.setTelahMeninggal(pemohonHubungan.getTelahMeninggal());
                                pemohonHubunganTemp.setTarikhMati(pemohonHubungan.getTarikhMati());

                                if (pemohonHubungan.getGaji() == null) {
                                    pemohonHubunganTemp.setGaji(BigDecimal.ZERO);
                                } else if (pemohonHubungan.getGaji() != null) {
                                    pemohonHubunganTemp.setGaji(pemohonHubungan.getGaji());
                                }

                                pemohonHubunganTemp.setKaitan(pemohonHubungan.getKaitan());
                                pemohonHubunganTemp.setWarganegara(pemohonHubungan.getWarganegara());
                            }
                            pemohonHubunganTemp.setInfoAudit(info);
                            pemohonHubunganTemp.setCawangan(permohonan.getCawangan());
                            disLaporanTanahService.getPelupusanService().simpanPemohonHbgn(pemohonHubunganTemp);

                            if ((add1 != null) && (!add1.equals(""))) {
                                Pihak pihak1 = new Pihak();
                                pihak1 = pemohon.getPihak();
                                pemohonHubunganTemp.setAlamat1(pihak1.getAlamat1());
                                pemohonHubunganTemp.setAlamat2(pihak1.getAlamat2());
                                pemohonHubunganTemp.setAlamat3(pihak1.getAlamat3());
                                pemohonHubunganTemp.setAlamat4(pihak1.getAlamat4());
                                pemohonHubunganTemp.setPoskod(pihak1.getPoskod());
                                pemohonHubunganTemp.setNegeri(pihak1.getNegeri());
                                disLaporanTanahService.getPelupusanService().simpanPemohonHbgn(pemohonHubunganTemp);
                            }
                        }
                    } else {
                        idPemohonHubungan = ctx.getRequest().getParameter("idPemohonHubungan");
                        if (pemohonHubungan != null) {
                            InfoAudit info = new InfoAudit();
                            info.setDiKemaskiniOleh(pguna);
                            info.setTarikhKemaskini(new java.util.Date());
                            PemohonHubungan pemohonHubunganTemp = new PemohonHubungan();
                            pemohonHubunganTemp = (PemohonHubungan) disLaporanTanahService.findObject(pemohonHubunganTemp, new String[]{idPemohonHubungan}, 2);
                            pemohonHubunganTemp.setNama(pemohonHubungan.getNama());
                            pemohonHubunganTemp.setTarikhLahir(pemohonHubungan.getTarikhLahir());
                            pemohonHubunganTemp.setTempatLahir(pemohonHubungan.getTempatLahir());
                            pemohonHubunganTemp.setPekerjaan(pemohonHubungan.getPekerjaan());
                            pemohonHubunganTemp.setUmur(pemohonHubungan.getUmur());
                            pemohonHubunganTemp.setTelahMeninggal(pemohonHubungan.getTelahMeninggal());
                            pemohonHubunganTemp.setTarikhMati(pemohonHubungan.getTarikhMati());

                            if (pemohonHubungan.getNoPengenalan() == null) {
                                pemohonHubunganTemp.setNoPengenalan("000000000000");
                            } else if (pemohonHubungan.getNoPengenalan() != null) {
                                pemohonHubunganTemp.setNoPengenalan(pemohonHubungan.getNoPengenalan());
                            }

                            pemohonHubunganTemp.setKaitan(pemohonHubungan.getKaitan());

                            if (type.equals("tAnak")) {
                                pemohonHubunganTemp.setKaitan("ANAK");

                                if (pemohonHubungan.getNoPengenalan() == null) {
                                    pemohonHubunganTemp.setNoPengenalan("000000000000");
                                } else if (pemohonHubungan.getNoPengenalan() != null) {
                                    pemohonHubunganTemp.setNoPengenalan(pemohonHubungan.getNoPengenalan());
                                }

                                if (pemohonHubungan.getUmur() == null) {
                                    pemohonHubunganTemp.setUmur(0);
                                } else if (pemohonHubungan.getUmur() != null) {
                                    pemohonHubunganTemp.setUmur(pemohonHubungan.getUmur());
                                }


                                pemohonHubunganTemp.setKodJantina(pemohonHubungan.getKodJantina());
                                pemohonHubunganTemp.setGaji(BigDecimal.ZERO);
                                pemohonHubunganTemp.setInstitusi(pemohonHubungan.getInstitusi());
                            }

                            if (!type.equals("tAnak")) {
                                pemohonHubunganTemp.setJenisPengenalan(pemohonHubungan.getJenisPengenalan());

                                if (pemohonHubungan.getGaji() == null) {
                                    pemohonHubunganTemp.setGaji(BigDecimal.ZERO);
                                } else if (pemohonHubungan.getGaji() != null) {
                                    pemohonHubunganTemp.setGaji(pemohonHubungan.getGaji());
                                }

                                pemohonHubunganTemp.setAlamat1(pemohonHubungan.getAlamat1());
                                pemohonHubunganTemp.setAlamat2(pemohonHubungan.getAlamat2());
                                pemohonHubunganTemp.setAlamat3(pemohonHubungan.getAlamat3());
                                pemohonHubunganTemp.setAlamat4(pemohonHubungan.getAlamat4());
                                pemohonHubunganTemp.setPoskod(pemohonHubungan.getPoskod());
                                pemohonHubunganTemp.setWarganegara(pemohonHubungan.getWarganegara());
                                KodNegeri kn = disLaporanTanahService.getKodNegeriDAO().findById(pemohonHubungan.getNegeri().getKod());
                                pemohonHubunganTemp.setNegeri(kn);
                            }

                            pemohonHubunganTemp.setInfoAudit(info);
                            disLaporanTanahService.getPelupusanService().saveOrUpdateHbgn(pemohonHubunganTemp);
                        }
                    }
                    listHubunganSuamiIsteri = disLaporanTanahService.getPelupusanService().findHubunganByIDPemohon(pemohon.getIdPemohon());
                    listHubunganIbuBapa = disLaporanTanahService.getPelupusanService().findHubunganByIDPemohonBAPA(pemohon.getIdPemohon());
                    listHubunganAnak = disLaporanTanahService.getPelupusanService().findHubunganByIDANAK(pemohon.getIdPemohon());

                }
            }
        }
        int typeNum = type.equals("tSuamiIsteri") ? 1
                : type.equals("tIbuBapa") ? 1
                : type.equals("tAnak") ? 1
                : type.equals("tPengarah") ? 2
                : 0;

        switch (typeNum) {
            case 1:
                forwardJSP = DisPermohonanPage.getKELUARGA_EDIT();
                break;
            case 2:
                forwardJSP = DisPermohonanPage.getPENGARAH_EDIT();
                break;
        }

        return new JSP(forwardJSP).addParameter("popup", "true");
    }

    public boolean pemohonHubunganIbuBapaValidation(String kodUrusan) {
        boolean flag = false;

        if (pemohonHubungan.getTelahMeninggal() == null) {
            flag = true;
            addSimpleError("Sila Masukkan Status Kematian");
        } else if (pemohonHubungan.getTelahMeninggal().equals("T")) {
            if ((pemohonHubungan.getNama() == null) || (pemohonHubungan.getNama().trim().equals(""))) {
                flag = true;
                addSimpleError("Sila Masukkan Nama");

            } else if ((pemohonHubungan.getJenisPengenalan() == null) || (pemohonHubungan.getJenisPengenalan().getKod().equals("0"))) {
                flag = true;
                addSimpleError("Sila Masukkan Jenis Pengenalan");

            } else if ((pemohonHubungan.getNoPengenalan() == null) || (pemohonHubungan.getNoPengenalan().trim().equals(""))) {
                flag = true;
                addSimpleError("Sila Masukkan Nombor Pengenalan");
            } else if ((pemohonHubungan.getKaitan().equals("0")) || (pemohonHubungan.getKaitan().trim().equals(""))) {
                flag = true;
                addSimpleError("Sila Masukkan Kaitan");

            } else if (pemohonHubungan.getTarikhLahir() == null) {
                flag = true;
                addSimpleError("Sila Masukkan Tarikh Lahir");

            } else if ((pemohonHubungan.getWarganegara() == null) || (pemohonHubungan.getWarganegara().getKod().equals("0"))) {
                flag = true;
                addSimpleError("Sila Masukkan Warganegara");
            } else if (add1 == null) {
                if ((pemohonHubungan.getAlamat1() == null) || (pemohonHubungan.getAlamat1().trim().equals(""))) {
                    flag = true;
                    addSimpleError("Sila Masukkan Alamat");
                } else if ((pemohonHubungan.getPoskod() == null) || (pemohonHubungan.getPoskod().trim().equals(""))) {
                    flag = true;
                    addSimpleError("Sila Masukkan Poskod");
                } else if ((pemohonHubungan.getNegeri() == null) || (pemohonHubungan.getNegeri().getKod().equals("0"))) {
                    flag = true;
                    addSimpleError("Sila Masukkan Negeri");
                }
            }
        } else if (pemohonHubungan.getTelahMeninggal().equals("Y")) {
            if ((pemohonHubungan.getNama() == null) || (pemohonHubungan.getNama().trim().equals(""))) {
                flag = true;
                addSimpleError("Sila Masukkan Nama");
            } else if ((pemohonHubungan.getKaitan().equals("0")) || (pemohonHubungan.getKaitan().trim().equals(""))) {
                flag = true;
                addSimpleError("Sila Masukkan Kaitan");
            } else if (pemohonHubungan.getTarikhMati() == null) {
                flag = true;
                addSimpleError("Sila Masukkan Tarikh Meninggal");

            } else if ((pemohonHubungan.getWarganegara() == null) || (pemohonHubungan.getWarganegara().getKod().equals("0"))) {
                flag = true;
                addSimpleError("Sila Masukkan Warganegara");
            }
        }
        return flag;
    }

    public boolean pemohonHubunganSuamiIsteriValidation(String kodUrusan) {
        boolean flag = false;

        if ((pemohonHubungan.getNama() == null) || (pemohonHubungan.getNama().trim().equals(""))) {
            flag = true;
            addSimpleError("Sila Masukkan Nama");

        } else if ((pemohonHubungan.getJenisPengenalan() == null) || (pemohonHubungan.getJenisPengenalan().getKod().equals("0"))) {
            flag = true;
            addSimpleError("Sila Masukkan Jenis Pengenalan");

        } else if ((pemohonHubungan.getNoPengenalan() == null) || (pemohonHubungan.getNoPengenalan().trim().equals(""))) {
            flag = true;
            addSimpleError("Sila Masukkan Nombor Pengenalan");
        } else if ((pemohonHubungan.getKaitan().equals("0")) || (pemohonHubungan.getKaitan().trim().equals(""))) {
            flag = true;
            addSimpleError("Sila Masukkan Kaitan");

        } else if (pemohonHubungan.getTarikhLahir() == null) {
            flag = true;
            addSimpleError("Sila Masukkan Tarikh Lahir");

        } else if ((pemohonHubungan.getWarganegara() == null) || (pemohonHubungan.getWarganegara().getKod().equals("0"))) {
            flag = true;
            addSimpleError("Sila Masukkan Warganegara");
        } else if (add1 == null) {
            if ((pemohonHubungan.getAlamat1() == null) || (pemohonHubungan.getAlamat1().trim().equals(""))) {
                flag = true;
                addSimpleError("Sila Masukkan Alamat");
            } else if ((pemohonHubungan.getPoskod() == null) || (pemohonHubungan.getPoskod().trim().equals(""))) {
                flag = true;
                addSimpleError("Sila Masukkan Poskod");
            } else if ((pemohonHubungan.getNegeri() == null) || (pemohonHubungan.getNegeri().getKod().equals("0"))) {
                flag = true;
                addSimpleError("Sila Masukkan Negeri");
            }
        }
        return flag;
    }

    public boolean pemohonHubunganAnakValidation(String kodUrusan) {
        boolean flag = false;

        if ((pemohonHubungan.getNama() == null) || (pemohonHubungan.getNama().trim().equals(""))) {
            flag = true;
            addSimpleError("Tiada Data Yang Dimasukkan" + "<br>" + "Butang Kembali Mungkin Membantu");
        }

        return flag;
    }

    public boolean pihakPengarahValidation(String kodUrusan) {
        boolean flag = false;
        if ((pihakPengarah.getNama() == null) || (pihakPengarah.getNama().trim().equals(""))) {
            flag = true;
            addSimpleError("Sila Masukkan Nama");
        } else if ((pihakPengarah.getJenisPengenalan() == null) || (pihakPengarah.getJenisPengenalan().getKod().equals("0"))) {
            flag = true;
            addSimpleError("Sila Masukkan Jenis Pengenalan");
        } else if ((pihakPengarah.getNoPengenalan() == null) || (pihakPengarah.getNoPengenalan().trim().equals(""))) {
            flag = true;
            addSimpleError("Sila Masukkan Nombor Pengenalan");
        }
        if (add1 == null) {
            if ((pihakPengarah.getAlamat1() == null) || (pihakPengarah.getAlamat1().trim().equals(""))) {
                flag = true;
                addSimpleError("Sila Masukkan Alamat");
            } else if ((pihakPengarah.getPoskod() == null) || (pihakPengarah.getPoskod().trim().equals(""))) {
                flag = true;
                addSimpleError("Sila Masukkan Poskod");
            } else if ((pihakPengarah.getKodNegeri() == null) || (pihakPengarah.getKodNegeri().getKod().equals("0"))) {
                flag = true;
                addSimpleError("Sila Masukkan Negeri");
            }
        }
        return flag;
    }

    public DisPemohonController getDisPemohonController() {
        return disPemohonController;
    }

    public void setDisPemohonController(DisPemohonController disPemohonController) {
        this.disPemohonController = disPemohonController;
    }

    public Pemohon getPemohon() {
        return pemohon;
    }

    public void setPemohon(Pemohon pemohon) {
        this.pemohon = pemohon;
    }

    public Pihak getPihak() {
        return pihak;
    }

    public void setPihak(Pihak pihak) {
        this.pihak = pihak;
    }

    public Permohonan getPermohonan() {
        return permohonan;
    }

    public void setPermohonan(Permohonan permohonan) {
        this.permohonan = permohonan;
    }

    public List<Pemohon> getListPemohon() {
        return listPemohon;
    }

    public void setListPemohon(List<Pemohon> listPemohon) {
        this.listPemohon = listPemohon;
    }

    public List<PemohonHubungan> getListHubunganIbuBapa() {
        return listHubunganIbuBapa;
    }

    public void setListHubunganIbuBapa(List<PemohonHubungan> listHubunganIbuBapa) {
        this.listHubunganIbuBapa = listHubunganIbuBapa;
    }

    public List<PemohonHubungan> getListHubunganAnak() {
        return listHubunganAnak;
    }

    public void setListHubunganAnak(List<PemohonHubungan> listHubunganAnak) {
        this.listHubunganAnak = listHubunganAnak;
    }

    public List<PihakPengarah> getListPengarah() {
        return listPengarah;
    }

    public void setListPengarah(List<PihakPengarah> listPengarah) {
        this.listPengarah = listPengarah;
    }

    public List<HakmilikPermohonan> getSenaraiHakmilik() {
        return senaraiHakmilik;
    }

    public void setSenaraiHakmilik(List<HakmilikPermohonan> senaraiHakmilik) {
        this.senaraiHakmilik = senaraiHakmilik;
    }

    public List<HakmilikPihakBerkepentingan> getSenaraiTuanTanah() {
        return senaraiTuanTanah;
    }

    public void setSenaraiTuanTanah(List<HakmilikPihakBerkepentingan> senaraiTuanTanah) {
        this.senaraiTuanTanah = senaraiTuanTanah;
    }

    public String getIdPermohonan() {
        return idPermohonan;
    }

    public void setIdPermohonan(String idPermohonan) {
        this.idPermohonan = idPermohonan;
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

    public int getSizeKod() {
        return sizeKod;
    }

    public void setSizeKod(int sizeKod) {
        this.sizeKod = sizeKod;
    }

    public String getIdPihak() {
        return idPihak;
    }

    public void setIdPihak(String idPihak) {
        this.idPihak = idPihak;
    }

    public List<Hakmilik> getListHakmilik() {
        return listHakmilik;
    }

    public void setListHakmilik(List<Hakmilik> listHakmilik) {
        this.listHakmilik = listHakmilik;
    }

    public List<KodBangsa> getSenaraiKodBangsa() {
        return senaraiKodBangsa;
    }

    public void setSenaraiKodBangsa(List<KodBangsa> senaraiKodBangsa) {
        this.senaraiKodBangsa = senaraiKodBangsa;
    }

    public PihakAlamatTamb getPihakAlamatTambahan() {
        return pihakAlamatTambahan;
    }

    public void setPihakAlamatTambahan(PihakAlamatTamb pihakAlamatTambahan) {
        this.pihakAlamatTambahan = pihakAlamatTambahan;
    }

    public String getTarikhLahir() {
        return tarikhLahir;
    }

    public void setTarikhLahir(String tarikhLahir) {
        this.tarikhLahir = tarikhLahir;
    }

    public List<PemohonHubungan> getListHubunganSuamiIsteri() {
        return listHubunganSuamiIsteri;
    }

    public void setListHubunganSuamiIsteri(List<PemohonHubungan> listHubunganSuamiIsteri) {
        this.listHubunganSuamiIsteri = listHubunganSuamiIsteri;
    }

    public String getIdPemohon() {
        return idPemohon;
    }

    public void setIdPemohon(String idPemohon) {
        this.idPemohon = idPemohon;
    }

    public PemohonHubungan getPemohonHubungan() {
        return pemohonHubungan;
    }

    public void setPemohonHubungan(PemohonHubungan pemohonHubungan) {
        this.pemohonHubungan = pemohonHubungan;
    }

    public String getAdd1() {
        return add1;
    }

    public void setAdd1(String add1) {
        this.add1 = add1;
    }

    public String getIdPemohonHubungan() {
        return idPemohonHubungan;
    }

    public void setIdPemohonHubungan(String idPemohonHubungan) {
        this.idPemohonHubungan = idPemohonHubungan;
    }

    public PihakPengarah getPihakPengarah() {
        return pihakPengarah;
    }

    public void setPihakPengarah(PihakPengarah pihakPengarah) {
        this.pihakPengarah = pihakPengarah;
    }

    public PermohonanKelompok getPermohonanKelompok() {
        return permohonanKelompok;
    }

    public void setPermohonanKelompok(PermohonanKelompok permohonanKelompok) {
        this.permohonanKelompok = permohonanKelompok;
    }

    public List<PermohonanKelompok> getSenaraiKelompok() {
        return senaraiKelompok;
    }

    public void setSenaraiKelompok(List<PermohonanKelompok> senaraiKelompok) {
        this.senaraiKelompok = senaraiKelompok;
    }

    public List<Hakmilik> getListHakmilikPasangan() {
        return listHakmilikPasangan;
    }

    public void setListHakmilikPasangan(List<Hakmilik> listHakmilikPasangan) {
        this.listHakmilikPasangan = listHakmilikPasangan;
    }

    public PermohonanBahanBatuan getPermohonanBahanBatuan() {
        return permohonanBahanBatuan;
    }

    public void setPermohonanBahanBatuan(PermohonanBahanBatuan permohonanBahanBatuan) {
        this.permohonanBahanBatuan = permohonanBahanBatuan;
    }
}
