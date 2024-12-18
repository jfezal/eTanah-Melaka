package etanah.view.hasil.validator;

import java.util.*;
import java.math.*;
import etanah.dao.*;
import etanah.model.*;
import org.hibernate.*;
import etanah.workflow.*;
import com.google.inject.Inject;
import org.apache.log4j.Logger;
import java.text.SimpleDateFormat;
import etanah.view.stripes.hasil.KutipanHasilManager;

/**
 * @author haqqiem
 * Thu 9 Jan 14
 */
public class PenguranganDendaValidator implements StageListener{
    private static final Logger LOG = Logger.getLogger(PenguranganDendaValidator.class);
    SimpleDateFormat yy = new SimpleDateFormat("yyyy");
    
    @Inject HakmilikDAO hakmilikDAO;
    @Inject PemohonDAO pemohonDAO;
    @Inject PenggunaDAO penggunaDAO;
    @Inject KutipanHasilManager manager;
    @Inject private etanah.kodHasilConfig hasil;
    @Inject KodTransaksiDAO kodTransaksiDAO;
    @Inject KodStatusTransaksiKewanganDAO kodStatusTransaksiDAO;
    
    @Inject protected com.google.inject.Provider<Session> sessionProvider;
    
    private boolean flag = false;
    private Pengguna pengguna = new Pengguna();
    private BigDecimal pengurangan = new BigDecimal(0.00);      // pengurangan = jumlah yg perlu di bayar

    @Override
    public boolean beforeStart(StageContext context) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean beforeGenerateReports(StageContext ctx) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void onGenerateReports(StageContext context) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String beforeComplete(StageContext context, String proposedOutcome) {
        LOG.info("beforeComplete::Start");
        Permohonan permohonan = context.getPermohonan();
        Hakmilik hm = new Hakmilik();

        pengguna = context.getPengguna();
        
        List<Pemohon> senaraiPemohon = new ArrayList();
        String[] name = {"permohonan"};
        Object[] value= {permohonan};
        senaraiPemohon = pemohonDAO.findByEqualCriterias(name, value, null);
        LOG.debug("senaraiPemohon.size :"+senaraiPemohon.size());
        LOG.debug("permohonan.getKodUrusan() :"+permohonan.getKodUrusan().getKod());
        
        if((permohonan.getKodUrusan().getKod().equals("REMRI"))||(permohonan.getKodUrusan().getKod().equals("REMSB"))){
            hm = permohonan.getSenaraiHakmilik().get(0).getHakmilik();
            LOG.info("hakmilik : "+hm.getIdHakmilik());
            
            String queryAkaun = "SELECT a FROM etanah.model.Akaun a WHERE a.hakmilik.idHakmilik = :idHakmilik AND a.kodAkaun.kod='AC'";
            Query qAkaun = sessionProvider.get().createQuery(queryAkaun);
            qAkaun.setString("idHakmilik", hm.getIdHakmilik());
            Akaun akaun  = (Akaun) qAkaun.uniqueResult();
            LOG.info("akaun : "+akaun.getNoAkaun());
            
            String query = "SELECT mf FROM etanah.model.FasaPermohonan mf WHERE mf.permohonan.idPermohonan = :idPermohonan AND mf.keputusan IS NOT NULL";
            Query q = sessionProvider.get().createQuery(query);
            q.setString("idPermohonan", permohonan.getIdPermohonan());
            List<FasaPermohonan> senaraiFasaPermohonan  = q.list();
            LOG.info("senaraiFasaPermohonan.size() : "+senaraiFasaPermohonan.size());

            for (FasaPermohonan fp : senaraiFasaPermohonan) {
                if(fp.getKeputusan().getKod().equals("L")){
                    flag = true;
                }
            }
            
            if(flag){
                pengurangan = calculatePengurangan(hm);     // pengurangan = jumlah yg perlu di bayar
                BigDecimal jumlahPengurangan = akaun.getBaki().subtract(pengurangan);
                LOG.info("jumlahPengurangan : "+jumlahPengurangan);
                
                Transaksi t = createTransaksi(jumlahPengurangan, pengguna, akaun, permohonan.getKodUrusan(), jumlahPengurangan, permohonan);
                LOG.info("t.getIdTransaksi() : "+t.getIdTransaksi());
                updateBakiAkaun(pengurangan, pengguna, akaun);
            }
        }
        LOG.info("beforeComplete::Finish");
        return proposedOutcome;
    }
    
    public Transaksi createTransaksi(BigDecimal kadar, Pengguna pguna, Akaun akaunKr, KodUrusan urusan, BigDecimal amaunRemisyen, Permohonan mohon){
        LOG.info("===== createTransaksi : START =====");
        Transaksi t = new Transaksi();
        Akaun akaunRemisyen = findAkaunRemisyen(pguna.getKodCawangan(), amaunRemisyen, pguna);
        Date now = new Date();
        int tahun = Integer.parseInt(yy.format(now));
        InfoAudit ia = new InfoAudit();
        
        t.setAkaunDebit(akaunRemisyen);
        t.setAkaunKredit(akaunKr);
        t.setAmaun(kadar);
        t.setBayaranAgensi("N");
        t.setCawangan(pguna.getKodCawangan());
        t.setKuantiti(1);
        t.setPermohonan(mohon);
        t.setStatus(kodStatusTransaksiDAO.findById('A'));
        t.setTahunKewangan(tahun);
        t.setUntukTahun(tahun);
            ia.setDimasukOleh(pguna);
            ia.setTarikhMasuk(new java.util.Date());
        t.setInfoAudit(ia);
        if(urusan.getKod().equals("REMRI"))
            t.setKodTransaksi(kodTransaksiDAO.findById(hasil.getProperty("remisyen.remri")));
        if(urusan.getKod().equals("REMSB"))
            t.setKodTransaksi(kodTransaksiDAO.findById(hasil.getProperty("remisyen.remsb")));
        
        manager.save(t);
        LOG.info("===== createTransaksi : FINISH =====");
        return t;
    }
    
    public Akaun findAkaunRemisyen(KodCawangan caw, BigDecimal amaunRemisyen, Pengguna pguna){
        LOG.info("===== findAkaunRemisyen : START =====");
        Date now = new Date();
        InfoAudit ia = new InfoAudit();
        Akaun a = new Akaun();
        
        String query = "SELECT a FROM etanah.model.Akaun a WHERE a.cawangan.kod = :kod AND a.kodAkaun.kod = 'AR'";
        Query q = sessionProvider.get().createQuery(query);
        q.setString("kod", caw.getKod());
        a  = (Akaun) q.uniqueResult();
        LOG.info("a.getNoAkaun() : "+a.getNoAkaun());
        
        //update baki for Akaun Remisyen (AR)
        a.setBaki(a.getBaki().add(amaunRemisyen));
            ia.setDimasukOleh(a.getInfoAudit().getDimasukOleh());
            ia.setTarikhMasuk(a.getInfoAudit().getTarikhMasuk());
            ia.setDiKemaskiniOleh(pguna);
            ia.setTarikhKemaskini(now);            
        a.setInfoAudit(ia);
        
        manager.saveOrUpdate(a);
        LOG.info("===== findAkaunRemisyen : FINISH =====");
        return a;
    }
    
    public Akaun updateBakiAkaun(BigDecimal total, Pengguna pguna, Akaun akaun) {
        LOG.info("===== updateBakiAkaun : STRAT =====");
        Date now = new Date();
        InfoAudit ia = new InfoAudit();
        Akaun a = akaun;
        
        a.setBaki(total);
            ia.setDimasukOleh(a.getInfoAudit().getDimasukOleh());
            ia.setTarikhMasuk(a.getInfoAudit().getTarikhMasuk());
            ia.setDiKemaskiniOleh(pguna);
            ia.setTarikhKemaskini(now);
        a.setInfoAudit(ia);
        
        LOG.info("===== updateBakiAkaun : FINISH =====");
        return a;
    }
    
    public int getJenisTanah(KodBandarPekanMukim kod){                  // followed MLR
        LOG.info("===== getJenisTanah : START =====");
        String bpm = kod.getKod()+"" ;
        LOG.info("nilai bpm : "+bpm);
        int typeNum = bpm.equals("31")  ? 1        // Melaka Town
                    : bpm.equals("30")  ? 1        // Bukit Baru Town
                    : bpm.equals("103") ? 2        // Alor Gajah    
                    : bpm.equals("61")  ? 2        // Jasin    
                    : bpm.equals("84")  ? 2        // Masjid Tanah
                    : bpm.equals("104") ? 2        // Masjid Tanah
                    : bpm.equals("50")  ? 2        // Merlimau
                    : bpm.equals("62")  ? 2        // Merlimau
                    : bpm.equals("90")  ? 2        // Pulau Sebang
                    : bpm.equals("105") ? 2        // Pulau Sebang
                    : bpm.equals("26")  ? 3        // Tangga Batu
                    : bpm.equals("39")  ? 3        // Tangga Batu
                    : bpm.equals("21")  ? 3        // Paya Rumput
                    : bpm.equals("37")  ? 3        // Paya Rumput
                    : bpm.equals("11")  ? 3        // Bukit Rambai
                    : bpm.equals("34")  ? 3        // Bukit Rambai
                    : bpm.equals("15")  ? 3        // Kandang
                    : bpm.equals("35")  ? 3        // Kandang
                    : bpm.equals("2")   ? 3        // Ayer Molek
                    : bpm.equals("32")  ? 3        // Ayer Molek
                    : bpm.equals("27")  ? 3        // Tanjong Kling
                    : bpm.equals("40")  ? 3        // Tanjong Kling
                    : bpm.equals("25")  ? 3        // Sungai Udang
                    : bpm.equals("38")  ? 3        // Sungai Udang
                    : bpm.equals("5")   ? 3        // Batu Berendam         <<-- sama line 113
                    : bpm.equals("33")  ? 3        // Batu Berendam         <<-- sama line 113
                    : bpm.equals("16")  ? 3        // Klebang
                    : bpm.equals("36")  ? 3        // Klebang
                    : bpm.equals("108") ? 3        // Lubok Cina
                    : bpm.equals("81 ") ? 3        // Kuala Sungai Baru
                    : bpm.equals("107") ? 3        // Kuala Sungai Baru
                    : bpm.equals("93")  ? 3        // Rembia
                    : bpm.equals("109") ? 3        // Rembia
                    : bpm.equals("76")  ? 3        // Durian Tunggal
                    : bpm.equals("106") ? 3        // Durian Tunggal
                    : bpm.equals("70")  ? 3        // Simpang Bekoh
                    : bpm.equals("63")  ? 3        // Asahan
                    : bpm.equals("45")  ? 3        // Chin Chin
                    : bpm.equals("66")  ? 3        // Chin Chin
                    : bpm.equals("58")  ? 3        // Sungai Rambai
                    : bpm.equals("71")  ? 3        // Sungai Rambai
                    : bpm.equals("51")  ? 3        // Nyalas
                    : bpm.equals("68")  ? 3        // Nyalas
                    : bpm.equals("42")  ? 3        // Batang Melaka
                    : bpm.equals("64")  ? 3        // Batang Melaka
                    : bpm.equals("65")  ? 3        // Bemban
                    : bpm.equals("67")  ? 3        // Kesang Pajak
                    : bpm.equals("54")  ? 3        // Selandar
                    : bpm.equals("69")  ? 3        // Selandar
                    : bpm.equals("17")  ? 4        // Klebang Kecil              
                    : bpm.equals("4")   ? 4        // Balai Panjang
                    : bpm.equals("3")   ? 4        // Bachang
                    : bpm.equals("22")  ? 4        // Pringgit
                    : bpm.equals("14")  ? 4        // Ujong Pasir
                    : bpm.equals("19")  ? 4        // Padang Semabok
                    : bpm.equals("10")  ? 4        // Bukit Piatu
                    : bpm.equals("24")  ? 4        // Semabok
                    : bpm.equals("12")  ? 4        // Cheng
                    : bpm.equals("6")   ? 4        // Bertam
//                    : bpm.equals("01") ? 4        // part of Bukit Baru    <<-- sama line 114
//                    : bpm.equals("01") ? 5        // Batu Berendam         <<-- sama line 87
//                    : bpm.equals("01") ? 5        // part of Bukit Baru    <<-- sama line 112
//                    : bpm.equals("01") ? 5        // part of Durian Tunggal
//                    : bpm.equals("01") ? 5        // part of Bukit Katil
//                    : bpm.equals("01") ? 5        // part of Bachang
//                    : bpm.equals("01") ? 5        // part of Peringgit
//                    : bpm.equals("01") ? 5        // part of Ayer Panas
//                    : bpm.equals("01") ? 5        // part of Kesang
//                    : bpm.equals("01") ? 5        // part of Ayer Molek
                    : 6;
        
        
        LOG.info("typeNum : "+typeNum);
        LOG.info("===== getJenisTanah : FINISH =====");
        return typeNum;
    }
    
    public BigDecimal calculatePengurangan(Hakmilik hakmilik){
        LOG.info("===== calculatePengurangan : START =====");
        LOG.info("hakmilik.getIdHakmilik() : "+hakmilik.getIdHakmilik());
        BigDecimal penguranganKepada  = new BigDecimal(0.00);
        BigDecimal conv = BigDecimal.ZERO;
        BigDecimal area = BigDecimal.ZERO;
        Hakmilik h = hakmilikDAO.findById(hakmilik.getIdHakmilik());
        
        double constant  = 0.00;
        LOG.info("h.getBandarPekanMukim().getKod() : "+h.getBandarPekanMukim().getKod());
        int jenis = getJenisTanah(h.getBandarPekanMukim());
        
        String kodLuas = h.getKodUnitLuas().getKod();
        
        if((kodLuas.equals("E"))||(kodLuas.equals("P"))){            // Ekar Perpuluhan 
            constant = 4046.8564224;
            conv = BigDecimal.valueOf(constant);
            area = h.getLuas().multiply(conv);
        }
        else if(kodLuas.equals("H")){                                // Hektar
            constant = 10000;
            conv = BigDecimal.valueOf(constant);
            area = h.getLuas().multiply(conv);
        }
        else if(kodLuas.equals("K")){                                // Kaki Persegi
            constant = 0.09290304;
            conv = BigDecimal.valueOf(constant);
            area = h.getLuas().multiply(conv);
        }
        else {                                                       // Meter Persegi
            constant = 1.0;
            conv = BigDecimal.valueOf(constant);
            area = h.getLuas().multiply(conv);            
        }
        LOG.info("luas tanah = "+area);
        
        switch (jenis){
            case 1 :
                // Town Land
                // Bandar Melaka & Bandar Bukit Baru
                penguranganKepada = area.multiply(new BigDecimal(0.18));
                break;
            case 2 :
                // Town Land II
                // Alor Gajah, Jasin, Masjid Tanah, Merlimau, Pulau Sebang
                penguranganKepada = area.multiply(new BigDecimal(0.18));
                break;
            case 3 :
                // Village Land
                // Tangga Batu, Paya Rumput, Bukit Rambai, Kandang, Ayer Molek,
                // Tanjomg Kling, Sungai Udang, Batu Berendam, Klebang, Lubok Cina,
                // Kuala Sungai Baru, Rembia, Durian Tunggal, Simpang Bekoh,
                // Asahan, Chin Chin, Sungai Rambai, Nyalas, Batang Melaka,
                // Bemban, Kesang Pajak, Selandar
                penguranganKepada = area.multiply(new BigDecimal(0.09));
                break;
            case 4 :
                // Country Land I : Boundry Town
                // Klebang Kecil, Balai Panjang, Bachang, Peringgit, Ujong Pasir,
                // Padang Semabok, Bukit Piatu, Semabok, Cheng, Bertam, part of Bukit Baru
                penguranganKepada = area.multiply(new BigDecimal(0.09));
                break;
            case 5 :
                // Country Land II : Hang Tuah Jaya
                // Batu Berendam, part of Bukit Baru, part of Durian Tunggal,
                // part ofBukit Katil, part of Bachang, part of Peringgit,
                // part of Ayer Panas, part of Kesang, part of Ayer Molek
                penguranganKepada = area.multiply(new BigDecimal(0.09));
                break;
            case 6 :
                // Country Land III : Other Mukim
                // Klebang Kecil, Balai Panjang, Bachang, Peringgit, Ujong Pasir,
                // Padang Semabok, Bukit Piatu, Semabok, Cheng, Bertam, part of Bukit Baru
                penguranganKepada = area.multiply(new BigDecimal(0.036));
                break;
        }
        LOG.info("pengurangan Denda : "+penguranganKepada.setScale(1, RoundingMode.UP));
        LOG.info("===== calculatePengurangan : FINISH =====");
        return penguranganKepada.setScale(1, RoundingMode.UP);
    }        

    @Override
    public void afterComplete(StageContext context) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String beforePushBack(StageContext context, String proposedOutcome) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void afterPushBack(StageContext context) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public BigDecimal getPengurangan() {
        return pengurangan;
    }

    public void setPengurangan(BigDecimal pengurangan) {
        this.pengurangan = pengurangan;
    }

    public Pengguna getPengguna() {
        return pengguna;
    }

    public void setPengguna(Pengguna pengguna) {
        this.pengguna = pengguna;
    }
    
}
