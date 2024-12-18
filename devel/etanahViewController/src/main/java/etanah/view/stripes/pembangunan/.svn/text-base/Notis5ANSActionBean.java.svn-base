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
import java.util.ArrayList;
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

/**
 *
 * @author NageswaraRao
 */
@UrlBinding("/pembangunan/notis5A_NS")
public class Notis5ANSActionBean extends AbleActionBean {

    private static final Logger LOG = Logger.getLogger(Notis5AActionBean.class);
    @Inject
    KodTuntutDAO kodTuntutDAO;
    @Inject
    PermohonanDAO permohonanDAO;
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

    private BigDecimal cukaiTahun;
    private BigDecimal premium;
    private BigDecimal dendap;
    private BigDecimal sumbangan;
    private BigDecimal bayaranUkur;
    private BigDecimal tandaSempadan;
    private BigDecimal penyediaan;
    private BigDecimal pendaftaran;
    private BigDecimal sementara;
    private BigDecimal notis;
    private BigDecimal pelan;
    private BigDecimal jumlah;

    private String item;
    private List<PermohonanTuntutanKos> listtuntutankos;
    private String idPermohonan;

    @DefaultHandler
    public Resolution showForm() {
        return new JSP("pembangunan/pecahSempadan/notis_5A_NS.jsp").addParameter("tab", "true");
    }

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {
        cukaiTahun = BigDecimal.ZERO;
        premium = BigDecimal.ZERO;
        sumbangan = BigDecimal.ZERO;
        bayaranUkur = BigDecimal.ZERO;
        tandaSempadan = BigDecimal.ZERO;
        penyediaan = BigDecimal.ZERO;
        pendaftaran = BigDecimal.ZERO;
        sementara = BigDecimal.ZERO;
        notis = BigDecimal.ZERO;
        jumlah = BigDecimal.ZERO;
        dendap = BigDecimal.ZERO;
        pelan = BigDecimal.ZERO;

        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = permohonanDAO.findById(idPermohonan);
        String[] tname = {"permohonan"};
        Object[] model = {permohonan};
        listtuntutankos = new ArrayList<PermohonanTuntutanKos>();
        // listtuntutankos = permohonanTuntutanKosDAO.findByEqualCriterias(tname, model, null);
        listtuntutankos = pembangunanServ.findTuntutByIdMohon(idPermohonan);

        if (!(listtuntutankos.isEmpty())) {
            for (int i = 0; i < listtuntutankos.size(); i++) {
                permohonantuntutkos = new PermohonanTuntutanKos();
                permohonantuntutkos = listtuntutankos.get(i);
                if (permohonantuntutkos.getKodTuntut() != null) {
                    if (permohonantuntutkos.getKodTuntut().getKod().equals("DEV11")) {
                        cukaiTahun = permohonantuntutkos.getAmaunTuntutan();
                    } else if (permohonantuntutkos.getKodTuntut().getKod().equals("DEV04")) {
                        premium = permohonantuntutkos.getAmaunTuntutan();
                    } else if (permohonantuntutkos.getKodTuntut().getKod().equals("DEV07")) {
                        sumbangan = permohonantuntutkos.getAmaunTuntutan();
                    } else if (permohonantuntutkos.getKodTuntut().getKod().equals("DEV05")) {
                        bayaranUkur = permohonantuntutkos.getAmaunTuntutan();
                    } else if (permohonantuntutkos.getKodTuntut().getKod().equals("DEV06")) {
                        tandaSempadan = permohonantuntutkos.getAmaunTuntutan();
                    } else if (permohonantuntutkos.getKodTuntut().getKod().equals("DEV13")) {
                        penyediaan = permohonantuntutkos.getAmaunTuntutan();
                    } else if (permohonantuntutkos.getKodTuntut().getKod().equals("DEV14")) {
                        pendaftaran = permohonantuntutkos.getAmaunTuntutan();
                    } else if (permohonantuntutkos.getKodTuntut().getKod().equals("DEV15")) {
                        sementara = permohonantuntutkos.getAmaunTuntutan();
                    } else if (permohonantuntutkos.getKodTuntut().getKod().equals("DEV09")) {
                        notis = permohonantuntutkos.getAmaunTuntutan();
                    } else if (permohonantuntutkos.getKodTuntut().getKod().equals("DEV17")) {
                        dendap = permohonantuntutkos.getAmaunTuntutan();
                    } else if (permohonantuntutkos.getKodTuntut().getKod().equals("DEV03")) {
                        pelan = permohonantuntutkos.getAmaunTuntutan();
                    }

                }
            }

            jumlah = cukaiTahun.add(premium).add(sumbangan).add(bayaranUkur).add(tandaSempadan).add(penyediaan).add(pendaftaran).add(sementara).add(notis).add(pelan);
            LOG.debug("---------Jumlah-----------:" + jumlah);
        }
    }

    public Resolution simpan() {
        Pengguna pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        KodCawangan caw = pengguna.getKodCawangan();
        InfoAudit ia = new InfoAudit();
        ia.setDimasukOleh(pengguna);
        ia.setTarikhMasuk(new java.util.Date());

        if (cukaiTahun == null) {
            cukaiTahun = new BigDecimal(0.00);
        }
        if (premium == null) {
            premium = new BigDecimal(0.00);
        }
        if (dendap == null) {
            dendap = new BigDecimal(0.00);
        }
        if (sumbangan == null) {
            sumbangan = new BigDecimal(0.00);
        }
        if (bayaranUkur == null) {
            bayaranUkur = new BigDecimal(0.00);
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
        if (sementara == null) {
            sementara = new BigDecimal(0.00);
        }
        if (notis == null) {
            notis = new BigDecimal(0.00);
        }
        if (pelan == null) {
            pelan = new BigDecimal(0.00);
        }

        if (!(listtuntutankos.isEmpty())) {
            for (int i = 0; i < listtuntutankos.size(); i++) {

                permohonantuntutkos = new PermohonanTuntutanKos();
                permohonantuntutkos = listtuntutankos.get(i);
                if (permohonantuntutkos.getKodTuntut() != null) {
                    if (permohonantuntutkos.getKodTuntut().getKod().equals("DEV11")) {
                        permohonantuntutkos.setAmaunTuntutan(cukaiTahun);
                    } else if (permohonantuntutkos.getKodTuntut().getKod().equals("DEV04")) {
                        permohonantuntutkos.setAmaunTuntutan(premium);
                    } else if (permohonantuntutkos.getKodTuntut().getKod().equals("DEV17")) {
                        permohonantuntutkos.setAmaunTuntutan(dendap);
                    } else if (permohonantuntutkos.getKodTuntut().getKod().equals("DEV07")) {
                        permohonantuntutkos.setAmaunTuntutan(sumbangan);
                    } else if (permohonantuntutkos.getKodTuntut().getKod().equals("DEV05")) {
                        permohonantuntutkos.setAmaunTuntutan(bayaranUkur);
                    } else if (permohonantuntutkos.getKodTuntut().getKod().equals("DEV06")) {
                        permohonantuntutkos.setAmaunTuntutan(tandaSempadan);
                    } else if (permohonantuntutkos.getKodTuntut().getKod().equals("DEV13")) {
                        permohonantuntutkos.setAmaunTuntutan(penyediaan);
                    } else if (permohonantuntutkos.getKodTuntut().getKod().equals("DEV14")) {
                        permohonantuntutkos.setAmaunTuntutan(pendaftaran);
                    } else if (permohonantuntutkos.getKodTuntut().getKod().equals("DEV15")) {
                        permohonantuntutkos.setAmaunTuntutan(sementara);
                    } else if (permohonantuntutkos.getKodTuntut().getKod().equals("DEV09")) {
                        permohonantuntutkos.setAmaunTuntutan(notis);
                    } else if (permohonantuntutkos.getKodTuntut().getKod().equals("DEV03")) {
                        permohonantuntutkos.setAmaunTuntutan(pelan);
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
                permohonanTuntutan = pembangunanServ.findPermohonanTuntutanByKodTransName(idPermohonan, "Surat Tuntutan Bayaran Notis5A");
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
            String[] itemList = {"DEV11", "DEV04", "DEV07", "DEV05", "DEV06", "DEV13", "DEV14", "DEV15", "DEV09", "DEV17", "DEV03"};
            String[] hasil = {"61403", "12107", "65096", "12108", "12109", "12112", "30010", "30080", "40040", "76199", "73902"};
            BigDecimal[] amaunTuntutanList = {cukaiTahun, premium, sumbangan, bayaranUkur, tandaSempadan, penyediaan, pendaftaran, sementara, notis, dendap, pelan};
            // added new code
            PermohonanTuntutan permohonanTuntutan = new PermohonanTuntutan();
            KodTransaksiTuntut kodTransTuntut = new KodTransaksiTuntut();
            kodTransTuntut = pembangunanServ.findKodTransTuntutByName("Surat Tuntutan Bayaran Notis5A");
            permohonanTuntutan.setCawangan(caw);
            permohonanTuntutan.setPermohonan(permohonan);
            permohonanTuntutan.setInfoAudit(ia);
            permohonanTuntutan.setKodTransaksiTuntut(kodTransTuntut);
            permohonanTuntutan.setTarikhTuntutan(new java.util.Date());
            pembangunanServ.simpanPermohonanTuntutan(permohonanTuntutan);

            for (int i = 0; i < itemList.length; i++) {
                KodTuntut kodTuntut = new KodTuntut();
                //kodTuntut = pembangunanServ.findKodTuntutByName(itemList[i]);
                kodTuntut = kodTuntutDAO.findById(itemList[i]);
                LOG.debug("---------kodTuntut---------:" + kodTuntut);
                permohonantuntutkos = new PermohonanTuntutanKos();
                permohonantuntutkos.setKodTuntut(kodTuntut);
                permohonantuntutkos.setInfoAudit(ia);
                permohonantuntutkos.setItem(kodTuntut.getNama());
                permohonantuntutkos.setAmaunTuntutan(amaunTuntutanList[i]);
                permohonantuntutkos.setPermohonan(permohonan);
                permohonantuntutkos.setCawangan(caw);
                permohonantuntutkos.setKodTransaksi(kodTransaksiDAO.findById(hasil[i]));
                pembangunanServ.simpanPermohonanTuntutanKos(permohonantuntutkos);

                // added new code
                PermohonanTuntutanButiran permohonanButir = new PermohonanTuntutanButiran();
                permohonanButir.setPermohonanTuntutan(permohonanTuntutan);
                permohonanButir.setPermohonanTuntutanKos(permohonantuntutkos);
                permohonanButir.setInfoAudit(ia);
                pembangunanServ.simpanPermohonanTuntutanButiran(permohonanButir);
            }
        }

        jumlah = cukaiTahun.add(premium).add(sumbangan).add(bayaranUkur).add(tandaSempadan).add(penyediaan).add(pendaftaran).add(sementara).add(notis).add(pelan);
        addSimpleMessage("Maklumat telah berjaya disimpan.");
        return new JSP("pembangunan/pecahSempadan/notis_5A_NS.jsp").addParameter("tab", "true");
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

    public BigDecimal getBayaranUkur() {
        return bayaranUkur;
    }

    public void setBayaranUkur(BigDecimal bayaranUkur) {
        this.bayaranUkur = bayaranUkur;
    }

    public BigDecimal getCukaiTahun() {
        return cukaiTahun;
    }

    public void setCukaiTahun(BigDecimal cukaiTahun) {
        this.cukaiTahun = cukaiTahun;
    }

    public BigDecimal getNotis() {
        return notis;
    }

    public void setNotis(BigDecimal notis) {
        this.notis = notis;
    }

    public BigDecimal getPendaftaran() {
        return pendaftaran;
    }

    public void setPendaftaran(BigDecimal pendaftaran) {
        this.pendaftaran = pendaftaran;
    }

    public BigDecimal getPenyediaan() {
        return penyediaan;
    }

    public void setPenyediaan(BigDecimal penyediaan) {
        this.penyediaan = penyediaan;
    }

    public BigDecimal getSumbangan() {
        return sumbangan;
    }

    public void setSumbangan(BigDecimal sumbangan) {
        this.sumbangan = sumbangan;
    }

    public String getIdPermohonan() {
        return idPermohonan;
    }

    public void setIdPermohonan(String idPermohonan) {
        this.idPermohonan = idPermohonan;
    }

    public BigDecimal getTandaSempadan() {
        return tandaSempadan;
    }

    public void setTandaSempadan(BigDecimal tandaSempadan) {
        this.tandaSempadan = tandaSempadan;
    }

    public BigDecimal getSementara() {
        return sementara;
    }

    public void setSementara(BigDecimal sementara) {
        this.sementara = sementara;
    }

    public BigDecimal getDendap() {
        return dendap;
    }

    public void setDendap(BigDecimal dendap) {
        this.dendap = dendap;
    }

    public BigDecimal getPelan() {
        return pelan;
    }

    public void setPelan(BigDecimal pelan) {
        this.pelan = pelan;
    }

}
