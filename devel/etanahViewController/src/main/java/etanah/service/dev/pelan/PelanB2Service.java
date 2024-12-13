/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.service.dev.pelan;

import com.google.inject.Inject;
import com.wideplay.warp.persist.Transactional;
import etanah.dao.KodBandarPekanMukimDAO;
import etanah.dao.KodDaerahDAO;
import etanah.dao.KodNegeriDAO;
import etanah.dao.KodSeksyenDAO;
import etanah.dao.NoPtDAO;
import etanah.dao.PermohonanPlotKpsnDAO;
import etanah.dao.PermohonanPlotPelanDAO;
import etanah.dao.gis.GISPelanDAO;
import etanah.model.KodBandarPekanMukim;
import etanah.model.KodDaerah;
import etanah.model.KodNegeri;
import etanah.model.KodSekatanKepentingan;
import etanah.model.KodSeksyen;
import etanah.model.KodSyaratNyata;
import etanah.model.NoPt;
import etanah.model.PermohonanPlotKpsn;
import etanah.model.PermohonanPlotPelan;
import etanah.model.gis.GISPelan;
import etanah.model.gis.GISPelanPK;
import etanah.util.FileUtil;
import etanah.view.stripes.pembangunan.pelan.SelectCustom;
import etanah.view.stripes.pembangunan.pelan.SenaraiPlotPT;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang.StringUtils;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author john
 */
public class PelanB2Service {

    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;
    @Inject
    etanah.Configuration conf;
    @Inject
    KodNegeriDAO kodNegeriDAO;
    @Inject
    KodDaerahDAO kodDaerahDAO;
    @Inject
    KodSeksyenDAO kodSeksyenDAO;
    @Inject
    KodBandarPekanMukimDAO kodBandarPekanMukimDAO;
    @Inject
    GISPelanDAO pelanGISDAO;
    @Inject
    PermohonanPlotKpsnDAO permohonanPlotKpsnDAO;
    @Inject
    NoPtDAO noPtDAO;
    @Inject
    PermohonanPlotPelanDAO permohonanPlotPelanDAO;

    public List<SenaraiPlotPT> findSenaraiByidPlot(long idPlot) {
        List<SenaraiPlotPT> senaraiPlotPt = new ArrayList<SenaraiPlotPT>();
        List<NoPt> senaraiNoPt = findNoPtbyIdPlot(idPlot);
                PermohonanPlotKpsn kpsn = permohonanPlotPelanDAO.findById(idPlot).getPermohonanPlotKpsn();

        KodDaerah daerah = kpsn.getDaerah();
        KodBandarPekanMukim bpm = kpsn.getBpm();
        KodSeksyen seksyen1 = kpsn.getSeksyen();
       
        for (NoPt pt : senaraiNoPt) {
            SenaraiPlotPT ppt = new SenaraiPlotPT();
            GISPelan gispelan = getGISPelan(daerah.getKod(), bpm.getbandarPekanMukim(), seksyen1!=null?seksyen1.getSeksyen():"", String.valueOf(pt.getNoPt()), "B2");
            ppt.setLuas(pt.getLuasDilulus());
            ppt.setUnitLuas(pt.getKodUOM().getNama());
            ppt.setKodUnitLuas(pt.getKodUOM().getKod());
            ppt.setNoPT(pt.getNoPt());
            if (gispelan != null) {
                ppt.setJenisPelan(gispelan.getPelanGISPK().getJenisPelan());
                ppt.setNoPelanAkui(gispelan.getNoPelanAkui());
                ppt.setPathPelan(setPath(gispelan));
                ppt.setNoLot(gispelan.getPelanGISPK().getNoLot());
            }
            ppt.setBumi(pt.getBumi());
            if (pt.getKodSekatanKepentingan() != null) {
                ppt.setSekatan(pt.getKodSekatanKepentingan().getKod());
            }
            if (pt.getKodSyaratNyata() != null) {
                ppt.setSyaratNyata(pt.getKodSyaratNyata().getKod());
            }
            ppt.setIdPt(pt.getIdPt());
            senaraiPlotPt.add(ppt);

        }

        return senaraiPlotPt;
    }

    public String setPath(GISPelan gispelan) {
        String path = "";
        String b1path = "";
        String b2path = "";
        if (gispelan.getPelanGISPK().getJenisPelan().equals("B1")) {
            b1path = conf.getPelanPath() + "B1" + gispelan.getPelanTif();
            path = b1path;

        } else {
            b2path = conf.getPelanPath() + "B2" + gispelan.getPelanTif();
            path = b2path;
        }
        return path;
    }

    public List<NoPt> findNoPtbyIdPlot(long idPlot) {
        String query = "Select p FROM etanah.model.NoPt p where p.permohonanPlotPelan.idPlot =:idPlot ";
        Query q = sessionProvider.get().createQuery(query);
        q.setLong("idPlot", idPlot);
        return q.list();
    }

    public List<PermohonanPlotPelan> findSenaraiPlotByIdPermohonan(String idPermohonan) {
        String query = "Select p FROM etanah.model.PermohonanPlotPelan p where "
                + "p.permohonan.idPermohonan = :idPermohonan and "
                + "((p.pemilikan.kod = 'K' and p.noPt='Y') or p.pemilikan.kod = 'H') order by p.noPlot";
        Query q = sessionProvider.get().createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        return q.list();
    }

    public void simpan(SenaraiPlotPT plotpt, String daerah, String mukim, String seksyen) throws IOException, Exception {

        Session s = sessionProvider.get();
        Transaction tx = s.beginTransaction();
        GISPelan pelan = new GISPelan();
        GISPelanPK pk = new GISPelanPK();
        KodNegeri kn = kodNegeriDAO.findById(conf.getKodNegeri());
        KodDaerah kd = kodDaerahDAO.findById(daerah);
        KodBandarPekanMukim bpm = kodBandarPekanMukimDAO.findById(Integer.parseInt(mukim));
        KodSeksyen seksyen1 = null;
        if (StringUtils.isNotBlank(seksyen)) {
            seksyen1 = kodSeksyenDAO.findById(Integer.parseInt(seksyen));
            pk.setKodSeksyen(String.valueOf(seksyen1.getSeksyen()));
        } else {
            pk.setKodSeksyen("000");

        }
        pk.setKodNegeri(kn);
        pk.setKodDaerah(kd);
        pk.setKodMukim(bpm.getbandarPekanMukim());

        pk.setNoLot(plotpt.getNoLot());
        pk.setJenisPelan(plotpt.getJenisPelan());
        pelan.setPelanGISPK(pk);

        pelan.setLuas(plotpt.getLuas());
        pelan.setNoPelanAkui(plotpt.getNoPelanAkui());
//        pelan.setPelanTif(pelantif);
        pelan.setUnitUkur(plotpt.getUnitLuas());
        pelan.setTrhKemaskini(new Date());
        pelan.setPelanTif(File.separator + kd.getKod() + File.separator + bpm.getbandarPekanMukim() + (StringUtils.isNotBlank(seksyen) ? File.separator + seksyen1.getSeksyen() : "") + File.separator + plotpt.getFileBean().getFileName().toLowerCase());
//        pelan.setTrhKemaskini(new Date());
        pelanGISDAO.saveOrUpdate(pelan);
        String loc = conf.getPelanPath() + File.separator + pelan.getPelanGISPK().getJenisPelan() + File.separator + kd.getKod() + File.separator + bpm.getbandarPekanMukim() + (StringUtils.isNotBlank(seksyen) ? File.separator + seksyen1.getSeksyen() : "");
        FileUtil fileUtil = new FileUtil(loc);
        System.out.print("sssss" + plotpt.getFileBean().getFileName().toLowerCase());
        fileUtil.saveFile(plotpt.getFileBean().getFileName().toLowerCase(), plotpt.getFileBean().getInputStream());
        tx.commit();

    }

    private GISPelan getGISPelan(String daerah, String mukim, String seksyen,
            String noLot, String jenispelan) {
        Map<GISPelan, String> m = new HashMap<GISPelan, String>();
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
        q.setString("daerah", daerah);
        if (StringUtils.isNotBlank(seksyen)) {
            q.setString("seksyen", seksyen);
        } else {
            q.setString("seksyen", "000");
        }
        q.setString("mukim", mukim);

        q.setString("noLot", noLot);

        q.setString("jenispelan", jenispelan);

        GISPelan gis = (GISPelan) q.uniqueResult();

        return gis;
    }

    public List<SelectCustom> findCustomSyaratNyataByIdPlot(long idPlot) {
        List<SelectCustom> list = new ArrayList<SelectCustom>();
        for (KodSyaratNyata ks : findSyaratNyataByIdPlot(idPlot)) {
            SelectCustom sc = new SelectCustom();
            sc.setKod(ks.getKod());
            sc.setNama(ks.getKod() + " - " + ks.getSyarat());
            list.add(sc);
        }
        return list;
    }

    public List<SelectCustom> findCustomSekatanByIdPlot(long idPlot) {
        List<SelectCustom> list = new ArrayList<SelectCustom>();
        for (KodSekatanKepentingan ks : findSekatanByIdPlot(idPlot)) {
            SelectCustom sc = new SelectCustom();
            sc.setKod(ks.getKod());
            sc.setNama(ks.getKod() + " - " + ks.getSekatan());
            list.add(sc);
        }
        return list;
    }

    public List<KodSekatanKepentingan> findSekatanByIdPlot(long idPlot) {
        String query = "Select p FROM etanah.model.KodSekatanKepentingan p, etanah.model.PermohonanPlotSekatan s where "
                + "s.permohonanPlotPelan.idPlot = :idPlot and "
                + "s.kodSekatanKepentingan.kod = p.kod";
        Query q = sessionProvider.get().createQuery(query);
        q.setLong("idPlot", idPlot);
        return q.list();
    }

    public List<KodSyaratNyata> findSyaratNyataByIdPlot(long idPlot) {
        String query = "Select p FROM etanah.model.KodSyaratNyata p , etanah.model.PermohonanPlotSyaratNyata s where "
                + "s.permohonanPlotPelan.idPlot = :idPlot and "
                + "s.kodSyaratNyata.kod = p.kod";
        Query q = sessionProvider.get().createQuery(query);
        q.setLong("idPlot", idPlot);
        return q.list();
    }

    @Transactional
    public void save(PermohonanPlotKpsn plotKpsn) {
        permohonanPlotKpsnDAO.save(plotKpsn);
    }

    @Transactional
    public void saveNoPt(NoPt pt) {
        noPtDAO.save(pt);
    }

    @Transactional
    public void savePermohonanPlotPelan(PermohonanPlotPelan p) {
        permohonanPlotPelanDAO.save(p);
    }

}
