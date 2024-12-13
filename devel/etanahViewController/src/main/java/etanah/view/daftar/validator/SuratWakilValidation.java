/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.daftar.validator;

import com.google.inject.Inject;
import etanah.dao.PenggunaDAO;
import etanah.dao.PermohonanDAO;
import etanah.dao.WakilKuasaDAO;
import etanah.model.FolderDokumen;
import etanah.model.HakmilikPermohonan;
import etanah.model.HakmilikPihakBerkepentingan;
import etanah.model.HakmilikUrusan;
import etanah.model.InfoAudit;
import etanah.model.KandunganFolder;
import etanah.model.KodUrusan;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.model.PermohonanAtasPerserahan;
import etanah.model.PermohonanHubungan;
import etanah.model.PermohonanRujukanLuar;
import etanah.model.WakilHakmilik;
import etanah.model.WakilKuasa;
import etanah.model.WakilKuasaHakmilik;
import etanah.service.common.HakmilikPihakKepentinganService;
import etanah.service.common.HakmilikUrusanService;
import etanah.service.common.PermohonanService;
import etanah.service.common.KandunganFolderService;
import etanah.service.daftar.BatalNotaService;
//import etanah.model.WakilPihak;
import etanah.service.daftar.PendaftaranSuratKuasaService;
import etanah.workflow.StageContext;
import etanah.workflow.StageListener;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import org.apache.commons.lang.ArrayUtils;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author mohd.fairul
 */
public class SuratWakilValidation implements StageListener {

    @Inject
    PendaftaranSuratKuasaService pdfSuratKuasaService;
    @Inject
    BatalNotaService batalNotaService;
    @Inject
    PenggunaDAO penggunaDAO;
    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    HakmilikUrusanService hakmilikService;
    @Inject
    PermohonanService permohonanService;
    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;
    @Inject
    HakmilikPihakKepentinganService hakmilikPihakKepentinganService;
    @Inject
    KandunganFolderService kandunganFolderService; 
    @Inject
    private WakilKuasaDAO WakilKuasaDAO;
    private static final Logger LOG = Logger.getLogger(SuratWakilValidation.class);
//private WakilPihak wakilPihak;
    private WakilKuasa wakilKuasa;
    private WakilKuasa wakilKuasaBatal;
    private Pengguna pengguna;
    private FolderDokumen folderDokumen;
    private Long idFolder;    
    private List<WakilKuasaHakmilik> wakilKuasaHakmilikList;
    private List<KandunganFolder> senaraiKandungan = new ArrayList<KandunganFolder>();

    @Override
    public boolean beforeStart(StageContext context) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean beforeGenerateReports(StageContext ctx) {
        return true;

    }

    @Override
    public void onGenerateReports(StageContext context) {
    }

    @Override
    public String beforeComplete(StageContext ctx, String proposedOutcome) {
        Permohonan permohonan = ctx.getPermohonan();
        if (!(permohonan.getKodUrusan().getKod().equalsIgnoreCase("SWDB")) || (permohonan.getKodUrusan().getKod().equalsIgnoreCase("SADB"))) {
            wakilKuasa = pdfSuratKuasaService.findWakilKuasa(permohonan.getIdPermohonan());            
        }

        Transaction tx = sessionProvider.get().beginTransaction();
        tx.begin();

        if (proposedOutcome.equals("D")) {
            if (!(permohonan.getKodUrusan().getKod().equalsIgnoreCase("SWDB")) || (permohonan.getKodUrusan().getKod().equalsIgnoreCase("SADB"))) {
                if (wakilKuasa !=null) {
                    setAktifWakilKuasa(wakilKuasa, permohonan, ctx);
                    setAktifWakilHakmilik(wakilKuasa, ctx);
                }                
            }
            //Added by Aizuddin for urusan to batal SW
            if (permohonan.getKodUrusan().getKod().equalsIgnoreCase("SWDB") || (permohonan.getKodUrusan().getKod().equalsIgnoreCase("SADB"))) {
                List<PermohonanAtasPerserahan> listDiTick = batalNotaService.findByidPermohonan(permohonan.getIdPermohonan());
                LOG.info("==== Size Perserahan Terlibat ====" + listDiTick.size());
                if (listDiTick.size() >= 0) {
                    for (PermohonanAtasPerserahan pap : listDiTick) {
                        String idMohonBatal = pap.getUrusan().getIdPerserahan();
                        wakilKuasaBatal = pdfSuratKuasaService.findWakilKuasa(idMohonBatal);
                        setDeaktifWakilKuasa(wakilKuasaBatal, permohonan, ctx);
                        setDeaktifWakilHakmilik(wakilKuasaBatal, ctx);
                    }
                } else {
                    ctx.addMessage("Sila Pilih Urusan Terlibat.");
                }
            }
            pengguna = ctx.getPengguna();
            InfoAudit info = new InfoAudit();
            info.setDimasukOleh(pengguna);
            info.setTarikhMasuk(new java.util.Date());

            try {
                daftarHakmilikUrusan(permohonan, info);
                if (permohonan.getKodUrusan().getKod().equalsIgnoreCase("SWDB") || (permohonan.getKodUrusan().getKod().equalsIgnoreCase("SADB"))){
                    batalUrusan(permohonan, info);
                    updateMohon(permohonan, info, ctx);
                    updateSuratWakilKuasa(info, permohonan, ctx);
                }
            } catch (Exception ex) {
                tx.rollback();
                Throwable t = ex;
                // getting the root cause
                while (t.getCause() != null) {
                    t = t.getCause();
                }
                t.printStackTrace();
                ctx.addMessage("Pendaftaran perserahan " + permohonan.getIdPermohonan()
                        + " Tidak Berjaya.Sila Hubungi Pentadbir Sistem.\r[ " + ex.toString() + " ]");
                return null;
            }
            tx.commit();
        }
        return proposedOutcome;
    }

    //Added by Aizuddin to batal urusan
    private void batalUrusan(Permohonan permohonan, InfoAudit info) throws Exception {
        LOG.info("START BATAL URUSAN");
        List<PermohonanAtasPerserahan> permohonanAtasPerserahanList = permohonan.getSenaraiPermohonanAtasPerserahan();
        List<HakmilikUrusan> hakmilikUrusanList = new ArrayList<HakmilikUrusan>();
        List<HakmilikPihakBerkepentingan> listTmp = new ArrayList<HakmilikPihakBerkepentingan>();
        HakmilikUrusan hu = hakmilikService.findByIdPerserahan(permohonan.getIdPermohonan());
        List<Long> SENARAI_URUSAN = new ArrayList<Long>();

        String kod = permohonan.getKodUrusan().getKod();

        for (PermohonanAtasPerserahan pas : permohonanAtasPerserahanList) {
            HakmilikUrusan hakmilikUrusan = pas.getUrusan();
            
            if (hakmilikUrusan != null) {
                //set mohon not active
                Permohonan mohonBatal = permohonanDAO.findById(hakmilikUrusan.getIdPerserahan());
                LOG.info("ID permohonan nak dibatalkan" +mohonBatal);
                mohonBatal.setStatus("TK");
                info = mohonBatal.getInfoAudit();
                info.setDiKemaskiniOleh(pengguna);
                info.setTarikhKemaskini(new java.util.Date());
                mohonBatal.setInfoAudit(info);
                permohonanService.saveOrUpdate(mohonBatal);
                LOG.info("Setel batalkan" +mohonBatal);
                
                //set hakmilik_urusan not active
                SENARAI_URUSAN.add(hakmilikUrusan.getIdUrusan());
                hakmilikUrusan.setUrusanBatal(hu);
                hakmilikUrusan.setTarikhBatal(permohonan.getInfoAudit().getTarikhMasuk());
                hakmilikUrusan.setIdPermohonanBatal(permohonan.getIdPermohonan());
                hakmilikUrusan.setAktif('T');
                hakmilikUrusan.setFolderDokumenBatal(permohonan.getFolderDokumen());
                info = hakmilikUrusan.getInfoAudit();
                info.setDiKemaskiniOleh(pengguna);
                info.setTarikhKemaskini(new java.util.Date());
                hakmilikUrusan.setInfoAudit(info);
                hakmilikUrusanList.add(hakmilikUrusan);

                //to set pihak involved not active
                List<HakmilikPihakBerkepentingan> hpList = hakmilikPihakKepentinganService.findHakmilikPihakByIdUrusan(hakmilikUrusan);
                for (HakmilikPihakBerkepentingan h : hpList) {
                    h.setAktif('T');
                    h.setPerserahanBatal(hu);
                    info = h.getInfoAudit();
                    info.setDiKemaskiniOleh(pengguna);
                    info.setTarikhKemaskini(new java.util.Date());
                    h.setInfoAudit(info);
                    listTmp.add(h);
                }
                LOG.debug("listTmp ::" + listTmp.size());
            }
        }
        
        hakmilikService.saveOrUpdate(hakmilikUrusanList);
        hakmilikPihakKepentinganService.save(listTmp);
        LOG.info("FINISH BATAL URUSAN");
    }
    
    private void updateMohon(Permohonan permohonan, InfoAudit info, StageContext ctx) throws Exception {
        Pengguna pengguna = ctx.getPengguna();
        Pengguna peng = penggunaDAO.findById(pengguna.getIdPengguna());
        idFolder = permohonan.getFolderDokumen().getFolderId();
        folderDokumen = kandunganFolderService.findFolder(idFolder);
        senaraiKandungan = kandunganFolderService.findByIdFolder(folderDokumen);

        for (KandunganFolder kf : senaraiKandungan) {
            if (kf.getDokumen().getNoRujukan() != null) {
                Permohonan pmhnn = permohonanDAO.findById(kf.getDokumen().getNoRujukan());
                wakilKuasa = WakilKuasaDAO.findById(kf.getDokumen().getNoRujukan());
                if ((pmhnn != null)&&(wakilKuasa != null)) {
                    InfoAudit inf = peng.getInfoAudit();
                    inf.setDiKemaskiniOleh(peng);
                    inf.setTarikhKemaskini(new java.util.Date());
                    pmhnn.setInfoAudit(inf);
                    pmhnn.setStatus("TK");
                    permohonanService.saveOrUpdate(pmhnn);
                }
            }
        }
    }

    @Override
    public void afterComplete(StageContext context) {
//        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void setAktifWakilKuasa(WakilKuasa wakilKuasa, Permohonan permohonan, StageContext ctx) {
        LOG.info("START UPDATE WAKILPIHAK");
        Pengguna pengguna = ctx.getPengguna();
        Pengguna peng = penggunaDAO.findById(pengguna.getIdPengguna());
        InfoAudit info = peng.getInfoAudit();
        info.setDiKemaskiniOleh(peng);
        info.setTarikhKemaskini(new java.util.Date());
        LOG.info(wakilKuasa.getIdWakil());
        LOG.info(peng.getNama());
        LOG.info(info.getDikemaskiniOleh());
        wakilKuasa.setInfoAudit(info);
        wakilKuasa.setAktif('Y');        
        wakilKuasa.setTarikhDaftar(permohonan.getTarikhKeputusan()); // save tarikh daftar
        pdfSuratKuasaService.updateWakilKuasa(wakilKuasa);
        LOG.debug("------- SAVE tarikh daftar:"+wakilKuasa.getTarikhDaftar());

        LOG.info("END UPDATE WAKILPIHAK");
    }
    
    public void updateSuratWakilKuasa(InfoAudit info, Permohonan permohonan, StageContext ctx) {
        idFolder = permohonan.getFolderDokumen().getFolderId();
        folderDokumen = kandunganFolderService.findFolder(idFolder);
        senaraiKandungan = kandunganFolderService.findByIdFolder(folderDokumen);
        Pengguna pengguna = ctx.getPengguna();
        Pengguna peng = penggunaDAO.findById(pengguna.getIdPengguna());
        for (KandunganFolder kf : senaraiKandungan) {
            if (kf.getDokumen().getNoRujukan() != null) {
                wakilKuasa = WakilKuasaDAO.findById(kf.getDokumen().getNoRujukan());
                if (wakilKuasa != null) {
                    InfoAudit inf = peng.getInfoAudit();
                    inf.setDiKemaskiniOleh(peng);
                    inf.setTarikhKemaskini(new java.util.Date());
                    wakilKuasa.setAktif('T');
                    wakilKuasa.setInfoAudit(inf);
                    pdfSuratKuasaService.updateWakilKuasa(wakilKuasa);
                }
            }
        }
    }

    public void setAktifWakilHakmilik(WakilKuasa wakilKuasa, StageContext ctx) {
        LOG.info("START UPDATE WAKILHAKMILIK");
        Pengguna pengguna = ctx.getPengguna();
        Pengguna peng = penggunaDAO.findById(pengguna.getIdPengguna());
        InfoAudit info = peng.getInfoAudit();
        info.setDiKemaskiniOleh(peng);
        info.setTarikhKemaskini(new java.util.Date());
        wakilKuasaHakmilikList = pdfSuratKuasaService.findWakilHakmilikByIdWakil(wakilKuasa.getIdWakil());

        for (WakilKuasaHakmilik wKuasaHakmilik : wakilKuasaHakmilikList) {
            LOG.info(wKuasaHakmilik.getHakmilik().getIdHakmilik());
            wKuasaHakmilik.setInfoAudit(info);
            wKuasaHakmilik.setAktif('Y');
            pdfSuratKuasaService.saveWakilKuasaHakmilik(wKuasaHakmilik);
        }
        LOG.info("END UPDATE WAKILHAKMILIK");


    }

    //Added by Aizuddin to deactivated surat kuasa wakil
    public void setDeaktifWakilKuasa(WakilKuasa wakilKuasa, Permohonan permohonan, StageContext ctx) {
        LOG.info("START BATAL WAKIL PIHAK");
        Pengguna pengguna = ctx.getPengguna();
        Pengguna peng = penggunaDAO.findById(pengguna.getIdPengguna());
        InfoAudit info = peng.getInfoAudit();
        info.setDiKemaskiniOleh(peng);
        info.setTarikhKemaskini(new java.util.Date());
        LOG.info(wakilKuasa.getIdWakil());
        LOG.info(peng.getNama());
        LOG.info(info.getDikemaskiniOleh());
        wakilKuasa.setInfoAudit(info);
        wakilKuasa.setAktif('T');
        pdfSuratKuasaService.updateWakilKuasa(wakilKuasa);

        LOG.info("End BATAL WAKIL PIHAK OH YEAH");
    }

    public void setDeaktifWakilHakmilik(WakilKuasa wakilKuasa, StageContext ctx) {
        LOG.info("START BATAL WAKILHAKMILIK");
        Pengguna pengguna = ctx.getPengguna();
        Pengguna peng = penggunaDAO.findById(pengguna.getIdPengguna());
        InfoAudit info = peng.getInfoAudit();
        info.setDiKemaskiniOleh(peng);
        info.setTarikhKemaskini(new java.util.Date());
        wakilKuasaHakmilikList = pdfSuratKuasaService.findWakilHakmilikByIdWakil(wakilKuasa.getIdWakil());

        for (WakilKuasaHakmilik wKuasaHakmilik : wakilKuasaHakmilikList) {
            LOG.info(wKuasaHakmilik.getHakmilik().getIdHakmilik());
            wKuasaHakmilik.setInfoAudit(info);
            wKuasaHakmilik.setAktif('T');
            pdfSuratKuasaService.saveWakilKuasaHakmilik(wKuasaHakmilik);
        }
        LOG.info("End BATAL WAKIL HAKMILIK OH YEAH");
    }

    //Added by Aizuddin to put urusan in table Hakmilik_Urusan
    private void daftarHakmilikUrusan(Permohonan permohonan, InfoAudit info) throws Exception {
        LOG.info("====START MASUK URUSAN SURATKUASA======");
        List<HakmilikPermohonan> hakmilikList = permohonan.getSenaraiHakmilik();

        KodUrusan kodUrusan = permohonan.getKodUrusan();
        List<HakmilikUrusan> hakmilikUrusanList = new ArrayList<HakmilikUrusan>();
        for (HakmilikPermohonan hakmilikPermohonan : hakmilikList) {
            if (hakmilikPermohonan == null || hakmilikPermohonan.getHakmilik() == null) {
                continue;
            }
            HakmilikUrusan hakmilikUrusan = new HakmilikUrusan();
            hakmilikUrusan.setInfoAudit(info);
            hakmilikUrusan.setDaerah(hakmilikPermohonan.getHakmilik().getDaerah());
            hakmilikUrusan.setIdPerserahan(permohonan.getIdPermohonan());
            hakmilikUrusan.setCawangan(hakmilikPermohonan.getHakmilik().getCawangan());
            hakmilikUrusan.setHakmilik(hakmilikPermohonan.getHakmilik());
            hakmilikUrusan.setKodUrusan(permohonan.getKodUrusan());
            hakmilikUrusan.setLuasTerlibat(hakmilikPermohonan.getLuasTerlibat());
            hakmilikUrusan.setTarikhDaftar(permohonan.getInfoAudit().getTarikhMasuk()); //tarikh perserahan
            hakmilikUrusan.setKodUnitLuas(hakmilikPermohonan.getHakmilik().getKodUnitLuas());
            hakmilikUrusan.setAktif('Y');
            hakmilikUrusan.setFolderDokumen(permohonan.getFolderDokumen());

            hakmilikUrusanList.add(hakmilikUrusan);
        }
        hakmilikService.saveOrUpdate(hakmilikUrusanList);
        LOG.info("======FINISH MASUK URUSAN SURATKUASA======");
    }

    @Override
    public void afterPushBack(StageContext context) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public String beforePushBack(StageContext context, String proposedOutcome) {
        proposedOutcome = "back";
        return proposedOutcome;
    }

    public Long getIdFolder() {
        return idFolder;
    }

    public void setIdFolder(Long idFolder) {
        this.idFolder = idFolder;
    }

    public FolderDokumen getFolderDokumen() {
        return folderDokumen;
    }

    public void setFolderDokumen(FolderDokumen folderDokumen) {
        this.folderDokumen = folderDokumen;
    }

    public List<KandunganFolder> getSenaraiKandungan() {
        return senaraiKandungan;
    }

    public void setSenaraiKandungan(List<KandunganFolder> senaraiKandungan) {
        this.senaraiKandungan = senaraiKandungan;
    }
    
    
}
