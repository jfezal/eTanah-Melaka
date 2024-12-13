/*
    Document   : Borang G
    Created on : May 28, 2012, 5:45 PM
    Author     : Aizuddin Orogenic Group
    Description: Borang G for MPJLB and MLCRG
 */
package etanah.view.stripes.pelupusan;

import java.util.Calendar;
import java.util.Date;
import able.stripes.AbleActionBean;
import able.stripes.JSP;
//import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Injector;
import etanah.dao.KodJenisPermitDAO;
import etanah.dao.KodTuntutDAO;
import etanah.dao.PermohonanDAO;
//import etanah.common.Functions;
import etanah.model.*;
import etanah.service.BPelService;
//import etanah.service.MaklumatPerizabanServiceModule;
import etanah.service.PelupusanService;
import etanah.view.etanahActionBeanContext;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
//import etanah.service.PelupusanServiceModule;
import net.sourceforge.stripes.action.ActionBean;
import net.sourceforge.stripes.action.ActionBeanContext;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.RedirectResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;
import oracle.bpel.services.workflow.task.model.Task;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

//@UrlBinding("/pelupusan/BorangG.action")
@UrlBinding("/pelupusan/BorangG/{currentStage}")

public class BorangGActionBean extends AbleActionBean {
	//Call Guice Service
//	Injector injector = Guice.createInjector(new PelupusanServiceModule());
//	PelupusanService pelupusanService = injector.getInstance(PelupusanService.class);
        @Inject
	PelupusanService pelupusanService;
        @Inject
        PermohonanDAO permohonanDAO;
        @Inject
        KodJenisPermitDAO kodJenisPermitDAO;
        @Inject
        KodTuntutDAO kodTuntutDAO;

private static final Logger LOG = Logger.getLogger(BorangGActionBean.class);        
private ActionBeanContext context;
private String sewaTahunan;
private String tempohDiluluskan;
private Date tarikhTamat;
private Date tarikhHabis;
private String no_lesen;
private String noLot;
private String idPermohonan;
private String idHakmilik;
private String id;
private int intTempohHabis;
private Pengguna peng;
private Permohonan permohonan;
private Pemohon pemohon;
private Permit permit;
private Permit prmtSave;
private Permit psblm;
private Permit psblmsave;
private PermitSahLaku permitSahLaku;
private PermohonanTuntutanKos mohonTuntutKos;
private PermohonanTuntutanKos ptksave;
private HakmilikPermohonan mohonHakmilik;
private Hakmilik hakmilik;
private String currentStage;

SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
       

	@DefaultHandler
        public Resolution editForm() {
		return new JSP("pelupusan/BorangG.jsp").addParameter("tab", "true");
	}
        
        public Resolution showForm() {
            getContext().getRequest().setAttribute("edit", Boolean.FALSE);
            return new JSP("pelupusan/BorangG.jsp").addParameter("tab", "true");
        }
        
        public String getCurrentStage() {
            return currentStage;
        }

        public void setCurrentStage(String currentStage) {
            this.currentStage = currentStage;
        }
        
//	@Before({LifecycleStage.BindingAndValidation})
        @Before(stages = { LifecycleStage.BindingAndValidation })  
	public void rehydrate() {
                peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
		idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
                idHakmilik = (String) getContext().getRequest().getSession().getAttribute("idHakmilik");
                permohonan = pelupusanService.findIdPermohonan(idPermohonan);
                
                if (permohonan.getPermohonanSebelum() != null && permohonan.getKodUrusan().getKod().equals("PJLB")) {
                    mohonHakmilik = pelupusanService.findByIdPermohonan(permohonan.getPermohonanSebelum().getIdPermohonan());
                    mohonTuntutKos =  pelupusanService.findMohonTuntutKosByIdPermohonan(idPermohonan);
                    permit = pelupusanService.findPermitByIdPermohonan(permohonan.getPermohonanSebelum().getIdPermohonan());
                } else {
                    mohonHakmilik = pelupusanService.findByIdPermohonan(idPermohonan);
                    mohonTuntutKos =  pelupusanService.findMohonTuntutKosByIdPermohonan(idPermohonan);
                    permit = pelupusanService.findPermitByIdPermohonan(idPermohonan);
                }
                                
                if (permit != null) {
                    permitSahLaku = pelupusanService.findPermitSahLakuByIdMohonIdPermit(idPermohonan, permit.getIdPermit());
                    tarikhHabis = permitSahLaku.getTarikhPermitTamat();
                    //tarikhHabis = permit.getTarikhPermitMula();
                    //tarikhTamat = permit.getTarikhpermitAkhir();
                    no_lesen = permit.getNoPermit();
                    
                    currentStage = getContext().getRequest().getParameter("currentStage");
                    LOG.info("currentStage: " + currentStage);
                    if(!StringUtils.isBlank(currentStage) &&currentStage.equals("sedia")){
                        this.getTempohDiluluskan();
                    }else if(!StringUtils.isBlank(currentStage) &&currentStage.equals("maklum")){
                        tempohDiluluskan = permit.getTempohSah().toString();
                        this.setTempohDiluluskan(tempohDiluluskan);
                        LOG.info(" -----Tempoh diluluskan = " +this.getTempohDiluluskan());
                    }else if(!StringUtils.isBlank(currentStage) &&currentStage.equals("semak")){
                        tempohDiluluskan = permit.getTempohSah().toString();
                        this.setTempohDiluluskan(tempohDiluluskan);
                        LOG.info(" -----Tempoh diluluskan untuk disemak -----= " +this.getTempohDiluluskan());
                    }else
                        this.getTempohDiluluskan();
                    if(no_lesen != null) {
                        no_lesen = permit.getNoPermit();
                        this.setNo_lesen(no_lesen);
                        LOG.info(" -----No Lesen not null = " +this.getNo_lesen());
                    } else {
                        Permit maxpermit = pelupusanService.getMaxOfNoPermit();
                        int maxVal = Integer.parseInt(maxpermit.getNoPermit()) + 1;
                        this.setNo_lesen(maxVal + "");
                        LOG.info(" -----No Lesen null so auto-generate = " +this.getNo_lesen());
                    } 
                
                    if(tempohDiluluskan != null) {
                        tempohDiluluskan = permit.getTempohSah().toString();
                        this.setTempohDiluluskan(tempohDiluluskan);
                        LOG.info(" -----Tempoh diluluskan = " +this.getTempohDiluluskan());
                        if(tarikhHabis!=null){
                            Calendar cal=Calendar.getInstance();
                            cal.setTime(tarikhHabis);
                            intTempohHabis =  new Integer(tempohDiluluskan).intValue();
                            cal.add(Calendar.MONTH,intTempohHabis);
                            tarikhTamat = cal.getTime();
                            this.setTarikhTamat(tarikhTamat);
                            } else {
                                addSimpleMessage("Tiada Maklumat Lesen Terdahulu");
                            } 
                     } else {
                        this.setTempohDiluluskan("");
                     }    
                        
                }   
                
                if (mohonTuntutKos != null){
                    sewaTahunan = mohonTuntutKos.getAmaunTuntutan().toString();
                    this.setSewaTahunan(sewaTahunan);
                }else {
                    this.setSewaTahunan("");
                } 
                     
                //If we want to retrieve no. lot, in Borang G jsp no need to retrieve no. lot
                if (mohonHakmilik != null) {
                    if (mohonHakmilik.getHakmilik() != null) {
                        hakmilik = mohonHakmilik.getHakmilik();
                        noLot = hakmilik.getNoLot();
                    } else {
                        noLot = mohonHakmilik.getNoLot();
                    }
                }    
	}
        
        public Resolution saveData() {

//		Injector injector = Guice
//				.createInjector(new MaklumatPerizabanServiceModule());
//		PelupusanService pelupusanService = injector
//				.getInstance(PelupusanService.class);
                idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
                //permohonanDAO.findById(idPermohonan);
                idHakmilik = (String) getContext().getRequest().getSession().getAttribute("idHakmilik");
                
                LOG.info("tempohDiluluskan: " + tempohDiluluskan);
                System.out.println("Save tempoh diluluskan");
		this.getTempohDiluluskan();
		permit = pelupusanService.findPermitByIdPermohonan(idPermohonan);
		permit.setTempohSah(new Integer(tempohDiluluskan));
                
                LOG.info("no_lesen: " + no_lesen);
                System.out.println("Save no lesen");
		permit.setNoPermit(this.getNo_lesen());
                
                LOG.info("tarikhTamat: " + tarikhTamat);
                System.out.println("Save tarikh tamat");
                this.getTarikhTamat();
                permit.setTarikhPermitMula(tarikhTamat);
                                                               
                LOG.info("sewaTahunan: " + sewaTahunan);
                System.out.println("Save sewa tahunan");
                this.getSewaTahunan();
                mohonTuntutKos =  pelupusanService.findMohonTuntutKosByIdPermohonan(idPermohonan);
                mohonTuntutKos.setAmaunTuntutan(new BigDecimal(sewaTahunan));
                
                ptksave = pelupusanService.simpanPermohonanTuntutanKos(mohonTuntutKos);
            	System.out.println("Save sewa tahunan ialah " +ptksave.getAmaunTuntutan());    
                
                LOG.info("No Lesen: " + no_lesen);
                this.getNo_lesen();
                permohonan = pelupusanService.findIdPermohonan(idPermohonan);
                id = permohonan.getPermohonanSebelum().getIdPermohonan();
                psblm = pelupusanService.findPermitByIdPermohonan(id);
                psblm.setNoPermit(no_lesen);
                
                psblmsave = pelupusanService.saveOrUpdate(permit);
                System.out.println("Save tempoh diluluskan ialah " +prmtSave.getTempohSah());
                System.out.println("Save no lesen ialah " +prmtSave.getNoPermit());
                System.out.println("Save sewa tahunan ialah " +ptksave.getAmaunTuntutan()); 
                
                
                addSimpleMessage("Maklumat telah berjaya disimpan.");
		return new ForwardResolution(
				"/pelupusan/BorangG.jsp").addParameter("tab", "true");

	}
        
        public Resolution saveData2() {
            Pengguna pguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
            idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
            idHakmilik = (String) getContext().getRequest().getSession().getAttribute("idHakmilik");
            
            LOG.info("---------Saving permit---------------");
            permit = pelupusanService.findPermitByIdPermohonan(idPermohonan);
            LOG.info("-------------permit--------------------" + permit);
            PermitSahLaku permitSahLakuTemp = null;
            InfoAudit ia = new InfoAudit();
            
            if (permit != null) {
                LOG.info("-------permitSahLaku--------------------" + permitSahLaku);
                permit.setKodCawangan(permohonan.getCawangan());
                ia = permit.getInfoAudit();
                ia.setDiKemaskiniOleh(pguna);
                ia.setTarikhKemaskini(new java.util.Date());
            } else {
                LOG.info("-------permit is Null--------------------");
                LOG.info("-------Generating No permit---------");
                permit = new Permit();
                permit.setPermohonan(permohonan);
               
                ia.setDimasukOleh(pguna);
                ia.setTarikhMasuk(new Date());
                permit.setInfoAudit(ia);
                permit.setKodCawangan(permohonan.getCawangan());
                permit.setTarikhPermit(new java.util.Date());
            }
            
            KodJenisPermit kodJenis = kodJenisPermitDAO.findById("G");
            if(tarikhHabis!=null)
                LOG.info(" -----Tarikh Lesen Terdahulu Tamat = " +this.getTarikhHabis());                  
                permit.setTarikhPermitMula(this.getTarikhHabis());
                LOG.info("-------Saving Success! Tarikh Lesen Baru Mula---------");
            if(tarikhTamat!=null)
                LOG.info(" -----Tarikh Tamat Lesen Baru = " +this.getTarikhTamat());
                permit.setTarikhpermitAkhir(this.getTarikhTamat());
                LOG.info("-------Saving Success! Tarikh Lesen Baru Tamat---------");
            permit.setKodJenisPermit(kodJenis);
            pemohon = pelupusanService.findPemohonByIdPemohon(idPermohonan);
            permit.setPihak(pemohon.getPihak());
            if (no_lesen != null) 
                permit.setNoPermit(no_lesen);
            if (tempohDiluluskan!=null)
                LOG.info(" -----tempohDiluluskan = " +this.getTempohDiluluskan());
                permit.setTempohSah(new Integer(this.getTempohDiluluskan()));
                LOG.info("-------Saving Success! Tempoh Diluluskan---------");
            permit = pelupusanService.saveOrUpdate(permit);
            
            LOG.info("---------Saving permitSahLaku---------------");
            permitSahLakuTemp = pelupusanService.findPermitSahLakuByIdMohonIdPermit(idPermohonan, permit.getIdPermit());
            InfoAudit info;
            if (permitSahLakuTemp != null) {
                LOG.info("-------permitSahLaku Not Null---------------::");
                info = permitSahLakuTemp.getInfoAudit();
                info.setDiKemaskiniOleh(pguna);
                info.setTarikhKemaskini(new java.util.Date());
                permitSahLakuTemp.setInfoAudit(info);

            } else {
                LOG.info("-------permitSahLaku is Null--------::");
                permitSahLakuTemp = new PermitSahLaku();
                permitSahLakuTemp.setPermit(permit);
                permitSahLakuTemp.setPermohonan(permohonan);
                info = new InfoAudit();
                info.setDimasukOleh(pguna);
                info.setTarikhMasuk(new java.util.Date());
                permitSahLakuTemp.setInfoAudit(info);
                permitSahLakuTemp.setKodCawangan(permohonan.getCawangan());
            }
            if (permitSahLakuTemp.getNoSiri() == null) {
                PermitSahLaku pTemp = pelupusanService.getMaxOfNoSiri();
                int maxSiri = Integer.parseInt(pTemp.getNoSiri()) + 1;
                permitSahLakuTemp.setNoSiri(maxSiri + "");
            }
                      
            permitSahLakuTemp.setTarikhPermit(new java.util.Date());
//            permitSahLaku.setTarikhPermitMula(tarikhHabis);
            permitSahLakuTemp.setTarikhPermitMula(permitSahLaku.getTarikhPermitMula());
//            permitSahLaku.setTarikhPermitTamat(tarikhTamat);
            permitSahLakuTemp.setTarikhPermitTamat(permitSahLaku.getTarikhPermitTamat());
            pelupusanService.simpanPermitSahLaku(permitSahLakuTemp);
        
            LOG.info("-------Saving Mohon Tuntut Kos---------");
            mohonTuntutKos =  pelupusanService.findMohonTuntutKosByIdPermohonan(idPermohonan);
            InfoAudit infoAudit = new InfoAudit();
            if ((mohonTuntutKos != null)) {
                infoAudit = mohonTuntutKos.getInfoAudit();
                infoAudit.setDiKemaskiniOleh(pguna);
                infoAudit.setTarikhKemaskini(new java.util.Date());
            } else {
                mohonTuntutKos = new PermohonanTuntutanKos();
                infoAudit.setDimasukOleh(pguna);
                infoAudit.setTarikhMasuk(new java.util.Date());
            }
            mohonTuntutKos.setCawangan(permohonan.getCawangan());
            if (permohonan.getKodUrusan().getKod().equals("MLCRG")) {
                mohonTuntutKos.setItem("Bayaran Memperbaharui Lesen Cari Gali dan Penjelajahan");
                mohonTuntutKos.setKodTuntut(kodTuntutDAO.findById("DISMCR"));
                mohonTuntutKos.setKodTransaksi(kodTuntutDAO.findById("DISMCR").getKodKewTrans());
            } else if (permohonan.getKodUrusan().getKod().equals("MPJLB")) {
                mohonTuntutKos.setItem("Bayaran Memperbaharui Lesen Pajakan Lombong");
                mohonTuntutKos.setKodTuntut(kodTuntutDAO.findById("DISMPL")); 
                mohonTuntutKos.setKodTransaksi(kodTuntutDAO.findById("DISMPL").getKodKewTrans());
            }
            mohonTuntutKos.setPermohonan(permohonan);
            mohonTuntutKos.setInfoAudit(infoAudit);

            if (sewaTahunan != null) {
                LOG.info(" -----Sewa Tahunan = " +this.getSewaTahunan());
                mohonTuntutKos.setAmaunTuntutan(new BigDecimal(this.getSewaTahunan()));
                LOG.info("-------Saving Success! Sewa Tahunan---------");
            }
            pelupusanService.simpanPermohonanTuntutanKos1(mohonTuntutKos);
            
//            rehydrate();
            addSimpleMessage("Maklumat telah berjaya disimpan.");
		return new ForwardResolution(
				"/pelupusan/BorangG.jsp").addParameter("tab", "true");
        }

        public String stageId(String taskId) {
            BPelService service = new BPelService();
            String stageId = null;
            if (StringUtils.isNotBlank(taskId)) {
                Task t = null;
                t = service.getTaskFromBPel(taskId, peng);
                stageId = t.getSystemAttributes().getStage();
            }
            return stageId;
        }    
	
	public String getTempohHabis() {
		return tempohHabis;
	}

	public void setTempohHabis(String tempohHabis) {
		this.tempohHabis = tempohHabis;
	}

	private String tempohHabis;
	
	public String getNo_lesen() {
		return no_lesen;
	}

	public void setNo_lesen(String no_lesen) {
		this.no_lesen = no_lesen;
	}

	public Date getTarikhTamat() {
		return tarikhTamat;
	}

	public void setTarikhTamat(Date tarikhTamat) {
		this.tarikhTamat = tarikhTamat;
	}

	public String getTempohDiluluskan() {
		return tempohDiluluskan;
	}

	public void setTempohDiluluskan(String tempohDiluluskan) {
		this.tempohDiluluskan = tempohDiluluskan;
	}

	public String getSewaTahunan() {
		return sewaTahunan;
	}

	public void setSewaTahunan(String sewaTahunan) {
		this.sewaTahunan = sewaTahunan;
	}
        
        public SimpleDateFormat getSdf() {
            return sdf;
        }

        public void setSdf(SimpleDateFormat sdf) {
            this.sdf = sdf;
        }

	public Date getTarikhHabis() {
            return tarikhHabis;
        }

        public void setTarikhHabis(Date tarikhHabis) {
            this.tarikhHabis = tarikhHabis;
        }
        
        public Permit getPermit() {
            return permit;
        }

        public void setPermit(Permit permit) {
            this.permit = permit;
        }
}

