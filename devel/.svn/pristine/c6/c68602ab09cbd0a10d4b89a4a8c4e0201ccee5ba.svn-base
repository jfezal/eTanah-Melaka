package etanah.view.strata;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.dao.KodKategoriBangunanDAO;
import etanah.dao.KodKegunaanBangunanDAO;
import etanah.dao.KodKegunaanPetakDAO;
import etanah.dao.PermohonanBangunanDAO;
import etanah.dao.PermohonanDAO;
import etanah.dao.PermohonanStrataDAO;
import etanah.dao.PermohonanSkimDAO;
import etanah.model.HakmilikPermohonan;
import etanah.model.InfoAudit;
import etanah.model.KodHartaBersama;
import etanah.model.KodKategoriBangunan;
import etanah.model.KodKegunaanPetak;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.model.PermohonanBangunan;
import etanah.model.PermohonanStrata;
import etanah.model.PermohonanSkim;
import etanah.model.Pihak;
import etanah.sequence.GeneratorNoSijilMC;
import etanah.service.StrataPtService;
import etanah.service.common.HakmilikPermohonanService;
import etanah.view.BasicTabActionBean;
import etanah.view.etanahActionBeanContext;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.RedirectResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import java.util.Comparator;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @faidzal
 */
@UrlBinding("/strata/bangunanPKBK")
public class BangunanKhasActionBean extends AbleActionBean {

    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    PermohonanBangunanDAO permohonanBangunanDAO;
    @Inject
    KodKegunaanBangunanDAO kodKegunaanBangunanDAO;
    @Inject
    StrataPtService strService;
    @Inject
    CommonService comm;
    @Inject
    KodKegunaanPetakDAO kodKegunaanPetakDAO;
    @Inject
    KodKategoriBangunanDAO kodkatbngnDAO;
    @Inject
    PermohonanStrataDAO permohonanStrataDAO;
    @Inject
    GeneratorNoSijilMC generatorNoSijilMC;
    @Inject
    HakmilikPermohonanService hakmilikPermohonanServ;
    @Inject
    PermohonanSkimDAO mohonSkimDAO;
    @Inject
    etanah.Configuration conf;
    List<SenaraiHargaPKKR> listHarga = new ArrayList();
    private PermohonanStrata mohonStrata = new PermohonanStrata();
    private Permohonan permohonan = new Permohonan();
    private PermohonanSkim mohonSkim = new PermohonanSkim();
    private PermohonanBangunan mohonBngn = new PermohonanBangunan();
    private String cfNoSijil;
    private String cfTarikhKeluar;
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    private static final Logger LOG = Logger.getLogger(BangunanKhasActionBean.class);
    private KodKegunaanPetak kodGunaPetak;
    private KodHartaBersama kodHarta;
    private KodHartaBersama hartaBsama;
    private String hartaBsama2;
    private List<String> senaraiHarta;
    private String bilBangunan;
    private String namaLain;
    private String gunaPetak;
    private String bilTingkat;
    private String bilPtk;
    private String bilTgkt;
    private String tarikhBgnSiap;
    private String tarikhSiap;
    private String bilBilik;
    private String bilPetak;
    private String lokasiBangunan;
    private String hargaPetak;
    private String blok;
    private String namaSkim;
    private String gunaBngn;
    private String lainLain;
    private String[] hartaBersama = null;
    private PermohonanBangunan mhnBngn;
    private List<PermohonanBangunan> senaraiMhnBngn;
    private List<KodHartaBersama> senaraikodHartaBersama;
    private String idBangunan;
    private String updatenamaLain;
    private String updatebilPetak;
    private String updatebilTgkt;
    private String updatebilBilik;
    private String idStrata;
    private List<KodHartaBersama> senaraiKodHarta;
    private String strKodGunaPetak;
    private List<String> checked;
    private String kodNegeri;

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {
        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        kodNegeri = conf.getProperty("kodNegeri");
        permohonan = permohonanDAO.findById(idPermohonan);

        mohonStrata = strService.findID(idPermohonan);
        if (mohonStrata != null) {
            if (mohonStrata.getTarikhSiap() != null) {
                tarikhSiap = sdf.format(mohonStrata.getTarikhSiap());
                cfTarikhKeluar = sdf.format(mohonStrata.getCfTarikhKeluar());
                cfNoSijil = mohonStrata.getCfNoSijil();
                namaSkim = mohonStrata.getNamaSkim();
                hargaPetak = String.valueOf(mohonStrata.getHargaPetak());
                lokasiBangunan = mohonStrata.getLaporanLokasi();
                kodGunaPetak = strService.findGunaPetakByNama(mohonStrata.getLaporanKategoriBangunani());
                if (kodGunaPetak != null) {
                    strKodGunaPetak = kodGunaPetak.getKod();
                }
                //add kemudahan from mohon_strata into list senaraiHarta
                if (kodNegeri.equals("05")) {
                    senaraiHarta = new ArrayList();
                    senaraiHarta.add(mohonStrata.getLaporanKemudahan1());
                    senaraiHarta.add(mohonStrata.getLaporanKemudahan2());
                    senaraiHarta.add(mohonStrata.getLaporanKemudahan3());
                    senaraiHarta.add(mohonStrata.getLaporanKemudahan4());
                    senaraiHarta.add(mohonStrata.getLaporanKemudahan5());
                    senaraiHarta.add(mohonStrata.getLaporanKemudahan6());
                    senaraiHarta.add(mohonStrata.getLaporanKemudahan7());
                    senaraiHarta.add(mohonStrata.getLaporanKemudahan8());
                    senaraiHarta.add(mohonStrata.getLaporanKemudahan9());
                    senaraiHarta.add(mohonStrata.getLaporanKemudahan10());
                    senaraiHarta.add(mohonStrata.getLaporanKemudahan11());
                    senaraiHarta.add(mohonStrata.getLaporanKemudahan12());
                    lainLain = mohonStrata.getLaporanKemudahan13();
                } else {
                    senaraiHarta = new ArrayList();
                    senaraiHarta.add(mohonStrata.getLaporanKemudahan1());
                    senaraiHarta.add(mohonStrata.getLaporanKemudahan2());
                    senaraiHarta.add(mohonStrata.getLaporanKemudahan3());
                    senaraiHarta.add(mohonStrata.getLaporanKemudahan4());
                    senaraiHarta.add(mohonStrata.getLaporanKemudahan5());
                    senaraiHarta.add(mohonStrata.getLaporanKemudahan6());
                    senaraiHarta.add(mohonStrata.getLaporanKemudahan7());
                    senaraiHarta.add(mohonStrata.getLaporanKemudahan8());
                    senaraiHarta.add(mohonStrata.getLaporanKemudahan9());
                    senaraiHarta.add(mohonStrata.getLaporanKemudahan10());
                    senaraiHarta.add(mohonStrata.getLaporanKemudahan11());
                    senaraiHarta.add(mohonStrata.getLaporanKemudahan12());
                    senaraiHarta.add(mohonStrata.getLaporanKemudahan13());
                    lainLain = mohonStrata.getLaporanKemudahan14();

                }
            }
        }
        senaraiMhnBngn = strService.findByIDPermohonan(idPermohonan);
        // from kod_harta_bersama
        senaraikodHartaBersama = new ArrayList<KodHartaBersama>();
        senaraikodHartaBersama = strService.findHartaBersamaByNama();
        checked = new ArrayList<String>();
        if (kodNegeri.equals("05")) {
            if (senaraiHarta != null) {
                for (int i = 0; i < 12; i++) {
                    hartaBsama = senaraikodHartaBersama.get(i);
                    hartaBsama2 = senaraiHarta.get(i);
                    if (hartaBsama.getNama().equalsIgnoreCase(hartaBsama2)) {
                        checked.add("checked");
                    } else if (!hartaBsama.getNama().equalsIgnoreCase(hartaBsama2)) {
                        checked.add("false");
                    } else {
                        checked.add("false");
                    }
                }
            }
        } else {
            if (senaraiHarta != null) {
                for (int i = 0; i < 13; i++) {
                    hartaBsama = senaraikodHartaBersama.get(i);
                    hartaBsama2 = senaraiHarta.get(i);
                    if (hartaBsama.getNama().equalsIgnoreCase(hartaBsama2)) {
                        checked.add("checked");
                    } else if (!hartaBsama.getNama().equalsIgnoreCase(hartaBsama2)) {
                        checked.add("false");
                    } else {
                        checked.add("false");
                    }
                }
            }
        }

        int iPetak = 0;
        int iTingkat = 0;
        int iBngn = 0;

        if (senaraiMhnBngn != null) {
            Comparator c = new Comparator<PermohonanBangunan>() {

                @Override
                public int compare(PermohonanBangunan b1, PermohonanBangunan b2) {
                    String blokName1 = b1.getNama();
                    String blokName2 = b2.getNama();
                    String blok1[] = blokName1.split("M");
                    String blok2[] = blokName2.split("M");
                    String namaBngn1Pecahan = String.valueOf(blok1[1]);
                    String namaBngn2Pecahan = String.valueOf(blok2[1]);
                    return Integer.parseInt(namaBngn1Pecahan) - Integer.parseInt(namaBngn2Pecahan);
                }
            };
            Collections.sort(senaraiMhnBngn, c);

            for (int i = 0; i < senaraiMhnBngn.size(); i++) {

                iPetak = iPetak + senaraiMhnBngn.get(i).getBilanganPetak();
                iTingkat = iTingkat + senaraiMhnBngn.get(i).getBilanganTingkat();
                iBngn = iBngn + 1;
            }

            bilTgkt = String.valueOf(iTingkat);
            bilPetak = String.valueOf(iPetak);
            bilBangunan = String.valueOf(iBngn);
            bilTingkat = "0";
            bilPtk = "0";

        } else {
            bilBangunan = "0";
            bilTgkt = "0";
            bilPetak = "0";
        }
        blok = (String) getContext().getRequest().getParameter("blok");
        PermohonanStrata ps = strService.findID(idPermohonan);
        if (ps != null) {
            idStrata = String.valueOf(ps.getIdStrata());
        }
    }

    public Resolution resetForm() throws ParseException {
        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");

        PermohonanStrata mhnStrata = strService.findID(idPermohonan);
        if (mhnStrata != null) {
            List<PermohonanBangunan> mhnBangunan = strService.checkMhnBangunanExist(idPermohonan);
            if (mhnBangunan != null) {
                try {
                    strService.deleteMohonBangunan(idPermohonan);
                    strService.deleteMohonStrata(mhnStrata.getIdStrata());
                    addSimpleMessage("Maklumat Bangunan Khas Berjaya Dikosongkan.");
                } catch (Exception e) {
                    LOG.error(e);
                    addSimpleError("Maklumat gagal dikosongkan.Error :" + e.toString());
                }
            }
        }
        rehydrate();
        return new RedirectResolution(BangunanKhasActionBean.class, "showForm");
    }

    public Resolution tambahBngnKhas() {
        // getContext().getRequest().setAttribute("tambah", Boolean.TRUE);
        String msg = getContext().getRequest().getParameter("message");
        String error = getContext().getRequest().getParameter("error");
        if (StringUtils.isNotBlank(msg)) {
            addSimpleMessage(msg);
        }
        if (StringUtils.isNotBlank(error)) {
            addSimpleError(error);
        }
        return new JSP("strata/bngn_khas/tambah_bangunan_khas.jsp").addParameter("popup", "true");
    }

    public Resolution simpanBngnKhas() throws ParseException {
        String error = null;
        String message = null;
        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        List<HakmilikPermohonan> idMHList;

        InfoAudit ia = new InfoAudit();
        ia = strService.getInfo(peng);
        ia.setDimasukOleh(peng);
        ia.setTarikhMasuk(new java.util.Date());
        ia.setDiKemaskiniOleh(peng);
        ia.setTarikhKemaskini(new java.util.Date());

        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        idMHList = hakmilikPermohonanServ.findIDMHByIDMohon(idPermohonan);
        permohonan = permohonanDAO.findById(idPermohonan);
        LOG.info("id mohon :" + permohonan.getIdPermohonan());
        mhnBngn = new PermohonanBangunan();
        mhnBngn.setPermohonan(permohonan);
        mhnBngn.setCawangan(peng.getKodCawangan());
        mhnBngn.setInfoAudit(ia);
        KodKategoriBangunan kodKategoriBngn = new KodKategoriBangunan();
        kodKategoriBngn = kodkatbngnDAO.findById("B");

        String nama = kodKategoriBngn.getNama();
        LOG.info("Nama kod kategori bngn : " + nama);

        LOG.info("BLOK : M" + blok);
        PermohonanBangunan pb = strService.findByNama("M" + blok, idPermohonan);
        if (pb != null) {
            LOG.info("No Blok sudah wujud");
            error = "Nombor Blok sudah wujud. Sila masukkan Nombor Blok yang lain.";
            addSimpleError("Nombor Blok sudah wujud. Sila masukkan Nombor Blok lain.");

        } else {
            if (kodNegeri.equals("05")) {
                for (int a = 0; a < idMHList.size(); a++) {
                    HakmilikPermohonan idMH = idMHList.get(a);
                    PermohonanSkim idSkim = strService.findIDSkimByIDMH(idMH.getId());
                    if (idSkim != null) {
                        mohonSkim = mohonSkimDAO.findById(idSkim.getIdSkim());
                        mhnBngn.setPermohonanSkim(mohonSkim);
                    } else {
                        mhnBngn.setPermohonanSkim(null);
                    }
                }
            }
            mhnBngn.setNama("M" + blok);
            mhnBngn.setNamaLain("-");
            mhnBngn.setBilanganPetak(Integer.parseInt(bilPtk));
            mhnBngn.setBilanganTingkat(Integer.parseInt(bilTingkat));
            mhnBngn.setKodKegunaanBangunan(kodKegunaanBangunanDAO.findById(gunaBngn));
            if (bilBilik != null) {
                mhnBngn.setBilanganBilik(Integer.parseInt(bilBilik));
            } else if (bilBilik == null) {
                mhnBngn.setBilanganBilik(Integer.parseInt("0"));
            }
            mhnBngn.setKodKategoriBangunan(kodKategoriBngn);

            try {
                strService.saveMohonBangunan(mhnBngn);
                LOG.info("Simpan Bangunan Kekal Berjaya");
            } catch (Exception e) {
                LOG.error(e);
                addSimpleError("Terdapat masalah semasa menambah Bangunan Khas. Ralat : " + e);
            }
        }
        rehydrate();
        return new JSP("strata/bngn_khas/maklumat_bangunan.jsp").addParameter("tab", "true").addParameter("error", error);
    }

    public Resolution deleteBngn() {

        idBangunan = getContext().getRequest().getParameter("idBangunan");

        if (idBangunan != null) {
            PermohonanBangunan bngn = permohonanBangunanDAO.findById(Long.parseLong(idBangunan));

            if (bngn != null) {
                LOG.info(".:ON DELETING BANGUNAN:.");
                strService.deleteBngn(bngn);
            }
        }
        rehydrate();
        return new RedirectResolution(BangunanKhasActionBean.class, "showForm");
    }

    public Resolution popupKemaskiniBngn() {
        // getContext().getRequest().setAttribute("update", Boolean.TRUE);
        idBangunan = getContext().getRequest().getParameter("idBangunan");
        LOG.info("id bangunan : " + idBangunan);

        if (idBangunan != null) {
            PermohonanBangunan bngn = permohonanBangunanDAO.findById(Long.parseLong(idBangunan));

            if (bngn != null) {
                String blokName = bngn.getNama();
                String blok[] = blokName.split("M");
                updatenamaLain = blok[1];
                updatebilPetak = String.valueOf(bngn.getBilanganPetak());
                updatebilTgkt = String.valueOf(bngn.getBilanganTingkat());
                updatebilBilik = String.valueOf(bngn.getBilanganBilik());
                if (bngn.getKodKegunaanBangunan() != null) {
                    gunaBngn = bngn.getKodKegunaanBangunan().getKod();
                }
            }

        }
        return new JSP("strata/bngn_khas/update_bangunan_khas.jsp").addParameter("popup", "true");
    }

    public Resolution updateBngn() throws ParseException {
        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        LOG.info("========id bangunan : " + idBangunan);
        if (idBangunan != null) {
            PermohonanBangunan bngn = permohonanBangunanDAO.findById(Long.parseLong(idBangunan));
            if (bngn != null) {
                InfoAudit ia = new InfoAudit();
                ia = bngn.getInfoAudit();
                ia.setDiKemaskiniOleh(peng);
                ia.setTarikhKemaskini(new java.util.Date());

                bngn.setNama("M" + updatenamaLain);
                bngn.setBilanganPetak(Integer.parseInt(updatebilPetak));
                bngn.setBilanganTingkat(Integer.parseInt(updatebilTgkt));
                if (updatebilBilik != null) {
                    bngn.setBilanganBilik(Integer.parseInt(updatebilBilik));
                } else if (updatebilBilik == null) {
                    bngn.setBilanganBilik(Integer.parseInt("0"));
                }
                bngn.setInfoAudit(ia);

                LOG.info("=======KEMASKINI BANGUNAN=======");
                strService.saveMohonBangunan(bngn);
                addSimpleMessage("Maklumat Bangunan Telah Dikemaskini.");
            }
        }

        rehydrate();
        return new JSP("strata/bngn_khas/maklumat_bangunan.jsp").addParameter("tab", "true");
    }

    @DefaultHandler
    public Resolution showForm() {
        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");

        String msg = getContext().getRequest().getParameter("message");
        String error = getContext().getRequest().getParameter("error");
        if (StringUtils.isNotBlank(msg)) {
            addSimpleMessage(msg);
        }
        if (StringUtils.isNotBlank(error)) {
            addSimpleError(error);
        }
        permohonan = permohonanDAO.findById(idPermohonan);

        return new JSP("strata/bngn_khas/maklumat_bangunan.jsp").addParameter("tab", "true");
    }
    
    public Resolution showForm2() {
        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");

        String msg = getContext().getRequest().getParameter("message");
        String error = getContext().getRequest().getParameter("error");
        if (StringUtils.isNotBlank(msg)) {
            addSimpleMessage(msg);
        }
        if (StringUtils.isNotBlank(error)) {
            addSimpleError(error);
        }
        permohonan = permohonanDAO.findById(idPermohonan);

        return new JSP("strata/bngn_khas/maklumat_bangunanReadOnly.jsp").addParameter("tab", "true");
    }

    public Resolution simpanBngn() throws ParseException {
        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        kodNegeri = conf.getProperty("kodNegeri");
        InfoAudit ia = new InfoAudit();
        ia = strService.getInfo(peng);
        ia.setDimasukOleh(peng);
        ia.setTarikhMasuk(new java.util.Date());
        ia.setDiKemaskiniOleh(peng);
        ia.setTarikhKemaskini(new java.util.Date());
        HakmilikPermohonan mohonHakmilik;
        mohonHakmilik = strService.findMohonHakmilik(permohonan.getIdPermohonan());

        if (StringUtils.isNotBlank(idStrata)) {

            long longidstrata = Long.parseLong(idStrata);
            LOG.info("id strata : " + longidstrata);
            setMohonStrata(permohonanStrataDAO.findById(longidstrata));
        } else {
            setMohonStrata(new PermohonanStrata());
            LOG.info("NEW PERMOHONAN STRATA for permohonan : " + permohonan.getIdPermohonan());
        }
        bilBangunan = getContext().getRequest().getParameter("bilBangunan");
        LOG.info("FLAG CHECK : >>>>>Bil Bangunan from getContext = " + bilBangunan);
        mohonStrata.setPermohonan(permohonan);
        mohonStrata.setCawangan(permohonan.getCawangan());
        mohonStrata.setHakmilikPermohonan(mohonHakmilik);

        mohonStrata.setNama("-");
        mohonStrata.setPemilikNama("-");
        mohonStrata.setNamaSkim(namaSkim);
        mohonStrata.setInfoAudit(ia);
        mohonStrata.setCfNoSijil(cfNoSijil);
        mohonStrata.setCfTarikhKeluar(sdf.parse(cfTarikhKeluar));
        mohonStrata.setHargaPetak(BigDecimal.valueOf(Double.parseDouble(hargaPetak)));
        mohonStrata.setTarikhSiap(sdf.parse(tarikhSiap));
        mohonStrata.setLaporanBilBangunan(Integer.parseInt(bilBangunan));

        LOG.info("kod guna petak kod : " + strKodGunaPetak);
        /* find kodKegunaanPetak by id from jsp */
        KodKegunaanPetak kgp = kodKegunaanPetakDAO.findById(strKodGunaPetak);
        mohonStrata.setLaporanKategoriBangunani(kgp.getNama());

        mohonStrata.setLaporanLokasi(lokasiBangunan);
        mohonStrata.setLaporanBilPetak(Integer.parseInt(bilPetak));

        if (kodNegeri.equals("05")) {
            for (int i = 0; i < senaraikodHartaBersama.size(); i++) {
                KodHartaBersama kodHartaBersama = new KodHartaBersama();
                kodHartaBersama = senaraikodHartaBersama.get(i);
                String kod = getContext().getRequest().getParameter(kodHartaBersama.getNama());
                if (i == 0) {
                    mohonStrata.setLaporanKemudahan1(kod);
                } else if (i == 1) {
                    mohonStrata.setLaporanKemudahan2(kod);
                } else if (i == 2) {
                    mohonStrata.setLaporanKemudahan3(kod);
                } else if (i == 3) {
                    mohonStrata.setLaporanKemudahan4(kod);
                } else if (i == 4) {
                    mohonStrata.setLaporanKemudahan5(kod);
                } else if (i == 5) {
                    mohonStrata.setLaporanKemudahan6(kod);
                } else if (i == 6) {
                    mohonStrata.setLaporanKemudahan7(kod);
                } else if (i == 7) {
                    mohonStrata.setLaporanKemudahan8(kod);
                } else if (i == 8) {
                    mohonStrata.setLaporanKemudahan9(kod);
                } else if (i == 9) {
                    mohonStrata.setLaporanKemudahan10(kod);
                } else if (i == 10) {
                    mohonStrata.setLaporanKemudahan11(kod);
                } else if (i == 11) {
                    if (kod != null) {
                        mohonStrata.setLaporanKemudahan12(kod);
                        mohonStrata.setLaporanKemudahan13(lainLain);
                    } else if (kod == null) {
                        mohonStrata.setLaporanKemudahan12(null);
                        mohonStrata.setLaporanKemudahan13(null);
                    }
                }
            }
        } else {
            for (int i = 0; i < senaraikodHartaBersama.size(); i++) {
                KodHartaBersama kodHartaBersama = new KodHartaBersama();
                kodHartaBersama = senaraikodHartaBersama.get(i);
                String kod = getContext().getRequest().getParameter(kodHartaBersama.getNama());
                if (i == 0) {
                    mohonStrata.setLaporanKemudahan1(kod);
                } else if (i == 1) {
                    mohonStrata.setLaporanKemudahan2(kod);
                } else if (i == 2) {
                    mohonStrata.setLaporanKemudahan3(kod);
                } else if (i == 3) {
                    mohonStrata.setLaporanKemudahan4(kod);
                } else if (i == 4) {
                    mohonStrata.setLaporanKemudahan5(kod);
                } else if (i == 5) {
                    mohonStrata.setLaporanKemudahan6(kod);
                } else if (i == 6) {
                    mohonStrata.setLaporanKemudahan7(kod);
                } else if (i == 7) {
                    mohonStrata.setLaporanKemudahan8(kod);
                } else if (i == 8) {
                    mohonStrata.setLaporanKemudahan9(kod);
                } else if (i == 9) {
                    mohonStrata.setLaporanKemudahan10(kod);
                } else if (i == 10) {
                    mohonStrata.setLaporanKemudahan11(kod);
                } else if (i == 11) {
                    mohonStrata.setLaporanKemudahan12(kod);
                } else if (i == 12) {
                    if (kod != null) {
                        mohonStrata.setLaporanKemudahan13(kod);
                        mohonStrata.setLaporanKemudahan14(lainLain);
                    } else if (kod == null) {
                        mohonStrata.setLaporanKemudahan13(null);
                        mohonStrata.setLaporanKemudahan14(null);
                    }
                }
            }
        }

        try {
            strService.simpanPemilik(mohonStrata);
            addSimpleMessage("Maklumat Bangunan Khas Telah Disimpan");
        } catch (Exception e) {
            LOG.error(e);
            addSimpleError("Maklumat gagal disimpan.Error :" + e.toString());
        }
        return new RedirectResolution(BangunanKhasActionBean.class, "showForm");
    }

    public Resolution simpanSementara() throws ParseException {
        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        kodNegeri = conf.getProperty("kodNegeri");
        InfoAudit ia = new InfoAudit();
        ia = strService.getInfo(peng);
        ia.setDimasukOleh(peng);
        ia.setTarikhMasuk(new java.util.Date());
        ia.setDiKemaskiniOleh(peng);
        ia.setTarikhKemaskini(new java.util.Date());

        HakmilikPermohonan mohonHakmilik;
        mohonHakmilik = strService.findMohonHakmilik(permohonan.getIdPermohonan());

        if (StringUtils.isNotBlank(idStrata)) {

            long longidstrata = Long.parseLong(idStrata);
            LOG.info("id strata : " + longidstrata);
            setMohonStrata(permohonanStrataDAO.findById(longidstrata));
        } else {
            setMohonStrata(new PermohonanStrata());
            LOG.info("NEW PERMOHONAN STRATA for permohonan : " + permohonan.getIdPermohonan());
        }

        mohonStrata.setPermohonan(permohonan);
        mohonStrata.setCawangan(permohonan.getCawangan());
        mohonStrata.setHakmilikPermohonan(mohonHakmilik);

        mohonStrata.setNama("-");
        mohonStrata.setPemilikNama("-");
        mohonStrata.setInfoAudit(ia);
        mohonStrata.setCfNoSijil(cfNoSijil);
        mohonStrata.setNamaSkim(namaSkim);
        if (cfTarikhKeluar != null) {
            mohonStrata.setCfTarikhKeluar(sdf.parse(cfTarikhKeluar));
        }
        if (hargaPetak != null) {
            mohonStrata.setHargaPetak(BigDecimal.valueOf(Double.parseDouble(hargaPetak)));
        }
        if (tarikhSiap != null) {
            mohonStrata.setTarikhSiap(sdf.parse(tarikhSiap));
        }
        if (bilBangunan != null) {
            mohonStrata.setLaporanBilBangunan(Integer.parseInt(bilBangunan));
        }

        /* find kodKegunaanPetak by id from jsp */
        if (strKodGunaPetak != null) {
            LOG.info("kod guna petak kod : " + strKodGunaPetak);
            KodKegunaanPetak kgp = kodKegunaanPetakDAO.findById(strKodGunaPetak);
            mohonStrata.setLaporanKategoriBangunani(kgp.getNama());
        }
        mohonStrata.setLaporanLokasi(lokasiBangunan);
        if (bilPetak != null) {
            mohonStrata.setLaporanBilPetak(Integer.parseInt(bilPetak));
        }
        if (kodNegeri.equals("05")) {
            if (senaraikodHartaBersama != null) {
                for (int i = 0; i < senaraikodHartaBersama.size(); i++) {
                    KodHartaBersama kodHartaBersama = new KodHartaBersama();
                    kodHartaBersama = senaraikodHartaBersama.get(i);
                    String kod = getContext().getRequest().getParameter(kodHartaBersama.getNama());
                    if (i == 0) {
                        mohonStrata.setLaporanKemudahan1(kod);
                    } else if (i == 1) {
                        mohonStrata.setLaporanKemudahan2(kod);
                    } else if (i == 2) {
                        mohonStrata.setLaporanKemudahan3(kod);
                    } else if (i == 3) {
                        mohonStrata.setLaporanKemudahan4(kod);
                    } else if (i == 4) {
                        mohonStrata.setLaporanKemudahan5(kod);
                    } else if (i == 5) {
                        mohonStrata.setLaporanKemudahan6(kod);
                    } else if (i == 6) {
                        mohonStrata.setLaporanKemudahan7(kod);
                    } else if (i == 7) {
                        mohonStrata.setLaporanKemudahan8(kod);
                    } else if (i == 8) {
                        mohonStrata.setLaporanKemudahan9(kod);
                    } else if (i == 9) {
                        mohonStrata.setLaporanKemudahan10(kod);
                    } else if (i == 10) {
                        mohonStrata.setLaporanKemudahan11(kod);
                    } else if (i == 11) {
                        if (kod != null) {
                            mohonStrata.setLaporanKemudahan12(kod);
                            mohonStrata.setLaporanKemudahan13(lainLain);
                        } else if (kod == null) {
                            mohonStrata.setLaporanKemudahan12(null);
                            mohonStrata.setLaporanKemudahan13(null);
                        }
                    }
                }
            }
        } else {
            if (senaraikodHartaBersama != null) {
                for (int i = 0; i < senaraikodHartaBersama.size(); i++) {
                    KodHartaBersama kodHartaBersama = new KodHartaBersama();
                    kodHartaBersama = senaraikodHartaBersama.get(i);
                    String kod = getContext().getRequest().getParameter(kodHartaBersama.getNama());
                    if (i == 0) {
                        mohonStrata.setLaporanKemudahan1(kod);
                    } else if (i == 1) {
                        mohonStrata.setLaporanKemudahan2(kod);
                    } else if (i == 2) {
                        mohonStrata.setLaporanKemudahan3(kod);
                    } else if (i == 3) {
                        mohonStrata.setLaporanKemudahan4(kod);
                    } else if (i == 4) {
                        mohonStrata.setLaporanKemudahan5(kod);
                    } else if (i == 5) {
                        mohonStrata.setLaporanKemudahan6(kod);
                    } else if (i == 6) {
                        mohonStrata.setLaporanKemudahan7(kod);
                    } else if (i == 7) {
                        mohonStrata.setLaporanKemudahan8(kod);
                    } else if (i == 8) {
                        mohonStrata.setLaporanKemudahan9(kod);
                    } else if (i == 9) {
                        mohonStrata.setLaporanKemudahan10(kod);
                    } else if (i == 10) {
                        mohonStrata.setLaporanKemudahan11(kod);
                    } else if (i == 11) {
                        mohonStrata.setLaporanKemudahan12(kod);
                    } else if (i == 12) {
                        if (kod != null) {
                            mohonStrata.setLaporanKemudahan13(kod);
                            mohonStrata.setLaporanKemudahan14(lainLain);
                        } else if (kod == null) {
                            mohonStrata.setLaporanKemudahan13(null);
                            mohonStrata.setLaporanKemudahan14(null);
                        }
                    }
                }
            }
        }
        try {
            strService.simpanPemilik(mohonStrata);

        } catch (Exception e) {
            LOG.error(e);
        }
        return new RedirectResolution(BangunanKhasActionBean.class, "showForm");
    }

    public List<SenaraiHargaPKKR> getListHarga() {
        return listHarga;

    }

    public void setListHarga(List<SenaraiHargaPKKR> listHarga) {
        this.listHarga = listHarga;

    }

    public String getCfNoSijil() {
        return cfNoSijil;

    }

    public void setCfNoSijil(String cfNoSijil) {
        this.cfNoSijil = cfNoSijil;

    }

    public String getCfTarikhKeluar() {
        return cfTarikhKeluar;

    }

    public void setCfTarikhKeluar(String cfTarikhKeluar) {
        this.cfTarikhKeluar = cfTarikhKeluar;

    }

    public KodKegunaanPetak getKodGunaPetak() {
        return kodGunaPetak;

    }

    public void setKodGunaPetak(KodKegunaanPetak kodGunaPetak) {
        this.kodGunaPetak = kodGunaPetak;

    }

    public String getBilBangunan() {
        return bilBangunan;

    }

    public void setBilBangunan(String bilBangunan) {
        this.bilBangunan = bilBangunan;

    }

    public String getBilBilik() {
        return bilBilik;

    }

    public void setBilBilik(String bilBilik) {
        this.bilBilik = bilBilik;

    }

    public String getBilPetak() {
        return bilPetak;

    }

    public void setBilPetak(String bilPetak) {
        this.bilPetak = bilPetak;

    }

    public String getBilTgkt() {
        return bilTgkt;

    }

    public void setBilTgkt(String bilTgkt) {
        this.bilTgkt = bilTgkt;

    }

    public KodHartaBersama getKodHarta() {
        return kodHarta;
    }

    public void setKodHarta(KodHartaBersama kodHarta) {
        this.kodHarta = kodHarta;
    }

    public String getLokasiBangunan() {
        return lokasiBangunan;

    }

    public void setLokasiBangunan(String lokasiBangunan) {
        this.lokasiBangunan = lokasiBangunan;

    }

    public String getTarikhSiap() {
        return tarikhSiap;

    }

    public void setTarikhSiap(String tarikhSiap) {
        this.tarikhSiap = tarikhSiap;

    }

    public String[] getHartaBersama() {
        return hartaBersama;

    }

    public void setHartaBersama(String[] hartaBersama) {
        this.hartaBersama = hartaBersama;

    }

    public String getNamaLain() {
        return namaLain;

    }

    public void setNamaLain(String namaLain) {
        this.namaLain = namaLain;

    }

    public List<PermohonanBangunan> getSenaraiMhnBngn() {
        return senaraiMhnBngn;

    }

    public void setSenaraiMhnBngn(List<PermohonanBangunan> senaraiMhnBngn) {
        this.senaraiMhnBngn = senaraiMhnBngn;

    }

    public String getHargaPetak() {
        return hargaPetak;

    }

    public void setHargaPetak(String hargaPetak) {
        this.hargaPetak = hargaPetak;

    }

    public String getUpdatebilBilik() {
        return updatebilBilik;

    }

    public void setUpdatebilBilik(String updatebilBilik) {
        this.updatebilBilik = updatebilBilik;

    }

    public String getUpdatebilTgkt() {
        return updatebilTgkt;

    }

    public void setUpdatebilTgkt(String updatebilTgkt) {
        this.updatebilTgkt = updatebilTgkt;

    }

    public String getUpdatenamaLain() {
        return updatenamaLain;

    }

    public void setUpdatenamaLain(String updatenamaLain) {
        this.updatenamaLain = updatenamaLain;

    }

    public String getUpdatebilPetak() {
        return updatebilPetak;

    }

    public void setUpdatebilPetak(String updatebilPetak) {
        this.updatebilPetak = updatebilPetak;

    }

    public List<KodHartaBersama> getSenaraiKodHarta() {
        return senaraiKodHarta;
    }

    public void setSenaraiKodHarta(List<KodHartaBersama> senaraiKodHarta) {
        this.senaraiKodHarta = senaraiKodHarta;
    }

    public String getBlok() {
        return blok;
    }

    public void setBlok(String blok) {
        this.blok = blok;
    }

    public String getIdStrata() {
        return idStrata;
    }

    public void setIdStrata(String idStrata) {
        this.idStrata = idStrata;
    }

    public String getStrKodGunaPetak() {
        return strKodGunaPetak;
    }

    public void setStrKodGunaPetak(String strKodGunaPetak) {
        this.strKodGunaPetak = strKodGunaPetak;
    }

    public String getIdBangunan() {
        return idBangunan;
    }

    public void setIdBangunan(String idBangunan) {
        this.idBangunan = idBangunan;
    }

    public List<String> getSenaraiHarta() {
        return senaraiHarta;
    }

    public void setSenaraiHarta(List<String> senaraiHarta) {
        this.senaraiHarta = senaraiHarta;
    }

    /**
     * @return the mohonSkim
     */
    public PermohonanSkim getMohonSkim() {
        return mohonSkim;
    }

    /**
     * @param mohonSkim the mohonSkim to set
     */
    public void setMohonSkim(PermohonanSkim mohonSkim) {
        this.mohonSkim = mohonSkim;
    }

    /**
     * @return the bilTingkat
     */
    public String getBilTingkat() {
        return bilTingkat;
    }

    /**
     * @param bilTingkat the bilTingkat to set
     */
    public void setBilTingkat(String bilTingkat) {
        this.bilTingkat = bilTingkat;
    }

    /**
     * @return the bilPtk
     */
    public String getBilPtk() {
        return bilPtk;
    }

    /**
     * @param bilPtk the bilPtk to set
     */
    public void setBilPtk(String bilPtk) {
        this.bilPtk = bilPtk;
    }

    /**
     * @return the mohonBngn
     */
    public PermohonanBangunan getMohonBngn() {
        return mohonBngn;
    }

    /**
     * @param mohonBngn the mohonBngn to set
     */
    public void setMohonBngn(PermohonanBangunan mohonBngn) {
        this.mohonBngn = mohonBngn;
    }

    /**
     * @return the lainLain
     */
    public String getLainLain() {
        return lainLain;
    }

    /**
     * @param lainLain the lainLain to set
     */
    public void setLainLain(String lainLain) {
        this.lainLain = lainLain;
    }

    /**
     * @return the senaraikodHartaBersama
     */
    public List<KodHartaBersama> getSenaraikodHartaBersama() {
        return senaraikodHartaBersama;
    }

    /**
     * @param senaraikodHartaBersama the senaraikodHartaBersama to set
     */
    public void setSenaraikodHartaBersama(List<KodHartaBersama> senaraikodHartaBersama) {
        this.senaraikodHartaBersama = senaraikodHartaBersama;
    }

    /**
     * @return the mohonStrata
     */
    public PermohonanStrata getMohonStrata() {
        return mohonStrata;
    }

    /**
     * @param mohonStrata the mohonStrata to set
     */
    public void setMohonStrata(PermohonanStrata mohonStrata) {
        this.mohonStrata = mohonStrata;
    }

    /**
     * @return the hartaBsama
     */
    public KodHartaBersama getHartaBsama() {
        return hartaBsama;
    }

    /**
     * @param hartaBsama the hartaBsama to set
     */
    public void setHartaBsama(KodHartaBersama hartaBsama) {
        this.hartaBsama = hartaBsama;
    }

    /**
     * @return the checked
     */
    public List<String> getChecked() {
        return checked;
    }

    /**
     * @param checked the checked to set
     */
    public void setChecked(List<String> checked) {
        this.checked = checked;
    }

    /**
     * @return the hartaBsama2
     */
    public String getHartaBsama2() {
        return hartaBsama2;
    }

    /**
     * @param hartaBsama2 the hartaBsama2 to set
     */
    public void setHartaBsama2(String hartaBsama2) {
        this.hartaBsama2 = hartaBsama2;
    }

    public String getKodNegeri() {
        return kodNegeri;
    }

    public void setKodNegeri(String kodNegeri) {
        this.kodNegeri = kodNegeri;
    }

    public String getNamaSkim() {
        return namaSkim;
    }

    public void setNamaSkim(String namaSkim) {
        this.namaSkim = namaSkim;
    }

    public String getGunaBngn() {
        return gunaBngn;
    }

    public void setGunaBngn(String gunaBngn) {
        this.gunaBngn = gunaBngn;
    }
}
