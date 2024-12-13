package etanah.view.stripes.lelong;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.dao.PermohonanDAO;
import etanah.model.Enkuiri;
import etanah.model.FasaPermohonan;
import etanah.model.Hakmilik;
import etanah.model.HakmilikPermohonan;
import etanah.model.HakmilikUrusan;
import etanah.model.InfoAudit;
import etanah.model.KodCawangan;
import etanah.model.KodStatusEnkuiri;
import etanah.model.Lelongan;
import etanah.model.Pemohon;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.model.PermohonanAsal;
import etanah.model.PermohonanAtasPerserahan;
import etanah.model.PermohonanPihak;
import etanah.model.PermohonanRujukanLuar;
import etanah.model.Pihak;
import etanah.service.common.EnkuiriService;
import etanah.service.common.LelongService;
import etanah.service.BPelService;
import etanah.service.common.FasaPermohonanService;
import org.apache.commons.lang.StringUtils;
import etanah.view.etanahActionBeanContext;
import oracle.bpel.services.workflow.task.model.Task;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.UrlBinding;
import org.apache.log4j.Logger;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.controller.LifecycleStage;

/**
 *
 * @author mazurahayati.yusop, nur.aqilah
 */
@UrlBinding("/lelong/maklumat_pemohon")
public class MaklumatPemohonLActionBean extends AbleActionBean {

    private static final Logger logger = Logger.getLogger(MaklumatPemohonLActionBean.class);
    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    EnkuiriService enService;
    @Inject
    LelongService lelongService;
    @Inject
    private etanah.Configuration conf;
    @Inject
    FasaPermohonanService fasaPermohonanService;
    private String idHakmilik2;
    private String idPermohonan;
    private Permohonan permohonan;
    private List<PermohonanPihak> senaraiPermohonanPihak3;
    private boolean pemegangGadai;
    private boolean display;
    private List<Pihak> listPihak;
    private List<String> listIDHakmilik;
    private List<String> listNoPerserahan;
    private List<Permohonan> listPermohonan = new ArrayList<Permohonan>();
    private List<Permohonan> addListMohon = new ArrayList<Permohonan>();
    private List<HakmilikPermohonan> addPermohonan = new ArrayList<HakmilikPermohonan>();
    private List<HakmilikUrusan> addCheckUrusan;
    private List<PermohonanRujukanLuar> addCheckPermohonanRujukanLuar;
    private List<PermohonanRujukanLuar> addCheckPermohonanRujukanLuar2;
    private List<Lelongan> listMohonAsal;
    private List<PermohonanAsal> listAsal;
    private List<PermohonanAtasPerserahan> listPap;
    private List<HakmilikUrusan> addCheckDaftarHakMilikUrusan;
    private List<HakmilikUrusan> addCheckDaftarHakMilikUrusan2;
    private List<Permohonan> checkUrusanMohon;
    private List<Permohonan> listMohon;
    private List<HakmilikPermohonan> getIdMohon;
    private List<HakmilikPermohonan> getIdMohon2;
    private Pengguna pengguna;
    private List<PermohonanPihak> listPemohon;
    private String taskId;
    private String stageId;
    private String keputusan;
    private String kodNegeri;
    private String idPerserahan;
    private String idPerserahan2;
    private String idPerserahan3;

    @DefaultHandler
    public Resolution showForm() {
        logger.info("Masuk showForm");
        display = true;

        //added by nur.aqilah
        //check lelong status AK ada ke tak id mohon tangguh
        List<Lelongan> checkMohonTangguh = lelongService.findbyIdLelongAKList(permohonan);

        if (!checkMohonTangguh.isEmpty()) {
            logger.info(("Ada lelong berstatus AK"));

            Lelongan lelongAK = checkMohonTangguh.get(0);
            logger.info("Lelong Yg Aktif " + lelongAK.getIdLelong());

            if (lelongAK.getPermohonanTangguh() != null) {
                logger.info("Ada Urusan PPTL");

                FasaPermohonan ff = null;

                //check mohon fasa id aliran = semak16HLantikJurulelong
                List<FasaPermohonan> checkSemakLantikJurulelong = lelongService.checkSemakLantikJurulelong(permohonan);

                if (!checkSemakLantikJurulelong.isEmpty()) {
                    logger.info("Dah kt stage semak16HLantikJurulelong");

                    FasaPermohonan semakLantikJurulelong = checkSemakLantikJurulelong.get(0);

                    if (semakLantikJurulelong.getKeputusan() == null) {
                        logger.info("Keputusan Jurulelong Tak Pilih Lagi");
                        if ("05".equals(conf.getProperty("kodNegeri"))) {
                            logger.info("negeri sembilan");
                            ff = lelongService.findFasaKpsnTangguh(lelongAK.getPermohonanTangguh().getIdPermohonan());

                            //simpan keputusan pilih jurulelong dr urusan pptl
                            if (ff != null) {
                                logger.info("simpan keputusan pilih jurulelong dr urusan pptl");
                                semakLantikJurulelong.setKeputusan(ff.getKeputusan());
                                semakLantikJurulelong.setUlasan(ff.getUlasan());
                                semakLantikJurulelong.setInfoAudit(ff.getInfoAudit());
                                lelongService.saveUpdate2(semakLantikJurulelong);
                            }

                        } else {
                            logger.info("melaka");
                            ff = lelongService.findlantikanPelelong(lelongAK.getPermohonanTangguh().getIdPermohonan());

                            //simpan keputusan pilih jurulelong dr urusan pptl
                            if (ff != null) {
                                logger.info("simpan keputusan pilih jurulelong dr urusan pptl");
                                semakLantikJurulelong.setKeputusan(ff.getKeputusan());
                                semakLantikJurulelong.setUlasan(ff.getUlasan());
                                semakLantikJurulelong.setInfoAudit(ff.getInfoAudit());
                                lelongService.saveUpdate2(semakLantikJurulelong);
                            }

                        }

                    }
                }
            }

        }

        for (HakmilikPermohonan pp : permohonan.getSenaraiHakmilik()) {
            List<HakmilikPermohonan> listHakMohon = lelongService.getHakmilikbyID(pp.getHakmilik().getIdHakmilik());
            for (HakmilikPermohonan hh : listHakMohon) {
                if (hh.getPermohonan().getKeputusan() != null) {
                    if (hh.getPermohonan().getKeputusan().getKod().equals("RM")) {
                        addSimpleError("ID Hakmilik ini Telah Dirujuk Mahkamah.");
                        break;
                    }
                }
            }
        }
        //CONDITION 1//
        //check idmohon ada perintah mahkamah ke tak
        for (HakmilikPermohonan pp : permohonan.getSenaraiHakmilik()) {
            List<HakmilikUrusan> checkUrusan = lelongService.checkPMKMH(pp.getHakmilik().getIdHakmilik());
            addCheckUrusan = new ArrayList<HakmilikUrusan>();

            for (HakmilikUrusan hu : checkUrusan) {
                logger.info("" + hu.getIdUrusan() + "" + hu.getKodUrusan().getKod());
                addCheckUrusan.add(hu);
            }
        }

        //id perserahan utk CONDITION 1
        StringBuilder sb = new StringBuilder();

        if (!addCheckUrusan.isEmpty()) {

            for (HakmilikUrusan acu : addCheckUrusan) {
                if (acu == null) {
                    continue;
                }

                if (sb.length() > 0) {
                    sb.append(" , ");
                }
                sb.append(acu.getIdPerserahan());
            }
            idPerserahan = sb.toString();

            addSimpleError("Hakmilik Tersebut Mempunyai Perintah Mahkamah / Perintah Larangan. Sila Rujuk Perserahan " + idPerserahan);
            return new JSP("lelong/maklumat_pemohon.jsp").addParameter("tab", "true");
        }

        //CONDITION 2// (ada kt mhl, xda kt hu, ada urusan mahkamah)
        //check perintah mahkamah dah daftar ke belum
        for (HakmilikPermohonan pp : permohonan.getSenaraiHakmilik()) {

            //check id hakmilik ada ke tak dlm table mohon rujuk luar
            List<PermohonanRujukanLuar> checkPermohonanRujukanLuar = lelongService.checkPermohonanRujukanLuar(pp.getHakmilik().getIdHakmilik());
            addCheckPermohonanRujukanLuar = new ArrayList<PermohonanRujukanLuar>();

            for (PermohonanRujukanLuar prl : checkPermohonanRujukanLuar) {
                addCheckPermohonanRujukanLuar.add(prl);
            }
            logger.info("table mohon rujuk luar: " + addCheckPermohonanRujukanLuar.size());

            if (!addCheckPermohonanRujukanLuar.isEmpty()) {
                for (PermohonanRujukanLuar prl2 : addCheckPermohonanRujukanLuar) {
                    logger.info("Id Hakmilik " + prl2.getHakmilik().getIdHakmilik());

                    //check id hakmilik ada ke tak dlm table hakmilik urusan
                    List<HakmilikUrusan> checkDaftarHakMilikUrusan = lelongService.checkDaftarHakMilikUrusan(prl2.getHakmilik().getIdHakmilik());
                    addCheckDaftarHakMilikUrusan = new ArrayList<HakmilikUrusan>();
                    for (HakmilikUrusan hu : checkDaftarHakMilikUrusan) {
                        addCheckDaftarHakMilikUrusan.add(hu);
                    }

                    //check ada urusan perintah mahkamah ke takk
                    if (addCheckDaftarHakMilikUrusan.isEmpty()) {

                        //cari senarai id mohon yg ada perintah mahkamah bg id hakmilik 
                        getIdMohon = lelongService.findPermohonanIdHakmilikMahkamah(pp.getHakmilik().getIdHakmilik());
                        logger.info("belum di daftarkn: " + getIdMohon.size());

                    }
                }
            }
        }

        StringBuilder sb2 = new StringBuilder();

        //id perserahan utk CONDITION 2
        if (!addCheckPermohonanRujukanLuar.isEmpty()
                && addCheckDaftarHakMilikUrusan.isEmpty() && !getIdMohon.isEmpty()) {
            logger.info("Ada Perintah Mahkamah Tp Belum Di Daftarkan");

            for (HakmilikPermohonan p2 : getIdMohon) {
                if (p2 == null) {
                    continue;
                }

                if (sb2.length() > 0) {
                    sb2.append(" , ");
                }
                sb2.append(p2.getPermohonan().getIdPermohonan());
            }
            idPerserahan2 = sb2.toString();

            addSimpleError("Hakmilik Tersebut Mempunyai Perintah Mahkamah / Perintah Larangan. Sila Rujuk Perserahan  " + idPerserahan2 + " - belum didaftarkan");
            return new JSP("lelong/maklumat_pemohon.jsp").addParameter("tab", "true");
        }

        //CONDITION 3// (xda kt mhl, xda kt hu, ada urusan mahkamah)
        //check perintah mahkamah dah daftar ke belum
        //check id mohon (xda kt mohon rujuk luar & xda kt hak milik urusan)
        for (HakmilikPermohonan pp : permohonan.getSenaraiHakmilik()) {

            //check id hakmilik ada ke tak dlm table mohon rujuk luar
            List<PermohonanRujukanLuar> checkPermohonanRujukanLuar2 = lelongService.checkPermohonanRujukanLuar(pp.getHakmilik().getIdHakmilik());
            addCheckPermohonanRujukanLuar2 = new ArrayList<PermohonanRujukanLuar>();

            for (PermohonanRujukanLuar prl : checkPermohonanRujukanLuar2) {
                addCheckPermohonanRujukanLuar2.add(prl);
            }
            logger.info("table mohon rujuk luar: " + addCheckPermohonanRujukanLuar2.size());
            if (addCheckPermohonanRujukanLuar2.isEmpty()) {

                //check id hakmilik ada ke tak dlm hak milik urusan
                List<HakmilikUrusan> checkHakMilikUrusan2 = lelongService.checkDaftarHakMilikUrusan(pp.getHakmilik().getIdHakmilik());
                addCheckDaftarHakMilikUrusan2 = new ArrayList<HakmilikUrusan>();

                for (HakmilikUrusan hu : checkHakMilikUrusan2) {
                    addCheckDaftarHakMilikUrusan2.add(hu);
                }

                if (addCheckDaftarHakMilikUrusan2.isEmpty()) {
                    //cari senarai id mohon yg ada perintah mahkamah bg id hakmilik 
                    getIdMohon2 = lelongService.findPermohonanIdHakmilikMahkamah(pp.getHakmilik().getIdHakmilik());

                    logger.info("belum di daftarkan " + getIdMohon2.size());

                }
            }
        }

        StringBuilder sb3 = new StringBuilder();

        //id perserahan utk CONDITION 3
        if (addCheckPermohonanRujukanLuar2.isEmpty()
                && addCheckDaftarHakMilikUrusan2.isEmpty() && !getIdMohon2.isEmpty()) {
            logger.info("Ada Perintah Mahkamah Tp Belum Di Daftarkan");

            for (HakmilikPermohonan p2 : getIdMohon2) {
                if (p2 == null) {
                    continue;
                }

                if (sb3.length() > 0) {
                    sb3.append(" , ");
                }
                sb3.append(p2.getPermohonan().getIdPermohonan());
            }
            idPerserahan3 = sb3.toString();

            addSimpleError("Hakmilik Tersebut Mempunyai Perintah Mahkamah / Perintah Larangan. Sila Rujuk Perserahan  " + idPerserahan3 + " - belum didaftarkan");
            return new JSP("lelong/maklumat_pemohon.jsp").addParameter("tab", "true");
        }

        return new JSP("lelong/maklumat_pemohon.jsp").addParameter("tab", "true");
    }

    public Resolution showForm2() {
        logger.info("showform2");
        display = false;
        for (HakmilikPermohonan pp : permohonan.getSenaraiHakmilik()) {
            List<HakmilikPermohonan> listHakMohon = lelongService.getHakmilikbyID(pp.getHakmilik().getIdHakmilik());
            for (HakmilikPermohonan hh : listHakMohon) {
                if (hh.getPermohonan().getKeputusan() != null) {
                    if (hh.getPermohonan().getKeputusan().getKod().equals("RM")) {
                        addSimpleError("ID Hakmilik ini Telah Dirujuk Mahkamah.");
                        break;
                    }
                }
            }
        }
        //CONDITION 1//
        //check idmohon ada perintah mahkamah ke tak
        for (HakmilikPermohonan pp : permohonan.getSenaraiHakmilik()) {
            List<HakmilikUrusan> checkUrusan = lelongService.checkPMKMH(pp.getHakmilik().getIdHakmilik());
            addCheckUrusan = new ArrayList<HakmilikUrusan>();

            for (HakmilikUrusan hu : checkUrusan) {
                logger.info("" + hu.getIdUrusan() + "" + hu.getKodUrusan().getKod());
                addCheckUrusan.add(hu);
            }
        }

        //id perserahan utk CONDITION 1
        StringBuilder sb = new StringBuilder();

        if (!addCheckUrusan.isEmpty()) {

            for (HakmilikUrusan acu : addCheckUrusan) {
                if (acu == null) {
                    continue;
                }

                if (sb.length() > 0) {
                    sb.append(" , ");
                }
                sb.append(acu.getIdPerserahan());
            }
            idPerserahan = sb.toString();

            addSimpleError("Hakmilik Tersebut Mempunyai Perintah Mahkamah / Perintah Larangan. Sila Rujuk Perserahan " + idPerserahan);
            return new JSP("lelong/maklumat_pemohon.jsp").addParameter("tab", "true");
        }

        //CONDITION 2// (ada kt mhl, xda kt hu, ada urusan mahkamah)
        //check perintah mahkamah dah daftar ke belum
        for (HakmilikPermohonan pp : permohonan.getSenaraiHakmilik()) {

            //check id hakmilik ada ke tak dlm table mohon rujuk luar
            List<PermohonanRujukanLuar> checkPermohonanRujukanLuar = lelongService.checkPermohonanRujukanLuar(pp.getHakmilik().getIdHakmilik());
            addCheckPermohonanRujukanLuar = new ArrayList<PermohonanRujukanLuar>();

            for (PermohonanRujukanLuar prl : checkPermohonanRujukanLuar) {
                addCheckPermohonanRujukanLuar.add(prl);
            }
            logger.info("table mohon rujuk luar: " + addCheckPermohonanRujukanLuar.size());

            if (!addCheckPermohonanRujukanLuar.isEmpty()) {
                for (PermohonanRujukanLuar prl2 : addCheckPermohonanRujukanLuar) {
                    logger.info("Id Hakmilik " + prl2.getHakmilik().getIdHakmilik());

                    //check id hakmilik ada ke tak dlm table hakmilik urusan
                    List<HakmilikUrusan> checkDaftarHakMilikUrusan = lelongService.checkDaftarHakMilikUrusan(prl2.getHakmilik().getIdHakmilik());
                    addCheckDaftarHakMilikUrusan = new ArrayList<HakmilikUrusan>();
                    for (HakmilikUrusan hu : checkDaftarHakMilikUrusan) {
                        addCheckDaftarHakMilikUrusan.add(hu);
                    }

                    //check ada urusan perintah mahkamah ke takk
                    if (addCheckDaftarHakMilikUrusan.isEmpty()) {

                        //cari senarai id mohon yg ada perintah mahkamah bg id hakmilik 
                        getIdMohon = lelongService.findPermohonanIdHakmilikMahkamah(pp.getHakmilik().getIdHakmilik());
                        logger.info("belum di daftarkn: " + getIdMohon.size());
                    }
                }
            }
        }

        StringBuilder sb2 = new StringBuilder();

        //id perserahan utk CONDITION 2
        if (!addCheckPermohonanRujukanLuar.isEmpty()
                && addCheckDaftarHakMilikUrusan.isEmpty() && !getIdMohon.isEmpty()) {
            logger.info("Ada Perintah Mahkamah Tp Belum Di Daftarkan");

            for (HakmilikPermohonan p2 : getIdMohon) {
                if (p2 == null) {
                    continue;
                }

                if (sb2.length() > 0) {
                    sb2.append(" , ");
                }
                sb2.append(p2.getPermohonan().getIdPermohonan());
            }
            idPerserahan2 = sb2.toString();

            addSimpleError("Hakmilik Tersebut Mempunyai Perintah Mahkamah / Perintah Larangan. Sila Rujuk Perserahan  " + idPerserahan2 + " - belum didaftarkan");
            return new JSP("lelong/maklumat_pemohon.jsp").addParameter("tab", "true");
        }

        //CONDITION 3// (xda kt mhl, xda kt hu, ada urusan mahkamah)
        //check perintah mahkamah dah daftar ke belum
        //check id mohon (xda kt mohon rujuk luar & xda kt hak milik urusan)
        for (HakmilikPermohonan pp : permohonan.getSenaraiHakmilik()) {

            //check id hakmilik ada ke tak dlm table mohon rujuk luar
            List<PermohonanRujukanLuar> checkPermohonanRujukanLuar2 = lelongService.checkPermohonanRujukanLuar(pp.getHakmilik().getIdHakmilik());
            addCheckPermohonanRujukanLuar2 = new ArrayList<PermohonanRujukanLuar>();

            for (PermohonanRujukanLuar prl : checkPermohonanRujukanLuar2) {
                addCheckPermohonanRujukanLuar2.add(prl);
            }
            logger.info("table mohon rujuk luar: " + addCheckPermohonanRujukanLuar2.size());
            if (addCheckPermohonanRujukanLuar2.isEmpty()) {

                //check id hakmilik ada ke tak dlm hak milik urusan
                List<HakmilikUrusan> checkHakMilikUrusan2 = lelongService.checkDaftarHakMilikUrusan(pp.getHakmilik().getIdHakmilik());
                addCheckDaftarHakMilikUrusan2 = new ArrayList<HakmilikUrusan>();

                for (HakmilikUrusan hu : checkHakMilikUrusan2) {
                    addCheckDaftarHakMilikUrusan2.add(hu);
                }

                if (addCheckDaftarHakMilikUrusan2.isEmpty()) {
                    //cari senarai id mohon yg ada perintah mahkamah bg id hakmilik 
                    getIdMohon2 = lelongService.findPermohonanIdHakmilikMahkamah(pp.getHakmilik().getIdHakmilik());

                    logger.info("belum di daftarkan " + getIdMohon2.size());

                }
            }
        }

        StringBuilder sb3 = new StringBuilder();

        //id perserahan utk CONDITION 3
        if (addCheckPermohonanRujukanLuar2.isEmpty()
                && addCheckDaftarHakMilikUrusan2.isEmpty() && !getIdMohon2.isEmpty()) {
            logger.info("Ada Perintah Mahkamah Tp Belum Di Daftarkan");

            for (HakmilikPermohonan p2 : getIdMohon2) {
                if (p2 == null) {
                    continue;
                }

                if (sb3.length() > 0) {
                    sb3.append(" , ");
                }
                sb3.append(p2.getPermohonan().getIdPermohonan());
            }
            idPerserahan3 = sb3.toString();

            addSimpleError("Hakmilik Tersebut Mempunyai Perintah Mahkamah / Perintah Larangan. Sila Rujuk Perserahan  " + idPerserahan3 + " - belum didaftarkan");
            return new JSP("lelong/maklumat_pemohon.jsp").addParameter("tab", "true");
        }

        return new JSP("lelong/maklumat_pemohon.jsp").addParameter("tab", "true");
    }

    public Resolution showForm3() {
        logger.info("showform3");
        display = true;
        for (HakmilikPermohonan pp : permohonan.getSenaraiHakmilik()) {
            List<HakmilikPermohonan> listHakMohon = lelongService.getHakmilikbyID(pp.getHakmilik().getIdHakmilik());
            for (HakmilikPermohonan hh : listHakMohon) {
                if (hh.getPermohonan().getKeputusan() != null) {
                    if (hh.getPermohonan().getKeputusan().getKod().equals("RM")) {
                        addSimpleError("ID Hakmilik ini Telah Dirujuk Mahkamah.");
                        break;
                    }
                }
            }
        }
        //CONDITION 1//
        //check idmohon ada perintah mahkamah ke tak
        for (HakmilikPermohonan pp : permohonan.getSenaraiHakmilik()) {
            List<HakmilikUrusan> checkUrusan = lelongService.checkPMKMH(pp.getHakmilik().getIdHakmilik());
            addCheckUrusan = new ArrayList<HakmilikUrusan>();

            for (HakmilikUrusan hu : checkUrusan) {
                logger.info("" + hu.getIdUrusan() + "" + hu.getKodUrusan().getKod());
                addCheckUrusan.add(hu);
            }
        }

        //id perserahan utk CONDITION 1
        StringBuilder sb = new StringBuilder();

        if (!addCheckUrusan.isEmpty()) {

            for (HakmilikUrusan acu : addCheckUrusan) {
                if (acu == null) {
                    continue;
                }

                if (sb.length() > 0) {
                    sb.append(" , ");
                }
                sb.append(acu.getIdPerserahan());
            }
            idPerserahan = sb.toString();

            addSimpleError("Hakmilik Tersebut Mempunyai Perintah Mahkamah / Perintah Larangan. Sila Rujuk Perserahan " + idPerserahan);
            return new JSP("lelong/maklumat_pemohon.jsp").addParameter("tab", "true");
        }

        //CONDITION 2// (ada kt mhl, xda kt hu, ada urusan mahkamah)
        //check perintah mahkamah dah daftar ke belum
        for (HakmilikPermohonan pp : permohonan.getSenaraiHakmilik()) {

            //check id hakmilik ada ke tak dlm table mohon rujuk luar
            List<PermohonanRujukanLuar> checkPermohonanRujukanLuar = lelongService.checkPermohonanRujukanLuar(pp.getHakmilik().getIdHakmilik());
            addCheckPermohonanRujukanLuar = new ArrayList<PermohonanRujukanLuar>();

            for (PermohonanRujukanLuar prl : checkPermohonanRujukanLuar) {
                addCheckPermohonanRujukanLuar.add(prl);
            }
            logger.info("table mohon rujuk luar: " + addCheckPermohonanRujukanLuar.size());

            if (!addCheckPermohonanRujukanLuar.isEmpty()) {
                for (PermohonanRujukanLuar prl2 : addCheckPermohonanRujukanLuar) {
                    logger.info("Id Hakmilik " + prl2.getHakmilik().getIdHakmilik());

                    //check id hakmilik ada ke tak dlm table hakmilik urusan
                    List<HakmilikUrusan> checkDaftarHakMilikUrusan = lelongService.checkDaftarHakMilikUrusan(prl2.getHakmilik().getIdHakmilik());
                    addCheckDaftarHakMilikUrusan = new ArrayList<HakmilikUrusan>();
                    for (HakmilikUrusan hu : checkDaftarHakMilikUrusan) {
                        addCheckDaftarHakMilikUrusan.add(hu);
                    }

                    //check ada urusan perintah mahkamah ke takk
                    if (addCheckDaftarHakMilikUrusan.isEmpty()) {

                        //cari senarai id mohon yg ada perintah mahkamah bg id hakmilik 
                        getIdMohon = lelongService.findPermohonanIdHakmilikMahkamah(pp.getHakmilik().getIdHakmilik());
                        logger.info("belum di daftarkn: " + getIdMohon.size());
                    }

                }
            }
        }

        StringBuilder sb2 = new StringBuilder();

        //id perserahan utk CONDITION 2
        if (!addCheckPermohonanRujukanLuar.isEmpty()
                && addCheckDaftarHakMilikUrusan.isEmpty() && !getIdMohon.isEmpty()) {
            logger.info("Ada Perintah Mahkamah Tp Belum Di Daftarkan");

            for (HakmilikPermohonan p2 : getIdMohon) {
                if (p2 == null) {
                    continue;
                }

                if (sb2.length() > 0) {
                    sb2.append(" , ");
                }
                sb2.append(p2.getPermohonan().getIdPermohonan());
            }
            idPerserahan2 = sb2.toString();

            addSimpleError("Hakmilik Tersebut Mempunyai Perintah Mahkamah / Perintah Larangan. Sila Rujuk Perserahan  " + idPerserahan2 + " - belum didaftarkan");
            return new JSP("lelong/maklumat_pemohon.jsp").addParameter("tab", "true");
        }

        //CONDITION 3// (xda kt mhl, xda kt hu, ada urusan mahkamah)
        //check perintah mahkamah dah daftar ke belum
        //check id mohon (xda kt mohon rujuk luar & xda kt hak milik urusan)
        for (HakmilikPermohonan pp : permohonan.getSenaraiHakmilik()) {

            //check id hakmilik ada ke tak dlm table mohon rujuk luar
            List<PermohonanRujukanLuar> checkPermohonanRujukanLuar2 = lelongService.checkPermohonanRujukanLuar(pp.getHakmilik().getIdHakmilik());
            addCheckPermohonanRujukanLuar2 = new ArrayList<PermohonanRujukanLuar>();

            for (PermohonanRujukanLuar prl : checkPermohonanRujukanLuar2) {
                addCheckPermohonanRujukanLuar2.add(prl);
            }
            logger.info("table mohon rujuk luar: " + addCheckPermohonanRujukanLuar2.size());
            if (addCheckPermohonanRujukanLuar2.isEmpty()) {

                //check id hakmilik ada ke tak dlm hak milik urusan
                List<HakmilikUrusan> checkHakMilikUrusan2 = lelongService.checkDaftarHakMilikUrusan(pp.getHakmilik().getIdHakmilik());
                addCheckDaftarHakMilikUrusan2 = new ArrayList<HakmilikUrusan>();

                for (HakmilikUrusan hu : checkHakMilikUrusan2) {
                    addCheckDaftarHakMilikUrusan2.add(hu);
                }

                if (addCheckDaftarHakMilikUrusan2.isEmpty()) {
                    //cari senarai id mohon yg ada perintah mahkamah bg id hakmilik 
                    getIdMohon2 = lelongService.findPermohonanIdHakmilikMahkamah(pp.getHakmilik().getIdHakmilik());

                    logger.info("belum di daftarkan " + getIdMohon2.size());

                }
            }
        }

        StringBuilder sb3 = new StringBuilder();

        //id perserahan utk CONDITION 3
        if (addCheckPermohonanRujukanLuar2.isEmpty()
                && addCheckDaftarHakMilikUrusan2.isEmpty() && !getIdMohon2.isEmpty()) {
            logger.info("Ada Perintah Mahkamah Tp Belum Di Daftarkan");

            for (HakmilikPermohonan p2 : getIdMohon2) {
                if (p2 == null) {
                    continue;
                }

                if (sb3.length() > 0) {
                    sb3.append(" , ");
                }
                sb3.append(p2.getPermohonan().getIdPermohonan());
            }
            idPerserahan3 = sb3.toString();

            addSimpleError("Hakmilik Tersebut Mempunyai Perintah Mahkamah / Perintah Larangan. Sila Rujuk Perserahan  " + idPerserahan3 + " - belum didaftarkan");
            return new JSP("lelong/maklumat_pemohon.jsp").addParameter("tab", "true");
        }

        return new JSP("lelong/maklumat_pemohon.jsp").addParameter("tab", "true");
    }

    public void save(String idPerserahan) {

        InfoAudit ia = new InfoAudit();
        ia.setDimasukOleh(pengguna);
        ia.setTarikhMasuk(new java.util.Date());

        List<HakmilikUrusan> listHakmilikUrusan = lelongService.getHakmilikUrusanByIdmohon(idPerserahan);

        Enkuiri enkuiri = lelongService.findEnkuiri(idPermohonan);

        KodCawangan kc = pengguna.getKodCawangan();
        if (enkuiri == null) {
            logger.info("----nulll-------");
            enkuiri = new Enkuiri();
            enkuiri.setBilanganKes(1);
            ia.setDimasukOleh(pengguna);
            ia.setTarikhMasuk(new java.util.Date());
        } else {
            logger.info("------tak null-------");
            ia.setDimasukOleh(pengguna);
            ia.setTarikhMasuk(new java.util.Date());
            ia.setDiKemaskiniOleh(pengguna);
            ia.setTarikhKemaskini(new java.util.Date());
        }
        enkuiri.setPermohonan(permohonan);
        enkuiri.setCawangan(kc);
        enkuiri.setInfoAudit(ia);
        enkuiri.setTujuanGadaian(listHakmilikUrusan.get(0).getKodUrusan().getNama());
        KodStatusEnkuiri kse = new KodStatusEnkuiri();
        kse.setKod("AK");
        enkuiri.setStatus(kse);
        enService.save(enkuiri);

        List<PermohonanAtasPerserahan> listPap2 = lelongService.getPermohonanAtasPerserahan(idPermohonan);
        if (!listPap2.isEmpty()) {
            for (PermohonanAtasPerserahan pap : listPap2) {
                lelongService.delete(pap);
            }
        }

        for (HakmilikPermohonan hp : permohonan.getSenaraiHakmilik()) {
            PermohonanAtasPerserahan pap = new PermohonanAtasPerserahan();
            pap.setPermohonan(permohonan);
            for (HakmilikUrusan hu : listHakmilikUrusan) {
                if (hu.getHakmilik().getIdHakmilik().equals(hp.getHakmilik().getIdHakmilik())) {
                    pap.setUrusan(hu);
                }
            }
            ia.setDimasukOleh(pengguna);
            ia.setTarikhMasuk(new java.util.Date());
            pap.setInfoAudit(ia);
            pap.setHakmilikPermohonan(hp);
            lelongService.saveOrUpdate(pap);
        }

        logger.debug("start simpan");
        List<PermohonanPihak> listPP2 = lelongService.getSenaraiPmohonPihak(idPermohonan);
        if (!listPP2.isEmpty()) {
            for (PermohonanPihak pp : listPP2) {
                lelongService.deleteAll(pp);
            }
        }
        //pemegang gadaian
         List<PermohonanPihak> listPP = lelongService.getSenaraiPmohonPihak(idPerserahan);
        logger.info("listPP : " + listPP.size());
        if (!listPP.isEmpty()) {
            long penggadaiKe = listPP.get(0).getPihak().getIdPihak();
            if ((penggadaiKe != 0)) {
                for (PermohonanPihak permohonanPihak : listPP) {
                    InfoAudit info = pengguna.getInfoAudit();
                    KodCawangan caw = pengguna.getKodCawangan();
                    PermohonanPihak permohonanP = new PermohonanPihak();
                    info.setDimasukOleh(pengguna);
                    info.setTarikhMasuk(new java.util.Date());
                    if (permohonanPihak.getSyerPembilang() != null) {
                        permohonanP.setSyerPembilang(permohonanPihak.getSyerPembilang());
                    }
                    if (permohonanPihak.getSyerPenyebut() != null) {
                        permohonanP.setSyerPenyebut(permohonanPihak.getSyerPenyebut());
                    }
                    permohonanP.setHakmilik(permohonanPihak.getHakmilik());
                    permohonanP.setInfoAudit(info);
                    permohonanP.setPihak(permohonanPihak.getPihak());
                    permohonanP.setJenis(permohonanPihak.getJenis());
                    permohonanP.setCawangan(caw);
                    permohonanP.setPermohonan(permohonan);
                    permohonanP.setNama(permohonanPihak.getNama());
                    permohonanP.setJenisPengenalan(permohonanPihak.getJenisPengenalan());
                    permohonanP.setNoPengenalan(permohonanPihak.getNoPengenalan());
                    permohonanP.setAlamat(permohonanPihak.getAlamat());
                    permohonanP.setAlamatSurat(permohonanPihak.getAlamatSurat());
                    lelongService.saveOrUpdate(permohonanP);
                }
            }
        }
        //penggadai
        List<Pemohon> listP = lelongService.getPemohon(idPerserahan);
        logger.info("listP : " + listP.size());
        if (!listP.isEmpty()) {
            for (Pemohon pemohon : listP) {
                long penggadaiKe = pemohon.getPihak().getIdPihak();
                if ((penggadaiKe != 0)) {
                    InfoAudit info = pengguna.getInfoAudit();
                    KodCawangan caw = pengguna.getKodCawangan();
                    PermohonanPihak permohonanP = new PermohonanPihak();
                    info.setDimasukOleh(pengguna);
                    info.setTarikhMasuk(new java.util.Date());
                    if (pemohon.getSyerPembilang() != null) {
                        permohonanP.setSyerPembilang(pemohon.getSyerPembilang());
                    }
                    if (pemohon.getSyerPenyebut() != null) {
                        permohonanP.setSyerPenyebut(pemohon.getSyerPenyebut());
                    }
                    permohonanP.setHakmilik(pemohon.getHakmilik());
                    permohonanP.setInfoAudit(info);
                    permohonanP.setPihak(pemohon.getPihak());
                    permohonanP.setJenis(pemohon.getJenis());
                    permohonanP.setCawangan(caw);
                    permohonanP.setPermohonan(permohonan);
                    permohonanP.setNama(pemohon.getNama());
                    permohonanP.setAlamat(pemohon.getAlamat());
                    permohonanP.setAlamatSurat(pemohon.getAlamatSurat());
                    permohonanP.setJenisPengenalan(pemohon.getJenisPengenalan());
                    permohonanP.setNoPengenalan(pemohon.getNoPengenalan());
                    lelongService.saveOrUpdate(permohonanP);
                }
            }
        }
        rehydrate();
    }

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        pengguna = (Pengguna) context.getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);

        kodNegeri = conf.getProperty("kodNegeri");
        taskId = (String) getContext().getRequest().getSession().getAttribute("taskId");
        logger.info("task id : " + taskId);

        BPelService service = new BPelService();

        if (StringUtils.isNotBlank(taskId)) {
            Task task = null;
            task = service.getTaskFromBPel(taskId, pengguna);
            if (task != null) {
                stageId = task.getSystemAttributes().getStage();
                logger.info("--------------stage Id BPEL ON--------------- : " + stageId);
            }
        } else {
            stageId = "semakPembida";
            logger.info("--------------stage Id BPEL OFF--------------- : " + stageId);
        }

        List<FasaPermohonan> senaraiFasa = fasaPermohonanService.findStatus(idPermohonan);
        System.out.println(" ---------------list senarai fasa---------" + senaraiFasa.size());
        for (FasaPermohonan fp : senaraiFasa) {

            if (fp.getIdAliran().equalsIgnoreCase(stageId)) {
                if (fp.getKeputusan() != null) {
                    keputusan = fp.getKeputusan().getKod();
                    logger.info("--------------keputusan untuk stage " + stageId + "--------------- : " + keputusan);
                }

            }
        }

        if (idPermohonan != null) {
            permohonan = permohonanDAO.findById(idPermohonan);

            List<String> list = new ArrayList<String>();
            //checking idmohon sebelum utk bnyk hakmilik
            if (permohonan.getPermohonanSebelum() == null) {
                for (HakmilikPermohonan hp : permohonan.getSenaraiHakmilik()) {
                    list.add(hp.getHakmilik().getIdHakmilik());
                }
                listPap = lelongService.getPermohonanAtasPerserahan(idPermohonan);

                Enkuiri enkuiri = lelongService.findEnkuiri(idPermohonan);
                if (enkuiri != null) {
                    listMohonAsal = lelongService.listMohonAsal3(enkuiri.getIdEnkuiri());
                    listAsal = lelongService.listMohonAsal(idPermohonan);
                }

                senaraiPermohonanPihak3 = new ArrayList<PermohonanPihak>();
                List<Pihak> listPP = lelongService.getSenaraiPmohonPihakDistinctPG(idPermohonan);
                if (!listPP.isEmpty()) {
                    for (Pihak oo : listPP) {
                        List<PermohonanPihak> ll = lelongService.getSenaraiPmohonPihakByHakmilikAktif(oo.getIdPihak(), idPermohonan);
                        if (ll.size() > 0) {
                            senaraiPermohonanPihak3.add(ll.get(0));
                        }

                    }
                }
            } else {
                List<PermohonanAsal> list2 = lelongService.listMohonAsal2(permohonan.getPermohonanSebelum().getIdPermohonan());
                if (list2.isEmpty()) {
                    for (HakmilikPermohonan hp : permohonan.getPermohonanSebelum().getSenaraiHakmilik()) {
                        list.add(hp.getHakmilik().getIdHakmilik());
                    }
                    listPap = lelongService.getPermohonanAtasPerserahan(permohonan.getPermohonanSebelum().getIdPermohonan());

                    Enkuiri enkuiri = lelongService.findEnkuiri(permohonan.getPermohonanSebelum().getIdPermohonan());
                    if (enkuiri != null) {
                        listMohonAsal = lelongService.listMohonAsal3(enkuiri.getIdEnkuiri());
                    }

                    senaraiPermohonanPihak3 = new ArrayList<PermohonanPihak>();
                    List<Pihak> listPP = lelongService.getSenaraiPmohonPihakDistinctPG(permohonan.getPermohonanSebelum().getIdPermohonan());
                    if (!listPP.isEmpty()) {
                        for (Pihak oo : listPP) {
                            List<PermohonanPihak> ll = lelongService.getSenaraiPmohonPihakByPihak(oo.getIdPihak(), permohonan.getPermohonanSebelum().getIdPermohonan());
                            senaraiPermohonanPihak3.add(ll.get(0));
                        }
                    }
                } else {
                    for (HakmilikPermohonan hp : list2.get(0).getIdPermohonanAsal().getSenaraiHakmilik()) {
                        list.add(hp.getHakmilik().getIdHakmilik());
                    }
                    listPap = lelongService.getPermohonanAtasPerserahan(list2.get(0).getIdPermohonanAsal().getIdPermohonan());

                    Enkuiri enkuiri = lelongService.findEnkuiri(list2.get(0).getIdPermohonanAsal().getIdPermohonan());
                    if (enkuiri != null) {
                        listMohonAsal = lelongService.listMohonAsal3(enkuiri.getIdEnkuiri());
                    }

                    senaraiPermohonanPihak3 = new ArrayList<PermohonanPihak>();
                    List<Pihak> listPP = lelongService.getSenaraiPmohonPihakDistinctPG(list2.get(0).getIdPermohonanAsal().getIdPermohonan());
                    if (!listPP.isEmpty()) {
                        for (Pihak oo : listPP) {
                            List<PermohonanPihak> ll = lelongService.getSenaraiPmohonPihakByPihak(oo.getIdPihak(), list2.get(0).getIdPermohonanAsal().getIdPermohonan());
                            senaraiPermohonanPihak3.add(ll.get(0));
                        }
                    }
                }
            }
//            
//            FasaPermohonan mohonFasa = fasaPermohonanService.findLastStage(idPermohonan);
//            
//            if (mohonFasa.getIdAliran().equals("semakanPermohonan")){
//                mohonFasa.setKeputusanUntuk(mohonFasa.getIdPengguna());
//                fasaPermohonanService.saveOrUpdate(mohonFasa);
//            }

            List<String> listHu = new ArrayList<String>();
            List<String> listHu2 = new ArrayList<String>();
            listHu = lelongService.listHakmilikUrusanPMG(list);
            if (listHu.isEmpty()) {
                listHu = lelongService.listHakmilikUrusan2(list);
                listHu2 = lelongService.listHakmilikUrusan3(listHu, list.size());
            } else {
                listHu2 = lelongService.listHakmilikUrusanPMG2(listHu, list.size());
            }
            //edit
            if (listHu2.size() < 1) {
                if (listPap.isEmpty()) {
                    save(listHu2.get(0));
                }
                pemegangGadai = true;
            } else {
                logger.info("----else----");
                //not finish yet
                if (!listHu2.isEmpty()) {
                    List<PermohonanPihak> listPemohon2 = lelongService.getPemohonByIdmohon(listHu2,list);
                    listPemohon = new ArrayList<PermohonanPihak>();
                    Map<String, String> pihakMap2 = new HashMap<String, String>();
                    for (PermohonanPihak hp : listPemohon2) {
                        String id = hp.getPermohonan().getIdPermohonan();
                        if (pihakMap2.containsKey(id)) {
                            continue;
                        } else {
                            pihakMap2.put(id, hp.getPermohonan().getIdPermohonan());
                            listPemohon.add(hp);
                        }
                    }
                    pemegangGadai = true;
                }
            }

            StringBuilder sb = new StringBuilder();
            if (permohonan != null) {
                if (permohonan.getSenaraiHakmilik() != null) {
                    for (HakmilikPermohonan hp : permohonan.getSenaraiHakmilik()) {
                        if (hp == null) {
                            continue;
                        }
                        Hakmilik h = hp.getHakmilik();
                        if (sb.length() > 0) {
                            sb.append("<br>");
                        }
                        sb.append(h.getIdHakmilik());
                    }
                }
            }
            idHakmilik2 = sb.toString();

            //sejarah permohonan
            for (HakmilikPermohonan oo : permohonan.getSenaraiHakmilik()) {
                List<HakmilikPermohonan> listhak = lelongService.getPermohonanByIdmohonPPJP(oo.getHakmilik().getIdHakmilik(), idPermohonan);
                logger.info("listhak : " + listhak.size());
                Map<String, HakmilikPermohonan> pihakMap2 = new HashMap<String, HakmilikPermohonan>();
                for (HakmilikPermohonan hp : listhak) {
                    String id = hp.getPermohonan().getIdPermohonan();
                    if (pihakMap2.containsKey(id)) {
                        continue;
                    } else {
                        pihakMap2.put(id, hp);
                    }
                }
                listhak = new ArrayList(pihakMap2.values());
                logger.info("listhak2 : " + listhak.size());
                if (!listhak.isEmpty()) {
                    listPermohonan = new ArrayList<Permohonan>();
                    for (HakmilikPermohonan hh : listhak) {
                        if (!hh.getPermohonan().getStatus().equals("AK") && !hh.getPermohonan().getStatus().equals("TA")) {
                            List<Permohonan> listFP = lelongService.getHistory2(hh.getPermohonan().getIdPermohonan());
                            listPermohonan.add(listFP.get(0));
                        }
                    }
                }
            }
        }
    }

    public Resolution simpanPG() {
        String idPihak = getContext().getRequest().getParameter("penggadai");
        save(idPihak);
        addSimpleMessage("Maklumat Telah Berjaya Disimpan");
        return new JSP("lelong/maklumat_pemohon.jsp").addParameter("tab", "true");
    }

    public Permohonan getPermohonan() {
        return permohonan;
    }

    public void setPermohonan(Permohonan permohonan) {
        this.permohonan = permohonan;
    }

    public String getIdPermohonan() {
        return idPermohonan;
    }

    public void setIdPermohonan(String idPermohonan) {
        this.idPermohonan = idPermohonan;
    }

    public boolean isPemegangGadai() {
        return pemegangGadai;
    }

    public void setPemegangGadai(boolean pemegangGadai) {
        this.pemegangGadai = pemegangGadai;
    }

    public boolean isDisplay() {
        return display;
    }

    public void setDisplay(boolean display) {
        this.display = display;
    }

    public String getIdHakmilik2() {
        return idHakmilik2;
    }

    public void setIdHakmilik2(String idHakmilik2) {
        this.idHakmilik2 = idHakmilik2;
    }

    public List<PermohonanPihak> getSenaraiPermohonanPihak3() {
        return senaraiPermohonanPihak3;
    }

    public void setSenaraiPermohonanPihak3(List<PermohonanPihak> senaraiPermohonanPihak3) {
        this.senaraiPermohonanPihak3 = senaraiPermohonanPihak3;
    }

    public List<Pihak> getListPihak() {
        return listPihak;
    }

    public void setListPihak(List<Pihak> listPihak) {
        this.listPihak = listPihak;
    }

    public List<String> getListIDHakmilik() {
        return listIDHakmilik;
    }

    public void setListIDHakmilik(List<String> listIDHakmilik) {
        this.listIDHakmilik = listIDHakmilik;
    }

    public List<Permohonan> getListPermohonan() {
        return listPermohonan;
    }

    public void setListPermohonan(List<Permohonan> listPermohonan) {
        this.listPermohonan = listPermohonan;
    }

    public List<String> getListNoPerserahan() {
        return listNoPerserahan;
    }

    public void setListNoPerserahan(List<String> listNoPerserahan) {
        this.listNoPerserahan = listNoPerserahan;
    }

    public List<PermohonanAtasPerserahan> getListPap() {
        return listPap;
    }

    public void setListPap(List<PermohonanAtasPerserahan> listPap) {
        this.listPap = listPap;
    }

    public Pengguna getPengguna() {
        return pengguna;
    }

    public void setPengguna(Pengguna pengguna) {
        this.pengguna = pengguna;
    }

    public List<PermohonanPihak> getListPemohon() {
        return listPemohon;
    }

    public void setListPemohon(List<PermohonanPihak> listPemohon) {
        this.listPemohon = listPemohon;
    }

    public String getKeputusan() {
        return keputusan;
    }

    public void setKeputusan(String keputusan) {
        this.keputusan = keputusan;
    }

    public String getKodNegeri() {
        return kodNegeri;
    }

    public void setKodNegeri(String kodNegeri) {
        this.kodNegeri = kodNegeri;
    }

    public String getStageId() {
        return stageId;
    }

    public void setStageId(String stageId) {
        this.stageId = stageId;
    }

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public List<Lelongan> getListMohonAsal() {
        return listMohonAsal;
    }

    public void setListMohonAsal(List<Lelongan> listMohonAsal) {
        this.listMohonAsal = listMohonAsal;
    }

    public List<PermohonanAsal> getListAsal() {
        return listAsal;
    }

    public void setListAsal(List<PermohonanAsal> listAsal) {
        this.listAsal = listAsal;
    }

    public String getIdPerserahan() {
        return idPerserahan;
    }

    public void setIdPerserahan(String idPerserahan) {
        this.idPerserahan = idPerserahan;
    }

    public List<HakmilikUrusan> getAddCheckDaftarHakMilikUrusan() {
        return addCheckDaftarHakMilikUrusan;
    }

    public void setAddCheckDaftarHakMilikUrusan(List<HakmilikUrusan> addCheckDaftarHakMilikUrusan) {
        this.addCheckDaftarHakMilikUrusan = addCheckDaftarHakMilikUrusan;
    }

    public String getIdPerserahan2() {
        return idPerserahan2;
    }

    public void setIdPerserahan2(String idPerserahan2) {
        this.idPerserahan2 = idPerserahan2;
    }

    public List<Permohonan> getCheckUrusanMohon() {
        return checkUrusanMohon;
    }

    public void setCheckUrusanMohon(List<Permohonan> checkUrusanMohon) {
        this.checkUrusanMohon = checkUrusanMohon;
    }

    public List<Permohonan> getAddListMohon() {
        return addListMohon;
    }

    public void setAddListMohon(List<Permohonan> addListMohon) {
        this.addListMohon = addListMohon;
    }

    public String getIdPerserahan3() {
        return idPerserahan3;
    }

    public void setIdPerserahan3(String idPerserahan3) {
        this.idPerserahan3 = idPerserahan3;
    }

    public List<HakmilikPermohonan> getAddPermohonan() {
        return addPermohonan;
    }

    public void setAddPermohonan(List<HakmilikPermohonan> addPermohonan) {
        this.addPermohonan = addPermohonan;
    }

    public List<Permohonan> getListMohon() {
        return listMohon;
    }

    public void setListMohon(List<Permohonan> listMohon) {
        this.listMohon = listMohon;
    }

    public List<PermohonanRujukanLuar> getAddCheckPermohonanRujukanLuar() {
        return addCheckPermohonanRujukanLuar;
    }

    public void setAddCheckPermohonanRujukanLuar(List<PermohonanRujukanLuar> addCheckPermohonanRujukanLuar) {
        this.addCheckPermohonanRujukanLuar = addCheckPermohonanRujukanLuar;
    }

    public List<HakmilikUrusan> getAddCheckUrusan() {
        return addCheckUrusan;
    }

    public void setAddCheckUrusan(List<HakmilikUrusan> addCheckUrusan) {
        this.addCheckUrusan = addCheckUrusan;
    }

    public List<HakmilikPermohonan> getGetIdMohon() {
        return getIdMohon;
    }

    public void setGetIdMohon(List<HakmilikPermohonan> getIdMohon) {
        this.getIdMohon = getIdMohon;
    }

    public List<PermohonanRujukanLuar> getAddCheckPermohonanRujukanLuar2() {
        return addCheckPermohonanRujukanLuar2;
    }

    public void setAddCheckPermohonanRujukanLuar2(List<PermohonanRujukanLuar> addCheckPermohonanRujukanLuar2) {
        this.addCheckPermohonanRujukanLuar2 = addCheckPermohonanRujukanLuar2;
    }

    public List<HakmilikUrusan> getAddCheckDaftarHakMilikUrusan2() {
        return addCheckDaftarHakMilikUrusan2;
    }

    public void setAddCheckDaftarHakMilikUrusan2(List<HakmilikUrusan> addCheckDaftarHakMilikUrusan2) {
        this.addCheckDaftarHakMilikUrusan2 = addCheckDaftarHakMilikUrusan2;
    }

    public List<HakmilikPermohonan> getGetIdMohon2() {
        return getIdMohon2;
    }

    public void setGetIdMohon2(List<HakmilikPermohonan> getIdMohon2) {
        this.getIdMohon2 = getIdMohon2;
    }
}
