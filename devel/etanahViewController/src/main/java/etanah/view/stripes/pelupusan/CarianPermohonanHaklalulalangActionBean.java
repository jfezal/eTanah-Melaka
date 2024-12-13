/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.stripes.pelupusan;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.dao.PermohonanDAO;
import etanah.dao.PermohonanTuntutanKosDAO;
import etanah.dao.HakmilikPermohonanDAO;
import etanah.dao.PemohonDAO;
import etanah.model.HakmilikPermohonan;
import etanah.model.HakmilikPermohonan;
import etanah.model.LaporanTanah;
import etanah.model.LaporanTanahSempadan;
import etanah.model.NoPt;
import etanah.model.Pemohon;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.model.PermohonanLaporanPelan;
import etanah.model.PermohonanRujukanLuar;
import etanah.model.PermohonanTuntutanKos;
import etanah.service.PelupusanService;
import etanah.service.common.PermohonanService;
import etanah.view.etanahActionBeanContext;
import etanah.view.stripes.pelupusan.disClass.DisHakmilikPermohonan;
import etanah.view.stripes.pelupusan.disClass.DisLaporanTanah;
import etanah.view.stripes.pelupusan.disClass.DisLaporanTanahService;
import etanah.workflow.WorkFlowService;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.OnwardResolution;
import net.sourceforge.stripes.action.RedirectResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.controller.LifecycleStage;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.validation.Validate;
import net.sourceforge.stripes.validation.ValidateNestedProperties;
import oracle.bpel.services.workflow.task.model.Task;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

/**
 *
 * @author afham
 */
@UrlBinding("/pelupusan/carian_permohonan_lalulalang")
public class CarianPermohonanHaklalulalangActionBean extends AbleActionBean {

    @Inject
    DisLaporanTanahService disLaporanTanahService;
    private Permohonan permohonan;
    private Permohonan permohonanSebelum;
    private Pengguna pengguna;
    private List<HakmilikPermohonan> listHakmilikPermohonan;
    private List<HakmilikPermohonan> listHakmilikPermohonanLalulalang;
    private List<LaporanTanah> laporanTanahSebelum;
    private List<PermohonanLaporanPelan> laporanPelanSebelum;
    private DisHakmilikPermohonan disHakmilikPermohonan = new DisHakmilikPermohonan();
    private String idPermohonan;
    private String idPermohonanSebelum = "";
    private static final String DEFAULT_VIEW = "pelupusan/pbhl/carian_permohonan_lalu_lalang.jsp";
    private static final Logger LOG = Logger.getLogger(CarianPermohonanHaklalulalangActionBean.class);

    @DefaultHandler
    public Resolution showForm() {
        return new JSP(DEFAULT_VIEW).addParameter("tab", true);
    }

    public Resolution carianSemula() {
        getContext().getRequest().setAttribute("false", Boolean.TRUE);
        return showForm();
    }

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");

        if (idPermohonan != null) {
            permohonan = disLaporanTanahService.getPelupusanService().findPermohonanByIdPermohonan1(idPermohonan);
            if (permohonan != null) {
                if (permohonan.getPermohonanSebelum() != null) {
                    permohonanSebelum = permohonan.getPermohonanSebelum();

                    if (permohonanSebelum != null) {

                        getContext().getRequest().setAttribute("status", Boolean.TRUE);
                        getContext().getRequest().setAttribute("simpan", Boolean.TRUE);

                        listHakmilikPermohonan = new ArrayList<HakmilikPermohonan>();
                        listHakmilikPermohonan = disLaporanTanahService.getPelupusanService().getHakmilikPermohonanListByCatatanNIdMohon(new String(), permohonanSebelum.getIdPermohonan());

                        listHakmilikPermohonanLalulalang = new ArrayList<HakmilikPermohonan>();
                        listHakmilikPermohonanLalulalang = disLaporanTanahService.getPelupusanService().getHakmilikPermohonanListByCatatanNIdMohon("L", permohonanSebelum.getIdPermohonan());


                    } else {
                        getContext().getRequest().setAttribute("status", Boolean.FALSE);
                    }
                }
            }
        }
    }

    public Resolution cariPermohonan() {

        LOG.info("idPermohonanSebelum :" + idPermohonanSebelum);
        if (idPermohonanSebelum != null) {
            permohonanSebelum = disLaporanTanahService.getPelupusanService().findPermohonanByIdPermohonan1(idPermohonanSebelum);

            if (permohonanSebelum != null) {

                getContext().getRequest().setAttribute("status", Boolean.TRUE);

                listHakmilikPermohonan = new ArrayList<HakmilikPermohonan>();
                listHakmilikPermohonan = disLaporanTanahService.getPelupusanService().getHakmilikPermohonanListByCatatanNIdMohon(new String(), idPermohonanSebelum);

                listHakmilikPermohonanLalulalang = new ArrayList<HakmilikPermohonan>();
                listHakmilikPermohonanLalulalang = disLaporanTanahService.getPelupusanService().getHakmilikPermohonanListByCatatanNIdMohon("L", idPermohonanSebelum);
                addSimpleMessage("Maklumat dijumpai");

            } else {
                addSimpleError("Maklumat tidak dijumpai");
                getContext().getRequest().setAttribute("status", Boolean.FALSE);

            }
        } else {
            addSimpleError("Sila Masukkan Id Permohonan");
        }

        return new JSP("pelupusan/pbhl/carian_permohonan_lalu_lalang.jsp").addParameter("tab", Boolean.TRUE);
    }

    public Resolution simpanPermohonanSebelum() {
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        if (idPermohonan != null && idPermohonanSebelum != null) {
            permohonan = disLaporanTanahService.getPelupusanService().findPermohonanByIdPermohonan1(idPermohonan);
            permohonanSebelum = disLaporanTanahService.getPelupusanService().findPermohonanByIdPermohonan1(idPermohonanSebelum);
            listHakmilikPermohonan = new ArrayList<HakmilikPermohonan>();
            listHakmilikPermohonan = disLaporanTanahService.getPelupusanService().getHakmilikPermohonanListByCatatanNIdMohon(new String(), idPermohonanSebelum);

            listHakmilikPermohonanLalulalang = new ArrayList<HakmilikPermohonan>();
            listHakmilikPermohonanLalulalang = disLaporanTanahService.getPelupusanService().getHakmilikPermohonanListByCatatanNIdMohon("L", idPermohonanSebelum);

            if (permohonanSebelum != null) {
                permohonan.setPermohonanSebelum(permohonanSebelum);
                disLaporanTanahService.getPelupusanService().simpanPermohonan(permohonan);
            }
        }
        if (!listHakmilikPermohonan.isEmpty()) {
            for (HakmilikPermohonan hp : listHakmilikPermohonan) {
                LOG.info("Copy Data (HakmilikPermohonan Pemilik)");
                HakmilikPermohonan mohonHakmilikBaru = new HakmilikPermohonan();
                mohonHakmilikBaru.setInfoAudit(permohonan.getInfoAudit());
                mohonHakmilikBaru.setPermohonan(permohonan);
                mohonHakmilikBaru.setKeteranganCukaiBaru(hp.getKeteranganCukaiBaru() != null ? hp.getKeteranganCukaiBaru() : new String());
                mohonHakmilikBaru.setKadarPremium(hp.getKadarPremium() != BigDecimal.ZERO ? hp.getKadarPremium() : BigDecimal.ZERO);
                mohonHakmilikBaru.setSyaratNyataBaru(hp.getSyaratNyataBaru() != null ? hp.getSyaratNyataBaru() : null);
                mohonHakmilikBaru.setSekatanKepentinganBaru(hp.getSekatanKepentinganBaru() != null ? hp.getSekatanKepentinganBaru() : null);
                mohonHakmilikBaru.setKodMilik(hp.getKodMilik() != null ? hp.getKodMilik() : null);
                mohonHakmilikBaru.setAgensiUpahUkur(hp.getAgensiUpahUkur() != null ? hp.getAgensiUpahUkur() : null);
                if (hp.getHakmilik() != null) {
                    mohonHakmilikBaru = disHakmilikPermohonan.copyPropertiesHakmilikToMH(permohonan, mohonHakmilikBaru, hp.getHakmilik(), new String[]{}, disLaporanTanahService);
                    mohonHakmilikBaru.setHakmilik(hp.getHakmilik());
                    mohonHakmilikBaru.setCatatan(hp.getCatatan() != null ? hp.getCatatan() : new String());
                } else {
                    String kbpm = new String();
                    String ksek = new String();
                    String khakmilik = new String();
                    String klot = new String();
                    String kktanah = new String();
                    String ksyarat = new String();
                    String kguna = new String();
                    String ksekatan = new String();
                    String tP = new String();
                    String luas = new String();
                    String kuom = new String();
                    String cukai = new String();

                    kbpm = hp.getBandarPekanMukimBaru() != null ? String.valueOf(hp.getBandarPekanMukimBaru().getKod()) : null;
                    ksek = hp.getKodSeksyen() != null ? String.valueOf(hp.getKodSeksyen().getKod()) : null;
                    khakmilik = hp.getKodHakmilik() != null ? hp.getKodHakmilik().getKod() : null;
                    klot = hp.getLot() != null ? hp.getLot().getKod() : null;
                    kktanah = hp.getKategoriTanahBaru() != null ? hp.getKategoriTanahBaru().getKod() : null;
                    ksyarat = hp.getSyaratNyataBaru() != null ? hp.getSyaratNyataBaru().getKod() : null;
                    kguna = hp.getKodKegunaanTanah() != null ? hp.getKodKegunaanTanah().getKod() : null;
                    ksekatan = hp.getSekatanKepentinganBaru() != null ? hp.getSekatanKepentinganBaru().getKod() : null;
                    tP = hp.getTempohPajakan() != null ? String.valueOf(hp.getTempohPajakan()) : null;
                    luas = hp.getLuasTerlibat() != null ? hp.getLuasTerlibat().toString() : null;
                    kuom = hp.getKodUnitLuas() != null ? hp.getKodUnitLuas().getKod() : null;
                    cukai = hp.getCukaiBaru() != null ? hp.getCukaiBaru().toString() : null;
                    mohonHakmilikBaru = disHakmilikPermohonan.copyPropertiesHakmilikToMH(permohonan, mohonHakmilikBaru, hp.getHakmilik(), new String[]{kbpm, ksek, khakmilik, klot, hp.getNoLot(), hp.getLokasi(), kktanah, ksyarat, kguna, ksekatan, hp.getPegangan(), tP, luas, kuom, cukai}, disLaporanTanahService);
                }
                disLaporanTanahService.getPelupusanService().simpanHakmilikPermohonan(mohonHakmilikBaru);
            }
        }

        if (!listHakmilikPermohonanLalulalang.isEmpty()) {
            for (HakmilikPermohonan hp : listHakmilikPermohonanLalulalang) {
                LOG.info("Copy Data (HakmilikPermohonan Lalu Lalang)");
                HakmilikPermohonan mohonHakmilikBaru = new HakmilikPermohonan();
                mohonHakmilikBaru.setInfoAudit(permohonan.getInfoAudit());
                mohonHakmilikBaru.setPermohonan(permohonan);
                mohonHakmilikBaru.setKeteranganCukaiBaru(hp.getKeteranganCukaiBaru() != null ? hp.getKeteranganCukaiBaru() : new String());
                mohonHakmilikBaru.setKadarPremium(hp.getKadarPremium() != BigDecimal.ZERO ? hp.getKadarPremium() : BigDecimal.ZERO);
                mohonHakmilikBaru.setSyaratNyataBaru(hp.getSyaratNyataBaru() != null ? hp.getSyaratNyataBaru() : null);
                mohonHakmilikBaru.setSekatanKepentinganBaru(hp.getSekatanKepentinganBaru() != null ? hp.getSekatanKepentinganBaru() : null);
                mohonHakmilikBaru.setKodMilik(hp.getKodMilik() != null ? hp.getKodMilik() : null);
                mohonHakmilikBaru.setAgensiUpahUkur(hp.getAgensiUpahUkur() != null ? hp.getAgensiUpahUkur() : null);
                if (hp.getHakmilik() != null) {
                    mohonHakmilikBaru = disHakmilikPermohonan.copyPropertiesHakmilikToMH(permohonan, mohonHakmilikBaru, hp.getHakmilik(), new String[]{}, disLaporanTanahService);
                    mohonHakmilikBaru.setHakmilik(hp.getHakmilik());
                    mohonHakmilikBaru.setCatatan(hp.getCatatan() != null ? hp.getCatatan() : new String());
                } else {
                    String kbpm = new String();
                    String ksek = new String();
                    String khakmilik = new String();
                    String klot = new String();
                    String kktanah = new String();
                    String ksyarat = new String();
                    String kguna = new String();
                    String ksekatan = new String();
                    String tP = new String();
                    String luas = new String();
                    String kuom = new String();
                    String cukai = new String();

                    kbpm = hp.getBandarPekanMukimBaru() != null ? String.valueOf(hp.getBandarPekanMukimBaru().getKod()) : null;
                    ksek = hp.getKodSeksyen() != null ? String.valueOf(hp.getKodSeksyen().getKod()) : null;
                    khakmilik = hp.getKodHakmilik() != null ? hp.getKodHakmilik().getKod() : null;
                    klot = hp.getLot() != null ? hp.getLot().getKod() : null;
                    kktanah = hp.getKategoriTanahBaru() != null ? hp.getKategoriTanahBaru().getKod() : null;
                    ksyarat = hp.getSyaratNyataBaru() != null ? hp.getSyaratNyataBaru().getKod() : null;
                    kguna = hp.getKodKegunaanTanah() != null ? hp.getKodKegunaanTanah().getKod() : null;
                    ksekatan = hp.getSekatanKepentinganBaru() != null ? hp.getSekatanKepentinganBaru().getKod() : null;
                    tP = hp.getTempohPajakan() != null ? String.valueOf(hp.getTempohPajakan()) : null;
                    luas = hp.getLuasTerlibat() != null ? hp.getLuasTerlibat().toString() : null;
                    kuom = hp.getKodUnitLuas() != null ? hp.getKodUnitLuas().getKod() : null;
                    cukai = hp.getCukaiBaru() != null ? hp.getCukaiBaru().toString() : null;
                    mohonHakmilikBaru = disHakmilikPermohonan.copyPropertiesHakmilikToMH(permohonan, mohonHakmilikBaru, hp.getHakmilik(), new String[]{kbpm, ksek, khakmilik, klot, hp.getNoLot(), hp.getLokasi(), kktanah, ksyarat, kguna, ksekatan, hp.getPegangan(), tP, luas, kuom, cukai}, disLaporanTanahService);
                }
                disLaporanTanahService.getPelupusanService().simpanHakmilikPermohonan(mohonHakmilikBaru);

                LOG.info("Copy Data (Laporan Pelan)");
                PermohonanLaporanPelan lp = new PermohonanLaporanPelan();
                lp = disLaporanTanahService.getPelupusanService().findByIdPermohonanPelanNIdMH(idPermohonanSebelum, hp.getId());
                if (lp != null) {
                    PermohonanLaporanPelan mohonLaporPelan = new PermohonanLaporanPelan();
                    mohonLaporPelan.setCawangan(permohonan.getCawangan());
                    mohonLaporPelan.setPermohonan(permohonan);
                    mohonLaporPelan.setInfoAudit(disLaporanTanahService.findAudit(mohonLaporPelan, "new", pengguna));
                    mohonLaporPelan.setHakmilikPermohonan(hp);
                    mohonLaporPelan.setNoLitho(lp.getNoLitho() != null ? lp.getNoLitho() : new String());
                    mohonLaporPelan.setCatatan(lp.getCatatan());
                    mohonLaporPelan.setProjekKerajaan(lp.getProjekKerajaan());
                    mohonLaporPelan.setKodTanah(lp.getKodTanah() != null ? lp.getKodTanah() : null);

                    disLaporanTanahService.getPelupusanService().simpanPermohonanLaporanPelan(mohonLaporPelan);
                }

                LOG.info("Copy Data (Laporan Tanah)");
                LaporanTanah lt = new LaporanTanah();
                lt = disLaporanTanahService.getPelupusanService().findLaporanTanahByIdMH(hp.getId());
                if (lt != null) {
                    LaporanTanah mohonLaporTanah = new LaporanTanah();
                    List<LaporanTanahSempadan> laporTanahSempadanList = new ArrayList<LaporanTanahSempadan>();
                    laporTanahSempadanList = disLaporanTanahService.getPelupusanService().findLaporTanahSmpdnByIdLapor(lt.getIdLaporan());
                    DisLaporanTanah disLaporanTanah = new DisLaporanTanah();
                    mohonLaporTanah.setInfoAudit(permohonan.getInfoAudit());
                    mohonLaporTanah.setPermohonan(permohonan);
                    mohonLaporTanah.setHakmilikPermohonan(mohonHakmilikBaru);
                    mohonLaporTanah = disLaporanTanah.copyPropertiesLaporanTanah(permohonan, mohonLaporTanah, lt, disLaporanTanahService);


                    disLaporanTanahService.getPlpservice().simpanLaporanTanah(mohonLaporTanah);

                    if (laporTanahSempadanList.size() > 0) {
                        for (LaporanTanahSempadan lts : laporTanahSempadanList) {
                            LaporanTanahSempadan ltsTemp = new LaporanTanahSempadan();
                            ltsTemp.setInfoAudit(permohonan.getInfoAudit());
                            ltsTemp.setLaporanTanah(mohonLaporTanah);
                            ltsTemp.setGuna(lts.getGuna());
                            ltsTemp.setKeadaanTanah(lts.getKeadaanTanah());
                            ltsTemp.setJenisSempadan(lts.getJenisSempadan());
                            ltsTemp.setMilikKerajaan(lts.getMilikKerajaan());
                            ltsTemp.setNoLot(lts.getNoLot() != null ? lts.getNoLot() : new String());
                            ltsTemp.setKodLot(lts.getKodLot() != null ? lts.getKodLot() : null);
                            disLaporanTanahService.getPlpservice().simpanLaporanTanahSempadan(ltsTemp);
                        }
                    }
                }
            }
        }
        if(permohonanSebelum.getSenaraiPemohon().size() > 0){
            LOG.info("Copy Data (Pemohon)");
            for(Pemohon p : permohonanSebelum.getSenaraiPemohon()){
                Pemohon pemohonTemp = new Pemohon() ;
                pemohonTemp.setCawangan(p.getCawangan());
                pemohonTemp.setInfoAudit(permohonan.getInfoAudit());
                pemohonTemp.setPihak(p.getPihak());
                pemohonTemp.setPermohonan(p.getPermohonan());
                disLaporanTanahService.getPelupusanService().simpanPemohon(pemohonTemp);
            }
        }
        
        LOG.info("Copy Data (MohonRujukanLuar)");
        if (permohonanSebelum.getSenaraiRujukanLuar().size() > 0) {
            for (PermohonanRujukanLuar prl : permohonanSebelum.getSenaraiRujukanLuar()) {
                PermohonanRujukanLuar prlTemp = new PermohonanRujukanLuar();
                prlTemp.setInfoAudit(permohonan.getInfoAudit());
                prlTemp.setAgensi(prl.getAgensi() != null ? prl.getAgensi() : null);
                prlTemp.setPermohonan(permohonan);
                prlTemp.setUlasan(prl.getUlasan() != null ? prl.getUlasan() : new String());
                prlTemp.setNamaAgensi(prl.getNamaAgensi() != null ? prl.getNamaAgensi() : new String());
                prlTemp.setNoRujukan(prl.getNoRujukan() != null ? prl.getNoRujukan() : new String());
                prlTemp.setTarikhRujukan(prl.getTarikhRujukan() != null ? prl.getTarikhRujukan() : new java.util.Date());
                prlTemp.setKodRujukan(prl.getKodRujukan());
                prlTemp.setCawangan(prl.getCawangan());

                disLaporanTanahService.getPelupusanService().simpanPermohonanRujukanLuar(prlTemp);
            }
        }
        addSimpleMessage("Maklumat telah disimpan");
        rehydrate();
        return new JSP("pelupusan/pbhl/carian_permohonan_lalu_lalang.jsp").addParameter("tab", Boolean.TRUE);
    }

    public Permohonan getPermohonan() {
        return permohonan;
    }

    public void setPermohonan(Permohonan permohonan) {
        this.permohonan = permohonan;
    }

    public Permohonan getPermohonanSebelum() {
        return permohonanSebelum;
    }

    public void setPermohonanSebelum(Permohonan permohonanSebelum) {
        this.permohonanSebelum = permohonanSebelum;
    }

    public List<HakmilikPermohonan> getListHakmilikPermohonan() {
        return listHakmilikPermohonan;
    }

    public void setListHakmilikPermohonan(List<HakmilikPermohonan> listHakmilikPermohonan) {
        this.listHakmilikPermohonan = listHakmilikPermohonan;
    }

    public List<HakmilikPermohonan> getListHakmilikPermohonanLalulalang() {
        return listHakmilikPermohonanLalulalang;
    }

    public void setListHakmilikPermohonanLalulalang(List<HakmilikPermohonan> listHakmilikPermohonanLalulalang) {
        this.listHakmilikPermohonanLalulalang = listHakmilikPermohonanLalulalang;
    }

    public List<LaporanTanah> getLaporanTanahSebelum() {
        return laporanTanahSebelum;
    }

    public void setLaporanTanahSebelum(List<LaporanTanah> laporanTanahSebelum) {
        this.laporanTanahSebelum = laporanTanahSebelum;
    }

    public List<PermohonanLaporanPelan> getLaporanPelanSebelum() {
        return laporanPelanSebelum;
    }

    public void setLaporanPelanSebelum(List<PermohonanLaporanPelan> laporanPelanSebelum) {
        this.laporanPelanSebelum = laporanPelanSebelum;
    }

    public String getIdPermohonan() {
        return idPermohonan;
    }

    public void setIdPermohonan(String idPermohonan) {
        this.idPermohonan = idPermohonan;
    }

    public String getIdPermohonanSebelum() {
        return idPermohonanSebelum;
    }

    public void setIdPermohonanSebelum(String idPermohonanSebelum) {
        this.idPermohonanSebelum = idPermohonanSebelum;
    }
}
