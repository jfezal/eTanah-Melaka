/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package etanah.view.stripes;

import com.google.inject.Inject;
import com.wideplay.warp.persist.Transactional;
import etanah.dao.AkaunDAO;
import etanah.dao.CaraBayaranDAO;
import etanah.dao.DokumenDAO;
import etanah.dao.DokumenKewanganDAO;
import etanah.dao.TransaksiDAO;
import etanah.model.Akaun;
import etanah.model.CaraBayaran;
import etanah.model.Dokumen;
import etanah.model.DokumenKewangan;
import etanah.model.Transaksi;

/**
 *
 * @author abdul.hakim
 */
public class KutipanHasilManager {

    @Inject
    DokumenKewanganDAO dokumenKewanganDAO;

    @Inject
    CaraBayaranDAO caraBayaranDAO;

    @Inject
    TransaksiDAO transakasiDAO;

    @Inject
    AkaunDAO akaunDAO;

    @Inject
    DokumenDAO dokumenDAO;

    @Transactional
    public void saveOrUpdate (DokumenKewangan dokumenKewangan){
        
        dokumenKewanganDAO.save(dokumenKewangan);
    }

    @Transactional
    public void saveOrUpdate (CaraBayaran caraBayaran){
        caraBayaranDAO.save(caraBayaran);
    }

    @Transactional
    public void saveOrUpdate (Transaksi transaksi){
        transakasiDAO.update(transaksi);
    }
    
    @Transactional
    public void save (Transaksi transaksi){
        transakasiDAO.save(transaksi);
    }

    @Transactional
    public void saveOrUpdate (Akaun akaun){
        akaunDAO.update(akaun);
    }

    @Transactional
    public void save (Dokumen dokumen){
        dokumenDAO.save(dokumen);
    }

    @Transactional
    public void saveOrUpdate(Dokumen dokumen){
        dokumenDAO.update(dokumen);
    }

}
