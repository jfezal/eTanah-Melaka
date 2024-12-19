/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.lelong.validator;

import com.google.inject.Inject;
import etanah.dao.KodDokumenDAO;
import etanah.dao.KodNotisDAO;
import etanah.model.*;
import etanah.service.common.LelongService;
import etanah.workflow.StageContext;
import etanah.workflow.StageListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.log4j.Logger;

/**
 *
 * @author ${md.izzat}
 */
public class MohonTangguhValidator implements StageListener {

    private static final Logger LOG = Logger.getLogger(MohonTangguhValidator.class);
    @Inject
    LelongService lelongService;
    @Inject
    KodDokumenDAO kodDokumenDAO;
    @Inject
    KodNotisDAO kodNotisDAO;
    @Inject
    etanah.Configuration conf;

    @Override
    public boolean beforeStart(StageContext sc) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean beforeGenerateReports(StageContext sc) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void onGenerateReports(StageContext sc) {
//        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public String beforeComplete(StageContext context, String proposedOutcome) {
        Permohonan permohonan = context.getPermohonan();
        String idPermohonan = permohonan.getIdPermohonan();

        List<Permohonan> listPermohonan = lelongService.getListPPTL(idPermohonan);
        if (!listPermohonan.isEmpty()) {
            LOG.info("----PPTL----");
            context.addMessage(idPermohonan + " - Terdapat Permohonan Tangguh 16O Pada Permohonan Ini. Urusan Tidak Dapat Diteruskan.");
            return null;
        }

        List<Permohonan> listPermohonan2 = lelongService.getListPPBL(idPermohonan);
        if (!listPermohonan2.isEmpty()) {
            LOG.info("----PPBL----");
            context.addMessage(idPermohonan + " - Terdapat Permohonan Pembatalan 16O Pada Permohonan Ini. Urusan Tidak Dapat Diteruskan.");
            return null;
        }
//
//        List<Permohonan> listPermohonan = lelongService.getListPPBLPPTL(permohonan.getIdPermohonan());
//        if (!listPermohonan.isEmpty()) {
//            context.addMessage(permohonan.getIdPermohonan() + " - Terdapat Permohonan Tangguh Pada Permohonan Ini.Urusan Tidak Dapat Diteruskan.");
//            return null;
//        }
        FasaPermohonan fasa;
        Pengguna pguna = context.getPengguna();
        List<FasaPermohonan> fasaList = lelongService.findFasaPermohonanMohonTangguhList(permohonan.getIdPermohonan());
        fasa = fasaList.get(0);
//        if (fasa.getKeputusan().getKod().equals("AG")) {
//            FolderDokumen fd = permohonan.getFolderDokumen();
//            List<KandunganFolder> listKD = lelongService.getListDokumen(fd.getFolderId());
//            if (!listKD.isEmpty()) {
//                KodDokumen kodD = null;
//                for (KandunganFolder kf : listKD) {
//                    if (kf.getDokumen().getKodDokumen().getKod().equals("SSL")) {
//                        kodD = kodDokumenDAO.findById("SSLM");
//                        Dokumen d = kf.getDokumen();
//                        d.setKodDokumen(kodD);
//                        lelongService.saveOrUpdatDokumen(d);
//                        kf.setDokumen(d);
//                        lelongService.saveOrUpdate(kf);
//                    }
//                }
//            }
//        }
        Enkuiri enkuiri = lelongService.findEnkuiri(permohonan.getIdPermohonan());

        if (fasa.getKeputusan().getKod().equals("AG")) {
            LOG.info("---AG--");
            if (enkuiri != null) {
                if (enkuiri.getTarikhEnkuiri() == null) {
                    context.addMessage(permohonan.getIdPermohonan() + " - Sila Masukkan Tarikh Enkuiri Di Tab Maklumat Keputusan");
                    return null;
                }
            } else {
                context.addMessage(permohonan.getIdPermohonan() + " - Sila Masukkan Tarikh Enkuiri Di Tab Maklumat Keputusan");
                return null;
            }
        }
        if ("04".equals(conf.getProperty("kodNegeri"))) {
            LOG.info("Negeri : " + conf.getProperty("kodNegeri"));
            if (fasa.getKeputusan().getKod().equals("AG")) {
                if (enkuiri != null) {
                    if (enkuiri.getTarikhEnkuiri() == null) {
                        context.addMessage(permohonan.getIdPermohonan() + " - Sila Masukkan Tarikh Enkuiri Di Tab Maklumat Keputusan");
                        return null;
                    }
                } else {
                    context.addMessage(permohonan.getIdPermohonan() + " - Sila Masukkan Tarikh Enkuiri Di Tab Maklumat Keputusan");
                    return null;
                }
                simpanNotis(permohonan, pguna);
            }

            if (fasa.getKeputusan().getKod().equals("WR")) {
                if (enkuiri != null) {
                    if (enkuiri.getTarikhEnkuiri() == null) {
                        context.addMessage(permohonan.getIdPermohonan() + " - Sila Masukkan Tarikh Enkuiri Di Tab Maklumat Keputusan");
                        return null;
                    }
                } else {
                    context.addMessage(permohonan.getIdPermohonan() + " - Sila Masukkan Tarikh Enkuiri Di Tab Maklumat Keputusan");
                    return null;
                }
                simpanNotisNSL(permohonan, pguna);
            }
        }

        return fasa.getKeputusan().getKod();
    }

    public void simpanNotis(Permohonan permohonan, Pengguna pengguna) {

        LOG.info("---simpanNotis---");
        List<Notis> listNotis = lelongService.getListNotis2(permohonan.getIdPermohonan(), "SSL");
        if (!listNotis.isEmpty()) {
            KodNotis kodNotis = kodNotisDAO.findById("NSL");
            for (Notis nn : listNotis) {
                nn.setKodNotis(kodNotis);
                lelongService.saveOrUpdate(nn);
            }
        }

        List<KandunganFolder> listKandunganFolder = lelongService.getListDokumen(permohonan.getFolderDokumen().getFolderId());
        if (!listKandunganFolder.isEmpty()) {
            Dokumen dokumen = new Dokumen();
            for (KandunganFolder kk : listKandunganFolder) {
                dokumen = kk.getDokumen();
            }
            List<String> listIDHakmilik = new ArrayList<String>();
            for (HakmilikPermohonan hp : permohonan.getSenaraiHakmilik()) {
                listIDHakmilik.add(hp.getHakmilik().getIdHakmilik());
            }
            LOG.info("listIDHakmilik : " + listIDHakmilik.size());
            List<HakmilikPihakBerkepentingan> listHP = lelongService.getHakmilikPihakALL(permohonan.getIdPermohonan(), listIDHakmilik);
            Map<Long, HakmilikPihakBerkepentingan> pihakMap = new HashMap<Long, HakmilikPihakBerkepentingan>();
            for (HakmilikPihakBerkepentingan hp : listHP) {
                Long id = hp.getPihak().getIdPihak();
                if (pihakMap.containsKey(id)) {
                    continue;
                } else {
                    pihakMap.put(id, hp);
                }
            }
            List<HakmilikPihakBerkepentingan> senaraiHakmilikPihak = new ArrayList(pihakMap.values());
            LOG.info("senaraiPermohonanPihak : " + senaraiHakmilikPihak.size());

            if (dokumen.getKodDokumen() != null) {
                KodNotis kodNotis = new KodNotis();
                kodNotis = kodNotisDAO.findById("SSL");
                if (dokumen.getKodDokumen().getKod().equals("SSL")) {
                    List<PermohonanAtasPerserahan> listPAP = lelongService.getPermohonanAtasPerserahan(permohonan.getIdPermohonan());
                    PermohonanAtasPerserahan pAP = listPAP.get(0);
                    if (pAP.getUrusan().getKodUrusan().getKod().equals("GD") || pAP.getUrusan().getKodUrusan().getKod().equals("GDWM")) {
                        List<PermohonanPihak> listPP = lelongService.getSenaraiPmohonPihakPG(permohonan.getIdPermohonan());
                        for (int j = 0; j < senaraiHakmilikPihak.size(); j++) {
                            //Selain Pemilik.PG dan lain2
                            if (!senaraiHakmilikPihak.get(j).getJenis().getKod().equals("PM")) {
                                PermohonanPihak pPihak = null;
                                long idPihak = senaraiHakmilikPihak.get(j).getPihak().getIdPihak();
                                for (PermohonanPihak pp : listPP) {
                                    if (pp.getPihak().getIdPihak() == idPihak) {
                                        pPihak = pp;
                                        break;
                                    }
                                }
                                LOG.info("senaraiPermohonanPihak name : " + senaraiHakmilikPihak.get(j).getPihak().getNama());
                                InfoAudit info = pengguna.getInfoAudit();
                                info.setDimasukOleh(pengguna);
                                info.setTarikhMasuk(new java.util.Date());
                                Notis notis2 = new Notis();
                                notis2.setPermohonan(permohonan);
                                notis2.setInfoAudit(info);
                                if (pPihak != null) {
                                    notis2.setPihak(pPihak);
                                }
                                notis2.setTarikhNotis(new java.util.Date());
                                notis2.setKodNotis(kodNotis);
                                notis2.setHakmilikPihakBerkepentingan(senaraiHakmilikPihak.get(j));
                                notis2.setDokumenNotis(dokumen);
                                notis2.setJabatan(permohonan.getKodUrusan().getJabatan());
                                lelongService.saveOrUpdate(notis2);
                            }
                        }
                        //Penggadai Sahaja
                        Map<Long, PermohonanPihak> pihakMap2 = new HashMap<Long, PermohonanPihak>();
                        listPP = lelongService.getSenaraiPmohonPihakPM(permohonan.getIdPermohonan());
                        if (!listPP.isEmpty()) {
                            for (PermohonanPihak hp : listPP) {
                                Long id = hp.getPihak().getIdPihak();
                                if (pihakMap2.containsKey(id)) {
                                    continue;
                                } else {
                                    pihakMap2.put(id, hp);
                                }
                            }
                            listPP = new ArrayList(pihakMap2.values());
                            for (PermohonanPihak pp : listPP) {
                                HakmilikPihakBerkepentingan hb = null;
                                long idPihak = pp.getPihak().getIdPihak();
                                for (int k = 0; k < senaraiHakmilikPihak.size(); k++) {
                                    if (senaraiHakmilikPihak.get(k).getPihak().getIdPihak() == idPihak) {
                                        hb = senaraiHakmilikPihak.get(k);
                                        break;
                                    }
                                }
                                InfoAudit info = pengguna.getInfoAudit();
                                info.setDimasukOleh(pengguna);
                                info.setTarikhMasuk(new java.util.Date());
                                Notis notis2 = new Notis();
                                notis2.setPermohonan(permohonan);
                                notis2.setInfoAudit(info);
                                notis2.setPihak(pp);
                                notis2.setTarikhNotis(new java.util.Date());
                                notis2.setKodNotis(kodNotis);
                                notis2.setHakmilikPihakBerkepentingan(hb);
                                notis2.setDokumenNotis(dokumen);
                                notis2.setJabatan(permohonan.getKodUrusan().getJabatan());
                                lelongService.saveOrUpdate(notis2);
                            }
                        }
                    }
                    //Untuk urusan GDPJ dan GDPJK hantar ke lain2 pihak dalam hakmilik pihak..GD hantar ke peggadai dan pemegang gadai sahaja
                    if (pAP.getUrusan().getKodUrusan().getKod().equals("GDPJ") || pAP.getUrusan().getKodUrusan().getKod().equals("GDPJK")) {
                        List<PermohonanPihak> listPP = lelongService.getSenaraiPmohonPihak(permohonan.getIdPermohonan());
                        for (int j = 0; j < senaraiHakmilikPihak.size(); j++) {
                            PermohonanPihak pPihak = null;
                            long idPihak = senaraiHakmilikPihak.get(j).getPihak().getIdPihak();
                            for (PermohonanPihak pp : listPP) {
                                if (pp.getPihak().getIdPihak() == idPihak) {
                                    pPihak = pp;
                                    break;
                                }
                            }
                            LOG.info("senaraiPermohonanPihak name : " + senaraiHakmilikPihak.get(j).getPihak().getNama());
                            InfoAudit info = pengguna.getInfoAudit();
                            info.setDimasukOleh(pengguna);
                            info.setTarikhMasuk(new java.util.Date());
                            Notis notis2 = new Notis();
                            notis2.setPermohonan(permohonan);
                            notis2.setInfoAudit(info);
                            if (pPihak != null) {
                                notis2.setPihak(pPihak);
                            }
                            notis2.setTarikhNotis(new java.util.Date());
                            notis2.setKodNotis(kodNotis);
                            notis2.setHakmilikPihakBerkepentingan(senaraiHakmilikPihak.get(j));
                            notis2.setDokumenNotis(dokumen);
                            notis2.setJabatan(permohonan.getKodUrusan().getJabatan());
                            lelongService.saveOrUpdate(notis2);
                        }
                    }
                } else {
                    LOG.info("lain-lain..");
                }
            }
        }
    }

    public void simpanNotisNSL(Permohonan permohonan, Pengguna pengguna) {

        LOG.info("---simpanNotisNSL---");
        List<Notis> listNotis = lelongService.getListNotis2(permohonan.getIdPermohonan(), "SSL");
        if (!listNotis.isEmpty()) {
            KodNotis kodNotis = kodNotisDAO.findById("NSL");
            for (Notis nn : listNotis) {
                nn.setKodNotis(kodNotis);
                lelongService.saveOrUpdate(nn);
            }
        }
    }

    @Override
    public void afterComplete(StageContext sc) {
//        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public String beforePushBack(StageContext sc, String string) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void afterPushBack(StageContext sc) {
//        throw new UnsupportedOperationException("Not supported yet.");
    }
}
