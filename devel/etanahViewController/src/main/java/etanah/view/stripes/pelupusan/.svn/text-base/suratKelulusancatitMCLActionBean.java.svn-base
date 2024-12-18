

package etanah.view.stripes.pelupusan;


import able.stripes.AbleActionBean ;
import able.stripes.JSP ;
import com.google.inject.Inject ;
import etanah.dao.HakmilikDAO;
import etanah.service.PelupusanService ;
import etanah.dao.PermohonanDAO ;
import etanah.dao.HakmilikPermohonanDAO ;
import etanah.dao.PermohonanKertasDAO;
import etanah.model.Hakmilik;
import etanah.model.Permohonan ;
import etanah.model.Pengguna ;
import etanah.model.HakmilikPermohonan ;
import etanah.model.InfoAudit;
import etanah.model.PermohonanKertas;
import etanah.view.etanahActionBeanContext ;
import java.util.List;
import net.sourceforge.stripes.action.Before ;
import net.sourceforge.stripes.action.DefaultHandler ;
import net.sourceforge.stripes.action.UrlBinding ;
import net.sourceforge.stripes.action.Resolution ;
import net.sourceforge.stripes.controller.LifecycleStage ;

/**
 *
 * @author afham
 */

@UrlBinding("/pelupusan/surat_kelulusan_catit_MCL")
public class suratKelulusancatitMCLActionBean extends AbleActionBean {
    @Inject
    PermohonanDAO permohonanDAO ;
    @Inject
    PermohonanKertasDAO permohonanKertasDAO ;
    @Inject
    PelupusanService plpservice ;
    private Permohonan permohonan ;
    private PermohonanKertas permohonanKertas ;
    private String tajuk ;

    @DefaultHandler
    public Resolution showForm(){
        return new JSP("pelupusan/surat_kelulusan_catit_MCL.jsp").addParameter("tab", "true") ;
    }

    @Before(stages={LifecycleStage.BindingAndValidation})
    public void rehydrate()
    {
        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = permohonanDAO.findById(idPermohonan);
        String[] tname = {"permohonan"};
        Object[] tvalue = {permohonan};

        List<PermohonanKertas> listppmk = permohonanKertasDAO.findByEqualCriterias(tname, tvalue, null) ;
        permohonanKertas = listppmk.get(0) ;
        tajuk = permohonanKertas.getTajuk() ;
    }

    public Resolution simpan(){


        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = permohonanDAO.findById(idPermohonan);

        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        InfoAudit infoAudit = new InfoAudit();

        if (permohonanKertas != null) {


            // ppmn = permohonanKertasDAO.findById(kertasKandungan.getKertas().getIdKertas());
            infoAudit.setDiKemaskiniOleh(peng);
            infoAudit.setTarikhKemaskini(new java.util.Date());

            permohonanKertas.setInfoAudit(infoAudit);
//            permohonanKertas.setPermohonan(permohonan);
//            permohonanKertas.setCawangan(permohonan.getCawangan());
            permohonanKertas.setTajuk(tajuk);
            plpservice.simpanPermohonanKertas(permohonanKertas);
        }
          addSimpleMessage("Maklumat Telah Disimpan") ;

        return new JSP("pelupusan/surat_kelulusan_catit_MCL.jsp").addParameter("tab", "true") ;
    }

    public Permohonan getPermohonan() {
        return permohonan;
    }

    public void setPermohonan(Permohonan permohonan) {
        this.permohonan = permohonan;
    }

    public PermohonanKertas getPermohonanKertas() {
        return permohonanKertas;
    }

    public void setPermohonanKertas(PermohonanKertas permohonanKertas) {
        this.permohonanKertas = permohonanKertas;
    }

    public String getTajuk() {
        return tajuk;
    }

    public void setTajuk(String tajuk) {
        this.tajuk = tajuk;
    }

    

}
