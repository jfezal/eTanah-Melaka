/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.stripes.lelong;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.dao.EnkuiriDAO;
import etanah.dao.PermohonanDAO;
import etanah.model.Enkuiri;
import etanah.model.FasaPermohonan;
import etanah.model.HakmilikPermohonan;
import etanah.model.InfoAudit;
import etanah.model.KodCawangan;
import etanah.model.KodStatusEnkuiri;
import etanah.model.Lelongan;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.service.common.EnkuiriService;
import etanah.service.common.LelongService;
import etanah.view.etanahActionBeanContext;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
 * @author mdizzat.mashrom
 */
@UrlBinding("/lelong/keputusan_rekod_penghantaran")
public class KeputusanRekodPenghantaranActionBean extends AbleActionBean {

    private static final Logger LOG = Logger.getLogger(KeputusanRekodPenghantaranActionBean.class);
    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    LelongService lelongService;
    @Inject
    EnkuiriService enService;
    @Inject
    EnkuiriDAO enkuiriDAO;
    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;
    private String idPermohonan;
    private Permohonan permohonan;
    private Enkuiri enkuiri;
    private List<Enkuiri> senaraiEnkuiri2;
    private List<String> idHakmilik = new ArrayList<String>();
    private List<String> idHakmilik2 = new ArrayList<String>();
    private List<Enkuiri> senaraiEnkuiri;
    private List<Lelongan> senaraiLelongan1;
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm aaa");
    SimpleDateFormat sdf1 = new SimpleDateFormat("dd/MM/yyyy");
    private String tarikhEnkuiri;
    private String tempat;
    private String status;
    private String perkara;
    private String jam1;
    private String minit1;
    private String ampm1;
    private List<FasaPermohonan> senaraifasamohon;
    private List<Enkuiri> senaraiEnkuiri3;

    @DefaultHandler
    public Resolution showFormA() {
//        rehydrate();
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        if (idPermohonan != null) {
            permohonan = permohonanDAO.findById(idPermohonan);

            senaraifasamohon = lelongService.getRekodKpsn(idPermohonan);
            FasaPermohonan ff = senaraifasamohon.get(0);

            if (ff.getKeputusan() == null) {
                getContext().getRequest().setAttribute("error", Boolean.FALSE);
                addSimpleError("Sila Masukkan Keputusan Terlebih Dahulu Di Tab Keputusan");
                return new JSP("lelong/rekod_penghantaran_enkuiri.jsp").addParameter("tab", "true");
            } else {
                if (ff.getKeputusan().getKod().equals("SM")) {
                    getContext().getRequest().setAttribute("error", Boolean.FALSE);
                    addSimpleMessage("Keputusan Disemak.Sila Klik Butang Selesai...");
                    return new JSP("lelong/rekod_penghantaran_enkuiri.jsp").addParameter("tab", "true");
                }
                if (ff.getKeputusan().getKod().equals("NG")) {
                    getContext().getRequest().setAttribute("error", Boolean.TRUE);
                    return new JSP("lelong/rekod_penghantaran_enkuiri.jsp").addParameter("tab", "true");
                }
            }
        }
        return new JSP("lelong/rekod_penghantaran_enkuiri.jsp").addParameter("tab", "true");
    }

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");

        if (idPermohonan != null) {
            permohonan = permohonanDAO.findById(idPermohonan);

            String[] tname = {"permohonan"};
            Object[] tvalue = {permohonan};
            senaraiEnkuiri3 = enkuiriDAO.findByEqualCriterias(tname, tvalue, null);

            senaraiEnkuiri2 = lelongService.getALLEnkuri(permohonan.getCawangan().getKod());
            LOG.info("senaraiEnkuiri2.size : " + senaraiEnkuiri2.size());
            for (Enkuiri ee : senaraiEnkuiri2) {
                List<HakmilikPermohonan> listHakmilikPermohonan = lelongService.getPermohonanByIdmohon(ee.getPermohonan().getIdPermohonan());
                HakmilikPermohonan kk = listHakmilikPermohonan.get(0);
                LOG.info("idHakmilik : " + kk.getHakmilik().getIdHakmilik());
                idHakmilik.add(kk.getHakmilik().getIdHakmilik());
            }

            Session ss = sessionProvider.get();
            Query qq = ss.createQuery("SELECT e FROM etanah.model.Enkuiri e where e.permohonan.idPermohonan = :idPermohonan order by e.idEnkuiri asc");
            qq.setString("idPermohonan", idPermohonan);
            senaraiEnkuiri = qq.list();

            senaraiLelongan1 = lelongService.getALLLelongan(permohonan.getCawangan().getKod());
            LOG.info("senaraiLelongan1.size : " + senaraiLelongan1.size());
            for (Lelongan ee : senaraiLelongan1) {
                List<HakmilikPermohonan> listHakmilikPermohonan = lelongService.getPermohonanByIdmohon(ee.getEnkuiri().getPermohonan().getIdPermohonan());
                HakmilikPermohonan kk = listHakmilikPermohonan.get(0);
                LOG.info("idHakmilik : " + kk.getHakmilik().getIdHakmilik());
                idHakmilik2.add(kk.getHakmilik().getIdHakmilik());
            }

            LOG.debug("senaraiEnkuiri size :" + senaraiEnkuiri.size());
            enkuiri = senaraiEnkuiri.get(senaraiEnkuiri.size() - 1);

            tarikhEnkuiri = sdf.format(enkuiri.getTarikhEnkuiri());
            tempat = new String(enkuiri.getTempat());

            if (!StringUtils.isEmpty(enkuiri.getPerkara())) {
                perkara = new String(enkuiri.getPerkara());

            }
            status = enkuiri.getStatus().getKod();
            senaraiEnkuiri.remove(senaraiEnkuiri.size() - 1);

        }

        try {
            if (enkuiri.getTarikhEnkuiri() != null) {
                tarikhEnkuiri = sdf.format(enkuiri.getTarikhEnkuiri()).substring(0, 10);
                jam1 = sdf.format(enkuiri.getTarikhEnkuiri()).substring(11, 13);
                minit1 = sdf.format(enkuiri.getTarikhEnkuiri()).substring(14, 16);
                ampm1 = sdf.format(enkuiri.getTarikhEnkuiri()).substring(17, 19);
            }
        } catch (Exception ex) {
            LOG.error(ex);
        }
    }

    public Resolution simpanEnkuiri() {

        Pengguna peng = (Pengguna) context.getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        InfoAudit ia = new InfoAudit();
        ia.setDimasukOleh(peng);
        ia.setTarikhMasuk(new java.util.Date());
        ia.setDiKemaskiniOleh(peng);
        ia.setTarikhKemaskini(new java.util.Date());
        String tujuanGadaian = null;
        for (Enkuiri ee : senaraiEnkuiri3) {
            tujuanGadaian = ee.getTujuanGadaian();
            if (ee.getStatus().getKod().equals("AK")) {
                KodStatusEnkuiri kse1 = new KodStatusEnkuiri();
                kse1.setKod("TG");
                ee.setStatus(kse1);
                enService.save(ee);
            }
        }


        Enkuiri enk = new Enkuiri();

        LOG.info("permohonan :" + permohonan.getIdPermohonan());
        enk.setBilanganKes(1);
        enk.setPermohonan(permohonan);
        KodCawangan kc = peng.getKodCawangan();
        enk.setCawangan(kc);
        enk.setInfoAudit(ia);

        tarikhEnkuiri = tarikhEnkuiri + " " + jam1 + ":" + " " + minit1 + " " + ampm1;
        LOG.debug("tarikhEnkuiri :" + tarikhEnkuiri);

        try {
            enk.setTarikhEnkuiri(sdf.parse(tarikhEnkuiri));
        } catch (Exception ex) {
            LOG.error(ex);
        }

        enk.setTempat(tempat);
        enk.setPerkara(perkara);

        KodStatusEnkuiri kse = new KodStatusEnkuiri();
        kse.setKod("AK");
        enk.setStatus(kse);
        enk.setTujuanGadaian(tujuanGadaian);

        enService.save(enk);
        LOG.info("idEnkuiri : " + enk.getIdEnkuiri());
        tarikhEnkuiri = sdf.format(enk.getTarikhEnkuiri()).substring(0, 10);
        rehydrate();
        showFormA();
        addSimpleMessage("Maklumat telah berjaya disimpan.");
        return new JSP("lelong/rekod_penghantaran_enkuiri.jsp").addParameter("tab", "true");
    }

    public Enkuiri getEnkuiri() {
        return enkuiri;
    }

    public void setEnkuiri(Enkuiri enkuiri) {
        this.enkuiri = enkuiri;
    }

    public String getIdPermohonan() {
        return idPermohonan;
    }

    public void setIdPermohonan(String idPermohonan) {
        this.idPermohonan = idPermohonan;
    }

    public Permohonan getPermohonan() {
        return permohonan;
    }

    public void setPermohonan(Permohonan permohonan) {
        this.permohonan = permohonan;
    }

    public String getAmpm1() {
        return ampm1;
    }

    public void setAmpm1(String ampm1) {
        this.ampm1 = ampm1;
    }

    public List<String> getIdHakmilik() {
        return idHakmilik;
    }

    public void setIdHakmilik(List<String> idHakmilik) {
        this.idHakmilik = idHakmilik;
    }

    public List<String> getIdHakmilik2() {
        return idHakmilik2;
    }

    public void setIdHakmilik2(List<String> idHakmilik2) {
        this.idHakmilik2 = idHakmilik2;
    }

    public String getJam1() {
        return jam1;
    }

    public void setJam1(String jam1) {
        this.jam1 = jam1;
    }

    public String getMinit1() {
        return minit1;
    }

    public void setMinit1(String minit1) {
        this.minit1 = minit1;
    }

    public String getPerkara() {
        return perkara;
    }

    public void setPerkara(String perkara) {
        this.perkara = perkara;
    }

    public List<Enkuiri> getSenaraiEnkuiri() {
        return senaraiEnkuiri;
    }

    public void setSenaraiEnkuiri(List<Enkuiri> senaraiEnkuiri) {
        this.senaraiEnkuiri = senaraiEnkuiri;
    }

    public List<Enkuiri> getSenaraiEnkuiri2() {
        return senaraiEnkuiri2;
    }

    public void setSenaraiEnkuiri2(List<Enkuiri> senaraiEnkuiri2) {
        this.senaraiEnkuiri2 = senaraiEnkuiri2;
    }

    public List<Lelongan> getSenaraiLelongan1() {
        return senaraiLelongan1;
    }

    public void setSenaraiLelongan1(List<Lelongan> senaraiLelongan1) {
        this.senaraiLelongan1 = senaraiLelongan1;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTarikhEnkuiri() {
        return tarikhEnkuiri;
    }

    public void setTarikhEnkuiri(String tarikhEnkuiri) {
        this.tarikhEnkuiri = tarikhEnkuiri;
    }

    public String getTempat() {
        return tempat;
    }

    public void setTempat(String tempat) {
        this.tempat = tempat;
    }

    public List<FasaPermohonan> getSenaraifasamohon() {
        return senaraifasamohon;
    }

    public void setSenaraifasamohon(List<FasaPermohonan> senaraifasamohon) {
        this.senaraifasamohon = senaraifasamohon;
    }

    public List<Enkuiri> getSenaraiEnkuiri3() {
        return senaraiEnkuiri3;
    }

    public void setSenaraiEnkuiri3(List<Enkuiri> senaraiEnkuiri3) {
        this.senaraiEnkuiri3 = senaraiEnkuiri3;
    }
}
