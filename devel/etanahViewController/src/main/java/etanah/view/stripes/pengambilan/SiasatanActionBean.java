/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.stripes.pengambilan;

/**
 *
 * @author massita
 */
import net.sourceforge.stripes.action.ForwardResolution;
import org.apache.commons.lang.StringUtils;
import able.stripes.AbleActionBean;
import able.stripes.JSP;
import java.text.ParseException;
import com.google.inject.Inject;
import etanah.dao.KodCawanganDAO;
import etanah.dao.PihakDAO;
import etanah.dao.PemohonDAO;
import etanah.dao.PermohonanDAO;
import etanah.dao.PermohonanRujukanLuarDAO;
import etanah.dao.PermohonanTuntutanKosDAO;
import etanah.dao.HakmilikDAO;
import etanah.dao.SyaratPendudukanDAO;
//import etanah.dao.HakmilikPihakBerkepentingan;
import etanah.dao.HakmilikPermohonanDAO;
import etanah.dao.HakmilikPihakBerkepentinganDAO;
import etanah.service.common.PemohonService;
import etanah.model.*;
import etanah.model.KodCawangan;
import etanah.model.Pengguna;
import etanah.model.PermohonanTuntutanKos;
import etanah.model.Hakmilik;
import etanah.model.HakmilikPermohonan;
import etanah.model.HakmilikPihakBerkepentingan;
import etanah.model.SyaratPendudukan;
import etanah.service.PengambilanService;
import etanah.view.etanahActionBeanContext;
import java.util.Date;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.RedirectResolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.controller.LifecycleStage;
import org.apache.log4j.Logger;
import java.util.List;
import java.math.BigDecimal;

@UrlBinding("/pengambilan/siasatan")
public class SiasatanActionBean extends AbleActionBean {

    private static Logger logger = Logger.getLogger(SiasatanActionBean.class);
    @Inject
    PermohonanRujukanLuarDAO permohonanRujukanLuarDAO;
    @Inject
    PermohonanTuntutanKosDAO permohonanTuntutanKosDAO;
    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    PengambilanService pengambilanService;
    @Inject
    PemohonDAO pemohonDAO;
    @Inject
    KodCawanganDAO kodCawanganDAO;
    @Inject
    PihakDAO pihakDAO;
    @Inject
    PemohonService pemohonService;
    @Inject
    HakmilikDAO hakmilikDAO;
    @Inject
    HakmilikPihakBerkepentinganDAO hakmilikPihakBerkepentinganDAO;
    @Inject
    HakmilikPermohonanDAO hakmilikPermohonanDAO;
    @Inject
    SyaratPendudukanDAO syaratPendudukanDAO;

    private List<Pemohon> pemohonList;
    private Permohonan permohonan;
    private SyaratPendudukan syaratPendudukan;
    private PermohonanRujukanLuar permohonanRujukanLuar;
    private List<PermohonanRujukanLuar> permohonanRujukanLuarList;
    private String idHakmilik;
    private Long idPihak;
    private Pemohon pemohon;
    private Pihak pihak;
    private List<Pihak> getSenaraiPihak;
    private KodCawangan cawangan;
    private String item;
    boolean kerosakan;
    private String idPermohonan;
    private Hakmilik hakmilik;
    private HakmilikPihakBerkepentingan hakmilikPihakBerkepentingan;
    private List<HakmilikPihakBerkepentingan> senaraiPihakBerkepentingan;
    private List<HakmilikPermohonan> senaraiHakmilikPermohonan;
    private KodJenisPihakBerkepentingan jenis;
    private boolean showMaklumatPemilik;
    private BigDecimal jumlahPampasan = new BigDecimal(0.00);
    private String idSyaratPendudukan;
    private char caraBayaran;
    private String perincianKerosakan;

    @DefaultHandler
    public Resolution showForm() {
//          getContext().getRequest().setAttribute("jumlahPampasan", Boolean.TRUE);
//           getContext().getRequest().setAttribute("caraBayaran", Boolean.TRUE);
//          getContext().getRequest().setAttribute("perincianKerosakan", Boolean.TRUE);
        return new JSP("pengambilan/negerisembilan/haklalulalangpersendirian/siasatanHLL.jsp").addParameter("tab", "true");
    }

      public Resolution simpan() throws ParseException {
        logger.debug("start simpan");
        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = permohonanDAO.findById(idPermohonan);
        cawangan = permohonan.getCawangan();

       // caraBayaran = getContext().getRequest().getParameter("caraBayaran").charAt(1);
          System.out.println("----------caraBayaran------"+caraBayaran);
          System.out.println("----------jumlahPampasan------"+jumlahPampasan);

        if (syaratPendudukan == null) {
            syaratPendudukan = new SyaratPendudukan();
        }
          boolean flag=true;

         try{
             System.out.println("--jumlahPampasan-try----"+jumlahPampasan);



        if (jumlahPampasan == null) {
            System.out.println("--jumlahPampasan-try--if--"+jumlahPampasan);
              flag = false;
//            syaratPendudukan.setTarikhSiasat(null);
            addSimpleError("Sila Masukkan Jumlah Pampasan.");

        }else if(jumlahPampasan == BigDecimal.ZERO){
             System.out.println("--jumlahPampasan-try--if2--"+jumlahPampasan);
              flag = false;
//            syaratPendudukan.setTarikhSiasat(null);
            addSimpleError("Sila Masukkan Jumlah Pampasan.");
        }
        else  if(caraBayaran == 's' || caraBayaran == 'a' ) {
//        else if(getContext().getRequest().getParameter("caraBayaran").isEmpty()) {
        }else{
              flag = false;
              addSimpleError("Sila Masukkan Cara Bayaran.");
        }
          if(perincianKerosakan == null) {
              flag=false;
            addSimpleError("Sila Masukkan Perincian Kerosakan."); 
        }
         }catch(NumberFormatException e){
             System.out.println("--catch-----"+jumlahPampasan);
             jumlahPampasan = BigDecimal.ZERO;
             flag = false;
             addSimpleError("Sila Masukkan Jumlah Pampasan.");
         }

         if(flag){
            syaratPendudukan.setPermohonan(permohonan);
            syaratPendudukan.setJumlahPampasan(jumlahPampasan);
            syaratPendudukan.setCaraBayaran(caraBayaran);
            syaratPendudukan.setPerincianKerosakan(perincianKerosakan);
            InfoAudit ia = syaratPendudukan.getInfoAudit();
        if (ia == null) {
            ia = new InfoAudit();
            ia.setDimasukOleh(peng);
            ia.setTarikhMasuk(new Date());
            syaratPendudukan.setInfoAudit(ia);
        } else {
            ia.setDiKemaskiniOleh(peng);
            ia.setTarikhKemaskini(new java.util.Date());
            syaratPendudukan.setInfoAudit(ia);
        }
        pengambilanService.saveSyaratPendudukan(syaratPendudukan);
        addSimpleMessage("Maklumat telah berjaya disimpan.");
        }

        
         getContext().getRequest().setAttribute("jumlahPampasan", Boolean.TRUE);
        getContext().getRequest().setAttribute("caraBayaran", Boolean.TRUE);
        getContext().getRequest().setAttribute("perincianKerosakan", Boolean.TRUE);
        return new JSP("pengambilan/negerisembilan/haklalulalangpersendirian/siasatanHLL.jsp").addParameter("popup", "true");
    }

    public Resolution refreshpage() {
        rehydrate();
        return new RedirectResolution(SiasatanActionBean.class, "locate");
    }

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {
        Pengguna pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = permohonanDAO.findById(idPermohonan);
//        idSyaratPendudukan = getContext().getRequest().getParameter("idSyaratPendudukan");
        if (idPermohonan != null) {
            logger.debug("idPermohonan :" + idPermohonan);
            syaratPendudukan = pengambilanService.findByIdPermohonan4SyaratPendudukan(idPermohonan);
            if(syaratPendudukan != null) {
                jumlahPampasan = syaratPendudukan.getJumlahPampasan();
                caraBayaran = syaratPendudukan.getCaraBayaran();
                perincianKerosakan = syaratPendudukan.getPerincianKerosakan();
            }
            
        }
    }

    public Pemohon getPemohon() {
        return pemohon;
    }

    public void setPemohon(Pemohon pemohon) {
        this.pemohon = pemohon;
    }

    public List<PermohonanRujukanLuar> getPermohonanRujukanLuarList() {
        return permohonanRujukanLuarList;
    }

    public void setPermohonanRujukanLuarList(List<PermohonanRujukanLuar> permohonanRujukanLuarList) {
        this.permohonanRujukanLuarList = permohonanRujukanLuarList;
    }

    public Permohonan getPermohonan() {
        return permohonan;
    }

    public void setPermohonan(Permohonan permohonan) {
        this.permohonan = permohonan;
    }

    public Hakmilik getHakmilik() {
        return hakmilik;
    }

    public void setHakmilik(Hakmilik hakmilik) {
        this.hakmilik = hakmilik;
    }

    public HakmilikPihakBerkepentingan getHakmilikPihakBerkepentingan() {
        return hakmilikPihakBerkepentingan;
    }

    public void setHakmilikPihakBerkepentingan(HakmilikPihakBerkepentingan hakmilikPihakBerkepentingan) {
        this.hakmilikPihakBerkepentingan = hakmilikPihakBerkepentingan;
    }

    public List<HakmilikPihakBerkepentingan> getSenaraiPihakBerkepentingan() {
        return senaraiPihakBerkepentingan;
    }

    public void setSenaraiPihakBerkepentingan(List<HakmilikPihakBerkepentingan> senaraiPihakBerkepentingan) {
        this.senaraiPihakBerkepentingan = senaraiPihakBerkepentingan;
    }

    public List<HakmilikPermohonan> getSenaraiHakmilikPermohonan() {
        return senaraiHakmilikPermohonan;
    }

    public KodJenisPihakBerkepentingan getJenis() {
        return jenis;
    }

    public void setJenis(KodJenisPihakBerkepentingan jenis) {
        this.jenis = jenis;
    }

    public void setSenaraiHakmilikPermohonan(List<HakmilikPermohonan> senaraiHakmilikPermohonan) {
        this.senaraiHakmilikPermohonan = senaraiHakmilikPermohonan;
    }

    public String getIdHakmilik() {
        return idHakmilik;
    }

    public void setIdHakmilik(String idHakmilik) {
        this.idHakmilik = idHakmilik;
    }

    public Long getIdPihak() {
        return idPihak;
    }

    public void setIdPihak(Long idPihak) {
        this.idPihak = idPihak;
    }

    public PermohonanRujukanLuar getPermohonanRujukanLuar() {
        return permohonanRujukanLuar;
    }

    public void setPermohonanRujukanLuar(PermohonanRujukanLuar permohonanRujukanLuar) {
        this.permohonanRujukanLuar = permohonanRujukanLuar;
    }

    public KodCawangan getCawangan() {
        return cawangan;
    }

    public void setCawangan(KodCawangan cawangan) {
        this.cawangan = cawangan;
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


    public List<Pemohon> getPemohonList() {
        return pemohonList;
    }

    public void setPemohonList(List<Pemohon> pemohonList) {
        this.pemohonList = pemohonList;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public List<Pihak> getGetSenaraiPihak() {
        return getSenaraiPihak;
    }

    public void setGetSenaraiPihak(List<Pihak> getSenaraiPihak) {
        this.getSenaraiPihak = getSenaraiPihak;
    }

    public boolean isShowMaklumatPemilik() {
        return showMaklumatPemilik;
    }

    public void setShowMaklumatPemilik(boolean showMaklumatPemilik) {
        this.showMaklumatPemilik = showMaklumatPemilik;
    }

    public BigDecimal getJumlahPampasan() {
        return jumlahPampasan;
    }

    public void setJumlahPampasan(BigDecimal jumlahPampasan) {
        this.jumlahPampasan = jumlahPampasan;
    }

    public String getIdSyaratPendudukan() {
        return idSyaratPendudukan;
    }

    public void setIdSyaratPendudukan(String idSyaratPendudukan) {
        this.idSyaratPendudukan = idSyaratPendudukan;
    }

    public SyaratPendudukan getSyaratPendudukan() {
        return syaratPendudukan;
    }

    public void setSyaratPendudukan(SyaratPendudukan syaratPendudukan) {
        this.syaratPendudukan = syaratPendudukan;
    }


    public char getCaraBayaran() {
        return caraBayaran;
    }

    public void setCaraBayaran(char caraBayaran) {
        this.caraBayaran = caraBayaran;
    }

    public boolean isKerosakan() {
        return kerosakan;
    }

    public void setKerosakan(boolean kerosakan) {
        this.kerosakan = kerosakan;
    }

    public String getPerincianKerosakan() {
        return perincianKerosakan;
    }

    public void setPerincianKerosakan(String perincianKerosakan) {
        this.perincianKerosakan = perincianKerosakan;
    }

}
