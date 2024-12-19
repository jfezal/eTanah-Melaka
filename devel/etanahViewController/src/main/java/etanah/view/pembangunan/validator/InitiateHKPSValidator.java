
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
import etanah.model.KodPeranan;
import etanah.model.KodUrusan;
import etanah.model.Notifikasi;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.service.DevIntegrationService;
import etanah.service.NotifikasiService;
import etanah.service.PembangunanService;
import etanah.service.StrataPtService;
import etanah.workflow.StageContext;
import etanah.workflow.StageListener;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;

/**
 *
 * @author w.fairul
 */
public class InitiateHKPSValidator implements StageListener {

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
    NotifikasiService notifikasiService;
    @Inject
    PembangunanService devService;
    @Inject
    KodPerananDAO kodPerananDAO;
    private static final Logger LOG = Logger.getLogger(InitiateHKPSValidator.class);
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
        Permohonan permohonanREG = new Permohonan();
        
        boolean terusHKGHS = false;
        List<Permohonan> senaraiMohon = new ArrayList<Permohonan>();
        senaraiMohon = devService.getListPermohonanByIdSebelumAndKodUrusan(permohonan.getIdPermohonan(),devService.cariKodUrusanHSPendaftaran(permohonan.getKodUrusan().getKod()));
        if(senaraiMohon.size()==1){
            for(Permohonan p:senaraiMohon){
                if(p.getStatus().equalsIgnoreCase("SL")&&p.getKeputusan().getKod().equalsIgnoreCase("D")){
                    permohonanREG = p;
                    terusHKGHS = true;
                }else{
                    permohonanREG = new Permohonan();;
                }
            }
        }
        
        if(terusHKGHS){
            
            LOG.info("Initiate FT");
            KodUrusan kod = new KodUrusan();            
            kod = kodUrusanDAO.findById("HKGHS");
            LOG.info(kod.getNama());
            LOG.info(permohonan.getFolderDokumen());
            Pengguna peng = (Pengguna) context.getPengguna();
            InfoAudit infoAudit = new InfoAudit();
            infoAudit.setDimasukOleh(peng);
            infoAudit.setTarikhMasuk(new java.util.Date());
            List<HakmilikPermohonan> senaraiHP = permohonanREG.getSenaraiHakmilik();
            List<Hakmilik> senaraiHakmilik = new ArrayList<Hakmilik>();
            for(int i = 0; i < senaraiHP.size(); i++){                
                HakmilikPermohonan hp = senaraiHP.get(i);
                Hakmilik h = hakmilikDAO.findById(hp.getHakmilik().getIdHakmilik());
                senaraiHakmilik.add(h);
            }
            
            String devInfo = dis.intRegPermohonan(kod, peng, senaraiHakmilik, permohonan, "6", "T", context.getStageName());
            Notifikasi n = new Notifikasi();

                n.setTajuk("Makluman dan Pengesahan Pengiraan Cukai Bagi Integrasi Modul Pembangunan");
                n.setMesej(devInfo);
                Pengguna p = context.getPengguna();
                n.setCawangan(p.getKodCawangan());
                ArrayList<KodPeranan> list = new ArrayList<KodPeranan>();
                list.add(kodPerananDAO.findById("9"));    
                n.setInfoAudit(infoAudit);
                notifikasiService.addRolesToNotifikasi(n, p.getKodCawangan(), list);
            
            
            //nak dapat kan MH sementara punya idhakmilik
            //buat HKGHS
            
        }else{
            
            //            permohonan.getPermohonanSebelum().
            LOG.info("Initiate FT");
            Pengguna peng = (Pengguna) context.getPengguna();
            InfoAudit infoAudit = new InfoAudit();
            infoAudit.setDimasukOleh(peng);
            infoAudit.setTarikhMasuk(new java.util.Date());
    //        idHakmilik = permohonan.getSenaraiHakmilik().get(0).getHakmilik().getIdHakmilik();
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
                kod = kodUrusanDAO.findById("HKPS");
            }
            else if(context.getPermohonan().getKodUrusan().getKod().equals("PPCB")){
                kod = kodUrusanDAO.findById("HKPB");
            }
            else if(context.getPermohonan().getKodUrusan().getKod().equals("PYTN")){
                kod = kodUrusanDAO.findById("HKC");
            }
            else if(context.getPermohonan().getKodUrusan().getKod().equals("SBMS")){
                kod = kodUrusanDAO.findById("HKSTB");
            }
            else if(context.getPermohonan().getKodUrusan().getKod().equals("PSBT")){
                kod = kodUrusanDAO.findById("HKSB");
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

    //        List<String> bpelName = new ArrayList<String>();
    //        bpelName.add("ptd");
    //        List listPp = new ArrayList<Pengguna>();
    //        listPp = dis.findPenggunaByBPEL(bpelName, p.getKodCawangan().getKod());
    //        for(int i=0;i<listPp.size();i++){
    //            Pengguna p2 = (Pengguna) listPp.get(i);
    //            if(p2.getPerananUtama()!=null){
    //                list.add(p2.getPerananUtama());
    //            }
    //        }
            n.setInfoAudit(infoAudit);
            notifikasiService.addRolesToNotifikasi(n, p.getKodCawangan(), list);
            
        }
        
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

    private boolean checkUrusanHakmilikSementaraDaftar(Permohonan permohonan) {
        boolean value = false;
        
        List<Permohonan> senaraiMohon = new ArrayList<Permohonan>();
        senaraiMohon = devService.getListPermohonanByIdSebelumAndKodUrusan(permohonan.getIdPermohonan(),devService.cariKodUrusanHSPendaftaran(permohonan.getKodUrusan().getKod()));
        if(senaraiMohon.size()==1){
            for(Permohonan p:senaraiMohon){
                if(p.getStatus().equalsIgnoreCase("SL")&&p.getKeputusan().getKod().equalsIgnoreCase("D")){
                    value = true;
                }else{
                    value = false;
                }


            }
        }else{
            value = false;
        }
        
        return value;
    }
}
