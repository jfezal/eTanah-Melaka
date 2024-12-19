/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.stripes.pelupusan;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.dao.HakmilikPermohonanDAO;
import etanah.dao.KodDokumenDAO;
import etanah.dao.KodKategoriTanahDAO;
import etanah.dao.KodKeputusanDAO;
import etanah.model.FasaPermohonan;
import java.util.ArrayList;
import java.util.List;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;
import org.apache.commons.lang.StringUtils;
import java.math.BigDecimal;
import etanah.service.BPelService;
import oracle.bpel.services.workflow.task.model.Task;
import etanah.model.HakmilikPermohonan;
import etanah.model.InfoAudit;
import etanah.model.KodHakmilik;
import etanah.model.KodItemPermit;
import etanah.model.KodKategoriTanah;
import etanah.model.KodKegunaanTanah;
import etanah.model.KodKeputusan;
import etanah.model.KodSekatanKepentingan;
import etanah.model.KodSyaratNyata;
import etanah.model.KodUOM;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.model.PermohonanKelompok;
import etanah.model.PermohonanKertas;
import etanah.model.PermohonanPermitItem;
import etanah.model.PermohonanTuntutanKos;
import etanah.service.CalcTaxPelupusan;
import javax.servlet.http.HttpSession;
import org.hibernate.Session;
import etanah.service.PelupusanService;
import etanah.view.etanahActionBeanContext;
import etanah.view.stripes.pelupusan.disClass.DisHakmilikPermohonan;
import etanah.view.stripes.pelupusan.disClass.DisLaporanTanahService;
import etanah.view.stripes.pelupusan.disClass.DisPermohonanBahanBatu;
import org.apache.log4j.Logger;
import java.text.ParseException;
import etanah.view.stripes.pelupusan.disClass.DisPermohonanPage;
import etanah.view.stripes.pelupusan.disClass.DisRekodKeputusanController;
import etanah.view.stripes.pelupusan.disClass.DisSyaratSekatan;
import net.sourceforge.stripes.action.StreamingResolution;

/**
 *
 * @author Afham
 */
@UrlBinding("/pelupusan/rekod_keputusanMMKV2")
public class RekodKeputusanMMKV2ActionBean extends AbleActionBean {

    @Inject
    DisLaporanTanahService disLaporanTanahService;
    @Inject
    KodKeputusanDAO kodKeputusanDAO;
    @Inject
    KodDokumenDAO kodDokumenDAO;
    @Inject
    PelupusanService pelupusanService;
    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;
    @Inject
    etanah.Configuration conf;
    @Inject
    HakmilikPermohonanDAO hakmillikPermohonanDAO;
    @Inject
    KodKategoriTanahDAO kodKategoriTanahDAO;
    @Inject
    CalcTaxPelupusan calcTax;
    private String idPermohonan;
    private String stageId;
    private String idHakmilik;
    private String kodNegeri;
    private String keputusan;
    private String kpsnMohonFasa;
    private String kod;
    private String syaratNyata;
    private String kodSktn;
    private String syaratNyata1;
    private String indexSyarat;
    private String kodU;
    private String kodHakmilik;
    private String keteranganKadarPremium;
    private String kodGunaTanah;
    private String index;
    private String kodSekatanKepentingan;
    private String kodSyaratNyata;
    private String sekatKpntgn2;
    private String syaratNyata2;
    private String keg;
    private String catatan;
    private boolean kelompok;
    private boolean sejKelompok;
    private Permohonan permohonan;
    private BigDecimal amnt;
    private HakmilikPermohonan hakmilikPermohonan;
    private PermohonanKertas permohonanKertas;
    private PermohonanTuntutanKos permohonanTuntutanKos;
    private PermohonanPermitItem permohonanPermitItem ;
    etanahActionBeanContext ctx;
    private DisRekodKeputusanController disRekodKeputusanController;
    private DisSyaratSekatan disSyaratSekatan;
    private DisPermohonanBahanBatu disPermohonanBahanBatu;
    private List<HakmilikPermohonan> senaraiHakmilikPermohonan;
    private List<HakmilikPermohonan> senaraiHakmilikTiadaKeputusan;
    private List<HakmilikPermohonan> senaraiHakmilikTolak;
    private List<HakmilikPermohonan> senaraiHakmilikLulus;
    private List<KodKegunaanTanah> senaraiKodKegunaanTanah;
    private List<KodKegunaanTanah> listKodGunaTanahByKatTanah;
    private List<String> senaraikodKadarPremium;
    private List hakmilikPermohonanList;
    private List<KodSyaratNyata> listKodSyaratNyata;
    private List<KodSekatanKepentingan> listKodSekatan;
    private List<PermohonanKelompok> senaraiKelompok;
    private List<PermohonanKelompok> sejarahKelompok;
    private static final Logger LOG = Logger.getLogger(RekodKeputusanMMKV2ActionBean.class);
    private static final boolean IS_LOG_DEBUG = LOG.isDebugEnabled();
    private Pengguna peng;
    private int sizeTiadaKeputusan;
    private int sizeLulus;
    private int sizeTolak;
    private String katTanahPilihan;
    private FasaPermohonan mohonFasa;

    @DefaultHandler
    public Resolution viewOnlyRekodKeputusanPT() {
        HttpSession httpSession = getContext().getRequest().getSession();
        disRekodKeputusanController = new DisRekodKeputusanController();
        disRekodKeputusanController = disRekodKeputusanController.viewOnlyRekodKeputusanPT();
        httpSession.setAttribute("disRekodKeputusanController", disRekodKeputusanController);
        return new JSP(DisPermohonanPage.getRK_MMK_V2_MAIN_PAGE()).addParameter("tab", "true");
    }

    public Resolution showFormBeforeMesyuarat() {
        HttpSession httpSession = getContext().getRequest().getSession();
        disRekodKeputusanController = new DisRekodKeputusanController();
        disRekodKeputusanController = disRekodKeputusanController.showFormBeforeMesyuarat();
        httpSession.setAttribute("disRekodKeputusanController", disRekodKeputusanController);
        httpSession.setAttribute("disShowFormBeforeMesyuarat", disRekodKeputusanController);
        return new JSP(DisPermohonanPage.getRK_MMK_V2_MAIN_PAGE()).addParameter("tab", "true");
    }

    public Resolution viewOnlyRekodKeputusan() {
        HttpSession httpSession = getContext().getRequest().getSession();
        disRekodKeputusanController = new DisRekodKeputusanController();
        disRekodKeputusanController = disRekodKeputusanController.viewOnlyRekodKeputusan();
        httpSession.setAttribute("disRekodKeputusanController", disRekodKeputusanController);
        return new JSP(DisPermohonanPage.getRK_MMK_V2_MAIN_PAGE()).addParameter("tab", "true");
    }

    public Resolution search() {
        index = getContext().getRequest().getParameter("index");
        return new JSP("pelupusan/common/searchSekatanNyata.jsp").addParameter("popup", "true");
    }

    public Resolution showFormCariKodSekatan() {
        index = getContext().getRequest().getParameter("index");
        return new JSP("pelupusan/common/searchSekatanNyataKpntngn.jsp").addParameter("popup", "true");
    }

    public Resolution cariKodSyaratNyata() {
        Pengguna peng = (Pengguna) context.getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        String kc = new String();
        String kategoriTanah = new String();
        idHakmilik = getContext().getRequest().getParameter("idHakmilik");
        HakmilikPermohonan hakmilikPermohonanSave = new HakmilikPermohonan();
        if (!StringUtils.isEmpty(idHakmilik)) {
            hakmilikPermohonanSave = (HakmilikPermohonan) disLaporanTanahService.findObject(hakmilikPermohonanSave, new String[]{idHakmilik}, 2);
        }
        if (hakmilikPermohonanSave != null) {
            if (hakmilikPermohonanSave.getPermohonan().getKodUrusan().getKod().equals("PBMT")) {
                if (hakmilikPermohonanSave.getKodHakmilik() != null) {
                    if (hakmilikPermohonanSave.getKodHakmilik().getKod().equals("PN") || hakmilikPermohonanSave.getKodHakmilik().getKod().equals("GRN") || hakmilikPermohonanSave.getKodHakmilik().getKod().equals("HSD")) {
                        kc = disLaporanTanahService.getKodCawanganDAO().findById("00").getKod();
                    } else {
                        kc = peng.getKodCawangan().getKod();
                    }
                }
            } else {
                kc = peng.getKodCawangan().getKod();
            }
            if(hakmilikPermohonanSave.getHakmilik() != null){
                kategoriTanah = hakmilikPermohonanSave.getHakmilik().getKategoriTanah() != null ? hakmilikPermohonanSave.getHakmilik().getKategoriTanah().getKod() : new String();
            }else{
                kategoriTanah = hakmilikPermohonanSave.getKategoriTanahBaru() != null ? hakmilikPermohonanSave.getKategoriTanahBaru().getKod() : new String();
            }
        } else {
            kc = peng.getKodCawangan().getKod();
        }
        if (disSyaratSekatan != null) {
            if (!StringUtils.isEmpty(disSyaratSekatan.getKodSyaratNyata())) {
                disSyaratSekatan.setListKodSyaratNyata(disLaporanTanahService.getPlpservice().searchKodSyaratNyataNew(disSyaratSekatan.getKodSyaratNyata(), kc, kategoriTanah, disSyaratSekatan.getSyaratNyata2()));
                LOG.info("listKodSyaratNyata.size : " + disSyaratSekatan.getListKodSyaratNyata().size());
                if (disSyaratSekatan.getListKodSyaratNyata().size() < 1) {
                    addSimpleError("Kod Syarat Nyata Tidak Sah");
                }
            } else {
                disSyaratSekatan.setListKodSyaratNyata(disLaporanTanahService.getPlpservice().searchKodSyaratNyataNew("%", kc, kategoriTanah, disSyaratSekatan.getSyaratNyata2()));
                if (disSyaratSekatan.getListKodSyaratNyata().size() < 1) {
                    addSimpleError("Kod Syarat Nyata Tidak Sah");
                }
            }
        } else {
            disSyaratSekatan = new DisSyaratSekatan();
            disSyaratSekatan.setListKodSyaratNyata(disLaporanTanahService.getPlpservice().searchKodSyaratNyataNew("%", kc, kategoriTanah, disSyaratSekatan.getSyaratNyata2()));
            if (disSyaratSekatan.getListKodSyaratNyata().size() < 1) {
                addSimpleError("Tiada Maklumat pada Table Syarat Nyata");
            }
        }

        return new JSP(DisPermohonanPage.getRK_MMK_SYARATNYATA_PAGE()).addParameter("popup", "true");
    }

    public Resolution cariKodSekatan() {
        Pengguna peng = (Pengguna) context.getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        String kc = new String();
        idHakmilik = getContext().getRequest().getParameter("idHakmilik");
        HakmilikPermohonan hakmilikPermohonanSave = new HakmilikPermohonan();
        if (!StringUtils.isEmpty(idHakmilik)) {
            hakmilikPermohonanSave = (HakmilikPermohonan) disLaporanTanahService.findObject(hakmilikPermohonanSave, new String[]{idHakmilik}, 2);
        }
        if (hakmilikPermohonanSave != null) {
            if (hakmilikPermohonanSave.getPermohonan().getKodUrusan().getKod().equals("PBMT")) {
                if (hakmilikPermohonanSave.getKodHakmilik() != null) {
                    if (hakmilikPermohonanSave.getKodHakmilik().getKod().equals("PN") || hakmilikPermohonanSave.getKodHakmilik().getKod().equals("GRN") || hakmilikPermohonanSave.getKodHakmilik().getKod().equals("HSD")) {
                        kc = disLaporanTanahService.getKodCawanganDAO().findById("00").getKod();
                    } else {
                        kc = peng.getKodCawangan().getKod();
                    }
                }
            } else {
                kc = peng.getKodCawangan().getKod();
            }
        } else {
            kc = peng.getKodCawangan().getKod();
        }
        if (disSyaratSekatan != null) {
            if (!StringUtils.isEmpty(disSyaratSekatan.getKodSekatanKepentingan())) {
                disSyaratSekatan.setListKodSekatan(disLaporanTanahService.getPlpservice().searchKodSekatan(disSyaratSekatan.getKodSekatanKepentingan(), kc, disSyaratSekatan.getSekatKpntgn2()));
                LOG.info("listKodSyaratNyata.size : " + disSyaratSekatan.getListKodSekatan().size());
                if (disSyaratSekatan.getListKodSekatan().size() < 1) {
                    addSimpleError("Kod Syarat Nyata Tidak Sah");
                }
            } else {
                disSyaratSekatan.setListKodSekatan(disLaporanTanahService.getPlpservice().searchKodSekatan("%", kc, disSyaratSekatan.getSekatKpntgn2()));
                if (disSyaratSekatan.getListKodSekatan().size() < 1) {
                    addSimpleError("Kod Syarat Nyata Tidak Sah");
                }
            }
        } else {
            disSyaratSekatan = new DisSyaratSekatan();
            disSyaratSekatan.setListKodSekatan(disLaporanTanahService.getPlpservice().searchKodSekatan("%", kc, disSyaratSekatan.getSekatKpntgn2()));
            if (disSyaratSekatan.getListKodSekatan().size() < 1) {
                addSimpleError("Tiada Maklumat pada Table Syarat Nyata");
            }
        }

        return new JSP(DisPermohonanPage.getRK_MMK_SEKATAN_PAGE()).addParameter("popup", "true");
    }

    public Resolution kiraCukai() {
        String result = "";
        String kodTanah = (String) getContext().getRequest().getParameter("kodTanah");
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        idHakmilik = ctx.getRequest().getParameter("idHakmilik");
        HakmilikPermohonan hp = new HakmilikPermohonan();
        if (!StringUtils.isEmpty(idHakmilik)) {
            hp = hakmillikPermohonanDAO.findById(Long.valueOf(idHakmilik));
        }

        if (hp != null) {

            if (hp.getLuasDiluluskan() != null && hp.getLuasDiluluskan().longValue() < 0) {
                result = "-1";
            } else {
                result = String.valueOf(calcTax.calculate(kodTanah, String.valueOf(hp.getBandarPekanMukimBaru().getKod()), "H", new BigDecimal(1), hp, null));
            }

        }
        return new StreamingResolution("text/plain", result);
    }

    public String stageId(String taskId, Pengguna pguna) {
        BPelService service = new BPelService();
        String stageId = null;
        if (StringUtils.isNotBlank(taskId)) {
            Task t = null;
            t = service.getTaskFromBPel(taskId, pguna);
            stageId = t.getSystemAttributes().getStage();
        }
//        else {
//            stageId = "g_charting_keputusan";
//        }
        return stageId;
    }

    @Before(stages = {LifecycleStage.BindingAndValidation}, on = {"!reload", "!simpanKeputusan", "!simpanSyarat"})
    public void rehydrate() {
        LOG.info("In rehydrate");
        ctx = (etanahActionBeanContext) getContext();
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        String taskId = (String) getContext().getRequest().getSession().getAttribute("taskId");
        Pengguna pguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        stageId = stageId(taskId, pguna);
        kodNegeri = disLaporanTanahService.getConf().getProperty("kodNegeri");
        idHakmilik = ctx.getRequest().getParameter("idHakmilik");
        peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);

        /**
         * Senarai Hakmilik
         */
        if (idPermohonan != null) {
            senaraiKelompok = disLaporanTanahService.getPelupusanService().findMohonKelompokByIdMohonInduk(idPermohonan);
            sejarahKelompok = disLaporanTanahService.getPelupusanService().findMohonKelompokByIdMohon(idPermohonan); // Utk kesenangan tgk data
            if (senaraiKelompok.size() > 0) {
                kelompok = true;
            } else {
                kelompok = false;
            }
            if (sejarahKelompok.size() > 0) {
                sejKelompok = true;
            } else {
                sejKelompok = false;
            }
            permohonan = new Permohonan();
            permohonan = (Permohonan) disLaporanTanahService.findObject(permohonan, new String[]{idPermohonan}, 0);
            keputusan = permohonan.getKeputusan() != null ? permohonan.getKeputusan().getKod() : null;

            senaraiHakmilikPermohonan = new ArrayList<HakmilikPermohonan>();

            if (permohonan.getKodUrusan().getKod().equals("PJLB") && permohonan.getPermohonanSebelum() != null) {
//              senaraiHakmilikPermohonan = permohonan.getPermohonanSebelum().getSenaraiHakmilik();
                senaraiHakmilikPermohonan = permohonan.getSenaraiHakmilik();
            } else if (permohonan.getKodUrusan().getKod().equals("PHLP")) {
                senaraiHakmilikPermohonan = disLaporanTanahService.getPlpservice().getHakmilikPermohonanListByCatatanNIdMohon("L", idPermohonan);
            } else if (permohonan.getKodUrusan().getKod().equals("PTGSA")) {
//                senaraiHakmilikPermohonan = disLaporanTanahService.getPlpservice().getHakmilikPermohonanListByCatatanNIdMohon(new String(), idPermohonan);
                senaraiHakmilikPermohonan = permohonan.getSenaraiHakmilik();
            } else {
                senaraiHakmilikPermohonan = permohonan.getSenaraiHakmilik();
            }

            if (senaraiHakmilikPermohonan.size() > 0) {
                senaraiHakmilikTiadaKeputusan = new ArrayList<HakmilikPermohonan>();
                senaraiHakmilikLulus = new ArrayList<HakmilikPermohonan>();
                senaraiHakmilikTolak = new ArrayList<HakmilikPermohonan>();
                if (kelompok) {
                    if (senaraiKelompok.size() > 0) {
                        for (PermohonanKelompok pk : senaraiKelompok) {
                            List<HakmilikPermohonan> senaraiHakmilikPermohonanTemp = new ArrayList<HakmilikPermohonan>();
                            List<HakmilikPermohonan> senaraiHakmilikPermohonanTempLulus = new ArrayList<HakmilikPermohonan>();
                            List<HakmilikPermohonan> senaraiHakmilikPermohonanTempTolak = new ArrayList<HakmilikPermohonan>();
                            senaraiHakmilikPermohonanTemp = disLaporanTanahService.getPelupusanService().getHakmilikPermohonanListByTiadaKeputusan(pk.getPermohonan().getIdPermohonan());
                            senaraiHakmilikPermohonanTempLulus = disLaporanTanahService.getPelupusanService().getHakmilikPermohonanListByLulus(pk.getPermohonan().getIdPermohonan());
                            senaraiHakmilikPermohonanTempTolak = disLaporanTanahService.getPelupusanService().getHakmilikPermohonanListByTolak(pk.getPermohonan().getIdPermohonan());
                            if (senaraiHakmilikPermohonanTemp.size() > 0) {
                                for (HakmilikPermohonan hp : senaraiHakmilikPermohonanTemp) {
                                    senaraiHakmilikTiadaKeputusan.add(hp);
                                }
                            }
                            if (senaraiHakmilikPermohonanTempLulus.size() > 0) {
                                for (HakmilikPermohonan hp : senaraiHakmilikPermohonanTempLulus) {
                                    senaraiHakmilikLulus.add(hp);
                                }
                            }
                            if (senaraiHakmilikPermohonanTempTolak.size() > 0) {
                                for (HakmilikPermohonan hp : senaraiHakmilikPermohonanTempTolak) {
                                    senaraiHakmilikTolak.add(hp);
                                }
                            }
                        }
                        sizeTiadaKeputusan = senaraiHakmilikTiadaKeputusan.size() > 0 ? senaraiHakmilikTiadaKeputusan.size() : 0;
                        sizeLulus = senaraiHakmilikLulus.size() > 0 ? senaraiHakmilikLulus.size() : 0;
                        sizeTolak = senaraiHakmilikTolak.size() > 0 ? senaraiHakmilikTolak.size() : 0;
                        if (senaraiKelompok.get(0).getJenisKelopok().equals("K")) {
                            kelompok = true;
                            if (sizeLulus > 0) {
                                copyDataKelompokToIndividu();
                            }
                        }
                    }
                } else {
                    senaraiHakmilikTiadaKeputusan = disLaporanTanahService.getPelupusanService().getHakmilikPermohonanListByTiadaKeputusan(idPermohonan);
                    sizeTiadaKeputusan = senaraiHakmilikTiadaKeputusan.size() > 0 ? senaraiHakmilikTiadaKeputusan.size() : 0;
                    senaraiHakmilikLulus = disLaporanTanahService.getPelupusanService().getHakmilikPermohonanListByLulus(idPermohonan);
                    sizeLulus = senaraiHakmilikLulus.size() > 0 ? senaraiHakmilikLulus.size() : 0;
                    senaraiHakmilikTolak = disLaporanTanahService.getPelupusanService().getHakmilikPermohonanListByTolak(idPermohonan);
                    sizeTolak = senaraiHakmilikTolak.size() > 0 ? senaraiHakmilikTolak.size() : 0;
                }
            }
            String kodDok = kertasDok(permohonan.getKodUrusan().getKod());
            permohonanKertas = new PermohonanKertas();
            mohonFasa = new FasaPermohonan();
            if (sejKelompok) {
                String idPermohonanTemp = sejarahKelompok.get(0).getPermohonanInduk().getIdPermohonan();
                permohonanKertas = (PermohonanKertas) disLaporanTanahService.findObject(permohonanKertas, new String[]{idPermohonanTemp, kodDok}, 0);
                mohonFasa = (FasaPermohonan) disLaporanTanahService.findObject(mohonFasa, new String[]{idPermohonanTemp, "rekod_keputusan_MMK"}, 0);
            } else {
                permohonanKertas = (PermohonanKertas) disLaporanTanahService.findObject(permohonanKertas, new String[]{idPermohonan, kodDok}, 0);
                mohonFasa = (FasaPermohonan) disLaporanTanahService.findObject(mohonFasa, new String[]{idPermohonan, "rekod_keputusan_MMK"}, 0);
            }
            senaraikodKadarPremium = disLaporanTanahService.getPlpservice().getSenaraiKodKadarPremiumDistinctNama();
        }
        if (StringUtils.isNotEmpty(idHakmilik)) {
            hakmilikPermohonan = hakmillikPermohonanDAO.findById(Long.valueOf(idHakmilik));
            if (hakmilikPermohonan != null) {
                if (hakmilikPermohonan.getKodKegunaanTanah() != null) {
                    senaraiKodKegunaanTanah = disLaporanTanahService.getPelupusanService().getSenaraiKegunaanTanah(hakmilikPermohonan.getKategoriTanahBaru().getKod());
                }
                if (hakmilikPermohonan.getKadarPremium() != null) {
                    amnt = hakmilikPermohonan.getKadarPremium();
                } else {
                    amnt = checkPermohonanTuntutanKos(permohonan);
                }
            }

        }

    }

    public Resolution openFrame() {
        ctx = (etanahActionBeanContext) getContext();
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        Pengguna pguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        idHakmilik = ctx.getRequest().getParameter("idHakmilik");
        String forwardJSP = new String();
        String type = ctx.getRequest().getParameter("type");
        kodNegeri = disLaporanTanahService.getConf().getProperty("kodNegeri");
        permohonan = new Permohonan();
        permohonan = (Permohonan) disLaporanTanahService.findObject(permohonan, new String[]{idPermohonan}, 0);
        hakmilikPermohonan = new HakmilikPermohonan();
        senaraiKelompok = disLaporanTanahService.getPelupusanService().findMohonKelompokByIdMohonInduk(idPermohonan);
        if (senaraiKelompok.size() > 0) {
            kelompok = true;
        } else {
            kelompok = false;
        }

        if (senaraiHakmilikPermohonan.size() > 0) {
            senaraiHakmilikTiadaKeputusan = new ArrayList<HakmilikPermohonan>();
            senaraiHakmilikLulus = new ArrayList<HakmilikPermohonan>();
            senaraiHakmilikTolak = new ArrayList<HakmilikPermohonan>();
            if (kelompok) {
                if (senaraiKelompok.size() > 0) {
                    for (PermohonanKelompok pk : senaraiKelompok) {
                        List<HakmilikPermohonan> senaraiHakmilikPermohonanTemp = new ArrayList<HakmilikPermohonan>();
                        List<HakmilikPermohonan> senaraiHakmilikPermohonanTempLulus = new ArrayList<HakmilikPermohonan>();
                        List<HakmilikPermohonan> senaraiHakmilikPermohonanTempTolak = new ArrayList<HakmilikPermohonan>();
                        senaraiHakmilikPermohonanTemp = disLaporanTanahService.getPelupusanService().getHakmilikPermohonanListByTiadaKeputusan(pk.getPermohonan().getIdPermohonan());
                        senaraiHakmilikPermohonanTempLulus = disLaporanTanahService.getPelupusanService().getHakmilikPermohonanListByLulus(pk.getPermohonan().getIdPermohonan());
                        senaraiHakmilikPermohonanTempTolak = disLaporanTanahService.getPelupusanService().getHakmilikPermohonanListByTolak(pk.getPermohonan().getIdPermohonan());
                        if (senaraiHakmilikPermohonanTemp.size() > 0) {
                            for (HakmilikPermohonan hp : senaraiHakmilikPermohonanTemp) {
                                senaraiHakmilikTiadaKeputusan.add(hp);
                            }
                        }
                        if (senaraiHakmilikPermohonanTempLulus.size() > 0) {
                            for (HakmilikPermohonan hp : senaraiHakmilikPermohonanTempLulus) {
                                senaraiHakmilikLulus.add(hp);
                            }
                        }
                        if (senaraiHakmilikPermohonanTempTolak.size() > 0) {
                            for (HakmilikPermohonan hp : senaraiHakmilikPermohonanTempTolak) {
                                senaraiHakmilikTolak.add(hp);
                            }
                        }
                    }
                }
            } else {
                senaraiHakmilikTiadaKeputusan = disLaporanTanahService.getPelupusanService().getHakmilikPermohonanListByTiadaKeputusan(idPermohonan);
                senaraiHakmilikLulus = disLaporanTanahService.getPelupusanService().getHakmilikPermohonanListByLulus(idPermohonan);
                senaraiHakmilikTolak = disLaporanTanahService.getPelupusanService().getHakmilikPermohonanListByTolak(idPermohonan);
            }
        }

        if (StringUtils.isNotBlank(type)) {
            /*
             * FORWARD TO DIFFERENT POPUP
             */
            forwardJSP = refreshData(type);
        }
        return new JSP(forwardJSP).addParameter("popup", Boolean.TRUE);
    }

    public Resolution editDetails() {
        ctx = (etanahActionBeanContext) getContext();
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        Pengguna pguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        String type = ctx.getRequest().getParameter("type");
        idHakmilik = ctx.getRequest().getParameter("idMH");
        kodNegeri = disLaporanTanahService.getConf().getProperty("kodNegeri");
        permohonan = new Permohonan();
        permohonan = (Permohonan) disLaporanTanahService.findObject(permohonan, new String[]{idPermohonan}, 0);
        senaraiKelompok = disLaporanTanahService.getPelupusanService().findMohonKelompokByIdMohonInduk(idPermohonan);
        if (senaraiKelompok.size() > 0) {
            if (senaraiKelompok.get(0).getJenisKelopok().equals("K")) {
                kelompok = false;
            } else {
                kelompok = false;
            }

        } else {
            kelompok = false;
        }
        hakmilikPermohonan = new HakmilikPermohonan();
        hakmilikPermohonan = hakmillikPermohonanDAO.findById(Long.valueOf(idHakmilik));
        if (hakmilikPermohonan.getKategoriTanahBaru() != null) {
            katTanahPilihan = hakmilikPermohonan.getKategoriTanahBaru().getKod();
            if (hakmilikPermohonan.getKodKegunaanTanah() != null) {
                kodGunaTanah = hakmilikPermohonan.getKodKegunaanTanah().getKod();
            }
            senaraiKodKegunaanTanah = disLaporanTanahService.getPelupusanService().getSenaraiKegunaanTanah(katTanahPilihan);
        }
        if (hakmilikPermohonan.getKadarPremium() != null) {
            amnt = hakmilikPermohonan.getKadarPremium();
        } else {
            amnt = checkPermohonanTuntutanKos(permohonan);
        }
        catatan = new String() ;
        catatan = permohonan.getSebab() ;
        senaraikodKadarPremium = disLaporanTanahService.getPlpservice().getSenaraiKodKadarPremiumDistinctNama();
        if (permohonan.getKodUrusan().getKod().equals("PLPS") || permohonan.getKodUrusan().getKod().equals("PBMT") || permohonan.getKodUrusan().getKod().equals("PJTK") || permohonan.getKodUrusan().getKod().equals("LPSP") || permohonan.getKodUrusan().getKod().equals("PLPTD") || permohonan.getKodUrusan().getKod().equals("PTPBP") || permohonan.getKodUrusan().getKod().equals("RLPS")) {
            permohonanPermitItem = new PermohonanPermitItem();
            if (permohonan.getKodUrusan().getKod().equals("LPSP")) {
                List<PermohonanPermitItem> senaraiPermohonanPermitItem = new ArrayList<PermohonanPermitItem>();
                senaraiPermohonanPermitItem = disLaporanTanahService.getPelupusanService().findPermohonanPermitItemByIdMohonList(idPermohonan);
                keg = new String();
                for (PermohonanPermitItem ppi : senaraiPermohonanPermitItem) {
                    if (ppi.getKodItemPermit().getKod().equals("LN")) {
                        keg = ppi.getKodItemPermit().getKod();

                    }
                }
            } else {
                permohonanPermitItem = (PermohonanPermitItem) disLaporanTanahService.findObject(permohonanPermitItem, new String[]{idPermohonan}, 0);
                if (permohonanPermitItem != null) {
                    if ((permohonanPermitItem != null) && (permohonanPermitItem.getKodItemPermit() != null)) {
                        keg = permohonanPermitItem.getKodItemPermit().getKod();
                    }
                } else {
                    permohonanPermitItem = new PermohonanPermitItem();
                }
            }

        }
        if (type.equals("update")) {
            return new JSP(DisPermohonanPage.getRK_MMK_V2_SYRT_EDIT_PAGE()).addParameter("tab", "true");
        } else {
            return new JSP(DisPermohonanPage.getRK_MMK_V2_SYRT_VIEW_PAGE()).addParameter("tab", "true");
        }

    }

    public Resolution editDetailsData() {
        ctx = (etanahActionBeanContext) getContext();
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        String luas = ctx.getRequest().getParameter("luas");
        String kodKat = ctx.getRequest().getParameter("kodKat");
        idHakmilik = ctx.getRequest().getParameter("idMH");
        kodNegeri = disLaporanTanahService.getConf().getProperty("kodNegeri");
        permohonan = new Permohonan();
        permohonan = (Permohonan) disLaporanTanahService.findObject(permohonan, new String[]{idPermohonan}, 0);
        hakmilikPermohonan = hakmillikPermohonanDAO.findById(Long.valueOf(idHakmilik));
        if (StringUtils.isNotEmpty(luas)) {
            double a = Double.parseDouble(luas);
            BigDecimal l = BigDecimal.valueOf(a);
            hakmilikPermohonan.setLuasDiluluskan(l);
        }
        if (StringUtils.isNotEmpty(kodKat)) {
            hakmilikPermohonan.setKategoriTanahBaru(kodKategoriTanahDAO.findById(kodKat));
            katTanahPilihan = kodKat;
            senaraiKodKegunaanTanah = disLaporanTanahService.getPelupusanService().getSenaraiKegunaanTanah(katTanahPilihan);
        }

        senaraikodKadarPremium = disLaporanTanahService.getPlpservice().getSenaraiKodKadarPremiumDistinctNama();
        return new JSP(DisPermohonanPage.getRK_MMK_V2_SYRT_EDIT_PAGE()).addParameter("tab", "true");

    }

    public String refreshData(String type) {
        String forwardJSP = new String();
        String kodDok = new String();
        int typeNum = type.equals("mMesyuarat") ? 1
                : type.equals("sKelulusan") ? 2
                : type.equals("main") ? 3
                : type.equals("sHakmilik") ? 4
                : type.equals("saveHakmilik") ? 5
                : type.equals("beforeMesyuarat") ? 6
                : 0;

        switch (typeNum) {
            case 1:
                forwardJSP = DisPermohonanPage.getRK_MMK_V2_MM_PAGE();
                kodDok = kertasDok(permohonan.getKodUrusan().getKod());
                permohonanKertas = new PermohonanKertas();
                permohonanKertas = (PermohonanKertas) disLaporanTanahService.findObject(permohonanKertas, new String[]{idPermohonan, kodDok}, 0);
                mohonFasa = (FasaPermohonan) disLaporanTanahService.findObject(mohonFasa, new String[]{idPermohonan, "RekodKeputusanMMK"}, 0);
                if (mohonFasa != null) {
                    kpsnMohonFasa = mohonFasa.getKeputusan() != null ? mohonFasa.getKeputusan().getKod() : new String();
                }
                disRekodKeputusanController = (DisRekodKeputusanController) getContext().getRequest().getSession().getAttribute("disRekodKeputusanController");
                break;
            case 2:
                forwardJSP = DisPermohonanPage.getRK_MMK_V2_SYRT_EDIT_PAGE();
                LOG.debug("Page--->" + forwardJSP);
                break;
            case 3:
                rehydrate();
                disRekodKeputusanController = (DisRekodKeputusanController) getContext().getRequest().getSession().getAttribute("disRekodKeputusanController");
                forwardJSP = DisPermohonanPage.getRK_MMK_V2_MAIN_PAGE();
                break;
            case 4:
                rehydrate();
                disRekodKeputusanController = (DisRekodKeputusanController) getContext().getRequest().getSession().getAttribute("disRekodKeputusanController");
                forwardJSP = DisPermohonanPage.getRK_MMK_V2_SH_PAGE();
                break;
            case 5:
                addSimpleMessage("Data telah berjaya simpan");
                disRekodKeputusanController = (DisRekodKeputusanController) getContext().getRequest().getSession().getAttribute("disRekodKeputusanController");
                forwardJSP = DisPermohonanPage.getRK_MMK_V2_SH_PAGE();
                break;
            case 6:
                rehydrate();
                disRekodKeputusanController = (DisRekodKeputusanController) getContext().getRequest().getSession().getAttribute("disShowFormBeforeMesyuarat");
                forwardJSP = DisPermohonanPage.getRK_MMK_V2_MAIN_PAGE();
                break;
        }
        return forwardJSP;
    }

    public String kertasDok(String urusan) {
        String kodDok = new String();
        int typeUrusan = urusan.equals("BMBT") ? 1
                : urusan.equals("PJBTR") ? 2
                : urusan.equals("PBMT") ? 3
                : urusan.equals("LPSP") ? 4
                : urusan.equals("PPRU") ? 5
                : urusan.equals("PRMP") ? 6
                : urusan.equals("PPTR") ? 7
                : urusan.equals("PBPTG") ? 8
                : urusan.equals("PWGSA") ? 9
                : urusan.equals("PLPS") ? 10
                : urusan.equals("PBRZ") ? 11
                : 0;

        switch (typeUrusan) {
            case 1:
                kodDok = "RMN";
                break;
            case 2:
                kodDok = "RMN";
                break;
            case 3:
                kodDok = "RMN";
                break;
            case 4:
                kodDok = "RMN";
                break;
            case 5:
                kodDok = "RMN";
                break;
            case 6:
                kodDok = "RMN";
                break;
            case 7:
                kodDok = "RMN";
                break;
            case 8:
                kodDok = "RMN";
                break;
            case 9:
                kodDok = "RMN";
                break;
            case 10:
                kodDok = "RMN";
                break;
            case 11:
                kodDok = "RMN";
                break;
            default:
                kodDok = "RMN";
                break ;
        }
        return kodDok;
    }

    public Resolution refreshpage() {

        //END
        return new JSP(refreshData(ctx.getRequest().getParameter("type"))).addParameter("popup", Boolean.TRUE);
    }

    public Resolution reload() {
        return showFormDisplay();
    }

    public Resolution showFormDisplay() {
//        statusPage = new String();
//        display = Boolean.TRUE;
        //Purpose : TO CATER LOADING MULTIPLE HAKMILIK USING DROPDOWN LIST
        rehydrate();
        HttpSession httpSession = getContext().getRequest().getSession();
        disRekodKeputusanController = new DisRekodKeputusanController();
        int typeNum = permohonan.getKodUrusan().getKod().equals("BMBT") ? 1
                : permohonan.getKodUrusan().getKod().equals("PJBTR") ? 2
                : 0;

        switch (typeNum) {
            case 1: // Urusan = PPBB
//                if (stageId.equalsIgnoreCase("laporan_tanah")) {
                disRekodKeputusanController = disRekodKeputusanController.viewOnlyRekodKeputusanPT();
                httpSession.setAttribute("disRekodKeputusanController", disRekodKeputusanController);
//                } 
//                } else {
//                    disLaporanTanahController = disLaporanTanahController.viewOnlyLaporanTanah();
//                    httpSession.setAttribute("disLaporanTanahController", disLaporanTanahController);
//                }
                break;
            case 2: // Urusan = PJBTR
                disRekodKeputusanController = disRekodKeputusanController.viewOnlyRekodKeputusanPT();
                httpSession.setAttribute("disRekodKeputusanController", disRekodKeputusanController);
                break;
            default:
                disRekodKeputusanController = disRekodKeputusanController.viewOnlyRekodKeputusanPT();
                httpSession.setAttribute("disRekodKeputusanController", disRekodKeputusanController);
        }
        return new JSP(DisPermohonanPage.getRK_MAIN_PAGE()).addParameter("tab", Boolean.TRUE);
    }

    public Resolution simpanKeputusan() throws ParseException {
//        rehydrate();
        ctx = (etanahActionBeanContext) getContext();
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        permohonan = disLaporanTanahService.getPermohonanDAO().findById(idPermohonan);
        Permohonan permohonanTemp = new Permohonan();
        permohonanTemp = (Permohonan) disLaporanTanahService.findObject(permohonanTemp, new String[]{idPermohonan}, 0);
        kodNegeri = disLaporanTanahService.getConf().getProperty("kodNegeri");
        String kodDok = kertasDok(permohonanTemp.getKodUrusan().getKod());
        PermohonanKertas permohonanKertasTemp = new PermohonanKertas();
        permohonanKertasTemp = (PermohonanKertas) disLaporanTanahService.findObject(permohonanKertasTemp, new String[]{idPermohonan, kodDok}, 0);
        if (permohonanKertasTemp != null) {
            permohonanKertasTemp.setCawangan(permohonan.getCawangan());
            permohonanKertasTemp.setNomborRujukan(permohonanKertas.getNomborRujukan());
            permohonanKertasTemp.setTarikhSidang(permohonanKertas.getTarikhSidang());
            permohonanKertasTemp.setPenyediaSidang(permohonanKertas.getPenyediaSidang());
            permohonanKertasTemp.setTarikhSahKeputusan(permohonanKertas.getTarikhSahKeputusan());
            permohonanKertasTemp.setInfoAudit(disLaporanTanahService.findAudit(permohonanKertasTemp, "update", peng));
            permohonanKertasTemp.setPermohonan(permohonan);
            disLaporanTanahService.getPlpservice().simpanPermohonanKertas(permohonanKertasTemp);

        } else {
            permohonanKertasTemp = new PermohonanKertas();
            permohonanKertasTemp.setKodDokumen(kodDokumenDAO.findById(kodDok));
            permohonanKertasTemp.setTajuk("Risalat Kertas MMK");
            permohonanKertasTemp.setCawangan(permohonan.getCawangan());
            permohonanKertasTemp.setNomborRujukan(permohonanKertas.getNomborRujukan());
            permohonanKertasTemp.setTarikhSidang(permohonanKertas.getTarikhSidang());
            permohonanKertasTemp.setPenyediaSidang(permohonanKertas.getPenyediaSidang());
            permohonanKertasTemp.setTarikhSahKeputusan(permohonanKertas.getTarikhSahKeputusan());
            permohonanKertasTemp.setInfoAudit(disLaporanTanahService.findAudit(permohonanKertasTemp, "new", peng));
            permohonanKertasTemp.setPermohonan(permohonan);
            disLaporanTanahService.getPlpservice().simpanPermohonanKertas(permohonanKertasTemp);
        }
        if (kodNegeri.equals("05") && permohonan.getKodUrusan().getKod().equals("PBMT") && !StringUtils.isEmpty(kpsnMohonFasa)) {
            mohonFasa = new FasaPermohonan();
            mohonFasa = (FasaPermohonan) disLaporanTanahService.findObject(mohonFasa, new String[]{idPermohonan, "rekod_keputusan_MMK"}, 0);
            mohonFasa.setInfoAudit(disLaporanTanahService.findAudit(mohonFasa, "update", peng));
            KodKeputusan kodKpsn = new KodKeputusan();
            kodKpsn = (KodKeputusan) disLaporanTanahService.findObject(kodKpsn, new String[]{kpsnMohonFasa}, 0);
            mohonFasa.setKeputusan(kodKpsn);
            disLaporanTanahService.getPlpservice().simpanFasaPermohonan(mohonFasa);
        }
//        if (kodNegeri.equals("04") && !StringUtils.isEmpty(kpsnMohonFasa)) {
//            if (permohonan.getKodUrusan().getKod().equals("LPSP")) {
//                mohonFasa = new FasaPermohonan();
//                mohonFasa = (FasaPermohonan) disLaporanTanahService.findObject(mohonFasa, new String[]{idPermohonan, "RekodKeputusanMMK"}, 0);
//                mohonFasa.setInfoAudit(disLaporanTanahService.findAudit(mohonFasa, "update", peng));
//                KodKeputusan kodKpsn = new KodKeputusan();
//                kodKpsn = (KodKeputusan) disLaporanTanahService.findObject(kodKpsn, new String[]{kpsnMohonFasa}, 0);
//                mohonFasa.setKeputusan(kodKpsn);
//                disLaporanTanahService.getPlpservice().simpanFasaPermohonan(mohonFasa);
//            } else if (permohonan.getKodUrusan().getKod().equals("PPRU")) {
//                mohonFasa = new FasaPermohonan();
//                mohonFasa = (FasaPermohonan) disLaporanTanahService.findObject(mohonFasa, new String[]{idPermohonan, "rekod_keputusan_mmkn"}, 0);
//                mohonFasa.setInfoAudit(disLaporanTanahService.findAudit(mohonFasa, "update", peng));
//                KodKeputusan kodKpsn = new KodKeputusan();
//                kodKpsn = (KodKeputusan) disLaporanTanahService.findObject(kodKpsn, new String[]{kpsnMohonFasa}, 0);
//                mohonFasa.setKeputusan(kodKpsn);
//                disLaporanTanahService.getPlpservice().simpanFasaPermohonan(mohonFasa);
//            }
//        }
        addSimpleMessage("Maklumat Telah Berjaya disimpan");
        disRekodKeputusanController = (DisRekodKeputusanController) getContext().getRequest().getSession().getAttribute("disRekodKeputusanController");
        return new JSP(DisPermohonanPage.getRK_MMK_V2_MM_PAGE()).addParameter("tab", "true");
    }

    public Resolution simpanMesyuarat() throws ParseException {
//        rehydrate();
        ctx = (etanahActionBeanContext) getContext();
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        permohonan = disLaporanTanahService.getPermohonanDAO().findById(idPermohonan);
        Permohonan permohonanTemp = new Permohonan();
        permohonanTemp = (Permohonan) disLaporanTanahService.findObject(permohonanTemp, new String[]{idPermohonan}, 0);
        kodNegeri = disLaporanTanahService.getConf().getProperty("kodNegeri");
        String kodDok = kertasDok(permohonanTemp.getKodUrusan().getKod());
        PermohonanKertas permohonanKertasTemp = new PermohonanKertas();
        permohonanKertasTemp = (PermohonanKertas) disLaporanTanahService.findObject(permohonanKertasTemp, new String[]{idPermohonan, kodDok}, 0);
        if (permohonanKertasTemp != null) {
            permohonanKertasTemp.setCawangan(permohonan.getCawangan());
            permohonanKertasTemp.setNomborRujukan(permohonanKertas.getNomborRujukan());
            permohonanKertasTemp.setTarikhSidang(permohonanKertas.getTarikhSidang());
            permohonanKertasTemp.setPenyediaSidang(permohonanKertas.getPenyediaSidang());
            permohonanKertasTemp.setTarikhSahKeputusan(permohonanKertas.getTarikhSahKeputusan());
            permohonanKertasTemp.setInfoAudit(disLaporanTanahService.findAudit(permohonanKertasTemp, "update", peng));
            permohonanKertasTemp.setPermohonan(permohonan);
            disLaporanTanahService.getPlpservice().simpanPermohonanKertas(permohonanKertasTemp);

        }
        addSimpleMessage("Maklumat Telah Berjaya disimpan");
        disRekodKeputusanController = (DisRekodKeputusanController) getContext().getRequest().getSession().getAttribute("disShowFormBeforeMesyuarat");
        return new JSP(DisPermohonanPage.getRK_MMK_V2_MM_PAGE()).addParameter("tab", "true");
    }

    public Resolution pilihTanah() {
        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        String kpsn = getContext().getRequest().getParameter("kpsn");
        String item = getContext().getRequest().getParameter("item");
        String[] listMH = item.split(",");
        LOG.info("Size :" + listMH.length);
        InfoAudit ia = new InfoAudit();

        for (int i = 0; i < listMH.length; i++) {

            if (!listMH[i].equals("T")) {
                hakmilikPermohonan = new HakmilikPermohonan();
                hakmilikPermohonan = hakmillikPermohonanDAO.findById(Long.valueOf(listMH[i]));
                if (hakmilikPermohonan != null) {
                    ia = hakmilikPermohonan.getInfoAudit();
                    ia.setDiKemaskiniOleh(peng);
                    ia.setTarikhKemaskini(new java.util.Date());
                    hakmilikPermohonan.setInfoAudit(ia);
                    hakmilikPermohonan.setKeputusan(kodKeputusanDAO.findById(kpsn));
                    disLaporanTanahService.getPelupusanService().saveOrUpdate(hakmilikPermohonan);
                }
                if (permohonan.getKodUrusan().getKod().equals("PLPS")) {
                    ia = permohonan.getInfoAudit();
                    ia.setDiKemaskiniOleh(peng);
                    ia.setTarikhKemaskini(new java.util.Date());
                    permohonan.setKeputusan(kodKeputusanDAO.findById(kpsn));
                    pelupusanService.simpanPermohonan(permohonan);
                }
            }
        }
        rehydrate();
        addSimpleMessage("Data telah berjaya disimpan");
        return new JSP(DisPermohonanPage.getRK_MMK_V2_SH_PAGE()).addParameter("tab", "true");
    }

    public Resolution simpanSyarat() throws ParseException {

        ctx = (etanahActionBeanContext) getContext();
        kodNegeri = disLaporanTanahService.getConf().getProperty("kodNegeri");
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = disLaporanTanahService.getPermohonanDAO().findById(idPermohonan);
        if (permohonan.getKodUrusan().getKod().equals("BMBT")) {
            updateMohonHakmilik();
        } else if (permohonan.getKodUrusan().getKod().equals("PJBTR")) {
            updateMohonHakmilik();
            updateMohonTuntutKos();
        } else if (permohonan.getKodUrusan().getKod().equals("PBMT")) {
            updateMohonHakmilik();
            if (kodNegeri.equals("05")) {
                updateMohonTuntutKos();
            }
        } else if (permohonan.getKodUrusan().getKod().equals("PPTR")) {
            updateMohonHakmilik();
            updateMohonTuntutKos();

        }else if (permohonan.getKodUrusan().getKod().equals("PLPT")) {
            updateMohonHakmilik();
            updateMohonTuntutKos();

        } else if (permohonan.getKodUrusan().getKod().equals("PPRU")) {
            updateMohonHakmilik();
            updateMohonTuntutKos();

        } else if (permohonan.getKodUrusan().getKod().equals("PRMP")) {
            updateMohonHakmilik();
            updateMohonTuntutKos();

        } else if (permohonan.getKodUrusan().getKod().equals("PLPS")) {
            updatePermohonan() ;
            updateMohonHakmilik();
            updateMohonPermitItem();
            updateMohonTuntutKos();

        }
        rehydrate();
        if (senaraiKelompok.size() > 0) {
            if (senaraiKelompok.get(0).getJenisKelopok().equals("K")) {
                kelompok = false;
            } else {
                kelompok = false;
            }

        } else {
            kelompok = false;
        }
        addSimpleMessage("Maklumat Berjaya Disimpan");
        return new JSP(DisPermohonanPage.getRK_MMK_V2_SYRT_EDIT_PAGE()).addParameter("tab", Boolean.TRUE);
    }

    public Resolution searchSyarat() {
        indexSyarat = getContext().getRequest().getParameter("index");
        idHakmilik = getContext().getRequest().getParameter("idHakmilik");
        String jenisSyarat = getContext().getRequest().getParameter("jenisSyarat");
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = disLaporanTanahService.getPermohonanDAO().findById(idPermohonan);
        String forwardJSP = new String();
        disSyaratSekatan = new DisSyaratSekatan();
        if (!StringUtils.isEmpty(jenisSyarat)) {
            if (jenisSyarat.equalsIgnoreCase("nyata")) {
                disSyaratSekatan.setKodSyaratNyata(new String());
                disSyaratSekatan.setSyaratNyata2(new String());
                String kodSekatan = getContext().getRequest().getParameter("kodSktn");
                if (!StringUtils.isEmpty(kodSekatan)) {
                    KodSekatanKepentingan kodSK = new KodSekatanKepentingan();
                    kodSK = (KodSekatanKepentingan) disLaporanTanahService.findObject(kodSK, new String[]{kodSekatan}, 0);
                    if (kodSK != null) {
                        disSyaratSekatan.setKodSktn(kodSK.getKod());
                        disSyaratSekatan.setSyaratSekatan(kodSK.getSekatan());
                    }
                }
                disSyaratSekatan.setListKodSyaratNyata(new ArrayList<KodSyaratNyata>());
                forwardJSP = DisPermohonanPage.getRK_MMK_SYARATNYATA_PAGE();
            } else if (jenisSyarat.equalsIgnoreCase("sekatan")) {
                disSyaratSekatan.setKodSekatan(new String());
                disSyaratSekatan.setSyaratSekatan(new String());
                String kodNyata = getContext().getRequest().getParameter("kodNyata");
                if (!StringUtils.isEmpty(kodNyata)) {
                    KodSyaratNyata kodSN = new KodSyaratNyata();
                    kodSN = (KodSyaratNyata) disLaporanTanahService.findObject(kodSN, new String[]{kodNyata}, 0);
                    if (kodSN != null) {
                        disSyaratSekatan.setKod(kodSN.getKod());
                        disSyaratSekatan.setSyaratNyata(kodSN.getSyarat());
                    }
                }
                disSyaratSekatan.setListKodSekatan(new ArrayList<KodSekatanKepentingan>());
                forwardJSP = DisPermohonanPage.getRK_MMK_SEKATAN_PAGE();
            }
        }
        return new JSP(forwardJSP).addParameter("popup", "true");
    }

    public Resolution simpanKodSyaratNyata() {
        idHakmilik = getContext().getRequest().getParameter("idHakmilik");
        HakmilikPermohonan hakmilikPermohonanSave = new HakmilikPermohonan();
        if (!StringUtils.isEmpty(idHakmilik)) {
            hakmilikPermohonanSave = (HakmilikPermohonan) disLaporanTanahService.findObject(hakmilikPermohonanSave, new String[]{idHakmilik}, 2);
        }
        if (disSyaratSekatan != null) {
            if (!StringUtils.isEmpty(disSyaratSekatan.getKod())) {
                KodSyaratNyata kodSN = new KodSyaratNyata();
                kodSN = (KodSyaratNyata) disLaporanTanahService.findObject(kodSN, new String[]{disSyaratSekatan.getKod()}, 0);
                if (kodSN != null) {
                    hakmilikPermohonanSave.setSyaratNyataBaru(kodSN);
                }
            }
        }

        disLaporanTanahService.getPlpservice().simpanHakmilikPermohonan(hakmilikPermohonanSave);
        rehydrate();
        if (!StringUtils.isEmpty(idHakmilik)) {
            hakmilikPermohonan = hakmillikPermohonanDAO.findById(Long.valueOf(idHakmilik));
        }
        return new JSP(DisPermohonanPage.getRK_MMK_V2_SYRT_EDIT_PAGE()).addParameter("tab", Boolean.TRUE);
    }

    public Resolution simpanKodSekatan() {
        idHakmilik = getContext().getRequest().getParameter("idHakmilik");
        HakmilikPermohonan hakmilikPermohonanSave = new HakmilikPermohonan();
        if (!StringUtils.isEmpty(idHakmilik)) {
            hakmilikPermohonanSave = (HakmilikPermohonan) disLaporanTanahService.findObject(hakmilikPermohonanSave, new String[]{idHakmilik}, 2);;
        }
        if (disSyaratSekatan != null) {
            if (!StringUtils.isEmpty(disSyaratSekatan.getKodSktn())) {
                KodSekatanKepentingan kodSK = new KodSekatanKepentingan();
                kodSK = (KodSekatanKepentingan) disLaporanTanahService.findObject(kodSK, new String[]{disSyaratSekatan.getKodSktn()}, 0);
                if (kodSK != null) {
                    hakmilikPermohonanSave.setSekatanKepentinganBaru(kodSK);
                }
            }
        }
        if (!StringUtils.isEmpty(idHakmilik)) {
            hakmilikPermohonan = hakmillikPermohonanDAO.findById(Long.valueOf(idHakmilik));
        }
        disLaporanTanahService.getPlpservice().simpanHakmilikPermohonan(hakmilikPermohonanSave);
        rehydrate();
        return new JSP(DisPermohonanPage.getRK_MMK_V2_SYRT_EDIT_PAGE()).addParameter("tab", Boolean.TRUE);
    }

    public Boolean updateMohonHakmilik() {

        boolean updateDB = false;
        ctx = (etanahActionBeanContext) getContext();
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = disLaporanTanahService.getPermohonanDAO().findById(idPermohonan);
        idHakmilik = getContext().getRequest().getParameter("idHakmilik");
        kodU = getContext().getRequest().getParameter("kodU");
        Pengguna pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        HakmilikPermohonan hakmilikPermohonanSave = new HakmilikPermohonan();
        senaraiKelompok = disLaporanTanahService.getPelupusanService().findMohonKelompokByIdMohonInduk(idPermohonan);
        if (senaraiKelompok.size() > 0) {
            if (senaraiKelompok.get(0).getJenisKelopok().equals("K")) {
                kelompok = false;
            } else {
                kelompok = false;
            }

        } else {
            kelompok = false;
        }
        if (!StringUtils.isEmpty(idHakmilik)) {
            hakmilikPermohonanSave = (HakmilikPermohonan) disLaporanTanahService.findObject(hakmilikPermohonanSave, new String[]{idHakmilik}, 2);;
        }
        if (!kelompok) {
            hakmilikPermohonanSave.setLuasDiluluskan(hakmilikPermohonan.getLuasDiluluskan());
            if (!permohonan.getKodUrusan().getKod().equals("PBMT")&&!permohonan.getKodUrusan().getKod().equals("PLPT")) {
                if (!StringUtils.isEmpty(kodU)) {
                    KodUOM kodUOM = new KodUOM();
                    kodUOM = (KodUOM) disLaporanTanahService.findObject(kodUOM, new String[]{kodU}, 0);
                    hakmilikPermohonanSave.setLuasLulusUom(kodUOM);
                } else {
                    addSimpleError("Sila Pilih Jenis Ukuran bagi Luas Disyorkan");
                }
            } else {
                if (hakmilikPermohonanSave.getKodUnitLuas() != null) {
                    hakmilikPermohonanSave.setLuasLulusUom(hakmilikPermohonan.getKodUnitLuas());
                }
            }
        }

        if (!StringUtils.isEmpty(hakmilikPermohonan.getStatusLuasDiluluskan())) {
            hakmilikPermohonanSave.setStatusLuasDiluluskan(hakmilikPermohonan.getStatusLuasDiluluskan());
        }
  if (hakmilikPermohonan.getTempohPajakan()!=null||hakmilikPermohonan.getTempohPajakan()!=0) {
      int a = hakmilikPermohonan.getTempohPajakan();
                hakmilikPermohonanSave.setTempohPajakan(a);
        }
        kodHakmilik = getContext().getRequest().getParameter("kodHakmilik");
        if (!StringUtils.isEmpty(kodHakmilik)) {
            KodHakmilik khm = disLaporanTanahService.getKodHakmilikDAO().findById(kodHakmilik);
            hakmilikPermohonanSave.setKodHakmilik(khm);
            if (kodHakmilik.equals("PM") || kodHakmilik.equals("PN")) {
                hakmilikPermohonanSave.setTempohPajakan(hakmilikPermohonan.getTempohPajakan());
            }
        }
        katTanahPilihan = getContext().getRequest().getParameter("kategoriTanahBaru.kod");
        if (!StringUtils.isEmpty(katTanahPilihan)) {
            KodKategoriTanah kktb = kodKategoriTanahDAO.findById(katTanahPilihan);
            hakmilikPermohonanSave.setKategoriTanahBaru(kktb);
        }
        kodGunaTanah = getContext().getRequest().getParameter("kodGunaTanah");
        if (!StringUtils.isEmpty(kodGunaTanah)) {
            KodKegunaanTanah kgt = disLaporanTanahService.getKodKegunaanTanahDAO().findById(kodGunaTanah);
            hakmilikPermohonanSave.setKodKegunaanTanah(kgt);
        }
        keteranganKadarPremium = getContext().getRequest().getParameter("keteranganKadarPremium");
        if (!StringUtils.isEmpty(keteranganKadarPremium)) {
            hakmilikPermohonanSave.setKeteranganKadarPremium(keteranganKadarPremium);
        }
        if (!StringUtils.isEmpty(hakmilikPermohonan.getKeteranganCukaiBaru())) {
            hakmilikPermohonanSave.setKeteranganCukaiBaru(hakmilikPermohonan.getKeteranganCukaiBaru());
        }
        if (!StringUtils.isEmpty(hakmilikPermohonan.getAgensiUpahUkur())) {
            hakmilikPermohonanSave.setAgensiUpahUkur(hakmilikPermohonan.getAgensiUpahUkur());
        }
        if (hakmilikPermohonan.getLuasTerlibat() != null && !StringUtils.isEmpty(hakmilikPermohonan.getLuasTerlibat().toString())) {
            hakmilikPermohonanSave.setLuasTerlibat(hakmilikPermohonan.getLuasTerlibat());
        }
        if (permohonan.getKodUrusan().getKod().equals("PBMT")) {
            if (amnt != BigDecimal.ZERO) {
                hakmilikPermohonanSave.setKadarPremium(amnt);
            }
            if (hakmilikPermohonanSave.getLuasDiluluskan() != null && hakmilikPermohonanSave.getKodUnitLuas() != null) {
                BigDecimal cukai = calcTax.calculate(kodGunaTanah, String.valueOf(hakmilikPermohonanSave.getBandarPekanMukimBaru().getKod()), hakmilikPermohonanSave.getKodUnitLuas().getKod(), hakmilikPermohonanSave.getLuasDiluluskan(), hakmilikPermohonanSave, null);
                if (cukai != null && !cukai.equals(BigDecimal.ZERO)) {
                    hakmilikPermohonanSave.setCukaiBaru(cukai);
                }
            }
            if (hakmilikPermohonan != null && hakmilikPermohonan.getCukaiPerMeterPersegi() != null) {
                hakmilikPermohonanSave.setCukaiPerMeterPersegi(hakmilikPermohonan.getCukaiPerMeterPersegi());
            }
            if (!StringUtils.isEmpty(hakmilikPermohonan.getKeteranganKadarPremium())) {
                hakmilikPermohonanSave.setKeteranganKadarPremium(hakmilikPermohonan.getKeteranganKadarPremium());
            }
        }

        if (permohonan.getKodUrusan().getKod().equals("PPRU")) {
            hakmilikPermohonanSave.setTempohPajakan(hakmilikPermohonan.getTempohPajakan());
        }

        disLaporanTanahService.getPlpservice().simpanHakmilikPermohonan(hakmilikPermohonanSave);

        return updateDB;
    }

    public void updateMohonPermitItem() {
        Pengguna pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        List<PermohonanPermitItem> senaraiMohonPermitItem = new ArrayList<PermohonanPermitItem>();
        senaraiMohonPermitItem = disLaporanTanahService.getPlpservice().findPermohonanPermitItemByIdMohonList(idPermohonan);

        for (PermohonanPermitItem ppi : senaraiMohonPermitItem) {
            if (!ppi.getKodItemPermit().getKod().equalsIgnoreCase("KB") && !ppi.getKodItemPermit().getKod().equalsIgnoreCase("PB") && !ppi.getKodItemPermit().getKod().equalsIgnoreCase("MB") && !ppi.getKodItemPermit().getKod().equalsIgnoreCase("LN")) {
                PermohonanPermitItem permohonanPermitItemSave = new PermohonanPermitItem();
                if (ppi.getKodItemPermit() != null) {
                    permohonanPermitItemSave = (PermohonanPermitItem) disLaporanTanahService.findObject(permohonanPermitItemSave, new String[]{ppi.getPermohonan().getIdPermohonan(), ppi.getKodItemPermit().getKod()}, 1);
                } else {
                    permohonanPermitItemSave = (PermohonanPermitItem) disLaporanTanahService.findObject(permohonanPermitItemSave, new String[]{ppi.getPermohonan().getIdPermohonan()}, 0);
                }
                if (permohonanPermitItemSave != null) {
                    permohonanPermitItemSave.setInfoAudit(disLaporanTanahService.findAudit(permohonanPermitItemSave, "update", pengguna));
                } else {
                    permohonanPermitItemSave = new PermohonanPermitItem();
                    permohonanPermitItemSave.setInfoAudit(disLaporanTanahService.findAudit(permohonanPermitItemSave, "new", pengguna));
                    permohonanPermitItemSave.setPermohonan(permohonan);
                    permohonanPermitItemSave.setKodCawangan(permohonan.getCawangan());
                }

                if (!StringUtils.isEmpty(keg)) {
                    KodItemPermit kip = new KodItemPermit();
                    kip = disLaporanTanahService.getKodItemPermitDAO().findById(keg);
                    if (kip != null) {
                        permohonanPermitItemSave.setKodItemPermit(kip);
                        disLaporanTanahService.getPlpservice().saveOrUpdate(permohonanPermitItemSave);
                    }
                }
            }
        }
        if (permohonan.getKodUrusan().getKod().equals("RLPS")) {
            System.err.println(senaraiMohonPermitItem.size());
            if (senaraiMohonPermitItem.size() == 0) {
                System.out.println("RLPS keg :" + keg);
                PermohonanPermitItem permohonanPermitItemSave = new PermohonanPermitItem();
                permohonanPermitItemSave.setInfoAudit(disLaporanTanahService.findAudit(permohonanPermitItemSave, "new", pengguna));
                permohonanPermitItemSave.setPermohonan(permohonan);
                permohonanPermitItemSave.setKodCawangan(permohonan.getCawangan());
                if (!StringUtils.isEmpty(keg)) {
                    KodItemPermit kip = new KodItemPermit();
                    kip = disLaporanTanahService.getKodItemPermitDAO().findById(keg);
                    if (kip != null) {
                        permohonanPermitItemSave.setKodItemPermit(kip);
                        disLaporanTanahService.getPlpservice().saveOrUpdate(permohonanPermitItemSave);
                    }
                }
            }
        }
    }

    public void updatePermohonan() {
        Permohonan permohonanSave = new Permohonan();
        permohonanSave = disLaporanTanahService.getPermohonanDAO().findById(idPermohonan);
        if (permohonanSave != null) {
            if (!StringUtils.isEmpty(catatan)) {
                permohonanSave.setCatatan(catatan);
                disLaporanTanahService.getPlpservice().simpanPermohonan(permohonanSave);
            }
        }
    }

    public void updateMohonTuntutKos() {
        Pengguna pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        PermohonanTuntutanKos mohonTuntutKos = new PermohonanTuntutanKos();
        HakmilikPermohonan mohonHM = new HakmilikPermohonan();
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        senaraiKelompok = disLaporanTanahService.getPelupusanService().findMohonKelompokByIdMohonInduk(idPermohonan);
        if (senaraiKelompok.size() > 0) {
            kelompok = true;
        } else {
            kelompok = false;
        }

        int numUrusan = permohonan.getKodUrusan().getKod().equals("PPBB") ? 1
                : permohonan.getKodUrusan().getKod().equals("PBPTD") ? 1
                : permohonan.getKodUrusan().getKod().equals("PBPTG") ? 1
                : permohonan.getKodUrusan().getKod().equals("LMCRG") ? 2
                : permohonan.getKodUrusan().getKod().equals("PJLB") ? 3
                //                : permohonan.getKodUrusan().getKod().equals("LPSP") ? 4
                : permohonan.getKodUrusan().getKod().equals("LPSP") ? 1
                : permohonan.getKodUrusan().getKod().equals("PLPS") ? 5
                : permohonan.getKodUrusan().getKod().equals("PPRU") ? 6
                : permohonan.getKodUrusan().getKod().equals("PPTR") ? 7
                : permohonan.getKodUrusan().getKod().equals("PRMP") ? 8
                : permohonan.getKodUrusan().getKod().equals("PBMT") ? 9
                : permohonan.getKodUrusan().getKod().equals("PJBTR") ? 10
                : 0;

        switch (numUrusan) {
            case 1:
                /*
                 * Add for Bayaran Kupon 
                 */

                mohonTuntutKos = disLaporanTanahService.getPlpservice().findMohonTuntutKosByIdPermohonanAndIdTuntut(idPermohonan, "DISKD");

                if (mohonTuntutKos == null) {
                    mohonTuntutKos = new PermohonanTuntutanKos();
                    mohonTuntutKos.setInfoAudit(disLaporanTanahService.findAudit(mohonTuntutKos, "new", pengguna));
                    mohonTuntutKos.setPermohonan(permohonan);
                    mohonTuntutKos.setCawangan(permohonan.getCawangan());
                    mohonTuntutKos.setItem("Bayaran Kupon");
                    mohonTuntutKos.setAmaunTuntutan(disPermohonanBahanBatu.getKupon());
                    mohonTuntutKos.setKuantiti(disPermohonanBahanBatu.getKuponQty());
                    BigDecimal seUnit = new BigDecimal(50);
                    mohonTuntutKos.setAmaunSeunit(seUnit);
                    mohonTuntutKos.setKodTuntut(disLaporanTanahService.getKodTuntutDAO().findById("DISKD"));
                    mohonTuntutKos.setKodTransaksi(disLaporanTanahService.getKodTuntutDAO().findById("DISKD").getKodKewTrans());
                    // plpservice.simpanSavePermohonanTuntutanKos(mohonTuntutKos);
                    disLaporanTanahService.getPermohonanTuntutanKosDAO().save(mohonTuntutKos);
                } else {
                    mohonTuntutKos.setInfoAudit(disLaporanTanahService.findAudit(mohonTuntutKos, "update", pengguna));
                    mohonTuntutKos.setPermohonan(permohonan);
                    mohonTuntutKos.setCawangan(permohonan.getCawangan());
                    mohonTuntutKos.setItem("Bayaran Kupon");
                    mohonTuntutKos.setAmaunTuntutan(disPermohonanBahanBatu.getKupon());
                    BigDecimal seUnit = new BigDecimal(50);
                    mohonTuntutKos.setAmaunSeunit(seUnit);
                    mohonTuntutKos.setKuantiti(disPermohonanBahanBatu.getKuponQty());
                    mohonTuntutKos.setKodTuntut(disLaporanTanahService.getKodTuntutDAO().findById("DISKD"));
                    mohonTuntutKos.setKodTransaksi(disLaporanTanahService.getKodTuntutDAO().findById("DISKD").getKodKewTrans());
                    disLaporanTanahService.getPlpservice().simpanPermohonanTuntutanKos(mohonTuntutKos);
                }
                /* End for bayaran kupon
                 * 
                 */
                /*Add for CAGARAN JALAN
                 * 
                 */
                PermohonanTuntutanKos mohonTuntutKosCagarJln = new PermohonanTuntutanKos();
                mohonTuntutKosCagarJln = disLaporanTanahService.getPlpservice().findMohonTuntutKosByIdPermohonanAndIdTuntut(idPermohonan, "DISCJ");

                if (mohonTuntutKosCagarJln == null) {
                    mohonTuntutKosCagarJln = new PermohonanTuntutanKos();
                    mohonTuntutKosCagarJln.setInfoAudit(disLaporanTanahService.findAudit(mohonTuntutKosCagarJln, "new", pengguna));
                    mohonTuntutKosCagarJln.setPermohonan(permohonan);
                    mohonTuntutKosCagarJln.setCawangan(permohonan.getCawangan());
                    mohonTuntutKosCagarJln.setItem("Cagaran Jalan");
                    mohonTuntutKosCagarJln.setAmaunTuntutan(disPermohonanBahanBatu.getCagarJalan());
////                        mohonTuntutKosCagarJln.setKuantiti(kuponQty);
//                        BigDecimal seUnit = new BigDecimal(50);
//                        mohonTuntutKosCagarJln.setAmaunSeunit(seUnit);
                    mohonTuntutKosCagarJln.setKodTuntut(disLaporanTanahService.getKodTuntutDAO().findById("DISCJ"));
                    mohonTuntutKosCagarJln.setKodTransaksi(disLaporanTanahService.getKodTuntutDAO().findById("DISKD").getKodKewTrans());
                    disLaporanTanahService.getPlpservice().simpanSavePermohonanTuntutanKos(mohonTuntutKosCagarJln);
                } else {
                    mohonTuntutKosCagarJln.setInfoAudit(disLaporanTanahService.findAudit(mohonTuntutKosCagarJln, "update", pengguna));
                    mohonTuntutKosCagarJln.setPermohonan(permohonan);
                    mohonTuntutKosCagarJln.setCawangan(permohonan.getCawangan());
                    mohonTuntutKosCagarJln.setItem("Cagaran Jalan");
                    mohonTuntutKosCagarJln.setAmaunTuntutan(disPermohonanBahanBatu.getCagarJalan());
//                        BigDecimal seUnit = new BigDecimal(50);
//                        mohonTuntutKosCagarJln.setAmaunSeunit(seUnit);
//                        mohonTuntutKosCagarJln.setKuantiti(kuponQty);
                    mohonTuntutKosCagarJln.setKodTuntut(disLaporanTanahService.getKodTuntutDAO().findById("DISCJ"));
                    mohonTuntutKosCagarJln.setKodTransaksi(disLaporanTanahService.getKodTuntutDAO().findById("DISCJ").getKodKewTrans());
                    disLaporanTanahService.getPlpservice().simpanPermohonanTuntutanKos(mohonTuntutKosCagarJln);
                }
                /* 
                 *  End for bayaran kupon
                 */
                /*
                 * Add for bayaran LPS - For urusan LPSP
                 */
                if (permohonan.getKodUrusan().getKod().equals("LPSP")) {
                    PermohonanTuntutanKos mohonTuntutKosBayaranLPS = new PermohonanTuntutanKos();
                    mohonTuntutKosBayaranLPS = disLaporanTanahService.getPlpservice().findMohonTuntutKosByIdPermohonanAndIdTuntut(idPermohonan, "DIS4B");
                    if (mohonTuntutKosBayaranLPS == null) {
                        mohonTuntutKosBayaranLPS = new PermohonanTuntutanKos();
                        mohonTuntutKosBayaranLPS.setInfoAudit(disLaporanTanahService.findAudit(mohonTuntutKosBayaranLPS, "new", pengguna));
                        mohonTuntutKosBayaranLPS.setPermohonan(permohonan);
                        mohonTuntutKosBayaranLPS.setCawangan(permohonan.getCawangan());
                        mohonTuntutKosBayaranLPS.setItem("Bayaran LPS");
                        mohonTuntutKosBayaranLPS.setAmaunTuntutan(amnt);
                        mohonTuntutKosBayaranLPS.setKodTuntut(disLaporanTanahService.getKodTuntutDAO().findById("DIS4B"));
                        mohonTuntutKosBayaranLPS.setKodTransaksi(disLaporanTanahService.getKodTuntutDAO().findById("DIS4B").getKodKewTrans());
                        disLaporanTanahService.getPermohonanTuntutanKosDAO().save(mohonTuntutKosBayaranLPS);
                    } else {
                        mohonTuntutKosBayaranLPS.setInfoAudit(disLaporanTanahService.findAudit(mohonTuntutKosBayaranLPS, "update", pengguna));
                        mohonTuntutKosBayaranLPS.setPermohonan(permohonan);
                        mohonTuntutKosBayaranLPS.setCawangan(permohonan.getCawangan());
                        mohonTuntutKosBayaranLPS.setItem("Bayaran LPS");
                        mohonTuntutKosBayaranLPS.setAmaunTuntutan(amnt);
                        mohonTuntutKosBayaranLPS.setKodTuntut(disLaporanTanahService.getKodTuntutDAO().findById("DIS4B"));
                        mohonTuntutKosBayaranLPS.setKodTransaksi(disLaporanTanahService.getKodTuntutDAO().findById("DIS4B").getKodKewTrans());
                        disLaporanTanahService.getPlpservice().simpanPermohonanTuntutanKos(mohonTuntutKosBayaranLPS);
                    }
                }
                /*
                 * End for bayaran LPS
                 */
                break;
            case 2:
                mohonTuntutKos = new PermohonanTuntutanKos();
                mohonTuntutKos = disLaporanTanahService.getPlpservice().findMohonTuntutKosByIdPermohonanAndIdTuntut(idPermohonan, "DISCR");
                idHakmilik = getContext().getRequest().getParameter("idHakmilik");
                mohonHM = new HakmilikPermohonan();
                if (!StringUtils.isEmpty(idHakmilik)) {
                    mohonHM = (HakmilikPermohonan) disLaporanTanahService.findObject(mohonHM, new String[]{idPermohonan, idHakmilik}, 0);
                } else {
                    mohonHM = (HakmilikPermohonan) disLaporanTanahService.findObject(mohonHM, new String[]{idPermohonan}, 1);
                }
                if (mohonTuntutKos == null) {
                    mohonTuntutKos = new PermohonanTuntutanKos();
                    mohonTuntutKos.setInfoAudit(disLaporanTanahService.findAudit(mohonTuntutKos, "new", pengguna));
                    mohonTuntutKos.setPermohonan(permohonan);
                    mohonTuntutKos.setCawangan(permohonan.getCawangan());

                } else {
                    mohonTuntutKos.setInfoAudit(disLaporanTanahService.findAudit(mohonTuntutKos, "update", pengguna));
                }
                if (mohonHM != null) {
                    mohonTuntutKos.setHakmilikPermohonan(mohonHM);
                }
                mohonTuntutKos.setItem(disLaporanTanahService.getKodTuntutDAO().findById("DISCR").getNama());
                mohonTuntutKos.setAmaunTuntutan(amnt);
                mohonTuntutKos.setKodTuntut(disLaporanTanahService.getKodTuntutDAO().findById("DISCR"));
                mohonTuntutKos.setKodTransaksi(disLaporanTanahService.getKodTuntutDAO().findById("DISCR").getKodKewTrans());
                disLaporanTanahService.getPlpservice().simpanPermohonanTuntutanKos(mohonTuntutKos);
                break;

            case 3:
                mohonTuntutKos = new PermohonanTuntutanKos();
                mohonTuntutKos = disLaporanTanahService.getPlpservice().findMohonTuntutKosByIdPermohonanAndIdTuntut(idPermohonan, "DISLB");
                idHakmilik = getContext().getRequest().getParameter("idHakmilik");
                mohonHM = new HakmilikPermohonan();
                if (!StringUtils.isEmpty(idHakmilik)) {
                    mohonHM = (HakmilikPermohonan) disLaporanTanahService.findObject(mohonHM, new String[]{idPermohonan, idHakmilik}, 0);
                } else {
                    mohonHM = (HakmilikPermohonan) disLaporanTanahService.findObject(mohonHM, new String[]{idPermohonan}, 1);
                }
                if (mohonTuntutKos == null) {
                    mohonTuntutKos = new PermohonanTuntutanKos();
                    mohonTuntutKos.setInfoAudit(disLaporanTanahService.findAudit(mohonTuntutKos, "new", pengguna));
                    mohonTuntutKos.setPermohonan(permohonan);
                    mohonTuntutKos.setCawangan(permohonan.getCawangan());

                } else {
                    mohonTuntutKos.setInfoAudit(disLaporanTanahService.findAudit(mohonTuntutKos, "update", pengguna));
                }
                if (mohonHM != null) {
                    mohonTuntutKos.setHakmilikPermohonan(mohonHM);
                }
                mohonTuntutKos.setItem(disLaporanTanahService.getKodTuntutDAO().findById("DISLB").getNama());
                mohonTuntutKos.setAmaunTuntutan(amnt);
                mohonTuntutKos.setKodTuntut(disLaporanTanahService.getKodTuntutDAO().findById("DISLB"));
                mohonTuntutKos.setKodTransaksi(disLaporanTanahService.getKodTuntutDAO().findById("DISLB").getKodKewTrans());
                disLaporanTanahService.getPlpservice().simpanPermohonanTuntutanKos(mohonTuntutKos);
                break;
            case 4:
                mohonTuntutKos = new PermohonanTuntutanKos();
                mohonTuntutKos = disLaporanTanahService.getPlpservice().findMohonTuntutKosByIdPermohonanAndIdTuntut(idPermohonan, "DIS4B");
                idHakmilik = getContext().getRequest().getParameter("idHakmilik");
                mohonHM = new HakmilikPermohonan();
                if (!StringUtils.isEmpty(idHakmilik)) {
                    mohonHM = (HakmilikPermohonan) disLaporanTanahService.findObject(mohonHM, new String[]{idPermohonan, idHakmilik}, 0);
                } else {
                    mohonHM = (HakmilikPermohonan) disLaporanTanahService.findObject(mohonHM, new String[]{idPermohonan}, 1);
                }
                if (mohonTuntutKos == null) {
                    mohonTuntutKos = new PermohonanTuntutanKos();
                    mohonTuntutKos.setInfoAudit(disLaporanTanahService.findAudit(mohonTuntutKos, "new", pengguna));
                    mohonTuntutKos.setPermohonan(permohonan);
                    mohonTuntutKos.setCawangan(permohonan.getCawangan());

                } else {
                    mohonTuntutKos.setInfoAudit(disLaporanTanahService.findAudit(mohonTuntutKos, "update", pengguna));
                }
                if (mohonHM != null) {
                    mohonTuntutKos.setHakmilikPermohonan(mohonHM);
                }
                mohonTuntutKos.setItem(disLaporanTanahService.getKodTuntutDAO().findById("DIS4B").getNama());
                mohonTuntutKos.setAmaunTuntutan(amnt);
                mohonTuntutKos.setKodTuntut(disLaporanTanahService.getKodTuntutDAO().findById("DIS4B"));
                mohonTuntutKos.setKodTransaksi(disLaporanTanahService.getKodTuntutDAO().findById("DIS4B").getKodKewTrans());
                disLaporanTanahService.getPlpservice().simpanPermohonanTuntutanKos(mohonTuntutKos);
                break;
            case 5:
                mohonTuntutKos = new PermohonanTuntutanKos();
                mohonTuntutKos = disLaporanTanahService.getPlpservice().findMohonTuntutKosByIdPermohonanAndIdTuntut(idPermohonan, "DISB4A");
                idHakmilik = getContext().getRequest().getParameter("idHakmilik");
                mohonHM = new HakmilikPermohonan();
                if (!StringUtils.isEmpty(idHakmilik)) {
                    mohonHM = (HakmilikPermohonan) disLaporanTanahService.findObject(mohonHM, new String[]{idPermohonan, idHakmilik}, 0);
                } else {
                    mohonHM = (HakmilikPermohonan) disLaporanTanahService.findObject(mohonHM, new String[]{idPermohonan}, 1);
                }
                if (mohonTuntutKos == null) {
                    mohonTuntutKos = new PermohonanTuntutanKos();
                    mohonTuntutKos.setInfoAudit(disLaporanTanahService.findAudit(mohonTuntutKos, "new", pengguna));
                    mohonTuntutKos.setPermohonan(permohonan);
                    mohonTuntutKos.setCawangan(permohonan.getCawangan());

                } else {
                    mohonTuntutKos.setInfoAudit(disLaporanTanahService.findAudit(mohonTuntutKos, "update", pengguna));
                }
                if (mohonHM != null) {
                    mohonTuntutKos.setHakmilikPermohonan(mohonHM);
                }
                mohonTuntutKos.setItem(disLaporanTanahService.getKodTuntutDAO().findById("DISB4A").getNama());
                mohonTuntutKos.setAmaunTuntutan(amnt);
                mohonTuntutKos.setKodTuntut(disLaporanTanahService.getKodTuntutDAO().findById("DISB4A"));
                mohonTuntutKos.setKodTransaksi(disLaporanTanahService.getKodTuntutDAO().findById("DISB4A").getKodKewTrans());
                disLaporanTanahService.getPlpservice().simpanPermohonanTuntutanKos(mohonTuntutKos);
                break;
            case 6:
                mohonTuntutKos = new PermohonanTuntutanKos();
                mohonTuntutKos = disLaporanTanahService.getPlpservice().findMohonTuntutKosByIdPermohonanAndIdTuntut(idPermohonan, "DISB4D");
                idHakmilik = getContext().getRequest().getParameter("idHakmilik");
                mohonHM = new HakmilikPermohonan();
                if (!StringUtils.isEmpty(idHakmilik)) {
                    mohonHM = (HakmilikPermohonan) disLaporanTanahService.findObject(mohonHM, new String[]{idPermohonan, idHakmilik}, 0);
                } else {
                    mohonHM = (HakmilikPermohonan) disLaporanTanahService.findObject(mohonHM, new String[]{idPermohonan}, 1);
                }
                if (mohonTuntutKos == null) {
                    mohonTuntutKos = new PermohonanTuntutanKos();
                    mohonTuntutKos.setInfoAudit(disLaporanTanahService.findAudit(mohonTuntutKos, "new", pengguna));
                    mohonTuntutKos.setPermohonan(permohonan);
                    mohonTuntutKos.setCawangan(permohonan.getCawangan());

                } else {
                    mohonTuntutKos.setInfoAudit(disLaporanTanahService.findAudit(mohonTuntutKos, "update", pengguna));
                }
                if (mohonHM != null) {
                    mohonTuntutKos.setHakmilikPermohonan(mohonHM);
                }
                mohonTuntutKos.setItem(disLaporanTanahService.getKodTuntutDAO().findById("DISB4D").getNama());
                mohonTuntutKos.setAmaunTuntutan(amnt);
                mohonTuntutKos.setKodTuntut(disLaporanTanahService.getKodTuntutDAO().findById("DISB4D"));
                mohonTuntutKos.setKodTransaksi(disLaporanTanahService.getKodTuntutDAO().findById("DISB4D").getKodKewTrans());
                disLaporanTanahService.getPlpservice().simpanPermohonanTuntutanKos(mohonTuntutKos);
                break;
            case 7:
                mohonTuntutKos = new PermohonanTuntutanKos();
                mohonTuntutKos = disLaporanTanahService.getPlpservice().findMohonTuntutKosByIdPermohonanAndIdTuntut(idPermohonan, "DISB4E");
                idHakmilik = getContext().getRequest().getParameter("idHakmilik");
                mohonHM = new HakmilikPermohonan();
                if (!StringUtils.isEmpty(idHakmilik)) {
                    mohonHM = (HakmilikPermohonan) disLaporanTanahService.findObject(mohonHM, new String[]{idPermohonan, idHakmilik}, 0);
                } else {
                    mohonHM = (HakmilikPermohonan) disLaporanTanahService.findObject(mohonHM, new String[]{idPermohonan}, 1);
                }
                if (mohonTuntutKos == null) {
                    mohonTuntutKos = new PermohonanTuntutanKos();
                    mohonTuntutKos.setInfoAudit(disLaporanTanahService.findAudit(mohonTuntutKos, "new", pengguna));
                    mohonTuntutKos.setPermohonan(permohonan);
                    mohonTuntutKos.setCawangan(permohonan.getCawangan());

                } else {
                    mohonTuntutKos.setInfoAudit(disLaporanTanahService.findAudit(mohonTuntutKos, "update", pengguna));
                }
                if (mohonHM != null) {
                    mohonTuntutKos.setHakmilikPermohonan(mohonHM);
                }
                mohonTuntutKos.setItem(disLaporanTanahService.getKodTuntutDAO().findById("DISB4E").getNama());
                mohonTuntutKos.setAmaunTuntutan(amnt);
                mohonTuntutKos.setKodTuntut(disLaporanTanahService.getKodTuntutDAO().findById("DISB4E"));
                mohonTuntutKos.setKodTransaksi(disLaporanTanahService.getKodTuntutDAO().findById("DISB4E").getKodKewTrans());
                disLaporanTanahService.getPlpservice().simpanPermohonanTuntutanKos(mohonTuntutKos);
                break;
            case 8:
                mohonTuntutKos = new PermohonanTuntutanKos();
                mohonTuntutKos = disLaporanTanahService.getPlpservice().findMohonTuntutKosByIdPermohonanAndIdTuntut(idPermohonan, "DISB7");
                idHakmilik = getContext().getRequest().getParameter("idHakmilik");
                mohonHM = new HakmilikPermohonan();
                if (!StringUtils.isEmpty(idHakmilik)) {
                    mohonHM = (HakmilikPermohonan) disLaporanTanahService.findObject(mohonHM, new String[]{idPermohonan, idHakmilik}, 0);
                } else {
                    mohonHM = (HakmilikPermohonan) disLaporanTanahService.findObject(mohonHM, new String[]{idPermohonan}, 1);
                }
                if (mohonTuntutKos == null) {
                    mohonTuntutKos = new PermohonanTuntutanKos();
                    mohonTuntutKos.setInfoAudit(disLaporanTanahService.findAudit(mohonTuntutKos, "new", pengguna));
                    mohonTuntutKos.setPermohonan(permohonan);
                    mohonTuntutKos.setCawangan(permohonan.getCawangan());

                } else {
                    mohonTuntutKos.setInfoAudit(disLaporanTanahService.findAudit(mohonTuntutKos, "update", pengguna));
                }
                if (mohonHM != null) {
                    mohonTuntutKos.setHakmilikPermohonan(mohonHM);
                }
                mohonTuntutKos.setItem(disLaporanTanahService.getKodTuntutDAO().findById("DISB7").getNama());
                mohonTuntutKos.setAmaunTuntutan(amnt);
                mohonTuntutKos.setKodTuntut(disLaporanTanahService.getKodTuntutDAO().findById("DISB7"));
                mohonTuntutKos.setKodTransaksi(disLaporanTanahService.getKodTuntutDAO().findById("DISB7").getKodKewTrans());
                disLaporanTanahService.getPlpservice().simpanPermohonanTuntutanKos(mohonTuntutKos);
                break;
            case 9:
                mohonTuntutKos = new PermohonanTuntutanKos();
                idHakmilik = getContext().getRequest().getParameter("idHakmilik");
                mohonHM = new HakmilikPermohonan();
                if (!StringUtils.isEmpty(idHakmilik)) {
                    mohonHM = (HakmilikPermohonan) disLaporanTanahService.findObject(mohonHM, new String[]{idHakmilik}, 2);
                } else {
                    mohonHM = (HakmilikPermohonan) disLaporanTanahService.findObject(mohonHM, new String[]{idPermohonan}, 1);
                }
                if (kelompok) {
                    if (mohonHM != null) {
                        mohonTuntutKos = disLaporanTanahService.getPlpservice().findMohonTuntutKosByIdPermohonanAndIdTuntut(mohonHM.getPermohonan().getIdPermohonan(), "DISPRM");
                    }
                } else {
                    mohonTuntutKos = disLaporanTanahService.getPlpservice().findMohonTuntutKosByIdPermohonanAndIdTuntut(idPermohonan, "DISPRM");
                }

                if (mohonTuntutKos == null) {
                    mohonTuntutKos = new PermohonanTuntutanKos();
                    mohonTuntutKos.setInfoAudit(disLaporanTanahService.findAudit(mohonTuntutKos, "new", pengguna));
                    if (kelompok) {
                        mohonTuntutKos.setPermohonan(mohonHM.getPermohonan());
                        mohonTuntutKos.setCawangan(mohonHM.getPermohonan().getCawangan());
                    } else {
                        mohonTuntutKos.setPermohonan(permohonan);
                        mohonTuntutKos.setCawangan(permohonan.getCawangan());
                    }

                } else {
                    mohonTuntutKos.setInfoAudit(disLaporanTanahService.findAudit(mohonTuntutKos, "update", pengguna));
                }
                if (mohonHM != null) {
                    mohonTuntutKos.setHakmilikPermohonan(mohonHM);
                }
                mohonTuntutKos.setItem(disLaporanTanahService.getKodTuntutDAO().findById("DISPRM").getNama());
                mohonTuntutKos.setAmaunTuntutan(amnt);
                mohonTuntutKos.setKodTuntut(disLaporanTanahService.getKodTuntutDAO().findById("DISPRM"));
                mohonTuntutKos.setKodTransaksi(disLaporanTanahService.getKodTuntutDAO().findById("DISPRM").getKodKewTrans());
                disLaporanTanahService.getPlpservice().simpanPermohonanTuntutanKos(mohonTuntutKos);

                PermohonanTuntutanKos mohonTuntutKosCukai = new PermohonanTuntutanKos();

                mohonHM = new HakmilikPermohonan();
                if (!StringUtils.isEmpty(idHakmilik)) {
                    mohonHM = (HakmilikPermohonan) disLaporanTanahService.findObject(mohonHM, new String[]{idHakmilik}, 2);
                } else {
                    mohonHM = (HakmilikPermohonan) disLaporanTanahService.findObject(mohonHM, new String[]{idPermohonan}, 1);
                }
                if (kelompok) {
                    if (mohonHM != null) {
                        mohonTuntutKosCukai = disLaporanTanahService.getPlpservice().findMohonTuntutKosByIdPermohonanAndIdTuntut(mohonHM.getPermohonan().getIdPermohonan(), "DISTAX");
                    }
                } else {
                    mohonTuntutKosCukai = disLaporanTanahService.getPlpservice().findMohonTuntutKosByIdPermohonanAndIdTuntut(idPermohonan, "DISTAX");
                }
                if (mohonTuntutKosCukai == null) {
                    mohonTuntutKosCukai = new PermohonanTuntutanKos();
                    mohonTuntutKosCukai.setInfoAudit(disLaporanTanahService.findAudit(mohonTuntutKos, "new", pengguna));
                    if (kelompok) {
                        mohonTuntutKosCukai.setPermohonan(mohonHM.getPermohonan());
                        mohonTuntutKosCukai.setCawangan(mohonHM.getPermohonan().getCawangan());
                    } else {
                        mohonTuntutKosCukai.setPermohonan(permohonan);
                        mohonTuntutKosCukai.setCawangan(permohonan.getCawangan());
                    }
                } else {
                    mohonTuntutKosCukai.setInfoAudit(disLaporanTanahService.findAudit(mohonTuntutKos, "update", pengguna));
                }
                if (mohonHM != null) {
                    mohonTuntutKosCukai.setHakmilikPermohonan(mohonHM);
                    mohonTuntutKosCukai.setAmaunTuntutan(calcTax.calculate(mohonHM.getKodKegunaanTanah().getKod(), String.valueOf(mohonHM.getBandarPekanMukimBaru().getKod()), mohonHM.getKodUnitLuas().getKod(), mohonHM.getLuasDiluluskan(), mohonHM, null));
                }
                mohonTuntutKosCukai.setItem(disLaporanTanahService.getKodTuntutDAO().findById("DISTAX").getNama());
                mohonTuntutKosCukai.setKodTuntut(disLaporanTanahService.getKodTuntutDAO().findById("DISTAX"));
                mohonTuntutKosCukai.setKodTransaksi(disLaporanTanahService.getKodTuntutDAO().findById("DISTAX").getKodKewTrans());
                disLaporanTanahService.getPlpservice().simpanPermohonanTuntutanKos(mohonTuntutKosCukai);
                break;
            case 10:
                mohonTuntutKos = new PermohonanTuntutanKos();
                mohonTuntutKos = disLaporanTanahService.getPlpservice().findMohonTuntutKosByIdPermohonanAndIdTuntut(idPermohonan, "DISSTR");
                idHakmilik = getContext().getRequest().getParameter("idHakmilik");
                mohonHM = new HakmilikPermohonan();
                if (!StringUtils.isEmpty(idHakmilik)) {
                    mohonHM = (HakmilikPermohonan) disLaporanTanahService.findObject(mohonHM, new String[]{idPermohonan, idHakmilik}, 0);
                } else {
                    mohonHM = (HakmilikPermohonan) disLaporanTanahService.findObject(mohonHM, new String[]{idPermohonan}, 1);
                }
                if (mohonTuntutKos == null) {
                    mohonTuntutKos = new PermohonanTuntutanKos();
                    mohonTuntutKos.setInfoAudit(disLaporanTanahService.findAudit(mohonTuntutKos, "new", pengguna));
                    mohonTuntutKos.setPermohonan(permohonan);
                    mohonTuntutKos.setCawangan(permohonan.getCawangan());

                } else {
                    mohonTuntutKos.setInfoAudit(disLaporanTanahService.findAudit(mohonTuntutKos, "update", pengguna));
                }
                if (mohonHM != null) {
                    mohonTuntutKos.setHakmilikPermohonan(mohonHM);
                }
                mohonTuntutKos.setItem(disLaporanTanahService.getKodTuntutDAO().findById("DISSTR").getNama());
                mohonTuntutKos.setAmaunTuntutan(amnt);
                mohonTuntutKos.setKodTuntut(disLaporanTanahService.getKodTuntutDAO().findById("DISSTR"));
                mohonTuntutKos.setKodTransaksi(disLaporanTanahService.getKodTuntutDAO().findById("DISSTR").getKodKewTrans());
                disLaporanTanahService.getPlpservice().simpanPermohonanTuntutanKos(mohonTuntutKos);
                break;
        }

    }

    public void copyDataKelompokToIndividu() {

        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        String taskId = (String) getContext().getRequest().getSession().getAttribute("taskId");
        Pengguna pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        kodNegeri = disLaporanTanahService.getConf().getProperty("kodNegeri");
        peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);

        if (idPermohonan != null) {
            senaraiKelompok = disLaporanTanahService.getPelupusanService().findMohonKelompokByIdMohonInduk(idPermohonan);
            permohonan = new Permohonan();
            permohonan = (Permohonan) disLaporanTanahService.findObject(permohonan, new String[]{idPermohonan}, 0);
            if (permohonan.getCatatan().equals("K")) //Make Kelompok copy to Individu for Lulus;
            {
                if (senaraiKelompok.size() > 0) {
                    Permohonan p = new Permohonan();
                    HakmilikPermohonan hpTemp = new HakmilikPermohonan();
                    hpTemp = permohonan.getSenaraiHakmilik().get(0);
                    DisHakmilikPermohonan disHakmilikPermohonan = new DisHakmilikPermohonan();
                    for (PermohonanKelompok pk : senaraiKelompok) {
                        p = pk.getPermohonan();
                        if (p.getSenaraiHakmilik().size() > 0) {
                            for (HakmilikPermohonan hmp : p.getSenaraiHakmilik()) {
                                if (hmp.getKeputusan().getKod().equals("L")) {
                                    BigDecimal luasLulus = new BigDecimal(0);
                                    hmp.setInfoAudit(p.getInfoAudit());
                                    hmp.setPermohonan(p);
                                    hmp.setKeteranganCukaiBaru(hpTemp.getKeteranganCukaiBaru() != null ? hpTemp.getKeteranganCukaiBaru() : new String());
                                    hmp.setKeteranganKadarPremium(hpTemp.getKeteranganKadarPremium() != null ? hpTemp.getKeteranganKadarPremium() : new String());
                                    hmp.setKadarPremium(hpTemp.getKadarPremium() != BigDecimal.ZERO ? hpTemp.getKadarPremium() : BigDecimal.ZERO);
                                    hmp.setSyaratNyataBaru(hpTemp.getSyaratNyataBaru() != null ? hpTemp.getSyaratNyataBaru() : null);
                                    hmp.setSekatanKepentinganBaru(hpTemp.getSekatanKepentinganBaru() != null ? hpTemp.getSekatanKepentinganBaru() : null);
                                    hmp.setKodMilik(hpTemp.getKodMilik() != null ? hpTemp.getKodMilik() : null);
                                    hmp.setAgensiUpahUkur(hpTemp.getAgensiUpahUkur() != null ? hpTemp.getAgensiUpahUkur() : null);
                                    if (hpTemp.getHakmilik() != null) {
                                        hmp = disHakmilikPermohonan.copyPropertiesHakmilikToMH(permohonan, hmp, hpTemp.getHakmilik(), new String[]{}, disLaporanTanahService);
                                        hmp.setHakmilik(hpTemp.getHakmilik());
                                    } else {
                                        String kbpm = new String();
                                        String ksek = new String();
                                        String khakmilik = new String();
                                        String klot = new String();
                                        String kktanah = new String();
                                        String ksyarat = new String();
                                        String kguna = new String();
                                        String ksekatan = new String();
                                        String tP = new String();
                                        String luas = new String();
                                        String kuom = new String();
                                        String cukai = new String();

                                        kbpm = hpTemp.getBandarPekanMukimBaru() != null ? String.valueOf(hpTemp.getBandarPekanMukimBaru().getKod()) : null;
                                        ksek = hpTemp.getKodSeksyen() != null ? String.valueOf(hpTemp.getKodSeksyen().getKod()) : null;
                                        khakmilik = hpTemp.getKodHakmilik() != null ? hpTemp.getKodHakmilik().getKod() : null;
                                        klot = null;
                                        kktanah = hpTemp.getKategoriTanahBaru() != null ? hpTemp.getKategoriTanahBaru().getKod() : null;
                                        ksyarat = hpTemp.getSyaratNyataBaru() != null ? hpTemp.getSyaratNyataBaru().getKod() : null;
                                        kguna = hpTemp.getKodKegunaanTanah() != null ? hpTemp.getKodKegunaanTanah().getKod() : null;
                                        ksekatan = hpTemp.getSekatanKepentinganBaru() != null ? hpTemp.getSekatanKepentinganBaru().getKod() : null;
                                        tP = hpTemp.getTempohPajakan() != null ? String.valueOf(hpTemp.getTempohPajakan()) : null;
                                        BigDecimal test = hpTemp.getLuasTerlibat() != null ? hpTemp.getLuasTerlibat() : null;
                                        double noLot = Double.parseDouble(hpTemp.getNoLot());
                                        BigDecimal divisor = BigDecimal.valueOf(noLot);
                                        LOG.info("Value =" + test);
                                        LOG.info("Divisor =" + divisor);
                                        luasLulus = test.divide(divisor);
                                        luas = test.divide(divisor).toString();
                                        hmp.setLuasDiluluskan(luasLulus);
                                        hmp.setLuasLulusUom(hpTemp.getLuasLulusUom());
                                        kuom = hpTemp.getKodUnitLuas() != null ? hpTemp.getKodUnitLuas().getKod() : null;
                                        cukai = hpTemp.getCukaiBaru() != null ? hpTemp.getCukaiBaru().toString() : null;
                                        hmp = disHakmilikPermohonan.copyPropertiesHakmilikToMH(permohonan, hmp, hpTemp.getHakmilik(), new String[]{kbpm, ksek, khakmilik, klot, null, hmp.getLokasi(), kktanah, ksyarat, kguna, ksekatan, hmp.getPegangan(), tP, luas, kuom, cukai}, disLaporanTanahService);
                                    }
                                    disLaporanTanahService.getPelupusanService().saveOrUpdate(hmp);

                                    //Mohon Tuntutan Kos
                                    PermohonanTuntutanKos mohonTuntutKos = new PermohonanTuntutanKos();
                                    mohonTuntutKos = disLaporanTanahService.getPlpservice().findMohonTuntutKosByIdPermohonanAndIdTuntut(hmp.getPermohonan().getIdPermohonan(), "DISPRM");

                                    if (mohonTuntutKos == null) {
                                        mohonTuntutKos = new PermohonanTuntutanKos();
                                        mohonTuntutKos.setInfoAudit(disLaporanTanahService.findAudit(mohonTuntutKos, "new", pengguna));
                                        mohonTuntutKos.setPermohonan(hmp.getPermohonan());
                                        mohonTuntutKos.setCawangan(hmp.getPermohonan().getCawangan());
                                    } else {
                                        mohonTuntutKos.setInfoAudit(disLaporanTanahService.findAudit(mohonTuntutKos, "update", pengguna));
                                    }

                                    mohonTuntutKos.setHakmilikPermohonan(hmp);
                                    mohonTuntutKos.setItem(disLaporanTanahService.getKodTuntutDAO().findById("DISPRM").getNama());
                                    mohonTuntutKos.setAmaunTuntutan(hpTemp.getKadarPremium());
                                    mohonTuntutKos.setKodTuntut(disLaporanTanahService.getKodTuntutDAO().findById("DISPRM"));
                                    mohonTuntutKos.setKodTransaksi(disLaporanTanahService.getKodTuntutDAO().findById("DISPRM").getKodKewTrans());
                                    disLaporanTanahService.getPlpservice().simpanPermohonanTuntutanKos(mohonTuntutKos);

                                    PermohonanTuntutanKos mohonTuntutKosCukai = new PermohonanTuntutanKos();
                                    mohonTuntutKosCukai = disLaporanTanahService.getPlpservice().findMohonTuntutKosByIdPermohonanAndIdTuntut(hmp.getPermohonan().getIdPermohonan(), "DISTAX");
                                    if (mohonTuntutKosCukai == null) {
                                        mohonTuntutKosCukai = new PermohonanTuntutanKos();
                                        mohonTuntutKosCukai.setInfoAudit(disLaporanTanahService.findAudit(mohonTuntutKos, "new", pengguna));
                                        mohonTuntutKosCukai.setPermohonan(hmp.getPermohonan());
                                        mohonTuntutKosCukai.setCawangan(hmp.getPermohonan().getCawangan());
                                    } else {
                                        mohonTuntutKosCukai.setInfoAudit(disLaporanTanahService.findAudit(mohonTuntutKos, "update", pengguna));
                                    }
                                    mohonTuntutKosCukai.setHakmilikPermohonan(hmp);
                                    mohonTuntutKosCukai.setAmaunTuntutan(calcTax.calculate(hpTemp.getKodKegunaanTanah().getKod(), String.valueOf(hpTemp.getBandarPekanMukimBaru().getKod()), hpTemp.getKodUnitLuas().getKod(), luasLulus, hmp, null));

                                    mohonTuntutKosCukai.setItem(disLaporanTanahService.getKodTuntutDAO().findById("DISTAX").getNama());
                                    mohonTuntutKosCukai.setKodTuntut(disLaporanTanahService.getKodTuntutDAO().findById("DISTAX"));
                                    mohonTuntutKosCukai.setKodTransaksi(disLaporanTanahService.getKodTuntutDAO().findById("DISTAX").getKodKewTrans());
                                    disLaporanTanahService.getPlpservice().simpanPermohonanTuntutanKos(mohonTuntutKosCukai);
                                }
                            }
                        }
                    }
                }
            }
        }

    }

    public BigDecimal checkPermohonanTuntutanKos(Permohonan p) {
        BigDecimal amaun = new BigDecimal(0);
        PermohonanTuntutanKos mohonTuntutKos = new PermohonanTuntutanKos();
        int typeNum = p.getKodUrusan().getKod().equals("PPBB") ? 1
                : p.getKodUrusan().getKod().equals("PBPTD") ? 2
                : p.getKodUrusan().getKod().equals("PBPTG") ? 3
                : p.getKodUrusan().getKod().equals("LMCRG") ? 4
                : p.getKodUrusan().getKod().equals("PJLB") ? 5
                : p.getKodUrusan().getKod().equals("LPSP") ? 6
                : p.getKodUrusan().getKod().equals("PLPS") ? 7
                : p.getKodUrusan().getKod().equals("PPRU") ? 8
                : p.getKodUrusan().getKod().equals("PPTR") ? 9
                : p.getKodUrusan().getKod().equals("PTGSA") ? 10
                : p.getKodUrusan().getKod().equals("PRMP") ? 11
                : p.getKodUrusan().getKod().equals("PBMT") ? 12
                : p.getKodUrusan().getKod().equals("MCMCL") ? 13
                : p.getKodUrusan().getKod().equals("MMMCL") ? 14
                : p.getKodUrusan().getKod().equals("PRIZ") ? 15
                : p.getKodUrusan().getKod().equals("PHLA") ? 16
                : p.getKodUrusan().getKod().equals("PBRZ") ? 17
                : p.getKodUrusan().getKod().equals("PBHL") ? 18
                : p.getKodUrusan().getKod().equals("BMBT") ? 19
                : p.getKodUrusan().getKod().equals("PJBTR") ? 20
                : p.getKodUrusan().getKod().equals("PLPTD") ? 21
                : p.getKodUrusan().getKod().equals("PBMMK") ? 22
                : 0;
        switch (typeNum) {
            case 7: //Urusan PLPS
                mohonTuntutKos = disLaporanTanahService.getPlpservice().findMohonTuntutKosByIdPermohonanAndIdTuntut(permohonan.getIdPermohonan(), "DISB4A");
                if (mohonTuntutKos != null) {
                    if (mohonTuntutKos.getAmaunTuntutan() != BigDecimal.ZERO) {
                        amaun = mohonTuntutKos.getAmaunTuntutan();
                    }
                }

                break;
            case 8: //Urusan PPTR
                mohonTuntutKos = disLaporanTanahService.getPlpservice().findMohonTuntutKosByIdPermohonanAndIdTuntut(permohonan.getIdPermohonan(), "DISB4D");
                if (mohonTuntutKos != null) {
                    if (mohonTuntutKos.getAmaunTuntutan() != BigDecimal.ZERO) {
                        amaun = mohonTuntutKos.getAmaunTuntutan();
                    }
                }

                break;
            case 9: //Urusan PPTR
                mohonTuntutKos = disLaporanTanahService.getPlpservice().findMohonTuntutKosByIdPermohonanAndIdTuntut(permohonan.getIdPermohonan(), "DISB4E");
                if (mohonTuntutKos != null) {
                    if (mohonTuntutKos.getAmaunTuntutan() != BigDecimal.ZERO) {
                        amaun = mohonTuntutKos.getAmaunTuntutan();
                    }
                }

                break;
            case 11: //Urusan PRMP
                mohonTuntutKos = disLaporanTanahService.getPlpservice().findMohonTuntutKosByIdPermohonanAndIdTuntut(permohonan.getIdPermohonan(), "DISB7");
                if (mohonTuntutKos != null) {
                    if (mohonTuntutKos.getAmaunTuntutan() != BigDecimal.ZERO) {
                        amaun = mohonTuntutKos.getAmaunTuntutan();
                    }
                }

                break;
            case 12: //Urusan PBMT
                mohonTuntutKos = disLaporanTanahService.getPlpservice().findMohonTuntutKosByIdPermohonanAndIdTuntut(permohonan.getIdPermohonan(), "DISPRM");
                if (mohonTuntutKos != null) {
                    if (mohonTuntutKos.getAmaunTuntutan() != BigDecimal.ZERO) {
                        amaun = mohonTuntutKos.getAmaunTuntutan();
                    }
                }
                break;

            default:
                break;
        }
        return amaun;
    }

    public HakmilikPermohonan getHakmilikPermohonan() {
        return hakmilikPermohonan;
    }

    public void setHakmilikPermohonan(HakmilikPermohonan hakmilikPermohonan) {
        this.hakmilikPermohonan = hakmilikPermohonan;
    }

    public List getHakmilikPermohonanList() {
        return hakmilikPermohonanList;
    }

    public void setHakmilikPermohonanList(List hakmilikPermohonanList) {
        this.hakmilikPermohonanList = hakmilikPermohonanList;
    }

    public String getIdHakmilik() {
        return idHakmilik;
    }

    public void setIdHakmilik(String idHakmilik) {
        this.idHakmilik = idHakmilik;
    }

    public String getIdPermohonan() {
        return idPermohonan;
    }

    public void setIdPermohonan(String idPermohonan) {
        this.idPermohonan = idPermohonan;
    }

    public String getKeputusan() {
        return keputusan;
    }

    public void setKeputusan(String keputusan) {
        this.keputusan = keputusan;
    }

    public String getKodNegeri() {
        return kodNegeri;
    }

    public void setKodNegeri(String kodNegeri) {
        this.kodNegeri = kodNegeri;
    }

    public Pengguna getPeng() {
        return peng;
    }

    public void setPeng(Pengguna peng) {
        this.peng = peng;
    }

    public Permohonan getPermohonan() {
        return permohonan;
    }

    public void setPermohonan(Permohonan permohonan) {
        this.permohonan = permohonan;
    }

    public PermohonanKertas getPermohonanKertas() {
        return permohonanKertas;
    }

    public void setPermohonanKertas(PermohonanKertas permohonanKertas) {
        this.permohonanKertas = permohonanKertas;
    }

    public List<HakmilikPermohonan> getSenaraiHakmilikPermohonan() {
        return senaraiHakmilikPermohonan;
    }

    public void setSenaraiHakmilikPermohonan(List<HakmilikPermohonan> senaraiHakmilikPermohonan) {
        this.senaraiHakmilikPermohonan = senaraiHakmilikPermohonan;
    }

    public String getStageId() {
        return stageId;
    }

    public void setStageId(String stageId) {
        this.stageId = stageId;
    }

    public DisRekodKeputusanController getDisRekodKeputusanController() {
        return disRekodKeputusanController;
    }

    public void setDisRekodKeputusanController(DisRekodKeputusanController disRekodKeputusanController) {
        this.disRekodKeputusanController = disRekodKeputusanController;
    }

    public DisSyaratSekatan getDisSyaratSekatan() {
        return disSyaratSekatan;
    }

    public void setDisSyaratSekatan(DisSyaratSekatan disSyaratSekatan) {
        this.disSyaratSekatan = disSyaratSekatan;
    }

    public String getIndexSyarat() {
        return indexSyarat;
    }

    public void setIndexSyarat(String indexSyarat) {
        this.indexSyarat = indexSyarat;
    }

    public String getKeteranganKadarPremium() {
        return keteranganKadarPremium;
    }

    public void setKeteranganKadarPremium(String keteranganKadarPremium) {
        this.keteranganKadarPremium = keteranganKadarPremium;
    }

    public String getKod() {
        return kod;
    }

    public void setKod(String kod) {
        this.kod = kod;
    }

    public String getKodGunaTanah() {
        return kodGunaTanah;
    }

    public void setKodGunaTanah(String kodGunaTanah) {
        this.kodGunaTanah = kodGunaTanah;
    }

    public String getKodHakmilik() {
        return kodHakmilik;
    }

    public void setKodHakmilik(String kodHakmilik) {
        this.kodHakmilik = kodHakmilik;
    }

    public String getKodSktn() {
        return kodSktn;
    }

    public void setKodSktn(String kodSktn) {
        this.kodSktn = kodSktn;
    }

    public String getKodU() {
        return kodU;
    }

    public void setKodU(String kodU) {
        this.kodU = kodU;
    }

    public String getSyaratNyata() {
        return syaratNyata;
    }

    public void setSyaratNyata(String syaratNyata) {
        this.syaratNyata = syaratNyata;
    }

    public String getSyaratNyata1() {
        return syaratNyata1;
    }

    public void setSyaratNyata1(String syaratNyata1) {
        this.syaratNyata1 = syaratNyata1;
    }

    public String getIndex() {
        return index;
    }

    public void setIndex(String index) {
        this.index = index;
    }

    public String getKodSekatanKepentingan() {
        return kodSekatanKepentingan;
    }

    public void setKodSekatanKepentingan(String kodSekatanKepentingan) {
        this.kodSekatanKepentingan = kodSekatanKepentingan;
    }

    public String getKodSyaratNyata() {
        return kodSyaratNyata;
    }

    public void setKodSyaratNyata(String kodSyaratNyata) {
        this.kodSyaratNyata = kodSyaratNyata;
    }

    public List<KodSekatanKepentingan> getListKodSekatan() {
        return listKodSekatan;
    }

    public void setListKodSekatan(List<KodSekatanKepentingan> listKodSekatan) {
        this.listKodSekatan = listKodSekatan;
    }

    public List<KodSyaratNyata> getListKodSyaratNyata() {
        return listKodSyaratNyata;
    }

    public void setListKodSyaratNyata(List<KodSyaratNyata> listKodSyaratNyata) {
        this.listKodSyaratNyata = listKodSyaratNyata;
    }

    public String getSekatKpntgn2() {
        return sekatKpntgn2;
    }

    public void setSekatKpntgn2(String sekatKpntgn2) {
        this.sekatKpntgn2 = sekatKpntgn2;
    }

    public String getSyaratNyata2() {
        return syaratNyata2;
    }

    public void setSyaratNyata2(String syaratNyata2) {
        this.syaratNyata2 = syaratNyata2;
    }

    public BigDecimal getAmnt() {
        return amnt;
    }

    public void setAmnt(BigDecimal amnt) {
        this.amnt = amnt;
    }

    public DisPermohonanBahanBatu getDisPermohonanBahanBatu() {
        return disPermohonanBahanBatu;
    }

    public void setDisPermohonanBahanBatu(DisPermohonanBahanBatu disPermohonanBahanBatu) {
        this.disPermohonanBahanBatu = disPermohonanBahanBatu;
    }

    public PermohonanTuntutanKos getPermohonanTuntutanKos() {
        return permohonanTuntutanKos;
    }

    public void setPermohonanTuntutanKos(PermohonanTuntutanKos permohonanTuntutanKos) {
        this.permohonanTuntutanKos = permohonanTuntutanKos;
    }

    public List<HakmilikPermohonan> getSenaraiHakmilikTiadaKeputusan() {
        return senaraiHakmilikTiadaKeputusan;
    }

    public void setSenaraiHakmilikTiadaKeputusan(List<HakmilikPermohonan> senaraiHakmilikTiadaKeputusan) {
        this.senaraiHakmilikTiadaKeputusan = senaraiHakmilikTiadaKeputusan;
    }

    public List<HakmilikPermohonan> getSenaraiHakmilikTolak() {
        return senaraiHakmilikTolak;
    }

    public void setSenaraiHakmilikTolak(List<HakmilikPermohonan> senaraiHakmilikTolak) {
        this.senaraiHakmilikTolak = senaraiHakmilikTolak;
    }

    public List<HakmilikPermohonan> getSenaraiHakmilikLulus() {
        return senaraiHakmilikLulus;
    }

    public void setSenaraiHakmilikLulus(List<HakmilikPermohonan> senaraiHakmilikLulus) {
        this.senaraiHakmilikLulus = senaraiHakmilikLulus;
    }

    public int getSizeTiadaKeputusan() {
        return sizeTiadaKeputusan;
    }

    public void setSizeTiadaKeputusan(int sizeTiadaKeputusan) {
        this.sizeTiadaKeputusan = sizeTiadaKeputusan;
    }

    public int getSizeLulus() {
        return sizeLulus;
    }

    public void setSizeLulus(int sizeLulus) {
        this.sizeLulus = sizeLulus;
    }

    public int getSizeTolak() {
        return sizeTolak;
    }

    public void setSizeTolak(int sizeTolak) {
        this.sizeTolak = sizeTolak;
    }

    public List<KodKegunaanTanah> getSenaraiKodKegunaanTanah() {
        if (StringUtils.isNotBlank(katTanahPilihan)) {
            return pelupusanService.getSenaraiKegunaanTanah(katTanahPilihan);
        }
        return new ArrayList<KodKegunaanTanah>();
    }

    public void setSenaraiKodKegunaanTanah(List<KodKegunaanTanah> senaraiKodKegunaanTanah) {
        this.senaraiKodKegunaanTanah = senaraiKodKegunaanTanah;
    }

    public String getKatTanahPilihan() {
        return katTanahPilihan;
    }

    public void setKatTanahPilihan(String katTanahPilihan) {
        this.katTanahPilihan = katTanahPilihan;
    }

    public List<String> getSenaraikodKadarPremium() {
        return senaraikodKadarPremium;
    }

    public void setSenaraikodKadarPremium(List<String> senaraikodKadarPremium) {
        this.senaraikodKadarPremium = senaraikodKadarPremium;
    }

    public List<KodKegunaanTanah> getListKodGunaTanahByKatTanah() {
        return listKodGunaTanahByKatTanah;
    }

    public void setListKodGunaTanahByKatTanah(List<KodKegunaanTanah> listKodGunaTanahByKatTanah) {
        this.listKodGunaTanahByKatTanah = listKodGunaTanahByKatTanah;
    }

    public FasaPermohonan getMohonFasa() {
        return mohonFasa;
    }

    public void setMohonFasa(FasaPermohonan mohonFasa) {
        this.mohonFasa = mohonFasa;
    }

    public String getKpsnMohonFasa() {
        return kpsnMohonFasa;
    }

    public void setKpsnMohonFasa(String kpsnMohonFasa) {
        this.kpsnMohonFasa = kpsnMohonFasa;
    }

    public boolean isKelompok() {
        return kelompok;
    }

    public void setKelompok(boolean kelompok) {
        this.kelompok = kelompok;
    }

    public String getKeg() {
        return keg;
    }

    public void setKeg(String keg) {
        this.keg = keg;
    }

    public String getCatatan() {
        return catatan;
    }

    public void setCatatan(String catatan) {
        this.catatan = catatan;
    }

    public PermohonanPermitItem getPermohonanPermitItem() {
        return permohonanPermitItem;
    }

    public void setPermohonanPermitItem(PermohonanPermitItem permohonanPermitItem) {
        this.permohonanPermitItem = permohonanPermitItem;
    }
    
    

}
