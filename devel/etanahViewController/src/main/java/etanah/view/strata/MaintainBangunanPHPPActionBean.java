/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.strata;

import able.stripes.JSP;
import java.io.FileNotFoundException;
import com.google.inject.Inject;
import etanah.dao.BangunanPetaAksesoriDAO;
import etanah.dao.BangunanPetakDAO;
import etanah.dao.BangunanTingkatDAO;
import etanah.dao.HakmilikPermohonanDAO;
import etanah.dao.KodHartaBersamaDAO;
import etanah.dao.KodKategoriBangunanDAO;
import etanah.dao.KodKegunaanBangunanDAO;
import etanah.dao.KodKegunaanPetakAksesoriDAO;
import etanah.dao.KodKegunaanPetakDAO;
import etanah.dao.KodNegeriDAO;
import etanah.dao.KodTujuanUkurDAO;
import etanah.dao.KodUOMDAO;
import etanah.dao.PermohonanBangunanDAO;
import etanah.dao.PermohonanDAO;
import etanah.dao.PermohonanHartaBersamaDAO;
import etanah.dao.PermohonanPihakDAO;
import etanah.dao.PermohonanSkimDAO;
import etanah.dao.PihakDAO;
import etanah.model.BangunanPetak;
import etanah.model.BangunanPetakAksesori;
import etanah.model.BangunanTingkat;
import etanah.model.Dokumen;
import etanah.model.HakmilikPermohonan;
import etanah.model.InfoAudit;
import etanah.model.KodHartaBersama;
import etanah.model.KodKategoriBangunan;
import etanah.model.KodKegunaanBangunan;
import etanah.model.KodKegunaanPetak;
import etanah.model.KodKegunaanPetakAksesori;
import etanah.model.KodNegeri;
import etanah.model.KodTujuanUkur;
import etanah.model.KodUOM;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.model.PermohonanBangunan;
import etanah.model.PermohonanHartaBersama;
import etanah.model.PermohonanSkim;
import etanah.model.PermohonanStrata;
import etanah.model.Pihak;
import etanah.model.strata.BadanPengurusan;
import etanah.service.BPelService;
import etanah.service.StrataPtService;
import java.io.IOException;
import java.text.ParseException;
import javax.xml.parsers.ParserConfigurationException;
import net.sourceforge.stripes.action.UrlBinding;
import etanah.view.BasicTabActionBean;
import etanah.view.etanahActionBeanContext;
import etanah.view.utility.JupemInIntegration;
import java.io.File;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ErrorResolution;
import net.sourceforge.stripes.action.RedirectResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.controller.LifecycleStage;
import org.apache.log4j.Logger;
import org.w3c.dom.*;
import org.xml.sax.SAXException;
import org.apache.commons.lang.StringUtils;
import oracle.bpel.services.workflow.task.model.Task;

/**
 *
 * @author tengku rafidah
 */
@UrlBinding("/strata/ekstrakPHPP")
public class MaintainBangunanPHPPActionBean extends BasicTabActionBean {

    @Inject
    KodTujuanUkurDAO kodTujuanUkurDAO;
    @Inject
    JupemInIntegration jum;
    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    PihakDAO pihakDAO;
    @Inject
    PermohonanPihakDAO permohonanPihakDAO;
    @Inject
    BangunanTingkatDAO bangunanTingkatDAO;
    @Inject
    BangunanPetakDAO bangunanPetakDAO;
    @Inject
    KodKegunaanPetakDAO kodKegunaanPetakDAO;
    @Inject
    BangunanPetaAksesoriDAO bangunanPetakAksesoriDAO;
    @Inject
    KodKegunaanPetakAksesoriDAO kodKegunaanPetakAksesoriDAO;
    @Inject
    HakmilikPermohonanDAO hakmilikPermohonanDAO;
    @Inject
    KodKategoriBangunanDAO kodKategoriBangunanDAO;
    @Inject
    KodNegeriDAO kodNegeriDAO;
    @Inject
    KodHartaBersamaDAO kodHartaBersamaDAO;
    @Inject
    PermohonanHartaBersamaDAO permohonanHartaBersamaDAO;
    private PermohonanBangunan bangunan;
    private Permohonan permohonan;
    private HakmilikPermohonan hakmilikpermohonan;
    @Inject
    PermohonanBangunanDAO permohonanBangunanDAO;
    @Inject
    StrataPtService strService;
    @Inject
    private etanah.Configuration conf;
    @Inject
    KodUOMDAO kodUOMDAO;
    @Inject
    PermohonanSkimDAO permohonanSkimDAO;
    @Inject
    BPelService service;
    @Inject
    KodKegunaanBangunanDAO kodKegunaanBangunanDAO;
    private static Document doc = null;
    private List<BangunanPetak> petakL;
    private List<BangunanTingkat> tingkatL;
    private List<PermohonanBangunan> pBangunanL;
    private List<BangunanPetakAksesori> petakAksesoriL;
    private List<KodKegunaanPetak> kGunaPetakL;
    private List<KodKegunaanPetakAksesori> kGunaPetakAksesoriL;
    private BangunanTingkat bangunanTingkat;
    private BangunanPetak bangunanPetak;
    private List<BangunanPetak> senaraiPetak;
    private List<BangunanPetakAksesori> senaraipetakAksesori;
    private List<BangunanTingkat> senaraiTingkat;
    private String[] petakLuas;
    private String[] petakSyer;
    private String[] petakKegunaan;
    private String[] petakKegunaanAksr;
    private String[] lokasiAksr;
    private String[] tingkatMezanin;
    private static final Logger LOG = Logger.getLogger(MaintainBangunanPHPPActionBean.class);
    private String namaBangunan;
    private String tingkat;
    private String idTingkat;
    private String idBangunan;
    private String idPetak;
    private int bilanganPetak;
    private int bilanganPetakAksesori;
    private String lokasiPetakAksesori;
    private BangunanPetak ptk;
    private int sum = 0;
    private int sum2 = 0;
    private int hv = 0;
    private int sum4 = 0;
    int jumlah_ptk_akrs = 0;
    private String luas;
    private String syer;
    private KodKegunaanPetak kegunaan;
    private KodKegunaanPetakAksesori kodkegunaan;
    private String idPermohonan;
    private List<HakmilikPermohonan> listHakPermohon;
    private String tempIdBangunan;
    private String tempIdTingkat;
    private String tempIdPetak;
    private int jumPetak = 0;
    private BadanPengurusan mc;
    private List<PermohonanSkim> listMohonSkim;
    private PermohonanSkim mohonSkim;
    private BadanPengurusan badanUrus;
    private PermohonanStrata permohonanstrata;
    private HakmilikPermohonan HakPermohon;
    private String stageId;
    private String taskId;
    private List<PermohonanHartaBersama> listpermohonanHartaBersama;
    private String kodNegeri;
    private String kodurusan;
    private int petakIndex = 0;
    private int currNoOfPetak = 0;
    private int prevNoOfPetak = 0;
    private int petakAksIndex = 0;
    private int currNoOfPetakAks = 0;
    private int prevNoOfPetakAks = 0;
    

    public Resolution selectedPitak() {

        tempIdBangunan = getContext().getRequest().getParameter("idBangunan");
        tempIdTingkat = getContext().getRequest().getParameter("idTingkat");
        tempIdPetak = getContext().getRequest().getParameter("idPetak");

        LOG.info("----------selectedPitak---------" + tempIdBangunan);
        LOG.info("----------selectedPitak---------" + tempIdTingkat);
        LOG.info("----------selectedPitak---------" + tempIdPetak);
        try {
            showForm1();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new JSP("strata/pbbm/jadualpetakPHPP.jsp").addParameter("tab", "true");
    }

    public Resolution selectedTingkat() {
        tempIdBangunan = getContext().getRequest().getParameter("idBangunan");
        tempIdTingkat = getContext().getRequest().getParameter("idTingkat");

        LOG.info("----------selectedTingkat---------" + tempIdBangunan);
        LOG.info("----------selectedTingkat---------" + tempIdTingkat);
        try {
            showForm1();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new JSP("strata/pbbm/jadualpetakPHPP.jsp").addParameter("tab", "true");
    }

    public Resolution selectedBangunan() {
        tempIdBangunan = getContext().getRequest().getParameter("idBangunan");
        String ekstrak = getContext().getRequest().getParameter("ekstrak");
        LOG.info("----------tempIdBangunan---------" + tempIdBangunan);
        LOG.info("----------ekstrak---------" + ekstrak);
        try {
            showForm1();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new JSP("strata/pbbm/jadualpetakPHPP.jsp").addParameter("tab", "true");
    }

    @DefaultHandler
    public Resolution showForm1() throws ParserConfigurationException, SAXException, IOException {
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        String msg = getContext().getRequest().getParameter("message");
        String error = getContext().getRequest().getParameter("error");
        if (StringUtils.isNotBlank(msg)) {
            addSimpleMessage(msg);
        }
        if (StringUtils.isNotBlank(error)) {
            addSimpleError(error);
        }

        // added by zadirul
        if (permohonan.getKodUrusan().getKod().equals("RMHS1")) {
            getContext().getRequest().setAttribute("RMHS", Boolean.TRUE);
        }

        // class to compare the name of Petak & sort accordingly
        Comparator c = new Comparator<BangunanPetak>() {
            @Override
            public int compare(BangunanPetak o1, BangunanPetak o2) {
                return Integer.parseInt(o1.getNama())
                        - Integer.parseInt(o1.getNama());
            }
        };

        Comparator c2 = new Comparator<BangunanTingkat>() {
            @Override
            public int compare(BangunanTingkat u1, BangunanTingkat u2) {
                return (u1.getTingkat() - u2.getTingkat());
            }
        };

        Comparator c3 = new Comparator<BangunanPetakAksesori>() {
            @Override
            public int compare(BangunanPetakAksesori p1, BangunanPetakAksesori p2) {
                String namaAksr1Pecahan = p1.getNama();
                String namaAksr2Pecahan = p2.getNama();
                return Integer.parseInt(namaAksr1Pecahan)
                        - Integer.parseInt(namaAksr2Pecahan);
            }
        };

        Comparator c4 = new Comparator<PermohonanBangunan>() {
            @Override
            public int compare(PermohonanBangunan b1, PermohonanBangunan b2) {
                String namaBngn1Pecahan = b1.getNama().length() > 1 ? b1.getNama().substring(1) : "0";
                LOG.info("---namaBngn1Pecahan---" + namaBngn1Pecahan);
                String namaBngn2Pecahan = b2.getNama().length() > 1 ? b2.getNama().substring(1) : "0";
                LOG.info("---namaBngn2Pecahan---" + namaBngn2Pecahan);
                return Integer.parseInt(namaBngn1Pecahan)
                        - Integer.parseInt(namaBngn2Pecahan);
            }
        };

        if (pBangunanL != null) {
            permohonan = permohonanDAO.findById(idPermohonan);
            LOG.info("---permohonan---" + permohonan.getIdPermohonan());
            Collections.sort(pBangunanL, c4);
            for (PermohonanBangunan p : pBangunanL) {
                permohonan.setSenaraiBangunan(pBangunanL);
                if (p.getSenaraiTingkat() != null) {
                    List<BangunanTingkat> listTingkat = p.getSenaraiTingkat();
                    Collections.sort(listTingkat, c2);
                    p.setSenaraiTingkat(listTingkat);
                    for (BangunanTingkat tgkt : p.getSenaraiTingkat()) {
                        List<BangunanPetak> listPetak = strService.getSenaraiPetak(tgkt.getIdTingkat());
                        Collections.sort(listPetak, c);
                        tgkt.setSenaraiPetak(listPetak);
                        for (BangunanPetak ptk1 : tgkt.getSenaraiPetak()) {
                            List<BangunanPetakAksesori> listPetakaksr = new ArrayList();
                            listPetakaksr = strService.findPtkAksrByIDPetak(ptk1.getIdPetak());
                            //LOG.info("--listPetakaksr--" + listPetakaksr);
                            petakL = strService.findByIDPetak(String.valueOf(ptk1.getIdPetak()));
                            Collections.sort(listPetakaksr, c3);
                            ptk1.setSenaraiPetakAksesori(listPetakaksr);
                        }
                    }
                }
            }
        }

        mc = strService.findBdn(idPermohonan);
        LOG.info("--mc--" + mc);

        if (!pBangunanL.isEmpty()) {
            if (pBangunanL.get(0) != null && pBangunanL.get(0).getPermohonanSkim() != null) {
            mohonSkim = strService.findSkimById(pBangunanL.get(0).getPermohonanSkim().getIdSkim());
            }
        }

        if (mohonSkim != null) {
            listpermohonanHartaBersama = strService.findHartaBersamaByidSkim(mohonSkim.getIdSkim());
            LOG.info("--listpermohonanHartaBersama--" + listpermohonanHartaBersama);
        }

        Dokumen d = strService.findDok(permohonan.getIdPermohonan(), "JPP");
        if (d != null) {
            String docPath = conf.getProperty("document.path");
            File f = new File(docPath + (docPath.endsWith(File.separator) ? "" : File.separator) + d.getNamaFizikal());
            LOG.info(f);
            LOG.info(d.getNamaFizikal());
            try {
                DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
                DocumentBuilder db = dbf.newDocumentBuilder();
                doc = db.parse(f);
            } catch (FileNotFoundException e) {
                return new JSP("strata/pbbm/jadualpetakPHPP.jsp").addParameter("tab", "true");
            }

            getContext().getRequest().setAttribute("ekstrak", Boolean.TRUE);
            return new JSP("strata/pbbm/jadualpetakPHPP.jsp").addParameter("tab", "true");
        } else {
            getContext().getRequest().setAttribute("ekstrak", Boolean.FALSE);
            if (permohonan.getPermohonanSebelum() != null) {
                return new JSP("strata/pbbm/jadualpetakPHPP.jsp").addParameter("tab", "true");
            } else {
                return new JSP("strata/pbbm/jadualpetakPHPP.jsp").addParameter("tab", "true");
            }
        }
    }

    public Resolution showForm2() {
        namaBangunan = getContext().getRequest().getParameter("namaBangunan");
        tingkat = getContext().getRequest().getParameter("tingkat");
        LOG.debug("Masuk");
        LOG.debug(namaBangunan + tingkat);

        return new JSP("strata/pbbm/tambah_maklumatpetak.jsp").addParameter("popup", "true");
    }

    public Resolution showForm3() {
        idPetak = getContext().getRequest().getParameter("idPetak");
        idBangunan = getContext().getRequest().getParameter("idBangunan");
        idTingkat = getContext().getRequest().getParameter("idTingkat");
        LOG.debug(idPetak + idBangunan + idTingkat);
        return new JSP("strata/pbbm/tambah_petakaksesori.jsp").addParameter("popup", "true");
    }

    public Resolution showForm4() {
        rehydrate();
        Comparator c = new Comparator<BangunanPetak>() {
            @Override
            public int compare(BangunanPetak o1, BangunanPetak o2) {
                return Integer.parseInt(o1.getNama())
                        - Integer.parseInt(o1.getNama());
            }
        };

        Comparator c2 = new Comparator<BangunanTingkat>() {
            @Override
            public int compare(BangunanTingkat u1, BangunanTingkat u2) {
                return (u1.getTingkat() - u2.getTingkat());
            }
        };

        Comparator c3 = new Comparator<BangunanPetakAksesori>() {
            @Override
            public int compare(BangunanPetakAksesori p1, BangunanPetakAksesori p2) {
                String namaAksr1Pecahan = p1.getNama().substring(1);
                String namaAksr2Pecahan = p2.getNama().substring(1);
                return Integer.parseInt(namaAksr1Pecahan)
                        - Integer.parseInt(namaAksr2Pecahan);
            }
        };

        Comparator c4 = new Comparator<PermohonanBangunan>() {
            @Override
            public int compare(PermohonanBangunan b1, PermohonanBangunan b2) {

                String namaBngn1Pecahan = b1.getNama();
                String namaBngn2Pecahan = b2.getNama();
                return Integer.parseInt(namaBngn1Pecahan)
                        - Integer.parseInt(namaBngn2Pecahan);
            }
        };

        if (pBangunanL != null) {
            permohonan = permohonanDAO.findById(idPermohonan);
            for (PermohonanBangunan p : pBangunanL) {
                permohonan.setSenaraiBangunan(pBangunanL);
                if (p.getSenaraiTingkat() != null) {
                    List<BangunanTingkat> listTingkat = p.getSenaraiTingkat();
                    p.setSenaraiTingkat(listTingkat);
                    for (BangunanTingkat tgkt : p.getSenaraiTingkat()) {
                        List<BangunanPetak> listPetak = strService.getSenaraiPetak(tgkt.getIdTingkat());
                        tgkt.setSenaraiPetak(listPetak);
                        for (BangunanPetak ptk1 : strService.getSenaraiPetak(tgkt.getIdTingkat())) {
                            List<BangunanPetakAksesori> listPetakaksr = new ArrayList();
                            listPetakaksr = ptk1.getSenaraiPetakAksesori();
                            petakL = strService.findByIDPetak(String.valueOf(ptk1.getIdPetak()));
                            ptk1.setSenaraiPetakAksesori(listPetakaksr);
                        }
                    }
                }
            }
        }
        getContext().getRequest().setAttribute("ekstrak", Boolean.FALSE);
        return new JSP("strata/pbbm/jadualpetakPHPP.jsp").addParameter("tab", "true");
    }

    public Resolution showPHPP() {
        return new JSP("strata/pecahPetak/tambahBangunan.jsp").addParameter("tab", "true");
    }

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {

        kodNegeri = conf.getProperty("kodNegeri");
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        if (idPermohonan != null) {
            permohonan = permohonanDAO.findById(idPermohonan);

            // added by zadirul : catter some function for urusan RMHS
            if (permohonan.getKodUrusan().getKod().equals("RMHS1")) {
                getContext().getRequest().setAttribute("RMHS", Boolean.TRUE);
            }

            pBangunanL = strService.findByIDPermohonan(permohonan.getIdPermohonan());
            kGunaPetakL = kodKegunaanPetakDAO.findAll();
            String[] tname = {"permohonan"};
            Object[] model = {permohonan};
            listHakPermohon = hakmilikPermohonanDAO.findByEqualCriterias(tname, model, null);
            kGunaPetakAksesoriL = kodKegunaanPetakAksesoriDAO.findAll();
            senaraiTingkat = new ArrayList<BangunanTingkat>();
            senaraiPetak = new ArrayList<BangunanPetak>();
            senaraipetakAksesori = new ArrayList<BangunanPetakAksesori>();
            List<PermohonanBangunan> pb = new ArrayList<PermohonanBangunan>();
            pb = strService.findByIDPermohonan(idPermohonan);
            for (PermohonanBangunan bngn : pb) {
                List<BangunanTingkat> t = bngn.getSenaraiTingkat();
                for (BangunanTingkat bt : t) {
                    senaraiTingkat.add(bt);
                    List<BangunanPetak> sb = bt.getSenaraiPetak();
                    for (BangunanPetak bp : sb) {
                        senaraiPetak.add(bp);
                    }
                }
            }

            if (pBangunanL.size() > 0) {
                if (senaraiTingkat.size() > 0) {
                    tingkatMezanin = new String[senaraiTingkat.size()];
                    for (int x = 0; x < senaraiTingkat.size(); x++) {
                        BangunanTingkat bt = senaraiTingkat.get(x);
                        tingkatMezanin[x] = String.valueOf(bt.getIdTingkat());
                        if (senaraiPetak.size() > 0) {
                            petakLuas = new String[senaraiPetak.size()];
                            petakSyer = new String[senaraiPetak.size()];
                            petakKegunaan = new String[senaraiPetak.size()];
                            for (int i = 0; i < senaraiPetak.size(); i++) {
                                BangunanPetak bp = senaraiPetak.get(i);
                                petakLuas[i] = String.valueOf(bp.getLuas());
                                petakSyer[i] = String.valueOf(bp.getSyer());
                                if (bp.getKegunaan() != null) {
                                    petakKegunaan[i] = String.valueOf(bp.getKegunaan().getNama());
                                }
                                if (senaraipetakAksesori.size() > 0) {
                                    petakKegunaanAksr = new String[senaraipetakAksesori.size()];
                                    lokasiAksr = new String[senaraipetakAksesori.size()];
                                    for (int a = 0; a < senaraipetakAksesori.size(); a++) {
                                        BangunanPetakAksesori pk1 = senaraipetakAksesori.get(a);
                                        petakKegunaanAksr[a] = String.valueOf(pk1.getKegunaan().getKod());
                                        lokasiAksr[a] = String.valueOf(pk1.getLokasi());
                                    }
                                }

                            }

                        }
                    }
                }
            }
        }
        stageId = getBPLStageId();
        LOG.info("-------------stageId--" + stageId);
        Permohonan permohonan2 = permohonanDAO.findById(idPermohonan);
        kodurusan = permohonan2.getKodUrusan().getKod();
        LOG.info("--kodurusan--:" + kodurusan);
    }

    public String getBPLStageId() {
        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        service = new BPelService();
        taskId = (String) getContext().getRequest().getSession().getAttribute("taskId");

        if (StringUtils.isNotBlank(taskId)) {
            Task t = null;
            t = service.getTaskFromBPel(taskId, peng);
            stageId = t.getSystemAttributes().getStage();
            LOG.info("-------------stageId--" + stageId);
        }
        return stageId;
    }

    public Resolution tambahBangunan() {
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");

        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        InfoAudit infoAudit = new InfoAudit();
        infoAudit.setDimasukOleh(peng);
        infoAudit.setTarikhMasuk(new java.util.Date());
        String error = "";
        String msg = "";

        String nama = bangunan.getNama();

        if (!nama.isEmpty()) {
            PermohonanBangunan pb = strService.findByNama(nama, idPermohonan);
            if ((pb != null)) {
                error = "Sila Masukkan Nama Bangunan yang berlainan.";
            } else {
                bangunan.setNama(nama);
                bangunan.setPermohonan(permohonan);
                bangunan.setInfoAudit(infoAudit);
                bangunan.setCawangan(peng.getKodCawangan());
                strService.simpanBangunan(bangunan);
                LOG.debug(bangunan.getNama());
                ArrayList<BangunanTingkat> listTingkat = new ArrayList<BangunanTingkat>();
                for (int i = 0; i < bangunan.getBilanganTingkat(); i++) {
                    LOG.debug("Bilangan Tingkat = " + i);
                    BangunanTingkat tgkt = new BangunanTingkat();
                    tgkt.setBangunan(bangunan);
                    tgkt.setNama(String.valueOf(i + 1));
                    tgkt.setTingkat(i + 1);
                    tgkt.setInfoAudit(infoAudit);
                    listTingkat.add(tgkt);
                    strService.simpanBangunanTingkat(tgkt);

                }
                bangunan.setSenaraiTingkat(listTingkat);
                strService.updateBangunan(bangunan);
                LOG.debug(bangunan.getIdBangunan());
                bangunan = permohonanBangunanDAO.findById(bangunan.getIdBangunan());
                LOG.debug(bangunan.getSenaraiTingkat().size());
                msg = "Maklumat telah berjaya disimpan.";
            }
        }

        return new RedirectResolution(MaintainBangunanPHPPActionBean.class, "showForm1").addParameter("error", error).addParameter("message", msg);

    }

    public Resolution tambahPetak() {

        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = permohonanDAO.findById(idPermohonan);
        LOG.info("ID:: " + idPermohonan);
        List<PermohonanBangunan> pb1 = permohonan.getSenaraiBangunan();
        for (PermohonanBangunan pb2 : pb1) {
            List<BangunanTingkat> bt1 = pb2.getSenaraiTingkat();
            LOG.info("BANGUNAN:: " + pb2.getNama());
            for (BangunanTingkat bt2 : bt1) {
                sum = sum + bt2.getBilanganPetak();
            }
        }

        idTingkat = getContext().getRequest().getParameter("idTingkat");
        BangunanTingkat bt3 = strService.findByPetak(idTingkat);
        sum2 = bt3.getBilanganPetak();
        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        InfoAudit infoAudit = new InfoAudit();
        infoAudit.setDimasukOleh(peng);
        infoAudit.setTarikhMasuk(new java.util.Date());
        infoAudit.setDiKemaskiniOleh(peng);
        infoAudit.setTarikhKemaskini(new java.util.Date());
        bangunanTingkat = bangunanTingkatDAO.findById(Long.parseLong(idTingkat));
        LOG.debug("id tingkat" + bangunanTingkat.getNama());
        bangunanTingkat.setInfoAudit(infoAudit);
        bangunanTingkat.setBilanganPetak(sum2 + bilanganPetak);

        strService.simpanBangunanTingkat(bangunanTingkat);
        LOG.info(sum);



        ArrayList<BangunanPetak> listPetak = new ArrayList<BangunanPetak>();
        for (int i = 0; i < bilanganPetak; i++) {
            InfoAudit infoAudit1 = new InfoAudit();
            infoAudit1.setDimasukOleh(peng);
            infoAudit1.setTarikhMasuk(new java.util.Date());
            LOG.debug("Bilangan Petak = " + i);
            BangunanPetak ptk = new BangunanPetak();
            ptk.setNama(String.valueOf(sum + i + 1));
            ptk.setInfoAudit(infoAudit1);
            ptk.setTingkat(bangunanTingkat);
            BigDecimal bd = new BigDecimal(luas);
            ptk.setLuas(bd);
            ptk.setSyer(Integer.parseInt(syer));
            ptk.setKegunaan(kegunaan);
            listPetak.add(ptk);
            LOG.debug(listPetak);
            strService.simpanPetak(ptk);
        }

        List<BangunanPetak> bgnP = bangunanTingkat.getSenaraiPetak();
        petakL = new LinkedList<BangunanPetak>();
        for (BangunanPetak bP : bgnP) {
            LOG.info("ID Bangunan Petak" + bP.getIdPetak());
            petakL.add(bP);
        }

        return new RedirectResolution(MaintainBangunanPHPPActionBean.class, "showForm1");
    }

    public Resolution deleteBngn() {

        idBangunan = getContext().getRequest().getParameter("idBangunan");

        if (idBangunan != null) {
            PermohonanBangunan bngn = permohonanBangunanDAO.findById(Long.parseLong(idBangunan));

            if (bngn != null) {
                strService.deleteBngn(bngn);
            }
        }
        rehydrate();
        return new RedirectResolution(MaintainBangunanPHPPActionBean.class, "showForm1");
    }

    public Resolution deletePetak() {

        String idPetak = getContext().getRequest().getParameter("idPetak");

        if (idPetak != null) {
            BangunanPetak petak = bangunanPetakDAO.findById(Long.parseLong(idPetak));
            BangunanTingkat tgkt = bangunanTingkatDAO.findById(petak.getTingkat().getIdTingkat());


            if (petak != null) {
                strService.deletePtkAksr(petak.getIdPetak());
                strService.deletePetak(petak);
                tgkt.getBilanganPetak();
            }
            int bilangan_petak = tgkt.getBilanganPetak() - 1;
            tgkt.setBilanganPetak(bilangan_petak);
            strService.simpanBangunanTingkat(tgkt);

        }

        rehydrate();
        return new RedirectResolution(MaintainBangunanPHPPActionBean.class, "showForm1");
    }

    public Resolution deleteTingkat() {

        idTingkat = getContext().getRequest().getParameter("idTingkat");

        if (idTingkat != null) {
            BangunanTingkat tgkt = bangunanTingkatDAO.findById(Long.parseLong(idTingkat));
            PermohonanBangunan bgnn = permohonanBangunanDAO.findById(tgkt.getBangunan().getIdBangunan());
            int bilangan_tgkt = bgnn.getBilanganTingkat() - 1;
            bgnn.setBilanganTingkat(bilangan_tgkt);
            bgnn.setTingkatMezanin(null);
            strService.updateBangunan(bgnn);
            if (tgkt != null) {

                strService.deleteTgkt(tgkt);
            }

        }

        rehydrate();
        return new RedirectResolution(MaintainBangunanPHPPActionBean.class, "showForm1");
    }

    public Resolution deletePtkAksr() {

        String idAksesori = getContext().getRequest().getParameter("idAksesori");

        if (idAksesori != null) {
            BangunanPetakAksesori ptkaksr = bangunanPetakAksesoriDAO.findById(Long.parseLong(idAksesori));


            if (ptkaksr != null) {
                strService.deleteAksesori(ptkaksr);
            }


        }

        rehydrate();
        return new RedirectResolution(MaintainBangunanPHPPActionBean.class, "showForm1");
    }

    public Resolution tambahPetakAksesori() {

        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = permohonanDAO.findById(idPermohonan);
        LOG.info("ID:: " + idPermohonan);
        List<PermohonanBangunan> pb1 = permohonan.getSenaraiBangunan();
        for (PermohonanBangunan pb2 : pb1) {
            int sum3 = strService.CountPetak(String.valueOf(pb2.getIdBangunan()));
            LOG.info(sum3);
            jumlah_ptk_akrs = jumlah_ptk_akrs + sum3;
            LOG.info(jumlah_ptk_akrs);
        }



        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        idPetak = getContext().getRequest().getParameter("idPetak");
        idBangunan = getContext().getRequest().getParameter("idBangunan");
        idTingkat = getContext().getRequest().getParameter("idTingkat");
        InfoAudit infoAudit = new InfoAudit();
        infoAudit.setDimasukOleh(peng);
        infoAudit.setTarikhMasuk(new java.util.Date());



        bangunan = permohonanBangunanDAO.findById(Long.parseLong(idBangunan));
        LOG.debug(bangunan.getIdBangunan());

        ptk = bangunanPetakDAO.findById(Long.parseLong(idPetak));
        bangunanTingkat = bangunanTingkatDAO.findById(Long.parseLong(idTingkat));


        ArrayList<BangunanPetakAksesori> listPetakAksesori = new ArrayList<BangunanPetakAksesori>();
        for (int i = 0; i < bilanganPetakAksesori; i++) {
            LOG.debug("Bilangan Petak Aksesori= " + (i + 1));
            BangunanPetakAksesori ptkAksesori = new BangunanPetakAksesori();
            ptkAksesori.setBangunan(bangunan);
            ptkAksesori.setCawangan(peng.getKodCawangan());
            ptkAksesori.setInfoAudit(infoAudit);
            ptkAksesori.setNama("A" + (jumlah_ptk_akrs + i + 1));
            ptkAksesori.setPetak(ptk);
            ptkAksesori.setTingkat(bangunanTingkat);
            ptkAksesori.setKegunaan(kodkegunaan);
            ptkAksesori.setLokasi(lokasiPetakAksesori);
            listPetakAksesori.add(ptkAksesori);
            LOG.debug(listPetakAksesori);
            strService.simpanPetakAksesori(ptkAksesori);
        }


        return new RedirectResolution(MaintainBangunanPHPPActionBean.class, "showForm1");
    }

    public Resolution simpanSemua() {

        String error = "";
        String msg = "";


        permohonan = permohonanDAO.findById(idPermohonan);
        LOG.info("ID:: " + idPermohonan);
        List<PermohonanBangunan> pb1 = permohonan.getSenaraiBangunan();
        int count = 0;
        int count2 = 0;
        int count3 = 0;

        LOG.info("1111111" + tingkatMezanin);
        for (PermohonanBangunan pb2 : pb1) {

            List<BangunanTingkat> bt1 = pb2.getSenaraiTingkat();
            LOG.info("BANGUNAN:: " + pb2.getNama());

            for (BangunanTingkat bt2 : bt1) {
                String t = tingkatMezanin[count2];
                if (StringUtils.isNotBlank(t)) {

                    pb2.setTingkatMezanin(bt2);
                    strService.updateBangunan(pb2);
                }
                List<BangunanPetak> p1 = bt2.getSenaraiPetak();

                LOG.info("Tingkat:: " + bt2.getIdTingkat());

                for (BangunanPetak p2 : p1) {
                    LOG.info("Petak:: " + p2.getNama());
                    LOG.info("ID Petak: " + p2.getIdPetak());
                    LOG.info("LUAS =" + petakLuas[count]);
                    LOG.info("SYER =" + petakSyer[count]);
                    LOG.info("Kegunaan =" + petakKegunaan[count]);

                    BigDecimal bd = new BigDecimal(petakLuas[count]);
                    p2.setLuas(bd);
                    p2.setSyer(Integer.parseInt(petakSyer[count]));
                    KodKegunaanPetak kodguna = kodKegunaanPetakDAO.findById(petakKegunaan[count]);
                    p2.setKegunaan(kodguna);
                    strService.updatePetak(p2);


                    List<BangunanPetakAksesori> pk1 = p2.getSenaraiPetakAksesori();
                    for (BangunanPetakAksesori pk2 : pk1) {

                        KodKegunaanPetakAksesori kodguna1 = kodKegunaanPetakAksesoriDAO.findById(petakKegunaanAksr[count3]);
                        pk2.setLokasi(lokasiAksr[count3]);
                        pk2.setKegunaan(kodguna1);
                        strService.updatePetakAksesori(pk2);
                        count3++;

                    }
                    msg = "Maklumat telah berjaya dikemaskini.";
                    count++;
                }
                count2++;
            }
        }


        return new RedirectResolution(MaintainBangunanPHPPActionBean.class, "showForm1").addParameter("error", error).addParameter("message", msg);
    }

    public boolean validateXML(NodeList n1) {
        boolean pass = false;
        String hakmilik = new String();
        for (int i = 0; i < n1.getLength(); i++) {
            hakmilik = n1.item(i).getAttributes().getNamedItem("negeri").getNodeValue()
                    + n1.item(i).getAttributes().getNamedItem("daerah").getNodeValue()
                    + n1.item(i).getAttributes().getNamedItem("seksyen").getNodeValue();

            if (hakmilik.equals((listHakPermohon.get(0)).getHakmilik().getIdHakmilik())) {
                pass = true;
                break;
            } else {
                pass = false;
            }
        }
        return pass;
    }

    public Resolution extrakBangunan() throws ParserConfigurationException, IOException, SAXException, ParseException {
        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        InfoAudit infoAudit = new InfoAudit();
        infoAudit.setDimasukOleh(peng);
        infoAudit.setTarikhMasuk(new java.util.Date());
        String error = "";
        String msg = "";

        permohonan = permohonanDAO.findById(idPermohonan);
        Dokumen d = strService.findDok(idPermohonan, "JPP");
        String docPath = conf.getProperty("document.path");
        if (docPath == null) {
            return new ErrorResolution(500, "Konfigurasi \"document.path\" tidak dijumpai");
        }

        if (d == null) {
            error = "Dokumen tidak Dijumpai. Sila Muat Naik Jadual Petak.";
            return new RedirectResolution(MaintainBangunanPHPPActionBean.class, "showForm1").addParameter("error", error).addParameter("message", msg);
        } else {

            List<PermohonanBangunan> pb = strService.findByIDPermohonan(permohonan.getIdPermohonan());
            for (PermohonanBangunan bngn : pb) {
                if (bngn != null) {
                    strService.deleteBngn(bngn);
                }
            }

            File f = new File(docPath + (docPath.endsWith(File.separator) ? "" : File.separator) + d.getNamaFizikal());
            LOG.info(f);
            LOG.info(d.getNamaFizikal());
            try {
                DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
                DocumentBuilder db = dbf.newDocumentBuilder();
                doc = db.parse(f);

                NodeList n1 = doc.getElementsByTagName("Scheme");
                NodeList n2 = doc.getElementsByTagName("Block");
                NodeList n3 = doc.getElementsByTagName("Tingkat");
                NodeList n4 = doc.getElementsByTagName("Petak");
                NodeList n5 = doc.getElementsByTagName("Ruang");
                NodeList n6 = doc.getElementsByTagName("Aksesori");
                NodeList n7 = doc.getElementsByTagName("CommonArea");
                List<PermohonanBangunan> pb1 = permohonan.getSenaraiBangunan();
                if (validateXML(n1)) {
                }
                String diukur_oleh,
                        pengukur_noic,
                        ulasan_jupem,
                        nama_pemaju,
                        nama_perbadanan_pengurusan,
                        alamat_perbadanan_pengurusan,
                        nama_projek,
                        no_ruj_jubl,
                        no_ruj_ljt,
                        no_fail_ukursemula,
                        kodtujuanukur, no_fail_ukur,
                        skim;
                int bilangan_petak;
                int bilangan_aksesori;
                Date tarikh_terima_sijil_akuan;
                Date tarikh_lulus_permohonan;
                Date tarikh_siap;
                BigDecimal bayaran_ukur;
                BigDecimal bayaran_pelan;

//                BigDecimal jumlah_unit_syer;
                //added
                BigDecimal jumlah_unit_syer = new BigDecimal(0);
                
                petakIndex = 0;
                currNoOfPetak = 0;
                prevNoOfPetak = 0;
                petakAksIndex = 0;
                currNoOfPetakAks = 0;
                prevNoOfPetakAks = 0;

                for (int i = 0; i < n1.getLength(); i++) {
                    mohonSkim = new PermohonanSkim();
                    KodTujuanUkur kodTujuanUkur = new KodTujuanUkur();
                    diukur_oleh = (n1.item(i).getAttributes().getNamedItem("diukur_oleh").getNodeValue());
                    pengukur_noic = (n1.item(i).getAttributes().getNamedItem("pengukur_noic").getNodeValue());
                    ulasan_jupem = (n1.item(i).getAttributes().getNamedItem("ulasan_jupem").getNodeValue());
                    nama_pemaju = (n1.item(i).getAttributes().getNamedItem("nama_pemaju").getNodeValue());
                    nama_perbadanan_pengurusan = (n1.item(i).getAttributes().getNamedItem("nama_perbadanan_pengurusan").getNodeValue());
                    alamat_perbadanan_pengurusan = (n1.item(i).getAttributes().getNamedItem("alamat_perbadanan_pengurusan").getNodeValue());
                    nama_projek = (n1.item(i).getAttributes().getNamedItem("nama_projek").getNodeValue());
                    no_ruj_jubl = (n1.item(i).getAttributes().getNamedItem("no_ruj_jubl").getNodeValue());
                    no_ruj_ljt = (n1.item(i).getAttributes().getNamedItem("no_ruj_ljt").getNodeValue());
                    no_fail_ukur = (n1.item(i).getAttributes().getNamedItem("no_fail_ukursemula").getNodeValue());
                    no_fail_ukursemula = (n1.item(i).getAttributes().getNamedItem("no_fail_ukur").getNodeValue());
                    kodtujuanukur = (n1.item(i).getAttributes().getNamedItem("kodtujuanukur").getNodeValue());
                    skim = (n1.item(i).getAttributes().getNamedItem("skim").getNodeValue());
                    bilangan_petak = Integer.valueOf(n1.item(i).getAttributes().getNamedItem("bilangan_petak").getNodeValue());
                    bilangan_aksesori = Integer.valueOf(n1.item(i).getAttributes().getNamedItem("bilangan_aksesori").getNodeValue());
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                    tarikh_terima_sijil_akuan = sdf.parse((n1.item(i).getAttributes().getNamedItem("tarikh_terima_sijil_akuan").getNodeValue()));
                    tarikh_lulus_permohonan = sdf.parse(n1.item(i).getAttributes().getNamedItem("tarikh_lulus_permohonan").getNodeValue());
                    tarikh_siap = sdf.parse(n1.item(i).getAttributes().getNamedItem("tarikh_siap").getNodeValue());
                    if (!StringUtils.isEmpty(n1.item(i).getAttributes().getNamedItem("bayaran_ukur").getNodeValue())) {
                        bayaran_ukur = BigDecimal.valueOf(Double.valueOf(n1.item(i).getAttributes().getNamedItem("bayaran_ukur").getNodeValue()));
                    }

                    if (!StringUtils.isEmpty(n1.item(i).getAttributes().getNamedItem("bayaran_pelan").getNodeValue())) {
                        bayaran_pelan = BigDecimal.valueOf(Double.valueOf(n1.item(i).getAttributes().getNamedItem("bayaran_pelan").getNodeValue()));
                    }

                    if (!StringUtils.isEmpty(n1.item(i).getAttributes().getNamedItem("jumlah_unit_syer").getNodeValue())) {
                        jumlah_unit_syer = BigDecimal.valueOf(Double.valueOf(n1.item(i).getAttributes().getNamedItem("jumlah_unit_syer").getNodeValue()));
                    }
                    if (!n1.item(i).getAttributes().getNamedItem("bayaran_ukur").getNodeValue().equals("")) {
                        bayaran_ukur = BigDecimal.valueOf(Double.valueOf(n1.item(i).getAttributes().getNamedItem("bayaran_ukur").getNodeValue()));
                    }
                    if (!n1.item(i).getAttributes().getNamedItem("bayaran_pelan").getNodeValue().equals("")) {
                        bayaran_pelan = BigDecimal.valueOf(Double.valueOf(n1.item(i).getAttributes().getNamedItem("bayaran_pelan").getNodeValue()));
                    }
                    if (!n1.item(i).getAttributes().getNamedItem("jumlah_unit_syer").getNodeValue().equals("")) {
                        jumlah_unit_syer = BigDecimal.valueOf(Double.valueOf(n1.item(i).getAttributes().getNamedItem("jumlah_unit_syer").getNodeValue()));
                    }

                    /*saving in Pihak */
                    LOG.info("---saving in Pihak---");
                    Pihak pihak = new Pihak();
                    InfoAudit ia = strService.getInfo(peng);
                    LOG.info("---nama_perbadanan_pengurusan---::" + nama_perbadanan_pengurusan);
                    pihak.setNama(nama_perbadanan_pengurusan);
                    LOG.info("---alamat_perbadanan_pengurusan---::" + alamat_perbadanan_pengurusan);
                    /* using string tokenizer for alamat_perbadanan_pengurusan coz
                     * only one string is there at XML file
                     * for saving in alamat1, alamat2, and so on using string tokenizer
                     */

                    String str = alamat_perbadanan_pengurusan;
                    LOG.info("---str---::" + alamat_perbadanan_pengurusan);
                    String[] rs = str.split(",");
                    LOG.info("-Alamat1--rs[0]---::" + rs[0]);
                    pihak.setAlamat1(rs[0]);
                    LOG.info("-Alamat2--rs[1].trim()---::" + rs[1].trim());
                    pihak.setAlamat2(rs[1].trim());
                    /*in XML dont have alamat3 and alamat4 thats y here setting emtpy string*/
                    pihak.setAlamat3("");
                    pihak.setAlamat4("");
                    String str1 = rs[2];
                    String[] rs1 = str1.trim().split(" ");
                    LOG.info("---str1.trim()--::" + str1.trim());
                    LOG.info("---rs1--::" + rs1);
                    LOG.info("---rs1[0]--::" + rs1[0]);
                    pihak.setPoskod(rs1[0]);
                    if (kodNegeri.equalsIgnoreCase("04")) {
                        LOG.info("---Melaka--::");
                        if (str.endsWith("Melaka")) {
                            LOG.info("---rs1[1]--::" + rs1[1]);
                            KodNegeri kd = kodNegeriDAO.findById("04");
                            LOG.info("---kd---::" + kd);
                            pihak.setNegeri(kd);
                        }
                    } else {
                        LOG.info("---Seremban--::");
                        if (rs1[1].endsWith("SEREMBAN")) {
                            LOG.info("---rs1[1]--::" + rs1[1]);
                            KodNegeri kd = kodNegeriDAO.findById("05");
                            LOG.info("---kd---::" + kd);
                            pihak.setNegeri(kd);
                        }
                    }
                    pihak.setInfoAudit(ia);
                    pihak = strService.savePihak(pihak);
                    LOG.info("---ID PIHAK---::" + pihak.getIdPihak());
                    pihak = pihakDAO.findById(pihak.getIdPihak());
                    /* end pihak */

                    /*saving in Strata_BDN_URUS */
                    LOG.info("---saving BadanPengurusan---");
                    mc = new BadanPengurusan();
                    InfoAudit ia1 = strService.getInfo(peng);
                    mc.setPihak(pihak);
                    mc.setPermohonan(permohonan);
                    mc.setCawangan(permohonan.getCawangan());
                    mc.setInfoAudit(ia1);
                    strService.simpanMaklumatBangunan(mc);
                    LOG.info("---ID BDN---::" + mc.getIdBadan());
                    /* end BadanPengurusan */

                    mohonSkim.setBadanPengurusan(mc);
                    mohonSkim.setCawangan(peng.getKodCawangan());
                    mohonSkim.setHakmilikPermohonan(listHakPermohon.get(0));
                    mohonSkim.setInfoAudit(infoAudit);
                    mohonSkim.setNamaPemaju(nama_pemaju);
                    mohonSkim.setNamaProjek(nama_projek);
                    mohonSkim.setDiukur(diukur_oleh);
                    mohonSkim.setNoKpPengukur(pengukur_noic);
                    mohonSkim.setNoFailUkur(no_ruj_ljt);
                    mohonSkim.setNoSkim(getIntegerValue1(skim));
                    mohonSkim.setNoRujJubl(no_ruj_jubl);
                    mohonSkim.setNoFailUkurSemula(no_fail_ukursemula);
                    mohonSkim.setNoFailPt(no_fail_ukur);
                    //added 16-05-2012 by murali                    
                    mohonSkim.setJumlahUnitSyer(jumlah_unit_syer.longValue());
                    kodTujuanUkur = kodTujuanUkurDAO.findById(kodtujuanukur);
                    mohonSkim.setKodTujuanUkur(kodTujuanUkur);//
                    if (!StringUtils.isEmpty(String.valueOf(bilangan_petak))) {
                        mohonSkim.setBilPetak(Long.parseLong(String.valueOf(bilangan_petak)));
                    }
                    if (!StringUtils.isEmpty(String.valueOf(bilangan_aksesori))) {

                        mohonSkim.setBilAksr(Long.parseLong(String.valueOf(bilangan_aksesori)));
                    }
                    mohonSkim.setTrhSiap(tarikh_siap);
                    mohonSkim.setTrhLulusCf(tarikh_terima_sijil_akuan);
                    mohonSkim = strService.saveSkim(mohonSkim);
                    PermohonanStrata mohonStrata = strService.findID(idPermohonan);
                    if (mohonStrata != null) {
                    } else {
                        mohonStrata = new PermohonanStrata();
                    }

                    mohonStrata.setPermohonan(permohonan);
                    mohonStrata.setInfoAudit(infoAudit);
                    mohonStrata.setCawangan(peng.getKodCawangan());
                    mohonStrata.setNama(nama_projek);
                    mohonStrata.setPemilikNama("-");
                    mohonStrata.setNamaSkim(getIntegerValue1(skim));
                    strService.simpanPemilik(mohonStrata);
                    int a;
                    for (a = 0; a < n2.getLength(); a++) {
                        KodKategoriBangunan kodKatBangunan = new KodKategoriBangunan();
                        String dl = "";
                        PermohonanBangunan bgn = new PermohonanBangunan();
                        jumPetak = 0;
                        bgn.setPermohonan(permohonan);
                        bgn.setInfoAudit(infoAudit);
                        bgn.setCawangan(peng.getKodCawangan());
                        kodKatBangunan = kodKategoriBangunanDAO.findById((n2.item(a).getAttributes().getNamedItem("blocktype").getNodeValue()));
                        if (kodKatBangunan.getKod().equals("B")) {
                            bgn.setNama("M" + getIntegerValue(n2.item(a).getAttributes().getNamedItem("bangunanno").getNodeValue()));
                        }
                        if (kodKatBangunan.getKod().equals("P")) {
                            bgn.setNama("P" + getIntegerValue(n2.item(a).getAttributes().getNamedItem("bangunanno").getNodeValue()));
                        }
                        if (kodKatBangunan.getKod().equals("L")) {
                            bgn.setNama(getIntegerValue(n2.item(a).getAttributes().getNamedItem("bangunanno").getNodeValue()));
                        }
                        bgn.setPermohonanSkim(mohonSkim);
                        bgn.setNama("M" + getIntegerValue(n2.item(a).getAttributes().getNamedItem("bangunanno").getNodeValue()));
                        bgn.setBilanganTingkat(Integer.valueOf(n2.item(a).getAttributes().getNamedItem("no_of_tingkat").getNodeValue()));
                        bgn.setKodKategoriBangunan(kodKatBangunan);
                        bgn.setSyerBlok(Integer.valueOf(n2.item(a).getAttributes().getNamedItem("unitsyer").getNodeValue()));

                        KodKegunaanBangunan kodkegunan = new KodKegunaanBangunan();
                        kodkegunan = kodKegunaanBangunanDAO.findById((n2.item(a).getAttributes().getNamedItem("kodkegunaanbangunan").getNodeValue()));
                        LOG.info("---kodkegunan---" + kodkegunan);
                        bgn.setKodKegunaanBangunan(kodkegunan);
                        strService.simpanBangunan(bgn);
                        msg = "Maklumat telah berjaya disimpan.";
                        extrakTgkt(n3, n4, n6, n7, bgn.getIdBangunan());
                        bgn.setBilanganPetak(jumPetak);
                        strService.simpanBangunan(bgn);
                    }
                }

                /*saving in Mohon_Harta_Bersama */
                LOG.info("---saving in Mohon_Harta_Bersama---");
                int ca;
                for (ca = 0; ca < n7.getLength(); ca++) {
                    PermohonanHartaBersama phb = new PermohonanHartaBersama();
                    infoAudit.setDimasukOleh(peng);
                    infoAudit.setTarikhMasuk(new java.util.Date());
                    phb.setInfoAudit(infoAudit);
                    phb.setCawangan(peng.getKodCawangan());
                    phb.setIdSkim(mohonSkim);
                    String nama = n7.item(ca).getAttributes().getNamedItem("commonareano").getNodeValue();
                    LOG.debug("--nama--::" + nama);
                    String temp = nama.substring(3, nama.length());
                    LOG.debug("--substring nama--::" + temp);
                    phb.setNama(temp);
                    if (n7.item(ca).getAttributes().getNamedItem("petaktype").getNodeValue() != null) {
                        String petaktype1 = n7.item(ca).getAttributes().getNamedItem("petaktype").getNodeValue();
                        LOG.debug("--petak type--::" + petaktype1);
                        KodHartaBersama khb = strService.findKodHartaBersamaByNama(petaktype1);
                        LOG.debug("--khb--::" + khb);
                        LOG.debug("--khb.nama--::" + khb.getNama());
                        phb.setJenisHartaBersama(khb);
                        LOG.debug("--saved in jenisHartaBersama coloum--::" + khb.getNama());
                    }
                    strService.simpanPermohonanHartaBersama(phb);
                }

            } catch (FileNotFoundException e) {
                e.printStackTrace();
                return new ErrorResolution(500,
                        "Fail " + docPath + d.getNamaFizikal() + " tidak dijumpai");
            }
        }
        return new RedirectResolution(MaintainBangunanPHPPActionBean.class, "showForm1").addParameter("error", error).addParameter("message", msg);
    }

    public String getIntegerValue(String s) {
        String remove = "(B)M";
        int i;
        String result = "";
        i = s.indexOf(remove);
        if (i != -1) {
            result = s.substring(0, i);
            result = result + s.substring(i + remove.length());
            s = result;
        }
        remove = "(B)";
        i = s.indexOf(remove);
        if (i != -1) {
            result = s.substring(0, i);
            result = result + s.substring(i + remove.length());
            s = result;
        }
        return s;
    }

    public String getIntegerValue1(String s) {
        String remove = "(S)";
        int i;
        String result = "";
        i = s.indexOf(remove);
        if (i != -1) {
            result = s.substring(0, i);
            result = result + s.substring(i + remove.length());
            s = result;
        }
        return s;
    }

    public Resolution extrakTgkt(NodeList n3, NodeList n4, NodeList n6, NodeList n7, long IdBangunan) {

        String error = "";
        String msg = "";
        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        InfoAudit infoAudit = new InfoAudit();
        infoAudit.setDimasukOleh(peng);
        infoAudit.setTarikhMasuk(new java.util.Date());
        ArrayList<BangunanTingkat> listTingkat = new ArrayList<BangunanTingkat>();
        PermohonanBangunan bgn = permohonanBangunanDAO.findById(IdBangunan);
        BangunanTingkat tgkt;

        int i = 0;
      //  int petakIndex = 0;
      //  int currNoOfPetak = 0;
      //  int prevNoOfPetak = 0;
        LOG.info("-----------hv-------------:" + hv);
        
        for (int b = hv; b < bgn.getBilanganTingkat() + hv; b++) {

            LOG.debug("Bilangan Tingkat = " + i);
            tgkt = new BangunanTingkat();
            tgkt.setBangunan(bgn);
            tgkt.setNama(String.valueOf(i + 1));
            tgkt.setTingkat(i + 1);
            tgkt.setBilanganPetak(Integer.parseInt(n3.item(b).getAttributes().getNamedItem("no_of_petak").getNodeValue()));
            currNoOfPetak = Integer.parseInt(n3.item(b).getAttributes().getNamedItem("no_of_petak").getNodeValue());
            LOG.info("------bngn-----petakIndex-------------:" + petakIndex);
            petakIndex = petakIndex + prevNoOfPetak;
            prevNoOfPetak = currNoOfPetak;
            //String bilanganPetakAksTemp = (n3.item(b).getAttributes().getNamedItem("no_of_aksesori").getNodeValue());
            String bilanganPetakAksTemp = (n3.item(b).getAttributes().getNamedItem("no_of_aksesori").getNodeValue());
            int bilanganPetakAks = 0;
            LOG.info("-----------bilanganPetakAksTemp-------------:" + bilanganPetakAksTemp);
            if (bilanganPetakAksTemp != null) {
                bilanganPetakAks = Integer.parseInt(bilanganPetakAksTemp);
                currNoOfPetakAks = Integer.parseInt(bilanganPetakAksTemp);
            }
            petakAksIndex = petakAksIndex + prevNoOfPetakAks;
            prevNoOfPetakAks = currNoOfPetakAks;
            LOG.info("-----------bilanganPetakAks-------------:" + bilanganPetakAks);
            jumPetak = jumPetak + tgkt.getBilanganPetak();
            tgkt.setInfoAudit(infoAudit);
            tgkt.setBilanganPetakAksesori(bilanganPetakAks);
            listTingkat.add(tgkt);
            strService.simpanBangunanTingkat(tgkt);
            extrakPetak(n4, n6, n7, tgkt.getIdTingkat(), bgn.getIdBangunan(), petakIndex,petakAksIndex);
            i++;
        }
        hv = bgn.getBilanganTingkat();
        bgn.setSenaraiTingkat(listTingkat);
        strService.updateBangunan(bgn);

        return new RedirectResolution(MaintainBangunanPHPPActionBean.class, "showForm1").addParameter("error", error).addParameter("message", msg);
    }

    public Resolution extrakPetak(NodeList n4, NodeList n6, NodeList n7, long idTingkat, long IdBangunan, int petakIndex,int petakAksIndex) {

        String error = "";
        String msg = "";
        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        InfoAudit infoAudit = new InfoAudit();
        infoAudit.setDimasukOleh(peng);
        infoAudit.setTarikhMasuk(new java.util.Date());
        PermohonanBangunan bgn = permohonanBangunanDAO.findById(IdBangunan);
        bangunanTingkat = bangunanTingkatDAO.findById(idTingkat);
        int c;
        ArrayList<BangunanPetak> listPetak = new ArrayList<BangunanPetak>();
        for (c = 0; c < bangunanTingkat.getBilanganPetak(); c++) {
            LOG.info("-----------petakIndex-------------:" + petakIndex);
            InfoAudit infoAudit1 = new InfoAudit();
            infoAudit1.setDimasukOleh(peng);
            infoAudit1.setTarikhMasuk(new java.util.Date());
            LOG.debug("Bilangan Petak = " + c);
            BangunanPetak petak = new BangunanPetak();
            petak.setNama(String.valueOf(sum4 + c + 1));
            petak.setInfoAudit(infoAudit1);
            petak.setTingkat(bangunanTingkat);
            BigDecimal bd = new BigDecimal(n4.item(petakIndex).getAttributes().getNamedItem("a_area").getNodeValue());
            petak.setLuas(bd);
            KodKegunaanPetak k = new KodKegunaanPetak();
            k.setKod(n4.item(petakIndex).getAttributes().getNamedItem("kodkegunaanpetak").getNodeValue());
            petak.setKegunaan(k);
            int uSyer = Integer.valueOf(n4.item(petakIndex).getAttributes().getNamedItem("unitsyer").getNodeValue());
            LOG.info("-----------uSyer-------------:" + uSyer);
            petak.setSyer(uSyer);
            String petakpab = n4.item(petakIndex).getAttributes().getNamedItem("pab").getNodeValue();
            LOG.info("-----------PAB-------------:" + petakpab);
            if (petakpab != null) {
                petak.setPabPetak(petakpab);
            }
            String unitluas = n4.item(petakIndex).getAttributes().getNamedItem("a_unit").getNodeValue();
            KodUOM kodUOM = kodUOMDAO.findById(unitluas);
            petak.setLuasUom(kodUOM);
            listPetak.add(petak);
            strService.simpanPetak(petak);
            petakIndex++;
        }
        sum4 = sum4 + bangunanTingkat.getBilanganPetak();
        LOG.debug("--bangunanTingkat--::"+bangunanTingkat.getIdTingkat());
        LOG.debug("--bangunanTingkat.getBilanganPetakAksesori()--::"+bangunanTingkat.getBilanganPetakAksesori());
        int bilanganPetakAks = bangunanTingkat.getBilanganPetakAksesori();
        LOG.debug("--bilanganPetakAks--::"+bilanganPetakAks);
        if (bilanganPetakAks > 0) {
            extrakPetakAksesori(n6, n7, bangunanTingkat.getIdTingkat(), bgn.getIdBangunan(), bilanganPetakAks,petakAksIndex);
        }

        return new RedirectResolution(MaintainBangunanPHPPActionBean.class, "showForm1").addParameter("error", error).addParameter("message", msg);
    }

    public Resolution extrakPetakAksesori(NodeList n6, NodeList n7, long idTingkat, long IdBangunan, int bilanganPetakAks,int petakAksIndex) {
        LOG.debug("--in extrakPetakAksesori--::");
        LOG.debug("--idTingkat--::"+idTingkat);
        LOG.debug("--IdBangunan--::"+IdBangunan);
        String error = "";
        String msg = "";
        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        InfoAudit infoAudit = new InfoAudit();
        infoAudit.setDimasukOleh(peng);
        infoAudit.setTarikhMasuk(new java.util.Date());
        PermohonanBangunan bgn = permohonanBangunanDAO.findById(IdBangunan);
        LOG.debug("--bgn--::" + bgn);

        /*Saving in BGN_PTK_AKS*/
        LOG.debug("--saving in BNGN_PTK_AKS--::" + bilanganPetakAks);
        int c;
        BangunanPetakAksesori bpk = new BangunanPetakAksesori();
        ArrayList<BangunanPetakAksesori> listPetakAks = new ArrayList<BangunanPetakAksesori>();
        StringBuffer sb = new StringBuffer();
        List<String> aksnameList = new ArrayList<String>();
        for (c = 0; c < bilanganPetakAks; c++) {
            LOG.debug("--aksesorino--::" + n6.item(petakAksIndex).getAttributes().getNamedItem("aksesorino").getNodeValue());
            String aksnama = n6.item(petakAksIndex).getAttributes().getNamedItem("aksesorino").getNodeValue();

            String petakupi = n6.item(petakAksIndex).getAttributes().getNamedItem("petakupi").getNodeValue();
             LOG.debug("--petakAksIndex--::" + petakAksIndex);
            LOG.debug("--petakupi--::" + petakupi);
            String tinketNama = "";
            String petakNama = "";
            if (petakupi != null) {

                int tinketIndex = petakupi.lastIndexOf("T");

                System.out.println("Tinket Index:" + tinketIndex);

                int petakIndex = petakupi.lastIndexOf("P");

                System.out.println("Petak Index:" + petakIndex);

                tinketNama = petakupi.substring(tinketIndex + 2, petakIndex - 1);

                System.out.println("Tinket Value:" + tinketNama);

                petakNama = petakupi.substring(petakIndex + 2);

                System.out.println("Petak Value:" + petakNama);
            }

            BangunanTingkat bgnTkt = new BangunanTingkat();
            BangunanPetak ptk = new BangunanPetak();
            bgnTkt = strService.findTinketByIdBngnNama(IdBangunan, tinketNama);
            LOG.debug("--bgnTkt--::" + bgnTkt.getIdTingkat());
            ptk = strService.findPetakByIdBngnIdTinketNama(bgnTkt.getIdTingkat(), petakNama);
            LOG.debug("--ptk--::" + ptk.getIdPetak());


            bpk = new BangunanPetakAksesori();
            InfoAudit infoAudit1 = new InfoAudit();
            infoAudit1.setDimasukOleh(peng);
            infoAudit1.setTarikhMasuk(new java.util.Date());
            if (aksnama != null) {
                LOG.debug("--aksnama--::" + aksnama);
                String temp = aksnama.substring(3, aksnama.length());
                bpk.setNama(temp);
            }
            bpk.setInfoAudit(infoAudit1);
            bpk.setCawangan(peng.getKodCawangan());
            bpk.setTingkat(bgnTkt);
            bpk.setBangunan(bgn);
            bpk.setPetak(ptk);
            String aksesorino = n6.item(c).getAttributes().getNamedItem("aksesorino").getNodeValue();
            LOG.debug("--aksesorino--::" + aksesorino);
            if (aksnama.charAt(0) == 'A') {
                bpk.setLokasi("Luar Bangunan");
            } else {
                bpk.setLokasi("Dalam Bangunan");
            }
            String petaktype = n6.item(c).getAttributes().getNamedItem("petaktype").getNodeValue();
            LOG.debug("--petak type--::" + petaktype);
            KodKegunaanPetakAksesori kodguna1 = kodKegunaanPetakAksesoriDAO.findById(petaktype);
            LOG.info("--kodguna1--" + kodguna1);
            if (kodguna1 != null) {
                bpk.setKegunaan(kodguna1);
            }
            //added
            String petakaksrpab = n6.item(c).getAttributes().getNamedItem("pab").getNodeValue();
            LOG.info("--petakaksrpab--" + petakaksrpab);
            if (petakaksrpab != null) {
                bpk.setPabAksesori(petakaksrpab);
            }
            strService.simpanPetakAksesori(bpk);
            petakAksIndex++;
        }
        return new RedirectResolution(MaintainBangunanPHPPActionBean.class, "showForm1").addParameter("error", error).addParameter("message", msg);
    }

    public Resolution HapusXml() {
        LOG.debug("---------Hapus Xml Started--------::");
        String msg = "";
        permohonan = permohonanDAO.findById(idPermohonan);
        Dokumen d = strService.findDok(idPermohonan, "JPP");
        String docPath = conf.getProperty("document.path");
        if (d != null) {
            LOG.debug("---------Xml Document deleting from table--------::");
            File f = new File(docPath + (docPath.endsWith(File.separator) ? "" : File.separator) + d.getNamaFizikal());
            d.setNamaFizikal("");
            strService.saveDokumen(d);
            LOG.debug("---------Xml physical path deleting--------::");
            f.delete();
        }

        LOG.debug("---------Data deleting from Mohon_bngn table--------::");
        List<PermohonanBangunan> pb1 = strService.findByIDPermohonan1(idPermohonan);
        LOG.debug("---------PermohonanBangunan--------::" + pb1);

        for (PermohonanBangunan pb2 : pb1) {

            List<BangunanTingkat> bt1 = pb2.getSenaraiTingkat();
            LOG.info("BANGUNAN:: " + pb2.getNama());

            for (BangunanTingkat bt2 : bt1) {

                List<BangunanPetak> p1 = bt2.getSenaraiPetak();

                LOG.info("Tingkat:: " + bt2.getIdTingkat());

                for (BangunanPetak p2 : p1) {

                    List<BangunanPetakAksesori> pk1 = strService.findPtkAksrByIDPetak(p2.getIdPetak());

                    for (BangunanPetakAksesori pk2 : pk1) {
                        LOG.info("--------petak aksesori deleting---------::");
                        strService.deleteAksesori(pk2);
                    }
                    LOG.info("--------petak deleting--------::");
                    strService.deletePetak(p2);
                }
                LOG.info("--------Tingkat deleting::");
                strService.deleteTgkt(bt2);
            }
            LOG.info("--------Permohonan Bangunan deleting::");
            strService.deleteBngn(pb2);
        }

        LOG.debug("--Data deleting from Mohon_strata table--::");
        permohonanstrata = strService.findID(idPermohonan);
        LOG.debug("--permohonanstrata--::" + permohonanstrata);
        strService.deletemohonStrataByIDMohon(idPermohonan);
        LOG.debug("--permohonanstrata deleted--::");

        if (kodNegeri.equalsIgnoreCase("05")) {
            LOG.debug("--Data deleting from Mohon_skim table--::");
            listHakPermohon = strService.getMaklumatTan(idPermohonan);
            LOG.debug("--listHakPermohon--::" + listHakPermohon);
            if (listHakPermohon.size() > 0) {
                HakPermohon = listHakPermohon.get(0);
                LOG.debug("--HakPermohon--::" + HakPermohon);
            }
            listMohonSkim = strService.findPermohonanSkimByIdMh(Long.valueOf(HakPermohon.getId()));
            LOG.debug("--mohonSkim list- based on date-::" + listMohonSkim);
            if (listMohonSkim.size() > 0) {
                mohonSkim = listMohonSkim.get(0);
                //added
                Long mhb = mohonSkim.getIdSkim();
                LOG.debug("--getIdSkim--::" + mhb);
                LOG.debug("--mohonSkim--::" + mohonSkim.getIdSkim());
                LOG.debug("--Data deleting from Mohon_Harta_Bersama--::");
                strService.deleteHartaBersamaByIdSkim(mhb);
                strService.deletemohonskim(mohonSkim.getIdSkim());
                LOG.debug("--mohonSkim Deleted--::");
            }
        }

        if (kodNegeri.equalsIgnoreCase("04")) {
            LOG.debug("--Data deleting from Mohon_skim table--:");
            if (pBangunanL != null) {
                mohonSkim = strService.findSkimById(pBangunanL.get(0).getPermohonanSkim().getIdSkim());
                LOG.debug("--mohonSkim--:" + mohonSkim.getIdSkim());
                strService.deleteHartaBersamaByIdSkim(mohonSkim.getIdSkim());
                LOG.debug("--deleted hartabersama--:");
                strService.deletemohonskim(mohonSkim.getIdSkim());
                LOG.debug("--deleted mohonSkim--:");
            }
        }

        LOG.debug("--Data deleting from BDN URS n Pihak--::");
        badanUrus = strService.findBdn(idPermohonan);
        LOG.debug("--BDN URS--::" + badanUrus);
        Long pk = badanUrus.getPihak().getIdPihak();
        LOG.debug("--pihak--::" + pk);
        strService.deleteBadanUrus(badanUrus);
        LOG.debug("--BDN URS n pihak Deleted--::");

        LOG.debug("--pihak-from BDN URS--::" + pk);
        Pihak pk1 = strService.findByIdPihak(pk);
        LOG.debug("--pihak--from--findBy Id--::" + pk1);
        strService.deletePihak(pk1.getIdPihak());
        LOG.debug("--pihak Deleted--::");

        msg = "Maklumat telah berjaya disimpan.";

        return new RedirectResolution(MaintainBangunanPHPPActionBean.class, "showForm4").addParameter("message", msg);
    }

    public String getTempIdBangunan() {
        return tempIdBangunan;
    }

    public void setTempIdBangunan(String tempIdBangunan) {
        this.tempIdBangunan = tempIdBangunan;
    }

    public String getTempIdTingkat() {
        return tempIdTingkat;
    }

    public void setTempIdTingkat(String tempIdTingkat) {
        this.tempIdTingkat = tempIdTingkat;
    }

    public String getTempIdPetak() {
        return tempIdPetak;
    }

    public void setTempIdPetak(String tempIdPetak) {
        this.tempIdPetak = tempIdPetak;
    }

    public List getListHakPermohon() {
        return listHakPermohon;
    }

    public void setListHakPermohon(List listHakPermohon) {
        this.listHakPermohon = listHakPermohon;
    }

    public HakmilikPermohonan getHakmilikpermohonan() {
        return hakmilikpermohonan;
    }

    public void setHakmilikpermohonan(HakmilikPermohonan hakmilikpermohonan) {
        this.hakmilikpermohonan = hakmilikpermohonan;
    }

    public BangunanPetak getBangunanPetak() {
        return bangunanPetak;
    }

    public void setBangunanPetak(BangunanPetak bangunanPetak) {
        this.bangunanPetak = bangunanPetak;
    }

    public String[] getTingkatMezanin() {
        return tingkatMezanin;
    }

    public void setTingkatMezanin(String[] tingkatMezanin) {
        this.tingkatMezanin = tingkatMezanin;
    }

    public List<BangunanTingkat> getSenaraiTingkat() {
        return senaraiTingkat;
    }

    public void setSenaraiTingkat(List<BangunanTingkat> senaraiTingkat) {
        this.senaraiTingkat = senaraiTingkat;
    }

    public String[] getLokasiAksr() {
        return lokasiAksr;
    }

    public void setLokasiAksr(String[] lokasiAksr) {
        this.lokasiAksr = lokasiAksr;
    }

    public String[] getPetakKegunaanAksr() {
        return petakKegunaanAksr;
    }

    public void setPetakKegunaanAksr(String[] petakKegunaanAksr) {
        this.petakKegunaanAksr = petakKegunaanAksr;
    }

    public String getLokasiPetakAksesori() {
        return lokasiPetakAksesori;
    }

    public void setLokasiPetakAksesori(String lokasiPetakAksesori) {
        this.lokasiPetakAksesori = lokasiPetakAksesori;
    }

    public String[] getPetakKegunaan() {
        return petakKegunaan;
    }

    public void setPetakKegunaan(String[] petakKegunaan) {
        this.petakKegunaan = petakKegunaan;
    }

    public String[] getPetakLuas() {
        return petakLuas;
    }

    public void setPetakLuas(String[] petakLuas) {
        this.petakLuas = petakLuas;
    }

    public String[] getPetakSyer() {
        return petakSyer;
    }

    public void setPetakSyer(String[] petakSyer) {
        this.petakSyer = petakSyer;
    }

    public List<BangunanPetak> getSenaraiPetak() {
        return senaraiPetak;
    }

    public List<BangunanPetakAksesori> getSenaraipetakAksesori() {
        return senaraipetakAksesori;
    }

    public void setSenaraipetakAksesori(List<BangunanPetakAksesori> senaraipetakAksesori) {
        this.senaraipetakAksesori = senaraipetakAksesori;
    }

    public void setSenaraiPetak(List<BangunanPetak> senaraiPetak) {
        this.senaraiPetak = senaraiPetak;
    }

    public KodKegunaanPetakAksesori getKodkegunaan() {
        return kodkegunaan;
    }

    public void setKodkegunaan(KodKegunaanPetakAksesori kodkegunaan) {
        this.kodkegunaan = kodkegunaan;
    }

    public int getBilanganPetakAksesori() {
        return bilanganPetakAksesori;
    }

    public void setBilanganPetakAksesori(int bilanganPetakAksesori) {
        this.bilanganPetakAksesori = bilanganPetakAksesori;
    }

    public List<KodKegunaanPetakAksesori> getkGunaPetakAksesoriL() {
        return kGunaPetakAksesoriL;
    }

    public void setkGunaPetakAksesoriL(List<KodKegunaanPetakAksesori> kGunaPetakAksesoriL) {
        this.kGunaPetakAksesoriL = kGunaPetakAksesoriL;
    }

    public String getIdBangunan() {
        return idBangunan;
    }

    public void setIdBangunan(String idBangunan) {
        this.idBangunan = idBangunan;
    }

    public String getIdPetak() {
        return idPetak;
    }

    public void setIdPetak(String idPetak) {
        this.idPetak = idPetak;
    }

    public KodKegunaanPetak getKegunaan() {
        return kegunaan;
    }

    public void setKegunaan(KodKegunaanPetak kegunaan) {
        this.kegunaan = kegunaan;
    }

    public List<KodKegunaanPetak> getkGunaPetakL() {
        return kGunaPetakL;
    }

    public void setkGunaPetakL(List<KodKegunaanPetak> kGunaPetakL) {
        this.kGunaPetakL = kGunaPetakL;
    }

    public int getSum2() {
        return sum2;
    }

    public void setSum2(int sum2) {
        this.sum2 = sum2;
    }

    public String getLuas() {
        return luas;
    }

    public void setLuas(String luas) {
        this.luas = luas;
    }

    public String getSyer() {
        return syer;
    }

    public void setSyer(String syer) {
        this.syer = syer;
    }

    public BangunanPetak getPtk() {
        return ptk;
    }

    public void setPtk(BangunanPetak ptk) {
        this.ptk = ptk;
    }

    public String getNamaBangunan() {
        return namaBangunan;
    }

    public void setNamaBangunan(String namaBangunan) {
        this.namaBangunan = namaBangunan;
    }

    public String getTingkat() {
        return tingkat;
    }

    public void setTingkat(String tingkat) {
        this.tingkat = tingkat;
    }

    public PermohonanBangunan getBangunan() {
        return bangunan;
    }

    public void setBangunan(PermohonanBangunan bangunan) {
        this.bangunan = bangunan;
    }

    public PermohonanPihakDAO getPermohonanPihakDAO() {
        return permohonanPihakDAO;
    }

    public void setPermohonanPihakDAO(PermohonanPihakDAO permohonanPihakDAO) {
        this.permohonanPihakDAO = permohonanPihakDAO;
    }

    public Permohonan getPermohonan() {
        return permohonan;
    }

    public void setPermohonan(Permohonan permohonan) {
        this.permohonan = permohonan;
    }

    public List<BangunanTingkat> getTingkatL() {
        return tingkatL;
    }

    public void setTingkatL(List<BangunanTingkat> tingkatL) {
        this.tingkatL = tingkatL;
    }

    public List<PermohonanBangunan> getpBangunanL() {
        return pBangunanL;
    }

    public void setpBangunanL(List<PermohonanBangunan> pBangunanL) {
        this.pBangunanL = pBangunanL;
    }

    public BangunanTingkat getBangunanTingkat() {
        return bangunanTingkat;
    }

    public List<BangunanPetakAksesori> getPetakAksesoriL() {
        return petakAksesoriL;
    }

    public void setPetakAksesoriL(List<BangunanPetakAksesori> petakAksesoriL) {
        this.petakAksesoriL = petakAksesoriL;
    }

    public void setBangunanTingkat(BangunanTingkat bangunanTingkat) {
        this.bangunanTingkat = bangunanTingkat;
    }

    public String getIdTingkat() {
        return idTingkat;
    }

    public void setIdTingkat(String idTingkat) {
        this.idTingkat = idTingkat;
    }

    public int getBilanganPetak() {
        return bilanganPetak;
    }

    public void setBilanganPetak(int bilanganPetak) {
        this.bilanganPetak = bilanganPetak;
    }

    public List<BangunanPetak> getPetakL() {
        return petakL;
    }

    public void setPetakL(List<BangunanPetak> petakL) {
        this.petakL = petakL;
    }

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    @Override
    public String getStageId() {
        return stageId;
    }

    @Override
    public void setStageId(String stageId) {
        this.stageId = stageId;
    }

    public List<PermohonanHartaBersama> getListpermohonanHartaBersama() {
        return listpermohonanHartaBersama;
    }

    public void setListpermohonanHartaBersama(List<PermohonanHartaBersama> listpermohonanHartaBersama) {
        this.listpermohonanHartaBersama = listpermohonanHartaBersama;
    }

    public String getKodNegeri() {
        return kodNegeri;
    }

    public void setKodNegeri(String kodNegeri) {
        this.kodNegeri = kodNegeri;
    }

    public String getKodurusan() {
        return kodurusan;
    }

    public void setKodurusan(String kodurusan) {
        this.kodurusan = kodurusan;
    }

    public PermohonanSkim getMohonSkim() {
        return mohonSkim;
    }

    public void setMohonSkim(PermohonanSkim mohonSkim) {
        this.mohonSkim = mohonSkim;
    }

    public int getPetakIndex() {
        return petakIndex;
    }

    public void setPetakIndex(int petakIndex) {
        this.petakIndex = petakIndex;
    }

    public int getCurrNoOfPetak() {
        return currNoOfPetak;
    }

    public void setCurrNoOfPetak(int currNoOfPetak) {
        this.currNoOfPetak = currNoOfPetak;
    }

    public int getPrevNoOfPetak() {
        return prevNoOfPetak;
    }

    public void setPrevNoOfPetak(int prevNoOfPetak) {
        this.prevNoOfPetak = prevNoOfPetak;
    }
    
    
}