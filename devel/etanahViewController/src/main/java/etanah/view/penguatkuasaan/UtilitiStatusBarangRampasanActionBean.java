/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.penguatkuasaan;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import java.util.List;

import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;

import com.google.inject.Inject;
import etanah.dao.BarangRampasanDAO;

import etanah.dao.PermohonanDAO;
import etanah.model.BarangRampasan;
import etanah.model.InfoAudit;
import etanah.model.KodStatusBarangRampasan;
import etanah.model.Pengguna;
import etanah.model.Permohonan;

import etanah.service.EnforceService;
import etanah.view.etanahActionBeanContext;
import java.util.ArrayList;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.controller.LifecycleStage;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

/**
 *
 * @author latifah.iskak
 */
@UrlBinding("/penguatkuasaan/status_barang_rampasan")
public class UtilitiStatusBarangRampasanActionBean extends AbleActionBean {

    private static Logger logger = Logger.getLogger(UtilitiStatusBarangRampasanActionBean.class);
    @Inject
    private PermohonanDAO permohonanDAO;
    @Inject
    EnforceService enforceService;
    @Inject
    private BarangRampasanDAO barangRampasanDAO;
    private Permohonan permohonan;
    private String idPermohonan;
    private List<BarangRampasan> listBarangRampasan;
    private String idBarang;
    private List<KodStatusBarangRampasan> senaraiKodStatus;
    private String status;
    private Pengguna pengguna;
    private boolean idPermohonanNotExist = Boolean.FALSE;
    private String statusBaru;
    private String ulasan;
    private String status2;

    @DefaultHandler
    public Resolution semakStatusBarangRampasan() {
        return new ForwardResolution("/WEB-INF/jsp/penguatkuasaan/semakan_status_barang_rampasan.jsp");
    }

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {
        pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        senaraiKodStatus = enforceService.getSenaraiKodStatusBarangRampasan();
    }

    public Resolution checkStatus() {
//        idPermohonan = getContext().getRequest().getParameter("id");
        System.out.println("idPermohonan : " + idPermohonan);
        if (idPermohonan != null) {
            permohonan = permohonanDAO.findById(idPermohonan);

            if (permohonan != null) {
                listBarangRampasan = new ArrayList<BarangRampasan>();
                listBarangRampasan.clear();
                listBarangRampasan.addAll(enforceService.searchBarangRampasan(idPermohonan.toUpperCase()));
                System.out.println("size search barang rampasan : " + listBarangRampasan.size());
                if (listBarangRampasan.size() == 0) {
                    System.out.println("size 0");
                    addSimpleMessage("Id Permohonan " + idPermohonan + " tiada rekod barang rampasan.");
                } else {
                    System.out.println("size > 0");
                    idPermohonan = permohonan.getIdPermohonan();
                    addSimpleMessage("Id Permohonan " + idPermohonan + " mempunyai " + listBarangRampasan.size() + " rekod barang rampasan.");
                }
            } else {
                idPermohonanNotExist = true;
                addSimpleError("Permohonan " + idPermohonan + " tidak dijumpai.");
            }
        } else {
            addSimpleError("Sila masukkan id permohonan.");
        }

        return new ForwardResolution("/WEB-INF/jsp/penguatkuasaan/semakan_status_barang_rampasan.jsp");


    }

    public Resolution simpan() {
        logger.info("-------------simpan---------------");

        try {
            String idOperasi = getContext().getRequest().getParameter("idOperasi");
            System.out.println("idOperasi : " + idOperasi);
            listBarangRampasan = enforceService.findByIDOperasi(Long.parseLong(idOperasi));
            System.out.println("list size masa simpan : " + listBarangRampasan.size());
            for (int i = 0; i < listBarangRampasan.size(); i++) {
                idBarang = getContext().getRequest().getParameter("idBarang" + i);
                status = getContext().getRequest().getParameter("status" + i);
                logger.info("status : "+i+" "+status);
                statusBaru = getContext().getRequest().getParameter("statusBaru" + i);
                logger.info("statusBaru : "+i+" "+statusBaru);
                ulasan = getContext().getRequest().getParameter("ulasan" + i);
//                System.out.println("idBarang value : " + idBarang);
//                System.out.println("status value : " + status);
//                System.out.println("status baru value : " + statusBaru);
//                System.out.println("ulasan value : " + ulasan);
                if (idBarang != null) {

                    BarangRampasan barang = barangRampasanDAO.findById(Long.parseLong(idBarang));
                    KodStatusBarangRampasan kodStatus = new KodStatusBarangRampasan();


                    KodStatusBarangRampasan kodStatusBaru = new KodStatusBarangRampasan();
                    kodStatusBaru.setKod(statusBaru);
//                    try {
//                        status2 = barang.getStatus().getKod();
//                    } catch (Exception e) {
//                        e.printStackTrace();
//
//                    }
//                    if (status2 == null) {
//                        status2 = kodStatusBaru.getKod();
//                    }
//                    logger.info("status2 : "+status2);
//                    kodStatus.setKod(status2);
                    

//                    kodStatusBaru.setKod(statusBaru);
                    InfoAudit info = new InfoAudit();
                    info = barang.getInfoAudit();
                    info.setDiKemaskiniOleh(pengguna);
                    info.setTarikhKemaskini(new java.util.Date());
                    barang.setInfoAudit(info);
//                    barang.setStatus(kodStatus);
                    barang.setUlasan(ulasan);
                    if(!statusBaru.equals("")){             //modified by hakim
                        logger.info("================================");
                        barang.setStatus(kodStatusBaru);
                        enforceService.simpanBarangRampasan(barang);
                    }
                    if(statusBaru.equals("")){
                        logger.info("0000000000000000000000000000000");
//                        logger.info("barang.getStatus().getKod() : "+barang.getStatus().getKod());
                        barang.setStatus(barang.getStatus());
                        enforceService.simpanBarangRampasan(barang);
                    }

                    if (barang.getStatusBarangRampasan() == null) {
                        logger.info("---------------------------------------------------------");
                        barang.setStatusBarangRampasan(kodStatusBaru);
                        if (barang.getStatus() == null) {
                            barang.setStatusBarangRampasan(kodStatus);
                            enforceService.simpanBarangRampasan(barang);
                        }
                    } 
//                    if (barang.getStatus() == null) {
//                        logger.info("+++++++++++++++++++++++++");
//                        barang.setStatus(kodStatusBaru);
//                        if (barang.getStatus() == null) {
//                            barang.setStatus(kodStatus);
//                            enforceService.simpanBarangRampasan(barang);
//                        }
//                    }
//                    if (!"".equals(statusBaru)) {
//                        logger.info("-------------------------- SINI --------------------------");
//                        barang.setStatus(kodStatusBaru);
//                        enforceService.simpanBarangRampasan(barang);
//                    }

                    System.out.println("idBarang value : " + idBarang);
//                    logger.info("-------------check status 33--------------- :" + barang.getStatusBarangRampasan().getNama());
                    logger.info("-------------check status baru--------------- :" + statusBaru);
                    logger.info("-------------check status --------------- :" + barang.getStatus().getNama());


                }
            }
        } catch (Exception e) {
            e.printStackTrace();

        }

        addSimpleMessage("Maklumat telah berjaya disimpan.");
        return new ForwardResolution("/WEB-INF/jsp/penguatkuasaan/semakan_status_barang_rampasan.jsp");
    }

    public Resolution simpanStatusWazer() {
        logger.info("-------------simpanStatus---------------");

        try {
            String idOperasi = getContext().getRequest().getParameter("idOperasi");
            System.out.println("idOperasi : " + idOperasi);
            listBarangRampasan = enforceService.findByIDOperasi(Long.parseLong(idOperasi));
            System.out.println("list size masa simpan : " + listBarangRampasan.size());
            for (int i = 0; i < listBarangRampasan.size(); i++) {
                idBarang = getContext().getRequest().getParameter("idBarang" + i);
                status = getContext().getRequest().getParameter("status" + i);
                statusBaru = getContext().getRequest().getParameter("statusBaru" + i);
                ulasan = getContext().getRequest().getParameter("ulasan" + i);
//                System.out.println("idBarang value : " + idBarang);
//                System.out.println("status value : " + status);
//                System.out.println("status baru value : " + statusBaru);
//                System.out.println("ulasan value : " + ulasan);
                if (idBarang != null) {


                    BarangRampasan barang = barangRampasanDAO.findById(Long.parseLong(idBarang));
                    KodStatusBarangRampasan kodStatus = new KodStatusBarangRampasan();


                    KodStatusBarangRampasan kodStatusBaru = new KodStatusBarangRampasan();
                    kodStatusBaru.setKod(statusBaru);
                    kodStatus.setKod(status);
                    if (idBarang != null) {
                        if (statusBaru != "") {


//                            KodStatusBarangRampasan kodStatusBaru = new KodStatusBarangRampasan();
//                            kodStatusBaru.setKod(statusBaru);

                            InfoAudit info = new InfoAudit();
                            info = barang.getInfoAudit();
                            info.setDiKemaskiniOleh(pengguna);
                            info.setTarikhKemaskini(new java.util.Date());
                            barang.setInfoAudit(info);
                            barang.setStatusBarangRampasan(kodStatus);
                            barang.setUlasan(ulasan);
                            barang.setStatus(kodStatusBaru);
                            enforceService.simpanBarangRampasan(barang);

                        } else {

//                            KodStatusBarangRampasan kodStatusBaru = new KodStatusBarangRampasan();
                            kodStatusBaru.setKod(statusBaru);
                            try {
                                status2 = barang.getStatus().getKod();
                            } catch (Exception e) {
                                e.printStackTrace();

                            }
                            if (status2 == null) {
                                status2 = kodStatusBaru.getKod();
                            }
                            kodStatus.setKod(status2);
                            barang.setStatusBarangRampasan(kodStatusBaru);

//                    kodStatusBaru.setKod(statusBaru);
                            InfoAudit info = new InfoAudit();
                            info = barang.getInfoAudit();
                            info.setDiKemaskiniOleh(pengguna);
                            info.setTarikhKemaskini(new java.util.Date());
                            barang.setInfoAudit(info);
//                    barang.setStatus(kodStatus);
                            barang.setUlasan(ulasan);

                            if (barang.getStatusBarangRampasan() == null) {
                                barang.setStatusBarangRampasan(kodStatus);
                                if (barang.getStatus() == null) {
                                    barang.setStatusBarangRampasan(kodStatusBaru);
                                    enforceService.simpanBarangRampasan(barang);
                                }
                            } else {
                                barang.setStatusBarangRampasan(kodStatus);
                            }
                            if (barang.getStatus() == null) {
                                barang.setStatus(kodStatusBaru);
                                if (barang.getStatus() == null) {
                                    barang.setStatus(kodStatus);
                                    enforceService.simpanBarangRampasan(barang);
                                }
                            }
                            if (statusBaru != "") {
                                barang.setStatus(kodStatusBaru);
                                enforceService.simpanBarangRampasan(barang);
                            }
//                            enforceService.simpanBarangRampasan(barang);
                        }

                    }
                    enforceService.simpanBarangRampasan(barang);
                    System.out.println("idBarang value : " + idBarang);
                    System.out.println("status value : " + status);
                    System.out.println("status baru value : " + statusBaru);
                    System.out.println("ulasan value : " + ulasan);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();

        }


        addSimpleMessage("Maklumat telah berjaya disimpan.");
        return new ForwardResolution("/WEB-INF/jsp/penguatkuasaan/semakan_status_barang_rampasan.jsp");
    }

    public Resolution simpanStatus() {
        logger.info("-------------simpanStatus---------------");

        try {
            String idOperasi = getContext().getRequest().getParameter("idOperasi");
            System.out.println("idOperasi : " + idOperasi);
            listBarangRampasan = enforceService.findByIDOperasi(Long.parseLong(idOperasi));
            System.out.println("list size masa simpan : " + listBarangRampasan.size());
            for (int i = 0; i < listBarangRampasan.size(); i++) {
                idBarang = getContext().getRequest().getParameter("idBarang" + i);
                status = getContext().getRequest().getParameter("status" + i);
                statusBaru = getContext().getRequest().getParameter("statusBaru" + i);
                ulasan = getContext().getRequest().getParameter("ulasan" + i);
                System.out.println("idBarang value : " + idBarang);
                System.out.println("status value : " + status);
                System.out.println("status baru value : " + statusBaru);
                System.out.println("ulasan value : " + ulasan);
                if (idBarang != null) {
                    BarangRampasan barang = barangRampasanDAO.findById(Long.parseLong(idBarang));
                    KodStatusBarangRampasan kodStatus = new KodStatusBarangRampasan();
                    kodStatus.setKod(status);

                    if (StringUtils.isNotBlank(statusBaru)) {
                        KodStatusBarangRampasan kodStatusBaru = new KodStatusBarangRampasan();
                        kodStatusBaru.setKod(statusBaru);
                        barang.setStatus(kodStatusBaru);
                    }

                    InfoAudit info = new InfoAudit();
                    info = barang.getInfoAudit();
                    info.setDiKemaskiniOleh(pengguna);
                    info.setTarikhKemaskini(new java.util.Date());
                    barang.setInfoAudit(info);
                    barang.setStatusBarangRampasan(kodStatus);
                    barang.setUlasan(ulasan);

                    enforceService.updateBarangRampasan(barang);


                }
            }
        } catch (Exception e) {
            e.printStackTrace();

        }

        addSimpleMessage("Maklumat telah berjaya disimpan.");
        return new ForwardResolution("/WEB-INF/jsp/penguatkuasaan/semakan_status_barang_rampasan.jsp");
    }

    public Resolution viewDetailPermohonan() {
        String idMohon = (String) getContext().getRequest().getParameter("idMohon");
        permohonan = permohonanDAO.findById(idMohon);
        return new JSP("penguatkuasaan/view_permohonan_detail.jsp").addParameter("popup", "true");
    }

    public void setPermohonan(Permohonan p) {
        this.permohonan = p;
    }

    public Permohonan getPermohonan() {
        return permohonan;
    }

    public String getIdPermohonan() {
        return idPermohonan;
    }

    public void setIdPermohonan(String idPermohonan) {
        this.idPermohonan = idPermohonan;
    }

    public String getIdBarang() {
        return idBarang;
    }

    public void setIdBarang(String idBarang) {
        this.idBarang = idBarang;
    }

    public List<KodStatusBarangRampasan> getSenaraiKodStatus() {
        return senaraiKodStatus;
    }

    public void setSenaraiKodStatus(List<KodStatusBarangRampasan> senaraiKodStatus) {
        this.senaraiKodStatus = senaraiKodStatus;
    }

    public List<BarangRampasan> getListBarangRampasan() {
        return listBarangRampasan;
    }

    public void setListBarangRampasan(List<BarangRampasan> listBarangRampasan) {
        this.listBarangRampasan = listBarangRampasan;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Pengguna getPengguna() {
        return pengguna;
    }

    public void setPengguna(Pengguna pengguna) {
        this.pengguna = pengguna;
    }

    public boolean isIdPermohonanNotExist() {
        return idPermohonanNotExist;
    }

    public void setIdPermohonanNotExist(boolean idPermohonanNotExist) {
        this.idPermohonanNotExist = idPermohonanNotExist;
    }

    public String getStatusBaru() {
        return statusBaru;
    }

    public void setStatusBaru(String statusBaru) {
        this.statusBaru = statusBaru;
    }

    public String getUlasan() {
        return ulasan;
    }

    public void setUlasan(String ulasan) {
        this.ulasan = ulasan;
    }
}
