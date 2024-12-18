/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package etanah.view.stripes.pengambilan;

import etanah.view.stripes.pembangunan.*;
import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.dao.KodAgensiDAO;
import etanah.dao.KodNegeriDAO;
import etanah.dao.PermohonanDAO;
import etanah.dao.PermohonanKertasDAO;
import etanah.dao.PermohonanKertasKandunganDAO;
import etanah.model.AduanLokasi;
import etanah.model.BarangRampasan;
import etanah.model.InfoAudit;
import etanah.model.KodAgensi;
import etanah.model.KodNegeri;
import etanah.model.Notis;
import etanah.model.OperasiPenguatkuasaan;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.model.PermohonanKertas;
import etanah.model.PermohonanKertasKandungan;
import etanah.model.PermohonanRujukanLuar;
import etanah.service.ConsentPtdService;
import etanah.service.EnforceService;
import etanah.service.HakLalulalangService;
import etanah.service.common.EnforcementService;
import etanah.view.stripes.consent.JabatanTeknikal;
import java.util.ArrayList;
import java.util.List;
import java.text.SimpleDateFormat;
import java.util.Date;
import etanah.view.etanahActionBeanContext;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;
import net.sourceforge.stripes.action.RedirectResolution;



/**
 *
 * @author massita
 */

@UrlBinding("/pengambilan/ulasan_jabatan_teknikalPHLL")
public class UlasanJabatanTeknikalPHLLActionBean extends AbleActionBean {

    @Inject
    PermohonanDAO permohonanDAO;
     @Inject
    PermohonanKertasKandunganDAO permohonanKertasKandunganDAO;
    @Inject
    ConsentPtdService conService;
    @Inject
    KodAgensiDAO kodAgensiDAO;
    @Inject
    HakLalulalangService hakLalulalangService;
    @Inject
    PermohonanKertasDAO permohonanKertasDAO;
    @Inject
    EnforcementService enforcementService;
    @Inject
    KodNegeriDAO kodNegeriDAO;
    @Inject
    EnforceService enforceService;
    private KodNegeri kodNegeri;
    private Permohonan permohonan;
    private PermohonanKertas permohonanKertas;
    private PermohonanKertasKandungan kandunganKertasPermohonan;
    private OperasiPenguatkuasaan operasiPenguatkuasaan;
    private PermohonanRujukanLuar permohonanRujukanLuar1;
    private KodAgensi kodAgensi;
    private String jabatanTeknikal;
    private List listUlasan2 = new ArrayList();
    private String ulasan;
    private List<Permohonan> list;
    private List<PermohonanKertas> senaraiKertas;
    private List<PermohonanKertasKandungan> senaraiKandungan;
    private List<PermohonanRujukanLuar> senaraiMahkamah;
    private List<BarangRampasan> senaraiBarangRampasan;
    private Long id_kertas;
    private List idKertas = new ArrayList();
    private Pengguna pguna;
    private AduanLokasi aduanLokasi;
    private Notis notis;
    private PermohonanKertasKandungan permohonanKertasKandungan;
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

    public Permohonan getPermohonan() {
        return permohonan;
    }

    public void setPermohonan(Permohonan permohonan) {
        this.permohonan = permohonan;
    }

    @DefaultHandler
    public Resolution showForm() {
        return new JSP("pengambilan/negerisembilan/haklalulalangpersendirian/ulasan_jabatan_teknikal.jsp").addParameter("tab", "true");
    }

    public Resolution ulasanPopup() {
        return new JSP("pengambilan/negerisembilan/haklalulalangpersendirian/kemasukan_ulasan_jabatan_teknikal.jsp").addParameter("popup", "true");
    }

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {

        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = permohonanDAO.findById(idPermohonan);

       senaraiKertas = hakLalulalangService.findPermohonanKertasByIdPermohonan(idPermohonan,"JABATAN TEKNIKAL");

        etanahActionBeanContext ctx = (etanahActionBeanContext) getContext();
        pguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        String kodN = ctx.getKodNegeri();
        kodNegeri = new KodNegeri();
        kodNegeri.setKod(kodN);
        kodNegeri = kodNegeriDAO.findById(kodN);

        if (idPermohonan != null) {

            operasiPenguatkuasaan = enforceService.findOperasiPenguatkuasaanByIdpermohonan(idPermohonan);
            permohonanKertas = enforcementService.findByKodIdPermohonan(idPermohonan);
            aduanLokasi = enforceService.findAduanLokasiByIdPermohonan(idPermohonan);
            senaraiMahkamah = enforcementService.findMahkamahByIDPermohonan(idPermohonan);
            permohonanRujukanLuar1 = enforceService.findLaporanPolisByIdpermohonan(idPermohonan);
            notis = enforcementService.findSamanByIdmohon(idPermohonan);
            OperasiPenguatkuasaan op = enforceService.findOperasiPenguatkuasaanByIdpermohonan(idPermohonan);

            if (op != null) {
                senaraiBarangRampasan = enforceService.findByIDOperasi(op.getIdOperasi());
            }
            if (permohonanKertas != null) {
                permohonanKertasKandungan = enforcementService.findByKodIdKertas(permohonanKertas.getIdKertas());

            }
        }

    }

    public Resolution simpan() {

            String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
            Pengguna pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);

//             permohonan = permohonanDAO.findById(idPermohonan);
//             senaraiKertas = permohonan.getSenaraiKertas();;
            InfoAudit infoAudit = new InfoAudit();
            infoAudit.setDimasukOleh(pengguna);
            infoAudit.setTarikhMasuk(new java.util.Date());

            PermohonanKertas kertas = new PermohonanKertas();
            

            if (idPermohonan != null){
                PermohonanKertasKandungan kandungan = hakLalulalangService.findKertasKandunganByJabatan(idPermohonan, jabatanTeknikal);
                    if(kandungan != null){
                        addSimpleMessage("Ulasan " +jabatanTeknikal +" telah dimasukkan.Sila masukkan ulasan jabatan teknikal yang berlainan.");
                    } else{
                        PermohonanKertasKandungan kandunganK = new PermohonanKertasKandungan();
                        if (jabatanTeknikal.equals("Jabatan Kebajikan Masyarakat")){
                            kandunganK.setSubtajuk("Jabatan Kebajikan Masyarakat");
                            kandunganK.setKandungan(ulasan);
                        }
                        else if (jabatanTeknikal.equals("Jabatan Kerja Raya")){
                            kandunganK.setSubtajuk("Jabatan Kerja Raya");
                            kandunganK.setKandungan(ulasan);
                        }
                        else if (jabatanTeknikal.equals("Jabatan Pengairan dddffan Saliran")){
                            kandunganK.setSubtajuk("Jabatan Pengairan dan Saliran");
                            kandunganK.setKandungan(ulasan);
                        }
                        else if (jabatanTeknikal.equals("Jabatan Perancang Bandar dan Desa")){
                            kandunganK.setSubtajuk("Jabatan Perancang Bandar dan Desa");
                            kandunganK.setKandungan(ulasan);
                        }
                        else if (jabatanTeknikal.equals("Jabatan Tenaga Kerja")){
                            kandunganK.setSubtajuk("Jabatan Tenaga Kerja");
                            kandunganK.setKandungan(ulasan);
                        }
                        kertas.setPermohonan(permohonan);
                        kertas.setCawangan(pengguna.getKodCawangan());
                        kertas.setTajuk("JABATAN TEKNIKAL");
                        kertas.setInfoAudit(infoAudit);
                        kandunganK.setCawangan(pengguna.getKodCawangan());
                        kandunganK.setKertas(kertas);
                        kandunganK.setInfoAudit(infoAudit);
                        hakLalulalangService.simpanPermohonanKertas(kertas);
                        hakLalulalangService.simpanPermohonanKertasKandungan(kandunganK);
                        addSimpleMessage("Maklumat telah berjaya disimpan.");
                    }
            }
//        rehydrate();
        return new ForwardResolution("/WEB-INF/jsp/pengambilan/negerisembilan/haklalulalangpersendirian/ulasan_jabatan_teknikal.jsp").addParameter("tab", "true");
    }

    public Resolution refreshpage() {
    rehydrate();
    return new RedirectResolution(UlasanJabatanTeknikalPHLLActionBean.class, "locate");
    }

    public Resolution refreshpageUlasanJabatanTeknikal(){
    rehydrate();
    return new JSP("pengambilan/negerisembilan/haklalulalangpersendirian/kemasukan_ulasan_jabatan_teknikal.jsp").addParameter("popup", "true");
    }

    public Resolution deleteSingle() {

        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        String idKertas = getContext().getRequest().getParameter("idKertas");
        System.out.println("idKertas:"+idKertas);
        if(idKertas != null){
            PermohonanKertas mohonKertas = hakLalulalangService.findById(idKertas);
            PermohonanKertasKandungan kandunganKertas = hakLalulalangService.findKertasKandunganByIdKertas(idKertas);
            System.out.println("kandunganKertas:" +kandunganKertas);
            if(mohonKertas != null){
                if(kandunganKertas != null){
                    hakLalulalangService.deleteKertasKandungan(kandunganKertas);
                    hakLalulalangService.deleteKertas(mohonKertas);
                    senaraiKertas = hakLalulalangService.findPermohonanKertasByIdPermohonan(idPermohonan,"ULASAN JABATAN TEKNIKAL");
                    addSimpleMessage("Data telah berjaya dihapuskan.");
                }
                else{
                    addSimpleMessage("Data tidak bejaya dihapuskan.");
                }
            }
        }
        rehydrate();
        return new JSP("pengambilan/negerisembilan/haklalulalangpersendirian/ulasan_jabatan_teknikal.jsp").addParameter("tab", "true");
    }

    public KodAgensi getKodAgensi() {
        return kodAgensi;
    }

    public void setKodAgensi(KodAgensi kodAgensi) {
        this.kodAgensi = kodAgensi;
    }

    public String getUlasan() {
        return ulasan;
    }

    public void setUlasan(String ulasan) {
        this.ulasan = ulasan;
    }

    public List getListUlasan2() {
        return listUlasan2;
    }

    public void setListUlasan2(List listUlasan2) {
        this.listUlasan2 = listUlasan2;
    }

    /**
     * @return the permohonanKertas
     */
    public PermohonanKertas getPermohonanKertas() {
        return permohonanKertas;
    }

    /**
     * @param permohonanKertas the permohonanKertas to set
     */
    public void setPermohonanKertas(PermohonanKertas permohonanKertas) {
        this.permohonanKertas = permohonanKertas;
    }

    /**
     * @return the kandunganKertasPermohonan
     */
    public PermohonanKertasKandungan getKandunganKertasPermohonan() {
        return kandunganKertasPermohonan;
    }

    /**
     * @param kandunganKertasPermohonan the kandunganKertasPermohonan to set
     */
    public void setKandunganKertasPermohonan(PermohonanKertasKandungan kandunganKertasPermohonan) {
        this.kandunganKertasPermohonan = kandunganKertasPermohonan;
    }

     /**
     * @return the jabatanTeknikal
     */
    public String getJabatanTeknikal() {
        return jabatanTeknikal;
    }

    /**
     * @param jabatanTeknikal the jabatanTeknikal to set
     */
    public void setJabatanTeknikal(String jabatanTeknikal) {
        this.jabatanTeknikal = jabatanTeknikal;
    }

    /**
     * @return the senaraiKertas
     */
    public List<PermohonanKertas> getSenaraiKertas() {
        return senaraiKertas;
    }

    /**
     * @param senaraiKertas the senaraiKertas to set
     */
    public void setSenaraiKertas(List<PermohonanKertas> senaraiKertas) {
        this.senaraiKertas = senaraiKertas;
    }

    /**
     * @return the senaraiKandungan
     */
    public List<PermohonanKertasKandungan> getSenaraiKandungan() {
        return senaraiKandungan;
    }

    /**
     * @param senaraiKandungan the senaraiKandungan to set
     */
    public void setSenaraiKandungan(List<PermohonanKertasKandungan> senaraiKandungan) {
        this.senaraiKandungan = senaraiKandungan;
    }

    /**
     * @return the list
     */
    public List<Permohonan> getList() {
        return list;
    }

    /**
     * @param list the list to set
     */
    public void setList(List<Permohonan> list) {
        this.list = list;
    }

    /**
     * @return the id_kertas
     */
    public Long getId_kertas() {
        return id_kertas;
    }

    /**
     * @param id_kertas the id_kertas to set
     */
    public void setId_kertas(Long id_kertas) {
        this.id_kertas = id_kertas;
    }

    /**
     * @return the idKertas
     */
    public List getIdKertas() {
        return idKertas;
    }

    /**
     * @param idKertas the idKertas to set
     */
    public void setIdKertas(List idKertas) {
        this.idKertas = idKertas;
    }

    public AduanLokasi getAduanLokasi() {
        return aduanLokasi;
    }

    public void setAduanLokasi(AduanLokasi aduanLokasi) {
        this.aduanLokasi = aduanLokasi;
    }

    public EnforceService getEnforceService() {
        return enforceService;
    }

    public void setEnforceService(EnforceService enforceService) {
        this.enforceService = enforceService;
    }

    public EnforcementService getEnforcementService() {
        return enforcementService;
    }

    public void setEnforcementService(EnforcementService enforcementService) {
        this.enforcementService = enforcementService;
    }

    public HakLalulalangService getHakLalulalangService() {
        return hakLalulalangService;
    }

    public void setHakLalulalangService(HakLalulalangService hakLalulalangService) {
        this.hakLalulalangService = hakLalulalangService;
    }

    public KodNegeri getKodNegeri() {
        return kodNegeri;
    }

    public void setKodNegeri(KodNegeri kodNegeri) {
        this.kodNegeri = kodNegeri;
    }

    public Notis getNotis() {
        return notis;
    }

    public void setNotis(Notis notis) {
        this.notis = notis;
    }

    public OperasiPenguatkuasaan getOperasiPenguatkuasaan() {
        return operasiPenguatkuasaan;
    }

    public void setOperasiPenguatkuasaan(OperasiPenguatkuasaan operasiPenguatkuasaan) {
        this.operasiPenguatkuasaan = operasiPenguatkuasaan;
    }

    public PermohonanKertasKandungan getPermohonanKertasKandungan() {
        return permohonanKertasKandungan;
    }

    public void setPermohonanKertasKandungan(PermohonanKertasKandungan permohonanKertasKandungan) {
        this.permohonanKertasKandungan = permohonanKertasKandungan;
    }

    public PermohonanRujukanLuar getPermohonanRujukanLuar1() {
        return permohonanRujukanLuar1;
    }

    public void setPermohonanRujukanLuar1(PermohonanRujukanLuar permohonanRujukanLuar1) {
        this.permohonanRujukanLuar1 = permohonanRujukanLuar1;
    }

    public Pengguna getPguna() {
        return pguna;
    }

    public void setPguna(Pengguna pguna) {
        this.pguna = pguna;
    }

    public List<BarangRampasan> getSenaraiBarangRampasan() {
        return senaraiBarangRampasan;
    }

    public void setSenaraiBarangRampasan(List<BarangRampasan> senaraiBarangRampasan) {
        this.senaraiBarangRampasan = senaraiBarangRampasan;
    }

    public List<PermohonanRujukanLuar> getSenaraiMahkamah() {
        return senaraiMahkamah;
    }

    public void setSenaraiMahkamah(List<PermohonanRujukanLuar> senaraiMahkamah) {
        this.senaraiMahkamah = senaraiMahkamah;
    }

}
