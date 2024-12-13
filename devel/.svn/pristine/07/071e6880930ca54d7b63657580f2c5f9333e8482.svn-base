/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.stripes.hasil;

import com.google.inject.Inject;
import com.wideplay.warp.persist.Transactional;
import etanah.dao.AkaunDAO;
import etanah.dao.DasarTuntutanNotisDAO;
import etanah.dao.HakmilikAlamatDAO;
import etanah.dao.HakmilikDAO;
import etanah.dao.HakmilikPermohonanDAO;
import etanah.dao.KodSekolahBantuanModalDAO;
import etanah.dao.PermohonanAktivitiDAO;
import etanah.dao.PermohonanDAO;
import etanah.dao.PermohonanPihakDAO;
import etanah.dao.PermohonanRujukanLuarDAO;
import etanah.dao.TransaksiDAO;
import etanah.dao.TransaksiHadapanDAO;
import etanah.model.Akaun;
import etanah.model.DasarTuntutanNotis;
import etanah.model.Hakmilik;
import etanah.model.HakmilikAlamat;
import etanah.model.HakmilikPermohonan;
import etanah.model.InfoAudit;
import etanah.model.KodRujukan;
import etanah.model.KodSekolahBantuanModal;
import etanah.model.KodUrusan;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.model.PermohonanAktiviti;
import etanah.model.PermohonanPihak;
import etanah.model.PermohonanRujukanLuar;
import etanah.model.Transaksi;
import etanah.model.TransaksiHadapan;
import etanah.sequence.GeneratorIdNotis;
import etanah.service.common.PermohonanService;
import etanah.util.StringUtils;
import java.math.BigDecimal;
import java.util.List;
import org.hibernate.TransactionException;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author abu.mansur
 */
public class RemisyenManager {

    private static final Logger LOG = Logger.getLogger(RemisyenManager.class);
    private static final boolean IS_LOG_DEBUG = LOG.isDebugEnabled();
    @Inject
    PermohonanRujukanLuarDAO permohonanRujukanLuarDAO;
    @Inject
    PermohonanAktivitiDAO permohonanAktivitiDAO;
    private List<PermohonanAktiviti> senaraiPermohonanAktivitiBase;
    @Inject
    HakmilikAlamatDAO hakmilikAlamatDAO;
    @Inject
    HakmilikDAO hakmilikDAO;
    private List<Hakmilik> senaraiHakmilik;
    @Inject
    AkaunDAO akaunDAO;
    @Inject
    TransaksiDAO transaksiDAO;
    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    PermohonanPihakDAO permohonanPihakDAO;
    @Inject
    DasarTuntutanNotisDAO dasarTuntutanNotisDAO;
    @Inject
    GeneratorIdNotis generatorIdNotis;
    @Inject
    StringUtils stringUtils;
    @Inject
    HakmilikPermohonanDAO hakmilikPermohonanDAO;
    @Inject
    GenerateIdPermohonanWorkflow gipw;
    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;
    @Inject
    TransaksiHadapanDAO transaksiHadapanDAO;
    @Inject
    KodSekolahBantuanModalDAO kodSekolahBantuanModalDAO;

    //save or update for mapping PermohonanUlasan
    @Transactional
    public String saveOrUpdate(PermohonanRujukanLuar permohonanRujukanLuar, Pengguna pengguna) {
        LOG.info("permohonanRujukanLuar:start");
        String result = null;
        InfoAudit info = new InfoAudit();
        info.setDiKemaskiniOleh(pengguna);
        info.setTarikhKemaskini(new java.util.Date());
        try {
            if (permohonanRujukanLuar.getIdRujukan() == 0) {
                info.setDimasukOleh(pengguna);
                info.setTarikhMasuk(new java.util.Date());
                permohonanRujukanLuar.setInfoAudit(info);
                permohonanRujukanLuarDAO.save(permohonanRujukanLuar);
                result = "save";
            } else {
                info.setDimasukOleh(permohonanRujukanLuar.getInfoAudit().getDimasukOleh());
                info.setTarikhMasuk(permohonanRujukanLuar.getInfoAudit().getTarikhMasuk());
                permohonanRujukanLuar.setInfoAudit(info);
                permohonanRujukanLuarDAO.update(permohonanRujukanLuar);
                result = "update";
            }
        } catch (Exception ex) {
            result = ex.toString();
            LOG.error("permohonanRujukanLuar ex: " + ex);
            ex.printStackTrace();
        }
        LOG.info("permohonanRujukanLuar:finish");
        return result;
    }

    //save or update for mapping PermohonanAktiviti
    @Transactional
    public String saveOrUpdate(PermohonanAktiviti permohonanAktiviti, String idPermohonan, Pengguna pengguna) {
        LOG.info("permohonanAktiviti:start");
        String result = null;
        InfoAudit info = new InfoAudit();
        info.setDiKemaskiniOleh(pengguna);
        info.setTarikhKemaskini(new java.util.Date());

        String[] name = {"idPermohonan"};
        Object[] value = {idPermohonan};
        try {
            senaraiPermohonanAktivitiBase = permohonanAktivitiDAO.findByEqualCriterias(name, value, null);
            if (permohonanAktivitiDAO.findById(idPermohonan) == null) {
                info.setDimasukOleh(pengguna);
                info.setTarikhMasuk(new java.util.Date());
                permohonanAktiviti.setInfoAudit(info);
                permohonanAktivitiDAO.save(permohonanAktiviti);
                result = "save";
            } else {
                info.setDimasukOleh(permohonanAktiviti.getInfoAudit().getDimasukOleh());
                info.setTarikhMasuk(permohonanAktiviti.getInfoAudit().getTarikhMasuk());
                permohonanAktiviti.setInfoAudit(info);
                permohonanAktivitiDAO.update(permohonanAktiviti);
                result = "update";
            }
        } catch (TransactionException tex) {
            result = tex.toString();
            LOG.error("permohonanAktiviti ex: " + tex);
        }
        LOG.info("permohonanAktiviti:finish");
        return result;
    }

    //update data for class PermohonanPihak
    @Transactional
    public void update(PermohonanPihak permohonanPihak, Pengguna pengguna) {
        LOG.info("permohonan:start");
        InfoAudit info = new InfoAudit();
        info.setDimasukOleh(permohonanPihak.getInfoAudit().getDimasukOleh());
        info.setTarikhMasuk(permohonanPihak.getInfoAudit().getTarikhMasuk());
        info.setDiKemaskiniOleh(pengguna);
        info.setTarikhKemaskini(new java.util.Date());
        try {
            permohonanPihak.setInfoAudit(info);
            permohonanPihakDAO.update(permohonanPihak);
        } catch (Exception ex) {
            LOG.error("ProsessRemisyen Rollback. permohonan Ex :" + ex);
        }
        LOG.info("permohonan:finish");
    }

    //save data for class PermohonanPihak
    @Transactional
    public void save(PermohonanPihak permohonanPihak, Pengguna pengguna) {
        LOG.info("permohonan:start");
        InfoAudit info = new InfoAudit();
        info.setDimasukOleh(pengguna);
        info.setTarikhMasuk(new java.util.Date());
        try {
            permohonanPihak.setInfoAudit(info);
            permohonanPihakDAO.save(permohonanPihak);
        } catch (Exception ex) {
            LOG.error("ProsessRemisyen Rollback. permohonan Ex :" + ex);
        }
        LOG.info("permohonan:finish");
    }

    //save or update for mapping HakmilikAlamat
    @Transactional
    public String saveOrUpdate(HakmilikAlamat hakmilikAlamat, String idHakmilik, Pengguna pengguna) {
        LOG.info("hakmilikAlamat:start");
        String result = null;
        LOG.debug("idHakmilik :" + idHakmilik);
        InfoAudit info = new InfoAudit();        
        info.setDiKemaskiniOleh(pengguna);
        info.setTarikhKemaskini(new java.util.Date());
        try {
            if (hakmilikAlamat.getIdHakmilik() == null) {
                info.setDimasukOleh(pengguna);
                info.setTarikhMasuk(new java.util.Date());
                hakmilikAlamat.setInfoAudit(info);
                hakmilikAlamat.setIdHakmilik(idHakmilik);
                hakmilikAlamatDAO.save(hakmilikAlamat);
                result = "save";
            } else {
                info.setDimasukOleh(hakmilikAlamat.getInfoAudit().getDimasukOleh());
                info.setTarikhMasuk(hakmilikAlamat.getInfoAudit().getTarikhMasuk());
                hakmilikAlamat.setInfoAudit(info);
                hakmilikAlamatDAO.update(hakmilikAlamat);
                result = "update";
            }
            LOG.debug("idHakmilikAlamat : " + hakmilikAlamat.getIdHakmilik());
        } catch (TransactionException tex) {
            result = tex.toString();
            LOG.error("HakmilikAlamat ex: " + tex);
        }
        LOG.info("hakmilikALamat:finish");
        return result;
    }

    //save and update data for class ProsessRemisyan
    @Transactional
    public void saveAndUpdate(Transaksi transaksi, Akaun akaunADL, Akaun akaunAR, Akaun akaunARC, Akaun akaunART, Akaun akaunAC, Pengguna pengguna) {
        LOG.info("transaksi:start");
        InfoAudit info = new InfoAudit();
        info.setDiKemaskiniOleh(pengguna);
        info.setTarikhKemaskini(new java.util.Date());
        try {
            if (akaunADL != null) {
                info.setDimasukOleh(akaunADL.getInfoAudit().getDimasukOleh());
                info.setTarikhMasuk(akaunADL.getInfoAudit().getTarikhMasuk());
                akaunADL.setInfoAudit(info);
                akaunDAO.update(akaunADL);
            }
            if (akaunAR != null) {
                info.setDimasukOleh(akaunAR.getInfoAudit().getDimasukOleh());
                info.setTarikhMasuk(akaunAR.getInfoAudit().getTarikhMasuk());
                akaunAR.setInfoAudit(info);
                akaunDAO.update(akaunAR);
            }
            if (akaunARC != null) {
                info.setDimasukOleh(akaunARC.getInfoAudit().getDimasukOleh());
                info.setTarikhMasuk(akaunARC.getInfoAudit().getTarikhMasuk());
                akaunARC.setInfoAudit(info);
                akaunDAO.update(akaunARC);
            }
            if (akaunART != null) {
                info.setDimasukOleh(akaunART.getInfoAudit().getDimasukOleh());
                info.setTarikhMasuk(akaunART.getInfoAudit().getTarikhMasuk());
                akaunART.setInfoAudit(info);
                akaunDAO.update(akaunART);
            }
            if(akaunAC != null){
                info.setDimasukOleh(akaunAC.getInfoAudit().getDimasukOleh());
                info.setTarikhMasuk(akaunAC.getInfoAudit().getTarikhMasuk());
                akaunAC.setInfoAudit(info);
                akaunDAO.update(akaunAC);
            }

            info.setDimasukOleh(pengguna);
            info.setTarikhMasuk(new java.util.Date());
            transaksi.setInfoAudit(info);
            transaksiDAO.save(transaksi);
        } catch (Exception ex) {
            LOG.error("ProsesRemisyen Rollback. Ex :" + ex);
        }
        LOG.info("transaksi:finish");
    }

    //special for RemisyenTanah <<utility>>
    @Transactional
    public void saveAndUpdateTransaksiAndAkaun(Transaksi transaksi, Akaun akaun){        
        akaunDAO.update(akaun);
        transaksiDAO.save(transaksi);
        LOG.info("(saveAndUpdateTransaksiAndAkaun) idtransaksi :"+transaksi.getIdTransaksi());
        LOG.info("(saveAndUpdateTransaksiAndAkaun) noAkaun :"+akaun.getNoAkaun());
    }

    @Transactional
    public void saveTransaksi(Transaksi transaksi){
        transaksiDAO.save(transaksi);
        LOG.info("(saveTransaksi) idtransaksi :"+transaksi.getIdTransaksi());
    }

    @Transactional
    public void updateAkaun(Akaun akaun){
        akaunDAO.update(akaun);
        LOG.info("(updateAkaun) noAkaun :"+akaun.getNoAkaun());
    }

    //save and update data for class ProsessRemisyan for table TransaksiHadapan
    @Transactional
    public void saveTransaksiHadapan(TransaksiHadapan transaksiHadapan, Pengguna pengguna) {
        LOG.info("transaksi:start");
        InfoAudit info = new InfoAudit();
        info.setDiKemaskiniOleh(pengguna);
        info.setTarikhKemaskini(new java.util.Date());
        try {
            info.setDimasukOleh(pengguna);
            info.setTarikhMasuk(new java.util.Date());
            transaksiHadapan.setInfoAudit(info);
            transaksiHadapanDAO.save(transaksiHadapan);
        } catch (Exception ex) {
            LOG.error("ProsesRemisyen Rollback. Ex :" + ex);
        }
        LOG.info("transaksiHadapan:finish");
    }

    //save data for class MaklumatLainActionBean
    @Transactional
    public void save(Permohonan permohonan, Pengguna pengguna) {
        LOG.info("permohonan:start");
        InfoAudit info = new InfoAudit();
        info.setDimasukOleh(permohonan.getInfoAudit().getDimasukOleh());
        info.setTarikhMasuk(permohonan.getInfoAudit().getTarikhMasuk());
        info.setDiKemaskiniOleh(pengguna);
        info.setTarikhKemaskini(new java.util.Date());
        try {
            permohonan.setInfoAudit(info);
            permohonanDAO.update(permohonan);
        } catch (Exception ex) {
            LOG.error("ProsessRemisyen Rollback. permohonan Ex :" + ex);
        }
        LOG.info("permohonan:finish");
    }

    //save data for class MaklumatLainActionBean
    @Transactional
    public String saveOrUpdate(Permohonan permohonan, PermohonanRujukanLuar permohonanRujukanLuar, Pengguna pengguna) {
        LOG.info("permohonan, permohonanRujukanLuar:start");
        String result = "";
        InfoAudit info = new InfoAudit();
        info.setDiKemaskiniOleh(pengguna);
        info.setTarikhKemaskini(new java.util.Date());
        try {
            if(permohonan != null){
                info.setDimasukOleh(permohonan.getInfoAudit().getDimasukOleh());
                info.setTarikhMasuk(permohonan.getInfoAudit().getTarikhMasuk());
                permohonan.setInfoAudit(info);
                permohonanDAO.update(permohonan);
            }
            if(permohonanRujukanLuar.getInfoAudit() == null){
                info.setDimasukOleh(pengguna);
                info.setTarikhMasuk(new java.util.Date());
            }else{
                info.setDimasukOleh(permohonanRujukanLuar.getInfoAudit().getDimasukOleh());
                info.setTarikhMasuk(permohonanRujukanLuar.getInfoAudit().getTarikhMasuk());
            }
            permohonanRujukanLuar.setInfoAudit(info);
            permohonanRujukanLuarDAO.saveOrUpdate(permohonanRujukanLuar);
            result = "success";
        } catch (Exception ex) {
            result = "ProsesRemisyen Rollback. permohonan, permohonanRujukanLuar Ex: " + ex;
            LOG.error("ProsesRemisyen Rollback. permohonan, permohonanRujukanLuar Ex: " + ex);
        }
        LOG.info("permohonan, permohonanRujukanLuar:finish");
        return result;
    }

    //save data for class MaklumatLainActionBean
    @Transactional
    public String saveOrUpdate(DasarTuntutanNotis dasarTuntutanNotis, Pengguna pengguna, String kodNegeri) {
        LOG.info("dasarTuntutanNotis:start");
        String result = "";
        InfoAudit info = new InfoAudit();
        info.setDiKemaskiniOleh(pengguna);
        info.setTarikhKemaskini(new java.util.Date());
        String idNotis = String.valueOf(dasarTuntutanNotis.getIdNotis());
        LOG.debug("idNotis :" + idNotis);
        try {
            if (dasarTuntutanNotis.getIdNotis() == 0) {
                info.setDimasukOleh(pengguna);
                info.setTarikhMasuk(new java.util.Date());
                Long seqIdNotis = Long.parseLong(generatorIdNotis.generate(kodNegeri, pengguna.getKodCawangan(), dasarTuntutanNotis));
                dasarTuntutanNotis.setIdNotis(seqIdNotis);
                dasarTuntutanNotis.setInfoAudit(info);
                dasarTuntutanNotisDAO.save(dasarTuntutanNotis);
                result = "simpan";
                LOG.debug(result);
            } else {
                info.setDimasukOleh(dasarTuntutanNotis.getInfoAudit().getDimasukOleh());
                info.setTarikhMasuk(dasarTuntutanNotis.getInfoAudit().getTarikhMasuk());
                dasarTuntutanNotis.setInfoAudit(info);
                dasarTuntutanNotisDAO.update(dasarTuntutanNotis);
                result = "update";
                LOG.debug(result);
            }
        } catch (Exception ex) {
            result = "ProsesRemisyen Rollback. dasarTuntutanNotis Ex: " + ex;
            LOG.error("ProsesRemisyen Rollback. dasarTuntutanNotis Ex: " + ex);
        }
        LOG.info("dasarTuntutanNotis:finish");
        return result;
    }

    //update table mohon_hakmilik for cukai baru and create idPermohonan for module Pendaftaran
//    @Transactional
    public Boolean updateAndCreateIdPermohonan(BigDecimal cukaiBaru, KodUrusan kodUrusan, Pengguna pengguna, Permohonan permohonan) {
        LOG.info("updateAndCreate:start");
        Session ses = sessionProvider.get();
        Transaction tx = ses.beginTransaction();
        Boolean result = true;
        Permohonan permohonanDaftar = new Permohonan();
        InfoAudit info = new InfoAudit();
        info.setDiKemaskiniOleh(pengguna);
        info.setTarikhKemaskini(new java.util.Date());
        try {
            HakmilikPermohonan hp = permohonan.getSenaraiHakmilik().get(0);
//            hp.setId(permohonan.getSenaraiHakmilik().get(0).getId());
            LOG.debug("updateAndCreate:permohonan.getSenaraiHakmilik().get(0).getId() = " + permohonan.getSenaraiHakmilik().get(0).getId());
            hp.setCukaiBaru(cukaiBaru);
            info.setDimasukOleh(hp.getInfoAudit().getDimasukOleh());
            info.setTarikhMasuk(hp.getInfoAudit().getTarikhMasuk());
            hp.setInfoAudit(info);
            hakmilikPermohonanDAO.update(hp);
            String[] name = {"idHakmilik"};
            Object[] value = {permohonan.getSenaraiHakmilik().get(0).getHakmilik().getIdHakmilik()};
            senaraiHakmilik = hakmilikDAO.findByEqualCriterias(name, value, null);
            permohonanDaftar = gipw.genWorkflowIdPermohonan(kodUrusan, pengguna, senaraiHakmilik, permohonan);
            if(!tx.isActive())
                tx = ses.beginTransaction();
//            LOG.debug("permohonanDaftar.senaraiHakmilik.size :"+permohonanDaftar.getSenaraiHakmilik().size());
            String[] pname = {"permohonan"};
            Object[] pvalue = {permohonanDaftar};
            List<HakmilikPermohonan> senaraiHakmilikDaftar = hakmilikPermohonanDAO.findByEqualCriterias(pname, pvalue, null);
            LOG.debug("senaraiHakmilikDaftar.size :"+senaraiHakmilikDaftar.size());
            HakmilikPermohonan hpDaftar = senaraiHakmilikDaftar.get(0);
            hpDaftar.setCukaiBaru(cukaiBaru);
            info.setDimasukOleh(hpDaftar.getInfoAudit().getDimasukOleh());
            info.setTarikhMasuk(hpDaftar.getInfoAudit().getTarikhMasuk());
            hpDaftar.setInfoAudit(info);
            hakmilikPermohonanDAO.update(hpDaftar);
            KodRujukan kr = new KodRujukan();
            kr.setKod("FL"); //Fail
            PermohonanRujukanLuar prlDaftar = new PermohonanRujukanLuar();
            prlDaftar.setCawangan(permohonan.getCawangan());
            prlDaftar.setKodRujukan(kr);
            LOG.debug("permohonanDaftar.idMohon :"+permohonanDaftar.getIdPermohonan());
            prlDaftar.setPermohonan(permohonanDaftar);
            prlDaftar.setNoFail(permohonan.getIdPermohonan());
            prlDaftar.setInfoAudit(info);
            permohonanRujukanLuarDAO.saveOrUpdate(prlDaftar);
            LOG.debug("prlDaftar.permohonan :"+prlDaftar.getPermohonan().getIdPermohonan());
            LOG.debug("updateAndCreate:senaraiHakmilik = " + senaraiHakmilik.size());
            tx.commit();
        } catch (Exception ex) {
            tx.rollback();
            result = false;
            LOG.error("ProsesRemisyen Rollback. updateAndCreate Ex: " + ex);
            ex.printStackTrace();
        }
        return result;
    }

    public List<Hakmilik> getSenaraiHakmilik() {
        return senaraiHakmilik;
    }

    public void setSenaraiHakmilik(List<Hakmilik> senaraiHakmilik) {
        this.senaraiHakmilik = senaraiHakmilik;
    }

    //save or update for mapping KodSekolahBantuanModal
    @Transactional
    public String saveOrUpdateKodSekolahBantuan(KodSekolahBantuanModal ksbm, Pengguna pengguna) {
        LOG.info("KodSekolahBantuanModal:start");
        String result = null;
        InfoAudit info = new InfoAudit();
        info.setDiKemaskiniOleh(pengguna);
        info.setTarikhKemaskini(new java.util.Date());
        try {
                info.setDimasukOleh(pengguna);
                info.setTarikhMasuk(new java.util.Date());
                ksbm.setInfoAudit(info);
                kodSekolahBantuanModalDAO.saveOrUpdate(ksbm);
                result = "save";
            LOG.debug("KodSekolahBantuanModal.kod : " + ksbm.getKod());
        } catch (TransactionException tex) {
            result = tex.toString();
            LOG.error("KodSekolahBantuanModal ex: " + tex);
            tex.printStackTrace();
        }catch(NullPointerException npe){
            LOG.error("KodSekolahBantuanModal ex: " + npe);
            npe.printStackTrace();
        }
        LOG.info("KodSekolahBantuanModal:finish");
        return result;
    }

    /********** all delete method ************/
    @Transactional
    public Boolean deleteKodBantuanSekolah(KodSekolahBantuanModal kodSekolahBantuanModal){
        boolean result = false;
        try{
            kodSekolahBantuanModalDAO.delete(kodSekolahBantuanModal);
            LOG.info("deleteKodBantuanSekolah: success");
            result = true;
        }catch(Exception ex){
            LOG.error("deleteKodBantuanSekolah: ex: "+ex);
            result = false;
        }
        return result;
    }

    //delete for mapping HakmilikAlamat
    @Transactional
    public Boolean deleteHakmilikAlamat(HakmilikAlamat hakmilikAlamat){
        boolean result = false;
        try{
            hakmilikAlamatDAO.delete(hakmilikAlamat);
            LOG.info("deleteHakmilikAlamat: success");
            result = true;
        }catch(Exception ex){
            LOG.error("deleteHakmilikAlamat: ex: "+ex);
            result = false;
        }
        return result;
    }
}
