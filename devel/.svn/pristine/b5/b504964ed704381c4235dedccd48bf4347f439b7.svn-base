/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package etanah.service;

import com.google.inject.Inject;
import com.wideplay.warp.persist.Transactional;
import etanah.dao.LogPenggunaApplikasiDAO;
import etanah.model.LogPenggunaApplikasi;

/**
 *
 * @author fikri
 */
public class PageAuditService {

    @Inject
    LogPenggunaApplikasiDAO logPenggunaApplikasiDAO;


    @Transactional
    public void save(LogPenggunaApplikasi log) {
        logPenggunaApplikasiDAO.save(log);
    }
}
