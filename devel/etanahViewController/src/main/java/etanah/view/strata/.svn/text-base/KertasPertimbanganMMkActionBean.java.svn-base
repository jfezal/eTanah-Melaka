               /*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.strata;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.dao.BangunanTingkatDAO;
import etanah.dao.HakmilikDAO;
import etanah.dao.HakmilikPermohonanDAO;
import etanah.dao.PemohonDAO;
import etanah.dao.PermohonanDAO;
import etanah.dao.PermohonanKertasDAO;
import etanah.model.Hakmilik;
import etanah.model.HakmilikPermohonan;
import etanah.model.HakmilikPihakBerkepentingan;
import etanah.model.InfoAudit;
import etanah.model.Pemohon;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.model.PermohonanKertas;
import etanah.model.PermohonanKertasKandungan;
import etanah.model.PermohonanPihak;
import etanah.service.ConsentPtdService;
import etanah.view.etanahActionBeanContext;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;
import org.apache.commons.lang.StringUtils;
import etanah.model.PermohonanStrata;
import etanah.dao.PermohonanStrataDAO;
import etanah.service.StrataPtService;
import org.apache.log4j.Logger;
import etanah.dao.PermohonanBangunanDAO;
import etanah.model.PermohonanBangunan;
import etanah.model.BangunanTingkat;
import etanah.dao.KodDokumenDAO;
import etanah.model.BangunanPetak;
import etanah.model.KodDokumen;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Date;

/**
 *
 * @author Murali
 */
@UrlBinding("/strata/Kertas_pertimbangan_MMK")
public class KertasPertimbanganMMkActionBean extends AbleActionBean {

    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    HakmilikDAO hakmilikDAO;
    @Inject
    PemohonDAO pemohonDAO;
    @Inject
    ConsentPtdService conService;
    @Inject
    PermohonanKertasDAO permohonanKertasDAO;
    @Inject
    PermohonanStrataDAO permohonanStrataDAO;
    @Inject
    StrataPtService strService;
    @Inject
    HakmilikPermohonanDAO hakmilikPermohonanDAO;
    @Inject
    PermohonanBangunanDAO permohonanBangunanDAO;
    @Inject
    BangunanTingkatDAO bangunanTingkatDAO;
    @Inject
    KodDokumenDAO kodDokumenDAO;
    private Permohonan permohonan;
    private Hakmilik hakmilik;
    private Pemohon pemohon;
    private HakmilikPermohonan hakmilikPermohonan;
    private HakmilikPermohonan mohonHakmilik = new HakmilikPermohonan();
    private PermohonanPihak penerima;
    private HakmilikPihakBerkepentingan hakmilikPihakBerkepentingan;
    private PermohonanKertasKandungan kertasKandungan;
    private PermohonanKertas permohonanKertas;
    private String stageId;
    String latarBelakang;
    String huraianPentadbir;
    String syorPentadbir;
    String huraianPtg;
    String syorPtg;
    String tarikhDaftar;
    String tarikhMesyuarat;
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    private PermohonanStrata permohonanStrata;
    private List<PermohonanStrata> list;
    private static final Logger LOG = Logger.getLogger(KertasPertimbanganMMkActionBean.class);
    private String tajuk;
    private List<PermohonanBangunan> pBangunanL;
    private List<BangunanTingkat> bangunanTingkatList;
    private List<BangunanPetak> bangunanPetakList;
    private PermohonanBangunan permohonanBangunan;
    private KodDokumen kd;
    private String tujuan;
    private String latarbelakang1;
    private String latarbelakang2;
    private String asaspermohon;
    private String asaspermohon2;
    private String asaspermohon3;
    private String ulasapengarah;
    private String syorpengarah;
    private String keputusan;
    private String pihaknama;
    private List<PermohonanKertasKandungan> senaraiLaporanKandungan1;
    private List<PermohonanKertasKandungan> senaraiLaporanKandungan2;
    private List<PermohonanKertasKandungan> senaraiLaporanKandungan3;
    private List<PermohonanKertasKandungan> senaraiLaporanKandungan4;
    private List<PermohonanKertasKandungan> senaraiLaporanKandungan5;
    private List<PermohonanKertasKandungan> senaraiLaporanKandungan6;
    private String idKandungan;
    NumberFormat money = new DecimalFormat("##,##,##0.00");
    private boolean emptySenarai = true;
    private boolean emptySenarai5 = true;
    private boolean emptySenarai6 = true;
    private String subAsasPermohon1;
    private String subAsasPermohon2;
    private String subAsasPermohon3;
    private String subUlasanPengarah1;
    private String subUlasanPengarah2;
    private String subUlasanPengarah3;

    public boolean isEmptySenarai5() {
        return emptySenarai5;
    }

    public void setEmptySenarai5(boolean emptySenarai5) {
        this.emptySenarai5 = emptySenarai5;
    }

    public boolean isEmptySenarai6() {
        return emptySenarai6;
    }

    public void setEmptySenarai6(boolean emptySenarai6) {
        this.emptySenarai6 = emptySenarai6;
    }

    public boolean isEmptySenarai() {
        return emptySenarai;
    }

    public void setEmptySenarai(boolean emptySenarai) {
        this.emptySenarai = emptySenarai;
    }

    public String getIdKandungan() {
        return idKandungan;
    }

    public void setIdKandungan(String idKandungan) {
        this.idKandungan = idKandungan;
    }

    public String getAsaspermohon() {
        return asaspermohon;
    }

    public void setAsaspermohon(String asaspermohon) {
        this.asaspermohon = asaspermohon;
    }

    public String getAsaspermohon2() {
        return asaspermohon2;
    }

    public void setAsaspermohon2(String asaspermohon2) {
        this.asaspermohon2 = asaspermohon2;
    }

    public String getAsaspermohon3() {
        return asaspermohon3;
    }

    public void setAsaspermohon3(String asaspermohon3) {
        this.asaspermohon3 = asaspermohon3;
    }

    public String getKeputusan() {
        return keputusan;
    }

    public void setKeputusan(String keputusan) {
        this.keputusan = keputusan;
    }

    public String getLatarbelakang1() {
        return latarbelakang1;
    }

    public void setLatarbelakang1(String latarbelakang1) {
        this.latarbelakang1 = latarbelakang1;
    }

    public String getLatarbelakang2() {
        return latarbelakang2;
    }

    public void setLatarbelakang2(String latarbelakang2) {
        this.latarbelakang2 = latarbelakang2;
    }

    public String getSyorpengarah() {
        return syorpengarah;
    }

    public void setSyorpengarah(String syorpengarah) {
        this.syorpengarah = syorpengarah;
    }

    public String getUlasapengarah() {
        return ulasapengarah;
    }

    public void setUlasapengarah(String ulasapengarah) {
        this.ulasapengarah = ulasapengarah;
    }

    public String getTujuan() {
        return tujuan;
    }

    public void setTujuan(String tujuan) {
        this.tujuan = tujuan;
    }

    public PermohonanKertas getPermohonanKertas() {
        return permohonanKertas;
    }

    public void setPermohonanKertas(PermohonanKertas permohonanKertas) {
        this.permohonanKertas = permohonanKertas;
    }

    public PermohonanKertasDAO getPermohonanKertasDAO() {
        return permohonanKertasDAO;
    }

    public void setPermohonanKertasDAO(PermohonanKertasDAO permohonanKertasDAO) {
        this.permohonanKertasDAO = permohonanKertasDAO;
    }

    public PermohonanBangunan getPermohonanBangunan() {
        return permohonanBangunan;
    }

    public void setPermohonanBangunan(PermohonanBangunan permohonanBangunan) {
        this.permohonanBangunan = permohonanBangunan;
    }

    public List<PermohonanBangunan> getpBangunanL() {
        return pBangunanL;
    }

    public void setpBangunanL(List<PermohonanBangunan> pBangunanL) {
        this.pBangunanL = pBangunanL;
    }

    public String getTajuk() {
        return tajuk;
    }

    public void setTajuk(String tajuk) {
        this.tajuk = tajuk;
    }

    public PermohonanStrata getPermohonanStrata() {
        return permohonanStrata;
    }

    public void setPermohonanStrata(PermohonanStrata permohonanStrata) {
        this.permohonanStrata = permohonanStrata;
    }

    public Permohonan getPermohonan() {
        return permohonan;
    }

    public void setPermohonan(Permohonan permohonan) {
        this.permohonan = permohonan;
    }

    public HakmilikPermohonan getHakmilikPermohonan() {
        return hakmilikPermohonan;
    }

    public void setHakmilikPermohonan(HakmilikPermohonan hakmilikPermohonan) {
        this.hakmilikPermohonan = hakmilikPermohonan;
    }

    public HakmilikPermohonan getMohonHakmilik() {
        return mohonHakmilik;
    }

    public void setMohonHakmilik(HakmilikPermohonan mohonHakmilik) {
        this.mohonHakmilik = mohonHakmilik;
    }

    public BangunanTingkatDAO getBangunanTingkatDAO() {
        return bangunanTingkatDAO;
    }

    public void setBangunanTingkatDAO(BangunanTingkatDAO bangunanTingkatDAO) {
        this.bangunanTingkatDAO = bangunanTingkatDAO;
    }

    public PermohonanBangunanDAO getPermohonanBangunanDAO() {
        return permohonanBangunanDAO;
    }

    public void setPermohonanBangunanDAO(PermohonanBangunanDAO permohonanBangunanDAO) {
        this.permohonanBangunanDAO = permohonanBangunanDAO;
    }

    public String getPihaknama() {
        return pihaknama;
    }

    public void setPihaknama(String pihaknama) {
        this.pihaknama = pihaknama;
    }

    @DefaultHandler
    public Resolution showForm() {
        getContext().getRequest().setAttribute("display", Boolean.TRUE);
        return new JSP("strata/kos_rendah/kertasPertimbangan_mmk.jsp").addParameter("tab", "true");
    }

    public Resolution showForm1() {
        getContext().getRequest().setAttribute("display", Boolean.TRUE);
        return new JSP("strata/kos_rendah/kertasPertimbangan_mmk_111.jsp").addParameter("tab", "true");
    }

    public Resolution showForm2() {
        getContext().getRequest().setAttribute("display", Boolean.TRUE);
        return new JSP("strata/kos_rendah/kertasPertimbangan_mmk_222.jsp").addParameter("tab", "true");
    }

    public Resolution showForm3() {
        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = permohonanDAO.findById(idPermohonan);
        permohonanStrata = strService.findID(idPermohonan);
        mohonHakmilik = strService.findMohonHakmilik(idPermohonan);
        getContext().getRequest().setAttribute("display", Boolean.TRUE);
        String lot = " ";
        if (mohonHakmilik.getHakmilik().getLot() != null) {
            lot = mohonHakmilik.getHakmilik().getLot().getNama();

        }

        String nlot = " ";
        if (mohonHakmilik.getHakmilik().getNoLot() != null) {
            nlot = mohonHakmilik.getHakmilik().getNoLot();
        }

        String kbpm = " ";
        if (mohonHakmilik.getHakmilik().getBandarPekanMukim() != null) {
            kbpm = mohonHakmilik.getHakmilik().getBandarPekanMukim().getNama();
        }

        String daerah = " ";
        if (mohonHakmilik.getHakmilik().getDaerah() != null) {
            daerah = mohonHakmilik.getHakmilik().getDaerah().getNama();
        }
        String date = " ";
        if (permohonan.getInfoAudit().getTarikhMasuk() != null) {
            date = sdf.format(permohonan.getInfoAudit().getTarikhMasuk());
        }

        int bngnTotal = 0;
        pBangunanL = strService.getSenaraiMohonBangunan(idPermohonan);
        bngnTotal = pBangunanL.size();
        int tingkatTotal = 0;
        int petakTotal = 0;
        for (PermohonanBangunan pb : pBangunanL) {
            bangunanTingkatList = strService.findByIDBangunan(pb.getIdBangunan());
            tingkatTotal += bangunanTingkatList.size();
            for (BangunanTingkat bt : bangunanTingkatList) {
                bangunanPetakList = strService.getSenaraiPetak(bt.getIdTingkat());
                petakTotal += bangunanPetakList.size();
            }
        }
        String lcatbang = " ";
        if (permohonanStrata.getLaporanKategoriBangunani() != null) {
            lcatbang = permohonanStrata.getLaporanKategoriBangunani();
        }


        String mhdate = " ";
        if (permohonanStrata.getCfTarikhKeluar() != null) {
            mhdate = sdf.format(permohonanStrata.getCfTarikhKeluar());
        }

        this.ulasapengarah = "PENGARAH TANAH DAN GALIAN BERPENDAPAT PERMOHONAN INI WAJAR DILULUSKAN BERDASARKAN KEPADA ASAS-ASAS BERIKUT :" + "<br>"
                + "  " + " 4.1.1:    HARGA JUALAN PETAK ADALAH RM " + money.format(permohonanStrata.getHargaPetak()) + " " + "SEUNIT, IAITU HARGA YANG DIKELASKAN SEBAGAI BANGUNAN KOS RENDAH" + "<br>"
                + "  " + " 4.1.2:    PEMBELI-PEMBELI ADALAH TERDIRI DARIPADA MEREKA YANG BERPENDAPATAN RENDAH;" + " " + "<br>"
                + "  " + " 4.1.3:    PENGKELASAN JENIS BANGUNAN INI AKAN MEMBANTU PEMBELI DARI SEGI ASPEK PENGURUSAN BANGUNAN.<br>"
                + " INI ADALAH KERANA SEBELUM PERBADANAN PENGURUSAN DITUBUHKAN, PENGURUSAN BANGUNAN AKAN DITANGGUNG OLEH PEMAJU DAN PEMBELI (BADAN PENGURUSAN BERSAMA)";

        this.syorpengarah = "PENGARAH TANAH DAN GALIAN MENGESYORKAN SUPAYA DILULUSKAN BANGUNAN" + " " + lcatbang
                + " ATAS " + lot + " "
                + nlot + " " + kbpm + " DAERAH "
                + daerah + " "
                + " DIKELASKAN SEBAGAI BANGUNAN KOS RENDAH DI BAWAH SEKSYEN 10B(2) AKTA HAKMILIK STRTAT 1985 (PINDAAN 2007 A1290)";


        this.keputusan = "DIBENTANGKAN UNTUK PERTIMBANGAN DAN KEPUTUSAN MAJLIS MESYUARAT KERAJAAN NEGERI SEMBILAN DARUL KHUSUS.";
        return new JSP("strata/kos_rendah/kertasPertimbangan_mmk_333.jsp").addParameter("tab", "true");
    }

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {

        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = permohonanDAO.findById(idPermohonan);
        LOG.info("---------idPermohonan---------::" + idPermohonan);
        pemohon = strService.findPemohonPihak(idPermohonan);



        permohonanKertas = strService.findKertasByKod(idPermohonan, "MMK");
        LOG.info("---------rehydrate-----permohonankertas--------------::" + permohonanKertas);
        if (permohonanKertas != null) {
            tajuk = permohonanKertas.getTajuk();
            LOG.info("TAJUK: " + tajuk);

        } else {
            LOG.info("CHECK: permohonankertas--------------::" + permohonanKertas);
            pemohon = strService.findById(idPermohonan);
            if (pemohon != null) {

                permohonanStrata = strService.findID(idPermohonan);
                mohonHakmilik = strService.findMohonHakmilik(idPermohonan);
                if ((mohonHakmilik != null) && (permohonanStrata != null)) {
                    tajuk = "PERMOHONAN SUPAYA BANGUNAN " + permohonanStrata.getLaporanKategoriBangunani() + " " + "ATAS" + " " + mohonHakmilik.getHakmilik().getLot().getNama() + " " + mohonHakmilik.getHakmilik().getNoLot() + " "
                            + mohonHakmilik.getHakmilik().getBandarPekanMukim().getNama() + " DAERAH " + mohonHakmilik.getHakmilik().getDaerah().getNama() + " DIKELASKAN SEBAGI BANGUNAN KOS RENDAH DI BAWAH SEKSYEN 10B(2) AKTA HAKMILIK STRATA 1982 (PINDAAN 2007 A 1290)";
                    LOG.info("TAJUK: " + tajuk);
                }




                tujuan = "TUJUAN KERTAS MESYUARAT INI ADALAH UNTUK MENDAPAT KELULUSAN MAJLIS MENYUARAT KERAJAAN SUPAYA BANGUNAN " + " "
                        + permohonanStrata.getLaporanKategoriBangunani().toUpperCase() + " ATAS " + mohonHakmilik.getHakmilik().getLot().getNama() + " "
                        + mohonHakmilik.getHakmilik().getNoLot() + " " + mohonHakmilik.getHakmilik().getBandarPekanMukim().getNama() + " " + "DAERAH" + " "
                        + mohonHakmilik.getHakmilik().getDaerah().getNama() + " "
                        + "DIKELASKAN SEBAGAI BANGUNAN KOS RENDAH DI BAWAH SEKSYEN 10B(2) AKTA HAKMILIK STRATA 1985(PINDAAN 2007 A 1290).";

                LOG.info("-------pemohon---------:" + pemohon);

//                if((pemohon.getPihak()!=null)){
//                 pihaknama = pemohon.getPihak().getNama();
//                }


                String pnama = " ";
                if (pemohon.getPihak() != null) {
                    pnama = pemohon.getPihak().getNama();

                }

                String lot = " ";
                if (mohonHakmilik.getHakmilik().getLot() != null) {
                    lot = mohonHakmilik.getHakmilik().getLot().getNama();

                }

                String nlot = " ";
                if (mohonHakmilik.getHakmilik().getNoLot() != null) {
                    nlot = mohonHakmilik.getHakmilik().getNoLot();
                }

                String kbpm = " ";
                if (mohonHakmilik.getHakmilik().getBandarPekanMukim() != null) {
                    kbpm = mohonHakmilik.getHakmilik().getBandarPekanMukim().getNama();
                }

                String daerah = " ";
                if (mohonHakmilik.getHakmilik().getDaerah() != null) {
                    daerah = mohonHakmilik.getHakmilik().getDaerah().getNama();
                }

                String date = " ";
                if (permohonan.getInfoAudit().getTarikhMasuk() != null) {
                    date = sdf.format(permohonan.getInfoAudit().getTarikhMasuk());
                }



                latarbelakang1 = pnama + " " + " (PEMAJU) SEBAGAI PEMEGANG SURAT KUASA WAKIL PEMILIK-PEMILIK "
                        + lot + " " + nlot + " " + kbpm + " " + "DAERAH" + " " + daerah + " " + "TELAH MENGEMUKAKAN PERMOHONAN "
                        + date + " " + "DI MANA BANGUNAN YANG DIDIRIKAN ADALAH BANGUNAN KOS RENDAH DAN MENGIKUT SEKSYEN 10B(2) AKTA HAKMILIK STRATA 1985 (PINDAAN 2007 A 1290) SUPAYA BANGUNAN " + " "
                        + permohonanStrata.getLaporanKategoriBangunani().toUpperCase() + " " + "YANG TELAH DIBINA DI ATAS" + " "
                        + mohonHakmilik.getHakmilik().getNoLot() + " " + "DIKELASKAN SEBAGAI BANGUNAN KOS RENDAH.";
//                   pemohon.getPihak().getNama() +


                String lcatbang = " ";
                if (permohonanStrata.getLaporanKategoriBangunani() != null) {
                    lcatbang = permohonanStrata.getLaporanKategoriBangunani().toUpperCase();
                }


                String mhdate = " ";
                if (permohonanStrata.getCfTarikhKeluar() != null) {
                    mhdate = sdf.format(permohonanStrata.getCfTarikhKeluar());
                }

                int bngnTotal = 0;
                pBangunanL = strService.getSenaraiMohonBangunan(idPermohonan);
                bngnTotal = pBangunanL.size();
                int tingkatTotal = 0;
                int petakTotal = 0;
                for (PermohonanBangunan pb : pBangunanL) {
                    tingkatTotal += pb.getBilanganTingkat();
                }
                bngnTotal = permohonanStrata.getLaporanBilBangunan();
                petakTotal = permohonanStrata.getLaporanBilPetak();
                latarbelakang2 = "BANGUNAN INI MERUPAKAN" + " " + lcatbang + " " + bngnTotal + " BLOK " + tingkatTotal + " TINGKAT YANG MENGANDUNGI "
                        + petakTotal + " PETAK. " + "BANGUNAN INI TELAH MEMPEROLEHI SIJIL PERAKUAN KELAYAKAN MENDUDUKI YANG DILULUSKAN PADA "
                        + mhdate + "." + " KESEMUA PETAK-PETAK TERSEBUT TELAH DIJUAL DAN DIDIAMI.";

                asaspermohon = "PERUNTUKAN SEKSYEN 10B(2) AKTA HAKMILIK STRATA 1985 (PINDAAN 2007 A 1290) MENGKEHENDAKI BANGUNAN TERSEBUT DIKELASKAN "
                        + "SEBAGAI BANGUNAN KOS RENDAH TERLEBIH DAHULU BAGI MEMBOLEHKAN PEMILIK BANGUNAN MENGEMUKAKAN "
                        + "PERMOHONAN PECAH BAHAGI BANGUNAN BERBILANG TINGKAT INI.";

                asaspermohon2 = "PERKARA-PERKARA UTAMA YANG PERLU DIURUSKAN BAGI BANGUNAN KOS RENDAH ADALAH SEPERTI BERIKUT:-" + "<br>"
                        + "  " + " 3.2.1:   MENGAWAL, MENGURUS, MENTADBIR DAN MENYELENGGARA HARTA BERSAMA SUPAYA BERADA DALAM KEADAAN YANG BAIK;" + "<br>"
                        + "  " + " 3.2.2:   MEMBAYAR CUKAI TANAH, MEMBELI POLISI INSURAN KEBAKARAN DAN MEMBAIK PULIH JIKA BERLAKU KEROSAKAN AKIBATNYA; dan " + "<br>"
                        + "  " + " 3.2.3:   MEMBOLEHKAN KUMPULAN WANG PENGURUSAN DITUBUHKAN, MEMBELI, MENYEWA ATAU MEMPEROLEHI HARTA ALIH SEBAGAI HARTA BERSAMA DAN LAIN-LAIN YANG MENJADI KEPENTINGAN BANGUNAN ATAU PENGGUNA.";
//                subAsasPermohon1 = "MENGAWAL, MENGURUS, MENTADBIR DAN MENYELENGGARA HARTA BERSAMA SUPAYA BERADA DALAM KEADAAN YANG BAIK;";
//                subAsasPermohon2 = "MEMBAYAR CUKAI TANAH, MEMBELI POLISI INSURAN KEBAKARAN DAN MEMBAIK PULIH JILA BERLAKU KEROSAKAN AKIBATNYA; dan";
//                subAsasPermohon3 = "MEMBOLEHKAN KUMPULAN WANG PENGURUSAN DITUBUHKAN, MEMBELI, MENYEWA ATAU MEMPEROLEHI HARTA ALIH SEBAGAI HARTA BERSAMA DAN LAIN-LAIN YANG MENJADI KEPENTINGAN BANGUNAN ATAU PENGGUNA.";
//
                asaspermohon3 = "OLEH KERANA PEMILIK-PEMILIK PETAK TERDIRI DARIPADA MEREKA YANG BERPENDAPATAN RENDAH, MAKA ADALAH SUKAR BAGI MEREKA MENTADBIR DAN MENGURUSKAN BANGUNAN TERSEBUT DI PERINGKAT AWAL DENGAN SEMPURNA.";

                ulasapengarah = "PENGARAH TANAH DAN GALIAN BERPENDAPAT PERMOHONAN INI WAJAR DILULUSKAN BERDASARKAN KEPADA ASAS-ASAS BERIKUT:-" + "<br>"
                        + "  " + " 4.1.1:   HARGA JUALAN PETAK ADALAH RM " + money.format(permohonanStrata.getHargaPetak()) + " " + "SEUNIT, IAITU HARGA YANG DIKELASKAN SEBAGAI BANGUNAN KOS RENDAH;" + "<br>"
                        + "  " + " 4.1.2:   PEMBELI-PEMBELI ADALAH TERDIRI DARIPADA MEREKA YANG BERPENDAPATAN RENDAH;" + " " + "<br>"
                        + "  " + " 4.1.3:   PENGKELASAN JENIS BANGUNAN INI AKAN MEMBANTU PEMBELI DARI SEGI ASPEK PENGURUSAN BANGUNAN SEMENTARA MENUNGGU PENUBUHAN PERBADANAN PENGURUSAN KERANA TANGGUNGJAWAB TERSEBUT AKAN DITANGGUNG OLEH PEMAJU DAN PEMBELI(BADAN PENGURSAN BERSAMA).<br>";
//                subUlasanPengarah1 = "HARGA JUALAN PETAK ADALAH RM " + money.format(permohonanStrata.getHargaPetak()) + " " + "SEUNIT, IAITU HARGA YANG DIKELASKAN SEBAGAI BANGUNAN KOS RENDAH;";
//                subUlasanPengarah2 = "PEMBELI-PEMBELI ADALAH TERDIRI DARIPADA MEREKA YANG BERPENDAPATAN RENDAH;";
//                subUlasanPengarah3 = "PENGKELASAN JENIS BANGUNAN INI AKAN MEMBANTU PEMBELI DARI SEGI ASPEK PENGURUSAN BANGUNAN SEMENTARA MENUNGGU PENUBUHAN PERBADANAN PENGURUSAN KERANA TANGGUNGJAWAB TERSEBUT AKAN DITANGGUNG OLEH PEMAJU DAN PEMBELI(BADAN PENGURSAN BERSAMA).";
                syorpengarah = "PENGARAH TANAH DAN GALIAN MENGESYORKAN SUPAYA DILULUSKAN BANGUNAN " + " " + lcatbang
                        + " ATAS " + lot + " "
                        + nlot + " " + kbpm + "DAERAH" + " "
                        + daerah + " "
                        + "DIKELASKAN SEBAGAI BANGUNAN KOS RENDAH DI BAWAH SEKSYEN 10B(2) AKTA HAKMILIK STRATA 1985 (PINDAAN 2007 A 1290).";


                keputusan = "BENTANGKAN UNTUK PERTIMBANGAN DAN KEPUTUSAN MAJLIS MESYUARAT KERAJAAN NEGERI SEMBILAN DARUL KHUSUS.";
            }

        }






        // Added
        PermohonanKertas permohonanKertas = new PermohonanKertas();
        permohonanKertas = strService.findKertasByKod(idPermohonan, "MMK");
        if (permohonanKertas != null) {
            senaraiLaporanKandungan1 = strService.findByIdLapor(permohonanKertas.getIdKertas(), 1);
            if (senaraiLaporanKandungan1.size() > 0) {
            }
            senaraiLaporanKandungan2 = strService.findByIdLapor(permohonanKertas.getIdKertas(), 2);
            if (senaraiLaporanKandungan2.size() > 0) {
            }
            senaraiLaporanKandungan3 = strService.findByIdLapor(permohonanKertas.getIdKertas(), 3);
            if (senaraiLaporanKandungan3.size() > 0) {
            }
            senaraiLaporanKandungan4 = strService.findByIdLapor(permohonanKertas.getIdKertas(), 4);
            if (senaraiLaporanKandungan4.size() > 0) {
                emptySenarai = false;
            }
            senaraiLaporanKandungan5 = strService.findByIdLapor(permohonanKertas.getIdKertas(), 5);
            if (senaraiLaporanKandungan5.size() > 0) {
                emptySenarai5 = false;
            }
            senaraiLaporanKandungan6 = strService.findByIdLapor(permohonanKertas.getIdKertas(), 6);
            if (senaraiLaporanKandungan6.size() > 0) {
                emptySenarai6 = false;
            }
        }

    }

    public Resolution simpan() {

        System.out.println("------------Simpan started-----------::");
        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        System.out.println("------------idPermohonan-----------::" + idPermohonan);
        permohonan = permohonanDAO.findById(idPermohonan);
        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        InfoAudit infoAudit = new InfoAudit();
        kd = kodDokumenDAO.findById("MMK");
        PermohonanKertas permohonanKertas = new PermohonanKertas();

        permohonanKertas = strService.findKertasByKod(idPermohonan, "MMK");
        LOG.info("-------Simpan-------permohonankertas--------------::");



        ArrayList listUlasan = new ArrayList();
        ArrayList<String> listSubtajuk = new ArrayList<String>();
        ArrayList<String> billNo = new ArrayList<String>();

        listUlasan.add(tujuan.toUpperCase());
        listUlasan.add(latarbelakang1.toUpperCase());
        listUlasan.add(latarbelakang2.toUpperCase());
        listUlasan.add(asaspermohon.toUpperCase());
        listUlasan.add(asaspermohon2.toUpperCase());
        listUlasan.add(asaspermohon3.toUpperCase());
        listUlasan.add(ulasapengarah.toUpperCase());
        listUlasan.add(syorpengarah.toUpperCase());
        listUlasan.add(keputusan.toUpperCase());


        listSubtajuk.add("1.1");
        listSubtajuk.add("2.1");
        listSubtajuk.add("2.2");
        listSubtajuk.add("3.1");
        listSubtajuk.add("3.2");
        listSubtajuk.add("3.3");
        listSubtajuk.add("4.1");
        listSubtajuk.add("5.1");
        listSubtajuk.add("6.1");


        billNo.add("1");
        billNo.add("2");
        billNo.add("2");
        billNo.add("3");
        billNo.add("3");
        billNo.add("3");
        billNo.add("4");
        billNo.add("5");
        billNo.add("6");

        if (permohonanKertas == null) {
            permohonanKertas = new PermohonanKertas();
            infoAudit.setTarikhMasuk(new java.util.Date());
            infoAudit.setDimasukOleh(peng);
            permohonanKertas.setInfoAudit(infoAudit);
            permohonanKertas.setPermohonan(permohonan);
            permohonanKertas.setCawangan(permohonan.getCawangan());
            permohonanKertas.setTajuk(tajuk);
            permohonanKertas.setKodDokumen(kd);
            strService.simpanPermohonanKertas(permohonanKertas);

//            LOG.info("------listUlasan.size()-------:" + listUlasan.size());
//            LOG.info("------listSubtajuk.size()-------:" + listSubtajuk.size());
//            LOG.info("------billNo.size()-------:" + billNo.size());

            for (int i = 0; i < listUlasan.size(); i++) {
                String ulasan = (String) listUlasan.get(i);
                String billNo1 = billNo.get(i);
                String subTajuk = listSubtajuk.get(i);
                LOG.info("------i-------:" + billNo.get(i));
                LOG.info("------billNo-------:" + billNo1);
                LOG.info("------SubTajuk-------:" + subTajuk);
                LOG.info("------KAND-------:" + ulasan);

                PermohonanKertasKandungan kertasKdgn = new PermohonanKertasKandungan();
                kertasKdgn.setKertas(permohonanKertas);
                kertasKdgn.setBil(Integer.parseInt(billNo1));
                kertasKdgn.setSubtajuk(subTajuk);
                kertasKdgn.setInfoAudit(infoAudit);
                kertasKdgn.setKandungan(ulasan.toUpperCase());
                kertasKdgn.setCawangan(permohonan.getCawangan());
                strService.simpanPermohonanKertasKandungan(kertasKdgn);
                LOG.info("ID kand : " + kertasKdgn.getIdKandungan());
            }
        } else {

            infoAudit = permohonanKertas.getInfoAudit();
            infoAudit.setDiKemaskiniOleh(peng);
            infoAudit.setTarikhKemaskini(new java.util.Date());
            strService.simpanPermohonanKertas(permohonanKertas);

            if (!permohonanKertas.getSenaraiKandungan().isEmpty()) {

                for (int j = 0; j < permohonanKertas.getSenaraiKandungan().size(); j++) {
                    PermohonanKertasKandungan kertasKdgn = new PermohonanKertasKandungan();
                    kertasKdgn = permohonanKertas.getSenaraiKandungan().get(j);

                    if (kertasKdgn.getBil() == 0) {
                        kertasKdgn.setKandungan(tajuk);
                    }

                    if (kertasKdgn.getSubtajuk().equals("1.1")) {
                        kertasKdgn.setKandungan(tujuan);
                    }
                    if (kertasKdgn.getSubtajuk().equals("2.1")) {
                        kertasKdgn.setKandungan(latarbelakang1);
                    }
                    if (kertasKdgn.getSubtajuk().equals("2.2")) {
                        kertasKdgn.setKandungan(latarbelakang2);
                    }
                    if (kertasKdgn.getSubtajuk().equals("3.1")) {
                        kertasKdgn.setKandungan(asaspermohon);
                    }
                    if (kertasKdgn.getSubtajuk().equals("3.2")) {
                        kertasKdgn.setKandungan(asaspermohon2);
                    }

                    if (kertasKdgn.getSubtajuk().equals("3.3")) {
                        kertasKdgn.setKandungan(asaspermohon3);
                    }

                    if (kertasKdgn.getSubtajuk().equals("4.1")) {
                        kertasKdgn.setKandungan(ulasapengarah);
                    }

                    if (kertasKdgn.getSubtajuk().equals("5.1")) {
                        kertasKdgn.setKandungan(syorpengarah);
                    }

                    if (kertasKdgn.getSubtajuk().equals("6.1")) {
                        kertasKdgn.setKandungan(keputusan);
                    }

                    kertasKdgn.setInfoAudit(infoAudit);
                    strService.simpanPermohonanKertasKandungan(kertasKdgn);
                }
            }


        }

        senaraiLaporanKandungan1 = strService.findByIdLapor(permohonanKertas.getIdKertas(), 1);

        int kira = Integer.parseInt(getContext().getRequest().getParameter("rowCount1"));
        int k = 2;
        for (int i = 1; i <= kira; i++) {
            PermohonanKertasKandungan permohonanKertasKandungan1 = new PermohonanKertasKandungan();
            if (senaraiLaporanKandungan1.size() != 0 && i <= senaraiLaporanKandungan1.size()) {
                Long id = senaraiLaporanKandungan1.get(i - 1).getIdKandungan();
                if (id != null) {
                    permohonanKertasKandungan1 = strService.findkandunganByIdKandungan(id);
                }
            } else {
                permohonanKertasKandungan1 = new PermohonanKertasKandungan();
            }
            permohonanKertasKandungan1.setKertas(permohonanKertas);
            permohonanKertasKandungan1.setBil(1);
            String kandungan = getContext().getRequest().getParameter("kandungan1" + i);
            permohonanKertasKandungan1.setKandungan(kandungan);
            permohonanKertasKandungan1.setCawangan(permohonan.getCawangan());
            permohonanKertasKandungan1.setSubtajuk("1." + k);
            InfoAudit iaP = new InfoAudit();
            iaP.setTarikhMasuk(new Date());
            iaP.setDimasukOleh(peng);
            permohonanKertasKandungan1.setInfoAudit(iaP);
            strService.simpanPermohonanKertasKandungan(permohonanKertasKandungan1);
            k++;
        }


        senaraiLaporanKandungan2 = strService.findByIdLapor(permohonanKertas.getIdKertas(), 2);

        kira = Integer.parseInt(getContext().getRequest().getParameter("rowCount2"));
        k = 3;
        for (int i = 1; i <= kira; i++) {
            PermohonanKertasKandungan permohonanKertasKandungan1 = new PermohonanKertasKandungan();
            if (senaraiLaporanKandungan2.size() != 0 && i <= senaraiLaporanKandungan2.size()) {
                Long id = senaraiLaporanKandungan2.get(i - 1).getIdKandungan();
                if (id != null) {
                    permohonanKertasKandungan1 = strService.findkandunganByIdKandungan(id);
                }
            } else {
                permohonanKertasKandungan1 = new PermohonanKertasKandungan();
            }
            permohonanKertasKandungan1.setKertas(permohonanKertas);
            permohonanKertasKandungan1.setBil(2);
            String kandungan = getContext().getRequest().getParameter("kandungan2" + i);
            permohonanKertasKandungan1.setKandungan(kandungan);
            permohonanKertasKandungan1.setCawangan(permohonan.getCawangan());
            permohonanKertasKandungan1.setSubtajuk("2." + k);
            InfoAudit iaP = new InfoAudit();
            iaP.setTarikhMasuk(new Date());
            iaP.setDimasukOleh(peng);
            permohonanKertasKandungan1.setInfoAudit(iaP);
            strService.simpanPermohonanKertasKandungan(permohonanKertasKandungan1);
            k++;
        }

        senaraiLaporanKandungan3 = strService.findByIdLapor(permohonanKertas.getIdKertas(), 3);

        kira = Integer.parseInt(getContext().getRequest().getParameter("rowCount3"));
        k = 4;
        for (int i = 1; i <= kira; i++) {
            PermohonanKertasKandungan permohonanKertasKandungan1 = new PermohonanKertasKandungan();
            if (senaraiLaporanKandungan3.size() != 0 && i <= senaraiLaporanKandungan3.size()) {
                Long id = senaraiLaporanKandungan3.get(i - 1).getIdKandungan();
                if (id != null) {
                    permohonanKertasKandungan1 = strService.findkandunganByIdKandungan(id);
                }
            } else {
                permohonanKertasKandungan1 = new PermohonanKertasKandungan();
            }
            permohonanKertasKandungan1.setKertas(permohonanKertas);
            permohonanKertasKandungan1.setBil(3);
            String kandungan = getContext().getRequest().getParameter("kandungan3" + i);
            permohonanKertasKandungan1.setKandungan(kandungan);
            permohonanKertasKandungan1.setCawangan(permohonan.getCawangan());
            permohonanKertasKandungan1.setSubtajuk("3." + k);
            InfoAudit iaP = new InfoAudit();
            iaP.setTarikhMasuk(new Date());
            iaP.setDimasukOleh(peng);
            permohonanKertasKandungan1.setInfoAudit(iaP);
            strService.simpanPermohonanKertasKandungan(permohonanKertasKandungan1);
            k++;
        }

        senaraiLaporanKandungan4 = strService.findByIdLapor(permohonanKertas.getIdKertas(), 4);

        kira = Integer.parseInt(getContext().getRequest().getParameter("rowCount4"));
        k = 2;
        for (int i = 1; i <= kira; i++) {
            PermohonanKertasKandungan permohonanKertasKandungan1 = new PermohonanKertasKandungan();
            if (senaraiLaporanKandungan4.size() != 0 && i <= senaraiLaporanKandungan4.size()) {
                Long id = senaraiLaporanKandungan4.get(i - 1).getIdKandungan();
                if (id != null) {
                    permohonanKertasKandungan1 = strService.findkandunganByIdKandungan(id);
                }
            } else {
                permohonanKertasKandungan1 = new PermohonanKertasKandungan();
            }
            permohonanKertasKandungan1.setKertas(permohonanKertas);
            permohonanKertasKandungan1.setBil(4);
            String kandungan = getContext().getRequest().getParameter("kandungan4" + i);
            permohonanKertasKandungan1.setKandungan(kandungan);
            permohonanKertasKandungan1.setCawangan(permohonan.getCawangan());
            permohonanKertasKandungan1.setSubtajuk("4." + k);
            InfoAudit iaP = new InfoAudit();
            iaP.setTarikhMasuk(new Date());
            iaP.setDimasukOleh(peng);
            permohonanKertasKandungan1.setInfoAudit(iaP);
            strService.simpanPermohonanKertasKandungan(permohonanKertasKandungan1);
            k++;
        }

        senaraiLaporanKandungan5 = strService.findByIdLapor(permohonanKertas.getIdKertas(), 5);

        kira = Integer.parseInt(getContext().getRequest().getParameter("rowCount5"));
        k = 2;
        for (int i = 1; i <= kira; i++) {
            PermohonanKertasKandungan permohonanKertasKandungan1 = new PermohonanKertasKandungan();
            if (senaraiLaporanKandungan5.size() != 0 && i <= senaraiLaporanKandungan5.size()) {
                Long id = senaraiLaporanKandungan5.get(i - 1).getIdKandungan();
                if (id != null) {
                    permohonanKertasKandungan1 = strService.findkandunganByIdKandungan(id);
                }
            } else {
                permohonanKertasKandungan1 = new PermohonanKertasKandungan();
            }
            permohonanKertasKandungan1.setKertas(permohonanKertas);
            permohonanKertasKandungan1.setBil(5);
            String kandungan = getContext().getRequest().getParameter("kandungan5" + i);
            permohonanKertasKandungan1.setKandungan(kandungan);
            permohonanKertasKandungan1.setCawangan(permohonan.getCawangan());
            permohonanKertasKandungan1.setSubtajuk("5." + k);
            InfoAudit iaP = new InfoAudit();
            iaP.setTarikhMasuk(new Date());
            iaP.setDimasukOleh(peng);
            permohonanKertasKandungan1.setInfoAudit(iaP);
            strService.simpanPermohonanKertasKandungan(permohonanKertasKandungan1);
            k++;
        }

        senaraiLaporanKandungan6 = strService.findByIdLapor(permohonanKertas.getIdKertas(), 6);

        kira = Integer.parseInt(getContext().getRequest().getParameter("rowCount6"));
        k = 2;
        for (int i = 1; i <= kira; i++) {
            PermohonanKertasKandungan permohonanKertasKandungan1 = new PermohonanKertasKandungan();
            if (senaraiLaporanKandungan6.size() != 0 && i <= senaraiLaporanKandungan6.size()) {
                Long id = senaraiLaporanKandungan6.get(i - 1).getIdKandungan();
                if (id != null) {
                    permohonanKertasKandungan1 = strService.findkandunganByIdKandungan(id);
                }
            } else {
                permohonanKertasKandungan1 = new PermohonanKertasKandungan();
            }
            permohonanKertasKandungan1.setKertas(permohonanKertas);
            permohonanKertasKandungan1.setBil(6);
            String kandungan = getContext().getRequest().getParameter("kandungan6" + i);
            permohonanKertasKandungan1.setKandungan(kandungan);
            permohonanKertasKandungan1.setCawangan(permohonan.getCawangan());
            permohonanKertasKandungan1.setSubtajuk("6." + k);
            InfoAudit iaP = new InfoAudit();
            iaP.setTarikhMasuk(new Date());
            iaP.setDimasukOleh(peng);
            permohonanKertasKandungan1.setInfoAudit(iaP);
            strService.simpanPermohonanKertasKandungan(permohonanKertasKandungan1);
            k++;
        }



        addSimpleMessage("Maklumat telah berjaya disimpan.");
        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        return new JSP("strata/kos_rendah/kertasPertimbangan_mmk111.jsp").addParameter("tab", "true");
    }

    public Resolution deleteSingle() {
        InfoAudit ia = new InfoAudit();
        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);

        idKandungan = getContext().getRequest().getParameter("idKandungan");
        PermohonanKertasKandungan permohonanKertasKandungan1 = new PermohonanKertasKandungan();
        try {
            permohonanKertasKandungan1 = strService.findkandunganByIdKandungan(Long.parseLong(idKandungan));
        } catch (Exception e) {
            LOG.debug("Hapus empty record");

        }
        if (permohonanKertasKandungan1 != null) {

//            ia = peng.getInfoAudit();
//            ia.setDiKemaskiniOleh(peng);
//            ia.setTarikhKemaskini(new java.util.Date());
//            permohonanKertasKandungan1.setInfoAudit(ia);
//            permohonanKertasKandungan1.setCawangan(permohonan.getCawangan());
            strService.deleteKertasKandungan(permohonanKertasKandungan1);
        }

        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        addSimpleMessage("Maklumat telah berjaya dihapus.");
        return new JSP("strata/kos_rendah/kertasPertimbangan_mmk111.jsp").addParameter("tab", "true");
    }

    public Resolution simpan111() {

        System.out.println("------------Simpan started-----------::");
        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        System.out.println("------------idPermohonan-----------::" + idPermohonan);
        permohonan = permohonanDAO.findById(idPermohonan);
        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        InfoAudit infoAudit = new InfoAudit();
        kd = kodDokumenDAO.findById("MMK");
        PermohonanKertas permohonanKertas = new PermohonanKertas();

        permohonanKertas = strService.findKertasByKod(idPermohonan, "MMK");
        LOG.info("-------Simpan-------permohonankertas--------------::");



        ArrayList listUlasan = new ArrayList();
        ArrayList<String> listSubtajuk = new ArrayList<String>();
        ArrayList<String> billNo = new ArrayList<String>();

        String asaspermohonan = "PERKARA-PERKARA UTAMA YANG PERLU DIURUSKAN BAGI BANGUNAN KOS RENDAH ADALAH SEPERTI BERIKUT:-" + "\n"
                + "\t" + " 3.2.1: MENGAWAL, MENGURUS, MENTADBIR DAN MENYELENGGARA HARTA BERSAMA SUPAYA \n\t\t BERADA DALAM KEADAAN YANG BAIK;" + "\n"
                + "\t" + " 3.2.2: MEMBAYAR CUKAI TANAH, MEMBELI POLISI INSURAN KEBAKARAN DAN MEMBAIK PULIH \n\t\t JIKA BERLAKU KEROSAKAN  AKIBATNYA; DAN " + "\n"
                + "\t" + " 3.2.3: MEMBOLEHKAN KUMPULAN WANG PENGURUSAN DITUBUHKAN, MEMBELI, MENYEWA ATAU \n\t\t MEMPEROLEHI HARTA ALIH  SEBAGAI "
                + "HARTA BERSAMA DAN LAIN-LAIN YANG \n\t\t MENJADI KEPENTINGAN BANGUNAN ATAU PENGGUNA.";
        listUlasan.add(tujuan);
        listUlasan.add(latarbelakang1);
        listUlasan.add(latarbelakang2);
        listUlasan.add(asaspermohon);
        listUlasan.add(asaspermohonan);//listUlasan.add(asaspermohon2);
        listUlasan.add(asaspermohon3);
//        listUlasan.add(ulasapengarah);
//        listUlasan.add(syorpengarah);
//        listUlasan.add(keputusan);

        listSubtajuk.add("1.1");
        listSubtajuk.add("2.1");
        listSubtajuk.add("2.2");
        listSubtajuk.add("3.1");
        listSubtajuk.add("3.2");
        listSubtajuk.add("3.3");
//        listSubtajuk.add("4.1");
//        listSubtajuk.add("5.1");
//        listSubtajuk.add("6.1");

        billNo.add("1");
        billNo.add("2");
        billNo.add("2");
        billNo.add("3");
        billNo.add("3");
        billNo.add("3");
//        billNo.add("4");
//        billNo.add("5");
//        billNo.add("6");

        if (permohonanKertas == null) {
            permohonanKertas = new PermohonanKertas();
            LOG.info("------listUlasan.size()-------:" + listUlasan.size());
            LOG.info("------listSubtajuk.size()-------:" + listSubtajuk.size());
            LOG.info("------billNo.size()-------:" + billNo.size());
            infoAudit.setTarikhMasuk(new java.util.Date());
            infoAudit.setDimasukOleh(peng);
            permohonanKertas.setInfoAudit(infoAudit);
            permohonanKertas.setPermohonan(permohonan);
            permohonanKertas.setCawangan(permohonan.getCawangan());
            permohonanKertas.setTajuk(tajuk);
            permohonanKertas.setKodDokumen(kd);
            permohonanKertas = strService.simpanPermohonanKertasObject(permohonanKertas);
            for (int i = 0; i < listUlasan.size(); i++) {
                String ulasan = (String) listUlasan.get(i);
                String billNo1 = billNo.get(i);
                String subTajuk = listSubtajuk.get(i);
                LOG.info("------i-------:" + billNo.get(i));
                LOG.info("------billNo-------:" + billNo1);
                LOG.info("------SubTajuk-------:" + subTajuk);
                LOG.info("------KAND-------:" + ulasan);
                PermohonanKertasKandungan kertasKdgn = new PermohonanKertasKandungan();
                kertasKdgn.setKertas(permohonanKertas);
                kertasKdgn.setBil(Integer.parseInt(billNo1));
                kertasKdgn.setSubtajuk(subTajuk);
                kertasKdgn.setInfoAudit(infoAudit);
                kertasKdgn.setKandungan(ulasan);
                kertasKdgn.setCawangan(permohonan.getCawangan());
                kertasKdgn = strService.simpanPermohonanKertasKandungan(kertasKdgn);
                LOG.info("===SIMPAN EMPTY permohonanKertas BERJAYA " + kertasKdgn.getIdKandungan());

            }
        }

        senaraiLaporanKandungan1 = strService.findByIdLapor(permohonanKertas.getIdKertas(), 1);

        int kira = Integer.parseInt(getContext().getRequest().getParameter("rowCount1"));
        int k = 2;
        for (int i = 1; i <= kira; i++) {
            PermohonanKertasKandungan permohonanKertasKandungan1 = new PermohonanKertasKandungan();
            if (!senaraiLaporanKandungan1.isEmpty() && i <= senaraiLaporanKandungan1.size()) {
                Long id = senaraiLaporanKandungan1.get(i - 1).getIdKandungan();
                if (id != null) {
                    LOG.info("Find ID :" + id);
                    permohonanKertasKandungan1 = strService.findkandunganByIdKandungan(id);
                }
            } else {
                permohonanKertasKandungan1 = new PermohonanKertasKandungan();
            }
            permohonanKertasKandungan1.setKertas(permohonanKertas);
            permohonanKertasKandungan1.setBil(1);
            String kandungan = getContext().getRequest().getParameter("kandungan1" + i);
            permohonanKertasKandungan1.setKandungan(kandungan);
            permohonanKertasKandungan1.setCawangan(permohonan.getCawangan());
            permohonanKertasKandungan1.setSubtajuk("1." + k);
            InfoAudit iaP = new InfoAudit();
            iaP.setTarikhMasuk(new Date());
            iaP.setDimasukOleh(peng);
            permohonanKertasKandungan1.setInfoAudit(iaP);
            strService.simpanPermohonanKertasKandungan(permohonanKertasKandungan1);
            k++;
        }


        senaraiLaporanKandungan2 = strService.findByIdLapor(permohonanKertas.getIdKertas(), 2);

        kira = Integer.parseInt(getContext().getRequest().getParameter("rowCount2"));
        k = 3;
        for (int i = 1; i <= kira; i++) {
            PermohonanKertasKandungan permohonanKertasKandungan1 = new PermohonanKertasKandungan();
            if (!senaraiLaporanKandungan2.isEmpty() && i <= senaraiLaporanKandungan2.size()) {
                Long id = senaraiLaporanKandungan2.get(i - 1).getIdKandungan();
                if (id != null) {
                    LOG.info("Find ID :" + id);
                    permohonanKertasKandungan1 = strService.findkandunganByIdKandungan(id);
                }
            } else {
                permohonanKertasKandungan1 = new PermohonanKertasKandungan();
            }
            permohonanKertasKandungan1.setKertas(permohonanKertas);
            permohonanKertasKandungan1.setBil(2);
            String kandungan = getContext().getRequest().getParameter("kandungan2" + i);
            permohonanKertasKandungan1.setKandungan(kandungan);
            permohonanKertasKandungan1.setCawangan(permohonan.getCawangan());
            permohonanKertasKandungan1.setSubtajuk("2." + k);
            InfoAudit iaP = new InfoAudit();
            iaP.setTarikhMasuk(new Date());
            iaP.setDimasukOleh(peng);
            permohonanKertasKandungan1.setInfoAudit(iaP);
            strService.simpanPermohonanKertasKandungan(permohonanKertasKandungan1);
            k++;
        }

        senaraiLaporanKandungan3 = strService.findByIdLapor(permohonanKertas.getIdKertas(), 3);

        kira = Integer.parseInt(getContext().getRequest().getParameter("rowCount3"));
        k = 4;
        for (int i = 1; i <= kira; i++) {
            PermohonanKertasKandungan permohonanKertasKandungan1 = new PermohonanKertasKandungan();
            if (!senaraiLaporanKandungan3.isEmpty() && i <= senaraiLaporanKandungan3.size()) {
                Long id = senaraiLaporanKandungan3.get(i - 1).getIdKandungan();
                if (id != null) {
                    LOG.info("Find ID :" + id);
                    permohonanKertasKandungan1 = strService.findkandunganByIdKandungan(id);
                }
            } else {
                permohonanKertasKandungan1 = new PermohonanKertasKandungan();
            }
            permohonanKertasKandungan1.setKertas(permohonanKertas);
            permohonanKertasKandungan1.setBil(3);
            String kandungan = getContext().getRequest().getParameter("kandungan3" + i);
            permohonanKertasKandungan1.setKandungan(kandungan);
            permohonanKertasKandungan1.setCawangan(permohonan.getCawangan());
            permohonanKertasKandungan1.setSubtajuk("3." + k);
            InfoAudit iaP = new InfoAudit();
            iaP.setTarikhMasuk(new Date());
            iaP.setDimasukOleh(peng);
            permohonanKertasKandungan1.setInfoAudit(iaP);
            strService.simpanPermohonanKertasKandungan(permohonanKertasKandungan1);
            k++;
        }
        rehydrate();
        addSimpleMessage("Maklumat telah berjaya disimpan.");
        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        return new JSP("strata/kos_rendah/kertasPertimbangan_mmk_111.jsp").addParameter("tab", "true");
    }

    public Resolution simpan333() {

        System.out.println("------------Simpan started-----------::");
        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        System.out.println("------------idPermohonan-----------::" + idPermohonan);
        permohonan = permohonanDAO.findById(idPermohonan);
        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        InfoAudit infoAudit = new InfoAudit();
        kd = kodDokumenDAO.findById("MMK");
        PermohonanKertas permohonanKertas = strService.findKertasByKod(idPermohonan, "MMK");

//        permohonanKertas = strService.findKertasByKod(idPermohonan, "MMK");
        LOG.info("-------Simpan-------permohonankertas--------------::");
        permohonan = permohonanDAO.findById(idPermohonan);
        permohonanStrata = strService.findID(idPermohonan);
        mohonHakmilik = strService.findMohonHakmilik(idPermohonan);
        getContext().getRequest().setAttribute("display", Boolean.TRUE);
        String lot = " ";
        if (mohonHakmilik.getHakmilik().getLot() != null) {
            lot = mohonHakmilik.getHakmilik().getLot().getNama();

        }

        String nlot = " ";
        if (mohonHakmilik.getHakmilik().getNoLot() != null) {
            nlot = mohonHakmilik.getHakmilik().getNoLot();
        }

        String kbpm = " ";
        if (mohonHakmilik.getHakmilik().getBandarPekanMukim() != null) {
            kbpm = mohonHakmilik.getHakmilik().getBandarPekanMukim().getNama();
        }

        String daerah = " ";
        if (mohonHakmilik.getHakmilik().getDaerah() != null) {
            daerah = mohonHakmilik.getHakmilik().getDaerah().getNama();
        }
        String date = " ";
        if (permohonanStrata.getCfTarikhKeluar() != null) {

            date = sdf.format(permohonanStrata.getCfTarikhKeluar());
        }

        int bngnTotal = 0;
        pBangunanL = strService.getSenaraiMohonBangunan(idPermohonan);
        bngnTotal = pBangunanL.size();
        int tingkatTotal = 0;
        int petakTotal = 0;
        for (PermohonanBangunan pb : pBangunanL) {
            bangunanTingkatList = strService.findByIDBangunan(pb.getIdBangunan());
            tingkatTotal += bangunanTingkatList.size();
            for (BangunanTingkat bt : bangunanTingkatList) {
                bangunanPetakList = strService.getSenaraiPetak(bt.getIdTingkat());
                petakTotal += bangunanPetakList.size();
            }
        }
        String lcatbang = " ";
        if (permohonanStrata.getLaporanKategoriBangunani() != null) {
            lcatbang = permohonanStrata.getLaporanKategoriBangunani();
        }


        String mhdate = " ";
        if (permohonanStrata.getCfTarikhKeluar() != null) {
            date = sdf.format(permohonanStrata.getCfTarikhKeluar());
        }

        ulasapengarah = "Pengarah Tanah dan Galian berpendapat permohonan ini wajar diluluskan berdasarkan kepada asas-asas berikut" + "\n"
                + "\t" + " 4.1.1: Harga jualan petak adalah RM " + money.format(permohonanStrata.getHargaPetak()) + " " + "seunit, iaitu harga yang dikelaskan sebagai Bangunan Kos Rendah;" + "\n"
                + "\t" + " 4.1.2: Pembeli-pembeli adalah terdiri daripada mereka yang berpendapatan rendah;" + "\n"
                + "\t" + " 4.1.3: Pengkelasan jenis bangunan ini akan membantu pembili dari segi aspek pengurusan bangunan.\n"
                + " Ini adalah kerana sebelum Perbadanan Pengurusan ditubuhkan, Pengurusan Bangunan akan ditanggung oleh pemaju dan pembeli (Badan Pengurusan Bersama).";

        syorpengarah = "Pengarah Tanah dan Galian mengesyorkan supaya DILULUSKAN bangunan " + " " + lcatbang
                + " atas " + lot + " "
                + nlot + " " + kbpm + "daerah" + " "
                + daerah + " "
                + " dikelaskan sebagai Bangunan Kos Rendah di bawah Seksyen 10B(2) akta Hakmilik Strata 1985 (Pindaan 2007 A1290)";


        keputusan = "Dibentangkan untuk pertimbangan dan keputusan Majlis Mesyuarat Kerajaan Negeri Sembilan Darul Khusus.";

        ArrayList listUlasan = new ArrayList();
        ArrayList<String> listSubtajuk = new ArrayList<String>();
        ArrayList<String> billNo = new ArrayList<String>();

//        listUlasan.add(tujuan);
//        listUlasan.add(latarbelakang1);
//        listUlasan.add(latarbelakang2);
//        listUlasan.add(asaspermohon);
//        listUlasan.add(asaspermohon2);
//        listUlasan.add(asaspermohon3);
        listUlasan.add(ulasapengarah.toUpperCase());
        listUlasan.add(syorpengarah.toUpperCase());
        listUlasan.add(keputusan.toUpperCase());

//        listSubtajuk.add("1.1");
//        listSubtajuk.add("2.1");
//        listSubtajuk.add("2.2");
//        listSubtajuk.add("3.1");
//        listSubtajuk.add("3.2");
//        listSubtajuk.add("3.3");
        listSubtajuk.add("4.1");
        listSubtajuk.add("5.1");
        listSubtajuk.add("6.1");

//        billNo.add("1");
//        billNo.add("2");
//        billNo.add("2");
//        billNo.add("3");
//        billNo.add("3");
//        billNo.add("3");
        billNo.add("4");
        billNo.add("5");
        billNo.add("6");



        LOG.info("------listUlasan.size()-------:" + listUlasan.size());
        LOG.info("------listSubtajuk.size()-------:" + listSubtajuk.size());
        LOG.info("------billNo.size()-------:" + billNo.size());

        for (int i = 0; i < listUlasan.size(); i++) {
            String ulasan = (String) listUlasan.get(i);
            String billNo1 = billNo.get(i);
            String subTajuk = listSubtajuk.get(i);
            LOG.info("------i-------:" + billNo.get(i));
            LOG.info("------billNo-------:" + billNo1);
            LOG.info("------SubTajuk-------:" + subTajuk);
            LOG.info("------KAND-------:" + ulasan);
            infoAudit.setTarikhMasuk(new java.util.Date());
            infoAudit.setDimasukOleh(peng);

            PermohonanKertasKandungan kertasKdgn = new PermohonanKertasKandungan();
            kertasKdgn.setKertas(permohonanKertas);
            kertasKdgn.setBil(Integer.parseInt(billNo1));
            kertasKdgn.setSubtajuk(subTajuk);
            kertasKdgn.setInfoAudit(infoAudit);
            kertasKdgn.setKandungan(ulasan);
            kertasKdgn.setCawangan(permohonan.getCawangan());
            strService.simpanPermohonanKertasKandungan(kertasKdgn);
        }


        senaraiLaporanKandungan4 = strService.findByIdLapor(permohonanKertas.getIdKertas(), 4);

        int kira = Integer.parseInt(getContext().getRequest().getParameter("rowCount4"));
        int k = 2;
        for (int i = 1; i <= kira; i++) {
            PermohonanKertasKandungan permohonanKertasKandungan1 = new PermohonanKertasKandungan();
            if (!senaraiLaporanKandungan4.isEmpty() && i <= senaraiLaporanKandungan4.size()) {
                Long id = senaraiLaporanKandungan4.get(i - 1).getIdKandungan();
                if (id != null) {
                    permohonanKertasKandungan1 = strService.findkandunganByIdKandungan(id);
                }
            } else {
                permohonanKertasKandungan1 = new PermohonanKertasKandungan();
            }
            permohonanKertasKandungan1.setKertas(permohonanKertas);
            permohonanKertasKandungan1.setBil(4);
            String kandungan = getContext().getRequest().getParameter("kandungan4" + i);
            permohonanKertasKandungan1.setKandungan(kandungan);
            permohonanKertasKandungan1.setCawangan(permohonan.getCawangan());
            permohonanKertasKandungan1.setSubtajuk("4." + k);
            InfoAudit iaP = new InfoAudit();
            iaP.setTarikhMasuk(new Date());
            iaP.setDimasukOleh(peng);
            permohonanKertasKandungan1.setInfoAudit(iaP);
            strService.simpanPermohonanKertasKandungan(permohonanKertasKandungan1);
            k++;
        }

        senaraiLaporanKandungan5 = strService.findByIdLapor(permohonanKertas.getIdKertas(), 5);

        kira = Integer.parseInt(getContext().getRequest().getParameter("rowCount5"));
        k = 2;
        for (int i = 1; i <= kira; i++) {
            PermohonanKertasKandungan permohonanKertasKandungan1 = new PermohonanKertasKandungan();
            if (!senaraiLaporanKandungan5.isEmpty() && i <= senaraiLaporanKandungan5.size()) {
                Long id = senaraiLaporanKandungan5.get(i - 1).getIdKandungan();
                if (id != null) {
                    permohonanKertasKandungan1 = strService.findkandunganByIdKandungan(id);
                }
            } else {
                permohonanKertasKandungan1 = new PermohonanKertasKandungan();
            }
            permohonanKertasKandungan1.setKertas(permohonanKertas);
            permohonanKertasKandungan1.setBil(5);
            String kandungan = getContext().getRequest().getParameter("kandungan5" + i);
            permohonanKertasKandungan1.setKandungan(kandungan);
            permohonanKertasKandungan1.setCawangan(permohonan.getCawangan());
            permohonanKertasKandungan1.setSubtajuk("5." + k);
            InfoAudit iaP = new InfoAudit();
            iaP.setTarikhMasuk(new Date());
            iaP.setDimasukOleh(peng);
            permohonanKertasKandungan1.setInfoAudit(iaP);
            strService.simpanPermohonanKertasKandungan(permohonanKertasKandungan1);
            k++;
        }

        senaraiLaporanKandungan6 = strService.findByIdLapor(permohonanKertas.getIdKertas(), 6);

        kira = Integer.parseInt(getContext().getRequest().getParameter("rowCount6"));
        k = 2;
        for (int i = 1; i <= kira; i++) {
            PermohonanKertasKandungan permohonanKertasKandungan1 = new PermohonanKertasKandungan();
            if (!senaraiLaporanKandungan6.isEmpty() && i <= senaraiLaporanKandungan6.size()) {
                Long id = senaraiLaporanKandungan6.get(i - 1).getIdKandungan();
                if (id != null) {
                    permohonanKertasKandungan1 = strService.findkandunganByIdKandungan(id);
                }
            } else {
                permohonanKertasKandungan1 = new PermohonanKertasKandungan();
            }
            permohonanKertasKandungan1.setKertas(permohonanKertas);
            permohonanKertasKandungan1.setBil(6);
            String kandungan = getContext().getRequest().getParameter("kandungan6" + i);
            permohonanKertasKandungan1.setKandungan(kandungan);
            permohonanKertasKandungan1.setCawangan(permohonan.getCawangan());
            permohonanKertasKandungan1.setSubtajuk("6." + k);
            InfoAudit iaP = new InfoAudit();
            iaP.setTarikhMasuk(new Date());
            iaP.setDimasukOleh(peng);
            permohonanKertasKandungan1.setInfoAudit(iaP);
            strService.simpanPermohonanKertasKandungan(permohonanKertasKandungan1);
            k++;
        }
        rehydrate();
        addSimpleMessage("Maklumat telah berjaya disimpan.");
        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        return new JSP("strata/kos_rendah/kertasPertimbangan_mmk_333.jsp").addParameter("tab", "true");
    }

    public Hakmilik getHakmilik() {
        return hakmilik;
    }

    public void setHakmilik(Hakmilik hakmilik) {
        this.hakmilik = hakmilik;
    }

    public Pemohon getPemohon() {
        return pemohon;
    }

    public void setPemohon(Pemohon pemohon) {
        this.pemohon = pemohon;
    }

    public String getTarikhDaftar() {
        return tarikhDaftar;
    }

    public void setTarikhDaftar(String tarikhDaftar) {
        this.tarikhDaftar = tarikhDaftar;
    }

    public String getHuraianPtg() {
        return huraianPtg;
    }

    public void setHuraianPtg(String huraianPtg) {
        this.huraianPtg = huraianPtg;
    }

    public String getLatarBelakang() {
        return latarBelakang;
    }

    public void setLatarBelakang(String latarBelakang) {
        this.latarBelakang = latarBelakang;
    }

    public String getSyorPtg() {
        return syorPtg;
    }

    public void setSyorPtg(String syorPtg) {
        this.syorPtg = syorPtg;
    }

    public String getHuraianPentadbir() {
        return huraianPentadbir;
    }

    public void setHuraianPentadbir(String huraianPentadbir) {
        this.huraianPentadbir = huraianPentadbir;
    }

    public String getSyorPentadbir() {
        return syorPentadbir;
    }

    public void setSyorPentadbir(String syorPentadbir) {
        this.syorPentadbir = syorPentadbir;
    }

    public String getTarikhMesyuarat() {
        return tarikhMesyuarat;
    }

    public void setTarikhMesyuarat(String tarikhMesyuarat) {
        this.tarikhMesyuarat = tarikhMesyuarat;
    }

    public HakmilikPihakBerkepentingan getHakmilikPihakBerkepentingan() {
        return hakmilikPihakBerkepentingan;
    }

    public void setHakmilikPihakBerkepentingan(HakmilikPihakBerkepentingan hakmilikPihakBerkepentingan) {
        this.hakmilikPihakBerkepentingan = hakmilikPihakBerkepentingan;
    }

    public PermohonanPihak getPenerima() {
        return penerima;
    }

    public void setPenerima(PermohonanPihak penerima) {
        this.penerima = penerima;
    }

    public List<PermohonanKertasKandungan> getSenaraiLaporanKandungan1() {
        return senaraiLaporanKandungan1;
    }

    public void setSenaraiLaporanKandungan1(List<PermohonanKertasKandungan> senaraiLaporanKandungan1) {
        this.senaraiLaporanKandungan1 = senaraiLaporanKandungan1;
    }

    public List<PermohonanKertasKandungan> getSenaraiLaporanKandungan2() {
        return senaraiLaporanKandungan2;
    }

    public void setSenaraiLaporanKandungan2(List<PermohonanKertasKandungan> senaraiLaporanKandungan2) {
        this.senaraiLaporanKandungan2 = senaraiLaporanKandungan2;
    }

    public List<PermohonanKertasKandungan> getSenaraiLaporanKandungan3() {
        return senaraiLaporanKandungan3;
    }

    public void setSenaraiLaporanKandungan3(List<PermohonanKertasKandungan> senaraiLaporanKandungan3) {
        this.senaraiLaporanKandungan3 = senaraiLaporanKandungan3;
    }

    public List<PermohonanKertasKandungan> getSenaraiLaporanKandungan4() {
        return senaraiLaporanKandungan4;
    }

    public void setSenaraiLaporanKandungan4(List<PermohonanKertasKandungan> senaraiLaporanKandungan4) {
        this.senaraiLaporanKandungan4 = senaraiLaporanKandungan4;
    }

    public List<PermohonanKertasKandungan> getSenaraiLaporanKandungan5() {
        return senaraiLaporanKandungan5;
    }

    public void setSenaraiLaporanKandungan5(List<PermohonanKertasKandungan> senaraiLaporanKandungan5) {
        this.senaraiLaporanKandungan5 = senaraiLaporanKandungan5;
    }

    public List<PermohonanKertasKandungan> getSenaraiLaporanKandungan6() {
        return senaraiLaporanKandungan6;
    }

    public void setSenaraiLaporanKandungan6(List<PermohonanKertasKandungan> senaraiLaporanKandungan6) {
        this.senaraiLaporanKandungan6 = senaraiLaporanKandungan6;
    }

    public String getStageId() {
        return stageId;
    }

    public void setStageId(String stageId) {
        this.stageId = stageId;
    }
}
