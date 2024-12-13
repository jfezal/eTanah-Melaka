/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.strata;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.dao.PermohonanDAO;
import etanah.model.LaporanTanah;
import etanah.model.Pemohon;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.model.HakmilikPermohonan;
import etanah.model.HakmilikPihakBerkepentingan;
import etanah.model.PermohonanLaporanKandungan;
import etanah.service.BPelService;
import etanah.service.StrataPtService;
import etanah.service.common.HakmilikPihakKepentinganService;
import etanah.service.common.PemohonService;
import etanah.view.etanahActionBeanContext;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;
import oracle.bpel.services.workflow.task.model.Task;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

/**
 *
 * @author faidzal
 */
@UrlBinding("/strata/laporanTanah")
public class LaporanTanahMelakaActionBean extends AbleActionBean {

    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    StrataPtService strService;
    @Inject
    CommonService comm;
    @Inject
    HakmilikPihakKepentinganService hakmilikPihakKepentinganService;
    @Inject
    PemohonService pemohonService;
    private LaporanTanah laporanTanah;
    private List<PermohonanLaporanKandungan> listLaporanTanah1 = new ArrayList<PermohonanLaporanKandungan>();
    private List<PermohonanLaporanKandungan> listLaporanTanah2 = new ArrayList<PermohonanLaporanKandungan>();
    private List<PermohonanLaporanKandungan> listLaporanTanah3 = new ArrayList<PermohonanLaporanKandungan>();
    private List<PermohonanLaporanKandungan> listLaporanTanah6 = new ArrayList<PermohonanLaporanKandungan>();
    private List<PermohonanLaporanKandungan> listLaporanTanah7 = new ArrayList<PermohonanLaporanKandungan>();
    List<HakmilikPihakBerkepentingan> listHakmilikPihak;
    private Permohonan permohonan;
    private Pengguna pengguna;
    private String perihalPermohonan;
    private String kedudukanTanah;
    private String keadaanTanah;
    private String lainlainperkara;
    private String syorPerakuan;
    private String namaPemohon;
    private String jenisluas;
    private boolean readOnly = false;
    private boolean stagePPTG = true;
    private Date tarikhSiasatan;
    private static final Logger LOG = Logger.getLogger(LaporanTanahMelakaActionBean.class);

    @DefaultHandler
    public Resolution showForm() {
        pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        String taskId = (String) getContext().getRequest().getSession().getAttribute("taskId");
//        comm.setPengguna(pengguna);
//        if (!comm.stageId(taskId).equals("sedialaporan")) {
//            readOnly = true;
//        }
        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        return new JSP("strata/pbbm/laporanTanah_melaka.jsp").addParameter("tab", "true");
    }

    public Resolution readOnly() {
        getContext().getRequest().setAttribute("edit", Boolean.FALSE);
        return new JSP("strata/pbbm/laporanTanah_melaka.jsp").addParameter("tab", "true");
    }

    public Resolution rayuanLaporan() {
        return new JSP("strata/pbbm/laporanTanah_melaka.jsp").addParameter("tab", "true");
    }

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {
        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = permohonanDAO.findById(idPermohonan);

        if (permohonan.getKodUrusan().getKod().equals("PHPP") || permohonan.getKodUrusan().getKod().equals("PHPC")) {
            for (HakmilikPermohonan hkm : permohonan.getSenaraiHakmilik()) {
                if (hkm.getHakmilik().getKodStatusHakmilik().getKod().equals("D")) {
                    listHakmilikPihak = hakmilikPihakKepentinganService.findHakmilikPihakByKod(hkm.getHakmilik(), "PM");
                    LOG.info("PEMILIK =========>" + listHakmilikPihak.size());
                    LOG.info("PEMILIK =========>" + listHakmilikPihak.toString());
                }
            }
        }

        String stageId = "";
        BPelService service = new BPelService();
        if (permohonan.getKodUrusan().getKod().equals("RTPS") || permohonan.getKodUrusan().getKod().equals("RTHS")) {
            readOnly = false;
        }
        pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        String taskId = (String) getContext().getRequest().getSession().getAttribute("taskId");
        if (StringUtils.isNotBlank(taskId)) {
            Task t = null;
            t = service.getTaskFromBPel(taskId, pengguna);
            stageId = t.getSystemAttributes().getStage();
        }
        permohonan = permohonanDAO.findById(idPermohonan);
        if (permohonan.getPermohonanSebelum() != null) {
            idPermohonan = permohonan.getPermohonanSebelum().getIdPermohonan();
            readOnly = true;

            if (permohonan.getKodUrusan().getKod().equals("PNB")) {
                idPermohonan = permohonan.getIdPermohonan();
                readOnly = false;
                if (stageId.equals("sedialaporan")) {
                    stagePPTG = false;
                }

            }
            if (permohonan.getKodUrusan().getKod().equals("RTPS") || permohonan.getKodUrusan().getKod().equals("RTHS")) {
                idPermohonan = permohonan.getIdPermohonan();
                readOnly = false;
                if (stageId.equals("sedialaporan")) {
                    stagePPTG = false;
                }
            }
        } else {
            idPermohonan = permohonan.getIdPermohonan();
        }
        laporanTanah = strService.findLaporanTanahByIdPermohonan(idPermohonan);
        if (laporanTanah != null) {
            LOG.info("--- Laporan Tanah tak null ---");
        } else {
            LOG.info("--- Laporan Tanah null, create new ---");
            permohonan = permohonanDAO.findById(idPermohonan);
            laporanTanah = new LaporanTanah();
            laporanTanah.setPermohonan(permohonan);
            laporanTanah.setInfoAudit(strService.getInfo(pengguna));
            strService.simpanLaporan(laporanTanah);
        }
        if (laporanTanah != null) {
            LOG.info("ID LAPOR = " + laporanTanah.getIdLaporan());
            listLaporanTanah1 = strService.findByIdLaporKand(laporanTanah.getIdLaporan(), 1);

            if (!listLaporanTanah1.isEmpty()) {
                perihalPermohonan = listLaporanTanah1.get(0).getKand();
                tarikhSiasatan = laporanTanah.getTarikhSiasat();
                LOG.info("TARIKH SIASAT = " + tarikhSiasatan);
            } else {
                namaPemohon = null;
                Pemohon pemohon1 = new Pemohon();
                List<Pemohon> pemohonList = new ArrayList<Pemohon>();
                if (permohonan.getPermohonanSebelum() != null) {
                    LOG.info("ID SEBELUM, WUJUD : " + permohonan.getPermohonanSebelum());
                    //pemohon1 = strService.findById(permohonan.getPermohonanSebelum().getIdPermohonan());
                    pemohonList = pemohonService.findPemohonByIdPermohonan(permohonan.getPermohonanSebelum().getIdPermohonan());
                    if (!pemohonList.isEmpty()) {
                        pemohon1 = pemohonList.get(0);
                    }


                } else {
                    //pemohon1 = strService.findById(permohonan.getIdPermohonan());
                    pemohonList = pemohonService.findPemohonByIdPermohonan(permohonan.getIdPermohonan());
                    if (!pemohonList.isEmpty()) {
                        pemohon1 = pemohonList.get(0);
                    }

                }
                if ((pemohon1 != null) && (pemohon1.getPihak() != null)) {
                    LOG.info("Pihak : " + pemohon1.getPihak().getNama());
                    namaPemohon = pemohon1.getPihak().getNama();
                }

                if (permohonan != null) {
                    if (!(permohonan.getKodUrusan().getKod().equals("PHPP") || permohonan.getKodUrusan().getKod().equals("PHPC"))) {
                        if (!permohonan.getSenaraiPemohon().isEmpty() && permohonan.getSenaraiPemohon().get(0).getPihak() != null && permohonan.getSenaraiHakmilik() != null) {
//                            perihalPermohonan = "Permohonan telah diterima dari " + permohonan.getSenaraiPemohon().get(0).getPihak().getNama() + " selaku tuan Tanah seluas lebih kurang " + permohonan.getSenaraiHakmilik().get(0).getHakmilik().getLuas() + " " + permohonan.getSenaraiHakmilik().get(0).getHakmilik().getKodUnitLuas().getNama() 
//                                    + " untuk tujuan bangunan kediaman di bawah seksyen 10 AHS 1985 ";
                            perihalPermohonan = "";
                        }

                    } else {
                        String nama = "";
                        int count = 1;
                        for (HakmilikPihakBerkepentingan hp : listHakmilikPihak) {
                            nama = nama + hp.getNama();
                            if (count < listHakmilikPihak.size()) {
                                nama = nama + " , ";
                                count++;
                            }
                        }

                        if (permohonan.getSenaraiHakmilik().get(0).getHakmilik().getKodUnitLuas() == null) {
                            perihalPermohonan = " ";
//                            perihalPermohonan = "Permohonan telah diterima dari " + nama + " selaku tuan, untuk tujuan bangunan kediaman di bawah seksyen 10 AHS 1985 ";
                        } else {
                            perihalPermohonan = " ";
//                            perihalPermohonan = "Permohonan telah diterima dari " + nama + " selaku tuan Tanah seluas lebih kurang " + permohonan.getSenaraiHakmilik().get(0).getHakmilik().getLuas() + " " + permohonan.getSenaraiHakmilik().get(0).getHakmilik().getKodUnitLuas().getNama() 
//                                    + " untuk tujuan bangunan kediaman di bawah seksyen 10 AHS 1985 ";
                        }



                    }
                }
                if (permohonan.getSenaraiHakmilik().get(0).getHakmilik().getKodUnitLuas() != null) {

                    jenisluas = permohonan.getSenaraiHakmilik().get(0).getHakmilik().getKodUnitLuas().getNama();
                }
//                perihalPermohonan = listLaporanTanah1.get(0).getKand();
                LOG.info("PERIHAL :" + perihalPermohonan);
            }
            listLaporanTanah2 = strService.findByIdLaporKand(laporanTanah.getIdLaporan(), 2);
            if (!listLaporanTanah2.isEmpty()) {
                kedudukanTanah = listLaporanTanah2.get(0).getKand();
                LOG.info("KEDUDUKAN :" + kedudukanTanah);
            }
            if (permohonan.getSenaraiHakmilik().get(0).getHakmilik().getLokasi() != null) {
                String tempat;
                kedudukanTanah = "Terletak di " + permohonan.getSenaraiHakmilik().get(0).getHakmilik().getLokasi();
                LOG.info("KEDUDUKAN :" + kedudukanTanah);
            } else {
                String tempat;
                LOG.info("KEDUDUKAN :" + kedudukanTanah);
            }
            listLaporanTanah3 = strService.findByIdLaporKand(laporanTanah.getIdLaporan(), 3);
            if (!listLaporanTanah3.isEmpty()) {
                keadaanTanah = listLaporanTanah3.get(0).getKand();
                LOG.info("KEADAAN :" + keadaanTanah);
            }
            listLaporanTanah6 = strService.findByIdLaporKand(laporanTanah.getIdLaporan(), 6);
            if (!listLaporanTanah6.isEmpty()) {
                lainlainperkara = listLaporanTanah6.get(0).getKand();
                LOG.info("LAIN LAIN :" + lainlainperkara);
            }
            listLaporanTanah7 = strService.findByIdLaporKand(laporanTanah.getIdLaporan(), 7);
            if (!listLaporanTanah7.isEmpty()) {
                syorPerakuan = listLaporanTanah7.get(0).getKand();
                LOG.info("SYOR :" + syorPerakuan);
            }

        }

    }

    public Resolution saveLaporan() {
        pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        String taskId = (String) getContext().getRequest().getSession().getAttribute("taskId");

        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = permohonanDAO.findById(idPermohonan);
        laporanTanah = strService.findLaporanTanahByIdPermohonan(idPermohonan);
        if (laporanTanah != null) {
        } else {
            laporanTanah = new LaporanTanah();
            laporanTanah.setPermohonan(permohonan);
            laporanTanah.setInfoAudit(strService.getInfo(pengguna));
            strService.simpanLaporan(laporanTanah);
        }
        if (laporanTanah != null) {
            listLaporanTanah1 = strService.findByIdLaporKand(laporanTanah.getIdLaporan(), 1);
            if (StringUtils.isNotBlank(perihalPermohonan)) {
                if (!listLaporanTanah1.isEmpty()) {
                    listLaporanTanah1.get(0).setKand(perihalPermohonan);

                    strService.SimpanLaporanKand(listLaporanTanah1.get(0));
                } else {
                    savePermohonanLaporanKand(perihalPermohonan, 1, laporanTanah);

                }
            }
            listLaporanTanah2 = strService.findByIdLaporKand(laporanTanah.getIdLaporan(), 2);
            if (StringUtils.isNotBlank(kedudukanTanah)) {
                if (!listLaporanTanah2.isEmpty()) {
                    listLaporanTanah2.get(0).setKand(kedudukanTanah);
                    strService.SimpanLaporanKand(listLaporanTanah2.get(0));
                } else {
                    savePermohonanLaporanKand(kedudukanTanah, 2, laporanTanah);

                }
            }
            listLaporanTanah3 = strService.findByIdLaporKand(laporanTanah.getIdLaporan(), 3);
            if (StringUtils.isNotBlank(keadaanTanah)) {
                if (!listLaporanTanah3.isEmpty()) {
                    listLaporanTanah3.get(0).setKand(keadaanTanah);
                    strService.SimpanLaporanKand(listLaporanTanah3.get(0));
                } else {
                    savePermohonanLaporanKand(keadaanTanah, 3, laporanTanah);

                }
            }
            listLaporanTanah6 = strService.findByIdLaporKand(laporanTanah.getIdLaporan(), 6);
            if (StringUtils.isNotBlank(lainlainperkara)) {
                if (!listLaporanTanah6.isEmpty()) {
                    listLaporanTanah6.get(0).setKand(lainlainperkara);
                    strService.SimpanLaporanKand(listLaporanTanah6.get(0));
                } else {
                    savePermohonanLaporanKand(lainlainperkara, 6, laporanTanah);

                }
            }
            listLaporanTanah7 = strService.findByIdLaporKand(laporanTanah.getIdLaporan(), 7);
            if (StringUtils.isNotBlank(syorPerakuan)) {
                if (!listLaporanTanah7.isEmpty()) {
                    listLaporanTanah7.get(0).setKand(syorPerakuan);
                    strService.SimpanLaporanKand(listLaporanTanah7.get(0));
                } else {
                    savePermohonanLaporanKand(syorPerakuan, 7, laporanTanah);

                }
            }
        }
        if (tarikhSiasatan != null) {
            laporanTanah.setTarikhSiasat(tarikhSiasatan);
            laporanTanah = strService.simpanLaporan(laporanTanah);
        }
        comm.setPengguna(pengguna);
        if (!comm.stageId(taskId).equals("sedialaporan")) {
            readOnly = true;
        }
        rehydrate();
        addSimpleMessage("Maklumat berjaya disimpan");
        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        return new JSP("strata/pbbm/laporanTanah_melaka.jsp").addParameter("tab", "true");
    }

    private void savePermohonanLaporanKand(String kand, int i, LaporanTanah lt) {
        PermohonanLaporanKandungan plk = new PermohonanLaporanKandungan();
        plk.setBil(Short.parseShort(String.valueOf(i)));
        plk.setKand(kand);
        plk.setLaporanTanah(lt);
        plk.setInfoAudit(strService.getInfo(pengguna));
        strService.SimpanLaporanKand(plk);
    }

    public Resolution lotSempadan() {
        return new JSP("strata/pbbm/laporanTanah_lotSempadan.jsp").addParameter("tab", "true");
    }

    public Resolution gambarLaporan() {
        return new JSP("strata/pbbm/laporanTanah_gambarLaporan.jsp").addParameter("tab", "true");
    }

    public Resolution resetForm() {
        LOG.info("--clearing Form--:");
        tarikhSiasatan = null;
        keadaanTanah = " ";
        lainlainperkara = " ";
        syorPerakuan = " ";

        if (permohonan.getSenaraiHakmilik().get(0).getHakmilik().getLokasi() == null) {
            kedudukanTanah = " ";
        } else if (permohonan.getSenaraiHakmilik().get(0).getHakmilik().getLokasi() != null) {
            kedudukanTanah = "Terletak di " + permohonan.getSenaraiHakmilik().get(0).getHakmilik().getLokasi();
        }
        LOG.info("--kedudukanTanah--:" + kedudukanTanah);


        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        String taskId = (String) getContext().getRequest().getSession().getAttribute("taskId");
        comm.setPengguna(pengguna);
        if (!comm.stageId(taskId).equals("sedialaporan")) {
            readOnly = true;
        }

        laporanTanah = strService.findLaporanTanahByIdPermohonan(idPermohonan);
        LOG.info("--laporanTanah--:" + laporanTanah);
        laporanTanah.setTarikhSiasat(tarikhSiasatan);
        laporanTanah.setKedudukanTanah(kedudukanTanah);
        strService.simpanLaporan(laporanTanah);

        listLaporanTanah2 = strService.findByIdLaporKand(laporanTanah.getIdLaporan(), 2);
        LOG.info("--listLaporanTanah2--:" + listLaporanTanah2);
        if (!listLaporanTanah2.isEmpty()) {
            listLaporanTanah2.get(0).setKand(kedudukanTanah);
            strService.SimpanLaporanKand(listLaporanTanah2.get(0));
        }

        listLaporanTanah3 = strService.findByIdLaporKand(laporanTanah.getIdLaporan(), 3);
        LOG.info("--listLaporanTanah3--:" + listLaporanTanah3);
        if (!listLaporanTanah3.isEmpty()) {
            listLaporanTanah3.get(0).setKand(keadaanTanah);
            strService.SimpanLaporanKand(listLaporanTanah3.get(0));
        }

        listLaporanTanah6 = strService.findByIdLaporKand(laporanTanah.getIdLaporan(), 6);
        LOG.info("--listLaporanTanah6--:" + listLaporanTanah6);
        if (!listLaporanTanah6.isEmpty()) {
            listLaporanTanah6.get(0).setKand(lainlainperkara);
            strService.SimpanLaporanKand(listLaporanTanah6.get(0));
        }

        listLaporanTanah7 = strService.findByIdLaporKand(laporanTanah.getIdLaporan(), 7);
        LOG.info("--listLaporanTanah7--:" + listLaporanTanah7);
        if (!listLaporanTanah7.isEmpty()) {
            listLaporanTanah7.get(0).setKand(syorPerakuan);
            strService.SimpanLaporanKand(listLaporanTanah7.get(0));
        }

        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        return new JSP("strata/pbbm/laporanTanah_melaka.jsp").addParameter("tab", "true");
    }

    public String getKeadaanTanah() {
        return keadaanTanah;
    }

    public void setKeadaanTanah(String keadaanTanah) {
        this.keadaanTanah = keadaanTanah;
    }

    public String getKedudukanTanah() {
        return kedudukanTanah;
    }

    public void setKedudukanTanah(String kedudukanTanah) {
        this.kedudukanTanah = kedudukanTanah;
    }

    public String getLainlainperkara() {
        return lainlainperkara;
    }

    public void setLainlainperkara(String lainlainperkara) {
        this.lainlainperkara = lainlainperkara;
    }

    public String getPerihalPermohonan() {
        return perihalPermohonan;
    }

    public void setPerihalPermohonan(String perihalPermohonan) {
        this.perihalPermohonan = perihalPermohonan;
    }

    public String getSyorPerakuan() {
        return syorPerakuan;
    }

    public void setSyorPerakuan(String syorPerakuan) {
        this.syorPerakuan = syorPerakuan;
    }

    public LaporanTanah getLaporanTanah() {
        return laporanTanah;
    }

    public void setLaporanTanah(LaporanTanah laporanTanah) {
        this.laporanTanah = laporanTanah;
    }

    public List<PermohonanLaporanKandungan> getListLaporanTanah1() {
        return listLaporanTanah1;
    }

    public void setListLaporanTanah1(List<PermohonanLaporanKandungan> listLaporanTanah1) {
        this.listLaporanTanah1 = listLaporanTanah1;
    }

    public List<PermohonanLaporanKandungan> getListLaporanTanah2() {
        return listLaporanTanah2;
    }

    public void setListLaporanTanah2(List<PermohonanLaporanKandungan> listLaporanTanah2) {
        this.listLaporanTanah2 = listLaporanTanah2;
    }

    public List<PermohonanLaporanKandungan> getListLaporanTanah3() {
        return listLaporanTanah3;
    }

    public void setListLaporanTanah3(List<PermohonanLaporanKandungan> listLaporanTanah3) {
        this.listLaporanTanah3 = listLaporanTanah3;
    }

    public List<PermohonanLaporanKandungan> getListLaporanTanah6() {
        return listLaporanTanah6;
    }

    public void setListLaporanTanah6(List<PermohonanLaporanKandungan> listLaporanTanah6) {
        this.listLaporanTanah6 = listLaporanTanah6;
    }

    public List<PermohonanLaporanKandungan> getListLaporanTanah7() {
        return listLaporanTanah7;
    }

    public void setListLaporanTanah7(List<PermohonanLaporanKandungan> listLaporanTanah7) {
        this.listLaporanTanah7 = listLaporanTanah7;
    }

    public boolean isReadOnly() {
        return readOnly;
    }

    public void setReadOnly(boolean readOnly) {
        this.readOnly = readOnly;
    }

    public boolean isStagePPTG() {
        return stagePPTG;
    }

    public void setStagePPTG(boolean stagePPTG) {
        this.stagePPTG = stagePPTG;
    }

    public Date getTarikhSiasatan() {
        return tarikhSiasatan;
    }

    public void setTarikhSiasatan(Date tarikhSiasatan) {
        this.tarikhSiasatan = tarikhSiasatan;
    }

    public List<HakmilikPihakBerkepentingan> getListHakmilikPihak() {
        return listHakmilikPihak;
    }

    public void setListHakmilikPihak(List<HakmilikPihakBerkepentingan> listHakmilikPihak) {
        this.listHakmilikPihak = listHakmilikPihak;
    }

    public Permohonan getPermohonan() {
        return permohonan;
    }

    public void setPermohonan(Permohonan permohonan) {
        this.permohonan = permohonan;
    }

    public String getJenisluas() {
        return jenisluas;
    }

    public void setJenisluas(String jenisluas) {
        this.jenisluas = jenisluas;
    }
}
