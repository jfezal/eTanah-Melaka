/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.stripes.pengambilan;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.dao.KodNegeriDAO;
import etanah.dao.PihakCawanganDAO;
import etanah.dao.PihakDAO;
import etanah.dao.PemohonDAO;
import etanah.dao.PermohonanDAO;
import etanah.service.common.HakmilikService;
import etanah.model.Hakmilik;
import etanah.model.HakmilikPermohonan;
import etanah.model.HakmilikPihakBerkepentingan;
import etanah.model.InfoAudit;
import etanah.model.KodNegeri;
import etanah.model.Pemohon;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.model.PermohonanAtasPihakBerkepentingan;
import etanah.model.PermohonanPihak;
import etanah.model.Pihak;
import etanah.model.PihakCawangan;
import etanah.service.SyerValidationService;
import etanah.service.common.HakmilikPihakKepentinganService;
import etanah.service.common.PemohonService;
import etanah.service.common.PermohonanPihakService;
import etanah.service.common.PermohonanService;
import etanah.service.common.PihakService;
import etanah.view.etanahActionBeanContext;
import java.util.ArrayList;
import java.util.List;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.RedirectResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.StreamingResolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;
import org.apache.commons.lang.StringUtils;
import etanah.model.PermohonanPihakKemaskini;
import etanah.service.daftar.PermohonanPihakKemaskiniService;
import org.apache.log4j.Logger;
import etanah.dao.PermohonanPihakKemaskiniDAO;
import etanah.model.KodCawangan;
import org.hibernate.Session;
import org.hibernate.Transaction;
import etanah.service.common.PermohonanAtasPerserahanService;
import etanah.service.RegService;

/**
 *
 * @author massita
 */
@UrlBinding("/pengambilan/pihak_berkepentingan_Terdahulu")
public class PihakBerkepentinganPermohonanTerdahuluActionBean extends AbleActionBean {

    private static Logger logger = Logger.getLogger(PihakBerkepentinganPermohonanTerdahuluActionBean.class);
    @Inject
    HakmilikService hakmilikManager;
    @Inject
    PihakService pihakService;
    @Inject
    PermohonanPihakService permohonanPihakService;
    @Inject
    PermohonanService permohonanService;
    @Inject
    PemohonService pemohonService;
    @Inject
    SyerValidationService syerService;
    @Inject
    HakmilikPihakKepentinganService hakmilikPihakKepentinganService;
    @Inject
    PihakDAO pihakDAO;
    @Inject
    PemohonDAO pemohonDAO;
    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    KodNegeriDAO kodNegeriDAO;
    @Inject
    PermohonanPihakKemaskiniService mohonPihakKemaskiniService;
    @Inject
    PermohonanPihakKemaskiniDAO mohonPihakKemaskiniDAO;
    @Inject
    PihakCawanganDAO pihakCawanganDAO;
    @Inject
    RegService regService;
    private List<PermohonanPihakKemaskini> mohonPihakKemaskiniList;
    private List<HakmilikPihakBerkepentingan> pihakKepentinganList;
    private List<HakmilikPihakBerkepentingan> othersPihakList;
    private List<PermohonanAtasPihakBerkepentingan> mapList;
    private List<HakmilikPihakBerkepentingan> pihakKepentinganList2;
    private List<HakmilikPihakBerkepentingan> pemilik;
    private List<HakmilikPihakBerkepentingan> selainpemilik;
    private List<PermohonanPihak> senaraiPemohonBerangkai;
    private List<HakmilikPihakBerkepentingan> listTuanTanah;
    private Pihak pihak;
    private PihakCawangan pihakCawangan;
    private PermohonanPihak permohonanPihak;
    private List<Pemohon> pemohonList;
    private List<PermohonanPihak> mohonPihakList;
    private List<PihakCawangan> cawanganList;
    private List<HakmilikPermohonan> hakmilikPermohonanList2;
    private List<HakmilikPermohonan> hakmilikPermohonanList;
//    private HakmilikPermohonan hakmilikPermohonan;
    private String idPermohonan;
    private Permohonan p;
    private Pengguna pguna;
    private String[] syer1;
    private String[] syer2;
    boolean cariFlag;
    boolean tiadaDataFlag = false;
    private PermohonanPihakKemaskini mohonPihakKemaskini;
    private String alamat1;
    private String alamat2;
    private String alamat3;
    private String alamat4;
    private String alamat5;
    private String alamat6;
    private String nama;
    private String nokp;
    private KodCawangan cawangan;
    boolean tambahCawFlag;
    private int size = 0;
    private String idSblm;
    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;
    @Inject
    PermohonanAtasPerserahanService mohonAtasSerahService;
    private String idCawangan;
    private ArrayList list2;

    @DefaultHandler
    public Resolution showForm() {
        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        Permohonan p = permohonanDAO.findById(idPermohonan);

        if (p.getPermohonanSebelum() != null) {
            idSblm = p.getPermohonanSebelum().getIdPermohonan();
        }
        if (idSblm != null) {
            getContext().getRequest().setAttribute("IdSblmIsNull", Boolean.TRUE);
        } else {
            addSimpleError("Haraf maaf sila masukkan id Permohonan Terdahulu");
            getContext().getRequest().setAttribute("IdSblmIsNull", Boolean.FALSE);
        }
        return new ForwardResolution("/WEB-INF/jsp/pengambilan/dev_senarai_pihak_berkepentingan.jsp").addParameter("tab", "true");
    }

    public Resolution showForm2() {
        return new ForwardResolution("/WEB-INF/jsp/pengambilan/maklumat_pengambilan_pemohon.jsp").addParameter("tab", "true");
    }

    public Resolution getSenaraiHakmilikKepentingan() {
//        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        if (idPermohonan != null) {
//            p = permohonanService.findById(idPermohonan);
            if (p != null && p.getPermohonanSebelum() != null) {
                Permohonan t = p.getPermohonanSebelum();
                if (t != null) {
                    getContext().getRequest().setAttribute("copy", Boolean.TRUE);
                }
            }
            //fikri :: urusan berangkai (25012010)
            if (p != null) {
                if (StringUtils.isNotBlank(p.getIdKumpulan())) {
                    //seandainya urusan berangkai, dan pemohonan belum lg didaftarkan
                    //kena ambil pihak dari pemohon.
                    if (p.getPermohonanSebelum() != null) {
                        Permohonan permohonanSebelum = p.getPermohonanSebelum();
                        String idPermohonanSblm = permohonanSebelum.getIdPermohonan();
                        senaraiPemohonBerangkai = permohonanPihakService.getSenaraiPmohonPihak(idPermohonanSblm);
                        if (senaraiPemohonBerangkai.size() > 0) {
                            getContext().getRequest().setAttribute("berangkai", Boolean.TRUE);
                        }
                    }
                }
            }
        }
        return new JSP("pengambilan/dev_senarai_pihak_berkepentingan.jsp").addParameter("tab", "true");
    }

    public Resolution senaraiHakmilikPindahMilikBerkepentingan() {
//        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
//        List<HakmilikPermohonan> hakmilikPermohonanList = p.getSenaraiHakmilik();
        if (hakmilikPermohonanList.size() > 0) {
            Hakmilik h = hakmilikPermohonanList.get(0).getHakmilik();
            String kodPihak = "";
            if (p.getKodUrusan().getKod().equals("PMP")) {
                kodPihak = "PJ"; //pemegang pajakan
            } else if (p.getKodUrusan().getKod().equals("PMG")) {
                kodPihak = "PG"; //pemegang gadaian
            }
            pihakKepentinganList2 = hakmilikPihakKepentinganService.findHakmilikPihakByKod(h, kodPihak);
        }
        return new JSP("daftar/kemasukan_pihak_berkepentingan_pindahmilik.jsp").addParameter("tab", "true");
    }

    public Resolution senaraiPihakBerkepentingan() {
//        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
//        List<HakmilikPermohonan> hakmilikPermohonanList = p.getSenaraiHakmilik();
        if (hakmilikPermohonanList.size() > 0) {
            Hakmilik h = hakmilikPermohonanList.get(0).getHakmilik();
            String kodPihak = "PM";
            pihakKepentinganList2 = hakmilikPihakKepentinganService.findHakmilikPihakByKod(h, kodPihak);
        }
        return new JSP("daftar/kemasukan_pihak_berkepentingan_pindahmilik.jsp").addParameter("tab", "true");
    }

    public Resolution refreshpage() {
        rehydrate();
        return new RedirectResolution(PihakBerkepentinganPermohonanTerdahuluActionBean.class, "locate");
    }

    public Resolution simpanPemohon() {

        String idPihak = getContext().getRequest().getParameter("idPihak");
        System.out.println("idPihak :" + idPihak);
        Pihak pi = pihakService.findById(idPihak);
        Pemohon pemohon = pemohonService.findPemohonByPermohonanPihak(p, pi);
        if (pemohon == null) {
            Pemohon pe = new Pemohon();
            InfoAudit ia = new InfoAudit();
            ia.setDimasukOleh(pguna);
            ia.setTarikhMasuk(new java.util.Date());
            pe.setInfoAudit(ia);
            pe.setPermohonan(p);
            pe.setPihak(pi);
            pe.setCawangan(pguna.getKodCawangan());
            pemohonService.saveOrUpdate(pe);
        } else {
            addSimpleError("Tuan Tanah " + pi.getNama() + " telah memohon. Sila pilih tuan tanah yang lain.");
        }
//        return new JSP("daftar/kemaskini_senarai_pihak_berkepentingan.jsp").addParameter("tab", "true");
        rehydrate();
        return getSenaraiHakmilikKepentingan();
    }

    public Resolution simpanPemohon2() {

        String idPihak = getContext().getRequest().getParameter("idPihak");
        System.out.println("idPihak :" + idPihak);
        Pihak pi = pihakService.findById(idPihak);
        Pemohon pemohon = pemohonService.findPemohonByPermohonanPihak(p, pi);
        if (pemohon == null) {
            Pemohon pe = new Pemohon();
            InfoAudit ia = new InfoAudit();
            ia.setDimasukOleh(pguna);
            ia.setTarikhMasuk(new java.util.Date());
            pe.setInfoAudit(ia);
            pe.setPermohonan(p);
            pe.setPihak(pi);
            pemohonService.saveOrUpdate(pe);
        } else {
            addSimpleError(pi.getNama() + " telah memohon.");
        }
//        return new JSP("daftar/kemaskini_senarai_pihak_berkepentingan.jsp").addParameter("tab", "true");
        rehydrate();
        return senaraiHakmilikPindahMilikBerkepentingan();
    }

    public Resolution deletePemohon() {

        String idPemohon = getContext().getRequest().getParameter("idPemohon");
        if (idPemohon != null) {
            Pemohon pemohon = pemohonService.findById(idPemohon);
            if (pemohon != null) {
                pemohonService.delete(pemohon);
                pemohonList = pemohonService.findPemohonByIdPermohonan(idPermohonan);
            }
        }
//        return new JSP("daftar/kemaskini_senarai_pihak_berkepentingan.jsp").addParameter("tab", "true");
        rehydrate();
        return getSenaraiHakmilikKepentingan();
    }

    public Resolution pemohonPopup() {
        return new JSP("pengambilan/maklumat_pemohon.jsp").addParameter("popup", "true");
    }

    public Resolution deleteSingle() {
//             InfoAudit ia = new InfoAudit();
//            Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        String idPemohon = getContext().getRequest().getParameter("idPemohon");

        if (idPemohon != null) {
            Pemohon pemohon = pemohonService.findById(idPemohon);
            if (pemohon != null) {
                pemohonService.delete(pemohon);
                pemohonList = pemohonService.findPemohonByIdPermohonan(idPermohonan);
            }
        }

        return new RedirectResolution(PihakBerkepentinganPermohonanTerdahuluActionBean.class, "locate");

    }

    public Resolution deletePemohon2() {

        String idPemohon = getContext().getRequest().getParameter("idPemohon");
        if (idPemohon != null) {
            Pemohon pemohon = pemohonService.findById(idPemohon);
            if (pemohon != null) {
                pemohonService.delete(pemohon);
                pemohonList = pemohonService.findPemohonByIdPermohonan(idPermohonan);
            }
        }
//        return new JSP("daftar/kemaskini_senarai_pihak_berkepentingan.jsp").addParameter("tab", "true");
        rehydrate();
        return senaraiHakmilikPindahMilikBerkepentingan();
    }

    public Resolution deleteChanges() {

        String idKkini = (String) getContext().getRequest().getParameter("idKkini");
        logger.debug("idKkini : " + idKkini);
        if (idKkini != null) {
            PermohonanPihakKemaskini mpk = mohonPihakKemaskiniDAO.findById(new Long(idKkini));
            if (mpk != null) {
                mohonPihakKemaskiniService.delete(mpk);
                mohonPihakKemaskiniList = p.getSenaraiPihakKemaskini();
            }
//            getContext().getMessages().add(new SimpleMessage("Data {1}.", mpk));
            addSimpleMessage("Data Telah Berjaya diHapuskan");
        }
        addSimpleError("idKkini tiada");


        return new RedirectResolution(PihakBerkepentinganPermohonanTerdahuluActionBean.class, "getSenaraiHakmilikKepentingan");
    }

    public Resolution getPaparanSenaraiHakmilikKepentingan() {
        return new JSP("pengambilan/dev_senarai_pihak_berkepentingan.jsp").addParameter("tab", "true");
    }

    public Resolution pihakKepentinganPopup() {
        String urusan = getContext().getRequest().getParameter("urusan");
        if (StringUtils.isNotBlank(urusan)) {
            getContext().getRequest().setAttribute("urusan", urusan);
        }
        return new JSP("pengambilan/dev_kemasukan_pihak_berkepentingan.jsp").addParameter("popup", "true");
    }

//    public Resolution pemohonPopup() {
//
//        getContext().getRequest().setAttribute("pemohon", Boolean.TRUE);
//        String urusan = getContext().getRequest().getParameter("urusan");
//        if (StringUtils.isNotBlank(urusan)) {
//            getContext().getRequest().setAttribute("urusan", urusan);
//        }
//        return new JSP("pengambilan/dev_kemasukan_pihak_berkepentingan.jsp").addParameter("popup", "true");
//    }
    public Resolution showPihakKepentinganForm() {
//        getContext().getRequest().setAttribute("popup", "false");
        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        return new JSP("daftar/kemasukan_senarai_tuan_tanah.jsp").addParameter("tab", "true");
    }

    public Resolution showPihakKepentinganPajakanForm() {
        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        return new JSP("daftar/kemasukan_pihak_pajakan.jsp").addParameter("tab", "true");
    }

    public Resolution showPihakBerkepentinganKaveatForm() {
        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        if (p != null) {
            mapList = p.getSenaraiPermohonanAtasPihakBerkepentingan();
        }
        return new JSP("daftar/kemasukan_tuan_tanah_kaveat.jsp").addParameter("tab", "true");
    }

    public Resolution showEditNamaPemohon() {
        String idPihak = getContext().getRequest().getParameter("idPihak");

        pihak = pihakDAO.findById(Long.valueOf(idPihak));

        return new JSP("pengambilan/edit_nama_pemohon.jsp").addParameter("popup", "true");
    }

    public Resolution showEditAlamatPemohon() {
        String idPihak = getContext().getRequest().getParameter("idPihak");

        pihak = pihakDAO.findById(Long.valueOf(idPihak));

        return new JSP("pengambilan/edit_alamat_pemohon.jsp").addParameter("popup", "true");
    }

    public Resolution showEditPemohon() {
        String idPihak = getContext().getRequest().getParameter("idPihak");
        pihak = pihakDAO.findById(Long.valueOf(idPihak));
        return new JSP("pengambilan/edit_pemohon.jsp").addParameter("popup", "true");
    }

    public Resolution simpanPihakKepentinganPopup() {
        String tmp = getContext().getRequest().getParameter("urusan");

        Permohonan permohonan = null;

        if (idPermohonan != null) {
            permohonan = permohonanService.findById(idPermohonan);
        }
        System.out.println("id permohonan :" + permohonan.getIdPermohonan());
        InfoAudit info = new InfoAudit();
        info.setDimasukOleh(pguna);
        info.setTarikhMasuk(new java.util.Date());
        Pihak t = pihakDAO.findById(pihak.getIdPihak());

        if (pihak.getBangsa() != null) {
            if (pihak.getBangsa().getKod().toString().length() > 4) {
                pihak.setBangsa(null);
            }
        }

        if (t == null) {
            t = new Pihak();
            t.setNama(pihak.getNama());
            t.setJenisPengenalan(pihak.getJenisPengenalan());
            t.setNoPengenalan(pihak.getNoPengenalan());
            t.setKodJantina(pihak.getKodJantina());
            t.setBangsa(pihak.getBangsa());
            t.setSuku(pihak.getSuku());
            t.setNoTelefon1(pihak.getNoTelefon1());
            t.setNoTelefon2(pihak.getNoTelefon2());
            t.setNoTelefonBimbit(pihak.getNoTelefonBimbit());
            t.setAlamat1(pihak.getAlamat1());
            t.setAlamat2(pihak.getAlamat2());
            t.setAlamat3(pihak.getAlamat3());
            t.setAlamat4(pihak.getAlamat4());
            t.setPoskod(pihak.getPoskod());
            t.setSuratAlamat1(pihak.getSuratAlamat1());
            t.setSuratAlamat2(pihak.getSuratAlamat2());
            t.setSuratAlamat3(pihak.getSuratAlamat3());
            t.setSuratAlamat4(pihak.getSuratAlamat4());
            t.setSuratPoskod(pihak.getSuratPoskod());
            t.setSuratNegeri(pihak.getSuratNegeri());
        }
        t.setInfoAudit(info);

        if (permohonanPihak != null) {

            permohonanPihak.setPermohonan(permohonan);
            permohonanPihak.setPihak(t);
            if (permohonan.getKodUrusan().getKod().equals("PJT")) {
                permohonanPihak.setSyerPembilang(1);
                permohonanPihak.setSyerPenyebut(1);
            } else {
                permohonanPihak.setSyerPembilang(0);
                permohonanPihak.setSyerPenyebut(0);
            }
            permohonanPihak.setInfoAudit(info);

            if (idCawangan != null) {
                pihakCawangan = new PihakCawangan();
                pihakCawangan = pihakCawanganDAO.findById(Long.valueOf(idCawangan));
//                permohonanPihak.setCawangan(pihakCawangan);
            }

            pihakService.saveOrUpdatePihakKepentinganPihak(t, permohonanPihak);
            if (pihakCawangan != null) {
                pihakCawangan.setIbupejabat(t);
                pihakCawangan.setInfoAudit(info);
                pihakService.saveOrUpdatePihakCawangan(pihakCawangan);
            }

            if (StringUtils.isNotBlank(tmp) && tmp.equals("pajakan")) {
                return new RedirectResolution("/pembangunan/pihak_berkepentingan?showPihakKepentinganPajakanForm").addParameter("tab", "true");
            }

            if (StringUtils.isNotBlank(tmp)) {
                return new RedirectResolution("/pembangunan/pihak_berkepentingan?showPihakKepentinganForm").addParameter("tab", "true");
            } else {
                if (permohonan.getKodUrusan().getKod().equals("PMG") || permohonan.getKodUrusan().getKod().equals("PMP")) {
                    return new RedirectResolution("/pembangunan/pihak_berkepentingan?senaraiHakmilikPindahMilikBerkepentingan").addParameter("tab", "true");
                } else {
                    return new RedirectResolution("/pembangunan/pihak_berkepentingan?getSenaraiHakmilikKepentingan").addParameter("tab", "true");
                }
            }
        } else {
            addSimpleError("Sila masukkan Jenis Pihak Berkepentingan");
        }

        return new StreamingResolution("text/plain", "1");

    }

    public Resolution simpanPemohonPopup() {

        Permohonan permohonan = null;

        if (idPermohonan != null) {
            permohonan = permohonanService.findById(idPermohonan);
        }
        System.out.println("id permohonan :" + permohonan.getIdPermohonan());
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
        }
        pihakTemp.setInfoAudit(info);

        Pemohon pemohon = new Pemohon();
        pemohon.setPermohonan(permohonan);
        pemohon.setPihak(pihakTemp);
        pemohon.setInfoAudit(info);
        pemohon.setCawangan(pguna.getKodCawangan());


        if (idCawangan != null) {
            pihakCawangan = new PihakCawangan();
            pihakCawangan = pihakCawanganDAO.findById(Long.valueOf(idCawangan));
            logger.debug(pihakCawangan);
            pemohon.setPihakCawangan(pihakCawangan);
        }

        if (pemohon != null) {
            logger.debug("Simpan:" + pihakTemp);
            pemohonService.simpanPihakPemohon(pihakTemp, pemohon);
//            return new RedirectResolution("/pengambilan/pihak_berkepentingan?getSenaraiHakmilikKepentingan").addParameter("tab", "true");
        }
        return new ForwardResolution("/WEB-INF/jsp/pengambilan/maklumat_pengambilan_pemohon.jsp").addParameter("tab", "true");
//        return new StreamingResolution("text/plain", "1");
    }

    public Resolution cariPihak() {

        String tmp = getContext().getRequest().getParameter("tuanTanah");
        if (StringUtils.isNotBlank(tmp)) {
            getContext().getRequest().setAttribute("tuanTanah", Boolean.TRUE);
        }

        List<Pihak> listPihak;

        if (pihak != null) {

            listPihak = pihakDAO.findByExample(pihak);

            if (!(listPihak.isEmpty())) {
                pihak = listPihak.get(0);
                cawanganList = pihak.getSenaraiCawangan();
                cariFlag = true;
                tiadaDataFlag = false;
            } else {
                addSimpleError("Tiada Data. Sila Masukkan Maklumat Baru.");
                cariFlag = true;
                tiadaDataFlag = true;
            }

        } else {

            addSimpleError("Sila Masukkan Jenis Pengenalan Dan Nombor Pengenalan");
        }

        return new JSP("pembangunan/common/dev_kemasukan_pihak_berkepentingan.jsp").addParameter("popup", "true");
    }

    public Resolution cariPihakPemohon() {

        getContext().getRequest().setAttribute("pemohon", Boolean.TRUE);

        String tmp = getContext().getRequest().getParameter("tuanTanah");
        if (StringUtils.isNotBlank(tmp)) {
            getContext().getRequest().setAttribute("tuanTanah", Boolean.TRUE);
        }

        List<Pihak> listPihak;

        if (pihak != null) {

            listPihak = pihakDAO.findByExample(pihak);

            if (!(listPihak.isEmpty())) {
                pihak = listPihak.get(0);
                cawanganList = pihak.getSenaraiCawangan();
                cariFlag = true;
                tiadaDataFlag = false;
            } else {
                addSimpleError("Tiada Data. Sila Masukkan Maklumat Baru.");
                cariFlag = true;
                tiadaDataFlag = true;
            }

        } else {

            addSimpleError("Sila Masukkan Jenis Pengenalan Dan Nombor Pengenalan");
        }

        return new JSP("pembangunan/common/dev_kemasukan_pihak_berkepentingan.jsp").addParameter("popup", "true");
    }

    public Resolution semakSyer() {

        String result = "Pembahagian tanah berjaya.";
        int i = 0;
        List<PermohonanPihak> permPihak = new ArrayList<PermohonanPihak>();
        for (PermohonanPihak pp : mohonPihakList) {
//            Fraction f = new Fraction(Integer.parseInt(syer1[i]), Integer.parseInt(syer2[i]));
//            pp.setSyer(f.doubleValue());
            pp.setSyerPembilang(Integer.parseInt(syer1[i]));
            pp.setSyerPenyebut(Integer.parseInt(syer2[i]));
            permPihak.add(pp);
            i = i + 1;
        }
        permohonanPihakService.saveOrUpdate(permPihak);

//        try {
//            int r = syerService.doValidateSyerPortion(idPermohonan);
//            if (r < 0) {
//                result = "Jumlah pembahagian tanah tidak mencukupi.";
//            } else if (r > 0) {
//                result = "Jumlah pembahagian tanah melebihi daripada bahagian pemohon.";
//            }
//
//        } catch (Exception ex) {
//            return new StreamingResolution("text/plain", "Pembahagian Tanah Gagal.");
//        }
        return new StreamingResolution("text/plain", result);
    }

    public Resolution simpanPihak() {
        Transaction tx = sessionProvider.get().beginTransaction();
        tx.begin();
        Pengguna pg = (Pengguna) context.getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        Pihak pi = pihakDAO.findById(pihak.getIdPihak());
        if (pihak == null) {
            InfoAudit ia = new InfoAudit();
            ia.setDimasukOleh(pg);
            ia.setTarikhMasuk(new java.util.Date());
            pi.setInfoAudit(ia);
            pihakService.saveOrUpdate(pi);

        } else {
//        try {
//            getContext().getRequest().setAttribute("edit", Boolean.TRUE);

//            Pengguna pg = (Pengguna) context.getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
            InfoAudit ia = new InfoAudit();
            ia.setDiKemaskiniOleh(pg);
            ia.setTarikhKemaskini(new java.util.Date());
            pi.setSuratAlamat1(pihak.getSuratAlamat1());
            pi.setSuratAlamat2(pihak.getSuratAlamat2());
            pi.setSuratAlamat3(pihak.getSuratAlamat3());
            pi.setSuratAlamat4(pihak.getSuratAlamat4());
            pi.setSuratPoskod(pihak.getSuratPoskod());
            KodNegeri kn = kodNegeriDAO.findById(pihak.getSuratNegeri().getKod());
            pi.setSuratNegeri(kn);
            pi.setInfoAudit(ia);
            pihakService.saveOrUpdate(pi);
//        } catch (Exception e) {
//            tx.rollback();
//            addSimpleError("Kemasukan Data Gagal. Sila Hubungi Pentadbir Sistem.");
//            return new JSP("pengambilan/edit_pemohon.jsp").addParameter("tab", "true");
//        }
//        }
//        tx.commit();
        }

        return new JSP("pengambilan/maklumat_pengambilan_pemohon.jsp").addParameter("tab", "true");

    }

    public Resolution tambahCawangan() {

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
        return new JSP("pembangunan/common/dev_kemasukan_pihak_berkepentingan.jsp").addParameter("popup", "true");
    }

    public Resolution simpanCawangan() {

        InfoAudit info = new InfoAudit();
        info.setDimasukOleh(pguna);
        info.setTarikhMasuk(new java.util.Date());

        pihakCawangan.setIbupejabat(pihak);
        pihakCawangan.setInfoAudit(info);
        pihakService.saveOrUpdatePihakCawangan(pihakCawangan);

        cariFlag = true;

        String id = String.valueOf(pihak.getIdPihak());

        if (cariFlag) {
            return new RedirectResolution("/pembangunan/pihak_berkepentingan?getTambahCawanganPenerima&idPihak=" + id).addParameter("popup", "true");
        }

        return new StreamingResolution("text/plain", "1");

    }

    public Resolution getTambahCawanganPenerima() {
        String idPihak = getContext().getRequest().getParameter("idPihak");
        cariFlag = true;
        pihak = new Pihak();
        pihak = pihakDAO.findById(Long.parseLong(idPihak));
        if (pihak.getSenaraiCawangan() != null) {
            cawanganList = pihak.getSenaraiCawangan();
        }

        return new JSP("pembangunan/common/dev_kemasukan_pihak_berkepentingan.jsp").addParameter("popup", "true");

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
            getContext().getRequest().setAttribute("pemohon", Boolean.TRUE);
        }
        return new JSP("pembangunan/common/dev_kemasukan_pihak_berkepentingan.jsp").addParameter("popup", "true");
    }

    public Resolution simpanCawanganPemohon() {

        getContext().getRequest().setAttribute("pemohon", Boolean.TRUE);
        InfoAudit info = new InfoAudit();
        info.setDimasukOleh(pguna);
        info.setTarikhMasuk(new java.util.Date());

        pihakCawangan.setIbupejabat(pihak);
        pihakCawangan.setInfoAudit(info);
        pihakService.saveOrUpdatePihakCawangan(pihakCawangan);

        cariFlag = true;

        String id = String.valueOf(pihak.getIdPihak());

        if (cariFlag) {
            return new RedirectResolution("/pembangunan/pihak_berkepentingan?getTambahCawanganPemohon&idPihak=" + id).addParameter("popup", "true");
        }

        return new StreamingResolution("text/plain", "1");
    }

    public Resolution getTambahCawanganPemohon() {
        String idPihak = getContext().getRequest().getParameter("idPihak");
        cariFlag = true;

        pihak = new Pihak();
        pihak = pihakDAO.findById(Long.parseLong(idPihak));

        cawanganList = pihak.getSenaraiCawangan();

        getContext().getRequest().setAttribute("pemohon", Boolean.TRUE);
        return new JSP("pembangunan/common/dev_kemasukan_pihak_berkepentingan.jsp").addParameter("popup", "true");
    }

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {
        pguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        p = permohonanDAO.findById(idPermohonan);
        pihakKepentinganList = new ArrayList<HakmilikPihakBerkepentingan>();
        listTuanTanah = new ArrayList<HakmilikPihakBerkepentingan>();

        if (p.getPermohonanSebelum() != null) {
            String idSblm = p.getPermohonanSebelum().getIdPermohonan();

            if (idSblm != null) {
//            Permohonan Sebelum = permohonanDAO.findById(idSblm);
                p = permohonanService.findById(idPermohonan);
                idPermohonan = p.getIdPermohonan();
                hakmilikPermohonanList2 = p.getSenaraiHakmilik();

                for (HakmilikPermohonan hakmilikPermohonan : hakmilikPermohonanList2) {

                    Hakmilik hak = hakmilikPermohonan.getHakmilik();

                    List<HakmilikPihakBerkepentingan> listHakPihak;
                    listHakPihak = hak.getSenaraiPihakBerkepentingan();
                    pihakKepentinganList.addAll(listHakPihak);
                    System.out.println("List Tuan Tanah" + pihakKepentinganList);
                    mohonPihakList = permohonanPihakService.getSenaraiPmohonPihak(idPermohonan);
                    pemohonList = pemohonService.findPemohonByIdPermohonan(idPermohonan);
                    pemilik = hakmilikPihakKepentinganService.findPihakActiveByHakmilik(hak);
                    String kodPihak = "PM";

                    listTuanTanah = hakmilikPihakKepentinganService.findHakmilikPihakByKod(hak, kodPihak);
                    System.out.println("List Tuan Tanah" + listTuanTanah);
                    size = listHakPihak.size();
                }



//             for (int i = 0; i < hakmilikPermohonanList.size(); i++) {
//                HakmilikPermohonan h = (HakmilikPermohonan) hakmilikPermohonanList.get(i);
//                pihakKepentinganList=h.getHakmilik().getSenaraiPihakBerkepentingan();
//                size = pihakKepentinganList.size()+ size;
//            }


//                    othersPihakList = hakmilikPihakKepentinganService.findHakmilikPihakBerkepentinganByIdHakmilik(hk.getIdHakmilik());
//                    mohonPihakList = permohonanPihakService.getSenaraiPmohonPihak(idPermohonan);
//                    pemohonList = pemohonService.findPemohonByIdPermohonan(idPermohonan);
//                    syer1 = new String[mohonPihakList.size()];
//                    syer2 = new String[mohonPihakList.size()];

//                    pihakKepentinganList = hakmilikPihakKepentinganService.findHakmilikPihakActiveByHakmilik(hk);
//            if (hakmilikPermohonanList.size() > 0) {
//                Hakmilik hk = hakmilikPermohonanList.get(0).getHakmilik();
//                if (hk != null) {
//                    pihakKepentinganList = hk.getSenaraiPihakBerkepentingan();
////                    pihakKepentinganList = hakmilikPihakKepentinganService.findHakmilikPihakActiveByHakmilik(hk);
//                    size = pihakKepentinganList.size();
//                    othersPihakList = hakmilikPihakKepentinganService.findHakmilikPihakBerkepentinganByIdHakmilik(hk.getIdHakmilik());
//                    mohonPihakList = permohonanPihakService.getSenaraiPmohonPihak(idPermohonan);
//                    pemohonList = pemohonService.findPemohonByIdPermohonan(idPermohonan);
//                    syer1 = new String[mohonPihakList.size()];
//                    syer2 = new String[mohonPihakList.size()];
//                }
//            }


//            if (mohonPihakList != null) {
//                for (int i = 0; i < mohonPihakList.size(); i++) {
//                    PermohonanPihak pp = mohonPihakList.get(i);
//                    syer1[i] = String.valueOf(pp.getSyerPembilang());
//                    syer2[i] = String.valueOf(pp.getSyerPenyebut());
//                }
//            }

                if (mohonPihakList != null) {
                    syer1 = new String[mohonPihakList.size()];
                    syer2 = new String[mohonPihakList.size()];
                    for (int i = 0; i < mohonPihakList.size(); i++) {
                        PermohonanPihak pp = mohonPihakList.get(i);
                        syer1[i] = String.valueOf(pp.getSyerPembilang());
                        syer2[i] = String.valueOf(pp.getSyerPenyebut());
                    }
                }
            }
        }
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

    public String getAlamat5() {
        return alamat5;
    }

    public void setAlamat5(String alamat5) {
        this.alamat5 = alamat5;
    }

    public String getAlamat6() {
        return alamat6;
    }

    public void setAlamat6(String alamat6) {
        this.alamat6 = alamat6;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getNokp() {
        return nokp;
    }

    public void setNokp(String nokp) {
        this.nokp = nokp;
    }

    public PermohonanPihakKemaskini getMohonPihakKemaskini() {
        return mohonPihakKemaskini;
    }

    public void setMohonPihakKemaskini(PermohonanPihakKemaskini mohonPihakKemaskini) {
        this.mohonPihakKemaskini = mohonPihakKemaskini;
    }

    public List<PermohonanPihakKemaskini> getMohonPihakKemaskiniList() {
        return mohonPihakKemaskiniList;
    }

    public void setMohonPihakKemaskiniList(List<PermohonanPihakKemaskini> mohonPihakKemaskiniList) {
        this.mohonPihakKemaskiniList = mohonPihakKemaskiniList;
    }

    public Permohonan getP() {
        return p;
    }

    public void setP(Permohonan p) {
        this.p = p;
    }

    public List<HakmilikPihakBerkepentingan> getPihakKepentinganList() {
        return pihakKepentinganList;
    }

    public void setPihakKepentinganList(List<HakmilikPihakBerkepentingan> pihakKepentinganList) {
        this.pihakKepentinganList = pihakKepentinganList;
    }

    public Pihak getPihak() {
        return pihak;
    }

    public void setPihak(Pihak pihak) {
        this.pihak = pihak;
    }

    public List<PermohonanPihak> getMohonPihakList() {
        return mohonPihakList;
    }

    public void setMohonPihakList(List<PermohonanPihak> mohonPihakList) {
        this.mohonPihakList = mohonPihakList;
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

    public String[] getSyer1() {
        return syer1;
    }

    public void setSyer1(String[] syer1) {
        this.syer1 = syer1;
    }

    public String[] getSyer2() {
        return syer2;
    }

    public void setSyer2(String[] syer2) {
        this.syer2 = syer2;
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

    public List<PermohonanAtasPihakBerkepentingan> getMapList() {
        return mapList;
    }

    public void setMapList(List<PermohonanAtasPihakBerkepentingan> mapList) {
        this.mapList = mapList;
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

    public List<HakmilikPihakBerkepentingan> getOthersPihakList() {
        return othersPihakList;
    }

    public void setOthersPihakList(List<HakmilikPihakBerkepentingan> othersPihakList) {
        this.othersPihakList = othersPihakList;
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

    public List<HakmilikPihakBerkepentingan> getPihakKepentinganList2() {
        return pihakKepentinganList2;
    }

    public void setPihakKepentinganList2(List<HakmilikPihakBerkepentingan> pihakKepentinganList2) {
        this.pihakKepentinganList2 = pihakKepentinganList2;
    }

    public List<PermohonanPihak> getSenaraiPemohonBerangkai() {
        return senaraiPemohonBerangkai;
    }

    public void setSenaraiPemohonBerangkai(List<PermohonanPihak> senaraiPemohonBerangkai) {
        this.senaraiPemohonBerangkai = senaraiPemohonBerangkai;
    }

    public List<HakmilikPermohonan> getHakmilikPermohonanList2() {
        return hakmilikPermohonanList2;
    }

    public void setHakmilikPermohonanList2(List<HakmilikPermohonan> hakmilikPermohonanList2) {
        this.hakmilikPermohonanList2 = hakmilikPermohonanList2;
    }

//    public List<HakmilikPermohonan> getHakmilikPermohonanList() {
//        return hakmilikPermohonanList;
//    }
//
//    public void setHakmilikPermohonanList(List<HakmilikPermohonan> hakmilikPermohonanList) {
//        this.hakmilikPermohonanList = hakmilikPermohonanList;
//    }
//    public HakmilikPermohonan getHakmilikPermohonan() {
//        return hakmilikPermohonan;
//    }
//
//    public void setHakmilikPermohonan(HakmilikPermohonan hakmilikPermohonan) {
//        this.hakmilikPermohonan = hakmilikPermohonan;
//    }
    /**
     * @return the cawangan
     */
    public KodCawangan getCawangan() {
        return cawangan;
    }

    /**
     * @param cawangan the cawangan to set
     */
    public void setCawangan(KodCawangan cawangan) {
        this.cawangan = cawangan;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public ArrayList getList2() {
        return list2;
    }

    public void setList2(ArrayList list2) {
        this.list2 = list2;
    }

    public List<HakmilikPihakBerkepentingan> getPemilik() {
        return pemilik;
    }

    public void setPemilik(List<HakmilikPihakBerkepentingan> pemilik) {
        this.pemilik = pemilik;
    }

    public List<HakmilikPihakBerkepentingan> getSelainpemilik() {
        return selainpemilik;
    }

    public void setSelainpemilik(List<HakmilikPihakBerkepentingan> selainpemilik) {
        this.selainpemilik = selainpemilik;
    }

    public List<HakmilikPihakBerkepentingan> getListTuanTanah() {
        return listTuanTanah;
    }

    public void setListTuanTanah(List<HakmilikPihakBerkepentingan> listTuanTanah) {
        this.listTuanTanah = listTuanTanah;
    }

    public String getIdSblm() {
        return idSblm;
    }

    public void setIdSblm(String idSblm) {
        this.idSblm = idSblm;
    }
}
