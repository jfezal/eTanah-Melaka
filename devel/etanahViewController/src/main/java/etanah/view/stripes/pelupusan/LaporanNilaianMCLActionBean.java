/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.stripes.pelupusan;

import java.text.ParseException;
import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.dao.HakmilikDAO;
import etanah.dao.HakmilikPermohonanDAO;
import etanah.dao.KodTuntutDAO;
import etanah.dao.KodUOMDAO;
import etanah.dao.NoPtDAO;
import etanah.dao.PermohonanDAO;
import etanah.dao.PermohonanRujukanLuarDAO;
import etanah.model.FasaPermohonan;
import etanah.model.Hakmilik;
import etanah.model.HakmilikPermohonan;
import etanah.model.HakmilikPihakBerkepentingan;
import etanah.model.InfoAudit;
import etanah.model.KodBandarPekanMukim;
import etanah.model.KodCawangan;
import etanah.model.KodRujukan;
import etanah.model.KodUOM;
import etanah.model.NoPt;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.model.PermohonanRujukanLuar;
import etanah.model.PermohonanTuntutanKos;
import etanah.service.PelupusanService;
import etanah.view.etanahActionBeanContext;
import etanah.view.stripes.pelupusan.disClass.DisPermohonanHakmilikPihak;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Vector;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;
import org.apache.log4j.Logger;

/**
 *
 * @author wan.fairul
 */
@UrlBinding("/pelupusan/laporanNilaianMCL")
public class LaporanNilaianMCLActionBean extends AbleActionBean {

    private static Logger logger = Logger.getLogger(LaporanNilaianMCLActionBean.class);
    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    KodUOMDAO kodUOMDAO;
    @Inject
    HakmilikPermohonanDAO hakmilikPermohonanDAO;
    @Inject
    PermohonanRujukanLuarDAO permohonanRujukanLuarDAO;
    @Inject
    NoPtDAO noPtDAO;
    @Inject
    PelupusanService pelupusanService;
    @Inject
    HakmilikDAO hakmilikDAO ;
    @Inject
    KodTuntutDAO kodTuntutDAO ;
    private Permohonan permohonan;
    private Pengguna pengguna;
    private HakmilikPermohonan hakmilikPermohonan;
    private List hakmilikPermohonanList = new ArrayList<HakmilikPermohonan>();
    private PermohonanRujukanLuar permohonanRujukanLuar;
    private NoPt noPt;
    private BigDecimal nilai;
    private String tarikhRujukan;
    private Date trhRujukan;
    private String sebab;
    private boolean viewOnly;
    private KodBandarPekanMukim kodBPM;
    private KodUOM kodUL;
    String kodgunatanah;
    SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
    private Vector<DisPermohonanHakmilikPihak> disPermohonanaHakmilikPihakList = new Vector<DisPermohonanHakmilikPihak>();
    private HakmilikPihakBerkepentingan hakmilikPihak ;
    private Hakmilik hakmilik ;
    private int tahunP ;
    private int tahunS ;
    private int test ;
    private BigDecimal nilaiP ;
    private BigDecimal test4 ;
    private int bakiTempohPajakan ;
    private BigDecimal btp ;
    private BigDecimal btp1 ;
    private BigDecimal btp2 ;
    private BigDecimal np1 ;
    private BigDecimal np2 ;
    private BigDecimal np3 ;
    private BigDecimal np4 ;
    private BigDecimal np5 ;
    private BigDecimal np6 ;
    private BigDecimal nilaiPasaran1 ;
    private BigDecimal nilaiPasaran2 ;
    private BigDecimal nilaiPasaran3 ;
    private BigDecimal premium ;
    List<PermohonanTuntutanKos> senaraiMohonTuntutKos ;
    private PermohonanTuntutanKos mohonTuntutKos ;
    private FasaPermohonan fasaPermohonan ;
    private String thnhakmilik;
	

    @DefaultHandler()
    public Resolution showForm() {
        viewOnly = Boolean.FALSE;
        return new JSP("pelupusan/mcl/laporanNilaianMCL.jsp").addParameter("tab", "true");
    }
    public Resolution viewForm() {
        viewOnly = Boolean.TRUE;
        return new JSP("pelupusan/mcl/laporanNilaianMCL.jsp").addParameter("tab", "true");
    }
    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {

        pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
	thnhakmilik = pelupusanService.findMohonFasaByIdMohonIdPengguna(idPermohonan, "tahun_hm_daftar").getKeputusan().getKod();
        if(pelupusanService.findByIdMohonRujukLuar(idPermohonan) != null)
        {
            permohonanRujukanLuar = pelupusanService.findByIdMohonRujukLuar(idPermohonan).get(0);
            tarikhRujukan = dateFormat.format(permohonanRujukanLuar.getTarikhRujukan());
        }
        
        //one idPermohonan to one idHakmilik
        if (idPermohonan != null) {
            System.out.println("idPermohonan:-----------" + idPermohonan);
            permohonan = permohonanDAO.findById(idPermohonan);
            hakmilikPermohonanList = permohonan.getSenaraiHakmilik();
            HakmilikPermohonan hmTemp = new HakmilikPermohonan();
            if(!hakmilikPermohonanList.isEmpty()){
                hmTemp = (HakmilikPermohonan) hakmilikPermohonanList.get(0);
            }
            String idhakmilikpermohonan = getContext().getRequest().getParameter("idHakmilik");
                if(idhakmilikpermohonan == null){
                    hakmilikPermohonan = new HakmilikPermohonan();
                    hakmilikPermohonan = hakmilikPermohonanDAO.findById(hmTemp.getId());
                    if(hakmilikPermohonan.getNilaianJpph() != null)
                    nilai = hakmilikPermohonan.getNilaianJpph() ;
                }
                else if (idhakmilikpermohonan != null && !idhakmilikpermohonan.equals("")) {
                    hakmilikPermohonan = new HakmilikPermohonan();
                    hakmilikPermohonan = hakmilikPermohonanDAO.findById(new Long(idhakmilikpermohonan));
                    if(hakmilikPermohonan.getNilaianJpph() != null)
                    nilai = hakmilikPermohonan.getNilaianJpph() ;
                }
                if (thnhakmilik.equals("96")){
                    nilai = new BigDecimal(1000);
                }
            
        }
    }
    public Resolution searchHakmilik() throws ParseException {

        String idhakmilikpermohonan = getContext().getRequest().getParameter("idHakmilik");
        String editType = getContext().getRequest().getParameter("edit");
        hakmilikPermohonan = new HakmilikPermohonan();
        hakmilikPermohonan = hakmilikPermohonanDAO.findById(new Long(idhakmilikpermohonan));
        rehydrate();
        return new ForwardResolution("/WEB-INF/jsp/pelupusan/mcl/laporanNilaianMCL.jsp").addParameter("tab", "true");
    }


    public Resolution simpan3() throws ParseException {
        logger.debug("start simpan");
        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = permohonanDAO.findById(idPermohonan);

        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        KodCawangan caw = peng.getKodCawangan();
        InfoAudit ia = new InfoAudit();
        if(hakmilikPermohonan != null){
            hakmilikPermohonan.setNilaianJpph(nilai);
            ia = hakmilikPermohonan.getInfoAudit();
            ia.setDiKemaskiniOleh(peng);
            ia.setTarikhKemaskini(new java.util.Date());
            pelupusanService.saveOrUpdate(hakmilikPermohonan);
            
             //Add for mohon tuntut kos
            hakmilik = hakmilikDAO.findById(hakmilikPermohonan.getHakmilik().getIdHakmilik()) ;
            nilaiP = nilai ;
            tahunP = Integer.parseInt(hakmilik.getTarikhLuput().toString().substring(0, 4));
            
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
            Date date = new Date();
            tahunS = Integer.parseInt(sdf.format(date));
            bakiTempohPajakan =  tahunP - tahunS;
            if(nilaiP != null && bakiTempohPajakan != 0){
                String baki = "" + bakiTempohPajakan ;
                double a = Double.parseDouble(baki);
                nilaiPasaran1 = nilaiP.multiply(BigDecimal.valueOf(a)).divide(new BigDecimal(100));
                nilaiPasaran2 = nilaiP.subtract(nilaiPasaran1);
                nilaiPasaran3 = nilaiPasaran2.divide(new BigDecimal(4));
                np6 = nilaiPasaran3.divide(new BigDecimal(2));
                premium = np6 ;
            }
            
        }
        
        
        if(pelupusanService.findByIdMohonRujukLuar(idPermohonan) != null){
            permohonanRujukanLuar = pelupusanService.findByIdMohonRujukLuar(idPermohonan).get(0);
            ia = permohonanRujukanLuar.getInfoAudit();

            System.out.println("lame...");
            ia.setDiKemaskiniOleh(peng);
            ia.setTarikhKemaskini(new java.util.Date());

                permohonanRujukanLuar.setInfoAudit(ia);
                permohonanRujukanLuar.setNilai(nilai);
                trhRujukan = dateFormat.parse(tarikhRujukan);
                permohonanRujukanLuar.setTarikhRujukan(trhRujukan);
                pelupusanService.simpanRujukLuar(permohonanRujukanLuar);
        }else{
            permohonanRujukanLuar = new PermohonanRujukanLuar();
            permohonanRujukanLuar.setPermohonan(permohonan);

            System.out.println("baru...");
            ia = new InfoAudit();
            ia.setDimasukOleh(peng);
            ia.setTarikhMasuk(new Date());
            permohonanRujukanLuar.setInfoAudit(ia);

            KodRujukan kodRuj = new KodRujukan();
            kodRuj.setKod("FL");
            permohonanRujukanLuar.setKodRujukan(kodRuj);
            permohonanRujukanLuar.setNamaSidang("Nilaian");
            permohonanRujukanLuar.setNilai(nilai);
            permohonanRujukanLuar.setCawangan(caw);
            trhRujukan = dateFormat.parse(tarikhRujukan);
            permohonanRujukanLuar.setTarikhRujukan(trhRujukan);
            pelupusanService.simpanRujukLuar(permohonanRujukanLuar);
        }
        
        
        //Bayaran Premium
        mohonTuntutKos = new PermohonanTuntutanKos();
        mohonTuntutKos = pelupusanService.findMohonTuntutKosByIdPermohonanAndIdTuntutAndIdHMP(idPermohonan, "DISPRM",hakmilikPermohonan.getId());
        InfoAudit info = new InfoAudit();

        if (mohonTuntutKos == null) {
            mohonTuntutKos = new PermohonanTuntutanKos();
            info.setDimasukOleh(pengguna);
            info.setTarikhMasuk(new java.util.Date());
            mohonTuntutKos.setInfoAudit(info);
            mohonTuntutKos.setPermohonan(permohonan);
            mohonTuntutKos.setCawangan(permohonan.getCawangan());
            mohonTuntutKos.setItem("Premium Tanah");
            mohonTuntutKos.setAmaunTuntutan(premium);
            mohonTuntutKos.setHakmilikPermohonan(hakmilikPermohonan);
            mohonTuntutKos.setKodTuntut(kodTuntutDAO.findById("DISPRM"));
            mohonTuntutKos.setKodTransaksi(kodTuntutDAO.findById("DISPRM").getKodKewTrans());
            pelupusanService.simpanSavePermohonanTuntutanKos(mohonTuntutKos);
        } else {
            mohonTuntutKos.setInfoAudit(info);
            mohonTuntutKos.setPermohonan(permohonan);
            info.setDiKemaskiniOleh(pengguna);
            info.setTarikhKemaskini(new java.util.Date());
            mohonTuntutKos.setCawangan(permohonan.getCawangan());
            mohonTuntutKos.setItem("Premium Tanah");
            mohonTuntutKos.setAmaunTuntutan(premium);
            mohonTuntutKos.setHakmilikPermohonan(hakmilikPermohonan);
            mohonTuntutKos.setKodTuntut(kodTuntutDAO.findById("DISPRM"));
            mohonTuntutKos.setKodTransaksi(kodTuntutDAO.findById("DISPRM").getKodKewTrans());
            pelupusanService.simpanPermohonanTuntutanKos(mohonTuntutKos);
        }
        
        
       //Bayaran Hasil Tahun Pertama
        
        mohonTuntutKos = pelupusanService.findMohonTuntutKosByIdPermohonanAndIdTuntutAndIdHMP(idPermohonan, "DISTAX",hakmilikPermohonan.getId());

        if (mohonTuntutKos == null) {
            mohonTuntutKos = new PermohonanTuntutanKos();
            info.setDimasukOleh(pengguna);
            info.setTarikhMasuk(new java.util.Date());
            mohonTuntutKos.setInfoAudit(info);
            mohonTuntutKos.setPermohonan(permohonan);
            mohonTuntutKos.setCawangan(permohonan.getCawangan());
            mohonTuntutKos.setItem("Hasil Tahun Pertama");
            Double convert = 0.00 ;
            convert = Double.parseDouble(hakmilik.getCukaiSebenar().toString()) / 2 ;
            BigDecimal cukai = BigDecimal.valueOf(convert) ;
            mohonTuntutKos.setAmaunTuntutan(cukai); //Temporary because need to ask Pendaftaran
            mohonTuntutKos.setHakmilikPermohonan(hakmilikPermohonan);
            mohonTuntutKos.setKodTuntut(kodTuntutDAO.findById("DISTAX"));
            mohonTuntutKos.setKodTransaksi(kodTuntutDAO.findById("DISTAX").getKodKewTrans());
            pelupusanService.simpanSavePermohonanTuntutanKos(mohonTuntutKos);
        } else {
            mohonTuntutKos.setInfoAudit(info);
            mohonTuntutKos.setPermohonan(permohonan);
            info.setDiKemaskiniOleh(pengguna);
            info.setTarikhKemaskini(new java.util.Date());
            mohonTuntutKos.setCawangan(permohonan.getCawangan());
            mohonTuntutKos.setItem("Hasil Tahun Pertama");
            Double convert = 0.00 ;
            convert = Double.parseDouble(hakmilik.getCukaiSebenar().toString()) / 2 ;
            BigDecimal cukai = BigDecimal.valueOf(convert) ;
            mohonTuntutKos.setAmaunTuntutan(cukai); //Temporary because need to ask Pendaftaran
            mohonTuntutKos.setHakmilikPermohonan(hakmilikPermohonan);
            mohonTuntutKos.setKodTuntut(kodTuntutDAO.findById("DISTAX"));
            mohonTuntutKos.setKodTransaksi(kodTuntutDAO.findById("DISTAX").getKodKewTrans());
            pelupusanService.simpanPermohonanTuntutanKos(mohonTuntutKos);
        }
        
        
        //Bayaran Pendaftaran Hakmilik Kekal or Hakmilik Sementara
        fasaPermohonan = pelupusanService.findMohonFasaByIdMohonIdPengguna(permohonan.getIdPermohonan(), "kemasukan");
        if (hakmilik != null) {
            if (fasaPermohonan.getKeputusan().getKod().equals("YT")) { //Kekal
                mohonTuntutKos = pelupusanService.findMohonTuntutKosByIdPermohonanAndIdTuntutAndIdHMP(idPermohonan, "DISFT", hakmilikPermohonan.getId());

                if (mohonTuntutKos == null) {
                    mohonTuntutKos = new PermohonanTuntutanKos();
                    info.setDimasukOleh(pengguna);
                    info.setTarikhMasuk(new java.util.Date());
                    mohonTuntutKos.setInfoAudit(info);
                    mohonTuntutKos.setPermohonan(permohonan);
                    mohonTuntutKos.setCawangan(permohonan.getCawangan());
                    mohonTuntutKos.setItem("Pendaftaran Hakmilik Kekal");
                    mohonTuntutKos.setAmaunTuntutan(new BigDecimal(30)); //Temporary because need to ask Pendaftaran
                    mohonTuntutKos.setHakmilikPermohonan(hakmilikPermohonan);
                    mohonTuntutKos.setKodTuntut(kodTuntutDAO.findById("DISFT"));
                    mohonTuntutKos.setKodTransaksi(kodTuntutDAO.findById("DISFT").getKodKewTrans());
                    pelupusanService.simpanSavePermohonanTuntutanKos(mohonTuntutKos);
                } else {
                    mohonTuntutKos.setInfoAudit(info);
                    mohonTuntutKos.setPermohonan(permohonan);
                    info.setDiKemaskiniOleh(pengguna);
                    info.setTarikhKemaskini(new java.util.Date());
                    mohonTuntutKos.setCawangan(permohonan.getCawangan());
                    mohonTuntutKos.setItem("Pendaftaran Hakmilik Kekal");
                    mohonTuntutKos.setAmaunTuntutan(new BigDecimal(30)); //Temporary because need to ask Pendaftaran
                    mohonTuntutKos.setHakmilikPermohonan(hakmilikPermohonan);
                    mohonTuntutKos.setKodTuntut(kodTuntutDAO.findById("DISFT"));
                    mohonTuntutKos.setKodTransaksi(kodTuntutDAO.findById("DISFT").getKodKewTrans());
                    pelupusanService.simpanPermohonanTuntutanKos(mohonTuntutKos);
                }
            } else if (fasaPermohonan.getKeputusan().getKod().equals("YQ")) { //Sementara
                mohonTuntutKos = pelupusanService.findMohonTuntutKosByIdPermohonanAndIdTuntutAndIdHMP(idPermohonan, "DISQT", hakmilikPermohonan.getId());

                if (mohonTuntutKos == null) {
                    mohonTuntutKos = new PermohonanTuntutanKos();
                    info.setDimasukOleh(pengguna);
                    info.setTarikhMasuk(new java.util.Date());
                    mohonTuntutKos.setInfoAudit(info);
                    mohonTuntutKos.setPermohonan(permohonan);
                    mohonTuntutKos.setCawangan(permohonan.getCawangan());
                    mohonTuntutKos.setItem("Pendaftaran Hakmilik Sementara");
                    mohonTuntutKos.setAmaunTuntutan(new BigDecimal(30)); //Temporary because need to ask Pendaftaran
                    mohonTuntutKos.setHakmilikPermohonan(hakmilikPermohonan);
                    mohonTuntutKos.setKodTuntut(kodTuntutDAO.findById("DISQT"));
                    mohonTuntutKos.setKodTransaksi(kodTuntutDAO.findById("DISQT").getKodKewTrans());
                    pelupusanService.simpanSavePermohonanTuntutanKos(mohonTuntutKos);
                } else {
                    mohonTuntutKos.setInfoAudit(info);
                    mohonTuntutKos.setPermohonan(permohonan);
                    info.setDiKemaskiniOleh(pengguna);
                    info.setTarikhKemaskini(new java.util.Date());
                    mohonTuntutKos.setCawangan(permohonan.getCawangan());
                    mohonTuntutKos.setItem("Pendaftaran Hakmilik Sementara");
                    mohonTuntutKos.setAmaunTuntutan(new BigDecimal(30)); //Temporary because need to ask Pendaftaran
                    mohonTuntutKos.setHakmilikPermohonan(hakmilikPermohonan);
                    mohonTuntutKos.setKodTuntut(kodTuntutDAO.findById("DISQT"));
                    mohonTuntutKos.setKodTransaksi(kodTuntutDAO.findById("DISQT").getKodKewTrans());
                    pelupusanService.simpanPermohonanTuntutanKos(mohonTuntutKos);
                }
            }
        }
        
        //Bayaran Pelan Geran
        
        mohonTuntutKos = pelupusanService.findMohonTuntutKosByIdPermohonanAndIdTuntutAndIdHMP(idPermohonan, "DISPEL",hakmilikPermohonan.getId());

        if (mohonTuntutKos == null) {
            mohonTuntutKos = new PermohonanTuntutanKos();
            info.setDimasukOleh(pengguna);
            info.setTarikhMasuk(new java.util.Date());
            mohonTuntutKos.setInfoAudit(info);
            mohonTuntutKos.setPermohonan(permohonan);
            mohonTuntutKos.setCawangan(permohonan.getCawangan());
            mohonTuntutKos.setItem("Pelan Geran");
            mohonTuntutKos.setAmaunTuntutan(new BigDecimal(10)); //Temporary because need to ask Pendaftaran
            mohonTuntutKos.setHakmilikPermohonan(hakmilikPermohonan);
            mohonTuntutKos.setKodTuntut(kodTuntutDAO.findById("DISPEL"));
            mohonTuntutKos.setKodTransaksi(kodTuntutDAO.findById("DISPEL").getKodKewTrans());
            pelupusanService.simpanSavePermohonanTuntutanKos(mohonTuntutKos);
        } else {
            mohonTuntutKos.setInfoAudit(info);
            mohonTuntutKos.setPermohonan(permohonan);
            info.setDiKemaskiniOleh(pengguna);
            info.setTarikhKemaskini(new java.util.Date());
            mohonTuntutKos.setCawangan(permohonan.getCawangan());
            mohonTuntutKos.setItem("Pelan Geran");
            mohonTuntutKos.setAmaunTuntutan(new BigDecimal(10)); //Temporary because need to ask Pendaftaran
            mohonTuntutKos.setHakmilikPermohonan(hakmilikPermohonan);
            mohonTuntutKos.setKodTuntut(kodTuntutDAO.findById("DISPEL"));
            mohonTuntutKos.setKodTransaksi(kodTuntutDAO.findById("DISPEL").getKodKewTrans());
            pelupusanService.simpanPermohonanTuntutanKos(mohonTuntutKos);
        }

        
        
        addSimpleMessage("Maklumat telah berjaya disimpan.");
        return new JSP("pelupusan/mcl/laporanNilaianMCL.jsp").addParameter("tab", "true");

        
    }

    public Pengguna getPengguna() {
        return pengguna;
    }

    public void setPengguna(Pengguna pengguna) {
        this.pengguna = pengguna;
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

    public KodUOM getKodUL() {
        return kodUL;
    }

    public void setKodUL(KodUOM kodUL) {
        this.kodUL = kodUL;
    }

    public String getSebab() {
        return sebab;
    }

    public void setSebab(String sebab) {
        this.sebab = sebab;
    }
	
	public String getthnhakmilik() {
		return thnhakmilik;
	}
	
	public void setthnhakmilik(String thnhakmilik) {
		this.thnhakmilik = thnhakmilik;
	}

    public KodBandarPekanMukim getKodBPM() {
        return kodBPM;
    }

    public void setKodBPM(KodBandarPekanMukim kodBPM) {
        this.kodBPM = kodBPM;
    }

    public BigDecimal getNilai() {
        return nilai;
    }

    public void setNilai(BigDecimal nilai) {
        this.nilai = nilai;
    }

    public PermohonanRujukanLuar getPermohonanRujukanLuar() {
        return permohonanRujukanLuar;
    }

    public void setPermohonanRujukanLuar(PermohonanRujukanLuar permohonanRujukanLuar) {
        this.permohonanRujukanLuar = permohonanRujukanLuar;
    }

    public String getTarikhRujukan() {
        return tarikhRujukan;
    }

    public void setTarikhRujukan(String tarikhRujukan) {
        this.tarikhRujukan = tarikhRujukan;
    }

    public NoPt getNoPt() {
        return noPt;
    }

    public void setNoPt(NoPt noPt) {
        this.noPt = noPt;
    }

    public String getKodgunatanah() {
        return kodgunatanah;
    }

    public void setKodgunatanah(String kodgunatanah) {
        this.kodgunatanah = kodgunatanah;
    }

    public boolean isViewOnly() {
        return viewOnly;
    }

    public void setViewOnly(boolean viewOnly) {
        this.viewOnly = viewOnly;
    }

    public Vector<DisPermohonanHakmilikPihak> getDisPermohonanaHakmilikPihakList() {
        return disPermohonanaHakmilikPihakList;
    }

    public void setDisPermohonanaHakmilikPihakList(Vector<DisPermohonanHakmilikPihak> disPermohonanaHakmilikPihakList) {
        this.disPermohonanaHakmilikPihakList = disPermohonanaHakmilikPihakList;
    }

    public List getHakmilikPermohonanList() {
        return hakmilikPermohonanList;
    }

    public void setHakmilikPermohonanList(List hakmilikPermohonanList) {
        this.hakmilikPermohonanList = hakmilikPermohonanList;
    }

    public int getBakiTempohPajakan() {
        return bakiTempohPajakan;
    }

    public void setBakiTempohPajakan(int bakiTempohPajakan) {
        this.bakiTempohPajakan = bakiTempohPajakan;
    }

    public BigDecimal getBtp() {
        return btp;
    }

    public void setBtp(BigDecimal btp) {
        this.btp = btp;
    }

    public BigDecimal getBtp1() {
        return btp1;
    }

    public void setBtp1(BigDecimal btp1) {
        this.btp1 = btp1;
    }

    public BigDecimal getBtp2() {
        return btp2;
    }

    public void setBtp2(BigDecimal btp2) {
        this.btp2 = btp2;
    }

    public Hakmilik getHakmilik() {
        return hakmilik;
    }

    public void setHakmilik(Hakmilik hakmilik) {
        this.hakmilik = hakmilik;
    }

    public PermohonanTuntutanKos getMohonTuntutKos() {
        return mohonTuntutKos;
    }

    public void setMohonTuntutKos(PermohonanTuntutanKos mohonTuntutKos) {
        this.mohonTuntutKos = mohonTuntutKos;
    }

    public BigDecimal getNilaiP() {
        return nilaiP;
    }

    public void setNilaiP(BigDecimal nilaiP) {
        this.nilaiP = nilaiP;
    }

    public BigDecimal getNilaiPasaran1() {
        return nilaiPasaran1;
    }

    public void setNilaiPasaran1(BigDecimal nilaiPasaran1) {
        this.nilaiPasaran1 = nilaiPasaran1;
    }

    public BigDecimal getNilaiPasaran2() {
        return nilaiPasaran2;
    }

    public void setNilaiPasaran2(BigDecimal nilaiPasaran2) {
        this.nilaiPasaran2 = nilaiPasaran2;
    }

    public BigDecimal getNilaiPasaran3() {
        return nilaiPasaran3;
    }

    public void setNilaiPasaran3(BigDecimal nilaiPasaran3) {
        this.nilaiPasaran3 = nilaiPasaran3;
    }

    public BigDecimal getNp1() {
        return np1;
    }

    public void setNp1(BigDecimal np1) {
        this.np1 = np1;
    }

    public BigDecimal getNp2() {
        return np2;
    }

    public void setNp2(BigDecimal np2) {
        this.np2 = np2;
    }

    public BigDecimal getNp3() {
        return np3;
    }

    public void setNp3(BigDecimal np3) {
        this.np3 = np3;
    }

    public BigDecimal getNp4() {
        return np4;
    }

    public void setNp4(BigDecimal np4) {
        this.np4 = np4;
    }

    public BigDecimal getNp5() {
        return np5;
    }

    public void setNp5(BigDecimal np5) {
        this.np5 = np5;
    }

    public BigDecimal getNp6() {
        return np6;
    }

    public void setNp6(BigDecimal np6) {
        this.np6 = np6;
    }

    public int getTahunP() {
        return tahunP;
    }

    public void setTahunP(int tahunP) {
        this.tahunP = tahunP;
    }

    public int getTahunS() {
        return tahunS;
    }

    public void setTahunS(int tahunS) {
        this.tahunS = tahunS;
    }

    public FasaPermohonan getFasaPermohonan() {
        return fasaPermohonan;
    }

    public void setFasaPermohonan(FasaPermohonan fasaPermohonan) {
        this.fasaPermohonan = fasaPermohonan;
    }
    
    
}

