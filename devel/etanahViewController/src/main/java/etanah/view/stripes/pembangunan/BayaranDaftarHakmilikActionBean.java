package etanah.view.stripes.pembangunan;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;
import org.apache.log4j.Logger;
import etanah.view.etanahActionBeanContext;
import java.util.List;
import etanah.model.Permohonan;
import etanah.model.PermohonanTuntutanKos;
import etanah.model.InfoAudit;
import etanah.model.Pengguna;
import etanah.model.HakmilikPermohonan;
import etanah.dao.PermohonanDAO;
import etanah.dao.HakmilikDAO;
import etanah.model.KodCawangan;
import etanah.model.KodKadarBayaran;
//import etanah.model.KodTuntut;
import etanah.service.PembangunanService;
import java.math.BigDecimal;
//import java.math.BigInteger;
import etanah.dao.KodTuntutDAO;
import etanah.model.PermohonanTuntutan;
import etanah.model.PermohonanTuntutanButiran;
import etanah.dao.KodTransaksiTuntutDAO;
import etanah.dao.KodTransaksiDAO;
import etanah.model.Hakmilik;
import java.util.ArrayList;

/**
 *
 * @author nursyazwani
 */
@UrlBinding("/pembangunan/melaka/bayaranHakmilik")
public class BayaranDaftarHakmilikActionBean extends AbleActionBean {

    private static final Logger LOG = Logger.getLogger(BayaranDaftarHakmilikActionBean.class);
    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    HakmilikDAO hakmilikDAO;
    @Inject
    PembangunanService devServ;
    @Inject
    KodTuntutDAO kodTuntutDAO;
    @Inject
    KodTransaksiTuntutDAO kodTransaksiTuntutDAO;
    @Inject
    KodTransaksiDAO kodTransaksiDAO;
    @Inject
    etanah.Configuration conf;
    private Permohonan permohonan;
    private List<KodKadarBayaran> kodBayaranList;
    private double hmSementara = 0;
    private double hmTetap = 0;
    private double pelan = 0;
    private double total = 0;
    private double quantityHs = 0;
    private double quantityHt = 0;
    private double quantityPln = 0;
    private double amaunHs = 0;
    private double amaunHt = 0;
    private double amaunPln = 0;
    private List<PermohonanTuntutanKos> tkosList;
    private HakmilikPermohonan hp;
    private char milik;

    @DefaultHandler
    public Resolution showForm() {
        LOG.info("showForm");
        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        return new JSP("pembangunan/melaka/bayaran_daftar_hakmilik.jsp").addParameter("tab", "true");
    }

    public Resolution showForm2() {
        return new JSP("pembangunan/melaka/bayaran_daftar_hakmilik.jsp").addParameter("tab", "true");
    }

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {
        LOG.info("rehydrate start");
        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = permohonanDAO.findById(idPermohonan);
        hp = permohonan.getSenaraiHakmilik().get(0);
        Hakmilik hak = hakmilikDAO.findById(hp.getHakmilik().getIdHakmilik());

        // for NS
        if ((hp.getKodHakmilikSementara() != null)
                && (hp.getKodHakmilikSementara().getKod().equals("PN") || hp.getKodHakmilikSementara().getKod().equals("GRN") || hp.getKodHakmilikSementara().getKod().equals("HSD")
                || hp.getKodHakmilikSementara().getKod().equals("PM") || hp.getKodHakmilikSementara().getKod().equals("GM") || hp.getKodHakmilikSementara().getKod().equals("HSM"))) {

            if (hp.getKodHakmilikSementara().getKod().equals("PN") || hp.getKodHakmilikSementara().getKod().equals("GRN") || hp.getKodHakmilikSementara().getKod().equals("HSD")) {
                milik = 'T';
            }
            if (hp.getKodHakmilikSementara().getKod().equals("PM") || hp.getKodHakmilikSementara().getKod().equals("GM") || hp.getKodHakmilikSementara().getKod().equals("HSM")) {
                milik = 'Y';
            }
        } else {
            // for MELAKA
            if (hak.getKodHakmilik().getKod().equals("PN") || hak.getKodHakmilik().getKod().equals("GRN") || hak.getKodHakmilik().getKod().equals("HSD")) {
                milik = 'T';
            }
            if (hak.getKodHakmilik().getKod().equals("PM") || hak.getKodHakmilik().getKod().equals("GM") || hak.getKodHakmilik().getKod().equals("HSM") || hak.getKodHakmilik().getKod().equals("GMM") || hak.getKodHakmilik().getKod().equals("HMM")) {
                milik = 'Y';
            }
        }

        LOG.info("-------milik--------:" + milik);
        kodBayaranList = devServ.findKodByrByUrusan(permohonan.getKodUrusan().getKod());
        for (KodKadarBayaran kb : kodBayaranList) {
            if (kb.getKategori().equals("BPHS") && kb.getMilikDaerah() == milik) {
                hmSementara = kb.getKadar().doubleValue();
            }
            if (kb.getKategori().equals("BPHT") && kb.getMilikDaerah() == milik) {
                hmTetap = kb.getKadar().doubleValue();
            }
            if (kb.getKategori().equals("BPLN") && kb.getMilikDaerah() == milik) {
                pelan = kb.getKadar().doubleValue();
            }
        }


        tkosList = devServ.findTuntutByIdMohon(idPermohonan);
        System.out.println("tuntut list:" + tkosList);
        if (tkosList != null) {
            for (PermohonanTuntutanKos tk : tkosList) {
                if (!tk.getKodTuntut().getKod().isEmpty()) {
                    if (tk.getKodTuntut().getKod().equals("DEV01")) {
                        if (tk.getKuantiti() != null) {
                            quantityHs = tk.getKuantiti().doubleValue();
                        }
                        amaunHs = tk.getAmaunTuntutan().doubleValue();

                    } else if (tk.getKodTuntut().getKod().equals("DEV02")) {
                        if (tk.getKuantiti() != null) {
                            quantityHt = tk.getKuantiti().doubleValue();
                        }
                        amaunHt = tk.getAmaunTuntutan().doubleValue();
                    } else if (tk.getKodTuntut().getKod().equals("DEV14")) {
                        if (tk.getKuantiti() != null) {
                            quantityPln = tk.getKuantiti().doubleValue();
                        }
                        amaunPln = tk.getAmaunTuntutan().doubleValue();
                    }
                } else {
                    amaunHs = 0;
                    amaunHt = 0;
                    amaunPln = 0;
                    quantityHs = 0;
                    quantityHt = 0;
                    quantityPln = 0;
                }
            }
            total = amaunHs + amaunHt + amaunPln;
        }
        LOG.info("rehydrate finish");
    }

    public Resolution simpan() {
        LOG.info("simpan start");
        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        Pengguna pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        permohonan = permohonanDAO.findById(idPermohonan);
        KodCawangan caw = pengguna.getKodCawangan();
        InfoAudit infoAudit = new InfoAudit();
        infoAudit.setDimasukOleh(pengguna);
        infoAudit.setTarikhMasuk(new java.util.Date());

        PermohonanTuntutan pt = new PermohonanTuntutan();
        pt.setInfoAudit(infoAudit);
        pt.setPermohonan(permohonan);
        pt.setKodTransaksiTuntut(kodTransaksiTuntutDAO.findById("DEVH01"));
        pt.setCawangan(caw);
        pt.setTarikhTuntutan(new java.util.Date());
        devServ.simpanPermohonanTuntutan(pt);

        amaunHs = hmSementara * quantityHs;
        amaunHt = hmTetap * quantityHt;
        amaunPln = pelan * quantityPln;
        total = amaunHs + amaunHt + amaunPln;
        String[] itemList = {"Hakmilik Sementara", "Hakmilik Tetap", "Pelan"};
//        Integer[] qttList = {(int)quantityHs, (int)quantityHt, (int)quantityPln};
//        BigDecimal[] amaunList = {new BigDecimal(amaunHs), new BigDecimal(amaunHt), new BigDecimal(amaunPln)};
        List<Integer> qttList = new ArrayList<Integer>();
        qttList.add((int) quantityHs);
        qttList.add((int) quantityHt);
        qttList.add((int) quantityPln);
        LOG.info("---qttList----:" + qttList);
        List<BigDecimal> amaunList = new ArrayList<BigDecimal>();
        amaunList.add(new BigDecimal(amaunHs));
        amaunList.add(new BigDecimal(amaunHt));
        amaunList.add(new BigDecimal(amaunPln));
        LOG.info("---amaunList----:" + amaunList);
        String[] kodTuntut = {"DEV01", "DEV02", "DEV14"};
        String[] kodKewTrans = {"72489", "72490", "73151"};
        List<PermohonanTuntutanKos> tkosList1 = new ArrayList<PermohonanTuntutanKos>();
        tkosList1 = devServ.findTuntutByKodTuntut(idPermohonan);

        if (tkosList1.isEmpty()) {
            for (int i = 0; i < 3; i++) {
                LOG.info("saving loop");
                PermohonanTuntutanKos ptk = new PermohonanTuntutanKos();
                ptk.setPermohonan(permohonan);
                ptk.setInfoAudit(infoAudit);
                ptk.setCawangan(caw);
                ptk.setItem(itemList[i]);
                ptk.setAmaunTuntutan(amaunList.get(i));
                ptk.setKuantiti(qttList.get(i));
                ptk.setKodTuntut(kodTuntutDAO.findById(kodTuntut[i]));
                ptk.setKodTransaksi(kodTransaksiDAO.findById(kodKewTrans[i]));
                devServ.simpanPermohonanTuntutanKos(ptk);
                PermohonanTuntutanButiran tb = new PermohonanTuntutanButiran();
                tb.setInfoAudit(infoAudit);
                tb.setPermohonanTuntutan(pt);
                tb.setPermohonanTuntutanKos(ptk);
                devServ.simpanPermohonanTuntutanButiran(tb);
            }
        } else {
            int i = 0;
            for (PermohonanTuntutanKos tk : tkosList1) {
                if (!tk.getKodTuntut().getKod().isEmpty()) {
                    LOG.info("---tk.getKodTuntut().getKod()----:" + tk.getKodTuntut().getKod());
                    LOG.info("---qttList.get(i)----:" + qttList.get(i));
                    infoAudit = tk.getInfoAudit();
                    infoAudit.setTarikhKemaskini(new java.util.Date());
                    infoAudit.setDiKemaskiniOleh(pengguna);
                    tk.setInfoAudit(infoAudit);
                    tk.setAmaunTuntutan(amaunList.get(i));
                    tk.setKuantiti(qttList.get(i));
                    devServ.simpanPermohonanTuntutanKos(tk);
                }
                i++;
            }

        }
        LOG.info("simpan fnish");
        addSimpleMessage("Maklumat telah berjaya disimpan.");
        return new ForwardResolution("/WEB-INF/jsp/pembangunan/melaka/bayaran_daftar_hakmilik.jsp").addParameter("tab", "true");
    }

    public Permohonan getPermohonan() {
        return permohonan;
    }

    public void setPermohonan(Permohonan permohonan) {
        this.permohonan = permohonan;
    }

    public List<KodKadarBayaran> getKodBayaranList() {
        return kodBayaranList;
    }

    public void setKodBayaranList(List<KodKadarBayaran> kodBayaranList) {
        this.kodBayaranList = kodBayaranList;
    }

    public List<PermohonanTuntutanKos> getTkosList() {
        return tkosList;
    }

    public void setTkosList(List<PermohonanTuntutanKos> tkosList) {
        this.tkosList = tkosList;
    }

    public double getAmaunHs() {
        return amaunHs;
    }

    public void setAmaunHs(double amaunHs) {
        this.amaunHs = amaunHs;
    }

    public double getAmaunHt() {
        return amaunHt;
    }

    public void setAmaunHt(double amaunHt) {
        this.amaunHt = amaunHt;
    }

    public double getAmaunPln() {
        return amaunPln;
    }

    public void setAmaunPln(double amaunPln) {
        this.amaunPln = amaunPln;
    }

    public double getHmSementara() {
        return hmSementara;
    }

    public void setHmSementara(double hmSementara) {
        this.hmSementara = hmSementara;
    }

    public double getHmTetap() {
        return hmTetap;
    }

    public void setHmTetap(double hmTetap) {
        this.hmTetap = hmTetap;
    }

    public double getPelan() {
        return pelan;
    }

    public void setPelan(double pelan) {
        this.pelan = pelan;
    }

    public double getQuantityHs() {
        return quantityHs;
    }

    public void setQuantityHs(double quantityHs) {
        this.quantityHs = quantityHs;
    }

    public double getQuantityHt() {
        return quantityHt;
    }

    public void setQuantityHt(double quantityHt) {
        this.quantityHt = quantityHt;
    }

    public double getQuantityPln() {
        return quantityPln;
    }

    public void setQuantityPln(double quantityPln) {
        this.quantityPln = quantityPln;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public HakmilikPermohonan getHp() {
        return hp;
    }

    public void setHp(HakmilikPermohonan hp) {
        this.hp = hp;
    }

    public char getMilik() {
        return milik;
    }

    public void setMilik(char milik) {
        this.milik = milik;
    }
}
