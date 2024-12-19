/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.ws.etapp.service;

import etanah.client.etapp.ArrayOfHakmilikPermohonanMyEtaPP;
import etanah.client.etapp.ArrayOfLampiranMyEtaPP;
import etanah.client.etapp.HakmilikPermohonanMyEtaPP;
import etanah.client.etapp.LampiranMyEtaPP;
import etanah.client.etapp.MaklumatHakmilikMyEtaPP;
import etanah.client.etapp.MaklumatPermohonanSek4MyEtaPP;
import etanah.client.etapp.MaklumatPermohonanSek8MyEtaPP;
import etanah.ws.myetapp.form.HakmilikForm;
import etanah.ws.myetapp.form.LampiranForm;
import etanah.ws.myetapp.form.MaklumatHakmilikForm;
import etanah.ws.myetapp.form.MaklumatPermohonanSek4Form;
import etanah.ws.myetapp.form.MaklumatPermohonanSek8Form;

/**
 *
 * @author faidzal
 */
public class EtappPengambilanService {

    public HakmilikForm setHakmilik(MaklumatHakmilikMyEtaPP ha) {
        HakmilikForm form = new HakmilikForm();
        form.setIdHakmilik(ha.getIdHakmilikEtaPP().getValue());
        form.setIdJenisHakmilik(ha.getIdJenisHakmilikEtaPP().getValue());
        form.setNoHakmilik(ha.getNoHakmilikEtaPP().getValue());
        form.setNoPT(ha.getNoPTEtaPP().getValue());
        form.setIdKategori(ha.getIdKategoriEtaPP().getValue());
//     form.idJenisPB= ha.get.getValue();
        form.setIdNegeri(ha.getIdNegeriEtaPP().getValue());
        form.setIdDaerah(ha.getIdDaerahEtaPP().getValue());
        form.setIdMukim(ha.getIdMukimEtaPP().getValue());
        form.setIdLuas(ha.getIdLuasEtaPP().getValue());
        form.setLuas(ha.getLuasEtaPP().getValue());
        form.setCatatan(ha.getCatatanEtaPP().getValue());
        form.setStatusPemilikan(ha.getStatusPemilikanEtaPP().getValue());
        form.setTanggungan(ha.getTanggunganEtaPP().getValue());
        form.setJenisTanah(ha.getJenisTanahEtaPP().getValue());
        form.setNoPerserahan(ha.getNoPerserahanEtaPP().getValue());
        form.setSyaratNyata(ha.getSyaratNyataEtaPP().getValue());
        form.setSekatan(ha.getSekatanEtaPP().getValue());
//    form.listPemilik1 = ha.getListPemilik().getValue().getPemilik();
        form.setNO_PERSERAHAN_GADAIAN(ha.getNOPERSERAHANGADAIANEtaPP().getValue());
        form.setKAVEAT(ha.getKAVEATEtaPP().getValue());
        form.setNO_PERSERAHAN_KAVEAT(ha.getNOPERSERAHANKAVEATEtaPP().getValue());
        form.setPAJAKAN(ha.getPAJAKANEtaPP().getValue());
        form.setNO_PERSERAHAN_PAJAKAN(ha.getNOPERSERAHANPAJAKANEtaPP().getValue());
        return form;
    }

    public MaklumatPermohonanSek4MyEtaPP setMaklumatPermohonanSek4MyEtaPP(MaklumatPermohonanSek4Form maklumatPermohonan) {
        MaklumatPermohonanSek4MyEtaPP f = new MaklumatPermohonanSek4MyEtaPP();
        f.getAlamat1EtaPP().setValue(maklumatPermohonan.getAlamat1());
        f.getAlamat2EtaPP().setValue(maklumatPermohonan.getAlamat2());
        f.getAlamat3EtaPP().setValue(maklumatPermohonan.getAlamat3());
        f.getAlamat4EtaPP().setValue(maklumatPermohonan.getAlamat4());
        f.getIdAgensiMyetappEtaPP().setValue(maklumatPermohonan.getId_agensi_myetapp());
        f.getIdKementerianMyetappEtaPP().setValue(maklumatPermohonan.getId_kementerian_myetapp());
        f.getJenisPengambilanEtaPP().setValue(maklumatPermohonan.getJenis_pengambilan());
        f.getJenisProjekPengambilanEtaPP().setValue(maklumatPermohonan.getJenis_projek_pengambilan());
        f.getKodAgensiEtaPP().setValue(maklumatPermohonan.getKodAgensi());
        f.getKodDaerahPengambilanEtaPP().setValue(maklumatPermohonan.getKod_daerah_pengambilan());
        f.getKodNegeriEtaPP().setValue(maklumatPermohonan.getKodNegeri());
        f.getKodKementerianEtaPP().setValue(maklumatPermohonan.getKodKementerian());
        f.getKodNegeriPengambilanEtaPP().setValue(maklumatPermohonan.getKod_negeri_pengambilan());
        f.getNamaAgensiEtaPP().setValue(maklumatPermohonan.getNama_agensi());
        f.getNamaDaerahPengambilanEtaPP().setValue(maklumatPermohonan.getNama_daerah_pengambilan());
        f.getNamaKementerianEtaPP().setValue(maklumatPermohonan.getNama_kementerian());
        f.getNamaNegeriPengambilanEtaPP().setValue(maklumatPermohonan.getNama_negeri_pengambilan());
        f.getNoFailJkptgEtaPP().setValue(maklumatPermohonan.getNo_fail_jkptg());
        f.getNoRujukanSuratKjpEtaPP().setValue(maklumatPermohonan.getNo_rujukan_surat_kjp());
        f.getPoskodEtaPP().setValue(maklumatPermohonan.getPoskod());
        f.getTarikhPermohonanEtaPP().setValue(maklumatPermohonan.getTarikh_permohonan());
        f.getTarikhSuratKjpEtaPP().setValue(maklumatPermohonan.getTarikh_surat_kjp());
        f.getTujuanDalamEnglishEtaPP().setValue(maklumatPermohonan.getTujuan_dalam_english());
        f.getTujuanEtaPP().setValue(maklumatPermohonan.getTujuan());

        return f;
    }

    public ArrayOfHakmilikPermohonanMyEtaPP setArrayOfHakmilikPermohonanMyEtaPP(MaklumatHakmilikForm[] maklumatHakmilikForm) {
        ArrayOfHakmilikPermohonanMyEtaPP a = new ArrayOfHakmilikPermohonanMyEtaPP();
        for (int i = 0; 1 < maklumatHakmilikForm.length; i++) {
            HakmilikPermohonanMyEtaPP e = new HakmilikPermohonanMyEtaPP();
            e.getIdHakmilikEtaPP().setValue(maklumatHakmilikForm[i].getId_hakmilik());
            e.getKodLuasAmbilEtaPP().setValue(maklumatHakmilikForm[i].getKod_luas_ambil());
            e.getKodLuasAsalEtaPP().setValue(maklumatHakmilikForm[i].getKod_luas_asal());
            e.getKodUnitHakmilikEtaPP().setValue(maklumatHakmilikForm[i].getKod_unit_hakmilik());
            e.getLuasAmbilEtaPP().setValue(maklumatHakmilikForm[i].getLuas_ambil());
            e.getLuasAsalEtaPP().setValue(maklumatHakmilikForm[i].getLuas_asal());
            e.getNoFailJkptgEtaPP().setValue(maklumatHakmilikForm[i].getNo_fail_jkptg());
            e.getNoHakmilikEtaPP().setValue(maklumatHakmilikForm[i].getNo_hakmilik());
            e.getNoLotEtaPP().setValue(maklumatHakmilikForm[i].getNo_lot());
            e.getNoWartaEtaPP().setValue(maklumatHakmilikForm[i].getNo_warta());
            e.getStatusBorangkEtaPP().setValue(maklumatHakmilikForm[i].getStatus_borangk());
            e.getTarikhBorangkEtaPP().setValue(maklumatHakmilikForm[i].getTarikh_borangk());
            e.getTarikhWartaEtaPP().setValue(maklumatHakmilikForm[i].getTarikh_warta());
            a.getHakmilikPermohonanMyEtaPP().add(e);
        }
        return a;
    }

    public LampiranMyEtaPP setLampiranMyEtaPP(LampiranForm attchment) {
        LampiranMyEtaPP l = new LampiranMyEtaPP();
        l.getBytesEtaPP().setValue(attchment.getBytes());
        l.getDocTypeEtaPP().setValue(attchment.getDocType());
        l.getFilenameEtaPP().setValue(attchment.getFilename());
        l.getKodDokumenEtaPP().setValue(attchment.getKodDokumen());
        return l;
    }

    public ArrayOfLampiranMyEtaPP setArrayOfLampiranMyEtaPP(LampiranForm[] attchment) {
        ArrayOfLampiranMyEtaPP a = new ArrayOfLampiranMyEtaPP();
        for (LampiranForm attchment1 : attchment) {
            LampiranMyEtaPP e = setLampiranMyEtaPP(attchment1);
            a.getLampiranMyEtaPP().add(e);
        }
        return a;
    }

    public MaklumatPermohonanSek8MyEtaPP setMaklumatPermohonanSek8MyEtaPP(MaklumatPermohonanSek8Form maklumatPermohonan) {
        MaklumatPermohonanSek8MyEtaPP f = new MaklumatPermohonanSek8MyEtaPP();
        f.getAlamat1EtaPP().setValue(maklumatPermohonan.getAlamat1());
        f.getAlamat2EtaPP().setValue(maklumatPermohonan.getAlamat2());
        f.getAlamat3EtaPP().setValue(maklumatPermohonan.getAlamat3());
        f.getAlamat4EtaPP().setValue(maklumatPermohonan.getAlamat4());
        f.getIdAgensiMyetappEtaPP().setValue(maklumatPermohonan.getId_agensi_myetapp());
        f.getIdKementerianMyetappEtaPP().setValue(maklumatPermohonan.getId_kementerian_myetapp());
        f.getJenisPengambilanEtaPP().setValue(maklumatPermohonan.getJenis_pengambilan());
        f.getJenisProjekPengambilanEtaPP().setValue(maklumatPermohonan.getJenis_projek_pengambilan());
        f.getKodAgensiEtaPP().setValue(maklumatPermohonan.getKodAgensi());
        f.getKodDaerahPengambilanEtaPP().setValue(maklumatPermohonan.getKod_daerah_pengambilan());
        f.getKodNegeriEtaPP().setValue(maklumatPermohonan.getKodNegeri());
        f.getKodKementerianEtaPP().setValue(maklumatPermohonan.getKodKementerian());
        f.getKodNegeriPengambilanEtaPP().setValue(maklumatPermohonan.getKod_negeri_pengambilan());
        f.getNamaAgensiEtaPP().setValue(maklumatPermohonan.getNama_agensi());
        f.getNamaDaerahPengambilanEtaPP().setValue(maklumatPermohonan.getNama_daerah_pengambilan());
        f.getNamaKementerianEtaPP().setValue(maklumatPermohonan.getNama_kementerian());
        f.getNamaNegeriPengambilanEtaPP().setValue(maklumatPermohonan.getNama_negeri_pengambilan());
        f.getNoFailJkptgEtaPP().setValue(maklumatPermohonan.getNo_fail_jkptg());
        f.getNoRujukanSuratKjpEtaPP().setValue(maklumatPermohonan.getNo_rujukan_surat_kjp());
        f.getPoskodEtaPP().setValue(maklumatPermohonan.getPoskod());
        f.getTarikhPermohonanEtaPP().setValue(maklumatPermohonan.getTarikh_permohonan());
        f.getTarikhSuratKjpEtaPP().setValue(maklumatPermohonan.getTarikh_surat_kjp());
        f.getTujuanDalamEnglishEtaPP().setValue(maklumatPermohonan.getTujuan_dalam_english());
        f.getTujuanEtaPP().setValue(maklumatPermohonan.getTujuan());
        return f;
    }
}
