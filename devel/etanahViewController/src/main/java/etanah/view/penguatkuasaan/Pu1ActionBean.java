/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.penguatkuasaan;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import etanah.Configuration;
import etanah.dao.PermohonanDAO;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;
import etanah.view.etanahActionBeanContext;
import com.google.inject.Inject;
import etanah.dao.HakmilikDAO;
import etanah.dao.KodKategoriTanahDAO;
import etanah.dao.KodNegeriDAO;
import etanah.dao.KodUOMDAO;
import etanah.model.InfoAudit;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.model.PermohonanUkur;
import etanah.dao.PermohonanUkurDAO;
import etanah.model.Alamat;
import etanah.model.Dokumen;
import etanah.model.Hakmilik;
import etanah.model.HakmilikPermohonan;
import etanah.model.Integrasi;
import etanah.model.IntegrasiDokumen;
import etanah.model.Pemohon;
import etanah.service.BPelService;
import etanah.service.EnforceService;
import etanah.service.JupemService;
import etanah.service.PembangunanService;
import etanah.service.common.EnforcementService;
//import java.math.BigDecimal;
import etanah.view.stripes.common.OutBoundIntegration;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.Format;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import net.sourceforge.stripes.action.ForwardResolution;
import oracle.bpel.services.workflow.task.model.Task;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

/**
 *
 * @author Rohan
 */
@UrlBinding("/penguatkuasaan/pu1")
public class Pu1ActionBean extends AbleActionBean {

    private static final Logger LOG = Logger.getLogger(Pu1ActionBean.class);
    @Inject
    PermohonanUkurDAO permohonanUkurDAO;
    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    EnforceService enforceService;
    @Inject
    EnforcementService enforcementService;
    @Inject
    JupemService jupemService;
    @Inject
    private etanah.Configuration conf;
    @Inject
    OutBoundIntegration obi;
    @Inject
    KodNegeriDAO kodNegeriDAO;
    @Inject
    KodKategoriTanahDAO kodKategoriTanahDAO;
    @Inject
    KodUOMDAO kodUOMDAO;
    @Inject
    HakmilikDAO hakmilikDAO;
    private PermohonanUkur permohonanUkur;
    private Permohonan permohonan;
    private Pengguna pguna;
    private String kodNegeri;
    private String stageId;
    private String idPermohonan;
    private String tujuan;
    private String tarikhPerakuan;
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    private Pemohon pemohon;
    private List<Pemohon> listPemohon;
    private String nama;
    private String alamat1;
    private String alamat2;
    private String alamat3;
    private String alamat4;
    private String poskod;
    private String kodNegeriPemohon;
    private Alamat alamat;
    private String anggaranLuas;
    private String anggaranLuasUOM;
    private String kodKategoriTanah;
    private List<HakmilikPermohonan> hakmilikList;
    private HakmilikPermohonan hp;
    ArrayList listIdPermohonan = new ArrayList<String>();
    String senaraiIdPermohonan[], idPertama, idKedua;
    private Boolean statusNorujukan = Boolean.FALSE;
    private Hakmilik hakmilik;
    private String ukurSemulaLot;

    @DefaultHandler
    public Resolution showForm() {
        String jspPage;
        if (kodNegeri.equalsIgnoreCase("04")) {
            jspPage = "penguatkuasaan/pu_melaka.jsp";
        } else {
            jspPage = "penguatkuasaan/pu.jsp";
        }
        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        return new JSP(jspPage).addParameter("tab", "true");
    }

    public Resolution showForm2() {
        String jspPage;
        if (kodNegeri.equalsIgnoreCase("04")) {
            jspPage = "penguatkuasaan/pu_melaka.jsp";
        } else {
            jspPage = "penguatkuasaan/pu.jsp";
        }
        getContext().getRequest().setAttribute("edit", Boolean.FALSE);
        return new JSP(jspPage).addParameter("tab", "true");
    }

    public Resolution showForm3() {
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        return new JSP("penguatkuasaan/hantar_PU.jsp").addParameter("tab", "true");
    }

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {
        String taskId = (String) getContext().getRequest().getSession().getAttribute("taskId");
        pguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        stageId(taskId);
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");

        kodNegeri = conf.getProperty("kodNegeri");
        System.out.println("idPermohonan::" + idPermohonan);
//        if ("04".equals(conf.getProperty("kodNegeri"))) {
//            kodNegeri = "Melaka";
//        }

        if (idPermohonan != null) {
            permohonan = permohonanDAO.findById(idPermohonan);
            permohonanUkur = enforceService.findPermohonanUkurByIdPermohonan(idPermohonan);

            if (permohonanUkur != null) {
                if (permohonanUkur.getTarikhPerakuan() != null) {
                    tarikhPerakuan = sdf.format(permohonanUkur.getTarikhPerakuan());
                }
                if (permohonanUkur.getAnggaranLuas() != null) {
                    anggaranLuas = permohonanUkur.getAnggaranLuas().toString();
                }
                if (permohonanUkur.getAnggaranLuatUOM() != null) {
                    anggaranLuasUOM = permohonanUkur.getAnggaranLuatUOM().getKod();
                }
                if (permohonanUkur.getKategoriTanah() != null) {
                    kodKategoriTanah = permohonanUkur.getKategoriTanah().getKod();
                }
                ukurSemulaLot = permohonanUkur.getUkurSemulaLot();
            }

            listPemohon = permohonan.getSenaraiPemohon();
            for (Pemohon p : listPemohon) {
                pemohon = p;
                if (pemohon != null) {
                    nama = pemohon.getNama();
                    if (pemohon.getAlamat() != null) {
                        alamat1 = pemohon.getAlamat().getAlamat1();
                        alamat2 = pemohon.getAlamat().getAlamat2();
                        alamat3 = pemohon.getAlamat().getAlamat3();
                        alamat4 = pemohon.getAlamat().getAlamat4();
                        poskod = pemohon.getAlamat().getPoskod();
                        if (pemohon.getAlamat().getNegeri() != null) {
                            kodNegeriPemohon = pemohon.getAlamat().getNegeri().getKod();
                        }

                    }

                }
            }
            Long id = null;
            hakmilikList = new ArrayList<HakmilikPermohonan>();
            if (permohonan.getPermohonanSebelum() != null) {
                hakmilikList = enforceService.findListMohonHakmilik(permohonan.getPermohonanSebelum().getIdPermohonan());
            }
            if (!hakmilikList.isEmpty()) {
                for (int j = 0; j < hakmilikList.size(); j++) {
                    if (hakmilikList.get(j).getNomborRujukan() != null) {
                        statusNorujukan = true;
                        System.out.println("::::::::::: value j :" + j);
                        HakmilikPermohonan hp = hakmilikList.get(j);
                        listIdPermohonan.add(hp.getNomborRujukan());

                        ArrayList<String> data = listIdPermohonan;


                        for (String a : data) {
                            senaraiIdPermohonan = a.split(",");
                            LOG.info("length senaraiIdPermohonan : " + senaraiIdPermohonan.length);
                            if (senaraiIdPermohonan.length > 1) {
                                idPertama = senaraiIdPermohonan[0];
                                idKedua = senaraiIdPermohonan[1];

                            }
                        }
                        LOG.info("::: idPertama : " + idPertama);
                        LOG.info("::: idKedua : " + idKedua);

                        String idMohon = "";

                        if (StringUtils.isNotEmpty(idPertama) && StringUtils.isNotEmpty(idKedua)) {
                            if (idPertama.equalsIgnoreCase(idPermohonan)) {
                                idMohon = idPertama;
                                id = hakmilikList.get(j).getId();
                                System.out.println("id MH (1): " + id);
                            } else if (idKedua.equalsIgnoreCase(idPermohonan)) {
                                idMohon = idKedua;
                                id = hakmilikList.get(j).getId();
                                System.out.println("id MH (2): " + id);
                            }
                        }

                        listIdPermohonan.clear();
                        idPertama = "";
                        idKedua = "";
                    }
                }

                System.out.println("::: id : " + id);
                System.out.println("::: statusNorujukan : " + statusNorujukan);

                if (statusNorujukan == true) {
                    if (id != null) {
                        LOG.info("::: using id MH");
                        hakmilikList = enforceService.findListMohonHakmilikById(id);
                    } else {
                        LOG.info("::: using id idPermohonan");
                        hakmilikList = enforceService.findListMohonHakmilik(permohonan.getIdPermohonan(), idPermohonan);
                    }
                }

                System.out.println(" hakmilikList.size() : " + hakmilikList.size());

                if (!hakmilikList.isEmpty()) {
                    hp = hakmilikList.get(0);
                    hakmilik = hakmilikDAO.findById(hp.getHakmilik().getIdHakmilik());
                }


            }
        }



    }

    public Resolution simpan() {
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = permohonanDAO.findById(idPermohonan);
        pguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        InfoAudit infoAudit = new InfoAudit();

        String idMohonUkur = (String) getContext().getRequest().getParameter("permohonanUkur.idMohonUkur");
        PermohonanUkur permohonanUkurTemp = new PermohonanUkur();

        System.out.println("-----idMohonUkur-----" + idMohonUkur);
        if (idMohonUkur != null && !idMohonUkur.equals("")) {
            permohonanUkurTemp = permohonanUkurDAO.findById(Integer.parseInt(idMohonUkur));
            infoAudit = permohonanUkurTemp.getInfoAudit();
            infoAudit.setDiKemaskiniOleh(pguna);
            infoAudit.setTarikhKemaskini(new java.util.Date());
        } else {
            infoAudit.setDimasukOleh(pguna);
            infoAudit.setTarikhMasuk(new java.util.Date());
//        }
            NumberFormat df = new DecimalFormat("0000");
            Date dt = new Date();
//            String kodCaw="";
//                if(permohonan.getCawangan().getKod().equals("01")){
//                    kodCaw ="MT";
//                }else if(permohonan.getCawangan().getKod().equals("02")){
//                    kodCaw ="JS";
//                }else if(permohonan.getCawangan().getKod().equals("03")){
//                    kodCaw ="AG";
//                }
//
//              System.out.println("----------kodCaw-----------"+kodCaw);
//
//            PermohonanUkur maxPuPermohonanUkur = devService.getMaxNoPUofPermohonanUkur(kodCaw);
            PermohonanUkur maxPuPermohonanUkur = enforceService.getMaxNoPUofPermohonanUkur(Integer.toString(1900 + dt.getYear()));
            System.out.println("----------maxPuPermohonanUkur-----------" + maxPuPermohonanUkur);

            if (maxPuPermohonanUkur == null) {
                int val = 1;
                String noPU = "";
//                        if(permohonan.getCawangan().getKod().equals("01")){
//                            noPU = (1900+dt.getYear())+"/MT/"+df.format(val);
//                        } else if(permohonan.getCawangan().getKod().equals("02")){
//                            noPU = (1900+dt.getYear())+"/JS/"+df.format(val);
//                        } else if(permohonan.getCawangan().getKod().equals("03")){
//                            noPU = (1900+dt.getYear())+"/AG/"+df.format(val);
//                        }
////                       testing
//                        else if(permohonan.getCawangan().getKod().endsWith("05")){
//                            noPU = (1900+dt.getYear())+"/T/"+df.format(val);
//                        }
                noPU = df.format(val) + "/" + (1900 + dt.getYear());

                System.out.println("-----------Seq-------------" + noPU);
                permohonanUkurTemp.setNoPermohonanUkur(noPU);
            } else {
                String maxNoPU = maxPuPermohonanUkur.getNoPermohonanUkur();
                System.out.println("-----------maxNoPU-------------" + maxNoPU);
                if (Integer.parseInt(maxNoPU.substring(5, 9)) == (1900 + dt.getYear())) {
                    String subNoPU1 = maxNoPU.substring(0, 4);
//                     String subNoPU2 = subNoPU1.substring(subNoPU1.lastIndexOf("0"));
//                     int val = Integer.parseInt(subNoPU2);
                    int val = Integer.parseInt(subNoPU1);
                    val = val + 1;
                    String noPU = "";
                    noPU = df.format(val) + "/" + (1900 + dt.getYear());
                    System.out.println("-----------Seq-------------" + noPU);
                    permohonanUkurTemp.setNoPermohonanUkur(noPU);
                } else {
                    int value = 1;
                    String nopu = "";
                    nopu = df.format(value) + "/" + (1900 + dt.getYear());
                    permohonanUkurTemp.setNoPermohonanUkur(nopu);
                }
            }

        }

        System.out.println("::::::::::; " + getContext().getRequest().getParameter("permohonanUkur.UkurSemulaLot"));
        permohonanUkurTemp.setNoRujukanPejabatTanah(permohonanUkur.getNoRujukanPejabatTanah());
        permohonanUkurTemp.setNoRujukanPejabatUkur(permohonanUkur.getNoRujukanPejabatUkur());
        //permohonanUkurTemp.setNoPermohonanUkur(permohonanUkur.getNoPermohonanUkur());
        permohonanUkurTemp.setPerincianBorang5b(permohonanUkur.getPerincianBorang5b());
        permohonanUkurTemp.setPerincianBorang5c(permohonanUkur.getPerincianBorang5c());
        permohonanUkurTemp.setPerincianBorang5d(permohonanUkur.getPerincianBorang5d());
        permohonanUkurTemp.setPerincianBorang5e(permohonanUkur.getPerincianBorang5e());
        permohonanUkurTemp.setPerincianPajakanLombong(permohonanUkur.getPerincianPajakanLombong());
        permohonanUkurTemp.setPerincianStrata(permohonanUkur.getPerincianStrata());
        permohonanUkurTemp.setStatusSuratanHakmilik(permohonanUkur.getStatusSuratanHakmilik());
        permohonanUkurTemp.setPemberiPengecualian(permohonanUkur.getPemberiPengecualian());
        permohonanUkurTemp.setPerengganKTN(permohonanUkur.getPerengganKTN());
        permohonanUkurTemp.setRujukanKTN(permohonanUkur.getRujukanKTN());
        permohonanUkurTemp.setJumlahPengecualian(permohonanUkur.getJumlahPengecualian());
        permohonanUkurTemp.setJumlahBayaranUkur(permohonanUkur.getJumlahBayaranUkur());
        permohonanUkurTemp.setNoResit(permohonanUkur.getNoResit());
        permohonanUkurTemp.setTarikhResit(permohonanUkur.getTarikhResit());
        permohonanUkurTemp.setStatusSuratanHakmilikSementara(permohonanUkur.getStatusSuratanHakmilikSementara());
        permohonanUkurTemp.setInfoAudit(infoAudit);
        permohonanUkurTemp.setPermohonan(permohonan);
        permohonanUkurTemp.setTujuan(permohonanUkur.getTujuan());
        permohonanUkurTemp.setBermilikTanahKerajaan(permohonanUkur.getBermilikTanahKerajaan());
        permohonanUkurTemp.setPecahanLot(permohonanUkur.getPecahanLot());
        permohonanUkurTemp.setCamtumanLot(permohonanUkur.getCamtumanLot());
        permohonanUkurTemp.setCamtumanLotDipecah(permohonanUkur.getCamtumanLotDipecah());
        permohonanUkurTemp.setSerahSebahagianLot(permohonanUkur.getSerahSebahagianLot());
        permohonanUkurTemp.setBerimilikSemulaBahagian(permohonanUkur.getBerimilikSemulaBahagian());
        permohonanUkurTemp.setAmbilSebahagianLot(permohonanUkur.getAmbilSebahagianLot());
        permohonanUkurTemp.setUkurSemulaLot(ukurSemulaLot);
        permohonanUkurTemp.setBorang10c(permohonanUkur.getBorang10c());
        permohonanUkurTemp.setPengecualianOlehLihat(permohonanUkur.getPengecualianOlehLihat());
        permohonanUkurTemp.setPengecualianSetakat(permohonanUkur.getPengecualianSetakat());
        permohonanUkurTemp.setPengecualianSetakatLihat(permohonanUkur.getPengecualianSetakatLihat());
        permohonanUkurTemp.setNoAturan(permohonanUkur.getNoAturan());
        permohonanUkurTemp.setNoAkaun(permohonanUkur.getNoAkaun());
        if (StringUtils.isNotBlank(anggaranLuas)) {
            BigDecimal luas = new BigDecimal(anggaranLuas);
            permohonanUkurTemp.setAnggaranLuas(luas);
        } else {
            permohonanUkurTemp.setAnggaranLuas(null);
        }

        if (StringUtils.isNotBlank(anggaranLuasUOM)) {
            permohonanUkurTemp.setAnggaranLuatUOM(kodUOMDAO.findById(anggaranLuasUOM));
        } else {
            permohonanUkurTemp.setAnggaranLuatUOM(null);
        }

        if (StringUtils.isNotBlank(kodKategoriTanah)) {
            permohonanUkurTemp.setKategoriTanah(kodKategoriTanahDAO.findById(kodKategoriTanah));
        } else {
            permohonanUkurTemp.setKategoriTanah(null);
        }

        try {
            if (tarikhPerakuan != null) {
                permohonanUkurTemp.setTarikhPerakuan(sdf.parse(tarikhPerakuan));
            }
        } catch (Exception e) {
            LOG.info(e);
        }

        enforceService.simpanPermohonanUkur(permohonanUkurTemp);

        if (pemohon == null) {
            pemohon = new Pemohon();
            infoAudit.setDimasukOleh(pguna);
            infoAudit.setTarikhMasuk(new java.util.Date());
        } else {
            infoAudit = pemohon.getInfoAudit();
            infoAudit.setDiKemaskiniOleh(pguna);
            infoAudit.setTarikhKemaskini(new java.util.Date());
        }

        pemohon.setPermohonan(permohonan);
        pemohon.setCawangan(pguna.getKodCawangan());
        pemohon.setInfoAudit(infoAudit);
        pemohon.setNama(nama);

        alamat = new Alamat();
        if (pemohon.getAlamat() != null) {
            alamat = pemohon.getAlamat();
        }

        alamat.setAlamat1(alamat1);
        alamat.setAlamat2(alamat2);
        alamat.setAlamat3(alamat3);
        alamat.setAlamat4(alamat4);
        alamat.setPoskod(poskod);
        if (StringUtils.isNotBlank(kodNegeriPemohon)) {
            alamat.setNegeri(kodNegeriDAO.findById(kodNegeriPemohon));
        }
        pemohon.setAlamat(alamat);
        enforcementService.simpanMaklumatPihak(pemohon);

        if (idPermohonan != null) {
            permohonanUkur = enforceService.findPermohonanUkurByIdPermohonan(idPermohonan);
        }

        String jspPage;
        if (kodNegeri.equalsIgnoreCase("04")) {
            jspPage = "/WEB-INF/jsp/penguatkuasaan/pu_melaka.jsp";
        } else {
            jspPage = "/WEB-INF/jsp/penguatkuasaan/pu.jsp";
        }
        addSimpleMessage("No PU telah dijana dan maklumat telah berjaya disimpan.");
        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        return new ForwardResolution(jspPage).addParameter("tab", "true");
    }

//    public Resolution janaNomborPU(){
//         NumberFormat df = new DecimalFormat("0000");
//         Date dt= new Date();
//
//        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
//        if(idPermohonan!=null){
//            permohonanUkur = devService.findPermohonanUkurByIdPermohonan(idPermohonan);
//        }
//
//        String kodCaw="";
//                if(permohonan.getCawangan().getKod().equals("01")){
//                    kodCaw ="MT";
//                }else if(permohonan.getCawangan().getKod().equals("02")){
//                    kodCaw ="JS";
//                }else if(permohonan.getCawangan().getKod().equals("03")){
//                    kodCaw ="AG";
//                }
//
//
//        if((permohonanUkur!=null)&& (permohonanUkur.getNoPermohonanUkur()==null) ){
//                // get maximum Permohonan Ukur
//                PermohonanUkur maxPuPermohonanUkur = devService.getMaxNoPUofPermohonanUkur();
//                if(maxPuPermohonanUkur == null){
//                       int val=1;
//                      String noPU = (1900+dt.getYear())+"MT"+df.format(val);
//                      System.out.println("-----------Seq-------------"+noPU);
//                    permohonanUkur.setNoPermohonanUkur(noPU);
//                }else{
//                    String maxNoPU = maxPuPermohonanUkur.getNoPermohonanUkur();
//                       String subNoPU1 = maxNoPU.substring(7);
//                       String subNoPU2 = maxNoPU.substring(subNoPU1.lastIndexOf("0"));
//                       int val = Integer.parseInt(subNoPU2);
//                       val = val+1;
//                       String noPU = (1900+dt.getYear())+"MT"+df.format(val);
//                       System.out.println("-----------Seq-------------"+noPU);
//                       permohonanUkur.setNoPermohonanUkur(noPU);
//                }
//        }
//
//
//        return new ForwardResolution("/WEB-INF/jsp/pembangunan/melaka/pu.jsp").addParameter("tab", "true");
//    }
    public String stageId(String taskId) {
        BPelService service = new BPelService();
        stageId = null;
        if (StringUtils.isNotBlank(taskId)) {
            Task t = null;
            t = service.getTaskFromBPel(taskId, pguna);
            stageId = t.getSystemAttributes().getStage();
        }
        return stageId;
    }

    //-------------------------------Transfer File To JUPEM-------------------------------------//
    public Resolution transferFile() {
        String msg = "";
        String error = "";
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = permohonanDAO.findById(idPermohonan);
        LOG.info("PERMOHONAN : " + permohonan.getIdPermohonan());
        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        String taskId = (String) getContext().getRequest().getSession().getAttribute("taskId");
        LOG.info("PENGGUNA : " + peng.getIdPengguna());
        LOG.info("TASK ID : " + taskId);

        // compare files 
//        List<Dokumen> dokumenList=new ArrayList<Dokumen>();
//        dokumenList = jupemService.findDokumenList(idPermohonan);
//        LOG.info("dokumenList :"+dokumenList.size());
//            for(int i=0;i<dokumenList.size();i++){
//                LOG.info("Kod Dokumen :"+dokumenList.get(i).getKodDokumen().getKod());
//            }
//        List<IntegrasiDokumen> integDokumenList = new ArrayList<IntegrasiDokumen>();
//        LOG.info(this.permohonan.getKodUrusan().getKod());
//        String idAliran = "g_hantar_pu";
//        integDokumenList = jupemService.findIntegrasiDokumenList(permohonan.getKodUrusan().getKod(),idAliran);
//        LOG.info("integDokumenList:"+integDokumenList.size());
//            for(int i=0;i<integDokumenList.size();i++){
//                LOG.info("Kod dokumen :"+integDokumenList.get(i).getKodDokumen().getKod());
//            }
//
//        if(integDokumenList.size() != dokumenList.size()){
//            addSimpleError("Dokumen tiada dalam senarai, sila jana dokumen terlebih dahulu..");
//            return new JSP("pembangunan/melaka/pu.jsp").addParameter("tab", "true");
//        }

//        obi = new OutBoundIntegration(permohonan, peng, taskId);
        obi.setPengguna(pguna);
        obi.setPermohonan(permohonan);
        obi.setTaskId(taskId);
        error = obi.copyfile();

        addSimpleMessage("Fail telah berjaya dihantar");
//        return new ForwardResolution("/WEB-INF/jsp/pengambilan/maklumat_hakmilik_pengambilan.jsp").addParameter("tab", "true");
        return new JSP("penguatkuasaan/pu.jsp").addParameter("tab", "true").addParameter("error", error).addParameter("message", msg);
    }

    public Permohonan getPermohonan() {
        return permohonan;
    }

    public void setPermohonan(Permohonan permohonan) {
        this.permohonan = permohonan;
    }

    public PermohonanUkur getPermohonanUkur() {
        return permohonanUkur;
    }

    public void setPermohonanUkur(PermohonanUkur permohonanUkur) {
        this.permohonanUkur = permohonanUkur;
    }

    public Pengguna getPguna() {
        return pguna;
    }

    public void setPguna(Pengguna pguna) {
        this.pguna = pguna;
    }

    public String getStageId() {
        return stageId;
    }

    public void setStageId(String stageId) {
        this.stageId = stageId;
    }

    public Configuration getConf() {
        return conf;
    }

    public void setConf(Configuration conf) {
        this.conf = conf;
    }

    public String getKodNegeri() {
        return kodNegeri;
    }

    public void setKodNegeri(String kodNegeri) {
        this.kodNegeri = kodNegeri;
    }

    public String getTujuan() {
        return tujuan;
    }

    public void setTujuan(String tujuan) {
        this.tujuan = tujuan;
    }

    public String getIdPermohonan() {
        return idPermohonan;
    }

    public void setIdPermohonan(String idPermohonan) {
        this.idPermohonan = idPermohonan;
    }

    public String getTarikhPerakuan() {
        return tarikhPerakuan;
    }

    public void setTarikhPerakuan(String tarikhPerakuan) {
        this.tarikhPerakuan = tarikhPerakuan;
    }

    public Pemohon getPemohon() {
        return pemohon;
    }

    public void setPemohon(Pemohon pemohon) {
        this.pemohon = pemohon;
    }

    public List<Pemohon> getListPemohon() {
        return listPemohon;
    }

    public void setListPemohon(List<Pemohon> listPemohon) {
        this.listPemohon = listPemohon;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
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

    public String getKodNegeriPemohon() {
        return kodNegeriPemohon;
    }

    public void setKodNegeriPemohon(String kodNegeriPemohon) {
        this.kodNegeriPemohon = kodNegeriPemohon;
    }

    public Alamat getAlamat() {
        return alamat;
    }

    public void setAlamat(Alamat alamat) {
        this.alamat = alamat;
    }

    public String getAnggaranLuas() {
        return anggaranLuas;
    }

    public void setAnggaranLuas(String anggaranLuas) {
        this.anggaranLuas = anggaranLuas;
    }

    public String getAnggaranLuasUOM() {
        return anggaranLuasUOM;
    }

    public void setAnggaranLuasUOM(String anggaranLuasUOM) {
        this.anggaranLuasUOM = anggaranLuasUOM;
    }

    public String getKodKategoriTanah() {
        return kodKategoriTanah;
    }

    public void setKodKategoriTanah(String kodKategoriTanah) {
        this.kodKategoriTanah = kodKategoriTanah;
    }

    public List<HakmilikPermohonan> getHakmilikList() {
        return hakmilikList;
    }

    public void setHakmilikList(List<HakmilikPermohonan> hakmilikList) {
        this.hakmilikList = hakmilikList;
    }

    public HakmilikPermohonan getHp() {
        return hp;
    }

    public void setHp(HakmilikPermohonan hp) {
        this.hp = hp;
    }

    public ArrayList getListIdPermohonan() {
        return listIdPermohonan;
    }

    public void setListIdPermohonan(ArrayList listIdPermohonan) {
        this.listIdPermohonan = listIdPermohonan;
    }

    public String[] getSenaraiIdPermohonan() {
        return senaraiIdPermohonan;
    }

    public void setSenaraiIdPermohonan(String[] senaraiIdPermohonan) {
        this.senaraiIdPermohonan = senaraiIdPermohonan;
    }

    public String getIdPertama() {
        return idPertama;
    }

    public void setIdPertama(String idPertama) {
        this.idPertama = idPertama;
    }

    public String getIdKedua() {
        return idKedua;
    }

    public void setIdKedua(String idKedua) {
        this.idKedua = idKedua;
    }

    public Boolean getStatusNorujukan() {
        return statusNorujukan;
    }

    public void setStatusNorujukan(Boolean statusNorujukan) {
        this.statusNorujukan = statusNorujukan;
    }

    public Hakmilik getHakmilik() {
        return hakmilik;
    }

    public void setHakmilik(Hakmilik hakmilik) {
        this.hakmilik = hakmilik;
    }

    public String getUkurSemulaLot() {
        return ukurSemulaLot;
    }

    public void setUkurSemulaLot(String ukurSemulaLot) {
        this.ukurSemulaLot = ukurSemulaLot;
    }
}
