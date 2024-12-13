/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.stripes.pengambilan.sek8;

import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.dao.BorangPerHakmilikDAO;
import etanah.dao.BorangPerPBDAO;
import etanah.dao.BorangPerPermohonanDAO;
import etanah.dao.KodJenisPengenalanDAO;
import etanah.dao.KodNegeriDAO;
import etanah.dao.KodNotisDAO;
import etanah.dao.PermohonanDAO;
import etanah.model.Dokumen;
import etanah.model.HakmilikPermohonan;
import etanah.model.InfoAudit;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.model.ambil.BorangPerHakmilik;
import etanah.model.ambil.BorangPerPB;
import etanah.model.ambil.BorangPerPermohonan;
import etanah.model.ambil.HakmilikPerbicaraan;
import etanah.model.ambil.HantarBorangPB;
import etanah.model.ambil.HantarBorangPermohonan;
import etanah.model.ambil.NotaSiasatanDokumen;
import etanah.model.ambil.NotaSiasatanLengkap;
import etanah.model.ambil.PermohonanPengambilan;
import etanah.model.ambil.TampalBorangHakmilik;
import etanah.model.ambil.TuntutanPerPB;
import etanah.ref.pengambilan.sek4.RefPeringkat;
import etanah.service.acq.integrationflow.Sek8aIntegrationFlowService;
import etanah.service.acq.service.BorangACQService;
import etanah.service.acq.service.BorangEFService;
import etanah.service.ambil.PengambilanAPTService;
import etanah.service.pengambilan.aduan.AduanService;
import etanah.view.BasicTabActionBean;
import etanah.view.daftar.PihakKepentinganAction;
import etanah.view.etanahActionBeanContext;
import etanah.view.stripes.pengambilan.share.form.BorangEForm;
import etanah.view.stripes.pengambilan.share.form.BorangFForm;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.FileBean;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.RedirectResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;

/**
 *
 * @author zipzap
 */
@UrlBinding("/pengambilan/sesi_perbicaraan")
public class PerbicaraanActionBean extends BasicTabActionBean {

    @Inject
    BorangPerPermohonanDAO borangPerPermohonanDAO;
    @Inject
    BorangACQService borangACQService;
    @Inject
    BorangEFService borangEFService;
    @Inject
    BorangPerHakmilikDAO borangPerHakmilikDAO;
    @Inject
    KodJenisPengenalanDAO kodJenisPengenalanDAO;
    @Inject
    BorangPerPBDAO borangPerPBDAO;
    @Inject
    KodNegeriDAO kodNegeriDAO;
    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    KodNotisDAO kodNotisDAO;
    @Inject
    PengambilanAPTService pengambilanAPTService;
    @Inject
    AduanService aduanService;
    @Inject
    Sek8aIntegrationFlowService sek8aIntegrationFlowService;
    List<BorangEForm> listBorangE;
    List<BorangFForm> listBorangF;
    List<TampalBorangHakmilik> listTampalBorangE;
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

    Dokumen dokumenBE;
    Dokumen dokumenBG;
    Dokumen dokumenBM;
    BorangPerHakmilik borangPerHakmilik;
    Permohonan permohonan;
    String urusan;
    String urlKembali;
    String idPermohonan;
    Long idBorangPerHm;
    Long idPerPb;
    String noBicara;
    String tempatBicara;
    String tarikhBicara;
    String flagBicara;
    FileBean fileToBeUpload;
    String keteranganBicara;
    Pengguna dibicaraOleh;

    Boolean penyampaian = Boolean.FALSE;
    BigDecimal amaunTuntutan;
    String itemTuntutan;
    List<TuntutanPerPB> listTuntutan;

    String idBorangHm;
    String idHakmilik;
    String ketPerbincgn;
    String kpsnBicara;

    String tarikhHantarB;
    String tempatHantarB;
    String penghantarB;
    String catatanB;
    String kategoriAPT;
    String tujuanAPT;

    @DefaultHandler
    public Resolution showForm() {
        idPermohonan = (String) getContext().getRequest().getParameter("idPermohonan");
        Permohonan p = permohonanDAO.findById(idPermohonan);

        urusan = p.getKodUrusan().getNama();
        urlKembali = "showForm";
        listBorangE = borangEFService.findHakmilikAktifNotTDK(idPermohonan);
        return new JSP("/pengambilan/APT/sesi_perbicaraan.jsp").addParameter("tab", "true");
    }

    public Resolution senaraiF() {
        listBorangF = new ArrayList<BorangFForm>();
        idPermohonan = (String) getContext().getRequest().getParameter("idPermohonan");
        permohonan = permohonanDAO.findById(idPermohonan);
        idHakmilik = (String) getContext().getRequest().getParameter("idHakmilik");
        idBorangHm = (String) getContext().getRequest().getParameter("idBorangPerP");
        idBorangPerHm = Long.valueOf(Long.valueOf(idBorangHm));
        borangPerHakmilik = borangPerHakmilikDAO.findById(idBorangPerHm);

        PermohonanPengambilan pp = pengambilanAPTService.findPermohonanPengambilanByIdMH(idPermohonan);
        if (pp != null) {
            kategoriAPT = pp.getKatPengambilan();
            if(kategoriAPT.equals("1")){
                kategoriAPT = "(a)";
            }else if(kategoriAPT.equals("2")){
                kategoriAPT = "(b)";
            }else{
                kategoriAPT = "(c)";
            }
            tujuanAPT = pp.getTujuanPermohonan();
        }

        BorangPerHakmilik borangPerHakmilikG = aduanService.findByIdMHNBE(borangPerHakmilik.getHakmilikPermohonan().getId(), "NBG");
        if (borangPerHakmilikG != null) {
            dokumenBG = borangPerHakmilikG.getDokumen();
            penyampaian = true;
        }
        BorangPerHakmilik borangPerHakmilikM = aduanService.findByIdMHNBE(borangPerHakmilik.getHakmilikPermohonan().getId(), "NBM");
        if (borangPerHakmilikM != null) {
            dokumenBM = borangPerHakmilikM.getDokumen();
        }

        dokumenBE = borangPerHakmilik.getDokumen();

        listBorangF = borangEFService.listBorangF(idPermohonan, idHakmilik);
        listTampalBorangE = borangEFService.findTampalBorangHakmilik(borangPerHakmilik);
        HakmilikPermohonan hakmilikPermohonan = pengambilanAPTService.findHakmilikPermohonanByIdMhnIdHM(idPermohonan, idHakmilik);
        HakmilikPerbicaraan hakmilikPerbicaraan = pengambilanAPTService.findHakmilikPerbicaraanByIdMH(hakmilikPermohonan.getId());
        if (hakmilikPerbicaraan != null) {
            kpsnBicara = hakmilikPerbicaraan.getKodkpsn();
            ketPerbincgn = hakmilikPerbicaraan.getCatatan();
        }
        return new JSP("/pengambilan/APT/sesi_perbicaraan.jsp").addParameter("tab", "true");
    }

    public Resolution kemaskiniTuntutan() throws ParseException {
        String idborangperpb = (String) getContext().getRequest().getParameter("idPerPb");
        BorangPerPB pb = borangPerPBDAO.findById(Long.valueOf(idborangperpb));

        listTuntutan = borangEFService.listTuntutanPerPB(pb);
        return new JSP("/pengambilan/APT/tuntutan_perpb_831.jsp").addParameter("popup", true);

    }

    public Resolution kemaskiniBicara() throws ParseException {
        String idborangperpb = (String) getContext().getRequest().getParameter("idPerPb");

        if (idborangperpb != null) {
            BorangPerPB pb = borangPerPBDAO.findById(Long.valueOf(idborangperpb));
            if (pb != null) {
                NotaSiasatanLengkap nsl = borangEFService.findNotaSiasatanByPerPB(pb);
                if (nsl != null) {
                    noBicara = nsl.getNoBicara();
                    tempatBicara = nsl.getTempatBicara();
                    tarikhBicara = nsl.getTarikhBicara().toString();
                    flagBicara = nsl.getFlagBicara();
                    keteranganBicara = nsl.getKeteranganicara();
                }
            }
        }

        BorangPerPB pb = borangPerPBDAO.findById(Long.valueOf(idborangperpb));
        NotaSiasatanLengkap nsl = borangEFService.findNotaSiasatanByPerPB(pb);
        noBicara = nsl.getNoBicara();
        tempatBicara = nsl.getTempatBicara();
        tarikhBicara = nsl.getTarikhBicara() != null ? sdf.format(nsl.getTarikhBicara()) : null;
        flagBicara = nsl.getFlagBicara();
        keteranganBicara = nsl.getKeteranganicara();

        return new JSP("/pengambilan/APT/detail_bicara.jsp").addParameter("popup", true);

    }

    public Resolution simpanBorangH() throws ParseException {
        Pengguna pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        String idBorangPerPBB = (String) getContext().getRequest().getParameter("idBorangPerPB");
        String idPermohonan = (String) getContext().getRequest().getParameter("idPermohonan");
        BorangPerPB borangPerPB = borangPerPBDAO.findById(Long.parseLong(idBorangPerPBB));
        InfoAudit infoAudit = setInfoAudit(pengguna);
        HantarBorangPB bm = borangACQService.findHantarBorangPB(borangPerPB);
        if (bm != null) {
        } else {
            bm = new HantarBorangPB();
        }
        bm.setBorangPerPB(borangPerPB);
        bm.setTrh_hantar(formatSDF(tarikhHantarB));
//        bm.set
//        bm.set(tempatHantarB);
        bm.setInfoAudit(infoAudit);
        bm.setCatatan(catatanB);
        bm.setPenghantar_notis(penghantarB);
        borangACQService.saveHantarBorangPB(bm);
        addSimpleMessage("Maklumat Berjaya disimpan");
        return new JSP("/pengambilan/APT/borangBpopup.jsp").addParameter("popup", true);

    }

    public Resolution tambahBorangH() {
        String idBorangPerPBB = (String) getContext().getRequest().getParameter("idBorangPerPB");
        BorangPerPB borangPerPB = borangPerPBDAO.findById(Long.parseLong(idBorangPerPBB));
        HantarBorangPB hb = borangACQService.findHantarBorangPB(borangPerPB);
        if (hb != null) {
            tarikhHantarB = hb.getTrh_hantar() != null ? sdf.format(hb.getTrh_hantar()) : null;
            catatanB = hb.getCatatan();
            penghantarB = hb.getPenghantar_notis();
        }
        return new JSP("/pengambilan/APT/borangHpopup.jsp").addParameter("popup", true);

    }

    public Date formatSDF(String date) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        return sdf.parse(date);
    }

    public Resolution hantar() {
        //idPermohonan = getContext().getRequest().getParameter("idPermohonan");
        Pengguna pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);

        idPermohonan = (String) getContext().getRequest().getParameter("idPermohonan");
        idHakmilik = (String) getContext().getRequest().getParameter("idHakmilik");
        String idBorangPerHm1 = (String) getContext().getRequest().getParameter("idBorangPerHm");
        idBorangPerHm = Long.valueOf(idBorangPerHm1);

        HakmilikPermohonan hakmilikPermohonan = pengambilanAPTService.findHakmilikPermohonanByIdMhnIdHM(idPermohonan, idHakmilik);
        permohonan = permohonanDAO.findById(idPermohonan);
        RefPeringkat ref = new RefPeringkat();

        HakmilikPerbicaraan hakmilikPerbicaraan = null;
        InfoAudit infoAudit = setInfoAudit(pengguna);
        hakmilikPerbicaraan = pengambilanAPTService.findHakmilikPerbicaraanByIdMH(hakmilikPermohonan.getId());
        if (hakmilikPerbicaraan == null) {
            hakmilikPerbicaraan = new HakmilikPerbicaraan();
        }

        hakmilikPerbicaraan.setHakmilikPermohonan(hakmilikPermohonan);
        hakmilikPerbicaraan.setCatatan(ketPerbincgn);
        hakmilikPerbicaraan.setCawangan(permohonan.getCawangan());
        hakmilikPerbicaraan.setKodkpsn(kpsnBicara);
        hakmilikPerbicaraan.setInfoAudit(infoAudit);
        pengambilanAPTService.saveOrUpdateHakmilikPerbicaraan(hakmilikPerbicaraan);

        if (kpsnBicara.equals("L")) {
            kpsnBicara = "SL";
        } else if (kpsnBicara.equals("T")) {
            kpsnBicara = "TG";
        } else {
            kpsnBicara = "PT";
        }
        List<BorangPerPB> borangPerPBs = aduanService.findByIdBorangPerHM(idBorangPerHm);
        if (borangPerPBs.size() > 0) {
            for (BorangPerPB pB : borangPerPBs) {
                NotaSiasatanLengkap nsl = borangEFService.findNotaSiasatanByPerPB(pB);
                if (nsl != null) {
                    nsl.setFlagBicara(kpsnBicara);
                    nsl.setKeteranganicara(ketPerbincgn);
                    borangEFService.saveNotaSiasatanLengkap(nsl);
                }
            }
        }

        return showForm();
    }

    public Resolution simpanBicara() throws ParseException, Exception {
        Pengguna pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        String idPerPbA = (String) getContext().getRequest().getParameter("idPerPb");
        String idPermohonan = (String) getContext().getRequest().getParameter("idPermohonan");
        permohonan = permohonanDAO.findById(idPermohonan);
        BorangPerPB borang = borangPerPBDAO.findById(Long.valueOf(idPerPbA));
        InfoAudit infoAudit = setInfoAudit(pengguna);
        NotaSiasatanLengkap nsl = borangEFService.findNotaSiasatanByPerPB(borang);
        nsl.setFlagBicara(flagBicara);
        nsl.setKeteranganicara(keteranganBicara);
        nsl.setDiBicaraOleh(dibicaraOleh);
        nsl.setNoBicara(noBicara);
        nsl.setTarikhBicara(formatSDF(tarikhBicara));

        nsl.setTempatBicara(tempatBicara);
        borangEFService.saveNotaSiasatanLengkap(nsl);
        if (fileToBeUpload != null) {
            Dokumen dok = pengambilanAPTService.saveUploadDokumen(permohonan, nsl, fileToBeUpload, "NSIA", setInfoAudit(pengguna));
            if (dok != null) {
                NotaSiasatanDokumen nsd = new NotaSiasatanDokumen();
                nsd.setDokumen(dok);
                nsd.setNotaSiasatanLengkap(nsl);
                nsd.setInfoAudit(setInfoAudit(pengguna));
                borangEFService.saveNotaSiasatanDokumen(nsd);
            }
        }
        addSimpleMessage("Maklumat Berjaya disimpan");
        return new JSP("/pengambilan/APT/detail_bicara.jsp").addParameter("popup", true);

    }

    public Resolution savepopupItem() throws ParseException {
        idPermohonan = (String) getContext().getRequest().getParameter("idPermohonan");
        String idborangperpb = (String) getContext().getRequest().getParameter("idPerPb");
        BorangPerPB pb = borangPerPBDAO.findById(Long.parseLong(idborangperpb));
        TuntutanPerPB tu = new TuntutanPerPB();
        tu.setBorangPerPB(pb);

        tu.setAmaun(amaunTuntutan);
        tu.setBorangPerPB(pb);
        tu.setItemTuntutan(itemTuntutan);

        borangACQService.saveTuntutan(tu);
        listTuntutan = borangACQService.listTuntutanPerPB(pb);
        Permohonan p = permohonanDAO.findById(idPermohonan);
        urusan = p.getKodUrusan().getNama();

        return showForm();
    }

    private InfoAudit setInfoAudit(Pengguna pengguna) {
        InfoAudit ia = new InfoAudit();
        ia.setDimasukOleh(pengguna);
        ia.setTarikhMasuk(new Date());
        return ia;
    }

    public BorangACQService getBorangACQService() {
        return borangACQService;
    }

    public void setBorangACQService(BorangACQService borangACQService) {
        this.borangACQService = borangACQService;
    }

    public BorangEFService getBorangEFService() {
        return borangEFService;
    }

    public void setBorangEFService(BorangEFService borangEFService) {
        this.borangEFService = borangEFService;
    }

    public BorangPerHakmilikDAO getBorangPerHakmilikDAO() {
        return borangPerHakmilikDAO;
    }

    public void setBorangPerHakmilikDAO(BorangPerHakmilikDAO borangPerHakmilikDAO) {
        this.borangPerHakmilikDAO = borangPerHakmilikDAO;
    }

    public KodJenisPengenalanDAO getKodJenisPengenalanDAO() {
        return kodJenisPengenalanDAO;
    }

    public void setKodJenisPengenalanDAO(KodJenisPengenalanDAO kodJenisPengenalanDAO) {
        this.kodJenisPengenalanDAO = kodJenisPengenalanDAO;
    }

    public BorangPerPBDAO getBorangPerPBDAO() {
        return borangPerPBDAO;
    }

    public void setBorangPerPBDAO(BorangPerPBDAO borangPerPBDAO) {
        this.borangPerPBDAO = borangPerPBDAO;
    }

    public KodNegeriDAO getKodNegeriDAO() {
        return kodNegeriDAO;
    }

    public void setKodNegeriDAO(KodNegeriDAO kodNegeriDAO) {
        this.kodNegeriDAO = kodNegeriDAO;
    }

    public PermohonanDAO getPermohonanDAO() {
        return permohonanDAO;
    }

    public void setPermohonanDAO(PermohonanDAO permohonanDAO) {
        this.permohonanDAO = permohonanDAO;
    }

    public KodNotisDAO getKodNotisDAO() {
        return kodNotisDAO;
    }

    public void setKodNotisDAO(KodNotisDAO kodNotisDAO) {
        this.kodNotisDAO = kodNotisDAO;
    }

    public PengambilanAPTService getPengambilanAPTService() {
        return pengambilanAPTService;
    }

    public void setPengambilanAPTService(PengambilanAPTService pengambilanAPTService) {
        this.pengambilanAPTService = pengambilanAPTService;
    }

    public Sek8aIntegrationFlowService getSek8aIntegrationFlowService() {
        return sek8aIntegrationFlowService;
    }

    public void setSek8aIntegrationFlowService(Sek8aIntegrationFlowService sek8aIntegrationFlowService) {
        this.sek8aIntegrationFlowService = sek8aIntegrationFlowService;
    }

    public List<BorangEForm> getListBorangE() {
        return listBorangE;
    }

    public void setListBorangE(List<BorangEForm> listBorangE) {
        this.listBorangE = listBorangE;
    }

    public List<BorangFForm> getListBorangF() {
        return listBorangF;
    }

    public void setListBorangF(List<BorangFForm> listBorangF) {
        this.listBorangF = listBorangF;
    }

    public List<TampalBorangHakmilik> getListTampalBorangE() {
        return listTampalBorangE;
    }

    public void setListTampalBorangE(List<TampalBorangHakmilik> listTampalBorangE) {
        this.listTampalBorangE = listTampalBorangE;
    }

    public Dokumen getDokumenBE() {
        return dokumenBE;
    }

    public void setDokumenBE(Dokumen dokumenBE) {
        this.dokumenBE = dokumenBE;
    }

    public BorangPerHakmilik getBorangPerHakmilik() {
        return borangPerHakmilik;
    }

    public void setBorangPerHakmilik(BorangPerHakmilik borangPerHakmilik) {
        this.borangPerHakmilik = borangPerHakmilik;
    }

    public Permohonan getPermohonan() {
        return permohonan;
    }

    public void setPermohonan(Permohonan permohonan) {
        this.permohonan = permohonan;
    }

    public String getUrusan() {
        return urusan;
    }

    public void setUrusan(String urusan) {
        this.urusan = urusan;
    }

    public String getUrlKembali() {
        return urlKembali;
    }

    public void setUrlKembali(String urlKembali) {
        this.urlKembali = urlKembali;
    }

    public String getIdPermohonan() {
        return idPermohonan;
    }

    public void setIdPermohonan(String idPermohonan) {
        this.idPermohonan = idPermohonan;
    }

    public Long getIdBorangPerHm() {
        return idBorangPerHm;
    }

    public void setIdBorangPerHm(Long idBorangPerHm) {
        this.idBorangPerHm = idBorangPerHm;
    }

    public Long getIdPerPb() {
        return idPerPb;
    }

    public void setIdPerPb(Long idPerPb) {
        this.idPerPb = idPerPb;
    }

    public String getIdBorangHm() {
        return idBorangHm;
    }

    public void setIdBorangHm(String idBorangHm) {
        this.idBorangHm = idBorangHm;
    }

    public String getIdHakmilik() {
        return idHakmilik;
    }

    public void setIdHakmilik(String idHakmilik) {
        this.idHakmilik = idHakmilik;
    }

    public String getFlagBicara() {
        return flagBicara;
    }

    public void setFlagBicara(String flagBicara) {
        this.flagBicara = flagBicara;
    }

    public String getKeteranganBicara() {
        return keteranganBicara;
    }

    public void setKeteranganBicara(String keteranganBicara) {
        this.keteranganBicara = keteranganBicara;
    }

    public Pengguna getDibicaraOleh() {
        return dibicaraOleh;
    }

    public void setDibicaraOleh(Pengguna dibicaraOleh) {
        this.dibicaraOleh = dibicaraOleh;
    }

    public BigDecimal getAmaunTuntutan() {
        return amaunTuntutan;
    }

    public void setAmaunTuntutan(BigDecimal amaunTuntutan) {
        this.amaunTuntutan = amaunTuntutan;
    }

    public String getItemTuntutan() {
        return itemTuntutan;
    }

    public void setItemTuntutan(String itemTuntutan) {
        this.itemTuntutan = itemTuntutan;
    }

    public List<TuntutanPerPB> getListTuntutan() {
        return listTuntutan;
    }

    public void setListTuntutan(List<TuntutanPerPB> listTuntutan) {
        this.listTuntutan = listTuntutan;
    }

    public String getNoBicara() {
        return noBicara;
    }

    public void setNoBicara(String noBicara) {
        this.noBicara = noBicara;
    }

    public String getTempatBicara() {
        return tempatBicara;
    }

    public void setTempatBicara(String tempatBicara) {
        this.tempatBicara = tempatBicara;
    }

    public FileBean getFileToBeUpload() {
        return fileToBeUpload;
    }

    public void setFileToBeUpload(FileBean fileToBeUpload) {
        this.fileToBeUpload = fileToBeUpload;
    }

    public String getTarikhBicara() {
        return tarikhBicara;
    }

    public void setTarikhBicara(String tarikhBicara) {
        this.tarikhBicara = tarikhBicara;
    }

    public Boolean getPenyampaian() {
        return penyampaian;
    }

    public void setPenyampaian(Boolean penyampaian) {
        this.penyampaian = penyampaian;
    }

    public String getKetPerbincgn() {
        return ketPerbincgn;
    }

    public void setKetPerbincgn(String ketPerbincgn) {
        this.ketPerbincgn = ketPerbincgn;
    }

    public String getKpsnBicara() {
        return kpsnBicara;
    }

    public void setKpsnBicara(String kpsnBicara) {
        this.kpsnBicara = kpsnBicara;
    }

    public Dokumen getDokumenBG() {
        return dokumenBG;
    }

    public void setDokumenBG(Dokumen dokumenBG) {
        this.dokumenBG = dokumenBG;
    }

    public Dokumen getDokumenBM() {
        return dokumenBM;
    }

    public void setDokumenBM(Dokumen dokumenBM) {
        this.dokumenBM = dokumenBM;
    }

    public String getTarikhHantarB() {
        return tarikhHantarB;
    }

    public void setTarikhHantarB(String tarikhHantarB) {
        this.tarikhHantarB = tarikhHantarB;
    }

    public String getTempatHantarB() {
        return tempatHantarB;
    }

    public void setTempatHantarB(String tempatHantarB) {
        this.tempatHantarB = tempatHantarB;
    }

    public String getPenghantarB() {
        return penghantarB;
    }

    public void setPenghantarB(String penghantarB) {
        this.penghantarB = penghantarB;
    }

    public String getCatatanB() {
        return catatanB;
    }

    public void setCatatanB(String catatanB) {
        this.catatanB = catatanB;
    }

    public String getKategoriAPT() {
        return kategoriAPT;
    }

    public void setKategoriAPT(String kategoriAPT) {
        this.kategoriAPT = kategoriAPT;
    }

    public String getTujuanAPT() {
        return tujuanAPT;
    }

    public void setTujuanAPT(String tujuanAPT) {
        this.tujuanAPT = tujuanAPT;
    }

}
