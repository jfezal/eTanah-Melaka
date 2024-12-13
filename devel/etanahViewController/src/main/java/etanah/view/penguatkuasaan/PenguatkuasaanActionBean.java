/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author farah.shafilla
 */
package etanah.view.penguatkuasaan;

import com.google.inject.Inject;
import able.stripes.AbleActionBean;
import able.stripes.JSP;
import etanah.dao.FasaPermohonanDAO;
import etanah.dao.HakmilikDAO;
import etanah.dao.PermohonanDAO;
import etanah.model.FasaPermohonan;
import etanah.model.Hakmilik;
import etanah.model.HakmilikPermohonan;
import etanah.model.InfoAudit;
import etanah.model.KodKeputusan;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.service.EnforceService;
import etanah.service.LaporanTanahService;
import etanah.service.common.PermohonanService;
import etanah.view.etanahActionBeanContext;
import java.util.List;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.HttpCache;

import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.controller.LifecycleStage;

@UrlBinding("/enforcement")
public class PenguatkuasaanActionBean extends AbleActionBean {

    private Permohonan permohonan;
    private Hakmilik hakmilik;
    private FasaPermohonan fasaPermohonan;
    private Pengguna pengguna;
    private InfoAudit infoAudit;
    @Inject
    PermohonanService permohonanManager;
    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    HakmilikDAO hakmilikDAO;
    @Inject
    FasaPermohonanDAO fasaPermohonanDAO;
    @Inject
    LaporanTanahService laporanTanahService;
    @Inject
    EnforceService enforceService;
    @Inject
    etanah.Configuration conf;

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {
        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);

        if (idPermohonan != null) {
            permohonan = permohonanDAO.findById(idPermohonan);
            HakmilikPermohonan hp = permohonan.getSenaraiHakmilik().get(0);
            if (hp != null) {
                hakmilik = hakmilikDAO.findById(hp.getHakmilik().getIdHakmilik());
            }
//            String[] tnameFasa = {"permohonan", "idAliran"};
//            Object[] modelFasa = {permohonan, "Pilih_tindakan1"};
//
//            List<FasaPermohonan> listFasa = fasaPermohonanDAO.findByEqualCriterias(tnameFasa, modelFasa, null);
//            if (listFasa.size() != 0) {
//                fasaPermohonan = listFasa.get(0);
//                infoAudit = fasaPermohonan.getInfoAudit();
//                infoAudit.setDiKemaskiniOleh(pengguna);
//                infoAudit.setTarikhKemaskini(new java.util.Date());
//            } else {
//                fasaPermohonan = new FasaPermohonan();
//                infoAudit.setDimasukOleh(pengguna);
//                infoAudit.setTarikhMasuk(new java.util.Date());
//            }
        }
    }

    public Resolution laporanmmk() {
        if ("05".equals(conf.getProperty("kodNegeri")) && permohonan.getKodUrusan().getKod().matches("127")) {
            if (permohonan.getKeputusan() == null) {
                getContext().getRequest().setAttribute("form", Boolean.FALSE);
                addSimpleError("Sila buat keputusan terlebih dahulu");
            } else if (!permohonan.getKeputusan().getKod().matches("PE")) {
                getContext().getRequest().setAttribute("form", Boolean.FALSE);
                addSimpleError("Draf ini tidak perlu disediakan");
            } else {
                getContext().getRequest().setAttribute("form", Boolean.TRUE);
            }
        } else {
            getContext().getRequest().setAttribute("form", Boolean.TRUE);
        }
        return new JSP("penguatkuasaan/laporan_MMK.jsp").addParameter("tab", "true");
    }

    public Resolution laporanmmkview() {
        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        return new JSP("penguatkuasaan/laporan_MMK.jsp").addParameter("tab", "true");
    }

    public Resolution simpanMMK() {

//        if (fasaPermohonan == null) {
//            fasaPermohonan = new FasaPermohonan();
//            infoAudit.setDimasukOleh(pengguna);
//            infoAudit.setTarikhMasuk(new java.util.Date());
//        } else {
//            infoAudit.setDiKemaskiniOleh(pengguna);
//            infoAudit.setTarikhKemaskini(new java.util.Date());
//        }
//        fasaPermohonan.setPermohonan(permohonan);
//        fasaPermohonan.setCawangan(permohonan.getCawangan());
//        fasaPermohonan.setInfoAudit(infoAudit);
//        fasaPermohonan.setIdPengguna(pengguna.getIdPengguna());
//        fasaPermohonan.setIdAliran("Pilih_tindakan1");
//        laporanTanahService.simpanFasaPermohonan(fasaPermohonan);
//
//
            String kod = getContext().getRequest().getParameter("permohonan.keputusan.kod");
            String ulasan = getContext().getRequest().getParameter("permohonan.ulasan");
            infoAudit = permohonan.getInfoAudit();
            infoAudit.setDiKemaskiniOleh(pengguna);
            infoAudit.setTarikhKemaskini(new java.util.Date());
            permohonan.setInfoAudit(infoAudit);
            permohonan.setKeputusanOleh(pengguna);
            permohonan.setTarikhKeputusan(new java.util.Date());
            KodKeputusan kodKpsn = permohonan.getKeputusan();
            kodKpsn.setKod(kod);
            permohonan.setKeputusan(kodKpsn);
            permohonan.setUlasan(ulasan);
            permohonanManager.saveOrUpdate(permohonan);

        getContext().getRequest().setAttribute("form", Boolean.TRUE);
        return new JSP("penguatkuasaan/laporan_MMK.jsp").addParameter("tab", "true");
    }

    public Resolution janaSuratPBN() {
        getContext().getRequest().setAttribute("suratPBN", Boolean.TRUE);
        return new JSP("penguatkuasaan/jana_surat.jsp").addParameter("tab", "true");
    }
    
    public Resolution janaSuratKos() {
        getContext().getRequest().setAttribute("suratKos", Boolean.TRUE);
        return new JSP("penguatkuasaan/jana_surat.jsp").addParameter("tab", "true");
    }

    public Resolution listtab() {
        return new ForwardResolution("/WEB-INF/jsp/penguatkuasaan/SenaraiTabSME.jsp");
    }

    public Resolution maklumataduan() {
        return new JSP("penguatkuasaan/maklumat_aduan.jsp").addParameter("tab", "true");
    }

    public Resolution maklumatpengadu() {
        return new JSP("penguatkuasaan/maklumat_pengadu.jsp").addParameter("tab", "true");
    }

    public Resolution maklumatlokasi() {
        return new JSP("penguatkuasaan/maklumat_lokasi_aduan.jsp").addParameter("tab", "true");
    }

    public Resolution maklumataduanview() {
        return new JSP("penguatkuasaan/maklumat_aduan_view.jsp").addParameter("tab", "true");
    }

    public Resolution maklumatpengaduview() {
        return new JSP("penguatkuasaan/maklumat_pengadu_view.jsp").addParameter("tab", "true");
    }

    public Resolution maklumatlokasiview() {
        return new JSP("penguatkuasaan/maklumat_lokasi_aduan_view.jsp").addParameter("tab", "true");
    }

    public Resolution arahan() {
        return new JSP("penguatkuasaan/arahan.jsp").addParameter("tab", "true");
    }

    public Resolution arahanview() {
        return new JSP("penguatkuasaan/arahan_view.jsp").addParameter("tab", "true");
    }

    public Resolution agih() {
        return new JSP("penguatkuasaan/agih_tugasan.jsp").addParameter("tab", "true");
    }

    public Resolution keputusan() {
        return new JSP("penguatkuasaan/keputusan.jsp").addParameter("tab", "true");
    }

    public Resolution keputusanview() {
        return new JSP("penguatkuasaan/keputusan_view.jsp").addParameter("tab", "true");
    }

    public Resolution keputusandanview() {
        return new JSP("penguatkuasaan/keputusan_dan_view.jsp").addParameter("tab", "true");
    }

    public Resolution laporan() {
        return new JSP("penguatkuasaan/laporan_lawatan_tapak.jsp").addParameter("tab", "true");
    }

    public Resolution laporanview() {
        return new JSP("penguatkuasaan/laporan_lawatan_tapak_view.jsp").addParameter("tab", "true");
    }

    public Resolution tindakanptd() {
        return new JSP("penguatkuasaan/tindakan_ptd.jsp").addParameter("tab", "true");
    }

    public Resolution tindakanpptd() {
        return new JSP("penguatkuasaan/tindakan_pptd.jsp").addParameter("tab", "true");
    }

    public Resolution lokasipencerobohan() {
        return new JSP("penguatkuasaan/lokasi_pencerobohan.jsp").addParameter("tab", "true");
    }

    public Resolution lokasipencerobohanview() {
        return new JSP("penguatkuasaan/lokasi_pencerobohan_view.jsp").addParameter("tab", "true");
    }

    public Resolution orangdisyaki() {
        return new JSP("penguatkuasaan/maklumat_orang_disyaki.jsp").addParameter("tab", "true");
    }

    public Resolution orangdisyakiview() {
        return new JSP("penguatkuasaan/maklumat_orang_disyaki_view.jsp").addParameter("tab", "true");
    }

    public Resolution maklumatnotis() {
        return new JSP("penguatkuasaan/maklumat_notis.jsp").addParameter("tab", "true");
    }

    public Resolution maklumatnotisview() {
        return new JSP("penguatkuasaan/maklumat_notis_view.jsp").addParameter("tab", "true");
    }

    public Resolution buktipenyampaiannotis() {
        return new JSP("penguatkuasaan/bukti_penyampaian_notis.jsp").addParameter("tab", "true");
    }

    public Resolution maklumatpemantauan() {
        return new JSP("penguatkuasaan/maklumat_pemantauan.jsp").addParameter("tab", "true");
    }

    public Resolution maklumatpemantauanview() {
        return new JSP("penguatkuasaan/maklumat_pemantauan_view.jsp").addParameter("tab", "true");
    }

    public Resolution tindakanpptdoperasi() {
        return new JSP("penguatkuasaan/tindakan_pptd_operasi.jsp").addParameter("tab", "true");
    }

    public Resolution gagalnotis() {
        return new JSP("penguatkuasaan/kegagalan_mematuhi_notis.jsp").addParameter("tab", "true");
    }

    public Resolution gagalnotisview() {
        return new JSP("penguatkuasaan/kegagalan_mematuhi_notis_view.jsp").addParameter("tab", "true");
    }

    public Resolution maklumatagensi() {
        return new JSP("penguatkuasaan/maklumat_agensi.jsp").addParameter("tab", "true");
    }

    public Resolution maklumatagensiview() {
        return new JSP("penguatkuasaan/maklumat_agensi_view.jsp").addParameter("tab", "true");
    }

    public Resolution suratbantuan() {
        return new JSP("penguatkuasaan/surat_bantuan_agensi.jsp").addParameter("tab", "true");
    }

    public Resolution keputusanmesyuarat() {
        return new JSP("penguatkuasaan/keputusan_mesyuarat.jsp").addParameter("tab", "true");
    }

    public Resolution keputusanmesyuaratview() {
        return new JSP("penguatkuasaan/keputusan_mesyuarat_view.jsp").addParameter("tab", "true");
    }

    public Resolution tindakanoperasi() {
        return new JSP("penguatkuasaan/tindakan_operasi.jsp").addParameter("tab", "true");
    }

    public Resolution tindakanoperasiview() {
        return new JSP("penguatkuasaan/tindakan_operasi_view.jsp").addParameter("tab", "true");
    }

    public Resolution laporanoperasi() {
        return new JSP("penguatkuasaan/laporan_operasi.jsp").addParameter("tab", "true");
    }

    public Resolution barangrampasan() {
        return new JSP("penguatkuasaan/maklumat_barang_rampasan.jsp").addParameter("tab", "true");
    }

    public Resolution barangrampasanstatus() {
        return new JSP("penguatkuasaan/maklumat_barang_rampasan_status.jsp").addParameter("tab", "true");
    }

    public Resolution barangrampasanview() {
        return new JSP("penguatkuasaan/maklumat_barang_rampasan_view.jsp").addParameter("tab", "true");
    }

    public Resolution resultPPTD() {
        return new JSP("penguatkuasaan/resultPPTD.jsp").addParameter("tab", "true");
    }

    public Resolution barangrampasannilai() {
        return new JSP("penguatkuasaan/maklumat_barang_rampasan_nilai.jsp").addParameter("tab", "true");
    }

    public Resolution barangrampasannilaiview() {
        return new JSP("penguatkuasaan/maklumat_barang_rampasan_nilai_view.jsp").addParameter("tab", "true");
    }

    public Resolution suratjaminan() {
        return new JSP("penguatkuasaan/surat_jaminan_akujanji.jsp").addParameter("tab", "true");
    }

    public Resolution suratjaminanview() {
        return new JSP("penguatkuasaan/surat_jaminan_akujanji_view.jsp").addParameter("tab", "true");
    }

    public Resolution status() {
        return new JSP("penguatkuasaan/status.jsp").addParameter("tab", "true");
    }

    public Resolution keputusanpt() {
        return new JSP("penguatkuasaan/keputusan_pt.jsp").addParameter("tab", "true");
    }

    public Resolution lucuthak() {
        return new JSP("penguatkuasaan/minit_arahan_lucuthak.jsp").addParameter("tab", "true");
    }

    public Resolution lucuthakview() {
        return new JSP("penguatkuasaan/minit_arahan_lucuthak_view.jsp").addParameter("tab", "true");
    }

    public Resolution kertassiasatan() {
        return new JSP("penguatkuasaan/maklumat_kertas_siasatan.jsp").addParameter("tab", "true");
    }

    public Resolution kertassiasatanview() {
        return new JSP("penguatkuasaan/maklumat_kertas_siasatan_view.jsp").addParameter("tab", "true");
    }

    public Resolution perakuanuppptg() {
        return new JSP("penguatkuasaan/perakuan_upp_ptg.jsp").addParameter("tab", "true");
    }

    public Resolution ringkasandraf() {
        return new JSP("penguatkuasaan/ringkasan_draf_kertas_siasatan.jsp").addParameter("tab", "true");
    }

    public Resolution keputusanmahkamah() {
        return new JSP("penguatkuasaan/keputusan_mahkamah.jsp").addParameter("tab", "true");
    }

    public Resolution keputusanmahkamahview() {
        return new JSP("penguatkuasaan/keputusan_mahkamah_view.jsp").addParameter("tab", "true");
    }

    public Resolution arahanpptd() {
        return new JSP("penguatkuasaan/arahan_pptd.jsp").addParameter("tab", "true");
    }

    public Resolution arahanpptdview() {
        return new JSP("penguatkuasaan/arahan_pptd_view.jsp").addParameter("tab", "true");
    }

    public Resolution tawarankompaun() {
        return new JSP("penguatkuasaan/maklumat_tawaran_kompaun.jsp").addParameter("tab", "true");
    }

    public Resolution tawarankompaunview() {
        return new JSP("penguatkuasaan/maklumat_tawaran_kompaun_view.jsp").addParameter("tab", "true");
    }

    public Resolution maklumatnotis8A() {
        return new JSP("penguatkuasaan/maklumat_notis8A.jsp").addParameter("tab", "true");
    }

    public Resolution arahanendorsan() {
        return new JSP("penguatkuasaan/arahan_endorsan.jsp").addParameter("tab", "true");
    }

    public Resolution keputusanrayuanptd() {
        return new JSP("penguatkuasaan/keputusan_rayuan_ptd.jsp").addParameter("tab", "true");
    }

    public Resolution keputusanrayuanptdview() {
        return new JSP("penguatkuasaan/keputusan_rayuan_ptd_view.jsp").addParameter("tab", "true");
    }

    public Resolution catatan() {
        return new JSP("penguatkuasaan/catatan.jsp").addParameter("tab", "true");
    }

    public Resolution maklumattanahview() {
        return new JSP("penguatkuasaan/maklumat_tanah_view.jsp").addParameter("tab", "true");
    }

    public Resolution maklumatpihakberkepentinganview() {
        return new JSP("penguatkuasaan/maklumat_pihak_berkepentingan_view.jsp").addParameter("tab", "true");
    }

    public Resolution laporan127() {
        return new JSP("penguatkuasaan/laporan_lawatan_tapak_127.jsp").addParameter("tab", "true");
    }

    public Resolution laporan127view() {
        return new JSP("penguatkuasaan/laporan_lawatan_tapak_127_view.jsp").addParameter("tab", "true");
    }

    public Resolution keputusanptd() {
        return new JSP("penguatkuasaan/keputusan_ptd.jsp").addParameter("tab", "true");
    }

    public Resolution maklumatpelanggaransyarat() {
        return new JSP("penguatkuasaan/maklumat_pelanggaran_syarat.jsp").addParameter("tab", "true");
    }

    public Resolution maklumatborang7A() {
        return new JSP("penguatkuasaan/maklumat_borang7A.jsp").addParameter("tab", "true");
    }

    public Resolution maklumatborang7Aview() {
        return new JSP("penguatkuasaan/maklumat_borang7A_view.jsp").addParameter("tab", "true");
    }

    public Resolution buktipenyampaian() {
        return new JSP("penguatkuasaan/bukti_penyampaian.jsp").addParameter("tab", "true");
    }

    public Resolution maklumatborang7B() {
        return new JSP("penguatkuasaan/maklumat_borang7B.jsp").addParameter("tab", "true");
    }

    public Resolution maklumatborang7Bview() {
        return new JSP("penguatkuasaan/maklumat_borang7B_view.jsp").addParameter("tab", "true");
    }

    public Resolution maklumatborang7E() {
        return new JSP("penguatkuasaan/maklumat_borang7E.jsp").addParameter("tab", "true");
    }

    public Resolution maklumatborang7Eview() {
        return new JSP("penguatkuasaan/maklumat_borang7E_view.jsp").addParameter("tab", "true");
    }

    public Resolution tindakanpptddenda() {
        return new JSP("penguatkuasaan/tindakan_pptd_denda.jsp").addParameter("tab", "true");
    }

    public Resolution maklumatperintahdenda() {
        return new JSP("penguatkuasaan/maklumat_perintah_denda.jsp").addParameter("tab", "true");
    }

    public Resolution tindakanpptdremedi() {
        return new JSP("penguatkuasaan/tindakan_pptd_remedi.jsp").addParameter("tab", "true");
    }

    public Resolution keputusanpptd() {
        return new JSP("penguatkuasaan/keputusan_pptd.jsp").addParameter("tab", "true");
    }

    public Resolution maklumatdendaterdahulu() {
        return new JSP("penguatkuasaan/maklumat_denda_terdahulu.jsp").addParameter("tab", "true");
    }

    public Resolution maklumatdendatambahan() {
        return new JSP("penguatkuasaan/maklumat_perintah_denda_tambahan.jsp").addParameter("tab", "true");
    }

    public Resolution maklumatdendatambahanview() {
        return new JSP("penguatkuasaan/maklumat_perintah_denda_tambahan_view.jsp").addParameter("tab", "true");
    }

    public Resolution statusbayarandenda() {
        return new JSP("penguatkuasaan/status_bayaran_denda.jsp").addParameter("tab", "true");
    }

    public Resolution maklumattambahandenda() {
        return new JSP("penguatkuasaan/maklumat_tambahan_denda.jsp").addParameter("tab", "true");
    }

    public Resolution maklumatborang7F() {
        return new JSP("penguatkuasaan/maklumat_borang7F.jsp").addParameter("tab", "true");
    }

    public Resolution tindakanpptdperampasan() {
        return new JSP("penguatkuasaan/tindakan_pptd_perampasan.jsp").addParameter("tab", "true");
    }

    public Resolution maklumatenkuiri() {
        return new JSP("penguatkuasaan/maklumat_enkuiri.jsp").addParameter("tab", "true");
    }

    public Resolution maklumatlaporanenkuiri() {
        return new JSP("penguatkuasaan/maklumat_laporan_enkuiri.jsp").addParameter("tab", "true");
    }

    public Resolution maklumatlaporanenkuiriview() {
        return new JSP("penguatkuasaan/maklumat_laporan_enkuiri_view.jsp").addParameter("tab", "true");
    }

    public Resolution tindakanpptdlanjutremedi() {
        return new JSP("penguatkuasaan/tindakan_pptd_lanjut_remedi.jsp").addParameter("tab", "true");
    }

    public Resolution arahanpptk() {
        return new JSP("penguatkuasaan/arahan_pptk.jsp").addParameter("tab", "true");
    }

    public Resolution keputusanpemilikansementara() {
        return new JSP("penguatkuasaan/keputusan_pemilikan_sementara.jsp").addParameter("tab", "true");
    }

    public Resolution keputusanpemilikansementaraview() {
        return new JSP("penguatkuasaan/keputusan_pemilikan_sementara_view.jsp").addParameter("tab", "true");
    }

    public Resolution arahanptd() {
        return new JSP("penguatkuasaan/arahan_ptd.jsp").addParameter("tab", "true");
    }

    public Resolution laporanremedi() {
        return new JSP("penguatkuasaan/laporan_operasi_remedi.jsp").addParameter("tab", "true");
    }

    public Resolution laporanremediview() {
        return new JSP("penguatkuasaan/laporan_operasi_remedi_view.jsp").addParameter("tab", "true");
    }

    public Resolution maklumatkosremedi() {
        return new JSP("penguatkuasaan/maklumat_tambahan_kos_remedi.jsp").addParameter("tab", "true");
    }

    public Resolution tindakanpptd127() {
        return new JSP("penguatkuasaan/tindakan_pptd_127.jsp").addParameter("tab", "true");
    }

    public Resolution tindakanpptd127view() {
        return new JSP("penguatkuasaan/tindakan_pptd_127_view.jsp").addParameter("tab", "true");
    }

    public Resolution tindakanuppptg() {
        return new JSP("penguatkuasaan/tindakan_uppptg.jsp").addParameter("tab", "true");
    }

    public Resolution ringkasanlaporanpolis() {
        return new JSP("penguatkuasaan/ringkasan_laporan_polis.jsp").addParameter("tab", "true");
    }

    public Resolution ringkasanlaporanpolisview() {
        return new JSP("penguatkuasaan/ringkasan_laporan_polis_view.jsp").addParameter("tab", "true");
    }

    public Resolution barangtahanan() {
        return new JSP("penguatkuasaan/maklumat_barang_tahanan.jsp").addParameter("tab", "true");
    }

    public Resolution statusbarang() {
        return new JSP("penguatkuasaan/status_barang_tahanan.jsp").addParameter("tab", "true");
    }

    public Resolution tindakanptdkompaun() {
        return new JSP("penguatkuasaan/tindakan_ptd_kompaun.jsp").addParameter("tab", "true");
    }

    public Resolution tindakanuppptgkompaun() {
        return new JSP("penguatkuasaan/tindakan_uppptg_kompaun.jsp").addParameter("tab", "true");
    }

    public Resolution drafmmk1() {
        return new JSP("penguatkuasaan/draf_mmk_1.jsp").addParameter("tab", "true");
    }

//    public Resolution laporanpolis() {
//        return new JSP("penguatkuasaan/laporan_polis.jsp").addParameter("tab", "true");
//    }
//
//    public Resolution laporanpolisview() {
//        return new JSP("penguatkuasaan/laporan_polis_view.jsp").addParameter("tab", "true");
//    }

    public Resolution syoruppptg() {
        return new JSP("penguatkuasaan/syor_uppptg.jsp").addParameter("tab", "true");
    }

    public Resolution syoruppptg427() {
        return new JSP("penguatkuasaan/syor_uppptg_427.jsp").addParameter("tab", "true");
    }

    public Resolution syoruppptg428() {
        return new JSP("penguatkuasaan/syor_uppptg_428kepu.jsp").addParameter("tab", "true");
    }

    public Resolution laporansiasatan() {
        return new JSP("penguatkuasaan/laporan_siasatan.jsp").addParameter("tab", "true");
    }

    public Resolution laporansiasatanview() {
        return new JSP("penguatkuasaan/laporan_siasatan_view.jsp").addParameter("tab", "true");
    }

    public Resolution maklumataduan427() {
        return new JSP("penguatkuasaan/maklumat_aduan_427.jsp").addParameter("tab", "true");
    }

    public Resolution maklumataduan427view() {
        return new JSP("penguatkuasaan/maklumat_aduan_427_view.jsp").addParameter("tab", "true");
    }

    public Resolution maklumataduan428() {
        return new JSP("penguatkuasaan/maklumat_aduan_428.jsp").addParameter("tab", "true");
    }

    public Resolution maklumataduan428view() {
        return new JSP("penguatkuasaan/maklumat_aduan_428_view.jsp").addParameter("tab", "true");
    }

    public Resolution ulasan() {
        return new JSP("penguatkuasaan/ulasan.jsp").addParameter("tab", "true");
    }

    public Resolution ulasanview() {
        return new JSP("penguatkuasaan/ulasan_view.jsp").addParameter("tab", "true");
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

    public FasaPermohonan getFasaPermohonan() {
        return fasaPermohonan;
    }

    public void setFasaPermohonan(FasaPermohonan fasaPermohonan) {
        this.fasaPermohonan = fasaPermohonan;
    }

    public InfoAudit getInfoAudit() {
        return infoAudit;
    }

    public void setInfoAudit(InfoAudit infoAudit) {
        this.infoAudit = infoAudit;
    }

    public Pengguna getPengguna() {
        return pengguna;
    }

    public void setPengguna(Pengguna pengguna) {
        this.pengguna = pengguna;
    }
}
