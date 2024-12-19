package etanah.view.stripes.hasil;

import com.google.inject.Inject;
import org.apache.log4j.Logger;
import able.stripes.AbleActionBean;
import etanah.dao.KodCawanganDAO;
import etanah.dao.TransaksiDAO;
import etanah.model.KodCawangan;
import etanah.model.Pengguna;
import etanah.model.Transaksi;
import etanah.view.etanahActionBeanContext;
import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import net.sourceforge.stripes.action.Wizard;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.HandlesEvent;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.StreamingResolution;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author abdul.hakim
 */
@Wizard(startEvents = {"Step1","getTransaction"})
@UrlBinding("/hasil/denda_tahunan")
public class DendaTahunanActionBean extends AbleActionBean {

    private static final Logger logger = Logger.getLogger(DendaTahunanActionBean.class);
    private static String kodNegeri = null;
    private boolean flag = false;
    private String cawanganCukai;
    private String cawanganDenda;
    private String cawanganAchive;
    private BigDecimal total = new BigDecimal(0.00);
    private List<Transaksi> transList = new ArrayList<Transaksi>();
    SimpleDateFormat yy = new SimpleDateFormat("yyyy");
    SimpleDateFormat tarikh = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss a");
    private KodCawangan kodCawangan;
    private List<KodCawangan> senaraiDaerah = new ArrayList<KodCawangan>();
    @Inject
    private etanah.Configuration conf;
    @Inject
    private KodCawanganDAO kodCawanganDAO;
    @Inject
    private TransaksiDAO transaksiDAO;
    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;

    @HandlesEvent("Step1")
    @DefaultHandler
    public Resolution showForm() {
        logger.info("Step1");
        if ("04".equals(conf.getProperty("kodNegeri"))) {
            kodNegeri = "melaka";
        }
        if ("05".equals(conf.getProperty("kodNegeri"))) {
            kodNegeri = "negeriSembilan";
        }

        String sql = "SELECT d FROM KodCawangan d ORDER BY d.name";
        Session s = sessionProvider.get();
        Query q = s.createQuery(sql);

        senaraiDaerah = q.list();
        logger.info("senaraiDaerah.size() : " + senaraiDaerah.size());

        return new ForwardResolution("/WEB-INF/jsp/hasil/setup_denda_tahunan.jsp");
    }
    
    public Resolution getTransaction() {
        logger.info("..:: INSIDE getTransaction() ::..");
        String results = "";
        String caw = getContext().getRequest().getParameter("kodCawangan");
        Date now = new Date();
        String tahun = yy.format(now);
        
        logger.info("cawangan : "+caw+" tahun : "+tahun);
        List<Long> trList = new ArrayList<Long>();

        String sql = "SELECT tr.idTransaksi FROM etanah.model.Transaksi tr "
                + "WHERE tr.cawangan =:caw AND tr.status ='A' "
                + "AND tr.kodTransaksi ='76152' AND tr.untukTahun =:year";
        Session s = sessionProvider.get();
        Query q = s.createQuery(sql);
        q.setString("caw", caw);
        q.setInteger("year", Integer.parseInt(tahun));
        
        trList = q.list();
        logger.info("q.list().size() : "+q.list().size());
        logger.info("trList.size() : "+trList.size());
        
        if (trList.size()>0) {
            String sqlTr = "SELECT tr FROM etanah.model.Transaksi tr WHERE tr.idTransaksi =:tr";
            Query qTr = s.createQuery(sqlTr);
            
            Long id = trList.get(0);
            qTr.setLong("tr", id);
            Transaksi t = (Transaksi) qTr.uniqueResult();
            String trhJana = tarikh.format(t.getInfoAudit().getTarikhMasuk());
            results = trhJana+" oleh "+t.getInfoAudit().getDimasukOleh().getNama();
        } else {
            results = "O";
        }
        logger.info("results : "+results);
        return new StreamingResolution("text/plain", results);
    }
    
     public List<Long> getTransactionsStrata() {
        logger.info("..:: INSIDE getTransaction() ::..");
        Date now = new Date();
        String tahun = yy.format(now);
        
        logger.info("cawangan : "+cawanganDenda+" tahun : "+tahun);
        List<Long> trList = new ArrayList<Long>();

        String sql = "SELECT tr.idTransaksi FROM etanah.model.Transaksi tr "
                + "WHERE tr.cawangan =:caw AND tr.status ='A' "
                + "AND tr.kodTransaksi ='76156' AND tr.untukTahun =:year";
        Session s = sessionProvider.get();
        Query q = s.createQuery(sql);
        q.setString("caw", cawanganDenda);
        q.setInteger("year", Integer.parseInt(tahun));
        
        trList = q.list();
        logger.info("q.list().size() : "+q.list().size());
        logger.info("trList.size() : "+trList.size());

        return trList;
    }
    
    public List<Long> getTransactions() {
        logger.info("..:: INSIDE getTransaction() ::..");
        Date now = new Date();
        String tahun = yy.format(now);
        
        logger.info("cawangan : "+cawanganDenda+" tahun : "+tahun);
        List<Long> trList = new ArrayList<Long>();

        String sql = "SELECT tr.idTransaksi FROM etanah.model.Transaksi tr "
                + "WHERE tr.cawangan =:caw AND tr.status ='A' "
                + "AND tr.kodTransaksi ='76152' AND tr.untukTahun =:year";
        Session s = sessionProvider.get();
        Query q = s.createQuery(sql);
        q.setString("caw", cawanganDenda);
        q.setInteger("year", Integer.parseInt(tahun));
        
        trList = q.list();
        logger.info("q.list().size() : "+q.list().size());
        logger.info("trList.size() : "+trList.size());

        return trList;
    }

    public Resolution generateCukai() throws SQLException {
        logger.info("cawanganCukai: " + cawanganCukai);
        kodCawangan = kodCawanganDAO.findById(cawanganCukai);

        logger.info("kodCawangan.getName() : " + kodCawangan.getName());
        generateArchive();

        Date now = new Date();
        int year = Integer.parseInt(yy.format(now)) + 1;
        String tahun = Integer.toString(year);
        logger.info("tahun : " + tahun);

        Connection con = null;
        con = sessionProvider.get().connection();
//        con = sessionProvider.get().;
        setCukai(kodCawangan.getKod(), tahun, con);
        showForm();

        addSimpleMessage("Cukai Tanah bagi cawangan " + kodCawangan.getName() + " untuk tahun " + tahun + " telah berjaya dijana.");
        return new ForwardResolution("/WEB-INF/jsp/hasil/setup_cukai_tahunan.jsp");
    }

    public static void setCukai(String kodCaw, String tahun, Connection con1) throws SQLException {
        Connection con = null;
        CallableStatement proc = null;

        try {
//            if(kodNegeri.equals("melaka")){
////                con = sessionProvider.get().connection();
////                        DriverManager.getConnection("jdbc:oracle:thin:@10.0.4.34:1521:etanah", "jasin", "jasin");
//                proc = con.prepareCall("{call et_cukai.create_cukai(?, ?)}");
//            }
//            if(kodNegeri.equals("negeriSembilan")){
//                con = DriverManager.getConnection("jdbc:oracle:thin:@10.0.4.34:1521:etanah", "seremban", "seremban");
//                proc = con.prepareCall("{call et_cukai.create_cukai(?, ?)}");
//            }

            con = con1;
            proc = con.prepareCall("{call et_cukai.create_cukai(?, ?)}");
            proc.setString(1, kodCaw);
            proc.setString(2, tahun);
            logger.info("proc : " + proc);
            proc.executeUpdate();
        } finally {
            try {
                proc.close();
            } catch (SQLException e) {
            }
            con.close();
        }
    }
    
     public Resolution generateDenda() throws SQLException {
        logger.info("cawanganDenda : " + cawanganDenda);
        kodCawangan = kodCawanganDAO.findById(cawanganDenda);
        etanahActionBeanContext ctx = new etanahActionBeanContext();
        ctx = (etanahActionBeanContext) getContext();
        Pengguna pguna = ctx.getUser();
        String id = pguna.getIdPengguna();

        logger.info("kodCawangan.getName() : " + kodCawangan.getName());
        Date now = new Date();
        String tahun = yy.format(now);
        logger.info("tahun : " + tahun);
        List<Long> senaraiDenda = getTransactions();
        logger.info("senaraiDenda.size() : "+senaraiDenda.size());
//        if(senaraiDenda.size() > 0){
//            logger.info("Denda telah dijana");
//            String sql = "SELECT tr FROM etanah.model.Transaksi tr "
//                + "WHERE tr.idTransaksi =:id ";
//            Session s = sessionProvider.get();
//            Query q = s.createQuery(sql);
//            q.setLong("id", senaraiDenda.get(0));
//            Transaksi t = (Transaksi) q.uniqueResult();
//            flag = true;
//            String trhJana = tarikh.format(t.getInfoAudit().getTarikhMasuk());
//            addSimpleMessage("Denda telah dijana pada "+trhJana+" oleh "+t.getInfoAudit().getDimasukOleh().getNama()+". Sila tekan butang Cetak untuk mencetak laporan.");
//        }else{

            Connection con = null;
            con = sessionProvider.get().connection();
            setDenda(kodCawangan.getKod(), tahun, con, id);
            showForm();

            transList = getTransaksiDenda(kodCawangan.getKod(), tahun);
            logger.info("transList.size() : " + transList.size());
            for (Transaksi t : transList) {
                total = total.add(t.getAmaun());}
            logger.info("total : "+total);
            addSimpleMessage("Denda Lewat bagi cawangan " + kodCawangan.getName() + " telah berjaya dijana.");
//        }
        return new ForwardResolution("/WEB-INF/jsp/hasil/setup_denda_tahunan1.jsp");
    }

    public Resolution generateDendaStrata() throws SQLException {
        logger.info("cawanganDenda : " + cawanganDenda);
        kodCawangan = kodCawanganDAO.findById(cawanganDenda);
        etanahActionBeanContext ctx = new etanahActionBeanContext();
        ctx = (etanahActionBeanContext) getContext();
        Pengguna pguna = ctx.getUser();
        String id = pguna.getIdPengguna();

        logger.info("kodCawangan.getName() : " + kodCawangan.getName());
        Date now = new Date();
        String tahun = yy.format(now);
        logger.info("tahun : " + tahun);
        List<Long> senaraiDenda = getTransactionsStrata();
        logger.info("senaraiDenda.size() : "+senaraiDenda.size());


            Connection con = null;
            con = sessionProvider.get().connection();
            setDendaStrata(kodCawangan.getKod(), tahun, con, id);
            showForm();

            transList = getTransaksiDendaStrata(kodCawangan.getKod(), tahun);
            logger.info("transList.size() : " + transList.size());
            for (Transaksi t : transList) {
                total = total.add(t.getAmaun());}
            logger.info("total : "+total);
            addSimpleMessage("Denda Lewat bagi cawangan " + kodCawangan.getName() + " telah berjaya dijana.");
//        }
        return new ForwardResolution("/WEB-INF/jsp/hasil/setup_denda_tahunan1.jsp");
    }
    
     public List<Transaksi> getTransaksiDendaStrata(String caw, String tahun) {
        List<Transaksi> trList = new ArrayList<Transaksi>();

        String sql = "SELECT tr FROM etanah.model.Transaksi tr "
                + "WHERE tr.cawangan =:caw AND tr.status ='A' "
                + "AND tr.kodTransaksi ='76156' AND tr.untukTahun =:year";
        Session s = sessionProvider.get();
        Query q = s.createQuery(sql);
        q.setString("caw", caw);
        q.setInteger("year", Integer.parseInt(tahun));
        
        trList = q.list();

        return trList;
    }

    public List<Transaksi> getTransaksiDenda(String caw, String tahun) {
        List<Transaksi> trList = new ArrayList<Transaksi>();

        String sql = "SELECT tr FROM etanah.model.Transaksi tr "
                + "WHERE tr.cawangan =:caw AND tr.status ='A' "
                + "AND tr.kodTransaksi ='76152' AND tr.untukTahun =:year";
        Session s = sessionProvider.get();
        Query q = s.createQuery(sql);
        q.setString("caw", caw);
        q.setInteger("year", Integer.parseInt(tahun));
        
        trList = q.list();

        return trList;
    }
    
     public static void setDendaStrata(String kodCaw, String tahun, Connection conc, String pguna) throws SQLException {
        Connection con = null;
        CallableStatement proc = null;
        logger.info("pguna : " + pguna);

        try {
            con = conc;
            if (kodNegeri.equals("melaka")) {
//                con = DriverManager.getConnection("jdbc:oracle:thin:@10.0.4.34:1521:etanah", "jasin", "jasin");
//                proc = con.prepareCall("{call ET_DENDA.create_denda(?, ?, ?)}");
                proc = con.prepareCall("{call et_denda_ml_v2_strata(?, ?)}");
            }
            if (kodNegeri.equals("negeriSembilan")) {
//                con = DriverManager.getConnection("jdbc:oracle:thin:@10.0.4.34:1521:etanah", "seremban", "seremban");
                proc = con.prepareCall("{call ET_DENDA_NS.create_denda(?, ?, ?)}");
            }
            proc.setString(1, kodCaw);
            proc.setString(2, tahun);
//            proc.setString(3, pguna);
            logger.info("proc : " + proc);
            proc.executeUpdate();
        } finally {
            try {
                proc.close();
            } catch (SQLException e) {
            }
            con.close();
        }
    }

    public static void setDenda(String kodCaw, String tahun, Connection conc, String pguna) throws SQLException {
        Connection con = null;
        CallableStatement proc = null;
        logger.info("pguna : " + pguna);

        try {
            con = conc;
            if (kodNegeri.equals("melaka")) {
//                con = DriverManager.getConnection("jdbc:oracle:thin:@10.0.4.34:1521:etanah", "jasin", "jasin");
//                proc = con.prepareCall("{call ET_DENDA.create_denda(?, ?, ?)}");
                proc = con.prepareCall("{call et_denda_ml_v2(?, ?)}");
            }
            if (kodNegeri.equals("negeriSembilan")) {
//                con = DriverManager.getConnection("jdbc:oracle:thin:@10.0.4.34:1521:etanah", "seremban", "seremban");
                proc = con.prepareCall("{call ET_DENDA_NS.create_denda(?, ?, ?)}");
            }
            proc.setString(1, kodCaw);
            proc.setString(2, tahun);
//            proc.setString(3, pguna);
            logger.info("proc : " + proc);
            proc.executeUpdate();
        } finally {
            try {
                proc.close();
            } catch (SQLException e) {
            }
            con.close();
        }
    }

    public Resolution generateArchive() throws SQLException {
//        logger.info("cawanganAchive : "+cawanganAchive);
//        kodCawangan = kodCawanganDAO.findById(cawanganAchive);
        kodCawangan = kodCawanganDAO.findById(cawanganCukai);

        logger.info("kodCawangan.getName() : " + kodCawangan.getName());
        Date now = new Date();
        String tahun = yy.format(now);
        logger.info("tahun : " + tahun);

        Connection con = null;
        con = sessionProvider.get().connection();
        setArchive(kodCawangan.getKod(), tahun, con);
        showForm();

        addSimpleMessage("Archive bagi cawangan " + kodCawangan.getName() + " telah berjaya dijana.");
        return new ForwardResolution("/WEB-INF/jsp/hasil/setup_cukai_tahunan.jsp");
    }

    public static void setArchive(String kodCaw, String tahun, Connection conc) throws SQLException {
        Connection con = null;
        CallableStatement proc = null;

        try {
            con = conc;
            proc = con.prepareCall("{call et_archive.archive_trans(?, ?)}");
//            if(kodNegeri.equals("melaka")){
//                con = DriverManager.getConnection("jdbc:oracle:thin:@10.0.4.34:1521:etanah", "jasin", "jasin");
//                proc = con.prepareCall("{call et_archive.archive_trans(?, ?)}");
//            }
//            if(kodNegeri.equals("negeriSembilan")){
//                con = DriverManager.getConnection("jdbc:oracle:thin:@10.0.4.34:1521:etanah", "seremban", "seremban");
//                proc = con.prepareCall("{call et_archive.archive_trans(?, ?)}");
//            }
            proc.setString(1, kodCaw);
            proc.setString(2, tahun);
            logger.info("proc : " + proc);
            proc.executeUpdate();
        } finally {
            try {
                proc.close();
            } catch (SQLException e) {
            }
            con.close();
        }
    }

    public String getKodNegeri() {
        return kodNegeri;
    }

    public void setKodNegeri(String kodNegeri) {
        this.kodNegeri = kodNegeri;
    }

    public List<KodCawangan> getSenaraiDaerah() {
        return senaraiDaerah;
    }

    public void setSenaraiDaerah(List<KodCawangan> senaraiDaerah) {
        this.senaraiDaerah = senaraiDaerah;
    }

    public KodCawangan getKodCawangan() {
        return kodCawangan;
    }

    public void setKodDaerah(KodCawangan kodCawangan) {
        this.kodCawangan = kodCawangan;
    }

    public String getCawanganCukai() {
        return cawanganCukai;
    }

    public void setCawanganCukai(String cawanganCukai) {
        this.cawanganCukai = cawanganCukai;
    }

    public String getCawanganDenda() {
        return cawanganDenda;
    }

    public void setCawanganDenda(String cawanganDenda) {
        this.cawanganDenda = cawanganDenda;
    }

    public String getCawanganAchive() {
        return cawanganAchive;
    }

    public void setCawanganAchive(String cawanganAchive) {
        this.cawanganAchive = cawanganAchive;
    }

    public List<Transaksi> getTransList() {
        return transList;
    }

    public void setTransList(List<Transaksi> transList) {
        this.transList = transList;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }
}
