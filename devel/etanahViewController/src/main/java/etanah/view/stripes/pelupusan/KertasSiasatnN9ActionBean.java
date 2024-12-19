/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.stripes.pelupusan;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.dao.KodDokumenDAO;
import etanah.dao.PermohonanDAO;
import etanah.dao.PermohonanKertasKandunganDAO;
import etanah.model.HakmilikPermohonan;
import etanah.model.InfoAudit;
import etanah.model.KodDokumen;
//import etanah.model.LaporanTanahSempadan;
import etanah.service.PelupusanPtService;
import etanah.model.Pemohon;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.model.PermohonanKertas;
import etanah.model.PermohonanKertasKandungan;
import java.util.List;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import etanah.service.PelupusanService;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.controller.LifecycleStage;
import etanah.view.etanahActionBeanContext;
import java.text.ParseException;
import java.util.ArrayList;
//import etanah.view.stripes.pelupusan.disClass.LotSempadan;
import net.sourceforge.stripes.action.RedirectResolution;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
//import etanah.dao.KodLotDAO;
//import etanah.model.KodLot;

/**
 *
 * @author sitinorajar
 */
@UrlBinding("/pelupusan/kertas_siasatan")
public class KertasSiasatnN9ActionBean extends AbleActionBean {

    @Inject
    PelupusanService pelupusanService;
    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    KodDokumenDAO kodDokumenDAO;
    @Inject
    PermohonanKertasKandunganDAO permohonanKertasKandunganDAO;
    @Inject
    PelupusanPtService pelPtService;
    private static final Logger LOG = Logger.getLogger(KertasSiasatnN9ActionBean.class);
    private static final boolean IS_LOG_DEBUG = LOG.isDebugEnabled();
    private String idPermohonan;
    private Permohonan permohonan;
    private Pemohon pemohon;
    private HakmilikPermohonan hakmilikPermohonan;
    private PermohonanKertasKandungan permohonanKertasKandungan;
    private PermohonanKertas permohonanKertasTemp;
    private PermohonanKertas permohonanKertasTemp2;
    private InfoAudit ia;
    private Pengguna peng;
    private boolean edit, edit1, edit2, edit23, edit3 = false;
    private List<PermohonanKertasKandungan> mohonKertasKandListOne;
    private List<PermohonanKertasKandungan> mohonKertasKandListTwo;
    private List<PermohonanKertasKandungan> mohonKertasKandListThree;
    private List<PermohonanKertasKandungan> mohonKertasKandListFour;
    private List<PermohonanKertasKandungan> mohonKertasKandListOne1;
    private List<PermohonanKertasKandungan> mohonKertasKandListTwo2;
    private List<PermohonanKertasKandungan> mohonKertasKandListThree3;
    private List<PermohonanKertasKandungan> mohonKertasKandListFour4;
    private List<PermohonanKertasKandungan> mohonKertasKandListFive5;
    private List listLaporTanahSpdnU;
    private List listLaporTanahSpdnS;
    private List listLaporTanahSpdnB;
    private List listLaporTanahSpdnT;
    private int uSize;
    private int sSize;
    private int bSize;
    private int tSize;
    private boolean statusEdit, statusEdit1, statusEdit2, statusEdit23, statusEdit3 = false;

    @DefaultHandler
    public Resolution showForm() {
        long idKertas = 32901;
        permohonanKertasKandungan = pelupusanService.findByBilNIdKertas(0, idKertas);
        return new JSP("pelupusan/kertas_siasaatn_N9.jsp").addParameter("tab", "true");
    }
    
    public Resolution showForm2() {
        return new JSP("pelupusan/kertas_makluman.jsp").addParameter("tab", "true");
    }

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() throws Exception {
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = permohonanDAO.findById(idPermohonan);
        peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        if (permohonan != null) {
            hakmilikPermohonan = pelupusanService.findHakmilikPermohonan(idPermohonan);
            pemohon = pelupusanService.findPemohon(idPermohonan);

            //saving Mohon Kertas

            permohonanKertasTemp = pelupusanService.findPermohonanKertasByIdPermohonanNKodDokumen(idPermohonan, "LPK");
            permohonanKertasTemp2 = pelupusanService.findPermohonanKertasByIdPermohonanNKodDokumen(idPermohonan, "KSM");
            PermohonanKertasKandungan permohonanKertasKandungan_ref = new PermohonanKertasKandungan();
            if (permohonanKertasTemp == null) {
                ia = new InfoAudit();
                permohonanKertasTemp = new PermohonanKertas();
                permohonanKertasTemp.setCawangan(permohonan.getCawangan());
                permohonanKertasTemp.setPermohonan(permohonan);
                KodDokumen kodDok = new KodDokumen();
                kodDok = kodDokumenDAO.findById("LPK");
                permohonanKertasTemp.setKodDokumen(kodDok);
                permohonanKertasTemp.setTajuk("Kertas Siasatan");
                ia.setDimasukOleh(peng);
                ia.setTarikhMasuk(new java.util.Date());
                permohonanKertasTemp.setInfoAudit(ia);
                //permohonanKertasTemp = new PermohonanKertas();
                //permohonanKertasTemp = pelupusanService.findPermohonanKertasByIdPermohonanNKodDokumen(idPermohonan, "KST");
            } else {
                ia = new InfoAudit();
                ia = permohonanKertasTemp.getInfoAudit();
                ia.setTarikhKemaskini(new java.util.Date());
                ia.setDiKemaskiniOleh(peng);
                permohonanKertasTemp.setInfoAudit(ia);
            }
            permohonanKertasTemp = pelupusanService.simpanPermohonanKertas(permohonanKertasTemp);
            
            if (permohonanKertasTemp2 == null) {
                ia = new InfoAudit();
                permohonanKertasTemp2 = new PermohonanKertas();
                permohonanKertasTemp2.setCawangan(permohonan.getCawangan());
                permohonanKertasTemp2.setPermohonan(permohonan);
                KodDokumen kodDok = new KodDokumen();
                kodDok = kodDokumenDAO.findById("KSM");
                permohonanKertasTemp2.setKodDokumen(kodDok);
                permohonanKertasTemp2.setTajuk("Kertas Siasatan Makluman");
                ia.setDimasukOleh(peng);
                ia.setTarikhMasuk(new java.util.Date());
                permohonanKertasTemp2.setInfoAudit(ia);
                //permohonanKertasTemp = new PermohonanKertas();
                //permohonanKertasTemp = pelupusanService.findPermohonanKertasByIdPermohonanNKodDokumen(idPermohonan, "KST");
            } else {
                ia = new InfoAudit();
                ia = permohonanKertasTemp2.getInfoAudit();
                ia.setTarikhKemaskini(new java.util.Date());
                ia.setDiKemaskiniOleh(peng);
                permohonanKertasTemp2.setInfoAudit(ia);
            }
            permohonanKertasTemp2 = pelupusanService.simpanPermohonanKertas(permohonanKertasTemp2);
            
            int bill1 = 1;
            mohonKertasKandListOne = new ArrayList<PermohonanKertasKandungan>();
            mohonKertasKandListOne = pelupusanService.findByIdKertasNbilforParent(permohonanKertasTemp.getIdKertas(), bill1);
            edit = (mohonKertasKandListOne.size() > 0) ? false : true;
            int bill2 = 2;
            mohonKertasKandListTwo = new ArrayList<PermohonanKertasKandungan>();
            mohonKertasKandListTwo = pelupusanService.findByIdKertasNbilforParent(permohonanKertasTemp.getIdKertas(), bill2);
            edit1 = (mohonKertasKandListTwo.size() > 0) ? false : true;
            int bill3 = 3;
            mohonKertasKandListThree = new ArrayList<PermohonanKertasKandungan>();
            mohonKertasKandListThree = pelupusanService.findByIdKertasNbilforParent(permohonanKertasTemp.getIdKertas(), bill3);
            //edit23 = (mohonKertasKandListThree.size() > 0) ? false : true;
            edit2 = (mohonKertasKandListThree.size() > 0) ? false : true;
            int bill4 = 4;
            mohonKertasKandListFour = new ArrayList<PermohonanKertasKandungan>();
            mohonKertasKandListFour = pelupusanService.findByIdKertasNbilforParent(permohonanKertasTemp.getIdKertas(), bill4);
            edit3 = (mohonKertasKandListFour.size() > 0) ? false : true;

            //untuk kertas siasatan makluman
            
            mohonKertasKandListOne1 = new ArrayList<PermohonanKertasKandungan>();
            mohonKertasKandListOne1 = pelupusanService.findByIdKertasNbilforParent(permohonanKertasTemp2.getIdKertas(), bill1);
            edit = (mohonKertasKandListOne1.size() > 0) ? false : true;
            
            mohonKertasKandListTwo2 = new ArrayList<PermohonanKertasKandungan>();
            mohonKertasKandListTwo2 = pelupusanService.findByIdKertasNbilforParent(permohonanKertasTemp2.getIdKertas(), bill2);
            edit1 = (mohonKertasKandListTwo2.size() > 0) ? false : true;
           
            mohonKertasKandListThree3 = new ArrayList<PermohonanKertasKandungan>();
            mohonKertasKandListThree3 = pelupusanService.findByIdKertasNbilforParent(permohonanKertasTemp2.getIdKertas(), bill3);
            //edit23 = (mohonKertasKandListThree.size() > 0) ? false : true;
            edit2 = (mohonKertasKandListThree3.size() > 0) ? false : true;
            
            mohonKertasKandListFour4 = new ArrayList<PermohonanKertasKandungan>();
            mohonKertasKandListFour4 = pelupusanService.findByIdKertasNbilforParent(permohonanKertasTemp2.getIdKertas(), bill4);
            edit3 = (mohonKertasKandListFour4.size() > 0) ? false : true;
            int bill5 = 5;
            mohonKertasKandListFive5 = new ArrayList<PermohonanKertasKandungan>();
            mohonKertasKandListFive5 = pelupusanService.findByIdKertasNbilforParent(permohonanKertasTemp2.getIdKertas(), bill5);
            edit3 = (mohonKertasKandListFive5.size() > 0) ? false : true;

            if (permohonan.getKodUrusan().getKod().equalsIgnoreCase("PTPBP")) {
                if (statusEdit) {
                    edit = true;
                }
                if (statusEdit1) {
                    edit1 = true;
                }
                if (statusEdit2) {
                    edit2 = true;
                }
                if (statusEdit3) {
                    edit3 = true;
                }


                LOG.info("::: edit (rehydrate):::" + edit);
                LOG.info("::: edit1 (rehydrate):::" + edit1);
                LOG.info("::: edit2 (rehydrate):::" + edit2);
                LOG.info("::: edit3 (rehydrate):::" + edit3);
            }

        }

    }

    public Resolution tambahRow() throws Exception {
        LOG.info(":::  tambahRow");
        Pengguna peng1 = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        permohonanKertasTemp = new PermohonanKertas();
        permohonanKertasKandungan = new PermohonanKertasKandungan();
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = new Permohonan();
        permohonan = permohonanDAO.findById(idPermohonan);
        permohonanKertasTemp = pelupusanService.findPermohonanKertasByIdPermohonanNKodDokumen(idPermohonan, "LPK");
        LOG.info("permohonanKertasTemp.size():" + permohonanKertasTemp.getIdKertas());
        String index = getContext().getRequest().getParameter("index");
        if (index.equals("1")) {
            if (mohonKertasKandListOne.size() > 0) {
                for (int i = 1; i <= mohonKertasKandListOne.size() + 1; i++) {
                    String kandungan = getContext().getRequest().getParameter("1kandungan" + String.valueOf(i));
                    LOG.info("kandungan.size():" + kandungan);
                    if (kandungan != null && !kandungan.equals("")) {
                        String idMohonKertas = getContext().getRequest().getParameter("1idMohonKertas" + String.valueOf(i));
                        if (idMohonKertas != null && !idMohonKertas.equals("")) {
                            permohonanKertasKandungan = new PermohonanKertasKandungan();
                            permohonanKertasKandungan = permohonanKertasKandunganDAO.findById(new Long(idMohonKertas));
                            if (permohonanKertasKandungan != null) {
                                ia = new InfoAudit();
                                ia = permohonanKertasKandungan.getInfoAudit();
                                ia.setDiKemaskiniOleh(peng1);
                                ia.setTarikhKemaskini(new java.util.Date());
                                permohonanKertasKandungan.setInfoAudit(ia);
                                permohonanKertasKandungan.setSubtajuk(String.valueOf(i));
                                permohonanKertasKandungan.setKandungan(kandungan);
                            }
                        } else {
                            permohonanKertasKandungan = new PermohonanKertasKandungan();
                            permohonanKertasKandungan.setKertas(permohonanKertasTemp);
                            permohonanKertasKandungan.setCawangan(permohonan.getCawangan());
                            permohonanKertasKandungan.setBil(1);
                            permohonanKertasKandungan.setSubtajuk(String.valueOf(i));
                            permohonanKertasKandungan.setKandungan(kandungan);
                            ia = new InfoAudit();
                            ia.setDimasukOleh(peng1);
                            ia.setTarikhMasuk(new java.util.Date());
                            permohonanKertasKandungan.setInfoAudit(ia);

                        }
                        pelupusanService.saveOrUpdatePermohonanKertasKandungan(permohonanKertasKandungan);

                    } else {
//                        addSimpleError("Sila Masukkan Maklumat berkaitan.");
                    }
                }
            } else {
                String kandungan_ref = getContext().getRequest().getParameter("1kandungan1");
                if (kandungan_ref != null && !kandungan_ref.equals("")) {
                    permohonanKertasKandungan = new PermohonanKertasKandungan();
                    permohonanKertasKandungan.setKertas(permohonanKertasTemp);
                    permohonanKertasKandungan.setCawangan(permohonan.getCawangan());
                    permohonanKertasKandungan.setBil(1);
                    permohonanKertasKandungan.setSubtajuk("1");
                    permohonanKertasKandungan.setKandungan(kandungan_ref);
                    ia = new InfoAudit();
                    ia.setDimasukOleh(peng1);
                    ia.setTarikhMasuk(new java.util.Date());
                    permohonanKertasKandungan.setInfoAudit(ia);
                    pelupusanService.saveOrUpdatePermohonanKertasKandungan(permohonanKertasKandungan);
                } else {
                    addSimpleError("Sila Masukkan Maklumat Tujuan.");
                }
            }
            int bill1 = 1;
            mohonKertasKandListOne = new ArrayList<PermohonanKertasKandungan>();
            mohonKertasKandListOne = pelupusanService.findByIdKertasNbilforParent(permohonanKertasTemp.getIdKertas(), bill1);
            edit = true;
            //addSimpleMessage("Maklumat Berjaya Disimpan");
        } else if (index.equals("2")) {
            if (mohonKertasKandListTwo.size() > 0) {
                for (int i = 1; i <= mohonKertasKandListTwo.size() + 1; i++) {
                    String kandungan = getContext().getRequest().getParameter("2kandungan" + String.valueOf(i));
                    if (kandungan != null && !kandungan.equals("")) {
                        String idMohonKertas = getContext().getRequest().getParameter("2idMohonKertas" + String.valueOf(i));
                        if (idMohonKertas != null && !idMohonKertas.equals("")) {
                            permohonanKertasKandungan = new PermohonanKertasKandungan();
                            permohonanKertasKandungan = permohonanKertasKandunganDAO.findById(new Long(idMohonKertas));
                            if (permohonanKertasKandungan != null) {
                                ia = new InfoAudit();
                                ia = permohonanKertasKandungan.getInfoAudit();
                                ia.setDiKemaskiniOleh(peng1);
                                ia.setTarikhKemaskini(new java.util.Date());
                                permohonanKertasKandungan.setInfoAudit(ia);
                                permohonanKertasKandungan.setSubtajuk(String.valueOf(i));
                                permohonanKertasKandungan.setKandungan(kandungan);

                            }
                        } else {
                            permohonanKertasKandungan = new PermohonanKertasKandungan();
                            permohonanKertasKandungan.setKertas(permohonanKertasTemp);
                            permohonanKertasKandungan.setCawangan(permohonan.getCawangan());
                            permohonanKertasKandungan.setBil(2);
                            permohonanKertasKandungan.setSubtajuk(String.valueOf(i));
                            permohonanKertasKandungan.setKandungan(kandungan);
                            ia = new InfoAudit();
                            ia.setDimasukOleh(peng1);
                            ia.setTarikhMasuk(new java.util.Date());
                            permohonanKertasKandungan.setInfoAudit(ia);

                        }
                        pelupusanService.saveOrUpdatePermohonanKertasKandungan(permohonanKertasKandungan);

                    }
                }
            } else {
                String kandungan_ref = getContext().getRequest().getParameter("2kandungan1");
                if (kandungan_ref != null && !kandungan_ref.equals("")) {
                    permohonanKertasKandungan = new PermohonanKertasKandungan();
                    permohonanKertasKandungan.setKertas(permohonanKertasTemp);
                    permohonanKertasKandungan.setCawangan(permohonan.getCawangan());
                    permohonanKertasKandungan.setBil(2);
                    permohonanKertasKandungan.setSubtajuk("1");
                    permohonanKertasKandungan.setKandungan(kandungan_ref);
                    ia = new InfoAudit();
                    ia.setDimasukOleh(peng1);
                    ia.setTarikhMasuk(new java.util.Date());
                    permohonanKertasKandungan.setInfoAudit(ia);
                    pelupusanService.saveOrUpdatePermohonanKertasKandungan(permohonanKertasKandungan);
                } else {
                    addSimpleError("Sila Masukkan Maklumat Kedudukan.");
                }
            }
            int bill2 = 2;
            mohonKertasKandListTwo = new ArrayList<PermohonanKertasKandungan>();
            mohonKertasKandListTwo = pelupusanService.findByIdKertasNbilforParent(permohonanKertasTemp.getIdKertas(), bill2);
            edit1 = true;
            //addSimpleMessage("Maklumat Berjaya Disimpan");
        } else if (index.equals("3")) {
            if (mohonKertasKandListThree.size() > 0) {
                for (int i = 1; i <= mohonKertasKandListThree.size() + 1; i++) {
                    String kandungan = getContext().getRequest().getParameter("3kandungan" + String.valueOf(i));
                    if (kandungan != null && !kandungan.equals("")) {
                        String idMohonKertas = getContext().getRequest().getParameter("3idMohonKertas" + String.valueOf(i));
                        if (idMohonKertas != null && !idMohonKertas.equals("")) {
                            permohonanKertasKandungan = new PermohonanKertasKandungan();
                            permohonanKertasKandungan = permohonanKertasKandunganDAO.findById(new Long(idMohonKertas));
                            if (permohonanKertasKandungan != null) {
                                ia = new InfoAudit();
                                ia = permohonanKertasKandungan.getInfoAudit();
                                ia.setDiKemaskiniOleh(peng1);
                                ia.setTarikhKemaskini(new java.util.Date());
                                permohonanKertasKandungan.setInfoAudit(ia);
                                permohonanKertasKandungan.setSubtajuk(String.valueOf(i));
                                permohonanKertasKandungan.setKandungan(kandungan);

                            }
                        } else {
                            permohonanKertasKandungan = new PermohonanKertasKandungan();
                            permohonanKertasKandungan.setKertas(permohonanKertasTemp);
                            permohonanKertasKandungan.setCawangan(permohonan.getCawangan());
                            permohonanKertasKandungan.setBil(3);
                            permohonanKertasKandungan.setSubtajuk(String.valueOf(i));
                            permohonanKertasKandungan.setKandungan(kandungan);
                            ia = new InfoAudit();
                            ia.setDimasukOleh(peng1);
                            ia.setTarikhMasuk(new java.util.Date());
                            permohonanKertasKandungan.setInfoAudit(ia);
                        }
                        pelupusanService.saveOrUpdatePermohonanKertasKandungan(permohonanKertasKandungan);

                    }
                }
            } else {
                String kandungan_ref = getContext().getRequest().getParameter("3kandungan1");
                //String kandungan_ref_in = getContext().getRequest().getParameter("3kandungan@");
                if (kandungan_ref != null && !kandungan_ref.equals("")) {
                    permohonanKertasKandungan = new PermohonanKertasKandungan();
                    permohonanKertasKandungan.setKertas(permohonanKertasTemp);
                    permohonanKertasKandungan.setCawangan(permohonan.getCawangan());
                    permohonanKertasKandungan.setBil(3);
                    permohonanKertasKandungan.setSubtajuk("1");
                    permohonanKertasKandungan.setKandungan(kandungan_ref);
                    ia = new InfoAudit();
                    ia.setDimasukOleh(peng1);
                    ia.setTarikhMasuk(new java.util.Date());
                    permohonanKertasKandungan.setInfoAudit(ia);
                    pelupusanService.saveOrUpdatePermohonanKertasKandungan(permohonanKertasKandungan);
                } else {
                    addSimpleError("Sila Masukkan Maklumat Hasil Siasatan.");
                }

            }
            int bill3 = 3;
            mohonKertasKandListThree = new ArrayList<PermohonanKertasKandungan>();
            mohonKertasKandListThree = pelupusanService.findByIdKertasNbilforParent(permohonanKertasTemp.getIdKertas(), bill3);
            edit2 = true;
            //addSimpleMessage("Maklumat Berjaya Disimpan");
        } else if (index.equals("4")) {
            if (mohonKertasKandListFour.size() > 0) {
                for (int i = 1; i <= mohonKertasKandListFour.size() + 1; i++) {
                    String kandungan = getContext().getRequest().getParameter("4kandungan" + String.valueOf(i));
                    if (kandungan != null && !kandungan.equals("")) {
                        String idMohonKertas = getContext().getRequest().getParameter("4idMohonKertas" + String.valueOf(i));
                        if (idMohonKertas != null && !idMohonKertas.equals("")) {
                            permohonanKertasKandungan = new PermohonanKertasKandungan();
                            permohonanKertasKandungan = permohonanKertasKandunganDAO.findById(new Long(idMohonKertas));
                            if (permohonanKertasKandungan != null) {
                                ia = new InfoAudit();
                                ia = permohonanKertasKandungan.getInfoAudit();
                                ia.setDiKemaskiniOleh(peng1);
                                ia.setTarikhKemaskini(new java.util.Date());
                                permohonanKertasKandungan.setInfoAudit(ia);
                                permohonanKertasKandungan.setSubtajuk(String.valueOf(i));
                                permohonanKertasKandungan.setKandungan(kandungan);
                            }
                        } else {
                            permohonanKertasKandungan = new PermohonanKertasKandungan();
                            permohonanKertasKandungan.setKertas(permohonanKertasTemp);
                            permohonanKertasKandungan.setCawangan(permohonan.getCawangan());
                            permohonanKertasKandungan.setBil(4);
                            permohonanKertasKandungan.setSubtajuk(String.valueOf(i));
                            permohonanKertasKandungan.setKandungan(kandungan);
                            ia = new InfoAudit();
                            ia.setDimasukOleh(peng1);
                            ia.setTarikhMasuk(new java.util.Date());
                            permohonanKertasKandungan.setInfoAudit(ia);
                        }
                        pelupusanService.saveOrUpdatePermohonanKertasKandungan(permohonanKertasKandungan);

                    }
                }
            } else {
                String kandungan_ref = getContext().getRequest().getParameter("4kandungan1");
                if (kandungan_ref != null && !kandungan_ref.equals("")) {
                    permohonanKertasKandungan = new PermohonanKertasKandungan();
                    permohonanKertasKandungan.setKertas(permohonanKertasTemp);
                    permohonanKertasKandungan.setCawangan(permohonan.getCawangan());
                    permohonanKertasKandungan.setBil(4);
                    permohonanKertasKandungan.setSubtajuk("1");
                    permohonanKertasKandungan.setKandungan(kandungan_ref);
                    ia = new InfoAudit();
                    ia.setDimasukOleh(peng1);
                    ia.setTarikhMasuk(new java.util.Date());
                    permohonanKertasKandungan.setInfoAudit(ia);
                    pelupusanService.saveOrUpdatePermohonanKertasKandungan(permohonanKertasKandungan);
                } else {
                    addSimpleError("Sila masukkan Maklumat Syor.");
                }
            }
            int bill4 = 4;
            mohonKertasKandListFour = new ArrayList<PermohonanKertasKandungan>();
            mohonKertasKandListFour = pelupusanService.findByIdKertasNbilforParent(permohonanKertasTemp.getIdKertas(), bill4);
            edit3 = true;
            //addSimpleMessage("Maklumat Berjaya Disimpan");
        }
        return new JSP("pelupusan/kertas_siasaatn_N9.jsp").addParameter("tab", "true");
    }

    
    public Resolution tambahRowMakluman() throws Exception {
        LOG.info(":::  tambahRowMakluman");
        Pengguna peng1 = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        permohonanKertasTemp2 = new PermohonanKertas();
        permohonanKertasKandungan = new PermohonanKertasKandungan();
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = new Permohonan();
        permohonan = permohonanDAO.findById(idPermohonan);
        permohonanKertasTemp2 = pelupusanService.findPermohonanKertasByIdPermohonanNKodDokumen(idPermohonan, "KSM");
        LOG.info("permohonanKertasTemp.size():" + permohonanKertasTemp2.getIdKertas());
        String index = getContext().getRequest().getParameter("index");
        if (index.equals("1")) {
            if (mohonKertasKandListOne1.size() > 0) {
                for (int i = 1; i <= mohonKertasKandListOne1.size() + 1; i++) {
                    String kandungan = getContext().getRequest().getParameter("1kandungan" + String.valueOf(i));
                    LOG.info("kandungan.size():" + kandungan);
                    if (kandungan != null && !kandungan.equals("")) {
                        String idMohonKertas = getContext().getRequest().getParameter("1idMohonKertas" + String.valueOf(i));
                        if (idMohonKertas != null && !idMohonKertas.equals("")) {
                            permohonanKertasKandungan = new PermohonanKertasKandungan();
                            permohonanKertasKandungan = permohonanKertasKandunganDAO.findById(new Long(idMohonKertas));
                            if (permohonanKertasKandungan != null) {
                                ia = new InfoAudit();
                                ia = permohonanKertasKandungan.getInfoAudit();
                                ia.setDiKemaskiniOleh(peng1);
                                ia.setTarikhKemaskini(new java.util.Date());
                                permohonanKertasKandungan.setInfoAudit(ia);
                                permohonanKertasKandungan.setSubtajuk(String.valueOf(i));
                                permohonanKertasKandungan.setKandungan(kandungan);
                            }
                        } else {
                            permohonanKertasKandungan = new PermohonanKertasKandungan();
                            permohonanKertasKandungan.setKertas(permohonanKertasTemp2);
                            permohonanKertasKandungan.setCawangan(permohonan.getCawangan());
                            permohonanKertasKandungan.setBil(1);
                            permohonanKertasKandungan.setSubtajuk(String.valueOf(i));
                            permohonanKertasKandungan.setKandungan(kandungan);
                            ia = new InfoAudit();
                            ia.setDimasukOleh(peng1);
                            ia.setTarikhMasuk(new java.util.Date());
                            permohonanKertasKandungan.setInfoAudit(ia);

                        }
                        pelupusanService.saveOrUpdatePermohonanKertasKandungan(permohonanKertasKandungan);

                    } else {
//                        addSimpleError("Sila Masukkan Maklumat berkaitan.");
                    }
                }
            } else {
                String kandungan_ref = getContext().getRequest().getParameter("1kandungan1");
                if (kandungan_ref != null && !kandungan_ref.equals("")) {
                    permohonanKertasKandungan = new PermohonanKertasKandungan();
                    permohonanKertasKandungan.setKertas(permohonanKertasTemp2);
                    permohonanKertasKandungan.setCawangan(permohonan.getCawangan());
                    permohonanKertasKandungan.setBil(1);
                    permohonanKertasKandungan.setSubtajuk("1");
                    permohonanKertasKandungan.setKandungan(kandungan_ref);
                    ia = new InfoAudit();
                    ia.setDimasukOleh(peng1);
                    ia.setTarikhMasuk(new java.util.Date());
                    permohonanKertasKandungan.setInfoAudit(ia);
                    pelupusanService.saveOrUpdatePermohonanKertasKandungan(permohonanKertasKandungan);
                } else {
                    addSimpleError("Sila Masukkan Maklumat Tujuan.");
                }
            }
            int bill1 = 1;
            mohonKertasKandListOne1 = new ArrayList<PermohonanKertasKandungan>();
            mohonKertasKandListOne1 = pelupusanService.findByIdKertasNbilforParent(permohonanKertasTemp2.getIdKertas(), bill1);
            edit = true;
            //addSimpleMessage("Maklumat Berjaya Disimpan");
        } else if (index.equals("2")) {
            if (mohonKertasKandListTwo2.size() > 0) {
                for (int i = 1; i <= mohonKertasKandListTwo2.size() + 1; i++) {
                    String kandungan = getContext().getRequest().getParameter("2kandungan" + String.valueOf(i));
                    if (kandungan != null && !kandungan.equals("")) {
                        String idMohonKertas = getContext().getRequest().getParameter("2idMohonKertas" + String.valueOf(i));
                        if (idMohonKertas != null && !idMohonKertas.equals("")) {
                            permohonanKertasKandungan = new PermohonanKertasKandungan();
                            permohonanKertasKandungan = permohonanKertasKandunganDAO.findById(new Long(idMohonKertas));
                            if (permohonanKertasKandungan != null) {
                                ia = new InfoAudit();
                                ia = permohonanKertasKandungan.getInfoAudit();
                                ia.setDiKemaskiniOleh(peng1);
                                ia.setTarikhKemaskini(new java.util.Date());
                                permohonanKertasKandungan.setInfoAudit(ia);
                                permohonanKertasKandungan.setSubtajuk(String.valueOf(i));
                                permohonanKertasKandungan.setKandungan(kandungan);

                            }
                        } else {
                            permohonanKertasKandungan = new PermohonanKertasKandungan();
                            permohonanKertasKandungan.setKertas(permohonanKertasTemp2);
                            permohonanKertasKandungan.setCawangan(permohonan.getCawangan());
                            permohonanKertasKandungan.setBil(2);
                            permohonanKertasKandungan.setSubtajuk(String.valueOf(i));
                            permohonanKertasKandungan.setKandungan(kandungan);
                            ia = new InfoAudit();
                            ia.setDimasukOleh(peng1);
                            ia.setTarikhMasuk(new java.util.Date());
                            permohonanKertasKandungan.setInfoAudit(ia);

                        }
                        pelupusanService.saveOrUpdatePermohonanKertasKandungan(permohonanKertasKandungan);

                    }
                }
            } else {
                String kandungan_ref = getContext().getRequest().getParameter("2kandungan1");
                if (kandungan_ref != null && !kandungan_ref.equals("")) {
                    permohonanKertasKandungan = new PermohonanKertasKandungan();
                    permohonanKertasKandungan.setKertas(permohonanKertasTemp2);
                    permohonanKertasKandungan.setCawangan(permohonan.getCawangan());
                    permohonanKertasKandungan.setBil(2);
                    permohonanKertasKandungan.setSubtajuk("1");
                    permohonanKertasKandungan.setKandungan(kandungan_ref);
                    ia = new InfoAudit();
                    ia.setDimasukOleh(peng1);
                    ia.setTarikhMasuk(new java.util.Date());
                    permohonanKertasKandungan.setInfoAudit(ia);
                    pelupusanService.saveOrUpdatePermohonanKertasKandungan(permohonanKertasKandungan);
                } else {
                    addSimpleError("Sila Masukkan Maklumat Kedudukan.");
                }
            }
            int bill2 = 2;
            mohonKertasKandListTwo2 = new ArrayList<PermohonanKertasKandungan>();
            mohonKertasKandListTwo2 = pelupusanService.findByIdKertasNbilforParent(permohonanKertasTemp2.getIdKertas(), bill2);
            edit1 = true;
            //addSimpleMessage("Maklumat Berjaya Disimpan");
        } else if (index.equals("3")) {
            if (mohonKertasKandListThree3.size() > 0) {
                for (int i = 1; i <= mohonKertasKandListThree3.size() + 1; i++) {
                    String kandungan = getContext().getRequest().getParameter("3kandungan" + String.valueOf(i));
                    if (kandungan != null && !kandungan.equals("")) {
                        String idMohonKertas = getContext().getRequest().getParameter("3idMohonKertas" + String.valueOf(i));
                        if (idMohonKertas != null && !idMohonKertas.equals("")) {
                            permohonanKertasKandungan = new PermohonanKertasKandungan();
                            permohonanKertasKandungan = permohonanKertasKandunganDAO.findById(new Long(idMohonKertas));
                            if (permohonanKertasKandungan != null) {
                                ia = new InfoAudit();
                                ia = permohonanKertasKandungan.getInfoAudit();
                                ia.setDiKemaskiniOleh(peng1);
                                ia.setTarikhKemaskini(new java.util.Date());
                                permohonanKertasKandungan.setInfoAudit(ia);
                                permohonanKertasKandungan.setSubtajuk(String.valueOf(i));
                                permohonanKertasKandungan.setKandungan(kandungan);

                            }
                        } else {
                            permohonanKertasKandungan = new PermohonanKertasKandungan();
                            permohonanKertasKandungan.setKertas(permohonanKertasTemp2);
                            permohonanKertasKandungan.setCawangan(permohonan.getCawangan());
                            permohonanKertasKandungan.setBil(3);
                            permohonanKertasKandungan.setSubtajuk(String.valueOf(i));
                            permohonanKertasKandungan.setKandungan(kandungan);
                            ia = new InfoAudit();
                            ia.setDimasukOleh(peng1);
                            ia.setTarikhMasuk(new java.util.Date());
                            permohonanKertasKandungan.setInfoAudit(ia);
                        }
                        pelupusanService.saveOrUpdatePermohonanKertasKandungan(permohonanKertasKandungan);

                    }
                }
            } else {
                String kandungan_ref = getContext().getRequest().getParameter("3kandungan1");
                //String kandungan_ref_in = getContext().getRequest().getParameter("3kandungan@");
                if (kandungan_ref != null && !kandungan_ref.equals("")) {
                    permohonanKertasKandungan = new PermohonanKertasKandungan();
                    permohonanKertasKandungan.setKertas(permohonanKertasTemp2);
                    permohonanKertasKandungan.setCawangan(permohonan.getCawangan());
                    permohonanKertasKandungan.setBil(3);
                    permohonanKertasKandungan.setSubtajuk("1");
                    permohonanKertasKandungan.setKandungan(kandungan_ref);
                    ia = new InfoAudit();
                    ia.setDimasukOleh(peng1);
                    ia.setTarikhMasuk(new java.util.Date());
                    permohonanKertasKandungan.setInfoAudit(ia);
                    pelupusanService.saveOrUpdatePermohonanKertasKandungan(permohonanKertasKandungan);
                } else {
                    addSimpleError("Sila Masukkan Maklumat Hasil Siasatan.");
                }

            }
            int bill3 = 3;
            mohonKertasKandListThree3 = new ArrayList<PermohonanKertasKandungan>();
            mohonKertasKandListThree3 = pelupusanService.findByIdKertasNbilforParent(permohonanKertasTemp2.getIdKertas(), bill3);
            edit2 = true;
            //addSimpleMessage("Maklumat Berjaya Disimpan");
        } else if (index.equals("4")) {
            if (mohonKertasKandListFour4.size() > 0) {
                for (int i = 1; i <= mohonKertasKandListFour4.size() + 1; i++) {
                    String kandungan = getContext().getRequest().getParameter("4kandungan" + String.valueOf(i));
                    if (kandungan != null && !kandungan.equals("")) {
                        String idMohonKertas = getContext().getRequest().getParameter("4idMohonKertas" + String.valueOf(i));
                        if (idMohonKertas != null && !idMohonKertas.equals("")) {
                            permohonanKertasKandungan = new PermohonanKertasKandungan();
                            permohonanKertasKandungan = permohonanKertasKandunganDAO.findById(new Long(idMohonKertas));
                            if (permohonanKertasKandungan != null) {
                                ia = new InfoAudit();
                                ia = permohonanKertasKandungan.getInfoAudit();
                                ia.setDiKemaskiniOleh(peng1);
                                ia.setTarikhKemaskini(new java.util.Date());
                                permohonanKertasKandungan.setInfoAudit(ia);
                                permohonanKertasKandungan.setSubtajuk(String.valueOf(i));
                                permohonanKertasKandungan.setKandungan(kandungan);
                            }
                        } else {
                            permohonanKertasKandungan = new PermohonanKertasKandungan();
                            permohonanKertasKandungan.setKertas(permohonanKertasTemp2);
                            permohonanKertasKandungan.setCawangan(permohonan.getCawangan());
                            permohonanKertasKandungan.setBil(4);
                            permohonanKertasKandungan.setSubtajuk(String.valueOf(i));
                            permohonanKertasKandungan.setKandungan(kandungan);
                            ia = new InfoAudit();
                            ia.setDimasukOleh(peng1);
                            ia.setTarikhMasuk(new java.util.Date());
                            permohonanKertasKandungan.setInfoAudit(ia);
                        }
                        pelupusanService.saveOrUpdatePermohonanKertasKandungan(permohonanKertasKandungan);

                    }
                }
            } else {
                String kandungan_ref = getContext().getRequest().getParameter("4kandungan1");
                if (kandungan_ref != null && !kandungan_ref.equals("")) {
                    permohonanKertasKandungan = new PermohonanKertasKandungan();
                    permohonanKertasKandungan.setKertas(permohonanKertasTemp2);
                    permohonanKertasKandungan.setCawangan(permohonan.getCawangan());
                    permohonanKertasKandungan.setBil(4);
                    permohonanKertasKandungan.setSubtajuk("1");
                    permohonanKertasKandungan.setKandungan(kandungan_ref);
                    ia = new InfoAudit();
                    ia.setDimasukOleh(peng1);
                    ia.setTarikhMasuk(new java.util.Date());
                    permohonanKertasKandungan.setInfoAudit(ia);
                    pelupusanService.saveOrUpdatePermohonanKertasKandungan(permohonanKertasKandungan);
                } else {
                    addSimpleError("Sila masukkan Maklumat Syor.");
                }
            }
            int bill4 = 4;
            mohonKertasKandListFour4 = new ArrayList<PermohonanKertasKandungan>();
            mohonKertasKandListFour4 = pelupusanService.findByIdKertasNbilforParent(permohonanKertasTemp2.getIdKertas(), bill4);
            edit3 = true;
            //addSimpleMessage("Maklumat Berjaya Disimpan");
        }
        return new JSP("pelupusan/kertas_makluman.jsp").addParameter("tab", "true");
    }
    
    public Resolution tambahRow1() throws Exception {
        Pengguna peng1 = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        permohonanKertasTemp = new PermohonanKertas();
        permohonanKertasKandungan = new PermohonanKertasKandungan();
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = new Permohonan();
        permohonan = permohonanDAO.findById(idPermohonan);
        permohonanKertasTemp = pelupusanService.findPermohonanKertasByIdPermohonanNKodDokumen(idPermohonan, "LPK");
        LOG.info("permohonanKertasTemp.size():" + permohonanKertasTemp.getIdKertas());
        String index = getContext().getRequest().getParameter("index");
        if (index.equals("1")) {
            if (mohonKertasKandListOne.size() > 0) {
                for (int i = 1; i <= mohonKertasKandListOne.size() + 1; i++) {
                    String kandungan = getContext().getRequest().getParameter("1kandungan" + String.valueOf(i));
                    LOG.info("kandungan.size():" + kandungan);
                    if (kandungan != null && !kandungan.equals("")) {
                        String idMohonKertas = getContext().getRequest().getParameter("1idMohonKertas" + String.valueOf(i));
                        if (idMohonKertas != null && !idMohonKertas.equals("")) {
                            permohonanKertasKandungan = new PermohonanKertasKandungan();
                            permohonanKertasKandungan = permohonanKertasKandunganDAO.findById(new Long(idMohonKertas));
                            if (permohonanKertasKandungan != null) {
                                ia = new InfoAudit();
                                ia = permohonanKertasKandungan.getInfoAudit();
                                ia.setDiKemaskiniOleh(peng1);
                                ia.setTarikhKemaskini(new java.util.Date());
                                permohonanKertasKandungan.setInfoAudit(ia);
                                permohonanKertasKandungan.setSubtajuk(String.valueOf(i));
                                permohonanKertasKandungan.setKandungan(kandungan);
                            }
                        } else {
                            permohonanKertasKandungan = new PermohonanKertasKandungan();
                            permohonanKertasKandungan.setKertas(permohonanKertasTemp);
                            permohonanKertasKandungan.setCawangan(permohonan.getCawangan());
                            permohonanKertasKandungan.setBil(1);
                            permohonanKertasKandungan.setSubtajuk(String.valueOf(i));
                            permohonanKertasKandungan.setKandungan(kandungan);
                            ia = new InfoAudit();
                            ia.setDimasukOleh(peng1);
                            ia.setTarikhMasuk(new java.util.Date());
                            permohonanKertasKandungan.setInfoAudit(ia);

                        }
                        pelupusanService.saveOrUpdatePermohonanKertasKandungan(permohonanKertasKandungan);
                        addSimpleMessage("Maklumat Berjaya Disimpan");
                    } else {
                        addSimpleError("Sila Masukkan Maklumat Tujuan.");
                    }
                }
            } else {
                String kandungan_ref = getContext().getRequest().getParameter("1kandungan1");
                if (kandungan_ref != null && !kandungan_ref.equals("")) {
                    permohonanKertasKandungan = new PermohonanKertasKandungan();
                    permohonanKertasKandungan.setKertas(permohonanKertasTemp);
                    permohonanKertasKandungan.setCawangan(permohonan.getCawangan());
                    permohonanKertasKandungan.setBil(1);
                    permohonanKertasKandungan.setSubtajuk("1");
                    permohonanKertasKandungan.setKandungan(kandungan_ref);
                    ia = new InfoAudit();
                    ia.setDimasukOleh(peng1);
                    ia.setTarikhMasuk(new java.util.Date());
                    permohonanKertasKandungan.setInfoAudit(ia);
                    pelupusanService.saveOrUpdatePermohonanKertasKandungan(permohonanKertasKandungan);
                    addSimpleMessage("Maklumat Berjaya Disimpan");
                } else {
                    System.out.println(" :" + kandungan_ref + ":");
                    addSimpleError("Sila Masukkan Maklumat Tujuan.");
                }
            }
            int bill1 = 1;
            mohonKertasKandListOne = new ArrayList<PermohonanKertasKandungan>();
            mohonKertasKandListOne = pelupusanService.findByIdKertasNbilforParent(permohonanKertasTemp.getIdKertas(), bill1);
            //edit = true;

        } else if (index.equals("2")) {
            if (mohonKertasKandListTwo.size() > 0) {
                for (int i = 1; i <= mohonKertasKandListTwo.size() + 1; i++) {
                    String kandungan = getContext().getRequest().getParameter("2kandungan" + String.valueOf(i));
                    if (kandungan != null && !kandungan.equals("")) {
                        String idMohonKertas = getContext().getRequest().getParameter("2idMohonKertas" + String.valueOf(i));
                        if (idMohonKertas != null && !idMohonKertas.equals("")) {
                            permohonanKertasKandungan = new PermohonanKertasKandungan();
                            permohonanKertasKandungan = permohonanKertasKandunganDAO.findById(new Long(idMohonKertas));
                            if (permohonanKertasKandungan != null) {
                                ia = new InfoAudit();
                                ia = permohonanKertasKandungan.getInfoAudit();
                                ia.setDiKemaskiniOleh(peng1);
                                ia.setTarikhKemaskini(new java.util.Date());
                                permohonanKertasKandungan.setInfoAudit(ia);
                                permohonanKertasKandungan.setSubtajuk(String.valueOf(i));
                                permohonanKertasKandungan.setKandungan(kandungan);

                            }
                        } else {
                            permohonanKertasKandungan = new PermohonanKertasKandungan();
                            permohonanKertasKandungan.setKertas(permohonanKertasTemp);
                            permohonanKertasKandungan.setCawangan(permohonan.getCawangan());
                            permohonanKertasKandungan.setBil(2);
                            permohonanKertasKandungan.setSubtajuk(String.valueOf(i));
                            permohonanKertasKandungan.setKandungan(kandungan);
                            ia = new InfoAudit();
                            ia.setDimasukOleh(peng1);
                            ia.setTarikhMasuk(new java.util.Date());
                            permohonanKertasKandungan.setInfoAudit(ia);

                        }
                        pelupusanService.saveOrUpdatePermohonanKertasKandungan(permohonanKertasKandungan);
                        addSimpleMessage("Maklumat Berjaya Disimpan");
                    } else {
                        addSimpleError("Sila Masukkan Maklumat Kedudukan.");
                    }
                }
            } else {
                String kandungan_ref = getContext().getRequest().getParameter("2kandungan1");
                if (kandungan_ref != null && !kandungan_ref.equals("")) {
                    permohonanKertasKandungan = new PermohonanKertasKandungan();
                    permohonanKertasKandungan.setKertas(permohonanKertasTemp);
                    permohonanKertasKandungan.setCawangan(permohonan.getCawangan());
                    permohonanKertasKandungan.setBil(2);
                    permohonanKertasKandungan.setSubtajuk("1");
                    permohonanKertasKandungan.setKandungan(kandungan_ref);
                    ia = new InfoAudit();
                    ia.setDimasukOleh(peng1);
                    ia.setTarikhMasuk(new java.util.Date());
                    permohonanKertasKandungan.setInfoAudit(ia);
                    pelupusanService.saveOrUpdatePermohonanKertasKandungan(permohonanKertasKandungan);
                    addSimpleMessage("Maklumat Berjaya Disimpan");
                } else {
                    addSimpleError("Sila Masukkan Maklumat Kedudukan.");
                }
            }
            int bill2 = 2;
            mohonKertasKandListTwo = new ArrayList<PermohonanKertasKandungan>();
            mohonKertasKandListTwo = pelupusanService.findByIdKertasNbilforParent(permohonanKertasTemp.getIdKertas(), bill2);
            //edit1 = true;

        } else if (index.equals("3")) {
            if (mohonKertasKandListThree.size() > 0) {
                for (int i = 1; i <= mohonKertasKandListThree.size() + 1; i++) {
                    String kandungan = getContext().getRequest().getParameter("3kandungan" + String.valueOf(i));
                    if (kandungan != null && !kandungan.equals("")) {
                        String idMohonKertas = getContext().getRequest().getParameter("3idMohonKertas" + String.valueOf(i));
                        if (idMohonKertas != null && !idMohonKertas.equals("")) {
                            permohonanKertasKandungan = new PermohonanKertasKandungan();
                            permohonanKertasKandungan = permohonanKertasKandunganDAO.findById(new Long(idMohonKertas));
                            if (permohonanKertasKandungan != null) {
                                ia = new InfoAudit();
                                ia = permohonanKertasKandungan.getInfoAudit();
                                ia.setDiKemaskiniOleh(peng1);
                                ia.setTarikhKemaskini(new java.util.Date());
                                permohonanKertasKandungan.setInfoAudit(ia);
                                permohonanKertasKandungan.setSubtajuk(String.valueOf(i));
                                permohonanKertasKandungan.setKandungan(kandungan);

                            }
                        } else {
                            permohonanKertasKandungan = new PermohonanKertasKandungan();
                            permohonanKertasKandungan.setKertas(permohonanKertasTemp);
                            permohonanKertasKandungan.setCawangan(permohonan.getCawangan());
                            permohonanKertasKandungan.setBil(3);
                            permohonanKertasKandungan.setSubtajuk(String.valueOf(i));
                            permohonanKertasKandungan.setKandungan(kandungan);
                            ia = new InfoAudit();
                            ia.setDimasukOleh(peng1);
                            ia.setTarikhMasuk(new java.util.Date());
                            permohonanKertasKandungan.setInfoAudit(ia);
                        }
                        pelupusanService.saveOrUpdatePermohonanKertasKandungan(permohonanKertasKandungan);
                        addSimpleMessage("Maklumat Berjaya Disimpan");
                    } else {
                        addSimpleError("Sila Masukkan Maklumat Hasil Siasatan.");
                    }
                }
            } else {
                String kandungan_ref = getContext().getRequest().getParameter("3kandungan1");
                //String kandungan_ref_in = getContext().getRequest().getParameter("3kandungan@");
                if (kandungan_ref != null && !kandungan_ref.equals("")) {
                    permohonanKertasKandungan = new PermohonanKertasKandungan();
                    permohonanKertasKandungan.setKertas(permohonanKertasTemp);
                    permohonanKertasKandungan.setCawangan(permohonan.getCawangan());
                    permohonanKertasKandungan.setBil(3);
                    permohonanKertasKandungan.setSubtajuk("1");
                    permohonanKertasKandungan.setKandungan(kandungan_ref);
                    ia = new InfoAudit();
                    ia.setDimasukOleh(peng1);
                    ia.setTarikhMasuk(new java.util.Date());
                    permohonanKertasKandungan.setInfoAudit(ia);
                    pelupusanService.saveOrUpdatePermohonanKertasKandungan(permohonanKertasKandungan);
                    addSimpleMessage("Maklumat Berjaya Disimpan");
                } else {
                    addSimpleError("Sila Masukkan Maklumat Hasil Siasatan.");
                }

            }
            int bill3 = 3;
            mohonKertasKandListThree = new ArrayList<PermohonanKertasKandungan>();
            mohonKertasKandListThree = pelupusanService.findByIdKertasNbilforParent(permohonanKertasTemp.getIdKertas(), bill3);
            //edit2 = true;

        } else if (index.equals("4")) {
            if (mohonKertasKandListFour.size() > 0) {
                for (int i = 1; i <= mohonKertasKandListFour.size() + 1; i++) {
                    String kandungan = getContext().getRequest().getParameter("4kandungan" + String.valueOf(i));
                    if (kandungan != null && !kandungan.equals("")) {
                        String idMohonKertas = getContext().getRequest().getParameter("4idMohonKertas" + String.valueOf(i));
                        if (idMohonKertas != null && !idMohonKertas.equals("")) {
                            permohonanKertasKandungan = new PermohonanKertasKandungan();
                            permohonanKertasKandungan = permohonanKertasKandunganDAO.findById(new Long(idMohonKertas));
                            if (permohonanKertasKandungan != null) {
                                ia = new InfoAudit();
                                ia = permohonanKertasKandungan.getInfoAudit();
                                ia.setDiKemaskiniOleh(peng1);
                                ia.setTarikhKemaskini(new java.util.Date());
                                permohonanKertasKandungan.setInfoAudit(ia);
                                permohonanKertasKandungan.setSubtajuk(String.valueOf(i));
                                permohonanKertasKandungan.setKandungan(kandungan);
                            }
                        } else {
                            permohonanKertasKandungan = new PermohonanKertasKandungan();
                            permohonanKertasKandungan.setKertas(permohonanKertasTemp);
                            permohonanKertasKandungan.setCawangan(permohonan.getCawangan());
                            permohonanKertasKandungan.setBil(4);
                            permohonanKertasKandungan.setSubtajuk(String.valueOf(i));
                            permohonanKertasKandungan.setKandungan(kandungan);
                            ia = new InfoAudit();
                            ia.setDimasukOleh(peng1);
                            ia.setTarikhMasuk(new java.util.Date());
                            permohonanKertasKandungan.setInfoAudit(ia);
                        }
                        pelupusanService.saveOrUpdatePermohonanKertasKandungan(permohonanKertasKandungan);
                        addSimpleMessage("Maklumat Berjaya Disimpan");
                    } else {
                        addSimpleError("Sila Masukkan Maklumat Syor.");
                    }
                }
            } else {
                String kandungan_ref = getContext().getRequest().getParameter("4kandungan1");
                if (kandungan_ref != null && !kandungan_ref.equals("")) {
                    permohonanKertasKandungan = new PermohonanKertasKandungan();
                    permohonanKertasKandungan.setKertas(permohonanKertasTemp);
                    permohonanKertasKandungan.setCawangan(permohonan.getCawangan());
                    permohonanKertasKandungan.setBil(4);
                    permohonanKertasKandungan.setSubtajuk("1");
                    permohonanKertasKandungan.setKandungan(kandungan_ref);
                    ia = new InfoAudit();
                    ia.setDimasukOleh(peng1);
                    ia.setTarikhMasuk(new java.util.Date());
                    permohonanKertasKandungan.setInfoAudit(ia);
                    pelupusanService.saveOrUpdatePermohonanKertasKandungan(permohonanKertasKandungan);
                    addSimpleMessage("Maklumat Berjaya Disimpan");
                } else {
                    addSimpleError("Sila masukkan Maklumat Syor.");
                }
            }
            int bill4 = 4;
            mohonKertasKandListFour = new ArrayList<PermohonanKertasKandungan>();
            mohonKertasKandListFour = pelupusanService.findByIdKertasNbilforParent(permohonanKertasTemp.getIdKertas(), bill4);
            //edit3 = true;

        }
        rehydrate();
        return new JSP("pelupusan/kertas_siasaatn_N9.jsp").addParameter("tab", "true");

    }

    public Resolution tambahRow2() throws Exception {
        Pengguna peng1 = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        permohonanKertasTemp2 = new PermohonanKertas();
        permohonanKertasKandungan = new PermohonanKertasKandungan();
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = new Permohonan();
        permohonan = permohonanDAO.findById(idPermohonan);
        permohonanKertasTemp2 = pelupusanService.findPermohonanKertasByIdPermohonanNKodDokumen(idPermohonan, "KSM");
        LOG.info("permohonanKertasTemp2.size():" + permohonanKertasTemp2.getIdKertas());
        String index = getContext().getRequest().getParameter("index");
        if (index.equals("1")) {
            if (mohonKertasKandListOne1.size() > 0) {
                for (int i = 1; i <= mohonKertasKandListOne1.size() + 1; i++) {
                    String kandungan = getContext().getRequest().getParameter("1kandungan" + String.valueOf(i));
                    LOG.info("kandungan.size():" + kandungan);
                    if (kandungan != null && !kandungan.equals("")) {
                        String idMohonKertas = getContext().getRequest().getParameter("1idMohonKertas" + String.valueOf(i));
                        if (idMohonKertas != null && !idMohonKertas.equals("")) {
                            permohonanKertasKandungan = new PermohonanKertasKandungan();
                            permohonanKertasKandungan = permohonanKertasKandunganDAO.findById(new Long(idMohonKertas));
                            if (permohonanKertasKandungan != null) {
                                ia = new InfoAudit();
                                ia = permohonanKertasKandungan.getInfoAudit();
                                ia.setDiKemaskiniOleh(peng1);
                                ia.setTarikhKemaskini(new java.util.Date());
                                permohonanKertasKandungan.setInfoAudit(ia);
                                permohonanKertasKandungan.setSubtajuk(String.valueOf(i));
                                permohonanKertasKandungan.setKandungan(kandungan);
                            }
                        } else {
                            permohonanKertasKandungan = new PermohonanKertasKandungan();
                            permohonanKertasKandungan.setKertas(permohonanKertasTemp2);
                            permohonanKertasKandungan.setCawangan(permohonan.getCawangan());
                            permohonanKertasKandungan.setBil(1);
                            permohonanKertasKandungan.setSubtajuk(String.valueOf(i));
                            permohonanKertasKandungan.setKandungan(kandungan);
                            ia = new InfoAudit();
                            ia.setDimasukOleh(peng1);
                            ia.setTarikhMasuk(new java.util.Date());
                            permohonanKertasKandungan.setInfoAudit(ia);

                        }
                        pelupusanService.saveOrUpdatePermohonanKertasKandungan(permohonanKertasKandungan);
                        addSimpleMessage("Maklumat Berjaya Disimpan");
                    } else {
                        addSimpleError("Sila Masukkan Maklumat Tujuan.");
                    }
                }
            } else {
                String kandungan_ref = getContext().getRequest().getParameter("1kandungan1");
                if (kandungan_ref != null && !kandungan_ref.equals("")) {
                    permohonanKertasKandungan = new PermohonanKertasKandungan();
                    permohonanKertasKandungan.setKertas(permohonanKertasTemp2);
                    permohonanKertasKandungan.setCawangan(permohonan.getCawangan());
                    permohonanKertasKandungan.setBil(1);
                    permohonanKertasKandungan.setSubtajuk("1");
                    permohonanKertasKandungan.setKandungan(kandungan_ref);
                    ia = new InfoAudit();
                    ia.setDimasukOleh(peng1);
                    ia.setTarikhMasuk(new java.util.Date());
                    permohonanKertasKandungan.setInfoAudit(ia);
                    pelupusanService.saveOrUpdatePermohonanKertasKandungan(permohonanKertasKandungan);
                    addSimpleMessage("Maklumat Berjaya Disimpan");
                } else {
                    System.out.println(" :" + kandungan_ref + ":");
                    addSimpleError("Sila Masukkan Maklumat Tujuan.");
                }
            }
            int bill1 = 1;
            mohonKertasKandListOne1 = new ArrayList<PermohonanKertasKandungan>();
            mohonKertasKandListOne1 = pelupusanService.findByIdKertasNbilforParent(permohonanKertasTemp2.getIdKertas(), bill1);
            //edit = true;

        } else if (index.equals("2")) {
            if (mohonKertasKandListTwo2.size() > 0) {
                for (int i = 1; i <= mohonKertasKandListTwo2.size() + 1; i++) {
                    String kandungan = getContext().getRequest().getParameter("2kandungan" + String.valueOf(i));
                    if (kandungan != null && !kandungan.equals("")) {
                        String idMohonKertas = getContext().getRequest().getParameter("2idMohonKertas" + String.valueOf(i));
                        if (idMohonKertas != null && !idMohonKertas.equals("")) {
                            permohonanKertasKandungan = new PermohonanKertasKandungan();
                            permohonanKertasKandungan = permohonanKertasKandunganDAO.findById(new Long(idMohonKertas));
                            if (permohonanKertasKandungan != null) {
                                ia = new InfoAudit();
                                ia = permohonanKertasKandungan.getInfoAudit();
                                ia.setDiKemaskiniOleh(peng1);
                                ia.setTarikhKemaskini(new java.util.Date());
                                permohonanKertasKandungan.setInfoAudit(ia);
                                permohonanKertasKandungan.setSubtajuk(String.valueOf(i));
                                permohonanKertasKandungan.setKandungan(kandungan);

                            }
                        } else {
                            permohonanKertasKandungan = new PermohonanKertasKandungan();
                            permohonanKertasKandungan.setKertas(permohonanKertasTemp2);
                            permohonanKertasKandungan.setCawangan(permohonan.getCawangan());
                            permohonanKertasKandungan.setBil(2);
                            permohonanKertasKandungan.setSubtajuk(String.valueOf(i));
                            permohonanKertasKandungan.setKandungan(kandungan);
                            ia = new InfoAudit();
                            ia.setDimasukOleh(peng1);
                            ia.setTarikhMasuk(new java.util.Date());
                            permohonanKertasKandungan.setInfoAudit(ia);

                        }
                        pelupusanService.saveOrUpdatePermohonanKertasKandungan(permohonanKertasKandungan);
                        addSimpleMessage("Maklumat Berjaya Disimpan");
                    } else {
                        addSimpleError("Sila Masukkan Maklumat Kedudukan.");
                    }
                }
            } else {
                String kandungan_ref = getContext().getRequest().getParameter("2kandungan1");
                if (kandungan_ref != null && !kandungan_ref.equals("")) {
                    permohonanKertasKandungan = new PermohonanKertasKandungan();
                    permohonanKertasKandungan.setKertas(permohonanKertasTemp2);
                    permohonanKertasKandungan.setCawangan(permohonan.getCawangan());
                    permohonanKertasKandungan.setBil(2);
                    permohonanKertasKandungan.setSubtajuk("1");
                    permohonanKertasKandungan.setKandungan(kandungan_ref);
                    ia = new InfoAudit();
                    ia.setDimasukOleh(peng1);
                    ia.setTarikhMasuk(new java.util.Date());
                    permohonanKertasKandungan.setInfoAudit(ia);
                    pelupusanService.saveOrUpdatePermohonanKertasKandungan(permohonanKertasKandungan);
                    addSimpleMessage("Maklumat Berjaya Disimpan");
                } else {
                    addSimpleError("Sila Masukkan Maklumat Kedudukan.");
                }
            }
            int bill2 = 2;
            mohonKertasKandListTwo2 = new ArrayList<PermohonanKertasKandungan>();
            mohonKertasKandListTwo2 = pelupusanService.findByIdKertasNbilforParent(permohonanKertasTemp2.getIdKertas(), bill2);
            //edit1 = true;

        } else if (index.equals("3")) {
            if (mohonKertasKandListThree3.size() > 0) {
                for (int i = 1; i <= mohonKertasKandListThree3.size() + 1; i++) {
                    String kandungan = getContext().getRequest().getParameter("3kandungan" + String.valueOf(i));
                    if (kandungan != null && !kandungan.equals("")) {
                        String idMohonKertas = getContext().getRequest().getParameter("3idMohonKertas" + String.valueOf(i));
                        if (idMohonKertas != null && !idMohonKertas.equals("")) {
                            permohonanKertasKandungan = new PermohonanKertasKandungan();
                            permohonanKertasKandungan = permohonanKertasKandunganDAO.findById(new Long(idMohonKertas));
                            if (permohonanKertasKandungan != null) {
                                ia = new InfoAudit();
                                ia = permohonanKertasKandungan.getInfoAudit();
                                ia.setDiKemaskiniOleh(peng1);
                                ia.setTarikhKemaskini(new java.util.Date());
                                permohonanKertasKandungan.setInfoAudit(ia);
                                permohonanKertasKandungan.setSubtajuk(String.valueOf(i));
                                permohonanKertasKandungan.setKandungan(kandungan);

                            }
                        } else {
                            permohonanKertasKandungan = new PermohonanKertasKandungan();
                            permohonanKertasKandungan.setKertas(permohonanKertasTemp2);
                            permohonanKertasKandungan.setCawangan(permohonan.getCawangan());
                            permohonanKertasKandungan.setBil(3);
                            permohonanKertasKandungan.setSubtajuk(String.valueOf(i));
                            permohonanKertasKandungan.setKandungan(kandungan);
                            ia = new InfoAudit();
                            ia.setDimasukOleh(peng1);
                            ia.setTarikhMasuk(new java.util.Date());
                            permohonanKertasKandungan.setInfoAudit(ia);
                        }
                        pelupusanService.saveOrUpdatePermohonanKertasKandungan(permohonanKertasKandungan);
                        addSimpleMessage("Maklumat Berjaya Disimpan");
                    } else {
                        addSimpleError("Sila Masukkan Maklumat Hasil Siasatan.");
                    }
                }
            } else {
                String kandungan_ref = getContext().getRequest().getParameter("3kandungan1");
                //String kandungan_ref_in = getContext().getRequest().getParameter("3kandungan@");
                if (kandungan_ref != null && !kandungan_ref.equals("")) {
                    permohonanKertasKandungan = new PermohonanKertasKandungan();
                    permohonanKertasKandungan.setKertas(permohonanKertasTemp2);
                    permohonanKertasKandungan.setCawangan(permohonan.getCawangan());
                    permohonanKertasKandungan.setBil(3);
                    permohonanKertasKandungan.setSubtajuk("1");
                    permohonanKertasKandungan.setKandungan(kandungan_ref);
                    ia = new InfoAudit();
                    ia.setDimasukOleh(peng1);
                    ia.setTarikhMasuk(new java.util.Date());
                    permohonanKertasKandungan.setInfoAudit(ia);
                    pelupusanService.saveOrUpdatePermohonanKertasKandungan(permohonanKertasKandungan);
                    addSimpleMessage("Maklumat Berjaya Disimpan");
                } else {
                    addSimpleError("Sila Masukkan Maklumat Hasil Siasatan.");
                }

            }
            int bill3 = 3;
            mohonKertasKandListThree3 = new ArrayList<PermohonanKertasKandungan>();
            mohonKertasKandListThree3 = pelupusanService.findByIdKertasNbilforParent(permohonanKertasTemp2.getIdKertas(), bill3);
            //edit2 = true;

        } else if (index.equals("4")) {
            if (mohonKertasKandListFour4.size() > 0) {
                for (int i = 1; i <= mohonKertasKandListFour4.size() + 1; i++) {
                    String kandungan = getContext().getRequest().getParameter("4kandungan" + String.valueOf(i));
                    if (kandungan != null && !kandungan.equals("")) {
                        String idMohonKertas = getContext().getRequest().getParameter("4idMohonKertas" + String.valueOf(i));
                        if (idMohonKertas != null && !idMohonKertas.equals("")) {
                            permohonanKertasKandungan = new PermohonanKertasKandungan();
                            permohonanKertasKandungan = permohonanKertasKandunganDAO.findById(new Long(idMohonKertas));
                            if (permohonanKertasKandungan != null) {
                                ia = new InfoAudit();
                                ia = permohonanKertasKandungan.getInfoAudit();
                                ia.setDiKemaskiniOleh(peng1);
                                ia.setTarikhKemaskini(new java.util.Date());
                                permohonanKertasKandungan.setInfoAudit(ia);
                                permohonanKertasKandungan.setSubtajuk(String.valueOf(i));
                                permohonanKertasKandungan.setKandungan(kandungan);
                            }
                        } else {
                            permohonanKertasKandungan = new PermohonanKertasKandungan();
                            permohonanKertasKandungan.setKertas(permohonanKertasTemp2);
                            permohonanKertasKandungan.setCawangan(permohonan.getCawangan());
                            permohonanKertasKandungan.setBil(4);
                            permohonanKertasKandungan.setSubtajuk(String.valueOf(i));
                            permohonanKertasKandungan.setKandungan(kandungan);
                            ia = new InfoAudit();
                            ia.setDimasukOleh(peng1);
                            ia.setTarikhMasuk(new java.util.Date());
                            permohonanKertasKandungan.setInfoAudit(ia);
                        }
                        pelupusanService.saveOrUpdatePermohonanKertasKandungan(permohonanKertasKandungan);
                        addSimpleMessage("Maklumat Berjaya Disimpan");
                    } else {
                        addSimpleError("Sila Masukkan Maklumat Syor.");
                    }
                }
            } else {
                String kandungan_ref = getContext().getRequest().getParameter("4kandungan1");
                if (kandungan_ref != null && !kandungan_ref.equals("")) {
                    permohonanKertasKandungan = new PermohonanKertasKandungan();
                    permohonanKertasKandungan.setKertas(permohonanKertasTemp2);
                    permohonanKertasKandungan.setCawangan(permohonan.getCawangan());
                    permohonanKertasKandungan.setBil(4);
                    permohonanKertasKandungan.setSubtajuk("1");
                    permohonanKertasKandungan.setKandungan(kandungan_ref);
                    ia = new InfoAudit();
                    ia.setDimasukOleh(peng1);
                    ia.setTarikhMasuk(new java.util.Date());
                    permohonanKertasKandungan.setInfoAudit(ia);
                    pelupusanService.saveOrUpdatePermohonanKertasKandungan(permohonanKertasKandungan);
                    addSimpleMessage("Maklumat Berjaya Disimpan");
                } else {
                    addSimpleError("Sila masukkan Maklumat Syor.");
                }
            }
            int bill4 = 4;
            mohonKertasKandListFour4 = new ArrayList<PermohonanKertasKandungan>();
            mohonKertasKandListFour4 = pelupusanService.findByIdKertasNbilforParent(permohonanKertasTemp2.getIdKertas(), bill4);
            //edit3 = true;

        } else if (index.equals("5")) {
            if (mohonKertasKandListFive5.size() > 0) {
                for (int i = 1; i <= mohonKertasKandListFive5.size() + 1; i++) {
                    String kandungan = getContext().getRequest().getParameter("5kandungan" + String.valueOf(i));
                    LOG.info("kandungan.size():" + kandungan);
                    if (kandungan != null && !kandungan.equals("")) {
                        String idMohonKertas = getContext().getRequest().getParameter("5idMohonKertas" + String.valueOf(i));
                        if (idMohonKertas != null && !idMohonKertas.equals("")) {
                            permohonanKertasKandungan = new PermohonanKertasKandungan();
                            permohonanKertasKandungan = permohonanKertasKandunganDAO.findById(new Long(idMohonKertas));
                            if (permohonanKertasKandungan != null) {
                                ia = new InfoAudit();
                                ia = permohonanKertasKandungan.getInfoAudit();
                                ia.setDiKemaskiniOleh(peng1);
                                ia.setTarikhKemaskini(new java.util.Date());
                                permohonanKertasKandungan.setInfoAudit(ia);
                                permohonanKertasKandungan.setSubtajuk(String.valueOf(i));
                                permohonanKertasKandungan.setKandungan(kandungan);
                            }
                        } else {
                            permohonanKertasKandungan = new PermohonanKertasKandungan();
                            permohonanKertasKandungan.setKertas(permohonanKertasTemp2);
                            permohonanKertasKandungan.setCawangan(permohonan.getCawangan());
                            permohonanKertasKandungan.setBil(5);
                            permohonanKertasKandungan.setSubtajuk(String.valueOf(i));
                            permohonanKertasKandungan.setKandungan(kandungan);
                            ia = new InfoAudit();
                            ia.setDimasukOleh(peng1);
                            ia.setTarikhMasuk(new java.util.Date());
                            permohonanKertasKandungan.setInfoAudit(ia);

                        }
                        pelupusanService.saveOrUpdatePermohonanKertasKandungan(permohonanKertasKandungan);
                        addSimpleMessage("Maklumat Berjaya Disimpan");
                    } else {
                        addSimpleError("Sila Masukkan Maklumat Tujuan.");
                    }
                }
            } else {
                String kandungan_ref = getContext().getRequest().getParameter("5kandungan1");
                if (kandungan_ref != null && !kandungan_ref.equals("")) {
                    permohonanKertasKandungan = new PermohonanKertasKandungan();
                    permohonanKertasKandungan.setKertas(permohonanKertasTemp2);
                    permohonanKertasKandungan.setCawangan(permohonan.getCawangan());
                    permohonanKertasKandungan.setBil(5);
                    permohonanKertasKandungan.setSubtajuk("1");
                    permohonanKertasKandungan.setKandungan(kandungan_ref);
                    ia = new InfoAudit();
                    ia.setDimasukOleh(peng1);
                    ia.setTarikhMasuk(new java.util.Date());
                    permohonanKertasKandungan.setInfoAudit(ia);
                    pelupusanService.saveOrUpdatePermohonanKertasKandungan(permohonanKertasKandungan);
                    addSimpleMessage("Maklumat Berjaya Disimpan");
                } else {
                    System.out.println(" :" + kandungan_ref + ":");
                    addSimpleError("Sila Masukkan Maklumat Tujuan.");
                }
            }
            int bill1 = 1;
            mohonKertasKandListOne1 = new ArrayList<PermohonanKertasKandungan>();
            mohonKertasKandListOne1 = pelupusanService.findByIdKertasNbilforParent(permohonanKertasTemp2.getIdKertas(), bill1);
            //edit = true;

        }
        rehydrate();
        return new JSP("pelupusan/kertas_makluman.jsp").addParameter("tab", "true");

    }
    
    public Resolution tambahRowSiasatan() throws Exception {
        LOG.info("::::: tambahRowSiasatan ::::: ");
        String index = getContext().getRequest().getParameter("index");
        String sizeRow = getContext().getRequest().getParameter("sizeRow");
        LOG.info("::::: index : " + index + "::::: sizeRow :" + sizeRow);
        boolean statusSimpan = false;
        int s = 0;
        if (StringUtils.isNotBlank(sizeRow)) {
            s = Integer.parseInt(sizeRow);
        }
        LOG.info("::::: s:" + s);
//        ArrayList sizeAllRow = new ArrayList(s);
//        LOG.info("::::: size  sizeAllRow : " + sizeAllRow.size());
        if (s > 0) {
            for (int i = 1; i <= s; i++) {
                String kandungan = getContext().getRequest().getParameter(index + "kandungan" + String.valueOf(i));
                LOG.info("kandungan.size():" + kandungan);
                if (kandungan != null && !kandungan.equals("")) {
                    String idMohonKertas = getContext().getRequest().getParameter(index + "idMohonKertas" + String.valueOf(i));
                    if (idMohonKertas != null && !idMohonKertas.equals("")) {
                        permohonanKertasKandungan = new PermohonanKertasKandungan();
                        permohonanKertasKandungan = permohonanKertasKandunganDAO.findById(new Long(idMohonKertas));
                        if (permohonanKertasKandungan != null) {
                            ia = new InfoAudit();
                            ia = permohonanKertasKandungan.getInfoAudit();
                            ia.setDiKemaskiniOleh(peng);
                            ia.setTarikhKemaskini(new java.util.Date());
                            permohonanKertasKandungan.setInfoAudit(ia);
                            permohonanKertasKandungan.setSubtajuk(String.valueOf(i));
                            permohonanKertasKandungan.setKandungan(kandungan);
                        }
                    } else {
                        permohonanKertasKandungan = new PermohonanKertasKandungan();
                        permohonanKertasKandungan.setKertas(permohonanKertasTemp);
                        permohonanKertasKandungan.setCawangan(permohonan.getCawangan());
                        permohonanKertasKandungan.setBil(Integer.parseInt(index));
                        permohonanKertasKandungan.setSubtajuk(String.valueOf(i));
                        permohonanKertasKandungan.setKandungan(kandungan);
                        ia = new InfoAudit();
                        ia.setDimasukOleh(peng);
                        ia.setTarikhMasuk(new java.util.Date());
                        permohonanKertasKandungan.setInfoAudit(ia);

                    }
                    pelupusanService.saveOrUpdatePermohonanKertasKandungan(permohonanKertasKandungan);
                    statusSimpan = true;
//                        addSimpleMessage("Maklumat Berjaya Disimpan");
                } else {
                    if (index.equalsIgnoreCase("1")) {
                        addSimpleError("Sila Masukkan Maklumat Tujuan.");
                        statusSimpan = false;
                        statusEdit = true;
                    } else if (index.equalsIgnoreCase("2")) {
                        addSimpleError("Sila Masukkan Maklumat Kedudukan.");
                        statusSimpan = false;
                        statusEdit1 = true;
                    } else if (index.equalsIgnoreCase("3")) {
                        addSimpleError("Sila Masukkan Maklumat Hasil Siasatan.");
                        statusSimpan = false;
                        statusEdit2 = true;
                    } else {
                        addSimpleError("Sila masukkan Maklumat Syor.");
                        statusSimpan = false;
                        statusEdit3 = true;
                    }
                }
            }
        } else {
            String kandungan_ref = getContext().getRequest().getParameter(index + "kandungan1");
            if (kandungan_ref != null && !kandungan_ref.equals("")) {
                permohonanKertasKandungan = new PermohonanKertasKandungan();
                permohonanKertasKandungan.setKertas(permohonanKertasTemp);
                permohonanKertasKandungan.setCawangan(permohonan.getCawangan());
                permohonanKertasKandungan.setBil(s);
                permohonanKertasKandungan.setSubtajuk("1");
                permohonanKertasKandungan.setKandungan(kandungan_ref);
                ia = new InfoAudit();
                ia.setDimasukOleh(peng);
                ia.setTarikhMasuk(new java.util.Date());
                permohonanKertasKandungan.setInfoAudit(ia);
                pelupusanService.saveOrUpdatePermohonanKertasKandungan(permohonanKertasKandungan);
                addSimpleMessage("Maklumat Berjaya Disimpan");
            } else {
                if (index.equalsIgnoreCase("1")) {
                    System.out.println(" :" + kandungan_ref + ":");
                    addSimpleError("Sila Masukkan Maklumat Tujuan.");
                    statusSimpan = false;
                } else if (index.equalsIgnoreCase("2")) {
                    addSimpleError("Sila Masukkan Maklumat Kedudukan.");
                    statusSimpan = false;
                } else if (index.equalsIgnoreCase("3")) {
                    addSimpleError("Sila Masukkan Maklumat Hasil Siasatan.");
                    statusSimpan = false;
                } else {
                    addSimpleError("Sila masukkan Maklumat Syor.");
                    statusSimpan = false;
                }
            }
        }

        if (index.equalsIgnoreCase("1")) {
            int bill1 = 1;
            mohonKertasKandListOne = new ArrayList<PermohonanKertasKandungan>();
            mohonKertasKandListOne = pelupusanService.findByIdKertasNbilforParent(permohonanKertasTemp.getIdKertas(), bill1);
        } else if (index.equalsIgnoreCase("2")) {
            int bill2 = 2;
            mohonKertasKandListTwo = new ArrayList<PermohonanKertasKandungan>();
            mohonKertasKandListTwo = pelupusanService.findByIdKertasNbilforParent(permohonanKertasTemp.getIdKertas(), bill2);
        } else if (index.equalsIgnoreCase("3")) {
            int bill3 = 3;
            mohonKertasKandListThree = new ArrayList<PermohonanKertasKandungan>();
            mohonKertasKandListThree = pelupusanService.findByIdKertasNbilforParent(permohonanKertasTemp.getIdKertas(), bill3);
        } else {
            int bill4 = 4;
            mohonKertasKandListFour = new ArrayList<PermohonanKertasKandungan>();
            mohonKertasKandListFour = pelupusanService.findByIdKertasNbilforParent(permohonanKertasTemp.getIdKertas(), bill4);
        }

        if (statusSimpan == true) {
            addSimpleMessage("Maklumat Berjaya Disimpan");
        }

        LOG.info("::: statusEdit (tambahRowSiasatan):::" + statusEdit);
        LOG.info("::: statusEdit1 (tambahRowSiasatan):::" + statusEdit1);
        LOG.info("::: statusEdit2 (tambahRowSiasatan):::" + statusEdit2);
        LOG.info("::: statusEdit3 (tambahRowSiasatan):::" + statusEdit3);
        rehydrate();
        return new JSP("pelupusan/kertas_siasaatn_N9.jsp").addParameter("tab", "true");
    }

    public Resolution deleteRow() throws Exception {
        String idKand = getContext().getRequest().getParameter("idKandungan");
        LOG.info("idKand:========" + idKand);
        if (idKand != null) {
            PermohonanKertasKandungan plk = new PermohonanKertasKandungan();
            plk = permohonanKertasKandunganDAO.findById(Long.parseLong(idKand));
            if (plk != null) {
                pelPtService.deleteKertasKandungan(plk);

            }
        }
        rehydrate();
        LOG.info("End:========rehydrate");
        return new RedirectResolution(KertasSiasatnN9ActionBean.class, "locate");
    }

    public Resolution deleteRowMakluman() throws Exception {
        String idKand = getContext().getRequest().getParameter("idKandungan");
        LOG.info("idKand:========" + idKand);
        if (idKand != null) {
            PermohonanKertasKandungan plk = new PermohonanKertasKandungan();
            plk = permohonanKertasKandunganDAO.findById(Long.parseLong(idKand));
            if (plk != null) {
                pelPtService.deleteKertasKandungan(plk);

            }
        }
        rehydrate();
        LOG.info("End:========rehydrate");
        return new JSP("pelupusan/kertas_makluman.jsp").addParameter("tab", "true");
    }
    
    public boolean isEdit() {
        return edit;
    }

    public void setEdit(boolean edit) {
        this.edit = edit;
    }

    public boolean isEdit1() {
        return edit1;
    }

    public void setEdit1(boolean edit1) {
        this.edit1 = edit1;
    }

    public boolean isEdit2() {
        return edit2;
    }

    public void setEdit2(boolean edit2) {
        this.edit2 = edit2;
    }

    public boolean isEdit3() {
        return edit3;
    }

    public void setEdit3(boolean edit3) {
        this.edit3 = edit3;
    }

    public HakmilikPermohonan getHakmilikPermohonan() {
        return hakmilikPermohonan;
    }

    public void setHakmilikPermohonan(HakmilikPermohonan hakmilikPermohonan) {
        this.hakmilikPermohonan = hakmilikPermohonan;
    }

    public List<PermohonanKertasKandungan> getMohonKertasKandListOne() {
        return mohonKertasKandListOne;
    }

    public void setMohonKertasKandListOne(List<PermohonanKertasKandungan> mohonKertasKandListOne) {
        this.mohonKertasKandListOne = mohonKertasKandListOne;
    }
    
    public List<PermohonanKertasKandungan> getMohonKertasKandListOne1() {
        return mohonKertasKandListOne1;
    }

    public void setMohonKertasKandListOne1(List<PermohonanKertasKandungan> mohonKertasKandListOne1) {
        this.mohonKertasKandListOne1 = mohonKertasKandListOne1;
    }

    public Pemohon getPemohon() {
        return pemohon;
    }

    public void setPemohon(Pemohon pemohon) {
        this.pemohon = pemohon;
    }

    public Permohonan getPermohonan() {
        return permohonan;
    }

    public void setPermohonan(Permohonan permohonan) {
        this.permohonan = permohonan;
    }

    public List<PermohonanKertasKandungan> getMohonKertasKandListFour() {
        return mohonKertasKandListFour;
    }

    public void setMohonKertasKandListFour(List<PermohonanKertasKandungan> mohonKertasKandListFour) {
        this.mohonKertasKandListFour = mohonKertasKandListFour;
    }

    public List<PermohonanKertasKandungan> getMohonKertasKandListThree() {
        return mohonKertasKandListThree;
    }

    public void setMohonKertasKandListThree(List<PermohonanKertasKandungan> mohonKertasKandListThree) {
        this.mohonKertasKandListThree = mohonKertasKandListThree;
    }

    public List<PermohonanKertasKandungan> getMohonKertasKandListTwo() {
        return mohonKertasKandListTwo;
    }

    public void setMohonKertasKandListTwo(List<PermohonanKertasKandungan> mohonKertasKandListTwo) {
        this.mohonKertasKandListTwo = mohonKertasKandListTwo;
    }

    public List<PermohonanKertasKandungan> getMohonKertasKandListFour4() {
        return mohonKertasKandListFour4;
    }

    public void setMohonKertasKandListFour4(List<PermohonanKertasKandungan> mohonKertasKandListFour4) {
        this.mohonKertasKandListFour4 = mohonKertasKandListFour4;
    }

    public List<PermohonanKertasKandungan> getMohonKertasKandListThree3() {
        return mohonKertasKandListThree3;
    }

    public void setMohonKertasKandListThree3(List<PermohonanKertasKandungan> mohonKertasKandListThree3) {
        this.mohonKertasKandListThree3 = mohonKertasKandListThree3;
    }

    public List<PermohonanKertasKandungan> getmohonKertasKandListFive5() {
        return mohonKertasKandListFive5;
    }

    public void setmohonKertasKandListFive5(List<PermohonanKertasKandungan> mohonKertasKandListFive5) {
        this.mohonKertasKandListFive5 = mohonKertasKandListFive5;
    }
    
    public List<PermohonanKertasKandungan> getMohonKertasKandListTwo2() {
        return mohonKertasKandListTwo2;
    }

    public void setMohonKertasKandListTwo2(List<PermohonanKertasKandungan> mohonKertasKandListTwo2) {
        this.mohonKertasKandListTwo2 = mohonKertasKandListTwo2;
    }
    public boolean isEdit23() {
        return edit23;
    }

    public void setEdit23(boolean edit23) {
        this.edit23 = edit23;
    }

    public int getbSize() {
        return bSize;
    }

    public void setbSize(int bSize) {
        this.bSize = bSize;
    }

    public List getListLaporTanahSpdnB() {
        return listLaporTanahSpdnB;
    }

    public void setListLaporTanahSpdnB(List listLaporTanahSpdnB) {
        this.listLaporTanahSpdnB = listLaporTanahSpdnB;
    }

    public List getListLaporTanahSpdnS() {
        return listLaporTanahSpdnS;
    }

    public void setListLaporTanahSpdnS(List listLaporTanahSpdnS) {
        this.listLaporTanahSpdnS = listLaporTanahSpdnS;
    }

    public List getListLaporTanahSpdnT() {
        return listLaporTanahSpdnT;
    }

    public void setListLaporTanahSpdnT(List listLaporTanahSpdnT) {
        this.listLaporTanahSpdnT = listLaporTanahSpdnT;
    }

    public List getListLaporTanahSpdnU() {
        return listLaporTanahSpdnU;
    }

    public void setListLaporTanahSpdnU(List listLaporTanahSpdnU) {
        this.listLaporTanahSpdnU = listLaporTanahSpdnU;
    }

    public PermohonanKertasKandungan getPermohonanKertasKandungan() {
        return permohonanKertasKandungan;
    }

    public void setPermohonanKertasKandungan(PermohonanKertasKandungan permohonanKertasKandungan) {
        this.permohonanKertasKandungan = permohonanKertasKandungan;
    }

    public int getsSize() {
        return sSize;
    }

    public void setsSize(int sSize) {
        this.sSize = sSize;
    }

    public int gettSize() {
        return tSize;
    }

    public void settSize(int tSize) {
        this.tSize = tSize;
    }

    public int getuSize() {
        return uSize;
    }

    public void setuSize(int uSize) {
        this.uSize = uSize;
    }

    public PermohonanKertas getPermohonanKertasTemp() {
        return permohonanKertasTemp;
    }

    public void setPermohonanKertasTemp(PermohonanKertas permohonanKertasTemp) {
        this.permohonanKertasTemp = permohonanKertasTemp;
    }

    public PermohonanKertas getPermohonanKertasTemp2() {
        return permohonanKertasTemp2;
    }

    public void setPermohonanKertasTemp2(PermohonanKertas permohonanKertasTemp2) {
        this.permohonanKertasTemp2 = permohonanKertasTemp2;
    }
    public String getIdPermohonan() {
        return idPermohonan;
    }

    public void setIdPermohonan(String idPermohonan) {
        this.idPermohonan = idPermohonan;
    }

    public Pengguna getPeng() {
        return peng;
    }

    public void setPeng(Pengguna peng) {
        this.peng = peng;
    }

    public boolean isStatusEdit() {
        return statusEdit;
    }

    public void setStatusEdit(boolean statusEdit) {
        this.statusEdit = statusEdit;
    }

    public boolean isStatusEdit1() {
        return statusEdit1;
    }

    public void setStatusEdit1(boolean statusEdit1) {
        this.statusEdit1 = statusEdit1;
    }

    public boolean isStatusEdit2() {
        return statusEdit2;
    }

    public void setStatusEdit2(boolean statusEdit2) {
        this.statusEdit2 = statusEdit2;
    }

    public boolean isStatusEdit23() {
        return statusEdit23;
    }

    public void setStatusEdit23(boolean statusEdit23) {
        this.statusEdit23 = statusEdit23;
    }

    public boolean isStatusEdit3() {
        return statusEdit3;
    }

    public void setStatusEdit3(boolean statusEdit3) {
        this.statusEdit3 = statusEdit3;
    }
}
