/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.stripes.pelupusan;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import com.tangosol.coherence.transaction.internal.storage.Session;
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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
//import etanah.view.stripes.pelupusan.disClass.LotSempadan;
import net.sourceforge.stripes.action.RedirectResolution;
import org.apache.log4j.Logger;
//import etanah.dao.KodLotDAO;
//import etanah.model.KodLot;

/**
 *
 * @author user
 */
@UrlBinding("/pelupusan/minit_mesyuarat_jksmnLSTP")
public class MinitMesyuaratJKSMNLSTPActionBean extends AbleActionBean {

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
    private InfoAudit ia;
    private Pengguna peng;
    private boolean edit, edit1, edit2, edit23, edit3, edit4, edit5, edit6 = false;
    private boolean viewOnly = false;
    private boolean asal = false;
    private List<PermohonanKertas> mohonKertasList;
    private List<PermohonanKertasKandungan> mohonKertasKandListOne;
    private List<PermohonanKertasKandungan> mohonKertasKandListTwo;
    private List<PermohonanKertasKandungan> mohonKertasKandListThree;
    private List<PermohonanKertasKandungan> mohonKertasKandListFour;
    private List<PermohonanKertasKandungan> mohonKertasKandListFive;
    private List<PermohonanKertasKandungan> mohonKertasKandListSix;
    private List<PermohonanKertasKandungan> mohonKertasKandListSeven;
    private List listLaporTanahSpdnU;
    private List listLaporTanahSpdnS;
    private List listLaporTanahSpdnB;
    private List listLaporTanahSpdnT;
    private int uSize;
    private int sSize;
    private int bSize;
    private int tSize;
    private String kodNegeri;
    private String noRuj;
    private String tarikhSidang;
//    private List<String> tarikhSidang = new ArrayList<String>();
    private String tempatSidang;
    private String jam;
    private String minit;
    private String ampm;
//    private List<String> jam = new ArrayList<String>();
//    private List<String> minit = new ArrayList<String>();
//    private List<String> ampm = new ArrayList<String>();
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    SimpleDateFormat sdf0 = new SimpleDateFormat("dd/MM/yyyy");
    SimpleDateFormat sdf1 = new SimpleDateFormat("dd/MM/yyyy hh:mm aaa");
    SimpleDateFormat sdf2 = new SimpleDateFormat("dd/MM/yyyy hh:mm aaa");

    @DefaultHandler
    public Resolution showForm() {

        return new JSP("pelupusan/minit_mesyuarat_jksmnLSTP.jsp").addParameter("tab", "true");
    }

    public Resolution viewOnly() {

        return new JSP("pelupusan/minit_mesyuarat_jksmn_view_LSTP.jsp").addParameter("tab", "true");
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

            permohonanKertasTemp = pelupusanService.findPermohonanKertasByIdPermohonanNKodDokumen(idPermohonan, "MNMJ");
            PermohonanKertasKandungan permohonanKertasKandungan_ref = new PermohonanKertasKandungan();
            if (permohonanKertasTemp == null) {
                ia = new InfoAudit();
                permohonanKertasTemp = new PermohonanKertas();
                permohonanKertasTemp.setCawangan(permohonan.getCawangan());
                permohonanKertasTemp.setPermohonan(permohonan);
                KodDokumen kodDok = new KodDokumen();
                kodDok = kodDokumenDAO.findById("MNMJ");
                permohonanKertasTemp.setKodDokumen(kodDok);
                permohonanKertasTemp.setTajuk("Minit Mesyuarat JKSMN");
                ia.setDimasukOleh(peng);
                ia.setTarikhMasuk(new java.util.Date());
                permohonanKertasTemp.setInfoAudit(ia);

            } else {
                ia = new InfoAudit();
                ia = permohonanKertasTemp.getInfoAudit();
                ia.setTarikhKemaskini(new java.util.Date());
                ia.setDiKemaskiniOleh(peng);
                permohonanKertasTemp.setInfoAudit(ia);
            }
            permohonanKertasTemp = pelupusanService.simpanPermohonanKertas(permohonanKertasTemp);

            if (permohonanKertasTemp.getTarikhSidang() != null) {
                noRuj = permohonanKertasTemp.getNomborRujukan();
                tempatSidang = permohonanKertasTemp.getTempatSidang();

                tarikhSidang = sdf0.format(permohonanKertasTemp.getTarikhSidang());
                jam = sdf1.format(permohonanKertasTemp.getTarikhSidang()).substring(11, 13);
                System.out.println("jam trh_operasi : " + jam);
                if (jam.startsWith("0")) {
                    jam = sdf1.format(permohonanKertasTemp.getTarikhSidang()).substring(12, 13);
                }
                minit = sdf1.format(permohonanKertasTemp.getTarikhSidang()).substring(14, 16);
                System.out.println("minit trh_operasi : " + minit);
                ampm = sdf1.format(permohonanKertasTemp.getTarikhSidang()).substring(17, 19);

            }


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
            int bill5 = 5;
            mohonKertasKandListFive = new ArrayList<PermohonanKertasKandungan>();
            mohonKertasKandListFive = pelupusanService.findByIdKertasNbilforParent(permohonanKertasTemp.getIdKertas(), bill5);
            edit4 = (mohonKertasKandListFive.size() > 0) ? false : true;
            int bill6 = 6;
            mohonKertasKandListSix = new ArrayList<PermohonanKertasKandungan>();
            mohonKertasKandListSix = pelupusanService.findByIdKertasNbilforParent(permohonanKertasTemp.getIdKertas(), bill6);
            edit5 = (mohonKertasKandListSix.size() > 0) ? false : true;
            int bill7 = 7;
            mohonKertasKandListSeven = new ArrayList<PermohonanKertasKandungan>();
            mohonKertasKandListSeven = pelupusanService.findByIdKertasNbilforParent(permohonanKertasTemp.getIdKertas(), bill7);
            edit6 = (mohonKertasKandListSeven.size() > 0) ? false : true;

        }

    }

    public Resolution tambahRow() throws Exception {
        Pengguna peng1 = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        permohonanKertasTemp = new PermohonanKertas();
        permohonanKertasKandungan = new PermohonanKertasKandungan();
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = new Permohonan();
        permohonan = permohonanDAO.findById(idPermohonan);
        permohonanKertasTemp = pelupusanService.findPermohonanKertasByIdPermohonanNKodDokumen(idPermohonan, "MNMJ");
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
                }

            }
            int bill3 = 3;
            mohonKertasKandListThree = new ArrayList<PermohonanKertasKandungan>();
            mohonKertasKandListThree = pelupusanService.findByIdKertasNbilforParent(permohonanKertasTemp.getIdKertas(), bill3);
//            permohonanKertasKandungan.setBil((short) 3);
//            mohonKertasKandListThree.add(permohonanKertasKandungan);
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
                }
            }
            int bill4 = 4;
            mohonKertasKandListFour = new ArrayList<PermohonanKertasKandungan>();
            mohonKertasKandListFour = pelupusanService.findByIdKertasNbilforParent(permohonanKertasTemp.getIdKertas(), bill4);
            edit3 = true;
            //addSimpleMessage("Maklumat Berjaya Disimpan");
        } else if (index.equals("5")) {
            if (mohonKertasKandListFive.size() > 0) {
                for (int i = 1; i <= mohonKertasKandListFive.size() + 1; i++) {
                    String kandungan = getContext().getRequest().getParameter("5kandungan" + String.valueOf(i));
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
                            permohonanKertasKandungan.setKertas(permohonanKertasTemp);
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

                    }
                }
            } else {
                String kandungan_ref = getContext().getRequest().getParameter("5kandungan1");
                if (kandungan_ref != null && !kandungan_ref.equals("")) {
                    permohonanKertasKandungan = new PermohonanKertasKandungan();
                    permohonanKertasKandungan.setKertas(permohonanKertasTemp);
                    permohonanKertasKandungan.setCawangan(permohonan.getCawangan());
                    permohonanKertasKandungan.setBil(5);
                    permohonanKertasKandungan.setSubtajuk("1");
                    permohonanKertasKandungan.setKandungan(kandungan_ref);
                    ia = new InfoAudit();
                    ia.setDimasukOleh(peng1);
                    ia.setTarikhMasuk(new java.util.Date());
                    permohonanKertasKandungan.setInfoAudit(ia);
                    pelupusanService.saveOrUpdatePermohonanKertasKandungan(permohonanKertasKandungan);
                }
            }
            int bill5 = 5;
            mohonKertasKandListFive = new ArrayList<PermohonanKertasKandungan>();
            mohonKertasKandListFive = pelupusanService.findByIdKertasNbilforParent(permohonanKertasTemp.getIdKertas(), bill5);
            edit4 = true;
            //addSimpleMessage("Maklumat Berjaya Disimpan");
        } else if (index.equals("6")) {
            if (mohonKertasKandListSix.size() > 0) {
                for (int i = 1; i <= mohonKertasKandListSix.size() + 1; i++) {
                    String kandungan = getContext().getRequest().getParameter("6kandungan" + String.valueOf(i));
                    if (kandungan != null && !kandungan.equals("")) {
                        String idMohonKertas = getContext().getRequest().getParameter("6idMohonKertas" + String.valueOf(i));
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
                            permohonanKertasKandungan.setBil(6);
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
                String kandungan_ref = getContext().getRequest().getParameter("6kandungan1");
                if (kandungan_ref != null && !kandungan_ref.equals("")) {
                    permohonanKertasKandungan = new PermohonanKertasKandungan();
                    permohonanKertasKandungan.setKertas(permohonanKertasTemp);
                    permohonanKertasKandungan.setCawangan(permohonan.getCawangan());
                    permohonanKertasKandungan.setBil(6);
                    permohonanKertasKandungan.setSubtajuk("1");
                    permohonanKertasKandungan.setKandungan(kandungan_ref);
                    ia = new InfoAudit();
                    ia.setDimasukOleh(peng1);
                    ia.setTarikhMasuk(new java.util.Date());
                    permohonanKertasKandungan.setInfoAudit(ia);
                    pelupusanService.saveOrUpdatePermohonanKertasKandungan(permohonanKertasKandungan);
                }
            }
            int bill6 = 6;
            mohonKertasKandListSix = new ArrayList<PermohonanKertasKandungan>();
            mohonKertasKandListSix = pelupusanService.findByIdKertasNbilforParent(permohonanKertasTemp.getIdKertas(), bill6);
            edit5 = true;
            //addSimpleMessage("Maklumat Berjaya Disimpan");
        } else if (index.equals("7")) {
            if (mohonKertasKandListSeven.size() > 0) {
                for (int i = 1; i <= mohonKertasKandListSeven.size() + 1; i++) {
                    String kandungan = getContext().getRequest().getParameter("7kandungan" + String.valueOf(i));
                    if (kandungan != null && !kandungan.equals("")) {
                        String idMohonKertas = getContext().getRequest().getParameter("7idMohonKertas" + String.valueOf(i));
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
                            permohonanKertasKandungan.setBil(7);
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
                String kandungan_ref = getContext().getRequest().getParameter("7kandungan1");
                if (kandungan_ref != null && !kandungan_ref.equals("")) {
                    permohonanKertasKandungan = new PermohonanKertasKandungan();
                    permohonanKertasKandungan.setKertas(permohonanKertasTemp);
                    permohonanKertasKandungan.setCawangan(permohonan.getCawangan());
                    permohonanKertasKandungan.setBil(7);
                    permohonanKertasKandungan.setSubtajuk("1");
                    permohonanKertasKandungan.setKandungan(kandungan_ref);
                    ia = new InfoAudit();
                    ia.setDimasukOleh(peng1);
                    ia.setTarikhMasuk(new java.util.Date());
                    permohonanKertasKandungan.setInfoAudit(ia);
                    pelupusanService.saveOrUpdatePermohonanKertasKandungan(permohonanKertasKandungan);
                }
            }
            int bill7 = 7;
            mohonKertasKandListSeven = new ArrayList<PermohonanKertasKandungan>();
            mohonKertasKandListSeven = pelupusanService.findByIdKertasNbilforParent(permohonanKertasTemp.getIdKertas(), bill7);
            edit6 = true;
            //addSimpleMessage("Maklumat Berjaya Disimpan");
        }
        return new JSP("pelupusan/minit_mesyuarat_jksmnLSTP.jsp").addParameter("tab", "true");
    }

    public Resolution tambahRow1() throws Exception {
        Pengguna peng1 = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        permohonanKertasTemp = new PermohonanKertas();
        permohonanKertasKandungan = new PermohonanKertasKandungan();
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = new Permohonan();
        permohonan = permohonanDAO.findById(idPermohonan);
        permohonanKertasTemp = pelupusanService.findPermohonanKertasByIdPermohonanNKodDokumen(idPermohonan, "MNMJ");
        LOG.info("permohonanKertasTemp.size():" + permohonanKertasTemp.getIdKertas());
        String index = getContext().getRequest().getParameter("index");

        if (permohonanKertasTemp == null) {
            ia = new InfoAudit();
            permohonanKertasTemp = new PermohonanKertas();
            permohonanKertasTemp.setCawangan(permohonan.getCawangan());
            permohonanKertasTemp.setPermohonan(permohonan);
            KodDokumen kodDok = new KodDokumen();
            kodDok = kodDokumenDAO.findById("MNMJ");
            permohonanKertasTemp.setKodDokumen(kodDok);
            permohonanKertasTemp.setTajuk("Minit Mesyuarat JKSMN");
            ia.setDimasukOleh(peng);
            ia.setTarikhMasuk(new java.util.Date());
            permohonanKertasTemp.setInfoAudit(ia);

        } else {
            ia = new InfoAudit();
            ia = permohonanKertasTemp.getInfoAudit();
            ia.setTarikhKemaskini(new java.util.Date());
            ia.setDiKemaskiniOleh(peng);
            permohonanKertasTemp.setInfoAudit(ia);
            permohonanKertasTemp.setNomborRujukan(noRuj);
            permohonanKertasTemp.setTempatSidang(tempatSidang);

            String StrTarikhBicara = tarikhSidang;
//                tarikhSidang = tarikhSidang + " " + jam + ":" + minit + " " + ampm;
            StrTarikhBicara = StrTarikhBicara + " " + jam + ":" + minit + " " + ampm;
            permohonanKertasTemp.setTarikhSidang(sdf1.parse(StrTarikhBicara));

//                permohonanKertasTemp.setTarikhSidang(sdf1.parse(tarikhSidang));

//                }
        }
        permohonanKertasTemp = pelupusanService.simpanPermohonanKertas(permohonanKertasTemp);

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
                }
            }
            int bill1 = 1;
            mohonKertasKandListOne = new ArrayList<PermohonanKertasKandungan>();
            mohonKertasKandListOne = pelupusanService.findByIdKertasNbilforParent(permohonanKertasTemp.getIdKertas(), bill1);
            //edit = true;
            addSimpleMessage("Maklumat Berjaya Disimpan");
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
                }
            }
            int bill2 = 2;
            mohonKertasKandListTwo = new ArrayList<PermohonanKertasKandungan>();
            mohonKertasKandListTwo = pelupusanService.findByIdKertasNbilforParent(permohonanKertasTemp.getIdKertas(), bill2);
            //edit1 = true;
            addSimpleMessage("Maklumat Berjaya Disimpan");
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
                }


            }
            int bill3 = 3;
            mohonKertasKandListThree = new ArrayList<PermohonanKertasKandungan>();
            mohonKertasKandListThree = pelupusanService.findByIdKertasNbilforParent(permohonanKertasTemp.getIdKertas(), bill3);
            //edit2 = true;
            addSimpleMessage("Maklumat Berjaya Disimpan");
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
                }
            }
            int bill4 = 4;
            mohonKertasKandListFour = new ArrayList<PermohonanKertasKandungan>();
            mohonKertasKandListFour = pelupusanService.findByIdKertasNbilforParent(permohonanKertasTemp.getIdKertas(), bill4);
            //edit3 = true;
            addSimpleMessage("Maklumat Berjaya Disimpan");
        } else if (index.equals("5")) {
            if (mohonKertasKandListFive.size() > 0) {
                for (int i = 1; i <= mohonKertasKandListFive.size() + 1; i++) {
                    String kandungan = getContext().getRequest().getParameter("5kandungan" + String.valueOf(i));
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
                            permohonanKertasKandungan.setKertas(permohonanKertasTemp);
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

                    }
                }
            } else {
                String kandungan_ref = getContext().getRequest().getParameter("5kandungan1");
                if (kandungan_ref != null && !kandungan_ref.equals("")) {
                    permohonanKertasKandungan = new PermohonanKertasKandungan();
                    permohonanKertasKandungan.setKertas(permohonanKertasTemp);
                    permohonanKertasKandungan.setCawangan(permohonan.getCawangan());
                    permohonanKertasKandungan.setBil(5);
                    permohonanKertasKandungan.setSubtajuk("1");
                    permohonanKertasKandungan.setKandungan(kandungan_ref);
                    ia = new InfoAudit();
                    ia.setDimasukOleh(peng1);
                    ia.setTarikhMasuk(new java.util.Date());
                    permohonanKertasKandungan.setInfoAudit(ia);
                    pelupusanService.saveOrUpdatePermohonanKertasKandungan(permohonanKertasKandungan);
                }
            }
            int bill5 = 5;
            mohonKertasKandListFive = new ArrayList<PermohonanKertasKandungan>();
            mohonKertasKandListFive = pelupusanService.findByIdKertasNbilforParent(permohonanKertasTemp.getIdKertas(), bill5);
            //edit3 = true;
            addSimpleMessage("Maklumat Berjaya Disimpan");
        } else if (index.equals("6")) {
            if (mohonKertasKandListSix.size() > 0) {
                for (int i = 1; i <= mohonKertasKandListSix.size() + 1; i++) {
                    String kandungan = getContext().getRequest().getParameter("6kandungan" + String.valueOf(i));
                    if (kandungan != null && !kandungan.equals("")) {
                        String idMohonKertas = getContext().getRequest().getParameter("6idMohonKertas" + String.valueOf(i));
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
                            permohonanKertasKandungan.setBil(6);
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
                String kandungan_ref = getContext().getRequest().getParameter("6kandungan1");
                if (kandungan_ref != null && !kandungan_ref.equals("")) {
                    permohonanKertasKandungan = new PermohonanKertasKandungan();
                    permohonanKertasKandungan.setKertas(permohonanKertasTemp);
                    permohonanKertasKandungan.setCawangan(permohonan.getCawangan());
                    permohonanKertasKandungan.setBil(6);
                    permohonanKertasKandungan.setSubtajuk("1");
                    permohonanKertasKandungan.setKandungan(kandungan_ref);
                    ia = new InfoAudit();
                    ia.setDimasukOleh(peng1);
                    ia.setTarikhMasuk(new java.util.Date());
                    permohonanKertasKandungan.setInfoAudit(ia);
                    pelupusanService.saveOrUpdatePermohonanKertasKandungan(permohonanKertasKandungan);
                }
            }
            int bill6 = 6;
            mohonKertasKandListSix = new ArrayList<PermohonanKertasKandungan>();
            mohonKertasKandListSix = pelupusanService.findByIdKertasNbilforParent(permohonanKertasTemp.getIdKertas(), bill6);
            //edit3 = true;
            addSimpleMessage("Maklumat Berjaya Disimpan");
        } else if (index.equals("7")) {
            if (mohonKertasKandListSeven.size() > 0) {
                for (int i = 1; i <= mohonKertasKandListSeven.size() + 1; i++) {
                    String kandungan = getContext().getRequest().getParameter("7kandungan" + String.valueOf(i));
                    if (kandungan != null && !kandungan.equals("")) {
                        String idMohonKertas = getContext().getRequest().getParameter("7idMohonKertas" + String.valueOf(i));
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
                            permohonanKertasKandungan.setBil(7);
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
                String kandungan_ref = getContext().getRequest().getParameter("7kandungan1");
                if (kandungan_ref != null && !kandungan_ref.equals("")) {
                    permohonanKertasKandungan = new PermohonanKertasKandungan();
                    permohonanKertasKandungan.setKertas(permohonanKertasTemp);
                    permohonanKertasKandungan.setCawangan(permohonan.getCawangan());
                    permohonanKertasKandungan.setBil(7);
                    permohonanKertasKandungan.setSubtajuk("1");
                    permohonanKertasKandungan.setKandungan(kandungan_ref);
                    ia = new InfoAudit();
                    ia.setDimasukOleh(peng1);
                    ia.setTarikhMasuk(new java.util.Date());
                    permohonanKertasKandungan.setInfoAudit(ia);
                    pelupusanService.saveOrUpdatePermohonanKertasKandungan(permohonanKertasKandungan);
                }
            }
            int bill7 = 7;
            mohonKertasKandListSeven = new ArrayList<PermohonanKertasKandungan>();
            mohonKertasKandListSeven = pelupusanService.findByIdKertasNbilforParent(permohonanKertasTemp.getIdKertas(), bill7);
            //edit3 = true;
            addSimpleMessage("Maklumat Berjaya Disimpan");
        }
        rehydrate();
        return new JSP("pelupusan/minit_mesyuarat_jksmnLSTP.jsp").addParameter("tab", "true");

    }

//    public Resolution deleteSelected() {
//        System.out.println("deleteSelected");
//        Long id = Long.parseLong(getContext().getRequest().getParameter("idImej"));
//
//        Session s = sessionProvider.get();
//        Transaction tx = s.beginTransaction();
//        tx.begin();
//        try {
//            Dokumen dok1 = dokumenDAO.findById(id);
//            dokumenDAO.delete(dok1);
//
//            tx.commit();
//
//        } catch (Exception ex) {
//            tx.rollback();
//            Throwable t = ex;
//            // getting the root cause
//            while (t.getCause() != null) {
//                t = t.getCause();
//            }
//            ex.printStackTrace();
//            addSimpleError(t.getMessage());
//        }
//        return new RedirectResolution(MinitMesyuaratJKSMNLSTPActionBean.class, "locate");
//    }

    public Resolution simpanMohonKertas() throws Exception {

        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = permohonanDAO.findById(idPermohonan);
        peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);

        if (permohonan != null) {
            pemohon = pelupusanService.findPemohon(idPermohonan);

            //saving Mohon Kertas
//            tarikhSidang = tarikhSidang + " " + jam + ":" + minit + " " + ampm;

            permohonanKertasTemp = pelupusanService.findPermohonanKertasByIdPermohonanNKodDokumen(idPermohonan, "MNMJ");
            PermohonanKertasKandungan permohonanKertasKandungan_ref = new PermohonanKertasKandungan();
            if (permohonanKertasTemp == null) {
                ia = new InfoAudit();
                permohonanKertasTemp = new PermohonanKertas();
                permohonanKertasTemp.setCawangan(permohonan.getCawangan());
                permohonanKertasTemp.setPermohonan(permohonan);
                KodDokumen kodDok = new KodDokumen();
                kodDok = kodDokumenDAO.findById("MNMJ");
                permohonanKertasTemp.setKodDokumen(kodDok);
                permohonanKertasTemp.setTajuk("Minit Mesyuarat JKSMN");
                ia.setDimasukOleh(peng);
                ia.setTarikhMasuk(new java.util.Date());
                permohonanKertasTemp.setInfoAudit(ia);

            } else {
                ia = new InfoAudit();
                ia = permohonanKertasTemp.getInfoAudit();
                ia.setTarikhKemaskini(new java.util.Date());
                ia.setDiKemaskiniOleh(peng);
                permohonanKertasTemp.setInfoAudit(ia);
                permohonanKertasTemp.setNomborRujukan(noRuj);
                permohonanKertasTemp.setTempatSidang(tempatSidang);

                String StrTarikhBicara = tarikhSidang;
//                tarikhSidang = tarikhSidang + " " + jam + ":" + minit + " " + ampm;
                StrTarikhBicara = StrTarikhBicara + " " + jam + ":" + minit + " " + ampm;
                permohonanKertasTemp.setTarikhSidang(sdf1.parse(StrTarikhBicara));

//                permohonanKertasTemp.setTarikhSidang(sdf1.parse(tarikhSidang));

//                }
            }
            permohonanKertasTemp = pelupusanService.simpanPermohonanKertas(permohonanKertasTemp);
        }

        rehydrate();
        LOG.info("End:========rehydrate update save mohon kertas");
        return new RedirectResolution(MinitMesyuaratJKSMNLSTPActionBean.class, "locate");
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
        return new RedirectResolution(MinitMesyuaratJKSMNLSTPActionBean.class, "locate");
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

    public boolean isViewOnly() {
        return viewOnly;
    }

    public void setViewOnly(boolean viewOnly) {
        this.viewOnly = viewOnly;
    }

    public String getKodNegeri() {
        return kodNegeri;
    }

    public void setKodNegeri(String kodNegeri) {
        this.kodNegeri = kodNegeri;
    }

    public boolean isEdit4() {
        return edit4;
    }

    public void setEdit4(boolean edit4) {
        this.edit4 = edit4;
    }

    public boolean isEdit5() {
        return edit5;
    }

    public void setEdit5(boolean edit5) {
        this.edit5 = edit5;
    }

    public boolean isEdit6() {
        return edit6;
    }

    public void setEdit6(boolean edit6) {
        this.edit6 = edit6;
    }

    public List<PermohonanKertasKandungan> getMohonKertasKandListFive() {
        return mohonKertasKandListFive;
    }

    public void setMohonKertasKandListFive(List<PermohonanKertasKandungan> mohonKertasKandListFive) {
        this.mohonKertasKandListFive = mohonKertasKandListFive;
    }

    public List<PermohonanKertasKandungan> getMohonKertasKandListSeven() {
        return mohonKertasKandListSeven;
    }

    public void setMohonKertasKandListSeven(List<PermohonanKertasKandungan> mohonKertasKandListSeven) {
        this.mohonKertasKandListSeven = mohonKertasKandListSeven;
    }

    public List<PermohonanKertasKandungan> getMohonKertasKandListSix() {
        return mohonKertasKandListSix;
    }

    public void setMohonKertasKandListSix(List<PermohonanKertasKandungan> mohonKertasKandListSix) {
        this.mohonKertasKandListSix = mohonKertasKandListSix;
    }

    public String getTempatSidang() {
        return tempatSidang;
    }

    public void setTempatSidang(String tempatSidang) {
        this.tempatSidang = tempatSidang;
    }

    public String getNoRuj() {
        return noRuj;
    }

    public void setNoRuj(String noRuj) {
        this.noRuj = noRuj;
    }

    public List<PermohonanKertas> getMohonKertasList() {
        return mohonKertasList;
    }

    public void setMohonKertasList(List<PermohonanKertas> mohonKertasList) {
        this.mohonKertasList = mohonKertasList;
    }

    public String getAmpm() {
        return ampm;
    }

    public void setAmpm(String ampm) {
        this.ampm = ampm;
    }

    public String getJam() {
        return jam;
    }

    public void setJam(String jam) {
        this.jam = jam;
    }

    public String getMinit() {
        return minit;
    }

    public void setMinit(String minit) {
        this.minit = minit;
    }

    public String getTarikhSidang() {
        return tarikhSidang;
    }

    public void setTarikhSidang(String tarikhSidang) {
        this.tarikhSidang = tarikhSidang;
    }

    public boolean isAsal() {
        return asal;
    }

    public void setAsal(boolean asal) {
        this.asal = asal;
    }
}


