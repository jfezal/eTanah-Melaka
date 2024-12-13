/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.strata.laporan;

import able.stripes.AbleActionBean;
import etanah.view.etanahActionBeanContext;
import com.google.inject.Inject;
import etanah.dao.*;
import etanah.model.*;
import etanah.model.strata.*;
import java.util.*;
import etanah.service.*;
import etanah.service.common.*;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.action.StreamingResolution;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.hibernate.Transaction;
import able.stripes.JSP;
import org.hibernate.Session;

@UrlBinding("/strata/cetakGeranstrata")
public class CetakGeranStrataActionBean extends AbleActionBean {

    private static final Logger LOG = Logger.getLogger(CetakGeranStrataActionBean.class);
    @Inject
    private etanah.Configuration conf;
    @Inject
    etanah.kodHasilConfig khconf;
    @Inject
    DokumenDAO dokumenDAO;
    @Inject
    DokumenCapaianDAO dokumenCapaianDAO;
    @Inject
    DokumenCapaianService dokumenCapaianService;
    private List<DokumenCapaian> senaraiDokumenCapai;
    @Inject
    KodStatusDokumenCapaiDAO kodStatusDokumenCapaiDAO;
    private String idHakmilikInduk;
    private String idPermohonan;
    private String idHakmilikStrata;
    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;
    @Inject
    KodCawanganDAO kodCawanganDAO;
    @Inject
    StrataPtService strataPtService;
    @Inject
    PenggunaDAO penggunaDAO;
    private List<Hakmilik> senaraiHakmilik;
    private List<Hakmilik> senaraiHakmilikProv = new ArrayList();
    private List<HakmilikTukarGantiStrata> senaraiTkrganti = new ArrayList();
    private List<HakmilikTukarGantiStrata> senaraiTkrgantiProv = new ArrayList();
    private List<HakmilikTukarGantiStrata> senaraiTkrganti2k3k = new ArrayList();
    private List<Dokumen> senaraiDokumen = new ArrayList();
    private List<Dokumen> senaraiDokumenKK = new ArrayList();
    private List<Dokumen> senaraiDokumenBSK = new ArrayList();
    private List<Dokumen> senaraiDokumenBSKK = new ArrayList();
    private List<Dokumen> senaraiDokumenPSK = new ArrayList();
    private List<Dokumen> senaraiDokumen2K3K = new ArrayList();
    private List<Dokumen> senaraiDokumenProv = new ArrayList();
    private List<Dokumen> senaraiDokumenProvKK = new ArrayList();
    private String sbb_cetakan_semula;
    private int bilHakmilik;

    @DefaultHandler
    public Resolution showForm() {
        LOG.info("showForm");
        idPermohonan = null;
        return new ForwardResolution("/WEB-INF/jsp/strata/Laporan/cetak_geran_strata.jsp");
    }

    public Resolution simpanSebab() {
        LOG.info("simpanSebab");
        String idHakmilik = getContext().getRequest().getParameter("idHakmilik");
        LOG.info("idHakmilik------" + idHakmilik);
        return new ForwardResolution("/WEB-INF/jsp/strata/Laporan/cetak_geran_strata.jsp");
    }

    public Resolution cetakSemula() {
        Pengguna pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        String sbbCetakanSemula = getContext().getRequest().getParameter("sbb_cetakan_semula");
        String idDokumen = getContext().getRequest().getParameter("id_dokumen");
        String idHakmilik = getContext().getRequest().getParameter("idHakmilik");
        Dokumen d = dokumenDAO.findById(Long.parseLong(idDokumen));

        if (d == null) {
            return new StreamingResolution("text/plain", "2");
        }

        List<DokumenCapaian> dc2 = strataPtService.findDokumenCapaian(d.getIdDokumen());
        int count = 0;
        for (DokumenCapaian dc1 : dc2) {
            LOG.info(dc1.getAlasan());
            String alasan = "CETAKAN SEMULA [ " + sbbCetakanSemula + " ]";
            if (dc1.getAlasan().equals(alasan)) {
                count++;
                return new StreamingResolution("text/plain", "0");
            }
        }
        if (count == 0) {
            DokumenCapaian dc = new DokumenCapaian();

            if (StringUtils.isNotBlank(sbbCetakanSemula)) {
                dc.setAlasan("CETAKAN SEMULA [ " + sbbCetakanSemula + " ]");
            } else {
                dc.setAlasan("Cetakan Dokumen");
            }

            dc.setDokumen(d);
            dc.setAktiviti(kodStatusDokumenCapaiDAO.findById("CD"));
            InfoAudit ia = new InfoAudit();
            ia.setDimasukOleh(pengguna);
            ia.setTarikhMasuk(new java.util.Date());
            dc.setInfoAudit(ia);

            Session s = sessionProvider.get();
            Transaction tx = s.beginTransaction();
            dokumenCapaianDAO.save(dc);

            Dokumen dk = strataPtService.findDokumen(dc.getDokumen().getIdDokumen());

            tx.commit();
        }

        return new StreamingResolution("text/plain", "1");
    }

    public Resolution cari() {

        idPermohonan = getContext().getRequest().getParameter("idPermohonan");

        sbb_cetakan_semula = null;

        senaraiDokumen = strataPtService.findDokumen(idPermohonan, "4KDHD");
        senaraiDokumenKK = strataPtService.findDokumen(idPermohonan, "4KDHK");
        senaraiDokumenBSK = strataPtService.findDokumen(idPermohonan, "BSKDK");
        senaraiDokumenBSKK = strataPtService.findDokumen(idPermohonan, "BSKKK");
        senaraiDokumenPSK = strataPtService.findDokumen(idPermohonan, "PSK");
        senaraiDokumenProv = strataPtService.findDokumen(idPermohonan, "4AKDH");
        senaraiDokumenProvKK = strataPtService.findDokumen(idPermohonan, "4AKKK");

        List <Dokumen> list2k = strataPtService.findDokumen(idPermohonan, "2K");
        List <Dokumen> list3k = strataPtService.findDokumen(idPermohonan, "3K");
        for (Dokumen dk : list2k) {
            senaraiDokumen2K3K.add(dk);
        }
        for (Dokumen dk : list3k) {
            senaraiDokumen2K3K.add(dk);
        }

        return new ForwardResolution("/WEB-INF/jsp/strata/Laporan/cetak_geran_strata.jsp");
    }

    public Resolution viewSejarahPaparan() {
        String idDokumen = getContext().getRequest().getParameter("idDokumen");
        senaraiDokumenCapai = dokumenCapaianService.findByIdDokumenAndPD(idDokumen);
        return new JSP("daftar/utiliti/view_sejarah_paparan.jsp").addParameter("popup", "true");
    }

    public String getIdHakmilikInduk() {
        return idHakmilikInduk;
    }

    public void setIdHakmilikInduk(String idHakmilikInduk) {
        this.idHakmilikInduk = idHakmilikInduk;
    }

    public String getIdHakmilikStrata() {
        return idHakmilikStrata;
    }

    public void setIdHakmilikStrata(String idHakmilikStrata) {
        this.idHakmilikStrata = idHakmilikStrata;
    }

    public List<Hakmilik> getSenaraiHakmilik() {
        return senaraiHakmilik;
    }

    public void setSenaraiHakmilik(List<Hakmilik> senaraiHakmilik) {
        this.senaraiHakmilik = senaraiHakmilik;
    }

    public String getSbb_cetakan_semula() {
        return sbb_cetakan_semula;
    }

    public void setSbb_cetakan_semula(String sbb_cetakan_semula) {
        this.sbb_cetakan_semula = sbb_cetakan_semula;
    }

    public List<Dokumen> getSenaraiDokumen() {
        return senaraiDokumen;
    }

    public void setSenaraiDokumen(List<Dokumen> senaraiDokumen) {
        this.senaraiDokumen = senaraiDokumen;
    }

    public List<DokumenCapaian> getSenaraiDokumenCapai() {
        return senaraiDokumenCapai;
    }

    public void setSenaraiDokumenCapai(List<DokumenCapaian> senaraiDokumenCapai) {
        this.senaraiDokumenCapai = senaraiDokumenCapai;
    }

    public List<Dokumen> getSenaraiDokumen2K3K() {
        return senaraiDokumen2K3K;
    }

    public void setSenaraiDokumen2K3K(List<Dokumen> senaraiDokumen2K3K) {
        this.senaraiDokumen2K3K = senaraiDokumen2K3K;
    }

    public List<Dokumen> getSenaraiDokumenProv() {
        return senaraiDokumenProv;
    }

    public void setSenaraiDokumenProv(List<Dokumen> senaraiDokumenProv) {
        this.senaraiDokumenProv = senaraiDokumenProv;
    }

    public int getBilHakmilik() {
        return bilHakmilik;
    }

    public void setBilHakmilik(int bilHakmilik) {
        this.bilHakmilik = bilHakmilik;
    }

    public List<HakmilikTukarGantiStrata> getSenaraiTkrganti() {
        return senaraiTkrganti;
    }

    public void setSenaraiTkrganti(List<HakmilikTukarGantiStrata> senaraiTkrganti) {
        this.senaraiTkrganti = senaraiTkrganti;
    }

    public List<HakmilikTukarGantiStrata> getSenaraiTkrganti2k3k() {
        return senaraiTkrganti2k3k;
    }

    public void setSenaraiTkrganti2k3k(List<HakmilikTukarGantiStrata> senaraiTkrganti2k3k) {
        this.senaraiTkrganti2k3k = senaraiTkrganti2k3k;
    }

    public List<Hakmilik> getSenaraiHakmilikProv() {
        return senaraiHakmilikProv;
    }

    public void setSenaraiHakmilikProv(List<Hakmilik> senaraiHakmilikProv) {
        this.senaraiHakmilikProv = senaraiHakmilikProv;
    }

    public List<HakmilikTukarGantiStrata> getSenaraiTkrgantiProv() {
        return senaraiTkrgantiProv;
    }

    public void setSenaraiTkrgantiProv(List<HakmilikTukarGantiStrata> senaraiTkrgantiProv) {
        this.senaraiTkrgantiProv = senaraiTkrgantiProv;
    }

    public List<Dokumen> getSenaraiDokumenBSK() {
        return senaraiDokumenBSK;
    }

    public void setSenaraiDokumenBSK(List<Dokumen> senaraiDokumenBSK) {
        this.senaraiDokumenBSK = senaraiDokumenBSK;
    }

    public List<Dokumen> getSenaraiDokumenPSK() {
        return senaraiDokumenPSK;
    }

    public void setSenaraiDokumenPSK(List<Dokumen> senaraiDokumenPSK) {
        this.senaraiDokumenPSK = senaraiDokumenPSK;
    }

    public List<Dokumen> getSenaraiDokumenKK() {
        return senaraiDokumenKK;
    }

    public void setSenaraiDokumenKK(List<Dokumen> senaraiDokumenKK) {
        this.senaraiDokumenKK = senaraiDokumenKK;
    }

    public List<Dokumen> getSenaraiDokumenBSKK() {
        return senaraiDokumenBSKK;
    }

    public void setSenaraiDokumenBSKK(List<Dokumen> senaraiDokumenBSKK) {
        this.senaraiDokumenBSKK = senaraiDokumenBSKK;
    }

    public String getIdPermohonan() {
        return idPermohonan;
    }

    public void setIdPermohonan(String idPermohonan) {
        this.idPermohonan = idPermohonan;
    }

    public List<Dokumen> getSenaraiDokumenProvKK() {
        return senaraiDokumenProvKK;
    }

    public void setSenaraiDokumenProvKK(List<Dokumen> senaraiDokumenProvKK) {
        this.senaraiDokumenProvKK = senaraiDokumenProvKK;
    }

}
