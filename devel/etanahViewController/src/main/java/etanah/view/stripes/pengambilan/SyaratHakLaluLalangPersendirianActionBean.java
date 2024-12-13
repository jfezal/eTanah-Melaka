/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.stripes.pengambilan;

/**
 *
 * @author massita
 */
import etanah.view.stripes.hasil.*;
import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.dao.FasaPermohonanDAO;
import etanah.dao.HakmilikPermohonanDAO;
import etanah.dao.LaporanTanahDAO;
import etanah.dao.PermohonanDAO;
import etanah.dao.PermohonanRujukanLuarDAO;
import etanah.model.FasaPermohonan;
import etanah.model.HakmilikPermohonan;
import etanah.model.InfoAudit;
import etanah.model.KodDokumen;
import etanah.model.KodRujukan;
import etanah.model.LaporanTanah;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.model.PermohonanRujukanLuar;
import etanah.service.BPelService;
import etanah.service.LaporanTanahService;
import etanah.view.etanahActionBeanContext;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;
import org.apache.log4j.Logger;
import org.apache.commons.lang.StringUtils;
import oracle.bpel.services.workflow.task.model.Task;

@UrlBinding("/pengambilan/syaratHLLP")
public class SyaratHakLaluLalangPersendirianActionBean extends AbleActionBean {

    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    LaporanTanahDAO laporanTanahDAO;
    @Inject
    FasaPermohonanDAO fasaPermohonanDAO;
    @Inject
    HakmilikPermohonanDAO hakmilikPermohonanDAO;
    @Inject
    PermohonanRujukanLuarDAO permohonanRujukanLuarDAO;
    @Inject
    LaporanTanahService laporanTanahService;
    @Inject
    private etanah.Configuration conf;
    List<HakmilikPermohonan> senaraiHakmilik = new ArrayList<HakmilikPermohonan>();
    private Permohonan permohonan;
    private HakmilikPermohonan hakmilikPermohonan = new HakmilikPermohonan();
    private LaporanTanah laporanTanah;
    private FasaPermohonan fasaPermohonan;
    private PermohonanRujukanLuar permohonanRujukanLuar;
    private static final Logger LOG = Logger.getLogger(SyaratHakLaluLalangPersendirianActionBean.class);
    private static final boolean IS_LOG_DEBUG = LOG.isDebugEnabled();
    private String stageId;
//    private static String id;
//    private static String idHakmilik;
//    private static String idPermohonan;
       private String id;
    private  String idHakmilik;
    private  String idPermohonan;
    private String idLaporan;
    private String kodNegeri;
    private boolean btn = false;
    private boolean flag1 = false;
    private List<LaporanTanah> senaraiTanah;
     private List<HakmilikPermohonan> senaraiHakmilikPermohonan;
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

    @DefaultHandler
    public Resolution showForm() {
        return new JSP("pengambilan/negerisembilan/haklalulalangpersendirian/syaratHakLaluLalangPersendirian.jsp").addParameter("tab", "true");
    }
}
