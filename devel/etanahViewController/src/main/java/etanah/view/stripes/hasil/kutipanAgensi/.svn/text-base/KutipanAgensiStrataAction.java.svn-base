/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.stripes.hasil.kutipanAgensi;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.dao.*;
import etanah.dao.hasil.LaporanPenyataPemungutDAO;
import etanah.model.*;
import etanah.model.hasil.LaporanPenyataPemungutItem;
import etanah.sequence.GeneratorIdLaporanPenyataPemungut;
import etanah.sequence.GeneratorNoResit;
import etanah.service.AkaunService;
import etanah.service.common.HakmilikService;
import etanah.view.etanahActionBeanContext;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.*;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.FileBean;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.StreamingResolution;
import net.sourceforge.stripes.action.UrlBinding;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import java.text.ParseException;
import net.sourceforge.stripes.action.RedirectResolution;

/**
 *
 * @author fikri
 */
@UrlBinding("/hasil/kutipan_agensi_strata")
public class KutipanAgensiStrataAction extends AbleActionBean {
    
    private FileBean txtFile;
    private String kodAgensi;
    private String kodAgensiCawangan;
    private List<KodAgensiKutipanCawangan> senaraiAgensiCawangan;
    private List<Transaksi> senaraiTransaksi;
    private List<Map<String, Object>> senaraiTrans;
    private List<Map<String, Object>> senaraiFail;
    private static String MAIN_PAGE = "hasil/kutipan/kutipan_agensi_strata_main.jsp";
    private static String UPDATE_PAGE = "hasil/kutipan/kutipan_agensi_strata_update.jsp";
    private String fileName;
    @Inject
    private KodAgensiKutipanCawanganDAO kodAgensiKutipanCawanganDAO;
    @Inject
    private KodAgensiKutipanDAO kodAgensiKutipanDAO;
    @Inject
    private KodStatusDokumenKewanganDAO kodStatusDokumenKewanganDAO;
    @Inject
    private KodStatusTransaksiKewanganDAO kodStatusTransaksiKewanganDAO;
    @Inject
    private KodTransaksiDAO kodTransaksiDAO;
    @Inject
    private KodKutipanDAO kodKutipanDAO;
    @Inject
    private KodDokumenDAO kodDokumenDAO;
    @Inject
    private KodCaraBayaranDAO kodCaraBayaranDAO;
    @Inject
    private DokumenKewanganDAO dokumenKewanganDAO;
    @Inject
    private DokumenKewanganBayaranDAO dokumenKewanganBayaranDAO;
    @Inject
    private CaraBayaranDAO caraBayaranDAO;
    @Inject
    private TransaksiDAO transaksiDAO;
    @Inject
    private AkaunStrataDAO akaunStrataDAO;
    @Inject
    private LaporanPenyataPemungutDAO laporanPenyataPemungutItemDAO;
    @Inject
    private HakmilikService hakmilikService;
    @Inject
    private GeneratorIdLaporanPenyataPemungut generator;
    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;
    @Inject
    private KutipanAgensiService kutipanAgensiService;
    @Inject
    private GeneratorNoResit noResitGenerator;
    @Inject
    private AkaunDAO akaunDAO;
    @Inject
    private AkaunService akaunService;
    @Inject    
    private KodCaraBayaranAgensiDAO kodAgensiBayaranAgensiDAO;
    @Inject    
    private KodCaraBayaran kodCaraBayaran;
    @Inject
    etanah.Configuration conf;
    private String total;
    private static Logger LOG = Logger.getLogger(KutipanAgensiAction.class);
    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
    //jdbc:oracle:thin:@10.66.128.25:1521/etnsstg
    //jdbc:oracle:thin:@localhost:1521/xe
//    private String URL = "jdbc:oracle:thin:@primus:1521/etanah";//todo put inside etanah.properties (office)
//    private String URL = "jdbc:oracle:thin:@10.66.128.25:1521/etnsstg";//todo put inside etanah.properties (site)
    private String URL = "jdbc:oracle:thin:@10.66.130.25:1521/etmlstg";
    SimpleDateFormat dfm = new java.text.SimpleDateFormat("dd/MM/yyyy");
    SimpleDateFormat dfmTime = new java.text.SimpleDateFormat("hh:mm:ssaa");    
    SimpleDateFormat yy = new SimpleDateFormat("yyyy");
    public StringBuilder sb_error = new StringBuilder();
    
    @DefaultHandler
    public Resolution showForm() {
        reset();
        return new JSP(MAIN_PAGE);
    }
    
    public Resolution searchForm() {
        reset();
        return new JSP(UPDATE_PAGE);
    }
 
    public Resolution hapusFail() throws ParseException {
        String Nama_Fail = getContext().getRequest().getParameter("Nama_Fail");
        Connection c = getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        try {           
            
            StringBuilder sb = new StringBuilder("Delete from transaksi where nama_fail = ?");
                               
            ps = c.prepareStatement(sb.toString());
            ps.setString(1, Nama_Fail);
            rs = ps.executeQuery();            
        }        
        catch (Exception ex) {
            LOG.error("[ Kutipan Error ]", ex);
        }finally {
            try {
                if (c != null) {
                    LOG.debug("connection close");
                    c.close();
                }
                if (ps != null) {
                    ps.close();
                }
                if (rs != null) {
                    rs.close();
                }
            } catch (Exception ex) {
                LOG.error("[ Kutipan Error ]", ex);
            }
        }
        return new RedirectResolution(KutipanAgensiAction.class, "searchByAgensi").addParameter("kodAgensi", kodAgensi);

    }


    public Resolution searchByAgensi() {
        
        reset();
        
        if (StringUtils.isBlank(kodAgensi)) {
            addSimpleError("Sila pilih agensi.");
        } else {
            
            Connection c = getConnection();
            PreparedStatement ps = null;
            ResultSet rs = null;
            senaraiFail = new ArrayList<Map<String, Object>>();
            
            try {
                
                StringBuilder sb = new StringBuilder("Select count(nama_fail), nama_fail from transaksi where KOD_AGENSI = ?")
                        .append(" AND STATUS_HANTAR in ('0','2')")
                        .append(" GROUP BY nama_fail");
                ps = c.prepareStatement(sb.toString());
                ps.setString(1, kodAgensi);
//                ps.setString(2, "2");
                rs = ps.executeQuery();
                
                while (rs.next()) {                    
                    
                    Map<String, Object> map = new HashMap<String, Object>();
//                    map.put("jumTran",rs.getString(1));
                    fileName = rs.getString(2);
                    map = getDetailTransaction(c, fileName);
                    map.put("namaFail", fileName);
                    senaraiFail.add(map);                    
                }
            } catch (Exception ex) {
                LOG.error("[ Kutipan Error ]", ex);
            } finally {
                try {
                    if (c != null) {
                        c.close();
                    }
                    if (ps != null) {
                        ps.close();
                    }
                    if (rs != null) {
                        rs.close();
                    }
                } catch (Exception ex) {
                    LOG.error("[ Kutipan Error ]", ex);
                }
            }
        }
        return new JSP(UPDATE_PAGE);
    }
    
    private Map<String, Object> getDetailTransaction(Connection c, String namaFail) {
        
        Map<String, Object> map = new HashMap<String, Object>();        
        int gagal = 0;
        int belumPosting = 0;
        
            PreparedStatement ps2 = null;
            ResultSet rs2 = null;
        
        try {            
  
            StringBuilder sb = new StringBuilder("Select STATUS_HANTAR from transaksi where nama_fail = ?")
                    .append(" AND STATUS_HANTAR in ('0','2')");
            
            ps2 = c.prepareStatement(sb.toString());
            ps2.setString(1, namaFail);
//                ps.setString(2, "2");
            rs2 = ps2.executeQuery();
            while (rs2.next()) {                
                String stsHantar = rs2.getString(1);
                if (StringUtils.isBlank(stsHantar)) {
                    continue;
                }
                if (stsHantar.equals("0")) {
                    belumPosting = belumPosting + 1;
                } else {
                    gagal = gagal + 1;
                }
            }
            map.put("gagal", gagal);
            map.put("belumPosting", belumPosting);
        } catch (Exception ex) {
            LOG.error("[ Kutipan Error ]", ex);
        }
//        finally {
//                try {                 
//                    if (ps2 != null) {
//                        ps2.close();
//                    }
//                    if (rs2 != null) {
//                        rs2.close();
//                    }
//                } catch (Exception ex) {
//                    LOG.error("[ Kutipan Error ]", ex);
//                }
//            }
        

        
        
        return map;
    }
    
    public Resolution search() {        

        senaraiTransaksi = null;
        senaraiTrans = null;
        senaraiFail = null;

        
        senaraiTransaksi = getTransaksi(fileName, "2");
        
        senaraiTrans = new ArrayList<Map<String, Object>>();        
        BigDecimal totalAmt = BigDecimal.ZERO;        
        
        for (Transaksi trans : senaraiTransaksi) {
            Map<String, Object> map = new HashMap<String, Object>();            
            
            
            map.put("id", trans.transID);
            map.put("akaunCukai", trans.akaunCukai);
            map.put("amaun", trans.bayaran);
            map.put("status", trans.statusHantar);
            map.put("ulasan", trans.ulasan);            
            map.put("resit_agensi", trans.noResitAgensi);
            map.put("trh_resit_manual", trans.masaByr);
            
            totalAmt = totalAmt.add(trans.bayaran);
            
            senaraiTrans.add(map);
            
            map = null;
            
        }
        
        total = String.valueOf(totalAmt);
        
        if (senaraiTrans.isEmpty()) {            
            addSimpleError("Tiada Rekod Dijumpai. Sila pastikan nama fail anda betul.");
        }
        
        return new JSP(UPDATE_PAGE);
    }
    
    public Resolution update() {
        
        String ids[] = getContext().getRequest().getParameterValues("id");
        String noAkauns[] = getContext().getRequest().getParameterValues("noAkaun");
        
        if (ids == null || noAkauns == null) {
            return new StreamingResolution("text/plain", "1");
        }
        
        if (ids.length > 0 && noAkauns.length > 0) {
            for (int i = 0; i < ids.length; i++) {
                LOG.debug("id = " + ids[i]);
                LOG.debug("noAkaun = " + noAkauns[i]);
                String msg = checkAkaun(ids[i], noAkauns[i]);
                
                if (StringUtils.isNotBlank(msg)) {                    
                    return new StreamingResolution("text/plain", msg);
                }
            }
            
            updateNoAkaun(noAkauns, ids);
            
        }       
        reset();
        return new JSP(UPDATE_PAGE);
    }
    
    private String checkAkaun(String id, String noAkaun) {
        String msg = "";
        Akaun akaun = akaunDAO.findById(noAkaun);
        if (akaun == null) {
            /** 
                 * modified by hakim
                 * purpose  : idHakmilik as No Akaun
                 * Line     : 323 - 329
                */
            Hakmilik hm1 = hakmilikService.findById(noAkaun);
            if(hm1 == null){
            return "1|" + noAkaun;
            }else{
                akaun = hm1.getAkaunCukai();
                //System.out.println("akaun.getNoAkaun() : "+akaun.getNoAkaun());
            }
        }
        
        Boolean nextStep = true;
        
        Hakmilik hm = akaun.getHakmilik();
        if (hm.getKodStatusHakmilik().getKod().equals("B")) {
            //hakmilik sambungan if any
            List<Hakmilik> senaraiHakmilikSambungan = hakmilikService.getHakmilikSambungan(hm.getIdHakmilik());
            if (senaraiHakmilikSambungan.size() > 0) {
                Hakmilik hmk = senaraiHakmilikSambungan.get(0);
//                    akaun = akaunDAO.findById(senaraiHakmilikSambungan.get(0).getIdHakmilik());
                akaun = akaunService.getAkaunCukaiForHakmilik(hmk.getCawangan().getKod(), hmk.getIdHakmilik());
                if (akaun == null) {
                    nextStep = false;
                    msg = "2|" + noAkaun;
                }
            } else {
                nextStep = false;
                msg = "3|" + noAkaun;
            }
        }
        
        return msg;
    }
    
    private void updateNoAkaun(String[] noAkauns, String[] ids) {
        Connection c = getConnection();
        PreparedStatement ps = null;
        try {
            
            ps = c.prepareStatement("update transaksi "
                    + "set akaun_cukai = ?,ulasan=null  where trans_id = ?");
            
            for (int i = 0; i < noAkauns.length; i++) {                
                
                ps.setString(1, noAkauns[i]);                
                ps.setLong(2, Long.valueOf(ids[i]));
                ps.execute();
            }
            c.commit();
        } catch (Exception ex) {
            try {
                c.rollback();
            } catch (Exception e) {
                LOG.error("[ ERROR at KUTUPAN ]", e);
            }
            
            LOG.error("[ ERROR at KUTUPAN ]", ex);
        } finally {
            if (ps != null) {
                try {
                    ps.close();
                } catch (Exception e) {
                }
            }
            if (c != null) {
                try {
                    LOG.debug("closing conn.");
                    c.close();
                } catch (Exception e) {
                }
            }
        }
    }
    
    public Resolution reloadAgensi() {
        LOG.info("txtFile = " + txtFile);
        senaraiAgensiCawangan = new ArrayList<KodAgensiKutipanCawangan>();
        
        if (StringUtils.isNotBlank(kodAgensi)) {
            senaraiAgensiCawangan = kutipanAgensiService.getSenaraiKodAgensiCawangan(kodAgensi);
        }
        
        return new JSP(MAIN_PAGE);
    }
    
    public Resolution check() {
        if (txtFile == null) {
            addSimpleError("Fail tidak dijumpai.");
        } else {
            try {
                
                fileName = txtFile.getFileName();
                if (!checkExistingFile()) {
                    Scanner scanner = new Scanner(txtFile.getInputStream()).useDelimiter("\n");
                    if (!processFile(scanner)) {
                        if (StringUtils.isNotBlank(sb_error.toString())) {
                            addSimpleError(sb_error.toString());
                        } else {
                            addSimpleError("Terdapat ralat pada sistem. Sila hubungi IT admin anda.");
                        }                        
                    }
                } else {
                    addSimpleError("Fail telah dimuatnaik. Sila pilih fail lain.");
                }
                
            } catch (Exception ex) {
                addSimpleError(ex.getMessage());
                LOG.error("[ ERROR kutipan agensi ]", ex);
            }
        }
        return new JSP(MAIN_PAGE);
    }
    
    public Resolution upload() {
        String status = getContext().getRequest().getParameter("status");
        
        boolean err = false;
        processKutipan(status);
        
        senaraiTrans = new ArrayList<Map<String, Object>>();
        
        BigDecimal totalAmt = BigDecimal.ZERO;        
        
        for (Transaksi trans : senaraiTransaksi) {
            Map<String, Object> map = new HashMap<String, Object>();
            
            map.put("id", trans.transID);
            map.put("akaunCukai", trans.akaunCukai);
            map.put("amaun", trans.bayaran);
            map.put("status", trans.statusHantar);
            map.put("ulasan", trans.ulasan);
            map.put("resit_agensi", trans.noResitAgensi);
            map.put("trh_resit_manual", trans.masaByr);
            totalAmt = totalAmt.add(trans.bayaran);
            if (trans.statusHantar == 2) {
                err = true;
            }
            
            senaraiTrans.add(map);
            map = null;
         }
        
        total = String.valueOf(totalAmt);
        
        if (err) {
            addSimpleError("Terdapat status GAGAL. Sila semak transaksi.");
            getContext().getRequest().setAttribute("err", "err");
        } else {
            addSimpleMessage("Penghantaran Data Berjaya. Sila semak status.");
        }
        
        getContext().getRequest().setAttribute("finish", "finish");
        
        reset();
        return new JSP(status == null ? MAIN_PAGE : UPDATE_PAGE);
    }
    
    public boolean processFile(Scanner scanner) throws Exception {
        senaraiTransaksi = new ArrayList<Transaksi>();
        
        senaraiTrans = new ArrayList<Map<String, Object>>();
        
        if (kodAgensi.equals("POS")) {            
            String kodNegeri = conf.getKodNegeri();
            if (kodNegeri.equals("04")) {
                processPosMelaka(scanner);
            } else {
                processPos(scanner);
            }
        } else if (kodAgensi.equals("MAINS")) {
            processMAINS(scanner);
        } else if (kodAgensi.equals("PPZ")) {
            processZakat(scanner);
        } else if (kodAgensi.startsWith("MP")
                || kodAgensi.equals("MBMB")) {
            processMPAG(scanner, kodAgensi);
        } else if (kodAgensi.startsWith("MD")) {
            processMP(scanner);
        } else if (kodAgensi.equals("BSN")) {
            processBSN(scanner);
        } else {            
            return false;
        }
        
        if (senaraiTransaksi.size() > 0) {
            insertIntoKutipan(senaraiTransaksi);
            
            if (senaraiTrans.isEmpty()) {
                return false;
            }            
        } else {
            return false;
        }
        return true;
    }
    
    private void processMPAG(Scanner scan, String kodAgensi) {
        dfm = new java.text.SimpleDateFormat("yyyyMMdd");
        SimpleDateFormat time = new java.text.SimpleDateFormat("hh:mm");
        
        while (scan.hasNext()) {
            String content = scan.next();
            if (content.trim().equals("")) {
                continue;
            }
            
            try {
                String kodPusat = content.substring(0, 3);
                Date tarikh = dfm.parse(content.substring(3, 11));
                Date masa = time.parse(content.substring(11, 16));
                String noResit = content.substring(24, 36);
                String kodHasil = content.substring(36, 45);
                String noAkaun = content.substring(45, 65);
                String jumlah = content.substring(65, 76);
                String daerah = noAkaun.trim().substring(0, 2);
                
                
                LOG.info("kodPusat = " + kodPusat);
                LOG.info("tarikh = " + tarikh);
                LOG.info("masa = " + masa);
                LOG.info("no resit agensi = " + kodPusat + noResit);
                LOG.info("bayaran = " + (new BigDecimal(jumlah).divide(new BigDecimal(10))));
                LOG.info("kodHasil = " + kodHasil);
                LOG.info("noAkaun = " + noAkaun);
                LOG.info("daerah = " + daerah);
                
                Transaksi trans = new Transaksi();
                
                trans.tarikh = tarikh;
                trans.masaByr = masa;
//            trans.caraBayar = content.substring(8, 9).trim();
                trans.caraBayar = "KA"; //kutipan agensi
                trans.akaunCukai = noAkaun.trim();
                trans.noResitAgensi = kodPusat.trim() + noResit.trim();
                trans.bayaran = new BigDecimal(jumlah).divide(new BigDecimal(10));
                trans.daerah = daerah;
                trans.kodAgensi = kodAgensi;
                trans.kodCawanganAgensi = kodPusat;
                trans.trhMasuk = new Date();
                trans.namaFail = fileName;
                
                senaraiTransaksi.add(trans);
            } catch (Exception ex) {
                sb_error.append("Fail agensi tidak sama dengan agensi yang dipilih. Sila muatnaik fail yang betul.");
                break;
            }
            
        }
    }
    
    private void processBSN(Scanner scan) {        
        dfm = new java.text.SimpleDateFormat("yyyyMMdd");
        
        
        while (scan.hasNext()) {
            
            String content = scan.next();
            if (content.trim().length() < 96) {
//                sb_error.append("Fail agensi tidak sama dengan agensi yang dipilih. Sila muatnaik fail yang betul.");
                continue;
            }
            
            try {
                String[] data = content.split("\\s+");

                String kodPusat = content.substring(0, 8);
                Date tarikh = dfm.parse(content.substring(8, 16));
                String trh = dfm.format(tarikh);
                String kodTransaksi = content.substring(16, 21);
                String turutan = content.substring(21, 28);
                String noResit = "216" + trh + turutan.substring(3, 7).trim();
//                String noAkaun = content.substring(28, 48);
                String noAkaunl = content.substring(28, 48);
                String noAkaun = data[1];

                String jumlah = content.substring(60, 90);
                String daerah = noAkaunl.trim().substring(0, 2);
                
                
                LOG.info("kodPusat = " + kodPusat);
                LOG.info("tarikh = " + tarikh);
                LOG.info("no resit agensi = 216" + noResit);
                LOG.info("jumlah = " + jumlah.substring(0, 15));
                LOG.info("bayaran = " + (new BigDecimal(jumlah.substring(0, 15)).divide(new BigDecimal(100))));
                LOG.info("noAkaun = " + noAkaun);
                LOG.info("daerah = " + daerah);
                
                Transaksi trans = new Transaksi();
                
                trans.tarikh = tarikh;
                trans.masaByr = tarikh;
//            trans.caraBayar = content.substring(8, 9).trim();
                trans.caraBayar = "KA";
                trans.akaunCukai = noAkaun.trim();
                trans.noResitAgensi = noResit;
                trans.bayaran = new BigDecimal(jumlah.substring(0, 15)).divide(new BigDecimal(100));
                trans.daerah = daerah;
                trans.kodAgensi = "BSN";
                trans.kodCawanganAgensi = "216";
                trans.trhMasuk = new Date();
                trans.namaFail = fileName;
                
                senaraiTransaksi.add(trans);
            } catch (Exception ex) {
                sb_error.append("Fail agensi tidak sama dengan agensi yang dipilih. Sila muatnaik fail yang betul.");
                break;
            }
        }
    }    
    
    private void processPosMelaka(Scanner scan) {
        dfm = new java.text.SimpleDateFormat("yyyyMMdd");
        
        while (scan.hasNext()) {
            String content = scan.next();
            
            if (content.trim().equals("")) {
                continue;
            }
            try {
                String kodPusat = content.substring(0, 5);
                Date tarikh = dfm.parse(content.substring(5, 13));
                String kodPos = content.substring(13, 17);
                String noResit = content.substring(17, 24);
                
                String noAkaun = content.substring(24, 42);
                String jumlah = content.substring(44, 60);
                String daerah = noAkaun.trim().substring(0, 2);
                
                LOG.info("kodPusat = " + kodPusat);
                LOG.info("tarikh = " + tarikh);
                LOG.info("no resit agensi = 217" + noResit);
                LOG.info("bayaran = " + (new BigDecimal(jumlah).divide(new BigDecimal(10))));
                LOG.info("kodHasil = " + kodPos);
                LOG.info("noAkaun = " + noAkaun);
                LOG.info("daerah = " + daerah);
                
                Transaksi trans = new Transaksi();
                
                trans.tarikh = tarikh;
                trans.masaByr = tarikh;
//            trans.caraBayar = content.substring(8, 9).trim();
                trans.caraBayar = "KA";
                trans.akaunCukai = noAkaun.trim();
                trans.noResitAgensi = "217" + noResit.trim();
                trans.bayaran = new BigDecimal(jumlah).divide(new BigDecimal(100));
                trans.daerah = daerah;
                trans.kodAgensi = "POS";
                trans.kodCawanganAgensi = "217";
                trans.trhMasuk = new Date();
                trans.namaFail = fileName;
                
                senaraiTransaksi.add(trans);
                
            } catch (Exception ex) {
                sb_error.append("Fail agensi tidak sama dengan agensi yang dipilih. Sila muatnaik fail yang betul.");
                break;
            }
        }
    }
    
    private void processPos(Scanner scan) {
        dfm = new java.text.SimpleDateFormat("yyyyMMdd");
        
        while (scan.hasNext()) {
            String content = scan.next();
            if (content.trim().equals("")) {
                continue;
            }
            try {
                LOG.info("tarikh = " + dfm.parse(content.substring(0, 8)));
                LOG.info("cara bayar = " + content.substring(8, 9));
                LOG.info("akaun cukai = " + content.substring(9, 29));
                LOG.info("no resit agensi = " + content.substring(33, 40));
                LOG.info("bayaran = " + (new BigDecimal(content.substring(43, 52)).divide(new BigDecimal(100))));
                LOG.info("status trans = " + content.substring(51, 52));
                LOG.info("kod agensi cawangan = " + content.substring(53, 58));
                LOG.info("daerah = " + content.substring(11, 13));
                
                Transaksi trans = new Transaksi();
                
                
                String caraBayar = content.substring(8, 9).trim();
                if (StringUtils.isNotBlank(caraBayar)) {
                    KodCaraBayaranAgensi kcba = kodAgensiBayaranAgensiDAO.findById(caraBayar);
                    if (kcba != null && kcba.getKodEtanah() != null) {
                        caraBayar = kcba.getKodEtanah().getKod();
                    }
                }
                trans.tarikh = dfm.parse(content.substring(0, 8));
                trans.masaByr = dfm.parse(content.substring(0, 8));
//            trans.caraBayar = content.substring(8, 9).trim();
                trans.caraBayar = caraBayar;
                trans.akaunCukai = content.substring(9, 29).trim();
                trans.noResitAgensi = content.substring(33, 40).trim();
                trans.bayaran = new BigDecimal(content.substring(43, 52)).divide(new BigDecimal(100));
                trans.daerah = content.substring(11, 13);
                trans.kodAgensi = "POS";
                trans.kodCawanganAgensi = content.substring(53, 58);
                trans.trhMasuk = new Date();
                trans.namaFail = fileName;
                
                senaraiTransaksi.add(trans);
            } catch (Exception ex) {
                sb_error.append("Fail agensi tidak sama dengan agensi yang dipilih. Sila muatnaik fail yang betul.");
                break;
            }
        }
    }
    
    private void processMAINS(Scanner scan) {        
        
        
        while (scan.hasNext()) {
            
            String content = scan.next();
            if (StringUtils.isBlank(content)) {
                continue;
            }
//            StringTokenizer st = new StringTokenizer(content, "|");
            String[] f = content.split("\\|");
            
            Transaksi trans = new Transaksi();
            try {
                String caraBayar = f[6].trim();
                if (StringUtils.isNotBlank(caraBayar)) {
                    KodCaraBayaranAgensi kcba = kodAgensiBayaranAgensiDAO.findById(caraBayar);
                    if (kcba != null && kcba.getKodEtanah() != null) {
                        caraBayar = kcba.getKodEtanah().getKod();
                    }
                }
                
                trans.tarikh = dfm.parse(f[1].trim());
                trans.masaByr = dfmTime.parse(f[2].trim());
//            trans.caraBayar = f[6].trim();
                trans.caraBayar = caraBayar;
                trans.akaunCukai = f[4].trim();
                trans.noResitAgensi = f[3].trim();
                trans.bayaran = (new BigDecimal(f[5].trim()).divide(new BigDecimal(100)));
                trans.daerah = content.substring(36, 38);
                trans.kodAgensi = "MAINS";
                trans.kodCawanganAgensi = "01009";
                trans.trhMasuk = new Date();
                trans.namaFail = fileName;

//            LOG.info(dfm.parse(content.substring(2, 12)));//tarikh
//            LOG.info(dfmTime.parse(content.substring(2, 23)));//masaByr
//            LOG.info(content.substring(66, 68).trim()); //caraBayar
//            LOG.info((content.substring(34, 54)).trim()); //akaunCukai
//            LOG.info(content.substring(24, 33).trim()); //noResitAgensi
//            LOG.info(new BigDecimal((content.substring(55, 65)).trim()).divide(new BigDecimal(100))); //bayaran
//            LOG.info(content.substring(36, 38));//daerah

                senaraiTransaksi.add(trans);
            } catch (Exception ex) {
                sb_error.append("Fail agensi tidak sama dengan agensi yang dipilih. Sila muatnaik fail yang betul.");
                break;
            }

//            trans.tarikh = dfm.parse(content.substring(2, 12));
//            trans.masaByr = dfmTime.parse(content.substring(2, 23));
//            trans.caraBayar = content.substring(66, 68).trim();
//            trans.akaunCukai = content.substring(34, 54).trim();
//            trans.noResitAgensi = content.substring(24, 33).trim();
//            trans.bayaran = new BigDecimal((content.substring(55, 65)).trim()).divide(new BigDecimal(100));
//            trans.daerah = content.substring(36, 38);

            
        }
    }
    
    private void processZakat(Scanner scan) {
        
        while (scan.hasNext()) {
            String content = scan.next();
            if (StringUtils.isBlank(content)) {
                continue;
            }
//            LOG.info("tarikh = " + dfm.parse(content.substring(2, 12)));
//            LOG.info("cara bayar = " +dfmTime.parse(content.substring(2, 23)));
//            LOG.info("akaun cukai = " +  (content.substring(34, 54)).trim());
//            LOG.info("no resit agensi = " + content.substring(24, 33));
//            LOG.info("bayaran = " + (new BigDecimal(content.substring(55, 65)).divide(new BigDecimal(100))));            
//            LOG.info("kod agensi cawangan = " + content.substring(36, 38));
//            LOG.info("daerah = " + content.substring(11, 13));

            String[] f = content.split("\\|");
            
            Transaksi trans = new Transaksi();
            
            try {
                String caraBayar = f[6].trim();
                if (StringUtils.isNotBlank(caraBayar)) {
                    KodCaraBayaranAgensi kcba = kodAgensiBayaranAgensiDAO.findById(caraBayar);
                    if (kcba != null && kcba.getKodEtanah() != null) {
                        caraBayar = kcba.getKodEtanah().getKod();
                    }
                }
                
                trans.tarikh = dfm.parse(f[1].trim());
                trans.masaByr = dfmTime.parse(f[2].trim());
//            trans.caraBayar = f[6].trim();
                trans.caraBayar = caraBayar;
                trans.akaunCukai = f[4].trim();
                trans.noResitAgensi = f[3].trim();
                trans.bayaran = (new BigDecimal(f[5].trim()).divide(new BigDecimal(100)));
//            trans.daerah = content.substring(36, 38);
                trans.kodAgensi = "PPZ";
                trans.kodCawanganAgensi = "01010";
                trans.trhMasuk = new Date();
                trans.namaFail = fileName;


//            trans.tarikh = dfm.parse(content.substring(2, 12));
//            trans.masaByr = dfmTime.parse(content.substring(2, 23));
//            trans.caraBayar = content.substring(66, 68);
//            trans.akaunCukai = (content.substring(34, 54)).trim();
//            trans.noResitAgensi = content.substring(24, 33).trim();
//            trans.bayaran = (new BigDecimal(content.substring(55, 65)).divide(new BigDecimal(100)));
//            trans.kodAgensi = "PSZNS";
//            trans.kodCawanganAgensi = "01010";
//            trans.daerah = content.substring(36, 38);
//            trans.trhMasuk = new Date();
//            trans.namaFail = fileName;

                senaraiTransaksi.add(trans);
            } catch (Exception ex) {
                sb_error.append("Fail agensi tidak sama dengan agensi yang dipilih. Sila muatnaik fail yang betul.");
                break;
            }
            
        }
    }
    
    private void processMP(Scanner scan) {        
        
        while (scan.hasNext()) {
            String content = scan.next();
            if (StringUtils.isBlank(content)) {
                continue;
            }
            String[] f = content.split("\\|");
            
            try {
                LOG.info("tarikh = " + dfm.parse(f[1].trim()));
                LOG.info("cara bayar = " + f[6].trim());
                LOG.info("akaun cukai = " + f[4].trim());
                LOG.info("no resit agensi = " + f[3].trim());
                LOG.info("bayaran = " + (new BigDecimal(f[5].trim()).divide(new BigDecimal(100))));
                LOG.info("daerah = " + content.substring(36, 38));
                
                Transaksi trans = new Transaksi();

//            trans.tarikh = dfm.parse(content.substring(2, 12));
//            trans.masaByr = dfmTime.parse(content.substring(2, 23));
//            trans.caraBayar = content.substring(66, 68);
//            trans.akaunCukai = (content.substring(34, 54)).trim();
//            trans.noResitAgensi = content.substring(24, 33).trim();
//            trans.bayaran = (new BigDecimal(content.substring(55, 65)).divide(new BigDecimal(100)));
//            trans.kodAgensi = "MPS";
//            trans.kodCawanganAgensi = "01001";
//            trans.daerah = content.substring(36, 38);

                String caraBayar = f[6].trim();
                if (StringUtils.isNotBlank(caraBayar)) {
                    KodCaraBayaranAgensi kcba = kodAgensiBayaranAgensiDAO.findById(caraBayar);
                    if (kcba != null && kcba.getKodEtanah() != null) {
                        caraBayar = kcba.getKodEtanah().getKod();
                    }
                }
                
                
                trans.tarikh = dfm.parse(f[1].trim());
                trans.masaByr = dfmTime.parse(f[2].trim());
//            trans.caraBayar = f[6].trim();
                trans.caraBayar = caraBayar;
                trans.akaunCukai = f[4].trim();
                trans.noResitAgensi = f[3].trim();
                trans.bayaran = (new BigDecimal(f[5].trim()).divide(new BigDecimal(100)));
//            trans.daerah = content.substring(36, 38);
                trans.kodAgensi = "MPS";
                trans.kodCawanganAgensi = "01001";
                trans.daerah = content.substring(36, 38);
                trans.trhMasuk = new Date();
                trans.namaFail = fileName;
                
                senaraiTransaksi.add(trans);
            } catch (Exception ex) {
                sb_error.append("Fail agensi tidak sama dengan agensi yang dipilih. Sila muatnaik fail yang betul.");
                break;
            }
        }
    }
    
    private void insertIntoKutipan(List<Transaksi> senaraiTransaksi) {
        
        Connection c = getConnection();
        PreparedStatement ps = null;
        BigDecimal totalAmt = BigDecimal.ZERO;
        try {
            
            ps = c.prepareStatement("insert into transaksi "
                    + "values (?, ?, ?, ?, ?, null,?, ?, ?, ?, ?, ?, ?, ?, ?,null)");            
            
            for (Transaksi trans : senaraiTransaksi) {
                
                Map<String, Object> map = new HashMap<String, Object>();
                Long ID = getID(c);                
                
                ps.setLong(1, ID);
                ps.setString(2, trans.akaunCukai);
                ps.setObject(3, trans.bayaran);
                ps.setString(4, trans.caraBayar);
                ps.setString(5, trans.daerah);
                ps.setString(6, trans.kodAgensi);
                ps.setString(7, trans.kodCawanganAgensi);
                ps.setTimestamp(8, new Timestamp(trans.tarikh.getTime())); //temp
                ps.setString(9, trans.namaFail);
                ps.setString(10, trans.noResitAgensi);
                ps.setString(11, "0");
                ps.setString(12, "0");
                ps.setTimestamp(13, new Timestamp(System.currentTimeMillis())); //temp
                ps.setTimestamp(14, new Timestamp(System.currentTimeMillis()));
                ps.execute();
                
                map.put("id", ID);
                map.put("akaunCukai", trans.akaunCukai);
                map.put("amaun", trans.bayaran);
                map.put("status", "0");
                map.put("resit_agensi", trans.noResitAgensi);
                map.put("trh_resit_manual", trans.tarikh);
                totalAmt = totalAmt.add(trans.bayaran);
                
                senaraiTrans.add(map);
            }
            total = String.valueOf(totalAmt);
            c.commit();
        } catch (Exception ex) {
            try {
                c.rollback();
                senaraiTrans = Collections.EMPTY_LIST;
            } catch (Exception e) {
                LOG.error("[ ERROR at KUTUPAN ]", e);
            }
            
            LOG.error("[ ERROR at KUTUPAN ]", ex);
        } finally {
            if (ps != null) {
                try {
                    ps.close();
                } catch (Exception e) {
                }
            }
            if (c != null) {
                try {
                    LOG.debug("closing conn.");
                    c.close();
                } catch (Exception e) {
                }
            }
        }
        
    }
    
    private Long getID(Connection c) {
        
        PreparedStatement ps = null;
        ResultSet rs = null;
        long ID = 0;
        
        try {
            
            ps = c.prepareStatement("Select max(TRANS_ID) from transaksi");
            rs = ps.executeQuery();
            if (rs.next()) {
                ID = rs.getLong(1) + 1;
            }
            
        } catch (Exception ex) {
            LOG.error("[ Kutipan Error ]", ex);
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
                if (rs != null) {
                    rs.close();
                }
            } catch (Exception ex) {
                LOG.error("[ Kutipan Error ]", ex);
            }
        }
        return ID;
    }
    
    public boolean checkExistingFile() {
        
        Connection c = getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        boolean flag = false;
        
        try {
            ps = c.prepareStatement("Select count(TRANS_ID) from transaksi where NAMA_FAIL = ?");
            ps.setString(1, fileName);
            rs = ps.executeQuery();
            
            if (rs.next()) {
                int r = rs.getInt(1);
                if (r > 0) {
                    flag = true;
                }
            }
        } catch (Exception ex) {
            LOG.error("[ Kutipan Error ]", ex);
        } finally {
            try {
                if (c != null) {
                    LOG.debug("connection close");
                    c.close();
                }
                if (ps != null) {
                    ps.close();
                }
                if (rs != null) {
                    rs.close();
                }
            } catch (Exception ex) {
                LOG.error("[ Kutipan Error ]", ex);
            }
        }
        return flag;
    }
    
    private List<Transaksi> getTransaksi(String fileName, String status) {
        
        Connection c = getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        List<Transaksi> list = new ArrayList<Transaksi>();
        
        try {
            
            StringBuilder sb = new StringBuilder("Select * from transaksi where NAMA_FAIL = ?");
            if (StringUtils.isNotBlank(status)) {
                if (status.equals("2")) {
                    sb.append(" AND STATUS_HANTAR IN ( ?, '0')");
                } else {
                    sb.append(" AND STATUS_HANTAR = ?");
                }
            }
            
            ps = c.prepareStatement(sb.toString());
            ps.setString(1, fileName);
            if (StringUtils.isNotBlank(status)) {
                ps.setString(2, status);
            }
            rs = ps.executeQuery();
            
            while (rs.next()) {
                Transaksi trans = new Transaksi();
                trans.transID = rs.getLong("TRANS_ID");
                trans.akaunCukai = rs.getString("AKAUN_CUKAI");
                trans.bayaran = rs.getBigDecimal("BAYARAN");
                trans.caraBayar = rs.getString("CARA_BAYAR");
                trans.daerah = rs.getString("DAERAH");
                trans.idKewDok = rs.getString("ID_KEW_DOK");
                trans.kodAgensi = rs.getString("KOD_AGENSI");
                trans.kodCawanganAgensi = rs.getString("KOD_CAWANGAN_AGENSI");
                trans.masaByr = rs.getTimestamp("MASA_BYR");
                trans.namaFail = rs.getString("NAMA_FAIL");
                trans.noResitAgensi = rs.getString("NO_RESIT_AGENSI");
                trans.statusHantar = rs.getInt("STATUS_HANTAR");
                trans.ulasan = rs.getString("ULASAN");
                list.add(trans);
                trans = null;
            }
        } catch (Exception ex) {
            LOG.error("[ Kutipan Error ]", ex);
        } finally {
            try {
                if (c != null) {
                    c.close();
                    c = null;
                }
                if (ps != null) {
                    ps.close();
                    ps = null;
                }
                if (rs != null) {
                    rs.close();
                    rs = null;
                }
            } catch (Exception ex) {
                LOG.error("[ Kutipan Error ]", ex);
            }
        }
        return list;
    }
    
    private void processKutipan(String status) {
        //kew_dokumen
        //cara_bayar
        //kew_trans
        //kew_dokumen_bayar
        //kew_akaun
        //kew_trans_hdpn

        etanahActionBeanContext ctx = (etanahActionBeanContext) getContext();
        
        Pengguna pengguna = ctx.getUser();
        
        String kodNegeri = ctx.getKodNegeri();
        
        KodCawangan kc = pengguna.getKodCawangan();
        
        InfoAudit ia = new InfoAudit();
        ia.setTarikhMasuk(new Date());
        ia.setDimasukOleh(pengguna); // FIXME

        List<Transaksi> senaraiTransaksiProcessed = new ArrayList<Transaksi>();
        
        
        Session s = sessionProvider.get();
        
        Transaction tx = s.beginTransaction();
        
        senaraiTransaksi = getTransaksi(fileName, status);
        
        if (kodAgensi == null) {
            kodAgensi = senaraiTransaksi.get(0).kodAgensi;
        }
        
        KodAgensiKutipanCawangan agensiKutipanCawangan = null;
        
        if (kodAgensiCawangan == null) {
            kodAgensiCawangan = senaraiTransaksi.get(0).kodCawanganAgensi;
            if (kodAgensiCawangan != null) {
                agensiKutipanCawangan = kodAgensiKutipanCawanganDAO.findById(kodAgensiCawangan);
            }            
        } else {
            agensiKutipanCawangan = kodAgensiKutipanCawanganDAO.findById(kodAgensiCawangan);
        }
        
        LOG.debug("kodAgensicawangan = " + kodAgensiCawangan);        
        
        
        
        for (Transaksi trans : senaraiTransaksi) {            
            AkaunStrata akaunS = akaunStrataDAO.findById(trans.akaunCukai);
            Akaun akaun = akaunS.getHakmilik().getAkaunCukai();
            trans.ulasan = "Terdapat masalah pada akaun ini.";
            trans.statusHantar = 2;
            
            boolean nextStep = true;
            
            if (akaunS == null) {
                //                trans.statusHantar = 2;
                /** 
                 * modified by hakim
                 * purpose  : idHakmilik as No Akaun
                 * Line     : 1158 - 1172
                */
                Hakmilik h = hakmilikService.findById(trans.akaunCukai);
                if(h == null){
                    trans.ulasan = "No Akaun tiada.";
                    senaraiTransaksiProcessed.add(trans);
                    continue;
                }else{
                    akaun = h.getAkaunCukai();
                    //System.out.println("akaun.getNoAkaun() : "+akaun.getNoAkaun());
                }
            }
            
            LOG.debug("processKutipan akaun cukai = " + akaun.getNoAkaun());

            //            String[] criteriaNames = new String[]{"akaunDebit"};
            //            Object[] criteriaValues = new Object[]{akaun};
            //            List<etanah.model.Transaksi> kewTrans = transaksiDAO.findByEqualCriterias(criteriaNames, criteriaValues, "untukTahun");            

            Query q = s.createQuery("SELECT tr FROM etanah.model.Transaksi tr where tr.akaunDebit.noAkaun = :noAkaun "
                    + "ORDER BY tr.kodTransaksi.keutamaan, tr.untukTahun asc");
            q.setParameter("noAkaun", akaun.getNoAkaun());
            List<etanah.model.Transaksi> kewTrans = q.list();
            
            
            if (!akaun.getStatus().getKod().equals("B") && kewTrans.size() <= 0) {
                //                trans.statusHantar = 2;
                trans.ulasan = "Transaksi untuk akaun ini tiada.";
                senaraiTransaksiProcessed.add(trans);
                continue;
            }
            
            Hakmilik hm = akaun.getHakmilik();
            if (hm.getKodStatusHakmilik().getKod().equals("B")) {
                //hakmilik sambungan if any
                List<Hakmilik> senaraiHakmilikSambungan = hakmilikService.getHakmilikSambungan(hm.getIdHakmilik());
                if (senaraiHakmilikSambungan.size() > 0) {
                    // tak semesti nya no akaun = id hakmilik !
//                                akaun = akaunDAO.findById(senaraiHakmilikSambungan.get(0).getIdHakmilik());                         
                    Hakmilik hmk = senaraiHakmilikSambungan.get(0);
                    akaun = akaunService.getAkaunCukaiForHakmilik(hmk.getCawangan().getKod(), hmk.getIdHakmilik());
                    if (akaun == null) {
                        nextStep = false;
                        trans.ulasan = "Akaun bagi hakmilik sambungan tiada.";
                    } else {
                        
                        q = s.createQuery("SELECT tr FROM etanah.model.Transaksi tr where tr.akaunDebit = :noAkaun "
                                + "ORDER BY tr.kodTransaksi.keutamaan, tr.untukTahun asc");
                        q.setParameter("noAkaun", akaun);
                        kewTrans = q.list();
                        if (kewTrans.size() <= 0) {
                            nextStep = false;
                            trans.ulasan = "Transaksi untuk akaun ini tiada.";
                        }
                    }
                } else {
                    nextStep = false;
                    trans.ulasan = "Hakmilik Sambungan tiada. Hakmilik ini telah dibatalkan.";
                }
            }
            
            
            
            if (!nextStep) {
                //                trans.statusHantar = 2;
                senaraiTransaksiProcessed.add(trans);
                continue;
            }
            
            DokumenKewangan dk = new DokumenKewangan();
            String noResit = noResitGenerator.generateNoResit(kodNegeri, kc, pengguna);
            dk.setIdDokumenKewangan(noResit);
            dk.setAgensiKutipan(agensiKutipanCawangan);
            dk.setAkaun(akaun);
            dk.setAmaunBayaran(trans.bayaran);
            dk.setStatus(kodStatusDokumenKewanganDAO.findById("A"));
            dk.setCawangan(akaun.getCawangan());
            if (trans.noResitAgensi == null || trans.noResitAgensi.equals("")) {
                dk.setNoRujukanManual(noResit);
            } else {
                dk.setNoRujukanManual(trans.noResitAgensi);
            }            
            dk.setMod(kodKutipanDAO.findById('B'));
            dk.setTarikhTransaksi(trans.masaByr); //fixme
            dk.setKodDokumen(kodDokumenDAO.findById("RBY"));
            if (trans.caraBayar.equals("T")) {
                dk.setAmaunTunai(trans.bayaran);
            }
            dk.setInfoAudit(ia);
            dokumenKewanganDAO.save(dk);
            
            CaraBayaran cb = new CaraBayaran();
            cb.setAktif('Y');
            cb.setAmaun(trans.bayaran);
            cb.setKodCaraBayaran(kodCaraBayaranDAO.findById(trans.caraBayar));
            cb.setCawangan(kc); //fixme
            cb.setInfoAudit(ia);
            caraBayaranDAO.save(cb);
            
            DokumenKewanganBayaran dkb = new DokumenKewanganBayaran();
            dkb.setAmaun(trans.bayaran);
            dkb.setDokumenKewangan(dk);
            dkb.setCaraBayaran(cb);
            dkb.setAktif('Y');
            dkb.setInfoAudit(ia);
            dokumenKewanganBayaranDAO.save(dkb);

            // all transaction code will be processed by asc year
            // by the following order 61502, 61401, 76152, 99000 - 99003, 61018             
            int utkTahun = 0;            
            BigDecimal total = BigDecimal.ZERO;
            BigDecimal baki = BigDecimal.ZERO;

            //            for (int a = kewTrans.size()-1; a>=0; a-- ) {
            for (etanah.model.Transaksi kewTran : kewTrans) {
                LOG.info("-------------------------------------------------- 0000000000000000000000 : "+kewTran.getIdTransaksi());

                //                etanah.model.Transaksi kewTran = kewTrans.get(a);
                if (akaun.getBaki().doubleValue() <= 0) {
                    break;
                }
                
                
                utkTahun = kewTran.getUntukTahun();
//                if (kewTran.getAmaun().equals(BigDecimal.ZERO)) {
//                    continue;
//                }

                // process 61402 (Tunggakan Cukai Tanah)
                if (kewTran.getKodTransaksi().getKod().equals("61502")) {
                    total = total.add(kewTran.getAmaun());
                    LOG.info("total ---------------------------- "+total);

                    //to cater payment before
                    q = s.createQuery("SELECT sum(tr.amaun) FROM etanah.model.Transaksi tr where tr.akaunKredit.noAkaun = :noAkaun "
                            + "and tr.untukTahun = :tahun and tr.kodTransaksi.kod = :kod");
                    q.setParameter("noAkaun", akaun.getNoAkaun());
                    q.setParameter("tahun", kewTran.getUntukTahun());
                    q.setParameter("kod", kewTran.getKodTransaksi().getKod());
                    BigDecimal byr = ((BigDecimal) q.uniqueResult() != null ? (BigDecimal) q.uniqueResult() : BigDecimal.ZERO);
                    
                    total = total.subtract(byr);                    
                    int i = total.compareTo(trans.bayaran);
                    
                    if (i <= 0) { // bayar lebih dari jumlah dalam kew_trans
                        
                        createKewTrans(kewTran.getKodTransaksi(), kewTran.getUntukTahun(), akaun, dk, ia,
                                dkb, "KR", kewTran.getAmaun(), trans, pengguna);
                        
                    } else if (i > 0) { // bayar kurang dari jumlah kew_trans                                  
                        createKewTrans(kewTran.getKodTransaksi(), kewTran.getUntukTahun(), akaun, dk, ia,
                                dkb, "KR", trans.bayaran, trans, pengguna);
                        break;
                    }
                    
                } else if (kewTran.getKodTransaksi().getKod().equals("61501")
                        || kewTran.getKodTransaksi().getKod().equals("76156")
                        || kewTran.getKodTransaksi().getKod().equals("99000")
                        || kewTran.getKodTransaksi().getKod().equals("99001")
                        || kewTran.getKodTransaksi().getKod().equals("99002")
                        || kewTran.getKodTransaksi().getKod().equals("99003")
                        || kewTran.getKodTransaksi().getKod().equals("99030")
                        || kewTran.getKodTransaksi().getKod().equals("99011")
                        || kewTran.getKodTransaksi().getKod().equals("61018")) {
                    
                    baki = trans.bayaran.subtract(total);
                    total = total.add(kewTran.getAmaun());
                    BigDecimal amt = kewTran.getAmaun();
                    
                    q = s.createQuery("SELECT sum(tr.amaun) FROM etanah.model.Transaksi tr where tr.akaunKredit.noAkaun = :noAkaun "
                            + "and tr.untukTahun = :tahun and tr.kodTransaksi.kod = :kod");
                    q.setParameter("noAkaun", akaun.getNoAkaun());
                    q.setParameter("tahun", kewTran.getUntukTahun());
                    q.setParameter("kod", kewTran.getKodTransaksi().getKod());
                    BigDecimal byr = ((BigDecimal) q.uniqueResult() != null ? (BigDecimal) q.uniqueResult() : BigDecimal.ZERO);
                    
                    amt = amt.subtract(byr);                    
                    total = total.subtract(byr);
                    int i = amt.compareTo(baki);
                    
                    if (i <= 0) {                        
                        createKewTrans(kewTran.getKodTransaksi(), kewTran.getUntukTahun(),
                                akaun, dk, ia, dkb, "KR", amt, trans, pengguna);

//                                    if (i == 0 ) {
//                                        break;
//                                    }
                        
                    } else if (i > 0) {
                        
                        createKewTrans(kewTran.getKodTransaksi(), kewTran.getUntukTahun(),
                                akaun, dk, ia, dkb, "KR", baki, trans, pengguna);                        
                        break;
                    }
                }
            }

            kewTrans = null; //yus add 07032019
            
            //lebihan
            baki = akaun.getBaki().subtract(trans.bayaran);
            if (baki.compareTo(BigDecimal.ZERO) < 0) {
                BigDecimal lebihan = trans.bayaran.subtract(total);
                int year = Integer.parseInt(yy.format(new Date()));
                createKewTrans(kodTransaksiDAO.findById("61501"),
                        year, akaun, dk, ia, dkb, "KR", lebihan, trans, pengguna);
            }
            
            akaun.setBaki(baki);
            ia.setDiKemaskiniOleh(pengguna);
            ia.setTarikhKemaskini(new Date());
            akaun.setInfoAudit(ia);
            
            akaunDAO.saveOrUpdate(akaun);

            //added for kod agensi (debit) - request by kak fida : 11/05/2013
            KodAgensiKutipan kodAgensiKutipan = kodAgensiKutipanDAO.findById(trans.kodAgensi);
            if (kodAgensiKutipan != null) {
                Akaun ak = kodAgensiKutipan.getAkaun();
                BigDecimal akBaki = akaun.getBaki();
                ak.setBaki(akBaki.add(trans.bayaran));
                akaunDAO.saveOrUpdate(ak);
            }
            
            trans.statusHantar = 1;
            trans.ulasan = "Berjaya diproses";
            trans.idKewDok = dk.getIdDokumenKewangan();
            senaraiTransaksiProcessed.add(trans);
            
            akaun = null; //yus add 06032019
            
        }

        
        tx.commit();
        
        tx = null; //yus add 06032019
        
        
        if (senaraiTransaksiProcessed.size() > 0) {
            
            Connection c = getConnection();
            PreparedStatement ps = null;
            try {
                
                ps = c.prepareStatement("update transaksi "
                        + "set status_hantar = ?, id_kew_dok = ?, ulasan = ? where trans_id = ?");
                
                for (Transaksi trans : senaraiTransaksiProcessed) {                    
                    
                    ps.setInt(1, trans.statusHantar);
                    ps.setString(2, trans.idKewDok);
                    ps.setString(3, trans.ulasan);
                    ps.setLong(4, trans.transID);
                    ps.execute();
                    
                }
                c.commit();
            } catch (Exception ex) {
                try {
                    c.rollback();
                } catch (Exception e) {
                    LOG.error("[ ERROR at KUTIPAN.senaraiTransaksiProcessed ]", e);
                }
                
                LOG.error("[ ERROR at KUTIPAN.senaraiTransaksiProcessed ]", ex);
            } finally {
                if (ps != null) {
                    try {
                        ps.close();
                    } catch (Exception e) {
                    }
                }
                if (c != null) {
                    try {
                        LOG.debug("closing conn.");
                        c.close();
                    } catch (Exception e) {
                    }
                }
            }
            senaraiTransaksiProcessed = null; //yus add 20022019
            
        }
          senaraiTransaksi = null;       

//        senaraiTransaksi = getTransaksi(fileName, null);
    }
    
    private void createKewTrans(KodTransaksi kodTrans, Integer utkThn,
            Akaun ka, DokumenKewangan dk, InfoAudit ia, DokumenKewanganBayaran dkb,
            String payType, BigDecimal ktAmaun, Transaksi t, Pengguna pengguna) {
        
        String thnKew = (new SimpleDateFormat("yyyy")).format(new java.util.Date());
        
        etanah.model.Transaksi kewTran = new etanah.model.Transaksi();
        
        kewTran.setCawangan(ka.getCawangan());
        kewTran.setKodTransaksi(kodTrans);
        kewTran.setAmaun(ktAmaun);
        kewTran.setDokumenKewangan(dk);
        kewTran.setInfoAudit(ia);
        kewTran.setBayaranAgensi("N");
        
        if (payType.equals("KR")) {
            kewTran.setAkaunKredit(ka);
            KodAgensiKutipan kodAgensiKutipan = kodAgensiKutipanDAO.findById(t.kodAgensi);
            kewTran.setAkaunDebit(kodAgensiKutipan.getAkaun());
//            if (t.kodAgensi.equals("POS")) {
////                Akaun ak = akaunDAO.findById("AGP05");               
//                kewTran.setAkaunDebit(kodAgensiKutipan.getAkaun());
//            } else if (t.kodAgensi.equals("MAINS")) {
////                Akaun ak = akaunDAO.findById("AGP06");
//                kewTran.setAkaunDebit(kodAgensiKutipan.getAkaun());
//            } else if (t.kodAgensi.equals("PSZNS")) {
////                Akaun ak = akaunDAO.findById("AGP07");
//                kewTran.setAkaunDebit(kodAgensiKutipan.getAkaun());
//            } else if (t.kodAgensi.startsWith("MP")
//                    || t.kodAgensi.startsWith("MD")) {
////                Akaun ak = akaunDAO.findById("AGP08");
//                kewTran.setAkaunDebit(kodAgensiKutipan.getAkaun());
//            }
        } else {
            kewTran.setAkaunDebit(ka);
        }
        
        kewTran.setTahunKewangan(Integer.parseInt(thnKew));
        kewTran.setUntukTahun(utkThn);
        kewTran.setStatus(kodStatusTransaksiKewanganDAO.findById('T'));
        kewTran.setKuantiti(1);
        
        transaksiDAO.save(kewTran);

//        createLaporPnyataPmungut(dk.getIdDokumenKewangan(),
//                kewTran.getIdTransaksi(), dkb.getIdKewanganBayaran(), ktAmaun, 'A', pengguna, dk.getCawangan());
    }
    
    private void createLaporPnyataPmungut(String idKewDok, long idTrans, long idKdb,
            BigDecimal amaun, char sts, Pengguna pengguna, KodCawangan kodCawangan) {
        
        LaporanPenyataPemungutItem lpp = new LaporanPenyataPemungutItem();
        Long id = Long.parseLong(generator.generate(pengguna.getKodCawangan().getKod(), kodCawangan, pengguna));
        lpp.setIdLaporan(id);
        lpp.setAmaun(amaun);
        lpp.setIdKewanganBayaran(idKdb);
        lpp.setIdTransaksi(idTrans);
        lpp.setStatus(sts);
        lpp.setIdDokumenKewangan(idKewDok);
        laporanPenyataPemungutItemDAO.save(lpp);
    }
    
    public List<AgensiView> sejarahTransaksi(String kodAgensi) {
        List<AgensiView> sejarah = new ArrayList<AgensiView>();
        Connection c = getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        try {
            
            StringBuilder sb = new StringBuilder("Select nama_fail, status_hantar,max(trh_masuk) from transaksi where KOD_AGENSI = ? group by nama_fail, status_hantar order by max(trh_masuk) desc");
            
            ps = c.prepareStatement(sb.toString());
            ps.setString(1, kodAgensi);
//                ps.setString(2, "2");
            rs = ps.executeQuery();
            
            while (rs.next()) {
                AgensiView av = new AgensiView();
                av.setNama_fail(rs.getString(1));
                av.setStatus_hantar(rs.getString(2));
                av.setTarikh(rs.getTimestamp(3));
               // av.setIdKewDok(rs.getString(4));
                //av.setNoResitAgensi(rs.getString(5));
                sejarah.add(av);
            }
        } catch (Exception ex) {
            LOG.error("[ Kutipan Error ]", ex);
        } finally {
            try {
                if (c != null) {
                    c.close();
                }
                if (ps != null) {
                    ps.close();
                }
                if (rs != null) {
                    rs.close();
                }
            } catch (Exception ex) {
                LOG.error("[ Kutipan Error ]", ex);
            }
        }
        return sejarah;
    }
    
    public String getKodAgensi() {
        return kodAgensi;
    }
    
    public void setKodAgensi(String kodAgensi) {
        this.kodAgensi = kodAgensi;
    }
    
    public String getKodAgensiCawangan() {
        return kodAgensiCawangan;
    }
    
    public void setKodAgensiCawangan(String kodAgensiCawangan) {
        this.kodAgensiCawangan = kodAgensiCawangan;
    }
    
    public FileBean getTxtFile() {
        return txtFile;
    }
    
    public void setTxtFile(FileBean txtFile) {
        this.txtFile = txtFile;
    }
    
    public List<KodAgensiKutipanCawangan> getSenaraiAgensiCawangan() {
        return senaraiAgensiCawangan;
    }
    
    public void setSenaraiAgensiCawangan(List<KodAgensiKutipanCawangan> senaraiAgensiCawangan) {
        this.senaraiAgensiCawangan = senaraiAgensiCawangan;
    }
    
    public List<Transaksi> getSenaraiTransaksi() {
        return senaraiTransaksi;
    }
    
    public void setSenaraiTransaksi(List<Transaksi> senaraiTransaksi) {
        this.senaraiTransaksi = senaraiTransaksi;
    }
    
    public List<Map<String, Object>> getSenaraiTrans() {
        return senaraiTrans;
    }
    
    public void setSenaraiTrans(List<Map<String, Object>> senaraiTrans) {
        this.senaraiTrans = senaraiTrans;
    }
    
    public List<Map<String, Object>> getSenaraiFail() {
        return senaraiFail;
    }
    
    public void setSenaraiFail(List<Map<String, Object>> senaraiFail) {
        this.senaraiFail = senaraiFail;
    }
    
    public String getFileName() {
        return fileName;
    }
    
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }    
    
    public String getTotal() {
        return total;
    }
    
    public void setTotal(String total) {
        this.total = total;
    }
    
    private Connection getConnection() {
        Connection conn = null;
        try {
            String driverName = "oracle.jdbc.driver.OracleDriver";
            Class.forName(driverName);
            LOG.info("URL = " + URL);
            URL = conf.getProperty("kutipan.agensi.location");
            conn = DriverManager.getConnection(URL, "kutipan", "kutipan123");
            conn.setAutoCommit(false);            
        } catch (Exception ex) {
            LOG.error("[ KUTIPAN ERROR ]", ex);
        }
        return conn;
    }
    
    private void reset() {
        fileName = "";
        senaraiTrans = null;
        senaraiFail = null;
        senaraiTransaksi = null;
    }
    
    public static class Transaksi {
        
        public Long transID;
        public Date tarikh;
        public String caraBayar;
        public String noResitAgensi;
        public String akaunCukai;
        public BigDecimal bayaran;
        public String kodAgensi;
        public String kodCawanganAgensi;
        public String statusTrans;
        public Date trhMasuk;
        public Date masaByr;
        public String daerah;
        public Integer statusHantar;
        public String namaFail;
        public String idKewDok;
        public String ulasan;
    }
}
