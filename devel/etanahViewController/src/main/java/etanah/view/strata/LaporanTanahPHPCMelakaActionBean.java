/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.strata;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.dao.DokumenDAO;
import etanah.dao.HakmilikPermohonanDAO;
import etanah.dao.PermohonanDAO;
import etanah.model.Dokumen;
import etanah.model.FolderDokumen;
import etanah.model.HakmilikPermohonan;
import etanah.model.ImejLaporan;
import etanah.model.InfoAudit;
import etanah.model.LaporanTanah;
import etanah.model.Pemohon;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.model.PermohonanLaporanKandungan;
import etanah.model.HakmilikPihakBerkepentingan;
import etanah.service.common.HakmilikPihakKepentinganService;
import etanah.service.BPelService;
import etanah.service.StrataPtService;
import etanah.service.common.PemohonService;
import etanah.view.etanahActionBeanContext;
import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.FileBean;
import net.sourceforge.stripes.action.RedirectResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;
import oracle.bpel.services.workflow.task.model.Task;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

/**
 *
 * @author murali
 */
@UrlBinding("/strata/laporanTanah_PHPC")
public class LaporanTanahPHPCMelakaActionBean extends AbleActionBean {

    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    StrataPtService strService;
    @Inject
    CommonService comm;
    @Inject
    HakmilikPermohonanDAO hakmilikPermohonanDAO;
    @Inject
    HakmilikPihakKepentinganService hakmilikPihakKepentinganService;
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
    private boolean readOnly = false;
    private boolean stagePPTG = true;
    private Date tarikhSiasatan;
    private static final Logger LOG = Logger.getLogger(LaporanTanahPHPCMelakaActionBean.class);
    private String gunaBarat;
    private String gunaTimur;
    private String gunaSelatan;
    private String gunaUtara;
    private String lotBarat;
    private String lotTimur;
    private String lotSelatan;
    private String lotUtara;
    private Long idMh;
    @Inject
    etanah.Configuration conf;
    @Inject
    DokumenDAO dokumenDAO;
    @Inject
    PemohonService pemohonService;
    private ImejLaporan imejLaporan = new ImejLaporan();
    private FileBean fileToBeUpload;
    private Dokumen dokumen;
    String catatan = "";
    private String imageFolderPath;
    private List<ImejLaporan> senaraiImejLaporan;
    private List<HakmilikPermohonan> senaraiHakmilikPermohonan;
    private HakmilikPermohonan hakmilikPermohonan;

    @DefaultHandler
    public Resolution showForm() {
        pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);

        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        return new JSP("strata/pecahPetak/laporan_tanah_PHPC.jsp").addParameter("tab", "true");
    }

    public Resolution readOnly() {
        getContext().getRequest().setAttribute("edit", Boolean.FALSE);
        return new JSP("strata/pecahPetak/laporan_tanah_PHPC.jsp").addParameter("tab", "true");
    }

    public Resolution rayuanLaporan() {
        return new JSP("strata/pecahPetak/laporan_tanah_PHPC.jsp").addParameter("tab", "true");
    }

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {
        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = permohonanDAO.findById(idPermohonan);
        String stageId = "";
        Long idMh1 = 0L;
        BPelService service = new BPelService();
        pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        String taskId = (String) getContext().getRequest().getSession().getAttribute("taskId");

        if (permohonan.getKodUrusan().getKod().equals("PHPP") || permohonan.getKodUrusan().getKod().equals("PHPC")) {
            for (HakmilikPermohonan hkm : permohonan.getSenaraiHakmilik()) {
                if (hkm.getHakmilik().getKodStatusHakmilik().getKod().equals("D")) {
                    listHakmilikPihak = hakmilikPihakKepentinganService.findHakmilikPihakByKod(hkm.getHakmilik(), "PM");
                    LOG.info("PEMILIK =========>" + listHakmilikPihak.size());
                    LOG.info("PEMILIK =========>" + listHakmilikPihak.toString());
                }
            }
        }

        if (StringUtils.isNotBlank(taskId)) {
            Task t = null;
            t = service.getTaskFromBPel(taskId, pengguna);
            stageId = t.getSystemAttributes().getStage();
        }

        String strIdMh = getContext().getRequest().getParameter("idMh");
        if (strIdMh == null) {
            idMh1 = permohonan.getSenaraiHakmilik().get(0).getId();
        } else {
            idMh1 = Long.parseLong(getContext().getRequest().getParameter("idMh"));
        }
        LOG.info("---------------idMh1:--------" + idMh1);
        //laporanTanah = strService.findLaporanTanahByIdPermohonan(idPermohonan);
        laporanTanah = strService.findLaporanTanahByIdMH(idPermohonan, idMh1);
        if (laporanTanah != null) {
            LOG.info("--- Laporan Tanah tak null ---");
        } else {
            LOG.info("--- Laporan Tanah null, create new ---");
            permohonan = permohonanDAO.findById(idPermohonan);
            laporanTanah = new LaporanTanah();
            laporanTanah.setPermohonan(permohonan);
            HakmilikPermohonan mohonHakmilik = new HakmilikPermohonan();
            mohonHakmilik = hakmilikPermohonanDAO.findById(idMh1);
            laporanTanah.setHakmilikPermohonan(mohonHakmilik);
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
                String namaPemohon = null;
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

//                    if (!permohonan.getSenaraiPemohon().isEmpty() && permohonan.getSenaraiPemohon().get(0).getPihak() != null && permohonan.getSenaraiHakmilik() != null) {
//                        perihalPermohonan = "Permohonan telah diterima dari " + permohonan.getSenaraiPemohon().get(0).getPihak().getNama() + " selaku tuan Tanah seluas lebih kurang " + permohonan.getSenaraiHakmilik().get(0).getHakmilik().getLuas() + " " + permohonan.getSenaraiHakmilik().get(0).getHakmilik().getKodUnitLuas().getNama() + " untuk tujuan bangunan kediaman di bawah seksyen 10 AHS 1985(AKTA 318) "
//                                + "dan Kaedah-Kaedah Hakmilik Strata Melaka 1987";
//                    }

                    if (!(permohonan.getKodUrusan().getKod().equals("PHPP") || permohonan.getKodUrusan().getKod().equals("PHPC"))) {
                        if (!permohonan.getSenaraiPemohon().isEmpty() && permohonan.getSenaraiPemohon().get(0).getPihak() != null && permohonan.getSenaraiHakmilik() != null) {
                            perihalPermohonan = "Permohonan telah diterima dari " + permohonan.getSenaraiPemohon().get(0).getPihak().getNama() + " selaku tuan Tanah seluas lebih kurang " + permohonan.getSenaraiHakmilik().get(0).getHakmilik().getLuas() + " " + permohonan.getSenaraiHakmilik().get(0).getHakmilik().getKodUnitLuas().getNama() + " untuk tujuan bangunan kediaman di bawah seksyen 10 AHS 1985 (AKTA 318) "
                                    + "dan Kaedah-Kaedah Hakmilik Strata Melaka 1987";
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
                            perihalPermohonan = "Permohonan telah diterima dari " + nama + " selaku tuan Tanah, untuk tujuan bangunan kediaman di bawah seksyen 10 AHS 1985 (AKTA 318) "
                                    + "dan Kaedah-Kaedah Hakmilik Strata Melaka 1987";
                        } else {
                            perihalPermohonan = "Permohonan telah diterima dari " + nama + " selaku tuan Tanah seluas lebih kurang " + permohonan.getSenaraiHakmilik().get(0).getHakmilik().getLuas() + " " + permohonan.getSenaraiHakmilik().get(0).getHakmilik().getKodUnitLuas().getNama() + " untuk tujuan bangunan kediaman di bawah seksyen 10 AHS 1985 (AKTA 318) "
                                    + "dan Kaedah-Kaedah Hakmilik Strata Melaka 1987";
                        }
                    }
                }


                LOG.info("PERIHAL :" + perihalPermohonan);
            }
            listLaporanTanah2 = strService.findByIdLaporKand(laporanTanah.getIdLaporan(), 2);
            if (!listLaporanTanah2.isEmpty()) {
                kedudukanTanah = listLaporanTanah2.get(0).getKand();
                LOG.info("KEDUDUKAN :" + kedudukanTanah);
            } else {
                String tempat;
                if (permohonan.getSenaraiHakmilik().get(0).getHakmilik().getLokasi() == null) {
                    kedudukanTanah = null;
                } else {
                    kedudukanTanah = "Terletak di " + permohonan.getSenaraiHakmilik().get(0).getHakmilik().getLokasi();
                }
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

            //sempadan
            LOG.info("--Sempadan--rehydrate--:");
            gunaBarat = laporanTanah.getSempadanBaratKegunaan();
            gunaTimur = laporanTanah.getSempadanTimurKegunaan();
            gunaSelatan = laporanTanah.getSempadanSelatanKegunaan();
            gunaUtara = laporanTanah.getSempadanUtaraKegunaan();
            lotBarat = laporanTanah.getSempadanBaratNoLot();
            lotTimur = laporanTanah.getSempadanTimurNoLot();
            lotSelatan = laporanTanah.getSempadanSelatanNoLot();
            lotUtara = laporanTanah.getSempadanUtaraNoLot();
            idMh = laporanTanah.getHakmilikPermohonan().getId();


            // Getting Image Path
            String dokumenPath = conf.getProperty("document.path");
            imageFolderPath = dokumenPath + (dokumenPath.endsWith(File.separator) ? "" : File.separator) + permohonan.getFolderDokumen().getFolderId() + File.separator;
            LOG.info("----------imageFolderPath--------------: " + imageFolderPath);
            FolderDokumen fd = permohonan.getFolderDokumen();
            String docPath = conf.getProperty("document.path");
            senaraiImejLaporan = new ArrayList<ImejLaporan>();
            senaraiImejLaporan = strService.findImejlaporan(laporanTanah.getIdLaporan());
        }

        //to get mohonhakmilik list
        senaraiHakmilikPermohonan = permohonan.getSenaraiHakmilik();
        LOG.info("----------HakmilikPermohonan List size--------------: " + senaraiHakmilikPermohonan.size());

    }

    public Resolution saveLaporan() {
        LOG.info("----------Saving:--------");
        pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        String taskId = (String) getContext().getRequest().getSession().getAttribute("taskId");

        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = permohonanDAO.findById(idPermohonan);
        Long idMh1 = Long.parseLong(getContext().getRequest().getParameter("idMh"));
        LOG.info("---------------idMh1:--------" + idMh1);
        //laporanTanah = strService.findLaporanTanahByIdPermohonan(idPermohonan);
        laporanTanah = strService.findLaporanTanahByIdMH(idPermohonan, idMh1);
        if (laporanTanah != null) {
        } else {
            laporanTanah = new LaporanTanah();
            laporanTanah.setPermohonan(permohonan);
            HakmilikPermohonan mohonHakmilik = new HakmilikPermohonan();
            mohonHakmilik = hakmilikPermohonanDAO.findById(idMh1);
            laporanTanah.setHakmilikPermohonan(mohonHakmilik);
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

            //sempadan saving
            LOG.info("--Sempadan--saving--:");
            laporanTanah.setSempadanBaratNoLot(lotBarat);
            laporanTanah.setSempadanUtaraNoLot(lotUtara);
            laporanTanah.setSempadanTimurNoLot(lotTimur);
            laporanTanah.setSempadanSelatanNoLot(lotSelatan);
            laporanTanah.setSempadanBaratKegunaan(gunaBarat);
            laporanTanah.setSempadanUtaraKegunaan(gunaUtara);
            laporanTanah.setSempadanTimurKegunaan(gunaTimur);
            laporanTanah.setSempadanSelatanKegunaan(gunaSelatan);
            laporanTanah = strService.simpanLaporan(laporanTanah);
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
        return new JSP("strata/pecahPetak/laporan_tanah_PHPC.jsp").addParameter("tab", "true");
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

        LOG.info("--clearing Laporan Tanah--:");
        tarikhSiasatan = null;
        keadaanTanah = " ";
        lainlainperkara = " ";
        syorPerakuan = " ";

        LOG.info("--clearing Laporan sempadan--:");
        lotUtara = "";
        gunaUtara = "";
        lotSelatan = "";
        gunaSelatan = "";
        lotTimur = "";
        gunaTimur = "";
        lotBarat = "";
        gunaBarat = "";

        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        String taskId = (String) getContext().getRequest().getSession().getAttribute("taskId");
        comm.setPengguna(pengguna);
        if (!comm.stageId(taskId).equals("sedialaporan")) {
            readOnly = true;
        }

        Long idMh1 = Long.parseLong(getContext().getRequest().getParameter("idMh"));
        LOG.info("---------------idMh1:--------" + idMh1);
        //laporanTanah = strService.findLaporanTanahByIdPermohonan(idPermohonan);
        laporanTanah = strService.findLaporanTanahByIdMH(idPermohonan, idMh1);
        LOG.info("--laporanTanah--:" + laporanTanah);
        laporanTanah.setTarikhSiasat(tarikhSiasatan);
        LOG.info("--laporan sempadan and tarik siasatan--:");
        laporanTanah.setSempadanBaratNoLot(lotBarat);
        laporanTanah.setSempadanUtaraNoLot(lotUtara);
        laporanTanah.setSempadanTimurNoLot(lotTimur);
        laporanTanah.setSempadanSelatanNoLot(lotSelatan);
        laporanTanah.setSempadanBaratKegunaan(gunaBarat);
        laporanTanah.setSempadanUtaraKegunaan(gunaUtara);
        laporanTanah.setSempadanTimurKegunaan(gunaTimur);
        laporanTanah.setSempadanSelatanKegunaan(gunaSelatan);
        strService.simpanLaporan(laporanTanah);

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
        return new JSP("strata/pecahPetak/laporan_tanah_PHPC.jsp").addParameter("tab", "true");
    }

    public Resolution uploadDoc() {
        return new JSP("strata/pecahPetak/muatnaik.jsp").addParameter("popup", "true");
    }

    public Resolution muatNaik() throws Exception {
        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        String lokasi = getContext().getRequest().getParameter("lokasi");
        Pengguna pguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);

        String error = "";
        String msg = "";

        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        InfoAudit ia = new InfoAudit();
        ia.setTarikhMasuk(new Date());
        ia.setDimasukOleh(pguna);
        Long idMh1 = Long.parseLong(getContext().getRequest().getParameter("idMh"));
        LOG.info("---------------idMh1:--------" + idMh1);
        //laporanTanah = strService.getLaporanTanahByIdPermohonan(idPermohonan);
        laporanTanah = strService.findLaporanTanahByIdMH(idPermohonan, idMh1);

        String dokumenPath = conf.getProperty("document.path");
        permohonan = permohonanDAO.findById(idPermohonan);
        LOG.info("INI ADALAH FOLDER DOKUMEN" + permohonan.getFolderDokumen().getFolderId());

        dokumenPath = dokumenPath + (dokumenPath.endsWith(File.separator) ? "" : File.separator) + permohonan.getFolderDokumen().getFolderId();
        if (fileToBeUpload == null) {
            addSimpleError("Sila pilih fail imej untuk dimuatnaik.");
            return new JSP("strata/pecahPetak/muatnaik.jsp").addParameter("popup", "true");
        } else if (!(fileToBeUpload.getFileName().endsWith(".jpg") || fileToBeUpload.getFileName().endsWith(".JPG") || fileToBeUpload.getFileName().endsWith(".bmp") || fileToBeUpload.getFileName().endsWith(".png") || fileToBeUpload.getFileName().endsWith(".gif"))) {
            LOG.info("----------------fileToBeUpload---else-----------:" + fileToBeUpload.getFileName());
            addSimpleError("Sila pilih fail imej dalam format *.jpg, *.bmp, *.png, *.gif untuk dimuatnaik.");
            return new JSP("strata/Ruang_Udara/muatnaik.jsp").addParameter("popup", "true");
        }
        LOG.info("----------------fileToBeUpload--------------:" + fileToBeUpload.getFileName()); //End

        Dokumen doc = comm.saveDokumen(dokumenPath, fileToBeUpload, permohonan.getFolderDokumen().getFolderId(), ia, permohonan);

        imejLaporan.setHakmilik(permohonan.getSenaraiHakmilik().get(0).getHakmilik());
        imejLaporan.setCawangan(peng.getKodCawangan());
        imejLaporan.setDokumen(doc);
        imejLaporan.setLaporanTanah(laporanTanah);
        imejLaporan.setCatatan(catatan);
        LOG.info("LOKASI" + lokasi);
        char c = 0;
        if (StringUtils.isNotEmpty(lokasi)) {
            c = (lokasi).charAt(0);
            imejLaporan.setPandanganImej(c);
        }

        imejLaporan.setInfoAudit(ia);
        comm.saveImej(imejLaporan);
        LOG.info("simpanMuatNaik::finish");
        addSimpleMessage("Muat naik fail berjaya.");
        rehydrate();
        return new JSP("strata/pecahPetak/muatnaik.jsp").addParameter("popup", "true");

    }

    public Resolution showRefresh() {
        LOG.info("------showRefresh----");
        Long idMh1 = Long.parseLong(getContext().getRequest().getParameter("idMh"));
        LOG.info("---------------idMh1:--------" + idMh1);
        hakdetails();
        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        return new JSP("strata/pecahPetak/laporan_tanah_PHPC.jsp").addParameter("popup", "true");
    }

    public Resolution hapusImej() {

        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = permohonanDAO.findById(idPermohonan);
        Long folderId = 0L;
        if (permohonan.getFolderDokumen() != null) {
            folderId = permohonan.getFolderDokumen().getFolderId();
        }

        LOG.info("---------------idPermohonan:--------" + idPermohonan);
        Long dokumenId = Long.parseLong(getContext().getRequest().getParameter("idDokumen"));
        LOG.info("---------------idDokumen:--------" + dokumenId);
        dokumen = dokumenDAO.findById(dokumenId);
        String docPath = conf.getProperty("document.path");
        File file = new File(docPath + dokumen.getNamaFizikal());
        file.delete();
        strService.deleteImejLaporanByIdDokumen(dokumenId);
        strService.deleteKandunganFolderByIdDokumen(folderId, dokumenId);
        strService.deleteDokumenCapaianByIdDokumen(dokumenId);
        strService.deleteAll2(dokumen);
        LOG.info("---------------Deleted idDokumen is::--------" + dokumenId);
        String msg = "Imej telah berjaya dipadamkan.";
        addSimpleMessage(msg);
        return new RedirectResolution(LaporanTanahPHPCMelakaActionBean.class, "showForm");
    }

    public Resolution hakdetails() {
        Long idMh1 = Long.parseLong(getContext().getRequest().getParameter("idMh"));
        LOG.info("---------------idMh1:--------" + idMh1);
        rehydrate();
        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        return new JSP("strata/pecahPetak/laporan_tanah_PHPC.jsp").addParameter("tab", "true");
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

    public String getGunaBarat() {
        return gunaBarat;
    }

    public void setGunaBarat(String gunaBarat) {
        this.gunaBarat = gunaBarat;
    }

    public String getGunaSelatan() {
        return gunaSelatan;
    }

    public void setGunaSelatan(String gunaSelatan) {
        this.gunaSelatan = gunaSelatan;
    }

    public String getGunaTimur() {
        return gunaTimur;
    }

    public void setGunaTimur(String gunaTimur) {
        this.gunaTimur = gunaTimur;
    }

    public String getGunaUtara() {
        return gunaUtara;
    }

    public void setGunaUtara(String gunaUtara) {
        this.gunaUtara = gunaUtara;
    }

    public String getLotBarat() {
        return lotBarat;
    }

    public void setLotBarat(String lotBarat) {
        this.lotBarat = lotBarat;
    }

    public String getLotSelatan() {
        return lotSelatan;
    }

    public void setLotSelatan(String lotSelatan) {
        this.lotSelatan = lotSelatan;
    }

    public String getLotTimur() {
        return lotTimur;
    }

    public void setLotTimur(String lotTimur) {
        this.lotTimur = lotTimur;
    }

    public String getLotUtara() {
        return lotUtara;
    }

    public void setLotUtara(String lotUtara) {
        this.lotUtara = lotUtara;
    }

    public ImejLaporan getImejLaporan() {
        return imejLaporan;
    }

    public void setImejLaporan(ImejLaporan imejLaporan) {
        this.imejLaporan = imejLaporan;
    }

    public FileBean getFileToBeUpload() {
        return fileToBeUpload;
    }

    public void setFileToBeUpload(FileBean fileToBeUpload) {
        this.fileToBeUpload = fileToBeUpload;
    }

    public Dokumen getDokumen() {
        return dokumen;
    }

    public void setDokumen(Dokumen dokumen) {
        this.dokumen = dokumen;
    }

    public String getCatatan() {
        return catatan;
    }

    public void setCatatan(String catatan) {
        this.catatan = catatan;
    }

    public String getImageFolderPath() {
        return imageFolderPath;
    }

    public void setImageFolderPath(String imageFolderPath) {
        this.imageFolderPath = imageFolderPath;
    }

    public List<ImejLaporan> getSenaraiImejLaporan() {
        return senaraiImejLaporan;
    }

    public void setSenaraiImejLaporan(List<ImejLaporan> senaraiImejLaporan) {
        this.senaraiImejLaporan = senaraiImejLaporan;
    }

    public List<HakmilikPermohonan> getSenaraiHakmilikPermohonan() {
        return senaraiHakmilikPermohonan;
    }

    public void setSenaraiHakmilikPermohonan(List<HakmilikPermohonan> senaraiHakmilikPermohonan) {
        this.senaraiHakmilikPermohonan = senaraiHakmilikPermohonan;
    }

    public Long getIdMh() {
        return idMh;
    }

    public void setIdMh(Long idMh) {
        this.idMh = idMh;
    }
}
