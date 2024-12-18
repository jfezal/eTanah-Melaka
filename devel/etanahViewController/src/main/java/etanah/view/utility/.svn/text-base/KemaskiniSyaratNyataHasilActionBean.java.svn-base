package etanah.view.utility;

import able.stripes.*;
import com.google.inject.Inject;
import etanah.dao.*;
import etanah.model.*;
import etanah.service.RegService;
import java.io.*;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.*;
import net.sourceforge.stripes.action.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.logging.Level;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.hibernate.*;
import etanah.view.etanahActionBeanContext;

/**
 * @wazer
 */
@UrlBinding("/utility/kemaskini")
public class KemaskiniSyaratNyataHasilActionBean extends AbleActionBean {

    @Inject
    private etanah.Configuration conf;
    @Inject
    private KodDaerahDAO kodDaerahDAO;
    @Inject
    private KodBangsaDAO kodBangsaDAO;
    @Inject
    private KodSyaratNyataDAO kodSyaratNyataDAO;
    @Inject
    KodKategoriTanahDAO kodKategoriTanahDAO;
    @Inject
    RegService regService;
    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;
    private static final Logger LOG = Logger.getLogger(KemaskiniSyaratNyataHasilActionBean.class);
    static SimpleDateFormat sdf2 = new SimpleDateFormat("dd MMMM yyyy");
    static SimpleDateFormat sdfNoSlash = new SimpleDateFormat("ddMMyyyy");
    static SimpleDateFormat sdfWithTime = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
    static DecimalFormat df2 = new DecimalFormat("0.00");
    static DecimalFormat dFormat = new DecimalFormat("#,###,##0.00");
    private static final String DEFAULT_VIEW = "/WEB-INF/jsp/utiliti/kemaskini_syarat.jsp";
    private static final String KEMASKINI_VIEW = "/WEB-INF/jsp/utiliti/Kemaskini_view.jsp";
    private static String kodNegeri;
    private String daerah = "";
    private String reportNo = "";
    private String tarikh = "";
    private String kodBpm = "";
    private String jenisHakmilik = "";
    private String bangsa = "";
    private String downloadLink = "";
    private boolean flagJana = false;
    private BigDecimal amaun = new BigDecimal(0.00);
    private Pengguna pengguna;
//    static String etanahPath = "/home/haqqiem/";       // local
    static String etanahPath = "/nfs_apps/dms/";       // server 
    private String[] listReport;
    private List<KodDaerah> senaraiDaerah = new ArrayList<KodDaerah>();
    private List<KodSyaratNyata> senaraiSyaratNyata = new ArrayList<KodSyaratNyata>();
    private KodSyaratNyata kodSyaratNyata;
    String kodSyarat;
    String katg;
    String syarat;

    @DefaultHandler
    public Resolution showForm() {
        pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        String kodCaw = pengguna.getKodCawangan().getKod();
        senaraiSyaratNyata = regService.searchKodSyaratByCawOnly(kodCaw);
//        getSenaraiSyaratNyata(kodCaw);
        return new ForwardResolution(DEFAULT_VIEW);
    }

    public Resolution kemaskiniView() {
        pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        kodSyarat = getContext().getRequest().getParameter("kodSyarat");

        kodSyaratNyata = kodSyaratNyataDAO.findById(kodSyarat);
        if (kodSyaratNyata.getKategoriHasil() != null) {
            katg = kodSyaratNyata.getKategoriHasil().getKod();
        }


//        getSenaraiSyaratNyata(kodCaw);
//         return new JSP(KEMASKINI_VIEW).addParameter("popup", "true");
        return new ForwardResolution(KEMASKINI_VIEW);
    }

    public Resolution CariKodSyarat() {
        pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        syarat = getContext().getRequest().getParameter("syarat");
        String kodCaw = pengguna.getKodCawangan().getKod();
        kodSyaratNyata = kodSyaratNyataDAO.findById(syarat);
        if (kodSyaratNyata != null) {
            senaraiSyaratNyata.add(kodSyaratNyata);
        } else {
            senaraiSyaratNyata = regService.searchKodSyaratByCawOnly(kodCaw);
        }
//        getSenaraiSyaratNyata(kodCaw);
//         return new JSP(KEMASKINI_VIEW).addParameter("popup", "true");
        return new ForwardResolution(DEFAULT_VIEW);
    }

    public Resolution simpanKemaskini() {
        pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
//        String kodSyarat = getContext().getRequest().getParameter("kodSyarat");
        katg = getContext().getRequest().getParameter("katg");
        kodSyarat = getContext().getRequest().getParameter("kodSyarat");
        kodSyaratNyata = kodSyaratNyataDAO.findById(kodSyarat);
        if (kodSyaratNyata != null) {
            KodKategoriTanah kategori = kodKategoriTanahDAO.findById(katg);
            if (kategori != null) {
                kodSyaratNyata.setKategoriHasil(kategori);
//                 kodSyaratNyataDAO.save(kodSyaratNyata);
                regService.simpanKodSyaratNyata(kodSyaratNyata);
            }
            addSimpleMessage("Kemasukan Berjaya");
        }
        return new ForwardResolution(KEMASKINI_VIEW);
    }

    public Pengguna getPengguna() {
        return pengguna;
    }

    public void setPengguna(Pengguna pengguna) {
        this.pengguna = pengguna;
    }

    public List<KodSyaratNyata> getSenaraiSyaratNyata() {
        return senaraiSyaratNyata;
    }

    public void setSenaraiSyaratNyata(List<KodSyaratNyata> senaraiSyaratNyata) {
        this.senaraiSyaratNyata = senaraiSyaratNyata;
    }

    public KodSyaratNyata getKodSyaratNyata() {
        return kodSyaratNyata;
    }

    public void setKodSyaratNyata(KodSyaratNyata kodSyaratNyata) {
        this.kodSyaratNyata = kodSyaratNyata;
    }

    public String getKatg() {
        return katg;
    }

    public void setKatg(String katg) {
        this.katg = katg;
    }
}
