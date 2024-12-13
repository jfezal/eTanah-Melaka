/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.stripes.pelupusan;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.dao.KodTransaksiDAO;
import etanah.dao.KodTuntutDAO;
import etanah.dao.PermohonanDAO;
import etanah.dao.PermohonanTuntutanKosDAO;
import etanah.model.HakmilikPermohonan;
import etanah.model.Permohonan;
import etanah.model.PermohonanTuntutanKos;
import etanah.model.Pengguna;
import etanah.model.KodCawangan;
import etanah.model.InfoAudit;
import etanah.model.KodKadarPremium;
import etanah.model.KodTanah;
import etanah.model.KodTransaksi;
import etanah.model.KodTransaksiTuntut;
import etanah.model.KodTuntut;
import etanah.model.PermohonanLaporanPelan;
import etanah.model.PermohonanRujukanLuar;
import etanah.model.PermohonanTuntutan;
import etanah.model.PermohonanTuntutanButiran;
import etanah.service.PelupusanService;
import java.math.BigDecimal;
import etanah.view.etanahActionBeanContext;
import etanah.service.PembangunanService;
import java.util.ArrayList;
import java.util.List;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;
import org.apache.log4j.Logger;

/**
 *
 * @author afham
 */
@UrlBinding("/pelupusan/borang_5A_rayuan")
public class Borang5ARayuanActionBean extends AbleActionBean {

    private static final Logger LOG = Logger.getLogger(Borang5ARayuanActionBean.class);
    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    KodTuntutDAO kodTuntutDAO;
    @Inject
    PermohonanTuntutanKosDAO permohonanTuntutanKosDAO;
    @Inject
    PelupusanService pelupusanService;
    @Inject
    KodTransaksiDAO kodTransaksiDAO;
    @Inject
    etanah.Configuration conf;
    private Permohonan permohonan;
    private String negeri;
    private PermohonanTuntutanKos permohonantuntutkos;
    private HakmilikPermohonan hakmilikPermohonan;
    private PermohonanRujukanLuar permohonanRujukanLuar;
    private BigDecimal cukaiBagiTahunYangPertama;
    private BigDecimal premium;
    private BigDecimal bayaranWangPelan;
    private BigDecimal bayaranUkur;
    private BigDecimal tandaSempadan;
    private BigDecimal hakmilikKekal;
    private BigDecimal hakmilikSementara;
    private BigDecimal jumlah;
    private String item;
    private String idPermohonan;
    private List<PermohonanTuntutanKos> listtuntutankos;
    private KodKadarPremium kodKadarPremium;
    private BigDecimal peratuas;
    private Integer harga;
    private BigDecimal tempohPajakan;
    private PermohonanLaporanPelan permohonanLaporanPelan;
    private String idPermohonanSebelum;

    @DefaultHandler
    public Resolution showForm() {
        return new JSP("pelupusan/rayuan/borang_5A_mlk.jsp").addParameter("tab", "true");
    }

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {
        LOG.debug("Going into rehydrate ");
        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = permohonanDAO.findById(idPermohonan);
        if (permohonan.getPermohonanSebelum() != null) {
            idPermohonanSebelum = permohonan.getPermohonanSebelum().getIdPermohonan();
        }

        premium = BigDecimal.ZERO;
        cukaiBagiTahunYangPertama = BigDecimal.ZERO;
        bayaranWangPelan = BigDecimal.ZERO;
        bayaranUkur = BigDecimal.ZERO;
        tandaSempadan = BigDecimal.ZERO;
        hakmilikKekal = BigDecimal.ZERO;
        hakmilikSementara = BigDecimal.ZERO;
        jumlah = BigDecimal.ZERO;

        String[] tname = {"permohonan"};
        Object[] model = {permohonan};

        listtuntutankos = new ArrayList<PermohonanTuntutanKos>();
        listtuntutankos = pelupusanService.findTuntutByIdMohon(idPermohonanSebelum);

        // Premium diluluskan disimpan di dalam notis 5a
        if (!(listtuntutankos.isEmpty())) {

            Pengguna pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
            KodCawangan caw = pengguna.getKodCawangan();
            InfoAudit ia = new InfoAudit();
            ia.setDimasukOleh(pengguna);
            ia.setTarikhMasuk(new java.util.Date());
            for (int i = 0; i < listtuntutankos.size(); i++) {
                permohonantuntutkos = new PermohonanTuntutanKos();
                permohonantuntutkos = listtuntutankos.get(i);
                if (permohonantuntutkos.getKodTuntut() != null) {
                    if (permohonantuntutkos.getKodTuntut().getKod().equals("DISPRM")) {
                        permohonantuntutkos.setAmaunTuntutan(permohonan.getNilaiKeputusan());
                        ia = permohonantuntutkos.getInfoAudit();
                        ia.setTarikhKemaskini(new java.util.Date());
                        ia.setDiKemaskiniOleh(pengguna);
                        permohonantuntutkos.setInfoAudit(ia);
                        permohonantuntutkos.setPermohonan(permohonan.getPermohonanSebelum());
                        permohonantuntutkos.setCawangan(caw);
                        pelupusanService.simpanPermohonanTuntutanKos1(permohonantuntutkos);
                    }
                }
            }

        }

        // Get all notis 5A
        if (!(listtuntutankos.isEmpty())) {
            for (int i = 0; i < listtuntutankos.size(); i++) {
                permohonantuntutkos = new PermohonanTuntutanKos();
                permohonantuntutkos = listtuntutankos.get(i);
                if (permohonantuntutkos.getKodTuntut() != null) {
                    if (permohonantuntutkos.getKodTuntut().getKod().equals("DISPRM")) {
                        premium = permohonantuntutkos.getAmaunTuntutan();
                    } else if (permohonantuntutkos.getKodTuntut().getKod().equals("DISTAX")) {
                        cukaiBagiTahunYangPertama = permohonantuntutkos.getAmaunTuntutan();
                    } else if (permohonantuntutkos.getKodTuntut().getKod().equals("DISPEL")) {
                        bayaranWangPelan = permohonantuntutkos.getAmaunTuntutan();
                    } else if (permohonantuntutkos.getKodTuntut().getKod().equals("DISUKUR")) {
                        bayaranUkur = permohonantuntutkos.getAmaunTuntutan();
                    } else if (permohonantuntutkos.getKodTuntut().getKod().equals("DISSEM")) {
                        tandaSempadan = permohonantuntutkos.getAmaunTuntutan();
                    } else if (permohonantuntutkos.getKodTuntut().getKod().equals("DISQT")) {
                        hakmilikKekal = permohonantuntutkos.getAmaunTuntutan();
                    } else if (permohonantuntutkos.getKodTuntut().getKod().equals("DISFT")) {
                        hakmilikSementara = permohonantuntutkos.getAmaunTuntutan();
                    }
//                    else if(permohonantuntutkos.getKodTuntut().getNama().equals("Jumlah")){
//                        jumlah = permohonantuntutkos.getAmaunTuntutan();
//                    }
                }
            }
            jumlah = premium.add(cukaiBagiTahunYangPertama).add(bayaranWangPelan).add(bayaranUkur).add(tandaSempadan).add(hakmilikKekal).add(hakmilikSementara);
            LOG.debug("---------Jumlah-----------:" + jumlah);
        }

    }

    public Resolution simpan() {
        Pengguna pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        KodCawangan caw = pengguna.getKodCawangan();
        InfoAudit ia = new InfoAudit();
        ia.setDimasukOleh(pengguna);
        ia.setTarikhMasuk(new java.util.Date());


        if (premium == null) {
            premium = new BigDecimal(0);
        } else {
            premium = new BigDecimal(premium.intValue());
        }
        if (cukaiBagiTahunYangPertama == null) {
            cukaiBagiTahunYangPertama = new BigDecimal(0);
        } else {
            cukaiBagiTahunYangPertama = new BigDecimal(cukaiBagiTahunYangPertama.intValue());
        }
        if (bayaranWangPelan == null) {
            bayaranWangPelan = new BigDecimal(0);
        } else {
            bayaranWangPelan = new BigDecimal(bayaranWangPelan.intValue());
        }
        if (bayaranUkur == null) {
            bayaranUkur = new BigDecimal(0);
        } else {
            bayaranUkur = new BigDecimal(bayaranUkur.intValue());
        }
        if (tandaSempadan == null) {
            tandaSempadan = new BigDecimal(0);
        } else {
            tandaSempadan = new BigDecimal(tandaSempadan.intValue());
        }
        if (hakmilikKekal == null) {
            hakmilikKekal = new BigDecimal(0);
        } else {
            hakmilikKekal = new BigDecimal(hakmilikKekal.intValue());
        }
        if (hakmilikSementara == null) {
            hakmilikSementara = new BigDecimal(0);
        } else {
            hakmilikSementara = new BigDecimal(hakmilikSementara.intValue());
        }


        jumlah = premium.add(cukaiBagiTahunYangPertama).add(bayaranWangPelan).add(bayaranUkur).add(tandaSempadan).add(hakmilikKekal).add(hakmilikSementara);
        System.out.println("jumlah:" + jumlah);

        if (!(listtuntutankos.isEmpty())) {

            for (int i = 0; i < listtuntutankos.size(); i++) {

                permohonantuntutkos = new PermohonanTuntutanKos();
                permohonantuntutkos = listtuntutankos.get(i);
                if (permohonantuntutkos.getKodTuntut() != null) {
                    if (permohonantuntutkos.getKodTuntut().getKod().equals("DISPRM")) {
                        permohonantuntutkos.setAmaunTuntutan(premium);
                    } else if (permohonantuntutkos.getKodTuntut().getKod().equals("DISTAX")) {
                        permohonantuntutkos.setAmaunTuntutan(cukaiBagiTahunYangPertama);
                    } else if (permohonantuntutkos.getKodTuntut().getKod().equals("DISPEL")) {
                        permohonantuntutkos.setAmaunTuntutan(bayaranWangPelan);
                    } else if (permohonantuntutkos.getKodTuntut().getKod().equals("DISUKUR")) {
                        permohonantuntutkos.setAmaunTuntutan(bayaranUkur);
                    } else if (permohonantuntutkos.getKodTuntut().getKod().equals("DISSEM")) {
                        permohonantuntutkos.setAmaunTuntutan(tandaSempadan);
                    } else if (permohonantuntutkos.getKodTuntut().getKod().equals("DISQT")) {
                        permohonantuntutkos.setAmaunTuntutan(hakmilikKekal);
                    } else if (permohonantuntutkos.getKodTuntut().getKod().equals("DISFT")) {
                        permohonantuntutkos.setAmaunTuntutan(hakmilikSementara);
                    }
//                    else if(permohonantuntutkos.getKodTuntut().getNama().equals("Jumlah")){
//                        permohonantuntutkos.setAmaunTuntutan(jumlah);
//                    }
                    ia = permohonantuntutkos.getInfoAudit();
                    ia.setTarikhKemaskini(new java.util.Date());
                    ia.setDiKemaskiniOleh(pengguna);
                    permohonantuntutkos.setInfoAudit(ia);
                    permohonantuntutkos.setPermohonan(permohonan.getPermohonanSebelum());
                    permohonantuntutkos.setCawangan(caw);
                    pelupusanService.simpanPermohonanTuntutanKos1(permohonantuntutkos);
                }
            }
        }
        // Added new Code
//            PermohonanTuntutan permohonanTuntutan=new PermohonanTuntutan();
//            permohonanTuntutan = pelupusanService.findPermohonanTuntutanByKodTransName(idPermohonanSebelum,"Notis 5A");
//            if(permohonanTuntutan != null){
//                ia = permohonanTuntutan.getInfoAudit();
//                ia.setTarikhKemaskini(new java.util.Date());
//                ia.setDiKemaskiniOleh(pengguna);
//                permohonanTuntutan.setInfoAudit(ia);
//                pelupusanService.simpanPermohonanTuntutan(permohonanTuntutan);
//                // find MohonTuntutButir by MohonTuntut and MohonTuntutKos
//                PermohonanTuntutanButiran permohonanTuntutanButiran=new PermohonanTuntutanButiran();
//                permohonanTuntutanButiran = pelupusanService.findPermohonanTuntutButirByIdTuntutAndIdKos(permohonantuntutkos.getIdKos(),permohonanTuntutan.getIdTuntut());
//                ia = permohonanTuntutanButiran.getInfoAudit();
//                ia.setTarikhKemaskini(new java.util.Date());
//                ia.setDiKemaskiniOleh(pengguna);
//                permohonanTuntutanButiran.setInfoAudit(ia);
//                pelupusanService.simpanPermohonanTuntutanButiran(permohonanTuntutanButiran);
//            }
//         }
//            if((listtuntutankos.size()==1)&& permohonantuntutkos.getKodTuntut().getKod().equals("DISPRM")){
////              
//                String[] itemList = {"DISTAX", "DISPEL", "DISUKUR", "DISSEM", "DISQT","DISFT"};
//                String[] kodKList = {"61403", "12110", "12108", "12109", "30080","30010"};
//              BigDecimal [] amaunTuntutanList = {cukaiBagiTahunYangPertama, bayaranWangPelan, bayaranUkur, tandaSempadan, hakmilikKekal,hakmilikSementara};
//                // added new code
//               if(permohonantuntutkos.getKodTuntut().getKod().equals("DISPRM")){
//                        permohonantuntutkos.setAmaunTuntutan(premium);
//                    }
//                PermohonanTuntutan permohonanTuntutan=new PermohonanTuntutan();
//                KodTransaksiTuntut  kodTransTuntut = new KodTransaksiTuntut();
//                kodTransTuntut = pelupusanService.findKodTransTuntutByName("Notis 5A");
//                permohonanTuntutan.setCawangan(caw);
//                permohonanTuntutan.setPermohonan(permohonan.getPermohonanSebelum());
//                permohonanTuntutan.setInfoAudit(ia);
//                permohonanTuntutan.setKodTransaksiTuntut(kodTransTuntut);
//                permohonanTuntutan.setTarikhTuntutan(new java.util.Date());
//                pelupusanService.simpanPermohonanTuntutan(permohonanTuntutan);
//
//            for(int i = 0; i < 6; i++) {
//                KodTuntut kodTuntut = new KodTuntut();
//                //kodTuntut = pembangunanServ.findKodTuntutByName(itemList[i]);
//                 KodTransaksi kt = new KodTransaksi();
//                kodTuntut = kodTuntutDAO.findById(itemList[i]);
//                kt= kodTransaksiDAO.findById(kodKList[i]);
//                LOG.debug("---------kodTuntut---------:"+kodTuntut);
//                permohonantuntutkos = new PermohonanTuntutanKos();
//                permohonantuntutkos.setKodTuntut(kodTuntut);
//                permohonantuntutkos.setKodTransaksi(kt);
//                permohonantuntutkos.setInfoAudit(ia);
//                permohonantuntutkos.setItem(kodTuntut.getNama());
//                permohonantuntutkos.setAmaunTuntutan(amaunTuntutanList[i]);
//                permohonantuntutkos.setPermohonan(permohonan.getPermohonanSebelum());
//                permohonantuntutkos.setCawangan(caw);
//
//                pelupusanService.simpanPermohonanTuntutanKos1(permohonantuntutkos);
//
//                // added new code
//                LOG.debug("---------PermohonanTuntutanButiran---------:");
//                PermohonanTuntutanButiran permohonanButir=new PermohonanTuntutanButiran();
//                permohonanButir.setPermohonanTuntutan(permohonanTuntutan);
//                permohonanButir.setPermohonanTuntutanKos(permohonantuntutkos);
//                permohonanButir.setInfoAudit(ia);
//                pelupusanService.simpanPermohonanTuntutanButiran(permohonanButir);
//            }
//
//            }
//
//        } else {
//            String[] itemList = {"DISPRM","DISTAX", "DISPEL", "DISUKUR", "DISSEM", "DISQT","DISFT"};
//            String[] kodKList = {"12301","61403", "12110", "12108", "12109", "30080","30010"};
//            BigDecimal [] amaunTuntutanList = { premium,cukaiBagiTahunYangPertama, bayaranWangPelan, bayaranUkur, tandaSempadan, hakmilikKekal,hakmilikSementara};
//                // added new code
//                PermohonanTuntutan permohonanTuntutan=new PermohonanTuntutan();
//                KodTransaksiTuntut  kodTransTuntut = new KodTransaksiTuntut();
//                kodTransTuntut = pelupusanService.findKodTransTuntutByName("Notis 5A");
//                permohonanTuntutan.setCawangan(caw);
//                permohonanTuntutan.setPermohonan(permohonan);
//                permohonanTuntutan.setInfoAudit(ia);
//                permohonanTuntutan.setKodTransaksiTuntut(kodTransTuntut);
//                permohonanTuntutan.setTarikhTuntutan(new java.util.Date());
//                pelupusanService.simpanPermohonanTuntutan(permohonanTuntutan);
//
//            for(int i = 0; i < 7; i++) {
//                KodTuntut kodTuntut = new KodTuntut();
//                //kodTuntut = pembangunanServ.findKodTuntutByName(itemList[i]);
//                KodTransaksi kt = new KodTransaksi();
//                kodTuntut = kodTuntutDAO.findById(itemList[i]);
//                kt= kodTransaksiDAO.findById(kodKList[i]);
//                LOG.debug("---------kodTuntut---------:"+kodTuntut);
//                permohonantuntutkos = new PermohonanTuntutanKos();
//                permohonantuntutkos.setKodTuntut(kodTuntut);
//                permohonantuntutkos.setInfoAudit(ia);
//                permohonantuntutkos.setKodTransaksi(kt);
//                permohonantuntutkos.setItem(kodTuntut.getNama());
//                permohonantuntutkos.setAmaunTuntutan(amaunTuntutanList[i]);
//                permohonantuntutkos.setPermohonan(permohonan.getPermohonanSebelum());
//                permohonantuntutkos.setCawangan(caw);
//                pelupusanService.simpanPermohonanTuntutanKos1(permohonantuntutkos);
//
//                // added new code
//                LOG.debug("---------PermohonanTuntutanButiran---------:");
//                PermohonanTuntutanButiran permohonanButir=new PermohonanTuntutanButiran();
//                permohonanButir.setPermohonanTuntutan(permohonanTuntutan);
//                permohonanButir.setPermohonanTuntutanKos(permohonantuntutkos);
//                permohonanButir.setInfoAudit(ia);
//                pelupusanService.simpanPermohonanTuntutanButiran(permohonanButir);
//            }
        //     }
        jumlah = premium.add(cukaiBagiTahunYangPertama).add(bayaranWangPelan).add(bayaranUkur).add(tandaSempadan).add(hakmilikKekal).add(hakmilikSementara);
        addSimpleMessage("Maklumat telah berjaya disimpan.");
        return new JSP("pelupusan/rayuan/borang_5A_mlk.jsp").addParameter("tab", "true");
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

    public BigDecimal getTandaSempadan() {
        return tandaSempadan;
    }

    public void setTandaSempadan(BigDecimal tandaSempadan) {
        this.tandaSempadan = tandaSempadan;
    }

    public BigDecimal getHakmilikKekal() {
        return hakmilikKekal;
    }

    public void setHakmilikKekal(BigDecimal hakmilikKekal) {
        this.hakmilikKekal = hakmilikKekal;
    }

    public BigDecimal getCukaiBagiTahunYangPertama() {
        return cukaiBagiTahunYangPertama;
    }

    public void setCukaiBagiTahunYangPertama(BigDecimal cukaiBagiTahunYangPertama) {
        this.cukaiBagiTahunYangPertama = cukaiBagiTahunYangPertama;
    }

    public BigDecimal getBayaranWangPelan() {
        return bayaranWangPelan;
    }

    public void setBayaranWangPelan(BigDecimal bayaranWangPelan) {
        this.bayaranWangPelan = bayaranWangPelan;
    }

    public BigDecimal getHakmilikSementara() {
        return hakmilikSementara;
    }

    public void setHakmilikSementara(BigDecimal hakmilikSementara) {
        this.hakmilikSementara = hakmilikSementara;
    }

    public String getIdPermohonan() {
        return idPermohonan;
    }

    public void setIdPermohonan(String idPermohonan) {
        this.idPermohonan = idPermohonan;
    }

    public KodKadarPremium getKodKadarPremium() {
        return kodKadarPremium;
    }

    public void setKodKadarPremium(KodKadarPremium kodKadarPremium) {
        this.kodKadarPremium = kodKadarPremium;
    }

    public String getIdPermohonanSebelum() {
        return idPermohonanSebelum;
    }

    public void setIdPermohonanSebelum(String idPermohonanSebelum) {
        this.idPermohonanSebelum = idPermohonanSebelum;
    }
}