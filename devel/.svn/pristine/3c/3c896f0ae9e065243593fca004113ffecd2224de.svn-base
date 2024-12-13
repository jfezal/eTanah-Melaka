/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package etanah.view.stripes.hasil;

import etanah.dao.*;
import able.stripes.*;
import com.google.inject.Inject;
import etanah.model.*;
import etanah.view.etanahActionBeanContext;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;
import net.sourceforge.stripes.action.*;
import net.sourceforge.stripes.controller.LifecycleStage;

/**
 *
 * @author abdul.hakim
 */

@UrlBinding("/hasil/maklumat_pemohon_ansuran")
public class MaklumatPemohonAnsuranActionBean extends AbleActionBean{

    private static final Logger LOG = Logger.getLogger(MaklumatPemohonAnsuranActionBean.class);
    private static final boolean IS_LOG_DEBUG = LOG.isDebugEnabled();

    private Permohonan permohonan;
    private HakmilikPermohonan hakmilikPermohonan;
    private PermohonanPihak permohonanPihak;

    private String kerja;
    private String idPermohonan;
    private double monthly;
    private double partner;
    private double total;
    private boolean simpanBtn = false;

    private PermohonanPihakDAO permohonanPihakDAO;
    private PermohonanDAO permohonanDAO;
    private HakmilikPermohonanDAO hakmilikPermohonanDAO;
    private HakmilikPihakBerkepentinganDAO hakmilikPermohonanBerkepentinganDAO;
    private KodJenisPihakBerkepentinganDAO kodJenisPihakBerkepentinganDAO;
    private List<PermohonanPihak> senaraiPermohonanPihak;
    private List<HakmilikPermohonan> senaraiHakmilikPermohonan;

    @Inject
    RemisyenManager manager;
    
    @Inject
    public MaklumatPemohonAnsuranActionBean(PermohonanPihakDAO permohonanPihakDAO, PermohonanDAO permohonanDAO,
                                            HakmilikPermohonanDAO hakmilikPermohonanDAO,
                                            HakmilikPihakBerkepentinganDAO hakmilikPermohonanBerkepentinganDAO,
                                            KodJenisPihakBerkepentinganDAO kodJenisPihakBerkepentinganDAO){
        this.permohonanPihakDAO = permohonanPihakDAO;
        this.permohonanDAO = permohonanDAO;
        this.hakmilikPermohonanDAO = hakmilikPermohonanDAO;
        this.hakmilikPermohonanBerkepentinganDAO = hakmilikPermohonanBerkepentinganDAO;
        this.kodJenisPihakBerkepentinganDAO = kodJenisPihakBerkepentinganDAO;
    }

    @DefaultHandler
    public Resolution showForm() {
        return new JSP("hasil/maklumat_pemohon_ansuran.jsp").addParameter("tab", "true");
    }


    public Resolution showForm2() {
        simpanBtn = true;
        return new JSP("hasil/maklumat_pemohon_ansuran.jsp").addParameter("tab", "true");
    }

    @Before(stages={LifecycleStage.BindingAndValidation})
    public void rehydrate() {
        LOG.info("rehydrate:start");
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = permohonanDAO.findById(idPermohonan);

        senaraiPermohonanPihak = new ArrayList<PermohonanPihak>();
        senaraiHakmilikPermohonan = new ArrayList<HakmilikPermohonan>();
        permohonanPihak = new PermohonanPihak();
        Long a = null;

        String[] n1 = {"permohonan"};
        Object[] v1 = {getPermohonan()};
        senaraiPermohonanPihak = permohonanPihakDAO.findByEqualCriterias(n1, v1, null);

        if (senaraiPermohonanPihak.size() != 0) {
            for (PermohonanPihak pp : senaraiPermohonanPihak) {
                a = pp.getIdPermohonanPihak();
            }

            permohonanPihak = permohonanPihakDAO.findById(a);
            if (permohonanPihak.getPendapatanLain() != null) {
                total = permohonanPihak.getPendapatan().doubleValue() + permohonanPihak.getPendapatanLain().doubleValue();
            }
        } else {
            List<HakmilikPihakBerkepentingan> hpb = new ArrayList<HakmilikPihakBerkepentingan>();
            String[] n2 = {"permohonan"};
            Object[] v2 = {getPermohonan()};
            senaraiHakmilikPermohonan = hakmilikPermohonanDAO.findByEqualCriterias(n2, v2, null);

            for (HakmilikPermohonan hp : senaraiHakmilikPermohonan) {
                String[] n3 = {"hakmilik"};
                Object[] v3 = {hp.getHakmilik()};
                hpb = hakmilikPermohonanBerkepentinganDAO.findByEqualCriterias(n3, v3, null);
            }
            for (HakmilikPihakBerkepentingan pihak : hpb) {
                permohonanPihak = new PermohonanPihak();

                etanahActionBeanContext ctx = new etanahActionBeanContext();
                ctx = (etanahActionBeanContext) getContext();
                Pengguna pengguna = ctx.getUser();
                permohonanPihak.setPermohonan(permohonan);
                permohonanPihak.setPihak(pihak.getPihak());
                permohonanPihak.setJenis(kodJenisPihakBerkepentinganDAO.findById("PM"));
                permohonanPihak.setSyerPembilang(1);
                permohonanPihak.setSyerPenyebut(1);
                permohonanPihak.setPendapatan(BigDecimal.ZERO);
                permohonanPihak.setPendapatanLain(BigDecimal.ZERO);
                permohonanPihak.setCawangan(pengguna.getKodCawangan());

                manager.save(permohonanPihak, pengguna);
            }
        }
    }

    public Resolution save(){
        etanahActionBeanContext ctx = new etanahActionBeanContext();
        ctx = (etanahActionBeanContext) getContext();

        Pengguna pengguna = ctx.getUser();
        permohonanPihak.setPekerjaan(permohonanPihak.getPekerjaan());
        permohonanPihak.setPendapatan(permohonanPihak.getPendapatan());
        permohonanPihak.setPendapatanLain(permohonanPihak.getPendapatanLain());

        manager.update(permohonanPihak, pengguna);
        addSimpleMessage("Maklumat Telah Berjaya Disimpan");
        return new JSP("hasil/maklumat_pemohon_ansuran.jsp").addParameter("tab", "true");
    }

    public HakmilikPermohonan getHakmilikPermohonan() {
        return hakmilikPermohonan;
    }

    public void setHakmilikPermohonan(HakmilikPermohonan hakmilikPermohonan) {
        this.hakmilikPermohonan = hakmilikPermohonan;
    }

    public Permohonan getPermohonan() {
        return permohonan;
    }

    public void setPermohonan(Permohonan permohonan) {
        this.permohonan = permohonan;
    }

    public String getKerja() {
        return kerja;
    }

    public void setKerja(String kerja) {
        this.kerja = kerja;
    }

    public double getMonthly() {
        return monthly;
    }

    public void setMonthly(double monthly) {
        this.monthly = monthly;
    }

    public double getPartner() {
        return partner;
    }

    public void setPartner(double partner) {
        this.partner = partner;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public PermohonanPihak getPermohonanPihak() {
        return permohonanPihak;
    }

    public void setPermohonanPihak(PermohonanPihak permohonanPihak) {
        this.permohonanPihak = permohonanPihak;
    }

    public String getIdPermohonan() {
        return idPermohonan;
    }

    public void setIdPermohonan(String idPermohonan) {
        this.idPermohonan = idPermohonan;
    }

    public List<PermohonanPihak> getSenaraiPermohonanPihak() {
        return senaraiPermohonanPihak;
    }

    public void setSenaraiPermohonanPihak(List<PermohonanPihak> senaraiPermohonanPihak) {
        this.senaraiPermohonanPihak = senaraiPermohonanPihak;
    }

    public List<HakmilikPermohonan> getSenaraiHakmilikPermohonan() {
        return senaraiHakmilikPermohonan;
    }

    public void setSenaraiHakmilikPermohonan(List<HakmilikPermohonan> senaraiHakmilikPermohonan) {
        this.senaraiHakmilikPermohonan = senaraiHakmilikPermohonan;
    }

    public boolean isSimpanBtn() {
        return simpanBtn;
    }

    public void setSimpanBtn(boolean simpanBtn) {
        this.simpanBtn = simpanBtn;
    }
}
