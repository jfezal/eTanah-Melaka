/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.penguatkuasaan;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.dao.KodDokumenDAO;
import etanah.dao.KodKlasifikasiDAO;
import etanah.model.Dokumen;
import etanah.model.KodBangsa;
import etanah.model.KodDokumen;
import etanah.model.KodNegeri;
import etanah.model.KodBangsa;
import etanah.model.KodJantina;
import etanah.model.KodWarganegara;
import etanah.model.KodJenisPengenalan;

import etanah.service.common.EnforcementService;
import etanah.model.KodJenisPengenalan;
import etanah.model.WarisOrangKenaSyak;
import net.sourceforge.stripes.action.RedirectResolution;
import etanah.dao.PermohonanDAO;
import etanah.model.Permohonan;
import etanah.dao.AduanOrangKenaSyakDAO;
import etanah.dao.DokumenDAO;
import etanah.dao.HakmilikDAO;
import etanah.dao.KandunganFolderDAO;
import etanah.dao.KodBangsaDAO;
import etanah.dao.KodJantinaDAO;
import etanah.dao.KodNegeriDAO;
import etanah.dao.KodWarganegaraDAO;
import etanah.dao.KodJenisPengenalanDAO;
import etanah.dao.WarisOrangKenaSyakDAO;
import etanah.model.AduanOrangKenaSyak;
import etanah.model.InfoAudit;
import etanah.model.KodNegeri;
import etanah.model.KodCawangan;
import etanah.model.Alamat;
import etanah.model.HakmilikPermohonan;
import etanah.model.KodJantina;
import etanah.model.KodWarganegara;
import etanah.model.KodWarnaKP;
import etanah.model.Pengguna;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;
import org.apache.log4j.Logger;
import etanah.service.EnforceService;
import etanah.service.common.LelongService;
import etanah.view.etanahActionBeanContext;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import net.sourceforge.stripes.action.FileBean;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author siti.zainal
 *
 *
 */
@UrlBinding("/penguatkuasaan/maklumat_waris")
public class MaklumatWarisActionBean extends AbleActionBean {

    private static Logger logger = Logger.getLogger(MaklumatWarisActionBean.class);
    @Inject
    KodJenisPengenalanDAO kodJenisPengenalanDAO;
    @Inject
    KodWarganegaraDAO kodWarganegaraDAO;
    @Inject
    KodNegeriDAO kodNegeriDAO;
    @Inject
    KodBangsaDAO kodBangsaDAO;
    @Inject
    KodJantinaDAO kodJantinaDAO;
    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    DokumenDAO dokumenDAO;
    @Inject
    AduanOrangKenaSyakDAO aduanOrangKenaSyakDAO;
    @Inject
    WarisOrangKenaSyakDAO warisOrangKenaSyakDAO;
    @Inject
    KodKlasifikasiDAO kodKlasDAO;
    @Inject
    KodDokumenDAO kodDokumenDAO;
    @Inject
    etanah.Configuration conf;
    @Inject
    KandunganFolderDAO kandunganFolderDAO;
    @Inject
    EnforcementService enforcementService;
    @Inject
    EnforceService enforceService;
    @Inject
    LelongService lelongService;
    @Inject
    HakmilikDAO hakmilikDAO;
    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;
    private List<HakmilikPermohonan> hakmilikPermohonanList = new ArrayList<HakmilikPermohonan>();
    private String kodNegeri;
    private Boolean multipleHakmilik = Boolean.FALSE;
    private String idHakmilik;
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    FileBean fileToBeUploaded;
    private static final Logger LOG = Logger.getLogger(MaklumatWarisActionBean.class);
    private List<WarisOrangKenaSyak> listWaris;
    private Pengguna pengguna;
    private Permohonan permohonan;
    private AduanOrangKenaSyak aduanOrangKenaSyak;
    private KodNegeri negeri;
    private KodBangsa bangsaWaris;
    private String bangsaWaris1;
    private String negeriWaris;
    private KodCawangan cawangan;
    private HakmilikPermohonan hakmilikPermohonan;
    private Alamat alamat;
    private String alamat1;
    private String alamat2;
    private String alamat3;
    private String alamat4;
    private String poskod;
    private String noPengenalan;
    private String negeri1;
    private String idOrangKenaSyak;
    private String idPermohonan;
    private String kodNegeriOrg;
    private String catatan;
    private String penyerahNoPengenalan;
    private String penyerahNama;
    private String nama;
    private String jenisPengenalan;
    private String idWaris;
    private String keterangan;
    private String senaraiHitam;
    private String tempatOksDitahan;
    private String tempatDireman;
    private String tarikhDitahan;
    private String tarikhDiBebas;
    private String diBebas;
    private KodJenisPengenalan kodJenisPengenalan;
    private String kp;
    private String bangsaW;
    private String kw;
    private String kj;
    private String kwg;
    private String noTelefon1;
    private KodWarnaKP kodWarnaKP;
    private KodJantina kodJantina;
    private KodWarganegara kodWarganegara;
    private String namaMajikan;
    private String noTelMajikan;
    private String noFaksMajikan;
    private String alamat1Majikan;
    private String alamat2Majikan;
    private String alamat3Majikan;
    private String alamat4Majikan;
    private String poskodMajikan;
    private KodNegeri kodNegeriMajikan;
    private WarisOrangKenaSyak warisOrangKenaSyak;
    private String namaWaris;
    private String alamat1Waris;
    private String alamat2Waris;
    private String alamat3Waris;
    private String alamat4Waris;
    private String poskodWaris;
    private KodNegeri kodNegeriWaris;
    private String hubungan;
    private String noPengenalanWaris;
    private KodJenisPengenalan jenisPengenalanWaris;
    private String kodBangsaOKS;
    private String kerja;
    private String tarikhLahir;
    private String hari;
    private String bulan;
    private String tahun;
    private KodJantina kodJantinaWaris;
    private KodWarganegara kodWarganegaraWaris;
    private String noTelWaris;
    private String noTelBimbitWaris;
    private String kngri; /* kod negeri waris*/

    private String warganegara;
    private String kp1; /*jenis pengenalan waris*/

    private String kjt; /*kod jantina*/

    private String kodNMajikan; /*kod negeri majikan*/

    private String jantinaWaris1;
    private String warganegaraWaris1;
    private List<KodBangsa> senaraiBangsa;
    private String negeriedit;
    private String bangsaedit;
    private String jantinaedit;
    private String warganegaraedit;
    private String jenispengenalanedit;

    @DefaultHandler
    public Resolution showForm() {
        return new JSP("penguatkuasaan/maklumat_waris.jsp").addParameter("tab", "true");
    }

    public Resolution showForm2() {
        return new JSP("penguatkuasaan/maklumat_waris_view.jsp").addParameter("tab", "true");
    }

    public Resolution popupWaris() {
        idHakmilik = getContext().getRequest().getParameter("idHakmilik");
        return new JSP("penguatkuasaan/maklumat_waris_popup.jsp").addParameter("popup", "true");
    }

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {
        pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        System.out.println("id mohon :" + idPermohonan);
        idWaris = getContext().getRequest().getParameter("idWaris");

        if (idPermohonan != null) {
            kodNegeri = conf.getProperty("kodNegeri");
            permohonan = permohonanDAO.findById(idPermohonan);
            hakmilikPermohonanList = enforceService.findSenaraiMohonHakmilik(permohonan.getIdPermohonan());

            if (kodNegeri.equalsIgnoreCase("04")) {
                if (!permohonan.getKodUrusan().getKod().equalsIgnoreCase("351") && !permohonan.getKodUrusan().getKod().equalsIgnoreCase("352") && !permohonan.getKodUrusan().getKod().equalsIgnoreCase("49")) {
                    if (!hakmilikPermohonanList.isEmpty()) {
                        hakmilikPermohonan = hakmilikPermohonanList.get(0);
                    }
                } else {
                    logger.info("::: multiple hakmilik for seksyen 49, 351 & 352 MLK :::");
                    multipleHakmilik = true;
                }
            } else {
                if (!hakmilikPermohonanList.isEmpty()) {
                    hakmilikPermohonan = hakmilikPermohonanList.get(0);
                }
            }
            listWaris = enforcementService.findWarisByIDPermohonan(idPermohonan);
            System.out.println("list waris: " + listWaris.size());
            senaraiBangsa = enforceService.senaraiBangsa();

        }
    }

    //maklumat_waris.jsp
    public Resolution reload() {
        System.out.println("masuk reload");
        return new RedirectResolution(MaklumatWarisActionBean.class, "locate");
    }

    public Resolution deleteWaris() {
        idWaris = getContext().getRequest().getParameter("idWarisOks");

        warisOrangKenaSyak = warisOrangKenaSyakDAO.findById(Long.parseLong(idWaris));

        if (warisOrangKenaSyak != null) {
            enforceService.deleteAll(warisOrangKenaSyak);
        }

        addSimpleMessage("Maklumat telah berjaya dihapuskan.");
        return new RedirectResolution(MaklumatWarisActionBean.class, "locate");
    }

    //view details of waris
    public Resolution viewWarisDetail() {
        LOG.debug("viewWarisDetail");
        idWaris = getContext().getRequest().getParameter("idWaris");
        if (idWaris != null) {
            LOG.info("idWaris :" + idWaris);
            warisOrangKenaSyak = warisOrangKenaSyakDAO.findById(Long.parseLong(idWaris));

        }
        return new JSP("penguatkuasaan/view_waris_detail.jsp").addParameter("popup", "true");
    }

    //view edit - grab data
    public Resolution editWarisPopup() {
        idWaris = getContext().getRequest().getParameter("idWaris");
        warisOrangKenaSyak = warisOrangKenaSyakDAO.findById(Long.parseLong(idWaris));
        if (warisOrangKenaSyak != null) {
            nama = warisOrangKenaSyak.getNama();
            noPengenalan = warisOrangKenaSyak.getNoPengenalan();
            alamat1 = warisOrangKenaSyak.getAlamat1();
            alamat2 = warisOrangKenaSyak.getAlamat2();
            alamat3 = warisOrangKenaSyak.getAlamat3();
            alamat4 = warisOrangKenaSyak.getAlamat4();
            poskod = warisOrangKenaSyak.getPoskod();
            noTelWaris = warisOrangKenaSyak.getNoTelefon();
            noTelBimbitWaris = warisOrangKenaSyak.getNoTelefonBimbit();
            hubungan = warisOrangKenaSyak.getHubungan();


            if (warisOrangKenaSyak.getJenisPengenalan() != null) {
                jenispengenalanedit = warisOrangKenaSyak.getJenisPengenalan().getKod();
            }
            if (warisOrangKenaSyak.getNegeri() != null) {
                negeriedit = warisOrangKenaSyak.getNegeri().getKod();
            }

            if (warisOrangKenaSyak.getKodBangsa() != null) {
                bangsaedit = warisOrangKenaSyak.getKodBangsa().getKod();
            }

            if (warisOrangKenaSyak.getKodJantina() != null) {
                jantinaedit = warisOrangKenaSyak.getKodJantina().getKod();
            }
            if (warisOrangKenaSyak.getKodWarganegara() != null) {
                warganegaraedit = warisOrangKenaSyak.getKodWarganegara().getKod();
            }
        }
        return new JSP("penguatkuasaan/popup_edit_waris.jsp").addParameter("popup", "true");
    }

    //save edit
    public Resolution editWaris() throws ParseException {
        logger.info("::: editWaris :::");
        idWaris = getContext().getRequest().getParameter("idWaris");
        try {
            negeriedit = getContext().getRequest().getParameter("negeriedit");
            bangsaedit = getContext().getRequest().getParameter("bangsaedit");
            jantinaedit = getContext().getRequest().getParameter("jantinaedit");
            warganegaraedit = getContext().getRequest().getParameter("warganegaraedit");
            jenispengenalanedit = getContext().getRequest().getParameter("jenispengenalanedit");

            KodNegeri negeri = new KodNegeri();
            negeri = kodNegeriDAO.findById(negeriedit);

            KodBangsa kodbangsa = new KodBangsa();
            kodbangsa = kodBangsaDAO.findById(bangsaedit);

            KodJantina kodJantina = new KodJantina();
            kodJantina = kodJantinaDAO.findById(jantinaedit);

            KodWarganegara kodWarganegara = new KodWarganegara();
            kodWarganegara = kodWarganegaraDAO.findById(warganegaraedit);

            KodJenisPengenalan kodJenisPengenalan = new KodJenisPengenalan();
            kodJenisPengenalan = kodJenisPengenalanDAO.findById(jenispengenalanedit);


            warisOrangKenaSyak = warisOrangKenaSyakDAO.findById(Long.parseLong(idWaris));


            warisOrangKenaSyak.setNama(nama);
            warisOrangKenaSyak.setNoPengenalan(noPengenalan);
            warisOrangKenaSyak.setJenisPengenalan(kodJenisPengenalan);
            warisOrangKenaSyak.setAlamat1(alamat1);
            warisOrangKenaSyak.setAlamat2(alamat2);
            warisOrangKenaSyak.setAlamat3(alamat3);
            warisOrangKenaSyak.setAlamat4(alamat4);
            warisOrangKenaSyak.setPoskod(poskod);
            warisOrangKenaSyak.setHubungan(hubungan);
            warisOrangKenaSyak.setNegeri(negeri);
            warisOrangKenaSyak.setKodBangsa(kodbangsa);
            warisOrangKenaSyak.setKodJantina(kodJantina);
            warisOrangKenaSyak.setKodWarganegara(kodWarganegara);
            warisOrangKenaSyak.setNoTelefon(noTelWaris);
            warisOrangKenaSyak.setNoTelefonBimbit(noTelBimbitWaris);


            InfoAudit ia = warisOrangKenaSyak.getInfoAudit();
            if (ia == null) {
                ia = new InfoAudit();
                ia.setDimasukOleh(pengguna);
                ia.setTarikhMasuk(new java.util.Date());
                warisOrangKenaSyak.setInfoAudit(ia);
            } else {
                ia.setDiKemaskiniOleh(pengguna);
                ia.setTarikhKemaskini(new java.util.Date());
            }
            enforceService.editWarisOrangKenaSyak(warisOrangKenaSyak);

        } catch (Exception e) {
            e.printStackTrace();
        }

        addSimpleMessage("Maklumat telah berjaya disimpan.");
        return new RedirectResolution(MaklumatWarisActionBean.class, "locate");
    }

    public Resolution simpan() {
        logger.info("::: simpan :::");

        InfoAudit ia = new InfoAudit();
        ia.setDimasukOleh(pengguna);
        ia.setTarikhMasuk(new java.util.Date());

        if (warisOrangKenaSyak == null) {
            warisOrangKenaSyak = new WarisOrangKenaSyak();
        }
        warisOrangKenaSyak.setInfoAudit(ia);
        warisOrangKenaSyak.setCawangan(pengguna.getKodCawangan());
        warisOrangKenaSyak.setNama(namaWaris);
        warisOrangKenaSyak.setAlamat1(alamat1Waris);
        warisOrangKenaSyak.setAlamat2(alamat2Waris);
        warisOrangKenaSyak.setAlamat3(alamat3Waris);
        warisOrangKenaSyak.setAlamat4(alamat4Waris);
        warisOrangKenaSyak.setPoskod(poskodWaris);
        warisOrangKenaSyak.setAduanOrangKenaSyak(aduanOrangKenaSyak);
        warisOrangKenaSyak.setHubungan(hubungan);
        warisOrangKenaSyak.setNoPengenalan(noPengenalanWaris);
        warisOrangKenaSyak.setNoTelefon(noTelWaris);
        warisOrangKenaSyak.setNoTelefonBimbit(noTelBimbitWaris);
        if (multipleHakmilik == false) {
            warisOrangKenaSyak.setHakmilik(hakmilikPermohonan.getHakmilik());
        } else {
            System.out.println("idhakmilik ms simpan : " + idHakmilik);
            warisOrangKenaSyak.setHakmilik(hakmilikDAO.findById(idHakmilik));
        }
        warisOrangKenaSyak.setPermohonan(permohonan);

        if (kngri != null) {
            KodNegeri kn1 = new KodNegeri();
            kn1.setKod(kngri);
            warisOrangKenaSyak.setNegeri(kn1);
        }

        if (warganegara != null) {
            KodWarganegara kodW = new KodWarganegara();
            kodW.setKod(warganegara);
            warisOrangKenaSyak.setKodWarganegara(kodW);
        }

        if (kp != null) {
            KodJenisPengenalan kod1 = new KodJenisPengenalan();
            kod1.setKod(kp);
            warisOrangKenaSyak.setJenisPengenalan(kod1);
        }

        if (bangsaW != null) {
            KodBangsa kodB = new KodBangsa();
            kodB.setKod(bangsaW);
            warisOrangKenaSyak.setKodBangsa(kodB);
        }

        if (kj != null) {
            KodJantina kj1 = new KodJantina();
            kj1.setKod(kj);
            warisOrangKenaSyak.setKodJantina(kj1);
        }

        enforceService.simpanMaklumatWaris(warisOrangKenaSyak);
        System.out.println("finish simpan insert service");

        rehydrate();
        addSimpleMessage("Maklumat Telah Berjaya Disimpan.");
        return new JSP("penguatkuasaan/maklumat_waris.jsp").addParameter("tab", "true");
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    /**
     * @return the negeri1
     */
    public String getNegeri1() {
        return negeri1;
    }

    /**
     * @param negeri1 the negeri1 to set
     */
    public void setNegeri1(String negeri1) {
        this.negeri1 = negeri1;
    }

    public String getKodNegeriOrg() {
        return kodNegeriOrg;
    }

    public void setKodNegeriOrg(String kodNegeriOrg) {
        this.kodNegeriOrg = kodNegeriOrg;
    }

    public String getSenaraiHitam() {
        return senaraiHitam;
    }

    public void setSenaraiHitam(String senaraiHitam) {
        this.senaraiHitam = senaraiHitam;
    }

    public String getTarikhSenaraiHitam() {
        return tarikhSenaraiHitam;
    }

    public void setTarikhSenaraiHitam(String tarikhSenaraiHitam) {
        this.tarikhSenaraiHitam = tarikhSenaraiHitam;
    }

    public String getKp() {
        return kp;
    }

    public void setKp(String kp) {
        this.kp = kp;
    }

    public KodNegeri getNegeri() {
        return negeri;
    }

    public void setNegeri(KodNegeri negeri) {
        this.negeri = negeri;
    }

    public String getTempatOksDitahan() {
        return tempatOksDitahan;
    }

    public void setTempatOksDitahan(String tempatOksDitahan) {
        this.tempatOksDitahan = tempatOksDitahan;
    }

    public String getKodNMajikan() {
        return kodNMajikan;
    }

    public void setKodNMajikan(String kodNMajikan) {
        this.kodNMajikan = kodNMajikan;
    }

    public String getKodBangsaOKS() {
        return kodBangsaOKS;
    }

    public void setKodBangsaOKS(String kodBangsaOKS) {
        this.kodBangsaOKS = kodBangsaOKS;
    }

    public String getKerja() {
        return kerja;
    }

    public void setKerja(String kerja) {
        this.kerja = kerja;
    }

    public String getTarikhLahir() {
        return tarikhLahir;
    }

    public void setTarikhLahir(String tarikhLahir) {
        this.tarikhLahir = tarikhLahir;
    }

    public String getBulan() {
        return bulan;
    }

    public void setBulan(String bulan) {
        this.bulan = bulan;
    }

    public String getHari() {
        return hari;
    }

    public void setHari(String hari) {
        this.hari = hari;
    }

    public String getTahun() {
        return tahun;
    }

    public void setTahun(String tahun) {
        this.tahun = tahun;
    }

    public String getKjt() {
        return kjt;
    }

    public void setKjt(String kjt) {
        this.kjt = kjt;
    }

    public String getKp1() {
        return kp1;
    }

    public void setKp1(String kp1) {
        this.kp1 = kp1;
    }

    public String getNoTelefon1() {
        return noTelefon1;
    }

    public void setNoTelefon1(String noTelefon1) {
        this.noTelefon1 = noTelefon1;
    }

    public String getKngri() {
        return kngri;
    }

    public void setKngri(String kngri) {
        this.kngri = kngri;
    }

    public String getAlamat1Waris() {
        return alamat1Waris;
    }

    public void setAlamat1Waris(String alamat1Waris) {
        this.alamat1Waris = alamat1Waris;
    }

    public String getAlamat2Waris() {
        return alamat2Waris;
    }

    public void setAlamat2Waris(String alamat2Waris) {
        this.alamat2Waris = alamat2Waris;
    }

    public String getAlamat3Waris() {
        return alamat3Waris;
    }

    public void setAlamat3Waris(String alamat3Waris) {
        this.alamat3Waris = alamat3Waris;
    }

    public String getAlamat4Waris() {
        return alamat4Waris;
    }

    public void setAlamat4Waris(String alamat4Waris) {
        this.alamat4Waris = alamat4Waris;
    }

    public String getHubungan() {
        return hubungan;
    }

    public void setHubungan(String hubungan) {
        this.hubungan = hubungan;
    }

    public KodJenisPengenalan getJenisPengenalanWaris() {
        return jenisPengenalanWaris;
    }

    public void setJenisPengenalanWaris(KodJenisPengenalan jenisPengenalanWaris) {
        this.jenisPengenalanWaris = jenisPengenalanWaris;
    }

    public KodJantina getKodJantinaWaris() {
        return kodJantinaWaris;
    }

    public void setKodJantinaWaris(KodJantina kodJantinaWaris) {
        this.kodJantinaWaris = kodJantinaWaris;
    }

    public KodNegeri getKodNegeriWaris() {
        return kodNegeriWaris;
    }

    public void setKodNegeriWaris(KodNegeri kodNegeriWaris) {
        this.kodNegeriWaris = kodNegeriWaris;
    }

    public KodWarganegara getKodWarganegaraWaris() {
        return kodWarganegaraWaris;
    }

    public void setKodWarganegaraWaris(KodWarganegara kodWarganegaraWaris) {
        this.kodWarganegaraWaris = kodWarganegaraWaris;
    }

    public String getNamaWaris() {
        return namaWaris;
    }

    public void setNamaWaris(String namaWaris) {
        this.namaWaris = namaWaris;
    }

    public String getNoPengenalanWaris() {
        return noPengenalanWaris;
    }

    public void setNoPengenalanWaris(String noPengenalanWaris) {
        this.noPengenalanWaris = noPengenalanWaris;
    }

    public String getNoTelBimbitWaris() {
        return noTelBimbitWaris;
    }

    public void setNoTelBimbitWaris(String noTelBimbitWaris) {
        this.noTelBimbitWaris = noTelBimbitWaris;
    }

    public String getNoTelWaris() {
        return noTelWaris;
    }

    public void setNoTelWaris(String noTelWaris) {
        this.noTelWaris = noTelWaris;
    }

    public String getPoskodWaris() {
        return poskodWaris;
    }

    public void setPoskodWaris(String poskodWaris) {
        this.poskodWaris = poskodWaris;
    }

    public WarisOrangKenaSyak getWarisOrangKenaSyak() {
        return warisOrangKenaSyak;
    }

    public void setWarisOrangKenaSyak(WarisOrangKenaSyak warisOrangKenaSyak) {
        this.warisOrangKenaSyak = warisOrangKenaSyak;
    }
//    private String kwg;

    public String getKwg() {
        return kwg;
    }

    public void setKwg(String kwg) {
        this.kwg = kwg;
    }

    public String getKj() {
        return kj;
    }

    public void setKj(String kj) {
        this.kj = kj;
    }

    public String getKw() {
        return kw;
    }

    public void setKw(String kw) {
        this.kw = kw;
    }

    public String getAlamat1Majikan() {
        return alamat1Majikan;
    }

    public void setAlamat1Majikan(String alamat1Majikan) {
        this.alamat1Majikan = alamat1Majikan;
    }

    public String getAlamat2Majikan() {
        return alamat2Majikan;
    }

    public void setAlamat2Majikan(String alamat2Majikan) {
        this.alamat2Majikan = alamat2Majikan;
    }

    public String getAlamat3Majikan() {
        return alamat3Majikan;
    }

    public void setAlamat3Majikan(String alamat3Majikan) {
        this.alamat3Majikan = alamat3Majikan;
    }

    public String getAlamat4Majikan() {
        return alamat4Majikan;
    }

    public void setAlamat4Majikan(String alamat4Majikan) {
        this.alamat4Majikan = alamat4Majikan;
    }

    public KodJantina getKodJantina() {
        return kodJantina;
    }

    public void setKodJantina(KodJantina kodJantina) {
        this.kodJantina = kodJantina;
    }

    public KodNegeri getKodNegeriMajikan() {
        return kodNegeriMajikan;
    }

    public void setKodNegeriMajikan(KodNegeri kodNegeriMajikan) {
        this.kodNegeriMajikan = kodNegeriMajikan;
    }

    public KodWarganegara getKodWarganegara() {
        return kodWarganegara;
    }

    public void setKodWarganegara(KodWarganegara kodWarganegara) {
        this.kodWarganegara = kodWarganegara;
    }

    public KodWarnaKP getKodWarnaKP() {
        return kodWarnaKP;
    }

    public void setKodWarnaKP(KodWarnaKP kodWarnaKP) {
        this.kodWarnaKP = kodWarnaKP;
    }

    public String getNamaMajikan() {
        return namaMajikan;
    }

    public void setNamaMajikan(String namaMajikan) {
        this.namaMajikan = namaMajikan;
    }

    public String getNoFaksMajikan() {
        return noFaksMajikan;
    }

    public void setNoFaksMajikan(String noFaksMajikan) {
        this.noFaksMajikan = noFaksMajikan;
    }

    public String getNoTelMajikan() {
        return noTelMajikan;
    }

    public void setNoTelMajikan(String noTelMajikan) {
        this.noTelMajikan = noTelMajikan;
    }

    public String getPoskodMajikan() {
        return poskodMajikan;
    }

    public void setPoskodMajikan(String poskodMajikan) {
        this.poskodMajikan = poskodMajikan;
    }

    public String getDiBebas() {
        return diBebas;
    }

    public void setDiBebas(String diBebas) {
        this.diBebas = diBebas;
    }

    public KodJenisPengenalan getKodJenisPengenalan() {
        return kodJenisPengenalan;
    }

    public void setKodJenisPengenalan(KodJenisPengenalan kodJenisPengenalan) {
        this.kodJenisPengenalan = kodJenisPengenalan;
    }

    public String getTarikhDiBebas() {
        return tarikhDiBebas;
    }

    public void setTarikhDiBebas(String tarikhDiBebas) {
        this.tarikhDiBebas = tarikhDiBebas;
    }

    public String getTarikhDitahan() {
        return tarikhDitahan;
    }

    public void setTarikhDitahan(String tarikhDitahan) {
        this.tarikhDitahan = tarikhDitahan;
    }

    public String getTempatDireman() {
        return tempatDireman;
    }

    public void setTempatDireman(String tempatDireman) {
        this.tempatDireman = tempatDireman;
    }
    SimpleDateFormat dateF = new SimpleDateFormat("dd/MM/yyyy");
    String tarikhSenaraiHitam;

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }

    public String getCatatan() {
        return catatan;
    }

    public void setCatatan(String catatan) {
        this.catatan = catatan;
    }

    public String getIdPermohonan() {
        return idPermohonan;
    }

    public void setIdPermohonan(String idPermohonan) {
        this.idPermohonan = idPermohonan;
    }

    public String getAlamat1() {
        return alamat1;
    }

    public void setAlamat1(String alamat1) {
        this.alamat1 = alamat1;
    }

    public String getAlamat2() {
        return alamat2;
    }

    public void setAlamat2(String alamat2) {
        this.alamat2 = alamat2;
    }

    public String getAlamat3() {
        return alamat3;
    }

    public void setAlamat3(String alamat3) {
        this.alamat3 = alamat3;
    }

    public String getAlamat4() {
        return alamat4;
    }

    public void setAlamat4(String alamat4) {
        this.alamat4 = alamat4;
    }

    public String getPoskod() {
        return poskod;
    }

    public void setPoskod(String poskod) {
        this.poskod = poskod;
    }

    public String getIdOrangKenaSyak() {
        return idOrangKenaSyak;
    }

    public void setIdOrangKenaSyak(String idOrangKenaSyak) {
        this.idOrangKenaSyak = idOrangKenaSyak;
    }

    public String getNoPengenalan() {
        return noPengenalan;
    }

    public void setNoPengenalan(String noPengenalan) {
        this.noPengenalan = noPengenalan;
    }

    public Alamat getAlamat() {
        return alamat;
    }

    public void setAlamat(Alamat alamat) {
        this.alamat = alamat;
    }

    public AduanOrangKenaSyak getAduanOrangKenaSyak() {
        return aduanOrangKenaSyak;
    }

    public void setAduanOrangKenaSyak(AduanOrangKenaSyak aduanOrangKenaSyak) {
        this.aduanOrangKenaSyak = aduanOrangKenaSyak;
    }

    public Permohonan getPermohonan() {
        return permohonan;
    }

    public void setPermohonan(Permohonan permohonan) {
        this.permohonan = permohonan;
    }

    public KodCawangan getCawangan() {
        return cawangan;
    }

    public void setCawangan(KodCawangan cawangan) {
        this.cawangan = cawangan;
    }

    public KodNegeri getKodNegeri() {
        return negeri;
    }

    public void setKodNegeri(KodNegeri negeri) {
        this.negeri = negeri;
    }

    public String getPenyerahNoPengenalan() {
        return penyerahNoPengenalan;
    }

    public void setPenyerahNoPengenalan(String penyerahNoPengenalan) {
        this.penyerahNoPengenalan = penyerahNoPengenalan;
    }

    public String getPenyerahNama() {
        return penyerahNama;
    }

    public void setPenyerahNama(String penyerahNama) {
        this.penyerahNama = penyerahNama;
    }


    public List<KodBangsa> getSenaraiBangsa() {
        return senaraiBangsa;
    }

    public void setSenaraiBangsa(List<KodBangsa> senaraiBangsa) {
        this.senaraiBangsa = senaraiBangsa;
    }

    public List<WarisOrangKenaSyak> getListWaris() {
        return listWaris;
    }

    public void setListWaris(List<WarisOrangKenaSyak> listWaris) {
        this.listWaris = listWaris;
    }

    public String getNegeriWaris() {
        return negeriWaris;
    }

    public void setNegeriWaris(String negeriWaris) {
        this.negeriWaris = negeriWaris;
    }

    public KodBangsa getBangsaWaris() {
        return bangsaWaris;
    }

    public void setBangsaWaris(KodBangsa bangsaWaris) {
        this.bangsaWaris = bangsaWaris;
    }

    public String getBangsaWaris1() {
        return bangsaWaris1;
    }

    public void setBangsaWaris1(String bangsaWaris1) {
        this.bangsaWaris1 = bangsaWaris1;
    }

    public String getWarganegaraWaris1() {
        return warganegaraWaris1;
    }

    public void setWarganegaraWaris1(String warganegaraWaris1) {
        this.warganegaraWaris1 = warganegaraWaris1;
    }

    public String getBangsaW() {
        return bangsaW;
    }

    public void setBangsaW(String bangsaW) {
        this.bangsaW = bangsaW;
    }

    public String getWarganegara() {
        return warganegara;
    }

    public void setWarganegara(String warganegara) {
        this.warganegara = warganegara;
    }

    public String getBangsaedit() {
        return bangsaedit;
    }

    public void setBangsaedit(String bangsaedit) {
        this.bangsaedit = bangsaedit;
    }

    public String getJantinaedit() {
        return jantinaedit;
    }

    public void setJantinaedit(String jantinaedit) {
        this.jantinaedit = jantinaedit;
    }

    public String getNegeriedit() {
        return negeriedit;
    }

    public void setNegeriedit(String negeriedit) {
        this.negeriedit = negeriedit;
    }

    public String getWarganegaraedit() {
        return warganegaraedit;
    }

    public void setWarganegaraedit(String warganegaraedit) {
        this.warganegaraedit = warganegaraedit;
    }

    public String getIdWaris() {
        return idWaris;
    }

    public void setIdWaris(String idWaris) {
        this.idWaris = idWaris;
    }

    public String getJantinaWaris1() {
        return jantinaWaris1;
    }

    public void setJantinaWaris1(String jantinaWaris1) {
        this.jantinaWaris1 = jantinaWaris1;
    }

    public String getJenisPengenalan() {
        return jenisPengenalan;
    }

    public void setJenisPengenalan(String jenisPengenalan) {
        this.jenisPengenalan = jenisPengenalan;
    }

    public String getJenispengenalanedit() {
        return jenispengenalanedit;
    }

    public void setJenispengenalanedit(String jenispengenalanedit) {
        this.jenispengenalanedit = jenispengenalanedit;
    }

    public List<HakmilikPermohonan> getHakmilikPermohonanList() {
        return hakmilikPermohonanList;
    }

    public void setHakmilikPermohonanList(List<HakmilikPermohonan> hakmilikPermohonanList) {
        this.hakmilikPermohonanList = hakmilikPermohonanList;
    }

    public Boolean getMultipleHakmilik() {
        return multipleHakmilik;
    }

    public void setMultipleHakmilik(Boolean multipleHakmilik) {
        this.multipleHakmilik = multipleHakmilik;
    }

    public HakmilikPermohonan getHakmilikPermohonan() {
        return hakmilikPermohonan;
    }

    public void setHakmilikPermohonan(HakmilikPermohonan hakmilikPermohonan) {
        this.hakmilikPermohonan = hakmilikPermohonan;
    }

    public String getIdHakmilik() {
        return idHakmilik;
    }

    public void setIdHakmilik(String idHakmilik) {
        this.idHakmilik = idHakmilik;
    }

    public Pengguna getPengguna() {
        return pengguna;
    }

    public void setPengguna(Pengguna pengguna) {
        this.pengguna = pengguna;
    }
}
