/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.service.ispeks;

import com.google.inject.Inject;
import com.wideplay.warp.persist.Transactional;
import etanah.dao.IspeksFileProsesDAO;
import etanah.model.DokumenKewanganBayaran;
import etanah.model.ispek.IspeksFileProses;
import etanah.model.ispek.IspeksResitPerbendaharaan;
import etanah.model.ispek.PenyataPemungut;
import etanah.model.ispek.StatusInfoIspeks;
import etanah.model.ispek.TugasanIspeks;
import etanah.view.stripes.ispeks.FileUtilForm;
import etanah.view.stripes.ispeks.StatusInfoIspeksForm;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author mohd.faidzal
 */
public class IspeksService {

    @Inject
    IspeksFileProsesDAO ispeksFileProsesDAO;
    @Inject
    ResitPerbendaharaanService resitPerbendaharaanService;
    @Inject
    TugasanIspekService tugasanIspekService;
    @Inject
    PenyataPemungutService penyataPemungutService;
    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;

    @Transactional
    public void simpanIspeksFileProses(IspeksFileProses file) {
        ispeksFileProsesDAO.save(file);
    }

    public List<FileUtilForm> findAllList() {
        List<FileUtilForm> list = new ArrayList<FileUtilForm>();
        String query = "SELECT h FROM etanah.model.ispek.IspeksFileProses h order by h.tarikhMuatTurun";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        if (!q.list().isEmpty()) {
            for (int i = 0; i < q.list().size(); i++) {
                IspeksFileProses f = new IspeksFileProses();
                f = (IspeksFileProses) q.list().get(i);
                FileUtilForm fm = new FileUtilForm();
                fm.setId(f.getId());
                fm.setFileName(f.getNamaFail());
                fm.setTarikh(f.getTarikhMuatTurun());
                fm.setKategori(f.getKategori());
                list.add(fm);
            }
        }
        return list;
    }

    public List<StatusInfoIspeksForm> findStatusInfoCawangan(String kod) {
        List<StatusInfoIspeksForm> l = new ArrayList<StatusInfoIspeksForm>();
        String query = "SELECT h FROM etanah.model.ispek.StatusInfoIspeks h where h.kodCawangan = :kod";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("kod", kod);
        List<StatusInfoIspeks> r = q.list();
        if (!r.isEmpty()) {
            for (StatusInfoIspeks s : r) {
                StatusInfoIspeksForm f = new StatusInfoIspeksForm();
                f.setStatusInfo(s);
                f = setFormValues(f, s.getNoRef(), s.getJenisFail());
                l.add(f);
            }
        }
        return l;
    }

    private String setButangResend(String noRef) {
        String b = "enable";

        IspeksResitPerbendaharaan resit = resitPerbendaharaanService.findResitByPPPenyata(noRef);
        if (resit != null) {
            b = "disable";
        }

        return b;
    }

    private StatusInfoIspeksForm setFormValues(StatusInfoIspeksForm f, String noRef, String jenisFail) {

        if ("PP".equals(jenisFail)) {
            f.setPpResend(setButangResend(noRef));
            PenyataPemungut pp = penyataPemungutService.findByNoPenyata(noRef);
            if (pp != null) {
                TugasanIspeks tu = tugasanIspekService.findByNoRef(pp.getId()+"","PP",pp.getKodCaw().getKod());
                if (tu != null) {
                    f.setPeringkat(tu.getKodPeringkat().getNama());

                }
            }
        } else {
            f.setPpResend("enable");
        }

        return f;
    }
    
    public List<DokumenKewanganBayaran> findDokKewByr(String idKewDok) {
        String query = "SELECT h FROM etanah.model.DokumenKewanganBayaran h where h.dokumenKewangan.idDokumenKewangan = :idKewDok";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idKewDok", idKewDok);
        return q.list();
    }

}
