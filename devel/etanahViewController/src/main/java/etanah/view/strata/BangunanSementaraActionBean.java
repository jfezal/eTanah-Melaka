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
import etanah.dao.KodKategoriBangunanDAO;
import etanah.dao.KodKegunaanPetakAksesoriDAO;
import etanah.dao.KodKegunaanPetakDAO;
import etanah.dao.PermohonanBangunanDAO;
import etanah.dao.PermohonanDAO;
import etanah.dao.PermohonanPihakDAO;
import etanah.dao.PihakDAO;
import etanah.model.BangunanPetak;
import etanah.model.BangunanPetakAksesori;
import etanah.model.BangunanTingkat;
import etanah.model.Dokumen;
import etanah.model.HakmilikPermohonan;
import etanah.model.InfoAudit;
import etanah.model.KodKategoriBangunan;
import etanah.model.KodKegunaanPetak;
import etanah.model.KodKegunaanPetakAksesori;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.model.PermohonanBangunan;
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
import oracle.bpel.services.workflow.StaleObjectException;
import oracle.bpel.services.workflow.WorkflowException;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.w3c.dom.*;
import org.xml.sax.SAXException;

/**
 *
 * @author zadhirul.farihim
 */
@UrlBinding("/strata/urusan_pbs1")
public class BangunanSementaraActionBean extends BasicTabActionBean {

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
    KodKategoriBangunanDAO kodkatbngnDAO;
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
    private PermohonanBangunan bangunan;
    private Permohonan permohonan;
    private HakmilikPermohonan hakmilikpermohonan;
    @Inject
    PermohonanBangunanDAO permohonanBangunanDAO;
    @Inject
    StrataPtService strService;
    @Inject
    private etanah.Configuration conf;
    private static Document doc = null;
    private List<BangunanPetak> petakL;
    private List<BangunanTingkat> tingkatL;
    private List<PermohonanBangunan> pBangunanL;
    private List<PermohonanBangunan> mhnBngnListP;
    private List<PermohonanBangunan> mhnBngnListM;
    private List<BangunanPetakAksesori> petakAksesoriL;
    private List<KodKegunaanPetak> kGunaPetakL;
    private List<KodKegunaanPetakAksesori> kGunaPetakAksesoriL;
    private BangunanTingkat bangunanTingkat;
    private BangunanPetak bangunanPetak;
    private List<BangunanPetak> senaraiPetak;
    private List<BangunanPetakAksesori> senaraipetakAksesori;
    private List<BangunanTingkat> senaraiTingkat;
    private KodKategoriBangunan kodKategoriBngn;
    private List<KodKategoriBangunan> senaraiKodKatgBngn;
    private String[] petakLuas;
    private String[] petakSyer;
    private String[] petakKegunaan;
    private String[] petakKegunaanAksr;
    private String[] lokasiAksr;
    private String[] tingkatMezanin;
    private static final Logger LOG = Logger.getLogger(BangunanSementaraActionBean.class);
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
    private List<HakmilikPermohonan> listHakPermohon;
    private String tempIdBangunan;
    private String tempIdTingkat;
    private String tempIdPetak;
    private int jumPetak = 0;
    private Pengguna pengguna;
    private String nama;
    private String bilPetak;
    private String bilTingkat;
    private String unitSyer;
    private Date trhBngnSiap;
    private PermohonanBangunan bngn;
    private String kodKatBngn;
    private String untukKediaman;
    private String untukPejabat;
    private String untukNiaga;
    private String untukLain;
    private String namaLain;
    private String[] namaBngnArray;
    private String[] bilPetakArray;
    private String[] unitSyerArray;
    private Date[] trhSiapArray;

    public Resolution selectedPetak() {
        tempIdBangunan = getContext().getRequest().getParameter("idBangunan");
        tempIdTingkat = getContext().getRequest().getParameter("idTingkat");
        tempIdPetak = getContext().getRequest().getParameter("idPetak");

        System.out.println("----------selectedPetak---------" + tempIdBangunan);
        System.out.println("----------selectedPetak---------" + tempIdTingkat);
        System.out.println("----------selectedPetak---------" + tempIdPetak);
        try {
            showForm1();
        } catch (Exception e) {
            e.printStackTrace();
        }
//        getContext().getRequest().setAttribute("ekstrak", Boolean.TRUE);
        return new JSP("strata/pbbm/maklumat_jadualpetak.jsp").addParameter("tab", "true");
    }

    public Resolution selectedTingkat() {
        tempIdBangunan = getContext().getRequest().getParameter("idBangunan");
        tempIdTingkat = getContext().getRequest().getParameter("idTingkat");

        System.out.println("----------selectedTingkat---------" + tempIdBangunan);
        System.out.println("----------selectedTingkat---------" + tempIdTingkat);
        try {
            showForm1();
        } catch (Exception e) {
            e.printStackTrace();
        }
//        getContext().getRequest().setAttribute("ekstrak", Boolean.TRUE);
        return new JSP("strata/pbbm/maklumat_jadualpetak.jsp").addParameter("tab", "true");
    }

    public Resolution selectedBangunan() {
        tempIdBangunan = getContext().getRequest().getParameter("idBangunan");
        System.out.println("----------tempIdBangunan---------" + tempIdBangunan);
        try {
            showForm1();
        } catch (Exception e) {
            e.printStackTrace();
        }

//        getContext().getRequest().setAttribute("ekstrak", Boolean.TRUE);
        return new JSP("strata/pbbm/maklumat_jadualpetak.jsp").addParameter("tab", "true");
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
            Collections.sort(pBangunanL, c4);
            for (PermohonanBangunan p : pBangunanL) {
                permohonan.setSenaraiBangunan(pBangunanL);
                if (p.getSenaraiTingkat() != null) {
                    List<BangunanTingkat> listTingkat = p.getSenaraiTingkat();
                    Collections.sort(listTingkat, c2);
                    p.setSenaraiTingkat(listTingkat);
                    for (BangunanTingkat tgkt : p.getSenaraiTingkat()) {
                        List<BangunanPetak> listPetak = tgkt.getSenaraiPetak();
                        Collections.sort(listPetak, c);
                        tgkt.setSenaraiPetak(listPetak);
                        for (BangunanPetak ptk1 : tgkt.getSenaraiPetak()) {
                            List<BangunanPetakAksesori> listPetakaksr = ptk1.getSenaraiPetakAksesori();
                            petakL = strService.findByIDPetak(String.valueOf(ptk1.getIdPetak()));
                            Collections.sort(listPetakaksr, c3);
                            ptk1.setSenaraiPetakAksesori(listPetakaksr);
                        }
                    }
                }
            }
        }

        Dokumen d = strService.findDok(idPermohonan, "JPP");
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
                return new JSP("strata/bngn_sementara/maklumat_jadualpetakPBS.jsp").addParameter("tab", "true");
            }

            getContext().getRequest().setAttribute("ekstrak", Boolean.TRUE);
            return new JSP("strata/bngn_sementara/maklumat_jadualpetakPBS.jsp").addParameter("tab", "true");
        } else {
            return new JSP("strata/bngn_sementara/maklumat_jadualpetakPBS.jsp").addParameter("tab", "true");

        }
    }

    public Resolution showForm() {
        String msg = getContext().getRequest().getParameter("message");
        String error = getContext().getRequest().getParameter("error");
        if (StringUtils.isNotBlank(msg)) {
            addSimpleMessage(msg);
        }
        if (StringUtils.isNotBlank(error)) {
            addSimpleError(error);
        }
        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        rehydrate();
        return new JSP("strata/bngn_sementara/maklumat_jadualpetakPBS.jsp").addParameter("tab", "true");
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
        getContext().getRequest().setAttribute("ekstrak", Boolean.FALSE);
        return new JSP("strata/pbbm/maklumat_jadualpetak.jsp").addParameter("tab", "true");
    }

    public Resolution showPHPC() {
        return new JSP("strata/pecahPetak/tambahBangunan.jsp").addParameter("tab", "true");
    }

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {
//         tempIdBangunan ="8700";

        mhnBngnListP = new ArrayList<PermohonanBangunan>();
        mhnBngnListM = new ArrayList<PermohonanBangunan>();

        pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);

        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = permohonanDAO.findById(idPermohonan);
        mhnBngnListP = strService.findByKodKatBngn(idPermohonan, "P");
        LOG.info("find mhn bngn P");
        mhnBngnListM = strService.findByKodKatBngn(idPermohonan, "B");
        LOG.info("find mhn bngn B");




        Comparator c = new Comparator<PermohonanBangunan>() {

            @Override
            public int compare(PermohonanBangunan b1, PermohonanBangunan b2) {

                String namaBngn1Pecahan = b1.getNama();
                String namaBngn2Pecahan = b2.getNama();
                return Integer.parseInt(namaBngn1Pecahan)
                        - Integer.parseInt(namaBngn2Pecahan);
            }
        };

        Collections.sort(mhnBngnListP, c);
        Collections.sort(mhnBngnListM, c);



        if (idPermohonan != null) {

            permohonan = permohonanDAO.findById(idPermohonan);
            pBangunanL = strService.findByIDPermohonan(idPermohonan);
            kGunaPetakL = kodKegunaanPetakDAO.findAll();
            String[] tname = {"permohonan"};
            Object[] model = {permohonan};
            listHakPermohon = hakmilikPermohonanDAO.findByEqualCriterias(tname, model, null);
            kGunaPetakAksesoriL = kodKegunaanPetakAksesoriDAO.findAll();
            senaraiTingkat = new ArrayList<BangunanTingkat>();
            senaraiPetak = new ArrayList<BangunanPetak>();
            senaraipetakAksesori = new ArrayList<BangunanPetakAksesori>();

            senaraiKodKatgBngn = kodkatbngnDAO.findAll();



            List<PermohonanBangunan> pb = permohonan.getSenaraiBangunan();
            for (PermohonanBangunan bngn : pb) {
                List<BangunanTingkat> t = bngn.getSenaraiTingkat();
                for (BangunanTingkat bt : t) {
                    senaraiTingkat.add(bt);
                    List<BangunanPetak> sb = bt.getSenaraiPetak();
                    for (BangunanPetak bp : sb) {
                        senaraiPetak.add(bp);
                        List<BangunanPetakAksesori> pk = bp.getSenaraiPetakAksesori();
                        for (BangunanPetakAksesori pk1 : pk) {
                            senaraipetakAksesori.add(pk1);
                        }
                    }
                }
            }

            if (mhnBngnListM.size() > 0) {
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
    }

    public Resolution simpanBngn() {

        bngn = new PermohonanBangunan();
        bngn.setPermohonan(permohonan);
        bngn.setCawangan(pengguna.getKodCawangan());
        bngn.setInfoAudit(strService.getInfo(pengguna));
        kodKategoriBngn = new KodKategoriBangunan();
        kodKategoriBngn = kodkatbngnDAO.findById(kodKatBngn);

        String kod = kodKategoriBngn.getKod();
        LOG.info("kod kat bngn : " + kod);

        String error = "";
        String msg = "";
        PermohonanBangunan pb = strService.findByNama(nama, idPermohonan);

        if ((pb != null)) {
            error = "Nama bangunan sudah wujud dalam pangkalan data.";
            System.out.println(error);
        } else {

            if (kod.equals("P")) {
                System.out.println("kod = P");
                bngn.setNama(nama);
                bngn.setNamaLain(namaLain);
                bngn.setBilanganPetak(Integer.parseInt(bilPetak));
                bngn.setBilanganTingkat(Integer.parseInt(bilTingkat));
                bngn.setSyerBlok(Integer.parseInt(unitSyer));
                //        bngn.setTrhBngnSiap
                bngn.setKodKategoriBangunan(kodKategoriBngn);

                if (untukLain != null) {
                    bngn.setUntukKediaman(untukKediaman.charAt(0));
                    LOG.info("kediaman : " + untukKediaman.charAt(0));
                }
                if (untukLain != null) {
                    bngn.setUntukPejabat(untukPejabat.charAt(0));
                    LOG.info("pejabat : " + untukPejabat.charAt(0));
                }
                if (untukLain != null) {
                    bngn.setUntukPerniagaan(untukNiaga.charAt(0));
                    LOG.info("niaga : " + untukNiaga.charAt(0));
                }
                if (untukLain != null) {
                    bngn.setUntukKegunaanLain(untukLain);
                }

                strService.saveMohonBangunan(bngn);
                LOG.info("Simpan Bangunan Sementara Berjaya");
                msg = "Maklumat telah berjaya disimpan.";

            } else {
                System.out.println(kod);
                bngn.setNama(nama);
                bngn.setNamaLain(namaLain);
                bngn.setBilanganPetak(Integer.parseInt(bilPetak));
                bngn.setBilanganTingkat(Integer.parseInt(bilTingkat));
                bngn.setSyerBlok(Integer.parseInt(unitSyer));
                bngn.setKodKategoriBangunan(kodKategoriBngn);
                if (untukLain != null) {
                    bngn.setUntukKediaman(untukKediaman.charAt(0));
                    LOG.info("kediaman : " + untukKediaman.charAt(0));
                }
                if (untukLain != null) {
                    bngn.setUntukPejabat(untukPejabat.charAt(0));
                    LOG.info("pejabat : " + untukPejabat.charAt(0));
                }
                if (untukLain != null) {
                    bngn.setUntukPerniagaan(untukNiaga.charAt(0));
                    LOG.info("niaga : " + untukNiaga.charAt(0));
                }
                if (untukLain != null) {
                    bngn.setUntukKegunaanLain(untukLain);

                }
                strService.simpanBangunan(bngn);
                LOG.info("Simpan Bangunan Kekal Berjaya");
                LOG.debug(bngn.getNama());
                ArrayList<BangunanTingkat> listTingkat = new ArrayList<BangunanTingkat>();
                for (int i = 0; i < bngn.getBilanganTingkat(); i++) {
                    LOG.debug("Bilangan Tingkat = " + i);
                    BangunanTingkat tgkt = new BangunanTingkat();
                    tgkt.setBangunan(bngn);
                    tgkt.setNama(String.valueOf(i + 1));
                    tgkt.setTingkat(i + 1);
                    tgkt.setInfoAudit(strService.getInfo(pengguna));
                    listTingkat.add(tgkt);
                    strService.simpanBangunanTingkat(tgkt);

                }
                bngn.setSenaraiTingkat(listTingkat);
                strService.updateBangunan(bngn);
                LOG.debug(bngn.getIdBangunan());
                bngn = permohonanBangunanDAO.findById(bngn.getIdBangunan());
                LOG.debug(bngn.getSenaraiTingkat().size());
                msg = "Maklumat telah berjaya disimpan.";
            }
        }

        return new RedirectResolution(BangunanSementaraActionBean.class, "showForm1").addParameter("error", error).addParameter("message", msg);


    }

    public Resolution kemaskiniBngnSementara() {

        String msg = getContext().getRequest().getParameter("message");
        String error = getContext().getRequest().getParameter("error");
        if (StringUtils.isNotBlank(msg)) {
            addSimpleMessage(msg);
        }
        if (StringUtils.isNotBlank(error)) {
            addSimpleError(error);
        }


        LOG.info("ID:: " + idPermohonan);

        for (PermohonanBangunan pb2 : mhnBngnListP) {
            LOG.info("NAMA:: " + pb2.getNama());
            PermohonanBangunan pb = strService.findByNama(pb2.getNama(), idPermohonan);
            if ((pb != null)) {
                error = "Nama bangunan sudah wujud dalam pangkalan data.";
                System.out.println(error);
            } else {
                strService.saveMohonBangunan(pb2);
                msg = "Maklumat telah berjaya dikemaskini";
                LOG.info("update berjaya");
            }

        }



        return new RedirectResolution(BangunanSementaraActionBean.class, "showForm1").addParameter("error", error).addParameter("message", msg);

    }

    public Resolution tambahBngnSementara() {
        String msg = getContext().getRequest().getParameter("message");
        String error = getContext().getRequest().getParameter("error");
        if (StringUtils.isNotBlank(msg)) {
            addSimpleMessage(msg);
        }
        if (StringUtils.isNotBlank(error)) {
            addSimpleError(error);
        }
        rehydrate();
        return new JSP("strata/bngn_sementara/tambahBangunanPBS.jsp").addParameter("popup", "true");
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
        System.out.println("Nama Bangunan :" + nama);

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

        return new RedirectResolution(BangunanSementaraActionBean.class, "showForm1").addParameter("error", error).addParameter("message", msg);

    }

    public Resolution tambahPetak() {

        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = permohonanDAO.findById(idPermohonan);
        LOG.info("ID:: " + idPermohonan);
        List<PermohonanBangunan> pb1 = permohonan.getSenaraiBangunan();
        for (PermohonanBangunan pb2 : mhnBngnListP) {
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

        return new RedirectResolution(BangunanSementaraActionBean.class, "showForm1");
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
        return new RedirectResolution(BangunanSementaraActionBean.class, "showForm1");
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
        return new RedirectResolution(BangunanSementaraActionBean.class, "showForm1");
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
        return new RedirectResolution(BangunanSementaraActionBean.class, "showForm1");
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
        return new RedirectResolution(BangunanSementaraActionBean.class, "showForm1");
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

        return new RedirectResolution(BangunanSementaraActionBean.class, "showForm1");
    }

    public Resolution kemaskini() {

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


        return new RedirectResolution(BangunanSementaraActionBean.class, "showForm1").addParameter("error", error).addParameter("message", msg);
    }

    public boolean validateXML(NodeList n1) {
        boolean pass = false;
        String hakmilik = new String();
        for (int i = 0; i < n1.getLength(); i++) {
            hakmilik = n1.item(i).getAttributes().getNamedItem("negeri").getNodeValue()
                    + n1.item(i).getAttributes().getNamedItem("daerah").getNodeValue()
                    + n1.item(i).getAttributes().getNamedItem("seksyen").getNodeValue();
//
//            "no_fail_ptg"
//                    "diukur_oleh"
//                    "pengukur_noic"
//                    ="123456789012" tarikh_siap="2008-05-10" bilangan_petak="291" bilangan_aksesori="291" tarikh_terima_sijil_akuan="2010-04-13" tarikh_lulus_permohonan="2010-06-10" bayaran_ukur="" bayaran_pelan="" ulasan_jupem="" nama_pemaju="" nama_perbadanan_pengurusan="" nama_projek="" no_ruj_jubl="JX597-2" no_ruj_ljt="338/00066" no_fail_ukursemula="PUBLWP260_2008" kodtujuanukur="18" jumlah_unit_syer="0">

            /**
             *   negeri="14"
            daerah="00"
            mukim="06"
            seksyen="000"
            lot="43905"
            skim="(S)-1"
            pa="PA113103"
            no_hakmilik="PN46013"
             *
             */
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
            return new RedirectResolution(BangunanSementaraActionBean.class, "showForm1").addParameter("error", error).addParameter("message", msg);

        } else {

            List<PermohonanBangunan> pb = permohonan.getSenaraiBangunan();
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
                List<PermohonanBangunan> pb1 = permohonan.getSenaraiBangunan();
                if (validateXML(n1)) {
                }
                String diukur_oleh,
                        pengukur_noic,
                        ulasan_jupem,
                        nama_pemaju,
                        nama_perbadanan_pengurusan,
                        nama_projek,
                        no_ruj_jubl,
                        no_ruj_ljt,
                        no_fail_ukursemula,
                        kodtujuanukur,
                        skim;
                int bilangan_petak;
                int bilangan_aksesori;
                Date tarikh_terima_sijil_akuan;
                Date tarikh_lulus_permohonan;
                Date tarikh_siap;
                BigDecimal bayaran_ukur;
                BigDecimal bayaran_pelan;

                BigDecimal jumlah_unit_syer;

                for (int i = 0; i < n1.getLength(); i++) {
                    diukur_oleh = (n1.item(i).getAttributes().getNamedItem("diukur_oleh").getNodeValue());
                    pengukur_noic = (n1.item(i).getAttributes().getNamedItem("pengukur_noic").getNodeValue());
                    ulasan_jupem = (n1.item(i).getAttributes().getNamedItem("ulasan_jupem").getNodeValue());
                    nama_pemaju = (n1.item(i).getAttributes().getNamedItem("nama_pemaju").getNodeValue());
                    nama_perbadanan_pengurusan = (n1.item(i).getAttributes().getNamedItem("nama_perbadanan_pengurusan").getNodeValue());
                    nama_projek = (n1.item(i).getAttributes().getNamedItem("nama_projek").getNodeValue());
                    no_ruj_jubl = (n1.item(i).getAttributes().getNamedItem("no_ruj_jubl").getNodeValue());
                    no_ruj_ljt = (n1.item(i).getAttributes().getNamedItem("no_ruj_ljt").getNodeValue());
                    no_fail_ukursemula = (n1.item(i).getAttributes().getNamedItem("no_fail_ukursemula").getNodeValue());
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
//                    if(n1.item(i).getAttributes().getNamedItem("bayaran_ukur").getNodeValue()!=null)bayaran_ukur = BigDecimal.valueOf(Double.valueOf(n1.item(i).getAttributes().getNamedItem("bayaran_ukur").getNodeValue()));
//                    if(n1.item(i).getAttributes().getNamedItem("bayaran_pelan").getNodeValue()!=null)bayaran_pelan = BigDecimal.valueOf(Double.valueOf(n1.item(i).getAttributes().getNamedItem("bayaran_pelan").getNodeValue()));
//                    if(n1.item(i).getAttributes().getNamedItem("jumlah_unit_syer").getNodeValue()!=null)jumlah_unit_syer = BigDecimal.valueOf(Double.valueOf(n1.item(i).getAttributes().getNamedItem("jumlah_unit_syer").getNodeValue()));

                }

                int a;
                for (a = 0; a < n2.getLength(); a++) {

                    PermohonanBangunan bgn = new PermohonanBangunan();
                    jumPetak = 0;
                    bgn.setPermohonan(permohonan);
                    bgn.setInfoAudit(infoAudit);
                    bgn.setCawangan(peng.getKodCawangan());

//                    bgn.setNama(n2.item(a).getAttributes().getNamedItem("bangunanno").getNodeValue());
                    bgn.setNama(getIntegerValue(n2.item(a).getAttributes().getNamedItem("bangunanno").getNodeValue()));
                    bgn.setBilanganTingkat(Integer.valueOf(n2.item(a).getAttributes().getNamedItem("no_of_tingkat").getNodeValue()));
//                    bgn.setKategoriBangunan((n2.item(a).getAttributes().getNamedItem("blocktype").getNodeValue()));
                    bgn.setSyerBlok(Integer.valueOf(n2.item(a).getAttributes().getNamedItem("unitsyer").getNodeValue()));
                    strService.simpanBangunan(bgn);
                    msg = "Maklumat telah berjaya disimpan.";
                    extrakTgkt(n3, n4, bgn.getIdBangunan());
                    bgn.setBilanganPetak(jumPetak);
                    strService.simpanBangunan(bgn);

                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                return new ErrorResolution(500,
                        "Fail " + docPath + d.getNamaFizikal() + " tidak dijumpai");
            }
        }
        return new RedirectResolution(BangunanSementaraActionBean.class, "showForm1").addParameter("error", error).addParameter("message", msg);

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
        return s;
    }

    public Resolution extrakTgkt(NodeList n3, NodeList n4, long IdBangunan) {

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
        for (int b = hv; b < bgn.getBilanganTingkat() + hv; b++) {

            LOG.debug("Bilangan Tingkat = " + i);
            tgkt = new BangunanTingkat();
            tgkt.setBangunan(bgn);
            tgkt.setNama(String.valueOf(i + 1));
            tgkt.setTingkat(i + 1);
            tgkt.setBilanganPetak(Integer.parseInt(n3.item(b).getAttributes().getNamedItem("no_of_petak").getNodeValue()));
            jumPetak = jumPetak + tgkt.getBilanganPetak();
            tgkt.setInfoAudit(infoAudit);
            listTingkat.add(tgkt);
            strService.simpanBangunanTingkat(tgkt);
            extrakPetak(n4, tgkt.getIdTingkat(), bgn.getIdBangunan());
            i++;
        }
        hv = bgn.getBilanganTingkat();
        bgn.setSenaraiTingkat(listTingkat);
        strService.updateBangunan(bgn);

        return new RedirectResolution(BangunanSementaraActionBean.class, "showForm1").addParameter("error", error).addParameter("message", msg);

    }

    public Resolution extrakPetak(NodeList n4, long idTingkat, long IdBangunan) {

        String error = "";
        String msg = "";
        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        InfoAudit infoAudit = new InfoAudit();
        infoAudit.setDimasukOleh(peng);
        infoAudit.setTarikhMasuk(new java.util.Date());
        bangunanTingkat = bangunanTingkatDAO.findById(idTingkat);


        int c;
        ArrayList<BangunanPetak> listPetak = new ArrayList<BangunanPetak>();
        for (c = 0; c < bangunanTingkat.getBilanganPetak(); c++) {
            InfoAudit infoAudit1 = new InfoAudit();
            infoAudit1.setDimasukOleh(peng);
            infoAudit1.setTarikhMasuk(new java.util.Date());
            LOG.debug("Bilangan Petak = " + c);
            BangunanPetak petak = new BangunanPetak();
            petak.setNama(String.valueOf(sum4 + c + 1));
            petak.setInfoAudit(infoAudit1);
            petak.setTingkat(bangunanTingkat);
            BigDecimal bd = new BigDecimal(n4.item(c).getAttributes().getNamedItem("a_area").getNodeValue());
            petak.setLuas(bd);
            KodKegunaanPetak k = new KodKegunaanPetak();
            k.setKod(n4.item(c).getAttributes().getNamedItem("kodkegunaanpetak").getNodeValue());
            petak.setKegunaan(k);
            petak.setSyer(Integer.valueOf(n4.item(c).getAttributes().getNamedItem("unitsyer").getNodeValue()));
            listPetak.add(petak);
            strService.simpanPetak(petak);
        }
        sum4 = sum4 + bangunanTingkat.getBilanganPetak();

        return new RedirectResolution(BangunanSementaraActionBean.class, "showForm1").addParameter("error", error).addParameter("message", msg);

    }

    /* getter and setter */
    public String getBilPetak() {
        return bilPetak;
    }

    public void setBilPetak(String bilPetak) {
        this.bilPetak = bilPetak;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public Date getTrhBngnSiap() {
        return trhBngnSiap;
    }

    public void setTrhBngnSiap(Date trhBngnSiap) {
        this.trhBngnSiap = trhBngnSiap;
    }

    public String getUnitSyer() {
        return unitSyer;
    }

    public void setUnitSyer(String unitSyer) {
        this.unitSyer = unitSyer;
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

    public String getKodKatBngn() {
        return kodKatBngn;
    }

    public void setKodKatBngn(String kodKatBngn) {
        this.kodKatBngn = kodKatBngn;
    }

    public String getBilTingkat() {
        return bilTingkat;
    }

    public void setBilTingkat(String bilTingkat) {
        this.bilTingkat = bilTingkat;
    }

    public List<PermohonanBangunan> getMhnBngnListP() {
        return mhnBngnListP;
    }

    public void setMhnBngnListP(List<PermohonanBangunan> mhnBngnListP) {
        this.mhnBngnListP = mhnBngnListP;
    }

    public List<PermohonanBangunan> getMhnBngnListM() {
        return mhnBngnListM;
    }

    public void setMhnBngnListM(List<PermohonanBangunan> mhnBngnListM) {
        this.mhnBngnListM = mhnBngnListM;
    }

    public List<KodKategoriBangunan> getSenaraiKodKatgBngn() {
        return senaraiKodKatgBngn;
    }

    public void setSenaraiKodKatgBngn(List<KodKategoriBangunan> senaraiKodKatgBngn) {
        this.senaraiKodKatgBngn = senaraiKodKatgBngn;
    }

    public String getUntukKediaman() {
        return untukKediaman;
    }

    public void setUntukKediaman(String untukKediaman) {
        this.untukKediaman = untukKediaman;
    }

    public String getUntukLain() {
        return untukLain;
    }

    public void setUntukLain(String untukLain) {
        this.untukLain = untukLain;
    }

    public String getUntukNiaga() {
        return untukNiaga;
    }

    public void setUntukNiaga(String untukNiaga) {
        this.untukNiaga = untukNiaga;
    }

    public String getUntukPejabat() {
        return untukPejabat;
    }

    public void setUntukPejabat(String untukPejabat) {
        this.untukPejabat = untukPejabat;
    }

    public String getNamaLain() {
        return namaLain;
    }

    public void setNamaLain(String namaLain) {
        this.namaLain = namaLain;
    }

    public String[] getNamaBngnArray() {
        return namaBngnArray;
    }

    public void setNamaBngnArray(String[] namaBngnArray) {
        this.namaBngnArray = namaBngnArray;
    }
}
