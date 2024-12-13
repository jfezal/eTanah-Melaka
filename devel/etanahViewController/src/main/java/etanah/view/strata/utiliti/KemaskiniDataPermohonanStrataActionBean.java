/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.strata.utiliti;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.dao.*;
import etanah.manager.TabManager;
import etanah.model.FasaPermohonan;
import etanah.model.InfoAudit;
import etanah.model.KodJenisPengenalan;
import etanah.model.KodNegeri;
import etanah.model.Pemohon;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.model.PermohonanBangunan;
import etanah.model.Pihak;
import etanah.model.PihakAlamatTamb;
import etanah.model.strata.BadanPengurusan;
import etanah.service.StrataPtService;
import etanah.service.common.PemohonService;
import etanah.service.common.PermohonanService;
import etanah.service.common.PihakService;
import etanah.view.etanahActionBeanContext;
import java.text.ParseException;
import java.util.Date;
import java.util.List;
import net.sourceforge.stripes.action.*;
import oracle.bpel.services.workflow.WorkflowException;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.hibernate.Session;

@UrlBinding("/strata/kemaskini_data")
public class KemaskiniDataPermohonanStrataActionBean extends AbleActionBean {

    @Inject
    TabManager tabManager;
    @Inject
    PihakDAO pihakDAO;
    @Inject
    private etanah.Configuration conf;
    @Inject
    StrataPtService strService;
    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    PemohonDAO pemohonDAO;
    @Inject
    PihakService pihakService;
    @Inject
    KodNegeriDAO kodNegeriDAO;
    @Inject
    KodPenyerahDAO kodPenyerahDAO;
    @Inject
    PermohonanService permohonanService;
    @Inject
    PemohonService pemohonService;
    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;
    private Permohonan permohonan;
    private Pengguna pengguna;
    private List<Pemohon> listPemohon;
    private Pemohon pemohon;
    private Pihak pihak;
    private PihakAlamatTamb pat;
    private PihakAlamatTamb pihakAlamatTamb;
    private BadanPengurusan mc;
    private String idPermohonan;
    private String penyerah;
    private String penyerahKodNama;
    private String penyerahKod;
    private String penyerahNama;
    private String penyerahAlamat1;
    private String penyerahAlamat2;
    private String penyerahAlamat3;
    private String penyerahAlamat4;
    private String penyerahPoskod;
    private String penyerahNamaNegeri;
    private String penyerahNegeri;
    private String penyerahNoTelefon;
    private String penyerahEmail;
    private String kodNegeri;
    private String checkAlamat;
    private String suratMenyurat;
    private String idPihak;
    private String jenisPengenalan;
    private String jenisPengenalan2;
    private String nomborPengenalan;
    private BadanPengurusan projek;
    private Pihak badanUrus;
    private String nama;
    private String msg;
    private String alamat1;
    private String alamat2;
    private String alamat3;
    private String alamat4;
    private String poskod;
    private String negeri;
    private String negeri1;
    private String suratAlamat1;
    private String suratAlamat2;
    private String suratAlamat3;
    private String suratAlamat4;
    private String suratPoskod;
    private String suratNegeri;
    private String suratNegeri1;
    private boolean penyerahAdalahPemohon = false;
    private String syor;
    private String pengurusanNama;
    private String pengurusanAlamat1;
    private String pengurusanAlamat2;
    private String pengurusanAlamat3;
    private String pengurusanAlamat4;
    private String pengurusanPoskod;
    private String pengurusanJenisPengenalan;
    private String pengurusanKodNegeri;
    private String pengurusanNamaNegeri;
    private String pengurusanTarikhRujukan;
    private String pengurusanTarikhSijil;
    private String pengurusanNoRujukan;
    private String pengurusanTelefon;
    private String pengurusanTelefon2;
    private boolean flag = false;
    public String selectedTab = "0";
    private static final Logger LOG = Logger.getLogger(KemaskiniDataPermohonanStrataActionBean.class);

    @DefaultHandler
    public Resolution showForm() {
        return new ForwardResolution("/WEB-INF/jsp/strata/utiliti/kemaskini_data_permohonan_strata.jsp");
    }

    public Resolution search() throws WorkflowException, ParseException {

        kodNegeri = conf.getProperty("kodNegeri");


        idPermohonan = (String) getContext().getRequest().getParameter("idPermohonan");
        LOG.debug("--- Search START ---");
        if (permohonan == null) {
            LOG.debug("======== permohonan null =======");
            addSimpleError("Sila masukkan ID Permohonan");
            return new ForwardResolution("/WEB-INF/jsp/strata/utiliti/kemaskini_data_permohonan_strata.jsp");
        }

        idPermohonan = permohonan.getIdPermohonan();
        LOG.debug("================== idPermohonan : " + idPermohonan);

        if (idPermohonan == null) {
            LOG.debug("======== idpermohonan null");
            addSimpleError("Sila masukkan ID Permohonan");
            return new ForwardResolution("/WEB-INF/jsp/strata/utiliti/kemaskini_data_permohonan_strata.jsp");
        }

        permohonan = permohonanDAO.findById(idPermohonan);
        LOG.debug("======== Permohonan :" + permohonan);



        if (permohonan != null) {

            List<FasaPermohonan> fasamohon = strService.checkIDAliran(permohonan.getIdPermohonan());
            LOG.debug("======== fasamohon" + fasamohon);
            LOG.debug("======== permohonan.getIdPermohonan()" + permohonan.getIdPermohonan());

            if (fasamohon != null) {
                if (kodNegeri.equals("05")) {
                    for (FasaPermohonan fp : fasamohon) {

                        if (fp.getIdAliran().equalsIgnoreCase("bayaran5F")) {
                            permohonan = null;
                        }
                    }
                }
                if (kodNegeri.equals("04")) {
                    for (FasaPermohonan fp : fasamohon) {

                        if (fp.getIdAliran().equalsIgnoreCase("keputusan1")) {
                            permohonan = null;
                        }
                    }
                }
            }
            if (permohonan == null) {
                msg = "Maaf. ID Permohonan " + idPermohonan + " telah didaftarkan. Data Permohonan tidak boleh dikemaskini.";
            }
        } else {
            msg = "Maaf. ID Permohonan " + idPermohonan + " tidak dijumpai.";

        }



        if (permohonan == null) {
            addSimpleError(msg);
            return new JSP("strata/utiliti/kemaskini_data_permohonan_strata.jsp");
        } else {
            LOG.debug("--- Search END ---");
            setFlag(true);
            pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
            LOG.debug("======== pengguna :" + pengguna);
            return papar();
        }


    }

    public Resolution papar() throws WorkflowException, ParseException {
        selectedTab = getContext().getRequest().getParameter("selectedTab");
        LOG.info("================= idPermohonan masuk : " + idPermohonan);
        LOG.info("================= Tab : " + selectedTab);

        permohonan = permohonanDAO.findById(idPermohonan);
        if (permohonan.getPermohonanSebelum() != null) {
            idPermohonan = permohonan.getPermohonanSebelum().getIdPermohonan();
        }

        //Maklumat Penyerah
        Pemohon penyerahpemohon = strService.findPenyerahPemohon(idPermohonan);
        if (penyerahpemohon != null) {
            penyerah = String.valueOf(penyerahpemohon.getIdPemohon());
            LOG.info("penyerah--> " + penyerah);
        }
        if ((permohonan.getKodPenyerah() != null) && (permohonan.getKodPenyerah().getNama() != null)) {
            penyerahKodNama = permohonan.getKodPenyerah().getNama();
            LOG.info("---------penyerahKodNama--------" + penyerahKodNama);
        }
        if ((permohonan.getKodPenyerah() != null) && (permohonan.getKodPenyerah().getKod() != null)) {
            penyerahKod = permohonan.getKodPenyerah().getKod();
            LOG.info("---------penyerahKod--------" + penyerahKod);
        }
        if (permohonan.getPenyerahNama() != null) {
            penyerahNama = permohonan.getPenyerahNama();
            LOG.info("---------penyerahNama--------" + penyerahNama);
        }
        if (permohonan.getPenyerahAlamat1() != null) {
            penyerahAlamat1 = permohonan.getPenyerahAlamat1();
            LOG.info("---------penyerahAlamat1--------" + penyerahAlamat1);
        }
        if (permohonan.getPenyerahAlamat1() != null) {
            penyerahAlamat1 = permohonan.getPenyerahAlamat1();
            LOG.info("---------penyerahAlamat1--------" + penyerahAlamat1);
        }
        if (permohonan.getPenyerahAlamat2() != null) {
            penyerahAlamat2 = permohonan.getPenyerahAlamat2();
            LOG.info("---------penyerahAlamat2--------" + penyerahAlamat2);
        }
        if (permohonan.getPenyerahAlamat3() != null) {
            penyerahAlamat3 = permohonan.getPenyerahAlamat3();
            LOG.info("---------penyerahAlamat3--------" + penyerahAlamat3);
        }
        if (permohonan.getPenyerahAlamat4() != null) {
            penyerahAlamat4 = permohonan.getPenyerahAlamat4();
            LOG.info("---------penyerahAlamat4--------" + penyerahAlamat4);
        }
        if (permohonan.getPenyerahPoskod() != null) {
            penyerahPoskod = permohonan.getPenyerahPoskod();
            LOG.info("---------penyerahPoskod--------" + penyerahPoskod);
        }
        if ((permohonan.getPenyerahNegeri() != null) && (permohonan.getPenyerahNegeri().getNama() != null)) {
            penyerahNamaNegeri = permohonan.getPenyerahNegeri().getNama();
            LOG.info("---------penyerahNamaNegeri--------" + penyerahNamaNegeri);
        }
        if ((permohonan.getPenyerahNegeri() != null) && (permohonan.getPenyerahNegeri().getKod() != null)) {
            penyerahNegeri = permohonan.getPenyerahNegeri().getKod();
            LOG.info("---------penyerahNegeri--------" + penyerahNegeri);
        }
        if (permohonan.getPenyerahNoTelefon1() != null) {
            penyerahNoTelefon = permohonan.getPenyerahNoTelefon1();
            LOG.info("---------penyerahNoTelefon--------" + penyerahNoTelefon);
        }
        if (permohonan.getPenyerahEmail() != null) {
            penyerahEmail = permohonan.getPenyerahEmail();
            LOG.info("---------penyerahEmail--------" + penyerahEmail);
        }

        //Maklumat Pemohon
        kodNegeri = conf.getProperty("kodNegeri");
        LOG.debug("=====kodNegeri --> " + kodNegeri);
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
            if (kodNegeri.equals("05")) {

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

        if (penyerahpemohon != null) {
            penyerahAdalahPemohon = true;
        }


        //Maklumat Perbadanan Pengurusan
        mc = strService.findBdn(idPermohonan);
        LOG.info("---------mc---Id-----" + mc);
        if (mc != null) {
            if (mc.getPihak() != null) {

                if (mc.getPihak().getNama() != null) {
                    pengurusanNama = mc.getPihak().getNama();
                }
                if (mc.getPihak().getAlamat1() != null) {
                    pengurusanAlamat1 = mc.getPihak().getAlamat1();
                }
                if (mc.getPihak().getAlamat2() != null) {
                    pengurusanAlamat2 = mc.getPihak().getAlamat2();
                }
                if (mc.getPihak().getAlamat3() != null) {
                    pengurusanAlamat3 = mc.getPihak().getAlamat3();
                }
                if (mc.getPihak().getAlamat4() != null) {
                    pengurusanAlamat4 = mc.getPihak().getAlamat4();
                }
                if (mc.getPihak().getPoskod() != null) {
                    pengurusanPoskod = mc.getPihak().getPoskod();
                }
                if ((mc.getPihak().getNegeri() != null) && (mc.getPihak().getNegeri().getNama() != null)) {
                    pengurusanNamaNegeri = mc.getPihak().getNegeri().getNama();
                }
                //pengurusanJenisPengenalan = mc.getPihak().getJenisPengenalan().getKod();
                if ((mc.getPihak().getNegeri() != null) && (mc.getPihak().getNegeri().getKod() != null)) {
                    pengurusanKodNegeri = mc.getPihak().getNegeri().getKod();
                    //pengurusanNamaNegeri = mc.getPihak().getNegeri().getNama();
                    LOG.info("---------pengurusanNamaNegeri--------" + pengurusanNamaNegeri);
                }
                if (mc.getPengurusanTarikhSijil() != null) {
                    pengurusanTarikhSijil = strService.formatSDF(mc.getPengurusanTarikhSijil());
                }
                pengurusanNoRujukan = mc.getPengurusanNoRujukan();
                if (mc.getPengurusanTarikhRujukan() != null) {
                    pengurusanTarikhRujukan = strService.formatSDF(mc.getPengurusanTarikhRujukan());
                }

            }
        }

        if (permohonan == null) {
            return new JSP("strata/utiliti/kemaskini_data_permohonan_strata.jsp");
        } else {
            return new JSP("strata/utiliti/tab_maklumat_permohonan_strata.jsp");
        }
    }

    public Resolution updateMaklumatBadan() {
        LOG.debug("---------masuk update maklumat badan--------");
        idPermohonan = (String) getContext().getRequest().getParameter("idPermohonan");
        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        permohonan = permohonanDAO.findById(idPermohonan);
        InfoAudit infoAudit = new InfoAudit();
        infoAudit.setTarikhMasuk(new java.util.Date());
        infoAudit.setDiKemaskiniOleh(peng);
        infoAudit.setDimasukOleh(peng);
        infoAudit.setTarikhKemaskini(new java.util.Date());
        if (permohonan != null) {
            permohonan.setInfoAudit(infoAudit);
            permohonan.setPenyerahNama(penyerahNama);
            permohonan.setPenyerahAlamat1(penyerahAlamat1);
            permohonan.setPenyerahAlamat2(penyerahAlamat2);
            permohonan.setPenyerahAlamat3(penyerahAlamat3);
            permohonan.setPenyerahAlamat4(penyerahAlamat4);
            permohonan.setPenyerahPoskod(penyerahPoskod);
            KodNegeri kn = kodNegeriDAO.findById(penyerahNegeri);
            if (kn != null) {
                permohonan.setPenyerahNegeri(kn);
            }
            permohonan.setPenyerahNoTelefon1(penyerahNoTelefon);
            permohonan.setPenyerahEmail(penyerahEmail);
            strService.savePermohonan(permohonan);
        }
        String msg = "";
        msg = "Maklumat perbadanan pengurusan telah berjaya dikemaskini.";
        addSimpleMessage(msg);
        return new RedirectResolution(KemaskiniDataPermohonanStrataActionBean.class, "papar").addParameter("idPermohonan", idPermohonan).addParameter("selectedTab", selectedTab);
    }

    //UPDATE MAKLUMAT PENYERAH 
    public Resolution updateMaklumatPenyerah() {
        LOG.info("---------masuk update maklumat penyerah--------");
        idPermohonan = (String) getContext().getRequest().getParameter("idPermohonan");
        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        permohonan = permohonanDAO.findById(idPermohonan);
        InfoAudit infoAudit = new InfoAudit();
        infoAudit.setTarikhMasuk(new java.util.Date());
        infoAudit.setDiKemaskiniOleh(peng);
        infoAudit.setDimasukOleh(peng);
        infoAudit.setTarikhKemaskini(new java.util.Date());
        if (permohonan != null) {
            permohonan.setInfoAudit(infoAudit);
            permohonan.setPenyerahNama(penyerahNama);
            permohonan.setKodPenyerah(kodPenyerahDAO.findById(penyerahKod));
            permohonan.setPenyerahAlamat1(penyerahAlamat1);
            permohonan.setPenyerahAlamat2(penyerahAlamat2);
            permohonan.setPenyerahAlamat3(penyerahAlamat3);
            permohonan.setPenyerahAlamat4(penyerahAlamat4);
            permohonan.setPenyerahPoskod(penyerahPoskod);
            KodNegeri kn = kodNegeriDAO.findById(penyerahNegeri);
            if (kn != null) {
                permohonan.setPenyerahNegeri(kn);
            }
            permohonan.setPenyerahNoTelefon1(penyerahNoTelefon);
            permohonan.setPenyerahEmail(penyerahEmail);
            strService.savePermohonan(permohonan);
        }
        String msg = "";
        msg = "Maklumat penyerah telah berjaya dikemaskini.";
        addSimpleMessage(msg);
        return new RedirectResolution(KemaskiniDataPermohonanStrataActionBean.class, "papar").addParameter("idPermohonan", idPermohonan).addParameter("selectedTab", selectedTab);
    }

    //UPDATE MAKLUMAT PENYERAH 
    public Resolution resetPenyerah() {
        LOG.info("---------masuk update maklumat penyerah--------");
        idPermohonan = (String) getContext().getRequest().getParameter("idPermohonan");
        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        permohonan = permohonanDAO.findById(idPermohonan);
        InfoAudit infoAudit = new InfoAudit();
        infoAudit.setTarikhMasuk(new java.util.Date());
        infoAudit.setDiKemaskiniOleh(peng);
        infoAudit.setDimasukOleh(peng);
        infoAudit.setTarikhKemaskini(new java.util.Date());
        if (permohonan != null) {
            permohonan.setInfoAudit(infoAudit);
            permohonan.setPenyerahNama("");
            // permohonan.setPenyerahNoPengenalan("");
            permohonan.setPenyerahAlamat1("");
            permohonan.setPenyerahAlamat2("");
            permohonan.setPenyerahAlamat3("");
            permohonan.setPenyerahAlamat4("");
            permohonan.setPenyerahPoskod("");
            // KodNegeri kn = kodNegeriDAO.findById(null);
            // if (kn != null) {
            permohonan.setPenyerahNegeri(null);
            //  }
            permohonan.setPenyerahNoTelefon1("");
            permohonan.setPenyerahEmail("");
            strService.savePermohonan(permohonan);
        }
        String msg = "";
        msg = "Maklumat penyerah telah berjaya dikemaskini.";
        addSimpleMessage(msg);
        return new RedirectResolution(KemaskiniDataPermohonanStrataActionBean.class, "papar").addParameter("idPermohonan", idPermohonan).addParameter("selectedTab", selectedTab);
    }

    public Resolution simpanMaklumatPenyerah() {
        LOG.info("---------masuk update maklumat penyerah--------");
        //idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        idPermohonan = (String) getContext().getRequest().getParameter("idPermohonan");
        LOG.debug("================= idPermohonan update maklumat penyerah : " + idPermohonan);
        permohonan = permohonanDAO.findById(idPermohonan);
        //Pihak pihak = new Pihak();
        String error = "";
        String msg = "";
        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        InfoAudit infoAudit = new InfoAudit();
        infoAudit.setTarikhMasuk(new java.util.Date());
        infoAudit.setDiKemaskiniOleh(peng);
        infoAudit.setDimasukOleh(peng);
        infoAudit.setTarikhKemaskini(new java.util.Date());

        if (permohonan != null) {
            permohonan.setPenyerahNama(penyerahNama);
            permohonan.setPenyerahAlamat1(penyerahAlamat1);
            permohonan.setPenyerahAlamat2(penyerahAlamat2);
            permohonan.setPenyerahAlamat3(penyerahAlamat3);
            permohonan.setPenyerahAlamat4(penyerahAlamat4);
            permohonan.setPenyerahPoskod(penyerahPoskod);

            permohonan.setPenyerahNegeri(kodNegeriDAO.findById(penyerahNegeri));
            permohonan.setPenyerahEmail(penyerahEmail);
            permohonan.setPenyerahNoTelefon1(penyerahNoTelefon);
            permohonan.setKodPenyerah(kodPenyerahDAO.findById(penyerahKodNama));
            permohonan.setInfoAudit(infoAudit);

            strService.savePermohonan(permohonan);
        }


        msg = "Maklumat Penyerah telah berjaya dikemaskini.";
        //return new RedirectResolution(KemaskiniDataPermohonanStrataActionBean.class, "showForm").addParameter("error", error).addParameter("message", msg);
        return new RedirectResolution(KemaskiniDataPermohonanStrataActionBean.class, "papar").addParameter("idPermohonan", idPermohonan).addParameter("error", error).addParameter("message", msg).addParameter("selectedTab", selectedTab);
        //return new JSP("strata/utiliti/tab_maklumat_permohonan_strata.jsp");
    }

    //UPDATE MAKLUMAT PEMOHON 
    public Resolution simpanPemohon() {
        LOG.info("---------masuk update maklumat PEMOHON--------");
        //idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        idPermohonan = (String) getContext().getRequest().getParameter("idPermohonan");
        LOG.debug("================= idPermohonan update maklumat PEMOHON : " + idPermohonan);
        permohonan = permohonanDAO.findById(idPermohonan);

        pemohon = strService.findById(idPermohonan);

        Pihak pihak = strService.findByIdPihak(pemohon.getPihak().getIdPihak());
        String error = "";
        String msg = "";
        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        InfoAudit infoAudit = new InfoAudit();
        infoAudit.setTarikhMasuk(new java.util.Date());
        infoAudit.setDiKemaskiniOleh(peng);
        infoAudit.setDimasukOleh(peng);
        infoAudit.setTarikhKemaskini(new java.util.Date());

        if (pihak != null) {

            infoAudit.setDiKemaskiniOleh(peng);
            infoAudit.setTarikhKemaskini(new Date());
            infoAudit.setDimasukOleh(pihak.getInfoAudit().getDimasukOleh());
            infoAudit.setTarikhMasuk(pihak.getInfoAudit().getTarikhMasuk());
            pihak.setInfoAudit(infoAudit);
            pihak = setValuePihak(pihak);
            pihak.setNama(nama);
            pihak.setAlamat1(alamat1);
            pihak.setAlamat2(alamat2);
            pihak.setAlamat3(alamat3);
            pihak.setAlamat4(alamat4);
            pihak.setPoskod(poskod);
            if (kodNegeriDAO.findById(negeri) != null) {
                pihak.setNegeri(kodNegeriDAO.findById(negeri));
            }

        }
        LOG.info("ss --> " + pihak.getAlamat1());
        pihak = strService.savePihak(pihak);


        PihakAlamatTamb alamatTamb = strService.findAlamatTambByIdPihak(pihak.getIdPihak());

        if (suratAlamat1 != null || suratAlamat2 != null || suratAlamat3 != null
                || suratAlamat4 != null || suratPoskod != null || suratNegeri != null) {

            if (alamatTamb == null) {
                alamatTamb = new PihakAlamatTamb();
                alamatTamb.setPihak(pihak);
            }
            alamatTamb.setAlamatKetiga1(suratAlamat1);
            alamatTamb.setAlamatKetiga2(suratAlamat2);
            alamatTamb.setAlamatKetiga3(suratAlamat3);
            alamatTamb.setAlamatKetiga4(suratAlamat4);
            alamatTamb.setAlamatKetigaPoskod(suratPoskod);
            if (kodNegeriDAO.findById(suratNegeri) != null) {
                alamatTamb.setAlamatKetigaNegeri(kodNegeriDAO.findById(suratNegeri));
            }
            alamatTamb.setInfoAudit(infoAudit);

            strService.simpanPihakAlamatTamb(alamatTamb);
        }



        msg = "";
        msg = "Maklumat Pemohon telah berjaya dikemaskini.";
        addSimpleMessage(msg);
        return new RedirectResolution(KemaskiniDataPermohonanStrataActionBean.class, "papar").addParameter("idPermohonan", idPermohonan).addParameter("selectedTab", selectedTab);
        //return new JSP("strata/utiliti/tab_maklumat_permohonan_strata.jsp");
    }

    //PADAM MAKLUMAT PEMOHON 
    public Resolution resetPemohon() {
        LOG.info("---------masuk update maklumat PEMOHON--------");
        //idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        idPermohonan = (String) getContext().getRequest().getParameter("idPermohonan");
        LOG.debug("================= idPermohonan update maklumat PEMOHON : " + idPermohonan);
        permohonan = permohonanDAO.findById(idPermohonan);

        pemohon = strService.findById(idPermohonan);

        Pihak pihak = strService.findByIdPihak(pemohon.getPihak().getIdPihak());
        String error = "";
        String msg = "";
        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        InfoAudit infoAudit = new InfoAudit();
        infoAudit.setTarikhMasuk(new java.util.Date());
        infoAudit.setDiKemaskiniOleh(peng);
        infoAudit.setDimasukOleh(peng);
        infoAudit.setTarikhKemaskini(new java.util.Date());

        if (pihak != null) {

            infoAudit.setDiKemaskiniOleh(peng);
            infoAudit.setTarikhKemaskini(new Date());
            infoAudit.setDimasukOleh(pihak.getInfoAudit().getDimasukOleh());
            infoAudit.setTarikhMasuk(pihak.getInfoAudit().getTarikhMasuk());
            pihak.setInfoAudit(infoAudit);
            pihak = setValuePihak(pihak);
            // pihak.setNama(nama);
            pihak.setNoPengenalan("");
            pihak.setAlamat1("");
            pihak.setAlamat2("");
            pihak.setAlamat3("");
            pihak.setAlamat4("");
            pihak.setPoskod("");
            pihak.setNegeri(null);
        }
        LOG.info("ss --> " + pihak.getAlamat1());
        pihak = strService.savePihak(pihak);

        PihakAlamatTamb alamatTamb = strService.findAlamatTambByIdPihak(pihak.getIdPihak());

        if (alamatTamb == null) {
            alamatTamb = new PihakAlamatTamb();
            alamatTamb.setPihak(pihak);
        }
        alamatTamb.setAlamatKetiga1("");
        alamatTamb.setAlamatKetiga2("");
        alamatTamb.setAlamatKetiga3("");
        alamatTamb.setAlamatKetiga4("");
        alamatTamb.setAlamatKetigaPoskod("");
        alamatTamb.setAlamatKetigaNegeri(null);
        alamatTamb.setInfoAudit(infoAudit);

        strService.simpanPihakAlamatTamb(alamatTamb);

        msg = "";
        msg = "Maklumat Pemohon telah berjaya dikemaskini.";
        addSimpleMessage(msg);
        return new RedirectResolution(KemaskiniDataPermohonanStrataActionBean.class, "papar").addParameter("idPermohonan", idPermohonan).addParameter("selectedTab", selectedTab);
        //return new JSP("strata/utiliti/tab_maklumat_permohonan_strata.jsp");
    }

    public Resolution reset() {
        permohonan = new Permohonan();
        return new ForwardResolution("/WEB-INF/jsp/strata/utiliti/kemaskini_data_permohonan_strata.jsp");
    }

    public Permohonan getPermohonan() {
        return this.permohonan;
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

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public String getSelectedTab() {
        return selectedTab;
    }

    public void setSelectedTab(String selectedTab) {
        this.selectedTab = selectedTab;
    }

    public String getPenyerahKodNama() {
        return penyerahKodNama;
    }

    public void setPenyerahKodNama(String penyerahKodNama) {
        this.penyerahKodNama = penyerahKodNama;
    }

    public String getPenyerahKod() {
        return penyerahKod;
    }

    public void setPenyerahKod(String penyerahKod) {
        this.penyerahKod = penyerahKod;
    }

    public String getPenyerahNama() {
        return penyerahNama;
    }

    public void setPenyerahNama(String penyerahNama) {
        this.penyerahNama = penyerahNama;
    }

    public String getPenyerahAlamat1() {
        return penyerahAlamat1;
    }

    public void setPenyerahAlamat1(String penyerahAlamat1) {
        this.penyerahAlamat1 = penyerahAlamat1;
    }

    public String getPenyerahAlamat2() {
        return penyerahAlamat2;
    }

    public void setPenyerahAlamat2(String penyerahAlamat2) {
        this.penyerahAlamat2 = penyerahAlamat2;
    }

    public String getPenyerahAlamat3() {
        return penyerahAlamat3;
    }

    public void setPenyerahAlamat3(String penyerahAlamat3) {
        this.penyerahAlamat3 = penyerahAlamat3;
    }

    public String getPenyerahAlamat4() {
        return penyerahAlamat4;
    }

    public void setPenyerahAlamat4(String penyerahAlamat4) {
        this.penyerahAlamat4 = penyerahAlamat4;
    }

    public String getPenyerahPoskod() {
        return penyerahPoskod;
    }

    public void setPenyerahPoskod(String penyerahPoskod) {
        this.penyerahPoskod = penyerahPoskod;
    }

    public String getPenyerahNamaNegeri() {
        return penyerahNamaNegeri;
    }

    public void setPenyerahNamaNegeri(String penyerahNamaNegeri) {
        this.penyerahNamaNegeri = penyerahNamaNegeri;
    }

    public String getPenyerahNegeri() {
        return penyerahNegeri;
    }

    public void setPenyerahNegeri(String penyerahNegeri) {
        this.penyerahNegeri = penyerahNegeri;
    }

    public String getPenyerahNoTelefon() {
        return penyerahNoTelefon;
    }

    public void setPenyerahNoTelefon(String penyerahNoTelefon) {
        this.penyerahNoTelefon = penyerahNoTelefon;
    }

    public String getPenyerahEmail() {
        return penyerahEmail;
    }

    public void setPenyerahEmail(String penyerahEmail) {
        this.penyerahEmail = penyerahEmail;
    }

    public List<Pemohon> getListPemohon() {
        return listPemohon;
    }

    public void setListPemohon(List<Pemohon> listPemohon) {
        this.listPemohon = listPemohon;
    }

    public Pihak getPihak() {
        return pihak;
    }

    public void setPihak(Pihak pihak) {
        this.pihak = pihak;
    }

    public String getKodNegeri() {
        return kodNegeri;
    }

    public void setKodNegeri(String kodNegeri) {
        this.kodNegeri = kodNegeri;
    }

    public String getCheckAlamat() {
        return checkAlamat;
    }

    public void setCheckAlamat(String checkAlamat) {
        this.checkAlamat = checkAlamat;
    }

    public String getSuratMenyurat() {
        return suratMenyurat;
    }

    public void setSuratMenyurat(String suratMenyurat) {
        this.suratMenyurat = suratMenyurat;
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

    public String getNomborPengenalan() {
        return nomborPengenalan;
    }

    public void setNomborPengenalan(String nomborPengenalan) {
        this.nomborPengenalan = nomborPengenalan;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
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

    public String getNegeri() {
        return negeri;
    }

    public void setNegeri(String negeri) {
        this.negeri = negeri;
    }

    public String getNegeri1() {
        return negeri1;
    }

    public void setNegeri1(String negeri1) {
        this.negeri1 = negeri1;
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

    public String getSuratPoskod() {
        return suratPoskod;
    }

    public void setSuratPoskod(String suratPoskod) {
        this.suratPoskod = suratPoskod;
    }

    public String getSuratNegeri() {
        return suratNegeri;
    }

    public void setSuratNegeri(String suratNegeri) {
        this.suratNegeri = suratNegeri;
    }

    public String getSuratNegeri1() {
        return suratNegeri1;
    }

    public void setSuratNegeri1(String suratNegeri1) {
        this.suratNegeri1 = suratNegeri1;
    }

    public String getSyor() {
        return syor;
    }

    public void setSyor(String syor) {
        this.syor = syor;
    }

    public boolean isPenyerahAdalahPemohon() {
        return penyerahAdalahPemohon;
    }

    public void setPenyerahAdalahPemohon(boolean penyerahAdalahPemohon) {
        this.penyerahAdalahPemohon = penyerahAdalahPemohon;
    }

    public BadanPengurusan getMc() {
        return mc;
    }

    public void setMc(BadanPengurusan mc) {
        this.mc = mc;
    }

    public void getValuePihak(Pihak pihak) {
        LOG.info("1234 : " + pihak.getNama());
        this.idPihak = String.valueOf(pihak.getIdPihak());
        this.pengurusanNama = pihak.getNama();
        this.pengurusanAlamat1 = pihak.getAlamat1();
        this.pengurusanAlamat2 = pihak.getAlamat2();
        this.pengurusanAlamat3 = pihak.getAlamat3();
        this.pengurusanAlamat4 = pihak.getAlamat4();
        this.pengurusanPoskod = pihak.getPoskod();
        if (pihak.getNegeri() != null) {
            this.pengurusanKodNegeri = pihak.getNegeri().getKod();
            this.pengurusanNamaNegeri = pihak.getNegeri().getNama();
        }
        this.pengurusanTelefon = pihak.getNoTelefon1();
        this.pengurusanTelefon2 = pihak.getNoTelefon2();
    }

    public Pihak setValuePihak(Pihak pihak) {
        pihak.setNama(pengurusanNama);
        pihak.setAlamat1(pengurusanAlamat1);
        pihak.setAlamat2(pengurusanAlamat2);
        pihak.setAlamat3(pengurusanAlamat3);
        pihak.setAlamat4(pengurusanAlamat4);
        pihak.setPoskod(pengurusanPoskod);
        //pihak.setNoPengenalan(pengurusan);
        if (StringUtils.isEmpty(pengurusanJenisPengenalan)) {
        } else {
            KodJenisPengenalan kjp = new KodJenisPengenalan();
            kjp.setKod(pengurusanJenisPengenalan);
            pihak.setJenisPengenalan(kjp);
        }
        if (StringUtils.isEmpty(pengurusanKodNegeri)) {
        } else {
            KodNegeri kodNegeri = new KodNegeri();
            kodNegeri.setKod(pengurusanKodNegeri);
            pihak.setNegeri(kodNegeri);
        }

        pihak.setNoTelefon1(pengurusanTelefon);
        pihak.setNoTelefon2(pengurusanTelefon2);
        return pihak;
    }

    public PermohonanDAO getPermohonanDAO() {
        return permohonanDAO;
    }

    public void setPermohonanDAO(PermohonanDAO permohonanDAO) {
        this.permohonanDAO = permohonanDAO;
    }

    public String getPengurusanNama() {
        return pengurusanNama;
    }

    public void setPengurusanNama(String pengurusanNama) {
        this.pengurusanNama = pengurusanNama;
    }

    public String getPengurusanAlamat1() {
        return pengurusanAlamat1;
    }

    public void setPengurusanAlamat1(String pengurusanAlamat1) {
        this.pengurusanAlamat1 = pengurusanAlamat1;
    }

    public String getPengurusanAlamat2() {
        return pengurusanAlamat2;
    }

    public void setPengurusanAlamat2(String pengurusanAlamat2) {
        this.pengurusanAlamat2 = pengurusanAlamat2;
    }

    public String getPengurusanAlamat3() {
        return pengurusanAlamat3;
    }

    public void setPengurusanAlamat3(String pengurusanAlamat3) {
        this.pengurusanAlamat3 = pengurusanAlamat3;
    }

    public String getPengurusanAlamat4() {
        return pengurusanAlamat4;
    }

    public void setPengurusanAlamat4(String pengurusanAlamat4) {
        this.pengurusanAlamat4 = pengurusanAlamat4;
    }

    public String getPengurusanPoskod() {
        return pengurusanPoskod;
    }

    public void setPengurusanPoskod(String pengurusanPoskod) {
        this.pengurusanPoskod = pengurusanPoskod;
    }

    public String getPengurusanJenisPengenalan() {
        return pengurusanJenisPengenalan;
    }

    public void setPengurusanJenisPengenalan(String pengurusanJenisPengenalan) {
        this.pengurusanJenisPengenalan = pengurusanJenisPengenalan;
    }

    public String getPengurusanKodNegeri() {
        return pengurusanKodNegeri;
    }

    public void setPengurusanKodNegeri(String pengurusanKodNegeri) {
        this.pengurusanKodNegeri = pengurusanKodNegeri;
    }

    public String getPengurusanTarikhRujukan() {
        return pengurusanTarikhRujukan;
    }

    public void setPengurusanTarikhRujukan(String pengurusanTarikhRujukan) {
        this.pengurusanTarikhRujukan = pengurusanTarikhRujukan;
    }

    public String getPengurusanTarikhSijil() {
        return pengurusanTarikhSijil;
    }

    public void setPengurusanTarikhSijil(String pengurusanTarikhSijil) {
        this.pengurusanTarikhSijil = pengurusanTarikhSijil;
    }

    public String getPengurusanNoRujukan() {
        return pengurusanNoRujukan;
    }

    public void setPengurusanNoRujukan(String pengurusanNoRujukan) {
        this.pengurusanNoRujukan = pengurusanNoRujukan;
    }

    public String getPengurusanTelefon() {
        return pengurusanTelefon;
    }

    public void setPengurusanTelefon(String pengurusanTelefon) {
        this.pengurusanTelefon = pengurusanTelefon;
    }

    public String getPengurusanTelefon2() {
        return pengurusanTelefon2;
    }

    public void setPengurusanTelefon2(String pengurusanTelefon2) {
        this.pengurusanTelefon2 = pengurusanTelefon2;
    }
}
