               /*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.stripes.pelupusan;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.dao.HakmilikDAO;
import etanah.dao.HakmilikPermohonanDAO;
import etanah.dao.KodDUNDAO;
import etanah.dao.KodDokumenDAO;
import etanah.dao.KodKawasanParlimenDAO;
import etanah.dao.KodPemilikanDAO;
import etanah.dao.KodRizabDAO;
import etanah.dao.KodSeksyenDAO;
import etanah.dao.KodTanahDAO;
import etanah.dao.PermohonanDAO;
import etanah.dao.PermohonanLaporanKawasanDAO;
import etanah.dao.PermohonanLaporanPelanDAO;
import etanah.dao.PermohonanManualDAO;
import etanah.dao.TanahRizabPermohonanDAO;
import etanah.model.Hakmilik;
import etanah.model.HakmilikPermohonan;
import etanah.model.InfoAudit;
import etanah.model.KodBandarPekanMukim;
import etanah.model.KodCawangan;
import etanah.model.KodDUN;
import etanah.model.KodDaerah;
import etanah.model.KodDokumen;
import etanah.model.KodHakmilik;
import etanah.model.KodKawasanParlimen;
import etanah.model.KodPemilikan;
import etanah.model.KodRizab;
import etanah.model.KodSeksyen;
import etanah.model.KodTanah;
import etanah.model.KodUOM;
import etanah.model.Pemohon;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.model.PermohonanLaporanKawasan;
import etanah.model.PermohonanLaporanPelan;
import etanah.model.PermohonanLaporanUlasan;
import etanah.model.PermohonanManual;
import etanah.model.TanahRizabPermohonan;
import etanah.service.LaporanPelukisPelanService;
import etanah.service.PelupusanService;
import etanah.service.common.TanahRizabService;
import etanah.view.etanahActionBeanContext;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.RedirectResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;
import org.apache.commons.lang.StringUtils;
import etanah.service.BPelService;
import oracle.bpel.services.workflow.task.model.Task;
import org.apache.log4j.Logger;

/**
 *
 * @author sitinorajar
 */
@UrlBinding("/pelupusan/laporan_pelukis_pelanGSAA")
public class LaporanPelukisPelanGSAActionBean extends AbleActionBean {

    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    PermohonanLaporanKawasanDAO permohonanLaporanKawasanDAO;
    @Inject
    TanahRizabPermohonanDAO tanahrizabPermohonanDAO;
    private TanahRizabPermohonan tanahrizabpermohonan;
    @Inject
    LaporanPelukisPelanService laporanPelukisPelanService;
    @Inject
    PelupusanService pelupusanService;
    @Inject
    KodRizabDAO kodRizabDAO;
    @Inject
    HakmilikDAO hakmilikDAO;
    @Inject
    KodTanahDAO kodTanahDAO;
    @Inject
    KodPemilikanDAO kodPemilikanDAO;
    @Inject
    HakmilikPermohonanDAO hakmilikPermohonanDAO;
    @Inject
    PermohonanLaporanPelanDAO permohonanLaporanPelanDAO;
    @Inject
    TanahRizabService tanahRizabService;
    @Inject
    PelupusanService plpservice;
    @Inject
    KodSeksyenDAO kodSeksyenDAO;
    @Inject
    PermohonanManualDAO permohonanManualDAO;
    @Inject
    KodDokumenDAO kodDokumenDAO;
    @Inject
    KodDUNDAO kodDUNDAO;
    @Inject
    KodKawasanParlimenDAO kodKawasanParlimenDAO;
    @Inject
    etanah.Configuration conf;
    private Permohonan permohonan;
    private PermohonanManual permohonanManual;
    private Hakmilik hakmilik;
    private HakmilikPermohonan hakmilikPermohonan;
    private PermohonanLaporanPelan permohonanLaporanPelan;
    private PermohonanLaporanKawasan permohonanLaporanKWS;
    private PermohonanLaporanUlasan permohonanLaporanUlasan;
    private KodRizab rizab;
    private KodHakmilik kodHakmilik;
    private KodBandarPekanMukim bandarPekanMukim;
    private KodDaerah daerah;
    private KodCawangan cawangan;
    private boolean jenisRizab;
    private String noLot;
    private String noFail;
    private String noLitho;
    private String projekKerajaan;
    private String noWarta;
    private String catatan;
    private String tarikhWarta;
    private String noHakmilik;
    private String noLPS;
    private String idHakmilik;
    private String idHakmilikPermohonan;
    private String idPermohonanSebelum;
    private String idtanahrizabPermohonan;
    private String lokasi;
    private String ulasan;
    private String negeri;
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    boolean p;
    Permohonan prmhnn;
    String id;
    private List<HakmilikPermohonan> hakmilikPermohonanList;
    private List<HakmilikPermohonan> hakmilikPermohonanList_ref;
    private List<PermohonanLaporanKawasan> senaraiLaporanKawasan;
    private List<KodSeksyen> kodSeksyenList;
    private List<Permohonan> permohonanTerdahuluList;
    private List<PermohonanManual> permohonanManualList;
    private List<Permohonan> permohonanSebelumList = new ArrayList<Permohonan>();
    Long idTanahRizabPermohonan;
    private int size = 0;
    private List<TanahRizabPermohonan> tanahRizabList;
    private List<PermohonanLaporanKawasan> permohonanLaporKSWList;
    String idPermohonan;
    String id2;
    private Pemohon pemohon;
    boolean status = false;
    boolean simpan = false;
    private String stageId;
    private Pengguna peng;
    private String rizab_melayu;
    private String kodRizab;
    private String addnoWarta;
    private String addtarikhWarta;
    private String addnoPelanWarta;
    private String gsa;
    private String hutan;
    private String lain;
    private String pbt;
    private String catatanLain;
    private String kodT;
    private String kodPString;
    private char kodP;
    private boolean edit;
    private String kodPemilikan;
    private static final Logger LOG = Logger.getLogger(LaporanPelukisPelanGSAActionBean.class);
    private static final boolean IS_LOG_DEBUG = LOG.isDebugEnabled();
    private KodUOM kodUL;
    private String kodD;
    private String kodPar;

    @DefaultHandler
    public Resolution showForm() {
        edit = Boolean.TRUE;
        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        getContext().getRequest().setAttribute("flag", Boolean.TRUE);
        return new JSP("pelupusan/gsa/laporan_pelukis_pelanGSA.jsp").addParameter("tab", "true");
    }

    public Resolution viewOnlyLaporanPelan() {
        getContext().getRequest().setAttribute("edit", Boolean.FALSE);
        getContext().getRequest().setAttribute("flag", Boolean.TRUE);
        return new JSP("pelupusan/gsa/laporan_pelukis_pelanGSA.jsp").addParameter("tab", "true");
    }

    public Resolution showForm2() {
        return new JSP("pelupusan/gsa/laporan_pelukis_pelanGSA.jsp").addParameter("tab", "true");
    }

    public Resolution tanahRizabPopup() {
        p = true;
        return new JSP("pelupusan/tanah_rizab_add.jsp").addParameter("popup", "true").addParameter("showForm", "false");
    }

    public Resolution tanahMilikPopup() {
        return new JSP("pembangunan/pecahSempadan/tanah_milik_add.jsp").addParameter("popup", "true").addParameter("showForm", "false");
    }

    public Resolution permohonanTerdahuluPopup() {
        getContext().getRequest().setAttribute("status", Boolean.FALSE);
        return new JSP("pelupusan/gsa/carian_permohonan_terdahuluGSA.jsp").addParameter("popup", "true").addParameter("showForm", "false");
    }

    public Resolution kawasanPopup() {
        getContext().getRequest().setAttribute("status", Boolean.FALSE);
        return new JSP("pelupusan/tambahKawasan.jsp").addParameter("popup", "true").addParameter("showForm", "false");
    }

    public Resolution cariSemula() {
        getContext().getRequest().setAttribute("status", Boolean.FALSE);
        return new JSP("pelupusan/carian_permohonan_terdahulu.jsp").addParameter("popup", "true");
    }

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {
        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        // hakmilikPermohonanList = plpservice.getHakmilikPermohonan(idPermohonan);

        negeri = conf.getProperty("kodNegeri");
        //idTanahRizabPermohonan = (String) getContext().getRequest().getParameter("idTanahRizabPermohonan");

//         prmhnn = permohonanDAO.findById(id);
        if (idPermohonan != null) {
            permohonan = permohonanDAO.findById(idPermohonan);
            tanahRizabList = permohonan.getSenaraiTanahRizab();
            if (permohonan.getKodUrusan().getKod().equals("PHLP")) {
                hakmilikPermohonanList = plpservice.getHakmilikPermohonanListByCatatanNIdMohon(new String(), idPermohonan);
            } else {
                hakmilikPermohonanList = permohonan.getSenaraiHakmilik();
            }
            senaraiLaporanKawasan = plpservice.findLaporanKawasanByIdPermohonanNKodRizab(idPermohonan);
            hakmilikPermohonanList_ref = plpservice.getHakmilikPermohonan(idPermohonan);
            HakmilikPermohonan hmTemp = new HakmilikPermohonan() ;
            if(!hakmilikPermohonanList_ref.isEmpty()){
                size = hakmilikPermohonanList_ref.size() ;
                hmTemp = hakmilikPermohonanList_ref.get(0) ;
            }
//            senaraiLaporanKawasan = plpservice.find

            if (hakmilikPermohonanList != null) {
                if (permohonan.getKodUrusan().getKod().equals("PHLP") || permohonan.getKodUrusan().getKod().equals("PTGSA")) {
                    List senaraiHakmilikTerlibat = new ArrayList<HakmilikPermohonan>();
                    if (permohonan.getKodUrusan().getKod().equals("PTGSA")) {
                        senaraiHakmilikTerlibat = plpservice.getHakmilikPermohonanListByCatatanNIdMohon(new String(), idPermohonan);
                    } else {
                        senaraiHakmilikTerlibat = plpservice.getHakmilikPermohonanListByCatatanNIdMohon("L", idPermohonan);
                    }
                    if (senaraiHakmilikTerlibat.size() > 0) {
                        hakmilikPermohonan = (HakmilikPermohonan) senaraiHakmilikTerlibat.get(0);
                    }
                } else {
                    hakmilikPermohonan = plpservice.findByIdPermohonan(idPermohonan);
                }
                
                String idhakmilikpermohanon = getContext().getRequest().getParameter("idHakmilik");
                if(idhakmilikpermohanon == null){
                    hakmilikPermohonan = new HakmilikPermohonan();
                    hakmilikPermohonan = hakmilikPermohonanDAO.findById(hmTemp.getId());
                }
                else if (idhakmilikpermohanon != null && !idhakmilikpermohanon.equals("")) {
                    hakmilikPermohonan = new HakmilikPermohonan();
                    hakmilikPermohonan = hakmilikPermohonanDAO.findById(new Long(idhakmilikpermohanon));
                }
//                hakmilikPermohonan = plpservice.findByIdPermohonan(idPermohonan) ;
                if ((hakmilikPermohonan != null) && (hakmilikPermohonan.getKodMilik() != null)) {
                    kodP = hakmilikPermohonan.getKodMilik().getKod();
                    kodPString = hakmilikPermohonan.getKodMilik().getNama();
                }
            }
            if (hakmilikPermohonan != null && hakmilikPermohonan.getBandarPekanMukimBaru() != null) {
                kodSeksyenList = plpservice.getSenaraiKodSeksyenByBPM(hakmilikPermohonan.getBandarPekanMukimBaru().getKod());
            }

            //Add for Parlimen and Adun
            if ((hakmilikPermohonan != null) && (hakmilikPermohonan.getKodDUN() != null)) {
                kodD = hakmilikPermohonan.getKodDUN().getKod();
            }
            if ((hakmilikPermohonan != null) && (hakmilikPermohonan.getKodKawasanParlimen() != null)) {
                kodPar = hakmilikPermohonan.getKodKawasanParlimen().getKod();
            }

//            String[] tname = {"permohonan"};
//            Object[] model = {permohonan};

            if (permohonan.getPermohonanSebelum() != null) {
                getContext().getRequest().setAttribute("status", Boolean.TRUE);
                getContext().getRequest().setAttribute("simpan", Boolean.TRUE);
                prmhnn = permohonanDAO.findById(permohonan.getPermohonanSebelum().getIdPermohonan());
                pemohon = plpservice.findPemohonByIdPemohon(prmhnn.getIdPermohonan());
            }

            String _idhakmilikpermohanon = getContext().getRequest().getParameter("idHakmilik");
            if(_idhakmilikpermohanon == null || _idhakmilikpermohanon.equals("")){
                permohonanLaporanPelan= laporanPelukisPelanService.findMohonLaporPelanByidMohonHackmilikMohan(idPermohonan,hmTemp.getId()) ; 
            }else {
                permohonanLaporanPelan= laporanPelukisPelanService.findMohonLaporPelanByidMohonHackmilikMohan(idPermohonan,new Long(_idhakmilikpermohanon)) ;
            }
            //_idhakmilikpermohanon = (_idhakmilikpermohanon == null || _idhakmilikpermohanon.equals("")) ? "0" : _idhakmilikpermohanon;
//            permohonanLaporanPelan= laporanPelukisPelanService.findByidP(idPermohonan);
            
            // List pplp = permohonanLaporanPelanDAO.findByEqualCriterias(tname, model, null) ;


//           tanahrizabpermohonan=tanahRizabService.findByidPermohonan(idPermohonan);
            // tanahrizabpermohonan=laporanPelukisPelanService.findTanahRizabByIdTanahRizab(Long.parseLong(idtanahRizabPermohonan));

            if (permohonanLaporanPelan != null) {
                noLitho = permohonanLaporanPelan.getNoLitho();
                catatan = permohonanLaporanPelan.getCatatan();
                projekKerajaan = permohonanLaporanPelan.getProjekKerajaan();
                permohonanLaporanUlasan = pelupusanService.findByIdMohonByJenisUlasan(idPermohonan, "JT_LL");
                if (permohonanLaporanUlasan != null) {
                    ulasan = permohonanLaporanUlasan.getUlasan();
                }
            }

            peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
            String taskId = (String) getContext().getRequest().getSession().getAttribute("taskId");
            BPelService serviceBpel = new BPelService();
            if (StringUtils.isNotBlank(taskId)) {
                Task t = null;
                t = serviceBpel.getTaskFromBPel(taskId, peng);
                stageId = t.getSystemAttributes().getStage();
            }


//            if(tanahrizabpermohonan != null) {
//                tarikhWarta = sdf.format(tanahrizabpermohonan.getTarikhWarta()).substring(0, 10);
//            }
//            else{
//                tanahrizabpermohonan = new TanahRizabPermohonan();
//            }

            ///trace mohon_laporan_kws
            permohonanLaporKSWList = laporanPelukisPelanService.findByidMohon(idPermohonan);
            LOG.info(permohonanLaporKSWList);
            for (PermohonanLaporanKawasan lpk : permohonanLaporKSWList) {
                LOG.info("trace kod rizab :: " + lpk.getKodRizab().getKod());
                String rezKod = String.valueOf(lpk.getKodRizab().getKod());
                if (rezKod.equalsIgnoreCase("1")) {
                    LOG.info("Load Kod Rizab 1");
                    pbt = "1";
                } else if (rezKod.equalsIgnoreCase("2")) {
                    gsa = "2";
                } else if (rezKod.equalsIgnoreCase("3")) {
                    rizab_melayu = "3";
                } else if (rezKod.equalsIgnoreCase("4")) {
                    hutan = "4";
                } else if (rezKod.equalsIgnoreCase("99")) {

                    lain = "99";
                    catatanLain = lpk.getCatatan();
                }
            }
        }

        permohonanManualList = plpservice.findByIdMohonlist(idPermohonan);
        LOG.info("--------permohonanManualList---------::" + permohonanManualList);

    }

    public Resolution showEditTanahRizab() {
        String idtanahRizabPermohonan = getContext().getRequest().getParameter("idTanahRizabPermohonan");
        tanahrizabpermohonan = tanahrizabPermohonanDAO.findById(Long.valueOf(idtanahRizabPermohonan));
        p = true;
        return new JSP("pelupusan/tanah_rizab_edit.jsp").addParameter("popup", "true");
    }

    public Resolution showEditPermohonanTerdahulu() {
        String idPermohonan = getContext().getRequest().getParameter("idPermohonan");
        Permohonan permohonan = permohonanDAO.findById(idPermohonan);
        return new JSP("pembangunan/pecahSempadan/permohonan_terdahulu_edit.jsp").addParameter("popup", "true");
    }

    public Resolution refreshpage() {
        rehydrate();
        String status = getContext().getRequest().getParameter("status");
        if (!StringUtils.isEmpty(status)) {
            if (status.equals("1")) {
                addSimpleMessage("Permohonan berjaya disimpan");
            }
        }
        return new RedirectResolution(LaporanPelukisPelanGSAActionBean.class, "locate");
    }

    public Resolution openLainlain() {
        String kodJenisRizab = (String) getContext().getRequest().getParameter("jenisRizab");
        if (kodJenisRizab.equals("99")) { //Lain-lain
            jenisRizab = Boolean.TRUE;
        } else {
            jenisRizab = Boolean.FALSE;
        }
        return new ForwardResolution("/WEB-INF/jsp/pelupusan/tambahKawasan.jsp").addParameter("popup", "true");
    }

    public Resolution tutupPermohonan() {
        rehydrate();
        return new ForwardResolution("/WEB-INF/jsp/pelupusan/gsa/laporan_pelukis_pelanGSA.jsp").addParameter("tab", "true");

    }

    public Resolution simpanTanahMilik() throws ParseException {

        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        InfoAudit info = peng.getInfoAudit();

        Permohonan permohonan = permohonanDAO.findById(idPermohonan);
        hakmilik = hakmilikDAO.findById(idHakmilik);

        if (idPermohonan != null) {
            hakmilikPermohonan = new HakmilikPermohonan();
            info.setDimasukOleh(peng);
            info.setTarikhMasuk(new java.util.Date());
            hakmilikPermohonan.setInfoAudit(info);
            hakmilikPermohonan.setPermohonan(permohonan);
            hakmilikPermohonan.setHakmilik(hakmilik);
            //hakmilikPermohonanList.add(hakmilikPermohonan);
            laporanPelukisPelanService.saveOrUpdateTanahMilik(hakmilikPermohonan);
            addSimpleMessage("Maklumat telah berjaya disimpan");
        }
        return new ForwardResolution("/WEB-INF/jsp/pelupusan/gsa/laporan_pelukis_pelanGSA.jsp").addParameter("tab", "true");
    }

    public Resolution deleteTanahMilik() {
        InfoAudit ia = new InfoAudit();
        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        String idhp = getContext().getRequest().getParameter("id");
        hakmilikPermohonan = laporanPelukisPelanService.findTanahMilikByIdHakmilikPermohonan(idhp);

        if (hakmilikPermohonan != null) {
            ia = peng.getInfoAudit();
            ia.setDiKemaskiniOleh(peng);
            ia.setTarikhKemaskini(new java.util.Date());
            hakmilikPermohonan.setInfoAudit(ia);
            //hakmilikPermohonan.setHakmilik(hakmilik);
            laporanPelukisPelanService.deleteTanahMilik(hakmilikPermohonan);
        }
        return new RedirectResolution(LaporanPelukisPelanGSAActionBean.class, "locate");
    }

    public Resolution simpanTanahRizab() throws ParseException {
        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
//        tanahrizabpermohonan=tanahRizabService.findByidPermohonan(idPermohonan);
        InfoAudit info = peng.getInfoAudit();

        Permohonan permohonan = permohonanDAO.findById(idPermohonan);
        if (idPermohonan != null) {
            tanahrizabpermohonan = new TanahRizabPermohonan();
            if (StringUtils.isNotBlank(tarikhWarta)) {
                info.setDimasukOleh(peng);
                info.setTarikhMasuk(new java.util.Date());
                tanahrizabpermohonan.setTarikhWarta(sdf.parse(tarikhWarta));
                tarikhWarta = sdf.format(tanahrizabpermohonan.getTarikhWarta());
//                    tarikhWarta = sdf.format(tanahrizabpermohonan.getTarikhWarta()).substring(0, 10);
            }
            tanahrizabpermohonan.setInfoAudit(info);
            tanahrizabpermohonan.setPermohonan(permohonan);
            tanahrizabpermohonan.setBandarPekanMukim(bandarPekanMukim);
            tanahrizabpermohonan.setDaerah(daerah);
            tanahrizabpermohonan.setCawangan(cawangan);
            tanahrizabpermohonan.setNoLot(noLot);
            tanahrizabpermohonan.setNoLitho(noLitho);
            tanahrizabpermohonan.setNoWarta(noWarta);
            tanahrizabpermohonan.setLokasi(lokasi);
            tanahrizabpermohonan.setRizab(rizab);
            tanahrizabpermohonan.setAktif('Y');
            tanahRizabList.add(tanahrizabpermohonan);
            laporanPelukisPelanService.saveOrUpdatetanahRizab(tanahrizabpermohonan);
            addSimpleMessage("Maklumat telah berjaya disimpan");
        } else {
            tanahrizabpermohonan = new TanahRizabPermohonan();
            if (StringUtils.isNotBlank(tarikhWarta)) {
                info.setDimasukOleh(peng);
                info.setTarikhMasuk(new java.util.Date());
                tanahrizabpermohonan.setTarikhWarta(sdf.parse(tarikhWarta));
                tarikhWarta = sdf.format(tanahrizabpermohonan.getTarikhWarta());
//                    tarikhWarta = sdf.format(tanahrizabpermohonan.getTarikhWarta()).substring(0, 10);
            }
            tanahrizabpermohonan.setInfoAudit(info);
            tanahrizabpermohonan.setPermohonan(permohonan);
            tanahrizabpermohonan.setBandarPekanMukim(bandarPekanMukim);
            tanahrizabpermohonan.setDaerah(daerah);
            tanahrizabpermohonan.setCawangan(cawangan);
            tanahrizabpermohonan.setNoLot(noLot);
            tanahrizabpermohonan.setNoLitho(noLitho);
            tanahrizabpermohonan.setNoWarta(noWarta);
            tanahrizabpermohonan.setLokasi(lokasi);
            tanahrizabpermohonan.setRizab(rizab);
            tanahrizabpermohonan.setAktif('Y');
            tanahRizabList.add(tanahrizabpermohonan);
            laporanPelukisPelanService.saveOrUpdatetanahRizab(tanahrizabpermohonan);
            addSimpleMessage("Maklumat telah berjaya disimpan");
        }

        rehydrate();
        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        return new ForwardResolution("/WEB-INF/jsp/pelupusan/gsa/laporan_pelukis_pelanGSA.jsp").addParameter("tab", "true");
        //return new JSP("pelupusan/laporan_pelukis_pelanGSA.jsp").addParameter("tab", "true");



    }

    public Resolution editTanahRizab() throws ParseException {

        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        Pengguna pg = (Pengguna) context.getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        idtanahrizabPermohonan = getContext().getRequest().getParameter("idTanahRizabPermohonan");
        Permohonan permohonan = permohonanDAO.findById(idPermohonan);
        InfoAudit ia = new InfoAudit();
        if (ia == null) {
            tanahrizabpermohonan = new TanahRizabPermohonan();
            ia = new InfoAudit();
            ia.setDimasukOleh(pg);
            ia.setTarikhMasuk(new java.util.Date());
            tanahrizabpermohonan.setInfoAudit(ia);
        } else {

            ia.setDiKemaskiniOleh(pg);
            ia.setTarikhKemaskini(new java.util.Date());
            ia.setDimasukOleh(pg);
            ia.setTarikhMasuk(new java.util.Date());
            tanahrizabpermohonan.setInfoAudit(ia);
            tanahrizabpermohonan.setPermohonan(permohonan);
            tanahrizabpermohonan.setAktif('Y');


        }
        tanahRizabService.saveOrUpdatetanahRizab(tanahrizabpermohonan);

        addSimpleMessage("Data Telah Berjaya dikemaskini");
        rehydrate();
        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        return new ForwardResolution("/WEB-INF/jsp/pelupusan/gsa/laporan_pelukis_pelanGSA.jsp").addParameter("tab", "true");
        //return new JSP("pelupusan/laporan_pelukis_pelanGSA.jsp").addParameter("tab", "true");




    }

    public Resolution deleteTanahRizab() {
        InfoAudit ia = new InfoAudit();
        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        tanahrizabpermohonan = new TanahRizabPermohonan();
        String idtanahRizabPermohonan = getContext().getRequest().getParameter("idTanahRizabPermohonan");
        tanahrizabpermohonan = laporanPelukisPelanService.findTanahRizabByIdTanahRizab(Long.parseLong(idtanahRizabPermohonan));

        if (tanahrizabpermohonan != null) {
            ia = peng.getInfoAudit();
            ia.setDiKemaskiniOleh(peng);
            ia.setTarikhKemaskini(new java.util.Date());
            tanahrizabpermohonan.setInfoAudit(ia);
            tanahrizabpermohonan.setPermohonan(permohonan);
            tanahrizabpermohonan.setBandarPekanMukim(bandarPekanMukim);
            tanahrizabpermohonan.setDaerah(daerah);
            tanahrizabpermohonan.setCawangan(cawangan);
            tanahrizabpermohonan.setNoLot(noLot);
            tanahrizabpermohonan.setNoWarta(noWarta);
            tanahrizabpermohonan.setRizab(rizab);
            //tanahrizabpermohonan.setCatatan(catatan);
            laporanPelukisPelanService.deleteAll(tanahrizabpermohonan);
        }
        return new RedirectResolution(LaporanPelukisPelanGSAActionBean.class, "locate");
    }

    public Resolution cariPermohonan() {
        if (id == null || id.equals("")) {
            addSimpleError("Masukkan Permohonan Id");
            getContext().getRequest().setAttribute("status", Boolean.FALSE);
            return new JSP("pelupusan/gsa/carian_permohonan_terdahuluGSA.jsp").addParameter("popup", "true");
        }
        prmhnn = permohonanDAO.findById(id);
        if (prmhnn != null) {
            getContext().getRequest().setAttribute("status", Boolean.TRUE);
            getContext().getRequest().setAttribute("simpan", Boolean.FALSE);
            pemohon = plpservice.findPemohonByIdPemohon(prmhnn.getIdPermohonan());
            System.out.println("----pemohon-------" + pemohon);
            String[] tname = {"permohonan"};
            Object[] model = {permohonan};
//            hmpList = hakmilikPermohonanDAO.findByEqualCriterias(tname, model, null);
            addSimpleMessage("Maklumat dijumpai");
            id2 = id;
        } else {
            addSimpleError("Maklumat tidak dijumpai");
            getContext().getRequest().setAttribute("status", Boolean.FALSE);
        }
        return new JSP("pelupusan/gsa/carian_permohonan_terdahuluGSA.jsp").addParameter("popup", "true");
    }

    public Resolution simpanPermohonanSblm() throws ParseException {
        prmhnn = permohonanDAO.findById(id2);
        cawangan = permohonan.getCawangan();
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = permohonanDAO.findById(idPermohonan);
        permohonan.setPermohonanSebelum(prmhnn);
//        plpservice.simpanPermohonan(permohonan);
        LOG.info("------simpan---Permohonan Terdahulu--------------::");
        LOG.info("------idPermohonan--------------::" + idPermohonan);
        LOG.info("------NoFail--------------::" + id2);
        permohonanManual = plpservice.findByIdMohonFailNo(idPermohonan, id2);

        InfoAudit infoAudit = new InfoAudit();
        if (permohonanManual != null) {
            LOG.info("--------permohonanManual NOT Null------------::");
            infoAudit = permohonanManual.getInfoAudit();
            infoAudit.setDiKemaskiniOleh(peng);
            infoAudit.setTarikhKemaskini(new java.util.Date());
        } else {
            LOG.info("--------permohonanManual Null------------::");
            permohonanManual = new PermohonanManual();
            infoAudit.setDimasukOleh(peng);
            infoAudit.setTarikhMasuk(new java.util.Date());
            permohonanManual.setInfoAudit(infoAudit);
            permohonanManual.setIdPermohonan(permohonan);
            permohonanManual.setNoFail(id2);
            permohonanManual.setCawangan(cawangan);
        }
        plpservice.simpanPermohonanManual(permohonanManual);

        addSimpleMessage("Data Telah Berjaya dikemaskini");
        rehydrate();
        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        return new ForwardResolution("/WEB-INF/jsp/pelupusan/gsa/laporan_pelukis_pelanGSA.jsp").addParameter("tab", "true");

    }

    public Resolution simpanKawasan() throws ParseException {
        PermohonanLaporanKawasan kws = new PermohonanLaporanKawasan();
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = permohonanDAO.findById(idPermohonan);
        InfoAudit infoAudit = new InfoAudit();
        infoAudit.setDimasukOleh(peng);
        infoAudit.setTarikhMasuk(new java.util.Date());
        kws.setKodRizab(kodRizabDAO.findById(Integer.parseInt(kodRizab)));
        if (kodRizab.equals("99")) {
            kws.setCatatan(catatan);
        }
        kws.setNoWarta(addnoWarta);
        kws.setInfoAudit(infoAudit);
        if (addtarikhWarta != null && !("").equals(addtarikhWarta)) {
            kws.setTarikhWarta(sdf.parse(addtarikhWarta));
        }
        kws.setNoPelanWarta(addnoPelanWarta);
        kws.setPermohonan(permohonan);
        kws.setKodCawangan(permohonan.getCawangan());
        plpservice.simpanPermohonanLaporKawasan(kws);
        addSimpleMessage("Maklumat Berjaya Disimpan");
        rehydrate();
        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        return new ForwardResolution("/WEB-INF/jsp/pelupusan/gsa/laporan_pelukis_pelanGSA.jsp").addParameter("tab", "true");

    }

    public Resolution deletePermohonanTerdahulu() {
        InfoAudit ia = new InfoAudit();
        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        Permohonan permohonan = permohonanDAO.findById(idPermohonan);

//        if (permohonan != null) {
//            ia = peng.getInfoAudit();
//            ia.setDiKemaskiniOleh(peng);
//            ia.setTarikhKemaskini(new java.util.Date());
//            permohonan.setInfoAudit(ia);
//            permohonan.setPermohonanSebelum(null);
//            laporanPelukisPelanService.saveOrUpdatePermohonan(permohonan);
//        }

        LOG.info("--------Delete permohonanManual------------::");
        String idMohonManual = getContext().getRequest().getParameter("idMohonManual");

        if (idMohonManual != null) {
            LOG.info("--------permohonanManual not null------------::");
            PermohonanManual permohonanManual1 = permohonanManualDAO.findById(Long.parseLong(idMohonManual));
            LOG.info("--------permohonanManual1::------------::" + permohonanManual1);
            if (permohonanManual1 != null) {
                pelupusanService.deletePermohonanManual(permohonanManual1);

            }
        }


        return new RedirectResolution(LaporanPelukisPelanGSAActionBean.class, "locate");
    }

    public Resolution removeLaporKawasan() {
        InfoAudit ia = new InfoAudit();
        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        Permohonan permohonan = permohonanDAO.findById(idPermohonan);
        String idMohonlaporKws = getContext().getRequest().getParameter("idMohonlaporKws");

        if (idMohonlaporKws != null) {
            PermohonanLaporanKawasan mohonLaporKwsn = new PermohonanLaporanKawasan();
            mohonLaporKwsn = permohonanLaporanKawasanDAO.findById(Long.parseLong(idMohonlaporKws));
            if (mohonLaporKwsn != null) {
                pelupusanService.deletePermohonanLaporanKwsn(mohonLaporKwsn);

            }
        }

        return new RedirectResolution(LaporanPelukisPelanGSAActionBean.class, "locate");
    }

    public boolean validation() {
        boolean flag = false;
        if ((kodT == null) || (kodT.equals("0"))) {
            flag = true;
            addSimpleError("Sila Pilih KodTanah");
        }

//        if ((kodP == null)||(kodP.equals("0"))) {
//            flag = true;
//            addSimpleError("Sila Pilih KodPemilik");
//        }

        if ((noLitho == null)) {
            flag = true;
            addSimpleError("Sila Pilih No. Lembaran Piawai");
        }

        String kod2 = getContext().getRequest().getParameter("kodUnitLuas.kod");
        String luas = getContext().getRequest().getParameter("hakmilikPermohonan.luasTerlibat");
        if (StringUtils.isBlank(luas)) {
            flag = true;
            addSimpleError("Sila Masukkan Luas");
        } else if (StringUtils.isBlank(kod2)) {
            flag = true;
            addSimpleError("Sila Masukkan Kod Unit Luas");
        }


        return flag;
    }

    public Resolution searchHakmilik() throws ParseException {

        String idhakmilikpermohanon = getContext().getRequest().getParameter("idHakmilik");
        String editType = getContext().getRequest().getParameter("edit");
        hakmilikPermohonan = new HakmilikPermohonan();
        hakmilikPermohonan = hakmilikPermohonanDAO.findById(new Long(idhakmilikpermohanon));
        rehydrate();
        if(StringUtils.isEmpty(editType)){
            if(editType.equalsIgnoreCase("true")){
                edit = Boolean.TRUE;
            }else
                edit = Boolean.FALSE;
        }
        return new ForwardResolution("/WEB-INF/jsp/pelupusan/gsa/laporan_pelukis_pelanGSA.jsp").addParameter("tab", "true");
    }

    public Resolution simpan() throws ParseException {

        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        InfoAudit info = peng.getInfoAudit();
        String kodS = getContext().getRequest().getParameter("kodSeksyen.nama");
        noLitho = getContext().getRequest().getParameter("permohonanLaporanPelan.noLitho");
        
        //add record in HakmilikPermohonan
        String idhakmilikpermohanon = getContext().getRequest().getParameter("idHakmilik");
        hakmilikPermohonan = new HakmilikPermohonan();
        hakmilikPermohonan = hakmilikPermohonanDAO.findById(new Long(idhakmilikpermohanon));

        LOG.info("-------------Saving in hakmilik permohonan-----------");
        if (permohonan.getKodUrusan().getKod().equals("PHLP") || permohonan.getKodUrusan().getKod().equals("PTGSA")) {
            List senaraiHakmilikTerlibat = new ArrayList<HakmilikPermohonan>();
            if (permohonan.getKodUrusan().getKod().equals("PTGSA")) {
                senaraiHakmilikTerlibat = plpservice.getHakmilikPermohonanListByCatatanNIdMohon(new String(), idPermohonan);
            } else {
                senaraiHakmilikTerlibat = plpservice.getHakmilikPermohonanListByCatatanNIdMohon("L", idPermohonan);
            }
//                     if(senaraiHakmilikTerlibat.size()>0)
//                         hakmilikPermohonan = (HakmilikPermohonan) senaraiHakmilikTerlibat.get(0);
        }
//                 else
//                     hakmilikPermohonan = plpservice.findByIdPermohonan(idPermohonan);
        if (validation()) {
            LOG.info("------simpan---validate--------------");
            getContext().getRequest().setAttribute("edit", Boolean.TRUE);
            getContext().getRequest().setAttribute("flag", Boolean.TRUE);
            return new JSP("pelupusan/gsa/laporan_pelukis_pelanGSA.jsp").addParameter("tab", "true");
        }
//        rizab_melayu = getContext().getRequest().getParameter("rizab_melayu");
        LOG.info(kodS);

        LOG.info("Test Rizab Melayu :: " + rizab_melayu);
        LOG.info("Pihak BerKuasa Tempatan  :: " + pbt);
        LOG.info("Rizab Hutan :: " + hutan);
        LOG.info("Rizab GSA :: " + gsa);
        LOG.info("Lain2 :: " + lain);

        if (!kodS.equals("0")) {
            Integer a = Integer.parseInt(kodS);
            int kod = a.intValue();
            KodSeksyen kodSeksyen = plpservice.findByKodSeksyen(kod);
            hakmilikPermohonan.setKodSeksyen(kodSeksyen);
            plpservice.simpanHakmilikPermohonan(hakmilikPermohonan);
        } else {
            hakmilikPermohonan.setKodSeksyen(null);
            plpservice.simpanHakmilikPermohonan(hakmilikPermohonan);
        }
        Permohonan permohonan = permohonanDAO.findById(idPermohonan);
        cawangan = permohonan.getCawangan();
        //permohonanLaporanPelan = plpservice.findByIdPermohonanPelan(idPermohonan);
        permohonanLaporanPelan = laporanPelukisPelanService.findMohonLaporPelanByidMohonHackmilikMohan(idPermohonan, new Long(idhakmilikpermohanon));

        KodTanah kodTanah = kodTanahDAO.findById(kodT);
        if (permohonanLaporanPelan == null) {

            permohonanLaporanPelan = new PermohonanLaporanPelan();
            info.setDimasukOleh(peng);
            info.setTarikhMasuk(new java.util.Date());
            permohonanLaporanPelan.setInfoAudit(info);
            permohonanLaporanPelan.setPermohonan(permohonan);
            permohonanLaporanPelan.setCawangan(cawangan);
            permohonanLaporanPelan.setNoLitho(noLitho);
            permohonanLaporanPelan.setProjekKerajaan(projekKerajaan);
            permohonanLaporanPelan.setKodTanah(kodTanah);
            permohonanLaporanPelan.setCatatan(catatan);
            //hakmilikPermohonanList.add(hakmilikPermohonan);
            permohonanLaporanPelan.setHakmilikPermohonan(hakmilikPermohonan);
            laporanPelukisPelanService.saveOrUpdatePermohonanLaporanPelan(permohonanLaporanPelan);

        } else {
            info.setDiKemaskiniOleh(peng);
            info.setTarikhKemaskini(new java.util.Date());
            permohonanLaporanPelan.setInfoAudit(info);
            permohonanLaporanPelan.setCawangan(cawangan);
            permohonanLaporanPelan.setNoLitho(noLitho);
            permohonanLaporanPelan.setProjekKerajaan(projekKerajaan);
            permohonanLaporanPelan.setCatatan(catatan);
            permohonanLaporanPelan.setKodTanah(kodTanah);
            permohonanLaporanPelan.setHakmilikPermohonan(hakmilikPermohonan);
            laporanPelukisPelanService.saveOrUpdatePermohonanLaporanPelan(permohonanLaporanPelan);
        }

        if (kodTanah.getKod().equals("99")) { //Lain - lain
            LOG.info("Adding for Ulasan Laporan Pelan for Lain-lain");
            permohonanLaporanUlasan = pelupusanService.findByIdMohonByJenisUlasan(idPermohonan, "JT_LL");
            InfoAudit ia = new InfoAudit();
            if (permohonanLaporanUlasan == null) {
                permohonanLaporanUlasan = new PermohonanLaporanUlasan();
                ia.setDimasukOleh(peng);
                ia.setTarikhMasuk(new java.util.Date());

            } else {
                ia = permohonanLaporanUlasan.getInfoAudit();
                ia.setDiKemaskiniOleh(peng);
                ia.setTarikhKemaskini(new java.util.Date());
            }
            permohonanLaporanUlasan.setInfoAudit(info);
            permohonanLaporanUlasan.setPermohonan(permohonan);
            permohonanLaporanUlasan.setCawangan(cawangan);
            permohonanLaporanUlasan.setJenisUlasan("JT_LL");
            KodDokumen kodDok = kodDokumenDAO.findById("LPE");
            permohonanLaporanUlasan.setKodDokumen(kodDok);
            permohonanLaporanUlasan.setUlasan(ulasan);
            pelupusanService.simpanPermohonanLaporanUlasan(permohonanLaporanUlasan);

        } else {
            permohonanLaporanUlasan = pelupusanService.findByIdMohonByJenisUlasan(idPermohonan, "JT_LL");
            LOG.info("Delete for Ulasan Laporan Pelan for Lain-lain");
            if (permohonanLaporanUlasan != null) {
                pelupusanService.deletePermohonanLaporanUlasan(permohonanLaporanUlasan);
            }

        }

//        Add record in mohon_lapor_kws
        LOG.info("Check Value PBT::" + pbt);
        if (StringUtils.isNotBlank(pbt)) {
            rizab = tanahRizabService.findByKod(pbt);
            permohonanLaporanKWS = laporanPelukisPelanService.findByidMohonKodRizab(idPermohonan, pbt);
            if (permohonanLaporanKWS != null) {
                updatePerLaPoKaw(permohonanLaporanKWS, rizab, info, "tick");
            } else {
                savePerLaPoKaw(permohonanLaporanKWS, rizab, info);
            }

        } else {
            rizab = tanahRizabService.findByKod("1");
            permohonanLaporanKWS = laporanPelukisPelanService.findByidMohonKodRizab(idPermohonan, "1");
            if (permohonanLaporanKWS != null) {
                updatePerLaPoKaw(permohonanLaporanKWS, rizab, info, "UnTick");
            } else {
                //DO Nothing
            }
        }


        if (StringUtils.isNotBlank(hutan)) {
            rizab = tanahRizabService.findByKod(hutan);
            permohonanLaporanKWS = laporanPelukisPelanService.findByidMohonKodRizab(idPermohonan, hutan);
            if (permohonanLaporanKWS != null) {
                updatePerLaPoKaw(permohonanLaporanKWS, rizab, info, "tick");
            } else {
                savePerLaPoKaw(permohonanLaporanKWS, rizab, info);
            }

        } else {
            rizab = tanahRizabService.findByKod("4");
            permohonanLaporanKWS = laporanPelukisPelanService.findByidMohonKodRizab(idPermohonan, "4");
            if (permohonanLaporanKWS != null) {
                updatePerLaPoKaw(permohonanLaporanKWS, rizab, info, "UnTick");
            } else {
                //DO Nothing
            }
        }

        if (StringUtils.isNotBlank(rizab_melayu)) {
            rizab = tanahRizabService.findByKod(rizab_melayu);
            permohonanLaporanKWS = laporanPelukisPelanService.findByidMohonKodRizab(idPermohonan, rizab_melayu);
            if (permohonanLaporanKWS != null) {
                updatePerLaPoKaw(permohonanLaporanKWS, rizab, info, "tick");
            } else {
                savePerLaPoKaw(permohonanLaporanKWS, rizab, info);
            }

        } else {
            rizab = tanahRizabService.findByKod("3");
            permohonanLaporanKWS = laporanPelukisPelanService.findByidMohonKodRizab(idPermohonan, "3");
            if (permohonanLaporanKWS != null) {
                updatePerLaPoKaw(permohonanLaporanKWS, rizab, info, "UnTick");
            } else {
                //DO Nothing
            }
        }
        if (StringUtils.isNotBlank(gsa)) {
            rizab = tanahRizabService.findByKod(gsa);
            permohonanLaporanKWS = laporanPelukisPelanService.findByidMohonKodRizab(idPermohonan, gsa);
            if (permohonanLaporanKWS != null) {
                updatePerLaPoKaw(permohonanLaporanKWS, rizab, info, "tick");
            } else {
                savePerLaPoKaw(permohonanLaporanKWS, rizab, info);
            }

        } else {
            rizab = tanahRizabService.findByKod("2");
            permohonanLaporanKWS = laporanPelukisPelanService.findByidMohonKodRizab(idPermohonan, "2");
            if (permohonanLaporanKWS != null) {
                updatePerLaPoKaw(permohonanLaporanKWS, rizab, info, "UnTick");
            } else {
                //DO Nothing
            }
        }
        if (StringUtils.isNotBlank(lain)) {
            rizab = tanahRizabService.findByKod(lain);
            permohonanLaporanKWS = laporanPelukisPelanService.findByidMohonKodRizab(idPermohonan, lain);
            if (permohonanLaporanKWS != null) {
                updatePerLaPoKaw(permohonanLaporanKWS, rizab, info, "tick");
            } else {
                savePerLaPoKaw(permohonanLaporanKWS, rizab, info);
            }

        } else {
            rizab = tanahRizabService.findByKod("99");
            permohonanLaporanKWS = laporanPelukisPelanService.findByidMohonKodRizab(idPermohonan, "99");
            if (permohonanLaporanKWS != null) {
                updatePerLaPoKaw(permohonanLaporanKWS, rizab, info, "UnTick");
            } else {
                //DO Nothing
            }
        }

        // end add record in mohon_lapor_kws



//        hakmilikPermohonan = pelupusanService.findByIdPermohonan(idPermohonan);
        KodPemilikan kodPemilikan = kodPemilikanDAO.findById(kodP);
        LOG.info("-------------find kodPemilikan::-----------" + kodPemilikan);

        InfoAudit infoAudit = new InfoAudit();
        if (hakmilikPermohonan != null) {
            infoAudit = hakmilikPermohonan.getInfoAudit();
            infoAudit.setDiKemaskiniOleh(peng);
            infoAudit.setTarikhKemaskini(new java.util.Date());
        }
        if (kodD != null) {
            KodDUN kd = kodDUNDAO.findById(kodD);
            hakmilikPermohonan.setKodDUN(kd);
        }
        if (kodPar != null) {
            KodKawasanParlimen kw = kodKawasanParlimenDAO.findById(kodPar);
            hakmilikPermohonan.setKodKawasanParlimen(kw);
        }

        kodUL = new KodUOM();
        String kod2 = getContext().getRequest().getParameter("kodUnitLuas.kod");

        if (StringUtils.isNotBlank(kod2)) {
            kodUL.setKod(kod2);
            hakmilikPermohonan.setKodUnitLuas(kodUL);
        }

        hakmilikPermohonan.setInfoAudit(infoAudit);
        hakmilikPermohonan.setPermohonan(permohonan);
        hakmilikPermohonan.setKodMilik(kodPemilikan);
        LOG.info("kodmilik in mohon_hakmilik :: " + kodPemilikan);
        pelupusanService.simpanHakmilikPermohonan(hakmilikPermohonan);

//        }

        addSimpleMessage("Maklumat telah berjaya disimpan");
        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        getContext().getRequest().setAttribute("flag", Boolean.FALSE);
        return new ForwardResolution("/WEB-INF/jsp/pelupusan/gsa/laporan_pelukis_pelanGSA.jsp").addParameter("tab", "true");
        // return new JSP("pelupusan/laporan_pelukis_pelanGSA.jsp").addParameter("tab", "true");
    }

    public void savePerLaPoKaw(PermohonanLaporanKawasan laporanKawasan, KodRizab kodRizab, InfoAudit info) {
        String kodRi = String.valueOf(kodRizab.getKod());
        laporanKawasan = new PermohonanLaporanKawasan();
        info.setDiKemaskiniOleh(peng);
        info.setTarikhKemaskini(new java.util.Date());
        laporanKawasan.setInfoAudit(info);
        laporanKawasan.setKodCawangan(cawangan);
        laporanKawasan.setKodRizab(kodRizab);
        laporanKawasan.setAktif("Y");
        if (kodRi.equalsIgnoreCase("99")) {
            if (StringUtils.isNotBlank(catatanLain)) {
                laporanKawasan.setCatatan(catatanLain);
            }
        }
        laporanKawasan.setPermohonan(permohonan);
        laporanPelukisPelanService.saveOrUpdatePermohonanLaporanKawasan(laporanKawasan);

        LOG.info("save success");


    }

    public void updatePerLaPoKaw(PermohonanLaporanKawasan laporanKawasan, KodRizab kodRizab, InfoAudit info, String FlagSave) {
        String kodRi = String.valueOf(kodRizab.getKod());
        if (laporanKawasan != null) {
            info.setDiKemaskiniOleh(peng);
            info.setTarikhKemaskini(new java.util.Date());
            laporanKawasan.setInfoAudit(info);
            if (FlagSave.equalsIgnoreCase("tick")) {
                laporanKawasan.setAktif("Y");
            } else if (FlagSave.equalsIgnoreCase("UnTick")) {
                laporanKawasan.setAktif("T");
            }
            if (kodRi.equalsIgnoreCase("99")) {
                if (StringUtils.isNotBlank(catatanLain)) {
                    laporanKawasan.setCatatan(catatanLain);
                }
            }
            laporanPelukisPelanService.saveOrUpdatePermohonanLaporanKawasan(laporanKawasan);
            LOG.info("Succes update");
        }

    }

    public Permohonan getPermohonan() {
        return permohonan;
    }

    public void setPermohonan(Permohonan permohonan) {
        this.permohonan = permohonan;
    }

    public Long getIdTanahRizab() {
        return idTanahRizabPermohonan;
    }

    public void setIdTanahRizab(Long idTanahRizabPermohonan) {
        this.idTanahRizabPermohonan = idTanahRizabPermohonan;
    }

    public String getNoLot() {
        return noLot;
    }

    public void setNoLot(String noLot) {
        this.noLot = noLot;
    }

    public List<TanahRizabPermohonan> gettanahRizabList() {
        return tanahRizabList;
    }

    public void setTanahRizabPermohonanList(List<TanahRizabPermohonan> tanahRizabList) {
        this.tanahRizabList = tanahRizabList;
    }

    public void setBandarPekanMukim(KodBandarPekanMukim bandarPekanMukim) {
        this.bandarPekanMukim = bandarPekanMukim;
    }

    public KodBandarPekanMukim getBandarPekanMukim() {
        return bandarPekanMukim;
    }

    public KodDaerah getDaerah() {
        return daerah;
    }

    public void setDaerah(KodDaerah daerah) {
        this.daerah = daerah;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public KodCawangan getCawangan() {
        return cawangan;
    }

    public void setCawangan(KodCawangan cawangan) {
        this.cawangan = cawangan;
    }

    public TanahRizabPermohonan getTanahrizab() {
        return tanahrizabpermohonan;
    }

    public void setTanahrizab(TanahRizabPermohonan tanahrizabpermohonan) {
        this.tanahrizabpermohonan = tanahrizabpermohonan;
    }

    public String getCatatan() {
        return catatan;
    }

    public void setCatatan(String catatan) {
        this.catatan = catatan;
    }

    public String getNoLitho() {
        return noLitho;
    }

    public void setNoLitho(String noLitho) {
        this.noLitho = noLitho;
    }

    public String getNoWarta() {
        return noWarta;
    }

    public void setNoWarta(String noWarta) {
        this.noWarta = noWarta;
    }

    public KodRizab getRizab() {
        return rizab;
    }

    public void setRizab(KodRizab rizab) {
        this.rizab = rizab;
    }

    public String getTarikhWarta() {
        return tarikhWarta;
    }

    public void setTarikhWarta(String tarikhWarta) {
        this.tarikhWarta = tarikhWarta;
    }

    public Hakmilik getHakmilik() {
        return hakmilik;
    }

    public void setHakmilik(Hakmilik hakmilik) {
        this.hakmilik = hakmilik;
    }

    public KodHakmilik getKodHakmilik() {
        return kodHakmilik;
    }

    public void setKodHakmilik(KodHakmilik kodHakmilik) {
        this.kodHakmilik = kodHakmilik;
    }

    public TanahRizabPermohonan getTanahrizabpermohonan() {
        return tanahrizabpermohonan;
    }

    public void setTanahrizabpermohonan(TanahRizabPermohonan tanahrizabpermohonan) {
        this.tanahrizabpermohonan = tanahrizabpermohonan;
    }

    public String getNoHakmilik() {
        return noHakmilik;
    }

    public void setNoHakmilik(String noHakmilik) {
        this.noHakmilik = noHakmilik;
    }

    public String getNoLPS() {
        return noLPS;
    }

    public void setNoLPS(String noLPS) {
        this.noLPS = noLPS;
    }

    public HakmilikPermohonan getHakmilikPermohonan() {
        return hakmilikPermohonan;
    }

    public void setHakmilikPermohonan(HakmilikPermohonan hakmilikPermohonan) {
        this.hakmilikPermohonan = hakmilikPermohonan;
    }

    public List<HakmilikPermohonan> getHakmilikPermohonanList() {
        return hakmilikPermohonanList;
    }

    public void setHakmilikPermohonanList(List<HakmilikPermohonan> hakmilikPermohonanList) {
        this.hakmilikPermohonanList = hakmilikPermohonanList;
    }

    public String getIdHakmilik() {
        return idHakmilik;
    }

    public void setIdHakmilik(String idHakmilik) {
        this.idHakmilik = idHakmilik;
    }

    public String getIdPermohonanSebelum() {
        return idPermohonanSebelum;
    }

    public void setIdPermohonanSebelum(String idPermohonanSebelum) {
        this.idPermohonanSebelum = idPermohonanSebelum;
    }

    public Long getIdTanahRizabPermohonan() {
        return idTanahRizabPermohonan;
    }

    public void setIdTanahRizabPermohonan(Long idTanahRizabPermohonan) {
        this.idTanahRizabPermohonan = idTanahRizabPermohonan;
    }

    public PermohonanLaporanPelan getPermohonanLaporanPelan() {
        return permohonanLaporanPelan;
    }

    public void setPermohonanLaporanPelan(PermohonanLaporanPelan permohonanLaporanPelan) {
        this.permohonanLaporanPelan = permohonanLaporanPelan;
    }

    public List<Permohonan> getPermohonanSebelumList() {
        return permohonanSebelumList;
    }

    public void setPermohonanSebelumList(List<Permohonan> permohonanSebelumList) {
        this.permohonanSebelumList = permohonanSebelumList;
    }

    public List<Permohonan> getPermohonanTerdahuluList() {
        return permohonanTerdahuluList;
    }

    public void setPermohonanTerdahuluList(List<Permohonan> permohonanTerdahuluList) {
        this.permohonanTerdahuluList = permohonanTerdahuluList;
    }

    public List<TanahRizabPermohonan> getTanahRizabList() {
        return tanahRizabList;
    }

    public void setTanahRizabList(List<TanahRizabPermohonan> tanahRizabList) {
        this.tanahRizabList = tanahRizabList;
    }

    public String getProjekKerajaan() {
        return projekKerajaan;
    }

    public void setProjekKerajaan(String projekKerajaan) {
        this.projekKerajaan = projekKerajaan;
    }

    public String getLokasi() {
        return lokasi;
    }

    public void setLokasi(String lokasi) {
        this.lokasi = lokasi;
    }

    public boolean isP() {
        return p;
    }

    public void setP(boolean p) {
        this.p = p;
    }

    public String getIdtanahrizabPermohonan() {
        return idtanahrizabPermohonan;
    }

    public void setIdtanahrizabPermohonan(String idtanahrizabPermohonan) {
        this.idtanahrizabPermohonan = idtanahrizabPermohonan;
    }

    public String getIdHakmilikPermohonan() {
        return idHakmilikPermohonan;
    }

    public void setIdHakmilikPermohonan(String idHakmilikPermohonan) {
        this.idHakmilikPermohonan = idHakmilikPermohonan;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId2() {
        return id2;
    }

    public void setId2(String id2) {
        this.id2 = id2;
    }

    public String getIdPermohonan() {
        return idPermohonan;
    }

    public void setIdPermohonan(String idPermohonan) {
        this.idPermohonan = idPermohonan;
    }

    public Pemohon getPemohon() {
        return pemohon;
    }

    public void setPemohon(Pemohon pemohon) {
        this.pemohon = pemohon;
    }

    public Permohonan getPrmhnn() {
        return prmhnn;
    }

    public void setPrmhnn(Permohonan prmhnn) {
        this.prmhnn = prmhnn;
    }

    public boolean isSimpan() {
        return simpan;
    }

    public void setSimpan(boolean simpan) {
        this.simpan = simpan;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getStageId() {
        return stageId;
    }

    public void setStageId(String stageId) {
        this.stageId = stageId;
    }

    public Pengguna getPeng() {
        return peng;
    }

    public void setPeng(Pengguna peng) {
        this.peng = peng;
    }

    public List<KodSeksyen> getKodSeksyenList() {
        return kodSeksyenList;
    }

    public void setKodSeksyenList(List<KodSeksyen> kodSeksyenList) {
        this.kodSeksyenList = kodSeksyenList;
    }

    public String getRizab_melayu() {
        return rizab_melayu;
    }

    public void setRizab_melayu(String rizab_melayu) {
        this.rizab_melayu = rizab_melayu;
    }

    public String getGsa() {
        return gsa;
    }

    public void setGsa(String gsa) {
        this.gsa = gsa;
    }

    public String getHutan() {
        return hutan;
    }

    public void setHutan(String hutan) {
        this.hutan = hutan;
    }

    public String getLain() {
        return lain;
    }

    public void setLain(String lain) {
        this.lain = lain;
    }

    public String getPbt() {
        return pbt;
    }

    public void setPbt(String pbt) {
        this.pbt = pbt;
    }

    public PermohonanLaporanKawasan getPermohonanLaporanKWS() {
        return permohonanLaporanKWS;
    }

    public void setPermohonanLaporanKWS(PermohonanLaporanKawasan permohonanLaporanKWS) {
        this.permohonanLaporanKWS = permohonanLaporanKWS;
    }

    public List<PermohonanLaporanKawasan> getPermohonanLaporKSWList() {
        return permohonanLaporKSWList;
    }

    public void setPermohonanLaporKSWList(List<PermohonanLaporanKawasan> permohonanLaporKSWList) {
        this.permohonanLaporKSWList = permohonanLaporKSWList;
    }

    public String getCatatanLain() {
        return catatanLain;
    }

    public void setCatatanLain(String catatanLain) {
        this.catatanLain = catatanLain;
    }

    public String getKodT() {
        return kodT;
    }

    public void setKodT(String kodT) {
        this.kodT = kodT;
    }

    public char getKodP() {
        return kodP;
    }

    public void setKodP(char kodP) {
        this.kodP = kodP;
    }

    public String getKodPemilikan() {
        return kodPemilikan;
    }

    public void setKodPemilikan(String kodPemilikan) {
        this.kodPemilikan = kodPemilikan;
    }

    public PermohonanManual getPermohonanManual() {
        return permohonanManual;
    }

    public void setPermohonanManual(PermohonanManual permohonanManual) {
        this.permohonanManual = permohonanManual;
    }

    public String getNoFail() {
        return noFail;
    }

    public void setNoFail(String noFail) {
        this.noFail = noFail;
    }

    public List<PermohonanManual> getPermohonanManualList() {
        return permohonanManualList;
    }

    public void setPermohonanManualList(List<PermohonanManual> permohonanManualList) {
        this.permohonanManualList = permohonanManualList;
    }

    public List<PermohonanLaporanKawasan> getSenaraiLaporanKawasan() {
        return senaraiLaporanKawasan;
    }

    public void setSenaraiLaporanKawasan(List<PermohonanLaporanKawasan> senaraiLaporanKawasan) {
        this.senaraiLaporanKawasan = senaraiLaporanKawasan;
    }

    public String getKodRizab() {
        return kodRizab;
    }

    public void setKodRizab(String kodRizab) {
        this.kodRizab = kodRizab;
    }

    public String getAddnoPelanWarta() {
        return addnoPelanWarta;
    }

    public void setAddnoPelanWarta(String addnoPelanWarta) {
        this.addnoPelanWarta = addnoPelanWarta;
    }

    public String getAddnoWarta() {
        return addnoWarta;
    }

    public void setAddnoWarta(String addnoWarta) {
        this.addnoWarta = addnoWarta;
    }

    public String getAddtarikhWarta() {
        return addtarikhWarta;
    }

    public void setAddtarikhWarta(String addtarikhWarta) {
        this.addtarikhWarta = addtarikhWarta;
    }

    public boolean isJenisRizab() {
        return jenisRizab;
    }

    public void setJenisRizab(boolean jenisRizab) {
        this.jenisRizab = jenisRizab;
    }

    public PermohonanLaporanUlasan getPermohonanLaporanUlasan() {
        return permohonanLaporanUlasan;
    }

    public void setPermohonanLaporanUlasan(PermohonanLaporanUlasan permohonanLaporanUlasan) {
        this.permohonanLaporanUlasan = permohonanLaporanUlasan;
    }

    public String getUlasan() {
        return ulasan;
    }

    public void setUlasan(String ulasan) {
        this.ulasan = ulasan;
    }

    public boolean isEdit() {
        return edit;
    }

    public void setEdit(boolean edit) {
        this.edit = edit;
    }

    public String getKodPString() {
        return kodPString;
    }

    public void setKodPString(String kodPString) {
        this.kodPString = kodPString;
    }

    public KodUOM getKodUL() {
        return kodUL;
    }

    public void setKodUL(KodUOM kodUL) {
        this.kodUL = kodUL;
    }

    public String getKodD() {
        return kodD;
    }

    public void setKodD(String kodD) {
        this.kodD = kodD;
    }

    public String getKodPar() {
        return kodPar;
    }

    public void setKodPar(String kodPar) {
        this.kodPar = kodPar;
    }

    public String getNegeri() {
        return negeri;
    }

    public void setNegeri(String negeri) {
        this.negeri = negeri;
    }

    public List<HakmilikPermohonan> getHakmilikPermohonanList_ref() {
        return hakmilikPermohonanList_ref;
    }

    public void setHakmilikPermohonanList_ref(List<HakmilikPermohonan> hakmilikPermohonanList_ref) {
        this.hakmilikPermohonanList_ref = hakmilikPermohonanList_ref;
    }
}
