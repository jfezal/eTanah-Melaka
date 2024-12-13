/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.stripes.pembangunan;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.model.InfoAudit;
import etanah.model.Pemohon;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.model.PermohonanPlotPelan;
import etanah.service.PembangunanService;
import etanah.service.common.PermohonanService;
import etanah.view.etanahActionBeanContext;
import java.util.Date;
import java.util.List;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.HttpCache;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;
import org.apache.log4j.Logger;

/**
 *
 * @author rajib
 */
@HttpCache(allow = false)
@UrlBinding("/pembangunan/maklumat_pemohonPlot")
public class MaklumatPemohonPlotActionBean extends AbleActionBean{
    
    private static final Logger logger = Logger.getLogger(MaklumatPemohonPlotActionBean.class);
    private Permohonan p;
    private Pengguna pguna;
    private String idPermohonan;
    private String kodNegeri;
    private String noPlot;
    private String[] plotTerpilihIndividu;
    private String[] plotTerpilihCompany;
    private List<Pemohon> pemohonList;
    private List<Pemohon> pemohonListIndividu;
    private List<Pemohon> pemohonListCompany;
    private List<PermohonanPlotPelan> listPlot;
    private Integer[] syerPembilangIndividu;
    private Integer[] syerPenyebutIndividu;
    private Integer[] syerPembilangCompany;
    private Integer[] syerPenyebutCompany;
    @Inject
    private etanah.Configuration conf;
    @Inject
    private PermohonanService permohonanService;
    @Inject
    private PembangunanService pembangunanServ;
    
    @DefaultHandler
    public Resolution showForm() {
        logger.info("showForm");
        getContext().getRequest().setAttribute("edit", Boolean.FALSE);
        return new JSP("pembangunan/pecahBahagian/dev_maklumat_pemohon_plot.jsp").addParameter("tab", "true");
    }
    
    public Resolution editForm() {
        logger.info("editForm");
        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        return new JSP("pembangunan/pecahBahagian/dev_maklumat_pemohon_plot.jsp").addParameter("tab", "true");
    }
    
    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {
        logger.info("---------rhydrate start.-----------");
        
        pguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        kodNegeri = conf.getProperty("kodNegeri");

        p = permohonanService.findById(idPermohonan);
        if (p != null) {
            
            pemohonList = p.getSenaraiPemohon();
            if (!pemohonList.isEmpty()) {
                pemohonListIndividu = pembangunanServ.findSenaraiPemohonIndividuByIdPermohonanAscIdPihak(idPermohonan);
                if(pemohonListIndividu.size()>0){
                    plotTerpilihIndividu = new String[pemohonListIndividu.size()];
                    syerPembilangIndividu = new Integer[pemohonListIndividu.size()];
                    syerPenyebutIndividu = new Integer[pemohonListIndividu.size()];
                    syerPembilangIndividu = setValuePembilang(syerPembilangIndividu,pemohonListIndividu);
                    syerPenyebutIndividu = setValuePenyebut(syerPenyebutIndividu,pemohonListIndividu);
                }
                pemohonListCompany = pembangunanServ.findSenaraiPemohonSyarikatByIdPermohonanAscIdPihak(idPermohonan);
                if(pemohonListCompany.size()>0){
                    plotTerpilihCompany = new String[pemohonListCompany.size()];
                    syerPembilangCompany = new Integer[pemohonListCompany.size()];
                    syerPenyebutCompany = new Integer[pemohonListCompany.size()];
                    syerPembilangCompany = setValuePembilang(syerPembilangCompany,pemohonListCompany);
                    syerPenyebutCompany = setValuePenyebut(syerPenyebutCompany,pemohonListCompany);
                    
                }
                
            }
            listPlot = pembangunanServ.findPermohonanPlotPelanPemilikanByIdPermohonan(idPermohonan);
            
            
        }
        
        logger.info("---------rehydrate finish.-----------");
    }
    
    public Resolution simpanPemohonPlot(){
        logger.info("-------------------- simpan start.-----------");
        
        pguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        p = permohonanService.findById(idPermohonan);
        
        if(pemohonListIndividu.size()>0){
            for(int ix=0;ix<pemohonListIndividu.size();ix++){
                InfoAudit ia = new InfoAudit();
                Pemohon pemohon = new Pemohon();
                pemohon = pemohonListIndividu.get(ix);
                pemohon.setDalamanNilai1(plotTerpilihIndividu[ix]);
                pemohon.setJumlahPembilang(syerPembilangIndividu[ix]);
                pemohon.setJumlahPenyebut(syerPenyebutIndividu[ix]);
                logger.info("---------noplot--------"+plotTerpilihIndividu[ix]);
                logger.info("---------syer--"+syerPembilangIndividu[ix]+"/"+syerPenyebutIndividu[ix]);
                
                ia = pemohon.getInfoAudit();
                ia.setTarikhKemaskini(new Date());
                ia.setDiKemaskiniOleh(pguna);
                pemohon.setInfoAudit(ia);
                
                pembangunanServ.simpanPemohon(pemohon);
            }
        }
        
        if(pemohonListCompany.size()>0){
            for(int iv=0;iv<pemohonListCompany.size();iv++){
                InfoAudit ia = new InfoAudit();
                Pemohon pemohon = new Pemohon();
                pemohon = pemohonListCompany.get(iv);
                pemohon.setDalamanNilai1(plotTerpilihCompany[iv]);
                pemohon.setJumlahPembilang(syerPembilangCompany[iv]);
                pemohon.setJumlahPenyebut(syerPenyebutCompany[iv]);
                logger.info("---------noplot--------"+plotTerpilihCompany[iv]);
                logger.info("---------syer--"+syerPembilangCompany[iv]+"/"+syerPenyebutCompany[iv]);
                ia = pemohon.getInfoAudit();
                ia.setTarikhKemaskini(new Date());
                ia.setDiKemaskiniOleh(pguna);
                pemohon.setInfoAudit(ia);
                pembangunanServ.simpanPemohon(pemohon);
            }
            
        }
        
        rehydrate();
        addSimpleMessage("Maklumat telah berjaya disimpan.");
        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        return new JSP("pembangunan/pecahBahagian/dev_maklumat_pemohon_plot.jsp").addParameter("tab", "true");
    }

    public List<Pemohon> getPemohonListIndividu() {
        return pemohonListIndividu;
    }

    public void setPemohonListIndividu(List<Pemohon> pemohonListIndividu) {
        this.pemohonListIndividu = pemohonListIndividu;
    }

    public List<Pemohon> getPemohonListCompany() {
        return pemohonListCompany;
    }

    public void setPemohonListCompany(List<Pemohon> pemohonListCompany) {
        this.pemohonListCompany = pemohonListCompany;
    }

    public PermohonanService getPermohonanService() {
        return permohonanService;
    }

    public void setPermohonanService(PermohonanService permohonanService) {
        this.permohonanService = permohonanService;
    }

    public PembangunanService getPembangunanServ() {
        return pembangunanServ;
    }

    public void setPembangunanServ(PembangunanService pembangunanServ) {
        this.pembangunanServ = pembangunanServ;
    }

    public Permohonan getP() {
        return p;
    }

    public void setP(Permohonan p) {
        this.p = p;
    }

    public Pengguna getPguna() {
        return pguna;
    }

    public void setPguna(Pengguna pguna) {
        this.pguna = pguna;
    }

    public String getIdPermohonan() {
        return idPermohonan;
    }

    public void setIdPermohonan(String idPermohonan) {
        this.idPermohonan = idPermohonan;
    }

    public String getKodNegeri() {
        return kodNegeri;
    }

    public void setKodNegeri(String kodNegeri) {
        this.kodNegeri = kodNegeri;
    }

    public List<Pemohon> getPemohonList() {
        return pemohonList;
    }

    public void setPemohonList(List<Pemohon> pemohonList) {
        this.pemohonList = pemohonList;
    }

    public List<PermohonanPlotPelan> getListPlot() {
        return listPlot;
    }

    public void setListPlot(List<PermohonanPlotPelan> listPlot) {
        this.listPlot = listPlot;
    }

    public String getNoPlot() {
        return noPlot;
    }

    public void setNoPlot(String noPlot) {
        this.noPlot = noPlot;
    }

    public String[] getPlotTerpilihIndividu() {
        return plotTerpilihIndividu;
    }

    public void setPlotTerpilihIndividu(String[] plotTerpilihIndividu) {
        this.plotTerpilihIndividu = plotTerpilihIndividu;
    }

    public String[] getPlotTerpilihCompany() {
        return plotTerpilihCompany;
    }

    public void setPlotTerpilihCompany(String[] plotTerpilihCompany) {
        this.plotTerpilihCompany = plotTerpilihCompany;
    }

    public Integer[] getSyerPembilangIndividu() {
        return syerPembilangIndividu;
    }

    public void setSyerPembilangIndividu(Integer[] syerPembilangIndividu) {
        this.syerPembilangIndividu = syerPembilangIndividu;
    }

    public Integer[] getSyerPenyebutIndividu() {
        return syerPenyebutIndividu;
    }

    public void setSyerPenyebutIndividu(Integer[] syerPenyebutIndividu) {
        this.syerPenyebutIndividu = syerPenyebutIndividu;
    }

    public Integer[] getSyerPembilangCompany() {
        return syerPembilangCompany;
    }

    public void setSyerPembilangCompany(Integer[] syerPembilangCompany) {
        this.syerPembilangCompany = syerPembilangCompany;
    }

    public Integer[] getSyerPenyebutCompany() {
        return syerPenyebutCompany;
    }

    public void setSyerPenyebutCompany(Integer[] syerPenyebutCompany) {
        this.syerPenyebutCompany = syerPenyebutCompany;
    }

    private Integer[] setValuePembilang(Integer[] syerPembilang, List<Pemohon> pemohonList) {
        for(int ix=0;ix<pemohonList.size();ix++){
            if(pemohonList.get(ix)!=null){
                if(pemohonList.get(ix).getJumlahPembilang()!=null){
                    syerPembilang[ix] = pemohonList.get(ix).getJumlahPembilang();
                }
            }
            
        }
        return syerPembilang;
    }

    private Integer[] setValuePenyebut(Integer[] syerPenyebut, List<Pemohon> pemohonList) {
        for(int ix=0;ix<pemohonList.size();ix++){
            if(pemohonList.get(ix)!=null){
                if(pemohonList.get(ix).getJumlahPenyebut()!=null){
                    syerPenyebut[ix] = pemohonList.get(ix).getJumlahPenyebut();
                }
            }
            
        }        
        return syerPenyebut;
    }

    
    
}
