/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.stripes.pelupusan;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.dao.FasaPermohonanDAO;
import etanah.dao.FolderDokumenDAO;
import etanah.dao.PermohonanDAO;
import etanah.model.InfoAudit;
import etanah.model.KodAgensi;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.service.ConsentPtdService;
import etanah.service.RegService;
import etanah.view.etanahActionBeanContext;
import java.util.ArrayList;
import java.util.List;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;
import etanah.dao.KodAgensiDAO;
import etanah.dao.KodCawanganDAO;
import etanah.dao.KodDokumenDAO;
import etanah.dao.KodKategoriAgensiDAO;
import etanah.dao.KodKlasifikasiDAO;
import etanah.dao.KodNegeriDAO;
import etanah.dao.KodRujukanDAO;
import etanah.dao.PermohonanLaporanPelanDAO;
import etanah.dao.PermohonanRujukanLuarDAO;
import etanah.dao.PermohonanRujukanLuarSalinanDAO;
import etanah.dao.PermohonanTandatanganDokumenDAO;
import etanah.model.*;
import etanah.service.BPelService;
import etanah.service.NotaService;
import etanah.service.PelupusanService;
import etanah.service.common.EnforcementService;
import java.text.SimpleDateFormat;
import java.util.StringTokenizer;
import java.text.ParseException;
import java.util.Date;
import etanah.report.ReportUtil;
import etanah.service.SemakDokumenService;
import etanah.service.common.DokumenService;
import etanah.service.common.HakmilikPermohonanService;
import etanah.service.common.KandunganFolderService;
import etanah.view.ListUtil;
import etanah.view.stripes.pelupusan.disClass.DisLaporanTanahService;
import java.util.Calendar;
import net.sourceforge.stripes.action.RedirectResolution;
import org.apache.log4j.Logger;
import org.apache.commons.lang.StringUtils;
import oracle.bpel.services.workflow.task.model.Task;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author HLS Creative
 */
@UrlBinding("/pelupusan/lesen_terdahulu_mpjlt")
public class LesenTerdahuluMPJLTActionBean extends AbleActionBean {

    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    KodDokumenDAO kodDokumenDAO;
    @Inject
    etanah.Configuration conf;
    @Inject
    KodCawanganDAO kodCawanganDAO;
    @Inject
    PelupusanService pelupusanService;
    @Inject
    HakmilikPermohonanService hakmilikPermohonanService;
    @Inject
    DisLaporanTanahService disLaporanTanahService;
    private List<Permit> listLesen;
    private String idPermohonan;
    private String noLesen;
    private String idMohonDulu;
    private boolean viewOnly = false;
    private Pengguna pguna = new Pengguna();
    private Permohonan permohonan = new Permohonan();
    private PermitSahLaku permitSahLakuAsal;
    private HakmilikPermohonan hakmilikPermohonan;
    private Pihak pihak;
    private List<PermitSahLaku> permitSahLakuList;
    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;
    private static final Logger LOG = Logger.getLogger(LesenTerdahuluMPJLTActionBean.class);
    private boolean cari=true;
    private boolean simpan=true;

    @DefaultHandler
    public Resolution showForm() {
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        pguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        permohonan = permohonanDAO.findById(idPermohonan);
        if (permohonan != null) {
            if (permohonan.getIdPermohonan() != null) {
                listLesen = new ArrayList<Permit>();
                if (permohonan.getPermohonanSebelum() != null) {
                    Permit permit = pelupusanService.findPermitByIdPermohonan(permohonan.getPermohonanSebelum().getIdPermohonan());
                    //Permohonan permohonanRef = pelupusanService.findIdPermohonan(idPermohonan);
                    if (permit != null) {
                        //if(permohonanRef != null){
                        //permit.setPermohonan(permohonanRef);
                        //}
                        listLesen.add(permit);
                        idMohonDulu = permohonan.getPermohonanSebelum().getIdPermohonan();
                        noLesen = permit.getNoPermit();

                    }
                }
            }
        }
        viewOnly = Boolean.FALSE;
        return new JSP("pelupusan/lesen_terdahulu_MPJLT.jsp").addParameter("tab", "true");
    }

    public Resolution viewForm() {
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        pguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        permohonan = permohonanDAO.findById(idPermohonan);
        if (permohonan != null) {
            if (permohonan.getIdPermohonan() != null) {
                listLesen = new ArrayList<Permit>();
                if (permohonan.getPermohonanSebelum() != null) {
                    Permit permit = pelupusanService.findPermitByIdPermohonan(permohonan.getPermohonanSebelum().getIdPermohonan());
                    // Permohonan permohonanRef = pelupusanService.findIdPermohonan(idPermohonan);
                    if (permit != null) {
                        // if(permohonanRef != null){
                        //permit.setPermohonan(permohonanRef);
                        //}
                        listLesen.add(permit);
                        idMohonDulu = permohonan.getPermohonanSebelum().getIdPermohonan();
                        noLesen = permit.getNoPermit();
//                         permitSahLakuAsal = pelupusanService.findPermitSahLakuByIdMohon(permit.getPermohonan().getIdPermohonan()) ;
                    }
                } else {
                    listLesen = new ArrayList<Permit>();
                }
            }
        }
        viewOnly = Boolean.TRUE;
        return new JSP("pelupusan/lesen_terdahulu_MPJLT.jsp").addParameter("tab", "true");
    }

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {
        LOG.info("rehydrate start");
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        pguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        permohonan = permohonanDAO.findById(idPermohonan);
        if (permohonan != null) {
            if (permohonan.getIdPermohonan() != null) {
                listLesen = new ArrayList<Permit>();
                if (permohonan.getPermohonanSebelum() != null) {
                    cari=false;
                    simpan=false;
                    Permit permit = pelupusanService.findPermitByIdPermohonan(permohonan.getPermohonanSebelum().getIdPermohonan());
                    //Permohonan permohonanRef = pelupusanService.findIdPermohonan(idPermohonan);
                    if (permit != null) {
                        //if(permohonanRef != null){
                        //permit.setPermohonan(permohonanRef);
                        //}
                        listLesen.add(permit);
                        idMohonDulu = permohonan.getPermohonanSebelum().getIdPermohonan();
                        noLesen = permit.getNoPermit();
 
                        if (permohonan.getKodUrusan().getKod().equals("MPJLT")) {
                            permitSahLakuList = pelupusanService.findPermitSahLakuByIdPermit(permit.getIdPermit());
                            hakmilikPermohonan = pelupusanService.findByIdPermohonan(permit.getPermohonan().getIdPermohonan());
                            PermohonanLaporanPelan permohonanLaporanPelan = pelupusanService.findByNoLitho(permit.getPermohonan().getIdPermohonan());
                            hakmilikPermohonan.setKeteranganKadarUkur(permohonanLaporanPelan.getNoLitho());
                            pihak = permit.getPihak();
                        } //End
                    }
                  
                  
                    if (permohonan.getKodUrusan().getKod().equals("MPJLT")) {
                        if (permitSahLakuList != null && !permitSahLakuList.isEmpty()) {
                            for (int i = 0; i < permitSahLakuList.size(); i++) {
                                permitSahLakuAsal = permitSahLakuList.get(0);
                            }
                        }
                    } //End
                }
            }
        }
    }

    public Resolution cariLesen() {
        if (!StringUtils.isBlank(noLesen)) {
            if (!noLesen.equals("1")) {
                listLesen = pelupusanService.findPermitByNoLesen(noLesen);
            }
            if (!listLesen.isEmpty()) {
                for (Permit permit : listLesen) {
                    idMohonDulu = permit.getPermohonan().getIdPermohonan();
                    if (permohonan.getKodUrusan().getKod().equals("PJLB")) {
                        permitSahLakuList = pelupusanService.findPermitSahLakuByIdPermit(permit.getIdPermit());
                        hakmilikPermohonan = pelupusanService.findByIdPermohonan(permit.getPermohonan().getIdPermohonan());
                        PermohonanLaporanPelan permohonanLaporanPelan = pelupusanService.findByNoLitho(permit.getPermohonan().getIdPermohonan());
                        hakmilikPermohonan.setKeteranganKadarUkur(permohonanLaporanPelan.getNoLitho());
                        pihak = permit.getPihak();
                    }
                    if (permohonan.getKodUrusan().getKod().equals("LMCRG")) {
                        permitSahLakuList = pelupusanService.findPermitSahLakuByIdPermit(permit.getIdPermit());
                        hakmilikPermohonan = pelupusanService.findByIdPermohonan(permit.getPermohonan().getIdPermohonan());
                        PermohonanLaporanPelan permohonanLaporanPelan = pelupusanService.findByNoLitho(permit.getPermohonan().getIdPermohonan());
                        hakmilikPermohonan.setKeteranganKadarUkur(permohonanLaporanPelan.getNoLitho());
                        pihak = permit.getPihak();
                    }
                  
                    if (permohonan.getKodUrusan().getKod().equals("MPJLT")) {
                        permitSahLakuList = pelupusanService.findPermitSahLakuByIdPermit(permit.getIdPermit());
                        hakmilikPermohonan = pelupusanService.findByIdPermohonan(permit.getPermohonan().getIdPermohonan());
                        PermohonanLaporanPelan permohonanLaporanPelan = pelupusanService.findByNoLitho(permit.getPermohonan().getIdPermohonan());
                        hakmilikPermohonan.setKeteranganKadarUkur(permohonanLaporanPelan.getNoLitho());
                        pihak = permit.getPihak();
                    } //End
                    break;
                }
                if (permohonan.getKodUrusan().getKod().equals("PJLB")) {
                    if (permitSahLakuList != null && !permitSahLakuList.isEmpty()) {
                        for (int i = 0; i < permitSahLakuList.size(); i++) {
                            permitSahLakuAsal = permitSahLakuList.get(0);
                        }
                    }
                }
                if (permohonan.getKodUrusan().getKod().equals("LMCRG")) {
                    if (permitSahLakuList != null && !permitSahLakuList.isEmpty()) {
                        for (int i = 0; i < permitSahLakuList.size(); i++) {
                            permitSahLakuAsal = permitSahLakuList.get(0);
                        }
                    }
                }
                //Add by Aizuddin Orogenic Group
                if (permohonan.getKodUrusan().getKod().equals("MPJLT")) {
                    if (permitSahLakuList != null && !permitSahLakuList.isEmpty()) {
                        for (int i = 0; i < permitSahLakuList.size(); i++) {
                            permitSahLakuAsal = permitSahLakuList.get(0);
                        }
                    }
                } //End
                addSimpleMessage("Maklumat Dijumpai");
            } else {
                addSimpleMessage("Tiada Lesen Terdahulu");
            }
        } else {
            addSimpleError("Sila Nyatakan No Lesen");
        }

        return new JSP("pelupusan/lesen_terdahulu_MPJLT.jsp").addParameter("tab", "true");
    }

    public Resolution simpan() {
        LOG.info("simpan start");
        if (!StringUtils.isBlank(idMohonDulu)) {
            idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
            pguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
            permohonan = permohonanDAO.findById(idPermohonan);
            String sbb = getContext().getRequest().getParameter("permohonan.sebab");
            Permohonan permohonanSebelum = !StringUtils.isBlank(idMohonDulu) ? permohonanDAO.findById(idMohonDulu) : new Permohonan();
            LOG.info("permohonanSebelum.getIdPermohonan() :" + permohonanSebelum.getIdPermohonan());
            if (permohonan != null) {
                if (permohonanSebelum.getIdPermohonan() != null) {
                    InfoAudit ia = new InfoAudit();
                    ia = permohonan.getInfoAudit();
                    ia.setDiKemaskiniOleh(pguna);
                    ia.setTarikhKemaskini(new java.util.Date());
                    permohonan.setInfoAudit(ia);
                    permohonan.setPermohonanSebelum(permohonanSebelum);
                    if (permohonan.getKodUrusan().getKod().equals("PJLB")) {
                        permohonan.setSebab(sbb);
                    }
                    //Add by Aizuddin Orogenic Group
                    if (permohonan.getKodUrusan().getKod().equals("MPJLB") || permohonan.getKodUrusan().getKod().equals("MPJLT")) {
                        permohonan.setSebab(sbb);
                        permohonan.setCatatan(permohonanSebelum.getCatatan());
                    } //End
                    pelupusanService.simpanPermohonan(permohonan);


                    //copy pemohon
                    Pemohon pemohon = permohonanSebelum.getSenaraiPemohon().get(0);
                    Long idpihaklong = pemohon.getPihak().getIdPihak();
                    String idPihakString = idpihaklong.toString();
                    LOG.info("idPihakString : " + idPihakString);
                    ia.setDimasukOleh(pguna);
                    ia.setTarikhMasuk(new java.util.Date());
                    Pihak pihakTemp = pelupusanService.findByIdPihak(idPihakString);
                    Pihak pihakNew = new Pihak();

                    pemohon = new Pemohon();
                    pemohon.setPermohonan(permohonan);
                    pemohon.setPihak(pihakTemp);
                    pemohon.setInfoAudit(ia);
                    pemohon.setCawangan(permohonan.getCawangan());
                    if (pemohon != null) {
                        pihakNew.setNama(pihakTemp.getNama());
                        pihakNew.setAlamat1(pihakTemp.getAlamat1());
                        pihakNew.setAlamat2(pihakTemp.getAlamat2());
                        pihakNew.setAlamat3(pihakTemp.getAlamat3());
                        pihakNew.setAlamat4(pihakTemp.getAlamat4());
                        pihakNew.setNegeri(pihakTemp.getNegeri());
                        pihakNew.setNoPengenalan(pihakTemp.getNoPengenalan());
                        pihakNew.setEmail(pihakTemp.getEmail());
                        pihakNew.setInfoAudit(ia);
                    }
                    pelupusanService.simpanPihakPemohon(pihakTemp, pemohon);
                    addSimpleMessage("Maklumat Berjaya Disimpan");
                }
            }

            //copy mohon hakmilik 
            LOG.info("permohonanSebelum.getIdPermohonan() :" + permohonanSebelum.getIdPermohonan());
            List<HakmilikPermohonan> hakmilikPermohonanTerdahulu = pelupusanService.getHakmilikPermohonan(permohonanSebelum.getIdPermohonan());
            for (HakmilikPermohonan hp : hakmilikPermohonanTerdahulu) {
                LOG.info("id hakmilik : " + hp.getPermohonan().getIdPermohonan());
                Hakmilik hm = hp.getHakmilik();
                Hakmilik hms = new Hakmilik();
                HakmilikPermohonan hakmilikSekarang = new HakmilikPermohonan();
                hakmilikSekarang = disLaporanTanahService.getLaporanTanahService().findByIdHakmilikIdPermohonan(idPermohonan, hms.getIdHakmilik());
                if (hakmilikSekarang == null) {
                    LOG.info("hakmilik copy ");
                    hakmilikSekarang = new HakmilikPermohonan();
                    hakmilikSekarang.setInfoAudit(disLaporanTanahService.findAudit(hakmilikSekarang, "new", pguna));
                    hakmilikSekarang.setPermohonan(permohonan);
                    hakmilikSekarang.setHakmilik(hm);
                    hakmilikSekarang.setLuasTerlibat(hp.getLuasTerlibat());
                    hakmilikSekarang.setLuasDiluluskan(hp.getLuasDiluluskan());
                    hakmilikSekarang.setLuasUkurHalus(hp.getLuasUkurHalus());
                    hakmilikSekarang.setLuasPelanB1(hp.getLuasPelanB1());
                    hakmilikSekarang.setKodUnitLuas(hp.getKodUnitLuas());
                    hakmilikSekarang.setLuasUkurHalusUom(hp.getLuasUkurHalusUom());
                    hakmilikSekarang.setLuasLulusUom(hp.getLuasLulusUom());
                    hakmilikSekarang.setLuasPelanB1Uom(hp.getLuasPelanB1Uom());
                    hakmilikSekarang.setKodSeksyen(hp.getKodSeksyen());
                    hakmilikSekarang.setBandarPekanMukimBaru(hp.getBandarPekanMukimBaru());
                    hakmilikSekarang.setKodHakmilik(hp.getKodHakmilik());
                    hakmilikSekarang.setLot(hp.getLot());
                    hakmilikSekarang.setNoLot(hp.getNoLot());
                    hakmilikSekarang.setNoPajakan(hp.getNoPajakan());
                    hakmilikSekarang.setKategoriTanahBaru(hp.getKategoriTanahBaru());
                    hakmilikSekarang.setSyaratNyataBaru(hp.getSyaratNyataBaru());
                    hakmilikSekarang.setSekatanKepentinganBaru(hp.getSekatanKepentinganBaru());
                    hakmilikSekarang.setKadarCukaiBaru(hp.getKadarCukaiBaru());
                    hakmilikSekarang.setCukaiBaru(hp.getCukaiBaru());
                    hakmilikSekarang.setLokasi(hp.getLokasi());
                    hakmilikSekarang.setJarak(hp.getJarak());
                    hakmilikSekarang.setUnitJarak(hp.getUnitJarak());
                    hakmilikSekarang.setJarakDari(hp.getJarakDari());
                    hakmilikSekarang.setJarakDariNama(hp.getJarakDariNama());
                    hakmilikSekarang.setNomborRujukan(hp.getNomborRujukan());
                    hakmilikSekarang.setKodMilik(hp.getKodMilik());
                    hakmilikSekarang.setTarikhAwalDaftarGeran(hp.getTarikhAwalDaftarGeran());
                    hakmilikSekarang.setTempohPajakan(hp.getTempohPajakan());
                    hakmilikSekarang.setKodHakmilikTetap(hp.getKodHakmilikTetap());
                    hakmilikSekarang.setKodHakmilikSementara(hp.getKodHakmilikSementara());
                    hakmilikSekarang.setTempohHakmilik(hp.getTempohHakmilik());
                    hakmilikSekarang.setCukaiPerMeterPersegi(hp.getCukaiPerMeterPersegi());
                    hakmilikSekarang.setCukaiPerLot(hp.getCukaiPerLot());
                    hakmilikSekarang.setKadarPremium(hp.getKadarPremium());
                    hakmilikSekarang.setDendaPremium(hp.getDendaPremium());
                    hakmilikSekarang.setJenisPenggunaanTanah(hp.getJenisPenggunaanTanah());
                    hakmilikSekarang.setSyaratNyata(hp.getSyaratNyata());
                    hakmilikSekarang.setSekatanKepentingan(hp.getSekatanKepentingan());
                    hakmilikSekarang.setNilaianJpph(hp.getNilaianJpph());
                    hakmilikSekarang.setCatatan(hp.getCatatan());
                    hakmilikSekarang.setHubunganHakmilik(hp.getHubunganHakmilik());
                    hakmilikSekarang.setDokumen1(hp.getDokumen1());
                    hakmilikSekarang.setDokumen2(hp.getDokumen2());
                    hakmilikSekarang.setDokumen3(hp.getDokumen3());
                    hakmilikSekarang.setDokumen4(hp.getDokumen4());
                    hakmilikSekarang.setDokumen5(hp.getDokumen5());
                    hakmilikSekarang.setDokumen6(hp.getDokumen6());
                    hakmilikSekarang.setKosInfra(hp.getKosInfra());
                    hakmilikSekarang.setTanahDiambil(hp.getTanahDiambil());
                    hakmilikSekarang.setKeteranganInfra(hp.getKeteranganInfra());
                    hakmilikSekarang.setKeteranganCukaiBaru(hp.getKeteranganCukaiBaru());
                    hakmilikSekarang.setKeteranganKadarPremium(hp.getKeteranganKadarPremium());
                    hakmilikSekarang.setKodKegunaanTanah(hp.getKodKegunaanTanah());
                    hakmilikSekarang.setKeteranganKadarUkur(hp.getKeteranganKadarUkur());
                    hakmilikSekarang.setKeteranganKadarDaftar(hp.getKeteranganKadarDaftar());
                    hakmilikSekarang.setJarakDariKediaman(hp.getJarakDariKediaman());
                    hakmilikSekarang.setJarakDariKediamanUom(hp.getJarakDariKediamanUom());
                    hakmilikSekarang.setAgensiUpahUkur(hp.getAgensiUpahUkur());
                    hakmilikSekarang.setStatusLuasDiluluskan(hp.getStatusLuasDiluluskan());
                    hakmilikSekarang.setPenjenisan(hp.getPenjenisan());
                    hakmilikSekarang.setNoUnitPetak(hp.getNoUnitPetak());
                    hakmilikSekarang.setPegangan(hp.getPegangan());
                    hakmilikSekarang.setKodDUN(hp.getKodDUN());
                    hakmilikSekarang.setKodKawasanParlimen(hp.getKodKawasanParlimen());
                    hakmilikSekarang.setTempohPengosongan(hp.getTempohPengosongan());
                    hakmilikPermohonanService.saveSingleHakmilikPermohonan(hakmilikSekarang);
                }
            }
        } else {
            addSimpleError("ID Permohonan Terdahulu Tidak Dijumpai");
        }
        rehydrate();
        return new JSP("pelupusan/lesen_terdahulu_MPJLT.jsp").addParameter("tab", "true");
    }

    public String getIdPermohonan() {
        return idPermohonan;
    }

    public void setIdPermohonan(String idPermohonan) {
        this.idPermohonan = idPermohonan;
    }

    public List<Permit> getListLesen() {
        return listLesen;
    }

    public void setListLesen(List<Permit> listLesen) {
        this.listLesen = listLesen;
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

    public String getNoLesen() {
        return noLesen;
    }

    public void setNoLesen(String noLesen) {
        this.noLesen = noLesen;
    }

    public String getIdMohonDulu() {
        return idMohonDulu;
    }

    public void setIdMohonDulu(String idMohonDulu) {
        this.idMohonDulu = idMohonDulu;
    }

    public boolean isViewOnly() {
        return viewOnly;
    }

    public void setViewOnly(boolean viewOnly) {
        this.viewOnly = viewOnly;
    }

    public PermitSahLaku getPermitSahLakuAsal() {
        return permitSahLakuAsal;
    }

    public void setPermitSahLakuAsal(PermitSahLaku permitSahLakuAsal) {
        this.permitSahLakuAsal = permitSahLakuAsal;
    }

    public List<PermitSahLaku> getPermitSahLakuList() {
        return permitSahLakuList;
    }

    public void setPermitSahLakuList(List<PermitSahLaku> permitSahLakuList) {
        this.permitSahLakuList = permitSahLakuList;
    }

    public HakmilikPermohonan getHakmilikPermohonan() {
        return hakmilikPermohonan;
    }

    public void setHakmilikPermohonan(HakmilikPermohonan hakmilikPermohonan) {
        this.hakmilikPermohonan = hakmilikPermohonan;
    }

    public Pihak getPihak() {
        return pihak;
    }

    public void setPihak(Pihak pihak) {
        this.pihak = pihak;
    }

    public boolean isCari() {
        return cari;
    }

    public void setCari(boolean cari) {
        this.cari = cari;
    }

    public boolean isSimpan() {
        return simpan;
    }

    public void setSimpan(boolean simpan) {
        this.simpan = simpan;
    }
    
    
}