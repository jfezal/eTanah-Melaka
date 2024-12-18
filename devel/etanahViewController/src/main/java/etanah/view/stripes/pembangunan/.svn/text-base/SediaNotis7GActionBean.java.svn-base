package etanah.view.stripes.pembangunan;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.dao.FasaPermohonanDAO;
import etanah.dao.PermohonanDAO;
import etanah.dao.PermohonanTuntutanKosDAO;
import etanah.model.FasaPermohonan;
import etanah.model.Permohonan;
import etanah.model.PermohonanTuntutanKos;
import etanah.model.Pengguna;
import etanah.model.KodCawangan;
import etanah.model.InfoAudit;
import java.math.BigDecimal;
import etanah.view.etanahActionBeanContext;
import etanah.service.PembangunanService;
//import java.util.ArrayList;
import java.util.List;
//import java.util.logging.Level;
//import java.util.logging.Logger;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;

/**
 *
 * @author nursyazwani
 */
@UrlBinding("/pembangunan/sediaNotis7G")
public class SediaNotis7GActionBean extends AbleActionBean {

    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    FasaPermohonanDAO fasaPermohonanDAO;
    @Inject
    PermohonanTuntutanKosDAO permohonanTuntutanKosDAO;
    @Inject
    PembangunanService pembangunanServ;
    private Permohonan permohonan;
    private FasaPermohonan fasaMohon;
    private PermohonanTuntutanKos permohonantuntutkos;
    private String negeri;
    @Inject
    etanah.Configuration conf;
    private Pengguna peng;
    private BigDecimal premium;
    private BigDecimal upah;
    private BigDecimal kos;
    private BigDecimal daftar;
    private BigDecimal sewa;
    private BigDecimal notis;
    private BigDecimal jumlah;
    private String item;
    private String perakuan;
    private List<PermohonanTuntutanKos> listtuntutankos;
    private String ulasan;


    @DefaultHandler
    public Resolution showForm(){
        return new JSP("pembangunan/TSPSS/sedia_surat_kelulusan_borang7G.jsp").addParameter("tab", "true");
    }

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {

        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = permohonanDAO.findById(idPermohonan);
        peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);

        String[] tname = {"permohonan"};
        Object[] model = {permohonan};

        List<FasaPermohonan> listFasa;
        listFasa = fasaPermohonanDAO.findByEqualCriterias(tname, model, null);

        if (!(listFasa.isEmpty())) {
            for (int i = 0; i < listFasa.size(); i++) {
                FasaPermohonan fp = new FasaPermohonan();
                fp = listFasa.get(i);
                if (fp.getIdAliran().equals("borang7G")) {
                    fasaMohon = listFasa.get(i);
                    ulasan = fp.getUlasan();
                }
            }
        }
        listtuntutankos = permohonanTuntutanKosDAO.findByEqualCriterias(tname, model, null);

        if(!(listtuntutankos.isEmpty())){
            for(int i=0; i < listtuntutankos.size(); i++){
                permohonantuntutkos = new PermohonanTuntutanKos();
                permohonantuntutkos = listtuntutankos.get(i);
                if(permohonantuntutkos.getItem().equals("Premium Tambahan")){
                    premium = permohonantuntutkos.getAmaunTuntutan();
                } else if(permohonantuntutkos.getItem().equals("Kos Sumbangan Infrastruktur")){
                    kos = permohonantuntutkos.getAmaunTuntutan();
                } else if(permohonantuntutkos.getItem().equals("Pendaftaran/Penulisan")){
                    daftar = permohonantuntutkos.getAmaunTuntutan();
                } else if(permohonantuntutkos.getItem().equals("Sewa Baru")){
                    sewa = permohonantuntutkos.getAmaunTuntutan();
                } else if(permohonantuntutkos.getItem().equals("Upah Ukur/Tanda Sempadan")){
                    upah = permohonantuntutkos.getAmaunTuntutan();
                } else if(permohonantuntutkos.getItem().equals("Notis")){
                    notis = permohonantuntutkos.getAmaunTuntutan();
                } else if(permohonantuntutkos.getItem().equals("Jumlah")){
                    jumlah = permohonantuntutkos.getAmaunTuntutan();
                }
            }
        }
    }

    public Resolution simpan(){

//        Pengguna pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        KodCawangan caw = peng.getKodCawangan();
        InfoAudit ia = new InfoAudit();
        ia.setDimasukOleh(peng);
        ia.setTarikhMasuk(new java.util.Date());

//        if(upah.equals(null)){
//            upah = new BigDecimal(0.00);
//        }
//        if(kos.equals(null)){
//            kos = new BigDecimal(0.00);
//        }
//        if(sewa.equals(null)){
//            sewa = new BigDecimal(0.00);
//        }
//        if(premium.equals(null)){
//            premium = new BigDecimal(0.00);
//        }
//        if(daftar.equals(null)){
//            daftar = new BigDecimal(0.00);
//        }
//        if(notis.equals(null)){
//            notis = new BigDecimal(0.00);
//        }

        if(upah == null){
            upah = new BigDecimal(0.00);
        }
        if(kos == null){
            kos = new BigDecimal(0.00);
        }
        if(sewa == null){
            sewa = new BigDecimal(0.00);
        }
        if(premium == null){
            premium = new BigDecimal(0.00);
        }
        if(daftar == null){
            daftar = new BigDecimal(0.00);
        }
        if(notis == null){
            notis = new BigDecimal(0.00);
        }

        jumlah = premium.add(kos).add(daftar).add(sewa).add(upah).add(notis);

        if(!(listtuntutankos.isEmpty())){

            for(int i=0; i < listtuntutankos.size(); i++){

                permohonantuntutkos = new PermohonanTuntutanKos();
                permohonantuntutkos = listtuntutankos.get(i);
                if(permohonantuntutkos.getItem().equals("Premium Tambahan")){
                    permohonantuntutkos.setAmaunTuntutan(premium);
                } else if(permohonantuntutkos.getItem().equals("Kos Sumbangan Infrastruktur")){
                    permohonantuntutkos.setAmaunTuntutan(kos);
                } else if(permohonantuntutkos.getItem().equals("Pendaftaran/Penulisan")){
                    permohonantuntutkos.setAmaunTuntutan(daftar);
                } else if(permohonantuntutkos.getItem().equals("Sewa Baru")){
                    permohonantuntutkos.setAmaunTuntutan(sewa);
                } else if(permohonantuntutkos.getItem().equals("Upah Ukur/Tanda Sempadan")){
                    permohonantuntutkos.setAmaunTuntutan(upah);
                } else if(permohonantuntutkos.getItem().equals("Notis")){
                    permohonantuntutkos.setAmaunTuntutan(notis);
                } else if(permohonantuntutkos.getItem().equals("Jumlah")){
                    permohonantuntutkos.setAmaunTuntutan(jumlah);
                }

                permohonantuntutkos.setInfoAudit(ia);
                permohonantuntutkos.setPermohonan(permohonan);
                permohonantuntutkos.setCawangan(caw);
                pembangunanServ.simpanPermohonanTuntutanKos(permohonantuntutkos);
            }
        }

        else {

            String[] itemList = {"Premium Tambahan", "Kos Sumbangan Infrastruktur", "Pendaftaran/Penulisan", "Sewa Baru", "Upah Ukur/Tanda Sempadan", "Notis", "Jumlah"};
            BigDecimal [] amaunTuntutanList = {premium, kos, daftar, sewa, upah, notis, jumlah};

            for(int i = 0; i < 7; i++) {
                    permohonantuntutkos = new PermohonanTuntutanKos();
                    permohonantuntutkos.setInfoAudit(ia);
                    permohonantuntutkos.setItem(itemList[i]);
                    permohonantuntutkos.setAmaunTuntutan(amaunTuntutanList[i]);
                    permohonantuntutkos.setPermohonan(permohonan);
                    permohonantuntutkos.setCawangan(caw);
                    pembangunanServ.simpanPermohonanTuntutanKos(permohonantuntutkos);
            }
        }

        String stageId = "borang7G";
        
        if(ulasan == null || ulasan.equals("")){
            ulasan = "TIADA DATA";
        }

        if(fasaMohon != null){
            fasaMohon.setPermohonan(permohonan);
            fasaMohon.setCawangan(caw);
            fasaMohon.setInfoAudit(ia);
            fasaMohon.setIdPengguna(peng.getIdPengguna());
            fasaMohon.setIdAliran(stageId);
            fasaMohon.setUlasan(ulasan);
            pembangunanServ.simpanFasaPermohonan(fasaMohon);
        } else{
            fasaMohon = new FasaPermohonan();
            fasaMohon.setPermohonan(permohonan);
            fasaMohon.setCawangan(caw);
            fasaMohon.setInfoAudit(ia);
            fasaMohon.setIdPengguna(peng.getIdPengguna());
            fasaMohon.setIdAliran(stageId);
            fasaMohon.setUlasan(ulasan);
            pembangunanServ.simpanFasaPermohonan(fasaMohon);
        }
        
        addSimpleMessage("Maklumat telah berjaya disimpan.");
        return new JSP("pembangunan/TSPSS/sedia_surat_kelulusan_borang7G.jsp").addParameter("tab", "true");
    }

    public Permohonan getPermohonan() {
        return permohonan;
    }

    public void setPermohonan(Permohonan permohonan) {
        this.permohonan = permohonan;
    }

    public FasaPermohonan getFasaMohon() {
        return fasaMohon;
    }

    public void setFasaMohon(FasaPermohonan fasaMohon) {
        this.fasaMohon = fasaMohon;
    }
    
    public String getNegeri() {
        return negeri;
    }

    public void setNegeri(String negeri) {
        this.negeri = negeri;
    }

    public Pengguna getPeng() {
        return peng;
    }

    public void setPeng(Pengguna peng) {
        this.peng = peng;
    }

    public BigDecimal getDaftar() {
        return daftar;
    }

    public void setDaftar(BigDecimal daftar) {
        this.daftar = daftar;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public BigDecimal getJumlah() {
        return jumlah;
    }

    public void setJumlah(BigDecimal jumlah) {
        this.jumlah = jumlah;
    }

    public BigDecimal getKos() {
        return kos;
    }

    public void setKos(BigDecimal kos) {
        this.kos = kos;
    }

    public BigDecimal getNotis() {
        return notis;
    }

    public void setNotis(BigDecimal notis) {
        this.notis = notis;
    }

    public PermohonanTuntutanKos getPermohonantuntutkos() {
        return permohonantuntutkos;
    }

    public void setPermohonantuntutkos(PermohonanTuntutanKos permohonantuntutkos) {
        this.permohonantuntutkos = permohonantuntutkos;
    }

    public BigDecimal getPremium() {
        return premium;
    }

    public void setPremium(BigDecimal premium) {
        this.premium = premium;
    }

    public BigDecimal getSewa() {
        return sewa;
    }

    public void setSewa(BigDecimal sewa) {
        this.sewa = sewa;
    }

    public BigDecimal getUpah() {
        return upah;
    }

    public void setUpah(BigDecimal upah) {
        this.upah = upah;
    }

    public String getPerakuan() {
        return perakuan;
    }

    public void setPerakuan(String perakuan) {
        this.perakuan = perakuan;
    }

    public List<PermohonanTuntutanKos> getListtuntutankos() {
        return listtuntutankos;
    }

    public void setListtuntutankos(List<PermohonanTuntutanKos> listtuntutankos) {
        this.listtuntutankos = listtuntutankos;
    }

    public String getUlasan() {
        return ulasan;
    }

    public void setUlasan(String ulasan) {
        this.ulasan = ulasan;
    }
    
}
