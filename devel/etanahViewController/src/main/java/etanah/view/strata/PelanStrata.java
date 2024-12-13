/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.strata;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.dao.HakmilikAsalDAO;
import etanah.dao.HakmilikSebelumDAO;
import etanah.dao.HakmilikDAO;
import etanah.dao.HakmilikPihakBerkepentinganDAO;
import etanah.dao.PermohonanDAO;
import etanah.dao.SejarahHakmilikDAO;
import etanah.model.Hakmilik;
import etanah.model.HakmilikAsal;
import etanah.model.HakmilikPermohonan;
import etanah.model.HakmilikSebelum;
import etanah.model.SejarahHakmilik;
import etanah.model.Pengguna;
import etanah.model.InfoAudit;
import etanah.view.etanahActionBeanContext;
//import etanah.model.HakmilikPermohonan;
import etanah.model.HakmilikPihakBerkepentingan;
import etanah.model.KodCawangan;
import etanah.model.Permohonan;
import etanah.model.Pihak;
import java.text.ParseException;
import java.util.List;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;

import etanah.service.RegService;
import etanah.service.SyerValidationService;
import etanah.view.stripes.common.MaklumatHakmilikPermohonanActionBean;
import java.util.ArrayList;
import java.util.Collections;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.HttpCache;
import net.sourceforge.stripes.action.RedirectResolution;
import net.sourceforge.stripes.action.StreamingResolution;
import org.apache.commons.math.fraction.Fraction;
import etanah.model.KodSeksyen;
import etanah.model.KodHakmilik;
import etanah.model.KodLot;
import etanah.model.KodKategoriTanah;
import etanah.model.KodUOM;
import etanah.model.KodDaerah;
import etanah.model.HakmilikPermohonan;
import etanah.model.HakmilikWaris;
import etanah.sequence.GeneratorIdHakmilik;
import java.util.Date;
import etanah.model.HakmilikAsalPermohonan;
import etanah.model.HakmilikSebelumPermohonan;
import etanah.model.HakmilikPermohonan;
import etanah.dao.HakmilikPermohonanDAO;
import etanah.dao.HakmilikAsalPermohonanDAO;
import etanah.dao.HakmilikSebelumPermohonanDAO;
import org.apache.log4j.Logger;
import org.apache.commons.lang.StringUtils;
import etanah.model.KodStatusHakmilik;
import etanah.model.PermohonanPihak;
import etanah.service.common.PermohonanPihakService;
import etanah.service.common.PemohonService;
import etanah.model.Pemohon;
import etanah.model.KodKadarCukai;
import java.math.BigDecimal;
import etanah.model.KodBandarPekanMukim;
import etanah.model.KodKegunaanTanah;
import etanah.dao.KodBandarPekanMukimDAO;
import etanah.dao.KodSeksyenDAO;
import etanah.Configuration;
import etanah.dao.BadanPengurusanDAO;
import etanah.dao.HakmilikUrusanDAO;
import java.math.MathContext;
import java.math.RoundingMode;
import etanah.dao.KodNegeriDAO;
import etanah.model.KodNegeri;
import etanah.model.HakmilikUrusan;
import etanah.model.HakmilikPihakBerkepentingan;
import etanah.service.common.HakmilikUrusanService;
import etanah.service.common.HakmilikPihakKepentinganService;
import etanah.dao.KodBandarPekanMukimDAO;
import org.hibernate.Transaction;
import org.hibernate.Session;
import etanah.dao.KodDaerahDAO;
import etanah.dao.gis.PelanGISDAO;
import etanah.model.gis.PelanGIS;
import etanah.service.common.PermohonanPihakService;
import etanah.dao.KodKegunaanTanahDAO;
import etanah.dao.KodUOMDAO;
import etanah.model.Akaun;
import etanah.model.KodAkaun;
import etanah.sequence.GeneratorNoAkaun;
import etanah.dao.KodBandarPekanMukimDAO;
import etanah.model.KodPBT;
import etanah.model.PermohonanRujukanLuar;
import etanah.service.CalcTax;
import etanah.view.ListUtil;
import java.text.SimpleDateFormat;
import etanah.dao.HakmilikUrusanDAO;
import etanah.dao.HakmilikWarisDAO;
import etanah.dao.KodJenisPengenalanDAO;
import etanah.dao.WakilPihakDAO;
import etanah.model.KodJenisPengenalan;
import etanah.model.KodSekatanKepentingan;
import etanah.model.KodTuntut;
import etanah.model.PermohonanTuntutanKos;
import etanah.model.PihakCawangan;
import etanah.model.WakilPihak;
import etanah.model.strata.BadanPengurusan;
import etanah.service.PembangunanService;
import etanah.service.PengambilanService;
import etanah.service.StrataPtService;
import etanah.service.common.HakmilikWarisService;
import etanah.service.common.LelongService;
import etanah.service.common.PermohonanRujukanLuarService;
import etanah.service.daftar.PembetulanService;
import etanah.service.daftar.PendaftaranSuratKuasaService;
import java.math.BigInteger;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import java.io.FileNotFoundException;
import com.google.inject.Inject;
import etanah.dao.*;
import etanah.model.*;
import etanah.model.strata.*;
import etanah.service.*;
import etanah.util.FileUtil;
import java.io.IOException;
import java.text.ParseException;
import javax.xml.parsers.ParserConfigurationException;
import net.sourceforge.stripes.action.UrlBinding;
import etanah.view.BasicTabActionBean;
import etanah.view.etanahActionBeanContext;
import etanah.view.uam.MailService;
import etanah.view.utility.JupemInIntegration;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.logging.Level;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import net.sourceforge.stripes.action.*;
import net.sourceforge.stripes.controller.LifecycleStage;
import org.apache.log4j.Logger;
import org.w3c.dom.*;
import org.xml.sax.SAXException;
import org.apache.commons.lang.StringUtils;
import oracle.bpel.services.workflow.task.model.Task;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.util.CollectionUtils;

/**
 *
 * @author muddmazani
 */
@UrlBinding("/strata/pelan")
public class PelanStrata extends AbleActionBean {

    @Inject
    HakmilikPermohonanDAO hakmilikPermohonanDAO;
    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;
    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    KodAgensiDAO kodAgensiDAO;
    @Inject
    StrataPtService strService;
    private FileBean file;
    private String idHakmilik;
    private String Profile_PIC;
    private String idPermohonan;
    private Pengguna pguna;
    private HakmilikPermohonan mohonHakmilik;
    private Permohonan permohonan;
    private KodAgensi kodAgensi;
    private List<KodAgensi> agensi = new ArrayList<KodAgensi>();
    private List<String> senaraiPelanString = new ArrayList();
    private List<String> senaraiPelanString2 = new ArrayList();
    private List<String> senaraiPelanUpload = new ArrayList();
    @Inject
    private etanah.Configuration conf;
    private static final Logger LOG = Logger.getLogger(PelanStrata.class);

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {
        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = permohonanDAO.findById(idPermohonan);

        List<String> senaraiPelan = strService.findNoPelanMohonBngn(idPermohonan);
        List<String> senaraiPelan2 = strService.findNoPelanBngnPetakAksr(idPermohonan);

        senaraiPelanString = new ArrayList();

        senaraiPelan.removeAll(senaraiPelan2);
        LOG.info(senaraiPelan);
        senaraiPelanString.addAll(senaraiPelan);
        senaraiPelanString.addAll(senaraiPelan2);
        if (senaraiPelanString.size() > 0) {
            Collections.sort(senaraiPelanString);
        }

        for (int i = 0; i < senaraiPelanString.size(); i++) {
            viewPelan(senaraiPelanString.get(i).toString());
        }
    }

    public void viewPelan(String nopelan) {
        LOG.info("MASUK VIEW");

        LOG.info("nopelan" + nopelan);

        String docPath = conf.getPelanPath() + "B4/PA(B)/PA(B)" + nopelan + ".TIF";

        LOG.info(docPath);
        InputStream fis = null;
        File f = new File(docPath);

        boolean flag = false;

        getContext().getResponse().setContentType("application/octet-stream");

        String filename = null;

        File viewFile = new File(docPath);
        FileInputStream fis2 = null;
        if (!viewFile.exists()) {
            senaraiPelanUpload.add(nopelan);
        }
    }

    @DefaultHandler
    public Resolution showForm1() {
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = permohonanDAO.findById(idPermohonan);
        agensi = strService.getKodAgensi("STR", null);

        return new JSP("strata/pbbm/pelanStrata.jsp").addParameter("tab", "true");
    }

    public Resolution mohonJupem() {
        getContext().getRequest().setAttribute("jupem", Boolean.TRUE);
        return new JSP("strata/pbbm/pelanStrata.jsp").addParameter("tab", "true");
    }

    public Resolution terimaPelan() {
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        getContext().getRequest().setAttribute("pelan", Boolean.TRUE);
//        return new JSP("daftar/pelanStrata.jsp").addParameter("tab", "true");
        return new JSP("strata/pbbm/pelanStrata.jsp").addParameter("tab", "true");
    }
    
    public Resolution uploadPelan() {
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        getContext().getRequest().setAttribute("pelan", Boolean.TRUE);
        return new JSP("strata/Ruang_Udara/muatnaikPelan.jsp").addParameter("popup", "true");
    }
    
    public Resolution refresh() {
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        getContext().getRequest().setAttribute("pelan", Boolean.TRUE);
//        return new JSP("daftar/pelanStrata.jsp").addParameter("tab", "true");
        return new JSP("strata/pbbm/pelanStrata.jsp").addParameter("tab", "true");
    }

    public Resolution simpanPelan() throws IOException, Exception {

       String path6 = (String) getContext().getRequest().getSession().getAttribute("pelan");
        
        String path = (String) getContext().getRequest().getParameter("file");
        String path5 = (String) getContext().getRequest().getParameter("pelan");
        String path2 = (String) getContext().getRequest().getParameter("test");
        String path3 = (String) getContext().getRequest().getParameter("Profile_PIC");
        String path4 = (String) getContext().getRequest().getParameter("FIP_PIC");

        File newFile = new File(path5);
        InputStream is = new FileInputStream(newFile);
        newFile.getName();
        LOG.info(newFile.getName());
         LOG.info("PA(B)" + newFile.getName() + ".TIF");
        String loc = conf.getPelanPath() + "B4" + File.separator + "PA(B)";
//        LOG.info(file.getFileName().substring(0, file.getFileName().lastIndexOf('.')));
        
//         LOG.info("PA(B)" + file.getFileName().substring(0, file.getFileName().lastIndexOf('.')) + ".TIF");
         
//        LOG.info(file.getInputStream());
        FileUtil fileUtil = new FileUtil(loc);
        fileUtil.saveFile(newFile.getName(), is);

        addSimpleMessage(newFile.getName() + " Berjaya dimuat naik.");
//        return new RedirectResolution(PelanStrata.class);
        return new RedirectResolution(PelanStrata.class
        ).addParameter("terimaPelan");

    }
    
    public Resolution muatNaik() throws Exception {
       
        Session s = sessionProvider.get();
        Transaction tx = s.beginTransaction();

        String loc = conf.getPelanPath() + File.separator + "B4" + File.separator + "PA(B)";

        FileUtil fileUtil = new FileUtil(loc);
        fileUtil.saveFile("PA(B)" + file.getFileName().substring(0, file.getFileName().lastIndexOf('.')) + ".TIF", file.getInputStream());
        tx.commit();
        addSimpleMessage("Pelan Berjaya di muat naik");
        rehydrate();
        return new JSP("strata/Ruang_Udara/muatnaikPelan.jsp").addParameter("popup", "true");

    }
    
    
    public void smpnPelan() throws IOException, Exception {
        LOG.info("MASUK VIEW");

        Session s = sessionProvider.get();
        Transaction tx = s.beginTransaction();

        String loc = conf.getPelanPath() + File.separator + "B4" + File.separator + "PA(B)";

        FileUtil fileUtil = new FileUtil(loc);
        fileUtil.saveFile("PA(B)" + file.getFileName().substring(0, file.getFileName().lastIndexOf('.')) + ".TIF", file.getInputStream());
        tx.commit();
    }

    @HandlesEvent("save")
    public void saveOrUpdate() throws IOException, Exception {

        String loc = conf.getPelanPath() + "B4" + File.separator + "PA(B)";
        LOG.info(file.getFileName().substring(0, file.getFileName().lastIndexOf('.')));
        LOG.info(file.getInputStream());
        FileUtil fileUtil = new FileUtil(loc);
        fileUtil.saveFile("PA(B)" + file.getFileName().substring(0, file.getFileName().lastIndexOf('.')) + ".TIF", file.getInputStream());
        addSimpleMessage("Pelan PA(B)" + file.getFileName().substring(0, file.getFileName().lastIndexOf('.')) + " Berjaya dimuat naik.");
        terimaPelan();
        
    }

    public String getIdHakmilik() {
        return idHakmilik;
    }

    public void setIdHakmilik(String idHakmilik) {
        this.idHakmilik = idHakmilik;
    }

    public HakmilikPermohonan getMohonHakmilik() {
        return mohonHakmilik;
    }

    public void setMohonHakmilik(HakmilikPermohonan mohonHakmilik) {
        this.mohonHakmilik = mohonHakmilik;
    }

    public String getIdPermohonan() {
        return idPermohonan;
    }

    public void setIdPermohonan(String idPermohonan) {
        this.idPermohonan = idPermohonan;
    }

    public Pengguna getPguna() {
        return pguna;
    }

    public void setPguna(Pengguna pguna) {
        this.pguna = pguna;
    }

    public List<KodAgensi> getAgensi() {
        return agensi;
    }

    public void setAgensi(List<KodAgensi> agensi) {
        this.agensi = agensi;
    }

    public KodAgensi getKodAgensi() {
        return kodAgensi;
    }

    public void setKodAgensi(KodAgensi kodAgensi) {
        this.kodAgensi = kodAgensi;
    }

    public Permohonan getPermohonan() {
        return permohonan;
    }

    public void setPermohonan(Permohonan permohonan) {
        this.permohonan = permohonan;
    }

    public FileBean getFile() {
        return file;
    }

    public void setFile(FileBean file) {
        this.file = file;
    }

    public List<String> getSenaraiPelanString() {
        return senaraiPelanString;
    }

    public void setSenaraiPelanString(List<String> senaraiPelanString) {
        this.senaraiPelanString = senaraiPelanString;
    }

    public List<String> getSenaraiPelanUpload() {
        return senaraiPelanUpload;
    }

    public void setSenaraiPelanUpload(List<String> senaraiPelanUpload) {
        this.senaraiPelanUpload = senaraiPelanUpload;
    }

    public List<String> getSenaraiPelanString2() {
        return senaraiPelanString2;
    }

    public void setSenaraiPelanString2(List<String> senaraiPelanString2) {
        this.senaraiPelanString2 = senaraiPelanString2;
    }

    public String getProfile_PIC() {
        return Profile_PIC;
    }

    public void setProfile_PIC(String Profile_PIC) {
        this.Profile_PIC = Profile_PIC;
    }
    

}
