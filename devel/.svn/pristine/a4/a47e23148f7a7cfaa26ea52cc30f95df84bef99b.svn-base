/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.stripes.hasil;

import java.util.*;
import etanah.dao.*;
import etanah.model.*;
import able.stripes.*;
import org.hibernate.*;
import java.math.BigDecimal;
import org.apache.log4j.Logger;
import com.google.inject.Inject;
import etanah.report.ReportUtil;
import java.text.SimpleDateFormat;
import net.sourceforge.stripes.action.*;
import etanah.service.LaporanTanahService;
import etanah.view.etanahActionBeanContext;
import net.sourceforge.stripes.controller.LifecycleStage;

@UrlBinding("/hasil/laporan_tanah")
public class LporanTanah6AActionBean extends AbleActionBean {

    private static final Logger LOG = Logger.getLogger(LporanTanah6AActionBean.class);
    private static final boolean IS_LOG_DEBUG = LOG.isDebugEnabled();
    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;
    @Inject
    HakmilikDAO hakmilikDAO;
    private Hakmilik hakmilik;
    @Inject
    DasarTuntutanCukaiHakmilikDAO dasarTuntutanCukaiHakmilikDAO;
    @Inject
    KutipanHasilManager manager;
    @Inject
    KodKeadaanTanahDAO kodKeadaanTanahDAO;
    @Inject
    PermohonanDAO permohonanDAO;
    Permohonan permohonan;
    @Inject
    ReportUtil reportUtil;
    @Inject
    etanah.Configuration conf;
    @Inject
    LaporanTanahService laporanTanahService;
    private List<DasarTuntutanCukaiHakmilik> senaraiDasarTuntutanCukaiHakmilik;
    private List<HakmilikPermohonan> senaraiHakmilikPermohonan;
    private boolean visible = false;
    private String noDasar;
    private Integer tempohTahun;
    private String kodStatus;
    private static String idHakmilik;
    private String id;
    private String now;
    private String negeri;
    private String lokasiTanah;
    private String keadaanTanah;
    private String bangunan;
    private String pemilikBangunan;
    private String keadaanBangunan;
    private String ulasan;
    private BigDecimal tunggakanCukai = new BigDecimal(0.00);
    private BigDecimal tunggakanDenda = new BigDecimal(0.00);
    private BigDecimal dendaSemasa = new BigDecimal(0.00);
    private int tunggakanTahun;
    private boolean flag = false;
    private boolean flag1 = false;
    private boolean btn = false;
    private PermohonanRujukanLuar permohonanRujukanLuar;
    private LaporanTanah laporanTanah;
    private FasaPermohonan fasaPermohonan;
    private DasarTuntutanCukai dasarTuntutanCukai;
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy");

    @DefaultHandler
    public Resolution showForm() {
        if ("04".equals(conf.getProperty("kodNegeri"))) {
            negeri = "melaka";
        }

//        String[] name = {"hakmilik"};
//        Object[] value = {getIdHakmilik()};
//
//                        Object [] tvalue={hp.getHakmilik()};
//        List<DasarTuntutanCukaiHakmilik> senaraiDasarTuntutanCukaiHakmilik = dasarTuntutanCukaiHakmilikDAO.findByEqualCriterias(name, value, null);
//
//
//       noDasar = senaraiDasarTuntutanCukaiHakmilik.get(0).getDasarTuntutanCukai().getIdDasar();
//      //noDasar = senaraiDasarHakmilik.get(0).getDasarTuntutanCukai().getIdDasar();
//        LOG.info("noDasar1:"+noDasar);
        return new JSP("hasil/laporan_6A.jsp").addParameter("tab", "true");
    }

    public Resolution showForm2() {
        setFlag(true);
        if ("04".equals(conf.getProperty("kodNegeri"))) {
            negeri = "melaka";
        }
        return new JSP("hasil/laporan_6A.jsp").addParameter("tab", "true");
    }

    //added by tulasi
    public Resolution showForm3() {
        setFlag(true);
        if ("04".equals(conf.getProperty("kodNegeri"))) {
            negeri = "melaka";
        }
        return new JSP("hasil/arahan_notis_6a.jsp").addParameter("tab", "true");
    }
     //ended

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {
        LOG.info("rehydrate:start");
        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        LOG.debug("idPermohonan :" + idPermohonan);

        if (idPermohonan != null) {
            permohonan = permohonanDAO.findById(idPermohonan);
            senaraiHakmilikPermohonan = new ArrayList<HakmilikPermohonan>();
            try {
                List<HakmilikPermohonan> senaraiHakmilikPermohonanAll = permohonan.getSenaraiHakmilik();
                for (HakmilikPermohonan hp : senaraiHakmilikPermohonanAll) {
                    if (hp.getHakmilik().getAkaunCukai().getBaki().intValue() > 0) {
                        senaraiHakmilikPermohonan.add(hp);
                        LOG.info("idHakmilik :"+hp.getHakmilik().getIdHakmilik());
                        String [] tname ={"hakmilik"};
                        Object [] tvalue={hp.getHakmilik()};
                        List<DasarTuntutanCukaiHakmilik> senaraiDasarHakmilik = dasarTuntutanCukaiHakmilikDAO.findByEqualCriterias(tname, tvalue, null);
                        noDasar = senaraiDasarHakmilik.get(0).getDasarTuntutanCukai().getIdDasar();
                        LOG.info("noDasar :"+noDasar);
                    }
                }
                LOG.debug("senaraiHakmilikPermohonan :" + senaraiHakmilikPermohonan.size());
            } catch (Exception ex) {
                LOG.error("rehydrate ex: " + ex);
            }
        }
        now = sdf.format(new java.util.Date());
        LOG.info("rehydrate:finish");
    }
    
    public Resolution simpanLaporanTanah(){
        LOG.info("INSIDE simpanLaporanTanah");
        LaporanTanah laporTanah = new LaporanTanah();
        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = permohonanDAO.findById(idPermohonan);
        etanahActionBeanContext ctx = new etanahActionBeanContext();
        ctx = (etanahActionBeanContext) getContext();

        Pengguna pengguna = ctx.getUser(); //penggunaDAO.findById("admin");
        InfoAudit ia = new InfoAudit();
        
        String queryTanah = "SELECT lt FROM etanah.model.LaporanTanah lt WHERE lt.permohonan.idPermohonan = :idMohon";
        Query qTanah = sessionProvider.get().createQuery(queryTanah);
        qTanah.setString("idMohon", idPermohonan);
        laporanTanah = (LaporanTanah) qTanah.uniqueResult();
        
        try{
            if(laporanTanah !=null){
                laporanTanah.setKedudukanTanah(lokasiTanah);
                laporanTanah.setKodKeadaanTanah(kodKeadaanTanahDAO.findById(keadaanTanah));
                laporanTanah.setLainLainBangunan(bangunan);
                laporanTanah.setDiusaha(pemilikBangunan);
                laporanTanah.setJenisBangunan(keadaanBangunan);
                laporanTanah.setUlasan(ulasan);
                    ia.setDimasukOleh(laporanTanah.getInfoAudit().getDimasukOleh());
                    ia.setTarikhMasuk(laporanTanah.getInfoAudit().getTarikhMasuk());
                    ia.setDiKemaskiniOleh(pengguna);
                    ia.setTarikhKemaskini(new java.util.Date());
                laporanTanah.setInfoAudit(ia);
                
                manager.update(laporanTanah);
                
            }else{
                laporTanah.setPermohonan(permohonan);
                laporTanah.setKedudukanTanah(lokasiTanah);
                laporTanah.setKodKeadaanTanah(kodKeadaanTanahDAO.findById(keadaanTanah));
                laporTanah.setLainLainBangunan(bangunan);
                laporTanah.setDiusaha(pemilikBangunan);
                laporTanah.setJenisBangunan(keadaanBangunan);
                laporTanah.setUlasan(ulasan);
                    ia.setDimasukOleh(pengguna);
                    ia.setTarikhMasuk(new java.util.Date());
                laporTanah.setInfoAudit(ia);
                
                manager.save(laporTanah);
            }
            hakmilik = hakmilikDAO.findById(idHakmilik);
            String tahun = sdf.format(new java.util.Date());
            LOG.info("tahun : "+tahun);
            int thn = Integer.parseInt(tahun);

            String query = "SELECT a FROM etanah.model.Akaun a WHERE a.hakmilik.idHakmilik = :id";
            Query q = sessionProvider.get().createQuery(query);
            q.setString("id", hakmilik.getIdHakmilik());
            Akaun a = (Akaun) q.uniqueResult();

            List<Transaksi> transaksiDebit = a.getSenaraiTransaksiDebit();
            LOG.info("transaksiDebit.size() : "+transaksiDebit.size());
            for (Transaksi t : transaksiDebit) {
                if(t.getKodTransaksi().getKod().equals("61402")){
                    tunggakanCukai = tunggakanCukai.add(t.getAmaun());
                }
                if((t.getKodTransaksi().getKod().equals("76152"))&&(Integer.toString(t.getUntukTahun()).equals(tahun))){
                    dendaSemasa = t.getAmaun();
                }
                if((t.getKodTransaksi().getKod().equals("76152"))&&(!Integer.toString(t.getUntukTahun()).equals(tahun))){
                    tunggakanDenda = tunggakanDenda.add(t.getAmaun());
                }
                if(t.getUntukTahun() < thn){
                    tunggakanTahun = t.getUntukTahun();
                    thn = t.getUntukTahun();
                }
            }
            addSimpleMessage("Maklumat telah berjaya disimpan.");
        }
        catch(Exception ex) {
            LOG.error(ex.getMessage(), ex);
            addSimpleError("Maklumat tidak berjaya disimpan.");
        }
        return new JSP("hasil/laporan_tanah.jsp").addParameter("tab", "true");
    }
    
    public Resolution sediaLaporanTanah() {
        String idhm = (String) getContext().getRequest().getParameter("idHakmilik");
        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        hakmilik = hakmilikDAO.findById(idhm);

        String tahun = sdf.format(new java.util.Date());
        LOG.info("tahun : "+tahun);
        int thn = Integer.parseInt(tahun);
        
        String query = "SELECT a FROM etanah.model.Akaun a WHERE a.hakmilik.idHakmilik = :id";
        Query q = sessionProvider.get().createQuery(query);
        q.setString("id", hakmilik.getIdHakmilik());
        Akaun a = (Akaun) q.uniqueResult();
        
        List<Transaksi> transaksiDebit = a.getSenaraiTransaksiDebit();
        LOG.info("transaksiDebit.size() : "+transaksiDebit.size());
        for (Transaksi t : transaksiDebit) {
            if(t.getKodTransaksi().getKod().equals("61402")){
                tunggakanCukai = tunggakanCukai.add(t.getAmaun());
            }
            if((t.getKodTransaksi().getKod().equals("76152"))&&(Integer.toString(t.getUntukTahun()).equals(tahun))){
                dendaSemasa = t.getAmaun();
            }
            if((t.getKodTransaksi().getKod().equals("76152"))&&(!Integer.toString(t.getUntukTahun()).equals(tahun))){
                tunggakanDenda = tunggakanDenda.add(t.getAmaun());
            }
            if(t.getUntukTahun() < thn){
                tunggakanTahun = t.getUntukTahun();
                thn = t.getUntukTahun();
            }
        }
        
        String queryTanah = "SELECT lt FROM etanah.model.LaporanTanah lt WHERE lt.permohonan.idPermohonan = :idMohon";
        Query qTanah = sessionProvider.get().createQuery(queryTanah);
        qTanah.setString("idMohon", idPermohonan);
        laporanTanah = (LaporanTanah) qTanah.uniqueResult();
        if(laporanTanah != null){
            lokasiTanah = laporanTanah.getKedudukanTanah();
            keadaanTanah = laporanTanah.getKodKeadaanTanah().getKod();
            bangunan = laporanTanah.getLainLainBangunan();
            pemilikBangunan = laporanTanah.getDiusaha();
            keadaanBangunan = laporanTanah.getJenisBangunan();
            ulasan = laporanTanah.getUlasan();            
        }
        
        return new JSP("hasil/laporan_tanah.jsp").addParameter("popup", "true");
    }


    public Resolution laporanTanah() {
        hakmilik = hakmilikDAO.findById(idHakmilik);
        return new JSP("hasil/hakmilik_details.jsp").addParameter("popup", "true");
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public Integer getTempohTahun() {
        return tempohTahun;
    }

    public void setTempohTahun(Integer tempohTahun) {
        this.tempohTahun = tempohTahun;
    }

    public String getKodStatus() {
        return kodStatus;
    }

    public void setKodStatus(String kodStatus) {
        this.kodStatus = kodStatus;
    }

    public String getNoDasar() {
        return noDasar;
    }

    public void setNoDasar(String noDasar) {
        this.noDasar = noDasar;
    }

    public List<DasarTuntutanCukaiHakmilik> getSenaraiDasarTuntutanCukaiHakmilik() {
        return senaraiDasarTuntutanCukaiHakmilik;
    }

    public void setSenaraiDasarTuntutanCukaiHakmilik(List<DasarTuntutanCukaiHakmilik> senaraiDasarTuntutanCukaiHakmilik) {
        this.senaraiDasarTuntutanCukaiHakmilik = senaraiDasarTuntutanCukaiHakmilik;
    }

    public Hakmilik getHakmilik() {
        return hakmilik;
    }

    public void setHakmilik(Hakmilik hakmilik) {
        this.hakmilik = hakmilik;
    }

    public String getIdHakmilik() {
        return idHakmilik;
    }

    public void setIdHakmilik(String idHakmilik) {
        this.idHakmilik = idHakmilik;
    }

    public Permohonan getPermohonan() {
        return permohonan;
    }

    public void setPermohonan(Permohonan permohonan) {
        this.permohonan = permohonan;
    }

    public List<HakmilikPermohonan> getSenaraiHakmilikPermohonan() {
        return senaraiHakmilikPermohonan;
    }

    public void setSenaraiHakmilikPermohonan(List<HakmilikPermohonan> senaraiHakmilikPermohonan) {
        this.senaraiHakmilikPermohonan = senaraiHakmilikPermohonan;
    }

    public String getNow() {
        return now;
    }

    public void setNow(String now) {
        this.now = now;
    }

    /**
     * @return the flag
     */
    public boolean isFlag() {
        return flag;
    }

    /**
     * @param flag the flag to set
     */
    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    /**
     * @return the flag1
     */
    public boolean isFlag1() {
        return flag1;
    }

    /**
     * @param flag1 the flag1 to set
     */
    public void setFlag1(boolean flag1) {
        this.flag1 = flag1;
    }

    public String getNegeri() {
        return negeri;
    }

    public void setNegeri(String negeri) {
        this.negeri = negeri;
    }

    public PermohonanRujukanLuar getPermohonanRujukanLuar() {
        return permohonanRujukanLuar;
    }

    public void setPermohonanRujukanLuar(PermohonanRujukanLuar permohonanRujukanLuar) {
        this.permohonanRujukanLuar = permohonanRujukanLuar;
    }

    public LaporanTanah getLaporanTanah() {
        return laporanTanah;
    }

    public void setLaporanTanah(LaporanTanah laporanTanah) {
        this.laporanTanah = laporanTanah;
    }

    public FasaPermohonan getFasaPermohonan() {
        return fasaPermohonan;
    }

    public void setFasaPermohonan(FasaPermohonan fasaPermohonan) {
        this.fasaPermohonan = fasaPermohonan;
    }

    /**
     * @return the idMohonHakmilik
     */
    public String getId() {
        return id;
    }

    /**
     * @param idMohonHakmilik the idMohonHakmilik to set
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * @return the dasarTuntutanCukai
     */
    public DasarTuntutanCukai getDasarTuntutanCukai() {
        return dasarTuntutanCukai;
    }

    /**
     * @param dasarTuntutanCukai the dasarTuntutanCukai to set
     */
    public void setDasarTuntutanCukai(DasarTuntutanCukai dasarTuntutanCukai) {
        this.dasarTuntutanCukai = dasarTuntutanCukai;
    }

    /**
     * @return the btn
     */
    public boolean isBtn() {
        return btn;
    }

    /**
     * @param btn the btn to set
     */
    public void setBtn(boolean btn) {
        this.btn = btn;
    }

    public String getLokasiTanah() {
        return lokasiTanah;
    }

    public void setLokasiTanah(String lokasiTanah) {
        this.lokasiTanah = lokasiTanah;
    }

    public String getKeadaanTanah() {
        return keadaanTanah;
    }

    public void setKeadaanTanah(String keadaanTanah) {
        this.keadaanTanah = keadaanTanah;
    }

    public String getBangunan() {
        return bangunan;
    }

    public void setBangunan(String bangunan) {
        this.bangunan = bangunan;
    }

    public String getPemilikBangunan() {
        return pemilikBangunan;
    }

    public void setPemilikBangunan(String pemilikBangunan) {
        this.pemilikBangunan = pemilikBangunan;
    }

    public String getKeadaanBangunan() {
        return keadaanBangunan;
    }

    public void setKeadaanBangunan(String keadaanBangunan) {
        this.keadaanBangunan = keadaanBangunan;
    }

    public String getUlasan() {
        return ulasan;
    }

    public void setUlasan(String ulasan) {
        this.ulasan = ulasan;
    }

    public BigDecimal getTunggakanCukai() {
        return tunggakanCukai;
    }

    public void setTunggakanCukai(BigDecimal tunggakanCukai) {
        this.tunggakanCukai = tunggakanCukai;
    }

    public int getTunggakanTahun() {
        return tunggakanTahun;
    }

    public void setTunggakanTahun(int tunggakanTahun) {
        this.tunggakanTahun = tunggakanTahun;
    }

    public BigDecimal getTunggakanDenda() {
        return tunggakanDenda;
    }

    public void setTunggakanDenda(BigDecimal tunggakanDenda) {
        this.tunggakanDenda = tunggakanDenda;
    }

    public BigDecimal getDendaSemasa() {
        return dendaSemasa;
    }

    public void setDendaSemasa(BigDecimal dendaSemasa) {
        this.dendaSemasa = dendaSemasa;
    }
}
