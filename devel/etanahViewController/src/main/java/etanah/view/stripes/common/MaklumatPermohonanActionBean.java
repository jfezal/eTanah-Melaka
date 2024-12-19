/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.stripes.common;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.Configuration;
import etanah.dao.FolderDokumenDAO;
import etanah.dao.HakmilikDAO;
import etanah.dao.KandunganFolderDAO;
import etanah.dao.KodDokumenDAO;
import etanah.dao.KodKeputusanDAO;
import etanah.dao.KodKlasifikasiDAO;
import etanah.dao.KodUrusanDAO;
import etanah.dao.PermohonanDAO;
import etanah.dao.SejarahHakmilikDAO;
import etanah.model.Permohonan;
import etanah.service.ConsentPtdService;
import java.text.ParseException;
import java.util.List;
import etanah.service.daftar.NotaDaftarKodMap;
import java.util.logging.Level;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;
import org.apache.commons.lang.StringUtils;
import etanah.model.HakmilikPermohonan;
import etanah.model.FolderDokumen;
import etanah.model.KandunganFolder;
import etanah.model.KodDokumen;
import java.util.HashMap;
import java.util.Map;
import etanah.model.Dokumen;
import etanah.model.Hakmilik;
import etanah.model.HakmilikPihakBerkepentingan;
import etanah.model.HakmilikSebelumPermohonan;
import etanah.model.HakmilikAsalPermohonan;
import etanah.model.HakmilikUrusan;
import etanah.model.InfoAudit;
import etanah.model.KodRujukan;
import etanah.model.KodSekatanKepentingan;
import etanah.model.KodUrusan;
import etanah.model.Pengguna;
import etanah.model.PermohonanPembetulanHakmilik;
import etanah.model.PermohonanPihak;
import etanah.model.PermohonanRujukanLuar;
import etanah.model.PermohonanUrusan;
import etanah.model.PermohonanHubungan;
import etanah.service.PelupusanService;
import java.util.ArrayList;
import java.util.LinkedList;
import net.sourceforge.stripes.action.ErrorResolution;
import org.apache.log4j.Logger;
import etanah.service.RegService;
import etanah.service.StrataPtService;
import etanah.service.common.DokumenService;
import etanah.service.common.NotisPenerimaanService;
import etanah.service.common.HakmilikPermohonanService;
import etanah.service.common.HakmilikPihakKepentinganService;
import etanah.service.common.HakmilikUrusanService;
import etanah.service.daftar.PembetulanService;
import etanah.util.DMSUtil;
import etanah.view.etanahActionBeanContext;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.apache.commons.lang.ArrayUtils;
import etanah.model.KodKeputusan;
import etanah.service.BPelService;
import oracle.bpel.services.workflow.task.model.Task;
import etanah.model.FasaPermohonan;
import etanah.model.KodKlasifikasi;
import etanah.model.Pemohon;
import etanah.model.PermohonanAtasPerserahan;
import etanah.model.SejarahHakmilik;
import etanah.model.WakilKuasa;
import etanah.model.WakilKuasaPemberi;
import etanah.model.WakilKuasaPenerima;
import etanah.service.common.PemohonService;
import etanah.service.common.PermohonanHubunganService;
import etanah.service.common.PermohonanAtasPerserahanService;
import etanah.service.common.PermohonanPihakService;
import etanah.service.common.PermohonanRujukanLuarService;
import etanah.service.common.PermohonanService;
import etanah.service.daftar.PendaftaranSuratKuasaService;
import java.util.Calendar;
import java.util.HashSet;
import etanah.sequence.GeneratorIdPermohonan;
import etanah.sequence.GeneratorIdPerserahan;
import etanah.service.daftar.SuratWakilKuasaService;
import etanah.dao.KodKlasifikasiDAO;

/**
 *
 * @author md.nurfikri
 */
@UrlBinding("/common/maklumat_permohonan")
public class MaklumatPermohonanActionBean extends AbleActionBean {

    private static final Logger LOGGER = Logger.getLogger(MaklumatPermohonanActionBean.class);
    private static boolean isDebug = LOGGER.isDebugEnabled();
    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    HakmilikDAO hakmilikDAO;
    @Inject
    KodUrusanDAO kodUrusanDAO;
    @Inject
    ConsentPtdService conService;
    @Inject
    RegService regService;
    @Inject
    PermohonanService permohonanService;
    @Inject
    DokumenService dokumenService;
    @Inject
    NotisPenerimaanService notisPenerimaanService;
    @Inject
    private HakmilikPihakKepentinganService hakmilikPihakKepentinganService;
    @Inject
    HakmilikUrusanService hakmilikUrusanService;
    @Inject
    HakmilikPermohonanService hakmilikPermohonanService;
    @Inject
    RegService o_regService;
    @Inject
    PembetulanService pService;
    @Inject
    HakmilikPermohonanService hpService;
    //added
    @Inject
    StrataPtService strPtService;
    @Inject
    PelupusanService lupusService;
    @Inject
    KodKeputusanDAO kodKpsnDAO;
    @Inject
    Pemohon pemohon;
    @Inject
    PemohonService pemohonService;
    @Inject
    private Configuration conf;
    @Inject
    PermohonanPihakService permohonanPihakService;
    @Inject
    PermohonanRujukanLuarService permohonanRujukanLuarService;
    @Inject
    PermohonanAtasPerserahanService permohonanAtasPerserahanService;
    @Inject
    PermohonanHubunganService permohonanHubunganService;
    @Inject
    PendaftaranSuratKuasaService suratKuasaService;
    @Inject
    private SejarahHakmilikDAO sejarahHakmilikDAO;
    @Inject
    private SuratWakilKuasaService suratWakilKuasaService;
    @Inject
    private GeneratorIdPerserahan idPerserahanGenerator;
    @Inject
    private Configuration configuration;
    @Inject
    private FolderDokumenDAO folderDokumenDAO;
    @Inject
    private KandunganFolderDAO kandunganFolderDAO;
    @Inject
    private KodDokumenDAO kodDokumenDAO;
    @Inject
    KodKlasifikasiDAO kodKlasifikasiDAO;

    private Permohonan permohonan;
    private Permohonan permohonan2;
    private Permohonan permohonanSebelum;
    private HakmilikUrusan hu;
    private HakmilikUrusan husblm;
    private String idHakmilik;
    private FolderDokumen folderDokumen;
    private PermohonanUrusan permohonanUrusan;
    private List<HakmilikPermohonan> hakmilikPermohonanList;
    private List<HakmilikSebelumPermohonan> senaraiHakmilikBatal;
    private List<HakmilikSebelumPermohonan> senaraiHakmilikBatalUnique;
    private Map<String, String> kodMap = new HashMap<String, String>();
    private List<Dokumen> senaraiKandungan = new ArrayList<Dokumen>();
    private String idPermohonanTerdahulu;
    private String idCP;
    private List<Permohonan> permohonanTerdahuluList;
    private List<HakmilikPihakBerkepentingan> hpkList = new ArrayList<HakmilikPihakBerkepentingan>();
    private List<HakmilikPihakBerkepentingan> hpkList1 = new ArrayList<HakmilikPihakBerkepentingan>();
    private List<Permohonan> senaraiPermohonan = new ArrayList<Permohonan>();
    private List<Pemohon> pemohonList;
    private List<PermohonanPihak> permohonanPihakList;
    private List<Permohonan> permohonanList;
    private List<WakilKuasaPemberi> wkPberi;
    private List<WakilKuasaPenerima> wkPnerima;
    private List<Hakmilik> listHakmilikBatal;
    private List<Map<String, String>> listHakmilikBatal2;
    private List listBatal = new ArrayList();
    private String multipleIdDokumen;
    private String multipleIdFolder;
    private String idPemohonan1;
    private String idPermohonanCP;
    private String taskId1;
    private String stageId1;
    private String kodNegeri;
    private String stageId;
    private Pengguna pengguna;
    private KodUrusan kodUrusan;
    private KodKeputusan kodKpsn;
    private KodKeputusan kodKeputusan;
    private int groupFlag;
    private String tarikhPerintah;
    private PermohonanRujukanLuar permohonanRujukanLuar;
    private SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    private List<HakmilikPermohonan> hakmilikPermohonanListtemp;
    private PermohonanAtasPerserahan permohonanAtasPerserahan;
    private PermohonanAtasPerserahan permohonanAtasPerserahanMH;
    private List<PermohonanAtasPerserahan> permohonanAtasPerserahanList;
    private List<PermohonanAtasPerserahan> permohonanAtasPerserahanMHList;
    private List<PermohonanHubungan> permohonanHubunganList;
    private List<WakilKuasa> wakilKuasaList;
    private HakmilikPermohonan hp;
    private static String[] EXCLUDE_SEKATAN = {
        "039999999",
        "010000000",
        "010000001",
        "010000003",
        "010000004",
        "010000010",
        "010000012",
        "010040002",
        "029999999",
        "030000000",
        "030000001",
        "030000002",
        "030000003",
        "030000004",
        "000000000",
        "000000010",
        "000099999",
        "020000001",
        "020000003",
        "009999999",
        "020000000",
        "010410012",
        "079999999",
        "059999999",
        "070000001",
        "070100001",
        "070110008",
        "019999999",
        "050000000",
        "050000001",
        "050000006"
    };

    public HakmilikPermohonan getHp() {
        return hp;
    }

    public void setHp(HakmilikPermohonan hp) {
        this.hp = hp;
    }

    public List<Pemohon> getPemohonList() {
        return pemohonList;
    }

    public void setPemohonList(List<Pemohon> pemohonList) {
        this.pemohonList = pemohonList;
    }

    public List<PermohonanPihak> getPermohonanPihakList() {
        return permohonanPihakList;
    }

    public void setPermohonanPihakList(List<PermohonanPihak> permohonanPihakList) {
        this.permohonanPihakList = permohonanPihakList;
    }

    public HakmilikUrusan getHusblm() {
        return husblm;
    }

    public void setHusblm(HakmilikUrusan husblm) {
        this.husblm = husblm;
    }

    public List<Dokumen> getSenaraiKandungan() {
        return senaraiKandungan;
    }

    public void setSenaraiKandungan(List<Dokumen> senaraiKandungan) {
        this.senaraiKandungan = senaraiKandungan;
    }

    public Map<String, String> getKodMap() {
        return kodMap;
    }

    public void setKodMap(Map<String, String> kodMap) {
        this.kodMap = kodMap;
    }

    public Permohonan getPermohonan() {
        return permohonan;
    }

    public void setPermohonan(Permohonan permohonan) {
        this.permohonan = permohonan;
    }

    public String getIdHakmilik() {
        return idHakmilik;
    }

    public void setIdHakmilik(String idHakmilik) {
        this.idHakmilik = idHakmilik;
    }

    public List<HakmilikPermohonan> getHakmilikPermohonanList() {
        return hakmilikPermohonanList;
    }

    public void setHakmilikPermohonanList(List<HakmilikPermohonan> hakmilikPermohonanList) {
        this.hakmilikPermohonanList = hakmilikPermohonanList;
    }

    public String getIdPermohonanTerdahulu() {
        return idPermohonanTerdahulu;
    }

    public void setIdPermohonanTerdahulu(String idPermohonanTerdahulu) {
        this.idPermohonanTerdahulu = idPermohonanTerdahulu;
    }

    public List<Permohonan> getPermohonanTerdahuluList() {
        return permohonanTerdahuluList;
    }

    public void setPermohonanTerdahuluList(List<Permohonan> permohonanTerdahuluList) {
        this.permohonanTerdahuluList = permohonanTerdahuluList;
    }

    public Permohonan getPermohonanSebelum() {
        return permohonanSebelum;
    }

    public void setPermohonanSebelum(Permohonan permohonanSebelum) {
        this.permohonanSebelum = permohonanSebelum;
    }

    public List<HakmilikPihakBerkepentingan> getHpkList() {
        return hpkList;
    }

    public void setHpkList(List<HakmilikPihakBerkepentingan> hpkList) {
        this.hpkList = hpkList;
    }

    public List<HakmilikPihakBerkepentingan> getHpkList1() {
        return hpkList1;
    }

    public void setHpkList1(List<HakmilikPihakBerkepentingan> hpkList1) {
        this.hpkList1 = hpkList1;
    }

    public String getMultipleIdDokumen() {
        return multipleIdDokumen;
    }

    public void setMultipleIdDokumen(String multipleIdDokumen) {
        this.multipleIdDokumen = multipleIdDokumen;
    }

    public String getMultipleIdFolder() {
        return multipleIdFolder;
    }

    public void setMultipleIdFolder(String multipleIdFolder) {
        this.multipleIdFolder = multipleIdFolder;
    }

    public Pengguna getPengguna() {
        return pengguna;
    }

    public void setPengguna(Pengguna pengguna) {
        this.pengguna = pengguna;
    }

    public HakmilikUrusan getHu() {
        return hu;
    }

    public void setHu(HakmilikUrusan hu) {
        this.hu = hu;
    }

    public KodKeputusan getKodKpsn() {
        return kodKpsn;
    }

    public void setKodKpsn(KodKeputusan kodKpsn) {
        this.kodKpsn = kodKpsn;
    }

    @DefaultHandler
    public Resolution showForm() {
        return new JSP("common/maklumat_permohonan.jsp").addParameter("tab", "true");
    }

    public Resolution showForm2() {
        return new JSP("common/paparan_maklumat_permohonan.jsp").addParameter("tab", "true");
    }

    public Resolution showUrusanTerdahulu() {
        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        return new JSP("common/maklumat_permohonan.jsp").addParameter("tab", "true");
    }

    public Resolution showForm3() {

        String kodNegeri = conf.getProperty("kodNegeri");
        if ("05".equals(kodNegeri)) {
            getContext().getRequest().setAttribute("cetak", Boolean.FALSE);
        }

        StringBuilder bukanWarganegara = new StringBuilder();

        StringBuilder urusanPerintah = new StringBuilder();

        List<HakmilikPermohonan> lhp = permohonan.getSenaraiHakmilik();

        List<String> senaraiUrusan = new ArrayList<String>();

        senaraiUrusan.add("PLT");
        senaraiUrusan.add("PLK");
        senaraiUrusan.add("PLS");
        senaraiUrusan.add("PMHUN");
        senaraiUrusan.add("PMHUK");

        for (HakmilikPermohonan hakmilikPermohonan : lhp) {
            if (regService.periksaHalangan(permohonan, hakmilikPermohonan.getHakmilik())) {
                addSimpleError("Hakmilik " + hakmilikPermohonan.getHakmilik().getIdHakmilik() + " Mempunyai Halangan Mahkamah");
            }
            if (hakmilikPermohonan == null || hakmilikPermohonan.getHakmilik() == null) {
                continue;
            }
            Hakmilik hm = hakmilikPermohonan.getHakmilik();
            for (String urusan : senaraiUrusan) {
                KodUrusan urusan1 = kodUrusanDAO.findById(urusan);
                LOGGER.info("urusan1 = " + urusan1.getKod());
                List<HakmilikUrusan> senaraiUrusanTerlibat = hakmilikUrusanService.findByIdHakmilikKodUrusanOnly(hm.getIdHakmilik(), urusan1.getKod());
                if (senaraiUrusanTerlibat.size() > 0) {
                    urusanPerintah.append("Hakmilik " + hm.getIdHakmilik() + " mempunyai ");
                }
                ArrayList al = new ArrayList();
                for (HakmilikUrusan hu : senaraiUrusanTerlibat) {
                    if (hu.getAktif() == 'T') {
                        continue;
                    }

                    al.add(hu.getKodUrusan().getNama());

// To remove Duplicate Name Hakmilik Urusan
                    HashSet hs = new HashSet();
                    hs.addAll(al);
                    al.clear();
                    al.addAll(hs);
                }

                int count = 0;
                for (Object obj : al) {
                    urusanPerintah.append(obj);
                    count = count + 1;
                    if (count < al.size()) {
                        urusanPerintah.append(", ");
                    } else {
                        urusanPerintah.append(".");
                    }
                }
            }
        }
        if (urusanPerintah.length() > 0) {
            //addSimpleError(urusanPerintah.toString());
            getContext().getRequest().setAttribute("warning", urusanPerintah.toString());
        }

        if (permohonan != null) {
//            getContext().getRequest().setAttribute("idFolder", permohonan.getFolderDokumen().getFolderId());

            List<PermohonanPihak> senaraiPermohonanPihakTerlibat = permohonan.getSenaraiPihak();
            String msg = "Pihak terlibat bukan warganegara bagi hakmilik ";
            StringBuilder b = new StringBuilder();
            for (PermohonanPihak pp : senaraiPermohonanPihakTerlibat) {
                if (pp == null) {
                    continue;
                }
                LOGGER.debug("id pihak = " + pp.getPihak().getIdPihak());
                if (pp.getPihak() == null || pp.getPihak().getWargaNegara() == null) {
                    continue;
                }
                String warganegara = pp.getPihak().getWargaNegara().getKod();
                if (StringUtils.isNotBlank(warganegara)
                        && !warganegara.equals("MAL") && !warganegara.equals("000")) {
                    if (b.length() > 0) {
                        b.append(",");
                    }
                    b.append(pp.getHakmilik().getIdHakmilik());
                }
            }

            if (b.length() > 0) {
                addSimpleError(msg + b.toString());
            }

            //for hiding hakmilik batal if there is not result. Temp solution
            //fixme
            if (permohonan.getKeputusan() != null) {
                getContext().getRequest().setAttribute("unhide", true);
            }
            DMSUtil dms = new DMSUtil();
//            getContext().getRequest().setAttribute("idFolder", dms.getDMSPath(pengguna, null));
            getContext().getRequest().setAttribute("idFolder", dms.getFizikalPath(permohonan));
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            Date today = new Date();
            getContext().getRequest().setAttribute("today", sdf.format(today));

            StringBuilder sb = new StringBuilder();
            StringBuilder sekatan_ = new StringBuilder();
            List<HakmilikSebelumPermohonan> l_temp = new ArrayList<HakmilikSebelumPermohonan>();
            List<HakmilikAsalPermohonan> l_temp2 = new ArrayList<HakmilikAsalPermohonan>();
            senaraiHakmilikBatal = new ArrayList<HakmilikSebelumPermohonan>();
            senaraiHakmilikBatalUnique = new ArrayList<HakmilikSebelumPermohonan>();
            List<HakmilikAsalPermohonan> senaraiHakmilikAsal = new ArrayList<HakmilikAsalPermohonan>(); // senarai hakmilik asal yang ingin dibatal
            listHakmilikBatal = new ArrayList<Hakmilik>();

            listHakmilikBatal2 = new ArrayList<Map<String, String>>();

            List<HakmilikPermohonan> senaraiHakmilik = permohonan.getSenaraiHakmilik();
            LOGGER.info("------> senarai hakmilik size : " + senaraiHakmilik.size());
            for (HakmilikPermohonan hp : senaraiHakmilik) {

                if (hp == null || hp.getHakmilik() == null) {
                    continue;
                }
                Hakmilik hm = hp.getHakmilik();
                if (StringUtils.isNotBlank(hm.getIdHakmilikInduk())) {
                    getContext().getRequest().setAttribute("hakmilikStrata", "true");
                }
//                List<HakmilikPihakBerkepentingan> list = hakmilikPihakKepentinganService.doCheckPihakKaviet(hm.getIdHakmilik());
                List<HakmilikUrusan> list = hakmilikUrusanService.doCheckUrusanKaviet(hm.getIdHakmilik());

                KodSekatanKepentingan ks = hm.getSekatanKepentingan();
                if (ks != null && !ArrayUtils.contains(EXCLUDE_SEKATAN, ks.getKod())) {
                    if (sekatan_.length() > 0) {
                        sekatan_.append(",");
                    }
                    sekatan_.append(hm.getIdHakmilik());
                }

                if (!list.isEmpty()) {
                    if (sb.length() > 0) {
                        sb.append(",");
                    }
                    sb.append(hm.getIdHakmilik() + " mempunyai KAVEAT terhadap perserahan ");
                }
                int counting = 0;
                for (HakmilikUrusan hu : list) {
                    if (sb.length() > 0) {
                        if (counting != 0) {
                            sb.append(",");
                        }
                    }
                    sb.append(hu.getIdPerserahan());
                    counting++;
                }

                l_temp = hp.getSenaraiHakmilikSebelum();
                l_temp2 = hp.getSenaraiHakmilikAsal();

                for (HakmilikSebelumPermohonan sebelumPermohonan : l_temp) {
                    if (sebelumPermohonan == null) {
                        continue;
                    }
                    SejarahHakmilik sej = sejarahHakmilikDAO.findById(sebelumPermohonan.getHakmilikSejarah());

                    if (sej == null || sej.getKodStatusHakmilik().getKod().equals("B")) {
                        continue;
                    }
                    senaraiHakmilikBatal.add(sebelumPermohonan);
                }

                for (HakmilikAsalPermohonan asalPermohonan : l_temp2) {
                    if (asalPermohonan == null) {
                        continue;
                    }
                    Hakmilik hmAsal = hakmilikDAO.findById(asalPermohonan.getHakmilikSejarah());

                    if (hmAsal != null && hmAsal.getKodStatusHakmilik() != null
                            && hmAsal.getKodStatusHakmilik().getKod().equals("B")) {
                        continue;
                    }
                    senaraiHakmilikAsal.add(asalPermohonan);
                }
            }

            List<HakmilikSebelumPermohonan> l_temphsp = senaraiHakmilikBatal;
            List<HakmilikAsalPermohonan> l_temphap = senaraiHakmilikAsal; // temp hakmilik asal permohonan
            LOGGER.debug("size hakmilik batal :" + l_temphsp.size());
//            List<HakmilikSebelumPermohonan> l_temphsp = new ArrayList<HakmilikSebelumPermohonan>();
//            LOGGER.debug("size hakmilik batal :" + senaraiHakmilikBatal.size());
//            for (int k = 0; k < senaraiHakmilikBatal.size(); k++) {
//                l_temphsp.set(k, senaraiHakmilikBatal.get(k));
//            }

            String tempIdHakmilikSejarah = ""; // clear tempIdHakmilikSejarah
            for (HakmilikSebelumPermohonan hsp : l_temphsp) { // temp hakmilik sebelum permohonan
                if (hsp == null) {
                    continue;
                }
                Map<String, String> map = new HashMap<String, String>();
                if (tempIdHakmilikSejarah.equals(hsp.getHakmilikSejarah())) {
                    LOGGER.debug("NOT UNIQUE IDHAKMILIKSEJARAH FOUND!");
                    continue;
                }
                senaraiHakmilikBatalUnique.add(hsp);
                Hakmilik tempSblm = hakmilikDAO.findById(hsp.getHakmilikSejarah());
//        map.put("idHakmilik", hsp.getHakmilikSejarah());
//        if (tempSblm != null) {
//            map.put("id_dokumen", tempSblm.getDhde()!= null ? String.valueOf(tempSblm.getDhde().getIdDokumen()) : null);
//            map.put("kod_dokumen_name", tempSblm.getDhde() != null ? String.valueOf(tempSblm.getDhde().getKodDokumen().getNama()) : null);
//            map.put("nama_fizikal", String.valueOf(tempSblm.getDhde().getNamaFizikal()));
//        } 
                listHakmilikBatal2.add(map);

                listHakmilikBatal.add(tempSblm);
                tempIdHakmilikSejarah = hsp.getHakmilikSejarah();
                LOGGER.debug("size hakmilik batal :" + senaraiHakmilikBatal.size());
            }

            for (HakmilikAsalPermohonan hap : l_temphap) { // temp hakmilik asal permohonan
                if (hap == null) {
                    continue;
                }
                if (tempIdHakmilikSejarah.equals(hap.getHakmilikSejarah())) {
                    LOGGER.debug("NOT UNIQUE IDHAKMILIKSEJARAH FOUND!");
                    continue;
                }
                Hakmilik tempAsal = hakmilikDAO.findById(hap.getHakmilikSejarah());

                if (tempAsal != null && tempAsal.getKodStatusHakmilik().getKod().equals("D")) {
                    listHakmilikBatal.add(tempAsal);
                }

                Map<String, String> map = new HashMap<String, String>();
//        map.put("idHakmilik", hap.getHakmilikSejarah());
//        if (tempAsal != null) {
//            map.put("id_dokumen", String.valueOf(tempAsal.getDhde().getIdDokumen()));
//            map.put("kod_dokumen_name", String.valueOf(tempAsal.getDhde().getKodDokumen().getNama()));
//            map.put("nama_fizikal", String.valueOf(tempAsal.getDhde().getNamaFizikal()));
//            listHakmilikBatal.add(tempAsal);
//        }   

                listHakmilikBatal2.add(map);
            }

            LOGGER.info("------- > listHakmilikBatal size : " + listHakmilikBatal.size());

            String kodSerah = permohonan.getKodUrusan().getKodPerserahan().getKod();

            boolean m_hakmilikKekal = false;
            boolean m_hakmilikSementara = false;
            boolean singleDigitalSign = true;

            if (permohonan.getKodUrusan().getNama().matches("(?i).*Hakmilik Kekal.*")) {
                if (!permohonan.getKodUrusan().getKod().equals("HTIR") && !permohonan.getKodUrusan().getKod().equals("HKGHS")) {
                    m_hakmilikKekal = true;
                }
            }

            if (permohonan.getKodUrusan().getNama().matches("(?i).*Hakmilik Sementara.*")
                    && !permohonan.getKodUrusan().getKod().equals("HKGHS")) {
                m_hakmilikSementara = true;
            }
            
            if (permohonan.getKodUrusan().getKod().equals("HTIR")) {
                for (HakmilikPermohonan hpBaru : hakmilikPermohonanList) {
                    Hakmilik hm = hpBaru.getHakmilik();
                    if (hm.getKodHakmilik().getNama().matches("(?i).*Hakmilik Sementara.*")) {
                        m_hakmilikSementara = true;
                    } else {
                        m_hakmilikKekal = true;
                    }
                }
            }
            
            if (permohonan.getKodUrusan().getKod().equals("HMSC")) {
                for (HakmilikPermohonan hpBaru : hakmilikPermohonanList) {
                    Hakmilik hm = hpBaru.getHakmilik();
                    if (hm.getKodHakmilik().getNama().matches("(?i).*Hakmilik Sementara.*")) {
                        m_hakmilikSementara = true;
                    } else {
                        m_hakmilikKekal = true;
                    }
                }
            }
            
            if (permohonan.getKodUrusan().getKod().equals("HKGHS")) {
                for (HakmilikPermohonan hpBaru : hakmilikPermohonanList) {
                    Hakmilik hm = hpBaru.getHakmilik();
                    if (hm.getKodHakmilik().getNama().matches("(?i).*Hakmilik Sementara.*")) {
                        m_hakmilikSementara = true;
                    } else {
                        m_hakmilikKekal = true;
                    }
                }
            }

            if (kodSerah.equals("MH")) {
                if (permohonan.getKodUrusan().getKod().equals("HKTHK")
                        || permohonan.getKodUrusan().getKod().equals("HSTHK")) {
                    singleDigitalSign = true;
                } else {
                    singleDigitalSign = false;
                }
            }

            if (!singleDigitalSign) {
                if (m_hakmilikKekal) {
                    getContext().getRequest().setAttribute("multiple_sign_kekal", "true");
                }
                if (m_hakmilikSementara) {
                    getContext().getRequest().setAttribute("multiple_sign_sementara", "true");
                }
            } else {
                getContext().getRequest().setAttribute("not_mh", "true");
            }

            if (sekatan_.length() > 0) {
                if (!permohonan.getKodUrusan().getKod().equals("KVPT")) {
                    LOGGER.info("permohonan.getKodUrusan().getKodPerserahan() " + permohonan.getKodUrusan().getKodPerserahan().getKod());
                    if (!permohonan.getKodUrusan().getKodPerserahan().getKod().equals("MH")) {
                        addSimpleError("Hakmilik " + sekatan_.toString() + " mempunyai sekatan.");
                    }
                }
            }
            //dibuka semula atas arahan en Faizal Ali - 07052015
            if (sb.length() > 0) {
                addSimpleError("Hakmilik " + sb.toString() + ".Sila semak surat kebenaran.");
            }
        }

        //modify by : mohd.fairul
        if (permohonan.getKodUrusan().getKodPerserahan().getKod().equalsIgnoreCase("N"))// edit for nota
        {
            String tmpCode = permohonan.getKodUrusan().getKod().replace("-", "");
            NotaDaftarKodMap nm = NotaDaftarKodMap.valueOf(tmpCode);
            int kodGroup = nm.getCode();
            LOGGER.info("Kod Urusan::" + permohonan.getKodUrusan().getKod());
            LOGGER.info("After::" + tmpCode);
            LOGGER.info("Kod Group::" + kodGroup);
            switch (kodGroup) {
                case 1: {
                    groupFlag = 1;
                    break;
                }
                case 2: {
                    groupFlag = 2;
                    break;
                }

                default:
                    groupFlag = 0;
                    break;
            }
            return new JSP("daftar/nota/paparan_dokumen_pendaftar.jsp").addParameter("tab", "true");
        } else {
            return new JSP("daftar/paparan_dokumen_pendaftar.jsp").addParameter("tab", "true");
        }//end of modified
    }

    public Resolution paparanMaklumatPermohonanDaftar() {

        StringBuilder urusanPerintah = new StringBuilder();

        List<String> senaraiUrusan = new ArrayList<String>();
        senaraiUrusan.add("PLT");
        senaraiUrusan.add("PLK");
        senaraiUrusan.add("PLS");
        senaraiUrusan.add("PMHUN");
        senaraiUrusan.add("PMHUK");
        if (permohonan != null) {

            if (permohonan.getSenaraiHakmilik() != null && permohonan.getSenaraiHakmilik().size() > 0) {
                idHakmilik = permohonan.getSenaraiHakmilik().get(0).getHakmilik().getIdHakmilik();
                Hakmilik h = permohonan.getSenaraiHakmilik().get(0).getHakmilik();

                if (regService.periksaHalangan(permohonan, h)) {
                    addSimpleError("Hakmilik ini Mempunyai Halangan Mahkamah");
                }

                for (HakmilikPermohonan hakmilikPermohonan : permohonan.getSenaraiHakmilik()) {
                    if (hakmilikPermohonan == null || hakmilikPermohonan.getHakmilik() == null) {
                        continue;
                    }
                    Hakmilik hm = hakmilikPermohonan.getHakmilik();
                    for (String urusan : senaraiUrusan) {
                        KodUrusan urusan1 = kodUrusanDAO.findById(urusan);
                        LOGGER.info("urusan1 = " + urusan1.getKod());
                        List<HakmilikUrusan> senaraiUrusanTerlibat = hakmilikUrusanService.findByIdHakmilikKodUrusanOnly(hm.getIdHakmilik(), urusan1.getKod());
                        if (senaraiUrusanTerlibat.size() > 0) {
                            urusanPerintah.append("Hakmilik " + hm.getIdHakmilik() + " mempunyai ");
                        }
                        for (HakmilikUrusan hu : senaraiUrusanTerlibat) {
                            if (hu.getAktif() == 'T') {
                                continue;
                            }
                            urusanPerintah.append(hu.getKodUrusan().getNama() + ",");
                        }

                    }
                }
//
            }

            StringBuilder sb = new StringBuilder();
            StringBuilder sekatan = new StringBuilder();
            StringBuilder rezab = new StringBuilder();
            StringBuilder alert = new StringBuilder();
            StringBuilder CPB = new StringBuilder();
            StringBuilder HPB = new StringBuilder();
            StringBuilder perluConvert = new StringBuilder();
            StringBuilder pajakanLuputTempoh = new StringBuilder();
            StringBuilder adaKaveat = new StringBuilder();

            List<HakmilikPermohonan> senaraiHakmilik = permohonan.getSenaraiHakmilik();
            List<String> listKod = new ArrayList();

            for (HakmilikPermohonan hp : senaraiHakmilik) {
                if (hp == null || hp.getHakmilik() == null) {
                    continue;
                }
                Hakmilik hm = hp.getHakmilik();
                listKod.clear();
                listKod.add("PG");
                //                List<HakmilikPihakBerkepentingan> list = hakmilikPihakKepentinganService.doCheckPihakKaviet(hm.getIdHakmilik());

                if (hm.getNoVersiDhde() != null && hm.getNoVersiDhde() == 0 && hm.getKodStatusHakmilik() != null
                        && hm.getKodStatusHakmilik().getKod().equals("D")) {
                    if (perluConvert.length() > 0) {
                        perluConvert.append(",");
                    }
                    perluConvert.append(hm.getIdHakmilik());
                }

//                List<HakmilikPihakBerkepentingan> list1 = hakmilikPihakKepentinganService.doCheckPihakKaviet(hm.getIdHakmilik());
                List<HakmilikUrusan> list1 = hakmilikUrusanService.doCheckUrusanKaviet(hm.getIdHakmilik());
                if (!list1.isEmpty()) {
                    if (adaKaveat.length() > 0) {
                        adaKaveat.append(",");
                    }
                    adaKaveat.append(hm.getIdHakmilik() + " mempunyai KAVEAT terhadap perserahan ");
                }
                int counting = 0;
                for (HakmilikUrusan hu : list1) {
                    if (adaKaveat.length() > 0) {
                        if (counting != 0) {
                            adaKaveat.append(",");
                        }
                    }
                    adaKaveat.append(hu.getIdPerserahan());
                    counting++;
                }

                List<HakmilikPihakBerkepentingan> list = hakmilikPihakKepentinganService.doCheckUrusan(hm.getIdHakmilik(), listKod);
                if (!list.isEmpty()) {
                    if (sb.length() > 0) {
                        sb.append(",");
                    }
                    sb.append(hm.getIdHakmilik());
                }
                KodSekatanKepentingan ks = hm.getSekatanKepentingan();
                if (ks != null && !ArrayUtils.contains(EXCLUDE_SEKATAN, ks.getKod())) {
                    if (sekatan.length() > 0) {
                        sekatan.append(",");
                    }
                    sekatan.append(hm.getIdHakmilik());
                }
                listKod.clear();
                if (hm.getRizab() != null && hm.getRizab().getKod() == 3) {
                    if (rezab.length() > 0) {
                        rezab.append(",");
                    }
                    rezab.append(hm.getIdHakmilik());
                } else {
//                    listKod.add("IRM");
                    KodUrusan urusan1 = kodUrusanDAO.findById("IRM");
//                    list = hakmilikPihakKepentinganService.doCheckUrusan(hm.getIdHakmilik(), listKod);
                    List<HakmilikUrusan> senarai = hakmilikUrusanService.findByIdHakmilikKodUrusanOnly(hm.getIdHakmilik(), urusan1.getKod());
                    if (!senarai.isEmpty()) {
                        if (rezab.length() > 0) {
                            rezab.append(",");
                        }
                        rezab.append(hm.getIdHakmilik());
                    }
                }
                //Added by Aizuddin for urusan dadah
                listKod.clear();
                listKod.add("ADBSB");
                listKod.add("ADBSS");
                listKod.add("TT");
                listKod.add("TTW");

                //Start, 2013-05-09: Add Message for urusan SHKK/SHSK if Pemilik < 2.
                if (permohonan.getKodUrusan().getKod().equals("SHKK") || permohonan.getKodUrusan().getKod().equals("SHSK")) {

                    List<HakmilikPihakBerkepentingan> Listhpb = hm.getSenaraiPihakBerkepentingan();
                    List<HakmilikPihakBerkepentingan> listHPBTemp = new ArrayList<HakmilikPihakBerkepentingan>();
                    for (HakmilikPihakBerkepentingan hpb : Listhpb) {
                        if (hpb.getJenis().getKod().contains("PM") && hpb.getAktif() == 'Y') {
                            listHPBTemp.add(hpb);
                        }
                    }
                    if (listHPBTemp.size() < 2) {
                        if (HPB.length() > 0) {
                            HPB.append(", ");
                        }
                        HPB.append(hm.getIdHakmilik());
                    }
                }//End, 2013-05-09: Add Message for urusan SHKK/SHSK if Pemilik < 2.

                //Start azmi, 2013-04-26 : Add Alert Messages for Urusan PMT on Kemasukan if Urusan SHKK and SHSK is Aktif.
                if (permohonan.getKodUrusan().getKod().equals("PMT")) {

                    if (permohonan.getSenaraiHakmilik() != null && permohonan.getSenaraiHakmilik().size() > 0) {

                        List<HakmilikUrusan> senaraiUrusanTerlibat = hakmilikUrusanService.findHakmilikUrusanActiveByIdHakmilikDistinctPermohonan(hm.getIdHakmilik());
                        for (HakmilikUrusan hu : senaraiUrusanTerlibat) {
                            if ((hu.getKodUrusan().getKod().equals("SHSK") && hu.getAktif() == 'Y')) {
                                listKod.add("SHSK");
                            }
                            if ((hu.getKodUrusan().getKod().equals("SHKK") && hu.getAktif() == 'Y')) {
                                listKod.add("SHKK");
                            }
                        }
                    }
                }//End azmi, 2013-04-26 : Add Alert Messages for Urusan PMT on Kemasukan if Urusan SHKK and SHSK is Aktif.
                for (String urusan0 : listKod) {
                    KodUrusan urusan1 = kodUrusanDAO.findById(urusan0);
                    LOGGER.info("urusan1 = " + urusan1.getKod());
                    List<HakmilikUrusan> urusAlert = hakmilikUrusanService.findByIdHakmilikKodUrusanOnly(hm.getIdHakmilik(), urusan1.getKod());
                    if (!urusAlert.isEmpty()) {
                        if (alert.length() > 0) {
                            alert.append(",");
                        }
                        alert.append("Hakmilik ").append(hm.getIdHakmilik()).append(" mempunyai urusan ");
                        int count = 0;
                        for (HakmilikUrusan hu : urusAlert) {
                            alert.append(hu.getKodUrusan().getNama())
                                    .append(" (")
                                    .append(hu.getKodUrusan().getKod())
                                    .append(") ")
                                    .append(hu.getIdPerserahan());
                            if (count++ < urusAlert.size()) {
                                alert.append(",");
                            }

                        }
                        alert.append(" yang didaftarkan. <br/>");
//                    for (HakmilikUrusan hu : urusAlert) {
//                        LOGGER.info(":::::::::::1st Loop:::::::::::");
//                        HakmilikPermohonan hp2 = hakmilikPermohonanService.findHakmilikPermohonan(hu.getHakmilik().getIdHakmilik(), hu.getIdPerserahan());
//                        permohonan2 = hp2.getPermohonan();
//                        LOGGER.info("ID Hakmilik:::::::::::" + hm.getIdHakmilik());
//                        LOGGER.info("ID Mohon:::::::::::" + permohonan2.getIdPermohonan());
//                        LOGGER.info("Kod Status:::::::::::" + permohonan2.getStatus());
//                        if (permohonan2.getStatus().equalsIgnoreCase("SL")) {
//                            if (!permohonan2.getKeputusan().getKod().isEmpty()) {
//                                LOGGER.info("Kod Keputusan:::::::::::" + permohonan2.getKeputusan().getKod());
//                                if (permohonan2.getKeputusan().getKod().equalsIgnoreCase("D")) {
//                                    if (alert.length() > 0) {
//                                        alert.append(",");
//                                    }
//                                    alert.append(hu.getHakmilik().getIdHakmilik());
//                                }
//                            }
//                        }
                    }
                }

                if (permohonan.getKodUrusan().getKod().equals("CPB")) {
                    String kodHakmilik = hm.getKodHakmilik().getKod();
                    if (!kodHakmilik.equals("GMM") || !kodHakmilik.equals("HMM")) {
                        if (CPB.length() > 0) {
                            CPB.append(",");
                        }
                        CPB.append(hm.getIdHakmilik());
                    }
                }

                //pajakan tamat tempoh
                listKod.clear();
                listKod.add("PJT");
                listKod.add("PJKT");
                listKod.add("PJTM");
                listKod.add("PJBT");
                listKod.add("PJKBT");

                for (String urusan0 : listKod) {
                    KodUrusan urusan1 = kodUrusanDAO.findById(urusan0);
                    LOGGER.info("urusan1 = " + urusan1.getKod());
                    List<HakmilikUrusan> senaraiUrusanTerlibat = hakmilikUrusanService.findByIdHakmilikKodUrusanOnly(hm.getIdHakmilik(), urusan1.getKod());
                    for (HakmilikUrusan urusan : senaraiUrusanTerlibat) {
                        if (urusan == null) {
                            continue;
                        }

                        Calendar now = Calendar.getInstance();
                        Calendar endDate = Calendar.getInstance();
                        if (urusan.getTarikhTamat() != null) {
                            endDate.setTime(urusan.getTarikhTamat());
                        } else {
//                endDate.setTime(urusan.getTarikhMula());
                            int year = urusan.getTempohTahun() != null ? urusan.getTempohTahun() : 0;
                            int month = urusan.getTempohBulan() != null ? urusan.getTempohBulan() : 0;
                            int day = urusan.getTempohHari() != null ? urusan.getTempohHari() : 0;
                            endDate.add(Calendar.MONDAY, month);
                            endDate.add(Calendar.YEAR, year);
                            endDate.add(Calendar.DATE, day);
                        }

                        if (now.after(endDate)) {
                            if (pajakanLuputTempoh.length() > 0) {
                                pajakanLuputTempoh.append(",");
                            }
                            pajakanLuputTempoh.append(hm.getIdHakmilik());
                        }
                    }
                }
            }

            if (sb.length() > 0) {
//                addSimpleError ("Hakmilik " + sb.toString() + " telah digadaikan pada urusan sebelum.");
                getContext().getRequest().setAttribute("warning", "Hakmilik " + sb.toString() + " telah digadaikan pada urusan sebelum.");

            }
            if (sekatan.length() > 0) {
                if (!permohonan.getKodUrusan().getKod().equals("KVPT")) {
                    getContext().getRequest().setAttribute("warning2", "Hakmilik " + sekatan.toString() + " mempunyai sekatan.");
                }
            }

            if (rezab.length() > 0) {
                getContext().getRequest().setAttribute("warning3", "Hakmilik " + rezab.toString() + " adalah rezab melayu.");
            }

            if (alert.length() > 0) {
//                getContext().getRequest().setAttribute("warning4", "Hakmilik " + alert.toString() + " mempunyai urusan " + permohonan2.getKodUrusan().getNama() + "(" + permohonan2.getKodUrusan().getKod() + ")" + " " + permohonan2.getIdPermohonan() + " yang didaftarkan.");
                getContext().getRequest().setAttribute("warning4", alert.toString());
            }

            if (CPB.length() > 0) {
                getContext().getRequest().setAttribute("warning5", "Hakmilik " + CPB.toString() + " bukan GMM atau HMM.");
            }
            if (HPB.length() > 0) {
                getContext().getRequest().setAttribute("warning6", "Urusan ini memerlukan Hakmilik " + HPB.toString() + " yang mempunyai lebih dari seorang pemilik.");
            }

            if (perluConvert.length() > 0) {

                StringBuilder error = new StringBuilder("Hakmilik ")
                        .append(perluConvert.toString())
                        .append(" perlu convert terlebih dahulu.");

                if (StringUtils.isNotBlank(permohonan.getCatatan())) {
                    error.append(".\n")
                            .append("ID Permohonan ")
                            .append(permohonan.getCatatan());
                }

                getContext().getRequest().setAttribute("warning7", error.toString());
            }

            if (pajakanLuputTempoh.length() > 0) {
                getContext().getRequest().setAttribute("warning8",
                        "Hakmilik " + pajakanLuputTempoh.toString() + " mempunyai pajakan yang tamat tempoh.");
            }
            if (adaKaveat.length() > 0) {
                getContext().getRequest().setAttribute("warning9", "Hakmilik " + adaKaveat.toString() + ".Sila semak surat kebenaran.");
            }

            if (permohonan.getPermohonanSebelum() != null) {
                permohonanTerdahuluList = new LinkedList<Permohonan>();
                permohonanTerdahuluList.add(permohonan.getPermohonanSebelum());
            }
            List<FasaPermohonan> fasaPermohonan = permohonan.getSenaraiFasa();
            for (FasaPermohonan f : fasaPermohonan) {
                if (f.getIdAliran().equalsIgnoreCase("keputusan")) {
                    List<FasaPermohonan> fasa = new LinkedList<FasaPermohonan>();
                }
            }
            if (permohonan.getKodUrusan().getKod().equals("PMT")) {

                wakilKuasaList = suratKuasaService.findWakilByIdPermohonanKuasaList(permohonan.getIdPermohonan());

            }
        }
        return new JSP("daftar/paparan_maklumat_permohonan_daftar.jsp").addParameter("tab", "true");
    }

    public Resolution saveCaraTerimaBahagian() {
//        String caraTerimaBahagian = getContext().getRequest().getParameter("caraTerimaSyer");
//            if(StringUtils.isNotBlank(caraTerimaBahagian)){
//                permohonan.setSebab(caraTerimaBahagian);
//                conService.simpanPermohonan(permohonan);
//            }
        if (isDebug) {
            LOGGER.debug("caraterima :" + permohonan.getSebab());
        }
        conService.simpanPermohonan(permohonan);

        return paparanMaklumatPermohonanDaftar();
    }

    public Resolution paparanSenaraiPerserahan() {
        return new JSP("daftar/common/senarai_perserahan.jsp").addParameter("tab", "true");
    }

    public Resolution paparanSurat() {
        return new JSP("daftar/common/paparan_surat.jsp").addParameter("tab", "true");
    }

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {
        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        if (idPermohonan != null) {

            permohonan = permohonanDAO.findById(idPermohonan);

            if (permohonan.getIdPermohonan().contains("STR")) {
                System.out.println("--Strata--IdMohon--");
                hakmilikPermohonanListtemp = strPtService.findIdHakmilikSort(permohonan.getIdPermohonan());
                System.out.println("--Strata--hakmilikPermohonanListtemp--" + hakmilikPermohonanListtemp);
                hakmilikPermohonanList = new ArrayList<HakmilikPermohonan>();
                for (int i = 0; i < hakmilikPermohonanListtemp.size(); i++) {
                    HakmilikPermohonan hp = new HakmilikPermohonan();
                    hp = hakmilikPermohonanListtemp.get(i);
                    if (hp.getHakmilik().getIdHakmilik().length() > 20) {
                        hakmilikPermohonanList.add(hp);
                        System.out.println("--Strata--hakmilikPermohonanList--" + hakmilikPermohonanList);
                    }
                }

            } else {
                hakmilikPermohonanList = hpService.findIdHakmilikSort(permohonan.getIdPermohonan());
            }

            if (permohonan == null) {
                addSimpleError("ID Permohonan " + idPermohonan + " tidak wujud");
                return;
            }

            if (permohonan.getSenaraiHakmilik().size() > 0) {
                if (permohonan.getSenaraiHakmilik().get(0).getHakmilik() != null) {
                    idHakmilik = permohonan.getSenaraiHakmilik().get(0).getHakmilik().getIdHakmilik();
                }
            }

            folderDokumen = permohonan.getFolderDokumen();

            if (permohonan.getFolderDokumen() != null) {
                for (KandunganFolder kf : folderDokumen.getSenaraiKandungan()) {
                    if (kf == null) {
                        continue;
                    }
                    KodDokumen kd = kf.getDokumen().getKodDokumen();
                    if (kd == null) {
                        continue;
                    }

                    kodMap.put(kd.getKod(), kd.getNama());
                    if (permohonan.getKodUrusan().getKod().equals("HSBM")) {
                        if (kd.getKod().equals("DHDK") || kd.getKod().equals("DHKK")) {
                            senaraiKandungan.add(kf.getDokumen());
                        }
                    } else if (kd.getKod().equals("VDOC")) {
                        senaraiKandungan.add(kf.getDokumen());
                    }
                }
            }

            //FOR URUSAN BANTAHAN TANAH ADAT CONSENT
            if (permohonan.getKodUrusan().getKod().equalsIgnoreCase("BTADT")) {
                permohonanUrusan = conService.findMohonUrusanByPerihal(idPermohonan, "URUSAN BANTAHAN");

                if (permohonanUrusan != null) {
                    kodUrusan = kodUrusanDAO.findById(permohonanUrusan.getCatatan());
                }
            } //FOR URUSAN PMMAT CONSENT
            if (permohonan.getKodUrusan().getKod().equalsIgnoreCase("PMMAT")) {
                if (!permohonan.getSenaraiRujukanLuar().isEmpty()) {
                    permohonanRujukanLuar = permohonan.getSenaraiRujukanLuar().get(0);
                    if (permohonanRujukanLuar.getTarikhRujukan() != null) {
                        tarikhPerintah = sdf.format(permohonanRujukanLuar.getTarikhRujukan());
                    }
                }
            }
//            if (permohonan.getKodUrusan().getKod().equalsIgnoreCase("BETHM")) {
//                InfoAudit ia = new InfoAudit();
//                List<PermohonanPembetulanHakmilik> pphlist = pService.findidPermohonan(String.valueOf(permohonan.getIdPermohonan()));
//                for (PermohonanPembetulanHakmilik pph : pphlist) {
//                    HakmilikPermohonan hp = pService.findByIdHakmilik(permohonan.getIdPermohonan(), pph.getIdHakmilik());
//                    Hakmilik hakmilik = hakmilikDAO.findById(pph.getIdHakmilik());
//                    if (hp == null) {
//                        hp = new HakmilikPermohonan();
//                        ia.setDimasukOleh(pengguna);
//                        ia.setTarikhMasuk(new java.util.Date());
//                        hp.setHakmilik(hakmilik);
//                        hp.setInfoAudit(ia);
//                        hp.setPermohonan(permohonan);
//                        pService.saveMohonHakmilik(hp);
//                    }
//                }
//            }
            if (permohonan.getKodUrusan().getKod().equals("PWGSA")) {
                String taskId = (String) getContext().getRequest().getSession().getAttribute("taskId");
                BPelService serviceBpel = new BPelService();
                String stageId = "";
                if (StringUtils.isNotBlank(taskId)) {
                    Task t = null;
                    t = serviceBpel.getTaskFromBPel(taskId, pengguna);
                    stageId = t.getSystemAttributes().getStage();
                }
                if (stageId.equals("01TrmArhn")) {
                    Permohonan pmohonan = permohonanDAO.findById(idPermohonan);
                    kodKpsn = kodKpsnDAO.findById("L");
                    pmohonan.setKeputusan(kodKpsn);
                    lupusService.simpanPermohonan(pmohonan);
                }
            }
            if (permohonan.getKodUrusan().getKod().equals("PTPT")) {
                kodNegeri = conf.getProperty("kodNegeri");
                String taskId = (String) getContext().getRequest().getSession().getAttribute("taskId");
                BPelService serviceBpel = new BPelService();

                if (StringUtils.isNotBlank(taskId)) {
                    Task task = null;
                    task = serviceBpel.getTaskFromBPel(taskId, pengguna);
                    if (task != null) {
                        stageId = task.getSystemAttributes().getStage();
                        LOGGER.info("--------------stage Id BPEL ON--------------- : " + stageId);
                    }
                } else {
                    stageId = "17ArahanMaklumanKpsn";
                    LOGGER.info("--------------stage Id BPEL OFF--------------- : " + stageId);
                }
                FasaPermohonan mf = new FasaPermohonan();
                mf = notisPenerimaanService.getFasaPermohonan(idPermohonan, "16KpsnMahkamah");
                if (mf != null) {
                    if (mf.getKeputusan() != null) {
                        kodKeputusan = mf.getKeputusan();
                    }
                }
            }
        }
    }

    public Resolution kemaskiniHakmilik() {
        pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        idPemohonan1 = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        taskId1 = (String) getContext().getRequest().getSession().getAttribute("taskId");
        stageId1 = getCurrentStageId(taskId1);

        LOGGER.debug("--> idpguna: " + pengguna.getIdPengguna());
        LOGGER.debug("--> nama pguna: " + pengguna.getNama());
        LOGGER.debug("--> Jawatan pguna: " + pengguna.getJawatan());
        LOGGER.debug("--> idPermohonan: " + idPemohonan1);
        LOGGER.debug("--> task id: " + taskId1);
        LOGGER.debug("--> Stage id: " + stageId1);

        return new JSP("daftar/nota/daftar_kemaskini_hakmilik.jsp").addParameter("tab", "true");
    }

    public String getCurrentStageId(String taskId) {
        BPelService service = new BPelService();
        stageId1 = null;
        if (StringUtils.isNotBlank(taskId)) {
            Task t = null;
            t = service.getTaskFromBPel(taskId, pengguna);
            stageId1 = t.getSystemAttributes().getStage();
        }
        return stageId1;
    }

    public Resolution simpanPermohonane() {
        conService.simpanPermohonan(permohonan);

        addSimpleMessage("Kemasukan Data Berjaya");
        return new JSP("common/maklumat_permohonan.jsp").addParameter("tab", "true");
    }

    public Resolution simpanPermohonanUrusan() {

        if (permohonanUrusan != null) {
            permohonanUrusan.setPermohonan(permohonan);
            permohonanUrusan.setCawangan(permohonan.getCawangan());
            permohonanUrusan.setPerihal("URUSAN BANTAHAN");
            InfoAudit infoAudit = new InfoAudit();
            infoAudit.setDimasukOleh(pengguna);
            infoAudit.setTarikhMasuk(new java.util.Date());
            permohonanUrusan.setInfoAudit(infoAudit);
            conService.simpanPermohonanUrusan(permohonanUrusan);
            addSimpleMessage("Maklumat telah berjaya disimpan.");
        } else {
            addSimpleError("Sila masukkan nama permohonan terdahulu.");
        }

        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        return new JSP("common/maklumat_permohonan.jsp").addParameter("tab", "true");
    }

    public Resolution simpanPermohonanRujukanLuar() {

        if (permohonanRujukanLuar != null) {

            if (permohonanRujukanLuar.getCatatan() != null && tarikhPerintah != null && permohonanRujukanLuar.getNoRujukan() != null) {
                permohonanRujukanLuar.setPermohonan(permohonan);
                permohonanRujukanLuar.setCawangan(permohonan.getCawangan());
                try {
                    permohonanRujukanLuar.setTarikhRujukan(sdf.parse(tarikhPerintah));
                } catch (ParseException ex) {
                    java.util.logging.Logger.getLogger(MaklumatPermohonanActionBean.class.getName()).log(Level.SEVERE, null, ex);
                }

                InfoAudit infoAudit = new InfoAudit();
                infoAudit.setDimasukOleh(pengguna);
                infoAudit.setTarikhMasuk(new java.util.Date());
                KodRujukan kodRujukan = new KodRujukan();
                kodRujukan.setKod("FL");
                permohonanRujukanLuar.setKodRujukan(kodRujukan);
                permohonanRujukanLuar.setInfoAudit(infoAudit);
                conService.simpanRujukanLuar(permohonanRujukanLuar);
                addSimpleMessage("Maklumat telah berjaya disimpan.");

            } else {
                addSimpleError("Sila masukkan maklumat dengan lengkap.");
            }
        } else {
            addSimpleError("Sila masukkan maklumat dengan lengkap.");
        }

        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        return new JSP("common/maklumat_permohonan.jsp").addParameter("tab", "true");
    }

    public Resolution searchMaklumatTerdahulu() {
        if (isDebug) {
            LOGGER.debug("idPermohonanTerdahulu::" + idPermohonanTerdahulu);
        }
        if (StringUtils.isNotBlank(idPermohonanTerdahulu)) {
            Permohonan p = permohonanDAO.findById(idPermohonanTerdahulu);
            if (p != null) {
                permohonanTerdahuluList = new LinkedList<Permohonan>();
                permohonanTerdahuluList.add(p);
            }
        }
        return new JSP("daftar/paparan_maklumat_permohonan_daftar.jsp").addParameter("tab", "true");
    }

    public Resolution janaCPbaru() {

        idPemohonan1 = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);

        KodUrusan urusanCPB = kodUrusanDAO.findById("CPB");
        String idPermohonanCP = idPerserahanGenerator.generate(
                configuration.getProperty("kodNegeri"), pengguna.getKodCawangan(), urusanCPB);
        LOGGER.info("idPermohonanCP = " + idPermohonanCP);
        permohonan = permohonanDAO.findById(idPemohonan1);
       wakilKuasaList = suratKuasaService.findWakilByIdPermohonanKuasaList(idPermohonanCP);
        if (wakilKuasaList.size() <= 0) {

            InfoAudit ia = new InfoAudit();
            KodDokumen kd = kodDokumenDAO.findById("SCP");
//        suratAttached.setNoRujukan(idPermohonanCP);
//        permohonan.set 
            Dokumen doc = null;
            doc = new Dokumen();
            ia.setDimasukOleh(pengguna);
            ia.setTarikhMasuk(new java.util.Date());
            doc.setNoRujukan(idPermohonanCP);
            doc.setPermohonan(permohonan);
            doc.setBaru('Y');

            Permohonan p = new Permohonan();
            p.setStatus("SL");
            p.setIdPermohonan(idPermohonanCP);
            p.setCawangan(pengguna.getKodCawangan());
            p.setKodUrusan(urusanCPB);

            InfoAudit iaPermohonan = new InfoAudit();
            Date d = new Date();
            iaPermohonan.setTarikhMasuk(d);
            iaPermohonan.setDimasukOleh(pengguna);
            p.setInfoAudit(iaPermohonan);
            // folder
            p.setFolderDokumen(permohonan.getFolderDokumen());
            p.setIdPenyerah(permohonan.getIdPenyerah());

            p.setPenyerahJenisPengenalan(permohonan.getPenyerahJenisPengenalan());
            p.setPenyerahNoPengenalan(permohonan.getPenyerahNoPengenalan());
            p.setPenyerahNama(permohonan.getPenyerahNama());
            p.setPenyerahAlamat1(permohonan.getPenyerahAlamat1());
            p.setPenyerahAlamat2(permohonan.getPenyerahAlamat2());
            p.setPenyerahAlamat3(permohonan.getPenyerahAlamat3());
            p.setPenyerahAlamat4(permohonan.getPenyerahAlamat4());
            p.setPenyerahPoskod(permohonan.getPenyerahPoskod());
            p.setPenyerahNegeri(permohonan.getPenyerahNegeri());
            p.setPenyerahEmail(permohonan.getPenyerahEmail());
            p.setPenyerahNoTelefon1(permohonan.getPenyerahNoTelefon1());
            p.setUntukTahun(d.getYear() + 1900);
            p.setFolderDokumen(permohonan.getFolderDokumen());

//        FolderDokumen fd = new FolderDokumen();
//        fd.setTajuk(idPermohonanCP);
//        fd.setInfoAudit(iaPermohonan);
            KodKlasifikasi klasifikasiAm = kodKlasifikasiDAO.findById(1);
            doc.setKlasifikasi(klasifikasiAm);
            doc.setFormat("application/pdf");
            doc.setInfoAudit(ia);
            //TODO : increase versi if regenarate report
            doc.setNoVersi("1.0");
            doc.setTajuk(kd.getNama());
            doc.setKodDokumen(kd);
//        doc.setDalamanNilai1(id);
            doc = dokumenService.saveOrUpdate(doc);
//        folderDokumenDAO.save(fd);

            KandunganFolder kf = new KandunganFolder();
            kf.setDokumen(doc);
            kf.setFolder(permohonan.getFolderDokumen());
            kf.setInfoAudit(iaPermohonan);
            kandunganFolderDAO.save(kf);

            p.setPermohonanSebelum(permohonan);
            permohonanService.saveOrUpdate(p);

            InfoAudit infoAudit = new InfoAudit();
            infoAudit.setDimasukOleh(pengguna);
            infoAudit.setTarikhMasuk(new java.util.Date());
            WakilKuasa cp = new WakilKuasa();
            cp.setIdWakil(idPermohonanCP);
            cp.setPermohonan(permohonan);
            cp.setCawangan(permohonan.getCawangan());
            cp.setFolder(p.getFolderDokumen());
            cp.setAktif('A');
            cp.setInfoAudit(infoAudit);
            suratKuasaService.saveWakilKuasa(cp);
        }
         wakilKuasaList = suratKuasaService.findWakilByIdPermohonanKuasaList(permohonan.getIdPermohonan());
        return new JSP("daftar/paparan_maklumat_permohonan_daftar.jsp").addParameter("tab", "true");
    }

    public Resolution deleteCertificatePerson() {

        idPemohonan1 = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
//        idPermohonanCP = (String) getContext().getRequest().getSession().getAttribute("idPermohonanCP");

        List<WakilKuasa> wakilKuasaList = suratKuasaService.findCertificatePerson(idPermohonanCP, idPemohonan1);
        for (WakilKuasa wk : wakilKuasaList) {
            suratKuasaService.deleteWakilKuasa(wk);
        }
        wakilKuasaList = suratKuasaService.findWakilByIdPermohonanKuasaList(permohonan.getIdPermohonan());

        return new JSP("daftar/paparan_maklumat_permohonan_daftar.jsp").addParameter("tab", "true");
    }

    public Resolution searchMaklumatCP() {
        idPemohonan1 = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");

        if (isDebug) {
            LOGGER.debug("idPermohonanTerdahulu::" + idCP);
        }
        if (idPemohonan1 != null) {
            permohonan = permohonanDAO.findById(idPemohonan1);
        }
        InfoAudit infoAudit = new InfoAudit();
        infoAudit.setDimasukOleh(pengguna);
        infoAudit.setTarikhMasuk(new java.util.Date());

        if (StringUtils.isNotBlank(idCP)) {
            Permohonan p = permohonanDAO.findById(idCP);
            if (p != null) {
                List<WakilKuasa> wakilKuasaList = suratKuasaService.findCertificatePerson(idCP, idPemohonan1);
                if (wakilKuasaList.size() > 0) {
//                for (WakilKuasa wk : wakilKuasaList) {
//                    if (wakilKuasaList.size() > 0) {
                    addSimpleError("Id" + idCP + "yang dimasukan Telah digunakan untuk permohonan ini.");
                } else {
                    LOGGER.debug("LOG MASUK SINI---------" + idCP);
                    KodDokumen kd = kodDokumenDAO.findById("SCP");
                    Dokumen doc = new Dokumen();
                    infoAudit.setDimasukOleh(pengguna);
                    infoAudit.setTarikhMasuk(new java.util.Date());
                    doc.setNoRujukan(idCP);
                    doc.setPermohonan(permohonan);
                    doc.setBaru('Y');
                    doc.setFormat("application/pdf");
                    doc.setInfoAudit(infoAudit);
                    //TODO : increase versi if regenarate report
                    doc.setNoVersi("1.0");
                    doc.setTajuk(kd.getNama());
                    doc.setKodDokumen(kd);
                    KodKlasifikasi klasifikasiAm = kodKlasifikasiDAO.findById(1);
                    doc.setKlasifikasi(klasifikasiAm);
                    doc = dokumenService.saveOrUpdate(doc);

                    KandunganFolder kf = new KandunganFolder();
                    kf.setDokumen(doc);
                    kf.setFolder(permohonan.getFolderDokumen());
                    kf.setInfoAudit(infoAudit);
                    kandunganFolderDAO.save(kf);
                    
                    WakilKuasa cp = new WakilKuasa();
                    cp.setIdWakil(idCP);
                    cp.setPermohonan(permohonan);
                    cp.setCawangan(permohonan.getCawangan());
                    cp.setFolder(permohonan.getFolderDokumen());
                    cp.setAktif('A');
                    cp.setInfoAudit(infoAudit);
                    suratKuasaService.saveWakilKuasa(cp);
                    
                }
            }
        }
         wakilKuasaList = suratKuasaService.findWakilByIdPermohonanKuasaList(permohonan.getIdPermohonan());

        return new JSP("daftar/paparan_maklumat_permohonan_daftar.jsp").addParameter("tab", "true");
    }

    public Resolution view() {
        String idPermohonan = getContext().getRequest().getParameter("idPermohonan");
        String idHakmilik = getContext().getRequest().getParameter("idHakmilik");
        LOGGER.info("--> id Permohonan : " + idPermohonan);
        LOGGER.info("--> id hakmilik : " + idHakmilik);
        if (StringUtils.isNotBlank(idPermohonan)) {
            permohonanSebelum = permohonanDAO.findById(idPermohonan); // Table MOHON
            hu = hakmilikUrusanService.findByIdPerserahan(idPermohonan); // Table HAKMILIK_URUSAN
            hp = hakmilikPermohonanService.findHakmilikPermohonan(idHakmilik, idPermohonan);

            if (permohonanSebelum != null
                    && permohonanSebelum.getKodUrusan() != null
                    && permohonanSebelum.getKodUrusan().getKod().equalsIgnoreCase("PLK")) {
                husblm = pService.findUrusan(String.valueOf(hu.getUrusanRujukan().getIdUrusan()));
            }

            if (permohonanSebelum != null) {
                LOGGER.info("--> kod urusan : " + permohonanSebelum.getKodUrusan().getKod());
                if (permohonanSebelum.getSenaraiHakmilik().size() > 0) {
                    Hakmilik hk = new Hakmilik();
                    if (StringUtils.isBlank(idHakmilik)) {
                        hk = permohonanSebelum.getSenaraiHakmilik().get(0).getHakmilik();
                    } else {
                        hk = hakmilikDAO.findById(idHakmilik);
                    }
                    if (hk != null) {
                        /* find HAKMILIK_PIHAK information */
                        // kod_pb for ketuan punyaan
                        String[] ketuanPunyaan = {"PM", "PA", "PP", "PK", "RP", "WPA", "KL", "WKL", "WS", "WAR", "JG", "CP", "JA", "PUH", "PAS", "ROS", "JK", "PLK", "PKS", "BP"};
                        // list of all available pihak perkepentingan
                        List<HakmilikPihakBerkepentingan> listPihak = hakmilikPihakKepentinganService.findHakmilikPihakBerkepentinganByIDHakmilik(idHakmilik);
                        for (HakmilikPihakBerkepentingan h : listPihak) {
                            if (h.getAktif() == 'Y') { // get status active                
                                if (ArrayUtils.contains(ketuanPunyaan, h.getJenis().getKod())) { // ketuanpunyaan                   
                                    hpkList1.add(h);
                                }

                                if (permohonanSebelum.getKodUrusan().getKod().startsWith("GD")) { // urusan start with GD
                                    if (h.getJenis().getKod().equals("PG") || h.getJenis().getKod().equals("PPG")
                                            || h.getJenis().getKod().equals("PAG") || h.getJenis().getKod().equals("PGA")) {
                                        hpkList1.add(h);
                                    }
                                }

                                if (permohonanSebelum.getKodUrusan().getKod().equals("PJKT")) { // Pajakan Kecil
                                    if (h.getJenis().getKod().equals("PJK")) {
                                        hpkList1.add(h);
                                    }
                                }

                                if (permohonanSebelum.getKodUrusan().getKod().equals("PJT")
                                        || permohonanSebelum.getKodUrusan().getKod().equals("PJTK")
                                        || permohonanSebelum.getKodUrusan().getKod().equals("PJTM")
                                        || permohonanSebelum.getKodUrusan().getKod().equals("PJTA")) { // urusan start with PJT
                                    if (h.getJenis().getKod().equals("PJ")) {
                                        hpkList1.add(h);
                                    }
                                }

                                if (permohonanSebelum.getKodUrusan().getKod().startsWith("KV")) { // all urusan kaviet
                                    if (h.getJenis().getKod().equals("PKS") || h.getJenis().getKod().equals("PKL")
                                            || h.getJenis().getKod().equals("PKA") || h.getJenis().getKod().equals("PKA")) {
                                        hpkList1.add(h);
                                    }
                                }

                                if (permohonanSebelum.getKodUrusan().getKod().startsWith("IS")) { // all urusan ismen
                                    if (h.getJenis().getKod().equals("PI")) {
                                        hpkList1.add(h);
                                    }
                                }

                                if (permohonanSebelum.getKodUrusan().getKod().equals("TEN") || permohonanSebelum.getKodUrusan().getKod().equals("TENBT")) { // all urusan tenansi
                                    if (h.getJenis().getKod().equals("PT")) {
                                        hpkList1.add(h);
                                    }
                                }

                                if (permohonanSebelum.getKodUrusan().getKod().equals("PB")) { // Perletakhakan Berkanun
                                    if (h.getJenis().getKod().equals("PAP")
                                            || h.getJenis().getKod().equals("PAT")
                                            || h.getJenis().getKod().equals("PAW")
                                            || h.getJenis().getKod().equals("WKF")) {
                                        hpkList1.add(h);
                                    }
                                }

                                if (permohonanSebelum.getKodUrusan().getKod().startsWith("JM")
                                        || permohonanSebelum.getKodUrusan().getKod().startsWith("JP")
                                        || permohonanSebelum.getKodUrusan().getKod().startsWith("JD")) { // all urusan Pindah Jualan
                                    if (h.getJenis().getKod().equals("PG")
                                            || h.getJenis().getKod().equals("PGA")) {
                                        hpkList1.add(h);
                                    }
                                }
//                  hpkList1.add(h);
                                hpkList.add(h);
                            }
                        }
                        LOGGER.info("hpkList1.size() : " + hpkList1.size());
                    }
                }
            }

            /* start:paparkan PEMBERI dan PENERIMA */
            //(1)pemberi
            pemohonList = pemohonService.senaraiPemohonByIdPermohonanIdHakmilik(idPermohonan, idHakmilik);
            wkPberi = suratKuasaService.findWakilKuasaListPemberi(idPermohonan); // for SW / SA
            //(2)penerima
            permohonanPihakList = permohonanPihakService.getSenaraiPmohonPihakByIdMohonAndIdHakmilik(idPermohonan, idHakmilik);
            wkPnerima = suratKuasaService.findWakilKuasaListPenerima(idPermohonan);  // for SW / SA
            /* end:paparkan PEMBERI dan PENERIMA */

            /*Paparkan Maklumat MOHON_RUJ_LUAR*/
            permohonanRujukanLuar = permohonanRujukanLuarService.findMohonRujukLuarIdHakmilikIdPermohonan(idHakmilik, idPermohonan);
            /*Paparkan Maklumat MOHON_ATAS_URUSAN*/
//            permohonanAtasPerserahan = permohonanAtasPerserahanService.findMohonAtasUrusanByIDPermohonanAndIDHakmilik(idPermohonan, idHakmilik);
//            permohonanAtasPerserahanMH = permohonanAtasPerserahanService.findMohonAtasUrusanByIDPermohonanAndIDMohonHakmilik(idPermohonan, idHakmilik);
//            
            permohonanAtasPerserahanList = permohonanAtasPerserahanService.findSenaraiMohonAtasUrusanByIDPermohonan(idPermohonan);
            permohonanAtasPerserahanMHList = permohonanAtasPerserahanService.findSenaraiMohonAtasUrusanByIDPermohonan(idPermohonan);


            /*Paparkan Maklumat Permohonan yg Berkaitan gadaian*/
            permohonanHubunganList = permohonanHubunganService.findMohonAtasUrusanByIDSumberAndIDHakmilik(idPermohonan, idHakmilik);
            //Paparkan Oleh
            if (permohonanSebelum.getKodUrusan().getKod().equals("GDPJL") || permohonanSebelum.getKodUrusan().getKod().equals("PJKT")) {

                for (PermohonanHubungan ph : permohonanHubunganList) {
                    if (ph.getPermohonanSasaran().getIdPermohonan() != null) {
                        permohonanPihakList = permohonanPihakService.getSenaraiPmohonPihakByIdMohonAndIdHakmilik(ph.getPermohonanSasaran().getIdPermohonan(), idHakmilik);
                    }
                }
            }

            if (permohonanSebelum.getIdKumpulan() != null) {
                String idKumpulan = permohonanSebelum.getIdKumpulan();
                permohonanList = permohonanService.findByIdKump(idKumpulan);
                for (Permohonan mohon : permohonanList) {
                    senaraiPermohonan.add(mohon);
                }
            }

            //pemohonList = pemohonService.findPemohonByIdPermohonan(idPermohonan);
            return new JSP("common/paparan_maklumat_sebelum_popup.jsp").addParameter("popup", "true");
        }
        return new ErrorResolution(500, "Id Permohonan Tidak Dikenali.");
    }

    public Resolution viewPermohonan() {
        String idPermohonan = getContext().getRequest().getParameter("idPermohonan");
        if (StringUtils.isNotBlank(idPermohonan)) {
            permohonanSebelum = permohonanDAO.findById(idPermohonan);
            if (permohonanSebelum != null) {
                // add by azri 12/8/2013: change paparan 'pihak berkuatkuasa'
                List<HakmilikPihakBerkepentingan> listPihak = hakmilikPihakKepentinganService.findAllPihakBerkepentinganByIdHakmilik(idHakmilik);
                LOGGER.info("--> listPihak.size() : " + listPihak.size());
                if (!listPihak.isEmpty()) {
                    // if listPihak is not empty, do this
                    for (HakmilikPihakBerkepentingan x : listPihak) {
                        if (x.getAktif() == 'Y') {
                            hpkList1.add(x);
                        }
                    }
                }
                // add by azri 12/8/2013: END
                hu = hakmilikUrusanService.findByIdPerserahan(idPermohonan);

                if (hu != null) {
                    LOGGER.debug("not null");
                    List<HakmilikPihakBerkepentingan> senaraiPihak = hakmilikPihakKepentinganService.findHakmilikPihakByIdUrusan(hu);
                    for (HakmilikPihakBerkepentingan h : senaraiPihak) {
                        if (h.getAktif() == 'Y') {
                            hpkList.add(h);
                        }
                    }
                }
            }
        }
        return new JSP("common/paparan_maklumat_sebelum_popup.jsp").addParameter("popup", "true");
    }

    public List<HakmilikSebelumPermohonan> getSenaraiHakmilikBatal() {
        return senaraiHakmilikBatal;
    }

    public void setSenaraiHakmilikBatal(List<HakmilikSebelumPermohonan> senaraiHakmilikBatal) {
        this.senaraiHakmilikBatal = senaraiHakmilikBatal;
    }

    public List<HakmilikSebelumPermohonan> getSenaraiHakmilikBatalUnique() {
        return senaraiHakmilikBatalUnique;
    }

    public void setSenaraiHakmilikBatalUnique(List<HakmilikSebelumPermohonan> senaraiHakmilikBatalUnique) {
        this.senaraiHakmilikBatalUnique = senaraiHakmilikBatalUnique;
    }

    public int getGroupFlag() {
        return groupFlag;
    }

    public void setGroupFlag(int groupFlag) {
        this.groupFlag = groupFlag;
    }

    public PermohonanUrusan getPermohonanUrusan() {
        return permohonanUrusan;
    }

    public void setPermohonanUrusan(PermohonanUrusan permohonanUrusan) {
        this.permohonanUrusan = permohonanUrusan;
    }

    public KodUrusan getKodUrusan() {
        return kodUrusan;
    }

    public void setKodUrusan(KodUrusan kodUrusan) {
        this.kodUrusan = kodUrusan;
    }

    public PermohonanRujukanLuar getPermohonanRujukanLuar() {
        return permohonanRujukanLuar;
    }

    public void setPermohonanRujukanLuar(PermohonanRujukanLuar permohonanRujukanLuar) {
        this.permohonanRujukanLuar = permohonanRujukanLuar;
    }

    public String getTarikhPerintah() {
        return tarikhPerintah;
    }

    public void setTarikhPerintah(String tarikhPerintah) {
        this.tarikhPerintah = tarikhPerintah;
    }

    public List<HakmilikPermohonan> getHakmilikPermohonanListtemp() {
        return hakmilikPermohonanListtemp;
    }

    public void setHakmilikPermohonanListtemp(List<HakmilikPermohonan> hakmilikPermohonanListtemp) {
        this.hakmilikPermohonanListtemp = hakmilikPermohonanListtemp;
    }

    public void setIdPemohonan1(String idPemohona1) {
        this.idPemohonan1 = idPemohona1;
    }

    public String getIdPemohonan1() {
        return idPemohonan1;
    }

    public void setStageId1(String stageId1) {
        this.stageId1 = stageId1;
    }

    public String getStageId1() {
        return stageId1;
    }

    public void setTaskId1(String taskId1) {
        this.taskId1 = taskId1;
    }

    public String getTaskId1() {
        return taskId1;
    }

    public PermohonanAtasPerserahan getPermohonanAtasPerserahan() {
        return permohonanAtasPerserahan;
    }

    public void setPermohonanAtasPerserahan(PermohonanAtasPerserahan permohonanAtasPerserahan) {
        this.permohonanAtasPerserahan = permohonanAtasPerserahan;
    }

    public List<PermohonanHubungan> getPermohonanHubunganList() {
        return permohonanHubunganList;
    }

    public void setPermohonanHubunganList(List<PermohonanHubungan> permohonanHubunganList) {
        this.permohonanHubunganList = permohonanHubunganList;
    }

    public KodKeputusan getKodKeputusan() {
        return kodKeputusan;
    }

    public void setKodKeputusan(KodKeputusan kodKeputusan) {
        this.kodKeputusan = kodKeputusan;
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

    public PermohonanAtasPerserahan getPermohonanAtasPerserahanMH() {
        return permohonanAtasPerserahanMH;
    }

    public void setPermohonanAtasPerserahanMH(PermohonanAtasPerserahan permohonanAtasPerserahanMH) {
        this.permohonanAtasPerserahanMH = permohonanAtasPerserahanMH;
    }

    public List<WakilKuasaPemberi> getWkPberi() {
        return wkPberi;
    }

    public void setWkPberi(List<WakilKuasaPemberi> wkPberi) {
        this.wkPberi = wkPberi;
    }

    public List<WakilKuasaPenerima> getWkPnerima() {
        return wkPnerima;
    }

    public void setWkPnerima(List<WakilKuasaPenerima> wkPnerima) {
        this.wkPnerima = wkPnerima;
    }

    public List getListBatal() {
        return listBatal;
    }

    public void setListBatal(List listBatal) {
        this.listBatal = listBatal;
    }

    public List<Hakmilik> getListHakmilikBatal() {
        return listHakmilikBatal;
    }

    public void setListHakmilikBatal(List<Hakmilik> listHakmilikBatal) {
        this.listHakmilikBatal = listHakmilikBatal;
    }

    public List<Map<String, String>> getListHakmilikBatal2() {
        return listHakmilikBatal2;
    }

    public void setListHakmilikBatal2(List<Map<String, String>> listHakmilikBatal2) {
        this.listHakmilikBatal2 = listHakmilikBatal2;
    }

    public List<Permohonan> getSenaraiPermohonan() {
        return senaraiPermohonan;
    }

    public void setSenaraiPermohonan(List<Permohonan> senaraiPermohonan) {
        this.senaraiPermohonan = senaraiPermohonan;
    }

    public List<Permohonan> getPermohonanList() {
        return permohonanList;
    }

    public void setPermohonanList(List<Permohonan> permohonanList) {
        this.permohonanList = permohonanList;
    }

    public List<PermohonanAtasPerserahan> getPermohonanAtasPerserahanList() {
        return permohonanAtasPerserahanList;
    }

    public void setPermohonanAtasPerserahanList(List<PermohonanAtasPerserahan> permohonanAtasPerserahanList) {
        this.permohonanAtasPerserahanList = permohonanAtasPerserahanList;
    }

    public List<PermohonanAtasPerserahan> getPermohonanAtasPerserahanMHList() {
        return permohonanAtasPerserahanMHList;
    }

    public void setPermohonanAtasPerserahanMHList(List<PermohonanAtasPerserahan> permohonanAtasPerserahanMHList) {
        this.permohonanAtasPerserahanMHList = permohonanAtasPerserahanMHList;
    }

    public String getIdCP() {
        return idCP;
    }

    public void setIdCP(String idCP) {
        this.idCP = idCP;
    }

    public List<WakilKuasa> getWakilKuasaList() {
        return wakilKuasaList;
    }

    public void setWakilKuasaList(List<WakilKuasa> wakilKuasaList) {
        this.wakilKuasaList = wakilKuasaList;
    }

    public String getIdPermohonanCP() {
        return idPermohonanCP;
    }

    public void setIdPermohonanCP(String idPermohonanCP) {
        this.idPermohonanCP = idPermohonanCP;
    }

}
