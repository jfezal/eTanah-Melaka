/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.strata;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import java.io.FileNotFoundException;
import com.google.inject.Inject;
import etanah.dao.*;
import etanah.model.*;
import etanah.model.strata.*;
import etanah.service.*;
import java.io.IOException;
import java.text.ParseException;
import javax.xml.parsers.ParserConfigurationException;
import net.sourceforge.stripes.action.UrlBinding;
import etanah.view.BasicTabActionBean;
import etanah.view.etanahActionBeanContext;
import etanah.view.pembangunan.validator.KeputusanNotification;
import etanah.view.utility.JupemInIntegration;
import java.io.File;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import net.sourceforge.stripes.action.*;
import net.sourceforge.stripes.controller.LifecycleStage;
import org.apache.log4j.Logger;
import org.w3c.dom.*;
import org.xml.sax.SAXException;
import org.apache.commons.lang.StringUtils;
import oracle.bpel.services.workflow.task.model.Task;

/**
 *
 * @author muddmazani
 */
@UrlBinding("/strata/jadualPetakExcelPH")
public class JadualPetakExcelPH extends AbleActionBean {
    private static final Logger logger = Logger.getLogger(JadualPetakExcelPH.class);
    @Inject
    HakmilikPermohonanDAO hakmilikPermohonanDAO;
    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    KodKegunaanBangunanDAO kodKegunaanBangunanDAO;
    @Inject
    HakmilikDAO hakmilikDAO;
    @Inject
    KodKelasTanahDAO kodKelasTanahDAO;
    @Inject
    StrataPtService strService;
    private String idHakmilik;
    private String idPermohonan;
    private String bilPetak;
    private String kategoriBangunan;
    private String kelasTanah;
    private HakmilikPermohonan mohonHakmilik;
    private SkimStrata skimStrata;
    private CukaiPetak cukaiPetak;
    private Permohonan mohon;
    private List<HakmilikPermohonan> listMohonHakmilik;
    private List<Hakmilik> hm = new ArrayList<Hakmilik>();
    private List<Hakmilik> listHM = new ArrayList<Hakmilik>();
    private List<HakmilikPetakAksesori> listPetakAksr = new ArrayList<HakmilikPetakAksesori>();
    private List<PermohonanBangunan> ringkasanBngn = new ArrayList<PermohonanBangunan>();
    private List<PermohonanBangunan> listBngn = new ArrayList<PermohonanBangunan>();
    private List<BangunanTingkat> ringkasanTgkt = new ArrayList<BangunanTingkat>();
    private List<BangunanTingkat> listTgkt = new ArrayList<BangunanTingkat>();
    private List<BangunanPetak> listPetak = new ArrayList<BangunanPetak>();
    private List<BangunanPetak> ringkasanPetak = new ArrayList<BangunanPetak>();
    private List<BangunanPetakAksesori> ringkasanPetakAksesori = new ArrayList<BangunanPetakAksesori>();
    private List<BangunanPetakAksesori> listPetakAksesori = new ArrayList<BangunanPetakAksesori>();
    private List<BangunanPetakAksesori> listPelan = new ArrayList<BangunanPetakAksesori>();
    private List<String> sumLuasByPetak = new ArrayList<String>();

    @DefaultHandler
    public Resolution showForm1() {
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        mohonHakmilik = strService.findMohonHakmilikAsc(idPermohonan);

        String sifusExist = null;

        if (mohonHakmilik != null) {
            idHakmilik = mohonHakmilik.getHakmilik().getIdHakmilik();

            if (idHakmilik != null) {
                ringkasanBngn = strService.checkMhnBangunanExist(idPermohonan);
                logger.debug("ringkasanBngn :" + ringkasanBngn);

                List<Long> idbgn = new ArrayList<Long>();
                for (PermohonanBangunan bngn : ringkasanBngn) {
                    listBngn.add(bngn);
                    ringkasanTgkt = strService.findByIDBangunan(bngn.getIdBangunan());
                    for (BangunanTingkat tgkt : ringkasanTgkt) {
                        listTgkt.add(tgkt);
                        ringkasanPetak = strService.findPetakByIdTingKat(tgkt.getIdTingkat());
                        for (BangunanPetak petak : ringkasanPetak) {
                            listPetak.add(petak);
                            ringkasanPetakAksesori = strService.findPtkAksrByIDPetak(petak.getIdPetak());
                        }
                    }
                    idbgn.add(bngn.getIdBangunan());
                }
                if (!idbgn.isEmpty()) {
                    listPetakAksesori = strService.listPetakAksr(idbgn);
                    listPelan = strService.listPelanTamb(idbgn);
                }
                if (ringkasanBngn.size() <= 0) {
                    addSimpleError("Permohonan ini masih belum muat naik fail excel.");
                }
            }
        }

        return new JSP("strata/pbbm/jadualPetakExcel.jsp").addParameter("tab", "true");
    }

    public Resolution delete() {
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");

        if (idPermohonan != null) {
            ringkasanBngn = strService.checkMhnBangunanExist(idPermohonan);
            for (PermohonanBangunan bngn : ringkasanBngn) {
                listBngn.add(bngn);
                ringkasanTgkt = strService.findByIDBangunan(bngn.getIdBangunan());
                for (BangunanTingkat tgkt : ringkasanTgkt) {
                    listTgkt.add(tgkt);
                    ringkasanPetak = strService.findPetakByIdTingKat(tgkt.getIdTingkat());
                    for (BangunanPetak petak : ringkasanPetak) {
                        listPetak.add(petak);
//                        ringkasanPetakAksesori = strService.findPtkAksrByIDPetak(petak.getIdPetak());
                    }
                }
                ringkasanPetakAksesori = strService.findPtkAksrByIdTgkt(bngn.getIdBangunan());
                for (BangunanPetakAksesori aksr : ringkasanPetakAksesori) {
                    strService.deleteAksesori(aksr);
                }
            }

            int count = 0;
            for (BangunanPetak petak : listPetak) {
                strService.deletePetak(petak);
                count = 1;
            }
            for (BangunanTingkat tgkt : listTgkt) {
                strService.deleteTgkt(tgkt);
                count = 1;
            }
            for (PermohonanBangunan bngn : listBngn) {
                strService.deleteBngn(bngn);
                count = 1;
            }

            if (count == 1) {
                addSimpleMessage("Maklumat Berjaya DiHapuskan");
                listBngn = null;
                listPetak = null;
                listTgkt = null;
                listPetakAksesori = null;
            } else {
                addSimpleError("Maklumat Tidak Berjaya DiHapuskan");
            }
        }

        return new JSP("strata/pbbm/jadualPetakExcel.jsp").addParameter("tab", "true");
    }

    public Resolution showFormPBB() {
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        mohonHakmilik = strService.findMohonHakmilikAsc(idPermohonan);
        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);

        InfoAudit infoAudit1 = new InfoAudit();
        infoAudit1.setDiKemaskiniOleh(peng);
        infoAudit1.setTarikhKemaskini(new java.util.Date());

        String sifusExist = null;
        String i = null;
        if (mohonHakmilik != null) {
            idHakmilik = mohonHakmilik.getHakmilik().getIdHakmilik();
            logger.debug("idHakmilik :" + idHakmilik);
            
            if (idHakmilik != null) {
                    mohon = permohonanDAO.findById(idPermohonan);
                    List<PermohonanBangunan> ringkasanBngn1 = strService.checkMhnBangunanExist(idPermohonan);
                    List<PermohonanBangunan> ringkasanBngn2 = strService.checkMhnBangunanExist(idPermohonan);
                    if (ringkasanBngn1.size() <= 0 && ringkasanBngn2.size() <= 0) {
                        addSimpleError("Id Hakmilik ini telah memohon SiFUS secara manual. Sila muatnaik fail excel menggunakan id permohonan sekarang. ");
                        return new JSP("strata/pbbm/jadualPetakExcel.jsp").addParameter("tab", "true");
                    }
                    for (PermohonanBangunan bngn : ringkasanBngn1) {
                        if (bngn.getIdPermohonanSambung() == null) {
                            bngn.setIdPermohonanSambung(bngn.getPermohonan().getIdPermohonan());
                            bngn.setPermohonan(mohon);
                            bngn.setInfoAudit(infoAudit1);
                            strService.save(bngn);
                        }
                    }

                    boolean PBS = false;
                    boolean PBBD = false;
                    boolean PBBS = false;
                    ringkasanBngn = strService.checkMhnBangunanExist(idPermohonan);

                    List<Long> idbgn = new ArrayList<Long>();
                    for (PermohonanBangunan bngn : ringkasanBngn) {
                        listBngn.add(bngn);
                        idbgn.add(bngn.getIdBangunan());

                        if (!(mohon.getKodUrusan().getKod().equals("PBS") || mohon.getKodUrusan().getKod().equals("PSBS"))) {
                            for (PermohonanBangunan bngnCheck : ringkasanBngn) {
                                if (bngnCheck.getKodKategoriBangunan().getKod().equals("P")) {
                                    PBS = true;
                                } else if (bngnCheck.getKodKategoriBangunan().getKod().equals("L")) {
                                    PBBD = true;
                                } else if (bngnCheck.getKodKategoriBangunan().getKod().equals("B")) {
                                    PBBS = true;
                                }
                            }
                            if ((PBS == true && PBBD == true & PBBS == true)
                                    || (PBS == true && PBBD == false & PBBS == false)
                                    || (PBS == true && PBBD == true & PBBS == false)
                                    || (PBS == true && PBBD == false & PBBS == true)) {
                                if (!mohon.getKodUrusan().getKod().equals("PBS")) {
                                    addSimpleError("Anda telah salah membuat kemasukan urusan. Kod Urusan yang sebenar ialah PBS");
                                }
                            } else if ((PBS == false && PBBD == true & PBBS == true)
                                    || (PBS == false && PBBD == true & PBBS == false)) {
                                if (!mohon.getKodUrusan().getKod().equals("PBBD")) {
                                    addSimpleError("Anda telah salah membuat kemasukan urusan. Kod Urusan yang sebenar ialah PBBD");
                                }
                            } else if (PBS == false && PBBD == false & PBBS == true) {
                                if (!mohon.getKodUrusan().getKod().equals("PBBS")) {
                                    addSimpleError("Anda telah salah membuat kemasukan urusan. Kod Urusan yang sebenar ialah PBBS");
                                }
                            }
                        }
                        ringkasanTgkt = strService.findByIDBangunan(bngn.getIdBangunan());
                        for (BangunanTingkat tgkt : ringkasanTgkt) {
                            listTgkt.add(tgkt);
                            ringkasanPetak = strService.findPetakByIdTingKat(tgkt.getIdTingkat());
                            for (BangunanPetak petak : ringkasanPetak) {
                                listPetak.add(petak);
                                ringkasanPetakAksesori = strService.findPtkAksrByIDPetak(petak.getIdPetak());

                                for (BangunanPetakAksesori aksr : ringkasanPetakAksesori) {
                                    listPetakAksesori.add(aksr);
                                }
                            }
                        }
                    }
                    if (idbgn.size() > 0) {
                        listPelan = strService.listPelanTamb(idbgn);
                    }
                } else {
                    addSimpleError("Id Hakmilik ini masih belum muat naik fail excel. ");
                }
        }
        return new JSP("strata/pbbm/jadualPetakExcel.jsp").addParameter("tab", "true");
    }

    public Resolution showFormPSBS() {
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        mohonHakmilik = strService.findMohonHakmilikAsc(idPermohonan);
        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);

        InfoAudit infoAudit1 = new InfoAudit();
        infoAudit1.setDiKemaskiniOleh(peng);
        infoAudit1.setTarikhKemaskini(new java.util.Date());

        String sifusExist = null;
        String i = null;
        if (mohonHakmilik != null) {
            idHakmilik = mohonHakmilik.getHakmilik().getIdHakmilik();

            if (idHakmilik != null) {
                listMohonHakmilik = strService.findIdPBBSByIdHakmilik(idHakmilik);

                for (HakmilikPermohonan mh : listMohonHakmilik) {
                    List<Projek> sifus = strService.findSifus(idHakmilik, null, "Y");

                    if (sifus.size() > 0) {
                        sifusExist = "ada";
                        i = "x";
                    } else {
                        sifusExist = null;
                    }

                    if (sifus.size() > 1) {
                        addSimpleError("Sila pastikan hanya terdapat 1 sijil formula unit syer yang aktif.");
                        return new JSP("strata/pbbm/jadualPetakExcel.jsp").addParameter("tab", "true");
                    }
                }
            }
        }

        if (i != null) {
            if (sifusExist.equals("ada")) {
                String excel = idPermohonan;

                if (excel != null) {
                    mohon = permohonanDAO.findById(idPermohonan);
                    List<PermohonanBangunan> ringkasanBngn1 = strService.checkMhnBangunanExist(excel);
                    if (ringkasanBngn1.size() > 0) {
                        for (PermohonanBangunan bngn : ringkasanBngn1) {
                            if (bngn.getIdPermohonanSambung() == null) {
                                bngn.setIdPermohonanSambung(bngn.getPermohonan().getIdPermohonan());
                                bngn.setPermohonan(mohon);
                                bngn.setInfoAudit(infoAudit1);
                                strService.save(bngn);
                            }
                        }

                        boolean PBS = false;
                        boolean PBBD = false;
                        boolean PBBS = false;
                        ringkasanBngn = strService.checkMhnBangunanExist(idPermohonan);
                        for (PermohonanBangunan bngn : ringkasanBngn) {
                            listBngn.add(bngn);
                            ringkasanTgkt = strService.findByIDBangunan(bngn.getIdBangunan());
                            for (BangunanTingkat tgkt : ringkasanTgkt) {
                                listTgkt.add(tgkt);
                                ringkasanPetak = strService.findPetakByIdTingKat(tgkt.getIdTingkat());
                                for (BangunanPetak petak : ringkasanPetak) {
                                    listPetak.add(petak);
                                    ringkasanPetakAksesori = strService.findPtkAksrByIDPetak(petak.getIdPetak());

                                    for (BangunanPetakAksesori aksr : ringkasanPetakAksesori) {
                                        listPetakAksesori.add(aksr);
                                    }
                                }
                            }
                        }
                    } else {
                        addSimpleError("Id Hakmilik ini masih belum muat naik fail excel.");
                    }
                } else {
                    addSimpleError("Id Hakmilik ini masih belum muat naik fail excel. ");
                }
            } else {
                addSimpleError("Id Hakmilik ini masih belum memohon SiFUS. ");
            }
        } else {
            addSimpleError("Id Hakmilik ini masih belum memohon SiFUS. ");
        }

        return new JSP("strata/pbbm/jadualPetakExcel.jsp").addParameter("tab", "true");
    }

    public Resolution skim() {
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);

        List<PermohonanBangunan> mb = strService.checkMhnBangunanExist(idPermohonan);

        if (mb.isEmpty()) {
            addSimpleError("Id Hakmilik ini masih belum muatnaik jadual petak. ");
        } else {
            Integer petak = 0;
            for (PermohonanBangunan list : mb) {
                petak = petak + list.getBilanganPetak();
            }

            bilPetak = String.valueOf(petak);
        }

        List<Long> idbgn = new ArrayList<Long>();
        for (PermohonanBangunan bngn : mb) {
            listBngn.add(bngn);
            ringkasanTgkt = strService.findByIDBangunan(bngn.getIdBangunan());
            for (BangunanTingkat tgkt : ringkasanTgkt) {
                listTgkt.add(tgkt);
                ringkasanPetak = strService.findPetakByIdTingKat(tgkt.getIdTingkat());
                for (BangunanPetak petak : ringkasanPetak) {
                    listPetak.add(petak);
                    ringkasanPetakAksesori = strService.findPtkAksrByIDPetak(petak.getIdPetak());
                }
            }
            idbgn.add(bngn.getIdBangunan());
            List<String> aksr = strService.sumLuasPetakAksr(bngn.getIdBangunan());

            for (int a = 0; a < aksr.size(); a++) {
                sumLuasByPetak.add(aksr.get(a));
            }
        }
        if (!idbgn.isEmpty()) {
            listPetakAksesori = strService.listPetakAksr(idbgn);
            listPelan = strService.listPelanTamb(idbgn);
        }

        mohon = permohonanDAO.findById(idPermohonan);
        List<HakmilikPermohonan> mh = mohon.getSenaraiHakmilik();

        for (HakmilikPermohonan list : mh) {
            if (list.getHakmilik().getIdHakmilikInduk() == null) {
                skimStrata = strService.findSkimStrata(list.getHakmilik().getIdHakmilik());
                if (skimStrata != null) {
                    if (skimStrata.getKategoriBangunan() != null) {
                        kategoriBangunan = skimStrata.getKategoriBangunan().getKod();
                    }
                    if (skimStrata.getKelasTanah() != null) {
                        kelasTanah = skimStrata.getKelasTanah().getKod();
                    }

                    cukaiPetak = strService.findCukaiPetak(kategoriBangunan, kelasTanah);
                }
            }
        }

        if (mh.size() > 1) {
            getContext().getRequest().setAttribute("jana", Boolean.TRUE);
        } else {
            getContext().getRequest().setAttribute("jana", Boolean.FALSE);
        }

        return new JSP("strata/pbbm/jadualCukaiPetak.jsp").addParameter("tab", "true");
    }

    public Resolution skim2() {
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);

        listMohonHakmilik = strService.findIdHakmilikSortNew(idPermohonan);

        if (listMohonHakmilik.isEmpty()) {
            addSimpleError("Sila klik tab 'Pendaftaran Hakmilik Strata' terlebih dahulu.  ");
        }

        for (HakmilikPermohonan list : listMohonHakmilik) {
            if (list.getHakmilik().getIdHakmilikInduk() != null) {
                skimStrata = strService.findSkimStrata(list.getHakmilik().getIdHakmilikInduk());
                if (skimStrata != null) {
                    if (skimStrata.getKategoriBangunan() != null) {
                        kategoriBangunan = skimStrata.getKategoriBangunan().getKod();
                    }
                    if (skimStrata.getKelasTanah() != null) {
                        kelasTanah = skimStrata.getKelasTanah().getKod();
                    }

                    cukaiPetak = strService.findCukaiPetak(kategoriBangunan, kelasTanah);
                }  
                hm = strService.findTotalLuasByHMstatusHmT(list.getHakmilik().getIdHakmilikInduk());
                listHM = strService.findHakmilikStrataTByHakmilikInduk(list.getHakmilik().getIdHakmilikInduk());
                listPetakAksr = strService.findHmPetakAksrstatusHmT(list.getHakmilik().getIdHakmilikInduk());
                break;
            }
        }

        if (listMohonHakmilik.size() > 1) {
            getContext().getRequest().setAttribute("jana", Boolean.TRUE);
        } else {
            getContext().getRequest().setAttribute("jana", Boolean.FALSE);
        }

        return new JSP("strata/pbbm/jadualCukaiPetak2.jsp").addParameter("tab", "true");
    }

    public Resolution janaCukai() {
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);

        mohon = permohonanDAO.findById(idPermohonan);
        List<HakmilikPermohonan> mh = mohon.getSenaraiHakmilik();
        listMohonHakmilik = mohon.getSenaraiHakmilik();

        int bilHm = 0;
        for (HakmilikPermohonan hp : mh) {
            if (mh.size() > 1 && hp.getHakmilik().getIdHakmilikInduk() != null) {
                String petak = (String) getContext().getRequest().getParameter("ptk" + bilHm);
                String totalCukai = (String) getContext().getRequest().getParameter("totalCukai" + bilHm);
//                petak = StringUtils.leftPad(String.valueOf(petak), 5, '0');

                Hakmilik hm = strService.findInfoByIdHakmilik(petak);
                if (hm != null) {
                    hm.setCukai(BigDecimal.valueOf(Double.valueOf(totalCukai)));
                    hm.setCukaiSebenar(BigDecimal.valueOf(Double.valueOf(totalCukai)));
                    strService.simpanHakmilik(hm);
                }
                bilHm++;
            }
        }
        addSimpleMessage("Cukai petak berjaya dijana.");

        SkimStrata ss = strService.findSkimStrata(mh.get(0).getHakmilik().getIdHakmilikInduk() != null ? mh.get(0).getHakmilik().getIdHakmilikInduk() : mh.get(0).getHakmilik().getIdHakmilik());

        if (ss != null) {
            ss.setFlagCukai("Y");
            ss.setFlagJana("Y");
            ss.setPegJanaCukai(peng);
            ss.setTarikhJanaCukai(new Date());
            strService.saveUpdateSkimStrata(ss);
        }

        getContext().getRequest().setAttribute("jana", Boolean.FALSE);
        skim2();
        return new JSP("strata/pbbm/jadualCukaiPetak2.jsp").addParameter("tab", "true");
    }

    public Resolution simpanSkim() {
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);

        InfoAudit infoAudit1 = new InfoAudit();
        infoAudit1.setDimasukOleh(peng);
        infoAudit1.setTarikhMasuk(new java.util.Date());

        List<PermohonanBangunan> mb = strService.checkMhnBangunanExist(idPermohonan);

        if (mb.isEmpty()) {
            addSimpleError("Id Hakmilik ini masih belum muatnaik jadual petak. ");
        } else {
            Integer petak = 0;
            for (PermohonanBangunan list : mb) {
                petak = petak + list.getBilanganPetak();
            }

            bilPetak = String.valueOf(petak);
        }

        mohon = permohonanDAO.findById(idPermohonan);
        List<HakmilikPermohonan> mh = mohon.getSenaraiHakmilik();

        for (HakmilikPermohonan list : mh) {
            if (list.getHakmilik().getIdHakmilikInduk() == null) {
                SkimStrata ss = strService.findSkimStrata(list.getHakmilik().getIdHakmilik());

                if (ss != null) {
                    ss.setKategoriBangunan(kodKegunaanBangunanDAO.findById(kategoriBangunan));
                    ss.setKelasTanah(kodKelasTanahDAO.findById(kelasTanah));
//                ss.setFlagCukai(skimStrata.getFlagCukai());
//                ss.setFlagJana(skimStrata.getFlagJana());
                    ss.setKosRendah(skimStrata.getKosRendah());

                    strService.saveUpdateSkimStrata(ss);
                    addSimpleMessage("Maklumat berjaya disimpan.");
                } else {

                    SkimStrata ssNew = new SkimStrata();
                    ssNew.setHakmilikInduk(list.getHakmilik());
                    ssNew.setKategoriBangunan(kodKegunaanBangunanDAO.findById(kategoriBangunan));
                    ssNew.setKelasTanah(kodKelasTanahDAO.findById(kelasTanah));
                    ssNew.setFlagCukai("T");
                    ssNew.setFlagJana("T");
                    ssNew.setKosRendah(skimStrata.getKosRendah());

                    strService.saveSkimStrata(ssNew);
                    addSimpleMessage("Maklumat berjaya disimpan.");
                }
            }
        }

        skim();
        return new JSP("strata/pbbm/jadualCukaiPetak.jsp").addParameter("tab", "true");
    }

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {

        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
    }

    public String getIdHakmilik() {
        return idHakmilik;
    }

    public void setIdHakmilik(String idHakmilik) {
        this.idHakmilik = idHakmilik;
    }

    public HakmilikPermohonan getMohonHakmilik() {
        return mohonHakmilik;
    }

    public void setMohonHakmilik(HakmilikPermohonan mohonHakmilik) {
        this.mohonHakmilik = mohonHakmilik;
    }

    public List<HakmilikPermohonan> getListMohonHakmilik() {
        return listMohonHakmilik;
    }

    public void setListMohonHakmilik(List<HakmilikPermohonan> listMohonHakmilik) {
        this.listMohonHakmilik = listMohonHakmilik;
    }

    public List<PermohonanBangunan> getRingkasanBngn() {
        return ringkasanBngn;
    }

    public void setRingkasanBngn(List<PermohonanBangunan> ringkasanBngn) {
        this.ringkasanBngn = ringkasanBngn;
    }

    public List<BangunanTingkat> getRingkasanTgkt() {
        return ringkasanTgkt;
    }

    public void setRingkasanTgkt(List<BangunanTingkat> ringkasanTgkt) {
        this.ringkasanTgkt = ringkasanTgkt;
    }

    public List<BangunanPetak> getRingkasanPetak() {
        return ringkasanPetak;
    }

    public void setRingkasanPetak(List<BangunanPetak> ringkasanPetak) {
        this.ringkasanPetak = ringkasanPetak;
    }

    public List<BangunanPetakAksesori> getRingkasanPetakAksesori() {
        return ringkasanPetakAksesori;
    }

    public void setRingkasanPetakAksesori(List<BangunanPetakAksesori> ringkasanPetakAksesori) {
        this.ringkasanPetakAksesori = ringkasanPetakAksesori;
    }

    public List<BangunanTingkat> getListTgkt() {
        return listTgkt;
    }

    public void setListTgkt(List<BangunanTingkat> listTgkt) {
        this.listTgkt = listTgkt;
    }

    public List<BangunanPetak> getListPetak() {
        return listPetak;
    }

    public void setListPetak(List<BangunanPetak> listPetak) {
        this.listPetak = listPetak;
    }

    public List<BangunanPetakAksesori> getListPetakAksesori() {
        return listPetakAksesori;
    }

    public void setListPetakAksesori(List<BangunanPetakAksesori> listPetakAksesori) {
        this.listPetakAksesori = listPetakAksesori;
    }

    public Permohonan getMohon() {
        return mohon;
    }

    public void setMohon(Permohonan mohon) {
        this.mohon = mohon;
    }

    public List<PermohonanBangunan> getListBngn() {
        return listBngn;
    }

    public void setListBngn(List<PermohonanBangunan> listBngn) {
        this.listBngn = listBngn;
    }

    public String getIdPermohonan() {
        return idPermohonan;
    }

    public void setIdPermohonan(String idPermohonan) {
        this.idPermohonan = idPermohonan;
    }

    public List<BangunanPetakAksesori> getListPelan() {
        return listPelan;
    }

    public void setListPelan(List<BangunanPetakAksesori> listPelan) {
        this.listPelan = listPelan;
    }

    public String getBilPetak() {
        return bilPetak;
    }

    public void setBilPetak(String bilPetak) {
        this.bilPetak = bilPetak;
    }

    public SkimStrata getSkimStrata() {
        return skimStrata;
    }

    public void setSkimStrata(SkimStrata skimStrata) {
        this.skimStrata = skimStrata;
    }

    public List<String> getSumLuasByPetak() {
        return sumLuasByPetak;
    }

    public void setSumLuasByPetak(List<String> sumLuasByPetak) {
        this.sumLuasByPetak = sumLuasByPetak;
    }

    public String getKategoriBangunan() {
        return kategoriBangunan;
    }

    public void setKategoriBangunan(String kategoriBangunan) {
        this.kategoriBangunan = kategoriBangunan;
    }

    public String getKelasTanah() {
        return kelasTanah;
    }

    public void setKelasTanah(String kelasTanah) {
        this.kelasTanah = kelasTanah;
    }

    public CukaiPetak getCukaiPetak() {
        return cukaiPetak;
    }

    public void setCukaiPetak(CukaiPetak cukaiPetak) {
        this.cukaiPetak = cukaiPetak;
    }

    public List<Hakmilik> getHm() {
        return hm;
    }

    public void setHm(List<Hakmilik> hm) {
        this.hm = hm;
    }

    public List<Hakmilik> getListHM() {
        return listHM;
    }

    public void setListHM(List<Hakmilik> listHM) {
        this.listHM = listHM;
    }

    public List<HakmilikPetakAksesori> getListPetakAksr() {
        return listPetakAksr;
    }

    public void setListPetakAksr(List<HakmilikPetakAksesori> listPetakAksr) {
        this.listPetakAksr = listPetakAksr;
    }

}
