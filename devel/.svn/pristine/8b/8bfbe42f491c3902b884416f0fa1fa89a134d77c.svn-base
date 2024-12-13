/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.daftar.carian.ws;

import com.google.inject.Injector;
import etanah.Configuration;
import etanah.dao.CarianHakmilikDAO;
import etanah.dao.DokumenKewanganDAO;
import etanah.dao.HakmilikDAO;
import etanah.dao.KodUrusanDAO;
import etanah.dao.PenggunaDAO;
import etanah.dao.PermohonanCarianDAO;
import etanah.model.CarianHakmilik;
import etanah.model.DokumenKewangan;
import etanah.model.Hakmilik;
import etanah.model.InfoAudit;
import etanah.model.KodUrusan;
import etanah.model.Pengguna;
import etanah.model.PermohonanCarian;
import etanah.report.ReportUtil;
import etanah.sequence.GeneratorIdPerserahan;
import etanah.view.etanahContextListener;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author fikri
 */
public class CarianWebServices implements ICarianServices {

    private static final String REPORT_NAME = "regCatatanCarianHM.rdf";

    private static final String REPORT_NAME_STR = "regCatatanCarianHMSB4K.rdf";

    private static Injector injector = etanahContextListener.getInjector();

    private PermohonanCarianDAO permohonanCarianDAO = injector.getInstance(PermohonanCarianDAO.class);

    private PenggunaDAO penggunaDAO = injector.getInstance(PenggunaDAO.class);

    private HakmilikDAO hakmilikDAO = injector.getInstance(HakmilikDAO.class);

    private CarianHakmilikDAO carianHakmilikDAO = injector.getInstance(CarianHakmilikDAO.class);

    private ReportUtil reportUtil = injector.getInstance(ReportUtil.class);

    private KodUrusanDAO kodUrusanDAO = injector.getInstance(KodUrusanDAO.class);

    private DokumenKewanganDAO dokumenKewanganDAO = injector.getInstance(DokumenKewanganDAO.class);

    private GeneratorIdPerserahan idPerserahanGenerator = injector.getInstance(GeneratorIdPerserahan.class);

    private static Logger LOGGER = Logger.getLogger(CarianWebServices.class);

    private etanah.Configuration conf = injector.getInstance(Configuration.class);

    public PermohonanCarian doInsertCarian(String[] idHakmiliks, String idPermohonan, Pengguna pengguna, KodUrusan kodUrusan, DokumenKewangan noResit) {

        Session sess = injector.getProvider(Session.class).get();

        Transaction tx = sess.beginTransaction();

        List<CarianHakmilik> senarai = new ArrayList<CarianHakmilik>();

        InfoAudit ia = new InfoAudit();
        ia.setTarikhMasuk(new Date());
        ia.setDimasukOleh(pengguna);

        PermohonanCarian p = new PermohonanCarian();
        p.setIdCarian(idPermohonan);
        p.setInfoAudit(ia);
        p.setUrusan(kodUrusan);
        p.setCawangan(pengguna.getKodCawangan());
        if (noResit != null) {

            p.setResit(noResit);
            LOGGER.debug(noResit.getIdDokumenKewangan());
        }

        permohonanCarianDAO.save(p);

        for (String idHakmilik : idHakmiliks) {
            Hakmilik hm = hakmilikDAO.findById(idHakmilik);
            CarianHakmilik ch = new CarianHakmilik();
            
            if (hm == null) {
                throw new RuntimeException("Hakmilik tidak dikenali.");
            }
            
            if(StringUtils.isNotBlank(hm.getIdHakmilikInduk())){
            ch.setIdHakmilik(hm.getIdHakmilikInduk());
            ch.setPermohonanCarian(p);
            ch.setKodUrusan(kodUrusan);
            ch.setCawangan(pengguna.getKodCawangan());
            ch.setNoBangunan(hm.getNoBangunan());
            ch.setNoTingkat(hm.getNoTingkat());
            ch.setNoPetak(hm.getNoPetak());
            ch.setInfoAudit(ia);
            carianHakmilikDAO.save(ch);
            }else{
            ch.setIdHakmilik(hm.getIdHakmilik());
            ch.setPermohonanCarian(p);
            ch.setKodUrusan(kodUrusan);
            ch.setCawangan(pengguna.getKodCawangan());
            ch.setInfoAudit(ia);
            carianHakmilikDAO.save(ch);
            }
            
            senarai.add(ch);
        }

        p.setSenaraiHakmilik(senarai);
        if (noResit != null) {

            p.setResit(noResit);
            LOGGER.debug(noResit.getIdDokumenKewangan());
        }
        tx.commit();
        return p;
    }

    private byte[] generateSijil(String idHakmilik, String idPermohonan, String type) {
        String reportName;
        if ("STR".equals(type)) {
            reportName = REPORT_NAME;
        } else {
            reportName = REPORT_NAME_STR;
        }
        return reportUtil.generateReport(reportName,
                new String[]{"p_id_hakmilik", "p_carian"},
                new String[]{idHakmilik, idPermohonan},
                null, null);
    }

    /**
     * Web service operation
     */
    @Override
    public List<byte[]> getSijil(
            String[] idHakmiliks,
            String kodNegeri,
            String kodUrusan,
            String idPengguna) throws RuntimeException {

        LOGGER.debug("invoke get sijil..");

        Pengguna pengguna = penggunaDAO.findById(idPengguna);

        LOGGER.debug("after..");

        if (pengguna == null) {
            throw new RuntimeException("Pengguna Tidak Dikenali.");
        }

        KodUrusan urusan = kodUrusanDAO.findById(kodUrusan);

        //TODO validate id hakmilik
        LOGGER.debug("kod urusan " + urusan.getKod());

        if (urusan == null) {
            throw new RuntimeException("Urusan tidak dikenali.");
        }

        String idPermohonan = idPerserahanGenerator.generate(
                conf.getProperty("kodNegeri"), pengguna.getKodCawangan(), urusan);

        PermohonanCarian p = doInsertCarian(idHakmiliks, idPermohonan, pengguna, urusan, null);

        if (p == null) {
            throw new RuntimeException("Terdapat masalah teknikal.");
        }

        List<byte[]> pdfs = new ArrayList<byte[]>();

        int count = 0;

        String type = "";
        if ("CSHS".equals(urusan)) {
            type = "STR";
        } else {
            type = "LND";
        }

        for (String idHakmilik : idHakmiliks) {

            LOGGER.debug("id hakmilik = " + idHakmilik);

            pdfs.add(generateSijil(idHakmilik, idPermohonan, type));

            LOGGER.debug("pdfs[" + count + "]");
            count++;
        }

        LOGGER.debug("returning..");
        return pdfs;
    }

    @Override
    public List<byte[]> getSijil(
            String[] idHakmiliks,
            String kodNegeri,
            String kodUrusan,
            String idPengguna,
            String noResit) throws RuntimeException {

        LOGGER.debug("invoke get sijil..");

        Pengguna pengguna = penggunaDAO.findById(idPengguna);

        LOGGER.debug("after..");

        if (pengguna == null) {
            throw new RuntimeException("Pengguna Tidak Dikenali.");
        }

        KodUrusan urusan = kodUrusanDAO.findById(kodUrusan);

        //TODO validate id hakmilik
        LOGGER.debug("kod urusan " + urusan.getKod());

        if (urusan == null) {
            throw new RuntimeException("Urusan tidak dikenali.");
        }

        String idPermohonan = idPerserahanGenerator.generate(
                conf.getProperty("kodNegeri"), pengguna.getKodCawangan(), urusan);

        DokumenKewangan dokW = dokumenKewanganDAO.findById(noResit);
        LOGGER.debug(dokW.getIdDokumenKewangan());
        PermohonanCarian p = doInsertCarian(idHakmiliks, idPermohonan, pengguna, urusan, dokW);

        if (p == null) {
            throw new RuntimeException("Terdapat masalah teknikal.");
        }

        List<byte[]> pdfs = new ArrayList<byte[]>();

        int count = 0;

        String type = "";
        if ("CSHS".equals(urusan)) {
            type = "STR";
        } else {
            type = "LND";
        }
        for (String idHakmilik : idHakmiliks) {

            LOGGER.debug("id hakmilik = " + idHakmilik);

            pdfs.add(generateSijil(idHakmilik, idPermohonan, type));

            LOGGER.debug("pdfs[" + count + "]");
            count++;
        }

        LOGGER.debug("returning..");
        return pdfs;
    }

}
