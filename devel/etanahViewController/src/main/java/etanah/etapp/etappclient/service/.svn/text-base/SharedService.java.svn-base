/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.etapp.etappclient.service;

import etanah.etapp.etappclient.form.DokumenForm;
import etanah.etapp.etappclient.form.HakmilikForm;
import etanah.etapp.etappclient.form.PermohonanForm;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author mohd.faidzal
 */
public class SharedService {
    
    public PermohonanForm setlistHakmilik(PermohonanForm permohonan, List<HakmilikForm> listHakmilikForm) {
//        List<HakmilikForm> listHakmilik = new ArrayList();
//        for (HakmilikForm f : listHakmilikForm) {
//           HakmilikForm h = new HakmilikForm();
//            h.setIdHakmilik(f.getIdHakmilik());
//            h.setIdHakmilikSambungan(f.getIdHakmilikSambungan());
//            h.setKodDaerah(f.getKodDaerah());
//            h.setKodHakmilik(f.getKodHakmilik());
//            h.setKodLot(f.getKodLot());
//            h.setKodLuasSambungan(f.getKodLuasSambungan());
//            h.setKodMukim(f.getKodMukim());
//            h.setKodNegeri(f.getKodNegeri());
//            h.setLuasSambungan(f.getLuasSambungan());
//            h.setMasaEndorsan(f.getMasaEndorsan());
//            h.setNoHakmilik(f.getNoHakmilik());
//            h.setNoLot(f.getNoLot());
//            h.setNoPerserahan(f.getNoPerserahan());
//            h.setNoSeksyen(f.getNoSeksyen());
//            h.setTarikhEndorsan(f.getTarikhEndorsan());
//            listHakmilik.add(h);
//        }
//        permohonan.getListHakmilik().addAll(listHakmilik);
        permohonan.setListHakmilik(listHakmilikForm);
        return permohonan;
    }
    
    public PermohonanForm setlistDokumen(PermohonanForm permohonan, List<DokumenForm> listDokumenForm) {
//        for (DokumenForm f : listDokumenForm) {
//            DokumenForm e = new DokumenForm();
//            e.setDocContent(f.getDocContent());
//            e.setJenisDokumen(f.getJenisDokumen());
//            e.setJenisMime(f.getJenisMime());
//            e.setNamaDokumen(f.getNamaDokumen());
//            listDokumenForm.add(e);
//            permohonan.setListDokumen(listDokumenForm);
//            permohonan.getListDokumen().add(e);
//        }
        permohonan.setListDokumen(listDokumenForm);
        
        return permohonan;
    }

    PermohonanForm setPermohonan(PermohonanForm f) {
        PermohonanForm permohonan = new PermohonanForm();
        permohonan.setCatatan(f.getCatatan());
        permohonan.setCatatanKeputusan(f.getCatatanKeputusan());
        permohonan.setJenis(f.getJenis());
        permohonan.setKeputusan(f.getKeputusan());
        permohonan.setNoFail(f.getNoFail());
        permohonan.setNoJilid(f.getNoJilid());
        permohonan.setNoPU(f.getNoJilid());
        permohonan.setNoRujukan(f.getNoRujukan());
        permohonan.setNoWarta(f.getNoWarta());
        permohonan.setTarikh(f.getTarikh());
        permohonan.setTarikhKeputusan(f.getTarikhKeputusan());
        permohonan.setTarikhWarta(f.getTarikhWarta());
        
        return permohonan;
    }
}
