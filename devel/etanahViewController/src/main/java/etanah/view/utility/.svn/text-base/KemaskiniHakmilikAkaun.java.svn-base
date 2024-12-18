/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.utility;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.model.Pengguna;
import etanah.model.Hakmilik;
import etanah.model.Akaun;
import etanah.model.KodStatusHakmilik;
import etanah.model.KodStatusAkaun;
import etanah.dao.HakmilikDAO;
import etanah.dao.KodStatusHakmilikDAO;
import etanah.dao.KodStatusAkaunDAO;
import etanah.dao.AkaunDAO;
import etanah.dao.DokumenDAO;
import etanah.model.Dokumen;
import etanah.model.InfoAuditBaru;
import etanah.service.RegService;
import etanah.service.daftar.PembetulanService;
import etanah.view.etanahActionBeanContext;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.RedirectResolution;
import net.sourceforge.stripes.action.HttpCache;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import org.apache.commons.lang.StringUtils;
import org.hibernate.Session;
import org.hibernate.Query;

/**
 *
 * @author Administrator
 */
@HttpCache(allow = false)
@UrlBinding("/utiliti/baiki_hakmilik_akaun")
public class KemaskiniHakmilikAkaun extends AbleActionBean {

    @Inject
    HakmilikDAO hakmilikDao;
    @Inject
    AkaunDAO akaunDAO;
    @Inject
    RegService regService;
    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;
    @Inject
    PembetulanService pService;
    @Inject
    KodStatusHakmilikDAO kodStatusHakmilikDAO;
    @Inject
    KodStatusAkaunDAO kodStatusAkaunDAO;    
    @Inject
    DokumenDAO dokumenDAO;
    Hakmilik hakmilik;
    Akaun akaun;
    String idHakmilik;
    String statusHakmilik;
    String kodStatusHakmilik;
    String cukaiSebenar;
    String statusAkaun;
    boolean showDetailHakmilik = false;
    private List<Akaun> listAkaun;
    private List<KodStatusHakmilik> listKodStatusHakmilik;
    private List<KodStatusAkaun> listKodStatusAkaun;
    private String idH;
    private String noAcc;
    private Pengguna peng;
    private InfoAuditBaru infoBaru;
    private String tarikhBatal;
    private String tarikhDaftarAsal;
    private String noVersiDhde;
    private String noVersiDhke;
    private String iddokumenDhde;
    private String iddokumenDhke;
    SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

    @DefaultHandler
    public Resolution showForm() {
        Pengguna peng = (Pengguna) context.getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        return new ForwardResolution("/WEB-INF/jsp/utiliti/baiki_hakmilik_akaun.jsp");
    }

    public Resolution cari() {
        String message = getContext().getRequest().getParameter("message");
        String error = getContext().getRequest().getParameter("error");

        if (StringUtils.isNotBlank(idHakmilik)) {
            hakmilik = hakmilikDao.findById(idHakmilik);
            if (hakmilik != null) {
                showDetailHakmilik = true;
                listAkaun = regService.findAkaunByIdHakmilik(idHakmilik);
            } else {
                addSimpleError("Hakmilik tidak wujud");
            }
            return new JSP("utiliti/baiki_hakmilik_akaun.jsp");
        } else {
            addSimpleError("Sila Masukkan Hakmilik");
            return new JSP("utiliti/baiki_hakmilik_akaun.jsp");
        }
    }

    public Resolution reload() {
        String idHakmilik = getContext().getRequest().getParameter("idHakmilik");
        hakmilik = hakmilikDao.findById(idHakmilik);
        showDetailHakmilik = true;
        listAkaun = regService.findAkaunByIdHakmilik(idHakmilik);
        return new JSP("utiliti/baiki_hakmilik_akaun.jsp");
    }

    public Resolution kemaskiniHakmilik() {
        String idHakmilik = getContext().getRequest().getParameter("idHakmilik");
        listKodStatusHakmilik = pService.findStatusHakmilik();
        hakmilik = new Hakmilik();
        Session s = sessionProvider.get();
        Query q1 = s.createQuery("SELECT hm FROM etanah.model.Hakmilik hm WHERE hm.idHakmilik = :idHakmilik");
        q1.setString("idHakmilik", idHakmilik);
        hakmilik = (Hakmilik) q1.uniqueResult();
        statusHakmilik = hakmilik.getKodStatusHakmilik().getNama();

        getContext().getRequest().setAttribute("idHakmilik", idHakmilik);

        return new JSP("utiliti/baiki_hakmilik_akaun_edit_hakmilik.jsp").addParameter("popup", "true");
    }

    public Resolution kemaskiniAkaun() {
        String noAkaun = getContext().getRequest().getParameter("noAkaun");
        listKodStatusAkaun = pService.findStatusAkaun();
        akaun = new Akaun();
        Session s = sessionProvider.get();
        Query q1 = s.createQuery("SELECT ac FROM etanah.model.Akaun ac WHERE ac.noAkaun = :noAkaun");
        q1.setString("noAkaun", noAkaun);
        akaun = (Akaun) q1.uniqueResult();
        statusAkaun = akaun.getStatus().getNama();

        getContext().getRequest().setAttribute("noAkaun", noAkaun);

        return new JSP("utiliti/baiki_hakmilik_akaun_edit_akaun.jsp").addParameter("popup", "true");
    }
    
      public Resolution saveTarikhNull() throws ParseException {
        idH = getContext().getRequest().getParameter("idHakmilik");
        peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);

        Hakmilik hm = pService.findHakmilik(idH);

        if (hm != null) {
            InfoAuditBaru ia = new InfoAuditBaru();
                ia.setDikemaskiniOlehBaru(peng);
                ia.setTarikhKemaskiniBaru(new java.util.Date());
                hm.setInfoAuditBaru(ia);         
                hm.setTarikhBatal(null);
            
            pService.updateHakmilik(hm);
            addSimpleMessage("Kemaskini Data Telah Berjaya");

        }

        listKodStatusHakmilik = pService.findStatusHakmilik();
        hakmilik = new Hakmilik();
        Session s = sessionProvider.get();
        Query q1 = s.createQuery("SELECT hm FROM etanah.model.Hakmilik hm WHERE hm.idHakmilik = :idHakmilik");
        q1.setString("idHakmilik", idH);
        hakmilik = (Hakmilik) q1.uniqueResult();
        statusHakmilik = hakmilik.getKodStatusHakmilik().getNama();

        getContext().getRequest().setAttribute("idHakmilik", idH);

        return new JSP("utiliti/baiki_hakmilik_akaun_edit_hakmilik.jsp").addParameter("popup", "true");
    }
      
    public Resolution saveTarikhDaftarAsalNull() throws ParseException {
        idH = getContext().getRequest().getParameter("idHakmilik");
        peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);

        Hakmilik hm = pService.findHakmilik(idH);

        if (hm != null) {
            InfoAuditBaru ia = new InfoAuditBaru();
                ia.setDikemaskiniOlehBaru(peng);
                ia.setTarikhKemaskiniBaru(new java.util.Date());
                hm.setInfoAuditBaru(ia);         
                hm.setTarikhDaftarAsal(null);
            
            pService.updateHakmilik(hm);
            addSimpleMessage("Kemaskini Data Telah Berjaya");

        }

        listKodStatusHakmilik = pService.findStatusHakmilik();
        hakmilik = new Hakmilik();
        Session s = sessionProvider.get();
        Query q1 = s.createQuery("SELECT hm FROM etanah.model.Hakmilik hm WHERE hm.idHakmilik = :idHakmilik");
        q1.setString("idHakmilik", idH);
        hakmilik = (Hakmilik) q1.uniqueResult();
        statusHakmilik = hakmilik.getKodStatusHakmilik().getNama();

        getContext().getRequest().setAttribute("idHakmilik", idH);

        return new JSP("utiliti/baiki_hakmilik_akaun_edit_hakmilik.jsp").addParameter("popup", "true");
    }
      
    public Resolution saveVersiNull() throws ParseException {
        idH = getContext().getRequest().getParameter("idHakmilik");
        peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);

        Hakmilik hm = pService.findHakmilik(idH);

        if (hm != null) {
            InfoAuditBaru ia = new InfoAuditBaru();
                ia.setDikemaskiniOlehBaru(peng);
                ia.setTarikhKemaskiniBaru(new java.util.Date());
                hm.setInfoAuditBaru(ia);         
                //hm.setTarikhBatal(null);
                hm.setNoVersiDhde(null);
                hm.setNoVersiDhke(null);
            
            pService.updateHakmilik(hm);
            addSimpleMessage("Kemaskini Data Telah Berjaya");

        }

        listKodStatusHakmilik = pService.findStatusHakmilik();
        hakmilik = new Hakmilik();
        Session s = sessionProvider.get();
        Query q1 = s.createQuery("SELECT hm FROM etanah.model.Hakmilik hm WHERE hm.idHakmilik = :idHakmilik");
        q1.setString("idHakmilik", idH);
        hakmilik = (Hakmilik) q1.uniqueResult();
        statusHakmilik = hakmilik.getKodStatusHakmilik().getNama();

        getContext().getRequest().setAttribute("idHakmilik", idH);

        return new JSP("utiliti/baiki_hakmilik_akaun_edit_hakmilik.jsp").addParameter("popup", "true");
    }

    public Resolution saveHakmilik() throws ParseException {
        idH = getContext().getRequest().getParameter("idHakmilik");
        peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);

        Hakmilik hm = pService.findHakmilik(idH);

        if (hm != null) {
            InfoAuditBaru ia = new InfoAuditBaru();
            ia.setDikemaskiniOlehBaru(peng);
            ia.setTarikhKemaskiniBaru(new java.util.Date());
            hm.setInfoAuditBaru(ia);
            if (StringUtils.isNotBlank(kodStatusHakmilik)) {
                hm.setKodStatusHakmilik(kodStatusHakmilikDAO.findById(kodStatusHakmilik));
            }
            if (StringUtils.isNotBlank(cukaiSebenar)) {
                BigDecimal cukai = new BigDecimal(cukaiSebenar);
                hm.setCukaiSebenar(cukai);
            }
            if (StringUtils.isNotBlank(tarikhBatal)) {
                hm.setTarikhBatal(dateFormat.parse(tarikhBatal));
            }
            if (StringUtils.isNotBlank(noVersiDhde)) {
                hm.setNoVersiDhde(Integer.parseInt(noVersiDhde));
            }
            if (StringUtils.isNotBlank(noVersiDhke)) {
                hm.setNoVersiDhke(Integer.parseInt(noVersiDhke));
            }
            if (StringUtils.isNotBlank(tarikhDaftarAsal)) {
                hm.setTarikhDaftarAsal(dateFormat.parse(tarikhDaftarAsal));
            }
            if (StringUtils.isNotBlank(iddokumenDhde)) {
                Dokumen dok = dokumenDAO.findById(Long.parseLong(iddokumenDhde));
                hm.setDhde(dok);
            }
            if (StringUtils.isNotBlank(iddokumenDhke)) {
                Dokumen dok = dokumenDAO.findById(Long.parseLong(iddokumenDhke));
                hm.setDhke(dok);
            }
            pService.updateHakmilik(hm);
            addSimpleMessage("Kemaskini Data Telah Berjaya");

        }

        listKodStatusHakmilik = pService.findStatusHakmilik();
        hakmilik = new Hakmilik();
        Session s = sessionProvider.get();
        Query q1 = s.createQuery("SELECT hm FROM etanah.model.Hakmilik hm WHERE hm.idHakmilik = :idHakmilik");
        q1.setString("idHakmilik", idH);
        hakmilik = (Hakmilik) q1.uniqueResult();
        statusHakmilik = hakmilik.getKodStatusHakmilik().getNama();

        getContext().getRequest().setAttribute("idHakmilik", idH);

        return new JSP("utiliti/baiki_hakmilik_akaun_edit_hakmilik.jsp").addParameter("popup", "true");
    }

    public Resolution saveAkaun() throws ParseException {
        noAcc = getContext().getRequest().getParameter("noAkaun");
        peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);

        Akaun acc = akaunDAO.findById(noAcc);

        if (acc != null) {
            InfoAuditBaru ia = new InfoAuditBaru();
            ia.setDikemaskiniOlehBaru(peng);
            ia.setTarikhKemaskiniBaru(new java.util.Date());
            acc.setInfoAuditBaru(ia);
            if (StringUtils.isNotBlank(statusAkaun)) {
                acc.setStatus(kodStatusAkaunDAO.findById(statusAkaun));
            }
            regService.saveOrUpdateWTrans(acc);
            addSimpleMessage("Kemaskini Data Telah Berjaya");
        }

        listKodStatusAkaun = pService.findStatusAkaun();
        akaun = new Akaun();
        Session s = sessionProvider.get();
        Query q1 = s.createQuery("SELECT ac FROM etanah.model.Akaun ac WHERE ac.noAkaun = :noAkaun");
        q1.setString("noAkaun", noAcc);
        akaun = (Akaun) q1.uniqueResult();
        statusAkaun = akaun.getStatus().getNama();

        getContext().getRequest().setAttribute("noAkaun", noAcc);

        return new JSP("utiliti/baiki_hakmilik_akaun_edit_akaun.jsp").addParameter("popup", "true");
    }

    public Hakmilik getHakmilik() {
        return hakmilik;
    }

    public void setHakmilik(Hakmilik hakmilik) {
        this.hakmilik = hakmilik;
    }

    public String getIdHakmilik() {
        return idHakmilik;
    }

    public void setIdHakmilik(String idHakmilik) {
        this.idHakmilik = idHakmilik;
    }

    public boolean isShowDetailHakmilik() {
        return showDetailHakmilik;
    }

    public void setShowDetailHakmilik(boolean showDetailHakmilik) {
        this.showDetailHakmilik = showDetailHakmilik;
    }

    public List<Akaun> getListAkaun() {
        return listAkaun;
    }

    public void setListAkaun(List<Akaun> ListAkaun) {
        this.listAkaun = listAkaun;
    }

    public List<KodStatusHakmilik> getListKodStatusHakmilik() {
        return listKodStatusHakmilik;
    }

    public void setListKodStatusHakmilik(List<KodStatusHakmilik> listKodStatusHakmilik) {
        this.listKodStatusHakmilik = listKodStatusHakmilik;
    }

    public String getStatusHakmilik() {
        return statusHakmilik;
    }

    public void setStatusHakmilik(String statusHakmilik) {
        this.statusHakmilik = statusHakmilik;
    }

    public Akaun getAkaun() {
        return akaun;
    }

    public void setAkaun(Akaun akaun) {
        this.akaun = akaun;
    }

    public String getStatusAkaun() {
        return statusAkaun;
    }

    public void setStatusAkaun(String statusAkaun) {
        this.statusAkaun = statusAkaun;
    }

    public List<KodStatusAkaun> getListKodStatusAkaun() {
        return listKodStatusAkaun;
    }

    public void setListKodStatusAkaun(List<KodStatusAkaun> listKodStatusAkaun) {
        this.listKodStatusAkaun = listKodStatusAkaun;
    }

    public String getIdH() {
        return idH;
    }

    public void setIdH(String idH) {
        this.idH = idH;
    }

    public Pengguna getPeng() {
        return peng;
    }

    public void setPeng(Pengguna peng) {
        this.peng = peng;
    }

    public InfoAuditBaru getInfoBaru() {
        return infoBaru;
    }

    public void setInfoBaru(InfoAuditBaru infoBaru) {
        this.infoBaru = infoBaru;
    }

    public String getKodStatusHakmilik() {
        return kodStatusHakmilik;
    }

    public void setKodStatusHakmilik(String kodStatusHakmilik) {
        this.kodStatusHakmilik = kodStatusHakmilik;
    }

    public String getCukaiSebenar() {
        return cukaiSebenar;
    }

    public void setCukaiSebenar(String cukaiSebenar) {
        this.cukaiSebenar = cukaiSebenar;
    }

    public String getTarikhBatal() {
        return tarikhBatal;
    }

    public void setTarikhBatal(String tarikhBatal) {
        this.tarikhBatal = tarikhBatal;
    }

    public String getNoAcc() {
        return noAcc;
    }

    public void setNoAcc(String noAcc) {
        this.noAcc = noAcc;
    }

    public String getNoVersiDhde() {
        return noVersiDhde;
    }

    public void setNoVersiDhde(String noVersiDhde) {
        this.noVersiDhde = noVersiDhde;
    }

    public String getNoVersiDhke() {
        return noVersiDhke;
    }

    public void setNoVersiDhke(String noVersiDhke) {
        this.noVersiDhke = noVersiDhke;
    }

    public String getTarikhDaftarAsal() {
        return tarikhDaftarAsal;
    }

    public void setTarikhDaftarAsal(String tarikhDaftarAsal) {
        this.tarikhDaftarAsal = tarikhDaftarAsal;
    }

    public String getIddokumenDhde() {
        return iddokumenDhde;
    }

    public void setIddokumenDhde(String iddokumenDhde) {
        this.iddokumenDhde = iddokumenDhde;
    }

    public String getIddokumenDhke() {
        return iddokumenDhke;
    }

    public void setIddokumenDhke(String iddokumenDhke) {
        this.iddokumenDhke = iddokumenDhke;
    }
    
}
