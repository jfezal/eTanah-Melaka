/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.stripes.consent;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.dao.KodCawanganDAO;
import etanah.dao.KodDokumenDAO;
import etanah.dao.PermohonanDAO;
import etanah.model.InfoAudit;
import etanah.model.KodCawangan;
import etanah.model.KodDokumen;
import etanah.model.Pengguna;
import etanah.model.PenggunaPeranan;
import etanah.model.Permohonan;
import etanah.model.PermohonanKertas;
import etanah.model.PermohonanRujukanLuar;
import etanah.model.PermohonanTandatanganDokumen;
import etanah.service.BPelService;
import etanah.service.ConsentPtdService;
import etanah.view.etanahActionBeanContext;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;
import oracle.bpel.services.workflow.task.model.Task;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author muhammad.amin
 */
@UrlBinding("/consent/tandatangan")
public class TandatanganActionBean extends AbleActionBean {

    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    KodDokumenDAO kodDokumenDAO;
    @Inject
    etanah.Configuration conf;
    @Inject
    KodCawanganDAO kodCawanganDAO;
    @Inject
    ConsentPtdService consentService;
    private List<PenggunaPeranan> penggunaPerananList;
    private List<PermohonanRujukanLuar> mohonRujukanLuarList;
    private PermohonanTandatanganDokumen mohonTandatanganDokumen;
    private PermohonanRujukanLuar permohonanRujukanLuar;
    private PermohonanKertas permohonanKertas;
    private Permohonan permohonan;
    private String idPermohonan;
    private String stageId;
    private String tarikhTamat;
    private String tarikhTandatangan;
    private String stageA;
    private String stageB;
    private String stageC;
    private String kodDokSave;
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;
    private static final Logger LOG = Logger.getLogger(TandatanganActionBean.class);

    @DefaultHandler
    public Resolution showForm() {
        return new JSP("consent/tandatangan.jsp").addParameter("tab", "true");
    }

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");

        permohonan = permohonanDAO.findById(idPermohonan);

        Pengguna pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        BPelService service = new BPelService();
        String taskId = (String) getContext().getRequest().getSession().getAttribute("taskId");
        stageId = "";

        if (StringUtils.isNotBlank(taskId)) {
            Task task = service.getTaskFromBPel(taskId, pengguna);
            stageId = task.getSystemAttributes().getStage();
        }

        if (permohonan.getKodUrusan().getKod().equals("PMJTL") || permohonan.getKodUrusan().getKod().equals("RMJTL")) {

            if (permohonan.getKodUrusan().getKod().equals("PMJTL")) {
                stageA = "Stage1"; // SURAT JABATAN TEKNIKAL
                stageB = "Stage6"; // SURAT PANGGILAN MESYUARAT
                stageC = "Stage8"; // SURAT KEBENARAN & SIJIL
            } else {
                stageA = "Stage1";
                stageB = "Stage5";
                stageC = "Stage7";
            }

            LOG.info("STAGE ID : " + stageId);
            String[] kumpBPEL = {"ptgkptconsent", "ppptg", "ptg"};
            penggunaPerananList = getSenaraiPenggunaPeranan(permohonan.getCawangan(), kumpBPEL);
            if (stageId.equals(stageA)) {
                mohonTandatanganDokumen = consentService.findMohonTandatanganDokomen(idPermohonan, "SUT");

                if (permohonan.getSenaraiRujukanLuar().size() > 0) {
                    permohonanRujukanLuar = permohonan.getSenaraiRujukanLuar().get(0);
                }

                if (permohonanRujukanLuar != null) {
                    if (permohonanRujukanLuar.getTarikhTamat() != null) {
                        tarikhTamat = sdf.format(permohonanRujukanLuar.getTarikhTamat());
                    }
                }

            } else if (stageId.equals(stageB)) {
                mohonTandatanganDokumen = consentService.findMohonTandatanganDokomen(idPermohonan, "KR");

            } else if (stageId.equals(stageC)) {

                mohonTandatanganDokumen = consentService.findMohonTandatanganDokomen(idPermohonan, "SK");
                permohonanRujukanLuar = consentService.findMohonRujukanNotTangguh2(idPermohonan);
                if (mohonTandatanganDokumen != null) {
                    if (mohonTandatanganDokumen.getTrhTt() != null) {
                        tarikhTandatangan = sdf.format(mohonTandatanganDokumen.getTrhTt());
                    }
                }

                if (permohonan.getSenaraiKertas() != null) {
                    permohonanKertas = consentService.findMohonKertasByTajuk(idPermohonan, "KERTAS JKTL");
                }
            }
        } else if (permohonan.getKodUrusan().getKod().equals("PMMK1") || permohonan.getKodUrusan().getKod().equals("PMMK2")) {
            if (permohonan.getKodUrusan().getKod().equals("PMMK1")) {
                if (stageId.equals("Stage1")) {
                    String[] kumpBPEL = {"tphgt", "tptg","kptmmkn"};
                    penggunaPerananList = getSenaraiPenggunaPeranan(permohonan.getCawangan(), kumpBPEL);
                    mohonTandatanganDokumen = consentService.findMohonTandatanganDokomen(idPermohonan, "SUT");
                } else if (stageId.equals("Stage8")) {
                    String[] kumpBPEL = {"tptg", "ptg"};
                    penggunaPerananList = getSenaraiPenggunaPeranan(permohonan.getCawangan(), kumpBPEL);
                    mohonTandatanganDokumen = consentService.findMohonTandatanganDokomen(idPermohonan, "SMM");
                } else if (stageId.equals("Stage10")) {
                    String[] kumpBPEL = {"tptg", "ptg"};
                    penggunaPerananList = getSenaraiPenggunaPeranan(permohonan.getCawangan(), kumpBPEL);
                    mohonTandatanganDokumen = consentService.findMohonTandatanganDokomen(idPermohonan, "SK");
                } else if (stageId.equals("Stage11")) {
                    String[] kumpBPEL = {"tptg", "ptg"};
                    penggunaPerananList = getSenaraiPenggunaPeranan(permohonan.getCawangan(), kumpBPEL);
                    mohonTandatanganDokumen = consentService.findMohonTandatanganDokomen(idPermohonan, "SMM");
                }
            } else if (permohonan.getKodUrusan().getKod().equals("PMMK2")) {
                if (stageId.equals("Stage1")) {
                    String[] kumpBPEL = {"tphgt", "tptg","kptmmkn"};
                    penggunaPerananList = getSenaraiPenggunaPeranan(permohonan.getCawangan(), kumpBPEL);
                    mohonTandatanganDokumen = consentService.findMohonTandatanganDokomen(idPermohonan, "SUT");
                } else if (stageId.equals("Stage8")) {
                    String[] kumpBPEL = {"tptg", "ptg"};
                    penggunaPerananList = getSenaraiPenggunaPeranan(permohonan.getCawangan(), kumpBPEL);
                    mohonTandatanganDokumen = consentService.findMohonTandatanganDokomen(idPermohonan, "SK");
                }
            }
        } else {

            String[] kumpBPEL = {"tptd", "ptd", "pentadbirtanah"};
            penggunaPerananList = getSenaraiPenggunaPeranan(permohonan.getCawangan(), kumpBPEL);
            mohonTandatanganDokumen = consentService.findMohonTandatanganDokomen(idPermohonan);
        }
    }

    public Resolution simpan() throws ParseException {

        Pengguna pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = permohonanDAO.findById(idPermohonan);
        BPelService service = new BPelService();
        String taskId = (String) getContext().getRequest().getSession().getAttribute("taskId");
        stageId = "";

        if (StringUtils.isNotBlank(taskId)) {
            Task task = service.getTaskFromBPel(taskId, pengguna);
            stageId = task.getSystemAttributes().getStage();
        }

        if (permohonan.getKodUrusan().getKod().equals("PMJTL") || permohonan.getKodUrusan().getKod().equals("RMJTL")) {

            boolean complete = false;

            if (stageId.equals("Stage1")) {

                if (permohonan.getKodUrusan().getKod().equals("PMJTL")) {
                    if (mohonTandatanganDokumen == null || tarikhTamat == null) {
                        addSimpleError("Sila masukkan maklumat terlebih dahulu.");
                    } else {
                        if (mohonTandatanganDokumen.getDiTandatangan() == null || tarikhTamat == null) {
                            addSimpleError("Sila masukkan maklumat terlebih dahulu.");
                        } else {
                            complete = true;
                            kodDokSave = "SUT";
                            mohonRujukanLuarList = consentService.findSenaraiMohonRujukanByAgensiNotNull(idPermohonan);

                            for (PermohonanRujukanLuar mohonRujLuar : mohonRujukanLuarList) {
                                if (tarikhTamat != null) {
                                    try {
                                        mohonRujLuar.setTarikhTamat(sdf.parse(tarikhTamat));
                                    } catch (ParseException ex) {
                                        LOG.error("exception: " + ex.getMessage());
                                        throw ex;
                                    }
                                }
                                consentService.simpanRujukanLuar(mohonRujLuar);
                            }
                        }
                    }
                } else {
                    if (mohonTandatanganDokumen == null) {
                        addSimpleError("Sila masukkan maklumat terlebih dahulu.");
                    } else {
                        if (mohonTandatanganDokumen.getDiTandatangan() == null) {
                            addSimpleError("Sila masukkan maklumat terlebih dahulu.");
                        } else {
                            complete = true;
                            kodDokSave = "SUT";
                        }
                    }
                }

            } else if (stageId.equals("Stage5") || stageId.equals("Stage6")) {
                if (mohonTandatanganDokumen == null) {
                    addSimpleError("Sila masukkan maklumat terlebih dahulu.");

                } else {
                    if (mohonTandatanganDokumen.getDiTandatangan() == null) {
                        addSimpleError("Sila masukkan maklumat terlebih dahulu.");
                    } else {
                        complete = true;
                        kodDokSave = "KR";
                    }
                }
            } else if (stageId.equals("Stage7") || stageId.equals("Stage8")) {

                if (permohonan.getKodUrusan().getKod().equals("RMJTL")) {
                    if (permohonanKertas.getNomborRujukan() == null) {
                        addSimpleError("Sila masukkan maklumat terlebih dahulu.");
                    } else {
                        complete = true;
                        kodDokSave = "SK";
                    }

                } else {
                    if (tarikhTandatangan == null) {
                        addSimpleError("Sila masukkan maklumat terlebih dahulu.");
                    } else {
                        complete = true;
                        kodDokSave = "SK";
                    }
                }
            }

            if (complete) {

                idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
                InfoAudit infoAudit = new InfoAudit();
                KodDokumen kodDokumen = new KodDokumen();

                PermohonanTandatanganDokumen mohonTandatanganDokumenTemp = consentService.findMohonTandatanganDokomen(idPermohonan, kodDokSave);
                kodDokumen.setKod(kodDokSave);

                if (mohonTandatanganDokumenTemp == null) {
                    mohonTandatanganDokumenTemp = new PermohonanTandatanganDokumen();
                    infoAudit.setDimasukOleh(pengguna);
                    infoAudit.setTarikhMasuk(new java.util.Date());
                } else {
                    infoAudit = mohonTandatanganDokumenTemp.getInfoAudit();
                    infoAudit.setDiKemaskiniOleh(pengguna);
                    infoAudit.setTarikhKemaskini(new java.util.Date());
                }

                mohonTandatanganDokumenTemp.setPermohonan(permohonan);
                mohonTandatanganDokumenTemp.setCawangan(permohonan.getCawangan());
                mohonTandatanganDokumenTemp.setInfoAudit(infoAudit);
                mohonTandatanganDokumenTemp.setKodDokumen(kodDokumen);

                //STAGE 8 PMJTL TIDAK PERLU - PTG SAHAJA BOLEH TANDATANGAN
                if (!stageId.equals("Stage7") && !stageId.equals("Stage8")) {
                    mohonTandatanganDokumenTemp.setDiTandatangan(mohonTandatanganDokumen.getDiTandatangan());
                }
                if (stageId.equals("Stage7") || stageId.equals("Stage8")) {
                    if (tarikhTandatangan != null) {
                        try {
                            mohonTandatanganDokumenTemp.setTrhTt(sdf.parse(tarikhTandatangan));
                        } catch (ParseException ex) {
                            LOG.error("exception: " + ex.getMessage());
                            throw ex;
                        }
                    }
                    if (permohonan.getKodUrusan().getKod().equals("RMJTL")) {
                        PermohonanKertas permohonanKertasTemp = consentService.findMohonKertasByTajuk(idPermohonan, "KERTAS JKTL");
                        if (permohonanKertasTemp != null) {
                            permohonanKertasTemp.setNomborRujukan(permohonanKertas.getNomborRujukan());
                            consentService.simpanPermohonanKertas(permohonanKertasTemp);
                        }
                    }
                }
                consentService.simpanMohonTandatanganDokumen(mohonTandatanganDokumenTemp);
                addSimpleMessage("Maklumat berjaya disimpan.");
            }
        } else if (permohonan.getKodUrusan().getKod().equals("PMMK1") || permohonan.getKodUrusan().getKod().equals("PMMK2")) {
            if (mohonTandatanganDokumen == null) {
                addSimpleError("Sila pilih maklumat terlebih dahulu.");

            } else {
                if (mohonTandatanganDokumen.getDiTandatangan() == null) {
                    addSimpleError("Sila pilih maklumat terlebih dahulu.");
                } else {

                    idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
                    InfoAudit infoAudit = new InfoAudit();
                    KodDokumen kodDokumen = new KodDokumen();

                    if (permohonan.getKodUrusan().getKod().equals("PMMK1")) {
                        if (stageId.equals("Stage1")) {
                            kodDokumen.setKod("SUT");
                        } else if (stageId.equals("Stage8")) {
                            kodDokumen.setKod("SMM");
                        } else if (stageId.equals("Stage10")) {
                            kodDokumen.setKod("SK");
                        } else if (stageId.equals("Stage11")) {
                            kodDokumen.setKod("SMM");
                        }
                    } else if (permohonan.getKodUrusan().getKod().equals("PMMK2")) {
                        if (stageId.equals("Stage1")) {
                            kodDokumen.setKod("SUT");
                        } else if (stageId.equals("Stage8")) {
                            kodDokumen.setKod("SK");
                        }
                    }

                    PermohonanTandatanganDokumen mohonTandatanganDokumenTemp = consentService.findMohonTandatanganDokomen(idPermohonan, kodDokumen.getKod());

                    if (mohonTandatanganDokumenTemp == null) {
                        infoAudit.setDimasukOleh(pengguna);
                        infoAudit.setTarikhMasuk(new java.util.Date());
                        mohonTandatanganDokumen.setPermohonan(permohonan);
                        mohonTandatanganDokumen.setCawangan(permohonan.getCawangan());
                        mohonTandatanganDokumen.setInfoAudit(infoAudit);
                        mohonTandatanganDokumen.setKodDokumen(kodDokumen);
                        mohonTandatanganDokumen.setDiTandatangan(mohonTandatanganDokumen.getDiTandatangan());
                        consentService.simpanMohonTandatanganDokumen(mohonTandatanganDokumen);

                    } else {
                        infoAudit = mohonTandatanganDokumenTemp.getInfoAudit();
                        infoAudit.setDiKemaskiniOleh(pengguna);
                        infoAudit.setTarikhKemaskini(new java.util.Date());
                        mohonTandatanganDokumenTemp.setInfoAudit(infoAudit);
                        mohonTandatanganDokumenTemp.setKodDokumen(kodDokumen);
                        mohonTandatanganDokumenTemp.setDiTandatangan(mohonTandatanganDokumen.getDiTandatangan());
                        consentService.simpanMohonTandatanganDokumen(mohonTandatanganDokumenTemp);
                    }

                    addSimpleMessage("Maklumat berjaya disimpan.");
                }
            }

        } else {
            if (mohonTandatanganDokumen == null) {
                addSimpleError("Sila pilih maklumat terlebih dahulu.");

            } else {
                if (mohonTandatanganDokumen.getDiTandatangan() == null) {
                    addSimpleError("Sila pilih maklumat terlebih dahulu.");
                } else {

                    idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
                    InfoAudit infoAudit = new InfoAudit();
                    KodDokumen kodDokumen = new KodDokumen();
                    kodDokumen.setKod("SK");

                    PermohonanTandatanganDokumen mohonTandatanganDokumenTemp = consentService.findMohonTandatanganDokomen(idPermohonan);

                    if (mohonTandatanganDokumenTemp == null) {
                        infoAudit.setDimasukOleh(pengguna);
                        infoAudit.setTarikhMasuk(new java.util.Date());
                        mohonTandatanganDokumen.setPermohonan(permohonan);
                        mohonTandatanganDokumen.setCawangan(permohonan.getCawangan());
                        mohonTandatanganDokumen.setInfoAudit(infoAudit);
                        mohonTandatanganDokumen.setKodDokumen(kodDokumen);
                        mohonTandatanganDokumen.setDiTandatangan(mohonTandatanganDokumen.getDiTandatangan());
                        consentService.simpanMohonTandatanganDokumen(mohonTandatanganDokumen);

                    } else {
                        infoAudit = mohonTandatanganDokumenTemp.getInfoAudit();
                        infoAudit.setDiKemaskiniOleh(pengguna);
                        infoAudit.setTarikhKemaskini(new java.util.Date());
                        mohonTandatanganDokumenTemp.setInfoAudit(infoAudit);
                        mohonTandatanganDokumenTemp.setKodDokumen(kodDokumen);
                        mohonTandatanganDokumenTemp.setDiTandatangan(mohonTandatanganDokumen.getDiTandatangan());
                        consentService.simpanMohonTandatanganDokumen(mohonTandatanganDokumenTemp);
                    }

                    addSimpleMessage("Maklumat berjaya disimpan.");
                }
            }
        }

        return new JSP("consent/tandatangan.jsp").addParameter("tab", "true");
    }

    public List<PenggunaPeranan> getSenaraiPenggunaPeranan(KodCawangan kod, String[] listKod) {
        try {
            String kumpBPEL = new String();
            int count = 1;
            for (String pu : listKod) {
                if (count == 1) {
                    kumpBPEL = "('";
                    kumpBPEL = kumpBPEL + pu;
                } else {
                    kumpBPEL = kumpBPEL + "','" + pu;
                }

                if (count == listKod.length) {
                    kumpBPEL = kumpBPEL + "')";
                }
                count++;
            }
            String query = "Select u.pengguna from etanah.model.PenggunaPeranan u where u.peranan.kumpBPEL in" + kumpBPEL + " and u.pengguna.kodCawangan.kod = :kod and u.pengguna.status ='A' order by u.pengguna.nama";
            LOG.info("QUERY->" + query);
            Session session = sessionProvider.get();
            Query q = session.createQuery(query);
            q.setString("kod", kod.getKod());
            return q.list();

        } finally {
        }
    }

    public List<PenggunaPeranan> getPenggunaPerananList() {
        return penggunaPerananList;
    }

    public void setPenggunaPerananList(List<PenggunaPeranan> penggunaPerananList) {
        this.penggunaPerananList = penggunaPerananList;
    }

    public PermohonanTandatanganDokumen getMohonTandatanganDokumen() {
        return mohonTandatanganDokumen;
    }

    public void setMohonTandatanganDokumen(PermohonanTandatanganDokumen mohonTandatanganDokumen) {
        this.mohonTandatanganDokumen = mohonTandatanganDokumen;
    }

    public String getStageId() {
        return stageId;
    }

    public void setStageId(String stageId) {
        this.stageId = stageId;
    }

    public PermohonanRujukanLuar getPermohonanRujukanLuar() {
        return permohonanRujukanLuar;
    }

    public void setPermohonanRujukanLuar(PermohonanRujukanLuar permohonanRujukanLuar) {
        this.permohonanRujukanLuar = permohonanRujukanLuar;
    }

    public String getTarikhTamat() {
        return tarikhTamat;
    }

    public void setTarikhTamat(String tarikhTamat) {
        this.tarikhTamat = tarikhTamat;
    }

    public Permohonan getPermohonan() {
        return permohonan;
    }

    public void setPermohonan(Permohonan permohonan) {
        this.permohonan = permohonan;
    }

    public String getTarikhTandatangan() {
        return tarikhTandatangan;
    }

    public void setTarikhTandatangan(String tarikhTandatangan) {
        this.tarikhTandatangan = tarikhTandatangan;
    }

    public PermohonanKertas getPermohonanKertas() {
        return permohonanKertas;
    }

    public void setPermohonanKertas(PermohonanKertas permohonanKertas) {
        this.permohonanKertas = permohonanKertas;
    }
}
