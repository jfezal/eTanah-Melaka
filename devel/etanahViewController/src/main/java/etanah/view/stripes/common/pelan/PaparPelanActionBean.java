/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.stripes.common.pelan;

import able.stripes.AbleActionBean;
import com.google.inject.Inject;
import etanah.dao.HakmilikPermohonanB1DAO;
import etanah.dao.KodBandarPekanMukimDAO;
import etanah.dao.KodDaerahDAO;
import etanah.dao.KodNegeriDAO;
import etanah.dao.KodSeksyenDAO;
import etanah.dao.NoPtDAO;
import etanah.dao.PermohonanDAO;
import etanah.dao.PermohonanPlotPelanDAO;
import etanah.dao.gis.PelanGISDAO;
import etanah.model.HakmilikPermohonanB1;
import etanah.model.KodBandarPekanMukim;
import etanah.model.KodDaerah;
import etanah.model.KodNegeri;
import etanah.model.KodSeksyen;
import etanah.model.NoPt;
import etanah.model.PermohonanPlotKpsn;
import etanah.model.gis.GISPelan;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.FileBean;
import net.sourceforge.stripes.action.HttpCache;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.StreamingResolution;
import net.sourceforge.stripes.action.UrlBinding;
import org.apache.commons.lang.StringUtils;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author john
 */
@HttpCache(allow = true)
@UrlBinding("/pelan/{jenisPelan}/{idPlot}/{id}")
public class PaparPelanActionBean extends AbleActionBean {

    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;
    @Inject
    etanah.Configuration conf;
    @Inject
    PelanGISDAO pelanGISDAO;
    @Inject
    KodDaerahDAO kodDaerahDAO;
    @Inject
    KodNegeriDAO kodNegeriDAO;
    @Inject
    KodSeksyenDAO kodSeksyenDAO;
    @Inject
    KodBandarPekanMukimDAO kodBandarPekanMukimDAO;
    @Inject
    PermohonanPlotPelanDAO permohonanPlotPelanDAO;
    @Inject
    PermohonanDAO mohonDao;
    @Inject
    NoPtDAO noPtDAO;
    @Inject
    HakmilikPermohonanB1DAO hakmilikPermohonanB1DAO;

    private FileBean file;
    private String jenisPelan;
    private String b1path;
    private String b2path;
    long id;
    long idPlot;

    @DefaultHandler
    public Resolution view() throws FileNotFoundException {
        id = Long.parseLong(getContext().getRequest().getParameter("id"));
        jenisPelan = getContext().getRequest().getParameter("jenisPelan");
        idPlot = Long.parseLong(getContext().getRequest().getParameter("idPlot"));
        PermohonanPlotKpsn kpsn = permohonanPlotPelanDAO.findById(idPlot).getPermohonanPlotKpsn();
        KodNegeri kn = kodNegeriDAO.findById(conf.getKodNegeri());
        KodDaerah kd = kpsn.getDaerah();
        KodBandarPekanMukim bpm = kpsn.getBpm();
        KodSeksyen seksyen1 = kpsn.getSeksyen();
        String noLot = "";
        if(jenisPelan.equals("B1")){
            HakmilikPermohonanB1 b1 = hakmilikPermohonanB1DAO.findById(id);
            noLot = b1.getNoLot();
        }else{
            NoPt nopt = noPtDAO.findById(id);
            noLot = ""+nopt.getNoPt();
        }
        String path = "";
        String sql = null;
        Session s = sessionProvider.get();
        Query q = null;

        sql = "select pelan from etanah.model.gis.GISPelan pelan  where "
                + "pelan.pelanGISPK.jenisPelan = :jenispelan "
                + "and pelan.pelanGISPK.kodDaerah.kod = :daerah  "
                + "and pelan.pelanGISPK.kodMukim =:mukim "
                + "and pelan.pelanGISPK.kodSeksyen = :seksyen ";

        sql = sql + "and pelan.pelanGISPK.noLot = :noLot";

        q = s.createQuery(sql);
        q.setString("daerah", kd.getKod());
        if (seksyen1 != null) {
            q.setString("seksyen", seksyen1.getSeksyen());
        } else {
            q.setString("seksyen", "000");
        }
        q.setString("mukim", bpm.getbandarPekanMukim());

        q.setString("noLot", noLot);

        q.setString("jenispelan", jenisPelan);
        GISPelan gis = (GISPelan) q.uniqueResult();
        if (gis.getPelanGISPK().getJenisPelan().equals("B1")) {
            b1path = conf.getPelanPath() + "B1" + gis.getPelanTif();
            path = b1path;

        } else {
            b2path = conf.getPelanPath() + "B2" + gis.getPelanTif();
            path = b2path;
        }

        File file = new File(path);
        FileInputStream fis = null;
        if (file.exists()) {
            fis = new FileInputStream(file);
        }
        return new StreamingResolution("image/tiff", fis).setFilename(file.getName());

    }

    public FileBean getFile() {
        return file;
    }

    public void setFile(FileBean file) {
        this.file = file;
    }

    public String getJenisPelan() {
        return jenisPelan;
    }

    public void setJenisPelan(String jenisPelan) {
        this.jenisPelan = jenisPelan;
    }

    public String getB1path() {
        return b1path;
    }

    public void setB1path(String b1path) {
        this.b1path = b1path;
    }

    public String getB2path() {
        return b2path;
    }

    public void setB2path(String b2path) {
        this.b2path = b2path;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getIdPlot() {
        return idPlot;
    }

    public void setIdPlot(long idPlot) {
        this.idPlot = idPlot;
    }
    
    

}
