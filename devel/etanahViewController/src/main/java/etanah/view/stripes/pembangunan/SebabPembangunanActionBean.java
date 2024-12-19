/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.stripes.pembangunan;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.dao.HakmilikDAO;
import etanah.dao.KodNegeriDAO;
import org.apache.commons.lang.StringUtils;
import java.util.List;
import java.util.ArrayList;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;
import org.apache.log4j.Logger;
import etanah.service.PembangunanService;
import etanah.view.etanahActionBeanContext;
import etanah.dao.PermohonanDAO;
import etanah.model.Hakmilik;
import etanah.model.HakmilikPermohonan;
import etanah.model.HakmilikPihakBerkepentingan;
import etanah.model.Permohonan;
import etanah.model.Pengguna;
import etanah.model.InfoAudit;
import etanah.model.KodCawangan;
import java.util.StringTokenizer;

/**
 *
 * @author nursyazwani
 */
@UrlBinding("/pembangunan/sebab_pembangunan")
public class SebabPembangunanActionBean extends AbleActionBean {

    private static final Logger logger = Logger.getLogger(SebabPembangunanActionBean.class);
    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    PembangunanService pembangunanServ;
    @Inject
    HakmilikDAO hakmilikDAO;
    @Inject
    KodNegeriDAO kodNegeriDAO;
    @Inject
    private etanah.Configuration conf;
    @Inject
    PembangunanUtility pu;
    private Permohonan mohon;
    private Hakmilik hakmilik;
    private String idPermohonan;
    private String tajuk;
    private String sebabPermohonan;
    private String catatanMohon;
    private String kodNegeri;
    private HakmilikPihakBerkepentingan hakmilikPihak;
    private String nama;
    private String lanjuttempoh;

    @DefaultHandler
    public Resolution showForm() {
        logger.info("showForm");
        kodNegeri = conf.getProperty("kodNegeri");
        Pengguna pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);

        if (mohon.getSebab() == null) {
            logger.info("Sebab Start :" + mohon.getSebab());
            String urusan = "";
            String lokasi = "";
            String kodNegeriNama = "";
            String daerah = "";
            if (kodNegeri != null) {
                kodNegeriNama = kodNegeriDAO.findById(kodNegeri).getNama();
            }

            List<HakmilikPermohonan> hakmilikPermohonanList = new ArrayList<HakmilikPermohonan>();
            hakmilikPermohonanList = mohon.getSenaraiHakmilik();
            for (int w = 0; w < hakmilikPermohonanList.size(); w++) {
                HakmilikPermohonan hp = mohon.getSenaraiHakmilik().get(w);
                hakmilik = hakmilikDAO.findById(hp.getHakmilik().getIdHakmilik());

                StringBuffer kodBPM = new StringBuffer();
                // make first letter Caps in each word
                if (hakmilik.getBandarPekanMukim() != null) {
                    String str1 = hakmilik.getBandarPekanMukim().getNama();
                    StringTokenizer st1 = new StringTokenizer(str1);
                    while (st1.hasMoreTokens()) {
                        String t1 = st1.nextToken();
                        t1 = Character.toUpperCase(t1.charAt(0)) + t1.toLowerCase().substring(1);
                        kodBPM.append(t1 + " ");
                    }
                }


                String noHakmilik = hakmilik.getNoHakmilik().replaceAll("^0*", "");
                String noLot = hakmilik.getNoLot().replaceAll("^0*", "");


                if (w == 0) {
                    lokasi = " Hakmilik " + hakmilik.getKodHakmilik().getKod() + " " + noHakmilik + ", " + hakmilik.getLot().getNama() + " " + noLot + ", seluas " + hakmilik.getLuas() + " " + hakmilik.getKodUnitLuas().getNama() + ", ";
                }

                if (w > 0) {
                    if (w <= ((hakmilikPermohonanList.size() + 2) - 4)) {
                        System.out.println("--ID HM > 1---");
                        lokasi = lokasi + ", " + " Hakmilik " + hakmilik.getKodHakmilik().getKod() + " " + noHakmilik + ", " + hakmilik.getLot().getNama() + " " + noLot + ", seluas " + hakmilik.getLuas() + " " + hakmilik.getKodUnitLuas().getNama() + ", ";

                    } else if (w == (hakmilikPermohonanList.size() - 1)) {
                        System.out.println("--ID HM = 1---");
                        lokasi = lokasi + " dan " + " Hakmilik " + hakmilik.getKodHakmilik().getKod() + " " + noHakmilik + ", " + hakmilik.getLot().getNama() + " " + noLot + ", seluas " + hakmilik.getLuas() + " " + hakmilik.getKodUnitLuas().getNama() + ", ";
                    }
                }
                String daerahInitCap = pu.convertStringtoInitCap(hakmilik.getDaerah().getNama());
                String kodNegeriNamaInitCap = pu.convertStringtoInitCap(kodNegeriNama);

                daerah = kodBPM + ", Daerah " + daerahInitCap + ", " + kodNegeriNamaInitCap;
            }

            //nama
            HakmilikPermohonan hp1 = new HakmilikPermohonan();
            hp1 = mohon.getSenaraiHakmilik().get(0);
            hakmilik = hakmilikDAO.findById(hp1.getHakmilik().getIdHakmilik());
            String kodPihak = "PM";
            List<HakmilikPihakBerkepentingan> hakmilikPihakList = new ArrayList<HakmilikPihakBerkepentingan>();
            hakmilikPihakList = pembangunanServ.findHakmilikPihakByKod(hakmilik.getIdHakmilik(), kodPihak);
            if (!(hakmilikPihakList.isEmpty())) {
                hakmilikPihak = hakmilikPihakList.get(0);
            }

            nama = "";
            for (int j = 0; j < hakmilikPihakList.size(); j++) {
                HakmilikPihakBerkepentingan pm = new HakmilikPihakBerkepentingan();
                pm = hakmilikPihakList.get(j);

                if (j == 0) {
                    nama = pm.getPihak().getNama();
                }
                if (j > 0) {
                    if (j <= ((hakmilikPihakList.size() + 2) - 4)) {
                        nama = nama + ", " + pm.getPihak().getNama();
                    } else if (j == (hakmilikPihakList.size() - 1)) {
                        nama = nama + " dan " + pm.getPihak().getNama();
                    }
                }
            }

            logger.info("------Nama----" + nama);
            nama = pu.convertStringtoInitCap(nama);


            urusan = mohon.getKodUrusan().getNama();

            //melaka
            if (kodNegeri.equals("04")) {
                if (mohon.getKodUrusan().getKod().equalsIgnoreCase("TSKKT")) {
                    if (hakmilik.getKategoriTanah().getKod().equalsIgnoreCase("0")
                            || hakmilik.getKategoriTanah().getKod().equalsIgnoreCase("4")
                            || hakmilik.getKategoriTanah().getKod().equalsIgnoreCase("5")) {

                        urusan = "Permohonan Tukar Syarat Pengenaan Kategori Kegunaan Tanah ";
                    }
                } else if (mohon.getKodUrusan().getKod().equals("RNS")) {
                    if (mohon.getPermohonanSebelum().getSebab() != null) {
                        sebabPermohonan = "Rayuan Penilaian Semula Tanah Untuk " + mohon.getPermohonanSebelum().getSebab();
                    } else {
                        sebabPermohonan = "Rayuan Penilaian Semula Tanah Untuk " + mohon.getPermohonanSebelum().getKodUrusan().getNama() + " di bawah Seksyen " + mohon.getPermohonanSebelum().getKodUrusan().getRujukanKanun() + " di atas " + lokasi + "" + daerah + ".";
                    }
                } else if (mohon.getKodUrusan().getKod().equals("RPMMK") || mohon.getKodUrusan().getKod().equals("RLTB") || mohon.getKodUrusan().getKod().equals("RPP")) {
                    if (nama != null) {
                        sebabPermohonan = urusan + " Daripada " + nama + " di atas" + lokasi + "" + daerah + ".";
                    } else {
                        sebabPermohonan = urusan + " di atas " + lokasi + "" + daerah + ".";
                    }

                } else if (mohon.getKodUrusan().getKod().equals("RTLK") || mohon.getKodUrusan().getKod().equals("PBSK")) {
                    sebabPermohonan = urusan + " di atas" + lokasi + "" + daerah + ".";
                } else {
                    sebabPermohonan = urusan + " di bawah Seksyen " + mohon.getKodUrusan().getRujukanKanun() + " di atas " + lokasi + "" + daerah + ".";
                }
            }

            //N9
            if (kodNegeri.equals("05")) {
                sebabPermohonan = urusan + " di atas " + lokasi + "" + daerah + " di bawah Seksyen " + mohon.getKodUrusan().getRujukanKanun() + ".";
            }

            catatanMohon = mohon.getCatatan();
            logger.info("Sebab End :" + sebabPermohonan);
        } else {
            sebabPermohonan = mohon.getSebab();
            catatanMohon = mohon.getCatatan();
        }

//        if (mohon.getKodUrusan().getKod().equals("PPCB")) {
//            InfoAudit infoAudit = new InfoAudit();
//
//            if (mohon != null) {
//                logger.info("Sebab != " + sebabPermohonan);
//                infoAudit = mohon.getInfoAudit();
//                infoAudit.setDiKemaskiniOleh(pengguna);
//                infoAudit.setTarikhKemaskini(new java.util.Date());
//                mohon.setIdPermohonan(idPermohonan);
//                mohon.setInfoAudit(infoAudit);
//                mohon.setKodUrusan(mohon.getKodUrusan());
//                mohon.setCawangan(pengguna.getKodCawangan());
//                mohon.setSebab(sebabPermohonan);
//                mohon.setCatatan(catatanMohon);
//                pembangunanServ.simpanPermohonan(mohon);
//            }
//            else{
//                logger.info("Sebab ==" + sebabPermohonan);
//                infoAudit = mohon.getInfoAudit();
//                infoAudit.setDiKemaskiniOleh(pengguna);
//                infoAudit.setTarikhKemaskini(new java.util.Date());
//                mohon.setSebab(sebabPermohonan);
//                mohon.setCatatan(catatanMohon);
//                pembangunanServ.simpanPermohonan(mohon);
//            }
//        }

        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        return new JSP("pembangunan/common/dev_sebab_pembangunan.jsp").addParameter("tab", "true");
    }

    public Resolution showForm2() {
        logger.info("showForm2");
        return new JSP("pembangunan/common/dev_sebab_pembangunan.jsp").addParameter("tab", "true");
    }

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {
        logger.info("rehydrate start.");
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        Pengguna pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);

        if (idPermohonan != null) {
            mohon = permohonanDAO.findById(idPermohonan);
        }

        if (mohon.getSebab() != null) {
            sebabPermohonan = mohon.getSebab();
            logger.info("Sebab " + sebabPermohonan);
        }

        if (mohon.getCatatan() != null) {
            catatanMohon = mohon.getCatatan();
            logger.info("Catatan " + catatanMohon);
        }

        kodNegeri = conf.getProperty("kodNegeri");
        String kodNegeriNama = "";
        if (kodNegeri != null) {
            kodNegeriNama = kodNegeriDAO.findById(kodNegeri).getNama();
        }
        logger.info("rehydrate end.");
    }

    public Resolution simpan() {
        logger.info("simpan start.");
        logger.info("simpan Sebab :" + sebabPermohonan);
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        Pengguna pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        InfoAudit infoAudit = new InfoAudit();

        if (mohon != null) {
            infoAudit = mohon.getInfoAudit();
            infoAudit.setDiKemaskiniOleh(pengguna);
            infoAudit.setTarikhKemaskini(new java.util.Date());
            mohon.setIdPermohonan(idPermohonan);
            mohon.setInfoAudit(infoAudit);
            mohon.setKodUrusan(mohon.getKodUrusan());
            mohon.setCawangan(pengguna.getKodCawangan());
            mohon.setSebab(sebabPermohonan);
            mohon.setCatatan(catatanMohon);
            pembangunanServ.simpanPermohonan(mohon);
        }
        addSimpleMessage("Maklumat telah berjaya disimpan.");
        logger.info("simpan end.");
        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        return new JSP("pembangunan/common/dev_sebab_pembangunan.jsp").addParameter("tab", "true");
    }

    public Permohonan getMohon() {
        return mohon;
    }

    public void setMohon(Permohonan mohon) {
        this.mohon = mohon;
    }

    public String getIdPermohonan() {
        return idPermohonan;
    }

    public void setIdPermohonan(String idPermohonan) {
        this.idPermohonan = idPermohonan;
    }

    public String getSebabPermohonan() {
        return sebabPermohonan;
    }

    public void setSebabPermohonan(String sebabPermohonan) {
        this.sebabPermohonan = sebabPermohonan;
    }

    public String getCatatanMohon() {
        return catatanMohon;
    }

    public void setCatatanMohon(String catatanMohon) {
        this.catatanMohon = catatanMohon;
    }

    public String getKodNegeri() {
        return kodNegeri;
    }

    public void setKodNegeri(String kodNegeri) {
        this.kodNegeri = kodNegeri;
    }

    public String getTajuk() {
        return tajuk;
    }

    public void setTajuk(String tajuk) {
        this.tajuk = tajuk;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getLanjuttempoh() {
        return lanjuttempoh;
    }

    public void setLanjuttempoh(String lanjuttempoh) {
        this.lanjuttempoh = lanjuttempoh;
    }
    
    
}
