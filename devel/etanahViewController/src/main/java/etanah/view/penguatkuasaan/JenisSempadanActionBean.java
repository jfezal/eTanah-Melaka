/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.penguatkuasaan;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.dao.HakmilikDAO;
import etanah.dao.KodKategoriTanahDAO;
import etanah.dao.KodLotDAO;
import etanah.dao.LaporanTanahDAO;
import etanah.dao.LaporanTanahSempadanDAO;
import etanah.dao.PermohonanDAO;
import etanah.model.Hakmilik;
import etanah.model.InfoAudit;
import etanah.model.KodKategoriTanah;
import etanah.model.KodLot;
import etanah.model.LaporanTanah;
import etanah.model.LaporanTanahSempadan;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.service.EnforceService;
import etanah.service.LaporanTanahService;
import org.apache.commons.lang.StringUtils;
import etanah.view.etanahActionBeanContext;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.RedirectResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.StreamingResolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;
import org.apache.log4j.Logger;

/**
 *
 * @author sitifariza.hanim
 */
@UrlBinding("/penguatkuasaan/jenis_sempadan")
public class JenisSempadanActionBean extends AbleActionBean {

    private static Logger logger = Logger.getLogger(JenisSempadanActionBean.class);
    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    EnforceService enforceService;
    @Inject
    LaporanTanahSempadanDAO laporanTanahSempadanDAO;
    @Inject
    LaporanTanahDAO laporanTanahDAO;
    @Inject
    KodKategoriTanahDAO kodKategoriTanahDAO;
    @Inject
    KodLotDAO kodLotDAO;
    @Inject
    HakmilikDAO hakmilikDAO;
    @Inject
    LaporanTanahService laporanTanahService;
    private List<LaporanTanahSempadan> senaraiLaporanTanahSempadan;
    private LaporanTanahSempadan laporanTanahSempadan;
    private String idLaporTanahSpdn;
    private String idLapor;
    private LaporanTanah laporanTanah;
    private Hakmilik hakmilik;
    private String jenisSempadan;
    private String milikKerajaan;
    private String idHakmilik;
    private String keadaanTanah;
    private String kodKategoriTanah;
    private String catatan;
    private String kodLot;
    private String noLot;
    private Permohonan permohonan;
    private String idPermohonan;
    private String kod;
    private Pengguna pguna;
    SimpleDateFormat dateF = new SimpleDateFormat("dd/MM/yyyy");
    private String statusCarian;
    private String noLotCarian;
    private String kodLotCarian;
    private String kodKategoriTanahCarian;
    private String jenisLaporan;

    public String getJenisLaporan() {
        return jenisLaporan;
    }

    public void setJenisLaporan(String jenisLaporan) {
        this.jenisLaporan = jenisLaporan;
    }

    @DefaultHandler
    public Resolution showForm() {
        return new JSP("/penguatkuasaan/maklumat_sempadan.jsp").addParameter("tab", "true");
    }

    public Resolution sempadanPopup() {
        idLapor = getContext().getRequest().getParameter("idLapor");
        jenisLaporan = getContext().getRequest().getParameter("jenisLaporan");
        return new JSP("penguatkuasaan/popup_maklumat_sempadan.jsp").addParameter("popup", "true");
    }

    @Before(stages = {LifecycleStage.BindingAndValidation}, on = {"!sempadanPopup", "!simpan"})
    public void rehydrate() {
        pguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        if (idPermohonan != null) {
            permohonan = permohonanDAO.findById(idPermohonan);
            logger.debug("idLaporTanahSpdn :" + idLaporTanahSpdn);
        }
    }

    public Resolution refreshpage() {

        return new RedirectResolution(LaporanTanahActionBean.class, "locate");
    }

    public Resolution simpan() {
        System.out.println("masuk sini");
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        pguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        permohonan = permohonanDAO.findById(idPermohonan);
        InfoAudit ia = new InfoAudit();
        ia.setDimasukOleh(pguna);
        ia.setTarikhMasuk(new java.util.Date());
        System.out.println(idLapor);
        if (idLapor != null) {
            idLapor = getContext().getRequest().getParameter("idLapor");
            laporanTanah = laporanTanahDAO.findById(Long.parseLong(idLapor));
        } else {
            List<LaporanTanah> listLaporanTanah = laporanTanahService.getListLaporanTanah(permohonan.getIdPermohonan(), jenisLaporan);

            System.out.println("test " + jenisLaporan);

            if (!(listLaporanTanah.isEmpty())) {

                laporanTanah = listLaporanTanah.get(0);
            } else {
                laporanTanah = new LaporanTanah();
            }
            laporanTanah.setInfoAudit(ia);
            laporanTanah.setPermohonan(permohonan);
            laporanTanah.setJenisLaporan(jenisLaporan);
            laporanTanahService.simpanLaporanTanah(laporanTanah);

        }
        idHakmilik = getContext().getRequest().getParameter("idHakmilik");
        System.out.println("id hakmilik: " + idHakmilik);
        if (StringUtils.isNotBlank(idHakmilik)) {
            hakmilik = hakmilikDAO.findById(idHakmilik);
        }

//        if (laporanTanahSempadan == null) {
//            laporanTanahSempadan = new LaporanTanahSempadan();
//
//        }
        System.out.println("masuk sini3");
        laporanTanahSempadan = new LaporanTanahSempadan();
        laporanTanahSempadan.setInfoAudit(ia);
        laporanTanahSempadan.setLaporanTanah(laporanTanah);
        laporanTanahSempadan.setJenisSempadan(jenisSempadan);
        laporanTanahSempadan.setMilikKerajaan(milikKerajaan);
        if (hakmilik != null) {
            System.out.println("hakmilik save : " + hakmilik.getIdHakmilik());
            laporanTanahSempadan.setHakmilik(hakmilik);
        } else {
            laporanTanahSempadan.setHakmilik(null);
        }
        if (kodKategoriTanah != null) {
            KodKategoriTanah kttn = kodKategoriTanahDAO.findById(kodKategoriTanah);
            laporanTanahSempadan.setKodKategoriTanah(kttn);
        } else {
            laporanTanahSempadan.setKodKategoriTanah(null);
        }

        laporanTanahSempadan.setNoLot(noLot);

        if (kodLot != null) {
            KodLot kl = kodLotDAO.findById(kodLot);
            laporanTanahSempadan.setKodLot(kl);
        } else {
            laporanTanahSempadan.setKodLot(null);
        }
        laporanTanahSempadan.setCatatan(catatan);

        enforceService.simpanSempadan(laporanTanahSempadan);
        addSimpleMessage("Maklumat telah berjaya disimpan.");
//        return new JSP("penguatkuasaan/maklumat_saksi.jsp").addParameter("tab", "true");
        rehydrate();
        return new RedirectResolution(LaporanTanahActionBean.class, "locate");
    }

    public Resolution edit() {
        System.out.println("masuk sini");
        pguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        idLaporTanahSpdn = getContext().getRequest().getParameter("idLaporTanahSpdn");
        System.out.println("idLaporTanahSpdn" + idLaporTanahSpdn);
        laporanTanahSempadan = laporanTanahSempadanDAO.findById(Long.parseLong(idLaporTanahSpdn));
        idHakmilik = getContext().getRequest().getParameter("idHakmilik");
        System.out.println("id hakmilik: " + idHakmilik);
        hakmilik = hakmilikDAO.findById(idHakmilik);

        InfoAudit ia = new InfoAudit();

        System.out.println("id permohonan:" + idPermohonan);
//        laporanTanahSempadan.setLaporanTanah(laporanTanah);
        laporanTanahSempadan.setJenisSempadan(jenisSempadan);
        if (hakmilik != null) {
            System.out.println("hakmilik save : " + hakmilik.getIdHakmilik());
            laporanTanahSempadan.setHakmilik(hakmilik);
        } else {
            laporanTanahSempadan.setHakmilik(null);
        }
        if (kodKategoriTanah != null) {
            KodKategoriTanah kttn = kodKategoriTanahDAO.findById(kodKategoriTanah);
            laporanTanahSempadan.setKodKategoriTanah(kttn);
        } else {
            laporanTanahSempadan.setKodKategoriTanah(null);
        }
        laporanTanahSempadan.setNoLot(noLot);
        if (kodLot != null) {
            KodLot kl = kodLotDAO.findById(kodLot);
            laporanTanahSempadan.setKodLot(kl);
        } else {
            laporanTanahSempadan.setKodLot(null);
        }

        laporanTanahSempadan.setCatatan(catatan);

//        if (ia == null) {
//            ia = new InfoAudit();
        ia.setDimasukOleh(pguna);
        ia.setTarikhMasuk(new Date());
//            permohonanSaksi.setInfoAudit(ia);
//        } else {
        ia.setDiKemaskiniOleh(pguna);
        ia.setTarikhKemaskini(new java.util.Date());
        laporanTanahSempadan.setInfoAudit(ia);

//        }
        System.out.println("saveEdit");
        enforceService.updateSempadan(laporanTanahSempadan);

        addSimpleMessage("Maklumat telah berjaya disimpan.");
//        return new ForwardResolution("penguatkuasaan/maklumat_orang_disyaki.jsp").addParameter("tab", "true");
        return new RedirectResolution(LaporanTanahActionBean.class, "locate");
    }

    public Resolution deleteSempadan() {

        idLaporTanahSpdn = getContext().getRequest().getParameter("idLaporTanahSpdn");
        System.out.println("idLaporTanahSpdn" + idLaporTanahSpdn);
        laporanTanahSempadan = laporanTanahSempadanDAO.findById(Long.parseLong(idLaporTanahSpdn));

        if (laporanTanahSempadan != null) {

            enforceService.deleteAllSempadan(laporanTanahSempadan);
        }
        return new RedirectResolution(LaporanTanahActionBean.class, "locate");

    }

    public Resolution editSempadan() {
        idLaporTanahSpdn = getContext().getRequest().getParameter("idLaporTanahSpdn");
        laporanTanahSempadan = laporanTanahSempadanDAO.findById(Long.parseLong(idLaporTanahSpdn));
        idHakmilik = getContext().getRequest().getParameter("idHakmilik");
        if (laporanTanahSempadan.getHakmilik() != null) {
            idHakmilik = laporanTanahSempadan.getHakmilik().getIdHakmilik();
        }
        jenisSempadan = laporanTanahSempadan.getJenisSempadan();
        milikKerajaan = laporanTanahSempadan.getMilikKerajaan();
        if (laporanTanahSempadan.getKodKategoriTanah() != null) {
            kodKategoriTanah = laporanTanahSempadan.getKodKategoriTanah().getKod();
        }
        noLot = laporanTanahSempadan.getNoLot();

        if (laporanTanahSempadan.getKodLot() != null) {
            kodLot = laporanTanahSempadan.getKodLot().getKod();
        }

        catatan = laporanTanahSempadan.getCatatan();

        return new JSP("penguatkuasaan/popup_edit_sempadan.jsp").addParameter("popup", "true");
    }

    public Resolution tanahSekelilingDetails() {
        idLaporTanahSpdn = getContext().getRequest().getParameter("idLaporTanahSpdn");
        laporanTanahSempadan = laporanTanahSempadanDAO.findById(Long.parseLong(idLaporTanahSpdn));
        idHakmilik = getContext().getRequest().getParameter("idHakmilik");
        if (laporanTanahSempadan.getHakmilik() != null) {
            idHakmilik = laporanTanahSempadan.getHakmilik().getIdHakmilik();
        }
        jenisSempadan = laporanTanahSempadan.getJenisSempadan();
        milikKerajaan = laporanTanahSempadan.getMilikKerajaan();
        if (laporanTanahSempadan.getKodKategoriTanah() != null) {
            kodKategoriTanah = laporanTanahSempadan.getKodKategoriTanah().getKod();
        }
        noLot = laporanTanahSempadan.getNoLot();

        if (laporanTanahSempadan.getKodLot() != null) {
            kodLot = laporanTanahSempadan.getKodLot().getKod();
        }

        catatan = laporanTanahSempadan.getCatatan();
        return new JSP("penguatkuasaan/popup_view_tnh_sekeliling.jsp").addParameter("popup", "true");
    }

    public Resolution viewSaksi() {
        idLaporTanahSpdn = getContext().getRequest().getParameter("idLaporTanahSpdn");
        laporanTanahSempadan = laporanTanahSempadanDAO.findById(Long.parseLong(idLaporTanahSpdn));
        return new JSP("penguatkuasaan/popup_view_sempadan.jsp").addParameter("popup", "true");
    }

    public Resolution semakIdHakmilik() {
        String result = "";

        idHakmilik = getContext().getRequest().getParameter("idHakmilik");
        System.out.println("IdHakmilik ialah :" + idHakmilik);
        Hakmilik idHakmilik2 = enforceService.semakIdHakmilik(idHakmilik);
        if (idHakmilik2 != null) {
            result = "exist";
        } else {
            result = "not exist";
        }
        return new StreamingResolution("test", result);
    }

    public Resolution findHakmilik() {
        logger.info("-----------findHakmilik-------------");
        idHakmilik = getContext().getRequest().getParameter("idHakmilik");
        idLaporTanahSpdn = "";
        idLaporTanahSpdn = getContext().getRequest().getParameter("idLaporTanahSpdn");
        logger.info("id laporan tanah sempadan :" + idLaporTanahSpdn);
        logger.info("id hakmilik carian :" + idHakmilik);
        hakmilik = enforceService.semakIdHakmilik(idHakmilik);

        if (hakmilik != null) {
            statusCarian = "W"; //W = wujud
            noLot = hakmilik.getNoLot();
            if (hakmilik.getLot() != null) {
                kodLotCarian = hakmilik.getLot().getKod();
            }
            if (hakmilik.getKegunaanTanah().getKategoriTanah().getKod() != null) {
                kodKategoriTanahCarian = hakmilik.getKegunaanTanah().getKategoriTanah().getKod();
            }
        } else {
            statusCarian = "TW"; //TW = Tidak Wujud
        }

        String popup = "";

        if (idLaporTanahSpdn != null && StringUtils.isNotBlank(idLaporTanahSpdn)) {
            logger.info("-----------edit-------------");
            popup = "penguatkuasaan/popup_edit_sempadan.jsp";
        } else {
            logger.info("-----------add-------------");
            popup = "penguatkuasaan/popup_maklumat_sempadan.jsp";
        }

        return new JSP(popup).addParameter("popup", "true");
    }

    public String getIdPermohonan() {
        return idPermohonan;
    }

    public void setIdPermohonan(String idPermohonan) {
        this.idPermohonan = idPermohonan;
    }

    public Permohonan getPermohonan() {
        return permohonan;
    }

    public void setPermohonan(Permohonan permohonan) {
        this.permohonan = permohonan;
    }

    public Pengguna getPguna() {
        return pguna;
    }

    public void setPguna(Pengguna pguna) {
        this.pguna = pguna;
    }

    public String getKod() {
        return kod;
    }

    public void setKod(String kod) {
        this.kod = kod;
    }

    public LaporanTanahSempadan getLaporanTanahSempadan() {
        return laporanTanahSempadan;
    }

    public void setLaporanTanahSempadan(LaporanTanahSempadan laporanTanahSempadan) {
        this.laporanTanahSempadan = laporanTanahSempadan;
    }

    public List<LaporanTanahSempadan> getSenaraiLaporanTanahSempadan() {
        return senaraiLaporanTanahSempadan;
    }

    public void setSenaraiLaporanTanahSempadan(List<LaporanTanahSempadan> senaraiLaporanTanahSempadan) {
        this.senaraiLaporanTanahSempadan = senaraiLaporanTanahSempadan;
    }

    public String getIdLaporTanahSpdn() {
        return idLaporTanahSpdn;
    }

    public void setIdLaporTanahSpdn(String idLaporTanahSpdn) {
        this.idLaporTanahSpdn = idLaporTanahSpdn;
    }

    public String getCatatan() {
        return catatan;
    }

    public void setCatatan(String catatan) {
        this.catatan = catatan;
    }

    public Hakmilik getHakmilik() {
        return hakmilik;
    }

    public void setHakmilik(Hakmilik hakmilik) {
        this.hakmilik = hakmilik;
    }

    public String getJenisSempadan() {
        return jenisSempadan;
    }

    public void setJenisSempadan(String jenisSempadan) {
        this.jenisSempadan = jenisSempadan;
    }

    public String getKeadaanTanah() {
        return keadaanTanah;
    }

    public void setKeadaanTanah(String keadaanTanah) {
        this.keadaanTanah = keadaanTanah;
    }

    public String getKodLot() {
        return kodLot;
    }

    public void setKodLot(String kodLot) {
        this.kodLot = kodLot;
    }

    public LaporanTanah getLaporanTanah() {
        return laporanTanah;
    }

    public void setLaporanTanah(LaporanTanah laporanTanah) {
        this.laporanTanah = laporanTanah;
    }

    public String getMilikKerajaan() {
        return milikKerajaan;
    }

    public void setMilikKerajaan(String milikKerajaan) {
        this.milikKerajaan = milikKerajaan;
    }

    public String getNoLot() {
        return noLot;
    }

    public void setNoLot(String noLot) {
        this.noLot = noLot;
    }

    public String getIdHakmilik() {
        return idHakmilik;
    }

    public void setIdHakmilik(String idHakmilik) {
        this.idHakmilik = idHakmilik;
    }

    public String getKodKategoriTanah() {
        return kodKategoriTanah;
    }

    public void setKodKategoriTanah(String kodKategoriTanah) {
        this.kodKategoriTanah = kodKategoriTanah;
    }

    public String getIdLapor() {
        return idLapor;
    }

    public void setIdLapor(String idLapor) {
        this.idLapor = idLapor;
    }

    public String getStatusCarian() {
        return statusCarian;
    }

    public void setStatusCarian(String statusCarian) {
        this.statusCarian = statusCarian;
    }

    public String getKodLotCarian() {
        return kodLotCarian;
    }

    public void setKodLotCarian(String kodLotCarian) {
        this.kodLotCarian = kodLotCarian;
    }

    public String getNoLotCarian() {
        return noLotCarian;
    }

    public void setNoLotCarian(String noLotCarian) {
        this.noLotCarian = noLotCarian;
    }

    public String getKodKategoriTanahCarian() {
        return kodKategoriTanahCarian;
    }

    public void setKodKategoriTanahCarian(String kodKategoriTanahCarian) {
        this.kodKategoriTanahCarian = kodKategoriTanahCarian;
    }
}
