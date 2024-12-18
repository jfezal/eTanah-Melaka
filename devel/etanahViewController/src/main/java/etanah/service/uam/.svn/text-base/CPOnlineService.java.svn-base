/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.service.uam;

import com.google.inject.Inject;
import com.google.inject.Injector;
import etanah.dao.DokumenDAO;
import etanah.model.Dokumen;
import etanah.model.PortalTransaksi;
import etanah.model.view.CarianPortalTrans;
import etanah.view.etanahContextListener;
import etanah.view.portal.ws.CarianPersendirianEbayarService;
import etanah.view.uam.CPTransaksi;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author mohd.faidzal
 */
public class CPOnlineService {

    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;
//    @Inject
//    CarianPersendirianEbayarService carian;    
    
    @Inject
    DokumenDAO dokumenDAO;
    private static Injector injector = etanahContextListener.getInjector();

    CarianPersendirianEbayarService carian = injector.getInstance(CarianPersendirianEbayarService.class);

    public List<CPTransaksi> findFailedGenerate() {
        List<CPTransaksi> list = new ArrayList<CPTransaksi>();
        Session s = sessionProvider.get();
        String query = "select p "
                + "from etanah.model.view.CarianPortalTrans p ";
        Query q = sessionProvider.get().createQuery(query);
        System.out.println("sql :" + q.getQueryString());
        List<CarianPortalTrans> listC = q.list();
        for (int i = 0; i < listC.size(); i++) {
            CarianPortalTrans pt = (CarianPortalTrans) listC.get(i);
            CPTransaksi cp = new CPTransaksi();
//            String query1 = "select dok "
//                    + "from etanah.model.PortalTransaksi p,etanah.model.PermohonanCarian pc, etanah.model.KandunganFolder kf, "
//                    + "etanah.model.Dokumen dok where "
//                    + "p.mohon = pc.idCarian and "
//                    + "pc.folderDokumen.folderId = kf.folder.folderId and "
//                    + "kf.dokumen.idDokumen = dok.idDokumen and "
//                    + "p.idTransaksi = :idTransaksi";
//            Query qq = sessionProvider.get().createQuery(query1);
//            qq.setString("idTransaksi", pt.getIdTransaksi() + "");
//            if (!qq.list().isEmpty()) {
//                Dokumen dok = (Dokumen) qq.list().get(0);
//                cp.setDokumen(dok);
//            }
            
            Dokumen dok = dokumenDAO.findById(Long.parseLong(pt.getIdDokumen()));
            
            cp.setIdHakmilik(pt.getIdHakmilik());
            cp.setIdmohon(pt.getIdCarian());
            cp.setNoResit(pt.getNoResit());
            cp.setTrhResit(pt.getTrhResit().toString());
            cp.setDokumen(dok);
            list.add(cp);
        }
        return list;
    }

    public String janaCarian(String accountNo, String transId, String paymentDateTime, String amaun, String idPengguna, String resitNo, String namaPenyerah) {
        String error = "";
        try {
            carian.updateCarianAccount(accountNo, transId, paymentDateTime, new BigDecimal(amaun), idPengguna, resitNo,namaPenyerah );
        } catch (Exception ex) {
            error = ex.toString();
        }
        return error;
    }
    
     public String janaPortalTrans(String idDokumen, String idmohon, String idHakmilik) {
        String error = "";
        try {
            carian.updateCarianPortalTrans(idDokumen, idmohon, idHakmilik);
        } catch (Exception ex) {
            error = ex.toString();
        }
        return error;
    }

}
