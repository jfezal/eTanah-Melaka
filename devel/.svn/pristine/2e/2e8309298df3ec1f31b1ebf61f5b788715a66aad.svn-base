/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author nurul.izza Modified By Murali
 */
package etanah.view.stripes.pelupusan;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.dao.KodCawanganDAO;
import etanah.dao.KodItemPermitDAO;
import etanah.dao.KodTransaksiDAO;
import etanah.dao.KodTransaksiTuntutDAO;
import etanah.dao.KodTuntutDAO;
import etanah.dao.PermitDAO;
import etanah.dao.PermohonanDAO;
import etanah.dao.PermohonanTuntutanButiranDAO;
import etanah.model.FasaPermohonan;
import etanah.model.InfoAudit;
import etanah.model.KodCawangan;
import etanah.model.KodItemPermit;
import etanah.model.KodTransaksi;
import etanah.model.KodTransaksiTuntut;
import etanah.model.KodTuntut;
import etanah.service.BPelService;
import oracle.bpel.services.workflow.task.model.Task;
import etanah.model.LupusPermit;
import etanah.model.Pengguna;
import etanah.model.Permit;
import etanah.model.PermitSahLaku;
import etanah.model.Permohonan;
import etanah.model.PermohonanPermitItem;
import etanah.model.PermohonanTuntutan;
import etanah.model.PermohonanTuntutanBayar;
import etanah.model.PermohonanTuntutanButiran;
import etanah.model.PermohonanTuntutanKos;
import etanah.service.EnforceService;
import etanah.service.PelupusanService;
import etanah.service.common.NotisPenerimaanService;
import etanah.service.StrataPtService;
import etanah.view.etanahActionBeanContext;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import net.sourceforge.stripes.action.RedirectResolution;

@UrlBinding("/pelupusan/surat_kelulusan_lps")
public class KeputusanPermohonanActionBean extends AbleActionBean {

    @Inject
    PermohonanDAO permohonanDAO;
    private Permohonan permohonan;
    @Inject
    PelupusanService pelupusanService;
    @Inject
    StrataPtService strService;
    @Inject
    EnforceService enforceService;
    @Inject
    NotisPenerimaanService notisPenerimaanService;
    @Inject
    KodTuntutDAO kodTuntutDAO;
    @Inject
    KodTransaksiDAO kodTransaksiDAO;
    @Inject
    PermohonanTuntutanButiranDAO permohonanTuntutanButiranDAO;
    @Inject
    PermitDAO permitDAO;
    @Inject
    KodCawanganDAO kodCawanganDAO;
    @Inject
    KodTransaksiTuntutDAO kodTransaksiTuntutDAO;
    @Inject
    KodItemPermitDAO kodItemPermitDAO;
    private PermohonanPermitItem ppi;
    private PermohonanTuntutanButiran permohonanTuntutanButiran;
//    private LupusPermit lupusPermit;
    private List<KodItemPermit> senaraiKodItemPermit;
    private Permit permit;
    private PermitSahLaku sahLaku;
    private BigDecimal bayaran;
    private Date tarikhPermitMula;
    private List<PermohonanTuntutanKos> listtuntutankos;
    private PermohonanTuntutanKos permohonantuntutkos;
    private FasaPermohonan fasaPermohonan;
    private BigDecimal amaunTuntutan;
    private String idPermohonan;
    private String stageId;
    private FasaPermohonan mohonFasa;
    private String kodkpsn;
    private static final Logger log = Logger.getLogger(KeputusanPermohonanActionBean.class);

    @DefaultHandler
    public Resolution showForm() {
        return new JSP("pelupusan/surat_keputusan_lps.jsp").addParameter("tab", "true");
    }

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {
        log.info("----------------------rehydrate()--------------");
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");

        log.info("Id Permohonan: " + idPermohonan);
        bayaran = BigDecimal.ZERO;
        String taskId = (String) getContext().getRequest().getSession().getAttribute("taskId");
        Pengguna pguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        stageId = stageId(taskId, pguna);
        if (idPermohonan != null) {
            permohonan = permohonanDAO.findById(idPermohonan);
            if (permohonan.getKodUrusan().getKod().equalsIgnoreCase("PLPTD")) {
                senaraiKodItemPermit = pelupusanService.getSenaraiKodItemPermitPLPTD();
            } else {
                senaraiKodItemPermit = pelupusanService.getSenaraiKodItemPermit();
            }
            ppi = pelupusanService.findByIdMohonPermitItem(idPermohonan);
            mohonFasa = new FasaPermohonan();

            if (permohonan.getKodUrusan().getKod().equalsIgnoreCase("PLPTD")) {
                mohonFasa = pelupusanService.findMohonFasaByIdMohonIdPengguna(permohonan.getIdPermohonan(), "16Kptsn");
            } else if (permohonan.getKodUrusan().getKod().equalsIgnoreCase("PLPS")) {
                
                mohonFasa = pelupusanService.findMohonFasaByIdMohonIdPengguna(permohonan.getIdPermohonan(), "29TrmMklmnKptsn");
                if(mohonFasa == null)
                {
                    mohonFasa = pelupusanService.findMohonFasaByIdMohonIdPengguna(permohonan.getIdPermohonan(), "52Keputusan");
                }
            } else if (permohonan.getKodUrusan().getKod().equalsIgnoreCase("PCRG")) {
                mohonFasa = pelupusanService.findMohonFasaByIdMohonIdPengguna(permohonan.getIdPermohonan(), "34Keputusan");
            } else if (permohonan.getKodUrusan().getKod().equalsIgnoreCase("MPCRG")) {
                mohonFasa = pelupusanService.findMohonFasaByIdMohonIdPengguna(permohonan.getIdPermohonan(), "23MklmnKptsnMMK");
            } else if (permohonan.getKodUrusan().getKod().equals("RLPS")) {
                mohonFasa = pelupusanService.findMohonFasaByIdMohonIdPengguna(permohonan.getIdPermohonan(), "09Kptsn");
            } else if (permohonan.getKodUrusan().getKod().equals("PTPBP")) {
                mohonFasa = pelupusanService.findMohonFasaByIdMohonIdPengguna(permohonan.getIdPermohonan(), "25TrmKptsnMMK");
            }


            if (mohonFasa != null) {
                if (mohonFasa.getKeputusan() != null) {
                    kodkpsn = mohonFasa.getKeputusan().getKod();
                }
            }

            log.info(("----------------------kodkpsn-------------------" + kodkpsn));
            permit = pelupusanService.findPermitByIdPermohonan(idPermohonan);
            log.info(("----------------------permit-------------------" + permit));
            if (permit != null) {
                if (permohonan.getKodUrusan().getKod().equals("RLPS")) {
                    sahLaku = pelupusanService.findPermitSahLakuByIdMohon(idPermohonan);
                } else {
                    sahLaku = pelupusanService.findPermitSahLakuByIdMohonIdPermit(idPermohonan, permit.getIdPermit());
                }
                if (sahLaku != null) {
                    tarikhPermitMula = sahLaku.getTarikhPermitMula();
                }
            }

            listtuntutankos = pelupusanService.findTuntutByIdMohon(idPermohonan);
            log.info(("----------------------listtuntutankos--------------" + listtuntutankos));
            if (!(listtuntutankos.isEmpty())) {
                log.info(("----------------------listtuntutankos not empty--------------------" + listtuntutankos));
                for (int i = 0; i < listtuntutankos.size(); i++) {
                    log.info(("----------------------listtuntutankos----for----------" + listtuntutankos));
                    permohonantuntutkos = new PermohonanTuntutanKos();
                    permohonantuntutkos = listtuntutankos.get(i);
                    if (permohonantuntutkos.getKodTuntut() != null) {
                        log.info(("----------------------listtuntutankos---if-----------" + listtuntutankos));
                        if (permohonantuntutkos.getKodTuntut().getKod().equals("DISB4A")) {
                            log.info(("----------------------listtuntutankos-----if 2---------" + listtuntutankos));
                            bayaran = permohonantuntutkos.getAmaunTuntutan();
                        } else if (permohonantuntutkos.getKodTuntut().getKod().equals("DISCR")) {
                            log.info(("----------------------listtuntutankos-----if 2---------" + listtuntutankos));
                            bayaran = permohonantuntutkos.getAmaunTuntutan();
                        } else if (permohonantuntutkos.getKodTuntut().getKod().equals("DISB7")) {
                            log.info(("----------------------listtuntutankos for PTPBP---------" + listtuntutankos));
                            bayaran = permohonantuntutkos.getAmaunTuntutan();
                        }

                    }
                }
            }
        }
    }

    public Resolution simpanKelulusanRLPS() {

        log.info("-------Simpan Started--------------------");
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");

        String catatan = getContext().getRequest().getParameter("permohonan.catatan");
        if (catatan != null) {
            permohonan = permohonanDAO.findById(idPermohonan);
            pelupusanService.simpanPermohonan(permohonan);
        }
        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        KodCawangan caw = peng.getKodCawangan();
        log.info("-------KodCawangan caw--------------------::" + caw);

        InfoAudit ia = peng.getInfoAudit();
        if (ia == null) {
            ia = new InfoAudit();
            ia.setDimasukOleh(peng);
            ia.setTarikhMasuk(new Date());
        } else {
            ia.setDiKemaskiniOleh(peng);
            ia.setTarikhKemaskini(new java.util.Date());
        }
        String kodPermit = getContext().getRequest().getParameter("kodPermit.kod");
        if (kodPermit.equals("")) {
            addSimpleError("Sila Masukkan Maksud Pendudukan.");
        } else if (tarikhPermitMula == null) {
            addSimpleError("Sila Masukkan Tarikh Mula Lesen.");
        } else if (bayaran == null) {
            addSimpleError("Sila Masukkan Bayaran.");
        } else {

            KodItemPermit kitem = new KodItemPermit();
            permit = new Permit();
            permit = pelupusanService.findPermitByIdPermohonan(idPermohonan);
            kitem.setKod(kodPermit);
            ia = new InfoAudit();
            ia.setDimasukOleh(peng);
            ia.setTarikhMasuk(new Date());

            ppi = pelupusanService.findByIdMohonPermitItem(idPermohonan);
            if (ppi != null) {

                ppi.setPermohonan(permohonan);
                ppi.setInfoAudit(ia);
                ppi.setKodCawangan(peng.getKodCawangan());
                ppi.setKodItemPermit(kitem);
                pelupusanService.saveOrUpdate(ppi);

            } else {
                ppi = new PermohonanPermitItem();
                ppi.setPermohonan(permohonan);
                ppi.setInfoAudit(ia);
                ppi.setKodCawangan(peng.getKodCawangan());
                ppi.setKodItemPermit(kitem);
                pelupusanService.saveOrUpdate(ppi);
            }
            KodItemPermit kitemtemp = new KodItemPermit();
            kitemtemp = kodItemPermitDAO.findById(kodPermit);

            if (permit != null) {
                permit.setPermohonan(permohonan);
                permit.setInfoAudit(ia);
                permit.setKodCawangan(peng.getKodCawangan());
                if (StringUtils.isNotBlank(kodPermit)) {
                    if (kodPermit.equals("LL") || kodPermit.equals("LN")) {
                        permit.setKeterangan(permohonan.getCatatan());
                    } else {
                        permit.setKeterangan(kitemtemp.getNama());
                    }
                }
                pelupusanService.saveOrUpdate(permit);
            } else {
                permit = new Permit();
                permit.setPermohonan(permohonan);
                permit.setInfoAudit(ia);
                permit.setKodCawangan(peng.getKodCawangan());
                if (StringUtils.isNotBlank(kodPermit)) {
                    if (kodPermit.equals("LL") || kodPermit.equals("LN")) {
                        permit.setKeterangan(permohonan.getCatatan());
                    } else {
                        permit.setKeterangan(kitemtemp.getNama());
                    }
                }
                pelupusanService.saveOrUpdate(permit);
            }


            log.info("-------Permit SahLaku Saving--------------------");

            permit = pelupusanService.findPermitByIdPermohonan(idPermohonan);
            log.info("-------permit--------------------" + permit);
            if (permit != null) {
                log.info("-------permit Not Null--------------------");

                log.info("-------sahLaku--------------------" + sahLaku);
                ia.setDiKemaskiniOleh(peng);
                ia.setTarikhKemaskini(new java.util.Date());
                permit.setInfoAudit(ia);
                pelupusanService.saveOrUpdate(permit);
            } else {
                log.info("-------permit is Null--------------------");

                permit = new Permit();
                permit.setPermohonan(permohonan);
                ia.setDimasukOleh(peng);
                ia.setTarikhMasuk(new Date());
                permit.setInfoAudit(ia);
                permit.setKodCawangan(peng.getKodCawangan());
                pelupusanService.saveOrUpdate(permit);
            }
            permit = pelupusanService.findPermitByIdPermohonan(idPermohonan);
            sahLaku = pelupusanService.findPermitSahLakuByIdMohon(idPermohonan);

            if (sahLaku != null) {
                log.info("-------sahLaku Not Null---------------::");
                ia.setDiKemaskiniOleh(peng);
                ia.setTarikhKemaskini(new java.util.Date());
                sahLaku.setInfoAudit(ia);
                sahLaku.setKodCawangan(peng.getKodCawangan());
                if (tarikhPermitMula != null) {
                    sahLaku.setTarikhPermitMula(tarikhPermitMula);
                }

            } else {
                log.info("-------sahLaku is Null--------::");
                //String tarikhMula = getContext().getRequest().getParameter("sahLaku.tarikhPermitMula");
                sahLaku = new PermitSahLaku();
                sahLaku.setPermit(permit);
                sahLaku.setPermohonan(permohonan);
                ia.setDimasukOleh(peng);
                ia.setTarikhMasuk(new Date());
                sahLaku.setInfoAudit(ia);
                sahLaku.setKodCawangan(peng.getKodCawangan());
                if (tarikhPermitMula != null) {
                    sahLaku.setTarikhPermitMula(tarikhPermitMula);
                }
            }
            pelupusanService.simpanPermitSahLaku(sahLaku);

            listtuntutankos = pelupusanService.findTuntutByIdMohon(idPermohonan);
            if (bayaran == null) {
                bayaran = new BigDecimal(0.00);
            }

            log.info("-------Mohon Tuntutukos List Updating--------::");

            KodTransaksi kt = new KodTransaksi();
            kt.setKod("12101");
            if (!(listtuntutankos.isEmpty())) {
                log.info("-------permohonantuntutkos--Not Empty--------::" + permohonantuntutkos);
                for (int i = 0; i < listtuntutankos.size(); i++) {
                    log.info("-------listtuntutankos.size()----------" + listtuntutankos.size());
                    permohonantuntutkos = new PermohonanTuntutanKos();
                    permohonantuntutkos = listtuntutankos.get(i);
                    if (permohonantuntutkos.getKodTuntut() != null) {
                        log.info("-------permohonantuntutkos-----getKodTuntut() Not Null-------------" + permohonantuntutkos);
                        if (permohonantuntutkos.getKodTuntut().getKod().equals("DISB4A")) {
                            permohonantuntutkos.setAmaunTuntutan(bayaran);
                            permohonantuntutkos.setKodTransaksi(kt);
                        }
                        ia = permohonantuntutkos.getInfoAudit();
                        ia.setTarikhKemaskini(new java.util.Date());
                        ia.setDiKemaskiniOleh(peng);
                        permohonantuntutkos.setInfoAudit(ia);
                        permohonantuntutkos.setPermohonan(permohonan);
                        permohonantuntutkos.setCawangan(peng.getKodCawangan());
                        pelupusanService.simpanPermohonanTuntutanKos1(permohonantuntutkos);

                    }
                }
            } else {
                if (permohonan.getKodUrusan().getKod().equalsIgnoreCase("PLPTD") || permohonan.getKodUrusan().getKod().equalsIgnoreCase("PLPS") || permohonan.getKodUrusan().getKod().equalsIgnoreCase("RLPS") || permohonan.getKodUrusan().getKod().equalsIgnoreCase("RLPSK") || permohonan.getKodUrusan().getKod().equalsIgnoreCase("PTPBP")) {
                    permohonantuntutkos = new PermohonanTuntutanKos();
                    ia = new InfoAudit();
                    ia.setDimasukOleh(peng);
                    ia.setTarikhMasuk(new java.util.Date());
                    permohonantuntutkos.setPermohonan(permohonan);
                    permohonantuntutkos.setAmaunTuntutan(bayaran);
                    permohonantuntutkos.setKodTransaksi(kt);
                    permohonantuntutkos.setInfoAudit(ia);
                    permohonantuntutkos.setCawangan(peng.getKodCawangan());
                    permohonantuntutkos.setKodTuntut(kodTuntutDAO.findById("DISB4A"));
                    permohonantuntutkos.setItem(kodTuntutDAO.findById("DISB4A").getNama());
                    pelupusanService.simpanPermohonanTuntutanKos1(permohonantuntutkos);
                }

            }



            // Added new Code
            PermohonanTuntutan permohonanTuntutan;
//             permohonanTuntutan = pelupusanService.findPermohonanTuntutanByKodTransName(idPermohonan, "Bayaran Borang 4A (LPS)");
            permohonanTuntutan = pelupusanService.findPermohonanTuntutanByKodTransName(idPermohonan, "DIS4A");

            if (permohonanTuntutan != null) {
                log.info("-------Mohonan Tuntutan Not Null-------------------");
                ia = permohonanTuntutan.getInfoAudit();
                ia.setTarikhKemaskini(new java.util.Date());
                ia.setDiKemaskiniOleh(peng);
                permohonanTuntutan.setInfoAudit(ia);
                pelupusanService.simpanPermohonanTuntutan(permohonanTuntutan);
            } else {

                log.info("-------Mohonan Tuntutan is Null-------------------");

                Calendar c = Calendar.getInstance();
                log.info("-------Todays Date::-------------------" + c);
                Date date = new Date();
                c.add(Calendar.MONTH, 3);
                log.info("-------After 3 months Date::-------------------" + c);
                date = c.getTime();
                log.info("-------After 3 months Date::-----date--------------" + date);
//            String itemList = "DISB4A";
//            BigDecimal amaunTuntutanList = bayaran;


//            kodTransTuntut = pelupusanService.findKodTransTuntutByName("DIS4A");
                KodCawangan test = kodCawanganDAO.findById(peng.getKodCawangan().getKod());
                permohonanTuntutan = new PermohonanTuntutan();
//            permohonanTuntutan.setCawangan(test);
                permohonanTuntutan.setCawangan(caw);
                permohonanTuntutan.setPermohonan(permohonan);
                permohonanTuntutan.setInfoAudit(ia);

                if (permohonan.getKodUrusan().getKod().equalsIgnoreCase("PCRG") || permohonan.getKodUrusan().getKod().equalsIgnoreCase("MPCRG")) {
                    KodTransaksiTuntut kodTransTuntut = kodTransaksiTuntutDAO.findById("DISCR");
                    permohonanTuntutan.setKodTransaksiTuntut(kodTransTuntut);
                } else {
                    KodTransaksiTuntut kodTransTuntut = kodTransaksiTuntutDAO.findById("DIS4A");
                    permohonanTuntutan.setKodTransaksiTuntut(kodTransTuntut);
                }
                permohonanTuntutan.setTarikhTuntutan(new java.util.Date());
                permohonanTuntutan.setTarikhAkhirBayaran(date);
                pelupusanService.simpanPermohonanTuntutan(permohonanTuntutan);
            }


            // added new code

            log.info("-------Mohonantuntan Butiran Saving--------------------");
            permohonanTuntutanButiran = pelupusanService.findPermohonanTuntutButirByIdTuntutAndIdKos(permohonantuntutkos.getIdKos(), permohonanTuntutan.getIdTuntut());
            if (permohonanTuntutanButiran != null) {

                ia.setDiKemaskiniOleh(peng);
                ia.setTarikhKemaskini(new java.util.Date());
                permohonanTuntutanButiran.setInfoAudit(ia);

            } else {
                PermohonanTuntutanButiran permohonanTuntutanButiran = new PermohonanTuntutanButiran();
                permohonanTuntutanButiran.setPermohonanTuntutan(permohonanTuntutan);
                permohonanTuntutanButiran.setPermohonanTuntutanKos(permohonantuntutkos);
                permohonanTuntutanButiran.setInfoAudit(ia);
                pelupusanService.simpanPermohonanTuntutanButiran(permohonanTuntutanButiran);
            }
            addSimpleMessage("Maklumat telah berjaya disimpan.");
        }



        return new JSP("pelupusan/surat_keputusan_lps.jsp").addParameter("tab", "true");

    }

    public Resolution simpanKelulusan() {

        log.info("-------Simpan Started--------------------");
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");

        String catatan = getContext().getRequest().getParameter("permohonan.catatan");
        if (catatan != null) {
            permohonan = permohonanDAO.findById(idPermohonan);
            pelupusanService.simpanPermohonan(permohonan);
        }
        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        KodCawangan caw = peng.getKodCawangan();
        log.info("-------KodCawangan caw--------------------::" + caw);

        InfoAudit ia = peng.getInfoAudit();
        if (ia == null) {
            ia = new InfoAudit();
            ia.setDimasukOleh(peng);
            ia.setTarikhMasuk(new Date());
        } else {
            ia.setDiKemaskiniOleh(peng);
            ia.setTarikhKemaskini(new java.util.Date());
        }

        if (permohonan.getKodUrusan().getKod().equalsIgnoreCase("PLPS") || permohonan.getKodUrusan().getKod().equalsIgnoreCase("RLPS") || permohonan.getKodUrusan().getKod().equalsIgnoreCase("PLPTD") || permohonan.getKodUrusan().getKod().equalsIgnoreCase("RLPSK") || permohonan.getKodUrusan().getKod().equalsIgnoreCase("PTPBP")) {
            String kodPermit = getContext().getRequest().getParameter("kodPermit.kod");
            KodItemPermit kitem = new KodItemPermit();
            permit = new Permit();
            permit = pelupusanService.findPermitByIdPermohonan(idPermohonan);
            kitem.setKod(kodPermit);
            ia = new InfoAudit();
            ia.setDimasukOleh(peng);
            ia.setTarikhMasuk(new Date());
            if (permohonan.getKodUrusan().getKod().equals("RLPS")) {
                if (kodPermit.equals("")) {
                    addSimpleError("Sila Masukkan Maksud Pendudukan.");
                    return new JSP("pelupusan/surat_keputusan_lps.jsp").addParameter("tab", "true");
                }
                if (tarikhPermitMula == null) {
                    addSimpleError("Sila Masukkan Tarikh Mula Lesen.");
                    return new JSP("pelupusan/surat_keputusan_lps.jsp").addParameter("tab", "true");
                }
                if (bayaran.equals("")) {
                    addSimpleError("Sila Masukkan Bayaran.");
                    return new JSP("pelupusan/surat_keputusan_lps.jsp").addParameter("tab", "true");
                }

            }
            ppi = pelupusanService.findByIdMohonPermitItem(idPermohonan);
            if (ppi != null) {

                ppi.setPermohonan(permohonan);
                ppi.setInfoAudit(ia);
                ppi.setKodCawangan(peng.getKodCawangan());
                ppi.setKodItemPermit(kitem);
                pelupusanService.saveOrUpdate(ppi);

            } else {
                ppi = new PermohonanPermitItem();
                ppi.setPermohonan(permohonan);
                ppi.setInfoAudit(ia);
                ppi.setKodCawangan(peng.getKodCawangan());
                ppi.setKodItemPermit(kitem);
                pelupusanService.saveOrUpdate(ppi);
            }

            KodItemPermit kitemtemp = new KodItemPermit();
            kitemtemp = kodItemPermitDAO.findById(kodPermit);

            if (permit != null) {
                permit.setPermohonan(permohonan);
                permit.setInfoAudit(ia);
                permit.setKodCawangan(peng.getKodCawangan());
                if (StringUtils.isNotBlank(kodPermit)) {
                    if (kodPermit.equals("LL") || kodPermit.equals("LN")) {
                        permit.setKeterangan(permohonan.getCatatan());
                    } else {
                        permit.setKeterangan(kitemtemp.getNama());
                    }
                }
                pelupusanService.saveOrUpdate(permit);
            } else {
                permit = new Permit();
                permit.setPermohonan(permohonan);
                permit.setInfoAudit(ia);
                permit.setKodCawangan(peng.getKodCawangan());
                if (StringUtils.isNotBlank(kodPermit)) {
                    if (kodPermit.equals("LL") || kodPermit.equals("LN")) {
                        permit.setKeterangan(permohonan.getCatatan());
                    } else {
                        permit.setKeterangan(kitemtemp.getNama());
                    }
                }
                pelupusanService.saveOrUpdate(permit);
            }




            log.info("-------Permit SahLaku Saving--------------------");

            permit = pelupusanService.findPermitByIdPermohonan(idPermohonan);
            log.info("-------permit--------------------" + permit);
            if (permit != null) {
                log.info("-------permit Not Null--------------------");

                log.info("-------sahLaku--------------------" + sahLaku);
                ia.setDiKemaskiniOleh(peng);
                ia.setTarikhKemaskini(new java.util.Date());
                permit.setInfoAudit(ia);
                pelupusanService.saveOrUpdate(permit);
            } else {
                log.info("-------permit is Null--------------------");

                permit = new Permit();
                permit.setPermohonan(permohonan);
                ia.setDimasukOleh(peng);
                ia.setTarikhMasuk(new Date());
                permit.setInfoAudit(ia);
                permit.setKodCawangan(peng.getKodCawangan());
                permitDAO.save(permit);
            }
            permit = pelupusanService.findPermitByIdPermohonan(idPermohonan);
            sahLaku = pelupusanService.findPermitSahLakuByIdMohonIdPermit(idPermohonan, permit.getIdPermit());

            if (sahLaku != null) {
                log.info("-------sahLaku Not Null---------------::");
                ia.setDiKemaskiniOleh(peng);
                ia.setTarikhKemaskini(new java.util.Date());
                sahLaku.setInfoAudit(ia);
                sahLaku.setKodCawangan(peng.getKodCawangan());
                if (tarikhPermitMula != null) {
                    sahLaku.setTarikhPermitMula(tarikhPermitMula);
                }

            } else {
                log.info("-------sahLaku is Null--------::");
                //String tarikhMula = getContext().getRequest().getParameter("sahLaku.tarikhPermitMula");
                sahLaku = new PermitSahLaku();
                ia = new InfoAudit();
                ia.setDimasukOleh(peng);
                ia.setTarikhMasuk(new Date());
                sahLaku.setInfoAudit(ia);
                sahLaku.setPermit(permit);
                sahLaku.setPermohonan(permohonan);
                sahLaku.setKodCawangan(peng.getKodCawangan());
                sahLaku.setTarikhPermitMula(tarikhPermitMula);
//                if (tarikhPermitMula != null) {
//                    sahLaku.setTarikhPermitMula(tarikhPermitMula);
//                }
            }
            pelupusanService.simpanPermitSahLaku(sahLaku);

            listtuntutankos = pelupusanService.findTuntutByIdMohon(idPermohonan);
            if (bayaran == null) {
                bayaran = new BigDecimal(0.00);
            }

            log.info("-------Mohon Tuntutukos List Updating--------::");

            KodTransaksi kt = new KodTransaksi();
            kt.setKod("12101");
            if (!(listtuntutankos.isEmpty())) {
                log.info("-------permohonantuntutkos--Not Empty--------::" + permohonantuntutkos);
                for (int i = 0; i < listtuntutankos.size(); i++) {
                    log.info("-------listtuntutankos.size()----------" + listtuntutankos.size());
                    permohonantuntutkos = new PermohonanTuntutanKos();
                    permohonantuntutkos = listtuntutankos.get(i);
                    if (permohonantuntutkos.getKodTuntut() != null) {
                        log.info("-------permohonantuntutkos-----getKodTuntut() Not Null-------------" + permohonantuntutkos);
                        if (permohonantuntutkos.getKodTuntut().getKod().equals("DISB4A")) {
                            permohonantuntutkos.setAmaunTuntutan(bayaran);
                            permohonantuntutkos.setKodTransaksi(kt);
                        }
                        ia = permohonantuntutkos.getInfoAudit();
                        ia.setTarikhKemaskini(new java.util.Date());
                        ia.setDiKemaskiniOleh(peng);
                        permohonantuntutkos.setInfoAudit(ia);
                        permohonantuntutkos.setPermohonan(permohonan);
                        permohonantuntutkos.setCawangan(peng.getKodCawangan());
                        pelupusanService.simpanPermohonanTuntutanKos1(permohonantuntutkos);

                    }
                }
            } else {
                if (permohonan.getKodUrusan().getKod().equalsIgnoreCase("PLPTD") || permohonan.getKodUrusan().getKod().equalsIgnoreCase("PLPS") || permohonan.getKodUrusan().getKod().equalsIgnoreCase("RLPS") || permohonan.getKodUrusan().getKod().equalsIgnoreCase("RLPSK") || permohonan.getKodUrusan().getKod().equalsIgnoreCase("PTPBP")) {
                    permohonantuntutkos = new PermohonanTuntutanKos();
                    ia = new InfoAudit();
                    ia.setDimasukOleh(peng);
                    ia.setTarikhMasuk(new java.util.Date());
                    permohonantuntutkos.setPermohonan(permohonan);
                    permohonantuntutkos.setAmaunTuntutan(bayaran);
                    permohonantuntutkos.setKodTransaksi(kt);
                    permohonantuntutkos.setInfoAudit(ia);
                    permohonantuntutkos.setCawangan(peng.getKodCawangan());
                    permohonantuntutkos.setKodTuntut(kodTuntutDAO.findById("DISB4A"));
                    permohonantuntutkos.setItem(kodTuntutDAO.findById("DISB4A").getNama());
                    pelupusanService.simpanPermohonanTuntutanKos1(permohonantuntutkos);
                }

            }



            // Added new Code
            PermohonanTuntutan permohonanTuntutan;
//             permohonanTuntutan = pelupusanService.findPermohonanTuntutanByKodTransName(idPermohonan, "Bayaran Borang 4A (LPS)");
            permohonanTuntutan = pelupusanService.findPermohonanTuntutanByKodTransName(idPermohonan, "DIS4A");

            if (permohonanTuntutan != null) {
                log.info("-------Mohonan Tuntutan Not Null-------------------");
                ia = permohonanTuntutan.getInfoAudit();
                ia.setTarikhKemaskini(new java.util.Date());
                ia.setDiKemaskiniOleh(peng);
                permohonanTuntutan.setInfoAudit(ia);
                pelupusanService.simpanPermohonanTuntutan(permohonanTuntutan);
            } else {

                log.info("-------Mohonan Tuntutan is Null-------------------");

                Calendar c = Calendar.getInstance();
                log.info("-------Todays Date::-------------------" + c);
                Date date = new Date();
                c.add(Calendar.MONTH, 3);
                log.info("-------After 3 months Date::-------------------" + c);
                date = c.getTime();
                log.info("-------After 3 months Date::-----date--------------" + date);
//            String itemList = "DISB4A";
//            BigDecimal amaunTuntutanList = bayaran;


//            kodTransTuntut = pelupusanService.findKodTransTuntutByName("DIS4A");
                KodCawangan test = kodCawanganDAO.findById(peng.getKodCawangan().getKod());
                permohonanTuntutan = new PermohonanTuntutan();
//            permohonanTuntutan.setCawangan(test);
                permohonanTuntutan.setCawangan(caw);
                permohonanTuntutan.setPermohonan(permohonan);
                permohonanTuntutan.setInfoAudit(ia);

                if (permohonan.getKodUrusan().getKod().equalsIgnoreCase("PCRG") || permohonan.getKodUrusan().getKod().equalsIgnoreCase("MPCRG")) {
                    KodTransaksiTuntut kodTransTuntut = kodTransaksiTuntutDAO.findById("DISCR");
                    permohonanTuntutan.setKodTransaksiTuntut(kodTransTuntut);
                } else {
                    KodTransaksiTuntut kodTransTuntut = kodTransaksiTuntutDAO.findById("DIS4A");
                    permohonanTuntutan.setKodTransaksiTuntut(kodTransTuntut);
                }
                permohonanTuntutan.setTarikhTuntutan(new java.util.Date());
                permohonanTuntutan.setTarikhAkhirBayaran(date);
                pelupusanService.simpanPermohonanTuntutan(permohonanTuntutan);
            }


            // added new code

            log.info("-------Mohonantuntan Butiran Saving--------------------");
            permohonanTuntutanButiran = pelupusanService.findPermohonanTuntutButirByIdTuntutAndIdKos(permohonantuntutkos.getIdKos(), permohonanTuntutan.getIdTuntut());
            if (permohonanTuntutanButiran != null) {

                ia.setDiKemaskiniOleh(peng);
                ia.setTarikhKemaskini(new java.util.Date());
                permohonanTuntutanButiran.setInfoAudit(ia);

            } else {
                PermohonanTuntutanButiran permohonanTuntutanButiran = new PermohonanTuntutanButiran();
                permohonanTuntutanButiran.setPermohonanTuntutan(permohonanTuntutan);
                permohonanTuntutanButiran.setPermohonanTuntutanKos(permohonantuntutkos);
                permohonanTuntutanButiran.setInfoAudit(ia);
                pelupusanService.simpanPermohonanTuntutanButiran(permohonanTuntutanButiran);
            }
            refreshpage();
            addSimpleMessage("Maklumat telah berjaya disimpan.");
        }


        //  To save for PCRG
        if (permohonan.getKodUrusan().getKod().equalsIgnoreCase("PCRG") || permohonan.getKodUrusan().getKod().equalsIgnoreCase("MPCRG")) {
            String kodPermit = "MN";
            KodItemPermit kitem = new KodItemPermit();
            permit = new Permit();
            permit = pelupusanService.findPermitByIdPermohonan(idPermohonan);
            kitem.setKod(kodPermit);
            ia = new InfoAudit();
            ia.setDimasukOleh(peng);
            ia.setTarikhMasuk(new Date());
            ppi = pelupusanService.findByIdMohonPermitItem(idPermohonan);
            if (ppi != null) {

                ppi.setPermohonan(permohonan);
                ppi.setInfoAudit(ia);
                ppi.setKodCawangan(peng.getKodCawangan());
                ppi.setKodItemPermit(kitem);
                pelupusanService.saveOrUpdate(ppi);

            } else {
                ppi = new PermohonanPermitItem();
                ppi.setPermohonan(permohonan);
                ppi.setInfoAudit(ia);
                ppi.setKodCawangan(peng.getKodCawangan());
                ppi.setKodItemPermit(kitem);
                pelupusanService.saveOrUpdate(ppi);
            }
            KodItemPermit kitemtemp = new KodItemPermit();
            kitemtemp = kodItemPermitDAO.findById(kodPermit);

            if (permit != null) {
                permit.setPermohonan(permohonan);
                permit.setInfoAudit(ia);
                permit.setKodCawangan(peng.getKodCawangan());
                if (StringUtils.isNotBlank(kodPermit)) {
                    if (kodPermit.equals("LL") || kodPermit.equals("LN")) {
                        permit.setKeterangan(permohonan.getCatatan());
                    } else {
                        permit.setKeterangan(kitemtemp.getNama());
                    }
                }
                pelupusanService.saveOrUpdate(permit);
            } else {
                permit = new Permit();
                permit.setPermohonan(permohonan);
                permit.setInfoAudit(ia);
                permit.setKodCawangan(peng.getKodCawangan());
                if (StringUtils.isNotBlank(kodPermit)) {
                    if (kodPermit.equals("LL") || kodPermit.equals("LN")) {
                        permit.setKeterangan(permohonan.getCatatan());
                    } else {
                        permit.setKeterangan(kitemtemp.getNama());
                    }
                }
                pelupusanService.saveOrUpdate(permit);
            }


            log.info("-------Permit SahLaku Saving--------------------");

            permit = pelupusanService.findPermitByIdPermohonan(idPermohonan);
            log.info("-------permit--------------------" + permit);
            if (permit != null) {
                log.info("-------permit Not Null--------------------");

                log.info("-------sahLaku--------------------" + sahLaku);
                ia.setDiKemaskiniOleh(peng);
                ia.setTarikhKemaskini(new java.util.Date());
                permit.setInfoAudit(ia);
                pelupusanService.saveOrUpdate(permit);
            } else {
                log.info("-------permit is Null--------------------");

                permit = new Permit();
                permit.setPermohonan(permohonan);
                ia.setDimasukOleh(peng);
                ia.setTarikhMasuk(new Date());
                permit.setInfoAudit(ia);
                permit.setKodCawangan(peng.getKodCawangan());
                permitDAO.save(permit);
            }
            permit = pelupusanService.findPermitByIdPermohonan(idPermohonan);
            sahLaku = pelupusanService.findPermitSahLakuByIdMohonIdPermit(idPermohonan, permit.getIdPermit());

            if (sahLaku != null) {
                log.info("-------sahLaku Not Null---------------::");
                ia.setDiKemaskiniOleh(peng);
                ia.setTarikhKemaskini(new java.util.Date());
                sahLaku.setInfoAudit(ia);
                sahLaku.setKodCawangan(peng.getKodCawangan());

            } else {
                log.info("-------sahLaku is Null--------::");
                //String tarikhMula = getContext().getRequest().getParameter("sahLaku.tarikhPermitMula");
                sahLaku = new PermitSahLaku();
                sahLaku.setPermit(permit);
                sahLaku.setPermohonan(permohonan);
                ia.setDimasukOleh(peng);
                ia.setTarikhMasuk(new Date());
                sahLaku.setInfoAudit(ia);
                sahLaku.setKodCawangan(peng.getKodCawangan());
                if (tarikhPermitMula != null) {
                    sahLaku.setTarikhPermitMula(tarikhPermitMula);
                }
            }
            pelupusanService.simpanPermitSahLaku(sahLaku);

            listtuntutankos = pelupusanService.findTuntutByIdMohon(idPermohonan);
            if (bayaran == null) {
                bayaran = new BigDecimal(0.00);
            }

            log.info("-------Mohon Tuntutukos List Updating--------::");

            KodTransaksi kt = new KodTransaksi();
            kt.setKod("71803");
            if (!(listtuntutankos.isEmpty())) {
                log.info("-------permohonantuntutkos--Not Empty--------::" + permohonantuntutkos);
                for (int i = 0; i < listtuntutankos.size(); i++) {
                    log.info("-------listtuntutankos.size()----------" + listtuntutankos.size());
                    permohonantuntutkos = new PermohonanTuntutanKos();
                    permohonantuntutkos = listtuntutankos.get(i);
                    if (permohonantuntutkos.getKodTuntut() != null) {
                        log.info("-------permohonantuntutkos-----getKodTuntut() Not Null-------------" + permohonantuntutkos);
                        if (permohonantuntutkos.getKodTuntut().getKod().equals("DISCR")) {
                            permohonantuntutkos.setAmaunTuntutan(bayaran);
                            permohonantuntutkos.setKodTransaksi(kt);
                        }
                        ia = permohonantuntutkos.getInfoAudit();
                        ia.setTarikhKemaskini(new java.util.Date());
                        ia.setDiKemaskiniOleh(peng);
                        permohonantuntutkos.setInfoAudit(ia);
                        permohonantuntutkos.setPermohonan(permohonan);
                        permohonantuntutkos.setCawangan(peng.getKodCawangan());
                        pelupusanService.simpanPermohonanTuntutanKos1(permohonantuntutkos);

                    }
                }
            } else {
                if (permohonan.getKodUrusan().getKod().equalsIgnoreCase("PCRG") || permohonan.getKodUrusan().getKod().equalsIgnoreCase("MPCRG")) {
                    permohonantuntutkos = new PermohonanTuntutanKos();
                    ia = new InfoAudit();
                    ia.setDimasukOleh(peng);
                    ia.setTarikhMasuk(new java.util.Date());
                    permohonantuntutkos.setPermohonan(permohonan);
                    permohonantuntutkos.setAmaunTuntutan(bayaran);
                    permohonantuntutkos.setKodTransaksi(kt);
                    permohonantuntutkos.setInfoAudit(ia);
                    permohonantuntutkos.setCawangan(peng.getKodCawangan());
                    permohonantuntutkos.setKodTuntut(kodTuntutDAO.findById("DISCR"));
                    permohonantuntutkos.setItem(kodTuntutDAO.findById("DISCR").getNama());
                    pelupusanService.simpanPermohonanTuntutanKos1(permohonantuntutkos);
                }
            }
            // Added new Code
            PermohonanTuntutan permohonanTuntutan;
            permohonanTuntutan = pelupusanService.findPermohonanTuntutanByKodTransName(idPermohonan, "DISCR");

            if (permohonanTuntutan != null) {
                log.info("-------Mohonan Tuntutan Not Null-------------------");
                ia = permohonanTuntutan.getInfoAudit();
                ia.setTarikhKemaskini(new java.util.Date());
                ia.setDiKemaskiniOleh(peng);
                permohonanTuntutan.setInfoAudit(ia);
                pelupusanService.simpanPermohonanTuntutan(permohonanTuntutan);
            } else {

                log.info("-------Mohonan Tuntutan is Null-------------------");

                Calendar c = Calendar.getInstance();
                log.info("-------Todays Date::-------------------" + c);
                Date date = new Date();
                c.add(Calendar.MONTH, 3);
                log.info("-------After 3 months Date::-------------------" + c);
                date = c.getTime();
                log.info("-------After 3 months Date::-----date--------------" + date);
//            String itemList = "DISB4A";
//            BigDecimal amaunTuntutanList = bayaran;


//            kodTransTuntut = pelupusanService.findKodTransTuntutByName("DIS4A");
                KodCawangan test = kodCawanganDAO.findById(peng.getKodCawangan().getKod());
                permohonanTuntutan = new PermohonanTuntutan();
//            permohonanTuntutan.setCawangan(test);
                permohonanTuntutan.setCawangan(caw);
                permohonanTuntutan.setPermohonan(permohonan);
                permohonanTuntutan.setInfoAudit(ia);

                if (permohonan.getKodUrusan().getKod().equalsIgnoreCase("PCRG") || permohonan.getKodUrusan().getKod().equalsIgnoreCase("MPCRG")) {
                    KodTransaksiTuntut kodTransTuntut = kodTransaksiTuntutDAO.findById("DISCR");
                    permohonanTuntutan.setKodTransaksiTuntut(kodTransTuntut);
                }
                permohonanTuntutan.setTarikhTuntutan(new java.util.Date());
                permohonanTuntutan.setTarikhAkhirBayaran(date);
                pelupusanService.simpanPermohonanTuntutan(permohonanTuntutan);
            }


            // added new code

            log.info("-------Mohonantuntan Butiran Saving--------------------");
            permohonanTuntutanButiran = pelupusanService.findPermohonanTuntutButirByIdTuntutAndIdKos(permohonantuntutkos.getIdKos(), permohonanTuntutan.getIdTuntut());
            if (permohonanTuntutanButiran != null) {

                ia.setDiKemaskiniOleh(peng);
                ia.setTarikhKemaskini(new java.util.Date());
                permohonanTuntutanButiran.setInfoAudit(ia);

            } else {
                PermohonanTuntutanButiran permohonanTuntutanButiran = new PermohonanTuntutanButiran();
                permohonanTuntutanButiran.setPermohonanTuntutan(permohonanTuntutan);
                permohonanTuntutanButiran.setPermohonanTuntutanKos(permohonantuntutkos);
                permohonanTuntutanButiran.setInfoAudit(ia);
                pelupusanService.simpanPermohonanTuntutanButiran(permohonanTuntutanButiran);
            }
            addSimpleMessage("Maklumat telah berjaya disimpan.");
        }
        return new JSP("pelupusan/surat_keputusan_lps.jsp").addParameter("tab", "true");

    }

    public Resolution refreshpage() {
        rehydrate();
        return new RedirectResolution(KeputusanPermohonanActionBean.class, "locate");
    }

    public Permohonan getPermohonan() {
        return permohonan;
    }

    public void setPermohonan(Permohonan permohonan) {
        this.permohonan = permohonan;
    }

    public PermohonanPermitItem getPpi() {
        return ppi;
    }

    public void setPpi(PermohonanPermitItem ppi) {
        this.ppi = ppi;
    }

    public Permit getPermit() {
        return permit;
    }

    public void setPermit(Permit permit) {
        this.permit = permit;
    }

    public List<KodItemPermit> getSenaraiKodItemPermit() {
        return senaraiKodItemPermit;
    }

    public void setSenaraiKodItemPermit(List<KodItemPermit> senaraiKodItemPermit) {
        this.senaraiKodItemPermit = senaraiKodItemPermit;
    }

    public BigDecimal getBayaran() {
        return bayaran;
    }

    public void setBayaran(BigDecimal bayaran) {
        this.bayaran = bayaran;
    }

    public PermitSahLaku getSahLaku() {
        return sahLaku;
    }

    public void setSahLaku(PermitSahLaku sahLaku) {
        this.sahLaku = sahLaku;
    }

    public Date getTarikhPermitMula() {
        return tarikhPermitMula;
    }

    public void setTarikhPermitMula(Date tarikhPermitMula) {
        this.tarikhPermitMula = tarikhPermitMula;
    }

    public List<PermohonanTuntutanKos> getListtuntutankos() {
        return listtuntutankos;
    }

    public void setListtuntutankos(List<PermohonanTuntutanKos> listtuntutankos) {
        this.listtuntutankos = listtuntutankos;
    }

    public PermohonanTuntutanKos getPermohonantuntutkos() {
        return permohonantuntutkos;
    }

    public void setPermohonantuntutkos(PermohonanTuntutanKos permohonantuntutkos) {
        this.permohonantuntutkos = permohonantuntutkos;
    }

    public BigDecimal getAmaunTuntutan() {
        return amaunTuntutan;
    }

    public void setAmaunTuntutan(BigDecimal amaunTuntutan) {
        this.amaunTuntutan = amaunTuntutan;
    }

    public PermohonanTuntutanButiran getPermohonanTuntutanButiran() {
        return permohonanTuntutanButiran;
    }

    public void setPermohonanTuntutanButiran(PermohonanTuntutanButiran permohonanTuntutanButiran) {
        this.permohonanTuntutanButiran = permohonanTuntutanButiran;
    }

    public String getIdPermohonan() {
        return idPermohonan;
    }

    public void setIdPermohonan(String idPermohonan) {
        this.idPermohonan = idPermohonan;
    }

    public String stageId(String taskId, Pengguna pguna) {
        BPelService service = new BPelService();
        String stageId = null;
        if (StringUtils.isNotBlank(taskId)) {
            Task t = null;
            t = service.getTaskFromBPel(taskId, pguna);
            stageId = t.getSystemAttributes().getStage();
        } else {
            stageId = "32SedSrtLulus";
        }
        return stageId;
    }

    public String getStageId() {
        return stageId;
    }

    public void setStageId(String stageId) {
        this.stageId = stageId;
    }

    public String getKodkpsn() {
        return kodkpsn;
    }

    public void setKodkpsn(String kodkpsn) {
        this.kodkpsn = kodkpsn;
    }

    public FasaPermohonan getFasaPermohonan() {
        return fasaPermohonan;
    }

    public void setFasaPermohonan(FasaPermohonan fasaPermohonan) {
        this.fasaPermohonan = fasaPermohonan;
    }
}
