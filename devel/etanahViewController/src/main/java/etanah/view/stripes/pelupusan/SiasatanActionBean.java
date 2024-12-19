/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package etanah.view.stripes.pelupusan;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.dao.HakmilikPermohonanDAO;
import etanah.dao.KodDokumenDAO;
import etanah.dao.KodKeputusanDAO;
import etanah.dao.PermohonanDAO;
import etanah.dao.PermohonanKertasDAO;
import etanah.dao.PermohonanKertasKandunganDAO;
import etanah.model.HakmilikPermohonan;
import etanah.model.InfoAudit;
import etanah.model.KodDokumen;
import etanah.model.Pemohon;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.model.PermohonanKertas;
import etanah.model.PermohonanKertasKandungan;
import etanah.service.PelupusanService;
import etanah.view.etanahActionBeanContext;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;
import org.apache.log4j.Logger;

/**
 *
 * @author Murali
 */
@UrlBinding("/pelupusan/siasatan")
public class SiasatanActionBean extends AbleActionBean {


    @Inject
    private Permohonan permohonan;
    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    PelupusanService pservice;
    @Inject
    KodKeputusanDAO kodKeputusanDAO;
    @Inject
    HakmilikPermohonanDAO hakmilikPermohonanDAO;
    @Inject
    KodDokumenDAO kodDokumenDAO;
    @Inject
    PermohonanKertasDAO permohonanKertasDAO;
    @Inject
    PermohonanKertasKandunganDAO permohonanKertasKandunganDAO;

    private static final Logger LOG = Logger.getLogger(SiasatanActionBean.class);
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

    private String idPermohonan;
    private Pemohon pemohon;
    private HakmilikPermohonan mohonHakmilik = new HakmilikPermohonan();
    private PermohonanKertasKandungan kertasKandungan;
    private PermohonanKertas permohonanKertas;
    private KodDokumen kd;
    private String keputusansiasatan;



    @DefaultHandler
    public Resolution showForm() {

     //   getContext().getRequest().setAttribute("edit", Boolean.TRUE);

        return new JSP("pelupusan/siasatan.jsp").addParameter("tab", "true");
    }


     @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {

        LOG.info("--------------rehydrate() Started--------------::");
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        LOG.info("---------idPermohonan---------::" + idPermohonan);
        permohonan = permohonanDAO.findById(idPermohonan);
        LOG.info("---------permohonan---------::" + permohonan);
        permohonanKertas = pservice.findKertasByKod(idPermohonan, "MMKS");


        if (permohonanKertas != null) {
            for (int j = 0; j < permohonanKertas.getSenaraiKandungan().size(); j++) {
                kertasKandungan = permohonanKertas.getSenaraiKandungan().get(j);
                if (kertasKandungan.getBil() == 0) {
                    keputusansiasatan = kertasKandungan.getKandungan();
                }
            }
        }

    }


      public Resolution simpan() {


        LOG.info("--------------rehydrate() Started--------------::");
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        LOG.info("---------idPermohonan---------::" + idPermohonan);
        permohonan = permohonanDAO.findById(idPermohonan);
        LOG.info("---------permohonan---------::" + permohonan);
        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        InfoAudit infoAudit = new InfoAudit();
        kd = kodDokumenDAO.findById("MMKS");
        LOG.info("----------Kod Dokumen----------::" + kd);
        permohonanKertas = pservice.findKertasByKod(idPermohonan, "MMKS");
        LOG.info("-------Simpan-------permohonankertas--------------::");


        if (permohonanKertas != null) {
            LOG.info("------if------permohonankertas NOT Null--------------::" + permohonanKertas);
            infoAudit = permohonanKertas.getInfoAudit();
            infoAudit.setDiKemaskiniOleh(peng);
            infoAudit.setTarikhKemaskini(new java.util.Date());
            pservice.simpanPermohonanKertas(permohonanKertas);

            if (keputusansiasatan == null || keputusansiasatan.equals("")) {
            keputusansiasatan = " ";
            }

            PermohonanKertasKandungan kertasKdgn = new PermohonanKertasKandungan();
            kertasKdgn.setInfoAudit(infoAudit);
            kertasKdgn.setKertas(permohonanKertas);
            kertasKdgn.setCawangan(permohonan.getCawangan());
            kertasKdgn.setSubtajuk("siasatan keputusan tangguh");
            kertasKdgn.setKandungan(keputusansiasatan);
            pservice.simpanPermohonanKertasKandungan(kertasKdgn);


        }


          addSimpleMessage("Maklumat telah berjaya disimpan.");
          return new JSP("pelupusan/siasatan.jsp").addParameter("tab", "true");

      }

    public String getKeputusansiasatan() {
        return keputusansiasatan;
    }

    public void setKeputusansiasatan(String keputusansiasatan) {
        this.keputusansiasatan = keputusansiasatan;
    }


    public HakmilikPermohonanDAO getHakmilikPermohonanDAO() {
        return hakmilikPermohonanDAO;
    }

    public void setHakmilikPermohonanDAO(HakmilikPermohonanDAO hakmilikPermohonanDAO) {
        this.hakmilikPermohonanDAO = hakmilikPermohonanDAO;
    }

    public String getIdPermohonan() {
        return idPermohonan;
    }

    public void setIdPermohonan(String idPermohonan) {
        this.idPermohonan = idPermohonan;
    }

    public KodDokumen getKd() {
        return kd;
    }

    public void setKd(KodDokumen kd) {
        this.kd = kd;
    }

    public PermohonanKertasKandungan getKertasKandungan() {
        return kertasKandungan;
    }

    public void setKertasKandungan(PermohonanKertasKandungan kertasKandungan) {
        this.kertasKandungan = kertasKandungan;
    }

    public KodDokumenDAO getKodDokumenDAO() {
        return kodDokumenDAO;
    }

    public void setKodDokumenDAO(KodDokumenDAO kodDokumenDAO) {
        this.kodDokumenDAO = kodDokumenDAO;
    }

    public KodKeputusanDAO getKodKeputusanDAO() {
        return kodKeputusanDAO;
    }

    public void setKodKeputusanDAO(KodKeputusanDAO kodKeputusanDAO) {
        this.kodKeputusanDAO = kodKeputusanDAO;
    }

    public HakmilikPermohonan getMohonHakmilik() {
        return mohonHakmilik;
    }

    public void setMohonHakmilik(HakmilikPermohonan mohonHakmilik) {
        this.mohonHakmilik = mohonHakmilik;
    }

    public Pemohon getPemohon() {
        return pemohon;
    }

    public void setPemohon(Pemohon pemohon) {
        this.pemohon = pemohon;
    }

    public Permohonan getPermohonan() {
        return permohonan;
    }

    public void setPermohonan(Permohonan permohonan) {
        this.permohonan = permohonan;
    }

    public PermohonanDAO getPermohonanDAO() {
        return permohonanDAO;
    }

    public void setPermohonanDAO(PermohonanDAO permohonanDAO) {
        this.permohonanDAO = permohonanDAO;
    }

    public PermohonanKertas getPermohonanKertas() {
        return permohonanKertas;
    }

    public void setPermohonanKertas(PermohonanKertas permohonanKertas) {
        this.permohonanKertas = permohonanKertas;
    }

    public PermohonanKertasDAO getPermohonanKertasDAO() {
        return permohonanKertasDAO;
    }

    public void setPermohonanKertasDAO(PermohonanKertasDAO permohonanKertasDAO) {
        this.permohonanKertasDAO = permohonanKertasDAO;
    }

    public PermohonanKertasKandunganDAO getPermohonanKertasKandunganDAO() {
        return permohonanKertasKandunganDAO;
    }

    public void setPermohonanKertasKandunganDAO(PermohonanKertasKandunganDAO permohonanKertasKandunganDAO) {
        this.permohonanKertasKandunganDAO = permohonanKertasKandunganDAO;
    }

    public PelupusanService getPservice() {
        return pservice;
    }

    public void setPservice(PelupusanService pservice) {
        this.pservice = pservice;
    }

    public SimpleDateFormat getSdf() {
        return sdf;
    }

    public void setSdf(SimpleDateFormat sdf) {
        this.sdf = sdf;
    }
}
