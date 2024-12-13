/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.service.ambil;

import com.google.inject.Inject;
import etanah.dao.PermohonanDAO;
import etanah.model.HakmilikPermohonan;
import etanah.model.Permohonan;
import etanah.model.ambil.PermohonanPengambilanHakmilik;
import etanah.view.stripes.pengambilan.share.form.PengambilanSek4Form;
import etanah.view.stripes.pengambilan.share.form.PengambilanSek8Form;
import etanah.view.stripes.pengambilan.share.form.SenaraiPermohonanACQForm;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author mohd.faidzal
 */
public class SenaraiPermohonanACQService {

    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;
    @Inject
    PermohonanDAO permohonanDAO;

    public List<SenaraiPermohonanACQForm> populateList() {
        List<SenaraiPermohonanACQForm> list = new ArrayList<SenaraiPermohonanACQForm>();
        for (Permohonan m : senaraiPermohonACQ()) {
//            list = new ArrayList<SenaraiPermohonanACQForm>();
            SenaraiPermohonanACQForm form = new SenaraiPermohonanACQForm();
            form.setPengambilanSek4Form(setFormSek4ByIdMohon(m.getIdPermohonan()));
            form.setPengambilanSek8Form(setFormSek8ByIdMohon(m.getIdPermohonan()));
            if (form.getPengambilanSek8Form() != null) {
                form.setRecordSek8(Boolean.TRUE);
            } else {
                form.setRecordSek8(Boolean.FALSE);
            }
            list.add(form);
        }
        return list;
    }

    public List<Permohonan> senaraiPermohonACQ() {
        Session s = sessionProvider.get();
        String query = "SELECT m FROM etanah.model.Permohonan m WHERE m.kodUrusan.kod = 'SEK4'";
        Query q = s.createQuery(query);
//        q.setParameter("id", idMH);
        return q.list();
    }

    public PengambilanSek4Form setFormSek4ByIdMohon(String idPermohonan) {
        PengambilanSek4Form form = new PengambilanSek4Form();
        Permohonan mohon = permohonanDAO.findById(idPermohonan);
        form.setIdPermohonan(mohon.getIdPermohonan());
        form = setSenaraiHakmilik(form, mohon.getSenaraiHakmilik());
        form.setTarikhPermohonan(formatSDF(mohon.getInfoAudit().getTarikhMasuk()));
        form.setTarikhKelulusanSek4(setTarikhKelulusanMMK(idPermohonan));
        form.setTarikhTamatTempohSek4(calcTarikhTamat(idPermohonan));
        form.setTarikhWartaSek4(setTarikhWarta(idPermohonan));
        return form;
    }

    public PengambilanSek8Form setFormSek8ByIdMohon(String idPermohonan) {
        PengambilanSek8Form form = new PengambilanSek8Form();
        Permohonan mohon = permohonanDAO.findById(idPermohonan).getPermohonanSebelum();
        if (mohon != null) {
            form.setIdPermohonan(mohon.getIdPermohonan());
            form = setSenaraiHakmilik(form, mohon.getSenaraiHakmilik());
            form.setTarikhBorangD(setTarikhBorangD(mohon.getIdPermohonan()));
            form.setTarikhBorangH(setTarikhBorangH(mohon.getIdPermohonan()));
            form.setTarikhBorangK(setTarikhBorangK(mohon.getIdPermohonan()));
            form.setTarikhPermohonan(mohon.getInfoAudit().getTarikhMasuk().toString());
            form.setJumlahHakmilikSamb(setJumlahHakmilikSamb(mohon.getIdPermohonan()));
            form.setJumlahHakmilikSelesai(setJumlaHHakmilikSelesai(mohon.getIdPermohonan()));
        } else {
            form = null;
        }
        return form;
    }

    private PengambilanSek8Form setSenaraiHakmilik(PengambilanSek8Form form, List<HakmilikPermohonan> senaraiHakmilik) {
        String v = "";
        int i = 0;
        OUTER:
        for (HakmilikPermohonan hp : senaraiHakmilik) {
            switch (i) {
                case 0:
                    v = hp.getHakmilik() != null ? hp.getHakmilik().getIdHakmilik() : "TDK";
                    break;
                case 4: {
                    String a = hp.getHakmilik() != null ? hp.getHakmilik().getIdHakmilik() : "TDK";
                    v = v + ", " + a;
                    form.setUrlSenaraiHakmilik("sssssssssssssssssssssssssssssssssss");
                    form.setUrlhakmilik(Boolean.TRUE);
                    break OUTER;
                }
                default: {
                    String a = hp.getHakmilik() != null ? hp.getHakmilik().getIdHakmilik() : "TDK";
                    v = v + ", " + a;
                    break;
                }
            }
            i++;
        }
        form.setSenaraiHakmilik(v);
        return form;
    }

    private PengambilanSek4Form setSenaraiHakmilik(PengambilanSek4Form form, List<HakmilikPermohonan> senaraiHakmilik) {
        String v = "";
        int i = 0;
        OUTER:
        for (HakmilikPermohonan hp : senaraiHakmilik) {
            switch (i) {
                case 0:
                    v = hp.getHakmilik() != null ? hp.getHakmilik().getIdHakmilik() : "TDK";
                    break;
                case 4: {
                    String a = hp.getHakmilik() != null ? hp.getHakmilik().getIdHakmilik() : "TDK";
                    v = v + ", " + a;
                    form.setUrlSenaraiHakmilik("sssssssssssssssssssssssssssssssssss");
                    form.setUrlhakmilik(Boolean.TRUE);
                    break OUTER;
                }
                default: {
                    String a = hp.getHakmilik() != null ? hp.getHakmilik().getIdHakmilik() : "TDK";
                    v = v + ", " + a;
                    break;
                }
            }
            i++;
        }
        form.setSenaraiHakmilik(v);
        return form;
    }

    private String setTarikhKelulusanMMK(String idPermohonan) {
        Date date = new Date();
        return formatSDF(date);
    }

    private String calcTarikhTamat(String idPermohonan) {
        Date date = new Date();
        return formatSDF(date);
    }

    private String setTarikhWarta(String idPermohonan) {
        Date date = new Date();

        return formatSDF(date);
    }

    private String setTarikhBorangD(String idPermohonan) {
        Date date = new Date();

        return formatSDF(date);
    }

    private String setTarikhBorangH(String idPermohonan) {
        Date date = new Date();

        return formatSDF(date);
    }

    private String setTarikhBorangK(String idPermohonan) {
        Date date = new Date();

        return formatSDF(date);
    }

    private String setJumlahHakmilikSamb(String idPermohonan) {
        return "";
    }

    private String setJumlaHHakmilikSelesai(String idPermohonan) {
        return "";
    }

    public String formatSDF(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        return sdf.format(date);
    }

}
