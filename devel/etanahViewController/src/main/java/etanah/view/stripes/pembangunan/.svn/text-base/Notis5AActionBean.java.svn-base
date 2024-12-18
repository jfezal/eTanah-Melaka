/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.stripes.pembangunan;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.dao.KodTransaksiTuntutDAO;
import etanah.dao.KodTuntutDAO;
import etanah.dao.PermohonanDAO;
import etanah.dao.PermohonanTuntutanKosDAO;
import etanah.model.HakmilikPermohonan;
import etanah.model.Permohonan;
import etanah.model.PermohonanTuntutanKos;
import etanah.model.Pengguna;
import etanah.model.KodCawangan;
import etanah.model.InfoAudit;
import etanah.model.KodTransaksiTuntut;
import etanah.model.KodTuntut;
import etanah.model.PermohonanTuntutan;
import etanah.model.PermohonanTuntutanButiran;
import java.math.BigDecimal;
import etanah.view.etanahActionBeanContext;
import etanah.service.PembangunanService;
//import java.util.ArrayList;
import java.util.ArrayList;
import java.util.List;
//import java.util.logging.Level;
//import java.util.logging.Logger;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;
import org.apache.log4j.Logger;

/**
 *
 * @author NageswaraRao
 */
@UrlBinding("/pembangunan/melaka/notis5A")
public class Notis5AActionBean extends AbleActionBean {
private static final Logger LOG = Logger.getLogger(Notis5AActionBean.class);
    @Inject
    KodTuntutDAO kodTuntutDAO;
    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    PermohonanTuntutanKosDAO permohonanTuntutanKosDAO;
    @Inject
    PembangunanService pembangunanServ;
    @Inject
    etanah.Configuration conf;
    @Inject
    KodTransaksiTuntutDAO kodTransaksiTuntutDAO;
    private Permohonan permohonan;
    private String negeri;
    private PermohonanTuntutanKos permohonantuntutkos;
    private BigDecimal hasilTahun;
    private BigDecimal premium;
    private BigDecimal sumbangan;
    private BigDecimal bayaranPelan;
    private BigDecimal hakmilikTetap;
    private BigDecimal hakmilikSemantara;
    private BigDecimal notis;
    private BigDecimal jumlah;
    private String item;
    private List<PermohonanTuntutanKos> listtuntutankos;
    private String idPermohonan;

    @DefaultHandler
    public Resolution showForm(){
        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        return new JSP("pembangunan/melaka/notis5A.jsp").addParameter("tab", "true");
    }

    public Resolution viewForm(){
        return new JSP("pembangunan/melaka/notis5A.jsp").addParameter("tab", "true");
    }

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {
        hasilTahun = BigDecimal.ZERO;
        premium = BigDecimal.ZERO;
        sumbangan = BigDecimal.ZERO;
        bayaranPelan = BigDecimal.ZERO;
        hakmilikTetap = BigDecimal.ZERO;
        hakmilikSemantara = BigDecimal.ZERO;
        notis = BigDecimal.ZERO;
        jumlah = BigDecimal.ZERO;

        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = permohonanDAO.findById(idPermohonan);
        String[] tname = {"permohonan"};
        Object[] model = {permohonan};
        listtuntutankos = new ArrayList<PermohonanTuntutanKos>();
       // listtuntutankos = permohonanTuntutanKosDAO.findByEqualCriterias(tname, model, null);
        listtuntutankos = pembangunanServ.findTuntutByIdMohon(idPermohonan);

        if(!(listtuntutankos.isEmpty())){
            for(int i=0; i < listtuntutankos.size(); i++){
                permohonantuntutkos = new PermohonanTuntutanKos();
                permohonantuntutkos = listtuntutankos.get(i);
                if(permohonantuntutkos.getKodTuntut() != null){
                    if(permohonantuntutkos.getKodTuntut().getKod().equals("DEV11")){
                        hasilTahun = permohonantuntutkos.getAmaunTuntutan();
                    } else if(permohonantuntutkos.getKodTuntut().getKod().equals("DEV04")){
                        premium = permohonantuntutkos.getAmaunTuntutan();
                    } else if(permohonantuntutkos.getKodTuntut().getKod().equals("DEV07")){
                        sumbangan = permohonantuntutkos.getAmaunTuntutan();
                    } else if(permohonantuntutkos.getKodTuntut().getKod().equals("DEV03")){
                        bayaranPelan = permohonantuntutkos.getAmaunTuntutan();
                    } else if(permohonantuntutkos.getKodTuntut().getKod().equals("DEV02")){
                        hakmilikTetap = permohonantuntutkos.getAmaunTuntutan();
                    } else if(permohonantuntutkos.getKodTuntut().getKod().equals("DEV01")){
                        hakmilikSemantara = permohonantuntutkos.getAmaunTuntutan();
                    } else if(permohonantuntutkos.getKodTuntut().getKod().equals("DEV13")){
                        notis = permohonantuntutkos.getAmaunTuntutan();
                    }
                }
            }
         jumlah = hasilTahun.add(premium).add(sumbangan).add(bayaranPelan).add(hakmilikTetap).add(hakmilikSemantara).add(notis);
         LOG.debug("---------Jumlah-----------:"+jumlah);
        }
    }

    public Resolution simpan(){
        Pengguna pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        KodCawangan caw = pengguna.getKodCawangan();
        InfoAudit ia = new InfoAudit();
        ia.setDimasukOleh(pengguna);
        ia.setTarikhMasuk(new java.util.Date());

        if(hasilTahun == null){
            hasilTahun = new BigDecimal(0.00);
        }
        if(premium == null){
            premium = new BigDecimal(0.00);
        }
        if(sumbangan == null){
            sumbangan = new BigDecimal(0.00);
        }
        if(bayaranPelan == null){
            bayaranPelan = new BigDecimal(0.00);
        }
        if(hakmilikTetap == null){
            hakmilikTetap = new BigDecimal(0.00);
        }
        if(hakmilikSemantara == null){
            hakmilikSemantara = new BigDecimal(0.00);
        }
        if(notis == null){
            notis = new BigDecimal(0.00);
        }



         if(!(listtuntutankos.isEmpty()) && listtuntutankos.size() == 7  ){
//        if(!(listtuntutankos.isEmpty())){
             for(int i=0; i < listtuntutankos.size(); i++){

                permohonantuntutkos = new PermohonanTuntutanKos();
                permohonantuntutkos = listtuntutankos.get(i);
                if(permohonantuntutkos.getKodTuntut() != null){
                    if(permohonantuntutkos.getKodTuntut().getKod().equals("DEV11")){
                        permohonantuntutkos.setAmaunTuntutan(hasilTahun);
                    } else if(permohonantuntutkos.getKodTuntut().getKod().equals("DEV04")){
                        permohonantuntutkos.setAmaunTuntutan(premium);
                    } else if(permohonantuntutkos.getKodTuntut().getKod().equals("DEV07")){
                        permohonantuntutkos.setAmaunTuntutan(sumbangan);
                    } else if(permohonantuntutkos.getKodTuntut().getKod().equals("DEV03")){
                        permohonantuntutkos.setAmaunTuntutan(bayaranPelan);
                    } else if(permohonantuntutkos.getKodTuntut().getKod().equals("DEV02")){
                        permohonantuntutkos.setAmaunTuntutan(hakmilikTetap);
                    } else if(permohonantuntutkos.getKodTuntut().getKod().equals("DEV01")){
                        permohonantuntutkos.setAmaunTuntutan(hakmilikSemantara);
                    } else if(permohonantuntutkos.getKodTuntut().getKod().equals("DEV13")){
                        permohonantuntutkos.setAmaunTuntutan(notis);
                    }
                    ia = permohonantuntutkos.getInfoAudit();
                    ia.setTarikhKemaskini(new java.util.Date());
                    ia.setDiKemaskiniOleh(pengguna);
                    permohonantuntutkos.setInfoAudit(ia);
                    permohonantuntutkos.setPermohonan(permohonan);
                    permohonantuntutkos.setCawangan(caw);
                    pembangunanServ.simpanPermohonanTuntutanKos(permohonantuntutkos);
                }

            // Added new Code
            PermohonanTuntutan permohonanTuntutan=new PermohonanTuntutan();
            permohonanTuntutan = pembangunanServ.findPermohonanTuntutanByKodTransName(idPermohonan,"Surat Tuntutan Bayaran Notis5A");
            if(permohonanTuntutan != null){
                ia = permohonanTuntutan.getInfoAudit();
                ia.setTarikhKemaskini(new java.util.Date());
                ia.setDiKemaskiniOleh(pengguna);
                permohonanTuntutan.setInfoAudit(ia);
                pembangunanServ.simpanPermohonanTuntutan(permohonanTuntutan);
                // find MohonTuntutButir by MohonTuntut and MohonTuntutKos
                PermohonanTuntutanButiran permohonanTuntutanButiran=new PermohonanTuntutanButiran();
                permohonanTuntutanButiran = pembangunanServ.findPermohonanTuntutButirByIdTuntutAndIdKos(permohonantuntutkos.getIdKos(),permohonanTuntutan.getIdTuntut());
                ia = permohonanTuntutanButiran.getInfoAudit();
                ia.setTarikhKemaskini(new java.util.Date());
                ia.setDiKemaskiniOleh(pengguna);
                permohonanTuntutanButiran.setInfoAudit(ia);
                pembangunanServ.simpanPermohonanTuntutanButiran(permohonanTuntutanButiran);
            }
         }

        } else {
             String[] itemList = { "DEV04","DEV11","DEV07", "DEV03","DEV02","DEV01","DEV13"};
             BigDecimal [] amaunTuntutanList = { premium,hasilTahun, sumbangan, bayaranPelan, hakmilikTetap, hakmilikSemantara, notis};

            KodTransaksiTuntut  kodTransTuntut = new KodTransaksiTuntut();
            kodTransTuntut = kodTransaksiTuntutDAO.findById("DEV5A");
            PermohonanTuntutan permohonanTuntutan=new PermohonanTuntutan();
            permohonanTuntutan.setCawangan(caw);
            permohonanTuntutan.setPermohonan(permohonan);
            permohonanTuntutan.setInfoAudit(ia);
            permohonanTuntutan.setKodTransaksiTuntut(kodTransTuntut);
            permohonanTuntutan.setTarikhTuntutan(new java.util.Date());
            pembangunanServ.simpanPermohonanTuntutan(permohonanTuntutan);
             
            int k=0;
            if(!(listtuntutankos.isEmpty())){
                for(int i=0;i<listtuntutankos.size();i++){
                permohonantuntutkos = new PermohonanTuntutanKos();
                permohonantuntutkos = listtuntutankos.get(i);
                ia = permohonantuntutkos.getInfoAudit();
                    if(permohonantuntutkos.getKodTuntut().getKod().equals("DEV11")){
                        permohonantuntutkos.setAmaunTuntutan(hasilTahun);
                    } else if(permohonantuntutkos.getKodTuntut().getKod().equals("DEV04")){
                        permohonantuntutkos.setAmaunTuntutan(premium);
                    } else if(permohonantuntutkos.getKodTuntut().getKod().equals("DEV07")){
                        permohonantuntutkos.setAmaunTuntutan(sumbangan);
                    }
                ia.setTarikhKemaskini(new java.util.Date());
                ia.setDiKemaskiniOleh(pengguna);
                permohonantuntutkos.setInfoAudit(ia);
                pembangunanServ.simpanPermohonanTuntutanKos(permohonantuntutkos);
                // Added permohonanTuntutButir details
                PermohonanTuntutanButiran permohonanButir=new PermohonanTuntutanButiran();
                permohonanButir.setPermohonanTuntutan(permohonanTuntutan);
                permohonanButir.setPermohonanTuntutanKos(permohonantuntutkos);
                permohonanButir.setInfoAudit(ia);
                pembangunanServ.simpanPermohonanTuntutanButiran(permohonanButir);
                k++;
             }
            }

            

            while(k<7){
                KodTuntut kodTuntut = new KodTuntut();                
                kodTuntut = kodTuntutDAO.findById(itemList[k]);
                LOG.debug("---------kodTuntut---------:"+kodTuntut);
                permohonantuntutkos = new PermohonanTuntutanKos();
                permohonantuntutkos.setKodTuntut(kodTuntut);
                permohonantuntutkos.setInfoAudit(ia);
                permohonantuntutkos.setItem(kodTuntut.getNama());
                permohonantuntutkos.setAmaunTuntutan(amaunTuntutanList[k]);
                permohonantuntutkos.setPermohonan(permohonan);
                permohonantuntutkos.setCawangan(caw);
                permohonantuntutkos.setKodTransaksi(kodTuntut.getKodKewTrans());
                pembangunanServ.simpanPermohonanTuntutanKos(permohonantuntutkos);

                // added new code
                PermohonanTuntutanButiran permohonanButir=new PermohonanTuntutanButiran();
                permohonanButir.setPermohonanTuntutan(permohonanTuntutan);
                permohonanButir.setPermohonanTuntutanKos(permohonantuntutkos);
                permohonanButir.setInfoAudit(ia);
                pembangunanServ.simpanPermohonanTuntutanButiran(permohonanButir);
                k++;
            }
        }
        jumlah = hasilTahun.add(premium).add(sumbangan).add(bayaranPelan).add(hakmilikTetap).add(hakmilikSemantara).add(notis);

         //Modify HakmilikPermohonan details
          HakmilikPermohonan hp = new HakmilikPermohonan();
          List<HakmilikPermohonan>  senaraiHakmilikPermohonan = permohonan.getSenaraiHakmilik();
          if(senaraiHakmilikPermohonan!=null && senaraiHakmilikPermohonan.size() > 0 ){
            hp = senaraiHakmilikPermohonan.get(0);
          }

          if(hp!=null){
                hp.setKadarPremium(premium);
                hp.setCukaiBaru(hasilTahun);
                hp.setKosInfra(sumbangan);
              pembangunanServ.simpanHakmilikPermohonan(hp);
          }


        addSimpleMessage("Maklumat telah berjaya disimpan.");
        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        return new JSP("pembangunan/melaka/notis5A.jsp").addParameter("tab", "true");
    }

    public Permohonan getPermohonan() {
        return permohonan;
    }

    public void setPermohonan(Permohonan permohonan) {
        this.permohonan = permohonan;
    }

    public String getNegeri() {
        return negeri;
    }

    public void setNegeri(String negeri) {
        this.negeri = negeri;
    }

    public BigDecimal getJumlah() {
        return jumlah;
    }

    public void setJumlah(BigDecimal jumlah) {
        this.jumlah = jumlah;
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

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public List<PermohonanTuntutanKos> getListtuntutankos() {
        return listtuntutankos;
    }

    public void setListtuntutankos(List<PermohonanTuntutanKos> listtuntutankos) {
        this.listtuntutankos = listtuntutankos;
    }

    public BigDecimal getBayaranPelan() {
        return bayaranPelan;
    }

    public void setBayaranPelan(BigDecimal bayaranPelan) {
        this.bayaranPelan = bayaranPelan;
    }

    public BigDecimal getHakmilikTetap() {
        return hakmilikTetap;
    }

    public void setHakmilikTetap(BigDecimal hakmilikTetap) {
        this.hakmilikTetap = hakmilikTetap;
    }

    public BigDecimal getHakmilikSemantara() {
        return hakmilikSemantara;
    }

    public void setHakmilikSemantara(BigDecimal hakmilikSemantara) {
        this.hakmilikSemantara = hakmilikSemantara;
    }

    public BigDecimal getHasilTahun() {
        return hasilTahun;
    }

    public void setHasilTahun(BigDecimal hasilTahun) {
        this.hasilTahun = hasilTahun;
    }

    public BigDecimal getSumbangan() {
        return sumbangan;
    }

    public void setSumbangan(BigDecimal sumbangan) {
        this.sumbangan = sumbangan;
    }

    public String getIdPermohonan() {
        return idPermohonan;
    }

    public void setIdPermohonan(String idPermohonan) {
        this.idPermohonan = idPermohonan;
    }

    public BigDecimal getNotis() {
        return notis;
    }

    public void setNotis(BigDecimal notis) {
        this.notis = notis;
    }

    
}
