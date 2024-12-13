/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.servlet;

import com.google.inject.Injector;
import etanah.dao.CaraBayaranDAO;
import etanah.dao.DokumenKewanganBayaranDAO;
import etanah.dao.DokumenKewanganDAO;
import etanah.dao.TransaksiDAO;
import etanah.model.CaraBayaran;
import etanah.model.DokumenKewangan;
import etanah.model.DokumenKewanganBayaran;
import etanah.model.KodCawangan;
import etanah.model.Pengguna;
import etanah.model.Transaksi;
import etanah.model.hasil.LaporanPenyataPemungutItem;
import etanah.view.etanahContextListener;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author fikri
 */
public class ReceiptCukaiServlet extends HttpServlet {

    private static Logger LOG = Logger.getLogger(ReceiptCukaiServlet.class);
    private boolean isDebug = LOG.isDebugEnabled();

    private static final String[] KOD_TRANS_TO_EXCLUDE = {
        "61611",
//        "76199",request by kak fida
        "99000",
        "99001",
        "99002",
        "99003"
//        "72199", request by fazli
//        "72457", request by kak fida
//        "79501", request by kak fida
//        "99011"  request by kak fida
    };

    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
//        PrintWriter out = response.getWriter();
        ServletOutputStream out = null;
        try {
            response.setContentType("application/octet-stream");

            String cetakCukaiInfo = request.getParameter("cetakCukaiInfo");
            String cetakResitBatal = request.getParameter("cetakResitBatal");
            String cetakBelakangCek = request.getParameter("cetakBelakangCek");
            String cetakPerserahanBatal = request.getParameter("cetakPerserahanBatal");
            String cetakRingkasanCukai = request.getParameter("summary");


            if (StringUtils.isBlank(cetakCukaiInfo)
                    && StringUtils.isBlank(cetakResitBatal)
                    && StringUtils.isBlank(cetakBelakangCek)
                    && StringUtils.isBlank(cetakPerserahanBatal)
                    && StringUtils.isBlank(cetakRingkasanCukai)) {
                response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Sila pastikan parameter dihantar dengan betul.");
                return;
            }
            
            String idKewDok = request.getParameter("id_kew_dok");
            String idCaraBayar = request.getParameter("id_cara_bayar");            
            String trhTrans = request.getParameter("trhTrans");
            String modKutip = request.getParameter("modKutip");
            String idKaunter = request.getParameter("idKaunter");

            if (isDebug) {
                LOG.debug("cetakCukaiInfo = " + cetakCukaiInfo);
                LOG.debug("cetakResitBatal = " + cetakResitBatal);
                LOG.debug("cetakBelakangCek = " + cetakBelakangCek);
                LOG.debug("cetakPerserahanBatal = " + cetakPerserahanBatal);
                LOG.debug("cetakRingkasanCukai = " + cetakRingkasanCukai);
                LOG.info("idKewDok = " + idKewDok);
                LOG.info("idCaraBayar = " + idCaraBayar);
                LOG.info("trhTrans = " + trhTrans);
                LOG.info("modKutip = " + modKutip);
                LOG.info("idKaunter = " + idKaunter);
            }            
         
            Map map = new HashMap();
            long start = System.currentTimeMillis();

            LOG.debug("[ starting to select records = " + start + " ]");

            if (StringUtils.isNotBlank(cetakRingkasanCukai)) {
                if (StringUtils.isBlank(trhTrans) && StringUtils.isBlank(modKutip)
                        && StringUtils.isBlank(idKaunter)) {
                    response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, 
                            "Sila pastikan parameter dihantar dengan betul.");
                    return;
                }
                 map = getSummaryInfo(trhTrans, modKutip, idKaunter);
            } else if (StringUtils.isNotBlank(cetakResitBatal) || StringUtils.isNotBlank(cetakPerserahanBatal)) {
                if ( StringUtils.isBlank(idKewDok) ) {
                    response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,
                            "Sila pastikan parameter dihantar dengan betul.");
                    return;
                }
                Map map1 = getResitBatal( idKewDok );
                Map map2 = getCukaiInfo2( idKewDok );
                map.putAll(map1);
                map.putAll(map2);
            } else if (StringUtils.isNotBlank(cetakCukaiInfo)) {
                if ( StringUtils.isBlank(idKewDok) ) {
                    response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,
                            "Sila pastikan parameter dihantar dengan betul.");
                    return;
                }
                 map = getCukaiInfo3( idKewDok );
            } else if (StringUtils.isNotBlank(cetakBelakangCek)) {
                if ( StringUtils.isBlank(idCaraBayar) ) {
                    response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,
                            "Sila pastikan parameter dihantar dengan betul.");
                    return;
                }
                map = getChequeInfo(idCaraBayar);
            }

            LOG.debug("[ finished select records = " + ( System.currentTimeMillis() - start ) + " ]");

            if (map != null) {
                out = response.getOutputStream();
                ObjectOutputStream objectOutput = new ObjectOutputStream(out);
                objectOutput.writeObject(map);
                objectOutput.flush();
                response.setStatus(HttpServletResponse.SC_OK);
            } else {
                response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            }

        } catch (Exception ex) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            ex.printStackTrace();
        } finally {
            if (out != null) {
                try {
                    out.close();
                } catch (Exception e) {
                    
                }
            }
        }
    }

    private Map getSummaryInfo (String trhTran, String modKutip, String idKaunter) {

        List<String> senaraiKewDokSah = new ArrayList<String>();
        List<String> senaraiKewDokBatal = new ArrayList<String>();
        
        int tunai = 0;
        int cek = 0;
        int kk = 0;
        int lain =0 ;
        int wp = 0;
        int kw = 0;
        
        int batal_tunai = 0;
        int batal_cek = 0;
        int batal_kk = 0;
        int batal_lain = 0;
        int batal_wp = 0;
        int batal_kw = 0;
        
        double amtTunai = 0;
        double amtCek = 0;
        double amtKk = 0;
        double amtLain = 0;
        double amtWp = 0;
        double amtKw = 0;
        double jumAmt = 0;
        
        double amtBatalTunai = 0;
        double amtBatalCek = 0;
        double amtBatalKk = 0;
        double amtBatalLain = 0;
        double amtBatalWp = 0;
        double amtBatalKw = 0;
        double jumAmtBatal = 0;

        
        Map map = new HashMap();
        Injector injector = etanahContextListener.getInjector();
        Session sess = injector.getProvider(Session.class).get();

        StringBuilder sbuilder = new StringBuilder()
                    .append("Select lpp,dkb from etanah.model.hasil.LaporanPenyataPemungutItem lpp, etanah.model.DokumenKewanganBayaran dkb ")
                    .append("where lpp.idKewanganBayaran = dkb.idKewanganBayaran ")
                    .append("and dkb.dokumenKewangan.mod.kod = :modKutip ")
                    .append("and dkb.dokumenKewangan.idKaunter = :idKaunter ")
                    .append("and dkb.dokumenKewangan.tarikhTransaksi = to_date(:date, 'dd/mm/yyyy') ");

        Iterator iter  = sess.createQuery(sbuilder.toString())
                    .setParameter("modKutip", modKutip)
                    .setParameter("idKaunter", idKaunter)
                    .setString("date", trhTran).iterate();

        while ( iter.hasNext() ) {
            Object[] obj = (Object[]) iter.next();
            LaporanPenyataPemungutItem lpp = (LaporanPenyataPemungutItem) obj[0];
            DokumenKewanganBayaran dkb = (DokumenKewanganBayaran) obj[1];
            CaraBayaran caraByrn = dkb.getCaraBayaran();
            String kodCaraByr = caraByrn.getKodCaraBayaran().getKod();            
            BigDecimal amt = lpp.getAmaun();
            
            if (lpp.getStatus() == 'B') {
                if ("T".equals(kodCaraByr)) {
                    batal_tunai ++;
                    amtBatalTunai = amtBatalTunai + amt.doubleValue();                    
                } else if ( "LB".equals(kodCaraByr)
                            || "CB".equals(kodCaraByr)
                            || "CT".equals(kodCaraByr)
                            || "CL".equals(kodCaraByr)
                            || "HC".equals(kodCaraByr)
                            || "IC".equals(kodCaraByr) ){
                    batal_cek ++;
                    amtBatalCek = amtBatalCek + amt.doubleValue();                    
                } else if ( "KK".equals(kodCaraByr)
                        || "VS".equals(kodCaraByr) ) {
                    batal_kk ++;
                    amtBatalKk = amtBatalKk + amt.doubleValue();
                } else if ( "WP".equals(kodCaraByr) ) {
                    batal_wp ++;
                    amtBatalWp = amtBatalWp + amt.doubleValue();
                } else if ( "KW".equals(kodCaraByr) ) {
                    batal_kw ++;
                    amtBatalKw = amtBatalKw + amt.doubleValue();
                }
              jumAmtBatal = jumAmtBatal + amt.doubleValue();
              if (!senaraiKewDokBatal.contains(lpp.getIdDokumenKewangan()))
                senaraiKewDokBatal.add(lpp.getIdDokumenKewangan());
            } else {
                if ("T".equals(kodCaraByr)) {
                    tunai ++;
                    LOG.debug("amt tunai =" + amt.doubleValue());
                    amtTunai = amtTunai + amt.doubleValue();                  
                } else if ( "LB".equals(kodCaraByr)
                            || "CB".equals(kodCaraByr)
                            || "CT".equals(kodCaraByr)
                            || "CL".equals(kodCaraByr)
                            || "HC".equals(kodCaraByr)
                            || "IC".equals(kodCaraByr) ){
                    cek ++;
                    amtCek = amtCek + amt.doubleValue();                    
                } else if ( "KK".equals(kodCaraByr)
                        || "VS".equals(kodCaraByr) ) {
                    kk ++;
                    amtKk = amtKk + amt.doubleValue();                    
                } else if ( "WP".equals(kodCaraByr) ) {
                    wp ++;
                    amtWp = amtWp + amt.doubleValue();                    
                } else if ( "KW".equals(kodCaraByr) ) {
                    kw ++;
                    amtKw = amtKw + amt.doubleValue();                    
                }
                jumAmt = jumAmt + amt.doubleValue();
                if (!senaraiKewDokSah.contains(lpp.getIdDokumenKewangan()))
                senaraiKewDokSah.add(lpp.getIdDokumenKewangan());
            }            
        }

        map.put("bilTunai", String.valueOf(tunai) );
        map.put("bilWp", String.valueOf(wp) );
        map.put("bilCek", String.valueOf(cek) );
        map.put("bilKw", String.valueOf(kw) );
        map.put("bilKk", String.valueOf(kk) );
        map.put("amtTunai", String.valueOf(amtTunai) );
        map.put("amtCek", String.valueOf(amtCek) );
        map.put("amtKk", String.valueOf(amtKk) );
        map.put("amtWp", String.valueOf(amtWp) );
        map.put("amtKw", String.valueOf(amtKw) );       

        map.put("bilBatalTunai", String.valueOf(batal_tunai) );
        map.put("bilBatalWp", String.valueOf(batal_wp) );
        map.put("bilBatalCek", String.valueOf(batal_cek) );
        map.put("bilBatalKw", String.valueOf(batal_kw) );
        map.put("bilBatalKk", String.valueOf(batal_kk) );
        map.put("amtBatalTunai", String.valueOf(amtBatalTunai) );
        map.put("amtBatalCek", String.valueOf(amtBatalCek) );
        map.put("amtBatalKk", String.valueOf(amtBatalKk) );
        map.put("amtBatalWp", String.valueOf(amtBatalWp) );
        map.put("amtBatalKw", String.valueOf(amtBatalKw) );

        map.put("jumAmtBatal",String.valueOf(jumAmtBatal) );
        map.put("jumAmt",String.valueOf(jumAmt) );
        map.put("bilSahih", String.valueOf(senaraiKewDokSah.size()) );
        map.put("bilBatal", String.valueOf(senaraiKewDokBatal.size()) );
        
        return map;
    }

    private Map getCukaiInfo3(String idKewDok) {
        Map map = null;
        if (StringUtils.isBlank(idKewDok)) {
            return null;
        } else {
            Injector injector = etanahContextListener.getInjector();

            Session sess = injector.getProvider(Session.class).get();

            StringBuilder sbuilder = new StringBuilder()
                    .append("Select lpp from etanah.model.hasil.LaporanPenyataPemungutItem lpp ")
                    .append("where lpp.idDokumenKewangan = :id");
            
            Query query = sess.createQuery(sbuilder.toString())
                    .setString("id", idKewDok);
            List<LaporanPenyataPemungutItem> senarai = query.list();
            if (!senarai.isEmpty()) {
                map = new HashMap();
                map.put("id", idKewDok);
                map.put("status", String.valueOf(senarai.get(0).getStatus()));
                BigDecimal jum = BigDecimal.ZERO;
                for (LaporanPenyataPemungutItem item : senarai) {
                    jum = jum.add(item.getAmaun());
                }
                map.put("amt", jum.toString());
                String caraByr = getCaraBayar(idKewDok, sess, map);
                map.put("cara_byr", caraByr);
                setInfo(senarai.get(0).getIdTransaksi(), sess, map);
            }            
        }
        return map;
    }

    private void setInfo (long idTrans, Session sess, Map map) {
        
        StringBuilder sbuilder = new StringBuilder()
                    .append("Select tr from etanah.model.Transaksi tr ")
                    .append("where tr.idTransaksi = :id");

         Query query = sess.createQuery(sbuilder.toString())
                    .setParameter("id", idTrans);

         List<Transaksi> senarai = query.list();
         if (!senarai.isEmpty()) {
             Transaksi transaksi = senarai.get(0);
             if ( transaksi.getAkaunKredit() != null) {
                 map.put("idHakmilik", transaksi.getAkaunKredit().getNoAkaun());
             }
             else {
                 map.put("idHakmilik", "");
             }
         }
    }

    private String getCaraBayar (String idKewDok, Session sess, Map map){
            StringBuilder caraByr = new StringBuilder();
            StringBuilder cukaiDescByr = new StringBuilder();

            StringBuilder sbuilder = new StringBuilder()
                    .append("Select dkb from etanah.model.DokumenKewanganBayaran dkb ")
                    .append("where dkb.dokumenKewangan.idDokumenKewangan = :id");

            Query query = sess.createQuery(sbuilder.toString())
                    .setString("id", idKewDok);
            List<DokumenKewanganBayaran> senarai = query.list();
            if (!senarai.isEmpty()) {
                DokumenKewangan dok = senarai.get(0).getDokumenKewangan();
                if (dok != null) {
                    if (dok.getCawangan().getKod().equals("00")) {
                        map.put("daerah", "PTG");
                    } else {
                        map.put("daerah", dok.getCawangan().getDaerah().getNama());
                    }
                    map.put("tarikhMasuk", dok.getInfoAudit().getTarikhMasuk());
                    
                    Pengguna pengguna = dok.getInfoAudit().getDimasukOleh();
                    map.put("cashier", pengguna.getNama());
                    map.put("resit", dok.getIdDokumenKewangan());
                    map.put("idKaunter", pengguna.getIdKaunter());
                }
                
                boolean cash = false;
                boolean cheque = false;
                boolean other = false;
                boolean wp = false;
                boolean kw = false;
                boolean kk = false;
                
                for (DokumenKewanganBayaran dkb : senarai) {                   

                    if (dkb == null
                            || dkb.getCaraBayaran() == null) continue;

                    String kod_cara_bayar = dkb.getCaraBayaran().getKodCaraBayaran().getKod();                   
                    
                    String tmp = kod_cara_bayar;
                    
                    if ("T".equals(kod_cara_bayar) && cash) continue;
                    else if ( ("LB".equals(kod_cara_bayar)
                            || "CB".equals(kod_cara_bayar)
                            || "CT".equals(kod_cara_bayar)
                            || "CL".equals(kod_cara_bayar)
                            || "HC".equals(kod_cara_bayar)
                            || "IC".equals(kod_cara_bayar) ) && cheque) continue;
                    else if ( "WP".equals(kod_cara_bayar) && wp ) continue;
                    else if ( "KW".equals(kod_cara_bayar) && kw ) continue;
                    else if ( ("KK".equals(kod_cara_bayar) || "VS".equals(kod_cara_bayar) ) && kk ) continue;

                    if (!kod_cara_bayar.equals("T")) {
                        tmp = (dkb.getCaraBayaran().getBank() != null
                                    ? dkb.getCaraBayaran().getBank().getKod() : "")
                                + " " + kod_cara_bayar + " "
                                + (StringUtils.isNotBlank(dkb.getCaraBayaran().getNoRujukan())
                                    ? dkb.getCaraBayaran().getNoRujukan() : "");                        
                    }

                    if ("T".equals(kod_cara_bayar)) cash = true;
                    else if ( "LB".equals(kod_cara_bayar)
                            || "CB".equals(kod_cara_bayar)
                            || "CT".equals(kod_cara_bayar)
                            || "CL".equals(kod_cara_bayar)
                            || "HC".equals(kod_cara_bayar)
                            || "IC".equals(kod_cara_bayar)) cheque = true;
                    else if ("WP".equals(kod_cara_bayar)) wp = true;
                    else if ("KW".equals(kod_cara_bayar)) kw = true;
                    else if ("KK".equals(kod_cara_bayar)
                            || "VS".equals(kod_cara_bayar)) kk = true;
                    else other = true;

                    if (caraByr.length() > 0) caraByr.append(",");
                        caraByr.append(kod_cara_bayar);

                    if (cukaiDescByr.length() > 0) cukaiDescByr.append(",");
                        cukaiDescByr.append(tmp);
                }
                map.put("cukai_desc_byr", cukaiDescByr.toString());
            }
            
            return caraByr.toString();
    }

    private Map getCukaiInfo2(String idKewDok) {
        Map map = null;

        if (StringUtils.isBlank(idKewDok)) {
            return null;
        } else {
            Injector injector = etanahContextListener.getInjector();

            Session sess = injector.getProvider(Session.class).get();

            StringBuilder sbuilder = new StringBuilder()
                    .append("Select tr from etanah.model.Transaksi tr ")
                    .append("where tr.dokumenKewangan.idDokumenKewangan = :id");

            Query query = sess.createQuery(sbuilder.toString())
                    .setString("id", idKewDok);

            List<Transaksi> senarai_transaksi = query.list();
            if (senarai_transaksi.size() > 0) {

                map = new HashMap();
                int size_transaksi = senarai_transaksi.size();
                
                map.put("id", idKewDok); 
                map.put("sizeTransaksi", String.valueOf(size_transaksi));

                for (int i = 0; i < size_transaksi; i++) {
                    Transaksi transaksi = senarai_transaksi.get(i);
                    if (transaksi.getAkaunKredit()!= null && transaksi.getAkaunKredit().getNoAkaun() != null)
                        map.put("idHakmilik", transaksi.getAkaunKredit().getNoAkaun());
                    else map.put("idHakmilik", "");
                    map.put("kodHasil" + i, transaksi.getKodTransaksi().getKod());
                    map.put("amntTransaksi" + i, String.valueOf(transaksi.getAmaun()));
                    map.put("status" + i, transaksi.getStatus().getKod());
                }

                DokumenKewangan dok = senarai_transaksi.get(0).getDokumenKewangan();
                List <DokumenKewanganBayaran> senarai_bayaran = dok.getSenaraiBayaran();

                String amaun = String.valueOf(dok.getAmaunBayaran());
                Pengguna pengguna = dok.getInfoAudit().getDimasukOleh();
                StringBuilder senarai_cara_bayar = new StringBuilder();
                
                if (dok.getCawangan().getKod().equals("00")) {
                    map.put("daerah", "PTG");
                } else {
                    map.put("daerah", dok.getCawangan().getDaerah().getNama());
                }

                String nama = "";

                if ( StringUtils.isNotBlank(pengguna.getNama()) ) {
                    nama = pengguna.getNama();
                    if ( nama.length() > 20 ) nama = nama.substring(0,20);
                } else {
                    nama = pengguna.getIdPengguna();
                }
                
                map.put("amaun", amaun);               
                map.put("cashier", nama);
                map.put("resit", dok.getIdDokumenKewangan());
                map.put("idKaunter", pengguna.getIdKaunter());
                map.put("sts", dok.getStatus().getKod());
                map.put("tarikhMasuk", dok.getInfoAudit().getTarikhMasuk());
                
                boolean cash = false;
                boolean cheque = false;
                boolean other = false;
                boolean wp = false;
                boolean kw = false;
                boolean kk = false;
                
                double cashFlow = 0.00;
                double chequeFlow = 0.00;
                double otherFlow = 0.00;                
                
                map.put("size_detail", String.valueOf(senarai_bayaran.size()));
                LOG.debug("...size details = " + senarai_bayaran.size());
                
                int i = 0;                
                for (DokumenKewanganBayaran dokumenKewanganBayaran : senarai_bayaran) {
                    if (dokumenKewanganBayaran == null
                            || dokumenKewanganBayaran.getCaraBayaran() == null) continue;                    

                    String kod_cara_bayar = dokumenKewanganBayaran.getCaraBayaran().getKodCaraBayaran().getKod();

                    BigDecimal amaunt = dokumenKewanganBayaran.getCaraBayaran().getAmaun();
                    
                    map.put("amt_" +i, String.valueOf(amaunt.doubleValue()));
                    LOG.debug("amt = " + String.valueOf(amaunt.doubleValue())) ;
                    map.put("cara_"+i, kod_cara_bayar);
                    map.put("sts_"+i, String.valueOf(dokumenKewanganBayaran.getAktif()));                   

                    i = i+1;
                    String tmp = kod_cara_bayar;
                    if ("T".equals(kod_cara_bayar) && cash) continue;
                    else if ( ("LB".equals(kod_cara_bayar)
                            || "CB".equals(kod_cara_bayar)
                            || "CT".equals(kod_cara_bayar)
                            || "CL".equals(kod_cara_bayar)
                            || "HC".equals(kod_cara_bayar)
                            || "IC".equals(kod_cara_bayar) ) && cheque) continue;
                    else if ( "WP".equals(kod_cara_bayar) && wp ) continue;
                    else if ( "KW".equals(kod_cara_bayar) && kw ) continue;
                    else if ( ("KK".equals(kod_cara_bayar) || "VS".equals(kod_cara_bayar) ) && kk ) continue;
                    
                    if (!kod_cara_bayar.equals("T")) {
                        tmp = (dokumenKewanganBayaran.getCaraBayaran().getBank() != null
                                    ? dokumenKewanganBayaran.getCaraBayaran().getBank().getKod() : "")
                                + " " + kod_cara_bayar + " "
                                + (StringUtils.isNotBlank(dokumenKewanganBayaran.getCaraBayaran().getNoRujukan())
                                    ? dokumenKewanganBayaran.getCaraBayaran().getNoRujukan() : "");
                    }

                    if ("T".equals(kod_cara_bayar)) cash = true;
                    else if ( "LB".equals(kod_cara_bayar)
                            || "CB".equals(kod_cara_bayar)
                            || "CT".equals(kod_cara_bayar)
                            || "CL".equals(kod_cara_bayar)
                            || "HC".equals(kod_cara_bayar)
                            || "IC".equals(kod_cara_bayar)) cheque = true;
                    else if ("WP".equals(kod_cara_bayar)) wp = true;
                    else if ("KW".equals(kod_cara_bayar)) kw = true;
                    else if ("KK".equals(kod_cara_bayar)
                            || "VS".equals(kod_cara_bayar)) kk = true;
                    else other = true;

                    if (senarai_cara_bayar.length() > 0) senarai_cara_bayar.append(",");
                    senarai_cara_bayar.append(tmp);
                    
                }
                map.put("cara_byr", senarai_cara_bayar.toString());
                map.put("cashAmount", String.valueOf(cashFlow) );
                map.put("chequeAmount", String.valueOf(chequeFlow) );
                map.put("otherAmount", String.valueOf(otherFlow) );
                
            }
        }
        return map;
    }    

    private Map getChequeInfo(String id) {
        Map map = null;

        if (StringUtils.isBlank(id)) {
            return null;
        } else {
            Injector injector = etanahContextListener.getInjector();
//            DokumenKewanganBayaranDAO dokumenKewanganDAO = injector.getInstance(DokumenKewanganBayaranDAO.class);
            Session sess = injector.getProvider(Session.class).get();
            CaraBayaranDAO caraBayaranDAO = injector.getInstance(CaraBayaranDAO.class);
//            TransaksiDAO transaksiDAO = injector.getInstance(TransaksiDAO.class);
            map = new HashMap();
            CaraBayaran caraBayaran = caraBayaranDAO.findById(Long.parseLong(id));
            List<DokumenKewanganBayaran> senaraiDokumenBayaran = caraBayaran.getSenaraiDokumenKewanganBayaran();
            StringBuilder sb = new StringBuilder();
            String kodPtj = "";
            String kodJab = "";

            for (DokumenKewanganBayaran dkb : senaraiDokumenBayaran) {
                DokumenKewangan dk = dkb.getDokumenKewangan();
                String[] name = {"dokumenKewangan.idDokumenKewangan"};
                Object[] value = {dk.getIdDokumenKewangan()};
                map.put("noresit", dk.getIdDokumenKewangan());
                if (dk.getAkaun() != null) {
                    map.put("noakaun", dk.getAkaun().getNoAkaun());
                }
//                List<Transaksi> listTrans = transaksiDAO.findByEqualCriterias(name, value, null);

                StringBuilder sbuilder = new StringBuilder()
                        .append("Select tr from etanah.model.Transaksi tr ")
                        .append("where tr.dokumenKewangan.idDokumenKewangan = :id");

                Query query = sess.createQuery(sbuilder.toString())
                        .setString("id", dk.getIdDokumenKewangan());

                List<Transaksi> listTrans = query.list();

                for (Transaksi tr : listTrans) {
                    if (tr.getKodTransaksi() == null) {
                        continue;
                    }

                    String kod = tr.getKodTransaksi().getKod();

                    if (ArrayUtils.contains(KOD_TRANS_TO_EXCLUDE, kod)) {
                        continue;
                    }

                    KodCawangan kc = tr.getCawangan();
                    if (kc != null) {
                        if (StringUtils.isBlank(kodPtj)) {
                            kodPtj = kc.getKodPTJ();
                        }
                        if (StringUtils.isBlank(kodJab)) {
                            kodJab = kc.getKodJabatanSpek();
                        }
                    }

                    boolean flag = false;
                    String[] tmp = sb.toString().split(",");
                    for (String str : tmp) {
                        if (str.equals(kod)) {
                            flag = true;
                            break;
                        }
                    }
                    if (flag) continue;

                    if (sb.length() > 0) {
                        sb.append(",");
                    }
                    sb.append(kod);

//                    if (tr.getKodTransaksi().getKod().equals("61401")) {
//                        map.put("noakaun", tr.getAkaunKredit().getNoAkaun());
//                    }
                }
            }

            map.put("kodtrans", sb.toString());
            map.put("norujukan", caraBayaran.getNoRujukan());
            map.put("carabyr", caraBayaran.getKodCaraBayaran().getKod());
            map.put("amaunbyr", String.valueOf(caraBayaran.getAmaun()));
            map.put("agensi", caraBayaran.getBank().getKod());
            map.put("idcasher", caraBayaran.getInfoAudit().getDimasukOleh().getNama());
            map.put("caw", caraBayaran.getInfoAudit().getDimasukOleh().getKodCawangan().getKod());
            map.put("kodPtj", kodPtj);
            map.put("kodJab", kodJab);

        }

        return map;
    }

    private Map getResitBatal(String id) {
        LOG.debug("id batal = " + id);
        Map map = null;
        Injector injector = etanahContextListener.getInjector();
        DokumenKewanganDAO dokumenKewanganDAO = injector.getInstance(DokumenKewanganDAO.class);
//            TransaksiDAO transaksiDAO = injector.getInstance(TransaksiDAO.class);
        DokumenKewangan dk = dokumenKewanganDAO.findById(id);
        Session sess = injector.getProvider(Session.class).get();

        if (dk != null) {
            LOG.debug("status = " + dk.getStatus().getNama());
            if (dk.getStatus().getKod().equals("B")) {
                map = new HashMap();

//                String[] name = {"dokumenKewangan.idDokumenKewangan"};
//                Object[] value = {dk.getIdDokumenKewangan()};
//                    List<Transaksi> listTrans = transaksiDAO.findByEqualCriterias(name, value, null);
                StringBuilder sbuilder = new StringBuilder()
                        .append("Select tr from etanah.model.Transaksi tr ")
                        .append("where tr.dokumenKewangan.idDokumenKewangan = :id");

                Query query = sess.createQuery(sbuilder.toString())
                        .setString("id", dk.getIdDokumenKewangan());

                List<Transaksi> listTrans = query.list();
                
                if (listTrans.size() > 0) {
                    Transaksi trans = listTrans.get(0);
                    String noAkaun = "";
                    if (trans != null) {
                        if (trans.getPermohonan() != null) {
                            noAkaun = trans.getPermohonan().getIdPermohonan();
                        } else {
                            if (trans.getAkaunKredit() != null && trans.getAkaunKredit().getNoAkaun() != null)
                                noAkaun = trans.getAkaunKredit().getNoAkaun();
                        }
                    }
                    map.put("noAkaun", noAkaun);
                }

                map.put("noresit", dk.getIdDokumenKewangan());
                map.put("amaunbyr", String.valueOf(dk.getAmaunBayaran()));
                String sebab = "";
                if (dk.getKodBatal().getKod().equals("LL")) {
                    sebab = dk.getCatatan();
                } else {
                    sebab = dk.getKodBatal().getNama();
                }
                map.put("sebabBatal", dk.getKodBatal().getNama());
                map.put("catatan", dk.getCatatan());
                map.put("trhBatal", String.valueOf(dk.getInfoAudit().getTarikhKemaskini()));
                map.put("idBatal", dk.getInfoAudit().getDikemaskiniOleh().getIdPengguna());
            }
        }

        return map;
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /** 
     * Handles the HTTP <code>POST</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /** 
     * Returns a short description of the servlet.
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
