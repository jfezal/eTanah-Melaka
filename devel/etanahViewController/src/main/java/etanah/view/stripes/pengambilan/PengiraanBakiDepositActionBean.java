/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.stripes.pengambilan;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.dao.PermohonanDAO;
import etanah.model.Akaun;
import etanah.model.FasaPermohonan;
import etanah.model.Hakmilik;
import etanah.model.HakmilikPermohonan;
import etanah.model.HakmilikPihakBerkepentingan;
import etanah.model.InfoAudit;
import etanah.model.KodKeputusan;
import etanah.model.KodStatusAkaun;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.model.PermohonanPihak;
import etanah.model.PermohonanTuntutanKos;
import etanah.service.PengambilanService;
import etanah.service.PengambilanService1;
import etanah.view.etanahActionBeanContext;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;

/**
 *
 * @author Rajesh
 */
@UrlBinding("/pengambilan/pengiraan_baki_deposit")
public class PengiraanBakiDepositActionBean extends AbleActionBean {

    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    PengambilanService pengambilanService;
    @Inject
    PengambilanService1 pengambilanService1;
    @Inject
    etanah.Configuration conf;
    private String idSblm;
    private Permohonan permohonan;
    private List<HakmilikPihakBerkepentingan> senaraiPihakBerkepentingan;
    private BigDecimal jumCaraBayar1 = new BigDecimal(0.00);
    private BigDecimal amaunSebenarTotal = new BigDecimal(0.00);
    private BigDecimal deposit = new BigDecimal(0.00);
    private BigDecimal pampasan = new BigDecimal(0.00);
    private BigDecimal baki = new BigDecimal(0.00);
    private BigDecimal balance = new BigDecimal(0.00);

    @DefaultHandler
    public Resolution showForm() {
        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");

        if (idPermohonan != null) {
            permohonan = permohonanDAO.findById(idPermohonan);
            FasaPermohonan fasaPermohonan = pengambilanService1.findFasaPermohonan_KeputusanNIDAliran(idPermohonan, "14TentukanKos");
            FasaPermohonan fasaPermohonan2 = pengambilanService1.findFasaPermohonan_KeputusanNIDAliran(idPermohonan, "16KeputusanBicara");
            if (fasaPermohonan != null) {
                if (fasaPermohonan.getKeputusan() != null) {
                    KodKeputusan kodKeputusan = fasaPermohonan.getKeputusan();
                    if (kodKeputusan != null) {
                        if (!kodKeputusan.getKod().equals("1B")) {
                            addSimpleError("Keputusan Bukan Gantirugi 3(1)(b)(c)");
                            getContext().getRequest().setAttribute("form1", Boolean.FALSE);
                        } else {
                            getContext().getRequest().setAttribute("form1", Boolean.TRUE);
                        }
                    }
                } else {
                    addSimpleError("Sila Buat Keputusan Terlebih Dahulu");
                    getContext().getRequest().setAttribute("form1", Boolean.FALSE);
                }
            }
            if (fasaPermohonan2 != null) {
                if (fasaPermohonan2.getKeputusan() != null) {
                    KodKeputusan kodKeputusan = fasaPermohonan2.getKeputusan();
                    if (kodKeputusan != null) {
                        if (!kodKeputusan.getKod().equals("1B")) {
                            addSimpleError("Keputusan Bukan Gantirugi 3(1)(b)(c)");
                            getContext().getRequest().setAttribute("form1", Boolean.FALSE);
                        } else {
                            getContext().getRequest().setAttribute("form1", Boolean.TRUE);
                        }
                    }
                } else {
                    addSimpleError("Sila Buat Keputusan Terlebih Dahulu");
                    getContext().getRequest().setAttribute("form1", Boolean.FALSE);
                }
            }

        }
        return new JSP("pengambilan/negerisembilan/penarikanbalik/Pengiraan_Baki_Deposit.jsp").addParameter("tab", "true");
    }

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {
        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = permohonanDAO.findById(idPermohonan);
        List<HakmilikPermohonan> senaraiHakmilikPermohonan = new ArrayList<HakmilikPermohonan>();
        List<PermohonanTuntutanKos> permohonanTuntutanKosList = new ArrayList<PermohonanTuntutanKos>();
        List<PermohonanTuntutanKos> permohonanTuntutanKosList1 = new ArrayList<PermohonanTuntutanKos>();

        if (permohonan != null) {
            senaraiHakmilikPermohonan = permohonan.getSenaraiHakmilik();
            if (senaraiHakmilikPermohonan != null && senaraiHakmilikPermohonan.size() > 0) {
                deposit = BigDecimal.ZERO;
                pampasan = BigDecimal.ZERO;
                jumCaraBayar1 = BigDecimal.ZERO;

                permohonanTuntutanKosList1 = pengambilanService.findMohonTuntutKosByIdPermohonan(permohonan.getPermohonanSebelum().getIdPermohonan(), "11304");
                for (PermohonanTuntutanKos permohonanTuntutKos : permohonanTuntutanKosList1) {
                    if (permohonanTuntutKos.getAmaunTuntutan() != null) {
                        jumCaraBayar1 = permohonanTuntutKos.getAmaunTuntutan().add(jumCaraBayar1);
                    }
                }
                for (HakmilikPermohonan hakmilikPermohonan : senaraiHakmilikPermohonan) {
                    Hakmilik hakmilik = hakmilikPermohonan.getHakmilik();
                    senaraiPihakBerkepentingan = hakmilik.getSenaraiPihakBerkepentingan();

                    BigDecimal amaunSebenarTuanTotal = BigDecimal.ZERO;

                    if (senaraiPihakBerkepentingan != null && senaraiPihakBerkepentingan.size() > 0) {
                        for (HakmilikPihakBerkepentingan hakmilikPihakBerkepentingan : senaraiPihakBerkepentingan) {
                            PermohonanPihak permohonanPihak = pengambilanService.getSenaraiPmohonPihakByIdHakmilikIdPihak(idPermohonan, hakmilik.getIdHakmilik(), hakmilikPihakBerkepentingan.getPihak().getIdPihak());
                            if (permohonanPihak != null) {
                                permohonanTuntutanKosList = pengambilanService.findByIdPermohonanPihak2(permohonanPihak.getIdPermohonanPihak());

                                amaunSebenarTotal = BigDecimal.ZERO;

                                for (PermohonanTuntutanKos permohonanTuntutanKos : permohonanTuntutanKosList) {
                                    if (permohonanTuntutanKos.getAmaunSebenar() != null) {
                                        amaunSebenarTotal = permohonanTuntutanKos.getAmaunSebenar().add(amaunSebenarTotal);
                                    }
                                }
                                amaunSebenarTuanTotal = amaunSebenarTuanTotal.add(amaunSebenarTotal);
                            }//if
                        }
                    }
                    pampasan = pampasan.add(amaunSebenarTuanTotal);
                }//for
                deposit = jumCaraBayar1;
                baki = deposit.subtract(pampasan);
                Akaun akaun = pengambilanService.findAkaunByIdMohon(idPermohonan);
                if ("05".equals(conf.getProperty("kodNegeri"))) {
                    if (!baki.subtract(akaun.getBaki()).equals(balance)) {
                        addSimpleMessage("Sila klik butang 'Integrate with Hasil' untuk proses kemaskini deposit.");
                    }
                }
            }
        }//if
    }

    public Resolution hasil() {
        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        System.out.println("id mohon " + idPermohonan);
        Pengguna pguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        if (idPermohonan != null) {
            permohonan = permohonanDAO.findById(idPermohonan);
            Akaun akaun = pengambilanService.findAkaunByIdMohon(idPermohonan);
            if (akaun != null) {
                akaun.setBaki(baki);
                InfoAudit info = new InfoAudit();
                info.setDiKemaskiniOleh(pguna);
                info.setTarikhKemaskini(new java.util.Date());
                akaun.setInfoAudit(info);
                if (baki.equals(0)) {
                    KodStatusAkaun kodStatusAkaun = pengambilanService.findKodStatusAkaun("B");
                    akaun.setStatus(kodStatusAkaun);
                }
                pengambilanService.simpanAkaun(akaun);
                System.out.println("simpan berjaya");
            }


        }
//        rehydrate();
        addSimpleMessage("Maklumat deposit telah berjaya dikemaskini");
        return new JSP("pengambilan/negerisembilan/penarikanbalik/Pengiraan_Baki_Deposit.jsp").addParameter("tab", "true");
    }

    public BigDecimal getDeposit() {
        return deposit;
    }

    public void setDeposit(BigDecimal deposit) {
        this.deposit = deposit;
    }

    public BigDecimal getPampasan() {
        return pampasan;
    }

    public void setPampasan(BigDecimal pampasan) {
        this.pampasan = pampasan;
    }

    public BigDecimal getBaki() {
        return baki;
    }

    public void setBaki(BigDecimal baki) {
        this.baki = baki;
    }
}
