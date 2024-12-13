/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.daftar;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.dao.HakmilikDAO;
import etanah.dao.HakmilikUrusanDAO;
import etanah.dao.KodPerhubunganPermohonanDAO;
import etanah.dao.PermohonanDAO;
import etanah.model.Hakmilik;
import etanah.model.HakmilikPermohonan;
import etanah.model.HakmilikUrusan;
import etanah.model.InfoAudit;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.model.PermohonanAtasPerserahan;
import etanah.model.PermohonanHubungan;
import etanah.service.common.HakmilikUrusanService;
import etanah.service.common.PermohonanAtasPerserahanService;
import etanah.service.common.PermohonanService;
import etanah.view.etanahActionBeanContext;
import java.util.ArrayList;
import java.util.List;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.RedirectResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

/**
 *
 * @author fikri
 */
@UrlBinding("/daftar/gadaian")
public class Gadaian extends AbleActionBean {

    private static Logger LOG = Logger.getLogger(Gadaian.class);
    private Permohonan permohonan;
    private List<HakmilikUrusan> hakmilikUrusanList;
    private List<HakmilikUrusan> hakmilikUrusanListTaktif;
    private List<PermohonanAtasPerserahan> senaraiMau;
    @Inject
    HakmilikUrusanService hakmilikUrusanService;
    @Inject
    PermohonanAtasPerserahanService permohonanAtasPerserahanService;
    @Inject
    HakmilikUrusanDAO hakmilikUrusanDAO;
    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    PermohonanService permohonanService;
    @Inject
    KodPerhubunganPermohonanDAO kodPerhubunganPermohonanDAO;
    @Inject
    private HakmilikDAO hakmilikDAO;
    private String[] chkbox;
    private PermohonanAtasPerserahan mau;
    private String idHakmilik;
    private Pengguna pengguna;
    private List<PermohonanHubungan> senaraiPermohonanHubungan;
    private List<HakmilikPermohonan> senaraiHakmilikTerlibat;
    private List senaraiList;
    private List<PermohonanHubungan> senaraiGadaianTangguh;
    private List<PermohonanHubungan> senaraiGadaianLepas;
    private static String[] URUSAN_NOT_TO_DELETE = {
        "PHMMK",
        "PHMM",
        "PMP",
        "PMG",
        "PNPBA",
        "PNPBK",
        "PMPJK",
        "JPGPJ",
        "JMGPJ",
        "PH30A"
    };
    private List<Permohonan> mohonTolakGantung;

    @DefaultHandler
    public Resolution searchGadaian() {
        String msg = getContext().getRequest().getParameter("message");
        String error = getContext().getRequest().getParameter("error");
        if (StringUtils.isNotBlank(msg)) {
            addSimpleMessage(msg);
        }
        if (StringUtils.isNotBlank(error)) {
            addSimpleError(error);
        }
        return new JSP("daftar/carian_gadaian.jsp").addParameter("tab", "true");
    }

    public Resolution maklumatTidakBerkuatkuasa() {
        return new JSP("common/rekod_sejarah.jsp").addParameter("tab", "true");
    }

    public Resolution searchPenangguhanGadaian() {

        String msg = getContext().getRequest().getParameter("message");
        String error = getContext().getRequest().getParameter("error");
        if (StringUtils.isNotBlank(msg)) {
            addSimpleMessage(msg);
        }
        if (StringUtils.isNotBlank(error)) {
            addSimpleError(error);
        }

        if (senaraiPermohonanHubungan.size() > 0) {
            senaraiGadaianTangguh = new ArrayList<PermohonanHubungan>();
            senaraiGadaianLepas = new ArrayList<PermohonanHubungan>();
            for (PermohonanHubungan ph : senaraiPermohonanHubungan) {
                if (ph.getHubunganPermohonan() == null) {
                    continue;
                }
                if (ph.getHubunganPermohonan().getKod().equals("GT")) {
                    senaraiGadaianTangguh.add(ph);
                } else if (ph.getHubunganPermohonan().getKod().equals("GL")) {
                    senaraiGadaianLepas.add(ph);
                }
            }
        }

        return new JSP("daftar/penangguhan_gadaian.jsp").addParameter("tab", "true");
    }

    public Resolution save() {
        StringBuilder msg = new StringBuilder();
        StringBuilder error_msg = new StringBuilder();
        InfoAudit ia = new InfoAudit();
        ia.setDimasukOleh(pengguna);
        ia.setTarikhMasuk(new java.util.Date());

        List<PermohonanHubungan> tempList = new ArrayList<PermohonanHubungan>();
        String[] hakmilikTerlibat = getContext().getRequest().getParameterValues("hakmilikTerlibat");
        String idHakmilik = getContext().getRequest().getParameter("idHakmilik");
        String copyToAllHakmiliks = getContext().getRequest().getParameter("copyToAll");

        if (chkbox == null) {
            if (error_msg.length() > 0) {
                error_msg.append(",");
            }
            error_msg.append("Sila Pilih Gadaian.");
            return new JSP("daftar/carian_gadaian.jsp")
                    .addParameter("tab", "true")
                    .addParameter("idHakmilik", idHakmilik)
                    .addParameter("error", error_msg.toString());
        }

        if (chkbox.length > 0) {
            for (int i = 0; i < chkbox.length; i++) {
                boolean flag = false;
                String idPermohonan = chkbox[i];
                for (PermohonanHubungan ph : senaraiPermohonanHubungan) {
                    if (ph.getPermohonanSasaran().getIdPermohonan().equals(idPermohonan)
                            && ph.getHakmilik().getIdHakmilik().equals(idHakmilik)) {
                        if (error_msg.length() > 0) {
                            error_msg.append(",");
                        }
                        error_msg.append("Urusan sudah dipilih.");
                        flag = true;
                        break;
                    }
                }
                if (flag) {
                    continue;
                }

                Permohonan p = permohonanService.findById(idPermohonan);

                PermohonanHubungan hb = new PermohonanHubungan();
                hb.setCawangan(pengguna.getKodCawangan());
                hb.setPermohonanSumber(permohonan);
                if ("JDGPJ".equals(permohonan.getKodUrusan().getKod()) || "JMGPJ".equals(permohonan.getKodUrusan().getKod()) || "JPGPJ".equals(permohonan.getKodUrusan().getKod())) {
                    List<PermohonanHubungan> senarai = permohonanService.getSenaraiHubungan(p.getIdPermohonan(), idHakmilik);
                    if (!senarai.isEmpty()) {
                        PermohonanHubungan ph = senarai.get(0);
                        hb.setCatatan(ph.getPermohonanSasaran().getIdPermohonan());
                    }
                } else {
                    hb.setCatatan(idHakmilik);//temp solution , save id hakmilik                        
                }
                Hakmilik hm = new Hakmilik();
                hm.setIdHakmilik(idHakmilik);
                hb.setHakmilik(hm);  //  add by azri: to save one id hakmilik in table mohon_hbgn at colunm id_hakmilik 30/05/2013
                if (ArrayUtils.contains(URUSAN_NOT_TO_DELETE, permohonan.getKodUrusan().getKod())) {
                    hb.setHubunganPermohonan(kodPerhubunganPermohonanDAO.findById("TB"));
                } else {
                    hb.setHubunganPermohonan(kodPerhubunganPermohonanDAO.findById("B"));
                }
                hb.setPermohonanSasar(p);
                hb.setInfoAudit(ia);
                tempList.add(hb);

                if (StringUtils.isNotBlank(copyToAllHakmiliks)) {
                    boolean fg = true;
                    for (HakmilikPermohonan hp : senaraiHakmilikTerlibat) {
                        if (hp == null
                                || hp.getHakmilik() == null
                                || hp.getHakmilik().getIdHakmilik().equals(idHakmilik)) {
                            continue;
                        }
                        for (PermohonanHubungan ph : senaraiPermohonanHubungan) {
                            if (ph.getPermohonanSasaran()
                                    .getIdPermohonan().equals(idPermohonan)
                                    && ph.getHakmilik().getIdHakmilik().equals(hp.getHakmilik().getIdHakmilik())) {
                                fg = false;
                                break;
                            }
                        }

                        if (fg) {

                            hb = new PermohonanHubungan();
                            hb.setCawangan(pengguna.getKodCawangan());
                            hb.setPermohonanSumber(permohonan);
                            hb.setCatatan(hp.getHakmilik().getIdHakmilik());//temp solution , save id hakmilik
                            //     add by azri: to save many id hakmilik in table mohon_hbgn at colunm id_hakmilik 30/05/2013
                            Hakmilik moreThenOneHakmilik = new Hakmilik();
                            moreThenOneHakmilik.setIdHakmilik(hp.getHakmilik().getIdHakmilik());
                            hb.setHakmilik(moreThenOneHakmilik);
                            if (ArrayUtils.contains(URUSAN_NOT_TO_DELETE, permohonan.getKodUrusan().getKod())) {
                                hb.setHubunganPermohonan(kodPerhubunganPermohonanDAO.findById("TB"));
                            } else {
                                hb.setHubunganPermohonan(kodPerhubunganPermohonanDAO.findById("B"));
                            }
                            hb.setPermohonanSasar(p);
                            hb.setInfoAudit(ia);
                            tempList.add(hb);
                        }
                    }
                }
            }

            permohonanService.savePermohonanHubungan(tempList);
            if (msg.length() > 0) {
                msg.append(",");
            }
            msg.append("Kemasukan data BERJAYA.");
        }

        return new RedirectResolution(Gadaian.class, "searchGadaian")
                .addParameter("tab", "true")
                .addParameter("message", msg.toString())
                .addParameter("idHakmilik", idHakmilik)
                .addParameter("error", error_msg.toString());
    }

    public Resolution saveMelepasGadaian() {
        StringBuilder msg = new StringBuilder();
        StringBuilder error_msg = new StringBuilder();
        InfoAudit ia = new InfoAudit();
        ia.setDimasukOleh(pengguna);
        ia.setTarikhMasuk(new java.util.Date());
        List<PermohonanAtasPerserahan> tmp = new ArrayList<PermohonanAtasPerserahan>();
        if (chkbox == null) {
            if (error_msg.length() > 0) {
                error_msg.append(",");
            }
            error_msg.append("Sila Pilih Gadaian.");
            return new JSP("daftar/carian_gadaian.jsp")
                    .addParameter("tab", "true")
                    .addParameter("error", error_msg.toString());
        }
        if (chkbox.length > 0) {
            for (int i = 0; i < chkbox.length; i++) {
                boolean flag = false;
                Long id = Long.parseLong(chkbox[i]);
                //remove duplicate item
                for (PermohonanAtasPerserahan permohonanAtasPerserahan : senaraiMau) {
                    if (permohonanAtasPerserahan.getUrusan().getIdUrusan() == id) {
                        //already added in permohonan. just leave it
                        if (error_msg.length() > 0) {
                            error_msg.append(",");
                        }
                        error_msg.append("Urusan sudah dipilih.");
                        flag = true;
                    }
                }
                if (flag) {
                    continue;
                }
                HakmilikUrusan hu = hakmilikUrusanDAO.findById(id);
                mau = new PermohonanAtasPerserahan();
                mau.setUrusan(hu);
                mau.setPermohonan(permohonan);
                mau.setInfoAudit(ia);
                tmp.add(mau);
            }
            if (permohonanAtasPerserahanService.save(tmp)) {
                if (msg.length() > 0) {
                    msg.append(",");
                }
                msg.append("Kemasukan data BERJAYA.");
            } else {
                if (error_msg.length() > 0) {
                    error_msg.append(",");
                }
                error_msg.append("Kemasukan data GAGAL.");
            }
        }
        return new RedirectResolution(Gadaian.class, "searchGadaian")
                .addParameter("tab", "true")
                .addParameter("message", msg.toString())
                .addParameter("error", error_msg.toString());
    }

    public Resolution saveUrusanTukarNama() {
        StringBuilder msg = new StringBuilder();
        StringBuilder error_msg = new StringBuilder();
        InfoAudit ia = new InfoAudit();
        idHakmilik = getContext().getRequest().getParameter("idHakmilik");
        ia.setDimasukOleh(pengguna);
        ia.setTarikhMasuk(new java.util.Date());
        List<PermohonanAtasPerserahan> tmp = new ArrayList<PermohonanAtasPerserahan>();
        if (chkbox == null) {
            if (error_msg.length() > 0) {
                error_msg.append(",");
            }
            error_msg.append("Sila Pilih Gadaian.");
            return new JSP("daftar/carian_gadaian.jsp")
                    .addParameter("tab", "true")
                    .addParameter("error", error_msg.toString());
        }
        if (chkbox.length > 0) {
//      for (int i = 0; i < chkbox.length; i++) {
            for (String idPermohonan : chkbox) {
                boolean flag = false;
//        String idPermohonan = chkbox[i];
                //remove duplicate item
                HakmilikUrusan hu = hakmilikUrusanService.findByIdPerserahanIdHakmilik(idPermohonan, idHakmilik);
                Long idUrusan = hu.getIdUrusan();
                PermohonanAtasPerserahan mau = permohonanAtasPerserahanService.findMohonAtasUrusanByIDPermohonanAndIdUrusan(permohonan.getIdPermohonan(), idUrusan);
                if (mau != null) {
                    for (PermohonanAtasPerserahan permohonanAtasPerserahan : senaraiMau) {
                        if (permohonanAtasPerserahan.getPermohonanBaru() != null
                                && permohonanAtasPerserahan.getPermohonanBaru().getIdPermohonan().equals(idPermohonan)) {
                            //already added in permohonan. just leave it
                            if (error_msg.length() > 0) {
                                error_msg.append(",");
                            }
                            error_msg.append("Urusan sudah dipilih.");
                            flag = true;
                        }
                    }
                    if (flag) {
                        continue;
                    }

                    mau = new PermohonanAtasPerserahan();
                    mau.setUrusan(hu);
                    mau.setPermohonan(permohonan);
                    mau.setPermohonanBaru(StringUtils.isNotBlank(idPermohonan) ? permohonanDAO.findById(idPermohonan) : null);
                    mau.setInfoAudit(ia);
                    tmp.add(mau);
                } else {
                    mau = new PermohonanAtasPerserahan();
                    mau.setUrusan(hu);
                    mau.setPermohonan(permohonan);
                    mau.setPermohonanBaru(StringUtils.isNotBlank(idPermohonan) ? permohonanDAO.findById(idPermohonan) : null);
                    mau.setInfoAudit(ia);
                    tmp.add(mau);
                }
            }
            if (permohonanAtasPerserahanService.save(tmp)) {
                if (msg.length() > 0) {
                    msg.append(",");
                }
                msg.append("Kemasukan data BERJAYA.");
            } else {
                if (error_msg.length() > 0) {
                    error_msg.append(",");
                }
                error_msg.append("Kemasukan data GAGAL.");
            }
        }
        return new RedirectResolution(Gadaian.class, "searchGadaian")
                .addParameter("tab", "true")
                .addParameter("message", msg.toString())
                .addParameter("error", error_msg.toString());
    }

    public Resolution savePenangguhanGadaian() {

        StringBuilder msg = new StringBuilder();
        StringBuilder error_msg = new StringBuilder();
        InfoAudit ia = new InfoAudit();
        ia.setDimasukOleh(pengguna);
        ia.setTarikhMasuk(new java.util.Date());

        String[] gadaianTangguh = getContext().getRequest().getParameterValues("tangguh");
        String[] gadaianLepas = getContext().getRequest().getParameterValues("lepas");
        String[] hakmilikTerlibat = getContext().getRequest().getParameterValues("hakmilikTerlibat");

        String idHakmilik = getContext().getRequest().getParameter("idHakmilik");

        List<PermohonanHubungan> tempList = new ArrayList<PermohonanHubungan>();

        if (etanah.util.StringUtils.isBlank(gadaianTangguh)) {
            if (error_msg.length() > 0) {
                error_msg.append(",");
            }
            error_msg.append("Sila Pilih Gadaian Tangguh.");
            return new RedirectResolution(Gadaian.class, "searchPenangguhanGadaian")
                    .addParameter("tab", "true")
                    .addParameter("idHakmilik", idHakmilik)
                    .addParameter("error", error_msg.toString());
        }

        Hakmilik hm = hakmilikDAO.findById(idHakmilik);

        if (etanah.util.StringUtils.isNotBlank(gadaianTangguh) && gadaianTangguh.length > 0) {
            boolean isExist = false;

            String idPermohonan = gadaianTangguh[0];
            for (PermohonanHubungan ph : senaraiPermohonanHubungan) {
                if (ph.getPermohonanSasaran().getIdPermohonan().equals(idPermohonan)) {
                    if (error_msg.length() > 0) {
                        error_msg.append(",");
                    }
                    error_msg.append("Urusan tangguh sudah dipilih ");
                    isExist = true;
                    break;
                }
            }

            if (!isExist) {
                Permohonan p = permohonanService.findById(idPermohonan);

                PermohonanHubungan hb = new PermohonanHubungan();
                hb.setCawangan(pengguna.getKodCawangan());
                hb.setPermohonanSumber(permohonan);
                hb.setCatatan(hakmilikTerlibat[0]);//temp solution , save id hakmilik
                hb.setHubunganPermohonan(kodPerhubunganPermohonanDAO.findById("GT"));
                hb.setHakmilik(hm);
                hb.setPermohonanSasar(p);
                hb.setInfoAudit(ia);
                tempList.add(hb);
            }
        }

        if (etanah.util.StringUtils.isNotBlank(gadaianLepas) && gadaianLepas.length > 0) {
            boolean isExist = false;

            String idPermohonan = gadaianLepas[0];
            for (PermohonanHubungan ph : senaraiPermohonanHubungan) {
                if (ph.getPermohonanSasaran().getIdPermohonan().equals(idPermohonan)) {
                    if (error_msg.length() > 0) {
                        error_msg.append(",");
                    }
                    error_msg.append("Urusan selepas sudah dipilih");
                    isExist = true;
                    break;
                }
            }

            if (!isExist) {
                Permohonan p = permohonanService.findById(idPermohonan);

                PermohonanHubungan hb = new PermohonanHubungan();
                hb.setCawangan(pengguna.getKodCawangan());
                hb.setPermohonanSumber(permohonan);
                hb.setCatatan(hakmilikTerlibat[0]);//temp solution , save id hakmilik
                hb.setHubunganPermohonan(kodPerhubunganPermohonanDAO.findById("GL"));
                hb.setPermohonanSasar(p);
                hb.setInfoAudit(ia);
                hb.setHakmilik(hm);
                tempList.add(hb);
            }
        }

        if (tempList.size() > 0) {
            permohonanService.savePermohonanHubungan(tempList);
        }

        return new RedirectResolution(Gadaian.class, "searchPenangguhanGadaian")
                .addParameter("idHakmilik", idHakmilik)
                .addParameter("tab", "true")
                .addParameter("message", msg.toString())
                .addParameter("idHakmilik", idHakmilik)
                .addParameter("error", error_msg.toString());
    }

    public Resolution delete() {
        String id = getContext().getRequest().getParameter("id");
        permohonanAtasPerserahanService.delete(Long.parseLong(id));
        return new RedirectResolution(Gadaian.class, "searchGadaian");
    }

    public Resolution deleteSelectedItem() {
        String[] uids = getContext().getRequest().getParameterValues("uids");

        String kodUrusan = permohonan.getKodUrusan().getKod();
        String idHakmilik = getContext().getRequest().getParameter("idHakmilik");

        if (uids.length > 0) {
//            permohonanAtasPerserahanService.deleteMultiple(uids);
            permohonanAtasPerserahanService.deleteHubungan(uids);
        }

        if (kodUrusan.equalsIgnoreCase("GDT")) {
            return new RedirectResolution(Gadaian.class, "searchPenangguhanGadaian").addParameter("idHakmilik", idHakmilik);
        } else {
            return new RedirectResolution(Gadaian.class, "searchGadaian").addParameter("idHakmilik", idHakmilik);
        }
    }

    public Resolution deleteSelectedItemTN() {
        String[] uids = getContext().getRequest().getParameterValues("uids");

        String kodUrusan = permohonan.getKodUrusan().getKod();
        String idHakmilik = getContext().getRequest().getParameter("idHakmilik");

        if (uids.length > 0) {
            permohonanAtasPerserahanService.deleteMultiple(uids);
//      permohonanAtasPerserahanService.deleteHubungan(uids);
        }

        return new RedirectResolution(Gadaian.class, "searchGadaian").addParameter("idHakmilik", idHakmilik);
    }

    @Before(stages = {LifecycleStage.BindingAndValidation}, on = {"!delete"})
    public void rehydrate() {
        pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = permohonanDAO.findById(idPermohonan);
        List<HakmilikUrusan> tmp = new ArrayList<HakmilikUrusan>();
        hakmilikUrusanList = new ArrayList<HakmilikUrusan>();
        hakmilikUrusanListTaktif = new ArrayList<HakmilikUrusan>();
        mohonTolakGantung = new ArrayList();
        senaraiList = new ArrayList();
        senaraiHakmilikTerlibat = permohonan.getSenaraiHakmilik();
        idHakmilik = getContext().getRequest().getParameter("idHakmilik");

        //for multiple hakmilik
        //todo berangkai
        if (!senaraiHakmilikTerlibat.isEmpty()) {
            if (senaraiHakmilikTerlibat.size() > 0) {
                getContext().getRequest().setAttribute("moreThanOneHakmilik", "true");
            }
            String idKump = permohonan.getIdKumpulan();
            List<String> ids = new ArrayList<String>();
            if (StringUtils.isBlank(idHakmilik)) {
                if (senaraiHakmilikTerlibat.size() > 0) {
                    idHakmilik = senaraiHakmilikTerlibat.get(0).getHakmilik().getIdHakmilik();
                }
            }

            if (StringUtils.isNotBlank(idKump)) {
//                ids = new ArrayList<String>();
                List<Permohonan> list = permohonanService.getPermohonanByIdKump(idKump);
                for (Permohonan p : list) {
                    if (p == null || permohonan.getIdPermohonan().equals(p.getIdPermohonan())) {
                        continue;
                    }
                    ids.add(p.getIdPermohonan());
                }
            } else {
                ids.add(permohonan.getIdPermohonan());
            }

//            List<HakmilikPermohonan> listHakmilik = permohonan.getSenaraiHakmilik();
            if (ids.size() > 0) {
                senaraiPermohonanHubungan = permohonanService.validatePerhubunganPermohonan(ids, idHakmilik);
            }

            if (StringUtils.isNotBlank(idKump)) {
                for (HakmilikPermohonan hp : senaraiHakmilikTerlibat) {
                    if (hp == null) {
                        continue;
                    }
                    if (StringUtils.isNotBlank(idHakmilik)
                            && (!hp.getHakmilik().getIdHakmilik().equals(idHakmilik))) {
                        continue;
                    }
                    tmp = hakmilikUrusanService.searchHakmilikUrusanGadaian(permohonan.getKodUrusan().getKod(), hp.getHakmilik().getIdHakmilik(), ids);

                    for (HakmilikUrusan hu : tmp) {
                        boolean f = true;
                        if (hu == null) {
                            continue;
                        }
                        //added by eda on 27/12/2017 - add if statement senaraiPermohonanHubungan null pointer
                        if(senaraiPermohonanHubungan != null){
                                for (PermohonanHubungan ph : senaraiPermohonanHubungan) {
                                    if (ph == null || ph.getHubunganPermohonan().getKod().equals("TB")
                                            || ph.getPermohonanSumber().getKumpulanNo() > permohonan.getKumpulanNo()) {
                                        continue;
                                    }
                                    if (hu.getIdPerserahan().equals(ph.getPermohonanSasaran().getIdPermohonan())) {
                                        f = false;
                                        break;
                                    }
                                }
                        }
                        
                        if (f) {
                            hakmilikUrusanList.add(hu);
                        }
                    }
                }
            } else {
                for (HakmilikPermohonan hp : senaraiHakmilikTerlibat) {
                    if (hp == null) {
                        continue;
                    }
                    if (StringUtils.isNotBlank(idHakmilik)
                            && (!hp.getHakmilik().getIdHakmilik().equals(idHakmilik))) {
                        continue;
                    }
                    tmp = hakmilikUrusanService.searchHakmilikUrusanGadaian(permohonan.getKodUrusan().getKod(), hp.getHakmilik().getIdHakmilik(), ids);
                    for (HakmilikUrusan hu : tmp) {
                        hakmilikUrusanList.add(hu);
                    }
                    if (permohonan.getKodUrusan().getKod().equals("JMGD")) {
                        tmp = hakmilikUrusanService.findUrusanKaveat(hp.getHakmilik().getIdHakmilik());
                        for (HakmilikUrusan hu : tmp) {
                            hakmilikUrusanList.add(hu);
                        }
                    }

                    List<HakmilikUrusan> list = hakmilikUrusanService.findHakmilikUrusanTAktif(hp.getHakmilik().getIdHakmilik());
                    for (HakmilikUrusan hu : list) {
                        hakmilikUrusanListTaktif.add(hu);
                    }

                    Permohonan p = permohonanService.searchPermohonanTolakGantung(hp.getPermohonan().getIdPermohonan());
                    if (p != null) {
                        mohonTolakGantung.add(p);
                    }
                }
            }

            for (HakmilikUrusan hu : hakmilikUrusanList) {
                if (hu == null) {
                    continue;
                }
                LOG.debug("id urusan terlibat " + hu.getIdUrusan());
                Permohonan p = permohonanService.findById(hu.getIdPerserahan());
                if (p == null) {
                    continue;
                }
                if (senaraiList.contains(p)) {
                    continue;
                }
                if (!hu.getKodUrusan().getKod().equals("HKGHS")) {
                    senaraiList.add(p);
                }
            }

            // for berangkai only...
            if (!ids.isEmpty() && ids.size() > 1) {
                List<Permohonan> senaraiPermohonanTerlibat
                        = hakmilikUrusanService.getUrusanBelumDaftar(ids, permohonan.getKodUrusan().getKod());
                for (Permohonan p : senaraiPermohonanTerlibat) {
                    if (p.getKumpulanNo() > permohonan.getKumpulanNo()) {
                        continue;
                    }
                    if (senaraiList.contains(p)) {
                        continue;
                    }
                    senaraiList.add(p);
                }
            }
        }
//        hakmilikUrusanList = hakmilikUrusanService
//                .searchHakmilikUrusanGadaian(permohonan.getKodUrusan().getKod(),
//                permohonan.getSenaraiHakmilik().get(0).getHakmilik().getIdHakmilik());

        if ((permohonan != null) && (!permohonan.getKodUrusan().getKod().equals("HKGHS"))) {
            senaraiMau = permohonan.getSenaraiPermohonanAtasPerserahan();
            senaraiPermohonanHubungan = permohonanService.getSenaraiHubungan(permohonan.getIdPermohonan(), idHakmilik);
        }
    }

    public Permohonan getPermohonan() {
        return permohonan;
    }

    public void setPermohonan(Permohonan permohonan) {
        this.permohonan = permohonan;
    }

    public List<HakmilikUrusan> getHakmilikUrusanList() {
        return hakmilikUrusanList;
    }

    public void setHakmilikUrusanList(List<HakmilikUrusan> hakmilikUrusanList) {
        this.hakmilikUrusanList = hakmilikUrusanList;
    }

    public String[] getChkbox() {
        return chkbox;
    }

    public void setChkbox(String[] chkbox) {
        this.chkbox = chkbox;
    }

    public List<PermohonanAtasPerserahan> getSenaraiMau() {
        return senaraiMau;
    }

    public void setSenaraiMau(List<PermohonanAtasPerserahan> senaraiMau) {
        this.senaraiMau = senaraiMau;
    }

    public List getSenaraiList() {
        return senaraiList;
    }

    public void setSenaraiList(List senaraiList) {
        this.senaraiList = senaraiList;
    }

    public List<PermohonanHubungan> getSenaraiPermohonanHubungan() {
        return senaraiPermohonanHubungan;
    }

    public void setSenaraiPermohonanHubungan(List<PermohonanHubungan> senaraiPermohonanHubungan) {
        this.senaraiPermohonanHubungan = senaraiPermohonanHubungan;
    }

    public List<PermohonanHubungan> getSenaraiGadaianLepas() {
        return senaraiGadaianLepas;
    }

    public void setSenaraiGadaianLepas(List<PermohonanHubungan> senaraiGadaianLepas) {
        this.senaraiGadaianLepas = senaraiGadaianLepas;
    }

    public List<PermohonanHubungan> getSenaraiGadaianTangguh() {
        return senaraiGadaianTangguh;
    }

    public void setSenaraiGadaianTangguh(List<PermohonanHubungan> senaraiGadaianTangguh) {
        this.senaraiGadaianTangguh = senaraiGadaianTangguh;
    }

    public List<HakmilikPermohonan> getSenaraiHakmilikTerlibat() {
        return senaraiHakmilikTerlibat;
    }

    public void setSenaraiHakmilikTerlibat(List<HakmilikPermohonan> senaraiHakmilikTerlibat) {
        this.senaraiHakmilikTerlibat = senaraiHakmilikTerlibat;
    }

    public String getIdHakmilik() {
        return idHakmilik;
    }

    public void setIdHakmilik(String idHakmilik) {
        this.idHakmilik = idHakmilik;
    }

    public List<HakmilikUrusan> getHakmilikUrusanListTaktif() {
        return hakmilikUrusanListTaktif;
    }

    public void setHakmilikUrusanListTaktif(List<HakmilikUrusan> hakmilikUrusanListTaktif) {
        this.hakmilikUrusanListTaktif = hakmilikUrusanListTaktif;
    }

    public List<Permohonan> getMohonTolakGantung() {
        return mohonTolakGantung;
    }

    public void setMohonTolakGantung(List<Permohonan> mohonTolakGantung) {
        this.mohonTolakGantung = mohonTolakGantung;
    }
}
