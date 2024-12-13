package etanah.view.strata.validator;

import com.google.inject.Inject;
import etanah.dao.PermohonanDAO;
import etanah.model.Permohonan;
import etanah.model.Pengguna;
import etanah.model.InfoAudit;
import etanah.workflow.StageContext;
import etanah.service.BPelService;
import etanah.model.IntegrasiPermohonanButir;
import etanah.dao.IntegrasiPermohonanDAO;
import etanah.model.IntegrasiPermohonan;
import etanah.model.KodUrusan;
import etanah.model.Dokumen;
import etanah.service.JupemService;
import etanah.workflow.StageListener;
import java.io.*;
import java.util.*;
//import javax.jms.Session;

public class KemasukanPermohonanValidator implements StageListener {

    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    IntegrasiPermohonanDAO integrasiPermohonanDAO;
    String stageId;
    IntegrasiPermohonan integrasiPermohonan;
    KodUrusan mohonKodUrusan;
    IntegrasiPermohonanButir integrasiPermohonanButir;
    @Inject
    JupemService service;
    private List<IntegrasiPermohonanButir> senaraiButiran;
//    @Inject
//    protected com.google.inject.Provider<Session> sessionProvider;
    @Inject
    etanah.Configuration conf;
    private KodUrusan kodUrusan;
    String outputPath;
    private List<Dokumen> list2;
    String fname;

    public void copyfile(StageContext ctx) {

        String idPermohonan = ctx.getPermohonan().getIdPermohonan();
        Permohonan p = permohonanDAO.findById(idPermohonan);

        Pengguna peng = ctx.getPengguna();
        InfoAudit ia = peng.getInfoAudit();
        BPelService serviceBpel = new BPelService();
        stageId = ctx.getStageName();

//        if (StringUtils.isNotBlank(taskId)) {
//            Task t = null;
//            t = serviceBpel.getTaskFromBPel(taskId, peng);
//            stageId = t.getSystemAttributes().getStage();
//        }
        System.out.println(stageId);
        //-------------------------------//
        List<Dokumen> list3 = service.findDokumenList(idPermohonan);
        //-------------------------------//

        if (integrasiPermohonan == null) {

            integrasiPermohonan = new IntegrasiPermohonan();
            mohonKodUrusan = p.getKodUrusan();
            integrasiPermohonan.setKodUrusan(mohonKodUrusan);
            integrasiPermohonan.setPermohonan(p);
            integrasiPermohonan.setIdAliran(stageId);
            if (ia == null) {
                ia = new InfoAudit();
                ia.setDimasukOleh(peng);
                ia.setTarikhMasuk(new java.util.Date());
                integrasiPermohonan.setInfoAudit(ia);
            } else {
                ia.setDiKemaskiniOleh(peng);
                ia.setTarikhKemaskini(new java.util.Date());
                integrasiPermohonan.setInfoAudit(ia);
            }
            service.simpanInIntegrasiPermohonan(integrasiPermohonan);
        } else {

            mohonKodUrusan = p.getKodUrusan();
            integrasiPermohonan.setKodUrusan(mohonKodUrusan);
            if (ia == null) {
                ia = new InfoAudit();
                ia.setDimasukOleh(peng);
                ia.setTarikhMasuk(new java.util.Date());
                integrasiPermohonan.setInfoAudit(ia);
            } else {
                ia.setDiKemaskiniOleh(peng);
                ia.setTarikhKemaskini(new java.util.Date());
                integrasiPermohonan.setInfoAudit(ia);
            }

            service.simpanInIntegrasiPermohonan(integrasiPermohonan);

        }
        String sourcePath = conf.getProperty("document.path");
        String destPath = conf.getProperty("gis.outbound.path");
        //String destPath = conf.getProperty("document.path");
        destPath = destPath + File.separator + p.getIdPermohonan() + stageId;
        File f = new File(destPath);
        f.mkdir();
        integrasiPermohonan = integrasiPermohonanDAO.findById(integrasiPermohonan.getIdInteg());
        if (integrasiPermohonanButir == null) {

            integrasiPermohonanButir = new IntegrasiPermohonanButir();
            integrasiPermohonanButir.setIntegrasiPermohonan(integrasiPermohonan);
            integrasiPermohonanButir.setNamaFolderHantar(destPath);
            if (ia == null) {
                ia = new InfoAudit();
                ia.setDimasukOleh(peng);
                ia.setTarikhMasuk(new java.util.Date());
                integrasiPermohonanButir.setInfoAudit(ia);
            } else {
                ia.setDiKemaskiniOleh(peng);
                ia.setTarikhKemaskini(new java.util.Date());
                integrasiPermohonanButir.setInfoAudit(ia);
            }
            service.simpanIntegrasiPermohonanButir(integrasiPermohonanButir);
        } else {

            integrasiPermohonanButir.setIntegrasiPermohonan(integrasiPermohonan);
            integrasiPermohonanButir.setNamaFolderHantar(destPath);
            if (ia == null) {
                ia = new InfoAudit();
                ia.setDimasukOleh(peng);
                ia.setTarikhMasuk(new java.util.Date());
                integrasiPermohonanButir.setInfoAudit(ia);
            } else {
                ia.setDiKemaskiniOleh(peng);
                ia.setTarikhKemaskini(new java.util.Date());
                integrasiPermohonanButir.setInfoAudit(ia);
            }

            service.simpanIntegrasiPermohonanButir(integrasiPermohonanButir);

        }

        String outpath;
        InputStream in = null;
        System.out.println(list3.get(0).getNamaFizikal());
        String fname;

        try {
            for (int i = 0; i < list3.size(); i++) {
                outpath = list3.get(i).getNamaFizikal();
                fname = outpath.substring(outpath.lastIndexOf(File.separator) + 1);
                in = new FileInputStream(sourcePath + File.separator + outpath);

            }

        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        }

        OutputStream out = null;

        try {
            for (int i = 0; i < list3.size(); i++) {
                outpath = list3.get(i).getNamaFizikal();
                fname = outpath.substring(outpath.lastIndexOf(File.separator) + 1);
                out = new FileOutputStream(destPath + File.separator + fname);
            }

        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        }
        // Transfer bytes from in to out
        byte[] buf = new byte[1024];
        int len;
        try {
            while ((len = in.read(buf)) > 0) {
                out.write(buf, 0, len);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        try {
            in.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        try {
            out.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }

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
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public String beforeComplete(StageContext context, String proposedOutcome) {
        copyfile(context);
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
        return proposedOutcome;
    }
}
