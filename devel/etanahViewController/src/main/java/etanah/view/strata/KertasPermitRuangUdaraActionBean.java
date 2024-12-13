/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.strata;

import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.dao.FasaPermohonanDAO;
import etanah.dao.HakmilikDAO;
import etanah.dao.KodCawanganDAO;
import etanah.dao.KodKeputusanDAO;
import etanah.dao.LaporanTanahDAO;
import etanah.dao.PermohonanDAO;
import etanah.dao.PermohonanKertasKandunganDAO;
import etanah.dao.PermohonanLaporanKandunganDAO;
import etanah.dao.PermohonanPermitButirDAO;
import etanah.model.FasaPermohonan;
import etanah.model.Hakmilik;
import etanah.model.HakmilikPermohonan;
import etanah.model.InfoAudit;
import etanah.model.KodCawangan;
import etanah.model.KodKadarBayaran;
import etanah.model.KodKeputusan;
import etanah.model.LaporanTanah;
import etanah.model.Pemohon;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.model.PermohonanKertas;
import etanah.model.PermohonanKertasKandungan;
import etanah.model.PermohonanLaporanKandungan;
import etanah.model.PermohonanPermitButir;
import etanah.model.Pihak;
import etanah.service.StrataPtService;
//import etanah.service.common.PemohonService;
import etanah.service.common.PermohonanService;
import etanah.service.common.PihakService;
import etanah.view.BasicTabActionBean;
import etanah.view.etanahActionBeanContext;
import java.util.ArrayList;
import java.util.List;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;
import org.apache.log4j.Logger;
import etanah.service.BPelService;
import java.math.BigDecimal;
import org.apache.commons.lang.StringUtils;
import oracle.bpel.services.workflow.task.model.Task;
import java.util.Collections;
import java.util.Comparator;

/**
 *
 * @author Srinu
 */
@UrlBinding("/strata/KertasPermit_RuangUdara")
public class KertasPermitRuangUdaraActionBean extends BasicTabActionBean {

    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    HakmilikDAO hakmilikDAO;
    @Inject
    PermohonanService permohonanService;
    @Inject
    PihakService pihakService;
    @Inject
    StrataPtService strataPtService;
    @Inject
    PermohonanPermitButirDAO permohonanPermitButirDAO;
    @Inject
    private LaporanTanahDAO laporanTanahDAO;
    @Inject
    PermohonanLaporanKandunganDAO laporanKandDAO;
    @Inject
    StrataPtService ptService;
    @Inject
    KodCawanganDAO kodCawanganDAO;
    @Inject
    PermohonanKertasKandunganDAO permohonanKertasKandunganDAO;
    @Inject
    KodKeputusanDAO kodKeputusanDAO;
    @Inject
    FasaPermohonanDAO fasaPermohonanDAO;
    private List<PermohonanKertasKandungan> senaraiKertasKandungan;
    private List<PermohonanKertasKandungan> senaraiKertasKandungan4;
    private PermohonanKertas permohonanKertas;
    private List<PermohonanLaporanKandungan> senaraiLaporanKandungan;
    private List<PermohonanLaporanKandungan> senaraiLaporanKandungan1;
    private PermohonanLaporanKandungan permohonanLaporanKandungan;
    private LaporanTanah laporanTanah;
    private LaporanTanah tanah;
    private List<PermohonanLaporanKandungan> senaraiLaporanK;
    private PermohonanLaporanKandungan laporanKand;
    private PermohonanLaporanKandungan kand;
    private String tujuan;
    private Pemohon pemohon;
    private Permohonan permohonan;
    private Hakmilik hakmilik;
    private Pihak pihak1;
    private PermohonanPermitButir permohonanPermitButir;
    private static final Logger LOG = Logger.getLogger(KertasPermitRuangUdaraActionBean.class);
    private boolean stage1 = false;
    private boolean stage2 = false;
    private boolean stage3 = false;
    int bilpermit = 0;
    private BigDecimal jumlahBayaran;
    private List<PermohonanPermitButir> permohonanPermitButirList;
    private String perakuan;
    private String ulasanDefault;
    private String sekatan;
    private String syarat;
    private String nLot;
    private String nHakmilik;
    private String syor;

    public List<PermohonanPermitButir> getPermohonanPermitButirList() {
        return permohonanPermitButirList;
    }

    public void setPermohonanPermitButirList(List<PermohonanPermitButir> permohonanPermitButirList) {
        this.permohonanPermitButirList = permohonanPermitButirList;
    }

    public int getBilpermit() {
        return bilpermit;
    }

    public void setBilpermit(int bilpermit) {
        this.bilpermit = bilpermit;
    }

    public BigDecimal getJumlahBayaran() {
        return jumlahBayaran;
    }

    public void setJumlahBayaran(BigDecimal jumlahBayaran) {
        this.jumlahBayaran = jumlahBayaran;
    }

    @DefaultHandler
    public Resolution showForm() {
        getContext().getRequest().setAttribute("read", Boolean.FALSE);
        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        return new JSP("strata/Ruang_Udara/KertasPermit_RuangUdara.jsp").addParameter("tab", "true");
    }

    public Resolution semak() {
        getContext().getRequest().setAttribute("edit", Boolean.FALSE);
        getContext().getRequest().setAttribute("read", Boolean.TRUE);
        return new JSP("strata/Ruang_Udara/KertasPermit_RuangUdara.jsp").addParameter("tab", "true");
    }

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {
        Pengguna pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        String taskId = (String) getContext().getRequest().getSession().getAttribute("taskId");
        String stageId = "";
        BPelService service = new BPelService();

        if (StringUtils.isNotBlank(taskId)) {
            Task t = null;
            t = service.getTaskFromBPel(taskId, pengguna);
            stageId = t.getSystemAttributes().getStage();
        }

        if (stageId.equals("drafkertasptg")) {

            stage1 = true;
        }
        if (stageId.equals("semakkertasptg")) {

            stage2 = true;
        }
        if (stageId.equals("keputusan")) {

            stage2 = true;
            stage3 = true;
        }
        if (stageId.equals("syorkertasptg")) {

            stage2 = true;
            stage3 = true;
        }


        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        Pihak idPihak = (Pihak) getContext().getRequest().getSession().getAttribute("idPihak");
        permohonan = permohonanDAO.findById(idPermohonan);
        HakmilikPermohonan hp = new HakmilikPermohonan();
        hp = permohonan.getSenaraiHakmilik().get(0);
        hakmilik = hakmilikDAO.findById(hp.getHakmilik().getIdHakmilik());
        if (hakmilik != null) {
            if (hakmilik.getSekatanKepentingan() != null) {
                sekatan = hakmilik.getSekatanKepentingan().getSekatan();
                LOG.info("SEKATAN : " + sekatan);
            }
            if (hakmilik.getSyaratNyata() != null) {
                syarat = hakmilik.getSyaratNyata().getSyarat();
                LOG.info("SYARAT : " + syarat);
            }
        }
        permohonan = permohonanService.findById(idPermohonan);
        permohonanPermitButirList = strataPtService.getSumOfPermitButirByIdHakmilikPermohonan(hp.getId());
        Comparator c = new Comparator<PermohonanPermitButir>() {
            @Override
            public int compare(PermohonanPermitButir ppb1, PermohonanPermitButir ppb2) {

                String namaBlok1 = ppb1.getNoBlok();
                String namaBlok2 = ppb2.getNoBlok();
                return Integer.parseInt(namaBlok1)
                        - Integer.parseInt(namaBlok2);
            }
        };
        Collections.sort(permohonanPermitButirList, c);
        System.out.println("-----------permohonanPermitButirList-------" + permohonanPermitButirList.size());
        if (!permohonan.getSenaraiPemohon().isEmpty()) {
            pihak1 = permohonan.getSenaraiPemohon().get(0).getPihak();
            if (pihak1.getNegeri() != null) {
                tujuan = "Kertas ini disediakan bagi mempertimbangkan Permohanan Permit Ruang Udara daripada" + " "
                        + pihak1.getNama() + "." + "yang beralamat di " + "," + pihak1.getAlamat1() + ","
                        + pihak1.getAlamat2() + "," + pihak1.getAlamat3() + "," + pihak1.getAlamat4() + ","
                        + pihak1.getPoskod() + ","
                        + pihak1.getNegeri().getNama() + "" + ".";
            } else {
                tujuan = "Kertas ini disediakan bagi mempertimbangkan Permohanan Permit Ruang Udara daripada" + " "
                        + pihak1.getNama() + "." + "yang beralamat di " + "," + pihak1.getAlamat1() + ","
                        + pihak1.getAlamat2() + "," + pihak1.getAlamat3() + "," + pihak1.getAlamat4() + ","
                        + pihak1.getPoskod() + ".";
            }
        }

        if (idPermohonan != null) {
            laporanTanah = ptService.findLaporanTanahByIdPermohonan(idPermohonan);
            if (laporanTanah != null) {
                senaraiLaporanKandungan = ptService.findLaporanTanahByLaporanTanah(laporanTanah.getIdLaporan());
            }
            if (laporanTanah != null) {
                senaraiLaporanKandungan1 = ptService.findLaporanTanahByLaporanTanah(laporanTanah.getIdLaporan());
            }
            permohonanKertas = ptService.findKertas(idPermohonan);
            if (permohonanKertas != null) {
                senaraiKertasKandungan = ptService.findByIdLapor(permohonanKertas.getIdKertas(), 3);
                senaraiKertasKandungan4 = ptService.findByIdLapor(permohonanKertas.getIdKertas(), 4);
            }
            ulasanDefault = "Bilangan permit yang dicadangkan adalah berasaskan kepada perakuan yang dibuat Jurukur Berlesen seperti"
                    + " pelan yang disertakan.";



        }
        bilpermit = 0;
        for (PermohonanPermitButir ppb : permohonanPermitButirList) {
            bilpermit = bilpermit + ppb.getBilPermit();
        }
        KodKadarBayaran kkb;
        kkb = ptService.findkodKadarBayar(permohonan.getKodUrusan().getKod());
        if (kkb != null) {
            LOG.info("KOD KADAR BAYAR : " + kkb.getKod());
            Double d = Double.parseDouble(String.valueOf(bilpermit));
            BigDecimal b = new BigDecimal(d);

            jumlahBayaran = (kkb.getKadar()).multiply(b).multiply(new BigDecimal(21));
        } else {
            jumlahBayaran = new BigDecimal(0);
        }


        FasaPermohonan fp = ptService.findMohonFasa(idPermohonan, "semakkertasptg");
        if (fp != null) {

            if (fp.getKeputusan() != null) {
                LOG.info("check fp not null, assigned perakuan = kod_keputusan.");
                perakuan = fp.getKeputusan().getKod();
            } else {
                LOG.info("check fp null. Auto assigned perakuan = L");
                perakuan = "L";
            }
        } else {
            LOG.info("check fp null. Auto assigned perakuan = L");
            perakuan = "L";
        }

        nLot = hakmilik.getNoLot().replaceAll("^0*", "");
        LOG.debug("---nLot---" + nLot);
        nHakmilik = hakmilik.getNoHakmilik().replaceAll("^0*", "");
        LOG.debug("---nHakmilik---" + nHakmilik);        
        
       FasaPermohonan mohonFasa = ptService.findFasaPermohonanByIdAliran(idPermohonan, "g_sedialaporan");
        if(mohonFasa.getKeputusan() != null && mohonFasa.getKeputusan().getKod().equals("DI")){
            getContext().getRequest().setAttribute("DI", Boolean.TRUE);
        }else{
            getContext().getRequest().setAttribute("DI", Boolean.FALSE);
        }        
    }

    public Resolution SimpanHahal() {
        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = permohonanDAO.findById(idPermohonan);
        Pengguna pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        InfoAudit infoAudit = new InfoAudit();
        KodCawangan cawangan = new KodCawangan();
        cawangan = kodCawanganDAO.findById(pengguna.getKodCawangan().getKod());
        permohonanKertas = ptService.findKertas(idPermohonan);
//        senaraiLaporanKandungan = new ArrayList<PermohonanLaporanKandungan>();
        if (permohonanKertas != null) {
            infoAudit = permohonanKertas.getInfoAudit();
            infoAudit.setDiKemaskiniOleh(pengguna);
            infoAudit.setTarikhKemaskini(new java.util.Date());
        } else {
            permohonanKertas = new PermohonanKertas();
            infoAudit.setDimasukOleh(pengguna);
            infoAudit.setTarikhMasuk(new java.util.Date());
            permohonanKertas.setCawangan(cawangan);
            permohonanKertas.setTajuk("KERTAS PERTIMBANGAN PTG");
        }
        permohonanKertas.setInfoAudit(infoAudit);
        permohonanKertas.setPermohonan(permohonan);

        permohonanKertas = ptService.simpanPermohonanKertasObject(permohonanKertas);

        int count = Integer.parseInt(getContext().getRequest().getParameter("rowCount3"));
        senaraiKertasKandungan = ptService.findByIdLapor(permohonanKertas.getIdKertas(), 3);
        PermohonanKertasKandungan permohonanKertasKand;
        LOG.info("--------count------------" + count);
        PermohonanKertasKandungan pkk = new PermohonanKertasKandungan();
        for (int j = 1; j <= count; j++) {

            if (senaraiKertasKandungan.size() != 0 && j <= senaraiKertasKandungan.size()) {
                LOG.info("IF : senaraiKertasKandungan not null");
                permohonanKertasKand = (PermohonanKertasKandungan) senaraiKertasKandungan.get(j - 1);
                if (permohonanKertasKand != null) {
                    LOG.info("IF : permohonanKertasKand not null");
                    //laporanKand = laporanKandDAO.findById(laporanKand.getIdKand());
                    infoAudit = permohonanKertas.getInfoAudit();
                    infoAudit.setDiKemaskiniOleh(pengguna);
                    infoAudit.setTarikhKemaskini(new java.util.Date());
                }
            } else {
                LOG.info("ELSE : senaraiKertasKandungan is null");
                permohonanKertasKand = new PermohonanKertasKandungan();
                infoAudit = new InfoAudit();
                infoAudit.setDimasukOleh(pengguna);
                infoAudit.setTarikhMasuk(new java.util.Date());
                permohonanKertasKand.setInfoAudit(infoAudit);
            }

            permohonanKertasKand.setKertas(permohonanKertas);
            permohonanKertasKand.setBil((short) 3);
            String kandungan = getContext().getRequest().getParameter("ulasan" + j);
            int bil = j + 1;
            bil = bil + 1;
            if (StringUtils.isEmpty(kandungan)) {

                addSimpleError("Sila isi maklumat pada Perakuan 3." + bil + ".");
                pkk = null;
                break;

            } else {
                permohonanKertasKand.setKandungan(kandungan);
                permohonanKertasKand.setSubtajuk("3." + bil);
                permohonanKertasKand.setCawangan(cawangan);
                pkk = ptService.simpanPermohonanKertasKandungan(permohonanKertasKand);
            }

        }
        if (pkk != null) {
            LOG.info("IF Berjaya Simpan : " + pkk.getKandungan());
            addSimpleMessage("Maklumat telah berjaya disimpan.");
        } else {
            LOG.info("ELSE NULL Gagal Simpan : ");
            addSimpleError("Maklumat gagal disimpan");
        }

        senaraiKertasKandungan = ptService.findByIdLapor(permohonanKertas.getIdKertas(), 3);
        getContext().getRequest().setAttribute("read", Boolean.FALSE);
        getContext().getRequest().setAttribute("edit", Boolean.TRUE);

        return new JSP("strata/Ruang_Udara/KertasPermit_RuangUdara.jsp").addParameter("tab", "true");

    }

    public Resolution SimpanHahalA() {
        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        LOG.info("id mohon : " + idPermohonan);
        permohonan = permohonanDAO.findById(idPermohonan);
        Pengguna pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        InfoAudit infoAudit = new InfoAudit();
        KodCawangan cawangan = new KodCawangan();
        cawangan = kodCawanganDAO.findById(pengguna.getKodCawangan().getKod());
        senaraiLaporanKandungan = new ArrayList<PermohonanLaporanKandungan>();

        permohonanKertas = ptService.findKertas(idPermohonan);
        if (permohonanKertas != null) {
            LOG.info("IF : permohonanKertas not null");
            infoAudit = permohonanKertas.getInfoAudit();
            infoAudit.setDiKemaskiniOleh(pengguna);
            infoAudit.setTarikhKemaskini(new java.util.Date());
        } else {
            LOG.info("ELSE : permohonanKertas is null");
            permohonanKertas = new PermohonanKertas();
            infoAudit.setDimasukOleh(pengguna);
            infoAudit.setTarikhMasuk(new java.util.Date());
            permohonanKertas.setCawangan(cawangan);
            permohonanKertas.setTajuk("KERTAS PERTIMBANGAN PTG");
        }
        permohonanKertas.setInfoAudit(infoAudit);
        permohonanKertas.setPermohonan(permohonan);


        permohonanKertas = ptService.simpanPermohonanKertasObject(permohonanKertas);
        int count = Integer.parseInt(getContext().getRequest().getParameter("rowCount4"));
        senaraiKertasKandungan4 = ptService.findByIdLapor(permohonanKertas.getIdKertas(), 4);
        PermohonanKertasKandungan permohonanKertasKand;

        System.out.println("--------count------------" + count);
        for (int j = 1; j <= count; j++) {
            if (senaraiKertasKandungan4.size() != 0 && j <= senaraiKertasKandungan4.size()) {
                LOG.info("IF : senaraiKertasKandungan4 not null");
                permohonanKertasKand = (PermohonanKertasKandungan) senaraiKertasKandungan4.get(j - 1);

                if (permohonanKertasKand != null) {
                    infoAudit = permohonanKertas.getInfoAudit();
                    infoAudit.setDiKemaskiniOleh(pengguna);
                    infoAudit.setTarikhKemaskini(new java.util.Date());
                }
            } else {
                LOG.info("ELSE : senaraiKertasKandungan4 is null");
                permohonanKertasKand = new PermohonanKertasKandungan();
                infoAudit = new InfoAudit();
                infoAudit.setDimasukOleh(pengguna);
                infoAudit.setTarikhMasuk(new java.util.Date());
                permohonanKertasKand.setInfoAudit(infoAudit);
            }

            System.out.println("-------out----laporanKand---------" + permohonanKertasKand);
            permohonanKertasKand.setKertas(permohonanKertas);
            permohonanKertasKand.setBil((short) 4);
            String kandungan = getContext().getRequest().getParameter("perakuan" + j);
            if (StringUtils.isEmpty(kandungan)) {
                addSimpleError("Sila isi maklumat pada Perakuan 4." + j + ".");
            } else {
                permohonanKertasKand.setKandungan(kandungan);
                permohonanKertasKand.setSubtajuk("4." + j);
                permohonanKertasKand.setInfoAudit(infoAudit);
                permohonanKertasKand.setCawangan(cawangan);
                ptService.simpanPermohonanKertasKandungan(permohonanKertasKand);
                addSimpleMessage("Maklumat Telah Berjaya Disimpan.");
            }
        }

        senaraiKertasKandungan4 = ptService.findByIdLapor(permohonanKertas.getIdKertas(), 4);
        getContext().getRequest().setAttribute("read", Boolean.FALSE);
        getContext().getRequest().setAttribute("edit", Boolean.TRUE);

        return new JSP("strata/Ruang_Udara/KertasPermit_RuangUdara.jsp").addParameter("tab", "true");
    }

    public Resolution deleteKandungan() {
        System.out.println("-----------deleteKandungan---------");
        String idKandungan = getContext().getRequest().getParameter("idKandungan");
        System.out.println("-----------deleteSingle---------" + idKandungan);
        if (idKandungan != null) {
            PermohonanKertasKandungan permohonanKertasKand = new PermohonanKertasKandungan();
            permohonanKertasKand = permohonanKertasKandunganDAO.findById(Long.parseLong(idKandungan));
            if (permohonanKertasKand != null) {

                try {
                    ptService.deleteKertasKandungan(permohonanKertasKand);
                    addSimpleMessage("Maklumat telah berjaya dipadam.");
                } catch (Exception e) {
                    LOG.error(e);
                    addSimpleError("Terdapat masalah teknikal.");
                }
            }
        }

        rehydrate();
        getContext().getRequest().setAttribute("read", Boolean.FALSE);
        getContext().getRequest().setAttribute("edit", Boolean.TRUE);

        return new JSP("strata/Ruang_Udara/KertasPermit_RuangUdara.jsp").addParameter("tab", "true");
    }

    public Resolution simpanPerakuan() {
        LOG.info(":: IN SAVING PERAKUAN INTO MOHON_FASA ::");
        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        Pengguna pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        permohonan = permohonanDAO.findById(idPermohonan);
        LOG.info("---- IDPERMOHONAN : " + permohonan.getIdPermohonan());
        InfoAudit infoAudit = new InfoAudit();
        KodCawangan cawangan = new KodCawangan();
        cawangan = kodCawanganDAO.findById(pengguna.getKodCawangan().getKod());
        LOG.info("---- perakuan : " + perakuan);
        KodKeputusan kodkeputusan = kodKeputusanDAO.findById(perakuan);
        if (kodkeputusan != null) {
            LOG.info("---- KodKeputusan : " + kodkeputusan.getNama());
        }



        String taskId = (String) getContext().getRequest().getSession().getAttribute("taskId");
        String stageId = "";
        BPelService service = new BPelService();

        if (StringUtils.isNotBlank(taskId)) {
            Task t = null;
            t = service.getTaskFromBPel(taskId, pengguna);
            stageId = t.getSystemAttributes().getStage();
        }

        if (stageId.equals("semakkertasptg")) {
            FasaPermohonan fasapermohonan = ptService.findMohonFasa(idPermohonan, stageId);
            if (fasapermohonan != null) {
                LOG.info("IF : fasapermohonan not null");
                infoAudit = fasapermohonan.getInfoAudit();
                infoAudit.setDiKemaskiniOleh(pengguna);
                infoAudit.setTarikhKemaskini(new java.util.Date());

            } else {
                LOG.info("ELSE : fasapermohonan null then new FasaPermohonan()");
                fasapermohonan = new FasaPermohonan();
                infoAudit.setDimasukOleh(pengguna);
                infoAudit.setTarikhMasuk(new java.util.Date());

            }
            fasapermohonan.setCawangan(cawangan);
            fasapermohonan.setPermohonan(permohonan);
            fasapermohonan.setKeputusan(kodkeputusan);
            fasapermohonan.setUlasan(kodkeputusan.getNama());
            fasapermohonan.setInfoAudit(infoAudit);
            fasapermohonan.setIdAliran(stageId);
            fasapermohonan.setIdPengguna(pengguna.getIdPengguna());
            fasapermohonan.setTarikhKeputusan(new java.util.Date());
            fasapermohonan = ptService.saveFasaMohon(fasapermohonan);
            LOG.info("fasapermohonan " + fasapermohonan.getIdFasa());
            LOG.info("save berjaya!");
            addSimpleMessage("Maklumat Telah Berjaya Disimpan.");
        } else {
            stageId = "semakkertasptg";
            LOG.info("ELSE : Stage default to semakkertasptg ");
            FasaPermohonan fasapermohonan = ptService.findMohonFasa(idPermohonan, stageId);
            if (fasapermohonan != null) {
                LOG.info("IF : fasapermohonan not null");
                infoAudit = fasapermohonan.getInfoAudit();
                infoAudit.setDiKemaskiniOleh(pengguna);
                infoAudit.setTarikhKemaskini(new java.util.Date());

            } else {
                LOG.info("ELSE : fasapermohonan null then new FasaPermohonan()");
                fasapermohonan = new FasaPermohonan();
                infoAudit.setDimasukOleh(pengguna);
                infoAudit.setTarikhMasuk(new java.util.Date());

            }
            fasapermohonan.setCawangan(cawangan);
            fasapermohonan.setPermohonan(permohonan);
            fasapermohonan.setKeputusan(kodkeputusan);
            fasapermohonan.setUlasan(kodkeputusan.getNama());
            fasapermohonan.setInfoAudit(infoAudit);
            fasapermohonan.setIdAliran(stageId);
            fasapermohonan.setIdPengguna(pengguna.getIdPengguna());
            fasapermohonan.setTarikhKeputusan(new java.util.Date());
            fasapermohonan = ptService.saveFasaMohon(fasapermohonan);
            LOG.info("fasapermohonan " + fasapermohonan.getIdFasa());
            LOG.info("save berjaya!");
            addSimpleMessage("Maklumat Telah Berjaya Disimpan.");

        }
        rehydrate();
        getContext().getRequest().setAttribute("read", Boolean.FALSE);
        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        return new JSP("strata/Ruang_Udara/KertasPermit_RuangUdara.jsp").addParameter("tab", "true");
    }

    public String getPerakuan() {
        return perakuan;
    }

    public void setPerakuan(String perakuan) {
        this.perakuan = perakuan;
    }

    public PermohonanLaporanKandungan getLaporanKand() {
        return laporanKand;
    }

    public void setLaporanKand(PermohonanLaporanKandungan laporanKand) {
        this.laporanKand = laporanKand;
    }

    public StrataPtService getPtService() {
        return ptService;
    }

    public void setPtService(StrataPtService ptService) {
        this.ptService = ptService;
    }

    public List<PermohonanLaporanKandungan> getSenaraiLaporanKandungan() {
        return senaraiLaporanKandungan;
    }

    public void setSenaraiLaporanKandungan(List<PermohonanLaporanKandungan> senaraiLaporanKandungan) {
        this.senaraiLaporanKandungan = senaraiLaporanKandungan;
    }

    public String getTujuan() {
        return tujuan;
    }

    public void setTujuan(String tujuan) {
        this.tujuan = tujuan;
    }

    public Hakmilik getHakmilik() {
        return hakmilik;
    }

    public void setHakmilik(Hakmilik hakmilik) {
        this.hakmilik = hakmilik;
    }

    public Pemohon getPemohon() {
        return pemohon;
    }

    public void setPemohon(Pemohon pemohon) {
        this.pemohon = pemohon;
    }

    public Permohonan getPermohonan() {
        return permohonan;
    }

    public void setPermohonan(Permohonan permohonan) {
        this.permohonan = permohonan;
    }

    public LaporanTanah getLaporanTanah() {
        return laporanTanah;
    }

    public void setLaporanTanah(LaporanTanah laporanTanah) {
        this.laporanTanah = laporanTanah;
    }

    public Pihak getPihak1() {
        return pihak1;
    }

    public void setPihak1(Pihak pihak1) {
        this.pihak1 = pihak1;
    }

    /**
     * @return the permohonanPermitButir
     */
    public PermohonanPermitButir getPermohonanPermitButir() {
        return permohonanPermitButir;
    }

    /**
     * @param permohonanPermitButir the permohonanPermitButir to set
     */
    public void setPermohonanPermitButir(PermohonanPermitButir permohonanPermitButir) {
        this.permohonanPermitButir = permohonanPermitButir;
    }

    /**
     * @return the laporanTanahDAO
     */
    public LaporanTanahDAO getLaporanTanahDAO() {
        return laporanTanahDAO;
    }

    /**
     * @param laporanTanahDAO the laporanTanahDAO to set
     */
    public void setLaporanTanahDAO(LaporanTanahDAO laporanTanahDAO) {
        this.laporanTanahDAO = laporanTanahDAO;
    }

    /**
     * @return the permohonanLaporanKandungan
     */
    public PermohonanLaporanKandungan getPermohonanLaporanKandungan() {
        return permohonanLaporanKandungan;
    }

    /**
     * @param permohonanLaporanKandungan the permohonanLaporanKandungan to set
     */
    public void setPermohonanLaporanKandungan(PermohonanLaporanKandungan permohonanLaporanKandungan) {
        this.permohonanLaporanKandungan = permohonanLaporanKandungan;
    }

    public List<PermohonanLaporanKandungan> getSenaraiLaporanK() {
        return senaraiLaporanK;
    }

    public void setSenaraiLaporanK(List<PermohonanLaporanKandungan> senaraiLaporanK) {
        this.senaraiLaporanK = senaraiLaporanK;
    }

    public List<PermohonanLaporanKandungan> getSenaraiLaporanKandungan1() {
        return senaraiLaporanKandungan1;
    }

    public void setSenaraiLaporanKandungan1(List<PermohonanLaporanKandungan> senaraiLaporanKandungan1) {
        this.senaraiLaporanKandungan1 = senaraiLaporanKandungan1;
    }

    public PermohonanKertas getPermohonanKertas() {
        return permohonanKertas;
    }

    public void setPermohonanKertas(PermohonanKertas permohonanKertas) {
        this.permohonanKertas = permohonanKertas;
    }

    public List<PermohonanKertasKandungan> getSenaraiKertasKandungan() {
        return senaraiKertasKandungan;
    }

    public void setSenaraiKertasKandungan(List<PermohonanKertasKandungan> senaraiKertasKandungan) {
        this.senaraiKertasKandungan = senaraiKertasKandungan;
    }

    public List<PermohonanKertasKandungan> getSenaraiKertasKandungan4() {
        return senaraiKertasKandungan4;
    }

    public void setSenaraiKertasKandungan4(List<PermohonanKertasKandungan> senaraiKertasKandungan4) {
        this.senaraiKertasKandungan4 = senaraiKertasKandungan4;
    }

    public boolean isStage1() {
        return stage1;
    }

    public void setStage1(boolean stage1) {
        this.stage1 = stage1;
    }

    public boolean isStage2() {
        return stage2;
    }

    public void setStage2(boolean stage2) {
        this.stage2 = stage2;
    }

    public boolean isStage3() {
        return stage3;
    }

    public void setStage3(boolean stage3) {
        this.stage3 = stage3;
    }

    public String getUlasanDefault() {
        return ulasanDefault;
    }

    public void setUlasanDefault(String ulasanDefault) {
        this.ulasanDefault = ulasanDefault;
    }

    public String getSekatan() {
        return sekatan;
    }

    public void setSekatan(String sekatan) {
        this.sekatan = sekatan;
    }

    public String getSyarat() {
        return syarat;
    }

    public void setSyarat(String syarat) {
        this.syarat = syarat;
    }

    public String getnLot() {
        return nLot;
    }

    public void setnLot(String nLot) {
        this.nLot = nLot;
    }

    public String getnHakmilik() {
        return nHakmilik;
    }

    public void setnHakmilik(String nHakmilik) {
        this.nHakmilik = nHakmilik;
    }

    public String getSyor() {
        return syor;
    }

    public void setSyor(String syor) {
        this.syor = syor;
    }
    
}
