/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.penguatkuasaan;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import etanah.dao.PermohonanDAO;
import etanah.model.Permohonan;
import etanah.model.AduanOrangKenaSyak;
import etanah.model.InfoAudit;
import etanah.service.EnforceService;

import etanah.model.Pengguna;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;
import org.apache.log4j.Logger;
import etanah.service.common.EnforcementService;
import etanah.view.etanahActionBeanContext;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;


import com.google.inject.Inject;
import etanah.dao.AduanOrangKenaSyakDAO;
import etanah.dao.BarangRampasanDAO;
import etanah.model.BarangRampasan;
import etanah.model.OperasiPenguatkuasaan;
import java.util.ArrayList;
import org.apache.commons.lang.StringUtils;

/**
 *
 * @author latifah.iskak
 */
@UrlBinding("/penguatkuasaan/senarai_dakwa")
public class SenaraiDakwaActionBean extends AbleActionBean {

    private static Logger logger = Logger.getLogger(SenaraiDakwaActionBean.class);
    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;
    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    BarangRampasanDAO barangRampasanDAO;
    @Inject
    EnforcementService enforcementService;
    @Inject
    EnforceService enforceService;
    @Inject
    AduanOrangKenaSyakDAO aduanOrangKenaSyakDAO;
    @Inject
    private etanah.Configuration conf;
    private Pengguna pengguna;
    private String idPermohonan;
    private Permohonan permohonan;
    private List<BarangRampasan> senaraiBarangRampasan;
    private List<AduanOrangKenaSyak> senaraiAduanOrangKenaSyak;
    private String pilihOks;
    private String pilihBarang;
    private BarangRampasan barang;
    private AduanOrangKenaSyak oks;
    private OperasiPenguatkuasaan operasiPenguatkuasaan;
    private String kodNegeri;
    private List<OperasiPenguatkuasaan> listOp;
    private String idOp;
    private Boolean opFlag = false;
    private String pilihOksDakwa;
    private List<BarangRampasan> senaraiBarangRampasanForKenderaan;
    private List<AduanOrangKenaSyak> senaraiOksForDakwa;
    private String pilihCheckBox;
    private List<AduanOrangKenaSyak> senaraiOksIp;
    private Long idOpBasedOnIdIP;

    @DefaultHandler
    public Resolution showForm() {
        if ("04".equals(conf.getProperty("kodNegeri"))) {
            if (permohonan.getKodUrusan().getKod().equalsIgnoreCase("425") || permohonan.getKodUrusan().getKod().equalsIgnoreCase("425A") || permohonan.getKodUrusan().getKod().equalsIgnoreCase("426")) {
                if (opFlag == false) {
                    addSimpleError("Sila isikan maklumat laporan operasi terlebih dahulu");
                }

                getContext().getRequest().setAttribute("multipleOp", Boolean.TRUE);
            } else {
                getContext().getRequest().setAttribute("edit", Boolean.TRUE);
            }
        } else {
            getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        }

        return new JSP("penguatkuasaan/senarai_kena_dakwa.jsp").addParameter("tab", "true");
    }

    public Resolution showForm2() {
        if ("04".equals(conf.getProperty("kodNegeri"))) {
            if (permohonan.getKodUrusan().getKod().equalsIgnoreCase("425") || permohonan.getKodUrusan().getKod().equalsIgnoreCase("425A") || permohonan.getKodUrusan().getKod().equalsIgnoreCase("426")) {
                getContext().getRequest().setAttribute("viewMultipleOp", Boolean.TRUE);
            } else {
                getContext().getRequest().setAttribute("view", Boolean.TRUE);
            }
        } else {
            getContext().getRequest().setAttribute("view", Boolean.TRUE);
        }
        return new JSP("penguatkuasaan/senarai_kena_dakwa.jsp").addParameter("tab", "true");
    }

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {
        logger.info("------------rehydrate--------------");
        pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        kodNegeri = conf.getProperty("kodNegeri");
        if (idPermohonan != null) {
            permohonan = permohonanDAO.findById(idPermohonan);
            if ("04".equals(conf.getProperty("kodNegeri"))) {
                if (permohonan.getKodUrusan().getKod().equalsIgnoreCase("425") || permohonan.getKodUrusan().getKod().equalsIgnoreCase("425A") || permohonan.getKodUrusan().getKod().equalsIgnoreCase("426")) {
                    listOp = enforceService.findListLaporanOperasi(idPermohonan);
                    System.out.println("listOp size : " + listOp.size());
                    if (listOp.size() != 0) {
                        System.out.println("opFlag true");
                        opFlag = true;
                        System.out.println("opFlag ---- : " + opFlag);
                        senaraiOksForDakwa = enforceService.findAllOksForDakwa(permohonan.getIdPermohonan());
                        senaraiBarangRampasanForKenderaan = enforceService.findOksForDakwa(permohonan.getIdPermohonan());
                    } else {
                        if (permohonan.getPermohonanSebelum() != null) {
                            listOp = enforceService.findListLaporanOperasi(permohonan.getPermohonanSebelum().getIdPermohonan());
                            opFlag = true;
                            System.out.println("opFlag ---- : " + opFlag);

                            senaraiOksIp = enforceService.getListAduanOrangkenaSyak(permohonan.getIdPermohonan());
                            logger.info("size senaraiOksIp : " + senaraiOksIp.size());


                            if (!senaraiOksIp.isEmpty()) {
                                idOpBasedOnIdIP = senaraiOksIp.get(0).getOperasiPenguatkuasaan().getIdOperasi();
                                logger.info("id operasi : " + idOpBasedOnIdIP);

                                listOp = enforcementService.findListLaporanOperasi(idOpBasedOnIdIP);

                                if (StringUtils.isNotBlank(idOpBasedOnIdIP.toString())) {
                                    senaraiOksForDakwa = enforcementService.getLisOKSForDakwa(permohonan.getIdPermohonan());
                                    senaraiBarangRampasanForKenderaan = enforceService.findOksForDakwaForIP(permohonan.getIdPermohonan(), idOpBasedOnIdIP);
                                }

                            }


                        }
                    }
                } else {
                    //for Melaka, only pass idPermohonan
                    operasiPenguatkuasaan = enforceService.findOperasiPenguatkuasaanByIdpermohonan(idPermohonan);
                }
            } else {
                //for N9, pass idPermohonan & kategoriTindakan
                operasiPenguatkuasaan = enforceService.findOperasiPenguatkuasaanByIdpermohonan(idPermohonan, "S");
            }

            if (operasiPenguatkuasaan != null) {
                if ("04".equals(conf.getProperty("kodNegeri"))) {
                    if (permohonan.getKodUrusan().getKod().equalsIgnoreCase("422") || permohonan.getKodUrusan().getKod().equalsIgnoreCase("423")) {
                        logger.info("------------find senarai barang rampasan for sek 422,423,425 & 426--------------");
                        senaraiBarangRampasan = enforceService.findBarangEmpunyaForDakwa(operasiPenguatkuasaan.getIdOperasi());
                        System.out.println("size senaraiBarangRampasan : " + senaraiBarangRampasan.size());
                    }

                    if (!permohonan.getKodUrusan().getKod().equalsIgnoreCase("422") && !permohonan.getKodUrusan().getKod().equalsIgnoreCase("423") && !permohonan.getKodUrusan().getKod().equalsIgnoreCase("425") && !permohonan.getKodUrusan().getKod().equalsIgnoreCase("426")) {
                        logger.info("------------find senarai barang rampasan for all seksyen except 422,423,425 & 426 for Melaka --------------");
                        senaraiBarangRampasan = enforceService.findBarangEmpunya(operasiPenguatkuasaan.getIdOperasi());
                        System.out.println("size senaraiBarangRampasan : " + senaraiBarangRampasan.size());
                    }
                }
                if ("05".equals(conf.getProperty("kodNegeri"))) {
                    if (!permohonan.getKodUrusan().getKod().equalsIgnoreCase("422") && !permohonan.getKodUrusan().getKod().equalsIgnoreCase("423") && !permohonan.getKodUrusan().getKod().equalsIgnoreCase("424") && !permohonan.getKodUrusan().getKod().equalsIgnoreCase("427") && !permohonan.getKodUrusan().getKod().equalsIgnoreCase("428")) {
                        logger.info("------------find senarai barang rampasan for N9 --------------");
                        senaraiBarangRampasan = enforceService.findBarangEmpunya(operasiPenguatkuasaan.getIdOperasi());
                        System.out.println("size senaraiBarangRampasan : " + senaraiBarangRampasan.size());
                    }

                }


            }
            senaraiAduanOrangKenaSyak = enforceService.findByID(idPermohonan);

            System.out.println("senarai oks : " + senaraiAduanOrangKenaSyak.size());
//            System.out.println("senarai barang rampasan : " + senaraiBarangRampasanForKenderaan.size());

//            System.out.println("senaraiOksForDakwa : " + senaraiOksForDakwa.size());

        }

    }

    public Resolution simpan() {
        logger.info("------------simpan--------------");
        Session s = sessionProvider.get();
        Transaction tx = s.beginTransaction();
        try {
            if ("04".equals(conf.getProperty("kodNegeri"))) {
                if (permohonan.getKodUrusan().getKod().equalsIgnoreCase("422") || permohonan.getKodUrusan().getKod().equalsIgnoreCase("423") || permohonan.getKodUrusan().getKod().equalsIgnoreCase("425") || permohonan.getKodUrusan().getKod().equalsIgnoreCase("426")) {
                    logger.info("------------simpan barang rampasan MLK--------------");
                    for (int i = 0; i < senaraiBarangRampasan.size(); i++) {
                        pilihBarang = getContext().getRequest().getParameter("pilihBarang" + i);
                        System.out.println("pilihBarang : " + pilihBarang);
                        if (pilihBarang != null) {
                            barang = barangRampasanDAO.findById(Long.parseLong(pilihBarang));
                            barang.setStatusDakwaan("Y");
                        } else {
                            barang = senaraiBarangRampasan.get(i);
                            barang.setStatusDakwaan("T");
                        }

                        InfoAudit info = new InfoAudit();
                        info = barang.getInfoAudit();
                        info.setDiKemaskiniOleh(pengguna);
                        info.setTarikhKemaskini(new java.util.Date());
                        barang.setInfoAudit(info);
                        barangRampasanDAO.saveOrUpdate(barang);
                    }

                }

            } else {
                //for NS
                if (permohonan.getKodUrusan().getKod().equalsIgnoreCase("425") || permohonan.getKodUrusan().getKod().equalsIgnoreCase("426")) {
                    logger.info("------------simpan barang rampasan NS--------------");
                    for (int i = 0; i < senaraiBarangRampasan.size(); i++) {
                        pilihBarang = getContext().getRequest().getParameter("pilihBarang" + i);
                        System.out.println("pilihBarang : " + pilihBarang);
                        if (pilihBarang != null) {
                            barang = barangRampasanDAO.findById(Long.parseLong(pilihBarang));
                            barang.setStatusDakwaan("Y");
                        } else {
                            barang = senaraiBarangRampasan.get(i);
                            barang.setStatusDakwaan("T");
                        }

                        InfoAudit info = new InfoAudit();
                        info = barang.getInfoAudit();
                        info.setDiKemaskiniOleh(pengguna);
                        info.setTarikhKemaskini(new java.util.Date());
                        barang.setInfoAudit(info);
                        barangRampasanDAO.saveOrUpdate(barang);
                    }

                }
            }



            for (int i = 0; i < senaraiAduanOrangKenaSyak.size(); i++) {
                pilihOks = getContext().getRequest().getParameter("pilihOks" + i);
                System.out.println("pilihOks: " + pilihOks);
                if (pilihOks != null) {
                    oks = aduanOrangKenaSyakDAO.findById(Long.parseLong(pilihOks));
                    oks.setStatusDakwaan("Y");
                } else {
                    System.out.println("update uncheck record : ");
                    oks = senaraiAduanOrangKenaSyak.get(i);
                    oks.setStatusDakwaan("T");
                }

                InfoAudit info = new InfoAudit();
                info = oks.getInfoAudit();
                info.setDiKemaskiniOleh(pengguna);
                info.setTarikhKemaskini(new java.util.Date());
                oks.setInfoAudit(info);
                aduanOrangKenaSyakDAO.saveOrUpdate(oks);
            }
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
            Throwable t = e;
            // getting the root cause
            while (t.getCause() != null) {
                t = t.getCause();
            }
            t.printStackTrace();
            addSimpleError(t.getMessage());
        }

        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        addSimpleMessage("Maklumat telah berjaya disimpan.");
        return new JSP("penguatkuasaan/senarai_kena_dakwa.jsp").addParameter("tab", "true");



    }

    public Resolution simpanSenaraiDakwa() {
        logger.info("------------simpanSenaraiDakwa--------------");
        for (int i = 0; i < listOp.size(); i++) {

            try {
                List<AduanOrangKenaSyak> listOks = new ArrayList<AduanOrangKenaSyak>();
                listOks = listOp.get(i).getSenaraiAduanOrangKenaSyak();
                logger.info("::::::::::size listOks::::::::::  " + listOks.size());
                String totalBarangRampasan = getContext().getRequest().getParameter("totalBarangRampasan" + i);
                System.out.println("totalBarangRampasan ::: " + totalBarangRampasan);
                if (StringUtils.isNotBlank(totalBarangRampasan)) {
                    if (!totalBarangRampasan.equalsIgnoreCase("0")) {
                        for (int j = 0; j < listOks.size(); j++) {
                            pilihOksDakwa = getContext().getRequest().getParameter("pilihOksDakwa" + i + j);
                            pilihCheckBox = getContext().getRequest().getParameter("pilihCheckBox" + i + j);

                            List<BarangRampasan> listBarangRampasan = new ArrayList<BarangRampasan>();

                            if (StringUtils.isNotBlank(pilihCheckBox)) {
                                logger.info("::::::::::CHECKED checkbox ::::::::::  ");
                                System.out.println("pilihCheckBox [" + i + j + "]: " + pilihCheckBox);
                                listBarangRampasan = enforceService.findBarangOks(Long.parseLong(pilihCheckBox));
                                if (!listBarangRampasan.isEmpty()) {
                                    for (int b = 0; b < listBarangRampasan.size(); b++) {
                                        barang = listBarangRampasan.get(b);
                                        barang.setStatusDakwaan("Y");
                                    }
                                }
                            } else {
                                logger.info("::::::::::UNCHECKED checkbox ::::::::::  ");
                                System.out.println("pilihOksDakwa [" + i + j + "]: " + pilihOksDakwa);
                                listBarangRampasan = enforceService.findBarangOks(Long.parseLong(pilihOksDakwa));
                                if (!listBarangRampasan.isEmpty()) {
                                    for (int b = 0; b < listBarangRampasan.size(); b++) {
                                        barang = listBarangRampasan.get(b);
                                        barang.setStatusDakwaan("T");
                                    }
                                }
                            }

                            InfoAudit info = new InfoAudit();
                            info = barang.getInfoAudit();
                            info.setDiKemaskiniOleh(pengguna);
                            info.setTarikhKemaskini(new java.util.Date());
                            barang.setInfoAudit(info);
                            enforceService.simpanBarangRampasan(barang);
                        }
                    }
                }


            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        getContext().getRequest().setAttribute("multipleOp", Boolean.TRUE);
        addSimpleMessage("Maklumat telah berjaya disimpan.");
        rehydrate();
        return new JSP("penguatkuasaan/senarai_kena_dakwa.jsp").addParameter("tab", "true");
    }

    public Resolution simpanOKSForDakwa() {
        logger.info("------------simpanOKSForDakwa--------------");
        for (int i = 0; i < listOp.size(); i++) {

            try {
                List<AduanOrangKenaSyak> listOks = new ArrayList<AduanOrangKenaSyak>();
                listOks = listOp.get(i).getSenaraiAduanOrangKenaSyak();
                logger.info("::::::::::size listOks::::::::::  " + listOks.size());
                String totalBarangRampasan = getContext().getRequest().getParameter("totalBarangRampasan" + i);
                System.out.println("totalBarangRampasan ::: " + totalBarangRampasan);
                for (int j = 0; j < listOks.size(); j++) {
                    pilihOksDakwa = getContext().getRequest().getParameter("pilihOksDakwa" + i + j);
                    pilihCheckBox = getContext().getRequest().getParameter("pilihCheckBox" + i + j);

                    if (StringUtils.isNotBlank(pilihCheckBox)) {
                        logger.info("::::::::::CHECKED checkbox ::::::::::  ");
                        System.out.println("pilihCheckBox [" + i + j + "]: " + pilihCheckBox);
                        oks = aduanOrangKenaSyakDAO.findById(Long.parseLong(pilihCheckBox));
                        if (oks != null) {
                            oks.setStatusDakwaan("Y");
                        }

                    } else {
                        logger.info("::::::::::UNCHECKED checkbox ::::::::::  ");
                        System.out.println("pilihOksDakwa [" + i + j + "]: " + pilihOksDakwa);
                        if (StringUtils.isNotEmpty(pilihOksDakwa)) {
                            oks = aduanOrangKenaSyakDAO.findById(Long.parseLong(pilihOksDakwa));
                            if (oks != null) {
                                oks.setStatusDakwaan("T");
                            }
                        }
                    }

                    InfoAudit info = new InfoAudit();
                    info = oks.getInfoAudit();
                    info.setDiKemaskiniOleh(pengguna);
                    info.setTarikhKemaskini(new java.util.Date());
                    oks.setInfoAudit(info);
                    enforceService.simpanAduanOrangDisyaki(oks);


                }


            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        getContext().getRequest().setAttribute("multipleOp", Boolean.TRUE);
        addSimpleMessage("Maklumat telah berjaya disimpan.");
        rehydrate();
        return new JSP("penguatkuasaan/senarai_kena_dakwa.jsp").addParameter("tab", "true");
    }

    public String getIdPermohonan() {
        return idPermohonan;
    }

    public void setIdPermohonan(String idPermohonan) {
        this.idPermohonan = idPermohonan;
    }

    public Pengguna getPengguna() {
        return pengguna;
    }

    public void setPengguna(Pengguna pengguna) {
        this.pengguna = pengguna;
    }

    public Permohonan getPermohonan() {
        return permohonan;
    }

    public void setPermohonan(Permohonan permohonan) {
        this.permohonan = permohonan;
    }

    public List<BarangRampasan> getSenaraiBarangRampasan() {
        return senaraiBarangRampasan;
    }

    public void setSenaraiBarangRampasan(List<BarangRampasan> senaraiBarangRampasan) {
        this.senaraiBarangRampasan = senaraiBarangRampasan;
    }

    public List<AduanOrangKenaSyak> getSenaraiAduanOrangKenaSyak() {
        return senaraiAduanOrangKenaSyak;
    }

    public void setSenaraiAduanOrangKenaSyak(List<AduanOrangKenaSyak> senaraiAduanOrangKenaSyak) {
        this.senaraiAduanOrangKenaSyak = senaraiAduanOrangKenaSyak;
    }

    public String getPilihBarang() {
        return pilihBarang;
    }

    public void setPilihBarang(String pilihBarang) {
        this.pilihBarang = pilihBarang;
    }

    public String getPilihOks() {
        return pilihOks;
    }

    public void setPilihOks(String pilihOks) {
        this.pilihOks = pilihOks;
    }

    public BarangRampasan getBarang() {
        return barang;
    }

    public void setBarang(BarangRampasan barang) {
        this.barang = barang;
    }

    public AduanOrangKenaSyak getOks() {
        return oks;
    }

    public void setOks(AduanOrangKenaSyak oks) {
        this.oks = oks;
    }

    public OperasiPenguatkuasaan getOperasiPenguatkuasaan() {
        return operasiPenguatkuasaan;
    }

    public void setOperasiPenguatkuasaan(OperasiPenguatkuasaan operasiPenguatkuasaan) {
        this.operasiPenguatkuasaan = operasiPenguatkuasaan;
    }

    public String getKodNegeri() {
        return kodNegeri;
    }

    public void setKodNegeri(String kodNegeri) {
        this.kodNegeri = kodNegeri;
    }

    public String getIdOp() {
        return idOp;
    }

    public void setIdOp(String idOp) {
        this.idOp = idOp;
    }

    public List<OperasiPenguatkuasaan> getListOp() {
        return listOp;
    }

    public void setListOp(List<OperasiPenguatkuasaan> listOp) {
        this.listOp = listOp;
    }

    public Boolean getOpFlag() {
        return opFlag;
    }

    public void setOpFlag(Boolean opFlag) {
        this.opFlag = opFlag;
    }

    public String getPilihOksDakwa() {
        return pilihOksDakwa;
    }

    public void setPilihOksDakwa(String pilihOksDakwa) {
        this.pilihOksDakwa = pilihOksDakwa;
    }

    public List<BarangRampasan> getSenaraiBarangRampasanForKenderaan() {
        return senaraiBarangRampasanForKenderaan;
    }

    public void setSenaraiBarangRampasanForKenderaan(List<BarangRampasan> senaraiBarangRampasanForKenderaan) {
        this.senaraiBarangRampasanForKenderaan = senaraiBarangRampasanForKenderaan;
    }

    public List<AduanOrangKenaSyak> getSenaraiOksForDakwa() {
        return senaraiOksForDakwa;
    }

    public void setSenaraiOksForDakwa(List<AduanOrangKenaSyak> senaraiOksForDakwa) {
        this.senaraiOksForDakwa = senaraiOksForDakwa;
    }

    public String getPilihCheckBox() {
        return pilihCheckBox;
    }

    public void setPilihCheckBox(String pilihCheckBox) {
        this.pilihCheckBox = pilihCheckBox;
    }

    public List<AduanOrangKenaSyak> getSenaraiOksIp() {
        return senaraiOksIp;
    }

    public void setSenaraiOksIp(List<AduanOrangKenaSyak> senaraiOksIp) {
        this.senaraiOksIp = senaraiOksIp;
    }

    public Long getIdOpBasedOnIdIP() {
        return idOpBasedOnIdIP;
    }

    public void setIdOpBasedOnIdIP(Long idOpBasedOnIdIP) {
        this.idOpBasedOnIdIP = idOpBasedOnIdIP;
    }
}
