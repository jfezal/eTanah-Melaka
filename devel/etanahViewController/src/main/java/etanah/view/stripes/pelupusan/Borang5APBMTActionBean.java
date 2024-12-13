/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package etanah.view.stripes.pelupusan;
import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.dao.PermohonanDAO;
import etanah.dao.PermohonanTuntutanKosDAO;
import etanah.model.Permohonan;
import etanah.model.PermohonanTuntutanKos;
import etanah.model.Pengguna;
import etanah.model.KodCawangan;
import etanah.model.InfoAudit;
import etanah.service.PelupusanService;
import java.math.BigDecimal;
import etanah.view.etanahActionBeanContext;
import java.util.List;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;
/**
 *
 * @author sitifariza.hanim
 */
@UrlBinding("/pelupusan/penyediaan_borang5A")

public class Borang5APBMTActionBean extends AbleActionBean {

    @Inject
    PermohonanDAO permohonanDAO;
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
    private BigDecimal upahUkur;
    private BigDecimal kosInfrastruktur;
    private BigDecimal pelanSuratanTetap;
    private BigDecimal suratanTetap;
    private BigDecimal pelanSuratanSementara;
    private BigDecimal suratanSementara;
    private BigDecimal notis;
    private BigDecimal jumlah;
    private String item;
    private List<PermohonanTuntutanKos> listtuntutankos;

    @DefaultHandler
    public Resolution showForm() {
        return new JSP("pelupusan/Borang_5APBMT.jsp").addParameter("tab", "true");
    }

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {

        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = permohonanDAO.findById(idPermohonan);

        String[] tname = {"permohonan"};
        Object[] model = {permohonan};

        listtuntutankos = permohonanTuntutanKosDAO.findByEqualCriterias(tname, model, null);

        if(!(listtuntutankos.isEmpty())){
            for(int i=0; i < listtuntutankos.size(); i++){
                permohonantuntutkos = new PermohonanTuntutanKos();
                permohonantuntutkos = listtuntutankos.get(i);
                if(permohonantuntutkos.getItem().equals("Cukai Tahun Pertama")){
                    cukai = permohonantuntutkos.getAmaunTuntutan();
                }else{
                    cukai = BigDecimal.ZERO;
                }
                if(permohonantuntutkos.getItem().equals("Premium")){
                    premium = permohonantuntutkos.getAmaunTuntutan();
                }else{
                    premium = BigDecimal.ZERO;
                }
                if(permohonantuntutkos.getItem().equals("Upah Ukur/Tanda Sempadan")){
                    upahUkur = permohonantuntutkos.getAmaunTuntutan();
                }else{
                    upahUkur = BigDecimal.ZERO;                
                }
                if(permohonantuntutkos.getItem().equals("Kos Sumbangan Infrastruktur")){
                    kosInfrastruktur = permohonantuntutkos.getAmaunTuntutan();
                }else{
                    kosInfrastruktur = BigDecimal.ZERO;
                }
                if(permohonantuntutkos.getItem().equals("Penyediaan Pelan Suratan Hakmilik Tetap")){
                    pelanSuratanTetap = permohonantuntutkos.getAmaunTuntutan();
                }else{
                    pelanSuratanTetap = BigDecimal.ZERO;
                }
                if(permohonantuntutkos.getItem().equals("Pendaftaran Suratan Hakmilik Tetap")){
                    suratanTetap = permohonantuntutkos.getAmaunTuntutan();
                }else{
                    suratanTetap = BigDecimal.ZERO;
                }
                if(permohonantuntutkos.getItem().equals("Penyediaan Pelan Suratan Hakmilik Sementara")){
                    pelanSuratanSementara = permohonantuntutkos.getAmaunTuntutan();
                }else{
                    pelanSuratanSementara = BigDecimal.ZERO;
                }
                if(permohonantuntutkos.getItem().equals("Pendaftaran Suratan Hakmilik Sementara")){
                    suratanSementara = permohonantuntutkos.getAmaunTuntutan();
                }else{
                    suratanSementara = BigDecimal.ZERO;
                }
                if(permohonantuntutkos.getItem().equals("Notis")){
                    notis = permohonantuntutkos.getAmaunTuntutan();
                }else{
                    notis = BigDecimal.ZERO;
                }
                if(permohonantuntutkos.getItem().equals("Jumlah")){
                    jumlah = permohonantuntutkos.getAmaunTuntutan();
                }
                else{
                    jumlah = BigDecimal.ZERO;
                }
            }
        }
        else {
                    cukai = BigDecimal.ZERO;
                
                    premium =  BigDecimal.ZERO;
                
                    upahUkur =  BigDecimal.ZERO;
         
                    kosInfrastruktur =  BigDecimal.ZERO;
               
                    pelanSuratanTetap =  BigDecimal.ZERO;
            
                    suratanTetap =  BigDecimal.ZERO;
                
                    pelanSuratanSementara =  BigDecimal.ZERO;
              
                    suratanSementara =  BigDecimal.ZERO;
        
                    notis =  BigDecimal.ZERO;
              
                    jumlah =  BigDecimal.ZERO;
        }
    }

    public Resolution simpan(){
        Pengguna pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        KodCawangan caw = pengguna.getKodCawangan();
        InfoAudit ia = new InfoAudit();
        ia.setDimasukOleh(pengguna);
        ia.setTarikhMasuk(new java.util.Date());

        if(cukai.equals(null)){
            cukai = new BigDecimal(0.00);
        }
        if(premium.equals(null)){
            premium = new BigDecimal(0.00);
        }
        if(upahUkur.equals(null)){
            upahUkur = new BigDecimal(0.00);
        }
        if(kosInfrastruktur.equals(null)){
            kosInfrastruktur = new BigDecimal(0.00);
        }
        if(pelanSuratanTetap.equals(null)){
            pelanSuratanTetap = new BigDecimal(0.00);
        }
        if(suratanTetap.equals(null)){
            suratanTetap = new BigDecimal(0.00);
        }
        if(pelanSuratanSementara.equals(null)){
            pelanSuratanSementara = new BigDecimal(0.00);
        }
        if(suratanSementara.equals(null)){
            suratanSementara = new BigDecimal(0.00);
        }
        if(notis.equals(null)){
            notis = new BigDecimal(0.00);
        }

        jumlah = cukai.add(premium).add(upahUkur).add(kosInfrastruktur).add(pelanSuratanTetap).add(suratanTetap).add(pelanSuratanSementara).add(suratanSementara).add(notis);
        System.out.println("jumlah:"+jumlah);

        if(!(listtuntutankos.isEmpty())){

            for(int i=0; i < listtuntutankos.size(); i++){

                permohonantuntutkos = new PermohonanTuntutanKos();
                permohonantuntutkos = listtuntutankos.get(i);
                if(permohonantuntutkos.getItem().equals("Cukai Tahun Pertama")){
                    permohonantuntutkos.setAmaunTuntutan(cukai);
                } else if(permohonantuntutkos.getItem().equals("Premium")){
                    permohonantuntutkos.setAmaunTuntutan(premium);
                } else if(permohonantuntutkos.getItem().equals("Upah Ukur/Tanda Sempadan")){
                    permohonantuntutkos.setAmaunTuntutan(upahUkur);
                } else if(permohonantuntutkos.getItem().equals("Kos Sumbangan Infrastruktur")){
                    permohonantuntutkos.setAmaunTuntutan(kosInfrastruktur);
                } else if(permohonantuntutkos.getItem().equals("Penyediaan Pelan Suratan Hakmilik Tetap")){
                    permohonantuntutkos.setAmaunTuntutan(pelanSuratanTetap);
                } else if(permohonantuntutkos.getItem().equals("Pendaftaran Suratan Hakmilik Tetap")){
                    permohonantuntutkos.setAmaunTuntutan(suratanTetap);
                } else if(permohonantuntutkos.getItem().equals("Penyediaan Pelan Suratan Hakmilik Sementara")){
                    permohonantuntutkos.setAmaunTuntutan(pelanSuratanSementara);
                } else if(permohonantuntutkos.getItem().equals("Pendaftaran Suratan Hakmilik Sementara")){
                   permohonantuntutkos.setAmaunTuntutan(suratanSementara);
                } else if(permohonantuntutkos.getItem().equals("Notis")){
                    permohonantuntutkos.setAmaunTuntutan(notis);
                } 
//                else if(permohonantuntutkos.getItem().equals("Jumlah")){
//                    permohonantuntutkos.setAmaunTuntutan(jumlah);
//                }

                permohonantuntutkos.setInfoAudit(ia);
                permohonantuntutkos.setPermohonan(permohonan);
                permohonantuntutkos.setCawangan(caw);
                pelupusanService.simpanPermohonanTuntutanKos(permohonantuntutkos);
            }
        }

        else {

            String[] itemList = {"Cukai Tahun Pertama", "Premium", "Upah Ukur/Tanda Sempadan", "Kos Sumbangan Infrastruktur",
                "Penyediaan Pelan Suratan Hakmilik Tetap", "Pendaftaran Suratan Hakmilik Tetap", "Penyediaan Pelan Suratan Hakmilik Sementara",
                "Pendaftaran Suratan Hakmilik Sementara", "Notis", "Jumlah"};
            BigDecimal [] amaunTuntutanList = {cukai, premium, upahUkur, kosInfrastruktur, pelanSuratanTetap, suratanTetap, pelanSuratanSementara, suratanSementara, notis, jumlah};

            for(int i = 0; i < 10; i++) {
                permohonantuntutkos = new PermohonanTuntutanKos();
                permohonantuntutkos.setInfoAudit(ia);
                permohonantuntutkos.setItem(itemList[i]);
                permohonantuntutkos.setAmaunTuntutan(amaunTuntutanList[i]);
                permohonantuntutkos.setPermohonan(permohonan);
                permohonantuntutkos.setCawangan(caw);
                pelupusanService.simpanPermohonanTuntutanKos(permohonantuntutkos);
            }
        }
        addSimpleMessage("Maklumat telah berjaya disimpan.");
        return new JSP("pelupusan/Borang_5APBMT.jsp").addParameter("tab", "true");
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

    public BigDecimal getCukai() {
        return cukai;
    }

    public void setCukai(BigDecimal cukai) {
        this.cukai = cukai;
    }

    public BigDecimal getJumlah() {
        return jumlah;
    }

    public void setJumlah(BigDecimal jumlah) {
        this.jumlah = jumlah;
    }

    public BigDecimal getKosInfrastruktur() {
        return kosInfrastruktur;
    }

    public void setKosInfrastruktur(BigDecimal kosInfrastruktur) {
        this.kosInfrastruktur = kosInfrastruktur;
    }

    public BigDecimal getNotis() {
        return notis;
    }

    public void setNotis(BigDecimal notis) {
        this.notis = notis;
    }

    public BigDecimal getPelanSuratanSementara() {
        return pelanSuratanSementara;
    }

    public void setPelanSuratanSementara(BigDecimal pelanSuratanSementara) {
        this.pelanSuratanSementara = pelanSuratanSementara;
    }

    public BigDecimal getPelanSuratanTetap() {
        return pelanSuratanTetap;
    }

    public void setPelanSuratanTetap(BigDecimal pelanSuratanTetap) {
        this.pelanSuratanTetap = pelanSuratanTetap;
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

    public BigDecimal getSuratanSementara() {
        return suratanSementara;
    }

    public void setSuratanSementara(BigDecimal suratanSementara) {
        this.suratanSementara = suratanSementara;
    }

    public BigDecimal getSuratanTetap() {
        return suratanTetap;
    }

    public void setSuratanTetap(BigDecimal suratanTetap) {
        this.suratanTetap = suratanTetap;
    }

    public BigDecimal getUpahUkur() {
        return upahUkur;
    }

    public void setUpahUkur(BigDecimal upahUkur) {
        this.upahUkur = upahUkur;
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

}
