/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.stripes.pelupusan.validator;

import com.google.inject.Inject;
import etanah.dao.KodCawanganDAO;
import etanah.dao.KodPerananDAO;
import etanah.dao.PermohonanDAO;
import etanah.model.FasaPermohonan;
import etanah.model.Hakmilik;
import etanah.model.HakmilikPermohonan;
import etanah.model.InfoAudit;
import etanah.model.KodBangsa;
import etanah.model.KodCawangan;
import etanah.model.Notifikasi;
import etanah.model.Pengguna;
import org.hibernate.Session;
import etanah.model.KodPeranan;
import etanah.model.KodUrusan;
import etanah.model.Pemohon;
import etanah.model.PemohonHubungan;
import etanah.model.Permit;
import etanah.model.Permohonan;
import etanah.model.Pihak;
import etanah.model.PihakPengarah;
import etanah.service.NotifikasiService;
import etanah.service.PelupusanService;
import etanah.service.common.FasaPermohonanService;
import etanah.service.common.PemohonService;
import etanah.service.common.PihakService;
import etanah.view.ListUtil;
import etanah.view.etanahContextListener;
import etanah.view.kaunter.ProsesTukarGanti;
import etanah.view.stripes.pelupusan.disClass.DisLaporanTanahService;
import java.util.ArrayList;
import java.util.Date;
import etanah.workflow.StageContext;
import etanah.workflow.StageListener;
import etanah.workflow.WorkFlowService;
import java.util.List;
import org.apache.log4j.Logger;
import org.apache.commons.lang.StringUtils;

/**
 *
 * @author wan.fairul
 */
public class MaklumanPermohonanValidator implements StageListener {

    private static final Logger LOG = Logger.getLogger(MaklumanPermohonanValidator.class);
    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;
    @Inject
    KodPerananDAO kodPerananDAO;
    @Inject
    NotifikasiService notifikasiService;
    @Inject
    PihakService pihakService;
    @Inject
    PemohonService pemohonService;
    @Inject
    PelupusanService pelupusanService;
    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    DisLaporanTanahService disLaporanTanahService;
    @Inject
    KodCawanganDAO kodCawanganDAO;
    @Inject
    ListUtil listUtil;
    @Inject
    FasaPermohonanService fasaPermohonanService;
    @Inject
    etanah.Configuration conf;
    private Pihak pihak;
    private Pemohon pemohon;
    private List<Pemohon> pemohonList;
    private List<PihakPengarah> pihakPengarahList;
    private List<PemohonHubungan> pemohonHubunganList;
    private List<KodBangsa> senaraiKodBangsa;
    private PemohonHubungan pemohonHubunganListBapa;
    private PemohonHubungan pemohonHubunganListIbu;
    private FasaPermohonan fasaPermohonan;

    @Override
    public boolean beforeStart(StageContext context) {
//        throw new UnsupportedOperationException("Not supported yet.");
        return true;
    }

    @Override
    public boolean beforeGenerateReports(StageContext ctx) {
        return true;
    }

    @Override
    public void onGenerateReports(StageContext context) {
    }

    @Override
    public String beforeComplete(StageContext context, String proposedOutcome) {

//        try {
        Pengguna peng = (Pengguna) context.getPengguna();
        List<Hakmilik> senaraiHakmilik = new ArrayList<Hakmilik>();
        Permohonan permohonan = context.getPermohonan();
        if (!(permohonan.getKodUrusan().getKod().equals("PTGSA"))) {
            if (permohonan.getSebab() == null) {
                context.addMessage("Sila Masukkan Tujuan Permohonan");
                return null;
            }
        }

        List<HakmilikPermohonan> senaraiMohonHakmilik = new ArrayList<HakmilikPermohonan>();
        senaraiMohonHakmilik = permohonan.getSenaraiHakmilik();
        if (senaraiMohonHakmilik.size() <= 0) {
            context.addMessage("Sila Buat Kemasukan Terlebih Dahulu dan SEMAK KEMASUKAN YANG DIBUAT sebelum dihantar ke peringkat seterusnya.Kerjasama Anda Amat dihargai. Terima Kasih");
            return null;
        } else {
            for (HakmilikPermohonan hm : senaraiMohonHakmilik) {
                List<FasaPermohonan> mohonFasaLIst = fasaPermohonanService.findStatusDESC(senaraiMohonHakmilik.get(0).getPermohonan().getIdPermohonan());
//                if (!mohonFasaLIst.get(0).getIdAliran().equals("01Kmskn")) {
                LOG.info("TESTING");
                if (!permohonan.getKodUrusan().getKod().equals("PHLP")) {
                    if (!permohonan.getKodUrusan().getKod().equals("PTGSA")) {
                        if (hm.getLuasTerlibat() == null) {
                            context.addMessage("Sila Masukan Luas Terlebih Dahulu Sebelum Dihantar Keperingkat Seterusnya.Kerjasama Anda Amat dihargai. Terima Kasih");
                            return null;
                        }
                    }
                }
            }
        }

        if (!(permohonan.getKodUrusan().getKod().equals("PHLP")) && !(permohonan.getKodUrusan().getKod().equals("PTGSA"))) {
            for (HakmilikPermohonan hm : senaraiMohonHakmilik) {
                LOG.info("hm.getKodUnitLuas() -- " + hm.getKodUnitLuas());
                if (hm.getKodUnitLuas() == null) {
                    context.addMessage("Sila Masukkan Luas Dipohon dan Kod Unit Luas di Tab Tanah.");
                    return null;
                }
            }
        }

//        pemohon = pelupusanService.findPemohonByIdPemohon(permohonan.getIdPermohonan());
        pemohonList = pelupusanService.findPemohonByIdPemohonList(permohonan.getIdPermohonan());

        for (Pemohon p : pemohonList) {
            if (p == null) {
                context.addMessage("Sila Masukkan Maklumat Pemohon");
                return null;
            } else {
                Pihak pihak = p.getPihak();
                if (pihak == null) {
                    context.addMessage("Sila Masukkan Maklumat Pemohon");
                    return null;
                } else if (context.getPermohonan().getKodUrusan().getKod().equals("PRIZ") || context.getPermohonan().getKodUrusan().getKod().equals("PBRZ")) {
                    if (pihak == null) {
                        context.addMessage("Sila Masukkan Maklumat Pemohon");
                        return null;
                    }
                } else if (context.getPermohonan().getKodUrusan().getKod().equals("PBRZ")) {
                    if (pihak == null) {
                        context.addMessage("Sila Masukkan Maklumat Pemohon");
                        return null;
                    }
                } else {
                    //U,D,S
                    if (pihak.getJenisPengenalan() != null) {
                        if (pihak.getJenisPengenalan().getKod().equals("U") || pihak.getJenisPengenalan().getKod().equals("D")
                                || pihak.getJenisPengenalan().getKod().equals("S")) {
                            if (!context.getPermohonan().getKodUrusan().getKod().equals("LMCRG")) {
                                pihakPengarahList = pihakService.findPengarahByIDPihak(pihak.getIdPihak());
                                senaraiKodBangsa = listUtil.getSenaraiKodBangsaByJenisPengenalanNotIn(pihak.getJenisPengenalan().getKod());
                                LOG.info("kod bangsa = " + senaraiKodBangsa.size());

                                int count = 0;
                                for (KodBangsa kb : senaraiKodBangsa) {
                                    if (pihak.getBangsa() != null) {
                                        if (kb.getKod().equals(pihak.getBangsa().getKod())) {
                                            count++;
                                        }
                                    }

                                }
                                if (count == 0) {
                                    if (pihakPengarahList.isEmpty()) {
                                        context.addMessage("Sila Masukkan Maklumat Ahli Lembaga Pengarah");
                                        return null;
                                    }
                                }
                            }
                        }
                    }

                    if (pihak.getJenisPengenalan() != null) {
                        if (pihak.getJenisPengenalan().getKod().equals("B") || pihak.getJenisPengenalan().getKod().equals("L")
                                || pihak.getJenisPengenalan().getKod().equals("P") || pihak.getJenisPengenalan().getKod().equals("T")
                                || pihak.getJenisPengenalan().getKod().equals("I") || pihak.getJenisPengenalan().getKod().equals("O")
                                || pihak.getJenisPengenalan().getKod().equals("N") || pihak.getJenisPengenalan().getKod().equals("F")) {

                            pemohonHubunganList = pelupusanService.findHubunganByIDPemohon(p.getIdPemohon());

                            // maklumat suami/isteri is mandatory for PBMT and PLPS
                            if (context.getPermohonan().getKodUrusan().getKod().equals("PBMT") || context.getPermohonan().getKodUrusan().getKod().equals("PLPS")) {
                                if (!StringUtils.isEmpty(p.getStatusKahwin()) && p.getStatusKahwin().equals("Berkahwin")) {
                                    if (pemohonHubunganList.isEmpty()) {
                                        context.addMessage("Sila Masukkan Maklumat Suami/Isteri");
                                        return null;
                                    }
                                }
                            }

                            // maklumat ibubapa is manadatory
                            if (context.getPermohonan().getKodUrusan().getKod().equals("PBMT")) {
                                pemohonHubunganListBapa = pelupusanService.findHubunganByIDPemohonBAPA2(p.getIdPemohon());

                                //                        if (pemohonHubunganListBapa == null) {
                                //                            System.out.println("baba tiada");
                                ////                            if(!pemohonHubunganListBapa.get(0).getKaitan().equals("BAPA")){
                                //                            context.addMessage("Sila Masukkan Maklumat Keluarga Ibu dan Bapa");
                                //                            return null;
                                ////                            }
                                //                        }
                                pemohonHubunganListIbu = pelupusanService.findHubunganByIDPemohonIBU(p.getIdPemohon());
                                if (pemohonHubunganListIbu == null || pemohonHubunganListBapa == null) {
                                    System.out.println("ibu tiada");
                                    //                            if(!pemohonHubunganListIbu.get(0).getKaitan().equals("IBU")){
                                    context.addMessage("Sila Masukkan Maklumat Ibu dan Bapa");
                                    return null;
                                    //                            }
                                }

                            }
                        }
                    }

                }

            }

        }

        if (permohonan.getKodUrusan().getKod().equals("PJLB")) {
            if (permohonan.getIdPermohonan() != null) {
                if (permohonan.getPermohonanSebelum() != null) {
                    Permit permit = pelupusanService.findPermitByIdPermohonan(permohonan.getPermohonanSebelum().getIdPermohonan());
                    //Permohonan permohonanRef = pelupusanService.findIdPermohonan(idPermohonan);
                    if (permit == null) {
                        context.addMessage("Sila Masukkan Maklumat Lesen Terdahulu");
                        return null;
                    } else {
                        Permohonan permohonanSebelum = !StringUtils.isBlank(permohonan.getPermohonanSebelum().getIdPermohonan()) ? permohonanDAO.findById(permohonan.getPermohonanSebelum().getIdPermohonan()) : new Permohonan();
                        if (permohonan != null) {
                            if (permohonanSebelum != null) {
                                Pengguna p = context.getPengguna();
                                updateMohonHakmilik(permohonan, p);
                            }
                        }
                    }
                } else {
                    context.addMessage("Sila Masukkan Maklumat Lesen Terdahulu");
                    return null;
                }
            }

        }

        // start coding to send application notification to required users/roles
        if (!context.getPermohonan().getKodUrusan().getKod().equals("LMCRG") && !context.getPermohonan().getKodUrusan().getKod().equals("PJLB")) {
            Notifikasi n = new Notifikasi();
            if (context.getPermohonan().getKodUrusan().getKod().equals("PBMT")) {
                n.setTajuk("Makluman Kemasukan Permohonan Pemberimilikan Tanah Kerajaan (Seksyen 76 KTN)");
            } else if (context.getPermohonan().getKodUrusan().getKod().equals("PLPS")) {
                n.setTajuk("Makluman Kemasukan Permohonan Lesen Pendudukan Sementara (Seksyen 65 KTN)");
            } else if (context.getPermohonan().getKodUrusan().getKod().equals("LPSP")) {
                n.setTajuk("Makluman Kemasukan Permohonan Lesen Pendudukan Sementara dan Permit (Seksyen 69 KTN)");
            } else if (context.getPermohonan().getKodUrusan().getKod().equals("PPBB")) {
                n.setTajuk("Makluman Kemasukan Permohonan Permit Bahan Batuan (Kelulusan Ketua Menteri)");
            } else if (context.getPermohonan().getKodUrusan().getKod().equals("PBPTD")) {
                n.setTajuk("Makluman Kemasukan Permohonan Permit Bahan Batuan (Kelulusan PTD)");
            } else if (context.getPermohonan().getKodUrusan().getKod().equals("PBPTG")) {
                n.setTajuk("Makluman Kemasukan Permohonan Permit Bahan Batuan (Kelulusan PTG)");
            } else if (context.getPermohonan().getKodUrusan().getKod().equals("PPRU")) {
                n.setTajuk("Makluman Kemasukan Permohonan Permit Menggunakan Ruang Udara");
            } else if (context.getPermohonan().getKodUrusan().getKod().equals("PRMP")) {
                n.setTajuk("Makluman Kemasukan Permohonan Permit Tanah Pertanian");
            } else if (context.getPermohonan().getKodUrusan().getKod().equals("LMCRG")) {
                n.setTajuk("Makluman Kemasukan Permohonan Lesen Mencarigali/Penjelajahan");
            } else if (context.getPermohonan().getKodUrusan().getKod().equals("PPTR")) {
                n.setTajuk("Makluman Kemasukan Permohonan Memajak Tanah Rizab");
            } else if (context.getPermohonan().getKodUrusan().getKod().equals("PTGSA")) {
                n.setTajuk("Makluman Kemasukan Permohonan Penamatan Tanah GSA");
            } else if (context.getPermohonan().getKodUrusan().getKod().equals("PHLP")) {
                n.setTajuk("Makluman Kemasukan Permohonan Hak Lalulalang Pentadbir Tanah");
            } else if (context.getPermohonan().getKodUrusan().getKod().equals("PRIZ")) {
                n.setTajuk("Makluman Kemasukan Permohonan Perizaban (Kuasa PTD)");
            } else if (context.getPermohonan().getKodUrusan().getKod().equals("PRMMK")) {
                n.setTajuk("Makluman Kemasukan Permohonan Perizaban (Kelulusan MMK)");
            } else if (context.getPermohonan().getKodUrusan().getKod().equals("PBRZ")) {
                n.setTajuk("Makluman Kemasukan Permohonan Pembatalan Perizaban");
            } else if (context.getPermohonan().getKodUrusan().getKod().equals("BMBT")) {
                n.setTajuk("Makluman Kemasukan Permohonan Pemberimilikan Stratum Tanah Bawah Tanah [Subseksyen 92D(1)(b)KTN]");
            } else if (context.getPermohonan().getKodUrusan().getKod().equals("PJBTR")) {
                n.setTajuk("Makluman Kemasukan Permohonan Pajakan Stratum Tanah Bawah Tanah [Subseksyen 92f(1)a & 92f(1)b KTN]");
            }
            n.setMesej(context.getPermohonan().getIdPermohonan() + " - " + context.getPermohonan().getKodUrusan().getNama() + " telah dihantar kepada Pentadbir Tanah untuk makluman");
            Pengguna p = context.getPengguna();
            n.setCawangan(p.getKodCawangan());
            ArrayList<KodPeranan> list = new ArrayList<KodPeranan>();
            list.add(kodPerananDAO.findById("225"));
            InfoAudit ia = new InfoAudit();
            ia.setDimasukOleh(p);
            ia.setTarikhMasuk(new Date());
            n.setInfoAudit(ia);
            notifikasiService.addRolesToNotifikasi(n, p.getKodCawangan(), list);
            if (context.getPermohonan().getKodUrusan().getKod().equals("PHLP") || context.getPermohonan().getKodUrusan().getKod().equals("PPBB") || context.getPermohonan().getKodUrusan().getKod().equals("PBMT") || context.getPermohonan().getKodUrusan().getKod().equals("PPTR")) {
                context.addMessage(" : Makluman Kemasukan Permohonan kepada Penolong Pegawai Tanah (Kanan) Telah Dihantar.");
            } else {
                context.addMessage(" : Makluman Kemasukan Permohonan kepada Pentadbir Tanah Telah Dihantar.");
            }
        }

        if (!senaraiHakmilik.isEmpty()) {
            //Need To generate before Noting
            LOG.info("buat urusan tukar ganti");
            for (Hakmilik h : senaraiHakmilik) {
                if (h.getNoVersiDhde() == 0) {
                    prosesTukarGanti(peng, senaraiHakmilik);

                }
            }
        }

        // send notification to Pentadbir Tanah
//        } catch (Exception e) {
//            LOG.error(e.getMessage());
//            return null;
//        }
//        return null;
        return proposedOutcome;
    }

    private void prosesTukarGanti(Pengguna pengguna, List<Hakmilik> senaraiHakmilik) {

        //urusan tukar ganti
        ProsesTukarGanti tukarGanti = etanahContextListener.newInstance(ProsesTukarGanti.class);
        String kodNegeri = conf.getProperty("kodNegeri");
        KodCawangan caw = null;
        if (!senaraiHakmilik.isEmpty() && ((senaraiHakmilik.get(0).getKodHakmilik().getKod().equals("GRN") || senaraiHakmilik.get(0).getKodHakmilik().getKod().equals("PN") || senaraiHakmilik.get(0).getKodHakmilik().getKod().equals("HSD")))) {
            caw = kodCawanganDAO.findById("00");
        } else {
            caw = pengguna.getKodCawangan();
        }

        if (!senaraiHakmilik.isEmpty()) {

            List<Permohonan> senaraiPermohonanTukarGanti = tukarGanti.doTukarGanti(kodNegeri, pengguna, senaraiHakmilik);
            if (!senaraiPermohonanTukarGanti.isEmpty()) {
                for (Permohonan p : senaraiPermohonanTukarGanti) {
                    KodUrusan ku = p.getKodUrusan();
                    try {
                        WorkFlowService.initiateTask(ku.getIdWorkflow(),
                                p.getIdPermohonan(), caw.getKod(), pengguna.getIdPengguna(),
                                p.getKodUrusan().getNama());

                        //fikri suruh pakai getidworkflow yang biasa
//                        WorkFlowService.initiateTask(ku.getIdWorkflowIntegration(),
//                            p.getIdPermohonan(), caw.getKod(), pengguna.getIdPengguna(),
//                            p.getKodUrusan().getNama());
                    } catch (Exception e) {
                        LOG.error(e);
                    }
                }
            }
        }
    }

    private void updateMohonHakmilik(Permohonan permohonan, Pengguna p) {

        List<HakmilikPermohonan> senaraiMohonHakmilik = new ArrayList<HakmilikPermohonan>();
        senaraiMohonHakmilik = permohonan.getPermohonanSebelum().getSenaraiHakmilik();
        if (!senaraiMohonHakmilik.isEmpty()) {
            for (HakmilikPermohonan mohonHakmilik : senaraiMohonHakmilik) {
                HakmilikPermohonan mohonHakmilikSave = new HakmilikPermohonan();
                mohonHakmilikSave.setPermohonan(permohonan);
                mohonHakmilikSave.setHakmilik(mohonHakmilik.getHakmilik());
                mohonHakmilikSave.setLuasTerlibat(mohonHakmilik.getLuasTerlibat());
                mohonHakmilikSave.setLuasDiluluskan(mohonHakmilik.getLuasDiluluskan());
                mohonHakmilikSave.setLuasUkurHalus(mohonHakmilik.getLuasUkurHalus());
                mohonHakmilikSave.setLuasPelanB1(mohonHakmilik.getLuasPelanB1());
                mohonHakmilikSave.setKodUnitLuas(mohonHakmilik.getKodUnitLuas());
                mohonHakmilikSave.setLuasUkurHalusUom(mohonHakmilik.getLuasUkurHalusUom());
                mohonHakmilikSave.setLuasLulusUom(mohonHakmilik.getLuasLulusUom());
                mohonHakmilikSave.setLuasPelanB1Uom(mohonHakmilik.getLuasPelanB1Uom());
                mohonHakmilikSave.setKodSeksyen(mohonHakmilik.getKodSeksyen());
                mohonHakmilikSave.setBandarPekanMukimBaru(mohonHakmilik.getBandarPekanMukimBaru());
                mohonHakmilikSave.setKodHakmilik(mohonHakmilik.getKodHakmilik());
                mohonHakmilikSave.setLot(mohonHakmilik.getLot());
                mohonHakmilikSave.setNoLot(mohonHakmilik.getNoLot());
                mohonHakmilikSave.setNoPajakan(mohonHakmilik.getNoPajakan());
                mohonHakmilikSave.setKategoriTanahBaru(mohonHakmilik.getKategoriTanahBaru());
                mohonHakmilikSave.setSyaratNyataBaru(mohonHakmilik.getSyaratNyataBaru());
                mohonHakmilikSave.setSekatanKepentinganBaru(mohonHakmilik.getSekatanKepentinganBaru());
                mohonHakmilikSave.setKadarCukaiBaru(mohonHakmilik.getKadarCukaiBaru());
                mohonHakmilikSave.setCukaiBaru(mohonHakmilik.getCukaiBaru());
                mohonHakmilikSave.setLokasi(mohonHakmilik.getLokasi());
                mohonHakmilikSave.setJarak(mohonHakmilik.getJarak());
                mohonHakmilikSave.setUnitJarak(mohonHakmilik.getUnitJarak());
                mohonHakmilikSave.setJarakDari(mohonHakmilik.getJarakDari());
                mohonHakmilikSave.setJarakDariNama(mohonHakmilik.getJarakDariNama());
                mohonHakmilikSave.setNomborRujukan(mohonHakmilik.getNomborRujukan());
                mohonHakmilikSave.setKodMilik(mohonHakmilik.getKodMilik());
                mohonHakmilikSave.setTarikhAwalDaftarGeran(mohonHakmilik.getTarikhAwalDaftarGeran());
                mohonHakmilikSave.setTempohPajakan(mohonHakmilik.getTempohPajakan());
                mohonHakmilikSave.setKodHakmilikTetap(mohonHakmilik.getKodHakmilikTetap());
                mohonHakmilikSave.setKodHakmilikSementara(mohonHakmilik.getKodHakmilikSementara());
                mohonHakmilikSave.setTempohHakmilik(mohonHakmilik.getTempohHakmilik());
                mohonHakmilikSave.setCukaiPerMeterPersegi(mohonHakmilik.getCukaiPerMeterPersegi());
                mohonHakmilikSave.setCukaiPerLot(mohonHakmilik.getCukaiPerLot());
                mohonHakmilikSave.setKadarPremium(mohonHakmilik.getKadarPremium());
                mohonHakmilikSave.setDendaPremium(mohonHakmilik.getDendaPremium());
                mohonHakmilikSave.setJenisPenggunaanTanah(mohonHakmilik.getJenisPenggunaanTanah());
                mohonHakmilikSave.setSyaratNyata(mohonHakmilik.getSyaratNyata());
                mohonHakmilikSave.setSekatanKepentingan(mohonHakmilik.getSekatanKepentingan());
                mohonHakmilikSave.setNilaianJpph(mohonHakmilik.getNilaianJpph());
                mohonHakmilikSave.setCatatan(mohonHakmilik.getCatatan());
                mohonHakmilikSave.setHubunganHakmilik(mohonHakmilik.getHubunganHakmilik());
                mohonHakmilikSave.setDokumen1(mohonHakmilik.getDokumen1());
                mohonHakmilikSave.setDokumen2(mohonHakmilik.getDokumen2());
                mohonHakmilikSave.setDokumen3(mohonHakmilik.getDokumen3());
                mohonHakmilikSave.setDokumen4(mohonHakmilik.getDokumen4());
                mohonHakmilikSave.setDokumen5(mohonHakmilik.getDokumen5());
                mohonHakmilikSave.setDokumen6(mohonHakmilik.getDokumen6());
                mohonHakmilikSave.setKosInfra(mohonHakmilik.getKosInfra());
                mohonHakmilikSave.setTanahDiambil(mohonHakmilik.getTanahDiambil());
                mohonHakmilikSave.setKeteranganInfra(mohonHakmilik.getKeteranganInfra());
                mohonHakmilikSave.setKeteranganCukaiBaru(mohonHakmilik.getKeteranganCukaiBaru());
                mohonHakmilikSave.setKeteranganKadarPremium(mohonHakmilik.getKeteranganKadarPremium());
                mohonHakmilikSave.setKodKegunaanTanah(mohonHakmilik.getKodKegunaanTanah());
                mohonHakmilikSave.setKeteranganKadarUkur(mohonHakmilik.getKeteranganKadarUkur());
                mohonHakmilikSave.setKeteranganKadarDaftar(mohonHakmilik.getKeteranganKadarDaftar());
                mohonHakmilikSave.setJarakDariKediaman(mohonHakmilik.getJarakDariKediaman());
                mohonHakmilikSave.setJarakDariKediamanUom(mohonHakmilik.getJarakDariKediamanUom());
                mohonHakmilikSave.setAgensiUpahUkur(mohonHakmilik.getAgensiUpahUkur());
                mohonHakmilikSave.setStatusLuasDiluluskan(mohonHakmilik.getStatusLuasDiluluskan());
                mohonHakmilikSave.setPenjenisan(mohonHakmilik.getPenjenisan());
                mohonHakmilikSave.setNoUnitPetak(mohonHakmilik.getNoUnitPetak());
                mohonHakmilikSave.setPegangan(mohonHakmilik.getPegangan());
                mohonHakmilikSave.setKodDUN(mohonHakmilik.getKodDUN());
                mohonHakmilikSave.setKodKawasanParlimen(mohonHakmilik.getKodKawasanParlimen());
                mohonHakmilikSave.setTempohPengosongan(mohonHakmilik.getTempohPengosongan());
                mohonHakmilikSave.setInfoAudit(disLaporanTanahService.findAudit(mohonHakmilikSave, "new", p));
                disLaporanTanahService.getPlpservice().simpanHakmilikPermohonan(mohonHakmilikSave);
            }
        }

    }

    @Override
    public void afterComplete(StageContext sc) {
//        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void afterPushBack(StageContext context) {
//        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public String beforePushBack(StageContext context, String proposedOutcome) {
        return proposedOutcome;
    }
}
