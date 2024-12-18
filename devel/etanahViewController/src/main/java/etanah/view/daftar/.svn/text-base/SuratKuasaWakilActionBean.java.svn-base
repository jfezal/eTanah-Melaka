/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.daftar;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.dao.HakmilikDAO;
import etanah.dao.HakmilikPermohonanDAO;
import etanah.dao.HakmilikPihakBerkepentinganDAO;
import etanah.dao.KodCawanganDAO;
import etanah.dao.KodJenisPengenalanDAO;
import etanah.dao.KodNegeriDAO;
import etanah.dao.PermohonanDAO;
import etanah.dao.PihakDAO;
import etanah.dao.WakilKuasaDAO;
import etanah.dao.WakilKuasaHakmilikDAO;
import etanah.dao.WakilKuasaPemberiDAO;
import etanah.dao.WakilKuasaPenerimaDAO;
import etanah.model.Dokumen;
import etanah.model.FolderDokumen;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.model.WakilHakmilik;
//import etanah.model.WakilPihak;
//import etanah.dao.WakilPihakDAO;
import etanah.model.Hakmilik;
import etanah.model.HakmilikPermohonan;
import etanah.model.HakmilikPihakBerkepentingan;
import etanah.model.HakmilikUrusan;
import etanah.model.InfoAudit;
import etanah.model.KandunganFolder;
import etanah.model.KodCawangan;
import etanah.model.KodDokumen;
import etanah.model.KodJenisPengenalan;
import etanah.model.KodNegeri;
import etanah.model.PermohonanAtasPerserahan;
import etanah.model.PermohonanDokumen;
import etanah.model.Pihak;
import etanah.model.WakilKuasa;
import etanah.model.WakilKuasaHakmilik;
import etanah.model.WakilKuasaPemberi;
import etanah.model.WakilKuasaPenerima;
import etanah.model.KodJenisSuratKuasa;
import etanah.service.common.PihakService;
import etanah.service.daftar.PendaftaranSuratKuasaService;
import etanah.view.etanahActionBeanContext;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import javax.swing.JOptionPane;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.RedirectResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import etanah.sequence.GeneratorIdWakil;
import etanah.service.daftar.BatalNotaService;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import net.sourceforge.stripes.action.ForwardResolution;

/**
 *
 * @author mohd.fairul Fix by Aizuddin
 */
@UrlBinding("/daftar/suratkuasawakil")
public class SuratKuasaWakilActionBean extends AbleActionBean {

    @Inject
    PermohonanDAO permohonanDAO;
//    @Inject
//    WakilPihakDAO wakilPihakDAO;
    @Inject
    PendaftaranSuratKuasaService suratkuasaService;
    @Inject
    PihakService pihakService;
    @Inject
    BatalNotaService batalNotaService;
    @Inject
    KodJenisPengenalanDAO kodjenisPengenalanDAO;
    @Inject
    PihakDAO pihakDAO;
    @Inject
    HakmilikDAO hakmilikDAO;
    @Inject
    KodNegeriDAO negeriDAO;
    @Inject
    private GeneratorIdWakil idWakilGenerator;
    @Inject
    HakmilikPihakBerkepentinganDAO hakmilikPihakBerkepentinganDAO;
    @Inject
    KodCawanganDAO cawanganDAO;
    @Inject
    HakmilikPermohonanDAO hakmilikPermohonanDAO;
    @Inject
    WakilKuasaPenerimaDAO wakilKuasaPenerimaDAO;
    @Inject
    WakilKuasaPemberiDAO wakilKuasaPemberiDAO;
    @Inject
    WakilKuasaDAO wakilKuasaDAO;
    @Inject
    WakilKuasaHakmilikDAO wakilKuasaHakmilikDAO;
    private String idPermohonan;
    private WakilKuasaHakmilik wakilKuasaHakmilik;
//    private WakilPihak wakilPihak;//will delete
    //New Table.....
    private WakilKuasa wakilKuasa;
    private WakilKuasa wakilKuasaB;
    private WakilKuasaPemberi wakilKuasaPemberi;
    private WakilKuasaPenerima wakilKuasaPenerima;
    private String amaunMaksima;
    private String syaratTambahan;
    private Permohonan permohonan;
    private Permohonan mhn;
    private String kodSerah;
    private Pihak pihak;
    private String noKP;
    private String kodKP;
    private String noKadPengenalan;
    private boolean flagCarian;
    private KodJenisPengenalan kodjenisPengenalan;
    private String namaPemberi;
    private String namaPenerima2;
    private String noKP2;
    private String kodKP2;
    private String jenisPengenalanPemberi;
    private String noPengenalanPemberi;
    private InfoAudit info;
    private String tmpIDpihak;
    private String namaPenerima;
    private String alamat1;
    private String alamat2;
    private String alamat3;
    private String alamat4;
    private String poskod;
    private String negeriPenerima;
    private KodNegeri negeri;
    private KodCawangan cawangan;
    private String jenisPengenalanPenerima;
    private String noPengenalanPenerima;
    private List<HakmilikPihakBerkepentingan> hpB;
    private List<HakmilikPihakBerkepentingan> hakmilikPihakBerkepentinganList;
    private List<HakmilikPihakBerkepentingan> hakmilikPihakBerkepentinganList2;
    private List<HakmilikPermohonan> hakmilikPermohonanList;
    private HakmilikPihakBerkepentingan hakmilikPihakBerkepentingan;
    private Pengguna peng;
    String pattern1 = "dd/MM/yyyy";
    SimpleDateFormat dateFormat = new SimpleDateFormat(pattern1);
    Calendar calendar = new GregorianCalendar();
    private Date tarikhDaftar;
    private String tkhDaftar;
    private int size;
//    private int sizeWH;
    private String kuasaL1;
    private String kuasaL2;
    private Hakmilik hakmilik;
    private String idH;
    private String idPihakPemberi;
    private String idPenerima;
    private String hakmilikWakil;
    private String idPemberi;
    List<WakilKuasaHakmilik> wKuasaHakmilik;
    List<WakilKuasa> wKuasa;
    List<Pihak> pihakList;
    private String jPihak;
    private List<KandunganFolder> senaraiKF;
    private List<Dokumen> dokumen;
//    private List<KandunganFolder> senaraiKF;
    private List<String> senaraiSW = new ArrayList<String>();
    private static final Logger LOG = Logger.getLogger(SuratKuasaWakilActionBean.class);
    private String[] noRujukan;
    private String[] idHakmilikSW;
    private String jenisSurat;
    private String jenisSuratLain2;
//    private static final boolean IS_LOG_DEBUG = LOG.isDebugEnabled();

    @DefaultHandler
    public Resolution maklumatPemPen() {
        return new JSP("daftar/maklumat_pemberi_penerima_suratkuasa_wakil.jsp").addParameter("tab", "true");
    }

    public Resolution maklumatSurat() {
        String msg = getContext().getRequest().getParameter("message");
        String error = getContext().getRequest().getParameter("error");
        if (StringUtils.isNotBlank(msg)) {
            addSimpleMessage(msg);
        }
        if (StringUtils.isNotBlank(error)) {
            addSimpleError(error);
        }
        return new JSP("daftar/tempohSuratKuasa.jsp").addParameter("tab", "true");
    }

    public Resolution senaraiHakmilik() {
        return new JSP("daftar/jenis_suratkuasa.jsp").addParameter("tab", "true");
    }

    public Resolution bidangKuasa() {
        return new JSP("daftar/bidang_kuasa.jsp").addParameter("tab", "true");
    }

    public Resolution cariPemberiPihak() {
        return new JSP("daftar/popUpCarianPihak.jsp").addParameter("popup", "true");
    }

    public Resolution showWakilForm() {
        boolean flag = false;
        if (StringUtils.isNotBlank(idPermohonan)) {
            Permohonan mhnSWDB = permohonanDAO.findById(idPermohonan);
            //Added by Aizuddin to show pemberi penerima SWDB
            if (mhnSWDB.getKodUrusan().getKod().equalsIgnoreCase("SWDB")) {
                LOG.info("::::::::ID MOHON:::::::::::" + idPermohonan);
                List<PermohonanAtasPerserahan> listMohon = batalNotaService.findByidPermohonan(idPermohonan);
                if (listMohon.size() > 0) {
                    PermohonanAtasPerserahan pap = listMohon.get(0);
                    String idBatal = pap.getUrusan().getIdPerserahan();
                    LOG.info("::::::::ID MOHON BATAL:::::::::::" + idBatal);
                    mhn = permohonanDAO.findById(idBatal);

                    if (mhn == null) {
                        addSimpleError("Permohonan dipilih tidak dikenali.");
                        return new JSP("common/msg.jsp").addParameter("tab", "true");
                    }
                } else {
                    if(!mhnSWDB.getSenaraiHakmilik().isEmpty())
                    addSimpleError("Sila pilih perserahan terlibat.");
                }

            } else {
                mhn = permohonanDAO.findById(idPermohonan);
            }

            if (mhn == null) {
                if (!mhnSWDB.getKodUrusan().getKod().equalsIgnoreCase("SWDB")) {
                    addSimpleError("Permohonan Tidak Dikenali.");
                    return new JSP("common/msg.jsp").addParameter("tab", "true");
                }
            } else {
                if (mhnSWDB.getKodUrusan().getKod().equalsIgnoreCase("SWDB")) {
                    LOG.info("::::ID_PERMOHONAN====" + mhn.getIdPermohonan());
                    List<WakilKuasa> SWtmp = suratkuasaService.findWakilKuasaList(mhn.getIdPermohonan());
                    for (WakilKuasa wk : SWtmp) {
                        wKuasa.add(wk);
                    }
                    LOG.info("::::Pemberi====" + wKuasa.get(0).getSenaraiPemberi().get(0).getPihak().getNama());
                    LOG.info("::::Penerima====" + wKuasa.get(0).getSenaraiPenerima().get(0).getNama());
                    flag = true;
                } else {
                    if (mhn.getFolderDokumen() != null) {
                        List<KandunganFolder> senaraiDokumen = mhn.getFolderDokumen().getSenaraiKandungan();
                        List<WakilKuasa> senarai_tmp = new ArrayList<WakilKuasa>();
                        wKuasa = new ArrayList<WakilKuasa>();
                        for (KandunganFolder kf : senaraiDokumen) {
                            if (kf == null || kf.getDokumen() == null) {
                                continue;
                            }
                            KodDokumen kd = kf.getDokumen().getKodDokumen();
                            LOG.info("::::::::KOD DOKUMEN:::::::::::" + kd.getKod());
                            if (kd.getKod().equals("SWB") || kd.getKod().equals("SWD")) {
                                if (kd.getKod().equals("SWD")) {
                                    LOG.info("::::ID_PERMOHONAN====" + kf.getDokumen().getNoRujukan());
                                    if (StringUtils.isNotBlank(kf.getDokumen().getNoRujukan())) {
                                        senarai_tmp = suratkuasaService.findWakilKuasaList(kf.getDokumen().getNoRujukan().trim());
                                        LOG.info("masuk :: 1234567890");

                                        for (WakilKuasa wk : senarai_tmp) {

                                            wKuasa.add(wk);
                                        }
                                    }
                                }
                                flag = true;
                                break;
                            }
                        }
                    }
                }
            }
        }

        if (flag) {
            if (wKuasa != null) {
                getContext().getRequest().setAttribute("noEditable", Boolean.TRUE);
            }
            return maklumatPemPen();
        } else {
            addSimpleMessage("Tiada Maklumat Untuk Surat Kuasa Wakil.");
            return new JSP("common/msg.jsp").addParameter("tab", "true");
        }
    }

    public Resolution showWakilFormSWDB() {
        boolean flag = false;
        if (StringUtils.isNotBlank(idPermohonan)) {
            Permohonan mhnSWDB = permohonanDAO.findById(idPermohonan);
            //Added by Aizuddin to show pemberi penerima SWDB

                
                    if (mhnSWDB.getFolderDokumen() != null) {
                        List<KandunganFolder> senaraiDokumen = mhnSWDB.getFolderDokumen().getSenaraiKandungan();
                        List<WakilKuasa> senarai_tmp = new ArrayList<WakilKuasa>();
                        wKuasa = new ArrayList<WakilKuasa>();
                        for (KandunganFolder kf : senaraiDokumen) {
                            if (kf == null || kf.getDokumen() == null) {
                                continue;
                            }
                            KodDokumen kd = kf.getDokumen().getKodDokumen();
                            LOG.info("::::::::KOD DOKUMEN:::::::::::" + kd.getKod());
                            if (kd.getKod().equals("SWB") || kd.getKod().equals("SWD")) {
                                if (kd.getKod().equals("SWD") || kd.getKod().equals("SWB")) {
                                    LOG.info("::::ID_PERMOHONAN====" + kf.getDokumen().getNoRujukan());
                                    if (StringUtils.isNotBlank(kf.getDokumen().getNoRujukan())) {
                                        senarai_tmp = suratkuasaService.findWakilKuasaList(kf.getDokumen().getNoRujukan().trim());
                                        LOG.info("masuk :: 1234567890");

                                        for (WakilKuasa wk : senarai_tmp) {

                                            wKuasa.add(wk);
                                        }
                                    }
                                }
                                flag = true;
                                break;
                            }
                        }
                    }
                
            
        }

        if (flag) {
            if (wKuasa != null) {
                getContext().getRequest().setAttribute("noEditable", Boolean.TRUE);
            }
            return maklumatPemPen();
        } else {
            addSimpleMessage("Tiada Maklumat Untuk Surat Kuasa Wakil.");
            return new JSP("common/msg.jsp").addParameter("tab", "true");
        }
    }
    
     public Resolution showWakilFormSADB() {
        boolean flag = false;
        if (StringUtils.isNotBlank(idPermohonan)) {
            Permohonan mhnSWDB = permohonanDAO.findById(idPermohonan);
            //Added by Aizuddin to show pemberi penerima SWDB

                
                    if (mhnSWDB.getFolderDokumen() != null) {
                        List<KandunganFolder> senaraiDokumen = mhnSWDB.getFolderDokumen().getSenaraiKandungan();
                        List<WakilKuasa> senarai_tmp = new ArrayList<WakilKuasa>();
                        wKuasa = new ArrayList<WakilKuasa>();
                        for (KandunganFolder kf : senaraiDokumen) {
                            if (kf == null || kf.getDokumen() == null) {
                                continue;
                            }
                            KodDokumen kd = kf.getDokumen().getKodDokumen();
                            LOG.info("::::::::KOD DOKUMEN:::::::::::" + kd.getKod());
                            if (kd.getKod().equals("SAB") || kd.getKod().equals("SAD")) {
                                if (kd.getKod().equals("SAD") || kd.getKod().equals("SAB")) {
                                    LOG.info("::::ID_PERMOHONAN====" + kf.getDokumen().getNoRujukan());
                                    if (StringUtils.isNotBlank(kf.getDokumen().getNoRujukan())) {
                                        senarai_tmp = suratkuasaService.findWakilKuasaList(kf.getDokumen().getNoRujukan().trim());
                                        LOG.info("masuk :: 1234567890");

                                        for (WakilKuasa wk : senarai_tmp) {

                                            wKuasa.add(wk);
                                        }
                                    }
                                }
                                flag = true;
                                break;
                            }
                        }
                    }
                
            
        }

        if (flag) {
            if (wKuasa != null) {
                getContext().getRequest().setAttribute("noEditable", Boolean.TRUE);
            }
            return maklumatPemPen();
        } else {
            addSimpleMessage("Tiada Maklumat Untuk Surat Kuasa Wakil.");
            return new JSP("common/msg.jsp").addParameter("tab", "true");
        }
    }
      
    public Resolution paparPemberi() {
        return new JSP("daftar/paparPemberi.jsp").addParameter("tab", "true");
    }

    public Resolution popUpKemasukanPenerimaWakil() {
        pihak = pihakDAO.findById(Long.parseLong(idPihakPemberi));
        LOG.info("Kod Serah:: " + kodSerah);
        if (jPihak.equalsIgnoreCase("penerima") && kodSerah.equalsIgnoreCase("SW")) {
//            return savePihak();
            return new JSP("daftar/popUpKemasukanPenerimaWakil.jsp").addParameter("popup", "true");
        } else {
            return saveCarianPihak();
        }

    }

    public Resolution tentukanHakmilik() {



        FolderDokumen fd = permohonan.getFolderDokumen();
        senaraiKF = fd.getSenaraiKandungan();
//            senaraiKF = suratkuasaService.findSWSahaja(idPermohonan);
        dokumen = new ArrayList<Dokumen>();
        if (senaraiKF != null) {
            for (KandunganFolder kf : senaraiKF) {
                if (kf != null) {
                    Dokumen d = kf.getDokumen();
                    if (d.getKodDokumen().getKod().equals("SWD")) {
//                            wakilkuasa = pService.findWakilKuasa(d.getNoRujukan());
                        LOG.info("Test ID Rujukan:: " + d.getNoRujukan());
                        if (StringUtils.isNotBlank(d.getNoRujukan())) {
                            dokumen.add(d);
                        }
                    }
//                        LOG.info("Test ID Rujukan:: "+ d.getNoRujukan());
                }
            }
        }


        return new JSP("daftar/tentukanSurat.jsp").addParameter("tab", "true");

    }

    public Resolution saveTentukanHakmilik() {
        int counter = 0;
        FolderDokumen fd = permohonan.getFolderDokumen();
        senaraiKF = fd.getSenaraiKandungan();
//            senaraiKF = suratkuasaService.findSWSahaja(idPermohonan);
        dokumen = new ArrayList<Dokumen>();
        if (senaraiKF != null) {
            for (KandunganFolder kf : senaraiKF) {
                if (kf != null) {
                    Dokumen d = kf.getDokumen();
                    if (d.getKodDokumen().getKod().equals("SWD")) {
//                            wakilkuasa = pService.findWakilKuasa(d.getNoRujukan());
                        LOG.info("Test ID Rujukan:: " + d.getNoRujukan());
                        if (StringUtils.isNotBlank(d.getNoRujukan())) {
                            dokumen.add(d);
                        }
                    }
//                        LOG.info("Test ID Rujukan:: "+ d.getNoRujukan());
                }
            }
        }
        for (Dokumen dk : dokumen) {
            LOG.info("Test" + dk.getNoRujukan());
//       LOG.info("Id Hakmilik"+ idHakmilikSW[counter]);
            LOG.info("no Rujukan" + noRujukan[counter]);

            counter = counter + 1;
        }


        return new RedirectResolution(SuratKuasaWakilActionBean.class, "tentukanHakmilik");
    }

    @Before(stages = {LifecycleStage.BindingAndValidation}, on = {"!saveOrUpdate"})
    public void rehydrate() {
        flagCarian = false;
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        peng = (Pengguna) context.getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);

        if (idPermohonan != null) {
            permohonan = permohonanDAO.findById(idPermohonan);
            kodSerah = permohonan.getKodUrusan().getKodPerserahan().getKod();

            //Comment out by Aizuddin
//            if (permohonan.getKodUrusan().getKod().equalsIgnoreCase("SW")) {
            wKuasa = suratkuasaService.findWakilKuasaList(idPermohonan);
            permohonan = permohonanDAO.findById(idPermohonan);
            hakmilikPermohonanList = permohonan.getSenaraiHakmilik();
            hakmilikPihakBerkepentinganList2 = new ArrayList<HakmilikPihakBerkepentingan>();

            if (hakmilikPermohonanList.size() != 0) {
                for (HakmilikPermohonan hP : hakmilikPermohonanList) {
                    hakmilikPihakBerkepentinganList = suratkuasaService.findHakmilikOnly(hP.getHakmilik().getIdHakmilik());
                    if (hakmilikPihakBerkepentinganList.size() != 0) {
                        for (HakmilikPihakBerkepentingan hPBP : hakmilikPihakBerkepentinganList) {
                            hakmilikPihakBerkepentinganList2.add(hPBP);
                        }

                    }

                }
            }
            {
            }
            //Comment out by Aizuddin so this page not base on urusan SW only
//            } else {
//                LOG.info("Not SW so do nothing");
//                //fixme. temp solution, fairul faisal to fix it after honeymoon..
//                wKuasa = suratkuasaService.findWakilKuasaList(idPermohonan);
//            }

            //Added by Aizuddin to rehydrate maklumat pemberi and penerima
//            WakilKuasaPenerima wkpenerima = suratkuasaService.findWakilPenerimabyIdPermohonan(idPermohonan);
//            Pihak pk = 
            LOG.info("====Wakil Kuasa Size===" + wKuasa.size());
            if (wKuasa.size() != 0) {
                for (WakilKuasa wakilKuasaList : wKuasa) {
//                if (jPihak.equalsIgnoreCase("pemberi")) {
                    for (WakilKuasaPemberi pemberiList : wakilKuasaList.getSenaraiPemberi()) {
                        Pihak pk = pemberiList.getPihak();
                        if (StringUtils.isNotBlank(pk.getNama())) {
                            namaPemberi = pk.getNama();
                        } else {
                            namaPemberi = "";
                        }
                        if (StringUtils.isNotBlank(pk.getJenisPengenalan().getKod())) {
                            kodKP = pk.getJenisPengenalan().getKod();
                        } else {
                            kodKP = "";
                        }
                        if (StringUtils.isNotBlank(pk.getNoPengenalan())) {
                            noKP = pk.getNoPengenalan();
                        } else {
                            noKP = "";
                        }
                    }
//                }
//                if (jPihak.equalsIgnoreCase("penerima")) {
                    for (WakilKuasaPenerima penerimaList : wakilKuasaList.getSenaraiPenerima()) {
                        if (StringUtils.isNotBlank(penerimaList.getNama())) {
                            namaPenerima2 = penerimaList.getNama();
                        } else {
                            namaPenerima2 = "";
                        }
                        if (StringUtils.isNotBlank(penerimaList.getJenisPengenalan().getKod())) {
                            kodKP2 = penerimaList.getJenisPengenalan().getKod();
                        } else {
                            kodKP2 = "";
                        }
                        if (StringUtils.isNotBlank(penerimaList.getJenisPengenalan().getKod())) {
                            noKP2 = penerimaList.getNoPengenalan();
                        } else {
                            noKP2 = "";
                        }
                        if (permohonan.getKodUrusan().getKodPerserahan().getKod().equalsIgnoreCase("SW")) {
                            //Added by Aizuddin to rehydrate Amaun Maksima & Syarat Tamabahan
                            if (penerimaList.getAmaunMaksima() != null) {
                                amaunMaksima = penerimaList.getAmaunMaksima().toString();
                            } else {
                                amaunMaksima = "";
                            }
                            if (StringUtils.isNotBlank(wakilKuasaList.getSyaratTambahan())) {
                                syaratTambahan = wakilKuasaList.getSyaratTambahan();
                            } else {
                                syaratTambahan = "";
                            }
                        }
                    }
//                }
                }
            }
        }
    }

    public Resolution savePihak() {
        Pihak pihakBaru = new Pihak();
        info = peng.getInfoAudit();
        info.setDimasukOleh(peng);
        info.setTarikhMasuk(new java.util.Date());
        wakilKuasa = suratkuasaService.findWakilKuasa(idPermohonan);
        KodJenisPengenalan jp = new KodJenisPengenalan();
        namaPemberi = namaPemberi.replace("_", "&").replace("..", "'");
        
        if(!(jenisPengenalanPemberi.equalsIgnoreCase("X")||jenisPengenalanPemberi.equalsIgnoreCase("0"))){
        if (suratkuasaService.findExistPihak(noPengenalanPemberi, jenisPengenalanPemberi, namaPemberi).size() == 0) {
            pihakBaru.setInfoAudit(info);
            LOG.info("Nama Pemberi::::::" + namaPemberi.replaceAll("__&&", "&"));
            pihakBaru.setNama(namaPemberi);
            LOG.info("No Pengenalan Pemberi::::::" + noPengenalanPemberi);
            pihakBaru.setNoPengenalan(noPengenalanPemberi);
            LOG.info("Jenis Pengenalan Pemberi::::::" + jenisPengenalanPemberi);
            jp = kodjenisPengenalanDAO.findById(jenisPengenalanPemberi);
            pihakBaru.setJenisPengenalan(jp);
            suratkuasaService.savePihak(pihakBaru);

            pihak = new Pihak();
            pihak = pihakBaru;
            //Start Saving

        } else {
            pihakList = suratkuasaService.findExistPihak(noPengenalanPemberi, jenisPengenalanPemberi, namaPemberi);
            for (Pihak p : pihakList) {
                pihak = p;
            }

        }
        }else{
            pihakBaru.setInfoAudit(info);
            LOG.info("Nama Pemberi::::::" + namaPemberi);
            pihakBaru.setNama(namaPemberi);
            LOG.info("No Pengenalan Pemberi::::::" + noPengenalanPemberi);
            pihakBaru.setNoPengenalan(noPengenalanPemberi);
            LOG.info("Jenis Pengenalan Pemberi::::::" + jenisPengenalanPemberi);
            jp = kodjenisPengenalanDAO.findById(jenisPengenalanPemberi);
            pihakBaru.setJenisPengenalan(jp);
            suratkuasaService.savePihak(pihakBaru);

            pihak = new Pihak();
            pihak = pihakBaru;
        }
        //Start Saving
        if (wakilKuasa == null) {
            wakilKuasa = new WakilKuasa();
            wakilKuasa.setInfoAudit(info);
            wakilKuasa.setCawangan(peng.getKodCawangan());
            wakilKuasa.setPermohonan(permohonan);
            //TODO: Re-set idWakil
            wakilKuasa.setIdWakil(permohonan.getIdPermohonan());
            wakilKuasa.setAktif('T');
            wakilKuasa.setKuasaAm('T');
            wakilKuasa.setKuasaGadai('T');
            wakilKuasa.setKuasaKaveat('T');
            wakilKuasa.setKuasaLepasGadai('T');
            wakilKuasa.setKuasaLepasKaveat('T');
            wakilKuasa.setKuasaPajak('T');
            wakilKuasa.setKuasaPajakKecil('T');
            wakilKuasa.setKuasaPindahMilik('T');
            wakilKuasa.setKuasaSewa('T');
            wakilKuasa.setKuasaTarikKaveat('T');
            wakilKuasa.setUntukSemuaHakmilik('T');
            suratkuasaService.saveWakilKuasa(wakilKuasa);
            LOG.info("==========Jenis Pihak============" + jPihak);
            if (jPihak.equalsIgnoreCase("pemberi")) {

                simpanPemberi(info, pihak, wakilKuasa, peng);

            } else if (jPihak.equalsIgnoreCase("penerima")) {

                simpanPenerima(info, wakilKuasa, peng, pihak.getNama(), pihak.getNoPengenalan(), pihak.getJenisPengenalan());
            }


        } else {
            LOG.info("==========Jenis Pihak 2============" + jPihak);
            if (jPihak.equalsIgnoreCase("pemberi")) {
                simpanPemberi(info, pihak, wakilKuasa, peng);
            } else if (jPihak.equalsIgnoreCase("penerima")) {
                simpanPenerima(info, wakilKuasa, peng, pihak.getNama(), pihak.getNoPengenalan(), pihak.getJenisPengenalan());
            }

            LOG.info("SIMPAN SUCCESS");
            
        }

//        return new RedirectResolution(SuratKuasaWakilActionBean.class);
      //  return new JSP("daftar/maklumat_pemberi_penerima_suratkuasa_wakil.jsp").addParameter("tab", "true");
        return new RedirectResolution(SuratKuasaWakilActionBean.class, "maklumatPemPen").addParameter("tab", "true");


    }
    public Resolution saveCarianPihak() {
        LOG.info("Pihak cari~~~~" + pihak.getIdPihak());
        info = peng.getInfoAudit();
        info.setDimasukOleh(peng);
        info.setTarikhMasuk(new java.util.Date());
        wakilKuasa = suratkuasaService.findWakilKuasa(idPermohonan);
        KodJenisPengenalan jp = new KodJenisPengenalan();


        //Start Saving
        if (wakilKuasa == null) {
            wakilKuasa = new WakilKuasa();
            wakilKuasa.setInfoAudit(info);
            wakilKuasa.setCawangan(peng.getKodCawangan());
            wakilKuasa.setPermohonan(permohonan);
            //TODO: Re-set idWakil
            wakilKuasa.setIdWakil(permohonan.getIdPermohonan());
            wakilKuasa.setAktif('T');
            wakilKuasa.setKuasaAm('T');
            wakilKuasa.setKuasaGadai('T');
            wakilKuasa.setKuasaKaveat('T');
            wakilKuasa.setKuasaLepasGadai('T');
            wakilKuasa.setKuasaLepasKaveat('T');
            wakilKuasa.setKuasaPajak('T');
            wakilKuasa.setKuasaPajakKecil('T');
            wakilKuasa.setKuasaPindahMilik('T');
            wakilKuasa.setKuasaSewa('T');
            wakilKuasa.setKuasaTarikKaveat('T');
            wakilKuasa.setUntukSemuaHakmilik('T');
            suratkuasaService.saveWakilKuasa(wakilKuasa);
            LOG.info("==========Jenis Pihak============" + jPihak);
            if (jPihak.equalsIgnoreCase("pemberi")) {

                simpanPemberi(info, pihak, wakilKuasa, peng);

            } else if (jPihak.equalsIgnoreCase("penerima")) {

                simpanPenerima(info, wakilKuasa, peng, pihak.getNama(), pihak.getNoPengenalan(), pihak.getJenisPengenalan());
            }


        } else {
            LOG.info("==========Jenis Pihak 2============" + jPihak);
            if (jPihak.equalsIgnoreCase("pemberi")) {
                simpanPemberi(info, pihak, wakilKuasa, peng);
            } else if (jPihak.equalsIgnoreCase("penerima")) {
                simpanPenerima(info, wakilKuasa, peng, pihak.getNama(), pihak.getNoPengenalan(), pihak.getJenisPengenalan());
            }

            LOG.info("SIMPAN SUCCESS");
            
        }

//        return new RedirectResolution(SuratKuasaWakilActionBean.class);
      //  return new JSP("daftar/maklumat_pemberi_penerima_suratkuasa_wakil.jsp").addParameter("tab", "true");
        return new RedirectResolution(SuratKuasaWakilActionBean.class, "maklumatPemPen").addParameter("tab", "true");


    }
    
    //add new function for update jenis surat
    public Resolution saveJenisSurat(){

        permohonan = permohonanDAO.findById(idPermohonan);
        wakilKuasaB = suratkuasaService.findWakilKuasa(idPermohonan);
        LOG.info("kod urusan"+permohonan.getKodUrusan().getKod());
        //String a = String.valueOf(wakilKuasa.getKuasaAm()));
        KodJenisSuratKuasa jsb = new KodJenisSuratKuasa();
try{
        String results = String.valueOf(wakilKuasa.getJenisSuratKuasa().getKod());
       LOG.info("jenis surat~~~~"+wakilKuasa.getJenisSuratKuasa().getKod());

        if("1".equalsIgnoreCase(results)){
           jsb.setKod(wakilKuasa.getJenisSuratKuasa().getKod());
           wakilKuasaB.setJenisSuratKuasa(jsb);
        }
        if("2".equalsIgnoreCase(results)){
           jsb.setKod(wakilKuasa.getJenisSuratKuasa().getKod());
           wakilKuasaB.setJenisSuratKuasa(jsb);
        }
        if("3".equalsIgnoreCase(results)){
           jsb.setKod(wakilKuasa.getJenisSuratKuasa().getKod());
           wakilKuasaB.setJenisSuratKuasa(jsb);
        }
        if("4".equalsIgnoreCase(results)){
           jsb.setKod(wakilKuasa.getJenisSuratKuasa().getKod());
           wakilKuasaB.setJenisSuratKuasa(jsb);
        }
        if("5".equalsIgnoreCase(results)){
           jsb.setKod(wakilKuasa.getJenisSuratKuasa().getKod());
           wakilKuasaB.setJenisSuratKuasa(jsb);
        }
        if("6".equalsIgnoreCase(results)){
           jsb.setKod(wakilKuasa.getJenisSuratKuasa().getKod());
           wakilKuasaB.setJenisSuratKuasa(jsb);
        }
        if("7".equalsIgnoreCase(results)){
           jsb.setKod(wakilKuasa.getJenisSuratKuasa().getKod());
           wakilKuasaB.setJenisSuratKuasa(jsb);
        }
        if("8".equalsIgnoreCase(results)){
           jsb.setKod(wakilKuasa.getJenisSuratKuasa().getKod());
           wakilKuasaB.setJenisSuratKuasa(jsb);
        }
        if("9".equalsIgnoreCase(results)){
           jsb.setKod(wakilKuasa.getJenisSuratKuasa().getKod());
           wakilKuasaB.setJenisSuratKuasa(jsb);
        }
        if("10".equalsIgnoreCase(results)){
           jsb.setKod(wakilKuasa.getJenisSuratKuasa().getKod());
           wakilKuasaB.setJenisSuratKuasa(jsb);
        }
        if("11".equalsIgnoreCase(results)){
           jsb.setKod(wakilKuasa.getJenisSuratKuasa().getKod());
           wakilKuasaB.setJenisSuratKuasa(jsb);
        }
        if(wakilKuasa.getKuasaLain1() != null){
           wakilKuasaB.setKuasaLain1(wakilKuasa.getKuasaLain1());
        }
        else{
            wakilKuasaB.setKuasaLain1(null);
        }
        
        suratkuasaService.updateWakilKuasa(wakilKuasaB);          
        addSimpleMessage("Maklumat berjaya disimpan.");
        
    }catch (Exception ex) {
      addSimpleError("Sila pilih jenis surat kebenaran");
    }
//     }else{
//         wakilKuasaB.setKuasaAm('T');
//         wakilKuasaB.setKuasaPindahMilik('T');
//         wakilKuasaB.setKuasaGadai('T');
//         wakilKuasaB.setKuasaLepasGadai('T');
//         wakilKuasaB.setKuasaKaveat('T');
//         wakilKuasaB.setKuasaTarikKaveat('T');
//         wakilKuasaB.setKuasaLepasKaveat('T');
//         wakilKuasaB.setKuasaPajak('T');
//         wakilKuasaB.setKuasaPajakKecil('T');
//         wakilKuasaB.setKuasaSewa('T');
//         suratkuasaService.updateWakilKuasa(wakilKuasaB);
//     }

        return new JSP("daftar/bidang_kuasa.jsp").addParameter("tab", "true");
    }

    public Resolution savePopup() {
        permohonan = permohonanDAO.findById(idPermohonan);
        pihak = pihakDAO.findById(Long.parseLong(idPihakPemberi));
        hakmilikPermohonanList = permohonan.getSenaraiHakmilik();

        KodJenisPengenalan kodjenisPengenalanPenerima = kodjenisPengenalanDAO.findById(jenisPengenalanPenerima);

        LOG.info("ID MOHON::" + permohonan.getIdPermohonan());
        LOG.info("ID PIHAK::" + pihak.getIdPihak());
        LOG.info("ID DIMASUK::" + peng.getNama());
        LOG.info("Nama Penerima::" + namaPenerima);
        LOG.info("ID Pihak::" + pihak.getNama());
        LOG.info("No Pengenalan::" + noPengenalanPenerima);
        LOG.info("Jenis Pengenalan::" + kodjenisPengenalanPenerima.getKod());
        wakilKuasa = suratkuasaService.findWakilKuasa(idPermohonan);
        info = peng.getInfoAudit();
        info.setDimasukOleh(peng);
        info.setTarikhMasuk(new java.util.Date());
        cawangan = cawanganDAO.findById(peng.getKodCawangan().getKod());
        LOG.info("WakilKuasa::" + wakilKuasa);

        //Start Saving
        if (wakilKuasa == null) {
            wakilKuasa = new WakilKuasa();
            wakilKuasa.setInfoAudit(info);
            wakilKuasa.setCawangan(cawangan);
            wakilKuasa.setPermohonan(permohonan);
            //TODO: Re-set idWakil
            wakilKuasa.setIdWakil(permohonan.getIdPermohonan());
            wakilKuasa.setAktif('T');
            wakilKuasa.setKuasaAm('T');
            wakilKuasa.setKuasaGadai('T');
            wakilKuasa.setKuasaKaveat('T');
            wakilKuasa.setKuasaLepasGadai('T');
            wakilKuasa.setKuasaLepasKaveat('T');
            wakilKuasa.setKuasaPajak('T');
            wakilKuasa.setKuasaPajakKecil('T');
            wakilKuasa.setKuasaPindahMilik('T');
            wakilKuasa.setKuasaSewa('T');
            wakilKuasa.setKuasaTarikKaveat('T');
            wakilKuasa.setUntukSemuaHakmilik('T');
            suratkuasaService.saveWakilKuasa(wakilKuasa);
            LOG.info("=========Jenis Pihak Popup==========" + jPihak);
            if (jPihak.equalsIgnoreCase("pemberi")) {
//           alert("Enter Pemberi", jPihak);
                simpanPemberi(info, pihak, wakilKuasa, peng);
            } else if (jPihak.equalsIgnoreCase("penerima")) {
//                alert("Enter Penerima", jPihak);
                simpanPenerima(info, wakilKuasa, peng, namaPenerima, noPengenalanPenerima, kodjenisPengenalanPenerima);
            }
            for (HakmilikPermohonan hPermohonan : hakmilikPermohonanList) {
                hakmilik = hakmilikDAO.findById(hPermohonan.getHakmilik().getIdHakmilik());
                simpanWakilHakmilik(peng, wakilKuasa, hakmilik);
            }

            addSimpleMessage("Maklumat berjaya disimpan.");
        } else {
            LOG.info("=========Jenis Pihak Popup 2==========" + jPihak);
            if (jPihak.equalsIgnoreCase("pemberi")) {
                simpanPemberi(info, pihak, wakilKuasa, peng);
            } else if (jPihak.equalsIgnoreCase("penerima")) {
                simpanPenerima(info, wakilKuasa, peng, namaPenerima, noPengenalanPenerima, kodjenisPengenalanPenerima);
            }
            for (HakmilikPermohonan hPermohonan : hakmilikPermohonanList) {
                hakmilik = hakmilikDAO.findById(hPermohonan.getHakmilik().getIdHakmilik());
                simpanWakilHakmilik(peng, wakilKuasa, hakmilik);
            }
            LOG.info("SIMPAN SUCCESS");
            addSimpleMessage("Maklumat berjaya disimpan.");
        }

//           return new RedirectResolution(SuratKuasaWakilActionBean.class);
        return new JSP("daftar/maklumat_pemberi_penerima_suratkuasa_wakil.jsp").addParameter("tab", "true");
//        return new RedirectResolution(SuratKuasaWakilActionBean.class, "maklumatPemPen").addParameter("tab", "true");
    }

    public String simpanPemberi(InfoAudit info, Pihak pihak, WakilKuasa wakilKuasa, Pengguna peng) {
        String msg = "";
        wakilKuasaPemberi = suratkuasaService.findWakilPemberi(wakilKuasa.getIdWakil(), String.valueOf(pihak.getIdPihak()));
        if (wakilKuasaPemberi != null) {
            addSimpleError("Data nama / no kad pengenalan telah direkodkan");
        } else {
            //start save pemberi :D wakilKuasaPemberi
            wakilKuasaPemberi = new WakilKuasaPemberi();
            wakilKuasaPemberi.setInfoAudit(info);
            wakilKuasaPemberi.setPihak(pihak);
            wakilKuasaPemberi.setNama(pihak.getNama());
            wakilKuasaPemberi.setNoPengenalan(pihak.getNoPengenalan());
            
            if(pihak.getJenisPengenalan().getKod() == null)
            {
                        
            wakilKuasaPemberi.setKodPengenalan(null);
            }
            
            else
            {
                KodJenisPengenalan kp = pihak.getJenisPengenalan();
                wakilKuasaPemberi.setKodPengenalan(kp.getKod());
            }
            
          
            wakilKuasaPemberi.setCawangan(peng.getKodCawangan());
            wakilKuasaPemberi.setWakilKuasa(wakilKuasa);
            suratkuasaService.saveWakilKuasaPemberi(wakilKuasaPemberi);
            LOG.info("SIMPAN PEMBERI SUCCESS");
            addSimpleMessage("Maklumat berjaya disimpan.");
        }
        return msg;
    }

    public String simpanPenerima(InfoAudit info, WakilKuasa wakilKuasa, Pengguna peng, String namaPenerima, String noPengenalanPenerima, KodJenisPengenalan kodjenisPengenalanPenerima) {
        String msg = "";
        LOG.info("id pihak~~~" + pihak.getIdPihak());
        wakilKuasaPenerima = suratkuasaService.findWakilPenerima(wakilKuasa.getIdWakil(),noPengenalanPenerima);
        //start save penerima :D wakilKuasaPenerima
        //suratkuasaService.findWakilPenerimabyIdPermohonan(idPermohonan);
        if(wakilKuasaPenerima != null){
            addSimpleError("Data nama / no kad pengenalan telah direkodkan");
        }else{
        wakilKuasaPenerima = new WakilKuasaPenerima();
        wakilKuasaPenerima.setInfoAudit(info);
        wakilKuasaPenerima.setCawangan(peng.getKodCawangan());
        wakilKuasaPenerima.setWakilKuasa(wakilKuasa);
        LOG.info("========namaPenerima=========" + namaPenerima);
        wakilKuasaPenerima.setNama(namaPenerima);
        LOG.info("========kodjenisPengenalanPenerima=========" + kodjenisPengenalanPenerima);
        wakilKuasaPenerima.setJenisPengenalan(kodjenisPengenalanPenerima);
        LOG.info("========noPengenalanPenerima=========" + noPengenalanPenerima);
        wakilKuasaPenerima.setNoPengenalan(noPengenalanPenerima);
        wakilKuasaPenerima.setStatus("A");
        //Changes by Aizuddin to filter field that isnt compulsory
        if (StringUtils.isNotBlank(amaunMaksima)) {
            LOG.info("========Amaun Maksima=========" + amaunMaksima);
            BigDecimal bd = new BigDecimal(amaunMaksima);
            wakilKuasaPenerima.setAmaunMaksima(bd);
        }
        if (StringUtils.isNotBlank(syaratTambahan)) {
            LOG.info("========Syarat Tambahan=========" + syaratTambahan);
            wakilKuasa.setSyaratTambahan(syaratTambahan);
//            suratkuasaService.saveWakilKuasa(wakilKuasa);
            suratkuasaService.saveOrUpdateWakilKuasa(wakilKuasa);
        }
        //Changes by Aizuddin to save
//        suratkuasaService.saveWakilKuasaPenerima(wakilKuasaPenerima);
        suratkuasaService.saveOrUpdateWakilKuasaPenerima(wakilKuasaPenerima);
        LOG.info("SIMPAN PENERIMA SUCCESS");
        addSimpleMessage("Maklumat berjaya disimpan.");
        }
        return msg;
    }

    public String simpanWakilHakmilik(Pengguna peng, WakilKuasa wakilKuasa, Hakmilik hakmilik) {
        String msg = "";
        wakilKuasaHakmilik = suratkuasaService.findWakilKuasaHakmilikByIdWakilHakmilik(wakilKuasa.getIdWakil(), hakmilik.getIdHakmilik());

        if (wakilKuasaHakmilik == null) {
            wakilKuasaHakmilik = new WakilKuasaHakmilik();
            InfoAudit iA = new InfoAudit();
            iA.setDimasukOleh(peng);
            iA.setTarikhMasuk(new java.util.Date());

            wakilKuasaHakmilik.setInfoAudit(iA);
            wakilKuasaHakmilik.setAktif('T');
            wakilKuasaHakmilik.setHakmilik(hakmilik);
            wakilKuasaHakmilik.setWakilKuasa(wakilKuasa);
//            suratkuasaService.saveWakilKuasaHakmilik(wakilKuasaHakmilik);
            wakilKuasaHakmilikDAO.saveOrUpdate(wakilKuasaHakmilik);
        } else {
            //start save hakmilik_wakil
            InfoAudit iA = new InfoAudit();
            iA.setDimasukOleh(wakilKuasaHakmilik.getInfoAudit().getDimasukOleh());
            iA.setTarikhMasuk(wakilKuasaHakmilik.getInfoAudit().getTarikhMasuk());
            iA.setDiKemaskiniOleh(peng);
            iA.setTarikhKemaskini(new java.util.Date());
            wakilKuasaHakmilik.setInfoAudit(info);
            suratkuasaService.saveWakilKuasaHakmilik(wakilKuasaHakmilik);
        }
        //
        return msg;
    }

    /**
     * public Resolution simpanTempoh() throws ParseException { if (idPermohonan
     * != null) { wakilPihak = suratkuasaService.findWakil(idPermohonan);
     *
     * {
     * if (wakilPihak != null) { info = peng.getInfoAudit();
     * info.setDiKemaskiniOleh(peng); info.setTarikhKemaskini(new
     * java.util.Date()); tarikhDaftar = dateFormat.parse(tkhDaftar);
     * wakilPihak.setInfoAudit(info); wakilPihak.setTarikhDaftar(tarikhDaftar);
     * suratkuasaService.save(wakilPihak);
     *
     * kiraTempoh(); } } } return new
     * JSP("daftar/tempohSuratKuasa.jsp").addParameter("tab", "true"); } *
     */
    public Resolution simpanWakilhakmilik() {

        return new JSP("daftar/jenis_suratkuasa.jsp").addParameter("tab", "true");
    }

    public Resolution semuaHakmilik() {
//        peng = (Pengguna) context.getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
//        if (wakilKuasa != null) {
//            for (HakmilikPihakBerkepentingan hPenting : hpB) {
//                wakilHakmilik = suratkuasaService.findWakilHakmilikByIdWakilHakmilik(wakilKuasa.getIdWakil(), hPenting.getHakmilik().getIdHakmilik());
//                if (wakilHakmilik != null) {
//                } else {
//                    wakilHakmilik = new WakilHakmilik();
//                    info = peng.getInfoAudit();
//                    info.setDimasukOleh(peng);
//                    info.setTarikhMasuk(new java.util.Date());
//                    wakilHakmilik.setInfoAudit(info);
//                    wakilPihak.setUntukSemuaHakmilik('Y');
////                    wakilHakmilik = new WakilHakmilik();
//                    wakilHakmilik.setInfoAudit(info);
////                    wakilHakmilik.setWakilPihak(wakilHakmilik);
//                    wakilHakmilik.setHakmilik(hPenting.getHakmilik());
//                    wakilHakmilik.setAktif('T');
//                    suratkuasaService.saveWakilHakmilik(wakilHakmilik);
//                }
//            }
//
//        }

        return new JSP("daftar/jenis_suratkuasa.jsp").addParameter("tab", "true");
    }

    public Resolution untickSemuaHakmilik() {
//        if (wakilPihak != null) {
//            info = peng.getInfoAudit();
//            info.setDiKemaskiniOleh(peng);
//            info.setTarikhKemaskini(new java.util.Date());
//            wakilPihak.setInfoAudit(info);
//            wakilPihak.setUntukSemuaHakmilik('T');
//            suratkuasaService.saveWakilKuasa(wakilKuasa);
//            for (HakmilikPihakBerkepentingan hPenting : hpB) {
//                wakilHakmilik = suratkuasaService.findWakilHakmilikByIdWakilHakmilik(wakilKuasa.getIdWakil(), hPenting.getHakmilik().getIdHakmilik());
//                if (wakilHakmilik != null) {
////                    alert("Id Hakmilik", hPenting.getHakmilik().getIdHakmilik());
//                    suratkuasaService.deleteAll(wakilHakmilik);
//
//                } else {
//                }
//            }
//        }

        return new JSP("daftar/jenis_suratkuasa.jsp").addParameter("tab", "true");
    }

    public Resolution deleteSingle() {
//        idH = getContext().getRequest().getParameter("idH");
//        wakilHakmilik = suratkuasaService.findWakilHakmilikByIdWakilHakmilik(wakilKuasa.getIdWakil(), idH);
//
//        if (wakilKuasa != null) {
//
//            info = peng.getInfoAudit();
//            info.setDiKemaskiniOleh(peng);
//            info.setTarikhKemaskini(new java.util.Date());
//            wakilKuasa.setInfoAudit(info);
//            wakilKuasa.setUntukSemuaHakmilik('T');
//            suratkuasaService.deleteAll(wakilHakmilik);
//        }
        return new JSP("daftar/jenis_suratkuasa.jsp").addParameter("tab", "true");
    }

    public Resolution cariPemberi() {
        if (jPihak.equalsIgnoreCase("pemberi")) {
            LOG.info("No Kad Pengenalan:: " + noKP);
            LOG.info("Kod Kad Pengenalan:: " + kodKP);
            LOG.info("Nama Pemberi:: " + namaPemberi);
        } else if (jPihak.equalsIgnoreCase("penerima")) {
            LOG.info("No Kad Pengenalan:: " + noKP2);
            LOG.info("Kod Kad Pengenalan:: " + kodKP2);
            LOG.info("Nama Penerima:: " + namaPenerima2);
        }


        if ((noKP != null) && (kodKP != null) && (namaPemberi == null)) {
            LOG.info("Stage 1");
            pihakList = pihakService.findPihakBynoKPkodKP(kodKP, noKP);
        } else if ((noKP == null) && (kodKP != null) && (namaPemberi != null)) {
            pihakList = pihakService.findPihakByName(namaPemberi, null);
        }

        return new JSP("daftar/popUpCarianPihak.jsp").addParameter("popup", "true");

    }

    public Resolution cariPenerima() {
        if (jPihak.equalsIgnoreCase("pemberi")) {
            LOG.info("No Kad Pengenalan:: " + noKP);
            LOG.info("Kod Kad Pengenalan:: " + kodKP);
            LOG.info("Nama Pemberi:: " + namaPemberi);
        } else if (jPihak.equalsIgnoreCase("penerima")) {
            LOG.info("No Kad Pengenalan:: " + noKP2);
            LOG.info("Kod Kad Pengenalan:: " + kodKP2);
            LOG.info("Nama Penerima:: " + namaPenerima2);
        }


        if ((noKP2 != null) && (kodKP2 != null) && (namaPenerima2 == null)) {
            LOG.info("Stage 1");
            pihakList = pihakService.findPihakBynoKPkodKP(kodKP2, noKP2);
        } else if ((noKP2 == null) && (kodKP2 != null) && (namaPenerima2 != null)) {
            pihakList = pihakService.findPihakByName(namaPenerima2, null);
        }

        return new JSP("daftar/popUpCarianPihak2.jsp").addParameter("popup", "true");

    }

    public String generateIdWakil() {
        etanahActionBeanContext ctx = (etanahActionBeanContext) getContext();
        Pengguna pengguna = ctx.getUser();
        KodCawangan caw = pengguna.getKodCawangan();
        String idWakil = null;
        idWakil = idWakilGenerator.generate(ctx.getKodNegeri(), caw, null);
//        alert("IDWAKIL:: ", idWakil);
        return idWakil;
    }

    public Resolution deletePenerima() {

        WakilKuasaPenerima wp = new WakilKuasaPenerima();
        wp = wakilKuasaPenerimaDAO.findById(Long.parseLong(idPenerima));
        if (wp != null) {
            suratkuasaService.deletePenerima(wp);
            addSimpleMessage("Maklumat penerima dihapus.");
        }
//        return new RedirectResolution(SuratKuasaWakilActionBean.class);
      //  return new JSP("daftar/maklumat_pemberi_penerima_suratkuasa_wakil.jsp").addParameter("tab", "true");
         return new RedirectResolution(SuratKuasaWakilActionBean.class, "maklumatPemPen").addParameter("tab", "true");
    }

    public Resolution deletePemberi() {

        WakilKuasaPemberi wPemberi = new WakilKuasaPemberi();
        wPemberi = wakilKuasaPemberiDAO.findById(Long.parseLong(idPemberi));
        if (wPemberi != null) {
            suratkuasaService.deletePemberi(wPemberi);
            addSimpleMessage("Maklumat pemberi dihapus.");
        }
//        return new RedirectResolution(SuratKuasaWakilActionBean.class);
        //return new JSP("daftar/maklumat_pemberi_penerima_suratkuasa_wakil.jsp").addParameter("tab", "true");
        return new RedirectResolution(SuratKuasaWakilActionBean.class, "maklumatPemPen").addParameter("tab", "true");
    }

    public void alert(String text, Object msg) {
        JOptionPane.showMessageDialog(null, text + " " + msg);
    }

    public List<HakmilikPihakBerkepentingan> getHpB() {
        return hpB;
    }

    public void setHpB(List<HakmilikPihakBerkepentingan> hpB) {
        this.hpB = hpB;
    }

    public String getTkhDaftar() {
        return tkhDaftar;
    }

    public void setTkhDaftar(String tkhDaftar) {
        this.tkhDaftar = tkhDaftar;
    }

    public String getJenisPengenalanPenerima() {
        return jenisPengenalanPenerima;
    }

    public void setJenisPengenalanPenerima(String jenisPengenalanPenerima) {
        this.jenisPengenalanPenerima = jenisPengenalanPenerima;
    }

    public String getNoPengenalanPenerima() {
        return noPengenalanPenerima;
    }

    public void setNoPengenalanPenerima(String noPengenalanPenerima) {
        this.noPengenalanPenerima = noPengenalanPenerima;
    }

    public String getNamaPenerima() {
        return namaPenerima;
    }

    public void setNamaPenerima(String namaPenerima) {
        this.namaPenerima = namaPenerima;
    }

    public String getNoKadPengenalan() {
        return noKadPengenalan;
    }

    public void setNoKadPengenalan(String noKadPengenalan) {
        this.noKadPengenalan = noKadPengenalan;
    }

    public boolean isFlagCarian() {
        return flagCarian;
    }

    public void setFlagCarian(boolean flagCarian) {
        this.flagCarian = flagCarian;
    }

    public String getIdPermohonan() {
        return idPermohonan;
    }

    public void setIdPermohonan(String idPermohonan) {
        this.idPermohonan = idPermohonan;
    }

    public WakilKuasaHakmilik getWakilKuasaHakmilik() {
        return wakilKuasaHakmilik;
    }

    public void setWakilHakmilik(WakilKuasaHakmilik wakilKuasaHakmilik) {
        this.wakilKuasaHakmilik = wakilKuasaHakmilik;
    }

//    public WakilPihak getWakilPihak() {
//        return wakilPihak;
//    }
//
//    public void setWakilPihak(WakilPihak wakilPihak) {
//        this.wakilPihak = wakilPihak;
//    }
    public String getKodKP() {
        return kodKP;
    }

    public void setKodKP(String kodKP) {
        this.kodKP = kodKP;
    }

    public String getNoKP() {
        return noKP;
    }

    public void setNoKP(String noKP) {
        this.noKP = noKP;
    }

    public Permohonan getPermohonan() {
        return permohonan;
    }

    public void setPermohonan(Permohonan permohonan) {
        this.permohonan = permohonan;
    }

    public Pihak getPihak() {
        return pihak;
    }

    public void setPihak(Pihak pihak) {
        this.pihak = pihak;
    }

    public KodJenisPengenalan getKodjenisPengenalan() {
        return kodjenisPengenalan;
    }

    public void setKodjenisPengenalan(KodJenisPengenalan kodjenisPengenalan) {
        this.kodjenisPengenalan = kodjenisPengenalan;
    }

    public String getJenisPengenalanPemberi() {
        return jenisPengenalanPemberi;
    }

    public void setJenisPengenalanPemberi(String jenisPengenalanPemberi) {
        this.jenisPengenalanPemberi = jenisPengenalanPemberi;
    }

    public String getNamaPemberi() {
        return namaPemberi;
    }

    public void setNamaPemberi(String namaPemberi) {
        this.namaPemberi = namaPemberi;
    }

    public String getNoPengenalanPemberi() {
        return noPengenalanPemberi;
    }

    public void setNoPengenalanPemberi(String noPengenalanPemberi) {
        this.noPengenalanPemberi = noPengenalanPemberi;
    }

    public String getTmpIDpihak() {
        return tmpIDpihak;
    }

    public void setTmpIDpihak(String tmpIDpihak) {
        this.tmpIDpihak = tmpIDpihak;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public String getKuasaL1() {
        return kuasaL1;
    }

    public void setKuasaL1(String kuasaL1) {
        this.kuasaL1 = kuasaL1;
    }

    public String getKuasaL2() {
        return kuasaL2;
    }

    public void setKuasaL2(String kuasaL2) {
        this.kuasaL2 = kuasaL2;
    }

    public Hakmilik getHakmilik() {
        return hakmilik;
    }

    public void setHakmilik(Hakmilik hakmilik) {
        this.hakmilik = hakmilik;
    }

    public String getIdH() {
        return idH;
    }

    public void setIdHakmilik(String idH) {
        this.idH = idH;
    }

    public List<WakilKuasaHakmilik> getwKuasaHakmilik() {
        return wKuasaHakmilik;
    }

    public void setwKuasaHakmilik(List<WakilKuasaHakmilik> wKuasaHakmilik) {
        this.wKuasaHakmilik = wKuasaHakmilik;
    }

    public String getAlamat1() {
        return alamat1;
    }

    public void setAlamat1(String alamat1) {
        this.alamat1 = alamat1;
    }

    public String getAlamat2() {
        return alamat2;
    }

    public void setAlamat2(String alamat2) {
        this.alamat2 = alamat2;
    }

    public String getAlamat3() {
        return alamat3;
    }

    public void setAlamat3(String alamat3) {
        this.alamat3 = alamat3;
    }

    public String getAlamat4() {
        return alamat4;
    }

    public void setAlamat4(String alamat4) {
        this.alamat4 = alamat4;
    }

    public KodNegeri getNegeri() {
        return negeri;
    }

    public void setNegeri(KodNegeri negeri) {
        this.negeri = negeri;
    }

    public String getNegeriPenerima() {
        return negeriPenerima;
    }

    public void setNegeriPenerima(String negeriPenerima) {
        this.negeriPenerima = negeriPenerima;
    }

    public String getPoskod() {
        return poskod;
    }

    public void setPoskod(String poskod) {
        this.poskod = poskod;
    }

    public List<HakmilikPihakBerkepentingan> getHakmilikPihakBerkepentinganList() {
        return hakmilikPihakBerkepentinganList;
    }

    public void setHakmilikPihakBerkepentinganList(List<HakmilikPihakBerkepentingan> hakmilikPihakBerkepentinganList) {
        this.hakmilikPihakBerkepentinganList = hakmilikPihakBerkepentinganList;
    }

    public List<HakmilikPihakBerkepentingan> getHakmilikPihakBerkepentinganList2() {
        return hakmilikPihakBerkepentinganList2;
    }

    public void setHakmilikPihakBerkepentinganList2(List<HakmilikPihakBerkepentingan> hakmilikPihakBerkepentinganList2) {
        this.hakmilikPihakBerkepentinganList2 = hakmilikPihakBerkepentinganList2;
    }

    public String getIdPihakPemberi() {
        return idPihakPemberi;
    }

    public void setIdPihakPemberi(String idPihakPemberi) {
        this.idPihakPemberi = idPihakPemberi;
    }

    public HakmilikPihakBerkepentingan getHakmilikPihakBerkepentingan() {
        return hakmilikPihakBerkepentingan;
    }

    public void setHakmilikPihakBerkepentingan(HakmilikPihakBerkepentingan hakmilikPihakBerkepentingan) {
        this.hakmilikPihakBerkepentingan = hakmilikPihakBerkepentingan;
    }

    public WakilKuasa getWakilKuasa() {
        return wakilKuasa;
    }

    public void setWakilKuasa(WakilKuasa wakilKuasa) {
        this.wakilKuasa = wakilKuasa;
    }

    public WakilKuasaPemberi getWakilKuasaPemberi() {
        return wakilKuasaPemberi;
    }

    public void setWakilKuasaPemberi(WakilKuasaPemberi wakilKuasaPemberi) {
        this.wakilKuasaPemberi = wakilKuasaPemberi;
    }

    public WakilKuasaPenerima getWakilKuasaPenerima() {
        return wakilKuasaPenerima;
    }

    public void setWakilKuasaPenerima(WakilKuasaPenerima wakilKuasaPenerima) {
        this.wakilKuasaPenerima = wakilKuasaPenerima;
    }

    public String getHakmilikWakil() {
        return hakmilikWakil;
    }

    public void setHakmilikWakil(String hakmilikWakil) {
        this.hakmilikWakil = hakmilikWakil;
    }

    public List<WakilKuasa> getwKuasa() {
        return wKuasa;
    }

    public void setwKuasa(List<WakilKuasa> wKuasa) {
        this.wKuasa = wKuasa;
    }

    public List<Pihak> getPihakList() {
        return pihakList;
    }

    public void setPihakList(List<Pihak> pihakList) {
        this.pihakList = pihakList;
    }

    public String getIdPenerima() {
        return idPenerima;
    }

    public void setIdPenerima(String idPenerima) {
        this.idPenerima = idPenerima;
    }

    public String getIdPemberi() {
        return idPemberi;
    }

    public void setIdPemberi(String idPemberi) {
        this.idPemberi = idPemberi;
    }

    public String getAmaunMaksima() {
        return amaunMaksima;
    }

    public void setAmaunMaksima(String amaunMaksima) {
        this.amaunMaksima = amaunMaksima;
    }

    public String getSyaratTambahan() {
        return syaratTambahan;
    }

    public void setSyaratTambahan(String syaratTambahan) {
        this.syaratTambahan = syaratTambahan;
    }

    public String getjPihak() {
        return jPihak;
    }

    public void setjPihak(String jPihak) {
        this.jPihak = jPihak;
    }

    public List<KandunganFolder> getSenaraiKF() {
        return senaraiKF;
    }

    public void setSenaraiKF(List<KandunganFolder> senaraiKF) {
        this.senaraiKF = senaraiKF;
    }

    public List<String> getSenaraiSW() {
        return senaraiSW;
    }

    public void setSenaraiSW(List<String> senaraiSW) {
        this.senaraiSW = senaraiSW;
    }

    public List<Dokumen> getDokumen() {
        return dokumen;
    }

    public void setDokumen(List<Dokumen> dokumen) {
        this.dokumen = dokumen;
    }

    public String[] getIdHakmilikSW() {
        return idHakmilikSW;
    }

    public void setIdHakmilikSW(String[] idHakmilikSW) {
        this.idHakmilikSW = idHakmilikSW;
    }

    public String[] getNoRujukan() {
        return noRujukan;
    }

    public void setNoRujukan(String[] noRujukan) {
        this.noRujukan = noRujukan;
    }

    public String getNamaPenerima2() {
        return namaPenerima2;
    }

    public void setNamaPenerima2(String namaPenerima2) {
        this.namaPenerima2 = namaPenerima2;
    }

    public String getNoKP2() {
        return noKP2;
    }

    public void setNoKP2(String noKP2) {
        this.noKP2 = noKP2;
    }

    public String getKodKP2() {
        return kodKP2;
    }

    public void setKodKP2(String kodKP2) {
        this.kodKP2 = kodKP2;
    }

    public String getJenisSurat() {
        return jenisSurat;
    }

    public void setJenisSurat(String jenisSurat) {
        this.jenisSurat = jenisSurat;
    }

    public String getJenisSuratLain2() {
        return jenisSuratLain2;
    }

    public void setJenisSuratLain2(String jenisSuratLain2) {
        this.jenisSuratLain2 = jenisSuratLain2;
    }

    public WakilKuasa getWakilKuasaB() {
        return wakilKuasaB;
    }

    public void setWakilKuasaB(WakilKuasa wakilKuasaB) {
        this.wakilKuasaB = wakilKuasaB;
    }
    
}