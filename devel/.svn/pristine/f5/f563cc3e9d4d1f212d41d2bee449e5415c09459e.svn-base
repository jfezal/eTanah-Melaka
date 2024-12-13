package etanah.view.penguatkuasaan;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.dao.KodDokumenDAO;
import etanah.dao.PermohonanDAO;
import etanah.dao.PermohonanKertasKandunganDAO;
import etanah.model.InfoAudit;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.model.PermohonanKertas;
import etanah.model.PermohonanKertasKandungan;
import etanah.service.BPelService;
import etanah.service.EnforceService;
import etanah.view.etanahActionBeanContext;
import java.util.ArrayList;
import java.util.List;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.RedirectResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;
import org.apache.log4j.Logger;
import org.apache.commons.lang.StringUtils;
import oracle.bpel.services.workflow.task.model.Task;
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author latifah.iskak
 */
@UrlBinding("/penguatkuasaan/maklumat_kertas_mmkn")
public class MaklumatKertasMMKNActionBean extends AbleActionBean {

    private static final Logger LOG = Logger.getLogger(MaklumatKertasMMKNActionBean.class);
    @Inject
    EnforceService enforceService;
    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    KodDokumenDAO kodDokumenDAO;
    @Inject
    PermohonanKertasKandunganDAO permohonanKertasKandunganDAO;
    @Inject
    private etanah.Configuration conf;
    private Pengguna pengguna;
    private String idPermohonan;
    private Permohonan permohonan;
    private PermohonanKertas permohonanKertas;
    private PermohonanKertasKandungan permohonanKertasKandungan;
    private String tajuk;
    private String tujuan;
    private List<PermohonanKertasKandungan> senaraiKertasKandungan1;
    private List<PermohonanKertasKandungan> senaraiKertasKandungan2;
    private List<PermohonanKertasKandungan> senaraiKertasKandungan3;
    private List<PermohonanKertasKandungan> senaraiKertasKandungan4;
    private List<PermohonanKertasKandungan> senaraiKertasKandungan5;
    private String rowCount1;
    private String rowCount2;
    private String rowCount3;
    private String rowCount4;
    private String rowCount5;
    private String rowCountTable;
    private String currentNum;
    private List<PermohonanKertasKandungan> senaraiKertasKandungan;
    private String latarBelakang;
    private String ulasan;
    private String keputusan;
    private String idKandungan;
    private String ulasanPtg;
    private String stageId;
    private String taskId;
    private String kodNegeri;
    private String kodRujukan;
    private Boolean statusPMMK = Boolean.FALSE;
    private String kodDokumenMMKN;
    private Boolean statusEditInfo = Boolean.FALSE;
    private Boolean ulasanPtgView = Boolean.FALSE;
    private Boolean statusEditPerakuanPT = Boolean.FALSE;
    private Boolean userPtgEditPerakuanPTD = Boolean.FALSE;

    @DefaultHandler
    public Resolution showForm() {

        if (("04".equals(conf.getProperty("kodNegeri")))) {
            if (permohonan.getKodUrusan().getKod().equalsIgnoreCase("425") || permohonan.getKodUrusan().getKod().equalsIgnoreCase("425A")) {
                if (stageId.equalsIgnoreCase("terima_semak_risalat_mmkn") || stageId.equalsIgnoreCase("sah_kertas_mmkn")
                        || stageId.equalsIgnoreCase("semak_draf_mmkn3_1") || stageId.equalsIgnoreCase("semak_draf_mmkn3_2")) {
                    LOG.info("-------------- : ulasan MMKN PTG Melaka--------------");
                    getContext().getRequest().setAttribute("ulasanPTG", Boolean.TRUE);
                } else {
                    LOG.info("------------else: edit mmkn--------------");
                    getContext().getRequest().setAttribute("edit", Boolean.TRUE);

                }
            } else {
                LOG.info("------------showForm : edit mmkn--------------");
                getContext().getRequest().setAttribute("edit", Boolean.TRUE);
            }
        } else {
            LOG.info("------------showForm : edit mmkn--------------");
            getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        }
//        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        return new JSP("penguatkuasaan/maklumat_kertas_MMKN.jsp").addParameter("tab", "true");
    }

    public Resolution showForm2() {
        getContext().getRequest().setAttribute("view", Boolean.TRUE);
        return new JSP("penguatkuasaan/maklumat_kertas_MMKN.jsp").addParameter("tab", "true");
    }

    public Resolution showForm3() {
        getContext().getRequest().setAttribute("ulasanPTD", Boolean.TRUE);
        return new JSP("penguatkuasaan/maklumat_kertas_MMKN.jsp").addParameter("tab", "true");
    }

    public Resolution showForm4() {
        getContext().getRequest().setAttribute("ulasanPTG", Boolean.TRUE);
        return new JSP("penguatkuasaan/maklumat_kertas_MMKN.jsp").addParameter("tab", "true");
    }

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {

        LOG.info("--------------rehydrate--------------");
        taskId = (String) getContext().getRequest().getSession().getAttribute("taskId");
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        kodNegeri = conf.getProperty("kodNegeri");

        BPelService service = new BPelService();

        if (StringUtils.isNotBlank(taskId)) {
            Task t = null;
            t = service.getTaskFromBPel(taskId, pengguna);
            stageId = t.getSystemAttributes().getStage();
            System.out.println("-------------stageId: BPEL ON ----" + stageId);
        } else {
            stageId = "semak_draf_mmkn3_1";
            System.out.println("-------------stageId: BPEL OFF ----" + stageId);
        }

        ArrayList stageTerlibat = new ArrayList<String>();
        ArrayList stageTerlibatEdit = new ArrayList<String>();
        ArrayList stageTerlibatViewPtg = new ArrayList<String>();
        ArrayList stageTerlibatEditPerakuanPT = new ArrayList<String>();
        if (stageId.equalsIgnoreCase("terima_semak_risalat_mmkn") || stageId.equalsIgnoreCase("semak_draf_mmkn3_1") || stageId.equalsIgnoreCase("semak_draf_mmkn3_2")) {
            if (pengguna.getPerananUtama() != null) {
                LOG.info("::: kumpulan bpel :" + pengguna.getPerananUtama().getKumpBPEL());
                if (pengguna.getPerananUtama().getKumpBPEL().contains("mmkn")) {
                    userPtgEditPerakuanPTD = true;
                }

            }
        }



        if (idPermohonan != null) {
            permohonan = permohonanDAO.findById(idPermohonan);

            if (kodNegeri.equalsIgnoreCase("04")) {
                if (permohonan.getKodUrusan().getKod().equalsIgnoreCase("127")) {
                    stageTerlibat.add("sedia_mmkn2");
                    stageTerlibat.add("semak1_mmkn2");
                    stageTerlibat.add("semak2_mmkn2");
                    stageTerlibat.add("semak3_mmkn2");
                    stageTerlibat.add("sahkan_mmkn2");
                    stageTerlibat.add("maklum_kpsn_mmkn1");
                    stageTerlibat.add("maklum_kpsn_mmkn2");
                    stageTerlibat.add("kpsn_mmkn2");
                }

                if (permohonan.getKodUrusan().getKod().equalsIgnoreCase("425") || permohonan.getKodUrusan().getKod().equalsIgnoreCase("425A")) {
//                    stageTerlibatEdit.add("semak_draf_mmkn3_1");
//                    stageTerlibatEdit.add("semak_draf_mmkn3_2");
                    stageTerlibatEdit.add("semak_draf_mmkn3_3");
                    stageTerlibatEdit.add("sah_kertas_mmkn");
                }

                if (permohonan.getKodUrusan().getKod().equalsIgnoreCase("425") || permohonan.getKodUrusan().getKod().equalsIgnoreCase("425A")) {
//                    stageTerlibatViewPtg.add("semak_draf_mmkn3_1");
//                    stageTerlibatViewPtg.add("semak_draf_mmkn3_2");
                    stageTerlibatViewPtg.add("semak_draf_mmkn3_3");
                }

                if (permohonan.getKodUrusan().getKod().equalsIgnoreCase("425") || permohonan.getKodUrusan().getKod().equalsIgnoreCase("425A")) {
                    stageTerlibatEditPerakuanPT.add("semak_draf_mmkn3_1");
                    stageTerlibatEditPerakuanPT.add("semak_draf_mmkn3_2");
                }
            }

            for (Object s : stageTerlibat) {
                if (stageId.equalsIgnoreCase(s.toString())) {
                    statusPMMK = true;
                }
            }

            for (Object s : stageTerlibatEdit) {
                if (stageId.equalsIgnoreCase(s.toString())) {
                    statusEditInfo = true;
                }
            }

            for (Object s : stageTerlibatViewPtg) {
                if (stageId.equalsIgnoreCase(s.toString())) {
                    ulasanPtgView = true;
                }
            }

            for (Object s : stageTerlibatEditPerakuanPT) {
                if (stageId.equalsIgnoreCase(s.toString())) {
                    statusEditPerakuanPT = true;
                }
            }

            LOG.info("::::::statusPMMK :" + statusPMMK);
            LOG.info("::::::statusEditInfo :" + statusEditInfo);
            LOG.info("::::::ulasanPtgView :" + ulasanPtgView);
            LOG.info("::::::statusEditPerakuanPT :" + statusEditPerakuanPT);
            LOG.info("::::::userPtgEditPerakuanPTD :" + userPtgEditPerakuanPTD);


            if (statusPMMK == true) {
                kodDokumenMMKN = "PMMK";
            } else {
                kodDokumenMMKN = "MMKN";
            }

            senaraiKertasKandungan1 = new ArrayList<PermohonanKertasKandungan>();
            senaraiKertasKandungan2 = new ArrayList<PermohonanKertasKandungan>();
            senaraiKertasKandungan3 = new ArrayList<PermohonanKertasKandungan>();
            senaraiKertasKandungan4 = new ArrayList<PermohonanKertasKandungan>();
            senaraiKertasKandungan5 = new ArrayList<PermohonanKertasKandungan>();

            permohonanKertas = enforceService.findByIdKrts(idPermohonan, kodDokumenMMKN);
            if (permohonanKertas != null) {
                tajuk = permohonanKertas.getTajuk();
                kodRujukan = permohonanKertas.getNomborRujukan();

                senaraiKertasKandungan = enforceService.findByIdKertas2(permohonanKertas.getIdKertas());
                if (senaraiKertasKandungan != null) {

                    for (int i = 0; i < senaraiKertasKandungan.size(); i++) {
                        if (senaraiKertasKandungan.get(i).getSubtajuk().equalsIgnoreCase("1.1")) {
                            permohonanKertasKandungan = enforceService.findKertasDetail(permohonanKertas.getIdKertas(), "1.1");
                            if (permohonanKertasKandungan != null) {
                                tujuan = permohonanKertasKandungan.getKandungan();
                            }
                        } else if (senaraiKertasKandungan.get(i).getSubtajuk().equalsIgnoreCase("2.1")) {
                            permohonanKertasKandungan = enforceService.findKertasDetail(permohonanKertas.getIdKertas(), "2.1");
                            if (permohonanKertasKandungan != null) {
                                latarBelakang = permohonanKertasKandungan.getKandungan();
                            }

                        } else if (senaraiKertasKandungan.get(i).getSubtajuk().equalsIgnoreCase("3.1")) {
                            permohonanKertasKandungan = enforceService.findKertasDetail(permohonanKertas.getIdKertas(), "3.1");
                            if (permohonanKertasKandungan != null) {
                                ulasan = permohonanKertasKandungan.getKandungan();
                            }

                        } else if (senaraiKertasKandungan.get(i).getSubtajuk().equalsIgnoreCase("4.1")) {
                            permohonanKertasKandungan = enforceService.findKertasDetail(permohonanKertas.getIdKertas(), "4.1");
                            if (permohonanKertasKandungan != null) {
                                keputusan = permohonanKertasKandungan.getKandungan();
                            }


                        } else if (senaraiKertasKandungan.get(i).getSubtajuk().equalsIgnoreCase("5.1")) {
                            permohonanKertasKandungan = enforceService.findKertasDetail(permohonanKertas.getIdKertas(), "5.1");
                            if (permohonanKertasKandungan != null) {
                                ulasanPtg = permohonanKertasKandungan.getKandungan();
                            }

                        }
                    }

                }

                senaraiKertasKandungan1 = enforceService.listKandunganByBilAsc(permohonanKertas.getIdKertas(), 1, "1.1");
                senaraiKertasKandungan2 = enforceService.listKandunganByBilAsc(permohonanKertas.getIdKertas(), 2, "2.1");
                senaraiKertasKandungan3 = enforceService.listKandunganByBilAsc(permohonanKertas.getIdKertas(), 3, "3.1");
                senaraiKertasKandungan4 = enforceService.listKandunganByBilAsc(permohonanKertas.getIdKertas(), 4, "4.1");
                senaraiKertasKandungan5 = enforceService.listKandunganByBilAsc(permohonanKertas.getIdKertas(), 5, "5.1");


            }

            rowCount1 = String.valueOf(senaraiKertasKandungan1.size());
            rowCount2 = String.valueOf(senaraiKertasKandungan2.size());
            rowCount3 = String.valueOf(senaraiKertasKandungan3.size());
            rowCount4 = String.valueOf(senaraiKertasKandungan4.size());
            rowCount5 = String.valueOf(senaraiKertasKandungan5.size());

        }
    }

    public Resolution simpan() {
        LOG.info("--------------simpan--------------");

        InfoAudit ia = new InfoAudit();

        if (permohonanKertas == null) {
            permohonanKertas = new PermohonanKertas();
            ia.setDimasukOleh(pengguna);
            ia.setTarikhMasuk(new java.util.Date());
        } else {
            ia = permohonanKertas.getInfoAudit();
            ia.setDiKemaskiniOleh(pengguna);
            ia.setTarikhKemaskini(new java.util.Date());

        }
        permohonanKertas.setPermohonan(permohonan);
        permohonanKertas.setInfoAudit(ia);
        permohonanKertas.setTajuk(tajuk);
        permohonanKertas.setCawangan(pengguna.getKodCawangan());
        permohonanKertas.setKodDokumen(kodDokumenDAO.findById(kodDokumenMMKN));
        if ("05".equalsIgnoreCase(conf.getProperty("kodNegeri"))) {
            permohonanKertas.setNomborRujukan(kodRujukan);
        }
        enforceService.simpanPermohonanKertas(permohonanKertas);

        //to save tujuan (1.1)
        if (tujuan != null) {
            permohonanKertasKandungan = enforceService.findKertasDetail(permohonanKertas.getIdKertas(), "1.1");
            if (permohonanKertasKandungan != null) {
                ia = permohonanKertasKandungan.getInfoAudit();
                ia.setDiKemaskiniOleh(pengguna);
                ia.setTarikhKemaskini(new java.util.Date());
            } else {
                permohonanKertasKandungan = new PermohonanKertasKandungan();
                ia.setDimasukOleh(pengguna);
                ia.setTarikhMasuk(new java.util.Date());
            }
            permohonanKertasKandungan.setKertas(permohonanKertas);
            permohonanKertasKandungan.setBil(1);
            permohonanKertasKandungan.setKandungan(tujuan);
            permohonanKertasKandungan.setCawangan(permohonan.getCawangan());
            permohonanKertasKandungan.setSubtajuk("1.1");
            permohonanKertasKandungan.setInfoAudit(ia);
            enforceService.simpanPermohonanKertasKandungan(permohonanKertasKandungan);
        }

        //to save latar belakang (2.1)
        if (latarBelakang != null) {
            permohonanKertasKandungan = enforceService.findKertasDetail(permohonanKertas.getIdKertas(), "2.1");
            if (permohonanKertasKandungan != null) {
                ia = permohonanKertasKandungan.getInfoAudit();
                ia.setDiKemaskiniOleh(pengguna);
                ia.setTarikhKemaskini(new java.util.Date());
            } else {
                permohonanKertasKandungan = new PermohonanKertasKandungan();
                ia.setDimasukOleh(pengguna);
                ia.setTarikhMasuk(new java.util.Date());
            }
            permohonanKertasKandungan.setKertas(permohonanKertas);
            permohonanKertasKandungan.setBil(2);
            permohonanKertasKandungan.setKandungan(latarBelakang);
            permohonanKertasKandungan.setCawangan(permohonan.getCawangan());
            permohonanKertasKandungan.setSubtajuk("2.1");
            permohonanKertasKandungan.setInfoAudit(ia);
            enforceService.simpanPermohonanKertasKandungan(permohonanKertasKandungan);
        }

        //to save ulasan (3.1)
        if (ulasan != null) {
            permohonanKertasKandungan = enforceService.findKertasDetail(permohonanKertas.getIdKertas(), "3.1");
            if (permohonanKertasKandungan != null) {
                ia = permohonanKertasKandungan.getInfoAudit();
                ia.setDiKemaskiniOleh(pengguna);
                ia.setTarikhKemaskini(new java.util.Date());
            } else {
                permohonanKertasKandungan = new PermohonanKertasKandungan();
                ia.setDimasukOleh(pengguna);
                ia.setTarikhMasuk(new java.util.Date());
            }
            permohonanKertasKandungan.setKertas(permohonanKertas);
            permohonanKertasKandungan.setBil(3);
            permohonanKertasKandungan.setKandungan(ulasan);
            permohonanKertasKandungan.setCawangan(permohonan.getCawangan());
            permohonanKertasKandungan.setSubtajuk("3.1");
            permohonanKertasKandungan.setInfoAudit(ia);
            enforceService.simpanPermohonanKertasKandungan(permohonanKertasKandungan);
        }

        //to save keputusan (4.1)
        if (keputusan != null) {
            permohonanKertasKandungan = enforceService.findKertasDetail(permohonanKertas.getIdKertas(), "4.1");
            if (permohonanKertasKandungan != null) {
                ia = permohonanKertasKandungan.getInfoAudit();
                ia.setDiKemaskiniOleh(pengguna);
                ia.setTarikhKemaskini(new java.util.Date());
            } else {
                permohonanKertasKandungan = new PermohonanKertasKandungan();
                ia.setDimasukOleh(pengguna);
                ia.setTarikhMasuk(new java.util.Date());
            }
            permohonanKertasKandungan.setKertas(permohonanKertas);
            permohonanKertasKandungan.setBil(4);
            permohonanKertasKandungan.setKandungan(keputusan);
            permohonanKertasKandungan.setCawangan(permohonan.getCawangan());
            permohonanKertasKandungan.setSubtajuk("4.1");
            permohonanKertasKandungan.setInfoAudit(ia);
            enforceService.simpanPermohonanKertasKandungan(permohonanKertasKandungan);
        }

        //to save ulasanPtg (5.1)
        if (ulasanPtg != null) {
            permohonanKertasKandungan = enforceService.findKertasDetail(permohonanKertas.getIdKertas(), "5.1");
            if (permohonanKertasKandungan != null) {
                ia = permohonanKertasKandungan.getInfoAudit();
                ia.setDiKemaskiniOleh(pengguna);
                ia.setTarikhKemaskini(new java.util.Date());
            } else {
                permohonanKertasKandungan = new PermohonanKertasKandungan();
                ia.setDimasukOleh(pengguna);
                ia.setTarikhMasuk(new java.util.Date());
            }
            permohonanKertasKandungan.setKertas(permohonanKertas);
            permohonanKertasKandungan.setBil(5);
            permohonanKertasKandungan.setKandungan(ulasanPtg);
            permohonanKertasKandungan.setCawangan(permohonan.getCawangan());
            permohonanKertasKandungan.setSubtajuk("5.1");
            permohonanKertasKandungan.setInfoAudit(ia);
            System.out.println("masuk tak dalam simpan ulasan PTG");
            enforceService.simpanPermohonanKertasKandungan(permohonanKertasKandungan);
        }


        //to save senaraiKertaskandungan1 list
        int k = 2;
        if (!rowCount1.equalsIgnoreCase("")) {
            for (int i = 1; i <= Integer.parseInt(rowCount1); i++) {
                PermohonanKertasKandungan permohonanKertasKandungan1 = new PermohonanKertasKandungan();
                if (senaraiKertasKandungan1.size() != 0 && i <= senaraiKertasKandungan1.size()) {
                    Long id = senaraiKertasKandungan1.get(i - 1).getIdKandungan();
                    if (id != null) {
                        permohonanKertasKandungan1 = permohonanKertasKandunganDAO.findById(id);
                        ia = permohonanKertasKandungan1.getInfoAudit();
                        ia.setDiKemaskiniOleh(pengguna);
                        ia.setTarikhKemaskini(new java.util.Date());
                    }
                } else {
                    permohonanKertasKandungan1 = new PermohonanKertasKandungan();
                    ia.setDimasukOleh(pengguna);
                    ia.setTarikhMasuk(new java.util.Date());
                }
                permohonanKertasKandungan1.setKertas(permohonanKertas);
                permohonanKertasKandungan1.setBil(1);
                String kandungan = getContext().getRequest().getParameter("kandungan1" + i);
                permohonanKertasKandungan1.setKandungan(kandungan);
                permohonanKertasKandungan1.setCawangan(permohonan.getCawangan());
                permohonanKertasKandungan1.setSubtajuk("1." + k);
                permohonanKertasKandungan1.setInfoAudit(ia);
                enforceService.simpanPermohonanKertasKandungan(permohonanKertasKandungan1);
                k++;
            }
        }

        //to save senaraiKertaskandungan2 list
        if (!rowCount2.equalsIgnoreCase("")) {
            for (int i = 1; i <= Integer.parseInt(rowCount2); i++) {
                PermohonanKertasKandungan permohonanKertasKandungan2 = new PermohonanKertasKandungan();
                if (senaraiKertasKandungan2.size() != 0 && i <= senaraiKertasKandungan2.size()) {
                    Long id = senaraiKertasKandungan2.get(i - 1).getIdKandungan();
                    if (id != null) {
                        permohonanKertasKandungan2 = permohonanKertasKandunganDAO.findById(id);
                        ia = permohonanKertasKandungan2.getInfoAudit();
                        ia.setDiKemaskiniOleh(pengguna);
                        ia.setTarikhKemaskini(new java.util.Date());
                    }
                } else {
                    permohonanKertasKandungan2 = new PermohonanKertasKandungan();
                    ia.setDimasukOleh(pengguna);
                    ia.setTarikhMasuk(new java.util.Date());
                }
                permohonanKertasKandungan2.setKertas(permohonanKertas);
                permohonanKertasKandungan2.setBil(2);
                String kandungan = getContext().getRequest().getParameter("kandungan2" + i);
                permohonanKertasKandungan2.setKandungan(kandungan);
                permohonanKertasKandungan2.setCawangan(permohonan.getCawangan());
                permohonanKertasKandungan2.setSubtajuk("2." + k);
                permohonanKertasKandungan2.setInfoAudit(ia);
                enforceService.simpanPermohonanKertasKandungan(permohonanKertasKandungan2);
                k++;
            }
        }

        //to save senaraiKertaskandungan3 list
        if (!rowCount3.equalsIgnoreCase("")) {
            for (int i = 1; i <= Integer.parseInt(rowCount3); i++) {
                PermohonanKertasKandungan permohonanKertasKandungan3 = new PermohonanKertasKandungan();
                if (senaraiKertasKandungan3.size() != 0 && i <= senaraiKertasKandungan3.size()) {
                    Long id = senaraiKertasKandungan3.get(i - 1).getIdKandungan();
                    if (id != null) {
                        permohonanKertasKandungan3 = permohonanKertasKandunganDAO.findById(id);
                        ia = permohonanKertasKandungan3.getInfoAudit();
                        ia.setDiKemaskiniOleh(pengguna);
                        ia.setTarikhKemaskini(new java.util.Date());
                    }
                } else {
                    permohonanKertasKandungan3 = new PermohonanKertasKandungan();
                    ia.setDimasukOleh(pengguna);
                    ia.setTarikhMasuk(new java.util.Date());
                }
                permohonanKertasKandungan3.setKertas(permohonanKertas);
                permohonanKertasKandungan3.setBil(3);
                String kandungan = getContext().getRequest().getParameter("kandungan3" + i);
                permohonanKertasKandungan3.setKandungan(kandungan);
                permohonanKertasKandungan3.setCawangan(permohonan.getCawangan());
                permohonanKertasKandungan3.setSubtajuk("3." + k);
                permohonanKertasKandungan3.setInfoAudit(ia);
                enforceService.simpanPermohonanKertasKandungan(permohonanKertasKandungan3);
                k++;
            }
        }

        //to save senaraiKertaskandungan4 list
        if (!rowCount4.equalsIgnoreCase("")) {
            for (int i = 1; i <= Integer.parseInt(rowCount4); i++) {
                PermohonanKertasKandungan permohonanKertasKandungan4 = new PermohonanKertasKandungan();
                if (senaraiKertasKandungan4.size() != 0 && i <= senaraiKertasKandungan4.size()) {
                    Long id = senaraiKertasKandungan4.get(i - 1).getIdKandungan();
                    if (id != null) {
                        permohonanKertasKandungan4 = permohonanKertasKandunganDAO.findById(id);
                        ia = permohonanKertasKandungan4.getInfoAudit();
                        ia.setDiKemaskiniOleh(pengguna);
                        ia.setTarikhKemaskini(new java.util.Date());
                    }
                } else {
                    permohonanKertasKandungan4 = new PermohonanKertasKandungan();
                    ia.setDimasukOleh(pengguna);
                    ia.setTarikhMasuk(new java.util.Date());
                }
                permohonanKertasKandungan4.setKertas(permohonanKertas);
                permohonanKertasKandungan4.setBil(4);
                String kandungan = getContext().getRequest().getParameter("kandungan4" + i);
                permohonanKertasKandungan4.setKandungan(kandungan);
                permohonanKertasKandungan4.setCawangan(permohonan.getCawangan());
                permohonanKertasKandungan4.setSubtajuk("4." + k);
                permohonanKertasKandungan4.setInfoAudit(ia);
                enforceService.simpanPermohonanKertasKandungan(permohonanKertasKandungan4);
                k++;
            }
        }

        //to save senaraiKertaskandungan5 list
        if (!rowCount5.equalsIgnoreCase("")) {
            for (int i = 1; i <= Integer.parseInt(rowCount5); i++) {
                PermohonanKertasKandungan permohonanKertasKandungan5 = new PermohonanKertasKandungan();
                if (senaraiKertasKandungan5.size() != 0 && i <= senaraiKertasKandungan5.size()) {
                    Long id = senaraiKertasKandungan5.get(i - 1).getIdKandungan();
                    if (id != null) {
                        permohonanKertasKandungan5 = permohonanKertasKandunganDAO.findById(id);
                        ia = permohonanKertasKandungan5.getInfoAudit();
                        ia.setDiKemaskiniOleh(pengguna);
                        ia.setTarikhKemaskini(new java.util.Date());
                    }
                } else {
                    permohonanKertasKandungan5 = new PermohonanKertasKandungan();
                    ia.setDimasukOleh(pengguna);
                    ia.setTarikhMasuk(new java.util.Date());
                }
                permohonanKertasKandungan5.setKertas(permohonanKertas);
                permohonanKertasKandungan5.setBil(5);
                String kandungan = getContext().getRequest().getParameter("kandungan5" + i);
                permohonanKertasKandungan5.setKandungan(kandungan);
                permohonanKertasKandungan5.setCawangan(permohonan.getCawangan());
                permohonanKertasKandungan5.setSubtajuk("5." + k);
                permohonanKertasKandungan5.setInfoAudit(ia);
                enforceService.simpanPermohonanKertasKandungan(permohonanKertasKandungan5);
                k++;
            }
        }
        if (stageId.equalsIgnoreCase("terima_semak_risalat_mmkn") || stageId.equalsIgnoreCase("sah_kertas_mmkn")
                || stageId.equalsIgnoreCase("semak_draf_mmkn3_1") || stageId.equalsIgnoreCase("semak_draf_mmkn3_2")) {
//        if (ulasanPtg != null || keputusan != null) {
            System.out.println("ulasan PTG tak null");
            getContext().getRequest().setAttribute("ulasanPTG", Boolean.TRUE);
        } else {
            System.out.println("masuk else");
            getContext().getRequest().setAttribute("edit", Boolean.TRUE);

        }

        //update numbering for subtajuk

        senaraiKertasKandungan1 = enforceService.listKandunganByBilAsc(permohonanKertas.getIdKertas(), 1, "1.1");
        senaraiKertasKandungan2 = enforceService.listKandunganByBilAsc(permohonanKertas.getIdKertas(), 2, "2.1");

        int i = 2;
        for (PermohonanKertasKandungan p : senaraiKertasKandungan1) {
            p.setSubtajuk("1." + i);
            enforceService.simpanPermohonanKertasKandungan(p);
            i++;
        }
        int j = 2;
        for (PermohonanKertasKandungan p1 : senaraiKertasKandungan2) {
            p1.setSubtajuk("2." + j);
            enforceService.simpanPermohonanKertasKandungan(p1);
            j++;
        }

        //update ulasan PTD & PTG at ringkasan MMKN
        PermohonanKertas pk = enforceService.findByIdKrts(idPermohonan, "RMN");
        boolean perakuanPTG = false;
        boolean perakuanPTD = false;
        if (pk != null) {
            for (PermohonanKertasKandungan pkk : pk.getSenaraiKandungan()) {
                if (pkk.getBil() == 7) { // perakuan ptg
                    perakuanPTG = true;
                } else if (pkk.getBil() == 9) {
                    perakuanPTD = true;
                }

                if (perakuanPTG) {
                    pkk.setKandungan(keputusan);
                    enforceService.simpanPermohonanKertasKandungan(pkk);
                }

                if (perakuanPTD) {
                    pkk.setKandungan(ulasan);
                    enforceService.simpanPermohonanKertasKandungan(pkk);
                }
            }


        }

        addSimpleMessage("Maklumat telah berjaya disimpan.");
        rehydrate();
        return new ForwardResolution("/WEB-INF/jsp/penguatkuasaan/maklumat_kertas_MMKN.jsp").addParameter("tab", "true");
    }

    public Resolution simpanUlasan() {
        LOG.info("--------------simpanUlasan--------------");

        InfoAudit ia = new InfoAudit();

        if (permohonanKertas == null) {
            permohonanKertas = new PermohonanKertas();
            ia.setDimasukOleh(pengguna);
            ia.setTarikhMasuk(new java.util.Date());
        } else {
            ia = permohonanKertas.getInfoAudit();
            ia.setDiKemaskiniOleh(pengguna);
            ia.setTarikhKemaskini(new java.util.Date());
        }
        int k = 2;
        //to save senaraiKertaskandungan3 list
        if (!rowCount3.equalsIgnoreCase("")) {
            for (int i = 1; i <= Integer.parseInt(rowCount3); i++) {
                PermohonanKertasKandungan permohonanKertasKandungan3 = new PermohonanKertasKandungan();
                if (senaraiKertasKandungan3.size() != 0 && i <= senaraiKertasKandungan3.size()) {
                    Long id = senaraiKertasKandungan3.get(i - 1).getIdKandungan();
                    if (id != null) {
                        permohonanKertasKandungan3 = permohonanKertasKandunganDAO.findById(id);
                        ia = permohonanKertasKandungan3.getInfoAudit();
                        ia.setDiKemaskiniOleh(pengguna);
                        ia.setTarikhKemaskini(new java.util.Date());
                    }
                } else {
                    permohonanKertasKandungan3 = new PermohonanKertasKandungan();
                    ia.setDimasukOleh(pengguna);
                    ia.setTarikhMasuk(new java.util.Date());
                }
                permohonanKertasKandungan3.setKertas(permohonanKertas);
                permohonanKertasKandungan3.setBil(3);
                String kandungan = getContext().getRequest().getParameter("kandungan3" + i);
                permohonanKertasKandungan3.setKandungan(kandungan);
                permohonanKertasKandungan3.setCawangan(permohonan.getCawangan());
                permohonanKertasKandungan3.setSubtajuk("3." + k);
                permohonanKertasKandungan3.setInfoAudit(ia);
                enforceService.simpanPermohonanKertasKandungan(permohonanKertasKandungan3);
                k++;
            }
        }
        getContext().getRequest().setAttribute("ulasanPTD", Boolean.TRUE);
        addSimpleMessage("Maklumat telah berjaya disimpan.");
        rehydrate();
        return new ForwardResolution("/WEB-INF/jsp/penguatkuasaan/maklumat_kertas_MMKN.jsp").addParameter("tab", "true");
    }

    public Resolution deleteSingle() {
        LOG.info("--------------deleteSingle--------------");
        idKandungan = getContext().getRequest().getParameter("idKandungan");
        LOG.info("--------------idKandungan : --------------" + idKandungan);
        permohonanKertasKandungan = new PermohonanKertasKandungan();

        try {
            if (idKandungan != null) {
                permohonanKertasKandungan = enforceService.findByIdKKand(idKandungan);
            }
            enforceService.deleteDiariSiasatan(permohonanKertasKandungan);
        } catch (Exception e) {
            e.printStackTrace();
            addSimpleError("kesalahan dalam menghapuskan rekod");
        }

        getContext().getRequest().setAttribute("ulasanPTG", Boolean.TRUE);
        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        addSimpleMessage("Rekod telah berjaya dihapuskan");
        return new RedirectResolution(MaklumatKertasMMKNActionBean.class, "locate");
//        return new JSP("penguatkuasaan/maklumat_kertas_MMKN.jsp").addParameter("tab", "true");
    }

    public Resolution deleteUlasanPtg() {
        LOG.info("--------------deleteUlasanPTG--------------");
        idKandungan = getContext().getRequest().getParameter("idKandungan");
        LOG.info("--------------idKandungan : --------------" + idKandungan);
        permohonanKertasKandungan = new PermohonanKertasKandungan();
        try {
            if (idKandungan != null) {
                permohonanKertasKandungan = enforceService.findByIdKKand(idKandungan);
            }
            enforceService.deleteDiariSiasatan(permohonanKertasKandungan);
        } catch (Exception e) {
            e.printStackTrace();
            addSimpleError("kesalahan dalam menghapuskan rekod");
        }

        getContext().getRequest().setAttribute("ulasanPTG", Boolean.TRUE);
        addSimpleMessage("Rekod telah berjaya dihapuskan");
        return new RedirectResolution(MaklumatKertasMMKNActionBean.class, "locate");
//        return new JSP("penguatkuasaan/maklumat_kertas_MMKN.jsp").addParameter("tab", "true");
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

    public PermohonanKertas getPermohonanKertas() {
        return permohonanKertas;
    }

    public void setPermohonanKertas(PermohonanKertas permohonanKertas) {
        this.permohonanKertas = permohonanKertas;
    }

    public List<PermohonanKertasKandungan> getSenaraiKertasKandungan1() {
        return senaraiKertasKandungan1;
    }

    public void setSenaraiKertasKandungan1(List<PermohonanKertasKandungan> senaraiKertasKandungan1) {
        this.senaraiKertasKandungan1 = senaraiKertasKandungan1;
    }

    public String getTajuk() {
        return tajuk;
    }

    public void setTajuk(String tajuk) {
        this.tajuk = tajuk;
    }

    public String getTujuan() {
        return tujuan;
    }

    public void setTujuan(String tujuan) {
        this.tujuan = tujuan;
    }

    public PermohonanKertasKandungan getPermohonanKertasKandungan() {
        return permohonanKertasKandungan;
    }

    public void setPermohonanKertasKandungan(PermohonanKertasKandungan permohonanKertasKandungan) {
        this.permohonanKertasKandungan = permohonanKertasKandungan;
    }

    public List<PermohonanKertasKandungan> getSenaraiKertasKandungan2() {
        return senaraiKertasKandungan2;
    }

    public void setSenaraiKertasKandungan2(List<PermohonanKertasKandungan> senaraiKertasKandungan2) {
        this.senaraiKertasKandungan2 = senaraiKertasKandungan2;
    }

    public String getRowCountTable() {
        return rowCountTable;
    }

    public void setRowCountTable(String rowCountTable) {
        this.rowCountTable = rowCountTable;
    }

    public String getCurrentNum() {
        return currentNum;
    }

    public void setCurrentNum(String currentNum) {
        this.currentNum = currentNum;
    }

    public List<PermohonanKertasKandungan> getSenaraiKertasKandungan() {
        return senaraiKertasKandungan;
    }

    public void setSenaraiKertasKandungan(List<PermohonanKertasKandungan> senaraiKertasKandungan) {
        this.senaraiKertasKandungan = senaraiKertasKandungan;
    }

    public String getKeputusan() {
        return keputusan;
    }

    public void setKeputusan(String keputusan) {
        this.keputusan = keputusan;
    }

    public String getLatarBelakang() {
        return latarBelakang;
    }

    public void setLatarBelakang(String latarBelakang) {
        this.latarBelakang = latarBelakang;
    }

    public String getUlasan() {
        return ulasan;
    }

    public void setUlasan(String ulasan) {
        this.ulasan = ulasan;
    }

    public List<PermohonanKertasKandungan> getSenaraiKertasKandungan3() {
        return senaraiKertasKandungan3;
    }

    public void setSenaraiKertasKandungan3(List<PermohonanKertasKandungan> senaraiKertasKandungan3) {
        this.senaraiKertasKandungan3 = senaraiKertasKandungan3;
    }

    public List<PermohonanKertasKandungan> getSenaraiKertasKandungan4() {
        return senaraiKertasKandungan4;
    }

    public void setSenaraiKertasKandungan4(List<PermohonanKertasKandungan> senaraiKertasKandungan4) {
        this.senaraiKertasKandungan4 = senaraiKertasKandungan4;
    }

    public String getRowCount1() {
        return rowCount1;
    }

    public void setRowCount1(String rowCount1) {
        this.rowCount1 = rowCount1;
    }

    public String getRowCount2() {
        return rowCount2;
    }

    public void setRowCount2(String rowCount2) {
        this.rowCount2 = rowCount2;
    }

    public String getRowCount3() {
        return rowCount3;
    }

    public void setRowCount3(String rowCount3) {
        this.rowCount3 = rowCount3;
    }

    public String getRowCount4() {
        return rowCount4;
    }

    public void setRowCount4(String rowCount4) {
        this.rowCount4 = rowCount4;
    }

    public String getIdKandungan() {
        return idKandungan;
    }

    public void setIdKandungan(String idKandungan) {
        this.idKandungan = idKandungan;
    }

    public String getUlasanPtg() {
        return ulasanPtg;
    }

    public void setUlasanPtg(String ulasanPtg) {
        this.ulasanPtg = ulasanPtg;
    }

    public List<PermohonanKertasKandungan> getSenaraiKertasKandungan5() {
        return senaraiKertasKandungan5;
    }

    public void setSenaraiKertasKandungan5(List<PermohonanKertasKandungan> senaraiKertasKandungan5) {
        this.senaraiKertasKandungan5 = senaraiKertasKandungan5;
    }

    public String getRowCount5() {
        return rowCount5;
    }

    public void setRowCount5(String rowCount5) {
        this.rowCount5 = rowCount5;
    }

    public String getStageId() {
        return stageId;
    }

    public void setStageId(String stageId) {
        this.stageId = stageId;
    }

    public String getKodNegeri() {
        return kodNegeri;
    }

    public void setKodNegeri(String kodNegeri) {
        this.kodNegeri = kodNegeri;
    }

    public String getKodRujukan() {
        return kodRujukan;
    }

    public void setKodRujukan(String kodRujukan) {
        this.kodRujukan = kodRujukan;
    }

    public Boolean getStatusPMMK() {
        return statusPMMK;
    }

    public void setStatusPMMK(Boolean statusPMMK) {
        this.statusPMMK = statusPMMK;
    }

    public String getKodDokumenMMKN() {
        return kodDokumenMMKN;
    }

    public void setKodDokumenMMKN(String kodDokumenMMKN) {
        this.kodDokumenMMKN = kodDokumenMMKN;
    }

    public Boolean getStatusEditInfo() {
        return statusEditInfo;
    }

    public void setStatusEditInfo(Boolean statusEditInfo) {
        this.statusEditInfo = statusEditInfo;
    }

    public Boolean getUlasanPtgView() {
        return ulasanPtgView;
    }

    public void setUlasanPtgView(Boolean ulasanPtgView) {
        this.ulasanPtgView = ulasanPtgView;
    }

    public Boolean getStatusEditPerakuanPT() {
        return statusEditPerakuanPT;
    }

    public void setStatusEditPerakuanPT(Boolean statusEditPerakuanPT) {
        this.statusEditPerakuanPT = statusEditPerakuanPT;
    }

    public Boolean getUserPtgEditPerakuanPTD() {
        return userPtgEditPerakuanPTD;
    }

    public void setUserPtgEditPerakuanPTD(Boolean userPtgEditPerakuanPTD) {
        this.userPtgEditPerakuanPTD = userPtgEditPerakuanPTD;
    }
}
