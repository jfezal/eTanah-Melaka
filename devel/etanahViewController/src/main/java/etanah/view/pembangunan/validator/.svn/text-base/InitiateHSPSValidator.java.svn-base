/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package etanah.view.pembangunan.validator;

import com.google.inject.Inject;
import etanah.dao.HakmilikDAO;
import etanah.dao.KodJabatanDAO;
import etanah.dao.KodPerananDAO;
import etanah.dao.KodRujukanDAO;
import etanah.dao.KodUrusanDAO;
import etanah.model.Hakmilik;
import etanah.model.HakmilikPermohonan;
import etanah.model.InfoAudit;
import etanah.model.KodJabatan;
import etanah.model.KodPeranan;
import etanah.model.KodRujukan;
import etanah.model.KodUrusan;
import etanah.model.Notifikasi;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.model.PermohonanRujukanLuar;
import etanah.service.DevIntegrationService;
import etanah.service.NotifikasiService;
import etanah.service.StrataPtService;
import etanah.workflow.StageContext;
import etanah.workflow.StageListener;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;

/**
 *
 * @author nursyazwani
 */
public class InitiateHSPSValidator implements StageListener{
    @Inject
    StrataPtService strService;
    @Inject
    GenerateIdPerserahanWorkflow generateIdPerserahanWorkflow;
    @Inject
    HakmilikDAO hakmilikDAO;
    @Inject
    KodUrusanDAO kodUrusanDAO;
    @Inject
    KodJabatanDAO kodJabatanDAO;
    @Inject
    KodRujukanDAO kodRujukanDAO;
    @Inject
    DevIntegrationService dis;
    @Inject
    KodPerananDAO kodPerananDAO;
    @Inject
    NotifikasiService notifikasiService;
    private static final Logger LOG = Logger.getLogger(InitiateHSPSValidator.class);
    private Hakmilik hakmilik;
    private String idHakmilik;


    public Hakmilik getHakmilik() {
        return hakmilik;
    }

    public void setHakmilik(Hakmilik hakmilik) {
        this.hakmilik = hakmilik;
    }

    public String getIdHakmilik() {
        return idHakmilik;
    }

    public void setIdHakmilik(String idHakmilik) {
        this.idHakmilik = idHakmilik;
    }

    @Override
    public boolean beforeStart(StageContext context) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void onGenerateReports(StageContext context) {
    }

    @Override
    public String beforeComplete(StageContext context, String proposedOutcome) {

        Permohonan permohonan = context.getPermohonan();

        //            permohonan.getPermohonanSebelum().
        LOG.info("Initiate QT");
        Pengguna peng = (Pengguna) context.getPengguna();
        InfoAudit infoAudit = new InfoAudit();
        infoAudit.setDimasukOleh(peng);
        infoAudit.setTarikhMasuk(new java.util.Date());
        idHakmilik = permohonan.getSenaraiHakmilik().get(0).getHakmilik().getIdHakmilik();
//        String[] name = {"idHakmilik"};
//        Object[] value = {idHakmilik};
//        List<Hakmilik> senaraiHakmilik = hakmilikDAO.findByEqualCriterias(name, value, null);
       List<HakmilikPermohonan> senaraiHP = permohonan.getSenaraiHakmilik();
        List<Hakmilik> senaraiHakmilik = new ArrayList<Hakmilik>();
        for(int i = 0; i < senaraiHP.size(); i++){
            HakmilikPermohonan hp = permohonan.getSenaraiHakmilik().get(i);
            Hakmilik h = hakmilikDAO.findById(hp.getHakmilik().getIdHakmilik());
            senaraiHakmilik.add(h);
        }
        KodUrusan kod = new KodUrusan();
        if(context.getPermohonan().getKodUrusan().getKod().equals("PPCS")){
            kod = kodUrusanDAO.findById("HSPS");
        }
        else if(context.getPermohonan().getKodUrusan().getKod().equals("PPCB")){
            kod = kodUrusanDAO.findById("HSPB");
        }
        else if(context.getPermohonan().getKodUrusan().getKod().equals("PYTN")){
            kod = kodUrusanDAO.findById("HSC");
        }
        else if(context.getPermohonan().getKodUrusan().getKod().equals("SBMS")){
            kod = kodUrusanDAO.findById("HSSTB");
        }
        else if(context.getPermohonan().getKodUrusan().getKod().equals("PSBT")){
            kod = kodUrusanDAO.findById("HSSB");
        }
        LOG.info(kod.getNama());
        LOG.info(permohonan.getFolderDokumen());
        //generateIdPerserahanWorkflow.generateIdPerserahanWorkflow(kod, peng, senaraiHakmilik, permohonan);
        String devInfo = dis.intRegPermohonan(kod, peng, dis.setListHakmilikByMohonHakmilik(permohonan.getSenaraiHakmilik()), permohonan, "6", "T", context.getStageName());
        Notifikasi n = new Notifikasi();

        n.setTajuk("Makluman dan Pengesahan Pengiraan Cukai Bagi Integrasi Modul Pembangunan");
        n.setMesej(devInfo);
        Pengguna p = context.getPengguna();
        n.setCawangan(p.getKodCawangan());
        ArrayList<KodPeranan> list = new ArrayList<KodPeranan>();
        list.add(kodPerananDAO.findById("9"));    
        n.setInfoAudit(infoAudit);
        notifikasiService.addRolesToNotifikasi(n, p.getKodCawangan(), list);
 //       proposedOutcome = "_WORKFLOW_DIRECTIVE_WITHDRAW";
        return proposedOutcome;
//        return null;
    }

    @Override
    public void afterComplete(StageContext context) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean beforeGenerateReports(StageContext ctx) {

        return true;
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
