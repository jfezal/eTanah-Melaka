/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package etanah.view.stripes.pengambilan;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.dao.PermohonanKertasDAO;
import etanah.dao.PermohonanKertasKandunganDAO;
import etanah.dao.HakmilikDAO;
import etanah.dao.KodUOMDAO;
import etanah.dao.PemohonDAO;
import etanah.dao.PermohonanDAO;
import etanah.dao.LaporanTanahDAO;
import etanah.model.PermohonanKertasKandungan;
import etanah.model.Hakmilik;
import etanah.model.HakmilikPermohonan;
import etanah.model.HakmilikPihakBerkepentingan;
import etanah.model.InfoAudit;
import etanah.model.KodUOM;
import etanah.model.Pemohon;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.model.PermohonanPihak;
import etanah.model.Pihak;
import etanah.model.PihakPengarah;
import etanah.model.LaporanTanah;
import etanah.service.CalcTax;
import etanah.service.ConsentPtdService;
import etanah.service.PembangunanService;
import etanah.view.etanahActionBeanContext;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;
import org.hibernate.Session;

@UrlBinding("/pengambilan/SemakanHakmilik")
public class SemakanHakmilikActionBean extends AbleActionBean{

    private static Logger logger = Logger.getLogger(SemakanHakmilikActionBean.class);
    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;
    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    HakmilikDAO hakmilikDAO;
    @Inject
    PemohonDAO pemohonDAO;
    @Inject
    CalcTax calcTax;
    @Inject
    KodUOMDAO kodUOMDAO;
    @Inject
    KodUOM KodOUM;
    @Inject
    ConsentPtdService conService;
    @Inject
    PembangunanService devService;
    @Inject
    LaporanTanahDAO laporanTanahDAO;
    @Inject
    PermohonanKertasDAO permohonanKertasDAO;
    @Inject
    PermohonanKertasKandunganDAO permohonanKertasKandunganDAO;
    private Permohonan permohonan;
    private Hakmilik hakmilik;
    private Pemohon pemohon;
    private PermohonanPihak penerima;
    private Permohonan permohonanSebelum;
    private HakmilikPihakBerkepentingan hakmilikPihakBerkepentingan;
    private PermohonanKertasKandungan kertasK;
    private LaporanTanah laporanTanah;
    private String stageId;
    private Pengguna peng;
    private BigDecimal convLuas;
    private BigDecimal amount = new BigDecimal(0);
    private BigDecimal totalLuas = new BigDecimal(0);
    private BigDecimal amountMH = new BigDecimal(0);
    private BigDecimal convH = new BigDecimal(0);
    String latarBelakang;
    String huraianPentadbir;
    String syorPentadbir;
    String huraianPtg;
    String syorPtg;
    String tarikhDaftar;
    String tarikhMesyuarat;
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

    private String tarikhPermohonan;
    private String kedudukanTnh;
    private String keadaanTnh;
    private String jenisTanaman;
    private String bilBangunan;
    private String tujuan;
    private String pembangunanDicadang;
    private List<PihakPengarah> listPengarah;
    private Pihak pihak;
    private List<Pemohon> listPemohon;
    private List<HakmilikPermohonan> hakmilikPermohonanList;
    private List<String> luasTerlibat = new ArrayList<String>();
    private List<String> kodUnitLuas = new ArrayList<String>();
    private boolean btn = true;
    private String lokasi;
    private String kmno;

    public String getKmno() {
        return kmno;
    }

    public void setKmno(String kmno) {
        this.kmno = kmno;
    }


    @DefaultHandler
    public Resolution showForm() {
        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = permohonanDAO.findById(idPermohonan);

       String idSblm = permohonan.getPermohonanSebelum().getIdPermohonan();

        if (idSblm != null && idPermohonan!=null) {
            idSblm = permohonan.getPermohonanSebelum().getIdPermohonan();
            permohonanSebelum = permohonanDAO.findById(idSblm);
        }
        return new JSP("pengambilan/semakanHakmilik.jsp").addParameter("tab", "true");
    }

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {
        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        System.out.println("----idsblm---------------"+getContext().getRequest().getSession().getAttribute("idSblm"));

        permohonan = permohonanDAO.findById(idPermohonan);
        String idSblm = permohonan.getPermohonanSebelum().getIdPermohonan();

        if(idSblm!=null){
        Permohonan p= permohonanDAO.findById(idSblm);
        HakmilikPermohonan hp = new HakmilikPermohonan();

        hakmilikPermohonanList = p.getSenaraiHakmilik();

        logger.debug(p + " permohonan");

        peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        String[] tname = {"permohonan"};
        Object[] model = {p};
        
        listPemohon = pemohonDAO.findByEqualCriterias(tname, model, null);

        if(pemohon == null){
            pemohon = new Pemohon();
        }
        for (Pemohon pemohon : listPemohon) {
            listPengarah = pemohon.getPihak().getSenaraiPengarah();
        }

        if (!(listPemohon.isEmpty())) {
            pemohon = listPemohon.get(0);
        }

        List<PermohonanPihak> listPenerima;
        listPenerima = p.getSenaraiPihak();

        if (!(listPenerima.isEmpty())) {
            penerima = listPenerima.get(0);
        }

        for (int w = 0; w < hakmilikPermohonanList.size(); w++){
            hp = p.getSenaraiHakmilik().get(w);
            logger.debug(p + " permohonan");
            hakmilik = hakmilikDAO.findById(hp.getHakmilik().getIdHakmilik());

//            if (w == 0){
//                lokasi = hakmilik.getLot().getNama() +" " + hakmilik.getNoLot() + ", " + hakmilik.getBandarPekanMukim().getNama() + ", Daerah " + hakmilik.getDaerah().getNama() + ", ";
//            }
//
//            if(w > 0 ){
//                if(w <= ((hakmilikPermohonanList.size() + 2) - 4)){
//                    lokasi = lokasi + hakmilik.getLot().getNama() +" " + hakmilik.getNoLot() + ", " + hakmilik.getBandarPekanMukim().getNama() + ", Daerah " + hakmilik.getDaerah().getNama() + ", ";
//                } else if(w == (hakmilikPermohonanList.size() - 1)){
//                    lokasi = lokasi + " dan " + hakmilik.getLot().getNama() +" " + hakmilik.getNoLot() + ", " + hakmilik.getBandarPekanMukim().getNama() + ", Daerah " + hakmilik.getDaerah().getNama();
//                    }
//                 }

                 try{
                 BigDecimal luas = hakmilikPermohonanList.get(w).getLuasTerlibat();
                 String name = hakmilikPermohonanList.get(w).getKodUnitLuas().getKod();
                 if(hakmilikPermohonanList.get(w).getLuasTerlibat() == null){
                     luasTerlibat.add("");
                 }else{
                 luasTerlibat.add(luas.toString());
                 }
                if(hakmilikPermohonanList.get(w).getLuasTerlibat()!= null)
                {
                    if(name.equals("H"))
                      {
                           System.out.println("Hektar");
                           System.out.println(luasTerlibat.get(w));
                           BigDecimal luasHektar=new BigDecimal(luasTerlibat.get(w));
                           convLuas = calcTax.toMeter(name,luasHektar);
                           amount= amount.add(convLuas);

                      }
                    if(name.equals("M"))
                    {
                        System.out.println("Meter Persegi");
                        System.out.println(luasTerlibat.get(w));
                        totalLuas=totalLuas.add(new BigDecimal(luasTerlibat.get(w)));
                    }

                    amountMH=totalLuas.add(amount);
                    convH = calcTax.toHectare("M",amountMH);
                    System.out.println(convH);
                }

            }catch(Exception e){

            }

            try{
                String name = hakmilikPermohonanList.get(w).getKodUnitLuas().getKod();
                kodUnitLuas.add(name);
            }catch(Exception e){
                kodUnitLuas.add("");
            }

            }
        }if (permohonan == null) {
            addSimpleError("Id Permohonan Tidak wujud");
            return;
        }
    }
   
    public Hakmilik getHakmilik() {
        return hakmilik;
    }

    public void setHakmilik(Hakmilik hakmilik) {
        this.hakmilik = hakmilik;
    }

    public Pemohon getPemohon() {
        return pemohon;
    }

    public void setPemohon(Pemohon pemohon) {
        this.pemohon = pemohon;
    }

    public String getTarikhDaftar() {
        return tarikhDaftar;
    }

    public void setTarikhDaftar(String tarikhDaftar) {
        this.tarikhDaftar = tarikhDaftar;
    }

    public String getTarikhMesyuarat() {
        return tarikhMesyuarat;
    }

    public void setTarikhMesyuarat(String tarikhMesyuarat) {
        this.tarikhMesyuarat = tarikhMesyuarat;
    }

    public HakmilikPihakBerkepentingan getHakmilikPihakBerkepentingan() {
        return hakmilikPihakBerkepentingan;
    }

    public void setHakmilikPihakBerkepentingan(HakmilikPihakBerkepentingan hakmilikPihakBerkepentingan) {
        this.hakmilikPihakBerkepentingan = hakmilikPihakBerkepentingan;
    }

    public PermohonanPihak getPenerima() {
        return penerima;
    }

    public void setPenerima(PermohonanPihak penerima) {
        this.penerima = penerima;
    }

    public String getStageId() {
        return stageId;
    }

    public void setStageId(String stageId) {
        this.stageId = stageId;
    }

    public String getTarikhPermohonan(){
        return tarikhPermohonan;
    }

    public void setTarikhPermohonan(String tarikhPermohonan){
        this.tarikhPermohonan = tarikhPermohonan;
    }

    public String getKedudukanTnh(){
        return kedudukanTnh;
    }

    public void setKedudukanTnh(String kedudukanTnh){
        this.kedudukanTnh = kedudukanTnh;
    }

    public String getKeadaanTnh(){
        return keadaanTnh;
    }

    public void setKeadaanTnh(String keadaanTnh){
        this.keadaanTnh = keadaanTnh;
    }

    public String getJenisTanaman(){
        return jenisTanaman;
    }

    public void setJenisTanaman(String jenisTanaman){
        this.jenisTanaman = jenisTanaman;
    }

    public String getBilBangunan(){
        return bilBangunan;
    }

    public void setBilBangunan(String bilBangunan){
        this.bilBangunan = bilBangunan;
    }

    public Pihak getPihak() {
        return pihak;
    }

    public void setPihak(Pihak pihak) {
        this.pihak = pihak;
    }

    public List<PihakPengarah> getListPengarah() {
        return listPengarah;
    }
 
    public void setPh(List<PihakPengarah> listPengarah) {
        this.listPengarah = listPengarah;
    }
 
    public List<Pemohon> getListPemohon() {
        return listPemohon;
    }
 
    public void setListPmohon(List<Pemohon> listPemohon) {
        this.listPemohon = listPemohon;
    }

    public String getTujuan() {
        return tujuan;
    }

    public void setTujuan(String tujuan) {
        this.tujuan = tujuan;
    }

    public LaporanTanah getLaporanTanah() {
        return laporanTanah;
    }

    public void setLaporanTanah(LaporanTanah laporanTanah) {
        this.laporanTanah = laporanTanah;
    }

     public List<HakmilikPermohonan> getHakmilikPermohonanList() {
        return hakmilikPermohonanList;
    }

    public void setHakmilikPermohonanList(List<HakmilikPermohonan> hakmilikPermohonanList) {
        this.hakmilikPermohonanList = hakmilikPermohonanList;
    }
    
    public Permohonan getPermohonan() {
        return permohonan;
    }

    public void setPermohonan(Permohonan permohonan) {
        this.permohonan = permohonan;
    }

    public boolean isBtn() {
        return btn;
    }

    public void setBtn(boolean btn) {
        this.btn = btn;
    }

    public Pengguna getPeng() {
        return peng;
    }

    public void setPeng(Pengguna peng) {
        this.peng = peng;
    }

    public String getLokasi() {
        return lokasi;
    }

    public void setLokasi(String lokasi) {
        this.lokasi = lokasi;
    }

    public Permohonan getPermohonanSebelum() {
        return permohonanSebelum;
    }

    public void setPermohonanSebelum(Permohonan permohonanSebelum) {
        this.permohonanSebelum = permohonanSebelum;
    }

    public KodUOM getKodOUM() {
        return KodOUM;
    }

    public void setKodOUM(KodUOM KodOUM) {
        this.KodOUM = KodOUM;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public BigDecimal getAmountMH() {
        return amountMH;
    }

    public void setAmountMH(BigDecimal amountMH) {
        this.amountMH = amountMH;
    }

    public CalcTax getCalcTax() {
        return calcTax;
    }

    public void setCalcTax(CalcTax calcTax) {
        this.calcTax = calcTax;
    }

    public BigDecimal getConvH() {
        return convH;
    }

    public void setConvH(BigDecimal convH) {
        this.convH = convH;
    }

    public BigDecimal getConvLuas() {
        return convLuas;
    }

    public void setConvLuas(BigDecimal convLuas) {
        this.convLuas = convLuas;
    }

    public List<String> getKodUnitLuas() {
        return kodUnitLuas;
    }

    public void setKodUnitLuas(List<String> kodUnitLuas) {
        this.kodUnitLuas = kodUnitLuas;
    }

    public List<String> getLuasTerlibat() {
        return luasTerlibat;
    }

    public void setLuasTerlibat(List<String> luasTerlibat) {
        this.luasTerlibat = luasTerlibat;
    }

    public BigDecimal getTotalLuas() {
        return totalLuas;
    }

    public void setTotalLuas(BigDecimal totalLuas) {
        this.totalLuas = totalLuas;
    }

    
}
