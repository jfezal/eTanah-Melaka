/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package etanah.view.stripes.pembangunan;


import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.dao.FasaPermohonanDAO;
import etanah.dao.HakmilikDAO;
import etanah.dao.KodDokumenDAO;
import etanah.dao.PemohonDAO;
import etanah.dao.PermohonanDAO;
import etanah.dao.PermohonanKertasDAO;
import etanah.dao.PermohonanKertasKandunganDAO;
import etanah.model.Hakmilik;
import etanah.model.HakmilikPermohonan;
import etanah.model.HakmilikPihakBerkepentingan;
import etanah.model.InfoAudit;
import etanah.model.KodDokumen;
import etanah.model.Pemohon;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.model.PermohonanKertas;
import etanah.model.PermohonanKertasKandungan;
import etanah.service.BPelService;
import etanah.model.PermohonanRujukanLuar;
import etanah.service.PembangunanService;
import etanah.view.etanahActionBeanContext;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;
import org.apache.commons.lang.StringUtils;
import oracle.bpel.services.workflow.task.model.Task;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.Date;
import net.sourceforge.stripes.action.RedirectResolution;
import org.apache.log4j.Logger;
/**
 *
 * @author NageswaraRao
 */

@UrlBinding("/pembangunan/notaSiasatan")
public class NotaSiasatanActionBean extends AbleActionBean {
    private static final Logger LOG = Logger.getLogger(NotaSiasatanActionBean.class);
    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    HakmilikDAO hakmilikDAO;
    @Inject
    PemohonDAO pemohonDAO;
    @Inject
    FasaPermohonanDAO fasaPermohonanDAO;
    @Inject
    PermohonanKertasDAO permohonanKertasDAO;
    @Inject
    PermohonanKertasKandunganDAO permohonanKertasKandunganDAO;
    @Inject
    PembangunanService pBangunService;
    @Inject
    KodDokumenDAO kodDokumenDAO;

    private PermohonanKertasKandungan kandunganK;
    private List<PermohonanKertas> senaraiKertas;
    private boolean btn = true;
    private List<HakmilikPermohonan> hakmilikPermohonanList;
    private String idPermohonan;
    private String persidangan;
    private String tarikhMesyuarat;
    private String masasidang;
    private String tempatsidang;
    private String kehadiran1;
    private String tidakHadir1;
    private String keterangan1;
    private String keadaan1;
    private String keputusan1;
    private Pengguna peng;
    private Permohonan permohonan;
    private SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    private SimpleDateFormat tdf = new SimpleDateFormat("hh:mm a");
    private DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy hh:mm a");
    private String taskId;
    private Task task = null;
    private BPelService service;
    private String stageId;
    private KodDokumen kd;    
    private List<PermohonanKertasKandungan> senaraiKandungan1;
    private List<PermohonanKertasKandungan> senaraiKandungan2;
    private List<PermohonanKertasKandungan> senaraiKandungan3;
    private List<PermohonanKertasKandungan> senaraiKandungan4;
    private List<PermohonanKertasKandungan> senaraiKandungan5;
    private int rowCount1;
    private int rowCount2;
    private int rowCount3;
    private int rowCount4;
    private int rowCount5;

    @DefaultHandler
    public Resolution showForm() {
        getContext().getRequest().setAttribute("edit", Boolean.TRUE);        
        return new JSP("pembangunan/pecahSempadan/dev_nota_siasatan.jsp").addParameter("tab", "true");
    }

    public Resolution showForm2() {     
        return new JSP("pembangunan/pecahSempadan/dev_nota_siasatan.jsp").addParameter("tab", "true");
    }    


    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {
        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        permohonan = permohonanDAO.findById(idPermohonan);
        rowCount1 = 1;
        rowCount2 = 1;
        rowCount3 = 1;
        rowCount4 = 1;
        rowCount5 = 1;

        service = new BPelService();
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = permohonanDAO.findById(idPermohonan);

        HakmilikPermohonan hp = new HakmilikPermohonan();
        if(!permohonan.getSenaraiHakmilik().isEmpty()){
            hp = permohonan.getSenaraiHakmilik().get(0);
        }

        hakmilikPermohonanList = permohonan.getSenaraiHakmilik();

        peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);


        String[] tname = {"permohonan"};
        Object[] model = {permohonan};

        List<Pemohon> listPemohon;
        listPemohon = pemohonDAO.findByEqualCriterias(tname, model, null);


//        taskId = (String) getContext().getRequest().getSession().getAttribute("taskId");
//        if (StringUtils.isBlank(taskId)) {
//            taskId = getContext().getRequest().getParameter("taskId");
//        }
//        task = service.getTaskFromBPel(taskId, peng);
//        if (task != null) {
//            stageId = task.getSystemAttributes().getStage();
//        } else {
//            stageId = getContext().getRequest().getParameter("stageId");
//        }

        String kodHakmilik="";
        String lotNama="";
        String BPM="";
        String daerah="";
        if (hp.getHakmilik().getKodHakmilik() != null) {
            kodHakmilik = hp.getHakmilik().getKodHakmilik().getKod();
        }
        if(hp.getHakmilik().getLot()!=null){
            lotNama = hp.getHakmilik().getLot().getNama();
        }
        if(hp.getHakmilik().getBandarPekanMukim()!=null){
            BPM = hp.getHakmilik().getBandarPekanMukim().getNama();
        }
        if(hp.getHakmilik().getDaerah()!=null){
           daerah =  hp.getHakmilik().getDaerah().getNama();
        }


        // testing
//        stageId = "sediajktl";
        PermohonanKertas kertasP = new PermohonanKertas();
        if (!(permohonan.getSenaraiKertas().isEmpty())) {
            
                kertasP = pBangunService.findKertasByKod(idPermohonan, "NSST");

            if(kertasP != null){
                if (kertasP.getTarikhSidang() != null) {
                    tarikhMesyuarat = sdf.format(kertasP.getTarikhSidang());
                    masasidang = tdf.format(kertasP.getTarikhSidang());
                }
                tempatsidang = kertasP.getTempatSidang();
                persidangan = kertasP.getTajuk();

                for (int j = 0; j < kertasP.getSenaraiKandungan().size(); j++) {

                    kandunganK = kertasP.getSenaraiKandungan().get(j);
                     if (kandunganK.getBil()== 1 && kandunganK.getSubtajuk().equals("1.1")) {
                        kehadiran1 = kandunganK.getKandungan();
                    } else if (kandunganK.getBil() == 2 && kandunganK.getSubtajuk().equals("2.1")) {
                        tidakHadir1 = kandunganK.getKandungan();
                    } else if (kandunganK.getBil() == 3 && kandunganK.getSubtajuk().equals("3.1")) {
                        keterangan1 = kandunganK.getKandungan();
                    } else if (kandunganK.getBil() == 4 && kandunganK.getSubtajuk().equals("4.1")) {
                        keadaan1 = kandunganK.getKandungan();
                    } else if (kandunganK.getBil() == 5 && kandunganK.getSubtajuk().equals("5.1")) {
                        keputusan1 = kandunganK.getKandungan();
                    }
                }


                senaraiKandungan1 = new ArrayList<PermohonanKertasKandungan>();
                senaraiKandungan2 = new ArrayList<PermohonanKertasKandungan>();
                senaraiKandungan3 = new ArrayList<PermohonanKertasKandungan>();
                senaraiKandungan4 = new ArrayList<PermohonanKertasKandungan>();
                senaraiKandungan5 = new ArrayList<PermohonanKertasKandungan>();
                senaraiKandungan1 = pBangunService.findKertasKandByIdKertas(kertasP.getIdKertas(), 1);
                senaraiKandungan2 = pBangunService.findKertasKandByIdKertas(kertasP.getIdKertas(), 2);
                senaraiKandungan3 = pBangunService.findKertasKandByIdKertas(kertasP.getIdKertas(), 3);
                senaraiKandungan4 = pBangunService.findKertasKandByIdKertas(kertasP.getIdKertas(), 4);
                senaraiKandungan5 = pBangunService.findKertasKandByIdKertas(kertasP.getIdKertas(), 5);
                rowCount1 = senaraiKandungan1.size();
                rowCount2 = senaraiKandungan2.size();
                rowCount3 = senaraiKandungan3.size();
                rowCount4 = senaraiKandungan4.size();
                rowCount5 = senaraiKandungan5.size();
            }
            if (rowCount1 == 0) {
            rowCount1 = 1;
        }
            
            if (rowCount2 == 0) {
            rowCount2 = 1;
        }

        if (rowCount3 == 0) {
            rowCount3 = 1;
        }

        if (rowCount4 == 0) {
            rowCount4 = 1;
        }

        if (rowCount5 == 0) {
            rowCount5 = 1;
        }
            
            else{
                persidangan = " SIASATAN "+permohonan.getKodUrusan().getNama()+" "+lotNama+" "+hp.getHakmilik().getNoLot().replaceAll("^0+", "")+
                              " "+kodHakmilik+" "+hp.getHakmilik().getNoHakmilik().replaceAll("^0+", "")+" "+BPM+" DAERAH "+daerah;

            }
        }else{
            persidangan = " SIASATAN "+permohonan.getKodUrusan().getNama()+" "+lotNama+" "+hp.getHakmilik().getNoLot().replaceAll("^0+", "")+
                           " " +kodHakmilik+" "+hp.getHakmilik().getNoHakmilik().replaceAll("^0+", "")+" "+BPM+" DAERAH "+daerah;
        }
    }

    public Resolution simpan() {
        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = permohonanDAO.findById(idPermohonan);
        Pengguna pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        InfoAudit infoAudit = new InfoAudit();

        PermohonanKertas permohonanKertas = new PermohonanKertas();
        kd = kodDokumenDAO.findById("NSST");

        if (kandunganK != null) {
            permohonanKertas = permohonanKertasDAO.findById(kandunganK.getKertas().getIdKertas());
            infoAudit = permohonanKertas.getInfoAudit();
            infoAudit.setDiKemaskiniOleh(pengguna);
            infoAudit.setTarikhKemaskini(new java.util.Date());

        } else {
            infoAudit.setDimasukOleh(pengguna);
            infoAudit.setTarikhMasuk(new java.util.Date());

        }

        
        if (tempatsidang == null || tempatsidang.equals("")) {
            tempatsidang = "TIADA DATA.";
        }
        
//        taskId = (String) getContext().getRequest().getSession().getAttribute("taskId");
//        if (StringUtils.isBlank(taskId)) {
//            taskId = getContext().getRequest().getParameter("taskId");
//        }
//        task = service.getTaskFromBPel(taskId, pengguna);
//        if (task != null) {
//            stageId = task.getSystemAttributes().getStage();
//        } else {
//            stageId = getContext().getRequest().getParameter("stageId");
//        }


        try {
            if (tarikhMesyuarat != null && masasidang != null) {
                Date tarikhsidang = (Date) formatter.parse(tarikhMesyuarat + " " + masasidang);
                permohonanKertas.setTarikhSidang(tarikhsidang);
            }
        } catch (Exception e) {
            LOG.info("Date Error1:" + e);
        }
        permohonanKertas.setTajuk(persidangan);
        permohonanKertas.setTempatSidang(tempatsidang);
        permohonanKertas.setInfoAudit(infoAudit);
        permohonanKertas.setCawangan(pengguna.getKodCawangan());
        permohonanKertas.setPermohonan(permohonan);
        permohonanKertas.setKodDokumen(kd);
        pBangunService.simpanPermohonanKertas(permohonanKertas);

        senaraiKandungan1 = pBangunService.findKertasKandByIdKertas(permohonanKertas.getIdKertas(),1);
        int kira1 = Integer.parseInt(getContext().getRequest().getParameter("rowCount1"));
        LOG.info("-----------count1------------:"+kira1);
        for (int i = 1; i <= kira1; i++) {
            InfoAudit iaP = new InfoAudit();
            PermohonanKertasKandungan permohonanKertasKandungan1=new PermohonanKertasKandungan();
            if (senaraiKandungan1.size() != 0 && i <= senaraiKandungan1.size()) {
                Long id = senaraiKandungan1.get(i - 1).getIdKandungan();
                if (id != null) {
                    permohonanKertasKandungan1 = permohonanKertasKandunganDAO.findById(id);
                    iaP = permohonanKertasKandungan1.getInfoAudit();
                    iaP.setTarikhKemaskini(new Date());
                    iaP.setDiKemaskiniOleh(pengguna);
                }
            } else {
                permohonanKertasKandungan1 = new PermohonanKertasKandungan();
                iaP.setTarikhMasuk(new Date());
                iaP.setDimasukOleh(pengguna);
            }
            permohonanKertasKandungan1.setKertas(permohonanKertas);
            permohonanKertasKandungan1.setBil(1);
            String kandungan = getContext().getRequest().getParameter("kehadiran" + i);
            if(kandungan == null || kandungan.equals("")){
                kandungan = "TIADA DATA.";
            }
            permohonanKertasKandungan1.setKandungan(kandungan);
            permohonanKertasKandungan1.setCawangan(permohonan.getCawangan());
            permohonanKertasKandungan1.setSubtajuk("1." + i);
            permohonanKertasKandungan1.setInfoAudit(iaP);
            pBangunService.simpanPermohonanKertasKandungan(permohonanKertasKandungan1);
        }

        senaraiKandungan2 = pBangunService.findKertasKandByIdKertas(permohonanKertas.getIdKertas(),2);
        int kira2 = Integer.parseInt(getContext().getRequest().getParameter("rowCount2"));
        LOG.info("-----------count2------------:"+kira2);
        for (int i = 1; i <= kira2; i++) {
            InfoAudit iaP = new InfoAudit();
            PermohonanKertasKandungan permohonanKertasKandungan1=new PermohonanKertasKandungan();
            if (senaraiKandungan2.size() != 0 && i <= senaraiKandungan2.size()) {
                Long id = senaraiKandungan2.get(i - 1).getIdKandungan();
                if (id != null) {
                    permohonanKertasKandungan1 = permohonanKertasKandunganDAO.findById(id);
                    iaP = permohonanKertasKandungan1.getInfoAudit();
                    iaP.setTarikhKemaskini(new Date());
                    iaP.setDiKemaskiniOleh(pengguna);
                }
            } else {
                permohonanKertasKandungan1 = new PermohonanKertasKandungan();
                iaP.setTarikhMasuk(new Date());
                iaP.setDimasukOleh(pengguna);
            }
            permohonanKertasKandungan1.setKertas(permohonanKertas);
            permohonanKertasKandungan1.setBil(2);
            String kandungan = getContext().getRequest().getParameter("tidakHadir" + i);
            if(kandungan == null || kandungan.equals("")){
                kandungan = "TIADA DATA.";
            }
            permohonanKertasKandungan1.setKandungan(kandungan);
            permohonanKertasKandungan1.setCawangan(permohonan.getCawangan());
            permohonanKertasKandungan1.setSubtajuk("2." + i);
            permohonanKertasKandungan1.setInfoAudit(iaP);
            pBangunService.simpanPermohonanKertasKandungan(permohonanKertasKandungan1);
        }


        senaraiKandungan3 = pBangunService.findKertasKandByIdKertas(permohonanKertas.getIdKertas(),3);
        int kira3 = Integer.parseInt(getContext().getRequest().getParameter("rowCount3"));
        LOG.info("-----------count3------------:"+kira3);
        for (int i = 1; i <= kira3; i++) {
            InfoAudit iaP = new InfoAudit();
            PermohonanKertasKandungan permohonanKertasKandungan1=new PermohonanKertasKandungan();
            if (senaraiKandungan3.size() != 0 && i <= senaraiKandungan3.size()) {
                Long id = senaraiKandungan3.get(i - 1).getIdKandungan();
                if (id != null) {
                    permohonanKertasKandungan1 = permohonanKertasKandunganDAO.findById(id);
                    iaP = permohonanKertasKandungan1.getInfoAudit();
                    iaP.setTarikhKemaskini(new Date());
                    iaP.setDiKemaskiniOleh(pengguna);
                }
            } else {
                permohonanKertasKandungan1 = new PermohonanKertasKandungan();
                iaP.setTarikhMasuk(new Date());
                iaP.setDimasukOleh(pengguna);
            }
            permohonanKertasKandungan1.setKertas(permohonanKertas);
            permohonanKertasKandungan1.setBil(3);
            String kandungan = getContext().getRequest().getParameter("keterangan" + i);
            if(kandungan == null || kandungan.equals("")){
                kandungan = "TIADA DATA.";
            }
            permohonanKertasKandungan1.setKandungan(kandungan);
            permohonanKertasKandungan1.setCawangan(permohonan.getCawangan());
            permohonanKertasKandungan1.setSubtajuk("3." + i);
            permohonanKertasKandungan1.setInfoAudit(iaP);
            pBangunService.simpanPermohonanKertasKandungan(permohonanKertasKandungan1);
        }


        senaraiKandungan4 = pBangunService.findKertasKandByIdKertas(permohonanKertas.getIdKertas(),4);
        int kira4 = Integer.parseInt(getContext().getRequest().getParameter("rowCount4"));
        LOG.info("-----------count4------------:"+kira4);
        for (int i = 1; i <= kira4; i++) {
            InfoAudit iaP = new InfoAudit();
            PermohonanKertasKandungan permohonanKertasKandungan1=new PermohonanKertasKandungan();
            if (senaraiKandungan4.size() != 0 && i <= senaraiKandungan4.size()) {
                Long id = senaraiKandungan4.get(i - 1).getIdKandungan();
                if (id != null) {
                    permohonanKertasKandungan1 = permohonanKertasKandunganDAO.findById(id);
                    iaP = permohonanKertasKandungan1.getInfoAudit();
                    iaP.setTarikhKemaskini(new Date());
                    iaP.setDiKemaskiniOleh(pengguna);
                }
            } else {
                permohonanKertasKandungan1 = new PermohonanKertasKandungan();
                iaP.setTarikhMasuk(new Date());
                iaP.setDimasukOleh(pengguna);
            }
            permohonanKertasKandungan1.setKertas(permohonanKertas);
            permohonanKertasKandungan1.setBil(4);
            String kandungan = getContext().getRequest().getParameter("keadaan" + i);
            if(kandungan == null || kandungan.equals("")){
                kandungan = "TIADA DATA.";
            }
            permohonanKertasKandungan1.setKandungan(kandungan);
            permohonanKertasKandungan1.setCawangan(permohonan.getCawangan());
            permohonanKertasKandungan1.setSubtajuk("4." + i);
            permohonanKertasKandungan1.setInfoAudit(iaP);
            pBangunService.simpanPermohonanKertasKandungan(permohonanKertasKandungan1);
        }

        senaraiKandungan5 = pBangunService.findKertasKandByIdKertas(permohonanKertas.getIdKertas(),5);
        int kira5 = Integer.parseInt(getContext().getRequest().getParameter("rowCount5"));
        LOG.info("-----------count5------------:"+kira5);
        for (int i = 1; i <= kira5; i++) {
            InfoAudit iaP = new InfoAudit();
            PermohonanKertasKandungan permohonanKertasKandungan1=new PermohonanKertasKandungan();
            if (senaraiKandungan5.size() != 0 && i <= senaraiKandungan5.size()) {
                Long id = senaraiKandungan5.get(i - 1).getIdKandungan();
                if (id != null) {
                    permohonanKertasKandungan1 = permohonanKertasKandunganDAO.findById(id);
                    iaP = permohonanKertasKandungan1.getInfoAudit();
                    iaP.setTarikhKemaskini(new Date());
                    iaP.setDiKemaskiniOleh(pengguna);
                }
            } else {
                permohonanKertasKandungan1 = new PermohonanKertasKandungan();
                iaP.setTarikhMasuk(new Date());
                iaP.setDimasukOleh(pengguna);
            }
            permohonanKertasKandungan1.setKertas(permohonanKertas);
            permohonanKertasKandungan1.setBil(5);
            String kandungan = getContext().getRequest().getParameter("keputusan" + i);
            if(kandungan == null || kandungan.equals("")){
                kandungan = "TIADA DATA.";
            }
            permohonanKertasKandungan1.setKandungan(kandungan);
            permohonanKertasKandungan1.setCawangan(permohonan.getCawangan());
            permohonanKertasKandungan1.setSubtajuk("5." + i);
            permohonanKertasKandungan1.setInfoAudit(iaP);
            pBangunService.simpanPermohonanKertasKandungan(permohonanKertasKandungan1);
        }


        rehydrate();
        addSimpleMessage("Maklumat telah berjaya disimpan.");
        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        return new ForwardResolution("/WEB-INF/jsp/pembangunan/pecahSempadan/dev_nota_siasatan.jsp").addParameter("tab", "true");
       
    }


    public Resolution deleteSingle() {
        String idKandungan = getContext().getRequest().getParameter("idKandungan");
        PermohonanKertasKandungan permohonanKertasKandungan1 = new PermohonanKertasKandungan();
        try {
            permohonanKertasKandungan1 = permohonanKertasKandunganDAO.findById(Long.parseLong(idKandungan));
        } catch (Exception e) {
            LOG.debug("Hapus empty record");
        }
        if (permohonanKertasKandungan1 != null) {
            pBangunService.deleteKertasKandungan(permohonanKertasKandungan1);
            LOG.debug("Record deleted Successfully...");
        }
        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        addSimpleMessage("Maklumat telah berjaya dihapus.");
        return new RedirectResolution(NotaSiasatanActionBean.class, "locate");
    }



    
    public String getTarikhMesyuarat() {
        return tarikhMesyuarat;
    }

    public void setTarikhMesyuarat(String tarikhMesyuarat) {
        this.tarikhMesyuarat = tarikhMesyuarat;
    }

    
    public PermohonanKertasKandungan getKandunganK() {
        return kandunganK;
    }

    public void setKandunganK(PermohonanKertasKandungan kandunganK) {
        this.kandunganK = kandunganK;
    }

    
    public List<PermohonanKertas> getSenaraiKertas() {
        return senaraiKertas;
    }

    public void setSenaraiKertas(List<PermohonanKertas> senaraiKertas) {
        this.senaraiKertas = senaraiKertas;
    }
    
    public Permohonan getPermohonan() {
        return permohonan;
    }

    public void setPermohonan(Permohonan permohonan) {
        this.permohonan = permohonan;
    }

    public boolean isBtn() {
        return btn;
    }

    public void setBtn(boolean btn) {
        this.btn = btn;
    }

    public List<HakmilikPermohonan> getHakmilikPermohonanList() {
        return hakmilikPermohonanList;
    }

    public void setHakmilikPermohonanList(List<HakmilikPermohonan> hakmilikPermohonanList) {
        this.hakmilikPermohonanList = hakmilikPermohonanList;
    }


    public Pengguna getPeng() {
        return peng;
    }

    public void setPeng(Pengguna peng) {
        this.peng = peng;
    }

    public KodDokumen getKd() {
        return kd;
    }

    public void setKd(KodDokumen kd) {
        this.kd = kd;
    }


    public BPelService getService() {
        return service;
    }

    public void setService(BPelService service) {
        this.service = service;
    }

    public String getStageId() {
        return stageId;
    }

    public void setStageId(String stageId) {
        this.stageId = stageId;
    }

    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public int getRowCount5() {
        return rowCount5;
    }

    public void setRowCount5(int rowCount5) {
        this.rowCount5 = rowCount5;
    }

    public List<PermohonanKertasKandungan> getSenaraiKandungan5() {
        return senaraiKandungan5;
    }

    public void setSenaraiKandungan5(List<PermohonanKertasKandungan> senaraiKandungan5) {
        this.senaraiKandungan5 = senaraiKandungan5;
    }

    public String getKeadaan1() {
        return keadaan1;
    }

    public void setKeadaan1(String keadaan1) {
        this.keadaan1 = keadaan1;
    }

    public String getKehadiran1() {
        return kehadiran1;
    }

    public void setKehadiran1(String kehadiran1) {
        this.kehadiran1 = kehadiran1;
    }

    public String getKeputusan1() {
        return keputusan1;
    }

    public void setKeputusan1(String keputusan1) {
        this.keputusan1 = keputusan1;
    }

    public String getKeterangan1() {
        return keterangan1;
    }

    public void setKeterangan1(String keterangan1) {
        this.keterangan1 = keterangan1;
    }

    public String getMasasidang() {
        return masasidang;
    }

    public void setMasasidang(String masasidang) {
        this.masasidang = masasidang;
    }

    public String getPersidangan() {
        return persidangan;
    }

    public void setPersidangan(String persidangan) {
        this.persidangan = persidangan;
    }

    public int getRowCount1() {
        return rowCount1;
    }

    public void setRowCount1(int rowCount1) {
        this.rowCount1 = rowCount1;
    }

    public int getRowCount2() {
        return rowCount2;
    }

    public void setRowCount2(int rowCount2) {
        this.rowCount2 = rowCount2;
    }

    public int getRowCount3() {
        return rowCount3;
    }

    public void setRowCount3(int rowCount3) {
        this.rowCount3 = rowCount3;
    }

    public int getRowCount4() {
        return rowCount4;
    }

    public void setRowCount4(int rowCount4) {
        this.rowCount4 = rowCount4;
    }

    public List<PermohonanKertasKandungan> getSenaraiKandungan1() {
        return senaraiKandungan1;
    }

    public void setSenaraiKandungan1(List<PermohonanKertasKandungan> senaraiKandungan1) {
        this.senaraiKandungan1 = senaraiKandungan1;
    }

    public List<PermohonanKertasKandungan> getSenaraiKandungan2() {
        return senaraiKandungan2;
    }

    public void setSenaraiKandungan2(List<PermohonanKertasKandungan> senaraiKandungan2) {
        this.senaraiKandungan2 = senaraiKandungan2;
    }

    public List<PermohonanKertasKandungan> getSenaraiKandungan3() {
        return senaraiKandungan3;
    }

    public void setSenaraiKandungan3(List<PermohonanKertasKandungan> senaraiKandungan3) {
        this.senaraiKandungan3 = senaraiKandungan3;
    }

    public List<PermohonanKertasKandungan> getSenaraiKandungan4() {
        return senaraiKandungan4;
    }

    public void setSenaraiKandungan4(List<PermohonanKertasKandungan> senaraiKandungan4) {
        this.senaraiKandungan4 = senaraiKandungan4;
    }

    public String getTidakHadir1() {
        return tidakHadir1;
    }

    public void setTidakHadir1(String tidakHadir1) {
        this.tidakHadir1 = tidakHadir1;
    }

    public String getTempatsidang() {
        return tempatsidang;
    }

    public void setTempatsidang(String tempatsidang) {
        this.tempatsidang = tempatsidang;
    }

    public String getIdPermohonan() {
        return idPermohonan;
    }

    public void setIdPermohonan(String idPermohonan) {
        this.idPermohonan = idPermohonan;
    }


}
