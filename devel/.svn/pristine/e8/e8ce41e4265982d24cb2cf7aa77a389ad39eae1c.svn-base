/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package etanah.hasil.workflow;

import com.google.inject.Inject;
import etanah.dao.DasarTuntutanCukaiDAO;
import etanah.dao.DasarTuntutanCukaiHakmilikDAO;
import etanah.dao.HakmilikPermohonanDAO;
import etanah.dao.KodRujukanDAO;
import etanah.dao.KodUrusanDAO;
import etanah.dao.PermohonanDAO;
import etanah.model.DasarTuntutanCukaiHakmilik;
import etanah.model.DasarTuntutanNotis;
import etanah.model.Hakmilik;
import etanah.model.HakmilikPermohonan;
import etanah.model.InfoAudit;
import etanah.model.KodRujukan;
import etanah.model.KodUrusan;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.model.PermohonanRujukanLuar;
import etanah.service.LaporanTanahService;
import etanah.view.stripes.hasil.GenerateIdPermohonanWorkflow;
import etanah.view.stripes.hasil.NotisPeringatan6AManager;
import etanah.workflow.StageContext;
import etanah.workflow.StageListener;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

/**
 *
 * @author abu.mansur
 */
public class ProsesPenyediaanEndorsan implements StageListener{
    private static final Logger LOG = Logger.getLogger(ProsesPenyediaanEndorsan.class);
    @Inject
    GenerateIdPermohonanWorkflow gipw;
    @Inject
    DasarTuntutanCukaiHakmilikDAO dasarTuntutanCukaiHakmilikDAO;
    @Inject
    DasarTuntutanCukaiDAO dasarTuntutanCukaiDAO;
    @Inject
    HakmilikPermohonanDAO hakmilikPermohonanDAO;
    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    KodUrusanDAO kodUrusanDAO;
    @Inject
    KodRujukanDAO kodRujukanDAO;
    @Inject
    NotisPeringatan6AManager npmgr;
    @Inject
    LaporanTanahService serviceMgr;

    @Override
    public boolean beforeStart(StageContext context) {
//        throw new UnsupportedOperationException("Not supported yet.");
        return true;
    }

    @Override
    public boolean beforeGenerateReports(StageContext ctx) {
//        throw new UnsupportedOperationException("Not supported yet.");
        return true;
    }

    @Override
    public void onGenerateReports(StageContext context) {
//        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public String beforeComplete(StageContext context, String proposedOutcome) {
        LOG.info("ProsesPenguranganDenda::beforeComplete()");
        Pengguna pengguna = context.getPengguna();
        Permohonan permohonan = context.getPermohonan();
        Hakmilik hm = new Hakmilik();
        KodUrusan ku = new KodUrusan();
        String Semak = "SM";
        String Lulus = "L";
        String kodUrusan1 = "N6A";  // for endorsan (N6A) in Modul Pendaftaran
        String kodUrusan2 = "N8A";  // for endorsan (N8A) in Modul Pendaftaran
        String kodUrusanB1 = "N6AB";  // for pembatalan endorsan (N6AB) in Modul Pendaftaran
        String kodUrusanB2 = "N8AB";  // for pembatalan endorsan (N8AB) in Modul Pendaftaran
        if(Semak.equals(proposedOutcome) || Lulus.equals(proposedOutcome)){
            if("ED6A".equals(permohonan.getKodUrusan().getKod()))
                ku = kodUrusanDAO.findById(kodUrusan1);
            if("ED8A".equals(permohonan.getKodUrusan().getKod()))
                ku = kodUrusanDAO.findById(kodUrusan2);
            if("ED6AB".equals(permohonan.getKodUrusan().getKod()))
                ku = kodUrusanDAO.findById(kodUrusanB1);
            if("ED8AB".equals(permohonan.getKodUrusan().getKod()))
                ku = kodUrusanDAO.findById(kodUrusanB2);
            String[] tname = {"permohonan"};
            Object[] tvalue = {permohonan};
            List<HakmilikPermohonan> senaraiHakmilikPermohonan = permohonan.getSenaraiHakmilik();
            if(!senaraiHakmilikPermohonan.isEmpty()){
                List<Hakmilik> senaraiHakmilik = new ArrayList<Hakmilik>();
                for (HakmilikPermohonan hp : senaraiHakmilikPermohonan) {
                    senaraiHakmilik.add(hp.getHakmilik());
                    LOG.debug("Hakmilik :"+hp.getHakmilik().getIdHakmilik());
                    hm = hp.getHakmilik();
                }
                LOG.debug("senaraiHakmilik.size :"+senaraiHakmilik.size());
                Permohonan permohonanBaru = gipw.genWorkflowIdPermohonan(ku, pengguna, senaraiHakmilik, permohonan);
                String noFailPendaftaran = "";              // no fail utk pendaftaran
                if(permohonanBaru != null){
                    KodRujukan kr = new KodRujukan();
                    PermohonanRujukanLuar prlDaftar = new PermohonanRujukanLuar();
                    InfoAudit info = new InfoAudit();
                    info.setDimasukOleh(pengguna);
                    info.setTarikhMasuk(new java.util.Date());
                    info.setDiKemaskiniOleh(pengguna);
                    info.setTarikhKemaskini(new java.util.Date());
//                    Permohonan permohonanBaru = permohonanDAO.findById(idPermohonan);
                    String[] name = {"hakmilik"};
                    Object[] value = {hm};
                    List<DasarTuntutanCukaiHakmilik> senaraiDTCH = dasarTuntutanCukaiHakmilikDAO.findByEqualCriterias(name, value, null);
                    for (DasarTuntutanCukaiHakmilik dtch : senaraiDTCH) {
                        if("ED6A".equals(permohonan.getKodUrusan().getKod())){
                            noFailPendaftaran = dtch.getNoRujukan();
                            dtch.setPerserahan6A(permohonanBaru);
                            kr = kodRujukanDAO.findById("FL"); // FL = Fail
                            prlDaftar.setKodRujukan(kr);
                            for (DasarTuntutanNotis dtn : dtch.getSenaraiNotis()) {
                                if("N6A".equals(dtn.getNotis().getKod())){ // perlu detail notis 6A
                                    prlDaftar.setTarikhDisampai(dtn.getTarikhTerima());
                                    prlDaftar.setTarikhRujukan(dtn.getTarikhNotis());
                                }
                                if("NG".equals(dtn.getNotis().getKod())){ // perlu no. warta Notis Gantian
                                    if(dtn.getNoRujukan() != null)
                                        prlDaftar.setNoRujukan(dtn.getNoRujukan());
                                }
                            }
                            prlDaftar.setCatatan("T"); // T = flag untuk notis disampaikan, G = flag untuk notis tidak disampaikan
                        }
                        if("ED8A".equals(permohonan.getKodUrusan().getKod())){
                            noFailPendaftaran = dtch.getNoRujukan();
                            dtch.setPerserahan8A(permohonanBaru);
                            kr = kodRujukanDAO.findById("FL"); // FL = Fail
                            prlDaftar.setKodRujukan(kr);
                            for (DasarTuntutanNotis dtn : dtch.getSenaraiNotis()) {
                                if("WR".equals(dtn.getNotis().getKod())){ // perlu detail warta
                                    prlDaftar.setNoRujukan(dtn.getNoRujukan());
                                    prlDaftar.setTarikhRujukan(dtn.getTarikhNotis());
                                }
                            }
                        }
                        if("ED6AB".equals(permohonan.getKodUrusan().getKod())){
                            noFailPendaftaran = dtch.getNoRujukan();
                            dtch.setPerserahanBatal6A(permohonanBaru);
                            kr = kodRujukanDAO.findById("FL"); // FL = Fail
                            prlDaftar.setKodRujukan(kr);
                        }
                        if("ED8AB".equals(permohonan.getKodUrusan().getKod())){
                            noFailPendaftaran = dtch.getNoRujukan();
                            dtch.setPerserahanBatal8A(permohonanBaru);
                            kr = kodRujukanDAO.findById("FL"); // FL = Fail
                            prlDaftar.setKodRujukan(kr);
                        }
                        prlDaftar.setCawangan(permohonan.getCawangan());
                        LOG.debug("permohonanDaftar.idMohon :"+permohonanBaru.getIdPermohonan());
                        prlDaftar.setPermohonan(permohonanBaru);
//                        prlDaftar.setNoFail(permohonan.getIdPermohonan());// no fail lama
                        prlDaftar.setNoFail(noFailPendaftaran);                 // no fail baru
                        prlDaftar.setInfoAudit(info);
                        prlDaftar.setHakmilik(hm);
                        serviceMgr.simpanRujukanLuar(prlDaftar); //create new record for new permohonan in modul pendaftaran
                        if(npmgr.updateDTCH(dtch,pengguna)){
                            context.addMessage(" - Permohonan di Modul Pendaftaran telah berjaya diwujudkan.");
                            LOG.info("Permohonan telah berjaya diwujudkan. Berjaya Update Table");
                        }else{
                            context.addMessage("Permohonan TIDAK berjaya diwujudkan.");
                            LOG.info("Permohonan TIDAK berjaya diwujudkan. Gagal update Table");
                            return null;
                        }
                    }                    
                }else{
                    context.addMessage("Permohonan TIDAK berjaya diwujudkan.");
                    LOG.info("Permohonan TIDAK berjaya diwujudkan. IdPermohonan tidak berjaya diwujudkan");
                    return null;
                }
            }else{
                context.addMessage("Tiada hakmilik dikenalpastikan.");
                LOG.error("senaraiHakmilikPermohonan.isEmpty");
                return null;
            }
        }else{
            LOG.error(permohonan.getIdPermohonan()+" - Keputusan selain daripada Disemak/Lulus.");
        }
        return proposedOutcome;
    }

    @Override
    public void afterComplete(StageContext context) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

     @Override
    public void afterPushBack(StageContext context) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public String beforePushBack(StageContext context, String proposedOutcome) {
//        return proposedOutcome;
        return "back";
    }
}
