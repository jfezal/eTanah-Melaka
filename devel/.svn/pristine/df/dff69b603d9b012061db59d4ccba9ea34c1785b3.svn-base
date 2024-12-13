/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.stripes.pengambilan.sek8.validator;

import com.google.inject.Inject;
import etanah.dao.KodNegeriDAO;
import etanah.dao.KodUrusanDAO;
import etanah.dao.PermohonanDAO;
import etanah.model.Hakmilik;
import etanah.model.HakmilikPermohonan;
import etanah.model.InfoAudit;
import etanah.model.KodCawangan;
import etanah.model.KodJenisPengenalan;
import etanah.model.KodNegeri;
import etanah.model.KodUrusan;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.model.PermohonanAduan;
import etanah.model.ambil.PermohonanHakmilikBaru;
import etanah.model.ambil.PermohonanPengambilanHakmilik;
import etanah.ref.pengambilan.sek8a.RefPeringkat;
import etanah.sequence.GeneratorIdPermohonan;
import etanah.service.acq.integrationflow.Sek8aIntegrationFlowService;
import etanah.service.ambil.PengambilanAPTService;
import etanah.service.pengambilan.aduan.AduanService;
import etanah.view.etanahActionBeanContext;
import etanah.workflow.StageContext;
import etanah.workflow.StageListener;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.apache.commons.lang.StringUtils;

/**
 *
 * @author mohd.faidzal
 */
public class SemakBezaLuasAmbilValidator implements StageListener {

    @Inject
    private GeneratorIdPermohonan idGenerator;
    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    KodNegeriDAO kodNegeriDAO;
    @Inject
    KodUrusanDAO kodUrusanDAO;
    @Inject
    private etanah.Configuration conf;
    @Inject
    PengambilanAPTService pengambilanAPTService;
    @Inject
    AduanService aduanService;
    @Inject
    Sek8aIntegrationFlowService sek8aIntegrationFlowService;

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
        Pengguna pengguna = context.getPengguna();
        InfoAudit infoAudit = new InfoAudit();
        infoAudit.setDimasukOleh(context.getPengguna());
        infoAudit.setTarikhMasuk(new Date());
        List<PermohonanPengambilanHakmilik> lphh = pengambilanAPTService.findHakmilikAktifNotTDK(context.getPermohonan().getIdPermohonan());
        boolean initiate = false;
        List<Hakmilik> listHakmilik = new ArrayList<Hakmilik>();
        for (PermohonanPengambilanHakmilik pengambilanHakmilik : lphh) {
            if (pengambilanHakmilik.getFlagBezaLuas().equals("Y")) {
                listHakmilik.add(pengambilanHakmilik.getHakmilikPermohonan().getHakmilik());
                initiate = true;
            }
        }
        if (initiate) {
            Permohonan permohonan = simpanData(pengguna);
            for (Hakmilik ha : listHakmilik) {
                HakmilikPermohonan hp = new HakmilikPermohonan();
                hp.setPermohonan(permohonan);
                hp.setHakmilik(ha);
                hp.setKodUnitLuas(ha.getKodUnitLuas());
                hp.setInfoAudit(infoAudit);
                hp.setCawangan(context.getPengguna().getKodCawangan());
                aduanService.saveHakmilikPermohonan(hp);
            }
            RefPeringkat ref8a = new RefPeringkat();
            sek8aIntegrationFlowService.completeTask(ref8a.WARTA_KELUASAN_SEMULA, permohonan, pengguna);

        }
        return proposedOutcome;
    }

    @Override
    public void afterComplete(StageContext context) {
    }

    @Override
    public String beforePushBack(StageContext context, String proposedOutcome) {
        return "back";
    }

    @Override
    public void afterPushBack(StageContext context) {
    }

    public Permohonan simpanData(Pengguna pengguna) {
        boolean s = false;
        Permohonan mohon = new Permohonan();
        KodCawangan caw = pengguna.getKodCawangan();

        KodUrusan ku = kodUrusanDAO.findById("PWSL");
//
//        KodNegeri kodNegeri = kodNegeriDAO.findById(negeri);
//        if (StringUtils.isNotBlank(kod)) {
//            KodJenisPengenalan kjp = kodJenisPengenalanDAO.findById(kod);
//            if (kjp != null) {
//                mohon.setPenyerahJenisPengenalan(kjp);
//            }
//        }
//
//        if (kodNegeri != null) {
//            mohon.setPenyerahNegeri(kodNegeri);
//
//        }
        mohon.setKodUrusan(ku);
        mohon.setPenyerahNama("Unit Dalaman");
//        mohon.setPenyerahNoPengenalan(noKp);
//        mohon.setPenyerahNoTelefon1(noTel);
//        mohon.setPenyerahPoskod(poskod);
//        mohon.setPenyerahAlamat1(alamat1);
//        mohon.setPenyerahAlamat2(alamat2);
//        mohon.setPenyerahAlamat3(alamat3);
//        mohon.setPenyerahAlamat4(alamat4);
//        mohon.setPenyerahEmail(email);
        mohon.setCawangan(caw);

        s = true;
        return generateMohon(mohon, pengguna);
    }

    public Permohonan generateMohon( Permohonan mohon,Pengguna pengguna) {
        KodCawangan caw = pengguna.getKodCawangan();

        KodUrusan ku = kodUrusanDAO.findById("PWSL");
        String idMohon = idGenerator.generate(conf.getProperty("kodNegeri"), caw, ku);
        mohon.setIdPermohonan(idMohon);
        mohon.setKodUrusan(ku);
        mohon.setCawangan(caw);
        mohon = aduanService.saveMohon(mohon, pengguna);

        System.out.println(" idPermohonan dalam generateMohon ++> " + idMohon);
        return mohon;

    }// end method

}
