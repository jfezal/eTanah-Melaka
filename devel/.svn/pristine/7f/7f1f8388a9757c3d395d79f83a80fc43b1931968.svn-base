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
 * @author nursyazwani
 */
//@UrlBinding("/pengambilan/pihak_berkepentingan2")
//public class PihakBerkepentinganActionBean extends AbleActionBean {
//
//    @DefaultHandler
//    public Resolution showForm() {
//        return new ForwardResolution("pengambilan/dev_senarai_pihak_berkepentingan.jsp").addParameter("tab", "true");
//    }
//    public Resolution showForm2() {
//        return new ForwardResolution("pengambilan/dev_paparan_maklumat_pihak_berkepentingan.jsp").addParameter("tab", "true");
//    }
//
//    @Before(stages = {LifecycleStage.BindingAndValidation})
//    public void rehydrate() {
//
//    }
//}

@UrlBinding("/pengambilan/pihak_berkepentingan2")
public class pbtActionBean extends AbleActionBean {

    private static Logger logger = Logger.getLogger(pbtActionBean.class);
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
    private List<PermohonanPihak> senaraiPemohonBerangkai;
    private Pihak pihak;
    private PihakCawangan pihakCawangan;
    private PermohonanPihak permohonanPihak;
    private Pemohon pemohon;

    public Pemohon getPemohon() {
        return pemohon;
    }

    public void setPemohon(Pemohon pemohon) {
        this.pemohon = pemohon;
    }
    private List<Pemohon> pemohonList;
    private List<PermohonanPihak> mohonPihakList;
    private List<PihakCawangan> cawanganList;
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
    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;
    @Inject
    PermohonanAtasPerserahanService mohonAtasSerahService;
    private String idCawangan;
    private List<HakmilikPermohonan> hakmilikPermohonanList2;


    @DefaultHandler
    public Resolution showForm() {
        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        return new ForwardResolution("/WEB-INF/jsp/pengambilan/maklumat_pengambilan_pemohon.jsp").addParameter("tab", "true");
    }

    public Resolution showForm2() {
       
        return new ForwardResolution("/WEB-INF/jsp/pengambilan/maklumat_pengambilan_pemohon.jsp").addParameter("tab", "true");
    }

    public Resolution getSenaraiHakmilikKepentingan() {
        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        if (idPermohonan != null) {
//            p = permohonanService.findById(idPermohonan);
            if (p != null && p.getPermohonanSebelum() != null) {
                Permohonan t = p.getPermohonanSebelum();
                if (t != null) {
                    getContext().getRequest().setAttribute("copy", Boolean.TRUE);
                }
            }
            //fikri :: urusan berangkai (25012010)
            if(p != null) {
                if(StringUtils.isNotBlank( p.getIdKumpulan()) ) {
                    //seandainya urusan berangkai, dan pemohonan belum lg didaftarkan
                    //kena ambil pihak dari pemohon.
                    if(p.getPermohonanSebelum() != null) {
                        Permohonan permohonanSebelum = p.getPermohonanSebelum();
                        String idPermohonanSblm = permohonanSebelum.getIdPermohonan();
                        senaraiPemohonBerangkai = permohonanPihakService.getSenaraiPmohonPihak(idPermohonanSblm);
                        if(senaraiPemohonBerangkai.size() > 0) {
                            getContext().getRequest().setAttribute("berangkai", Boolean.TRUE);
                        }
                    }
                }
            }
        }
        return new JSP("pengambilan/maklumat_pengambilan_pemohon.jsp").addParameter("tab", "true");
    }

    public Resolution senaraiHakmilikPindahMilikBerkepentingan() {
        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        List<HakmilikPermohonan> hakmilikPermohonanList = p.getSenaraiHakmilik();
        if (hakmilikPermohonanList.size() > 0) {
            Hakmilik h = hakmilikPermohonanList.get(0).getHakmilik();
            String kodPihak = "";
            if (p.getKodUrusan().getKod().equals("PMP")) {
                kodPihak = "PJ"; //pemegang pajakan
            } else if(p.getKodUrusan().getKod().equals("PMG")) {
                kodPihak = "PG"; //pemegang gadaian
            }
            pihakKepentinganList2 = hakmilikPihakKepentinganService.findHakmilikPihakByKod(h, kodPihak);
        }
        return new JSP("daftar/kemasukan_pihak_berkepentingan_pindahmilik.jsp").addParameter("tab", "true");
    }

    public Resolution tukar() {
//        PermohonanPihakKemaskini mpk = new PermohonanPihakKemaskini();

        logger.debug("start tukar nama / tukar alamat");

        String kodUrusan = p.getKodUrusan().getKod();
        pihak = pihakDAO.findById(pihak.getIdPihak());
        InfoAudit info = new InfoAudit();
        info.setDimasukOleh(pguna);
        info.setTarikhMasuk(new java.util.Date());


//        List <PermohonanPihakKemaskini> listTemp = new ArrayList();
        if (kodUrusan.equals("TN")) {
            if (nama != null) {
                mohonPihakKemaskini = new PermohonanPihakKemaskini();
                logger.debug("save nama");
                mohonPihakKemaskini.setNamaMedan("nama");
                mohonPihakKemaskini.setNilaiLama(pihak.getNama());
                mohonPihakKemaskini.setNilaiBaru(nama);
                mohonPihakKemaskini.setPermohonan(p);
                mohonPihakKemaskini.setInfoAudit(info);
                mohonPihakKemaskiniService.save(mohonPihakKemaskini);
            }
            if (nokp != null) {
                mohonPihakKemaskini = new PermohonanPihakKemaskini();
                logger.debug("save nokp");
                mohonPihakKemaskini.setNamaMedan("nokp");
                mohonPihakKemaskini.setNilaiLama(pihak.getNoPengenalan());
                mohonPihakKemaskini.setNilaiBaru(nokp);
                mohonPihakKemaskini.setPermohonan(p);
                mohonPihakKemaskini.setInfoAudit(info);
                mohonPihakKemaskiniService.save(mohonPihakKemaskini);
            }
        }
        if (kodUrusan.equals("TA")) {
            if (alamat1 != null) {
                mohonPihakKemaskini = new PermohonanPihakKemaskini();
                logger.debug("save alamat 1");
                mohonPihakKemaskini.setNamaMedan("rumahAlamat1");
                mohonPihakKemaskini.setNilaiLama(pihak.getAlamat1());
                mohonPihakKemaskini.setNilaiBaru(alamat1);
                mohonPihakKemaskini.setPermohonan(p);
                mohonPihakKemaskini.setInfoAudit(info);
                mohonPihakKemaskiniService.save(mohonPihakKemaskini);
//                listTemp.add(mohonPihakKemaskini);
            }
            if (alamat2 != null) {
                mohonPihakKemaskini = new PermohonanPihakKemaskini();
                logger.debug("save alamat 2");
                mohonPihakKemaskini.setNamaMedan("rumahAlamat2");
                mohonPihakKemaskini.setNilaiLama(pihak.getAlamat2());
                mohonPihakKemaskini.setNilaiBaru(alamat2);
                mohonPihakKemaskini.setPermohonan(p);
                mohonPihakKemaskini.setInfoAudit(info);
                mohonPihakKemaskiniService.save(mohonPihakKemaskini);
//                listTemp.add(mohonPihakKemaskini);
            }
            if (alamat3 != null) {
                mohonPihakKemaskini = new PermohonanPihakKemaskini();
                logger.debug("save alamat 3");
                mohonPihakKemaskini.setNamaMedan("rumahAlamat3");
                mohonPihakKemaskini.setNilaiLama(pihak.getAlamat3());
                mohonPihakKemaskini.setNilaiBaru(alamat3);
                mohonPihakKemaskini.setPermohonan(p);
                mohonPihakKemaskini.setInfoAudit(info);
                mohonPihakKemaskiniService.save(mohonPihakKemaskini);
//                listTemp.add(mohonPihakKemaskini);
            }
            if (alamat4 != null) {
                mohonPihakKemaskini = new PermohonanPihakKemaskini();
                logger.debug("save alamat 4");
                mohonPihakKemaskini.setNamaMedan("rumahAlamat4");
                mohonPihakKemaskini.setNilaiLama(pihak.getAlamat4());
                mohonPihakKemaskini.setNilaiBaru(alamat4);
                mohonPihakKemaskini.setPermohonan(p);
                mohonPihakKemaskini.setInfoAudit(info);
                mohonPihakKemaskiniService.save(mohonPihakKemaskini);
//                listTemp.add(mohonPihakKemaskini);
            }
            if (alamat5 != null) {
                mohonPihakKemaskini = new PermohonanPihakKemaskini();
                logger.debug("save alamat 5");
                mohonPihakKemaskini.setNamaMedan("rumahPoskod");
                mohonPihakKemaskini.setNilaiLama(pihak.getPoskod());
                mohonPihakKemaskini.setNilaiBaru(alamat5);
                mohonPihakKemaskini.setPermohonan(p);
                mohonPihakKemaskini.setInfoAudit(info);
                mohonPihakKemaskiniService.save(mohonPihakKemaskini);
//                listTemp.add(mohonPihakKemaskini);
            }
            if (!alamat6.equals("Pilih ...")) {
                mohonPihakKemaskini = new PermohonanPihakKemaskini();
                logger.debug("save alamat 6");
                mohonPihakKemaskini.setNamaMedan("rumahNegeri.kod");

                if (pihak.getNegeri() != null) {
                    mohonPihakKemaskini.setNilaiLama(pihak.getNegeri().getKod());
                } else {
                    mohonPihakKemaskini.setNilaiLama(null);
                }
                mohonPihakKemaskini.setNilaiBaru(alamat6);
                mohonPihakKemaskini.setPermohonan(p);
                mohonPihakKemaskini.setInfoAudit(info);
                mohonPihakKemaskiniService.save(mohonPihakKemaskini);
                mohonPihakKemaskiniList = p.getSenaraiPihakKemaskini();
//                listTemp.add(mohonPihakKemaskini);
            }
        }
        addSimpleMessage("Kemaskini Data Berjaya");
//        rehydrate();
        return new RedirectResolution(pbtActionBean.class, "getSenaraiHakmilikKepentingan");
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


        return new RedirectResolution(pbtActionBean.class, "getSenaraiHakmilikKepentingan");
    }

    public Resolution getPaparanSenaraiHakmilikKepentingan() {
        return new JSP("pengambilan/maklumat_pengambilan_pemohon.jsp").addParameter("tab", "true");
    }

    public Resolution pihakKepentinganPopup() {
        String urusan = getContext().getRequest().getParameter("urusan");
        if (StringUtils.isNotBlank(urusan)) {
            getContext().getRequest().setAttribute("urusan", urusan);
        }
        return new JSP("pengambilan/dev_kemasukan_pihak_berkepentingan.jsp").addParameter("popup", "true");
    }

    public Resolution pemohonPopup() {

        getContext().getRequest().setAttribute("pemohon", Boolean.TRUE);
        String urusan = getContext().getRequest().getParameter("urusan");
        if (StringUtils.isNotBlank(urusan)) {
            getContext().getRequest().setAttribute("urusan", urusan);
        }
        return new JSP("pengambilan/dev_kemasukan_pihak_berkepentingan.jsp").addParameter("popup", "true");
    }

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
            if(permohonan.getKodUrusan().getKod().equals("PJT")) {
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
                return new RedirectResolution("/pengambilan/pihak_berkepentingan2?showPihakKepentinganPajakanForm").addParameter("tab", "true");
            }

            if (StringUtils.isNotBlank(tmp)) {
                return new RedirectResolution("/pengambilan/pihak_berkepentingan2?showPihakKepentinganForm").addParameter("tab", "true");
            } else {
                if(permohonan.getKodUrusan().getKod().equals("PMG") || permohonan.getKodUrusan().getKod().equals("PMP")) {
                    return new RedirectResolution("/pengambilan/pihak_berkepentingan2?senaraiHakmilikPindahMilikBerkepentingan")
                            .addParameter("tab", "true");
                }else{
                    return new RedirectResolution("/pengambilan/pihak_berkepentingan2?getSenaraiHakmilikKepentingan").addParameter("tab", "true");
                }
            }
        } else {
            addSimpleError("Sila masukkan Jenis Pihak Berkepentingan");
        }

        return new StreamingResolution("text/plain", "1");

    }
 public Resolution refreshpage1() {
        rehydrate();
        return new RedirectResolution(pbtActionBean.class, "locate");
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

            pihakTemp.setEmail(pihak.getEmail());
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
            return new RedirectResolution("/pengambilan/pihak_berkepentingan2?getSenaraiHakmilikKepentingan").addParameter("tab", "true");
        }

        return new StreamingResolution("text/plain", "1");
    }

//     public Resolution simpanPemohonPopup() {
//
//        Permohonan permohonan = null;
//
//        if (idPermohonan != null) {
//            permohonan = permohonanService.findById(idPermohonan);
//        }
//        System.out.println("id permohonan :" + permohonan.getIdPermohonan());
//        InfoAudit info = new InfoAudit();
//        info.setDimasukOleh(pguna);
//        info.setTarikhMasuk(new java.util.Date());
//        Pihak pihakTemp = pihakDAO.findById(pihak.getIdPihak());
//
//        if (pihak.getBangsa() != null) {
//            if (pihak.getBangsa().getKod().toString().length() > 4) {
//                pihak.setBangsa(null);
//            }
//        }
//
//        if (pihakTemp == null) {
//            pihakTemp = new Pihak();
//            pihakTemp.setNama(pihak.getNama());
//            pihakTemp.setJenisPengenalan(pihak.getJenisPengenalan());
//            pihakTemp.setNoPengenalan(pihak.getNoPengenalan());
//            pihakTemp.setKodJantina(pihak.getKodJantina());
//            pihakTemp.setBangsa(pihak.getBangsa());
//            pihakTemp.setSuku(pihak.getSuku());
//            pihakTemp.setEmail(pihak.getEmail());
//            pihakTemp.setNoTelefon1(pihak.getNoTelefon1());
//            pihakTemp.setNoTelefon2(pihak.getNoTelefon2());
//            pihakTemp.setNoTelefonBimbit(pihak.getNoTelefonBimbit());
////            pihakTemp.setAlamat1(pihak.getAlamat1());
////            pihakTemp.setAlamat2(pihak.getAlamat2());
////            pihakTemp.setAlamat3(pihak.getAlamat3());
////            pihakTemp.setAlamat4(pihak.getAlamat4());
////            pihakTemp.setPoskod(pihak.getPoskod());
//            pihakTemp.setNegeri(pihak.getNegeri());
//            pihakTemp.setSuratAlamat1(pihak.getSuratAlamat1());
//            pihakTemp.setSuratAlamat2(pihak.getSuratAlamat2());
//            pihakTemp.setSuratAlamat3(pihak.getSuratAlamat3());
//            pihakTemp.setSuratAlamat4(pihak.getSuratAlamat4());
//            pihakTemp.setSuratPoskod(pihak.getSuratPoskod());
////            pihakTemp.setSuratNegeri(pihak.getSuratNegeri());
//        }
//        pihakTemp.setInfoAudit(info);
//
//        Pemohon pemohon = new Pemohon();
//        pemohon.setPermohonan(permohonan);
//        pemohon.setPihak(pihakTemp);
//        pemohon.setInfoAudit(info);
//        pemohon.setCawangan(pguna.getKodCawangan());
//
//
//        if (idCawangan != null) {
//            pihakCawangan = new PihakCawangan();
//            pihakCawangan = pihakCawanganDAO.findById(Long.valueOf(idCawangan));
//            logger.debug(pihakCawangan);
//            pemohon.setPihakCawangan(pihakCawangan);
//        }
//
//        if (pemohon != null) {
//            logger.debug("Simpan:" + pihakTemp);
//            pemohonService.simpanPihakPemohon(pihakTemp, pemohon);
////            return new RedirectResolution("/pengambilan/pihak_berkepentingan?getSenaraiHakmilikKepentingan").addParameter("tab", "true");
//        }
//        return new ForwardResolution("/WEB-INF/jsp/pengambilan/maklumat_pengambilan_pemohon.jsp").addParameter("tab", "true");
////        return new StreamingResolution("text/plain", "1");
//    }














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
                System.out.println(pihak);
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

        return new JSP("pengambilan/dev_kemasukan_pihak_berkepentingan.jsp").addParameter("popup", "true");
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
                System.out.println(pihak);
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

        return new JSP("pengambilan/dev_kemasukan_pihak_berkepentingan.jsp").addParameter("popup", "true");
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
        try {
            getContext().getRequest().setAttribute("edit", Boolean.TRUE);
            Pengguna pg = (Pengguna) context.getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);

            InfoAudit infoAudit = new InfoAudit();

            Pihak pihakTemp = pihakDAO.findById(pihak.getIdPihak());
            pihakTemp.setSuratAlamat1(pihak.getSuratAlamat1());
            pihakTemp.setSuratAlamat2(pihak.getSuratAlamat2());
            pihakTemp.setSuratAlamat3(pihak.getSuratAlamat3());
            pihakTemp.setSuratAlamat4(pihak.getSuratAlamat4());
            pihakTemp.setSuratPoskod(pihak.getSuratPoskod());
            pihakTemp.setNoTelefon1(pihak.getNoTelefon1());
            pihakTemp.setNoTelefon2(pihak.getNoTelefon2());
            pihakTemp.setEmail(pihak.getEmail());
            KodNegeri kn = kodNegeriDAO.findById(pihak.getSuratNegeri().getKod());
            pihakTemp.setSuratNegeri(kn);

            infoAudit = pihakTemp.getInfoAudit();
            infoAudit.setDiKemaskiniOleh(pg);
            infoAudit.setTarikhKemaskini(new java.util.Date());
            pihakTemp.setInfoAudit(infoAudit);

            pihakService.saveOrUpdate(pihakTemp);

            Pemohon pmohon = pemohonService.findPemohonByPermohonanPihak(p, pihakTemp);
            infoAudit = new InfoAudit();
            infoAudit = pmohon.getInfoAudit();
            infoAudit.setDiKemaskiniOleh(pg);
            infoAudit.setTarikhKemaskini(new java.util.Date());
            pmohon.setInfoAudit(infoAudit);

            if (pemohon != null) {
                pmohon.setKaitan(pemohon.getKaitan());
            } else {
                pmohon.setKaitan("");
            }
            if (pihakTemp.getJenisPengenalan().getKod().equals("B") || pihakTemp.getJenisPengenalan().getKod().equals("L") || pihakTemp.getJenisPengenalan().getKod().equals("T") || pihakTemp.getJenisPengenalan().getKod().equals("I")) {

                if (pemohon != null) {
                    pmohon.setStatusKahwin(pemohon.getStatusKahwin());
                    pmohon.setPekerjaan(pemohon.getPekerjaan());
                    pmohon.setUmur(pemohon.getUmur());
                    pmohon.setPendapatan(pemohon.getPendapatan());
//                    pmohon.setTangungan(pemohon.getTangungan());
                }
            }
            pemohonService.saveOrUpdate(pmohon);
        } catch (Exception e) {
            e.printStackTrace();
            tx.rollback();
            addSimpleError("Kemasukan Data Gagal. Sila Hubungi Pentadbir Sistem.");
            return new JSP("pengambilan/maklumat_pengambilan_pemohon.jsp").addParameter("tab", "true");
        }
        tx.commit();

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
        return new JSP("pengambilan/dev_kemasukan_pihak_berkepentingan.jsp").addParameter("popup", "true");
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
            return new RedirectResolution("/pengambilan/pihak_berkepentingan2?getTambahCawanganPenerima&idPihak=" + id).addParameter("popup", "true");
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

        return new JSP("pengambilan/dev_kemasukan_pihak_berkepentingan.jsp").addParameter("popup", "true");

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
        return new JSP("pengambilan/dev_kemasukan_pihak_berkepentingan.jsp").addParameter("popup", "true");
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
            return new RedirectResolution("/pengambilan/pihak_berkepentingan2?getTambahCawanganPemohon&idPihak=" + id).addParameter("popup", "true");
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
        return new JSP("pengambilan/dev_kemasukan_pihak_berkepentingan.jsp").addParameter("popup", "true");
    }

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {
        pguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        p = permohonanService.findById(idPermohonan);

         pihakKepentinganList = new ArrayList<HakmilikPihakBerkepentingan>();

         if (p != null) {
//            p = permohonanService.findById(idPermohonan);
            idPermohonan = p.getIdPermohonan();
             hakmilikPermohonanList2 = p.getSenaraiHakmilik();

            for (HakmilikPermohonan hakmilikPermohonan : hakmilikPermohonanList2) {

                 Hakmilik hak = hakmilikPermohonan.getHakmilik();

                 List<HakmilikPihakBerkepentingan> listHakPihak;
                 listHakPihak =  hak.getSenaraiPihakBerkepentingan();
                 pihakKepentinganList.addAll(listHakPihak);
                 mohonPihakList = permohonanPihakService.getSenaraiPmohonPihak(idPermohonan);
                 pemohonList = pemohonService.findPemohonByIdPermohonan(idPermohonan);


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

    public List<HakmilikPermohonan> getHakmilikPermohonanList2() {
        return hakmilikPermohonanList2;
    }

    public void setHakmilikPermohonanList2(List<HakmilikPermohonan> hakmilikPermohonanList2) {
        this.hakmilikPermohonanList2 = hakmilikPermohonanList2;
    }

}
