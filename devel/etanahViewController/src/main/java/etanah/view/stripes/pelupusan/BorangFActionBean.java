/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.stripes.pelupusan;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.dao.KodJenisPermitDAO;
import etanah.dao.KodTuntutDAO;
import etanah.model.FasaPermohonan;
import etanah.model.Hakmilik;
import etanah.model.HakmilikPermohonan;
import etanah.model.InfoAudit;
import etanah.model.KodJenisPermit;
import etanah.model.KodUOM;
import etanah.model.Pemohon;
import etanah.model.Pengguna;
import etanah.model.Permit;
import etanah.model.PermitSahLaku;
import etanah.model.Permohonan;
import etanah.model.PermohonanTuntutanKos;
import etanah.view.stripes.pelupusan.disClass.DisLaporanTanahService;
import etanah.service.PelupusanService;
import org.apache.commons.lang.StringUtils;
import etanah.view.etanahActionBeanContext;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;
import org.apache.log4j.Logger;

/**
 *
 * @author afham
 */
@UrlBinding("/pelupusan/borangF")
public class BorangFActionBean extends AbleActionBean {

    @Inject
    PelupusanService pelupusanService;
    @Inject
    KodJenisPermitDAO kodJenisPermitDAO;
    @Inject
    KodTuntutDAO kodTuntutDAO;
    @Inject
    DisLaporanTanahService disLaporanTanahService;
    private Permohonan permohonan;
    private Pengguna pguna;
    private PermohonanTuntutanKos mohonTuntutKos;
    private Permit permit;
    private String idPermohonan;
    private String idHakmilik;
    private String noLot;
    private String noPajakan;
    private String kawasan;
    private String bpm;
    private String daerah;
    private String kodNegeri;
    private HakmilikPermohonan mohonHakmilik;
    private Hakmilik hakmilik;
//    private String syarat1;
//    private String syarat2;
    private Date bertarikh;
    private Date tarikhPendaftaran;
    private Date tarikhHabisTempoh;
    private String sewaTahunan;
    private String tempohPajakan;
    private KodUOM kodTempoh;
    // private String kodTempohSementara;
    private String namaPemegangLesen;
    private String noKPPN;
    private String alamatPemegangPajakan;
    private PermitSahLaku permitSahLaku;
    private PermohonanTuntutanKos permohonanTuntutanKos;
    private Pemohon pemohon;
    private List<FasaPermohonan> fp;
    private BigDecimal jumlahPegangan = new BigDecimal(0.00);
    SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
    private static final Logger log = Logger.getLogger(BorangFActionBean.class);

    @DefaultHandler
    public Resolution editForm() {
//            getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        return new JSP("pelupusan/carigali/Borang_F.jsp").addParameter("tab", "true");
    }

    public Resolution showForm() {
        getContext().getRequest().setAttribute("edit", Boolean.FALSE);
        return new JSP("pelupusan/carigali/Borang_F.jsp").addParameter("tab", "true");
    }

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {

        Pengguna pguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        idHakmilik = (String) getContext().getRequest().getSession().getAttribute("idHakmilik");
        permohonan = pelupusanService.findById(idPermohonan);
        fp = pelupusanService.findFasaPermohonanByIdMohon(idPermohonan);

        if (permohonan.getPermohonanSebelum() != null && permohonan.getKodUrusan().getKod().equals("PJLB")) {
            mohonHakmilik = pelupusanService.findByIdPermohonan(permohonan.getPermohonanSebelum().getIdPermohonan());
            // mohonTuntutKos = pelupusanService.findMohonTuntutKosByIdPermohonanAndIdTuntut(idPermohonan, "DISCR"); COMMENTED BY SHAZWAN 21062012
            mohonTuntutKos = pelupusanService.findMohonTuntutKosByIdPermohonanAndIdTuntut(idPermohonan, "DISLB"); //modifid on 21062012 
            // permohonanTuntutanKos = pelupusanService.findMohonTuntutKosByIdPermohonanAndIdTuntut(idPermohonan, "DISCR") ;
        } else if (permohonan.getPermohonanSebelum() != null && permohonan.getKodUrusan().getKod().equals("MPJLB")) {
            mohonHakmilik = pelupusanService.findByIdPermohonan(permohonan.getPermohonanSebelum().getIdPermohonan());
            mohonTuntutKos = pelupusanService.findMohonTuntutKosByIdPermohonanAndIdTuntut(idPermohonan, "DISLB");
        } else {
            mohonHakmilik = pelupusanService.findByIdPermohonan(idPermohonan);
            kodNegeri = disLaporanTanahService.getConf().getProperty("kodNegeri");
            if (kodNegeri.equals("05")) {
                if (permohonan.getKodUrusan().getKod().equals("PJLB") || permohonan.getKodUrusan().getKod().equals("LSTP")) {
                    mohonTuntutKos = pelupusanService.findMohonTuntutKosByIdPermohonanAndIdTuntut(idPermohonan, "DISLB");

                    if (mohonHakmilik.getJumlahPegangan() != null) {
                        jumlahPegangan = mohonHakmilik.getJumlahPegangan();
                    }
                } else {
                    mohonTuntutKos = pelupusanService.findMohonTuntutKosByIdPermohonanAndIdTuntut(idPermohonan, "DISCR");
                }
            } else {
                mohonTuntutKos = pelupusanService.findMohonTuntutKosByIdPermohonanAndIdTuntut(idPermohonan, "DISCR");
                //permohonanTuntutanKos = pelupusanService.findMohonTuntutKosByIdPermohonanAndIdTuntut(idPermohonan, "DISCR") ;
            }
        }
        permit = pelupusanService.findPermitByIdPermohonan(idPermohonan);
        if (permit != null) {
            permitSahLaku = pelupusanService.findPermitSahLakuByIdMohonIdPermit(idPermohonan, permit.getIdPermit());
        }

        if (mohonHakmilik != null) {
            if (mohonHakmilik.getHakmilik() != null) {
                hakmilik = mohonHakmilik.getHakmilik();
                noLot = hakmilik.getNoLot();
                bpm = hakmilik.getBandarPekanMukim().getNama();
                daerah = hakmilik.getDaerah().getNama();
            } else {
                noLot = mohonHakmilik.getNoLot();
                bpm = mohonHakmilik.getBandarPekanMukimBaru().getNama();
                daerah = mohonHakmilik.getBandarPekanMukimBaru().getDaerah().getNama();
            }
        }

        if (permit != null) {
            tarikhPendaftaran = permit.getTarikhPermit();
//            tarikhHabisTempoh = permit.getTarikhpermitAkhir();
        }
    }

    public Resolution simpan() {

        Pengguna pguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        idHakmilik = (String) getContext().getRequest().getSession().getAttribute("idHakmilik");
        log.info("-------Permit SahLaku Saving--------------------");

        permit = pelupusanService.findPermitByIdPermohonan(idPermohonan);
        log.info("-------permit--------------------" + permit);
        PermitSahLaku permitSahLakuTemp = null;
        if (permit != null) {
            log.info("-------permitSahLaku--------------------" + permitSahLaku);
            permit.setKodCawangan(permohonan.getCawangan());
            InfoAudit ia = permit.getInfoAudit();
            ia.setDiKemaskiniOleh(pguna);
            ia.setTarikhKemaskini(new java.util.Date());
        } else {
            log.info("-------permit is Null--------------------");

            log.info("-------Generating No permit---------");
            permit = new Permit();
            permit.setPermohonan(permohonan);
            InfoAudit ia = new InfoAudit();
            ia.setDimasukOleh(pguna);
            ia.setTarikhMasuk(new Date());
            permit.setInfoAudit(ia);
            permit.setKodCawangan(permohonan.getCawangan());
            permit.setTarikhPermit(new java.util.Date());
        }
        log.info("-------permit Not Null--------------------");

        if (permit.getNoPermit() == null) {
            if (permohonan.getKodUrusan().getKod().equalsIgnoreCase("LSTP") || permohonan.getKodUrusan().getKod().equalsIgnoreCase("LMCRG")) {
                Permit maxpermit = pelupusanService.getMaxOfNoPermit2();

                String maxVal = (maxpermit.getNoPermit()) + 1;
                permit.setNoPermit(maxVal + "");
            } else {
//                Permit maxpermit = pelupusanService.getMaxOfNoPermit();
                Permit maxpermit = pelupusanService.getMaxOfNoPermit2();

//                int maxVal = Integer.parseInt(maxpermit.getNoPermit()) + 1;
                String maxVal = (maxpermit.getNoPermit()) + 1;
                permit.setNoPermit(maxVal + "");
            }
//            if (maxpermit == null) {
//                permit.setNoPermit("1");
//            } else {

        }
        KodJenisPermit kodJenis = kodJenisPermitDAO.findById("G");
        if (permitSahLaku.getTarikhPermitMula() != null) {
            permit.setTarikhPermitMula(permitSahLaku.getTarikhPermitMula());

        }
        if (permitSahLaku.getTarikhPermitTamat() != null) {
            permit.setTarikhpermitAkhir(permitSahLaku.getTarikhPermitTamat());

        }
        permit.setKodJenisPermit(kodJenis);
        permit.setNoPermitStrata(Integer.parseInt(noKPPN));
        pemohon = pelupusanService.findPemohonByIdPemohon(idPermohonan);
        permit.setPihak(pemohon.getPihak());
        permit = pelupusanService.saveOrUpdate(permit);



        permitSahLakuTemp = pelupusanService.findPermitSahLakuByIdMohonIdPermit(idPermohonan, permit.getIdPermit());

        InfoAudit info;
        if (permitSahLakuTemp != null) {
            log.info("-------permitSahLaku Not Null---------------::");
            info = permitSahLakuTemp.getInfoAudit();
            info.setDiKemaskiniOleh(pguna);
            info.setTarikhKemaskini(new java.util.Date());
            permitSahLakuTemp.setInfoAudit(info);

        } else {
            log.info("-------permitSahLaku is Null--------::");
            permitSahLakuTemp = new PermitSahLaku();
            permitSahLakuTemp.setPermit(permit);
            permitSahLakuTemp.setPermohonan(permohonan);
            info = new InfoAudit();
            info.setDimasukOleh(pguna);
            info.setTarikhMasuk(new java.util.Date());
            permitSahLakuTemp.setInfoAudit(info);
            permitSahLakuTemp.setKodCawangan(permohonan.getCawangan());
        }
        if (permitSahLakuTemp.getNoSiri() == null) {
            PermitSahLaku pTemp = pelupusanService.getMaxOfNoSiri();
            int maxSiri = Integer.parseInt(pTemp.getNoSiri()) + 1;

            permitSahLakuTemp.setNoSiri(maxSiri + "");
        }
        permitSahLakuTemp.setTarikhPermit(new java.util.Date());
        permitSahLakuTemp.setTarikhPermitMula(permitSahLaku.getTarikhPermitMula());
        permitSahLakuTemp.setTarikhPermitTamat(permitSahLaku.getTarikhPermitTamat());
        pelupusanService.simpanPermitSahLaku(permitSahLakuTemp);
        String amaunTuntut = getContext().getRequest().getParameter("mohonTuntutKos.amaunTuntutan");
        System.out.println("amaunTuntut  :" + amaunTuntut);
        BigDecimal amaun = null;
        if (StringUtils.isNotBlank(amaunTuntut)) {
            double bayaran = Double.parseDouble(amaunTuntut);
            amaun = BigDecimal.valueOf(bayaran);
            System.out.println("amaun :" + amaun);
        }

        log.info(":: Saving mohon Hakmilik ::");
        kodNegeri = disLaporanTanahService.getConf().getProperty("kodNegeri");
        if (kodNegeri.equals("05")) {
            if (permohonan.getKodUrusan().getKod().equals("LSTP") || permohonan.getKodUrusan().getKod().equals("PJLB")) {
                mohonHakmilik = pelupusanService.findByIdPermohonan(idPermohonan);
                InfoAudit infoAudit = new InfoAudit();
                if (mohonHakmilik != null) {
                    infoAudit = mohonHakmilik.getInfoAudit();
                    infoAudit.setDiKemaskiniOleh(pguna);
                    infoAudit.setTarikhKemaskini(new java.util.Date());
                } else {
                    mohonHakmilik = new HakmilikPermohonan();
                    infoAudit.setDimasukOleh(pguna);
                    infoAudit.setTarikhMasuk(new java.util.Date());
                }
                pelupusanService.simpanHakmilikPermohonan(mohonHakmilik);

                jumlahPegangan = BigDecimal.ZERO;
                BigDecimal fiPengeluaran = BigDecimal.ZERO;
                BigDecimal fiPegangan = BigDecimal.ZERO;
                BigDecimal kosInfra = BigDecimal.ZERO;
                BigDecimal dendaPremium = BigDecimal.ZERO;

                fiPengeluaran = mohonHakmilik.getFiPengeluaran();
                fiPegangan = mohonHakmilik.getFiPegangan();
                kosInfra = mohonHakmilik.getKosInfra();
                dendaPremium = mohonHakmilik.getDendaPremium();

                jumlahPegangan = jumlahPegangan.add(fiPengeluaran.add(fiPegangan.add(kosInfra.add(dendaPremium))));
                mohonHakmilik.setJumlahPegangan(jumlahPegangan);
                pelupusanService.simpanHakmilikPermohonan(mohonHakmilik);

            }
        }

        log.info("-------Saving Mohon Tuntut Kos---------");
        if (permohonan.getKodUrusan().getKod().equals("PJLB") || permohonan.getKodUrusan().getKod().equals("LSTP")) {
            mohonTuntutKos = pelupusanService.findMohonTuntutKosByIdPermohonanAndIdTuntut(idPermohonan, "DISLB"); //CHANGES ON 21062012

        } else {
            mohonTuntutKos = pelupusanService.findMohonTuntutKosByIdPermohonanAndIdTuntut(idPermohonan, "DISCR");


        }
        InfoAudit infoAudit = new InfoAudit();
        if ((mohonTuntutKos != null)) {
            infoAudit = mohonTuntutKos.getInfoAudit();
            infoAudit.setDiKemaskiniOleh(pguna);
            infoAudit.setTarikhKemaskini(new java.util.Date());
        } else {
            mohonTuntutKos = new PermohonanTuntutanKos();
            infoAudit.setDimasukOleh(pguna);
            infoAudit.setTarikhMasuk(new java.util.Date());

//        if (permohonanTuntutanKos != null) {
//            info = permohonanTuntutanKos.getInfoAudit();
//            info.setTarikhKemaskini(new java.util.Date());
//            info.setDiKemaskiniOleh(pguna);
//            permohonanTuntutanKos.setInfoAudit(info);
//        } else {
//            permohonanTuntutanKos = new PermohonanTuntutanKos();
//            info = new InfoAudit();
//            info.setDimasukOleh(pguna);
//            info.setTarikhMasuk(new java.util.Date());
//            permohonanTuntutanKos.setInfoAudit(info);
//       
        }
        mohonTuntutKos.setCawangan(permohonan.getCawangan());
        if (permohonan.getKodUrusan().getKod().equals("LMCRG") || permohonan.getKodUrusan().getKod().equals("PCRG")) {
            mohonTuntutKos.setItem("Bayaran Lesen Cari Gali dan Penjelajahan");
            mohonTuntutKos.setKodTuntut(kodTuntutDAO.findById("DISCR"));
            mohonTuntutKos.setKodTransaksi(kodTuntutDAO.findById("DISCR").getKodKewTrans());
        } else if (permohonan.getKodUrusan().getKod().equals("PJLB")) {
            mohonTuntutKos.setItem("Bayaran Lesen Pajakan Lombong");
            mohonTuntutKos.setKodTuntut(kodTuntutDAO.findById("DISLB")); //changes on 21062012
            mohonTuntutKos.setKodTransaksi(kodTuntutDAO.findById("DISLB").getKodKewTrans()); //changes on 21062012
            //  mohonTuntutKos.setKodTuntut(kodTuntutDAO.findById("DISCR")); //Tukar kod tuntut (For PJLB) changes on 21062012
            //  mohonTuntutKos.setKodTransaksi(kodTuntutDAO.findById("DISCR").getKodKewTrans()); //Tukar kod transaksi changes on 21062012
        } else if (permohonan.getKodUrusan().getKod().equals("LSTP")) {
            mohonTuntutKos.setItem("Amaun Bayaran Tuntutan");
            mohonTuntutKos.setKodTuntut(kodTuntutDAO.findById("DISLB")); //changes on 21062012
            mohonTuntutKos.setKodTransaksi(kodTuntutDAO.findById("DISLB").getKodKewTrans()); //changes on 21062012
        }
        mohonTuntutKos.setPermohonan(permohonan);
        mohonTuntutKos.setInfoAudit(infoAudit);

        if (kodNegeri.equals("05")) {
            if (permohonan.getKodUrusan().getKod().equals("LSTP") || permohonan.getKodUrusan().getKod().equals("PJLB")) {
                mohonTuntutKos.setAmaunTuntutan(mohonHakmilik.getJumlahPegangan());
            }
        } else {
            if (amaun != null) {
                mohonTuntutKos.setAmaunTuntutan(amaun);
            }
        }
        pelupusanService.simpanPermohonanTuntutanKos1(mohonTuntutKos);
        rehydrate();
        addSimpleMessage("Maklumat telah disimpan");
        return new JSP("pelupusan/carigali/Borang_F.jsp").addParameter("tab", "true");
    }

    public Date getBertarikh() {
        return bertarikh;
    }

    public void setBertarikh(Date bertarikh) {
        this.bertarikh = bertarikh;
    }

    public String getBpm() {
        return bpm;
    }

    public void setBpm(String bpm) {
        this.bpm = bpm;
    }

    public String getDaerah() {
        return daerah;
    }

    public void setDaerah(String daerah) {
        this.daerah = daerah;
    }

    public String getIdHakmilik() {
        return idHakmilik;
    }

    public void setIdHakmilik(String idHakmilik) {
        this.idHakmilik = idHakmilik;
    }

    public String getIdPermohonan() {
        return idPermohonan;
    }

    public void setIdPermohonan(String idPermohonan) {
        this.idPermohonan = idPermohonan;
    }

    public String getKawasan() {
        return kawasan;
    }

    public void setKawasan(String kawasan) {
        this.kawasan = kawasan;
    }

    public HakmilikPermohonan getMohonHakmilik() {
        return mohonHakmilik;
    }

    public void setMohonHakmilik(HakmilikPermohonan mohonHakmilik) {
        this.mohonHakmilik = mohonHakmilik;
    }

    public String getNoLot() {
        return noLot;
    }

    public void setNoLot(String noLot) {
        this.noLot = noLot;
    }

    public String getSewaTahunan() {
        return sewaTahunan;
    }

    public void setSewaTahunan(String sewaTahunan) {
        this.sewaTahunan = sewaTahunan;
    }

    public Date getTarikhHabisTempoh() {
        return tarikhHabisTempoh;
    }

    public void setTarikhHabisTempoh(Date tarikhHabisTempoh) {
        this.tarikhHabisTempoh = tarikhHabisTempoh;
    }

    public Date getTarikhPendaftaran() {
        return tarikhPendaftaran;
    }

    public void setTarikhPendaftaran(Date tarikhPendaftaran) {
        this.tarikhPendaftaran = tarikhPendaftaran;
    }

    public String getTempohPajakan() {
        return tempohPajakan;
    }

    public void setTempohPajakan(String tempohPajakan) {
        this.tempohPajakan = tempohPajakan;
    }

    public String getAlamatPemegangPajakan() {
        return alamatPemegangPajakan;
    }

    public void setAlamatPemegangPajakan(String alamatPemegangPajakan) {
        this.alamatPemegangPajakan = alamatPemegangPajakan;
    }

    public String getNamaPemegangLesen() {
        return namaPemegangLesen;
    }

    public void setNamaPemegangLesen(String namaPemegangLesen) {
        this.namaPemegangLesen = namaPemegangLesen;
    }

    public String getNoKPPN() {
        return noKPPN;
    }

    public void setNoKPPN(String noKPPN) {
        this.noKPPN = noKPPN;
    }

    public KodUOM getKodTempoh() {
        return kodTempoh;
    }

    public void setKodTempoh(KodUOM kodTempoh) {
        this.kodTempoh = kodTempoh;
    }

    public String getNoPajakan() {
        return noPajakan;
    }

    public void setNoPajakan(String noPajakan) {
        this.noPajakan = noPajakan;
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

    public Permit getPermit() {
        return permit;
    }

    public void setPermit(Permit permit) {
        this.permit = permit;
    }

    public PermitSahLaku getPermitSahLaku() {
        return permitSahLaku;
    }

    public void setPermitSahLaku(PermitSahLaku permitSahLaku) {
        this.permitSahLaku = permitSahLaku;
    }

    public Permohonan getPermohonan() {
        return permohonan;
    }

    public void setPermohonan(Permohonan permohonan) {
        this.permohonan = permohonan;
    }

    public PermohonanTuntutanKos getPermohonanTuntutanKos() {
        return permohonanTuntutanKos;
    }

    public void setPermohonanTuntutanKos(PermohonanTuntutanKos permohonanTuntutanKos) {
        this.permohonanTuntutanKos = permohonanTuntutanKos;
    }

    public PermohonanTuntutanKos getMohonTuntutKos() {
        return mohonTuntutKos;
    }

    public void setMohonTuntutKos(PermohonanTuntutanKos mohonTuntutKos) {
        this.mohonTuntutKos = mohonTuntutKos;
    }

    public Pengguna getPguna() {
        return pguna;
    }

    public void setPguna(Pengguna pguna) {
        this.pguna = pguna;
    }

    public List<FasaPermohonan> getFp() {
        return fp;
    }

    public void setFp(List<FasaPermohonan> fp) {
        this.fp = fp;
    }

    public String getKodNegeri() {
        return kodNegeri;
    }

    public void setKodNegeri(String kodNegeri) {
        this.kodNegeri = kodNegeri;
    }

    public BigDecimal getJumlahPegangan() {
        return jumlahPegangan;
    }

    public void setJumlahPegangan(BigDecimal jumlahPegangan) {
        this.jumlahPegangan = jumlahPegangan;
    }
}
