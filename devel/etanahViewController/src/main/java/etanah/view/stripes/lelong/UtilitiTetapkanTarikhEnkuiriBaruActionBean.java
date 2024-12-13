/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.stripes.lelong;

import able.stripes.AbleActionBean;
import com.google.inject.Inject;
import etanah.dao.FasaPermohonanDAO;
import etanah.dao.KodDokumenDAO;
import etanah.dao.KodStatusEnkuiriDAO;
import etanah.dao.PermohonanDAO;
import etanah.model.*;
import etanah.service.common.EnkuiriService;
import etanah.service.common.LelongService;
import etanah.view.etanahActionBeanContext;
import java.io.FileNotFoundException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.StreamingResolution;
import net.sourceforge.stripes.action.UrlBinding;
import org.apache.log4j.Logger;
import org.apache.commons.lang.StringUtils;

/**
 *
 * @author mazurahayati.yusop
 */
@UrlBinding("/lelong/utiliti_maklumat_enkuiri")
public class UtilitiTetapkanTarikhEnkuiriBaruActionBean extends AbleActionBean {

    private static final Logger LOG = Logger.getLogger(MaklumatEnkuiriActionBean.class);
    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    FasaPermohonanDAO fasapermohonanDAO;
    @Inject
    KodStatusEnkuiriDAO kodStatusEnkuiriDAO;
    @Inject
    EnkuiriService enService;
    @Inject
    LelongService lelongService;
    @Inject
    KodDokumenDAO kodDokumenDAO;
    @Inject
    CalenderManager manager;
    private Permohonan permohonan;
    private FasaPermohonan fasaPermohonan;
    private List<Enkuiri> senaraiEnkuiri;
    private Enkuiri enkuiri;
    private String tarikhEnkuiri;
    private String jam;
    private String minit;
    private String ampm;
    private String hari;
    private String idPermohonan;
    private String count;
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm aaa");
    private String tempat;
    private String perkara;
    private String disabled = null;
    private List<String> idHakmilik = new ArrayList<String>();
    private boolean flag = false;
    private List<CalenderLelong> listCalender;
    private List<CalenderLelong> listCalender2;

    @DefaultHandler
    public Resolution selectTransaction() {
        return new ForwardResolution("/WEB-INF/jsp/lelong/UtilitiTetapkanTarikhEnkuiriBaru.jsp");
    }

    public Resolution showForm3() {
        etanahActionBeanContext ctx = new etanahActionBeanContext();
        ctx = (etanahActionBeanContext) getContext();
        listCalender = manager.getALLEnkuri(ctx.getKodCawangan().getKod());
        listCalender2 = manager.getALLLelongan(ctx.getKodCawangan().getKod());
        return new ForwardResolution("/WEB-INF/jsp/lelong/calender_lelong5.jsp").addParameter("popup", "true");
    }

    public Resolution find() {

        if (StringUtils.isNotBlank(idPermohonan)) {

            permohonan = permohonanDAO.findById(idPermohonan);
            if (permohonan != null) {
                List<FasaPermohonan> listFasa1 = lelongService.getFasaKemasukanPerintah(idPermohonan);
                List<FasaPermohonan> stage1 = lelongService.findFasa(idPermohonan);
                boolean test = false;
                for (int i = 0; i < stage1.size(); i++) {
                    FasaPermohonan fasa = stage1.get(i);
                    if ("kemasukanPerintah".equals(fasa.getIdAliran())) {
                        test = true;
                        break;
                    }
                }
                if (stage1.size() == 1 && test == true) {
                    LOG.debug("kp");
                    addSimpleError("Utiliti Ini Tidak Dapat Diteruskan. ID Permohonan :"+idPermohonan+ "berada di Fasa Kemasukan Perintah");
                    return new ForwardResolution("/WEB-INF/jsp/lelong/UtilitiTetapkanTarikhEnkuiriBaru.jsp");
                } else if (!listFasa1.isEmpty()) {
                    LOG.debug("error");
                    addSimpleError("Utiliti Ini Tidak Dapat Diteruskan. ID Permohonan :"+idPermohonan+ " Telah Melepasi Fasa Yang Sepatutnya");
                    return new ForwardResolution("/WEB-INF/jsp/lelong/UtilitiTetapkanTarikhEnkuiriBaru.jsp");
                } else {
                    senaraiEnkuiri = lelongService.getEnkuiriNotAK(idPermohonan);
                    enkuiri = lelongService.findEnkuiri(idPermohonan);
                    if (enkuiri != null) {
                        try {
                            tarikhEnkuiri = sdf.format(enkuiri.getTarikhEnkuiri()).substring(0, 10);
                            jam = sdf.format(enkuiri.getTarikhEnkuiri()).substring(11, 13);
                            minit = sdf.format(enkuiri.getTarikhEnkuiri()).substring(14, 16);
                            ampm = sdf.format(enkuiri.getTarikhEnkuiri()).substring(17, 19);
                            tempat = enkuiri.getTempat();
                            perkara = enkuiri.getPerkara();
                        } catch (Exception ex) {
                            LOG.error(ex);
                        }
                        FolderDokumen fd = permohonan.getFolderDokumen();
                        List<KandunganFolder> listKD = lelongService.getListDokumen(fd.getFolderId());
                        if (!listKD.isEmpty()) {
                            String idDok = String.valueOf(listKD.get(0).getDokumen().getIdDokumen());
                            getContext().getRequest().setAttribute("idDok", idDok);
                        }
                    } else {
                        addSimpleError("Tarikh Enkuiri Belum Ditetapkan.Sila Tetapkan Tarikh Siasatan Terlebih Dahulu.");
                        return new ForwardResolution("/WEB-INF/jsp/lelong/UtilitiTetapkanTarikhEnkuiriBaru.jsp");
                    }
                }

            } else {
                addSimpleError("IdPermohonan Salah.Sila Masukkan IdPermohonan Sekali Lagi");
                return new ForwardResolution("/WEB-INF/jsp/lelong/UtilitiTetapkanTarikhEnkuiriBaru.jsp");
            }
        }
        flag = true;
        return new ForwardResolution("/WEB-INF/jsp/lelong/UtilitiTetapkanTarikhEnkuiriBaru.jsp");
    }

    public Resolution simpanEnkuiri() {
        if (StringUtils.isNotBlank(idPermohonan)) {
            permohonan = permohonanDAO.findById(idPermohonan);
            getContext().getRequest().setAttribute("idPermohonan", idPermohonan);

            senaraiEnkuiri = lelongService.getEnkuiri(idPermohonan);

            Pengguna peng = (Pengguna) context.getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
            InfoAudit ia = new InfoAudit();
            ia.setDimasukOleh(peng);
            ia.setTarikhMasuk(new java.util.Date());
            ia.setDiKemaskiniOleh(peng);
            ia.setTarikhKemaskini(new java.util.Date());

            for (Enkuiri enkuiri2 : senaraiEnkuiri) {
                if (enkuiri2.getStatus().getKod().equals("AK")) {
                    enkuiri = enkuiri2;
                    enkuiri2.setStatus(kodStatusEnkuiriDAO.findById("TT"));
                    enService.save(enkuiri2);
                }
            }

            Enkuiri enk = new Enkuiri();
            if (StringUtils.isNotEmpty(enkuiri.getTujuanGadaian())) {
                enk.setTujuanGadaian(enkuiri.getTujuanGadaian());
            }
            if (enkuiri.getAmaunTunggakan() != null) {
                enk.setAmaunTunggakan(enkuiri.getAmaunTunggakan());
            }
            enk.setPermohonan(permohonan);
            enk.setCawangan(peng.getKodCawangan());
            enk.setInfoAudit(ia);
            enk.setBilanganKes(enkuiri.getBilanganKes() + 1);
            tarikhEnkuiri = tarikhEnkuiri + " " + jam + ":" + " " + minit + " " + ampm;
            LOG.debug("tarikhEnkuiri :" + tarikhEnkuiri);

            try {
                enk.setTarikhEnkuiri(sdf.parse(tarikhEnkuiri));
            } catch (Exception ex) {
                LOG.error(ex);
            }

            enk.setTempat(tempat);
            enk.setPerkara(perkara);
            enk.setStatus(kodStatusEnkuiriDAO.findById("AK"));
            enService.save(enk);

            try {
                tarikhEnkuiri = sdf.format(enk.getTarikhEnkuiri()).substring(0, 10);
                jam = sdf.format(enk.getTarikhEnkuiri()).substring(11, 13);
                minit = sdf.format(enk.getTarikhEnkuiri()).substring(14, 16);
                ampm = sdf.format(enk.getTarikhEnkuiri()).substring(17, 19);
                tempat = enk.getTempat();
                perkara = enk.getPerkara();
            } catch (Exception ex) {
                LOG.error(ex);
            }
            senaraiEnkuiri = lelongService.getEnkuiriNotAK(idPermohonan);
            enkuiri = lelongService.findEnkuiri(idPermohonan);
            FolderDokumen fd = permohonan.getFolderDokumen();
            List<KandunganFolder> listKD = lelongService.getListDokumen(fd.getFolderId());
            if (!listKD.isEmpty()) {
                KodDokumen kodD = null;
                for (KandunganFolder kf : listKD) {
                    kodD = kodDokumenDAO.findById("SSLM");
                    if (kf.getDokumen().getKodDokumen().getKod().equals("SSL")) {
                        Dokumen dd = kf.getDokumen();
                        dd.setKodDokumen(kodD);
                        lelongService.saveOrUpdatDokumen(dd);
                        kf.setDokumen(dd);
                        lelongService.saveOrUpdate(kf);
                    }
                }
            }
            LOG.info("genReport :: start");
            LOG.info("generate report start.");

            etanahActionBeanContext ctx = new etanahActionBeanContext();
            ctx = (etanahActionBeanContext) getContext();

            try {
                LOG.info("genReportFromXML");
                String gen = "LLGSuratSiasatan_MLK.rdf";
                String code = "SSL";
                lelongService.reportGen(permohonan, gen, code, ctx.getUser());
            } catch (Exception ex) {
                LOG.error(ex);
            }
            LOG.info("genReport :: finish");

            listKD = lelongService.getListDokumen(fd.getFolderId());
            if (!listKD.isEmpty()) {
                String idDok = String.valueOf(listKD.get(0).getDokumen().getIdDokumen());
                getContext().getRequest().setAttribute("idDok", idDok);
            }
            flag = true;
            addSimpleMessage("Maklumat telah berjaya disimpan.");
        }
        return new ForwardResolution("/WEB-INF/jsp/lelong/UtilitiTetapkanTarikhEnkuiriBaru.jsp");
    }

    public Resolution cetak() throws FileNotFoundException {
        if (StringUtils.isNotBlank(idPermohonan)) {
            permohonan = permohonanDAO.findById(idPermohonan);
        }

        etanahActionBeanContext ctx = new etanahActionBeanContext();
        ctx = (etanahActionBeanContext) getContext();

        Pengguna pengguna = ctx.getUser();
//        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        FolderDokumen fd = permohonan.getFolderDokumen();
        List<KandunganFolder> listKD = lelongService.getListDokumen(fd.getFolderId());
        if (!listKD.isEmpty()) {
            KodDokumen kodD = null;
            for (KandunganFolder kf : listKD) {

                kodD = kodDokumenDAO.findById("SSLM");
                if (kf.getDokumen().getKodDokumen().getKod().equals("SSL")) {
                    Dokumen dd = kf.getDokumen();
                    dd.setKodDokumen(kodD);
                    lelongService.saveOrUpdatDokumen(dd);
                    kf.setDokumen(dd);
                    lelongService.saveOrUpdate(kf);
                }
            }
        }
        LOG.info("genReport :: start");
        LOG.info("generate report start.");
        String msg = "Laporan telah dijana. Sila buat semakan sebelum cetakan.";

        try {
            LOG.info("genReportFromXML");
            String gen = "LLGSuratSiasatan_MLK.rdf";
            String code = "SSL";
            lelongService.reportGen(permohonan, gen, code, pengguna);
        } catch (Exception ex) {
            return new StreamingResolution("text/plain", "Dokumen Tidak Dapat Dijana." + ex.getMessage());
        }
        LOG.info("genReport :: finish");
        return new StreamingResolution("text/plain", msg);
    }

    public String getAmpm() {
        return ampm;
    }

    public void setAmpm(String ampm) {
        this.ampm = ampm;
    }

    public String getDisabled() {
        return disabled;
    }

    public void setDisabled(String disabled) {
        this.disabled = disabled;
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public String getHari() {
        return hari;
    }

    public void setHari(String hari) {
        this.hari = hari;
    }

    public List<String> getIdHakmilik() {
        return idHakmilik;
    }

    public void setIdHakmilik(List<String> idHakmilik) {
        this.idHakmilik = idHakmilik;
    }

    public String getIdPermohonan() {
        return idPermohonan;
    }

    public void setIdPermohonan(String idPermohonan) {
        this.idPermohonan = idPermohonan;
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

    public String getPerkara() {
        return perkara;
    }

    public void setPerkara(String perkara) {
        this.perkara = perkara;
    }

    public Permohonan getPermohonan() {
        return permohonan;
    }

    public void setPermohonan(Permohonan permohonan) {
        this.permohonan = permohonan;
    }

    public List<Enkuiri> getSenaraiEnkuiri() {
        return senaraiEnkuiri;
    }

    public void setSenaraiEnkuiri(List<Enkuiri> senaraiEnkuiri) {
        this.senaraiEnkuiri = senaraiEnkuiri;
    }

    public String getTarikhEnkuiri() {
        return tarikhEnkuiri;
    }

    public void setTarikhEnkuiri(String tarikhEnkuiri) {
        this.tarikhEnkuiri = tarikhEnkuiri;
    }

    public String getTempat() {
        return tempat;
    }

    public void setTempat(String tempat) {
        this.tempat = tempat;
    }

    public Enkuiri getEnkuiri() {
        return enkuiri;
    }

    public void setEnkuiri(Enkuiri enkuiri) {
        this.enkuiri = enkuiri;
    }

    public List<CalenderLelong> getListCalender() {
        return listCalender;
    }

    public void setListCalender(List<CalenderLelong> listCalender) {
        this.listCalender = listCalender;
    }

    public List<CalenderLelong> getListCalender2() {
        return listCalender2;
    }

    public void setListCalender2(List<CalenderLelong> listCalender2) {
        this.listCalender2 = listCalender2;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }
}
