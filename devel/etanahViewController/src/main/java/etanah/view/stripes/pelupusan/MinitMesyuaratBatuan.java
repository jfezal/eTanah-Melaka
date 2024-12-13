/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 * 
 * Author : Shazwan 15/6/2011
 */
package etanah.view.stripes.pelupusan;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.dao.KodCawanganDAO;
import etanah.dao.KodDokumenDAO;
import etanah.dao.KodWarnaKPDAO;
import etanah.dao.PemohonDAO;
import etanah.dao.PermohonanDAO;
import etanah.dao.PermohonanKertasKandunganDAO;
import etanah.model.HakmilikPermohonan;
import etanah.model.InfoAudit;
import etanah.model.KodCawangan;
import etanah.model.KodDokumen;
import etanah.model.KodWarnaKP;
import etanah.model.Pemohon;
import etanah.model.PemohonHubungan;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.model.PermohonanBahanBatuan;
import etanah.model.PermohonanKertas;
import etanah.model.PermohonanKertasKandungan;
import etanah.model.PermohonanPermitItem;
import etanah.model.PermohonanRujukanLuar;
import etanah.model.Pihak;
import etanah.model.PihakPengarah;
import etanah.service.BPelService;
import etanah.service.PelupusanPtService;
import etanah.service.PelupusanService;
import etanah.view.etanahActionBeanContext;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Vector;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.StreamingResolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;
import org.apache.commons.lang.StringUtils;
import oracle.bpel.services.workflow.task.model.Task;
import org.apache.log4j.Logger;

/**
 *
 * @author Shazwan
 */
@UrlBinding("/pelupusan/minit_mesyuaratBatuan")
public class MinitMesyuaratBatuan extends AbleActionBean {

    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    KodWarnaKPDAO kodWarnaDAO;
    @Inject
    etanah.Configuration conf;
    @Inject
    PermohonanKertasKandunganDAO permohonanKertasKandunganDAO;
    @Inject
    PemohonDAO pemohonDAO;
    @Inject
    KodCawanganDAO kodCawanganDAO;
    @Inject
    KodDokumenDAO kodDokumenDAO;
    @Inject
    PelupusanService pelupusanService;
    Logger logger = Logger.getLogger(DrafMMKNActionBean.class);
    @Inject
    BPelService service;
    @Inject
    PelupusanPtService pelPtService;
    @Inject
    PermohonanKertasKandunganDAO permohonanKertasKandDAO;
    private Permohonan permohonan;
    private Pemohon pemohon;
    private String stageId;
    private Pengguna peng;
    private String idPermohonan;
    private String idPemohon;
    private String kodNeg;
    private String namaNegeri;
    private String tajukMesyuarat;
    private String kenyataanBil1;
    private String kandunganPopUp;
    private int maxBil = 0;
    private List<PermohonanKertasKandungan> senaraiLaporanKetuaMenteri = new Vector();
    private List<PermohonanBahanBatuan> senaraiBahanBatu;
    private HakmilikPermohonan hakmilikPermohonan;
    private String subTajukPopUp;
    private PermohonanKertas permohonankertas;
    
    @DefaultHandler
    public Resolution showForm1() {
        return new JSP("pelupusan/batuan/minit_mesyuarat.jsp").addParameter("tab", "true");
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

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {
        boolean checkExistBil0 = false;
        boolean checkExistBil1 = false;
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        
        peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        String taskId = (String) getContext().getRequest().getSession().getAttribute("taskId");
        tajukMesyuarat = kodDokumenDAO.findById("MYB").getNama();
        stageId = stageId(taskId);
        kodNeg = conf.getProperty("kodNegeri");
        namaNegeri = new String();
        if(kodNeg.equals("04"))
            namaNegeri= "Melaka";
        if(kodNeg.equals("05")) 
            namaNegeri= "Negeri Sembilan";
        logger.info("THIS IS IDPERMOHONAN = " + idPermohonan);
        
        if (idPermohonan != null) {
            permohonan = permohonanDAO.findById(idPermohonan);
             permohonankertas = new PermohonanKertas();
             permohonankertas = pelupusanService.findPermohonanKertasByIdPermohonanNKodDokumen(idPermohonan, "MYB");
            
            hakmilikPermohonan = pelupusanService.findByIdPermohonan(idPermohonan);
            pemohon = pelupusanService.findPemohonByIdPemohon(idPermohonan);
            senaraiBahanBatu = pelupusanService.findPermohonanBahanBatuanByIdMohonList(idPermohonan);
            
            PermohonanKertas mohonKertas = new PermohonanKertas();
            
            List senaraiLaporanKandunganPTemp = new Vector();
            List senaraiDefaultValue = new Vector();
            String permitItem = new String();
            String jenisBahanBatu = new String();
            List senaraiMohonPermitItem = new Vector();
            senaraiMohonPermitItem = pelupusanService.findPermohonanPermitItemByIdMohonList(idPermohonan);
            
            PermohonanBahanBatuan mohonBahanBatuan = (PermohonanBahanBatuan) senaraiBahanBatu.get(0);
            if (senaraiMohonPermitItem.size() == 1) {
                jenisBahanBatu = mohonBahanBatuan.getJenisBahanBatu().getNama();
            } else {
                jenisBahanBatu = jenisBahanBatu + mohonBahanBatuan.getJenisBahanBatu().getNama() + " ";
            }
            settingBil1(senaraiMohonPermitItem);
            /*
             * MOHON_KERTAS
             */
            mohonKertas = pelupusanService.findPermohonanKertasByIdPermohonanNKodDokumen(idPermohonan, "MYB"); 
            if (mohonKertas != null) {
                /*
                 * FOR BIL 1 
                 * BIL 1
                 */
                senaraiLaporanKandunganPTemp = pelupusanService.findByIdKertasOnlyWithOrderByBil(mohonKertas.getIdKertas());
               senaraiLaporanKetuaMenteri = new Vector();
                if(senaraiLaporanKandunganPTemp.size()>0){
                    maxBil = 0;
                    for(int i=0;i<senaraiLaporanKandunganPTemp.size();i++){
                        PermohonanKertasKandungan mohonKK = new PermohonanKertasKandungan();
                        mohonKK = (PermohonanKertasKandungan) senaraiLaporanKandunganPTemp.get(i); 
                        if(mohonKK.getBil()==0)
                            tajukMesyuarat = mohonKK.getKandungan();
                        else if(mohonKK.getBil()==1)
                            kenyataanBil1 = mohonKK.getKandungan();                       
                        else 
                            senaraiLaporanKetuaMenteri.add(mohonKK);
                        maxBil = maxBil+1;
                    }
                 }
                PermohonanKertasKandungan mohonKertasKand = new PermohonanKertasKandungan();
                List<PermohonanKertasKandungan> vecMohonKertas;
                vecMohonKertas = pelupusanService.findByIdKertasOnly(mohonKertas.getIdKertas());
                if (vecMohonKertas.size() > 0) {
                    for (int i = 0; i < vecMohonKertas.size(); i++) {
                        PermohonanKertasKandungan pkk = new PermohonanKertasKandungan();
                        pkk = vecMohonKertas.get(i);
                        switch (pkk.getBil()) {
                            case (0):
                                tajukMesyuarat = pkk.getKandungan();
                                checkExistBil0 = true;
                                break;
                            case (1):
                                kenyataanBil1 = pkk.getKandungan();
                                checkExistBil1 = true;
                                break;
                        }
                    }
                    senaraiMohonPermitItem = new Vector();
                    senaraiMohonPermitItem = pelupusanService.findPermohonanPermitItemByIdMohonList(idPermohonan);

                    if (!checkExistBil0) {
                        tajukMesyuarat = kodDokumenDAO.findById("MYB").getNama();
                    }
                    if (!checkExistBil1) {
                        settingBil1(senaraiMohonPermitItem);
                    }
                } else {
                    senaraiMohonPermitItem = new Vector();
                    senaraiMohonPermitItem = pelupusanService.findPermohonanPermitItemByIdMohonList(idPermohonan);

                    tajukMesyuarat = kodDokumenDAO.findById("MYB").getNama();
                    settingBil1(senaraiMohonPermitItem);
                }

            } else {

                senaraiMohonPermitItem = new Vector();
                senaraiMohonPermitItem = pelupusanService.findPermohonanPermitItemByIdMohonList(idPermohonan);

                 tajukMesyuarat = kodDokumenDAO.findById("MYB").getNama();
                settingBil1(senaraiMohonPermitItem);
            }
        }
    }
    
    public void settingBil1(List senaraiMohonPermitItem) {
        /*
         * SETTING TAJUK DRAF WHICH BIL =1; 
         */
        String permitItem = new String();
            String jenisBahanBatu = new String();
            senaraiMohonPermitItem = pelupusanService.findPermohonanPermitItemByIdMohonList(idPermohonan);
            PermohonanBahanBatuan mohonBahanBatuan = (PermohonanBahanBatuan) senaraiBahanBatu.get(0);
            if (senaraiMohonPermitItem.size() == 1) {
                jenisBahanBatu = mohonBahanBatuan.getJenisBahanBatu().getNama();
            } else {
                jenisBahanBatu = jenisBahanBatu + mohonBahanBatuan.getJenisBahanBatu().getNama() + " ";
            }
            kenyataanBil1 = "Diangkat Rencana Pentadbir Tanah "+ pemohon.getCawangan().getName()+", "+namaNegeri+" seperti di kandungan (1) permohonan daripada "+pemohon.getPihak().getNama()+" untuk mendapatkan permit mengeluarkan "
                              +jenisBahanBatu+" sebanyak "+mohonBahanBatuan.getJumlahIsipaduDipohon() + " " + mohonBahanBatuan.getJumlahIsipaduDipohonUom().getNama()+" di atas tanah "+hakmilikPermohonan.getLokasi()+" ,Mukim "+hakmilikPermohonan.getBandarPekanMukimBaru().getNama()
                              +" Daerah "+pemohon.getCawangan().getName()+" untuk pertimbangan YAB Datuk Seri.";
    }

    public Resolution refreshDrafJKBB() {
        rehydrate();
        return showForm1();
    }

    public Resolution SimpandrafJKBB() {

        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        InfoAudit info = new InfoAudit();
        info.setDimasukOleh(peng);
        info.setTarikhMasuk(new java.util.Date());
//        int checkSenarai = this.senaraiLaporanKandunganPerihalTanah.size();
        permohonan = pelupusanService.findPermohonanByIdPermohonanWithoutKodUrusan(idPermohonan);
            /*
             * MOHON_KERTAS
             */
            if (permohonan != null) {
                PermohonanKertas mohonKertas = new PermohonanKertas();
                mohonKertas = pelupusanService.findPermohonanKertasByIdPermohonanNKodDokumen(idPermohonan, "MYB");
                KodDokumen kodDokumen = kodDokumenDAO.findById("MYB");
                if (mohonKertas == null) {
                    mohonKertas = new PermohonanKertas();
                    mohonKertas.setCawangan(permohonan.getCawangan());
                    mohonKertas.setPermohonan(permohonan);
                    mohonKertas.setTajuk(kodDokumen.getNama());
                    mohonKertas.setInfoAudit(info);
                    mohonKertas.setKodDokumen(kodDokumenDAO.findById("MYB"));
                    pelupusanService.simpanSavePermohonanKertas(mohonKertas);
                    mohonKertas = new PermohonanKertas();
                    mohonKertas = pelupusanService.findPermohonanKertasByIdPermohonanNKodDokumen(idPermohonan, "MYB");
                }
                /*
                 * END OF MOHON KERTAS
                 */
                /*
                 * MOHON KERTAS KANDUNGAN
                 */

                if (mohonKertas != null) {
                    List listmohonKertasKandungan = pelupusanService.findByIdKertasOnly(mohonKertas.getIdKertas());
                    List listmohonKertasKandunganInsert = new Vector();
                    boolean checkExistBil0 = false;
                    boolean checkExistBil1 = false;
                    if (listmohonKertasKandungan.size() > 0) {


                        for (int i = 0; i < listmohonKertasKandungan.size(); i++) {
                            PermohonanKertasKandungan mohonKertasKandungan = new PermohonanKertasKandungan();
                            mohonKertasKandungan = (PermohonanKertasKandungan) listmohonKertasKandungan.get(i);
                            switch (mohonKertasKandungan.getBil()) {
                                case 0:
                                        mohonKertasKandungan.setKandungan(tajukMesyuarat);
                                        listmohonKertasKandunganInsert.add(mohonKertasKandungan);
                                        checkExistBil0 = true;
                                        break;
                                case 1:
                                        mohonKertasKandungan.setKandungan(kenyataanBil1);
                                        listmohonKertasKandunganInsert.add(mohonKertasKandungan);
                                        checkExistBil1 = true;
                                        break;
                                /*
                                 *  END OF DEFAULT VALUE
                                 */
                            }

                        }
                        /*
                         * DEFAULT VALUE IF NOT IN DB
                         */
                        if (!checkExistBil0) {
                            PermohonanKertasKandungan mohonKertasKandungan = new PermohonanKertasKandungan();
                            mohonKertasKandungan.setCawangan(permohonan.getCawangan());
                            mohonKertasKandungan.setKertas(mohonKertas);
                            mohonKertasKandungan.setInfoAudit(info);
                            mohonKertasKandungan.setBil(0);
                            mohonKertasKandungan.setKandungan(tajukMesyuarat);
                            listmohonKertasKandunganInsert.add(mohonKertasKandungan);
                        }
                        if (!checkExistBil1) {
                            PermohonanKertasKandungan mohonKertasKandungan = new PermohonanKertasKandungan();
                            mohonKertasKandungan.setCawangan(permohonan.getCawangan());
                            mohonKertasKandungan.setKertas(mohonKertas);
                            mohonKertasKandungan.setInfoAudit(info);
                            mohonKertasKandungan.setBil(1);
                            mohonKertasKandungan.setKandungan(kenyataanBil1);
                            listmohonKertasKandunganInsert.add(mohonKertasKandungan);
                        }
                        /*
                         * DYNAMIC VALUE
                         */
//                        for (int z = 0; z < senaraiLaporanKetuaMenteri.size(); z++) {
//                            String test = (String) getContext().getRequest().getParameter("minit_mesyuarat.kandungan" + z);
//                            PermohonanKertasKandungan mohonKertasKand = new PermohonanKertasKandungan();
//
//                            mohonKertasKand = senaraiLaporanKetuaMenteri.get(z);
//                            mohonKertasKand.setKandungan(test);
//                            listmohonKertasKandunganInsert.add(mohonKertasKand);
//                        }
                        /*
                         * END OF DYNAMIC VALUE
                         */

                    } else {
                        /*
                         * DEFAULT VALUE IF NOT IN DB
                         */
                        if (!checkExistBil0) {
                            PermohonanKertasKandungan mohonKertasKandungan = new PermohonanKertasKandungan();
                            mohonKertasKandungan.setCawangan(permohonan.getCawangan());
                            mohonKertasKandungan.setKertas(mohonKertas);
                            mohonKertasKandungan.setInfoAudit(info);
                            mohonKertasKandungan.setBil(0);
                            mohonKertasKandungan.setKandungan(tajukMesyuarat);
                            listmohonKertasKandunganInsert.add(mohonKertasKandungan);
                        }
                        if (!checkExistBil1) {
                            PermohonanKertasKandungan mohonKertasKandungan = new PermohonanKertasKandungan();
                            mohonKertasKandungan.setCawangan(permohonan.getCawangan());
                            mohonKertasKandungan.setKertas(mohonKertas);
                            mohonKertasKandungan.setInfoAudit(info);
                            mohonKertasKandungan.setBil(1);
                            mohonKertasKandungan.setKandungan(kenyataanBil1);
                            listmohonKertasKandunganInsert.add(mohonKertasKandungan);
                        }
                        /*
                         * DYNAMIC VALUE
                         */
                        //
//                        for (int z = 0; z < senaraiLaporanKetuaMenteri.size(); z++) {
//                            String test = (String) getContext().getRequest().getParameter("minit_mesyuarat.kandungan" + z); 
//                            PermohonanKertasKandungan mohonKertasKand = new PermohonanKertasKandungan();
//
//                            mohonKertasKand = senaraiLaporanKetuaMenteri.get(z);
//                            mohonKertasKand.setKandungan(test);
//                            listmohonKertasKandunganInsert.add(mohonKertasKand);
//                        }
                        /*
                         * END OF DYNAMIC VALUE
                         */
                    }
                    /*
                     * SAVING DATA INTO MOHON KERTAS KANDUNGAN
                     */
                    for (int ii = 0; ii < listmohonKertasKandunganInsert.size(); ii++) {
                        PermohonanKertasKandungan mohonKertasKand = new PermohonanKertasKandungan();
                        mohonKertasKand = (PermohonanKertasKandungan) listmohonKertasKandunganInsert.get(ii);
                        PermohonanKertasKandungan mohonKertasKandInsert = pelupusanService.findkandunganByIdKandungan(mohonKertasKand.getIdKandungan());
                        if (mohonKertasKandInsert != null) {
                            pelupusanService.simpanPermohonanKertasKandungan(mohonKertasKand);
                        } else {
                            pelupusanService.simpanSavePermohonanKertasKandungan(mohonKertasKand);
                        }
                    }
                    /*
                     * 
                     */
                } else {
                    addSimpleMessage("Mohon Kertas IS NULL");
                }
                /*
                 * END OF MOHON KERTAS KANDUNGAN
                 */

                addSimpleMessage("Maklumat telah berjaya disimpan");
            } else {
                addSimpleMessage("IDPERMOHONAN TIDAK DIJUMPAI -- SILA HUBUNGI PENTADBIR SISTEM");
            }
            rehydrate();

        return new JSP("pelupusan/batuan/minit_mesyuarat.jsp").addParameter("tab", "true");
    }
    
    public Resolution simpanMinitMesyPopUp() {
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        subTajukPopUp = (String) getContext().getRequest().getParameter("subTajukPopUp");
        logger.info(subTajukPopUp);
        peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        InfoAudit info = new InfoAudit();
        info = pelupusanService.getInfoAudit(peng);

        Permohonan mohon = new Permohonan();
        mohon = pelupusanService.findPermohonanByIdPermohonanWithoutKodUrusan(idPermohonan);
        PermohonanKertas mohonKertas = new PermohonanKertas();
        mohonKertas = pelupusanService.findPermohonanKertasByIdPermohonanNKodDokumen(idPermohonan, "MYB");
        KodDokumen kodDok = new KodDokumen();
        kodDok = kodDokumenDAO.findById("MYB");
        if (mohon != null) {

            if (mohonKertas != null) {
                mohonKertas.setCawangan(mohon.getCawangan());
                mohonKertas.setPermohonan(mohon);
                mohonKertas.setTajuk(kodDok.getNama());
                mohonKertas.setInfoAudit(info);
                mohonKertas.setKodDokumen(kodDokumenDAO.findById("MYB"));
                pelupusanService.simpanPermohonanKertas(mohonKertas);
            } else {
                mohonKertas = new PermohonanKertas();
                mohonKertas.setCawangan(mohon.getCawangan());
                mohonKertas.setPermohonan(mohon);
                mohonKertas.setTajuk(kodDok.getNama());
                mohonKertas.setInfoAudit(info);
                mohonKertas.setKodDokumen(kodDokumenDAO.findById("MYB"));
                pelupusanService.simpanSavePermohonanKertas(mohonKertas);
            }
            mohonKertas = pelupusanService.findPermohonanKertasByIdPermohonanNKodDokumen(idPermohonan, "MYB");
            if (mohonKertas != null) {
                
                popUpData(mohonKertas,mohon,info);
                
            } else {
                addSimpleError("MOHON KERTAS TIDAK DIJUMPAI, SILA HUBUNGI PENTADBIR SISTEM");
            }
            rehydrate();
            getContext().getRequest().setAttribute("flag", Boolean.FALSE);

        } else {
            addSimpleError("IDPERMOHONAN TIDAK DIJUMPAI, SILA HUBUNGI PENTADBIR SISTEM");
        }

        return new JSP("pelupusan/batuan/tambah_MinitMesyuarat.jsp").addParameter("tab", "true");
    }
    public void parentData(PermohonanKertas mohonKertas,Permohonan mohon,InfoAudit info){
        if(senaraiLaporanKetuaMenteri.size()>0){
            
            List <PermohonanKertasKandungan> vec = pelupusanService.findByIdKertasOnly(mohonKertas.getIdKertas());
            /*
             * BIL 1
             */
            if(vec.size()>0){
                boolean exist= false;
                    for(int z=0;z<vec.size();z++){
                        PermohonanKertasKandungan mohonKertasKandTemp = new PermohonanKertasKandungan();
                        mohonKertasKandTemp = vec.get(z);
                        if(mohonKertasKandTemp.getBil()==1){
                            pelupusanService.simpanPermohonanKertasKandungan(mohonKertasKandTemp);
                            exist = true;
                            break;
                        }
                    }
                    if(!exist){
                        PermohonanKertasKandungan mohonKK = new PermohonanKertasKandungan();
                        mohonKK.setBil(1);
                        mohonKK.setCawangan(mohonKertas.getCawangan());
                        mohonKK.setKertas(mohonKertas);
                        mohonKK.setKandungan(kenyataanBil1);
                        mohonKK.setInfoAudit(info);
                        pelupusanService.simpanSavePermohonanKertasKandungan(mohonKK);
                    }
                        
            }else{
                PermohonanKertasKandungan mohonKK = new PermohonanKertasKandungan();
                mohonKK.setBil(1);
                mohonKK.setCawangan(mohonKertas.getCawangan());
                mohonKK.setKertas(mohonKertas);
                mohonKK.setKandungan(kenyataanBil1);
                mohonKK.setInfoAudit(info);
                pelupusanService.simpanSavePermohonanKertasKandungan(mohonKK);
            }
            /*
             * DYNAMIC VALUE
             */
            for(int i=0;i<senaraiLaporanKetuaMenteri.size();i++){
                PermohonanKertasKandungan mohonKertasKand = new PermohonanKertasKandungan();
                 String kandungan = (String) getContext().getRequest().getParameter("minit_mesyuarat.kandungan"+i);
                mohonKertasKand = senaraiLaporanKetuaMenteri.get(i);
                mohonKertasKand.setKandungan(kandungan);
                if(vec.size()>0){
                    boolean exist= false;
                    for(int z=0;z<vec.size();z++){
                        PermohonanKertasKandungan mohonKertasKandTemp = new PermohonanKertasKandungan();
                        mohonKertasKandTemp = vec.get(z);
                        if(mohonKertasKand.getBil()==mohonKertasKandTemp.getBil()){
                            exist = true;
                            break;
                        }
                    }
                    if(exist){
                         pelupusanService.simpanPermohonanKertasKandungan(mohonKertasKand);
                    }else
                        pelupusanService.simpanSavePermohonanKertasKandungan(mohonKertasKand);
                }else{
                    pelupusanService.simpanSavePermohonanKertasKandungan(mohonKertasKand);
                }
            }
        }
    }
    public void popUpData(PermohonanKertas mohonKertas,Permohonan mohon,InfoAudit info){
        List <PermohonanKertasKandungan> senaraiLaporanKetuaMenteriPopUp = pelupusanService.findByIdKertasOnly(mohonKertas.getIdKertas());
                if (senaraiLaporanKetuaMenteriPopUp.size() > 0) {
                    /*
                     * CHECKING WHETHER THE SUBTAJUK EXIST OR NOT, IF EXIST,OVERWRITE KANDUNGAN, ELSE INSERT NEW
                     */
                    boolean exists = false;
                    for (int i = 0; i < senaraiLaporanKetuaMenteriPopUp.size(); i++) {
                        PermohonanKertasKandungan mohonKertasKand = new PermohonanKertasKandungan();
                        mohonKertasKand = senaraiLaporanKetuaMenteriPopUp.get(i);
                        int bil = Integer.parseInt(subTajukPopUp);
                        if (mohonKertasKand.getBil()== bil) {
                            exists = true;
                        }
                    }
                    /*
                     * IF NOT EXISTS, INSERT NEW
                     */
                    if (!exists) {
                        PermohonanKertasKandungan mohonKertasKand = new PermohonanKertasKandungan();
                        mohonKertasKand.setCawangan(mohon.getCawangan());
                        mohonKertasKand.setKertas(mohonKertas);
                        mohonKertasKand.setKandungan(kandunganPopUp);
                        mohonKertasKand.setInfoAudit(info);
                        mohonKertasKand.setBil(Integer.parseInt(subTajukPopUp));
                        pelupusanService.simpanSavePermohonanKertasKand(mohonKertasKand);
                    } else {
                        for (int i = 0; i < senaraiLaporanKetuaMenteri.size(); i++) {
                            PermohonanKertasKandungan mohonKertasKand = new PermohonanKertasKandungan();
                            mohonKertasKand = senaraiLaporanKetuaMenteri.get(i);
                            int bil = Integer.parseInt(subTajukPopUp);
                            if (mohonKertasKand.getBil()== bil) {
                                mohonKertasKand.setCawangan(mohon.getCawangan());
                                mohonKertasKand.setKertas(mohonKertas);
                                mohonKertasKand.setKandungan(kandunganPopUp);
                                mohonKertasKand.setInfoAudit(info);
                                mohonKertasKand.setBil(bil);
                                pelupusanService.simpanPermohonanKertasKandungan(mohonKertasKand);
                            }
                        }
                    }
                } else {
                    PermohonanKertasKandungan mohonKertasKand = new PermohonanKertasKandungan();
                    mohonKertasKand.setCawangan(mohon.getCawangan());
                    mohonKertasKand.setKertas(mohonKertas);
                    mohonKertasKand.setKandungan(kandunganPopUp);
                    mohonKertasKand.setInfoAudit(info);
                    mohonKertasKand.setBil(Integer.parseInt(subTajukPopUp));
                    pelupusanService.simpanSavePermohonanKertasKand(mohonKertasKand);

                }
    }
    public Resolution showTambahMinitMesy() {
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        PermohonanKertas mohonKertas = new PermohonanKertas();
        mohonKertas = pelupusanService.findPermohonanKertasByIdPermohonanNKodDokumen(idPermohonan, "MYB");
        Permohonan mohon = new Permohonan();
        mohon = pelupusanService.findPermohonanByIdPermohonanWithoutKodUrusan(idPermohonan);
        InfoAudit info = new InfoAudit();
        info = pelupusanService.getInfoAudit(peng);
//        parentData(mohonKertas,mohon,info);
        
        
        if (mohonKertas != null) {
            if (senaraiLaporanKetuaMenteri.size() > 0) {
                PermohonanKertasKandungan mohonKertasKand = new PermohonanKertasKandungan();
//                mohonKertasKand = senaraiLaporanKetuaMenteri.get(senaraiLaporanKetuaMenteri.size() - 1);
                mohonKertasKand = senaraiLaporanKetuaMenteri.get(senaraiLaporanKetuaMenteri.size()-1);
                subTajukPopUp =String.valueOf(mohonKertasKand.getBil()+1);
            } else {
                subTajukPopUp = "2";
            }
        } else {
            subTajukPopUp = "2";
        }

        getContext().getRequest().setAttribute("flag", Boolean.TRUE);
        return new JSP("pelupusan/batuan/tambah_MinitMesyuarat.jsp").addParameter("popup", "true");
    }

    public Resolution simpanKandungan() throws ParseException {


        int index = 0;
        index = Integer.parseInt(getContext().getRequest().getParameter("index"));
        String kand = getContext().getRequest().getParameter("kandungan");
//        LOG.info("CHECKING:..... index :" + index + " kand :" + kand);
        switch (index) {
            case 1:

                break;
            case 2: //FOR Perihal Permohonan
                updateKandungan(2, kand);

                break;
//            case 3:
//
//                updateKandungan(3, kand);
//
//                break;
//            case 4:
//
//                updateKandungan(4, kand);
//
//                break;
            case 5:// FOR ASAS-ASAS PERTIMBANGAN

                updateKandungan(5, kand);

                break;
            case 6:// PERAKUAN PENTADBIR TANAH DAERAH

                updateKandungan(6, kand);
                break;
              case 7: // PERAKUAN PENGARAH TANAH DAN GALIAN
                  updateKandungan(7, kand);
                  break;
              case 8: // FOR HURAIAN PENGARAH TANAH DAN GALIAN
                  updateKandungan(8, kand);
                  break;
              case 9: // FOR SYOR PENGARAH TANAH DAN GALIAN
                  updateKandungan(9, kand);
                  break;
              case 22: // FOR PERIHAL TANAH 2.3.*
                  updateKandungan(22, kand);
                  break;
//            default:
//                LOG.info("alamak!! tiada index");
        }
        rehydrate();
        addSimpleMessage("Maklumat Berjaya Disimpan");
        return new JSP("pelupusan/batuan/minit_mesyuarat.jsp").addParameter("tab", "true");
    }
    public void updateKandungan(int i, String kand) {


        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = permohonanDAO.findById(idPermohonan);
        Pengguna pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        InfoAudit infoAudit = new InfoAudit();
        KodCawangan cawangan = new KodCawangan();
        cawangan = kodCawanganDAO.findById(pengguna.getKodCawangan().getKod());
//        logger.info(permohonankertas.getKodDokumen().getKod());

        if (permohonankertas != null) {
            infoAudit = permohonankertas.getInfoAudit();
            infoAudit.setDiKemaskiniOleh(pengguna);
            infoAudit.setTarikhKemaskini(new java.util.Date());
        } else {
            permohonankertas = new PermohonanKertas();
            infoAudit.setDimasukOleh(pengguna);
            infoAudit.setTarikhMasuk(new java.util.Date());

        }
        permohonankertas.setTajuk("Minit Keputusan Y.A.B Ketua Menteri");
        KodDokumen kod = kodDokumenDAO.findById("MYB");
        permohonankertas.setKodDokumen(kod);
        permohonankertas.setCawangan(cawangan);
        permohonankertas.setInfoAudit(infoAudit);
        permohonankertas.setPermohonan(permohonan);
        pelPtService.simpanPermohonanKertas(permohonankertas);

        long a = permohonankertas.getIdKertas();
        List<PermohonanKertasKandungan> plk = pelPtService.findByIdLapor(a, i);

        PermohonanKertasKandungan pLK = new PermohonanKertasKandungan();
//        LOG.info("index :" + i + " kand :" + kand + " id_lapor :" + a);
        if(maxBil==0||maxBil==1){
            List<PermohonanKertasKandungan> plkTemp = pelPtService.findMohonKertasByMohonKertas(a);
            int bil = 0;
            if(plkTemp.size()>0){
                for(PermohonanKertasKandungan pkk:plkTemp){
                    if(pkk.getBil()>1){
                        if(pkk.getBil()>bil){
                            bil = pkk.getBil();
                           
                        }
                    }
                    logger.info(bil);
                        
                }
                 pLK.setBil(bil+1);
            }else
                pLK.setBil(2);
        }
        else{
            List<PermohonanKertasKandungan> plkTemp = pelPtService.findMohonKertasByMohonKertas(a);
            int bil = 0;
            if(plkTemp.size()>0){
                for(PermohonanKertasKandungan pkk:plkTemp){
                    if(pkk.getBil()>1){
                        if(pkk.getBil()>bil){
                            bil = pkk.getBil();
                           
                        }
                    }
                    logger.info(bil);
                        
                }
                 pLK.setBil(bil+1);
            }else
                pLK.setBil(2);
        }
        pLK.setKandungan(kand);
        pLK.setKertas(permohonankertas);
        pLK.setInfoAudit(infoAudit);
        pLK.setCawangan(cawangan);
        pelPtService.simpanPermohonanKertasKandungan(pLK);

    }
    public Resolution tambahRow() {

        PermohonanKertasKandungan pkk = new PermohonanKertasKandungan();
        int index = 0;
        index = Integer.parseInt(getContext().getRequest().getParameter("index"));
        switch (index) {
            case 1:
                break;
            case 2:
                pkk = new PermohonanKertasKandungan();
                pkk.setBil(maxBil+1);
                senaraiLaporanKetuaMenteri.add(pkk);
                break;
            default:
        }
        return new JSP("pelupusan/batuan/minit_mesyuarat.jsp").addParameter("tab", "true");
    }
    public Resolution deleteRow() throws ParseException {


        System.out.println("-----------deleteKandungan---------");
        String idKand = getContext().getRequest().getParameter("idKandungan");
        System.out.println("-----------deleteSingle---------" + idKand);
        if (idKand != null) {
            PermohonanKertasKandungan plk = new PermohonanKertasKandungan();
            plk = permohonanKertasKandDAO.findById(Long.parseLong(idKand));
            if (plk != null) {

                try {
                    pelPtService.deleteKertasKandungan(plk);
                } catch (Exception e) {
                }
            }
        }
        rehydrate();
        return new JSP("pelupusan/batuan/minit_mesyuarat.jsp").addParameter("tab", "true");
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

    public Pengguna getPeng() {
        return peng;
    }

    public void setPeng(Pengguna peng) {
        this.peng = peng;
    }

    public String getStageId() {
        return stageId;
    }

    public void setStageId(String stageId) {
        this.stageId = stageId;
    }

    public HakmilikPermohonan getHakmilikPermohonan() {
        return hakmilikPermohonan;
    }

    public void setHakmilikPermohonan(HakmilikPermohonan hakmilikPermohonan) {
        this.hakmilikPermohonan = hakmilikPermohonan;
    }

    public String getIdPemohon() {
        return idPemohon;
    }

    public void setIdPemohon(String idPemohon) {
        this.idPemohon = idPemohon;
    }

    public String getIdPermohonan() {
        return idPermohonan;
    }

    public void setIdPermohonan(String idPermohonan) {
        this.idPermohonan = idPermohonan;
    }

    public String getKodNeg() {
        return kodNeg;
    }

    public void setKodNeg(String kodNeg) {
        this.kodNeg = kodNeg;
    }

    public List<PermohonanKertasKandungan> getSenaraiLaporanKetuaMenteri() {
        return senaraiLaporanKetuaMenteri;
    }

    public void setSenaraiLaporanKetuaMenteri(List<PermohonanKertasKandungan> senaraiLaporanKetuaMenteri) {
        this.senaraiLaporanKetuaMenteri = senaraiLaporanKetuaMenteri;
    }

    public String getTajukMesyuarat() {
        return tajukMesyuarat;
    }

    public void setTajukMesyuarat(String tajukMesyuarat) {
        this.tajukMesyuarat = tajukMesyuarat;
    }

    public List<PermohonanBahanBatuan> getSenaraiBahanBatu() {
        return senaraiBahanBatu;
    }

    public void setSenaraiBahanBatu(List<PermohonanBahanBatuan> senaraiBahanBatu) {
        this.senaraiBahanBatu = senaraiBahanBatu;
    }

    public String getNamaNegeri() {
        return namaNegeri;
    }

    public void setNamaNegeri(String namaNegeri) {
        this.namaNegeri = namaNegeri;
    }

    public String getSubTajukPopUp() {
        return subTajukPopUp;
    }

    public void setSubTajukPopUp(String subTajukPopUp) {
        this.subTajukPopUp = subTajukPopUp;
    }

    public String getKenyataanBil1() {
        return kenyataanBil1;
    }

    public void setKenyataanBil1(String kenyataanBil1) {
        this.kenyataanBil1 = kenyataanBil1;
    }

    public String getKandunganPopUp() {
        return kandunganPopUp;
    }

    public void setKandunganPopUp(String kandunganPopUp) {
        this.kandunganPopUp = kandunganPopUp;
    }

    public PermohonanKertas getPermohonankertas() {
        return permohonankertas;
    }

    public void setPermohonankertas(PermohonanKertas permohonankertas) {
        this.permohonankertas = permohonankertas;
    }

    public int getMaxBil() {
        return maxBil;
    }

    public void setMaxBil(int maxBil) {
        this.maxBil = maxBil;
    }


    
}
