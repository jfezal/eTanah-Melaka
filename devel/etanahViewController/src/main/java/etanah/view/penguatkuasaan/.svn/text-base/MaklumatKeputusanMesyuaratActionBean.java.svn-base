/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.penguatkuasaan;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import etanah.dao.OperasiAgensiDAO;
import etanah.dao.PermohonanDAO;
import etanah.model.Permohonan;
import etanah.model.InfoAudit;
import etanah.model.KodCawangan;

import etanah.model.Pengguna;
import etanah.model.PermohonanLaporanUlasan;
import java.text.ParseException;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import etanah.service.common.EnforcementService;
import java.text.SimpleDateFormat;
import java.util.List;
import net.sourceforge.stripes.action.RedirectResolution;
import etanah.view.etanahActionBeanContext;
import java.util.Date;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.google.inject.Inject;
import etanah.dao.KehadiranMesyuaratPenguatkuasaanDAO;
import etanah.dao.KodAgensiDAO;
import etanah.dao.MesyuaratPenguatkuasaanDAO;
import etanah.dao.OperasiPenguatkuasaanDAO;
import etanah.model.AduanOrangKenaSyak;
import etanah.model.KehadiranMesyuaratPenguatkuasaan;
import etanah.model.KodAgensi;
import etanah.model.MesyuaratPenguatkuasaan;
import etanah.model.OperasiAgensi;
import etanah.model.OperasiPenguatkuasaan;
import etanah.service.BPelService;
import etanah.service.EnforceService;
import java.util.ArrayList;
import net.sourceforge.stripes.action.ForwardResolution;
import oracle.bpel.services.workflow.task.model.Task;

/**
 *
 * @author farah.shafilla
 */
@UrlBinding("/penguatkuasaan/maklumat_keputusan_mesyuarat")
public class MaklumatKeputusanMesyuaratActionBean extends AbleActionBean {

    private static Logger logger = Logger.getLogger(MaklumatKeputusanMesyuaratActionBean.class);
    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;
    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    OperasiAgensiDAO operasiAgensiDAO;
    @Inject
    OperasiPenguatkuasaanDAO operasiPenguatkuasaanDAO;
    @Inject
    EnforcementService enforcementService;
    @Inject
    EnforceService enforceService;
    @Inject
    KodAgensiDAO kodAgensiDAO;
    @Inject
    MesyuaratPenguatkuasaanDAO mesyuaratPenguatkuasaanDAO;
    @Inject
    KehadiranMesyuaratPenguatkuasaanDAO kehadiranMesyuaratPenguatkuasaanDAO;
    private Permohonan permohonan;
    private Pengguna pengguna;
    private MesyuaratPenguatkuasaan mesyuaratPenguatkuasaan;
    private OperasiAgensi operasiAgensi;
    private String idPermohonan;
    private String idMesyuarat;
    private String tarikhBerkumpul;
    private String jam;
    private String minit;
    private String saat;
    private String ampm;
    private String nama;
    private String noTel;
    private String email;
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss aaa");
    private List<KehadiranMesyuaratPenguatkuasaan> senaraiKehadiranMesyuaratPenguatkuasaan = new ArrayList<KehadiranMesyuaratPenguatkuasaan>();
    private String alamatAgensi;
    private String stageId;
    private String taskId;
    private String rowCount;
    private String kodAgensi;
    private String tempatBerkumpul;
    private List<MesyuaratPenguatkuasaan> senaraiMesyuarat = new ArrayList<MesyuaratPenguatkuasaan>();
    private String keputusan;
    private KehadiranMesyuaratPenguatkuasaan kehadiranMesyuaratPenguatkuasaan;
    private String jawatan;

    @DefaultHandler
    public Resolution showForm() {
        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        return new JSP("penguatkuasaan/maklumat_keputusan_mesyuarat.jsp").addParameter("tab", "true");
    }

    public Resolution showForm2() {
        getContext().getRequest().setAttribute("view", Boolean.TRUE);
        return new JSP("penguatkuasaan/maklumat_keputusan_mesyuarat.jsp").addParameter("tab", "true");
    }

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {
        logger.info("-----------rehydrate----------------");
        pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        taskId = (String) getContext().getRequest().getSession().getAttribute("taskId");

        if (idPermohonan != null) {
            permohonan = permohonanDAO.findById(idPermohonan);
            if (permohonan != null) {
                senaraiMesyuarat = enforcementService.findListMesyuarat(permohonan.getIdPermohonan());
            }
        }
    }

    public Resolution addRecord() {
        logger.info("--------------addRecord--------------");
        idMesyuarat = getContext().getRequest().getParameter("idMesyuarat");
        logger.info("::addRecord - idMesyuarat : " + idMesyuarat);

        if (StringUtils.isNotBlank(idMesyuarat)) {
            mesyuaratPenguatkuasaan = mesyuaratPenguatkuasaanDAO.findById(Long.parseLong(idMesyuarat));

            if (mesyuaratPenguatkuasaan != null) {
                if (mesyuaratPenguatkuasaan.getTarikh() != null) {
                    tarikhBerkumpul = sdf.format(mesyuaratPenguatkuasaan.getTarikh()).substring(0, 10);
                    jam = sdf.format(mesyuaratPenguatkuasaan.getTarikh()).substring(11, 13);
                    minit = sdf.format(mesyuaratPenguatkuasaan.getTarikh()).substring(14, 16);
                    saat = "00";
                    ampm = sdf.format(mesyuaratPenguatkuasaan.getTarikh()).substring(20, 22);
                }

                tempatBerkumpul = mesyuaratPenguatkuasaan.getTempat();
                keputusan = mesyuaratPenguatkuasaan.getKeputusan();

                senaraiKehadiranMesyuaratPenguatkuasaan = enforcementService.findListKehadiranMesyuarat(mesyuaratPenguatkuasaan.getIdMesyuarat());
                rowCount = String.valueOf(senaraiKehadiranMesyuaratPenguatkuasaan.size());
            }

        }
        return new JSP("penguatkuasaan/popup_keputusan_mesyuarat.jsp").addParameter("popup", "true");
    }

    public Resolution refreshPage() {
        rehydrate();
        return new RedirectResolution(MaklumatKeputusanMesyuaratActionBean.class, "locate");
    }

    public Resolution deleteSingle() {
        logger.info("-----------deleteSingle----------------");
        idMesyuarat = getContext().getRequest().getParameter("idMesyuarat");

        if (StringUtils.isNotBlank(idMesyuarat)) {
            mesyuaratPenguatkuasaan = mesyuaratPenguatkuasaanDAO.findById(Long.parseLong(idMesyuarat));

            if (mesyuaratPenguatkuasaan != null) {
                senaraiKehadiranMesyuaratPenguatkuasaan = enforcementService.findListKehadiranMesyuarat(mesyuaratPenguatkuasaan.getIdMesyuarat());
                for (int i = 0; i < senaraiKehadiranMesyuaratPenguatkuasaan.size(); i++) {
                    kehadiranMesyuaratPenguatkuasaan = kehadiranMesyuaratPenguatkuasaanDAO.findById(senaraiKehadiranMesyuaratPenguatkuasaan.get(i).getIdKehadiran());
                    enforcementService.deleteKehadiranMesyuaratPenguatkuasaan(kehadiranMesyuaratPenguatkuasaan);
                }
                enforcementService.deleteMesyuaratPenguatkuasaan(mesyuaratPenguatkuasaan);
            }
        }
        return new RedirectResolution(MaklumatKeputusanMesyuaratActionBean.class, "locate");
    }

    public Resolution deleteKehadiran() {
        String idKehadiran;
        idKehadiran = getContext().getRequest().getParameter("idKehadiran");
        System.out.println("id masa delete : " + idKehadiran);
        try {
            if (StringUtils.isNotBlank(idKehadiran)) {
                kehadiranMesyuaratPenguatkuasaan = kehadiranMesyuaratPenguatkuasaanDAO.findById(Long.parseLong(idKehadiran));
            }
            enforcementService.deleteKehadiranMesyuaratPenguatkuasaan(kehadiranMesyuaratPenguatkuasaan);
        } catch (Exception e) {
            e.printStackTrace();
            addSimpleError("kesalahan dalam menghapuskan rekod");
        }
        addSimpleMessage("Maklumat berjaya dihapuskan.");
        rehydrate();
        return new ForwardResolution("/WEB-INF/jsp/penguatkuasaan/popup_keputusan_mesyuarat.jsp").addParameter("popup", "true");
    }

    public Resolution simpanKpsnMesyuarat() throws Exception {
        logger.info("--------------simpanKpsnMesyuarat--------------");
        idMesyuarat = getContext().getRequest().getParameter("idMesyuarat");
        logger.info("::addRecord - dMesyuarat : " + idMesyuarat);

        if (StringUtils.isNotBlank(idMesyuarat)) {
            mesyuaratPenguatkuasaan = mesyuaratPenguatkuasaanDAO.findById(Long.parseLong(idMesyuarat));
            senaraiKehadiranMesyuaratPenguatkuasaan = enforcementService.findListKehadiranMesyuarat(mesyuaratPenguatkuasaan.getIdMesyuarat());
        }

        InfoAudit ia = new InfoAudit();
        if (mesyuaratPenguatkuasaan != null) {
            ia = mesyuaratPenguatkuasaan.getInfoAudit();
            ia.setDiKemaskiniOleh(pengguna);
            ia.setTarikhKemaskini(new java.util.Date());
        } else {
            mesyuaratPenguatkuasaan = new MesyuaratPenguatkuasaan();
            ia.setDimasukOleh(pengguna);
            ia.setTarikhMasuk(new java.util.Date());
        }
        mesyuaratPenguatkuasaan.setPermohonan(permohonan);
        mesyuaratPenguatkuasaan.setTempat(tempatBerkumpul);
        mesyuaratPenguatkuasaan.setKeputusan(keputusan);
        mesyuaratPenguatkuasaan.setInfoAudit(ia);

        if (StringUtils.isNotBlank(tarikhBerkumpul) && StringUtils.isNotBlank(jam) && StringUtils.isNotBlank(minit) && StringUtils.isNotBlank(ampm)) {
            tarikhBerkumpul = tarikhBerkumpul + " " + jam + ":" + minit + ":00 " + ampm;
            logger.info("tarikhBerkumpul :" + tarikhBerkumpul);
        }

        if (StringUtils.isNotBlank(tarikhBerkumpul)) {
            mesyuaratPenguatkuasaan.setTarikh(sdf.parse(tarikhBerkumpul));
        }
        enforcementService.simpanMesyuaratPenguatkuasaan(mesyuaratPenguatkuasaan);



//        senaraiOperasiAgensi = enforcementService.findOperasiByIDOpeasi(operasiPenguatkuasaan.getIdOperasi());
        if (StringUtils.isNotBlank(rowCount)) {
            for (int i = 0; i < Integer.parseInt(rowCount); i++) {
                if (senaraiKehadiranMesyuaratPenguatkuasaan.size() != 0 && i < senaraiKehadiranMesyuaratPenguatkuasaan.size()) {
                    Long id = senaraiKehadiranMesyuaratPenguatkuasaan.get(i).getIdKehadiran();
                    if (id != null) {
                        kehadiranMesyuaratPenguatkuasaan = kehadiranMesyuaratPenguatkuasaanDAO.findById(id);
                        ia = kehadiranMesyuaratPenguatkuasaan.getInfoAudit();
                        ia.setDiKemaskiniOleh(pengguna);
                        ia.setTarikhKemaskini(new java.util.Date());
                    } else {
                        System.out.println("create new operasiAgensi if list not empty");
                        kehadiranMesyuaratPenguatkuasaan = new KehadiranMesyuaratPenguatkuasaan();
                        ia.setDimasukOleh(pengguna);
                        ia.setTarikhMasuk(new java.util.Date());
                    }
                } else {
                    System.out.println("create new operasiAgensi if list empty");
                    kehadiranMesyuaratPenguatkuasaan = new KehadiranMesyuaratPenguatkuasaan();
                    ia.setDimasukOleh(pengguna);
                    ia.setTarikhMasuk(new java.util.Date());
                }

                kodAgensi = getContext().getRequest().getParameter("kodAgensi" + i);
                nama = getContext().getRequest().getParameter("nama" + i);
                jawatan = getContext().getRequest().getParameter("jawatan" + i);
                email = getContext().getRequest().getParameter("email" + i);
                alamatAgensi = getContext().getRequest().getParameter("alamatAgensi" + i);

                kehadiranMesyuaratPenguatkuasaan.setMesyuaratPenguatkuasaan(mesyuaratPenguatkuasaan);
                kehadiranMesyuaratPenguatkuasaan.setKodAgensi(kodAgensiDAO.findById(kodAgensi));
                kehadiranMesyuaratPenguatkuasaan.setNama(nama);
                kehadiranMesyuaratPenguatkuasaan.setEmail(email);
                kehadiranMesyuaratPenguatkuasaan.setAlamatAgensi(alamatAgensi);
                kehadiranMesyuaratPenguatkuasaan.setInfoAudit(ia);
                kehadiranMesyuaratPenguatkuasaan.setJawatan(jawatan);
                enforcementService.simpanKehadiranMesyuaratPenguatkuasaan(kehadiranMesyuaratPenguatkuasaan);

            }
        }

        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        return new JSP("penguatkuasaan/maklumat_keputusan_mesyuarat.jsp").addParameter("tab", "true");
    }

    public Resolution refreshpageNotis() {
        getContext().getRequest().setAttribute("addKpsnMesyuarat", Boolean.TRUE);
        rehydrate();
        return new JSP("penguatkuasaan/surat_bantuan_agensi.jsp").addParameter("tab", "true");
    }

    public Resolution viewMaklumatAgensi() {
        logger.info("--------------viewMaklumatAgensi--------------");

        getContext().getRequest().setAttribute("viewMaklumatAgensi", Boolean.TRUE);
        return new JSP("penguatkuasaan/popup_tambah_keputusan_mesyuarat.jsp").addParameter("popup", "true");
    }

    public String getIdPermohonan() {
        return idPermohonan;
    }

    public void setIdPermohonan(String idPermohonan) {
        this.idPermohonan = idPermohonan;
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

    public OperasiAgensi getOperasiAgensi() {
        return operasiAgensi;
    }

    public void setOperasiAgensi(OperasiAgensi operasiAgensi) {
        this.operasiAgensi = operasiAgensi;
    }

    public String getTarikhBerkumpul() {
        return tarikhBerkumpul;
    }

    public void setTarikhBerkumpul(String tarikhBerkumpul) {
        this.tarikhBerkumpul = tarikhBerkumpul;
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

    public String getSaat() {
        return saat;
    }

    public void setSaat(String saat) {
        this.saat = saat;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getNoTel() {
        return noTel;
    }

    public void setNoTel(String noTel) {
        this.noTel = noTel;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAlamatAgensi() {
        return alamatAgensi;
    }

    public void setAlamatAgensi(String alamatAgensi) {
        this.alamatAgensi = alamatAgensi;
    }

    public String getStageId() {
        return stageId;
    }

    public void setStageId(String stageId) {
        this.stageId = stageId;
    }

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public String getRowCount() {
        return rowCount;
    }

    public void setRowCount(String rowCount) {
        this.rowCount = rowCount;
    }

    public String getKodAgensi() {
        return kodAgensi;
    }

    public void setKodAgensi(String kodAgensi) {
        this.kodAgensi = kodAgensi;
    }

    public String getTempatBerkumpul() {
        return tempatBerkumpul;
    }

    public void setTempatBerkumpul(String tempatBerkumpul) {
        this.tempatBerkumpul = tempatBerkumpul;
    }

    public List<MesyuaratPenguatkuasaan> getSenaraiMesyuarat() {
        return senaraiMesyuarat;
    }

    public void setSenaraiMesyuarat(List<MesyuaratPenguatkuasaan> senaraiMesyuarat) {
        this.senaraiMesyuarat = senaraiMesyuarat;
    }

    public String getIdMesyuarat() {
        return idMesyuarat;
    }

    public void setIdMesyuarat(String idMesyuarat) {
        this.idMesyuarat = idMesyuarat;
    }

    public List<KehadiranMesyuaratPenguatkuasaan> getSenaraiKehadiranMesyuaratPenguatkuasaan() {
        return senaraiKehadiranMesyuaratPenguatkuasaan;
    }

    public void setSenaraiKehadiranMesyuaratPenguatkuasaan(List<KehadiranMesyuaratPenguatkuasaan> senaraiKehadiranMesyuaratPenguatkuasaan) {
        this.senaraiKehadiranMesyuaratPenguatkuasaan = senaraiKehadiranMesyuaratPenguatkuasaan;
    }

    public String getKeputusan() {
        return keputusan;
    }

    public void setKeputusan(String keputusan) {
        this.keputusan = keputusan;
    }

    public MesyuaratPenguatkuasaan getMesyuaratPenguatkuasaan() {
        return mesyuaratPenguatkuasaan;
    }

    public void setMesyuaratPenguatkuasaan(MesyuaratPenguatkuasaan mesyuaratPenguatkuasaan) {
        this.mesyuaratPenguatkuasaan = mesyuaratPenguatkuasaan;
    }

    public KehadiranMesyuaratPenguatkuasaan getKehadiranMesyuaratPenguatkuasaan() {
        return kehadiranMesyuaratPenguatkuasaan;
    }

    public void setKehadiranMesyuaratPenguatkuasaan(KehadiranMesyuaratPenguatkuasaan kehadiranMesyuaratPenguatkuasaan) {
        this.kehadiranMesyuaratPenguatkuasaan = kehadiranMesyuaratPenguatkuasaan;
    }

    public String getJawatan() {
        return jawatan;
    }

    public void setJawatan(String jawatan) {
        this.jawatan = jawatan;
    }
}
