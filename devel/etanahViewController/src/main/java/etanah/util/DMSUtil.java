package etanah.util;

import etanah.model.Pengguna;
import etanah.model.Permohonan;
import java.io.File;
import java.util.ArrayDeque;
import java.util.Calendar;
import java.util.Date;
import java.util.Deque;
import org.apache.log4j.Logger;


/**
 *
 * @author fikri
 */
public class DMSUtil {
    
    private static final Logger logger = Logger.getLogger(DMSUtil.class);
      
    
    /**
     * Get the DMS for the given Permohonan
     * @param permohonan
     * @return 
     */
    public String getDMSPath(Permohonan permohonan) {
        // FIXME: We should able to @Inject Configuration, but this does not work here!
        String path = System.getProperty("etanah/dms");
        path = buildPath(path, permohonan);
        File dir = new File(path);
        checkAndMkdir(dir);
        return path;
    }
    
    /**
     * Fizikal path without DMS_BASE
     * @param permohonan
     * @return 
     */
    public String getFizikalPath(Permohonan permohonan) {
        return buildPath(null, permohonan);
    }
    
    /**
     * Please use {@link getFizikalPath(Permohonan permohonan)} instead
     * @param pguna
     * @return
     * @deprecated
     */
    @Deprecated
    public String getFizikalPath(Pengguna pguna) {
        String caw = pguna.getKodCawangan().getKod();
        return buildPath(null, caw, new Date(), null);
    }
    
    /**
     * Please use {@link getDMSPath(Permohonan permohonan)} instead
     * @param pguna
     * @return
     * @deprecated
     */
    @Deprecated
    public String getDMSPath(Pengguna pguna) {
        String path = System.getProperty("etanah/dms");
        String caw = pguna.getKodCawangan().getKod();
        path = buildPath(path, caw, new Date(), null);
        File dir = new File(path);
        checkAndMkdir(dir);
        return path;
    }
    
    
    private String buildPath(String base, Permohonan permohonan) {
        String path = null;
        if (permohonan != null) {
            Pengguna pguna = permohonan.getInfoAudit().getDimasukOleh();
            Date trh = permohonan.getInfoAudit().getTarikhMasuk();
            String folder = String.valueOf(permohonan.getFolderDokumen().getFolderId());            
            String caw = pguna.getKodCawangan().getKod();
            path = buildPath(base, caw, trh, folder);
        }
        else {
            logger.error("Permohonan is NULL!");
            path = buildPath(base, null, null, null);
        }
        return path;
    }
    
    /**
     * Build the path - base/caw/tahun/bulan/hari/folder
     * @param base DMS_BASE, can be null
     * @param caw if null, then just return DMS_BASE, if both base and caw null return empty string
     * @param trh
     * @param folder
     * @return 
     */
    private String buildPath(String base, String caw, Date trh, String folder) {
        StringBuilder sb = new StringBuilder(base == null ? "" : base);
        if (caw == null) return sb.toString();
        Calendar cal = Calendar.getInstance();
        cal.setTime(trh);
        if (base != null && !base.endsWith(File.separator)) {
            sb.append(File.separator);
        }
        sb.append(caw)
           .append(File.separator)
           .append(cal.get(Calendar.YEAR))
           .append(File.separator)
           .append(DateUtil.getDateName(String.valueOf(cal.get(Calendar.MONTH) +1)))
           .append(File.separator)
           .append(cal.get(Calendar.DAY_OF_MONTH));
        if (folder != null) {
            sb.append(File.separator)
              .append(folder); 
        }
//        if (logger.isDebugEnabled())
//            logger.debug("Generated path: " + sb.toString());
        return sb.toString();
    }
    
    
    /**
     * Create the directory structure, one-by-one, and set the permission correctly
     * FIXME: this is just a workaround for NFS permission problems!
     * @param dir 
     */
    private void checkAndMkdir(File dir) {
        if (dir != null && !dir.exists()) {
            Deque<File> makeDir = new ArrayDeque<File>();
            makeDir.push(dir);
            File last = dir.getParentFile();
            while (true) {
                if (!last.exists()) {
                    makeDir.push(last);
                    last = last.getParentFile();
                } else {
                    break;
                }
            }
            
            // create dirs
            File d = null;
            while (!makeDir.isEmpty()) {
                d = makeDir.pop();
                logger.debug("Creating dir - " + d.getAbsolutePath());
                d.mkdir();
                d.setExecutable(true, false);
                d.setReadable(true, false);
            }
        }
    }
}
