/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.stripes.pelupusan;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.dao.FasaPermohonanDAO;
import etanah.dao.FolderDokumenDAO;
import etanah.dao.PermohonanDAO;
import etanah.model.InfoAudit;
import etanah.model.KodAgensi;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.service.ConsentPtdService;
import etanah.service.RegService;
import etanah.view.etanahActionBeanContext;
import java.util.ArrayList;
import java.util.List;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;
import etanah.dao.KodAgensiDAO;
import etanah.dao.KodCawanganDAO;
import etanah.dao.KodDokumenDAO;
import etanah.dao.KodKategoriAgensiDAO;
import etanah.dao.KodKlasifikasiDAO;
import etanah.dao.KodNegeriDAO;
import etanah.dao.KodRujukanDAO;
import etanah.dao.PermohonanLaporanPelanDAO;
import etanah.dao.PermohonanRujukanLuarDAO;
import etanah.dao.PermohonanRujukanLuarSalinanDAO;
import etanah.dao.PermohonanTandatanganDokumenDAO;
import etanah.model.Dokumen;
import etanah.service.BPelService;
import etanah.model.FasaPermohonan;
import etanah.model.FolderDokumen;
import etanah.model.HakmilikPermohonan;
import etanah.model.KandunganFolder;
import etanah.model.KodCawangan;
import etanah.model.KodDokumen;
import etanah.model.KodKategoriAgensi;
import etanah.model.KodKementerian;
import etanah.model.KodNegeri;
import etanah.model.KodRujukan;
import etanah.model.Permit;
import etanah.model.PermitSahLaku;
import etanah.model.PermohonanRujukanLuar;
import etanah.service.NotaService;
import etanah.service.PelupusanService;
import etanah.service.common.EnforcementService;
import java.text.SimpleDateFormat;
import java.util.StringTokenizer;
import java.text.ParseException;
import java.util.Date;
import etanah.model.PermohonanLaporanPelan;
import etanah.model.PermohonanRujukanLuarDokumen;
import etanah.model.PermohonanRujukanLuarSalinan;
import etanah.model.PermohonanTandatanganDokumen;
import etanah.model.Pihak;
import etanah.model.SuratRujukanLuar;
import etanah.report.ReportUtil;
import etanah.service.SemakDokumenService;
import etanah.service.common.DokumenService;
import etanah.service.common.HakmilikPermohonanService;
import etanah.service.common.KandunganFolderService;
import etanah.view.ListUtil;
import java.util.Calendar;
import net.sourceforge.stripes.action.RedirectResolution;
import org.apache.log4j.Logger;
import org.apache.commons.lang.StringUtils;
import oracle.bpel.services.workflow.task.model.Task;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author shazwan 
 * Edit by Aizuddin 6 Jun 2012
 */
@UrlBinding("/pelupusan/lesen_terdahulu")
public class LesenTerdahuluActionBean extends AbleActionBean {

    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    KodDokumenDAO kodDokumenDAO;
    @Inject
    etanah.Configuration conf;
    @Inject
    KodCawanganDAO kodCawanganDAO ;
    @Inject
    PelupusanService pelupusanService;
    private List<Permit> listLesen;
    private String idPermohonan;
    private String noLesen;
    private String idMohonDulu;
    private boolean viewOnly = false;
    private Pengguna pguna = new Pengguna();
    private Permohonan permohonan = new Permohonan();
    private PermitSahLaku permitSahLakuAsal  ;
    private HakmilikPermohonan hakmilikPermohonan ;
    private Pihak pihak ;
    private List<PermitSahLaku> permitSahLakuList ;
    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;
    private static final Logger LOG = Logger.getLogger(LesenTerdahuluActionBean.class);

    @DefaultHandler
    public Resolution showForm() {
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        pguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        permohonan = permohonanDAO.findById(idPermohonan);
        if (permohonan != null) {
            if(permohonan.getIdPermohonan()!=null){
                listLesen = new ArrayList<Permit>();
                if(permohonan.getPermohonanSebelum()!=null){
                Permit permit = pelupusanService.findPermitByIdPermohonan(permohonan.getPermohonanSebelum().getIdPermohonan());
                     //Permohonan permohonanRef = pelupusanService.findIdPermohonan(idPermohonan);
                    if(permit!=null){
                        //if(permohonanRef != null){
                        //permit.setPermohonan(permohonanRef);
                        //}
                        listLesen.add(permit);
                        idMohonDulu = permohonan.getPermohonanSebelum().getIdPermohonan();
                        noLesen = permit.getNoPermit();
 
                    }
                }
            }
        }
        viewOnly = Boolean.FALSE;
        return new JSP("pelupusan/common/lesen_terdahulu.jsp").addParameter("tab", "true");
    }
    public Resolution viewForm() {
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        pguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        permohonan = permohonanDAO.findById(idPermohonan);
        if (permohonan != null) {
            if(permohonan.getIdPermohonan()!=null){
                listLesen = new ArrayList<Permit>();
                if(permohonan.getPermohonanSebelum()!=null){
                    Permit permit = pelupusanService.findPermitByIdPermohonan(permohonan.getPermohonanSebelum().getIdPermohonan());
                   // Permohonan permohonanRef = pelupusanService.findIdPermohonan(idPermohonan);
                    if(permit!=null){
                       // if(permohonanRef != null){
                        //permit.setPermohonan(permohonanRef);
                        //}
                        listLesen.add(permit);
                        idMohonDulu = permohonan.getPermohonanSebelum().getIdPermohonan();
                        noLesen = permit.getNoPermit();
//                         permitSahLakuAsal = pelupusanService.findPermitSahLakuByIdMohon(permit.getPermohonan().getIdPermohonan()) ;
                    }
                }else
                    listLesen = new ArrayList<Permit>();
            }
        }
        viewOnly = Boolean.TRUE;
        return new JSP("pelupusan/common/lesen_terdahulu.jsp").addParameter("tab", "true");
    }
    
    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {

        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        pguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        permohonan = permohonanDAO.findById(idPermohonan);
        if (permohonan != null) {
            if (permohonan.getIdPermohonan() != null) {
                listLesen = new ArrayList<Permit>();
                if (permohonan.getPermohonanSebelum() != null) {
                    Permit permit = pelupusanService.findPermitByIdPermohonan(permohonan.getPermohonanSebelum().getIdPermohonan());
                     //Permohonan permohonanRef = pelupusanService.findIdPermohonan(idPermohonan);
                    if(permit!=null){
                        //if(permohonanRef != null){
                        //permit.setPermohonan(permohonanRef);
                        //}
                        listLesen.add(permit);
                        idMohonDulu = permohonan.getPermohonanSebelum().getIdPermohonan();
                        noLesen = permit.getNoPermit();
                        if(permohonan.getKodUrusan().getKod().equals("PJLB")){
                         permitSahLakuList = pelupusanService.findPermitSahLakuByIdPermit(permit.getIdPermit());
                         hakmilikPermohonan = pelupusanService.findByIdPermohonan(permit.getPermohonan().getIdPermohonan());
                         PermohonanLaporanPelan permohonanLaporanPelan = pelupusanService.findByNoLitho(permit.getPermohonan().getIdPermohonan());
                         hakmilikPermohonan.setKeteranganKadarUkur(permohonanLaporanPelan.getNoLitho());
                         pihak = permit.getPihak() ;
                        }
                        if(permohonan.getKodUrusan().getKod().equals("LMCRG")){
                         permitSahLakuList = pelupusanService.findPermitSahLakuByIdPermit(permit.getIdPermit());
                         hakmilikPermohonan = pelupusanService.findByIdPermohonan(permit.getPermohonan().getIdPermohonan());
                         PermohonanLaporanPelan permohonanLaporanPelan = pelupusanService.findByNoLitho(permit.getPermohonan().getIdPermohonan());
                         hakmilikPermohonan.setKeteranganKadarUkur(permohonanLaporanPelan.getNoLitho());
                         pihak = permit.getPihak() ;
                        }
                        //Add by Aizuddin Orogenic Group
                        if(permohonan.getKodUrusan().getKod().equals("MPJLB") || permohonan.getKodUrusan().getKod().equals("MLCRG")){
                         permitSahLakuList = pelupusanService.findPermitSahLakuByIdPermit(permit.getIdPermit());
                         hakmilikPermohonan = pelupusanService.findByIdPermohonan(permit.getPermohonan().getIdPermohonan());
                         PermohonanLaporanPelan permohonanLaporanPelan = pelupusanService.findByNoLitho(permit.getPermohonan().getIdPermohonan());
                         hakmilikPermohonan.setKeteranganKadarUkur(permohonanLaporanPelan.getNoLitho());
                         pihak = permit.getPihak() ;
                        } //End
                    }
                    if(permohonan.getKodUrusan().getKod().equals("PJLB")){
                    if( permitSahLakuList != null && !permitSahLakuList.isEmpty()){
                        for(int i = 0 ; i < permitSahLakuList.size(); i++){
                            permitSahLakuAsal = permitSahLakuList.get(0) ;
                        }
                    }
                    }
                    if(permohonan.getKodUrusan().getKod().equals("LMCRG")){
                    if( permitSahLakuList != null && !permitSahLakuList.isEmpty()){
                        for(int i = 0 ; i < permitSahLakuList.size(); i++){
                            permitSahLakuAsal = permitSahLakuList.get(0) ;
                        }
                    }
                    }
                    //Add by Aizuddin Orogenic Group
                    if(permohonan.getKodUrusan().getKod().equals("MPJLB") || permohonan.getKodUrusan().getKod().equals("MLCRG")){
                        if( permitSahLakuList != null && !permitSahLakuList.isEmpty()){
                            for(int i = 0 ; i < permitSahLakuList.size(); i++){
                                permitSahLakuAsal = permitSahLakuList.get(0) ;
                            }
                        }
                    } //End
                }
            }
        }
    }
    
    public Resolution cariLesen() {
        if(!StringUtils.isBlank(noLesen)){
            if(!noLesen.equals("1"))
                listLesen = pelupusanService.findPermitByNoLesen(noLesen);
            if(!listLesen.isEmpty()){
                for(Permit permit : listLesen){
                    idMohonDulu = permit.getPermohonan().getIdPermohonan();
                    if(permohonan.getKodUrusan().getKod().equals("PJLB")){
                         permitSahLakuList = pelupusanService.findPermitSahLakuByIdPermit(permit.getIdPermit());
                         hakmilikPermohonan = pelupusanService.findByIdPermohonan(permit.getPermohonan().getIdPermohonan());
                         PermohonanLaporanPelan permohonanLaporanPelan = pelupusanService.findByNoLitho(permit.getPermohonan().getIdPermohonan());
                          hakmilikPermohonan.setKeteranganKadarUkur(permohonanLaporanPelan.getNoLitho());
                         pihak = permit.getPihak() ;
                    }
                    if(permohonan.getKodUrusan().getKod().equals("LMCRG")){
                         permitSahLakuList = pelupusanService.findPermitSahLakuByIdPermit(permit.getIdPermit());
                         hakmilikPermohonan = pelupusanService.findByIdPermohonan(permit.getPermohonan().getIdPermohonan());
                         PermohonanLaporanPelan permohonanLaporanPelan = pelupusanService.findByNoLitho(permit.getPermohonan().getIdPermohonan());
                          hakmilikPermohonan.setKeteranganKadarUkur(permohonanLaporanPelan.getNoLitho());
                         pihak = permit.getPihak() ;
                    }
                    //Add by Aizuddin Orogenic Group
                    if(permohonan.getKodUrusan().getKod().equals("MPJLB") || permohonan.getKodUrusan().getKod().equals("MLCRG")){
                         permitSahLakuList = pelupusanService.findPermitSahLakuByIdPermit(permit.getIdPermit());
                         hakmilikPermohonan = pelupusanService.findByIdPermohonan(permit.getPermohonan().getIdPermohonan());
                         PermohonanLaporanPelan permohonanLaporanPelan = pelupusanService.findByNoLitho(permit.getPermohonan().getIdPermohonan());
                          hakmilikPermohonan.setKeteranganKadarUkur(permohonanLaporanPelan.getNoLitho());
                         pihak = permit.getPihak() ;
                    } //End
                    break;
                }
                if(permohonan.getKodUrusan().getKod().equals("PJLB")){
                 if( permitSahLakuList != null && !permitSahLakuList.isEmpty()){
                        for(int i = 0 ; i < permitSahLakuList.size(); i++){
                            permitSahLakuAsal = permitSahLakuList.get(0) ;
                        }
                    }
                }
                if(permohonan.getKodUrusan().getKod().equals("LMCRG")){
                 if( permitSahLakuList != null && !permitSahLakuList.isEmpty()){
                        for(int i = 0 ; i < permitSahLakuList.size(); i++){
                            permitSahLakuAsal = permitSahLakuList.get(0) ;
                        }
                    }
                }
                //Add by Aizuddin Orogenic Group
                if(permohonan.getKodUrusan().getKod().equals("MPJLB") || permohonan.getKodUrusan().getKod().equals("MLCRG")){
                 if( permitSahLakuList != null && !permitSahLakuList.isEmpty()){
                        for(int i = 0 ; i < permitSahLakuList.size(); i++){
                            permitSahLakuAsal = permitSahLakuList.get(0) ;
                        }
                    }
                } //End
                addSimpleMessage("Maklumat Dijumpai");
            }
            else
                addSimpleMessage("Tiada Lesen Terdahulu");
        }else{
            addSimpleError("Sila Nyatakan No Lesen");
        }
        
        return new JSP("pelupusan/common/lesen_terdahulu.jsp").addParameter("tab", "true");
    }
     public Resolution simpan() {
        if(!StringUtils.isBlank(idMohonDulu)){
            idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
            pguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
            permohonan = permohonanDAO.findById(idPermohonan);
            String sbb = getContext().getRequest().getParameter("permohonan.sebab");
            Permohonan permohonanSebelum = !StringUtils.isBlank(idMohonDulu)?permohonanDAO.findById(idMohonDulu):new Permohonan();
            if(permohonan!=null){
                if(permohonanSebelum!=null){
                    InfoAudit ia = new InfoAudit();
                    ia = permohonan.getInfoAudit();
                    ia.setDiKemaskiniOleh(pguna);
                    ia.setTarikhKemaskini(new java.util.Date());
                    permohonan.setInfoAudit(ia);
                    permohonan.setPermohonanSebelum(permohonanSebelum);
                    if(permohonan.getKodUrusan().getKod().equals("PJLB")){
                        permohonan.setSebab(sbb);
                    }
                    //Add by Aizuddin Orogenic Group
                    if(permohonan.getKodUrusan().getKod().equals("MPJLB") || permohonan.getKodUrusan().getKod().equals("MLCRG")){
                        permohonan.setSebab(sbb);
                    } //End
                    pelupusanService.simpanPermohonan(permohonan);
                    addSimpleMessage("Maklumat Berjaya Disimpan");
                }
            }
        }else{
            addSimpleError("ID Permohonan Terdahulu Tidak Dijumpai");
        }
        rehydrate();
        return new JSP("pelupusan/common/lesen_terdahulu.jsp").addParameter("tab", "true");
    }
    public String getIdPermohonan() {
        return idPermohonan;
    }

    public void setIdPermohonan(String idPermohonan) {
        this.idPermohonan = idPermohonan;
    }

    public List<Permit> getListLesen() {
        return listLesen;
    }

    public void setListLesen(List<Permit> listLesen) {
        this.listLesen = listLesen;
    }

    public Pengguna getPguna() {
        return pguna;
    }

    public void setPguna(Pengguna pguna) {
        this.pguna = pguna;
    }

    public Permohonan getPermohonan() {
        return permohonan;
    }

    public void setPermohonan(Permohonan permohonan) {
        this.permohonan = permohonan;
    }

    public String getNoLesen() {
        return noLesen;
    }

    public void setNoLesen(String noLesen) {
        this.noLesen = noLesen;
    }

    public String getIdMohonDulu() {
        return idMohonDulu;
    }

    public void setIdMohonDulu(String idMohonDulu) {
        this.idMohonDulu = idMohonDulu;
    }

    public boolean isViewOnly() {
        return viewOnly;
    }

    public void setViewOnly(boolean viewOnly) {
        this.viewOnly = viewOnly;
    }

    public PermitSahLaku getPermitSahLakuAsal() {
        return permitSahLakuAsal;
    }

    public void setPermitSahLakuAsal(PermitSahLaku permitSahLakuAsal) {
        this.permitSahLakuAsal = permitSahLakuAsal;
    }
    
    public List<PermitSahLaku> getPermitSahLakuList() {
        return permitSahLakuList;
    }

    public void setPermitSahLakuList(List<PermitSahLaku> permitSahLakuList) {
        this.permitSahLakuList = permitSahLakuList;
    }

    public HakmilikPermohonan getHakmilikPermohonan() {
        return hakmilikPermohonan;
    }

    public void setHakmilikPermohonan(HakmilikPermohonan hakmilikPermohonan) {
        this.hakmilikPermohonan = hakmilikPermohonan;
    }

    public Pihak getPihak() {
        return pihak;
    }

    public void setPihak(Pihak pihak) {
        this.pihak = pihak;
    }

    
}
