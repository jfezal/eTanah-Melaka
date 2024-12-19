/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.stripes.pelupusan;

import java.util.List;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.RedirectResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.StreamingResolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;
import org.apache.log4j.Logger;
import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.dao.PenempatanPesertaDAO;
import etanah.model.InfoAudit;
import etanah.model.PenempatanPeserta;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.service.PelupusanService;
import etanah.view.etanahActionBeanContext;
import org.hibernate.Session;
import org.apache.commons.lang.StringUtils;

/**
 *
 * @author Navin
 */
@UrlBinding("/pelupusan/penempatan_peserta")
public class PenempatanPesertaActionBean extends AbleActionBean {
    
    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;
    @Inject
    PenempatanPesertaDAO penempatanPesertaDAO ;
    @Inject
    PelupusanService plpservice ;
    private static final Logger LOG = Logger.getLogger(PenempatanPesertaActionBean.class);
    private Permohonan permohonan ;
    private PenempatanPeserta pnmptnPsrta ;
    private Pengguna pguna ;
    private String idPermohonan ;
    private List<PenempatanPeserta> penempatanPesertaList ;
    private boolean forLot = false ;
    
    @DefaultHandler
    public Resolution showForm() {
        String error = getContext().getRequest().getParameter("error");
        if (StringUtils.isNotBlank(error)) {
            addSimpleError(error);
        }
        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        return new JSP("pelupusan/gsa/penempatan_peserta.jsp").addParameter("tab", "true");
    }

    public Resolution viewPemohon() {
        String error = getContext().getRequest().getParameter("error");
        if (StringUtils.isNotBlank(error)) {
            addSimpleError(error);
        }
        getContext().getRequest().setAttribute("edit", Boolean.FALSE);
        return new JSP("pelupusan/gsa/penempatan_peserta.jsp").addParameter("tab", "true");
    }
    
    public Resolution showForLot() {
        String error = getContext().getRequest().getParameter("error");
        if (StringUtils.isNotBlank(error)) {
            addSimpleError(error);
        }
        forLot = Boolean.TRUE ;
        getContext().getRequest().setAttribute("edit", Boolean.FALSE);
        return new JSP("pelupusan/gsa/penempatan_peserta.jsp").addParameter("tab", "true");
    }
    
    public Resolution addPnmptnPsrta() {
        pnmptnPsrta = new PenempatanPeserta();

        getContext().getRequest().setAttribute("flag", Boolean.TRUE);
        return new JSP("pelupusan/gsa/add_penempatan_peserta.jsp").addParameter("tab", "true");
    }
    
    public Resolution updatePnmptnPsrta() {
        String idPnmptnPsrta = getContext().getRequest().getParameter("idPnmptnPsrta");
        getContext().getRequest().setAttribute("flag", Boolean.TRUE);
        pnmptnPsrta = plpservice.findOnePenempatanPesertaByIdPnmptnPsrta(Long.parseLong(idPnmptnPsrta));
        forLot = Boolean.FALSE ;
        return new JSP("pelupusan/gsa/update_penempatan_peserta.jsp").addParameter("tab", "true");
    }
    
    public Resolution updateLotPnmptnPsrta() {
        String idPnmptnPsrta = getContext().getRequest().getParameter("idPnmptnPsrta");
        getContext().getRequest().setAttribute("flag", Boolean.TRUE);
        pnmptnPsrta = plpservice.findOnePenempatanPesertaByIdPnmptnPsrta(Long.parseLong(idPnmptnPsrta));
        forLot = Boolean.TRUE ;
        return new JSP("pelupusan/gsa/update_penempatan_peserta.jsp").addParameter("tab", "true");
    }
    
     @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {
         pguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
         
         String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
         //idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
          permohonan = plpservice.findPermohonanByIdPermohonanWithoutKodUrusan(idPermohonan);
          if(permohonan != null){
              penempatanPesertaList = plpservice.findPenempatanPesertaByIdMohon(idPermohonan);
          }
     
     }

    public Resolution refreshMaklumatPenempatan() {
        rehydrate();
        return new RedirectResolution(PenempatanPesertaActionBean.class, "locate");
    }
    
    public Resolution refreshMaklumatLot() {
        rehydrate();
        return showForLot();
    }
    
    public Resolution simpanPnmptnPsrta() {
        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = plpservice.findPermohonanByIdPermohonanWithoutKodUrusan(idPermohonan);
        Pengguna pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        penempatanPesertaList = plpservice.findPenempatanPesertaByIdMohon(idPermohonan);
//        int a = 1 ;
//        if(penempatanPesertaList != null){
//            a = a + penempatanPesertaList.size() ;
//        }
        if (validationAdd()) {
            getContext().getRequest().setAttribute("flag", Boolean.TRUE);
            return new JSP("pelupusan/gsa/add_penempatan_peserta.jsp").addParameter("popup", "true");
        }
        InfoAudit infoAudit = new InfoAudit();
        infoAudit.setDimasukOleh(pengguna);
        infoAudit.setTarikhMasuk(new java.util.Date());
        PenempatanPeserta pnmptnPsrtaTemp = new PenempatanPeserta() ;
//        pnmptnPsrtaTemp.setIdPenempatanPeserta("" + a);
        pnmptnPsrtaTemp.setPermohonan(permohonan);
        pnmptnPsrtaTemp.setNama(pnmptnPsrta.getNama());
        pnmptnPsrtaTemp.setNoPengenalan(pnmptnPsrta.getNoPengenalan());
        pnmptnPsrtaTemp.setInfoAudit(infoAudit);
        pnmptnPsrtaTemp.setUmur(pnmptnPsrta.getUmur());
        pnmptnPsrtaTemp.setAlamat1(pnmptnPsrta.getAlamat1());
        pnmptnPsrtaTemp.setAlamat2(pnmptnPsrta.getAlamat2());
        pnmptnPsrtaTemp.setAlamat3(pnmptnPsrta.getAlamat3());
        pnmptnPsrtaTemp.setAlamat4(pnmptnPsrta.getAlamat4());
        pnmptnPsrtaTemp.setPoskod(pnmptnPsrta.getPoskod());
        pnmptnPsrtaTemp.setNegeri(pnmptnPsrta.getNegeri());
        pnmptnPsrtaTemp.setMarkahTemuduga(pnmptnPsrta.getMarkahTemuduga());
        plpservice.savePenempatanPeserta(pnmptnPsrtaTemp);
        rehydrate();
        getContext().getRequest().setAttribute("flag", Boolean.FALSE);
        return new JSP("pelupusan/gsa/add_penempatan_peserta.jsp").addParameter("tab", "true");
    }
    
    
    
    public boolean validationAdd() {
        boolean flag = false;
        System.out.println("pnmptnPsrta.getNegeri().getKod() :"+pnmptnPsrta.getNegeri().getKod());
        if ((pnmptnPsrta.getNama() == null) || (pnmptnPsrta.getNama().trim().equals(""))) {
                flag = true;
                addSimpleError("Sila Masukkan Nama");
            } else if (pnmptnPsrta.getNoPengenalan() == null) {
                flag = true;
                addSimpleError("Sila Masukkan No Pengenalan");
            } else if (pnmptnPsrta.getUmur() == null) {
                flag = true;
                addSimpleError("Sila Masukkan Umur");
            } else if ((pnmptnPsrta.getAlamat1() == null) || (pnmptnPsrta.getAlamat1().trim().equals(""))) {
                flag = true;
                addSimpleError("Sila Masukkan Alamat");
            } else if (pnmptnPsrta.getPoskod() == null) {
                flag = true;
                addSimpleError("Sila Masukkan Poskod");
            } else if (pnmptnPsrta.getNegeri().getKod().equals("0")) {
                flag = true;
                addSimpleError("Sila Pilih Negeri");

            }  else if (pnmptnPsrta.getMarkahTemuduga() == null) {
                flag = true;
                addSimpleError("Sila Masukkan Markah Temuduga");

            }
        return flag;
    }
    
     public Resolution updatePP() {
         String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = plpservice.findPermohonanByIdPermohonanWithoutKodUrusan(idPermohonan);
         pguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        if (validationUpdate()) {
            getContext().getRequest().setAttribute("flag", Boolean.TRUE);
            return new JSP("pelupusan/gsa/update_penempatan_peserta.jsp").addParameter("popup", "true");
        }
        System.out.println("permohonan.getIdPermohonan() :"+permohonan.getIdPermohonan());
        InfoAudit infoAudit = new InfoAudit();
        PenempatanPeserta pnmptnPsrtaTemp = plpservice.findOnePenempatanPesertaByIdPnmptnPsrta(pnmptnPsrta.getIdPenempatanPeserta()) ;
        if(pnmptnPsrta != null){
        pnmptnPsrtaTemp.setNama(pnmptnPsrta.getNama());
        pnmptnPsrtaTemp.setNoPengenalan(pnmptnPsrta.getNoPengenalan());
        infoAudit = pnmptnPsrtaTemp.getInfoAudit();
        infoAudit.setDiKemaskiniOleh(pguna);
        infoAudit.setTarikhKemaskini(new java.util.Date());
        pnmptnPsrtaTemp.setInfoAudit(infoAudit);
        pnmptnPsrtaTemp.setPermohonan(permohonan);
        pnmptnPsrtaTemp.setUmur(pnmptnPsrta.getUmur());
        pnmptnPsrtaTemp.setAlamat1(pnmptnPsrta.getAlamat1());
        pnmptnPsrtaTemp.setAlamat2(pnmptnPsrta.getAlamat2());
        pnmptnPsrtaTemp.setAlamat3(pnmptnPsrta.getAlamat3());
        pnmptnPsrtaTemp.setAlamat4(pnmptnPsrta.getAlamat4());
        pnmptnPsrtaTemp.setPoskod(pnmptnPsrta.getPoskod());
        pnmptnPsrtaTemp.setNegeri(pnmptnPsrta.getNegeri());
        pnmptnPsrtaTemp.setMarkahTemuduga(pnmptnPsrta.getMarkahTemuduga());
        }
        plpservice.updatePenempatanPeserta(pnmptnPsrtaTemp);
        rehydrate();
        forLot = Boolean.FALSE ;
        getContext().getRequest().setAttribute("flag", Boolean.FALSE);
        return new JSP("pelupusan/gsa/update_penempatan_peserta.jsp").addParameter("tab", "true");
    }
     
    public Resolution updateLot() {
        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = plpservice.findPermohonanByIdPermohonanWithoutKodUrusan(idPermohonan);
         pguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        if (pnmptnPsrta.getNoLot() == null || pnmptnPsrta.getNoLot().trim().equals("")) {
            addSimpleError("Sila Masukkan Lot");
            forLot = Boolean.TRUE ;
            getContext().getRequest().setAttribute("flag", Boolean.TRUE);
            return new JSP("pelupusan/gsa/update_penempatan_peserta.jsp").addParameter("popup", "true");
        }
        InfoAudit infoAudit = new InfoAudit();
        PenempatanPeserta pnmptnPsrtaTemp = plpservice.findOnePenempatanPesertaByIdPnmptnPsrta(pnmptnPsrta.getIdPenempatanPeserta()) ;
        if(pnmptnPsrta != null){
        pnmptnPsrtaTemp.setNama(pnmptnPsrta.getNama());
        pnmptnPsrtaTemp.setNoPengenalan(pnmptnPsrta.getNoPengenalan());
        infoAudit = pnmptnPsrtaTemp.getInfoAudit();
        infoAudit.setDiKemaskiniOleh(pguna);
        infoAudit.setTarikhKemaskini(new java.util.Date());
        pnmptnPsrtaTemp.setInfoAudit(infoAudit);
        pnmptnPsrtaTemp.setPermohonan(permohonan);
        pnmptnPsrtaTemp.setUmur(pnmptnPsrta.getUmur());
        pnmptnPsrtaTemp.setAlamat1(pnmptnPsrta.getAlamat1());
        pnmptnPsrtaTemp.setAlamat2(pnmptnPsrta.getAlamat2());
        pnmptnPsrtaTemp.setAlamat3(pnmptnPsrta.getAlamat3());
        pnmptnPsrtaTemp.setAlamat4(pnmptnPsrta.getAlamat4());
        pnmptnPsrtaTemp.setPoskod(pnmptnPsrta.getPoskod());
        pnmptnPsrtaTemp.setNegeri(pnmptnPsrta.getNegeri());
        pnmptnPsrtaTemp.setMarkahTemuduga(pnmptnPsrta.getMarkahTemuduga());
        pnmptnPsrtaTemp.setNoLot(pnmptnPsrta.getNoLot());
        }
        plpservice.updatePenempatanPeserta(pnmptnPsrtaTemp);
        rehydrate();
        forLot = Boolean.TRUE ;
        getContext().getRequest().setAttribute("flag", Boolean.FALSE);
        
        return new JSP("pelupusan/gsa/update_penempatan_peserta.jsp").addParameter("tab", "true");
    } 
    
    
    public boolean validationUpdate() {
        boolean flag = false;
            if ((pnmptnPsrta.getAlamat1() == null) || (pnmptnPsrta.getAlamat1().trim().equals(""))) {
                flag = true;
                addSimpleError("Sila Masukkan Alamat");
            } else if (pnmptnPsrta.getPoskod() == null) {
                flag = true;
                addSimpleError("Sila Masukkan Poskod");
            } else if (pnmptnPsrta.getNegeri().getKod().equals("0")) {
                flag = true;
                addSimpleError("Sila Pilih Negeri");

            } else if (pnmptnPsrta.getMarkahTemuduga() == null) {
                flag = true;
                addSimpleError("Sila Masukkan Markah Temuduga");

            }
        return flag;
    }
    
    public Resolution deletePnmptnPsrta() {

        String idPnmptnPsrta = getContext().getRequest().getParameter("idPnmptnPsrta");
        if (idPnmptnPsrta != null) {
            
            PenempatanPeserta penempatanPeserta = plpservice.findOnePenempatanPesertaByIdPnmptnPsrta(Long.parseLong(idPnmptnPsrta));
            if (penempatanPeserta != null) {
                plpservice.deletePenempatanPeserta(penempatanPeserta);

            }
        }
        rehydrate();
        return showForm();
    }

    public String getIdPermohonan() {
        return idPermohonan;
    }

    public void setIdPermohonan(String idPermohonan) {
        this.idPermohonan = idPermohonan;
    }

    public List<PenempatanPeserta> getPenempatanPesertaList() {
        return penempatanPesertaList;
    }

    public void setPenempatanPesertaList(List<PenempatanPeserta> penempatanPesertaList) {
        this.penempatanPesertaList = penempatanPesertaList;
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

    public PenempatanPeserta getPnmptnPsrta() {
        return pnmptnPsrta;
    }

    public void setPnmptnPsrta(PenempatanPeserta pnmptnPsrta) {
        this.pnmptnPsrta = pnmptnPsrta;
    }

    public boolean isForLot() {
        return forLot;
    }

    public void setForLot(boolean forLot) {
        this.forLot = forLot;
    }
    
    
}
