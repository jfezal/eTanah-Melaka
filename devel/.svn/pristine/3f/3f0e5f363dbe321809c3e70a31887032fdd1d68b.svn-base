package etanah.view.stripes.pelupusan;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.dao.*;
import etanah.model.*;
import etanah.model.ambil.HakmilikPerbicaraan;
import etanah.model.ambil.PerbicaraanKehadiran;
import etanah.service.BPelService;
import etanah.service.PelupusanService;
import etanah.service.common.NotisPenerimaanService;
import etanah.service.daftar.PembetulanService;
import etanah.view.etanahActionBeanContext;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import net.sourceforge.stripes.action.*;
import net.sourceforge.stripes.controller.LifecycleStage;
import oracle.bpel.services.workflow.task.model.Task;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

@UrlBinding("/pelupusan/nota_Siasatan_PBRZ")
public class NotaSiasatanActionBean extends AbleActionBean {

    @Inject
    private Permohonan permohonan;
    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    PelupusanService plpService;
    @Inject
    KodKeputusanDAO kodKeputusanDAO;
    @Inject
    HakmilikDAO hakmilikDAO;
    @Inject
    HakmilikPermohonanDAO hakmilikPermohonanDAO;
    @Inject
    KodDokumenDAO kodDokumenDAO;
    @Inject
    PermohonanKertasDAO permohonanKertasDAO;
    @Inject
    PermohonanKertasKandunganDAO permohonanKertasKandunganDAO;
    @Inject
    private PembetulanService pembetulanService;
    @Inject
    NotisPenerimaanService notisPenerimaanService;
    @Inject
    KodCawanganDAO kodCawanganDAO;
    private static final Logger LOG = Logger.getLogger(NotaSiasatanActionBean.class);
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    private String idPermohonan;
    private Pemohon pemohon;
    private HakmilikPermohonan mohonHakmilik = new HakmilikPermohonan();
    private PermohonanKertasKandungan kertasKandungan;
    private PermohonanKertas mohonKertas;
    private KodDokumen kd;
    private String keputusansiasatan;
    private String waktu;
    private Date tarikhSidang;
    private String tempat;
    private String kand0;
    private String kand1;
    private String kand2;
    private String kand3;
    private String kand4;
    private String perihalPermohonan;
    private String kedudukanRizab;
    private String asasBantahan;
    private String syorPerakuan;
    private String idPermohonanSebelum;
    private List<PermohonanKertasKandungan> latarBelakangListRMN = new ArrayList<PermohonanKertasKandungan>();
    private List<PermohonanKertasKandungan> latarBelakangList = new ArrayList<PermohonanKertasKandungan>();
    private String stageId;
    private Pengguna peng;

    public String getStageId() {
        return stageId;
    }

    public void setStageId(String stageId) {
        this.stageId = stageId;
    }
    
    public List<PermohonanKertasKandungan> getLatarBelakangList() {
        return latarBelakangList;
    }

    public void setLatarBelakangList(List<PermohonanKertasKandungan> latarBelakangList) {
        this.latarBelakangList = latarBelakangList;
    }

    public String getIdPermohonanSebelum() {
        return idPermohonanSebelum;
    }

    public void setIdPermohonanSebelum(String idPermohonanSebelum) {
        this.idPermohonanSebelum = idPermohonanSebelum;
    }

    public String getAsasBantahan() {
        return asasBantahan;
    }

    public void setAsasBantahan(String asasBantahan) {
        this.asasBantahan = asasBantahan;
    }

    public String getKedudukanRizab() {
        return kedudukanRizab;
    }

    public void setKedudukanRizab(String kedudukanRizab) {
        this.kedudukanRizab = kedudukanRizab;
    }

    public String getPerihalPermohonan() {
        return perihalPermohonan;
    }

    public void setPerihalPermohonan(String perihalPermohonan) {
        this.perihalPermohonan = perihalPermohonan;
    }

    public String getSyorPerakuan() {
        return syorPerakuan;
    }

    public void setSyorPerakuan(String syorPerakuan) {
        this.syorPerakuan = syorPerakuan;
    }

    public String getKand0() {
        return kand0;
    }

    public void setKand0(String kand0) {
        this.kand0 = kand0;
    }

    public String getKand1() {
        return kand1;
    }

    public void setKand1(String kand1) {
        this.kand1 = kand1;
    }

    public String getKand2() {
        return kand2;
    }

    public void setKand2(String kand2) {
        this.kand2 = kand2;
    }

    public String getKand3() {
        return kand3;
    }

    public void setKand3(String kand3) {
        this.kand3 = kand3;
    }

    public String getKand4() {
        return kand4;
    }

    public void setKand4(String kand4) {
        this.kand4 = kand4;
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

    public String getIdPermohonan() {
        return idPermohonan;
    }

    public void setIdPermohonan(String idPermohonan) {
        this.idPermohonan = idPermohonan;
    }

    public PermohonanKertasKandungan getKertasKandungan() {
        return kertasKandungan;
    }

    public void setKertasKandungan(PermohonanKertasKandungan kertasKandungan) {
        this.kertasKandungan = kertasKandungan;
    }

    public HakmilikPermohonan getMohonHakmilik() {
        return mohonHakmilik;
    }

    public void setMohonHakmilik(HakmilikPermohonan mohonHakmilik) {
        this.mohonHakmilik = mohonHakmilik;
    }

    public PembetulanService getPembetulanService() {
        return pembetulanService;
    }

    public void setPembetulanService(PembetulanService pembetulanService) {
        this.pembetulanService = pembetulanService;
    }

    public Pemohon getPemohon() {
        return pemohon;
    }

    public void setPemohon(Pemohon pemohon) {
        this.pemohon = pemohon;
    }

    public Permohonan getPermohonan() {
        return permohonan;
    }

    public void setPermohonan(Permohonan permohonan) {
        this.permohonan = permohonan;
    }

    public List<PerbicaraanKehadiran> getSenaraiPerbicaraanKehadiran() {
        return senaraiPerbicaraanKehadiran;
    }

    public void setSenaraiPerbicaraanKehadiran(List<PerbicaraanKehadiran> senaraiPerbicaraanKehadiran) {
        this.senaraiPerbicaraanKehadiran = senaraiPerbicaraanKehadiran;
    }
    private String idHakmilik;
    private String notaSiasatan;
    private Hakmilik hakmilik;
    private HakmilikPermohonan hakmilikPermohonan;
    private HakmilikPerbicaraan hakmilikPerbicaraan;
    private List<PerbicaraanKehadiran> senaraiPerbicaraanKehadiran;

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

    public String getKeputusansiasatan() {
        return keputusansiasatan;
    }

    public void setKeputusansiasatan(String keputusansiasatan) {
        this.keputusansiasatan = keputusansiasatan;
    }

    public Date getTarikhSidang() {
        return tarikhSidang;
    }

    public void setTarikhSidang(Date tarikhSidang) {
        this.tarikhSidang = tarikhSidang;
    }

    public String getTempat() {
        return tempat;
    }

    public void setTempat(String tempat) {
        this.tempat = tempat;
    }

    public String getWaktu() {
        return waktu;
    }

    public void setWaktu(String waktu) {
        this.waktu = waktu;
    }

    public String getNotaSiasatan() {
        return notaSiasatan;
    }

    public void setNotaSiasatan(String notaSiasatan) {
        this.notaSiasatan = notaSiasatan;
    }
//    private ActionBeanContext context;

    public NotaSiasatanActionBean() {
        // TODO Auto-generated constructor stub
    }

    public ActionBeanContext getContext() {
        // TODO Auto-generated method stub
        return this.context;
    }

    public void setContext(ActionBeanContext context) {
        this.context = context;

    }

    @DefaultHandler
    public Resolution showForm() {
        LOG.info("showForm");
        return new JSP("pelupusan/nota_siasatan.jsp").addParameter("tab", "true");
    }

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {

        LOG.info("--------------rehydrate() Started--------------::");
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        LOG.info("---------idPermohonan---------::" + idPermohonan);
        permohonan = permohonanDAO.findById(idPermohonan);
        LOG.info("---------permohonan---------::" + permohonan);
        String taskId = (String) getContext().getRequest().getSession().getAttribute("taskId");
        mohonKertas = plpService.findKertasByKod(idPermohonan, "NSIA");
        idPermohonanSebelum = permohonan.getPermohonanSebelum().getIdPermohonan();
        HakmilikPermohonan hakmilikPermohonan = new HakmilikPermohonan();
        hakmilikPermohonan = plpService.findByIdPermohonan(idPermohonan);
        if (mohonKertas != null) {
            tarikhSidang = mohonKertas.getTarikhSidang();
            List<PermohonanKertasKandungan> kertasKand0 = plpService.findByIdLapor(mohonKertas.getIdKertas(), 0);
            if (kertasKand0 != null) {
                kand0 = kertasKand0.get(0).getKandungan();
            } else {
                kand0 = "";
            }

            List<PermohonanKertasKandungan> kertasKand1 = plpService.findByIdLapor(mohonKertas.getIdKertas(), 1);
//             PermohonanKertasKandungan kertasKand1 = strService.findByIdLapor(mohonKertas.getIdKertas(), 1);
            if (kertasKand1 != null) {
                kand1 = kertasKand1.get(0).getKandungan();
            } else {
                kand1 = "";
            }
//            List<PermohonanKertasKandungan> kertasKand2 = plpService.findByIdLapor(mohonKertas.getIdKertas(), 2);
//            if (kertasKand2 != null) {
//                latarBelakangList=kertasKand2;
//            } else {
//                kand2 = "";
//            }
            latarBelakangList = plpService.findByIdLapor(mohonKertas.getIdKertas(), 2);
            List<PermohonanKertasKandungan> kertasKand3 = plpService.findByIdLapor(mohonKertas.getIdKertas(), 3);
            if (kertasKand3 != null) {
                kand3 = kertasKand3.get(0).getKandungan();
            } else {
                kand3 = "";
            }
            List<PermohonanKertasKandungan> kertasKand4 = plpService.findByIdLapor(mohonKertas.getIdKertas(), 4);
            if (kertasKand4 != null) {
                kand4 = kertasKand4.get(0).getKandungan();
            } else {
                kand4 = "";
            }
        } else {
            TanahRizabPermohonan mohonTrizab = plpService.findTanahRizabByIdPermohonan(idPermohonan);
            String hklokasi = " ";
            BigDecimal luas = BigDecimal.ZERO;
            String bpm = "";
            String koduom = " ";
            String sbb = "";

            if ((hakmilikPermohonan != null) && (hakmilikPermohonan.getLokasi() != null)) {
                hklokasi = hakmilikPermohonan.getLokasi();
            }

            String pihakname = "";
            if (pemohon != null && pemohon.getPihak() != null) {
                pihakname = pemohon.getPihak().getNama();
            }

            String mtarikmasuk = " ";
            if (permohonan != null) {
                mtarikmasuk = sdf.format(permohonan.getInfoAudit().getTarikhMasuk());
            }

            if (hakmilikPermohonan.getLuasTerlibat() != null) {
                luas = hakmilikPermohonan.getLuasTerlibat();
            }

            if (hakmilikPermohonan.getBandarPekanMukimBaru() != null) {
                bpm = hakmilikPermohonan.getBandarPekanMukimBaru().getNama();
            }

            if ((hakmilikPermohonan != null) && (hakmilikPermohonan.getKodKegunaanTanah() != null)) {
                sbb = hakmilikPermohonan.getKodKegunaanTanah().getNama();
            }

            kand0 = "BANTAHAN PEMBATALAN RIZAB";
            kand1 = " Pentadbir Tanah Melaka Tengah telah menerima satu permohonan daripada " + pihakname + " pada " + mtarikmasuk + " untuk "
                    + " Permohonan Pembatalan Perizaban " + " di " + hklokasi + ", " + " selulas " + luas + " " + koduom + " di mukim/kawasan " + bpm + ", "
                    + " Daerah Melaka Tengah untuk tujuan " + sbb + ".";
            PermohonanKertas mohonKertasSebelum = new PermohonanKertas();
            mohonKertas = plpService.findKertasByKod(idPermohonan, "RMN");
            latarBelakangList = plpService.findByIdLapor(mohonKertas.getIdKertas(), 2);
            kand3 = mohonTrizab.getCatatan();
            kand4 = "Pengarah Tanah dan Galian telah meneliti permohonan ini dan memperakukan seperti berikut : ";
        }
        
//        stageId = stageId(taskId);
//        LOG.info("stageId : "+stageId);
//        if(stageId.equals("030_PerakuanNotaSiasatan")){
//            getContext().getRequest().setAttribute("editPeraku", Boolean.TRUE);
//        }
     

    }

     public String stageId(String taskId) {
        BPelService service = new BPelService();
        stageId = null;
        if (StringUtils.isNotBlank(taskId)) {
            Task t = null;
            t = service.getTaskFromBPel(taskId, peng);
            stageId = t.getSystemAttributes().getStage();
        }
        return stageId;
    }
     
    public Resolution simpan() {

        BigDecimal luas = BigDecimal.ZERO;
        String bpm = "";
        String koduom = " ";
        String sbb = "";
        LOG.info("--------------rehydrate() Started--------------::");
        notaSiasatan = (String) getContext().getRequest().getSession().getAttribute("notaSiasatan");
        LOG.info("notaSiasatan :" + notaSiasatan);
        mohonKertas = plpService.findKertasByKod(idPermohonan, "NSIA");
        idPermohonanSebelum = permohonan.getPermohonanSebelum().getIdPermohonan();
        HakmilikPermohonan hakmilikPermohonan = new HakmilikPermohonan();
        hakmilikPermohonan = plpService.findByIdPermohonan(idPermohonan);
        if (mohonKertas != null) {
            Pengguna pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
            InfoAudit infoAudit = new InfoAudit();
            KodCawangan cawangan = new KodCawangan();
            cawangan = kodCawanganDAO.findById(pengguna.getKodCawangan().getKod());
            infoAudit = mohonKertas.getInfoAudit();
            infoAudit.setDiKemaskiniOleh(pengguna);
            infoAudit.setTarikhKemaskini(new java.util.Date());


            mohonKertas.setInfoAudit(infoAudit);
            mohonKertas.setTarikhSidang(tarikhSidang);
            plpService.simpanPermohonanKertas(mohonKertas);
            
            String hklokasi = " ";
            if ((hakmilikPermohonan != null) && (hakmilikPermohonan.getLokasi() != null)) {
                hklokasi = hakmilikPermohonan.getLokasi();
            }

            String pihakname = "";
            if (pemohon != null && pemohon.getPihak() != null) {
                pihakname = pemohon.getPihak().getNama();
            }

            String mtarikmasuk = " ";
            if (permohonan != null) {
                mtarikmasuk = sdf.format(permohonan.getInfoAudit().getTarikhMasuk());
            }

            if (hakmilikPermohonan.getLuasTerlibat() != null) {
                luas = hakmilikPermohonan.getLuasTerlibat();
            }

            if (hakmilikPermohonan.getBandarPekanMukimBaru() != null) {
                bpm = hakmilikPermohonan.getBandarPekanMukimBaru().getNama();
            }

            if ((hakmilikPermohonan != null) && (hakmilikPermohonan.getKodKegunaanTanah() != null)) {
                sbb = hakmilikPermohonan.getKodKegunaanTanah().getNama();
            }

            
            List<PermohonanKertasKandungan> kertasKand0 = plpService.findByIdLapor(mohonKertas.getIdKertas(), 0);
            List<PermohonanKertasKandungan> kertasKand1 = plpService.findByIdLapor(mohonKertas.getIdKertas(), 1);
            List<PermohonanKertasKandungan> kertasKand3 = plpService.findByIdLapor(mohonKertas.getIdKertas(), 3);
            List<PermohonanKertasKandungan> kertasKand4 = plpService.findByIdLapor(mohonKertas.getIdKertas(), 4);
          
             if(!kertasKand0.isEmpty()){
//                 addSimpleMessage("0");  
                 PermohonanKertasKandungan kertasK0 = kertasKand0.get(0);
                 kertasK0.setCawangan(cawangan);
                 kertasK0.setInfoAudit(infoAudit);
                 kertasK0.setKandungan(kand0);
                 plpService.simpanPermohonanKertasKandungan(kertasK0);
             }
             
             if(!kertasKand1.isEmpty()){
//                 addSimpleMessage("1");  
                 PermohonanKertasKandungan kertasK1 = kertasKand1.get(0);
                 kertasK1.setCawangan(cawangan);
                 kertasK1.setInfoAudit(infoAudit);
                 kertasK1.setKandungan(kand1);
                 plpService.simpanPermohonanKertasKandungan(kertasK1);
             }      

 
            latarBelakangListRMN= plpService.findByIdLapor(mohonKertas.getIdKertas(), 2);
            LOG.info("id mohon kertas RMN adalah : " + mohonKertas.getIdKertas());
            if (latarBelakangListRMN!= null) {
                for (PermohonanKertasKandungan pkk : latarBelakangListRMN) {
                    PermohonanKertasKandungan kertas2 = latarBelakangListRMN.get(0);
                    kertas2.setCawangan(cawangan);
                    kertas2.setInfoAudit(infoAudit);
                    kertas2.setKertas(mohonKertas);
                    kertas2.setBil(2);
                    kertas2.setSubtajuk(pkk.getSubtajuk());
                    LOG.info("pkk.getSubtajuk() : "+pkk.getSubtajuk());
                    kertas2.setKandungan(pkk.getKandungan());
                    plpService.simpanPermohonanKertasKandungan(kertas2);
                }
            } else {
                LOG.info("latarBelakangListSebelum is null");
            }             
        
 
            if(!kertasKand3.isEmpty()){
//                 addSimpleMessage("3"); 
                 PermohonanKertasKandungan kertasK3 =  kertasKand3.get(0);
                 kertasK3.setCawangan(cawangan);
                 kertasK3.setInfoAudit(infoAudit);
                 kertasK3.setKandungan(kand3);
                 plpService.simpanPermohonanKertasKandungan(kertasK3);
             }    
            
            if(!kertasKand4.isEmpty()){
//                 addSimpleMessage("4"); 
                 PermohonanKertasKandungan kertasK4 =  kertasKand4.get(0);
                 kertasK4.setCawangan(cawangan);
                 kertasK4.setInfoAudit(infoAudit);
                 kertasK4.setKandungan(kand4);
                 plpService.simpanPermohonanKertasKandungan(kertasK4);
             }      

            addSimpleMessage("Maklumat telah berjaya dikemaskini.");            
            
        } else {
            mohonKertas = new PermohonanKertas();
            Pengguna pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
            InfoAudit infoAudit = new InfoAudit();
            KodCawangan cawangan = new KodCawangan();
            cawangan = kodCawanganDAO.findById(pengguna.getKodCawangan().getKod());
            infoAudit.setDimasukOleh(pengguna);
            infoAudit.setTarikhMasuk(new java.util.Date());
            mohonKertas.setTajuk("Nota Siasatan");
            KodDokumen kodDokumen = kodDokumenDAO.findById("NSIA");
            mohonKertas.setKodDokumen(kodDokumen);
            mohonKertas.setCawangan(cawangan);
            mohonKertas.setInfoAudit(infoAudit);
            mohonKertas.setPermohonan(permohonan);
            mohonKertas.setTarikhSidang(tarikhSidang);
            plpService.simpanPermohonanKertas(mohonKertas);

            String hklokasi = " ";
            if ((hakmilikPermohonan != null) && (hakmilikPermohonan.getLokasi() != null)) {
                hklokasi = hakmilikPermohonan.getLokasi();
            }

            String pihakname = "";
            if (pemohon != null && pemohon.getPihak() != null) {
                pihakname = pemohon.getPihak().getNama();
            }

            String mtarikmasuk = " ";
            if (permohonan != null) {
                mtarikmasuk = sdf.format(permohonan.getInfoAudit().getTarikhMasuk());
            }

            if (hakmilikPermohonan.getLuasTerlibat() != null) {
                luas = hakmilikPermohonan.getLuasTerlibat();
            }

            if (hakmilikPermohonan.getBandarPekanMukimBaru() != null) {
                bpm = hakmilikPermohonan.getBandarPekanMukimBaru().getNama();
            }

            if ((hakmilikPermohonan != null) && (hakmilikPermohonan.getKodKegunaanTanah() != null)) {
                sbb = hakmilikPermohonan.getKodKegunaanTanah().getNama();
            }

            PermohonanKertasKandungan kertas0 = new PermohonanKertasKandungan();
            PermohonanKertasKandungan kertas1 = new PermohonanKertasKandungan();
            PermohonanKertasKandungan kertas3 = new PermohonanKertasKandungan();
            PermohonanKertasKandungan kertas4 = new PermohonanKertasKandungan();


            kertas0.setCawangan(cawangan);
            kertas0.setInfoAudit(infoAudit);
            kertas0.setKertas(mohonKertas);
            kertas0.setBil(0);
            kertas0.setSubtajuk("1");
            kertas0.setKandungan(kand0);
            plpService.simpanPermohonanKertasKandungan(kertas0);

            kertas1.setCawangan(cawangan);
            kertas1.setInfoAudit(infoAudit);
            kertas1.setKertas(mohonKertas);
            kertas1.setBil(1);
            kertas1.setSubtajuk("1");
//            perihalPermohonan = " Pentadbir Tanah Melaka Tengah telah menerima satu permohonan daripada " + pihakname + " pada " + mtarikmasuk + " untuk "
//                    + " Permohonan Pembatalan Perizaban " + " di " + hklokasi + ", " + " selulas " + luas + " " + koduom + " di mukim/kawasan " + bpm + ", "
//                    + " Daerah Melaka Tengah untuk tujuan " + sbb + ".";
            kertas1.setKandungan(kand1);
            plpService.simpanPermohonanKertasKandungan(kertas1);

            PermohonanKertas mohonKertasRMN = new PermohonanKertas();
            mohonKertasRMN = plpService.findKertasByKod(idPermohonan, "RMN");
            latarBelakangListRMN= plpService.findByIdLapor(mohonKertasRMN.getIdKertas(), 2);
            if (latarBelakangListRMN!= null) {
                for (PermohonanKertasKandungan pkk : latarBelakangListRMN) {
                    PermohonanKertasKandungan kertas2 = new PermohonanKertasKandungan();
                    kertas2.setCawangan(cawangan);
                    kertas2.setInfoAudit(infoAudit);
                    kertas2.setKertas(mohonKertas);
                    kertas2.setBil(2);
                    kertas2.setSubtajuk(pkk.getSubtajuk());
                    LOG.info("pkk.getSubtajuk() : "+pkk.getSubtajuk());
                    kertas2.setKandungan(pkk.getKandungan());
                    plpService.simpanPermohonanKertasKandungan(kertas2);
                }
            } else {
                LOG.info("latarBelakangListSebelum is null");
            }

//            TanahRizabPermohonan mohonTrizab = plpService.findTanahRizabByIdPermohonan(idPermohonan);
            kertas3.setCawangan(cawangan);
            kertas3.setInfoAudit(infoAudit);
            kertas3.setKertas(mohonKertas);
            kertas3.setBil(3);
            kertas3.setSubtajuk("1");
//            asasBantahan = mohonTrizab.getCatatan();
            kertas3.setKandungan(kand3);
            plpService.simpanPermohonanKertasKandungan(kertas3);

            kertas4.setCawangan(cawangan);
            kertas4.setInfoAudit(infoAudit);
            kertas4.setKertas(mohonKertas);
            kertas4.setBil(4);
            kertas4.setSubtajuk("1");
//            syorPerakuan = "Pengarah Tanah dan Galian telah meneliti permohonan ini dan memperakukan seperti berikut : ";
            kertas4.setKandungan(kand4);
            plpService.simpanPermohonanKertasKandungan(kertas4);
            addSimpleMessage("Maklumat telah berjaya disimpan.");
        }
        
        return new JSP("pelupusan/nota_siasatan.jsp").addParameter("tab", "true");

    }
}
