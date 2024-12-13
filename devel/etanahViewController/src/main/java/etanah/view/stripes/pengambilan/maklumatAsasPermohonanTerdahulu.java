/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package etanah.view.stripes.pengambilan;

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
import net.sourceforge.stripes.action.ForwardResolution;

/**
 *
 * @author massita
 */
@UrlBinding("/pengambilan/maklumat_asas_pengambilan_Terdahulu")
public class maklumatAsasPermohonanTerdahulu extends AbleActionBean {
    private static final Logger logger = Logger.getLogger(maklumatAsasPermohonanTerdahulu.class);
    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    HakmilikDAO hakmilikDAO;
    @Inject
    RegService regService;
    @Inject
    ListUtil listUtil;
    private Permohonan p;
//    String tarikhDaftar;
    private Hakmilik hakmilik;
    private List<HakmilikPermohonan> hakmilikPermohonanList;
    private List<KodSekatanKepentingan> listKodSekatan;
    private List<KodSyaratNyata> listKodSyaratNyata;
    private List<KodPBT> senaraiKodPBT;
    private String idHakmilik;
//    String tarikhDaftar;
    Date tarikhDaftar;
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    String kodSekatan;
    String kodSyaratNyata;
    String syaratNyata;
    String sekatan;
    String kodDaerah;
    String kodJenisHakmilik;

    @DefaultHandler
    public Resolution showForm() {
       String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        p = permohonanDAO.findById(idPermohonan);

       String idSblm = p.getPermohonanSebelum().getIdPermohonan();

        if (idSblm != null && idPermohonan!=null) {
            idSblm = p.getPermohonanSebelum().getIdPermohonan();
            p = permohonanDAO.findById(idSblm);
        }
        return new JSP("pengambilan/melaka/penarikanbalik/maklumat_asas_tanah_Terdahulu.jsp").addParameter("tab", "true");
    }
    public Resolution hakMilikPopup() {
        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        p = permohonanDAO.findById(idPermohonan);

       String idSblm = p.getPermohonanSebelum().getIdPermohonan();

        if (idSblm != null && idPermohonan!=null) {
            idSblm = p.getPermohonanSebelum().getIdPermohonan();
            p = permohonanDAO.findById(idSblm);
        }
        return new JSP("pengambilan/melaka/penarikanbalik/maklumat_asas_tanah_Terdahulu.jsp").addParameter("popup", "true").addParameter("showForm", "false");
    }

    public Resolution hakmilikDetail() {
        idHakmilik = getContext().getRequest().getParameter("idHakmilik");
        System.out.println("----idHakmilik-----"+idHakmilik);
        hakmilik = hakmilikDAO.findById(idHakmilik);
        return new ForwardResolution("/WEB-INF/jsp/pengambilan/melaka/penarikanbalik/maklumat_asas_tanah_Terdahulu.jsp").addParameter("popup", "true");
    }

    public Resolution showFormCariKodSekatan() {
        cariKodSekatan();
        return new JSP("common/carian_kodsekatan.jsp").addParameter("popup", "true");
    }

    public Resolution showFormCariKodSyaratNyata() {
        cariKodSyaratNyata();
        return new JSP("common/carian_kodsyaratnyata.jsp").addParameter("popup", "true");
    }

    public Resolution cariKodSekatan() {
        Pengguna peng = (Pengguna) context.getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        String kc = peng.getKodCawangan().getKod();
//        logger.debug("peng.getKodCawangan().getKod :" + kc);
//        logger.debug("kodSekatan :" + kodSekatan);
        if (kodSekatan != null) {
            listKodSekatan = regService.searchKodSekatan(kodSekatan, kc, sekatan);
            logger.debug("listKodSekatan.size :" + listKodSekatan.size());
            if (listKodSekatan.size() < 1) {
                addSimpleError("Kod Sekatan Tidak Sah");
            }
        } else {
            listKodSekatan = regService.searchKodSekatan("%", kc, sekatan);
//            addSimpleError("Sila Cari / Pilih Kod Sekatan");
            if (listKodSekatan.size() < 1) {
                addSimpleError("Kod Sekatan Tidak Sah");
            }
        }
        return new JSP("common/carian_kodsekatan.jsp").addParameter("popup", "true");
    }

    public Resolution cariKodSyaratNyata() {
        Pengguna peng = (Pengguna) context.getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        String kc = peng.getKodCawangan().getKod();
//        logger.debug("kodSyaratNyata : " + kodSyaratNyata);
        if (kodSyaratNyata != null) {
//            listKodSyaratNyata = regService.searchKodSyaratNyata(kodSyaratNyata, kc, syaratNyata);
            logger.debug("listKodSyaratNyata.size : " + listKodSyaratNyata.size());
            if (listKodSyaratNyata.size() < 1) {
                addSimpleError("Kod Syarat Nyata Tidak Sah");
            }
        } else {
//            listKodSyaratNyata = regService.searchKodSyaratNyata("%", kc, syaratNyata);
//            addSimpleError("Sila Cari / Pilih Kod Syarat Nyata");
            if (listKodSyaratNyata.size() < 1) {
                addSimpleError("Kod Syarat Nyata Tidak Sah");
            }
        }


        return new JSP("common/carian_kodsyaratnyata.jsp").addParameter("popup", "true");
    }

    public Resolution simpan() throws ParseException {
        logger.debug("masuk method save");
        logger.debug("tarikhDaftar :" + tarikhDaftar);

        List<Hakmilik> listHakmilik = new ArrayList<Hakmilik>();
        for (HakmilikPermohonan hakmilikPermohonan : hakmilikPermohonanList) {
            Hakmilik h = hakmilikPermohonan.getHakmilik();
            //h.setKodHakmilik(hakmilik.getKodHakmilik());
            h.setRizab(hakmilik.getRizab());
            h.setPegangan(hakmilik.getPegangan());

            h.setTarikhDaftar(tarikhDaftar);
            h.setTempohPegangan(hakmilik.getTempohPegangan());

            /*IF TEMPOH PEGANGAN = 99 TARIKH LUPUT = TARIKHDAFTAR + 99*/
            if (hakmilik.getPegangan() != null) {
                if (hakmilik.getPegangan() == 'P') {
                    if (hakmilik.getTempohPegangan() == 99) {
                        Calendar calendar = new GregorianCalendar();
                        calendar.setTime(tarikhDaftar);
                        calendar.add(Calendar.DAY_OF_YEAR, 99 * 365);
                        Date tarikhLuput = calendar.getTime();
                        logger.debug("tarikhLuput : " + tarikhLuput);
                        h.setTarikhLuput(tarikhLuput);
                    } else if (hakmilik.getTempohPegangan() == 66) {
                        Calendar calendar = new GregorianCalendar();
                        calendar.setTime(tarikhDaftar);
                        calendar.add(Calendar.DAY_OF_YEAR, 66 * 365);
                        Date tarikhLuput = calendar.getTime();
                        logger.debug("tarikhLuput : " + tarikhLuput);
                        h.setTarikhLuput(tarikhLuput);
                    }

                } else {
                    h.setTarikhLuput(null);
                }
            }
            h.setPbt(hakmilik.getPbt());
            h.setNoPu(hakmilik.getNoPu());
//            h.setSyaratNyata(hakmilik.getSyaratNyata());
            if (hakmilik.getSekatanKepentingan() == null) {
                KodSekatanKepentingan ksk = new KodSekatanKepentingan();
                ksk.setKod("0000000");
                h.setSekatanKepentingan(ksk);
            } else {
                h.setSekatanKepentingan(hakmilik.getSekatanKepentingan());
            }
            listHakmilik.add(h);
        }
        regService.simpanHakmilikList(listHakmilik);

        hakmilik = listHakmilik.get(0);

        addSimpleMessage("Kemasukan Data Berjaya");

        return new RedirectResolution(maklumatAsasPermohonanTerdahulu.class).addParameter("tab", "true");
//        return new JSP("daftar/kemasukan_maklumat_asas.jsp").addParameter("tab", "true");
    }

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {

        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");

        logger.debug("p :" + p);
//        hakmilik = new Hakmilik();
        p = permohonanDAO.findById(idPermohonan);
        String idSblm = p.getPermohonanSebelum().getIdPermohonan();
        if (idSblm != null) {
            p = permohonanDAO.findById(idSblm);
            List<HakmilikPermohonan> lh = new ArrayList();
            lh = p.getSenaraiHakmilik();
            if (p.getSenaraiHakmilik().size() > 0) {
                Hakmilik h = p.getSenaraiHakmilik().get(0).getHakmilik();
                kodDaerah = h.getDaerah().getKod();
                kodJenisHakmilik = h.getKodHakmilik().getKod();
            }
            //kodDaerah = p.getCawangan().getDaerah().getKod();
            logger.debug("cari pbt utk koddaerah :"+kodDaerah);
            senaraiKodPBT = listUtil.getSenaraiKodPBTByDaerah(kodDaerah);
            logger.debug("senaraiKodPBT :" + senaraiKodPBT.size());
            hakmilikPermohonanList = p.getSenaraiHakmilik();
            if (hakmilikPermohonanList.size() > 0) {
                tarikhDaftar = p.getSenaraiHakmilik().get(0).getHakmilik().getTarikhDaftar();
                if (p.getSenaraiHakmilik().get(0).getHakmilik().getSekatanKepentingan() != null) {
                    kodSekatan = p.getSenaraiHakmilik().get(0).getHakmilik().getSekatanKepentingan().getKod();
                }
                if (p.getSenaraiHakmilik().get(0).getHakmilik().getSyaratNyata() != null) {
                    kodSyaratNyata = p.getSenaraiHakmilik().get(0).getHakmilik().getSyaratNyata().getKod();
                }
            }
        }
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

    public Date getTarikhDaftar() {
        return tarikhDaftar;
    }

    public void setTarikhDaftar(Date tarikhDaftar) {
        this.tarikhDaftar = tarikhDaftar;
    }

//    public String getTarikhDaftar() {
//        return tarikhDaftar;
//    }
//
//    public void setTarikhDaftar(String tarikhDaftar) {
//        this.tarikhDaftar = tarikhDaftar;
//    }
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

}
