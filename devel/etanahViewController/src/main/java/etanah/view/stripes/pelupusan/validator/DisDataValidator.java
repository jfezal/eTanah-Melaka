/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.stripes.pelupusan.validator;

import com.google.inject.Inject;
import etanah.dao.FasaPermohonanDAO;
import etanah.dao.HakmilikDAO;
import etanah.dao.HakmilikPermohonanDAO;
import etanah.dao.KodKeputusanDAO;
import etanah.dao.KodUrusanDAO;
import etanah.dao.PermohonanDAO;
import etanah.dao.PermohonanKertasDAO;
import etanah.dao.PermohonanKertasKandunganDAO;
import etanah.model.FasaPermohonan;
import etanah.model.Hakmilik;
import etanah.model.HakmilikPermohonan;
import etanah.model.HakmilikUrusan;
import etanah.model.InfoAudit;
import etanah.model.KodPeranan;
import etanah.model.KodUrusan;
import etanah.model.Notifikasi;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.model.PermohonanKertas;
import etanah.model.PermohonanRujukanLuar;
import etanah.sequence.GeneratorIdPermohonan;
import etanah.service.NotifikasiService;
import etanah.service.PelupusanService;
import etanah.service.common.HakmilikUrusanService;
import etanah.view.stripes.pelupusan.disClass.DisLaporanTanahService;
import etanah.view.stripes.pelupusan.disClass.DisPermohonanData;
import etanah.view.stripes.pelupusan.initiateService.InitiateTaskService;
import etanah.view.stripes.pelupusan.initiateService.MohonHakmilikPelupusanService;
import etanah.workflow.StageContext;
import etanah.workflow.StageListener;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import org.apache.log4j.Logger;
import oracle.bpel.services.workflow.StaleObjectException;
import oracle.bpel.services.workflow.WorkflowException;
import org.apache.commons.lang.StringUtils;

/**
 *
 * @author afham
 * Modified by Shazwan
 * purpose : Data Validation All in Here
 */
public class DisDataValidator implements StageListener {

    @Inject
    InitiateTaskService its;    
    @Inject
    GenerateIdPerserahanWorkflow generateIdPerserahanWorkflow;
    @Inject
    DisLaporanTanahService disLaporanTanahService;
    private static final Logger LOG = Logger.getLogger(CatitTanahValidator.class);
    @Inject
    NotifikasiService notifikasiService;
    @Inject
    etanah.Configuration conf;
    

    @Override
    public boolean beforeStart(StageContext context) {
        return true;
    }

    @Override
    public boolean beforeGenerateReports(StageContext ctx) {
        return true;
    }

    @Override
    public void onGenerateReports(StageContext context) {
    }

    @Override
    public String beforeComplete(StageContext context, String proposedOutcome) {

        Permohonan permohonan = context.getPermohonan();
        Pengguna pengguna = context.getPengguna();
        String stage = context.getStageName();
        KodUrusan kodUrusan = permohonan.getKodUrusan();
        DisPermohonanData mohonData = new DisPermohonanData();
        String negeri = conf.getProperty("kodNegeri");
        ArrayList listString = new ArrayList<String>();
        int numUrusan = permohonan.getKodUrusan().getKod().equals("PPBB") ? 1
                : permohonan.getKodUrusan().getKod().equals("PBPTD") ? 2
                : permohonan.getKodUrusan().getKod().equals("PBPTG") ? 3
                : permohonan.getKodUrusan().getKod().equals("LMCRG") ? 4
                : permohonan.getKodUrusan().getKod().equals("PJLB") ? 5
                : permohonan.getKodUrusan().getKod().equals("LPSP") ? 6
                : permohonan.getKodUrusan().getKod().equals("PLPS") ? 7
                : permohonan.getKodUrusan().getKod().equals("PPRU") ? 8
                : permohonan.getKodUrusan().getKod().equals("PPTR") ? 9
                : permohonan.getKodUrusan().getKod().equals("PTGSA") ? 10
                : permohonan.getKodUrusan().getKod().equals("PRMP") ? 11
                : permohonan.getKodUrusan().getKod().equals("PBMT") ? 12
                : permohonan.getKodUrusan().getKod().equals("MCMCL") ? 13
                : permohonan.getKodUrusan().getKod().equals("MMMCL") ? 14
                : permohonan.getKodUrusan().getKod().equals("PRIZ") ? 15
                : permohonan.getKodUrusan().getKod().equals("PHLA") ? 16
                : permohonan.getKodUrusan().getKod().equals("PBRZ") ? 17
                : permohonan.getKodUrusan().getKod().equals("PBHL") ? 18
                : permohonan.getKodUrusan().getKod().equals("BMBT") ? 19
                : permohonan.getKodUrusan().getKod().equals("PJBTR") ? 20
                : permohonan.getKodUrusan().getKod().equals("PLPTD") ? 21
                : permohonan.getKodUrusan().getKod().equals("PBMMK") ? 22
                : permohonan.getKodUrusan().getKod().equals("PTMTA") ? 23
                : permohonan.getKodUrusan().getKod().equals("PTPBP") ? 24
                : permohonan.getKodUrusan().getKod().equals("RLPS") ? 25
                : permohonan.getKodUrusan().getKod().equals("PJTK") ? 26
                : permohonan.getKodUrusan().getKod().equals("PWGSA") ? 27
                : 0;
        
        switch (numUrusan) {
            case 1: 
                //PBMT
                //TODO HERE 
                break;                
            case 12: 
                //PPBB
                listString = mohonData.getProposedByCasePBMT(numUrusan, stage, negeri,permohonan,disLaporanTanahService);
                break;                            
            case 26: 
                //PJTK
                listString = mohonData.getProposedByCasePJTK(numUrusan, stage, negeri,permohonan,disLaporanTanahService);
                break;                
            case 27: 
                //PWGSA
                listString = mohonData.getProposedByCasePWGSA(numUrusan, stage, negeri,permohonan,disLaporanTanahService);
                break;
        }
        if(!listString.isEmpty()){
            for(int i=0;i<listString.size();i++){
                context.addMessage(listString.get(i).toString());
            }
            return null;
        }
        
        // start coding to send application notification to required users/roles
        if (!context.getPermohonan().getKodUrusan().getKod().equals("LMCRG") && !context.getPermohonan().getKodUrusan().getKod().equals("PJLB")) {
            if(stage.equals("kemasukan")){
                    Notifikasi n = new Notifikasi();
                    List<String> bpelName = new ArrayList<String>();
                if (context.getPermohonan().getKodUrusan().getKod().equals("PBMT")) {
                    n.setTajuk("Makluman Kemasukan Permohonan Pemberimilikan Tanah Kerajaan (Seksyen 76 KTN)");
                } else if (context.getPermohonan().getKodUrusan().getKod().equals("PLPS")) {
                    n.setTajuk("Makluman Kemasukan Permohonan Lesen Pendudukan Sementara (Seksyen 65 KTN)");
                } else if (context.getPermohonan().getKodUrusan().getKod().equals("LPSP")) {
                    n.setTajuk("Makluman Kemasukan Permohonan Lesen Pendudukan Sementara dan Permit (Seksyen 69 KTN)");
                } else if (context.getPermohonan().getKodUrusan().getKod().equals("PPBB")) {
                    n.setTajuk("Makluman Kemasukan Permohonan Permit Bahan Batuan (Kelulusan Ketua Menteri)");
                } else if (context.getPermohonan().getKodUrusan().getKod().equals("PBPTD")) {
                    n.setTajuk("Makluman Kemasukan Permohonan Permit Bahan Batuan (Kelulusan PTD)");
                } else if (context.getPermohonan().getKodUrusan().getKod().equals("PBPTG")) {
                    n.setTajuk("Makluman Kemasukan Permohonan Permit Bahan Batuan (Kelulusan PTG)");
                } else if (context.getPermohonan().getKodUrusan().getKod().equals("PPRU")) {
                    n.setTajuk("Makluman Kemasukan Permohonan Permit Menggunakan Ruang Udara");
                } else if (context.getPermohonan().getKodUrusan().getKod().equals("PRMP")) {
                    n.setTajuk("Makluman Kemasukan Permohonan Permit Tanah Pertanian");
                } else if (context.getPermohonan().getKodUrusan().getKod().equals("LMCRG")) {
                    n.setTajuk("Makluman Kemasukan Permohonan Lesen Mencarigali/Penjelajahan");
                } else if (context.getPermohonan().getKodUrusan().getKod().equals("PPTR")) {
                    n.setTajuk("Makluman Kemasukan Permohonan Memajak Tanah Rizab");
                } else if (context.getPermohonan().getKodUrusan().getKod().equals("PTGSA")) {
                    n.setTajuk("Makluman Kemasukan Permohonan Penamatan Tanah GSA");
                } else if (context.getPermohonan().getKodUrusan().getKod().equals("PHLP")) {
                    n.setTajuk("Makluman Kemasukan Permohonan Hak Lalulalang Persendirian");
                } else if (context.getPermohonan().getKodUrusan().getKod().equals("PRIZ")) {
                    n.setTajuk("Makluman Kemasukan Permohonan Perizaban (Kuasa PTD)");
                } else if (context.getPermohonan().getKodUrusan().getKod().equals("PRMMK")) {
                    n.setTajuk("Makluman Kemasukan Permohonan Perizaban (Kelulusan MMK)");
                } else if (context.getPermohonan().getKodUrusan().getKod().equals("PBRZ")) {
                    n.setTajuk("Makluman Kemasukan Permohonan Pembatalan Perizaban");
                } else if (context.getPermohonan().getKodUrusan().getKod().equals("BMBT")) {
                    n.setTajuk("Makluman Kemasukan Permohonan Pemberimilikan Stratum Tanah Bawah Tanah [Subseksyen 92D(1)(b)KTN]");
                } else if (context.getPermohonan().getKodUrusan().getKod().equals("PJBTR")) {
                    n.setTajuk("Makluman Kemasukan Permohonan Pajakan Stratum Tanah Bawah Tanah [Subseksyen 92f(1)a & 92f(1)b KTN]");
                } 
                n.setMesej(context.getPermohonan().getIdPermohonan() + " - " + context.getPermohonan().getKodUrusan().getNama() + " telah dihantar kepada Pentadbir Tanah untuk makluman");
                Pengguna p = context.getPengguna();
                n.setCawangan(p.getKodCawangan());
                ArrayList<KodPeranan> list = new ArrayList<KodPeranan>();
                bpelName.add("ptd");
                List listPp = new ArrayList<Pengguna>();
                listPp = disLaporanTanahService.getPelupusanService().findPenggunaByBPEL(bpelName, permohonan.getCawangan().getKod());
                for(int i=0;i<listPp.size();i++){
                    if(pengguna.getPerananUtama()!=null)
                        list.add(pengguna.getPerananUtama());
                }
                
//                list.add(listPp.get(0));
//                list.add(kodPerananDAO.findById("225"));
                InfoAudit ia = new InfoAudit();
                ia.setDimasukOleh(p);
                ia.setTarikhMasuk(new Date());
                n.setInfoAudit(ia);
                notifikasiService.addRolesToNotifikasi(n, p.getKodCawangan(), list);
                context.addMessage(" : Makluman Kemasukan Permohonan kepada Pentadbir Tanah Telah Dihantar.");
            }
            
        }
        
        return proposedOutcome;
    }

    @Override
    public void afterComplete(StageContext context) {
    }

    @Override
    public void afterPushBack(StageContext context) {
    }

    @Override
    public String beforePushBack(StageContext context, String proposedOutcome) {
        return proposedOutcome;
    }
}
