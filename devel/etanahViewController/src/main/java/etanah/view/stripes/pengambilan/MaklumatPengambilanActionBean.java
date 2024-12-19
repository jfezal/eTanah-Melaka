/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.stripes.pengambilan;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.dao.KodBandarPekanMukimDAO;
import etanah.dao.KodStatusTanahLepasPengambilanDAO;
import etanah.dao.PermohonanDAO;
import etanah.dao.PermohonanRujukanLuarDAO;
import etanah.dao.StatusTanahLepasPengambilanDAO;
import etanah.model.HakmilikPermohonan;
import etanah.model.Permohonan;
import etanah.model.ambil.PermohonanPengambilan;
import etanah.model.PermohonanRujukanLuar;
import etanah.model.KodCawangan;
import etanah.model.KodBandarPekanMukim;
import etanah.model.KodRujukan;
import etanah.model.KodUOM;
import etanah.model.StatusTanahLepasPengambilan;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import etanah.service.common.HakmilikService;
import etanah.service.common.PermohonanRujukanLuarService;
import etanah.service.PengambilanService;
import java.util.List;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.HttpCache;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;
import etanah.model.InfoAudit;
import etanah.model.Pengguna;
import etanah.service.ambil.PengambilanAPTService;
import org.apache.commons.lang.StringUtils;
import etanah.view.etanahActionBeanContext;
import java.math.BigDecimal;
import javax.swing.JOptionPane;
import net.sourceforge.stripes.action.ForwardResolution;

/**
 *
 * @author nordiyana
 */
@UrlBinding("/pengambilan/maklumat_pengambilan")
public class MaklumatPengambilanActionBean extends AbleActionBean {

    @Inject
    PermohonanDAO permohonanDAO;
    PermohonanRujukanLuarDAO permohonanRujukanLuarDAO;
    @Inject
    StatusTanahLepasPengambilanDAO statusTanahLepasPengambilanDAO;
    @Inject
    KodStatusTanahLepasPengambilanDAO kodStatusTanahLepasPengambilanDAO;
    @Inject
    PengambilanAPTService pengambilanAPTService;

    private Permohonan permohonan;
    private PermohonanPengambilan permohonanPengambilan;
    private StatusTanahLepasPengambilan statusLepasPengambilan;
    @Inject
    PermohonanRujukanLuarService service;
    private String tarikhRujukan;
    private String cawangan;
    private KodCawangan kodCawangan;
    private String sebab;
    private PermohonanRujukanLuar permohonanRujukanLuar;
    private List<PermohonanRujukanLuar> mrlList;
    private String noRujukan;
    private List<HakmilikPermohonan> hakmilikPermohonanList;
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    private KodRujukan kodRujukan;
    private KodBandarPekanMukim bpm;
    private KodUOM kodUom;
    private String LuasTerlibat;
    private List<StatusTanahLepasPengambilan> selepasPengambilan;
    private String hakmilikSambungan;
    private String tanahRizab;
    private String tanahKerajaan;
    private String tujuanPengambilan;
    private String idPermohonan;
    private String flag;
    private String flag2;

    public List<StatusTanahLepasPengambilan> getSelepasPengambilan() {
        return selepasPengambilan;
    }

    public void setSelepasPengambilan(List<StatusTanahLepasPengambilan> selepasPengambilan) {
        this.selepasPengambilan = selepasPengambilan;
    }

    public String getLuasTerlibat() {
        return LuasTerlibat;
    }

    public void setLuasTerlibat(String LuasTerlibat) {
        this.LuasTerlibat = LuasTerlibat;
    }

    @DefaultHandler
    public Resolution showForm() {
        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        return new JSP("pengambilan/maklumat_pengambilan.jsp").addParameter("tab", "true");
    }

    public Resolution showForm2() {
        getContext().getRequest().setAttribute("!edit", Boolean.TRUE);
        return new JSP("pengambilan/maklumat_pengambilan.jsp").addParameter("tab", "true");
    }

    public Resolution kedesakan() {
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
//        String flag = (String) getContext().getRequest().getSession().getAttribute("kedesakanvalue1");
//        String flag2 = (String) getContext().getRequest().getSession().getAttribute("kedesakanvalue2");

        if (flag != null) {
            permohonanPengambilan.setKedesakan(flag);
            service.simpanAmbil(permohonanPengambilan);
        } else if (flag2 != null) {
            permohonanPengambilan.setKedesakan(flag2);
            service.simpanAmbil(permohonanPengambilan);
        } else {
            // addSimpleError("Silap Pilih KEDESAKAN");
        }

        return new JSP("pengambilan/maklumat_pengambilan.jsp").addParameter("tab", "true");

    }

    public Resolution showForm3() {
        return new JSP("pengambilan/Laporan_PelukisPelan.jsp").addParameter("tab", "true");
    }

    public Resolution showForm4() {
        return new JSP("pengambilan/paparan_pelukis_pelan.jsp").addParameter("tab", "true");
    }

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {
        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        if (idPermohonan != null) {
            permohonanPengambilan = service.findByidP(idPermohonan);
            permohonan = permohonanDAO.findById(idPermohonan);
//            permohonanRujukanLuar = service.findByidPermohonan(idPermohonan);
//            JOptionPane.showMessageDialog(null, permohonanRujukanLuar);
            mrlList = service.findByidPermohonanList(idPermohonan);
            permohonanRujukanLuar = service.findByidPermohonanNoRujsurat(idPermohonan);
            System.out.println("Mohon rujukan luar " + mrlList.size());
            if (mrlList.size() != 0) {
                //mrlList.get(0).getNoRujukan();
                if (mrlList.get(0).getNoRujukan() != null) {
                    noRujukan = permohonanRujukanLuar.getNoRujukan();
                    System.out.println("no ruj " + permohonanRujukanLuar.getNoRujukan());
                } else {
                    noRujukan = "TIDAK DINYATAKAN";
                }

                if (mrlList.get(0).getTarikhRujukan() != null) {
                    tarikhRujukan = sdf.format(permohonanRujukanLuar.getTarikhRujukan()).substring(0, 10);
                } else {
                    tarikhRujukan = "TIDAK DINYATAKAN";
                }

//                if (mrlList.get(0).getTarikhRujukan() != null) { // tarikhRujukan = sdf.format(mrlList.get(0).getTarikhRujukan()).substring(0, 10);}
//                }
            } else {
                permohonanRujukanLuar = new PermohonanRujukanLuar();
                noRujukan = "";
                // System.out.println("no ruj " + permohonanRujukanLuar.getNoRujukan());
                // tarikhRujukan = sdf.format(permohonanRujukanLuar.getTarikhRujukan()).substring(0, 10);

            }

//            if (mrlList.size() != 0) {
//                //mrlList.get(0).getNoRujukan();
//                if (mrlList.get(0).getTarikhRujukan() != null) { // tarikhRujukan = sdf.format(mrlList.get(0).getTarikhRujukan()).substring(0, 10);}
//                }
//            } else {
//                permohonanRujukanLuar = new PermohonanRujukanLuar();
//            }
            StatusTanahLepasPengambilan statusTanahLepasPengambilan = service.findMohonLPSAmbilByIdMohonKodLPS(idPermohonan, "HS");
            if (statusTanahLepasPengambilan != null) {
                hakmilikSambungan = statusTanahLepasPengambilan.getKodStatusTanahLepasPengambilan().getKod();
            }
            StatusTanahLepasPengambilan statusTanahLepasPengambilan1 = service.findMohonLPSAmbilByIdMohonKodLPS(idPermohonan, "TR");
            if (statusTanahLepasPengambilan1 != null) {
                tanahRizab = statusTanahLepasPengambilan1.getKodStatusTanahLepasPengambilan().getKod();
            }
            StatusTanahLepasPengambilan statusTanahLepasPengambilan2 = service.findMohonLPSAmbilByIdMohonKodLPS(idPermohonan, "TK");
            if (statusTanahLepasPengambilan2 != null) {
                tanahKerajaan = statusTanahLepasPengambilan2.getKodStatusTanahLepasPengambilan().getKod();
            }

            permohonanPengambilan = pengambilanAPTService.findPermohonanPengambilanByIdMH(idPermohonan);
            if (permohonanPengambilan != null) {
                tujuanPengambilan = permohonanPengambilan.getTujuanPermohonan();
                if (permohonanPengambilan.getKedesakan() != null) {
                    flag = permohonanPengambilan.getKedesakan();
                }
            }

        }

    }

    public Resolution savePengambilanInfo() throws ParseException {
        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        String noRujukan = getContext().getRequest().getParameter("noRujukan");
        flag = (String) getContext().getRequest().getSession().getAttribute("kedesakanvalue1");
        flag2 = (String) getContext().getRequest().getSession().getAttribute("kedesakanvalue2");
        if (tujuanPengambilan == null) {
            tujuanPengambilan = getContext().getRequest().getParameter("tujuanPengambilan");
        }

        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        permohonan = permohonanDAO.findById(idPermohonan);
        InfoAudit info = peng.getInfoAudit();

        if (hakmilikSambungan != null) {
            StatusTanahLepasPengambilan statusTanahLepasPengambilan = service.findMohonLPSAmbilByIdMohonKodLPS(idPermohonan, hakmilikSambungan);
            if (statusTanahLepasPengambilan == null) {
                statusTanahLepasPengambilan = new StatusTanahLepasPengambilan();
                statusTanahLepasPengambilan.setIdPermohonan(permohonan);
                statusTanahLepasPengambilan.setCawangan(permohonan.getCawangan());
                statusTanahLepasPengambilan.setKodStatusTanahLepasPengambilan(kodStatusTanahLepasPengambilanDAO.findById(hakmilikSambungan));
                InfoAudit ia = new InfoAudit();
                ia.setDimasukOleh(peng);
                ia.setTarikhMasuk(new java.util.Date());
                statusTanahLepasPengambilan.setInfoAudit(ia);
                service.saveOrUpdateStatusTanahLepasPengambilan(statusTanahLepasPengambilan);
            }

        } else {
            StatusTanahLepasPengambilan statusTanahLepasPengambilan = service.findMohonLPSAmbilByIdMohonKodLPS(idPermohonan, "HS");
            if (statusTanahLepasPengambilan != null) {
                service.deleteStatusTanahLepasPengambilan(statusTanahLepasPengambilan);
            }
        }

        if (tanahRizab != null) {
            StatusTanahLepasPengambilan statusTanahLepasPengambilan = service.findMohonLPSAmbilByIdMohonKodLPS(idPermohonan, tanahRizab);
            if (statusTanahLepasPengambilan == null) {
                statusTanahLepasPengambilan = new StatusTanahLepasPengambilan();
                statusTanahLepasPengambilan.setIdPermohonan(permohonan);
                statusTanahLepasPengambilan.setCawangan(permohonan.getCawangan());
                statusTanahLepasPengambilan.setKodStatusTanahLepasPengambilan(kodStatusTanahLepasPengambilanDAO.findById(tanahRizab));
                InfoAudit ia = new InfoAudit();
                ia.setDimasukOleh(peng);
                ia.setTarikhMasuk(new java.util.Date());
                statusTanahLepasPengambilan.setInfoAudit(ia);
                service.saveOrUpdateStatusTanahLepasPengambilan(statusTanahLepasPengambilan);
            }

        } else {
            StatusTanahLepasPengambilan statusTanahLepasPengambilan = service.findMohonLPSAmbilByIdMohonKodLPS(idPermohonan, "TR");
            if (statusTanahLepasPengambilan != null) {
                service.deleteStatusTanahLepasPengambilan(statusTanahLepasPengambilan);
            }
        }

        if (tanahKerajaan != null) {
            StatusTanahLepasPengambilan statusTanahLepasPengambilan = service.findMohonLPSAmbilByIdMohonKodLPS(idPermohonan, tanahKerajaan);
            if (statusTanahLepasPengambilan == null) {
                statusTanahLepasPengambilan = new StatusTanahLepasPengambilan();
                statusTanahLepasPengambilan.setIdPermohonan(permohonan);
                statusTanahLepasPengambilan.setCawangan(permohonan.getCawangan());
                statusTanahLepasPengambilan.setKodStatusTanahLepasPengambilan(kodStatusTanahLepasPengambilanDAO.findById(tanahKerajaan));
                InfoAudit ia = new InfoAudit();
                ia.setDimasukOleh(peng);
                ia.setTarikhMasuk(new java.util.Date());
                statusTanahLepasPengambilan.setInfoAudit(ia);
                service.saveOrUpdateStatusTanahLepasPengambilan(statusTanahLepasPengambilan);
            }

        } else {
            StatusTanahLepasPengambilan statusTanahLepasPengambilan = service.findMohonLPSAmbilByIdMohonKodLPS(idPermohonan, "TK");
            if (statusTanahLepasPengambilan != null) {
                service.deleteStatusTanahLepasPengambilan(statusTanahLepasPengambilan);
            }
        }
        permohonanPengambilan = service.findByidP(permohonan.getIdPermohonan());
        if (permohonanPengambilan != null) {
            permohonanPengambilan.setTujuanPermohonan(tujuanPengambilan);
            service.savePermohonanPengambilan(permohonanPengambilan);
        }else{
            permohonanPengambilan = new PermohonanPengambilan();
        }

//        try {
        permohonanRujukanLuar = service.findByidPermohonan(idPermohonan);
        if (permohonanRujukanLuar == null) {
            permohonanRujukanLuar = new PermohonanRujukanLuar();
            if (StringUtils.isNotBlank(tarikhRujukan)) {
                info.setDimasukOleh(peng);
                info.setTarikhMasuk(new java.util.Date());
                permohonanRujukanLuar.setTarikhRujukan(sdf.parse(tarikhRujukan));
                tarikhRujukan = sdf.format(permohonanRujukanLuar.getTarikhRujukan()).substring(0, 10);
            }

            kodRujukan = new KodRujukan();
            kodRujukan.setKod("NF");
            kodUom = new KodUOM();
            kodUom.setKod("M");
            bpm = new KodBandarPekanMukim();
            bpm.setKod(45);
            permohonanPengambilan.setBandarPekanMukim(bpm);
            permohonanPengambilan.setKodUnitLuas(kodUom);
            permohonanPengambilan.setPermohonan(permohonan);
            permohonanPengambilan.setCawangan(peng.getKodCawangan());
            permohonanPengambilan.setLuasTerlibat(new BigDecimal("123"));
            permohonanRujukanLuar.setPermohonan(permohonan);
            permohonanRujukanLuar.setKodRujukan(kodRujukan);
            permohonanRujukanLuar.setNoRujukan(noRujukan);
            permohonanPengambilan.setInfoAudit(info);
            permohonanRujukanLuar.setCawangan(peng.getKodCawangan());
            permohonanRujukanLuar.setInfoAudit(info);
            service.simpanrujluar(permohonan);
            service.simpanAmbil(permohonanPengambilan);
            service.save(permohonanRujukanLuar);
            addSimpleMessage("Kemasukan Data Berjaya.");
        } else {
            if (StringUtils.isNotBlank(tarikhRujukan)) {
                permohonanRujukanLuar.setTarikhRujukan(sdf.parse(tarikhRujukan));
            }
//            tarikhRujukan = sdf.format(permohonanRujukanLuar.getTarikhRujukan());

            info.setDiKemaskiniOleh(peng);
            info.setTarikhKemaskini(new java.util.Date());
            kodRujukan = new KodRujukan();
            kodRujukan.setKod("NF");
            kodUom = new KodUOM();
            kodUom.setKod("M");
            bpm = new KodBandarPekanMukim();
            bpm.setKod(45);
            permohonanPengambilan.setBandarPekanMukim(bpm);
            permohonanPengambilan.setPermohonan(permohonan);
            permohonanPengambilan.setCawangan(peng.getKodCawangan());
            permohonanPengambilan.setLuasTerlibat(new BigDecimal("123"));
            permohonanPengambilan.setKodUnitLuas(kodUom);
            permohonanRujukanLuar.setPermohonan(permohonan);
            permohonanRujukanLuar.setKodRujukan(kodRujukan);
            permohonanRujukanLuar.setNoRujukan(noRujukan);
            permohonanPengambilan.setInfoAudit(info);
            permohonanRujukanLuar.setCawangan(peng.getKodCawangan());
            permohonanRujukanLuar.setInfoAudit(info);
//            permohonan.setSebab(sebab);
            service.simpanrujluar(permohonan);
            service.simpanAmbil(permohonanPengambilan);
            service.update(permohonanRujukanLuar);
            addSimpleMessage("Kemaskini Data Berjaya.");

        }

        kedesakan();

//        } catch (Exception ex) {
//            ex.printStackTrace();
//            addSimpleError("Kemasukan Data Gagal. Sila hubungi Pentadbir Sistem");
//            return showForm();
//        }
        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        return new JSP("pengambilan/maklumat_pengambilan.jsp").addParameter("tab", "true");
    }

    public String gettarikhRujukan() {
        return tarikhRujukan;
    }

    public void settarikhRujukan(String tarikhRujukan) {
        this.tarikhRujukan = tarikhRujukan;
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

    public PermohonanRujukanLuar getPermohonanRujukanLuar() {
        return permohonanRujukanLuar;
    }

    public void setPermohonanRujukanLuar(PermohonanRujukanLuar permohonanRujukanLuar) {
        this.permohonanRujukanLuar = permohonanRujukanLuar;
    }

    public String getSebab() {
        return sebab;
    }

    public void setSebab(String sebab) {
        this.sebab = sebab;
    }

//     public String getberimilik() {
//        return berimilik;
//    }
//
//    public void setberimilik(String berimilik) {
//        this.berimilik = berimilik;
//    }
    public PermohonanPengambilan getpermohonanPengambilan() {
        return permohonanPengambilan;
    }

    public void setPermohonanPengambilan(PermohonanPengambilan permohonanPengambilan) {
        this.permohonanPengambilan = permohonanPengambilan;
    }

    public String getCawangan() {
        return cawangan;
    }

    public void setCawangan(String cawangan) {
        this.cawangan = cawangan;
    }

    public KodBandarPekanMukim getBandarPekanMukim() {
        return bpm;
    }

    public void setBandarPekanMukim(KodBandarPekanMukim bpm) {
        this.bpm = bpm;
    }

    public KodRujukan getKodRujukan() {
        return kodRujukan;
    }

    public void setKodRujukan(KodRujukan kodRujukan) {
        this.kodRujukan = kodRujukan;
    }

    public StatusTanahLepasPengambilan getStatusLepasPengambilan() {
        return statusLepasPengambilan;
    }

    public void setStatusLepasPengambilan(StatusTanahLepasPengambilan statusLepasPengambilan) {
        this.statusLepasPengambilan = statusLepasPengambilan;
    }

    public String getHakmilikSambungan() {
        return hakmilikSambungan;
    }

    public void setHakmilikSambungan(String hakmilikSambungan) {
        this.hakmilikSambungan = hakmilikSambungan;
    }

    public String getTanahKerajaan() {
        return tanahKerajaan;
    }

    public void setTanahKerajaan(String tanahKerajaan) {
        this.tanahKerajaan = tanahKerajaan;
    }

    public String getTanahRizab() {
        return tanahRizab;
    }

    public void setTanahRizab(String tanahRizab) {
        this.tanahRizab = tanahRizab;
    }

    public KodUOM getKodUom() {
        return kodUom;
    }

    public void setKodUom(KodUOM kodUom) {
        this.kodUom = kodUom;
    }

    public String getNoRujukan() {
        return noRujukan;
    }

    public void setNoRujukan(String noRujukan) {
        this.noRujukan = noRujukan;
    }

    public List<PermohonanRujukanLuar> getMrlList() {
        return mrlList;
    }

    public void setMrlList(List<PermohonanRujukanLuar> mrlList) {
        this.mrlList = mrlList;
    }

    public String getTujuanPengambilan() {
        return tujuanPengambilan;
    }

    public void setTujuanPengambilan(String tujuanPengambilan) {
        this.tujuanPengambilan = tujuanPengambilan;
    }

    public PermohonanDAO getPermohonanDAO() {
        return permohonanDAO;
    }

    public void setPermohonanDAO(PermohonanDAO permohonanDAO) {
        this.permohonanDAO = permohonanDAO;
    }

    public PermohonanRujukanLuarDAO getPermohonanRujukanLuarDAO() {
        return permohonanRujukanLuarDAO;
    }

    public void setPermohonanRujukanLuarDAO(PermohonanRujukanLuarDAO permohonanRujukanLuarDAO) {
        this.permohonanRujukanLuarDAO = permohonanRujukanLuarDAO;
    }

    public StatusTanahLepasPengambilanDAO getStatusTanahLepasPengambilanDAO() {
        return statusTanahLepasPengambilanDAO;
    }

    public void setStatusTanahLepasPengambilanDAO(StatusTanahLepasPengambilanDAO statusTanahLepasPengambilanDAO) {
        this.statusTanahLepasPengambilanDAO = statusTanahLepasPengambilanDAO;
    }

    public KodStatusTanahLepasPengambilanDAO getKodStatusTanahLepasPengambilanDAO() {
        return kodStatusTanahLepasPengambilanDAO;
    }

    public void setKodStatusTanahLepasPengambilanDAO(KodStatusTanahLepasPengambilanDAO kodStatusTanahLepasPengambilanDAO) {
        this.kodStatusTanahLepasPengambilanDAO = kodStatusTanahLepasPengambilanDAO;
    }

    public PengambilanAPTService getPengambilanAPTService() {
        return pengambilanAPTService;
    }

    public void setPengambilanAPTService(PengambilanAPTService pengambilanAPTService) {
        this.pengambilanAPTService = pengambilanAPTService;
    }

    public PermohonanRujukanLuarService getService() {
        return service;
    }

    public void setService(PermohonanRujukanLuarService service) {
        this.service = service;
    }

    public String getTarikhRujukan() {
        return tarikhRujukan;
    }

    public void setTarikhRujukan(String tarikhRujukan) {
        this.tarikhRujukan = tarikhRujukan;
    }

    public KodCawangan getKodCawangan() {
        return kodCawangan;
    }

    public void setKodCawangan(KodCawangan kodCawangan) {
        this.kodCawangan = kodCawangan;
    }

    public SimpleDateFormat getSdf() {
        return sdf;
    }

    public void setSdf(SimpleDateFormat sdf) {
        this.sdf = sdf;
    }

    public KodBandarPekanMukim getBpm() {
        return bpm;
    }

    public void setBpm(KodBandarPekanMukim bpm) {
        this.bpm = bpm;
    }

    public String getIdPermohonan() {
        return idPermohonan;
    }

    public void setIdPermohonan(String idPermohonan) {
        this.idPermohonan = idPermohonan;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public String getFlag2() {
        return flag2;
    }

    public void setFlag2(String flag2) {
        this.flag2 = flag2;
    }

}
