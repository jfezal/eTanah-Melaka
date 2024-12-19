/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.stripes.pelupusan;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.dao.HakmilikPermohonanDAO;
import etanah.dao.LaporanTanahDAO;
import etanah.dao.PermohonanDAO;
import etanah.dao.PermohonanRujukanLuarDAO;
import etanah.dao.TanahRizabPermohonanDAO;
import etanah.model.Dokumen;
import etanah.model.HakmilikPermohonan;
import etanah.model.ImejLaporan;
import etanah.model.KodBandarPekanMukim;
import etanah.model.KodCawangan;
import etanah.model.KodDaerah;
import etanah.model.KodKecerunanTanah;
import etanah.model.KodRizab;
import etanah.model.LaporanTanah;
import etanah.model.Permohonan;
import etanah.model.PermohonanLaporanPelan;
import etanah.model.PermohonanRujukanLuar;
import etanah.model.TanahRizabPermohonan;
import etanah.service.LaporanTanahService;
import java.util.ArrayList;
import java.util.List;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;

/**
 *
 * @author afham
 */
@UrlBinding("/pelupusan/pelupusan")
public class PelupusanActionBean extends AbleActionBean {

    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    HakmilikPermohonanDAO hakmilikPermohonanDAO ;
    @Inject
    TanahRizabPermohonanDAO tanahRizabPermohonanDAO;
    @Inject
    LaporanTanahDAO laporanTanahDAO ;
    @Inject
    PermohonanRujukanLuarDAO permohonanRujukanLuarDAO ;
     @Inject
    LaporanTanahService laporanTanahService;
    private PermohonanRujukanLuar permohonanRujukanLuar ;
    private Permohonan permohonan;
    private String idPermohonan;
    private List<HakmilikPermohonan> hakmilikPermohonanList;
    private List<TanahRizabPermohonan> tanahRizabPermohonanList ;
    private PermohonanLaporanPelan permohonanLaporanPelan ;
    private LaporanTanah laporanTanah ;
    private KodBandarPekanMukim bandarPekanMukim;
     private KodKecerunanTanah kecerunanTanah;
     private KodDaerah daerah;
     private KodCawangan cawangan;
     private String noLot;
     private String noLitho;
     private String noWarta;
      private String catatan;
    private String projekKerajaan;
     private String lokasi;
     private KodRizab rizab;
     private String tarikhWarta;
     private String idtanahrizabPermohonan;
     private String ulasan;
     private char pandanganImej;
     private List<Dokumen> dokumenList = new ArrayList<Dokumen>();
     private List<String> imej = new ArrayList<String>();
     private ArrayList imageList[]=new ArrayList[5];

     private List<ImejLaporan> imejLaporanList;
     private List<ImejLaporan> utaraImejLaporanList;
     private List<ImejLaporan> selatanImejLaporanList;
     private List<ImejLaporan> timurImejLaporanList;
     private List<ImejLaporan> baratImejLaporanList;
    

    @DefaultHandler
    public Resolution laporan_pelukis_pelan() {
        return new JSP("pelupusan/laporan_pelukis_pelan.jsp").addParameter("tab", "true");
    }

    public Resolution laporan_tanah(){
        return new JSP("pelupusan/laporan_tanah2.jsp").addParameter("tab", "true");
    }


    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {

        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        if (idPermohonan != null) {
            permohonan = permohonanDAO.findById(idPermohonan);
        }
        if(permohonan != null){
            String[] tname = {"permohonan"} ;
            Object[] tvalue = {permohonan} ;
            hakmilikPermohonanList = hakmilikPermohonanDAO.findByEqualCriterias(tname, tvalue, null) ;
            tanahRizabPermohonanList = tanahRizabPermohonanDAO.findByEqualCriterias(tname, tvalue, null) ;
            List<LaporanTanah> lprnTanah = laporanTanahDAO.findByEqualCriterias(tname, tvalue, null) ;
            List<PermohonanRujukanLuar> llrujukLuar = permohonanRujukanLuarDAO.findByEqualCriterias(tname, tvalue, null) ;
            if(!lprnTanah.isEmpty()){
                laporanTanah = lprnTanah.get(0) ;
                   imejLaporanList = laporanTanahService.getLaporImejByLaporanId(laporanTanah.getIdLaporan());
                utaraImejLaporanList = laporanTanahService.getUtaraLaporImejByLaporanId(laporanTanah.getIdLaporan());
                selatanImejLaporanList = laporanTanahService.getSelatanLaporImejByLaporanId(laporanTanah.getIdLaporan());
                timurImejLaporanList = laporanTanahService.getTimurLaporImejByLaporanId(laporanTanah.getIdLaporan());
                baratImejLaporanList = laporanTanahService.getBaratLaporImejByLaporanId(laporanTanah.getIdLaporan());
            }
            if(!llrujukLuar.isEmpty()){
                permohonanRujukanLuar = llrujukLuar.get(0);

            }
        }

    }

    public List<HakmilikPermohonan> getHakmilikPermohonanList() {
        return hakmilikPermohonanList;
    }

    public void setHakmilikPermohonanList(List<HakmilikPermohonan> hakmilikPermohonanList) {
        this.hakmilikPermohonanList = hakmilikPermohonanList;
    }

    public List<TanahRizabPermohonan> getTanahRizabPermohonanList() {
        return tanahRizabPermohonanList;
    }

    public void setTanahRizabPermohonanList(List<TanahRizabPermohonan> tanahRizabPermohonanList) {
        this.tanahRizabPermohonanList = tanahRizabPermohonanList;
    }

    public Permohonan getPermohonan() {
        return permohonan;
    }

    public void setPermohonan(Permohonan permohonan) {
        this.permohonan = permohonan;
    }

    public PermohonanLaporanPelan getPermohonanLaporanPelan() {
        return permohonanLaporanPelan;
    }

    public void setPermohonanLaporanPelan(PermohonanLaporanPelan permohonanLaporanPelan) {
        this.permohonanLaporanPelan = permohonanLaporanPelan;
    }

    public LaporanTanah getLaporanTanah() {
        return laporanTanah;
    }

    public void setLaporanTanah(LaporanTanah laporanTanah) {
        this.laporanTanah = laporanTanah;
    }

    public PermohonanRujukanLuar getPermohonanRujukanLuar() {
        return permohonanRujukanLuar;
    }

    public void setPermohonanRujukanLuar(PermohonanRujukanLuar permohonanRujukanLuar) {
        this.permohonanRujukanLuar = permohonanRujukanLuar;
    }

    public List<ImejLaporan> getBaratImejLaporanList() {
        return baratImejLaporanList;
    }

    public void setBaratImejLaporanList(List<ImejLaporan> baratImejLaporanList) {
        this.baratImejLaporanList = baratImejLaporanList;
    }

    public List<ImejLaporan> getImejLaporanList() {
        return imejLaporanList;
    }

    public void setImejLaporanList(List<ImejLaporan> imejLaporanList) {
        this.imejLaporanList = imejLaporanList;
    }

    public List<ImejLaporan> getSelatanImejLaporanList() {
        return selatanImejLaporanList;
    }

    public void setSelatanImejLaporanList(List<ImejLaporan> selatanImejLaporanList) {
        this.selatanImejLaporanList = selatanImejLaporanList;
    }

    public List<ImejLaporan> getTimurImejLaporanList() {
        return timurImejLaporanList;
    }

    public void setTimurImejLaporanList(List<ImejLaporan> timurImejLaporanList) {
        this.timurImejLaporanList = timurImejLaporanList;
    }

    public List<ImejLaporan> getUtaraImejLaporanList() {
        return utaraImejLaporanList;
    }

    public void setUtaraImejLaporanList(List<ImejLaporan> utaraImejLaporanList) {
        this.utaraImejLaporanList = utaraImejLaporanList;
    }

    public KodCawangan getCawangan() {
        return cawangan;
    }

    public void setCawangan(KodCawangan cawangan) {
        this.cawangan = cawangan;
    }

    public KodDaerah getDaerah() {
        return daerah;
    }

    public void setDaerah(KodDaerah daerah) {
        this.daerah = daerah;
    }

    public List<Dokumen> getDokumenList() {
        return dokumenList;
    }

    public void setDokumenList(List<Dokumen> dokumenList) {
        this.dokumenList = dokumenList;
    }

    public String getIdtanahrizabPermohonan() {
        return idtanahrizabPermohonan;
    }

    public void setIdtanahrizabPermohonan(String idtanahrizabPermohonan) {
        this.idtanahrizabPermohonan = idtanahrizabPermohonan;
    }

    public ArrayList[] getImageList() {
        return imageList;
    }

    public void setImageList(ArrayList[] imageList) {
        this.imageList = imageList;
    }

    public List<String> getImej() {
        return imej;
    }

    public void setImej(List<String> imej) {
        this.imej = imej;
    }

    public KodKecerunanTanah getKecerunanTanah() {
        return kecerunanTanah;
    }

    public void setKecerunanTanah(KodKecerunanTanah kecerunanTanah) {
        this.kecerunanTanah = kecerunanTanah;
    }

    public String getLokasi() {
        return lokasi;
    }

    public void setLokasi(String lokasi) {
        this.lokasi = lokasi;
    }

    public String getNoLitho() {
        return noLitho;
    }

    public void setNoLitho(String noLitho) {
        this.noLitho = noLitho;
    }

    public String getNoLot() {
        return noLot;
    }

    public void setNoLot(String noLot) {
        this.noLot = noLot;
    }

    public String getNoWarta() {
        return noWarta;
    }

    public void setNoWarta(String noWarta) {
        this.noWarta = noWarta;
    }

    public char getPandanganImej() {
        return pandanganImej;
    }

    public void setPandanganImej(char pandanganImej) {
        this.pandanganImej = pandanganImej;
    }

    public KodRizab getRizab() {
        return rizab;
    }

    public void setRizab(KodRizab rizab) {
        this.rizab = rizab;
    }

    public String getTarikhWarta() {
        return tarikhWarta;
    }

    public void setTarikhWarta(String tarikhWarta) {
        this.tarikhWarta = tarikhWarta;
    }

    public String getUlasan() {
        return ulasan;
    }

    public void setUlasan(String ulasan) {
        this.ulasan = ulasan;
    }

    public String getCatatan() {
        return catatan;
    }

    public void setCatatan(String catatan) {
        this.catatan = catatan;
    }

    public String getProjekKerajaan() {
        return projekKerajaan;
    }

    public void setProjekKerajaan(String projekKerajaan) {
        this.projekKerajaan = projekKerajaan;
    }

    
    
}
//    public Resolution showForm() {
//        return new ForwardResolution("/WEB-INF/jsp/pelupusan/pengurusan_dokumen.jsp");
//    }
//

    /**
     * -----------------------------TEST------------------------------------
     * @return
     */
//    public Resolution showForm2() {
//        return new ForwardResolution("/WEB-INF/jsp/pelupusan/kemasukan_bilangan_JKTN.jsp");
//    }
//
//    public Resolution showForm3() {
//        return new ForwardResolution("/WEB-INF/jsp/pelupusan/permohonan_pemberimilikan_tanah.jsp");
//    }
//
//    public Resolution showForm4() {
//        return new JSP("pelupusan/senarai_semakan.jsp").addParameter("tab", "true");
//    }
//
//    public Resolution showTab() {
//        return new JSP("pelupusan/pengurusan_dokumen.jsp").addParameter("popup", "true");
//    }
//
//    public Resolution showForm5() {
//        return new ForwardResolution("/WEB-INF/jsp/pelupusan/semakan_laporan_tanah.jsp");
//    }
//
//    public Resolution showForm6() {
//        return new ForwardResolution("/WEB-INF/jsp/pelupusan/ulasan_jabatan_teknikal.jsp");
//    }
//
//    public Resolution showForm7() {
//        return new ForwardResolution("/WEB-INF/jsp/pelupusan/terima_ulasan_jabatan_teknikal.jsp");
//    }
//
//    public Resolution showForm8() {
//        return new ForwardResolution("/WEB-INF/jsp/pelupusan/senarai_tugasan.jsp");
//    }
//
//    public Resolution showForm9() {
//        return new ForwardResolution("/WEB-INF/jsp/pelupusan/semakan_PU.jsp");
//    }
//
//    public Resolution showForm10() {
//        return new ForwardResolution("/WEB-INF/jsp/pelupusan/pru_penyediaan_borang_4D.jsp");
//    }
//
//    public Resolution showForm11() {
//        return new ForwardResolution("/WEB-INF/jsp/pelupusan/pru_perakuan_MMK.jsp");
//    }
//
//    public Resolution showForm12() {
//        return new ForwardResolution("/WEB-INF/jsp/pelupusan/pru_pengesahan_borang_4D.jsp");
//    }
//
//    public Resolution showForm13() {
//        return new ForwardResolution("/WEB-INF/jsp/pelupusan/permohonan_PU.jsp");
//    }
//
//    public Resolution showForm14() {
//        return new ForwardResolution("/WEB-INF/jsp/pelupusan/penyediaan_plan_QT.jsp");
//    }
//
//    public Resolution showForm15() {
//        return new ForwardResolution("/WEB-INF/jsp/pelupusan/pru_penyediaan_kertas_JKTN.jsp");
//    }
//
//    public Resolution showForm16() {
//        return new ForwardResolution("/WEB-INF/jsp/pelupusan/penyediaan_hakmilik_tetap.jsp");
//    }
//
//    public Resolution showForm17() {
//        return new ForwardResolution("/WEB-INF/jsp/pelupusan/kemasukan_bilangan_MMK.jsp");
//    }
//
//    public Resolution showForm18() {
//        return new ForwardResolution("/WEB-INF/jsp/pelupusan/penyediaan_PU.jsp");
//    }
//
//    public Resolution showForm19() {
//        return new ForwardResolution("/WEB-INF/jsp/pelupusan/pilih_jabatan_teknikal.jsp");
//    }
//
//    public Resolution showForm20() {
//        return new ForwardResolution("/WEB-INF/jsp/pelupusan/pru_kemasukan_ulasan_JPPH.jsp");
//    }
//
//    public Resolution showForm21() {
//        return new ForwardResolution("/WEB-INF/jsp/pelupusan/hakmilik_tetap.jsp");
//    }
//
//    public Resolution showForm22() {
//        return new ForwardResolution("/WEB-INF/jsp/pelupusan/pru_rekod_keputusan_MMK.jsp");
//    }
//
//    public Resolution showForm24() {
//        return new ForwardResolution("/WEB-INF/jsp/pelupusan/tambah_bilangan_JKTN.jsp");
//    }
//
//    public Resolution showForm25() {
//        return new ForwardResolution("/WEB-INF/jsp/pelupusan/pentadbir_tanah_daerah.jsp");
//    }
//
//    public Resolution showForm26() {
//        return new ForwardResolution("/WEB-INF/jsp/pelupusan/senarai_jabatan_teknikal.jsp");
//    }
//
//    public Resolution showForm27() {
//        return new ForwardResolution("/WEB-INF/jsp/pelupusan/laporan_tanah.jsp");
//    }
//
//    public Resolution showForm28() {
//        return new ForwardResolution("/WEB-INF/jsp/pelupusan/maklumat_lot_bersempadan.jsp");
//    }
//
//    public Resolution showForm29() {
//        return new ForwardResolution("/WEB-INF/jsp/pelupusan/pru_perakuan_draf_kertaskerja_JKTN.jsp");
//    }
//
//    public Resolution showForm30() {
//        return new ForwardResolution("/WEB-INF/jsp/pelupusan/penyediaan_borang_4A.jsp");
//    }
//
//    public Resolution showForm31() {
//        return new ForwardResolution("/WEB-INF/jsp/pelupusan/kemasukan_permohonan_pembaharuan_LPS.jsp");
//    }
//
//    public Resolution showForm32() {
//        return new ForwardResolution("/WEB-INF/jsp/pelupusan/pengurusan_dokumen_tambah_baru.jsp");
//    }
//
//    public Resolution showForm33() {
//        return new ForwardResolution("/WEB-INF/jsp/pelupusan/permohonan_permit_ruang_udara.jsp");
//    }
//
//    public Resolution showForm34() {
//        return new ForwardResolution("/WEB-INF/jsp/pelupusan/permohonan_permit_bahan_batuan.jsp");
//    }
//
//    public Resolution showForm35() {
//        return new ForwardResolution("/WEB-INF/jsp/pelupusan/semak_dan_syor_draf_kertaskerja_JKTN.jsp");
//    }
//
//    public Resolution showForm36() {
//        return new ForwardResolution("/WEB-INF/jsp/pelupusan/rekod_keputusan_JKTN.jsp");
//    }
//
//    public Resolution showForm37() {
//        return new ForwardResolution("/WEB-INF/jsp/pelupusan/makluman_keputusan_MMK.jsp");
//    }
//
//    public Resolution showForm38() {
//        return new ForwardResolution("/WEB-INF/jsp/pelupusan/tapisan_keputusan_MMK.jsp");
//    }
//
//    public Resolution showForm39() {
//        return new ForwardResolution("/WEB-INF/jsp/pelupusan/kemaskini_kertas_MMK.jsp");
//    }
//
//    public Resolution showForm40() {
//        return new ForwardResolution("/WEB-INF/jsp/pelupusan/semak_dan_syor_kertaskerja_JKTN.jsp");
//    }
//
//    public Resolution showForm41() {
//        return new ForwardResolution("/WEB-INF/jsp/pelupusan/permohonan_pembatalan_perizaban_tanah.jsp");
//    }
//
//    public Resolution showForm42() {
//        return new ForwardResolution("/WEB-INF/jsp/pelupusan/maklumat_pengarah_pemegang_saham.jsp");
//    }
//
//    public Resolution showForm43() {
//        return new ForwardResolution("/WEB-INF/jsp/pelupusan/maklumat_pekerja.jsp");
//    }
//
//    public Resolution showForm44() {
//        return new ForwardResolution("/WEB-INF/jsp/pelupusan/laporan_tanah2.jsp");
//    }
//
//    public Resolution showForm45() {
//        return new ForwardResolution("/WEB-INF/jsp/pelupusan/laporan_pelukis_pelan2.jsp");
//    }
//
//    public Resolution showForm46() {
//        return new ForwardResolution("/WEB-INF/jsp/pelupusan/ulasan_jabatan_teknikal2.jsp");
//    }
//
//    public Resolution showForm47() {
//        return new ForwardResolution("/WEB-INF/jsp/pelupusan/laporan_tanah3.jsp");
//    }
//
//    public Resolution showForm48() {
//        return new ForwardResolution("/WEB-INF/jsp/pelupusan/maklumat_pembangunan_atas_tanah.jsp");
//    }
//
//    public Resolution showForm49() {
//        return new ForwardResolution("/WEB-INF/jsp/pelupusan/maklumat_keputusan_terdahulu.jsp");
//    }
//
//    public Resolution showForm50() {
//        return new ForwardResolution("/WEB-INF/jsp/pelupusan/senarai_dokumen_disertakan.jsp");
//    }
//
//    public Resolution showForm51() {
//        return new ForwardResolution("/WEB-INF/jsp/pelupusan/penyediaan_kertas_pertimbangan.jsp");
//    }
//
//    public Resolution showForm52() {
//        return new ForwardResolution("/WEB-INF/jsp/pelupusan/semak_kertas_pertimbangan.jsp");
//    }
//
//    public Resolution showForm53() {
//        return new ForwardResolution("/WEB-INF/jsp/pelupusan/perakuan_kertas_pertimbangan.jsp");
//    }
//
//    public Resolution showForm54() {
//        return new ForwardResolution("/WEB-INF/jsp/pelupusan/permohonan_perizaban_tanah.jsp");
//    }
//
//    public Resolution showForm55() {
//        return new ForwardResolution("/WEB-INF/jsp/pelupusan/makluman_dan_penyediaan_surat_keputusan.jsp");
//    }
//
//    public Resolution showForm56() {
//        return new ForwardResolution("/WEB-INF/jsp/pelupusan/kemasukan_maklumat_permohonan_rayuan.jsp");
//    }
//
//    public Resolution showForm57() {
//        return new ForwardResolution("/WEB-INF/jsp/pelupusan/perakuan_kertas_kerja_JKTN.jsp");
//    }
//
//    public Resolution showForm58() {
//        return new ForwardResolution("/WEB-INF/jsp/pelupusan/perakuan_PTG_untuk_ke_JKTN.jsp");
//    }
//
//    public Resolution showForm59() {
//        return new ForwardResolution("/WEB-INF/jsp/pelupusan/tapisan_keputusan_MMK2.jsp");
//    }
//
//    public Resolution showForm60() {
//        return new ForwardResolution("/WEB-INF/jsp/pelupusan/pengesahan_borang_5A.jsp");
//    }
//
//    public Resolution showForm61() {
//        return new ForwardResolution("/WEB-INF/jsp/pelupusan/penyediaan_borang_5A.jsp");
//    }
//
//    public Resolution showForm62() {
//        return new ForwardResolution("/WEB-INF/jsp/pelupusan/kemasukan_maklumat_ansuran.jsp");
//    }
//
//    public Resolution showForm63() {
//        return new ForwardResolution("/WEB-INF/jsp/pelupusan/kemasukan_maklumat_ansuran2.jsp");
//    }
//
//    public Resolution showForm64() {
//        return new ForwardResolution("/WEB-INF/jsp/pelupusan/permohonanan_hak_lalu_lalang_awam.jsp");
//    }
//
//    public Resolution showForm65() {
//        return new ForwardResolution("/WEB-INF/jsp/pelupusan/kemasukan_permohonan_enkuiri.jsp");
//    }
//
//    public Resolution showForm66() {
//        return new ForwardResolution("/WEB-INF/jsp/pelupusan/semak_keputusan_enkuiri.jsp");
//    }
//
//    public Resolution showForm67() {
//        return new ForwardResolution("/WEB-INF/jsp/pelupusan/penyediaan_notis_2a.jsp");
//    }
//
//    public Resolution showForm68() {
//        return new ForwardResolution("/WEB-INF/jsp/pelupusan/semak_kehadiran_pihak_terlibat.jsp");
//    }
//
//    public Resolution showForm69() {
//        return new ForwardResolution("/WEB-INF/jsp/pelupusan/rekod_keputusan_enkuiri.jsp");
//    }
//
//    public Resolution showForm70() {
//        return new ForwardResolution("/WEB-INF/jsp/pelupusan/rekod_cek_pampasan.jsp");
//    }
//
//    public Resolution showForm71() {
//        return new ForwardResolution("/WEB-INF/jsp/pelupusan/penyediaan_surat_pengambilan_cek_pampasan.jsp");
//    }
//
//    public Resolution showForm72() {
//        return new ForwardResolution("/WEB-INF/jsp/pelupusan/rekod_pengambilan_cek_pampasan.jsp");
//    }