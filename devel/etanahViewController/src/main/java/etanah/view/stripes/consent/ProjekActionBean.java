/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.stripes.consent;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.dao.ProjekDAO;
import etanah.model.InfoAudit;
import etanah.model.Pengguna;
import etanah.model.Projek;
import etanah.service.ConsentPtdService;
import etanah.view.etanahActionBeanContext;
import java.util.List;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author mohd.azhar
 */
@UrlBinding("/consent/projek")
public class ProjekActionBean extends AbleActionBean {

    @Inject
    ConsentPtdService consServ;
    @Inject
    ProjekDAO projekDAO;
    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;
    private List<Projek> listProjek;
    private static final Logger LOG = Logger.getLogger(ProjekActionBean.class);
    private Projek projek;

    @DefaultHandler
    public Resolution showForm() {
//        etanahActionBeanContext ctx = new etanahActionBeanContext();
//        ctx = (etanahActionBeanContext) getContext();
//        Pengguna pengguna = ctx.getUser();

        listProjek = projekDAO.findAll();
        return new ForwardResolution("/WEB-INF/jsp/consent/projek.jsp");
    }

    public Resolution simpan() {
        etanahActionBeanContext ctx = new etanahActionBeanContext();
        ctx = (etanahActionBeanContext) getContext();
        Pengguna pengguna = ctx.getUser();

        LOG.info("simpan");

        if (projek != null) {
            if (projek.getNamaPemaju() != null && projek.getNoRujukanProjek() != null && projek.getJenisProjek() != null && projek.getJumlahSemuaUnit() != null && projek.getMaksimumUnit() != null) {

                InfoAudit objInfoAudit = new InfoAudit();
                objInfoAudit.setDimasukOleh(pengguna);
                objInfoAudit.setTarikhMasuk(new java.util.Date());
                Projek projekTemp = new Projek();
                if (projek.getIdProjek() != null) {
                    projekTemp = projekDAO.findById(projek.getIdProjek());
                }
                projekTemp.setInfoAudit(objInfoAudit);
                projekTemp.setNamaPemaju(projek.getNamaPemaju());
                projekTemp.setNoRujukanProjek(projek.getNoRujukanProjek());
                projekTemp.setJenisProjek(projek.getJenisProjek());
                projekTemp.setJumlahSemuaUnit(projek.getJumlahSemuaUnit());
                projekTemp.setMaksimumUnit(projek.getMaksimumUnit());

                consServ.simpanProjek(projekTemp);
                projek = null;
//                projek.setIdProjek(projekTemp.getIdProjek());

                addSimpleMessage("Maklumat Telah Berjaya Disimpan.");
            } else {
                addSimpleError("Sila masukkan maklumat dengan lengkap.");
            }
        } else {
            addSimpleError("Sila masukkan maklumat terlebih dahulu.");
        }
        listProjek = projekDAO.findAll();
        return new ForwardResolution("/WEB-INF/jsp/consent/projek.jsp");
    }

    public Resolution delete() {
//        Pengguna pengguna = (Pengguna) context.getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
//        Transaction tx = sessionProvider.get().beginTransaction();
//        tx.begin();
        try {

            String idProjek = getContext().getRequest().getParameter("idProjek");
            LOG.info(":::: DELETE ID PROJEK : " + idProjek + " ::::");
            projek = projekDAO.findById(Long.parseLong(idProjek));
            if (projek != null) {
                consServ.deleteProjek(projek);
            }
            else{
                 LOG.info(":::: DELETE ID PROJEK : " + idProjek + " NOT FOUND ::::");
            }
        } catch (Exception e) {
            e.printStackTrace();
//            tx.rollback();
            addSimpleError("Hapus Data Gagal. Sila Hubungi Pentadbir Sistem.");
            return new JSP("consent/projek.jsp").addParameter("tab", "true");
        }

//        tx.commit();
        listProjek = projekDAO.findAll();
        projek = null;
        return new JSP("consent/projek.jsp").addParameter("tab", "true");
    }

    public Resolution showEdit() {
        String idProjek = getContext().getRequest().getParameter("idProjek");
        projek = projekDAO.findById(Long.parseLong(idProjek));

        return new JSP("consent/projek_edit.jsp").addParameter("popup", "true");
    }

    public Resolution simpanEdit() {

        Pengguna pengguna = (Pengguna) context.getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        Transaction tx = sessionProvider.get().beginTransaction();
        tx.begin();
        try {
            Projek projekTemp = projekDAO.findById(projek.getIdProjek());
            if (!projekTemp.getIdProjek().equals(projek.getIdProjek())) {
            } else {
                InfoAudit infoAudit = new InfoAudit();
                infoAudit = projekTemp.getInfoAudit();
                infoAudit.setDiKemaskiniOleh(pengguna);
                infoAudit.setTarikhKemaskini(new java.util.Date());

                projekTemp.setNamaPemaju(projek.getNamaPemaju());
                projekTemp.setNoRujukanProjek(projek.getNoRujukanProjek());
                projekTemp.setJenisProjek(projek.getJenisProjek());
                projekTemp.setJumlahSemuaUnit(projek.getJumlahSemuaUnit());
                projekTemp.setMaksimumUnit(projek.getMaksimumUnit());


                consServ.simpanProjek(projekTemp);
                addSimpleMessage("Maklumat Telah Berjaya Dikemaskini.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            tx.rollback();
            addSimpleError("Kemasukan Data Gagal. Sila Hubungi Pentadbir Sistem.");
            listProjek = projekDAO.findAll();
            return new JSP("consent/projek.jsp");

        }
        listProjek = projekDAO.findAll();
        return new JSP("consent/projek.jsp").addParameter("popup", "true");
    }

    public Projek getProjek() {
        return projek;
    }

    public void setProjek(Projek projek) {
        this.projek = projek;
    }

    public List<Projek> getListProjek() {
        return listProjek;
    }

    public void setListProjek(List<Projek> listProjek) {
        this.listProjek = listProjek;
    }
}
