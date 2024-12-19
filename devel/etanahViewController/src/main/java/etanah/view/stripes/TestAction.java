/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.stripes;

import able.guice.AbleContextListener;
import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.Configuration;
import etanah.dao.DokumenDAO;
import etanah.dao.HakmilikDAO;
import etanah.model.Dokumen;
import etanah.model.Hakmilik;
import etanah.model.Pengguna;

import etanah.view.etanahActionBeanContext;
import etanah.view.etapp.ws.EtappLogService;
import etanah.view.utility.ETappUtil;
import etanah.workflow.WorkFlowService;
import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.StreamingResolution;
import net.sourceforge.stripes.action.UrlBinding;
import oracle.bpel.services.workflow.StaleObjectException;
import oracle.bpel.services.workflow.WorkflowException;
import oracle.bpel.services.workflow.verification.IWorkflowContext;
import org.apache.commons.lang.StringUtils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author fikri
 */
@UrlBinding("/test_etapp")
public class TestAction extends AbleActionBean {

    @Inject
    Configuration conf;

    @Inject
    private DokumenDAO dokumenDAO;

    @Inject
    private HakmilikDAO hakmilikDAO;
      @Inject
    EtappLogService logservice;

    public String idPermohonan;

    public String branch;

    public String noFail;

    public String keterangan;

    public String borang;

    public String idDokumen;

    public String idHakmilik;

    private static final Logger LOG = LoggerFactory.getLogger(TestAction.class);

    public Resolution updateBranch() {
        try {

            etanahActionBeanContext ctx = (etanahActionBeanContext) getContext();
            Pengguna pengguna = ctx.getUser();

            IWorkflowContext wc = WorkFlowService.authenticate(pengguna.getIdPengguna());

            WorkFlowService.updateTaskBranch(idPermohonan, branch, wc);

        } catch (StaleObjectException ex) {
            System.out.println("ex" + ex.toString());
        } catch (WorkflowException ex) {
            System.out.println("ex" + ex.toString());
        }

        return new StreamingResolution("text/plain", "1");
    }

    public Resolution testEtappConnection() {

        LOG.info("keterangan {}", keterangan);
        LOG.info("noFail {}", noFail);

        String result = "";

        ETappUtil util = AbleContextListener.newInstance(ETappUtil.class);

        result = util.updateKeputusanMMK(borang, "Y", keterangan, noFail, new Date());

        return new StreamingResolution("text/plain", result.equals("201") ? "SUCCESS" : "NOT SUCCESS");
    }

    @DefaultHandler
    public Resolution showFormTestEtapp() {
        return new JSP("utiliti/test_etapp.jsp");
    }

    public Resolution testEtapWithAttachment() {
        ETappUtil util = AbleContextListener.newInstance(ETappUtil.class);
        String result = "";
        StringBuilder sb = new StringBuilder();
        result = util.updateKeputusanMMK(borang, "Y", keterangan, noFail, new Date());
        sb.append("update keputusan MMK :")
                .append(result.equals("201") ? "SUCCESS" : "NOT SUCCESS")
                .append("\n");

        String docPath = conf.getProperty("document.path");

        if (StringUtils.isNotBlank(idDokumen)) {
            List<Map<String, String>> listOfDocuments = new ArrayList<Map<String, String>>();
            Dokumen d = dokumenDAO.findById(Long.valueOf(idDokumen));
            if (d != null) {
                String fn = d.getNamaFizikal();
                File f = new File(docPath + (docPath.endsWith(File.separator) ? "" : File.separator) + fn);
                if (f != null) {
                    Map<String, String> map = new HashMap<String, String>();
                    map.put("path", f.getAbsolutePath());
                    map.put("borang", borang);
                    map.put("fileName", f.getName());
                    map.put("contentType", d.getFormat());
                    map.put("title", d.getTajuk());
                    map.put("length", String.valueOf(f.length()));
                    map.put("failNo", noFail);
                    listOfDocuments.add(map);
                }

            }
            result = util.dokumenToMyEtapp(listOfDocuments);
            sb.append("dokumen to etapp :")
                    .append(result.equals("201") ? "SUCCESS" : "NOT SUCCESS");
        }

        return new StreamingResolution("text/plain", sb.toString());
    }

    public Resolution testHakmilikFed() {

        String result = "";
        StringBuilder sb = new StringBuilder();

        if (StringUtils.isBlank(idHakmilik)) {
            return new StreamingResolution("text/plain", "masukan id hakmilik");
        } else {

            Hakmilik hm = hakmilikDAO.findById(idHakmilik);
            if (hm == null) {
                addSimpleError("Hakmilik tidak dijumpai!!");
                return new JSP("utiliti/test_etapp.jsp");
            }

            ETappUtil util = AbleContextListener.newInstance(ETappUtil.class);
            result = util.insertHakmilikFederal(hm);
            if (result.equals("201")) {
                logservice.insertLog("HTP", "1", "Hakmilik Federal ="+idHakmilik);
            } else {
                
                logservice.insertLog("HTP", "2", "Hakmilik Federal ="+idHakmilik);
            }
            sb.append("testHakmilikFed :")
                    .append(result.equals("201") ? "SUCCESS" : "NOT SUCCESS");

        }

        return new StreamingResolution("text/plain", sb.toString());
    }
}
