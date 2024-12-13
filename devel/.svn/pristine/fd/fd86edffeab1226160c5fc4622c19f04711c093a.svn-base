/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package etanah.view.kaunter;

import able.stripes.AbleActionBean;
import com.google.inject.Inject;
import etanah.dao.*;
import etanah.model.*;
import etanah.report.ReportUtil;
import etanah.sequence.GeneratorNoResit;
import etanah.view.etanahActionBeanContext;
import etanah.view.stripes.hasil.KutipanHasilActionBean;
import etanah.view.stripes.hasil.KutipanHasilManager;
import etanah.view.stripes.hasil.PenyataPemungutService;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.text.ParseException;
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

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 * 
 * @author abdul.hakim
 */
@UrlBinding("/kaunter/bayaran_pelbagai")
public class BayaranPelbagaiActionBean2 extends AbleActionBean {

    public static final String[] SENARAI_BAYARAN_MELAKA = { "40171", "51530",
            "51531", "51532", "79505", "79502", "81103", "81199", "74999",
            "79564", "72438", "51522", "79503", "73301", "71720", "71708",
            "71713", "71719", "79599", "74201", "81101","72467"};

    public static final String[] SENARAI_BAYARAN_NS = { "61999", "65096",
            "71711", "71799", "71784", "71803", "72199", "72499", "73151",
            "73199", "73601", "73605", "74999", "76152", "76199", "79501",
            "99020", "99021", "99022", "99023", "99024", "99025", "99026",
            "99027" };

    private static final Logger LOG = Logger
            .getLogger(BayaranPelbagaiActionBean2.class);

    private static final String DEFAULT_VIEW = "/WEB-INF/jsp/kaunter/kaunter_bayaran_pelbagai.jsp";

    // Kod Urusan to check
    private KodUrusan kodUrusan;

    private String urusan = "Bayaran Pelbagai";

    private int bil = 5;

    private BigDecimal jumlah;

    private List<BayaranPelbagaiValue> listUrusan = new ArrayList<BayaranPelbagaiValue>();

    private List<BayaranPelbagaiValue> listTrans = new ArrayList<BayaranPelbagaiValue>();

    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

    SimpleDateFormat yy = new SimpleDateFormat("yyyy");

    private String[] senaraiBayaran;

    private List<KodTransaksi> senaraiKodTransaksi = new ArrayList<KodTransaksi>();

    @Inject
    private KodUrusanDAO kodUrusanDAO;

    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;

    @Inject
    KutipanHasilManager manager;

    @Inject
    KodTransaksiDAO kodTransaksiDAO;

    @Inject
    GeneratorNoResit noResitGenerator;

    @Inject
    private etanah.Configuration conf;

    @Inject
    PenyataPemungutService pp;

    @Inject
    KutipanHasilActionBean kut;

    @Inject
    KodKutipanDAO kodKutipDAO;

    @Inject
    KodNegeriDAO kodNegeriDAO;

    @Inject
    BayaranSessionService bayaranSessionService;

    @DefaultHandler
    public Resolution showForm() {
        int x = showReports().length;
        LOG.debug("x : " + x);
        for (int i = 0; i < x; i++) {
            String kod = senaraiBayaran[i];
            LOG.info("kod : " + kod);
            KodTransaksi kodTransaksi = kodTransaksiDAO.findById(kod);
            LOG.info("kodTransaksi.getKod() : " + kodTransaksi.getKod());
            senaraiKodTransaksi.add(kodTransaksi);
        }
        LOG.debug("senaraiKodTransaksi.size() : " + senaraiKodTransaksi.size());
        // collectUrusan();

        if ((listUrusan.size() == 0) || (listTrans.size() == 0)) {
            for (int i = 0; i < bil; i++) {
                BayaranPelbagaiValue bpv = new BayaranPelbagaiValue();
                bpv.kategoriUrusan = 'Y';
                listUrusan.add(bpv);
                bpv = new BayaranPelbagaiValue();
                bpv.kategoriUrusan = 'T';
                bpv.setKuantiti(1);
                listTrans.add(bpv);
            }
            bil = listUrusan.size();
        }

        return new ForwardResolution(DEFAULT_VIEW);
    }

    private String[] showReports() {
        if ("04".equals(conf.getProperty("kodNegeri"))) {
            senaraiBayaran = SENARAI_BAYARAN_MELAKA;
        } else if ("05".equals(conf.getProperty("kodNegeri"))) {
            senaraiBayaran = SENARAI_BAYARAN_NS;
        }

        return senaraiBayaran;
    }

    public Resolution getCajForKod() {
        String kUrusan = getContext().getRequest().getParameter("kod");
        LOG.debug("kod=" + kUrusan);
        String results = "";
        kodUrusan = kodUrusanDAO.findById(kUrusan);
        if (kodUrusan != null) {
            String caj = kodUrusan.getCaj().toString();
            results = caj;
        } else {
            results = "xx";
        }
        return new StreamingResolution("text/plain", results);
    }

    public Resolution collectPayment() {
        etanahActionBeanContext ctx = (etanahActionBeanContext) getContext();
        // serialize to session
        if (listUrusan != null && listUrusan.size() > 0) {
            for (BayaranPelbagaiValue bp : listUrusan) {
                if (StringUtils.isNotBlank(bp.kodUrusan)
                        && !"0".equals(bp.kodUrusan)) {
                    int pos = bayaranSessionService.addUrusan(ctx,
                            (UrusanKaunter) bp);
                }
            }
        }

        if (listTrans != null && listTrans.size() > 0) {
            for (BayaranPelbagaiValue bp : listTrans) {
                if (StringUtils.isNotBlank(bp.kodUrusan)
                        && !"0".equals(bp.kodUrusan)) {
                    int pos = bayaranSessionService.addUrusan(ctx,
                            (UrusanKaunter) bp);
                }
            }
        }

        return new RedirectResolution(TerimaBayaran.class);
    }

    public KodUrusan getKodUrusan() {
        return kodUrusan;
    }

    public void setKodUrusan(KodUrusan kodUrusan) {
        this.kodUrusan = kodUrusan;
    }

    public List<KodUrusan> getSenaraiUrusan() {
        Session s = sessionProvider.get();
        Query q = s
                .createQuery("from etanah.model.KodUrusan u where u.urusanBayaran = 'Y' order by u.kod asc");
        return q.list();
    }

    public String getUrusan() {
        return urusan;
    }

    public void setUrusan(String urusan) {
        this.urusan = urusan;
    }

    public List<BayaranPelbagaiValue> getListUrusan() {
        return listUrusan;
    }

    public void setListUrusan(List<BayaranPelbagaiValue> listUrusan) {
        this.listUrusan = listUrusan;
    }

    public BigDecimal getJumlah() {
        return jumlah;
    }

    public void setJumlah(BigDecimal jumlah) {
        this.jumlah = jumlah;
    }

    public String[] getSenaraiBayaran() {
        return senaraiBayaran;
    }

    public void setSenaraiBayaran(String[] senaraiBayaran) {
        this.senaraiBayaran = senaraiBayaran;
    }

    public List<KodTransaksi> getSenaraiKodTransaksi() {
        return senaraiKodTransaksi;
    }

    public List<BayaranPelbagaiValue> getListTrans() {
        return listTrans;
    }

    public void setListTrans(List<BayaranPelbagaiValue> listTrans) {
        this.listTrans = listTrans;
    }

    public int getBil() {
        return bil;
    }

    public void setBil(int bil) {
        this.bil = bil;
    }

}
