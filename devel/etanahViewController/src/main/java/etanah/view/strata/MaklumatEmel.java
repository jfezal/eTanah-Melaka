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
import etanah.view.uam.MailService;
import etanah.view.uam.MailService2;
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
@UrlBinding("/strata/emel")
public class MaklumatEmel extends AbleActionBean {

    @Inject
    HakmilikPermohonanDAO hakmilikPermohonanDAO;
    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    KodAgensiDAO kodAgensiDAO;
    @Inject
    StrataPtService strService;
    private String idHakmilik;
    private String kodAgensiSTR;
    private String idPermohonan;
    private Pengguna pguna;
    private HakmilikPermohonan mohonHakmilik;
    private Permohonan permohonan;
    private KodAgensi kodAgensi;
    private List<KodAgensi> agensi = new ArrayList<KodAgensi>();
    @Inject
    private etanah.Configuration conf;
    private static final Logger LOG = Logger.getLogger(MaklumatEmel.class);

    @DefaultHandler
    public Resolution showForm1() {
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = permohonanDAO.findById(idPermohonan);
        agensi = strService.getKodAgensi("STR", null);

        return new JSP("strata/pbbm/strataEmel.jsp").addParameter("tab", "true");
    }

    public Resolution hantarEmel() {
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");

        pguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        permohonan = permohonanDAO.findById(idPermohonan);
        List<KodAgensi> agensi = strService.getKodAgensi("STR", "Y");
        FasaPermohonan mf = strService.findFasaPermohonanByIdAliran(idPermohonan, "keputusan");
        try {
            String keputusan = "";
            if (mf != null) {
                keputusan = mf.getKeputusan().getKod();
            }
            String kodNegeri = conf.getKodNegeri();
//            String[] to = new String[agensi.size()];
            MailService2 mail = new MailService2();
//            String[] email = new String[agensi.size()];
            int a = 0;
            for (KodAgensi pp : agensi) {
                String[] to = new String[1];
                to[0] = pp.getEmel();
                a++;

                String kpsn = null;
                String negeri = null;
                if (keputusan != null) {
                    if (keputusan.equals("L")) {
                        kpsn = "diluluskan";
                    } else {
                        kpsn = "ditolak";
                    }
                }

                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                
                if (kodNegeri.equals("04")) {
                    negeri = "Melaka";
                } else {
                    negeri = "Negeri Sembilan";
                }
                String bpm = "";
                if (permohonan.getSenaraiHakmilik().get(0).getHakmilik().getBandarPekanMukim() != null) {
                    bpm = permohonan.getSenaraiHakmilik().get(0).getHakmilik().getBandarPekanMukim().getNama();
                }
                if (permohonan.getSenaraiHakmilik().get(0).getHakmilik().getSeksyen() != null) {
                    bpm = bpm + permohonan.getSenaraiHakmilik().get(0).getHakmilik().getSeksyen().getNama();
                }
                List<PermohonanBangunan> mb = strService.checkMhnBangunanExist(idPermohonan);
                int petakTanah = 0;
                int petak = 0;
                int petakAksr = 0;
                int blokSementaraB = 0;
                int blokSementaraL = 0;

                for (PermohonanBangunan mb1 : mb) {
                    if (mb1.getKodKategoriBangunan().getKod().equals("L")) {
                        petakTanah += mb1.getBilanganPetak();
                    }
                    if (mb1.getKodKategoriBangunan().getKod().equals("P")) {
                        blokSementaraB += mb1.getBilanganPetak();
                    }
                    if (mb1.getKodKategoriBangunan().getKod().equals("PL")) {
                        blokSementaraL += mb1.getBilanganPetak();
                    }
                    if (mb1.getKodKategoriBangunan().getKod().equals("B")) {
                        petak += mb1.getBilanganPetak();
                    }
                    petakAksr = strService.CountPetakAksr(String.valueOf(mb1.getIdBangunan()));
                }

                String subject = "Notifikasi Keputusan PTG (Strata)";
                String msg = permohonan.getKodUrusan().getNama()
                        + " untuk ID permohonan " + permohonan.getIdPermohonan() + " telah "
                        + kpsn + ". Maklumat permohonan adalah seperti berikut :" + "  \n"+ "  \n"
                        + "No. Rujukan                  : " + permohonan.getIdPermohonan() + "  \n"
                        + "JTB                          : " + permohonan.getPenyerahNama() + "  \n"
                        + "Negeri                       : " + negeri + "  \n"
                        + "Daerah                       : " + permohonan.getSenaraiHakmilik().get(0).getHakmilik().getDaerah().getNama() + " \n"
                        + "Mukim                        : " + bpm + "  \n"
                        + "Bil. Petak Tanah             : " + petakTanah + "  \n"
                        + "Bil. Petak                   : " + petak + "  \n"
                        + "Bil. Petak Aksesori          : " + petakAksr + "  \n"
                        + "Bil. Blok Sementara Bangunan : " + blokSementaraB + "  \n"
                        + "Bil. Blok Sementara Tanah    : " + blokSementaraL + "  \n"
                        + "Tarikh Notis                 : " + sdf.format(new Date()) + "  \n"
                        + "  \n"+ "  \n"+ "  \n"
                        + "Ini adalah janaan komputer untuk makluman tuan/puan sahaja. Sila jangan balas ke emel ini.";

                mail.sendEmailMany(to, subject, msg, kodNegeri);

                LOG.info(to);
            }

//            mail.sendEmailHTML(email, subject, mail.HTMLJtek(permohonan), kodNegeri);
            try {
                addSimpleMessage("Makluman secara emel telah berjaya dihantar ");
            } catch (Exception e) {
                addSimpleError("Makluman secara emel TIDAK berjaya dihantar ");
                LOG.error(e.getMessage());
                return null;
            }

        } catch (Exception e) {
            LOG.error(e.getMessage());
            addSimpleError("Makluman secara emel TIDAK berjaya dihantar ");
            return null;
        }

        return showForm1();
    }

    public Resolution popupKogAgensi() {
        String kod = (String) getContext().getRequest().getParameter("kod");
        List<KodAgensi> agen = strService.getKodAgensibyKod(kod);
        for (KodAgensi agensi1 : agen) {
            kodAgensi = agensi1;
        }
        return new JSP("strata/pbbm/strataEmelPopup.jsp").addParameter("popup", "true");
    }

    public Resolution savepopupKogAgensi() {
        if (kodAgensi != null) {
            List<KodAgensi> agen = strService.getKodAgensibyKod(kodAgensi.getKod());
            for (KodAgensi agensi1 : agen) {
                agensi1.setAktif(kodAgensi.getAktif());
                agensi1.setEmel(kodAgensi.getEmel());
                agensi1.setNama(kodAgensi.getNama());
                strService.saveKodAgensi(agensi1);
                addSimpleMessage("Maklumat Berjaya Disimpan");
            }
        } else {
            addSimpleError("Maklumat Tidak Berjaya Disimpan");
        }

        return new JSP("strata/pbbm/strataEmelPopup.jsp").addParameter("popup", "true");
    }

    public Resolution addAgensi() {
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        getContext().getRequest().setAttribute("add", Boolean.TRUE);
        kodAgensi = new KodAgensi();
        return new JSP("strata/pbbm/strataEmel.jsp").addParameter("tab", "true");
    }

    public Resolution saveAgensi() {
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        getContext().getRequest().setAttribute("add", Boolean.FALSE);
        if (kodAgensi != null) {
            List<KodAgensi> agen = strService.getKodAgensi(kodAgensi.getKod(), null);
            KodKategoriAgensi katg = strService.getKodKategoriAgensi("STR");
            if (agen.size() <= 0) {
                KodAgensi ka = new KodAgensi();
                ka.setKod(kodAgensi.getKod());
                ka.setNama(kodAgensi.getNama());
                ka.setEmel(kodAgensi.getEmel());
                ka.setAktif('Y');
                ka.setKategoriAgensi(katg);
                strService.saveKodAgensi(ka);
                addSimpleMessage("Maklumat Berjaya Disimpan");
            } else {
                addSimpleError("Maaf. Kod ini telah digunakan. Sila masukkan kod agensi yang lain.");
            }
        }

        return showForm1();
    }

    public Resolution delAgensi() {
        kodAgensiSTR = (String) getContext().getRequest().getParameter("kodAgensiSTR");
        List<KodAgensi> agen = strService.getKodAgensibyKod(kodAgensiSTR);
        getContext().getRequest().setAttribute("add", Boolean.FALSE);

        for (KodAgensi ka : agen) {
            strService.delKodAgensi(ka);
            addSimpleMessage("Maklumat Berjaya Dihapuskan");
        }
        showForm1();
        return new JSP("strata/pbbm/strataEmel.jsp").addParameter("tab", "true");
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

    public String getIdPermohonan() {
        return idPermohonan;
    }

    public void setIdPermohonan(String idPermohonan) {
        this.idPermohonan = idPermohonan;
    }

    public Pengguna getPguna() {
        return pguna;
    }

    public void setPguna(Pengguna pguna) {
        this.pguna = pguna;
    }

    public List<KodAgensi> getAgensi() {
        return agensi;
    }

    public void setAgensi(List<KodAgensi> agensi) {
        this.agensi = agensi;
    }

    public KodAgensi getKodAgensi() {
        return kodAgensi;
    }

    public void setKodAgensi(KodAgensi kodAgensi) {
        this.kodAgensi = kodAgensi;
    }

    public Permohonan getPermohonan() {
        return permohonan;
    }

    public void setPermohonan(Permohonan permohonan) {
        this.permohonan = permohonan;
    }

    public String getKodAgensiSTR() {
        return kodAgensiSTR;
    }

    public void setKodAgensiSTR(String kodAgensiSTR) {
        this.kodAgensiSTR = kodAgensiSTR;
    }

}
