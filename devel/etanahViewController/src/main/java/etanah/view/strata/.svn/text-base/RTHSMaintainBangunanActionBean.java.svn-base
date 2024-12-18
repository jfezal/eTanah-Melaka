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
import etanah.model.FasaPermohonan;
import etanah.model.InfoAudit;
import etanah.model.KodKegunaanPetak;
import etanah.model.KodKegunaanPetakAksesori;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.model.PermohonanBangunan;
import etanah.service.PembangunanService;
import etanah.service.StrataPtService;
import java.io.IOException;
import javax.xml.parsers.ParserConfigurationException;
import net.sourceforge.stripes.action.UrlBinding;
import etanah.view.BasicTabActionBean;
import etanah.view.etanahActionBeanContext;
import java.io.File;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
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
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.w3c.dom.*;
import org.xml.sax.SAXException;

/**
 *
 * @author Sreenivasa Reddy Munagala
 */
@UrlBinding("/strata/RTHSUrusan_RTHS")
public class RTHSMaintainBangunanActionBean extends BasicTabActionBean {

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
    private PermohonanBangunan bangunan;
    private Permohonan permohonan;
    @Inject
    PermohonanBangunanDAO permohonanBangunanDAO;
    @Inject
    StrataPtService strService;
    @Inject
    PembangunanService devService;
    @Inject
    private etanah.Configuration conf;
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
    private static final Logger LOG = Logger.getLogger(RTHSMaintainBangunanActionBean.class);
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
    private String menara;
    private String idPermohonanTerdahulu;

    public String getIdPermohonanTerdahulu() {
        return idPermohonanTerdahulu;
    }

    public void setIdPermohonanTerdahulu(String idPermohonanTerdahulu) {
        this.idPermohonanTerdahulu = idPermohonanTerdahulu;
    }
    
    public String getMenara() {
        return menara;
    }

    public void setMenara(String menara) {
        this.menara = menara;
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

    @DefaultHandler
    public Resolution showForm1() {
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        String msg = getContext().getRequest().getParameter("message");
        String error = getContext().getRequest().getParameter("error");

        stageId = "keputusanPTG";
        String idPermohonanTerdahulu = (String) getContext().getRequest().getParameter("idPermohonanTerdahulu");

         String idPermohonanTerdahulu2=(String)getContext().getRequest().getSession().getAttribute("idPermohonanTerdahulu");

         if(idPermohonanTerdahulu != null && !idPermohonanTerdahulu.trim().equals("")||(idPermohonanTerdahulu2!=null)){
             FasaPermohonan fp = devService.findFasaPermohonanByIdAliran(idPermohonanTerdahulu2, stageId);
             System.out.println("FASA PERMOHONAN"+fp);
            if(fp!=null){
                addSimpleError("IDPermohonan ini telah diproses");
                getContext().getRequest().setAttribute("edit123", Boolean.FALSE);
                 return new JSP("strata/Lanjutan_Tempo_Mohon/jadualPetak.jsp").addParameter("tab", "true");
            }

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
                String[] namaPetak1Pecahan = o1.getNama().split(" ");
                String[] namaPetak2Pecahan = o2.getNama().split(" ");
                return Integer.parseInt(namaPetak1Pecahan[1]) -
                        Integer.parseInt(namaPetak2Pecahan[1]);
            }
        };

        Comparator c2 = new Comparator<BangunanTingkat>() {

            @Override
            public int compare(BangunanTingkat u1, BangunanTingkat u2) {
                String[] namaTgkt1Pecahan = u1.getNama().split(" ");
                String[] namaTgkt2Pecahan = u2.getNama().split(" ");
                return Integer.parseInt(namaTgkt1Pecahan[1]) -
                        Integer.parseInt(namaTgkt2Pecahan[1]);
            }
        };

        Comparator c3 = new Comparator<BangunanPetakAksesori>() {

            @Override
            public int compare(BangunanPetakAksesori p1, BangunanPetakAksesori p2) {
                String namaAksr1Pecahan = p1.getNama().substring(1);
                String namaAksr2Pecahan = p2.getNama().substring(1);
                return Integer.parseInt(namaAksr1Pecahan) -
                        Integer.parseInt(namaAksr2Pecahan);
            }
        };

        Comparator c4 = new Comparator<PermohonanBangunan>() {

            @Override
            public int compare(PermohonanBangunan b1, PermohonanBangunan b2) {
                String namaBngn1Pecahan = b1.getNama().substring(4);
                String namaBngn2Pecahan = b2.getNama().substring(4);
                return Integer.parseInt(namaBngn1Pecahan) -
                        Integer.parseInt(namaBngn2Pecahan);
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

        getContext().getRequest().setAttribute("disable", Boolean.FALSE);
        return new JSP("strata/Lanjutan_Tempo_Mohon/rthsmaklumat_jadualpetak.jsp").addParameter("tab", "true");
    }else{
             addSimpleError(" Sila masukkan ID Permohonan terdahulu.");
             getContext().getRequest().setAttribute("edit123", Boolean.FALSE);
             return new JSP("strata/Lanjutan_Tempo_Mohon/jadualPetak.jsp").addParameter("tab", "true");
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
        String idPermohonanTerdahulu = (String) getContext().getRequest().getParameter("idPermohonanTerdahulu");
        stageId = "keputusanPTG";
        String idPermohonanTerdahulu2 = (String) getContext().getRequest().getSession().getAttribute("idPermohonanTerdahulu");
        if (idPermohonanTerdahulu != null && !idPermohonanTerdahulu.trim().equals("") || (idPermohonanTerdahulu2 != null)) {
            permohonan = permohonanDAO.findById(idPermohonanTerdahulu2);

            if (permohonan == null) {
                addSimpleError("Id Permohonan ini tiada di dalam pangkalan data.");
                getContext().getRequest().setAttribute("edit123", Boolean.FALSE);
                return new JSP("strata/Lanjutan_Tempo_Mohon/jadualPetak.jsp").addParameter("tab", "true");
            }
            FasaPermohonan fp = devService.findFasaPermohonanByIdAliran(idPermohonanTerdahulu2, stageId);
            System.out.println("FASA PERMOHONAN" + fp);
            if (fp != null) {
                addSimpleError("IDPermohonan ini telah diproses");
                getContext().getRequest().setAttribute("edit123", Boolean.FALSE);
                return new JSP("strata/Lanjutan_Tempo_Mohon/jadualPetak.jsp").addParameter("tab", "true");
            }
            return new JSP("strata/Lanjutan_Tempo_Mohon/papar_jadualpetak.jsp").addParameter("popup", "true");
        } else {
            addSimpleError(" Sila masukkan ID Permohonan terdahulu.");
            getContext().getRequest().setAttribute("edit123", Boolean.FALSE);
            return new JSP("strata/Lanjutan_Tempo_Mohon/jadualPetak.jsp").addParameter("tab", "true");
            // return new JSP("strata/Lanjutan_Tempo_Mohon/papar_jadualpetak.jsp").addParameter("popup", "true");
        }
    }

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");

         idPermohonanTerdahulu = (String) getContext().getRequest().getSession().getAttribute("idPermohonanTerdahulu");
        if (idPermohonanTerdahulu != null) {
            permohonan = permohonanDAO.findById(idPermohonanTerdahulu);
            if(permohonan!=null){
            pBangunanL = strService.findByIDPermohonan(idPermohonanTerdahulu);
            kGunaPetakL = kodKegunaanPetakDAO.findAll();
            kGunaPetakAksesoriL = kodKegunaanPetakAksesoriDAO.findAll();
            senaraiTingkat = new ArrayList<BangunanTingkat>();
            senaraiPetak = new ArrayList<BangunanPetak>();
            senaraipetakAksesori = new ArrayList<BangunanPetakAksesori>();

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
                                petakKegunaan[i] = String.valueOf(bp.getKegunaan().getKod());
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
                    tgkt.setNama("Tingkat " + (i + 1));
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

        return new RedirectResolution(RTHSMaintainBangunanActionBean.class, "showForm1").addParameter("error", error).addParameter("message", msg);

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
            ptk.setNama("Petak " + (sum + i + 1));
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

        return new RedirectResolution(RTHSMaintainBangunanActionBean.class, "showForm1");
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
        return new RedirectResolution(RTHSMaintainBangunanActionBean.class, "showForm1");
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
        return new RedirectResolution(RTHSMaintainBangunanActionBean.class, "showForm1");
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
        return new RedirectResolution(RTHSMaintainBangunanActionBean.class, "showForm1");
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
        return new RedirectResolution(RTHSMaintainBangunanActionBean.class, "showForm1");
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
         //   ptkAksesori.setCawangan(peng.getKodCawangan());
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


        return new RedirectResolution(RTHSMaintainBangunanActionBean.class, "showForm1");
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


        return new RedirectResolution(RTHSMaintainBangunanActionBean.class, "showForm1").addParameter("error", error).addParameter("message", msg);
    }

    public Resolution extrakBangunan() throws ParserConfigurationException, IOException, SAXException {

        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        InfoAudit infoAudit = new InfoAudit();
        infoAudit.setDimasukOleh(peng);
        infoAudit.setTarikhMasuk(new java.util.Date());
        String error = "";
        String msg = "";

        permohonan = permohonanDAO.findById(idPermohonan);
        Dokumen d = strService.findDok(idPermohonan, "SJT");
        String docPath = conf.getProperty("document.path");
        if (docPath == null) {
            return new ErrorResolution(500, "Konfigurasi \"document.path\" tidak dijumpai");
        }

        if (d == null) {
            error = "Dokumen tidak Dijumpai. Sila Muat Naik Jadual Petak.";
            return new RedirectResolution(RTHSMaintainBangunanActionBean.class, "showForm1").addParameter("error", error).addParameter("message", msg);
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

                int a;
                for (a = 0; a < n2.getLength(); a++) {

                    PermohonanBangunan bgn = new PermohonanBangunan();
                    bgn.setPermohonan(permohonan);
                    bgn.setInfoAudit(infoAudit);
                    bgn.setCawangan(peng.getKodCawangan());
                    bgn.setNama(n2.item(a).getAttributes().getNamedItem("bangunanno").getNodeValue());
                    bgn.setBilanganTingkat(Integer.valueOf(n2.item(a).getAttributes().getNamedItem("no_of_tingkat").getNodeValue()));
                    strService.simpanBangunan(bgn);
                    msg = "Maklumat telah berjaya disimpan.";
                    extrakTgkt(n3, n4, bgn.getIdBangunan());
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                return new ErrorResolution(500,
                        "Fail " + docPath + d.getNamaFizikal() + " tidak dijumpai");
            }
        }
        return new RedirectResolution(RTHSMaintainBangunanActionBean.class, "showForm1").addParameter("error", error).addParameter("message", msg);
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
            tgkt.setNama("Tingkat " + (i + 1));
            tgkt.setTingkat(i + 1);
            tgkt.setBilanganPetak(Integer.parseInt(n3.item(b).getAttributes().getNamedItem("no_of_petak").getNodeValue()));
            tgkt.setInfoAudit(infoAudit);
            listTingkat.add(tgkt);
            strService.simpanBangunanTingkat(tgkt);
            extrakPetak(n4, tgkt.getIdTingkat(), bgn.getIdBangunan());
            i++;
        }
        hv = bgn.getBilanganTingkat();
        bgn.setSenaraiTingkat(listTingkat);
        strService.updateBangunan(bgn);

        return new RedirectResolution(RTHSMaintainBangunanActionBean.class, "showForm1").addParameter("error", error).addParameter("message", msg);
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
            petak.setNama("Petak " + (sum4 + c + 1));
            petak.setInfoAudit(infoAudit1);
            petak.setTingkat(bangunanTingkat);
            BigDecimal bd = new BigDecimal(n4.item(c).getAttributes().getNamedItem("a_area").getNodeValue());
            petak.setLuas(bd);
            petak.setSyer(Integer.parseInt(n4.item(c).getAttributes().getNamedItem("unitsyer").getNodeValue()));
            KodKegunaanPetak k = new KodKegunaanPetak();
            k.setKod(n4.item(c).getAttributes().getNamedItem("kodkegunaanpetak").getNodeValue());
            petak.setKegunaan(k);
            listPetak.add(petak);
            strService.simpanPetak(petak);
        }
        sum4 = sum4 + bangunanTingkat.getBilanganPetak();

        return new RedirectResolution(RTHSMaintainBangunanActionBean.class, "showForm1").addParameter("error", error).addParameter("message", msg);
    }
}

