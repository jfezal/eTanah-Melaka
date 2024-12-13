/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.penguatkuasaan;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import etanah.dao.KodDokumenDAO;
import etanah.dao.KodPerananDAO;
import etanah.dao.PermohonanDAO;
import etanah.model.KodPeranan;
import etanah.model.PermohonanLaporanUlasan;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;
import com.google.inject.Inject;
import etanah.dao.HakmilikDAO;
import etanah.dao.HakmilikPermohonanDAO;
import etanah.dao.PermohonanLaporanUlasanDAO;
import etanah.dao.PermohonanUkurDAO;
import etanah.model.KodCawangan;
import etanah.model.Hakmilik;
import etanah.model.HakmilikPermohonan;
import etanah.model.Permohonan;
import etanah.model.PermohonanUkur;
import etanah.model.Pengguna;
import etanah.model.InfoAudit;
import etanah.model.KodDokumen;
import etanah.service.EnforceService;
import etanah.service.PembangunanService;
import etanah.view.etanahActionBeanContext;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.apache.log4j.Logger;
import java.util.List;
import net.sourceforge.stripes.action.ForwardResolution;
import org.apache.commons.lang.StringUtils;

/**
 *
 * @author latifah.iskak modified by ctzainal on 31May2011
 */
@UrlBinding("/penguatkuasaan/sijilPengecualianUkur")
public class SijilPengecualianUkurActionBean extends AbleActionBean {

    private static final Logger logger = Logger.getLogger(SijilPengecualianUkurActionBean.class);
    @Inject
    etanah.Configuration conf;
    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    PermohonanUkurDAO permohonanUkurDAO;
    @Inject
    HakmilikDAO hakmilikDAO;
    @Inject
    KodPerananDAO kodPerananDAO;
    @Inject
    PembangunanService devService;
    @Inject
    EnforceService enfService;
    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;
    @Inject
    HakmilikPermohonanDAO hakmilikPermohonanDAO;
    @Inject
    PermohonanLaporanUlasanDAO permohonanLaporanUlasanDAO;
    @Inject
    KodDokumenDAO kodDokumenDAO;
    private PermohonanUkur permohonanUkur;
    private Permohonan permohonan;
    private Hakmilik hakmilik;
    private String tarikhPerakuan;
    private String ulasan;
    private Pengguna pguna;
    private KodCawangan cawangan;
    private PermohonanLaporanUlasan permohonanLaporanUlasan;
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    private List<HakmilikPermohonan> hakmilikList;
    private HakmilikPermohonan hp;
    ArrayList listIdPermohonan = new ArrayList<String>();
    String senaraiIdPermohonan[], idPertama, idKedua;
    private Boolean statusNorujukan = Boolean.FALSE;
    private Boolean maklumatTanahSek49 = Boolean.FALSE;
    private String failPTG;

    @DefaultHandler
    public Resolution showForm() {
        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        return new JSP("penguatkuasaan/sijilPengecualianUkur.jsp").addParameter("tab", "true");
    }

    public Resolution showForm2() {
        return new JSP("penguatkuasaan/sijil_pengecualian_ukur_view.jsp").addParameter("tab", "true");
    }

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {
        logger.info("rehydrate start");
        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = permohonanDAO.findById(idPermohonan);
        pguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);

        try {
            if ("04".equalsIgnoreCase(conf.getProperty("kodNegeri"))) {
                if (permohonan.getKodUrusan().getKod().equalsIgnoreCase("49")) {
                    if (permohonan.getPermohonanSebelum() != null) {
                        //1) After create new IP
                        permohonan = permohonan.getPermohonanSebelum();
                    }
                    maklumatTanahSek49 = true;
                }
            }

            Long id = null;
            hakmilikList = enfService.findListMohonHakmilik(permohonan.getIdPermohonan());

            if (maklumatTanahSek49 == true) {
                if (!hakmilikList.isEmpty()) {
                    for (int j = 0; j < hakmilikList.size(); j++) {
                        if (hakmilikList.get(j).getNomborRujukan() != null) {
                            statusNorujukan = true;
                            System.out.println("::::::::::: value j :" + j);
                            HakmilikPermohonan hp = hakmilikList.get(j);
                            listIdPermohonan.add(hp.getNomborRujukan());

                            ArrayList<String> data = listIdPermohonan;


                            for (String a : data) {
                                senaraiIdPermohonan = a.split(",");
                                logger.info("length senaraiIdPermohonan : " + senaraiIdPermohonan.length);
                                if (senaraiIdPermohonan.length > 1) {
                                    idPertama = senaraiIdPermohonan[0];
                                    idKedua = senaraiIdPermohonan[1];

                                }
                            }
                            logger.info("::: idPertama : " + idPertama);
                            logger.info("::: idKedua : " + idKedua);

                            String idMohon = "";

                            if (StringUtils.isNotEmpty(idPertama) && StringUtils.isNotEmpty(idKedua)) {
                                if (idPertama.equalsIgnoreCase(idPermohonan)) {
                                    idMohon = idPertama;
                                    id = hakmilikList.get(j).getId();
                                    System.out.println("id MH (1): " + id);
                                } else if (idKedua.equalsIgnoreCase(idPermohonan)) {
                                    idMohon = idKedua;
                                    id = hakmilikList.get(j).getId();
                                    System.out.println("id MH (2): " + id);
                                }
                            }

                            listIdPermohonan.clear();
                            idPertama = "";
                            idKedua = "";
                        }
                    }

                    System.out.println("::: id : " + id);
                    System.out.println("::: statusNorujukan : " + statusNorujukan);

                    if (statusNorujukan == true) {
                        if (id != null) {
                            logger.info("::: using id MH");
                            hakmilikList = enfService.findListMohonHakmilikById(id);
                        } else {
                            logger.info("::: using id idPermohonan");
                            hakmilikList = enfService.findListMohonHakmilik(permohonan.getIdPermohonan(), idPermohonan);
                        }
                    }

                    System.out.println(" hakmilikList.size() : " + hakmilikList.size());

                    if (!hakmilikList.isEmpty()) {
                        hp = hakmilikList.get(0);
                        hakmilik = hakmilikDAO.findById(hp.getHakmilik().getIdHakmilik());
                    }


                }
            }else{
                hakmilik = hakmilikList.get(0).getHakmilik();
            }


            permohonanUkur = devService.findPermohonanUkurByIdPermohonan(idPermohonan);
            permohonanLaporanUlasan = enfService.findPermohonanUkurByIdPermohonanTujuan(idPermohonan);

            if (permohonanUkur != null) {
                tarikhPerakuan = sdf.format(permohonanUkur.getTarikhPerakuan());
                failPTG = permohonanUkur.getNoFailISO();
            }

            if (permohonanLaporanUlasan != null) {
                ulasan = permohonanLaporanUlasan.getUlasan();

            }




        } catch (Exception ex) {
            logger.error(ex);
            ex.printStackTrace();
        }

        logger.info("rehydrate finish");
    }

    public Resolution simpan() {
        logger.info("simpan start");
        Transaction tx = sessionProvider.get().beginTransaction();
        tx.begin();
        try {
            String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
            permohonan = permohonanDAO.findById(idPermohonan);
            InfoAudit infoAudit = new InfoAudit();

            pguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
            String kodPeranan = pguna.getPerananUtama().getKod();


            permohonanLaporanUlasan = enfService.findPermohonanUkurByIdPermohonanTujuan(idPermohonan);

            if (permohonanLaporanUlasan != null) {
                logger.info("update tarikh perakuan in mohon ukur start");
                infoAudit = permohonanLaporanUlasan.getInfoAudit();
                infoAudit.setDiKemaskiniOleh(pguna);
                infoAudit.setTarikhKemaskini(new java.util.Date());
            } else {
                permohonanLaporanUlasan = new PermohonanLaporanUlasan();
                infoAudit.setTarikhMasuk(new java.util.Date());
                infoAudit.setDimasukOleh(pguna);
            }

            permohonanLaporanUlasan.setUlasan(ulasan);
            KodDokumen kodDokumen = kodDokumenDAO.findById("SPU");
            permohonanLaporanUlasan.setKodDokumen(kodDokumen);
            permohonanLaporanUlasan.setJenisUlasan("SijilPU");
            permohonanLaporanUlasan.setCawangan(pguna.getKodCawangan());
            permohonanLaporanUlasan.setPermohonan(permohonan);
            permohonanLaporanUlasan.setInfoAudit(infoAudit);
            KodPeranan kp = kodPerananDAO.findById(kodPeranan);
            permohonanLaporanUlasan.setKodPeranan(kp);

            permohonanLaporanUlasanDAO.saveOrUpdate(permohonanLaporanUlasan);


            addSimpleMessage("Maklumat telah berjaya disimpan.");
        } catch (Exception e) {
            tx.rollback();
            addSimpleError("Kemasukan Data Gagal. Sila Hubungi Pentadbir Sistem.");
            return new JSP("penguatkuasaan/sijilPengecualianUkur.jsp").addParameter("tab", "true");
        }
        logger.info("simpan finish");
        tx.commit();
        return new JSP("penguatkuasaan/sijilPengecualianUkur.jsp").addParameter("tab", "true");
    }

    public Resolution simpanMohonUkur() {
        logger.info("simpan mohon ukur start");
        Transaction tx = sessionProvider.get().beginTransaction();
        tx.begin();
        try {
            String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
            permohonan = permohonanDAO.findById(idPermohonan);
            Pengguna pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
            InfoAudit infoAudit = new InfoAudit();

            String idMohonUkur = (String) getContext().getRequest().getParameter("permohonanUkur.idMohonUkur");
//            PermohonanUkur mohonUkur = devService.findPermohonanUkurByIdPermohonan(idPermohonan);
            PermohonanUkur mohonUkur = new PermohonanUkur();

            if (idMohonUkur != null && !idMohonUkur.equals("")) {
                mohonUkur = permohonanUkurDAO.findById(Integer.parseInt(idMohonUkur));
                infoAudit = mohonUkur.getInfoAudit();
                infoAudit.setDiKemaskiniOleh(pengguna);
                infoAudit.setTarikhKemaskini(new java.util.Date());

            } else {
                infoAudit.setDimasukOleh(pengguna);
                infoAudit.setTarikhMasuk(new java.util.Date());

                NumberFormat df = new DecimalFormat("0000");
                Date dt = new Date();
                PermohonanUkur maxPuPermohonanUkur = devService.getMaxNoPUofPermohonanUkur(Integer.toString(1900 + dt.getYear()));
                System.out.println("----------maxPuPermohonanUkur-----------" + maxPuPermohonanUkur);

                if (maxPuPermohonanUkur == null) {
                    int val = 1;
                    String noPU = "";
                    noPU = df.format(val) + "/" + (1900 + dt.getYear());

                    System.out.println("-----------Seq-------------" + noPU);
                    mohonUkur.setNoPermohonanUkur(noPU);
                } else {
                    String maxNoPU = maxPuPermohonanUkur.getNoPermohonanUkur();
                    System.out.println("-----------maxNoPU-------------" + maxNoPU);
                    if (Integer.parseInt(maxNoPU.substring(5, 9)) == (1900 + dt.getYear())) {
                        String subNoPU1 = maxNoPU.substring(0, 4);
                        //                     String subNoPU2 = subNoPU1.substring(subNoPU1.lastIndexOf("0"));
                        //                     int val = Integer.parseInt(subNoPU2);
                        int val = Integer.parseInt(subNoPU1);
                        val = val + 1;
                        String noPU = "";
                        noPU = df.format(val) + "/" + (1900 + dt.getYear());
                        System.out.println("-----------Seq-------------" + noPU);
                        mohonUkur.setNoPermohonanUkur(noPU);
                    } else {
                        int value = 1;
                        String nopu = "";
                        nopu = df.format(value) + "/" + (1900 + dt.getYear());
                        mohonUkur.setNoPermohonanUkur(nopu);
                    }
                }

            }
            mohonUkur.setNoRujukanPejabatTanah(permohonan.getIdPermohonan());
            mohonUkur.setInfoAudit(infoAudit);
            mohonUkur.setPermohonan(permohonan);

            if (tarikhPerakuan != null) {
                mohonUkur.setTarikhPerakuan(sdf.parse(tarikhPerakuan));
                mohonUkur.setNoFailISO(failPTG);
            }

            permohonanUkurDAO.saveOrUpdate(mohonUkur);

            permohonanLaporanUlasan = enfService.findPermohonanUkurByIdPermohonanTujuan(idPermohonan);
            String kodPeranan = pengguna.getPerananUtama().getKod();

            if (permohonanLaporanUlasan != null) {
                logger.info("update tarikh perakuan in mohon ukur start");
                infoAudit = permohonanLaporanUlasan.getInfoAudit();
                infoAudit.setDiKemaskiniOleh(pengguna);
                infoAudit.setTarikhKemaskini(new java.util.Date());
            } else {
                permohonanLaporanUlasan = new PermohonanLaporanUlasan();
                infoAudit.setTarikhMasuk(new java.util.Date());
                infoAudit.setDimasukOleh(pengguna);
            }

            permohonanLaporanUlasan.setUlasan(ulasan);
            KodDokumen kodDokumen = kodDokumenDAO.findById("SPU");
            permohonanLaporanUlasan.setKodDokumen(kodDokumen);
            permohonanLaporanUlasan.setJenisUlasan("SijilPU");
            permohonanLaporanUlasan.setCawangan(pengguna.getKodCawangan());
            permohonanLaporanUlasan.setPermohonan(permohonan);
            permohonanLaporanUlasan.setInfoAudit(infoAudit);
            KodPeranan kp = kodPerananDAO.findById(kodPeranan);
            permohonanLaporanUlasan.setKodPeranan(kp);

            permohonanLaporanUlasanDAO.saveOrUpdate(permohonanLaporanUlasan);

            addSimpleMessage("Maklumat telah berjaya disimpan.");

            if (idPermohonan != null) {
                permohonanUkur = devService.findPermohonanUkurByIdPermohonan(idPermohonan);
            }
        } catch (Exception e) {
            tx.rollback();
            addSimpleError("Kemasukan Data Gagal. Sila Hubungi Pentadbir Sistem.");
            return new JSP("penguatkuasaan/sijilPengecualianUkur.jsp").addParameter("tab", "true");
        }
        tx.commit();
        logger.info("simpan finish");
        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        return new ForwardResolution("/WEB-INF/jsp/penguatkuasaan/sijilPengecualianUkur.jsp").addParameter("tab", "true");
    }

    public Permohonan getPermohonan() {
        return permohonan;
    }

    public void setPermohonan(Permohonan permohonan) {
        this.permohonan = permohonan;
    }

    public PermohonanUkur getPermohonanUkur() {
        return permohonanUkur;
    }

    public void setPermohonanUkur(PermohonanUkur permohonanUkur) {
        this.permohonanUkur = permohonanUkur;
    }

    public Hakmilik getHakmilik() {
        return hakmilik;
    }

    public void setHakmilik(Hakmilik hakmilik) {
        this.hakmilik = hakmilik;
    }

    public String getTarikhPerakuan() {
        return tarikhPerakuan;
    }

    public void setTarikhPerakuan(String tarikhPerakuan) {
        this.tarikhPerakuan = tarikhPerakuan;
    }

    public PermohonanLaporanUlasan getPermohonanLaporanUlasan() {
        return permohonanLaporanUlasan;
    }

    public void setPermohonanLaporanUlasan(PermohonanLaporanUlasan permohonanLaporanUlasan) {
        this.permohonanLaporanUlasan = permohonanLaporanUlasan;
    }

    public String getUlasan() {
        return ulasan;
    }

    public void setUlasan(String ulasan) {
        this.ulasan = ulasan;
    }

    public Pengguna getPguna() {
        return pguna;
    }

    public void setPguna(Pengguna pguna) {
        this.pguna = pguna;
    }

    public KodCawangan getCawangan() {
        return cawangan;
    }

    public void setCawangan(KodCawangan cawangan) {
        this.cawangan = cawangan;
    }

    public List<HakmilikPermohonan> getHakmilikList() {
        return hakmilikList;
    }

    public void setHakmilikList(List<HakmilikPermohonan> hakmilikList) {
        this.hakmilikList = hakmilikList;
    }

    public HakmilikPermohonan getHp() {
        return hp;
    }

    public void setHp(HakmilikPermohonan hp) {
        this.hp = hp;
    }

    public String[] getSenaraiIdPermohonan() {
        return senaraiIdPermohonan;
    }

    public void setSenaraiIdPermohonan(String[] senaraiIdPermohonan) {
        this.senaraiIdPermohonan = senaraiIdPermohonan;
    }

    public String getIdPertama() {
        return idPertama;
    }

    public void setIdPertama(String idPertama) {
        this.idPertama = idPertama;
    }

    public String getIdKedua() {
        return idKedua;
    }

    public void setIdKedua(String idKedua) {
        this.idKedua = idKedua;
    }

    public Boolean getStatusNorujukan() {
        return statusNorujukan;
    }

    public void setStatusNorujukan(Boolean statusNorujukan) {
        this.statusNorujukan = statusNorujukan;
    }

    public String getFailPTG() {
        return failPTG;
    }

    public void setFailPTG(String failPTG) {
        this.failPTG = failPTG;
    }
}
