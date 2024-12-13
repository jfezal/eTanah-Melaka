package etanah.view.stripes.pembangunan;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.dao.HakmilikPermohonanDAO;
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
import java.text.ParseException;
//import java.util.ArrayList;
import java.text.SimpleDateFormat;
import java.util.Date;
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
@UrlBinding("/pembangunan/melaka/nilaianTanahDanPremiumBaruNotis5A")
public class NilaianTanahDanPremiumBaruNotis5AActionBean extends AbleActionBean {
private static final Logger LOG = Logger.getLogger(NilaianTanahDanPremiumBaruActionBean.class);
    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    KodTuntutDAO kodTuntutDAO;
    @Inject
    PermohonanTuntutanKosDAO permohonanTuntutanKosDAO;
    @Inject
    KodTransaksiTuntutDAO kodTransaksiTuntutDAO;
    @Inject
    PembangunanService pembangunanServ;
    @Inject
    etanah.Configuration conf;
    private Permohonan permohonan;
    private BigDecimal nilaianTanah;
    private BigDecimal premium;
    private PermohonanTuntutanKos permohonantuntutkos;
    private List<PermohonanTuntutanKos> listtuntutankos;
    private SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    private String tarikhNilaian;
    private String nomborRujukan;
    private String tarikhNilaianJPPH;

    @DefaultHandler
    public Resolution showForm(){
        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        return new JSP("pembangunan/melaka/nilaianTanah_dan_PremiumBaru_notis5A.jsp").addParameter("tab", "true");
    }

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {

        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = permohonanDAO.findById(idPermohonan);
        listtuntutankos =  pembangunanServ.findTuntutByIdMohon(idPermohonan);

        if(!(listtuntutankos.isEmpty())){
            for(int i=0; i < listtuntutankos.size(); i++){
                permohonantuntutkos = new PermohonanTuntutanKos();
                permohonantuntutkos = listtuntutankos.get(i);
                if(permohonantuntutkos.getKodTuntut() != null){
//                    if(permohonantuntutkos.getKodTuntut().getNama().equals("Premium")){
                    if(permohonantuntutkos.getKodTuntut().getKod().equals("DEV04")){
                        premium = permohonantuntutkos.getAmaunTuntutan();
                    }
                }
            }
        }

      HakmilikPermohonan hp = new HakmilikPermohonan();
      List<HakmilikPermohonan>  senaraiHakmilikPermohonan = permohonan.getSenaraiHakmilik();
      if(senaraiHakmilikPermohonan!=null && senaraiHakmilikPermohonan.size() > 0 ){
        hp = senaraiHakmilikPermohonan.get(0);
      }
      nilaianTanah = hp.getNilaianJpph();
      if(hp.getTarikhNilaianJPPH()!=null){
      tarikhNilaianJPPH = sdf.format(hp.getTarikhNilaianJPPH());
      }
      nomborRujukan = hp.getNomborRujukan();
      try{
        tarikhNilaian = sdf.format(hp.getTarikhAwalDaftarGeran());
        LOG.info("---rehydrade------tarikhNilaian-----------:"+tarikhNilaian);
      }catch(Exception e){
          e.printStackTrace();
      }
    }

    public Resolution viewPremiumBaru(){
        return new JSP("pembangunan/melaka/nilaianTanah_dan_PremiumBaru_notis5A.jsp").addParameter("tab", "true");
    }




    public Resolution simpan() throws ParseException{
        Pengguna pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        KodCawangan caw = pengguna.getKodCawangan();
        InfoAudit ia = new InfoAudit();
        ia.setDimasukOleh(pengguna);
        ia.setTarikhMasuk(new java.util.Date());

        if(premium == null){
            premium = new BigDecimal(0.00);
        }
        if(nilaianTanah == null){
            nilaianTanah = new BigDecimal(0.00);
        }

        String kadarRayuan = permohonan.getNoMahkamah();
        if(kadarRayuan != null && nilaianTanah != null){
            double test = Double.parseDouble(kadarRayuan) ;
            BigDecimal a = BigDecimal.valueOf(test);
            LOG.info("a:"+a);
            BigDecimal b = a.divide(new BigDecimal(100));
            LOG.info("b:"+b);
            premium = nilaianTanah.multiply(b);
        }



        if(!(listtuntutankos.isEmpty())){
              for(int i=0; i < listtuntutankos.size(); i++){
                permohonantuntutkos = new PermohonanTuntutanKos();
                permohonantuntutkos = listtuntutankos.get(i);
                if(permohonantuntutkos.getKodTuntut() != null){
//                    if(permohonantuntutkos.getKodTuntut().getNama().equals("Premium")){
                      if(permohonantuntutkos.getKodTuntut().getKod().equals("DEV04")){
                        permohonantuntutkos.setAmaunTuntutan(premium);
                    }
                    ia = permohonantuntutkos.getInfoAudit();
                    ia.setDiKemaskiniOleh(pengguna);
                    ia.setTarikhKemaskini(new java.util.Date());
                    permohonantuntutkos.setInfoAudit(ia);
                    permohonantuntutkos.setPermohonan(permohonan);
                    permohonantuntutkos.setCawangan(caw);
                    pembangunanServ.simpanPermohonanTuntutanKos(permohonantuntutkos);
                }
            }
        }else {
                // Added new code
                LOG.debug("---------KodTransaksiTuntut---------:");
                KodTuntut kodTuntut = new KodTuntut();
                kodTuntut = kodTuntutDAO.findById("DEV04");
                permohonantuntutkos = new PermohonanTuntutanKos();
                permohonantuntutkos.setInfoAudit(ia);
                permohonantuntutkos.setKodTuntut(kodTuntut);
                permohonantuntutkos.setItem(kodTuntut.getNama());
                permohonantuntutkos.setAmaunTuntutan(premium);
                permohonantuntutkos.setPermohonan(permohonan);
                permohonantuntutkos.setCawangan(caw);
                permohonantuntutkos.setKodTransaksi(kodTuntut.getKodKewTrans());
                pembangunanServ.simpanPermohonanTuntutanKos(permohonantuntutkos);

        }

      HakmilikPermohonan hp = new HakmilikPermohonan();
      List<HakmilikPermohonan>  senaraiHakmilikPermohonan = permohonan.getSenaraiHakmilik();
      if(senaraiHakmilikPermohonan!=null && senaraiHakmilikPermohonan.size() > 0 ){
        hp = senaraiHakmilikPermohonan.get(0);
      }

      if(hp!=null){
          hp.setNilaianJpph(nilaianTanah);
          hp.setNomborRujukan(nomborRujukan);
          hp.setKadarPremium(premium);
          
          if(tarikhNilaianJPPH != null){
             hp.setTarikhNilaianJPPH(sdf.parse(tarikhNilaianJPPH));
          }

          try{              
              hp.setTarikhAwalDaftarGeran((Date)sdf.parse(tarikhNilaian));
          }catch(Exception e){
              e.printStackTrace();
          }
          LOG.info("-------tarikhNilaian-----------:"+tarikhNilaian);
          pembangunanServ.simpanHakmilikPermohonan(hp);
      }
        addSimpleMessage("Maklumat telah berjaya disimpan.");
        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        return new JSP("pembangunan/melaka/nilaianTanah_dan_PremiumBaru_notis5A.jsp").addParameter("tab", "true");
    }

    public Permohonan getPermohonan() {
        return permohonan;
    }

    public void setPermohonan(Permohonan permohonan) {
        this.permohonan = permohonan;
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

    public List<PermohonanTuntutanKos> getListtuntutankos() {
        return listtuntutankos;
    }

    public void setListtuntutankos(List<PermohonanTuntutanKos> listtuntutankos) {
        this.listtuntutankos = listtuntutankos;
    }

    public BigDecimal getNilaianTanah() {
        return nilaianTanah;
    }

    public void setNilaianTanah(BigDecimal nilaianTanah) {
        this.nilaianTanah = nilaianTanah;
    }

    public String getTarikhNilaian() {
        return tarikhNilaian;
    }

    public void setTarikhNilaian(String tarikhNilaian) {
        this.tarikhNilaian = tarikhNilaian;
    }

    public String getNomborRujukan() {
        return nomborRujukan;
    }

    public void setNomborRujukan(String nomborRujukan) {
        this.nomborRujukan = nomborRujukan;
    }

}
