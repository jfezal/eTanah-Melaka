/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.stripes.hasil;

import com.google.inject.Inject;
import com.wideplay.warp.persist.Transactional;
import etanah.dao.DokumenKewanganDAO;
import etanah.dao.DokumenKewanganBayaranDAO;
import etanah.dao.TransaksiDAO;
import etanah.model.DokumenKewangan;
import etanah.model.Transaksi;
import etanah.model.Akaun;
import etanah.dao.AkaunDAO;
import etanah.dao.CaraBayaranDAO;
import etanah.dao.KodCaraBayaranDAO;
import etanah.dao.hasil.LaporanPenyataPemungutDAO;
import etanah.model.CaraBayaran;
import etanah.model.DokumenKewanganBayaran;
import etanah.model.hasil.LaporanPenyataPemungutItem;
import java.util.List;
import org.apache.log4j.Logger;

/**
 *
 * @author nurfaizati
 */
public class PembatalanResitManager {

    private static final Logger LOG = Logger.getLogger(PembatalanResitManager.class);

    @Inject
    DokumenKewanganDAO dokumenKewanganDAO;
    @Inject
    DokumenKewanganBayaranDAO dokumenKewanganBayaranDAO;
    @Inject
    TransaksiDAO transaksiDAO;
    @Inject
    AkaunDAO akaunDAO;
    @Inject
    LaporanPenyataPemungutDAO laporanPenyataPemungutDAO;
    @Inject
    CaraBayaranDAO caraBayaranDAO;

    @Transactional

    public void update(DokumenKewangan dokumenKewangan, List<Transaksi> listTrans) {

        for (Transaksi trans : listTrans) {
            transaksiDAO.update(trans);
        }

    }

    @Transactional
    public void updateAll(DokumenKewangan dokumenKewangan, List<Akaun> akaunList, List<Transaksi> listTrans) {
//     public void updateAll(DokumenKewangan dokumenKewangan, List<Transaksi> listTrans) {

        for (Akaun akaun : akaunList) {
            akaunDAO.update(akaun);
        }
        for (Transaksi trans : listTrans) {
            transaksiDAO.update(trans);
        }

    }

    @Transactional
    public void updateAkaun(List<Akaun> akaunList) {

        for (Akaun akaun : akaunList) {
            akaunDAO.update(akaun);
        }

    }

    @Transactional
    public void updatePenyataPemungut(List<LaporanPenyataPemungutItem> senaraiLPPI) {
        try {
            for (LaporanPenyataPemungutItem laporanPenyataPemungutItem : senaraiLPPI) {
                laporanPenyataPemungutDAO.saveOrUpdate(laporanPenyataPemungutItem);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Transactional
    public void updateAkaun(Akaun akKredit) {

        akaunDAO.save(akKredit);

    }

    @Transactional
    public void updateAkaunDebit(Akaun akDebit) {

        akaunDAO.save(akDebit);

    }

    @Transactional
    public void save(Transaksi transaksi) {
        transaksiDAO.saveOrUpdate(transaksi);
    }

    @Transactional
    public void update(DokumenKewangan dk, Transaksi t) {

        dokumenKewanganDAO.update(dk);
        transaksiDAO.saveOrUpdate(t);

    }

    @Transactional
    public void saveOrUpdate(Akaun akaun) {
        akaunDAO.update(akaun);
    }

    @Transactional
    public void saveOrUpdate(DokumenKewangan dk, List<Transaksi> senaraiTransaksiBaru, List<Transaksi> senaraiTransaksiUpdate) {
        try {
            dokumenKewanganDAO.saveOrUpdate(dk);
            for (Transaksi transBaru : senaraiTransaksiBaru) {
                transaksiDAO.save(transBaru);
                LOG.info("idTransaksiBaru :" + transBaru.getIdTransaksi());
            }
            for (Transaksi transUpdate : senaraiTransaksiUpdate) {
                transaksiDAO.saveOrUpdate(transUpdate);
                LOG.info("idTransUpdate :" + transUpdate.getIdTransaksi());
            }
        } catch (Exception ex) {
            LOG.error(ex);
            ex.printStackTrace(); // for development only
        }
    }

    @Transactional
    public void updateDokKew(DokumenKewangan dk) {
        try {

            dokumenKewanganDAO.update(dk);
        } catch (Exception ex) {
            ex.printStackTrace(); // for development only
        }

    }
    
    @Transactional
    public void updateDokKewBayar(List<DokumenKewanganBayaran> dkb) {
        try {
            for (DokumenKewanganBayaran dkBaru : dkb) {
               dokumenKewanganBayaranDAO.update(dkBaru);        
            }

            
        } catch (Exception ex) {
            ex.printStackTrace(); // for development only
        }

    }

    @Transactional
    public void updateCaraBayar(List<CaraBayaran> crbyr) {
        try {
            for (CaraBayaran cbyrBaru : crbyr) {
               caraBayaranDAO.update(cbyrBaru);        
            }

            
        } catch (Exception ex) {
            ex.printStackTrace(); // for development only
        }

    }
}
