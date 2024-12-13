/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.laporan;
import able.stripes.AbleActionBean;
import com.google.inject.Inject;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import org.apache.log4j.Logger;
/**
 *
 * @author zadhirul.farihim
 */
@UrlBinding("/laporanHasil_NS")
public class HasilLaporanActionBean extends AbleActionBean {




    private static final Logger LOG = Logger.getLogger(HasilLaporanActionBean.class);
    @Inject
    private etanah.Configuration conf;
    private String[] senaraiReport;
    private String[] senaraiReportName;
    private String reportName;

    @DefaultHandler
    public Resolution showForm() {
        LOG.info("showForm");
        showReports();
        return new ForwardResolution("/WEB-INF/jsp/hasil/hasil_laporan.jsp");
    }

    private void showReports() {
        LOG.info("showReport:start");

            senaraiReport = new String[]{"Laporan Senarai Terimaan",
                        "Laporan Senarai Penjenisan Hasil Mengikut Kod Jenis Hasil",
                        "Laporan Kemajuan Cukai Tanah, Denda & Notis Mengikut Mukim",
                        "Laporan Senarai Kutipan Hasil Mengikut Kod Jenis Hasil",
                        "Laporan Hakmilik Yang Dikutip Di Tempat Lain",
                        "Laporan Hakmilik Luar Yang Dikutip",
                        "Laporan Senarai Induk Hakmilik Mengikut Mukim",
                        "Laporan Senarai Index Hakmilik Ikut Nama",
                        "Laporan Senarai Index Lot",
                        "Laporan Senarai Hakmilik Batal Mengikut Mukim",
                        "Laporan Senarai Hakmilik Yang Telah Dibatalkan Mengikut Tarikh Batal",
                        "Laporan Senarai Hakmilik Tertunggak bagi Amaun Dari RM dan Ke Atas",
                        "Laporan Senarai Hakmilik Ada Amaun Lebih (Mengikut Daerah)",
                        "Laporan Senarai Alamat Ragu",
                        "Laporan Senarai Hakmilik Beralamat Dan Tidak Beralamat",
                        "Laporan Senarai Hakmilik Yang Dikenakan Notis 6A Tetapi Belum Membuat Bayaran",
                        "Laporan Senarai Hakmilik Yang Dikenakan Notis 6A Tetapi Telah Membuat Bayaran",
                        "Laporan Senarai Hakmilik Mengikut Nama",
                        "Laporan Senarai Hakmilik Yang Kurang Bayar Tahun Ini",
                        "Laporan Senarai Hakmilik Yang Lebih Bayar Tahun Ini",
                        "Laporan Senarai Hakmilik Telah Bayar",
                        "Laporan Senarai Hakmilik Yang Belum Bayar Tahun Ini",
                        "Surat Keputusan Kutipan Tidak Sah",
                        "Surat Keputusan Pengurangan Denda",
                        "Surat Keputusan Permohonan Remisyen",
                        "Notis 6A",
                        "Surat Peringatan",
                        "Borang 8A",
                        "Warta Kerajaan NSDK Sek 100/130 KTN",
                        "Borang Pembatalan Notis 6A",
                        "Perintah Notis Gantian (Surat Kebenaran Notis Gantian, Perintah Penyampaian Notis Gantian, Warta Kerajaan NSDK Notis Gantian)",
                        "Senarai Resit Yang Dibatalkan Bagi Bulan... Tahun...",
                        "Penyata Kutipan Harian/Mingguan",
                        "Laporan Logoff Kutipan Bayaran Urusan Tanah",
                        "Surat Akuan Berkanun",
                        "Bilangan Hakmilik Mengikut Jenis Hakmilik",
                        "Laporan Terimaan Harian dari POS"};

            senaraiReportName = new String[]{"HSLSenaraiTerimaan.rdf",
                        "SPOCLaporanPenjenisanHasil_NS.rdf",
                        "HSLKemajuanKutipanCukaiTanahDendaNotisIkutMukim.rdf",
                        "HSLSenaraiKutipanHasil_Kod_Jenis_Hasil_NS.rdf",
                        "HSLHakmilikYgDikutipDiTempatLain.rdf",
                        "HSLSenaraiHakmilikLuarYgDikutip.rdf",
                        "HSLSenaraiIndukHakmilikMengikutMukim_002.rdf",
                        "HSLSenaraiIndexHakmilikIkutNama.rdf",
                        "HSLSenaraiIndexLot001.rdf",
                        "HSLSenaraiHakmilikBatalMengikutMukim_003.rdf",
                        "HSLSenaraiHakmilikYangTelahDibatalkanMengikutTarikhBatal_005",
                        "HSLSenaraiHmTunggakBgAmaunDanPdHinggaTarikh_002.rdf",
                        "HSLSenaraiHakmilikAdaAmaunLebih001.rdf",
                        "HSLSenaraiAlamatRagu.rdf",
                        "HSLSenaraiHakmilikBeralamatDanTidakBeralamat.rdf",
                        "HSLSenaraiHakmilikNotis6ABelumBayar001.rdf",
                        "HSLSenaraiHakmilikNotis6ABayar001.rdf",
                        "HSLSenaraiHakmilikMengikutNama001.rdf",
                        "HSLSenaraiHakmilikYangKurangBayar.rdf",
                        "HSLSenaraiHakmilikYangLebihBayar.rdf",
                        "HSLSenaraiHakmilikTelahBayar.rdf",
                        "HSLSenaraiHakmilikYangBelumBayar.rdf",
                        "hasilSuratGantiCekTakLakuResit.rdf",
                        "hasilRayuanKurangCukai.rdf",
                        "hasilRayuanKurangCukai.rdf",
                        "hasilNotis6A.rdf",
                        "hasilSuratPeringatan.rdf",
                        "hasilNotis8A.rdf",
                        "hasilWartaKerajaan.rdf",
                        "hasilPembatalanNotis6A.rdf",
                        "hasilNotisGantian.rdf",
                        "HSLSenaraiResitBatal.rdf",
                        "HSLPenyataKutipanMingguan.rdf",
                        "HSLLaporanLogoffKutipan.rdf",
                        "HSLSuratAkuanBerkanun.rdf",
                        "HSLPungutanHasilTanahBilanganHakmilik.rdf",
                        "HSLLaporanTerimaanPos.rdf"};
            
        LOG.info("showReport:finish");
    }

    public Resolution requestParam() {
        reportName = getContext().getRequest().getParameter("namaReport");
        return new ForwardResolution("/WEB-INF/jsp/hasil/hasil_laporan_param.jsp").addParameter("popup", "true");
    }

    public String[] getSenaraiReport() {
        return senaraiReport;
    }

    public void setSenaraiReport(String[] senaraiReport) {
        this.senaraiReport = senaraiReport;
    }

    public String[] getSenaraiReportName() {
        return senaraiReportName;
    }

    public void setSenaraiReportName(String[] senaraiReportName) {
        this.senaraiReportName = senaraiReportName;
    }

    public String getReportName() {
        return reportName;
    }

    public void setReportName(String reportName) {
        this.reportName = reportName;
    }
}
