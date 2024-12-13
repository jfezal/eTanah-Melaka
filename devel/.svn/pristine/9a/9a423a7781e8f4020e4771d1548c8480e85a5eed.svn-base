/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.etapp.ws.service;

import etanah.etapp.ws.client.Dokumen;
import etanah.etapp.ws.client.Hakmilik;
import etanah.etapp.ws.client.Permohonan;
import etanah.etapp.ws.form.DokumenForm;
import etanah.etapp.ws.form.HakmilikForm;
import etanah.etapp.ws.form.PermohonanForm;

/**
 *
 * @author mohd.faidzal
 */
public class PermohonanService {

    Permohonan setForm(PermohonanForm form) {
        Permohonan p = new Permohonan();
        p.setCatatan(form.getCatatan());
        p.setCatatanKeputusan(form.getCatatanKeputusan());
        p.setJenis(form.getJenis());
        p.setKeputusan(form.getKeputusan());
        p.setNoFail(form.getNoFail());
        p.setNoJilid(form.getNoJilid());
        p.setNoPU(form.getNoPU());
        p.setNoRujukan(form.getNoRujukan());
        p.setNoWarta(form.getNoWarta());
        p.setTarikh(form.getTarikh());
        p.setTarikhKeputusan(form.getTarikhKeputusan());
        p.setTarikhWarta(form.getTarikhWarta());
        if (form.getListDokumen()!=null) {
            for (int i = 0; i < form.getListDokumen().size(); i++) {
                DokumenForm dokumenForm = form.getListDokumen().get(i);
                Dokumen dokumen = new Dokumen();
                dokumen.setDoContent(dokumenForm.getDoContent());
                dokumen.setJenisDokumen(dokumenForm.getJenisDokumen());
                dokumen.setJenisMime(dokumenForm.getJenisMime());
                dokumen.setNamaDokumen(dokumenForm.getNamaDokumen());
                p.getListDokumen().add(dokumen);
            }
        } else {
        }
        if (form.getListHakmilik()!=null) {

            for (int i = 0; i < form.getListHakmilik().size(); i++) {
                HakmilikForm f = form.getListHakmilik().get(i);
                Hakmilik h = new Hakmilik();
                h.setIdHakmilik(f.getIdHakmilik());
                h.setIdHakmilikSambungan(f.getIdHakmilikSambungan());
                h.setKodDaerah(f.getKodDaerah());
                h.setKodHakmilik(f.getKodHakmilik());
                h.setKodLot(f.getKodLot());
                h.setKodLuasSambungan(f.getKodLuasSambungan());
                h.setKodMukim(f.getKodMukim());
                h.setKodNegeri(f.getKodNegeri());
                h.setLuasSambungan(f.getLuasSambungan());
                h.setMasaEndorsan(f.getMasaEndorsan());
                h.setNoHakmilik(f.getNoHakmilik());
                h.setNoLot(f.getNoLot());
                h.setNoPerserahan(f.getNoPerserahan());
                h.setNoSeksyen(f.getNoSeksyen());
                h.setTarikhEndorsan(f.getTarikhEndorsan());
                p.getListHakmilik().add(h);
            }
        }

        return p;
    }
}
