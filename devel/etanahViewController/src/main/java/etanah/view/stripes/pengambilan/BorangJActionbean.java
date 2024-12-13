/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.stripes.pengambilan;

/**
 *
 * @author nordiyana
 */
import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.dao.PermohonanDAO;
import etanah.model.HakmilikPermohonan;
import etanah.model.Permohonan;
import etanah.model.Pengguna;
import etanah.model.InfoAudit;
import etanah.model.Hakmilik;
import etanah.model.KodUOM;
import etanah.dao.KodUOMDAO;
import etanah.dao.HakmilikDAO;
import etanah.service.common.HakmilikService;
import etanah.service.common.HakmilikPermohonanService;
import etanah.service.PengambilanService;
import java.util.List;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.HttpCache;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.RedirectResolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;
import java.util.Collections;
import java.util.LinkedList;
import net.sourceforge.stripes.action.ForwardResolution;
import etanah.model.HakmilikPihakBerkepentingan;
import etanah.model.AmbilPampasan;
import etanah.model.ambil.HakmilikPerbicaraan;
import etanah.model.ambil.PerbicaraanKehadiran;
import etanah.model.ambil.Penilaian;
import etanah.model.FasaPermohonan;
import etanah.model.FolderDokumen;
import etanah.model.PermohonanLaporanBangunan;
import etanah.model.Dokumen;
import etanah.model.KandunganFolder;
import etanah.service.common.NotisPenerimaanService;
import java.math.BigDecimal;
import java.util.ArrayList;
import etanah.view.etanahActionBeanContext;
import java.math.RoundingMode;
import javax.swing.JOptionPane;
import java.io.*;
import java.util.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import etanah.service.LaporanTanahService;
import etanah.service.common.LelongService;
import net.sourceforge.stripes.action.StreamingResolution;
import org.apache.log4j.Logger;

@UrlBinding("/pengambilan/borangJ")
public class BorangJActionbean extends AbleActionBean {

    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    Permohonan permohonan;
    @Inject
    HakmilikPermohonanService hakmilikpermohonanservice;
    @Inject
    PengambilanService service;
    @Inject
    HakmilikService hakmilikService;
    @Inject
    NotisPenerimaanService notisPenerimaanService;
    String idHakmilik;
    @Inject
    Hakmilik hakmilik;
    @Inject
    KodUOM KodOUM;
    @Inject
    HakmilikDAO hakmilikDAO;
    @Inject
    KodUOMDAO kodUOMDAO;
    @Inject
    LaporanTanahService laporanTanahService;
    @Inject
    LelongService lelongService;
    private List<HakmilikPermohonan> hakmilikPermohonanList;
    private List<String> tempohKeteranganBertulis = new ArrayList<String>();
    private HakmilikPermohonan hakmilikPermohonan;
    private FasaPermohonan fasaPermohonan;
    private HakmilikPerbicaraan hakmilikPerbicaraan;
    private PerbicaraanKehadiran perbicaraanKehadiran;
    private List<HakmilikPerbicaraan> hakmilikPerbicaraanList;
    private List<PerbicaraanKehadiran> perbicaraanKehadiranList;
    private List<PermohonanLaporanBangunan> permohonanLaporanBangunanList;
    private boolean borangJ = false;
    private String mesej;
    private static final Logger LOGGER = Logger.getLogger(BorangJActionbean.class);
    private Pengguna pengguna;
    private String idPermohonan;

    @DefaultHandler
    public Resolution showForm() {
        if (borangJ) {
            addSimpleMessage(mesej);

        }
//        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        return new JSP("pengambilan/BorangJ.jsp").addParameter("tab", "true");
    }

    public Resolution showForm3() {

//        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        return new JSP("pengambilan/Borang567.jsp").addParameter("tab", "true");
    }

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {
        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        if (idPermohonan != null) {

            permohonan = permohonanDAO.findById(idPermohonan);
            hakmilikPermohonanList = permohonan.getSenaraiHakmilik();

//            for (int i = 0; i < hakmilikPermohonanList.size(); i++) {
//                hakmilikPerbicaraan = notisPenerimaanService.getHakmilikPerbicaraanByidMH(hakmilikPermohonanList.get(i).getId());
//                if (tempohKeteranganBertulis.size() != 0) {
//                    if (tempohKeteranganBertulis.get(i) != null) {
//                        tempohKeteranganBertulis.add(hakmilikPerbicaraan.getTempohPengosongan().toString());
//                    }
//                    
//                    
//                }
//                
//            }

            permohonanLaporanBangunanList = new ArrayList<PermohonanLaporanBangunan>();
            permohonanLaporanBangunanList = laporanTanahService.getLaporBngnByIdPermohonan(idPermohonan);
            System.out.println("permohonanLaporanBangunanList" + permohonanLaporanBangunanList.size());
//             if(permohonanLaporanBangunanList==null){
//             borangJ=true;
//             addSimpleMessage("Berdasarkan Laporan Tanah yang disediakan oleh Penolong Pegawai Tanah, tidak ada sebarang bangunan terdapat di lot ini.");
//
//
//             }
            if (permohonanLaporanBangunanList.size() != 0) {
                System.out.println("ada bangunan");
                setBorangJ(false);


            } else {
                System.out.println("tiada bangunan");
                setBorangJ(true);
                setMesej("Berdasarkan Laporan Tanah yang disediakan oleh Penolong Pegawai Tanah, tidak ada sebarang bangunan terdapat di dalam permohonan ini.");


            }

            for (int i = 0; i < hakmilikPermohonanList.size(); i++) {
                try {

                    if (hakmilikPermohonanList.get(i).getTempohPengosongan() == null) {
                        tempohKeteranganBertulis.add("");
                    } else if (hakmilikPermohonanList.get(i).getTempohPengosongan() != null) {
                        tempohKeteranganBertulis.add(hakmilikPermohonanList.get(i).getTempohPengosongan().toString());

                    }

                } catch (Exception e) {
                    addSimpleError("Invalid Data");
                }
            }
        }
    }

    public Resolution simpan() {
        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        idHakmilik = (String) getContext().getRequest().getParameter("idHakmilik");

        if (idPermohonan != null) {
            permohonan = permohonanDAO.findById(idPermohonan);
            hakmilikPermohonanList = permohonan.getSenaraiHakmilik();
            for (int i = 0; i < hakmilikPermohonanList.size(); i++) {
                HakmilikPermohonan hp = hakmilikPermohonanList.get(i);
                hakmilikPerbicaraan = service.getHakmilikPerbicaraanPampasan(hp.getId());
                if (hakmilikPerbicaraan != null) {
                    try {
                        if (i < tempohKeteranganBertulis.size()) {
                            if (tempohKeteranganBertulis.get(i) != null) {
                                hakmilikPerbicaraan.setTempohPengosongan(Integer.parseInt(tempohKeteranganBertulis.get(i)));
                            }

                        }
                    } catch (Exception e) {
                        addSimpleError("Invalid Data");
                    }
                    service.saveSingleHakmilikPerbicaraan(hakmilikPerbicaraan);


                }
            }



        }
        rehydrate();
        addSimpleMessage("Maklumat telah berjaya disimpan.");
        return new ForwardResolution("/WEB-INF/jsp/pengambilan/BorangJ.jsp").addParameter("tab", "true");
    }

    public Resolution genReport() {
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        Permohonan p = permohonanDAO.findById(idPermohonan);
        LOGGER.info("genReport :: start");
        LOGGER.info("generate report start.");
        String msg = "Laporan telah dijana. Sila buat semakan sebelum cetakan.";
        String gen = "";
        String genJ = "";
        String code = "";


        genJ = "ACQDocJ_NS.rdf";
        code = "J";
        try {
            LOGGER.info("genReportFromXML");
            lelongService.reportGen(p, genJ, code, pengguna);
        } catch (Exception ex) {
            return new StreamingResolution("text/plain", "Dokumen Tidak Dapat Dijana." + ex.getMessage());
        }




        LOGGER.info("genReport :: finish");
        return new StreamingResolution("text/plain", msg);
    }

    public Resolution genReport567() {
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        Permohonan p = permohonanDAO.findById(idPermohonan);
        LOGGER.info("genReport :: start");
        LOGGER.info("generate report start.");
        String msg = "Laporan telah dijana. Sila buat semakan sebelum cetakan.";
        String gen = "";
        String genJ = "";
        String gen7 = "";
        String code = "";
        String code7 = "";

        fasaPermohonan = service.findFasaPermohonan126Keputusan(idPermohonan);
        if (fasaPermohonan.getKeputusan().getKod().equalsIgnoreCase("L")) {
            genJ = "ACQBorang5_NS.rdf";
            code = "5";
            msg = "Borang 5 telah dijana. Sila buat semakan sebelum cetakan.";
        } else if (fasaPermohonan.getKeputusan().getKod().equalsIgnoreCase("TL")) {
            genJ = "ACQBorang6_NS.rdf";
            code = "6";
            gen7 = "ACQBorang7_NS.rdf";
            code7 = "7";

            try {
                LOGGER.info("genReportFromXML");
                lelongService.reportGen(p, gen7, code7, pengguna);
            } catch (Exception ex) {
                return new StreamingResolution("text/plain", "Dokumen Tidak Dapat Dijana." + ex.getMessage());
            }
            msg = "Borang 6 dan 7 telah dijana. Sila buat semakan sebelum cetakan.";
        }
        try {
            LOGGER.info("genReportFromXML");
            lelongService.reportGen(p, genJ, code, pengguna);
        } catch (Exception ex) {
            return new StreamingResolution("text/plain", "Dokumen Tidak Dapat Dijana." + ex.getMessage());
        }
        LOGGER.info("genReport :: finish");
        return new StreamingResolution("text/plain", msg);
    }

    public Resolution simpanHakmilik() {
        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        Permohonan p = permohonanDAO.findById(idPermohonan);
        String kod = null;
//        JOptionPane.showConfirmDialog(null, hakmilikPermohonanList.size());
        if (tempohKeteranganBertulis.isEmpty()) {
            addSimpleError("Sila Masukkan Tempoh Pengosongan Bangunan");

        } else {

            for (int i = 0; i < hakmilikPermohonanList.size(); i++) {
                HakmilikPermohonan hmp = hakmilikPermohonanList.get(i);


                try {
                    if (i < tempohKeteranganBertulis.size()) {
                        if (tempohKeteranganBertulis.get(i) != null) {
                            hmp.setTempohPengosongan(Integer.parseInt(tempohKeteranganBertulis.get(i)));
                        }
                    }
                } catch (Exception e) {
                    addSimpleError("Invalid Data");
                }
                hakmilikpermohonanservice.saveSingleHakmilikPermohonan(hmp);


            }
        }
//        rehydrate();

//        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        addSimpleMessage("Maklumat telah berjaya disimpan.");
        return new ForwardResolution("/WEB-INF/jsp/pengambilan/BorangJ.jsp").addParameter("tab", "true");
    }

    public HakmilikPerbicaraan getHakmilikPerbicaraan() {
        return hakmilikPerbicaraan;
    }

    public void setHakmilikPerbicaraan(HakmilikPerbicaraan hakmilikPerbicaraan) {
        this.hakmilikPerbicaraan = hakmilikPerbicaraan;
    }

    public List<HakmilikPerbicaraan> getHakmilikPerbicaraanList() {
        return hakmilikPerbicaraanList;
    }

    public void setHakmilikPerbicaraanList(List<HakmilikPerbicaraan> hakmilikPerbicaraanList) {
        this.hakmilikPerbicaraanList = hakmilikPerbicaraanList;
    }

    public HakmilikPermohonan getHakmilikPermohonan() {
        return hakmilikPermohonan;
    }

    public void setHakmilikPermohonan(HakmilikPermohonan hakmilikPermohonan) {
        this.hakmilikPermohonan = hakmilikPermohonan;
    }

    public List<HakmilikPermohonan> getHakmilikPermohonanList() {
        return hakmilikPermohonanList;
    }

    public void setHakmilikPermohonanList(List<HakmilikPermohonan> hakmilikPermohonanList) {
        this.hakmilikPermohonanList = hakmilikPermohonanList;
    }

    public PerbicaraanKehadiran getPerbicaraanKehadiran() {
        return perbicaraanKehadiran;
    }

    public void setPerbicaraanKehadiran(PerbicaraanKehadiran perbicaraanKehadiran) {
        this.perbicaraanKehadiran = perbicaraanKehadiran;
    }

    public List<PerbicaraanKehadiran> getPerbicaraanKehadiranList() {
        return perbicaraanKehadiranList;
    }

    public void setPerbicaraanKehadiranList(List<PerbicaraanKehadiran> perbicaraanKehadiranList) {
        this.perbicaraanKehadiranList = perbicaraanKehadiranList;
    }

    public List<String> getTempohKeteranganBertulis() {
        return tempohKeteranganBertulis;
    }

    public void setTempohKeteranganBertulis(List<String> tempohKeteranganBertulis) {
        this.tempohKeteranganBertulis = tempohKeteranganBertulis;
    }

    public List<PermohonanLaporanBangunan> getPermohonanLaporanBangunanList() {
        return permohonanLaporanBangunanList;
    }

    public void setPermohonanLaporanBangunanList(List<PermohonanLaporanBangunan> permohonanLaporanBangunanList) {
        this.permohonanLaporanBangunanList = permohonanLaporanBangunanList;
    }

    public String getMesej() {
        return mesej;
    }

    public void setMesej(String mesej) {
        this.mesej = mesej;
    }

    public boolean isBorangJ() {
        return borangJ;
    }

    public void setBorangJ(boolean borangJ) {
        this.borangJ = borangJ;
    }
}
