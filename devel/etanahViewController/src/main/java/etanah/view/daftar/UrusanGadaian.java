/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package etanah.view.daftar;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.dao.HakmilikUrusanDAO;
import etanah.dao.PermohonanDAO;
import etanah.model.Hakmilik;
import etanah.model.HakmilikPermohonan;
import etanah.model.HakmilikUrusan;
import etanah.model.InfoAudit;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.model.PermohonanAtasPerserahan;
import etanah.service.common.UrusanGadaianService;
import etanah.service.common.PermohonanAtasPerserahanService;
import etanah.view.etanahActionBeanContext;
import java.util.ArrayList;
import java.util.List;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.RedirectResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;
import org.apache.commons.lang.StringUtils;

/**
 *
 * @author fikri
 */
@UrlBinding("/daftar/urusan_gadaian")
public class UrusanGadaian extends AbleActionBean{
    private Permohonan permohonan;
    private List<HakmilikUrusan> hakmilikUrusanList;
    private List<Permohonan> hakmilikUrusanList2;
    private List<PermohonanAtasPerserahan> senaraiMau;
    @Inject
    UrusanGadaianService urusanGadaianService;
    @Inject
    PermohonanAtasPerserahanService permohonanAtasPerserahanService;
    @Inject
    HakmilikUrusanDAO hakmilikUrusanDAO;
    @Inject
    PermohonanDAO permohonanDAO;
    private String[] chkbox;
    private PermohonanAtasPerserahan mau;
    private Pengguna pengguna;

    public Resolution SenaraiUrusanGadaian(){
        return new JSP("common/senarai_urusan_gadaian.jsp").addParameter("tab", "true");
    }
    public Resolution saveMelepasGadaian(){
        InfoAudit ia = new InfoAudit();
        ia.setDimasukOleh(pengguna);
        ia.setTarikhMasuk(new java.util.Date());
        List<PermohonanAtasPerserahan> tmp = new ArrayList<PermohonanAtasPerserahan>();
        if(chkbox.length > 0){
            for (int i = 0; i < chkbox.length; i++) {
                boolean flag = false;
                Long id = Long.parseLong(chkbox[i]);
                //remove duplicate item
                for (PermohonanAtasPerserahan permohonanAtasPerserahan : senaraiMau) {
                    if(permohonanAtasPerserahan.getUrusan().getIdUrusan() == id){
                        //already added in permohonan. just leave it
                        flag = true;
                    }
                }
                if(flag)
                    continue;
                HakmilikUrusan hu = hakmilikUrusanDAO.findById(id);
                mau = new PermohonanAtasPerserahan();
                mau.setUrusan(hu);
                mau.setPermohonan(permohonan);
                mau.setInfoAudit(ia);
                tmp.add(mau);
            }
            if(permohonanAtasPerserahanService.save(tmp))
                addSimpleMessage("Kemasukan data berjaya.");
            else
                addSimpleError("Kemasukan data GAGAL. Sila hubungi Pentadbir Sistem.");
        }
        return new RedirectResolution(UrusanGadaian.class, "SenaraiUrusanGadaian");

    }

    public Resolution delete(){
        String id = getContext().getRequest().getParameter("id");
        permohonanAtasPerserahanService.delete(Long.parseLong(id));
        return new RedirectResolution(UrusanGadaian.class, "SenaraiUrusanGadaian");
        
    }

     @Before(stages={LifecycleStage.BindingAndValidation}, on={"!delete"})
    public void rehydrate(){
        pengguna = (Pengguna)getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
//        permohonan = ((etanahActionBeanContext)getContext()).getPermohonan();
        String idPermohonan = (String)getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = permohonanDAO.findById(idPermohonan);
        if (permohonan != null) {
            hakmilikUrusanList = new ArrayList<HakmilikUrusan>();
            List<HakmilikPermohonan> senaraihakmilik = permohonan.getSenaraiHakmilik();
            for (HakmilikPermohonan hp : senaraihakmilik) {
                if (hp == null) continue;
                Hakmilik hm = hp.getHakmilik();
                if (hm == null) continue;
                List<HakmilikUrusan> senaraiTmp = urusanGadaianService.searchHakmilikUrusanGadaian(hm.getIdHakmilik());
                for (HakmilikUrusan hu : senaraiTmp) {
                    if (hu == null) continue;
                    hakmilikUrusanList.add(hu);
                }
            }
        }

//        hakmilikUrusanList2 = urusanGadaianService.searchHakmilikUrusanGadaian();
        if(permohonan != null){
            senaraiMau = permohonan.getSenaraiPermohonanAtasPerserahan();
        }
    }


    public List<HakmilikUrusan> getHakmilikUrusanList() {
        return hakmilikUrusanList;
    }

    public void setHakmilikUrusanList(List<HakmilikUrusan> hakmilikUrusanList) {
        this.hakmilikUrusanList = hakmilikUrusanList;
    }

    public String[] getChkbox() {
        return chkbox;
    }

    public void setChkbox(String[] chkbox) {
        this.chkbox = chkbox;
    }

    public List<PermohonanAtasPerserahan> getSenaraiMau() {
        return senaraiMau;
    }

    public void setSenaraiMau(List<PermohonanAtasPerserahan> senaraiMau) {
        this.senaraiMau = senaraiMau;
    }

    /**
     * @return the hakmilikUrusanList2
     */
    public List<Permohonan> getHakmilikUrusanList2() {
        return hakmilikUrusanList2;
    }

    /**
     * @param hakmilikUrusanList2 the hakmilikUrusanList2 to set
     */
    public void setHakmilikUrusanList2(List<Permohonan> hakmilikUrusanList2) {
        this.hakmilikUrusanList2 = hakmilikUrusanList2;
    }
}
