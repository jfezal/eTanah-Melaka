package etanah.view.stripes.pelupusan;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.model.Permohonan;
import etanah.dao.PermohonanDAO;
import etanah.model.InfoAudit;
import etanah.model.PermohonanLaporanKawasan;
import etanah.model.TanahRizabPermohonan;
import etanah.model.KodRizab;
import etanah.model.Pengguna;
import etanah.service.PelupusanService;
import etanah.view.etanahActionBeanContext;
import java.util.Date;
import net.sourceforge.stripes.action.*;
import net.sourceforge.stripes.controller.LifecycleStage;
import org.apache.log4j.Logger;

@UrlBinding("/pelupusan/maklumat_penerimaan_warta")
public class MaklumatPenerimaanWartaActionBean extends AbleActionBean {

    private static final Logger LOG = Logger.getLogger(MaklumatPenerimaanWartaActionBean.class);
    @Inject
    PelupusanService pelupusanService;
    @Inject
    PermohonanDAO permohonanDAO;
    private Date tarikhTerimaWarta;
    private Date tarikhWarta;
    private String nomborWarta;
    private String idPermohonan;
    PermohonanLaporanKawasan permohonanLaporanKawasan;
    TanahRizabPermohonan trp;
    private ActionBeanContext context;

    @DefaultHandler
    public Resolution view() {
        return new JSP("pelupusan/maklumat_penerimaan_warta.jsp").addParameter("tab", "true");
    }

    public Resolution saveData() {
        LOG.info("nomborWarta: " + nomborWarta);
        Pengguna pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        Permohonan permohonan = pelupusanService.findById(idPermohonan);
        InfoAudit infoAudit = new InfoAudit();
        if (!permohonan.getKodUrusan().getKod().equals("MMRE") && !permohonan.getKodUrusan().getKod().equals("WMRE") && !permohonan.getKodUrusan().getKod().equals("BMRE")) {
            PermohonanLaporanKawasan permohonanLaporanKawasan1 = pelupusanService.findByidMohonKodRizab(idPermohonan, "14");

            if (permohonanLaporanKawasan1 != null) {
                permohonanLaporanKawasan1.setNoWarta(nomborWarta);
                permohonanLaporanKawasan1.setTarikhWarta(tarikhWarta);
                permohonanLaporanKawasan1.setTarikhTerimaWarta(tarikhTerimaWarta);
                KodRizab kodRizab = new KodRizab();
                kodRizab.setKod(14);
                permohonanLaporanKawasan1.setKodRizab(kodRizab);
                infoAudit.setDiKemaskiniOleh(pengguna);
                infoAudit.setTarikhKemaskini(new java.util.Date());
                permohonanLaporanKawasan1.setInfoAudit(infoAudit);
            } else {
                permohonanLaporanKawasan1 = new PermohonanLaporanKawasan();
                permohonanLaporanKawasan1.setNoWarta(nomborWarta);
                permohonanLaporanKawasan1.setTarikhWarta(tarikhWarta);
                permohonanLaporanKawasan1.setTarikhTerimaWarta(tarikhTerimaWarta);
                permohonanLaporanKawasan1.setKodCawangan(permohonan.getCawangan());
                permohonanLaporanKawasan1.setPermohonan(permohonan);
                infoAudit.setTarikhMasuk(new java.util.Date());
                infoAudit.setDimasukOleh(pengguna);
                permohonanLaporanKawasan1.setInfoAudit(infoAudit);
                KodRizab kodRizab = new KodRizab();
                kodRizab.setKod(14);
                permohonanLaporanKawasan1.setKodRizab(kodRizab);
            }

            pelupusanService.simpanPermohonanLaporKawasan(permohonanLaporanKawasan1);
        }
        if (permohonan.getKodUrusan().getKod().equals("MMRE") || permohonan.getKodUrusan().getKod().equals("WMRE") || permohonan.getKodUrusan().getKod().equals("BMRE")) {
            PermohonanLaporanKawasan permohonanLaporanKawasan1 = pelupusanService.findByidMohonKodRizab(idPermohonan, "3");

            if (permohonanLaporanKawasan1 != null) {
                permohonanLaporanKawasan1.setNoWarta(nomborWarta);
                permohonanLaporanKawasan1.setTarikhWarta(tarikhWarta);
                permohonanLaporanKawasan1.setTarikhTerimaWarta(tarikhTerimaWarta);
                //permohonanLaporanKawasan1.setKodRizab(trp.getRizab());
                infoAudit.setDiKemaskiniOleh(pengguna);
                infoAudit.setTarikhKemaskini(new java.util.Date());
                permohonanLaporanKawasan1.setInfoAudit(infoAudit);
            } else {
                permohonanLaporanKawasan1 = new PermohonanLaporanKawasan();

                permohonanLaporanKawasan1.setNoWarta(nomborWarta);
                permohonanLaporanKawasan1.setTarikhWarta(tarikhWarta);
                permohonanLaporanKawasan1.setTarikhTerimaWarta(tarikhTerimaWarta);
                // permohonanLaporanKawasan1.setKodRizab(trp.getRizab());
                permohonanLaporanKawasan1.setKodCawangan(permohonan.getCawangan());
                permohonanLaporanKawasan1.setPermohonan(permohonan);
                infoAudit.setTarikhMasuk(new java.util.Date());
                infoAudit.setDimasukOleh(pengguna);
                permohonanLaporanKawasan1.setInfoAudit(infoAudit);
            }

            pelupusanService.simpanPermohonanLaporKawasan(permohonanLaporanKawasan1);
        }
        addSimpleMessage("Maklumat Berjaya Disimpan");
        return new JSP("pelupusan/maklumat_penerimaan_warta.jsp").addParameter("tab", "true");

    }

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {

        retrieveData();

    }

    public void retrieveData() {

        LOG.info("Tarikh Terima Warta: " + tarikhTerimaWarta);
        LOG.info("Nombor Warta: " + nomborWarta);
        LOG.info("Tarikh Warta: " + tarikhWarta);
        LOG.info("ID Mohon: " + idPermohonan);
        // LOG.info("Kod Rizab: " + trp.getRizab());

        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        //idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        // PermohonanLaporanKawasan permohonanLaporanKawasan = pelupusanService.findByidMohonKodRizab("idPermohonan", trp.getRizab().getNama());
        Permohonan permohonan = pelupusanService.findById(idPermohonan);

        //this.setNomborWarta(permohonanLaporanKawasan.getNoWarta());
        //this.setTarikhWarta(permohonanLaporanKawasan.getTarikhWarta());
        //this.setTarikhTerimaWarta(permohonanLaporanKawasan.getTarikhTerimaWarta());
        //--------

        LOG.info("Rehydrate TerimaPamirwarta");
        // Get Permohonan Laporan Kawasan Information

        if (permohonan.getKodUrusan().getKod().equals("MMRE") || permohonan.getKodUrusan().getKod().equals("WMRE") || permohonan.getKodUrusan().getKod().equals("BMRE")) {
            // Kod Rizab Melayu : 3
            permohonanLaporanKawasan = pelupusanService.findByidMohonKodRizab(idPermohonan, "3");
        } else {
            permohonanLaporanKawasan = pelupusanService.findByidMohonKodRizab(idPermohonan, "14");
        }

        if (permohonanLaporanKawasan != null) {
            this.setNomborWarta(permohonanLaporanKawasan.getNoWarta());
            this.setTarikhTerimaWarta(permohonanLaporanKawasan.getTarikhTerimaWarta());
            this.setTarikhWarta(permohonanLaporanKawasan.getTarikhWarta());
        } else {
            if (permohonan.getKodUrusan().getKod().equals("PRMMK")) {
//                this.setNomborWarta("");
//                this.setTarikhTerimaWarta(null);
//                this.setTarikhWarta(null);
            } else if (permohonan.getKodUrusan().getKod().equals("MMRE") || permohonan.getKodUrusan().getKod().equals("WMRE") || permohonan.getKodUrusan().getKod().equals("BMRE")) {
                this.setNomborWarta("");
                this.setTarikhTerimaWarta(null);
                this.setTarikhWarta(null);
            } else {
                this.setNomborWarta("");
                this.setTarikhTerimaWarta(permohonanLaporanKawasan.getTarikhTerimaWarta());
                this.setTarikhWarta(permohonanLaporanKawasan.getTarikhWarta());
            }

        }
    }

    public Date getTarikhTerimaWarta() {
        return tarikhTerimaWarta;
    }

    public void setTarikhTerimaWarta(Date tarikhTerimaWarta) {
        this.tarikhTerimaWarta = tarikhTerimaWarta;
    }

    public Date getTarikhWarta() {
        return tarikhWarta;
    }

    public void setTarikhWarta(Date tarikhWarta) {
        this.tarikhWarta = tarikhWarta;
    }

    public String getNomborWarta() {
        return nomborWarta;
    }

    public void setNomborWarta(String nomborWarta) {
        this.nomborWarta = nomborWarta;
    }

    public MaklumatPenerimaanWartaActionBean() {
        // TODO Auto-generated constructor stub
    }

    @Override
    public ActionBeanContext getContext() {
        // TODO Auto-generated method stub
        return this.context;
    }

    @Override
    public void setContext(ActionBeanContext context) {
        this.context = context;

    }
}
