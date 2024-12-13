/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.strata;

import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.dao.PermohonanDAO;
import etanah.dao.PermohonanKertasKandunganDAO;
import etanah.model.InfoAudit;
import etanah.model.LanjutanTempoh;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.model.PermohonanKertas;
import etanah.model.PermohonanKertasKandungan;
import etanah.service.BPelService;
import etanah.service.StrataPtService;
import etanah.service.common.PermohonanService;
import net.sourceforge.stripes.action.UrlBinding;
import etanah.view.BasicTabActionBean;
import etanah.view.etanahActionBeanContext;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.controller.LifecycleStage;
import oracle.bpel.services.workflow.task.model.Task;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

/**
 *
 * @author User
 */
@UrlBinding("/strata/maklumat_rayuan")
public class MaklumatRayuanActionBean extends BasicTabActionBean {

    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    PermohonanService mohonService;
    @Inject
    StrataPtService strService;
    @Inject
    PermohonanKertasKandunganDAO permohonanKertasKandDAO;
    private String tempohlanjutan;
    private String lanjutantempo;
    private Permohonan permohonan;
    private LanjutanTempoh lanjutTempoh;
    private Integer tempohHari = 60;
    private String sebab;
    private String idPermohonan;
    private Date tarikhPohon = new Date();
    private List<PermohonanKertasKandungan> listMohonKertasKand = new ArrayList<PermohonanKertasKandungan>();
    private PermohonanKertas mohonKertas = new PermohonanKertas();
    private static final Logger LOG = Logger.getLogger(MaklumatRayuanActionBean.class);
    private String kandungan;
    private boolean readOnly = false;

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = permohonanDAO.findById(idPermohonan);
        mohonKertas = strService.findKertas(idPermohonan);
        if (mohonKertas != null) {
            listMohonKertasKand = strService.findByIdLapor(mohonKertas.getIdKertas(), 2);

            // check kalau empty, button simpanKaliPertama is true
            if (!listMohonKertasKand.isEmpty()) {
                getContext().getRequest().setAttribute("kaliPertama", Boolean.FALSE);
            } else {
                getContext().getRequest().setAttribute("kaliPertama", Boolean.TRUE);
            }
        } else {
            getContext().getRequest().setAttribute("kaliPertama", Boolean.TRUE);
        }
        lanjutTempoh = strService.findMohonLanjutTempoh(permohonan.getSenaraiHakmilik().get(0).getId());

        if (lanjutTempoh != null) {
            tarikhPohon = lanjutTempoh.getTarikhAkhirDipohon();
        }

        // added by zadirul
        if (permohonan.getKodUrusan().getKod().equals("PNB")) {
            getContext().getRequest().setAttribute("displayTarikh", Boolean.FALSE);
            getContext().getRequest().setAttribute("headerRayuan", Boolean.FALSE);
        } else {
            getContext().getRequest().setAttribute("displayTarikh", Boolean.TRUE);
            getContext().getRequest().setAttribute("headerRayuan", Boolean.TRUE);
        }
        if (permohonan.getKodUrusan().getKod().equals("RMHS1")) {
            getContext().getRequest().setAttribute("displayTarikh", Boolean.FALSE);
            getContext().getRequest().setAttribute("headerRayuan", Boolean.TRUE);
        }
        if (permohonan.getKodUrusan().getKod().equals("RMH1A")) {
            getContext().getRequest().setAttribute("displayTarikh", Boolean.FALSE);
            getContext().getRequest().setAttribute("headerRayuan", Boolean.TRUE);
        }
        if (permohonan.getKodUrusan().getKod().equals("RMHS5")) {
            getContext().getRequest().setAttribute("displayTarikh", Boolean.FALSE);
            getContext().getRequest().setAttribute("headerRayuan", Boolean.TRUE);
        }
        if (permohonan.getKodUrusan().getKod().equals("RMHS6")) {
            getContext().getRequest().setAttribute("displayTarikh", Boolean.FALSE);
            getContext().getRequest().setAttribute("headerRayuan", Boolean.TRUE);
        }
        if (permohonan.getKodUrusan().getKod().equals("RMHS7")) {
            getContext().getRequest().setAttribute("displayTarikh", Boolean.FALSE);
            getContext().getRequest().setAttribute("headerRayuan", Boolean.TRUE);
        }


    }

    public Resolution tambahRow() {
        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");

        String taskId = (String) getContext().getRequest().getSession().getAttribute("taskId");
        String stageId = "";
        BPelService service = new BPelService();
        Pengguna pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        if (kandungan == null && listMohonKertasKand.isEmpty()) {
            addSimpleError("Sila masukkan maklumat dan simpan pada ruangan 2.1 sebelum klik butang 'Tambah'.");
            getContext().getRequest().setAttribute("edit1", Boolean.TRUE);
            getContext().getRequest().setAttribute("edit", Boolean.TRUE);
            return new JSP("strata/Lanjutan_Tempo_Mohon/maklumat_rayuan.jsp").addParameter("tab", "true");
        }
        InfoAudit ia = new InfoAudit();
        ia.setDimasukOleh(pengguna);
        ia.setTarikhMasuk(new Date());
        Permohonan permohonan = permohonanDAO.findById(idPermohonan);
        mohonKertas = strService.findKertas(idPermohonan);
        if (mohonKertas == null) {
            mohonKertas = new PermohonanKertas();
            mohonKertas.setTajuk("Kertas Pertimbangan PTG");
            mohonKertas.setCawangan(permohonan.getCawangan());
            mohonKertas.setInfoAudit(ia);
            mohonKertas.setPermohonan(permohonan);
            strService.simpanPermohonanKertas(mohonKertas);
        }
        PermohonanKertasKandungan pkk = new PermohonanKertasKandungan();
        int index = 0;
        index = Integer.parseInt(getContext().getRequest().getParameter("index"));
        switch (index) {
            case 1:
                break;
            case 2:
                pkk = new PermohonanKertasKandungan();
                pkk.setSubtajuk(String.valueOf(lastNumberKertasKandungan(mohonKertas.getIdKertas(), 2)));
                pkk.setBil((short) 2);
                listMohonKertasKand.add(pkk);
                break;

            default:
        }

        // added by zadirul
        if (permohonan.getKodUrusan().getKod().equals("PNB")) {
            getContext().getRequest().setAttribute("displayTarikh", Boolean.FALSE);
            getContext().getRequest().setAttribute("headerRayuan", Boolean.FALSE);
        } else {
            getContext().getRequest().setAttribute("displayTarikh", Boolean.TRUE);
            getContext().getRequest().setAttribute("headerRayuan", Boolean.TRUE);
        }
        if (permohonan.getKodUrusan().getKod().equals("RMHS1")) {
            getContext().getRequest().setAttribute("displayTarikh", Boolean.FALSE);
            getContext().getRequest().setAttribute("headerRayuan", Boolean.TRUE);
        }
        if (permohonan.getKodUrusan().getKod().equals("RMH1A")) {
            getContext().getRequest().setAttribute("displayTarikh", Boolean.FALSE);
            getContext().getRequest().setAttribute("headerRayuan", Boolean.TRUE);
        }
        if (permohonan.getKodUrusan().getKod().equals("RMHS5")) {
            getContext().getRequest().setAttribute("displayTarikh", Boolean.FALSE);
            getContext().getRequest().setAttribute("headerRayuan", Boolean.TRUE);
        }
        if (permohonan.getKodUrusan().getKod().equals("RMHS6")) {
            getContext().getRequest().setAttribute("displayTarikh", Boolean.FALSE);
            getContext().getRequest().setAttribute("headerRayuan", Boolean.TRUE);
        }
        if (permohonan.getKodUrusan().getKod().equals("RMHS7")) {
            getContext().getRequest().setAttribute("displayTarikh", Boolean.FALSE);
            getContext().getRequest().setAttribute("headerRayuan", Boolean.TRUE);
        }

        getContext().getRequest().setAttribute("edit1", Boolean.TRUE);
        getContext().getRequest().setAttribute("edit", Boolean.TRUE);

        return new JSP("strata/Lanjutan_Tempo_Mohon/maklumat_rayuan.jsp").addParameter("tab", "true");
    }

    private Integer lastNumberKertasKandungan(Long idKertas, int bil) {
        int last = 1;
        List<PermohonanKertasKandungan> listLastKandungan = strService.findByIdLapor(idKertas, bil);
        if (listLastKandungan.isEmpty()) {
            last = 1;
        } else {
            last = Integer.parseInt(listLastKandungan.get(listLastKandungan.size() - 1).getSubtajuk()) + 1;
        }
        return last;
    }

    public Resolution deleteRow() {

        String taskId = (String) getContext().getRequest().getSession().getAttribute("taskId");
        String stageId = "";
        BPelService service = new BPelService();


        System.out.println("-----------deleteKandungan---------");
        String idKand = getContext().getRequest().getParameter("idKandungan");
        System.out.println("-----------deleteSingle---------" + idKand);
        if (idKand != null) {
            PermohonanKertasKandungan plk = new PermohonanKertasKandungan();
            plk = permohonanKertasKandDAO.findById(Long.parseLong(idKand));
            if (plk != null) {

                try {
                    strService.deleteKertasKandungan(plk);
                } catch (Exception e) {
                }
            }
        }
        rehydrate();

        getContext().getRequest().setAttribute("edit1", Boolean.TRUE);
        getContext().getRequest().setAttribute("edit", Boolean.TRUE);

        return new JSP("strata/Lanjutan_Tempo_Mohon/maklumat_rayuan.jsp").addParameter("tab", "true");
    }

    @DefaultHandler
    public Resolution showForm() {
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = permohonanDAO.findById(idPermohonan);
        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        if (permohonan.getTempohHari() != 0) {
            tempohHari = permohonan.getTempohHari();
        } else {
        }
        lanjutTempoh = strService.findMohonLanjutTempoh(permohonan.getSenaraiHakmilik().get(0).getId());
        if (lanjutTempoh != null) {
            tarikhPohon = lanjutTempoh.getTarikhAkhirDipohon();
        }
        sebab = permohonan.getSebab();
        Pengguna pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        BPelService service = new BPelService();
        String taskId = (String) getContext().getRequest().getSession().getAttribute("taskId");
        if (StringUtils.isNotBlank(taskId)) {
            Task t = null;
            t = service.getTaskFromBPel(taskId, pengguna);
            stageId = t.getSystemAttributes().getStage();
        }

        // added by zadirul
        if (permohonan.getKodUrusan().getKod().equals("PNB")) {
            getContext().getRequest().setAttribute("displayTarikh", Boolean.FALSE);
            getContext().getRequest().setAttribute("headerRayuan", Boolean.FALSE);
        } else {
            getContext().getRequest().setAttribute("displayTarikh", Boolean.TRUE);
            getContext().getRequest().setAttribute("headerRayuan", Boolean.TRUE);
        }
        if (permohonan.getKodUrusan().getKod().equals("RMHS1")) {
            getContext().getRequest().setAttribute("displayTarikh", Boolean.FALSE);
            getContext().getRequest().setAttribute("headerRayuan", Boolean.TRUE);
        }
        if (permohonan.getKodUrusan().getKod().equals("RMH1A")) {
            getContext().getRequest().setAttribute("displayTarikh", Boolean.FALSE);
            getContext().getRequest().setAttribute("headerRayuan", Boolean.TRUE);
        }
        if (permohonan.getKodUrusan().getKod().equals("RMHS5")) {
            getContext().getRequest().setAttribute("displayTarikh", Boolean.FALSE);
            getContext().getRequest().setAttribute("headerRayuan", Boolean.TRUE);
        }
        if (permohonan.getKodUrusan().getKod().equals("RMHS6")) {
            getContext().getRequest().setAttribute("displayTarikh", Boolean.FALSE);
            getContext().getRequest().setAttribute("headerRayuan", Boolean.TRUE);
        }
        if (permohonan.getKodUrusan().getKod().equals("RMHS7")) {
            getContext().getRequest().setAttribute("displayTarikh", Boolean.FALSE);
            getContext().getRequest().setAttribute("headerRayuan", Boolean.TRUE);
        }


        return new JSP("strata/Lanjutan_Tempo_Mohon/maklumat_rayuan.jsp").addParameter("tab", "true");
    }

    public Resolution showForm2() {
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        readOnly = true;
        getContext().getRequest().setAttribute("edit1", Boolean.FALSE);
        getContext().getRequest().setAttribute("edit", Boolean.FALSE);
        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        InfoAudit infoAudit = new InfoAudit();
        infoAudit.setDimasukOleh(peng);
        infoAudit.setTarikhMasuk(new java.util.Date());

        permohonan = permohonanDAO.findById(idPermohonan);

        // added by zadirul
        if (permohonan.getKodUrusan().getKod().equals("PNB")) {
            getContext().getRequest().setAttribute("displayTarikh", Boolean.FALSE);
            getContext().getRequest().setAttribute("headerRayuan", Boolean.FALSE);
        } else {
            getContext().getRequest().setAttribute("displayTarikh", Boolean.TRUE);
            getContext().getRequest().setAttribute("headerRayuan", Boolean.TRUE);
        }
        if (permohonan.getKodUrusan().getKod().equals("RMHS1")) {
            getContext().getRequest().setAttribute("displayTarikh", Boolean.FALSE);
            getContext().getRequest().setAttribute("headerRayuan", Boolean.TRUE);
        }
        if (permohonan.getKodUrusan().getKod().equals("RMH1A")) {
            getContext().getRequest().setAttribute("displayTarikh", Boolean.FALSE);
            getContext().getRequest().setAttribute("headerRayuan", Boolean.TRUE);
        }
        if (permohonan.getKodUrusan().getKod().equals("RMHS5")) {
            getContext().getRequest().setAttribute("displayTarikh", Boolean.FALSE);
            getContext().getRequest().setAttribute("headerRayuan", Boolean.TRUE);
        }
        if (permohonan.getKodUrusan().getKod().equals("RMHS6")) {
            getContext().getRequest().setAttribute("displayTarikh", Boolean.FALSE);
            getContext().getRequest().setAttribute("headerRayuan", Boolean.TRUE);
        }
        if (permohonan.getKodUrusan().getKod().equals("RMHS7")) {
            getContext().getRequest().setAttribute("displayTarikh", Boolean.FALSE);
            getContext().getRequest().setAttribute("headerRayuan", Boolean.TRUE);
        }



        return new JSP("strata/Lanjutan_Tempo_Mohon/maklumat_rayuan.jsp").addParameter("tab", "true");
    }

    public Resolution simpan() {
        Pengguna pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);

        InfoAudit ia = new InfoAudit();
        ia.setDimasukOleh(pengguna);
        ia.setTarikhMasuk(new Date());
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        getContext().getRequest().setAttribute("edit", Boolean.TRUE);


        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);

        permohonan = permohonanDAO.findById(idPermohonan);
        mohonKertas = strService.findKertas(idPermohonan);
        if (mohonKertas != null) {
        } else {
            ia.setDimasukOleh(peng);
            ia.setTarikhMasuk(new Date());
            mohonKertas = new PermohonanKertas();
            mohonKertas.setCawangan(permohonan.getCawangan());
            mohonKertas.setPermohonan(permohonan);
            mohonKertas.setInfoAudit(ia);
            mohonKertas.setTajuk("Kertas Draf Rayuan");
            mohonKertas = strService.simpanPermohonanKertasObject(mohonKertas);
        }
        int bil = 1;
        for (PermohonanKertasKandungan pkk : listMohonKertasKand) {
            ia.setTarikhKemaskini(new Date());
            pkk.setInfoAudit(ia);
            pkk.setKertas(mohonKertas);
            pkk.setCawangan(permohonan.getCawangan());
            if (StringUtils.isNotBlank(pkk.getKandungan())) {
                strService.simpanPermohonanKertasKandungan(pkk);
            } else {
                addSimpleError("Sila pastikan maklumat 2." + bil);
                return new JSP("strata/Lanjutan_Tempo_Mohon/maklumat_rayuan.jsp").addParameter("tab", "true");
            }
            bil++;
        }
        lanjutTempoh = strService.findMohonLanjutTempoh(permohonan.getSenaraiHakmilik().get(0).getId());
        if (lanjutTempoh != null) {
        } else {
            lanjutTempoh = new LanjutanTempoh();
        }
        lanjutTempoh.setTarikhAkhirDipohon(tarikhPohon);
        lanjutTempoh.setCawangan(permohonan.getCawangan());
        lanjutTempoh.setHakmilikPermohonan(permohonan.getSenaraiHakmilik().get(0));
        lanjutTempoh.setInfoAudit(ia);
        lanjutTempoh = strService.saveLanjutTempoh(lanjutTempoh);
        rehydrate();
        addSimpleMessage("Maklumat berjaya disimpan");
        return new JSP("strata/Lanjutan_Tempo_Mohon/maklumat_rayuan.jsp").addParameter("tab", "true");

    }

    public Resolution simpanKaliPertama() {
        Pengguna pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        getContext().getRequest().setAttribute("edit", Boolean.TRUE);

        InfoAudit ia = new InfoAudit();
        ia.setDimasukOleh(pengguna);
        ia.setTarikhMasuk(new Date());
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");

        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);

        permohonan = permohonanDAO.findById(idPermohonan);
        mohonKertas = strService.findKertas(idPermohonan);
        LOG.info("==InSaving MohonKertas==");
        if (mohonKertas != null) {
        } else {
            ia.setDimasukOleh(peng);
            ia.setTarikhMasuk(new Date());
            mohonKertas = new PermohonanKertas();
            mohonKertas.setCawangan(permohonan.getCawangan());
            mohonKertas.setPermohonan(permohonan);
            mohonKertas.setInfoAudit(ia);
            mohonKertas.setTajuk("Kertas Draf Rayuan");
            mohonKertas = strService.simpanPermohonanKertasObject(mohonKertas);
        }
        LOG.info("==InSaving MohonKertasKand==");
        PermohonanKertasKandungan pkk = new PermohonanKertasKandungan();
        pkk.setInfoAudit(ia);
        pkk.setBil((short) 2);
        pkk.setSubtajuk("1");
        if (kandungan != null) {
            pkk.setKandungan(kandungan);
        } else {
            addSimpleError("Sila isi maklumat 2.1");
            return new JSP("strata/Lanjutan_Tempo_Mohon/maklumat_rayuan.jsp").addParameter("tab", "true");
        }
        pkk.setKertas(mohonKertas);
        pkk.setCawangan(permohonan.getCawangan());
        strService.simpanPermohonanKertasKandungan(pkk);

        if (!permohonan.getKodUrusan().getKod().equals("RMHS1")) {
            lanjutTempoh = strService.findMohonLanjutTempoh(permohonan.getSenaraiHakmilik().get(0).getId());
            if (lanjutTempoh != null) {
            } else {
                lanjutTempoh = new LanjutanTempoh();
            }
            lanjutTempoh.setTarikhAkhirDipohon(tarikhPohon);
            lanjutTempoh.setCawangan(permohonan.getCawangan());
            lanjutTempoh.setHakmilikPermohonan(permohonan.getSenaraiHakmilik().get(0));
            lanjutTempoh.setInfoAudit(ia);
            lanjutTempoh = strService.saveLanjutTempoh(lanjutTempoh);

        }

        rehydrate();
        addSimpleMessage("Maklumat berjaya disimpan");
        return new JSP("strata/Lanjutan_Tempo_Mohon/maklumat_rayuan.jsp").addParameter("tab", "true");

    }

    public List<PermohonanKertasKandungan> getListMohonKertasKand() {
        return listMohonKertasKand;
    }

    public void setListMohonKertasKand(List<PermohonanKertasKandungan> listMohonKertasKand) {
        this.listMohonKertasKand = listMohonKertasKand;
    }

    public String getSebab() {
        return sebab;
    }

    public void setSebab(String sebab) {
        this.sebab = sebab;
    }

    public Integer getTempohHari() {
        return tempohHari;
    }

    public void setTempohHari(Integer tempohHari) {
        this.tempohHari = tempohHari;
    }

    public Permohonan getPermohonan() {
        return permohonan;
    }

    public void setPermohonan(Permohonan permohonan) {
        this.permohonan = permohonan;
    }

    public String getLanjutantempo() {
        return lanjutantempo;
    }

    public void setLanjutantempo(String lanjutantempo) {
        this.lanjutantempo = lanjutantempo;
    }

    public String getTempohlanjutan() {
        return tempohlanjutan;
    }

    public void setTempohlanjutan(String tempohlanjutan) {
        this.tempohlanjutan = tempohlanjutan;
    }

    public LanjutanTempoh getLanjutTempoh() {
        return lanjutTempoh;
    }

    public void setLanjutTempoh(LanjutanTempoh lanjutTempoh) {
        this.lanjutTempoh = lanjutTempoh;
    }

    public Date getTarikhPohon() {
        return tarikhPohon;
    }

    public void setTarikhPohon(Date tarikhPohon) {
        this.tarikhPohon = tarikhPohon;
    }

    public String getKandungan() {
        return kandungan;
    }

    public void setKandungan(String kandungan) {
        this.kandungan = kandungan;
    }

    public boolean isReadOnly() {
        return readOnly;
    }

    public void setReadOnly(boolean readOnly) {
        this.readOnly = readOnly;
    }
}
