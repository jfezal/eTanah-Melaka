/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.stripes.pembangunan;

/**
 *
 * @author NageswaraRao
 */
import java.text.ParseException;
import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.dao.HakmilikPermohonanDAO;
import etanah.dao.KodKeputusanDAO;
import etanah.dao.KodTransaksiTuntutDAO;
import etanah.dao.KodTuntutDAO;
import etanah.dao.PermohonanDAO;
import etanah.model.HakmilikPermohonan;
import etanah.model.InfoAudit;
import etanah.model.KodCawangan;
import etanah.model.KodTransaksiTuntut;
import etanah.model.KodTuntut;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.model.PermohonanTuntutanKos;
import etanah.model.LanjutanBayaran;
import etanah.model.PermohonanTuntutan;
import etanah.model.PermohonanTuntutanButiran;
import etanah.service.PelupusanService;
import etanah.view.etanahActionBeanContext;
import java.math.BigDecimal;
import java.util.Date;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;
import org.apache.log4j.Logger;
import oracle.bpel.services.workflow.task.model.Task;
import etanah.service.BPelService;
import etanah.service.PembangunanService;
import java.util.List;
import org.apache.commons.lang.StringUtils;

@UrlBinding("/pembangunan/rayuan/keputusanRPP")
public class KeputusanRPPActionBean extends AbleActionBean {

    private static Logger logger = Logger.getLogger(KeputusanRPPActionBean.class);
    @Inject
    KodKeputusanDAO kodKeputusanDAO;
    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    HakmilikPermohonanDAO hakmilikPermohonanDAO;
    @Inject
    KodTransaksiTuntutDAO kodTransaksiTuntutDAO;
    @Inject
    KodTuntutDAO kodTuntutDAO;
    @Inject
    PelupusanService pelupusanService;
    @Inject
    PembangunanService devService;
    private String idPermohonan;
    private Pengguna pengguna;
    private Permohonan permohonan;
    private Permohonan permohonanSebelum;
    private HakmilikPermohonan hakmilikPermohonan;
    private BigDecimal kadarPremium;
    private BigDecimal cukaiPerMeterPersegi;
    private BigDecimal premiumBaru;
    private Integer tempoh;
    private String sebab;
    private PermohonanTuntutanKos permohonanTuntutanKos;
    private PermohonanTuntutanKos permohonanTuntutanKosSemasa;
    private LanjutanBayaran lanjutBayaran;
    private boolean editPTD;
    private String kodNegeri;
    private String stageId;
    private String kpsn;
    private BigDecimal premium;
    private List<PermohonanTuntutanKos> listtuntutankos;
    private PermohonanTuntutanKos permohonantuntutkos;

    @DefaultHandler
    public Resolution showForm() {
        logger.debug("-------ShowForm-------");
        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        return new JSP("pembangunan/rayuan/keputusanRPP.jsp").addParameter("tab", "true");
    }

    public Resolution viewPremium() {
        return new JSP("pembangunan/rayuan/keputusanRPP.jsp").addParameter("tab", "true");
    }

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {
        pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");

        if (idPermohonan != null) {
            permohonan = permohonanDAO.findById(idPermohonan);
        }
        if (permohonan != null) {
            permohonanSebelum = permohonan.getPermohonanSebelum();
        }
        if (permohonan.getPermohonanSebelum() != null) {
            permohonanTuntutanKos = devService.findMohonTuntutKosByIdPermohonanAndIdTuntut(permohonanSebelum.getIdPermohonan(), "DEV04");
        }

        listtuntutankos = devService.findTuntutByIdMohon(idPermohonan);

        if (!(listtuntutankos.isEmpty())) {
            for (int i = 0; i < listtuntutankos.size(); i++) {
                permohonantuntutkos = new PermohonanTuntutanKos();
                permohonantuntutkos = listtuntutankos.get(i);
                if (permohonantuntutkos.getKodTuntut() != null) {
                    if (permohonantuntutkos.getKodTuntut().getKod().equals("DEV04")) {
                        premium = permohonantuntutkos.getAmaunTuntutan();
                    }
                }
            }
        }


//        if(permohonan.getKeputusan()!=null){
//            kpsn = permohonan.getKeputusan().getKod();
//        }
    }

    public Resolution premium() {
        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        if (idPermohonan != null) {
            permohonan = permohonanDAO.findById(idPermohonan);
            if (permohonan.getKodUrusan().getKod().equals("RAYK")) {
                if (permohonan.getPermohonanSebelum() != null) {
                    permohonanSebelum = permohonan.getPermohonanSebelum();
                    if (permohonanSebelum != null) {
                        permohonanTuntutanKos = pelupusanService.findMohonTuntutKosByIdPermohonanAndIdTuntut(permohonanSebelum.getIdPermohonan(), "DISPRM");
                        if (permohonanTuntutanKos.getAmaunTuntutan() == null) {
                            addSimpleError("Tiada Lagi Bayaran Premium");
                        }
                    }
                }
            }
            if (permohonan.getKodUrusan().getKod().equals("RYKN")) {
                permohonanTuntutanKosSemasa = pelupusanService.findMohonTuntutKosByIdPermohonanAndIdTuntut(permohonan.getIdPermohonan(), "DISPRM");
                if (permohonan.getPermohonanSebelum() != null) {
                    permohonanSebelum = permohonan.getPermohonanSebelum();
                    if (permohonanSebelum != null) {
                        permohonanTuntutanKos = pelupusanService.findMohonTuntutKosByIdPermohonanAndIdTuntut(permohonanSebelum.getIdPermohonan(), "DISPRM");
                        if (permohonanTuntutanKos.getAmaunTuntutan() == null) {
                            addSimpleError("Tiada Lagi Bayaran Premium");
                        }
                    }
                }
                editPTD = Boolean.FALSE;
            }
        }
        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        return new JSP("pembangunan/rayuan/keputusanRPP.jsp").addParameter("tab", "true");
    }

    public Resolution simpanPremium() throws ParseException {
        logger.debug("-------start simpan-------");
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = permohonanDAO.findById(idPermohonan);
        if (permohonan != null) {
            permohonanSebelum = permohonan.getPermohonanSebelum();
        }
        if (permohonan.getPermohonanSebelum() != null) {
            permohonanTuntutanKos = devService.findMohonTuntutKosByIdPermohonanAndIdTuntut(permohonanSebelum.getIdPermohonan(), "DEV04");
        }
        String kadarRayuan = permohonan.getNoMahkamah();

        if (kadarRayuan != null) {
            double test = Double.parseDouble(kadarRayuan);
            logger.info("test:" + test);
            BigDecimal a = BigDecimal.valueOf(test);
            logger.info("a:" + a);
            BigDecimal b = a.divide(new BigDecimal(100));
            logger.info("b:" + b);
            kadarPremium = permohonanTuntutanKos.getAmaunTuntutan().multiply(b);
            premium = permohonanTuntutanKos.getAmaunTuntutan().subtract(kadarPremium);
        }

        logger.info("--------kadarPremium----------" + premium);
        Pengguna pengg = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        KodCawangan caw = pengg.getKodCawangan();
        InfoAudit ia = permohonan.getInfoAudit();
        if (ia == null) {
            ia = new InfoAudit();
            ia.setDimasukOleh(pengg);
            ia.setTarikhMasuk(new Date());
            permohonan.setInfoAudit(ia);
        } else {
            ia.setDiKemaskiniOleh(pengg);
            ia.setTarikhKemaskini(new java.util.Date());
            permohonan.setInfoAudit(ia);
        }

        permohonan.setNilaiDipohon(premium);
        logger.info(premium);
        if (premium != null) {
            permohonan.setNilaiDipohon(premium);
        } else {
            permohonan.setNilaiDipohon(null);
        }


        if (premium == null) {
            premium = new BigDecimal(0.00);
        }


        if (!(listtuntutankos.isEmpty())) {
            for (int i = 0; i < listtuntutankos.size(); i++) {
                permohonantuntutkos = new PermohonanTuntutanKos();
                permohonantuntutkos = listtuntutankos.get(i);
                if (permohonantuntutkos.getKodTuntut() != null) {
                    if (permohonantuntutkos.getKodTuntut().getKod().equals("DEV04")) {
                        permohonantuntutkos.setAmaunTuntutan(premium);
                    }
                    ia = permohonantuntutkos.getInfoAudit();
                    ia.setDiKemaskiniOleh(pengguna);
                    ia.setTarikhKemaskini(new java.util.Date());
                    permohonantuntutkos.setInfoAudit(ia);
                    permohonantuntutkos.setPermohonan(permohonan);
                    permohonantuntutkos.setCawangan(caw);
                    devService.simpanPermohonanTuntutanKos(permohonantuntutkos);
                }
            }
        } else {
            // Added new code
            KodTransaksiTuntut kodTransTuntut = new KodTransaksiTuntut();
            kodTransTuntut = kodTransaksiTuntutDAO.findById("DEV5A");
            PermohonanTuntutan permohonanTuntutan = new PermohonanTuntutan();
            permohonanTuntutan.setCawangan(caw);
            permohonanTuntutan.setPermohonan(permohonan);
            permohonanTuntutan.setInfoAudit(ia);
            permohonanTuntutan.setKodTransaksiTuntut(kodTransTuntut);
            permohonanTuntutan.setTarikhTuntutan(new java.util.Date());
            devService.simpanPermohonanTuntutan(permohonanTuntutan);

            KodTuntut kodTuntut = new KodTuntut();
            kodTuntut = kodTuntutDAO.findById("DEV04");
            permohonantuntutkos = new PermohonanTuntutanKos();
            permohonantuntutkos.setInfoAudit(ia);
            permohonantuntutkos.setKodTuntut(kodTuntut);
            permohonantuntutkos.setItem(kodTuntut.getNama());
            permohonantuntutkos.setAmaunTuntutan(premium);
            permohonantuntutkos.setPermohonan(permohonan);
            permohonantuntutkos.setCawangan(caw);
            permohonantuntutkos.setKodTransaksi(kodTuntut.getKodKewTrans());
            devService.simpanPermohonanTuntutanKos(permohonantuntutkos);

            // added new code
            PermohonanTuntutanButiran permohonanButir = new PermohonanTuntutanButiran();
            permohonanButir.setPermohonanTuntutan(permohonanTuntutan);
            permohonanButir.setPermohonanTuntutanKos(permohonantuntutkos);
            permohonanButir.setInfoAudit(ia);
            devService.simpanPermohonanTuntutanButiran(permohonanButir);
        }

        //editPremiumAsal    
        permohonantuntutkos = devService.findMohonTuntutKosByIdPermohonanAndIdTuntut(permohonanSebelum.getIdPermohonan(), "DEV04");
        if (permohonantuntutkos != null) {
            KodTuntut kodTuntut = new KodTuntut();
            kodTuntut = kodTuntutDAO.findById("DEV04");

            ia = permohonantuntutkos.getInfoAudit();
            ia.setDiKemaskiniOleh(pengguna);
            ia.setTarikhKemaskini(new java.util.Date());
            permohonantuntutkos.setInfoAudit(ia);

            permohonantuntutkos.setPermohonan(permohonan.getPermohonanSebelum());
            permohonantuntutkos.setCawangan(caw);

            permohonantuntutkos.setItem(kodTuntut.getNama());
            permohonantuntutkos.setAmaunSebenar(permohonantuntutkos.getAmaunTuntutan());
            logger.info("--------AmaunSebenar----------" + permohonantuntutkos.getAmaunSebenar());

            permohonantuntutkos.setAmaunTuntutan(premium);
            logger.info("--------Premium----------" + premium);
            logger.info("--------AmaunTuntutan----------" + permohonantuntutkos.getAmaunTuntutan());           
            devService.simpanPermohonanTuntutanKos(permohonantuntutkos);

        } else {
            logger.info("--------Not inserting----------");
        }

        pelupusanService.simpanPermohonan(permohonan);
        addSimpleMessage("Maklumat telah berjaya disimpan.");

        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        return new JSP("pembangunan/rayuan/keputusanRPP.jsp").addParameter("tab", "true");
    }

    public Resolution premiumView() {
        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        if (idPermohonan != null) {
            permohonan = permohonanDAO.findById(idPermohonan);
            if (permohonan.getKodUrusan().getKod().equals("RAYK")) {
                if (permohonan.getPermohonanSebelum() != null) {
                    permohonanSebelum = permohonan.getPermohonanSebelum();
                    if (permohonanSebelum != null) {
                        permohonanTuntutanKos = pelupusanService.findMohonTuntutKosByIdPermohonanAndIdTuntut(permohonanSebelum.getIdPermohonan(), "DISPRM");
                        if (permohonanTuntutanKos.getAmaunTuntutan() == null) {
                            addSimpleError("Tiada Lagi Bayaran Premium");
                        }
                    }
                }
            }
            if (permohonan.getKodUrusan().getKod().equals("RYKN")) {
                permohonanTuntutanKosSemasa = pelupusanService.findMohonTuntutKosByIdPermohonanAndIdTuntut(permohonan.getIdPermohonan(), "DISPRM");
                if (permohonan.getPermohonanSebelum() != null) {
                    permohonanSebelum = permohonan.getPermohonanSebelum();
                    if (permohonanSebelum != null) {
                        permohonanTuntutanKos = pelupusanService.findMohonTuntutKosByIdPermohonanAndIdTuntut(permohonanSebelum.getIdPermohonan(), "DISPRM");
                        if (permohonanTuntutanKos.getAmaunTuntutan() == null) {
                            addSimpleError("Tiada Lagi Bayaran Premium");
                        }
                    }
                }
                editPTD = Boolean.FALSE;
            }
        }
        getContext().getRequest().setAttribute("edit", Boolean.FALSE);
        return new JSP("pelupusan/rayuan_pengurangan_premium.jsp").addParameter("tab", "true");
    }

    public Resolution premiumPTD() {
        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        if (idPermohonan != null) {
            permohonan = permohonanDAO.findById(idPermohonan);
            if (permohonan.getKodUrusan().getKod().equals("RAYK")) {
                if (permohonan.getPermohonanSebelum() != null) {
                    permohonanSebelum = permohonan.getPermohonanSebelum();
                    if (permohonanSebelum != null) {
                        permohonanTuntutanKos = pelupusanService.findMohonTuntutKosByIdPermohonanAndIdTuntut(permohonanSebelum.getIdPermohonan(), "DISPRM");
                        if (permohonanTuntutanKos.getAmaunTuntutan() == null) {
                            addSimpleError("Tiada Lagi Bayaran Premium");
                        }
                    }
                }
            }
            if (permohonan.getKodUrusan().getKod().equals("RYKN")) {
                if (permohonan.getPermohonanSebelum() != null) {
                    permohonanSebelum = permohonan.getPermohonanSebelum();
                    if (permohonanSebelum != null) {
                        permohonanTuntutanKos = pelupusanService.findMohonTuntutKosByIdPermohonanAndIdTuntut(permohonanSebelum.getIdPermohonan(), "DISPRM");
                        if (permohonanTuntutanKos.getAmaunTuntutan() == null) {
                            addSimpleError("Tiada Lagi Bayaran Premium");
                        }
                    }
                }
                editPTD = Boolean.TRUE;
            }
        }
        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        return new JSP("pelupusan/rayuan_pengurangan_premium.jsp").addParameter("tab", "true");
    }

    public Resolution premiumViewPTD() {
        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        if (idPermohonan != null) {
            permohonan = permohonanDAO.findById(idPermohonan);
            if (permohonan.getKodUrusan().getKod().equals("RAYK")) {
                if (permohonan.getPermohonanSebelum() != null) {
                    permohonanSebelum = permohonan.getPermohonanSebelum();
                    if (permohonanSebelum != null) {
                        permohonanTuntutanKos = pelupusanService.findMohonTuntutKosByIdPermohonanAndIdTuntut(permohonanSebelum.getIdPermohonan(), "DISPRM");
                        if (permohonanTuntutanKos.getAmaunTuntutan() == null) {
                            addSimpleError("Tiada Lagi Bayaran Premium");
                        }
                    }
                }
            }
            if (permohonan.getKodUrusan().getKod().equals("RYKN")) {
                if (permohonan.getPermohonanSebelum() != null) {
                    permohonanSebelum = permohonan.getPermohonanSebelum();
                    if (permohonanSebelum != null) {
                        permohonanTuntutanKos = pelupusanService.findMohonTuntutKosByIdPermohonanAndIdTuntut(permohonanSebelum.getIdPermohonan(), "DISPRM");
                        if (permohonanTuntutanKos.getAmaunTuntutan() == null) {
                            addSimpleError("Tiada Lagi Bayaran Premium");
                        }
                    }
                }
                editPTD = Boolean.TRUE;
            }
        }
        getContext().getRequest().setAttribute("edit", Boolean.FALSE);
        return new JSP("pelupusan/rayuan_pengurangan_premium.jsp").addParameter("tab", "true");
    }

    public Resolution lanjutan_tempoh() {
        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        if (idPermohonan != null) {
            permohonan = permohonanDAO.findById(idPermohonan);

            if (permohonan.getPermohonanSebelum() != null) {
                permohonanSebelum = permohonan.getPermohonanSebelum();
//                  if (permohonanSebelum != null) {
//                      permohonanTuntutanKos = pelupusanService.findMohonTuntutKosByIdPermohonanAndIdTuntut(permohonanSebelum.getIdPermohonan(), "DISPRM");
//                      if (permohonanTuntutanKos.getAmaunTuntutan() == null) {
//                          addSimpleError("Tiada Lagi Bayaran Premium");
//                      }
//                  }
            }
        }
        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        return new JSP("pelupusan/rayuan_lanjut_tempoh_bayaran.jsp").addParameter("tab", "true");
    }

    public Resolution lanjutan_tempohView() {

        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        if (idPermohonan != null) {
            permohonan = permohonanDAO.findById(idPermohonan);

            if (permohonan.getPermohonanSebelum() != null) {
                permohonanSebelum = permohonan.getPermohonanSebelum();
            }
        }
        getContext().getRequest().setAttribute("edit", Boolean.FALSE);
        return new JSP("pelupusan/rayuan_lanjut_tempoh_bayaran.jsp").addParameter("tab", "true");
    }

    public Resolution showFormPTD() {
//        edit = Boolean.FALSE;
//        edit = Boolean.TRUE;
//        ptd = Boolean.FALSE;
//        openPTG = Boolean.TRUE;
        if (permohonan.getPermohonanSebelum() != null) {
            permohonanSebelum = permohonan.getPermohonanSebelum();
            if (permohonanSebelum != null) {
                permohonanTuntutanKos = pelupusanService.findMohonTuntutKosByIdPermohonanAndIdTuntut(permohonanSebelum.getIdPermohonan(), "DISPRM");
                if (permohonanTuntutanKos.getAmaunTuntutan() == null) {
                    addSimpleError("Tiada Lagi Bayaran Premium");
                }
            }
        }
        editPTD = Boolean.TRUE;
//        editPTG = Boolean.TRUE;
        return new JSP("pelupusan/rayuan_lanjut_tempoh_bayaran.jsp").addParameter("tab", "true");
    }

    public Resolution simpan() throws ParseException {
        logger.debug("start simpan");
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = permohonanDAO.findById(idPermohonan);

        sebab = getContext().getRequest().getParameter("permohonan.rujukanUndang2");
        permohonan.setRujukanUndang2(sebab);

        pelupusanService.simpanPermohonan(permohonan);

        addSimpleMessage("Maklumat telah berjaya disimpan.");
        return new JSP("pelupusan/rayuan_RAYT.jsp").addParameter("tab", "true");

    }

    public Resolution simpanLanjut() throws ParseException {
        logger.debug("start simpan");
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = permohonanDAO.findById(idPermohonan);
//        lanjutBayaran = pelupusanService.findLanjutBayaranByIdMohon(idPermohonan);
//        Pengguna pengg = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
//        if(lanjutBayaran != null){
//        InfoAudit ia = lanjutBayaran.getInfoAudit();
//            ia.setDiKemaskiniOleh(pengg);
//            ia.setTarikhKemaskini(new java.util.Date());
//            lanjutBayaran.setInfoAudit(ia);
//
//        }
//        else{
//            InfoAudit ia = new InfoAudit();
//            ia.setDimasukOleh(pengg);
//            ia.setTarikhMasuk(new Date());
//            lanjutBayaran.setInfoAudit(ia);
//
//        }
//
//        lanjutBayaran.setPermohonan(permohonan);
//        java.math.BigDecimal s1 = (BigDecimal)hakmilikPermohonan.getCukaiPerMeterPersegi();
//        hakmilikPermohonan.setCukaiPerMeterPersegi(s1);
        Pengguna pengg = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        InfoAudit ia = permohonan.getInfoAudit();
        if (ia == null) {
            ia = new InfoAudit();
            ia.setDimasukOleh(pengg);
            ia.setTarikhMasuk(new Date());
            permohonan.setInfoAudit(ia);
        } else {
            ia.setDiKemaskiniOleh(pengg);
            ia.setTarikhKemaskini(new java.util.Date());
            permohonan.setInfoAudit(ia);
        }

        tempoh = (Integer) permohonan.getTempohBulan();
        logger.info(tempoh);
        permohonan.setTempohBulan(tempoh);
        pelupusanService.simpanPermohonan(permohonan);

        logger.debug("tess2 :" + permohonan.getIdPermohonan());

        addSimpleMessage("Maklumat telah berjaya disimpan.");
        return lanjutan_tempoh();

    }

    public Resolution simpan3() throws ParseException {
        logger.debug("start simpan");
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = permohonanDAO.findById(idPermohonan);
        if (permohonan != null) {
            permohonanSebelum = permohonan.getPermohonanSebelum();
        }
        if (permohonan.getPermohonanSebelum() != null) {
            permohonanTuntutanKos = devService.findMohonTuntutKosByIdPermohonanAndIdTuntut(permohonanSebelum.getIdPermohonan(), "DEV04");
        }
        String kadarRayuan = permohonan.getNoMahkamah();

        if (kadarRayuan != null) {
            double test = Double.parseDouble(kadarRayuan);
            logger.info(test);
            BigDecimal a = BigDecimal.valueOf(test);
            logger.info("a:" + a);
            BigDecimal b = a.divide(new BigDecimal(100));
            logger.info("b:" + b);
            kadarPremium = permohonanTuntutanKos.getAmaunTuntutan().multiply(b);
            kadarPremium = permohonanTuntutanKos.getAmaunTuntutan().subtract(kadarPremium);
        }

//                if(kadarRayuan.equals("0.25") || kadarRayuan.equals("0.5")){
//                    logger.info(kadarRayuan);
//                    double test = Double.parseDouble(kadarRayuan) ;
//                    logger.info(test);
//                    BigDecimal a = BigDecimal.valueOf(test);
//                    logger.info(a);
//                    kadarPremium = permohonanTuntutanKos.getAmaunTuntutan().multiply(a);
//                    kadarPremium = permohonanTuntutanKos.getAmaunTuntutan().subtract(kadarPremium);
//                }
//                else if(kadarRayuan.equals("N")){
//                    kadarPremium = (BigDecimal)permohonan.getNilaiDipohon();
//                 }
//        }
        logger.info("--------kadarPremium----------" + kadarPremium);
        Pengguna pengg = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        InfoAudit ia = permohonan.getInfoAudit();
        if (ia == null) {
            ia = new InfoAudit();
            ia.setDimasukOleh(pengg);
            ia.setTarikhMasuk(new Date());
            permohonan.setInfoAudit(ia);
        } else {
            ia.setDiKemaskiniOleh(pengg);
            ia.setTarikhKemaskini(new java.util.Date());
            permohonan.setInfoAudit(ia);
        }


//       if (permohonan.getKodUrusan().getKod().equals("RYKN")){
//           if (editPTD){
//                logger.info(kadarPremium);
//                permohonan.setNilaiDipohon(kadarPremium);
//           }
//       }
//       else {
        logger.info("--------kadarPremium-----------" + kadarPremium);
        permohonan.setNilaiDipohon(kadarPremium);
//       }

//       BPelService service = new BPelService();
//        String taskId = (String) getContext().getRequest().getSession().getAttribute("taskId");
//
//        if (StringUtils.isNotBlank(taskId)) {
//            Task t = null;
//            t = service.getTaskFromBPel(taskId, pengguna);
//            stageId = t.getSystemAttributes().getStage();
//        }

//            if(permohonan.getKodUrusan().getKod().equals("RYKN")){
//                if (stageId.equals("03SemakSyorKertas")) {
//                    pelupusanService.simpanPermohonan(permohonan);
//                    addSimpleMessage("Maklumat telah berjaya disimpan.");
//                    return premiumPTD();
//                }
//                else {
//                    pelupusanService.simpanPermohonan(permohonan);
//                    addSimpleMessage("Maklumat telah berjaya disimpan.");
//                    return premium() ;
//                }
//
//            }

//            else if(!permohonan.getKodUrusan().getKod().equals("RYKN")){
        logger.info(kadarPremium);
        if (kadarPremium != null) {
            permohonan.setNilaiDipohon(kadarPremium);
        } else {
            permohonan.setNilaiDipohon(null);
        }
        pelupusanService.simpanPermohonan(permohonan);
        addSimpleMessage("Maklumat telah berjaya disimpan.");
//                return premium() ;
//            }

        //   return new JSP("pelupusan/rayuan_pengurangan_premium.jsp").addParameter("tab", "true");

//        return premium() ;
        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        return new JSP("pembangunan/rayan/keputusanRPP.jsp").addParameter("tab", "true");
    }

    public HakmilikPermohonan getHakmilikPermohonan() {
        return hakmilikPermohonan;
    }

    public void setHakmilikPermohonan(HakmilikPermohonan hakmilikPermohonan) {
        this.hakmilikPermohonan = hakmilikPermohonan;
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

    public String getIdPermohonan() {
        return idPermohonan;
    }

    public void setIdPermohonan(String idPermohonan) {
        this.idPermohonan = idPermohonan;
    }

    public BigDecimal getKadarPremium() {
        return kadarPremium;
    }

    public void setKadarPremium(BigDecimal kadarPremium) {
        this.kadarPremium = kadarPremium;
    }

    public BigDecimal getCukaiPerMeterPersegi() {
        return cukaiPerMeterPersegi;
    }

    public void setCukaiPerMeterPersegi(BigDecimal cukaiPerMeterPersegi) {
        this.cukaiPerMeterPersegi = cukaiPerMeterPersegi;
    }

    public Permohonan getPermohonanSebelum() {
        return permohonanSebelum;
    }

    public void setPermohonanSebelum(Permohonan permohonanSebelum) {
        this.permohonanSebelum = permohonanSebelum;
    }

    public PermohonanTuntutanKos getPermohonanTuntutanKos() {
        return permohonanTuntutanKos;
    }

    public void setPermohonanTuntutanKos(PermohonanTuntutanKos permohonanTuntutanKos) {
        this.permohonanTuntutanKos = permohonanTuntutanKos;
    }

    public LanjutanBayaran getLanjutBayaran() {
        return lanjutBayaran;
    }

    public void setLanjutBayaran(LanjutanBayaran lanjutBayaran) {
        this.lanjutBayaran = lanjutBayaran;
    }

    public String getSebab() {
        return sebab;
    }

    public void setSebab(String sebab) {
        this.sebab = sebab;
    }

    public Integer getTempoh() {
        return tempoh;
    }

    public void setTempoh(Integer tempoh) {
        this.tempoh = tempoh;
    }

    public boolean isEditPTD() {
        return editPTD;
    }

    public void setEditPTD(boolean editPTD) {
        this.editPTD = editPTD;
    }

    public PermohonanTuntutanKos getPermohonanTuntutanKosSemasa() {
        return permohonanTuntutanKosSemasa;
    }

    public void setPermohonanTuntutanKosSemasa(PermohonanTuntutanKos permohonanTuntutanKosSemasa) {
        this.permohonanTuntutanKosSemasa = permohonanTuntutanKosSemasa;
    }

    public String getKpsn() {
        return kpsn;
    }

    public void setKpsn(String kpsn) {
        this.kpsn = kpsn;
    }

    public BigDecimal getPremium() {
        return premium;
    }

    public void setPremium(BigDecimal premium) {
        this.premium = premium;
    }

    public BigDecimal getPremiumBaru() {
        return premiumBaru;
    }

    public void setPremiumBaru(BigDecimal premiumBaru) {
        this.premiumBaru = premiumBaru;
    }

    public String getKodNegeri() {
        return kodNegeri;
    }

    public void setKodNegeri(String kodNegeri) {
        this.kodNegeri = kodNegeri;
    }

    public PermohonanTuntutanKos getPermohonantuntutkos() {
        return permohonantuntutkos;
    }

    public void setPermohonantuntutkos(PermohonanTuntutanKos permohonantuntutkos) {
        this.permohonantuntutkos = permohonantuntutkos;
    }
}