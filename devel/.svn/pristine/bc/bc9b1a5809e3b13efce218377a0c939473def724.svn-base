/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.daftar;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.dao.KodJenisPengenalanDAO;
import etanah.dao.PermohonanDAO;
import etanah.dao.PihakDAO;
import etanah.dao.WakilKuasaPemberiDAO;
import etanah.dao.WakilKuasaPenerimaDAO;
import etanah.model.InfoAudit;
import etanah.model.KodJenisPengenalan;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.model.Pihak;
import etanah.model.WakilKuasa;
import etanah.model.WakilKuasaPemberi;
import etanah.model.WakilKuasaPenerima;
import etanah.service.common.PihakService;
import etanah.service.daftar.PendaftaranSuratKuasaService;
import etanah.view.etanahActionBeanContext;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.RedirectResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

/**
 *
 * @author faidzal
 */
@UrlBinding("/daftar/suratwakilkuasa")
public class SuratWakilKuasaActionBean extends AbleActionBean {

    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    KodJenisPengenalanDAO kodJenisPengenalanDAO;
    @Inject
    PendaftaranSuratKuasaService suratkuasaService;
    @Inject
    PihakService pihakService;
    @Inject
    PihakDAO pihakDAO;
    @Inject
    WakilKuasaPemberiDAO wakilKuasaPemberiDAO;
    @Inject
    WakilKuasaPenerimaDAO wakilKuasaPenerimaDAO;
    List<WakilKuasa> wKuasa = new ArrayList();
    List<Pihak> pihakList = new ArrayList<Pihak>();
    String idPermohonan = new String();
    Pengguna peng = new Pengguna();
    Permohonan mohon = new Permohonan();
    WakilKuasa wakilKuasa = new WakilKuasa();
    WakilKuasa wakilKuasa2 = new WakilKuasa();
    WakilKuasaPemberi wakilKuasaPemberi = new WakilKuasaPemberi();
    WakilKuasaPenerima wakilKuasaPenerima = new WakilKuasaPenerima();
    Pihak pihak = new Pihak();
    InfoAudit info = new InfoAudit();
    private static final Logger LOG = Logger.getLogger(SuratWakilKuasaActionBean.class);
    private boolean notSW = false;
    private boolean readOnly = false;
    private boolean idSblm = false;
    private String[] NOT_SW = {"SA", "SB"};
    private String[] URUSAN_READ_ONLY = {"SWDB", "SBD"};
    private String[] URUSAN_USE_MOHON_SBLM = {"SWDB"};
    private String nama;
    private String noKp;
    private String kodKp;
    private String amaunMaksima;
    private String syaratTambahan;
    private String jPihak;
    private String chkboxpihak;
    private String idPenerima;
    private String idPemberi;
    String idPihak;
    private int jumPemberi = 0;
    private int jumPenerima = 0;

    @DefaultHandler
    public Resolution maklumatPemPen2() {
        String message = getContext().getRequest().getParameter("message");
        String error = getContext().getRequest().getParameter("error");
        if (StringUtils.isNotBlank(message)) {
            addSimpleMessage(message);
        }
        if (StringUtils.isNotBlank(error)) {
            addSimpleError(error);
        }

        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        peng = (Pengguna) context.getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        mohon = permohonanDAO.findById(idPermohonan);
        validateUrusan(mohon);
        if (idSblm) {
            wKuasa = suratkuasaService.findWakilKuasaList(mohon.getPermohonanSebelum().getIdPermohonan());
        } else {
            wKuasa = suratkuasaService.findWakilKuasaList(idPermohonan);
        }



        return new JSP("daftar/maklumat_pemberi_penerima_suratkuasa_wakilv2.jsp").addParameter("tab", "true");
    }

    public void validateUrusan(Permohonan mohon) {
        if (ArrayUtils.contains(NOT_SW, mohon.getKodUrusan().getKod())) {
            notSW = true;
        } else {
            notSW = false;
        }

        if (ArrayUtils.contains(URUSAN_READ_ONLY, mohon.getKodUrusan().getKod())) {
            readOnly = true;
        } else {
            readOnly = false;
        }
        if (ArrayUtils.contains(URUSAN_USE_MOHON_SBLM, mohon.getKodUrusan().getKod())) {
            idSblm = true;
        } else {
            idSblm = false;
        }

    }

    public Resolution tambahPemberiPenerimaV2() {
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        peng = (Pengguna) context.getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        String msg = "";
        wakilKuasa = suratkuasaService.findWakilKuasa(idPermohonan);
        Pihak pihak = new Pihak();
        KodJenisPengenalan jp = new KodJenisPengenalan();
        info = new InfoAudit();
        info.setDimasukOleh(peng);
        info.setTarikhMasuk(new Date());
        if (wakilKuasa != null) {
            wakilKuasa.setTempohBulan(jumPenerima);
            wakilKuasa.setTempohHari(jumPemberi);
            suratkuasaService.saveWakilKuasa(wakilKuasa);
        } else {
            wakilKuasa = new WakilKuasa();
            wakilKuasa.setIdWakil(idPermohonan);
            wakilKuasa.setPermohonan(permohonanDAO.findById(idPermohonan));
//            wakilKuasa.setAktif('T');
            wakilKuasa.setAktif('Y');
            wakilKuasa.setInfoAudit(info);
            wakilKuasa.setCawangan(permohonanDAO.findById(idPermohonan).getCawangan());
            wakilKuasa.setKuasaAm('T');
            wakilKuasa.setKuasaGadai('T');
            wakilKuasa.setKuasaKaveat('T');
            wakilKuasa.setKuasaLepasGadai('T');
            wakilKuasa.setKuasaLepasKaveat('T');
            wakilKuasa.setKuasaPajak('T');
            wakilKuasa.setKuasaPajakKecil('T');
            wakilKuasa.setKuasaPindahMilik('T');
            wakilKuasa.setKuasaSewa('T');
            wakilKuasa.setKuasaTarikKaveat('T');
            wakilKuasa.setUntukSemuaHakmilik('T');
            wakilKuasa.setTempohBulan(jumPenerima);
            wakilKuasa.setTempohHari(jumPemberi);
            suratkuasaService.saveWakilKuasa(wakilKuasa);
        }
        List<WakilKuasaPenerima> penerima = suratkuasaService.findWakilKuasaListPenerima(wakilKuasa.getIdWakil());
        List<WakilKuasaPemberi> pemberi = suratkuasaService.findWakilKuasaListPemberi(wakilKuasa.getIdWakil());
        if (jPihak.equals("pemberi")) {
            if (pemberi.size() < wakilKuasa.getTempohHari()) {
                pihak = new Pihak();
                pihak.setInfoAudit(info);
                LOG.info("Nama Pemberi::::::" + nama);
                pihak.setNama(nama);
                LOG.info("No Pengenalan Pemberi::::::" + noKp);
                pihak.setNoPengenalan(noKp);
                LOG.info("Jenis Pengenalan Pemberi::::::" + kodKp);
                jp = kodJenisPengenalanDAO.findById(kodKp);
                pihak.setJenisPengenalan(jp);
                suratkuasaService.savePihak(pihak);
                wakilKuasaPemberi = new WakilKuasaPemberi();
                wakilKuasaPemberi.setInfoAudit(info);
                wakilKuasaPemberi.setPihak(pihak);
                wakilKuasaPemberi.setCawangan(peng.getKodCawangan());
                wakilKuasaPemberi.setWakilKuasa(wakilKuasa);
                suratkuasaService.saveWakilKuasaPemberi(wakilKuasaPemberi);
                addSimpleMessage("Maklumat berjaya disimpan.");
            } else {
                msg = "Maklumat " + jPihak + " telah maksimum.";
            }
        } else {
            if (penerima.size() < wakilKuasa.getTempohBulan()) {
                wakilKuasaPenerima = new WakilKuasaPenerima();
                wakilKuasaPenerima.setInfoAudit(info);
                wakilKuasaPenerima.setCawangan(peng.getKodCawangan());
                wakilKuasaPenerima.setWakilKuasa(wakilKuasa);
                LOG.info("========namaPenerima=========" + nama);
                wakilKuasaPenerima.setNama(nama);
                jp = kodJenisPengenalanDAO.findById(kodKp);
                wakilKuasaPenerima.setJenisPengenalan(jp);
                LOG.info("========noPengenalanPenerima=========" + noKp);
                wakilKuasaPenerima.setNoPengenalan(noKp);
                wakilKuasaPenerima.setStatus("A");
                if (StringUtils.isNotBlank(amaunMaksima)) {
                    LOG.info("========Amaun Maksima=========" + amaunMaksima);
                    BigDecimal bd = new BigDecimal(amaunMaksima);
                    wakilKuasaPenerima.setAmaunMaksima(bd);
                }
                if (StringUtils.isNotBlank(syaratTambahan)) {
                    LOG.info("========Syarat Tambahan=========" + syaratTambahan);
                    wakilKuasa.setSyaratTambahan(syaratTambahan);
                    suratkuasaService.saveOrUpdateWakilKuasa(wakilKuasa);
                }

                suratkuasaService.saveOrUpdateWakilKuasaPenerima(wakilKuasaPenerima);
                addSimpleMessage("Maklumat berjaya disimpan.");
            } else {
                msg = "Maklumat " + jPihak + " telah maksimum.";
            }
        }
        rehydrate();
        nama = "";
        kodKp = "";
        noKp = "";
        amaunMaksima = "";
        syaratTambahan = "";

        wKuasa = suratkuasaService.findWakilKuasaList(idPermohonan);

        return new RedirectResolution(SuratWakilKuasaActionBean.class, "maklumatPemPen2").addParameter("error", msg);
//        return new JSP("daftar/maklumat_pemberi_penerima_suratkuasa_wakilv2.jsp").addParameter("tab", "true");
    }

    public Resolution cariPihak() {
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        peng = (Pengguna) context.getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        wakilKuasa = suratkuasaService.findWakilKuasa(idPermohonan);
        KodJenisPengenalan jp = new KodJenisPengenalan();
        info = new InfoAudit();
        info.setDimasukOleh(peng);
        info.setTarikhMasuk(new Date());
        if (wakilKuasa == null) {
            wakilKuasa = new WakilKuasa();
            wakilKuasa.setIdWakil(idPermohonan);
            wakilKuasa.setPermohonan(permohonanDAO.findById(idPermohonan));
//            wakilKuasa.setAktif('T');
            wakilKuasa.setAktif('Y');
            wakilKuasa.setInfoAudit(info);
            wakilKuasa.setCawangan(permohonanDAO.findById(idPermohonan).getCawangan());
            wakilKuasa.setAktif('T');
            wakilKuasa.setKuasaAm('T');
            wakilKuasa.setKuasaGadai('T');
            wakilKuasa.setKuasaKaveat('T');
            wakilKuasa.setKuasaLepasGadai('T');
            wakilKuasa.setKuasaLepasKaveat('T');
            wakilKuasa.setKuasaPajak('T');
            wakilKuasa.setKuasaPajakKecil('T');
            wakilKuasa.setKuasaPindahMilik('T');
            wakilKuasa.setKuasaSewa('T');
            wakilKuasa.setKuasaTarikKaveat('T');
            wakilKuasa.setUntukSemuaHakmilik('T');
            wakilKuasa.setTempohBulan(jumPenerima);
            wakilKuasa.setTempohHari(jumPemberi);
            suratkuasaService.saveWakilKuasa(wakilKuasa);
        } else {
            wakilKuasa.setTempohBulan(jumPenerima);
            wakilKuasa.setTempohHari(jumPemberi);
            suratkuasaService.saveWakilKuasa(wakilKuasa);
        }
        LOG.info("Stage 1sssssssssssssssssssssss");
        if ((noKp != null) && (kodKp != null) && (nama == null)) {
            LOG.info("Stage 1");
            pihakList = pihakService.findPihakBynoKPkodKP(kodKp, noKp);
        } else if ((noKp == null) && (kodKp != null) && (nama != null)) {
            pihakList = pihakService.findPihakByName(nama, null);
        } else if ((nama != null)) {
            pihakList = pihakService.findPihakByName(nama, null);
        }
        return new JSP("daftar/popUpCarianPihakWakilKuasa.jsp").addParameter("popup", "true");
    }

    public Resolution simpanByPihak() {
        String msg = "";
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        peng = (Pengguna) context.getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        wakilKuasa = suratkuasaService.findWakilKuasa(idPermohonan);
        KodJenisPengenalan jp = new KodJenisPengenalan();
        info = new InfoAudit();
        info.setDimasukOleh(peng);
        info.setTarikhMasuk(new Date());
        if (wakilKuasa == null) {
            wakilKuasa = new WakilKuasa();
            wakilKuasa.setIdWakil(idPermohonan);
            wakilKuasa.setPermohonan(permohonanDAO.findById(idPermohonan));
//            wakilKuasa.setAktif('T');
            wakilKuasa.setAktif('Y');
            wakilKuasa.setInfoAudit(info);
            wakilKuasa.setCawangan(permohonanDAO.findById(idPermohonan).getCawangan());
            wakilKuasa.setAktif('T');
            wakilKuasa.setKuasaAm('T');
            wakilKuasa.setKuasaGadai('T');
            wakilKuasa.setKuasaKaveat('T');
            wakilKuasa.setKuasaLepasGadai('T');
            wakilKuasa.setKuasaLepasKaveat('T');
            wakilKuasa.setKuasaPajak('T');
            wakilKuasa.setKuasaPajakKecil('T');
            wakilKuasa.setKuasaPindahMilik('T');
            wakilKuasa.setKuasaSewa('T');
            wakilKuasa.setKuasaTarikKaveat('T');
            wakilKuasa.setUntukSemuaHakmilik('T');
            suratkuasaService.saveWakilKuasa(wakilKuasa);
        }
        List<WakilKuasaPenerima> penerima = suratkuasaService.findWakilKuasaListPenerima(wakilKuasa.getIdWakil());
        List<WakilKuasaPemberi> pemberi = suratkuasaService.findWakilKuasaListPemberi(wakilKuasa.getIdWakil());
        if (StringUtils.isNotEmpty(jPihak)) {
            Pihak pihak = new Pihak();
            if (StringUtils.isNotEmpty(chkboxpihak)) {
                pihak = pihakDAO.findById(Long.parseLong(chkboxpihak));
            }
            int size = 0;
            int s = 0;

            if (jPihak.equalsIgnoreCase("pemberi")) {
                size = pemberi.size();
                s = wakilKuasa.getTempohHari();
                if (pemberi.size() < wakilKuasa.getTempohHari()) {
                    LOG.info("SIZE PENERIMA :::" + size);
                    WakilKuasaPemberi wkb = new WakilKuasaPemberi();
                    wkb.setInfoAudit(info);
                    wkb.setPihak(pihak);
                    wkb.setCawangan(peng.getKodCawangan());
                    wkb.setWakilKuasa(wakilKuasa);
                    suratkuasaService.saveWakilKuasaPemberi(wkb);
                } else {
                    msg = "Maklumat " + jPihak + " telah maksimum.";
                }
            } else {
                if (penerima.size() < wakilKuasa.getTempohBulan()) {
                    LOG.info("SIZE PENERIMA :::" + pemberi.size());
                    wakilKuasaPenerima = new WakilKuasaPenerima();
                    wakilKuasaPenerima.setInfoAudit(info);
                    wakilKuasaPenerima.setCawangan(peng.getKodCawangan());
                    wakilKuasaPenerima.setWakilKuasa(wakilKuasa);
                    wakilKuasaPenerima.setNama(pihak.getNama());
                    wakilKuasaPenerima.setJenisPengenalan(pihak.getJenisPengenalan());
                    LOG.info("========noPengenalanPenerima=========" + noKp);
                    wakilKuasaPenerima.setNoPengenalan(pihak.getNoPengenalan());
                    wakilKuasaPenerima.setStatus("A");
                    if (StringUtils.isNotBlank(amaunMaksima)) {
                        LOG.info("========Amaun Maksima=========" + amaunMaksima);
                        BigDecimal bd = new BigDecimal(amaunMaksima);
                        wakilKuasaPenerima.setAmaunMaksima(bd);
                    }
                    if (StringUtils.isNotBlank(syaratTambahan)) {
                        LOG.info("========Syarat Tambahan=========" + syaratTambahan);
                        wakilKuasa.setSyaratTambahan(syaratTambahan);
                        suratkuasaService.saveOrUpdateWakilKuasa(wakilKuasa);
                    }

                    suratkuasaService.saveOrUpdateWakilKuasaPenerima(wakilKuasaPenerima);
                } else {
                    msg = "Maklumat " + jPihak + " telah maksimum.";
                }
            }
            addSimpleError(msg);

        }
        return new RedirectResolution(SuratWakilKuasaActionBean.class, "maklumatPemPen2").addParameter("error", msg);
    }

    public Resolution updateSyaratPopup() {
        idPihak = getContext().getRequest().getParameter("idPihak");
        if (StringUtils.isNotEmpty(chkboxpihak)) {
            pihak = pihakDAO.findById(Long.parseLong(chkboxpihak));
        }
        return new JSP("daftar/popUpSyaratPenerimaWakil.jsp").addParameter("popup", "true");
    }

    public Resolution updateSyarat() {

        idPihak = getContext().getRequest().getParameter("idPihak");
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        peng = (Pengguna) context.getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        wakilKuasa = suratkuasaService.findWakilKuasa(idPermohonan);

        List<WakilKuasaPenerima> penerima = suratkuasaService.findWakilKuasaListPenerima(wakilKuasa.getIdWakil());
        String msg = "";
        if (wakilKuasa != null) {
        } else {
            if (wakilKuasa == null) {
                wakilKuasa = new WakilKuasa();
                wakilKuasa.setIdWakil(idPermohonan);
                wakilKuasa.setPermohonan(permohonanDAO.findById(idPermohonan));
                wakilKuasa.setAktif('T');
                wakilKuasa.setInfoAudit(info);
                wakilKuasa.setCawangan(permohonanDAO.findById(idPermohonan).getCawangan());
                wakilKuasa.setAktif('T');
                wakilKuasa.setKuasaAm('T');
                wakilKuasa.setKuasaGadai('T');
                wakilKuasa.setKuasaKaveat('T');
                wakilKuasa.setKuasaLepasGadai('T');
                wakilKuasa.setKuasaLepasKaveat('T');
                wakilKuasa.setKuasaPajak('T');
                wakilKuasa.setKuasaPajakKecil('T');
                wakilKuasa.setKuasaPindahMilik('T');
                wakilKuasa.setKuasaSewa('T');
                wakilKuasa.setKuasaTarikKaveat('T');
                wakilKuasa.setUntukSemuaHakmilik('T');
                suratkuasaService.saveWakilKuasa(wakilKuasa);
            }
        }
        pihak = new Pihak();
        System.out.println("pihak::::" + pihak);
        System.out.println("chkboxpihak::::" + chkboxpihak);

        if (StringUtils.isNotEmpty(chkboxpihak)) {
            pihak = pihakDAO.findById(Long.parseLong(chkboxpihak));
        }

        if (penerima.size() < wakilKuasa.getTempohBulan()) {
            info = new InfoAudit();
            info.setDimasukOleh(peng);
            info.setTarikhMasuk(new Date());
            wakilKuasaPenerima = new WakilKuasaPenerima();
            wakilKuasaPenerima.setInfoAudit(info);
            wakilKuasaPenerima.setCawangan(peng.getKodCawangan());
            wakilKuasaPenerima.setWakilKuasa(wakilKuasa);
            wakilKuasaPenerima.setNama(pihak.getNama());
            wakilKuasaPenerima.setJenisPengenalan(pihak.getJenisPengenalan());
            wakilKuasaPenerima.setNoPengenalan(pihak.getNoPengenalan());
            wakilKuasaPenerima.setStatus("A");
            if (StringUtils.isNotBlank(amaunMaksima)) {
                BigDecimal bd = new BigDecimal(amaunMaksima);
                wakilKuasaPenerima.setAmaunMaksima(bd);
            }
            if (StringUtils.isNotBlank(syaratTambahan)) {
                wakilKuasa.setSyaratTambahan(syaratTambahan);
                suratkuasaService.saveOrUpdateWakilKuasa(wakilKuasa);
            }

            suratkuasaService.saveOrUpdateWakilKuasaPenerima(wakilKuasaPenerima);
        } else {
            msg = "Maklumat " + jPihak + " telah maksimum.";
        }
        return new RedirectResolution(SuratWakilKuasaActionBean.class, "maklumatPemPen2").addParameter("error", msg);
    }

    public Resolution deletePenerima2() {

        WakilKuasaPenerima wp = new WakilKuasaPenerima();
        wp = wakilKuasaPenerimaDAO.findById(Long.parseLong(idPenerima));
        if (wp != null) {
            suratkuasaService.deletePenerima(wp);
            addSimpleMessage("Maklumat Penerima dihapus.");
        }
        return new RedirectResolution(SuratWakilKuasaActionBean.class, "maklumatPemPen2");
//        return new JSP("daftar/maklumat_pemberi_penerima_suratkuasa_wakil2.jsp").addParameter("tab", "true");
    }

    public Resolution deletePemberi2() {

        WakilKuasaPemberi wPemberi = new WakilKuasaPemberi();
        wPemberi = wakilKuasaPemberiDAO.findById(Long.parseLong(idPemberi));
        if (wPemberi != null) {
            suratkuasaService.deletePemberi(wPemberi);
            addSimpleMessage("Maklumat Pemberi dihapus.");
        }
        return new RedirectResolution(SuratWakilKuasaActionBean.class, "maklumatPemPen2");
//        return new JSP("daftar/maklumat_pemberi_penerima_suratkuasa_wakil2.jsp").addParameter("tab", "true");
    }

    public Resolution viewDetailPenerima() {

        WakilKuasaPenerima wp = new WakilKuasaPenerima();
        wp = wakilKuasaPenerimaDAO.findById(Long.parseLong(idPenerima));
        if (wp != null) {
            pihak.setNama(wp.getNama());
            pihak.setNoPengenalan(wp.getNoPengenalan());
            pihak.setJenisPengenalan(wp.getJenisPengenalan());
            suratkuasaService.savePihak(pihak);
            amaunMaksima = wp.getAmaunMaksima().toString();
            syaratTambahan = wp.getWakilKuasa().getSyaratTambahan();
        }
        return new JSP("daftar/popUpCarianPihakWakilKuasa.jsp").addParameter("popup", "true");
    }

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        peng = (Pengguna) context.getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        mohon = permohonanDAO.findById(idPermohonan);
        validateUrusan(mohon);
        if (idSblm) {
            wKuasa = suratkuasaService.findWakilKuasaList(mohon.getPermohonanSebelum().getIdPermohonan());
        } else {
            wakilKuasa2 = suratkuasaService.findWakilKuasa(idPermohonan);
            if (wakilKuasa2 != null) {
                jumPemberi = wakilKuasa2.getTempohHari();
                jumPenerima = wakilKuasa2.getTempohBulan();
            }
            wKuasa = suratkuasaService.findWakilKuasaList(idPermohonan);
        }
    }

    public PendaftaranSuratKuasaService getSuratkuasaService() {
        return suratkuasaService;
    }

    public void setSuratkuasaService(PendaftaranSuratKuasaService suratkuasaService) {
        this.suratkuasaService = suratkuasaService;
    }

    public List<WakilKuasa> getwKuasa() {
        return wKuasa;
    }

    public void setwKuasa(List<WakilKuasa> wKuasa) {
        this.wKuasa = wKuasa;
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

    public boolean isNotSW() {
        return notSW;
    }

    public void setNotSW(boolean notSW) {
        this.notSW = notSW;
    }

    public PermohonanDAO getPermohonanDAO() {
        return permohonanDAO;
    }

    public void setPermohonanDAO(PermohonanDAO permohonanDAO) {
        this.permohonanDAO = permohonanDAO;
    }

    public KodJenisPengenalanDAO getKodJenisPengenalanDAO() {
        return kodJenisPengenalanDAO;
    }

    public void setKodJenisPengenalanDAO(KodJenisPengenalanDAO kodJenisPengenalanDAO) {
        this.kodJenisPengenalanDAO = kodJenisPengenalanDAO;
    }

    public Permohonan getMohon() {
        return mohon;
    }

    public void setMohon(Permohonan mohon) {
        this.mohon = mohon;
    }

    public WakilKuasa getWakilKuasa() {
        return wakilKuasa;
    }

    public void setWakilKuasa(WakilKuasa wakilKuasa) {
        this.wakilKuasa = wakilKuasa;
    }

    public WakilKuasaPemberi getWakilKuasaPemberi() {
        return wakilKuasaPemberi;
    }

    public void setWakilKuasaPemberi(WakilKuasaPemberi wakilKuasaPemberi) {
        this.wakilKuasaPemberi = wakilKuasaPemberi;
    }

    public WakilKuasaPenerima getWakilKuasaPenerima() {
        return wakilKuasaPenerima;
    }

    public void setWakilKuasaPenerima(WakilKuasaPenerima wakilKuasaPenerima) {
        this.wakilKuasaPenerima = wakilKuasaPenerima;
    }

    public InfoAudit getInfo() {
        return info;
    }

    public void setInfo(InfoAudit info) {
        this.info = info;
    }

    public String[] getNOT_SW() {
        return NOT_SW;
    }

    public void setNOT_SW(String[] NOT_SW) {
        this.NOT_SW = NOT_SW;
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

    public String getKodKp() {
        return kodKp;
    }

    public void setKodKp(String kodKp) {
        this.kodKp = kodKp;
    }

    public String getAmaunMaksima() {
        return amaunMaksima;
    }

    public void setAmaunMaksima(String amaunMaksima) {
        this.amaunMaksima = amaunMaksima;
    }

    public String getSyaratTambahan() {
        return syaratTambahan;
    }

    public void setSyaratTambahan(String syaratTambahan) {
        this.syaratTambahan = syaratTambahan;
    }

    public List<Pihak> getPihakList() {
        return pihakList;
    }

    public void setPihakList(List<Pihak> pihakList) {
        this.pihakList = pihakList;
    }

    public boolean isReadOnly() {
        return readOnly;
    }

    public void setReadOnly(boolean readOnly) {
        this.readOnly = readOnly;
    }

    public String[] getURUSAN_READ_ONLY() {
        return URUSAN_READ_ONLY;
    }

    public void setURUSAN_READ_ONLY(String[] URUSAN_READ_ONLY) {
        this.URUSAN_READ_ONLY = URUSAN_READ_ONLY;
    }

    public String getjPihak() {
        return jPihak;
    }

    public void setjPihak(String jPihak) {
        this.jPihak = jPihak;
    }

    public String getChkboxpihak() {
        return chkboxpihak;
    }

    public void setChkboxpihak(String chkboxpihak) {
        this.chkboxpihak = chkboxpihak;
    }

    public String getIdPenerima() {
        return idPenerima;
    }

    public void setIdPenerima(String idPenerima) {
        this.idPenerima = idPenerima;
    }

    public String getIdPemberi() {
        return idPemberi;
    }

    public void setIdPemberi(String idPemberi) {
        this.idPemberi = idPemberi;
    }

    public Pihak getPihak() {
        return pihak;
    }

    public void setPihak(Pihak pihak) {
        this.pihak = pihak;
    }

    public String getIdPihak() {
        return idPihak;
    }

    public void setIdPihak(String idPihak) {
        this.idPihak = idPihak;
    }

    public boolean isIdSblm() {
        return idSblm;
    }

    public void setIdSblm(boolean idSblm) {
        this.idSblm = idSblm;
    }

    public String[] getURUSAN_USE_MOHON_SBLM() {
        return URUSAN_USE_MOHON_SBLM;
    }

    public void setURUSAN_USE_MOHON_SBLM(String[] URUSAN_USE_MOHON_SBLM) {
        this.URUSAN_USE_MOHON_SBLM = URUSAN_USE_MOHON_SBLM;
    }

    public WakilKuasa getWakilKuasa2() {
        return wakilKuasa2;
    }

    public void setWakilKuasa2(WakilKuasa wakilKuasa2) {
        this.wakilKuasa2 = wakilKuasa2;
    }

    public int getJumPemberi() {
        return jumPemberi;
    }

    public void setJumPemberi(int jumPemberi) {
        this.jumPemberi = jumPemberi;
    }

    public int getJumPenerima() {
        return jumPenerima;
    }

    public void setJumPenerima(int jumPenerima) {
        this.jumPenerima = jumPenerima;
    }
}
