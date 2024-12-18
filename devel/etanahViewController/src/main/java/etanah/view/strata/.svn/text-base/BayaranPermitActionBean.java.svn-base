/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package etanah.view.strata;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.dao.PermohonanDAO;
import etanah.model.HakmilikPermohonan;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.model.PermohonanPermitButir;
import etanah.service.StrataPtService;
import net.sourceforge.stripes.action.UrlBinding;
import etanah.view.BasicTabActionBean;
import etanah.view.etanahActionBeanContext;
import java.util.ArrayList;
import java.util.List;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.controller.LifecycleStage;
/**
 *
 * @author User
 */
@UrlBinding("/strata/Bayarn Permit")
public class BayaranPermitActionBean extends BasicTabActionBean {

    @Inject
    StrataPtService ptService;
    @Inject
    PermohonanDAO permohonanDAO;

    private PermohonanPermitButir permohonanPermitButir;
    private Permohonan permohonan;
    private List<PermohonanPermitButir> senaraiPermohonanPermitButir;

    private Integer jumlahBillPermit;
    private String kadarBayaran;
    private Integer jumlahBayaran;


    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {
        senaraiPermohonanPermitButir = new ArrayList<PermohonanPermitButir>();
        Pengguna pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = permohonanDAO.findById(idPermohonan);
        HakmilikPermohonan hp = permohonan.getSenaraiHakmilik().get(0);
        senaraiPermohonanPermitButir = ptService.getSumOfPermitButirByIdHakmilikPermohonan(hp.getId());

        jumlahBillPermit = 0;
        if(senaraiPermohonanPermitButir != null){
            for(int i=0;i<senaraiPermohonanPermitButir.size();i++){
                PermohonanPermitButir permitButir = senaraiPermohonanPermitButir.get(i);
                Integer a = permitButir.getBilPermit();
                jumlahBillPermit = jumlahBillPermit + a;
            }
        }

        jumlahBayaran = jumlahBillPermit *10*21;
        kadarBayaran = "RM 10xtempoh(21)";


    }

    @DefaultHandler
    public Resolution showForm() {

       return new JSP("strata/Ruang_Udara/bayaran_permit.jsp").addParameter("tab", "true");
    }

   public Resolution simpan() {

       return new JSP("strata/Ruang_Udara/bayaran_permit.jsp").addParameter("tab", "true");
    }

    public Permohonan getPermohonan() {
        return permohonan;
    }

    public void setPermohonan(Permohonan permohonan) {
        this.permohonan = permohonan;
    }

    public PermohonanPermitButir getPermohonanPermitButir() {
        return permohonanPermitButir;
    }

    public void setPermohonanPermitButir(PermohonanPermitButir permohonanPermitButir) {
        this.permohonanPermitButir = permohonanPermitButir;
    }

    public List<PermohonanPermitButir> getSenaraiPermohonanPermitButir() {
        return senaraiPermohonanPermitButir;
    }

    public void setSenaraiPermohonanPermitButir(List<PermohonanPermitButir> senaraiPermohonanPermitButir) {
        this.senaraiPermohonanPermitButir = senaraiPermohonanPermitButir;
    }

    public Integer getJumlahBayaran() {
        return jumlahBayaran;
    }

    public void setJumlahBayaran(Integer jumlahBayaran) {
        this.jumlahBayaran = jumlahBayaran;
    }

    public Integer getJumlahBillPermit() {
        return jumlahBillPermit;
    }

    public void setJumlahBillPermit(Integer jumlahBillPermit) {
        this.jumlahBillPermit = jumlahBillPermit;
    }

    public String getKadarBayaran() {
        return kadarBayaran;
    }

    public void setKadarBayaran(String kadarBayaran) {
        this.kadarBayaran = kadarBayaran;
    }



}
