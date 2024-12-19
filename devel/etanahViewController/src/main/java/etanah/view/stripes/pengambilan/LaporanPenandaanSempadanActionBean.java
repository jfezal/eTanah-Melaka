/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package etanah.view.stripes.pengambilan;

/**
 *
 * @author massita
 */
import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.dao.HakmilikDAO;
import etanah.dao.LaporanTandaSempadanDAO;
import etanah.dao.PermohonanDAO;
import etanah.model.Hakmilik;
import etanah.model.HakmilikPermohonan;
import etanah.model.InfoAudit;
import etanah.service.common.PendudukanSementaraService;
import etanah.model.KodCawangan;
import etanah.model.LaporanTandaSempadan;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.service.PengambilanService;
import etanah.view.etanahActionBeanContext;
import java.util.Date;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.RedirectResolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.controller.LifecycleStage;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import java.text.ParseException;

@UrlBinding("/pengambilan/LaporanPenandaanSempadan")
public class LaporanPenandaanSempadanActionBean extends AbleActionBean
{
    private static Logger logger = Logger.getLogger(LaporanPenandaanSempadanActionBean.class);
    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    PengambilanService pengambilanService;
    @Inject
    HakmilikDAO hakmilikDAO;

    private LaporanTandaSempadan laporanTandaSempadan;
    private Permohonan permohonan;
    private HakmilikPermohonan hakmilikPermohonan;
    private Hakmilik hakmilik;
    private String idHakmilik;
    private char offset;
    private char piketTanda;
    private String ikatan;
    private String catatan;
    private String idPermohonan;

    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;

    @DefaultHandler
    public Resolution showForm() {
        return new JSP("pengambilan/laporanPenandaanSempadan.jsp").addParameter("popup", "true");
    }

    public Resolution refreshpage() {
        rehydrate();
        return new RedirectResolution(LaporanPenandaanSempadanActionBean.class, "locate");
    }

    @Before(stages = {LifecycleStage.BindingAndValidation})
     public void rehydrate() {
        Pengguna pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = permohonanDAO.findById(idPermohonan);

    }

    public Resolution hakmilikDetails() {
        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        hakmilik = hakmilikDAO.findById(idHakmilik);

        if (idPermohonan != null && idHakmilik != null) {
            hakmilikPermohonan= pengambilanService.findByIdHakmilikIdPermohonan(idPermohonan, idHakmilik);
            laporanTandaSempadan = pengambilanService.getLaporanTandaSempadanByidMH(hakmilikPermohonan.getId());
            if(laporanTandaSempadan != null){
                ikatan = laporanTandaSempadan.getAdaIkatanBatu();
                offset = laporanTandaSempadan.getOffsetBangunanPagar();
                piketTanda= laporanTandaSempadan.getPiketTanda();
                catatan  = laporanTandaSempadan.getCatatan();
            }
            if(ikatan == null)
                ikatan = "y";
            if(offset != 'y' && offset!='t')
                offset = 'y';
            if(piketTanda != 'y' && piketTanda!='t')
                piketTanda= 'y';
        }

        getContext().getRequest().setAttribute("piketTanda", Boolean.TRUE);
        getContext().getRequest().setAttribute("offset", Boolean.TRUE);
        return new JSP("pengambilan/laporanPenandaanSempadan.jsp").addParameter("tab", "true");
    }

    public Resolution simpan() throws ParseException {
        logger.debug("start simpan");
        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = permohonanDAO.findById(idPermohonan);
        hakmilik = hakmilikDAO.findById(idHakmilik);
        InfoAudit ia;
        if (idPermohonan != null && idHakmilik != null) {
            hakmilikPermohonan= pengambilanService.findByIdHakmilikIdPermohonan(idPermohonan, idHakmilik);
            laporanTandaSempadan = pengambilanService.getLaporanTandaSempadanByidMH(hakmilikPermohonan.getId());
            if(laporanTandaSempadan == null){
                laporanTandaSempadan = new LaporanTandaSempadan();
                laporanTandaSempadan.setPermohonan(permohonan);
                laporanTandaSempadan.setHakmilikPermohonan(hakmilikPermohonan);
                ia = new InfoAudit();
                ia.setTarikhMasuk(new java.util.Date());
                ia.setDimasukOleh(peng);
                laporanTandaSempadan.setInfoAudit(ia);
            }else{
                ia = laporanTandaSempadan.getInfoAudit();
                ia.setTarikhKemaskini(new java.util.Date());
                ia.setDiKemaskiniOleh(peng);
                laporanTandaSempadan.setInfoAudit(ia);
            }
            laporanTandaSempadan.setAdaIkatanBatu(ikatan);
            laporanTandaSempadan.setPiketTanda(piketTanda);
            laporanTandaSempadan.setOffsetBangunanPagar(offset);
            laporanTandaSempadan.setCatatan(catatan);
            pengambilanService.simpanLaporanTandaSempadan(laporanTandaSempadan);
        }


        addSimpleMessage("Maklumat telah berjaya disimpan.");
        getContext().getRequest().setAttribute("piketTanda", Boolean.TRUE);
        getContext().getRequest().setAttribute("offset", Boolean.TRUE);
        return new JSP("pengambilan/laporanPenandaanSempadan.jsp").addParameter("popup", "true");
    }


    public LaporanTandaSempadan getLaporanTandaSempadan() {
        return laporanTandaSempadan;
    }

    public void setLaporanTandaSempadan(LaporanTandaSempadan laporanTandaSempadan) {
        this.laporanTandaSempadan = laporanTandaSempadan;
    }

     public char getOffset() {
        return offset;
    }

    public void setOffset(char offset) {
        this.offset = offset;
    }

    public char getPiketTanda() {
        return piketTanda;
    }

    public void setPiketTanda(char piketTanda) {
        this.piketTanda = piketTanda;
    }

    public Permohonan getPermohonan() {
        return permohonan;
    }

    public void setPermohonan(Permohonan permohonan) {
        this.permohonan = permohonan;
    }

    public String getIdPermohonan() {
        return idPermohonan;
    }

    public void setIdPermohonan(String idPermohonan) {
        this.idPermohonan = idPermohonan;
    }

    public Hakmilik getHakmilik() {
        return hakmilik;
    }

    public void setHakmilik(Hakmilik hakmilik) {
        this.hakmilik = hakmilik;
    }

    public String getIdHakmilik() {
        return idHakmilik;
    }

    public void setIdHakmilik(String idHakmilik) {
        this.idHakmilik = idHakmilik;
    }

    public HakmilikPermohonan getHakmilikPermohonan() {
        return hakmilikPermohonan;
    }

    public void setHakmilikPermohonan(HakmilikPermohonan hakmilikPermohonan) {
        this.hakmilikPermohonan = hakmilikPermohonan;
    }

    public String getCatatan() {
        return catatan;
    }

    public void setCatatan(String catatan) {
        this.catatan = catatan;
    }

    public String getIkatan() {
        return ikatan;
    }

    public void setIkatan(String ikatan) {
        this.ikatan = ikatan;
    }




}
