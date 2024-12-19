/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package etanah.view.stripes.hasil;

import able.stripes.AbleActionBean;
import com.google.inject.Inject;
import etanah.dao.*;
import etanah.model.*;
import etanah.report.ReportUtil;
import etanah.view.etanahActionBeanContext;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.RedirectResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.StreamingResolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.action.Wizard;
import org.apache.log4j.Logger;

/**
 *
 * @author abdul.hakim
 */
@Wizard(startEvents = {"showForm"})
@UrlBinding("/hasil/cetak_semula_surat_akuan")
public class CetakSemulaSuratAkuanActionBean extends AbleActionBean {
    private static final Logger LOG = Logger.getLogger(CetakSemulaSuratAkuanActionBean.class);
    private static final String DEFAULT_VIEW = "/WEB-INF/jsp/hasil/cetak_semula_resit.jsp";
    private static final String DETAIL_VIEW = "/WEB-INF/jsp/hasil/cetak_semula_surat_akuan.jsp";

    private Permohonan permohonan;
    private Transaksi transaksi;
    private DokumenKewangan dokumenKewangan;
    private DokumenKewanganBayaran dokumenKewanganBayaran;
    private Pengguna pengguna;
    private static String kodNegeri = null;

    private TransaksiDAO transaksiDAO;
    private PenggunaDAO penggunaDAO;
    private PermohonanDAO permohonanDAO;
    private DokumenKewanganDAO dokumenKewanganDAO;
    private DokumenKewanganBayaranDAO dokumenKewanganBayaranDAO;
    private List<Transaksi> senaraiTransaksi = new ArrayList<Transaksi>();
    private List<DokumenKewanganBayaran> senaraiCaraBayaran = new ArrayList<DokumenKewanganBayaran>();

    @Inject
    public CetakSemulaSuratAkuanActionBean(TransaksiDAO transaksiDAO, DokumenKewanganBayaranDAO dokumenKewanganBayaranDAO,
                                           PenggunaDAO penggunaDAO, DokumenKewanganDAO dokumenKewanganDAO, PermohonanDAO permohonanDAO){
        this.transaksiDAO = transaksiDAO;
        this.dokumenKewanganBayaranDAO = dokumenKewanganBayaranDAO;
        this.penggunaDAO = penggunaDAO;
        this.dokumenKewanganDAO = dokumenKewanganDAO;
        this.permohonanDAO = permohonanDAO;
    }

    @Inject
    private ReportUtil reportUtil;
    
    @Inject
    private etanah.Configuration conf;

    @DefaultHandler
    public Resolution showForm() {
        return new ForwardResolution(DEFAULT_VIEW);
    }

    public Resolution search(){
        LOG.info("::START::");
        LOG.info("permohonan.getIdPermohonan() : "+permohonan.getIdPermohonan());
        if("04".equals(conf.getProperty("kodNegeri"))){
            kodNegeri = "melaka";
        }
        if("05".equals(conf.getProperty("kodNegeri"))){
            kodNegeri = "negeri";
        }
        permohonan = permohonanDAO.findById(permohonan.getIdPermohonan());
        if(permohonan != null){
            String [] n1 = {"permohonan"};
            Object [] v1 = {permohonan};
            senaraiTransaksi = transaksiDAO.findByEqualCriterias(n1, v1, null);
            LOG.info("senaraiTransaksi.size() : "+senaraiTransaksi.size());
            dokumenKewangan = new DokumenKewangan();
            String rst = "";
            for (Transaksi tr : senaraiTransaksi) {
                rst = tr.getDokumenKewangan().getIdDokumenKewangan();
                LOG.info("rst() : "+rst);
            }
            if(dokumenKewangan != null){

                dokumenKewangan = dokumenKewanganDAO.findById(rst);
//                LOG.info("dokumenKewangan.getIdDokumenKewangan() : "+dokumenKewangan.getIdDokumenKewangan());
                String p = dokumenKewangan.getIdDokumenKewangan().substring(10, 12);
                LOG.info("p : "+p);

                String [] v3 = {"idKaunter"};
                String [] v4 = {p};
                List<Pengguna> pList = penggunaDAO.findByEqualCriterias(v3, v4, null);
                String idPguna = "";
                for (Pengguna pguna : pList) {
                    idPguna = pguna.getIdPengguna();
                }
                pengguna = new Pengguna();
                pengguna = penggunaDAO.findById(idPguna);
            }

            String [] n2 = {"dokumenKewangan"};
            Object [] v2 = {dokumenKewangan};
            senaraiCaraBayaran = dokumenKewanganBayaranDAO.findByEqualCriterias(n2, v2, null);
        }

        return new ForwardResolution(DETAIL_VIEW);
    }

    public Resolution cetak() throws FileNotFoundException{
        etanahActionBeanContext ctx = new etanahActionBeanContext();
        ctx = (etanahActionBeanContext) getContext();

        pengguna = ctx.getUser();
        Date now = new Date();
        File f = null;
        String tarikh = (new SimpleDateFormat("ddMMyyyyhhmm")).format(now);
        String idMohon = getContext().getRequest().getParameter("idMohon");
        LOG.info("idMohon : "+idMohon);
        String documentPath = conf.getProperty("document.path");
        String path = tarikh + File.separator + String.valueOf(idMohon);
        reportUtil.generateReport("SPOCCetakanSemulaSuratAkuan_MLK.rdf",
                new String[]{"p_id_mohon"},
                new String[]{idMohon},
                documentPath + path, null);

        f = new File(documentPath + path);
        FileInputStream fis = new FileInputStream(f);
        return new StreamingResolution("application/pdf", fis);
    }

    public Resolution main(){
        dokumenKewangan = new DokumenKewangan();
        permohonan = new Permohonan();
        senaraiTransaksi = new ArrayList<Transaksi>();
        senaraiCaraBayaran = new ArrayList<DokumenKewanganBayaran>();
        return new RedirectResolution(CetakSemulaResitActionBean.class);
    }

    public Permohonan getPermohonan() {
        return permohonan;
    }

    public void setPermohonan(Permohonan permohonan) {
        this.permohonan = permohonan;
    }

    public DokumenKewangan getDokumenKewangan() {
        return dokumenKewangan;
    }

    public void setDokumenKewangan(DokumenKewangan dokumenKewangan) {
        this.dokumenKewangan = dokumenKewangan;
    }

    public DokumenKewanganBayaran getDokumenKewanganBayaran() {
        return dokumenKewanganBayaran;
    }

    public void setDokumenKewanganBayaran(DokumenKewanganBayaran dokumenKewanganBayaran) {
        this.dokumenKewanganBayaran = dokumenKewanganBayaran;
    }

    public Transaksi getTransaksi() {
        return transaksi;
    }

    public void setTransaksi(Transaksi transaksi) {
        this.transaksi = transaksi;
    }

    public List<Transaksi> getSenaraiTransaksi() {
        return senaraiTransaksi;
    }

    public void setSenaraiTransaksi(List<Transaksi> senaraiTransaksi) {
        this.senaraiTransaksi = senaraiTransaksi;
    }

    public List<DokumenKewanganBayaran> getSenaraiCaraBayaran() {
        return senaraiCaraBayaran;
    }

    public void setSenaraiCaraBayaran(List<DokumenKewanganBayaran> senaraiCaraBayaran) {
        this.senaraiCaraBayaran = senaraiCaraBayaran;
    }

    public Pengguna getPengguna() {
        return pengguna;
    }

    public void setPengguna(Pengguna pengguna) {
        this.pengguna = pengguna;
    }

    public String getKodNegeri() {
        return kodNegeri;
    }

    public void setKodNegeri(String kodNegeri) {
        this.kodNegeri = kodNegeri;
    }
}
