/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.penguatkuasaan;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.dao.HakmilikDAO;
import etanah.dao.HakmilikPermohonanDAO;
import etanah.dao.KodLotDAO;
import etanah.dao.KodUOMDAO;
import etanah.dao.PermohonanDAO;
import etanah.model.Hakmilik;
import etanah.model.HakmilikPermohonan;
import etanah.model.InfoAudit;
import etanah.model.KodUOM;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.service.EnforceService;
import etanah.view.etanahActionBeanContext;
import java.math.BigDecimal;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;
import org.apache.log4j.Logger;
import etanah.service.BPelService;
import oracle.bpel.services.workflow.task.model.Task;
import org.apache.commons.lang.StringUtils;

/**
 *
 * @author User
 */
@UrlBinding("/penguatkuasaan/maklumat_keputusan")
public class MaklumatKeputusanActionBean extends AbleActionBean {

    private static final Logger LOG = Logger.getLogger(MaklumatKeputusanActionBean.class);
    private Hakmilik hakmilik;
    @Inject
    HakmilikDAO hakmilikDAO;
    @Inject
    HakmilikPermohonanDAO hakmilikPermohonanDAO;
    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    KodUOMDAO kodUOMDAO;
    @Inject
    EnforceService enforceService;
    @Inject
    KodLotDAO kodLotDAO;
    private String idHakmilik;
    private String cawangan;
    private String daerah;
    private String mukim;
    private String unitLot;
    private String unitLuas;
    private String noLot;
    private String kategoriTanah;
    private BigDecimal luasLama;
    private Permohonan permohonan;
    private Pengguna pguna;
    private String luasBaru;
    private HakmilikPermohonan hakmilikPermohonan;
    private List<HakmilikPermohonan> hakmilikPermohonanList;
    private int recordCount;
    private String stageId;
    private String idPermohonan;
    private KodUOM kodUL;
    ArrayList listIdPermohonan = new ArrayList<String>();
    String senaraiIdPermohonan[], idPertama, idKedua;
    private Boolean statusNorujukan = Boolean.FALSE;
    private Boolean maklumatTanahSek49 = Boolean.FALSE;

    @DefaultHandler
    public Resolution showForm() {
        return new JSP("penguatkuasaan/maklumat_keputusan.jsp").addParameter("tab", "true");
    }

    public Resolution showForm2() {
        return new JSP("penguatkuasaan/maklumat_keputusan_view.jsp").addParameter("tab", "true");
    }

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {
        pguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        String taskId = (String) getContext().getRequest().getSession().getAttribute("taskId");
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        stageId(taskId);
        LOG.info("::::: stage id :" + stageId);
        hakmilikPermohonanList = new ArrayList<HakmilikPermohonan>();

        if (idPermohonan != null) {
            permohonan = permohonanDAO.findById(idPermohonan);

            if (permohonan.getKodUrusan().getKod().equalsIgnoreCase("49")) {
                if (permohonan.getPermohonanSebelum() != null) {
                    //1) After create new IP
                    permohonan = permohonan.getPermohonanSebelum();
                }
                maklumatTanahSek49 = true;
            }

            Long id = null;

            hakmilikPermohonanList = enforceService.findListMohonHakmilik(permohonan.getIdPermohonan());

            if (maklumatTanahSek49 = true) {
                if (!hakmilikPermohonanList.isEmpty()) {

                    for (int j = 0; j < hakmilikPermohonanList.size(); j++) {
                        if (hakmilikPermohonanList.get(j).getNomborRujukan() != null) {
                            statusNorujukan = true;
                            System.out.println("::::::::::: value j :" + j);
                            HakmilikPermohonan hp = hakmilikPermohonanList.get(j);
                            listIdPermohonan.add(hp.getNomborRujukan());

                            ArrayList<String> data = listIdPermohonan;


                            for (String a : data) {
                                senaraiIdPermohonan = a.split(",");
                                LOG.info("length senaraiIdPermohonan : " + senaraiIdPermohonan.length);
                                if (senaraiIdPermohonan.length > 1) {
                                    idPertama = senaraiIdPermohonan[0];
                                    idKedua = senaraiIdPermohonan[1];

                                }
                            }
                            LOG.info("::: idPertama : " + idPertama);
                            LOG.info("::: idKedua : " + idKedua);

                            String idMohon = "";

                            if (StringUtils.isNotEmpty(idPertama) && StringUtils.isNotEmpty(idKedua)) {
                                if (idPertama.equalsIgnoreCase(idPermohonan)) {
                                    idMohon = idPertama;
                                    id = hakmilikPermohonanList.get(j).getId();
                                    System.out.println("id MH (1): " + id);
                                } else if (idKedua.equalsIgnoreCase(idPermohonan)) {
                                    idMohon = idKedua;
                                    id = hakmilikPermohonanList.get(j).getId();
                                    System.out.println("id MH (2): " + id);
                                }
                            }

                            listIdPermohonan.clear();
                            idPertama = "";
                            idKedua = "";
                        }
                    }

                    System.out.println("::: id : " + id);
                    System.out.println("::: statusNorujukan : " + statusNorujukan);

                    if (statusNorujukan == true) {
                        if (id != null) {
                            LOG.info("::: using id MH");
                            hakmilikPermohonanList = enforceService.findListMohonHakmilikById(id);
                        } else {
                            LOG.info("::: using id idPermohonan");
                            hakmilikPermohonanList = enforceService.findListMohonHakmilik(permohonan.getIdPermohonan(), idPermohonan);
                        }
                    }

                }
            }

            for (HakmilikPermohonan hp : hakmilikPermohonanList) {
                hakmilikPermohonan = hp;
                hakmilik = hp.getHakmilik();
//                    luasBaru = hakmilikPermohonan.getLuasTerlibat().toString();
            }
            recordCount = hakmilikPermohonanList.size();
            System.out.println("record count : " + recordCount);
        }

    }

    public Resolution searchHakmilik() {
        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        idHakmilik = getContext().getRequest().getParameter("idHakmilik");
        System.out.println("id hak milik : " + idHakmilik);

        Hakmilik hkmilik = hakmilikDAO.findById(idHakmilik);

        if (hkmilik != null) {
            cawangan = hkmilik.getCawangan().getName();
            daerah = hkmilik.getDaerah().getNama();
            mukim = hkmilik.getBandarPekanMukim().getNama();
            unitLot = hkmilik.getKodUnitLuas().getNama();
            noLot = hkmilik.getNoLot();
            kategoriTanah = hkmilik.getKategoriTanah().getNama();
            luasLama = hkmilik.getLuas();

        } else {
            addSimpleError("Sila Masukan ID Hak Milik Yang Betul.");
        }
        return new ForwardResolution("/WEB-INF/jsp/penguatkuasaan/maklumat_keputusan.jsp").addParameter("tab", "true");

    }

    public Resolution simpan() throws ParseException {

        LOG.info("simpan hak milik");

        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        idHakmilik = getContext().getRequest().getParameter("hakmilik.idHakmilik");
        hakmilik = hakmilikDAO.findById(idHakmilik);
        pguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        luasBaru = getContext().getRequest().getParameter("hakmilikPermohonan.luasTerlibat");
        BigDecimal luas = new BigDecimal(luasBaru);

        if (hakmilik == null) {
            addSimpleError("Maklumat hakmilik tidak dijumpai");
        } else {
            LOG.info("id_mohon : " + idPermohonan + " id_hakmilik : " + idHakmilik);
            InfoAudit ia = new InfoAudit();
            //find hakmilikPermohonan recently inserted to db (based on id_mohon & id_hakmilik) -- for updated luas baru

            if (maklumatTanahSek49 = false) {
                hakmilikPermohonan = enforceService.findLatestRecord(idHakmilik, idPermohonan);
                permohonan = permohonanDAO.findById(idPermohonan);
            }

            if (hakmilikPermohonan == null) {
                LOG.info("record is not availabe - insert new luas");
                hakmilikPermohonan = new HakmilikPermohonan();
                ia.setDimasukOleh(pguna);
                ia.setTarikhMasuk(new java.util.Date());

            } else {
                LOG.info("record is already exist - want to update luas");
                ia = hakmilikPermohonan.getInfoAudit();
                ia.setDiKemaskiniOleh(pguna);
                ia.setTarikhKemaskini(new java.util.Date());
            }

            InfoAudit info = pguna.getInfoAudit();
            info.setDimasukOleh(pguna);
            info.setTarikhMasuk(new java.util.Date());

            hakmilikPermohonan.setHakmilik(hakmilik);
            hakmilikPermohonan.setPermohonan(permohonan);
            hakmilikPermohonan.setInfoAudit(info);
            hakmilikPermohonan.setLuasTerlibat(luas);
            kodUL = new KodUOM();
            String kod4 = getContext().getRequest().getParameter("kodUnitLuas.kod");
            kodUL.setKod(kod4);
            hakmilikPermohonan.setKodUnitLuas(kodUL);
            enforceService.simpanhakmilikPermohonan(hakmilikPermohonan);

            addSimpleMessage("Maklumat Telah Berjaya Disimpan.");

        }

        return new JSP("penguatkuasaan/maklumat_keputusan.jsp").addParameter("tab", "true");

    }

    public Resolution updateLuas() throws ParseException {

        LOG.info("::: updateLuas");


        String idMH = getContext().getRequest().getParameter("hakmilikPermohonan.id");
        String noLotBaru = getContext().getRequest().getParameter("hakmilikPermohonan.noLot");
        String kodLotBaru = getContext().getRequest().getParameter("lot.kod");

        if (StringUtils.isNotBlank(idMH)) {
            hakmilikPermohonan = hakmilikPermohonanDAO.findById(Long.parseLong(idMH));

            if (hakmilikPermohonan != null) {
                InfoAudit ia = hakmilikPermohonan.getInfoAudit();
                ia.setDiKemaskiniOleh(pguna);
                ia.setTarikhKemaskini(new java.util.Date());
                hakmilikPermohonan.setInfoAudit(ia);
                hakmilikPermohonan.setNoLot(noLotBaru);
                if (StringUtils.isNotBlank(kodLotBaru)) {
                    hakmilikPermohonan.setLot(kodLotDAO.findById(kodLotBaru));
                }
                enforceService.simpanhakmilikPermohonan(hakmilikPermohonan);
            }
        }

        addSimpleMessage("Maklumat Telah Berjaya Disimpan.");
        return new JSP("penguatkuasaan/maklumat_keputusan_view.jsp").addParameter("tab", "true");

    }

    public String stageId(String taskId) {
        BPelService service = new BPelService();
        stageId = null;
        if (StringUtils.isNotBlank(taskId)) {
            Task t = null;
            t = service.getTaskFromBPel(taskId, pguna);
            stageId = t.getSystemAttributes().getStage();
            System.out.println("stageId" + stageId);
        } else {
            stageId = "g_hantar_pu";
        }

        return stageId;
    }

    public String getIdPermohonan() {
        return idPermohonan;
    }

    public void setIdPermohonan(String idPermohonan) {
        this.idPermohonan = idPermohonan;
    }

    public String getStageId() {
        return stageId;
    }

    public void setStageId(String stageId) {
        this.stageId = stageId;
    }

    public int getRecordCount() {
        return recordCount;
    }

    public void setRecordCount(int recordCount) {
        this.recordCount = recordCount;
    }

    public List<HakmilikPermohonan> getHakmilikPermohonanList() {
        return hakmilikPermohonanList;
    }

    public void setHakmilikPermohonanList(List<HakmilikPermohonan> hakmilikPermohonanList) {
        this.hakmilikPermohonanList = hakmilikPermohonanList;
    }

    public HakmilikPermohonan getHakmilikPermohonan() {
        return hakmilikPermohonan;
    }

    public void setHakmilikPermohonan(HakmilikPermohonan hakmilikPermohonan) {
        this.hakmilikPermohonan = hakmilikPermohonan;
    }

    public String getLuasBaru() {
        return luasBaru;
    }

    public void setLuasBaru(String luasBaru) {
        this.luasBaru = luasBaru;
    }

    public Pengguna getPguna() {
        return pguna;
    }

    public void setPguna(Pengguna pguna) {
        this.pguna = pguna;
    }

    public Permohonan getPermohonan() {
        return permohonan;
    }

    public void setPermohonan(Permohonan permohonan) {
        this.permohonan = permohonan;
    }

    public String getCawangan() {
        return cawangan;
    }

    public void setCawangan(String cawangan) {
        this.cawangan = cawangan;
    }

    public String getDaerah() {
        return daerah;
    }

    public void setDaerah(String daerah) {
        this.daerah = daerah;
    }

    public String getKategoriTanah() {
        return kategoriTanah;
    }

    public void setKategoriTanah(String kategoriTanah) {
        this.kategoriTanah = kategoriTanah;
    }

    public BigDecimal getLuasLama() {
        return luasLama;
    }

    public void setLuasLama(BigDecimal luasLama) {
        this.luasLama = luasLama;
    }

    public String getMukim() {
        return mukim;
    }

    public void setMukim(String mukim) {
        this.mukim = mukim;
    }

    public String getNoLot() {
        return noLot;
    }

    public void setNoLot(String noLot) {
        this.noLot = noLot;
    }

    public String getUnitLot() {
        return unitLot;
    }

    public void setUnitLot(String unitLot) {
        this.unitLot = unitLot;
    }

    public String getUnitLuas() {
        return unitLuas;
    }

    public void setUnitLuas(String unitLuas) {
        this.unitLuas = unitLuas;
    }

    public String getIdHakmilik() {
        return idHakmilik;
    }

    public void setIdHakmilik(String idHakmilik) {
        this.idHakmilik = idHakmilik;
    }

    public Hakmilik getHakmilik() {
        return hakmilik;
    }

    public void setHakmilik(Hakmilik hakmilik) {
        this.hakmilik = hakmilik;
    }

    public KodUOM getKodUL() {
        return kodUL;
    }

    public void setKodUL(KodUOM kodUL) {
        this.kodUL = kodUL;
    }
}
