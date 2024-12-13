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
 * @author wan.fairul
 */
@UrlBinding("/strata/RMH1AUrusan_rmhs")
public class RMH1AMaintainBangunanActionBean extends BasicTabActionBean {

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
    private static Document doc = null;
    private List<BangunanPetak> petakL;
    private List<BangunanTingkat> tingkatL;
    private List<PermohonanBangunan> pBangunanL;
    private List<PermohonanBangunan> pBangunanLandParcel;
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
    private static final Logger LOG = Logger.getLogger(RMH1AMaintainBangunanActionBean.class);
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

    public Resolution selectedPitak() {

        tempIdBangunan = getContext().getRequest().getParameter("idBangunan");
        tempIdTingkat = getContext().getRequest().getParameter("idTingkat");
        tempIdPetak = getContext().getRequest().getParameter("idPetak");

        LOG.info("----------selectedPitak---------" + tempIdBangunan);
        LOG.info("----------selectedPitak---------" + tempIdTingkat);
        LOG.info("----------selectedPitak---------" + tempIdPetak);
        try {
            readonlyPBBD();
        } catch (Exception e) {
            e.printStackTrace();
        }
//        getContext().getRequest().setAttribute("ekstrak", Boolean.TRUE);
        return new JSP("strata/pbbm/maklumat_jadualpetak_readonly1.jsp").addParameter("tab", "true");
    }

    public Resolution selectedTingkat() {
        tempIdBangunan = getContext().getRequest().getParameter("idBangunan");
        tempIdTingkat = getContext().getRequest().getParameter("idTingkat");

        LOG.info("----------selectedTingkat---------" + tempIdBangunan);
        LOG.info("----------selectedTingkat---------" + tempIdTingkat);
        try {
            readonlyPBBD();
        } catch (Exception e) {
            e.printStackTrace();
        }
//        getContext().getRequest().setAttribute("ekstrak", Boolean.TRUE);
        return new JSP("strata/pbbm/maklumat_jadualpetak_readonly1.jsp").addParameter("tab", "true");
    }

    public Resolution selectedBangunan() {
        tempIdBangunan = getContext().getRequest().getParameter("idBangunan");
        String ekstrak = getContext().getRequest().getParameter("ekstrak");
        LOG.info("----------tempIdBangunan---------" + tempIdBangunan);
        LOG.info("----------ekstrak---------" + ekstrak);
        try {
            readonlyPBBD();
        } catch (Exception e) {
            e.printStackTrace();
        }


//        getContext().getRequest().setAttribute("ekstrak", Boolean.TRUE);
        return new JSP("strata/pbbm/maklumat_jadualpetak_readonly1.jsp").addParameter("tab", "true");
    }

   

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {

        kodNegeri = conf.getProperty("kodNegeri");

//         tempIdBangunan ="8700";
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        if (idPermohonan != null) {
            permohonan = permohonanDAO.findById(idPermohonan);

            pBangunanL = strService.findByIDPermohonan(permohonan.getPermohonanSebelum().getIdPermohonan());
            kGunaPetakL = kodKegunaanPetakDAO.findAll();
            String[] tname = {"permohonan"};
            Object[] model = {permohonan};
            listHakPermohon = hakmilikPermohonanDAO.findByEqualCriterias(tname, model, null);
            kGunaPetakAksesoriL = kodKegunaanPetakAksesoriDAO.findAll();
            senaraiTingkat = new ArrayList<BangunanTingkat>();
            senaraiPetak = new ArrayList<BangunanPetak>();
            senaraipetakAksesori = new ArrayList<BangunanPetakAksesori>();
            List<PermohonanBangunan> pb = new ArrayList<PermohonanBangunan>();
//            pb = permohonan.getSenaraiBangunan();
            pb = strService.findByIDPermohonan(idPermohonan);
            for (PermohonanBangunan bngn : pb) {
                List<BangunanTingkat> t = bngn.getSenaraiTingkat();
                for (BangunanTingkat bt : t) {
                    senaraiTingkat.add(bt);
                    List<BangunanPetak> sb = bt.getSenaraiPetak();
                    for (BangunanPetak bp : sb) {
                        senaraiPetak.add(bp);
//                        List<BangunanPetakAksesori> pk = new ArrayList();
//                        pk = bp.getSenaraiPetakAksesori();
//                        LOG.info(pk.size());
//                        for (BangunanPetakAksesori pk1 : pk) {
//                            senaraipetakAksesori.add(pk1);
//                        }
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
//        stageId = "g_semakkemasukan";
        return stageId;
    }

    
    
    @DefaultHandler
    public Resolution readonlyPBBD() throws ParserConfigurationException, SAXException, IOException {
        LOG.info("--PBBD Read Only calling--:");
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        String msg = getContext().getRequest().getParameter("message");
        String error = getContext().getRequest().getParameter("error");
        if (StringUtils.isNotBlank(msg)) {
            addSimpleMessage(msg);
        }
        if (StringUtils.isNotBlank(error)) {
            addSimpleError(error);
        }

        // class to compare the name of Petak & sort accordingly
        Comparator c = new Comparator<BangunanPetak>() {

            @Override
            public int compare(BangunanPetak o1, BangunanPetak o2) {
//                String[] namaPetak1Pecahan = o1.getNama().split(" ");
//                String[] namaPetak2Pecahan = o2.getNama().split(" ");
                return Integer.parseInt(o1.getNama())
                        - Integer.parseInt(o1.getNama());
            }
        };

        Comparator c2 = new Comparator<BangunanTingkat>() {

            @Override
            public int compare(BangunanTingkat u1, BangunanTingkat u2) {
//                String[] namaTgkt1Pecahan = u1.getNama().split(" ");
//                String[] namaTgkt2Pecahan = u2.getNama().split(" ");
//                return Integer.parseInt(namaTgkt1Pecahan[1])
//                        - Integer.parseInt(namaTgkt2Pecahan[1]);

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

//                String namaBngn1Pecahan = b1.getNama();
//                String namaBngn2Pecahan = b2.getNama();
//                return Integer.parseInt(namaBngn1Pecahan)
//                        - Integer.parseInt(namaBngn2Pecahan);
                String namaBngn1Pecahan = b1.getNama().length() > 1 ? b1.getNama().substring(1) : "0";
                LOG.info("---namaBngn1Pecahan---" + namaBngn1Pecahan);
                String namaBngn2Pecahan = b2.getNama().length() > 1 ? b2.getNama().substring(1) : "0";
                LOG.info("---namaBngn2Pecahan---" + namaBngn2Pecahan);
                return Integer.parseInt(namaBngn1Pecahan)
                        - Integer.parseInt(namaBngn2Pecahan);
            }
        };

        LOG.info("---pBangunanList---");
        Permohonan permohonanRMH1 = permohonanDAO.findById(idPermohonan);
        LOG.info("---permohonan--RMH1A----" + permohonanRMH1.getIdPermohonan());
        LOG.info("---permohonan--PBBD----" + permohonanRMH1.getPermohonanSebelum().getIdPermohonan());
        pBangunanL = strService.findByIDPermohonan(permohonanRMH1.getPermohonanSebelum().getIdPermohonan());
        LOG.info("---pBangunanL---" + pBangunanL);
        if (pBangunanL != null) {
            permohonan = permohonanDAO.findById(permohonanRMH1.getPermohonanSebelum().getIdPermohonan());

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
//                            listPetakaksr = ptk1.getSenaraiPetakAksesori();
                            listPetakaksr = strService.findPtkAksrByIDPetak(ptk1.getIdPetak());
                            LOG.info("--listPetakaksr--" + listPetakaksr);
                            petakL = strService.findByIDPetak(String.valueOf(ptk1.getIdPetak()));
                            Collections.sort(listPetakaksr, c3);
                            ptk1.setSenaraiPetakAksesori(listPetakaksr);
                        }
                    }
                }
            }
        }

       LOG.info("---pBangunanLandParcel---");
        Permohonan permohonanRMH1A = permohonanDAO.findById(idPermohonan);
        LOG.info("---permohonan--RMH1A----" + permohonanRMH1A.getIdPermohonan());
        LOG.info("---permohonan--PBBD----" + permohonanRMH1A.getPermohonanSebelum().getIdPermohonan());
        pBangunanLandParcel = strService.findByIDPermohonanByLandparcel(permohonanRMH1A.getPermohonanSebelum().getIdPermohonan());
        LOG.info("---pBangunanLandParcel---" + pBangunanLandParcel);
        if (pBangunanLandParcel != null) {
            permohonan = permohonanDAO.findById(permohonanRMH1A.getPermohonanSebelum().getIdPermohonan());           

            Collections.sort(pBangunanLandParcel, c4);
            for (PermohonanBangunan p : pBangunanLandParcel) {
                permohonan.setSenaraiBangunan(pBangunanLandParcel);
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
//                            listPetakaksr = ptk1.getSenaraiPetakAksesori();
                            listPetakaksr = strService.findPtkAksrByIDPetak(ptk1.getIdPetak());
                            LOG.info("--listPetakaksr--" + listPetakaksr);
                            petakL = strService.findByIDPetak(String.valueOf(ptk1.getIdPetak()));
                            Collections.sort(listPetakaksr, c3);
                            ptk1.setSenaraiPetakAksesori(listPetakaksr);
                        }
                    }
                }
            }
        }

        //to get data from mohon_harta_bersama
        Permohonan permohonan1 = permohonanDAO.findById(idPermohonan);        
            LOG.info("--permohonan1--" + permohonan1);
            LOG.info("--permohonan Sebulam--" + permohonan1.getPermohonanSebelum().getIdPermohonan());
            mc = strService.findBdn(permohonan1.getPermohonanSebelum().getIdPermohonan());
            LOG.info("--mc--" + mc);
            if (mc != null) {
                mohonSkim = strService.findSkimByBdnUrusan(mc.getIdBadan());               
            }
            if (mohonSkim != null) {
                listpermohonanHartaBersama = strService.findHartaBersamaByidSkim(mohonSkim.getIdSkim());
                LOG.info("--listpermohonanHartaBersama--" + listpermohonanHartaBersama);
            }
        //end

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
//                return new JSP("strata/pecahPetak/tambahBangunan.jsp").addParameter("tab", "true");
                return new JSP("strata/pbbm/maklumat_jadualpetak_readonly1.jsp").addParameter("tab", "true");
            }

            getContext().getRequest().setAttribute("ekstrak", Boolean.TRUE);
            return new JSP("strata/pbbm/maklumat_jadualpetak_readonly1.jsp").addParameter("tab", "true");
        } else {
            getContext().getRequest().setAttribute("ekstrak", Boolean.FALSE);
            if (permohonan.getPermohonanSebelum() != null) {
                return new JSP("strata/pbbm/maklumat_jadualpetak_readonly1.jsp").addParameter("tab", "true");
            } else {
//                return new JSP("strata/pecahPetak/tambahBangunan.jsp").addParameter("tab", "true");
                return new JSP("strata/pbbm/maklumat_jadualpetak_readonly1.jsp").addParameter("tab", "true");
            }
        }
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

    public List<PermohonanBangunan> getpBangunanLandParcel() {
        return pBangunanLandParcel;
    }

    public void setpBangunanLandParcel(List<PermohonanBangunan> pBangunanLandParcel) {
        this.pBangunanLandParcel = pBangunanLandParcel;
    }

    public PermohonanSkim getMohonSkim() {
        return mohonSkim;
    }

    public void setMohonSkim(PermohonanSkim mohonSkim) {
        this.mohonSkim = mohonSkim;
    }
    
}
