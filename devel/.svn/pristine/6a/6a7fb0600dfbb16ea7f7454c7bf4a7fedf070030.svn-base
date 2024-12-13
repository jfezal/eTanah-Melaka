/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.stripes.pelupusan;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import electric.xml.ParseException;
import etanah.dao.PermohonanBahanBatuanDAO;
import etanah.dao.PermohonanDAO;
import etanah.dao.LaporanTanahDAO;
import etanah.dao.HakmilikDAO;
import etanah.dao.PermohonanJenteraDAO;
import etanah.model.HakmilikPermohonan;
import etanah.model.InfoAudit;
import etanah.model.KodBahanBatu;
import etanah.model.KodBandarPekanMukim;
import etanah.model.Pemohon;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.model.PermohonanBahanBatuan;
import etanah.model.PermohonanJentera;
import etanah.model.Pihak;
import etanah.service.PelupusanService;
import etanah.view.etanahActionBeanContext;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import etanah.view.etanahActionBeanContext;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.RedirectResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;
import org.apache.log4j.Logger;
import etanah.model.ImejLaporan;
import etanah.model.LaporanTanah;
import etanah.service.LaporanTanahService;
import etanah.view.stripes.pelupusan.disClass.DisLaporanTanahService;
import java.util.ArrayList;

/**
 *
 * @author Akmal
 */
@UrlBinding("/pelupusan/maklumat_rayuan")
public class MaklumatRayuanActionBean extends AbleActionBean {

    private static Logger logger = Logger.getLogger(MaklumatRayuanActionBean.class);
    @Inject
    PermohonanBahanBatuanDAO permohonanBahanBatuanDAO;
    @Inject
    PelupusanService pelupusanService;
    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    HakmilikDAO hakmilikDAO;
    @Inject
    DisLaporanTanahService disLaporanTanahService;
    @Inject
    private etanah.Configuration conf;
    private Permohonan permohonan;
    private Permohonan permohonanSebelum;
    private HakmilikPermohonan hakmilikPermohonan;
    private List<HakmilikPermohonan> hakmilikPermohonanList;
    private List<Pemohon> pemohonList;
    private String idPermohonanSebelum;
    private String id2PermohonanSebelum;
    private String idPermohonan;
    private Pengguna pengguna;
    private Pemohon pemohon;
    private Pihak pihak;
    boolean status = false;
    boolean simpan = false;
    private String tujuan;
    private Date tarikhPermohonan;
    private String kodNegeri;
    private Permohonan idPermohonanRAYL;
    private Permohonan idPermohonanPBMT;

    @DefaultHandler
    public Resolution showForm() {
//        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        status = false;
        simpan = true;
        return new JSP("pelupusan/rayuan/maklumat_rayuan_mlk.jsp").addParameter("tab", "true");
    }

    public Resolution viewForm() {
        status = true;
        return new JSP("pelupusan/rayuan/maklumat_rayuan_mlk.jsp").addParameter("tab", "true");
    }

    public Resolution refreshCari() {
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        getContext().getRequest().setAttribute("status", Boolean.FALSE);
        permohonan = permohonanDAO.findById(idPermohonan);
        permohonan.setPermohonanSebelum(null);
        pelupusanService.simpanPermohonan(permohonan);
        idPermohonanSebelum = "";
        rehydrate();
        addSimpleError("Rekod permohonan sebelum telah dihapuskan.");
        return new JSP("pelupusan/rayuan/maklumat_rayuan_mlk.jsp").addParameter("tab", "true");
    }

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {
        pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = permohonanDAO.findById(idPermohonan);
        kodNegeri = conf.getProperty("kodNegeri");
        if (permohonan != null) {

            if (permohonan != null) {
                String[] tname = {"permohonan"};
                Object[] tvalue = {permohonan};

                pemohonList = pelupusanService.findPemohonByIdPermohonan(idPermohonan);
            }

            if (permohonan.getPermohonanSebelum() != null) {
                permohonanSebelum = permohonan.getPermohonanSebelum();
//                pemohon = pelupusanService.findPemohonByIdPemohon(permohonanSebelum.getIdPermohonan()) ;
                List<Pemohon> pemohonList = new ArrayList<Pemohon>();
                pemohonList = pelupusanService.findPemohonByIdPermohonan(permohonanSebelum.getIdPermohonan());
                if (!pemohonList.isEmpty()) {
                    pemohon = pemohonList.get(0);
                }
                hakmilikPermohonanList = permohonanSebelum.getSenaraiHakmilik();
                getContext().getRequest().setAttribute("status", Boolean.TRUE);
                getContext().getRequest().setAttribute("simpan", Boolean.FALSE);

                //for RLPTG
                if (kodNegeri.equalsIgnoreCase("05")) {
                    if (permohonan.getKodUrusan().getKod().equalsIgnoreCase("RLPTG")) {
                        idPermohonanRAYL = permohonanDAO.findById(permohonan.getPermohonanSebelum().getIdPermohonan());
                        System.out.println("::::::::::id RAYL" + idPermohonanRAYL.getIdPermohonan());
                        if (idPermohonanRAYL != null) {
                            if (idPermohonanRAYL.getPermohonanSebelum() != null) {
                                idPermohonanPBMT = permohonanDAO.findById(idPermohonanRAYL.getPermohonanSebelum().getIdPermohonan());
                                permohonanSebelum = idPermohonanPBMT;
                                hakmilikPermohonanList = idPermohonanPBMT.getSenaraiHakmilik();

                                pemohonList = pelupusanService.findPemohonByIdPermohonan(idPermohonanPBMT.getIdPermohonan());
                                if (!pemohonList.isEmpty()) {
                                    pemohon = pemohonList.get(0);
                                }

                            }
                        }
                    }
                }

            }
        }
        //idPermohonanSebelum = permohonanDAO.findById(idPermohonan);



    }

    public Resolution searchId() {
        idPermohonanSebelum = getContext().getRequest().getParameter("idPermohonanSebelum");
        // idPermohonanSebelum = a;
        logger.info("idPermohonanSebelum :" + idPermohonanSebelum);
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = permohonanDAO.findById(idPermohonan);
        permohonanSebelum = pelupusanService.findPermohonanByIdPermohonanWithoutKodUrusan(idPermohonanSebelum);
        if (permohonanSebelum != null) {
//            pemohon = pelupusanService.findPemohonByIdPemohon(permohonanSebelum.getIdPermohonan());
            if (permohonan.getKodUrusan().getKod().equals("PBGSA")) {
                if (permohonanSebelum.getKodUrusan().getKod().equals("PWGSA")) {
                    List<Pemohon> pemohonList = new ArrayList<Pemohon>();
                    pemohonList = pelupusanService.findPemohonByIdPermohonan(permohonanSebelum.getIdPermohonan());
                    if (!pemohonList.isEmpty()) {
                        pemohon = pemohonList.get(0);
                    }

                    hakmilikPermohonanList = permohonanSebelum.getSenaraiHakmilik();
                    if (pemohon != null) {
                        if (pemohon.getPihak() != null) {
                            pihak = pemohon.getPihak();
                        }
                    }

                    getContext().getRequest().setAttribute("status", Boolean.TRUE);
                    getContext().getRequest().setAttribute("simpan", Boolean.TRUE);
                    status = false;
                    addSimpleMessage("Maklumat dijumpai");
                } else {
                    addSimpleError("Id Permohonan ini bukan urusan Permohonan Berkelompok GSA");
                }
            } else if (permohonan.getKodUrusan().getKod().equals("PTGSA")) {
                if (permohonanSebelum.getKodUrusan().getKod().equals("PBGSA")) {
                    List<Pemohon> pemohonList = new ArrayList<Pemohon>();
                    pemohonList = pelupusanService.findPemohonByIdPermohonan(permohonanSebelum.getIdPermohonan());
                    if (!pemohonList.isEmpty()) {
                        pemohon = pemohonList.get(0);
                    }

                    hakmilikPermohonanList = permohonanSebelum.getSenaraiHakmilik();
                    if (pemohon != null) {
                        if (pemohon.getPihak() != null) {
                            pihak = pemohon.getPihak();
                        }
                    }

                    getContext().getRequest().setAttribute("status", Boolean.TRUE);
                    getContext().getRequest().setAttribute("simpan", Boolean.TRUE);
                    status = false;
                    addSimpleMessage("Maklumat dijumpai");
                } else {
                    addSimpleError("Id Permohonan ini bukan urusan Permohonan Berkelompok GSA");
                }
            } else {
                List<Pemohon> pemohonList = new ArrayList<Pemohon>();
                pemohonList = pelupusanService.findPemohonByIdPermohonan(permohonanSebelum.getIdPermohonan());
                if (!pemohonList.isEmpty()) {
                    pemohon = pemohonList.get(0);
                }

                hakmilikPermohonanList = permohonanSebelum.getSenaraiHakmilik();
                if (pemohon != null) {
                    if (pemohon.getPihak() != null) {
                        pihak = pemohon.getPihak();
                    }
                }

                getContext().getRequest().setAttribute("status", Boolean.TRUE);
                getContext().getRequest().setAttribute("simpan", Boolean.TRUE);
                status = false;
                addSimpleMessage("Maklumat dijumpai");
            }


        } else {

            getContext().getRequest().setAttribute("status", Boolean.FALSE);
            addSimpleError("Maklumat tidak dijumpai");
        }

        return new JSP("pelupusan/rayuan/maklumat_rayuan_mlk.jsp").addParameter("tab", "true");
    }

    public Resolution simpan() {

        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = permohonanDAO.findById(idPermohonan);
        idPermohonanSebelum = getContext().getRequest().getParameter("idPermohonanSebelum");
        permohonanSebelum = permohonanDAO.findById(idPermohonanSebelum);

        permohonan.setPermohonanSebelum(permohonanSebelum);

        pelupusanService.simpanPermohonan(permohonan);

        if (permohonan.getKodUrusan().getKod().equals("PTGSA") || permohonan.getKodUrusan().getKod().equals("PBGSA")) {
            HakmilikPermohonan hakmilikPermohonanSblm = new HakmilikPermohonan();
            //HakmilikPermohonan hakmilikmohon = new HakmilikPermohonan();
            hakmilikPermohonanSblm = (HakmilikPermohonan) disLaporanTanahService.getPelupusanService().findByIdPermohonan(permohonan.getPermohonanSebelum().getIdPermohonan());
            HakmilikPermohonan hakmilikmohon = (HakmilikPermohonan) disLaporanTanahService.getPelupusanService().findByIdPermohonan(permohonan.getIdPermohonan());
            if (hakmilikmohon == null) {
                hakmilikmohon = new HakmilikPermohonan();
                hakmilikmohon.setBandarPekanMukimBaru(hakmilikPermohonanSblm.getBandarPekanMukimBaru());
                hakmilikmohon.setCatatan(hakmilikPermohonanSblm.getCatatan());
                hakmilikmohon.setHakmilik(hakmilikmohon.getHakmilik());
                hakmilikmohon.setInfoAudit(hakmilikPermohonanSblm.getInfoAudit());
                hakmilikmohon.setJarak(hakmilikPermohonanSblm.getJarak());
                hakmilikmohon.setJarakDari(hakmilikPermohonanSblm.getJarakDari());
                hakmilikmohon.setJarakDariKediaman(hakmilikPermohonanSblm.getJarakDariKediaman());
                hakmilikmohon.setJarakDariKediamanUom(hakmilikPermohonanSblm.getJarakDariKediamanUom());
                hakmilikmohon.setJarakDariNama(hakmilikPermohonanSblm.getJarakDariNama());
                hakmilikmohon.setJenisPenggunaanTanah(hakmilikPermohonanSblm.getJenisPenggunaanTanah());
                hakmilikmohon.setKategoriTanahBaru(hakmilikPermohonanSblm.getKategoriTanahBaru());
                hakmilikmohon.setKodDUN(hakmilikPermohonanSblm.getKodDUN());
                hakmilikmohon.setKodHakmilik(hakmilikPermohonanSblm.getKodHakmilik());
                hakmilikmohon.setKodHakmilikSementara(hakmilikPermohonanSblm.getKodHakmilikSementara());
                hakmilikmohon.setKodHakmilikTetap(hakmilikPermohonanSblm.getKodHakmilikTetap());
                hakmilikmohon.setKodKegunaanTanah(hakmilikPermohonanSblm.getKodKegunaanTanah());
                hakmilikmohon.setKodKawasanParlimen(hakmilikPermohonanSblm.getKodKawasanParlimen());
                hakmilikmohon.setKodMilik(hakmilikPermohonanSblm.getKodMilik());
                hakmilikmohon.setKodUnitLuas(hakmilikPermohonanSblm.getKodUnitLuas());
                hakmilikmohon.setLokasi(hakmilikPermohonanSblm.getLokasi());
                hakmilikmohon.setLuasDiluluskan(hakmilikPermohonanSblm.getLuasDiluluskan());
                hakmilikmohon.setLuasLulusUom(hakmilikPermohonanSblm.getLuasLulusUom());
                hakmilikmohon.setNoLot(hakmilikPermohonanSblm.getNoLot());
                hakmilikmohon.setPegangan(hakmilikPermohonanSblm.getPegangan());
                hakmilikmohon.setSekatanKepentingan(hakmilikPermohonanSblm.getSekatanKepentingan());
                hakmilikmohon.setSekatanKepentinganBaru(hakmilikPermohonanSblm.getSekatanKepentinganBaru());
                //hakmilikmohon.setSenaraiLaporanTanah(hakmilikPermohonanSblm.getSenaraiLaporanTanah());
                hakmilikmohon.setSyaratNyata(hakmilikPermohonanSblm.getSyaratNyata());
                hakmilikmohon.setSyaratNyataBaru(hakmilikPermohonanSblm.getSyaratNyataBaru());
                hakmilikmohon.setLuasTerlibat(hakmilikPermohonanSblm.getLuasTerlibat());
                hakmilikmohon.setUnitJarak(hakmilikPermohonanSblm.getUnitJarak());
                hakmilikmohon.setPermohonan(permohonan);
                disLaporanTanahService.getPlpservice().saveOrUpdate(hakmilikmohon);
            }
        }
        addSimpleMessage("Maklumat Telah Disimpan");
        getContext().getRequest().setAttribute("status", Boolean.TRUE);
        getContext().getRequest().setAttribute("simpan", Boolean.FALSE);
        status = false;
        rehydrate();
        return new JSP("pelupusan/rayuan/maklumat_rayuan_mlk.jsp").addParameter("tab", "true");
    }

    public HakmilikPermohonan getHakmilikPermohonan() {
        return hakmilikPermohonan;
    }

    public void setHakmilikPermohonan(HakmilikPermohonan hakmilikPermohonan) {
        this.hakmilikPermohonan = hakmilikPermohonan;
    }

    public Permohonan getPermohonan() {
        return permohonan;
    }

    public void setPermohonan(Permohonan permohonan) {
        this.permohonan = permohonan;
    }

    public List<HakmilikPermohonan> getHakmilikPermohonanList() {
        return hakmilikPermohonanList;
    }

    public void setHakmilikPermohonanList(List<HakmilikPermohonan> hakmilikPermohonanList) {
        this.hakmilikPermohonanList = hakmilikPermohonanList;
    }

    public List<Pemohon> getPemohonList() {
        return pemohonList;
    }

    public void setPemohonList(List<Pemohon> pemohonList) {
        this.pemohonList = pemohonList;
    }

    public Pemohon getPemohon() {
        return pemohon;
    }

    public void setPemohon(Pemohon pemohon) {
        this.pemohon = pemohon;
    }

    public Pengguna getPengguna() {
        return pengguna;
    }

    public void setPengguna(Pengguna pengguna) {
        this.pengguna = pengguna;
    }

    public Pihak getPihak() {
        return pihak;
    }

    public void setPihak(Pihak pihak) {
        this.pihak = pihak;
    }

    public String getIdPermohonanSebelum() {
        return idPermohonanSebelum;
    }

    public void setIdPermohonanSebelum(String idPermohonanSebelum) {
        this.idPermohonanSebelum = idPermohonanSebelum;
    }

    public boolean isSimpan() {
        return simpan;
    }

    public void setSimpan(boolean simpan) {
        this.simpan = simpan;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public HakmilikDAO getHakmilikDAO() {
        return hakmilikDAO;
    }

    public void setHakmilikDAO(HakmilikDAO hakmilikDAO) {
        this.hakmilikDAO = hakmilikDAO;
    }

    public static Logger getLogger() {
        return logger;
    }

    public static void setLogger(Logger logger) {
        MaklumatRayuanActionBean.logger = logger;
    }

    public PelupusanService getPelupusanService() {
        return pelupusanService;
    }

    public void setPelupusanService(PelupusanService pelupusanService) {
        this.pelupusanService = pelupusanService;
    }

    public PermohonanBahanBatuanDAO getPermohonanBahanBatuanDAO() {
        return permohonanBahanBatuanDAO;
    }

    public void setPermohonanBahanBatuanDAO(PermohonanBahanBatuanDAO permohonanBahanBatuanDAO) {
        this.permohonanBahanBatuanDAO = permohonanBahanBatuanDAO;
    }

    public PermohonanDAO getPermohonanDAO() {
        return permohonanDAO;
    }

    public void setPermohonanDAO(PermohonanDAO permohonanDAO) {
        this.permohonanDAO = permohonanDAO;
    }

    public String getIdPermohonan() {
        return idPermohonan;
    }

    public void setIdPermohonan(String idPermohonan) {
        this.idPermohonan = idPermohonan;
    }

    public Permohonan getPermohonanSebelum() {
        return permohonanSebelum;
    }

    public void setPermohonanSebelum(Permohonan permohonanSebelum) {
        this.permohonanSebelum = permohonanSebelum;
    }

    public String getId2PermohonanSebelum() {
        return id2PermohonanSebelum;
    }

    public void setId2PermohonanSebelum(String id2PermohonanSebelum) {
        this.id2PermohonanSebelum = id2PermohonanSebelum;
    }

    public Date getTarikhPermohonan() {
        return tarikhPermohonan;
    }

    public void setTarikhPermohonan(Date tarikhPermohonan) {
        this.tarikhPermohonan = tarikhPermohonan;
    }

    public String getTujuan() {
        return tujuan;
    }

    public void setTujuan(String tujuan) {
        this.tujuan = tujuan;
    }

    public Permohonan getIdPermohonanRAYL() {
        return idPermohonanRAYL;
    }

    public void setIdPermohonanRAYL(Permohonan idPermohonanRAYL) {
        this.idPermohonanRAYL = idPermohonanRAYL;
    }

    public Permohonan getIdPermohonanPBMT() {
        return idPermohonanPBMT;
    }

    public void setIdPermohonanPBMT(Permohonan idPermohonanPBMT) {
        this.idPermohonanPBMT = idPermohonanPBMT;
    }

    public String getKodNegeri() {
        return kodNegeri;
    }

    public void setKodNegeri(String kodNegeri) {
        this.kodNegeri = kodNegeri;
    }
}
