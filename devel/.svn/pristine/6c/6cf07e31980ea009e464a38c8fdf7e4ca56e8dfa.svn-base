/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.stripes.pelupusan;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import electric.xml.ParseException;
import etanah.dao.PermohonanBahanBatuanDAO;
import etanah.dao.PermohonanDAO;
import etanah.dao.HakmilikDAO;
import etanah.dao.HakmilikPerbicaraanDAO;
import etanah.model.ambil.PerbicaraanKehadiran;
import etanah.dao.PermohonanJenteraDAO;
import etanah.model.HakmilikPermohonan;
import etanah.model.InfoAudit;
import etanah.model.KodBahanBatu;
import etanah.model.KodBandarPekanMukim;
import etanah.model.KodCawangan;
import etanah.model.KodSuku;
import etanah.model.Pemohon;
import etanah.model.PemohonHubungan;
import etanah.model.Pengguna;
import etanah.model.PenghantarNotis;
import etanah.model.Permohonan;
import etanah.model.PermohonanBahanBatuan;
import etanah.model.PermohonanJentera;
import etanah.model.Pihak;
import etanah.model.ambil.HakmilikPerbicaraan;
import etanah.service.PelupusanService;
import etanah.service.common.PerbicaraanService;
import etanah.view.etanahActionBeanContext;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import etanah.view.etanahActionBeanContext;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.RedirectResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;
import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author Akmal
 */
@UrlBinding("/pelupusan/borangN_adat")
public class BorangNAdatActionBean extends AbleActionBean {

    @Inject
    PermohonanBahanBatuanDAO permohonanBahanBatuanDAO;
    @Inject
    PelupusanService pelupusanService;
    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    HakmilikDAO hakmilikDAO;
    @Inject
    HakmilikPerbicaraanDAO hakmilikPerbicaraanDAO;
    private List<String> tarikhBicara = new ArrayList<String>();
     private List<String> tarikhTerimaBicara = new ArrayList<String>();
    private List<String> waktuPerbicaraan = new ArrayList<String>();
    private List<String> lokasiBicara = new ArrayList<String>();
    private List<String> jam = new ArrayList<String>();
    private List<String> minit = new ArrayList<String>();
    private List<String> ampm = new ArrayList<String>();
    private Pemohon pemohon;
    private Permohonan permohonan;
    private Pengguna pguna;
    private String idPermohonan;
    private List<PemohonHubungan> pemohonHubungan;
    private List<PemohonHubungan> pemohonHubunganSaudara ;
    private HakmilikPermohonan hakmilikPermohonan;
    private List<HakmilikPermohonan> hakmilikPermohonanList;
    private List senaraiBicara = new ArrayList();
//    private List senaraiPemohon = new ArrayList();
    private HakmilikPerbicaraan hakmilikPerbicaraan;
    private PerbicaraanKehadiran perbicaraanKehadiranHadir ;
    private PenghantarNotis penghantarNotis ;
    @Inject
    private PerbicaraanService perbicaraanService;
    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm aaa");
    SimpleDateFormat sdf1 = new SimpleDateFormat("dd/MM/yyyy");
    private static final Logger LOG = Logger.getLogger(BorangNAdatActionBean.class);
    private List<HakmilikPerbicaraan> hakmilikPList;
    private List<PerbicaraanKehadiran> hakmilikPHList ;
    private List<PenghantarNotis> penghantarNotisList ;
    private String namaKetuaSuku ;
    private String tarikhTerimaBicara1 ;
    private KodSuku kodSuku ;
    private Pihak pihak ;

    @DefaultHandler
    public Resolution showForm() {
        return new JSP("pelupusan/tanah_adat/borangN_adat.jsp").addParameter("tab", "true");
    }

    public Resolution popup() {
        return new JSP("pelupusan/tanah_adat/add_borangN_adat.jsp").addParameter("popup", "true");
    }
    
     public Resolution terimaNotis() {
        return new JSP("pelupusan/tanah_adat/terima_notis.jsp").addParameter("tab", "true");
    }

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        LOG.info("--------------" + idPermohonan);
        pguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        permohonan = permohonanDAO.findById(idPermohonan);
        hakmilikPermohonan = pelupusanService.findByIdPermohonan(idPermohonan);
        if (permohonan != null) {
            senaraiBicara = new ArrayList();
            pemohon = pelupusanService.findPemohonByIdPemohon(idPermohonan);
            senaraiBicara.add(pemohon.getPihak().getNama());
            if (pemohon != null) {
                pemohonHubungan = pelupusanService.findHubunganByIDANAK(pemohon.getIdPemohon());
                pemohonHubunganSaudara = pelupusanService.findHubunganByIDPemohonSaudara(pemohon.getIdPemohon());
                pihak = pemohon.getPihak() ;
                if(pihak.getSuku() != null){
                    if(pihak.getSuku().getPemimpinNama() != null){
                        namaKetuaSuku = pihak.getSuku().getPemimpinNama() ;
                    if(namaKetuaSuku != null)
                    senaraiBicara.add(namaKetuaSuku);
                    }
                }
            }
            // Add untuk anak
            if (!pemohonHubungan.isEmpty()) {
                for (int i = 0; i < pemohonHubungan.size(); i++) {
                    senaraiBicara.add(pemohonHubungan.get(i).getNama());
                }
            }
            //Add for saudara
            if(pemohonHubunganSaudara.isEmpty()){
                   for (int i = 0; i < pemohonHubunganSaudara.size(); i++) {
                    senaraiBicara.add(pemohonHubunganSaudara.get(i).getNama());
                }
            }
            if (!senaraiBicara.isEmpty()) {
                hakmilikPList = pelupusanService.findHakmilikPerbicaraanByIdMHList(hakmilikPermohonan.getId());
                if (!hakmilikPList.isEmpty()) {
                    for (int i = 0; i < senaraiBicara.size(); i++) {

                        //   hakmilikPerbicaraan = perbicaraanService.getHakmilikPerbicaraanByIdMH(hakmilikPermohonan.getId());
                        hakmilikPerbicaraan = hakmilikPList.get(i);
                        try {
                            String lb = hakmilikPerbicaraan.getLokasiBicara();
                            lokasiBicara.add(lb);
                            String tb = sdf.format(hakmilikPerbicaraan.getTarikhBicara());

                            String tb1 = tb.substring(0, 10);
                            String tb2 = tb.substring(11, 13);
                            String tb3 = tb.substring(14, 16);
                            String tb4 = tb.substring(17, 19);
                            tarikhBicara.add(tb1);
                            jam.add(tb2);
                            minit.add(tb3);
                            ampm.add(tb4);
                        } catch (Exception e) {
                        }
                    }
                }
            }
            penghantarNotis = pelupusanService.findPenghantarNotisByNoPengenalan(pihak.getNoPengenalan());
            if(penghantarNotis != null){
                tarikhTerimaBicara1 = sdf1.format(penghantarNotis.getInfoAudit().getTarikhMasuk()) ;
            }
        }
    }

    public Resolution simpanSenaraiBicara() {
        int size = senaraiBicara.size();
        LOG.info("SIZE ---->" + size);
        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);

        Permohonan permohonan = permohonanDAO.findById(idPermohonan);
        KodCawangan cawangan = permohonan.getCawangan();
        hakmilikPermohonan = pelupusanService.findByIdPermohonan(idPermohonan);
        hakmilikPList = pelupusanService.findHakmilikPerbicaraanByIdMHList(hakmilikPermohonan.getId());
        if (!hakmilikPList.isEmpty()) {
            for (int i = 0; i < hakmilikPList.size(); i++) {
                HakmilikPerbicaraan hakmilikPerbicaraan1 = hakmilikPList.get(i);
                if(hakmilikPerbicaraan1 != null){
                    PerbicaraanKehadiran perbicaraanKehadiran1 = pelupusanService.findPerbicaraanKehadiranByIdBicara(hakmilikPerbicaraan1.getIdPerbicaraan());
                    if(perbicaraanKehadiran1 != null)
                    pelupusanService.deletePerbicaraanHadir(perbicaraanKehadiran1);
                }
                hakmilikPerbicaraan1.setHakmilikPermohonan(hakmilikPerbicaraan.getHakmilikPermohonan());
                hakmilikPerbicaraan1.setTarikhBicara(hakmilikPerbicaraan.getTarikhBicara());
                pelupusanService.deleteHakmilikPerbicaraan(hakmilikPerbicaraan1);
            }
        }
        hakmilikPList = pelupusanService.findHakmilikPerbicaraanByIdMHList(hakmilikPermohonan.getId());
        if (hakmilikPList.isEmpty()) {
            for (int i = 0; i < size; i++) {
                hakmilikPerbicaraan = new HakmilikPerbicaraan();
                InfoAudit info = new InfoAudit();
                info.setDimasukOleh(peng);
                info.setTarikhMasuk(new java.util.Date());
                hakmilikPerbicaraan.setInfoAudit(info);
                hakmilikPerbicaraan.setCawangan(cawangan);
                hakmilikPerbicaraan.setHakmilikPermohonan(hakmilikPermohonan);

                try {

                    if (i < lokasiBicara.size()) {
                        if (lokasiBicara.get(i) != null) {
                            hakmilikPerbicaraan.setLokasiBicara(lokasiBicara.get(i));
                        }
                    }

                    if (i < tarikhBicara.size()) {
                        if (tarikhBicara.get(i) != null) {
                            String StrTarikhBicara = tarikhBicara.get(i);
                            StrTarikhBicara = StrTarikhBicara + " " + jam.get(i) + ":" + minit.get(i) + " " + ampm.get(i);
                            hakmilikPerbicaraan.setTarikhBicara(sdf.parse(StrTarikhBicara));
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    addSimpleError("Invalid Data");
                }
                perbicaraanService.saveSingleHakmilikPerbicaraan(hakmilikPerbicaraan);
                
                perbicaraanKehadiranHadir = new PerbicaraanKehadiran() ;
                perbicaraanKehadiranHadir.setPerbicaraan(hakmilikPerbicaraan);
                perbicaraanKehadiranHadir.setInfoAudit(info);
                perbicaraanKehadiranHadir.setCawangan(permohonan.getCawangan());
                perbicaraanKehadiranHadir.setNama(senaraiBicara.get(i).toString());
                pelupusanService.simpanPerbicaraanKehadiran(perbicaraanKehadiranHadir);
            }
        }

        addSimpleMessage("Maklumat telah berjaya disimpan.");
        return new ForwardResolution("/WEB-INF/jsp/pelupusan/tanah_adat/borangN_adat.jsp").addParameter("tab", "true");
    }
    
    public Resolution simpanTerimaBicara() throws java.text.ParseException {
        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);

        Permohonan permohonan = permohonanDAO.findById(idPermohonan);
        KodCawangan cawangan = permohonan.getCawangan();
        pemohon = pelupusanService.findPemohonByIdPemohon(idPermohonan);
        if(pemohon != null) {
        pihak = pemohon.getPihak() ;
        penghantarNotis = pelupusanService.findPenghantarNotisByNoPengenalan(pihak.getNoPengenalan());
        InfoAudit ia = new InfoAudit() ;
        if(penghantarNotis != null){
            ia = penghantarNotis.getInfoAudit() ;
            ia.setDiKemaskiniOleh(peng);
            ia.setTarikhKemaskini(new java.util.Date());
            ia.setTarikhMasuk(new java.util.Date());
            penghantarNotis.setInfoAudit(ia);
        }
        else {
            penghantarNotis = new PenghantarNotis() ;
            ia.setDimasukOleh(peng);
            ia.setTarikhMasuk(new java.util.Date());
            penghantarNotis.setInfoAudit(ia);
        }
        penghantarNotis.setCawangan(cawangan);
        penghantarNotis.setKodJenisPengenalan(pihak.getJenisPengenalan());
        penghantarNotis.setNoPengenalan(pihak.getNoPengenalan());
        penghantarNotis.setNama(pihak.getNama());
        pelupusanService.simpanPenghantarNotis(penghantarNotis);
        }
         addSimpleMessage("Maklumat telah berjaya disimpan.");
         rehydrate() ;
        return new JSP("pelupusan/tanah_adat/terima_notis.jsp").addParameter("tab", "true");
    }

    public Pemohon getPemohon() {
        return pemohon;
    }

    public void setPemohon(Pemohon pemohon) {
        this.pemohon = pemohon;
    }

    public List<PemohonHubungan> getPemohonHubungan() {
        return pemohonHubungan;
    }

    public void setPemohonHubungan(List<PemohonHubungan> pemohonHubungan) {
        this.pemohonHubungan = pemohonHubungan;
    }

    public List getSenaraiBicara() {
        return senaraiBicara;
    }

    public void setSenaraiBicara(List senaraiBicara) {
        this.senaraiBicara = senaraiBicara;
    }

    public List<String> getAmpm() {
        return ampm;
    }

    public void setAmpm(List<String> ampm) {
        this.ampm = ampm;
    }

    public String getIdPermohonan() {
        return idPermohonan;
    }

    public void setIdPermohonan(String idPermohonan) {
        this.idPermohonan = idPermohonan;
    }

    public List<String> getJam() {
        return jam;
    }

    public void setJam(List<String> jam) {
        this.jam = jam;
    }

    public List<String> getLokasiBicara() {
        return lokasiBicara;
    }

    public void setLokasiBicara(List<String> lokasiBicara) {
        this.lokasiBicara = lokasiBicara;
    }

    public List<String> getMinit() {
        return minit;
    }

    public void setMinit(List<String> minit) {
        this.minit = minit;
    }

    public Permohonan getPermohonan() {
        return permohonan;
    }

    public void setPermohonan(Permohonan permohonan) {
        this.permohonan = permohonan;
    }

    public Pengguna getPguna() {
        return pguna;
    }

    public void setPguna(Pengguna pguna) {
        this.pguna = pguna;
    }

    public List<String> getTarikhBicara() {
        return tarikhBicara;
    }

    public void setTarikhBicara(List<String> tarikhBicara) {
        this.tarikhBicara = tarikhBicara;
    }

    public List<String> getWaktuPerbicaraan() {
        return waktuPerbicaraan;
    }

    public void setWaktuPerbicaraan(List<String> waktuPerbicaraan) {
        this.waktuPerbicaraan = waktuPerbicaraan;
    }

    public HakmilikPerbicaraan getHakmilikPerbicaraan() {
        return hakmilikPerbicaraan;
    }

    public void setHakmilikPerbicaraan(HakmilikPerbicaraan hakmilikPerbicaraan) {
        this.hakmilikPerbicaraan = hakmilikPerbicaraan;
    }

    public HakmilikPermohonan getHakmilikPermohonan() {
        return hakmilikPermohonan;
    }

    public void setHakmilikPermohonan(HakmilikPermohonan hakmilikPermohonan) {
        this.hakmilikPermohonan = hakmilikPermohonan;
    }

    public List<HakmilikPermohonan> getHakmilikPermohonanList() {
        return hakmilikPermohonanList;
    }

    public void setHakmilikPermohonanList(List<HakmilikPermohonan> hakmilikPermohonanList) {
        this.hakmilikPermohonanList = hakmilikPermohonanList;
    }

    public List<HakmilikPerbicaraan> getHakmilikPList() {
        return hakmilikPList;
    }

    public void setHakmilikPList(List<HakmilikPerbicaraan> hakmilikPList) {
        this.hakmilikPList = hakmilikPList;
    }

    public String getNamaKetuaSuku() {
        return namaKetuaSuku;
    }

    public void setNamaKetuaSuku(String namaKetuaSuku) {
        this.namaKetuaSuku = namaKetuaSuku;
    }

    public List<PemohonHubungan> getPemohonHubunganSaudara() {
        return pemohonHubunganSaudara;
    }

    public void setPemohonHubunganSaudara(List<PemohonHubungan> pemohonHubunganSaudara) {
        this.pemohonHubunganSaudara = pemohonHubunganSaudara;
    }

    public KodSuku getKodSuku() {
        return kodSuku;
    }

    public void setKodSuku(KodSuku kodSuku) {
        this.kodSuku = kodSuku;
    }

    public Pihak getPihak() {
        return pihak;
    }

    public void setPihak(Pihak pihak) {
        this.pihak = pihak;
    }

    public List<PerbicaraanKehadiran> getHakmilikPHList() {
        return hakmilikPHList;
    }

    public void setHakmilikPHList(List<PerbicaraanKehadiran> hakmilikPHList) {
        this.hakmilikPHList = hakmilikPHList;
    }

    public PerbicaraanKehadiran getPerbicaraanKehadiranHadir() {
        return perbicaraanKehadiranHadir;
    }

    public void setPerbicaraanKehadiranHadir(PerbicaraanKehadiran perbicaraanKehadiranHadir) {
        this.perbicaraanKehadiranHadir = perbicaraanKehadiranHadir;
    }

    public List<String> getTarikhTerimaBicara() {
        return tarikhTerimaBicara;
    }

    public void setTarikhTerimaBicara(List<String> tarikhTerimaBicara) {
        this.tarikhTerimaBicara = tarikhTerimaBicara;
    }

    public PenghantarNotis getPenghantarNotis() {
        return penghantarNotis;
    }

    public void setPenghantarNotis(PenghantarNotis penghantarNotis) {
        this.penghantarNotis = penghantarNotis;
    }

    public List<PenghantarNotis> getPenghantarNotisList() {
        return penghantarNotisList;
    }

    public void setPenghantarNotisList(List<PenghantarNotis> penghantarNotisList) {
        this.penghantarNotisList = penghantarNotisList;
    }

    public String getTarikhTerimaBicara1() {
        return tarikhTerimaBicara1;
    }

    public void setTarikhTerimaBicara1(String tarikhTerimaBicara1) {
        this.tarikhTerimaBicara1 = tarikhTerimaBicara1;
    }
    
    
}
