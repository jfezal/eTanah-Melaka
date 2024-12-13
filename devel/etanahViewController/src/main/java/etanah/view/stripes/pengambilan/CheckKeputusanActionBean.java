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
import etanah.dao.FasaPermohonanDAO;
import etanah.dao.KodCawanganDAO;
import etanah.dao.PermohonanDAO;
import etanah.model.*;
import etanah.model.KodCawangan;
import etanah.model.FasaPermohonan;
import etanah.service.PengambilanService;
import etanah.view.etanahActionBeanContext;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import org.apache.log4j.Logger;
import org.hibernate.Session;

@UrlBinding("/pengambilan/checkKeputusan")
public class CheckKeputusanActionBean extends AbleActionBean {

    private static Logger logger = Logger.getLogger(CheckKeputusanActionBean.class);
    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    KodCawanganDAO kodCawanganDAO;
    @Inject
    FasaPermohonanDAO fasaPermohonanDAO;
    @Inject
    PengambilanService pengambilanService;
    @Inject
    etanah.Configuration conf;
    private Permohonan permohonan;
    private KodCawangan cawangan;
    private String idPermohonan;
    private FasaPermohonan fasaPermohonan;
    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;

    @DefaultHandler
    public Resolution showForm() {

        Pengguna pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");

        if (idPermohonan != null) {
            permohonan = permohonanDAO.findById(idPermohonan);


            fasaPermohonan = pengambilanService.findFasaPermohonan_KeputusanNIDAliran(idPermohonan, "16Perbicaraan");
            if (fasaPermohonan != null) {
                if (fasaPermohonan.getKeputusan() != null) {
                    KodKeputusan kodKeputusan = new KodKeputusan();
                    kodKeputusan = fasaPermohonan.getKeputusan();
                    System.out.println(kodKeputusan + "kodKeputusan");

                    if (kodKeputusan != null) {
                        if (fasaPermohonan.getIdAliran().equals("16Perbicaraan")) {
                            if (kodKeputusan.getKod().equals("L")) {
                                getContext().getRequest().setAttribute("form1", Boolean.TRUE);
                            }
                            if (kodKeputusan.getKod().equals("TG")) {
                                getContext().getRequest().setAttribute("form1", Boolean.TRUE);
                            }

                            if (kodKeputusan.getKod().equals("PR")) {
                                getContext().getRequest().setAttribute("form1", Boolean.TRUE);
                            }
                        }
                    }
                } else {
                    addSimpleError("Sila Buat Keputusan Terlebih Dahulu");
                    getContext().getRequest().setAttribute("form1", Boolean.FALSE);
                }
            }
        }
        return new JSP("pengambilan/negerisembilan/haklalulalangpersendirian/checkKeputusan.jsp").addParameter("tab", "true");
    }

    public KodCawangan getCawangan() {
        return cawangan;
    }

    public void setCawangan(KodCawangan cawangan) {
        this.cawangan = cawangan;
    }

    public String getIdPermohonan() {
        return idPermohonan;
    }

    public void setIdPermohonan(String idPermohonan) {
        this.idPermohonan = idPermohonan;
    }

    public PengambilanService getPengambilanService() {
        return pengambilanService;
    }

    public void setPengambilanService(PengambilanService pengambilanService) {
        this.pengambilanService = pengambilanService;
    }

    public Permohonan getPermohonan() {
        return permohonan;
    }

    public void setPermohonan(Permohonan permohonan) {
        this.permohonan = permohonan;
    }

    public FasaPermohonan getFasaPermohonan() {
        return fasaPermohonan;
    }

    public void setFasaPermohonan(FasaPermohonan fasaPermohonan) {
        this.fasaPermohonan = fasaPermohonan;
    }
}








