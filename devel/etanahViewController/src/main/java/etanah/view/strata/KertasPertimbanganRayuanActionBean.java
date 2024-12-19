/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.strata;

import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.dao.BangunanPetaAksesoriDAO;
import etanah.dao.BangunanPetakDAO;
import etanah.dao.BangunanTingkatDAO;
import etanah.dao.HakmilikDAO;
import etanah.dao.PemohonDAO;
import etanah.dao.PermohonanBangunanDAO;
import etanah.dao.PermohonanDAO;
import etanah.dao.PihakDAO;
import etanah.model.BangunanPetak;
import etanah.model.BangunanPetakAksesori;
import etanah.model.BangunanTingkat;
import etanah.model.Hakmilik;
import etanah.model.HakmilikPihakBerkepentingan;
import etanah.model.HakmilikUrusan;
import etanah.model.InfoAudit;
import etanah.model.Pemohon;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.model.PermohonanBangunan;
import etanah.model.PermohonanKertas;
import etanah.model.PermohonanKertasKandungan;
import etanah.model.Pihak;
import etanah.service.StrataPtService;
import etanah.view.BasicTabActionBean;
import etanah.view.etanahActionBeanContext;
import java.util.ArrayList;
import java.util.List;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.RedirectResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

/**
 *
 * @author faidzal
 */
@UrlBinding("/strata/rayuankertas")
public class KertasPertimbanganRayuanActionBean extends BasicTabActionBean {

    @Inject
    StrataPtService strataService;
    @Inject
    PermohonanDAO permohonanDAO;
    @Inject

    PermohonanBangunanDAO mohonBangunanDAO;
    @Inject
    BangunanTingkatDAO mohonTingkatDAO;
    @Inject
    BangunanPetakDAO mohonPetakDAO;
    @Inject
    BangunanPetaAksesoriDAO mohonAksDAO;
    @Inject
    PemohonDAO pemohonDAO;
    @Inject
    PihakDAO pihakDAO;
    @Inject
    HakmilikDAO hakmilikDAO;
    Pemohon pemohon;
    Permohonan permohonan;
    private PermohonanBangunan mohonbngn;
    ArrayList<PermohonanBangunan> listMohonBangunan = new ArrayList();
    ArrayList<BangunanTingkat> listMohonTingkat = new ArrayList();
    ArrayList<BangunanPetak> listMohonPetak = new ArrayList();
    ArrayList<BangunanPetakAksesori> listMohonAks = new ArrayList();
    private Pihak pihak;
    List<HakmilikUrusan> listhakmilikUrusan;
    private String idHakmilik;
    Hakmilik hakmilik;
    private int bil_petak = 0;
    private int bil_tgkt = 0;
    private int bil_bgnn;
    private int bil_ptkAksr = 0;
    private int jumlah_syer = 0;
    private int jumlah = 0;
    PermohonanKertas mohonKertas;
//    private PermohonanKertasKandungan kertasKandungan;
    private HakmilikPihakBerkepentingan hakmilikPihakBerkepentingan;
    String ulasanJabatanUkurdanPemetaan;
    String asaspertimbangan;
    String syorTP;
    List<PermohonanKertasKandungan> listKand3 = null;
    List<PermohonanKertasKandungan> listKand4;
    List<PermohonanKertasKandungan> listKand5;
    private static final Logger LOG = Logger.getLogger(KertasPertimbanganRayuanActionBean.class);

    @DefaultHandler
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
        return new JSP("strata/rayuan/kertas_pertimbangan_rayuan.jsp").addParameter("tab", "true");
    }

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        Permohonan permohonanLama = new Permohonan();
        permohonan = permohonanDAO.findById(idPermohonan);
        if (permohonan.getPermohonanSebelum() != null) {
            permohonanLama = permohonanDAO.findById(permohonan.getPermohonanSebelum().getIdPermohonan());
            listMohonBangunan = (ArrayList<PermohonanBangunan>) strataService.findByIDPermohonan(permohonanLama.getIdPermohonan());
            pemohon = strataService.findById(permohonanLama.getIdPermohonan());
            pihak = pihakDAO.findById(pemohon.getPihak().getIdPihak());
            idHakmilik = permohonanLama.getSenaraiHakmilik().get(0).getHakmilik().getIdHakmilik();
            hakmilik = hakmilikDAO.findById(idHakmilik);
            listhakmilikUrusan = strataService.findByID(idHakmilik);
            for (PermohonanBangunan bngn : listMohonBangunan) {
                bil_tgkt = bil_tgkt + bngn.getSenaraiTingkat().size();
                bil_ptkAksr = bil_ptkAksr + strataService.CountPetakAksr(String.valueOf(bngn.getIdBangunan()));
                bil_bgnn = strataService.CountBangunan(permohonanLama.getIdPermohonan());

                List<PermohonanBangunan> pb1 = permohonanLama.getSenaraiBangunan();
                for (PermohonanBangunan pb2 : pb1) {
                    List<BangunanTingkat> bt1 = pb2.getSenaraiTingkat();
                    LOG.info("BANGUNAN:: " + pb2.getNama());
                    for (BangunanTingkat bt2 : bt1) {
                        bil_petak = bil_petak + bt2.getBilanganPetak();
                        List<BangunanPetak> bp = bt2.getSenaraiPetak();
                        for (BangunanPetak bp2 : bp) {
                            if (bp2.getSyer() != null) {
                                System.out.print("OK je");
                            }
                            jumlah_syer = bp2.getSyer() != null ? bp2.getSyer() : 0;
                            jumlah = jumlah + jumlah_syer;
                            LOG.info(jumlah);
                        }
                    }
                }
            }
        }


        mohonKertas = strataService.findKertas(idPermohonan);
        if (mohonKertas != null) {
            listKand3 = strataService.findByIdLapor(mohonKertas.getIdKertas(), 3);
            if (listKand3.size() > 0) {
                ulasanJabatanUkurdanPemetaan = listKand3.get(0).getKandungan();
            }
            listKand4 = strataService.findByIdLapor(mohonKertas.getIdKertas(), 4);
            if (listKand4.size() > 0) {
                asaspertimbangan = listKand4.get(0).getKandungan();
            }
            listKand5 = strataService.findByIdLapor(mohonKertas.getIdKertas(), 5);
            if (listKand5.size() > 0) {
                ulasanJabatanUkurdanPemetaan = listKand5.get(0).getKandungan();
            }
        } else {
//            if (listKand3 == null) {
//                listKand3 = new ArrayList<PermohonanKertasKandungan>();
//                PermohonanKertasKandungan pk = new PermohonanKertasKandungan();
//                pk.setKandungan(null);
//                listKand3.clear();
//            }
//
//            if (listKand4 == null) {
//                listKand4 = new ArrayList<PermohonanKertasKandungan>();
//                PermohonanKertasKandungan pk = new PermohonanKertasKandungan();
//                pk.setKandungan(null);
//                listKand4.clear();
//            }
//
//            if (listKand5 == null) {
//                listKand5 = new ArrayList<PermohonanKertasKandungan>();
//                PermohonanKertasKandungan pk = new PermohonanKertasKandungan();
//                pk.setKandungan(null);
//                listKand5.clear();
//            }
        }
    }

    public Resolution tambah() {

        String error = null;
        String msg = null;
        int kes = Integer.parseInt(getContext().getRequest().getParameter("bil"));
        switch (kes) {
            case 3:
                listKand3.add(new PermohonanKertasKandungan());
                break;
            case 4:
                listKand4.add(new PermohonanKertasKandungan());
                break;
            case 5:
                listKand5.add(new PermohonanKertasKandungan());
                break;

        }
        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        return new RedirectResolution(KertasPertimbanganRayuanActionBean.class, "showForm").addParameter("error", error).addParameter("message", msg);

    }

//    public Resolution simpan() {
//        String error = null;
//        String msg = null;
//        mohonKertas.setInfoAudit(null);
//        mohonKertas.setCawangan(null);
//        mohonKertas.setPermohonan(permohonan);
//        mohonKertas.setSenaraiKandungan(null);
//
//
//        for (PermohonanKertasKandungan kertasKandungan : listKand3) {
//            kertasKandungan.setBil(bil_tgkt);
//            kertasKandungan.setCawangan(null);
//            kertasKandungan.setInfoAudit(null);
//            kertasKandungan.setKandungan(txnCode);
//            kertasKandungan.setKertas(mohonKertas);
//        }
//
//        for (PermohonanKertasKandungan kertasKandungan : listKand4) {
//            kertasKandungan.setBil(bil_tgkt);
//            kertasKandungan.setCawangan(null);
//            kertasKandungan.setInfoAudit(null);
//            kertasKandungan.setKandungan(txnCode);
//            kertasKandungan.setKertas(mohonKertas);
//        }
//        for (PermohonanKertasKandungan kertasKandungan : listKand5) {
//            kertasKandungan.setBil(bil_tgkt);
//            kertasKandungan.setCawangan(null);
//            kertasKandungan.setInfoAudit(null);
//            kertasKandungan.setKandungan(txnCode);
//            kertasKandungan.setKertas(mohonKertas);
//        }
//
//        msg = "Maklumat telah berjaya disimpan.";
//
//        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
//        return new RedirectResolution(KertasPertimbanganRayuanActionBean.class, "showForm").addParameter("error", error).addParameter("message", msg);
//    }
    public Resolution simpan() {
        InfoAudit ia = new InfoAudit();
        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);

        ia = strataService.getInfo(peng);
        String error = null;
        String msg = null;
        mohonKertas = strataService.findKertas(idPermohonan);
        if (mohonKertas != null) {
            PermohonanKertasKandungan kertasKandungan = new PermohonanKertasKandungan();
            kertasKandungan.setKandungan(ulasanJabatanUkurdanPemetaan);
            kertasKandungan.setCawangan(permohonan.getCawangan());
            kertasKandungan.setInfoAudit(ia);
            kertasKandungan.setBil(3);
            kertasKandungan.setAktif('Y');

            kertasKandungan.setKertas(mohonKertas);
            kertasKandungan = strataService.simpanPermohonanKertasKandungan(kertasKandungan);
            PermohonanKertasKandungan kertasKandungan2 = new PermohonanKertasKandungan();
            kertasKandungan2.setKandungan(asaspertimbangan);
            kertasKandungan2.setCawangan(permohonan.getCawangan());
            kertasKandungan2.setInfoAudit(ia);
            kertasKandungan2.setBil(3);
            kertasKandungan2.setAktif('Y');
            kertasKandungan2.setKertas(mohonKertas);
            kertasKandungan2 = strataService.simpanPermohonanKertasKandungan(kertasKandungan2);
            PermohonanKertasKandungan kertasKandungan3 = new PermohonanKertasKandungan();
            kertasKandungan3.setKandungan(ulasanJabatanUkurdanPemetaan);
            kertasKandungan3.setCawangan(permohonan.getCawangan());
            kertasKandungan3.setInfoAudit(ia);
            kertasKandungan3.setBil(3);
            kertasKandungan3.setAktif('Y');
            kertasKandungan3.setKertas(mohonKertas);
            kertasKandungan3 = strataService.simpanPermohonanKertasKandungan(kertasKandungan3);
        }
        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        return new RedirectResolution(KertasPertimbanganRayuanActionBean.class, "showForm").addParameter("error", error).addParameter("message", msg);

    }

    public String getAsaspertimbangan() {
        return asaspertimbangan;
    }

    public void setAsaspertimbangan(String asaspertimbangan) {
        this.asaspertimbangan = asaspertimbangan;
    }

    public int getBil_bgnn() {
        return bil_bgnn;
    }

    public void setBil_bgnn(int bil_bgnn) {
        this.bil_bgnn = bil_bgnn;
    }

    public int getBil_petak() {
        return bil_petak;
    }

    public void setBil_petak(int bil_petak) {
        this.bil_petak = bil_petak;
    }

    public int getBil_ptkAksr() {
        return bil_ptkAksr;
    }

    public void setBil_ptkAksr(int bil_ptkAksr) {
        this.bil_ptkAksr = bil_ptkAksr;
    }

    public int getBil_tgkt() {
        return bil_tgkt;
    }

    public void setBil_tgkt(int bil_tgkt) {
        this.bil_tgkt = bil_tgkt;
    }

    public String getIdHakmilik() {
        return idHakmilik;
    }

    public void setIdHakmilik(String idHakmilik) {
        this.idHakmilik = idHakmilik;
    }

    public int getJumlah() {
        return jumlah;
    }

    public void setJumlah(int jumlah) {
        this.jumlah = jumlah;
    }

    public int getJumlah_syer() {
        return jumlah_syer;
    }

    public void setJumlah_syer(int jumlah_syer) {
        this.jumlah_syer = jumlah_syer;
    }

    public List<HakmilikUrusan> getListhakmilikUrusan() {
        return listhakmilikUrusan;
    }

    public void setListhakmilikUrusan(List<HakmilikUrusan> listhakmilikUrusan) {
        this.listhakmilikUrusan = listhakmilikUrusan;
    }

    public BangunanPetaAksesoriDAO getMohonAksDAO() {
        return mohonAksDAO;
    }

    public void setMohonAksDAO(BangunanPetaAksesoriDAO mohonAksDAO) {
        this.mohonAksDAO = mohonAksDAO;
    }

    public PermohonanBangunanDAO getMohonBangunanDAO() {
        return mohonBangunanDAO;
    }

    public void setMohonBangunanDAO(PermohonanBangunanDAO mohonBangunanDAO) {
        this.mohonBangunanDAO = mohonBangunanDAO;
    }

    public BangunanPetakDAO getMohonPetakDAO() {
        return mohonPetakDAO;
    }

    public void setMohonPetakDAO(BangunanPetakDAO mohonPetakDAO) {
        this.mohonPetakDAO = mohonPetakDAO;
    }

    public BangunanTingkatDAO getMohonTingkatDAO() {
        return mohonTingkatDAO;
    }

    public void setMohonTingkatDAO(BangunanTingkatDAO mohonTingkatDAO) {
        this.mohonTingkatDAO = mohonTingkatDAO;
    }

    public Pemohon getPemohon() {
        return pemohon;
    }

    public void setPemohon(Pemohon pemohon) {
        this.pemohon = pemohon;
    }

    public PemohonDAO getPemohonDAO() {
        return pemohonDAO;
    }

    public void setPemohonDAO(PemohonDAO pemohonDAO) {
        this.pemohonDAO = pemohonDAO;
    }

    public Permohonan getPermohonan() {
        return permohonan;
    }

    public void setPermohonan(Permohonan permohonan) {
        this.permohonan = permohonan;
    }

    public PermohonanDAO getPermohonanDAO() {
        return permohonanDAO;
    }

    public void setPermohonanDAO(PermohonanDAO permohonanDAO) {
        this.permohonanDAO = permohonanDAO;
    }

    public Pihak getPihak() {
        return pihak;
    }

    public void setPihak(Pihak pihak) {
        this.pihak = pihak;
    }

    public PihakDAO getPihakDAO() {
        return pihakDAO;
    }

    public void setPihakDAO(PihakDAO pihakDAO) {
        this.pihakDAO = pihakDAO;
    }

    public StrataPtService getStrataService() {
        return strataService;
    }

    public void setStrataService(StrataPtService strataService) {
        this.strataService = strataService;
    }

    public String getSyorTP() {
        return syorTP;
    }

    public void setSyorTP(String syorTP) {
        this.syorTP = syorTP;
    }

    public String getUlasanJabatanUkurdanPemetaan() {
        return ulasanJabatanUkurdanPemetaan;
    }

    public void setUlasanJabatanUkurdanPemetaan(String ulasanJabatanUkurdanPemetaan) {
        this.ulasanJabatanUkurdanPemetaan = ulasanJabatanUkurdanPemetaan;
    }

    public Hakmilik getHakmilik() {
        return hakmilik;
    }

    public void setHakmilik(Hakmilik hakmilik) {
        this.hakmilik = hakmilik;
    }

    public HakmilikPihakBerkepentingan getHakmilikPihakBerkepentingan() {
        return hakmilikPihakBerkepentingan;
    }

    public void setHakmilikPihakBerkepentingan(HakmilikPihakBerkepentingan hakmilikPihakBerkepentingan) {
        this.hakmilikPihakBerkepentingan = hakmilikPihakBerkepentingan;
    }

    public List<PermohonanKertasKandungan> getListKand3() {
        return listKand3;
    }

    public void setListKand3(List<PermohonanKertasKandungan> listKand3) {
        this.listKand3 = listKand3;
    }

    public List<PermohonanKertasKandungan> getListKand4() {
        return listKand4;
    }

    public void setListKand4(List<PermohonanKertasKandungan> listKand4) {
        this.listKand4 = listKand4;
    }

    public List<PermohonanKertasKandungan> getListKand5() {
        return listKand5;
    }

    public void setListKand5(List<PermohonanKertasKandungan> listKand5) {
        this.listKand5 = listKand5;
    }

    public HakmilikDAO getHakmilikDAO() {
        return hakmilikDAO;
    }

    public void setHakmilikDAO(HakmilikDAO hakmilikDAO) {
        this.hakmilikDAO = hakmilikDAO;
    }

    public ArrayList<BangunanPetakAksesori> getListMohonAks() {
        return listMohonAks;
    }

    public void setListMohonAks(ArrayList<BangunanPetakAksesori> listMohonAks) {
        this.listMohonAks = listMohonAks;
    }

    public ArrayList<PermohonanBangunan> getListMohonBangunan() {
        return listMohonBangunan;
    }

    public void setListMohonBangunan(ArrayList<PermohonanBangunan> listMohonBangunan) {
        this.listMohonBangunan = listMohonBangunan;
    }

    public ArrayList<BangunanPetak> getListMohonPetak() {
        return listMohonPetak;
    }

    public void setListMohonPetak(ArrayList<BangunanPetak> listMohonPetak) {
        this.listMohonPetak = listMohonPetak;
    }

    public ArrayList<BangunanTingkat> getListMohonTingkat() {
        return listMohonTingkat;
    }

    public void setListMohonTingkat(ArrayList<BangunanTingkat> listMohonTingkat) {
        this.listMohonTingkat = listMohonTingkat;
    }

    public PermohonanKertas getMohonKertas() {
        return mohonKertas;
    }

    public void setMohonKertas(PermohonanKertas mohonKertas) {
        this.mohonKertas = mohonKertas;
    }

    public PermohonanBangunan getMohonbngn() {
        return mohonbngn;
    }

    public void setMohonbngn(PermohonanBangunan mohonbngn) {
        this.mohonbngn = mohonbngn;
    }
}


