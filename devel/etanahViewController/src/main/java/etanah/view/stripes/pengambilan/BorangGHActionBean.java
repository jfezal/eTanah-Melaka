/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.stripes.pengambilan;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.dao.HakmilikDAO;
import etanah.dao.PermohonanDAO;
import etanah.model.FasaPermohonan;
import etanah.model.Hakmilik;
import etanah.model.HakmilikPermohonan;
import etanah.model.Permohonan;
import etanah.model.ambil.PermohonanPengambilan;
import etanah.model.PermohonanPihak;
import etanah.model.PermohonanRujukanLuar;
import etanah.model.ambil.HakmilikPerbicaraan;
import etanah.model.ambil.Penilaian;
import etanah.model.ambil.PerbicaraanKehadiran;
import etanah.service.PengambilanService;
import etanah.service.common.FasaPermohonanService;
import etanah.service.common.LelongService;
import etanah.service.common.NotisPenerimaanService;
import etanah.service.daftar.PembetulanService;

import java.math.BigDecimal;
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
 * @author Rajesh Reddy
 */
@UrlBinding("/pengambilan/borangGH")
public class BorangGHActionBean extends AbleActionBean {

    private static final Logger LOG = Logger.getLogger(PenerimaanNotisBorang831ANSActionBean.class);
    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    HakmilikDAO hakmilikDAO;
    @Inject
    PembetulanService pembetulanService;
    @Inject
    PengambilanService pengambilanService;
    @Inject
    FasaPermohonanService fasaPermohonanService;
    @Inject
    NotisPenerimaanService notisPenerimaanService;
    private Hakmilik hakmilik;
    private HakmilikPermohonan hakmilikPermohonan;
    private HakmilikPerbicaraan hakmilikPerbicaraan;
    private PermohonanPengambilan permohonanPengambilan;
    private PermohonanRujukanLuar permohonanRujukanLuar;
    private List<PermohonanRujukanLuar> permohonanRujukanLuarList;
    private String idPermohonan;
    private String idHakmilik;
    private BigDecimal itemNilaianTanah;
    private BigDecimal itemNilaianBngn;
    private BigDecimal itemNilaianLain;
    private BigDecimal itemNilaianTanah2;
    private BigDecimal itemNilaianBngn2;
    private BigDecimal itemNilaianLain2;
    private List<Penilaian> penilaianList;
    private List<Penilaian> penilaianList2;
    @Inject
    LelongService lelongService;
    @Inject
    PengambilanService service;
    private List<BigDecimal> itemNilaianTanahList = new ArrayList<BigDecimal>();
    ;
    private List<BigDecimal> itemNilaianBngnList = new ArrayList<BigDecimal>();
    ;
    private List<BigDecimal> itemNilaianLainList = new ArrayList<BigDecimal>();
    ;
    private List<HakmilikPermohonan> hakmilikPermohonanList = new ArrayList<HakmilikPermohonan>();
    private List<PerbicaraanKehadiran> senaraiPerbicaraanKehadiran = new ArrayList<PerbicaraanKehadiran>();
    private List<PerbicaraanKehadiran> senaraiHadirPBT = new ArrayList<PerbicaraanKehadiran>();
    private List<PermohonanPihak> senaraiPermohonanPihak = new ArrayList<PermohonanPihak>();
    private List<Penilaian> penilaianTanahList;
    private List<Penilaian> penilaianBngnList;
    private List<Penilaian> penilaianLainList;
    private BigDecimal jumlah;
    private List<PerbicaraanKehadiran> senaraiPM;
    private List<PerbicaraanKehadiran> senaraiNOTPM;
    private List<Penilaian> penilaianPinjamanList;
    private BigDecimal itemNilaianPinjaman;

    public BigDecimal getItemNilaianPinjaman() {
        return itemNilaianPinjaman;
    }

    public void setItemNilaianPinjaman(BigDecimal itemNilaianPinjaman) {
        this.itemNilaianPinjaman = itemNilaianPinjaman;
    }

    public List<Penilaian> getPenilaianPinjamanList() {
        return penilaianPinjamanList;
    }

    public void setPenilaianPinjamanList(List<Penilaian> penilaianPinjamanList) {
        this.penilaianPinjamanList = penilaianPinjamanList;
    }

    public List<PerbicaraanKehadiran> getSenaraiNOTPM() {
        return senaraiNOTPM;
    }

    public void setSenaraiNOTPM(List<PerbicaraanKehadiran> senaraiNOTPM) {
        this.senaraiNOTPM = senaraiNOTPM;
    }

    public List<PerbicaraanKehadiran> getSenaraiPM() {
        return senaraiPM;
    }

    public void setSenaraiPM(List<PerbicaraanKehadiran> senaraiPM) {
        this.senaraiPM = senaraiPM;
    }

    @DefaultHandler
    public Resolution showForm() {
        return new JSP("pengambilan/Borang_GH.jsp").addParameter("tab", "true");
    }

    public Resolution showForm2() {
        return new JSP("pengambilan/BorangH.jsp").addParameter("tab", "true");
    }

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");

        if (idPermohonan != null) {
            Permohonan p = permohonanDAO.findById(idPermohonan);
            // Permohonan HAkmilik Kedesakan
            FasaPermohonan fasaPermohonan = null;
            try {
                // bila split kedesakan
                fasaPermohonan = fasaPermohonanService.getKodKedesakan(idPermohonan, "DE");
                System.out.println("fasa " + fasaPermohonan.getIdAliran());
                if (fasaPermohonan != null) {
                    // listkan ade kedesakan
                    hakmilikPermohonanList = pengambilanService.findbyIdHakmilikAdaKedesakan(idPermohonan);
                }
            } catch (Exception h) {

                hakmilikPermohonanList = pengambilanService.findbyIdHakmilikTiadaKedesakan(idPermohonan);
                System.out.println("Error fasa Permohonan " + h);
            }
        }
    }

    public Resolution hakmilikDetails() {
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");

        hakmilik = hakmilikDAO.findById(idHakmilik);
        senaraiPerbicaraanKehadiran = new ArrayList<PerbicaraanKehadiran>();
        hakmilikPermohonan = pembetulanService.findByIdHakmilikIdPermohonan(idPermohonan, idHakmilik);
        permohonanRujukanLuarList = service.findByIDMohonWartaAll(idPermohonan);
        if (!permohonanRujukanLuarList.isEmpty()) {
            LOG.debug("List warta x empty");
        }
        if (hakmilikPermohonan != null) {
            hakmilikPerbicaraan = notisPenerimaanService.getHakmilikPerbicaraanByidMH(hakmilikPermohonan.getId());

            if (hakmilikPerbicaraan != null) {
                senaraiPermohonanPihak = notisPenerimaanService.getSenaraiTuanTanahMohonPihak(hakmilikPerbicaraan.getIdPerbicaraan());
//                senaraiPerbicaraanKehadiran = notisPenerimaanService.getPerbicaraanKehadiranbyidBicara2(hakmilikPerbicaraan.getIdPerbicaraan());
                senaraiPerbicaraanKehadiran = notisPenerimaanService.getPerbicaraanKehadiranPM(hakmilikPerbicaraan.getIdPerbicaraan());
                senaraiHadirPBT = notisPenerimaanService.getPerbicaraanKehadiranbyidBicaraPPTB(hakmilikPerbicaraan.getIdPerbicaraan());
                senaraiPM = notisPenerimaanService.getPerbicaraanKehadiranPM(hakmilikPerbicaraan.getIdPerbicaraan());
                senaraiNOTPM = notisPenerimaanService.getPerbicaraanKehadiranNOTPM(hakmilikPerbicaraan.getIdPerbicaraan());
                for (PerbicaraanKehadiran pp : senaraiNOTPM) {
                    itemNilaianPinjaman = BigDecimal.ZERO;
                    penilaianPinjamanList = notisPenerimaanService.getPenilaianPinjamanByidHadir(pp.getIdKehadiran());
                    for (Penilaian penilaian : penilaianPinjamanList) {
                        itemNilaianPinjaman = itemNilaianPinjaman.add(penilaian.getAmaun());
                    }

                }

                for (PerbicaraanKehadiran perbicaraanKehadiran7 : senaraiPerbicaraanKehadiran) {
                    itemNilaianTanah2 = BigDecimal.ZERO;
                    itemNilaianBngn2 = BigDecimal.ZERO;
                    itemNilaianLain2 = BigDecimal.ZERO;
                    penilaianList2 = perbicaraanKehadiran7.getSenaraiPenilaian();
                    for (int i = 0; i < penilaianList2.size(); i++) {
                        Penilaian penilaian = penilaianList2.get(i);
                        if (penilaian.getJenis() == 'T') {
                            itemNilaianTanah2 = itemNilaianTanah2.add(penilaian.getAmaun());
                        }
                        if (penilaian.getJenis() == 'B') {
                            itemNilaianBngn2 = itemNilaianBngn2.add(penilaian.getAmaun());
                        }
                        if (penilaian.getJenis() == 'L') {
                            itemNilaianLain2 = itemNilaianLain2.add(penilaian.getAmaun());
                        }
                    }
                    itemNilaianTanahList.add(itemNilaianTanah2);
                    itemNilaianBngnList.add(itemNilaianBngn2);
                    itemNilaianLainList.add(itemNilaianLain2);

                }

//            senaraiPerbicaraanKehadiran=notisPenerimaanService.getPerbicaraanKehadiranbyidBicaraPPTB(hakmilikPerbicaraan.getIdPerbicaraan());
                for (PerbicaraanKehadiran perbicaraanKehadiran : senaraiHadirPBT) {
                    perbicaraanKehadiran = notisPenerimaanService.getPerbicaraanKehadiranbyidHadir(perbicaraanKehadiran.getIdKehadiran());
                    if (perbicaraanKehadiran != null) {
                        itemNilaianTanah = BigDecimal.ZERO;
                        itemNilaianBngn = BigDecimal.ZERO;
                        itemNilaianLain = BigDecimal.ZERO;
                        penilaianTanahList = notisPenerimaanService.getPenilaianTanahByidHadir(perbicaraanKehadiran.getIdKehadiran());
                        penilaianBngnList = notisPenerimaanService.getPenilaianBngnByidHadir(perbicaraanKehadiran.getIdKehadiran());
                        penilaianLainList = notisPenerimaanService.getPenilaianLainByidHadir(perbicaraanKehadiran.getIdKehadiran());
                        for (Penilaian penilaian : penilaianTanahList) {
                            itemNilaianTanah = itemNilaianTanah.add(penilaian.getAmaun());
                        }
                        for (Penilaian penilaian : penilaianBngnList) {
                            itemNilaianBngn = itemNilaianBngn.add(penilaian.getAmaun());
                        }
                        for (Penilaian penilaian : penilaianLainList) {
                            itemNilaianLain = itemNilaianLain.add(penilaian.getAmaun());
                        }
//                jumlah = jumlah.add(itemNilaianTanah).add(itemNilaianBngn).add(itemNilaianLain);
                    }
                }








            }
        }

        getContext().getRequest().getSession().setAttribute("idHakmilik", idHakmilik);

        return new JSP("pengambilan/Borang_GH.jsp").addParameter("tab", "true");
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

    public Hakmilik getHakmilik() {
        return hakmilik;
    }

    public void setHakmilik(Hakmilik hakmilik) {
        this.hakmilik = hakmilik;
    }

    public HakmilikPerbicaraan getHakmilikPerbicaraan() {
        return hakmilikPerbicaraan;
    }

    public void setHakmilikPerbicaraan(HakmilikPerbicaraan hakmilikPerbicaraan) {
        this.hakmilikPerbicaraan = hakmilikPerbicaraan;
    }

    public HakmilikPermohonan getHakmilikPermohonan() {
        return hakmilikPermohonan;
    }

    public void setHakmilikPermohonan(HakmilikPermohonan hakmilikPermohonan) {
        this.hakmilikPermohonan = hakmilikPermohonan;
    }

    public String getIdHakmilik() {
        return idHakmilik;
    }

    public void setIdHakmilik(String idHakmilik) {
        this.idHakmilik = idHakmilik;
    }

    public BigDecimal getItemNilaianBngn() {
        return itemNilaianBngn;
    }

    public void setItemNilaianBngn(BigDecimal itemNilaianBngn) {
        this.itemNilaianBngn = itemNilaianBngn;
    }

    public List<BigDecimal> getItemNilaianBngnList() {
        return itemNilaianBngnList;
    }

    public void setItemNilaianBngnList(List<BigDecimal> itemNilaianBngnList) {
        this.itemNilaianBngnList = itemNilaianBngnList;
    }

    public BigDecimal getItemNilaianLain() {
        return itemNilaianLain;
    }

    public void setItemNilaianLain(BigDecimal itemNilaianLain) {
        this.itemNilaianLain = itemNilaianLain;
    }

    public List<BigDecimal> getItemNilaianLainList() {
        return itemNilaianLainList;
    }

    public void setItemNilaianLainList(List<BigDecimal> itemNilaianLainList) {
        this.itemNilaianLainList = itemNilaianLainList;
    }

    public BigDecimal getItemNilaianTanah() {
        return itemNilaianTanah;
    }

    public void setItemNilaianTanah(BigDecimal itemNilaianTanah) {
        this.itemNilaianTanah = itemNilaianTanah;
    }

    public List<BigDecimal> getItemNilaianTanahList() {
        return itemNilaianTanahList;
    }

    public void setItemNilaianTanahList(List<BigDecimal> itemNilaianTanahList) {
        this.itemNilaianTanahList = itemNilaianTanahList;
    }

    public List<Penilaian> getPenilaianList() {
        return penilaianList;
    }

    public void setPenilaianList(List<Penilaian> penilaianList) {
        this.penilaianList = penilaianList;
    }

    public List<PerbicaraanKehadiran> getSenaraiPerbicaraanKehadiran() {
        return senaraiPerbicaraanKehadiran;
    }

    public void setSenaraiPerbicaraanKehadiran(List<PerbicaraanKehadiran> senaraiPerbicaraanKehadiran) {
        this.senaraiPerbicaraanKehadiran = senaraiPerbicaraanKehadiran;
    }

    public PermohonanPengambilan getPermohonanPengambilan() {
        return permohonanPengambilan;
    }

    public void setPermohonanPengambilan(PermohonanPengambilan permohonanPengambilan) {
        this.permohonanPengambilan = permohonanPengambilan;
    }

    public List<Penilaian> getPenilaianBngnList() {
        return penilaianBngnList;
    }

    public void setPenilaianBngnList(List<Penilaian> penilaianBngnList) {
        this.penilaianBngnList = penilaianBngnList;
    }

    public List<Penilaian> getPenilaianLainList() {
        return penilaianLainList;
    }

    public void setPenilaianLainList(List<Penilaian> penilaianLainList) {
        this.penilaianLainList = penilaianLainList;
    }

    public List<Penilaian> getPenilaianTanahList() {
        return penilaianTanahList;
    }

    public void setPenilaianTanahList(List<Penilaian> penilaianTanahList) {
        this.penilaianTanahList = penilaianTanahList;
    }

    public List<PerbicaraanKehadiran> getSenaraiHadirPBT() {
        return senaraiHadirPBT;
    }

    public void setSenaraiHadirPBT(List<PerbicaraanKehadiran> senaraiHadirPBT) {
        this.senaraiHadirPBT = senaraiHadirPBT;
    }

    public BigDecimal getJumlah() {
        return jumlah;
    }

    public void setJumlah(BigDecimal jumlah) {
        this.jumlah = jumlah;
    }

    public BigDecimal getItemNilaianBngn2() {
        return itemNilaianBngn2;
    }

    public void setItemNilaianBngn2(BigDecimal itemNilaianBngn2) {
        this.itemNilaianBngn2 = itemNilaianBngn2;
    }

    public BigDecimal getItemNilaianLain2() {
        return itemNilaianLain2;
    }

    public void setItemNilaianLain2(BigDecimal itemNilaianLain2) {
        this.itemNilaianLain2 = itemNilaianLain2;
    }

    public BigDecimal getItemNilaianTanah2() {
        return itemNilaianTanah2;
    }

    public void setItemNilaianTanah2(BigDecimal itemNilaianTanah2) {
        this.itemNilaianTanah2 = itemNilaianTanah2;
    }

    public List<Penilaian> getPenilaianList2() {
        return penilaianList2;
    }

    public void setPenilaianList2(List<Penilaian> penilaianList2) {
        this.penilaianList2 = penilaianList2;
    }

    public PermohonanRujukanLuar getPermohonanRujukanLuar() {
        return permohonanRujukanLuar;
    }

    public void setPermohonanRujukanLuar(PermohonanRujukanLuar permohonanRujukanLuar) {
        this.permohonanRujukanLuar = permohonanRujukanLuar;
    }

    public List<PermohonanRujukanLuar> getPermohonanRujukanLuarList() {
        return permohonanRujukanLuarList;
    }

    public void setPermohonanRujukanLuarList(List<PermohonanRujukanLuar> permohonanRujukanLuarList) {
        this.permohonanRujukanLuarList = permohonanRujukanLuarList;
    }

    public List<PermohonanPihak> getSenaraiPermohonanPihak() {
        return senaraiPermohonanPihak;
    }

    public void setSenaraiPermohonanPihak(List<PermohonanPihak> senaraiPermohonanPihak) {
        this.senaraiPermohonanPihak = senaraiPermohonanPihak;
    }
}
