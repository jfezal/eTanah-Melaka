/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.pembangunan.validator;

import com.google.inject.Inject;
import etanah.dao.HakmilikDAO;
import etanah.dao.KodJabatanDAO;
import etanah.dao.KodPerananDAO;
import etanah.dao.KodUrusanDAO;
import etanah.model.Hakmilik;
import etanah.model.HakmilikPermohonan;
import etanah.model.KodUrusan;
import etanah.model.Permohonan;
import etanah.service.DevIntegrationService;
import etanah.service.NotifikasiService;
import etanah.service.PembangunanService;
import etanah.workflow.StageContext;
import etanah.workflow.StageListener;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author faidzal
 *
 */
public class NSdevValidator implements StageListener {

    @Inject
    DevIntegrationService dis;
    @Inject
    PembangunanService ps;
    @Inject
    PembangunanService devService;
    @Inject
    HakmilikDAO hakmilikDAO;
    @Inject
    KodUrusanDAO kodUrusanDAO;
    @Inject
    KodJabatanDAO kodJabatanDAO;
    @Inject
    KodPerananDAO kodPerananDAO;
    @Inject
    NotifikasiService notifikasiService;
    //private static final Logger LOG = Logger.getLogger(NSdevValidator.class);

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
        KodUrusan kod = new KodUrusan();

        if (context.getPermohonan().getKodUrusan().getKod().equals("PPCS")) {
            if (context.getStageName().equals("kemasukan")) {
                kod = dis.findKodUrusan("PSM");
            }
            if (context.getStageName().equals("cetaksrtkpsnmmktolak")) {
                kod = dis.findKodUrusan("PSB");
            }
            if (context.getStageName().equals("kelulusan9a")) {
                kod = dis.findKodUrusan("PSL");
            }
            if (context.getStageName().equals("g_terima_b2")) {
                System.out.println("Id Hakmilik Inside g_terima_b2 : " + permohonan.getSenaraiHakmilik().get(0).getHakmilik().getIdHakmilik());
                List listNaikTarafKwsn = ps.findHakmilikUrusanByHakmilikAndUrusan(permohonan.getSenaraiHakmilik().get(0).getHakmilik().getIdHakmilik(), "ITP", "ITB");
                System.out.println("List Taraf Kwsn : " + listNaikTarafKwsn.size());
                if (listNaikTarafKwsn.size() > 0) {
                    kod = dis.findKodUrusan("HSPTK");
                } else {
                    kod = dis.findKodUrusan("HSPS");
                }
            }
            if (context.getStageName().equals("g_semak_pa_b1")) {
                List listNaikTarafKwsn = ps.findHakmilikUrusanByHakmilikAndUrusan(permohonan.getSenaraiHakmilik().get(0).getHakmilik().getIdHakmilik(), "ITP", "ITB");
                if (listNaikTarafKwsn.size() > 0) {
                    kod = dis.findKodUrusan("HKPTK");
                } else {
                    kod = dis.findKodUrusan("HKPS");
                }
            }
        }

        //addedd for PPCB
        //added new condition ||
        if (context.getPermohonan().getKodUrusan().getKod().equals("PPCB") || context.getPermohonan().getKodUrusan().getKod().equals("PPCBA")) {
            if (context.getStageName().equals("kemasukan")) {
                if (context.getPermohonan().getKodUrusan().getKod().equals("PPCB") || context.getPermohonan().getKodUrusan().getKod().equals("PPCBA")) {
                    kod = dis.findKodUrusan("PBM");
                }
            }
            if (context.getStageName().equals("kelulusan9bpertimbptg")) {
                kod = dis.findKodUrusan("PBL");
            }
            if (context.getStageName().equals("kelulusan9bmmk")) {
                kod = dis.findKodUrusan("PBL");
            }
            if (context.getStageName().equals("cetaksrtkpsnptdbyrnhmilik")) {
                kod = dis.findKodUrusan("PBL");
            }

            if (context.getStageName().equals("g_terima_b2")) {
                System.out.println("Id Hakmilik Inside g_terima_b2 : " + permohonan.getSenaraiHakmilik().get(0).getHakmilik().getIdHakmilik());
                List listNaikTarafKwsn = ps.findHakmilikUrusanByHakmilikAndUrusan(permohonan.getSenaraiHakmilik().get(0).getHakmilik().getIdHakmilik(), "ITP", "ITB");
                System.out.println("List Taraf Kwsn : " + listNaikTarafKwsn.size());
                if (listNaikTarafKwsn.size() > 0) {
                    kod = dis.findKodUrusan("HSBTK");
                } else {
                    kod = dis.findKodUrusan("HSPB");
                }
            }
            if (context.getStageName().equals("g_semak_pa_b1")) {
                List listNaikTarafKwsn = ps.findHakmilikUrusanByHakmilikAndUrusan(permohonan.getSenaraiHakmilik().get(0).getHakmilik().getIdHakmilik(), "ITP", "ITB");
                if (listNaikTarafKwsn.size() > 0) {
                    kod = dis.findKodUrusan("HKBTK");
                } else {
                    kod = dis.findKodUrusan("HKPB");
                }
            }

            if (context.getStageName().equals("cetaksrtkpsnmmktolak")) {
                kod = dis.findKodUrusan("PBB");
            }
        }
        //

        if (context.getPermohonan().getKodUrusan().getKod().equals("PYTN")) {
            if (context.getStageName().equals("kemasukan")) {
                kod = dis.findKodUrusan("CM");
            }
            if (context.getStageName().equals("cetaksrtkpsnptdbyrnhmilik")) {
                kod = dis.findKodUrusan("CL");
            }
            if (context.getStageName().equals("kelulusan9cpertimbptg")) {
                kod = dis.findKodUrusan("CL");
            }
            if (context.getStageName().equals("kelulusan9cmmk")) {
                kod = dis.findKodUrusan("CL");
            }
            if (context.getStageName().equals("g_terima_b2")) {
                System.out.println("Id Hakmilik Inside g_terima_b2 : " + permohonan.getSenaraiHakmilik().get(0).getHakmilik().getIdHakmilik());
                List listNaikTarafKwsn = ps.findHakmilikUrusanByHakmilikAndUrusan(permohonan.getSenaraiHakmilik().get(0).getHakmilik().getIdHakmilik(), "ITP", "ITB");
                System.out.println("List Taraf Kwsn : " + listNaikTarafKwsn.size());
                if (listNaikTarafKwsn.size() > 0) {
                    kod = dis.findKodUrusan("HSCTK");
                } else {
                    kod = dis.findKodUrusan("HSC");
                }
            }
            if (context.getStageName().equals("g_semak_pa_b1")) {
                List listNaikTarafKwsn = ps.findHakmilikUrusanByHakmilikAndUrusan(permohonan.getSenaraiHakmilik().get(0).getHakmilik().getIdHakmilik(), "ITP", "ITB");
                if (listNaikTarafKwsn.size() > 0) {
                    kod = dis.findKodUrusan("HKCTK");
                } else {
                    kod = dis.findKodUrusan("HKC");
                }
            }
            if (context.getStageName().equals("cetaksrtkpsnmmktolak")) {
                kod = dis.findKodUrusan("CB");
            }
        }


        if (context.getPermohonan().getKodUrusan().getKod().equals("SBMS")) {
            if (context.getStageName().equals("kemasukan")) {
                kod = dis.findKodUrusan("SBKSM");
            }
            if (context.getStageName().equals("cetaksuratlulusnotis5a")) {
                kod = dis.findKodUrusan("SBKSL");
            }
            if (context.getStageName().equals("g_terima_b2")) {
                kod = dis.findKodUrusan("HSSTB");
            }
            if (context.getStageName().equals("g_semak_pa_b1")) {
                kod = dis.findKodUrusan("HKSTB");
            }
            if (context.getStageName().equals("cetaksrtkpsnmmktolak")) {
                kod = dis.findKodUrusan("SBKSB");
            }
        }



        if (context.getPermohonan().getKodUrusan().getKod().equals("SBPS")) {
            if (context.getStageName().equals("kemasukan")) {
                kod = dis.findKodUrusan("SBKSM");
            }
            if (context.getStageName().equals("cetaksuratlulusnotis5a")) {
                kod = dis.findKodUrusan("SBKSL");
            }
            if (context.getStageName().equals("g_terima_b2")) {
                kod = dis.findKodUrusan("HSSTB");
            }
            if (context.getStageName().equals("g_semak_pa_b1")) {
                kod = dis.findKodUrusan("HKSTB");
            }
            if (context.getStageName().equals("cetaksrtkpsnmmktolak")) {
                kod = dis.findKodUrusan("SBKSB");
            }
        }

        if (context.getPermohonan().getKodUrusan().getKod().equals("PSBT")) {
            if (context.getStageName().equals("kemasukan")) {
                kod = dis.findKodUrusan("SBSTM");
            }
            if (context.getStageName().equals("cetaksrtkpsnptdbyrnhmilik")) {
                kod = dis.findKodUrusan("SBSTL");
            }
            if (context.getStageName().equals("g_terima_b2")) {
                kod = dis.findKodUrusan("HSSB");
            }
            if (context.getStageName().equals("g_semak_pa_b1")) {
                kod = dis.findKodUrusan("HKSB");
            }
            if (context.getStageName().equals("cetaksrtkpsntolak")) {
                kod = dis.findKodUrusan("SBSTB");
            }
        }
        if (context.getPermohonan().getKodUrusan().getKod().equals("PSMT")) {
            if (context.getStageName().equals("kemasukan")) {
                kod = dis.findKodUrusan("SBTM");
            }
            if (context.getStageName().equals("cetaksrtkpsnptdbyrnhmilik") || context.getStageName().equals("cetaksrtkpsnptgbyrnhmilik")) {
                kod = dis.findKodUrusan("SBTL");
            }
            if (context.getStageName().equals("cetaksrtkpsntolak")) {
                kod = dis.findKodUrusan("SBTB");
            }
        }


        //urusan Tukar Syrat
        if (context.getPermohonan().getKodUrusan().getKod().equals("TSPTG")) {
            if (context.getStageName().equals("kemasukan")) {
                if (context.getPermohonan().getKodUrusan().getKod().equals("TSPTG")) {
                    kod = dis.findKodUrusan("TSSKM");
                }
            }
            if (context.getStageName().equals("sediaborang7c")) {
                if (permohonan.getKeputusan().getKod().equals("L")) {
                    kod = dis.findKodUrusan("TSSKL");
                } else {
                    kod = dis.findKodUrusan("TSSKB");
                }
            }
            if (context.getStageName().equals("cetaksuratkpsntolak")) {
                if (permohonan.getKeputusan().getKod().equals("L")) {
                    kod = dis.findKodUrusan("TSSKL");
                } else {
                    kod = dis.findKodUrusan("TSSKB");
                }
            }
        }

        if (context.getPermohonan().getKodUrusan().getKod().equals("TSMMK")) {
            if (context.getStageName().equals("kemasukan")) {
                kod = dis.findKodUrusan("TSSKM");
            }
            if (context.getStageName().equals("sediaborang7c")) {
                kod = dis.findKodUrusan("TSSKL");
            }
            if (context.getStageName().equals("cetaksuratkpsntolak")) {
                kod = dis.findKodUrusan("TSSKB");
            }
        }

        if (context.getPermohonan().getKodUrusan().getKod().equals("TSPTD")) {
            if (context.getStageName().equals("kemasukan")) {
                kod = dis.findKodUrusan("TSSKM");
            }
            if (context.getStageName().equals("sediaborang7c")) {

                kod = dis.findKodUrusan("TSSKL");

            }
        }

        if (context.getPermohonan().getKodUrusan().getKod().equals("TSPSS")) {
            if (context.getStageName().equals("kemasukan")) {
                kod = dis.findKodUrusan("SSKPM");
            }
            if (context.getStageName().equals("cetaksrtkpsnmmknotis7g")) {
                kod = dis.findKodUrusan("SSKPL");
            }
            if (context.getStageName().equals("g_terima_b2")) {
                kod = dis.findKodUrusan("HSPS");
            }
            if (context.getStageName().equals("g_semak_pa_b1")) {
                kod = dis.findKodUrusan("HKPS");
            }
            if (context.getStageName().equals("cetaksrtkpsnmmktolak")) {
                kod = dis.findKodUrusan("SSKPB");
            }
        }

//        if (context.getPermohonan().getKodUrusan().getKod().equals("PSMT")) {
//            if (context.getStageName().equals("cetaksrtkpsntolak")) {
//                kod = dis.findKodUrusan("SSKPB");
//            }
//        }
       
        
        Permohonan permohonanREG = new Permohonan();
        
        boolean terusHKGHS = false;
        List<Permohonan> senaraiMohon = new ArrayList<Permohonan>();
        senaraiMohon = devService.getListPermohonanByIdSebelumAndKodUrusan(permohonan.getIdPermohonan(),devService.cariKodUrusanHSPendaftaran(context.getPermohonan().getKodUrusan().getKod()));
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
        
        List<Hakmilik> senaraiHakmilikHKGHS = new ArrayList<Hakmilik>();
        
        if(terusHKGHS){

            List<HakmilikPermohonan> senaraiHP = permohonanREG.getSenaraiHakmilik();
            for(int i = 0; i < senaraiHP.size(); i++){                
                HakmilikPermohonan hp = senaraiHP.get(i);
                Hakmilik h = hakmilikDAO.findById(hp.getHakmilik().getIdHakmilik());
                senaraiHakmilikHKGHS.add(h);
                
                kod = dis.findKodUrusan("HKGHS");                
            }
            
            
            //nak dapat kan MH sementara punya idhakmilik
            //buat HKGHS
            
        }
        
        

        try {
            //dis.intRegKelulusan(kod, context.getPengguna(), dis.setListHakmilikByMohonHakmilik(permohonan.getSenaraiHakmilik()), permohonan, "16", "T");
            System.out.println("Kod -->" + kod.getKod());
            if (kod.getKod().equals("HKC") || kod.getKod().equals("HSC")) {
                /*Komen sementara*/
                dis.intRegPermohonan(kod, context.getPengguna(), dis.setListHakmilikByMohonHakmilikPYTN(permohonan.getSenaraiHakmilik()), permohonan, "6", "T", context.getStageName());
                //dis.intRegPermohonan(kod, context.getPengguna(), dis.setListHakmilikByMohonHakmilik(permohonan.getSenaraiHakmilik()), permohonan, "6", "T", context);
            } else if(kod.getKod().equals("HKGHS")){
                dis.intRegPermohonan(kod, context.getPengguna(), senaraiHakmilikHKGHS, permohonan, "6", "T", context.getStageName());
            }else{
                dis.intRegPermohonan(kod, context.getPengguna(), dis.setListHakmilikByMohonHakmilik(permohonan.getSenaraiHakmilik()), permohonan, "6", "T", context.getStageName());
            }

        } catch (Exception ex) {
            Logger.getLogger(NSdevValidator.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }

        return proposedOutcome;

    }

    @Override
    public void afterComplete(StageContext context) {
    }

    @Override
    public String beforePushBack(StageContext context, String proposedOutcome) {
        return proposedOutcome;
    }

    @Override
    public void afterPushBack(StageContext context) {
    }
}
