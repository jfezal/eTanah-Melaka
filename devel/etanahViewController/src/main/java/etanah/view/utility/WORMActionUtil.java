/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package etanah.view.utility;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.dao.DokumenDAO;
import etanah.dao.HakmilikDAO;
import etanah.model.Dokumen;
import etanah.model.Hakmilik;
import etanah.util.FileUtil;
import etanah.util.WORMUtil;
import etanah.view.etanahContextListener;
import java.io.File;
import java.io.IOException;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

/**
 *
 * @author fikri
 */
@UrlBinding("/utility/worm")
public class WORMActionUtil extends AbleActionBean{
    
    private static Logger LOG = Logger.getLogger(WORMActionUtil.class);
    
    private String idHakmilik;
    
    private Dokumen document;
    
    private Hakmilik hakmilik;
    
    @Inject
    private DokumenDAO dokumenDAO;
    
    @Inject
    private HakmilikDAO hakmilikDAO;
    
    @Inject
    private etanah.Configuration conf;
    
    @DefaultHandler
    public Resolution showForm() {
        return new JSP("utiliti/worm_util.jsp");
    }
    
    public Resolution searchHakmilik() {
        
        if (StringUtils.isNotBlank(idHakmilik)) {
            hakmilik = hakmilikDAO.findById(idHakmilik);
            if (hakmilik == null) {
                addSimpleError("Hakmilik tidak dijumpai.");
            } else {
                if (hakmilik.getDhke() == null || StringUtils.isEmpty(hakmilik.getDhke().getNamaFizikal())) {
                    addSimpleError("Geran tiada untuk hakmilik " + hakmilik.getIdHakmilik()+ ". Sila pilih hakmilik lain.");
                    hakmilik = null;
                }
            }
        }
        
        return new JSP("utiliti/worm_util.jsp");
    }
    
    public Resolution testWorm() {
        
        if (hakmilik != null && StringUtils.isNotBlank(hakmilik.getIdHakmilik())) {
            hakmilik = hakmilikDAO.findById(hakmilik.getIdHakmilik());
            
            FileUtil fileUtil = new FileUtil(conf.getDocumentPath());
            File file = fileUtil.getFile(hakmilik.getDhke().getNamaFizikal());
            if (file != null) {
                WORMUtil util = etanahContextListener.newInstance(WORMUtil.class);
                try {
                    int status = util.put(file, hakmilik.getIdHakmilik(), 
                        hakmilik.getDaerah().getKod(), hakmilik.getBandarPekanMukim().getbandarPekanMukim(), 
                        String.valueOf(hakmilik.getSeksyen().getKod()), 
                        hakmilik.getKodHakmilik().getKod(), 
                        String.valueOf(hakmilik.getNoVersiDhke()), 
                        hakmilik.getKodStatusHakmilik().getKod());
                    
                    addSimpleMessage(String.valueOf(status));
                    
                } catch (IOException ex) {
                    addSimpleError(ex.getMessage());
                    LOG.error(ex);
                }
            } else {
                addSimpleError("Geran tidak dijumpai dalam DMS!!");
            }
        }
        
        return new JSP("utiliti/worm_util.jsp");
    }

    public Dokumen getDocument() {
        return document;
    }

    public void setDocument(Dokumen document) {
        this.document = document;
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
    
}
