/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.stripes.lelong;

import com.google.inject.Inject;
import com.wideplay.warp.persist.Transactional;
import etanah.dao.JuruLelongDAO;
import etanah.dao.MenuCapaianDAO;
import etanah.dao.SytJuruLelongDAO;
import etanah.model.JuruLelong;
import etanah.model.MenuCapaian;
import etanah.model.SytJuruLelong;

/**
 *
 * @author mdizzat.mashrom
 */
public class DaftarJurulelongManager {

    @Inject
    JuruLelongDAO jurulelongDAO;
    @Inject
    SytJuruLelongDAO sytJuruLelongDAO;
    @Inject
    MenuCapaianDAO menuCapaianDAO;

    @Transactional
    public void saveOrUpdate(JuruLelong j) {
        jurulelongDAO.saveOrUpdate(j);
    }

    @Transactional
    public void saveOrUpdate(SytJuruLelong j) {
        sytJuruLelongDAO.saveOrUpdate(j);
    }
    
    @Transactional
    public void saveOrUpdate(MenuCapaian j) {
        menuCapaianDAO.saveOrUpdate(j);
    }


}
