package etanah.view.stripes.hasil;

import com.google.inject.Inject;
import org.apache.log4j.Logger;
import able.stripes.AbleActionBean;
import etanah.dao.KodCawanganDAO;
import etanah.model.KodCawangan;
import etanah.model.Pengguna;
import etanah.view.etanahActionBeanContext;
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
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author abdul.hakim
 */
@Wizard(startEvents = {"Step1"})
@UrlBinding("/hasil/cukai_tahunan")
public class CukaiTahunanActionBean extends AbleActionBean {
    private static final Logger logger = Logger.getLogger(CukaiTahunanActionBean.class);

    private static String kodNegeri = null;
    private String cawanganCukai;
    private String cawanganDenda;
    private String cawanganAchive;
    private int tahunSetup = 0;
    private boolean flag = false;
    SimpleDateFormat yy = new SimpleDateFormat("yyyy");
    
    private KodCawangan kodCawangan;
    private List<KodCawangan> senaraiDaerah = new ArrayList<KodCawangan>();

    @Inject
    private etanah.Configuration conf;

    @Inject
    private KodCawanganDAO kodCawanganDAO;

    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;

    
    @HandlesEvent("Step1")
    @DefaultHandler
    public Resolution showForm() {
        logger.info("Step1");
        if("04".equals(conf.getProperty("kodNegeri"))){
            kodNegeri = "melaka";
        }
        if("05".equals(conf.getProperty("kodNegeri"))){
            kodNegeri = "negeriSembilan";
        }

        tahunSetup = Integer.parseInt(yy.format(new Date()));
        logger.info(tahunSetup);
        
        String sql = "select d from KodDaerah d order by d.nama";
        Session s = sessionProvider.get();
        Query q = s.createQuery(sql);

        senaraiDaerah = q.list();
        logger.info("senaraiDaerah.size() : "+senaraiDaerah.size());

        return new ForwardResolution("/WEB-INF/jsp/hasil/setup_cukai_tahunan.jsp");
    }

    public Resolution generateCukai() throws SQLException{
        logger.info("cawanganCukai: "+cawanganCukai);
        kodCawangan = kodCawanganDAO.findById(cawanganCukai);
        etanahActionBeanContext ctx = new etanahActionBeanContext();
        ctx = (etanahActionBeanContext) getContext();
        Pengguna pguna = ctx.getUser();
        String id = pguna.getIdPengguna();

        logger.info("kodCawangan.getName() : "+kodCawangan.getName());
        generateArchive();

//        Date now = new Date();
//        int year = Integer.parseInt( yy.format(now))+1;
//        String tahun =Integer.toString(year);
        String tahun = Integer.toString(tahunSetup);
        String thn = tahun;
        logger.info("tahun : "+tahun);

        Connection con = null;
        con = sessionProvider.get().connection();
//        con = sessionProvider.get().;
        setCukai(kodCawangan.getKod(), tahun, con, id);
        showForm();
        flag = true;
        addSimpleMessage("Cukai Tanah bagi cawangan "+kodCawangan.getName()+" untuk tahun "+thn+" telah berjaya dijana.");
        return new ForwardResolution("/WEB-INF/jsp/hasil/setup_cukai_tahunan.jsp");
    }

    public static void setCukai(String kodCaw, String tahun, Connection con1, String pguna) throws SQLException {
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
            proc = con.prepareCall("{call et_cukai.create_cukai(?, ?, ?)}");
            proc.setString(1, kodCaw);
            proc.setString(2, tahun);
            proc.setString(3, pguna);
            logger.info("proc : "+proc);
            proc.executeUpdate();
        }
        finally {
            try {
                proc.close();
            } catch (SQLException e) {
            }
            con.close();
        }
    }

    public Resolution generateDenda() throws SQLException{
        logger.info("cawanganDenda : "+cawanganDenda);
        kodCawangan = kodCawanganDAO.findById(cawanganDenda);

        logger.info("kodCawangan.getName() : "+kodCawangan.getName());
        Date now = new Date();
        String tahun = yy.format(now);
        logger.info("tahun : "+tahun);

        Connection con = null;
        con = sessionProvider.get().connection();
        setDenda(kodCawangan.getKod(), tahun, con);
        showForm();

        addSimpleMessage("Denda Lewat bagi cawangan "+kodCawangan.getName()+" telah berjaya dijana.");
        return new ForwardResolution("/WEB-INF/jsp/hasil/setup_cukai_tahunan.jsp");
    }

    public static void setDenda(String kodCaw, String tahun, Connection conc) throws SQLException {
        Connection con = null;
        CallableStatement proc = null;

        try {
            con = conc;
            if(kodNegeri.equals("melaka")){
//                con = DriverManager.getConnection("jdbc:oracle:thin:@10.0.4.34:1521:etanah", "jasin", "jasin");
                proc = con.prepareCall("{call ET_DENDA_ML.create_denda(?, ?)}");
            }
            if(kodNegeri.equals("negeriSembilan")){
//                con = DriverManager.getConnection("jdbc:oracle:thin:@10.0.4.34:1521:etanah", "seremban", "seremban");
                proc = con.prepareCall("{call ET_DENDA_NS.create_denda(?, ?)}");
            }
            proc.setString(1, kodCaw);
            proc.setString(2, tahun);
            logger.info("proc : "+proc);
            proc.executeUpdate();
        }
        finally {
            try {
                proc.close();
            } catch (SQLException e) {
            }
            con.close();
        }
    }

    public Resolution generateArchive() throws SQLException{
//        logger.info("cawanganAchive : "+cawanganAchive);
//        kodCawangan = kodCawanganDAO.findById(cawanganAchive);
        kodCawangan = kodCawanganDAO.findById(cawanganCukai);

        logger.info("kodCawangan.getName() : "+kodCawangan.getName());
        Date now = new Date();
        String tahun = "";
        String tahun1 = yy.format(now);
        if(tahun1.equals(tahunSetup)){
            int prevYear = tahunSetup-1;
            tahun = Integer.toString(prevYear);
        }else{
            tahun = yy.format(now);
        }
        logger.info("tahun : "+tahun);

        Connection con = null;
        con = sessionProvider.get().connection();
        setArchive(kodCawangan.getKod(), tahun, con);
//        showForm();

        addSimpleMessage("Archive bagi cawangan "+kodCawangan.getName()+" telah berjaya dijana.");
        return new ForwardResolution("/WEB-INF/jsp/hasil/setup_cukai_tahunan.jsp");
    }

    public static void setArchive(String kodCaw, String tahun, Connection conc) throws SQLException {
        Connection con = null;
        CallableStatement proc = null;
        int nextYear = Integer.parseInt(tahun)+1;
        String tahunDepan = Integer.toString(nextYear);
        logger.info("tahunDepan : "+tahunDepan);

        try {
            con = conc;
//            proc = con.prepareCall("{call et_archive.archive_trans(?, ?)}");
//            proc = con.prepareCall("{call et_archive2.archive_kew_trans(?, ?, ?)}");                  //24-Jan-2014
            proc = con.prepareCall("{call et_archive3.archive_trans(?, ?, ?)}");
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
            proc.setString(3, tahunDepan);
            logger.info("proc : "+proc);
            proc.executeUpdate();
        }
        finally {
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

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public int getTahunSetup() {
        return tahunSetup;
    }

    public void setTahunSetup(int tahunSetup) {
        this.tahunSetup = tahunSetup;
    }
}
