/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.stripes.pengambilan;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import etanah.model.Notis;
import etanah.service.PengambilanService;
import etanah.model.Permohonan;
import etanah.model.Pengguna;
import etanah.model.Hakmilik;
import etanah.model.Pihak;
import etanah.dao.PermohonanDAO;
import etanah.dao.HakmilikDAO;
import etanah.model.HakmilikPermohonan;
import etanah.model.PermohonanPihak;
import etanah.model.ambil.HakmilikPerbicaraan;
import etanah.model.AmbilPampasan;
import etanah.model.ambil.PerbicaraanKehadiran;
import etanah.view.etanahActionBeanContext;
import net.sourceforge.stripes.action.RedirectResolution;

/**
 *
 * @author hazirah
 */
@UrlBinding("/pengambilan/carian_agensi")
public class CarianAgensiActionBean extends AbleActionBean {

    private String idMohon;
    private String idHakmilik;
    private String noKP;
    private String nama;
    private String jenisAgensi;
    private PerbicaraanKehadiran keterangan;
    private List<PerbicaraanKehadiran> listKeterangan;
//    private Agensi agensi;
//    private List<Agensi> listAgensi;
    private String kodNegeri;
    private String pampasan;
    private Notis notis;
    private List<Notis> listNotis;
    @Inject
    PengambilanService pengambilanService;
    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    HakmilikDAO hakmilikDAO;
    PermohonanPihak permohonanPihak;
    HakmilikPermohonan hakmilikPermohonan;
    HakmilikPerbicaraan hakmilikPerbicaraan;
    AmbilPampasan ambilPampasan;
    PerbicaraanKehadiran perbicaraanKehadiran;
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    private Map<String, String> map;
    private List<Map> listMap = new ArrayList<Map>();
    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;
    private static Logger LOG = Logger.getLogger(CarianAgensiActionBean.class);
    @Inject
    private etanah.Configuration conf;
    private List<PermohonanPihak> listPermohonanPihak;
    private List<AmbilPampasan> listAmbilPampasan;

    @DefaultHandler
    public Resolution showForm() {
        LOG.debug("--- Carian agensi main ---");
        if ("04".equals(conf.getProperty("kodNegeri"))) {
            kodNegeri = "Mlk";
        }
        if ("05".equals(conf.getProperty("kodNegeri"))) {
            kodNegeri = "N9";
        }
        LOG.info("--- kod negeri= " + kodNegeri);
        return new JSP("pengambilan/negerisembilan/carian_agensi.jsp");
    }

    public Resolution searchAgensi() {
        StringBuilder hql = new StringBuilder("Select p.idPermohonan, mp.hakmilik.idHakmilik, ph.nama, ap.jumTerimaPampasan");
//            hql.append(",to_char(d.infoAudit.tarikhMasuk, 'DD-MM-YYYY HH:MI:SS')");
        hql.append(" from etanah.model.Permohonan p, etanah.model.PermohonanPihak mp, etanah.model.Pihak ph, etanah.model.AmbilPampasan ap");
        hql.append(",etanah.model.ambil.PerbicaraanKehadiran pk, etanah.model.KandunganFolder kf");
        hql.append(",etanah.model.KandunganFolder kf, etanah.model.Dokumen d");
        hql.append(" where hp.permohonan.idPermohonan = p.idPermohonan");
        hql.append(" and p.folderDokumen.folderId = kf.folder.folderId");
        hql.append(" and kf.dokumen.idDokumen = d.idDokumen");

//    public Resolution searchNotis(){
//        StringBuilder hql = new StringBuilder("Select p.idPermohonan, hp.hakmilik.idHakmilik, d.idDokumen, p.kodUrusan.nama");
//            hql.append(",to_char(d.infoAudit.tarikhMasuk, 'DD-MM-YYYY HH:MI:SS') , d.kodDokumen.kod");
//            hql.append(" from etanah.model.Permohonan p, etanah.model.HakmilikPermohonan hp, etanah.model.Notis n");
//            hql.append(",etanah.model.KandunganFolder kf, etanah.model.Dokumen d");
//            hql.append(" where hp.permohonan.idPermohonan = p.idPermohonan");
//            hql.append(" and p.folderDokumen.folderId = kf.folder.folderId");
//            hql.append(" and kf.dokumen.idDokumen = d.idDokumen");


        if (StringUtils.isNotBlank(idMohon)) {
            hql.append(" and p.idPermohonan = '" + idMohon + "'");
        }
        LOG.debug("--------idMohon = " + idMohon);

        if (StringUtils.isNotBlank(idHakmilik)) {
            hql.append(" and mp.hakmilik.idHakmilik = '" + idHakmilik + "'");
        }
        LOG.debug("--------idHakmilik = " + idHakmilik);

        if (StringUtils.isNotBlank(noKP)) {
            hql.append(" and ph.noPengenalan = '" + noKP + "'");
        }
        LOG.debug("--------noPengenalan = " + noKP);

        if (StringUtils.isNotBlank(nama)) {
            hql.append(" and ph.nama = '" + nama + "'");
        }
        LOG.debug("--------nama = " + nama);

        if (StringUtils.isNotBlank(jenisAgensi)) {
            hql.append(" and pk.keterangan = '" + jenisAgensi + "'");
        }
        LOG.debug("--------agensi = " + jenisAgensi);

        if (StringUtils.isNotBlank(pampasan)) {
            hql.append(" and ap.jumTerimaPampasan = '" + pampasan + "'");
        }
        LOG.debug("--------pampasan = " + pampasan);



        LOG.debug("hql = " + hql.toString());

        Query query = sessionProvider.get().createQuery(hql.toString());
        Iterator iter = query.iterate();
        map = new HashMap<String, String>();

        LOG.debug("--- Map --" + map);
        listMap = new ArrayList<Map>();
        while (iter.hasNext()) {
            map = new HashMap<String, String>();
            Object[] row = (Object[]) iter.next();
            int count = 0;
            for (Object object : row) {
                LOG.debug("object =" + object.toString());
                if (count == 0) {
                    map.put("idMohon", object.toString());
                }
                if (count == 1) {
                    map.put("idHakmilik", object.toString());
                }
                if (count == 2) {
                    map.put("nama", object.toString());
                }
                if (count == 3) {
                    map.put("pampasan", object.toString());
                }

                count++;
            }
            listMap.add(map);
//
        }
//
        if (map.isEmpty()) {
            addSimpleError("Tiada Rekod Dijumpai.");
        }
        return showForm();
    }

    public Resolution cariIdSebelumOSebab() {
        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idMohon");
        String idHakmilik = (String) getContext().getRequest().getSession().getAttribute("idHakmilik");
        String noKP = (String) getContext().getRequest().getSession().getAttribute("noKP");
        String nama = (String) getContext().getRequest().getSession().getAttribute("nama");
        String jenisAgensi = (String) getContext().getRequest().getSession().getAttribute("jenisAgensi");

//        Permohonan permohonan=permohonanDAO.findById(idPermohonan);
//
//
//        listNotis = new ArrayList<Notis>();
        LOG.info("cariIdSebelumOSebab");
        LOG.info("idPermohonan" + idPermohonan);
        LOG.info("idHakmilik" + idHakmilik);
        LOG.info("keterangan" + jenisAgensi);
        if (idPermohonan != null || idHakmilik != null || jenisAgensi != null) {
            LOG.info("buat searching");
            listKeterangan = pengambilanService.searchAgensi1(idPermohonan, idHakmilik, noKP, nama, jenisAgensi);
            LOG.info("size listKeterangan" + listKeterangan.size());
            if (listKeterangan.size() < 1) {
                addSimpleError("Agensi Tidak Dijumpai");
            }
        }
        listKeterangan = pengambilanService.searchAgensi1(idPermohonan, idHakmilik, noKP, nama, jenisAgensi);
        return new JSP("pengambilan/negerisembilan/carian_agensi.jsp");

    }

    public Resolution searchAgensi1() {
        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        String idPermohonan = (String) getContext().getRequest().getParameter("idMohon");
//      String idHakmilik2 = (String) getContext().getRequest().getSession().getAttribute("idHakmilik");
        String idHakmilik = (String) getContext().getRequest().getParameter("idHakmilik");
        String noKP = (String) getContext().getRequest().getParameter("noKP");
        String nama = (String) getContext().getRequest().getParameter("nama");
        String jenisAgensi = (String) getContext().getRequest().getParameter("jenisAgensi");
        String pampasan = (String) getContext().getRequest().getParameter("pampasan");


        if (idPermohonan != null || idHakmilik != null) {
            if (idPermohonan != null) {
                LOG.info("id Permohonan x null" + idPermohonan);
                Permohonan p = permohonanDAO.findById(idPermohonan);
//          PermohonanPihak pr=permohonanPihak.setPermohonan(idPermohonan);
//          if(!p.getIdPermohonan().equals(pr.getPermohonan().getIdPermohonan()))
//          if(!p.getCawangan().getKod().equals(peng.getKodCawangan().getKod()))
                if (p == null) {
                    addSimpleError("Sila Masukkan Maklumat Hakmilik/Id Permohonan yang berkaitan sahaja");
                    refreshpage();
                } else {

                    LOG.info("Kod Cawangan pengguna" + idPermohonan);
                    StringBuilder hql = new StringBuilder("Select distinct p.idPermohonan, pr.hakmilik.idHakmilik, ph.nama, ap.jumTerimaPampasan");
                    hql.append(" from etanah.model.Permohonan p,etanah.model.PermohonanPihak pr,etanah.model.Pihak ph,etanah.model.ambil.PerbicaraanKehadiran pk ");
                    hql.append(",etanah.model.AmbilPampasan ap");
                    hql.append(" where pk.idKehadiran = ap.perbicaraanKehadiran.idKehadiran");


                    if (StringUtils.isNotBlank(idPermohonan)) {
                        hql.append(" and p.idPermohonan = '" + idPermohonan + "'");
                    }
                    LOG.debug("--------idPermohonan = " + idPermohonan);

                    if (StringUtils.isNotBlank(idHakmilik)) {
                        hql.append(" and pr.hakmilik.idHakmilik = '" + idHakmilik + "'");
                    }
                    LOG.debug("--------idHakmilik = " + idHakmilik);

                    if (StringUtils.isNotBlank(jenisAgensi)) {
                        hql.append(" and pk.keterangan = '" + jenisAgensi + "'");
                    }
                    LOG.debug("--------agensi = " + jenisAgensi);

                    if (StringUtils.isNotBlank(pampasan)) {
                        hql.append(" and ap.jumTerimaPampasan = '" + pampasan + "'");
                    }
                    LOG.debug("--------pampasan = " + pampasan);



                    LOG.debug("hql = " + hql.toString());

                    Query query = sessionProvider.get().createQuery(hql.toString());
                    Iterator iter = query.iterate();
                    map = new HashMap<String, String>();

                    LOG.debug("--- Map --" + map);
                    listMap = new ArrayList<Map>();
                    while (iter.hasNext()) {
                        map = new HashMap<String, String>();
                        Object[] row = (Object[]) iter.next();
                        int count = 0;
                        for (Object object : row) {
                            LOG.debug("object =" + object.toString());
                            if (count == 0) {
                                map.put("idMohon", object.toString());
                            }
                            if (count == 1) {
                                map.put("idHakmilik", object.toString());
                            }
                            if (count == 2) {
                                map.put("nama", object.toString());
                            }
                            if (count == 3) {
                                map.put("pampasan", object.toString());
                            }
//                      if(count == 5) map.put("kodDokumen", object.toString());
                            count++;
                        }
                        listMap.add(map);
                        System.out.println("::::::::::listMap : " + listMap.size());

                    }
//
                    if (map.isEmpty()) {
                        addSimpleError("Tiada Rekod Dijumpai.");
                    }
                }
            }
//            if (idHakmilik != null) {
//                LOG.info("id Hakmilik x null" + idHakmilik);
//                Hakmilik h = hakmilikDAO.findById(idHakmilik);
//                if (!h.getCawangan().getKod().equals(peng.getKodCawangan().getKod())) {
//                    addSimpleError("Sila Masukkan Maklumat Hakmilik/Id Permohonan yang berkaitan sahaja");
//                    refreshpage();
//                } else {
//                    LOG.info("Kod Cawangan pengguna" + peng.getKodCawangan().getKod());
//                    StringBuilder hql = new StringBuilder("Select distinct p.idPermohonan, mp.hakmilik.idHakmilik, ph.nama, ap.jumTerimaPampasan");
//                    hql.append(" from etanah.model.Permohonan p, etanah.model.PermohonanPihak mp, etanah.model.Pihak ph, etanah.model.AmbilPampasan ap");
//                    hql.append(",etanah.model.ambil.PebicaraanKehadiran pk");
////            hql.append(" where hp.permohonan.idPermohonan = p.permohonan.idPermohonan");
//
//
//                    if (StringUtils.isNotBlank(idPermohonan)) {
//                        hql.append(" and p.idPermohonan = '" + idPermohonan + "'");
//                    }
//                    LOG.debug("--------idPermohonan = " + idPermohonan);
//
//                    if (StringUtils.isNotBlank(idHakmilik)) {
//                        hql.append(" and mp.idHakmilik = '" + idHakmilik + "'");
//                    }
//                    LOG.debug("--------idHakmilik = " + idHakmilik);
//
//                    if (StringUtils.isNotBlank(jenisAgensi)) {
//                        hql.append(" and ph.keterangan = '" + jenisAgensi + "'");
//                    }
//                    LOG.debug("--------agensi = " + jenisAgensi);
//
//
//
//                    LOG.debug("hql = " + hql.toString());
//
//                    Query query = sessionProvider.get().createQuery(hql.toString());
//                    Iterator iter = query.iterate();
//                    map = new HashMap<String, String>();
//
//                    LOG.debug("--- Map --" + map);
//                    listMap = new ArrayList<Map>();
//                    while (iter.hasNext()) {
//                        map = new HashMap<String, String>();
//                        Object[] row = (Object[]) iter.next();
//                        int count = 0;
//                        for (Object object : row) {
//                            LOG.debug("object =" + object.toString());
//                            if (count == 0) {
//                                map.put("idMohon", object.toString());
//                            }
//                            if (count == 1) {
//                                map.put("idHakmilik", object.toString());
//                            }
//                            if (count == 2) {
//                                map.put("nama", object.toString());
//                            }
//                            if (count == 3) {
//                                map.put("pampasan", object.toString());
//                            }
////                      if(count == 5) map.put("kodDokumen", object.toString());
//                            count++;
//                        }
//                        listMap.add(map);
//
//                    }
//
//                    if (map.isEmpty()) {
//                        addSimpleError("Tiada Rekod Dijumpai.");
//                    }
//
//
//
//                }
//            }


        }
//        else if (jenisAgensi != null && idPermohonan == null && idHakmilik == null) {
//            LOG.info("Kod Cawangan pengguna" + peng.getKodCawangan().getKod());
//            StringBuilder hql = new StringBuilder("Select distinct p.idPermohonan, mp.hakmilik.idHakmilik, ph.nama, ap.jumTerimaPampasan");
//            hql.append(" from from etanah.model.Permohonan p, etanah.model.PermohonanPihak mp, etanah.model.Pihak ph, etanah.model.AmbilPampasan ap");
//            hql.append(",etanah.model.ambil.PebicaraanKehadiran pk");
////            hql.append(" where hp.permohonan.idPermohonan = p.permohonan.idPermohonan");
//
//
//            if (StringUtils.isNotBlank(idPermohonan)) {
//                hql.append(" and p.idPermohonan = '" + idPermohonan + "'");
//            }
//            LOG.debug("--------idPermohonan = " + idPermohonan);
//
//            if (StringUtils.isNotBlank(idHakmilik)) {
//                hql.append(" and mp.hakmilik.idHakmilik = '" + idHakmilik + "'");
//
//            }
////            LOG.debug("--------idHakmilik = " + idHakmilik);
////         LOG.debug("--------hp.hakmilik.cawangan.kod = " + peng.getKodCawangan().getKod());
////
////        if (StringUtils.isNotBlank(agensi)) {
////            hql.append(" and n.kodNotis.kod = '" + agensi + "'");
////            hql.append(" and hp.hakmilik.cawangan.kod ='" + peng.getKodCawangan().getKod() + "'");
////        }LOG.debug("--------jenisNotis = " + agensi);
//
//
//
//            LOG.debug("hql = " + hql.toString());
//
//            Query query = sessionProvider.get().createQuery(hql.toString());
//            Iterator iter = query.iterate();
//            map = new HashMap<String, String>();
//
//            LOG.debug("--- Map --" + map);
//            listMap = new ArrayList<Map>();
//            while (iter.hasNext()) {
//                map = new HashMap<String, String>();
//                Object[] row = (Object[]) iter.next();
//                int count = 0;
//                for (Object object : row) {
//                    LOG.debug("object =" + object.toString());
//                    if (count == 0) {
//                        map.put("idMohon", object.toString());
//                    }
//                    if (count == 1) {
//                        map.put("idHakmilik", object.toString());
//                    }
//                    if (count == 2) {
//                        map.put("nama", object.toString());
//                    }
//                    if (count == 3) {
//                        map.put("pampasan", object.toString());
//                    }
////                      if(count == 5) map.put("kodDokumen", object.toString());
//                    count++;
//                }
//                listMap.add(map);
//
//            }
//
//            if (map.isEmpty()) {
//                addSimpleError("Tiada Rekod Dijumpai.");
//            }
//
//
//        }

        return showForm();
    }

    public Resolution searchAgensi2() {
        String idPermohonan = (String) getContext().getRequest().getParameter("idMohon");
//      String idHakmilik2 = (String) getContext().getRequest().getSession().getAttribute("idHakmilik");
        String idHakmilik = (String) getContext().getRequest().getParameter("idHakmilik");
        String noKP = (String) getContext().getRequest().getParameter("noKP");
        String nama = (String) getContext().getRequest().getParameter("nama");
        String jenisAgensi = (String) getContext().getRequest().getParameter("jenisAgensi");
        String pampasan = (String) getContext().getRequest().getParameter("pampasan");


        if (idPermohonan != null || idHakmilik != null) {
            if (idPermohonan != null) {
                LOG.info("id Permohonan x null" + idPermohonan);
                Permohonan p = permohonanDAO.findById(idPermohonan);
//          PermohonanPihak pr=permohonanPihak.setPermohonan(idPermohonan);
//          if(!p.getIdPermohonan().equals(pr.getPermohonan().getIdPermohonan()))
//          if(!p.getCawangan().getKod().equals(peng.getKodCawangan().getKod()))
                if (p == null) {
                    addSimpleError("Sila Masukkan Maklumat Hakmilik/Id Permohonan yang berkaitan sahaja");
                    refreshpage();
                } else {
                    LOG.info("Kod Cawangan pengguna" + idPermohonan);

                    listAmbilPampasan = pengambilanService.searchPihak(idPermohonan, idHakmilik, noKP, nama, jenisAgensi);
                    System.out.println("::::::::::::: size listPermohonanPihak :" + listAmbilPampasan.size());

                    if (listAmbilPampasan.isEmpty()) {
                        addSimpleError("Tiada Rekod Dijumpai.");
                    }



                }
            }


        }

        return showForm();
    }

    public Resolution refreshpage() {
        showForm();
        return new RedirectResolution(CarianAgensiActionBean.class, "locate");
    }



    public String getJenisAgensi() {
        return jenisAgensi;
    }

    public void setJenisAgensi(String jenisAgensi) {
        this.jenisAgensi = jenisAgensi;
    }

//    public Agensi getAgensi() {
//        return agensi;
//    }
//
//    public void setAgensi(Agensi agensi) {
//        this.agensi = agensi;
//    }
    public String getIdHakmilik() {
        return idHakmilik;
    }

    public void setIdHakmilik(String idHakmilik) {
        this.idHakmilik = idHakmilik;
    }

    public String getIdMohon() {
        return idMohon;
    }

    public void setIdMohon(String idMohon) {
        this.idMohon = idMohon;
    }

    public String getKodNegeri() {
        return kodNegeri;
    }

    public void setKodNegeri(String kodNegeri) {
        this.kodNegeri = kodNegeri;
    }

    public List<Notis> getListAgensi() {
        return listNotis;
    }

    public void setListAgensi(List<Notis> listAgensi) {
        this.listNotis = listAgensi;
    }

    public List<Map> getListMap() {
        return listMap;
    }

    public void setListMap(List<Map> listMap) {
        this.listMap = listMap;
    }

    public Map<String, String> getMap() {
        return map;
    }

    public void setMap(Map<String, String> map) {
        this.map = map;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getNoKP() {
        return noKP;
    }

    public void setNoKP(String noKP) {
        this.noKP = noKP;
    }

    public String getPampasan() {
        return pampasan;
    }

    public void setPampasan(String pampasan) {
        this.pampasan = pampasan;
    }

    public List<PermohonanPihak> getListPermohonanPihak() {
        return listPermohonanPihak;
    }

    public void setListPermohonanPihak(List<PermohonanPihak> listPermohonanPihak) {
        this.listPermohonanPihak = listPermohonanPihak;
    }

    public List<AmbilPampasan> getListAmbilPampasan() {
        return listAmbilPampasan;
    }

    public void setListAmbilPampasan(List<AmbilPampasan> listAmbilPampasan) {
        this.listAmbilPampasan = listAmbilPampasan;
    }





//    public List<Map> getListMap() {
//        return listMap;
//    }
//
//    public void setListMap(List<Map> listMap) {
//        this.listMap = listMap;
//    }
    public PermohonanPihak getPermohonanPihak() {
        return permohonanPihak;
    }

    public void setPermohonanPihak(PermohonanPihak permohonanPihak) {
        this.permohonanPihak = permohonanPihak;
    }

    public AmbilPampasan getAmbilPampasan() {
        return ambilPampasan;
    }

    public void setAmbilPampasan(AmbilPampasan ambilPampasan) {
        this.ambilPampasan = ambilPampasan;
    }

    public HakmilikPerbicaraan getHakmilikPerbicaraan() {
        return hakmilikPerbicaraan;
    }

    public void setHakmilikPerbicaraan(HakmilikPerbicaraan hakmilikPerbicaraan) {
        this.hakmilikPerbicaraan = hakmilikPerbicaraan;
    }

    public HakmilikPermohonan getHakmilikPermohonan() {
        return hakmilikPermohonan;
    }

    public void setHakmilikPermohonan(HakmilikPermohonan hakmilikPermohonan) {
        this.hakmilikPermohonan = hakmilikPermohonan;
    }

    public PerbicaraanKehadiran getPerbicaraanKehadiran() {
        return perbicaraanKehadiran;
    }

    public void setPerbicaraanKehadiran(PerbicaraanKehadiran perbicaraanKehadiran) {
        this.perbicaraanKehadiran = perbicaraanKehadiran;
    }

}

