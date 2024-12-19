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
import etanah.model.PermohonanBangunan;
import etanah.model.PermohonanSkim;
import etanah.service.RegService;
import etanah.service.StrataPtService;
import etanah.service.common.HakmilikPihakKepentinganService;
import net.sourceforge.stripes.action.UrlBinding;
import etanah.view.BasicTabActionBean;
import etanah.view.etanahActionBeanContext;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
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
@UrlBinding("/strata/maklumat_tanah_phpp")
public class MaklumatTanahPHPPActionBean extends BasicTabActionBean {

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
    @Inject
    RegService regService;
    private Permohonan permohonan;
    private Hakmilik hakmilik;
    private String idHakmilik;
    private String idPermohonanTerdahulu;
//    private HakmilikPermohonan hakmilikPermohonan;
    List<HakmilikPermohonan> listHakmilikP = new ArrayList();
    List<HakmilikPihakBerkepentingan> listHakmilikPihak;
    List<HakmilikPihakBerkepentingan> listHakmilikPihak2;
    private static final Logger LOG = Logger.getLogger(MaklumatTanahPHPPActionBean.class);
    private String jarakDari;
    private String lokasiTanah;
    private String kBpm;
    private String bpm;
    List<HakmilikPermohonan> hakmilikPermohonanListBaru;
    String kodNegeri1;
    String kodNegeri;
    private List<Hakmilik> hakmilikList;
    private List<Hakmilik> idStrataList;
    private String nLot;
    private String jumlahUnitSyor;
    private List<HakmilikPihakBerkepentingan> pihakKepentinganList;
    private List<HakmilikPihakBerkepentingan> pihakKepentinganSelainPMList;
    private String noPelan1;
    @Inject
    StrataPtService strService;

    @DefaultHandler
    public Resolution showForm() {

        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");


        if (idPermohonan != null) {
            permohonan = permohonanDAO.findById(idPermohonan);
            if (permohonan.getSenaraiHakmilik().size() != 0) {
                idHakmilik = permohonan.getSenaraiHakmilik().get(0).getHakmilik().getIdHakmilik();
                hakmilik = hakmilikDAO.findById(idHakmilik);
            }
        }
        return new JSP("strata/pbbm/maklumatTanahListPHPP.jsp").addParameter("tab", "true");
    }

    public Resolution showFormPopup() {

        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        idHakmilik = getContext().getRequest().getParameter("idHakmilik");
        System.out.println("----idHakmilik----" + idHakmilik);

        if (idHakmilik != null) {
            hakmilik = hakmilikDAO.findById(idHakmilik);
            nLot = hakmilik.getNoLot().replaceAll("^0*", "");
            LOG.debug("---nLot---" + nLot);
        }
        return new JSP("strata/pbbm/maklumatTanahListPHPP.jsp").addParameter("popup", "true");
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

        System.out.println("----idPermohonan----" + idPermohonan1);

        if (idPermohonan1 != null) {
            permohonan = permohonanDAO.findById(idPermohonan1);
            listHakmilikP = strService1.getMaklumatTan(idPermohonan1);

            if (permohonan.getSenaraiHakmilik().size() != 0) {
                System.out.println("----Hakmilik----:" + permohonan.getSenaraiHakmilik().get(0).getHakmilik().getIdHakmilik());

                if (permohonan.getKodUrusan().getKod().equals("HT")) {
                    System.out.println("----kod urusan HT----");
                    listHakmilikPihak = hakmilikPihakKepentinganService.findHakmilikPihakByKod((permohonan.getPermohonanSebelum().getSenaraiHakmilik().get(0)).getHakmilik(), "PM");
                    System.out.println("----listHakmilikPihak----" + listHakmilikPihak.size());
                } else {
                    System.out.println("----kod urusan Not HT----");
                    listHakmilikPihak = hakmilikPihakKepentinganService.findHakmilikPihakByKod((permohonan.getSenaraiHakmilik().get(0)).getHakmilik(), "PM");
                    System.out.println("----listHakmilikPihak----" + listHakmilikPihak.size());
                }

                System.out.println("----listHakmilikPihak------:" + listHakmilikPihak.size());
                idHakmilik = permohonan.getSenaraiHakmilik().get(0).getHakmilik().getIdHakmilik();
                hakmilik = hakmilikDAO.findById(idHakmilik);

                /*to make textformation */
                String kBpm1 = hakmilik.getDaerah().getNama().toLowerCase();
                int kBpm2 = kBpm1.length();
                kBpm = kBpm1.substring(0, 1).toUpperCase().concat(kBpm1.substring(1, kBpm2));
                System.out.println("----kBpm------" + kBpm);

                String kBpm3 = hakmilik.getBandarPekanMukim().getNama().toLowerCase();
                int kBpm4 = kBpm3.length();
                bpm = kBpm3.substring(0, 1).toUpperCase().concat(kBpm3.substring(1, kBpm4));
                System.out.println("----kBpm--1----" + bpm);

            }

//            System.out.println("----hakmilikPermohonan------"+hakmilikPermohonan);
//            System.out.println("----hakmilikPermohonan------"+hakmilikPermohonan.getNoLot());
        }

        // Untuk perihal tanah ruang udara.  Check maklumat jarakDari & lokasiTanah.
        if (!listHakmilikP.isEmpty() && listHakmilikP != null) {
            HakmilikPermohonan hp = listHakmilikP.get(0);

            if (hp != null) {
                jarakDari = hp.getJarakDari();
                lokasiTanah = hp.getLokasi();

            }
        }
        kodNegeri = conf.getProperty("kodNegeri");
        kodNegeri1 = conf.getProperty("kodNegeri");
        System.out.println("----kodNegeri1----" + kodNegeri1);
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
        kodNegeri = conf.getProperty("kodNegeri");
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");

        //to get data for maklumat Tanah at Pendaftaran hakmilik_strata.xml stage kemasukan
        if (kodNegeri.equals("05")) {
            LOG.debug("---seremban---");
            if (permohonan.getKodUrusan().getKod().equals("HT") && !permohonan.getPermohonanSebelum().getKodUrusan().getKod().equals("PHPP")) {
                LOG.debug("---current Permohonan Urusan HT---");
                LOG.debug("---Permohonan Sebelum Urusan Not PHPP---");
                Permohonan idpsb = new Permohonan();
                idpsb = permohonan.getPermohonanSebelum();
                LOG.debug("---Id permohonan---SBLM---" + idpsb);
                List<HakmilikPermohonan> hakmilikPermohonanList1 = new ArrayList<HakmilikPermohonan>();
                hakmilikPermohonanList1 = idpsb.getSenaraiHakmilik();
                LOG.debug("---HakmilikPermohonanList1 from---SBLM---" + hakmilikPermohonanList1);

                String hkp1 = hakmilikPermohonanList1.get(0).getHakmilik().getIdHakmilik();
                LOG.debug("---Id Hakmilik---SBLM---" + hkp1);
                hakmilik = hakmilikDAO.findById(hkp1);
                nLot = hakmilik.getNoLot().replaceAll("^0*", "");
                LOG.debug("---Kod Negeri---" + kodNegeri);
                LOG.debug("---No Lot---" + nLot);
            } 
            else if (permohonan.getKodUrusan().getKod().equals("HT") && permohonan.getPermohonanSebelum().getKodUrusan().getKod().equals("PHPP")) {
                LOG.debug("---current Permohonan Urusan HT---");
                LOG.debug("---Permohonan Sebelum Urusan PHPP---");
                if (idPermohonan != null) {
                    permohonan = permohonanDAO.findById(permohonan.getPermohonanSebelum().getIdPermohonan());
                    hakmilikPermohonanListBaru = new ArrayList<HakmilikPermohonan>();
                    hakmilikPermohonanListBaru = strService1.findIdHakmilikSortAsc(permohonan.getIdPermohonan());
                    LOG.debug("---hakmilikPermohonanList1---" + hakmilikPermohonanListBaru.size());
                    String hkp1 = hakmilikPermohonanListBaru.get(0).getHakmilik().getIdHakmilik();
                    LOG.debug("---Id Hakmilik---" + hkp1);
                    hakmilik = hakmilikDAO.findById(hkp1);
                    nLot = hakmilik.getNoLot().replaceAll("^0*", "");
                }
            } else {
                LOG.debug("---Not HT/HTSPV---");
                if (idPermohonan != null) {
                    permohonan = permohonanDAO.findById(idPermohonan);
                    hakmilikPermohonanListBaru = new ArrayList<HakmilikPermohonan>();
                    hakmilikPermohonanListBaru = strService1.findIdHakmilikSortAsc(idPermohonan);
                    LOG.debug("---hakmilikPermohonanList1---" + hakmilikPermohonanListBaru.size());
                    String hkp1 = hakmilikPermohonanListBaru.get(0).getHakmilik().getIdHakmilik();
                    LOG.debug("---Id Hakmilik---" + hkp1);
                    hakmilik = hakmilikDAO.findById(hkp1);
                   // Hakmilik hk = strService.findIdHakmilik(hkp1);
                   // if(hk.getIdHakmilikInduk() != null)
                        
                    nLot = hakmilik.getNoLot().replaceAll("^0*", "");
                }
            }
        } else {
            LOG.debug("---Melaka---");
            if (idPermohonan != null) {
                permohonan = permohonanDAO.findById(idPermohonan);
                hakmilikPermohonanListBaru = new ArrayList<HakmilikPermohonan>();
                hakmilikPermohonanListBaru = strService1.findIdHakmilikSortAsc(idPermohonan);
                LOG.debug("---hakmilikPermohonanList1---" + hakmilikPermohonanListBaru.size());
                String hkp1 = hakmilikPermohonanListBaru.get(0).getHakmilik().getIdHakmilik();
                LOG.debug("---Id Hakmilik---" + hkp1);
                hakmilik = hakmilikDAO.findById(hkp1);
            }
        }

        return new JSP("strata/pbbm/maklumatTanahPHPP.jsp").addParameter("tab", "true");
    }

    public Resolution showFormHTSC() {
        kodNegeri = conf.getProperty("kodNegeri");
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");

        //to get data for maklumat Tanah at Pendaftaran hakmilik_strata.xml stage kemasukan
        if (kodNegeri.equals("05")) {
            LOG.debug("---seremban---");
            if (permohonan.getKodUrusan().getKod().equals("HT") && !permohonan.getPermohonanSebelum().getKodUrusan().getKod().equals("PHPP")) {
                LOG.debug("---current Permohonan Urusan HT---");
                LOG.debug("---Permohonan Sebelum Urusan Not PHPP---");
                Permohonan idpsb = new Permohonan();
                idpsb = permohonan.getPermohonanSebelum();
                LOG.debug("---Id permohonan---SBLM---" + idpsb);
                List<HakmilikPermohonan> hakmilikPermohonanList1 = new ArrayList<HakmilikPermohonan>();
                hakmilikPermohonanList1 = idpsb.getSenaraiHakmilik();
                LOG.debug("---HakmilikPermohonanList1 from---SBLM---" + hakmilikPermohonanList1);

                String hkp1 = hakmilikPermohonanList1.get(0).getHakmilik().getIdHakmilik();
                LOG.debug("---Id Hakmilik---SBLM---" + hkp1);
                hakmilik = hakmilikDAO.findById(hkp1);
                nLot = hakmilik.getNoLot().replaceAll("^0*", "");
                LOG.debug("---Kod Negeri---" + kodNegeri);
                LOG.debug("---No Lot---" + nLot);
            } 
            else if (permohonan.getKodUrusan().getKod().equals("HT") && permohonan.getPermohonanSebelum().getKodUrusan().getKod().equals("PHPP")) {
                LOG.debug("---current Permohonan Urusan HT---");
                LOG.debug("---Permohonan Sebelum Urusan PHPP---");
                if (idPermohonan != null) {
                    permohonan = permohonanDAO.findById(permohonan.getPermohonanSebelum().getIdPermohonan());
                    hakmilikPermohonanListBaru = new ArrayList<HakmilikPermohonan>();
                    hakmilikPermohonanListBaru = strService1.findIdHakmilikSortAsc(permohonan.getIdPermohonan());
                    LOG.debug("---hakmilikPermohonanList1---" + hakmilikPermohonanListBaru.size());
                    String hkp1 = hakmilikPermohonanListBaru.get(0).getHakmilik().getIdHakmilik();
                    LOG.debug("---Id Hakmilik---" + hkp1);
                    hakmilik = hakmilikDAO.findById(hkp1);
                    nLot = hakmilik.getNoLot().replaceAll("^0*", "");
                }
            } else {
                LOG.debug("---Not HT/HTSPV---");
                if (idPermohonan != null) {
                    permohonan = permohonanDAO.findById(idPermohonan);
                    hakmilikPermohonanListBaru = new ArrayList<HakmilikPermohonan>();
                    //hakmilikPermohonanListBaru = strService1.findIdHakmilikSortAsc(idPermohonan);
                    //LOG.debug("---hakmilikPermohonanList1---" + hakmilikPermohonanListBaru.size());
                    //String hkp1 = hakmilikPermohonanListBaru.get(0).getHakmilik().getIdHakmilik();
                    Permohonan idpsb = permohonan.getPermohonanSebelum();
                    //HakmilikPermohonan hakmilikSblm = strService.findIdbyIDMohon(idpsb.getIdPermohonan());
                    hakmilikPermohonanListBaru = strService1.findIdHakmilikSortAsc(idpsb.getIdPermohonan());
                    HakmilikPermohonan hakmilikSblm = strService.findIdMH(idpsb.getIdPermohonan());
                    LOG.debug("---hakmilikPermohonanList1---" + idpsb.getIdPermohonan());
                  //  String hkp1 = hakmilikSblm.getHakmilik().getIdHakmilik();
                  //  LOG.debug("---Id Hakmilik---" + hkp1);
                  //  LOG.debug("---Id Permohonan ---" + idpsb.getIdPermohonan());
                  //  hakmilik = hakmilikDAO.findById(hkp1);
                   // Hakmilik hk = strService.findIdHakmilik(hkp1);
                   // if(hk.getIdHakmilikInduk() != null)
                        
                   // nLot = hakmilik.getNoLot().replaceAll("^0*", "");
                    String hkp1 = hakmilikPermohonanListBaru.get(0).getHakmilik().getIdHakmilik();
                    LOG.debug("---Id Hakmilik---" + hkp1);
                    hakmilik = hakmilikDAO.findById(hkp1);
                    nLot = hakmilik.getNoLot().replaceAll("^0*", "");
                }
            }
        } 

        return new JSP("strata/pbbm/maklumatTanahPHPP_NS.jsp").addParameter("tab", "true");
    }
    
     public Resolution showFormHakmilikAsal() {
        kodNegeri = conf.getProperty("kodNegeri");
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");

        //to get data for maklumat Tanah at Pendaftaran hakmilik_strata.xml stage kemasukan
        if (kodNegeri.equals("05")) {
            LOG.debug("---seremban---");
            if (permohonan.getKodUrusan().getKod().equals("HT") && !permohonan.getPermohonanSebelum().getKodUrusan().getKod().equals("PHPP")) {
                LOG.debug("---current Permohonan Urusan HT---");
                LOG.debug("---Permohonan Sebelum Urusan Not PHPP---");
                Permohonan idpsb = new Permohonan();
                idpsb = permohonan.getPermohonanSebelum();
                LOG.debug("---Id permohonan---SBLM---" + idpsb);
                List<HakmilikPermohonan> hakmilikPermohonanList1 = new ArrayList<HakmilikPermohonan>();
                hakmilikPermohonanList1 = idpsb.getSenaraiHakmilik();
                LOG.debug("---HakmilikPermohonanList1 from---SBLM---" + hakmilikPermohonanList1);

                String hkp1 = hakmilikPermohonanList1.get(0).getHakmilik().getIdHakmilik();
                LOG.debug("---Id Hakmilik---SBLM---" + hkp1);
                hakmilik = hakmilikDAO.findById(hkp1);
                nLot = hakmilik.getNoLot().replaceAll("^0*", "");
                LOG.debug("---Kod Negeri---" + kodNegeri);
                LOG.debug("---No Lot---" + nLot);
            } 
            else if (permohonan.getKodUrusan().getKod().equals("HT") && permohonan.getPermohonanSebelum().getKodUrusan().getKod().equals("PHPP")) {
                LOG.debug("---current Permohonan Urusan HT---");
                LOG.debug("---Permohonan Sebelum Urusan PHPP---");
                if (idPermohonan != null) {
                    permohonan = permohonanDAO.findById(permohonan.getPermohonanSebelum().getIdPermohonan());
                    hakmilikPermohonanListBaru = new ArrayList<HakmilikPermohonan>();
                    hakmilikPermohonanListBaru = strService1.findIdHakmilikSortAsc(permohonan.getIdPermohonan());
                    LOG.debug("---hakmilikPermohonanList1---" + hakmilikPermohonanListBaru.size());
                    String hkp1 = hakmilikPermohonanListBaru.get(0).getHakmilik().getIdHakmilik();
                    LOG.debug("---Id Hakmilik---" + hkp1);
                    hakmilik = hakmilikDAO.findById(hkp1);
                    nLot = hakmilik.getNoLot().replaceAll("^0*", "");
                }
            } else {
                LOG.debug("---Not HT/HTSPV---");
                if (idPermohonan != null) {
                    permohonan = permohonanDAO.findById(idPermohonan);
                    hakmilikPermohonanListBaru = new ArrayList<HakmilikPermohonan>();
                    hakmilikPermohonanListBaru = strService1.findIdHakmilikSortAsc(idPermohonan);
                    LOG.debug("---hakmilikPermohonanList1---" + hakmilikPermohonanListBaru.size());
                    //String hkp1 = hakmilikPermohonanListBaru.get(0).getHakmilik().getIdHakmilik();
                    Permohonan idpsb = permohonan.getPermohonanSebelum();
                    HakmilikPermohonan hakmilikSblm = strService.findIdbyIDMohon(idpsb.getIdPermohonan());
                    LOG.debug("---hakmilikPermohonanList1---" + idpsb.getIdPermohonan());
                    String hkp1 = hakmilikSblm.getHakmilik().getIdHakmilik();
                    LOG.debug("---Id Hakmilik---" + hkp1);
                    LOG.debug("---Id Permohonan ---" + idpsb.getIdPermohonan());
                    hakmilik = hakmilikDAO.findById(hkp1);
                   // Hakmilik hk = strService.findIdHakmilik(hkp1);
                   // if(hk.getIdHakmilikInduk() != null)
                        
                    nLot = hakmilik.getNoLot().replaceAll("^0*", "");
                }
            }
        } 

        return new JSP("strata/pbbm/maklumatHakmilik_PHPC.jsp").addParameter("tab", "true");
    }
     
    public Resolution perihalTanahRuangUdara() {
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");


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
            KodUrusan ku = permohonan.getKodUrusan();
            if (ku.getKod().equals("P8") || ku.getKod().equals("P40A") || ku.getKod().equals("P14A")
                    || ku.getKod().equals("P22A") || ku.getKod().equals("P22B")) {
                AduanStrata aduanStrata = strService1.findAduanStrataIdMohon(idPermohonan);
                if (aduanStrata != null) {
                    if (aduanStrata.getHakmilik() != null) {
                        hakmilik = aduanStrata.getHakmilik();
                        LOG.debug("Hakmilik not null, IDHakmilik = " + hakmilik.getIdHakmilik());
                        listHakmilikPihak2 = hakmilikPihakKepentinganService.findPihakActiveByHakmilik(hakmilik);
                    }
                }
            }
        }
        return new JSP("strata/common/butiran_hakmilik_tanah.jsp").addParameter("tab", "true");
    }

    public Resolution showForm3() {
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = permohonanDAO.findById(idPermohonan);
        //hakmilikPermohonanList = strService1.findIdHakmilikSortAsc(idPermohonan);
        hakmilikList = strService1.findIdHakmilikByIdHakmilikInduk(permohonan.getSenaraiHakmilik().get(0).getHakmilik().getIdHakmilik());
        LOG.debug("-------hakmilikList------::" + hakmilikList.size());
        if (!hakmilikList.isEmpty()) {
            idStrataList = new ArrayList<Hakmilik>();
            for (int i = 0; i < hakmilikList.size(); i++) {
                Hakmilik hm = new Hakmilik();
                hm = hakmilikList.get(i);
                if (hm.getIdHakmilik().length() > 20 && hm.getKodStatusHakmilik().getKod().equals("D")) {
                    idStrataList.add(hm);
                    Comparator c4 = new Comparator<Hakmilik>() {
                        @Override
                        public int compare(Hakmilik h1, Hakmilik h2) {
                            String idHakmilik1 = h1.getIdHakmilik();
                            String idHakmilik2 = h2.getIdHakmilik();
                            String namahk = idHakmilik1.length() > 1 ? idHakmilik1.substring(idHakmilik1.length()-4,idHakmilik1.length()).replaceAll("^0*", "") : "0";
                            LOG.info("---namahkX---" + namahk);
                            String namahk2 = idHakmilik2.length() > 1 ? idHakmilik2.substring(idHakmilik2.length()-4,idHakmilik2.length()).replaceAll("^0*", "") : "0";
                            LOG.info("---namahk2---" + namahk2);
                            if(namahk.startsWith("L")){
                               namahk = namahk.substring(1, namahk.length());
                            }
                            if(namahk2.startsWith("L")){
                               namahk2 = namahk2.substring(1, namahk2.length());
                            }
                            return Integer.parseInt(namahk)
                                    - Integer.parseInt(namahk2);
                        }
                    };
                    Collections.sort(idStrataList, c4);
                   jumlahUnitSyor = hakmilikList.get(0).getJumlahUnitSyer().toString();
                }
            }
        }
        return new JSP("strata/pbbm/maklumatTanah_PPPP.jsp").addParameter("tab", "true");
    }

    public Resolution showHakmilikStrata() {
        idHakmilik = (String) getContext().getRequest().getParameter("idHakmilik");
        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = permohonanDAO.findById(idPermohonan);
        String idHm = permohonan.getSenaraiHakmilik().get(0).getHakmilik().getIdHakmilik();
        LOG.info("ID HAKMILIK = " + idHm);
        pihakKepentinganList = hakmilikPihakKepentinganService.findHakmilikPihakActiveAndWarisCucuCicitByHakmilik(hakmilikDAO.findById(idHm));
        pihakKepentinganSelainPMList = regService.searchPihakBerKepentinganSelainPemilikWarisCucuCicit(idHm);
        hakmilik = hakmilikDAO.findById(idHakmilik);
        noPelan1 = hakmilik.getNoPelan();
        LOG.debug("---show Hakmilik Strata--noPelan---:" + noPelan1);
        return new JSP("strata/pbbm/perincian_hakmilik_strata_PPPP.jsp").addParameter("tab", "true");
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

    public List<HakmilikPermohonan> getHakmilikPermohonanListBaru() {
        return hakmilikPermohonanListBaru;
    }

    public void setHakmilikPermohonanListBaru(List<HakmilikPermohonan> hakmilikPermohonanListBaru) {
        this.hakmilikPermohonanListBaru = hakmilikPermohonanListBaru;
    }

    public String getKodNegeri() {
        return kodNegeri;
    }

    public void setKodNegeri(String kodNegeri) {
        this.kodNegeri = kodNegeri;
    }

    public String getKodNegeri1() {
        return kodNegeri1;
    }

    public void setKodNegeri1(String kodNegeri1) {
        this.kodNegeri1 = kodNegeri1;
    }

    public String getnLot() {
        return nLot;
    }

    public void setnLot(String nLot) {
        this.nLot = nLot;
    }

    public List<Hakmilik> getHakmilikList() {
        return hakmilikList;
    }

    public void setHakmilikList(List<Hakmilik> hakmilikList) {
        this.hakmilikList = hakmilikList;
    }

    public List<Hakmilik> getIdStrataList() {
        return idStrataList;
    }

    public void setIdStrataList(List<Hakmilik> idStrataList) {
        this.idStrataList = idStrataList;
    }

    public String getJumlahUnitSyor() {
        return jumlahUnitSyor;
    }

    public void setJumlahUnitSyor(String jumlahUnitSyor) {
        this.jumlahUnitSyor = jumlahUnitSyor;
    }

    public List<HakmilikPihakBerkepentingan> getPihakKepentinganList() {
        return pihakKepentinganList;
    }

    public void setPihakKepentinganList(List<HakmilikPihakBerkepentingan> pihakKepentinganList) {
        this.pihakKepentinganList = pihakKepentinganList;
    }

    public List<HakmilikPihakBerkepentingan> getPihakKepentinganSelainPMList() {
        return pihakKepentinganSelainPMList;
    }

    public void setPihakKepentinganSelainPMList(List<HakmilikPihakBerkepentingan> pihakKepentinganSelainPMList) {
        this.pihakKepentinganSelainPMList = pihakKepentinganSelainPMList;
    }

    public String getNoPelan1() {
        return noPelan1;
    }

    public void setNoPelan1(String noPelan1) {
        this.noPelan1 = noPelan1;
    }
}
