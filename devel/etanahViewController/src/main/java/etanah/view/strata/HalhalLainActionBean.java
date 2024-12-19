/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.strata;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.dao.KodKeputusanDAO;
import etanah.dao.LaporanTanahDAO;
import etanah.dao.PermohonanDAO;
import etanah.dao.PermohonanLaporanKandunganDAO;
import etanah.model.FasaPermohonan;
import etanah.model.HakmilikPermohonan;
import etanah.model.InfoAudit;
import etanah.model.KodKeputusan;
import etanah.model.LanjutanTempoh;
import etanah.model.LaporanTanah;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.model.PermohonanLaporanKandungan;
import etanah.service.StrataPtService;
import net.sourceforge.stripes.action.UrlBinding;
import etanah.view.etanahActionBeanContext;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.RedirectResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.controller.LifecycleStage;
import org.apache.log4j.Logger;
import org.apache.commons.lang.StringUtils;

/**
 *
 * @author murali
 */
@UrlBinding("/strata/Hal_halLain")
public class HalhalLainActionBean extends AbleActionBean {

    @Inject
    private etanah.Configuration conf;
    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    LaporanTanahDAO laporanTanahDAO;
    @Inject
    PermohonanLaporanKandunganDAO laporanKandDAO;
    @Inject
    KodKeputusanDAO kodKpsnDAO;
    @Inject
    StrataPtService ptService;
    private Permohonan permohonan;
    private LaporanTanah laporanTanah;
    private HakmilikPermohonan mohonHakmilik = new HakmilikPermohonan();
    private List<PermohonanLaporanKandungan> senaraiLaporanKandungan;
    private PermohonanLaporanKandungan laporanKand;
    private String ulasaan1;
    private String idKand;
    private Date tariktolak = new Date();
    private Date tarikhLulus;
    private static final Logger LOG = Logger.getLogger(HalhalLainActionBean.class);
    private String syor;

    @DefaultHandler
    public Resolution showForm() {
        return new JSP("strata/Ruang_Udara/hal_halLain.jsp").addParameter("tab", "true");
    }

    public Resolution showForm2() {
        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        if ("05".equalsIgnoreCase(conf.getProperty("kodNegeri"))) {
            FasaPermohonan mohonFasa = ptService.findFasaPermohonanByIdAliran(idPermohonan, "semakkertasptg");
            if (mohonFasa != null) {
                if (mohonFasa.getKeputusan() != null && mohonFasa.getKeputusan().getKod().equals("DT")) {
                    getContext().getRequest().setAttribute("DT", Boolean.TRUE);//Ditolak hide date
                } else {
                    getContext().getRequest().setAttribute("DT", Boolean.FALSE);//Disokong show date
                }
            }
        } else {
            FasaPermohonan mohonFasa = new FasaPermohonan();
            permohonan = permohonanDAO.findById(idPermohonan);
            if (permohonan.getKodUrusan().getKod().equals("RBHS")) {
                mohonFasa = ptService.findFasaPermohonanByIdAliran(idPermohonan, "semakkertasptg");
            } else {
                mohonFasa = ptService.findFasaPermohonanByIdAliran(idPermohonan, "semaklaporan");
            }
            if (mohonFasa.getKeputusan() != null && mohonFasa.getKeputusan().getKod().equals("DT")) {
                getContext().getRequest().setAttribute("DT", Boolean.TRUE);//Ditolak hide date         
            } else {
                getContext().getRequest().setAttribute("DT", Boolean.FALSE);//Disokong show date  
            }
        }
        return new JSP("strata/common/tarikhlulus.jsp").addParameter("tab", "true");
    }

    public Resolution simpanTarikh() {
        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = permohonanDAO.findById(idPermohonan);
        Pengguna pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        InfoAudit infoAudit = new InfoAudit();
        mohonHakmilik = ptService.findMohonHakmilik(idPermohonan);
        infoAudit = ptService.getInfo(pengguna);
        LanjutanTempoh lanjutTempoh = new LanjutanTempoh();
        lanjutTempoh = ptService.findMohonLanjutTempoh(mohonHakmilik.getId());
        if (permohonan.getKodUrusan().getKod().equals("RBHS")) {
            if (lanjutTempoh == null) {
                LanjutanTempoh lanjutTempoh2 = new LanjutanTempoh();
                lanjutTempoh2.setCawangan(pengguna.getKodCawangan());
                lanjutTempoh2.setTarikhAkhirDilulus(tarikhLulus);
                lanjutTempoh2.setHakmilikPermohonan(mohonHakmilik);
                lanjutTempoh2.setInfoAudit(infoAudit);
                lanjutTempoh2 = ptService.saveLanjutTempoh(lanjutTempoh2);
            } else {
                lanjutTempoh.setCawangan(pengguna.getKodCawangan());
                lanjutTempoh.setTarikhAkhirDilulus(tarikhLulus);
                lanjutTempoh.setHakmilikPermohonan(mohonHakmilik);
                lanjutTempoh.setInfoAudit(infoAudit);
                lanjutTempoh = ptService.saveLanjutTempoh(lanjutTempoh);
            }
        } else {
            if (lanjutTempoh != null) {
                lanjutTempoh.setCawangan(pengguna.getKodCawangan());
                if (tarikhLulus != null) {
                    lanjutTempoh.setTarikhAkhirDilulus(tarikhLulus);
                }
                lanjutTempoh.setHakmilikPermohonan(mohonHakmilik);
                lanjutTempoh.setInfoAudit(infoAudit);
                lanjutTempoh = ptService.saveLanjutTempoh(lanjutTempoh);

            }
        }
        addSimpleMessage("Maklumat Berjaya Disimpan");
        return new JSP("strata/common/tarikhlulus.jsp").addParameter("tab", "true");
    }

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {
        Pengguna pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = permohonanDAO.findById(idPermohonan);
        if (idPermohonan != null) {
            laporanTanah = ptService.findLaporanTanahByIdPermohonan(idPermohonan);
            LOG.info("--------laporanTanah-------:" + laporanTanah);
        }
        if (laporanTanah != null) {
            LOG.info("--------laporanTanah.getIdLaporan()-------:" + laporanTanah.getIdLaporan());
            senaraiLaporanKandungan = ptService.findLaporanTanahByLaporanTanah(laporanTanah.getIdLaporan());
            LOG.info("--------senaraiLaporanKandungan-------:" + senaraiLaporanKandungan);
            Comparator c = new Comparator<PermohonanLaporanKandungan>() {

                @Override
                public int compare(PermohonanLaporanKandungan plk1, PermohonanLaporanKandungan plk2) {

                    long id1 = plk1.getIdKand();
                    long id2 = plk2.getIdKand();
                    return (int) id1 - (int) id2;
                }
            };

            Collections.sort(senaraiLaporanKandungan, c);
        }

        mohonHakmilik = ptService.findMohonHakmilik(idPermohonan);
        LanjutanTempoh lanjutTempoh = new LanjutanTempoh();
        if (mohonHakmilik != null) {
            lanjutTempoh = ptService.findMohonLanjutTempoh(mohonHakmilik.getId());
            LOG.info("--------lanjutTempoh-------:" + lanjutTempoh);
            if (lanjutTempoh != null) {
                if (lanjutTempoh.getTarikhAkhirDilulus() == null) {
                    tarikhLulus = lanjutTempoh.getTarikhAkhirDipohon();
                } else {
                    tarikhLulus = lanjutTempoh.getTarikhAkhirDilulus();
                }
                LOG.info("--------tarikhLulus-------:" + tarikhLulus);
            }
        }

        FasaPermohonan mohonFasa = ptService.findFasaPermohonanByIdAliran(idPermohonan, "g_sedialaporan");
        if (mohonFasa != null) {
            if (mohonFasa.getKeputusan() != null) {
                syor = mohonFasa.getKeputusan().getKod();
            }
        }
    }

    public Resolution SimpanHahal() {
        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = permohonanDAO.findById(idPermohonan);
        Pengguna pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        InfoAudit infoAudit = new InfoAudit();
        FasaPermohonan mohonFasa = ptService.findFasaPermohonanByIdAliran(idPermohonan, "g_sedialaporan");
        if (mohonFasa != null) {
            if (StringUtils.isNotBlank(syor)) {
                KodKeputusan kodKpsn = new KodKeputusan();
                kodKpsn = kodKpsnDAO.findById(syor);
                mohonFasa.setKeputusan(kodKpsn);
                System.out.println("-----kodKpsn-----" + kodKpsn);
                mohonFasa.setKeputusan(kodKpsn);
                ptService.saveFasaMohon(mohonFasa);
            }
        }

        senaraiLaporanKandungan = new ArrayList<PermohonanLaporanKandungan>();
        laporanTanah = ptService.findLaporanTanahByIdPermohonan(idPermohonan);

        if (laporanTanah != null) {
            LOG.info("---LAPORAN TANAH NOT NULL---- " + laporanTanah.getIdLaporan());
            infoAudit = laporanTanah.getInfoAudit();
            infoAudit.setDiKemaskiniOleh(pengguna);
            infoAudit.setTarikhKemaskini(new java.util.Date());
        } else {
            LOG.info("---LAPORAN TANAH IS NULL---- ");
            laporanTanah = new LaporanTanah();
            infoAudit.setDimasukOleh(pengguna);
            infoAudit.setTarikhMasuk(new java.util.Date());
        }
        laporanTanah.setInfoAudit(infoAudit);
        laporanTanah.setPermohonan(permohonan);
        ptService.SimpanKand(laporanTanah);
        LOG.info("Simpan LaporanTanah berjaya");
        laporanTanah = ptService.findLaporanTanahByIdPermohonan(idPermohonan);

        senaraiLaporanKandungan = ptService.findLaporanTanahByLaporanTanah(laporanTanah.getIdLaporan());

        Comparator c1 = new Comparator<PermohonanLaporanKandungan>() {

            @Override
            public int compare(PermohonanLaporanKandungan plk1, PermohonanLaporanKandungan plk2) {

                long id1 = plk1.getIdKand();
                long id2 = plk2.getIdKand();
                return (int) id1 - (int) id2;
            }
        };
        Collections.sort(senaraiLaporanKandungan, c1);

        if (senaraiLaporanKandungan.size() > 0) {
            LOG.info("--senaraiLaporanKandungan IS NOT EMPTY--");

            int count = Integer.parseInt(getContext().getRequest().getParameter("rowCount"));

            LOG.info("COUNT :" + count);
            for (int j = 1; j <= count; j++) {
                if (senaraiLaporanKandungan.size() != 0 && j <= senaraiLaporanKandungan.size()) {
                    laporanKand = (PermohonanLaporanKandungan) senaraiLaporanKandungan.get(j - 1);

                    if (laporanKand != null) {
                        LOG.info("-----------if laporanKand NOT NULL---------" + laporanKand.getIdKand());
                        //laporanKand = laporanKandDAO.findById(laporanKand.getIdKand());
                        infoAudit = laporanTanah.getInfoAudit();
                        infoAudit.setDiKemaskiniOleh(pengguna);
                        infoAudit.setTarikhKemaskini(new java.util.Date());
                    }
                } else {
                    LOG.info("-------else----laporanKand IS NULL---------" + laporanKand);
                    laporanKand = new PermohonanLaporanKandungan();
                    infoAudit = new InfoAudit();
                    infoAudit.setDimasukOleh(pengguna);
                    infoAudit.setTarikhMasuk(new java.util.Date());
                }

                laporanKand.setLaporanTanah(laporanTanah);
                laporanKand.setBil((short) 6);
                String kandungan = getContext().getRequest().getParameter("ulasan" + j);
                if (StringUtils.isEmpty(kandungan)) {
                    addSimpleError("Sila Isikan pada ruangan yang disediakan sebelum Simpan.");
                    break;
                }
                laporanKand.setKand(kandungan);
                laporanKand.setSubtajuk("Hal-hal lain");
                laporanKand.setInfoAudit(infoAudit);
                ptService.SimpanLaporanKand(laporanKand);
            }
            addSimpleMessage("Maklumat berjaya disimpan");

        } else {
            LOG.info("-------else----senaraiLaporanKandungan IS EMPTY---------");
            laporanKand = new PermohonanLaporanKandungan();
            infoAudit = new InfoAudit();
            infoAudit.setDimasukOleh(pengguna);
            infoAudit.setTarikhMasuk(new java.util.Date());
            laporanKand.setLaporanTanah(laporanTanah);
            laporanKand.setBil((short) 6);
            String kandungan = getContext().getRequest().getParameter("ulasaan1");
            if (StringUtils.isEmpty(kandungan)) {
                addSimpleError("Sila Isikan pada ruangan yang disediakan sebelum Simpan.");
            } else {
                LOG.info(kandungan);
                laporanKand.setKand(kandungan);
                laporanKand.setSubtajuk("Hal-hal lain");
                laporanKand.setInfoAudit(infoAudit);
                ptService.SimpanLaporanKand(laporanKand);
                addSimpleMessage("Maklumat berjaya disimpan");
            }
        }
        if (laporanTanah != null) {
            senaraiLaporanKandungan = ptService.findLaporanTanahByLaporanTanah(laporanTanah.getIdLaporan());
            Comparator c = new Comparator<PermohonanLaporanKandungan>() {

                @Override
                public int compare(PermohonanLaporanKandungan plk1, PermohonanLaporanKandungan plk2) {

                    long id1 = plk1.getIdKand();
                    long id2 = plk2.getIdKand();
                    return (int) id1 - (int) id2;
                }
            };

            Collections.sort(senaraiLaporanKandungan, c);
        }

        return new JSP("strata/Ruang_Udara/hal_halLain.jsp").addParameter("tab", "true");
    }

    public Resolution deleteKandungan() {
        InfoAudit ia = new InfoAudit();
        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        String idKandungan = getContext().getRequest().getParameter("idKandungan");
        if (idKandungan != null) {
            PermohonanLaporanKandungan laporanKand1 = new PermohonanLaporanKandungan();
            laporanKand1 = laporanKandDAO.findById(Long.parseLong(idKandungan));
            if (laporanKand1 != null) {
                ptService.deleteLaporanKandungan(laporanKand1);
                addSimpleMessage("Maklumat Telah Dihapuskan.");
            }
        }
//        return new JSP("strata/Ruang_Udara/hal_halLain.jsp").addParameter("tab", "true");
        return new RedirectResolution(HalhalLainActionBean.class, "showForm");
    }

    public Resolution tambahRow() {
//if (senaraiLaporanKandungan !=null){
        senaraiLaporanKandungan.add(new PermohonanLaporanKandungan());
//}
        return new JSP("strata/Ruang_Udara/hal_halLain.jsp").addParameter("tab", "true");

    }

    public String getIdKand() {
        return idKand;
    }

    public void setIdKand(String idKand) {
        this.idKand = idKand;
    }

    public LaporanTanah getLaporanTanah() {
        return laporanTanah;
    }

    public void setLaporanTanah(LaporanTanah laporanTanah) {
        this.laporanTanah = laporanTanah;
    }

    public Permohonan getPermohonan() {
        return permohonan;
    }

    public void setPermohonan(Permohonan permohonan) {
        this.permohonan = permohonan;
    }

    public List<PermohonanLaporanKandungan> getSenaraiLaporanKandungan() {
        return senaraiLaporanKandungan;
    }

    public void setSenaraiLaporanKandungan(List<PermohonanLaporanKandungan> senaraiLaporanKandungan) {
        this.senaraiLaporanKandungan = senaraiLaporanKandungan;
    }

    public PermohonanLaporanKandungan getLaporanKand() {
        return laporanKand;
    }

    public void setLaporanKand(PermohonanLaporanKandungan laporanKand) {
        this.laporanKand = laporanKand;
    }

    public Date getTarikhLulus() {
        return tarikhLulus;
    }

    public void setTarikhLulus(Date tarikhLulus) {
        this.tarikhLulus = tarikhLulus;
    }

    public String getUlasaan1() {
        return ulasaan1;
    }

    public void setUlasaan1(String ulasaan1) {
        this.ulasaan1 = ulasaan1;
    }

    public Date getTariktolak() {
        return tariktolak;
    }

    public void setTariktolak(Date tariktolak) {
        this.tariktolak = tariktolak;
    }

    public String getSyor() {
        return syor;
    }

    public void setSyor(String syor) {
        this.syor = syor;
    }
}
