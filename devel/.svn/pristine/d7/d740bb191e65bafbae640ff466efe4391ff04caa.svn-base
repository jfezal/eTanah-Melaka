/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package etanah.service.common;

/**
 *
 * @author massita
 */

import etanah.model.Permohonan;
import java.util.List;

import org.hibernate.Query;
import com.google.inject.Inject;
import com.wideplay.warp.persist.Transactional;
import etanah.dao.BorangQDAO;
import etanah.dao.FolderDokumenDAO;
import etanah.dao.LaporanPemulihanTanahDAO;
import etanah.dao.LaporanTandaSempadanDAO;
import etanah.dao.PermohonanDAO;
import etanah.dao.SyaratPendudukanDAO;
import etanah.model.BorangQ;
import etanah.model.InfoAudit;
import etanah.model.KandunganFolder;
import etanah.model.LaporanPemulihanTanah;
import etanah.model.LaporanTandaSempadan;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.model.PermohonanKertasKandungan;
import etanah.model.SyaratPendudukan;
import etanah.report.ReportUtil;
import etanah.service.RegService;
import etanah.service.common.PermohonanPihakService;
import etanah.view.etanahActionBeanContext;
import java.util.Date;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class PendudukanSementaraService {
    
    private static final Logger logger = Logger.getLogger(RegService.class);
    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;
    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    FolderDokumenDAO folderDokumenDAO;
    @Inject
    SyaratPendudukanDAO syaratPendudukanDAO;
    @Inject
    BorangQDAO borangQDAO;
    @Inject
    LaporanTandaSempadanDAO laporanTandaSempadanDAO;
    @Inject
    LaporanPemulihanTanahDAO laporanPemulihanTanahDAO;

    Permohonan permohonan;
    SyaratPendudukan syaratPendudukan;
    BorangQ borangQ;
    LaporanTandaSempadan laporanTandaSempadan;
    LaporanPemulihanTanah laporanPemulihanTanah;


    public BorangQ findByIdPermohonan4BorangQ(String idPermohonan) {
        String query = "SELECT b FROM etanah.model.BorangQ b where b.permohonan.idPermohonan = :idPermohonan";
        return (BorangQ) sessionProvider.get().createQuery(query).setString("idPermohonan", idPermohonan).uniqueResult();
    }

    public LaporanTandaSempadan findByIdPermohonan4LaporanTandaSempadan(String idPermohonan) {
        String query = "SELECT b FROM etanah.model.LaporanTandaSempadan b where b.permohonan.idPermohonan = :idPermohonan";
        return (LaporanTandaSempadan) sessionProvider.get().createQuery(query).setString("idPermohonan", idPermohonan).uniqueResult();
    }

    public LaporanPemulihanTanah findByIdPermohonan4LaporanPemulihanTanah(String idPermohonan) {
        String query = "SELECT b FROM etanah.model.LaporanPemulihanTanah b where b.permohonan.idPermohonan = :idPermohonan";
        return (LaporanPemulihanTanah) sessionProvider.get().createQuery(query).setString("idPermohonan", idPermohonan).uniqueResult();
    }




    @Transactional
    public BorangQ saveBorangQ(BorangQ borangQ){
       return borangQDAO.saveOrUpdate(borangQ);
    }

    @Transactional
    public void saveLaporanTandaSempadanDAO(LaporanTandaSempadan laporanTandaSempadan){
       laporanTandaSempadanDAO.save(laporanTandaSempadan);
    }

    @Transactional
    public void saveLaporanPemulihanTanahDAO(LaporanPemulihanTanah laporanPemulihanTanah){
       laporanPemulihanTanahDAO.save(laporanPemulihanTanah);
    }
}
