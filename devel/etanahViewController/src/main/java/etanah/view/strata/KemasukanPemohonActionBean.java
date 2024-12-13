/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.strata;

import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.dao.HakmilikDAO;
import etanah.dao.KodJenisPengenalanDAO;
import etanah.dao.KodJenisPihakBerkepentinganDAO;
import etanah.dao.KodNegeriDAO;
import etanah.dao.PemohonDAO;
import etanah.dao.PermohonanDAO;
import etanah.dao.PihakDAO;
import etanah.model.FasaPermohonan;
import etanah.model.InfoAudit;
import etanah.model.KodBangsa;
import etanah.model.KodJenisPengenalan;
import etanah.model.KodNegeri;
import etanah.model.Pemohon;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.model.PermohonanPihak;
import etanah.model.Pihak;
import etanah.model.PihakAlamatTamb;
import etanah.model.strata.BadanPengurusan;
import etanah.service.StrataPtService;
import etanah.service.common.PemohonService;
import etanah.service.common.PermohonanPihakService;
import etanah.service.common.PermohonanService;
import etanah.service.common.PihakService;
import etanah.view.BasicTabActionBean;
import etanah.view.ListUtil;
import etanah.view.etanahActionBeanContext;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.RedirectResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;
import org.apache.log4j.Logger;
import org.apache.commons.lang.StringUtils;
import org.hibernate.Session;
import etanah.model.Hakmilik;
import etanah.model.HakmilikPermohonan;
import etanah.dao.HakmilikPermohonanDAO;

/**
 *
 * @author zadhirul.farihim
 */
@UrlBinding("/strata/kemasukan_pemohon")
public class KemasukanPemohonActionBean extends BasicTabActionBean {

    @Inject
    private etanah.Configuration conf;
    @Inject
    KodJenisPengenalanDAO kodJenisPengenalanDAO;
    @Inject
    KodNegeriDAO kodNegeriDAO;
    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    PemohonDAO pemohonDAO;
    @Inject
    PemohonService pemohonService;
    @Inject
    PihakDAO pihakDAO;
    @Inject
    PermohonanService permohonanService;
    @Inject
    PihakService pihakService;
    @Inject
    PermohonanPihakService permohonanPihakService;
    @Inject
    StrataPtService strService;
    @Inject
    KodJenisPihakBerkepentinganDAO kodJenisPihakBerkepentinganDAO;
    @Inject
    ListUtil listUtil;
    @Inject
    private HakmilikDAO hakmilikDAO;
    @Inject
    private HakmilikPermohonanDAO hakmilikPermohonanDAO;
    
    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;
    private BadanPengurusan badananPengurusan;
    private Permohonan permohonan;
    private List<Pemohon> listPemohon;
    private List<Pihak> senaraiPihak;
    private PermohonanPihak permohonanPihak;
    private Pihak pihak;
    private Pemohon pemohon;
    private String jenis;
    private String checkAlamat;
    private List<KodBangsa> senaraiKodBangsa;
    private String kodPengenalan;
    private String noPengenalan;
    private String namaPemohon;
    private String nama;
    private String jenisPengenalan;
    private String jenisPengenalan2;
    private String nomborPengenalan;
    private String alamat1;
    private String alamat2;
    private String alamat3;
    private String alamat4;
    private String poskod;
    private String negeri;
    private String suratAlamat1;
    private String suratAlamat2;
    private String suratAlamat3;
    private String suratAlamat4;
    private String suratNegeri;
    private String suratPoskod;
    private String idPihak;
    private static final Logger LOG = Logger.getLogger(KemasukanPemohonActionBean.class);
    private boolean penyerahAdalahPemohon = false;
    private List<Pihak> senaraiPihak1;
    private String carianNoPengenalan;
    private String index;
    private String suratNegeri1;
    private String negeri1;
    private String suratMenyurat;
    private String kodNegeri;
    private PihakAlamatTamb pat;
    private PihakAlamatTamb pihakAlamatTamb;
    private String syor;
    private HakmilikPermohonan hakmilikPermohonan;

    @DefaultHandler
    public Resolution showForm() {
        kodNegeri = conf.getProperty("kodNegeri");
        return new JSP("strata/maklumat_pemohon.jsp").addParameter("tab", "true");
    }

    public Resolution resetForm() {
//        nama = "";
//        jenisPengenalan = "";
//        nomborPengenalan = "";
//        alamat1 = "";
//        alamat2 = "";
//        alamat3 = "";
//        alamat4 = "";
//        poskod = "";
//        negeri = "";
//        suratAlamat1 = "";
//        suratAlamat2 = "";
//        suratAlamat3 = "";
//        suratAlamat4 = "";
//        suratPoskod = "";
//        suratNegeri = "";
//        checkAlamat = "No";

        /* new request : if Isi Semula = delete pemohon */
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        pemohon = strService.findById(idPermohonan);
        if (pemohon != null) {
            Pihak pihak1 = pihakDAO.findById(pemohon.getPihak().getIdPihak());
            try {
                LOG.info("::DELETE PEMOHON::");
                strService.deletePemohonByIDMohon(idPermohonan);

                if (pihak1 != null) {
                    try {

                        LOG.info("::DELETE PIHAK Alamat Tamb::");
                        strService.deletePihakATBByIdPihak(pihak1.getIdPihak());

                        LOG.info("::DELETE PIHAK::");
                        strService.deletePihakByIdPihak(pihak1.getIdPihak());

                        addSimpleMessage("Maklumat pemohon berjaya dikosongkan.");
                    } catch (Exception e) {
                        LOG.error(e);
                    }
                }

            } catch (Exception e) {
                LOG.error(e);
                addSimpleError("Terdapat masalah teknikal semasa memadam rekod.");
            }

        }

//        return new JSP("strata/maklumat_pemohon.jsp").addParameter("tab", "true");
        return new RedirectResolution(KemasukanPemohonActionBean.class, "showForm");
    }

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {
        kodNegeri = conf.getProperty("kodNegeri");
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = permohonanDAO.findById(idPermohonan);

        if (!permohonan.getKodUrusan().getKod().equals("PNB")) {
            if (permohonan.getPermohonanSebelum() != null) {
                permohonan = permohonan.getPermohonanSebelum();
            }
        }

        listPemohon = pemohonService.findPemohonByIdPermohonan(idPermohonan);
        if (!(listPemohon.isEmpty())) {
            getContext().getRequest().setAttribute("emptypemohon", Boolean.FALSE);
            LOG.info("::List pemohon not empty:: Pemohon found.");
            pemohon = listPemohon.get(0);
            pihak = pihakDAO.findById(pemohon.getPihak().getIdPihak());
            if (kodNegeri.equals("05") || kodNegeri.equals("04")) {
                if (pihak != null) {
                    LOG.info("pihak.getJenisPengenalan----::" + pihak.getJenisPengenalan());
                    LOG.info("pihak.getNoPengenalan----::" + pihak.getNoPengenalan());
                    if (pihak.getJenisPengenalan() != null && pihak.getNoPengenalan() != null) {
                        /*
                         * IF Jenis Pengenalan AND No Pengenalan not null, FIND BY THOSE CRITERIA
                         */
                        LOG.info("pihak.getJenisPengenalan----::" + pihak.getJenisPengenalan().getKod());
                        LOG.info("pihak.getNoPengenalan----::" + pihak.getNoPengenalan());
                        Pihak phk = strService.findPemohonAlamatSuratEqualAlamat(pihak.getJenisPengenalan().getKod(), pihak.getNoPengenalan());
                        LOG.info(":: CARI Pihak Alamat = Alamat_Surat by nombor dan jenis pengenalan ::");

                        if (phk != null) {
                            LOG.info(":: Alamat = Alamat_Surat ::");
                            checkAlamat = "Yes";
                            pat = pihakService.getAlamatTambahan(pihak);
                            if (pat == null) {
                                LOG.info("phk.getSuratAlamat1----::" + phk.getSuratAlamat1());
                                LOG.info("phk.getSuratAlamat2----::" + phk.getSuratAlamat2());
                                LOG.info("phk.getSuratAlamat3----::" + phk.getSuratAlamat3());
                                LOG.info("phk.getSuratAlamat4----::" + phk.getSuratAlamat4());
                                LOG.info("phk.getSuratPoskod----::" + phk.getSuratPoskod());
                                LOG.info("phk.getSuratNegeri----::" + phk.getSuratNegeri());

                                if (phk.getSuratAlamat1() != null || phk.getSuratAlamat2() != null || phk.getSuratAlamat3() != null || phk.getSuratAlamat4() != null
                                        || phk.getSuratPoskod() != null || phk.getSuratNegeri() != null) {
                                    LOG.info("suratMenyurat----::YES");
                                    suratMenyurat = "Yes";

                                } else {
                                    LOG.info("suratMenyurat----::NO");
                                    suratMenyurat = "No";
                                }
                            } else {
                                if (pat.getAlamatKetiga1() != null || pat.getAlamatKetiga2() != null || pat.getAlamatKetiga3() != null || pat.getAlamatKetiga4() != null
                                        || pat.getAlamatKetigaNegeri() != null || pat.getAlamatKetigaPoskod() != null) {
                                    suratMenyurat = "Yes";
                                } else {
                                    suratMenyurat = "No";
                                }
                            }
                        } else {
                            LOG.info(":: Alamat tak sama Alamat_Surat ::");
                            LOG.info("suratMenyurat----::NO");
                            checkAlamat = "No";
                            suratMenyurat = "No";
                        }
                    } else {
                        pat = pihakService.getAlamatTambahan(pihak);
                        if (pat == null) {
                            checkAlamat = "No";
                            suratMenyurat = "No";
                        } else {
                            checkAlamat = "Yes";
                            suratMenyurat = "Yes";
                        }
                    }
                    idPihak = String.valueOf(pihak.getIdPihak());
                    if (pihak.getJenisPengenalan() != null) {
                        jenisPengenalan = pihak.getJenisPengenalan().getKod();
                        jenisPengenalan2 = pihak.getJenisPengenalan().getNama();
                        LOG.info("Jenis Pengenalan tak null : " + jenisPengenalan);
                    }

                    if (pihak.getNoPengenalan() != null) {
                        nomborPengenalan = pihak.getNoPengenalan();
                    }
                    nama = pihak.getNama();
                    alamat1 = pihak.getAlamat1();
                    alamat2 = pihak.getAlamat2();
                    alamat3 = pihak.getAlamat3();
                    alamat4 = pihak.getAlamat4();
                    poskod = pihak.getPoskod();
                    if (pihak.getNegeri() != null) {
                        negeri = pihak.getNegeri().getKod();
                        negeri1 = pihak.getNegeri().getNama().toUpperCase();
                        LOG.info("--negeri1--" + negeri1);
                    }

                    pihakAlamatTamb = pihakService.getAlamatTambahan(pihak);
                    LOG.info("--pihakAlamatTamb--" + pihakAlamatTamb);
                    if (pihakAlamatTamb != null) {
                        suratAlamat1 = pihakAlamatTamb.getAlamatKetiga1();
                        suratAlamat2 = pihakAlamatTamb.getAlamatKetiga2();
                        suratAlamat3 = pihakAlamatTamb.getAlamatKetiga3();
                        suratAlamat4 = pihakAlamatTamb.getAlamatKetiga4();
                        suratPoskod = pihakAlamatTamb.getAlamatKetigaPoskod();
                        if (pihakAlamatTamb.getAlamatKetigaNegeri() != null) {
                            suratNegeri = pihakAlamatTamb.getAlamatKetigaNegeri().getKod();
                            suratNegeri1 = pihakAlamatTamb.getAlamatKetigaNegeri().getNama().toUpperCase();
                            LOG.info("--suratNegeri1--" + suratNegeri1);
                        }
                    }
                }
            } else {
                if (pihak != null) {
                    if (pihak.getJenisPengenalan() != null && pihak.getNoPengenalan() != null) {
                        /*
                         * IF Jenis Pengenalan AND No Pengenalan not null, FIND BY THOSE CRITERIA
                         */
                        LOG.info("pihak.getJenisPengenalan----::" + pihak.getJenisPengenalan().getKod());
                        LOG.info("pihak.getNoPengenalan----::" + pihak.getNoPengenalan());
                        Pihak phk = strService.findPemohonAlamatSuratEqualAlamat(pihak.getJenisPengenalan().getKod(), pihak.getNoPengenalan());
                        LOG.info(":: CARI Pihak Alamat = Alamat_Surat by nombor dan jenis pengenalan ::");

                        if (phk != null) {
                            LOG.info(":: Alamat = Alamat_Surat ::");
                            checkAlamat = "Yes";
                        } else {
                            LOG.info(":: Alamat tak sama Alamat_Surat ::");
                            checkAlamat = "No";
                        }
                    }
                    idPihak = String.valueOf(pihak.getIdPihak());
                    if (pihak.getJenisPengenalan() != null) {
                        jenisPengenalan = pihak.getJenisPengenalan().getKod();
                        jenisPengenalan2 = pihak.getJenisPengenalan().getNama();
                        LOG.info("Jenis Pengenalan tak null : " + jenisPengenalan);
                    }

                    if (pihak.getNoPengenalan() != null) {
                        nomborPengenalan = pihak.getNoPengenalan();
                    }
                    nama = pihak.getNama();
                    alamat1 = pihak.getAlamat1();
                    alamat2 = pihak.getAlamat2();
                    alamat3 = pihak.getAlamat3();
                    alamat4 = pihak.getAlamat4();
                    poskod = pihak.getPoskod();
                    if (pihak.getNegeri() != null) {
                        negeri = pihak.getNegeri().getKod();
                        negeri1 = pihak.getNegeri().getNama().toUpperCase();
                        LOG.info("--negeri1--" + negeri1);
                    }

                    pihakAlamatTamb = pihakService.getAlamatTambahan(pihak);
                    LOG.info("--pihakAlamatTamb--" + pihakAlamatTamb);
                    if (pihakAlamatTamb != null) {
                        suratAlamat1 = pihakAlamatTamb.getAlamatKetiga1();
                        suratAlamat2 = pihakAlamatTamb.getAlamatKetiga2();
                        suratAlamat3 = pihakAlamatTamb.getAlamatKetiga3();
                        suratAlamat4 = pihakAlamatTamb.getAlamatKetiga4();
                        suratPoskod = pihakAlamatTamb.getAlamatKetigaPoskod();
                        if (pihakAlamatTamb.getAlamatKetigaNegeri() != null) {
                            suratNegeri = pihakAlamatTamb.getAlamatKetigaNegeri().getKod();
                            suratNegeri1 = pihakAlamatTamb.getAlamatKetigaNegeri().getNama().toUpperCase();
                            LOG.info("--suratNegeri1--" + suratNegeri1);
                        }
                    }
                }
            }

        } else {
            getContext().getRequest().setAttribute("emptypemohon", Boolean.TRUE);
        }
        Pemohon penyerahpemohon = strService.findPenyerahPemohon(idPermohonan);
        if (penyerahpemohon != null) {
            penyerahAdalahPemohon = true;
        }

        if (kodNegeri.equals("04") && permohonan.getKodUrusan().getKod().equals("PPRUS")) {
            FasaPermohonan mohonFasa = strService.findFasaPermohonanByIdAliran(idPermohonan, "g_sedialaporan");
            if (mohonFasa != null) {
                if (mohonFasa.getKeputusan() != null) {
                    syor = mohonFasa.getKeputusan().getKod();
                }
            }
        }


    }

    public Resolution cariPemohonPopup() {

        return new JSP("strata/popup_cari_pemohon.jsp").addParameter("popup", "true");
    }

    public Resolution cariPihak() {

        String carianNama = (String) getContext().getRequest().getParameter("namaPemohon");
        LOG.info("carianNama : " + carianNama);
        senaraiPihak = new ArrayList<Pihak>();
        String carianKodPengenalan = getContext().getRequest().getParameter("kodPengenalan");
//        String carianNoPengenalan = getContext().getRequest().getParameter("noPengenalan");
        carianNoPengenalan = getContext().getRequest().getParameter("noPengenalan");
        LOG.info("===== kod : " + carianKodPengenalan + " no IC : " + carianNoPengenalan + " =====");

        if (!carianNama.isEmpty() && carianNoPengenalan.isEmpty() && carianKodPengenalan.isEmpty()) {
            getContext().getRequest().setAttribute("cariByNama", Boolean.TRUE);
            senaraiPihak = pihakService.findPihakByNama(carianNama);

            if (senaraiPihak.isEmpty()) {
                LOG.info("===senaraiPihak is empty===");
                getContext().getRequest().setAttribute("cariByNama", Boolean.FALSE);
                addSimpleError("Tiada data dijumpai.");
            } else {
                LOG.info("senaraiPihak : " + senaraiPihak.size());
                Comparator c = new Comparator<Pihak>() {
                    @Override
                    public int compare(Pihak p1, Pihak p2) {

                        String namaPihak1 = p1.getNama();
                        String namaPihak2 = p2.getNama();
                        return namaPihak1.compareTo(namaPihak2);
                    }
                };
                Collections.sort(senaraiPihak, c);
                for (Pihak pihak1 : senaraiPihak) {
//                    LOG.info("--negeri caps-1-");
                    if (pihak1.getNegeri() != null) {
                        pihak1.getNegeri().setNama(pihak1.getNegeri().getNama().toUpperCase());
//                    LOG.info("--negeri caps-2-");
                    }
                }
            }

        } else if (carianNama.isEmpty() && !carianNoPengenalan.isEmpty() && !carianKodPengenalan.isEmpty()) {
            getContext().getRequest().setAttribute("cariByIC", Boolean.TRUE);
//            pihak = pihakService.findPihak(carianKodPengenalan, carianNoPengenalan);
            LOG.info("carianKodPengenalan----::" + carianKodPengenalan);
            LOG.info("carianNoPengenalan----::" + carianNoPengenalan);
            senaraiPihak = pihakService.findPihakBynoKPkodKP(carianKodPengenalan, carianNoPengenalan);
//            if (pihak != null) {            
//                LOG.info("--pihak not null--::");
//                addSimpleMessage("Maklumat pemohon dijumpai.");
//                Pihak phk = strService.findPemohonAlamatSuratEqualAlamatByIdPihak(pihak.getIdPihak());
//                LOG.info(":: CARI Pihak Alamat = Alamat_Surat ::");
//                if (phk != null) {
//                    LOG.info(":: Alamat = Alamat_Surat ::");
//                    checkAlamat = "Yes";
//                } else {
//                    LOG.info(":: Alamat tak sama Alamat_Surat ::");
//                    checkAlamat = "No";
//                }
//                idPihak = String.valueOf(pihak.getIdPihak());
//                nama = pihak.getNama();
//                if (pihak.getJenisPengenalan() != null) {
//                    jenisPengenalan = pihak.getJenisPengenalan().getKod();
//                }
//                nomborPengenalan = pihak.getNoPengenalan();
//                alamat1 = pihak.getAlamat1();
//                alamat2 = pihak.getAlamat2();
//                alamat3 = pihak.getAlamat3();
//                alamat4 = pihak.getAlamat4();
//                poskod = pihak.getPoskod();
//                if (pihak.getNegeri() != null) {
//                    negeri = pihak.getNegeri().getKod();
//                }
//                suratAlamat1 = pihak.getSuratAlamat1();
//                suratAlamat2 = pihak.getSuratAlamat2();
//                suratAlamat3 = pihak.getSuratAlamat3();
//                suratAlamat4 = pihak.getSuratAlamat4();
//                suratPoskod = pihak.getSuratPoskod();
//                if (pihak.getSuratNegeri() != null) {
//                    suratNegeri = pihak.getSuratNegeri().getKod();
//                }

            /*start*/
            if (senaraiPihak.isEmpty()) {
                LOG.info("===senaraiPihak is empty===");
                getContext().getRequest().setAttribute("cariByIC", Boolean.FALSE);
                addSimpleError("Tiada data dijumpai.");
            } else {
                LOG.info("===senaraiPihak is not empty===");
                LOG.info("--display data base on nopenganalan--::");
                Comparator c = new Comparator<Pihak>() {
                    @Override
                    public int compare(Pihak p1, Pihak p2) {
                        String noPeng1 = p1.getNoPengenalan();
                        String noPeng2 = p2.getNoPengenalan();
                        return noPeng1.compareTo(noPeng2);
                    }
                };
                Collections.sort(senaraiPihak, c);
                for (Pihak pihak1 : senaraiPihak) {
//                    LOG.info("--negeri caps-1-");
                    if (pihak1.getNegeri() != null) {
                        pihak1.getNegeri().setNama(pihak1.getNegeri().getNama().toUpperCase());
//                    LOG.info("--negeri caps-2-");
                    }
                }
                LOG.info("--displayed data base on nopenganalan--::");
            }
            /*end*/

//            } else {
//                getContext().getRequest().setAttribute("cariByIC", Boolean.FALSE);
////                addSimpleError("Tiada maklumat dijumpai bagi carian tersebut.");
//                addSimpleError("Tiada data dijumpai.");
//            }


        } else if (!carianNama.isEmpty() && !carianNoPengenalan.isEmpty() && !carianKodPengenalan.isEmpty()) {

            addSimpleError("Sila isi maklumat bagi salah satu carian.");
        } else if (carianNama.isEmpty() && !carianNoPengenalan.isEmpty() && carianKodPengenalan.isEmpty()) {

            addSimpleError("Sila pilih jenis pengenalan.");
        } else if (carianNama.isEmpty() && carianNoPengenalan.isEmpty() && !carianKodPengenalan.isEmpty()) {

//            addSimpleError("Sila masukkan nombor pengenalan.");             
            addSimpleError("Tiada data dijumpai.");
        } else {

            addSimpleError("Sila masukkan data untuk melakukan carian.");
        }
        return new JSP("strata/popup_cari_pemohon.jsp").addParameter("popup", "true");
    }

    public Resolution simpanPemohon() {
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = permohonanService.findById(idPermohonan);
        Pengguna pguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        InfoAudit ia = new InfoAudit();

        LOG.info("===== kod : " + jenisPengenalan + " no IC : " + nomborPengenalan + " =====");

        Pihak phk = null;
        if (jenisPengenalan != null && nomborPengenalan != null) {
            phk = pihakService.findPihak(jenisPengenalan, nomborPengenalan);
        } else {
            if (idPihak != null) {
                LOG.info("--idPihak--:" + idPihak);
                phk = pihakService.findById(idPihak);
            }
        }

        if (phk != null) {
            LOG.info("--Pihak is not null, Id--: " + phk.getIdPihak());
            ia = phk.getInfoAudit();
            ia.setDiKemaskiniOleh(pguna);
            ia.setTarikhKemaskini(new java.util.Date());
        } else {
            LOG.info("--Pihak not found, create new Pihak()--");
            phk = new Pihak();
            ia.setDimasukOleh(pguna);
            ia.setTarikhMasuk(new java.util.Date());
        }
        phk.setNama(nama);
        if (jenisPengenalan != null) {
            phk.setJenisPengenalan(kodJenisPengenalanDAO.findById(jenisPengenalan));
        } else {
            phk.setJenisPengenalan(null);
        }
        phk.setNoPengenalan(nomborPengenalan);
        phk.setAlamat1(alamat1);
        phk.setAlamat2(alamat2);
        phk.setAlamat3(alamat3);
        phk.setAlamat4(alamat4);
        phk.setPoskod(poskod);
        if (negeri != null && !negeri.equals("")) {
            phk.setNegeri(kodNegeriDAO.findById(negeri));
        } else {
            phk.setNegeri(null);
        }
        phk.setInfoAudit(ia);
        LOG.info("--saving in pihak--");
        strService.simpanPihak(phk);

        LOG.info("--saving in Pihak_Alamat_Tamb--");
        PihakAlamatTamb phkAlamatTamb = null;

        phkAlamatTamb = pihakService.getAlamatTambahan(phk);
        LOG.info("--phkAlamatTamb--:" + phkAlamatTamb);

        if (phkAlamatTamb == null) {
            phkAlamatTamb = new PihakAlamatTamb();
            phkAlamatTamb.setPihak(phk);
        }
        phkAlamatTamb.setAlamatKetiga1(suratAlamat1);
        phkAlamatTamb.setAlamatKetiga2(suratAlamat2);
        phkAlamatTamb.setAlamatKetiga3(suratAlamat3);
        phkAlamatTamb.setAlamatKetiga4(suratAlamat4);
        phkAlamatTamb.setAlamatKetigaPoskod(suratPoskod);
        if (suratNegeri != null && !suratNegeri.equals("")) {
            phkAlamatTamb.setAlamatKetigaNegeri(kodNegeriDAO.findById(suratNegeri));
        } else {
            phkAlamatTamb.setAlamatKetigaNegeri(null);
        }
        phkAlamatTamb.setInfoAudit(ia);
        strService.simpanPihakAlamatTamb(phkAlamatTamb);
        LOG.info("--saved in pihak alamat tamb--");


        List<Pemohon> senaraiPemohon = pemohonService.findPemohonByIdPermohonan(idPermohonan);
        InfoAudit info1 = new InfoAudit();
        if (!(senaraiPemohon.isEmpty())) {
            LOG.info("list pemohon found.");
            pemohon = senaraiPemohon.get(0);
            info1 = pemohon.getInfoAudit();
            info1.setDiKemaskiniOleh(pguna);
            info1.setTarikhKemaskini(new java.util.Date());
        } else {
            LOG.info("list pemohon not found.");
            pemohon = new Pemohon();
            info1.setDimasukOleh(pguna);
            info1.setTarikhMasuk(new java.util.Date());
        }
        pemohon.setPermohonan(permohonan);
        pemohon.setPihak(phk);
        pemohon.setInfoAudit(info1);
        pemohon.setCawangan(permohonan.getCawangan());
        LOG.info("==IN SAVING PEMOHON==");
        try {
            pemohonService.saveOrUpdate(pemohon);
            addSimpleMessage("Maklumat pemohon telah berjaya disimpan.");
        } catch (Exception e) {
            LOG.error(e);
            addSimpleError("Terdapat masalah semasa menyimpan maklumat pemohon.");
        }

        /*If Penyerah = Pemohon, updating Permohonan table for each changes of Penyerah information*/
        Pemohon pmohon = strService.findPenyerahPemohon(idPermohonan);
        if (pmohon != null) {
            if (phk != null) {
                LOG.info("idPihak = " + phk.getIdPihak());

                ia = permohonan.getInfoAudit();
                ia.setDiKemaskiniOleh(pguna);
                ia.setTarikhKemaskini(new java.util.Date());

                permohonan.setInfoAudit(ia);

                if (phk.getNama() != null) {
                    permohonan.setPenyerahNama(phk.getNama());
                }
                if (phk.getAlamat1() != null) {
                    permohonan.setPenyerahAlamat1(phk.getAlamat1());
                }
                if (phk.getAlamat2() != null) {
                    permohonan.setPenyerahAlamat2(phk.getAlamat2());
                }
                if (phk.getAlamat3() != null) {
                    permohonan.setPenyerahAlamat3(phk.getAlamat3());
                }
                if (phk.getAlamat4() != null) {
                    permohonan.setPenyerahAlamat4(phk.getAlamat4());
                }
                if (phk.getPoskod() != null) {
                    permohonan.setPenyerahPoskod(phk.getPoskod());
                }
                if (phk.getNegeri() != null) {
                    permohonan.setPenyerahNegeri(kodNegeriDAO.findById(phk.getNegeri().getKod()));
                }
                if (phk.getJenisPengenalan() != null) {
                    permohonan.setPenyerahJenisPengenalan(kodJenisPengenalanDAO.findById(phk.getJenisPengenalan().getKod()));
                }
                if (phk.getNoPengenalan() != null) {
                    permohonan.setPenyerahNoPengenalan(phk.getNoPengenalan());
                }
                LOG.info("=========IN UPDATING TABLE PERMOHANAN========");
                permohonan = permohonanService.saveOrUpdate(permohonan);
            }
        }

        return new RedirectResolution(KemasukanPemohonActionBean.class, "showForm");
    }

    public Resolution simpanPemohonPopup() {

        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = permohonanService.findById(idPermohonan);
        Pengguna pguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        InfoAudit ia = new InfoAudit();

        Pihak phk = pihakDAO.findById(Long.parseLong(idPihak));
        if (phk == null) {
            phk = new Pihak();
            ia.setDimasukOleh(pguna);
            ia.setTarikhMasuk(new java.util.Date());
        } else {
            ia = phk.getInfoAudit();
            ia.setDiKemaskiniOleh(pguna);
            ia.setTarikhKemaskini(new java.util.Date());
        }

        phk.setNama(nama);
        phk.setJenisPengenalan(kodJenisPengenalanDAO.findById(jenisPengenalan));
        phk.setNoPengenalan(nomborPengenalan);
        phk.setAlamat1(alamat1);
        phk.setAlamat2(alamat2);
        phk.setAlamat3(alamat3);
        phk.setAlamat4(alamat4);
        phk.setPoskod(poskod);
        if (negeri != null && !negeri.equals("")) {
            phk.setNegeri(kodNegeriDAO.findById(negeri));
        } else {
            phk.setNegeri(null);
        }
        phk.setInfoAudit(ia);
        LOG.info("==IN SAVING PIHAK==");
        strService.simpanPihak(phk);

        LOG.info("--saving in Pihak_Alamat_Tamb--");
        PihakAlamatTamb phkAlamatTamb = null;

        phkAlamatTamb = pihakService.getAlamatTambahan(phk);
        LOG.info("--phkAlamatTamb--:" + phkAlamatTamb);

        if (phkAlamatTamb == null) {
            phkAlamatTamb = new PihakAlamatTamb();
            phkAlamatTamb.setPihak(phk);
        }
        phkAlamatTamb.setAlamatKetiga1(suratAlamat1);
        phkAlamatTamb.setAlamatKetiga2(suratAlamat2);
        phkAlamatTamb.setAlamatKetiga3(suratAlamat3);
        phkAlamatTamb.setAlamatKetiga4(suratAlamat4);
        phkAlamatTamb.setAlamatKetigaPoskod(suratPoskod);
        if (suratNegeri != null && !suratNegeri.equals("")) {
            phkAlamatTamb.setAlamatKetigaNegeri(kodNegeriDAO.findById(suratNegeri));
        } else {
            phkAlamatTamb.setAlamatKetigaNegeri(null);
        }
        phkAlamatTamb.setInfoAudit(ia);
        strService.simpanPihakAlamatTamb(phkAlamatTamb);
        LOG.info("--saved in pihak alamat tamb--");

        List<Pemohon> senaraiPemohon = pemohonService.findPemohonByIdPermohonan(idPermohonan);
        InfoAudit info1 = new InfoAudit();
        if (!(senaraiPemohon.isEmpty())) {
            LOG.info("list pemohon found.");
            pemohon = senaraiPemohon.get(0);
            info1 = pemohon.getInfoAudit();
            info1.setDiKemaskiniOleh(pguna);
            info1.setTarikhKemaskini(new java.util.Date());
        } else {
            LOG.info("list pemohon not found.");
            pemohon = new Pemohon();
            info1.setDimasukOleh(pguna);
            info1.setTarikhMasuk(new java.util.Date());
        }
        pemohon.setPermohonan(permohonan);
        pemohon.setPihak(phk);
        pemohon.setInfoAudit(info1);
        pemohon.setCawangan(permohonan.getCawangan());
        LOG.info("==IN SAVING PEMOHON==");
        try {
            pemohonService.saveOrUpdate(pemohon);
            addSimpleMessage("Maklumat pemohon telah berjaya disimpan.");
        } catch (Exception e) {
            LOG.error(e);
            addSimpleError("Terdapat masalah semasa menyimpan maklumat pemohon.");
        }

        return new RedirectResolution(KemasukanPemohonActionBean.class, "showForm");
//        return new JSP("strata/maklumat_pemohon.jsp").addParameter("tab", "true");
    }

    public Resolution simpanPemohonPopup2() {
        LOG.info("== CHECK IDPIHAK : " + idPihak);
        idPihak = getContext().getRequest().getParameter("idPihak");
        LOG.info("===== idPihak : " + idPihak);

        Pihak pihaksearch = pihakService.findById(idPihak);

        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = permohonanService.findById(idPermohonan);
        Pengguna pguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        InfoAudit ia = new InfoAudit();

        List<Pemohon> senaraiPemohon = pemohonService.findPemohonByIdPermohonan(idPermohonan);
        if (!(senaraiPemohon.isEmpty())) {
            LOG.info("list pemohon found.");
            pemohon = senaraiPemohon.get(0);
            ia = pemohon.getInfoAudit();
            ia.setDiKemaskiniOleh(pguna);
            ia.setTarikhKemaskini(new java.util.Date());
        } else {
            LOG.info("list pemohon not found.");
            pemohon = new Pemohon();
            ia.setDimasukOleh(pguna);
            ia.setTarikhMasuk(new java.util.Date());
        }
        pemohon.setPermohonan(permohonan);
        pemohon.setPihak(pihaksearch);
        pemohon.setInfoAudit(ia);
        pemohon.setCawangan(permohonan.getCawangan());
        LOG.info("==IN SAVING PEMOHON==");
        try {
            pemohonService.saveOrUpdate(pemohon);
            addSimpleMessage("Maklumat pemohon telah berjaya disimpan.");
        } catch (Exception e) {
            LOG.error(e);
            addSimpleError("Terdapat masalah semasa menyimpan maklumat pemohon.");
        }

        return new RedirectResolution(KemasukanPemohonActionBean.class, "showForm");
//        return new JSP("strata/maklumat_pemohon.jsp").addParameter("tab", "true");
    }

    public Resolution kemaskiniPemohon() {
        getContext().getRequest().setAttribute("kemaskini", Boolean.TRUE);
        return new JSP("strata/maklumat_pemohon.jsp").addParameter("tab", "true");
    }

    public Resolution maklumatPemohonReadOnly() {
        getContext().getRequest().setAttribute("readOnly", Boolean.TRUE);        
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = permohonanService.findById(idPermohonan);
        hakmilikPermohonan = strService.findMohonHakmilik(idPermohonan);
        if(permohonan.getKodUrusan().getKod().equals("PNSS")){
            LOG.info("== CHECK HAKMILIK : " + hakmilikPermohonan.getHakmilik().getIdHakmilik());
            badananPengurusan = strService.findBdnByIdHmInduk(hakmilikPermohonan.getHakmilik().getIdHakmilik());
            //badananPengurusan = strService.findBdn(permohonan.getPermohonanSebelum().getIdPermohonan());
        }
        return new JSP("strata/maklumat_pemohon_PNSS.jsp").addParameter("tab", "true");
    }

    public Resolution maklumatPemohonReadOnly2() {
        getContext().getRequest().setAttribute("readonly", Boolean.TRUE);
        return new JSP("strata/maklumat_pemohon_Rayuan.jsp").addParameter("tab", "true");
    }

    public Resolution maklumatPemohonReadOnly3() {
        getContext().getRequest().setAttribute("readOnly", Boolean.TRUE);
        return new JSP("strata/maklumat_pemohon_readonly.jsp").addParameter("tab", "true");
    }

    public String getCheckAlamat() {
        return checkAlamat;
    }

    public void setCheckAlamat(String checkAlamat) {
        this.checkAlamat = checkAlamat;
    }

    public String getJenis() {
        return jenis;
    }

    public void setJenis(String jenis) {
        this.jenis = jenis;
    }

    public String getKodPengenalan() {
        return kodPengenalan;
    }

    public void setKodPengenalan(String kodPengenalan) {
        this.kodPengenalan = kodPengenalan;
    }

    public List<Pemohon> getListPemohon() {
        return listPemohon;
    }

    public void setListPemohon(List<Pemohon> listPemohon) {
        this.listPemohon = listPemohon;
    }

    public String getNamaPemohon() {
        return namaPemohon;
    }

    public void setNamaPemohon(String namaPemohon) {
        this.namaPemohon = namaPemohon;
    }

    public String getNegeri() {
        return negeri;
    }

    public void setNegeri(String negeri) {
        this.negeri = negeri;
    }

    public String getNoPengenalan() {
        return noPengenalan;
    }

    public void setNoPengenalan(String noPengenalan) {
        this.noPengenalan = noPengenalan;
    }

    public Pihak getPihak() {
        return pihak;
    }

    public void setPihak(Pihak pihak) {
        this.pihak = pihak;
    }

    public List<Pihak> getSenaraiPihak() {
        return senaraiPihak;
    }

    public void setSenaraiPihak(List<Pihak> senaraiPihak) {
        this.senaraiPihak = senaraiPihak;
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

    public String getPoskod() {
        return poskod;
    }

    public void setPoskod(String poskod) {
        this.poskod = poskod;
    }

    public String getSuratAlamat1() {
        return suratAlamat1;
    }

    public void setSuratAlamat1(String suratAlamat1) {
        this.suratAlamat1 = suratAlamat1;
    }

    public String getSuratAlamat2() {
        return suratAlamat2;
    }

    public void setSuratAlamat2(String suratAlamat2) {
        this.suratAlamat2 = suratAlamat2;
    }

    public String getSuratAlamat3() {
        return suratAlamat3;
    }

    public void setSuratAlamat3(String suratAlamat3) {
        this.suratAlamat3 = suratAlamat3;
    }

    public String getSuratAlamat4() {
        return suratAlamat4;
    }

    public void setSuratAlamat4(String suratAlamat4) {
        this.suratAlamat4 = suratAlamat4;
    }

    public String getSuratNegeri() {
        return suratNegeri;
    }

    public void setSuratNegeri(String suratNegeri) {
        this.suratNegeri = suratNegeri;
    }

    public String getSuratPoskod() {
        return suratPoskod;
    }

    public void setSuratPoskod(String suratPoskod) {
        this.suratPoskod = suratPoskod;
    }

    public String getIdPihak() {
        return idPihak;
    }

    public void setIdPihak(String idPihak) {
        this.idPihak = idPihak;
    }

    public String getJenisPengenalan() {
        return jenisPengenalan;
    }

    public void setJenisPengenalan(String jenisPengenalan) {
        this.jenisPengenalan = jenisPengenalan;
    }

    public String getJenisPengenalan2() {
        return jenisPengenalan2;
    }

    public void setJenisPengenalan2(String jenisPengenalan2) {
        this.jenisPengenalan2 = jenisPengenalan2;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getNomborPengenalan() {
        return nomborPengenalan;
    }

    public void setNomborPengenalan(String nomborPengenalan) {
        this.nomborPengenalan = nomborPengenalan;
    }

    public boolean isPenyerahAdalahPemohon() {
        return penyerahAdalahPemohon;
    }

    public void setPenyerahAdalahPemohon(boolean penyerahAdalahPemohon) {
        this.penyerahAdalahPemohon = penyerahAdalahPemohon;
    }

    public String getCarianNoPengenalan() {
        return carianNoPengenalan;
    }

    public void setCarianNoPengenalan(String carianNoPengenalan) {
        this.carianNoPengenalan = carianNoPengenalan;
    }

    public String getIndex() {
        return index;
    }

    public void setIndex(String index) {
        this.index = index;
    }

    public String getNegeri1() {
        return negeri1;
    }

    public void setNegeri1(String negeri1) {
        this.negeri1 = negeri1;
    }

    public String getSuratNegeri1() {
        return suratNegeri1;
    }

    public void setSuratNegeri1(String suratNegeri1) {
        this.suratNegeri1 = suratNegeri1;
    }

    public Permohonan getPermohonan() {
        return permohonan;
    }

    public void setPermohonan(Permohonan permohonan) {
        this.permohonan = permohonan;
    }

    /**
     * @return the suratMenyurat
     */
    public String getSuratMenyurat() {
        return suratMenyurat;
    }

    /**
     * @param suratMenyurat the suratMenyurat to set
     */
    public void setSuratMenyurat(String suratMenyurat) {
        this.suratMenyurat = suratMenyurat;
    }

    /**
     * @return the kodNegeri
     */
    public String getKodNegeri() {
        return kodNegeri;
    }

    /**
     * @param kodNegeri the kodNegeri to set
     */
    public void setKodNegeri(String kodNegeri) {
        this.kodNegeri = kodNegeri;
    }

    /**
     * @return the pat
     */
    public PihakAlamatTamb getPat() {
        return pat;
    }

    /**
     * @param pat the pat to set
     */
    public void setPat(PihakAlamatTamb pat) {
        this.pat = pat;
    }

    public String getSyor() {
        return syor;
    }

    public void setSyor(String syor) {
        this.syor = syor;
    }

    public BadanPengurusan getBadananPengurusan() {
        return badananPengurusan;
    }

    public void setBadananPengurusan(BadanPengurusan badananPengurusan) {
        this.badananPengurusan = badananPengurusan;
    }
  
}
