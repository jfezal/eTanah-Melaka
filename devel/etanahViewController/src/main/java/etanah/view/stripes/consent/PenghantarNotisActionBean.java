/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.stripes.consent;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.dao.KodJenisPengenalanDAO;
import etanah.dao.PenghantarNotisDAO;
import etanah.dao.PermohonanDAO;
import etanah.model.InfoAudit;
import etanah.model.KodCawangan;
import etanah.model.KodJenisPengenalan;
import etanah.model.Pengguna;
import etanah.model.PenghantarNotis;
import etanah.service.ConsentPtdService;
import etanah.view.etanahActionBeanContext;
import java.util.List;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.action.ForwardResolution;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;

@UrlBinding("/consent/penghantar_notis")
public class PenghantarNotisActionBean extends AbleActionBean {

    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    ConsentPtdService consentService;
    @Inject
    PenghantarNotisDAO penghantarNotisDAO;
    @Inject
    KodJenisPengenalanDAO kodJenisPengenalanDAO;
    private List<PenghantarNotis> listPenghantar;
    private static final Logger LOG = Logger.getLogger(PenghantarNotisActionBean.class);
    private PenghantarNotis penghantarNotis;
    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;

    @DefaultHandler
    public Resolution showForm() {
        etanahActionBeanContext ctx = new etanahActionBeanContext();
        ctx = (etanahActionBeanContext) getContext();
        Pengguna pengguna = ctx.getUser();
        listPenghantar = consentService.findSenaraiPenghantarNotisByCaw(pengguna.getKodCawangan().getKod(), "Y");
        return new ForwardResolution("/WEB-INF/jsp/consent/penghantar_notis.jsp");
    }

    public Resolution showEdit() {
        String idPenghantarNotis = getContext().getRequest().getParameter("idPenghantarNotis");

        penghantarNotis = penghantarNotisDAO.findById(Integer.parseInt(idPenghantarNotis));

        return new JSP("consent/penghantar_notis_edit.jsp").addParameter("popup", "true");
    }

    public Resolution simpan() {
        etanahActionBeanContext ctx = new etanahActionBeanContext();
        ctx = (etanahActionBeanContext) getContext();
        Pengguna pengguna = ctx.getUser();

        LOG.info("simpan");
        if (penghantarNotis != null) {

            if (penghantarNotis.getNama() != null && penghantarNotis.getKodJenisPengenalan() != null && penghantarNotis.getNoPengenalan() != null) {

                List<PenghantarNotis> listPenghantarCheck = consentService.findSenaraiPenghantarNotisByNoKpAndCaw(penghantarNotis.getKodJenisPengenalan().getKod(), penghantarNotis.getNoPengenalan(), pengguna.getKodCawangan().getKod(), "Y");
                if (listPenghantarCheck.isEmpty()) {

                    KodCawangan caw = pengguna.getKodCawangan();
                    InfoAudit ia = new InfoAudit();
                    ia.setDimasukOleh(pengguna);
                    ia.setTarikhMasuk(new java.util.Date());

                    penghantarNotis.setInfoAudit(ia);
                    penghantarNotis.setCawangan(caw);
                    penghantarNotis.setAktif('Y');
                    KodJenisPengenalan kod = kodJenisPengenalanDAO.findById(penghantarNotis.getKodJenisPengenalan().getKod());
                    penghantarNotis.setKodJenisPengenalan(kod);
                    consentService.simpanPenghantarNotis(penghantarNotis);
                    penghantarNotis = null;
                    addSimpleMessage("Maklumat Telah Berjaya Disimpan.");
                } else {
                    addSimpleError("Maklumat penghantar notis Nombor Pengenalan " + penghantarNotis.getNoPengenalan() + " telah wujud.");
                }
            } else {
                addSimpleError("Sila masukkan maklumat dengan lengkap.");
            }
        } else {
            addSimpleError("Sila masukkan maklumat terlebih dahulu.");
        }
        listPenghantar = consentService.findSenaraiPenghantarNotisByCaw(pengguna.getKodCawangan().getKod(), "Y");

        return new ForwardResolution("/WEB-INF/jsp/consent/penghantar_notis.jsp");
    }

    public Resolution simpanEdit() {
        Pengguna pengguna = (Pengguna) context.getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        Transaction tx = sessionProvider.get().beginTransaction();
        tx.begin();
        try {

            PenghantarNotis penghantarNotisTemp = penghantarNotisDAO.findById(penghantarNotis.getIdPenghantarNotis());

            if (penghantarNotisTemp == null) {
                penghantarNotisTemp = new PenghantarNotis();
            }
            boolean duplicate = false;
            if (!penghantarNotis.getKodJenisPengenalan().getKod().equals(penghantarNotisTemp.getKodJenisPengenalan().getKod()) || !penghantarNotis.getNoPengenalan().equals(penghantarNotisTemp.getNoPengenalan())) {
                List<PenghantarNotis> listPenghantarCheck = consentService.findSenaraiPenghantarNotisByNoKpAndCaw(penghantarNotis.getKodJenisPengenalan().getKod(), penghantarNotis.getNoPengenalan(), pengguna.getKodCawangan().getKod(), "Y");
                if (!listPenghantarCheck.isEmpty()) {
                    duplicate = true;
                }
            }

            if (!duplicate) {

                InfoAudit infoAudit = new InfoAudit();
                infoAudit = penghantarNotisTemp.getInfoAudit();
                infoAudit.setDiKemaskiniOleh(pengguna);
                infoAudit.setTarikhKemaskini(new java.util.Date());

                penghantarNotisTemp.setNama(penghantarNotis.getNama());
                penghantarNotisTemp.setKodJenisPengenalan(penghantarNotis.getKodJenisPengenalan());
                penghantarNotisTemp.setNoPengenalan(penghantarNotis.getNoPengenalan());

                consentService.simpanPenghantarNotis(penghantarNotisTemp);
                addSimpleMessage("Maklumat Telah Berjaya Dikemaskini.");
            } else {
                addSimpleError("Kemaskini data gagal. Maklumat penghantar notis Nombor Pengenalan " + penghantarNotis.getNoPengenalan() + " telah wujud.");
            }

        } catch (Exception e) {
            e.printStackTrace();
            tx.rollback();
            addSimpleError("Kemasukan Data Gagal. Sila Hubungi Pentadbir Sistem.");
            return new JSP("consent/penghantar_notis.jsp");
        }

        tx.commit();
        listPenghantar = consentService.findSenaraiPenghantarNotisByCaw(pengguna.getKodCawangan().getKod(), "Y");
        penghantarNotis = null;
        return new JSP("consent/penghantar_notis.jsp").addParameter("popup", "true");
    }

    public Resolution delete() {

        //SAVE AKTIF FROM Y TO T
        Pengguna pengguna = (Pengguna) context.getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        Transaction tx = sessionProvider.get().beginTransaction();
        tx.begin();
        try {

            String idPenghantarNotis = getContext().getRequest().getParameter("idPenghantarNotis");

            penghantarNotis = penghantarNotisDAO.findById(Integer.parseInt(idPenghantarNotis));

            InfoAudit infoAudit = new InfoAudit();
            infoAudit = penghantarNotis.getInfoAudit();
            infoAudit.setDiKemaskiniOleh(pengguna);
            infoAudit.setTarikhKemaskini(new java.util.Date());
            penghantarNotis.setAktif('T');
            consentService.simpanPenghantarNotis(penghantarNotis);

        } catch (Exception e) {
            e.printStackTrace();
            tx.rollback();
            addSimpleError("Kemasukan Data Gagal. Sila Hubungi Pentadbir Sistem.");
            return new JSP("consent/penghantar_notis.jsp").addParameter("tab", "true");
        }

        tx.commit();
        listPenghantar = consentService.findSenaraiPenghantarNotisByCaw(pengguna.getKodCawangan().getKod(), "Y");
        penghantarNotis = null;
        return new JSP("consent/penghantar_notis.jsp").addParameter("tab", "true");
    }

    public PenghantarNotis getPenghantarNotis() {
        return penghantarNotis;
    }

    public void setPenghantarNotis(PenghantarNotis penghantarNotis) {
        this.penghantarNotis = penghantarNotis;
    }

    public List<PenghantarNotis> getListPenghantar() {
        return listPenghantar;
    }

    public void setListPenghantar(List<PenghantarNotis> listPenghantar) {
        this.listPenghantar = listPenghantar;
    }
}
