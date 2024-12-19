/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.etapp;

import com.google.inject.Inject;
import com.wideplay.warp.persist.Transactional;
import etanah.dao.HakmilikDAO;
import etanah.dao.HakmilikPermohonanDAO;
import etanah.dao.PemohonDAO;
import etanah.dao.PenggunaDAO;
import etanah.dao.PermohonanDAO;
import etanah.dao.PermohonanPihakDAO;
import etanah.dao.PermohonanPihakHubunganDAO;
import etanah.dao.PermohonanRujukanLuarDAO;
import etanah.dao.PihakDAO;
import etanah.model.Hakmilik;
import etanah.model.HakmilikPermohonan;
import etanah.model.HakmilikPihakBerkepentingan;
import etanah.model.InfoAudit;
import etanah.model.KodJenisPengenalan;
import etanah.model.Pemohon;
import etanah.model.Permohonan;
import etanah.model.PermohonanPihak;
import etanah.model.PermohonanPihakHubungan;
import etanah.model.PermohonanRujukanLuar;
import etanah.model.Pihak;
import etanah.model.etapp.EtappHakmilik;
import etanah.service.common.HakmilikPihakKepentinganService;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author faidzal
 */
public class PesakaService {

    @Inject
    PihakDAO pihakDAO;
    @Inject
    PermohonanPihakDAO permohonanPihakDAO;
    @Inject
    PenggunaDAO penggunaDAO;
    @Inject
    PermohonanRujukanLuarDAO permohonanRujukanLuarDAO;
    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;
    @Inject
    HakmilikPihakKepentinganService hakmilikPihakKepentinganService;
    @Inject
    HakmilikDAO hakmilikDAO;
    @Inject
    PemohonDAO pemohonDAO;
    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    HakmilikPermohonanDAO hakmilikPermohonanDAO;
    @Inject
    PermohonanPihakHubunganDAO permohonanPihakHubunganDAO;

    public EtappHakmilik findHakmilik(String noFail, String idHakmilik) {
        String query = "select p from etanah.model.etapp.EtappHakmilik p where p.etappPerintah.noFail =:noFail and p.hakmilik.idHakmilik=:idHakmilik";
        Query q = sessionProvider.get().createQuery(query);
        // Query q = s.createQuery("from etanah.model.LogPenggunaApplikasi p where p.idPguna =:idPguna order by p.idPgunaAppLog asc");
        q.setString("noFail", noFail);
        q.setString("idHakmilik", idHakmilik);
        return (EtappHakmilik) q.uniqueResult();
    }

    @Transactional
    public Pihak savePihak(Pihak pihak) {
        return pihakDAO.saveOrUpdate(pihak);
    }

    @Transactional
    public PermohonanPihak saveMohonPihak(PermohonanPihak mohonPihak) {
        return permohonanPihakDAO.saveOrUpdate(mohonPihak);
    }

    @Transactional
    public PermohonanPihakHubungan saveMohonPihakHbgn(PermohonanPihakHubungan pihakHbgn) {
        return permohonanPihakHubunganDAO.saveOrUpdate(pihakHbgn);
    }
    
    public InfoAudit getInfoAudit() {
        InfoAudit ia = new InfoAudit();
        ia.setDimasukOleh(penggunaDAO.findById("portal"));
        ia.setTarikhMasuk(new Date());
        return ia;
    }

    @Transactional
    public PermohonanRujukanLuar saveMohonRuj(PermohonanRujukanLuar mohonRuj) {
        return permohonanRujukanLuarDAO.saveOrUpdate(mohonRuj);
    }

    public HakmilikPihakBerkepentingan findSimatiPihakKepentingan(String idHakmilik, String noKpSimati) {
        HakmilikPihakBerkepentingan hak = new HakmilikPihakBerkepentingan();
        noKpSimati = convert(noKpSimati);
        Hakmilik hakmilik = hakmilikDAO.findById(idHakmilik);
        List<HakmilikPihakBerkepentingan> listHakmilik = hakmilikPihakKepentinganService.findPihakActiveByHakmilik(hakmilik);
        for (HakmilikPihakBerkepentingan hak1 : listHakmilik) {
            String s = hak.getPihak().getNoPengenalan();
            if (noKpSimati.equals(s)) {
                hak = hak1;
                break;
            }
        }
        return hak;
    }

    public String convert(String s) {
        if (s.contains("-")) {
            s = s.replace("-", "");
        }
        return s;
    }

    @Transactional
    public Pemohon savePemohon(Pemohon pemohon) {
        return pemohonDAO.saveOrUpdate(pemohon);
    }

    public void updatePemohon(Permohonan mohon, String idHakmilik, String noKpSimati) {
        HakmilikPihakBerkepentingan hakmilikPihak = findSimatiPihakKepentingan(idHakmilik, noKpSimati);
        Pemohon pemohon = new Pemohon();
        pemohon.setPihak(hakmilikPihak.getPihak());
        pemohon.setPermohonan(mohon);
        pemohon.setInfoAudit(getInfoAudit());
        pemohon.setCawangan(mohon.getCawangan());
        pemohon.setJenisPemohon("X");
        pemohon.setHakmilik(hakmilikPihak.getHakmilik());
        pemohon.setJenis(hakmilikPihak.getJenis());
        pemohon.setJumlahPenyebut(hakmilikPihak.getJumlahPenyebut());
        pemohon.setJumlahPembilang(hakmilikPihak.getJumlahPembilang());
        pemohon.setNama(hakmilikPihak.getPihak().getNama());
        pemohon.setJenisPengenalan(hakmilikPihak.getPihak().getJenisPengenalan());
        pemohon.setNoPengenalan(hakmilikPihak.getPihak().getNoPengenalan());
        pemohon = savePemohon(pemohon);
    }

    public Permohonan saveMohon(Permohonan mohon) {
        return permohonanDAO.saveOrUpdate(mohon);
    }

    public HakmilikPermohonan saveHakmilikPermohonan(HakmilikPermohonan hakmhn) {
        return hakmilikPermohonanDAO.saveOrUpdate(hakmhn);
    }

    public HakmilikPihakBerkepentingan findSimati(String idHakmilik, String simati) {
        String query = "select p from etanah.model.HakmilikPihakBerkepentingan p where p.hakmilik.idHakmilik =:idHakmilik and p.noPengenalan LIKE :simati and p.jenis.kod = 'PM' and p.aktif = 'Y'";
        Query q = sessionProvider.get().createQuery(query);
        // Query q = s.createQuery("from etanah.model.LogPenggunaApplikasi p where p.idPguna =:idPguna order by p.idPgunaAppLog asc");
        q.setString("idHakmilik", idHakmilik);
        q.setString("simati", "%" + simati + "%");
        return (HakmilikPihakBerkepentingan) q.uniqueResult();
    }

    public KodJenisPengenalan findKP(String jenisPengenalan) {
     String query = "select p from etanah.model.KodJenisPengenalan p where p.kodMyEtapp =:jenisPengenalan";
        Query q = sessionProvider.get().createQuery(query);
        // Query q = s.createQuery("from etanah.model.LogPenggunaApplikasi p where p.idPguna =:idPguna order by p.idPgunaAppLog asc");
        q.setString("jenisPengenalan", jenisPengenalan);
        return (KodJenisPengenalan) q.uniqueResult();
    }

//    public Pihak findPihak(String namaWaris, String noKpWaris) {
//       
//    }

    public int kiraUmur(String noKpWaris) {
        int umur = 0;
         
        int year = Integer.parseInt(noKpWaris.substring(0, 2));
        if (year > 25 && year < 99) {
          year = Integer.parseInt("19" + year);
        } else {
          year = Integer.parseInt("20" + year);
        }
        
        int curYear = Calendar.getInstance().get(Calendar.YEAR);

        umur = curYear - year;
        return umur;
    }

    public String tukarIC(String noKpWaris) {

        String ic = null;
        
        ic =   noKpWaris.substring(0, 6)+"-"+ noKpWaris.substring(6, 8)+"-"+noKpWaris.substring(8, 12) ;    
        return ic;
    }
}


