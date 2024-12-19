/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.util;

import com.google.inject.Inject;
import etanah.dao.DokumenDAO;
import etanah.dao.HakmilikDAO;
import etanah.model.Dokumen;
import etanah.model.Hakmilik;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

/**
 *
 * @author khairil
 */
public class CompareTitle {

    @Inject
    etanah.Configuration conf;
    @Inject
    DokumenDAO dokumenDAO;
    @Inject
    HakmilikDAO hakmilikDAO;
    String idHakmilik;
    Long idDokumen;
    private static final Logger LOGGER = Logger.getLogger(CompareTitle.class);

    public DokumenDAO getDokumenDAO() {
        return dokumenDAO;
    }

    public void setDokumenDAO(DokumenDAO dokumenDAO) {
        this.dokumenDAO = dokumenDAO;
    }

    public HakmilikDAO getHakmilikDAO() {
        return hakmilikDAO;
    }

    public void setHakmilikDAO(HakmilikDAO hakmilikDAO) {
        this.hakmilikDAO = hakmilikDAO;
    }

    public String getIdHakmilik() {
        return idHakmilik;
    }

    public void setIdHakmilik(String idHakmilik) {
        this.idHakmilik = idHakmilik;
    }

    public Long getIdDokumen() {
        return idDokumen;
    }

    public void setIdDokumen(Long idDokumen) {
        this.idDokumen = idDokumen;
    }

//    public static void main(String[] param) {
//
//        String pdf1 = "/home/khairil/etanah/report/00/2011/JANUARI/19/43670";
//        String pdf2 = "/home/khairil/etanah/report/00/2011/JANUARI/19/43671";
//        convertPDFToText(pdf1);
//        convertPDFToText(pdf2);
//        String text1 = pdf1 + ".txt";
//        String text2 = pdf2 + ".txt";
//        System.out.println(compareText(text1, text2));
//
//    }
    public String convertAndCompare(String idHakmilik) {

        String result = "";
        if (StringUtils.isNotBlank(idHakmilik)) {
            LOGGER.debug("CompareTitle idHakmilik :" + idHakmilik);
            Hakmilik h = hakmilikDAO.findById(idHakmilik);
            if (h != null) {
                if (h.getDhke().getIdDokumen() != 0) {
                    LOGGER.debug("idDokumen :" + h.getDhke().getIdDokumen());
                    Dokumen d = dokumenDAO.findById(h.getDhke().getIdDokumen());
                    if (d != null) {
                        String sptbPath = conf.getProperty("document.sptb.path");
                        String etanahPath = conf.getProperty("document.path");

                        LOGGER.debug("sptbPath:" + sptbPath);
                        LOGGER.debug("etanahPath:" + etanahPath);
                        String pdfSptbPath = sptbPath + (sptbPath.endsWith(File.separator) ? "" : File.separator) + idHakmilik;
                        String pdfEtanahPath = etanahPath + (etanahPath.endsWith(File.separator) ? "" : File.separator) + d.getNamaFizikal();
                        LOGGER.debug("path sptb :" + pdfSptbPath);
                        LOGGER.debug("path etanah :" + pdfEtanahPath);

                        convertPDFToText(pdfSptbPath);
                        convertPDFToText(pdfEtanahPath);
                        String text1 = pdfSptbPath + ".txt";
                        String text2 = pdfEtanahPath + ".txt";
                        result = compareText(text1, text2);
                        LOGGER.debug("result : " + result);
                    }
                }
            }
        }

        return result;
    }

    public String convertPDFToText(String pdf) {
        String result = "";
        try {
            Process p = Runtime.getRuntime().exec("pdftotext -nopgbrk " + pdf);
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(p.getInputStream()));
            String line = null;
            while ((line = in.readLine()) != null) {
                System.out.println(line);
            }
            result = "convert successful";
        } catch (IOException e) {
            result = "convert failed";
            e.printStackTrace();
        }

        return result;
    }

    public String compareText(String text1, String text2) {
        String result = "";
        String truePercent = "";
        List<String> percent = new ArrayList(3);
        int counter = 0;
        try {
            for (int i = 1; i < 3; i++) {

                String shell = "wdiff -i -s " + text1 + " " + text2 + " | tail -n" + i + "| head -n1 | cut -d' ' -f6";
                System.out.println("running shell script:" + shell);
                //Process p = Runtime.getRuntime().exec(shell);

                String[] cmd = {"/bin/sh", "-c", shell};
                Process p = Runtime.getRuntime().exec(cmd);
                BufferedReader in = new BufferedReader(
                        new InputStreamReader(p.getInputStream()));
                List<String> lines = new ArrayList<String>();
                String line = null;
                while ((line = in.readLine()) != null) {
                    lines.add(line);
//                    System.out.println(line);
                }
                lines.toArray(new String[lines.size()]);
                for (String string : lines) {
                    //percent.add(string);
                    if (!string.equals("100%")) {
                        truePercent = string;
                    }
                    if (string.equals("100%")) {
                        counter = counter + 1;
                    }
                }

                LOGGER.debug("counter 100% : "+counter);
                if (counter > 1) {
                    truePercent = "100%";
                }


            }
            result = truePercent;

        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }
}
