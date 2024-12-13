/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package etanah.view.stripes.pengambilan;

/**
 *
 * @author massita
 */
import net.sourceforge.stripes.action.ForwardResolution;
import org.apache.commons.lang.StringUtils;
import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.dao.KodCawanganDAO;
import etanah.dao.PihakDAO;
import etanah.dao.PemohonDAO;
import etanah.dao.PermohonanDAO;
import etanah.dao.PermohonanRujukanLuarDAO;
import etanah.dao.PermohonanTuntutanKosDAO;
import etanah.service.common.PemohonService;
import etanah.model.*;
import etanah.model.KodCawangan;
import etanah.model.PermohonanTuntutanKos;
import etanah.service.PengambilanService;
import etanah.view.etanahActionBeanContext;
import java.util.Date;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.RedirectResolution;
import net.sourceforge.stripes.action.StreamingResolution;
import net.sourceforge.stripes.action.UrlBinding;
import java.util.List;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.controller.LifecycleStage;
import org.apache.log4j.Logger;
import org.apache.commons.lang.StringUtils;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import java.math.BigDecimal;
import org.hibernate.Transaction;
import java.text.ParseException;

@UrlBinding("/pengambilan/JanaSuratJPPH")
public class JanaSuratKepadaJPPHActionBean extends AbleActionBean
{
    private static Logger logger = Logger.getLogger(JanaSuratKepadaJPPHActionBean.class);
    @Inject
    PermohonanRujukanLuarDAO permohonanRujukanLuarDAO;
    @Inject
    PermohonanTuntutanKosDAO permohonanTuntutanKosDAO;
    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    PengambilanService pengambilanService;
    @Inject
    PemohonDAO pemohonDAO;
    @Inject
    KodCawanganDAO kodCawanganDAO;
    @Inject
    PihakDAO pihakDAO;
    @Inject
    PemohonService pemohonService;

    private List<Pemohon> pemohonList;
    private Permohonan permohonan;
    private Dokumen dokumen;
    private PermohonanRujukanLuar permohonanRujukanLuar;
    private List<PermohonanRujukanLuar> permohonanRujukanLuarList;
    private String idsblm;
    private String borangK;
    private String kodDokumen;
    private Pemohon pemohon;
    private Pihak pihak;
    private KodCawangan cawangan;
    private PermohonanTuntutanKos permohonanTuntutanKos;
    private List<PermohonanTuntutanKos> senaraiKosgantiRugi;
    private BigDecimal jumCaraBayar = (BigDecimal.ZERO);
    private BigDecimal jumCaraBayar1 = (BigDecimal.ZERO);
    private String nama;
    private String item;
    boolean kerosakan;
    private String idKos;
    //private String[] AmaunDituntut;
    private BigDecimal amaunTuntutan;
    //private String[] AmaunDiLaksanakan;
    private BigDecimal amaunSebenar;
    private String idPermohonan;
    private Integer index;

    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;

    @DefaultHandler
    public Resolution showForm() {
        return new JSP("pengambilan/janaSuratKepadaJPPH.jsp").addParameter("tab", "true");
    }
}


