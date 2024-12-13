
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.stripes.pelupusan;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.dao.KodTuntutDAO;
import etanah.dao.PermohonanDAO;
import etanah.dao.PermohonanTuntutanKosDAO;
import etanah.model.Permohonan;
import etanah.model.PermohonanTuntutanKos;
import etanah.model.Pengguna;
import etanah.model.KodCawangan;
import etanah.model.InfoAudit;
import etanah.model.KodTransaksi;
import etanah.model.KodTransaksiTuntut;
import etanah.model.KodTuntut;
import etanah.model.PermohonanTuntutan;
import etanah.model.PermohonanTuntutanButiran;
import etanah.service.PelupusanService;
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
 * @author Rohan
 */
@UrlBinding("/pelupusan/borang_5A_Ns")
public class Borang5ANsActionBean  extends AbleActionBean {
private static final Logger LOG = Logger.getLogger(Borang5ANsActionBean.class);
    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    KodTuntutDAO kodTuntutDAO;
    @Inject
    PermohonanTuntutanKosDAO permohonanTuntutanKosDAO;
    @Inject
    PelupusanService pelupusanService;
    @Inject
    etanah.Configuration conf;
    private Permohonan permohonan;
    private String negeri;
    private PermohonanTuntutanKos permohonantuntutkos;
    private BigDecimal cukai;
    private BigDecimal premium;
    private BigDecimal pendaftaranHakmilikTetap;
    private BigDecimal bayaran;
    private BigDecimal tandaSempadan;
    private BigDecimal penyediaanHakmilikTetap;
    private BigDecimal hakmilikSementara;
    private BigDecimal jumlah;
    private BigDecimal notis;
    private String item;
    private String idPermohonan;
    private List<PermohonanTuntutanKos> listtuntutankos ;
   

    @DefaultHandler
    public Resolution showForm(){
        return new JSP("pelupusan/borang_5a_Ns.jsp").addParameter("tab", "true");
    }

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {

        premium = BigDecimal.ZERO;
        cukai = BigDecimal.ZERO;
        pendaftaranHakmilikTetap = BigDecimal.ZERO;
        bayaran = BigDecimal.ZERO;
        tandaSempadan = BigDecimal.ZERO;
        penyediaanHakmilikTetap = BigDecimal.ZERO;
        hakmilikSementara = BigDecimal.ZERO;
        jumlah = BigDecimal.ZERO;
        notis = BigDecimal.ZERO;

        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = permohonanDAO.findById(idPermohonan);

        String[] tname = {"permohonan"};
        Object[] model = {permohonan};

        listtuntutankos = new ArrayList<PermohonanTuntutanKos>();
        listtuntutankos = pelupusanService.findTuntutByIdMohon(idPermohonan);


  if(!(listtuntutankos.isEmpty())){
            for(int i=0; i < listtuntutankos.size(); i++){
                permohonantuntutkos = new PermohonanTuntutanKos();
                permohonantuntutkos = listtuntutankos.get(i);
                if(permohonantuntutkos.getKodTuntut() != null){
                      if(permohonantuntutkos.getKodTuntut().getKod().equals("DISTX")){
                        cukai = permohonantuntutkos.getAmaunTuntutan();
                    }else if(permohonantuntutkos.getKodTuntut().getKod().equals("DISPM")){
                        premium = permohonantuntutkos.getAmaunTuntutan();
                    }else if(permohonantuntutkos.getKodTuntut().getKod().equals("DISUK")){
                        bayaran = permohonantuntutkos.getAmaunTuntutan();
                    }else if(permohonantuntutkos.getKodTuntut().getKod().equals("DISFP")){
                        penyediaanHakmilikTetap = permohonantuntutkos.getAmaunTuntutan();
                    } else if(permohonantuntutkos.getKodTuntut().getKod().equals("DISSM")){
                        tandaSempadan = permohonantuntutkos.getAmaunTuntutan();
                    } else if(permohonantuntutkos.getKodTuntut().getKod().equals("DISFR")){
                        hakmilikSementara = permohonantuntutkos.getAmaunTuntutan();
                    } else if(permohonantuntutkos.getKodTuntut().getKod().equals("DISQT")){
                        pendaftaranHakmilikTetap = permohonantuntutkos.getAmaunTuntutan();
                    } else if(permohonantuntutkos.getKodTuntut().getKod().equals("DISNO")){
                        notis = permohonantuntutkos.getAmaunTuntutan();
                    }
                }
            }
              jumlah = premium.add(cukai).add(bayaran).add(penyediaanHakmilikTetap).add(tandaSempadan).add(pendaftaranHakmilikTetap).add(hakmilikSementara).add(notis);
         LOG.debug("---------Jumlah-----------:"+jumlah);
         }
    }

    public Resolution simpan(){
        Pengguna pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        KodCawangan caw = pengguna.getKodCawangan();
        InfoAudit ia = new InfoAudit();
        ia.setDimasukOleh(pengguna);
        ia.setTarikhMasuk(new java.util.Date());
        if (cukai == null) {
            cukai = new BigDecimal(0);
        } else {
            cukai = new BigDecimal(cukai.intValue());
        }

        if (premium == null) {
            premium = new BigDecimal(0);
        } else {
            premium = new BigDecimal(premium.intValue());
        }
          
        if(bayaran == null){
            bayaran = new BigDecimal(0);
        }else{
            bayaran = new BigDecimal(bayaran.intValue());
        }

        if(tandaSempadan == null){
            tandaSempadan = new BigDecimal(0);
       }else{
            tandaSempadan = new BigDecimal(tandaSempadan.intValue());
        }
        
        if(penyediaanHakmilikTetap == null){
            penyediaanHakmilikTetap = new BigDecimal(0);
        }else{
            penyediaanHakmilikTetap = new BigDecimal(penyediaanHakmilikTetap.intValue());
        }
        
        if(pendaftaranHakmilikTetap == null){
            pendaftaranHakmilikTetap = new BigDecimal(0);
        }else{
            pendaftaranHakmilikTetap = new BigDecimal(pendaftaranHakmilikTetap.intValue());
        }
        if(hakmilikSementara == null){
            hakmilikSementara = new BigDecimal(0);
         }else{
            hakmilikSementara = new BigDecimal(hakmilikSementara.intValue());
        }

         if(notis == null){
            notis = new BigDecimal(0);
         }else{
            notis = new BigDecimal(notis.intValue());
        }

        jumlah = cukai.add(premium).add(bayaran).add(tandaSempadan).add(penyediaanHakmilikTetap).add(pendaftaranHakmilikTetap).add(hakmilikSementara).add(notis);
        System.out.println("jumlah:"+jumlah);
        KodTransaksi kt = new KodTransaksi();
        if(!(listtuntutankos.isEmpty())){

            for(int i=0; i < listtuntutankos.size(); i++){

                permohonantuntutkos = new PermohonanTuntutanKos();
                permohonantuntutkos = listtuntutankos.get(i);
                if(permohonantuntutkos.getKodTuntut() != null){
                        if(permohonantuntutkos.getKodTuntut().getKod().equals("DISTX")){
                        permohonantuntutkos.setAmaunTuntutan(cukai);
                         kt.setKod("61403");
                        permohonantuntutkos.setKodTransaksi(kt);
                    }else if(permohonantuntutkos.getKodTuntut().getKod().equals("DISPM")){
                        permohonantuntutkos.setAmaunTuntutan(premium);
                        kt.setKod("12301");
                        permohonantuntutkos.setKodTransaksi(kt);
                      
                    } else if(permohonantuntutkos.getKodTuntut().getKod().equals("DISUK")){
                        permohonantuntutkos.setAmaunTuntutan(bayaran);
                         kt.setKod("12108");
                        permohonantuntutkos.setKodTransaksi(kt);
                    } else if(permohonantuntutkos.getKodTuntut().getKod().equals("DISFP")){
                        permohonantuntutkos.setAmaunTuntutan(penyediaanHakmilikTetap);
                         kt.setKod("12109");
                        permohonantuntutkos.setKodTransaksi(kt);
                    } else if(permohonantuntutkos.getKodTuntut().getKod().equals("DISSM")){
                        permohonantuntutkos.setAmaunTuntutan(tandaSempadan);
                         kt.setKod("12112");
                        permohonantuntutkos.setKodTransaksi(kt);
                    } else if(permohonantuntutkos.getKodTuntut().getKod().equals("DISFR")){
                        permohonantuntutkos.setAmaunTuntutan(pendaftaranHakmilikTetap);
                         kt.setKod("30010");
                        permohonantuntutkos.setKodTransaksi(kt);
                    }else if(permohonantuntutkos.getKodTuntut().getKod().equals("DISQT")){
                         permohonantuntutkos.setAmaunTuntutan(hakmilikSementara);
                          kt.setKod("30080");
                        permohonantuntutkos.setKodTransaksi(kt);
                    }else if(permohonantuntutkos.getKodTuntut().getKod().equals("DISNO")){
                         kt.setKod("40040");
                        permohonantuntutkos.setKodTransaksi(kt);
                        permohonantuntutkos.setAmaunTuntutan(notis);
                    }
                    ia = permohonantuntutkos.getInfoAudit();
                    ia.setTarikhKemaskini(new java.util.Date());
                    ia.setDiKemaskiniOleh(pengguna);
                    permohonantuntutkos.setInfoAudit(ia);
                    permohonantuntutkos.setPermohonan(permohonan);
                    permohonantuntutkos.setCawangan(caw);
                    pelupusanService.simpanPermohonanTuntutanKos1(permohonantuntutkos);
                }
                            // Added new Code
            PermohonanTuntutan permohonanTuntutan=new PermohonanTuntutan();
            permohonanTuntutan = pelupusanService.findPermohonanTuntutanByKodTransName(idPermohonan,"Notis 5A");
            if(permohonanTuntutan != null){
                ia = permohonanTuntutan.getInfoAudit();
                ia.setTarikhKemaskini(new java.util.Date());
                ia.setDiKemaskiniOleh(pengguna);
                permohonanTuntutan.setInfoAudit(ia);
                pelupusanService.simpanPermohonanTuntutan(permohonanTuntutan);
                // find MohonTuntutButir by MohonTuntut and MohonTuntutKos
                PermohonanTuntutanButiran permohonanTuntutanButiran=new PermohonanTuntutanButiran();
                permohonanTuntutanButiran = pelupusanService.findPermohonanTuntutButirByIdTuntutAndIdKos(permohonantuntutkos.getIdKos(),permohonanTuntutan.getIdTuntut());
                ia = permohonanTuntutanButiran.getInfoAudit();
                ia.setTarikhKemaskini(new java.util.Date());
                ia.setDiKemaskiniOleh(pengguna);
                permohonanTuntutanButiran.setInfoAudit(ia);
                pelupusanService.simpanPermohonanTuntutanButiran(permohonanTuntutanButiran);
            }
         }
            if((listtuntutankos.size()==1)&& permohonantuntutkos.getKodTuntut().getKod().equals("DISPM")){
              String[] itemList = {"DISTX", "DISUK","DISFP", "DISSM", "DISFR","DISQT","DISNO"};
              BigDecimal [] amaunTuntutanList = {cukai, bayaran, penyediaanHakmilikTetap, tandaSempadan, pendaftaranHakmilikTetap,hakmilikSementara,notis};
                // added new code
               if(permohonantuntutkos.getKodTuntut().getKod().equals("DISPM")){
                        permohonantuntutkos.setAmaunTuntutan(premium);
                    }
                PermohonanTuntutan permohonanTuntutan=new PermohonanTuntutan();
                KodTransaksiTuntut  kodTransTuntut = new KodTransaksiTuntut();
                kodTransTuntut = pelupusanService.findKodTransTuntutByName("Notis 5A");
                permohonanTuntutan.setCawangan(caw);
                permohonanTuntutan.setPermohonan(permohonan);
                permohonanTuntutan.setInfoAudit(ia);
                permohonanTuntutan.setKodTransaksiTuntut(kodTransTuntut);
                permohonanTuntutan.setTarikhTuntutan(new java.util.Date());
                pelupusanService.simpanPermohonanTuntutan(permohonanTuntutan);

            for(int i = 0; i < 8; i++) {
                KodTuntut kodTuntut = new KodTuntut();
                //kodTuntut = pembangunanServ.findKodTuntutByName(itemList[i]);
                kodTuntut = kodTuntutDAO.findById(itemList[i]);
                LOG.debug("---------kodTuntut---------:"+kodTuntut);
                permohonantuntutkos = new PermohonanTuntutanKos();
                permohonantuntutkos.setKodTuntut(kodTuntut);
                permohonantuntutkos.setInfoAudit(ia);
                permohonantuntutkos.setItem(itemList[i]);
                permohonantuntutkos.setAmaunTuntutan(amaunTuntutanList[i]);
                permohonantuntutkos.setPermohonan(permohonan);
                permohonantuntutkos.setCawangan(caw);
                pelupusanService.simpanPermohonanTuntutanKos1(permohonantuntutkos);

                // added new code
                LOG.debug("---------PermohonanTuntutanButiran---------:");
                PermohonanTuntutanButiran permohonanButir=new PermohonanTuntutanButiran();
                permohonanButir.setPermohonanTuntutan(permohonanTuntutan);
                permohonanButir.setPermohonanTuntutanKos(permohonantuntutkos);
                permohonanButir.setInfoAudit(ia);
                pelupusanService.simpanPermohonanTuntutanButiran(permohonanButir);
            }

            }

        } else {
            String[] itemList = {"DISTX","DISPM", "DISUK","DISFP", "DISSM","DISFR","DISQT","DISNO"};
            BigDecimal [] amaunTuntutanList = { cukai,premium, bayaran, tandaSempadan, penyediaanHakmilikTetap, pendaftaranHakmilikTetap,hakmilikSementara,notis};
                // added new code
                PermohonanTuntutan permohonanTuntutan=new PermohonanTuntutan();
                KodTransaksiTuntut  kodTransTuntut = new KodTransaksiTuntut();
                kodTransTuntut = pelupusanService.findKodTransTuntutByName("Notis 5A");
                permohonanTuntutan.setCawangan(caw);
                permohonanTuntutan.setPermohonan(permohonan);
                permohonanTuntutan.setInfoAudit(ia);
                permohonanTuntutan.setKodTransaksiTuntut(kodTransTuntut);
                permohonanTuntutan.setTarikhTuntutan(new java.util.Date());
                pelupusanService.simpanPermohonanTuntutan(permohonanTuntutan);

            for(int i = 0; i < 8; i++) {
                KodTuntut kodTuntut = new KodTuntut();
                //kodTuntut = pembangunanServ.findKodTuntutByName(itemList[i]);
                kodTuntut = kodTuntutDAO.findById(itemList[i]);
                LOG.debug("---------kodTuntut---------:"+kodTuntut);
                permohonantuntutkos = new PermohonanTuntutanKos();
                permohonantuntutkos.setKodTuntut(kodTuntut);
                permohonantuntutkos.setInfoAudit(ia);
                permohonantuntutkos.setItem(itemList[i]);
                permohonantuntutkos.setAmaunTuntutan(amaunTuntutanList[i]);
                permohonantuntutkos.setPermohonan(permohonan);
                permohonantuntutkos.setCawangan(caw);
                pelupusanService.simpanPermohonanTuntutanKos1(permohonantuntutkos);

                // added new code
                LOG.debug("---------PermohonanTuntutanButiran---------:");
                PermohonanTuntutanButiran permohonanButir=new PermohonanTuntutanButiran();
                permohonanButir.setPermohonanTuntutan(permohonanTuntutan);
                permohonanButir.setPermohonanTuntutanKos(permohonantuntutkos);
                permohonanButir.setInfoAudit(ia);
                pelupusanService.simpanPermohonanTuntutanButiran(permohonanButir);
            }
        }
        jumlah =  premium .add(cukai).add(bayaran).add(tandaSempadan).add(penyediaanHakmilikTetap).add(pendaftaranHakmilikTetap).add(hakmilikSementara).add(notis);
        addSimpleMessage("Maklumat telah berjaya disimpan.");
        return new JSP("pelupusan/borang_5a_Ns.jsp").addParameter("tab", "true");
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


    public BigDecimal getTandaSempadan() {
        return tandaSempadan;
    }

    public void setTandaSempadan(BigDecimal tandaSempadan) {
        this.tandaSempadan = tandaSempadan;
    }

    public BigDecimal getHakmilikSementara() {
        return hakmilikSementara;
    }

    public void setHakmilikSementara(BigDecimal hakmilikSementara) {
        this.hakmilikSementara = hakmilikSementara;
    }

    public BigDecimal getBayaran() {
        return bayaran;
    }

    public void setBayaran(BigDecimal bayaran) {
        this.bayaran = bayaran;
    }

    public BigDecimal getCukai() {
        return cukai;
    }

    public void setCukai(BigDecimal cukai) {
        this.cukai = cukai;
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

    public BigDecimal getPendaftaranHakmilikTetap() {
        return pendaftaranHakmilikTetap;
    }

    public void setPendaftaranHakmilikTetap(BigDecimal pendaftaranHakmilikTetap) {
        this.pendaftaranHakmilikTetap = pendaftaranHakmilikTetap;
    }

    public BigDecimal getPenyediaanHakmilikTetap() {
        return penyediaanHakmilikTetap;
    }

    public void setPenyediaanHakmilikTetap(BigDecimal penyediaanHakmilikTetap) {
        this.penyediaanHakmilikTetap = penyediaanHakmilikTetap;
    }
    
}
