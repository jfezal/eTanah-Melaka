/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.stripes.pengambilan.aduan;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.dao.KodBandarPekanMukimDAO;
import etanah.dao.KodJenisPengenalanDAO;
import etanah.dao.KodLotDAO;
import etanah.dao.KodNegeriDAO;
import etanah.dao.KodSeksyenDAO;
import etanah.dao.KodUOMDAO;
import etanah.dao.KodUrusanDAO;
import etanah.dao.PermohonanDAO;
import etanah.model.Dokumen;
import etanah.model.Hakmilik;
import etanah.model.HakmilikPermohonan;
import etanah.model.InfoAudit;
import etanah.model.KodBandarPekanMukim;
import etanah.model.KodCawangan;
import etanah.model.KodJenisPengenalan;
import etanah.model.KodNegeri;
import etanah.model.KodSeksyen;
import etanah.model.KodUOM;
import etanah.model.KodUrusan;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.model.PermohonanAduan;
import etanah.model.TanahRizabPermohonan;
import etanah.sequence.GeneratorIdPermohonan;
import etanah.service.EnforceService;
import etanah.service.common.HakmilikPermohonanService;
import etanah.service.common.HakmilikService;
import etanah.service.pengambilan.aduan.AduanService;
import etanah.view.ListUtil;
import etanah.view.etanahActionBeanContext;
import etanah.view.stripes.pelupusan.disClass.DisLaporanTanahService;
import etanah.workflow.WorkFlowService;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.RedirectResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.StreamingResolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

//import java.time.LocalDateTime;
//import java.time.format.DateTimeFormatter; 
/**
 *
 * @author user
 */
@UrlBinding("/pengambilan/aduan_kerosakan/terima_aduan")
public class TerimaAduanActionBean extends AbleActionBean {

    private static Logger logger = Logger.getLogger(TerimaAduanActionBean.class);

    Permohonan mohon;
    PermohonanAduan mohonAduan;
    String idPermohonan;
    String nama;
    String noKp;
    String noKPengenalan;
    String kod;
    // my input
    String alamat1;
    String alamat2;
    String alamat3;
    String alamat4;

    String poskod;
    String kodNegeri;
    String noTel;
    String email;
    String hubungan;

    String negeri;

    String aduan;

    private String kodCawangan;
    private String kodDaerah;
    private Permohonan permohonan;

    private KodCawangan cawangan;

    @Inject
    AduanService aduanService;
    @Inject
    private GeneratorIdPermohonan idGenerator;
    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    KodUrusanDAO kodUrusanDAO;
    @Inject
    private etanah.Configuration conf;
    String report_p_id_mohon;
    @Inject
    KodUOMDAO kodUOMDAO;
    @Inject
    KodNegeriDAO kodNegeriDAO;
    @Inject
    ListUtil listUtil;

    private List<KodBandarPekanMukim> senaraiKodBPM;
    private List<KodBandarPekanMukim> listBandarPekanMukim;

    String idHakmilik;
    String noLot;
    String luas;
    String daerah;
    String bpm;
    String kegunaan;

    private List<HakmilikPermohonan> hakmilikPermohonanList;
    @Inject
    HakmilikPermohonanService hakmilikpermohonanservice;
    @Inject
    HakmilikService hakmilikService;
    @Inject
    KodLotDAO kodLotDAO;

    @Inject
    Hakmilik hakmilik;
    @Inject
    EnforceService enforceService;
    @Inject
    DisLaporanTanahService disLaporanTanahService;
    @Inject
    KodBandarPekanMukimDAO kodBandarPekanMukimDAO;
    @Inject
    KodUOM KodOUM;
    @Inject
    KodSeksyenDAO kodSeksyenDAO;
    @Inject
    KodJenisPengenalanDAO kodJenisPengenalanDAO;

    private String kodLot;
//    private String noLot;
    private TanahRizabPermohonan rizab;
    private BigDecimal totalLuas = new BigDecimal(0);
    private BigDecimal amount = new BigDecimal(0);
    private BigDecimal amountMH = new BigDecimal(0);
    private BigDecimal convH = new BigDecimal(0);
    private BigDecimal luasT;
    private String kodUnit;
    private int bandarPekanMukimBaru;

    private String namaDaerah;

    private List<KodSeksyen> listKodSeksyen;
    private String kodSeksyen;

    private ArrayList<Permohonan> senaraiUrusan = new ArrayList<Permohonan>();
    String idH;

    Dokumen dokumen;

    @DefaultHandler
    public Resolution showForm() {
        String idPermohonan = getContext().getRequest().getParameter("idPermohonan");
        if (StringUtils.isNotBlank(idPermohonan)) {
            permohonan = permohonanDAO.findById(idPermohonan);
            hakmilikPermohonanList = hakmilikpermohonanservice.findByIdPermohonan(permohonan.getIdPermohonan());
            setData(permohonan);
        }
        return new JSP("pengambilan/aduan_kerosakan/terima_aduan1.jsp").addParameter("idPermohonan", idPermohonan);

    }

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {

    }// end method

    public Resolution generatIdMohon() {
        String idPermohonan = getContext().getRequest().getParameter("idPermohonan");

        if (StringUtils.isNotBlank(idPermohonan)) {
            permohonan = permohonanDAO.findById(idPermohonan);
            if (permohonan != null) {
            } else {
                permohonan = generateMohon(idPermohonan);
            }

        } else {
            permohonan = generateMohon(idPermohonan);

        }
        simpanData(permohonan.getIdPermohonan());
        return new StreamingResolution("text/plain", permohonan.getIdPermohonan());
    }

    public boolean simpanData(String idPermohonan) {
        boolean s = false;

        if (StringUtils.isEmpty(idPermohonan)) {
            mohon = generateMohon(idPermohonan);
        } else {
            System.out.println("idPermohonan not null");
            mohon = permohonanDAO.findById(idPermohonan);
        }
        Pengguna pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        KodCawangan caw = pengguna.getKodCawangan();

        KodUrusan ku = kodUrusanDAO.findById("SEK4A");

        KodNegeri kodNegeri = kodNegeriDAO.findById(negeri);
        if (StringUtils.isNotBlank(kod)) {
            KodJenisPengenalan kjp = kodJenisPengenalanDAO.findById(kod);
            if (kjp != null) {
                mohon.setPenyerahJenisPengenalan(kjp);
            }
        }

        if (kodNegeri != null) {
            mohon.setPenyerahNegeri(kodNegeri);

        }
        mohon.setKodUrusan(ku);
        mohon.setPenyerahNama(nama);
        mohon.setPenyerahNoPengenalan(noKp);
        mohon.setPenyerahNoTelefon1(noTel);
        mohon.setPenyerahPoskod(poskod);
        mohon.setPenyerahAlamat1(alamat1);
        mohon.setPenyerahAlamat2(alamat2);
        mohon.setPenyerahAlamat3(alamat3);
        mohon.setPenyerahAlamat4(alamat4);
        mohon.setPenyerahEmail(email);
        mohon.setCawangan(caw);
        // start aduan insert 
        mohonAduan = aduanService.findAduanByIdMohon(idPermohonan);
        if (mohonAduan != null) {
        } else {
            mohonAduan = new PermohonanAduan();
        }
        mohonAduan.setPermohonan(mohon);
        mohonAduan.setPerihal(aduan);
        mohonAduan.setCawangan(caw);
        InfoAudit ia = new InfoAudit();
        ia.setDimasukOleh(pengguna);
        ia.setTarikhMasuk(new java.util.Date());
        mohonAduan.setInfoAudit(ia);
        aduanService.savePermohonan(mohon);
        
        aduanService.saveMaklumatAduan(mohonAduan);
        s = true;
        return s;
    }

    public Resolution simpan() {
        idPermohonan = getContext().getRequest().getParameter("idPermohonan");

        simpanData(idPermohonan);
                    hakmilikPermohonanList = hakmilikpermohonanservice.findIDMHByIDMohon(idPermohonan);

        addSimpleMessage("Maklumat berjaya disimpan");
        return new RedirectResolution(TerimaAduanActionBean.class).addParameter("idPermohonan", idPermohonan);
    }

    public Permohonan generateMohon(String idMohon) {

        System.out.println("start generateMohon +++>");
        Pengguna pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);

        if (StringUtils.isNotBlank(idMohon)) {
            System.out.println("idPermohonan ++> " + idMohon);
            mohon = permohonanDAO.findById(idMohon);
        } else {
            KodCawangan caw = pengguna.getKodCawangan();

            KodUrusan ku = kodUrusanDAO.findById("SEK4A");
            idMohon = idGenerator.generate(conf.getProperty("kodNegeri"), caw, ku);
            mohon = new Permohonan();
            mohon.setIdPermohonan(idMohon);
            mohon.setKodUrusan(ku);
            mohon.setCawangan(cawangan);
            mohon = aduanService.saveMohon(mohon, pengguna);
        }
        System.out.println(" idPermohonan dalam generateMohon ++> " + idMohon);
        return mohon;

    }// end method

    public Resolution selesai() throws Exception {
        Pengguna pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);

        System.out.println("inside selesai  ");
        idPermohonan = getContext().getRequest().getParameter("idPermohonan");
        simpanData(idPermohonan);
        Permohonan p = permohonanDAO.findById(idPermohonan);

        WorkFlowService.initiateTask(p.getKodUrusan().getIdWorkflow(),
                p.getIdPermohonan(), p.getCawangan().getKod() + ",00", pengguna.getIdPengguna(),
                p.getKodUrusan().getNama());

        dokumen = new Dokumen();
        dokumen = aduanService.generateReport(p, pengguna);

        setDokumen(dokumen);
        System.out.println("dokumen fizikal " + dokumen.getTajuk());
        System.out.println("dokumen id " + dokumen.getIdDokumen());

        senaraiUrusan.add(p);
        //todo
        return new JSP("pengambilan/aduan_kerosakan/cetak_akuan_terima.jsp");

    }

    public Resolution hakMilikPopup() {

        System.out.println("start hakMilikPopup ++");
        Pengguna pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);

        String namaD = pengguna.getKodCawangan().getDaerah().getNama();
        System.out.println("nama Daerah" + namaD);
        setNamaDaerah(namaD);

        if (permohonan != null) {
            kodCawangan = pengguna.getKodCawangan().getKod();
            kodDaerah = pengguna.getKodCawangan().getDaerah().getKod();
        } else {

            kodCawangan = pengguna.getKodCawangan().getKod();
            kodDaerah = pengguna.getKodCawangan().getDaerah().getKod();

        }

        kodCawangan = pengguna.getKodCawangan().getKod();
        kodDaerah = pengguna.getKodCawangan().getDaerah().getKod();

        System.out.println("kodDaerah ++> " + kodDaerah);
        System.out.println("kodCawangan ++> " + kodCawangan);

        if (kodCawangan.equals("00")) {
            senaraiKodBPM = listUtil.getSenaraiKodBPMByDaerah(kodDaerah);
        } else {
            senaraiKodBPM = listUtil.getSenaraiKodBPMByDaerahAndCawangan(kodDaerah, kodCawangan);
        }

        return new JSP("pengambilan/aduan_kerosakan/kemasukan_hakmilikAduan.jsp").addParameter("popup", "true").addParameter("showForm", "false");
    }

    public Resolution findKodSeksyen() {
        String kodBpm = getContext().getRequest().getParameter("kod");
        //Pengguna pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        listKodSeksyen = disLaporanTanahService.getPelupusanService().getSenaraiKodSeksyen(kodBpm);

        return new JSP("pengambilan/aduan_kerosakan/kemasukan_hakmilikAduan.jsp").addParameter("popup", "true").addParameter("showForm", "false");
    }

    public Resolution searchHakmilik() {
        System.out.println("Start searchHakmilik ++>");
        idPermohonan = getContext().getRequest().getParameter("idPermohonan");
        idH = getContext().getRequest().getParameter("idH");
        hakmilik = hakmilikService.findById(idH);
        idHakmilik = hakmilik.getIdHakmilik();
        return new JSP("pengambilan/aduan_kerosakan/kemasukan_hakmilikAduan.jsp").addParameter("popup", "true").addParameter("idPermohonan", idPermohonan);
    }

    public Resolution saveHakmilik() {

        idPermohonan = getContext().getRequest().getParameter("idPermohonan");
        System.out.println("2");
        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        kodSeksyen = getContext().getRequest().getParameter("kodSeksyen");
        logger.info("kod seksyen - " + kodSeksyen);
        Permohonan permohonan = permohonanDAO.findById(idPermohonan);
        hakmilik = hakmilikService.findById(idHakmilik);

        InfoAudit info = peng.getInfoAudit();
        info.setDimasukOleh(peng);
        info.setTarikhMasuk(new java.util.Date());
        HakmilikPermohonan hmp = new HakmilikPermohonan();
        hmp.setInfoAudit(info);
        hmp.setPermohonan(permohonan);
        hmp.setHakmilik(hakmilik);
        hmp.setLuasTerlibat(hakmilik.getLuas());
        hmp.setKodUnitLuas(hakmilik.getKodUnitLuas());
        hmp.setBandarPekanMukimBaru(hakmilik.getBandarPekanMukim());
        hmp.setKodHakmilik(hakmilik.getKodHakmilik());
        hmp.setLot(hakmilik.getLot());
        hmp.setNoLot(hakmilik.getNoLot());
        hakmilikService.saveOrUpdatehakmilikpermohonan(hmp);

        addSimpleMessage("Maklumat telah berjaya latest ++> ");
        return new JSP("pengambilan/aduan_kerosakan/kemasukan_hakmilikAduan.jsp").addParameter("popup", "true").addParameter("idPermohonan", idPermohonan);

    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getNoKp() {
        return noKp;
    }

    public void setNoKp(String noKp) {
        this.noKp = noKp;
    }

    public String getIdPermohonan() {
        return idPermohonan;
    }

    public void setIdPermohonan(String idPermohonan) {
        this.idPermohonan = idPermohonan;
    }

    public String getAduan() {
        return aduan;
    }

    public void setAduan(String aduan) {
        this.aduan = aduan;
    }

    public Permohonan getMohon() {
        return mohon;
    }

    public void setMohon(Permohonan mohon) {
        this.mohon = mohon;
    }

    public String getAlamat1() {
        return alamat1;
    }

    public void setAlamat1(String alamat1) {
        this.alamat1 = alamat1;
    }

    public String getAlamat2() {
        return alamat2;
    }

    public void setAlamat2(String alamat2) {
        this.alamat2 = alamat2;
    }

    public String getAlamat3() {
        return alamat3;
    }

    public void setAlamat3(String alamat3) {
        this.alamat3 = alamat3;
    }

    public String getAlamat4() {
        return alamat4;
    }

    public void setAlamat4(String alamat4) {
        this.alamat4 = alamat4;
    }

    public String getPoskod() {
        return poskod;
    }

    public void setPoskod(String poskod) {
        this.poskod = poskod;
    }

    public String getKodNegeri() {
        return kodNegeri;
    }

    public void setKodNegeri(String kodNegeri) {
        this.kodNegeri = kodNegeri;
    }

    public String getNoTel() {
        return noTel;
    }

    public void setNoTel(String noTel) {
        this.noTel = noTel;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getHubungan() {
        return hubungan;
    }

    public void setHubungan(String hubungan) {
        this.hubungan = hubungan;
    }

    public String getKodCawangan() {
        return kodCawangan;
    }

    public void setKodCawangan(String kodCawangan) {
        this.kodCawangan = kodCawangan;
    }

    public String getKodDaerah() {
        return kodDaerah;
    }

    public void setKodDaerah(String kodDaerah) {
        this.kodDaerah = kodDaerah;
    }

    public List<KodBandarPekanMukim> getSenaraiKodBPM() {
        return senaraiKodBPM;
    }

    public void setSenaraiKodBPM(List<KodBandarPekanMukim> senaraiKodBPM) {
        this.senaraiKodBPM = senaraiKodBPM;
    }

    public List<KodBandarPekanMukim> getListBandarPekanMukim() {
        return listBandarPekanMukim;
    }

    public void setListBandarPekanMukim(List<KodBandarPekanMukim> listBandarPekanMukim) {
        this.listBandarPekanMukim = listBandarPekanMukim;
    }

    public KodCawangan getCawangan() {
        return cawangan;
    }

    public void setCawangan(KodCawangan cawangan) {
        this.cawangan = cawangan;
    }

    public BigDecimal getTotalLuas() {
        return totalLuas;
    }

    public void setTotalLuas(BigDecimal totalLuas) {
        this.totalLuas = totalLuas;
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

    public BigDecimal getConvH() {
        return convH;
    }

    public void setConvH(BigDecimal convH) {
        this.convH = convH;
    }

    public Permohonan getPermohonan() {
        return permohonan;
    }

    public void setPermohonan(Permohonan permohonan) {
        this.permohonan = permohonan;
    }

    public String getNamaDaerah() {
        return namaDaerah;
    }

    public void setNamaDaerah(String namaDaerah) {
        this.namaDaerah = namaDaerah;
    }

    public List<KodSeksyen> getListKodSeksyen() {
        return listKodSeksyen;
    }

    public void setListKodSeksyen(List<KodSeksyen> listKodSeksyen) {
        this.listKodSeksyen = listKodSeksyen;
    }

    public List<HakmilikPermohonan> getHakmilikPermohonanList() {
        return hakmilikPermohonanList;
    }

    public void setHakmilikPermohonanList(List<HakmilikPermohonan> hakmilikPermohonanList) {
        this.hakmilikPermohonanList = hakmilikPermohonanList;
    }

    public String getKodLot() {
        return kodLot;
    }

    public void setKodLot(String kodLot) {
        this.kodLot = kodLot;
    }

    public int getBandarPekanMukimBaru() {
        return bandarPekanMukimBaru;
    }

    public void setBandarPekanMukimBaru(int bandarPekanMukimBaru) {
        this.bandarPekanMukimBaru = bandarPekanMukimBaru;
    }

    public String getIdHakmilik() {
        return idHakmilik;
    }

    public void setIdHakmilik(String idHakmilik) {
        this.idHakmilik = idHakmilik;
    }

    public Hakmilik getHakmilik() {
        return hakmilik;
    }

    public void setHakmilik(Hakmilik hakmilik) {
        this.hakmilik = hakmilik;
    }

    public String getNoLot() {
        return noLot;
    }

    public void setNoLot(String noLot) {
        this.noLot = noLot;
    }

    public BigDecimal getLuasT() {
        return luasT;
    }

    public void setLuasT(BigDecimal luasT) {
        this.luasT = luasT;
    }

    public String getKodUnit() {
        return kodUnit;
    }

    public void setKodUnit(String kodUnit) {
        this.kodUnit = kodUnit;
    }

    public String getKodSeksyen() {
        return kodSeksyen;
    }

    public void setKodSeksyen(String kodSeksyen) {
        this.kodSeksyen = kodSeksyen;
    }

    public String getNegeri() {
        return negeri;
    }

    public void setNegeri(String negeri) {
        this.negeri = negeri;
    }

    public String getKod() {
        return kod;
    }

    public void setKod(String kod) {
        this.kod = kod;
    }

    public ArrayList<Permohonan> getSenaraiUrusan() {
        return senaraiUrusan;
    }

    public void setSenaraiUrusan(ArrayList<Permohonan> senaraiUrusan) {
        this.senaraiUrusan = senaraiUrusan;
    }

    public String getIdH() {
        return idH;
    }

    public void setIdH(String idH) {
        this.idH = idH;
    }

    public Dokumen getDokumen() {
        return dokumen;
    }

    public void setDokumen(Dokumen dokumen) {
        this.dokumen = dokumen;
    }

    private void setData(Permohonan permohonan) {
        nama = permohonan.getPenyerahNama();
        noKp = permohonan.getPenyerahNoPengenalan();
        noKPengenalan = permohonan.getPenyerahNoPengenalan();
        kod = permohonan.getPenyerahJenisPengenalan() != null ? permohonan.getPenyerahJenisPengenalan().getKod() : "";
        // my input
        alamat1 = permohonan.getPenyerahAlamat1();
        alamat2 = permohonan.getPenyerahAlamat2();
        alamat3 = permohonan.getPenyerahAlamat3();
        alamat4 = permohonan.getPenyerahAlamat4();

        poskod = permohonan.getPenyerahPoskod();
        kodNegeri = permohonan.getPenyerahNegeri() != null ? permohonan.getPenyerahNegeri().getKod() : "";
        noTel = permohonan.getPenyerahNoTelefon1();
        email = permohonan.getPenyerahEmail();
//     hubungan=permohonan.getPenyerahAlamat1();
        if (permohonan.getPenyerahNegeri() != null) {
            negeri = permohonan.getPenyerahNegeri().getKod();
        }
        PermohonanAduan permohonanAduan = aduanService.findAduanByIdMohon(idPermohonan);
        aduan = permohonanAduan != null ? permohonanAduan.getPerihal() : "";
    }

    public String getLuas() {
        return luas;
    }

    public void setLuas(String luas) {
        this.luas = luas;
    }

    public String getDaerah() {
        return daerah;
    }

    public void setDaerah(String daerah) {
        this.daerah = daerah;
    }

    public String getBpm() {
        return bpm;
    }

    public void setBpm(String bpm) {
        this.bpm = bpm;
    }

    public String getKegunaan() {
        return kegunaan;
    }

    public void setKegunaan(String kegunaan) {
        this.kegunaan = kegunaan;
    }
    
    

}// end class
