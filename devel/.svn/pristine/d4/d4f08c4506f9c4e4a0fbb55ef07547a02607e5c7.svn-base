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
@UrlBinding("/strata/sebabTolak")
public class SebabTolak extends AbleActionBean {

    @Inject
    KodDokumenDAO kodDokumenDAO;
    @Inject
    HakmilikPermohonanDAO hakmilikPermohonanDAO;
    @Inject
    StrataPtService strService;
    private String kandungan;
    private String idPermohonan;
    private String nomborRujukan;
    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    private Permohonan permohonan;
    private PermohonanKertas mohonKertas;
    private FasaPermohonan mohonFasa;
    private String trhKpsn;
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

    @DefaultHandler
    public Resolution showForm1() {

        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = permohonanDAO.findById(idPermohonan);
        PermohonanKertas mhnKertas = strService.findIDKertasByIDMohonKod(idPermohonan, "SFUST");
        PermohonanKertas mhnKertasL = strService.findIDKertasByIDMohonKod(idPermohonan, "SFUSL");
        if (mhnKertas != null) {
            PermohonanKertasKandungan knd = strService.findByIdkertasBil(mhnKertas.getIdKertas(), 1);
            if (knd != null) {
                kandungan = knd.getKandungan();
            }
        }
        if (mhnKertasL != null) {
            PermohonanKertasKandungan knd = strService.findByIdkertasBil(mhnKertasL.getIdKertas(), 1);
            if (knd != null) {
                kandungan = knd.getKandungan();
            }
        }
        mohonFasa = strService.findMohonFasa(idPermohonan, "keputusan");

        return new JSP("strata/pbbm/sebabTolak.jsp").addParameter("tab", "true");
    }

    public Resolution tolakPKKR() {

        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = permohonanDAO.findById(idPermohonan);
        mohonFasa = strService.findMohonFasa(idPermohonan, "keputusan");
        if (mohonFasa != null) {
            trhKpsn = sdf.format(mohonFasa.getTarikhKeputusan());

            if (mohonFasa.getKeputusan().getKod().equals("T")) {
                mohonKertas = strService.findIDKertasByIDMohonKod(idPermohonan, "PKKRT");
            } else {
                mohonKertas = strService.findIDKertasByIDMohonKod(idPermohonan, "PKKRL");
            }
        }
        if (mohonKertas != null) {
            PermohonanKertasKandungan knd = strService.findByIdkertasBil(mohonKertas.getIdKertas(), 1);
            if (knd != null) {
                kandungan = knd.getKandungan();
            }
        }

        return new JSP("strata/pbbm/sebabTolak.jsp").addParameter("tab", "true");
    }

    public Resolution tolakSUBMC() {

        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = permohonanDAO.findById(idPermohonan);
        mohonFasa = strService.findMohonFasa(idPermohonan, "keputusan");
        if (mohonFasa != null) {
            trhKpsn = sdf.format(mohonFasa.getTarikhKeputusan());

            if (mohonFasa.getKeputusan().getKod().equals("T")) {
                mohonKertas = strService.findIDKertasByIDMohonKod(idPermohonan, "STLK");
            } else {
                mohonKertas = strService.findIDKertasByIDMohonKod(idPermohonan, "SKL");
            }
        }
        if (mohonKertas != null) {
            PermohonanKertasKandungan knd = strService.findByIdkertasBil(mohonKertas.getIdKertas(), 1);
            if (knd != null) {
                kandungan = knd.getKandungan();
            }
        }

        return new JSP("strata/pbbm/sebabTolak.jsp").addParameter("tab", "true");
    }

    public Resolution tolakPBBS() {

        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = permohonanDAO.findById(idPermohonan);
        mohonFasa = strService.findMohonFasa(idPermohonan, "keputusan");
        if (mohonFasa != null) {
            trhKpsn = sdf.format(mohonFasa.getTarikhKeputusan());

            if (mohonFasa.getKeputusan().getKod().equals("T")) {
                mohonKertas = strService.findIDKertasByIDMohonKod(idPermohonan, "STBP");
            }else{
                mohonKertas = strService.findIDKertasByIDMohonKod(idPermohonan, "SKL");
            }
        }
        if (mohonKertas != null) {
            PermohonanKertasKandungan knd = strService.findByIdkertasBil(mohonKertas.getIdKertas(), 1);
            if (knd != null) {
                kandungan = knd.getKandungan();
            }
        }

        return new JSP("strata/pbbm/sebabTolak.jsp").addParameter("tab", "true");
    }

    public Resolution simpanSebabTolak() {
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = permohonanDAO.findById(idPermohonan);

        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        InfoAudit infoAudit = new InfoAudit();
        infoAudit.setDimasukOleh(peng);
        infoAudit.setTarikhMasuk(new java.util.Date());
        InfoAudit infoAudit2 = new InfoAudit();
        infoAudit2.setDiKemaskiniOleh(peng);
        infoAudit2.setTarikhKemaskini(new java.util.Date());
        mohonFasa = strService.findMohonFasa(idPermohonan, "keputusan");

        PermohonanKertas mhnKertas = strService.findIDKertasByIDMohonKod(idPermohonan, "SFUST");
        PermohonanKertas mhnKertasL = strService.findIDKertasByIDMohonKod(idPermohonan, "SFUSL");

        if (kandungan != null) {
            String lt = "x";
            if (mohonFasa != null) {
                lt = mohonFasa.getKeputusan().getKod();
            }
            if (lt.equals("T")) {
                if (mhnKertas == null) {
                    PermohonanKertas permohonanKertas = new PermohonanKertas();
                    permohonanKertas.setInfoAudit(infoAudit);
                    permohonanKertas.setPermohonan(permohonan);
                    permohonanKertas.setKodDokumen(kodDokumenDAO.findById("SFUST"));
                    permohonanKertas.setCawangan(permohonan.getCawangan());
                    permohonanKertas.setTajuk("Butir Penolakan - " + permohonan.getIdPermohonan());
                    strService.simpanPermohonanKertas(permohonanKertas);

                    PermohonanKertasKandungan knd = new PermohonanKertasKandungan();
                    knd.setBil(1);
                    knd.setKertas(permohonanKertas);
                    knd.setKandungan(kandungan);
                    knd.setInfoAudit(infoAudit);
                    knd.setCawangan(permohonan.getCawangan());
                    strService.simpanPermohonanKertasKandungan(knd);

                    addSimpleMessage("Maklumat Berjaya Disimpan");
                }
                if (mhnKertas != null) {
                    PermohonanKertasKandungan knd = strService.findByIdkertasBil(mhnKertas.getIdKertas(), 1);
                    if (knd != null) {
                        knd.setKandungan(kandungan);
                        knd.setInfoAudit(infoAudit2);
                        strService.simpanPermohonanKertasKandungan(knd);
                        addSimpleMessage("Maklumat Berjaya Disimpan");
                    } else {
                        PermohonanKertasKandungan kndBaru = new PermohonanKertasKandungan();
                        kndBaru.setBil(1);
                        kndBaru.setKertas(mhnKertas);
                        kndBaru.setKandungan(kandungan);
                        kndBaru.setInfoAudit(infoAudit2);
                        kndBaru.setCawangan(permohonan.getCawangan());
                        strService.simpanPermohonanKertasKandungan(kndBaru);

                        addSimpleMessage("Maklumat Berjaya Disimpan");
                    }

                }
            }

            if (lt.equals("L")) {
                if (mhnKertasL == null) {
                    PermohonanKertas permohonanKertas = new PermohonanKertas();
                    permohonanKertas.setInfoAudit(infoAudit);
                    permohonanKertas.setPermohonan(permohonan);
                    permohonanKertas.setKodDokumen(kodDokumenDAO.findById("SFUSL"));
                    permohonanKertas.setCawangan(permohonan.getCawangan());
                    permohonanKertas.setTajuk("Butir Kelulusan - " + permohonan.getIdPermohonan());
                    strService.simpanPermohonanKertas(permohonanKertas);

                    PermohonanKertasKandungan knd = new PermohonanKertasKandungan();
                    knd.setBil(1);
                    knd.setKertas(permohonanKertas);
                    knd.setKandungan(kandungan);
                    knd.setInfoAudit(infoAudit);
                    knd.setCawangan(permohonan.getCawangan());
                    strService.simpanPermohonanKertasKandungan(knd);

                    addSimpleMessage("Maklumat Berjaya Disimpan");
                }
                if (mhnKertasL != null) {
                    PermohonanKertasKandungan knd = strService.findByIdkertasBil(mhnKertasL.getIdKertas(), 1);
                    if (knd != null) {
                        knd.setKandungan(kandungan);
                        knd.setInfoAudit(infoAudit2);
                        strService.simpanPermohonanKertasKandungan(knd);
                        addSimpleMessage("Maklumat Berjaya Disimpan");
                    } else {
                        PermohonanKertasKandungan kndBaru = new PermohonanKertasKandungan();
                        kndBaru.setBil(1);
                        kndBaru.setKertas(mhnKertas);
                        kndBaru.setKandungan(kandungan);
                        kndBaru.setInfoAudit(infoAudit2);
                        kndBaru.setCawangan(permohonan.getCawangan());
                        strService.simpanPermohonanKertasKandungan(kndBaru);

                        addSimpleMessage("Maklumat Berjaya Disimpan");
                    }

                }
            }
        }
        return new JSP("strata/pbbm/sebabTolak.jsp").addParameter("tab", "true");
//        return new RedirectResolution(KertasPertimbanganActionBean.class, "sebabTolak").addParameter("error", error).addParameter("message", msg);

    }

    public Resolution simpanSebabTolakPKKR() {
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = permohonanDAO.findById(idPermohonan);

        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        InfoAudit infoAudit = new InfoAudit();
        infoAudit.setDimasukOleh(peng);
        infoAudit.setTarikhMasuk(new java.util.Date());
        InfoAudit infoAudit2 = new InfoAudit();
        infoAudit2.setDiKemaskiniOleh(peng);
        infoAudit2.setTarikhKemaskini(new java.util.Date());
        mohonFasa = strService.findMohonFasa(idPermohonan, "keputusan");

        mohonKertas = strService.findIDKertasByIDMohonKod(idPermohonan, "PKKRT");

        String lt = "x";
        if (mohonFasa != null) {
            lt = mohonFasa.getKeputusan().getKod();
        }
        if (lt.equals("T")) {
            if (kandungan != null) {
                if (mohonKertas == null) {
                    PermohonanKertas permohonanKertas = new PermohonanKertas();
                    permohonanKertas.setInfoAudit(infoAudit);
                    permohonanKertas.setPermohonan(permohonan);
                    permohonanKertas.setKodDokumen(kodDokumenDAO.findById("PKKRT"));
                    permohonanKertas.setCawangan(permohonan.getCawangan());
                    permohonanKertas.setTajuk("Butir Penolakan - " + permohonan.getIdPermohonan());
                    permohonanKertas.setNomborRujukan(nomborRujukan);
                    strService.simpanPermohonanKertas(permohonanKertas);

                    PermohonanKertasKandungan knd = new PermohonanKertasKandungan();
                    knd.setBil(1);
                    knd.setKertas(permohonanKertas);
                    knd.setKandungan(kandungan);
                    knd.setInfoAudit(infoAudit);
                    knd.setCawangan(permohonan.getCawangan());
                    strService.simpanPermohonanKertasKandungan(knd);

                    addSimpleMessage("Maklumat Berjaya Disimpan");
                } else if (mohonKertas != null) {
                    PermohonanKertasKandungan knd = strService.findByIdkertasBil(mohonKertas.getIdKertas(), 1);
                    mohonKertas.setNomborRujukan(nomborRujukan);
                    strService.simpanPermohonanKertas(mohonKertas);
                    if (knd != null) {
                        knd.setKandungan(kandungan);
                        knd.setInfoAudit(infoAudit2);
                        strService.simpanPermohonanKertasKandungan(knd);
                        addSimpleMessage("Maklumat Berjaya Disimpan");
                    } else {
                        PermohonanKertasKandungan kndBaru = new PermohonanKertasKandungan();
                        kndBaru.setBil(1);
                        kndBaru.setKertas(mohonKertas);
                        kndBaru.setKandungan(kandungan);
                        kndBaru.setInfoAudit(infoAudit2);
                        kndBaru.setCawangan(permohonan.getCawangan());
                        strService.simpanPermohonanKertasKandungan(kndBaru);

                        addSimpleMessage("Maklumat Berjaya Disimpan");
                    }

                }
            }
        } else {
            mohonKertas = strService.findIDKertasByIDMohonKod(idPermohonan, "PKKRL");
            if (mohonKertas == null) {
                PermohonanKertas permohonanKertas = new PermohonanKertas();
                permohonanKertas.setInfoAudit(infoAudit);
                permohonanKertas.setPermohonan(permohonan);
                permohonanKertas.setKodDokumen(kodDokumenDAO.findById("PKKRL"));
                permohonanKertas.setCawangan(permohonan.getCawangan());
                permohonanKertas.setTajuk("Butir Kelulusan - " + permohonan.getIdPermohonan());
                permohonanKertas.setNomborRujukan(nomborRujukan);
                strService.simpanPermohonanKertas(permohonanKertas);

                PermohonanKertasKandungan knd = new PermohonanKertasKandungan();
                knd.setBil(1);
                knd.setKertas(permohonanKertas);
                knd.setKandungan(kandungan);
                knd.setInfoAudit(infoAudit);
                knd.setCawangan(permohonan.getCawangan());
                strService.simpanPermohonanKertasKandungan(knd);

                addSimpleMessage("Maklumat Berjaya Disimpan");
            } else if (mohonKertas != null) {
                PermohonanKertasKandungan knd = strService.findByIdkertasBil(mohonKertas.getIdKertas(), 1);
                mohonKertas.setNomborRujukan(nomborRujukan);
                strService.simpanPermohonanKertas(mohonKertas);
                if (knd != null) {
                    knd.setKandungan(kandungan);
                    knd.setInfoAudit(infoAudit2);
                    strService.simpanPermohonanKertasKandungan(knd);
                    addSimpleMessage("Maklumat Berjaya Disimpan");
                } else {
                    PermohonanKertasKandungan kndBaru = new PermohonanKertasKandungan();
                    kndBaru.setBil(1);
                    kndBaru.setKertas(mohonKertas);
                    kndBaru.setKandungan(kandungan);
                    kndBaru.setInfoAudit(infoAudit2);
                    kndBaru.setCawangan(permohonan.getCawangan());
                    strService.simpanPermohonanKertasKandungan(kndBaru);

                    addSimpleMessage("Maklumat Berjaya Disimpan");
                }

            }
        }
        return new JSP("strata/pbbm/sebabTolak.jsp").addParameter("tab", "true");

    }

    public Resolution simpanSebabTolakSUBMC() {
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = permohonanDAO.findById(idPermohonan);

        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        InfoAudit infoAudit = new InfoAudit();
        infoAudit.setDimasukOleh(peng);
        infoAudit.setTarikhMasuk(new java.util.Date());
        InfoAudit infoAudit2 = new InfoAudit();
        infoAudit2.setDiKemaskiniOleh(peng);
        infoAudit2.setTarikhKemaskini(new java.util.Date());
        mohonFasa = strService.findMohonFasa(idPermohonan, "keputusan");

        mohonKertas = strService.findIDKertasByIDMohonKod(idPermohonan, "STLK");

        String lt = "x";
        if (mohonFasa != null) {
            lt = mohonFasa.getKeputusan().getKod();
        }
        if (lt.equals("T")) {
            if (kandungan != null) {
                if (mohonKertas == null) {
                    PermohonanKertas permohonanKertas = new PermohonanKertas();
                    permohonanKertas.setInfoAudit(infoAudit);
                    permohonanKertas.setPermohonan(permohonan);
                    permohonanKertas.setKodDokumen(kodDokumenDAO.findById("STLK"));
                    permohonanKertas.setCawangan(permohonan.getCawangan());
                    permohonanKertas.setTajuk("Butir Penolakan - " + permohonan.getIdPermohonan());
                    permohonanKertas.setNomborRujukan(nomborRujukan);
                    strService.simpanPermohonanKertas(permohonanKertas);

                    PermohonanKertasKandungan knd = new PermohonanKertasKandungan();
                    knd.setBil(1);
                    knd.setKertas(permohonanKertas);
                    knd.setKandungan(kandungan);
                    knd.setInfoAudit(infoAudit);
                    knd.setCawangan(permohonan.getCawangan());
                    strService.simpanPermohonanKertasKandungan(knd);

                    addSimpleMessage("Maklumat Berjaya Disimpan");
                } else if (mohonKertas != null) {
                    PermohonanKertasKandungan knd = strService.findByIdkertasBil(mohonKertas.getIdKertas(), 1);
                    mohonKertas.setNomborRujukan(nomborRujukan);
                    strService.simpanPermohonanKertas(mohonKertas);
                    if (knd != null) {
                        knd.setKandungan(kandungan);
                        knd.setInfoAudit(infoAudit2);
                        strService.simpanPermohonanKertasKandungan(knd);
                        addSimpleMessage("Maklumat Berjaya Disimpan");
                    } else {
                        PermohonanKertasKandungan kndBaru = new PermohonanKertasKandungan();
                        kndBaru.setBil(1);
                        kndBaru.setKertas(mohonKertas);
                        kndBaru.setKandungan(kandungan);
                        kndBaru.setInfoAudit(infoAudit2);
                        kndBaru.setCawangan(permohonan.getCawangan());
                        strService.simpanPermohonanKertasKandungan(kndBaru);

                        addSimpleMessage("Maklumat Berjaya Disimpan");
                    }

                }
            }
        } else {
            mohonKertas = strService.findIDKertasByIDMohonKod(idPermohonan, "SKL");
            if (mohonKertas == null) {
                PermohonanKertas permohonanKertas = new PermohonanKertas();
                permohonanKertas.setInfoAudit(infoAudit);
                permohonanKertas.setPermohonan(permohonan);
                permohonanKertas.setKodDokumen(kodDokumenDAO.findById("SKL"));
                permohonanKertas.setCawangan(permohonan.getCawangan());
                permohonanKertas.setTajuk("Butir Kelulusan - " + permohonan.getIdPermohonan());
                permohonanKertas.setNomborRujukan(nomborRujukan);
                strService.simpanPermohonanKertas(permohonanKertas);

                PermohonanKertasKandungan knd = new PermohonanKertasKandungan();
                knd.setBil(1);
                knd.setKertas(permohonanKertas);
                knd.setKandungan(kandungan);
                knd.setInfoAudit(infoAudit);
                knd.setCawangan(permohonan.getCawangan());
                strService.simpanPermohonanKertasKandungan(knd);

                addSimpleMessage("Maklumat Berjaya Disimpan");
            } else if (mohonKertas != null) {
                PermohonanKertasKandungan knd = strService.findByIdkertasBil(mohonKertas.getIdKertas(), 1);
                mohonKertas.setNomborRujukan(nomborRujukan);
                strService.simpanPermohonanKertas(mohonKertas);
                if (knd != null) {
                    knd.setKandungan(kandungan);
                    knd.setInfoAudit(infoAudit2);
                    strService.simpanPermohonanKertasKandungan(knd);
                    addSimpleMessage("Maklumat Berjaya Disimpan");
                } else {
                    PermohonanKertasKandungan kndBaru = new PermohonanKertasKandungan();
                    kndBaru.setBil(1);
                    kndBaru.setKertas(mohonKertas);
                    kndBaru.setKandungan(kandungan);
                    kndBaru.setInfoAudit(infoAudit2);
                    kndBaru.setCawangan(permohonan.getCawangan());
                    strService.simpanPermohonanKertasKandungan(kndBaru);

                    addSimpleMessage("Maklumat Berjaya Disimpan");
                }

            }
        }
        return new JSP("strata/pbbm/sebabTolak.jsp").addParameter("tab", "true");

    }

    public Resolution simpanSebabTolakPBBS() {
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = permohonanDAO.findById(idPermohonan);

        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        InfoAudit infoAudit = new InfoAudit();
        infoAudit.setDimasukOleh(peng);
        infoAudit.setTarikhMasuk(new java.util.Date());
        InfoAudit infoAudit2 = new InfoAudit();
        infoAudit2.setDiKemaskiniOleh(peng);
        infoAudit2.setTarikhKemaskini(new java.util.Date());
        mohonFasa = strService.findMohonFasa(idPermohonan, "keputusan");

        mohonKertas = strService.findIDKertasByIDMohonKod(idPermohonan, "STBP");
        if (mohonKertas == null) {
            mohonKertas = strService.findIDKertasByIDMohonKod(idPermohonan, "SKL");
        }

        String lt = "x";
        if (mohonFasa != null) {
            lt = mohonFasa.getKeputusan().getKod();
        }
        if (lt.equals("T")) {
            if (kandungan != null) {
                if (mohonKertas == null) {
                    PermohonanKertas permohonanKertas = new PermohonanKertas();
                    permohonanKertas.setInfoAudit(infoAudit);
                    permohonanKertas.setPermohonan(permohonan);
                    permohonanKertas.setKodDokumen(kodDokumenDAO.findById("STBP"));
                    permohonanKertas.setCawangan(permohonan.getCawangan());
                    permohonanKertas.setTajuk("Butir Penolakan - " + permohonan.getIdPermohonan());
                    permohonanKertas.setNomborRujukan(nomborRujukan);
                    strService.simpanPermohonanKertas(permohonanKertas);

                    PermohonanKertasKandungan knd = new PermohonanKertasKandungan();
                    knd.setBil(1);
                    knd.setKertas(permohonanKertas);
                    knd.setKandungan(kandungan);
                    knd.setInfoAudit(infoAudit);
                    knd.setCawangan(permohonan.getCawangan());
                    strService.simpanPermohonanKertasKandungan(knd);

                    addSimpleMessage("Maklumat Berjaya Disimpan");
                } else if (mohonKertas != null) {
                    PermohonanKertasKandungan knd = strService.findByIdkertasBil(mohonKertas.getIdKertas(), 1);
                    mohonKertas.setNomborRujukan(nomborRujukan);
                    strService.simpanPermohonanKertas(mohonKertas);
                    if (knd != null) {
                        knd.setKandungan(kandungan);
                        knd.setInfoAudit(infoAudit2);
                        strService.simpanPermohonanKertasKandungan(knd);
                        addSimpleMessage("Maklumat Berjaya Disimpan");
                    } else {
                        PermohonanKertasKandungan kndBaru = new PermohonanKertasKandungan();
                        kndBaru.setBil(1);
                        kndBaru.setKertas(mohonKertas);
                        kndBaru.setKandungan(kandungan);
                        kndBaru.setInfoAudit(infoAudit2);
                        kndBaru.setCawangan(permohonan.getCawangan());
                        strService.simpanPermohonanKertasKandungan(kndBaru);

                        addSimpleMessage("Maklumat Berjaya Disimpan");
                    }

                }
            }
        } else {
            if (kandungan != null) {
                if (mohonKertas == null) {
                    PermohonanKertas permohonanKertas = new PermohonanKertas();
                    permohonanKertas.setInfoAudit(infoAudit);
                    permohonanKertas.setPermohonan(permohonan);
                    permohonanKertas.setKodDokumen(kodDokumenDAO.findById("SKL"));
                    permohonanKertas.setCawangan(permohonan.getCawangan());
                    permohonanKertas.setTajuk("Butir Kelulusan - " + permohonan.getIdPermohonan());
                    permohonanKertas.setNomborRujukan(nomborRujukan);
                    strService.simpanPermohonanKertas(permohonanKertas);

                    PermohonanKertasKandungan knd = new PermohonanKertasKandungan();
                    knd.setBil(1);
                    knd.setKertas(permohonanKertas);
                    knd.setKandungan(kandungan);
                    knd.setInfoAudit(infoAudit);
                    knd.setCawangan(permohonan.getCawangan());
                    strService.simpanPermohonanKertasKandungan(knd);

                    addSimpleMessage("Maklumat Berjaya Disimpan");
                } else if (mohonKertas != null) {
                    PermohonanKertasKandungan knd = strService.findByIdkertasBil(mohonKertas.getIdKertas(), 1);
                    mohonKertas.setNomborRujukan(nomborRujukan);
                    strService.simpanPermohonanKertas(mohonKertas);
                    if (knd != null) {
                        knd.setKandungan(kandungan);
                        knd.setInfoAudit(infoAudit2);
                        strService.simpanPermohonanKertasKandungan(knd);
                        addSimpleMessage("Maklumat Berjaya Disimpan");
                    } else {
                        PermohonanKertasKandungan kndBaru = new PermohonanKertasKandungan();
                        kndBaru.setBil(1);
                        kndBaru.setKertas(mohonKertas);
                        kndBaru.setKandungan(kandungan);
                        kndBaru.setInfoAudit(infoAudit2);
                        kndBaru.setCawangan(permohonan.getCawangan());
                        strService.simpanPermohonanKertasKandungan(kndBaru);

                        addSimpleMessage("Maklumat Berjaya Disimpan");
                    }

                }
            }
        }
        return new JSP("strata/pbbm/sebabTolak.jsp").addParameter("tab", "true");

    }

    public String getKandungan() {
        return kandungan;
    }

    public void setKandungan(String kandungan) {
        this.kandungan = kandungan;
    }

    public Permohonan getPermohonan() {
        return permohonan;
    }

    public void setPermohonan(Permohonan permohonan) {
        this.permohonan = permohonan;
    }

    public String getIdPermohonan() {
        return idPermohonan;
    }

    public void setIdPermohonan(String idPermohonan) {
        this.idPermohonan = idPermohonan;
    }

    public FasaPermohonan getMohonFasa() {
        return mohonFasa;
    }

    public void setMohonFasa(FasaPermohonan mohonFasa) {
        this.mohonFasa = mohonFasa;
    }

    public String getNomborRujukan() {
        return nomborRujukan;
    }

    public void setNomborRujukan(String nomborRujukan) {
        this.nomborRujukan = nomborRujukan;
    }

    public String getTrhKpsn() {
        return trhKpsn;
    }

    public void setTrhKpsn(String trhKpsn) {
        this.trhKpsn = trhKpsn;
    }

    public PermohonanKertas getMohonKertas() {
        return mohonKertas;
    }

    public void setMohonKertas(PermohonanKertas mohonKertas) {
        this.mohonKertas = mohonKertas;
    }

}
