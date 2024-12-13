/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package etanah.view.stripes.hasil;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.dao.DasarTuntutanCukaiHakmilikDAO;
import etanah.dao.HakmilikDAO;
import etanah.dao.KodCaraPenghantaranDAO;
import etanah.dao.PermohonanDAO;
import etanah.model.DasarTuntutanCukaiHakmilik;
import etanah.model.DasarTuntutanNotis;
import etanah.model.Hakmilik;
import etanah.model.HakmilikPermohonan;
import etanah.model.KodCaraPenghantaran;
import etanah.model.KodNotis;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.report.ReportUtil;
import etanah.view.etanahActionBeanContext;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.StreamingResolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;
import org.hibernate.Query;
import org.hibernate.Session;
import org.apache.log4j.Logger;

/**
 *
 * @author nurfaizati
 */
@UrlBinding("/hasil/penerimaan_warta")
public class RekodWartaActionBean  extends AbleActionBean{
    
    private static final Logger LOG = Logger.getLogger(RekodWartaActionBean.class);
    private static final boolean IS_LOG_DEBUG = LOG.isDebugEnabled();

    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;
    @Inject
    HakmilikDAO hakmilikDAO;
    private Hakmilik hakmilik;
    @Inject
    PermohonanDAO permohonanDAO;
    Permohonan permohonan;
    @Inject
    DasarTuntutanCukaiHakmilikDAO dasarTuntutanCukaiHakmilikDAO;
    @Inject
    KodCaraPenghantaranDAO kodCaraPenghantaranDAO;
    @Inject
    RemisyenManager manager;
    @Inject
    private ReportUtil reportUtil;
    @Inject
    etanah.Configuration conf;


    private List<DasarTuntutanCukaiHakmilik> senaraiDasarTuntutanCukaiHakmilik;
    private List<DasarTuntutanNotis> senaraiNotis;
    private List<HakmilikPermohonan> senaraiHakmilikPermohonan;
    private List senaraiNoRujukan;

//    private boolean visible = false;
    private String noHakmilik;
    private Integer tempohTahun;
    private String kodStatus;
    private String noWarta;
    private String idHakmilik;
    private String dateHantarWarta;
    private String dateTerimaWarta;
    private String negeri;
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

     @DefaultHandler
       public Resolution showForm(){
         if("04".equals(conf.getProperty("kodNegeri")))
            negeri = "melaka";
        return new JSP("hasil/rekod_penerimaan_warta2.jsp").addParameter("tab", "true");
    }

     @Before(stages={LifecycleStage.BindingAndValidation})
    public void rehydrate(){
        LOG.info("rehydrate:start");
        String idPermohonan = (String)getContext().getRequest().getSession().getAttribute("idPermohonan");
        LOG.debug("idPermohonan :"+idPermohonan);

        if(idPermohonan != null){
            permohonan = permohonanDAO.findById(idPermohonan);
            senaraiHakmilikPermohonan = new ArrayList<HakmilikPermohonan>();
            senaraiNoRujukan = new ArrayList();
            try{
                List<HakmilikPermohonan> senaraiHakmilikPermohonanAll = permohonan.getSenaraiHakmilik();
                for (HakmilikPermohonan hp : senaraiHakmilikPermohonanAll) {
                    if(hp.getHakmilik().getAkaunCukai().getBaki().intValue() > 0)
                        senaraiHakmilikPermohonan.add(hp);
                }
                for(int i = 0; i < senaraiHakmilikPermohonan.size(); i++){
                    String[] name = {"hakmilik"};
                    Object[] value = {senaraiHakmilikPermohonan.get(i).getHakmilik()};
                    senaraiDasarTuntutanCukaiHakmilik = dasarTuntutanCukaiHakmilikDAO.findByEqualCriterias(name, value, null);
                    LOG.debug("senaraiDasarTuntutanCukaiHakmilik.size() :"+senaraiDasarTuntutanCukaiHakmilik.size());
                    senaraiNoRujukan.add(senaraiDasarTuntutanCukaiHakmilik.get(0).getNoRujukan());
                    for(DasarTuntutanCukaiHakmilik dtch : senaraiDasarTuntutanCukaiHakmilik){
                        senaraiNotis = dtch.getSenaraiNotis();
                        for(DasarTuntutanNotis dtn : senaraiNotis){
                            if(dtn.getNotis().getKod().equals("WR")){
                                noWarta = dtn.getNoRujukan();
                                if(dtn.getTarikhDihantar() != null)
                                    dateHantarWarta = sdf.format(dtn.getTarikhDihantar());
                                if(dtn.getTarikhTerima() != null)
                                    dateTerimaWarta = sdf.format(dtn.getTarikhTerima());
                            }
                        }
                    }
                }
            }catch(Exception ex){
                LOG.error("rehydrate ex: "+ex);
            }
        }
        LOG.info("rehydrate:finish");
    }

    public Resolution saveOrUpdate(){
        LOG.info("saveOrUpdate:start");
        Date now = new Date();
        Pengguna peng = (Pengguna) context.getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        etanahActionBeanContext ctx = (etanahActionBeanContext) getContext();
        KodNotis kn = new KodNotis();
        kn.setKod("WR"); // WR = kod for warta
        KodCaraPenghantaran kcp = kodCaraPenghantaranDAO.findById("WA"); // WA = kod for 'Warta', PO = kod for 'Pos', PN = kod for 'Penghantar Notis'
        String result = "";
        for(int i = 0; i < senaraiHakmilikPermohonan.size(); i++){
            String[] name = {"hakmilik"};
            Object[] value = {senaraiHakmilikPermohonan.get(i).getHakmilik()};
            senaraiDasarTuntutanCukaiHakmilik = dasarTuntutanCukaiHakmilikDAO.findByEqualCriterias(name, value, null);
            LOG.debug("senaraiDasarTuntutanCukaiHakmilik.size() :"+senaraiDasarTuntutanCukaiHakmilik.size());
            for(DasarTuntutanCukaiHakmilik dtch : senaraiDasarTuntutanCukaiHakmilik){
                senaraiNotis = dtch.getSenaraiNotis();
                List arraycheck = new ArrayList<DasarTuntutanNotis>();
                LOG.debug("senaraiNotis.size() :"+senaraiNotis.size());
                //searching notis for update according code "WR"
                for(DasarTuntutanNotis dtn : senaraiNotis){
                    LOG.debug("dtn.getIdNotis() :"+dtn.getIdNotis());
                    if(dtn.getNotis().getKod().equals("WR")){
                        LOG.debug("update > dtn.getIdNotis() :"+dtn.getIdNotis());
                        dtn.setCawangan(peng.getKodCawangan());
                        dtn.setNoRujukan(noWarta);
                        dtn.setCaraPenghantaran(kcp);
                        try {
                            if(dateHantarWarta != null)
                                dtn.setTarikhDihantar(sdf.parse(dateHantarWarta));
                            if(dateTerimaWarta != null)
                                dtn.setTarikhTerima(sdf.parse(dateTerimaWarta));
                        } catch (ParseException ex) {
                            LOG.error("saveOrUpdate ex: "+ex);
                        }
                        result = manager.saveOrUpdate(dtn, peng, ctx.getKodNegeri());
                        arraycheck.add(dtn);
                    }
                }
                //checking for arraylist to store new record in DasarTuntutanNotis Object
                if(arraycheck.size() == 0){
                    DasarTuntutanNotis dtn = new DasarTuntutanNotis();
                    LOG.debug("add > dtn.getIdNotis() :"+dtn.getIdNotis());
                    dtn.setCawangan(peng.getKodCawangan());
                    dtn.setHakmilik(dtch);
                    dtn.setNoRujukan(noWarta);
                    dtn.setNotis(kn);
                    dtn.setCaraPenghantaran(kcp);
                    dtn.setTarikhNotis(now);
                    try {
                        if(dateHantarWarta != null)
                            dtn.setTarikhDihantar(sdf.parse(dateHantarWarta));
                        if(dateTerimaWarta != null)
                            dtn.setTarikhTerima(sdf.parse(dateTerimaWarta));
                    } catch (ParseException ex) {
                        LOG.error("saveOrUpdate ex: "+ex);
                    }
                    result = manager.saveOrUpdate(dtn, peng, ctx.getKodNegeri());
                }
            }
        }
        if("simpan".equals(result)){
            addSimpleMessage("Maklumat Telah Berjaya Disimpan..");
            LOG.debug("Maklumat Telah Berjaya Disimpan..");
        }else if("update".equals(result)){
            addSimpleMessage("Maklumat Telah Berjaya Dikemaskini..");
            LOG.debug("Maklumat Telah Berjaya Dikemaskini..");
        }else{
            addSimpleError("Sistem Tergendala Sementara. Harap Maklum..");
            LOG.error("Sistem Tergendala Sementara. Harap Maklum..");
        }
        LOG.info("saveOrUpdate:finish");
        return new JSP("hasil/rekod_penerimaan_warta2.jsp").addParameter("tab", "true");
    }

    public Resolution popup(){
        hakmilik = hakmilikDAO.findById(idHakmilik);
        return new JSP("hasil/hakmilik_details.jsp").addParameter("popup", "true");
    }

    public Resolution cetakDraf() throws FileNotFoundException{
         etanahActionBeanContext ctx = (etanahActionBeanContext) getContext();
        Pengguna p = ctx.getUser();
        String idPermohonan = (String)getContext().getRequest().getSession().getAttribute("idPermohonan");
        Date now1 = new Date();
        File f = null;
        String tarikh = (new SimpleDateFormat("ddMMyyyyhhmmss")).format(now1);
        String documentPath = File.separator + "tmp" + File.separator;
        String path = tarikh + File.separator + String.valueOf(idPermohonan);
        reportUtil.generateReport("Notis8ASS130ENGBM_002.rdf",
                new String[]{"p_id_mohon"},
                new String[]{idPermohonan},
                documentPath + path, null);

        f = new File(documentPath + path);
        FileInputStream fis = new FileInputStream(f);
        return new StreamingResolution("application/pdf", fis);
    }

    public String getKodStatus() {
        return kodStatus;
    }

    public void setKodStatus(String kodStatus) {
        this.kodStatus = kodStatus;
    }

    public String getNoHakmilik() {
        return noHakmilik;
    }

    public void setNoHakmilik(String noHakmilik) {
        this.noHakmilik = noHakmilik;
    }

    public Integer getTempohTahun() {
        return tempohTahun;
    }

    public void setTempohTahun(Integer tempohTahun) {
        this.tempohTahun = tempohTahun;
    }

//    public boolean isVisible() {
//        return visible;
//    }
//
//    public void setVisible(boolean visible) {
//        this.visible = visible;
//    }

    public List<DasarTuntutanCukaiHakmilik> getSenaraiDasarTuntutanCukaiHakmilik() {
        return senaraiDasarTuntutanCukaiHakmilik;
    }

    public void setSenaraiDasarTuntutanCukaiHakmilik(List<DasarTuntutanCukaiHakmilik> senaraiDasarTuntutanCukaiHakmilik) {
        this.senaraiDasarTuntutanCukaiHakmilik = senaraiDasarTuntutanCukaiHakmilik;
    }

    public String getNoWarta() {
        return noWarta;
    }

    public void setNoWarta(String noWarta) {
        this.noWarta = noWarta;
    }

    public String getIdHakmilik() {
        return idHakmilik;
    }

    public void setIdHakmilik(String idHakmilik) {
        this.idHakmilik = idHakmilik;
    }

    public Hakmilik getHakmilik() {
        return hakmilik;
    }

    public void setHakmilik(Hakmilik hakmilik) {
        this.hakmilik = hakmilik;
    }

    public List getSenaraiNotis() {
        return senaraiNotis;
    }

    public void setSenaraiNotis(List senaraiNotis) {
        this.senaraiNotis = senaraiNotis;
    }

    public Permohonan getPermohonan() {
        return permohonan;
    }

    public void setPermohonan(Permohonan permohonan) {
        this.permohonan = permohonan;
    }

    public List<HakmilikPermohonan> getSenaraiHakmilikPermohonan() {
        return senaraiHakmilikPermohonan;
    }

    public void setSenaraiHakmilikPermohonan(List<HakmilikPermohonan> senaraiHakmilikPermohonan) {
        this.senaraiHakmilikPermohonan = senaraiHakmilikPermohonan;
    }

    public String getDateHantarWarta() {
        return dateHantarWarta;
    }

    public void setDateHantarWarta(String dateHantarWarta) {
        this.dateHantarWarta = dateHantarWarta;
    }

    public String getDateTerimaWarta() {
        return dateTerimaWarta;
    }

    public void setDateTerimaWarta(String dateTerimaWarta) {
        this.dateTerimaWarta = dateTerimaWarta;
    }

    public String getNegeri() {
        return negeri;
    }

    public void setNegeri(String negeri) {
        this.negeri = negeri;
    }

    public List getSenaraiNoRujukan() {
        return senaraiNoRujukan;
    }

    public void setSenaraiNoRujukan(List senaraiNoRujukan) {
        this.senaraiNoRujukan = senaraiNoRujukan;
    }
}
