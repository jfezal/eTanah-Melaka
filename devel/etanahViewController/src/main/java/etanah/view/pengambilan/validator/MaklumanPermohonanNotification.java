/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.pengambilan.validator;


import etanah.dao.HakmilikDAO;
import etanah.dao.KodJabatanDAO;
import etanah.dao.KodPerananDAO;
import etanah.dao.KodRujukanDAO;
import etanah.dao.KodUrusanDAO;
import etanah.model.Hakmilik;
import etanah.model.HakmilikPermohonan;
import etanah.model.HakmilikPihakBerkepentingan;
import etanah.model.Notifikasi;
import org.hibernate.Session;
import etanah.model.KodPeranan;
import etanah.service.NotifikasiService;
import etanah.service.StrataPtService;
import java.util.Date;
import etanah.service.PembangunanService;
import com.google.inject.Inject;
import etanah.dao.FolderDokumenDAO;
import etanah.dao.KodDokumenDAO;
import etanah.dao.KodKlasifikasiDAO;
import etanah.dao.KodNotisDAO;
import etanah.dao.LaporanTanahDAO;
import etanah.manager.TabManager;
import etanah.model.InfoAudit;
import etanah.model.KodUrusan;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.report.ReportUtil;
import etanah.service.PelupusanService;
import etanah.service.SemakDokumenService;
import etanah.service.SyerValidationService;
import etanah.service.common.DokumenService;
import etanah.service.common.HakmilikPermohonanService;
import etanah.service.common.KandunganFolderService;
import etanah.service.common.NotisService;
import etanah.service.common.ValidationService;
import etanah.view.pembangunan.validator.GenerateIdPermohonan;
import etanah.workflow.StageContext;
import etanah.workflow.StageListener;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author massita
 */
public class MaklumanPermohonanNotification implements StageListener {
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
    protected com.google.inject.Provider<Session> sessionProvider;
    @Inject
    KodPerananDAO kodPerananDAO;
    @Inject
    NotifikasiService notifikasiService;
    @Inject
    GenerateIdPermohonan generateIdPerserahanWorkflow;
    @Inject
    HakmilikDAO hakmilikDAO;
    @Inject
    KodUrusanDAO kodUrusanDAO;
    @Inject
    StrataPtService strService;
    @Inject
    KodJabatanDAO kodJabatanDAO;
    @Inject
    PembangunanService devService;
    @Inject
    HakmilikPermohonanService hakmilikPermohonanService;
    private Hakmilik hakmilik;
    private String idHakmilik;
    private List<HakmilikPihakBerkepentingan> senaraipihak;
    @Inject
    KodRujukanDAO kodRujukanDAO;

    public Hakmilik getHakmilik() {
        return hakmilik;
    }

    public void setHakmilik(Hakmilik hakmilik) {
        this.hakmilik = hakmilik;
    }

    public List<HakmilikPihakBerkepentingan> getSenaraipihak() {
        return senaraipihak;
    }

    public void setSenaraipihak(List<HakmilikPihakBerkepentingan> senaraipihak) {
        this.senaraipihak = senaraipihak;
    }
    
    @Override
    public boolean beforeStart(StageContext context) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void onGenerateReports(StageContext context) {
        throw new UnsupportedOperationException("Not supported yet.");
    }


   

    @Override
    public String beforeComplete(StageContext context, String proposedOutcome) {

       String kodNegeri = conf.getProperty("kodNegeri");
       if(kodNegeri.equalsIgnoreCase("05")) {	// negeri
        Notifikasi n = new Notifikasi();
        n.setTajuk("Makluman Permohonan Modul Pengambilan");
        n.setMesej(context.getPermohonan().getKodUrusan().getNama() + " telah dihantar kepada Penolong Pegawai Tanah (Kanan) untuk tindakan selanjutnya.");
        Pengguna p = context.getPengguna();
        n.setCawangan(p.getKodCawangan());
        ArrayList<KodPeranan> list = new ArrayList<KodPeranan>();
        list.add(kodPerananDAO.findById("71")); //etns - penolong pentadbir(pptd)
        list.add(kodPerananDAO.findById("63"));//etns - Penolong Pegawai Tanah (Kanan)(kpptanah-so kanan)
        list.add(kodPerananDAO.findById("33"));//negeri - penolong pentadbir(pptd)
        list.add(kodPerananDAO.findById("223"));//negeri - Penolong Pegawai Tanah (Kanan)(kpptanah-so kanan)
        InfoAudit ia = new InfoAudit();
        ia.setDimasukOleh(p);
        ia.setTarikhMasuk(new Date());
        n.setInfoAudit(ia);
        notifikasiService.addRolesToNotifikasi(n, p.getKodCawangan(), list);
        initiate(context, proposedOutcome);
        context.addMessage("Makluman kepada Penolong Pentadbir Tanah dan Pentadbir Tanah telah dihantar.");
       }
       else if(kodNegeri.equalsIgnoreCase("04")) {	// negeri
        Notifikasi n = new Notifikasi();
        n.setTajuk("Makluman Permohonan Modul Pengambilan");
        n.setMesej(context.getPermohonan().getKodUrusan().getNama() + " telah dihantar kepada Penolong Pegawai Tanah (Kanan) untuk tindakan selanjutnya.");
        Pengguna p = context.getPengguna();
        n.setCawangan(p.getKodCawangan());
        ArrayList<KodPeranan> list = new ArrayList<KodPeranan>();
        list.add(kodPerananDAO.findById("71")); //etns - penolong pentadbir(pptd)
        list.add(kodPerananDAO.findById("63"));//etns - Penolong Pegawai Tanah (Kanan)(kpptanah-so kanan)
        list.add(kodPerananDAO.findById("33"));//negeri - penolong pentadbir(pptd)
        list.add(kodPerananDAO.findById("223"));//negeri - Penolong Pegawai Tanah (Kanan)(kpptanah-so kanan)
        list.add(kodPerananDAO.findById("30"));//etml- pembantu tadbir kanan
        list.add(kodPerananDAO.findById("77"));//etml - pentadbir tanah
        InfoAudit ia = new InfoAudit();
        ia.setDimasukOleh(p);
        ia.setTarikhMasuk(new Date());
        n.setInfoAudit(ia);
        notifikasiService.addRolesToNotifikasi(n, p.getKodCawangan(), list);
        initiate(context, proposedOutcome);
        context.addMessage("Makluman kepada Pentadbir Tanah dan Pembantu Tadbir Kanan telah dihantar.");
       }
        return proposedOutcome;
    }

    @Override
    public void afterComplete(StageContext context) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean beforeGenerateReports(StageContext ctx) {
        return true;
    }

    void initiate(StageContext context, String proposedOutcome) {
        Permohonan permohonan = context.getPermohonan();
        Pengguna peng = (Pengguna) context.getPengguna();
        InfoAudit infoAudit = new InfoAudit();
        infoAudit.setDimasukOleh(peng);
        infoAudit.setTarikhMasuk(new java.util.Date());
        List<HakmilikPermohonan> senaraiHP = permohonan.getSenaraiHakmilik();
        List<Hakmilik> senaraiHakmilik = new ArrayList<Hakmilik>();
        for(int i = 0; i < senaraiHP.size(); i++){
            HakmilikPermohonan hp = permohonan.getSenaraiHakmilik().get(i);
            Hakmilik h = hakmilikDAO.findById(hp.getHakmilik().getIdHakmilik());
            senaraiHakmilik.add(h);
        }
        
        KodUrusan kod = new KodUrusan();
        if(context.getPermohonan().getKodUrusan().getKod().equals("PTPT")){
            kod = kodUrusanDAO.findById("PTPT");
        }
        
        for(int i = 0; i < senaraiHakmilik.size(); i++){
                hakmilik = senaraiHakmilik.get(i);
                senaraipihak = devService.senaraiPihakBerkepentingan(idHakmilik);
            }
        generateIdPerserahanWorkflow.generateIdPerserahanWorkflow(kod, peng, senaraiHakmilik, permohonan);
       
    }

     @Override
    public void afterPushBack(StageContext context) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public String beforePushBack(StageContext context, String proposedOutcome) {
        return proposedOutcome;
    }
}
