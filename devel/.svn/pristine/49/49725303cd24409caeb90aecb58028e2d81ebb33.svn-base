/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.stripes.pembangunan;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.dao.KodTuntutDAO;
import etanah.dao.PermohonanDAO;
import etanah.dao.PermohonanTuntutanKosDAO;
import etanah.model.Permohonan;
import etanah.model.PermohonanTuntutanKos;
import etanah.model.Pengguna;
import etanah.model.KodCawangan;
import etanah.model.InfoAudit;
import etanah.model.KodTransaksiTuntut;
import etanah.model.KodTuntut;
import etanah.model.PermohonanTuntutan;
import etanah.model.PermohonanTuntutanButiran;
import java.math.BigDecimal;
import etanah.view.etanahActionBeanContext;
import etanah.service.PembangunanService;
//import java.util.ArrayList;
import java.util.List;
//import java.util.logging.Level;
//import java.util.logging.Logger;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;
import org.apache.log4j.Logger;
import etanah.dao.KodTransaksiDAO;
import etanah.model.HakmilikPermohonan;
import etanah.model.PermohonanPlotPelan;
import java.math.BigInteger;
import org.hibernate.hql.classic.HavingParser;

/**
 *
 * @author NageswaraRao
 */
@UrlBinding("/pembangunan/notis7G_NS")
public class Notis7GNSActionBean extends AbleActionBean {

    private static final Logger LOG = Logger.getLogger(Notis7GActionBean.class);
    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    KodTuntutDAO kodTuntutDAO;
    @Inject
    PermohonanTuntutanKosDAO permohonanTuntutanKosDAO;
    @Inject
    PembangunanService pembangunanServ;
    @Inject
    etanah.Configuration conf;
    @Inject
    KodTransaksiDAO kodTransaksiDAO;
    private Permohonan permohonan;
    private String negeri;
    private PermohonanTuntutanKos permohonantuntutkos;
    private HakmilikPermohonan hakmilikPermohonan;
    private BigDecimal premium;
    private BigDecimal sumbangan;
    private BigDecimal penulisan;
    private BigDecimal sewaBaru;
    private BigDecimal bayaranUpahUkur;
    private BigDecimal tandaSempadan;
    private BigDecimal penyediaan;
    private BigDecimal pendaftaran;
    private BigDecimal semantara;
    private BigDecimal notis;
    private BigDecimal borang7c;
    private BigDecimal jumlah;
    private BigDecimal dendap;
    private String item;
    private List<PermohonanTuntutanKos> listtuntutankos;
    private String idPermohonan;
    private BigDecimal luas;
    private String kodluas;
    private String idHakmilik;

    @DefaultHandler
    public Resolution showForm() {
        return new JSP("pembangunan/pecahSempadan/notis_7G_NS.jsp").addParameter("tab", "true");
    }

    public Resolution showForm2() {
        return new JSP("pembangunan/common/cukai.jsp").addParameter("tab", "true");
    }

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {
        premium = BigDecimal.ZERO;
        sumbangan = BigDecimal.ZERO;
        penulisan = BigDecimal.ZERO;
        sewaBaru = BigDecimal.ZERO;
        bayaranUpahUkur = BigDecimal.ZERO;
        tandaSempadan = BigDecimal.ZERO;
        penyediaan = BigDecimal.ZERO;
        pendaftaran = BigDecimal.ZERO;
        semantara = BigDecimal.ZERO;
        notis = BigDecimal.ZERO;
        borang7c = BigDecimal.ZERO;
        dendap = BigDecimal.ZERO;
        jumlah = BigDecimal.ZERO;
        luas = BigDecimal.ZERO;


        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = permohonanDAO.findById(idPermohonan);
        List permohonanPlotPelanList = pembangunanServ.findPermohonanPlotPelanGroupByIdPermohonan(idPermohonan);
        if (permohonan.getKodUrusan().getKod().equals("TSPTG") || permohonan.getKodUrusan().getKod().equals("TSPTD") || permohonan.getKodUrusan().getKod().equals("TSMMK")) {
            idHakmilik = "";
            luas = null;
            kodluas = "";
        } else {

            if (permohonanPlotPelanList != null) {
                PermohonanPlotPelan permohonanPlotPelan = (PermohonanPlotPelan) permohonanPlotPelanList.get(0);
                idHakmilik = permohonanPlotPelan.getHakmilikPermohonan().getHakmilik().getIdHakmilik();
                kodluas = permohonanPlotPelan.getKodUnitLuas().getKod();

                for (int a = 0; a < permohonanPlotPelanList.size(); a++) {
                    PermohonanPlotPelan permohonanPlotPelan1 = (PermohonanPlotPelan) permohonanPlotPelanList.get(a);
                    BigDecimal luas1 = permohonanPlotPelan1.getLuas();
                    luas = luas.add(luas1);
                }
            }
        }
        permohonan = permohonanDAO.findById(idPermohonan);
        hakmilikPermohonan = pembangunanServ.findByIdPermohonan(idPermohonan);
        String[] tname = {"permohonan"};
        Object[] model = {permohonan};
//        listtuntutankos = permohonanTuntutanKosDAO.findByEqualCriterias(tname, model, null);

        listtuntutankos = pembangunanServ.findTuntutByIdMohon(idPermohonan);

        if (!(listtuntutankos.isEmpty())) {
            for (int i = 0; i < listtuntutankos.size(); i++) {
                permohonantuntutkos = new PermohonanTuntutanKos();
                permohonantuntutkos = listtuntutankos.get(i);
                if (permohonantuntutkos.getKodTuntut() != null) {

                    if (permohonantuntutkos.getKodTuntut().getKod().equals("DEV04")) {
                        premium = permohonantuntutkos.getAmaunTuntutan();
                    } else if (permohonantuntutkos.getKodTuntut().getKod().equals("DEV07")) {
                        sumbangan = permohonantuntutkos.getAmaunTuntutan();
                    } else if (permohonantuntutkos.getKodTuntut().getKod().equals("DEV17")) {
                        dendap = permohonantuntutkos.getAmaunTuntutan();
                    } else if (permohonantuntutkos.getKodTuntut().getKod().equals("DEV08")) {
                        penulisan = permohonantuntutkos.getAmaunTuntutan();
                    } else if (permohonantuntutkos.getKodTuntut().getKod().equals("DEV10")) {
                        sewaBaru = permohonantuntutkos.getAmaunTuntutan();
                    } else if (permohonantuntutkos.getKodTuntut().getKod().equals("DEV05")) {
                        bayaranUpahUkur = permohonantuntutkos.getAmaunTuntutan();
                    } else if (permohonantuntutkos.getKodTuntut().getKod().equals("DEV06")) {
                        tandaSempadan = permohonantuntutkos.getAmaunTuntutan();
                    } else if (permohonantuntutkos.getKodTuntut().getKod().equals("DEV13")) {
                        penyediaan = permohonantuntutkos.getAmaunTuntutan();
                    } else if (permohonantuntutkos.getKodTuntut().getKod().equals("DEV14")) {
                        pendaftaran = permohonantuntutkos.getAmaunTuntutan();
                    } else if (permohonantuntutkos.getKodTuntut().getKod().equals("DEV15")) {
                        semantara = permohonantuntutkos.getAmaunTuntutan();
                    } else if (permohonantuntutkos.getKodTuntut().getKod().equals("DEV09")) {
                        notis = permohonantuntutkos.getAmaunTuntutan();
                    } else if (permohonantuntutkos.getKodTuntut().getKod().equals("DEV12")) {
                        borang7c = permohonantuntutkos.getAmaunTuntutan();
                    }
                }
            }

            jumlah = premium.add(sumbangan).add(penulisan).add(sewaBaru).add(bayaranUpahUkur).add(tandaSempadan).add(penyediaan).add(pendaftaran).add(semantara).add(notis).add(borang7c).add(dendap);

        } else {

            if (hakmilikPermohonan.getDendaPremium() != null) {
                dendap = hakmilikPermohonan.getDendaPremium();
                jumlah = jumlah.add(dendap);
            }

            if (hakmilikPermohonan.getKeteranganCukaiBaru() != null) {
                sewaBaru = new BigDecimal(hakmilikPermohonan.getKeteranganCukaiBaru());
                jumlah = jumlah.add(sewaBaru);
            }

        }
    }

    public String getIdHakmilik() {
        return idHakmilik;
    }

    public void setIdHakmilik(String idHakmilik) {
        this.idHakmilik = idHakmilik;
    }

    public Resolution simpan() {
        Pengguna pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        KodCawangan caw = pengguna.getKodCawangan();
        InfoAudit ia = new InfoAudit();
        ia.setDimasukOleh(pengguna);
        ia.setTarikhMasuk(new java.util.Date());

        String cukai = (String) getContext().getRequest().getParameter("cukai");



        if (dendap == null) {
            dendap = new BigDecimal(0.00);
        }
        if (premium == null) {
            premium = new BigDecimal(0.00);
        }
        if (sumbangan == null) {
            sumbangan = new BigDecimal(0.00);
        }
        if (penulisan == null) {
            penulisan = new BigDecimal(0.00);
        }
        if (sewaBaru == null) {
            sewaBaru = new BigDecimal(0.00);
        }
        if (bayaranUpahUkur == null) {
            bayaranUpahUkur = new BigDecimal(0.00);
        }
        if (tandaSempadan == null) {
            tandaSempadan = new BigDecimal(0.00);
        }
        if (penyediaan == null) {
            penyediaan = new BigDecimal(0.00);
        }
        if (pendaftaran == null) {
            pendaftaran = new BigDecimal(0.00);
        }
        if (semantara == null) {
            semantara = new BigDecimal(0.00);
        }
        if (notis == null) {
            notis = new BigDecimal(0.00);
        }
        if (borang7c == null) {
            borang7c = new BigDecimal(0.00);
        }



        if (!(listtuntutankos.isEmpty()) && listtuntutankos.size() == 12) {
            for (int i = 0; i < listtuntutankos.size(); i++) {

                permohonantuntutkos = new PermohonanTuntutanKos();
                permohonantuntutkos = listtuntutankos.get(i);
                if (permohonantuntutkos.getKodTuntut() != null) {
                    if (permohonantuntutkos.getKodTuntut().getKod().equals("DEV04")) {
                        permohonantuntutkos.setAmaunTuntutan(premium);
                    } else if (permohonantuntutkos.getKodTuntut().getKod().equals("DEV17")) {
                        permohonantuntutkos.setAmaunTuntutan(dendap);
                    } else if (permohonantuntutkos.getKodTuntut().getKod().equals("DEV07")) {
                        permohonantuntutkos.setAmaunTuntutan(sumbangan);
                    } else if (permohonantuntutkos.getKodTuntut().getKod().equals("DEV08")) {
                        permohonantuntutkos.setAmaunTuntutan(penulisan);
                    } else if (permohonantuntutkos.getKodTuntut().getKod().equals("DEV10")) {
                        permohonantuntutkos.setAmaunTuntutan(sewaBaru);
                    } else if (permohonantuntutkos.getKodTuntut().getKod().equals("DEV05")) {
                        permohonantuntutkos.setAmaunTuntutan(bayaranUpahUkur);
                    } else if (permohonantuntutkos.getKodTuntut().getKod().equals("DEV06")) {
                        permohonantuntutkos.setAmaunTuntutan(tandaSempadan);
                    } else if (permohonantuntutkos.getKodTuntut().getKod().equals("DEV13")) {
                        permohonantuntutkos.setAmaunTuntutan(penyediaan);
                    } else if (permohonantuntutkos.getKodTuntut().getKod().equals("DEV14")) {
                        permohonantuntutkos.setAmaunTuntutan(pendaftaran);
                    } else if (permohonantuntutkos.getKodTuntut().getKod().equals("DEV15")) {
                        permohonantuntutkos.setAmaunTuntutan(semantara);
                    } else if (permohonantuntutkos.getKodTuntut().getKod().equals("DEV09")) {
                        permohonantuntutkos.setAmaunTuntutan(notis);
                    } else if (permohonantuntutkos.getKodTuntut().getKod().equals("DEV12")) {
                        permohonantuntutkos.setAmaunTuntutan(borang7c);
                    }

                    ia = permohonantuntutkos.getInfoAudit();
                    ia.setTarikhKemaskini(new java.util.Date());
                    ia.setDiKemaskiniOleh(pengguna);
                    permohonantuntutkos.setInfoAudit(ia);
                    permohonantuntutkos.setPermohonan(permohonan);
                    permohonantuntutkos.setCawangan(caw);
                    pembangunanServ.simpanPermohonanTuntutanKos(permohonantuntutkos);
                }

                // Added new Code
                PermohonanTuntutan permohonanTuntutan = new PermohonanTuntutan();
                permohonanTuntutan = pembangunanServ.findPermohonanTuntutanByKodTransName(idPermohonan, "Surat Tuntutan Bayaran Notis7G");
                if (permohonanTuntutan != null) {
                    ia = permohonanTuntutan.getInfoAudit();
                    ia.setTarikhKemaskini(new java.util.Date());
                    ia.setDiKemaskiniOleh(pengguna);
                    permohonanTuntutan.setInfoAudit(ia);
                    pembangunanServ.simpanPermohonanTuntutan(permohonanTuntutan);
                    // find MohonTuntutButir by MohonTuntut and MohonTuntutKos
                    PermohonanTuntutanButiran permohonanTuntutanButiran = new PermohonanTuntutanButiran();
                    permohonanTuntutanButiran = pembangunanServ.findPermohonanTuntutButirByIdTuntutAndIdKos(permohonantuntutkos.getIdKos(), permohonanTuntutan.getIdTuntut());
                    ia = permohonanTuntutanButiran.getInfoAudit();
                    ia.setTarikhKemaskini(new java.util.Date());
                    ia.setDiKemaskiniOleh(pengguna);
                    permohonanTuntutanButiran.setInfoAudit(ia);
                    pembangunanServ.simpanPermohonanTuntutanButiran(permohonanTuntutanButiran);
                }
            }
        } else {
            String[] itemList = {"DEV04", "DEV17", "DEV07", "DEV08", "DEV10", "DEV05", "DEV06", "DEV13", "DEV14", "DEV15", "DEV09", "DEV12"};
            BigDecimal[] amaunTuntutanList = {premium, dendap, sumbangan, penulisan, sewaBaru, bayaranUpahUkur, tandaSempadan, penyediaan, pendaftaran, semantara, notis, borang7c};
            String[] kodhasil = {"12107", "40040", "65096", "40040", "61403", "12108", "12109", "12112", "30010", "30080", "40040", "40040"};
            // added new code
            PermohonanTuntutan permohonanTuntutan = new PermohonanTuntutan();
            int i = 0;

            if (!(listtuntutankos.isEmpty()) && listtuntutankos.size() == 1) {
                i = 1;
                permohonanTuntutan = pembangunanServ.findPermohonanTuntutanByKodTransName(idPermohonan, "Surat Tuntutan Bayaran Notis7G");
            } else {
                KodTransaksiTuntut kodTransTuntut = new KodTransaksiTuntut();
                kodTransTuntut = pembangunanServ.findKodTransTuntutByName("Surat Tuntutan Bayaran Notis7G");
                permohonanTuntutan.setCawangan(caw);
                permohonanTuntutan.setPermohonan(permohonan);
                permohonanTuntutan.setInfoAudit(ia);
                permohonanTuntutan.setKodTransaksiTuntut(kodTransTuntut);
                permohonanTuntutan.setTarikhTuntutan(new java.util.Date());
                pembangunanServ.simpanPermohonanTuntutan(permohonanTuntutan);
            }

            while (i < itemList.length) {
                KodTuntut kodTuntut = new KodTuntut();
                //kodTuntut = pembangunanServ.findKodTuntutByName(itemList[i]);
                kodTuntut = kodTuntutDAO.findById(itemList[i]);
                LOG.debug("---------kodTuntut---------:" + kodTuntut);
                permohonantuntutkos = new PermohonanTuntutanKos();
                permohonantuntutkos.setInfoAudit(ia);
                permohonantuntutkos.setKodTuntut(kodTuntut);
                permohonantuntutkos.setItem(kodTuntut.getNama());
                permohonantuntutkos.setAmaunTuntutan(amaunTuntutanList[i]);
                permohonantuntutkos.setPermohonan(permohonan);
                permohonantuntutkos.setCawangan(caw);
                permohonantuntutkos.setKodTransaksi(kodTransaksiDAO.findById(kodhasil[i]));
                pembangunanServ.simpanPermohonanTuntutanKos(permohonantuntutkos);

                // added new code
                PermohonanTuntutanButiran permohonanButir = new PermohonanTuntutanButiran();
                permohonanButir.setPermohonanTuntutan(permohonanTuntutan);
                permohonanButir.setPermohonanTuntutanKos(permohonantuntutkos);
                permohonanButir.setInfoAudit(ia);
                pembangunanServ.simpanPermohonanTuntutanButiran(permohonanButir);
                i++;
            }
        }

        jumlah = premium.add(sumbangan).add(penulisan).add(sewaBaru).add(bayaranUpahUkur).add(tandaSempadan).add(penyediaan).add(pendaftaran).add(semantara).add(notis).add(borang7c).add(dendap);
        addSimpleMessage("Maklumat telah berjaya disimpan.");
        if (cukai != null) {
            return new JSP("pembangunan/common/cukai.jsp").addParameter("tab", "true");
        } else {
            return new JSP("pembangunan/pecahSempadan/notis_7G_NS.jsp").addParameter("tab", "true");
        }
    }

    public Permohonan getPermohonan() {
        return permohonan;
    }

    public void setPermohonan(Permohonan permohonan) {
        this.permohonan = permohonan;
    }

    public String getNegeri() {
        return negeri;
    }

    public void setNegeri(String negeri) {
        this.negeri = negeri;
    }

    public BigDecimal getJumlah() {
        return jumlah;
    }

    public void setJumlah(BigDecimal jumlah) {
        this.jumlah = jumlah;
    }

    public PermohonanTuntutanKos getPermohonantuntutkos() {
        return permohonantuntutkos;
    }

    public void setPermohonantuntutkos(PermohonanTuntutanKos permohonantuntutkos) {
        this.permohonantuntutkos = permohonantuntutkos;
    }

    public BigDecimal getPremium() {
        return premium;
    }

    public void setPremium(BigDecimal premium) {
        this.premium = premium;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public List<PermohonanTuntutanKos> getListtuntutankos() {
        return listtuntutankos;
    }

    public void setListtuntutankos(List<PermohonanTuntutanKos> listtuntutankos) {
        this.listtuntutankos = listtuntutankos;
    }

    public BigDecimal getSumbangan() {
        return sumbangan;
    }

    public void setSumbangan(BigDecimal sumbangan) {
        this.sumbangan = sumbangan;
    }

    public BigDecimal getPendaftaran() {
        return pendaftaran;
    }

    public void setPendaftaran(BigDecimal pendaftaran) {
        this.pendaftaran = pendaftaran;
    }

    public BigDecimal getLuas() {
        return luas;
    }

    public void setLuas(BigDecimal luas) {
        this.luas = luas;
    }

    public String getKodluas() {
        return kodluas;
    }

    public void setKodluas(String kodluas) {
        this.kodluas = kodluas;
    }

    public BigDecimal getSewaBaru() {
        return sewaBaru;
    }

    public void setSewaBaru(BigDecimal sewaBaru) {
        this.sewaBaru = sewaBaru;
    }

    public String getIdPermohonan() {
        return idPermohonan;
    }

    public void setIdPermohonan(String idPermohonan) {
        this.idPermohonan = idPermohonan;
    }

    public BigDecimal getBayaranUpahUkur() {
        return bayaranUpahUkur;
    }

    public void setBayaranUpahUkur(BigDecimal bayaranUpahUkur) {
        this.bayaranUpahUkur = bayaranUpahUkur;
    }

    public BigDecimal getNotis() {
        return notis;
    }

    public void setNotis(BigDecimal notis) {
        this.notis = notis;
    }

    public BigDecimal getPenulisan() {
        return penulisan;
    }

    public void setPenulisan(BigDecimal penulisan) {
        this.penulisan = penulisan;
    }

    public BigDecimal getPenyediaan() {
        return penyediaan;
    }

    public void setPenyediaan(BigDecimal penyediaan) {
        this.penyediaan = penyediaan;
    }

    public BigDecimal getSemantara() {
        return semantara;
    }

    public void setSemantara(BigDecimal semantara) {
        this.semantara = semantara;
    }

    public BigDecimal getTandaSempadan() {
        return tandaSempadan;
    }

    public void setTandaSempadan(BigDecimal tandaSempadan) {
        this.tandaSempadan = tandaSempadan;
    }

    public BigDecimal getBorang7c() {
        return borang7c;
    }

    public void setBorang7c(BigDecimal borang7c) {
        this.borang7c = borang7c;
    }

    public BigDecimal getDendap() {
        return dendap;
    }

    public void setDendap(BigDecimal dendap) {
        this.dendap = dendap;
    }
}
