/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.stripes.pembangunan;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.dao.HakmilikDAO;
import etanah.dao.KodPerananDAO;
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
import etanah.service.common.PermohonanService;
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
import etanah.dao.KodTransaksiTuntutDAO;
import etanah.model.Hakmilik;
import etanah.model.HakmilikPermohonan;
import etanah.model.KodPeranan;
import etanah.model.KodUOM;
import etanah.service.CalcTax;
import etanah.model.Notifikasi;
import etanah.service.NotifikasiService;
import java.util.ArrayList;

/**
 *
 * @author NageswaraRao
 */
@UrlBinding("/pembangunan/melaka/notis7G")
public class Notis7GActionBean extends AbleActionBean {

    private static final Logger LOG = Logger.getLogger(Notis7GActionBean.class);
    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    KodTuntutDAO kodTuntutDAO;
    @Inject
    HakmilikDAO hakmilikDAO;
    @Inject
    PermohonanTuntutanKosDAO permohonanTuntutanKosDAO;
    @Inject
    PembangunanService pembangunanServ;
    @Inject
    PermohonanService permohonanService;
    @Inject
    etanah.Configuration conf;
    @Inject
    KodTransaksiTuntutDAO kodTransaksiTuntutDAO;
    @Inject
    CalcTax calcTax;
    @Inject
    NotifikasiService notifikasiService;
    @Inject
    KodPerananDAO kodPerananDAO;
    private Permohonan permohonan;
    private String negeri;
    private PermohonanTuntutanKos permohonantuntutkos;
    private BigDecimal premium;
    private BigDecimal premiumTambahan;
    private BigDecimal cukai;
    private BigDecimal premiumDenda;
    private BigDecimal sumbangan;
    private BigDecimal pendaftaran;
    private BigDecimal jumlah;
    private BigDecimal pendaftaranSahaja;
//  private BigDecimal sewaBaru;
    private BigDecimal hasil;
    private BigDecimal notis;
    private BigDecimal pelan;
    private String item;
    private List<PermohonanTuntutanKos> listtuntutankos;
    private String idPermohonan;
    private String luas;
    private String idH;
    private String kodUOM;
    private String result;

    @DefaultHandler
    public Resolution showForm() {
        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        return new JSP("pembangunan/melaka/notis7G.jsp").addParameter("tab", "true");
    }

    public Resolution viewForm() {
        return new JSP("pembangunan/melaka/notis7G.jsp").addParameter("tab", "true");
    }

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {
        premium = BigDecimal.ZERO;
        premiumTambahan = BigDecimal.ZERO;
        cukai = BigDecimal.ZERO;
        premiumDenda = BigDecimal.ZERO;
        sumbangan = BigDecimal.ZERO;
        pendaftaran = BigDecimal.ZERO;
        hasil = BigDecimal.ZERO;
        jumlah = BigDecimal.ZERO;
        notis = BigDecimal.ZERO;
        pendaftaranSahaja = BigDecimal.ZERO;
        pelan = BigDecimal.ZERO;

        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = permohonanDAO.findById(idPermohonan);
        String[] tname = {"permohonan"};
        Object[] model = {permohonan};
//        listtuntutankos = permohonanTuntutanKosDAO.findByEqualCriterias(tname, model, null);

        listtuntutankos = pembangunanServ.findTuntutByIdMohon(idPermohonan);

        //hasilTahunPertama
//        if (hasil != null) {
//            //DO NOTHING
//        } else {
        kiraHasilTahunPertama(idPermohonan);
//        }

        if (!(listtuntutankos.isEmpty())) {
            for (int i = 0; i < listtuntutankos.size(); i++) {
                permohonantuntutkos = new PermohonanTuntutanKos();
                permohonantuntutkos = listtuntutankos.get(i);
                if (permohonantuntutkos.getKodTuntut() != null) {
                    if (permohonantuntutkos.getKodTuntut().getKod().equals("DEV04")) {
                        premium = permohonantuntutkos.getAmaunTuntutan();
                    } else if (permohonantuntutkos.getKodTuntut().getKod().equals("DEV06")) {
                        premiumTambahan = permohonantuntutkos.getAmaunTuntutan();
                    } else if (permohonantuntutkos.getKodTuntut().getKod().equals("DEV11")) {
                        hasil = permohonantuntutkos.getAmaunTuntutan();
                    } else if (permohonantuntutkos.getKodTuntut().getKod().equals("DEV07")) {
                        sumbangan = permohonantuntutkos.getAmaunTuntutan();
                    } else if (permohonantuntutkos.getKodTuntut().getKod().equals("DEV01")) {
                        pendaftaran = permohonantuntutkos.getAmaunTuntutan();
                    } else if (permohonantuntutkos.getKodTuntut().getKod().equals("DEV02")) {
                        cukai = permohonantuntutkos.getAmaunTuntutan();
                    } else if (permohonantuntutkos.getKodTuntut().getKod().equals("DEV03")) {
                        premiumDenda = permohonantuntutkos.getAmaunTuntutan();
                    } else if (permohonantuntutkos.getKodTuntut().getKod().equals("DEV13")) {
                        notis = permohonantuntutkos.getAmaunTuntutan();
                    } else if (permohonantuntutkos.getKodTuntut().getKod().equals("DEV08")) {
                        pendaftaranSahaja = permohonantuntutkos.getAmaunTuntutan();
                    } else if (permohonantuntutkos.getKodTuntut().getKod().equals("DEV14")) {
                        pelan = permohonantuntutkos.getAmaunTuntutan();
                    }
                }
            }

            if (permohonan.getKodUrusan().getKod().equalsIgnoreCase("TSPSS")) {
                jumlah = premium.add(premiumTambahan).add(premiumDenda).add(sumbangan).add(notis).add(pendaftaran).add(cukai).add(pelan);
            } else {
                jumlah = premium.add(premiumTambahan).add(premiumDenda).add(sumbangan).add(notis).add(pendaftaranSahaja);
            }
        }
    }

    public Resolution simpan() {
        Pengguna pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        KodCawangan caw = pengguna.getKodCawangan();
        InfoAudit ia = new InfoAudit();
        ia.setDimasukOleh(pengguna);
        ia.setTarikhMasuk(new java.util.Date());

        if (cukai == null) {
            cukai = new BigDecimal(0.00);
        }
        if (premium == null) {
            premium = new BigDecimal(0.00);
        }
        if (premiumTambahan == null) {
            premiumTambahan = new BigDecimal(0.00);
        }
        if (premiumDenda == null) {
            premiumDenda = new BigDecimal(0.00);
        }
        if (sumbangan == null) {
            sumbangan = new BigDecimal(0.00);
        }
        if (pendaftaran == null) {
            pendaftaran = new BigDecimal(0.00);
        }
        if (hasil == null) {
            hasil = new BigDecimal(0.00);
        }
        if (notis == null) {
            notis = new BigDecimal(0.00);
        }
        if (pendaftaranSahaja == null) {
            pendaftaranSahaja = new BigDecimal(0.00);
        }
        if (pelan == null) {
            pelan = new BigDecimal(0.00);
        }

        if (!(listtuntutankos.isEmpty()) && listtuntutankos.size() == 10) {

            for (int i = 0; i < listtuntutankos.size(); i++) {

                permohonantuntutkos = new PermohonanTuntutanKos();
                permohonantuntutkos = listtuntutankos.get(i);
                if (permohonantuntutkos.getKodTuntut() != null) {

                    if (permohonantuntutkos.getKodTuntut().getKod().equals("DEV04")) {
                        permohonantuntutkos.setAmaunTuntutan(premium);
                    } else if (permohonantuntutkos.getKodTuntut().getKod().equals("DEV06")) {
                        permohonantuntutkos.setAmaunTuntutan(premiumTambahan);
                    } else if (permohonantuntutkos.getKodTuntut().getKod().equals("DEV11")) {
                        permohonantuntutkos.setAmaunTuntutan(hasil);
                    } else if (permohonantuntutkos.getKodTuntut().getKod().equals("DEV07")) {
                        permohonantuntutkos.setAmaunTuntutan(sumbangan);
                    } else if (permohonantuntutkos.getKodTuntut().getKod().equals("DEV01")) {
                        permohonantuntutkos.setAmaunTuntutan(pendaftaran);
                    } else if (permohonantuntutkos.getKodTuntut().getKod().equals("DEV02")) {
                        permohonantuntutkos.setAmaunTuntutan(cukai);
                    } else if (permohonantuntutkos.getKodTuntut().getKod().equals("DEV03")) {
                        permohonantuntutkos.setAmaunTuntutan(premiumDenda);
                    } else if (permohonantuntutkos.getKodTuntut().getKod().equals("DEV13")) {
                        permohonantuntutkos.setAmaunTuntutan(notis);
                    } else if (permohonantuntutkos.getKodTuntut().getKod().equals("DEV08")) {
                        permohonantuntutkos.setAmaunTuntutan(pendaftaranSahaja);
                    } else if (permohonantuntutkos.getKodTuntut().getKod().equals("DEV14")) {
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

            String[] itemList = {"DEV04", "DEV11", "DEV07", "DEV06", "DEV01", "DEV02", "DEV14", "DEV13", "DEV08", "DEV03"};
            BigDecimal[] amaunTuntutanList = {premium, hasil, sumbangan, premiumTambahan, pendaftaran, cukai, pelan, notis, pendaftaranSahaja, premiumDenda};

            KodTransaksiTuntut kodTransTuntut = new KodTransaksiTuntut();
            kodTransTuntut = kodTransaksiTuntutDAO.findById("DEV7G");
            PermohonanTuntutan permohonanTuntutan = new PermohonanTuntutan();
            permohonanTuntutan.setCawangan(caw);
            permohonanTuntutan.setPermohonan(permohonan);
            permohonanTuntutan.setInfoAudit(ia);
            permohonanTuntutan.setKodTransaksiTuntut(kodTransTuntut);
            permohonanTuntutan.setTarikhTuntutan(new java.util.Date());
            pembangunanServ.simpanPermohonanTuntutan(permohonanTuntutan);

            int k = 0;
            if (!(listtuntutankos.isEmpty())) {
                for (int i = 0; i < listtuntutankos.size(); i++) {
                    permohonantuntutkos = new PermohonanTuntutanKos();
                    permohonantuntutkos = listtuntutankos.get(i);
                    ia = permohonantuntutkos.getInfoAudit();
                    if (permohonantuntutkos.getKodTuntut().getKod().equals("DEV11")) {
                        permohonantuntutkos.setAmaunTuntutan(hasil);
                    } else if (permohonantuntutkos.getKodTuntut().getKod().equals("DEV04")) {
                        permohonantuntutkos.setAmaunTuntutan(premium);
                    } else if (permohonantuntutkos.getKodTuntut().getKod().equals("DEV07")) {
                        permohonantuntutkos.setAmaunTuntutan(sumbangan);
                    }
                    ia.setTarikhKemaskini(new java.util.Date());
                    ia.setDiKemaskiniOleh(pengguna);
                    permohonantuntutkos.setInfoAudit(ia);
                    pembangunanServ.simpanPermohonanTuntutanKos(permohonantuntutkos);
                    // Added permohonanTuntutButir details
                    PermohonanTuntutanButiran permohonanButir = new PermohonanTuntutanButiran();
                    permohonanButir.setPermohonanTuntutan(permohonanTuntutan);
                    permohonanButir.setPermohonanTuntutanKos(permohonantuntutkos);
                    permohonanButir.setInfoAudit(ia);
                    pembangunanServ.simpanPermohonanTuntutanButiran(permohonanButir);
                    k++;
                }
            }

            while (k < 10) {
                KodTuntut kodTuntut = new KodTuntut();
                kodTuntut = kodTuntutDAO.findById(itemList[k]);
                LOG.debug("---------kodTuntut---------:" + kodTuntut);
                permohonantuntutkos = new PermohonanTuntutanKos();
                permohonantuntutkos.setKodTuntut(kodTuntut);
                permohonantuntutkos.setInfoAudit(ia);
                permohonantuntutkos.setItem(kodTuntut.getNama());
                permohonantuntutkos.setAmaunTuntutan(amaunTuntutanList[k]);
                permohonantuntutkos.setPermohonan(permohonan);
                permohonantuntutkos.setCawangan(caw);
                permohonantuntutkos.setKodTransaksi(kodTuntut.getKodKewTrans());
                pembangunanServ.simpanPermohonanTuntutanKos(permohonantuntutkos);

                // added new code
                PermohonanTuntutanButiran permohonanButir = new PermohonanTuntutanButiran();
                permohonanButir.setPermohonanTuntutan(permohonanTuntutan);
                permohonanButir.setPermohonanTuntutanKos(permohonantuntutkos);
                permohonanButir.setInfoAudit(ia);
                pembangunanServ.simpanPermohonanTuntutanButiran(permohonanButir);
                k++;
            }
        }
        if (permohonan.getKodUrusan().getKod().equalsIgnoreCase("TSPSS")) {
            jumlah = premium.add(premiumTambahan).add(premiumDenda).add(sumbangan).add(notis).add(pendaftaran).add(cukai).add(pelan);
        } else {
            jumlah = premium.add(premiumTambahan).add(premiumDenda).add(sumbangan).add(notis).add(pendaftaranSahaja);
        }

        //Modify HakmilikPermohonan details
        HakmilikPermohonan hp = new HakmilikPermohonan();
        List<HakmilikPermohonan> senaraiHakmilikPermohonan = permohonan.getSenaraiHakmilik();
        if (senaraiHakmilikPermohonan != null && senaraiHakmilikPermohonan.size() > 0) {
            hp = senaraiHakmilikPermohonan.get(0);
        }

        if (hp != null) {
            hp.setKadarPremium(premium);
            hp.setCukaiBaru(hasil);
            hp.setKosInfra(sumbangan);
            pembangunanServ.simpanHakmilikPermohonan(hp);
        }

        addSimpleMessage("Maklumat telah berjaya disimpan.");
        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        return new JSP("pembangunan/melaka/notis7G.jsp").addParameter("tab", "true");
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

    public BigDecimal getCukai() {
        return cukai;
    }

    public void setCukai(BigDecimal cukai) {
        this.cukai = cukai;
    }

    public BigDecimal getPendaftaran() {
        return pendaftaran;
    }

    public void setPendaftaran(BigDecimal pendaftaran) {
        this.pendaftaran = pendaftaran;
    }

    public BigDecimal getPremiumDenda() {
        return premiumDenda;
    }

    public void setPremiumDenda(BigDecimal premiumDenda) {
        this.premiumDenda = premiumDenda;
    }

    public String getIdPermohonan() {
        return idPermohonan;
    }

    public void setIdPermohonan(String idPermohonan) {
        this.idPermohonan = idPermohonan;
    }

    public BigDecimal getHasil() {
        return hasil;
    }

    public void setHasil(BigDecimal hasil) {
        this.hasil = hasil;
    }

    public BigDecimal getPremiumTambahan() {
        return premiumTambahan;
    }

    public void setPremiumTambahan(BigDecimal premiumTambahan) {
        this.premiumTambahan = premiumTambahan;
    }

    public BigDecimal getNotis() {
        return notis;
    }

    public void setNotis(BigDecimal notis) {
        this.notis = notis;
    }

    public BigDecimal getPendaftaranSahaja() {
        return pendaftaranSahaja;
    }

    public void setPendaftaranSahaja(BigDecimal pendaftaranSahaja) {
        this.pendaftaranSahaja = pendaftaranSahaja;
    }

    public BigDecimal getPelan() {
        return pelan;
    }

    public void setPelan(BigDecimal pelan) {
        this.pelan = pelan;
    }

    public String getLuas() {
        return luas;
    }

    public void setLuas(String luas) {
        this.luas = luas;
    }

    public String getIdH() {
        return idH;
    }

    public void setIdH(String idH) {
        this.idH = idH;
    }

    public String getKodUOM() {
        return kodUOM;
    }

    public void setKodUOM(String kodUOM) {
        this.kodUOM = kodUOM;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    private void kiraHasilTahunPertama(String idPermohonan) {
        Hakmilik h = new Hakmilik();
        HakmilikPermohonan hm = new HakmilikPermohonan();

        permohonan = permohonanDAO.findById(idPermohonan);
        Permohonan ps = permohonanService.searchPermohonanSebelum(idPermohonan);
        hm = pembangunanServ.findByIdPermohonan(idPermohonan);
        idH = hm.getHakmilik().getIdHakmilik();
        h = hakmilikDAO.findById(idH);
        if (ps != null) {
            if (ps.getKodUrusan().getKod().equals("TSKKL") && ps.getStatus().equals("SL") && ps.getKeputusan().getKod().equals("D")) {
                System.out.println("IF HERE");
//                hm = pembangunanServ.findByIdPermohonan(idPermohonan);
                result = "";
                idH = hm.getHakmilik().getIdHakmilik();
                kodUOM = hm.getHakmilik().getKodUnitLuas().getKod();
                luas = hm.getHakmilik().getLuas().toString();
                System.out.println("-- " + idH + " -- " + kodUOM + " -- " + luas);

                String kodRizab;
                h = hakmilikDAO.findById(idH);

                //        MathContext mc = new MathContext(0);
                //        mc = mc.DECIMAL32;
                LOG.info(":: Start :: KiraCukaiKelompok");
                LOG.info(">idhakmilik: " + idH);
                LOG.info(">luas yang terlibat : " + luas);
                LOG.info(">kodUOM luas terlibat : " + kodUOM);
                //    LOG.info("kod Guna Tanah hakmilik : " + h.getKegunaanTanah().getKod());
                LOG.info("kod BPM hakmilik : " + h.getBandarPekanMukim().getKod());
                LOG.info("Luas Asal hakmilik : " + h.getLuas());

                BigDecimal luasDiambil = new BigDecimal(luas); // convert input type 'luas' to bigDecimal        
                BigDecimal luasTinggal = new BigDecimal(0);
                BigDecimal luasConverted = calcTax.kiraUnitLuas(kodUOM, h.getKodUnitLuas().getKod(), h.getLuas()); // LUAS CONVERTER FUNCTION
                LOG.info("Luas yang Ditukar : " + luasConverted);

                // check pertanian and kodUOM hakmilik
//                LOG.info(" /* Kategori Tanah = " + h.getKategoriTanah().getKod() + " , Unit Luas = " + h.getKodUnitLuas().getNama() + " */ ");
//                if ("1".equals(h.getKategoriTanah().getKod()) && "H".equals(h.getKodUnitLuas().getKod())
//                        || "2".equals(h.getKategoriTanah().getKod()) && "M".equals(h.getKodUnitLuas().getKod())
//                        || "3".equals(h.getKategoriTanah().getKod()) && "M".equals(h.getKodUnitLuas().getKod())) {
//                    luasTinggal = h.getLuas().subtract(luasDiambil);
//                } else {
//                    luasTinggal = luasConverted.subtract(luasDiambil);
//                }
//                LOG.debug(":Luas Tinggal: " + luasTinggal);
                if (h.getRizab() != null) {
                    LOG.debug("kodRizab : " + h.getRizab().getKod());
                    kodRizab = String.valueOf(h.getRizab().getKod());
                } else {
                    kodRizab = null;
                }

                result = String.valueOf(calcTax.calculate(h.getKegunaanTanah().getKod(), String.valueOf(h.getBandarPekanMukim().getKod()), kodUOM, luasConverted, h, kodRizab));
            } else {
                System.out.println("HERE ELSE");
                hm = pembangunanServ.findByIdPermohonan(idPermohonan);
                result = "";
                idH = hm.getHakmilik().getIdHakmilik();
                kodUOM = hm.getKodUnitLuas().getKod();
                luas = hm.getLuasTerlibat().toString();
                System.out.println("-- " + idH + " -- " + kodUOM + " -- " + luas);

                String kodRizab;
                h = hakmilikDAO.findById(idH);

                //        MathContext mc = new MathContext(0);
                //        mc = mc.DECIMAL32;
                LOG.info(":: Start :: KiraCukaiKelompok");
                LOG.info(">idhakmilik: " + idH);
                LOG.info(">luas yang terlibat : " + luas);
                LOG.info(">kodUOM luas terlibat : " + kodUOM);
                //    LOG.info("kod Guna Tanah hakmilik : " + h.getKegunaanTanah().getKod());
                LOG.info("kod BPM hakmilik : " + h.getBandarPekanMukim().getKod());
                LOG.info("Luas Asal hakmilik : " + h.getLuas());
                LOG.info("kodunitLuas codeAsal hakmilik : " + h.getKodUnitLuas().toString());
                LOG.info("kodunitLuas codeBaru hakmilik : " + h.getKodUnitLuas().getKod());

                BigDecimal luasDiambil = new BigDecimal(luas); // convert input type 'luas' to bigDecimal    
                LOG.info("Luas yang Diambil : " + luasDiambil);
                BigDecimal luasTinggal = new BigDecimal(0);
                LOG.info("Luas yang tinggal : " + luasTinggal);
                BigDecimal luasConverted = calcTax.kiraUnitLuas(kodUOM, h.getKodUnitLuas().getKod(), h.getLuas()); // LUAS CONVERTER FUNCTION
                LOG.info("Luas yang Ditukar : " + luasConverted);

//                // check pertanian and kodUOM hakmilik
//                LOG.info(" /* Kategori Tanah = " + hm.getKategoriTanahBaru().getKod() + " , Unit Luas = " + hm.getKodUnitLuas().getNama() + " */ ");
//                if ("1".equals(hm.getKategoriTanahBaru().getKod()) && "H".equals(hm.getKodUnitLuas().getKod())
//                        || "2".equals(hm.getKategoriTanahBaru().getKod()) && "M".equals(hm.getKodUnitLuas().getKod())
//                        || "3".equals(hm.getKategoriTanahBaru().getKod()) && "M".equals(hm.getKodUnitLuas().getKod())) {
//                    luasTinggal = h.getLuas().subtract(luasDiambil);
//                } else {
//                    luasTinggal = luasConverted.subtract(luasDiambil);
//                }
//                LOG.debug(":Luas Tinggal: " + luasTinggal);
                if (h.getRizab() != null) {
                    LOG.debug("kodRizab : " + h.getRizab().getKod());
                    kodRizab = String.valueOf(h.getRizab().getKod());
                } else {
                    kodRizab = null;
                }

                result = String.valueOf(calcTax.calculate(hm.getKodKegunaanTanah().getKod(), String.valueOf(h.getBandarPekanMukim().getKod()), kodUOM, luasConverted, h, kodRizab));

            }
        }
        System.out.println("--RESULT--" + result);

        PermohonanTuntutanKos saveKos = pembangunanServ.findMohonTuntutKosByIdPermohonanAndIdTuntut(idPermohonan, "DEV11");
        if (saveKos != null) {
            List<HakmilikPermohonan> hmprmhnan = pembangunanServ.findHakmilikPermohonanByIdPermohonan(idPermohonan);
            if (hmprmhnan.size() > 0) {
                for (HakmilikPermohonan hp2 : hmprmhnan) {
                    if (hp2.getKodKegunaanTanah().getKod().equalsIgnoreCase("B02")) {
                        System.out.println("--HERE1--");
                        //DONOTHING
                        //SEMENTARA KOD CUKAI UNTUK KOD B02 SETTLE VALIDATION DENGAN HASIL
                    } else {
                        System.out.println("--HERE2--");
                        result = saveKos.getAmaunTuntutan().toString();
                        BigDecimal bg = new BigDecimal(result);
                        saveKos.setAmaunTuntutan(bg);
                        pembangunanServ.simpanPermohonanTuntutanKos(saveKos);
                    }
                }
            }
        } else {
            BigDecimal bg = new BigDecimal(result);
            if (saveKos != null) {
                saveKos.setAmaunTuntutan(bg);
                pembangunanServ.simpanPermohonanTuntutanKos(saveKos);
            }
        }

        Notifikasi n = new Notifikasi();
        n.setTajuk("Makluman dan Pengesahan Pengiraan Cukai Bagi Integrasi Modul Pembangunan");
        String strMesej = new String();
        String syaratNyataBaru = "";

        if (hm.getSyaratNyataBaru() != null) {
            syaratNyataBaru = hm.getSyaratNyataBaru().getSyarat();
        } else {
            syaratNyataBaru = "";
        }

        strMesej = "<br/>"
                + "<center><p><u><b>PENGIRAAN CUKAI BARU</b></u></p></center>"
                + "<br/>"
                + "<br/>"
                + "<center>" + permohonan.getSebab() + "</center>"
                + "<br/>"
                + "<br/>+"
                + "<center><table>"
                + "  <tr>"
                + "    <th align=\"center\" width=\"5%\">Bil</th>"
                + "    <th align=\"center\" width=\"10%\">Jenis H/Milik</th>"
                + "    <th align=\"center\" width=\"10%\">No H/Milik</th>"
                + "    <th align=\"center\" width=\"10%\">No.Pt/Lot</th>"
                + "    <th align=\"center\" width=\"10%\">Kategori</th>"
                + "    <th align=\"center\" width=\"30%\">SyaratNyata</th>"
                + "    <th align=\"center\" width=\"10%\">Luas</th>"
                + "    <th align=\"center\" width=\"10%\">KadarCukaiBaru</th>"
                + "  </tr>"
                + "  <tr>"
                + "    <td align=\"center\" width=\"5%\">1</td>"
                + "    <td align=\"center\" width=\"10%\">" + h.getKodHakmilik().getKod() + "</td>"
                + "    <td align=\"center\" width=\"10%\">" + h.getNoHakmilik() + "</td>"
                + "    <td align=\"center\" width=\"10%\">" + h.getLot().getNama() + " " + h.getNoLot() + "</td>"
                + "    <td align=\"center\" width=\"10%\">" + h.getKategoriTanah().getNama() + "</t>"
                + "    <td align=\"center\" width=\"30%\">" + syaratNyataBaru + "</td>"
                + "    <td align=\"center\" width=\"10%\">" + h.getLuas() + " " + h.getKodUnitLuas().getNama() + "</td>"
                + "    <td align=\"center\" width=\"10%\">" + result + "</td>"
                + "  </tr>"
                + "</table></center>"
                + "<br/>";
        n.setMesej(strMesej);
        Pengguna pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        InfoAudit infoAudit = new InfoAudit();
        infoAudit.setDimasukOleh(pengguna);
        infoAudit.setTarikhMasuk(new java.util.Date());
        n.setCawangan(pengguna.getKodCawangan());
        ArrayList<KodPeranan> list = new ArrayList<KodPeranan>();
        list.add(kodPerananDAO.findById("9"));    //ke modul hasil
        n.setInfoAudit(infoAudit);
        notifikasiService.addRolesToNotifikasi(n, pengguna.getKodCawangan(), list);

    }
}
