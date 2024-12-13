/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.stripes.consent;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.dao.PermohonanDAO;
import etanah.dao.PermohonanLampiranPerintahDAO;
import etanah.model.InfoAudit;
import etanah.model.LampiranPerintah;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.model.PermohonanLampiranPerintah;
import etanah.model.PermohonanPihak;
import etanah.service.ConsentPtdService;
import etanah.service.common.PermohonanPihakService;
import etanah.view.etanahActionBeanContext;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.RedirectResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.StreamingResolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import java.text.SimpleDateFormat;

/**
 *
 * @author muhammad.amin
 */
@UrlBinding("/consent/maklumat_perintah")
public class MaklumatPerintahActionBean extends AbleActionBean {

    private static final Logger LOG = Logger.getLogger(MaklumatPerintahActionBean.class);
    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    PermohonanLampiranPerintahDAO permohonanLampiranPerintahDAO;
    @Inject
    ConsentPtdService conService;
    @Inject
    PermohonanPihakService permohonanPihakService;
    private Permohonan permohonan;
    private Permohonan permohonanCarian;
    private PermohonanLampiranPerintah mohonLampiranPerintah;
    private PermohonanLampiranPerintah permohonanLampiranPerintahKPSH;
    private PermohonanLampiranPerintah permohonanLampiranPerintahKKP;
    private PermohonanLampiranPerintah permohonanLampiranPerintahKPA;
//    private PermohonanLampiranPerintah permohonanLampiranPerintahPKP;
//    private PermohonanLampiranPerintah permohonanLampiranPerintahPPA;
    private LampiranPerintah lampiranPerintah;
    private List<Map<String, String>> listKPSH = new ArrayList<Map<String, String>>();
//    private List<Map<String, String>> listKKP = new ArrayList<Map<String, String>>();
    private List<Map<String, String>> listPPSH = new ArrayList<Map<String, String>>();
    private List<Map<String, String>> listPKP = new ArrayList<Map<String, String>>();
    private List<Map<String, String>> listPPA = new ArrayList<Map<String, String>>();
    private String checkKPSH;
    private String checkKKP;
    private String checkKPA;
    private String checkPPSH;
    private String checkPKP;
    private String checkPPA;
    private String checkKPSHPopup;
//    private String checkKKPPopup;
    private String checkPPSHPopup;
    private String checkPKPPopup;
    private String checkPPAPopup;
    private String namaKPSH;
    private String pengenalanKPSH;
    private String syerKPSH;
//    private String namaKKP;
//    private String pengenalanKKP;
    private String namaPPSH;
    private String noSerahanPPSH;
    private String tarikhPPSH;
//    private String syerPPSH;
    private String namaPKP;
    private String noSerahanPKP;
    private String tarikhPKP;
    private String namaPPA;
    private String noSerahanPPA;
    private String tarikhPPA;
    etanahActionBeanContext ctx;
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

    @DefaultHandler
    public Resolution showForm() {
        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        return new JSP("consent/maklumat_perintah.jsp").addParameter("tab", "true");
    }

    public Resolution showPaparan() {
        return new JSP("consent/maklumat_perintah.jsp").addParameter("tab", "true");
    }

    public Resolution perintahPopup() {
        String TYPE = getContext().getRequest().getParameter("type");

        if (TYPE.equals("KPSH")) {
            checkKPSHPopup = "Y";
        } else if (TYPE.equals("PPSH")) {
            checkPPSHPopup = "Y";
        } else if (TYPE.equals("PKP")) {
            checkPKPPopup = "Y";
        } else if (TYPE.equals("PPA")) {
            checkPPAPopup = "Y";
        }

        return new JSP("consent/maklumat_perintah_popup.jsp").addParameter("popup", "true");
    }

    public Resolution showCarianForm() {

        String TYPE = getContext().getRequest().getParameter("type");

        if (TYPE.equals("KPSH")) {
            checkKPSHPopup = "Y";
        } else if (TYPE.equals("PPSH")) {
            checkPPSHPopup = "Y";
        } else if (TYPE.equals("PKP")) {
            checkPKPPopup = "Y";
        } else if (TYPE.equals("PPA")) {
            checkPPAPopup = "Y";
        }


        return new JSP("consent/maklumat_perintah_carian.jsp").addParameter("popup", Boolean.TRUE);

    }

    public Resolution carianPerintah() {
        ctx = (etanahActionBeanContext) getContext();

        if (StringUtils.isNotBlank(permohonanCarian.getIdPermohonan())) {

            List<PermohonanLampiranPerintah> listMohonLP = new ArrayList<PermohonanLampiranPerintah>();
            List<PermohonanPihak> listMohonPihak = new ArrayList<PermohonanPihak>();

            //------------PPSH------------

            if (checkPPSHPopup != null) {

                listMohonLP = conService.findSenaraiMohonLampiranPerintahByJenis(permohonanCarian.getIdPermohonan(), "KPSH");

                if (listMohonLP.size() > 0) {
                    for (LampiranPerintah lampPerintah : listMohonLP.get(0).getSenaraiLampiranPerintah()) {

                        if (lampPerintah.getPerihalPerintah().equals("NAMA")) {
                            namaPPSH = lampPerintah.getCatatanPerintah();
                            tarikhPPSH = String.valueOf(sdf.format(lampPerintah.getInfoAudit().getTarikhMasuk()));
                            noSerahanPPSH = lampPerintah.getPermohonanLampiranPerintah().getPermohonan().getIdPermohonan();
                        }
                    }
                } else {
                    addSimpleError("Carian tidak dijumpai.");
                }
                checkPPSH = "Y";
            } //------------PKP------------
            else if (checkPKPPopup != null) {
                permohonanLampiranPerintahKKP = conService.findMohonLampiranPerintahByJenis(permohonanCarian.getIdPermohonan(), "KKP");

                listMohonPihak = permohonanPihakService.getSenaraiPmohonPihakByKod(permohonanCarian.getIdPermohonan(), "PA");

                if (permohonanLampiranPerintahKKP != null) {
                    if (listMohonPihak.size() > 0) {
                        namaPKP = listMohonPihak.get(0).getPihak().getNama();
                        noSerahanPKP = permohonanLampiranPerintahKKP.getPermohonan().getIdPermohonan();
                        tarikhPKP = String.valueOf(sdf.format(permohonanLampiranPerintahKKP.getInfoAudit().getTarikhMasuk()));
                    }
                } else {
                    addSimpleError("Carian tidak dijumpai.");
                }
                checkPKP = "Y";
            } //------------PPA------------
            else if (checkPPAPopup != null) {
                permohonanLampiranPerintahKPA = conService.findMohonLampiranPerintahByJenis(permohonanCarian.getIdPermohonan(), "KPA");

                listMohonPihak = permohonanPihakService.getSenaraiPmohonPihakByKod(permohonanCarian.getIdPermohonan(), "PA");

                if (permohonanLampiranPerintahKPA != null) {
                    if (listMohonPihak.size() > 0) {
                        namaPPA = listMohonPihak.get(0).getPihak().getNama();
                        noSerahanPPA = permohonanLampiranPerintahKPA.getPermohonan().getIdPermohonan();
                        tarikhPPA = String.valueOf(sdf.format(permohonanLampiranPerintahKPA.getInfoAudit().getTarikhMasuk()));
                    }
                } else {
                    addSimpleError("Carian tidak dijumpai.");
                }
                checkPPA = "Y";
            }

        } else {
            addSimpleError("Sila masukkan ID permohonan untuk membuat carian.");
        }
//        String doSearch = ctx.getRequest().getParameter("doSearch");

//        if (StringUtils.isNotBlank(doSearch)) {
//            LOG.debug("Starting cari pihak...");
//            senaraiPihak = pihakService.findAll(ctx.getRequest().getParameterMap());
//
//            if (senaraiPihak.isEmpty()) {
//                getContext().getRequest().setAttribute("addNewPihak", "true");
//            } else {
//                LOG.debug("cari " + senaraiPihak.size() + " pihak Successfully...");
//            }
//        }
        return new JSP("consent/maklumat_perintah_carian.jsp").addParameter("popup", Boolean.TRUE);
    }

//    public Resolution searchPerintah() {
//        ctx = (etanahActionBeanContext) getContext();
//
//        if (StringUtils.isNotBlank(permohonan.getIdPermohonan())) {
//
//            List<PermohonanLampiranPerintah> listMohonLP = new ArrayList<PermohonanLampiranPerintah>();
//            listMohonLP = conService.findSenaraiMohonLampiranPerintahByJenis(permohonan.getIdPermohonan(), "KPSH");
//
//            for (PermohonanLampiranPerintah mohonLP : listMohonLP) {
//
//                Map<String, String> mapPPSH = new HashMap<String, String>();
//                mapPPSH.put("ID", String.valueOf(mohonLP.getIdPermohonanPerintah()));
//                mapPPSH.put("SERAHAN", String.valueOf(permohonan.getIdPermohonan()));
//                mapPPSH.put("TARIKH", String.valueOf(mohonLP.getInfoAudit().getTarikhMasuk()));
//                for (LampiranPerintah lampPerintah : mohonLP.getSenaraiLampiranPerintah()) {
//                    mapPPSH.put(lampPerintah.getPerihalPerintah(), lampPerintah.getCatatanPerintah());
//                }
//
//                listPPSH.add(mapPPSH);
//                checkPPSH = "Y";
//            }
//
//
//            //------------PKP------------
//            permohonanLampiranPerintahKKP = conService.findMohonLampiranPerintahByJenis(permohonan.getIdPermohonan(), "KKP");
//
//            List<PermohonanPihak> listMohonPihak = permohonanPihakService.getSenaraiPmohonPihakByKod(permohonan.getIdPermohonan(), "PA");
//
//            for (PermohonanPihak mohonPihak : listMohonPihak) {
//                Map<String, String> mapPKP = new HashMap<String, String>();
//                mapPKP.put("NAMA", String.valueOf(mohonPihak.getPihak().getNama()));
//                mapPKP.put("SERAHAN", String.valueOf(permohonan.getIdPermohonan()));
//                mapPKP.put("TARIKH", String.valueOf(permohonanLampiranPerintahKKP.getInfoAudit().getTarikhMasuk()));
//                listPKP.add(mapPKP);
//                checkPKP = "Y";
//            }
//
//            //------------PPA------------
//
//            permohonanLampiranPerintahKPA = conService.findMohonLampiranPerintahByJenis(permohonan.getIdPermohonan(), "KPA");
//
//            listMohonPihak = permohonanPihakService.getSenaraiPmohonPihakByKod(permohonan.getIdPermohonan(), "PA");
//
//            for (PermohonanPihak mohonPihak : listMohonPihak) {
//                Map<String, String> mapPPA = new HashMap<String, String>();
//                mapPPA.put("NAMA", String.valueOf(mohonPihak.getPihak().getNama()));
//                mapPPA.put("SERAHAN", String.valueOf(permohonan.getIdPermohonan()));
//                mapPPA.put("TARIKH", String.valueOf(permohonanLampiranPerintahKPA.getInfoAudit().getTarikhMasuk()));
//
//                listPPA.add(mapPPA);
//                checkPPA = "Y";
//            }
//
//        }
////        String doSearch = ctx.getRequest().getParameter("doSearch");
//
////        if (StringUtils.isNotBlank(doSearch)) {
////            LOG.debug("Starting cari pihak...");
////            senaraiPihak = pihakService.findAll(ctx.getRequest().getParameterMap());
////
////            if (senaraiPihak.isEmpty()) {
////                getContext().getRequest().setAttribute("addNewPihak", "true");
////            } else {
////                LOG.debug("cari " + senaraiPihak.size() + " pihak Successfully...");
////            }
////        }
//        return new JSP("consent/maklumat_perintah_carian.jsp").addParameter("popup", Boolean.TRUE);
//    }
    public Resolution editPerintah() {
        String TYPE = getContext().getRequest().getParameter("type");
        String ID = getContext().getRequest().getParameter("id");

        LOG.info("---Edit Perintah " + TYPE + " ID MOHON PERINTAH : " + ID + " ---");

        mohonLampiranPerintah = permohonanLampiranPerintahDAO.findById(Long.parseLong(ID));

        if (TYPE.equals("KPSH")) {
            checkKPSHPopup = "Y";
            for (LampiranPerintah lampPerintah : mohonLampiranPerintah.getSenaraiLampiranPerintah()) {

                if (lampPerintah.getPerihalPerintah().equals("NAMA")) {
                    namaKPSH = lampPerintah.getCatatanPerintah();
                }
                if (lampPerintah.getPerihalPerintah().equals("PENGENALAN")) {
                    pengenalanKPSH = lampPerintah.getCatatanPerintah();
                }
                if (lampPerintah.getPerihalPerintah().equals("SYER")) {
                    syerKPSH = lampPerintah.getCatatanPerintah();
                }
            }
        } //        else if (TYPE.equals("KKP")) {
        //            checkKKPPopup = "Y";
        //            for (LampiranPerintah lampPerintah : mohonLampiranPerintah.getSenaraiLampiranPerintah()) {
        //
        //                if (lampPerintah.getPerihalPerintah().equals("NAMA")) {
        //                    namaKKP = lampPerintah.getCatatanPerintah();
        //                }
        //                if (lampPerintah.getPerihalPerintah().equals("PENGENALAN")) {
        //                    pengenalanKKP = lampPerintah.getCatatanPerintah();
        //                }
        //            }
        //        }
        else if (TYPE.equals("PPSH")) {
            checkPPSHPopup = "Y";
            for (LampiranPerintah lampPerintah : mohonLampiranPerintah.getSenaraiLampiranPerintah()) {

                if (lampPerintah.getPerihalPerintah().equals("NAMA")) {
                    namaPPSH = lampPerintah.getCatatanPerintah();
                }
                if (lampPerintah.getPerihalPerintah().equals("SERAHAN")) {
                    noSerahanPPSH = lampPerintah.getCatatanPerintah();
                }
                if (lampPerintah.getPerihalPerintah().equals("TARIKH")) {
                    tarikhPPSH = lampPerintah.getCatatanPerintah();
                }
            }
        } else if (TYPE.equals("PKP")) {
            checkPKPPopup = "Y";
            for (LampiranPerintah lampPerintah : mohonLampiranPerintah.getSenaraiLampiranPerintah()) {

                if (lampPerintah.getPerihalPerintah().equals("NAMA")) {
                    namaPKP = lampPerintah.getCatatanPerintah();
                }
                if (lampPerintah.getPerihalPerintah().equals("SERAHAN")) {
                    noSerahanPKP = lampPerintah.getCatatanPerintah();
                }
                if (lampPerintah.getPerihalPerintah().equals("TARIKH")) {
                    tarikhPKP = lampPerintah.getCatatanPerintah();
                }

            }
        } else if (TYPE.equals("PPA")) {
            checkPPAPopup = "Y";
            for (LampiranPerintah lampPerintah : mohonLampiranPerintah.getSenaraiLampiranPerintah()) {

                if (lampPerintah.getPerihalPerintah().equals("NAMA")) {
                    namaPPA = lampPerintah.getCatatanPerintah();
                }
                if (lampPerintah.getPerihalPerintah().equals("SERAHAN")) {
                    noSerahanPPA = lampPerintah.getCatatanPerintah();
                }
                if (lampPerintah.getPerihalPerintah().equals("TARIKH")) {
                    tarikhPPA = lampPerintah.getCatatanPerintah();
                }

            }
        }
        return new JSP("consent/maklumat_perintah_edit.jsp").addParameter("popup", "true");
    }

    public Resolution getMainPage() {
        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        return new JSP("consent/maklumat_perintah.jsp").addParameter("tab", "true");
    }

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {
        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        if (idPermohonan != null) {
            permohonan = permohonanDAO.findById(idPermohonan);

            permohonanLampiranPerintahKPSH = conService.findMohonLampiranPerintahByJenis(idPermohonan, "KPSH");
            permohonanLampiranPerintahKKP = conService.findMohonLampiranPerintahByJenis(idPermohonan, "KKP");
            permohonanLampiranPerintahKPA = conService.findMohonLampiranPerintahByJenis(idPermohonan, "KPA");
//            permohonanLampiranPerintahPKP = conService.findMohonLampiranPerintahByJenis(idPermohonan, "PKP");
//            permohonanLampiranPerintahPPA = conService.findMohonLampiranPerintahByJenis(idPermohonan, "PPA");

            if (permohonanLampiranPerintahKPSH != null) {
                checkKPSH = "Y";
            }
            if (permohonanLampiranPerintahKKP != null) {
                checkKKP = "Y";
            }
            if (permohonanLampiranPerintahKPA != null) {
                checkKPA = "Y";
            }
//            if (permohonanLampiranPerintahPKP != null) {
//                checkPKP = "Y";
//                for (LampiranPerintah lampPerintah : permohonanLampiranPerintahPKP.getSenaraiLampiranPerintah()) {
//
//                    if (lampPerintah.getPerihalPerintah().equals("NAMA")) {
//                        namaPKP = lampPerintah.getCatatanPerintah();
//                    }
//                    if (lampPerintah.getPerihalPerintah().equals("SERAHAN")) {
//                        noSerahanPKP = lampPerintah.getCatatanPerintah();
//                    }
//                    if (lampPerintah.getPerihalPerintah().equals("TARIKH")) {
//                        tarikhPKP = lampPerintah.getCatatanPerintah();
//                    }
//                }
//            }
//            if (permohonanLampiranPerintahPPA != null) {
//                checkPPA = "Y";
//                for (LampiranPerintah lampPerintah : permohonanLampiranPerintahPPA.getSenaraiLampiranPerintah()) {
//
//                    if (lampPerintah.getPerihalPerintah().equals("NAMA")) {
//                        namaPPA = lampPerintah.getCatatanPerintah();
//                    }
//                    if (lampPerintah.getPerihalPerintah().equals("SERAHAN")) {
//                        noSerahanPPA = lampPerintah.getCatatanPerintah();
//                    }
//                    if (lampPerintah.getPerihalPerintah().equals("TARIKH")) {
//                        tarikhPPA = lampPerintah.getCatatanPerintah();
//                    }
//                }
//            }

            List<PermohonanLampiranPerintah> listMohonLP = new ArrayList<PermohonanLampiranPerintah>();

            //------------KPSH------------
//            listMohonLP = conService.findSenaraiMohonLampiranPerintahByJenis(idPermohonan, "KPSH");
//
//            for (PermohonanLampiranPerintah mohonLP : listMohonLP) {
//
//                Map<String, String> mapKPSH = new HashMap<String, String>();
//
//                mapKPSH.put("ID", String.valueOf(mohonLP.getIdPermohonanPerintah()));
//                for (LampiranPerintah lampPerintah : mohonLP.getSenaraiLampiranPerintah()) {
//                    mapKPSH.put(lampPerintah.getPerihalPerintah(), lampPerintah.getCatatanPerintah());
//                }
//
//                listKPSH.add(mapKPSH);
//                checkKPSH = "Y";
//            }

            //------------KKP------------
//            listMohonLP = conService.findSenaraiMohonLampiranPerintahByJenis(idPermohonan, "KKP");
//
//            for (PermohonanLampiranPerintah mohonLP : listMohonLP) {
//
//                Map<String, String> mapKKP = new HashMap<String, String>();
//                mapKKP.put("ID", String.valueOf(mohonLP.getIdPermohonanPerintah()));
//                for (LampiranPerintah lampPerintah : mohonLP.getSenaraiLampiranPerintah()) {
//                    mapKKP.put(lampPerintah.getPerihalPerintah(), lampPerintah.getCatatanPerintah());
//                }
//
//                listKKP.add(mapKKP);
//                checkKKP = "Y";
//            }

            //------------PPSH------------
            listMohonLP = conService.findSenaraiMohonLampiranPerintahByJenis(idPermohonan, "PPSH");

            for (PermohonanLampiranPerintah mohonLP : listMohonLP) {

                Map<String, String> mapPPSH = new HashMap<String, String>();
                mapPPSH.put("ID", String.valueOf(mohonLP.getIdPermohonanPerintah()));
                for (LampiranPerintah lampPerintah : mohonLP.getSenaraiLampiranPerintah()) {
                    mapPPSH.put(lampPerintah.getPerihalPerintah(), lampPerintah.getCatatanPerintah());
                }

                listPPSH.add(mapPPSH);
                checkPPSH = "Y";
            }

            //------------PKP------------
            listMohonLP = conService.findSenaraiMohonLampiranPerintahByJenis(idPermohonan, "PKP");

            for (PermohonanLampiranPerintah mohonLP : listMohonLP) {

                Map<String, String> mapPKP = new HashMap<String, String>();
                mapPKP.put("ID", String.valueOf(mohonLP.getIdPermohonanPerintah()));
                for (LampiranPerintah lampPerintah : mohonLP.getSenaraiLampiranPerintah()) {
                    mapPKP.put(lampPerintah.getPerihalPerintah(), lampPerintah.getCatatanPerintah());
                }

                listPKP.add(mapPKP);
                checkPKP = "Y";
            }

            //------------PPA------------
            listMohonLP = conService.findSenaraiMohonLampiranPerintahByJenis(idPermohonan, "PPA");

            for (PermohonanLampiranPerintah mohonLP : listMohonLP) {

                Map<String, String> mapPPA = new HashMap<String, String>();
                mapPPA.put("ID", String.valueOf(mohonLP.getIdPermohonanPerintah()));
                for (LampiranPerintah lampPerintah : mohonLP.getSenaraiLampiranPerintah()) {
                    mapPPA.put(lampPerintah.getPerihalPerintah(), lampPerintah.getCatatanPerintah());
                }

                listPPA.add(mapPPA);
                checkPPA = "Y";
            }
        }
    }

    public Resolution simpan() {

        Pengguna pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");

        InfoAudit infoAuditEdit = new InfoAudit();
        InfoAudit infoAudit = new InfoAudit();
        infoAudit.setDimasukOleh(pengguna);
        infoAudit.setTarikhMasuk(new java.util.Date());

        //-----------Kemasukan Penghuni Seumur Hidup (KPSH)----------
        if (checkKPSH != null) {
            permohonanLampiranPerintahKPSH = conService.findMohonLampiranPerintahByJenis(idPermohonan, "KPSH");
            if (permohonanLampiranPerintahKPSH == null) {
                PermohonanLampiranPerintah mohonLampiranPerintahKPSH = new PermohonanLampiranPerintah();
                mohonLampiranPerintahKPSH.setPermohonan(permohonan);
                mohonLampiranPerintahKPSH.setJenisLaporan("KPSH");
                mohonLampiranPerintahKPSH.setInfoAudit(infoAudit);
                conService.simpanMohonLampiranPerintah(mohonLampiranPerintahKPSH);
            } else {
                infoAuditEdit = permohonanLampiranPerintahKPSH.getInfoAudit();
                infoAuditEdit.setDiKemaskiniOleh(pengguna);
                infoAuditEdit.setTarikhKemaskini(new java.util.Date());
                permohonanLampiranPerintahKPSH.setInfoAudit(infoAuditEdit);
                conService.simpanMohonLampiranPerintah(permohonanLampiranPerintahKPSH);
            }
        } else {//IF UNCHECK DELETE DATA Kemasukan Penghuni Seumur Hidup (KPSH)
            permohonanLampiranPerintahKPSH = conService.findMohonLampiranPerintahByJenis(idPermohonan, "KPSH");
            if (permohonanLampiranPerintahKPSH != null) {
                permohonanLampiranPerintahDAO.delete(permohonanLampiranPerintahKPSH);
            }
        }

        //-----------Kemasukan Kaveat Pendaftar (KKP)----------
        if (checkKKP != null) {
            permohonanLampiranPerintahKKP = conService.findMohonLampiranPerintahByJenis(idPermohonan, "KKP");
            if (permohonanLampiranPerintahKKP == null) {
                PermohonanLampiranPerintah mohonLampiranPerintahKPA = new PermohonanLampiranPerintah();
                mohonLampiranPerintahKPA.setPermohonan(permohonan);
                mohonLampiranPerintahKPA.setJenisLaporan("KKP");
                mohonLampiranPerintahKPA.setInfoAudit(infoAudit);
                conService.simpanMohonLampiranPerintah(mohonLampiranPerintahKPA);
            } else {
                infoAuditEdit = permohonanLampiranPerintahKKP.getInfoAudit();
                infoAuditEdit.setDiKemaskiniOleh(pengguna);
                infoAuditEdit.setTarikhKemaskini(new java.util.Date());
                permohonanLampiranPerintahKKP.setInfoAudit(infoAuditEdit);
                conService.simpanMohonLampiranPerintah(permohonanLampiranPerintahKKP);
            }
        } else {//IF UNCHECK DELETE DATA Kemasukan Kaveat Pendaftar (KKP)
            permohonanLampiranPerintahKKP = conService.findMohonLampiranPerintahByJenis(idPermohonan, "KKP");
            if (permohonanLampiranPerintahKKP != null) {
                permohonanLampiranPerintahDAO.delete(permohonanLampiranPerintahKKP);
            }
        }

        //-----------Kemasukan Pemegang Amanah (KPA)----------
        if (checkKPA != null) {
            permohonanLampiranPerintahKPA = conService.findMohonLampiranPerintahByJenis(idPermohonan, "KPA");
            if (permohonanLampiranPerintahKPA == null) {
                PermohonanLampiranPerintah mohonLampiranPerintahKPA = new PermohonanLampiranPerintah();
                mohonLampiranPerintahKPA.setPermohonan(permohonan);
                mohonLampiranPerintahKPA.setJenisLaporan("KPA");
                mohonLampiranPerintahKPA.setInfoAudit(infoAudit);
                conService.simpanMohonLampiranPerintah(mohonLampiranPerintahKPA);
            } else {
                infoAuditEdit = permohonanLampiranPerintahKPA.getInfoAudit();
                infoAuditEdit.setDiKemaskiniOleh(pengguna);
                infoAuditEdit.setTarikhKemaskini(new java.util.Date());
                permohonanLampiranPerintahKPA.setInfoAudit(infoAuditEdit);
                conService.simpanMohonLampiranPerintah(permohonanLampiranPerintahKPA);
            }
        } else {//IF UNCHECK DELETE DATA Kemasukan Pemegang Amanah (KPA)
            permohonanLampiranPerintahKPA = conService.findMohonLampiranPerintahByJenis(idPermohonan, "KPA");
            if (permohonanLampiranPerintahKPA != null) {
                permohonanLampiranPerintahDAO.delete(permohonanLampiranPerintahKPA);
            }
        }
//        //-----------PKP----------
//        if (checkPKP != null) {
//            permohonanLampiranPerintahPKP = conService.findMohonLampiranPerintahByJenis(idPermohonan, "PKP");
//            if (permohonanLampiranPerintahPKP == null) {
//                PermohonanLampiranPerintah mohonLampiranPerintahPKP = new PermohonanLampiranPerintah();
//                mohonLampiranPerintahPKP.setPermohonan(permohonan);
//                mohonLampiranPerintahPKP.setJenisLaporan("PKP");
//                mohonLampiranPerintahPKP.setInfoAudit(infoAudit);
//                conService.simpanMohonLampiranPerintah(mohonLampiranPerintahPKP);
//
//                for (int i = 0; i < 3; i++) {
//
//                    lampiranPerintah = new LampiranPerintah();
//                    if (i == 0) {
//                        lampiranPerintah.setPerihalPerintah("NAMA");
//                        lampiranPerintah.setCatatanPerintah(namaPKP);
//                    }
//                    if (i == 1) {
//                        lampiranPerintah.setPerihalPerintah("SERAHAN");
//                        lampiranPerintah.setCatatanPerintah(noSerahanPKP);
//                    }
//                    if (i == 2) {
//                        lampiranPerintah.setPerihalPerintah("TARIKH");
//                        lampiranPerintah.setCatatanPerintah(tarikhPKP);
//                    }
//
//                    lampiranPerintah.setPermohonanLampiranPerintah(mohonLampiranPerintahPKP);
//                    lampiranPerintah.setInfoAudit(infoAudit);
//                    conService.simpanLampiranPerintah(lampiranPerintah);
//                }
//            } else {
//
//                for (LampiranPerintah lampPerintah : permohonanLampiranPerintahPKP.getSenaraiLampiranPerintah()) {
//
//                    infoAuditEdit = lampPerintah.getInfoAudit();
//                    infoAuditEdit.setDiKemaskiniOleh(pengguna);
//                    infoAuditEdit.setTarikhKemaskini(new java.util.Date());
//                    if (lampPerintah.getPerihalPerintah().equals("NAMA")) {
//                        lampPerintah.setCatatanPerintah(namaPKP);
//                    } else if (lampPerintah.getPerihalPerintah().equals("SERAHAN")) {
//                        lampPerintah.setCatatanPerintah(noSerahanPKP);
//                    } else if (lampPerintah.getPerihalPerintah().equals("TARIKH")) {
//                        lampPerintah.setCatatanPerintah(tarikhPKP);
//                    }
//
//                    lampPerintah.setInfoAudit(infoAuditEdit);
//                    conService.simpanLampiranPerintah(lampPerintah);
//                }
//            }
//        }
//        //-----------PPA----------
//        if (checkPPA != null) {
//            permohonanLampiranPerintahPPA = conService.findMohonLampiranPerintahByJenis(idPermohonan, "PPA");
//            if (permohonanLampiranPerintahPPA == null) {
//                PermohonanLampiranPerintah mohonLampiranPerintahPPA = new PermohonanLampiranPerintah();
//                mohonLampiranPerintahPPA.setPermohonan(permohonan);
//                mohonLampiranPerintahPPA.setJenisLaporan("PPA");
//                mohonLampiranPerintahPPA.setInfoAudit(infoAudit);
//                conService.simpanMohonLampiranPerintah(mohonLampiranPerintahPPA);
//
//                for (int i = 0; i < 3; i++) {
//
//                    lampiranPerintah = new LampiranPerintah();
//                    if (i == 0) {
//                        lampiranPerintah.setPerihalPerintah("NAMA");
//                        lampiranPerintah.setCatatanPerintah(namaPPA);
//                    }
//                    if (i == 1) {
//                        lampiranPerintah.setPerihalPerintah("SERAHAN");
//                        lampiranPerintah.setCatatanPerintah(noSerahanPPA);
//                    }
//                    if (i == 2) {
//                        lampiranPerintah.setPerihalPerintah("TARIKH");
//                        lampiranPerintah.setCatatanPerintah(tarikhPPA);
//                    }
//
//                    lampiranPerintah.setPermohonanLampiranPerintah(mohonLampiranPerintahPPA);
//                    lampiranPerintah.setInfoAudit(infoAudit);
//                    conService.simpanLampiranPerintah(lampiranPerintah);
//                }
//            } else {
//
//                for (LampiranPerintah lampPerintah : permohonanLampiranPerintahPPA.getSenaraiLampiranPerintah()) {
//
//                    infoAuditEdit = lampPerintah.getInfoAudit();
//                    infoAuditEdit.setDiKemaskiniOleh(pengguna);
//                    infoAuditEdit.setTarikhKemaskini(new java.util.Date());
//
//                    if (lampPerintah.getPerihalPerintah().equals("NAMA")) {
//                        lampPerintah.setCatatanPerintah(namaPPA);
//                    } else if (lampPerintah.getPerihalPerintah().equals("SERAHAN")) {
//                        lampPerintah.setCatatanPerintah(noSerahanPPA);
//                    } else if (lampPerintah.getPerihalPerintah().equals("TARIKH")) {
//                        lampPerintah.setCatatanPerintah(tarikhPPA);
//                    }
//
//                    lampPerintah.setInfoAudit(infoAuditEdit);
//                    conService.simpanLampiranPerintah(lampPerintah);
//                }
//            }
//        }

        addSimpleMessage("Maklumat telah berjaya disimpan.");

        getContext().getRequest().setAttribute("edit", Boolean.TRUE);

        return new ForwardResolution("/WEB-INF/jsp/consent/maklumat_perintah.jsp").addParameter("tab", "true");
    }

    public Resolution simpanPopup() {

        Pengguna pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        InfoAudit infoAudit = new InfoAudit();
        infoAudit.setDimasukOleh(pengguna);
        infoAudit.setTarikhMasuk(new java.util.Date());

        //-----------KPSH----------
        if (checkKPSHPopup != null) {
            PermohonanLampiranPerintah mohonLampiranPerintahKPSH = new PermohonanLampiranPerintah();
            mohonLampiranPerintahKPSH.setPermohonan(permohonan);
            mohonLampiranPerintahKPSH.setJenisLaporan("KPSH");
            mohonLampiranPerintahKPSH.setInfoAudit(infoAudit);
            conService.simpanMohonLampiranPerintah(mohonLampiranPerintahKPSH);

            for (int i = 0; i < 3; i++) {

                lampiranPerintah = new LampiranPerintah();
                if (i == 0) {
                    lampiranPerintah.setPerihalPerintah("NAMA");
                    lampiranPerintah.setCatatanPerintah(namaKPSH);
                }
                if (i == 1) {
                    lampiranPerintah.setPerihalPerintah("PENGENALAN");
                    lampiranPerintah.setCatatanPerintah(pengenalanKPSH);
                }
                if (i == 2) {
                    lampiranPerintah.setPerihalPerintah("SYER");
                    lampiranPerintah.setCatatanPerintah(syerKPSH);
                }
                lampiranPerintah.setPermohonanLampiranPerintah(mohonLampiranPerintahKPSH);
                lampiranPerintah.setInfoAudit(infoAudit);
                conService.simpanLampiranPerintah(lampiranPerintah);
            }
        }
        //-----------KKP----------
//        if (checkKKPPopup != null) {
//            PermohonanLampiranPerintah mohonLampiranPerintahKKP = new PermohonanLampiranPerintah();
//            mohonLampiranPerintahKKP.setPermohonan(permohonan);
//            mohonLampiranPerintahKKP.setJenisLaporan("KKP");
//            mohonLampiranPerintahKKP.setInfoAudit(infoAudit);
//            conService.simpanMohonLampiranPerintah(mohonLampiranPerintahKKP);
//
//            for (int i = 0; i < 2; i++) {
//
//                lampiranPerintah = new LampiranPerintah();
//                if (i == 0) {
//                    lampiranPerintah.setPerihalPerintah("NAMA");
//                    lampiranPerintah.setCatatanPerintah(namaKKP);
//                }
//                if (i == 1) {
//                    lampiranPerintah.setPerihalPerintah("PENGENALAN");
//                    lampiranPerintah.setCatatanPerintah(pengenalanKKP);
//                }
//
//                lampiranPerintah.setPermohonanLampiranPerintah(mohonLampiranPerintahKKP);
//                lampiranPerintah.setInfoAudit(infoAudit);
//                conService.simpanLampiranPerintah(lampiranPerintah);
//            }
//        }
        //-----------PPSH----------
        if (checkPPSHPopup != null) {
            PermohonanLampiranPerintah mohonLampiranPerintahPPSH = new PermohonanLampiranPerintah();
            mohonLampiranPerintahPPSH.setPermohonan(permohonan);
            mohonLampiranPerintahPPSH.setJenisLaporan("PPSH");
            mohonLampiranPerintahPPSH.setInfoAudit(infoAudit);
            conService.simpanMohonLampiranPerintah(mohonLampiranPerintahPPSH);

            for (int i = 0; i < 3; i++) {

                lampiranPerintah = new LampiranPerintah();
                if (i == 0) {
                    lampiranPerintah.setPerihalPerintah("NAMA");
                    lampiranPerintah.setCatatanPerintah(namaPPSH);
                }
                if (i == 1) {
                    lampiranPerintah.setPerihalPerintah("SERAHAN");
                    lampiranPerintah.setCatatanPerintah(noSerahanPPSH);
                }
                if (i == 2) {
                    lampiranPerintah.setPerihalPerintah("TARIKH");
                    lampiranPerintah.setCatatanPerintah(tarikhPPSH);
                }
                lampiranPerintah.setPermohonanLampiranPerintah(mohonLampiranPerintahPPSH);
                lampiranPerintah.setInfoAudit(infoAudit);
                conService.simpanLampiranPerintah(lampiranPerintah);
            }
        }
        //-----------PKP----------
        if (checkPKPPopup != null) {
            PermohonanLampiranPerintah mohonLampiranPerintahPKP = new PermohonanLampiranPerintah();
            mohonLampiranPerintahPKP.setPermohonan(permohonan);
            mohonLampiranPerintahPKP.setJenisLaporan("PKP");
            mohonLampiranPerintahPKP.setInfoAudit(infoAudit);
            conService.simpanMohonLampiranPerintah(mohonLampiranPerintahPKP);

            for (int i = 0; i < 3; i++) {

                lampiranPerintah = new LampiranPerintah();
                if (i == 0) {
                    lampiranPerintah.setPerihalPerintah("NAMA");
                    lampiranPerintah.setCatatanPerintah(namaPKP);
                }
                if (i == 1) {
                    lampiranPerintah.setPerihalPerintah("SERAHAN");
                    lampiranPerintah.setCatatanPerintah(noSerahanPKP);
                }
                if (i == 2) {
                    lampiranPerintah.setPerihalPerintah("TARIKH");
                    lampiranPerintah.setCatatanPerintah(tarikhPKP);
                }

                lampiranPerintah.setPermohonanLampiranPerintah(mohonLampiranPerintahPKP);
                lampiranPerintah.setInfoAudit(infoAudit);
                conService.simpanLampiranPerintah(lampiranPerintah);
            }
        }
        //-----------PPA----------
        if (checkPPAPopup != null) {
            PermohonanLampiranPerintah mohonLampiranPerintahPPA = new PermohonanLampiranPerintah();
            mohonLampiranPerintahPPA.setPermohonan(permohonan);
            mohonLampiranPerintahPPA.setJenisLaporan("PPA");
            mohonLampiranPerintahPPA.setInfoAudit(infoAudit);
            conService.simpanMohonLampiranPerintah(mohonLampiranPerintahPPA);

            for (int i = 0; i < 3; i++) {

                lampiranPerintah = new LampiranPerintah();
                if (i == 0) {
                    lampiranPerintah.setPerihalPerintah("NAMA");
                    lampiranPerintah.setCatatanPerintah(namaPPA);
                }
                if (i == 1) {
                    lampiranPerintah.setPerihalPerintah("SERAHAN");
                    lampiranPerintah.setCatatanPerintah(noSerahanPPA);
                }
                if (i == 2) {
                    lampiranPerintah.setPerihalPerintah("TARIKH");
                    lampiranPerintah.setCatatanPerintah(tarikhPPA);
                }

                lampiranPerintah.setPermohonanLampiranPerintah(mohonLampiranPerintahPPA);
                lampiranPerintah.setInfoAudit(infoAudit);
                conService.simpanLampiranPerintah(lampiranPerintah);
            }
        }

        if (permohonan != null) {
            return new RedirectResolution("/consent/maklumat_perintah?getMainPage").addParameter("tab", "true");
        }

        return new StreamingResolution("text/plain", "1");
    }

    public Resolution simpanCarianPopup() {

        Pengguna pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        InfoAudit infoAudit = new InfoAudit();
        infoAudit.setDimasukOleh(pengguna);
        infoAudit.setTarikhMasuk(new java.util.Date());

        //-----------PPSH----------
        if (checkPPSHPopup != null) {
            PermohonanLampiranPerintah mohonLampiranPerintahPPSH = new PermohonanLampiranPerintah();
            mohonLampiranPerintahPPSH.setPermohonan(permohonan);
            mohonLampiranPerintahPPSH.setJenisLaporan("PPSH");
            mohonLampiranPerintahPPSH.setInfoAudit(infoAudit);
            conService.simpanMohonLampiranPerintah(mohonLampiranPerintahPPSH);

            for (int i = 0; i < 3; i++) {

                lampiranPerintah = new LampiranPerintah();
                if (i == 0) {
                    lampiranPerintah.setPerihalPerintah("NAMA");
                    lampiranPerintah.setCatatanPerintah(namaPPSH);
                }
                if (i == 1) {
                    lampiranPerintah.setPerihalPerintah("SERAHAN");
                    lampiranPerintah.setCatatanPerintah(noSerahanPPSH);
                }
                if (i == 2) {
                    lampiranPerintah.setPerihalPerintah("TARIKH");
                    lampiranPerintah.setCatatanPerintah(tarikhPPSH);
                }
                lampiranPerintah.setPermohonanLampiranPerintah(mohonLampiranPerintahPPSH);
                lampiranPerintah.setInfoAudit(infoAudit);
                conService.simpanLampiranPerintah(lampiranPerintah);
            }
        }
        //-----------PKP----------
        if (checkPKPPopup != null) {
            PermohonanLampiranPerintah mohonLampiranPerintahPKP = new PermohonanLampiranPerintah();
            mohonLampiranPerintahPKP.setPermohonan(permohonan);
            mohonLampiranPerintahPKP.setJenisLaporan("PKP");
            mohonLampiranPerintahPKP.setInfoAudit(infoAudit);
            conService.simpanMohonLampiranPerintah(mohonLampiranPerintahPKP);

            for (int i = 0; i < 3; i++) {

                lampiranPerintah = new LampiranPerintah();
                if (i == 0) {
                    lampiranPerintah.setPerihalPerintah("NAMA");
                    lampiranPerintah.setCatatanPerintah(namaPKP);
                }
                if (i == 1) {
                    lampiranPerintah.setPerihalPerintah("SERAHAN");
                    lampiranPerintah.setCatatanPerintah(noSerahanPKP);
                }
                if (i == 2) {
                    lampiranPerintah.setPerihalPerintah("TARIKH");
                    lampiranPerintah.setCatatanPerintah(tarikhPKP);
                }

                lampiranPerintah.setPermohonanLampiranPerintah(mohonLampiranPerintahPKP);
                lampiranPerintah.setInfoAudit(infoAudit);
                conService.simpanLampiranPerintah(lampiranPerintah);
            }
        }
        //-----------PPA----------
        if (checkPPAPopup != null) {
            PermohonanLampiranPerintah mohonLampiranPerintahPPA = new PermohonanLampiranPerintah();
            mohonLampiranPerintahPPA.setPermohonan(permohonan);
            mohonLampiranPerintahPPA.setJenisLaporan("PPA");
            mohonLampiranPerintahPPA.setInfoAudit(infoAudit);
            conService.simpanMohonLampiranPerintah(mohonLampiranPerintahPPA);

            for (int i = 0; i < 3; i++) {

                lampiranPerintah = new LampiranPerintah();
                if (i == 0) {
                    lampiranPerintah.setPerihalPerintah("NAMA");
                    lampiranPerintah.setCatatanPerintah(namaPPA);
                }
                if (i == 1) {
                    lampiranPerintah.setPerihalPerintah("SERAHAN");
                    lampiranPerintah.setCatatanPerintah(noSerahanPPA);
                }
                if (i == 2) {
                    lampiranPerintah.setPerihalPerintah("TARIKH");
                    lampiranPerintah.setCatatanPerintah(tarikhPPA);
                }

                lampiranPerintah.setPermohonanLampiranPerintah(mohonLampiranPerintahPPA);
                lampiranPerintah.setInfoAudit(infoAudit);
                conService.simpanLampiranPerintah(lampiranPerintah);
            }
        }

        if (permohonan != null) {
            return new RedirectResolution("/consent/maklumat_perintah?getMainPage").addParameter("tab", "true");
        }

        return new StreamingResolution("text/plain", "1");
    }

    public Resolution simpanEdit() {

        Pengguna pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        InfoAudit infoAudit = new InfoAudit();

        //-----------KPSH----------
        if (checkKPSHPopup != null) {
            LOG.info("---Simpan Edit Permohonan Lampiran perintah KPSH : " + mohonLampiranPerintah.getIdPermohonanPerintah() + " ---");
            PermohonanLampiranPerintah mohonLampiranPerintahKPSH = new PermohonanLampiranPerintah();
            mohonLampiranPerintahKPSH = permohonanLampiranPerintahDAO.findById(mohonLampiranPerintah.getIdPermohonanPerintah());

            for (LampiranPerintah lampPerintah : mohonLampiranPerintahKPSH.getSenaraiLampiranPerintah()) {

                infoAudit = lampPerintah.getInfoAudit();
                infoAudit.setDiKemaskiniOleh(pengguna);
                infoAudit.setTarikhKemaskini(new java.util.Date());

                if (lampPerintah.getPerihalPerintah().equals("NAMA")) {
                    lampPerintah.setCatatanPerintah(namaKPSH);
                } else if (lampPerintah.getPerihalPerintah().equals("PENGENALAN")) {
                    lampPerintah.setCatatanPerintah(pengenalanKPSH);
                } else if (lampPerintah.getPerihalPerintah().equals("SYER")) {
                    lampPerintah.setCatatanPerintah(syerKPSH);
                }

                lampPerintah.setInfoAudit(infoAudit);
                conService.simpanLampiranPerintah(lampPerintah);
            }
        }
        //-----------KKP----------
//        if (checkKKPPopup != null) {
//            LOG.info("---Simpan Edit Permohonan Lampiran perintah KKP : " + mohonLampiranPerintah.getIdPermohonanPerintah() + " ---");
//            PermohonanLampiranPerintah mohonLampiranPerintahKKP = new PermohonanLampiranPerintah();
//            mohonLampiranPerintahKKP = permohonanLampiranPerintahDAO.findById(mohonLampiranPerintah.getIdPermohonanPerintah());
//
//            for (LampiranPerintah lampPerintah : mohonLampiranPerintahKKP.getSenaraiLampiranPerintah()) {
//
//                infoAudit = lampPerintah.getInfoAudit();
//                infoAudit.setDiKemaskiniOleh(pengguna);
//                infoAudit.setTarikhKemaskini(new java.util.Date());
//
//                if (lampPerintah.getPerihalPerintah().equals("NAMA")) {
//                    lampPerintah.setCatatanPerintah(namaKKP);
//                } else if (lampPerintah.getPerihalPerintah().equals("PENGENALAN")) {
//                    lampPerintah.setCatatanPerintah(pengenalanKKP);
//                }
//
//                lampPerintah.setInfoAudit(infoAudit);
//                conService.simpanLampiranPerintah(lampPerintah);
//            }
//        }
        //-----------PPSH----------
        if (checkPPSHPopup != null) {
            LOG.info("---Simpan Edit Permohonan Lampiran perintah PPSH : " + mohonLampiranPerintah.getIdPermohonanPerintah() + " ---");
            PermohonanLampiranPerintah mohonLampiranPerintahPPSH = new PermohonanLampiranPerintah();
            mohonLampiranPerintahPPSH = permohonanLampiranPerintahDAO.findById(mohonLampiranPerintah.getIdPermohonanPerintah());

            for (LampiranPerintah lampPerintah : mohonLampiranPerintahPPSH.getSenaraiLampiranPerintah()) {

                infoAudit = lampPerintah.getInfoAudit();
                infoAudit.setDiKemaskiniOleh(pengguna);
                infoAudit.setTarikhKemaskini(new java.util.Date());

                if (lampPerintah.getPerihalPerintah().equals("NAMA")) {
                    lampPerintah.setCatatanPerintah(namaPPSH);
                } else if (lampPerintah.getPerihalPerintah().equals("SERAHAN")) {
                    lampPerintah.setCatatanPerintah(noSerahanPPSH);
                } else if (lampPerintah.getPerihalPerintah().equals("TARIKH")) {
                    lampPerintah.setCatatanPerintah(tarikhPPSH);
                }

                lampPerintah.setInfoAudit(infoAudit);
                conService.simpanLampiranPerintah(lampPerintah);
            }
        }
        //-----------PKP----------
        if (checkPKPPopup != null) {
            LOG.info("---Simpan Edit Permohonan Lampiran perintah PKP : " + mohonLampiranPerintah.getIdPermohonanPerintah() + " ---");
            PermohonanLampiranPerintah mohonLampiranPerintahPKP = new PermohonanLampiranPerintah();
            mohonLampiranPerintahPKP = permohonanLampiranPerintahDAO.findById(mohonLampiranPerintah.getIdPermohonanPerintah());

            for (LampiranPerintah lampPerintah : mohonLampiranPerintahPKP.getSenaraiLampiranPerintah()) {

                infoAudit = lampPerintah.getInfoAudit();
                infoAudit.setDiKemaskiniOleh(pengguna);
                infoAudit.setTarikhKemaskini(new java.util.Date());

                if (lampPerintah.getPerihalPerintah().equals("NAMA")) {
                    lampPerintah.setCatatanPerintah(namaPKP);
                } else if (lampPerintah.getPerihalPerintah().equals("SERAHAN")) {
                    lampPerintah.setCatatanPerintah(noSerahanPKP);
                } else if (lampPerintah.getPerihalPerintah().equals("TARIKH")) {
                    lampPerintah.setCatatanPerintah(tarikhPKP);
                }

                lampPerintah.setInfoAudit(infoAudit);
                conService.simpanLampiranPerintah(lampPerintah);
            }
        }
        //-----------PPA----------
        if (checkPPAPopup != null) {
            LOG.info("---Simpan Edit Permohonan Lampiran perintah PPA : " + mohonLampiranPerintah.getIdPermohonanPerintah() + " ---");
            PermohonanLampiranPerintah mohonLampiranPerintahPPA = new PermohonanLampiranPerintah();
            mohonLampiranPerintahPPA = permohonanLampiranPerintahDAO.findById(mohonLampiranPerintah.getIdPermohonanPerintah());

            for (LampiranPerintah lampPerintah : mohonLampiranPerintahPPA.getSenaraiLampiranPerintah()) {

                infoAudit = lampPerintah.getInfoAudit();
                infoAudit.setDiKemaskiniOleh(pengguna);
                infoAudit.setTarikhKemaskini(new java.util.Date());

                if (lampPerintah.getPerihalPerintah().equals("NAMA")) {
                    lampPerintah.setCatatanPerintah(namaPPA);
                } else if (lampPerintah.getPerihalPerintah().equals("SERAHAN")) {
                    lampPerintah.setCatatanPerintah(noSerahanPPA);
                } else if (lampPerintah.getPerihalPerintah().equals("TARIKH")) {
                    lampPerintah.setCatatanPerintah(tarikhPPA);
                }

                lampPerintah.setInfoAudit(infoAudit);
                conService.simpanLampiranPerintah(lampPerintah);
            }
        }

        if (permohonan != null) {
            return new RedirectResolution("/consent/maklumat_perintah?getMainPage").addParameter("tab", "true");
        }

        return new StreamingResolution("text/plain", "1");
    }

    public Resolution delete() {
        LOG.info("---Delete Permohonan Lampiran Perintah---");
        String[] ids = getContext().getRequest().getParameterValues("ids");
        conService.deleteMohonLampiranPerintah(ids);
        return new RedirectResolution(MaklumatPerintahActionBean.class).addParameter("tab", "true");
    }

    public List<Map<String, String>> getListKPSH() {
        return listKPSH;
    }

    public void setListKPSH(List<Map<String, String>> listKPSH) {
        this.listKPSH = listKPSH;
    }

//    public List<Map<String, String>> getListKKP() {
//        return listKKP;
//    }
//
//    public void setListKKP(List<Map<String, String>> listKKP) {
//        this.listKKP = listKKP;
//    }
    public List<Map<String, String>> getListPPSH() {
        return listPPSH;
    }

    public void setListPPSH(List<Map<String, String>> listPPSH) {
        this.listPPSH = listPPSH;
    }

    public Permohonan getPermohonan() {
        return permohonan;
    }

    public void setPermohonan(Permohonan permohonan) {
        this.permohonan = permohonan;
    }

    public String getNamaKPSH() {
        return namaKPSH;
    }

    public void setNamaKPSH(String namaKPSH) {
        this.namaKPSH = namaKPSH;
    }

    public String getPengenalanKPSH() {
        return pengenalanKPSH;
    }

    public void setPengenalanKPSH(String pengenalanKPSH) {
        this.pengenalanKPSH = pengenalanKPSH;
    }

    public String getSyerKPSH() {
        return syerKPSH;
    }

    public void setSyerKPSH(String syerKPSH) {
        this.syerKPSH = syerKPSH;
    }

//    public String getNamaKKP() {
//        return namaKKP;
//    }
//
//    public void setNamaKKP(String namaKKP) {
//        this.namaKKP = namaKKP;
//    }
    public String getNamaPPSH() {
        return namaPPSH;
    }

    public void setNamaPPSH(String namaPPSH) {
        this.namaPPSH = namaPPSH;
    }

    public String getNoSerahanPKP() {
        return noSerahanPKP;
    }

    public void setNoSerahanPKP(String noSerahanPKP) {
        this.noSerahanPKP = noSerahanPKP;
    }

    public String getNoSerahanPPA() {
        return noSerahanPPA;
    }

    public void setNoSerahanPPA(String noSerahanPPA) {
        this.noSerahanPPA = noSerahanPPA;
    }

    public String getNoSerahanPPSH() {
        return noSerahanPPSH;
    }

    public void setNoSerahanPPSH(String noSerahanPPSH) {
        this.noSerahanPPSH = noSerahanPPSH;
    }

//    public String getPengenalanKKP() {
//        return pengenalanKKP;
//    }
//
//    public void setPengenalanKKP(String pengenalanKKP) {
//        this.pengenalanKKP = pengenalanKKP;
//    }
    public String getTarikhPKP() {
        return tarikhPKP;
    }

    public void setTarikhPKP(String tarikhPKP) {
        this.tarikhPKP = tarikhPKP;
    }

    public String getTarikhPPA() {
        return tarikhPPA;
    }

    public void setTarikhPPA(String tarikhPPA) {
        this.tarikhPPA = tarikhPPA;
    }

    public String getTarikhPPSH() {
        return tarikhPPSH;
    }

    public void setTarikhPPSH(String tarikhPPSH) {
        this.tarikhPPSH = tarikhPPSH;
    }

    public String getCheckKKP() {
        return checkKKP;
    }

    public void setCheckKKP(String checkKKP) {
        this.checkKKP = checkKKP;
    }

    public String getCheckKPA() {
        return checkKPA;
    }

    public void setCheckKPA(String checkKPA) {
        this.checkKPA = checkKPA;
    }

    public String getCheckKPSH() {
        return checkKPSH;
    }

    public void setCheckKPSH(String checkKPSH) {
        this.checkKPSH = checkKPSH;
    }

    public String getCheckPKP() {
        return checkPKP;
    }

    public void setCheckPKP(String checkPKP) {
        this.checkPKP = checkPKP;
    }

    public String getCheckPPA() {
        return checkPPA;
    }

    public void setCheckPPA(String checkPPA) {
        this.checkPPA = checkPPA;
    }

    public String getCheckPPSH() {
        return checkPPSH;
    }

    public void setCheckPPSH(String checkPPSH) {
        this.checkPPSH = checkPPSH;
    }

//    public String getCheckKKPPopup() {
//        return checkKKPPopup;
//    }
//
//    public void setCheckKKPPopup(String checkKKPPopup) {
//        this.checkKKPPopup = checkKKPPopup;
//    }
    public String getCheckKPSHPopup() {
        return checkKPSHPopup;
    }

    public void setCheckKPSHPopup(String checkKPSHPopup) {
        this.checkKPSHPopup = checkKPSHPopup;
    }

    public String getCheckPPSHPopup() {
        return checkPPSHPopup;
    }

    public void setCheckPPSHPopup(String checkPPSHPopup) {
        this.checkPPSHPopup = checkPPSHPopup;
    }

    public String getCheckPKPPopup() {
        return checkPKPPopup;
    }

    public void setCheckPKPPopup(String checkPKPPopup) {
        this.checkPKPPopup = checkPKPPopup;
    }

    public String getCheckPPAPopup() {
        return checkPPAPopup;
    }

    public void setCheckPPAPopup(String checkPPAPopup) {
        this.checkPPAPopup = checkPPAPopup;
    }

    public List<Map<String, String>> getListPKP() {
        return listPKP;
    }

    public void setListPKP(List<Map<String, String>> listPKP) {
        this.listPKP = listPKP;
    }

    public List<Map<String, String>> getListPPA() {
        return listPPA;
    }

    public void setListPPA(List<Map<String, String>> listPPA) {
        this.listPPA = listPPA;
    }

    public PermohonanLampiranPerintah getMohonLampiranPerintah() {
        return mohonLampiranPerintah;
    }

    public void setMohonLampiranPerintah(PermohonanLampiranPerintah mohonLampiranPerintah) {
        this.mohonLampiranPerintah = mohonLampiranPerintah;
    }

    public String getNamaPKP() {
        return namaPKP;
    }

    public void setNamaPKP(String namaPKP) {
        this.namaPKP = namaPKP;
    }

    public String getNamaPPA() {
        return namaPPA;
    }

    public void setNamaPPA(String namaPPA) {
        this.namaPPA = namaPPA;
    }

    public Permohonan getPermohonanCarian() {
        return permohonanCarian;
    }

    public void setPermohonanCarian(Permohonan permohonanCarian) {
        this.permohonanCarian = permohonanCarian;
    }

    public PermohonanLampiranPerintah getPermohonanLampiranPerintahKPSH() {
        return permohonanLampiranPerintahKPSH;
    }

    public void setPermohonanLampiranPerintahKPSH(PermohonanLampiranPerintah permohonanLampiranPerintahKPSH) {
        this.permohonanLampiranPerintahKPSH = permohonanLampiranPerintahKPSH;
    }
}
