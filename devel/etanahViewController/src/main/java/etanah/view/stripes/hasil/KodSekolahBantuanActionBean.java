/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.stripes.hasil;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.dao.SenaraiRujukanDAO;
import etanah.model.Alamat;
import etanah.model.InfoAudit;
import etanah.model.KodNegeri;
import etanah.model.KodSenarai;
import etanah.model.Pengguna;
import etanah.model.SenaraiRujukan;
import etanah.view.ListUtil;
import etanah.view.etanahActionBeanContext;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.StreamingResolution;
import net.sourceforge.stripes.action.UrlBinding;
import org.apache.commons.lang.StringUtils;
import org.hibernate.Session;
import org.apache.log4j.Logger;

/**
 *
 * @author abu.mansur
 */
@UrlBinding("/hasil/kodSekolahBantuan")
public class KodSekolahBantuanActionBean extends AbleActionBean {

    private static final Logger LOG = Logger.getLogger(KodSekolahBantuanActionBean.class);
    @Inject
    private KodSekolahBantuanService kodSekolahBantuanService;
    @Inject
    private ListUtil listUtil;
    @Inject
    private SenaraiRujukanDAO senaraiRujukanDAO;
    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;
    private SenaraiRujukan senaraiRuju = new SenaraiRujukan();
    private List<SenaraiRujukan> senaraiRujukan = new ArrayList<SenaraiRujukan>();
    private String kodNegeri;
    private String searchKod;
    private String searchNama;
    private String negeriEdit;

    public String getSearchKod() {
        return searchKod;
    }

    public void setSearchKod(String searchKod) {
        this.searchKod = searchKod;
    }

    public String getSearchNama() {
        return searchNama;
    }

    public void setSearchNama(String searchNama) {
        this.searchNama = searchNama;
    }

    public String getKodNegeri() {
        return kodNegeri;
    }

    public void setKodNegeri(String kodNegeri) {
        this.kodNegeri = kodNegeri;
    }

    public KodSekolahBantuanService getKodSekolahBantuanService() {
        return kodSekolahBantuanService;
    }

    public void setKodSekolahBantuanService(KodSekolahBantuanService kodSekolahBantuanService) {
        this.kodSekolahBantuanService = kodSekolahBantuanService;
    }

    public ListUtil getListUtil() {
        return listUtil;
    }

    public void setListUtil(ListUtil listUtil) {
        this.listUtil = listUtil;
    }

    public SenaraiRujukan getSenaraiRuju() {
        return senaraiRuju;
    }

    public void setSenaraiRuju(SenaraiRujukan senaraiRuju) {
        this.senaraiRuju = senaraiRuju;
    }

    public List<SenaraiRujukan> getSenaraiRujukan() {
        return senaraiRujukan;
    }

    public void setSenaraiRujukan(List<SenaraiRujukan> senaraiRujukan) {
        this.senaraiRujukan = senaraiRujukan;
    }

    public String getNegeriEdit() {
        return negeriEdit;
    }

    public void setNegeriEdit(String negeriEdit) {
        this.negeriEdit = negeriEdit;
    }

    @DefaultHandler
    public Resolution showForm() {
        return new ForwardResolution("/WEB-INF/jsp/hasil/kod_sekolah_bantuan.jsp");
    }

    public Resolution createKod() {
        getContext().getRequest().setAttribute("New", Boolean.TRUE);
        senaraiRuju = new SenaraiRujukan();
        return new ForwardResolution("/WEB-INF/jsp/hasil/kod_sekolah_bantuan_popup.jsp").addParameter("popup", "true");
    }

    public Resolution showEditKod() {
        getContext().getRequest().setAttribute("New", Boolean.FALSE);
        String kod2 = getContext().getRequest().getParameter("kod");
        senaraiRuju = senaraiRujukanDAO.findById(kod2);
        if (senaraiRuju != null) {
            negeriEdit = senaraiRuju.getAlamat().getNegeri().getKod();
        }
        return new JSP("hasil/kod_sekolah_bantuan_popup.jsp").addParameter("popup", "true");
    }

    public Resolution refreshpage() {
        senaraiRujukan = kodSekolahBantuanService.getKodSekolah(searchKod, searchNama, kodNegeri);
        getContext().getRequest().setAttribute("Show", Boolean.TRUE);
        return new ForwardResolution("/WEB-INF/jsp/hasil/kod_sekolah_bantuan.jsp");
    }

    public Resolution simpanKod() {
        Pengguna pg = (Pengguna) context.getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        InfoAudit ia = new InfoAudit();
        ia.setDiKemaskiniOleh(pg);
        ia.setTarikhKemaskini(new java.util.Date());
        SenaraiRujukan s = new SenaraiRujukan();
        s = senaraiRujukanDAO.findById(senaraiRuju.getKod());
        if (s == null) {
            s = new SenaraiRujukan();
            s.setCawangan(pg.getKodCawangan());
            KodSenarai sen = new KodSenarai();
            sen.setKod("01"); // kodSenarai 01 = Kod Sekolah Bantuan
            s.setSenarai(sen);
        }
        s.setKod(senaraiRuju.getKod());
        s.setNama(senaraiRuju.getNama());
        Alamat a = new Alamat();
        LOG.debug("alamat1 :" + senaraiRuju.getAlamat().getAlamat1().toString());
        a.setAlamat1(senaraiRuju.getAlamat().getAlamat1());
        a.setAlamat2(senaraiRuju.getAlamat().getAlamat2());
        a.setAlamat3(senaraiRuju.getAlamat().getAlamat3());
        a.setAlamat4(senaraiRuju.getAlamat().getAlamat4());
        LOG.debug("Poskod :" + senaraiRuju.getAlamat().getPoskod().toString());
        a.setPoskod(senaraiRuju.getAlamat().getPoskod());
        LOG.debug("Kod Negeri :" + negeriEdit);
        KodNegeri k = new KodNegeri();
        k.setKod(negeriEdit);
        a.setNegeri(k);
        s.setAlamat(a);
        s.setNoTel1(senaraiRuju.getNoTel1());
        s.setNoTel2(senaraiRuju.getNoTel2());
        s.setNofax(senaraiRuju.getNofax());
        s.setEmail(senaraiRuju.getEmail());
        ia.setTarikhMasuk(new Date());
        ia.setDimasukOleh(pg);
        s.setInfoAudit(ia);

        kodSekolahBantuanService.simpanKod(s);
        getContext().getRequest().setAttribute("Show", Boolean.TRUE);
        return new ForwardResolution("/WEB-INF/jsp/hasil/kod_sekolah_bantuan.jsp");
    }

    public Resolution doCheckKod() {
        String results = "Tiada";
        String kodSekolah = getContext().getRequest().getParameter("kod");
        LOG.info("kodSekolah :" + kodSekolah);
        SenaraiRujukan SR = new SenaraiRujukan();
        if (StringUtils.isNotBlank(kodSekolah)) {
            SR = senaraiRujukanDAO.findById(kodSekolah);
            if (SR != null) {
                results = "Ada";
            }
        }
        return new StreamingResolution("text/plain", results);
    }

    public Resolution search() {
        senaraiRujukan = kodSekolahBantuanService.getKodSekolah(searchKod, searchNama, kodNegeri);
        getContext().getRequest().setAttribute("Show", Boolean.TRUE);
        return new ForwardResolution("/WEB-INF/jsp/hasil/kod_sekolah_bantuan.jsp");
    }

    public Resolution deleteKod() {
        String kod1 = getContext().getRequest().getParameter("kod");
        if (StringUtils.isNotBlank(kod1)) {
            SenaraiRujukan senari = senaraiRujukanDAO.findById(kod1);
            try {
                kodSekolahBantuanService.deleteSenaraiRujukan(senari);
            } catch (Exception ex) {
                LOG.error("Delete Rekod ex: " + ex);
                ex.printStackTrace();
            }
        }
        getContext().getRequest().setAttribute("Show", Boolean.TRUE);
        return new ForwardResolution("/WEB-INF/jsp/hasil/kod_sekolah_bantuan.jsp");
    }
}
