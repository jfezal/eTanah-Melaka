/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.stripes.pelupusan;

import java.text.ParseException;
import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.dao.HakmilikDAO;
import etanah.dao.HakmilikPermohonanDAO;
import etanah.dao.KodBandarPekanMukimDAO;
import etanah.dao.KodJenisPengenalanDAO;
import etanah.dao.KodJenisPihakBerkepentinganDAO;
import etanah.dao.KodKategoriTanahDAO;
import etanah.dao.KodLotDAO;
import etanah.dao.KodSeksyenDAO;
import etanah.dao.KodUOMDAO;
import etanah.dao.KodWarganegaraDAO;
import net.sourceforge.stripes.action.StreamingResolution;
import etanah.dao.PermohonanDAO;
import etanah.model.Hakmilik;
import etanah.model.HakmilikPermohonan;
import etanah.model.InfoAudit;
import etanah.model.KodBandarPekanMukim;
import etanah.model.KodItemPermit;
import etanah.model.KodJenisPengenalan;
import etanah.model.KodKategoriTanah;
import etanah.model.KodKegunaanTanah;
import etanah.model.KodLot;
import etanah.model.KodSeksyen;
import etanah.model.KodUOM;
import etanah.model.KodWarganegara;
import etanah.model.Pemohon;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.model.PermohonanLaporanPelan;
import etanah.model.PermohonanPermitItem;
import etanah.model.Pihak;
import etanah.service.LaporanPelukisPelanService;
import etanah.service.PelupusanService;
import etanah.service.RegService;
import etanah.view.ListUtil;
import etanah.view.etanahActionBeanContext;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.HttpCache;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;
import org.apache.log4j.Logger;
import etanah.service.common.HakmilikService;
import java.math.BigDecimal;
import java.util.ArrayList;
import net.sourceforge.stripes.action.RedirectResolution;
import org.hibernate.Session;
import org.apache.commons.lang.StringUtils;
import org.hibernate.Query;

/**
 *
 * @author Shazwan 13 September 2011
 */
@UrlBinding("/pelupusan/maklumat_penggunaan")
public class MaklumatPenggunaanActionBean extends AbleActionBean {

    private static Logger logger = Logger.getLogger(MaklumatTanahActionBean.class);
    @Inject
    LaporanPelukisPelanService laporanPelukisPelanService;
    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    HakmilikPermohonanDAO hakmilikPermohonanDAO;
    @Inject
    PelupusanService pelupusanService;
    @Inject
    ListUtil listUtil;
    @Inject
    HakmilikDAO hakmilikDAO;
    @Inject
    HakmilikService hakmilikService;
    @Inject
    RegService regService;
    @Inject
    KodKategoriTanahDAO kodKategoriTanahDAO;
    @Inject
    KodBandarPekanMukimDAO kodBandarPekanMukimDAO;
    @Inject
    KodUOMDAO kodUOMDAO;
    @Inject
    KodLotDAO kodLotDAO;
    @Inject
    KodJenisPengenalanDAO kodJenisPengenalanDAO;
    @Inject
    KodJenisPihakBerkepentinganDAO kodJenisPihakBerkepentinganDAO;
    @Inject
    KodWarganegaraDAO kodWarganegaraDAO;
    @Inject
    KodSeksyenDAO kodSeksyenDAO;
    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;
    @Inject
    etanah.Configuration conf;
    @Inject
    PelupusanService pservice;
    
    private Pengguna peng;
    private Pihak pihak;
    private Permohonan permohonan;
    private HakmilikPermohonan mohonHakmilik = new HakmilikPermohonan();
    private String idPermohonan;
    private String luasterlibatUOM;
    private String jenisPengenalan;
    private String wargaNegara;
    private String idPihak;
    private String luasTerlibatName;
    private String warganegaraName;
    private String jenisPengenalanName;
    private boolean statusPguna = false;
    private boolean viewOnly = false;
    String _MSG_SUCCES_SAVE = "Maklumat telah berjaya disimpan.";
    String _MSG_SUCCES_UPDATE = "Maklumat telah berjaya dikemaskini.";
    
    @DefaultHandler
    public Resolution showForm() {
        viewOnly = Boolean.FALSE;
        return new JSP("pelupusan/maklumat_penggunaan.jsp").addParameter("tab", "true");
    }
    public Resolution viewForm() {
        viewOnly = Boolean.TRUE;
        return new JSP("pelupusan/maklumat_penggunaan.jsp").addParameter("tab", "true");
    }

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {
        peng = (Pengguna) context.getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        String taskId = (String) getContext().getRequest().getSession().getAttribute("taskId");
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = permohonanDAO.findById(idPermohonan);
        mohonHakmilik = pservice.findMohonHakmilik(idPermohonan);
        Pemohon pemohon = new Pemohon();
        pemohon = pservice.findPermohonanByIdMohonNKodPihak(idPermohonan,"PBK");
        if(mohonHakmilik!=null){
            luasterlibatUOM = mohonHakmilik.getKodUnitLuas()!=null?mohonHakmilik.getKodUnitLuas().getKod():new String();
            luasTerlibatName = mohonHakmilik.getKodUnitLuas()!=null?mohonHakmilik.getKodUnitLuas().getNama():new String();
        }
        if(pemohon!=null){
            pihak = pemohon.getPihak();
            jenisPengenalan = pihak.getJenisPengenalan()!=null?pihak.getJenisPengenalan().getKod():new String();
            wargaNegara = pihak.getWargaNegara()!=null?pihak.getWargaNegara().getKod():new String();
            warganegaraName = pihak.getWargaNegara()!=null?pihak.getWargaNegara().getNama():new String();
            statusPguna = true;
        }else{
            pihak = new Pihak();
            statusPguna = false;
        }
    }
    /*
     * STATUS
     * 1-> PENGGUNA ONLY
     * 2-> MOHON HAKMILIK && PERMOHONAN ONLY
     * 3-> ALL
     */
    public boolean validate(int status){
        boolean validate = true;
        switch(status){
            case 1 :    if(jenisPengenalan.equals("0")){
                        addSimpleError("Sila nyatakan Jenis Pengenalan");
                        validate = false;
                        }
                        if(pihak.getNoPengenalan()==null){
                            addSimpleError("Sila nyatakan No Pengenalan");
                            validate = false;
                        }
                        break;
            case 2 : 
                        if(mohonHakmilik==null){
                            if(mohonHakmilik.getLuasTerlibat()==null){
                                addSimpleError("Sila nyatakan Luas Terlibat");
                                validate = false;
                            }
                            if(luasterlibatUOM.equals("0")){
                                addSimpleError("Sila nyatakan Jenis Keluasan Penggunaan");
                                validate = false;
                            }
                        }
                        if(permohonan!=null){
                            if(StringUtils.isBlank(permohonan.getSebab())){
                                addSimpleError("Sila nyatakan Tujuan Penggunaan");
                                validate = false;
                            }
                        }
                        break;
            case 3 : 
                        if(jenisPengenalan.equals("0")){
                        addSimpleError("Sila nyatakan Jenis Pengenalan");
                        validate = false;
                        }
                        if(pihak.getNoPengenalan()==null){
                            addSimpleError("Sila nyatakan No Pengenalan");
                            validate = false;
                        }
                        if(mohonHakmilik!=null){
                            if(mohonHakmilik.getLuasTerlibat()==null){
                                addSimpleError("Sila nyatakan Luas Terlibat");
                                validate = false;
                            }
                            if(luasterlibatUOM.equals("0")){
                                addSimpleError("Sila nyatakan Jenis Keluasan Penggunaan");
                                validate = false;
                            }
                        }else{
                             addSimpleError("Sila nyatakan Jenis Keluasan Penggunaan");
                             validate = false;
                        }
                        if(permohonan!=null){
                            if(StringUtils.isBlank(permohonan.getSebab())){
                                addSimpleError("Sila nyatakan Tujuan Penggunaan");
                                validate = false;
                            }
                        }else{
                            addSimpleError("Sila nyatakan Tujuan Penggunaan");
                            validate = false;
                        }
                        if(wargaNegara.equals("0")){
                             addSimpleError("Sila nyatakan Kewarganegaraan");
                             validate = false;
                        }
                        statusPguna = true;
                        break;
        }
        
        return validate;
    }
    public Resolution cariPengguna() throws ParseException {
        
        Pengguna pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        if (idPermohonan != null) {
            permohonan = permohonanDAO.findById(idPermohonan);
            String noPengenalan = pihak.getNoPengenalan();
            if(validate(1)){
                if(permohonan!=null){
                    Pihak checkPihak = new Pihak();                
                    checkPihak = pelupusanService.findPihak(jenisPengenalan, noPengenalan);
                    if(checkPihak!=null){
                        pihak = new Pihak();
                        pihak = checkPihak;
                        idPihak = String.valueOf(pihak.getIdPihak());
                        wargaNegara = pihak.getWargaNegara()!=null?pihak.getWargaNegara().getKod():new String();
                        jenisPengenalan = pihak.getJenisPengenalan().getKod();
                        addSimpleMessage("Pengguna dijumpai");
                        statusPguna = true;
                    }else{
                        pihak = new Pihak();
                        idPihak = new String();
                        pihak.setJenisPengenalan(kodJenisPengenalanDAO.findById(jenisPengenalan));
                        pihak.setNoPengenalan(noPengenalan);
                        jenisPengenalan = pihak.getJenisPengenalan().getKod();
                        addSimpleMessage("Pengguna belum berdaftar");
                        statusPguna = true;
                    }
                }
            }            
        }
        
        return new JSP("pelupusan/maklumat_penggunaan.jsp").addParameter("tab", "true");

    }

    public Resolution simpan() throws ParseException {
        
        Pengguna pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        if (idPermohonan != null) {
            permohonan = permohonanDAO.findById(idPermohonan);
            String noPengenalan = pihak.getNoPengenalan();
            if(validate(3)){
                if(pihak!=null){
                    InfoAudit ia = new InfoAudit();
                    /*
                     * INSERT PIHAK
                     */
                    if(StringUtils.isBlank(idPihak)) {
                        Pihak pihakTemp = new Pihak();
                        pihakTemp = pihak;
                        ia.setTarikhMasuk(new java.util.Date());
                        ia.setDimasukOleh(pengguna);
                        pihakTemp.setInfoAudit(ia);
                        pihakTemp.setJenisPengenalan(!StringUtils.isBlank(jenisPengenalan)?kodJenisPengenalanDAO.findById(jenisPengenalan):new KodJenisPengenalan());
                        pihakTemp.setWargaNegara(!StringUtils.isBlank(wargaNegara)?kodWarganegaraDAO.findById(wargaNegara):new KodWarganegara());    
                        logger.info(pihakTemp.getNoPengenalan());
                        pelupusanService.saveOnly(pihakTemp);

                    }else{
                        ia = pihak.getInfoAudit();
                        ia.setDiKemaskiniOleh(pengguna);
                        ia.setTarikhKemaskini(new java.util.Date());
                        pihak.setIdPihak(Long.parseLong(idPihak));
                        pihak.setInfoAudit(ia);
                        pihak.setJenisPengenalan(!StringUtils.isBlank(jenisPengenalan)?kodJenisPengenalanDAO.findById(jenisPengenalan):new KodJenisPengenalan());
                        pihak.setWargaNegara(!StringUtils.isBlank(wargaNegara)?kodWarganegaraDAO.findById(wargaNegara):new KodWarganegara());
                        pihak = pelupusanService.saveOrUpdate(pihak);
                    }
                    /*
                     * PEMOHON
                     */
                    List<Pihak> listPihak = pelupusanService.findListPihakByNoPengenalanNjenisKP(noPengenalan,jenisPengenalan);
                    if(listPihak.size()>0){
                        for(Pihak p: listPihak){
                            Pemohon pemohon = new Pemohon();
                            pemohon = pelupusanService.findPermohonanByIdMohonNKodPihak(idPermohonan, "PBK");
                            ia = new InfoAudit();
                            if(pemohon!=null){
                                ia = pemohon.getInfoAudit();
                                ia.setDiKemaskiniOleh(pengguna);
                                ia.setTarikhKemaskini(new java.util.Date());
                                pemohon.setInfoAudit(ia);
                                
                            }else{
                                ia.setDimasukOleh(pengguna);
                                ia.setTarikhMasuk(new java.util.Date());
                                pemohon = new Pemohon();
                                pemohon.setInfoAudit(ia);
                                pemohon.setPermohonan(permohonan);
                                pemohon.setCawangan(permohonan.getCawangan());
                            }
                            pemohon.setPihak(p);
                            pemohon.setJenis(kodJenisPihakBerkepentinganDAO.findById("PBK"));
                            
                            if(pemohon.getJenis()!=null){
                                pelupusanService.saveOrUpdate(pemohon);                                
                                addSimpleMessage(_MSG_SUCCES_SAVE);
                            }else{
                                addSimpleError("Kod Pihak Tidak Wujud");
                                return null;
                            }
                        }
                    }else{
                        addSimpleError("Maklumat Tidak Berjaya Disimpan, Pengguna tidak Dijumpai");
                        return null;
                    }
                    /*
                     * MOHON HAKMILIK
                     */
                    if(mohonHakmilik!=null){
                        mohonHakmilik.setKodUnitLuas(kodUOMDAO.findById(luasterlibatUOM));
                        pelupusanService.saveOrUpdate(mohonHakmilik);
                    }
                }
            }
            
        }
        return new JSP("pelupusan/maklumat_penggunaan.jsp").addParameter("tab", "true");

    }

    public String getIdPermohonan() {
        return idPermohonan;
    }

    public void setIdPermohonan(String idPermohonan) {
        this.idPermohonan = idPermohonan;
    }

    public HakmilikPermohonan getMohonHakmilik() {
        return mohonHakmilik;
    }

    public void setMohonHakmilik(HakmilikPermohonan mohonHakmilik) {
        this.mohonHakmilik = mohonHakmilik;
    }

    public Pengguna getPeng() {
        return peng;
    }

    public void setPeng(Pengguna peng) {
        this.peng = peng;
    }

    public Permohonan getPermohonan() {
        return permohonan;
    }

    public void setPermohonan(Permohonan permohonan) {
        this.permohonan = permohonan;
    }

    public String getLuasterlibatUOM() {
        return luasterlibatUOM;
    }

    public void setLuasterlibatUOM(String luasterlibatUOM) {
        this.luasterlibatUOM = luasterlibatUOM;
    }

    public Pihak getPihak() {
        return pihak;
    }

    public void setPihak(Pihak pihak) {
        this.pihak = pihak;
    }

    public String getJenisPengenalan() {
        return jenisPengenalan;
    }

    public void setJenisPengenalan(String jenisPengenalan) {
        this.jenisPengenalan = jenisPengenalan;
    }

    public String getWargaNegara() {
        return wargaNegara;
    }

    public void setWargaNegara(String wargaNegara) {
        this.wargaNegara = wargaNegara;
    }

    public boolean isStatusPguna() {
        return statusPguna;
    }

    public void setStatusPguna(boolean statusPguna) {
        this.statusPguna = statusPguna;
    }

    public String getMSG_SUCCES_SAVE() {
        return _MSG_SUCCES_SAVE;
    }

    public void setMSG_SUCCES_SAVE(String _MSG_SUCCES_SAVE) {
        this._MSG_SUCCES_SAVE = _MSG_SUCCES_SAVE;
    }

    public String getMSG_SUCCES_UPDATE() {
        return _MSG_SUCCES_UPDATE;
    }

    public void setMSG_SUCCES_UPDATE(String _MSG_SUCCES_UPDATE) {
        this._MSG_SUCCES_UPDATE = _MSG_SUCCES_UPDATE;
    }

    public String getIdPihak() {
        return idPihak;
    }

    public void setIdPihak(String idPihak) {
        this.idPihak = idPihak;
    }

    public String getLuasTerlibatName() {
        return luasTerlibatName;
    }

    public void setLuasTerlibatName(String luasTerlibatName) {
        this.luasTerlibatName = luasTerlibatName;
    }

    public boolean isViewOnly() {
        return viewOnly;
    }

    public void setViewOnly(boolean viewOnly) {
        this.viewOnly = viewOnly;
    }

    public String getWarganegaraName() {
        return warganegaraName;
    }

    public void setWarganegaraName(String warganegaraName) {
        this.warganegaraName = warganegaraName;
    }

    public String getJenisPengenalanName() {
        return jenisPengenalanName;
    }

    public void setJenisPengenalanName(String jenisPengenalanName) {
        this.jenisPengenalanName = jenisPengenalanName;
    }
    
    
}
