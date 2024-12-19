
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.stripes.pelupusan;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.dao.KodStatusPermohonanDAO;
import etanah.dao.PermohonanDAO;
import etanah.model.Hakmilik;
import etanah.model.HakmilikPermohonan;
import etanah.model.KodStatusPermohonan;
import etanah.model.Pemohon;
import etanah.model.Permit;
import etanah.model.PermitItem;
import etanah.model.PermitSahLaku;
import etanah.model.Permohonan;
import etanah.model.Pihak;
import etanah.service.common.HakmilikService;
import etanah.service.PelupusanService;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;
import org.apache.log4j.Logger;

/**
 *
 * @author Rohan
 */
@UrlBinding("/pelupusan/perihal_tanah")
public class PerihalTanahActionBean extends AbleActionBean {

    private static final Logger LOG = Logger.getLogger(PerihalTanahActionBean.class);
    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    PelupusanService pelupusanService;
    @Inject
    KodStatusPermohonanDAO kodStatusPermohonanDAO;
    @Inject
    HakmilikService hakmilikService;
    @Inject
    etanah.Configuration conf;
    private Permohonan permohonan;
    private Permohonan p;
    private List<Hakmilik> hakmilikList = new ArrayList<Hakmilik>();
    private List<HakmilikPermohonan> hakmilikPermohonanList = new ArrayList<HakmilikPermohonan>();
    private List<KodStatusPermohonan> kodStatusPermohonan;
    private KodStatusPermohonan kodStatusPermohonan1;
    private HakmilikPermohonan hakmilikPermohonan;
    private String nolot;
    private Pemohon pemohon;
    private Pihak pihak;
    String idPermohonan;
    private String recordCount;
    private String kodp;
    private String kodStatusPermohonanname;
    List<String> kodList = new ArrayList<String>();
    private List<HakmilikPermohonan> hakmilikPermohonanList1 = new Vector<HakmilikPermohonan>();
    private List<Pemohon> pemohonList = new ArrayList<Pemohon>();
    private Permit permit;
    private PermitItem permitItem;
    private PermitSahLaku permitSahLaku;
    private Long idPermit;
    private Long idPermitItem;

    @DefaultHandler
    public Resolution showForm() {
        return new JSP("pelupusan/perihal_tanah.jsp").addParameter("tab", "true");
    }

    public Resolution showForPerihal() {
        String idMohon = getContext().getRequest().getParameter("idMohon");
        LOG.info("Id Permohonan : " + idMohon);
        permohonan = permohonanDAO.findById(idMohon);

        if (permohonan != null) {
//        String[] tname = {"permohonan"} ;
//        Object[] tvalue = {permohonan} ;

            pemohonList = pelupusanService.findPemohonByIdPermohonan(idMohon);
            //permitItemList = pelupusanService.findPermitItemByIdPermit(idPermitItem);melaka

            permit = pelupusanService.findPermitByIdPermohonan(idMohon);
            permitItem = pelupusanService.findPermitItemByIdPermit(permit.getIdPermit());
            permitSahLaku = pelupusanService.findPermitSahLakuByIdMohon(idMohon);
            idPermit = permit.getIdPermit();
            idPermitItem = permitItem.getIdPermitItem();
            if (permit != null || permitItem != null || permitSahLaku != null) {
                LOG.info("--------------idPermit " + permit.getIdPermit());
                LOG.info("--------------tarikhMula " + permitSahLaku.getTarikhPermitMula());
                LOG.info("--------------tarikhAkhir " + permitSahLaku.getTarikhPermitTamat());
            }

        }
        return new JSP("pelupusan/common/maklumat_lesen.jsp").addParameter("popup", "true");
    }

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        p = pelupusanService.findById(idPermohonan);
        hakmilikPermohonan = pelupusanService.findByIdPermohonan(idPermohonan);
        if (hakmilikPermohonan != null) {
            nolot = hakmilikPermohonan.getNoLot();
        }
        
        if (nolot != null) {
            hakmilikList = pelupusanService.findHakmilikByLot(nolot);
        }


        for (Hakmilik hm : hakmilikList) {
            hakmilikPermohonanList = pelupusanService.getHakmilikPermohonanListByIdHakmilik(hm.getIdHakmilik());
            if (hakmilikPermohonanList != null) {
                for (HakmilikPermohonan hp : hakmilikPermohonanList) {
                    String kodp = hp.getPermohonan().getStatus();
                    LOG.info("kodp : " + kodp);
                    kodStatusPermohonan1 = kodStatusPermohonanDAO.findById(kodp);
                    if (kodStatusPermohonan1 != null) {
                        kodStatusPermohonanname = kodStatusPermohonan1.getNama();
                        kodList.add(kodStatusPermohonanname);
                    }

                    hakmilikPermohonanList1.add(hp);
                }

            }
        }

    }

    public HakmilikPermohonan getHakmilikPermohonan() {
        return hakmilikPermohonan;
    }

    public void setHakmilikPermohonan(HakmilikPermohonan hakmilikPermohonan) {
        this.hakmilikPermohonan = hakmilikPermohonan;
    }

    public List<HakmilikPermohonan> getHakmilikPermohonanList() {
        return hakmilikPermohonanList;
    }

    public void setHakmilikPermohonanList(List<HakmilikPermohonan> hakmilikPermohonanList) {
        this.hakmilikPermohonanList = hakmilikPermohonanList;
    }

    public Permohonan getP() {
        return p;
    }

    public void setP(Permohonan p) {
        this.p = p;
    }

    public Permohonan getPermohonan() {
        return permohonan;
    }

    public void setPermohonan(Permohonan permohonan) {
        this.permohonan = permohonan;
    }

    public String getNolot() {
        return nolot;
    }

    public void setNolot(String nolot) {
        this.nolot = nolot;
    }

    public String getIdPermohonan() {
        return idPermohonan;
    }

    public void setIdPermohonan(String idPermohonan) {
        this.idPermohonan = idPermohonan;
    }

    public List<KodStatusPermohonan> getKodStatusPermohonan() {
        return kodStatusPermohonan;
    }

    public void setKodStatusPermohonan(List<KodStatusPermohonan> kodStatusPermohonan) {
        this.kodStatusPermohonan = kodStatusPermohonan;
    }

    public String getKodp() {
        return kodp;
    }

    public void setKodp(String kodp) {
        this.kodp = kodp;
    }

    public Pemohon getPemohon() {
        return pemohon;
    }

    public void setPemohon(Pemohon pemohon) {
        this.pemohon = pemohon;
    }

    public Pihak getPihak() {
        return pihak;
    }

    public void setPihak(Pihak pihak) {
        this.pihak = pihak;
    }

    public String getRecordCount() {
        return recordCount;
    }

    public void setRecordCount(String recordCount) {
        this.recordCount = recordCount;
    }

    public String getKodStatusPermohonanname() {
        return kodStatusPermohonanname;
    }

    public void setKodStatusPermohonanname(String kodStatusPermohonanname) {
        this.kodStatusPermohonanname = kodStatusPermohonanname;
    }

    public KodStatusPermohonan getKodStatusPermohonan1() {
        return kodStatusPermohonan1;
    }

    public void setKodStatusPermohonan1(KodStatusPermohonan kodStatusPermohonan1) {
        this.kodStatusPermohonan1 = kodStatusPermohonan1;
    }

    public List<String> getKodList() {
        return kodList;
    }

    public void setKodList(List<String> kodList) {
        this.kodList = kodList;
    }

    public List<Hakmilik> getHakmilikList() {
        return hakmilikList;
    }

    public void setHakmilikList(List<Hakmilik> hakmilikList) {
        this.hakmilikList = hakmilikList;
    }

    public List<HakmilikPermohonan> getHakmilikPermohonanList1() {
        return hakmilikPermohonanList1;
    }

    public void setHakmilikPermohonanList1(List<HakmilikPermohonan> hakmilikPermohonanList1) {
        this.hakmilikPermohonanList1 = hakmilikPermohonanList1;
    }

    public Long getIdPermit() {
        return idPermit;
    }

    public void setIdPermit(Long idPermit) {
        this.idPermit = idPermit;
    }

    public Long getIdPermitItem() {
        return idPermitItem;
    }

    public void setIdPermitItem(Long idPermitItem) {
        this.idPermitItem = idPermitItem;
    }

    public Permit getPermit() {
        return permit;
    }

    public void setPermit(Permit permit) {
        this.permit = permit;
    }

    public PermitItem getPermitItem() {
        return permitItem;
    }

    public void setPermitItem(PermitItem permitItem) {
        this.permitItem = permitItem;
    }

    public PermitSahLaku getPermitSahLaku() {
        return permitSahLaku;
    }

    public void setPermitSahLaku(PermitSahLaku permitSahLaku) {
        this.permitSahLaku = permitSahLaku;
    }

    public List<Pemohon> getPemohonList() {
        return pemohonList;
    }

    public void setPemohonList(List<Pemohon> pemohonList) {
        this.pemohonList = pemohonList;
    }
}
