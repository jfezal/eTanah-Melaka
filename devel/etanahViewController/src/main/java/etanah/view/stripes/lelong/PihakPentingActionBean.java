/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.stripes.lelong;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.dao.KodNegeriDAO;
import etanah.dao.KodSukuDAO;
import etanah.dao.PermohonanAtasPerserahanDAO;
import etanah.dao.PermohonanDAO;
import etanah.model.Alamat;
import etanah.model.AlamatSurat;
import etanah.model.Enkuiri;
import etanah.model.Hakmilik;
import etanah.model.HakmilikPermohonan;
import etanah.model.HakmilikPihakBerkepentingan;
import etanah.model.HakmilikUrusan;
import etanah.model.InfoAudit;
import etanah.model.KodCawangan;
import etanah.model.KodNegeri;
import etanah.model.KodStatusEnkuiri;
import etanah.model.KodSuku;
import etanah.model.Pemohon;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.model.PermohonanAsal;
import etanah.model.PermohonanAtasPerserahan;
import etanah.model.PermohonanHubungan;
import etanah.model.PermohonanPihak;
import etanah.model.Pihak;
import etanah.model.PihakAlamatTamb;
import etanah.service.RegService;
import etanah.service.common.EnkuiriService;
import etanah.service.common.LelongService;
import etanah.service.daftar.PembetulanService;
import etanah.view.etanahActionBeanContext;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Map;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.UrlBinding;
import org.apache.log4j.Logger;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.controller.LifecycleStage;

/**
 *
 * @author mazurahayati.yusop
 */
@UrlBinding("/lelong/pihak_berkepentingan")
public class PihakPentingActionBean extends AbleActionBean {

    private static final Logger logger = Logger.getLogger(PihakPentingActionBean.class);
    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    PermohonanAtasPerserahanDAO permohonanAtasPerserahanDAO;
    @Inject
    LelongService lelongService;
    @Inject
    RegService regService;
    @Inject
    PembetulanService pService;
    @Inject
    EnkuiriService enService;
    @Inject
    KodNegeriDAO kodNegeriDAO;
    @Inject
    KodSukuDAO kodSukuDAO;
    @Inject
    private etanah.Configuration conf;
    private String idHakmilik;
    private List<HakmilikPermohonan> hakmilikPermohonanList;
    private List<HakmilikPihakBerkepentingan> pihakKepentinganList = new ArrayList<HakmilikPihakBerkepentingan>();
    private String idPermohonan;
    private String idPihak;
    private Permohonan permohonan;
    private Enkuiri enkuiri;
    private Enkuiri enkuiriSBlm;
    private List<PermohonanPihak> senaraiPermohonanPihak3;
    private List<String> senaraiPermohonanPihak4;
    private PermohonanPihak mohonPihak;
    private Pihak pihak;
    private HakmilikPihakBerkepentingan hakmilikPihakBerkepentingan;
    private String perserahanGadaian;
    private boolean view = false;
    private boolean tangguhbatale = false;
    private boolean ppjp = false;
    private boolean viewnama = false;
    private boolean negeri = false;
    private PihakAlamatTamb pihakAlamatTamb;
    private List<HakmilikUrusan> listHakmilikUrusan;
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    private List<HakmilikPihakBerkepentingan> listHakmilikPihakBerkepentingan;
    private List<HakmilikPermohonan> listHakmilikMohon;
    private List<String> listidHakmilik;
    private boolean iDSerah;
    private List<PermohonanAtasPerserahan> listNoPerserahan = new ArrayList<PermohonanAtasPerserahan>();
    private List<String> listIDHakmilik = new ArrayList<String>();
    private List<String> listIDHakmilik2 = new ArrayList<String>();
    private Pengguna pengguna;
    private String tempohGadaian;
    private String tamatGadaian;

    @DefaultHandler
    public Resolution showForm() {
        ppjp = true;
        return new JSP("lelong/maklumat_pihak_berkepentingan.jsp").addParameter("tab", "true");
    }

    public Resolution showForm3() {
        ppjp = true;
        iDSerah = false;
        return new JSP("lelong/maklumat_pihak_berkepentingan.jsp").addParameter("tab", "true");
    }

    public Resolution tangguhBatal() {
        tangguhbatale = true;
        if (permohonan.getPermohonanSebelum() != null && permohonan.getPermohonanSebelum().getKodUrusan().getKod().equals("PJTA")) {
            viewnama = true;
            ppjp = false;
        } else {
            ppjp = true;
        }
        return new JSP("lelong/maklumat_pihak_berkepentingan.jsp").addParameter("tab", "true");
    }

    public Resolution suku() {
        ppjp = false;
        return new JSP("lelong/maklumat_pihak_berkepentingan.jsp").addParameter("tab", "true");
    }

    public Resolution sukuView() {
        viewnama = true;
        iDSerah = false;
        return new JSP("lelong/maklumat_pihak_berkepentingan.jsp").addParameter("tab", "true");
    }

    public Resolution showForm4() {

        if (permohonan.getPermohonanSebelum() != null) {
        } else if (permohonan.getPermohonanSebelum() == null) {
            addSimpleError("Sila Pilih Urusan Di Tab Senarai Urusan Terlebih Dahulu...");
            view = true;
            return new JSP("lelong/maklumat_pihak_berkepentingan.jsp").addParameter("tab", "true");
        }
        rehydrate();
        tangguhbatale = true;
        if (permohonan.getPermohonanSebelum() != null && permohonan.getPermohonanSebelum().getKodUrusan().getKod().equals("PJTA")) {
            viewnama = true;
            ppjp = false;
        } else {
            ppjp = true;
        }

        return new JSP("lelong/maklumat_pihak_berkepentingan.jsp").addParameter("tab", "true");
    }

    public Resolution tambahAlamat() {
        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        logger.info("idPermohonan = " + idPermohonan);
        listHakmilikMohon = lelongService.getPermohonanByIdmohon(idPermohonan);
        idPihak = getContext().getRequest().getParameter("nama");
        logger.info(idPihak);
        pihak = lelongService.getPihakID(idPihak);
        logger.info("listHakmilikMohon.size()=-----" + listHakmilikMohon.size());
        if (listHakmilikMohon.size() == 1) {
            logger.info("ni 1 hakmilik");
            if (permohonan.getPermohonanSebelum() != null) {
                mohonPihak = lelongService.getPihakIDinMohonPihak(permohonan.getPermohonanSebelum().getIdPermohonan(), idPihak);
                hakmilikPihakBerkepentingan = lelongService.getHakmilikPihakBerkepentingan1(idPihak, permohonan.getPermohonanSebelum().getIdPermohonan());
            } else {
                mohonPihak = lelongService.getPihakIDinMohonPihak2(idPermohonan, idPihak, listHakmilikMohon.get(0).getHakmilik().getIdHakmilik());
                hakmilikPihakBerkepentingan = lelongService.getHakmilikPihakBerkepentingan1(idPihak, idPermohonan);
            }
        } else {
            logger.info("ni lebih 1 hakmilik");
            if (permohonan.getPermohonanSebelum() != null) {
                senaraiPermohonanPihak3 = lelongService.getSenaraiPmohonPihakByPihak(Long.parseLong(idPihak), permohonan.getPermohonanSebelum().getIdPermohonan());
                listHakmilikPihakBerkepentingan = lelongService.getlistPihak(permohonan.getPermohonanSebelum().getIdPermohonan(), Long.parseLong(idPihak));
            } else {
                senaraiPermohonanPihak3 = lelongService.getSenaraiPmohonPihakByPihak(Long.parseLong(idPihak), idPermohonan);
                listHakmilikPihakBerkepentingan = lelongService.getlistPihak(idPermohonan, Long.parseLong(idPihak));
            }
            for (PermohonanPihak mpihak : senaraiPermohonanPihak3) {
                logger.info("mpihak");
                mohonPihak = mpihak;
            }
            for (HakmilikPihakBerkepentingan hpb : listHakmilikPihakBerkepentingan) {
                logger.info("hpb");
                hakmilikPihakBerkepentingan = hpb;
            }

        }

        if ("05".equals(conf.getProperty("kodNegeri"))) {
            negeri = false;
        }

        pihakAlamatTamb = lelongService.getPihakAlamatTamb(pihak.getIdPihak());
        idPihak = String.valueOf(pihak.getIdPihak());
        return new JSP("lelong/tambah_alamat.jsp").addParameter("popup", "true");
    }

    public Resolution tambahSuku() {
        String idPihak = getContext().getRequest().getParameter("nama");
        logger.info(idPihak);
        pihak = lelongService.getPihakID(idPihak);
        return new JSP("lelong/tambahsukudalamPihak.jsp").addParameter("popup", "true");
    }

    //added by nur.aqilah
    //delete alamat tambahan
    public Resolution deleteAlamat() {

        logger.info("delete alamat tambahan");
        idPihak = getContext().getRequest().getParameter("idPihak");
        logger.info("idPihak : " + idPihak);
        pihak = lelongService.getPihakID(idPihak);

        String alamat = getContext().getRequest().getParameter("alamat");
        logger.info(alamat);
        if (alamat.equals("ketiga")) {
            pihakAlamatTamb = lelongService.getPihakAlamatTamb(pihak.getIdPihak());
            pihakAlamatTamb.setAlamatKetiga1("");
            pihakAlamatTamb.setAlamatKetiga2("");
            pihakAlamatTamb.setAlamatKetiga3("");
            pihakAlamatTamb.setAlamatKetiga4("");
            pihakAlamatTamb.setAlamatKetigaNegeri(null);
            pihakAlamatTamb.setAlamatKetigaPoskod(null);
        } else if (alamat.equals("keempat")) {
            pihakAlamatTamb = lelongService.getPihakAlamatTamb(pihak.getIdPihak());
            pihakAlamatTamb.setAlamatKeempat1("");
            pihakAlamatTamb.setAlamatKeempat2("");
            pihakAlamatTamb.setAlamatKeempat3("");
            pihakAlamatTamb.setAlamatKeempat4("");
            pihakAlamatTamb.setAlamatKeempatNegeri(null);
            pihakAlamatTamb.setAlamatKeempatPoskod(null);
        } else if (alamat.equals("kelima")) {
            pihakAlamatTamb = lelongService.getPihakAlamatTamb(pihak.getIdPihak());
            pihakAlamatTamb.setAlamatKelima1("");
            pihakAlamatTamb.setAlamatKelima2("");
            pihakAlamatTamb.setAlamatKelima3("");
            pihakAlamatTamb.setAlamatKelima4("");
            pihakAlamatTamb.setAlamatKelimaNegeri(null);
            pihakAlamatTamb.setAlamatKelimaPoskod(null);
        }

        lelongService.saveOrUpdate(pihakAlamatTamb);

        addSimpleMessage("Alamat Tambahan Berjaya Di Hapus");
        return new JSP("lelong/tambah_alamat.jsp").addParameter("tab", "true");
    }

    public Resolution tambahSukuview() {
        String idPihak = getContext().getRequest().getParameter("nama");
        logger.info(idPihak);
        pihak = lelongService.getPihakID(idPihak);
        return new JSP("lelong/tambahsukudalamPihak_view.jsp").addParameter("popup", "true");
    }

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        pengguna = (Pengguna) context.getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);

        if (idPermohonan != null) {
            permohonan = permohonanDAO.findById(idPermohonan);

            if (permohonan.getPermohonanSebelum() == null) {
                hakmilikPermohonanList = lelongService.getPermohonanByIdmohon(idPermohonan);
                enkuiri = lelongService.findEnkuiri(idPermohonan);
                listNoPerserahan = lelongService.getPermohonanAtasPerserahan(idPermohonan);
            } else {
                List<PermohonanAsal> list = lelongService.listMohonAsal2(permohonan.getPermohonanSebelum().getIdPermohonan());
                if (list.isEmpty()) {
                    listNoPerserahan = lelongService.getPermohonanAtasPerserahan(permohonan.getPermohonanSebelum().getIdPermohonan());
                    enkuiri = lelongService.findEnkuiri(permohonan.getPermohonanSebelum().getIdPermohonan());
                    hakmilikPermohonanList = lelongService.getPermohonanByIdmohon(permohonan.getPermohonanSebelum().getIdPermohonan());
                    enkuiriSBlm = enkuiri;
                } else {
                    listNoPerserahan = lelongService.getPermohonanAtasPerserahan(list.get(0).getIdPermohonanAsal().getIdPermohonan());
                    enkuiri = lelongService.findEnkuiri(list.get(0).getIdPermohonanAsal().getIdPermohonan());
                    hakmilikPermohonanList = lelongService.getPermohonanByIdmohon(list.get(0).getIdPermohonanAsal().getIdPermohonan());
                    enkuiriSBlm = enkuiri;
                }
            }

            if (!listNoPerserahan.isEmpty()) {
                for (PermohonanAtasPerserahan pp : listNoPerserahan) {
                    logger.info("pp : " + pp.getUrusan());
                    perserahanGadaian = pp.getUrusan().getIdPerserahan();
                }

                hakmilikPermohonanList = lelongService.getPermohonanByIdmohon(idPermohonan);
                listHakmilikPihakBerkepentingan = lelongService.getHakmilikPihakBerkepentingan(idPermohonan);

                //lps pilih
                List<PermohonanPihak> listPP = null;
                if (permohonan.getPermohonanSebelum() == null) {
                    listPP = lelongService.getSenaraiPmohonPihak(idPermohonan);
                } else {
                    List<PermohonanAsal> list2 = lelongService.listMohonAsal2(permohonan.getPermohonanSebelum().getIdPermohonan());
                    if (list2.isEmpty()) {
                        listPP = lelongService.getSenaraiPmohonPihak(permohonan.getPermohonanSebelum().getIdPermohonan());
                    } else {
                        listPP = lelongService.getSenaraiPmohonPihak(list2.get(0).getIdPermohonanAsal().getIdPermohonan());
                    }
                }
                Map<Long, PermohonanPihak> pihakMap2 = new HashMap<Long, PermohonanPihak>();
                for (int i = 0; i < listPP.size(); i++) {
                    Long id = listPP.get(i).getPihak().getIdPihak();
                    if (pihakMap2.containsKey(id)) {
                        continue;
                    } else {
                        pihakMap2.put(id, listPP.get(i));
                    }
                }
                List<PermohonanPihak> senarai = new ArrayList<PermohonanPihak>(pihakMap2.values());
                sortList(senarai);
                listIDHakmilik2 = new ArrayList<String>();
                for (PermohonanPihak hoi : senaraiPermohonanPihak3) {
                    if (permohonan.getPermohonanSebelum() == null) {
                        listPP = lelongService.getSenaraiPmohonPihakByPihak(hoi.getPihak().getIdPihak(), idPermohonan);
                    } else {
                        List<PermohonanAsal> list2 = lelongService.listMohonAsal2(permohonan.getPermohonanSebelum().getIdPermohonan());
                        if (list2.isEmpty()) {
                            listPP = lelongService.getSenaraiPmohonPihakByPihak(hoi.getPihak().getIdPihak(), permohonan.getPermohonanSebelum().getIdPermohonan());
                        } else {
                            listPP = lelongService.getSenaraiPmohonPihakByPihak(hoi.getPihak().getIdPihak(), list2.get(0).getIdPermohonanAsal().getIdPermohonan());
                        }
                    }
                    if (!listPP.isEmpty()) {
                        StringBuilder sb = new StringBuilder();
                        for (PermohonanPihak hh : listPP) {
                            if (hh == null) {
                                continue;
                            }
                            Hakmilik h = hh.getHakmilik();
                            if (sb.length() > 0) {
                                sb.append("<br>");
                            }
                            if (!h.getKodStatusHakmilik().getKod().equals("B")) {
                                sb.append(h.getIdHakmilik());
                            }
                        }
                        listIDHakmilik2.add(sb.toString());
                    }
                }

            }
        }
    }

    public void sortList(List<PermohonanPihak> pp) {
        senaraiPermohonanPihak3 = new ArrayList<PermohonanPihak>();
        String idHakmilik = hakmilikPermohonanList.get(0).getHakmilik().getIdHakmilik();
        String idMohon = permohonan.getIdPermohonan();
        if (permohonan.getKodUrusan().getKod().equals("PPBL")) {
            List<PermohonanPihak> listMp = regService.findMohonPihakByHakmilikAndIdMohon(idHakmilik, permohonan.getPermohonanSebelum().getIdPermohonan());
            boolean a = true;
            do {
                for (PermohonanPihak ps : listMp) {
                    if (ps.getJenis().getKod().equals("PG")) {
//                    String idHakmilikMp = ps.getHakmilik().getIdHakmilik();
//                    if (idHakmilikMp == idHakmilik) {
                        senaraiPermohonanPihak3.add(ps);
                        a = false;
                        pp.remove(ps);
                        break;
//                    }
                    }
                }
            } while (a != false);
            for (PermohonanPihak permohonanPihak : listMp) {
                if (permohonanPihak.getJenis().getKod().equals("PM")) {
                    senaraiPermohonanPihak3.add(permohonanPihak);
                }
            }
        } else {
            List<PermohonanPihak> listMp = regService.findMohonPihakByHakmilikAndIdMohon(idHakmilik, idMohon);
            boolean a = true;

            for (PermohonanPihak ps : listMp) {
                if (ps.getJenis().getKod().equals("PG")) {
//                    String idHakmilikMp = ps.getHakmilik().getIdHakmilik();
//                    if (idHakmilikMp == idHakmilik) {
                    senaraiPermohonanPihak3.add(ps);
                    a = false;
                    pp.remove(ps);
                    break;
//                    }
                }
            }

            for (PermohonanPihak permohonanPihak : listMp) {
                if (permohonanPihak.getJenis().getKod().equals("PM")) {
                    senaraiPermohonanPihak3.add(permohonanPihak);
                }
            }
        }

    }

    public Resolution simpanSuku() {
        logger.info("-----simpan Suku-----");
        String idPihak2 = getContext().getRequest().getParameter("namaPihak");
        logger.info(idPihak2);
        Pihak pp = lelongService.getPihakID(idPihak2);
        logger.info("Pihak : " + pp.getNama());
        InfoAudit ia = new InfoAudit();

        KodSuku ks = new KodSuku();
        if (pp != null) {
            ks = kodSukuDAO.findById(pihak.getSuku().getKod());
            pp.setSuku(ks);
            pp.setSubSuku(pihak.getSubSuku());
            pp.setKeturunan(pihak.getKeturunan());
            pp.setTempatSuku(pihak.getTempatSuku());

            ia.setDimasukOleh(pengguna);
            ia.setTarikhMasuk(new java.util.Date());
            ia.setDiKemaskiniOleh(pengguna);
            ia.setTarikhKemaskini(new java.util.Date());
            pp.setInfoAudit(ia);

        }
        lelongService.saveOrUpdate(pp);
        addSimpleMessage("Maklumat Telah Berjaya DiSimpan...");
        return new JSP("lelong/maklumat_pihak_berkepentingan.jsp").addParameter("tab", "true");
    }

    public Resolution simpanAlamat() {
        logger.info("-------simpanAlamat-------s");
        String idPihak2 = getContext().getRequest().getParameter("namaPihak");
        String alamatMp1 = getContext().getRequest().getParameter("mohonPihak.alamat.alamat1");
        String alamatMp2 = getContext().getRequest().getParameter("mohonPihak.alamat.alamat2");
        String alamatMp3 = getContext().getRequest().getParameter("mohonPihak.alamat.alamat3");
        String alamatMp4 = getContext().getRequest().getParameter("mohonPihak.alamat.alamat4");
        String poskodMp1 = getContext().getRequest().getParameter("mohonPihak.alamat.poskod");
        String negeriMp1 = getContext().getRequest().getParameter("mohonPihak.alamat.negeri.kod");

        String alamatSurat1 = getContext().getRequest().getParameter("mohonPihak.alamatSurat.alamatSurat1");
        String alamatSurat2 = getContext().getRequest().getParameter("mohonPihak.alamatSurat.alamatSurat2");
        String alamatSurat3 = getContext().getRequest().getParameter("mohonPihak.alamatSurat.alamatSurat3");
        String alamatSurat4 = getContext().getRequest().getParameter("mohonPihak.alamatSurat.alamatSurat4");
        String poskodSurat = getContext().getRequest().getParameter("mohonPihak.alamatSurat.poskodSurat");
        String negeriSurat = getContext().getRequest().getParameter("mohonPihak.alamatSurat.negeriSurat.kod");

        logger.info(idPihak2);
        Pihak pp = lelongService.getPihakID(idPihak2);
        if (permohonan.getSenaraiHakmilik().size() == 1) {
            logger.info("ni 1 hakmilik");
            senaraiPermohonanPihak3 = lelongService.getSenaraiPmohonPihakByPihak(Long.parseLong(idPihak2), idPermohonan);
//            PermohonanPihak mpihak = lelongService.getPihakIDinMohonPihak(idPermohonan, idPihak2);
            logger.info("Pihak : " + pp.getNama());
            InfoAudit ia = new InfoAudit();
            PihakAlamatTamb ptb = lelongService.getPihakAlamatTamb(pp.getIdPihak());
            KodNegeri kodN = new KodNegeri();

            for (PermohonanPihak mpihak : senaraiPermohonanPihak3) {
                logger.info("mpihak");
                mohonPihak = mpihak;
                logger.info("Pihak : " + pp.getNama());
                if (ptb == null) {
                    logger.info("----nulll-------");
                    ptb = new PihakAlamatTamb();
                    ia.setDimasukOleh(pengguna);
                    ia.setTarikhMasuk(new java.util.Date());
                } else {
                    logger.info("------tak null-------");
                    ia.setDimasukOleh(pengguna);
                    ia.setTarikhMasuk(new java.util.Date());
                    ia.setDiKemaskiniOleh(pengguna);
                    ia.setTarikhKemaskini(new java.util.Date());
                    mpihak.setInfoAudit(ia);
                }

                if (mpihak != null) {
                    ia.setDiKemaskiniOleh(pengguna);
                    ia.setTarikhKemaskini(new java.util.Date());
                    mpihak.setInfoAudit(ia);
                }

                lelongService.saveOrUpdate(mpihak);
            }
            if (mohonPihak.getAlamat() != null) {
                Alamat alamatBaru = mohonPihak.getAlamat();
                if (alamatMp1 != null) {
                    alamatBaru.setAlamat1(alamatMp1);
                }
                if (alamatMp2 != null) {
                    alamatBaru.setAlamat2(alamatMp2);
                }
                if (alamatMp3 != null) {
                    alamatBaru.setAlamat3(alamatMp3);
                }
                if (alamatMp4 != null) {
                    alamatBaru.setAlamat1(alamatMp4);
                }
                if (poskodMp1 != null) {
                    alamatBaru.setPoskod(poskodMp1);
                }
                if (negeriMp1 != null) {
                    KodNegeri kodNBaru = kodNegeriDAO.findById(negeriMp1);
                    alamatBaru.setNegeri(kodNBaru);

                }
                mohonPihak.setAlamat(alamatBaru);
                lelongService.saveOrUpdate(mohonPihak);
            }
            if (mohonPihak.getAlamatSurat() != null) {
                AlamatSurat alamatSurat = mohonPihak.getAlamatSurat();
                if (alamatSurat1 != null) {
                    alamatSurat.setAlamatSurat1(alamatSurat1);
                }
                if (alamatSurat2 != null) {
                    alamatSurat.setAlamatSurat2(alamatSurat2);
                }
                if (alamatSurat3 != null) {
                    alamatSurat.setAlamatSurat3(alamatSurat3);
                }
                if (alamatSurat4 != null) {
                    alamatSurat.setAlamatSurat4(alamatSurat4);
                }
                if (poskodSurat != null) {
                    alamatSurat.setPoskodSurat(poskodSurat);
                }
                if (negeriSurat != null) {
                    KodNegeri kodNSurat = kodNegeriDAO.findById(negeriSurat);
                    alamatSurat.setNegeriSurat(kodNSurat);
                }
                mohonPihak.setAlamatSurat(alamatSurat);
                lelongService.saveOrUpdate(mohonPihak);
            }
            if (mohonPihak.getJenis().getKod().equals("PG")) {
                ptb.setAlamatKetiga1(pihakAlamatTamb.getAlamatKetiga1());
                ptb.setAlamatKetiga2(pihakAlamatTamb.getAlamatKetiga2());
                ptb.setAlamatKetiga3(pihakAlamatTamb.getAlamatKetiga3());
                ptb.setAlamatKetiga4(pihakAlamatTamb.getAlamatKetiga4());
                ptb.setAlamatKetigaPoskod(pihakAlamatTamb.getAlamatKetigaPoskod());
                kodN = kodNegeriDAO.findById(pihakAlamatTamb.getAlamatKetigaNegeri().getKod());
                ptb.setAlamatKetigaNegeri(kodN);
            } else {
                ptb.setAlamatKetiga1(pihakAlamatTamb.getAlamatKetiga1());
                ptb.setAlamatKetiga2(pihakAlamatTamb.getAlamatKetiga2());
                ptb.setAlamatKetiga3(pihakAlamatTamb.getAlamatKetiga3());
                ptb.setAlamatKetiga4(pihakAlamatTamb.getAlamatKetiga4());
                ptb.setAlamatKetigaPoskod(pihakAlamatTamb.getAlamatKetigaPoskod());
                kodN = kodNegeriDAO.findById(pihakAlamatTamb.getAlamatKetigaNegeri().getKod());
                ptb.setAlamatKetigaNegeri(kodN);
                ptb.setAlamatKeempat1(pihakAlamatTamb.getAlamatKeempat1());
                ptb.setAlamatKeempat2(pihakAlamatTamb.getAlamatKeempat2());
                ptb.setAlamatKeempat3(pihakAlamatTamb.getAlamatKeempat3());
                ptb.setAlamatKeempat4(pihakAlamatTamb.getAlamatKeempat4());
                ptb.setAlamatKeempatPoskod(pihakAlamatTamb.getAlamatKeempatPoskod());
                kodN = kodNegeriDAO.findById(pihakAlamatTamb.getAlamatKeempatNegeri().getKod());
                ptb.setAlamatKeempatNegeri(kodN);
                ptb.setAlamatKelima1(pihakAlamatTamb.getAlamatKelima1());
                ptb.setAlamatKelima2(pihakAlamatTamb.getAlamatKelima2());
                ptb.setAlamatKelima3(pihakAlamatTamb.getAlamatKelima3());
                ptb.setAlamatKelima4(pihakAlamatTamb.getAlamatKelima4());
                ptb.setAlamatKelimaPoskod(pihakAlamatTamb.getAlamatKelimaPoskod());
                kodN = kodNegeriDAO.findById(pihakAlamatTamb.getAlamatKelimaNegeri().getKod());
                ptb.setAlamatKelimaNegeri(kodN);
                ptb.setInfoAudit(ia);
                ptb.setPihak(pp);
            }

            ptb.setInfoAudit(ia);
            ptb.setPihak(pp);
            lelongService.saveOrUpdate(ptb);

        } else {
            logger.info("ni lebih 1 hakmilik");
            senaraiPermohonanPihak3 = lelongService.getSenaraiPmohonPihakByPihak(Long.parseLong(idPihak2), idPermohonan);
            KodNegeri kodN = new KodNegeri();
            PihakAlamatTamb ptb = lelongService.getPihakAlamatTamb(pp.getIdPihak());
            InfoAudit ia = new InfoAudit();
            for (PermohonanPihak mpihak : senaraiPermohonanPihak3) {
                logger.info("mpihak");
                mohonPihak = mpihak;
                logger.info("Pihak : " + pp.getNama());
//                InfoAudit ia = new InfoAudit();
//                PihakAlamatTamb ptb = lelongService.getPihakAlamatTamb(pp.getIdPihak());
//                KodNegeri kodN = new KodNegeri();
                if (ptb == null) {
                    logger.info("----nulll-------");
                    ptb = new PihakAlamatTamb();
                    ia.setDimasukOleh(pengguna);
                    ia.setTarikhMasuk(new java.util.Date());
                } else {
                    logger.info("------tak null-------");
                    ia.setDimasukOleh(pengguna);
                    ia.setTarikhMasuk(new java.util.Date());
                    ia.setDiKemaskiniOleh(pengguna);
                    ia.setTarikhKemaskini(new java.util.Date());
                    mpihak.setInfoAudit(ia);
                }

                if (mpihak != null) {
//            mpihak.setAlamat(mohonPihak.getAlamat());
//            mpihak.setAlamatSurat(mohonPihak.getAlamatSurat());
                    ia.setDiKemaskiniOleh(pengguna);
                    ia.setTarikhKemaskini(new java.util.Date());
                    mpihak.setInfoAudit(ia);
                }

                lelongService.saveOrUpdate(mpihak);
            }
            if (mohonPihak.getJenis().getKod().equals("PG")) {
                ptb.setAlamatKetiga1(pihakAlamatTamb.getAlamatKetiga1());
                ptb.setAlamatKetiga2(pihakAlamatTamb.getAlamatKetiga2());
                ptb.setAlamatKetiga3(pihakAlamatTamb.getAlamatKetiga3());
                ptb.setAlamatKetiga4(pihakAlamatTamb.getAlamatKetiga4());
                ptb.setAlamatKetigaPoskod(pihakAlamatTamb.getAlamatKetigaPoskod());
                kodN = kodNegeriDAO.findById(pihakAlamatTamb.getAlamatKetigaNegeri().getKod());
                ptb.setAlamatKetigaNegeri(kodN);
            } else {
                ptb.setAlamatKetiga1(pihakAlamatTamb.getAlamatKetiga1());
                ptb.setAlamatKetiga2(pihakAlamatTamb.getAlamatKetiga2());
                ptb.setAlamatKetiga3(pihakAlamatTamb.getAlamatKetiga3());
                ptb.setAlamatKetiga4(pihakAlamatTamb.getAlamatKetiga4());
                ptb.setAlamatKetigaPoskod(pihakAlamatTamb.getAlamatKetigaPoskod());
                kodN = kodNegeriDAO.findById(pihakAlamatTamb.getAlamatKetigaNegeri().getKod());
                ptb.setAlamatKetigaNegeri(kodN);
                ptb.setAlamatKeempat1(pihakAlamatTamb.getAlamatKeempat1());
                ptb.setAlamatKeempat2(pihakAlamatTamb.getAlamatKeempat2());
                ptb.setAlamatKeempat3(pihakAlamatTamb.getAlamatKeempat3());
                ptb.setAlamatKeempat4(pihakAlamatTamb.getAlamatKeempat4());
                ptb.setAlamatKeempatPoskod(pihakAlamatTamb.getAlamatKeempatPoskod());
                kodN = kodNegeriDAO.findById(pihakAlamatTamb.getAlamatKeempatNegeri().getKod());
                ptb.setAlamatKeempatNegeri(kodN);
                ptb.setAlamatKelima1(pihakAlamatTamb.getAlamatKelima1());
                ptb.setAlamatKelima2(pihakAlamatTamb.getAlamatKelima2());
                ptb.setAlamatKelima3(pihakAlamatTamb.getAlamatKelima3());
                ptb.setAlamatKelima4(pihakAlamatTamb.getAlamatKelima4());
                ptb.setAlamatKelimaPoskod(pihakAlamatTamb.getAlamatKelimaPoskod());
                kodN = kodNegeriDAO.findById(pihakAlamatTamb.getAlamatKelimaNegeri().getKod());
                ptb.setAlamatKelimaNegeri(kodN);
            }
            ptb.setInfoAudit(ia);
            ptb.setPihak(pp);

            lelongService.saveOrUpdate(ptb);

//            }
        }
        addSimpleMessage("Maklumat Telah Berjaya DiSimpan...");
        return new JSP("lelong/maklumat_pihak_berkepentingan.jsp").addParameter("tab", "true");
    }

    public Resolution refreshpage() {

        rehydrate();
        addSimpleMessage("Maklumat Telah Berjaya Disimpan...");
        return new JSP("lelong/maklumat_pihak_berkepentingan.jsp").addParameter("tab", "true");
    }

    public Resolution deleteId() {

        String id = getContext().getRequest().getParameter("id");
        PermohonanAtasPerserahan pap = permohonanAtasPerserahanDAO.findById(Long.parseLong(id));
        lelongService.delete(pap);
        rehydrate();
        return new JSP("lelong/maklumat_pihak_berkepentingan.jsp").addParameter("tab", "true");
    }

    public Pihak getPihak() {
        return pihak;
    }

    public void setPihak(Pihak pihak) {
        this.pihak = pihak;
    }

    public Permohonan getPermohonan() {
        return permohonan;
    }

    public void setPermohonan(Permohonan permohonan) {
        this.permohonan = permohonan;
    }

    public List<HakmilikPermohonan> getHakmilikPermohonanList() {
        return hakmilikPermohonanList;
    }

    public void setHakmilikPermohonanList(List<HakmilikPermohonan> hakmilikPermohonanList) {
        this.hakmilikPermohonanList = hakmilikPermohonanList;
    }

    public List<HakmilikPihakBerkepentingan> getPihakKepentinganList() {
        return pihakKepentinganList;
    }

    public void setPihakKepentinganList(List<HakmilikPihakBerkepentingan> pihakKepentinganList) {
        this.pihakKepentinganList = pihakKepentinganList;
    }

    public HakmilikPihakBerkepentingan getHakmilikPihakBerkepentingan() {
        return hakmilikPihakBerkepentingan;
    }

    public void setHakmilikPihakBerkepentingan(HakmilikPihakBerkepentingan hakmilikPihakBerkepentingan) {
        this.hakmilikPihakBerkepentingan = hakmilikPihakBerkepentingan;
    }

    public String getIdPermohonan() {
        return idPermohonan;
    }

    public void setIdPermohonan(String idPermohonan) {
        this.idPermohonan = idPermohonan;
    }

    public Enkuiri getEnkuiri() {
        return enkuiri;
    }

    public void setEnkuiri(Enkuiri enkuiri) {
        this.enkuiri = enkuiri;
    }

    public String getPerserahanGadaian() {
        return perserahanGadaian;
    }

    public void setPerserahanGadaian(String perserahanGadaian) {
        this.perserahanGadaian = perserahanGadaian;
    }

    public boolean isView() {
        return view;
    }

    public void setView(boolean view) {
        this.view = view;
    }

    public PihakAlamatTamb getPihakAlamatTamb() {
        return pihakAlamatTamb;
    }

    public void setPihakAlamatTamb(PihakAlamatTamb pihakAlamatTamb) {
        this.pihakAlamatTamb = pihakAlamatTamb;
    }

    public Enkuiri getEnkuiriSBlm() {
        return enkuiriSBlm;
    }

    public void setEnkuiriSBlm(Enkuiri enkuiriSBlm) {
        this.enkuiriSBlm = enkuiriSBlm;
    }

    public boolean isTangguhbatale() {
        return tangguhbatale;
    }

    public void setTangguhbatale(boolean tangguhbatale) {
        this.tangguhbatale = tangguhbatale;
    }

    public List<HakmilikUrusan> getListHakmilikUrusan() {
        return listHakmilikUrusan;
    }

    public void setListHakmilikUrusan(List<HakmilikUrusan> listHakmilikUrusan) {
        this.listHakmilikUrusan = listHakmilikUrusan;
    }

    public List<HakmilikPihakBerkepentingan> getListHakmilikPihakBerkepentingan() {
        return listHakmilikPihakBerkepentingan;
    }

    public void setListHakmilikPihakBerkepentingan(List<HakmilikPihakBerkepentingan> listHakmilikPihakBerkepentingan) {
        this.listHakmilikPihakBerkepentingan = listHakmilikPihakBerkepentingan;
    }

    public List<PermohonanPihak> getSenaraiPermohonanPihak3() {
        return senaraiPermohonanPihak3;
    }

    public void setSenaraiPermohonanPihak3(List<PermohonanPihak> senaraiPermohonanPihak3) {
        this.senaraiPermohonanPihak3 = senaraiPermohonanPihak3;
    }

    public List<String> getListidHakmilik() {
        return listidHakmilik;
    }

    public void setListidHakmilik(List<String> listidHakmilik) {
        this.listidHakmilik = listidHakmilik;
    }

    public boolean isiDSerah() {
        return iDSerah;
    }

    public void setiDSerah(boolean iDSerah) {
        this.iDSerah = iDSerah;
    }

    public String getIdHakmilik() {
        return idHakmilik;
    }

    public void setIdHakmilik(String idHakmilik) {
        this.idHakmilik = idHakmilik;
    }

    public List<String> getListIDHakmilik() {
        return listIDHakmilik;
    }

    public void setListIDHakmilik(List<String> listIDHakmilik) {
        this.listIDHakmilik = listIDHakmilik;
    }

    public List<String> getListIDHakmilik2() {
        return listIDHakmilik2;
    }

    public void setListIDHakmilik2(List<String> listIDHakmilik2) {
        this.listIDHakmilik2 = listIDHakmilik2;
    }

    public Pengguna getPengguna() {
        return pengguna;
    }

    public void setPengguna(Pengguna pengguna) {
        this.pengguna = pengguna;
    }

    public boolean isPpjp() {
        return ppjp;
    }

    public void setPpjp(boolean ppjp) {
        this.ppjp = ppjp;
    }

    public boolean isViewnama() {
        return viewnama;
    }

    public void setViewnama(boolean viewnama) {
        this.viewnama = viewnama;
    }

    public List<PermohonanAtasPerserahan> getListNoPerserahan() {
        return listNoPerserahan;
    }

    public void setListNoPerserahan(List<PermohonanAtasPerserahan> listNoPerserahan) {
        this.listNoPerserahan = listNoPerserahan;
    }

    public String getTamatGadaian() {
        return tamatGadaian;
    }

    public void setTamatGadaian(String tamatGadaian) {
        this.tamatGadaian = tamatGadaian;
    }

    public String getTempohGadaian() {
        return tempohGadaian;
    }

    public void setTempohGadaian(String tempohGadaian) {
        this.tempohGadaian = tempohGadaian;
    }

    public PermohonanPihak getMohonPihak() {
        return mohonPihak;
    }

    public void setMohonPihak(PermohonanPihak mohonPihak) {
        this.mohonPihak = mohonPihak;
    }

    public String getIdPihak() {
        return idPihak;
    }

    public void setIdPihak(String idPihak) {
        this.idPihak = idPihak;
    }

    public boolean isNegeri() {
        return negeri;
    }

    public void setNegeri(boolean negeri) {
        this.negeri = negeri;
    }
}
