/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.stripes.pelupusan;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.dao.KodCawanganDAO;
import etanah.dao.KodDokumenDAO;
import etanah.dao.PermohonanDAO;
import etanah.dao.PermohonanKertasKandunganDAO;
import etanah.dao.PermohonanLaporanKandunganDAO;
import etanah.model.BangunanTingkat;
import etanah.model.FasaPermohonan;
import etanah.model.Hakmilik;
import etanah.model.HakmilikPermohonan;
import etanah.model.InfoAudit;
import etanah.model.KodCawangan;
import etanah.model.KodDokumen;
import etanah.model.LaporanTanah;
import etanah.model.Pemohon;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.model.PermohonanBangunan;
import etanah.model.PermohonanKertas;
import etanah.model.PermohonanKertasKandungan;
import etanah.model.PermohonanLaporanKandungan;
import etanah.model.Pihak;
import etanah.service.BPelService;
import etanah.service.PelupusanService;
import etanah.service.StrataPtService;
import etanah.view.etanahActionBeanContext;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
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
 * @author afham
 */
@UrlBinding("/pelupusan/kertas_ringkas_rayt")
public class KertasRingkasRAYTActionBean extends AbleActionBean {

    @Inject
    StrataPtService strService;
    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    PermohonanKertasKandunganDAO permohonanKertasKandDAO;
    @Inject
    KodCawanganDAO kodCawanganDAO;
    @Inject
    PelupusanService plpService;
    @Inject
    KodDokumenDAO kodDokumenDAO;
//    private List<PermohonanKertasKandungan> listKertasHuraianPTD = new ArrayList<PermohonanKertasKandungan>();
//    private List<PermohonanKertasKandungan> listKertasSyorPTD = new ArrayList<PermohonanKertasKandungan>();
    private List<PermohonanKertasKandungan> listKertasPerakuanPTG = new ArrayList<PermohonanKertasKandungan>();
    private List<PermohonanKertasKandungan> listKertasPerakuanPTGSebelum = new ArrayList<PermohonanKertasKandungan>();
    private List<PermohonanKertasKandungan> listKertasPerakuanPTD = new ArrayList<PermohonanKertasKandungan>();
    private List<PermohonanKertasKandungan> listKertasPerakuanPTDSebelum = new ArrayList<PermohonanKertasKandungan>();
    private List<PermohonanKertasKandungan> latarBelakangList = new ArrayList<PermohonanKertasKandungan>();
    private List<PermohonanKertasKandungan> latarBelakangListSebelum = new ArrayList<PermohonanKertasKandungan>();
    private List<PermohonanKertasKandungan> rayuanList = new ArrayList<PermohonanKertasKandungan>();
    private List<PermohonanKertasKandungan> rayuanListSebelum = new ArrayList<PermohonanKertasKandungan>();
    private static final Logger LOG = Logger.getLogger(KertasRingkasRAYTActionBean.class);
    private PermohonanKertas mohonKertas;
    private Permohonan permohonan;
    private String idPermohonan;
    private Pemohon pemohon;
    private HakmilikPermohonan hakmilikPermohonan;
    private String idPermohonanSebelum;
    private String tajuk;
    private String tujuan;
    private String perakuanPTG;
    private String perihalPemohon;
    private String perihalTanah;
    private String perihalRayuan;
    private Pihak pihak;
    private Date tarikhSidang;
    private String bilMesy;
    private SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    private PelupusanUtiliti pelupusanUtiliti = new PelupusanUtiliti();

    @DefaultHandler
    public Resolution showForm() {
        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        if (permohonan.getKodUrusan().getKod().equals("RAYT")) {
            getContext().getRequest().setAttribute("keputusanPTD", Boolean.TRUE);
        } else {
            getContext().getRequest().setAttribute("keputusanPTD", Boolean.FALSE);
        }
        return new JSP("pelupusan/rayuan/kertas_ringkas_rayt.jsp").addParameter("tab", "true");
    }

    public Resolution showFormPTG() {
        getContext().getRequest().setAttribute("edit", Boolean.FALSE);
        getContext().getRequest().setAttribute("ptg", Boolean.TRUE);
        return new JSP("pelupusan/rayuan/kertas_ringkas_rayt.jsp").addParameter("tab", "true");
    }

    public Resolution viewFormPTG() {
        getContext().getRequest().setAttribute("view", Boolean.TRUE);
        getContext().getRequest().setAttribute("keputusan", Boolean.FALSE);
        getContext().getRequest().setAttribute("ptg", Boolean.TRUE);
        return new JSP("pelupusan/rayuan/kertas_ringkas_rayt.jsp").addParameter("tab", "true");
    }

    public Resolution editKeputusanPTG() {
        getContext().getRequest().setAttribute("view", Boolean.FALSE);
        getContext().getRequest().setAttribute("keputusan", Boolean.TRUE);
        getContext().getRequest().setAttribute("ptg", Boolean.TRUE);
        return new JSP("pelupusan/rayuan/kertas_ringkas_rayt.jsp").addParameter("tab", "true");
    }

    public Resolution showFormPTD() {
        getContext().getRequest().setAttribute("edit", Boolean.FALSE);
        //   getContext().getRequest().setAttribute("ptd", Boolean.TRUE);
        if (permohonan.getKodUrusan().getKod().equals("RAYT")) {
            getContext().getRequest().setAttribute("keputusanPTD", Boolean.TRUE);
        } else {
            getContext().getRequest().setAttribute("keputusanPTD", Boolean.FALSE);
        }
        return new JSP("pelupusan/rayuan/kertas_ringkas_rayt.jsp").addParameter("tab", "true");
    }

    public Resolution tambahRow() {

        PermohonanKertasKandungan pkk = new PermohonanKertasKandungan();
        int index = 0;
        index = Integer.parseInt(getContext().getRequest().getParameter("index"));
        switch (index) {
            case 1:
                break;
            case 2:
                pkk = new PermohonanKertasKandungan();
                pkk.setBil((short) 2);
                latarBelakangList.add(pkk);
                break;
            case 7:
                pkk = new PermohonanKertasKandungan();
                pkk.setBil((short) 7);
                rayuanList.add(pkk);
                break;
            case 4:
                pkk = new PermohonanKertasKandungan();
                pkk.setBil((short) 4);
                listKertasPerakuanPTD.add(pkk);
                break;
            case 5:
                pkk = new PermohonanKertasKandungan();
                pkk.setBil((short) 5);
                listKertasPerakuanPTG.add(pkk);
                break;
            default:
        }
        System.out.println(index);
        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        if (permohonan.getKodUrusan().getKod().equals("RAYT")) {
            getContext().getRequest().setAttribute("keputusanPTD", Boolean.TRUE);
        } else {
            getContext().getRequest().setAttribute("keputusanPTD", Boolean.FALSE);
        }
        return new JSP("pelupusan/rayuan/kertas_ringkas_rayt.jsp").addParameter("tab", "true");
    }

    public Resolution simpan() throws ParseException {

        int index = 0;
        index = Integer.parseInt(getContext().getRequest().getParameter("index"));
        String kand = getContext().getRequest().getParameter("kandungan");
        LOG.info("CHECKING:..... index :" + index + " kand :" + kand);
        switch (index) {
            case 1:

                break;
            case 2:
                updateKandungan(2, kand);

                break;
            case 7:
                updateKandungan(7, kand);

                break;
            case 4:

                updateKandungan(4, kand);

                break;
            case 5:

                updateKandungan(5, kand);

                break;
            default:
                LOG.info("Tiada index");
        }
        rehydrate();

        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        if (permohonan.getKodUrusan().getKod().equals("RAYT")) {
            getContext().getRequest().setAttribute("keputusanPTD", Boolean.TRUE);
        } else {
            getContext().getRequest().setAttribute("keputusanPTD", Boolean.FALSE);
        }
        addSimpleMessage("Maklumat Berjaya Disimpan");
        return new JSP("pelupusan/rayuan/kertas_ringkas_rayt.jsp").addParameter("tab", "true");
    }

    public Resolution kemaskini() {
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);

        int index = 0;
        index = Integer.parseInt(getContext().getRequest().getParameter("index"));
        String kand = getContext().getRequest().getParameter("kandungan");
        String idKand = getContext().getRequest().getParameter("idKandungan");
        LOG.info("CHECKING:..... index :" + index + " kand :" + kand + "idKandungan" + idKand);
        InfoAudit ia = new InfoAudit();
        if (idKand != null) {
            if (!idKand.equals("0")) {
                PermohonanKertasKandungan plk = permohonanKertasKandDAO.findById(Long.parseLong(idKand));
                if (plk != null) {
                    ia = plk.getInfoAudit();
                    ia.setDiKemaskiniOleh(peng);
                    ia.setTarikhKemaskini(new java.util.Date());
                    plk.setInfoAudit(ia);
                    plk.setKandungan(kand);
                    plpService.simpanPermohonanKertasKandungan(plk);
                }
            } else {
                InfoAudit infoAudit = new InfoAudit();
                KodCawangan cawangan = new KodCawangan();
                cawangan = kodCawanganDAO.findById(peng.getKodCawangan().getKod());
                mohonKertas = plpService.findKertasByKod(idPermohonan, "RMN");

                if (mohonKertas != null) {
                    infoAudit = mohonKertas.getInfoAudit();
                    infoAudit.setDiKemaskiniOleh(peng);
                    infoAudit.setTarikhKemaskini(new java.util.Date());
                } else {
                    mohonKertas = new PermohonanKertas();
                    infoAudit.setDimasukOleh(peng);
                    infoAudit.setTarikhMasuk(new java.util.Date());

                }
                mohonKertas.setTajuk("Kertas Ringkas Rayuan");
                mohonKertas.setCawangan(cawangan);
                mohonKertas.setInfoAudit(infoAudit);
                mohonKertas.setPermohonan(permohonan);
                strService.simpanPermohonanKertas(mohonKertas);

                long a = mohonKertas.getIdKertas();
                List<PermohonanKertasKandungan> plk = strService.findByIdLapor(a, index);

                PermohonanKertasKandungan pLK = new PermohonanKertasKandungan();
                LOG.info("index :" + index + " kand :" + kand + " id_lapor :" + a);

                if (plk.isEmpty()) {
                    pLK.setSubtajuk("1");
                    LOG.info("PLK" + pLK.getSubtajuk());
                } else {
                    LOG.info("plk.size()  : " + plk.size());
                    LOG.info("sub tajuk : " + plk.get(plk.size() - 1).getSubtajuk());
                    pLK.setSubtajuk(String.valueOf(convertList(plk.get(plk.size() - 1).getSubtajuk())));
                }
                pLK.setBil((short) index);
                pLK.setKandungan(kand);
                pLK.setKertas(mohonKertas);
                pLK.setInfoAudit(infoAudit);
                pLK.setCawangan(cawangan);
                strService.simpanPermohonanKertasKandungan(pLK);
            }
        }

        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        if (permohonan.getKodUrusan().getKod().equals("RAYT")) {
            getContext().getRequest().setAttribute("keputusanPTD", Boolean.TRUE);
        } else {
            getContext().getRequest().setAttribute("keputusanPTD", Boolean.FALSE);
        }
        rehydrate();
        addSimpleMessage("Maklumat Berjaya Dikemaskini");
        return new JSP("pelupusan/rayuan/kertas_ringkas_rayt.jsp").addParameter("tab", "true");
    }

    public Resolution deleteRow() throws ParseException {

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
        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        if (permohonan.getKodUrusan().getKod().equals("RAYT")) {
            getContext().getRequest().setAttribute("keputusanPTD", Boolean.TRUE);
        } else {
            getContext().getRequest().setAttribute("keputusanPTD", Boolean.FALSE);
        }
        return new JSP("pelupusan/rayuan/kertas_ringkas_rayt.jsp").addParameter("tab", "true");
    }

    //Perakuan PTG
    public Resolution tambahPerakuanRowPTG() {

        PermohonanKertasKandungan pkk = new PermohonanKertasKandungan();
        int index = 0;
        index = Integer.parseInt(getContext().getRequest().getParameter("index"));
        switch (index) {
            case 6:
                pkk = new PermohonanKertasKandungan();
                pkk.setBil((short) 6);
                listKertasPerakuanPTG.add(pkk);
                break;
            default:
        }
        System.out.println(index);
        getContext().getRequest().setAttribute("view", Boolean.FALSE);
        getContext().getRequest().setAttribute("keputusan", Boolean.TRUE);
        getContext().getRequest().setAttribute("ptg", Boolean.TRUE);
        return new JSP("pelupusan/rayuan/kertas_ringkas_rayt.jsp").addParameter("tab", "true");
    }

    public Resolution simpanPerakuanPTG() throws ParseException {

        int index = 0;
        index = Integer.parseInt(getContext().getRequest().getParameter("index"));
        String kand = getContext().getRequest().getParameter("kandungan");
        LOG.info("CHECKING:..... index :" + index + " kand :" + kand);
        switch (index) {
            case 1:

                break;
            case 6:

                updateKandungan(6, kand);
                break;
            default:
                LOG.info("Tiada index");
        }
        rehydrate();

        getContext().getRequest().setAttribute("view", Boolean.FALSE);
        getContext().getRequest().setAttribute("keputusan", Boolean.TRUE);
        getContext().getRequest().setAttribute("ptg", Boolean.TRUE);
        addSimpleMessage("Maklumat Berjaya Disimpan");
        return new JSP("pelupusan/rayuan/kertas_ringkas_rayt.jsp").addParameter("tab", "true");
    }

    public Resolution kemaskiniPerakuanPTG() {
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);

        int index = 0;
        index = Integer.parseInt(getContext().getRequest().getParameter("index"));
        String kand = getContext().getRequest().getParameter("kandungan");
        String idKand = getContext().getRequest().getParameter("idKandungan");
        LOG.info("CHECKING:..... index :" + index + " kand :" + kand + "idKandungan" + idKand);
        InfoAudit ia = new InfoAudit();
        if (idKand != null) {
            if (!idKand.equals("0")) {
                PermohonanKertasKandungan plk = permohonanKertasKandDAO.findById(Long.parseLong(idKand));
                if (plk != null) {
                    ia = plk.getInfoAudit();
                    ia.setDiKemaskiniOleh(peng);
                    ia.setTarikhKemaskini(new java.util.Date());
                    plk.setInfoAudit(ia);
                    plk.setKandungan(kand);
                    plpService.simpanPermohonanKertasKandungan(plk);
                }
            } else {
                InfoAudit infoAudit = new InfoAudit();
                KodCawangan cawangan = new KodCawangan();
                cawangan = kodCawanganDAO.findById(peng.getKodCawangan().getKod());
                mohonKertas = plpService.findKertasByKod(idPermohonan, "RMN");

                if (mohonKertas != null) {
                    infoAudit = mohonKertas.getInfoAudit();
                    infoAudit.setDiKemaskiniOleh(peng);
                    infoAudit.setTarikhKemaskini(new java.util.Date());
                } else {
                    mohonKertas = new PermohonanKertas();
                    infoAudit.setDimasukOleh(peng);
                    infoAudit.setTarikhMasuk(new java.util.Date());

                }
                mohonKertas.setTajuk("Kertas Ringkas Rayuan");
                mohonKertas.setCawangan(cawangan);
                mohonKertas.setInfoAudit(infoAudit);
                mohonKertas.setPermohonan(permohonan);
                strService.simpanPermohonanKertas(mohonKertas);

                long a = mohonKertas.getIdKertas();
                List<PermohonanKertasKandungan> plk = strService.findByIdLapor(a, index);

                PermohonanKertasKandungan pLK = new PermohonanKertasKandungan();
                LOG.info("index :" + index + " kand :" + kand + " id_lapor :" + a);

                if (plk.isEmpty()) {
                    pLK.setSubtajuk("1");
                    LOG.info("PLK" + pLK.getSubtajuk());
                } else {
                    LOG.info("plk.size()  : " + plk.size());
                    LOG.info("sub tajuk : " + plk.get(plk.size() - 1).getSubtajuk());
                    pLK.setSubtajuk(String.valueOf(convertList(plk.get(plk.size() - 1).getSubtajuk())));
                }
                pLK.setBil((short) index);
                pLK.setKandungan(kand);
                pLK.setKertas(mohonKertas);
                pLK.setInfoAudit(infoAudit);
                pLK.setCawangan(cawangan);
                strService.simpanPermohonanKertasKandungan(pLK);
            }
        }

        getContext().getRequest().setAttribute("view", Boolean.FALSE);
        getContext().getRequest().setAttribute("keputusan", Boolean.TRUE);
        getContext().getRequest().setAttribute("ptg", Boolean.TRUE);
        rehydrate();
        addSimpleMessage("Maklumat Berjaya Dikemaskini");
        return new JSP("pelupusan/rayuan/kertas_ringkas_rayt.jsp").addParameter("tab", "true");
    }

    public Resolution deleteRowPerakuanPTG() throws ParseException {

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
        getContext().getRequest().setAttribute("view", Boolean.FALSE);
        getContext().getRequest().setAttribute("keputusan", Boolean.TRUE);
        getContext().getRequest().setAttribute("ptg", Boolean.TRUE);
        return new JSP("pelupusan/rayuan/kertas_ringkas_rayt.jsp").addParameter("tab", "true");
    }

    //Perakuan PTD
    public Resolution tambahPerakuanRowPTD() {

        PermohonanKertasKandungan pkk = new PermohonanKertasKandungan();
        int index = 0;
        index = Integer.parseInt(getContext().getRequest().getParameter("index"));
        switch (index) {
            case 4:
                pkk = new PermohonanKertasKandungan();
                pkk.setBil((short) 4);
                listKertasPerakuanPTD.add(pkk);
                break;
            default:
        }
        System.out.println(index);
        getContext().getRequest().setAttribute("edit", Boolean.FALSE);
        getContext().getRequest().setAttribute("ptd", Boolean.TRUE);
        getContext().getRequest().setAttribute("keputusanPTD", Boolean.TRUE);
        return new JSP("pelupusan/rayuan/kertas_ringkas_rayt.jsp").addParameter("tab", "true");
    }

    public Resolution simpanPerakuanPTD() throws ParseException {

        int index = 0;
        index = Integer.parseInt(getContext().getRequest().getParameter("index"));
        String kand = getContext().getRequest().getParameter("kandungan");
        LOG.info("CHECKING:..... index :" + index + " kand :" + kand);
        switch (index) {
            case 1:

                break;
            case 4:

                updateKandungan(4, kand);

                break;
            default:
                LOG.info("alamak!! tiada index");
        }
        rehydrate();

        getContext().getRequest().setAttribute("edit", Boolean.FALSE);
        getContext().getRequest().setAttribute("ptd", Boolean.TRUE);
        getContext().getRequest().setAttribute("keputusanPTD", Boolean.TRUE);
        addSimpleMessage("Maklumat Berjaya Disimpan");
        return new JSP("pelupusan/rayuan/kertas_ringkas_rayt.jsp").addParameter("tab", "true");
    }

    public Resolution kemaskiniPerakuanPTD() {
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);

        int index = 0;
        index = Integer.parseInt(getContext().getRequest().getParameter("index"));
        String kand = getContext().getRequest().getParameter("kandungan");
        String idKand = getContext().getRequest().getParameter("idKandungan");
        LOG.info("CHECKING:..... index :" + index + " kand :" + kand + "idKandungan" + idKand);
        InfoAudit ia = new InfoAudit();
        if (idKand != null) {
            if (!idKand.equals("0")) {
                PermohonanKertasKandungan plk = permohonanKertasKandDAO.findById(Long.parseLong(idKand));
                if (plk != null) {
                    ia = plk.getInfoAudit();
                    ia.setDiKemaskiniOleh(peng);
                    ia.setTarikhKemaskini(new java.util.Date());
                    plk.setInfoAudit(ia);
                    plk.setKandungan(kand);
                    plpService.simpanPermohonanKertasKandungan(plk);
                }
            } else {
                InfoAudit infoAudit = new InfoAudit();
                KodCawangan cawangan = new KodCawangan();
                cawangan = kodCawanganDAO.findById(peng.getKodCawangan().getKod());
                mohonKertas = plpService.findKertasByKod(idPermohonan, "RMN");

                if (mohonKertas != null) {
                    infoAudit = mohonKertas.getInfoAudit();
                    infoAudit.setDiKemaskiniOleh(peng);
                    infoAudit.setTarikhKemaskini(new java.util.Date());
                } else {
                    mohonKertas = new PermohonanKertas();
                    infoAudit.setDimasukOleh(peng);
                    infoAudit.setTarikhMasuk(new java.util.Date());

                }
                mohonKertas.setTajuk("Kertas Ringkas Rayuan");
                mohonKertas.setCawangan(cawangan);
                mohonKertas.setInfoAudit(infoAudit);
                mohonKertas.setPermohonan(permohonan);
                strService.simpanPermohonanKertas(mohonKertas);

                long a = mohonKertas.getIdKertas();
                List<PermohonanKertasKandungan> plk = strService.findByIdLapor(a, index);

                PermohonanKertasKandungan pLK = new PermohonanKertasKandungan();
                LOG.info("index :" + index + " kand :" + kand + " id_lapor :" + a);

                if (plk.isEmpty()) {
                    pLK.setSubtajuk("1");
                    LOG.info("PLK" + pLK.getSubtajuk());
                } else {
                    LOG.info("plk.size()  : " + plk.size());
                    LOG.info("sub tajuk : " + plk.get(plk.size() - 1).getSubtajuk());
                    pLK.setSubtajuk(String.valueOf(convertList(plk.get(plk.size() - 1).getSubtajuk())));
                }
                pLK.setBil((short) index);
                pLK.setKandungan(kand);
                pLK.setKertas(mohonKertas);
                pLK.setInfoAudit(infoAudit);
                pLK.setCawangan(cawangan);
                strService.simpanPermohonanKertasKandungan(pLK);
            }
        }

        getContext().getRequest().setAttribute("edit", Boolean.FALSE);
        getContext().getRequest().setAttribute("ptd", Boolean.TRUE);
        getContext().getRequest().setAttribute("keputusanPTD", Boolean.TRUE);
        rehydrate();
        addSimpleMessage("Maklumat Berjaya Dikemaskini");
        return new JSP("pelupusan/rayuan/kertas_ringkas_rayt.jsp").addParameter("tab", "true");
    }

    public Resolution deleteRowPerakuanPTD() throws ParseException {

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
        getContext().getRequest().setAttribute("edit", Boolean.FALSE);
        getContext().getRequest().setAttribute("ptd", Boolean.TRUE);
        getContext().getRequest().setAttribute("keputusanPTD", Boolean.TRUE);
        return new JSP("pelupusan/rayuan/kertas_ringkas_rayt.jsp").addParameter("tab", "true");
    }

    public void updateKandungan(int i, String kand) {

        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        Permohonan permohonan = permohonanDAO.findById(idPermohonan);
        Pengguna pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        InfoAudit infoAudit = new InfoAudit();
        KodCawangan cawangan = new KodCawangan();
        cawangan = kodCawanganDAO.findById(pengguna.getKodCawangan().getKod());
        mohonKertas = plpService.findKertasByKod(idPermohonan, "RMN");

        if (mohonKertas != null) {
            infoAudit = mohonKertas.getInfoAudit();
            infoAudit.setDiKemaskiniOleh(pengguna);
            infoAudit.setTarikhKemaskini(new java.util.Date());
        } else {
            mohonKertas = new PermohonanKertas();
            infoAudit.setDimasukOleh(pengguna);
            infoAudit.setTarikhMasuk(new java.util.Date());

        }
        mohonKertas.setTajuk("Kertas Ringkas Rayuan");
        mohonKertas.setCawangan(cawangan);
        mohonKertas.setInfoAudit(infoAudit);
        mohonKertas.setPermohonan(permohonan);
        strService.simpanPermohonanKertas(mohonKertas);

        long a = mohonKertas.getIdKertas();
        List<PermohonanKertasKandungan> plk = strService.findByIdLapor(a, i);

        PermohonanKertasKandungan pLK = new PermohonanKertasKandungan();
        LOG.info("index :" + i + " kand :" + kand + " id_lapor :" + a);

        if (plk.isEmpty()) {
            pLK.setSubtajuk("1");
            LOG.info("PLK" + pLK.getSubtajuk());
        } else {
            LOG.info("plk.size()  : " + plk.size());
            LOG.info("sub tajuk : " + plk.get(plk.size() - 1).getSubtajuk());
            pLK.setSubtajuk(String.valueOf(convertList(plk.get(plk.size() - 1).getSubtajuk())));
        }
        pLK.setBil((short) i);
        pLK.setKandungan(kand);
        pLK.setKertas(mohonKertas);
        pLK.setInfoAudit(infoAudit);
        pLK.setCawangan(cawangan);
        strService.simpanPermohonanKertasKandungan(pLK);

    }

    public Resolution kemaskiniTajuk() {
        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        Permohonan permohonan = permohonanDAO.findById(idPermohonan);
        String idPermohonanDahulu = permohonan.getPermohonanSebelum().getIdPermohonan();
//        idPermohonan = idPermohonanDahulu;
        mohonKertas = plpService.findKertasByKod(idPermohonan, "RMN");
        LOG.info("mohonKertas.getIdKertas : " + mohonKertas.getIdKertas());
        List<PermohonanKertasKandungan> plk0 = strService.findByIdLapor(mohonKertas.getIdKertas(), 0);
        Pengguna pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        InfoAudit infoAudit = new InfoAudit();
        KodCawangan cawangan = new KodCawangan();
        cawangan = kodCawanganDAO.findById(pengguna.getKodCawangan().getKod());
        PermohonanKertasKandungan kertasK0 = plk0.get(0);
        infoAudit = kertasK0.getInfoAudit();
        infoAudit.setDiKemaskiniOleh(pengguna);
        infoAudit.setTarikhKemaskini(new java.util.Date());
        kertasK0.setCawangan(cawangan);
        kertasK0.setInfoAudit(infoAudit);
        kertasK0.setKertas(mohonKertas);
        kertasK0.setBil(0);
        kertasK0.setSubtajuk("1");
        kertasK0.setKandungan(tajuk.toUpperCase());
        plpService.simpanPermohonanKertasKandungan(kertasK0);

        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        if (permohonan.getKodUrusan().getKod().equals("RAYT")) {
            getContext().getRequest().setAttribute("keputusanPTD", Boolean.TRUE);
        } else {
            getContext().getRequest().setAttribute("keputusanPTD", Boolean.FALSE);
        }
        addSimpleMessage("Maklumat Berjaya Disimpan");
        return new JSP("pelupusan/rayuan/kertas_ringkas_rayt.jsp").addParameter("tab", "true");
    }

    public Resolution kemaskiniTujuan() {
        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        Permohonan permohonan = permohonanDAO.findById(idPermohonan);
        // idPermohonan = permohonan.getPermohonanSebelum().getIdPermohonan();

        mohonKertas = plpService.findKertasByKod(idPermohonan, "RMN");

        List<PermohonanKertasKandungan> plk = strService.findByIdLapor(mohonKertas.getIdKertas(), 1);
        Pengguna pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        InfoAudit infoAudit = new InfoAudit();
        KodCawangan cawangan = new KodCawangan();
        cawangan = kodCawanganDAO.findById(pengguna.getKodCawangan().getKod());
        PermohonanKertasKandungan kertasK0 = plk.get(0);
        infoAudit = kertasK0.getInfoAudit();
        infoAudit.setDiKemaskiniOleh(pengguna);
        infoAudit.setTarikhKemaskini(new java.util.Date());
        kertasK0.setCawangan(cawangan);
        kertasK0.setInfoAudit(infoAudit);
        kertasK0.setKertas(mohonKertas);
        kertasK0.setBil(1);
        kertasK0.setSubtajuk("1");
        kertasK0.setKandungan(tujuan);
        plpService.simpanPermohonanKertasKandungan(kertasK0);
        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        if (permohonan.getKodUrusan().getKod().equals("RAYT")) {
            getContext().getRequest().setAttribute("keputusanPTD", Boolean.TRUE);
        } else {
            getContext().getRequest().setAttribute("keputusanPTD", Boolean.FALSE);
        }
        addSimpleMessage("Maklumat Berjaya Disimpan");
        return new JSP("pelupusan/rayuan/kertas_ringkas_rayt.jsp").addParameter("tab", "true");
    }

    public Resolution kemaskiniPTG() {
        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        Permohonan permohonan = permohonanDAO.findById(idPermohonan);

        mohonKertas = plpService.findKertasByKod(idPermohonan, "RMN");

        List<PermohonanKertasKandungan> plk = strService.findByIdLapor(mohonKertas.getIdKertas(), 5);
        Pengguna pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        InfoAudit infoAudit = new InfoAudit();
        KodCawangan cawangan = new KodCawangan();
        cawangan = kodCawanganDAO.findById(pengguna.getKodCawangan().getKod());
        PermohonanKertasKandungan kertasK0 = plk.get(0);
        infoAudit = kertasK0.getInfoAudit();
        infoAudit.setDiKemaskiniOleh(pengguna);
        infoAudit.setTarikhKemaskini(new java.util.Date());
        kertasK0.setCawangan(cawangan);
        kertasK0.setInfoAudit(infoAudit);
        kertasK0.setKertas(mohonKertas);
        kertasK0.setBil(5);
        kertasK0.setSubtajuk("1");
        kertasK0.setKandungan(perakuanPTG);
        plpService.simpanPermohonanKertasKandungan(kertasK0);

        getContext().getRequest().setAttribute("view", Boolean.FALSE);
        getContext().getRequest().setAttribute("keputusan", Boolean.TRUE);
        getContext().getRequest().setAttribute("ptg", Boolean.TRUE);
        addSimpleMessage("Maklumat Berjaya Disimpan");
        return new JSP("pelupusan/rayuan/kertas_ringkas_rayt.jsp").addParameter("tab", "true");
    }

    public Resolution kemaskiniRayuan() {
        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        Permohonan permohonan = permohonanDAO.findById(idPermohonan);

        mohonKertas = plpService.findKertasByKod(idPermohonan, "RMN");

        List<PermohonanKertasKandungan> plk = strService.findByIdLapor(mohonKertas.getIdKertas(), 3);
        Pengguna pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        InfoAudit infoAudit = new InfoAudit();
        KodCawangan cawangan = new KodCawangan();
        cawangan = kodCawanganDAO.findById(pengguna.getKodCawangan().getKod());
        PermohonanKertasKandungan kertasK0 = plk.get(0);
        infoAudit = kertasK0.getInfoAudit();
        infoAudit.setDiKemaskiniOleh(pengguna);
        infoAudit.setTarikhKemaskini(new java.util.Date());
        kertasK0.setCawangan(cawangan);
        kertasK0.setInfoAudit(infoAudit);
        kertasK0.setKertas(mohonKertas);
        kertasK0.setBil(3);
        kertasK0.setSubtajuk("1");
        kertasK0.setKandungan(perihalRayuan);
        plpService.simpanPermohonanKertasKandungan(kertasK0);
        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        if (permohonan.getKodUrusan().getKod().equals("RAYT")) {
            getContext().getRequest().setAttribute("keputusanPTD", Boolean.TRUE);
        } else {
            getContext().getRequest().setAttribute("keputusanPTD", Boolean.FALSE);
        }
        addSimpleMessage("Maklumat Berjaya Disimpan");
        return new JSP("pelupusan/rayuan/kertas_ringkas_rayt.jsp").addParameter("tab", "true");
    }

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {

        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = permohonanDAO.findById(idPermohonan);

        if (permohonan.getPermohonanSebelum() != null) {
            idPermohonanSebelum = permohonan.getPermohonanSebelum().getIdPermohonan();
            // idPermohonan = idPermohonanSebelum;
            if (idPermohonanSebelum != null) {
                hakmilikPermohonan = plpService.findByIdPermohonan(idPermohonanSebelum);
                pemohon = plpService.findPemohonByIdPemohon(idPermohonanSebelum);
                pihak = pemohon.getPihak();
            }
        }

        mohonKertas = plpService.findKertasByKod(idPermohonan, "RMN");
        PermohonanKertas mohonKertasSebelum = new PermohonanKertas();
        LOG.info(" idPermohonanSebelum " + idPermohonanSebelum + " idPermohonan :" + idPermohonan);
        if (mohonKertas != null) {
            LOG.info(" imohonKertas != null ");
            LOG.info("mohonKertas permohonan:" + mohonKertas.getTajuk());
            latarBelakangList = strService.findByIdLapor(mohonKertas.getIdKertas(), 2);
            rayuanList = strService.findByIdLapor(mohonKertas.getIdKertas(), 7);
            ;
            listKertasPerakuanPTD = strService.findByIdLapor(mohonKertas.getIdKertas(), 4);
            listKertasPerakuanPTG = strService.findByIdLapor(mohonKertas.getIdKertas(), 6);
        } else {
            mohonKertas = new PermohonanKertas();
            mohonKertasSebelum = plpService.findKertasByKod(idPermohonanSebelum, "RMN");
            Pengguna pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
            InfoAudit infoAudit = new InfoAudit();
            KodCawangan cawangan = new KodCawangan();
            cawangan = kodCawanganDAO.findById(pengguna.getKodCawangan().getKod());
            infoAudit.setDimasukOleh(pengguna);
            infoAudit.setTarikhMasuk(new java.util.Date());

            mohonKertas.setTajuk("Kertas Rayuan");
            KodDokumen kodDokumen = kodDokumenDAO.findById("RMN");
            mohonKertas.setKodDokumen(kodDokumen);
            mohonKertas.setCawangan(cawangan);
            mohonKertas.setInfoAudit(infoAudit);
            mohonKertas.setPermohonan(permohonan);
            strService.simpanPermohonanKertas(mohonKertas);

            LOG.info(" imohonKertas != null ");
            LOG.info("mohonKertas permohonan sebelum:" + mohonKertasSebelum.getTajuk() + " permohonan : " + mohonKertasSebelum.getPermohonan().getIdPermohonan());

            latarBelakangListSebelum = strService.findByIdLapor(mohonKertasSebelum.getIdKertas(), 2);
            if (latarBelakangListSebelum != null) {
                for (PermohonanKertasKandungan pkk : latarBelakangListSebelum) {
                    PermohonanKertasKandungan pkknew = new PermohonanKertasKandungan();
                    pkknew.setKertas(mohonKertas);
                    pkknew.setBil(2);
                    pkknew.setSubtajuk(pkk.getSubtajuk());
                    pkknew.setKandungan(pkk.getKandungan());
                    LOG.info("pkk.getKandungan() : " + pkk.getKandungan());
                    pkknew.setCawangan(cawangan);
                    pkknew.setInfoAudit(infoAudit);

                    plpService.simpanPermohonanKertasKandungan(pkknew);
                }
            } else {
                LOG.info("latarBelakangListSebelum is null");
            }

            rayuanListSebelum = strService.findByIdLapor(mohonKertasSebelum.getIdKertas(), 7);
            if (rayuanListSebelum != null) {
                if (rayuanListSebelum != null) {
                    for (PermohonanKertasKandungan pkk : rayuanListSebelum) {
                        PermohonanKertasKandungan pkknew = new PermohonanKertasKandungan();
                        pkknew.setKertas(mohonKertas);
                        pkknew.setBil(7);
                        pkknew.setSubtajuk(pkk.getSubtajuk());
                        pkknew.setKandungan(pkk.getKandungan());
                        LOG.info("pkk.getKandungan() : " + pkk.getKandungan());
                        pkknew.setCawangan(cawangan);
                        pkknew.setInfoAudit(infoAudit);
                        plpService.simpanPermohonanKertasKandungan(pkknew);
                    }
                }
            } else {
                LOG.info("rayuanListSebelum is null");
            }

            listKertasPerakuanPTDSebelum = strService.findByIdLapor(mohonKertasSebelum.getIdKertas(), 4);
            if (listKertasPerakuanPTDSebelum != null) {
                if (listKertasPerakuanPTDSebelum != null) {
                    for (PermohonanKertasKandungan pkk : listKertasPerakuanPTDSebelum) {
                        PermohonanKertasKandungan pkknew = new PermohonanKertasKandungan();
                        pkknew.setKertas(mohonKertas);
                        pkknew.setBil(4);
                        pkknew.setSubtajuk(pkk.getSubtajuk());
                        pkknew.setKandungan(pkk.getKandungan());
                        LOG.info("pkk.getKandungan() : " + pkk.getKandungan());
                        pkknew.setCawangan(cawangan);
                        pkknew.setInfoAudit(infoAudit);

                        plpService.simpanPermohonanKertasKandungan(pkknew);
                    }
                }
            } else {
                LOG.info("rayuanListSebelum is null");
            }

            listKertasPerakuanPTGSebelum = strService.findByIdLapor(mohonKertasSebelum.getIdKertas(), 6);
            if (listKertasPerakuanPTGSebelum != null) {
                if (listKertasPerakuanPTGSebelum != null) {
                    for (PermohonanKertasKandungan pkk : listKertasPerakuanPTGSebelum) {
                        PermohonanKertasKandungan pkknew = new PermohonanKertasKandungan();
                        pkknew.setKertas(mohonKertas);
                        pkknew.setBil(6);
                        pkknew.setSubtajuk(pkk.getSubtajuk());
                        pkknew.setKandungan(pkk.getKandungan());
                        LOG.info("pkk.getKandungan() : " + pkk.getKandungan());
                        pkknew.setCawangan(cawangan);
                        pkknew.setInfoAudit(infoAudit);
                        plpService.simpanPermohonanKertasKandungan(pkknew);
                    }
                }
            } else {
                LOG.info("rayuanListSebelum is null");
            }

            latarBelakangList = strService.findByIdLapor(mohonKertas.getIdKertas(), 2);
            rayuanList = strService.findByIdLapor(mohonKertas.getIdKertas(), 7);
            listKertasPerakuanPTD = strService.findByIdLapor(mohonKertas.getIdKertas(), 4);
            listKertasPerakuanPTG = strService.findByIdLapor(mohonKertas.getIdKertas(), 6);
        }

        //Untuk tujuan
        String noLot = "";
        String luas = "";
        String koduom = "";
        String bpm = "";
        String daerah = "";
        String ktanah = "";
        tujuan = " ";
        if (hakmilikPermohonan.getNoLot() != null) {
            noLot = " Lot " + hakmilikPermohonan.getNoLot();
        }
        if (hakmilikPermohonan.getLuasTerlibat() != null) {
            luas = "Seluas " + hakmilikPermohonan.getLuasTerlibat().toString() + " " + hakmilikPermohonan.getKodUnitLuas().getNama();
        }
        if (hakmilikPermohonan.getKodUnitLuas() != null) {
            koduom = hakmilikPermohonan.getKodUnitLuas().getNama();
        }
        if (hakmilikPermohonan.getBandarPekanMukimBaru() != null) {
            bpm = pelupusanUtiliti.convertStringtoInitCap(hakmilikPermohonan.getBandarPekanMukimBaru().getNama());
        }
        if (hakmilikPermohonan.getKategoriTanahBaru() != null) {
            ktanah = hakmilikPermohonan.getKategoriTanahBaru().getNama();
        }
        if (permohonan.getCawangan() != null) {
            daerah = pelupusanUtiliti.convertStringtoInitCap(permohonan.getCawangan().getDaerah().getNama());
        }
        //Untuk Tanah
        String kodMilik = "";
        String lokasi = "";
        String and = "";
        perihalTanah = " ";
        if (hakmilikPermohonan.getKodMilik() != null) {
            kodMilik = " tanah " + hakmilikPermohonan.getKodMilik().getNama();
        }
        if (hakmilikPermohonan.getLokasi() != null) {
            lokasi = " terletak di " + pelupusanUtiliti.convertStringtoInitCap(hakmilikPermohonan.getLokasi());
        }
        if (!kodMilik.equals("") && !lokasi.equals("")) {
            and = " dan ";
        }
        //Untuk Pemohon
        String kerja = "";
        String nama = "";
        String noP = "";
        String warganegara = "";
        String umurTangungan = "";
        perihalPemohon = " ";
        if (pihak.getNama() != null) {
            nama = pelupusanUtiliti.convertStringtoInitCap(pihak.getNama());
        }
        if (pihak.getNoPengenalan() != null) {
            noP = " No. KP: " + pihak.getNoPengenalan();
        }
        if (pihak.getWargaNegara() != null) {
            warganegara = " adalah seorang warganegara " + pihak.getWargaNegara().getNama();
        }
        if (pemohon.getUmur() != null && pemohon.getTanggungan() != null) {
            umurTangungan = ".Pemohon berumur " + pemohon.getUmur()
                    + " tahun dan mempunyai " + pemohon.getTanggungan() + " tanggungan.";
        }
        if (pemohon.getPekerjaan() != null && pemohon.getPendapatan() != null) {
            kerja = ".Pekerjaan pemohon adalah sebagai " + pelupusanUtiliti.convertStringtoInitCap(pemohon.getPekerjaan()) + " dengan pendapatan sebanyak RM "
                    + pemohon.getPendapatan() + " sebulan ";
        }

        //Rayuan PTD
//      LOG.info("Mohon Kertas adalah " + mohonKertas.getTajuk());
        if (mohonKertas.getTajuk() != null) {
            if (permohonan.getKodUrusan().getKod().equals("RAYT")) {
                tujuan = "Kertas ini disediakan adalah untuk mendapat pertimbangan Majlis Mesyuarat Kerajaan Negeri terhadap permohonan daripada " + nama + " untuk "
                        + permohonan.getKodUrusan().getNama() + " ke atas penolakan Pemberimilikan Tanah Kerajaan " + luas + lokasi + ", " + bpm + ", " + daerah + " untuk tujuan " + ktanah;

                tajuk = "RAYUAN ATAS PENOLAKAN PEMBERIMILIKAN TANAH KERAJAAN " + luas.toUpperCase() + " DI " + lokasi.toUpperCase()
                        + ", " + bpm.toUpperCase() + ", " + daerah.toUpperCase() + " UNTUK TUJUAN " + ktanah.toUpperCase();
            }

            Pengguna pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
            InfoAudit infoAudit = new InfoAudit();
            KodCawangan cawangan = new KodCawangan();
            cawangan = kodCawanganDAO.findById(pengguna.getKodCawangan().getKod());

            if (mohonKertas != null) {
                infoAudit = mohonKertas.getInfoAudit();
                infoAudit.setDiKemaskiniOleh(pengguna);
                infoAudit.setTarikhKemaskini(new java.util.Date());
            } else {
                mohonKertas = new PermohonanKertas();
                infoAudit.setDimasukOleh(pengguna);
                infoAudit.setTarikhMasuk(new java.util.Date());

            }
//            mohonKertas.setTajuk("Kertas Rayuan");
//            KodDokumen kodDokumen = kodDokumenDAO.findById("RMN");
//            mohonKertas.setKodDokumen(kodDokumen);
//            mohonKertas.setCawangan(cawangan);
//            mohonKertas.setInfoAudit(infoAudit);
//            mohonKertas.setPermohonan(permohonan);
//            strService.simpanPermohonanKertas(mohonKertas);

            List<PermohonanKertasKandungan> plk0 = strService.findByIdLapor(mohonKertas.getIdKertas(), 0);
            List<PermohonanKertasKandungan> plk = strService.findByIdLapor(mohonKertas.getIdKertas(), 1);
            List<PermohonanKertasKandungan> plk1 = strService.findByIdLapor(mohonKertas.getIdKertas(), 5);
            List<PermohonanKertasKandungan> plk2Rayuan = strService.findByIdLapor(mohonKertas.getIdKertas(), 3);

            if (plk0.isEmpty()) {
                PermohonanKertasKandungan kertasK0 = new PermohonanKertasKandungan();
                kertasK0.setCawangan(cawangan);
                kertasK0.setInfoAudit(infoAudit);
                kertasK0.setKertas(mohonKertas);
                kertasK0.setBil(0);
                kertasK0.setSubtajuk("1");
                kertasK0.setKandungan(tajuk);
                plpService.simpanPermohonanKertasKandungan(kertasK0);
            } else {
                tajuk = plk0.get(0).getKandungan();
            }
            if (plk.isEmpty()) {
                PermohonanKertasKandungan kertasK1 = new PermohonanKertasKandungan();
                kertasK1.setCawangan(cawangan);
                kertasK1.setInfoAudit(infoAudit);
                kertasK1.setKertas(mohonKertas);
                kertasK1.setBil(1);
                kertasK1.setSubtajuk("1");
                kertasK1.setKandungan(tujuan);
                plpService.simpanPermohonanKertasKandungan(kertasK1);
            } else {
                tujuan = plk.get(0).getKandungan();
            }
            if (plk1.isEmpty()) {
                PermohonanKertasKandungan kertasK2 = new PermohonanKertasKandungan();
                kertasK2.setCawangan(cawangan);
                kertasK2.setInfoAudit(infoAudit);
                kertasK2.setKertas(mohonKertas);
                kertasK2.setBil(5);
                kertasK2.setSubtajuk("1");
                perakuanPTG = "Pengarah Tanah dan Galian setelah meneliti permohonan ini dan memperakukan seperti berikut : ";
                kertasK2.setKandungan(perakuanPTG);
                plpService.simpanPermohonanKertasKandungan(kertasK2);
            } else {
                perakuanPTG = plk1.get(0).getKandungan();
            }
            if (plk2Rayuan.isEmpty()) {
                PermohonanKertasKandungan kertasK3 = new PermohonanKertasKandungan();
                kertasK3.setCawangan(cawangan);
                kertasK3.setInfoAudit(infoAudit);
                kertasK3.setKertas(mohonKertas);
                kertasK3.setBil(3);
                kertasK3.setSubtajuk("1");
                String daerahPTD = "";
                if (cawangan.getDaerah() != null) {
                    daerahPTD = " " + pelupusanUtiliti.convertStringtoInitCap(cawangan.getDaerah().getNama()) + " ";
                }
                perihalRayuan = "Pentadbir Tanah" + daerahPTD + " telah menerima surat rayuan pemohon pada " + sdf.format(permohonan.getInfoAudit().getTarikhMasuk()) + " dengan alasan : " + permohonan.getSebab();
                kertasK3.setKandungan(perihalRayuan);
                plpService.simpanPermohonanKertasKandungan(kertasK3);
            } else {
                perihalRayuan = plk2Rayuan.get(0).getKandungan();
            }
        }

    }

    public List<PermohonanKertasKandungan> getListKertasPerakuanPTG() {
        return listKertasPerakuanPTG;
    }

    public void setListKertasPerakuanPTG(List<PermohonanKertasKandungan> listKertasPerakuanPTG) {
        this.listKertasPerakuanPTG = listKertasPerakuanPTG;
    }

    public PermohonanKertas getMohonKertas() {
        return mohonKertas;
    }

    public void setMohonKertas(PermohonanKertas mohonKertas) {
        this.mohonKertas = mohonKertas;
    }

    public String getIdPermohonan() {
        return idPermohonan;
    }

    public void setIdPermohonan(String idPermohonan) {
        this.idPermohonan = idPermohonan;
    }

    public Permohonan getPermohonan() {
        return permohonan;
    }

    public void setPermohonan(Permohonan permohonan) {
        this.permohonan = permohonan;
    }

    public Pemohon getPemohon() {
        return pemohon;
    }

    public void setPemohon(Pemohon pemohon) {
        this.pemohon = pemohon;
    }

    public HakmilikPermohonan getHakmilikPermohonan() {
        return hakmilikPermohonan;
    }

    public void setHakmilikPermohonan(HakmilikPermohonan hakmilikPermohonan) {
        this.hakmilikPermohonan = hakmilikPermohonan;
    }

    public String getIdPermohonanSebelum() {
        return idPermohonanSebelum;
    }

    public void setIdPermohonanSebelum(String idPermohonanSebelum) {
        this.idPermohonanSebelum = idPermohonanSebelum;
    }

    public Pihak getPihak() {
        return pihak;
    }

    public void setPihak(Pihak pihak) {
        this.pihak = pihak;
    }

    public String getPerihalPemohon() {
        return perihalPemohon;
    }

    public void setPerihalPemohon(String perihalPemohon) {
        this.perihalPemohon = perihalPemohon;
    }

    public String getPerihalTanah() {
        return perihalTanah;
    }

    public void setPerihalTanah(String perihalTanah) {
        this.perihalTanah = perihalTanah;
    }

    public String getTujuan() {
        return tujuan;
    }

    public void setTujuan(String tujuan) {
        this.tujuan = tujuan;
    }

    public List<PermohonanKertasKandungan> getLatarBelakangList() {
        return latarBelakangList;
    }

    public void setLatarBelakangList(List<PermohonanKertasKandungan> latarBelakangList) {
        this.latarBelakangList = latarBelakangList;
    }

    public String getTajuk() {
        return tajuk;
    }

    public void setTajuk(String tajuk) {
        this.tajuk = tajuk;
    }

    public String getBilMesy() {
        return bilMesy;
    }

    public void setBilMesy(String bilMesy) {
        this.bilMesy = bilMesy;
    }

    public Date getTarikhSidang() {
        return tarikhSidang;
    }

    public void setTarikhSidang(Date tarikhSidang) {
        this.tarikhSidang = tarikhSidang;
    }

    public PelupusanUtiliti getPelupusanUtiliti() {
        return pelupusanUtiliti;
    }

    public void setPelupusanUtiliti(PelupusanUtiliti pelupusanUtiliti) {
        this.pelupusanUtiliti = pelupusanUtiliti;
    }

    public List<PermohonanKertasKandungan> getRayuanList() {
        return rayuanList;
    }

    public void setRayuanList(List<PermohonanKertasKandungan> rayuanList) {
        this.rayuanList = rayuanList;
    }

    public List<PermohonanKertasKandungan> getListKertasPerakuanPTD() {
        return listKertasPerakuanPTD;
    }

    public void setListKertasPerakuanPTD(List<PermohonanKertasKandungan> listKertasPerakuanPTD) {
        this.listKertasPerakuanPTD = listKertasPerakuanPTD;
    }

    public String getPerakuanPTG() {
        return perakuanPTG;
    }

    public void setPerakuanPTG(String perakuanPTG) {
        this.perakuanPTG = perakuanPTG;
    }

    public String getPerihalRayuan() {
        return perihalRayuan;
    }

    public void setPerihalRayuan(String perihalRayuan) {
        this.perihalRayuan = perihalRayuan;
    }

    public SimpleDateFormat getSdf() {
        return sdf;
    }

    public void setSdf(SimpleDateFormat sdf) {
        this.sdf = sdf;
    }

    public String convertList(String source) {

        int indexBackSlash = source.lastIndexOf("."); //indexBackSlash
        String value = source.substring(indexBackSlash + 1);
        int intValue = new Integer(value).intValue() + 1;
        String tambah = new Integer(intValue).toString();
        String asal = source.substring(0, indexBackSlash + 1);
        return asal + tambah;

    }
}
