/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.stripes.pengambilan;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.dao.HakmilikDAO;
import etanah.dao.HakmilikPermohonanDAO;
import etanah.dao.KodCaraBayaranDAO;
import etanah.dao.PermohonanDAO;
import etanah.model.AmbilPampasan;
import etanah.model.Hakmilik;
import etanah.model.HakmilikPermohonan;
import etanah.model.InfoAudit;
import etanah.model.KodCaraBayaran;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.model.PermohonanMahkamah;
import etanah.model.PermohonanPihak;
import etanah.model.ambil.HakmilikPerbicaraan;
import etanah.model.ambil.PerbicaraanKehadiran;
import etanah.service.PengambilanService;
import etanah.service.PermohonanSupayaBantahanService;
import etanah.service.common.NotisPenerimaanService;
import etanah.view.etanahActionBeanContext;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
 * @author Rajesh
 */
@UrlBinding("/pengambilan/perintah")
public class PerintahActionBean extends AbleActionBean {

    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    HakmilikDAO hakmilikDAO;
    @Inject
    PermohonanSupayaBantahanService permohonanSupayaBantahanService;
    @Inject
    NotisPenerimaanService notisPenerimaanService;
    private static final Logger logger = Logger.getLogger(PerintahActionBean.class);
    private String idPermohonan;
    private String idHakmilik;
    private long idPihak;
    private Permohonan permohonan;
    private Hakmilik hakmilik;
    private PermohonanPihak permohonanPihak;
    private PermohonanMahkamah permohonanMahkamah;
    private String namaPenolongKananPendaftar;
    private String samanPemulaBil;
    private String tarikhSaman;
    private String lokasiSaman;
    private Date tarikhIkrar;
    private Date tarikhTerimaPerintah;
    private String show;
    private String hide = "FALSE";
    private String jam;
    private String minit;
    private String pagiPetang;
    private SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss a");
    //added
    private HakmilikPermohonan hakmilikPermohonan;
    private List<HakmilikPermohonan> senaraihakmilikPermohonan;
    @Inject
    HakmilikPermohonanDAO hakmilikPermohonanDAO;
    @Inject
    PengambilanService pengambilanService;
    @Inject
    KodCaraBayaranDAO kodCaraBayaranDAO;
    private HakmilikPerbicaraan hakmilikPerbicaraan;
    private PerbicaraanKehadiran perbicaraanKehadiran;
    private AmbilPampasan ambilPampasan;
    private BigDecimal amnt = BigDecimal.ZERO;

    @DefaultHandler
    public Resolution showForm1() {
        Pengguna pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        if (idPermohonan != null) {
            permohonan = permohonanDAO.findById(idPermohonan);
            if (permohonan != null) {
                permohonanSupayaBantahanService.simpanPermohonanPihak(permohonan, pengguna);
            }
        }
        show = "Edit";
        return new JSP("pengambilan/Deraf_Perintah.jsp").addParameter("tab", "true");
    }

    public Resolution showForm2() {
        show = "Details";
        return new JSP("pengambilan/Deraf_Perintah.jsp").addParameter("tab", "true");
    }

    public Resolution showForm3() {
        Pengguna pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        if (idPermohonan != null) {
            permohonan = permohonanDAO.findById(idPermohonan);
            if (permohonan != null) {
                permohonanSupayaBantahanService.simpanPermohonanPihak(permohonan, pengguna);
            }
        }
        show = "Edit";
        hide = "TRUE";
        return new JSP("pengambilan/Deraf_Perintah.jsp").addParameter("tab", "true");
    }

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        if (idPermohonan != null) {
            permohonan = permohonanDAO.findById(idPermohonan);
        }
    }

    public Resolution pihakDetails() {
        logger.debug("-------pihakDetails------");

        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        idHakmilik = (String) getContext().getRequest().getParameter("idHakmilik");
        idPihak = Long.parseLong(getContext().getRequest().getParameter("idPihak"));

        permohonanPihak = permohonanSupayaBantahanService.getSenaraiPmohonPihakByIdHakmilikIdPihak(idPermohonan, idHakmilik, idPihak);
        if (permohonanPihak != null) {
            permohonanMahkamah = permohonanSupayaBantahanService.findPermohonanMahkamahByidMP(permohonanPihak.getIdPermohonanPihak());
            if (permohonanMahkamah != null) {
                namaPenolongKananPendaftar = permohonanMahkamah.getNamaPenolongKananPendaftar();
                samanPemulaBil = permohonanMahkamah.getSamanPemulaBil();
                if (permohonanMahkamah.getTarikhSaman() != null) {
                    String tarikh = dateFormat.format(permohonanMahkamah.getTarikhSaman());
                    tarikhSaman = tarikh.substring(0, 10);
                    jam = tarikh.substring(11, 13);
                    minit = tarikh.substring(14, 16);
                    String ampm = tarikh.substring(20, 22);
                    if (ampm.equalsIgnoreCase("AM")) {
                        pagiPetang = "PAGI";
                    } else if (ampm.equalsIgnoreCase("PM")) {
                        pagiPetang = "PETANG";
                    }
                }
                lokasiSaman = permohonanMahkamah.getLokasiSaman();
                tarikhIkrar = permohonanMahkamah.getTarikhIkrar();
                tarikhTerimaPerintah = permohonanMahkamah.getTarikhTerimaPerintah();
            }
        }
        if (show.equals("Edit")) {
            show = "Edit";
            getContext().getRequest().setAttribute("showEdit", Boolean.TRUE);
        }
        if (show.equals("Details")) {
            show = "Details";
            getContext().getRequest().setAttribute("showDetails", Boolean.TRUE);
        }
        if (hide.equals("TRUE")) {
            hide = "TRUE";
        }
        return new JSP("pengambilan/Deraf_Perintah.jsp").addParameter("tab", "true");
    }

    public Resolution simpan() throws ParseException {
        Pengguna pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        idPihak = Long.parseLong(getContext().getRequest().getParameter("idPihak"));
        idHakmilik = (String) getContext().getRequest().getParameter("idHakmilik");
        permohonanPihak = permohonanSupayaBantahanService.getSenaraiPmohonPihakByIdHakmilikIdPihak(idPermohonan, idHakmilik, idPihak);
        InfoAudit infoAudit;

        //Added by Murali 11-10-2011

        //Start:Saving in ambil_hakmilik_bicara table

            hakmilikPermohonan = pengambilanService.findByIdHakmilikIdPermohonan(idPermohonan, permohonanPihak.getHakmilik().getIdHakmilik());
             logger.debug("-------hakmilikPermohonan------" + hakmilikPermohonan);
             
            hakmilikPerbicaraan = permohonanSupayaBantahanService.getHakmilikPerbicaraanByidMH(hakmilikPermohonan.getId());
            logger.debug("-------hakmilikPerbicaraan------" + hakmilikPerbicaraan);

            logger.debug("-------Saving in hakmilikPerbicaraan------::");
            if (hakmilikPerbicaraan == null) {
                logger.debug("-------hakmilikPerbicaraan is null------::");
                hakmilikPerbicaraan = new HakmilikPerbicaraan();
                infoAudit = new InfoAudit();
                infoAudit.setDimasukOleh(pengguna);
                infoAudit.setTarikhMasuk(new java.util.Date());
            } else {
                infoAudit = hakmilikPerbicaraan.getInfoAudit();
                infoAudit.setDiKemaskiniOleh(pengguna);
                infoAudit.setTarikhKemaskini(new java.util.Date());
            }
            hakmilikPerbicaraan.setInfoAudit(infoAudit);
            hakmilikPerbicaraan.setHakmilikPermohonan(hakmilikPermohonan);
            hakmilikPerbicaraan.setCawangan(permohonan.getCawangan());
            permohonanSupayaBantahanService.saveOrUpdateHakmilikPerbicaraan(hakmilikPerbicaraan);
            //End
       
        if (permohonanPihak != null) {
            permohonanMahkamah = permohonanSupayaBantahanService.findPermohonanMahkamahByidMP(permohonanPihak.getIdPermohonanPihak());
            if (permohonanMahkamah == null) {
                permohonanMahkamah = new PermohonanMahkamah();
                infoAudit = new InfoAudit();
                infoAudit.setDimasukOleh(pengguna);
                infoAudit.setTarikhMasuk(new java.util.Date());
            } else {
                infoAudit = permohonanMahkamah.getInfoAudit();
                infoAudit.setDiKemaskiniOleh(pengguna);
                infoAudit.setTarikhKemaskini(new java.util.Date());
            }
            permohonanMahkamah.setInfoAudit(infoAudit);
            permohonanMahkamah.setPermohonanPihak(permohonanPihak);
            permohonanMahkamah.setNamaPenolongKananPendaftar(namaPenolongKananPendaftar);
            if (samanPemulaBil == null) {
                samanPemulaBil = "Tiada Data";
            }
            permohonanMahkamah.setSamanPemulaBil(samanPemulaBil);
            if (tarikhSaman != null && tarikhSaman.length() > 0 && jam.length() > 0 && minit.length() > 0 && pagiPetang.length() > 0) {
                String ampm = "";
                if (pagiPetang.equalsIgnoreCase("PAGI")) {
                    ampm = "AM";
                } else if (pagiPetang.equalsIgnoreCase("PETANG")) {
                    ampm = "PM";
                }
                String strMasa = tarikhSaman + " " + jam + ":" + minit + ":00 " + ampm;
                Date tarikh = dateFormat.parse(strMasa);
                permohonanMahkamah.setTarikhSaman(tarikh);
            }
            permohonanMahkamah.setLokasiSaman(lokasiSaman);
            permohonanMahkamah.setTarikhIkrar(tarikhIkrar);
            permohonanMahkamah.setTarikhTerimaPerintah(tarikhTerimaPerintah);
            permohonanSupayaBantahanService.saveOrupdatePermohonanMahkamah(permohonanMahkamah);


            //Added by Murali 11-10-2011

            //Start:Saving in ambil_bicara_hadir table
            hakmilikPerbicaraan = pengambilanService.getHakmilikPerbicaraanByidMH(hakmilikPermohonan.getId());
            if (hakmilikPerbicaraan != null) {
                perbicaraanKehadiran = pengambilanService.getPerbicaraanKehadiranbyidMPidBicara(permohonanPihak.getIdPermohonanPihak(), hakmilikPerbicaraan.getIdPerbicaraan());
                logger.debug("-------perbicaraanKehadiran------::" + perbicaraanKehadiran);

                logger.debug("-------Saving in ambil_bicara_hadir------::");
                if (perbicaraanKehadiran == null) {
                    perbicaraanKehadiran = new PerbicaraanKehadiran();
                    infoAudit = new InfoAudit();
                    infoAudit.setDimasukOleh(pengguna);
                    infoAudit.setTarikhMasuk(new java.util.Date());
                } else {
                    infoAudit = perbicaraanKehadiran.getInfoAudit();
                    infoAudit.setDiKemaskiniOleh(pengguna);
                    infoAudit.setTarikhKemaskini(new java.util.Date());
                }
                perbicaraanKehadiran.setInfoAudit(infoAudit);
                perbicaraanKehadiran.setPihak(permohonanPihak);
                perbicaraanKehadiran.setCawangan(permohonan.getCawangan());
                perbicaraanKehadiran.setPerbicaraan(hakmilikPerbicaraan);
                perbicaraanKehadiran.setPihak(permohonanPihak);
                permohonanSupayaBantahanService.saveOrUpdatePerbicaraanKehadiran(perbicaraanKehadiran);

                //END

                //Start:Saving in ambil_Pampasan table
                logger.debug("-------Saving in ambil_Pampasan------::");
                perbicaraanKehadiran = pengambilanService.getPerbicaraanKehadiranbyidMPidBicara(permohonanPihak.getIdPermohonanPihak(), hakmilikPerbicaraan.getIdPerbicaraan());
                if (perbicaraanKehadiran != null) {
                    ambilPampasan = permohonanSupayaBantahanService.getAmbilPampasanListByidHadir1(perbicaraanKehadiran.getIdKehadiran());
                    logger.debug("-------ambilPampasan------::"+ambilPampasan);
                    if (ambilPampasan == null) {
                        ambilPampasan = new AmbilPampasan();
                        infoAudit = new InfoAudit();
                        infoAudit.setDimasukOleh(pengguna);
                        infoAudit.setTarikhMasuk(new java.util.Date());
                    } else {
                        infoAudit = ambilPampasan.getInfoAudit();
                        infoAudit.setDiKemaskiniOleh(pengguna);
                        infoAudit.setTarikhKemaskini(new java.util.Date());
                    }
                    ambilPampasan.setInfoAudit(infoAudit);
                    ambilPampasan.setPerbicaraanKehadiran(perbicaraanKehadiran);                    
                    ambilPampasan.setJumTerimaPampasan(amnt);
                    KodCaraBayaran kodCaraBayaran = kodCaraBayaranDAO.findById("T");
                    ambilPampasan.setKodCaraBayaran(kodCaraBayaran);
                    permohonanSupayaBantahanService.saveOrupdateAmbilPampasan(ambilPampasan);
                }
            }//END
        }

        if (show.equals("Edit")) {
            show = "Edit";
            getContext().getRequest().setAttribute("showEdit", Boolean.TRUE);
        }
        if (show.equals("Details")) {
            show = "Details";
            getContext().getRequest().setAttribute("showDetails", Boolean.TRUE);
        }
        if (hide.equals("TRUE")) {
            hide = "TRUE";
        }
        addSimpleMessage("Maklumat telah berjaya disimpan");
        return new JSP("pengambilan/Deraf_Perintah.jsp").addParameter("tab", "true");
    }

    public String getIdPermohonan() {
        return idPermohonan;
    }

    public void setIdPermohonan(String idPermohonan) {
        this.idPermohonan = idPermohonan;
    }

    public Permohonan getPermohonan() {
        return permohonan;
    }

    public void setPermohonan(Permohonan permohonan) {
        this.permohonan = permohonan;
    }

    public Hakmilik getHakmilik() {
        return hakmilik;
    }

    public void setHakmilik(Hakmilik hakmilik) {
        this.hakmilik = hakmilik;
    }

    public String getIdHakmilik() {
        return idHakmilik;
    }

    public void setIdHakmilik(String idHakmilik) {
        this.idHakmilik = idHakmilik;
    }

    public String getLokasiSaman() {
        return lokasiSaman;
    }

    public void setLokasiSaman(String lokasiSaman) {
        this.lokasiSaman = lokasiSaman;
    }

    public String getNamaPenolongKananPendaftar() {
        return namaPenolongKananPendaftar;
    }

    public void setNamaPenolongKananPendaftar(String namaPenolongKananPendaftar) {
        this.namaPenolongKananPendaftar = namaPenolongKananPendaftar;
    }

    public PermohonanMahkamah getPermohonanMahkamah() {
        return permohonanMahkamah;
    }

    public void setPermohonanMahkamah(PermohonanMahkamah permohonanMahkamah) {
        this.permohonanMahkamah = permohonanMahkamah;
    }

    public PermohonanPihak getPermohonanPihak() {
        return permohonanPihak;
    }

    public void setPermohonanPihak(PermohonanPihak permohonanPihak) {
        this.permohonanPihak = permohonanPihak;
    }

    public String getSamanPemulaBil() {
        return samanPemulaBil;
    }

    public void setSamanPemulaBil(String samanPemulaBil) {
        this.samanPemulaBil = samanPemulaBil;
    }

    public Date getTarikhIkrar() {
        return tarikhIkrar;
    }

    public void setTarikhIkrar(Date tarikhIkrar) {
        this.tarikhIkrar = tarikhIkrar;
    }

    public String getJam() {
        return jam;
    }

    public void setJam(String jam) {
        this.jam = jam;
    }

    public String getMinit() {
        return minit;
    }

    public void setMinit(String minit) {
        this.minit = minit;
    }

    public String getPagiPetang() {
        return pagiPetang;
    }

    public void setPagiPetang(String pagiPetang) {
        this.pagiPetang = pagiPetang;
    }

    public String getTarikhSaman() {
        return tarikhSaman;
    }

    public void setTarikhSaman(String tarikhSaman) {
        this.tarikhSaman = tarikhSaman;
    }

    public Date getTarikhTerimaPerintah() {
        return tarikhTerimaPerintah;
    }

    public void setTarikhTerimaPerintah(Date tarikhTerimaPerintah) {
        this.tarikhTerimaPerintah = tarikhTerimaPerintah;
    }

    public String getShow() {
        return show;
    }

    public void setShow(String show) {
        this.show = show;
    }

    public long getIdPihak() {
        return idPihak;
    }

    public void setIdPihak(long idPihak) {
        this.idPihak = idPihak;
    }

    public String getHide() {
        return hide;
    }

    public void setHide(String hide) {
        this.hide = hide;
    }

    public AmbilPampasan getAmbilPampasan() {
        return ambilPampasan;
    }

    public void setAmbilPampasan(AmbilPampasan ambilPampasan) {
        this.ambilPampasan = ambilPampasan;
    }

    public HakmilikPerbicaraan getHakmilikPerbicaraan() {
        return hakmilikPerbicaraan;
    }

    public void setHakmilikPerbicaraan(HakmilikPerbicaraan hakmilikPerbicaraan) {
        this.hakmilikPerbicaraan = hakmilikPerbicaraan;
    }

    public PerbicaraanKehadiran getPerbicaraanKehadiran() {
        return perbicaraanKehadiran;
    }

    public void setPerbicaraanKehadiran(PerbicaraanKehadiran perbicaraanKehadiran) {
        this.perbicaraanKehadiran = perbicaraanKehadiran;
    }

    public BigDecimal getAmnt() {
        return amnt;
    }

    public void setAmnt(BigDecimal amnt) {
        this.amnt = amnt;
    }
    
}
