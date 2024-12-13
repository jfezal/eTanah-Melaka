package etanah.view.stripes.hasil;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.model.*;
import etanah.dao.*;
import etanah.view.etanahActionBeanContext;
import java.lang.Math;
import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;
import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author abdul.hakim
 */
@UrlBinding("/hasil/pelarasan")
public class PelarasanActionBean extends AbleActionBean {

    private static final Logger LOG = Logger.getLogger(PelarasanActionBean.class);
    private static final boolean IS_LOG_DEBUG = LOG.isDebugEnabled();
    private static final String DEFAULT_VIEW = "hasil/pelarasan.jsp";

    private static String kodNegeri;
    private String idPermohonan;
    private BigDecimal remisyen = new BigDecimal(0.00);
    private BigDecimal debit = new BigDecimal(0.00);
    private BigDecimal kredit = new BigDecimal(0.00);
    private BigDecimal tunggakan = new BigDecimal(0.00);
    private BigDecimal denda = new BigDecimal(0.00);
    private BigDecimal notis = new BigDecimal(0.00);
    private BigDecimal cukai = new BigDecimal(0.00);
    private BigDecimal cukaiAsal = new BigDecimal(0.00);
    private BigDecimal total = new BigDecimal(0.00);

    private boolean fDebit = false;
    private boolean fKredit = false;
    private boolean fTunggakkan = false;
    private boolean fDenda = false;
    private boolean fNotis = false;
    private boolean fRemisyen = false;

    private Hakmilik hakmilik;
    private Akaun akaun;
    private Permohonan permohonan;
    private HakmilikPermohonan hakmilikPermohonan;
    private Pihak pihak;

    private HakmilikDAO hakmilikDAO;
    private PermohonanDAO permohonanDAO;
    private HakmilikPermohonanDAO hakmilikPermohonanDAO;
    private PihakDAO pihakDAO;
    SimpleDateFormat yy = new SimpleDateFormat("yyyy");


    @Inject
    public PelarasanActionBean(HakmilikDAO hakmilikDAO, PermohonanDAO permohonanDAO,
                               HakmilikPermohonanDAO hakmilikPermohonanDAO, PihakDAO pihakDAO){
        this.hakmilikDAO = hakmilikDAO;
        this.permohonanDAO = permohonanDAO;
        this.hakmilikPermohonanDAO = hakmilikPermohonanDAO;
        this.pihakDAO = pihakDAO;
    }

    @Inject
    private etanah.Configuration conf;

    @Inject
    KutipanHasilManager manager;

    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;
    
    @Inject
    KodStatusTransaksiKewanganDAO kodStatusTransaksiKewanganDAO;

    @DefaultHandler
    public Resolution showForm() {
        if("04".equals(conf.getProperty("kodNegeri"))){
            kodNegeri = "melaka";
        }else{
            kodNegeri = "negeriSembilan";
        }
        LOG.info("kodNegeri : "+kodNegeri);
        return new JSP(DEFAULT_VIEW).addParameter("tab", "true");
    }

    @Before(stages={LifecycleStage.BindingAndValidation})
    public void rehydrate() {
        LOG.info("rehydrate:start");
        
        idPermohonan = (String)getContext().getRequest().getSession().getAttribute("idPermohonan");

        permohonan = permohonanDAO.findById(idPermohonan);
        String idHakmilik = "";
        String [] n1 = {"permohonan"};
        Object [] v1 = {permohonan};
        List<HakmilikPermohonan> hpList = hakmilikPermohonanDAO.findByEqualCriterias(n1, v1, null);

        LOG.info("hpList.size() : "+hpList.size());
        for (HakmilikPermohonan hp : hpList) {
            idHakmilik = hp.getHakmilik().getIdHakmilik();
        }
        LOG.info("idHakmilik : "+idHakmilik);
        hakmilik = hakmilikDAO.findById(idHakmilik);
        akaun = hakmilik.getAkaunCukai();
        if (akaun.getBaki().compareTo(new BigDecimal(0)) == -1) {
            kredit = akaun.getBaki().multiply(new BigDecimal(-1));
            fKredit = true;
        }
        cukai = hakmilik.getCukaiSebenar();
        cukaiAsal = hakmilik.getCukai().setScale(0, BigDecimal.ROUND_UP);
        List<Transaksi> trList = akaun.getSemuaTransaksi();
        for (Transaksi tr : trList) {
//            if(tr.getKodTransaksi().getKod().equals("61611")){
//                kredit = tr.getAmaun();
//                fKredit = true;
//            }
//            if(tr.getKodTransaksi().getKod().equals("61016")){
//                debit = tr.getAmaun();
//                fDebit = true;
//            }
            if(tr.getKodTransaksi().getKod().equals("76152")){
                denda = tr.getAmaun();
                fDenda = true;
            }
            if(tr.getKodTransaksi().getKod().equals("61402")){
                tunggakan = tr.getAmaun();
                fTunggakkan = true;
            }
            if(tr.getKodTransaksi().getKod().equals("72457")){
                notis = tr.getAmaun();
                fNotis = true;
            }
            if((tr.getKodTransaksi().getKod().equals("99006")) || (tr.getKodTransaksi().getKod().equals("99000"))){
                remisyen = tr.getAmaun();
                fRemisyen = true;
            }
        }
        boolean f = false;
        Session s = sessionProvider.get();
        Query q = s.createQuery("SELECT t from etanah.model.Transaksi t where t.akaunKredit.noAkaun = :noAkaun AND t.status.kod = 'T' ");
        q.setString("noAkaun", akaun.getNoAkaun());
        List<Transaksi> tList = q.list();
        LOG.info("tList.size() : "+tList.size());
        if(tList.size() > 0){
            f = true;
        }
        if(f){
            List<Transaksi> listDebit  = akaun.getSenaraiTransaksiDebit();
            List<Transaksi> listKredit = akaun.getSenaraiTransaksiKredit();

            BigDecimal totalDebit  = new BigDecimal(0.00);
            BigDecimal totalKredit = new BigDecimal(0.00);

            for (Transaksi t : listDebit) {
                totalDebit = totalDebit.add(t.getAmaun());
            }

            for (Transaksi t : listKredit) {
                totalKredit = totalKredit.add(t.getAmaun());
            }

            debit = totalDebit.subtract(totalKredit);
            fDebit = true;
        }
    }

    public Resolution save(){
        etanahActionBeanContext ctx = new etanahActionBeanContext();
        ctx = (etanahActionBeanContext) getContext();
//        Hakmilik hm = new Hakmilik();
//        Akaun ac = new Akaun();
//        Transaksi tr = new Transaksi();
        boolean flag = false;
        List<Transaksi> trList = akaun.getSemuaTransaksi();
        Pengguna pengguna = ctx.getUser();
        KodCawangan caw = pengguna.getKodCawangan();
        Date now = new Date();
        InfoAudit ia = new InfoAudit();
        int year = Integer.parseInt(yy.format(now));

        if(cukai.compareTo(hakmilik.getCukaiSebenar()) != 0){
            hakmilik.setCukaiSebenar(cukai);
            if((akaun.getBaki().doubleValue()) > 0){
                akaun.setBaki(cukai);
                flag = true;
            }
        }
        if(flag){
            for (Transaksi trans : trList) {
                if(trans.getKodTransaksi().getKod().equals("61401")){
                    trans.setAmaun(cukai);
                    manager.saveOrUpdate(trans);
                }
            }
        }
        manager.saveOrUpdate(akaun);
        manager.saveOrUpdate(hakmilik);

        if(debit.compareTo(new BigDecimal(0)) > 0){
            if(fDebit){
                for(Transaksi tr : trList){
                    if(tr.getKodTransaksi().getKod().equals("61016")){
                        tr.setAmaun(debit);
                        manager.saveOrUpdate(tr);
                    }
                }
            }else{
                Transaksi tr = new Transaksi();
                ia.setDimasukOleh(pengguna);
                ia.setTarikhMasuk(new java.util.Date());

                KodTransaksi kt = new KodTransaksi();
                kt.setKod("61016");
                tr.setKodTransaksi(kt);
                tr.setStatus(kodStatusTransaksiKewanganDAO.findById('A'));
                tr.setUntukTahun(year);
                tr.setCawangan(caw);
                tr.setAmaun(debit);
                tr.setAkaunDebit(akaun);
                tr.setInfoAudit(ia);
                manager.save(tr);
                akaun.setBaki(akaun.getBaki().add(debit));
                manager.saveOrUpdate(akaun);
            }
        }

        if(kredit.compareTo(new BigDecimal(0)) > 0){
            if(fKredit){
                for(Transaksi tr : trList){
                    if(tr.getKodTransaksi().getKod().equals("61611")){
                        tr.setAmaun(kredit);
                        manager.saveOrUpdate(tr);
                    }
                }
            }else{
                Transaksi tr = new Transaksi();
                ia.setDimasukOleh(pengguna);
                ia.setTarikhMasuk(new java.util.Date());

                KodTransaksi kt = new KodTransaksi();
                kt.setKod("61611");
                tr.setKodTransaksi(kt);
                tr.setStatus(kodStatusTransaksiKewanganDAO.findById('A'));
                tr.setUntukTahun(year);
                tr.setCawangan(caw);
                tr.setAmaun(kredit);
                tr.setAkaunKredit(akaun);
                tr.setInfoAudit(ia);
                manager.save(tr);
                akaun.setBaki(akaun.getBaki().subtract(kredit));
                manager.saveOrUpdate(akaun);
            }
        }

        if(denda.compareTo(new BigDecimal(0)) > 0){
            if(fDenda){
                for(Transaksi tr : trList){
                    if(tr.getKodTransaksi().getKod().equals("76152")){
                        tr.setAmaun(denda);
                        manager.saveOrUpdate(tr);
                    }
                }
            }else{
                Transaksi tr = new Transaksi();
                ia.setDimasukOleh(pengguna);
                ia.setTarikhMasuk(new java.util.Date());

                KodTransaksi kt = new KodTransaksi();
                kt.setKod("76152");
                tr.setKodTransaksi(kt);
                tr.setStatus(kodStatusTransaksiKewanganDAO.findById('A'));
                tr.setUntukTahun(year);
                tr.setCawangan(caw);
                tr.setAmaun(denda);
                tr.setAkaunDebit(akaun);
                tr.setInfoAudit(ia);
                manager.save(tr);
                akaun.setBaki(akaun.getBaki().add(denda));
                manager.saveOrUpdate(akaun);
            }
        }

        if(tunggakan.compareTo(new BigDecimal(0)) > 0){
            if(fTunggakkan){
                for(Transaksi tr : trList){
                    if(tr.getKodTransaksi().getKod().equals("61402")){
                        tr.setAmaun(tunggakan);
                        manager.saveOrUpdate(tr);
                    }
                }
            }else{
                Transaksi tr = new Transaksi();
                ia.setDimasukOleh(pengguna);
                ia.setTarikhMasuk(new java.util.Date());

                KodTransaksi kt = new KodTransaksi();
                kt.setKod("61402");
                tr.setKodTransaksi(kt);
                tr.setStatus(kodStatusTransaksiKewanganDAO.findById('A'));
                tr.setUntukTahun(year);
                tr.setCawangan(caw);
                tr.setAmaun(tunggakan);
                tr.setAkaunDebit(akaun);
                tr.setInfoAudit(ia);
                manager.save(tr);
                akaun.setBaki(akaun.getBaki().add(tunggakan));
                manager.saveOrUpdate(akaun);
            }
        }

        if(remisyen.compareTo(new BigDecimal(0)) > 0){
            if(fRemisyen){
                for(Transaksi tr : trList){
                    if(tr.getKodTransaksi().getKod().equals("99006")){
                        tr.setAmaun(remisyen);
                        manager.saveOrUpdate(tr);
                    }
                }
            }else{
                Transaksi tr = new Transaksi();
                ia.setDimasukOleh(pengguna);
                ia.setTarikhMasuk(new java.util.Date());

                KodTransaksi kt = new KodTransaksi();
                kt.setKod("99006");
                tr.setKodTransaksi(kt);
                tr.setStatus(kodStatusTransaksiKewanganDAO.findById('A'));
                tr.setUntukTahun(year);
                tr.setCawangan(caw);
                tr.setAmaun(remisyen);
                tr.setAkaunKredit(akaun);
                tr.setInfoAudit(ia);
                manager.save(tr);
                akaun.setBaki(akaun.getBaki().subtract(remisyen));
                manager.saveOrUpdate(akaun);
            }
        }

        if(notis.compareTo(new BigDecimal(0)) > 0){
            if(fNotis){
                for(Transaksi tr : trList){
                    if(tr.getKodTransaksi().getKod().equals("72457")){
                        tr.setAmaun(notis);
                        manager.saveOrUpdate(tr);
                    }
                }
            }else{
                Transaksi tr = new Transaksi();
                ia.setDimasukOleh(pengguna);
                ia.setTarikhMasuk(new java.util.Date());

                KodTransaksi kt = new KodTransaksi();
                kt.setKod("72457");
                tr.setKodTransaksi(kt);
                tr.setStatus(kodStatusTransaksiKewanganDAO.findById('A'));
                tr.setUntukTahun(year);
                tr.setCawangan(caw);
                tr.setAmaun(notis);
                tr.setAkaunDebit(akaun);
                tr.setInfoAudit(ia);
                manager.save(tr);
                akaun.setBaki(akaun.getBaki().add(notis));
                manager.saveOrUpdate(akaun);
            }
        }

        addSimpleMessage("Maklumat Telah Berjaya Disimpan");
        return new JSP(DEFAULT_VIEW).addParameter("tab", "true");
    }

    public String getKodNegeri() {
        return kodNegeri;
    }

    public void setKodNegeri(String kodNegeri) {
        this.kodNegeri = kodNegeri;
    }

    public Akaun getAkaun() {
        return akaun;
    }

    public void setAkaun(Akaun akaun) {
        this.akaun = akaun;
    }

    public Hakmilik getHakmilik() {
        return hakmilik;
    }

    public void setHakmilik(Hakmilik hakmilik) {
        this.hakmilik = hakmilik;
    }

    public Permohonan getPermohonan() {
        return permohonan;
    }

    public void setPermohonan(Permohonan permohonan) {
        this.permohonan = permohonan;
    }

    public HakmilikPermohonan getHakmilikPermohonan() {
        return hakmilikPermohonan;
    }

    public void setHakmilikPermohonan(HakmilikPermohonan hakmilikPermohonan) {
        this.hakmilikPermohonan = hakmilikPermohonan;
    }

    public String getIdPermohonan() {
        return idPermohonan;
    }

    public void setIdPermohonan(String idPermohonan) {
        this.idPermohonan = idPermohonan;
    }

    public Pihak getPihak() {
        return pihak;
    }

    public void setPihak(Pihak pihak) {
        this.pihak = pihak;
    }

    public BigDecimal getDebit() {
        return debit;
    }

    public void setDebit(BigDecimal debit) {
        this.debit = debit;
    }

    public BigDecimal getDenda() {
        return denda;
    }

    public void setDenda(BigDecimal denda) {
        this.denda = denda;
    }

    public BigDecimal getKredit() {
        return kredit;
    }

    public void setKredit(BigDecimal kredit) {
        this.kredit = kredit;
    }

    public BigDecimal getNotis() {
        return notis;
    }

    public void setNotis(BigDecimal notis) {
        this.notis = notis;
    }

    public BigDecimal getRemisyen() {
        return remisyen;
    }

    public void setRemisyen(BigDecimal remisyen) {
        this.remisyen = remisyen;
    }

    public BigDecimal getTunggakan() {
        return tunggakan;
    }

    public void setTunggakan(BigDecimal tunggakan) {
        this.tunggakan = tunggakan;
    }

    public BigDecimal getCukai() {
        return cukai;
    }

    public void setCukai(BigDecimal cukai) {
        this.cukai = cukai;
    }

    public BigDecimal getCukaiAsal() {
        return cukaiAsal;
    }

    public void setCukaiAsal(BigDecimal cukaiAsal) {
        this.cukaiAsal = cukaiAsal;
    }

    public boolean isfDebit() {
        return fDebit;
    }

    public void setfDebit(boolean fDebit) {
        this.fDebit = fDebit;
    }

    public boolean isfDenda() {
        return fDenda;
    }

    public void setfDenda(boolean fDenda) {
        this.fDenda = fDenda;
    }

    public boolean isfKredit() {
        return fKredit;
    }

    public void setfKredit(boolean fKredit) {
        this.fKredit = fKredit;
    }

    public boolean isfNotis() {
        return fNotis;
    }

    public void setfNotis(boolean fNotis) {
        this.fNotis = fNotis;
    }

    public boolean isfRemisyen() {
        return fRemisyen;
    }

    public void setfRemisyen(boolean fRemisyen) {
        this.fRemisyen = fRemisyen;
    }

    public boolean isfTunggakkan() {
        return fTunggakkan;
    }

    public void setfTunggakkan(boolean fTunggakkan) {
        this.fTunggakkan = fTunggakkan;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

}
