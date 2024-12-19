/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.strata;

import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.dao.HakmilikDAO;
import etanah.dao.HakmilikPermohonanDAO;
import etanah.dao.KodNegeriDAO;
import etanah.dao.PemohonDAO;
import etanah.dao.PermohonanDAO;
import etanah.dao.PihakDAO;
import etanah.model.Hakmilik;
import etanah.model.HakmilikPermohonan;
import etanah.model.InfoAudit;
import etanah.model.KodNegeri;
import etanah.model.Pemohon;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.model.Pihak;
import etanah.service.StrataPtService;
import net.sourceforge.stripes.action.UrlBinding;
import etanah.view.BasicTabActionBean;
import net.sourceforge.stripes.action.Before;
import org.apache.log4j.Logger;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.controller.LifecycleStage;
import etanah.view.etanahActionBeanContext;
import net.sourceforge.stripes.action.RedirectResolution;
import org.apache.commons.lang.StringUtils;
import org.hibernate.Transaction;
import org.hibernate.Session;

/**
 *
 * @author wan.fairul
 */
@UrlBinding("/strata/maklumat_penyerah") 
public class MaklumatPenyerahActionBean extends BasicTabActionBean {

    @Inject
    StrataPtService strService;
    @Inject
    PihakDAO pihakDAO;
    @Inject
    PemohonDAO pemohonDAO;
    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    KodNegeriDAO kodNegeriDAO;
    @Inject
    CommonService comm;
    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;
    private Permohonan permohonan;
    private Pemohon pemohon;
    private Pengguna pengguna;
    private String penyerah;
    private static final Logger LOG = Logger.getLogger(MaklumatPenyerahActionBean.class);
    private boolean rayuan = true;
//    private boolean check= "false";
    private String negeri;

    public boolean isRayuan() {
        return rayuan;
    }

    public void setRayuan(boolean rayuan) {
        this.rayuan = rayuan;
    }

    public Permohonan getPermohonan() {
        return permohonan;
    }

    public void setPermohonan(Permohonan permohonan) {
        this.permohonan = permohonan;
    }

    public Pemohon getPemohon() {
        return pemohon;
    }

    public void setPemohon(Pemohon pemohon) {
        this.pemohon = pemohon;
    }

    public String getPenyerah() {
        return penyerah;
    }

    public void setPenyerah(String penyerah) {
        this.penyerah = penyerah;
    }

    public String getNegeri() {
        return negeri;
    }

    public void setNegeri(String negeri) {
        this.negeri = negeri;
    }


    @DefaultHandler
    public Resolution showForm() {
        return new JSP("strata/pbbm/maklumat_penyerah.jsp").addParameter("tab", "true");
    }

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {
        
        pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        String taskId = (String) getContext().getRequest().getSession().getAttribute("taskId");
        LOG.info("task ID: " + taskId);
        if (idPermohonan != null) {
            permohonan = permohonanDAO.findById(idPermohonan);
        }

        if (permohonan.getPermohonanSebelum() != null) {
            rayuan = false;
        }

//        if (permohonan.getKodUrusan().getKod().equals("PWPN")) {
//            rayuan = false;
//        }
//        if (permohonan.getKodUrusan().getKod().equals("RBHS") || permohonan.getKodUrusan().getKod().equals("RMHS1")
//                || permohonan.getKodUrusan().getKod().equals("RTHS") || permohonan.getKodUrusan().getKod().equals("RTPS")) {
//            rayuan = false;
//        }
        if (permohonan.getKodUrusan().getKod().equals("RMHS1") || permohonan.getKodUrusan().getKod().equals("RBHS")
                || permohonan.getKodUrusan().getKod().equals("PNB")||permohonan.getKodUrusan().getKod().equals("PWPN")) {
            rayuan = false;
        } else {

            comm.setPengguna(pengguna);
            String idAliran = comm.stageId(taskId);
            if (StringUtils.isNotBlank(idAliran)) {
                LOG.info("id Aliran = " + idAliran);

                if (!idAliran.equals("kemasukan")) {
                    LOG.info("====(!idAliran.equals('kemasukan'))====");
                    rayuan = false;
                }
            }
        }

        Pemohon penyerahpemohon = strService.findPenyerahPemohon(idPermohonan);
        if (penyerahpemohon != null) {
            penyerah = String.valueOf(penyerahpemohon.getIdPemohon());
            LOG.info(penyerah);
        }

        /*to make caps to kodnegeri*/
        negeri = permohonan.getPenyerahNegeri().getNama().toUpperCase();
    }

    /*
     * Method to copy penyerah information from table mohon into table pemohon.
     * for case : Penyerah adalah pemohon
     * added by zadhirul
     */
    public Resolution copyPenyerah() {
        LOG.info("Start : copy");

        pemohon = strService.findById(idPermohonan);

        if (pemohon == null) {
            pemohon = new Pemohon();
        } else {

            long idPihak = pemohon.getPihak().getIdPihak();
            Pihak pihak1 = pihakDAO.findById(idPihak);
            try {
                strService.deletePemohonByIDMohon(idPermohonan);
                LOG.info("Maklumat Pemohon telah dipadam");
            } catch (Exception e) {
                LOG.error(e);
                addSimpleError("Terdapat masalah teknikal");
            }
            if (pihak1 != null) {
                try {
                    strService.deletePihakByIdPihak(pihak1.getIdPihak());
                    LOG.info("Maklumat Pihak telah dipadam");
                } catch (Exception e) {
                    LOG.error(e);
                }

            }
            LOG.info("::CREATE NEW PEMOHON AFTER DELETE::");
            pemohon = new Pemohon();
        }

        Pihak pihak = new Pihak();
        InfoAudit ia = strService.getInfo(pengguna);
        LOG.info("::NAMA::" + permohonan.getPenyerahNama());
        pihak.setNama(permohonan.getPenyerahNama());
        pihak.setAlamat1(permohonan.getPenyerahAlamat1());
        pihak.setAlamat2(permohonan.getPenyerahAlamat2());
        pihak.setAlamat3(permohonan.getPenyerahAlamat3());
        pihak.setAlamat4(permohonan.getPenyerahAlamat4());
        LOG.info("::POSKOD::" + permohonan.getPenyerahPoskod());
        pihak.setPoskod(permohonan.getPenyerahPoskod());
        if (permohonan.getPenyerahNegeri() != null) {
            KodNegeri kd = kodNegeriDAO.findById(permohonan.getPenyerahNegeri().getKod());
            LOG.info("::NEGERI::" + kd.getNama());
            pihak.setNegeri(kd);
        }
        pihak.setNoTelefon1(permohonan.getPenyerahNoTelefon1());
        if (permohonan.getPenyerahJenisPengenalan() != null) {
            pihak.setJenisPengenalan(permohonan.getPenyerahJenisPengenalan());
        }
        if (permohonan.getPenyerahNoPengenalan() != null) {
            pihak.setNoPengenalan(permohonan.getPenyerahNoPengenalan());
        }
        pihak.setInfoAudit(ia);
        pihak = strService.savePihak(pihak);
        LOG.info("ID PIHAK" + pihak.getIdPihak());
        pihak = pihakDAO.findById(pihak.getIdPihak());

        pemohon.setPihak(pihak);
        pemohon.setPermohonan(permohonan);
        pemohon.setInfoAudit(ia);
        pemohon.setCawangan(permohonan.getCawangan());
        strService.savePemohon(pemohon);
//        return new JSP("strata/pbbm/maklumat_penyerah.jsp").addParameter("tab", "true");
        Pemohon penyerahpemohon = strService.findPenyerahPemohon(idPermohonan);
        if (penyerahpemohon != null) {
            addSimpleMessage("Maklumat Penyerah Telah Disimpan Sebagai Pemohon.");
        }
        return new RedirectResolution(MaklumatPenyerahActionBean.class, "showForm");

    }

    /* Method to delete from table pemohon and pihak if penyerah is not pemohon.
     * added by zadhirul
     */
    public Resolution deletePemohonBukanPenyerah() {
        LOG.info("Start : delete");
        pemohon = strService.findById(idPermohonan);

        if (pemohon != null) {
            long idPihak = pemohon.getPihak().getIdPihak();
            Pihak pihak1 = pihakDAO.findById(idPihak);
            try {
                strService.deletePemohonByIDMohon(idPermohonan);

                if (pihak1 != null) {
                    try {
                        strService.deletePihakByIdPihak(pihak1.getIdPihak());
                    } catch (Exception e) {
                        LOG.error(e);
                    }

                }
                LOG.info("::DELETE PEMOHON::");
                addSimpleMessage("Maklumat Penyerah Telah Dipadam Sebagai Pemohon.");

            } catch (Exception e) {
                LOG.error(e);
                addSimpleError("Terdapat masalah teknikal semasa memadam rekod.");
            }

        }
        return new RedirectResolution(MaklumatPenyerahActionBean.class, "showForm");

    }
}
