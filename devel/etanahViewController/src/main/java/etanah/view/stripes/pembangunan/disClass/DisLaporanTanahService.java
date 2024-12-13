package etanah.view.stripes.pembangunan.disClass;

import able.stripes.AbleActionBean;
import com.google.inject.Inject;
import etanah.Configuration;
import etanah.dao.DokumenDAO;
import etanah.dao.FasaPermohonanDAO;
import etanah.dao.HakmilikDAO;
import etanah.dao.HakmilikPermohonanDAO;
import etanah.dao.KodDUNDAO;
import etanah.dao.KodHakmilikDAO;
import etanah.dao.KodItemPermitDAO;
import etanah.dao.KodKadarPremiumDAO;
import etanah.dao.KodKawasanParlimenDAO;
import etanah.dao.KodKeadaanTanahDAO;
import etanah.dao.KodKecerunanTanahDAO;
import etanah.dao.KodKeputusanDAO;
import etanah.dao.KodPemilikanDAO;
import etanah.dao.KodRizabDAO;
import etanah.dao.KodSekatanKepentinganDAO;
import etanah.dao.KodSeksyenDAO;
import etanah.dao.KodStrukturTanahDAO;
import etanah.dao.KodSyaratNyataDAO;
import etanah.dao.KodTanahDAO;
import etanah.dao.KodTuntutDAO;
import etanah.dao.KodUOMDAO;
import etanah.dao.LaporanTanahDAO;
import etanah.dao.PermohonanDAO;
import etanah.dao.PermohonanLaporanKawasanDAO;
import etanah.dao.PermohonanLaporanUlasanDAO;
import etanah.dao.PermohonanManualDAO;
import etanah.dao.PermohonanPermitItemDAO;
import etanah.dao.PermohonanRujukanLuarDAO;
import etanah.dao.PermohonanTuntutanKosDAO;
import etanah.dao.TanahRizabPermohonanDAO;
import etanah.service.LaporanPelukisPelanService;
import etanah.service.LaporanTanahService;
import etanah.service.PelupusanService;
import etanah.service.RegService;
import etanah.service.common.TanahRizabService;
import etanah.view.strata.CommonService;
import etanah.view.stripes.pelupusan.PelupusanUtiliti;
import com.google.inject.Inject;
import etanah.dao.FolderDokumenDAO;
import etanah.dao.ImejLaporanDAO;
import etanah.dao.KodAgensiDAO;
import etanah.dao.KodBandarPekanMukimDAO;
import etanah.dao.KodCawanganDAO;
import etanah.dao.KodJenisPihakBerkepentinganDAO;
import etanah.dao.KodKategoriTanahDAO;
import etanah.dao.KodKegunaanTanahDAO;
import etanah.dao.KodLotDAO;
import etanah.dao.KodRujukanDAO;
import etanah.dao.KodStatusUlasanJabatanTeknikalDAO;
import etanah.dao.LaporanTanahSempadanDAO;
import etanah.dao.PenggunaDAO;
import etanah.dao.PermohonanKertasDAO;
import etanah.dao.PermohonanKertasKandunganDAO;
import etanah.dao.PermohonanLaporanBangunanDAO;
import etanah.dao.PermohonanLaporanPohonDAO;
import etanah.dao.PermohonanPihakPendepositDAO;
import etanah.dao.PermohonanUkurDAO;
import etanah.model.Dokumen;
import etanah.model.FasaPermohonan;
import etanah.model.FolderDokumen;
import etanah.model.Hakmilik;
import etanah.model.HakmilikPermohonan;
import etanah.model.HakmilikUrusan;
import etanah.model.ImejLaporan;
import etanah.model.InfoAudit;
import etanah.model.KodBandarPekanMukim;
import etanah.model.KodCawangan;
import etanah.model.KodHakmilik;
import etanah.model.KodItemPermit;
import etanah.model.KodKategoriTanah;
import etanah.model.KodKegunaanTanah;
import etanah.model.KodKeputusan;
import etanah.model.KodLot;
import etanah.model.KodSekatanKepentingan;
import etanah.model.KodSeksyen;
import etanah.model.KodSyaratNyata;
import etanah.model.KodUOM;
import etanah.model.KodUrusan;
import etanah.model.KodUrusanAgensi;
import etanah.model.LaporanTanah;
import etanah.model.LaporanTanahSempadan;
import etanah.model.NoPt;
import etanah.model.Notifikasi;
import etanah.model.Pemohon;
import etanah.model.PemohonHubungan;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.model.PermohonanBahanBatuan;
import etanah.model.PermohonanKertas;
import etanah.model.PermohonanKertasKandungan;
import etanah.model.PermohonanLaporanBangunan;
import etanah.model.PermohonanLaporanKandungan;
import etanah.model.PermohonanLaporanKawasan;
import etanah.model.PermohonanLaporanPelan;
import etanah.model.PermohonanLaporanPohon;
import etanah.model.PermohonanLaporanUlasan;
import etanah.model.PermohonanManual;
import etanah.model.PermohonanPermitItem;
import etanah.model.PermohonanPihakPendeposit;
import etanah.model.PermohonanRujukanLuar;
import etanah.model.PermohonanTuntutanKos;
import etanah.model.PermohonanUkur;
import etanah.model.Pihak;
import etanah.model.TanahRizabPermohonan;
import etanah.model.UlasanJabatanTeknikal;
import etanah.sequence.GeneratorIdPermohonan;
import etanah.service.BPelService;
import etanah.service.JTeknikalService;
import etanah.service.PelupusanPtService;
import etanah.service.common.HakmilikUrusanService;
import etanah.service.common.PihakService;
import etanah.view.etanahActionBeanContext;
import etanah.workflow.WorkFlowService;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import oracle.bpel.services.workflow.task.model.Task;
import org.apache.commons.lang.StringUtils;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author Shazwan 21022012 11:44 AM
 * 
 * 
 */
public class DisLaporanTanahService extends AbleActionBean {

    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    PermohonanLaporanKawasanDAO permohonanLaporanKawasanDAO;
    @Inject
    PermohonanKertasDAO permohonanKertasDAO;
    @Inject
    KodRizabDAO kodRizabDAO;
    @Inject
    DokumenDAO dokumenDAO;
    @Inject
    LaporanTanahDAO laporanTanahDAO;
    @Inject
    FasaPermohonanDAO fasaPermohonanDAO;
    @Inject
    PermohonanRujukanLuarDAO permohonanRujukanLuarDAO;
    @Inject
    LaporanTanahService laporanTanahService;
    @Inject
    HakmilikPermohonanDAO hakmilikPermohonanDAO;
    @Inject
    TanahRizabPermohonanDAO tanahRizabPermohonanDAO;
    @Inject
    TanahRizabService tanahRizabService;
    @Inject
    HakmilikDAO hakmilikDAO;
    @Inject
    etanah.Configuration conf;
    @Inject
    CommonService commonService;
    @Inject
    PelupusanService plpservice;
    @Inject
    KodSeksyenDAO kodSeksyenDAO;
    @Inject
    PermohonanManualDAO permohonanManualDAO;
    @Inject
    PermohonanLaporanUlasanDAO permohonanLaporanUlasanDAO;
    @Inject
    KodSyaratNyataDAO kodSyaratNyataDAO;
    @Inject
    KodSekatanKepentinganDAO kodSekatanKepentinganDAO;
    @Inject
    KodHakmilikDAO kodHakmilikDAO;
    @Inject
    KodUOMDAO kodUOMDAO;
    @Inject
    KodStatusUlasanJabatanTeknikalDAO kodStatusUlasanJabatanTeknikalDAO;
    @Inject
    KodLotDAO kodLotDAO;
    @Inject
    KodKategoriTanahDAO kodKategoriTanahDAO;
    @Inject
    KodBandarPekanMukimDAO kodBandarPekanMukimDAO;
    @Inject
    KodDUNDAO kodDUNDAO;
    @Inject
    KodKawasanParlimenDAO kodKawasanParlimenDAO;
    @Inject
    KodKeadaanTanahDAO kodKeadaanTanahDAO;
    @Inject
    KodKadarPremiumDAO kodKadarPremiumDAO;
    @Inject
    KodItemPermitDAO kodItemPermitDAO;
    @Inject
    KodStrukturTanahDAO kodStrukturTanahDAO;
    @Inject
    KodPemilikanDAO kodPemilikanDAO;
    @Inject
    KodTanahDAO kodTanahDAO;
    @Inject
    KodKecerunanTanahDAO kodKecerunanTanahDAO;
    @Inject
    KodKeputusanDAO kodKeputusanDAO;
    @Inject
    PermohonanPermitItemDAO permohonanPermitItemDAO;
    @Inject
    PermohonanTuntutanKosDAO permohonanTuntutanKosDAO;
    @Inject
    KodTuntutDAO kodTuntutDAO;
    @Inject
    PenggunaDAO penggunaDAO;
    @Inject
    KodKegunaanTanahDAO kodKegunaanTanahDAO;
    @Inject
    KodCawanganDAO kodCawanganDAO;
    @Inject
    RegService regService;
    @Inject
    LaporanPelukisPelanService laporanPelukisPelanService;
    @Inject
    PelupusanUtiliti pelUtiliti;
    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;
    @Inject
    PermohonanLaporanBangunanDAO permohonanLaporanBangunanDAO;
    @Inject
    HakmilikUrusanService hakmilikUrusanService;
    @Inject
    LaporanTanahSempadanDAO laporanTanahSempadanDAO;
    @Inject
    PelupusanPtService pelPtService;
    @Inject
    ImejLaporanDAO imejLaporanDAO;
    @Inject
    PelupusanService pelupusanService;
    @Inject
    FolderDokumenDAO folderDokumenDAO;
    @Inject
    GeneratorIdPermohonan generatorIdPermohonan;
    @Inject
    PermohonanUkurDAO permohonanUkurDAO;
    @Inject
    PermohonanPihakPendepositDAO permohonanPihakPendepositDAO;
    @Inject
    KodJenisPihakBerkepentinganDAO kodJenisPihakBerkepentinganDAO;
    @Inject
    PermohonanKertasKandunganDAO permohonanKertasKandunganDAO;
    @Inject
    KodAgensiDAO kodAgensiDAO;
    @Inject
    KodRujukanDAO kodRujukanDAO;
    @Inject
    PermohonanLaporanPohonDAO permohonanLaporanPohonDAO;
    @Inject
    PihakService pihakService;
    @Inject
    JTeknikalService jTeknikalService;

    public String findStageId(String taskId, Pengguna pguna) {
        BPelService service = new BPelService();
        String stageId = null;
        if (StringUtils.isNotBlank(taskId)) {
            Task t = null;
            t = service.getTaskFromBPel(taskId, pguna);
            stageId = t.getSystemAttributes().getStage();
        }
        return stageId;
    }

    /*
     * Author : Shazwan
     * Date   : 06/01/2012
     * Ver    : 1.0
     * Purpose : setting Audit based on type
     */
    public InfoAudit findAudit(Object obj, String type, Pengguna pengguna) {
//        Pengguna pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        InfoAudit info = new InfoAudit();
        if (obj instanceof HakmilikPermohonan) {
            HakmilikPermohonan mohonHM = (HakmilikPermohonan) obj;
            info = mohonHM.getInfoAudit();
        } else if (obj instanceof LaporanTanah) {
            LaporanTanah laporTanah = new LaporanTanah();
            laporTanah = (LaporanTanah) obj;
            info = laporTanah.getInfoAudit();
        } else if (obj instanceof PermohonanLaporanPelan) {
            PermohonanLaporanPelan mohonLaporPelan = new PermohonanLaporanPelan();
            mohonLaporPelan = (PermohonanLaporanPelan) obj;
            info = mohonLaporPelan.getInfoAudit();
        } else if (obj instanceof TanahRizabPermohonan) {
            TanahRizabPermohonan trizab = new TanahRizabPermohonan();
            trizab = (TanahRizabPermohonan) obj;
            info = trizab.getInfoAudit();
        } else if (obj instanceof PermohonanLaporanKandungan) {
            PermohonanLaporanKandungan mohonLaporKand = new PermohonanLaporanKandungan();
            mohonLaporKand = (PermohonanLaporanKandungan) obj;
            info = mohonLaporKand.getInfoAudit();
        } else if (obj instanceof PermohonanLaporanBangunan) {
            PermohonanLaporanBangunan mohonLaporBangunan = new PermohonanLaporanBangunan();
            mohonLaporBangunan = (PermohonanLaporanBangunan) obj;
            info = mohonLaporBangunan.getInfoAudit();
        } else if (obj instanceof PermohonanLaporanKawasan) {
            PermohonanLaporanKawasan mohonLaporKawasan = new PermohonanLaporanKawasan();
            mohonLaporKawasan = (PermohonanLaporanKawasan) obj;
            info = mohonLaporKawasan.getInfoAudit();
        } else if (obj instanceof PermohonanLaporanUlasan) {
            PermohonanLaporanUlasan mohonLaporUlasan = new PermohonanLaporanUlasan();
            mohonLaporUlasan = (PermohonanLaporanUlasan) obj;
            info = mohonLaporUlasan.getInfoAudit();
        } else if (obj instanceof FasaPermohonan) {
            FasaPermohonan mohonFasa = new FasaPermohonan();
            mohonFasa = (FasaPermohonan) obj;
            info = mohonFasa.getInfoAudit();
        } else if (obj instanceof ImejLaporan) {
            ImejLaporan imejLaporan = new ImejLaporan();
            imejLaporan = (ImejLaporan) obj;
            info = imejLaporan.getInfoAudit();
        } else if (obj instanceof PermohonanManual) {
            PermohonanManual mohonManual = new PermohonanManual();
            mohonManual = (PermohonanManual) obj;
            info = mohonManual.getInfoAudit();
        } else if (obj instanceof PermohonanBahanBatuan) {
            PermohonanBahanBatuan mohonBahanBatu = new PermohonanBahanBatuan();
            mohonBahanBatu = (PermohonanBahanBatuan) obj;
            info = mohonBahanBatu.getInfoAudit();
        } else if (obj instanceof PermohonanTuntutanKos) {
            PermohonanTuntutanKos mohonTuntutKos = new PermohonanTuntutanKos();
            mohonTuntutKos = (PermohonanTuntutanKos) obj;
            info = mohonTuntutKos.getInfoAudit();

        } else if (obj instanceof NoPt) {
            NoPt noPt = new NoPt();
            noPt = (NoPt) obj;
            info = noPt.getInfoAudit();
        } else if (obj instanceof PermohonanPermitItem) {
            PermohonanPermitItem permohonanPermitItem = new PermohonanPermitItem();
            permohonanPermitItem = (PermohonanPermitItem) obj;
            info = permohonanPermitItem.getInfoAudit();

        } else if (obj instanceof Dokumen) {
            Dokumen dokumen = new Dokumen();
            dokumen = (Dokumen) obj;
            info = dokumen.getInfoAudit();
        } else if (obj instanceof Notifikasi) {
            Notifikasi notifikasi = new Notifikasi();
            notifikasi = (Notifikasi) obj;
            info = notifikasi.getInfoAudit();
        } else if (obj instanceof Permohonan) {
            Permohonan permohonan = new Permohonan();
            permohonan = (Permohonan) obj;
            info = permohonan.getInfoAudit();
        } else if (obj instanceof PermohonanKertas) {
            PermohonanKertas permohonanKertas = new PermohonanKertas();
            permohonanKertas = (PermohonanKertas) obj;
            info = permohonanKertas.getInfoAudit();
        } else if (obj instanceof PermohonanUkur) {
            PermohonanUkur mohonUkur = new PermohonanUkur();
            mohonUkur = (PermohonanUkur) obj;
            info = mohonUkur.getInfoAudit();
        } else if (obj instanceof Pihak) {
            Pihak pihak = new Pihak();
            pihak = (Pihak) obj;
            info = pihak.getInfoAudit();
        } else if (obj instanceof PermohonanPihakPendeposit) {
            PermohonanPihakPendeposit mohonPihakPendeposit = new PermohonanPihakPendeposit();
            mohonPihakPendeposit = (PermohonanPihakPendeposit) obj;
            info = mohonPihakPendeposit.getInfoAudit();
        } else if (obj instanceof UlasanJabatanTeknikal) {
            UlasanJabatanTeknikal ulasanJabatanTeknikal = new UlasanJabatanTeknikal();
            ulasanJabatanTeknikal = (UlasanJabatanTeknikal) obj;
            info = ulasanJabatanTeknikal.getInfoAudit();
        }


        if (type.equalsIgnoreCase("new")) {
            info = new InfoAudit();
            info.setDimasukOleh(pengguna);
            info.setTarikhMasuk(new java.util.Date());
        } else if (type.equalsIgnoreCase("update")) {
            info.setDiKemaskiniOleh(pengguna);
            info.setTarikhKemaskini(new java.util.Date());
        }

        return info;
    }

    /*
     * Author : Shazwan
     * Date   : 06/01/2012
     * Ver    : 1.0
     * Purpose : to find DATA based on Object Given
     * obj = The Object of the instance
     * data = Value to find
     * num = filter for which method of finding to call
     * 
     * List Of Object in Method - 
     * HakmilikPermohonan,
     * Permohonan,
     * LaporanTanah,
     * PermohonanLaporanPelan,
     * TanahRizabService,
     * TanahRizabPermohonan,
     * PermohonanLaporanKandungan
     * LaporanTanahSempadan
     * PermohonanLaporanUlasan
     * PermohonanLaporanKawasan
     * PermohonanTuntutanKos
     * PermohonanPermitItem
     * NoPt
     * PermohonanBahanBatuan
     * permohonanLaporanBangunan
     * HakmilikUrusan,
     * kodUrusanAgensi
     * PermohonanRujukanLuar
     * KodBandarPekanMukim                           
     * KodSeksyen
     * KodHakmilik
     * KodLot
     * KodKategoriTanah
     * KodSyaratNyata
     * KodKegunaanTanah
     * KODUOM
     * PermohonaUkur
     * KodItemPermit
     * FasaPermohonan
     */
    public Object findObject(Object obj, String[] data, int num) {

        if (obj instanceof HakmilikPermohonan) {
            if (num == 0) {
                // data[0] = idPermohonan, data[1] = idHakmilik
                obj = plpservice.findByIdHakmilikIdPermohonan(data[0], data[1]);
            } else if (num == 1) {
                //data[0] = idPermohonan()
                //Method shared with integration With eMMKN, handle with care
                obj = plpservice.findByIdPermohonan(data[0]);
            } else if (num == 2) {
                //data[0] = idMH
                obj = hakmilikPermohonanDAO.findById(Long.parseLong(data[0]));
            }
        } else if (obj instanceof Permohonan) {
            if (num == 0) {
                // data[0] = idPermohonan, data[1] = idHakmilik            
                obj = plpservice.findById(data[0]);
            }
        } else if (obj instanceof HakmilikUrusan) {
            if (num == 0) {
                // data[0] = idHakmilik, data[1] = kodUrusan            
                obj = plpservice.findByHakmilikNKodUrusan(data[0], data[1]);
            }
        } else if (obj instanceof LaporanTanah) {
            if (num == 0) {
                // data[0] = hakmilikPermohonan.getId();
                obj = plpservice.findLaporanTanahByIdMH(Long.parseLong(data[0]));
            } else if (num == 1) {
                // data[0] = idPermohonan
                obj = plpservice.findLaporanTanahByIdPermohonan(data[0]);
            } else if (num == 2) {
                // data[0] = idlapor
                obj = laporanTanahDAO.findById(Long.valueOf(data[0]));
            }
        } else if (obj instanceof PermohonanLaporanPelan) {
            if (num == 0) {
                // data[0] = idPermohonan
                obj = plpservice.findByIdPermohonanPelan(data[0]);
            } else if (num == 1) {
                //data[0] = idPermohonan , data[1] = idHakmilik
                obj = plpservice.findByIdPermohonanPelanNIdHakmilik(data[0], data[1]);
            } else if (num == 2) {
                //data[0] = idPermohonan , data[1] = idMohonHakmilik
                obj = plpservice.findByIdPermohonanPelanNIdMH(data[0], Long.getLong(data[1]));
            }
        } else if (obj instanceof TanahRizabService) {
            if (num == 0) {
                // data[0] = idPermohonan
                obj = tanahRizabService.findByidPermohonan(data[0]);
            }
        } else if (obj instanceof TanahRizabPermohonan) {
            if (num == 0) {
                // data[0] = idPermohonan, data[1] = idHakmilik
                obj = plpservice.findTanahRizabByIdPermohonanNnoHM(data[0], data[1]); //MULTIPLE HM
            } else if (num == 1) {
                //data[0] = idPermohonan
                obj = tanahRizabService.findByidPermohonan(data[0]);
            }
        } else if (obj instanceof PermohonanLaporanKandungan) {
            if (num == 0) {
                // data[0] = "Lain-lain Jenis Tanah", data[1] = laporanTanah.getIdLaporan();
                obj = plpservice.findByIdLaporSubtajuk(data[0], Long.parseLong(data[1]));
            }
        } else if (obj instanceof LaporanTanahSempadan) {
            if (num == 0) {
                // data[0] = "idKand"
                obj = plpservice.findLaporanTanahSempadanById(Long.parseLong(data[0]));
            } else if (num == 1) {
                // data[0] = "getIdKandungan"
                obj = laporanTanahSempadanDAO.findById(new Long(data[0]));
            }
        } else if (obj instanceof PermohonanLaporanUlasan) {
            if (num == 0) {
                // data[0] = "idKand"
                obj = plpservice.findPermohonanLaporanUlasanById(Long.parseLong(data[0]));
            } else if (num == 1) {
                //data[0] = "idPermohonan" data[1] = "JenisUlasan"
                obj = plpservice.findByIdMohonByJenisUlasan(data[0], data[1]);
            }

        } else if (obj instanceof PermohonanLaporanKawasan) {
            if (num == 0) {
                // data[0] = "mohonLaporKawasan.id"
                obj = plpservice.findPermohonanLaporanKawasanById(Long.parseLong(data[0]));
            } else if (num == 1) {
                // data[0] = "idPermohonan", data[1] = "kod Rizab"                
                obj = plpservice.findPermohonanLaporanKawasanById(Long.parseLong(data[0]));
            }
        } else if (obj instanceof PermohonanTuntutanKos) {
            if (num == 0) {
                // data[0] = "idPermohonan", data[1]="Id tuntut"
                obj = plpservice.findMohonTuntutKosByIdPermohonanAndIdTuntut(data[0], data[1]);
            }
            if (num == 1) {
                // data[0] = "idPermohonan"
                obj = plpservice.findMohonTuntutKosByIdPermohonan(data[0]);
            }
        } else if (obj instanceof PermohonanPermitItem) {
            if (num == 0) {
                // data[0] = "idMohonPermitItem"
                obj = plpservice.findByIdMohonPermitItem(data[0]);
            }
        } else if (obj instanceof NoPt) {
            if (num == 0) {
                // data[0] = hakmilikPermohonan.getId()
                obj = plpservice.findNoPTByIdMH(Long.parseLong(data[0]));
            } else if (num == 1) {
                // data[0] = noPtSementara
                obj = plpservice.findNoPTByNoPtSementara(Long.parseLong(data[0]));
            }
        } else if (obj instanceof PermohonanBahanBatuan) {
            if (num == 0) {
                // data[0] = idPermohonan
                obj = plpservice.findByIdMBB(data[0]);
            }
        } else if (obj instanceof PermohonanLaporanBangunan) {
            if (num == 0) {
                // data[0] = idLaporBangunan
                obj = permohonanLaporanBangunanDAO.findById(Long.valueOf(data[0]));
            }
        } else if (obj instanceof ImejLaporan) {
            if (num == 0) {
                // data[0] = idImej
                obj = imejLaporanDAO.findById(Long.valueOf(data[0]));
            }
        } else if (obj instanceof Dokumen) {
            if (num == 0) {
                // data[0] = idDokumen
                obj = dokumenDAO.findById(Long.valueOf(data[0]));
            }
        } else if (obj instanceof KodUOM) {
            if (num == 0) {
                // data[0] = kod
                obj = kodUOMDAO.findById(data[0]);
            }
        } else if (obj instanceof PermohonanKertas) {
            if (num == 0) {
                // data[0] = idPermohonan
                // data[1] = RMN (KOD KERTAS)
                obj = plpservice.findKertasByKod(data[0], data[1]);
            }
        } else if (obj instanceof KodLot) {
            if (num == 0) {
                //data[0] = kod
                obj = kodLotDAO.findById(data[0]);
            }
        } else if (obj instanceof KodUrusanAgensi) {
            if (num == 0) {
                //data[0] = kod
                obj = pelupusanService.findKodAgensiByKodUrusan(data[0]);
            }
        } else if (obj instanceof PermohonanRujukanLuar) {
            if (num == 0) {
                //data[0] = kod
                obj = pelupusanService.findIDPermohonanRujByIdPermohonan(data[0]);
            } else if (num == 1) {
                //data[0] = idRujukan
                obj = permohonanRujukanLuarDAO.findById(Long.valueOf(data[0]));
            }
        } else if (obj instanceof KodBandarPekanMukim) {
            if (num == 0) {
                //data[0] = kod
                obj = kodBandarPekanMukimDAO.findById(Integer.parseInt(data[0]));
            }
        } else if (obj instanceof KodSeksyen) {
            if (num == 0) {
                //data[0] = kod
                obj = kodSeksyenDAO.findById(Integer.parseInt(data[0]));
            }
        } else if (obj instanceof KodHakmilik) {
            if (num == 0) {
                //data[0] = kod
                obj = kodHakmilikDAO.findById(data[0]);
            }
        } else if (obj instanceof KodLot) {
            if (num == 0) {
                //data[0] = kod
                obj = kodLotDAO.findById(data[0]);
            }
        } else if (obj instanceof KodKategoriTanah) {
            if (num == 0) {
                //data[0] = kod
                obj = kodKategoriTanahDAO.findById(data[0]);
            }
        } else if (obj instanceof KodSyaratNyata) {
            if (num == 0) {
                //data[0] = kod
                obj = kodSyaratNyataDAO.findById(data[0]);
            }
        } else if (obj instanceof KodKegunaanTanah) {
            if (num == 0) {
                //data[0] = kod
                obj = kodKegunaanTanahDAO.findById(data[0]);
            }
        } else if (obj instanceof KodSekatanKepentingan) {
            if (num == 0) {
                // data[0] = kodSekatan
                obj = kodSekatanKepentinganDAO.findById(data[0]);
            }
        } else if (obj instanceof PermohonanUkur) {
            if (num == 0) {
                // data[0] = idPermohonan
                obj = pelupusanService.findPermohonanUkurByIdPermohonan(data[0]);
            }
        } else if (obj instanceof PermohonanPihakPendeposit) {
            if (num == 0) {
                // data[0] = idPermohonan
                obj = pelupusanService.findPermohonanPihakPendepositByIdPermohonanPihakPendeposit(Long.valueOf(data[0]));
            }
        } else if (obj instanceof KodItemPermit) {
            if (num == 0) {
                // data[0] = kod
                obj = kodItemPermitDAO.findById(data[0]);
            }
        } else if (obj instanceof PermohonanManual) {
            if (num == 0) {
                // data[0] = idMohonManual
                obj = permohonanManualDAO.findById(Long.valueOf(data[0]));
            }
        } else if (obj instanceof PermohonanLaporanKawasan) {
            if (num == 0) {
                // data[0] = idMohonManual
                obj = permohonanLaporanKawasanDAO.findById(Long.valueOf(data[0]));
            }
        } else if (obj instanceof FasaPermohonan) {
            if (num == 0) {
                //data[0] = idPermohonan
                //data[1] = stageName
                obj = pelupusanService.findMohonFasaByIdMohonIdPengguna(data[0], data[1]);
            }
        } else if (obj instanceof KodKeputusan) {
            if (num == 0) {
                //data[0] = kod Keputusan
                obj = kodKeputusanDAO.findById(data[0]);
            }
        } else if (obj instanceof PermohonanKertasKandungan) {
            if (num == 0) {
                //data[0] = Id Kandungan
                obj = permohonanKertasKandunganDAO.findById(Long.parseLong(data[0]));
            }
        } else if (obj instanceof Pengguna) {
            if (num == 0) {
                //data[0] = ID Pengguna
                obj = penggunaDAO.findById(data[0]);
            }
        } else if (obj instanceof PermohonanLaporanPohon) {
            if (num == 0) {
                //data[0] = Id Laporan Pohon
                obj = permohonanLaporanPohonDAO.findById(Long.parseLong(data[0]));
            }
        } else if (obj instanceof Pemohon) {
            if (num == 0) {
                //data[0] = Id Laporan Pohon
                obj = pelupusanService.findPemohonByIdPemohon(data[0]);
            }
        } else if (obj instanceof PemohonHubungan) {
            if (num == 0) {
                //data[0] = Id Laporan Pohon
                obj = pelupusanService.findHubunganByIDPemohonBAPA2(Long.parseLong(data[0]));
            } else if (num == 1) {
                obj = pelupusanService.findHubunganByIDPemohonIBU(Long.parseLong(data[0]));
            }
        } else if (obj instanceof UlasanJabatanTeknikal) {
            if (num == 0) {
                //data[0] = Id Laporan Pohon
                obj = pelupusanService.findHubunganByIDPemohonBAPA2(Long.parseLong(data[0]));
            } else if (num == 1) {
                obj = pelupusanService.findHubunganByIDPemohonIBU(Long.parseLong(data[0]));
            }
        }

        return obj;
    }

    /*
     * Author : Shazwan
     * Date   : 06/01/2012
     * Ver    : 1.0
     * Purpose : to Delete Data In Database based on Object Given
     */
    public String delObject(String tName, String[] data, String typeName) {
        String type = new String();
        if (tName.equals("mohonLaporSempadan")) {
            // data[0] = "idKand"
            LaporanTanahSempadan plk = new LaporanTanahSempadan();
            plk = (LaporanTanahSempadan) this.findObject(plk, new String[]{String.valueOf(data[0])}, 0);
            if (plk != null) {
                this.getPelPtService().deleteLaporanTanahSempadan(plk);
            }
            type = "lSempadan";
        } else if (tName.equals("mohonLaporUlas")) {
            // data[0] = "idKand"
            PermohonanLaporanUlasan mohonLaporUlas = new PermohonanLaporanUlasan();
            mohonLaporUlas = (PermohonanLaporanUlasan) this.findObject(mohonLaporUlas, new String[]{String.valueOf(data[0])}, 0);
            if (mohonLaporUlas != null) {
                pelPtService.deletePermohonanLaporanUlasan(mohonLaporUlas);
            }
            if (!StringUtils.isEmpty(typeName)) {
                if (typeName.equals("PPT")) {
                    type = "syorPPT";
                } else if (typeName.equals("PPTKanan")) {
                    type = "syorPPTKanan";
                }
            }
        } else if (tName.equals("mohonLaporKawasan")) {
            PermohonanLaporanKawasan mohonLaporKwsn = new PermohonanLaporanKawasan();
            mohonLaporKwsn = (PermohonanLaporanKawasan) this.findObject(mohonLaporKwsn, new String[]{String.valueOf(data[0])}, 0);
            if (mohonLaporKwsn != null) {
                plpservice.deletePermohonanLaporanKwsn(mohonLaporKwsn);
            }
        } else if (tName.equals("laporanImej")) {
            //data[0] = idImej;
            ImejLaporan imejLaporan = new ImejLaporan();
            imejLaporan = (ImejLaporan) this.findObject(imejLaporan, new String[]{data[0]}, 0);
            if (imejLaporan != null) {
                imejLaporanDAO.delete(imejLaporan);
            }
        } else if (tName.equals("dokumen")) {
            //data[0] = idDokumen;
            Dokumen dok1 = new Dokumen();
            dok1 = (Dokumen) this.findObject(dok1, new String[]{data[0]}, 0);
            if (dok1 != null) {
                dokumenDAO.delete(dok1);
            }
        } else if (tName.equals("mohonHakmilik")) {
            //data[0] = idDokumen;
            HakmilikPermohonan mohonHakmilik = new HakmilikPermohonan();
            mohonHakmilik = (HakmilikPermohonan) this.findObject(mohonHakmilik, new String[]{data[0]}, 2);
            if (mohonHakmilik != null) {
                this.getPelPtService().deleteHakmilikPermohonan(mohonHakmilik);
            }
        } else if (tName.equals("mohonPihakPendeposit")) {
            PermohonanPihakPendeposit ppp = new PermohonanPihakPendeposit();
            ppp = (PermohonanPihakPendeposit) this.findObject(ppp, new String[]{String.valueOf(data[0])}, 0);
            if (ppp != null) {
                pelupusanService.deletePermohonanPihakPendeposit(ppp);
            }
            type = "pendeposit";
        } else if (tName.equals("mohonManual")) {
            PermohonanManual pm = new PermohonanManual();
            pm = (PermohonanManual) this.findObject(pm, new String[]{String.valueOf(data[0])}, 0);
            if (pm != null) {
                pelupusanService.deletePermohonanManual(pm);
            }
            type = "pTerdahulu";
        } else if (tName.equals("mohonManualLT")) {
            PermohonanManual pm = new PermohonanManual();
            pm = (PermohonanManual) this.findObject(pm, new String[]{String.valueOf(data[0])}, 0);
            if (pm != null) {
                pelupusanService.deletePermohonanManual(pm);
            }
            type = "pTanah";
        } else if (tName.equals("mohonLaporKWS")) {
            PermohonanLaporanKawasan plk = new PermohonanLaporanKawasan();
            plk = (PermohonanLaporanKawasan) this.findObject(plk, new String[]{String.valueOf(data[0])}, 0);
            if (plk != null) {
                pelupusanService.deletePermohonanLaporanKwsn(plk);
            }
            type = "dKawasan";
        } else if (tName.equals("mohonKertasKandungan")) {
            PermohonanKertasKandungan pkk = new PermohonanKertasKandungan();
            pkk = (PermohonanKertasKandungan) this.findObject(pkk, new String[]{String.valueOf(data[0])}, 0);
            if (pkk != null) {
                pelPtService.deleteKertasKandungan(pkk);
            }
            if (!StringUtils.isEmpty(typeName)) {
                if (typeName.equals("kTujuan")) {
                    type = "kTujuan";
                } else if (typeName.equals("kPermohonan")) {
                    type = "kPermohonan";
                } else if (typeName.equals("kTanah")) {
                    type = "kTanah";
                } else if (typeName.equals("kHuraianPtd")) {
                    type = "kHuraianPtd";
                } else if (typeName.equals("kPerakuanPtd")) {
                    type = "kPerakuanPtd";
                } else if (typeName.equals("kPerakuanPtg")) {
                    type = "kPerakuanPtg";
                } else if (typeName.equals("kKeputusan")) {
                    type = "kKeputusan";
                } else if (typeName.equals("kLatarBelakang")) {
                    type = "kLatarBelakang";
                } else if (typeName.equals("kMakluman")) {
                    type = "kMakluman";
                }
            }
        } else if (tName.equals("mohonRujukLuar")) {
            PermohonanRujukanLuar prl = new PermohonanRujukanLuar();
            prl = (PermohonanRujukanLuar) this.findObject(prl, new String[]{String.valueOf(data[0])}, 1);
            if (prl != null) {
                pelupusanService.deletePermohonanRujLuar(prl);
            }
            type = "jTeknikal";
        } else if (tName.equals("mohonLaporPohon")) {
            PermohonanLaporanPohon plp = new PermohonanLaporanPohon();
            plp = (PermohonanLaporanPohon) this.findObject(plp, new String[]{String.valueOf(data[0])}, 0);
            if (plp != null) {
                pelupusanService.deletePermohonanLaporanPohon(plp);
            }
            type = "lBelakangTanah";
        }

        return type;
    }

    public boolean generatePermohonanWorkflow(KodUrusan ku, Pengguna pengguna, List<HakmilikPermohonan> senaraiHakmilik, Permohonan permohonan) {
        KodCawangan caw = pengguna.getKodCawangan();
        String idMohon = new String();
        InfoAudit ia = new InfoAudit();
        Date now = new Date();
        String tarikh = (new SimpleDateFormat("ddMMyyyyhhmmss")).format(now);
        ia.setDimasukOleh(pengguna);
        ia.setTarikhMasuk(new java.util.Date());
        if (ku == null) {
            return false;
        }
        Session s = sessionProvider.get();
        Transaction tx = s.beginTransaction();
        long idFolder = Long.parseLong(tarikh); // TODO create seq
        Permohonan p = new Permohonan();
        try {

            FolderDokumen fd = new FolderDokumen();
            fd.setTajuk("TEST_" + tarikh); // TODO
            fd.setInfoAudit(ia);
            fd.setFolderId(idFolder);
            folderDokumenDAO.save(fd);
            p.setStatus("TA");
            p.setIdPermohonan(generatorIdPermohonan.generate(conf.getProperty("kodNegeri"), permohonan.getCawangan(), ku));
            p.setCawangan(pengguna.getKodCawangan());
            p.setKodUrusan(ku);
            p.setFolderDokumen(fd);
            if (permohonan != null) {
                p.setPermohonanSebelum(permohonan);
                p.setPenyerahNama(permohonan.getPenyerahNama());
                p.setKodPenyerah(permohonan.getKodPenyerah());
                p.setPenyerahAlamat1(permohonan.getPenyerahAlamat1());
                p.setPenyerahAlamat2(permohonan.getPenyerahAlamat2());
                if (permohonan.getPenyerahAlamat3() != null) {
                    p.setPenyerahAlamat3(permohonan.getPenyerahAlamat3());
                }

                if (permohonan.getPenyerahAlamat4() != null) {
                    p.setPenyerahAlamat4(permohonan.getPenyerahAlamat4());
                }
                p.setPenyerahPoskod(permohonan.getPenyerahPoskod());
                p.setPenyerahNegeri(permohonan.getPenyerahNegeri());
                idMohon = p.getIdPermohonan();
                p.setInfoAudit(ia);
                permohonanDAO.save(p);

            }
            tx.commit();

        } catch (Exception e) {
            tx.rollback();
            Throwable t = e;
            // getting the root cause
            while (t.getCause() != null) {
                t = t.getCause();
            }
            t.printStackTrace();

            return false;
        }
        try {
            WorkFlowService.initiateTask(p.getKodUrusan().getIdWorkflow(),
                    p.getIdPermohonan(), permohonan.getCawangan().getKod(), pengguna.getIdPengguna(),
                    p.getKodUrusan().getNama());
        } catch (Exception ex) {
            Logger.getLogger(DisLaporanTanahService.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }

        if (!StringUtils.isEmpty(idMohon)) {
            for (HakmilikPermohonan hm : senaraiHakmilik) {
                HakmilikPermohonan hakmilikPermohonan = new HakmilikPermohonan();
                p = new Permohonan();
                p = pelupusanService.findIdPermohonan(idMohon);
                hakmilikPermohonan.setPermohonan(p);
                hakmilikPermohonan.setHakmilik(hm.getHakmilik());
                hakmilikPermohonan.setInfoAudit(this.findAudit(hakmilikPermohonan, "new", pengguna));
                hakmilikPermohonanDAO.save(hakmilikPermohonan);
            }
        }

        return true;
    }

    public Configuration getConf() {
        return conf;
    }

    public void setConf(Configuration conf) {
        this.conf = conf;
    }

    public LaporanTanahService getLaporanTanahService() {
        return laporanTanahService;
    }

    public void setLaporanTanahService(LaporanTanahService laporanTanahService) {
        this.laporanTanahService = laporanTanahService;
    }

    public TanahRizabService getTanahRizabService() {
        return tanahRizabService;
    }

    public void setTanahRizabService(TanahRizabService tanahRizabService) {
        this.tanahRizabService = tanahRizabService;
    }

    public LaporanPelukisPelanService getLaporanPelukisPelanService() {
        return laporanPelukisPelanService;
    }

    public void setLaporanPelukisPelanService(LaporanPelukisPelanService laporanPelukisPelanService) {
        this.laporanPelukisPelanService = laporanPelukisPelanService;
    }

    public PelupusanService getPlpservice() {
        return plpservice;
    }

    public void setPlpservice(PelupusanService plpservice) {
        this.plpservice = plpservice;
    }

    public KodRizabDAO getKodRizabDAO() {
        return kodRizabDAO;
    }

    public void setKodRizabDAO(KodRizabDAO kodRizabDAO) {
        this.kodRizabDAO = kodRizabDAO;
    }

    public KodPemilikanDAO getKodPemilikanDAO() {
        return kodPemilikanDAO;
    }

    public void setKodPemilikanDAO(KodPemilikanDAO kodPemilikanDAO) {
        this.kodPemilikanDAO = kodPemilikanDAO;
    }

    public KodTanahDAO getKodTanahDAO() {
        return kodTanahDAO;
    }

    public void setKodTanahDAO(KodTanahDAO kodTanahDAO) {
        this.kodTanahDAO = kodTanahDAO;
    }

    public KodKawasanParlimenDAO getKodKawasanParlimenDAO() {
        return kodKawasanParlimenDAO;
    }

    public void setKodKawasanParlimenDAO(KodKawasanParlimenDAO kodKawasanParlimenDAO) {
        this.kodKawasanParlimenDAO = kodKawasanParlimenDAO;
    }

    public KodDUNDAO getKodDUNDAO() {
        return kodDUNDAO;
    }

    public void setKodDUNDAO(KodDUNDAO kodDUNDAO) {
        this.kodDUNDAO = kodDUNDAO;
    }

    public PermohonanDAO getPermohonanDAO() {
        return permohonanDAO;
    }

    public void setPermohonanDAO(PermohonanDAO permohonanDAO) {
        this.permohonanDAO = permohonanDAO;
    }

    public LaporanTanahDAO getLaporanTanahDAO() {
        return laporanTanahDAO;
    }

    public void setLaporanTanahDAO(LaporanTanahDAO laporanTanahDAO) {
        this.laporanTanahDAO = laporanTanahDAO;
    }

    public CommonService getCommonService() {
        return commonService;
    }

    public void setCommonService(CommonService commonService) {
        this.commonService = commonService;
    }

    public KodKeputusanDAO getKodKeputusanDAO() {
        return kodKeputusanDAO;
    }

    public void setKodKeputusanDAO(KodKeputusanDAO kodKeputusanDAO) {
        this.kodKeputusanDAO = kodKeputusanDAO;
    }

    public HakmilikDAO getHakmilikDAO() {
        return hakmilikDAO;
    }

    public void setHakmilikDAO(HakmilikDAO hakmilikDAO) {
        this.hakmilikDAO = hakmilikDAO;
    }

    public KodKecerunanTanahDAO getKodKecerunanTanahDAO() {
        return kodKecerunanTanahDAO;
    }

    public void setKodKecerunanTanahDAO(KodKecerunanTanahDAO kodKecerunanTanahDAO) {
        this.kodKecerunanTanahDAO = kodKecerunanTanahDAO;
    }

    public KodUOMDAO getKodUOMDAO() {
        return kodUOMDAO;
    }

    public void setKodUOMDAO(KodUOMDAO kodUOMDAO) {
        this.kodUOMDAO = kodUOMDAO;
    }

    public KodStrukturTanahDAO getKodStrukturTanahDAO() {
        return kodStrukturTanahDAO;
    }

    public void setKodStrukturTanahDAO(KodStrukturTanahDAO kodStrukturTanahDAO) {
        this.kodStrukturTanahDAO = kodStrukturTanahDAO;
    }

    public KodKeadaanTanahDAO getKodKeadaanTanahDAO() {
        return kodKeadaanTanahDAO;
    }

    public void setKodKeadaanTanahDAO(KodKeadaanTanahDAO kodKeadaanTanahDAO) {
        this.kodKeadaanTanahDAO = kodKeadaanTanahDAO;
    }

    public PermohonanLaporanBangunanDAO getPermohonanLaporanBangunanDAO() {
        return permohonanLaporanBangunanDAO;
    }

    public void setPermohonanLaporanBangunanDAO(PermohonanLaporanBangunanDAO permohonanLaporanBangunanDAO) {
        this.permohonanLaporanBangunanDAO = permohonanLaporanBangunanDAO;
    }

    public PelupusanPtService getPelPtService() {
        return pelPtService;
    }

    public void setPelPtService(PelupusanPtService pelPtService) {
        this.pelPtService = pelPtService;
    }

    public KodCawanganDAO getKodCawanganDAO() {
        return kodCawanganDAO;
    }

    public void setKodCawanganDAO(KodCawanganDAO kodCawanganDAO) {
        this.kodCawanganDAO = kodCawanganDAO;
    }

    public KodTuntutDAO getKodTuntutDAO() {
        return kodTuntutDAO;
    }

    public void setKodTuntutDAO(KodTuntutDAO kodTuntutDAO) {
        this.kodTuntutDAO = kodTuntutDAO;
    }

    public PermohonanTuntutanKosDAO getPermohonanTuntutanKosDAO() {
        return permohonanTuntutanKosDAO;
    }

    public void setPermohonanTuntutanKosDAO(PermohonanTuntutanKosDAO permohonanTuntutanKosDAO) {
        this.permohonanTuntutanKosDAO = permohonanTuntutanKosDAO;
    }

    public KodItemPermitDAO getKodItemPermitDAO() {
        return kodItemPermitDAO;
    }

    public void setKodItemPermitDAO(KodItemPermitDAO kodItemPermitDAO) {
        this.kodItemPermitDAO = kodItemPermitDAO;
    }

    public KodHakmilikDAO getKodHakmilikDAO() {
        return kodHakmilikDAO;
    }

    public void setKodHakmilikDAO(KodHakmilikDAO kodHakmilikDAO) {
        this.kodHakmilikDAO = kodHakmilikDAO;
    }

    public PelupusanService getPelupusanService() {
        return pelupusanService;
    }

    public void setPelupusanService(PelupusanService pelupusanService) {
        this.pelupusanService = pelupusanService;
    }

    public PermohonanKertasDAO getPermohonanKertasDAO() {
        return permohonanKertasDAO;
    }

    public void setPermohonanKertasDAO(PermohonanKertasDAO permohonanKertasDAO) {
        this.permohonanKertasDAO = permohonanKertasDAO;
    }

    public KodKegunaanTanahDAO getKodKegunaanTanahDAO() {
        return kodKegunaanTanahDAO;
    }

    public void setKodKegunaanTanahDAO(KodKegunaanTanahDAO kodKegunaanTanahDAO) {
        this.kodKegunaanTanahDAO = kodKegunaanTanahDAO;
    }

    public KodJenisPihakBerkepentinganDAO getKodJenisPihakBerkepentinganDAO() {
        return kodJenisPihakBerkepentinganDAO;
    }

    public void setKodJenisPihakBerkepentinganDAO(KodJenisPihakBerkepentinganDAO kodJenisPihakBerkepentinganDAO) {
        this.kodJenisPihakBerkepentinganDAO = kodJenisPihakBerkepentinganDAO;
    }

    public PelupusanUtiliti getPelUtiliti() {
        return pelUtiliti;
    }

    public void setPelUtiliti(PelupusanUtiliti pelUtiliti) {
        this.pelUtiliti = pelUtiliti;
    }

    public HakmilikPermohonanDAO getHakmilikPermohonanDAO() {
        return hakmilikPermohonanDAO;
    }

    public void setHakmilikPermohonanDAO(HakmilikPermohonanDAO hakmilikPermohonanDAO) {
        this.hakmilikPermohonanDAO = hakmilikPermohonanDAO;
    }

    public KodKategoriTanahDAO getKodKategoriTanahDAO() {
        return kodKategoriTanahDAO;
    }

    public void setKodKategoriTanahDAO(KodKategoriTanahDAO kodKategoriTanahDAO) {
        this.kodKategoriTanahDAO = kodKategoriTanahDAO;
    }

    public KodAgensiDAO getKodAgensiDAO() {
        return kodAgensiDAO;
    }

    public void setKodAgensiDAO(KodAgensiDAO kodAgensiDAO) {
        this.kodAgensiDAO = kodAgensiDAO;
    }

    public KodRujukanDAO getKodRujukanDAO() {
        return kodRujukanDAO;
    }

    public void setKodRujukanDAO(KodRujukanDAO kodRujukanDAO) {
        this.kodRujukanDAO = kodRujukanDAO;
    }

    public KodStatusUlasanJabatanTeknikalDAO getKodStatusUlasanJabatanTeknikalDAO() {
        return kodStatusUlasanJabatanTeknikalDAO;
    }

    public void setKodStatusUlasanJabatanTeknikalDAO(KodStatusUlasanJabatanTeknikalDAO kodStatusUlasanJabatanTeknikalDAO) {
        this.kodStatusUlasanJabatanTeknikalDAO = kodStatusUlasanJabatanTeknikalDAO;
    }

    public JTeknikalService getjTeknikalService() {
        return jTeknikalService;
    }

    public void setjTeknikalService(JTeknikalService jTeknikalService) {
        this.jTeknikalService = jTeknikalService;
    }
    
    
}
