package etanah.view.daftar;

import able.stripes.JSP;
import etanah.view.kaunter.*;
import java.util.List;

import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;

import com.google.inject.Inject;
import etanah.dao.HakmilikDAO;
import etanah.dao.KodJabatanDAO;
import etanah.dao.KodRujukanDAO;
import etanah.dao.PermohonanDAO;
import etanah.dao.PermohonanHubunganDAO;
import etanah.model.*;
import etanah.service.common.NotisService;
import etanah.service.common.PermohonanRujukanLuarService;
import etanah.service.common.PermohonanService;
import etanah.service.daftar.PembetulanService;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import oracle.bpel.services.workflow.WorkflowException;
import org.apache.log4j.Logger;
import org.apache.commons.lang.StringUtils;

/**

 * @author wan.fairul
 *
 */
@UrlBinding("/daftar/rekodPermotongankaveat")
public class UtilitiRekodkvpt extends PermohonanKaunter {

    private Permohonan permohonan;
    private Permohonan permohonanSebelum;
    private PermohonanRujukanLuar permohonanRujLuar;
    private PermohonanHubungan permohonanHubungan;
    private Pengguna pengguna;
    private List<PermohonanRujukanLuar> senaraiPermohonanRujukanLuar;
    private List<Pemohon> listPemohon;
    private List<HakmilikPihakBerkepentingan> hpkList = new ArrayList<HakmilikPihakBerkepentingan>();
    private List<PermohonanHubungan> senaraiHubungan;
    private String noFail;
    private Notis notis;
    private String idHakmilik;
    private Hakmilik hakmilik;
    private String tkhSidang;
    private String tkhRujukan;
    private String tkhTamat;
    private String tkhKuatKuasa;
    private String tkhNotis;
    private String tkhTampal;
    private String tkhWarta;
    private String tkhHantar;
    private String idSasar;
    private char status;
    
    @Inject
    private PermohonanDAO permohonanDAO;
    @Inject
    private PermohonanHubunganDAO permohonanHubunganDAO;
    @Inject
    private PembetulanService pService;
    @Inject
    NotisService notisService;
    @Inject
    PermohonanService permohonanService;
    @Inject
    KodJabatanDAO kodJabatanDAO;
    @Inject
    KodRujukanDAO kodRujukanDAO;
    @Inject
    PermohonanRujukanLuarService service;
    @Inject
    HakmilikDAO hakmilikDAO;
    
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    private static final Logger LOG = Logger.getLogger(UtilitiRekodkvpt.class);
    
    @DefaultHandler
    public Resolution insertIdPermohonan() {
        return new ForwardResolution("/WEB-INF/jsp/daftar/main_rekod_kvpt.jsp");
    }

    public Resolution checkPermohonan() throws WorkflowException {

        if (permohonan == null) {
            addSimpleError("Sila masukkan ID Permohonan/Perserahan");
            return new ForwardResolution("/WEB-INF/jsp/daftar/main_rekod_kvpt.jsp");
        }
        
        String idPermohonan = permohonan.getIdPermohonan();
        LOG.debug("======== idPermohonan :" +idPermohonan);
        
        if (idPermohonan == null) {
            addSimpleError("Sila masukkan ID Permohonan/Perserahan");
            return new ForwardResolution("/WEB-INF/jsp/daftar/main_rekod_kvpt.jsp");
        }

        permohonan = permohonanDAO.findById(idPermohonan);
        LOG.debug("======== Permohonan1 :" +permohonan);
        if (permohonan == null) {
            System.out.print("Permohonan " + idPermohonan + " tidak dijumpai.");
            addSimpleError("Permohonan " + idPermohonan + " tidak dijumpai.");
            return new ForwardResolution("/WEB-INF/jsp/daftar/main_rekod_kvpt.jsp");
        } else {
            senaraiPermohonanRujukanLuar = permohonan.getSenaraiRujukanLuar();
            LOG.debug("======== Senarai Permohonan Rujukan Luar :" +senaraiPermohonanRujukanLuar);
            List<HakmilikPermohonan> hp = permohonan.getSenaraiHakmilik();
            LOG.debug("======== kod.status :" +permohonan.getKodUrusan().getKodPerserahan().getKod());
            if ((permohonan.getKodUrusan().getKodPerserahan().getKod().equals("SC")) 
                    || (permohonan.getKodUrusan().getKodPerserahan().getKod().equals("B"))){
                for (HakmilikPermohonan h : hp) {
                    idHakmilik = h.getHakmilik().getIdHakmilik();
                    HakmilikUrusan hu = pService.findHUrusan(idHakmilik, idPermohonan);
                    String batal = hu.getIdPermohonanBatal();
                    if(batal !=null){
                        status = hu.getAktif();
                    }
                    LOG.debug("======== idPermohonan :" +idPermohonan +"=====idhakmilik======="+idHakmilik);
                    senaraiHubungan = permohonanService.getSenaraiHubungan(idPermohonan, idHakmilik ); 
                    LOG.debug("======== senaraiHubungan.size() :" +senaraiHubungan.size());
//                  LOG.debug("======== list senarai hubungan :" +senaraiHubungan.get(0)); 
                    for (int i =0; i < senaraiHubungan.size(); i++) {
                        permohonanHubungan = senaraiHubungan.get(i);
                        if (permohonanHubungan != null){
//                            Long idHakmilik1 = new Long(idHakmilik);
//                            permohonanHubungan = permohonanHubunganDAO.findById(idHakmilik1);
                            idSasar = permohonanHubungan.getPermohonanSasaran().getIdPermohonan();
                            LOG.debug("======== idSasar :" +idSasar);
                        }                        
                    }
                }
                
                listPemohon = permohonan.getSenaraiPemohon();
                LOG.debug("======== list Permohonan :" +listPemohon);
                
                if (!(senaraiPermohonanRujukanLuar.isEmpty())) {
                    permohonanRujLuar = senaraiPermohonanRujukanLuar.get(0);
                    if (permohonanRujLuar.getNoFail() != null) {
                        noFail = permohonanRujLuar.getNoFail();
                        LOG.debug("======== No Fail :" +noFail);
                    }
                }
                List<Notis> senaraiNotis = notisService.getSenaraiNotis(permohonan.getIdPermohonan());
                if (senaraiNotis.size() > 0) {
                    notis = senaraiNotis.get(0);
                    if (notis != null) {
                        if (notis.getTarikhNotis() != null) {
                            tkhNotis = sdf.format(notis.getTarikhNotis());
                        }
                        if (notis.getTarikhTampal() != null) {
                            tkhTampal = sdf.format(notis.getTarikhTampal());
                        }
                        if (notis.getTarikhHantar() != null) {
                            tkhHantar = sdf.format(notis.getTarikhHantar());
                        }
                        if (notis.getWarta() != null) {
                            if (notis.getWarta().getTarikhRujukan() != null) {
                                tkhWarta = sdf.format(notis.getWarta().getTarikhRujukan());
                            }
                            if (notis.getWarta().getTarikhTamat() != null) {
                                tkhTamat = sdf.format(notis.getWarta().getTarikhTamat());
                            }
                        }
                    }
                }
                
                 Hakmilik hk = new Hakmilik();
                    if (StringUtils.isBlank(idHakmilik)) {
                        hk = permohonan.getSenaraiHakmilik().get(0).getHakmilik();
                    } else {
                        hk = hakmilikDAO.findById(idHakmilik);
                    }
                    if (hk != null) {
                        List<HakmilikPihakBerkepentingan> l = hk.getSenaraiPihakBerkepentingan();
                        for (HakmilikPihakBerkepentingan h : l) {
                            if (((h.getAktif() == 'Y')&&(h.getJenis().getKod().contains("PM")))||((h.getAktif() == 'Y')&&(h.getJenis().getKod().contains("PKS")))) {
                                hpkList.add(h);

                            }
                        }
                    }
//                LOG.debug("======== idPermohonan :" +idPermohonan +"=====idhakmilik======="+idHakmilik);
//                List<PermohonanHubungan> senaraiHubungan = permohonanService.getSenaraiHubungan(idPermohonan, idHakmilik );
////                LOG.debug("======== list senarai hubungan :" +senaraiHubungan.get(0));
//                for (PermohonanHubungan ph : senaraiHubungan) {
//                    if (ph != null){
//                        idSasar = ph.getPermohonanSasaran().getIdPermohonan();
//                        LOG.debug("======== idSasar :" +idSasar);
//                    }
//                }
            }
        }
        return new ForwardResolution("/WEB-INF/jsp/daftar/permohonan_kvpt.jsp");
    }

    public Resolution viewhakmilikDetail() {
        idHakmilik = (String) getContext().getRequest().getParameter("idHakmilik");
        hakmilik = pService.findHakmilik(idHakmilik);
        return new JSP("daftar/utiliti/paparan_hakmilik_single.jsp").addParameter("popup", "true");
    }

    public Resolution saveNotisInfo() {
        try {
            InfoAudit ia = new InfoAudit();
            PermohonanRujukanLuar ruj = null;

            if (notis != null) {
                ruj = notis.getWarta();
            }

            if (ruj == null) {
                LOG.debug("RUJ LUAR : NULL");
                ruj = new PermohonanRujukanLuar();
                ia.setDimasukOleh(pengguna);
                ia.setTarikhMasuk(new Date());
            } else {
                LOG.debug("RUJ LUAR : NOT NULL");
                if (ruj.getInfoAudit() == null) {
                    ia.setDimasukOleh(pengguna);
                    ia.setTarikhMasuk(new Date());
                } else {
                    ia = ruj.getInfoAudit();
                    ia.setDiKemaskiniOleh(pengguna);
                    ia.setTarikhKemaskini(new Date());
                }
            }

            if (StringUtils.isNotBlank(tkhWarta)) {
                ruj.setTarikhRujukan(sdf.parse(tkhWarta));
            }
            if (StringUtils.isNotBlank(tkhTamat)) {
                ruj.setTarikhTamat(sdf.parse(tkhTamat));
            }

            ruj.setPermohonan(permohonan);
            ruj.setKodRujukan(kodRujukanDAO.findById("FL"));
            ruj.setCawangan(pengguna.getKodCawangan());
            ruj.setInfoAudit(ia);
            service.saveOrUpdate(ruj);
            notis.setWarta(ruj);

            if (permohonan.getKodUrusan() != null) {
                notis.setJabatan(permohonan.getKodUrusan().getJabatan());
            } else {
                notis.setJabatan(kodJabatanDAO.findById("16"));//pendaftaran
            }

            if (StringUtils.isNotBlank(tkhNotis)) {
                notis.setTarikhNotis(sdf.parse(tkhNotis));
            }

            if (StringUtils.isNotBlank(tkhTampal)) {
                notis.setTarikhTampal(sdf.parse(tkhTampal));
            }

            notisService.saveOrUpdate(notis);
            addSimpleMessage("Kemaskini Data Berjaya.");
        } catch (Exception ex) {
            addSimpleError("Kemaskini Data Gagal : " + ex.getMessage());
            ex.printStackTrace();
        }
        return new ForwardResolution("/WEB-INF/jsp/daftar/permohonan_kvpt.jsp");
    }
    
    public List<PermohonanHubungan> getSenaraiHubungan() {
        return senaraiHubungan;
    }

    public void setSenaraiHubungan(List<PermohonanHubungan> senaraiHubungan) {
        this.senaraiHubungan = senaraiHubungan;
    }
    
    public String getIdSasar() {
        return idSasar;
    }

    public void setIdSasar(String idSasar) {
        this.idSasar = idSasar;
    }

    public Hakmilik getHakmilik() {
        return hakmilik;
    }

    public void setHakmilik(Hakmilik hakmilik) {
        this.hakmilik = hakmilik;
    }

    public Notis getNotis() {
        return notis;
    }

    public void setNotis(Notis notis) {
        this.notis = notis;
    }

    public String getTkhHantar() {
        return tkhHantar;
    }

    public void setTkhHantar(String tkhHantar) {
        this.tkhHantar = tkhHantar;
    }

    public String getTkhKuatKuasa() {
        return tkhKuatKuasa;
    }

    public void setTkhKuatKuasa(String tkhKuatKuasa) {
        this.tkhKuatKuasa = tkhKuatKuasa;
    }

    public String getTkhNotis() {
        return tkhNotis;
    }

    public void setTkhNotis(String tkhNotis) {
        this.tkhNotis = tkhNotis;
    }

    public String getTkhRujukan() {
        return tkhRujukan;
    }

    public void setTkhRujukan(String tkhRujukan) {
        this.tkhRujukan = tkhRujukan;
    }

    public String getTkhSidang() {
        return tkhSidang;
    }

    public void setTkhSidang(String tkhSidang) {
        this.tkhSidang = tkhSidang;
    }

    public String getTkhTamat() {
        return tkhTamat;
    }

    public void setTkhTamat(String tkhTamat) {
        this.tkhTamat = tkhTamat;
    }

    public String getTkhTampal() {
        return tkhTampal;
    }

    public void setTkhTampal(String tkhTampal) {
        this.tkhTampal = tkhTampal;
    }

    public String getTkhWarta() {
        return tkhWarta;
    }

    public void setTkhWarta(String tkhWarta) {
        this.tkhWarta = tkhWarta;
    }

    public void setPermohonan(Permohonan p) {
        this.permohonan = p;
    }

    public Permohonan getPermohonan() {
        return permohonan;
    }

    public String getNoFail() {
        return noFail;
    }

    public void setNoFail(String noFail) {
        this.noFail = noFail;
    }

    public List<Pemohon> getListPemohon() {
        return listPemohon;
    }

    public void setListPemohon(List<Pemohon> listPemohon) {
        this.listPemohon = listPemohon;
    }

    public String getIdHakmilik() {
        return idHakmilik;
    }

    public void setIdHakmilik(String idHakmilik) {
        this.idHakmilik = idHakmilik;
    }

    public char getStatus() {
        return status;
    }

    public void setStatus(char status) {
        this.status = status;
    }
        
    public PermohonanHubungan getPermohonanHubungan() {
        return permohonanHubungan;
    }

    public void setPermohonanHubungan(PermohonanHubungan permohonanHubungan) {
        this.permohonanHubungan = permohonanHubungan;
    }

    public List<HakmilikPihakBerkepentingan> getHpkList() {
        return hpkList;
    }

    public void setHpkList(List<HakmilikPihakBerkepentingan> hpkList) {
        this.hpkList = hpkList;
    }
    
}
