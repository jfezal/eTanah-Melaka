package etanah.view.utility;

import able.stripes.AbleActionBean;
import com.google.inject.Inject;
import etanah.model.KodAgensi;
import etanah.model.UlasanJabatanTeknikal;
import etanah.view.jtek.ws.JTEKServices;
import etanah.view.jtek.ws.JTEKServices2;
import java.util.ArrayList;
import java.util.List;
import javax.naming.NamingException;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;
import org.apache.log4j.Logger;
import org.hibernate.Session;

/**
 *
 * @author nurashidah
 */
@UrlBinding("/utility/ulasan_JTEK")
public class JTEKUtilityActionBean extends AbleActionBean {

    private static Logger logger = Logger.getLogger(JTEKUtilityActionBean.class);
    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;
    @Inject
    JTEKServices2 JTEKServices;
    private List<UlasanJabatanTeknikal> ulasanJTEK;
    private List<KodAgensi> senaraiKodAgensi;
    private String idMohon;
    private String kodAgensi;

    @DefaultHandler
    public Resolution showForm() {

        return new ForwardResolution("/WEB-INF/jsp/utiliti/listUlasanJTEK.jsp");
    }

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {
        logger.info("------------rehydrate--------------");
        ulasanJTEK = JTEKServices.getSenaraiUlasan();
        System.out.println("size Ulasan : " + ulasanJTEK.size());

        senaraiKodAgensi = JTEKServices.getSenaraiAgensiJTEK();
        System.out.println("size kodAgensi : " + senaraiKodAgensi.size());
    }

    public Resolution searchUlasan() throws NamingException {
        if (idMohon != null && kodAgensi != null) {
            System.out.println("search idMohon : " + idMohon + "kodAgensi " + kodAgensi);
            ulasanJTEK = new ArrayList<UlasanJabatanTeknikal>();
            ulasanJTEK.addAll(JTEKServices.searchIdMohonKodAgensi(idMohon, kodAgensi));

        } 
        else if (idMohon != null && kodAgensi == null) {
            System.out.println("search idMohon : " + idMohon);
            ulasanJTEK = new ArrayList<UlasanJabatanTeknikal>();
            ulasanJTEK.addAll(JTEKServices.searchIdMohon(idMohon));
        } 
        else if (idMohon == null && kodAgensi != null) {
            System.out.println("search kodAgensi " + kodAgensi);
            ulasanJTEK = new ArrayList<UlasanJabatanTeknikal>();
            ulasanJTEK.addAll(JTEKServices.searchkodAgensi(kodAgensi));
        }
        return new ForwardResolution("/WEB-INF/jsp/utiliti/listUlasanJTEK.jsp");
    }

    public List<UlasanJabatanTeknikal> getUlasanJTEK() {
        return ulasanJTEK;
    }

    public void setUlasanJTEK(List<UlasanJabatanTeknikal> ulasanJTEK) {
        this.ulasanJTEK = ulasanJTEK;
    }

    public List<KodAgensi> getSenaraiKodAgensi() {
        return senaraiKodAgensi;
    }

    public void setSenaraiKodAgensi(List<KodAgensi> senaraiKodAgensi) {
        this.senaraiKodAgensi = senaraiKodAgensi;
    }

    public String getIdMohon() {
        return idMohon;
    }

    public void setIdMohon(String idMohon) {
        this.idMohon = idMohon;
    }

    public String getKodAgensi() {
        return kodAgensi;
    }

    public void setKodAgensi(String kodAgensi) {
        this.kodAgensi = kodAgensi;
    }
}
