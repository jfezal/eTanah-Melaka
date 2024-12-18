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
import etanah.service.PengambilanService1;
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

@UrlBinding("/pengambilan/MaklumSediaBayaran")
public class JanaSuratMaklumSediaBayaranActionBean extends AbleActionBean {

    private static Logger logger = Logger.getLogger(JanaSuratMaklumSediaBayaranActionBean.class);
    @Inject
    PermohonanRujukanLuarDAO permohonanRujukanLuarDAO;
    @Inject
    PermohonanTuntutanKosDAO permohonanTuntutanKosDAO;
    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    PengambilanService1 pengambilanService1;
    @Inject
    PemohonDAO pemohonDAO;
    @Inject
    KodCawanganDAO kodCawanganDAO;
    @Inject
    PihakDAO pihakDAO;
    @Inject
    PemohonService pemohonService;
    @Inject
    etanah.Configuration conf;
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
        Pengguna pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");

        if (idPermohonan != null) {
            permohonan = permohonanDAO.findById(idPermohonan);
            FasaPermohonan fasaPermohonan;

            if ("04".equals(conf.getProperty("kodNegeri"))) {
                fasaPermohonan = pengambilanService1.findFasaPermohonan_KeputusanNIDAliran(idPermohonan, "14TentukanKos");
                if (fasaPermohonan != null) {
                    if (fasaPermohonan.getKeputusan() != null) {
                        KodKeputusan kodKeputusan = new KodKeputusan();
                        kodKeputusan = fasaPermohonan.getKeputusan();

                        if (kodKeputusan != null) {
                            if (fasaPermohonan.getIdAliran().equals("14TentukanKos") && !kodKeputusan.getKod().equals("2A")) {
                                addSimpleError("Keputusan Bukan Gantirugi (3(1)(a))");
                                getContext().getRequest().setAttribute("form1", Boolean.FALSE);
                            } else {
                                getContext().getRequest().setAttribute("form1", Boolean.TRUE);
                            }
                        }
                    } else {
                        addSimpleError("Sila Buat Keputusan Terlebih Dahulu");
                        getContext().getRequest().setAttribute("form1", Boolean.FALSE);
                    }
                }
            }

            if ("05".equals(conf.getProperty("kodNegeri"))) {
                fasaPermohonan = pengambilanService1.findFasaPermohonan_KeputusanNIDAliran(idPermohonan, "16KeputusanBicara");
                if (fasaPermohonan != null) {

                    if (fasaPermohonan.getKeputusan() != null) {
                        KodKeputusan kodKeputusan = new KodKeputusan();
                        kodKeputusan = fasaPermohonan.getKeputusan();

                        if (kodKeputusan != null) {
                            if (fasaPermohonan.getIdAliran().equals("16KeputusanBicara") && !kodKeputusan.getKod().equals("1A")) {
                                addSimpleError("Keputusan Bukan Gantirugi (3(1)(a))");
                                getContext().getRequest().setAttribute("form1", Boolean.FALSE);
                            } else {
                                getContext().getRequest().setAttribute("form1", Boolean.TRUE);
                            }
                        }
                    } else {
                        addSimpleError("Sila Buat Keputusan Terlebih Dahulu");
                        getContext().getRequest().setAttribute("form1", Boolean.FALSE);
                    }
                }
            }
        }
//        }
        return new JSP("pengambilan/janaSuratMaklumSediaBayaran.jsp").addParameter("tab", "true");
    }

    public Resolution showForm2() {
        getContext().getRequest().setAttribute("form1", Boolean.TRUE);
        return new JSP("pengambilan/janaSuratMaklumSediaBayaran.jsp").addParameter("tab", "true");
    }
}


