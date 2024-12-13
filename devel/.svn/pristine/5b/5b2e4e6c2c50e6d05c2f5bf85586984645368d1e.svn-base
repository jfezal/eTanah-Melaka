/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.utility;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.dao.PermohonanDAO;
import etanah.model.Dokumen;
import etanah.model.InfoAudit;
import etanah.model.KodUrusan;
import etanah.model.Permohonan;
import etanah.model.gis.PelanGIS;
import etanah.service.RegService;
import etanah.service.common.DokumenService;
import etanah.util.FileUtil;
import etanah.view.stripes.pelupusan.LaporanTanah4ActionBean;
import etanah.view.stripes.pelupusan.disClass.DisLaporanTanahService;
import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ErrorResolution;
import net.sourceforge.stripes.action.FileBean;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.HttpCache;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import org.apache.log4j.Logger;
import org.springframework.util.StringUtils;

/**
 *
 * @author afham
 */
@HttpCache(allow = false)
@UrlBinding("/utiliti/carian_pelan")
public class CarianPelanActionBean extends AbleActionBean {

    private Permohonan permohonan;
    private String idPermohonan = new String();
    @Inject
    private PermohonanDAO permohonanDAO;
    @Inject
    DisLaporanTanahService disLaporanTanahService;
    @Inject
    RegService regService;
    @Inject
    DokumenService dokumenService;
    private List<PelanGIS> listPelanGIS;
    private String kodNegeri;
    private String idDokumenB1;
    private String idDokumenB2;
    private final static org.apache.log4j.Logger LOG = org.apache.log4j.Logger.getLogger(CarianPelanActionBean.class);

    @DefaultHandler
    public Resolution showForm() {
        return new JSP("/utiliti/carian_pelan.jsp");
    }

    public Resolution carianPelan() throws Exception {
        idPermohonan = getContext().getRequest().getParameter("idPermohonan");
        kodNegeri = disLaporanTanahService.getConf().getProperty("kodNegeri");
        if (idPermohonan != null) {
            permohonan = new Permohonan();
            permohonan = disLaporanTanahService.getPermohonanDAO().findById(StringUtils.trimWhitespace(idPermohonan));
            if (permohonan != null) {
                listPelanGIS = new ArrayList<PelanGIS>();
                listPelanGIS = disLaporanTanahService.getPelupusanService().findListPelanGISPKByIdPermohonan(permohonan.getIdPermohonan());
                if (listPelanGIS.isEmpty()) {
                    addSimpleError("Tiada pelan untuk ID Permohonan ini");
                } else {
                    addSimpleMessage("Pelan dijumpai");
                    for (PelanGIS pelan : listPelanGIS) {
                        if (pelan.getJenisPelan().equals("B1") || pelan.getJenisPelan().equals("B2")) {
                            String jenisPelan = pelan.getJenisPelan();
                            if (jenisPelan.equals("B1")) {
                                if(kodNegeri.equals("04")){
                                    idDokumenB1 = "DIS_BorangB1eMLK.rdf" ;
                                }else{
                                    idDokumenB1 = "DIS_BorangB1eNS.rdf" ;
                                }
                                LOG.info("Dokumen B1:" + idDokumenB1);
                            } else if (jenisPelan.equals("B2")) {
                                if(kodNegeri.equals("04")){
                                    idDokumenB2 = "DIS_BorangB2eMLK.rdf" ;
                                }else{
                                    idDokumenB2 = "DIS_BorangB2eNS.rdf" ;
                                }
                                LOG.info("Dokumen B2:" + idDokumenB2);
                            }
                        }
                    }
                }
            } else {
                addSimpleError("Id Permohonan tidak dijumpai");
            }
        } else {
            addSimpleError("Sila masukkan Id Permohonan");
        }
        return new ForwardResolution("/WEB-INF/jsp/utiliti/carian_pelan.jsp");

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

    public PermohonanDAO getPermohonanDAO() {
        return permohonanDAO;
    }

    public void setPermohonanDAO(PermohonanDAO permohonanDAO) {
        this.permohonanDAO = permohonanDAO;
    }

    public DisLaporanTanahService getDisLaporanTanahService() {
        return disLaporanTanahService;
    }

    public void setDisLaporanTanahService(DisLaporanTanahService disLaporanTanahService) {
        this.disLaporanTanahService = disLaporanTanahService;
    }

    public List<PelanGIS> getListPelanGIS() {
        return listPelanGIS;
    }

    public void setListPelanGIS(List<PelanGIS> listPelanGIS) {
        this.listPelanGIS = listPelanGIS;
    }

    public String getIdDokumenB1() {
        return idDokumenB1;
    }

    public void setIdDokumenB1(String idDokumenB1) {
        this.idDokumenB1 = idDokumenB1;
    }

    public String getIdDokumenB2() {
        return idDokumenB2;
    }

    public void setIdDokumenB2(String idDokumenB2) {
        this.idDokumenB2 = idDokumenB2;
    }

    public String getKodNegeri() {
        return kodNegeri;
    }

    public void setKodNegeri(String kodNegeri) {
        this.kodNegeri = kodNegeri;
    }
}
