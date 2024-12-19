/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package etanah.view.stripes.common;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.dao.KodSyaratNyataDAO;
import etanah.dao.PermohonanDAO;
import etanah.model.Hakmilik;
import etanah.model.HakmilikPermohonan;
import etanah.model.InfoAudit;
import etanah.model.KodSyaratNyata;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.view.etanahActionBeanContext;
import java.util.List;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;
import org.hibernate.Transaction;
import etanah.service.RegService;
import org.hibernate.Session;

/**
 *
 * @author abu.mansur
 */
@UrlBinding("/common/maklumat_hakmilik_single")
public class MaklumatHakmilikSingleActionBean extends AbleActionBean {

    @Inject
    PermohonanDAO permohonanDAO;
    private List<HakmilikPermohonan> hakmilikPermohonanList;
    private HakmilikPermohonan hakmilikPermohonan;
    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;

    @Inject
    KodSyaratNyataDAO kodSyaratNyataDAO;
    @Inject
    RegService regService;
    private KodSyaratNyata kodSyaratNyata;
    @Inject
    private etanah.Configuration conf;
    private String idSyarat;
    private String negeri;
    private Hakmilik hakmilik;
    private Pengguna pengguna;

    @DefaultHandler
    public Resolution showForm(){
        if("04".equals(conf.getProperty("kodNegeri")))
            negeri = "melaka";
        return new JSP("common/paparan_hakmilik_single.jsp").addParameter("tab", "true");
    }

    public Resolution maklumatPetak(){
        return new JSP("common/paparan_maklumat_petak.jsp").addParameter("tab", "true");
    }
    public Resolution simpanPetak(){
        InfoAudit ia = new InfoAudit();
        if(!hakmilik.getNoPetak().isEmpty())
        {
            ia = hakmilik.getInfoAudit();
            ia.setDimasukOleh(ia.getDimasukOleh());
            ia.setTarikhMasuk(ia.getTarikhMasuk());
            ia.setDiKemaskiniOleh(pengguna);
            ia.setTarikhKemaskini(new java.util.Date());

             Transaction tx = sessionProvider.get().beginTransaction();
            tx.begin();
            try {
                hakmilik.setInfoAudit(ia);
                regService.simpanHakmilik(hakmilik);
                tx.commit();
            } catch (Exception e) {
                tx.rollback();
                Throwable t = e;
                // getting the root cause
                while (t.getCause() != null) {
                    t = t.getCause();
                }
                t.printStackTrace();
                addSimpleError("Hapus Tidak Berjaya.Sila Hubungi Pentadbir Sistem.");
                return null;

            }
        }else{
            ia.setDimasukOleh(pengguna);
            ia.setTarikhMasuk(new java.util.Date());
        }
        addSimpleMessage("Kemasukan Data Berjaya");
        return new JSP("common/paparan_maklumat_petak.jsp").addParameter("tab", "true");
    }

    @Before(stages={LifecycleStage.BindingAndValidation})
    public void rehydrate(){
        String idPermohonan = (String)getContext().getRequest().getSession().getAttribute("idPermohonan");
        pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        if(idPermohonan != null){
            Permohonan p = permohonanDAO.findById(idPermohonan);
            hakmilikPermohonanList = p.getSenaraiHakmilik();

            if(!(hakmilikPermohonanList.isEmpty()))
                hakmilikPermohonan = hakmilikPermohonanList.get(0);
                hakmilik = hakmilikPermohonan.getHakmilik();
        }
    }

    public Resolution popup(){
        kodSyaratNyata = kodSyaratNyataDAO.findById(idSyarat);
        return new JSP("hasil/papar_syarat_nyata.jsp").addParameter("popup", "true");
    }

    public Pengguna getPengguna() {
        return pengguna;
    }

    public void setPengguna(Pengguna pengguna) {
        this.pengguna = pengguna;
    }

    
    public HakmilikPermohonan getHakmilikPermohonan() {
        return hakmilikPermohonan;
    }

    public Hakmilik getHakmilik() {
        return hakmilik;
    }

    public void setHakmilik(Hakmilik hakmilik) {
        this.hakmilik = hakmilik;
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

    public String getIdSyarat() {
        return idSyarat;
    }

    public void setIdSyarat(String idSyarat) {
        this.idSyarat = idSyarat;
    }

    public KodSyaratNyata getKodSyaratNyata() {
        return kodSyaratNyata;
    }

    public void setKodSyaratNyata(KodSyaratNyata kodSyaratNyata) {
        this.kodSyaratNyata = kodSyaratNyata;
    }

    public String getNegeri() {
        return negeri;
    }

    public void setNegeri(String negeri) {
        this.negeri = negeri;
    }
}
