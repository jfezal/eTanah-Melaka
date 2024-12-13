/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.uam;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.model.Dokumen;
import etanah.model.KodDokumen;
import net.sourceforge.stripes.action.HttpCache;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import etanah.util.StringUtils;
import java.util.ArrayList;
import java.util.List;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import org.hibernate.Session;
import org.hibernate.Query;
import etanah.dao.KodDokumenDAO;

/**
 *
 * @author amir.muhaimin
 */
@HttpCache(allow = false)
@UrlBinding("/uam/carianKodDokumen")
public class CarianKodDokumenActionBean extends AbleActionBean {
    
    private final static org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(CarianKodDokumenActionBean.class);
    private String idkodDok;
    private String keteranganDok;
    private String rbDok;
    private List<KodDokumen> listKodDok = new ArrayList<KodDokumen>();
    private List<KodDokumen> listKodKeterangan = new ArrayList<KodDokumen>();
    private List<KodDokumen> listAll = new ArrayList<KodDokumen>();
    private boolean flag = false;
    private boolean flag1 = false;
    private boolean flag2 = false;
    private int sizeKod;
    private int paparan = 20;
    private static final String DEFAULT_VIEW = "/WEB-INF/jsp/uam/auditCapaianDokumen_1.jsp";
    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;
    @Inject
    KodDokumenDAO kodDokumenDAO;
    
    @DefaultHandler
    public Resolution showForm() {
        getContext().getRequest().setAttribute("simpan", Boolean.TRUE);
        getContext().getRequest().setAttribute("kemaskini", Boolean.FALSE);
        getContext().getRequest().setAttribute("id", Boolean.TRUE);
        getContext().getRequest().setAttribute("kataLaluan", Boolean.TRUE);
//        return new JSP("uam/auditCapaianDokumen_1.jsp");
        flag2 = true;
            listAll = kodDokumenDAO.findAll();
        return new JSP("uam/auditCapaianDokumen_1.jsp").addParameter("popup", "true");
    }
    
    public Resolution search() {
        paparan = (Integer) this.getPaparan();        
        if (idkodDok != null) {
            listKodDok = searchKodDokumen(idkodDok);
            flag = true;
            flag1 = false;
            sizeKod = listKodDok.size() > 0 ? listKodDok.size() : 0;
        } else if (keteranganDok != null) {
            listKodKeterangan = searchByKeterangan(keteranganDok);
            flag = false;
            flag1 = true;
            sizeKod = listKodKeterangan.size() > 0 ? listKodKeterangan.size() : 0;
        } else if (idkodDok == null && keteranganDok == null) {
            flag = false;
            flag1 = false;
            flag2 = true;
            listAll = kodDokumenDAO.findAll();
            logger.info("LIST ALL::::::" + listAll.size());
            sizeKod = listAll.size() > 0 ? listAll.size() : 0;
        }
        return new JSP("uam/auditCapaianDokumen_1.jsp").addParameter("popup", "true");
//        return new ForwardResolution(DEFAULT_VIEW);
    }
    
    public List<KodDokumen> searchKodDokumen(String idDok) {
        
        List<KodDokumen> list = new ArrayList<KodDokumen>();
        
        Session s = sessionProvider.get();
        Query q = s.createQuery("from etanah.model.KodDokumen u where u.kod = :nama");
        q.setString("nama", idDok);
        
        System.out.println("sql :" + q.getQueryString());
        list = q.list();
        logger.info("list.size() : " + list.size());
        
        return list;
    }
    
    public List<KodDokumen> searchByKeterangan(String idDok) {
        
        List<KodDokumen> list = new ArrayList<KodDokumen>();
        
        Session s = sessionProvider.get();
        Query q = s.createQuery("from etanah.model.KodDokumen u where u.nama LIKE :nama");
        q.setString("nama", "%" + idDok + "%");
        
        System.out.println("sql :" + q.getQueryString());
        list = q.list();
        logger.info("list.size() : " + list.size());
        
        return list;
    }
    
    public Resolution simpanPeguam() throws Exception {
        logger.info("Start Save....");
        return new ForwardResolution("Testing");
    }
    
    public String getIdkodDok() {
        return idkodDok;
    }
    
    public void setIdkodDok(String idkodDok) {
        this.idkodDok = idkodDok;
    }
    
    public List<KodDokumen> getListKodDok() {
        return listKodDok;
    }
    
    public void setListKodDok(List<KodDokumen> listKodDok) {
        this.listKodDok = listKodDok;
    }
    
    public List<KodDokumen> getListKodKeterangan() {
        return listKodKeterangan;
    }
    
    public void setListKodKeterangan(List<KodDokumen> listKodKeterangan) {
        this.listKodKeterangan = listKodKeterangan;
    }
    
    public List<KodDokumen> getListAll() {
        return listAll;
    }
    
    public void setListAll(List<KodDokumen> listAll) {
        this.listAll = listAll;
    }
    
    public boolean isFlag() {
        return flag;
    }
    
    public void setFlag(boolean flag) {
        this.flag = flag;
    }
    
    public boolean isFlag1() {
        return flag1;
    }
    
    public void setFlag1(boolean flag1) {
        this.flag1 = flag1;
    }
    
    public boolean isFlag2() {
        return flag2;
    }
    
    public void setFlag2(boolean flag2) {
        this.flag2 = flag2;
    }
    
    public String getKeteranganDok() {
        return keteranganDok;
    }
    
    public void setKeteranganDok(String keteranganDok) {
        this.keteranganDok = keteranganDok;
    }
    
    public String getRbDok() {
        return rbDok;
    }
    
    public void setRbDok(String rbDok) {
        this.rbDok = rbDok;
    }
    
    public int getSizeKod() {
        return sizeKod;
    }
    
    public void setSizeKod(int sizeKod) {
        this.sizeKod = sizeKod;
    }
    
    public int getPaparan() {
        return paparan;
    }
    
    public void setPaparan(int paparan) {
        this.paparan = paparan;
    }
}
