/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.stripes;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.dao.HakmilikDAO;
import etanah.dao.HakmilikPihakBerkepentinganDAO;
import etanah.dao.KodJenisPihakBerkepentinganDAO;
import etanah.dao.PermohonanDAO;
import etanah.dao.PihakDAO;
import etanah.model.Hakmilik;
import etanah.model.HakmilikPermohonan;
import etanah.model.HakmilikPihakBerkepentingan;
import etanah.service.common.HakmilikPihakKepentinganService;
import etanah.model.InfoAudit;
import etanah.model.Pengguna;
import java.text.ParseException;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;
import etanah.model.Pihak;
import etanah.service.RegService;
import etanah.service.common.PihakService;
import etanah.view.etanahActionBeanContext;
import java.util.List;
import net.sourceforge.stripes.action.RedirectResolution;
import org.apache.commons.lang.StringUtils;
import etanah.model.Permohonan;
import net.sourceforge.stripes.action.StreamingResolution;
import org.apache.log4j.Logger;
import etanah.model.PermohonanPihak;
import etanah.service.common.PermohonanPihakService;
import etanah.view.stripes.common.MaklumatHakmilikPermohonanActionBean;
import etanah.view.stripes.nota.pembetulanPihakActionBean;
import etanah.model.PihakCawangan;
import etanah.dao.PihakCawanganDAO;
import etanah.dao.KodJenisPengenalanDAO;
import org.hibernate.Session;
import org.hibernate.Transaction;
import etanah.view.ListUtil;
import etanah.model.KodBangsa;
import etanah.model.KodJenisPihakBerkepentingan;
import java.text.SimpleDateFormat;
import etanah.model.KodCawangan;
import etanah.dao.KodCawanganDAO;
import etanah.dao.KodPenubuhanSyarikatDAO;
import etanah.model.AlamatSurat;
import etanah.model.HakmilikPihakBerkepentingan;
import etanah.model.KodPenubuhanSyarikat;
import etanah.service.common.HakmilikPihakKepentinganService;
import java.util.ArrayList;
import org.apache.commons.math.fraction.Fraction;
//import java.util.ArrayList;
//import org.apache.commons.math.fraction.Fraction;

/**
 *
 * @author md.nurfikri
 */
@UrlBinding("/pihakBerkepentingan")
public class PihakBerkepentinganActionBean extends AbleActionBean {

    private static final Logger logger = Logger.getLogger(PihakBerkepentinganActionBean.class);
    String idHakmilik;
    String idPBK;
    private Pihak pihak;
    HakmilikPihakBerkepentingan pihakBerkepentingan;
    Hakmilik hakmilik;
    @Inject
    RegService regService;
    @Inject
    KodPenubuhanSyarikatDAO kodPenubuhanSyarikatDAO;
    @Inject
    HakmilikPihakKepentinganService hakmilikPihakKepentinganService;
    @Inject
    PihakDAO pihakDAO;
    @Inject
    HakmilikDAO hakmilikDAO;
    @Inject
    PihakService pihakService;
    @Inject
    KodJenisPihakBerkepentinganDAO kodJenisPihakKepentinganDAO;
    @Inject
    HakmilikPihakBerkepentinganDAO pihakBerkepentinganDAO;
    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    PermohonanPihakService permohonanPihakService;
    @Inject
    PihakCawanganDAO pihakCawanganDAO;
    @Inject
    KodCawanganDAO kodCawanganDAO;
    @Inject
    ListUtil listUtil;
    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;
    @Inject
    HakmilikPihakKepentinganService hpkService;    
    @Inject
    KodJenisPengenalanDAO kodJenisPengenalanDAO;
    SimpleDateFormat sdf = new SimpleDateFormat("yyMMdd");
    private List<HakmilikPermohonan> hakmilikPermohonanList;
    private List<HakmilikPermohonan> hakmilikPermohonanListHakmilik;
    private List<PermohonanPihak> mohonPihakList;
    private List<Pihak> list;
    private List<KodBangsa> senaraiKodBangsa;
    private List<HakmilikPihakBerkepentingan> pihakKepentinganList;
    private List<PihakCawangan> cawanganList;
    public List<KodJenisPihakBerkepentingan> senaraiKodJenisPihakBerkepentingan;
    private List<KodJenisPihakBerkepentingan> senaraiKodPenerima;
    private List<KodPenubuhanSyarikat> senaraiSyktTubuh;
    private Permohonan p;
    private Permohonan pmhnn;
    private PihakCawangan pihakCawangan;
    private KodPenubuhanSyarikat syarikatTubuh;
    private String syktTubuh;
    private String idmohon;
    boolean cariFlag;
//    private String[] syerPembilang;
//    private String[] syerPenyebut;
    boolean showF;
    boolean tiadaDataFlag = false;
    boolean tambahCawFlag = false;
    boolean hideCari = true;
    boolean showAddCaw = true;
    String kodCawangan;
    String kodPengenalan;

    @DefaultHandler
    public Resolution showForm() {
        return new ForwardResolution("/WEB-INF/jsp/daftar/kemasukan_hakmilik_pihak.jsp").addParameter("tab", "true");
    }

    public Resolution paparTuanTanah() {
        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        p = permohonanDAO.findById(idPermohonan);
        List<HakmilikPermohonan> lhp = p.getSenaraiHakmilik();
        hakmilik = lhp.get(0).getHakmilik();
        pihakKepentinganList = hpkService.findHakmilikPihakActiveByHakmilik(hakmilik);
        return new JSP("daftar/paparan_tuan_tanah.jsp").addParameter("tab", "true");
    }

    public Resolution pihakKepentinganPopup() {
        idHakmilik = (String) getContext().getRequest().getParameter("idHakmilik");
        //first set showF flag to false to hide form when popup
        showF = false;
//        cariFlag = true;
        getContext().getRequest().setAttribute("idHakmilik", idHakmilik);
        return new JSP("daftar/kemasukan_hakmilik_pihak.jsp").addParameter("popup", "true").addParameter("idHakmilik", idHakmilik);
    }

    public Resolution pihakKepentinganPopup2() {
        idHakmilik = (String) getContext().getRequest().getParameter("idHakmilik");
        //first set showF flag to false to hide form when popup
        showF = false;
//        cariFlag = true;
        getContext().getRequest().setAttribute("idHakmilik", idHakmilik);
        return new JSP("daftar/kemasukan_hakmilik_pihak_bukan_pemilik.jsp").addParameter("popup", "true").addParameter("idHakmilik", idHakmilik);
    }

    public Resolution tambahCawanganPopup() {
        idHakmilik = (String) getContext().getRequest().getParameter("idHakmilik");
        String idPihak = getContext().getRequest().getParameter("idPihak");
        getContext().getRequest().setAttribute("idPihak", idPihak);
        cariFlag = true;
        pihak = new Pihak();
        pihak = pihakDAO.findById(Long.parseLong(idPihak));
        if (pihak.getSenaraiCawangan() != null) {
            cawanganList = pihak.getSenaraiCawangan();
        }
        //first set showF flag to false to hide form when popup
        showF = true;
//        cariFlag = true;
        return new JSP("daftar/kemasukan_hakmilik_pihak.jsp").addParameter("popup", "true").addParameter("idHakmilik", idHakmilik);
    }

    public Resolution maklumatPihakBekepentingan() {
        return new ForwardResolution("/WEB-INF/jsp/common/senarai_pihak_berkepentingan.jsp").addParameter("tab", "true");
    }

    public Resolution showFormEdit() {
        String idPihak = (String) getContext().getRequest().getParameter("idPihak");
        idHakmilik = (String) getContext().getRequest().getParameter("idHakmilik");
        idPBK = (String) getContext().getRequest().getParameter("idPBK");
        pihakBerkepentingan = pihakBerkepentinganDAO.findById(Long.parseLong(idPBK));

//        senaraiSyktTubuh = listUtil.getSenaraiKodPenubuhanSyarikat();
        senaraiSyktTubuh = kodPenubuhanSyarikatDAO.findAll();

//        getContext().getRequest().setAttribute("idHakmilik", idHakmilik);
        pihak = pihakDAO.findById(Long.parseLong(idPihak));
        if(pihak.getJenisPengenalan()!=null){
            kodPengenalan = pihak.getJenisPengenalan().getKod();
        }
        return new ForwardResolution("/WEB-INF/jsp/common/edit_pihak.jsp").addParameter("popup", "true").addParameter("idHakmilik", idHakmilik);
    }

    public Resolution tambahCawangan() {
        idHakmilik = (String) getContext().getRequest().getParameter("idHakmilik");
        logger.debug(":::tambahCawangan:::");
        Pengguna peng = (Pengguna) context.getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        String id = "";
        if (pihak.getNama() == null) {

            addSimpleError("Sila masukkan data yang bertanda *.");
            cariFlag = true;
            tiadaDataFlag = true;

        } else {
            if (!(pihakDAO.exists(pihak.getIdPihak()))) {
                Transaction tx = sessionProvider.get().getTransaction();
                tx.begin();

                try {
                    InfoAudit info = new InfoAudit();
                    info.setDimasukOleh(peng);
                    info.setTarikhMasuk(new java.util.Date());
                    pihak.setInfoAudit(info);
                    pihakService.saveOrUpdate(pihak);
                    logger.debug("idPihak : " + pihak.getIdPihak());
                    getContext().getRequest().setAttribute("idPihak", pihak.getIdPihak());

                } catch (Exception ex) {
                    tx.rollback();
                }
                tx.commit();
            } else {
                getContext().getRequest().setAttribute("idPihak", pihak.getIdPihak());
            }

            tambahCawFlag = true;
//            showF = true;

        }

        return new JSP("daftar/kemasukan_hakmilik_pihak.jsp").addParameter("popup", "true").addParameter("idHakmilik", idHakmilik);
    }

    public Resolution simpanCawangan() {
        idHakmilik = (String) getContext().getRequest().getParameter("idHakmilik");
        String idPihak = getContext().getRequest().getParameter("idPihak");
        getContext().getRequest().setAttribute("idPihak", idPihak);
        logger.debug("idPihak : " + idPihak);
//        Pihak pihak1 = (Pihak) getContext().getRequest().getSession().getAttribute("pihak");
        Pihak pihak1 = pihakDAO.findById(Long.parseLong(idPihak));
        Pengguna peng = (Pengguna) context.getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        InfoAudit info = new InfoAudit();
        info.setDimasukOleh(peng);
        info.setTarikhMasuk(new java.util.Date());
        if (pihakCawangan != null) {
            logger.debug("saving pihakCawangan : " + pihakCawangan.getNamaCawangan());
            pihakCawangan.setIbupejabat(pihak1);
            pihakCawangan.setInfoAudit(info);
            pihakService.saveOrUpdatePihakCawangan(pihakCawangan);
            logger.debug("saving pihakCawangan for pihak :" + pihak1.getIdPihak());
//            cawanganList = pihak1.getSenaraiCawangan();
//            pihak = pihak1;

            cariFlag = true;
            showF = true;
        } else {
            logger.debug("pihakCawangan NULL!!");
        }
        return new RedirectResolution(PihakBerkepentinganActionBean.class, "tambahCawanganPopup").addParameter("popup", "true").addParameter("idHakmilik", idHakmilik).addParameter("idPihak", pihak1.getIdPihak());
    }

    public Resolution simpanPihak() {
        idHakmilik = (String) getContext().getRequest().getAttribute("idHakmilik");
        syktTubuh = (String) getContext().getRequest().getAttribute("idPBK2");
        String penubuhanSyarikat = (String) getContext().getRequest().getParameter("pihak.penubuhanSyarikat.kod");
        Pengguna peng = (Pengguna) context.getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        InfoAudit info = peng.getInfoAudit();
        info.setDimasukOleh(info.getDimasukOleh());
        info.setTarikhMasuk(info.getTarikhMasuk());
        info.setDiKemaskiniOleh(peng);
        info.setTarikhKemaskini(new java.util.Date());
//        info.setTarikhMasuk(new java.util.Date());
        logger.debug("simpan pihak for hakmilik :" + idHakmilik);
        Pihak p = new Pihak();
        p = pihakDAO.findById(pihak.getIdPihak());
        p.setNama(pihak.getNama().toUpperCase());
        p.setNoPengenalan(pihak.getNoPengenalan());
        p.setNoPengenalanLain(pihak.getNoPengenalanLain());
        p.setBangsa(pihak.getBangsa());        
        if (StringUtils.isNotBlank(kodPengenalan)) {
            p.setJenisPengenalan(kodJenisPengenalanDAO.findById(kodPengenalan));
        }
        if (pihak.getWargaNegara() != null) {
            p.setWargaNegara(pihak.getWargaNegara());
        }
        p.setKodJantina(pihak.getKodJantina());

        if (pihak.getAlamat1() != null) {
            p.setAlamat1(pihak.getAlamat1().toUpperCase());
        } else {
            p.setAlamat1("");
        }
        if (pihak.getAlamat2() != null) {
            p.setAlamat2(pihak.getAlamat2().toUpperCase());
        } else {
            p.setAlamat2("");
        }
        if (pihak.getAlamat3() != null) {
            p.setAlamat3(pihak.getAlamat3().toUpperCase());
        } else {
            p.setAlamat3("");
        }
        if (pihak.getAlamat4() != null) {
            p.setAlamat4(pihak.getAlamat4().toUpperCase());
        } else {
            p.setAlamat4("");
        }
        p.setPoskod(pihak.getPoskod());
        if (pihak.getNegeri() != null) {
            p.setNegeri(pihak.getNegeri());
        }

        if (pihak.getSuratAlamat1() != null) {
            p.setSuratAlamat1(pihak.getSuratAlamat1().toUpperCase());
        } else {
            p.setSuratAlamat1("");
        }
        if (pihak.getSuratAlamat2() != null) {
            p.setSuratAlamat2(pihak.getSuratAlamat2().toUpperCase());
        } else {
            p.setSuratAlamat2("");
        }
        if (pihak.getSuratAlamat3() != null) {
            p.setSuratAlamat3(pihak.getSuratAlamat3().toUpperCase());
        }
        if (pihak.getSuratAlamat4() != null) {
            p.setSuratAlamat4(pihak.getSuratAlamat4().toUpperCase());
        } else {
            p.setSuratAlamat4("");
        }
        p.setSuratPoskod(pihak.getSuratPoskod());
        if (pihak.getSuratNegeri() != null) {
            p.setSuratNegeri(pihak.getSuratNegeri());
        }
        p.setInfoAudit(info);
        if (p != null) {
            regService.updatePihak(p);
        }

        logger.info("--> id pbk " + idPBK);
        HakmilikPihakBerkepentingan pbk = pihakBerkepentinganDAO.findById(Long.parseLong(idPBK));
        if (pbk != null) {
            pbk.setNama(p.getNama().toUpperCase());
            pbk.setNoPengenalan(p.getNoPengenalan());
            pbk.setNoPengenalanLama(p.getNoPengenalanLain());
            pbk.setWargaNegara(p.getWargaNegara());
            pbk.setJenisPengenalan(kodJenisPengenalanDAO.findById(kodPengenalan));            
            if (pihakBerkepentingan.getJenis() != null) {
                pbk.setJenis(pihakBerkepentingan.getJenis());
            }
            if (pihak.getAlamat1() != null) {
                pbk.setAlamat1(p.getAlamat1().toUpperCase());
            } else {
                pbk.setAlamat1("");
            }

            if (pihak.getAlamat2() != null) {
                pbk.setAlamat2(p.getAlamat2().toUpperCase());
            } else {
                pbk.setAlamat2("");
            }

            if (pihak.getAlamat3() != null) {
                pbk.setAlamat3(p.getAlamat3().toUpperCase());
            } else {
                pbk.setAlamat3("");
            }

            if (pihak.getAlamat4() != null) {
                pbk.setAlamat4(p.getAlamat4().toUpperCase());
            } else {
                pbk.setAlamat4("");
            }

            pbk.setPoskod(p.getPoskod());
            pbk.setNegeri(p.getNegeri());

            AlamatSurat alamatSurat = new AlamatSurat();
            if (pihak.getSuratAlamat1() != null) {
                alamatSurat.setAlamatSurat1(p.getSuratAlamat1().toUpperCase());
            } else {
                alamatSurat.setAlamatSurat1("");
            }
            if (pihak.getSuratAlamat2() != null) {
                alamatSurat.setAlamatSurat2(p.getSuratAlamat2().toUpperCase());
            } else {
                alamatSurat.setAlamatSurat2("");
            }
            if (pihak.getSuratAlamat3() != null) {
                alamatSurat.setAlamatSurat3(p.getSuratAlamat3().toUpperCase());
            } else {
                alamatSurat.setAlamatSurat3("");
            }
            if (pihak.getSuratAlamat4() != null) {
                alamatSurat.setAlamatSurat4(p.getSuratAlamat4().toUpperCase());
            } else {
                alamatSurat.setAlamatSurat4("");
            }

            alamatSurat.setPoskodSurat(p.getSuratPoskod());
            alamatSurat.setNegeriSurat(p.getSuratNegeri());
            pbk.setAlamatSurat(alamatSurat);
            pbk.setPenubuhanSyarikat(p.getPenubuhanSyarikat());
//            logger.info("syarikat tubuh :" + pihak.getPenubuhanSyarikat().getNama());
            pbk.setInfoAudit(info);
            regService.simpanHakmilikPihak(pbk);
        }
        addSimpleMessage("Pihak Telah Berjaya diKemaskini");
        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        pmhnn = permohonanDAO.findById(idPermohonan);

        if (pmhnn.getKodUrusan().getKod().equalsIgnoreCase("BETUL")) {
            return new RedirectResolution(pembetulanPihakActionBean.class, "showForm").addParameter("tab", "true");
        } else {
            return new RedirectResolution(MaklumatHakmilikPermohonanActionBean.class, "showForm2").addParameter("tab", "true").addParameter("idHakmilik", idHakmilik);
        }

    }

    public Resolution simpanPihakBerkelompok() {
        idHakmilik = (String) getContext().getRequest().getAttribute("idHakmilik");
        syktTubuh = (String) getContext().getRequest().getAttribute("idPBK2");
        idmohon = p.getIdPermohonan();
        String penubuhanSyarikat = (String) getContext().getRequest().getParameter("pihak.penubuhanSyarikat.kod");
        Pengguna peng = (Pengguna) context.getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        InfoAudit info = peng.getInfoAudit();
        info.setDimasukOleh(info.getDimasukOleh());
        info.setTarikhMasuk(info.getTarikhMasuk());
        info.setDiKemaskiniOleh(peng);
        info.setTarikhKemaskini(new java.util.Date());

        if (!idHakmilik.equals(null)) {
            hakmilikPermohonanList = regService.searchMohonHakmilikByIdMohon(idmohon);
            for (HakmilikPermohonan hpHMbaru : hakmilikPermohonanList) {
                idHakmilik = hpHMbaru.getHakmilik().getIdHakmilik();
                logger.debug("simpan pihak for hakmilik :" + idHakmilik);
                Pihak p = new Pihak();
                p = pihakDAO.findById(pihak.getIdPihak());
                p.setNama(pihak.getNama().toUpperCase());
                p.setNoPengenalan(pihak.getNoPengenalan());
                p.setBangsa(pihak.getBangsa());
                if (pihak.getWargaNegara() != null) {
                    p.setWargaNegara(pihak.getWargaNegara());
                }
                p.setKodJantina(pihak.getKodJantina());

                if (pihak.getAlamat1() != null) {
                    p.setAlamat1(pihak.getAlamat1().toUpperCase());
                } else {
                    p.setAlamat1("");
                }
                if (pihak.getAlamat2() != null) {
                    p.setAlamat2(pihak.getAlamat2().toUpperCase());
                } else {
                    p.setAlamat2("");
                }
                if (pihak.getAlamat3() != null) {
                    p.setAlamat3(pihak.getAlamat3().toUpperCase());
                } else {
                    p.setAlamat3("");
                }
                if (pihak.getAlamat4() != null) {
                    p.setAlamat4(pihak.getAlamat4().toUpperCase());
                } else {
                    p.setAlamat4("");
                }
                p.setPoskod(pihak.getPoskod());
                if (pihak.getNegeri() != null) {
                    p.setNegeri(pihak.getNegeri());
                }

                if (pihak.getSuratAlamat1() != null) {
                    p.setSuratAlamat1(pihak.getSuratAlamat1().toUpperCase());
                } else {
                    p.setSuratAlamat1("");
                }
                if (pihak.getSuratAlamat2() != null) {
                    p.setSuratAlamat2(pihak.getSuratAlamat2().toUpperCase());
                } else {
                    p.setSuratAlamat2("");
                }
                if (pihak.getSuratAlamat3() != null) {
                    p.setSuratAlamat3(pihak.getSuratAlamat3().toUpperCase());
                }
                if (pihak.getSuratAlamat4() != null) {
                    p.setSuratAlamat4(pihak.getSuratAlamat4().toUpperCase());
                } else {
                    p.setSuratAlamat4("");
                }
                p.setSuratPoskod(pihak.getSuratPoskod());
                if (pihak.getSuratNegeri() != null) {
                    p.setSuratNegeri(pihak.getSuratNegeri());
                }
                p.setInfoAudit(info);
                if (p != null) {
                    regService.updatePihak(p);
                }

                logger.info("--> id pbk " + idPBK);
                HakmilikPihakBerkepentingan pbk = pihakBerkepentinganDAO.findById(Long.parseLong(idPBK));
                List<HakmilikPihakBerkepentingan> HakmilikBerkepentinganList = hakmilikPihakKepentinganService.findHakmilikPihakBerkepentinganByIDHakmilikGeran(idHakmilik);
                for (HakmilikPihakBerkepentingan hp : HakmilikBerkepentinganList) {
                    hp.setNama(pihak.getNama().toUpperCase());
                    hp.setNoPengenalan(pihak.getNoPengenalan());
                    hp.setWargaNegara(pihak.getWargaNegara());
                    if (pihak.getAlamat1() != null) {
                        hp.setAlamat1(pihak.getAlamat1().toUpperCase());
                    } else {
                        hp.setAlamat1("");
                    }

                    if (pihak.getAlamat2() != null) {
                        hp.setAlamat2(pihak.getAlamat2().toUpperCase());
                    } else {
                        hp.setAlamat2("");
                    }

                    if (pihak.getAlamat3() != null) {
                        hp.setAlamat3(pihak.getAlamat3().toUpperCase());
                    } else {
                        hp.setAlamat3("");
                    }

                    if (pihak.getAlamat4() != null) {
                        hp.setAlamat4(pihak.getAlamat4().toUpperCase());
                    } else {
                        hp.setAlamat4("");
                    }

                    hp.setPoskod(pihak.getPoskod());
                    hp.setNegeri(pihak.getNegeri());

                    AlamatSurat alamatSurat = new AlamatSurat();
                    if (pihak.getSuratAlamat1() != null) {
                        alamatSurat.setAlamatSurat1(pihak.getSuratAlamat1().toUpperCase());
                    } else {
                        alamatSurat.setAlamatSurat1("");
                    }
                    if (pihak.getSuratAlamat2() != null) {
                        alamatSurat.setAlamatSurat2(pihak.getSuratAlamat2().toUpperCase());
                    } else {
                        alamatSurat.setAlamatSurat2("");
                    }
                    if (pihak.getSuratAlamat3() != null) {
                        alamatSurat.setAlamatSurat3(pihak.getSuratAlamat3().toUpperCase());
                    } else {
                        alamatSurat.setAlamatSurat3("");
                    }
                    if (pihak.getSuratAlamat4() != null) {
                        alamatSurat.setAlamatSurat4(pihak.getSuratAlamat4().toUpperCase());
                    } else {
                        alamatSurat.setAlamatSurat4("");
                    }

                    alamatSurat.setPoskodSurat(pihak.getSuratPoskod());
                    alamatSurat.setNegeriSurat(pihak.getSuratNegeri());
                    hp.setAlamatSurat(alamatSurat);
                    hp.setPenubuhanSyarikat(pihak.getPenubuhanSyarikat());
                    hp.setInfoAudit(info);
                    regService.simpanHakmilikPihak(hp);

                }
            }
        }

        addSimpleMessage(
                "Pihak Telah Berjaya diKemaskini");
        return new RedirectResolution(MaklumatHakmilikPermohonanActionBean.class, "showForm2").addParameter(
                "tab", "true").addParameter("idHakmilik", idHakmilik);

    }

    public Resolution updateSyerHakmilikPihak() {
        String idPihak = getContext().getRequest().getParameter("idpihak");
        String s1 = getContext().getRequest().getParameter("syer1");//pembilang
        String s2 = getContext().getRequest().getParameter("syer2");//penyebut
//        LOG.debug("s1:" + s1);
//        LOG.debug("s2:" + s2);
//        LOG.debug("idpihak:" + idPihak);
        if (StringUtils.isNotBlank(idPihak) && StringUtils.isNotBlank(s1) && StringUtils.isNotBlank(s2)) {
//            LOG.debug(idPihak);
//            LOG.debug(s1);
//            LOG.debug(s2);
            //PermohonanPihak pp = permohonanPihakService.findById(idPihak);
            HakmilikPihakBerkepentingan hpb = hakmilikPihakKepentinganService.findById(idPihak);
            hpb.setJumlahPembilang(Integer.parseInt(s1));
            hpb.setJumlahPenyebut(Integer.parseInt(s2));
            hpb.setSyerPembilang(Integer.parseInt(s1));
            hpb.setSyerPenyebut(Integer.parseInt(s2));            
            //permohonanPihakService.saveOrUpdate(pp);
            hakmilikPihakKepentinganService.save(hpb);
        }
        return new StreamingResolution("text/plain", "1");
    }

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {
//        Pengguna peng = (Pengguna) context.getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        idHakmilik = getContext().getRequest().getParameter("idHakmilik");
        getContext().getRequest().setAttribute("idHakmilik", idHakmilik);

        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        if (idPermohonan != null) {
            p = permohonanDAO.findById(idPermohonan);
            kodCawangan = p.getCawangan().getKod();
            p.getKodUrusan().getKod();
            hakmilikPermohonanList = p.getSenaraiHakmilik();
            pihakKepentinganList = regService.searchPihakBerKepentingan(idHakmilik);
            //pihakKepentinganList = hpkService.findHakmilikPihakActiveAndWarisCucuCicitByHakmilik(hakmilikDAO.findById(idHakmilik));
//            mohonPihakList = permohonanPihakService.getSenaraiPmohonPihak(idPermohonan);
            mohonPihakList = p.getSenaraiPihak();

//            syerPembilang = new String[pihakKepentinganList.size()];
//            syerPenyebut = new String[pihakKepentinganList.size()];
//            size = hakmilikPermohonanList.size();
        }
    }

//    public Resolution agihSamaRata() {
//
//        //TODO : multiple hakmilik
//        Hakmilik hm = p.getSenaraiHakmilik().get(0).getHakmilik();
//        Fraction sumAllPemohon = Fraction.ZERO;
//        Fraction samaRata = Fraction.ZERO;
//       //List<PermohonanPihak> mohonPihak = new ArrayList<PermohonanPihak>();
//        for (PermohonanPihak permohonanPihak : mohonPihakList) {
//            HakmilikPihakBerkepentingan hmk = hakmilikPihakKepentinganService.findHakmilikPihakByPihak(permohonanPihak.getPihak().getIdPihak(), hm);
//            if (hmk != null) {
//                sumAllPemohon = sumAllPemohon.add(new Fraction(hmk.getSyerPembilang(), hmk.getSyerPenyebut()));
//            }
//        }
//
//        if (mohonPihakList != null && mohonPihakList.size() > 0) {
//            samaRata = sumAllPemohon.divide(mohonPihakList.size());
//
//            for (PermohonanPihak pp : mohonPihakList) {
//                pp.setSyerPembilang(samaRata.getNumerator());
//                pp.setSyerPenyebut(samaRata.getDenominator());
//            }
//        }
//
//        permohonanPihakService.saveOrUpdate(mohonPihakList);
//
//        return new RedirectResolution(PihakBerkepentinganActionBean.class, "getSenaraiHakmilikKepentingan");
//    }
    public Resolution simpanPihakKepentingan() {
        String result = "1";
        idHakmilik = getContext().getRequest().getParameter("idHakmilik");
        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        pmhnn = permohonanDAO.findById(idPermohonan);
        logger.debug("Adding idHakmilik :" + idHakmilik);
//        Fraction f = new Fraction(Integer.parseInt(syerA), Integer.parseInt(syerB));
        Pengguna peng = (Pengguna) context.getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        InfoAudit info = peng.getInfoAudit();

        int pbsize = 0;
        if (idHakmilik != null) {
            hakmilik = hakmilikDAO.findById(idHakmilik);
        }

//        PermohonanPihak pp = new PermohonanPihak();
        HakmilikPihakBerkepentingan hpb = new HakmilikPihakBerkepentingan();

        if (pihak.getNama() != null && pihak.getNoPengenalan() != null) {

            String idPihak = getContext().getRequest().getParameter("idPihak");
            logger.debug("idPihak : " + idPihak);
            Pihak pihakb = new Pihak();
            if (StringUtils.isNotEmpty(idPihak)) {
                pihakb = pihakDAO.findById(pihak.getIdPihak());
                info.setDimasukOleh(info.getDimasukOleh());
                info.setTarikhMasuk(info.getTarikhMasuk());
                info.setDiKemaskiniOleh(peng);
                info.setTarikhKemaskini(new java.util.Date());
                cawanganList = pihakb.getSenaraiCawangan();
                if (cawanganList.size() > 0) {
                    PihakCawangan pc = cawanganList.get(0);
                    hpb.setPihakCawangan(pc);
                }
            } else {
                info.setDimasukOleh(peng);
                info.setTarikhMasuk(new java.util.Date());
            }

            pihakb.setNama(pihak.getNama().toUpperCase());
            pihakb.setBangsa(pihak.getBangsa());
            pihakb.setJenisPengenalan(pihak.getJenisPengenalan());
            
            if (pihak.getJenisPengenalan() != null) {
                if (pihak.getJenisPengenalan().getKod().equalsIgnoreCase("X")) {
                    pihakb.setNoPengenalan(null);
                } else if (!pihak.getJenisPengenalan().getKod().equalsIgnoreCase("X")) {
                    pihakb.setNoPengenalan(pihak.getNoPengenalan());
                }
            }
            
            else
                pihakb.setNoPengenalan(null);
            
            pihakb.setWargaNegara(pihak.getWargaNegara());

            if (pihak.getAlamat1() != null) {
                pihakb.setAlamat1(pihak.getAlamat1().toUpperCase());
            }
            if (pihak.getAlamat2() != null) {
                pihakb.setAlamat2(pihak.getAlamat2().toUpperCase());
            }
            if (pihak.getAlamat3() != null) {
                pihakb.setAlamat3(pihak.getAlamat3().toUpperCase());
            }
            if (pihak.getAlamat4() != null) {
                pihakb.setAlamat4(pihak.getAlamat4().toUpperCase());
            }
            if (pihak.getSuratAlamat1() != null) {
                pihakb.setSuratAlamat1(pihak.getSuratAlamat1().toUpperCase());
            }
            if (pihak.getSuratAlamat2() != null) {
                pihakb.setSuratAlamat2(pihak.getSuratAlamat2().toUpperCase());
            }
            if (pihak.getSuratAlamat3() != null) {
                pihakb.setSuratAlamat3(pihak.getSuratAlamat3().toUpperCase());
            }
            if (pihak.getSuratAlamat4() != null) {
                pihakb.setSuratAlamat4(pihak.getSuratAlamat4().toUpperCase());
            }
            pihakb.setPoskod(pihak.getPoskod());
            pihakb.setNegeri(pihak.getNegeri());
            pihakb.setSuratPoskod(pihak.getSuratPoskod());
            pihakb.setSuratNegeri(pihakb.getSuratNegeri());
            pihakb.setInfoAudit(info);
            logger.debug("pihak alamat1 :" + pihak.getAlamat1());
            logger.debug("pihak alamat2 :" + pihak.getAlamat2());
            logger.debug("pihak alamat3 :" + pihak.getAlamat3());
            logger.debug("pihak alamat4 :" + pihak.getAlamat4());
            KodCawangan kc = new KodCawangan();
            kc.setKod(kodCawangan);
//            pp.setCawangan(kc);
//            pp.setPermohonan(p);
//            pp.setJenis(pihakBerkepentingan.getJenis());
//            pp.setSyerPembilang(0);
//            pp.setSyerPenyebut(0);
//            pp.setInfoAudit(info);
//            pp.setPihak(pihakb);
//            if (hakmilik != null) {
//                pp.setHakmilik(hakmilik);
//            }
            hpb.setCawangan(hakmilik.getCawangan());
            hpb.setNama(pihak.getNama());
            hpb.setJenisPengenalan(pihak.getJenisPengenalan());           
            if (pihak.getJenisPengenalan() != null) {
                if(pihak.getJenisPengenalan().getKod().equalsIgnoreCase("X")){
                    hpb.setNoPengenalan(null);
                } else if(!pihak.getJenisPengenalan().getKod().equalsIgnoreCase("X")) {
                    hpb.setNoPengenalan(pihak.getNoPengenalan());
                }
            }
            else
            hpb.setNoPengenalan(null);
            hpb.setAlamat1(pihak.getAlamat1());
            hpb.setAlamat2(pihak.getAlamat2());
            hpb.setAlamat3(pihak.getAlamat3());
            hpb.setAlamat4(pihak.getAlamat4());
            hpb.setPoskod(pihak.getPoskod());
            hpb.setNegeri(pihak.getNegeri());
            hpb.setJenis(pihakBerkepentingan.getJenis());
            hpb.setWargaNegara(pihak.getWargaNegara());
            hpb.setJumlahPembilang(1);
            hpb.setJumlahPenyebut(1);
            hpb.setSyerPembilang(1);
            hpb.setSyerPenyebut(1);
            hpb.setInfoAudit(info);
            hpb.setPihak(pihakb);
            hpb.setKaveatAktif('T');
            hpb.setAktif('Y');
            if (hakmilik != null) {
                hpb.setHakmilik(hakmilik);
                //new added : request by safina 05092014
                if (pihak.getBangsa() != null && pihak.getBangsa().getKod().equals("KP")) {
                    hakmilik.setMilikPersekutuan('Y');
                    Transaction tx = sessionProvider.get().getTransaction();
                    tx.begin();
                    hakmilikDAO.save(hakmilik);
                    tx.commit();

                }
            }
            logger.debug("trying to save pihak:" + pihak.getIdPihak() + " to hakmilik :" + idHakmilik);
//            pihakDAO.saveOrUpdate(pihak);
            //pihakService.saveOrUpdatePihakKepentinganPihak(pihakb, pp);
            pihakService.saveOrUpdateHakmilikPihakKepentinganPihak(pihakb, hpb);

            if (idHakmilik != null) {

                List<HakmilikPihakBerkepentingan> lpb = regService.searchPihakBerKepentinganPemilik(idHakmilik);
                List<HakmilikPihakBerkepentingan> senarai = new ArrayList();
                pbsize = lpb.size();

                Fraction sumAllPemohon = Fraction.ONE;
                Fraction samaRata = Fraction.ZERO;

                if (lpb != null && lpb.size() > 0) {
                    logger.debug("size pihak:" + lpb.size());
                    samaRata = sumAllPemohon.divide(lpb.size());

                    for (HakmilikPihakBerkepentingan hpbaru : lpb) {
                        hpbaru.setJumlahPembilang(samaRata.getNumerator());
                        hpbaru.setJumlahPenyebut(samaRata.getDenominator());
                        hpbaru.setSyerPembilang(samaRata.getNumerator());
                        hpbaru.setSyerPenyebut(samaRata.getDenominator());
                        senarai.add(hpbaru);
                    }
                    hakmilikPihakKepentinganService.saveList(senarai);
                }
            }

            //rehydrate();
            getContext().getRequest().setAttribute("idHakmilik", idHakmilik);
            addSimpleMessage("Kemasukan Data Berjaya");
            //return new RedirectResolution(KemasukanPerincianHakmilikActionBean.class).addParameter("tab", "true").addParameter("idHakmilik", idHakmilik);
            //return new RedirectResolution(MaklumatHakmilikPermohonanActionBean.class, "showForm2").addParameter("tab", "true").addParameter("idHakmilik", idHakmilik);
            if (pmhnn.getKodUrusan().getKod().equalsIgnoreCase("BETUL")) {
                return new JSP("daftar/kemasukan_pihak_baru_pembetulan.jsp").addParameter("tab", "true");

            } else if (!pmhnn.getKodUrusan().getKod().equalsIgnoreCase("BETUL")) {
                return new RedirectResolution(MaklumatHakmilikPermohonanActionBean.class
                ).addParameter("showForm2", "").addParameter("tab", "true").addParameter("idHakmilik", idHakmilik);
            }
            //return new JSP("common/paparan_maklumat_hakmilik_permohonan_terperinci.jsp").addParameter("tab", "true").addParameter("idHakmilik", idHakmilik);
            //return new ForwardResolution("/WEB-INF/jsp/common/paparan_maklumat_hakmilik_permohonan_terperinci.jsp").addParameter("idHakmilik", hakmilik.getIdHakmilik());
            //return new JSP("common/paparan_maklumat_hakmilik_permohonan_terperinci.jsp").addParameter("tab", "true").addParameter("idHakmilik", hakmilik.getIdHakmilik());
        } else {
            result = "0";
            addSimpleError("Sila masukkan Jenis Pihak Berkepentingan");
        }
        return new StreamingResolution("text/plain", result);
    }

    public Resolution cariPihak() throws ParseException {
        List<Pihak> listPihak;

        logger.debug("jenis pengenalan:" + pihak.getJenisPengenalan().getKod());
        if (pihak.getJenisPengenalan().getKod().equals("0") || pihak.getJenisPengenalan().getKod().equals("X")) {
            showF = true;
            cariFlag = false;
            tiadaDataFlag = true;
            hideCari = false;
            //listPihak.clear();
            senaraiKodBangsa = listUtil.getSenaraiKodBangsaByJenisPengenalan(pihak.getJenisPengenalan().getKod());

            senaraiKodPenerima = listUtil.getSenaraiKodPihakPenerima(p.getKodUrusan().getJabatanNama(), p.getKodUrusan().getNama());
            addSimpleMessage("Pihak Belum Pernah Memohon.Sila Masukkan Maklumat Pihak");
        } else if (!pihak.getJenisPengenalan().getKod().equals("0") || !pihak.getJenisPengenalan().getKod().equals("X")) {
            if (pihak != null) {
                if (pihak.getJenisPengenalan().getKod() != null) {
                    //if pihak = persendirian hide cawangan
                    if (pihak.getJenisPengenalan().getKod().equals("B") || pihak.getJenisPengenalan().getKod().equals("L") || pihak.getJenisPengenalan().getKod().equals("P") || pihak.getJenisPengenalan().getKod().equals("T") || pihak.getJenisPengenalan().getKod().equals("I")) {
                        showAddCaw = false;

                    }
                    if (pihak.getJenisPengenalan().getKod().equals("B")) {

                        if (pihak.getNoPengenalan() == null) {
                            addSimpleError("Sila Masukkan No Pengenalan");
                            return new JSP("daftar/kemasukan_hakmilik_pihak.jsp").addParameter("popup", "true");
                        }

                        //Check for pihak over 18 to proceed
                        //if (regService.checkAge(pihak.getNoPengenalan().substring(0, 6))) {
                        listPihak = pihakDAO.findByExample(pihak);
                        /*flag to hide carian form*/
                        showF = true;
                        if ((!listPihak.isEmpty())) {
                            pihak = listPihak.get(0);
                            cariFlag = true;
                            cawanganList = pihak.getSenaraiCawangan();
                            tiadaDataFlag = false;
                            hideCari = false;
                            getContext().getRequest().setAttribute("idPihak", pihak.getIdPihak());
                        } else {
                            cariFlag = false;
                            tiadaDataFlag = true;
                            hideCari = false;
                            //listPihak.clear();
                            if (pihak.getNoPengenalan() != null
                                    && !pihak.getNoPengenalan().equals("") && pihak.getNoPengenalan().length() == 12) {
                                String lastVal = pihak.getNoPengenalan().substring(11);
                                if (lastVal != null && !"".equals(lastVal)) {
                                    int val = Integer.valueOf(lastVal);
                                    int val2 = val % 2;
                                    if (val2 == 0) {
                                        pihak.setKodJantina("2");
                                    } else {
                                        pihak.setKodJantina("1");
                                    }

                                }

                            }
                            addSimpleMessage("Pihak Belum Pernah Memohon.Sila Masukkan Maklumat Pihak");
                        }

                        //} else {
                        //addSimpleError("Pemohon Tidak Cukup Umur");
                        // }
                    } else {
                        listPihak = pihakDAO.findByExample(pihak);
                        /*flag to hide carian form*/
                        showF = true;
                        if ((!listPihak.isEmpty())) {
                            pihak = listPihak.get(0);
                            cariFlag = true;
                            cawanganList = pihak.getSenaraiCawangan();
                            tiadaDataFlag = false;
                            hideCari = false;
                            getContext().getRequest().setAttribute("idPihak", pihak.getIdPihak());
                        } else {
                            cariFlag = false;
                            tiadaDataFlag = true;
                            hideCari = false;
                            //listPihak.clear();
                            addSimpleMessage("Pihak Belum Pernah Memohon.Sila Masukkan Maklumat Pihak");
                        }

                    }

                    if (pihak.getJenisPengenalan() != null) {
                        senaraiKodBangsa = listUtil.getSenaraiKodBangsaByJenisPengenalan(pihak.getJenisPengenalan().getKod());
                    }

                    senaraiKodPenerima = listUtil.getSenaraiKodPihakPenerima(p.getKodUrusan().getJabatanNama(), p.getKodUrusan().getNama());
                } else if (pihak.getJenisPengenalan().getKod() == null) {
                    addSimpleError("Sila Masukkan Jenis Pengenalan");
                } //        if (pihak.getNoPengenalan() == null) {
                //          addSimpleError("Sila Masukkan No Pengenalan");
                //        }
            } else {
                addSimpleError("Sila Masukkan Jenis & No Pengenalan");
            }
        }
        return new JSP("daftar/kemasukan_hakmilik_pihak.jsp").addParameter("popup", "true");
    }

    public Resolution deletePihakKepentingan() {
        String idHPB = getContext().getRequest().getParameter("id_hP");
        String idHakmilik = getContext().getRequest().getParameter("idHakmilik");
        HakmilikPihakBerkepentingan pB = hakmilikPihakKepentinganService.findById(idHPB);
        if (pB != null) {
            Pengguna peng = (Pengguna) context.getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
            InfoAudit info = peng.getInfoAudit();
            info.setDiKemaskiniOleh(peng);
            info.setTarikhKemaskini(new java.util.Date());
            pB.setAktif('T');
            regService.simpanHakmilikPihak(pB);
        }

//        if (StringUtils.isNotBlank(idHakmilik)) {
//            List<HakmilikPihakBerkepentingan> lpb = regService.searchPihakBerKepentinganPemilik(idHakmilik);
//            List<HakmilikPihakBerkepentingan> senarai = new ArrayList();
//
//            int pbsize = 0;
//            pbsize = lpb.size();
//
//            Fraction sumAllPemohon = Fraction.ONE;
//            Fraction samaRata = Fraction.ZERO;
//
//            if (lpb != null && lpb.size() > 0) {
//                logger.debug("size pihak:" + lpb.size());
//                samaRata = sumAllPemohon.divide(lpb.size());
//
//                for (HakmilikPihakBerkepentingan hpbaru : lpb) {
//                    hpbaru.setSyerPembilang(samaRata.getNumerator());
//                    hpbaru.setSyerPenyebut(samaRata.getDenominator());
//                    senarai.add(hpbaru);
//                }
//                hakmilikPihakKepentinganService.saveList(senarai);
//            }
//        }
        //rehydrate();
        addSimpleMessage("Data Telah Berjaya diHapuskan");

        return new RedirectResolution(MaklumatHakmilikPermohonanActionBean.class
        ).addParameter("showForm2", "").addParameter("tab", "true").addParameter("idHakmilik", idHakmilik);
        //return new JSP("common/paparan_maklumat_hakmilik_permohonan_terperinci.jsp").addParameter("tab", "true").addParameter("idHakmilik", idHakmilik);
    }

//    public String[] getSyerPembilang() {
//        return syerPembilang;
//    }
//
//    public void setSyerPembilang(String[] syerPembilang) {
//        this.syerPembilang = syerPembilang;
//    }
//
//    public String[] getSyerPenyebut() {
//        return syerPenyebut;
//    }
//
//    public void setSyerPenyebut(String[] syerPenyebut) {
//        this.syerPenyebut = syerPenyebut;
//    }
    public boolean isShowAddCaw() {
        return showAddCaw;
    }

    public void setShowAddCaw(boolean showAddCaw) {
        this.showAddCaw = showAddCaw;
    }

    public String getKodCawangan() {
        return kodCawangan;
    }

    public void setKodCawangan(String kodCawangan) {
        this.kodCawangan = kodCawangan;
    }

    public Permohonan getP() {
        return p;
    }

    public void setP(Permohonan p) {
        this.p = p;
    }

    public List<KodJenisPihakBerkepentingan> getSenaraiKodJenisPihakBerkepentingan() {
        return listUtil.getSenaraiKodJenisPihakBerkepentinganBySerah(p.getKodUrusan().getKodPerserahan().getKod());
    }

    public PihakCawangan getPihakCawangan() {
        return pihakCawangan;
    }

    public void setPihakCawangan(PihakCawangan pihakCawangan) {
        this.pihakCawangan = pihakCawangan;
    }

    public boolean isHideCari() {
        return hideCari;
    }

    public void setHideCari(boolean hideCari) {
        this.hideCari = hideCari;
    }

    public boolean isTambahCawFlag() {
        return tambahCawFlag;
    }

    public void setTambahCawFlag(boolean tambahCawFlag) {
        this.tambahCawFlag = tambahCawFlag;
    }

    public boolean isTiadaDataFlag() {
        return tiadaDataFlag;
    }

    public void setTiadaDataFlag(boolean tiadaDataFlag) {
        this.tiadaDataFlag = tiadaDataFlag;
    }

    public List<PermohonanPihak> getMohonPihakList() {
        return mohonPihakList;
    }

    public List<PihakCawangan> getCawanganList() {
        return cawanganList;
    }

    public void setCawanganList(List<PihakCawangan> cawanganList) {
        this.cawanganList = cawanganList;
    }

    public void setMohonPihakList(List<PermohonanPihak> mohonPihakList) {
        this.mohonPihakList = mohonPihakList;
    }

    public boolean isShowF() {
        return showF;
    }

    public void setShowF(boolean showF) {
        this.showF = showF;
    }

    public boolean isCariFlag() {
        return cariFlag;
    }

    public void setCariFlag(boolean cariFlag) {
        this.cariFlag = cariFlag;
    }

    public List<Pihak> getList() {
        return list;
    }

    public void setList(List<Pihak> list) {
        this.list = list;
    }

    public List<HakmilikPihakBerkepentingan> getPihakKepentinganList() {
        return pihakKepentinganList;
    }

    public void setPihakKepentinganList(List<HakmilikPihakBerkepentingan> pihakKepentinganList) {
        this.pihakKepentinganList = pihakKepentinganList;
    }

    public List<HakmilikPermohonan> getHakmilikPermohonanList() {
        return hakmilikPermohonanList;
    }

    public void setHakmilikPermohonanList(List<HakmilikPermohonan> hakmilikPermohonanList) {
        this.hakmilikPermohonanList = hakmilikPermohonanList;
    }

    public String getIdHakmilik() {
        return idHakmilik;
    }

    public void setIdHakmilik(String idHakmilik) {
        this.idHakmilik = idHakmilik;
    }

    public Hakmilik getHakmilik() {
        return hakmilik;
    }

    public void setHakmilik(Hakmilik hakmilik) {
        this.hakmilik = hakmilik;
    }

    public HakmilikPihakBerkepentingan getPihakBerkepentingan() {
        return pihakBerkepentingan;
    }

    public void setPihakBerkepentingan(HakmilikPihakBerkepentingan pihakBerkepentingan) {
        this.pihakBerkepentingan = pihakBerkepentingan;
    }

    public List<KodJenisPihakBerkepentingan> getSenaraiKodPenerima() {
        return senaraiKodPenerima;
    }

    public void setSenaraiKodPenerima(List<KodJenisPihakBerkepentingan> senaraiKodPenerima) {
        this.senaraiKodPenerima = senaraiKodPenerima;
    }

    public Pihak getPihak() {
        return pihak;
    }

    public void setPihak(Pihak pihak) {
        this.pihak = pihak;
    }

    public List<KodBangsa> getSenaraiKodBangsa() {
        return senaraiKodBangsa;
    }

    public void setSenaraiKodBangsa(List<KodBangsa> senaraiKodBangsa) {
        this.senaraiKodBangsa = senaraiKodBangsa;
    }

    public String getIdPBK() {
        return idPBK;
    }

    public void setIdPBK(String idPBK) {
        this.idPBK = idPBK;
    }

//    @Before(stages = {LifecycleStage.BindingAndValidation})
//    public void rehydrate() {
//
//         String idPermohonan = (String)getContext().getRequest().getSession().getAttribute("idPermohonan");
//
//
//    }
//    public KodPenubuhanSyarikat getKodPenubuhanSyarikatDAO() {
//        return KodPenubuhanSyarikatDAO;
//    }
//
//    public void setKodPenubuhanSyarikatDAO(KodPenubuhanSyarikat KodPenubuhanSyarikatDAO) {
//        this.KodPenubuhanSyarikatDAO = KodPenubuhanSyarikatDAO;
//    }
    public KodPenubuhanSyarikat getSyarikatTubuh() {
        return syarikatTubuh;
    }

    public void setSyarikatTubuh(KodPenubuhanSyarikat syarikatTubuh) {
        this.syarikatTubuh = syarikatTubuh;
    }

    public List<KodPenubuhanSyarikat> getSenaraiSyktTubuh() {
        return senaraiSyktTubuh;
    }

    public void setSenaraiSyktTubuh(List<KodPenubuhanSyarikat> senaraiSyktTubuh) {
        this.senaraiSyktTubuh = senaraiSyktTubuh;
    }

    public String getSyktTubuh() {
        return syktTubuh;
    }

    public void setSyktTubuh(String syktTubuh) {
        this.syktTubuh = syktTubuh;
    }

    public String getIdmohon() {
        return idmohon;
    }

    public void setIdmohon(String idmohon) {
        this.idmohon = idmohon;
    }

    public String getKodPengenalan() {
        return kodPengenalan;
    }

    public void setKodPengenalan(String kodPengenalan) {
        this.kodPengenalan = kodPengenalan;
    } 

}
