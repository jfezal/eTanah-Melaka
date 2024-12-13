package etanah.view.penguatkuasaan;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.dao.BarangRampasanDAO;
import etanah.dao.KodCawanganDAO;
import etanah.dao.KodDokumenDAO;
import etanah.dao.KodPerananDAO;
import etanah.dao.OperasiBarangPenguatkuasaanDAO;
import etanah.dao.OperasiPenguatkuasaanDAO;
import etanah.dao.PenggunaDAO;
import etanah.dao.PenyerahBarangOperasiDAO;
import etanah.dao.PermohonanDAO;
import etanah.dao.PermohonanTandatanganDokumenDAO;
import etanah.model.AduanOrangKenaSyak;
import etanah.model.BarangRampasan;
import etanah.model.InfoAudit;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.service.EnforceService;
import etanah.view.etanahActionBeanContext;
import java.util.Date;
import java.util.List;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import org.apache.log4j.Logger;
import etanah.model.KodDokumen;
import etanah.model.OperasiBarangPenguatkuasaan;
import etanah.model.OperasiPenguatkuasaan;
import etanah.model.PenyerahBarangOperasi;
import etanah.model.PermohonanTandatanganDokumen;
import etanah.service.common.EnforcementService;
import net.sourceforge.stripes.action.RedirectResolution;
import net.sourceforge.stripes.action.StreamingResolution;
import org.apache.commons.lang.StringUtils;
import org.hibernate.Session;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author latifah.iskak
 */
@UrlBinding("/penguatkuasaan/serahan_barang_rampasan")
public class SerahanBarangRampasanActionBean extends AbleActionBean {

    @Inject
    EnforceService enforceService;
    @Inject
    EnforcementService enforcementService;
    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    KodDokumenDAO kodDokumenDAO;
    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;
    @Inject
    KodPerananDAO kodPerananDAO;
    @Inject
    PenggunaDAO penggunaDAO;
    @Inject
    KodCawanganDAO kodCawanganDAO;
    @Inject
    private etanah.Configuration conf;
    @Inject
    OperasiBarangPenguatkuasaanDAO operasiBarangPenguatkuasaanDAO;
    @Inject
    PenyerahBarangOperasiDAO penyerahBarangOperasiDAO;
    @Inject
    BarangRampasanDAO barangRampasanDAO;
    @Inject
    OperasiPenguatkuasaanDAO operasiPenguatkuasaanDAO;
    @Inject
    PermohonanTandatanganDokumenDAO permohonanTandatanganDokumenDAO;
    private static final Logger LOG = Logger.getLogger(SerahanBarangRampasanActionBean.class);
    private KodDokumen kodDokumen;
    private Permohonan permohonan;
    private String idPermohonan;
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm aaa");
    SimpleDateFormat sdf1 = new SimpleDateFormat("dd/MM/yyyy hh:mm aaa");
    SimpleDateFormat minitFormat = new SimpleDateFormat("mm");
    SimpleDateFormat jamFormat = new SimpleDateFormat("hh");
    SimpleDateFormat ampmFormat = new SimpleDateFormat("aaa");
    private String recordCount;
    private int rowCount;
    private Pengguna pengguna;
    private List<Pengguna> senaraiPengguna;
    private PermohonanTandatanganDokumen mohonTandatanganDokumen;
    private String diTandatanganOleh;
    private List<OperasiPenguatkuasaan> listOp;
    private Boolean opFlag = false;
    private String idOperasi;
    private String noPengenalanPenyerah;
    private String pekerjaanPenyerah;
    private String tempatKerjaPenyerah;
    private String noSerahan;
    private List<OperasiBarangPenguatkuasaan> senaraiBaranganOperasi;
    private List<BarangRampasan> senaraiBaranganRampasan;
    private List<BarangRampasan> senaraiBaranganRampasanForSerahan;
    private String idPenyerah;
    private PenyerahBarangOperasi penyerahBarangOperasi;
    private OperasiBarangPenguatkuasaan operasiBarangPenguatkuasaan;
    private String namaBarangan;
    private String jenisBarangan;
    private String modelBarangan;
    private String kuantitiBarangan;
    private String penggunaSerahan;
    private String pilihBarang;
    private BarangRampasan barang;
    private OperasiPenguatkuasaan operasiPenguatkuasaan;
    private String pilihBarangTemp;
    private List<Pengguna> senaraiTandatanganPengguna;
    private String idPenyerahBarangOperasi;
    private String idBarangOperasi;
    private String statusOperation;
    private OperasiPenguatkuasaan operasiPenguatkuasaanIP;
    private List<AduanOrangKenaSyak> senaraiOksIp;
    private Boolean statusIP = Boolean.FALSE;
    private String jenisBarang;

    @DefaultHandler
    public Resolution showForm() {
        if (opFlag == false) {
            addSimpleError("Sila isikan maklumat laporan operasi terlebih dahulu");
        }
        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        return new JSP("penguatkuasaan/serahan_barang_rampasan.jsp").addParameter("tab", "true");
    }

    public Resolution showForm2() {
        getContext().getRequest().setAttribute("view", Boolean.TRUE);
        return new JSP("penguatkuasaan/serahan_barang_rampasan.jsp").addParameter("tab", "true");
    }

    public Resolution barangOperasi() {
        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        return new JSP("penguatkuasaan/maklumat_barang_operasi.jsp").addParameter("tab", "true");
    }

    public Resolution viewBarangOperasi() {
        getContext().getRequest().setAttribute("view", Boolean.TRUE);
        return new JSP("penguatkuasaan/maklumat_barang_operasi.jsp").addParameter("tab", "true");
    }

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {
        System.out.println("--------------rehydrate--------------");
        pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");

        if (idPermohonan != null) {
            permohonan = permohonanDAO.findById(idPermohonan);

            ArrayList kumpulanBpel = new ArrayList<String>();
            if (permohonan.getCawangan().getKod().equalsIgnoreCase("00")) { //00 = PTG MELAKA
                kumpulanBpel.add("pptptgkuasa"); // PPT
                kumpulanBpel.add("pptkptgkuasa"); //PPTK
            } else { // for 01 = melaka tengah, 02 = jasin & 03 = alor gajah
//                kumpulanBpel.add("pptptdkuasa"); // PPT
                kumpulanBpel.add("PPTanah");
                kumpulanBpel.add("pptkptdkuasa"); //PPTK
            }
            senaraiPengguna = enforcementService.findUserKumpBpel(kumpulanBpel, permohonan.getCawangan().getKod());


            ArrayList kumpulanBpelTt = new ArrayList<String>();
            //'PPTK','PPTT','PPTD','PTD','PUU'
            if (permohonan.getCawangan().getKod().equalsIgnoreCase("00")) { //00 = PTG MELAKA
                kumpulanBpelTt.add("pptkptgkuasa"); //PPTK
                kumpulanBpelTt.add("ppttptgkuasa"); //PPTT
                kumpulanBpelTt.add("puuptg"); // PUU
            } else { // for 01 = melaka tengah, 02 = jasin & 03 = alor gajah
                kumpulanBpelTt.add("pptkptdkuasa"); //PPTK
                kumpulanBpelTt.add("ppttptdkuasa"); //PPTT
                kumpulanBpelTt.add("pptd"); // PPTD
                kumpulanBpelTt.add("ptd"); // PTD
            }

            senaraiTandatanganPengguna = enforcementService.findUserKumpBpel(kumpulanBpelTt, permohonan.getCawangan().getKod());

            senaraiBaranganOperasi = new ArrayList<OperasiBarangPenguatkuasaan>();
            senaraiBaranganRampasan = new ArrayList<BarangRampasan>();

            senaraiBaranganRampasanForSerahan = new ArrayList<BarangRampasan>();

            senaraiBaranganRampasanForSerahan = enforceService.findBarangSerahan(permohonan.getIdPermohonan());
            System.out.println("size senaraiBaranganRampasanForSerahan : " + senaraiBaranganRampasanForSerahan.size());

            if ("04".equals(conf.getProperty("kodNegeri"))) {
                if (permohonan.getKodUrusan().getKod().equalsIgnoreCase("425") || permohonan.getKodUrusan().getKod().equalsIgnoreCase("425A") || permohonan.getKodUrusan().getKod().equalsIgnoreCase("426") || permohonan.getKodUrusan().getKod().equalsIgnoreCase("999")) {
                    listOp = enforceService.findListLaporanOperasi(idPermohonan);
                    LOG.info("listOp size : " + listOp.size());
                    if (listOp.size() != 0) {
                        LOG.info("opFlag true");
                        opFlag = true;
                    } else {
                        if (permohonan.getPermohonanSebelum() != null) {
                            LOG.info("::::::: USING ID PERMOHONAN SEBELUM :::::::::" + permohonan.getPermohonanSebelum().getIdPermohonan());
                            listOp = enforceService.findListLaporanOperasi(permohonan.getPermohonanSebelum().getIdPermohonan());
                            opFlag = true;

                            senaraiOksIp = enforceService.getListAduanOrangkenaSyak(permohonan.getIdPermohonan());

                            LOG.info("size senaraiOksIp : " + senaraiOksIp.size());

                            if (senaraiOksIp.size() != 0) {
                                Long idOperasi = senaraiOksIp.get(0).getOperasiPenguatkuasaan().getIdOperasi();
                                System.out.println("id operasi : " + idOperasi);

                                listOp = enforcementService.findListLaporanOperasi(idOperasi);
                                statusIP = true;

                            }
                        }
                    }
                }
            }
        }

    }

    public Resolution addRecord() {
        idOperasi = getContext().getRequest().getParameter("idOp");
        statusOperation = getContext().getRequest().getParameter("status");
        LOG.info("::add record - id OP : " + idOperasi);
        findData(idOperasi, null);
        if (StringUtils.isNotBlank(statusOperation)) {
            if (statusOperation.equalsIgnoreCase("edit")) {
                getContext().getRequest().setAttribute("edit", Boolean.TRUE);
            } else {
                getContext().getRequest().setAttribute("view", Boolean.TRUE);
            }
        }
        return new JSP("penguatkuasaan/popup_tambah_penyerah.jsp").addParameter("popup", "true");
    }

    public Resolution addBarangOperasi() {
        idOperasi = getContext().getRequest().getParameter("idOp");
        LOG.info("::add record - id OP : " + idOperasi);
        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        return new JSP("penguatkuasaan/popup_tambah_barang_operasi.jsp").addParameter("popup", "true");
    }

    public Resolution editBarangOperasi() {
        idOperasi = getContext().getRequest().getParameter("idOp");
        idBarangOperasi = getContext().getRequest().getParameter("idBarangOperasi");
        statusOperation = getContext().getRequest().getParameter("status");
        LOG.info("(" + statusOperation + "):: - id OP : " + idOperasi + "::: idBarangOperasi - : " + idBarangOperasi);
        if (StringUtils.isNotBlank(idBarangOperasi)) {
            operasiBarangPenguatkuasaan = operasiBarangPenguatkuasaanDAO.findById(Long.parseLong(idBarangOperasi));
            if (operasiBarangPenguatkuasaan != null) {
                namaBarangan = operasiBarangPenguatkuasaan.getNama();
                jenisBarangan = operasiBarangPenguatkuasaan.getJenis();
                modelBarangan = operasiBarangPenguatkuasaan.getModel();
                kuantitiBarangan = Integer.toString(operasiBarangPenguatkuasaan.getKntt());
                jenisBarang = Character.toString(operasiBarangPenguatkuasaan.getKategoriBarangOperasi());
                if (operasiBarangPenguatkuasaan.getPengguna() != null) {
                    penggunaSerahan = operasiBarangPenguatkuasaan.getPengguna().getIdPengguna();
                }
            }
        }
        if (StringUtils.isNotBlank(statusOperation)) {
            if (statusOperation.equalsIgnoreCase("edit")) {
                getContext().getRequest().setAttribute("edit", Boolean.TRUE);
            } else {
                getContext().getRequest().setAttribute("view", Boolean.TRUE);
            }
        }
        return new JSP("penguatkuasaan/popup_tambah_barang_operasi.jsp").addParameter("popup", "true");
    }

    public Resolution editRecord() {
        idPenyerahBarangOperasi = getContext().getRequest().getParameter("idPenyerahBarangOperasi");
        idOperasi = getContext().getRequest().getParameter("idOp");
        statusOperation = getContext().getRequest().getParameter("status");
        LOG.info("::editRecord : " + idPenyerahBarangOperasi);

        if (StringUtils.isNotBlank(idPenyerahBarangOperasi)) {
            penyerahBarangOperasi = penyerahBarangOperasiDAO.findById(Long.parseLong(idPenyerahBarangOperasi));

            if (penyerahBarangOperasi != null) {
                idPenyerah = penyerahBarangOperasi.getPengguna().getIdPengguna();
                noPengenalanPenyerah = penyerahBarangOperasi.getPengguna().getNoPengenalan();
                pekerjaanPenyerah = penyerahBarangOperasi.getPengguna().getJawatan();
                noSerahan = penyerahBarangOperasi.getNoPerserahan();

                if (penyerahBarangOperasi.getPengguna().getKodCawangan() != null) {
                    if (penyerahBarangOperasi.getPengguna().getKodCawangan().getAlamat() != null) {
                        tempatKerjaPenyerah = penyerahBarangOperasi.getPengguna().getKodCawangan().getAlamat().getAlamat1();
                    }
                }

                if (penyerahBarangOperasi.getDokumenTandatangan() != null) {
                    mohonTandatanganDokumen = permohonanTandatanganDokumenDAO.findById(penyerahBarangOperasi.getDokumenTandatangan().getIdDokTt());
                    if (mohonTandatanganDokumen != null) {
                        diTandatanganOleh = mohonTandatanganDokumen.getDiTandatangan();
                    }
                }

            }

            findData(idOperasi, idPenyerahBarangOperasi);
        }

        if (StringUtils.isNotBlank(statusOperation)) {
            if (statusOperation.equalsIgnoreCase("edit")) {
                getContext().getRequest().setAttribute("edit", Boolean.TRUE);
            } else {
                getContext().getRequest().setAttribute("view", Boolean.TRUE);
            }
        }
        return new JSP("penguatkuasaan/popup_tambah_penyerah.jsp").addParameter("popup", "true");
    }

    public void findData(String id, String idPenyerahBarangOperasi) {
        LOG.info("::findData - id OP : " + id);
        if (StringUtils.isNotBlank(idPenyerahBarangOperasi)) {
            senaraiBaranganRampasan = enforceService.findBarangSerahanByIdSerahan(permohonan.getIdPermohonan(), Long.parseLong(idPenyerahBarangOperasi));
        } else {
            senaraiBaranganRampasan = enforceService.findBarangSerahan(permohonan.getIdPermohonan(), Long.parseLong(id));
        }
        if (statusIP == true) {
            LOG.info("statusIP : " + statusIP);
            senaraiBaranganRampasan = enforceService.findBarangSerahanForView(permohonan.getIdPermohonan(), Long.parseLong(idPenyerahBarangOperasi));
        }
        LOG.info("size senaraiBaranganRampasan :" + senaraiBaranganRampasan.size());
    }

    public Resolution findPengguna() {
        String id = getContext().getRequest().getParameter("id");
        Pengguna p = penggunaDAO.findById(id);

        if (p != null) {
            noPengenalanPenyerah = p.getNoPengenalan();
            pekerjaanPenyerah = p.getJawatan();

            if (p.getKodCawangan() != null) {
                if (p.getKodCawangan().getAlamat() != null) {
                    tempatKerjaPenyerah = p.getKodCawangan().getAlamat().getAlamat1();
                }
            }

            System.out.println("noPengenalanPenyerah : " + noPengenalanPenyerah);
            System.out.println("pekerjaanPenyerah : " + pekerjaanPenyerah);
            System.out.println("tempatKerjaPenyerah : " + tempatKerjaPenyerah);

        }
        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        return new JSP("penguatkuasaan/popup_tambah_penyerah.jsp").addParameter("popup", "true");
    }

    public Resolution simpan() throws ParseException {

        InfoAudit ia = new InfoAudit();
        ia.setDimasukOleh(pengguna);
        ia.setTarikhMasuk(new java.util.Date());


        idOperasi = getContext().getRequest().getParameter("idOperasi");
        LOG.info("::simpan - id OP : " + idOperasi);
        if (StringUtils.isNotBlank(idOperasi)) {
            operasiPenguatkuasaan = enforceService.findByIdOp(Long.parseLong(idOperasi));
            findData(idOperasi, null);
        }

        //save to table PenyerahBarangOperasi(penyerah_brg_op)
        if (StringUtils.isNotBlank(idPenyerahBarangOperasi)) {
            penyerahBarangOperasi = penyerahBarangOperasiDAO.findById(Long.parseLong(idPenyerahBarangOperasi));
            findData(idOperasi, idPenyerahBarangOperasi);
        }

        if (penyerahBarangOperasi != null) {
            ia = penyerahBarangOperasi.getInfoAudit();
            ia.setDiKemaskiniOleh(pengguna);
            ia.setTarikhKemaskini(new java.util.Date());
        } else {
            penyerahBarangOperasi = new PenyerahBarangOperasi();
        }
        penyerahBarangOperasi.setInfoAudit(ia);
        penyerahBarangOperasi.setPengguna(penggunaDAO.findById(idPenyerah));
        penyerahBarangOperasi.setKategoriPenerima("D");
        penyerahBarangOperasi.setNoPerserahan(noSerahan);
        penyerahBarangOperasi.setOperasi(operasiPenguatkuasaan);
        enforceService.savePenyerahBarangOperasi(penyerahBarangOperasi);

        //update table BarangRampasan (op_brg_rampas)
        for (int j = 0; j < senaraiBaranganRampasan.size(); j++) {
            pilihBarang = getContext().getRequest().getParameter("pilihBarang" + j);
            pilihBarangTemp = getContext().getRequest().getParameter("pilihBarangTemp" + j);
            System.out.println("pilihBarang : " + pilihBarang);
            if (StringUtils.isNotBlank(pilihBarang)) {
                barang = barangRampasanDAO.findById(Long.parseLong(pilihBarang));
                barang.setStatusSerah("Y");
                barang.setPenyerahBarangOperasi(penyerahBarangOperasi);
            } else {
                barang = barangRampasanDAO.findById(Long.parseLong(pilihBarangTemp));
                barang.setStatusSerah("T");
                barang.setPenyerahBarangOperasi(null);
            }

            ia = barang.getInfoAudit();
            ia.setDiKemaskiniOleh(pengguna);
            ia.setTarikhKemaskini(new java.util.Date());
            barang.setInfoAudit(ia);
            enforceService.simpanBarangRampasan(barang);
        }

        //save to table PermohonanTandatanganDokumen(mohon_dok_tt)
        if (penyerahBarangOperasi != null) {
            if (penyerahBarangOperasi.getDokumenTandatangan() != null) {
                mohonTandatanganDokumen = permohonanTandatanganDokumenDAO.findById(penyerahBarangOperasi.getDokumenTandatangan().getIdDokTt());
            }
        }

        if (mohonTandatanganDokumen == null) {
            mohonTandatanganDokumen = new PermohonanTandatanganDokumen();
        } else {
            ia = mohonTandatanganDokumen.getInfoAudit();
            ia.setDiKemaskiniOleh(pengguna);
            ia.setTarikhKemaskini(new java.util.Date());
        }

        mohonTandatanganDokumen.setPermohonan(permohonan);
        mohonTandatanganDokumen.setInfoAudit(ia);
        mohonTandatanganDokumen.setCawangan(pengguna.getKodCawangan());
        mohonTandatanganDokumen.setDiTandatangan(diTandatanganOleh);
        mohonTandatanganDokumen.setKodDokumen(kodDokumenDAO.findById("SERAH"));
        mohonTandatanganDokumen.setTrhTt(new java.util.Date());
        enforceService.simpanPermohonanTandatanganDok(mohonTandatanganDokumen);


        //update table PenyerahBarangOperasi(penyerah_brg_op)
        penyerahBarangOperasi.setDokumenTandatangan(mohonTandatanganDokumen);
        enforceService.savePenyerahBarangOperasi(penyerahBarangOperasi);


        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        rehydrate();
        return new ForwardResolution("/WEB-INF/jsp/penguatkuasaan/serahan_barang_rampasan.jsp").addParameter("tab", "true");
    }

    public Resolution simpanBarangOperasi() throws ParseException {
        InfoAudit ia = new InfoAudit();
        ia.setDimasukOleh(pengguna);
        ia.setTarikhMasuk(new java.util.Date());


        idOperasi = getContext().getRequest().getParameter("idOperasi");
        LOG.info("::simpanBarangOperasi - id OP : " + idOperasi);
        if (StringUtils.isNotBlank(idOperasi)) {
            operasiPenguatkuasaan = enforceService.findByIdOp(Long.parseLong(idOperasi));
        }

        if (StringUtils.isNotBlank(idBarangOperasi)) {
            operasiBarangPenguatkuasaan = operasiBarangPenguatkuasaanDAO.findById(Long.parseLong(idBarangOperasi));
        }

        if (operasiBarangPenguatkuasaan != null) {
            ia = operasiBarangPenguatkuasaan.getInfoAudit();
            ia.setDiKemaskiniOleh(pengguna);
            ia.setTarikhKemaskini(new java.util.Date());
        } else {
            operasiBarangPenguatkuasaan = new OperasiBarangPenguatkuasaan();
        }

        operasiBarangPenguatkuasaan.setNama(namaBarangan);
        operasiBarangPenguatkuasaan.setJenis(jenisBarangan);
        operasiBarangPenguatkuasaan.setModel(modelBarangan);
        operasiBarangPenguatkuasaan.setKntt(Integer.parseInt(kuantitiBarangan));
        operasiBarangPenguatkuasaan.setPengguna(penggunaDAO.findById(penggunaSerahan));
        operasiBarangPenguatkuasaan.setInfoAudit(ia);
        operasiBarangPenguatkuasaan.setOperasi(operasiPenguatkuasaan);
        operasiBarangPenguatkuasaan.setKategoriBarangOperasi(jenisBarang.charAt(0));
        enforceService.saveOperasiBarangPenguatkuasaan(operasiBarangPenguatkuasaan);

        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        rehydrate();
        return new ForwardResolution("/WEB-INF/jsp/penguatkuasaan/popup_tambah_barang_operasi.jsp").addParameter("tab", "true");

    }

    public Resolution refreshPageBarangOperasi() {
        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        rehydrate();
        return new JSP("penguatkuasaan/maklumat_barang_operasi.jsp").addParameter("tab", "true");
    }

    public Resolution deleteBarangOperasi() {
        String idBarangOperasi;
        idBarangOperasi = getContext().getRequest().getParameter("idBarangOperasi");
        System.out.println("id masa delete : " + idBarangOperasi);
        try {
            if (idBarangOperasi != null) {
                operasiBarangPenguatkuasaan = operasiBarangPenguatkuasaanDAO.findById(Long.parseLong(idBarangOperasi));
            }
            enforceService.deleteOperasiBarangPenguatkuasaan(operasiBarangPenguatkuasaan);
        } catch (Exception e) {
            e.printStackTrace();
            addSimpleError("kesalahan dalam menghapuskan rekod");
        }
        addSimpleMessage("Maklumat berjaya dihapuskan.");
        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        rehydrate();
        return new JSP("penguatkuasaan/maklumat_barang_operasi.jsp").addParameter("tab", "true");
    }

    public Resolution deletePenyerah() {
        idPenyerahBarangOperasi = getContext().getRequest().getParameter("idPenyerahBarangOperasi");
        System.out.println("id masa delete PenyerahBarangOperasi: " + idPenyerahBarangOperasi);
        try {
            if (StringUtils.isNotBlank(idPenyerahBarangOperasi)) {
                penyerahBarangOperasi = penyerahBarangOperasiDAO.findById(Long.parseLong(idPenyerahBarangOperasi));
            }

            //delete mohon_dok_tt
            if (penyerahBarangOperasi != null) {
                mohonTandatanganDokumen = permohonanTandatanganDokumenDAO.findById(penyerahBarangOperasi.getDokumenTandatangan().getIdDokTt());

                //clear value id_penyerah at op_brg_rampas
                senaraiBaranganRampasan = enforceService.findBarangSerahanForDelete(penyerahBarangOperasi.getIdPenyerahBarangOperasi());
                for (int j = 0; j < senaraiBaranganRampasan.size(); j++) {
                    barang = senaraiBaranganRampasan.get(j);
                    barang.setStatusSerah(null);
                    barang.setPenyerahBarangOperasi(null);

                    InfoAudit ia = barang.getInfoAudit();
                    ia.setDiKemaskiniOleh(pengguna);
                    ia.setTarikhKemaskini(new java.util.Date());
                    barang.setInfoAudit(ia);
                    enforceService.simpanBarangRampasan(barang);
                }

                enforceService.deletePenyerah(penyerahBarangOperasi);

                if (mohonTandatanganDokumen != null) {
                    enforceService.deletePermohonanTandatanganDok(mohonTandatanganDokumen);
                }

            }


        } catch (Exception e) {
            e.printStackTrace();
            addSimpleError("kesalahan dalam menghapuskan rekod");
        }
        addSimpleMessage("Maklumat berjaya dihapuskan.");
        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        rehydrate();
        return new JSP("penguatkuasaan/serahan_barang_rampasan.jsp").addParameter("tab", "true");
    }

    public Resolution findNoSerahan() {
        String result = "";

        noSerahan = getContext().getRequest().getParameter("noSerahan");
        System.out.println("noSerahan ialah :" + noSerahan);
        List<PenyerahBarangOperasi> senaraiPenyerahBarangOperasi = enforceService.checkNoSerahan(noSerahan.toLowerCase());
        System.out.println("size senaraiPenyerahBarangOperasi : " + senaraiPenyerahBarangOperasi);
        if (senaraiPenyerahBarangOperasi.size() != 0) {
            result = "exist";
        } else {
            result = "not exist";
        }
        return new StreamingResolution("test", result);
    }

    public Resolution refreshpage() {
        rehydrate();
        return new RedirectResolution(SerahanBarangRampasanActionBean.class, "locate");
    }

    public KodDokumen getKodDokumen() {
        return kodDokumen;
    }

    public void setKodDokumen(KodDokumen kodDokumen) {
        this.kodDokumen = kodDokumen;
    }

    public Permohonan getPermohonan() {
        return permohonan;
    }

    public void setPermohonan(Permohonan permohonan) {
        this.permohonan = permohonan;
    }

    public int getRowCount() {
        return rowCount;
    }

    public void setRowCount(int rowCount) {
        this.rowCount = rowCount;
    }

    public String getIdPermohonan() {
        return idPermohonan;
    }

    public void setIdPermohonan(String idPermohonan) {
        this.idPermohonan = idPermohonan;
    }

    public String getRecordCount() {
        return recordCount;
    }

    public void setRecordCount(String recordCount) {
        this.recordCount = recordCount;
    }

    public List<Pengguna> getSenaraiPengguna() {
        return senaraiPengguna;
    }

    public void setSenaraiPengguna(List<Pengguna> senaraiPengguna) {
        this.senaraiPengguna = senaraiPengguna;
    }

    public Pengguna getPengguna() {
        return pengguna;
    }

    public void setPengguna(Pengguna pengguna) {
        this.pengguna = pengguna;
    }

    public PermohonanTandatanganDokumen getMohonTandatanganDokumen() {
        return mohonTandatanganDokumen;
    }

    public void setMohonTandatanganDokumen(PermohonanTandatanganDokumen mohonTandatanganDokumen) {
        this.mohonTandatanganDokumen = mohonTandatanganDokumen;
    }

    public String getDiTandatanganOleh() {
        return diTandatanganOleh;
    }

    public void setDiTandatanganOleh(String diTandatanganOleh) {
        this.diTandatanganOleh = diTandatanganOleh;
    }

    public List<OperasiPenguatkuasaan> getListOp() {
        return listOp;
    }

    public void setListOp(List<OperasiPenguatkuasaan> listOp) {
        this.listOp = listOp;
    }

    public String getIdOperasi() {
        return idOperasi;
    }

    public void setIdOperasi(String idOperasi) {
        this.idOperasi = idOperasi;
    }

    public String getNoPengenalanPenyerah() {
        return noPengenalanPenyerah;
    }

    public void setNoPengenalanPenyerah(String noPengenalanPenyerah) {
        this.noPengenalanPenyerah = noPengenalanPenyerah;
    }

    public String getPekerjaanPenyerah() {
        return pekerjaanPenyerah;
    }

    public void setPekerjaanPenyerah(String pekerjaanPenyerah) {
        this.pekerjaanPenyerah = pekerjaanPenyerah;
    }

    public String getTempatKerjaPenyerah() {
        return tempatKerjaPenyerah;
    }

    public void setTempatKerjaPenyerah(String tempatKerjaPenyerah) {
        this.tempatKerjaPenyerah = tempatKerjaPenyerah;
    }

    public String getNoSerahan() {
        return noSerahan;
    }

    public void setNoSerahan(String noSerahan) {
        this.noSerahan = noSerahan;
    }

    public List<OperasiBarangPenguatkuasaan> getSenaraiBaranganOperasi() {
        return senaraiBaranganOperasi;
    }

    public void setSenaraiBaranganOperasi(List<OperasiBarangPenguatkuasaan> senaraiBaranganOperasi) {
        this.senaraiBaranganOperasi = senaraiBaranganOperasi;
    }

    public List<BarangRampasan> getSenaraiBaranganRampasan() {
        return senaraiBaranganRampasan;
    }

    public void setSenaraiBaranganRampasan(List<BarangRampasan> senaraiBaranganRampasan) {
        this.senaraiBaranganRampasan = senaraiBaranganRampasan;
    }

    public String getIdPenyerah() {
        return idPenyerah;
    }

    public void setIdPenyerah(String idPenyerah) {
        this.idPenyerah = idPenyerah;
    }

    public PenyerahBarangOperasi getPenyerahBarangOperasi() {
        return penyerahBarangOperasi;
    }

    public void setPenyerahBarangOperasi(PenyerahBarangOperasi penyerahBarangOperasi) {
        this.penyerahBarangOperasi = penyerahBarangOperasi;
    }

    public OperasiBarangPenguatkuasaan getOperasiBarangPenguatkuasaan() {
        return operasiBarangPenguatkuasaan;
    }

    public void setOperasiBarangPenguatkuasaan(OperasiBarangPenguatkuasaan operasiBarangPenguatkuasaan) {
        this.operasiBarangPenguatkuasaan = operasiBarangPenguatkuasaan;
    }

    public String getNamaBarangan() {
        return namaBarangan;
    }

    public void setNamaBarangan(String namaBarangan) {
        this.namaBarangan = namaBarangan;
    }

    public String getJenisBarangan() {
        return jenisBarangan;
    }

    public void setJenisBarangan(String jenisBarangan) {
        this.jenisBarangan = jenisBarangan;
    }

    public String getModelBarangan() {
        return modelBarangan;
    }

    public void setModelBarangan(String modelBarangan) {
        this.modelBarangan = modelBarangan;
    }

    public String getKuantitiBarangan() {
        return kuantitiBarangan;
    }

    public void setKuantitiBarangan(String kuantitiBarangan) {
        this.kuantitiBarangan = kuantitiBarangan;
    }

    public String getPenggunaSerahan() {
        return penggunaSerahan;
    }

    public void setPenggunaSerahan(String penggunaSerahan) {
        this.penggunaSerahan = penggunaSerahan;
    }

    public String getPilihBarang() {
        return pilihBarang;
    }

    public void setPilihBarang(String pilihBarang) {
        this.pilihBarang = pilihBarang;
    }

    public BarangRampasan getBarang() {
        return barang;
    }

    public void setBarang(BarangRampasan barang) {
        this.barang = barang;
    }

    public OperasiPenguatkuasaan getOperasiPenguatkuasaan() {
        return operasiPenguatkuasaan;
    }

    public void setOperasiPenguatkuasaan(OperasiPenguatkuasaan operasiPenguatkuasaan) {
        this.operasiPenguatkuasaan = operasiPenguatkuasaan;
    }

    public String getPilihBarangTemp() {
        return pilihBarangTemp;
    }

    public void setPilihBarangTemp(String pilihBarangTemp) {
        this.pilihBarangTemp = pilihBarangTemp;
    }

    public List<Pengguna> getSenaraiTandatanganPengguna() {
        return senaraiTandatanganPengguna;
    }

    public void setSenaraiTandatanganPengguna(List<Pengguna> senaraiTandatanganPengguna) {
        this.senaraiTandatanganPengguna = senaraiTandatanganPengguna;
    }

    public String getIdPenyerahBarangOperasi() {
        return idPenyerahBarangOperasi;
    }

    public void setIdPenyerahBarangOperasi(String idPenyerahBarangOperasi) {
        this.idPenyerahBarangOperasi = idPenyerahBarangOperasi;
    }

    public List<BarangRampasan> getSenaraiBaranganRampasanForSerahan() {
        return senaraiBaranganRampasanForSerahan;
    }

    public void setSenaraiBaranganRampasanForSerahan(List<BarangRampasan> senaraiBaranganRampasanForSerahan) {
        this.senaraiBaranganRampasanForSerahan = senaraiBaranganRampasanForSerahan;
    }

    public String getIdBarangOperasi() {
        return idBarangOperasi;
    }

    public void setIdBarangOperasi(String idBarangOperasi) {
        this.idBarangOperasi = idBarangOperasi;
    }

    public String getStatusOperation() {
        return statusOperation;
    }

    public void setStatusOperation(String statusOperation) {
        this.statusOperation = statusOperation;
    }

    public OperasiPenguatkuasaan getOperasiPenguatkuasaanIP() {
        return operasiPenguatkuasaanIP;
    }

    public void setOperasiPenguatkuasaanIP(OperasiPenguatkuasaan operasiPenguatkuasaanIP) {
        this.operasiPenguatkuasaanIP = operasiPenguatkuasaanIP;
    }

    public List<AduanOrangKenaSyak> getSenaraiOksIp() {
        return senaraiOksIp;
    }

    public void setSenaraiOksIp(List<AduanOrangKenaSyak> senaraiOksIp) {
        this.senaraiOksIp = senaraiOksIp;
    }

    public Boolean getStatusIP() {
        return statusIP;
    }

    public void setStatusIP(Boolean statusIP) {
        this.statusIP = statusIP;
    }

    public String getJenisBarang() {
        return jenisBarang;
    }

    public void setJenisBarang(String jenisBarang) {
        this.jenisBarang = jenisBarang;
    }
}
