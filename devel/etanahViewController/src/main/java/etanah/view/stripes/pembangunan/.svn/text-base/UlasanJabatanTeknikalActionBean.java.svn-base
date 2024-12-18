/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package etanah.view.stripes.pembangunan;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.dao.KodAgensiDAO;
import etanah.dao.PermohonanDAO;
import etanah.dao.PermohonanKertasDAO;
import etanah.dao.PermohonanKertasKandunganDAO;
import etanah.model.InfoAudit;
import etanah.model.KodAgensi;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.model.PermohonanKertas;
import etanah.model.PermohonanKertasKandungan;
import etanah.service.ConsentPtdService;
import etanah.service.PembangunanService;
import etanah.view.stripes.consent.JabatanTeknikal;
import java.util.ArrayList;
import java.util.List;
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
 * @author nursyazwani
 */

@UrlBinding("/pembangunan/ulasan_jabatan_teknikal")
public class UlasanJabatanTeknikalActionBean extends AbleActionBean {

    @Inject
    PermohonanDAO permohonanDAO;
     @Inject
    PermohonanKertasKandunganDAO permohonanKertasKandunganDAO;
    @Inject
    ConsentPtdService conService;
    @Inject
    KodAgensiDAO kodAgensiDAO;
    @Inject
    PembangunanService devService;
    @Inject
    PermohonanKertasDAO permohonanKertasDAO;
    private Permohonan permohonan;
    private PermohonanKertas kertasPermohonan;
    private PermohonanKertasKandungan kandunganKertasPermohonan;
    private KodAgensi kodAgensi;
    private String jabatanTeknikal;
    private List listUlasan2 = new ArrayList();
    private String ulasan;
    private List<Permohonan> list;
    private List<PermohonanKertas> senaraiKertas;
    private List<PermohonanKertasKandungan> senaraiKandungan;
    private Long id_kertas;
    private List idKertas = new ArrayList();

    public Permohonan getPermohonan() {
        return permohonan;
    }

    public void setPermohonan(Permohonan permohonan) {
        this.permohonan = permohonan;
    }

    @DefaultHandler
    public Resolution showForm() {
        return new JSP("pembangunan/common/ulasan_jabatan_teknikal.jsp").addParameter("tab", "true");
    }

    public Resolution ulasanPopup() {
        return new JSP("pembangunan/common/kemasukan_ulasan_jabatan_teknikal.jsp").addParameter("tab", "true");
    }

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {

        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = permohonanDAO.findById(idPermohonan);

//        senaraiKertas = permohonan.getSenaraiKertas();
          senaraiKertas = devService.findPermohonanKertasByIdPermohonan(idPermohonan,"JABATAN TEKNIKAL");

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
                PermohonanKertasKandungan kandungan = devService.findKertasKandunganByJabatan(idPermohonan, jabatanTeknikal);
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
                        else if (jabatanTeknikal.equals("Jabatan Pengairan dan Saliran")){
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
                        devService.simpanPermohonanKertas(kertas);
                        devService.simpanPermohonanKertasKandungan(kandunganK);
                        addSimpleMessage("Maklumat telah berjaya disimpan.");
                    }
            }
//        rehydrate();
        return new ForwardResolution("/WEB-INF/jsp/pembangunan/common/ulasan_jabatan_teknikal.jsp").addParameter("tab", "true");
    }

    public Resolution refreshpage() {
    rehydrate();
    return new RedirectResolution(UlasanJabatanTeknikalActionBean.class, "locate");
    }

    public Resolution deleteSingle() {

        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        String idKertas = getContext().getRequest().getParameter("idKertas");
        System.out.println("idKertas:"+idKertas);
        if(idKertas != null){
            PermohonanKertas mohonKertas = devService.findById(idKertas);
            PermohonanKertasKandungan kandunganKertas = devService.findKertasKandunganByIdKertas(idKertas);
            System.out.println("kandunganKertas:" +kandunganKertas);
            if(mohonKertas != null){
                if(kandunganKertas != null){
                    devService.deleteKertasKandungan(kandunganKertas);
                    devService.deleteKertas(mohonKertas);
                    senaraiKertas = devService.findPermohonanKertasByIdPermohonan(idPermohonan,"ULASAN JABATAN TEKNIKAL");
                    addSimpleMessage("Data telah berjaya dihapuskan.");
                }
                else{
                    addSimpleMessage("Data tidak bejaya dihapuskan.");
                }
            }
        }
        rehydrate();
        return new JSP("pembangunan/common/ulasan_jabatan_teknikal.jsp").addParameter("tab", "true");
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
     * @return the kertasPermohonan
     */
    public PermohonanKertas getKertasPermohonan() {
        return kertasPermohonan;
    }

    /**
     * @param kertasPermohonan the kertasPermohonan to set
     */
    public void setKertasPermohonan(PermohonanKertas kertasPermohonan) {
        this.kertasPermohonan = kertasPermohonan;
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
}
