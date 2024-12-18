/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package etanah.view.stripes.pembangunan;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.dao.HakmilikDAO;
import java.text.ParseException;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;
import etanah.model.Permohonan;
import etanah.dao.PermohonanDAO;
import etanah.model.Hakmilik;
import etanah.model.HakmilikPermohonan;
import etanah.service.RegService;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import net.sourceforge.stripes.action.RedirectResolution;
import etanah.model.KodSekatanKepentingan;
import etanah.model.KodSyaratNyata;
import org.apache.log4j.Logger;
import etanah.model.Pengguna;
import etanah.view.etanahActionBeanContext;
import etanah.model.KodPBT;
import etanah.view.ListUtil;
import etanah.model.KodLot;
import etanah.dao.KodLotDAO;
import etanah.service.PembangunanService;
import etanah.util.StringUtils;

/**
 *
 * @author NageswaraRao
 */
@UrlBinding("/pembangunan/carianSyaratSekatan")
public class CarianSyaratSekatanActionBean extends AbleActionBean {

    private static final Logger logger = Logger.getLogger(CarianSyaratSekatanActionBean.class);
    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    HakmilikDAO hakmilikDAO;
    @Inject
    KodLotDAO kodLotDAO;
    @Inject
    RegService regService;
    @Inject
    PembangunanService pembangunanService;
    @Inject
    ListUtil listUtil;
    @Inject
    etanah.Configuration conf;
    private Permohonan p;
    private Hakmilik hakmilik;
    private List<HakmilikPermohonan> hakmilikPermohonanList;
    private List<KodSekatanKepentingan> listKodSekatan;
    private List<KodSyaratNyata> listKodSyaratNyata;
    private List<KodPBT> senaraiKodPBT;
    private Date tarikhDaftar;
    private String tarikhDaftarAsal;
    private String tarikhLuput;
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    private String kodSekatan;
    private String kodSyaratNyata;
    private String syaratNyata;
    private String sekatan;
    private String kodDaerah;
    private String kodJenisHakmilik;
    private String pegangan;
    int tempohPegangan;
    private String kodNegeri;
    private List<KodLot> senaraiKodLot;
    private String jenisHakmilik;
    private String index;
    private String kodKatTanah;
    

    @DefaultHandler
    public Resolution showForm() {
        if (p.getSenaraiHakmilik().size() > 0) {
            Hakmilik h = p.getSenaraiHakmilik().get(0).getHakmilik();
            jenisHakmilik = h.getKodHakmilik().getKod();

        } else {
            addSimpleError("Sila Tambah hakmilik dahulu sebelum mengisi maklumat asas");
        }
        return new JSP("daftar/kemasukan_maklumat_asas.jsp").addParameter("tab", "true");
    }

    public Resolution showFormCariKodSekatan() {
        index = getContext().getRequest().getParameter("index");
        cariKodSekatan();
        return new JSP("pembangunan/pecahSempadan/dev_carian_kodsekatan.jsp").addParameter("tab", "true");
    }

    public Resolution showFormCariKodSyaratNyata() {
        index = getContext().getRequest().getParameter("index");
        cariKodSyaratNyata();
        return new JSP("pembangunan/pecahSempadan/dev_carian_kodsyaratnyata.jsp").addParameter("tab", "true");
    }

    public Resolution cariKodSekatan() {
        Pengguna peng = (Pengguna) context.getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        String kc = peng.getKodCawangan().getKod();
//        logger.debug("peng.getKodCawangan().getKod :" + kc);
//        logger.debug("kodSekatan :" + kodSekatan);
        if (kodSekatan != null) {
            listKodSekatan = pembangunanService.searchKodSekatan(kodSekatan, kc, sekatan);
            logger.debug("listKodSekatan.size :" + listKodSekatan.size());
            if (listKodSekatan.size() < 1) {
                addSimpleError("Kod Sekatan Tidak Sah");
            }
        } else {
            listKodSekatan = pembangunanService.searchKodSekatan("%", kc, sekatan);
            if (listKodSekatan.size() < 1) {
                addSimpleError("Kod Sekatan Tidak Sah");
            }
        }
        return new JSP("pembangunan/pecahSempadan/dev_carian_kodsekatan.jsp").addParameter("tab", "true");
    }

    public Resolution cariKodSyaratNyata() {
        Pengguna peng = (Pengguna) context.getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        String kc = peng.getKodCawangan().getKod();
//        logger.debug("kodSyaratNyata : " + kodSyaratNyata);
        if (kodSyaratNyata != null) {
            listKodSyaratNyata = pembangunanService.searchKodSyaratNyata(kodSyaratNyata, kc, syaratNyata);
            logger.debug("listKodSyaratNyata.size : " + listKodSyaratNyata.size());
            if (listKodSyaratNyata.size() < 1) {
                addSimpleError("Kod Syarat Nyata Tidak Sah");
            }
        } else {
            listKodSyaratNyata = pembangunanService.searchKodSyaratNyata("%", kc, syaratNyata);
//            addSimpleError("Sila Cari / Pilih Kod Syarat Nyata");
            if (listKodSyaratNyata.size() < 1) {
                addSimpleError("Kod Syarat Nyata Tidak Sah");
            }
        }

        return new JSP("pembangunan/pecahSempadan/dev_carian_kodsyaratnyata.jsp").addParameter("tab", "true");
    }


    // Added for MaklumatTukarSyarat for MaklumatTukarSyarat
    public Resolution showFormCariKodSyaratNyata2() {
        index = getContext().getRequest().getParameter("index");
//        cariKodSyaratNyata2();
        return new JSP("pembangunan/TS_PTD/carian_kodSyaratNyata.jsp").addParameter("tab", "true");
    }
    
    public Resolution showFormCariKodSekatan2() {
        index = getContext().getRequest().getParameter("index");
//        cariKodSekatan();
        return new JSP("pembangunan/TS_PTD/carian_kodSekatan.jsp").addParameter("tab", "true");
    }


    // Added for MaklumatTukarSyarat for MaklumatTukarSyarat
    public Resolution cariKodSyaratNyata2() {
        Pengguna peng = (Pengguna) context.getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        String kc = peng.getKodCawangan().getKod();
//        logger.debug("kodSyaratNyata : " + kodSyaratNyata);
        if (kodSyaratNyata != null) {
            logger.info("---cariKodSyaratNyata2---kodKatTanah-----:"+kodKatTanah);
            listKodSyaratNyata = regService.searchKodSyaratNyata(kodSyaratNyata, kc, syaratNyata,kodKatTanah);
            logger.debug("listKodSyaratNyata.size : " + listKodSyaratNyata.size());
            if (listKodSyaratNyata.size() < 1) {
                addSimpleError("Kod Syarat Nyata Tidak Sah");
            }
        } else {
            listKodSyaratNyata = regService.searchKodSyaratNyata("%", kc, syaratNyata,kodKatTanah);
//            addSimpleError("Sila Cari / Pilih Kod Syarat Nyata");
            if (listKodSyaratNyata.size() < 1) {
                addSimpleError("Kod Syarat Nyata Tidak Sah");
            }
        }
        return new JSP("pembangunan/TS_PTD/carian_kodSyaratNyata.jsp").addParameter("tab", "true");
    }


    public String getPegangan() {
        return pegangan;
    }

    public void setPegangan(String pegangan) {
        this.pegangan = pegangan;
    }

    public String getJenisHakmilik() {
        return jenisHakmilik;
    }

    public void setJenisHakmilik(String jenisHakmilik) {
        this.jenisHakmilik = jenisHakmilik;
    }

    public String getKodNegeri() {
        return kodNegeri;
    }

    public void setKodNegeri(String kodNegeri) {
        this.kodNegeri = kodNegeri;
    }

    public int getTempohPegangan() {
        return tempohPegangan;
    }

    public void setTempohPegangan(int tempohPegangan) {
        this.tempohPegangan = tempohPegangan;
    }

    public String getKodJenisHakmilik() {
        return kodJenisHakmilik;
    }

    public void setKodJenisHakmilik(String kodJenisHakmilik) {
        this.kodJenisHakmilik = kodJenisHakmilik;
    }

    public List<KodPBT> getSenaraiKodPBT() {
        return senaraiKodPBT;
    }

    public void setSenaraiKodPBT(List<KodPBT> senaraiKodPBT) {
        this.senaraiKodPBT = senaraiKodPBT;
    }

    public String getKodDaerah() {
        return kodDaerah;
    }

    public void setKodDaerah(String kodDaerah) {
        this.kodDaerah = kodDaerah;
    }

    public String getKodSyaratNyata() {
        return kodSyaratNyata;
    }

    public void setKodSyaratNyata(String kodSyaratNyata) {
        this.kodSyaratNyata = kodSyaratNyata;
    }

    public String getSyaratNyata() {
        return syaratNyata;
    }

    public void setSyaratNyata(String syaratNyata) {
        this.syaratNyata = syaratNyata;
    }

    public String getSekatan() {
        return sekatan;
    }

    public void setSekatan(String sekatan) {
        this.sekatan = sekatan;
    }

    public String getKodSekatan() {
        return kodSekatan;
    }

    public void setKodSekatan(String kodSekatan) {
        this.kodSekatan = kodSekatan;
    }

    public List<KodSyaratNyata> getListKodSyaratNyata() {
        return listKodSyaratNyata;
    }

    public void setListKodSyaratNyata(List<KodSyaratNyata> listKodSyaratNyata) {
        this.listKodSyaratNyata = listKodSyaratNyata;
    }

    public List<KodSekatanKepentingan> getListKodSekatan() {
        return listKodSekatan;
    }

    public void setListKodSekatan(List<KodSekatanKepentingan> listKodSekatan) {
        this.listKodSekatan = listKodSekatan;
    }

    public Permohonan getP() {
        return p;
    }

    public void setP(Permohonan p) {
        this.p = p;
    }

    public String getTarikhDaftarAsal() {
        return tarikhDaftarAsal;
    }

    public void setTarikhDaftarAsal(String tarikhDaftarAsal) {
        this.tarikhDaftarAsal = tarikhDaftarAsal;
    }

    public Date getTarikhDaftar() {
        return tarikhDaftar;
    }

    public void setTarikhDaftar(Date tarikhDaftar) {
        this.tarikhDaftar = tarikhDaftar;
    }

    public String getTarikhLuput() {
        return tarikhLuput;
    }

    public void setTarikhLuput(String tarikhLuput) {
        this.tarikhLuput = tarikhLuput;
    }


    public Hakmilik getHakmilik() {
        return hakmilik;
    }

    public void setHakmilik(Hakmilik hakmilik) {
        this.hakmilik = hakmilik;
    }

    public List<HakmilikPermohonan> getHakmilikPermohonanList() {
        return hakmilikPermohonanList;
    }

    public void setHakmilikPermohonanList(List<HakmilikPermohonan> hakmilikPermohonanList) {
        this.hakmilikPermohonanList = hakmilikPermohonanList;
    }

    public String getIndex() {
        return index;
    }

    public void setIndex(String index) {
        this.index = index;
    }

    public String getKodKatTanah() {
        return kodKatTanah;
    }

    public void setKodKatTanah(String kodKatTanah) {
        this.kodKatTanah = kodKatTanah;
    }

    
}
