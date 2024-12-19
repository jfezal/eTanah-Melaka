/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package etanah.view.stripes.pengambilan;

import etanah.dao.*;
import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.model.Hakmilik;
import etanah.model.HakmilikPermohonan;

import etanah.model.InfoAudit;
import etanah.model.Pengguna;
import java.util.Date;

import etanah.model.Permohonan;
import etanah.model.KodCawangan;
import etanah.model.KodDokumen;
import etanah.model.LaporanTanah;
import etanah.model.Pemohon;
import etanah.model.PermohonanKertas;
import etanah.model.PermohonanKertasKandungan;
import etanah.model.PermohonanPihak;

import etanah.view.etanahActionBeanContext;
import java.util.List;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;
import org.apache.log4j.Logger;
import java.text.SimpleDateFormat;
import etanah.service.PendudukanSementaraMMKNService;
import etanah.service.RegService;
import etanah.workflow.WorkFlowService;
import java.util.ArrayList;
import net.sourceforge.stripes.action.RedirectResolution;
import oracle.bpel.services.workflow.verification.IWorkflowContext;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author massita
 */
@UrlBinding("/pengambilan/permohonanLaluLalang")
public class PermohonanLaluLalangActionBean extends AbleActionBean{

    private static Logger logger = Logger.getLogger(PermohonanLaluLalangActionBean.class);
    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;
    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    PemohonDAO pemohonDAO;
    @Inject
    PendudukanSementaraMMKNService pendudukanSementaraMMKNService;
    @Inject
    PermohonanKertasDAO permohonanKertasDAO;
    @Inject
    HakmilikDAO hakmilikDAO;
    @Inject
    PermohonanKertasKandunganDAO permohonanKertasKandunganDAO;
    @Inject
    RegService regService;
    @Inject
    HakmilikPermohonanDAO hakmilikPermohonanDAO;

    IWorkflowContext ctx = null;
    private Permohonan permohonanSebelum;
    private Permohonan permohonan;
    private String idPermohonan;
    private Pengguna pguna;
    private KodCawangan cawangan;
    private LaporanTanah laporanTanah;

    private int bil = 0;
//    private String masa;
    private String kertasBil;
    private String kandungan;
    private String idKandungan;
    private String tempat;
    private String syorPtg;
    private List<HakmilikPermohonan> hakmilikPermohonanList;
    private List<Pemohon> listPemohon;
    private Pemohon pemohon;
    private PermohonanPihak penerima;
    private Hakmilik hakmilik;
    private String tujuan;
    private String tarikhMesyuarat;
    String tarikhDaftar;
    String namaProjek;
    private String heading;
//    private Boolean opFlag = false;
    private final String tajuk = "KERTAS PERMOHONAN PENGAMBILAN PENARIKAN BALIK NEGERI MELAKA";
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss aaa");


    @DefaultHandler
    public Resolution showForm2() {
        getContext().getRequest().setAttribute("formPtg", Boolean.TRUE);
        return new JSP("pengambilan/negerisembilan/haklalulalangpersendirian/permohonanLaluLalang.jsp").addParameter("tab", "true");
    }
}
