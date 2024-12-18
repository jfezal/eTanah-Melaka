/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.strata;

import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.dao.HakmilikDAO;
import etanah.dao.HakmilikPermohonanDAO;
import etanah.dao.PermohonanDAO;
import etanah.model.AduanStrata;
import etanah.model.Hakmilik;
import etanah.model.HakmilikPermohonan;
import etanah.model.HakmilikPihakBerkepentingan;
import etanah.model.InfoAudit;
import etanah.model.KodUrusan;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.service.StrataPtService;
import etanah.service.common.HakmilikPihakKepentinganService;
import net.sourceforge.stripes.action.UrlBinding;
import etanah.view.BasicTabActionBean;
import etanah.view.etanahActionBeanContext;
import java.util.ArrayList;
import java.util.List;
import net.sourceforge.stripes.action.Before;
import org.apache.log4j.Logger;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.controller.LifecycleStage;
import net.sourceforge.stripes.action.ForwardResolution;

/**
 *
 * @author wan.fairul
 */
@UrlBinding("/strata/maklumat_tanah")
public class MaklumatTanahActionBean extends BasicTabActionBean {

    @Inject
    StrataPtService strService1;
    @Inject
    HakmilikDAO hakmilikDAO;
    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    HakmilikPermohonanDAO hakmilikPermohonanDAO;
    @Inject
    HakmilikPihakKepentinganService hakmilikPihakKepentinganService;
    @Inject
    etanah.Configuration conf;
    private Permohonan permohonan;
    private Hakmilik hakmilik;
    private String idHakmilik;
    private String idPermohonanTerdahulu;
//    private HakmilikPermohonan hakmilikPermohonan;
    List<HakmilikPermohonan> listHakmilikP = new ArrayList();
    List<HakmilikPihakBerkepentingan> listHakmilikPihak;
    List<HakmilikPihakBerkepentingan> listHakmilikPihak2;
    List<HakmilikPihakBerkepentingan> listHP;
    List<HakmilikPermohonan> listHakmilikUrusan;
    List<HakmilikPermohonan> listHakmilikPermohonan;
    private static final Logger LOG = Logger.getLogger(MaklumatTanahActionBean.class);
    private String jarakDari;
    private String lokasiTanah;
    private String kBpm;
    private String bpm;
    private String nLot;
    private String nHakmilik;
    private static String kodNegeri;
    private String kodNegeri1;
    private List<Hakmilik> hakmilikList;
    private String idHakmilikinduk;

    @DefaultHandler
    public Resolution showForm() {
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        if (idPermohonan != null) {
            permohonan = permohonanDAO.findById(idPermohonan);
            if (permohonan.getSenaraiHakmilik().size() != 0) {
                idHakmilik = permohonan.getSenaraiHakmilik().get(0).getHakmilik().getIdHakmilik();
                hakmilik = hakmilikDAO.findById(idHakmilik);
                listHakmilikPermohonan = strService1.getHakmilikPermohonanAKByIdHakmilik(idHakmilik);
                listHakmilikUrusan = strService1.getHakmilikUrusanAkByIdHakmilik(idHakmilik);
            }
        }
        return new JSP("strata/pbbm/maklumatTanahList.jsp").addParameter("tab", "true");
    }

    public Resolution hakmilikDetail() {
        hakmilik = hakmilikDAO.findById(idHakmilik);
        return new ForwardResolution("/WEB-INF/jsp/strata/pbbm/maklumat_hakmilik_detail.jsp").addParameter("popup", "true");
    }

    public Resolution showFormRayuan() {
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        String msg = getContext().getRequest().getParameter("message");
        String error = getContext().getRequest().getParameter("error");
        idPermohonanTerdahulu = (String) getContext().getRequest().getSession().getAttribute("idPermohonanTerdahulu");
        permohonan = permohonanDAO.findById(idPermohonan);
        if (permohonan.getPermohonanSebelum() != null) {
            if (!permohonan.getPermohonanSebelum().getSenaraiHakmilik().isEmpty()) {
                listHakmilikP = permohonan.getPermohonanSebelum().getSenaraiHakmilik();
            }
        } else {
            listHakmilikP = permohonan.getSenaraiHakmilik();
        }
        return new JSP("strata/pbbm/maklumatTanahList.jsp").addParameter("tab", "true");
    }

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {
        String idPermohonan1 = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        if (idPermohonan1 != null) {
            permohonan = permohonanDAO.findById(idPermohonan1);
            listHakmilikP = strService1.getMaklumatTan(idPermohonan1);
            if (permohonan.getSenaraiHakmilik().size() != 0) {
                if (permohonan.getKodUrusan().getKod().equals("HT")) {
                    listHakmilikPihak = hakmilikPihakKepentinganService.findHakmilikPihakByKod((permohonan.getPermohonanSebelum().getSenaraiHakmilik().get(0)).getHakmilik(), "PM");
                } else {
                    for (HakmilikPermohonan hkm : permohonan.getSenaraiHakmilik()) {
                        if (hkm.getHakmilik().getKodStatusHakmilik().getKod().equals("D")) {
                            listHakmilikPihak = hakmilikPihakKepentinganService.findHakmilikPihakByKod(hkm.getHakmilik(), "PM");
                        }
                    }
                }
                LOG.info("----listHakmilikPihak----" + listHakmilikPihak.size());
                idHakmilik = permohonan.getSenaraiHakmilik().get(0).getHakmilik().getIdHakmilik();
                hakmilik = hakmilikDAO.findById(idHakmilik);

                /*to make textformation */
                String kBpm1 = hakmilik.getDaerah().getNama().toLowerCase();
                int kBpm2 = kBpm1.length();
                kBpm = kBpm1.substring(0, 1).toUpperCase().concat(kBpm1.substring(1, kBpm2));

                String kBpm3 = hakmilik.getBandarPekanMukim().getNama().toLowerCase();
                int kBpm4 = kBpm3.length();
                bpm = kBpm3.substring(0, 1).toUpperCase().concat(kBpm3.substring(1, kBpm4));
            }
        }

        // Untuk perihal tanah ruang udara.  Check maklumat jarakDari & lokasiTanah.
        if (!listHakmilikP.isEmpty() && listHakmilikP != null) {
            HakmilikPermohonan hp = listHakmilikP.get(0);

            if (hp != null) {
                jarakDari = hp.getJarakDari();
                lokasiTanah = hp.getLokasi();
            }
        }
    }

    public Resolution showMaklumatTanah() {
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        if (idPermohonan != null) {
            permohonan = permohonanDAO.findById(idPermohonan);
            idHakmilik = permohonan.getSenaraiHakmilik().get(0).getHakmilik().getIdHakmilik();
            hakmilik = hakmilikDAO.findById(idHakmilik);
        }
        return new JSP("strata/Ruang_Udara/maklumat_sediatanah.jsp").addParameter("tab", "true");
    }

    public Resolution showForm2() {
        String kodNegeri = conf.getProperty("kodNegeri");
        kodNegeri1 = conf.getProperty("kodNegeri");
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        LOG.info("kodNegeri: " + kodNegeri);
        //to get data for maklumat Tanah at Pendaftaran hakmilik_strata.xml stage kemasukan
        if (kodNegeri.equals("05")) {
            if (permohonan.getKodUrusan().getKod().equals("HT")
                    || permohonan.getKodUrusan().getKod().equals("HTSPV")) {
                Permohonan idpsb = new Permohonan();
                idpsb = permohonan.getPermohonanSebelum();
                List<HakmilikPermohonan> hakmilikPermohonanList1 = new ArrayList<HakmilikPermohonan>();
                hakmilikPermohonanList1 = idpsb.getSenaraiHakmilik();
                String hkp1 = hakmilikPermohonanList1.get(0).getHakmilik().getIdHakmilik();
                hakmilik = hakmilikDAO.findById(hkp1);
                nLot = hakmilik.getNoLot().replaceAll("^0*", "");
            } else {
                if (idPermohonan != null) {
                    permohonan = permohonanDAO.findById(idPermohonan);
                    List<HakmilikPermohonan> hakmilikPermohonanList1 = new ArrayList<HakmilikPermohonan>();
                    hakmilikPermohonanList1 = strService1.findIdHakmilikSortAsc(idPermohonan);
                    String hkp1 = hakmilikPermohonanList1.get(0).getHakmilik().getIdHakmilik();
                    hakmilik = hakmilikDAO.findById(hkp1);
                    nLot = hakmilik.getNoLot().replaceAll("^0*", "");
                }
            }
        } else {
            if (idPermohonan != null) {
                permohonan = permohonanDAO.findById(idPermohonan);
                List<HakmilikPermohonan> hakmilikPermohonanList1 = new ArrayList<HakmilikPermohonan>();
                hakmilikPermohonanList1 = strService1.findIdHakmilikSortAsc(idPermohonan);
                String hkp1 = hakmilikPermohonanList1.get(0).getHakmilik().getIdHakmilik();
                hakmilik = hakmilikDAO.findById(hkp1);
                nLot = hakmilik.getNoLot().replaceAll("^0*", "");
                LOG.debug("---nLot---" + nLot);
            }
        }
        return new JSP("strata/pbbm/maklumatTanah.jsp").addParameter("tab", "true");
    }

    public Resolution perihalTanahRuangUdara() {
        String kodNegeri = conf.getProperty("kodNegeri");
        kodNegeri1 = conf.getProperty("kodNegeri");
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        nLot = hakmilik.getNoLot().replaceAll("^0*", "");
        if (idPermohonan != null) {
            permohonan = permohonanDAO.findById(idPermohonan);
            if (permohonan.getSenaraiHakmilik().size() != 0) {
                idHakmilik = permohonan.getSenaraiHakmilik().get(0).getHakmilik().getIdHakmilik();
                hakmilik = hakmilikDAO.findById(idHakmilik);
            }
        }
        return new JSP("strata/Ruang_Udara/maklumat_sediatanah.jsp").addParameter("tab", "true");
    }

    public Resolution simpanPerihalTanahRuangUdara() {
        String kodNegeri = conf.getProperty("kodNegeri");
        kodNegeri1 = conf.getProperty("kodNegeri");
        nLot = hakmilik.getNoLot().replaceAll("^0*", "");
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        Pengguna pguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        LOG.info("==SIMPAN PERIHAL TANAH==");
        InfoAudit ia = new InfoAudit();
        HakmilikPermohonan hp = new HakmilikPermohonan();
        if (idPermohonan != null) {
            permohonan = permohonanDAO.findById(idPermohonan);
            listHakmilikP = strService1.getMaklumatTan(idPermohonan);
            if (!listHakmilikP.isEmpty() && listHakmilikP != null) {
                LOG.info("IF : Senarai Mohon Hakmilik not empty and not null");
                hp = listHakmilikP.get(0);
                ia = hp.getInfoAudit();
                ia.setDiKemaskiniOleh(pguna);
                ia.setTarikhKemaskini(new java.util.Date());
                hp.setInfoAudit(ia);
                hp.setJarakDari(jarakDari);
                hp.setLokasi(lokasiTanah);
                try {
                    hp = strService1.saveHakmilikPermohonan(hp);
                    addSimpleMessage("Maklumat Telah Berjaya Disimpan");
                } catch (Exception e) {
                    LOG.error(e);
                    addSimpleError("Maklumat gagal disimpan. Terdapat ralat : " + e);
                }
            }
            idHakmilik = permohonan.getSenaraiHakmilik().get(0).getHakmilik().getIdHakmilik();
            hakmilik = hakmilikDAO.findById(idHakmilik);
        }
        rehydrate();
        return new JSP("strata/Ruang_Udara/maklumat_sediatanah.jsp").addParameter("tab", "true");
    }

    public Resolution showButiranHakmilik() {
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = permohonanDAO.findById(idPermohonan);

        if (permohonan != null) {
            nLot = hakmilik.getNoLot().replaceAll("^0*", "");
            nHakmilik = hakmilik.getNoHakmilik().replaceAll("^0*", "");
            KodUrusan ku = permohonan.getKodUrusan();
            if (ku.getKod().equals("P8") || ku.getKod().equals("P40A") || ku.getKod().equals("P14A")
                    || ku.getKod().equals("P22A") || ku.getKod().equals("P22B") || ku.getKod().equals("P20A")) {
                AduanStrata aduanStrata = strService1.findAduanStrataIdMohon(idPermohonan);
                if (aduanStrata != null) {
                    if (aduanStrata.getHakmilik() != null) {
                        hakmilik = aduanStrata.getHakmilik();
                        listHakmilikPihak2 = hakmilikPihakKepentinganService.findPihakActiveByHakmilik(hakmilik);
                    }
                }
            }
        }
        return new JSP("strata/common/butiran_hakmilik_tanah.jsp").addParameter("tab", "true");
    }

    public Resolution showRTPS() {
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        if (idPermohonan != null) {
            permohonan = permohonanDAO.findById(idPermohonan);
            List<HakmilikPermohonan> hakmilikPermohonanList1 = new ArrayList<HakmilikPermohonan>();
            hakmilikPermohonanList1 = strService1.findIdHakmilikSortAsc(idPermohonan);
            String hkp1 = hakmilikPermohonanList1.get(0).getHakmilik().getIdHakmilik();
            hakmilik = hakmilikDAO.findById(hkp1);
            nLot = hakmilik.getNoLot().replaceAll("^0*", "");
        }
        return new JSP("strata/pbbm/maklumatTanah_RTPS.jsp").addParameter("tab", "true");
    }

    public Resolution showPNSS() {
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        if (idPermohonan != null) {
            permohonan = permohonanDAO.findById(idPermohonan);
            List<Hakmilik> hakmilikListtemp = new ArrayList<Hakmilik>();
            hakmilikListtemp = strService1.findIdHakmilikByIdHakmilikInduk(permohonan.getSenaraiHakmilik().get(0).getHakmilik().getIdHakmilik());
            idHakmilikinduk = hakmilikListtemp.get(0).getIdHakmilikInduk();
            hakmilikList = new ArrayList<Hakmilik>();
            for (int i = 0; i < hakmilikListtemp.size(); i++) {
                Hakmilik hk = new Hakmilik();
                hk = hakmilikListtemp.get(i);
                hakmilikList.add(hk);
            }
        }
        return new JSP("strata/pbbm/maklumatTanah_PNSS.jsp").addParameter("tab", "true");
    }

    public Resolution showFormPopupPNSS() {

        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        idHakmilik = getContext().getRequest().getParameter("idHakmilik");
        if (idHakmilik != null) {
            hakmilik = hakmilikDAO.findById(idHakmilik);
            nLot = hakmilik.getNoLot().replaceAll("^0*", "");
            LOG.debug("---nLot---" + nLot);
            listHP = hakmilikPihakKepentinganService.findHakmilikPihakByKod(hakmilik, "PM");
        }
        return new JSP("strata/pbbm/maklumatTanahListPNSS.jsp").addParameter("popup", "true");
    }

    public String getKodNegeri1() {
        return kodNegeri1;
    }

    public void setKodNegeri1(String kodNegeri1) {
        this.kodNegeri1 = kodNegeri1;
    }

    public static String getKodNegeri() {
        return kodNegeri;
    }

    public static void setKodNegeri(String kodNegeri) {
        MaklumatTanahActionBean.kodNegeri = kodNegeri;
    }

    public Permohonan getPermohonan() {
        return permohonan;
    }

    public void setPermohonan(Permohonan permohonan) {
        this.permohonan = permohonan;
    }

    public Hakmilik getHakmilik() {
        return hakmilik;
    }

    public void setHakmilik(Hakmilik hakmilik) {
        this.hakmilik = hakmilik;
    }

    public String getIdHakmilik() {
        return idHakmilik;
    }

    public void setIdHakmilik(String idHakmilik) {
        this.idHakmilik = idHakmilik;
    }

    public StrataPtService getStrService1() {
        return strService1;
    }

    public void setStrService1(StrataPtService strService1) {
        this.strService1 = strService1;
    }

    public List<HakmilikPermohonan> getListHakmilikP() {
        return listHakmilikP;
    }

    public void setListHakmilikP(List<HakmilikPermohonan> listHakmilikP) {
        this.listHakmilikP = listHakmilikP;
    }

    public List<HakmilikPihakBerkepentingan> getListHakmilikPihak() {
        return listHakmilikPihak;
    }

    public void setListHakmilikPihak(List<HakmilikPihakBerkepentingan> listHakmilikPihak) {
        this.listHakmilikPihak = listHakmilikPihak;
    }

    public String getJarakDari() {
        return jarakDari;
    }

    public void setJarakDari(String jarakDari) {
        this.jarakDari = jarakDari;
    }

    public String getLokasiTanah() {
        return lokasiTanah;
    }

    public void setLokasiTanah(String lokasiTanah) {
        this.lokasiTanah = lokasiTanah;
    }

    public List<HakmilikPihakBerkepentingan> getListHakmilikPihak2() {
        return listHakmilikPihak2;
    }

    public void setListHakmilikPihak2(List<HakmilikPihakBerkepentingan> listHakmilikPihak2) {
        this.listHakmilikPihak2 = listHakmilikPihak2;
    }

    public String getkBpm() {
        return kBpm;
    }

    public void setkBpm(String kBpm) {
        this.kBpm = kBpm;
    }

    public String getBpm() {
        return bpm;
    }

    public void setBpm(String bpm) {
        this.bpm = bpm;
    }

    public String getnLot() {
        return nLot;
    }

    public void setnLot(String nLot) {
        this.nLot = nLot;
    }

    public String getnHakmilik() {
        return nHakmilik;
    }

    public void setnHakmilik(String nHakmilik) {
        this.nHakmilik = nHakmilik;
    }

    public List<Hakmilik> getHakmilikList() {
        return hakmilikList;
    }

    public void setHakmilikList(List<Hakmilik> hakmilikList) {
        this.hakmilikList = hakmilikList;
    }

    public String getIdHakmilikinduk() {
        return idHakmilikinduk;
    }

    public void setIdHakmilikinduk(String idHakmilikinduk) {
        this.idHakmilikinduk = idHakmilikinduk;
    }

    public List<HakmilikPihakBerkepentingan> getListHP() {
        return listHP;
    }

    public void setListHP(List<HakmilikPihakBerkepentingan> listHP) {
        this.listHP = listHP;
    }

    public List<HakmilikPermohonan> getListHakmilikUrusan() {
        return listHakmilikUrusan;
    }

    public void setListHakmilikUrusan(List<HakmilikPermohonan> listHakmilikUrusan) {
        this.listHakmilikUrusan = listHakmilikUrusan;
    }

    public List<HakmilikPermohonan> getListHakmilikPermohonan() {
        return listHakmilikPermohonan;
    }

    public void setListHakmilikPermohonan(List<HakmilikPermohonan> listHakmilikPermohonan) {
        this.listHakmilikPermohonan = listHakmilikPermohonan;
    }
    
}
