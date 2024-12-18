/*
 * To change this template, choose Tools | Templates 
 * and open the template in the editor.
 */

package etanah.view.stripes.pengambilan;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.Configuration;
import etanah.dao.AkaunDAO;
import etanah.dao.AkuanTerimaDepositDAO;
import etanah.dao.CaraBayaranDAO;
import etanah.dao.DokumenDAO;
import etanah.dao.DokumenKewanganDAO;
import etanah.dao.HakmilikDAO;
import etanah.dao.KandunganFolderDAO;
import etanah.dao.KodCaraBayaranDAO;
import etanah.dao.KodDokumenDAO;
import etanah.dao.KodKlasifikasiDAO;
import etanah.dao.KodKutipanDAO;
import etanah.dao.KodStatusAkaunDAO;
import etanah.dao.KodStatusDokumenKewanganDAO;
import etanah.dao.KodStatusTransaksiKewanganDAO;
import etanah.dao.KodTransaksiDAO;
import etanah.dao.KodUrusanDAO;
import etanah.dao.PermohonanDAO;
import etanah.dao.PermohonanPihakDAO;
import etanah.dao.PermohonanTuntutanBayarDAO;
import etanah.dao.PihakDAO;
import etanah.dao.TransaksiDAO;
import etanah.model.*;
import etanah.model.ambil.HakmilikPerbicaraan;
import etanah.model.ambil.Penilaian;
import etanah.model.ambil.PerbicaraanKehadiran;
import etanah.sequence.GeneratorIdPermohonan;
import etanah.sequence.GeneratorNoResit2;
import etanah.service.AkaunService;
import etanah.service.KaunterService;
import etanah.service.PengambilanAduanService;
import etanah.service.PengambilanService;
import etanah.service.PermohonanSupayaBantahanService;
import etanah.service.common.PengambilanDepositService;
import etanah.view.etanahActionBeanContext;
import java.io.File;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.RedirectResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import org.apache.log4j.Logger;
import org.hibernate.LockMode;
import org.hibernate.Session;
import org.hibernate.Transaction;
import etanah.report.ReportUtil;
import etanah.service.BPelService;
import etanah.view.kaunter.UrusanValue;
import etanah.view.stripes.hasil.ModKutipService;
import etanah.view.stripes.hasil.PenyataPemungutService;
import etanah.workflow.WorkFlowService;
import java.util.HashMap;
import java.util.Map;
import net.sourceforge.stripes.action.*;
import net.sourceforge.stripes.controller.LifecycleStage;
import oracle.bpel.services.workflow.task.model.Task;
import oracle.bpel.services.workflow.verification.IWorkflowContext;
import org.apache.commons.lang.StringUtils;

/**
 *
 * @author Rajesh
 */
@UrlBinding("/pengambilan/kemasukanBantahanMahkamah")
public class KemasukkanIdhahmilik_BantahanMahkamahActionBean extends AbleActionBean {

    @Inject
    private etanah.Configuration conf;
    @Inject
    PengambilanAduanService aduanService;
    @Inject
    PengambilanService pengambilanService;
    @Inject
    PermohonanSupayaBantahanService permohonanSupayaBantahanService;
    @Inject
    KaunterService kaunterService;
    @Inject
    AkaunService akaunService;
    @Inject
    PengambilanDepositService pengambilanDepositService;
    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    HakmilikDAO hakmilikDAO;
    @Inject
    PihakDAO pihakDAO;
    @Inject
    PermohonanPihakDAO permohonanPihakDAO;
    @Inject
    KodUrusanDAO kodUrusanDAO;
    @Inject
    KodKlasifikasiDAO kodKlasifikasiDAO;
    @Inject
    KodDokumenDAO kodDokumenDAO;
    @Inject
    DokumenDAO dokumenDAO;
    @Inject
    KodStatusDokumenKewanganDAO kodStatusDokumenKewanganDAO;
    @Inject
    CaraBayaranDAO caraBayaranDAO;
    @Inject
    DokumenKewanganDAO dokumenKewanganDAO;
    @Inject
    KodTransaksiDAO kodTransaksiDAO;
    @Inject
    KodStatusAkaunDAO kodStatusAkaunDAO;
    @Inject
    AkaunDAO akaunDAO;
    @Inject
    KodStatusTransaksiKewanganDAO kodStatusDAO;
    @Inject
    TransaksiDAO transaksiDAO;
    @Inject
    PermohonanTuntutanBayarDAO permohonanTuntutanBayarDAO;
    @Inject
    AkuanTerimaDepositDAO akuanTerimaDepositDAO;
    @Inject
    KandunganFolderDAO kandunganFolderDAO;
    @Inject
    KodCaraBayaranDAO kodCaraBayaranDAO;
    @Inject
    GeneratorIdPermohonan idPermohonanGenerator;
    @Inject
    GeneratorNoResit2 noResitGenerator;
    @Inject
    private ReportUtil reportUtil;
    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;
    @Inject
    private PenyataPemungutService penyataPemungutService;
    @Inject
    private ModKutipService modKutip;
    @Inject
    KodKutipanDAO kodKutipDAO;

    private static Logger logger = Logger.getLogger(KemasukkanIdhahmilik_BantahanMahkamahActionBean.class);
    private String idPermohonan;
    private String mod = "";
    private Permohonan permohonan;
    private Permohonan permohonanSblm;
    private PermohonanPihak permohonanPihak;
    private Hakmilik hakmilik;
    private FolderDokumen folderDokumen;
    private Pemohon pmohon;
    private String idHakmilik;
    private String selectedIdSblm;
    private String selectedIdHakmilik;
    private String selectedIdPihak;
    private String resitNo;
    private BigDecimal amaunTotal;
    private BigDecimal amaunTawarPampasan;
    private BigDecimal amaunTambahPampasan;
    private BigDecimal amaunTawarRosak;
    private BigDecimal total = null;
    private List<Permohonan> senaraiPermohonan = new ArrayList<Permohonan>();
    private List<KandunganFolder> senaraiKandungan = new ArrayList<KandunganFolder>();
    private ArrayList<String> senaraiKodUrusan = new ArrayList<String>();
    private List<KandunganFolder> kandunganFolderTamb = new ArrayList<KandunganFolder>();
    private List<KodDokumen> kodDokumenNotRequired = new ArrayList<KodDokumen>();
    private ArrayList<CaraBayaran> senaraiCaraBayaran = new ArrayList<CaraBayaran>();
    private ArrayList<UrusanValue> senaraiPermohonan1 = new ArrayList<UrusanValue>();
    private List<Akaun> akaunDeposit;
    private KodUrusan kodUrusan;
    public String stageId = "";
    IWorkflowContext ctxW = null;

    @DefaultHandler
    public Resolution showForm() {
        return new JSP("pengambilan/melaka/bantahanmahkamah/KemasukkanIdhahmilik_BantahanMahkamah.jsp");
    }

    public Resolution showForm2() {
        calculate();
        return new JSP("pengambilan/melaka/bantahanmahkamah/nilaiPampasanMahkamah.jsp").addParameter("tab", "true");
    }

    public void calculate(){
//        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
//        permohonan = permohonanDAO.findById(idPermohonan);
//
//        amaunTotal = BigDecimal.ZERO;
//
//        List<HakmilikPermohonan> senaraiHakmilikPermohonan = new ArrayList<HakmilikPermohonan>();
//        senaraiHakmilikPermohonan = permohonan.getSenaraiHakmilik();
//        if(senaraiHakmilikPermohonan.size() > 0){
//            hakmilik = senaraiHakmilikPermohonan.get(0).getHakmilik();
//            if(hakmilik != null) {
//                List<PermohonanPihak> senaraiPermohonanPihak = new ArrayList<PermohonanPihak>();
//                senaraiPermohonanPihak = pengambilanService.getSenaraiPermohonPihakByIdHakmilik(idPermohonan, hakmilik.getIdHakmilik());
//                if(senaraiPermohonanPihak.size() > 0){
//                    permohonanPihak = senaraiPermohonanPihak.get(0);
//                    if(permohonanPihak != null){
//                        PermohonanMahkamah mahkamah =  permohonanSupayaBantahanService.findPermohonanMahkamahByidMP(permohonanPihak.getIdPermohonanPihak());
//                        if(mahkamah != null){
//                            amaunTawarPampasan = mahkamah.getAmnTawarPampasan();
//                            amaunTambahPampasan=mahkamah.getTambahanPampasan();
//                            amaunTawarRosak=mahkamah.getAmnTawarRosak();
//                        }
//                    }
//
//
//                    if(permohonan.getPermohonanSebelum() != null){
//                        HakmilikPermohonan hakmilikPermohonan = pengambilanService.findByIdHakmilikIdPermohonan(permohonan.getPermohonanSebelum().getIdPermohonan(), hakmilik.getIdHakmilik());
//                        PermohonanPihak pp = pengambilanService.getPmohonPihakByIdHakmilikIdPihak(permohonan.getPermohonanSebelum().getIdPermohonan(),hakmilik.getIdHakmilik(),permohonanPihak.getPihak().getIdPihak());
//                        if(hakmilikPermohonan != null) {
//                            HakmilikPerbicaraan hakmilikPerbicaraan = pengambilanService.getHakmilikPerbicaraanByidMH(hakmilikPermohonan.getId());
//                            if(hakmilikPerbicaraan != null && pp != null){
//                                PerbicaraanKehadiran kehadiran = pengambilanService.getPerbicaraanKehadiranbyidMPidBicara(pp.getIdPermohonanPihak(), hakmilikPerbicaraan.getIdPerbicaraan());
//                                if(kehadiran != null){
//                                    List<Penilaian> penilaianList = new ArrayList<Penilaian>();
//                                    penilaianList = kehadiran.getSenaraiPenilaian();
//                                    for(Penilaian penilaian : penilaianList){
//                                        amaunTotal = penilaian.getAmaun().add(amaunTotal);
//                                    }
//
//                                }
//
//                            }
//                        }
//
//                    }
//
//                }
//
//            }
//        }
           String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        permohonan = permohonanDAO.findById(idPermohonan);
        KodCawangan cawangan = permohonan.getCawangan();
        amaunTotal = BigDecimal.ZERO;

        List<HakmilikPermohonan> senaraiHakmilikPermohonan = new ArrayList<HakmilikPermohonan>();
        senaraiHakmilikPermohonan = permohonan.getSenaraiHakmilik();
        if (senaraiHakmilikPermohonan.size() > 0) {
            hakmilik = senaraiHakmilikPermohonan.get(0).getHakmilik();
            if (hakmilik != null) {
                List<PermohonanPihak> senaraiPermohonanPihak = new ArrayList<PermohonanPihak>();
                senaraiPermohonanPihak = pengambilanService.getSenaraiPermohonPihakByIdHakmilik(idPermohonan, hakmilik.getIdHakmilik());
                if (senaraiPermohonanPihak.size() > 0) {
                    permohonanPihak = senaraiPermohonanPihak.get(0);
                    if (permohonanPihak != null) {
                        PermohonanMahkamah mahkamah = permohonanSupayaBantahanService.findPermohonanMahkamahByidMP(permohonanPihak.getIdPermohonanPihak());
                        if (mahkamah != null) {
                            amaunTawarPampasan = mahkamah.getAmnTawarPampasan();
                            amaunTawarRosak = mahkamah.getAmnTawarRosak();
                            amaunTambahPampasan = mahkamah.getTambahanPampasan();
                        }
                    }


                    if (permohonan.getPermohonanSebelum() != null) {
                        HakmilikPermohonan hakmilikPermohonan = pengambilanService.findByIdHakmilikIdPermohonan(permohonan.getPermohonanSebelum().getIdPermohonan(), hakmilik.getIdHakmilik());
                        PermohonanPihak pp = pengambilanService.getPmohonPihakByIdHakmilikIdPihak(permohonan.getPermohonanSebelum().getIdPermohonan(), hakmilik.getIdHakmilik(), permohonanPihak.getPihak().getIdPihak());
                        if (hakmilikPermohonan != null) {
                            HakmilikPerbicaraan hakmilikPerbicaraan = pengambilanService.getHakmilikPerbicaraanByidMH(hakmilikPermohonan.getId());
                            if (hakmilikPerbicaraan != null && pp != null) {
                                PerbicaraanKehadiran kehadiran = pengambilanService.getPerbicaraanKehadiranbyidMPidBicara(pp.getIdPermohonanPihak(), hakmilikPerbicaraan.getIdPerbicaraan());
                                if (kehadiran != null) {
                                    List<Penilaian> penilaianList = new ArrayList<Penilaian>();
                                    penilaianList = kehadiran.getSenaraiPenilaian();
                                    for (Penilaian penilaian : penilaianList) {
                                        amaunTotal = penilaian.getAmaun().add(amaunTotal);
                                    }
                                }
                            }
                        }

                    }

                }

            }
        }
    }
    
    //    @Before(stages = {LifecycleStage.BindingAndValidation}, on = {"!simpanLaporanTanah"})
     @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {

         System.out.println("-------------rehydrate---------");

        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");

       

        if (idPermohonan != null) {
            permohonan = permohonanDAO.findById(idPermohonan);
                   List<HakmilikPermohonan> senaraiHakmilikPermohonan = new ArrayList<HakmilikPermohonan>();
        senaraiHakmilikPermohonan = permohonan.getSenaraiHakmilik();
        if(senaraiHakmilikPermohonan.size() > 0){
            hakmilik = senaraiHakmilikPermohonan.get(0).getHakmilik();
            if(hakmilik != null) {
                List<PermohonanPihak> senaraiPermohonanPihak = new ArrayList<PermohonanPihak>();
                senaraiPermohonanPihak = pengambilanService.getSenaraiPermohonPihakByIdHakmilik(idPermohonan, hakmilik.getIdHakmilik());
                if(senaraiPermohonanPihak.size() > 0){
                    permohonanPihak = senaraiPermohonanPihak.get(0);
                    if(permohonanPihak != null){
                        PermohonanMahkamah mahkamah =  permohonanSupayaBantahanService.findPermohonanMahkamahByidMP(permohonanPihak.getIdPermohonanPihak());
                        if(mahkamah != null){
                            amaunTawarPampasan = mahkamah.getAmnTawarPampasan();
                            amaunTambahPampasan=mahkamah.getTambahanPampasan();
                            amaunTawarRosak=mahkamah.getAmnTawarRosak();
                        }
                    }

                }

            }
        }
            
        }
    }


    public Resolution searchHakmilik(){
        idHakmilik = getContext().getRequest().getParameter("idHakmilik");
        senaraiPermohonan =new ArrayList<Permohonan>();
        if (!idHakmilik.isEmpty()) {
            senaraiPermohonan = pengambilanService.getSeneraiPermohonanByidHakmilik(idHakmilik);
            if (senaraiPermohonan.size() < 1){
                addSimpleError("Id Hakmilik Tidak Dijumpai");
            }
        }
        return new JSP("pengambilan/melaka/bantahanmahkamah/KemasukkanIdhahmilik_BantahanMahkamah.jsp");
    }

    public Resolution selectHakmilik() {
        idHakmilik = getContext().getRequest().getParameter("idHakmilik");
        searchHakmilik();
        selectedIdSblm = getContext().getRequest().getParameter("selectedIdSblm");
        selectedIdHakmilik = getContext().getRequest().getParameter("selectedIdHakmilik");
        if(selectedIdHakmilik !=null){
            hakmilik = hakmilikDAO.findById(selectedIdHakmilik);
        }
        return new JSP("pengambilan/melaka/bantahanmahkamah/KemasukkanIdhahmilik_BantahanMahkamah.jsp");
    }

    public Resolution simpan() {
        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        etanahActionBeanContext ctx = (etanahActionBeanContext) getContext();

        idHakmilik = getContext().getRequest().getParameter("idHakmilik");
        searchHakmilik();

        selectedIdSblm = getContext().getRequest().getParameter("selectedIdSblm");
        selectedIdHakmilik = getContext().getRequest().getParameter("selectedIdHakmilik");

        if(selectedIdHakmilik !=null){
            hakmilik = hakmilikDAO.findById(selectedIdHakmilik);
        }
        if(selectedIdPihak != null){
            KodUrusan kodUrusan = kodUrusanDAO.findById("BMAHK");
            idPermohonan = idPermohonanGenerator.generate(ctx.getKodNegeri(), peng.getKodCawangan(), kodUrusan);
            if(selectedIdSblm != null) {
                Permohonan permohonanSblm = permohonanDAO.findById(selectedIdSblm);
                permohonan = new Permohonan();
                permohonan.setIdPermohonan(idPermohonan);
                permohonan.setCawangan(peng.getKodCawangan());
                permohonan.setKodUrusan(kodUrusan);
                permohonan.setPermohonanSebelum(permohonanSblm);
                InfoAudit info = new InfoAudit();
                info.setDimasukOleh(peng);
                info.setTarikhMasuk(new java.util.Date());
                permohonan.setInfoAudit(info);
                permohonan = pengambilanService.simpanPermohonan(permohonan);
                addSimpleMessage("Id Permohonan : " + idPermohonan);

                if(permohonanSblm.getSenaraiPemohon().size() > 0){
                    for(Pemohon pem : permohonanSblm.getSenaraiPemohon()){
                        Pemohon pemohon = aduanService.findPemohonByPihak(permohonan.getIdPermohonan(), pem.getPihak().getIdPihak());
                        if(pemohon == null){
                            pemohon = new Pemohon();
                            InfoAudit ia = new InfoAudit();
                            ia.setDimasukOleh(peng);
                            ia.setTarikhMasuk(new java.util.Date());
                            pemohon.setInfoAudit(ia);
                            pemohon.setPermohonan(permohonan);
                            pemohon.setCawangan(permohonan.getCawangan());
                            pemohon.setPihak(pem.getPihak());
                            aduanService.simpanPemohon(pemohon);
                        }
                    }
                }

                if(hakmilik != null){
                    List<HakmilikPermohonan> senaraiHakmilikPermohonan = new ArrayList<HakmilikPermohonan>();
                    senaraiHakmilikPermohonan = permohonan.getSenaraiHakmilik();
                    if(senaraiHakmilikPermohonan != null && senaraiHakmilikPermohonan.size() > 0){
                        for(HakmilikPermohonan hakmilikPermohonan : senaraiHakmilikPermohonan){
                            if(hakmilikPermohonan.getHakmilik().getIdHakmilik() != hakmilik.getIdHakmilik()){
                                pengambilanService.deleteHakmilikPermohonan(hakmilikPermohonan);
                            }
                        }
                    }
                    HakmilikPermohonan hakmilikPermohonan = pengambilanService.findByIdHakmilikIdPermohonan(permohonan.getIdPermohonan(), hakmilik.getIdHakmilik());
                    if(hakmilikPermohonan == null) {
                        hakmilikPermohonan= new HakmilikPermohonan();
                        InfoAudit ia = new InfoAudit();
                        ia.setDimasukOleh(peng);
                        ia.setTarikhMasuk(new java.util.Date());
                        hakmilikPermohonan.setInfoAudit(ia);
                        hakmilikPermohonan.setPermohonan(permohonan);
                        hakmilikPermohonan.setHakmilik(hakmilik);
                        aduanService.simpanHP(hakmilikPermohonan);
                    }
                    permohonanPihak = pengambilanService.getPmohonPihakByIdHakmilikIdPihak(permohonan.getIdPermohonan(),hakmilik.getIdHakmilik(),Long.valueOf(selectedIdPihak));
                    if(permohonanPihak == null){
                        permohonanPihak = new PermohonanPihak();
                        info = new InfoAudit();
                        info.setDimasukOleh(peng);
                        info.setTarikhMasuk(new java.util.Date());
                        permohonanPihak.setPermohonan(permohonan);
                        permohonanPihak.setHakmilik(hakmilik);
                        permohonanPihak.setPihak(pihakDAO.findById(Long.valueOf(selectedIdPihak)));
                        permohonanPihak.setCawangan(permohonan.getCawangan());
                        HakmilikPihakBerkepentingan hpb = pengambilanService.getHakmilikPihakByidPihak(hakmilik.getIdHakmilik(), Long.valueOf(selectedIdPihak));
                        permohonanPihak.setJenis(hpb.getJenis());
                        permohonanPihak.setSyerPembilang(hpb.getSyerPembilang());
                        permohonanPihak.setSyerPenyebut(hpb.getSyerPenyebut());
                        permohonanPihak.setInfoAudit(info);
                        aduanService.simpanPP(permohonanPihak);
                    }
                    List<PermohonanPihak> senaraiPermohonanPihak = new ArrayList<PermohonanPihak>();
                    senaraiPermohonanPihak = pengambilanService.getSenaraiPermohonPihakByIdHakmilik(permohonan.getIdPermohonan(), hakmilik.getIdHakmilik());
                    for(PermohonanPihak pp : senaraiPermohonanPihak){
                        if(pp.getPihak().getIdPihak() != permohonanPihak.getPihak().getIdPihak()){
                            pengambilanService.deletePermohonanPihak(pp);
                        }
                    }
                }
            }
        }

        return new JSP("pengambilan/melaka/bantahanmahkamah/kemasukan_dokumen_tambahan.jsp");
    }

    public Resolution simpanMahkamah(){
        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        String idPermohonanPihak = getContext().getRequest().getParameter("idPermohonanPihak");
        if(idPermohonanPihak != null) {
            PermohonanMahkamah mahkamah =  permohonanSupayaBantahanService.findPermohonanMahkamahByidMP(Long.valueOf(idPermohonanPihak));
            if(mahkamah == null) {
                mahkamah = new PermohonanMahkamah();
                InfoAudit infoAudit = new InfoAudit();
                infoAudit.setDimasukOleh(peng);
                infoAudit.setTarikhMasuk(new java.util.Date());
                mahkamah.setInfoAudit(infoAudit);
                mahkamah.setPermohonanPihak(permohonanPihakDAO.findById(Long.valueOf(idPermohonanPihak)));
            }else {
                InfoAudit infoAudit = mahkamah.getInfoAudit();
                infoAudit.setDiKemaskiniOleh(peng);
                infoAudit.setTarikhKemaskini(new java.util.Date());
                mahkamah.setInfoAudit(infoAudit);
            }
            mahkamah.setTambahanPampasan(amaunTambahPampasan);
//            mahkamah.setAmnTawarPampasan(amaunTawarPampasan);
            permohonanSupayaBantahanService.saveOrupdatePermohonanMahkamah(mahkamah);
            addSimpleMessage("Maklumat telah berjaya disimpan");
        }
        calculate();

        return new JSP("pengambilan/melaka/bantahanmahkamah/nilaiPampasanMahkamah.jsp").addParameter("tab", "true");
    }

    public Resolution addDocForm() {
        idPermohonan = getContext().getRequest().getParameter("idPermohonan");
        permohonan = permohonanDAO.findById(idPermohonan);
        senaraiKodUrusan.add(permohonan.getKodUrusan().getKod());
        // reset the additional documents submitted to 10
        if (kandunganFolderTamb.size() == 0) {
//            for (int i = 0; i < 3; i++) {
            KandunganFolder kf = new KandunganFolder();
            kandunganFolderTamb.add(kf);
//            }
        }
        return new JSP("pengambilan/melaka/bantahanmahkamah/kemasukan_tambahan_doc.jsp").addParameter("popup", "true");
    }

    public Resolution simpanDokumenTambahan() {
        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);

        InfoAudit ia = new InfoAudit();
        ia.setDimasukOleh(peng);
        ia.setTarikhMasuk(new java.util.Date());

        permohonan = permohonanDAO.findById(idPermohonan);
        folderDokumen = permohonan.getFolderDokumen();



        List<KandunganFolder> akf = new ArrayList<KandunganFolder>();
        if (folderDokumen != null) {
            akf = folderDokumen.getSenaraiKandungan();
        } else {
            FolderDokumen fd = new FolderDokumen();
            fd.setTajuk(permohonan.getIdPermohonan());
            fd.setInfoAudit(ia);
            folderDokumen = pengambilanService.saveOrUpdateFolderDokumen(fd);
            permohonan.setFolderDokumen(folderDokumen);
            pengambilanService.simpanPermohonan(permohonan);
        }

        KandunganFolder f = kandunganFolderTamb.get(0);
        Dokumen d = f.getDokumen();
        KodDokumen kd = kodDokumenDAO.findById(d.getKodDokumen().getKod());

        if ((d.getKodDokumen() != null && !d.getKodDokumen().getKod().equals("0"))
                || (f.getCatatan() != null && f.getCatatan().trim().length() > 0)) {
            d.setInfoAudit(ia);
            d.setTajuk(kd == null ? "KIV" : kd.getNama());
            d.setNoVersi("1.0");
            d.setKlasifikasi(kodKlasifikasiDAO.findById(1));
            d = pengambilanService.saveOrUpdateDokumen(d);
            f.setFolder(folderDokumen);
            f.setInfoAudit(ia);
            f.setDokumen(d);
            f = pengambilanService.saveOrUpdateKandunganFolder(f);
            akf.add(f);
        }
        if (akf.size() > 0) {
            folderDokumen.setSenaraiKandungan(akf);
            folderDokumen.setInfoAudit(ia);
            folderDokumen = pengambilanService.saveOrUpdateFolderDokumen(folderDokumen);
        }


        senaraiKandungan = new ArrayList<KandunganFolder>();
        if (folderDokumen != null) {
            if (folderDokumen.getSenaraiKandungan() != null) {
                for (KandunganFolder kf : folderDokumen.getSenaraiKandungan()) {
                    if (kf == null || kf.getDokumen() == null) {
                        continue;
                    }
                    senaraiKandungan.add(kf);
                }
            }
        }

        return new JSP("pengambilan/melaka/bantahanmahkamah/kemasukan_dokumen_tambahan.jsp");
    }

    public Resolution reload() {
        permohonan = permohonanDAO.findById(idPermohonan);
        folderDokumen = permohonan.getFolderDokumen();

        senaraiKandungan = new ArrayList<KandunganFolder>();
        if (folderDokumen != null) {
            if (folderDokumen.getSenaraiKandungan() != null) {
                for (KandunganFolder kf : folderDokumen.getSenaraiKandungan()) {
                    if (kf == null || kf.getDokumen() == null) {
                        continue;
                    }
                    senaraiKandungan.add(kf);
                }
            }
        }
        return new JSP("pengambilan/melaka/bantahanmahkamah/kemasukan_dokumen_tambahan.jsp");
    }

    public Resolution deleteSelected() {
        permohonan = permohonanDAO.findById(idPermohonan);
        folderDokumen = permohonan.getFolderDokumen();
        List<KandunganFolder> akf = folderDokumen.getSenaraiKandungan();

        String[] ids = getContext().getRequest().getParameterValues("chkbox");

        for (String id : ids) {
            Long id1 = Long.parseLong(id);
            Dokumen dok1 = dokumenDAO.findById(id1);
            pengambilanService.deleteDokumen(dok1);

            if(akf != null && akf.size() > 0){
                for (KandunganFolder fd1 : akf) {
                    if (id1 == fd1.getDokumen().getIdDokumen()) {
                        pengambilanService.deleteKandunganFolder(fd1);
                    }
                }
            }
        }

        return new RedirectResolution(KemasukkanIdhahmilik_BantahanMahkamahActionBean.class, "reload").addParameter("idPermohonan", idPermohonan);
    }

    public Resolution nextPage() {
        permohonan = permohonanDAO.findById(idPermohonan);

        if (senaraiCaraBayaran == null) {
            senaraiCaraBayaran = new ArrayList<CaraBayaran>();
        }

        if (senaraiCaraBayaran.size() == 0) {
            for (int i = 0; i < 5; i++) {
                CaraBayaran cr = new CaraBayaran();
                cr.setAmaun(BigDecimal.ZERO); // reset amount
                senaraiCaraBayaran.add(cr);
            }
        }
        return new JSP("pengambilan/melaka/bantahanmahkamah/kemasukan_bayaran.jsp");
    }

    public Resolution save() throws Exception {
        etanahActionBeanContext ctx = (etanahActionBeanContext) getContext();
        Pengguna pengguna = ctx.getUser();
        permohonan = permohonanDAO.findById(idPermohonan);

        Date now = new Date();
        int year = now.getYear() + 1900;
        InfoAudit ia = new InfoAudit();
        ia.setDimasukOleh(pengguna);
        ia.setTarikhMasuk(now);

        Session s = sessionProvider.get();
        Transaction tx = s.beginTransaction();
        tx.begin();
        mod = modKutip.loadPenyerahFromSession(ctx);
        try {

        KodCawangan caw = pengguna.getKodCawangan();

        KodKlasifikasi klasifikasiAm = kodKlasifikasiDAO.findById(1);
        String documentPath = conf.getProperty("document.path");

        if (permohonan.getKodUrusan().getJabatan().getKod().equals("9")) {
            total = new BigDecimal(getContext().getRequest().getParameter("jumCaraBayar"));
        }

        Akaun akTerima = akaunService.getAkaunForCawangan("AKH", caw);
        if (akTerima == null) {
            logger.fatal("RALAT: Akaun untuk Kutipan tidak wujud");
            throw new RuntimeException("RALAT: Akaun untuk Kutipan tidak wujud");
        }

//        Session s = sessionProvider.get();
//        Transaction tx = s.beginTransaction();

        //set KEW_DOKUMEN
//        resitNo = noResitGenerator.generate(ctx.getKodNegeri(), caw, pengguna);
            resitNo = noResitGenerator.getAndLockSerialNo(pengguna);       //new noResitGenerator
        logger.info("RESIT NO:" + resitNo);

        DokumenKewangan dk = new DokumenKewangan();
        dk.setIdDokumenKewangan(resitNo);
        // set idResit to pageContext
        ctx.getRequest().setAttribute("noResit", dk.getIdDokumenKewangan());
        dk.setAmaunBayaran(total);
        dk.setKodDokumen(kodDokumenDAO.findById("RBY"));
        dk.setStatus(kodStatusDokumenKewanganDAO.findById("A"));
        dk.setInfoAudit(ia);
        dk.setCawangan(caw);
        dk.setAkaun(akTerima);
        if (mod != null && mod.length() > 0) {
            dk.setMod(kodKutipDAO.findById(mod.charAt(0)));
        }
        saveCaraBayaran(caw, dk, ia);
        dokumenKewanganDAO.save(dk);
        
        //new requirement by hasil's team
        penyataPemungutService.savePenyataPemungut(dk);

//set KEW_TRANS
        String arrSplit[], aSplit;
//        pmohon=pengambilanDepositService.findById(idPermohonan);
        Long idPihak=permohonan.getSenaraiPihak().get(0).getPihak().getIdPihak();
        akaunDeposit= pengambilanDepositService.findByIDMohonIDPihak(idPermohonan,Long.toString(idPihak));

        PermohonanTuntutanKos permohonanTuntutanKos = pengambilanService.getMohonTuntutKosByIdMohonItem(idPermohonan, permohonan.getKodUrusan().getNama());
        if(permohonanTuntutanKos == null){
            permohonanTuntutanKos = new PermohonanTuntutanKos();
            permohonanTuntutanKos.setInfoAudit(ia);
            permohonanTuntutanKos.setPermohonan(permohonan);
            permohonanTuntutanKos.setCawangan(caw);
//            @melaka site
            permohonanTuntutanKos.setKodTransaksi(kodTransaksiDAO.findById("79660"));
//            @opis
//            permohonanTuntutanKos.setKodTransaksi(kodTransaksiDAO.findById("20018"));
            permohonanTuntutanKos.setItem(permohonan.getKodUrusan().getNama());
            permohonanTuntutanKos.setAmaunTuntutan(total);
            permohonanTuntutanKos = pengambilanService.simpanMohonTuntutKos(permohonanTuntutanKos);
        }

        if (permohonanTuntutanKos != null) {
            logger.info("START KEW_AKAUN");
            // create Ak.Amanah

            if(akaunDeposit.size()==0){
                Akaun akAmanah = new Akaun();
                KodAkaun akaunDeposit=new KodAkaun();
                akaunDeposit.setKod("DP");
                akAmanah.setCawangan(permohonan.getCawangan());
                akAmanah.setNoAkaun(idPermohonan);
                akAmanah.setKodAkaun(akaunDeposit);
                akAmanah.setPermohonan(permohonan);
                akAmanah.setStatus(kodStatusAkaunDAO.findById("A"));
                akAmanah.setPemegang(permohonan.getSenaraiPihak().get(0).getPihak());
                akAmanah.setBilCetakPenyata(0);
                akAmanah.setBaki(new BigDecimal(0)); // kredit
                akAmanah.setInfoAudit(ia);
                akaunDAO.save(akAmanah);

                logger.info("END KEW_AKAUN: " + akAmanah.getNoAkaun());
            }


            logger.info("START KEW_TRANS");
            Transaksi trans = new Transaksi();
            Akaun akKreditDeposit = pengambilanDepositService.getAkaunDeposit(idPermohonan, Long.toString(idPihak));

            trans.setKodTransaksi(permohonanTuntutanKos.getKodTransaksi());
            trans.setAkaunDebit(akTerima);
            trans.setAkaunKredit(akKreditDeposit);
            trans.setAmaun(permohonanTuntutanKos.getAmaunTuntutan());
            trans.setUntukTahun(year);
            trans.setPermohonan(permohonanTuntutanKos.getPermohonan());
            trans.setDokumenKewangan(dk);
            trans.setStatus(kodStatusDAO.findById('T'));
            trans.setKuantiti(1);
            trans.setInfoAudit(ia);
            trans.setCawangan(caw);
            transaksiDAO.save(trans);

            logger.info("END KEW_TRANS: " + trans.getIdTransaksi());


            //save baki inside Kew_Amaun
            logger.info("START KEW_AKAUN");
            if(akKreditDeposit!=null) {
                BigDecimal baki =akKreditDeposit.getBaki();
               BigDecimal amaun=trans.getAmaun();
               BigDecimal balanceDeposit=baki.subtract(amaun);
               akKreditDeposit.setBaki(balanceDeposit);
               akKreditDeposit.setInfoAudit(ia);
               akaunDAO.save(akKreditDeposit);
            }

            logger.info("END KEW_AKAUN: " + akKreditDeposit.getBaki());

            logger.info("START PermohonanTuntutanBayaran");
            PermohonanTuntutanBayar ptb = new PermohonanTuntutanBayar();
            ptb.setPermohonanTuntutanKos(permohonanTuntutanKos);
            ptb.setDokumenKewangan(dk);
            ptb.setAmaun(permohonanTuntutanKos.getAmaunTuntutan());
            ptb.setTarikhBayar(now);
            ptb.setInfoAudit(ia);
            permohonanTuntutanBayarDAO.save(ptb);
            logger.info("END PermohonanTuntutanBayaran: " + ptb.getIdBayar());

//            logger.info("START AKUAN_DEPOSIT");
//            AkuanTerimaDeposit atd = new AkuanTerimaDeposit();
//            atd.setPermohonan(permohonan);
//            atd.setCaraBayaran(kodCaraBayaranDAO.findById("T"));
//            atd.setNoResit(dk.getIdDokumenKewangan());
//            atd.setPermohonanPihak(permohonan.getSenaraiPihak().get(0));
//            atd.setTarikhResit(now);
//            atd.setAmaun(permohonanTuntutanKos.getAmaunTuntutan());
//            atd.setInfoAudit(ia);
//            akuanTerimaDepositDAO.save(atd);

            logger.info("END AKUAN_DEPOSIT: ");
        }


//set KEW_AKAUN ---> AKH
        logger.info(akTerima.getCawangan().getKod());
        if (!total.equals(BigDecimal.ZERO)) {
            s.lock(akTerima, LockMode.UPGRADE);
            akTerima.setBaki(akTerima.getBaki().add(total));
        }



//dokumen dokumen_capai folder folder_dok
        if(permohonan.getFolderDokumen() == null){
            FolderDokumen fd = new FolderDokumen();
            fd.setTajuk(permohonan.getIdPermohonan());
            fd.setInfoAudit(ia);
            folderDokumen = pengambilanService.saveOrUpdateFolderDokumen(fd);
            permohonan.setFolderDokumen(folderDokumen);
            pengambilanService.simpanPermohonan(permohonan);
        }
        long idFolder = permohonan.getFolderDokumen().getFolderId();
        Dokumen resit = new Dokumen();
        resit.setFormat("application/pdf");
        resit.setInfoAudit(ia);
        resit.setKlasifikasi(klasifikasiAm);
        KodDokumen kodResit = kodDokumenDAO.findById("RBY");
        resit.setKodDokumen(kodResit);
        resit.setNoVersi("1.0");
        logger.info(kodResit.getNama());
        resit.setTajuk("Resit Bayaran Proses");
        resit = dokumenDAO.saveOrUpdate(resit);

        logger.info("RESIT IDDOCUMENT: " + resit.getIdDokumen());
        String path = documentPath + (documentPath.endsWith(File.separator) ? "" : File.separator);
        String path2 = permohonan.getFolderDokumen().getFolderId() + File.separator + String.valueOf(resit.getIdDokumen());
        if ("04".equals(conf.getProperty("kodNegeri"))) {
            reportUtil.generateReport("HSLResitUrusanTanah_MLK.rdf",
                    new String[]{"p_id_kew_dok"},
                    new String[]{resitNo},
                    path + path2, null);
        } else {
            reportUtil.generateReport("HSLResitUrusanTanah.rdf",
                    new String[]{"p_id_kew_dok"},
                    new String[]{resitNo},
                    path + path2, null);
        }
        resit.setNamaFizikal(reportUtil.getDMSPath());
        getContext().getRequest().setAttribute("resitId", String.valueOf(resit.getIdDokumen()));
        dokumenDAO.update(resit);

        KandunganFolder kf = new KandunganFolder();
        kf.setDokumen(resit);
        kf.setFolder(permohonan.getFolderDokumen());
        kf.setInfoAudit(ia);
        kf.setCatatan("");
        kandunganFolderDAO.save(kf);

        if (!permohonan.getKodUrusan().getKod().equals("BMAHK")) {
                kodUrusan = kodUrusanDAO.findById(permohonan.getKodUrusan().getKod());
                if (kodUrusan.getKePTG() == 'Y') {

                    WorkFlowService.initiateTask(kodUrusan.getIdWorkflow(),
                            idPermohonan, pengguna.getKodCawangan().getKod() + ",00", pengguna.getIdPengguna(),
                            kodUrusan.getNama());
                } else if (kodUrusan.getKePTG() == 'T') {
                    WorkFlowService.initiateTask(kodUrusan.getIdWorkflow(),
                            idPermohonan, pengguna.getKodCawangan().getKod(), pengguna.getIdPengguna(),
                            kodUrusan.getNama());
                }
            }

//            UrusanValue uv = new UrusanValue();
//            uv.setKodUrusan(kodUrusan.getKod());
//            uv.setIdPermohonan(idPermohonan);
//            senaraiPermohonan1.add(uv);

        tx.commit();

//        BPelService service = new BPelService();
//        String taskId = (String) getContext().getRequest().getSession().getAttribute("taskId");
//
//        if (StringUtils.isNotBlank(taskId)) {
//            Task t = null;
//            t = service.getTaskFromBPel(taskId, pengguna);
//            stageId = t.getSystemAttributes().getStage();
//        }
//
//
//        String msg = "";
//        StringBuilder sb = new StringBuilder();
//        try {
//            List<Map<String, String>> list = getPermohonanWithTaskID(pengguna);
//            for (Map<String, String> map : list) {
//                String taskID = map.get("taskId");
//                String idPermohonan = map.get("idPermohonan");
//                if (sb.length() > 0) {
//                    sb.append(",");
//                }
//                sb.append(idPermohonan);
//                logger.info("TaskID ::" + taskID);
//                logger.info("idPermohonan ::" + idPermohonan);
//                try {
//                    ctxW = WorkFlowService.authenticate(pengguna.getIdPengguna());
//                } catch (Exception e) {
//                    logger.error("error ::" + e.getMessage());
//                }
//                WorkFlowService.updateTaskOutcome(taskID, ctxW, "APPROVE");
////                syslog.info(pguna.getIdPengguna() + " agih tugasan untuk permohonan :" + idPermohonan + " kepada " + pengguna.getIdPengguna());
//            }
//
//        } catch (Exception ex) {
//            ex.printStackTrace();
//            return new RedirectResolution(KemasukkanIdhahmilik_BantahanMahkamahActionBean.class).addParameter("error", sb.toString() + " :Agihan Tugasan Tidak Berjaya. Sila hubungi Pentadbir Sistem.");
//        }
//
//        msg = sb.toString() + " : Agihan tugasan kepada " + pengguna.getIdPengguna() + " telah Berjaya.";
        addSimpleMessage("Urusan telah berjaya didaftarkan.");

        } catch (Exception ex) {
            noResitGenerator.rollbackAndUnlockSerialNo(pengguna);
            tx.rollback();
            Throwable t = ex;
            // getting the root cause
            while (t.getCause() != null) {
                t = t.getCause();
            }
            ex.printStackTrace();
            addSimpleError(t.getMessage());
        }finally {
            noResitGenerator.commitAndUnlockSerialNo(pengguna);
        }
        return new JSP("pengambilan/melaka/bantahanmahkamah/cetak_resit_bayaran.jsp");
    }

    private void saveCaraBayaran(KodCawangan caw, DokumenKewangan dk, InfoAudit ia) {
        logger.info("START CARABAYAR:-----------------------------");

        ArrayList<DokumenKewanganBayaran> adkb = new ArrayList<DokumenKewanganBayaran>();

        for (CaraBayaran cb : senaraiCaraBayaran) {
            if (cb.getKodCaraBayaran() != null && !cb.getKodCaraBayaran().getKod().equals("0")
                    && cb.getAmaun() != null && cb.getAmaun().compareTo(BigDecimal.ZERO) > 0) {
                // clear if null
                if (cb.getBank() != null && cb.getBank().getKod().equals("0")) {
                    cb.setBank(null);
                    cb.setBankCawangan(null);
                }
                cb.setCawangan(caw);
                cb.setInfoAudit(ia);
                cb.setAktif('Y');
                caraBayaranDAO.save(cb);
                DokumenKewanganBayaran dkb = new DokumenKewanganBayaran();
                dkb.setCaraBayaran(cb);
                dkb.setDokumenKewangan(dk);
                dkb.setAmaun(cb.getAmaun()); // TODO: cheque handling
                dkb.setInfoAudit(ia);
                dkb.setAktif('Y');
                adkb.add(dkb);
            }
        }
        dk.setSenaraiBayaran(adkb);
    }

    private List<Map<String, String>> getPermohonanWithTaskID(Pengguna pengguna) throws Exception {
        List<Map<String, String>> list = new ArrayList<Map<String, String>>();
        Map<String, String> map = null;
        logger.info("Urusan tidak berangkai");
        String taskId = (String) getContext().getRequest().getSession().getAttribute("taskId");
        map = new HashMap<String, String>();
        map.put("idPermohonan", permohonan.getIdPermohonan());
        map.put("taskId", taskId);
        list.add(map);
        return list;
    }

    public String getIdHakmilik() {
        return idHakmilik;
    }

    public void setIdHakmilik(String idHakmilik) {
        this.idHakmilik = idHakmilik;
    }

    public Permohonan getPermohonan() {
        return permohonan;
    }

    public void setPermohonan(Permohonan permohonan) {
        this.permohonan = permohonan;
    }

    public Permohonan getPermohonanSblm() {
        return permohonanSblm;
    }

    public void setPermohonanSblm(Permohonan permohonanSblm) {
        this.permohonanSblm = permohonanSblm;
    }

    public String getSelectedIdSblm() {
        return selectedIdSblm;
    }

    public void setSelectedIdSblm(String selectedIdSblm) {
        this.selectedIdSblm = selectedIdSblm;
    }

    public List<Permohonan> getSenaraiPermohonan() {
        return senaraiPermohonan;
    }

    public void setSenaraiPermohonan(List<Permohonan> senaraiPermohonan) {
        this.senaraiPermohonan = senaraiPermohonan;
    }

    public Hakmilik getHakmilik() {
        return hakmilik;
    }

    public void setHakmilik(Hakmilik hakmilik) {
        this.hakmilik = hakmilik;
    }

    public String getSelectedIdHakmilik() {
        return selectedIdHakmilik;
    }

    public void setSelectedIdHakmilik(String selectedIdHakmilik) {
        this.selectedIdHakmilik = selectedIdHakmilik;
    }

    public BigDecimal getAmaunTawarPampasan() {
        return amaunTawarPampasan;
    }

    public void setAmaunTawarPampasan(BigDecimal amaunTawarPampasan) {
        this.amaunTawarPampasan = amaunTawarPampasan;
    }

    public BigDecimal getAmaunTotal() {
        return amaunTotal;
    }

    public void setAmaunTotal(BigDecimal amaunTotal) {
        this.amaunTotal = amaunTotal;
    }

    public PermohonanPihak getPermohonanPihak() {
        return permohonanPihak;
    }

    public void setPermohonanPihak(PermohonanPihak permohonanPihak) {
        this.permohonanPihak = permohonanPihak;
    }

    public String getSelectedIdPihak() {
        return selectedIdPihak;
    }

    public void setSelectedIdPihak(String selectedIdPihak) {
        this.selectedIdPihak = selectedIdPihak;
    }

    public List<KandunganFolder> getSenaraiKandungan() {
        return senaraiKandungan;
    }

    public void setSenaraiKandungan(List<KandunganFolder> senaraiKandungan) {
        this.senaraiKandungan = senaraiKandungan;
    }

    public List<KodDokumen> getKodDokumenNotRequired() {
        return kaunterService.getKodDokumenNotRequired(senaraiKodUrusan);
    }

    public String getIdPermohonan() {
        return idPermohonan;
    }

    public void setIdPermohonan(String idPermohonan) {
        this.idPermohonan = idPermohonan;
    }

    public List<KandunganFolder> getKandunganFolderTamb() {
        return kandunganFolderTamb;
    }

    public void setKandunganFolderTamb(List<KandunganFolder> kandunganFolderTamb) {
        this.kandunganFolderTamb = kandunganFolderTamb;
    }

    public ArrayList<String> getSenaraiKodUrusan() {
        return senaraiKodUrusan;
    }

    public void setSenaraiKodUrusan(ArrayList<String> senaraiKodUrusan) {
        this.senaraiKodUrusan = senaraiKodUrusan;
    }

    public FolderDokumen getFolderDokumen() {
        return folderDokumen;
    }

    public void setFolderDokumen(FolderDokumen folderDokumen) {
        this.folderDokumen = folderDokumen;
    }

    public ArrayList<CaraBayaran> getSenaraiCaraBayaran() {
        return senaraiCaraBayaran;
    }

    public void setSenaraiCaraBayaran(ArrayList<CaraBayaran> senaraiCaraBayaran) {
        this.senaraiCaraBayaran = senaraiCaraBayaran;
    }

    public List<Akaun> getAkaunDeposit() {
        return akaunDeposit;
    }

    public void setAkaunDeposit(List<Akaun> akaunDeposit) {
        this.akaunDeposit = akaunDeposit;
    }

    public Configuration getConf() {
        return conf;
    }

    public void setConf(Configuration conf) {
        this.conf = conf;
    }

    public IWorkflowContext getCtxW() {
        return ctxW;
    }

    public void setCtxW(IWorkflowContext ctxW) {
        this.ctxW = ctxW;
    }

    public Pemohon getPmohon() {
        return pmohon;
    }

    public void setPmohon(Pemohon pmohon) {
        this.pmohon = pmohon;
    }

    public ReportUtil getReportUtil() {
        return reportUtil;
    }

    public void setReportUtil(ReportUtil reportUtil) {
        this.reportUtil = reportUtil;
    }

    public String getResitNo() {
        return resitNo;
    }

    public void setResitNo(String resitNo) {
        this.resitNo = resitNo;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public KodUrusan getKodUrusan() {
        return kodUrusan;
    }

    public void setKodUrusan(KodUrusan kodUrusan) {
        this.kodUrusan = kodUrusan;
    }

    public ArrayList<UrusanValue> getSenaraiPermohonan1() {
        return senaraiPermohonan1;
    }

    public void setSenaraiPermohonan1(ArrayList<UrusanValue> senaraiPermohonan1) {
        this.senaraiPermohonan1 = senaraiPermohonan1;
    }


    public BigDecimal getAmaunTambahPampasan() {
        return amaunTambahPampasan;
    }

    public void setAmaunTambahPampasan(BigDecimal amaunTambahPampasan) {
        this.amaunTambahPampasan = amaunTambahPampasan;
    }

    public BigDecimal getAmaunTawarRosak() {
        return amaunTawarRosak;
    }

    public void setAmaunTawarRosak(BigDecimal amaunTawarRosak) {
        this.amaunTawarRosak = amaunTawarRosak;
    }

    public String getMod() {
        return mod;
    }

    public void setMod(String mod) {
        this.mod = mod;
    }
}
