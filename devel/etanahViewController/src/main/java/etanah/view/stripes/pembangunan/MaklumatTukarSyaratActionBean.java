/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.stripes.pembangunan;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.dao.HakmilikDAO;
import etanah.dao.HakmilikPermohonanDAO;
import etanah.dao.KodKategoriTanahDAO;
import etanah.dao.KodKegunaanTanahDAO;
import etanah.dao.KodSekatanKepentinganDAO;
import etanah.dao.KodSyaratNyataDAO;
import etanah.dao.PemohonDAO;
import etanah.dao.PermohonanDAO;
import etanah.dao.PermohonanKertasDAO;
import etanah.model.Hakmilik;
import etanah.model.HakmilikPermohonan;
import etanah.model.InfoAudit;
import etanah.model.KodKategoriTanah;
import etanah.model.KodKegunaanTanah;
import etanah.model.KodSekatanKepentingan;
import etanah.model.KodSyaratNyata;
import etanah.model.Pemohon;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.model.PermohonanKertas;
import etanah.model.PermohonanKertasKandungan;
import etanah.model.PermohonanPihak;
import etanah.model.PermohonanTuntutanKos;
import etanah.model.PihakPengarah;
import java.util.List;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;
import java.text.SimpleDateFormat;
import etanah.view.etanahActionBeanContext;
import java.util.ArrayList;
import etanah.service.PembangunanService;
import java.math.BigDecimal;
import org.apache.log4j.Logger;
import net.sourceforge.stripes.action.ForwardResolution;

/**
 *
 * @author nursyazwani Modified By k.Hazwan
 */
@UrlBinding("/pembangunan/maklumat_tukarSyarat")
public class MaklumatTukarSyaratActionBean extends AbleActionBean {

    private static final Logger LOG = Logger.getLogger(MaklumatTukarSyaratActionBean.class);
    @Inject
    KodKategoriTanahDAO kodKategoriTanahDAO;
    @Inject
    KodSyaratNyataDAO kodSyaratNyataDAO;
    @Inject
    KodSekatanKepentinganDAO kodSekatanKepentinganDAO;
    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    HakmilikDAO hakmilikDAO;
    @Inject
    PemohonDAO pemohonDAO;
    @Inject
    PermohonanKertasDAO permohonanKertasDAO;
    @Inject
    KodKegunaanTanahDAO kodKegunaanTanahDAO;
    @Inject
    PembangunanService devService;
    @Inject
    etanah.Configuration conf;
    private Permohonan permohonan;
    private HakmilikPermohonan hp;
    private Hakmilik hakmilik;
    private String gunaAsal;
    private String gunaBaru;
    private String kenaGuna;
    private String batalUngakapan;
    private String batalSyarat;
    private String syaratAsal;
    private String syaratBaru;
    private String batalSekatan;
    private String sekatanAsal;
    private String sekatanBaru;
    private BigDecimal item1;
    private BigDecimal item2;
    private List<KodSyaratNyata> listKodSyaratNyata;
    private List<KodSekatanKepentingan> listKodSekatan;
    private List<PermohonanTuntutanKos> listtuntutankos;
    private String syaratNama;
    private String sekatanNama;
    private BigDecimal premium;
    private BigDecimal cukai;
    private BigDecimal premiumDenda;
    private BigDecimal sumbangan;
    private BigDecimal pendaftaran;
    private BigDecimal bayaran;
    private BigDecimal jumlah;
    private BigDecimal sewaBaru;
    private BigDecimal bornag7c;
    private BigDecimal dendap;
    private BigDecimal penulisan;
    private BigDecimal bayaranUpahUkur;
    private BigDecimal tandaSempadan;
    private BigDecimal penyediaan;
    private BigDecimal semantara;
    private BigDecimal notis;
    private BigDecimal borang7c;
    private BigDecimal hasilThnPertama;
    private PermohonanTuntutanKos permohonantuntutkos;
    private List<KodKegunaanTanah> senaraiKodKegunaanTanahs0;
    private List<KodKegunaanTanah> senaraiKodKegunaanTanahs1;
    private List<KodKegunaanTanah> senaraiKodKegunaanTanahs2;
    private List<KodKegunaanTanah> senaraiKodKegunaanTanahs3;
    private List<KodKegunaanTanah> senaraiKodKegunaanTanahs4;
    private List<KodKegunaanTanah> senaraiKodKegunaanTanahs5;
    private List<KodKegunaanTanah> senaraiKodKegunaanTanahs8;
    private List<KodKegunaanTanah> senaraiKodKegunaanTanahs11;
    private HakmilikPermohonan hakmilikPermohonan = new HakmilikPermohonan();

    public Resolution kodSekatanPopup() {
        return new JSP("common/carian_kodsekatan.jsp").addParameter("popup", "true");
    }

    public Resolution kodSyaratNyataPopup() {
        return new JSP("pembangunan/TS_PTD/carian_kodSyaratNyata.jsp").addParameter("popup", "true");
    }

    @DefaultHandler
    public Resolution showForm() {
        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        return new JSP("pembangunan/TS_PTD/maklumat_tukar_syarat.jsp").addParameter("tab", "true");
    }

    public Resolution showForm2() {
        return new JSP("pembangunan/TS_PTD/maklumat_tukar_syarat.jsp").addParameter("tab", "true");
    }

    public Resolution showForm3() {
        return new JSP("pembangunan/TS_PTD/maklumat_tukar_syarat_after_endosan.jsp").addParameter("tab", "true");
    }

    public Resolution showBorang7c() {
        premium = BigDecimal.ZERO;
        cukai = BigDecimal.ZERO;
        premiumDenda = BigDecimal.ZERO;
        sumbangan = BigDecimal.ZERO;
        pendaftaran = BigDecimal.ZERO;
        bayaran = BigDecimal.ZERO;
        sewaBaru = BigDecimal.ZERO;
        bornag7c = BigDecimal.ZERO;
        jumlah = BigDecimal.ZERO;
        dendap = BigDecimal.ZERO;
        penulisan = BigDecimal.ZERO;
        bayaranUpahUkur = BigDecimal.ZERO;
        tandaSempadan = BigDecimal.ZERO;
        penyediaan = BigDecimal.ZERO;
        semantara = BigDecimal.ZERO;
        notis = BigDecimal.ZERO;
        borang7c = BigDecimal.ZERO;
        hasilThnPertama = BigDecimal.ZERO;
        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        List<PermohonanTuntutanKos> tuntutanKosList = new ArrayList<PermohonanTuntutanKos>();
        tuntutanKosList = devService.findTuntutByIdMohon(idPermohonan);
        if (!tuntutanKosList.isEmpty()) {
            for (PermohonanTuntutanKos tuntutKos : tuntutanKosList) {
                if (tuntutKos.getKodTuntut().getKod().equals("DEV04")) {
                    item1 = tuntutKos.getAmaunTuntutan();
                }
                if (tuntutKos.getKodTuntut().getKod().equals("DEV10")) {
                    item2 = tuntutKos.getAmaunTuntutan();
                }
            }
        }
        listtuntutankos = devService.findTuntutByIdMohon(idPermohonan);
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
                    } else if (permohonantuntutkos.getKodTuntut().getKod().equals("DEV11")) {
                        hasilThnPertama = permohonantuntutkos.getAmaunTuntutan();
                    }
                }
            }
            jumlah = premium.add(sumbangan).add(penulisan).add(sewaBaru).add(bayaranUpahUkur).add(tandaSempadan).add(penyediaan).add(pendaftaran).add(semantara).add(notis).add(borang7c).add(dendap);
        }
        return new JSP("pembangunan/TS_PTD/sedia_borang7C.jsp").addParameter("tab", "true");
    }

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {
        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = permohonanDAO.findById(idPermohonan);
        hp = new HakmilikPermohonan();
        hakmilikPermohonan = new HakmilikPermohonan();

        String kodNegeri = conf.getKodNegeri();
        if (kodNegeri.equalsIgnoreCase("05")) {
            Permohonan mhn = new Permohonan();
            mhn = devService.findByIdPermohonanFilterByUrusan(idPermohonan, "TSSKL");
            if (mhn != null) {
                System.out.println("Id Perserahan : " + mhn.getIdPermohonan());
                hakmilikPermohonan = devService.findByIdPermohonan(mhn.getIdPermohonan());
            }
        }

        if (!permohonan.getSenaraiHakmilik().isEmpty()) {
            hp = permohonan.getSenaraiHakmilik().get(0);
            hakmilik = hakmilikDAO.findById(hp.getHakmilik().getIdHakmilik());
        }

        HakmilikPermohonan hpm = devService.findByIdPermohonan(idPermohonan);
        if (hpm != null) {

            if (hp.getKategoriTanahBaru() != null) {
                gunaBaru = hp.getKategoriTanahBaru().getKod();
            } else {
                if (hpm.getHakmilik().getKategoriTanah() != null) {
                    hpm.setKategoriTanahBaru(hpm.getHakmilik().getKategoriTanah());
                    devService.simpanHakmilikPermohonan(hpm);
                }
            }

            if (hp.getKodKegunaanTanah() != null) {
                kenaGuna = hp.getKodKegunaanTanah().getKod();
            } else {
                if (hpm.getHakmilik().getKegunaanTanah() != null) {
                    hpm.setKodKegunaanTanah(hpm.getHakmilik().getKegunaanTanah());
                    devService.simpanHakmilikPermohonan(hpm);
                }
            }
        }

        if (hp != null) {
            batalUngakapan = hp.getCatatan();
            if (hp.getKategoriTanahBaru() != null) {
                gunaBaru = hp.getKategoriTanahBaru().getKod();
            }
            if (hp.getKodKegunaanTanah() != null) {
                kenaGuna = hp.getKodKegunaanTanah().getKod();
            }
        }



    }

    public Resolution simpan() {
        LOG.info("-----simpan----:");
        List<HakmilikPermohonan> hpList = new ArrayList<HakmilikPermohonan>();
        hpList = permohonan.getSenaraiHakmilik();
        if (!hpList.isEmpty()) {
            for (HakmilikPermohonan hp : hpList) {
                LOG.info("-----batalUngakapan-catatan---:" + batalUngakapan);
                hp.setCatatan(batalUngakapan);
                if (gunaBaru != null) {
                    hp.setKategoriTanahBaru(kodKategoriTanahDAO.findById(gunaBaru));
                } else {
                    hp.setKategoriTanahBaru(hp.getHakmilik().getKategoriTanah());
                }

                if (kenaGuna != null) {
                    hp.setKodKegunaanTanah(kodKegunaanTanahDAO.findById(kenaGuna));
                    LOG.info("---kenaGuna----:" + kenaGuna);
                } else {
                    hp.setKodKegunaanTanah(hp.getHakmilik().getKegunaanTanah());
                }


                String syaratBaru1 = getContext().getRequest().getParameter("syaratBaru1");
                LOG.info("-----syaratBaru----:" + syaratBaru1);
                if (syaratBaru1 != null && !syaratBaru1.equals("")) {
                    LOG.info("---if--syaratBaru----:" + syaratBaru1);
                    hp.setSyaratNyataBaru(kodSyaratNyataDAO.findById(syaratBaru1));
                } else {
                    LOG.info("---if--syaratBaru----:" + syaratBaru1);
                    hp.setSyaratNyataBaru(null);
                }
                String sekatanBaru1 = getContext().getRequest().getParameter("sekatanBaru1");
                LOG.info("-----sekatanBaru----:" + sekatanBaru1);
                if (sekatanBaru1 != null && !sekatanBaru1.equals("")) {
                    LOG.info("--if---sekatanBaru----:" + sekatanBaru1);
                    hp.setSekatanKepentinganBaru(kodSekatanKepentinganDAO.findById(sekatanBaru1));
                } else {
                    LOG.info("--else---sekatanBaru----:" + sekatanBaru1);
                    hp.setSekatanKepentinganBaru(null);
                }
                devService.simpanHakmilikPermohonan(hp);
            }
        }
        rehydrate();
        addSimpleMessage("Maklumat telah berjaya disimpan.");
        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        return new JSP("pembangunan/TS_PTD/maklumat_tukar_syarat.jsp").addParameter("tab", "true");
    }

    public Permohonan getPermohonan() {
        return permohonan;
    }

    public void setPermohonan(Permohonan permohonan) {
        this.permohonan = permohonan;
    }

    public String getBatalSekatan() {
        return batalSekatan;
    }

    public void setBatalSekatan(String batalSekatan) {
        this.batalSekatan = batalSekatan;
    }

    public String getBatalSyarat() {
        return batalSyarat;
    }

    public void setBatalSyarat(String batalSyarat) {
        this.batalSyarat = batalSyarat;
    }

    public String getBatalUngakapan() {
        return batalUngakapan;
    }

    public void setBatalUngakapan(String batalUngakapan) {
        this.batalUngakapan = batalUngakapan;
    }

    public String getGunaAsal() {
        return gunaAsal;
    }

    public void setGunaAsal(String gunaAsal) {
        this.gunaAsal = gunaAsal;
    }

    public String getGunaBaru() {
        return gunaBaru;
    }

    public void setGunaBaru(String gunaBaru) {
        this.gunaBaru = gunaBaru;
    }

    public String getKenaGuna() {
        return kenaGuna;
    }

    public void setKenaGuna(String kenaGuna) {
        this.kenaGuna = kenaGuna;
    }

    public String getSekatanAsal() {
        return sekatanAsal;
    }

    public void setSekatanAsal(String sekatanAsal) {
        this.sekatanAsal = sekatanAsal;
    }

    public String getSekatanBaru() {
        return sekatanBaru;
    }

    public void setSekatanBaru(String sekatanBaru) {
        this.sekatanBaru = sekatanBaru;
    }

    public String getSyaratAsal() {
        return syaratAsal;
    }

    public void setSyaratAsal(String syaratAsal) {
        this.syaratAsal = syaratAsal;
    }

    public String getSyaratBaru() {
        return syaratBaru;
    }

    public void setSyaratBaru(String syaratBaru) {
        this.syaratBaru = syaratBaru;
    }

    public Hakmilik getHakmilik() {
        return hakmilik;
    }

    public void setHakmilik(Hakmilik hakmilik) {
        this.hakmilik = hakmilik;
    }

    public HakmilikPermohonan getHp() {
        return hp;
    }

    public void setHp(HakmilikPermohonan hp) {
        this.hp = hp;
    }

    public BigDecimal getItem1() {
        return item1;
    }

    public void setItem1(BigDecimal item1) {
        this.item1 = item1;
    }

    public BigDecimal getItem2() {
        return item2;
    }

    public void setItem2(BigDecimal item2) {
        this.item2 = item2;
    }

    public List<KodSyaratNyata> getListKodSyaratNyata() {
        return listKodSyaratNyata;
    }

    public void setListKodSyaratNyata(List<KodSyaratNyata> listKodSyaratNyata) {
        this.listKodSyaratNyata = listKodSyaratNyata;
    }

    public List<KodSekatanKepentingan> getListKodSekatan() {
        return listKodSekatan;
    }

    public void setListKodSekatan(List<KodSekatanKepentingan> listKodSekatan) {
        this.listKodSekatan = listKodSekatan;
    }

    public String getSekatanNama() {
        return sekatanNama;
    }

    public void setSekatanNama(String sekatanNama) {
        this.sekatanNama = sekatanNama;
    }

    public String getSyaratNama() {
        return syaratNama;
    }

    public void setSyaratNama(String syaratNama) {
        this.syaratNama = syaratNama;
    }

    public BigDecimal getJumlah() {
        return jumlah;
    }

    public void setJumlah(BigDecimal jumlah) {
        this.jumlah = jumlah;
    }

    public List<KodKegunaanTanah> getSenaraiKodKegunaanTanahs0() {
        return devService.getSenaraiKegunaanTanah0();
    }

    public void setSenaraiKodKegunaanTanahs0(List<KodKegunaanTanah> senaraiKodKegunaanTanahs0) {
        this.senaraiKodKegunaanTanahs0 = senaraiKodKegunaanTanahs0;
    }

    public List<KodKegunaanTanah> getSenaraiKodKegunaanTanahs1() {
        //return senaraiKodKegunaanTanahs1;
        return devService.getSenaraiKegunaanTanah1();
    }

    public void setSenaraiKodKegunaanTanahs1(List<KodKegunaanTanah> senaraiKodKegunaanTanahs1) {
        this.senaraiKodKegunaanTanahs1 = senaraiKodKegunaanTanahs1;
    }

    public List<KodKegunaanTanah> getSenaraiKodKegunaanTanahs2() {
        //return senaraiKodKegunaanTanahs2;
        return devService.getSenaraiKegunaanTanah2();
    }

    public void setSenaraiKodKegunaanTanahs2(List<KodKegunaanTanah> senaraiKodKegunaanTanahs2) {
        this.senaraiKodKegunaanTanahs2 = senaraiKodKegunaanTanahs2;
    }

    public List<KodKegunaanTanah> getSenaraiKodKegunaanTanahs3() {
        //return senaraiKodKegunaanTanahs3;
        return devService.getSenaraiKegunaanTanah3();
    }

    public void setSenaraiKodKegunaanTanahs3(List<KodKegunaanTanah> senaraiKodKegunaanTanahs3) {
        this.senaraiKodKegunaanTanahs3 = senaraiKodKegunaanTanahs3;
    }

    public List<KodKegunaanTanah> getSenaraiKodKegunaanTanahs4() {
        //return senaraiKodKegunaanTanahs4;
        return devService.getSenaraiKegunaanTanah4();
    }

    public void setSenaraiKodKegunaanTanahs4(List<KodKegunaanTanah> senaraiKodKegunaanTanahs4) {
        this.senaraiKodKegunaanTanahs4 = senaraiKodKegunaanTanahs4;
    }

    public List<KodKegunaanTanah> getSenaraiKodKegunaanTanahs5() {
        //return senaraiKodKegunaanTanahs5;
        return devService.getSenaraiKegunaanTanah5();
    }

    public void setSenaraiKodKegunaanTanahs5(List<KodKegunaanTanah> senaraiKodKegunaanTanahs5) {
        this.senaraiKodKegunaanTanahs5 = senaraiKodKegunaanTanahs5;
    }

    public List<KodKegunaanTanah> getSenaraiKodKegunaanTanahs8() {
        //return senaraiKodKegunaanTanahs8;
        return devService.getSenaraiKegunaanTanah8();
    }

    public void setSenaraiKodKegunaanTanahs8(List<KodKegunaanTanah> senaraiKodKegunaanTanahs8) {
        this.senaraiKodKegunaanTanahs8 = senaraiKodKegunaanTanahs8;
    }

    public List<KodKegunaanTanah> getSenaraiKodKegunaanTanahs11() {
        //return senaraiKodKegunaanTanahs11;
        return devService.getSenaraiKegunaanTanah11();
    }

    public void setSenaraiKodKegunaanTanahs11(List<KodKegunaanTanah> senaraiKodKegunaanTanahs11) {
        this.senaraiKodKegunaanTanahs11 = senaraiKodKegunaanTanahs11;
    }

    public HakmilikPermohonan getHakmilikPermohonan() {
        return hakmilikPermohonan;
    }

    public void setHakmilikPermohonan(HakmilikPermohonan hakmilikPermohonan) {
        this.hakmilikPermohonan = hakmilikPermohonan;
    }

    public BigDecimal getHasilThnPertama() {
        return hasilThnPertama;
    }

    public void setHasilThnPertama(BigDecimal hasilThnPertama) {
        this.hasilThnPertama = hasilThnPertama;
    }

    public BigDecimal getPremium() {
        return premium;
    }

    public void setPremium(BigDecimal premium) {
        this.premium = premium;
    }
}
