/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package etanah.view.stripes.pengambilan;
import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.dao.PermohonanDAO;
import etanah.model.Permohonan;
import etanah.service.ConsentPtdService;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;
import org.apache.commons.lang.StringUtils;


/**
 *
 * @author nordiyana
 */
@UrlBinding("/pengambilansementara")
public class PengambilanSementaraActionBean extends AbleActionBean {
@Inject
    PermohonanDAO permohonanDAO;
    @Inject
    ConsentPtdService conService;
    private Permohonan permohonan;
    private String idHakmilik;

    public Permohonan getPermohonan() {
        return permohonan;
    }

    public void setPermohonan(Permohonan permohonan) {
        this.permohonan = permohonan;
    }

    public String getIdHakmilik() {
        return idHakmilik;
    }

    public void setIdHakmilik(String idHakmilik) {
        this.idHakmilik = idHakmilik;
    }

    @DefaultHandler
    public Resolution showForm() {
        return new JSP("pengambilan/kemasukan_warta.jsp").addParameter("tab", "true");
    }

    public Resolution showForm2() {
        return new JSP("pengambilan/penyediaan_surat_tangguh_bicara.jsp").addParameter("tab", "true");
    }
    public Resolution showForm3() {
        return new JSP("pengambilan/nota_siasatan_pengambilan.jsp").addParameter("tab", "true");
    }
    public Resolution showForm4() {
        return new JSP("pengambilan/keputusan_warta.jsp").addParameter("tab", "true");
    }
    public Resolution showForm5() {
        return new JSP("pengambilan/keputusan_mesyuarat_jkkpt.jsp").addParameter("tab", "true");
    }
    public Resolution showForm6() {
        return new JSP("pengambilan/akuan_terima.jsp").addParameter("tab", "true");
    }
     public Resolution showForm7() {
        return new JSP("pengambilan/keputusan_perbicaraan.jsp").addParameter("tab", "true");
    }
     public Resolution showForm8() {
        return new JSP("pengambilan/laporanpenandaansempadan.jsp").addParameter("tab", "true");
    }
      public Resolution showForm9() {
        return new JSP("pengambilan/penetapan_rundingan.jsp").addParameter("tab", "true");
    }
       public Resolution showForm10() {
        return new JSP("pengambilan/syarat_pendudukan.jsp").addParameter("tab", "true");
    }
        public Resolution showForm11() {
        return new JSP("pengambilan/keputusan_perbicaraan_kosgantirugi.jsp").addParameter("tab", "true");
    }
    public Resolution showForm12() {
        return new JSP("pengambilan/rekod_penerimaan_bayaran_pampasan_tambahan.jsp").addParameter("tab", "true");
    }

//    public Resolution showForm3() {
//        return new JSP("daftar/paparan_dokumen_pendaftar.jsp").addParameter("tab", "true");
//    }

    public Resolution paparanMaklumatPermohonanDaftar() {
        if(permohonan != null){
            idHakmilik = permohonan.getSenaraiHakmilik().get(0).getHakmilik().getIdHakmilik();
        }
        return new JSP("daftar/paparan_maklumat_permohonan_daftar.jsp").addParameter("tab", "true");
    }

    public Resolution paparanSenaraiPerserahan(){
        return new JSP("daftar/common/senarai_perserahan.jsp").addParameter("tab", "true");
    }

    public Resolution paparanSurat(){
        return new JSP("daftar/common/paparan_surat.jsp").addParameter("tab","true");
    }

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {
        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        if(idPermohonan != null){
            permohonan = permohonanDAO.findById(idPermohonan);

            if (permohonan.getPermohonanSebelum() != null) {
                if (StringUtils.isNotBlank(permohonan.getPermohonanSebelum().getIdPermohonan())) {
                    permohonan = permohonanDAO.findById(permohonan.getPermohonanSebelum().getIdPermohonan());
                }
            }
        }
    }

    public Resolution simpanPermohonan() {
        conService.simpanPermohonan(permohonan);
        return new JSP("common/maklumat_permohonan.jsp").addParameter("tab", "true");
    }

}
