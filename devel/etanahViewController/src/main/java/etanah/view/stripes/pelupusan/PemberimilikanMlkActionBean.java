
/**
 *
 * @author nurul.izza
 */

package etanah.view.stripes.pelupusan;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.dao.HakmilikDAO;
import etanah.dao.HakmilikPihakBerkepentinganDAO;
import etanah.dao.KodNegeriDAO;
import etanah.dao.PihakCawanganDAO;
import etanah.dao.PihakDAO;
import etanah.model.Hakmilik;
import etanah.model.HakmilikPihakBerkepentingan;
import etanah.model.InfoAudit;
import etanah.model.KodBangsa;
import etanah.model.Pemohon;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.model.PermohonanPihak;
import etanah.model.Pihak;
import etanah.model.PihakCawangan;
import etanah.service.common.PemohonService;
import etanah.service.common.PermohonanService;
import etanah.service.common.PihakService;
import etanah.view.etanahActionBeanContext;
import java.util.List;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.RedirectResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.StreamingResolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;
import org.apache.log4j.Logger;
import etanah.model.KodNegeri;
import etanah.service.common.CawanganService;
import etanah.view.ListUtil;
import java.util.ArrayList;
import org.hibernate.Session;
import org.hibernate.Transaction;
import java.text.SimpleDateFormat;
import java.text.ParseException;
import etanah.dao.PermohonanDAO;
import etanah.model.Permohonan;
/**
 *
 * @author nurul.izza
 */

@UrlBinding("/pelupusan/pemberimilikan_tanah")

public class PemberimilikanMlkActionBean extends AbleActionBean {
    private static Logger logger = Logger.getLogger(MaklumatPemohonActionBean.class);
    @Inject
    PihakService pihakService;
    @Inject
    PermohonanService permohonanService;
    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    PemohonService pemohonService;
    @Inject
    CawanganService cawanganService;
    @Inject
    PihakDAO pihakDAO;
    @Inject
    KodNegeriDAO kodNegeriDAO;
    @Inject
    PihakCawanganDAO pihakCawanganDAO;
    @Inject
    HakmilikDAO hakmilikDAO;
    @Inject
    HakmilikPihakBerkepentinganDAO hakmilikPihakBerkepentinganDAO;
    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;
    @Inject
    ListUtil listUtil;
    private Pihak pihak;
    private PihakCawangan pihakCawangan;
    private PermohonanPihak permohonanPihak;
    private List<Pemohon> pemohonList;
    private List<PihakCawangan> cawanganList;
    private List<KodBangsa> senaraiKodBangsa;
    private static final Logger LOG = Logger.getLogger(MaklumatPemohonActionBean.class);
    private static final boolean IS_LOG_DEBUG = LOG.isDebugEnabled();
    private ArrayList hakmilikList = new ArrayList();
    private String idPermohonan;
    private Permohonan p;
    private Pengguna pguna;
    private Pemohon pemohon;
    String tarikhLahir;
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    boolean cariFlag;
    boolean tiadaDataFlag = false;
    boolean tambahCawFlag;
    private String idCawangan;

@DefaultHandler
    public Resolution showForm() {
        return new JSP("pelupusan/pemberimilikan_tanah.jsp").addParameter("tab", "true");
    }

//    public Resolution showTambahPemohon(){
//        return new JSP("pelupusan/tambah_pemohon_LPS.jsp").addParameter("popup", "true");
//    }

//     public Resolution showTambahPemohon2(){
//        return new JSP("pelupusan/latarbelakang.jsp").addParameter("tab", "true");
//    }

    public Resolution showTambahPemohon3() {
        return new JSP("pelupusan/penyediaan_borang4A.jsp").addParameter("popup", "true");
    }

    public Resolution showTambahPemohon4() {
        return new JSP("pelupusan/penempatan_peserta.jsp").addParameter("popup", "true");
    }

    public Resolution showTambahPemohon5() {
        return new JSP("pelupusan/penyediaan_borang4B.jsp").addParameter("popup", "true");
    }

    public Resolution showTambahPemohon6() {
        return new JSP("pelupusan/pengesahan_borang4B.jsp").addParameter("popup", "true");
    }

    public Resolution showTambahPemohon7() {
        return new JSP("pelupusan/surat_keputusan.jsp").addParameter("tab", "true");
    }

    public Resolution showForm1() {
        return new JSP("pelupusan/ulasan_YBADUN.jsp").addParameter("tab", "true");
    }

    public Resolution showForm2() {
        return new JSP("pelupusan/buktipenyampaian.jsp").addParameter("tab", "true");
    }

   

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {

        pguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        p = permohonanService.findById(idPermohonan);

        if (p != null) {
            idPermohonan = p.getIdPermohonan();
            pemohonList = pemohonService.findPemohonByIdPermohonan(idPermohonan);

            if (!pemohonList.isEmpty()) {
                hakmilikList.clear();
                for (int i = 0; i < pemohonList.size(); i++) {

                    Pihak phk = new Pihak();
                    phk = pemohonList.get(i).getPihak();

                    String[] tname = {"pihak"};
                    Object[] model = {phk};
                    List<HakmilikPihakBerkepentingan> pbList;
                    pbList = hakmilikPihakBerkepentinganDAO.findByEqualCriterias(tname, model, null);

                    if (!pbList.isEmpty()) {
                        for (HakmilikPihakBerkepentingan hpb : pbList) {
                            Hakmilik hak = new Hakmilik();
                            hak = hakmilikDAO.findById(hpb.getHakmilik().getIdHakmilik());
                            hakmilikList.add(hak);
                        }
                    }
                }
            }
        }
    }

    public Resolution cariPihakPemohon() {

        if (pihak != null) {
            if (pihak.getJenisPengenalan() != null && pihak.getNoPengenalan() != null) {

                Pihak pihakSearch = new Pihak();
                pihakSearch = pihakService.findPihak(pihak.getJenisPengenalan().getKod(), pihak.getNoPengenalan());

                boolean duplicateFlag = false;

                if (pihakSearch != null) {

                    idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
                    p = permohonanService.findById(idPermohonan);

                    if (!(p.getSenaraiPemohon().isEmpty())) {
                        for (Pemohon pem : p.getSenaraiPemohon()) {
                            if (pem.getPihak().getIdPihak() == pihakSearch.getIdPihak()) {
                                duplicateFlag = true;
                                break;
                            }
                        }
                    }
                }

                if (duplicateFlag == false) {
                    if (pihakSearch != null) {
                        pihak = pihakSearch;
                        cawanganList = pihak.getSenaraiCawangan();
                        cariFlag = true;
                        tiadaDataFlag = false;
                    } else {
                        addSimpleMessage("Tiada Data. Sila Masukkan Maklumat Baru.");
                        cariFlag = true;
                        tiadaDataFlag = true;
                    }
                    senaraiKodBangsa = listUtil.getSenaraiKodBangsaByJenisPengenalan(pihak.getJenisPengenalan().getKod());
                } else {
                    pihak = new Pihak();
                    addSimpleError("Maklumat Ini Telah Dipilih.");
                }
            } else {
                if (pihak.getJenisPengenalan() == null) {
                    addSimpleError("Sila Masukkan Jenis Pengenalan.");
                } else if (pihak.getNoPengenalan() == null) {
                    addSimpleError("Sila Masukkan  Nombor Pengenalan.");
                }
            }

        } else {

            addSimpleError("Sila Masukkan Jenis Pengenalan Dan Nombor Pengenalan.");
        }

        return new JSP("pelupusan/tambah_pemohon.jsp").addParameter("popup", "true");
    }

    public Resolution simpanPemohonPopup() throws ParseException {

        Permohonan permohonan = null;

        if (idPermohonan != null) {
            permohonan = permohonanService.findById(idPermohonan);
        }

        InfoAudit info = new InfoAudit();
        info.setDimasukOleh(pguna);
        info.setTarikhMasuk(new java.util.Date());
        Pihak pihakTemp = pihakDAO.findById(pihak.getIdPihak());

        if (pihak.getBangsa() != null) {
            if (pihak.getBangsa().getKod().toString().length() > 4) {
                pihak.setBangsa(null);
            }
        }

        if (pihakTemp == null) {
            pihakTemp = new Pihak();
            pihakTemp.setNama(pihak.getNama());
            pihakTemp.setJenisPengenalan(pihak.getJenisPengenalan());
            pihakTemp.setNoPengenalan(pihak.getNoPengenalan());
            pihakTemp.setKodJantina(pihak.getKodJantina());
            pihakTemp.setBangsa(pihak.getBangsa());
            pihakTemp.setSuku(pihak.getSuku());
            pihakTemp.setNoTelefon1(pihak.getNoTelefon1());
            pihakTemp.setNoTelefon2(pihak.getNoTelefon2());
            pihakTemp.setNoTelefonBimbit(pihak.getNoTelefonBimbit());
            pihakTemp.setWargaNegara(pihak.getWargaNegara());
            pihakTemp.setAlamat1(pihak.getAlamat1());
            pihakTemp.setAlamat2(pihak.getAlamat2());
            pihakTemp.setAlamat3(pihak.getAlamat3());
            pihakTemp.setAlamat4(pihak.getAlamat4());
            pihakTemp.setPoskod(pihak.getPoskod());
            pihakTemp.setNegeri(pihak.getNegeri());
            pihakTemp.setSuratAlamat1(pihak.getSuratAlamat1());
            pihakTemp.setSuratAlamat2(pihak.getSuratAlamat2());
            pihakTemp.setSuratAlamat3(pihak.getSuratAlamat3());
            pihakTemp.setSuratAlamat4(pihak.getSuratAlamat4());
            pihakTemp.setSuratPoskod(pihak.getSuratPoskod());
            pihakTemp.setSuratNegeri(pihak.getSuratNegeri());
            pihakTemp.setTempatLahir(pihak.getTempatLahir());

            if (tarikhLahir != null) {
                try {
                    pihakTemp.setTarikhLahir(sdf.parse(tarikhLahir));
                } catch (ParseException ex) {
                    logger.error("exception: " + ex.getMessage());
                    throw ex;
                }
            }
        }

        pihakTemp.setInfoAudit(info);
        Pemohon pmohon = new Pemohon();
        pmohon.setPermohonan(permohonan);
        pmohon.setPihak(pihakTemp);
        pmohon.setInfoAudit(info);
        pmohon.setCawangan(permohonan.getCawangan());

        if (idCawangan != null) {
            pihakCawangan = new PihakCawangan();
            pihakCawangan = pihakCawanganDAO.findById(Long.valueOf(idCawangan));
            pmohon.setPihakCawangan(pihakCawangan);
            //pemohon.setPihakCawangan(pihakCawangan);
        }

        if (pmohon != null) {

            pihakTemp.setSuratAlamat1(pihak.getSuratAlamat1());
            pihakTemp.setSuratAlamat2(pihak.getSuratAlamat2());
            pihakTemp.setSuratAlamat3(pihak.getSuratAlamat3());
            pihakTemp.setSuratAlamat4(pihak.getSuratAlamat4());
            pihakTemp.setSuratPoskod(pihak.getSuratPoskod());
            pihakTemp.setSuratNegeri(pihak.getSuratNegeri());
            pihakTemp.setTempatLahir(pihak.getTempatLahir());

            if (tarikhLahir != null) {
                try {
                    pihakTemp.setTarikhLahir(sdf.parse(tarikhLahir));
                } catch (ParseException ex) {
                    logger.error("exception: " + ex.getMessage());
                    throw ex;
                }
            }
            pemohonService.simpanPihakPemohon(pihakTemp, pmohon);
            return new RedirectResolution("/pelupusan/maklumat_pemohon?showForm").addParameter("tab", "true");
        }

        return new StreamingResolution("text/plain", "1");
    }

    public Resolution simpanEditPemohon() {
        Transaction tx = sessionProvider.get().beginTransaction();
        tx.begin();
        try {
            Pengguna pg = (Pengguna) context.getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
            InfoAudit infoAudit = new InfoAudit();
            infoAudit.setDiKemaskiniOleh(pg);
            infoAudit.setTarikhKemaskini(new java.util.Date());
            Pihak pihakTemp = pihakDAO.findById(pihak.getIdPihak());
            pihakTemp.setSuratAlamat1(pihak.getSuratAlamat1());
            pihakTemp.setSuratAlamat2(pihak.getSuratAlamat2());
            pihakTemp.setSuratAlamat3(pihak.getSuratAlamat3());
            pihakTemp.setSuratAlamat4(pihak.getSuratAlamat4());
            pihakTemp.setSuratPoskod(pihak.getSuratPoskod());
            KodNegeri kn = kodNegeriDAO.findById(pihak.getSuratNegeri().getKod());
            pihakTemp.setSuratNegeri(kn);
//            p.setInfoAudit(infoAudit);
            pihakService.saveOrUpdate(pihakTemp);

            Pemohon pmohon = pemohonService.findPemohonByPermohonanPihak(p, pihakTemp);
            infoAudit = new InfoAudit();
            infoAudit = pmohon.getInfoAudit();
            infoAudit.setDiKemaskiniOleh(pg);
            infoAudit.setTarikhKemaskini(new java.util.Date());
            pmohon.setInfoAudit(infoAudit);
            pmohon.setKaitan(pemohon.getKaitan());

            if (pihakTemp.getJenisPengenalan().getKod().equals("B") || pihakTemp.getJenisPengenalan().getKod().equals("L") || pihakTemp.getJenisPengenalan().getKod().equals("T") || pihakTemp.getJenisPengenalan().getKod().equals("I")) {
                pmohon.setStatusKahwin(pemohon.getStatusKahwin());
                pmohon.setPekerjaan(pemohon.getPekerjaan());
                pmohon.setUmur(pemohon.getUmur());
                pmohon.setPendapatan(pemohon.getPendapatan());
                //pmohon.setTangungan(pemohon.getTangungan());
            }
            pemohonService.saveOrUpdate(pmohon);
        } catch (Exception e) {
            tx.rollback();
            addSimpleError("Kemasukan Data Gagal. Sila Hubungi Pentadbir Sistem.");
            return new JSP("pelupusan/maklumat_pemohon.jsp").addParameter("tab", "true");
        }
        tx.commit();
        return new JSP("pelupusan/maklumat_pemohon.jsp").addParameter("tab", "true");

    }

    public Resolution tambahCawanganPemohon() {

        if (pihak.getNama() == null) {
            addSimpleError("Sila masukkan data yang bertanda *.");
            cariFlag = true;
            tiadaDataFlag = true;
        } else {
            if (!(pihakDAO.exists(pihak.getIdPihak()))) {
                Transaction tx = sessionProvider.get().getTransaction();
                tx.begin();
                try {
                    if (pihak.getBangsa() != null) {
                        if (pihak.getBangsa().getKod().toString().length() > 4) {
                            pihak.setBangsa(null);
                        }
                    }

                    if (pihak.getSuku() != null) {
                        if (pihak.getSuku().getKod().toString().length() > 4) {
                            pihak.setSuku(null);
                        }
                    }

                    InfoAudit info = new InfoAudit();
                    info.setDimasukOleh(pguna);
                    info.setTarikhMasuk(new java.util.Date());
                    pihak.setInfoAudit(info);
                    pihakService.saveOrUpdate(pihak);

                } catch (Exception ex) {
                    tx.rollback();
                }
                tx.commit();
            }
            tambahCawFlag = true;
        }
        return new JSP("pelupusan/tambah_pemohon.jsp").addParameter("popup", "true");
    }

    public Resolution simpanCawanganPemohon() {

        InfoAudit info = new InfoAudit();
        info.setDimasukOleh(pguna);
        info.setTarikhMasuk(new java.util.Date());

        pihakCawangan.setIbupejabat(pihak);
        pihakCawangan.setInfoAudit(info);
        pihakService.saveOrUpdatePihakCawangan(pihakCawangan);

        cariFlag = true;

        String id = String.valueOf(pihak.getIdPihak());

        if (cariFlag) {
            return new RedirectResolution("/pelupusan/maklumat_pemohon?getTambahCawanganPemohon&idPihak=" + id).addParameter("popup", "true");
        }

        return new StreamingResolution("text/plain", "1");
    }

    public Resolution getTambahCawanganPemohon() {
        String idPihak = getContext().getRequest().getParameter("idPihak");
        cariFlag = true;
        pihak = new Pihak();
        pihak = pihakDAO.findById(Long.parseLong(idPihak));
        cawanganList = pihak.getSenaraiCawangan();
        return new JSP("pelupusan/tambah_pemohon.jsp").addParameter("popup", "true");
    }

    public Resolution editCawanganPemohon() {
        String idCaw = getContext().getRequest().getParameter("idCawangan");
        tambahCawFlag = true;
        pihakCawangan = new PihakCawangan();
        pihakCawangan = cawanganService.findById(idCaw);
        pihak = new Pihak();
        pihak = pihakDAO.findById(pihakCawangan.getIbupejabat().getIdPihak());
        return new JSP("pelupusan/tambah_pemohon.jsp").addParameter("popup", "true");
    }

    public Resolution deletePemohon() {

        String idPemohon = getContext().getRequest().getParameter("idPemohon");
        if (idPemohon != null) {
            Pemohon pmohon = pemohonService.findById(idPemohon);
            if (pmohon != null) {
                pemohonService.delete(pmohon);
                pemohonList = pemohonService.findPemohonByIdPermohonan(idPermohonan);
            }
        }
        rehydrate();
        return showForm();
    }

    public Resolution deleteCawanganPemohon() {

        String idCaw = getContext().getRequest().getParameter("idCawangan");
        cariFlag = true;

        if (idCaw != null) {

            PihakCawangan pihakCaw = cawanganService.findById(idCaw);
            Long id = pihakCaw.getIbupejabat().getIdPihak();

            if (pihakCaw != null) {
                cawanganService.delete(pihakCaw);
                pihak = new Pihak();
                pihak = pihakDAO.findById(id);
                if (pihak.getSenaraiCawangan() != null) {
                    cawanganList = pihak.getSenaraiCawangan();
                }
            }
        }
        return new JSP("pelupusan/tambah_pemohon.jsp").addParameter("popup", "true");
    }

    public Resolution backCawanganPemohon() {

        pihak = pihakService.findPihak(pihak.getJenisPengenalan().getKod(), pihak.getNoPengenalan());
        cawanganList = pihak.getSenaraiCawangan();
        senaraiKodBangsa = listUtil.getSenaraiKodBangsaByJenisPengenalan(pihak.getJenisPengenalan().getKod());
        cariFlag = true;
        tiadaDataFlag = false;
        return new JSP("pelupusan/tambah_pemohon.jsp").addParameter("popup", "true");
    }

    public Resolution showEditPemohon() {
        String idPihak = getContext().getRequest().getParameter("idPihak");
        pihak = pihakDAO.findById(Long.valueOf(idPihak));
        pemohon = pemohonService.findPemohonByPermohonanPihak(p, pihak);
        return new JSP("pelupusan/edit_pemohon.jsp").addParameter("popup", "true");
    }

    public Permohonan getP() {
        return p;
    }

    public void setP(Permohonan p) {
        this.p = p;
    }

    public Pihak getPihak() {
        return pihak;
    }

    public void setPihak(Pihak pihak) {
        this.pihak = pihak;
    }

    public PermohonanPihak getPermohonanPihak() {
        return permohonanPihak;
    }

    public void setPermohonanPihak(PermohonanPihak permohonanPihak) {
        this.permohonanPihak = permohonanPihak;
    }

    public List<Pemohon> getPemohonList() {
        return pemohonList;
    }

    public void setPemohonList(List<Pemohon> pemohonList) {
        this.pemohonList = pemohonList;
    }

    public boolean isCariFlag() {
        return cariFlag;
    }

    public void setCariFlag(boolean cariFlag) {
        this.cariFlag = cariFlag;
    }

    public boolean isTiadaDataFlag() {
        return tiadaDataFlag;
    }

    public void setTiadaDataFlag(boolean tiadaDataFlag) {
        this.tiadaDataFlag = tiadaDataFlag;
    }

    public boolean isTambahCawFlag() {
        return tambahCawFlag;
    }

    public void setTambahCawFlag(boolean tambahCawFlag) {
        this.tambahCawFlag = tambahCawFlag;
    }

    public PihakCawangan getPihakCawangan() {
        return pihakCawangan;
    }

    public void setPihakCawangan(PihakCawangan pihakCawangan) {
        this.pihakCawangan = pihakCawangan;
    }

    public String getIdCawangan() {
        return idCawangan;
    }

    public void setIdCawangan(String idCawangan) {
        this.idCawangan = idCawangan;
    }

    public List<PihakCawangan> getCawanganList() {
        return cawanganList;
    }

    public void setCawanganList(List<PihakCawangan> cawanganList) {
        this.cawanganList = cawanganList;
    }

    public ArrayList getHakmilikList() {
        return hakmilikList;
    }

    public void setHakmilikList(ArrayList hakmilikList) {
        this.hakmilikList = hakmilikList;
    }

    public List<KodBangsa> getSenaraiKodBangsa() {
        return senaraiKodBangsa;
    }

    public void setSenaraiKodBangsa(List<KodBangsa> senaraiKodBangsa) {
        this.senaraiKodBangsa = senaraiKodBangsa;
    }

    public String getTarikhLahir() {
        return tarikhLahir;
    }

    public void setTarikhLahir(String tarikhLahir) {
        this.tarikhLahir = tarikhLahir;
    }

    public Pemohon getPemohon() {
        return pemohon;
    }

    public void setPemohon(Pemohon pemohon) {
        this.pemohon = pemohon;
    }
}
