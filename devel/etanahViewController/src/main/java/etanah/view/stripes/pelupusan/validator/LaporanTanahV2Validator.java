/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.stripes.pelupusan.validator;

import com.google.inject.Inject;
import etanah.dao.FolderDokumenDAO;
import etanah.dao.KodDokumenDAO;
import etanah.dao.KodKlasifikasiDAO;
import etanah.dao.KodNotisDAO;
import etanah.dao.LaporanTanahDAO;
import etanah.manager.TabManager;
import etanah.model.FasaPermohonan;
import etanah.model.HakmilikPermohonan;
import etanah.model.LaporanTanah;
import etanah.model.LaporanTanahSempadan;
import etanah.model.Permohonan;
import etanah.model.PermohonanLaporanPelan;
import etanah.model.PermohonanPermitItem;
import etanah.model.PermohonanTuntutanKos;
import etanah.model.TanahRizabPermohonan;
import etanah.report.ReportUtil;
import etanah.service.PelupusanService;
import etanah.service.SemakDokumenService;
import etanah.service.SyerValidationService;
import etanah.service.common.DokumenService;
import etanah.service.common.HakmilikPermohonanService;
import etanah.service.common.KandunganFolderService;
import etanah.service.common.NotisService;
import etanah.service.common.ValidationService;
import etanah.workflow.StageContext;
import etanah.workflow.StageListener;
import org.apache.log4j.Logger;
import java.util.List;

/**
 *
 * @author mazurahayati.yusop
 */
public class LaporanTanahV2Validator implements StageListener {

    @Inject
    etanah.Configuration conf;
    @Inject
    SyerValidationService syerService;
    @Inject
    ValidationService validationService;
    @Inject
    TabManager tabManager;
    @Inject
    KodDokumenDAO kodDokumenDAO;
    @Inject
    FolderDokumenDAO folderDokumenDAO;
    @Inject
    SemakDokumenService semakDokumenService;
    @Inject
    DokumenService dokumenService;
    @Inject
    KandunganFolderService kandunganFolderService;
    @Inject
    KodKlasifikasiDAO kodKlasifikasiDAO;
    @Inject
    NotisService notisService;
    @Inject
    KodNotisDAO kodNotisDAO;
    @Inject
    ReportUtil reportUtil;
    @Inject
    PelupusanService plpservice;
    @Inject
    LaporanTanahDAO laporanTanahDAO;
    @Inject
    HakmilikPermohonanService hakmilikPermohonanService;
    @Inject
    PelupusanService pelupusanService;
    private static final Logger LOGGER = Logger.getLogger(LaporanTanahV2Validator.class);

    @Override
    public boolean beforeStart(StageContext context) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean beforeGenerateReports(StageContext ctx) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void onGenerateReports(StageContext context) {
//        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public String beforeComplete(StageContext context, String proposedOutcome) {
        Permohonan permohonan = context.getPermohonan();

        HakmilikPermohonan hm = pelupusanService.findMohonHakmilik(permohonan.getIdPermohonan());
        LaporanTanah lt = pelupusanService.findLaporanTanahByIdPermohonan(permohonan.getIdPermohonan());
        TanahRizabPermohonan trp = pelupusanService.findTanahRizabByIdPermohonan(permohonan.getIdPermohonan());
        PermohonanLaporanPelan plp = pelupusanService.findByIdPermohonanPelan(permohonan.getIdPermohonan());
        FasaPermohonan fp = pelupusanService.findMohonFasaByIdMohonIdPengguna(permohonan.getIdPermohonan(), "laporan_tanah");
        PermohonanPermitItem ppi = pelupusanService.findByIdMohonPermitItem(permohonan.getIdPermohonan());
        PermohonanTuntutanKos ptk = pelupusanService.findMohonTuntutKosByIdPermohonan(permohonan.getIdPermohonan());
//        LaporanTanahSempadan lts = pelupusanService.findLaporanTanahSempadanById(lt.getIdLaporan());


        //Perihal Tanah
        if (hm != null) {
            if (hm.getKodMilik() == null) {
                context.addMessage(permohonan.getIdPermohonan() + " - Sila Pilih Status Tanah Yang Berkaitan");
                return null;
            } else if (lt != null) {
                if ("K".equals(hm.getKodMilik().getKod().toString())) {
                    LOGGER.info("------K---------");
                    if (lt.getBolehBerimilik() == null) {
                        context.addMessage(permohonan.getIdPermohonan() + " - Sila Pilih Ya Atau Tidak di Bahagian Tanah Kerajaan Boleh diberimilik");
                        return null;
                    }
                }
            }
            if ("R".equals(hm.getKodMilik().getKod().toString())) {
                LOGGER.info("-------R-------");
                if (trp.getRizab() == null) {
                    context.addMessage(permohonan.getIdPermohonan() + " - Sila Masukkan Jenis Rizab");
                    return null;
                }

                if (trp.getNoWarta() == null) {
                    context.addMessage(permohonan.getIdPermohonan() + " - Sila Masukkan No. Warta Kerajaan");
                    return null;
                }
            }

            if ("PPTR".equals(permohonan.getKodUrusan().getKod())) {
                LOGGER.info("--------PPTR");
                if (trp != null) {
                    if (trp.getTarikhWarta() == null) {
                        context.addMessage(permohonan.getIdPermohonan() + " - Sila Masukkan Tarikh Warta");
                        return null;
                    }
                    if (trp.getNoPW() == null) {
                        context.addMessage(permohonan.getIdPermohonan() + " - Sila Masukkan No Pelan Warta");
                        return null;
                    }
                    if (trp.getPenjaga() == null) {
                        context.addMessage(permohonan.getIdPermohonan() + " - Sila Masukkan Nama Pengawal Yang Berkaitan");
                        return null;
                    }
                }
            }

            if (plp != null) {
                if (plp.getKodTanah() == null) {
                    context.addMessage(permohonan.getIdPermohonan() + " - Sila Pilih Jenis Tanah Yang Berkaitan");
                    return null;
                }
            }
            if (hm.getKodKawasanParlimen() == null) {
                context.addMessage(permohonan.getIdPermohonan() + " - Sila Pilih Kawasan Parlimen Yang Berkaitan");
                return null;
            }
            if (hm.getLokasi() == null) {
                context.addMessage(permohonan.getIdPermohonan() + " - Sila Masukkan Lokasi Kedudukan Tanah");
                return null;
            }
            if (hm.getKodDUN() == null) {
                context.addMessage(permohonan.getIdPermohonan() + " - Sila Masukkan DUN Kawasan Tersebut");
                return null;
            }
        }

        // Bersempadan
        if (lt != null) {
            if (lt.getJenisJalan() == null) {
                context.addMessage(permohonan.getIdPermohonan() + " - Sila Masukkan Jenis Jalan Yang Berkaitan");
                return null;
            }
            if (lt.getAdaJalanMasuk() == null) {
                context.addMessage(permohonan.getIdPermohonan() + " - Sila Masukkan Ada Jalan Masuk");
                return null;
            }
        }

        //Keadaan Tanah
        if ("PPTR".equals(permohonan.getKodUrusan().getKod())) {
            if (hm != null) {
                if (hm.getJarak() == null) {
                    context.addMessage(permohonan.getIdPermohonan() + " - Sila Masukkan Jarak Yang Berkaitan");
                    return null;
                }
                if (hm.getJarakDariKediaman() == null) {
                    context.addMessage(permohonan.getIdPermohonan() + " - Sila Masukkan Jarak Kediaman Yang Bersesuaian");
                    return null;
                }
            }
        }

        if (lt != null) {
            if (lt.getKecerunanTanah() == null) {
                context.addMessage(permohonan.getIdPermohonan() + " - Sila Masukkan Kecerunan Tanah");
                return null;
            }
            if (lt.getStrukturTanah() == null) {
                context.addMessage(permohonan.getIdPermohonan() + " - Sila Masukkan Klasifikasi Tanah");
                return null;
            }
            if (lt.getKodKeadaanTanah() == null) {
                context.addMessage(permohonan.getIdPermohonan() + " - Sila Masukkan Keadaan Tanah");
                return null;
            }
            if (lt.getTanahBertumpu() == null) {
                context.addMessage(permohonan.getIdPermohonan() + " - Sila Masukkan Tanah Dipohon Bertumpu");
                return null;
            }
            if (lt.getUsaha() == null) {
                context.addMessage(permohonan.getIdPermohonan() + " - Sila Masukkan Tanah Diusahakan");
                return null;
            }
            if (lt.getAdaBangunan() == null) {
                context.addMessage(permohonan.getIdPermohonan() + " - Sila Masukkan Bangunan");
                return null;
            }
            if (lt.getPerenggan() == null) {
                context.addMessage(permohonan.getIdPermohonan() + " - Sila Masukkan Tanah Sudah Diperenggan");
                return null;
            }
        }

        if ("PHLP".equals(permohonan.getKodUrusan().getKod())) {
            if (lt != null) {
                if (lt.getNilaiTanah() == null) {
                    context.addMessage(permohonan.getIdPermohonan() + " - Sila Masukkan Nilai Anggaran Yang Berkaitan");
                    return null;
                }
            }
        }

        //Perihal Lot-Lot Bersempadan
        List<LaporanTanahSempadan> listlts = pelupusanService.getListLaporanTanahSempadan(permohonan.getIdPermohonan());

        if (listlts.size() == 0) {
            context.addMessage(permohonan.getIdPermohonan() + " - Sila Masukkan Data Yang Bersesuaian di Perihal Lot-Lot Bersempadanan");
            return null;
        } else {
            for (LaporanTanahSempadan listsss : listlts) {
                if (listsss.getJenisSempadan() == null) {
                    context.addMessage(permohonan.getIdPermohonan() + " - Sila Masukkan Sempadan Yang Berkaitan");
                    return null;
                }
                if (listsss.getGuna() == null) {
                    context.addMessage(permohonan.getIdPermohonan() + " - Sila Masukkan di Ruangan Kegunaan Tanah");
                    return null;
                }
                if (listsss.getKeadaanTanah() == null) {
                    context.addMessage(permohonan.getIdPermohonan() + " - Sila Masukkan di Ruangan Keadaan Tanah");
                    return null;
                }
                if (listsss.getMilikKerajaan() == null) {
                    context.addMessage(permohonan.getIdPermohonan() + " - Sila Pilih Data Yang Berkaitan di Ruangan Milik");
                    return null;
                }
            }
        }

        //Syor Penolong Pegawai Tanah
        if (fp != null) {
            if (fp.getKeputusan() == null) {
                context.addMessage(permohonan.getIdPermohonan() + " - Sila Masukkan Syor Penolong Pegawai Tanah");
                return null;
            } else if ("SL".equals(fp.getKeputusan().getKod().toString())) {
                LOGGER.info("-------Sokong--------");
                if (ppi.getKodItemPermit() == null) {
                    context.addMessage(permohonan.getIdPermohonan() + " - Sila Masukkan ruangan Kegunaan");
                    return null;
                }
                if (ptk.getAmaunTuntutan() == null) {
                    context.addMessage(permohonan.getIdPermohonan() + " - Sila Masukkan ruangan Bayaran");
                    return null;
                }
                if (hm.getStatusLuasDiluluskan() == null) {
                    context.addMessage(permohonan.getIdPermohonan() + " - Sila Masukkan ruangan Keluasan");
                    return null;
                } else if ("S".equals(hm.getStatusLuasDiluluskan())) {
                    LOGGER.info("-------S--------");
                    if (hm.getLuasDiluluskan() == null) {
                        context.addMessage(permohonan.getIdPermohonan() + " - Sila Masukkan ruangan Luas DiLuluskan");
                        return null;
                    }
                }
            }
            if ("ST".equals(fp.getKeputusan().getKod().toString())) {
                LOGGER.info("-------Tak Sokong--------");
                if (fp.getUlasan() == null) {
                    context.addMessage(permohonan.getIdPermohonan() + " - Sila Masukkan ruangan Ulasan");
                    return null;
                }
            }

        }
        return proposedOutcome;
    }

    @Override
    public void afterComplete(StageContext context) {
//        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void afterPushBack(StageContext context) {
//        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public String beforePushBack(StageContext context, String proposedOutcome) {
        return proposedOutcome;
    }
}