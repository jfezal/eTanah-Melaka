/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.stripes.lelong;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.dao.PermohonanDAO;
import etanah.dao.EnkuiriDAO;
import etanah.model.Enkuiri;
import etanah.model.InfoAudit;
import etanah.model.KodCawangan;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.service.common.EnkuiriService;
import etanah.view.etanahActionBeanContext;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import java.util.List;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author mazurahayati.yusop
 */
@UrlBinding("/lelong/maklumat_16D_16E")
public class Maklumat16D16EActionBean extends AbleActionBean {

    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    EnkuiriDAO enkuiriDAO;
    @Inject
    EnkuiriService enService;
    private Permohonan permohonan;
    Enkuiri enkuiri;
    private List<Enkuiri> senaraiEnkuiri;
    private String sebabTunggakan;
    private String tujuanGadaian;
    private Integer tempohTunggakan;
    private Integer tempohRemedi;
    private BigDecimal amaunTunggakan;
    private Integer tempohGadaian;
     @Inject
    protected com.google.inject.Provider<Session> sessionProvider;

    @DefaultHandler
    public Resolution showForm() {
        return new JSP("lelong/maklumat_16G_16D.jsp").addParameter("tab", "true");
    }

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {

        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        if (idPermohonan != null) {
            permohonan = permohonanDAO.findById(idPermohonan);
            try {
                String[] tname = {"permohonan"};
                Object[] tvalue = {permohonan};
                senaraiEnkuiri = enkuiriDAO.findByEqualCriterias(tname, tvalue, null);

                enkuiri = senaraiEnkuiri.get(0);

//                Session s = sessionProvider.get();
//
//                Query q = s.createQuery("from Enkuiri e where e.permohonan.idPermohonan = :idPermohonan");
//                q.setString("idPermohonan",idPermohonan);
//                senaraiEnkuiri = q.list();
//                enkuiri = senaraiEnkuiri.get(0);


            } catch (Exception ex) {
                System.out.println(ex);
            }
        }
    }

    public Resolution simpanEnkuiri() {
        String result = null;
        Pengguna peng = (Pengguna) context.getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        InfoAudit ia = new InfoAudit();
        ia.setDimasukOleh(peng);
        ia.setTarikhMasuk(new java.util.Date());
        ia.setDiKemaskiniOleh(peng);
        ia.setTarikhKemaskini(new java.util.Date());
        KodCawangan kc = peng.getKodCawangan();

        System.out.println("permohonan :" + permohonan.getIdPermohonan());
        enkuiri.setPermohonan(permohonan);
        enkuiri.setCawangan(kc);
        enkuiri.setInfoAudit(ia);
        enService.save(enkuiri);

        addSimpleMessage("Maklumat telah berjaya disimpan.");
        return new JSP("lelong/maklumat_16G_16D.jsp").addParameter("tab", "true");
    }

    public Permohonan getPermohonan() {
        return permohonan;
    }

    public void setPermohonan(Permohonan permohonan) {
        this.permohonan = permohonan;
    }

    public Enkuiri getEnkuiri() {
        return enkuiri;
    }

    public void setEnkuiri(Enkuiri enkuiri) {
        this.enkuiri = enkuiri;
    }

    public List<Enkuiri> getSenaraiEnkuiri() {
        return senaraiEnkuiri;
    }

    public void setSenaraiEnkuiri(List<Enkuiri> senaraiEnkuiri) {
        this.senaraiEnkuiri = senaraiEnkuiri;
    }

    public String getSebabTunggakan() {
        return sebabTunggakan;
    }

    public void setSebabTunggakan(String sebabTunggakan) {
        this.sebabTunggakan = sebabTunggakan;
    }

    public String getTujuanGadaian() {
        return tujuanGadaian;
    }

    public void setTujuanGadaian(String tujuanGadaian) {
        this.tujuanGadaian = tujuanGadaian;
    }

    public Integer getTempohTunggakan() {
        return tempohTunggakan;
    }

    public void setTempohTunggakan(Integer tempohTunggakan) {
        this.tempohTunggakan = tempohTunggakan;
    }

    public BigDecimal getAmaunTunggakan() {
        return amaunTunggakan;
    }

    public void setAmaunTunggakan(BigDecimal amaunTunggakan) {
        this.amaunTunggakan = amaunTunggakan;
    }

    public Integer getTempohGadaian() {
        return tempohGadaian;
    }

    public void setTempohGadaian(Integer tempohGadaian) {
        this.tempohGadaian = tempohGadaian;
    }

    public Integer getTempohRemedi() {
        return tempohRemedi;
    }

    public void setTempohRemedi(Integer tempohRemedi) {
        this.tempohRemedi = tempohRemedi;
    }
}
