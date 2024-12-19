/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.stripes.pengambilan.sek8;

import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.dao.KodBandarPekanMukimDAO;
import etanah.dao.KodDaerahDAO;
import etanah.dao.KodNegeriDAO;
import etanah.dao.KodSeksyenDAO;
import etanah.dao.KodUOMDAO;
import etanah.dao.KodUnitDAO;
import etanah.dao.PermohonanDAO;
import etanah.dao.PermohonanHakmilikBaruDAO;
import etanah.dao.PermohonanPengambilanHakmilikDAO;
import etanah.dao.gis.GISPelanDAO;
import etanah.model.KodBandarPekanMukim;
import etanah.model.KodDaerah;
import etanah.model.KodNegeri;
import etanah.model.KodSeksyen;
import etanah.model.KodUOM;
import etanah.model.KodUnit;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.model.PermohonanPlotKpsn;
import etanah.model.ambil.PermohonanHakmilikBaru;
import etanah.model.ambil.PermohonanPengambilanHakmilik;
import etanah.model.gis.GISPelan;
import etanah.model.gis.GISPelanPK;
import etanah.service.acq.service.BorangEFService;
import etanah.service.ambil.PengambilanAPTService;
import etanah.util.FileUtil;
import etanah.view.BasicTabActionBean;
import etanah.view.etanahActionBeanContext;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.FileBean;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import org.apache.commons.lang.StringUtils;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author mohd.faidzal
 */
@UrlBinding("/pengambilan/pelan_pa")
public class KemasukanPelanPAActionBean extends BasicTabActionBean {

    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;
    @Inject
    etanah.Configuration conf;
    @Inject
    GISPelanDAO pelanGISDAO;
    @Inject
    KodNegeriDAO kodNegeriDAO;
    @Inject
    KodDaerahDAO kodDaerahDAO;
    @Inject
    KodBandarPekanMukimDAO kodBandarPekanMukimDAO;
    @Inject
    KodUOMDAO kodUOMDAO;
    @Inject
    KodSeksyenDAO kodSeksyenDAO;
    @Inject
    BorangEFService borangEFService;
    @Inject
    PengambilanAPTService pengambilanAPTService;
    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    PermohonanHakmilikBaruDAO permohonanHakmilikBaruDAO;
    @Inject
    PermohonanPengambilanHakmilikDAO permohonanPengambilanHakmilikDAO;
    PermohonanHakmilikBaru phb;
    String kodDaerah;
    String kodMukim;
    String kodSeksyen;
    String noLot;
    String noPa;
    String luas;
    String kodUOM;
    FileBean file;
    List<KemasukanPelanPAForm> listKemasukanPelanPAForm;

    @DefaultHandler
    public Resolution showForm() {
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        listKemasukanPelanPAForm = pengambilanAPTService.setListKemasukanPelanForm(idPermohonan);
        Permohonan p = permohonanDAO.findById(idPermohonan);
        return new JSP("/pengambilan/APT/kemasukan_pelanpa.jsp").addParameter("tab", "true");
    }

    public Resolution simpan() {
                Pengguna pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);

        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        String[] ids = getContext().getRequest().getParameterValues("idAh");
        String[] bezaLuas = getContext().getRequest().getParameterValues("bezaLuas");
        for (int i = 0; i < ids.length; i++) {

            PermohonanPengambilanHakmilik permohonanPengambilanHakmilik = permohonanPengambilanHakmilikDAO.findById(Long.valueOf(ids[i]));
            permohonanPengambilanHakmilik.setFlagBezaLuas(bezaLuas[i]);
            pengambilanAPTService.saveOrUpdatehakmilikpermohonan(permohonanPengambilanHakmilik);
        }
        return new JSP("/pengambilan/APT/kemasukan_pelanpa.jsp").addParameter("tab", "true");
    }

    public Resolution kemaskiniBaru() {
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        String id = getContext().getRequest().getParameter("id");
        GISPelan gis;
        phb = permohonanHakmilikBaruDAO.findById(Long.valueOf(id));
        kodDaerah = phb.getHakmilikPermohonan().getHakmilik().getDaerah().getKod();
        kodMukim = phb.getHakmilikPermohonan().getHakmilik().getBandarPekanMukim().getKod() + "";
        kodSeksyen = phb.getHakmilikPermohonan().getHakmilik().getSeksyen()!=null?phb.getHakmilikPermohonan().getHakmilik().getSeksyen().getKod() + "":"";
        noLot = phb.getNoLot();
        noPa = phb.getNoPelanPA();
        luas = phb.getLuas();
        kodUOM = phb.getKodUom() != null ? phb.getKodUom().getKod() : "";
        return new JSP("/pengambilan/APT/muat_naik_b1.jsp").addParameter("popup", "true");
    }

    public Resolution simpanPopup() throws IOException, Exception {
        String id = getContext().getRequest().getParameter("id");
        GISPelan gis;

        Session s = sessionProvider.get();
        Transaction tx = s.beginTransaction();
        GISPelan pelan = new GISPelan();
        GISPelanPK pk = new GISPelanPK();
        KodNegeri kn = kodNegeriDAO.findById(conf.getKodNegeri());
        KodDaerah kd = kodDaerahDAO.findById(kodDaerah);
        KodBandarPekanMukim bpm =null;
         KodSeksyen seksyen1  = null;
        if (StringUtils.isNotBlank(kodMukim)) {
             bpm = kodBandarPekanMukimDAO.findById(Integer.parseInt(kodMukim));
        }
        if (StringUtils.isNotBlank(kodSeksyen)) {
             seksyen1 = kodSeksyenDAO.findById(Integer.parseInt(kodSeksyen));
        }
        if (seksyen1 != null) {
            pk.setKodSeksyen(String.valueOf(seksyen1.getSeksyen()));
        } else {
            pk.setKodSeksyen("000");

        }
        pk.setKodNegeri(kn);
        pk.setKodDaerah(kd);
        pk.setKodMukim(bpm.getbandarPekanMukim());

        pk.setNoLot(noLot);
        pk.setJenisPelan("B1");
        pelan.setPelanGISPK(pk);
        KodUOM unit = null;
        if(StringUtils.isNotBlank(kodUOM)){unit = kodUOMDAO.findById(kodUOM);}
        pelan.setLuas(new BigDecimal(luas));
//        pelan.setUnitUkur(unit.getId());
        pelan.setNoPelanAkui(noPa);
        pelan.setTrhKemaskini(new Date());
        pelan.setPelanTif(File.separator + kd.getKod() + File.separator + bpm.getbandarPekanMukim() + (seksyen1 != null ? File.separator + seksyen1.getSeksyen() : "") + File.separator + file.getFileName().toLowerCase());
//        pelan.setTrhKemaskini(new Date());
        pelanGISDAO.saveOrUpdate(pelan);
        String loc = conf.getPelanPath() + File.separator + pelan.getPelanGISPK().getJenisPelan() + File.separator + kd.getKod() + File.separator + bpm.getbandarPekanMukim() + (seksyen1 != null ? File.separator + seksyen1.getSeksyen() : "");
        FileUtil fileUtil = new FileUtil(loc);
        System.out.print("sssss" + file.getFileName().toLowerCase());
        fileUtil.saveFile(file.getFileName().toLowerCase(), file.getInputStream());
        phb = permohonanHakmilikBaruDAO.findById(Long.valueOf(id));
        phb.setLuas(luas);
        phb.setNoLot(noLot);
        phb.setNoPelanPA(noPa);
        phb.setKodUom(unit);
permohonanHakmilikBaruDAO.save(phb);
        tx.commit();

        return new JSP("/pengambilan/APT/muat_naik_b1.jsp").addParameter("popup", "true");
    }

    public List<KemasukanPelanPAForm> getListKemasukanPelanPAForm() {
        return listKemasukanPelanPAForm;
    }

    public void setListKemasukanPelanPAForm(List<KemasukanPelanPAForm> listKemasukanPelanPAForm) {
        this.listKemasukanPelanPAForm = listKemasukanPelanPAForm;
    }

    public PermohonanHakmilikBaru getPhb() {
        return phb;
    }

    public void setPhb(PermohonanHakmilikBaru phb) {
        this.phb = phb;
    }

    public String getKodDaerah() {
        return kodDaerah;
    }

    public void setKodDaerah(String kodDaerah) {
        this.kodDaerah = kodDaerah;
    }

    public String getKodMukim() {
        return kodMukim;
    }

    public void setKodMukim(String kodMukim) {
        this.kodMukim = kodMukim;
    }

    public String getKodSeksyen() {
        return kodSeksyen;
    }

    public void setKodSeksyen(String kodSeksyen) {
        this.kodSeksyen = kodSeksyen;
    }

    public String getNoLot() {
        return noLot;
    }

    public void setNoLot(String noLot) {
        this.noLot = noLot;
    }

    public String getNoPa() {
        return noPa;
    }

    public void setNoPa(String noPa) {
        this.noPa = noPa;
    }

    public String getLuas() {
        return luas;
    }

    public void setLuas(String luas) {
        this.luas = luas;
    }

    public String getKodUOM() {
        return kodUOM;
    }

    public void setKodUOM(String kodUOM) {
        this.kodUOM = kodUOM;
    }

    public FileBean getFile() {
        return file;
    }

    public void setFile(FileBean file) {
        this.file = file;
    }

}
