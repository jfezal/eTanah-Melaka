/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package etanah.view.daftar.utiliti;

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
import etanah.dao.PermohonanDAO;
import etanah.dao.HakmilikDAO;
import etanah.model.HakmilikPermohonan;
import etanah.view.etanahActionBeanContext;
import net.sourceforge.stripes.action.RedirectResolution;

/**
 *
 * @author fikri
 */
@UrlBinding("/daftar/pertanyaan_notis")
public class PertanyaanNotisActionBean extends AbleActionBean{

    private String idPerserahan;
    private String idHakmilik;
    private String idNotis;
    private String jenisNotis;
    private String kodNegeri;
    private Notis notis;
    private List<Notis> listNotis;
    @Inject
    PengambilanService pengambilanService;
    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    HakmilikDAO hakmilikDAO;
    HakmilikPermohonan hakmilikPermohonan;

    
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

    private Map<String, String> map;
     private List<Map>listMap = new ArrayList<Map>();
    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;    
    private static Logger LOG = Logger.getLogger(PertanyaanNotisActionBean.class);
    @Inject
    private etanah.Configuration conf;
    
    @DefaultHandler
    public Resolution showForm(){
        LOG.debug("--- Pertanyaan notis main ---");
        if ("04".equals(conf.getProperty("kodNegeri"))) {
            kodNegeri = "Mlk";
        }
        if ("05".equals(conf.getProperty("kodNegeri"))) {
            kodNegeri = "N9";
        }
        LOG.info("--- kod negeri= " + kodNegeri);
        return new JSP("daftar/utiliti/carian_notis.jsp");
    }    

    public Resolution searchNotis(){
        StringBuilder hql = new StringBuilder("Select p.idPermohonan, hp.hakmilik.idHakmilik, n.warta.hakmilik.noLot, n.warta.hakmilik.lot.nama, d.idDokumen, p.kodUrusan.nama");
            hql.append(",to_char(d.infoAudit.tarikhMasuk, 'DD-MM-YYYY HH:MI:SS AM') , d.kodDokumen.kod ,");
            hql.append(" from etanah.model.Permohonan p, etanah.model.HakmilikPermohonan hp, etanah.model.Notis n");
            hql.append(",etanah.model.KandunganFolder kf, etanah.model.Dokumen d");
            hql.append(" where hp.permohonan.idPermohonan = p.idPermohonan");
            hql.append(" and p.folderDokumen.folderId = kf.folder.folderId");
            hql.append(" and kf.dokumen.idDokumen = d.idDokumen");


        if(StringUtils.isNotBlank(idPerserahan)) {
            hql.append(" and p.idPermohonan = '" + idPerserahan + "'");
        }LOG.debug("--------idPerserahan = " + idPerserahan);
            
        if(StringUtils.isNotBlank(idHakmilik)) {
            hql.append(" and hp.hakmilik.idHakmilik = '" + idHakmilik + "'");
        }LOG.debug("--------idHakmilik = " + idHakmilik);
            
        if(StringUtils.isNotBlank(idNotis)) {
            hql.append(" and d.idDokumen = '" + idNotis + "'");
        }LOG.debug("--------idNotis = " + idNotis);

        if (StringUtils.isNotBlank(jenisNotis)) {
            hql.append(" and d.kodDokumen.kod = '" + jenisNotis + "'");
        }LOG.debug("--------jenisNotis = " + jenisNotis);

       

         LOG.debug("hql = " + hql.toString());

        Query query = sessionProvider.get().createQuery(hql.toString());
        Iterator iter =  query.iterate();
        map = new HashMap<String,String>();

        LOG.debug ("--- Map --"+map);
        listMap = new ArrayList<Map>();
        while(iter.hasNext()) {
            map = new HashMap<String,String>();
		  Object[] row = (Object[]) iter.next();
                  int count = 0;
                  for (Object object : row) {
                      LOG.debug("object =" + object.toString());
                      if(count == 0) map.put("idMohon", object.toString());
                      if(count == 1) map.put("idHakmilik", object.toString());
                      if(count == 2) map.put("noLot", object.toString());
                      if(count == 3) map.put("namaLot", object.toString());
                      if(count == 4) map.put("idDokumen", object.toString());
                      if(count == 5) map.put("urusan", object.toString());
                      if(count == 6) map.put("trhNotis", object.toString());
                      if(count == 7) map.put("kodDokumen", object.toString());
                      count ++;
            }
                  listMap.add(map);
 
	}

        if(map.isEmpty()) addSimpleError("Tiada Rekod Dijumpai.");
        return showForm();
    }


    public Resolution cariIdSebelumOSebab() {
        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPerserahan");
        String idHakmilik = (String) getContext().getRequest().getSession().getAttribute("idHakmilik");
        String kod = (String) getContext().getRequest().getSession().getAttribute("jenisNotis");

//        Permohonan permohonan=permohonanDAO.findById(idPermohonan);


//        listNotis = new ArrayList<Notis>();
        LOG.info("cariIdSebelumOSebab");
        LOG.info("idPermohonan"+idPermohonan);
        LOG.info("idHakmilik"+idHakmilik);
        LOG.info("kod"+kod);
          if (idPermohonan != null || idHakmilik != null || kod != null) {
            LOG.info("buat searching");
            listNotis = pengambilanService.searchNotis1(idPermohonan, idHakmilik, kod);
            LOG.info("size listNotis"+listNotis.size());
            if (listNotis.size() < 1) {
                addSimpleError("Notis Tidak Dijumpai");
            }
        }
        listNotis = pengambilanService.searchNotis1(idPermohonan, idHakmilik, kod);
        return new JSP("daftar/utiliti/carian_notis.jsp");
    }


  public Resolution searchNotis1(){
      Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
      String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPerserahan");
      String idHakmilik2 = (String) getContext().getRequest().getSession().getAttribute("idHakmilik");


      if(idPerserahan!=null||idHakmilik!=null)
      {
          if(idPerserahan!=null)
          {
          LOG.info("id Permohonan x null"+idPerserahan);
          Permohonan p=permohonanDAO.findById(idPerserahan);
          if(!p.getCawangan().getKod().equals(peng.getKodCawangan().getKod()))
          {
             addSimpleError("Sila Masukkan Maklumat Hakmilik/Id Perserahan yang berkaitan sahaja");
             refreshpage();
          }
          else
          {

          LOG.info("Kod Cawangan pengguna"+peng.getKodCawangan().getKod());
            StringBuilder hql = new StringBuilder("Select distinct n.permohonan.idPermohonan, hp.hakmilik.idHakmilik, n.warta.hakmilik.noLot, n.warta.hakmilik.lot.nama,n.permohonan.kodUrusan.nama");
            hql.append(",to_char(n.infoAudit.tarikhMasuk, 'DD-MM-YYYY HH:MI:SS AM'), n.kodNotis.kod");
            hql.append(" from etanah.model.HakmilikPermohonan hp, etanah.model.Notis n,etanah.model.Pengguna p");
            hql.append(" where hp.permohonan.idPermohonan = n.permohonan.idPermohonan");


        if(StringUtils.isNotBlank(idPerserahan)) {
            hql.append(" and n.permohonan.idPermohonan = '" + idPerserahan + "'");
        }LOG.debug("--------idPerserahan = " + idPerserahan);

        if(StringUtils.isNotBlank(idHakmilik)) {
            hql.append(" and hp.hakmilik.idHakmilik = '" + idHakmilik + "'");
        }LOG.debug("--------idHakmilik = " + idHakmilik);

        if (StringUtils.isNotBlank(jenisNotis)) {
            hql.append(" and n.kodNotis.kod = '" + jenisNotis + "'");
        }LOG.debug("--------jenisNotis = " + jenisNotis);



         LOG.debug("hql = " + hql.toString());

        Query query = sessionProvider.get().createQuery(hql.toString());
        Iterator iter =  query.iterate();
        map = new HashMap<String,String>();

        LOG.debug ("--- Map --"+map);
        listMap = new ArrayList<Map>();
        while(iter.hasNext()) {
            map = new HashMap<String,String>();
		  Object[] row = (Object[]) iter.next();
                  int count = 0;
                  for (Object object : row) {
                      LOG.debug("object =" + object.toString());
                      if(count == 0) map.put("idMohon", object.toString());
                      if(count == 1) map.put("idHakmilik", object.toString());
                      if(count == 2) map.put("noLot", object.toString());
                      if(count == 3) map.put("namaLot", object.toString());
                      if(count == 4) map.put("urusan", object.toString());
                      if(count == 5) map.put("trhNotis", object.toString());
                      if(count == 6) map.put("kodNotis", object.toString());
//                      if(count == 5) map.put("kodDokumen", object.toString());
                      count ++;
            }
                  listMap.add(map);

	}

        if(map.isEmpty()) addSimpleError("Tiada Rekod Dijumpai.");
          }
          }
          if(idHakmilik!=null)
          {
          LOG.info("id Hakmilik x null"+idHakmilik);
          Hakmilik h=hakmilikDAO.findById(idHakmilik);
          if(!h.getCawangan().getKod().equals(peng.getKodCawangan().getKod()))
          {
             addSimpleError("Sila Masukkan Maklumat Hakmilik/Id Perserahan yang berkaitan sahaja");
             refreshpage();
          }
          else
          {
            LOG.info("Kod Cawangan pengguna"+peng.getKodCawangan().getKod());
            StringBuilder hql = new StringBuilder("Select distinct n.permohonan.idPermohonan, hp.hakmilik.idHakmilik,n.warta.hakmilik.noLot, n.warta.hakmilik.lot.nama, n.permohonan.kodUrusan.nama");
            hql.append(",to_char(n.infoAudit.tarikhMasuk, 'DD-MM-YYYY HH:MI:SS AM'), n.kodNotis.kod");
            hql.append(" from etanah.model.HakmilikPermohonan hp, etanah.model.Notis n,etanah.model.Pengguna p");
            hql.append(" where hp.permohonan.idPermohonan = n.permohonan.idPermohonan");


        if(StringUtils.isNotBlank(idPerserahan)) {
            hql.append(" and n.permohonan.idPermohonan = '" + idPerserahan + "'");
        }LOG.debug("--------idPerserahan = " + idPerserahan);

        if(StringUtils.isNotBlank(idHakmilik)) {
            hql.append(" and hp.hakmilik.idHakmilik = '" + idHakmilik + "'");
        }LOG.debug("--------idHakmilik = " + idHakmilik);

        if (StringUtils.isNotBlank(jenisNotis)) {
            hql.append(" and n.kodNotis.kod = '" + jenisNotis + "'");
        }LOG.debug("--------jenisNotis = " + jenisNotis);



         LOG.debug("hql = " + hql.toString());

        Query query = sessionProvider.get().createQuery(hql.toString());
        Iterator iter =  query.iterate();
        map = new HashMap<String,String>();

        LOG.debug ("--- Map --"+map);
        listMap = new ArrayList<Map>();
        while(iter.hasNext()) {
            map = new HashMap<String,String>();
		  Object[] row = (Object[]) iter.next();
                  int count = 0;
                  for (Object object : row) {
                      LOG.debug("object =" + object.toString());
                      if(count == 0) map.put("idMohon", object.toString());
                      if(count == 1) map.put("idHakmilik", object.toString());
                      if(count == 2) map.put("noLot", object.toString());
                      if(count == 3) map.put("namaLot", object.toString());
                      if(count == 4) map.put("urusan", object.toString());
                      if(count == 5) map.put("trhNotis", object.toString());
                      if(count == 6) map.put("kodNotis", object.toString());
//                      if(count == 5) map.put("kodDokumen", object.toString());
                      count ++;
            }
                  listMap.add(map);

	}

           if(map.isEmpty()) addSimpleError("Tiada Rekod Dijumpai.");



          }
          }


      }
      else if(jenisNotis!=null && idPerserahan==null && idHakmilik==null)
      {
            LOG.info("Kod Cawangan pengguna"+peng.getKodCawangan().getKod());
            StringBuilder hql = new StringBuilder("Select distinct n.permohonan.idPermohonan, hp.hakmilik.idHakmilik,n.warta.hakmilik.noLot, n.warta.hakmilik.lot.nama, n.permohonan.kodUrusan.nama");
            hql.append(",to_char(n.infoAudit.tarikhMasuk, 'DD-MM-YYYY HH:MI:SS AM'), n.kodNotis.kod");
            hql.append(" from etanah.model.HakmilikPermohonan hp, etanah.model.Notis n,etanah.model.Pengguna p");
            hql.append(" where hp.permohonan.idPermohonan = n.permohonan.idPermohonan");


        if(StringUtils.isNotBlank(idPerserahan)) {
            hql.append(" and n.permohonan.idPermohonan = '" + idPerserahan + "'");
        }LOG.debug("--------idPerserahan = " + idPerserahan);

        if(StringUtils.isNotBlank(idHakmilik)) {
            hql.append(" and hp.hakmilik.idHakmilik = '" + idHakmilik + "'");
           
        }LOG.debug("--------idHakmilik = " + idHakmilik);
         LOG.debug("--------hp.hakmilik.cawangan.kod = " + peng.getKodCawangan().getKod());

        if (StringUtils.isNotBlank(jenisNotis)) {
            hql.append(" and n.kodNotis.kod = '" + jenisNotis + "'");
            hql.append(" and hp.hakmilik.cawangan.kod ='" + peng.getKodCawangan().getKod() + "'");
        }LOG.debug("--------jenisNotis = " + jenisNotis);



         LOG.debug("hql = " + hql.toString());

        Query query = sessionProvider.get().createQuery(hql.toString());
        Iterator iter =  query.iterate();
        map = new HashMap<String,String>();

        LOG.debug ("--- Map --"+map);
        listMap = new ArrayList<Map>();
        while(iter.hasNext()) {
            map = new HashMap<String,String>();
		  Object[] row = (Object[]) iter.next();
                  int count = 0;
                  for (Object object : row) {
                      LOG.debug("object =" + object.toString());
                      if(count == 0) map.put("idMohon", object.toString());
                      if(count == 1) map.put("idHakmilik", object.toString());
                      if(count == 2) map.put("noLot", object.toString());
                      if(count == 3) map.put("namaLot", object.toString());
                      if(count == 4) map.put("urusan", object.toString());
                      if(count == 5) map.put("trhNotis", object.toString());
                      if(count == 6) map.put("kodNotis", object.toString());
//                      if(count == 5) map.put("kodDokumen", object.toString());
                      count ++;
            }
                  listMap.add(map);

	}

        if(map.isEmpty()) addSimpleError("Tiada Rekod Dijumpai.");
      
      
      
      
      }


      
        
        return showForm();
    }

   public Resolution refreshpage() {
        showForm();
        return new RedirectResolution(PertanyaanNotisActionBean.class, "locate");
    }

























    public String getIdHakmilik() {
        return idHakmilik;
    }

    public void setIdHakmilik(String idHakmilik) {
        this.idHakmilik = idHakmilik;
    }

    public String getIdNotis() {
        return idNotis;
    }

    public void setIdNotis(String idNotis) {
        this.idNotis = idNotis;
    }

    public String getIdPerserahan() {
        return idPerserahan;
    }

    public void setIdPerserahan(String idPerserahan) {
        this.idPerserahan = idPerserahan;
    }

    public Map<String, String> getMap() {
        return map;
    }

    public void setMap(Map<String, String> map) {
        this.map = map;
    }

    public String getJenisNotis() {
        return jenisNotis;
    }

    public void setJenisNotis(String jenisNotis) {
        this.jenisNotis = jenisNotis;
    }
    
    public String getKodNegeri() {
        return kodNegeri;
    }

    public void setKodNegeri(String kodNegeri) {
        this.kodNegeri = kodNegeri;
    }

    public List<Map> getListMap() {
        return listMap;
    }

    public void setListMap(List<Map> listMap) {
        this.listMap = listMap;
    }
    public List<Notis> getListNotis() {
        return listNotis;
    }

    public void setListNotis(List<Notis> listNotis) {
        this.listNotis = listNotis;
    }

    public Notis getNotis() {
        return notis;
    }

    public void setNotis(Notis notis) {
        this.notis = notis;
    }
    public HakmilikPermohonan getHakmilikPermohonan() {
        return hakmilikPermohonan;
    }

    public void setHakmilikPermohonan(HakmilikPermohonan hakmilikPermohonan) {
        this.hakmilikPermohonan = hakmilikPermohonan;
    }


}
