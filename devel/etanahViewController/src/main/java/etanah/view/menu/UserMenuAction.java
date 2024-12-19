package etanah.view.menu;

import java.io.*;

import javax.servlet.*;
import javax.servlet.http.*;

import org.apache.log4j.Logger;

import com.google.inject.Inject;
import com.tangosol.net.CacheFactory;
import com.tangosol.net.NamedCache;

import etanah.model.Pengguna;
import etanah.service.MenuService;
import etanah.view.etanahActionBeanContext;

import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.HttpCache;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.StreamingResolution;
import net.sourceforge.stripes.action.UrlBinding;

import able.stripes.AbleActionBean;

/**
 * 
 * @author hishammk (private copyright)
 * 
 *         minin - added coherence caching (Oct 5, 2010)
 * 
 */

@HttpCache(allow = true)
@UrlBinding("/menu/{idPengguna}")
public class UserMenuAction extends AbleActionBean {

    private char refresh = 'N';

    private String idPengguna;

    @Inject
    private MenuService menuService;

    private final static String TMP_DIR = System.getProperty("java.io.tmpdir");

    private final static String MENU_PREFIX = "__menu";

    private static final Logger LOG = Logger.getLogger(MenuService.class);
    private static final boolean debug = LOG.isDebugEnabled();
    private static boolean coherenceCache = false;
    private static final String CACHE_KEY = "dist_menu_" + System.getProperty("etanah/kodNegeri");

    private NamedCache cache = null;

    static {
        LOG.info("TEMP FOLDER:" + TMP_DIR);
        if ("true".equals(System.getProperty("etanah/coherence")))
            coherenceCache = true;
    }

    public UserMenuAction() {
        // requires dedicated coherence server
        System.setProperty("tangosol.coherence.distributed.localstorage", "false");
        // dist_menu_* configured with maximum of 5000 items, expires after 32min
        if (coherenceCache)
            cache = CacheFactory.getCache(CACHE_KEY);
    }

    public void setRefresh(char refresh) {
        this.refresh = refresh;
    }

    public char getRefresh() {
        return refresh;
    }

    /**
     * Note that it's useless to set Id Pengguna because we are still going to
     * get from session
     * 
     * @param id
     */
    public void setIdPengguna(String id) {
        this.idPengguna = id;
    }

    public String getIdPengguna() {
        return idPengguna;
    }

    @DefaultHandler
    public Resolution renderUserMenu() {
        etanahActionBeanContext ctx = (etanahActionBeanContext) getContext();

        Pengguna p = ctx.getUser();
        if (p == null) {
            return new StreamingResolution("text/javascript", "<script>var auth = false;</script>");
        }

        String key = ctx.getRequest().getSession().getId();
        String user = ctx.getUser().getIdPengguna();
        String path = ctx.getRequest().getContextPath();
        // String menu = (String) cache.get(key);
        Object menu = null;

        if (coherenceCache)
            menu = getMenu(key, user, path);

        // check again
        if (!coherenceCache) {
            menu = getMenuFromDisk(key, user, path);
            return new StreamingResolution("text/javascript", (Reader) menu);
        }
        // return from coherence cache
        return new StreamingResolution("text/javascript", (String) menu);
    }
    
    /*
     * public long getLastModified(HttpServletRequest arg0) { return (new
     * java.util.Date()).getTime(); }
     */

    /**
     * Get menu from coherence cache
     * 
     * @param key
     * @param user
     * @param path
     * @return
     */
    private String getMenu(String key, String user, String path) {
        String menu = null;
        try {
            menu = (String) cache.get(key);
            if (menu == null) {
                menu = menuService.renderUserMenu(path, user);
                cache.put(key, menu);
            }
        } catch (com.tangosol.net.RequestPolicyException e) {
            LOG.warn("Coherence not available. Fallback to caching in filesystem!\n"
                    + "Please restart the coherence server and weblogic to re-enables caching.");
            coherenceCache = false;
        }
        return menu;
    }

    /**
     * Get menu from local disk cache
     * 
     * @param key
     * @param user
     * @param path
     * @return
     */
    private Reader getMenuFromDisk(String key, String user, String path) {
        File f = new File(getUserMenuPath(key));

        // check if already generated
        // TODO: to clear if user logout
        Reader in = getMenuFromDisk(f);
        if (in != null)
            return in;

        String menu = menuService.renderUserMenu(path, user);
        // save in filesystem
        try {
            FileWriter fw = new FileWriter(f);
            fw.write(menu);
            fw.close();
            in = getMenuFromDisk(f);
        } catch (IOException e) {
            LOG.error(e.getMessage(), e);
        }
        return in;
    }

    private Reader getMenuFromDisk(File f) {
        if (refresh != 'Y') {
            if (f.exists()) {
                FileReader fr;
                try {
                    fr = new FileReader(f);
                    return fr;
                } catch (FileNotFoundException e) {
                    LOG.error(e.getMessage(), e);
                }
            }
        }
        return null;
    }
    
    private static final String getUserMenuPath(String key){
        return TMP_DIR + File.separator + MENU_PREFIX + key;
    }

}
