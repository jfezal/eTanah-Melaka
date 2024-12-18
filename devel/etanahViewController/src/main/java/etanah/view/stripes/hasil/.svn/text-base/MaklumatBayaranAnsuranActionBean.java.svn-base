/**
 * Action bean for Maklumat Bayaran Ansuran for Negeri Sembilan
 *
 * @author Mohd Hairudin Mansur
 * @version 1.0 14 Dec 2009
 */
package etanah.view.stripes.hasil;

import etanah.dao.*;
import able.stripes.*;
import com.google.inject.Inject;
import etanah.model.*;
import etanah.service.AnsuranService;
import etanah.view.etanahActionBeanContext;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.apache.log4j.Logger;
import net.sourceforge.stripes.action.*;
import net.sourceforge.stripes.controller.LifecycleStage;

@UrlBinding("/hasil/maklumat_bayaran_ansuran")
public class MaklumatBayaranAnsuranActionBean extends AbleActionBean {

    private static final Logger LOG = Logger.getLogger(MaklumatBayaranAnsuranActionBean.class);
    private static final boolean IS_LOG_DEBUG = LOG.isDebugEnabled();

    private Permohonan permohonan;
    private HakmilikPermohonan hakmilikPermohonan;
    private Hakmilik hakmilik;
    private Akaun akaun;
    private Akaun akaunAmanah;

    private String idPermohonan;
    private boolean flag = false;
    private boolean checking = false;
    private boolean denda = false;
    private boolean simpanBtn = false;
    private int tempoh;
    private double firstPayment;
    private BigDecimal monthly = new BigDecimal(0);
    private BigDecimal lastPayment = new BigDecimal(0);
    private BigDecimal dendaPayment = new BigDecimal(0);
    private String alasan;
    private String tarikhAnsuran;
    private ArrayList jadual = new ArrayList();
    private ArrayList year = new ArrayList();
    private String bulan = null;
    private static String kodNegeri;

    private PermohonanDAO permohonanDAO;
    private HakmilikPermohonanDAO hakmilikPermohonanDAO;
    private HakmilikDAO hakmilikDAO;
    private KodAkaunDAO kodAkaunDAO;
    private AkaunDAO akaunDAO;
    BigDecimal fine;
    BigDecimal amauntAnsuran;
    String noResitDepo;
    BigDecimal totalValue;

    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

    @Inject
    KutipanHasilManager service;

    @Inject
    RemisyenManager manager;
    
    @Inject
    AnsuranService ansuranService;
    @Inject
    private etanah.Configuration conf;

    @Inject
    public MaklumatBayaranAnsuranActionBean(PermohonanDAO permohonanDAO, HakmilikPermohonanDAO hakmilikPermohonanDAO,
                                            HakmilikDAO hakmilikDAO, KodAkaunDAO kodAkaunDAO, AkaunDAO akaunDAO){
        this.permohonanDAO = permohonanDAO;
        this.hakmilikPermohonanDAO = hakmilikPermohonanDAO;
        this.hakmilikDAO = hakmilikDAO;
        this.kodAkaunDAO = kodAkaunDAO;
        this.akaunDAO = akaunDAO;
    }

    @DefaultHandler
    public Resolution showForm() {
        if("04".equals(conf.getProperty("kodNegeri"))){
            kodNegeri = "melaka";
        }else
            kodNegeri = "n9";
        return new JSP("hasil/maklumat_bayaran_ansuran.jsp").addParameter("tab", "true");
    }

    public Resolution showForm2() {
        simpanBtn = true;
        return new JSP("hasil/maklumat_bayaran_ansuran.jsp").addParameter("tab", "true");
    }

    @Before(stages={LifecycleStage.BindingAndValidation})
    public void rehydrate() throws ParseException{
        LOG.info("rehydrate:start");
       if("04".equals(conf.getProperty("kodNegeri"))){
            kodNegeri = "melaka";
        }else{
            kodNegeri = "n9";
        }
        idPermohonan = (String)getContext().getRequest().getSession().getAttribute("idPermohonan");

        permohonan = permohonanDAO.findById(idPermohonan);
        tempoh= permohonan.getTempohBulan();
        String idHakmilik = null;

        String [] n1 = {"permohonan"};
        Object [] v1 = {getPermohonan()};
        List<HakmilikPermohonan> hpList = hakmilikPermohonanDAO.findByEqualCriterias(n1, v1, null);
        Hakmilik hm = hpList.get(0).getHakmilik();
        LOG.info("idHakmilik : "+hm.getIdHakmilik());
        idHakmilik = hm.getIdHakmilik();
//        for(HakmilikPermohonan hp : hpList){
//            idHakmilik = hp.getHakmilik().getIdHakmilik();
//        }

        hakmilik = hakmilikDAO.findById(idHakmilik);
        String [] n2 = {"hakmilik"};
        Object [] v2 = {getHakmilik()};
        List<Akaun> senaraiAkaun = akaunDAO.findByEqualCriterias(n2, v2, null);
        String akaunCukai = null;
        String akAmanah = null;
        for (Akaun ak : senaraiAkaun) {
            if(ak.getKodAkaun().getKod().equals("AC")){
                akaunCukai = ak.getNoAkaun();
            }
            if(ak.getKodAkaun().getKod().equals("ACT")){
                akAmanah = ak.getNoAkaun();
            }
        }
        akaun = akaunDAO.findById(akaunCukai);

        //  untuk melaka
        if(kodNegeri.equals("melaka")){
//            if(akAmanah !=null){
//                akaunAmanah = akaunDAO.findById(akAmanah);
//                flag = true;

                if(permohonan.getTarikhMula() != null){
                    flag = true;
                    checking = true;
                    tarikhAnsuran = sdf.format(permohonan.getTarikhMula());
                    table(permohonan.getTempohBulan(), permohonan.getTarikhMula(),permohonan);
                }
//            }
        }

        //  untuk n9
        if(kodNegeri.equals("n9")){
            if(permohonan.getTempohBulan() != null && permohonan.getTarikhMula() != null){
                flag = true;
                checking = true;

                tarikhAnsuran = sdf.format(permohonan.getTarikhMula());
                table(permohonan.getTempohBulan(), permohonan.getTarikhMula(),permohonan);
            }
        }
    }

    public Resolution janaJadual() throws ParseException{
        flag = true;
        etanahActionBeanContext ctx = new etanahActionBeanContext();
        ctx = (etanahActionBeanContext) getContext();

        Pengguna pengguna = ctx.getUser();
        KodCawangan caw = pengguna.getKodCawangan();
        Date now = new Date();
        InfoAudit ia = new InfoAudit();
        ia.setDimasukOleh(pengguna);
        ia.setTarikhMasuk(new java.util.Date());
        Date trkh = sdf.parse(tarikhAnsuran);
        LOG.info("tarikhAnsuran : "+tarikhAnsuran+" trkh : "+trkh);
//        tempoh = permohonan.getTempohBulan();
if(tempoh > 1){
        table(tempoh, trkh,permohonan);
}
        return new JSP("hasil/maklumat_bayaran_ansuran.jsp").addParameter("tab", "true");
    }

    public Resolution generateTable() throws ParseException{
        flag = true;
        etanahActionBeanContext ctx = new etanahActionBeanContext();
        ctx = (etanahActionBeanContext) getContext();

        Pengguna pengguna = ctx.getUser();
        KodCawangan caw = pengguna.getKodCawangan();
        Date now = new Date();
        InfoAudit ia = new InfoAudit();
        ia.setDimasukOleh(pengguna);
        ia.setTarikhMasuk(new java.util.Date());
        Date trkh = sdf.parse(tarikhAnsuran);
//        tempoh = permohonan.getTempohBulan();
        table(tempoh, trkh, permohonan);

        if(checking){
            permohonan.setSebab(permohonan.getSebab());
            permohonan.setTempohBulan(tempoh);
            permohonan.setTarikhMula(trkh);
            manager.save(permohonan, pengguna);
        }
        else{
            permohonan.setSebab(permohonan.getSebab());
            permohonan.setTempohBulan(tempoh);
            permohonan.setTarikhMula(trkh);
            manager.save(permohonan, pengguna);
        }

        addSimpleMessage("Maklumat Telah Berjaya Disimpan");
        return new JSP("hasil/maklumat_bayaran_ansuran.jsp").addParameter("tab", "true");
    }

    public void table(int tt, Date d, Permohonan mohon) throws ParseException{
        jadual = new ArrayList();
        year = new ArrayList();
        BigDecimal x = new BigDecimal(0.00);
        BigDecimal dendaSemasa = new BigDecimal(0.00);
//        melaka xde akaun amanah 21/06/2013
//        if(kodNegeri.equals("melaka")){
//            x = akaunAmanah.getAmaunMatang().add(akaunAmanah.getBaki());
//        }else{
//            x = akaun.getBaki();
//        }
        

        int month = d.getMonth();
        int tahun = 0;

        for(int i=0;i<tt;i++){
            int bln = month +i;
            String t = new SimpleDateFormat("yyyy").format(d);
            tahun = Integer.parseInt(t);
            if(bln > 11){
                bln = bln - 12;
                tahun = tahun + 1;
            }

            if(bln == 0){
                denda = false;
                bulan = "Januari";}
            if(bln == 1){
                denda = false;
                bulan = "Februari";}
            if(bln == 2){
                denda = false;
                bulan = "Mac";}
            if(bln == 3){
                denda = false;
                bulan = "April";}
            if(bln == 4){
                denda = false;
                bulan = "Mei";}
            if(bln == 5){
                denda = true;
                bulan = "Jun";}
            if(bln == 6){
                denda = true;
                bulan = "Julai";}
            if(bln == 7){
                denda = true;
                bulan = "Ogos";}
            if(bln == 8){
                denda = true;
                bulan = "September";}
            if(bln == 9){
                denda = true;
                bulan = "Oktober";}
            if(bln == 10){
                denda = true;
                bulan = "November";}
            if(bln == 11){
                denda = true;
                bulan = "Disember";}

            jadual.add(bulan);
            year.add(tahun);
        }
        if(!denda){
            x = akaun.getBaki();
            fine = new BigDecimal(BigInteger.ZERO);
        }else{
            x = calculateDenda(akaun, tahun, mohon);
        }
        LOG.info("xxxxxxxxxxxxx : "+x);
        BigDecimal temp = x.remainder(new BigDecimal(tt));
        totalValue = x;
        if(temp.compareTo(new BigDecimal(0)) == 0){
            monthly = x.divide(new BigDecimal(tt));
            lastPayment = monthly;
        }
        if(temp.compareTo(new BigDecimal(0)) == 1){
            int dd = tt-1;
            double m = Math.ceil(x.doubleValue()/tt);
            monthly = new BigDecimal(m);
            lastPayment = x.subtract(monthly.multiply(new BigDecimal(dd)));
        }
        PermohonanAnsuran ansuran = ansuranService.findByIdPermohonan(mohon);
        ansuran.setDenda(fine);
        noResitDepo = ansuran.getIdResitDepo();
        amauntAnsuran = ansuran.getAmaunDepo();
        if (tt == 6) {
            ansuran.setJadualByr1(monthly);
            ansuran.setJadualByr2(monthly);
            ansuran.setJadualByr3(monthly);
            ansuran.setJadualByr4(monthly);
            ansuran.setJadualByr5(monthly);
            ansuran.setJadualByr6(lastPayment);
        } else if (tt == 5) {
            ansuran.setJadualByr1(monthly);
            ansuran.setJadualByr2(monthly);
            ansuran.setJadualByr3(monthly);
            ansuran.setJadualByr4(monthly);
            ansuran.setJadualByr5(lastPayment);
            ansuran.setJadualByr6(new BigDecimal(BigInteger.ZERO));
        } else if (tt == 4) {
            ansuran.setJadualByr1(monthly);
            ansuran.setJadualByr2(monthly);
            ansuran.setJadualByr3(monthly);
            ansuran.setJadualByr4(lastPayment);
            ansuran.setJadualByr5(new BigDecimal(BigInteger.ZERO));
            ansuran.setJadualByr6(new BigDecimal(BigInteger.ZERO));
        } else if (tt == 3) {
             ansuran.setJadualByr1(monthly);
            ansuran.setJadualByr2(monthly);
            ansuran.setJadualByr3(lastPayment);
            ansuran.setJadualByr4(new BigDecimal(BigInteger.ZERO));
            ansuran.setJadualByr5(new BigDecimal(BigInteger.ZERO));
            ansuran.setJadualByr6(new BigDecimal(BigInteger.ZERO));
        } else if (tt == 2) {
             ansuran.setJadualByr1(monthly);
            ansuran.setJadualByr2(lastPayment);
            ansuran.setJadualByr3(new BigDecimal(BigInteger.ZERO));
            ansuran.setJadualByr4(new BigDecimal(BigInteger.ZERO));
            ansuran.setJadualByr5(new BigDecimal(BigInteger.ZERO));
            ansuran.setJadualByr6(new BigDecimal(BigInteger.ZERO));
        } else if (tt == 1) {
            ansuran.setJadualByr1(lastPayment);
            ansuran.setJadualByr2(new BigDecimal(BigInteger.ZERO));
            ansuran.setJadualByr3(new BigDecimal(BigInteger.ZERO));
            ansuran.setJadualByr4(new BigDecimal(BigInteger.ZERO));
            ansuran.setJadualByr5(new BigDecimal(BigInteger.ZERO));
            ansuran.setJadualByr6(new BigDecimal(BigInteger.ZERO));
        }
      ansuranService.saveAnsuran(ansuran);
    }

    public Hakmilik getHakmilik() {
        return hakmilik;
    }
    
    public BigDecimal calculateDenda(Akaun ak, int tahun, Permohonan mohon){
        BigDecimal d = new BigDecimal(0);
        BigDecimal x = new BigDecimal(0);
        boolean flgDenda = false;
        List<Transaksi> lt = ak.getSenaraiTransaksiDebit();
        for (Transaksi t : lt) {
            if((t.getKodTransaksi().getKod().equals("76152"))&&(t.getUntukTahun()==tahun)){
                flgDenda = true;
            }
        }
        if(flgDenda){
            d = ak.getBaki();
        }else{
            //baki plus table ansuran
            PermohonanAnsuran ansuran = ansuranService.findByIdPermohonan(mohon);
            BigDecimal beforedepo = ak.getBaki().add(ansuran.getAmaunDepo());
            if (beforedepo.intValue() > 100) {
                x = new BigDecimal(0.10);
                fine = (beforedepo.multiply(x)).setScale(1, BigDecimal.ROUND_HALF_UP);
                d = ak.getBaki().add(new BigDecimal(beforedepo.multiply(x).doubleValue()).setScale(1, BigDecimal.ROUND_HALF_UP));
            } else if (beforedepo.intValue() > 50) {
                x = new BigDecimal(10.0);
                d = ak.getBaki().add(x);
            } else if (beforedepo.intValue() > 20) {
                x = new BigDecimal(5.0);
                d = ak.getBaki().add(x);
            } else if (beforedepo.intValue() >= 5) {
                x = new BigDecimal(1.5);
                d = ak.getBaki().add(x);
            } else if (beforedepo.intValue() > 0) {
                x = new BigDecimal(1.0);
                d = ak.getBaki().add(x);
            }
        }

        LOG.info("baki : "+d);
        return d;
    }

    public void setHakmilik(Hakmilik hakmilik) {
        this.hakmilik = hakmilik;
    }

    public HakmilikPermohonan getHakmilikPermohonan() {
        return hakmilikPermohonan;
    }

    public void setHakmilikPermohonan(HakmilikPermohonan hakmilikPermohonan) {
        this.hakmilikPermohonan = hakmilikPermohonan;
    }

    public Permohonan getPermohonan() {
        return permohonan;
    }

    public void setPermohonan(Permohonan permohonan) {
        this.permohonan = permohonan;
    }

    public String getIdPermohonan() {
        return idPermohonan;
    }

    public void setIdPermohonan(String idPermohonan) {
        this.idPermohonan = idPermohonan;
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public Akaun getAkaun() {
        return akaun;
    }

    public void setAkaun(Akaun akaun) {
        this.akaun = akaun;
    }

    public int getTempoh() {
        return tempoh;
    }

    public void setTempoh(int tempoh) {
        this.tempoh = tempoh;
    }

    public String getAlasan() {
        return alasan;
    }

    public void setAlasan(String alasan) {
        this.alasan = alasan;
    }

    public String getTarikhAnsuran() {
        return tarikhAnsuran;
    }

    public void setTarikhAnsuran(String tarikhAnsuran) {
        this.tarikhAnsuran = tarikhAnsuran;
    }

    public ArrayList getJadual() {
        return jadual;
    }

    public void setJadual(ArrayList jadual) {
        this.jadual = jadual;
    }

    public String getBulan() {
        return bulan;
    }

    public void setBulan(String bulan) {
        this.bulan = bulan;
    }

    public ArrayList getYear() {
        return year;
    }

    public void setYear(ArrayList year) {
        this.year = year;
    }

    public double getFirstPayment() {
        return firstPayment;
    }

    public void setFirstPayment(double firstPayment) {
        this.firstPayment = firstPayment;
    }

    public BigDecimal getMonthly() {
        return monthly;
    }

    public void setMonthly(BigDecimal monthly) {
        this.monthly = monthly;
    }

    public Akaun getAkaunAmanah() {
        return akaunAmanah;
    }

    public void setAkaunAmanah(Akaun akaunAmanah) {
        this.akaunAmanah = akaunAmanah;
    }

    public String getKodNegeri() {
        return kodNegeri;
    }

    public void setKodNegeri(String kodNegeri) {
        this.kodNegeri = kodNegeri;
    }

    public boolean isChecking() {
        return checking;
    }

    public void setChecking(boolean checking) {
        this.checking = checking;
    }

    public boolean isSimpanBtn() {
        return simpanBtn;
    }

    public void setSimpanBtn(boolean simpanBtn) {
        this.simpanBtn = simpanBtn;
    }

    public BigDecimal getLastPayment() {
        return lastPayment;
    }

    public void setLastPayment(BigDecimal lastPayment) {
        this.lastPayment = lastPayment;
    }

    public boolean isDenda() {
        return denda;
    }

    public void setDenda(boolean denda) {
        this.denda = denda;
    }

    public BigDecimal getDendaPayment() {
        return dendaPayment;
    }

    public void setDendaPayment(BigDecimal dendaPayment) {
        this.dendaPayment = dendaPayment;
    }

    public BigDecimal getFine() {
        return fine;
    }

    public void setFine(BigDecimal fine) {
        this.fine = fine;
    }

    public String getNoResitDepo() {
        return noResitDepo;
    }

    public void setNoResitDepo(String noResitDepo) {
        this.noResitDepo = noResitDepo;
    }

    public BigDecimal getTotalValue() {
        return totalValue;
    }

    public void setTotalValue(BigDecimal totalValue) {
        this.totalValue = totalValue;
    }

    public BigDecimal getAmauntAnsuran() {
        return amauntAnsuran;
    }

    public void setAmauntAnsuran(BigDecimal amauntAnsuran) {
        this.amauntAnsuran = amauntAnsuran;
    }
    
    
    
}
