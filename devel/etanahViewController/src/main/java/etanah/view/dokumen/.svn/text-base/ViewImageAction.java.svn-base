/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package etanah.view.dokumen;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.dao.PermohonanDAO;
import etanah.model.Dokumen;
import etanah.model.FolderDokumen;
import etanah.model.KandunganFolder;
import etanah.model.Permohonan;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.SequenceInputStream;
import java.util.ArrayList;
import java.util.List;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ErrorResolution;
import net.sourceforge.stripes.action.HttpCache;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import org.apache.commons.lang.StringUtils;

/**
 *
 * @author fikri
 */

@HttpCache(allow = true)
@UrlBinding("/dokumen/view/image/{idPermohonan}")
public class ViewImageAction extends AbleActionBean{

    private String idPermohonan;

    @Inject PermohonanDAO permohonanDAO;

    @Inject
    private etanah.Configuration conf;

    private List<KandunganFolder> senaraiKandungan;

    public String getIdPermohonan() {
        return idPermohonan;
    }

    public void setIdPermohonan(String idPermohonan) {
        this.idPermohonan = idPermohonan;
    }

    public List<KandunganFolder> getSenaraiKandungan() {
        return senaraiKandungan;
    }

    public void setSenaraiKandungan(List<KandunganFolder> senaraiKandungan) {
        this.senaraiKandungan = senaraiKandungan;
    }  

    
    @DefaultHandler
    public Resolution view() {
        if (StringUtils.isBlank(idPermohonan)) return new ErrorResolution(500, "Permohonan tidak dinyatakan");

        Permohonan permohonan = permohonanDAO.findById(idPermohonan);

        if(permohonan == null) return new ErrorResolution(500, "Permohonan tidak dijumpai");

        FolderDokumen fd = permohonan.getFolderDokumen();        
        
        if (fd == null) {
            return new ErrorResolution(500, "Folder tidak dijumpai");
        }

        String docPath = conf.getProperty("document.path");
        if (docPath == null) {
            return new ErrorResolution(500,
                    "Konfigurasi \"document.path\" tidak dijumpai");
        }

        senaraiKandungan = new ArrayList<KandunganFolder>();

        List<KandunganFolder> senarai = fd.getSenaraiKandungan();

        for (KandunganFolder kandunganFolder : senarai) {
            if (kandunganFolder == null || kandunganFolder.getDokumen() == null) continue;
            Dokumen d = kandunganFolder.getDokumen();
            if (d.getFormat() != null && d.getFormat().startsWith("image")) {
                senaraiKandungan.add(kandunganFolder);
            }
        }
        
        return new JSP("dokumen/view_image.jsp").addParameter("popup", "true");
    }   

    private InputStream openAll(List<File> files) throws Exception{

       InputStream is= null;

       if (files == null || files.size() == 0)
          return is; // nothing to read

       is = new FileInputStream(files.get(0));
       for (int i= 1; i < files.size(); i++)
          is = new SequenceInputStream(is, new FileInputStream(files.get(i)) );

       BufferedReader br = new BufferedReader(new InputStreamReader(is));

       return is;
    }
}
