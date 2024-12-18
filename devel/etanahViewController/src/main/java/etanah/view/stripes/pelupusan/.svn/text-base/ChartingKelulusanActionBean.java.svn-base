/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.stripes.pelupusan;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;
import etanah.service.PembangunanService;
import etanah.model.Permohonan;
import etanah.model.FasaPermohonan;
import etanah.model.HakmilikPermohonan;
import etanah.dao.PermohonanDAO;
import etanah.view.etanahActionBeanContext;
import etanah.model.Pengguna;
import etanah.service.BPelService;
import etanah.view.stripes.pelupusan.disClass.DisLaporanTanahService;
import org.apache.commons.lang.StringUtils;
import oracle.bpel.services.workflow.task.model.Task;
import org.apache.log4j.Logger;
import etanah.dao.KodKeputusanDAO;
import etanah.model.InfoAudit;
import etanah.service.PelupusanService;

/**
 *
 * @author nursyazwani
 */
@UrlBinding("/pelupusan/chartingKelulusan")
public class ChartingKelulusanActionBean extends AbleActionBean {

    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    PembangunanService devService;
    @Inject
    DisLaporanTanahService disLaporanTanahService;
    @Inject
    etanah.Configuration conf;
    @Inject
    KodKeputusanDAO kodKeputusanDAO;
    @Inject
    PelupusanService pelupusanService;
    private Permohonan permohonan;
    private FasaPermohonan fasaMohon;
    private HakmilikPermohonan hakmilikPermohonan;
    private Pengguna peng;
    private String stageId;
    private String keputusan;
    private String kodNegeri;
    private String idPermohonan = new String();
    private static final Logger LOG = Logger.getLogger(ChartingKelulusanActionBean.class);

    @DefaultHandler
    public Resolution showForm() {
        return new JSP("pelupusan/charting_kelulusan.jsp").addParameter("tab", "true");
    }

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {

        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);

        permohonan = permohonanDAO.findById(idPermohonan);
        kodNegeri = disLaporanTanahService.getConf().getProperty("kodNegeri");

        if (!(permohonan.getSenaraiFasa().isEmpty())) {

            for (int i = 0; i < permohonan.getSenaraiFasa().size(); i++) {

                fasaMohon = new FasaPermohonan();
                fasaMohon = permohonan.getSenaraiFasa().get(i);
                if (permohonan.getKodUrusan().getKod().equalsIgnoreCase("PRIZ")) {
                    if (fasaMohon.getIdAliran().equals("014")) {
                        keputusan = fasaMohon.getKeputusan().getNama();
                    }
                } else if (permohonan.getKodUrusan().getKod().equalsIgnoreCase("PBGSA")) {
                    if (fasaMohon.getIdAliran().equals("33Rekodkputsan")) {
                        keputusan = fasaMohon.getKeputusan().getNama();
                    }
                } else if (permohonan.getKodUrusan().getKod().equalsIgnoreCase("PHLA")) {
                    if (fasaMohon.getIdAliran().equals("20BtPrnth")) {
                        keputusan = fasaMohon.getKeputusan().getNama();
                    }
                } else if (permohonan.getKodUrusan().getKod().equalsIgnoreCase("MMMCL")) {
                    if (fasaMohon.getIdAliran().equals("perakuan_ptd")) {
                        keputusan = fasaMohon.getKeputusan().getNama();
                    }
                } else if (permohonan.getKodUrusan().getKod().equalsIgnoreCase("PBRZ")) {
                    if (fasaMohon.getIdAliran().equals("16TrmKptsnMMK")) {
                        keputusan = fasaMohon.getKeputusan().getNama();
                    }
                } //                else if (permohonan.getKodUrusan().getKod().equalsIgnoreCase("MLCRG")) {
                //                    if (fasaMohon.getIdAliran().equals("g_charting_keputusan")) {
                //                        keputusan = fasaMohon.getKeputusan().getNama();
                //                    }
                //                }
                else if (permohonan.getKodUrusan().getKod().equalsIgnoreCase("PBHL")) {
                    if (fasaMohon.getIdAliran().equals("A03BuatPerintah")) {
                        keputusan = fasaMohon.getKeputusan().getNama();
                    }
                } else if (permohonan.getKodUrusan().getKod().equalsIgnoreCase("PBMMK")) {
                    System.out.println("PBMMK " + fasaMohon.getIdAliran());
                    if (fasaMohon.getIdAliran().equals("07SDP")) {
                        //keputusan = fasaMohon.getKeputusan().getNama();
                        if (fasaMohon.getKeputusan().getKod().equals("PL")) {
                            keputusan = "T";
                            InfoAudit info = new InfoAudit();
                            info.setDimasukOleh(peng);
                            info.setTarikhMasuk(new java.util.Date());
                            permohonan.setKeputusan(kodKeputusanDAO.findById(keputusan));
                            permohonan.setKeputusanOleh(peng);
                            info.setDimasukOleh(peng);
                            info.setTarikhMasuk(new java.util.Date());
                            permohonan.setTarikhKeputusan(new java.util.Date());
                            permohonan.setInfoAudit(info);
                            pelupusanService.simpanPermohonan(permohonan);
                        }
                    } else {
                        if (fasaMohon.getIdAliran().equals("25TrmKptsnMMK")) {

                            keputusan = fasaMohon.getKeputusan().getNama();
                        }
                    }
                } else if (permohonan.getKodUrusan().getKod().equalsIgnoreCase("PBPTG")) {
                    if (kodNegeri.equals("04")) {
                        if (fasaMohon.getIdAliran().equals("semak_peraku")) {
                            keputusan = fasaMohon.getKeputusan().getNama();
                        }
                    } else {
                        if (fasaMohon.getIdAliran().equals("07Sdp")) {
                            if (fasaMohon.getKeputusan().getKod().equals("PL")) {
                                keputusan = "T";
                                InfoAudit info = new InfoAudit();
                                info.setDimasukOleh(peng);
                                info.setTarikhMasuk(new java.util.Date());
                                permohonan.setKeputusan(kodKeputusanDAO.findById(keputusan));
                                permohonan.setKeputusanOleh(peng);
                                info.setDimasukOleh(peng);
                                info.setTarikhMasuk(new java.util.Date());
                                permohonan.setTarikhKeputusan(new java.util.Date());
                                permohonan.setInfoAudit(info);
                                pelupusanService.simpanPermohonan(permohonan);
                            }
                        } else if (fasaMohon.getIdAliran().equals("23Keputusan")) {
                            keputusan = fasaMohon.getKeputusan().getNama();
                        }
                    }
                } else if (permohonan.getKodUrusan().getKod().equalsIgnoreCase("PBPTD")) {
                    if (kodNegeri.equals("04")) {
                        if (fasaMohon.getIdAliran().equals("perakuan_draf_rencana_ptd")) {
                            keputusan = fasaMohon.getKeputusan().getNama();
                        }
                    } else {
                        if (fasaMohon.getIdAliran().equals("07Sdp")) {
                            if (fasaMohon.getKeputusan().getKod().equals("PL")) {
                                keputusan = "T";
                                InfoAudit info = new InfoAudit();
                                info.setDimasukOleh(peng);
                                info.setTarikhMasuk(new java.util.Date());
                                permohonan.setKeputusan(kodKeputusanDAO.findById(keputusan));
                                permohonan.setKeputusanOleh(peng);
                                permohonan.setTarikhKeputusan(new java.util.Date());
                                info.setDimasukOleh(peng);
                                info.setTarikhMasuk(new java.util.Date());
                                permohonan.setInfoAudit(info);
                                pelupusanService.simpanPermohonan(permohonan);
                            }
                        } else if (fasaMohon.getIdAliran().equals("15Keputusan")) {
                            keputusan = fasaMohon.getKeputusan().getNama();
                        }
                    }
                } else if (permohonan.getKodUrusan().getKod().equalsIgnoreCase("PPRU")) {
                    if (kodNegeri.equals("04")) {
                        //Not implemented
                    } else {
                        if (fasaMohon.getIdAliran().equals("20TrmKptsnMMK")) {
                            keputusan = fasaMohon.getKeputusan().getNama();
                        }
                    }
                } else if (permohonan.getKodUrusan().getKod().equalsIgnoreCase("PWGSA")) {
                    if (kodNegeri.equals("04")) {
                        //Not implemented
                    } else {
                        if (fasaMohon.getIdAliran().equals("19MklmnWartadanChartg")) {
                            keputusan = fasaMohon.getKeputusan().getNama();
                        }
                    }

                } else if (permohonan.getKodUrusan().getKod().equalsIgnoreCase("PTPBP")) {
                    System.out.println("PTPBP " + fasaMohon.getIdAliran());
                    if (kodNegeri.equals("04")) {
                        //Not implemented
                    } else {
                        if (fasaMohon.getIdAliran().equals("07SDP")) {
                            //keputusan = fasaMohon.getKeputusan().getNama();
                            if (fasaMohon.getKeputusan().getKod().equals("PL")) {
                                keputusan = "T";
                                InfoAudit info = new InfoAudit();
                                info = permohonan.getInfoAudit();
                                info.setDiKemaskiniOleh(peng);
                                info.setTarikhKemaskini(new java.util.Date());
                                permohonan.setKeputusan(kodKeputusanDAO.findById(keputusan));
                                permohonan.setKeputusanOleh(peng);
                                permohonan.setTarikhKeputusan(new java.util.Date());
                                permohonan.setInfoAudit(info);
                                pelupusanService.simpanPermohonan(permohonan);
                            }
                        } else {
                            if (fasaMohon.getIdAliran().equals("25TrmKptsnMMK")) {
                                keputusan = fasaMohon.getKeputusan().getNama();
                            }
                        }
                    }

                } else if (permohonan.getKodUrusan().getKod().equalsIgnoreCase("LPSP")) {
                    System.out.println("LPSP " + fasaMohon.getIdAliran());
                    if (kodNegeri.equals("04")) {
                        //Not implemented
                    } else {
                        if (fasaMohon.getIdAliran().equals("07Sdp")) {
                            //keputusan = fasaMohon.getKeputusan().getNama();
                            if (fasaMohon.getKeputusan().getKod().equals("PL")) {
                                keputusan = "T";
                                InfoAudit info = new InfoAudit();
                                info = permohonan.getInfoAudit();
                                info.setDiKemaskiniOleh(peng);
                                info.setTarikhKemaskini(new java.util.Date());
                                permohonan.setKeputusan(kodKeputusanDAO.findById(keputusan));
                                permohonan.setKeputusanOleh(peng);
                                permohonan.setTarikhKeputusan(new java.util.Date());
                                permohonan.setInfoAudit(info);
                                pelupusanService.simpanPermohonan(permohonan);
                            }
                        } else {
                            if (fasaMohon.getIdAliran().equals("25TrmMklmnKptsn")) {
                                keputusan = fasaMohon.getKeputusan().getNama();
                            }
                        }
                    }

                } else if (permohonan.getKodUrusan().getKod().equalsIgnoreCase("PCRG") || permohonan.getKodUrusan().getKod().equalsIgnoreCase("LPJH")) {
                    if (kodNegeri.equals("04")) {
                        //Not implemented
                    } else {
                        if (fasaMohon.getIdAliran().equals("34Keputusan")) {
                            keputusan = fasaMohon.getKeputusan().getNama();
                        }
                        hakmilikPermohonan = pelupusanService.findHakmilikPermohonan(idPermohonan);
                    }
                } else if (permohonan.getKodUrusan().getKod().equalsIgnoreCase("PRMMK")) {
                    System.out.println("PRMMK " + fasaMohon.getIdAliran());
                    if (kodNegeri.equals("04")) {
                        //Not implemented
                    } else {
                        if (fasaMohon.getIdAliran().equals("07SDP")) {
                            //keputusan = fasaMohon.getKeputusan().getNama();
                            if (fasaMohon.getKeputusan().getKod().equals("PL")) {
                                keputusan = "T";
                                InfoAudit info = new InfoAudit();
                                info = permohonan.getInfoAudit();
                                info.setDiKemaskiniOleh(peng);
                                info.setTarikhKemaskini(new java.util.Date());
                                permohonan.setKeputusan(kodKeputusanDAO.findById(keputusan));
                                permohonan.setKeputusanOleh(peng);
                                permohonan.setTarikhKeputusan(new java.util.Date());
                                permohonan.setInfoAudit(info);
                                pelupusanService.simpanPermohonan(permohonan);
                            }
                        } else {
                            if (fasaMohon.getIdAliran().equals("21TrmKptsnMMK")) {
                                keputusan = fasaMohon.getKeputusan().getNama();
                            }
                        }
                    }

                } else {
                    if (fasaMohon.getIdAliran().equals("semak_borang_4a")) {
                        keputusan = fasaMohon.getKeputusan().getNama();
                    }
                }
            }
        }


        if (permohonan.getKeputusan() != null) {
            if (permohonan.getKeputusan().getKod() != null) {
                LOG.info("permohonan.getKeputusan() : " + permohonan.getKeputusan().getNama());
                if (keputusan != null) {
                    keputusan = permohonan.getKeputusan().getNama();
                    LOG.info("keputusan : " + keputusan);
                }
            }
        }

        String taskId = (String) getContext().getRequest().getSession().getAttribute("taskId");
        BPelService serviceBpel = new BPelService();
        if (StringUtils.isNotBlank(taskId)) {
            Task t = null;
            t = serviceBpel.getTaskFromBPel(taskId, peng);
            stageId = t.getSystemAttributes().getStage();
        }


    }

    public Permohonan getPermohonan() {
        return permohonan;
    }

    public void setPermohonan(Permohonan permohonan) {
        this.permohonan = permohonan;
    }

    public FasaPermohonan getFasaMohon() {
        return fasaMohon;
    }

    public void setFasaMohon(FasaPermohonan fasaMohon) {
        this.fasaMohon = fasaMohon;
    }

    public String getStageId() {
        return stageId;
    }

    public void setStageId(String stageId) {
        this.stageId = stageId;
    }

    public String getKeputusan() {
        return keputusan;
    }

    public void setKeputusan(String keputusan) {
        this.keputusan = keputusan;
    }

    public String getIdPermohonan() {
        return idPermohonan;
    }

    public void setIdPermohonan(String idPermohonan) {
        this.idPermohonan = idPermohonan;
    }

    public Pengguna getPeng() {
        return peng;
    }

    public void setPeng(Pengguna peng) {
        this.peng = peng;
    }

    public String getKodNegeri() {
        return kodNegeri;
    }

    public void setKodNegeri(String kodNegeri) {
        this.kodNegeri = kodNegeri;
    }

    public HakmilikPermohonan getHakmilikPermohonan() {
        return hakmilikPermohonan;
    }

    public void setHakmilikPermohonan(HakmilikPermohonan hakmilikPermohonan) {
        this.hakmilikPermohonan = hakmilikPermohonan;
    }
}
