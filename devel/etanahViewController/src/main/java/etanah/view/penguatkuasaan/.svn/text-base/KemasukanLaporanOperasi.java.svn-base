/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.penguatkuasaan;

import etanah.dao.*;
import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;

import etanah.model.InfoAudit;
import etanah.model.Pengguna;
import java.text.ParseException;
import etanah.model.AduanLokasi;
import etanah.model.Dokumen;
import etanah.model.Permohonan;
import etanah.model.KodCawangan;
import etanah.model.KodJenisPengenalan;
import etanah.model.KodPerananOperasi;
import etanah.model.OperasiAgensi;
import etanah.model.OperasiPenguatkuasaan;
import etanah.model.OperasiPenguatkuasaanPasukan;
import etanah.model.PermohonanRujukanLuar;
import etanah.view.etanahActionBeanContext;
import java.util.List;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;
import org.apache.log4j.Logger;
import java.text.SimpleDateFormat;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.RedirectResolution;
import etanah.service.EnforceService;
import etanah.service.common.EnforcementService;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import net.sourceforge.stripes.action.StreamingResolution;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author latifah.iskak
 */
@UrlBinding("/penguatkuasaan/kemasukan_laporan_operasi")
public class KemasukanLaporanOperasi extends AbleActionBean {

    private static Logger logger = Logger.getLogger(KemasukanLaporanOperasi.class);
    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;
    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    DokumenDAO dokumenDAO;
    @Inject
    EnforceService enforceService;
    @Inject
    EnforcementService enforcementService;
    @Inject
    OperasiPenguatkuasaanDAO operasiPenguatkuasaanDAO;
    @Inject
    OperasiPenguatkuasaanPasukanDAO operasiPenguatkuasaanPasukanDAO;
    @Inject
    OperasiAgensiDAO operasiAgensiDAO;
    @Inject
    KodPerananOperasiDAO kodPerananOperasiDAO;
    @Inject
    KodJenisPengenalanDAO kodJenisPengenalanDAO;
    @Inject
    KodAgensiDAO kodAgensiDAO;
    private AduanLokasi aduanLokasi;
    private OperasiPenguatkuasaan operasiPenguatkuasaan;
    private Permohonan permohonan;
    private String catatan;
    private String lokasi;
    private String tarikhOperasi;
    private String tarikhRujukan;
    private String idPermohonan;
    private Pengguna pguna;
    private KodCawangan cawangan;
    private PermohonanRujukanLuar permohonanRujukanLuar;
    private String jenisTindakan;
    private int jumlahTangkapan;
    private String jam;
    private String minit;
    private String ampm;
    private String platKenderaan;
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    SimpleDateFormat sdf0 = new SimpleDateFormat("dd/MM/yyyy");
    SimpleDateFormat sdf1 = new SimpleDateFormat("dd/MM/yyyy hh:mm aaa");
    SimpleDateFormat sdf2 = new SimpleDateFormat("dd/MM/yyyy hh:mm aaa");
    private List<OperasiPenguatkuasaanPasukan> senaraiPasukan;
    private List<OperasiPenguatkuasaanPasukan> senaraiPasukanWithoutKetua;
    private List<OperasiAgensi> senaraiOperasiAgensi;
    private ArrayList<AgensiValue> senaraiAgensi = new ArrayList<AgensiValue>();
    private OperasiAgensi operasiAgensi;
    private String recordCount;
    private OperasiPenguatkuasaanPasukan operasiPenguatkuasaanPasukan;
    private String nama;
    private String noKp;
    private String peranan;
    private BigDecimal nilaiKecurian;
    private String kadKuasa;
    private String namaKetua;
    private String idKetua;
    private String noPengenalanKetua;
    private String kadKuasaKetua;
    private String namaKetuaDisabled;
    private String tarikhPenahanan;
    private String hour;
    private String minute;
    private String ampm1;
    private String idAgensi;
    private String namaAgensi;
    private String namaKetuaAgensi;
    private String jenisPengenalan;
    private String noPengenalan;
    private String jawatan;
    private String noTelefon;
    private String email;
    private String kodAgensi;
    private String id;
    private String idOpsAgen;
    private Pengguna pengguna;

    class UrusanCache implements Serializable {

        ArrayList<AgensiValue> senaraiAgensi;
        OperasiPenguatkuasaan op;
    }

    @DefaultHandler
    public Resolution showForm() {
        return new JSP("penguatkuasaan/kemasukan_laporan_operasi.jsp").addParameter("tab", "true");
    }

    public Resolution showForm2() {
        return new JSP("penguatkuasaan/kemasukan_laporan_operasi_view.jsp").addParameter("tab", "true");
    }

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {
        pengguna = (Pengguna) context.getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        senaraiPasukan = new ArrayList<OperasiPenguatkuasaanPasukan>();
        if (idPermohonan != null) {
            try {
                operasiPenguatkuasaan = enforceService.findOperasiPenguatkuasaanByIdpermohonan(idPermohonan, "O");
                permohonan = permohonanDAO.findById(idPermohonan);

                if (operasiPenguatkuasaan != null) {
                    senaraiPasukan = enforceService.getSenaraiPasukan(operasiPenguatkuasaan.getIdOperasi());
                    senaraiPasukanWithoutKetua = enforcementService.getSenaraiPasukanWithoutKetua(operasiPenguatkuasaan.getIdOperasi());
                    senaraiOperasiAgensi = enforcementService.findOperasiByIDOpeasi(operasiPenguatkuasaan.getIdOperasi());
                    System.out.println("size senarai operasi agensi : " + senaraiOperasiAgensi.size());

                    senaraiAgensi = new ArrayList<AgensiValue>();

                    for (OperasiAgensi oa : senaraiOperasiAgensi) {
                        AgensiValue agensiList = new AgensiValue();
                        agensiList.setIdAgensi(oa.getIdOperasiAgensi());
                        agensiList.setNamaAgensi(oa.getAgensi().getNama());
                        agensiList.setKodAgensi(oa.getAgensi().getKod());
                        agensiList.setNamaKetuaAgensi(oa.getNamaHubungan());
                        if (oa.getKodPengenalan() != null) {
                            agensiList.setKodJenisPengenalan(oa.getKodPengenalan().getKod());
                        }
                        agensiList.setNoPengenalan(oa.getNoPengenalan());
                        agensiList.setJawatan(oa.getJawatanHubungan());
                        agensiList.setNoTelefon(oa.getNoTelefonHubungan());
                        agensiList.setEmail(oa.getEmailHubungan());

                        senaraiAgensi.add(agensiList);
                        logger.info("--------rehydrate : size senarai agensi -------" + senaraiAgensi.size());
                        logger.info("--------info list agensi value-------");
                        logger.info("info list-id agensi :" + agensiList.getIdAgensi());
                        logger.info("info list-nama agensi : " + agensiList.getNamaAgensi());
                        logger.info("info list-kod agensi : " + agensiList.getKodAgensi());
                        logger.info("info list-nama ketua : " + agensiList.getNamaKetuaAgensi());
                        logger.info("info list-kod pengenalan : " + agensiList.getKodJenisPengenalan());
                        logger.info("info list-no pengenalan : " + agensiList.getNoPengenalan());
                        logger.info("info list-jawatan : " + agensiList.getJawatan());
                        logger.info("info list-no telefon : " + agensiList.getNoTelefon());
                        logger.info("info list-email : " + agensiList.getEmail());
                    }

                    if (senaraiPasukanWithoutKetua != null) {
                        recordCount = String.valueOf(senaraiPasukanWithoutKetua.size());
                        System.out.println("record count : " + recordCount);
                    }

                    try {
                        if (senaraiPasukan.size() != 0) {
                            for (int i = 0; i < senaraiPasukan.size(); i++) {
                                if (senaraiPasukan.get(i).getKodPerananOperasi().getKod().equalsIgnoreCase("K")) {
                                    namaKetua = senaraiPasukan.get(i).getNama();
                                    namaKetuaDisabled = senaraiPasukan.get(i).getNama();
                                    kadKuasaKetua = senaraiPasukan.get(i).getKadKuasa();
                                    noPengenalanKetua = senaraiPasukan.get(i).getNoKp();
                                    idKetua = Long.toString(senaraiPasukan.get(i).getIdOperasiPenguatkuasaanPasukan());
                                }
                            }
                        }
                    } catch (Exception e) {
                        e.getCause();
                    }

                    catatan = operasiPenguatkuasaan.getCatatan();
                    lokasi = operasiPenguatkuasaan.getLokasi();
                    jenisTindakan = operasiPenguatkuasaan.getJenisTindakan();
                    jumlahTangkapan = operasiPenguatkuasaan.getJumlahTangkapan();
                    nilaiKecurian = operasiPenguatkuasaan.getNilaiKecurian();
                    platKenderaan = operasiPenguatkuasaan.getKenderaan();

                    if (operasiPenguatkuasaan.getTarikhOperasi() != null) {
                        tarikhOperasi = sdf.format(operasiPenguatkuasaan.getTarikhOperasi());
                        tarikhOperasi = sdf1.format(operasiPenguatkuasaan.getTarikhOperasi()).substring(0, 10);
                        jam = sdf1.format(operasiPenguatkuasaan.getTarikhOperasi()).substring(11, 13);
                        if (jam.startsWith("0")) {
                            jam = sdf1.format(operasiPenguatkuasaan.getTarikhOperasi()).substring(12, 13);
                        }
                        minit = sdf1.format(operasiPenguatkuasaan.getTarikhOperasi()).substring(14, 16);
                        if (minit.startsWith("0")) {
                            minit = sdf1.format(operasiPenguatkuasaan.getTarikhOperasi()).substring(15, 16);
                        }
                        ampm = sdf1.format(operasiPenguatkuasaan.getTarikhOperasi()).substring(17, 19);
                    }

                    if (operasiPenguatkuasaan.getTarikhPenahanan() != null) {
                        tarikhPenahanan = sdf0.format(operasiPenguatkuasaan.getTarikhPenahanan());
                        tarikhPenahanan = sdf2.format(operasiPenguatkuasaan.getTarikhPenahanan()).substring(0, 10);
                        hour = sdf2.format(operasiPenguatkuasaan.getTarikhPenahanan()).substring(11, 13);
                        if (hour.startsWith("0")) {
                            hour = sdf2.format(operasiPenguatkuasaan.getTarikhPenahanan()).substring(12, 13);
                        }
                        minute = sdf2.format(operasiPenguatkuasaan.getTarikhPenahanan()).substring(14, 16);
                        if (minute.startsWith("0")) {
                            minute = sdf2.format(operasiPenguatkuasaan.getTarikhOperasi()).substring(15, 16);
                        }
                        ampm1 = sdf2.format(operasiPenguatkuasaan.getTarikhPenahanan()).substring(17, 19);

                    }


                }


            } catch (Exception ex) {
                ex.printStackTrace();
            }

            getUrusanfromSession();
        }
    }

    public Resolution deletePenguatkuasaanPasukan() {
        String idOperasiPenguatkuasaanPasukan;
        idOperasiPenguatkuasaanPasukan = getContext().getRequest().getParameter("idOperasiPenguatkuasaanPasukan");
        System.out.println("id masa delete : " + idOperasiPenguatkuasaanPasukan);
        try {
            if (idOperasiPenguatkuasaanPasukan != null) {
                operasiPenguatkuasaanPasukan = operasiPenguatkuasaanPasukanDAO.findById(Long.parseLong(idOperasiPenguatkuasaanPasukan));
            }
            enforceService.deleteOperasiPasukan(operasiPenguatkuasaanPasukan);
        } catch (Exception e) {
            e.printStackTrace();
            addSimpleError("kesalahan dalam menghapuskan rekod");
        }
        addSimpleMessage("Maklumat berjaya dihapuskan.");
        rehydrate();
        return new ForwardResolution("/WEB-INF/jsp/penguatkuasaan/laporan_operasi_polis.jsp").addParameter("tab", "true");
    }

    public Resolution simpan() throws ParseException {

        InfoAudit ia = new InfoAudit();

        operasiPenguatkuasaan = enforceService.findOperasiPenguatkuasaanByIdpermohonan(idPermohonan, "O");
        System.out.println("operasiPenguatkuasaan : " + operasiPenguatkuasaan);
        if (operasiPenguatkuasaan == null) {
            operasiPenguatkuasaan = new OperasiPenguatkuasaan();
            ia.setDimasukOleh(pengguna);
            ia.setTarikhMasuk(new java.util.Date());
        } else {
            System.out.println("id kkuasa op : " + operasiPenguatkuasaan.getIdOperasi());
            ia = operasiPenguatkuasaan.getInfoAudit();
            ia.setDiKemaskiniOleh(pengguna);
            ia.setTarikhKemaskini(new java.util.Date());
        }

        tarikhOperasi = tarikhOperasi + " " + jam + ":" + minit + " " + ampm;
        operasiPenguatkuasaan.setPermohonan(permohonan);
        operasiPenguatkuasaan.setInfoAudit(ia);
        operasiPenguatkuasaan.setJenisTindakan(jenisTindakan);
        operasiPenguatkuasaan.setCatatan(catatan);
        operasiPenguatkuasaan.setLokasi(lokasi);
        operasiPenguatkuasaan.setJumlahTangkapan(jumlahTangkapan);
        operasiPenguatkuasaan.setCawangan(pengguna.getKodCawangan());
        operasiPenguatkuasaan.setKenderaan(platKenderaan);
        operasiPenguatkuasaan.setKategoriTindakan("O"); //O = operasi

        try {
            operasiPenguatkuasaan.setTarikhOperasi(sdf1.parse(tarikhOperasi));
        } catch (Exception ex) {
            ex.getCause();
            logger.error(ex);
        }

        enforceService.simpanOperasiPenguatkuasaan(operasiPenguatkuasaan);

        try {

            try {
                //for saving ketua to table : kkuasa_pasukan
                idKetua = getContext().getRequest().getParameter("idKetua");
                namaKetua = getContext().getRequest().getParameter("namaKetua");
                noPengenalanKetua = getContext().getRequest().getParameter("noPengenalanKetua");
                kadKuasaKetua = getContext().getRequest().getParameter("kadKuasaKetua");

                if (!idKetua.isEmpty()) {
                    operasiPenguatkuasaanPasukan = enforcementService.findKetua(Long.parseLong(idKetua));
                    ia = operasiPenguatkuasaanPasukan.getInfoAudit();
                    ia.setDiKemaskiniOleh(pengguna);
                    ia.setTarikhKemaskini(new java.util.Date());
                } else {
                    operasiPenguatkuasaanPasukan = new OperasiPenguatkuasaanPasukan();
                    ia.setDimasukOleh(pengguna);
                    ia.setTarikhMasuk(new java.util.Date());
                }

                KodPerananOperasi kodKetua = kodPerananOperasiDAO.findById("K"); //kod : for ketua(K)
                operasiPenguatkuasaanPasukan.setCawangan(pengguna.getKodCawangan());
                operasiPenguatkuasaanPasukan.setIdOperasiPenguatkuasaan(operasiPenguatkuasaan);
                operasiPenguatkuasaanPasukan.setNama(namaKetua);
                operasiPenguatkuasaanPasukan.setNoKp(noPengenalanKetua);
                operasiPenguatkuasaanPasukan.setKodPerananOperasi(kodKetua);
                operasiPenguatkuasaanPasukan.setKadKuasa(kadKuasaKetua);
                operasiPenguatkuasaanPasukan.setInfoAudit(ia);
                enforceService.simpanOperasiPenguatkuasaanPasukan(operasiPenguatkuasaanPasukan);

            } catch (Exception ex) {
                ex.printStackTrace();

            }
            senaraiPasukanWithoutKetua = enforcementService.getSenaraiPasukanWithoutKetua(operasiPenguatkuasaan.getIdOperasi());
            System.out.println("senaraiPasukan tanpa ketua size : " + senaraiPasukanWithoutKetua.size());
            int rowCount = Integer.parseInt(getContext().getRequest().getParameter("recordCount"));
            for (int i = 0; i < rowCount; i++) {
                if (senaraiPasukanWithoutKetua.size() != 0 && i < senaraiPasukanWithoutKetua.size()) {
                    Long id = senaraiPasukanWithoutKetua.get(i).getIdOperasiPenguatkuasaanPasukan();
                    if (id != null) {
                        operasiPenguatkuasaanPasukan = enforceService.findSenaraiPasukan(id);
                    }
                } else {
                    System.out.println("create new pasukan");
                    operasiPenguatkuasaanPasukan = new OperasiPenguatkuasaanPasukan();
                }

                nama = getContext().getRequest().getParameter("nama" + i);
                noKp = getContext().getRequest().getParameter("noKp" + i);
                peranan = getContext().getRequest().getParameter("peranan" + i);
                kadKuasa = getContext().getRequest().getParameter("kadKuasa" + i);

                KodPerananOperasi kp = kodPerananOperasiDAO.findById(peranan);

                operasiPenguatkuasaanPasukan.setCawangan(pengguna.getKodCawangan());
                operasiPenguatkuasaanPasukan.setIdOperasiPenguatkuasaan(operasiPenguatkuasaan);
                operasiPenguatkuasaanPasukan.setNama(nama);
                operasiPenguatkuasaanPasukan.setNoKp(noKp);
                operasiPenguatkuasaanPasukan.setKodPerananOperasi(kp);
                operasiPenguatkuasaanPasukan.setKadKuasa(kadKuasa);
                operasiPenguatkuasaanPasukan.setInfoAudit(ia);
                enforceService.simpanOperasiPenguatkuasaanPasukan(operasiPenguatkuasaanPasukan);
            }

            //to save data to table operasiAgensi
            logger.info("size senarai agensi before save : " + senaraiAgensi.size());
            for (int i = 1; i <= senaraiAgensi.size(); i++) {
                if (i <= senaraiAgensi.size()) {
                    Long idOpAgensi = senaraiAgensi.get(i - 1).getIdAgensi();
                    if (idOpAgensi != null && idOpAgensi != 0) {
                        System.out.println("existing data : id agensi " + idOpAgensi);
                        operasiAgensi = operasiAgensiDAO.findById(idOpAgensi);
                        ia = operasiAgensi.getInfoAudit();
                        ia.setDiKemaskiniOleh(pengguna);
                        ia.setTarikhKemaskini(new java.util.Date());
                    } else {
                        System.out.println("new data");
                        operasiAgensi = new OperasiAgensi();
                        ia.setTarikhMasuk(new java.util.Date());
                        ia.setDimasukOleh(pengguna);
                    }
                }
                if (senaraiAgensi.get(i - 1).kodJenisPengenalan != null) {
                    KodJenisPengenalan kodJenisPengenalan = kodJenisPengenalanDAO.findById(senaraiAgensi.get(i - 1).kodJenisPengenalan);
                    operasiAgensi.setKodPengenalan(kodJenisPengenalan);
                }
                operasiAgensi.setOperasi(operasiPenguatkuasaan);
                operasiAgensi.setCawangan(pengguna.getKodCawangan());
                operasiAgensi.setAgensi(kodAgensiDAO.findById(senaraiAgensi.get(i - 1).getKodAgensi()));
                operasiAgensi.setNamaHubungan(senaraiAgensi.get(i - 1).namaKetuaAgensi);
                operasiAgensi.setNoTelefonHubungan(senaraiAgensi.get(i - 1).noTelefon);
                operasiAgensi.setEmailHubungan(senaraiAgensi.get(i - 1).email);
                operasiAgensi.setNoPengenalan(senaraiAgensi.get(i - 1).noPengenalan);
                operasiAgensi.setJawatanHubungan(senaraiAgensi.get(i - 1).jawatan);
                operasiAgensi.setInfoAudit(ia);
                enforceService.updateOperasiAgensi(operasiAgensi);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
            logger.error(ex);
        }
        etanahActionBeanContext ctx = (etanahActionBeanContext) getContext();
        UrusanCache u = (UrusanCache) ctx.getWorkData();
        if (u == null) {
            u = new UrusanCache();
        }
        u.senaraiAgensi = senaraiAgensi;
        u.op = operasiPenguatkuasaan;

        ctx.removeWorkdata();

        addSimpleMessage("Maklumat telah berjaya disimpan.");
        rehydrate();
        return new ForwardResolution("/WEB-INF/jsp/penguatkuasaan/kemasukan_laporan_operasi.jsp").addParameter("tab", "true");
    }

    public Resolution deleteSelected() {
        System.out.println("deleteSelected");
        Long id = Long.parseLong(getContext().getRequest().getParameter("idImej"));

        Session s = sessionProvider.get();
        Transaction tx = s.beginTransaction();
        tx.begin();
        try {
            Dokumen dok1 = dokumenDAO.findById(id);
            dokumenDAO.delete(dok1);

            tx.commit();

        } catch (Exception ex) {
            tx.rollback();
            Throwable t = ex;
            // getting the root cause
            while (t.getCause() != null) {
                t = t.getCause();
            }
            ex.printStackTrace();
            addSimpleError(t.getMessage());
        }
        return new RedirectResolution(KemasukanLaporanOperasi.class, "locate");
    }

    public Resolution findNoPengenalan() {
        logger.info("--------findNoPengenalan--------");
        String result = "";
        String noPengenalan = getContext().getRequest().getParameter("noPengenalan");

        List<Pengguna> pengguna = enforcementService.findNoKadPengenalanKetua(noPengenalan.trim());
        System.out.println("pengguna : " + pengguna.size());
        if (pengguna.size() == 1) {
            for (int i = 0; i < pengguna.size(); i++) {
                result = pengguna.get(i).getNama();
            }
        } else if (pengguna.size() == 0) {
            result = "NotExist";
        } else if (pengguna.size() > 1) {
            result = "Duplicate";
        }

        return new StreamingResolution("text/plain", result);
    }

    public Resolution addAgensi() {
        logger.info("----------------add agensi popup-----------------");
        kodAgensi = getContext().getRequest().getParameter("idAgensi");
        namaAgensi = getContext().getRequest().getParameter("namaAgensi");
        getContext().getRequest().setAttribute("add", Boolean.TRUE);
        return new JSP("penguatkuasaan/popup_tambah_agensi.jsp").addParameter("popup", "true");
    }

    public Resolution saveAgensi() {
        logger.debug("------------saveAgensi-------------");

        AgensiValue av = new AgensiValue();
        av.setKodAgensi(kodAgensi);
        av.setNamaAgensi(namaAgensi);
        av.setNamaKetuaAgensi(namaKetuaAgensi);
        av.setKodJenisPengenalan(jenisPengenalan);
        av.setNoPengenalan(noPengenalan);
        av.setJawatan(jawatan);
        av.setNoTelefon(noTelefon);
        av.setEmail(email);

        senaraiAgensi.add(av);

        etanahActionBeanContext ctx = (etanahActionBeanContext) getContext();
        saveToSession(ctx);

        addSimpleMessage("Maklumat telah berjaya ditambah.");
        return new RedirectResolution(KemasukanLaporanOperasi.class, "locate");
    }

    public final void saveToSession(etanahActionBeanContext ctx) {
        logger.debug("---------saveToSession---------");
        UrusanCache u = (UrusanCache) ctx.getWorkData();
        if (u == null) {
            u = new UrusanCache();
        }
        u.senaraiAgensi = senaraiAgensi;
        u.op = operasiPenguatkuasaan;

        ctx.setWorkData(u);
    }

    public final void getUrusanfromSession() {
        logger.debug("---------getUrusanfromSession---------");

        etanahActionBeanContext ctx = (etanahActionBeanContext) getContext();
        UrusanCache u = (UrusanCache) ctx.getWorkData();
        if (u != null) {
            for (int i = 0; i < u.senaraiAgensi.size(); i++) {
                AgensiValue av = new AgensiValue();
                av.setNamaAgensi(u.senaraiAgensi.get(i).getNamaAgensi());
                av.setKodAgensi(u.senaraiAgensi.get(i).getKodAgensi());
                av.setNamaKetuaAgensi(u.senaraiAgensi.get(i).getNamaKetuaAgensi());
                av.setKodJenisPengenalan(u.senaraiAgensi.get(i).getKodJenisPengenalan());
                av.setNoPengenalan(u.senaraiAgensi.get(i).getNoPengenalan());
                av.setJawatan(u.senaraiAgensi.get(i).getJawatan());
                av.setNoTelefon(u.senaraiAgensi.get(i).getNoTelefon());
                av.setEmail(u.senaraiAgensi.get(i).getEmail());
            }

            senaraiAgensi = u.senaraiAgensi;

        } else {
            logger.debug("---------no data in session---------");
        }
    }

    public Resolution editPopupAgensi() {
        logger.debug("----------editPopupAgensi----------");

        etanahActionBeanContext ctx = (etanahActionBeanContext) getContext();
        UrusanCache u = (UrusanCache) ctx.getWorkData();
        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        if (u != null) {
            id = getContext().getRequest().getParameter("id");
            System.out.println("id - editPopupAgensi : " + id);
            int idInt = Integer.parseInt(id);
            namaAgensi = u.senaraiAgensi.get(idInt).getNamaAgensi();
            kodAgensi = u.senaraiAgensi.get(idInt).getKodAgensi();
            namaKetuaAgensi = u.senaraiAgensi.get(idInt).getNamaKetuaAgensi();
            jenisPengenalan = u.senaraiAgensi.get(idInt).getKodJenisPengenalan();
            noPengenalan = u.senaraiAgensi.get(idInt).getNoPengenalan();
            jawatan = u.senaraiAgensi.get(idInt).getJawatan();
            noTelefon = u.senaraiAgensi.get(idInt).getNoTelefon();
            email = u.senaraiAgensi.get(idInt).getEmail();

        } else {
            logger.debug("----- no data in session : take data from db ------");
            idOpsAgen = getContext().getRequest().getParameter("idOpsAgen");
            System.out.println("idOpsAgen : " + idOpsAgen);
            operasiAgensi = operasiAgensiDAO.findById(Long.parseLong(idOpsAgen));
            if (operasiAgensi != null) {
                namaAgensi = operasiAgensi.getAgensi().getNama();
                kodAgensi = operasiAgensi.getAgensi().getKod();
                namaKetuaAgensi = operasiAgensi.getNamaHubungan();
                jenisPengenalan = operasiAgensi.getKodPengenalan().getKod();
                noPengenalan = operasiAgensi.getNoPengenalan();
                jawatan = operasiAgensi.getJawatanHubungan();
                noTelefon = operasiAgensi.getNoTelefonHubungan();
                email = operasiAgensi.getEmailHubungan();
            }

        }
        return new JSP("penguatkuasaan/popup_tambah_agensi.jsp").addParameter("popup", "true");
    }

    public Resolution editAgensi() {
        logger.debug("----------editAgensi----------");

        etanahActionBeanContext ctx = (etanahActionBeanContext) getContext();
        UrusanCache u = (UrusanCache) ctx.getWorkData();
        if (u != null) {
            id = getContext().getRequest().getParameter("id");
            System.out.println("editAgensi-id : " + id);
            AgensiValue av = new AgensiValue();
            av.setKodAgensi(kodAgensi);
            av.setNamaAgensi(namaAgensi);
            av.setNamaKetuaAgensi(namaKetuaAgensi);
            av.setKodJenisPengenalan(jenisPengenalan);
            av.setNoPengenalan(noPengenalan);
            av.setJawatan(jawatan);
            av.setNoTelefon(noTelefon);
            av.setEmail(email);
            u.senaraiAgensi.set(Integer.parseInt(id), av);
        } else {
            logger.debug("----- no data in session : take data from db ------");
            idOpsAgen = getContext().getRequest().getParameter("idOpsAgen");
            System.out.println("editAgensi-idOpsAgen : " + idOpsAgen);
            operasiAgensi = operasiAgensiDAO.findById(Long.parseLong(idOpsAgen));
            if (operasiAgensi != null) {
                operasiAgensi.setNamaHubungan(namaKetuaAgensi);
                operasiAgensi.setNoTelefonHubungan(noTelefon);
                operasiAgensi.setEmailHubungan(email);
                operasiAgensi.setKodPengenalan(kodJenisPengenalanDAO.findById(jenisPengenalan));
                operasiAgensi.setNoPengenalan(noPengenalan);
                operasiAgensi.setJawatanHubungan(jawatan);
                enforceService.updateOperasiAgensi(operasiAgensi);
            }
        }

        addSimpleMessage("Maklumat telah berjaya dikemaskini.");
        return new RedirectResolution(KemasukanLaporanOperasi.class, "locate");
    }

    public Resolution removeAgensi() {
        logger.debug("------------removeAgensi------------");

        etanahActionBeanContext ctx = (etanahActionBeanContext) getContext();
        UrusanCache u = (UrusanCache) ctx.getWorkData();
        if (u != null) {
            id = getContext().getRequest().getParameter("id");
            ArrayList<AgensiValue> agensi = u.senaraiAgensi;
            for (Iterator<AgensiValue> it = agensi.iterator(); it.hasNext();) {
                AgensiValue agensiVal = it.next();
                if (Integer.parseInt(id) == agensi.indexOf(agensiVal)) {
                    it.remove();
                    break;
                }
            }
        } else {
            logger.debug("----- no data in session : take data from db ------");
            idOpsAgen = getContext().getRequest().getParameter("idOpsAgen");
            System.out.println("removeAgensi-idOpsAgen : " + idOpsAgen);
            operasiAgensi = enforcementService.findOperasiByIdOPAgensi(Long.parseLong(idOpsAgen));
            if (operasiAgensi != null) {
                enforcementService.deleteOperasiAgensi(operasiAgensi);
            }
        }

        addSimpleMessage("Maklumat telah berjaya dihapus.");
        return new RedirectResolution(KemasukanLaporanOperasi.class, "locate");
    }

    public Resolution refreshpage() {
        return new RedirectResolution(KemasukanLaporanOperasi.class, "locate");
    }

    public Resolution refreshPage() {
        logger.debug("refreshPage");
        return new RedirectResolution(KemasukanLaporanOperasi.class, "locate");
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getNoKp() {
        return noKp;
    }

    public void setNoKp(String noKp) {
        this.noKp = noKp;
    }

    public String getPeranan() {
        return peranan;
    }

    public void setPeranan(String peranan) {
        this.peranan = peranan;
    }

    public List<OperasiPenguatkuasaanPasukan> getSenaraiPasukan() {
        return senaraiPasukan;
    }

    public OperasiPenguatkuasaanPasukan getOperasiPenguatkuasaanPasukan() {
        return operasiPenguatkuasaanPasukan;
    }

    public void setOperasiPenguatkuasaanPasukan(OperasiPenguatkuasaanPasukan operasiPenguatkuasaanPasukan) {
        this.operasiPenguatkuasaanPasukan = operasiPenguatkuasaanPasukan;
    }

    public void setSenaraiPasukan(List<OperasiPenguatkuasaanPasukan> senaraiPasukan) {
        this.senaraiPasukan = senaraiPasukan;
    }

    public PermohonanRujukanLuar getPermohonanRujukanLuar() {
        return permohonanRujukanLuar;
    }

    public void setPermohonanRujukanLuar(PermohonanRujukanLuar permohonanRujukanLuar) {
        this.permohonanRujukanLuar = permohonanRujukanLuar;
    }

    public String getTarikhRujukan() {
        return tarikhRujukan;
    }

    public void setTarikhRujukan(String tarikhRujukan) {
        this.tarikhRujukan = tarikhRujukan;
    }

    public OperasiPenguatkuasaan getOperasiPenguatkuasaan() {
        return operasiPenguatkuasaan;
    }

    public void setOperasiPenguatkuasaan(OperasiPenguatkuasaan operasiPenguatkuasaan) {
        this.operasiPenguatkuasaan = operasiPenguatkuasaan;
    }

    public KodCawangan getCawangan() {
        return cawangan;
    }

    public void setCawangan(KodCawangan cawangan) {
        this.cawangan = cawangan;
    }

    public Pengguna getPguna() {
        return pguna;
    }

    public void setPguna(Pengguna pguna) {
        this.pguna = pguna;
    }

    public String getIdPermohonan() {
        return idPermohonan;
    }

    public void setIdPermohonan(String idPermohonan) {
        this.idPermohonan = idPermohonan;
    }

    public String getTarikhOperasi() {
        return tarikhOperasi;
    }

    public void setTarikhOperasi(String tarikhOperasi) {
        this.tarikhOperasi = tarikhOperasi;
    }

    public String getLokasi() {
        return lokasi;
    }

    public void setLokasi(String lokasi) {
        this.lokasi = lokasi;
    }

    public String getCatatan() {
        return catatan;
    }

    public void setCatatan(String catatan) {
        this.catatan = catatan;
    }

    public Permohonan getPermohonan() {
        return permohonan;
    }

    public void setPermohonan(Permohonan permohonan) {
        this.permohonan = permohonan;
    }

    public String getJenisTindakan() {
        return jenisTindakan;
    }

    public void setJenisTindakan(String jenisTindakan) {
        this.jenisTindakan = jenisTindakan;
    }

    public int getJumlahTangkapan() {
        return jumlahTangkapan;
    }

    public void setJumlahTangkapan(int jumlahTangkapan) {
        this.jumlahTangkapan = jumlahTangkapan;
    }

    public String getAmpm() {
        return ampm;
    }

    public void setAmpm(String ampm) {
        this.ampm = ampm;
    }

    public String getJam() {
        return jam;
    }

    public void setJam(String jam) {
        this.jam = jam;
    }

    public String getMinit() {
        return minit;
    }

    public void setMinit(String minit) {
        this.minit = minit;
    }

    public String getPlatKenderaan() {
        return platKenderaan;
    }

    public void setPlatKenderaan(String platKenderaan) {
        this.platKenderaan = platKenderaan;
    }

    public String getRecordCount() {
        return recordCount;
    }

    public void setRecordCount(String recordCount) {
        this.recordCount = recordCount;
    }

    public BigDecimal getNilaiKecurian() {
        return nilaiKecurian;
    }

    public void setNilaiKecurian(BigDecimal nilaiKecurian) {
        this.nilaiKecurian = nilaiKecurian;
    }

    public String getKadKuasa() {
        return kadKuasa;
    }

    public void setKadKuasa(String kadKuasa) {
        this.kadKuasa = kadKuasa;
    }

    public String getIdKetua() {
        return idKetua;
    }

    public void setIdKetua(String idKetua) {
        this.idKetua = idKetua;
    }

    public String getKadKuasaKetua() {
        return kadKuasaKetua;
    }

    public void setKadKuasaKetua(String kadKuasaKetua) {
        this.kadKuasaKetua = kadKuasaKetua;
    }

    public String getNamaKetua() {
        return namaKetua;
    }

    public void setNamaKetua(String namaKetua) {
        this.namaKetua = namaKetua;
    }

    public String getNoPengenalanKetua() {
        return noPengenalanKetua;
    }

    public void setNoPengenalanKetua(String noPengenalanKetua) {
        this.noPengenalanKetua = noPengenalanKetua;
    }

    public List<OperasiPenguatkuasaanPasukan> getSenaraiPasukanWithoutKetua() {
        return senaraiPasukanWithoutKetua;
    }

    public void setSenaraiPasukanWithoutKetua(List<OperasiPenguatkuasaanPasukan> senaraiPasukanWithoutKetua) {
        this.senaraiPasukanWithoutKetua = senaraiPasukanWithoutKetua;
    }

    public String getNamaKetuaDisabled() {
        return namaKetuaDisabled;
    }

    public void setNamaKetuaDisabled(String namaKetuaDisabled) {
        this.namaKetuaDisabled = namaKetuaDisabled;
    }

    public AduanLokasi getAduanLokasi() {
        return aduanLokasi;
    }

    public void setAduanLokasi(AduanLokasi aduanLokasi) {
        this.aduanLokasi = aduanLokasi;
    }

    public String getAmpm1() {
        return ampm1;
    }

    public void setAmpm1(String ampm1) {
        this.ampm1 = ampm1;
    }

    public String getHour() {
        return hour;
    }

    public void setHour(String hour) {
        this.hour = hour;
    }

    public String getMinute() {
        return minute;
    }

    public void setMinute(String minute) {
        this.minute = minute;
    }

    public String getTarikhPenahanan() {
        return tarikhPenahanan;
    }

    public void setTarikhPenahanan(String tarikhPenahanan) {
        this.tarikhPenahanan = tarikhPenahanan;
    }

    public List<OperasiAgensi> getSenaraiOperasiAgensi() {
        return senaraiOperasiAgensi;
    }

    public void setSenaraiOperasiAgensi(List<OperasiAgensi> senaraiOperasiAgensi) {
        this.senaraiOperasiAgensi = senaraiOperasiAgensi;
    }

    public String getIdAgensi() {
        return idAgensi;
    }

    public void setIdAgensi(String idAgensi) {
        this.idAgensi = idAgensi;
    }

    public String getNamaAgensi() {
        return namaAgensi;
    }

    public void setNamaAgensi(String namaAgensi) {
        this.namaAgensi = namaAgensi;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getJawatan() {
        return jawatan;
    }

    public void setJawatan(String jawatan) {
        this.jawatan = jawatan;
    }

    public String getJenisPengenalan() {
        return jenisPengenalan;
    }

    public void setJenisPengenalan(String jenisPengenalan) {
        this.jenisPengenalan = jenisPengenalan;
    }

    public String getNamaKetuaAgensi() {
        return namaKetuaAgensi;
    }

    public void setNamaKetuaAgensi(String namaKetuaAgensi) {
        this.namaKetuaAgensi = namaKetuaAgensi;
    }

    public String getNoPengenalan() {
        return noPengenalan;
    }

    public void setNoPengenalan(String noPengenalan) {
        this.noPengenalan = noPengenalan;
    }

    public String getNoTelefon() {
        return noTelefon;
    }

    public void setNoTelefon(String noTelefon) {
        this.noTelefon = noTelefon;
    }

    public ArrayList<AgensiValue> getSenaraiAgensi() {
        return senaraiAgensi;
    }

    public void setSenaraiAgensi(ArrayList<AgensiValue> senaraiAgensi) {
        this.senaraiAgensi = senaraiAgensi;
    }

    public String getKodAgensi() {
        return kodAgensi;
    }

    public void setKodAgensi(String kodAgensi) {
        this.kodAgensi = kodAgensi;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public OperasiAgensi getOperasiAgensi() {
        return operasiAgensi;
    }

    public void setOperasiAgensi(OperasiAgensi operasiAgensi) {
        this.operasiAgensi = operasiAgensi;
    }

    public String getIdOpsAgen() {
        return idOpsAgen;
    }

    public void setIdOpsAgen(String idOpsAgen) {
        this.idOpsAgen = idOpsAgen;
    }

    public Pengguna getPengguna() {
        return pengguna;
    }

    public void setPengguna(Pengguna pengguna) {
        this.pengguna = pengguna;
    }
}
