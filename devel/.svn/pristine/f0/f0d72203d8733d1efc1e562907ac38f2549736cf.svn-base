package etanah.view.stripes.hasil;

import able.stripes.AbleActionBean;
import com.google.inject.Inject;
import etanah.dao.hasil.TagAkaunDAO;
import etanah.model.*;
import etanah.model.hasil.TagAkaun;
import etanah.model.hasil.TagAkaunAhli;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author abu.mansur
 */
@UrlBinding("/hasil/view_remisyen_tanah")
public class ViewRemisyenActionBean extends AbleActionBean {
    private static final Logger LOG = Logger.getLogger(ViewRemisyenActionBean.class);

    private static String negeri;
    private String idKumpulan = null;
    private static String namaKumpulan = null;
    private static String idHakmilik = null;
    private static String noAkaun = null;
    private String idKumpulanP;
    private String kodCawP;
    private String namaKumpulanP;
    private String msg;
    private String peratusRemisyenTanah = null;
    private String peratusRemisyenTunggak = null;
    private String peratusRemisyenDenda = null;
    private String peratusRemisyenTunggakDenda = null;
    private String tahunRemisyen;
    private int tahunSkrg = 0;
    private List<TagAkaun> senaraiKumpulanAkaun;
    private List<Akaun> senaraiAkaun;
    private static List<Akaun> senaraiAkaunStatic;
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy");

    @Inject etanah.Configuration conf;
     @Inject TagAkaunDAO tagAkaunDAO;
     @Inject protected com.google.inject.Provider<Session> sessionProvider;

    @DefaultHandler
    public Resolution testingSearch() {

        if("05".equals(conf.getProperty("kodNegeri")))
            negeri = "sembilan";
        if("04".equals(conf.getProperty("kodNegeri")))
            negeri = "melaka";
        
        noAkaun = getContext().getRequest().getParameter("noAkaun");
        idHakmilik = getContext().getRequest().getParameter("hakmilik");
        idKumpulan = getContext().getRequest().getParameter("kumpulan");

        peratusRemisyenTanah = getContext().getRequest().getParameter("remTanah");
        peratusRemisyenTunggak = getContext().getRequest().getParameter("remTunggak");
        peratusRemisyenDenda = getContext().getRequest().getParameter("remDenda");
        peratusRemisyenTunggakDenda = getContext().getRequest().getParameter("remTunggakDenda");
        LOG.info("noAkaun : " + noAkaun);
        LOG.info("idHakmilik : " + idHakmilik);
        LOG.info("idKumpulan : " + idKumpulan);
        LOG.info("peratusRemisyenTanah : " + peratusRemisyenTanah);
        LOG.info("peratusRemisyenTunggak : " + peratusRemisyenTunggak);
        LOG.info("peratusRemisyenDenda : " + peratusRemisyenDenda);
        LOG.info("peratusRemisyenTunggakDenda : " + peratusRemisyenTunggakDenda);


        tahunSkrg = Integer.parseInt(sdf.format(new java.util.Date()));
        senaraiAkaun = new ArrayList<Akaun>();
        senaraiAkaunStatic = new ArrayList<Akaun>();
        if((!idKumpulan.equalsIgnoreCase("")) && ((idHakmilik.equalsIgnoreCase("")) ||(noAkaun.equalsIgnoreCase("")))){
            LOG.info("carian Kumpulan");
            cariKumpulan(idKumpulan);
            senaraiAkaunStatic = senaraiAkaun;
        }else if((idKumpulan.equalsIgnoreCase(""))&& ((!idHakmilik.equalsIgnoreCase("")) || (!noAkaun.equalsIgnoreCase("")))){
            LOG.info("carian individu");
//            cariIndividu(idHakmilik, noAkaun);
            senaraiAkaunStatic = cariIndividu(idHakmilik, noAkaun);
        }
        
        return new ForwardResolution("/WEB-INF/jsp/hasil/remisyen_tanah_1.jsp");
    }

    private void cariKumpulan(String kumpulan){
        LOG.info("kumpulan :"+kumpulan);
        senaraiAkaun = new ArrayList<Akaun>();
        try{
            TagAkaun tagAkaun = tagAkaunDAO.findById(kumpulan);
            for (TagAkaunAhli tagAhli : tagAkaun.getSenaraiAhli()) {
                senaraiAkaun.add(tagAhli.getAkaun());
                LOG.debug("idAhli :"+tagAhli.getIdAhli());
            }
            namaKumpulan = tagAkaun.getNama();
            LOG.debug("nama kumpulan :"+namaKumpulan);
        }catch(Exception ex){
            LOG.error("ID Kumpulan salah.");
            ex.printStackTrace(); // for development only
        }
    }

    private List<Akaun>  cariIndividu(String hakmilik, String akaun) {
        senaraiAkaun = new ArrayList<Akaun>();
        Session ses = sessionProvider.get();
        String sql = "select a from etanah.model.Akaun a where 1=1 ";
        if (!idHakmilik.equalsIgnoreCase("")) {sql += "and a.hakmilik.idHakmilik like :hakmilik ";}
        if (!noAkaun.equalsIgnoreCase("")) {sql += "and a.noAkaun like :akaun ";}
        LOG.info("sql :" + sql);

        Query q = ses.createQuery(sql);
        if (!idHakmilik.equalsIgnoreCase("")) {q.setString("hakmilik", "%" + hakmilik + "%");}
        if (!noAkaun.equalsIgnoreCase("")) {q.setString("akaun", "%" + akaun + "%");}
        senaraiAkaun = q.list();

        return senaraiAkaun;
    }

    public String getIdHakmilik() {
        return idHakmilik;
    }

    public void setIdHakmilik(String idHakmilik) {
        ViewRemisyenActionBean.idHakmilik = idHakmilik;
    }

    public String getIdKumpulan() {
        return idKumpulan;
    }

    public void setIdKumpulan(String idKumpulan) {
        this.idKumpulan = idKumpulan;
    }

    public String getIdKumpulanP() {
        return idKumpulanP;
    }

    public void setIdKumpulanP(String idKumpulanP) {
        this.idKumpulanP = idKumpulanP;
    }

    public String getKodCawP() {
        return kodCawP;
    }

    public void setKodCawP(String kodCawP) {
        this.kodCawP = kodCawP;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getNamaKumpulan() {
        return namaKumpulan;
    }

    public void setNamaKumpulan(String namaKumpulan) {
        ViewRemisyenActionBean.namaKumpulan = namaKumpulan;
    }

    public String getNamaKumpulanP() {
        return namaKumpulanP;
    }

    public void setNamaKumpulanP(String namaKumpulanP) {
        this.namaKumpulanP = namaKumpulanP;
    }

    public String getNegeri() {
        return negeri;
    }

    public void setNegeri(String negeri) {
        ViewRemisyenActionBean.negeri = negeri;
    }

    public String getNoAkaun() {
        return noAkaun;
    }

    public void setNoAkaun(String noAkaun) {
        ViewRemisyenActionBean.noAkaun = noAkaun;
    }

    public String getPeratusRemisyenDenda() {
        return peratusRemisyenDenda;
    }

    public void setPeratusRemisyenDenda(String peratusRemisyenDenda) {
        this.peratusRemisyenDenda = peratusRemisyenDenda;
    }

    public String getPeratusRemisyenTanah() {
        return peratusRemisyenTanah;
    }

    public void setPeratusRemisyenTanah(String peratusRemisyenTanah) {
        this.peratusRemisyenTanah = peratusRemisyenTanah;
    }

    public String getPeratusRemisyenTunggak() {
        return peratusRemisyenTunggak;
    }

    public void setPeratusRemisyenTunggak(String peratusRemisyenTunggak) {
        this.peratusRemisyenTunggak = peratusRemisyenTunggak;
    }

    public String getPeratusRemisyenTunggakDenda() {
        return peratusRemisyenTunggakDenda;
    }

    public void setPeratusRemisyenTunggakDenda(String peratusRemisyenTunggakDenda) {
        this.peratusRemisyenTunggakDenda = peratusRemisyenTunggakDenda;
    }

    public List<Akaun> getSenaraiAkaun() {
        return senaraiAkaun;
    }

    public void setSenaraiAkaun(List<Akaun> senaraiAkaun) {
        this.senaraiAkaun = senaraiAkaun;
    }

    public List<TagAkaun> getSenaraiKumpulanAkaun() {
        return senaraiKumpulanAkaun;
    }

    public void setSenaraiKumpulanAkaun(List<TagAkaun> senaraiKumpulanAkaun) {
        this.senaraiKumpulanAkaun = senaraiKumpulanAkaun;
    }

    public String getTahunRemisyen() {
        return tahunRemisyen;
    }

    public void setTahunRemisyen(String tahunRemisyen) {
        this.tahunRemisyen = tahunRemisyen;
    }

    public int getTahunSkrg() {
        return tahunSkrg;
    }

    public void setTahunSkrg(int tahunSkrg) {
        this.tahunSkrg = tahunSkrg;
    }
}
