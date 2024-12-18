/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.stripes.pelupusan;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import java.util.List;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.controller.LifecycleStage;
import net.sourceforge.stripes.action.RedirectResolution;
import etanah.service.PembangunanService;
import etanah.model.Permohonan;
import etanah.model.Hakmilik;
import etanah.model.HakmilikPermohonan;
import etanah.dao.HakmilikDAO;
import etanah.dao.KodBandarPekanMukimDAO;
import etanah.dao.KodLotDAO;
import etanah.dao.KodPemilikanDAO;
import etanah.dao.PermohonanDAO;
import etanah.model.InfoAudit;
import etanah.model.KodPemilikan;
import etanah.model.NoPt;
import etanah.model.SiriNoPt;
import etanah.model.Pengguna;
import etanah.model.PermohonanKelompok;
import etanah.model.ambil.PermohonanPengambilan;
import etanah.model.PermohonanPlotPelan;
import etanah.model.PermohonanUkur;
import etanah.service.BPelService;
import etanah.service.PelupusanService;
import etanah.view.etanahActionBeanContext;
import etanah.view.stripes.pelupusan.disClass.DisLaporanTanahService;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.ArrayList;
import oracle.bpel.services.workflow.task.model.Task;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

/**
 *
 * @author Afham
 */
@UrlBinding("/pelupusan/daftar_no_ptV2")
public class DaftarNoPTActionBeanV2 extends AbleActionBean {

    private static final Logger LOG = Logger.getLogger(DaftarNoPTActionBeanV2.class);
    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    DisLaporanTanahService disLaporanTanahService;
    @Inject
    HakmilikDAO hakmilikDAO;
    @Inject
    PelupusanService pelupusanService;
    @Inject
    KodBandarPekanMukimDAO kodBandarPekanMukimDAO;
    @Inject
    KodLotDAO kodLotDAO;
    private HakmilikPermohonan hakmilikPermohonan;
    private Permohonan permohonan;
    private Pengguna pengguna;
    private NoPt noPT;
    private List<HakmilikPermohonan> hakmilikPermohonanList;
    private List<NoPt> noPTList;
    private String idPermohonan;
    private int sizeHakmilikPermohonan;
    private boolean daftarPT;
    private List<PermohonanKelompok> senaraiKelompok;
    private boolean kelompok;

    @DefaultHandler
    public Resolution showDaftarPTSementara() {
        setDaftarPT(false);
        return new JSP("pelupusan/common/daftar_no_ptV2.jsp").addParameter("tab", "true");
    }

    public Resolution showDaftarPT() {
        setDaftarPT(true);
        return new JSP("pelupusan/common/daftar_no_ptV2.jsp").addParameter("tab", "true");
    }

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {
        pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = permohonanDAO.findById(idPermohonan);
        senaraiKelompok = disLaporanTanahService.getPelupusanService().findMohonKelompokByIdMohonIndukJenisKelompok(permohonan.getIdPermohonan(), "K");
        if (senaraiKelompok.size() > 0) {
            copyPTSementaraToIndividu();
            kelompok = true;
            noPTList = new ArrayList();
            noPTList.clear();
            for (PermohonanKelompok pk : senaraiKelompok) {
                Permohonan p = pk.getPermohonan();
                if (p != null) {
                    hakmilikPermohonanList = new ArrayList<HakmilikPermohonan>();
                    hakmilikPermohonanList = p.getSenaraiHakmilik();

                    for (HakmilikPermohonan hm : hakmilikPermohonanList) {
                        if (hm.getKeputusan().getKod().equals("L")) {
                            NoPt noPT = new NoPt();
                            noPT = (NoPt) disLaporanTanahService.findObject(noPT, new String[]{String.valueOf(hm.getId())}, 0);
                            if (noPT != null) {
                                if (!StringUtils.isEmpty(noPT.getIdPt().toString())) {
                                    noPTList.add(noPT);
                                }
                            }
                        }
                    }
                }
            }
            if (noPTList.size() > 0) {
                sizeHakmilikPermohonan = noPTList.size();
            }
        } else {
            hakmilikPermohonanList = new ArrayList<HakmilikPermohonan>();
            hakmilikPermohonanList = permohonan.getSenaraiHakmilik();
            if (hakmilikPermohonanList.size() > 0) {
                sizeHakmilikPermohonan = hakmilikPermohonanList.size();
            }
            noPTList = new ArrayList();
            noPTList.clear();
            for (HakmilikPermohonan hm : hakmilikPermohonanList) {
                NoPt noPT = new NoPt();
                noPT = (NoPt) disLaporanTanahService.findObject(noPT, new String[]{String.valueOf(hm.getId())}, 0);
                if (noPT != null) {
                    if (!StringUtils.isEmpty(noPT.getIdPt().toString())) {
                        noPTList.add(noPT);
                    }
                }
            }
        }

    }

    public Resolution daftarNoPTSementara() {
        hakmilikPermohonanList = permohonan.getSenaraiHakmilik();
        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        InfoAudit info = new InfoAudit();
        info.setDimasukOleh(peng);
        info.setTarikhMasuk(new java.util.Date());
        boolean check = false;
        if (hakmilikPermohonanList.size() > 0) {

            int index = 0;
            //Checking
            for (HakmilikPermohonan hp : hakmilikPermohonanList) {
                noPT = pelupusanService.findNoPtByIdHakmilikPermohonan(hp.getId());
                if (noPT != null) {
                    check = true;
                } else {
                    noPT = new NoPt();
                    index++;
                    noPT.setInfoAudit(info);
                    if (hp.getHakmilik() != null) {
                        noPT.setKodBpm(kodBandarPekanMukimDAO.findById(hp.getHakmilik().getBandarPekanMukim().getKod()));
                    } else {
                        noPT.setKodBpm(kodBandarPekanMukimDAO.findById(hp.getBandarPekanMukimBaru().getKod()));
                    }
                    noPT.setHakmilikPermohonan(hp);
                    NoPt maxNoPt = new NoPt();
                    NoPt maxNoPtSementara = new NoPt();
                    maxNoPt = pelupusanService.getMaxOfNoPT(hp.getBandarPekanMukimBaru().getKod());
                    maxNoPtSementara = pelupusanService.getMaxOfNoPTSementara(hp.getBandarPekanMukimBaru().getKod());
                    int no1 = 0;
                    String noPtSementara = null;
                    Long checkNoPtSementara = maxNoPtSementara != null && maxNoPtSementara.getNoPtSementara() != null ? maxNoPtSementara.getNoPtSementara() : 0;
                    if (!checkNoPtSementara.toString().equals("0")) {
                        if (maxNoPt.getNoPt() > maxNoPtSementara.getNoPtSementara()) {
                            no1 = Integer.parseInt(maxNoPt.getNoPt().toString()) + 1;
                            noPtSementara = no1 + "";
                            noPT.setNoPtSementara(Long.parseLong(noPtSementara));
                        } else {
                            no1 = Integer.parseInt(maxNoPtSementara.getNoPtSementara().toString()) + 1;
                            noPtSementara = no1 + "";
                            noPT.setNoPtSementara(Long.parseLong(noPtSementara));
                        }
                    } else {
                        no1 = Integer.parseInt(maxNoPt.getNoPt().toString()) + 1;
                        noPtSementara = no1 + "";
                        noPT.setNoPtSementara(Long.parseLong(noPtSementara));
                    }
                    pelupusanService.simpanNoPt(noPT);
//                    hp.setNoLot(noPtSementara);
//                    hp.setLot(kodLotDAO.findById("2")); Hold off for sementara
//                    pelupusanService.saveOrUpdate(hp);
                }
            }
        }

        rehydrate();
        addSimpleMessage("No PT Sementara telah daftar");
        return new JSP("pelupusan/common/daftar_no_ptV2.jsp").addParameter("tab", "true");
    }

    public Resolution daftarNoPT() {
        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        String item = getContext().getRequest().getParameter("item");
        String[] listHakmilikPermohonan = item.split(",");
        LOG.info("Size :" + listHakmilikPermohonan.length);
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = permohonanDAO.findById(idPermohonan);
        senaraiKelompok = disLaporanTanahService.getPelupusanService().findMohonKelompokByIdMohonIndukJenisKelompok(permohonan.getIdPermohonan(), "K");
        InfoAudit info = new InfoAudit();
        HakmilikPermohonan hp = new HakmilikPermohonan();
        if (listHakmilikPermohonan.length > 0) {
            for (int i = 0; i < listHakmilikPermohonan.length; i++) {
                noPT = pelupusanService.findNoPtByIdHakmilikPermohonan(Long.valueOf(listHakmilikPermohonan[i]));
                hakmilikPermohonan = disLaporanTanahService.getHakmilikPermohonanDAO().findById(Long.valueOf(listHakmilikPermohonan[i]));
                if (noPT != null) {
                    info = noPT.getInfoAudit();
                    info.setDiKemaskiniOleh(peng);
                    info.setTarikhKemaskini(new java.util.Date());
                    noPT.setInfoAudit(info);
                    noPT.setNoPt(noPT.getNoPtSementara());
                    pelupusanService.simpanNoPt(noPT);

                    if (hakmilikPermohonan != null) {
                        if (hakmilikPermohonan.getNoLot() == null) {
                            String zeroPad = new String();
                            zeroPad = String.valueOf(noPT.getNoPtSementara());
                            int count = 7;
                            int sizePad = zeroPad.length();
                            while (sizePad < count) {
                                zeroPad = "0" + zeroPad;
                                sizePad++;
                            }
                            hakmilikPermohonan.setNoLot(zeroPad);
                        }
                        if (senaraiKelompok.size() > 0) {
                            hakmilikPermohonan.setLot(kodLotDAO.findById("3"));
                        } else {
                            if (hakmilikPermohonan.getLot() == null) {
                                hakmilikPermohonan.setLot(kodLotDAO.findById("3"));
                            }
                        }
                        pelupusanService.saveOrUpdate(hakmilikPermohonan);
                    }

                }
            }
        }
        setDaftarPT(true);
        addSimpleMessage("No PT telah daftar");
        return new JSP("pelupusan/common/daftar_no_ptV2.jsp").addParameter("tab", "true");
    }

    public void copyPTSementaraToIndividu() {
        LOG.info("Copy data to individu jika perlu");
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        senaraiKelompok = disLaporanTanahService.getPelupusanService().findMohonKelompokByIdMohonIndukJenisKelompok(permohonan.getIdPermohonan(), "K");
        permohonan = permohonanDAO.findById(idPermohonan);
        if (senaraiKelompok.size() > 0) {
            for (PermohonanKelompok pk : senaraiKelompok) {
                Permohonan p = pk.getPermohonan();
                if (p != null) { //Copy individu
                    hakmilikPermohonanList = new ArrayList<HakmilikPermohonan>();
                    hakmilikPermohonanList = p.getSenaraiHakmilik();
                    pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
                    InfoAudit info = new InfoAudit();
                    info.setDimasukOleh(pengguna);
                    info.setTarikhMasuk(new java.util.Date());
                    boolean check = false;
                    if (hakmilikPermohonanList.size() > 0) {

                        int index = 0;
                        //Checking
                        for (HakmilikPermohonan hp : hakmilikPermohonanList) {
                            if (hp.getKeputusan() != null && hp.getKeputusan().getKod().equals("L")) {
                                noPT = pelupusanService.findNoPtByIdHakmilikPermohonan(hp.getId());
                                if (noPT != null) {
                                    check = true;
                                } else {
                                    noPT = new NoPt();
                                    index++;
                                    noPT.setInfoAudit(info);
                                    if (hp.getHakmilik() != null) {
                                        noPT.setKodBpm(kodBandarPekanMukimDAO.findById(hp.getHakmilik().getBandarPekanMukim().getKod()));
                                    } else {
                                        noPT.setKodBpm(kodBandarPekanMukimDAO.findById(hp.getBandarPekanMukimBaru().getKod()));
                                    }
                                    noPT.setHakmilikPermohonan(hp);
                                    NoPt maxNoPt = new NoPt();
                                    NoPt maxNoPtSementara = new NoPt();
                                    maxNoPt = pelupusanService.getMaxOfNoPT(hp.getBandarPekanMukimBaru().getKod());
                                    maxNoPtSementara = pelupusanService.getMaxOfNoPTSementara(hp.getBandarPekanMukimBaru().getKod());
                                    int no1 = 0;
                                    String noPtSementara = null;
                                    Long checkNoPtSementara = maxNoPtSementara != null && maxNoPtSementara.getNoPtSementara() != null ? maxNoPtSementara.getNoPtSementara() : 0;
                                    if (!checkNoPtSementara.toString().equals("0")) {
                                        if (maxNoPt.getNoPt() > maxNoPtSementara.getNoPtSementara()) {
                                            no1 = Integer.parseInt(maxNoPt.getNoPt().toString()) + 1;
                                            noPtSementara = no1 + "";
                                            noPT.setNoPtSementara(Long.parseLong(noPtSementara));
                                        } else {
                                            no1 = Integer.parseInt(maxNoPtSementara.getNoPtSementara().toString()) + 1;
                                            noPtSementara = no1 + "";
                                            noPT.setNoPtSementara(Long.parseLong(noPtSementara));
                                        }
                                    } else {
                                        no1 = Integer.parseInt(maxNoPt.getNoPt().toString()) + 1;
                                        noPtSementara = no1 + "";
                                        noPT.setNoPtSementara(Long.parseLong(noPtSementara));
                                    }
                                    pelupusanService.simpanNoPt(noPT);
                                }
                            }
                        }
                    }
                }
            }
        }
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

    public String getIdPermohonan() {
        return idPermohonan;
    }

    public void setIdPermohonan(String idPermohonan) {
        this.idPermohonan = idPermohonan;
    }

    public NoPt getNoPT() {
        return noPT;
    }

    public void setNoPT(NoPt noPT) {
        this.noPT = noPT;
    }

    public List<NoPt> getNoPTList() {
        return noPTList;
    }

    public void setNoPTList(List<NoPt> noPTList) {
        this.noPTList = noPTList;
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

    public boolean isDaftarPT() {
        return daftarPT;
    }

    public void setDaftarPT(boolean daftarPT) {
        this.daftarPT = daftarPT;
    }

    public int getSizeHakmilikPermohonan() {
        return sizeHakmilikPermohonan;
    }

    public void setSizeHakmilikPermohonan(int sizeHakmilikPermohonan) {
        this.sizeHakmilikPermohonan = sizeHakmilikPermohonan;
    }

    public boolean isKelompok() {
        return kelompok;
    }

    public void setKelompok(boolean kelompok) {
        this.kelompok = kelompok;
    }
}
