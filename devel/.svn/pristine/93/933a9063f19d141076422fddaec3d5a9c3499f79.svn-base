/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.service.acq.service;

import com.Ostermiller.util.Base64;
import com.google.inject.Inject;
import com.google.inject.Injector;
import etanah.etapp.etappclient.form.DokumenForm;
import etanah.etapp.etappclient.form.HakmilikForm;
import etanah.etapp.etappclient.form.PermohonanForm;
import etanah.model.HakmilikPermohonan;
import etanah.model.HakmilikUrusan;
import etanah.model.InfoMmkn;
import etanah.model.KandunganFolder;
import etanah.model.Permohonan;
import etanah.ref.pengambilan.myetapp.RefPeringkat;
import etanah.service.acq.integrationflow.Sek4IntegrationFlowService;
import etanah.view.etanahContextListener;
import etanah.view.etapp.HakmilikEndorsanForm;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author zipzap
 */
public class MyEtappMapDataService {

    private static Injector injector = etanahContextListener.getInjector();
    @Inject
    private etanah.Configuration conf;
    @Inject
    Sek4IntegrationFlowService Sek4IntegrationFlowService;
    @Inject
    MyEtappMapDataService myEtappMapDataService;
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    SimpleDateFormat toTime = new SimpleDateFormat("dd/MM/yyyy");

    public PermohonanForm setPermohonanValue(Permohonan p, String kod) {
        RefPeringkat ref = new RefPeringkat();
        PermohonanForm f = new PermohonanForm();
        f.setCatatan(StringUtils.isEmpty(p.getCatatan()) ? null : p.getCatatan());
//        f.setCatatanKeputusan("-");
        f.setJenis("-");
//        f.setKeputusan("-");
//        f.setNoFail(p.getIdPermohonan());
        f.setNoJilid("-");
//        f.setNoPU("-");
//        f.setNoRujukan("-");
//        f.setNoWarta("-");
        f.setTarikh(sdf.format(new Date()));
        f.setTarikhKeputusan(sdf.format(new Date()));
        f.setTarikhWarta(sdf.format(new Date()));
        if (ref.SEK4_HANTAR_BORANGA.equals(kod)) {
            InfoMmkn info = Sek4IntegrationFlowService.findMMKByKpsn(p.getIdPermohonan(), "ETHMK");
            f.setKeputusan(getValueFromKpsn(info.getKodKpsn()));
            f.setTarikhKeputusan(sdf.format(info.getTrhSah()));
//            f.setNoRujukan(info.getNoFailMmkn());
            f.setCatatanKeputusan("-");
        } else if (ref.SEK4_HANTAR_BORANGB.equals(kod)) {
            f.setCatatan(kod);

        } else if (ref.SEK8_HANTAR_BORANGC.equals(kod)) {
            InfoMmkn info = Sek4IntegrationFlowService.findMMKByKpsn(p.getIdPermohonan(), "ET8MK");
            f.setKeputusan(getValueFromKpsn(info.getKodKpsn()));
            f.setTarikhKeputusan(sdf.format(info.getTrhSah()));
            f.setNoRujukan(info.getNoFailMmkn());
            f.setCatatanKeputusan("-");

        } else if (ref.SEK8_HANTAR_BD.equals(kod)) {
            f.setCatatan(kod);

        } else if (ref.SEK8_HANTAR_BK.equals(kod)) {
            f.setCatatan(kod);

        } else if (ref.SEK8_HANTAR_SPU.equals(kod)) {
            f.setCatatan(kod);

        } else if (ref.SEK8_HANTAR_HS.equals(kod)) {
            f.setCatatan(kod);

        }

        return f;
    }

    public List<HakmilikForm> setHakmilikValue(Permohonan p, String kod) {
        RefPeringkat ref = new RefPeringkat();
        List<HakmilikForm> f = new ArrayList<HakmilikForm>();
        if (ref.SEK8_HANTAR_BD.equals(kod)||ref.SEK8_HANTAR_BK.equals(kod)) {
            String[] u = new String[1];
            if (ref.SEK8_HANTAR_BD.equals(kod)) {
                u = new String[1];
                u[0] = "ABT-D";
            } else if (ref.SEK8_HANTAR_BK.equals(kod)) {
                u = new String[2];
                u[0] = "ABTKB";
                u[1] = "ABT-K";
            }
            List<HakmilikEndorsanForm> listHakmilikEndos = myEtappMapDataService.findListHakmilikEndors(p.getIdPermohonan(), u);
            for (HakmilikEndorsanForm hp : listHakmilikEndos) {
                HakmilikForm form = new HakmilikForm();
                form.setIdHakmilik(hp.getHakmilik().getIdHakmilik());
                form.setKodDaerah(hp.getHakmilik().getDaerah().getKod());
                form.setKodHakmilik(hp.getHakmilik().getKodHakmilik().getKod());
                form.setKodLot(hp.getHakmilik().getLot().getKod());
                form.setNoLot(hp.getHakmilik().getNoLot());
                form.setKodMukim(hp.getHakmilik().getBandarPekanMukim().getbandarPekanMukim() + "");
                form.setKodNegeri("04");
                form.setTarikhEndorsan(hp.getTarikhDaftar());
                form.setMasaEndorsan(hp.getMasaDaftar());
                form.setNoHakmilik(hp.getHakmilik().getNoHakmilik());
                form.setNoPerserahan(hp.getIdPerserahan());
                f.add(form);
            }
        } else {
            for (HakmilikPermohonan hp : p.getSenaraiHakmilik()) {
                HakmilikForm form = new HakmilikForm();
                form.setIdHakmilik(hp.getHakmilik().getIdHakmilik());
                form.setKodDaerah(hp.getHakmilik().getDaerah().getKod());
                form.setKodHakmilik(hp.getHakmilik().getKodHakmilik().getKod());
                form.setKodLot(hp.getHakmilik().getLot().getKod());
                form.setNoLot(hp.getHakmilik().getNoLot());
                form.setKodLuasSambungan("");
                form.setKodMukim(hp.getHakmilik().getBandarPekanMukim().getbandarPekanMukim() + "");
                form.setKodNegeri("04");
                form.setLuasSambungan("");
                form.setMasaEndorsan("");
                form.setNoHakmilik(hp.getHakmilik().getNoHakmilik());
                form.setNoPerserahan("");
                if (hp.getHakmilik().getSeksyen() != null) {
                    form.setNoSeksyen(hp.getHakmilik().getSeksyen().getKod() + "");
                }
                form.setTarikhEndorsan("");
                if (ref.SEK4_HANTAR_BORANGA.equals(kod)) {
                } else if (ref.SEK4_HANTAR_BORANGB.equals(kod)) {

                }
                f.add(form);
            }
        }

        return f;
    }

    public List<DokumenForm> setDokumenValue(Permohonan p, String kod) {
        RefPeringkat ref = new RefPeringkat();
        List<DokumenForm> f = new ArrayList<DokumenForm>();
        String[] kodDokumen = null;
        if (ref.SEK4_HANTAR_BORANGA.equals(kod)) {
            kodDokumen = new String[2];
            kodDokumen[0] = "A";
            kodDokumen[1] = "SIIA";
            f = setDokumenListByKod(p, kodDokumen);
        } else if (ref.SEK4_HANTAR_BORANGB.equals(kod)) {
            kodDokumen = new String[2];
            kodDokumen[0] = "B";
            kodDokumen[1] = "SIIB";
            f = setDokumenListByKod(p, kodDokumen);

        } else if (ref.SEK8_HANTAR_BORANGC.equals(kod)) {
            kodDokumen = new String[3];
            kodDokumen[0] = "C";
            kodDokumen[1] = "D";
            kodDokumen[2] = "SIID";
            f = setDokumenListByKod(p, kodDokumen);
        } else if (ref.SEK8_HANTAR_BD.equals(kod)) {
            kodDokumen = new String[3];
            kodDokumen[0] = "D";
            kodDokumen[1] = "SIID";
            kodDokumen[2] = "SCR";
            f = setDokumenListByKod(p, kodDokumen);
        } else if (ref.SEK8_HANTAR_BK.equals(kod)) {
            kodDokumen = new String[3];
            kodDokumen[0] = "K";
            kodDokumen[1] = "SIIK";
            kodDokumen[2] = "SCR";

            f = setDokumenListByKod(p, kodDokumen);
        } else if (ref.SEK8_HANTAR_SPU.equals(kod)) {
            kodDokumen = new String[2];
            kodDokumen[0] = "SPU";
            kodDokumen[1] = "SIPU";
            f = setDokumenListByKod(p, kodDokumen);
        } else if (ref.SEK8_HANTAR_HS.equals(kod)) {
            kodDokumen = new String[1];
            kodDokumen[0] = "A";
            f = setDokumenListByKod(p, kodDokumen);

        }
        return f;
    }

    private List<DokumenForm> setDokumenListByKod(Permohonan p, String[] kodDokumen) {
        List<DokumenForm> f = new ArrayList<DokumenForm>();
        List<KandunganFolder> listKd = findDokumenByKodDokumen(p, kodDokumen);
        for (KandunganFolder kd : listKd) {
            kd.getDokumen();
            DokumenForm form = new DokumenForm();
            form.setNamaDokumen(kd.getDokumen().getKodDokumen().getKod());
            form.setJenisMime(kd.getDokumen().getFormat());
            form.setJenisDokumen(kd.getDokumen().getKodDokumen().getKodMyEtapp());
            String docPath = conf.getProperty("document.path");
            String fn = kd.getDokumen().getNamaFizikal();
            System.out.println("fn ::" + fn);
            File ff = new File(docPath + (docPath.endsWith(File.separator) ? "" : File.separator) + fn);
            form.setDoContent(encodeFileToBase64(ff));
            f.add(form);
        }
        return f;
    }

    private List<KandunganFolder> findDokumenByKodDokumen(Permohonan p, String[] kodDokumen) {
        String query = "SELECT a FROM etanah.model.KandunganFolder a,etanah.model.Permohonan p"
                + " where a.folder.folderId = p.folderDokumen.folderId "
                + "and a.dokumen.kodDokumen.kod in (:kodDokumen) "
                + "and p.idPermohonan = :idPermohonan";
        Session session = injector.getProvider(Session.class).get();
        Query q = session.createQuery(query);
        q.setString("idPermohonan", p.getIdPermohonan());
        q.setParameterList("kodDokumen", kodDokumen);
        return q.list();
    }

    private static String encodeFileToBase64(File file) {
        try {
            InputStream inputStream = new FileInputStream(file.getAbsolutePath());
            byte[] fileContent = IOUtils.toByteArray(inputStream);
//            byte[] fileContent = Files.readAllBytes(file.toPath());
            return Base64.encodeToString(fileContent);
        } catch (IOException e) {
            throw new IllegalStateException("could not read file " + file, e);
        }
    }

    private String getValueFromKpsn(String kodKpsn) {
        String v = "";
        if (kodKpsn.equals("Y")) {
            v = "1";
        } else if (kodKpsn.equals("T")) {
            v = "2";
        } else if (kodKpsn.equals("TG")) {
            v = "3";
        }
        return v;
    }

    public List<HakmilikEndorsanForm> findListHakmilikEndors(String idPermohonan, String[] kod_urusan) {
        List<HakmilikEndorsanForm> list = new ArrayList<HakmilikEndorsanForm>();
        String query = "SELECT p FROM etanah.model.Permohonan p"
                + " where p.permohonanSebelum.idPermohonan = :idPermohonan "
                + "and p.kodUrusan.kod in (:kod_urusan)";
        Session session = injector.getProvider(Session.class).get();
        Query q = session.createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        q.setParameterList("kod_urusan", kod_urusan);
        List<Permohonan> listP = q.list();
        if (!listP.isEmpty()) {
            for (Permohonan mohon : listP) {
                String query1 = "SELECT hp FROM etanah.model.HakmilikPermohonan hp"
                        + " where hp.permohonan.idPermohonan = :idPermohonan ";
                Session session1 = injector.getProvider(Session.class).get();
                Query q1 = session1.createQuery(query1);
                q1.setString("idPermohonan", mohon.getIdPermohonan());
                List<HakmilikPermohonan> listHp = q1.list();
                if (!listHp.isEmpty()) {
                    for (HakmilikPermohonan hp : listHp) {
                        HakmilikEndorsanForm form = new HakmilikEndorsanForm();
                        form.setHakmilik(hp.getHakmilik());
                        form.setIdPerserahan(hp.getPermohonan().getIdPermohonan());
                        HakmilikUrusan hu = findMasaDaftarIdMohon(hp.getPermohonan().getIdPermohonan(), hp.getHakmilik().getIdHakmilik());
                        if (hu != null) {
                            form.setTarikhDaftar(sdf.format(hu.getTarikhDaftar()));
                            form.setMasaDaftar(toTime.format(hu.getTarikhDaftar()));
                        }
                        list.add(form);
                    }
                }
            }
        }

        return list;
    }

    public HakmilikUrusan findMasaDaftarIdMohon(String idPermohonan, String idHakmilik) {
        String query1 = "SELECT p FROM etanah.model.HakmilikUrusan p"
                + " where p.idPerserahan = :idPermohonan "
                + "and p.hakmilik.idHakmilik = :idHakmilik";
        Session session1 = injector.getProvider(Session.class).get();
        Query q1 = session1.createQuery(query1);
        q1.setString("idPermohonan", idPermohonan);
        q1.setString("idHakmilik", idHakmilik);
        return (HakmilikUrusan) q1.uniqueResult();

    }

}
