/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package etanah.view.stripes.pengambilan;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.dao.HakmilikDAO;
import etanah.dao.KodUOMDAO;
import etanah.dao.PenggunaDAO;
import etanah.dao.PermohonanDAO;
import etanah.model.*;
import etanah.model.ambil.HakmilikPerbicaraan;
import etanah.model.ambil.Penilaian;
import etanah.model.ambil.PerbicaraanKehadiran;
import etanah.service.PengambilanService;
import etanah.service.common.NotisPenerimaanService;
import etanah.service.daftar.PembetulanService;
import etanah.view.etanahActionBeanContext;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;
import net.sourceforge.stripes.util.bean.ParseException;
import java.text.SimpleDateFormat;
import org.apache.log4j.Logger;

/**
 *
 * @author Rajesh
 */
@UrlBinding("/pengambilan/siasatanletrik")
public class NotaSiasatanLektrikActionBean extends AbleActionBean {
    private static final Logger logger = Logger.getLogger(NotaSiasatanLektrikActionBean.class);
    @Inject
    PenggunaDAO penggunaDAO;
    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    HakmilikDAO hakmilikDAO;
    @Inject
    KodUOMDAO kodUOMDAO;
    @Inject
    private PembetulanService pembetulanService;
    @Inject
    NotisPenerimaanService notisPenerimaanService;
    @Inject
    PengambilanService pengambilanService;
    private String idPermohonan;
    private Hakmilik hakmilik;
    private HakmilikPerbicaraan hakmilikPerbicaraan;
    private HakmilikPerbicaraan hakmilikPerbicaraan2;
    private HakmilikPermohonan hakmilikPermohonan;
    private PerbicaraanKehadiran perbicaraanKehadiran;
    private String idHakmilik;
    private List<HakmilikPermohonan> hakmilikPermohonanList;
    private List<PerbicaraanKehadiran> senaraiPerbicaraanKehadiran;
    private List<HakmilikPerbicaraan> hakbicaraList;
    private List<Pengguna> senaraiPengguna;
    private PerbicaraanKehadiran perbicaraanKehadiranPPTB;
    public String noRujukan;
    private Date tarikhPemilikan;
    private String caraPemilikan;
    private BigDecimal hargaPembelian;
    private String lokasi;
    private BigDecimal jarakKeBandar;
    private KodUOM kodUOM;
    private String keadaanTanah;
    private String bangunan;
    private String tanaman;
    private BigDecimal amaunDituntut;
    private String alasanTuntutan;
    private String pemohonUlasan;
    private String penilaiNoRujukan;
    private String penilaiTarikh;
    private String penilaiAmaun;
    private String penilaiUlasan;
    private String catatan;
    private Date tarikhMasuk;
    private String diMasuk;
    private List<Penilaian> penilaianList;
    private List<Penilaian> penilaianTanahList;
    private List<Penilaian> penilaianBngnList;
    private List<Penilaian> penilaianLainList;
    private BigDecimal itemNilaianTanah;
    private BigDecimal itemNilaianBngn;
    private BigDecimal itemNilaianLain;
    private List<BigDecimal> itemNilaianTanahList = new ArrayList<BigDecimal>();
    private List<BigDecimal> itemNilaianBngnList = new ArrayList<BigDecimal>();
    private List<BigDecimal> itemNilaianLainList = new ArrayList<BigDecimal>();
    private List<Character> hadir = new ArrayList<Character>();
    private List<Character> bantah = new ArrayList<Character>();
    private Penilaian penilaian;
    private char r='T';
    private char p='T';
    private char pbt='T';
    private String tarikhBicara;
    private String jam;
    private String minit;
    private String ampm;
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss a");
    private PermohonanPihakTidakBerkepentingan pptb;
    private List<PermohonanPihakTidakBerkepentingan> senaraiPPTB;
    private String kodUrusan;
    private List<HakmilikPerbicaraan> senaraiKandungan1;
    private List<HakmilikPerbicaraan> senaraiKandungan2;
    private List<String> bantahList;
    private HakmilikPerbicaraan hakmilikBicara;
    private String kandungan;
    private List<String> hadirBantahList= new ArrayList<String>();
    @DefaultHandler
    public Resolution showForm() {
        Pengguna pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        if(idPermohonan != null) {
            Permohonan permohonan = permohonanDAO.findById(idPermohonan);
            if(permohonan != null) {
                pengambilanService.simpanPermohonanPihak(permohonan, pengguna);
            }
        }
        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        return new JSP("pengambilan/nota_siasatan_letrik.jsp").addParameter("tab", "true");
    }
    

    public Resolution showPerintah() {
        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        idHakmilik = (String) getContext().getRequest().getParameter("idHakmilik");
        if(idHakmilik != null) {
            hakmilik = hakmilikDAO.findById(idHakmilik);
            hakmilikPermohonan = pembetulanService.findByIdHakmilikIdPermohonan(idPermohonan, idHakmilik);
            if(hakmilikPermohonan != null){
                hakmilikPerbicaraan = notisPenerimaanService.getHakmilikPerbicaraanByidMH(hakmilikPermohonan.getId());
                if(hakmilikPerbicaraan != null) {
                    senaraiPerbicaraanKehadiran = hakmilikPerbicaraan.getSenaraiKehadiran();
                }
            }
        }
        return new JSP("pengambilan/perintah.jsp").addParameter("popup", "true");
    }

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {
        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
         idHakmilik = (String) getContext().getRequest().getParameter("idHakmilik");
         Permohonan permohonan = permohonanDAO.findById(idPermohonan);
         kodUrusan=permohonan.getKodUrusan().getKod();
        if (idPermohonan != null) {
            Permohonan p = permohonanDAO.findById(idPermohonan);
            hakmilikPermohonanList = p.getSenaraiHakmilik();
            
        }

         senaraiPengguna = notisPenerimaanService.getPengguna();
    }

    public Resolution showEditTuanTanah() {
        String idpihak = getContext().getRequest().getParameter("idPihak");
        return new JSP("pengambilan/notasiasatan_popup.jsp").addParameter("popup", "true");

    }

    public Resolution showTuanTanahPopup() {
        return new JSP("pengambilan/notasiasatan_popup.jsp").addParameter("popup", "true");
    }

    public Resolution hakmilikDetails() {
        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        Permohonan permohonan = permohonanDAO.findById(idPermohonan);
        hadir = new ArrayList<Character>();
        bantah = new ArrayList<Character>();

        hakmilik = hakmilikDAO.findById(idHakmilik);
        List<HakmilikPihakBerkepentingan> hpList = hakmilik.getSenaraiPihakBerkepentingan();
        System.out.println("hakmilik pihak berkepentingan"+hpList.size());
        logger.info("id Hakmilik "+idHakmilik);
        logger.info("idPermohonan "+idPermohonan);
        hakmilikPermohonan = pembetulanService.findByIdHakmilikIdPermohonan(idPermohonan, idHakmilik);
        kodUrusan=permohonan.getKodUrusan().getKod();
        logger.info("hakmilikPermohonan "+hakmilikPermohonan.getId());
        logger.info("kodUrusan "+kodUrusan);
        if(hakmilikPermohonan != null){
            if(permohonan.getKodUrusan().getKod().equalsIgnoreCase("PHLLA") ||permohonan.getKodUrusan().getKod().equalsIgnoreCase("PHLLP")){
            kodUrusan=permohonan.getKodUrusan().getKod();
            hakmilikPerbicaraan = notisPenerimaanService.getHakmilikbicaraPHHLA(hakmilikPermohonan.getId());
            logger.info("hakmilikPerbicaraan id "+hakmilikPerbicaraan.getIdPerbicaraan());
            hakbicaraList=notisPenerimaanService.getHakmilikPerbicaraanList(hakmilikPermohonan.getId());
            logger.info("hakbicaraList"+hakbicaraList.get(0).getIdPerbicaraan());
            if(hakmilikPerbicaraan == null){
                logger.info("create id bicara baru ");
                hakmilikPerbicaraan=new HakmilikPerbicaraan();
                InfoAudit info = new InfoAudit();
                info.setDimasukOleh(peng);
                info.setTarikhMasuk(new java.util.Date());
                hakmilikPerbicaraan.setInfoAudit(info);
                hakmilikPerbicaraan.setCawangan(permohonan.getCawangan());
                hakmilikPerbicaraan.setHakmilikPermohonan(hakmilikPermohonan);
                hakmilikPerbicaraan = notisPenerimaanService.saveOrUpdateHakmilikPerbicaraan(hakmilikPerbicaraan);
            }
            if(hakmilikPerbicaraan.getKeputusanNilai() != null) {
                getContext().getRequest().setAttribute("showPerintah", Boolean.TRUE);
            }

            if(hakmilikPerbicaraan.getTarikhBicara() != null) {
                String tarikh = sdf.format(hakmilikPerbicaraan.getTarikhBicara());
                tarikhBicara = tarikh.substring(0, 10);
                jam = tarikh.substring(11, 13);
                minit = tarikh.substring(14, 16);
                ampm = tarikh.substring(20, 22);
            }
            
            
            
            
            
            }
            else
            {
             hakmilikPerbicaraan = notisPenerimaanService.getHakmilikbicaraPHHLA(hakmilikPermohonan.getId());
            logger.info("hakmilikPerbicaraan id "+hakmilikPerbicaraan.getIdPerbicaraan());
            
            hakbicaraList=notisPenerimaanService.getHakmilikPerbicaraanList(hakmilikPermohonan.getId());
            logger.info("hakbicaraList"+hakbicaraList.get(0).getIdPerbicaraan());
            if(hakmilikPerbicaraan == null){
                logger.info("create id bicara baru ");
                hakmilikPerbicaraan=new HakmilikPerbicaraan();
                InfoAudit info = new InfoAudit();
                info.setDimasukOleh(peng);
                info.setTarikhMasuk(new java.util.Date());
                hakmilikPerbicaraan.setInfoAudit(info);
                hakmilikPerbicaraan.setCawangan(permohonan.getCawangan());
                hakmilikPerbicaraan.setHakmilikPermohonan(hakmilikPermohonan);
                hakmilikPerbicaraan = notisPenerimaanService.saveOrUpdateHakmilikPerbicaraan(hakmilikPerbicaraan);
            }
            if(hakmilikPerbicaraan.getKeputusanNilai() != null) {
                getContext().getRequest().setAttribute("showPerintah", Boolean.TRUE);
            }

            if(hakmilikPerbicaraan.getTarikhBicara() != null) {
                String tarikh = sdf.format(hakmilikPerbicaraan.getTarikhBicara());
                tarikhBicara = tarikh.substring(0, 10);
                jam = tarikh.substring(11, 13);
                minit = tarikh.substring(14, 16);
                ampm = tarikh.substring(20, 22);
            }
            
            senaraiKandungan1 = notisPenerimaanService.getSenaraiKandunganList(hakmilikPerbicaraan.getIdPerbicaraan(),"Y");
            senaraiKandungan2 = notisPenerimaanService.getSenaraiKandunganList(hakmilikPerbicaraan.getIdPerbicaraan(),"T");
            
            
            
            
            
            
            }
           
            
            senaraiPPTB=notisPenerimaanService.getSenaraiPPTBByHakmilik(hakmilikPermohonan.getId());
            if(!senaraiPPTB.isEmpty())
            {
                for(PermohonanPihakTidakBerkepentingan ptb:senaraiPPTB)
                {
                pptb=notisPenerimaanService.getPBT(hakmilikPermohonan.getId(),ptb.getIdPermohonanPhkTdkBerptg());
                perbicaraanKehadiranPPTB=notisPenerimaanService.getPerbicaraanKehadiranbyidMPTidBicara(ptb.getIdPermohonanPhkTdkBerptg(), hakmilikPerbicaraan.getIdPerbicaraan());
                if(perbicaraanKehadiranPPTB == null) {
                    perbicaraanKehadiranPPTB = new PerbicaraanKehadiran();
                    InfoAudit infoAudit = new InfoAudit();
                    infoAudit.setTarikhMasuk(new java.util.Date());
                    infoAudit.setDimasukOleh(peng);
                    perbicaraanKehadiranPPTB.setInfoAudit(infoAudit);
                    perbicaraanKehadiranPPTB.setCawangan(permohonan.getCawangan());
                    perbicaraanKehadiranPPTB.setPerbicaraan(hakmilikPerbicaraan);
                    perbicaraanKehadiranPPTB.setPermohonanPihakTidakBerkepentingan(pptb);
                    notisPenerimaanService.saveOrUpdatePerbicaraanKehadiran(perbicaraanKehadiranPPTB);
                }
                
                
                
                }
//                for (int i=0;i<=senaraiPPTB.size();i++){
//                PermohonanPihakTidakBerkepentingan ptb= senaraiPPTB.get(i);
//                pptb=notisPenerimaanService.getPBT(hakmilikPermohonan.getId(),ptb.getIdPermohonanPhkTdkBerptg());
//                perbicaraanKehadiranPPTB=notisPenerimaanService.getPerbicaraanKehadiranbyidMPTidBicara(ptb.getIdPermohonanPhkTdkBerptg(), hakmilikPerbicaraan.getIdPerbicaraan());
//                if(perbicaraanKehadiranPPTB == null) {
//                    perbicaraanKehadiranPPTB = new PerbicaraanKehadiran();
//                    InfoAudit infoAudit = new InfoAudit();
//                    infoAudit.setTarikhMasuk(new java.util.Date());
//                    infoAudit.setDimasukOleh(peng);
//                    perbicaraanKehadiranPPTB.setInfoAudit(infoAudit);
//                    perbicaraanKehadiranPPTB.setCawangan(permohonan.getCawangan());
//                    perbicaraanKehadiranPPTB.setPerbicaraan(hakmilikPerbicaraan);
//                    perbicaraanKehadiranPPTB.setPermohonanPihakTidakBerkepentingan(pptb);
//                    notisPenerimaanService.saveOrUpdatePerbicaraanKehadiran(perbicaraanKehadiranPPTB);
//                }
//                
//                }
                
            
            }
            

            for(int i=0; i<hpList.size(); i++) {
                System.out.println("masuk loop senarai kehadiran");

                HakmilikPihakBerkepentingan hp = hpList.get(i);
                PermohonanPihak permohonanPihak = notisPenerimaanService.getSenaraiPmohonPihakByIdHakmilikIdPihak(idPermohonan, idHakmilik, hp.getPihak().getIdPihak());
                perbicaraanKehadiran = notisPenerimaanService.getPerbicaraanKehadiranbyidMPidBicara(permohonanPihak.getIdPermohonanPihak(), hakmilikPerbicaraan.getIdPerbicaraan());
                if(perbicaraanKehadiran == null) {
                    perbicaraanKehadiran = new PerbicaraanKehadiran();
                    InfoAudit infoAudit = new InfoAudit();
                    infoAudit.setTarikhMasuk(new java.util.Date());
                    infoAudit.setDimasukOleh(peng);
                    perbicaraanKehadiran.setInfoAudit(infoAudit);
                    perbicaraanKehadiran.setCawangan(permohonan.getCawangan());
                    perbicaraanKehadiran.setPerbicaraan(hakmilikPerbicaraan);
                    perbicaraanKehadiran.setPihak(permohonanPihak);
                    notisPenerimaanService.saveOrUpdatePerbicaraanKehadiran(perbicaraanKehadiran);
                }
            }
            int c=1;
            int counter=hakbicaraList.size()-c;
            senaraiPerbicaraanKehadiran = hakmilikPerbicaraan.getSenaraiKehadiran();
            for(int i=0; i<senaraiPerbicaraanKehadiran.size(); i++) 
            {
                hadir.add('0');
                
            }
            for(int i=0; i<senaraiPerbicaraanKehadiran.size(); i++) 
            {
                 perbicaraanKehadiran = senaraiPerbicaraanKehadiran.get(i);
                if(perbicaraanKehadiran.getHadir() != null && perbicaraanKehadiran.getHadir()!= ' '){
                    hadir.set(i, perbicaraanKehadiran.getHadir());
                }

                  if(perbicaraanKehadiran.getBantahElektrik() == null){
                     hadirBantahList.add("");
                 }
                 else if(perbicaraanKehadiran.getBantahElektrik()!= null)
                {
                     hadirBantahList.add(bantah.toString());
                }
            }
        }

        getContext().getRequest().getSession().setAttribute("idHakmilik", idHakmilik);
        return new JSP("pengambilan/nota_siasatan_letrik.jsp").addParameter("tab", "true");
    }

        public Resolution simpan() throws ParseException, java.text.ParseException {

        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        idHakmilik = (String) getContext().getRequest().getSession().getAttribute("idHakmilik");
        hakmilik = hakmilikDAO.findById(idHakmilik);
        Permohonan permohonan = permohonanDAO.findById(idPermohonan);
        hakmilikPermohonan = pembetulanService.findByIdHakmilikIdPermohonan(idPermohonan, idHakmilik);

        HakmilikPerbicaraan hakmilikPerbicaraanTemp;
        if(permohonan.getKodUrusan().getKod().equalsIgnoreCase("PHLLA") ||permohonan.getKodUrusan().getKod().equalsIgnoreCase("PHLLP"))
        {
            if(notisPenerimaanService.getHakmilikbicaraPHHLA(hakmilikPermohonan.getId()) == null) {
                hakmilikPerbicaraanTemp=new HakmilikPerbicaraan();
                InfoAudit info = new InfoAudit();
                info.setDimasukOleh(peng);
                info.setTarikhMasuk(new java.util.Date());
                hakmilikPerbicaraanTemp.setInfoAudit(info);
                hakmilikPerbicaraanTemp.setCawangan(permohonan.getCawangan());
                hakmilikPerbicaraanTemp.setHakmilikPermohonan(hakmilikPermohonan);
            }else{
                hakmilikPerbicaraanTemp = notisPenerimaanService.getHakmilikbicaraPHHLA(hakmilikPermohonan.getId());
                InfoAudit ia = hakmilikPerbicaraanTemp.getInfoAudit();
                ia.setDiKemaskiniOleh(peng);
                ia.setTarikhKemaskini(new java.util.Date());
                hakmilikPerbicaraanTemp.setInfoAudit(ia);
            }
        }
        else 
        {
            if(notisPenerimaanService.getHakmilikbicaraPHHLA(hakmilikPermohonan.getId()) == null) {
            hakmilikPerbicaraanTemp=new HakmilikPerbicaraan();
            InfoAudit info = new InfoAudit();
            info.setDimasukOleh(peng);
            info.setTarikhMasuk(new java.util.Date());
            hakmilikPerbicaraanTemp.setInfoAudit(info);
            hakmilikPerbicaraanTemp.setCawangan(permohonan.getCawangan());
            hakmilikPerbicaraanTemp.setHakmilikPermohonan(hakmilikPermohonan);
            }else{
            hakmilikPerbicaraanTemp = notisPenerimaanService.getHakmilikbicaraPHHLA(hakmilikPermohonan.getId());
            InfoAudit ia = hakmilikPerbicaraanTemp.getInfoAudit();
            ia.setDiKemaskiniOleh(peng);
            ia.setTarikhKemaskini(new java.util.Date());
            hakmilikPerbicaraanTemp.setInfoAudit(ia);
            }
        }
        hakmilikPerbicaraanTemp.setCatatan(hakmilikPerbicaraan.getCatatan());
        hakmilikPerbicaraanTemp.setNoRujukan(hakmilikPerbicaraan.getNoRujukan());
        hakmilikPerbicaraanTemp.setDibicaraOleh(hakmilikPerbicaraan.getDibicaraOleh());
        hakmilikPerbicaraanTemp.setTarikhPemilikan(hakmilikPerbicaraan.getTarikhPemilikan());
        hakmilikPerbicaraanTemp.setCaraPemilikan(hakmilikPerbicaraan.getCaraPemilikan());
        hakmilikPerbicaraanTemp.setHargaPembelian(hakmilikPerbicaraan.getHargaPembelian());
        hakmilikPerbicaraanTemp.setNilaianPenilai(hakmilikPerbicaraan.getNilaianPenilai());
        hakmilikPerbicaraanTemp.setPenilaiNoRujukan(hakmilikPerbicaraan.getPenilaiNoRujukan());
        hakmilikPerbicaraanTemp.setTanaman(hakmilikPerbicaraan.getTanaman());
        hakmilikPerbicaraanTemp.setBangunan(hakmilikPerbicaraan.getBangunan());
        hakmilikPerbicaraanTemp.setPemohonUlasan(hakmilikPerbicaraan.getPemohonUlasan());
        hakmilikPerbicaraanTemp.setAlasanTuntutan(hakmilikPerbicaraan.getAlasanTuntutan());
        hakmilikPerbicaraanTemp.setKeputusanNilai(hakmilikPerbicaraan.getKeputusanNilai());
        hakmilikPerbicaraanTemp.setKodUOM(hakmilikPerbicaraan.getKodUOM());
        hakmilikPerbicaraanTemp.setPenilaiNoRujukan(hakmilikPerbicaraan.getPenilaiNoRujukan());
        hakmilikPerbicaraanTemp.setKeteranganGKL(hakmilikPerbicaraan.getKeteranganGKL());
        hakmilikPerbicaraanTemp.setKeteranganPPP(hakmilikPerbicaraan.getKeteranganPPP());
        hakmilikPerbicaraanTemp.setKeteranganTuanpunya(hakmilikPerbicaraan.getKeteranganTuanpunya());
        hakmilikPerbicaraanTemp.setKeteranganLain(hakmilikPerbicaraan.getKeteranganLain());
        hakmilikPerbicaraanTemp.setTarikhSuratPenilai(hakmilikPerbicaraan.getTarikhSuratPenilai());
        hakmilikPerbicaraanTemp.setTarikhPecahSyarat(hakmilikPerbicaraan.getTarikhPecahSyarat());
        hakmilikPerbicaraanTemp.setAkujanjiPenilai(hakmilikPerbicaraan.getAkujanjiPenilai());
        hakmilikPerbicaraanTemp.setPenilaiNama(hakmilikPerbicaraan.getPenilaiNama());
        hakmilikPerbicaraanTemp.setUlasanPenilai(hakmilikPerbicaraan.getUlasanPenilai());
        hakmilikPerbicaraanTemp.setLokasiTerkini(hakmilikPerbicaraan.getLokasiTerkini());
        hakmilikPerbicaraanTemp.setPecahSyarat(hakmilikPerbicaraan.getPecahSyarat());
        hakmilikPerbicaraanTemp.setUjudGPPJ(hakmilikPerbicaraan.getUjudGPPJ());
        hakmilikPerbicaraanTemp.setKomenGPPJ(hakmilikPerbicaraan.getKomenGPPJ());
        hakmilikPerbicaraanTemp.setBatalRizab(r);
        hakmilikPerbicaraanTemp.setKawasanPBT(pbt);
        hakmilikPerbicaraanTemp.setPelanPembangunan(p);
        hakmilikPerbicaraan = notisPenerimaanService.saveOrUpdateHakmilikPerbicaraan(hakmilikPerbicaraanTemp);
        
        
         senaraiKandungan1 = notisPenerimaanService.getSenaraiKandunganList(hakmilikPerbicaraan.getIdPerbicaraan(),"Y");
         int kira3 = Integer.parseInt(getContext().getRequest().getParameter("rowCount3"));

            for (int i = 1; i <= kira3; i++) {
            if (!(senaraiKandungan1.isEmpty()) && i <= senaraiKandungan1.size()) {
              
                hakmilikPerbicaraanTemp = new HakmilikPerbicaraan();
            }
           
            kandungan = getContext().getRequest().getParameter("kandungan3" + i);
           
            hakmilikPerbicaraanTemp.setKeteranganGKL(kandungan);
            hakmilikPerbicaraanTemp.setUjudGPPJ("Y");
            hakmilikPerbicaraanTemp.setTempohPengosongan(i);
            InfoAudit iaP = new InfoAudit();
            iaP.setTarikhMasuk(new java.util.Date());
            iaP.setDimasukOleh(peng);
            hakmilikPerbicaraanTemp.setInfoAudit(iaP);
            hakmilikPerbicaraan = notisPenerimaanService.saveOrUpdateHakmilikPerbicaraan(hakmilikPerbicaraanTemp);
        }

        if(hakmilikPerbicaraan.getIdPerbicaraan() > 0) {
            senaraiPerbicaraanKehadiran = hakmilikPerbicaraan.getSenaraiKehadiran();
            for(int i=0; i<senaraiPerbicaraanKehadiran.size(); i++) {
                perbicaraanKehadiran = senaraiPerbicaraanKehadiran.get(i);
                perbicaraanKehadiran.setHadir(hadir.get(i));
                notisPenerimaanService.saveOrUpdatePerbicaraanKehadiran(perbicaraanKehadiran);
            }
            if(hakmilikPerbicaraan.getKeputusanNilai() != null) {
                getContext().getRequest().setAttribute("showPerintah", Boolean.TRUE);
            }
        }


        addSimpleMessage("Maklumat telah berjaya disimpan");
        return new JSP("pengambilan/nota_siasatan_letrik.jsp").addParameter("tab", "true");
    }


        public Resolution simpanKehadiran() {
        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        Permohonan permohonan = permohonanDAO.findById(idPermohonan);
        hadir = new ArrayList<Character>();
        bantah = new ArrayList<Character>();
        idHakmilik = (String) getContext().getRequest().getSession().getAttribute("idHakmilik");

        hakmilik = hakmilikDAO.findById(idHakmilik);
            System.out.println("begin");
        List<HakmilikPihakBerkepentingan> hpList = hakmilik.getSenaraiPihakBerkepentingan();

        hakmilikPermohonan = pembetulanService.findByIdHakmilikIdPermohonan(idPermohonan, idHakmilik);
        hakmilikPermohonanList=permohonan.getSenaraiHakmilik();
        for(int i=0; i<hakmilikPermohonanList.size(); i++)
        {
           
        HakmilikPermohonan hp = hakmilikPermohonanList.get(i);
        hakmilikPerbicaraan = notisPenerimaanService.getHakmilikbicara(hp.getId());
        if(hakmilikPerbicaraan != null){
            System.out.println("loop 2");
            senaraiPerbicaraanKehadiran = notisPenerimaanService.getPerbicaraanKehadiranbyidBicara(hakmilikPerbicaraan.getIdPerbicaraan());
                if(senaraiPerbicaraanKehadiran != null) {
                    System.out.println("masuk sini create org baru");
                    perbicaraanKehadiran = new PerbicaraanKehadiran();
                    InfoAudit infoAudit = new InfoAudit();
                    infoAudit.setTarikhMasuk(new java.util.Date());
                    infoAudit.setDimasukOleh(peng);
                    perbicaraanKehadiran.setInfoAudit(infoAudit);
                    perbicaraanKehadiran.setCawangan(permohonan.getCawangan());
                    perbicaraanKehadiran.setPerbicaraan(hakmilikPerbicaraan);
                    perbicaraanKehadiran.setNama(getContext().getRequest().getParameter("perbicaraanKehadiran.nama"));
                    perbicaraanKehadiran.setNoPengenalan(getContext().getRequest().getParameter("perbicaraanKehadiran.noPengenalan"));
                    perbicaraanKehadiran.setJawatan(getContext().getRequest().getParameter("perbicaraanKehadiran.jawatan"));
                    perbicaraanKehadiran.setPenilaiUlasan(getContext().getRequest().getParameter("perbicaraanKehadiran.penilaiUlasan"));
                    notisPenerimaanService.saveOrUpdatePerbicaraanKehadiran(perbicaraanKehadiran);
                }
            }
        }
        rehydrate();
        getContext().getRequest().getSession().setAttribute("idHakmilik", idHakmilik);
        return new JSP("pengambilan/nota_siasatan_letrik.jsp").addParameter("tab", "true");
    }

        public Resolution simpanPerintah() {
        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        idHakmilik = (String) getContext().getRequest().getSession().getAttribute("idHakmilik");
        hakmilik = hakmilikDAO.findById(idHakmilik);
        Permohonan permohonan = permohonanDAO.findById(idPermohonan);
        hakmilikPermohonan = pembetulanService.findByIdHakmilikIdPermohonan(idPermohonan, idHakmilik);

        HakmilikPerbicaraan hakmilikPerbicaraanTemp;
        if(notisPenerimaanService.getHakmilikPerbicaraanByidMH(hakmilikPermohonan.getId()) == null) {
            hakmilikPerbicaraanTemp=new HakmilikPerbicaraan();
            InfoAudit info = new InfoAudit();
            info.setDimasukOleh(peng);
            info.setTarikhMasuk(new java.util.Date());
            hakmilikPerbicaraanTemp.setInfoAudit(info);
            hakmilikPerbicaraanTemp.setCawangan(permohonan.getCawangan());
            hakmilikPerbicaraanTemp.setHakmilikPermohonan(hakmilikPermohonan);
        }else{
            hakmilikPerbicaraanTemp = notisPenerimaanService.getHakmilikPerbicaraanByidMH(hakmilikPermohonan.getId());
            InfoAudit ia = hakmilikPerbicaraanTemp.getInfoAudit();
            ia.setDiKemaskiniOleh(peng);
            ia.setTarikhKemaskini(new java.util.Date());
            hakmilikPerbicaraanTemp.setInfoAudit(ia);
        }
        hakmilikPerbicaraanTemp.setJenisPembangunan(hakmilikPerbicaraan.getJenisPembangunan());
        hakmilikPerbicaraan = notisPenerimaanService.saveOrUpdateHakmilikPerbicaraan(hakmilikPerbicaraanTemp);

        if(hakmilikPerbicaraan.getIdPerbicaraan() > 0) {
            if(hakmilikPerbicaraan.getKeputusanNilai() != null) {
                getContext().getRequest().setAttribute("showPerintah", Boolean.TRUE);
            }
        }


        
        getContext().getRequest().getSession().setAttribute("idHakmilik", idHakmilik);
        return new JSP("pengambilan/nota_siasatan_letrik.jsp").addParameter("tab", "true");
    }
    public Resolution savePerbicaraanKehadiran() {
        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        Permohonan permohonan = permohonanDAO.findById(idPermohonan);
        perbicaraanKehadiran = new PerbicaraanKehadiran();
        perbicaraanKehadiran.setCawangan(permohonan.getCawangan());
        perbicaraanKehadiran.setPerbicaraan(hakmilikPerbicaraan);
        addSimpleMessage("Maklumat telah berjaya disimpan");
        return new ForwardResolution("/WEB-INF/jsp/pengambilan/nota_siasatan_letrik.jsp").addParameter("tab", "true");
    }


    public Hakmilik getHakmilik() {
        return hakmilik;
    }

    public void setHakmilik(Hakmilik hakmilik) {
        this.hakmilik = hakmilik;
    }

    public List<HakmilikPermohonan> getHakmilikPermohonanList() {
        return hakmilikPermohonanList;
    }

    public void setHakmilikPermohonanList(List<HakmilikPermohonan> hakmilikPermohonanList) {
        this.hakmilikPermohonanList = hakmilikPermohonanList;
    }

    public String getIdHakmilik() {
        return idHakmilik;
    }

    public void setIdHakmilik(String idHakmilik) {
        this.idHakmilik = idHakmilik;
    }

    public HakmilikPerbicaraan getHakmilikPerbicaraan() {
        return hakmilikPerbicaraan;
    }

    public void setHakmilikPerbicaraan(HakmilikPerbicaraan hakmilikPerbicaraan) {
        this.hakmilikPerbicaraan = hakmilikPerbicaraan;
    }

    public HakmilikPermohonan getHakmilikPermohonan() {
        return hakmilikPermohonan;
    }

    public void setHakmilikPermohonan(HakmilikPermohonan hakmilikPermohonan) {
        this.hakmilikPermohonan = hakmilikPermohonan;
    }

    public String getAlasanTuntutan() {
        return alasanTuntutan;
    }

    public void setAlasanTuntutan(String alasanTuntutan) {
        this.alasanTuntutan = alasanTuntutan;
    }

    public String getCaraPemilikan() {
        return caraPemilikan;
    }

    public void setCaraPemilikan(String caraPemilikan) {
        this.caraPemilikan = caraPemilikan;
    }

    public String getCatatan() {
        return catatan;
    }

    public void setCatatan(String catatan) {
        this.catatan = catatan;
    }

    public String getIdPermohonan() {
        return idPermohonan;
    }

    public void setIdPermohonan(String idPermohonan) {
        this.idPermohonan = idPermohonan;
    }

    public String getKeadaanTanah() {
        return keadaanTanah;
    }

    public void setKeadaanTanah(String keadaanTanah) {
        this.keadaanTanah = keadaanTanah;
    }

    public KodUOM getKodUOM() {
        return kodUOM;
    }

    public void setKodUOM(KodUOM kodUOM) {
        this.kodUOM = kodUOM;
    }

    public String getLokasi() {
        return lokasi;
    }

    public void setLokasi(String lokasi) {
        this.lokasi = lokasi;
    }

    public String getPemohonUlasan() {
        return pemohonUlasan;
    }

    public void setPemohonUlasan(String pemohonUlasan) {
        this.pemohonUlasan = pemohonUlasan;
    }

    public String getPenilaiAmaun() {
        return penilaiAmaun;
    }

    public void setPenilaiAmaun(String penilaiAmaun) {
        this.penilaiAmaun = penilaiAmaun;
    }

    public String getPenilaiNoRujukan() {
        return penilaiNoRujukan;
    }

    public void setPenilaiNoRujukan(String penilaiNoRujukan) {
        this.penilaiNoRujukan = penilaiNoRujukan;
    }

    public String getPenilaiTarikh() {
        return penilaiTarikh;
    }

    public void setPenilaiTarikh(String penilaiTarikh) {
        this.penilaiTarikh = penilaiTarikh;
    }

    public String getPenilaiUlasan() {
        return penilaiUlasan;
    }

    public void setPenilaiUlasan(String penilaiUlasan) {
        this.penilaiUlasan = penilaiUlasan;
    }

    public String getTanaman() {
        return tanaman;
    }

    public void setTanaman(String tanaman) {
        this.tanaman = tanaman;
    }

    public BigDecimal getAmaunDituntut() {
        return amaunDituntut;
    }

    public void setAmaunDituntut(BigDecimal amaunDituntut) {
        this.amaunDituntut = amaunDituntut;
    }

    public String getBangunan() {
        return bangunan;
    }

    public void setBangunan(String bangunan) {
        this.bangunan = bangunan;
    }

    public BigDecimal getHargaPembelian() {
        return hargaPembelian;
    }

    public void setHargaPembelian(BigDecimal hargaPembelian) {
        this.hargaPembelian = hargaPembelian;
    }

    public BigDecimal getJarakKeBandar() {
        return jarakKeBandar;
    }

    public void setJarakKeBandar(BigDecimal jarakKeBandar) {
        this.jarakKeBandar = jarakKeBandar;
    }

    public String getNoRujukan() {
        return noRujukan;
    }

    public void setNoRujukan(String noRujukan) {
        this.noRujukan = noRujukan;
    }

    public Date getTarikhPemilikan() {
        return tarikhPemilikan;
    }

    public void setTarikhPemilikan(Date tarikhPemilikan) {
        this.tarikhPemilikan = tarikhPemilikan;
    }

    public Date getTarikhMasuk() {
        return tarikhMasuk;
    }

    public void setTarikhMasuk(Date tarikhMasuk) {
        this.tarikhMasuk = tarikhMasuk;
    }

    public List<PerbicaraanKehadiran> getSenaraiPerbicaraanKehadiran() {
        return senaraiPerbicaraanKehadiran;
    }

    public void setSenaraiPerbicaraanKehadiran(List<PerbicaraanKehadiran> senaraiPerbicaraanKehadiran) {
        this.senaraiPerbicaraanKehadiran = senaraiPerbicaraanKehadiran;
    }

    public PerbicaraanKehadiran getPerbicaraanKehadiran() {
        return perbicaraanKehadiran;
    }

    public void setPerbicaraanKehadiran(PerbicaraanKehadiran perbicaraanKehadiran) {
        this.perbicaraanKehadiran = perbicaraanKehadiran;
    }

    public List<Pengguna> getSenaraiPengguna() {
        return senaraiPengguna;
    }

    public void setSenaraiPengguna(List<Pengguna> senaraiPengguna) {
        this.senaraiPengguna = senaraiPengguna;
    }

    public String getDiMasuk() {
        return diMasuk;
    }

    public void setDiMasuk(String diMasuk) {
        this.diMasuk = diMasuk;
    }

    public BigDecimal getItemNilaianBngn() {
        return itemNilaianBngn;
    }

    public void setItemNilaianBngn(BigDecimal itemNilaianBngn) {
        this.itemNilaianBngn = itemNilaianBngn;
    }

    public BigDecimal getItemNilaianLain() {
        return itemNilaianLain;
    }

    public void setItemNilaianLain(BigDecimal itemNilaianLain) {
        this.itemNilaianLain = itemNilaianLain;
    }

    public BigDecimal getItemNilaianTanah() {
        return itemNilaianTanah;
    }

    public void setItemNilaianTanah(BigDecimal itemNilaianTanah) {
        this.itemNilaianTanah = itemNilaianTanah;
    }

    public List<Penilaian> getPenilaianBngnList() {
        return penilaianBngnList;
    }

    public void setPenilaianBngnList(List<Penilaian> penilaianBngnList) {
        this.penilaianBngnList = penilaianBngnList;
    }

    public List<Penilaian> getPenilaianLainList() {
        return penilaianLainList;
    }

    public void setPenilaianLainList(List<Penilaian> penilaianLainList) {
        this.penilaianLainList = penilaianLainList;
    }

    public List<Penilaian> getPenilaianList() {
        return penilaianList;
    }

    public void setPenilaianList(List<Penilaian> penilaianList) {
        this.penilaianList = penilaianList;
    }

    public List<Penilaian> getPenilaianTanahList() {
        return penilaianTanahList;
    }

    public void setPenilaianTanahList(List<Penilaian> penilaianTanahList) {
        this.penilaianTanahList = penilaianTanahList;
    }

    public List<BigDecimal> getItemNilaianBngnList() {
        return itemNilaianBngnList;
    }

    public void setItemNilaianBngnList(List<BigDecimal> itemNilaianBngnList) {
        this.itemNilaianBngnList = itemNilaianBngnList;
    }

    public List<BigDecimal> getItemNilaianLainList() {
        return itemNilaianLainList;
    }

    public void setItemNilaianLainList(List<BigDecimal> itemNilaianLainList) {
        this.itemNilaianLainList = itemNilaianLainList;
    }

    public List<BigDecimal> getItemNilaianTanahList() {
        return itemNilaianTanahList;
    }

    public void setItemNilaianTanahList(List<BigDecimal> itemNilaianTanahList) {
        this.itemNilaianTanahList = itemNilaianTanahList;
    }

    public List<Character> getHadir() {
        return hadir;
    }

    public void setHadir(List<Character> hadir) {
        this.hadir = hadir;
    }

    public String getAmpm() {
        return ampm;
    }

    public void setAmpm(String ampm) {
        this.ampm = ampm;
    }

    public String getJam() {
        return jam;
    }

    public void setJam(String jam) {
        this.jam = jam;
    }

    public String getMinit() {
        return minit;
    }

    public void setMinit(String minit) {
        this.minit = minit;
    }

    public String getTarikhBicara() {
        return tarikhBicara;
    }

    public void setTarikhBicara(String tarikhBicara) {
        this.tarikhBicara = tarikhBicara;
    }

    public HakmilikPerbicaraan getHakmilikPerbicaraan2() {
        return hakmilikPerbicaraan2;
    }

    public void setHakmilikPerbicaraan2(HakmilikPerbicaraan hakmilikPerbicaraan2) {
        this.hakmilikPerbicaraan2 = hakmilikPerbicaraan2;
    }


    public List<HakmilikPerbicaraan> getHakbicaraList() {
        return hakbicaraList;
    }

    public void setHakbicaraList(List<HakmilikPerbicaraan> hakbicaraList) {
        this.hakbicaraList = hakbicaraList;
    }
    
    public PermohonanPihakTidakBerkepentingan getPptb() {
        return pptb;
    }

    public void setPptb(PermohonanPihakTidakBerkepentingan pptb) {
        this.pptb = pptb;
    }

    public Penilaian getPenilaian() {
        return penilaian;
    }

    public void setPenilaian(Penilaian penilaian) {
        this.penilaian = penilaian;
    }
    
    public PerbicaraanKehadiran getPerbicaraanKehadiranPPTB() {
        return perbicaraanKehadiranPPTB;
    }

    public void setPerbicaraanKehadiranPPTB(PerbicaraanKehadiran perbicaraanKehadiranPPTB) {
        this.perbicaraanKehadiranPPTB = perbicaraanKehadiranPPTB;
    }
    
    public List<PermohonanPihakTidakBerkepentingan> getSenaraiPPTB() {
        return senaraiPPTB;
    }

    public void setSenaraiPPTB(List<PermohonanPihakTidakBerkepentingan> senaraiPPTB) {
        this.senaraiPPTB = senaraiPPTB;
    }
    
    public String getKodUrusan() {
        return kodUrusan;
    }

    public void setKodUrusan(String kodUrusan) {
        this.kodUrusan = kodUrusan;
    }
    public List<HakmilikPerbicaraan> getSenaraiKandungan1() {
        return senaraiKandungan1;
    }

    public void setSenaraiKandungan1(List<HakmilikPerbicaraan> senaraiKandungan1) {
        this.senaraiKandungan1 = senaraiKandungan1;
    }

    public List<HakmilikPerbicaraan> getSenaraiKandungan2() {
        return senaraiKandungan2;
    }

    public void setSenaraiKandungan2(List<HakmilikPerbicaraan> senaraiKandungan2) {
        this.senaraiKandungan2 = senaraiKandungan2;
    }
    
    public HakmilikPerbicaraan getHakmilikBicara() {
        return hakmilikBicara;
    }

    public void setHakmilikBicara(HakmilikPerbicaraan hakmilikBicara) {
        this.hakmilikBicara = hakmilikBicara;
    }
    
    public String getKandungan() {
        return kandungan;
    }

    public void setKandungan(String kandungan) {
        this.kandungan = kandungan;
    }
    
    public List<Character> getBantah() {
        return bantah;
    }

    public void setBantah(List<Character> bantah) {
        this.bantah = bantah;
    }
    public List<String> getBantahList() {
        return bantahList;
    }

    public void setBantahList(List<String> bantahList) {
        this.bantahList = bantahList;
    }
    public List<String> getHadirBantahList() {
        return hadirBantahList;
    }

    public void setHadirBantahList(List<String> hadirBantahList) {
        this.hadirBantahList = hadirBantahList;
    }

    

   
}