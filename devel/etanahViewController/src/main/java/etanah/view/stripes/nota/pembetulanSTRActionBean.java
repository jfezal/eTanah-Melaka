/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.stripes.nota;

import able.stripes.*;
import com.google.inject.Inject;
import etanah.dao.*;
import etanah.model.*;
import etanah.sequence.GeneratorIdPerserahanPaksa;
import etanah.service.*;
import etanah.model.strata.*;
import etanah.service.common.*;
import etanah.service.daftar.*;
import etanah.view.*;
import java.math.*;
import java.text.*;
import java.util.logging.Level;
import net.sourceforge.stripes.action.*;
import net.sourceforge.stripes.controller.LifecycleStage;
import org.apache.log4j.Logger;
import java.util.*;
import org.apache.commons.lang.StringUtils;

@UrlBinding("/daftar/pembetulan/betulSTR")
public class pembetulanSTRActionBean extends AbleActionBean {

    @Inject
    FolderDokumenDAO folderDokumenDAO;
    @Inject
    KodNegeriDAO kodNegeriDAO;
    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    HakmilikDAO hakmilikDAO;
    @Inject
    BadanPengurusanDAO badanPengurusanDAO;
    @Inject
    PermohonanPihakKemaskiniDAO permohonanPihakKemaskiniDAO;
    @Inject
    PihakDAO pihakDAO;
    @Inject
    PembetulanService pService;
    @Inject
    StrataPtService strService;
    private String idPermohonan;
    private String noFailSTR;
    private String tarikhDaftarSTR;
    private String nopuSTR;
    private String noBukuDaftarSTR;
    private String noSkimSTR;
    private String jumSyerSTR;
    private String namaBdnUrus;
    private String alamat1BdnUrus;
    private String petakAksrSTR;
    private String unitSyerSTR;
    private String noPelanSTR;
    private String luasPetakSTR;
    private String luasPetakAksrSTR;
    private String status;
    private String noPelanPetakSTR;
    private String lokasiSTR;
    private String alamat2BdnUrus;
    private String alamat3BdnUrus;
    private String alamat4BdnUrus;
    private String poskodBdnUrus;
    private String negeriBdnUrus;
    private String alamat1SuratBdnUrus;
    private String alamat2SuratBdnUrus;
    private String alamat3SuratBdnUrus;
    private String alamat4SuratBdnUrus;
    private String poskodSuratBdnUrus;
    private String negeriSuratBdnUrus;
    private String idH;
    private String noPetak;
    private String noTingkatSTR;
    private Pengguna peng;
    private Permohonan permohonan;
    private Hakmilik hakmilik;
    private Pihak badanUrus;
    private HakmilikPermohonan mohonHakmilik;
    private PermohonanPihakKemaskini permohonanPihakKemaskini;
    private List<HakmilikPermohonan> hakmilikPermohonanList;
    private List<PermohonanPihakKemaskini> permohonanPihakKemaskiniList;
    private List<PermohonanPihakKemaskini> permohonanPihakKemaskiniPetakAksr;
    private List<PermohonanPihakKemaskini> permohonanPihakKemaskiniNoPelan;
    private List<HakmilikPetakAksesori> listHakmilikPetakAksesori;
    private String noPelanList;
    private String noPetakAksrList;
    private String noPelanPetak;
    private String idKini;
    private String cukai;
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    private static final Logger LOG = Logger.getLogger(pembetulanSTRActionBean.class);
    private static boolean isDebug = LOG.isDebugEnabled();

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        permohonan = permohonanDAO.findById(idPermohonan);
        hakmilikPermohonanList = permohonan.getSenaraiHakmilik();

        List<PermohonanPihakKemaskini> kinicukaiPetakSTR = pService.findListbyIdMohonIdHmMedan(idPermohonan,
                hakmilikPermohonanList.get(0).getHakmilik().getIdHakmilik(), "cukaiPetakSTR");

        SkimStrata skimStrata = strService.findSkimStrata(hakmilikPermohonanList.get(0).getHakmilik().getIdHakmilikInduk() != null
                ? hakmilikPermohonanList.get(0).getHakmilik().getIdHakmilikInduk() : hakmilikPermohonanList.get(0).getHakmilik().getIdHakmilik());

        InfoAudit ia = new InfoAudit();
        ia.setDimasukOleh(peng);
        ia.setTarikhMasuk(new java.util.Date());
        InfoAudit ia2 = new InfoAudit();
        ia.setDiKemaskiniOleh(peng);
        ia.setTarikhKemaskini(new java.util.Date());

        if (skimStrata != null) {
            CukaiPetak cukaiPetak = strService.findCukaiPetak(skimStrata.getKategoriBangunan().getKod(), skimStrata.getKelasTanah().getKod());
            List<PermohonanPihakKemaskini> kinipetakAksrSTR = pService.findListbyIdMohonIdHmMedan(idPermohonan,
                    hakmilikPermohonanList.get(0).getHakmilik().getIdHakmilik(), "petakAksrSTR");
            List<PermohonanPihakKemaskini> kiniluasPetakSTR = pService.findListbyIdMohonIdHmMedan(idPermohonan,
                    hakmilikPermohonanList.get(0).getHakmilik().getIdHakmilik(), "luasPetakSTR");

            BigDecimal luas = BigDecimal.ZERO;
            //String luas1;

            for (PermohonanPihakKemaskini aksr : kinipetakAksrSTR) {
                if (aksr.getNilaiBerkaitan2() != null) {
                    luas = luas.add(BigDecimal.valueOf(Double.valueOf(aksr.getNilaiBerkaitan2())));
                }
            }

            if (kiniluasPetakSTR.size() <= 0) {
                luas = luas.add(hakmilikPermohonanList.get(0).getHakmilik().getLuas());
            } else {

                for (PermohonanPihakKemaskini petak : kiniluasPetakSTR) {
                    if (petak.getNilaiBaru() != null) {
                        luas = luas.add(BigDecimal.valueOf(Double.valueOf(petak.getNilaiBaru())));
                        //luas1 = petak.getNilaiBaru(); //guna utk betulkn kak ros salah isi petak aksr dlm 
                    }
                }
            }

            if (cukaiPetak != null) {
                BigDecimal cukaiBaru = BigDecimal.ZERO;

                if (hakmilikPermohonanList.get(0).getHakmilik().getIdHakmilik().contains("L")) {
                    cukaiBaru = luas.multiply(BigDecimal.valueOf(Double.valueOf(cukaiPetak.getKadarLanded())));
                } else {
                    cukaiBaru = luas.multiply(BigDecimal.valueOf(Double.valueOf(cukaiPetak.getKadar())));
                }

                if (hakmilikPermohonanList.get(0).getHakmilik().getKodHakmilik().getKod().equals("HMM")
                        || hakmilikPermohonanList.get(0).getHakmilik().getKodHakmilik().getKod().equals("GMM")) {
                    cukaiBaru = cukaiBaru.divide(BigDecimal.valueOf(2));
                }
                cukaiBaru = new BigDecimal(Math.ceil(cukaiBaru.doubleValue()));
                if (skimStrata.getKosRendah().equals("Y") && cukaiBaru.compareTo(BigDecimal.valueOf(15)) < 0) {
                    cukaiBaru = new BigDecimal(15);
                }
                cukai = String.valueOf(cukaiBaru);

                if (kinicukaiPetakSTR.size() <= 0) {
                    PermohonanPihakKemaskini kkini = new PermohonanPihakKemaskini();
                    kkini.setPermohonan(permohonan);
                    kkini.setHakmilik(hakmilikPermohonanList.get(0).getHakmilik());
                    kkini.setNamaMedan("cukaiPetakSTR");
                    kkini.setNilaiLama(String.valueOf(hakmilikPermohonanList.get(0).getHakmilik().getCukai()));
                    kkini.setNilaiBaru(String.valueOf(cukaiBaru));
                    kkini.setInfoAudit(ia);
                    pService.saveKKini(kkini);
                } else {
                    for (PermohonanPihakKemaskini cukaikkini : kinicukaiPetakSTR) {
                        cukaikkini.setNilaiBaru(String.valueOf(cukaiBaru));
                        cukaikkini.setInfoAudit(ia2);
                        pService.saveKKini(cukaikkini);
                    }
                }
            }
        }
    }

    public Resolution asasHmInduk() {
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = permohonanDAO.findById(idPermohonan);
        hakmilikPermohonanList = permohonan.getSenaraiHakmilik();
        getContext().getRequest().setAttribute("hmstr", Boolean.FALSE);

        return new JSP("daftar/pembetulan/pembetul_maklumatAsasHakmilikSTR.jsp").addParameter("tab", "true");
    }

    public Resolution asasHmSTR() {
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = permohonanDAO.findById(idPermohonan);
        hakmilikPermohonanList = permohonan.getSenaraiHakmilik();
        status = null;
        getContext().getRequest().setAttribute("hmstr", Boolean.TRUE);
        return new JSP("daftar/pembetulan/pembetul_maklumatAsasHakmilikSTR.jsp").addParameter("tab", "true");
    }

    public Resolution pembetulanBdnUrus() {
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = permohonanDAO.findById(idPermohonan);
        hakmilikPermohonanList = permohonan.getSenaraiHakmilik();

        return new JSP("daftar/pembetulan/pembetulanBdnUrus.jsp").addParameter("tab", "true");
    }

    public Resolution popupAsas() {
        idH = getContext().getRequest().getParameter("idH");
        List<Hakmilik> hmStr = strService.findHakmilibyParent(idH);
        if (hmStr.size() > 0) {
            hakmilik = hakmilikDAO.findById(hmStr.get(0).getIdHakmilik());
            permohonanPihakKemaskiniList = pService.findListbyIdMohonIdHm(idPermohonan, hakmilik.getIdHakmilikInduk());

            for (PermohonanPihakKemaskini kkini : permohonanPihakKemaskiniList) {
                if (kkini.getNamaMedan().equals("noFailSTR")) {
                    noFailSTR = kkini.getNilaiBaru();
                }
                if (kkini.getNamaMedan().equals("tarikhDaftarSTR")) {
                    tarikhDaftarSTR = kkini.getNilaiBaru();
                }
                if (kkini.getNamaMedan().equals("nopuSTR")) {
                    nopuSTR = kkini.getNilaiBaru();
                }
                if (kkini.getNamaMedan().equals("noBukuDaftarSTR")) {
                    noBukuDaftarSTR = kkini.getNilaiBaru();
                }
                if (kkini.getNamaMedan().equals("noSkimSTR")) {
                    noSkimSTR = kkini.getNilaiBaru();
                }
                if (kkini.getNamaMedan().equals("jumSyerSTR")) {
                    jumSyerSTR = kkini.getNilaiBaru();
                }
            }
            getContext().getRequest().setAttribute("hmstr", Boolean.FALSE);
        }
        return new JSP("daftar/pembetulan/popup_editAsas_hmSTR.jsp").addParameter("popup", "true");
    }

    public Resolution popupAsasSTR() {
        idH = getContext().getRequest().getParameter("idH");
        Pengguna pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        hakmilik = hakmilikDAO.findById(idH);

        if (hakmilik != null) {
            permohonanPihakKemaskiniList = pService.findListbyIdMohonIdHm(idPermohonan, hakmilik.getIdHakmilik());
            listHakmilikPetakAksesori = strService.findIDforPetak(hakmilik.getIdHakmilik());

            noPelanList = null;
            for (HakmilikPetakAksesori hpa : listHakmilikPetakAksesori) {
                if (hpa.getNoPelan() != null) {
                    String pelan = "PA(B)" + hpa.getNoPelan() + ", ";
                    noPelanList += pelan;
                    noPelanList = noPelanList.replace("null", "");
                }
                if (hpa.getNama() != null) {
                    String aksr = "A" + hpa.getNama() + ", ";
                    noPetakAksrList += aksr;
                    noPetakAksrList = noPetakAksrList.replace("null", "");
                }
            }

            if (hakmilik.getIdHakmilikInduk() != null && hakmilik.getKodKategoriBangunan() != null) {
                if (hakmilik.getKodKategoriBangunan().getKod().equals("B") && hakmilik.getNoPetak() != null) {
                    int noPetakold;
                    if (hakmilik.getIdHakmilik().length() == 28) {
                        noPetakold = Integer.parseInt(hakmilik.getIdHakmilik().substring(23, hakmilik.getIdHakmilik().length()));
                    }  else {
                        noPetakold = Integer.parseInt(hakmilik.getIdHakmilik().substring(24, hakmilik.getIdHakmilik().length()));
                    }
                    LOG.info("No Petak Lama ::" + noPetakold);
                    LOG.info("No Petak column ::" + hakmilik.getNoPetak());
                    if (hakmilik.getNoPetak().startsWith(" ")) {
                        List<PermohonanPihakKemaskini> kininoPetakSTR = pService.findListbyIdMohonIdHmMedan(idPermohonan, hakmilik.getIdHakmilik(),
                                "noPetakSTR");

                        InfoAudit ia = new InfoAudit();
                        ia.setDimasukOleh(pengguna);
                        ia.setTarikhMasuk(new java.util.Date());

                        if (kininoPetakSTR.isEmpty()) {
                            PermohonanPihakKemaskini kkini = new PermohonanPihakKemaskini();
                            kkini.setPermohonan(permohonan);
                            kkini.setHakmilik(hakmilik);
                            kkini.setNamaMedan("noPetakSTR");
                            kkini.setNilaiLama(hakmilik.getNoPetak());

                            int noPetakint;
                            if (hakmilik.getIdHakmilik().length() == 28) {
                                noPetakint = Integer.parseInt(hakmilik.getIdHakmilik().substring(23, hakmilik.getIdHakmilik().length()));
                            } else {
                                noPetakint = Integer.parseInt(hakmilik.getIdHakmilik().substring(24, hakmilik.getIdHakmilik().length()));
                            }
                            LOG.info("No Petak Baru ::" + noPetakint);
                            kkini.setNilaiBaru(String.valueOf(noPetakint));
                            noPetak = String.valueOf(noPetakint);
                            kkini.setInfoAudit(ia);
                            pService.saveKKini(kkini);
                        }
                    } else {
                        if (!hakmilik.getNoPetak().equals(String.valueOf(noPetakold))) {
                            List<PermohonanPihakKemaskini> kininoPetakSTR = pService.findListbyIdMohonIdHmMedan(idPermohonan, hakmilik.getIdHakmilik(),
                                "noPetakSTR");

                            InfoAudit ia = new InfoAudit();
                            ia.setDimasukOleh(pengguna);
                            ia.setTarikhMasuk(new java.util.Date());
                                if (kininoPetakSTR.isEmpty()) {
                                PermohonanPihakKemaskini kkini = new PermohonanPihakKemaskini();
                                kkini.setPermohonan(permohonan);
                                kkini.setHakmilik(hakmilik);
                                kkini.setNamaMedan("noPetakSTR");
                                kkini.setNilaiLama(hakmilik.getNoPetak());

                                int noPetakint;
                                if (hakmilik.getIdHakmilik().length() == 28) {
                                    noPetakint = Integer.parseInt(hakmilik.getIdHakmilik().substring(23, hakmilik.getIdHakmilik().length()));
                                } else {
                                    noPetakint = Integer.parseInt(hakmilik.getIdHakmilik().substring(24, hakmilik.getIdHakmilik().length()));
                                }
                                LOG.info("No Petak Baruu ::" + noPetakint);
                                kkini.setNilaiBaru(String.valueOf(noPetakint));
                                noPetak = String.valueOf(noPetakint);
                                kkini.setInfoAudit(ia);
                                pService.saveKKini(kkini);
                            }
                        }
                    }
                }
                if (hakmilik.getKodKategoriBangunan().getKod().equals("B") && hakmilik.getNoPetak() == null) {
                    if (hakmilik.getNoPetak().startsWith(" ")) {
                        List<PermohonanPihakKemaskini> kininoPetakSTR = pService.findListbyIdMohonIdHmMedan(idPermohonan, hakmilik.getIdHakmilik(),
                                "noPetakSTR");

                        InfoAudit ia = new InfoAudit();
                        ia.setDimasukOleh(pengguna);
                        ia.setTarikhMasuk(new java.util.Date());

                        if (kininoPetakSTR.isEmpty()) {
                            PermohonanPihakKemaskini kkini = new PermohonanPihakKemaskini();
                            kkini.setPermohonan(permohonan);
                            kkini.setHakmilik(hakmilik);
                            kkini.setNamaMedan("noPetakSTR");
                            kkini.setNilaiLama(hakmilik.getNoPetak());

                            int noPetakint;
                            if (hakmilik.getIdHakmilik().length() == 28) {
                                noPetakint = Integer.parseInt(hakmilik.getIdHakmilik().substring(23, hakmilik.getIdHakmilik().length()));
                            } else {
                                noPetakint = Integer.parseInt(hakmilik.getIdHakmilik().substring(20, hakmilik.getIdHakmilik().length()));
                            }
                            LOG.info("No Petak Baruuu ::" + noPetakint);
                            kkini.setNilaiBaru(String.valueOf(noPetakint));
                            noPetak = String.valueOf(noPetakint);
                            kkini.setInfoAudit(ia);
                            pService.saveKKini(kkini);
                        }
                    }
                }
            }

            permohonanPihakKemaskiniPetakAksr = new ArrayList<PermohonanPihakKemaskini>();
            permohonanPihakKemaskiniNoPelan = new ArrayList<PermohonanPihakKemaskini>();
            for (PermohonanPihakKemaskini kkini : permohonanPihakKemaskiniList) {
                if (kkini.getNamaMedan().equals("petakAksrSTR")) {
                    permohonanPihakKemaskiniPetakAksr.add(kkini);
                }
                if (kkini.getNamaMedan().equals("unitSyerSTR")) {
                    unitSyerSTR = kkini.getNilaiBaru();
                }
                if (kkini.getNamaMedan().equals("noPelanPetakSTR")) {
                    noPelanPetakSTR = kkini.getNilaiBaru();
                }
                if (kkini.getNamaMedan().equals("noPetakSTR")) {
                    noPetak = kkini.getNilaiBaru();
                }
                if (kkini.getNamaMedan().equals("noPelanSTR")) {
                    permohonanPihakKemaskiniNoPelan.add(kkini);
                }
                if (kkini.getNamaMedan().equals("luasPetakSTR")) {
                    luasPetakSTR = kkini.getNilaiBaru();
                }
                if (kkini.getNamaMedan().equals("cukaiPetakSTR")) {
                    cukai = kkini.getNilaiBaru();
                }
                if (kkini.getNamaMedan().equals("noTingkatSTR")) {
                    noTingkatSTR = kkini.getNilaiBaru();
                }
            }

            if (status == null) {
                if (permohonanPihakKemaskiniList.isEmpty() && !listHakmilikPetakAksesori.isEmpty() && noPetakAksrList != null) {
                    InfoAudit ia = new InfoAudit();
                    ia.setDimasukOleh(pengguna);
                    ia.setTarikhMasuk(new java.util.Date());

                    for (HakmilikPetakAksesori aksr : listHakmilikPetakAksesori) {
                        PermohonanPihakKemaskini kkini = new PermohonanPihakKemaskini();
                        kkini.setPermohonan(permohonan);
                        kkini.setHakmilik(aksr.getHakmilik());
                        kkini.setNamaMedan("petakAksrSTR");
                        kkini.setNilaiBaru(aksr.getNama());
                        kkini.setNilaiLama(noPetakAksrList);
                        kkini.setNilaiBerkaitan1(aksr.getLokasi());
                        if (aksr.getLuas() != null) {
                            kkini.setNilaiBerkaitan2(String.valueOf(aksr.getLuas()));
                        }
                        kkini.setInfoAudit(ia);
                        pService.saveKKini(kkini);
                    }
                }
                if (permohonanPihakKemaskiniList.isEmpty() && !listHakmilikPetakAksesori.isEmpty() && noPelanList != null) {
                    InfoAudit ia = new InfoAudit();
                    ia.setDimasukOleh(pengguna);
                    ia.setTarikhMasuk(new java.util.Date());

                    for (HakmilikPetakAksesori aksr : listHakmilikPetakAksesori) {
                        if (aksr.getNoPelan() != null) {
                            PermohonanPihakKemaskini kkini = new PermohonanPihakKemaskini();
                            kkini.setPermohonan(permohonan);
                            kkini.setHakmilik(aksr.getHakmilik());
                            kkini.setNamaMedan("noPelanSTR");
                            kkini.setNilaiBaru(aksr.getNoPelan());
                            kkini.setNilaiLama(noPelanList);
                            kkini.setInfoAudit(ia);
                            pService.saveKKini(kkini);
                        }
                    }
                }
            }
        }
        getContext().getRequest().setAttribute("hmstr", Boolean.TRUE);
        return new JSP("daftar/pembetulan/popup_editAsas_hmSTR.jsp").addParameter("popup", "true");
    }

    public Resolution popupPetakAksr() {
        idH = getContext().getRequest().getParameter("idH");
        String idKkini = getContext().getRequest().getParameter("idKkini");
        String pelan = getContext().getRequest().getParameter("pelan");
        hakmilik = hakmilikDAO.findById(idH);
        status = "ada";

        PermohonanPihakKemaskini kkini = null;
        if (idKkini != null) {
            kkini = permohonanPihakKemaskiniDAO.findById(Long.parseLong(idKkini));
        }

        if (kkini != null) {
            idKini = String.valueOf(kkini.getIdKemaskini());
            petakAksrSTR = kkini.getNilaiBaru();
            lokasiSTR = kkini.getNilaiBerkaitan1();
            luasPetakAksrSTR = kkini.getNilaiBerkaitan2();
        }
        popupAsasSTR();
        getContext().getRequest().setAttribute("hmstr", Boolean.TRUE);
        if (StringUtils.isNotBlank(pelan)) {
            getContext().getRequest().setAttribute("pelan", Boolean.TRUE);
        }
        return new JSP("daftar/pembetulan/popup_editPetak.jsp").addParameter("popup", "true");
    }

    public Resolution kembali() {
        idH = getContext().getRequest().getParameter("idH");
        hakmilik = hakmilikDAO.findById(idH);
        status = "ada";
        popupAsasSTR();

        getContext().getRequest().setAttribute("hmstr", Boolean.TRUE);
        return new JSP("daftar/pembetulan/popup_editAsas_hmSTR.jsp").addParameter("popup", "true");
    }

    public Resolution hapusAksr() {
        idH = getContext().getRequest().getParameter("idH");
        String medan = getContext().getRequest().getParameter("medan");
        String idKkini = getContext().getRequest().getParameter("idKkini");
        hakmilik = hakmilikDAO.findById(idH);

        PermohonanPihakKemaskini kkini = null;

        if (idKkini != null) {
            kkini = permohonanPihakKemaskiniDAO.findById(Long.parseLong(idKkini));
        } else {
            permohonanPihakKemaskiniList = pService.findListbyIdMohonIdHmMedan(idPermohonan,
                    hakmilik.getIdHakmilik(), medan);

            int i = 1;
            for (PermohonanPihakKemaskini kini : permohonanPihakKemaskiniList) {
                pService.delete(kini);
                i++;
            }
            if (i == permohonanPihakKemaskiniList.size()) {
                addSimpleMessage("Maklumat Berjaya Dihapus.");
            }
        }

        if (kkini != null) {
            pService.delete(kkini);
            addSimpleMessage("Maklumat Berjaya Dihapus.");
        }

        status = "ada";
        rehydrate();
        popupAsasSTR();

        getContext().getRequest().setAttribute("hmstr", Boolean.TRUE);
        return new JSP("daftar/pembetulan/popup_editAsas_hmSTR.jsp").addParameter("popup", "true");
    }

    public Resolution hapus() {
        idH = getContext().getRequest().getParameter("idH");
        String medan = getContext().getRequest().getParameter("medan");
        hakmilik = hakmilikDAO.findById(idH);

        permohonanPihakKemaskiniList = pService.findListbyIdMohonIdHmMedan(idPermohonan,
                hakmilik.getIdHakmilik(), medan);
        int i = 1;
        for (PermohonanPihakKemaskini kini : permohonanPihakKemaskiniList) {
            pService.delete(kini);
            i++;
        }
        if (i == permohonanPihakKemaskiniList.size()) {
            addSimpleMessage("Maklumat Berjaya Dihapus.");
        }

        popupAsas();
        getContext().getRequest().setAttribute("hmstr", Boolean.FALSE);
        return new JSP("daftar/pembetulan/popup_editAsas_hmSTR.jsp").addParameter("popup", "true");
    }

    public Resolution popupBdnUrus() {
        idH = getContext().getRequest().getParameter("idH");
        List<Hakmilik> hmStr = strService.findHakmilibyParent(idH);
        if (hmStr.size() > 0) {
            hakmilik = hakmilikDAO.findById(hmStr.get(0).getIdHakmilik());
            permohonanPihakKemaskiniList = pService.findListbyIdMohonIdHm(idPermohonan, hakmilik.getIdHakmilikInduk());
            BadanPengurusan idBdn = badanPengurusanDAO.findById(hakmilik.getBadanPengurusan().getIdBadan());

            if (idBdn != null) {
                badanUrus = pihakDAO.findById(idBdn.getPihak().getIdPihak());
            }

            for (PermohonanPihakKemaskini kkini : permohonanPihakKemaskiniList) {
                if (kkini.getNamaMedan().equals("bdnUrusSTR")) {
                    Pihak pihak = pihakDAO.findById(Long.parseLong(kkini.getNilaiBaru()));
                    if (pihak != null) {
                        namaBdnUrus = pihak.getNama();
                        alamat1BdnUrus = pihak.getAlamat1();
                        alamat2BdnUrus = pihak.getAlamat2();
                        alamat3BdnUrus = pihak.getAlamat3();
                        alamat4BdnUrus = pihak.getAlamat4();
                        poskodBdnUrus = pihak.getPoskod();
                        if (pihak.getNegeri() != null) {
                            negeriBdnUrus = pihak.getNegeri().getKod();
                        }

                        alamat1SuratBdnUrus = pihak.getSuratAlamat1();
                        alamat2SuratBdnUrus = pihak.getSuratAlamat2();
                        alamat3SuratBdnUrus = pihak.getSuratAlamat3();
                        alamat4SuratBdnUrus = pihak.getSuratAlamat4();
                        poskodSuratBdnUrus = pihak.getSuratPoskod();
                        if (pihak.getSuratNegeri() != null) {
                            negeriSuratBdnUrus = pihak.getSuratNegeri().getKod();
                        }
                    }
                }
            }

        }
        return new JSP("daftar/pembetulan/popup_BdnUrus.jsp").addParameter("popup", "true");
    }

    public Resolution saveBetul() {
        Pengguna pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        InfoAudit ia = new InfoAudit();
        ia.setDimasukOleh(pengguna);
        ia.setTarikhMasuk(new java.util.Date());
        InfoAudit ia2 = new InfoAudit();
        ia2.setDiKemaskiniOleh(pengguna);
        ia2.setTarikhKemaskini(new java.util.Date());

        List<Hakmilik> hmStr = strService.findHakmilibyParent(idH);
        if (hmStr.size() > 0) {
            hakmilik = hakmilikDAO.findById(hmStr.get(0).getIdHakmilik());
            Hakmilik hakmilikInduk = hakmilikDAO.findById(hmStr.get(0).getIdHakmilikInduk());
            mohonHakmilik = strService.findByIdHakmilikIdPermohonan(permohonan.getIdPermohonan(), idH);
            permohonanPihakKemaskiniList = pService.findListbyIdMohonIdHm(idPermohonan, hakmilik.getIdHakmilikInduk());

            List<PermohonanPihakKemaskini> kininoFailSTR = pService.findListbyIdMohonIdHmMedan(idPermohonan, hakmilik.getIdHakmilikInduk(),
                    "noFailSTR");
            if (kininoFailSTR.size() <= 0) {
                if (StringUtils.isNotBlank(noFailSTR)) {
                    PermohonanPihakKemaskini kkini = new PermohonanPihakKemaskini();
                    kkini.setPermohonan(permohonan);
                    kkini.setHakmilik(hakmilikInduk);
                    kkini.setNamaMedan("noFailSTR");
                    kkini.setNilaiBaru(noFailSTR);
                    kkini.setNilaiLama(hakmilik.getNoFail());
                    kkini.setInfoAudit(ia);
                    pService.saveKKini(kkini);
                }
            } else {
                for (PermohonanPihakKemaskini kini : kininoFailSTR) {
                    if (StringUtils.isNotBlank(noFailSTR)) {
                        kini.setNilaiBaru(noFailSTR);
                        kini.setInfoAudit(ia2);
                        pService.updateKKini(kini);
                    }
                }
            }

            List<PermohonanPihakKemaskini> kinitarikhDaftarSTR = pService.findListbyIdMohonIdHmMedan(idPermohonan, hakmilik.getIdHakmilikInduk(),
                    "tarikhDaftarSTR");
            if (kinitarikhDaftarSTR.size() <= 0) {
                if (StringUtils.isNotBlank(tarikhDaftarSTR)) {
                    PermohonanPihakKemaskini kkini = new PermohonanPihakKemaskini();
                    kkini.setPermohonan(permohonan);
                    kkini.setHakmilik(hakmilikInduk);
                    kkini.setNamaMedan("tarikhDaftarSTR");
                    kkini.setNilaiBaru(tarikhDaftarSTR);
                    kkini.setNilaiLama(sdf.format(hakmilik.getTarikhDaftar()));
                    kkini.setInfoAudit(ia);
                    pService.saveKKini(kkini);
                }
            } else {
                for (PermohonanPihakKemaskini kini : kinitarikhDaftarSTR) {
                    if (StringUtils.isNotBlank(tarikhDaftarSTR)) {
                        kini.setNilaiBaru(tarikhDaftarSTR);
                        kini.setInfoAudit(ia2);
                        pService.saveKKini(kini);
                    }
                }
            }

            List<PermohonanPihakKemaskini> kininopuSTR = pService.findListbyIdMohonIdHmMedan(idPermohonan, hakmilik.getIdHakmilikInduk(),
                    "nopuSTR");
            if (kininopuSTR.size() <= 0) {
                if (StringUtils.isNotBlank(nopuSTR)) {
                    PermohonanPihakKemaskini kkini = new PermohonanPihakKemaskini();
                    kkini.setPermohonan(permohonan);
                    kkini.setHakmilik(hakmilikInduk);
                    kkini.setNamaMedan("nopuSTR");
                    kkini.setNilaiBaru(nopuSTR);
                    kkini.setNilaiLama(hakmilik.getNoPu());
                    kkini.setInfoAudit(ia);
                    pService.saveKKini(kkini);
                }
            } else {
                for (PermohonanPihakKemaskini kini : kininopuSTR) {
                    if (StringUtils.isNotBlank(nopuSTR)) {
                        kini.setNilaiBaru(nopuSTR);
                        kini.setInfoAudit(ia2);
                        pService.saveKKini(kini);
                    }
                }
            }

            List<PermohonanPihakKemaskini> kininoBukuDaftarSTR = pService.findListbyIdMohonIdHmMedan(idPermohonan, hakmilik.getIdHakmilikInduk(),
                    "noBukuDaftarSTR");
            if (kininoBukuDaftarSTR.size() <= 0) {
                if (StringUtils.isNotBlank(noBukuDaftarSTR)) {
                    PermohonanPihakKemaskini kkini = new PermohonanPihakKemaskini();
                    kkini.setPermohonan(permohonan);
                    kkini.setHakmilik(hakmilikInduk);
                    kkini.setNamaMedan("noBukuDaftarSTR");
                    kkini.setNilaiBaru(noBukuDaftarSTR);
                    kkini.setNilaiLama(hakmilik.getNoBukuDaftarStrata());
                    kkini.setInfoAudit(ia);
                    pService.saveKKini(kkini);
                }
            } else {
                for (PermohonanPihakKemaskini kini : kininoBukuDaftarSTR) {
                    if (StringUtils.isNotBlank(noBukuDaftarSTR)) {
                        kini.setNilaiBaru(noBukuDaftarSTR);
                        kini.setInfoAudit(ia2);
                        pService.saveKKini(kini);
                    }
                }
            }

            List<PermohonanPihakKemaskini> kininoSkimSTR = pService.findListbyIdMohonIdHmMedan(idPermohonan, hakmilik.getIdHakmilikInduk(),
                    "noSkimSTR");
            if (kininoBukuDaftarSTR.size() <= 0) {
                if (StringUtils.isNotBlank(noSkimSTR)) {
                    PermohonanPihakKemaskini kkini = new PermohonanPihakKemaskini();
                    kkini.setPermohonan(permohonan);
                    kkini.setHakmilik(hakmilikInduk);
                    kkini.setNamaMedan("noSkimSTR");
                    kkini.setNilaiBaru(noSkimSTR);
                    kkini.setNilaiLama(hakmilik.getNoSkim());
                    kkini.setInfoAudit(ia);
                    pService.saveKKini(kkini);
                }
            } else {
                for (PermohonanPihakKemaskini kini : kininoSkimSTR) {
                    if (StringUtils.isNotBlank(noSkimSTR)) {
                        kini.setNilaiBaru(noSkimSTR);
                        kini.setInfoAudit(ia2);
                        pService.saveKKini(kini);
                    }
                }
            }

            List<PermohonanPihakKemaskini> kinijumSyerSTR = pService.findListbyIdMohonIdHmMedan(idPermohonan, hakmilik.getIdHakmilikInduk(),
                    "jumSyerSTR");
            if (kininoBukuDaftarSTR.size() <= 0) {
                if (StringUtils.isNotBlank(jumSyerSTR)) {
                    PermohonanPihakKemaskini kkini = new PermohonanPihakKemaskini();
                    kkini.setPermohonan(permohonan);
                    kkini.setHakmilik(hakmilikInduk);
                    kkini.setNamaMedan("jumSyerSTR");
                    kkini.setNilaiBaru(jumSyerSTR);
                    kkini.setNilaiLama(String.valueOf(hakmilik.getJumlahUnitSyer()));
                    kkini.setInfoAudit(ia);
                    pService.saveKKini(kkini);
                }
            } else {
                for (PermohonanPihakKemaskini kini : kinijumSyerSTR) {
                    if (StringUtils.isNotBlank(jumSyerSTR)) {
                        kini.setNilaiBaru(jumSyerSTR);
                        kini.setInfoAudit(ia2);
                        pService.saveKKini(kini);
                    }
                }
            }

            addSimpleMessage("Maklumat Berjaya Disimpan.");
        }

        return new JSP("daftar/pembetulan/popup_editAsas_hmSTR.jsp").addParameter("popup", "true");
    }

    public Resolution saveBdnUrus() {
        Pengguna pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        InfoAudit ia = new InfoAudit();
        ia.setDimasukOleh(pengguna);
        ia.setTarikhMasuk(new java.util.Date());
        InfoAudit ia2 = new InfoAudit();
        ia2.setDiKemaskiniOleh(pengguna);
        ia2.setTarikhKemaskini(new java.util.Date());
        permohonanPihakKemaskiniList = null;

        List<Hakmilik> hmStr = strService.findHakmilibyParent(idH);
        if (hmStr.size() > 0) {
            hakmilik = hakmilikDAO.findById(hmStr.get(0).getIdHakmilik());
            Hakmilik hakmilikInduk = hakmilikDAO.findById(hmStr.get(0).getIdHakmilikInduk());
            mohonHakmilik = strService.findByIdHakmilikIdPermohonan(permohonan.getIdPermohonan(), idH);
            permohonanPihakKemaskiniList = pService.findListbyIdMohonIdHmMedan(idPermohonan,
                    hakmilik.getIdHakmilikInduk(), "bdnUrusSTR");
            BadanPengurusan idbdn = badanPengurusanDAO.findById(hakmilik.getBadanPengurusan().getIdBadan());

            if (permohonanPihakKemaskiniList.size() <= 0) {
                Pihak pihak = new Pihak();
                if (StringUtils.isNotBlank(namaBdnUrus)) {
                    pihak.setNama(namaBdnUrus);
                    pihak.setAlamat1(alamat1BdnUrus);
                    pihak.setAlamat2(alamat2BdnUrus);
                    pihak.setAlamat3(alamat3BdnUrus);
                    pihak.setAlamat4(alamat4BdnUrus);
                    pihak.setPoskod(poskodBdnUrus);

                    if (negeriBdnUrus != null) {
                        pihak.setNegeri(kodNegeriDAO.findById(negeriBdnUrus));
                    }

                    pihak.setSuratAlamat1(alamat1SuratBdnUrus);
                    pihak.setSuratAlamat2(alamat2SuratBdnUrus);
                    pihak.setSuratAlamat3(alamat3SuratBdnUrus);
                    pihak.setSuratAlamat4(alamat4SuratBdnUrus);
                    pihak.setSuratPoskod(poskodSuratBdnUrus);
                    if (negeriBdnUrus != negeriSuratBdnUrus) {
                        pihak.setSuratNegeri(kodNegeriDAO.findById(negeriSuratBdnUrus));
                    }
                    pihak.setInfoAudit(ia);
                    strService.savePihak(pihak);

                    PermohonanPihakKemaskini kkini = new PermohonanPihakKemaskini();
                    kkini.setPermohonan(permohonan);
                    kkini.setHakmilik(hakmilikInduk);
                    kkini.setNamaMedan("bdnUrusSTR");
                    kkini.setNilaiBaru(String.valueOf(pihak.getIdPihak()));
                    kkini.setNilaiLama(String.valueOf(idbdn.getPihak().getIdPihak()));
                    kkini.setInfoAudit(ia);
                    pService.saveKKini(kkini);

                    BadanPengurusan strBdnUrus = new BadanPengurusan();
                    strBdnUrus.setCawangan(pengguna.getKodCawangan());
                    strBdnUrus.setInfoAudit(ia);
                    strBdnUrus.setPermohonan(permohonan);
                    strBdnUrus.setPihak(pihak);
                    strBdnUrus.setPengurusanNoRujukan(hakmilikInduk.getIdHakmilik());
                    strService.simpanBdnPengurusan(strBdnUrus);

                    addSimpleMessage("Maklumat Berjaya Disimpan.");
                }
            } else {
                for (PermohonanPihakKemaskini mohonKKini : permohonanPihakKemaskiniList) {
                    if (mohonKKini.getNamaMedan().equals("bdnUrusSTR")) {
                        Pihak pihak = pihakDAO.findById(Long.parseLong(mohonKKini.getNilaiBaru()));
                        if (pihak != null) {
                            pihak.setNama(namaBdnUrus);
                            pihak.setAlamat1(alamat1BdnUrus);
                            pihak.setAlamat2(alamat2BdnUrus);
                            pihak.setAlamat3(alamat3BdnUrus);
                            pihak.setAlamat4(alamat4BdnUrus);
                            pihak.setPoskod(poskodBdnUrus);
                            pihak.setNegeri(kodNegeriDAO.findById(negeriBdnUrus));

                            pihak.setSuratAlamat1(alamat1SuratBdnUrus);
                            pihak.setSuratAlamat2(alamat2SuratBdnUrus);
                            pihak.setSuratAlamat3(alamat3SuratBdnUrus);
                            pihak.setSuratAlamat4(alamat4SuratBdnUrus);
                            pihak.setSuratPoskod(poskodSuratBdnUrus);
                            pihak.setSuratNegeri(kodNegeriDAO.findById(negeriSuratBdnUrus));
                            pihak.setInfoAudit(ia2);
                            strService.updatePihak(pihak);
                            addSimpleMessage("Maklumat Berjaya Disimpan.");

                            mohonKKini.setNilaiBaru(String.valueOf(pihak.getIdPihak()));
                            mohonKKini.setInfoAudit(ia2);
                            pService.saveKKini(mohonKKini);
                        }
                    }
                }
            }
        }
        popupBdnUrus();

        return new JSP("daftar/pembetulan/popup_BdnUrus.jsp").addParameter("popup", "true");
    }

    public Resolution saveHmStr() {
        Pengguna pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        InfoAudit ia = new InfoAudit();
        ia.setDimasukOleh(pengguna);
        ia.setTarikhMasuk(new java.util.Date());
        InfoAudit ia2 = new InfoAudit();
        ia2.setDiKemaskiniOleh(pengguna);
        ia2.setTarikhKemaskini(new java.util.Date());
        permohonanPihakKemaskiniList = null;

        hakmilik = hakmilikDAO.findById(idH);
        permohonanPihakKemaskiniList = pService.findListbyIdMohonIdHm(idPermohonan,
                hakmilik.getIdHakmilik());
        List<PermohonanPihakKemaskini> kiniunitSyerSTR = pService.findListbyIdMohonIdHmMedan(idPermohonan, hakmilik.getIdHakmilik(),
                "unitSyerSTR");
        List<PermohonanPihakKemaskini> kininoPelanPetakSTR = pService.findListbyIdMohonIdHmMedan(idPermohonan, hakmilik.getIdHakmilik(),
                "noPelanPetakSTR");
        List<PermohonanPihakKemaskini> kiniluasPetakSTR = pService.findListbyIdMohonIdHmMedan(idPermohonan, hakmilik.getIdHakmilik(),
                "luasPetakSTR");
        List<PermohonanPihakKemaskini> kininoTingkatSTR = pService.findListbyIdMohonIdHmMedan(idPermohonan, hakmilik.getIdHakmilik(),
                "noTingkatSTR");
        
        if (kiniunitSyerSTR.size() <= 0) {
            if (StringUtils.isNotBlank(unitSyerSTR)) {
                PermohonanPihakKemaskini kkini = new PermohonanPihakKemaskini();
                kkini.setPermohonan(permohonan);
                kkini.setHakmilik(hakmilik);
                kkini.setNamaMedan("unitSyerSTR");
                kkini.setNilaiBaru(unitSyerSTR);
                kkini.setNilaiLama(String.valueOf(hakmilik.getUnitSyer()));
                kkini.setInfoAudit(ia);
                pService.saveKKini(kkini);
            }
        } else {
            for (PermohonanPihakKemaskini mohonKKini : permohonanPihakKemaskiniList) {
                if (mohonKKini.getNamaMedan().equals("unitSyerSTR")) {
                    if (StringUtils.isNotBlank(unitSyerSTR)) {
                        if (mohonKKini.getNamaMedan().equals("unitSyerSTR")) {
                            mohonKKini.setNilaiBaru(unitSyerSTR);
                            mohonKKini.setInfoAudit(ia2);
                            pService.saveKKini(mohonKKini);
                        }
                    }
                }
            }
        }
        if (kininoPelanPetakSTR.size() <= 0) {
            if (StringUtils.isNotBlank(noPelanPetakSTR)) {
                PermohonanPihakKemaskini kkini = new PermohonanPihakKemaskini();
                kkini.setPermohonan(permohonan);
                kkini.setHakmilik(hakmilik);
                kkini.setNamaMedan("noPelanPetakSTR");
                kkini.setNilaiBaru(noPelanPetakSTR);
                kkini.setNilaiLama(String.valueOf(hakmilik.getNoPelan()));
                kkini.setInfoAudit(ia);
                pService.saveKKini(kkini);
            }
        } else {
            for (PermohonanPihakKemaskini mohonKKini : permohonanPihakKemaskiniList) {
                if (mohonKKini.getNamaMedan().equals("noPelanPetakSTR")) {
                    if (StringUtils.isNotBlank(noPelanPetakSTR)) {
                        if (mohonKKini.getNamaMedan().equals("noPelanPetakSTR")) {
                            mohonKKini.setNilaiBaru(noPelanPetakSTR);
                            mohonKKini.setInfoAudit(ia2);
                            pService.saveKKini(mohonKKini);
                        }
                    }
                }
            }
        }
        if (kiniluasPetakSTR.size() <= 0) {
            if (StringUtils.isNotBlank(luasPetakSTR)) {
                PermohonanPihakKemaskini kkini = new PermohonanPihakKemaskini();
                kkini.setPermohonan(permohonan);
                kkini.setHakmilik(hakmilik);
                kkini.setNamaMedan("luasPetakSTR");
                kkini.setNilaiBaru(luasPetakSTR);
                kkini.setNilaiLama(String.valueOf(hakmilik.getLuas()));
                kkini.setInfoAudit(ia);
                pService.saveKKini(kkini);
            }
        } else {
            for (PermohonanPihakKemaskini mohonKKini : permohonanPihakKemaskiniList) {
                if (mohonKKini.getNamaMedan().equals("luasPetakSTR")) {
                    if (StringUtils.isNotBlank(luasPetakSTR)) {
                        if (mohonKKini.getNamaMedan().equals("luasPetakSTR")) {
                            mohonKKini.setNilaiBaru(luasPetakSTR);
                            mohonKKini.setInfoAudit(ia2);
                            pService.saveKKini(mohonKKini);
                        }
                    }
                }
            }
        }
        LOG.info("No Tingkat size() ::" + kininoTingkatSTR.size());
        LOG.info("No Tingkat Baru ::" + noTingkatSTR);
        LOG.info("No Tingkat Lama ::" + noTingkatSTR);
        if (kininoTingkatSTR.size() <= 0) {
            if (StringUtils.isNotBlank(noTingkatSTR)) {
                PermohonanPihakKemaskini kkini = new PermohonanPihakKemaskini();
                kkini.setPermohonan(permohonan);
                kkini.setHakmilik(hakmilik);
                kkini.setNamaMedan("noTingkatSTR");
                kkini.setNilaiBaru(noTingkatSTR);
                kkini.setNilaiLama(String.valueOf(hakmilik.getNoTingkat()));
                kkini.setInfoAudit(ia);
                pService.saveKKini(kkini);
            }
        } else {
            for (PermohonanPihakKemaskini mohonKKini : permohonanPihakKemaskiniList) {
                if (mohonKKini.getNamaMedan().equals("noTingkatSTR")) {
                    if (StringUtils.isNotBlank(noTingkatSTR)) {
                        if (mohonKKini.getNamaMedan().equals("noTingkatSTR")) {
                            mohonKKini.setNilaiBaru(noTingkatSTR);
                            mohonKKini.setInfoAudit(ia2);
                            pService.saveKKini(mohonKKini);
                        }
                    }
                }
            }
        }
        addSimpleMessage("Maklumat Berjaya Disimpan.");
        status = "ada";
        rehydrate();
        popupAsasSTR();
        return new JSP("daftar/pembetulan/popup_editAsas_hmSTR.jsp").addParameter("popup", "true");
    }

    public Resolution saveHmStrPetakAksr() {
        Pengguna pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        InfoAudit ia = new InfoAudit();
        ia.setDimasukOleh(pengguna);
        ia.setTarikhMasuk(new java.util.Date());
        InfoAudit ia2 = new InfoAudit();
        ia2.setDiKemaskiniOleh(pengguna);
        ia2.setTarikhKemaskini(new java.util.Date());
        idH = getContext().getRequest().getParameter("idH");
        idKini = getContext().getRequest().getParameter("idKini");
        hakmilik = hakmilikDAO.findById(idH);

        List<HakmilikPetakAksesori> hpaList = strService.findIDforPetak(hakmilik.getIdHakmilik());

        noPelanList = null;
        noPetakAksrList = null;
        for (HakmilikPetakAksesori hpa : hpaList) {
            if (hpa.getNoPelan() != null) {
                String pelan = hpa.getNoPelan() + ", ";
                noPelanList += pelan;
                noPelanList = noPelanList.replace("null", "");
            }
            if (hpa.getNama() != null) {
                String aksr = "A" + hpa.getNama() + ", ";
                noPetakAksrList += aksr;
                noPetakAksrList = noPetakAksrList.replace("null", "");
            }
        }

        permohonanPihakKemaskiniList = null;

        hakmilik = hakmilikDAO.findById(idH);

        PermohonanPihakKemaskini mohonKkini = null;
        if (!idKini.isEmpty()) {
            mohonKkini = permohonanPihakKemaskiniDAO.findById(Long.parseLong(idKini));
        }

        if (StringUtils.isNotBlank(petakAksrSTR) && mohonKkini == null) {
            PermohonanPihakKemaskini kkini = new PermohonanPihakKemaskini();
            kkini.setPermohonan(permohonan);
            kkini.setHakmilik(hakmilik);
            kkini.setNamaMedan("petakAksrSTR");
            kkini.setNilaiBerkaitan1(lokasiSTR);
            kkini.setNilaiBerkaitan2(luasPetakAksrSTR);
            kkini.setNilaiBaru(petakAksrSTR);
            kkini.setNilaiLama(noPetakAksrList);
            kkini.setInfoAudit(ia);
            pService.saveKKini(kkini);
        } else {
            if(mohonKkini != null){
                mohonKkini.setNilaiBerkaitan1(lokasiSTR);
                mohonKkini.setNilaiBerkaitan2(luasPetakAksrSTR);
                mohonKkini.setNilaiBaru(petakAksrSTR);
                mohonKkini.setInfoAudit(ia);
                pService.updateKKini(mohonKkini);
            }
        }
        if (StringUtils.isNotBlank(noPelanSTR)) {
            PermohonanPihakKemaskini kkini = new PermohonanPihakKemaskini();
            kkini.setPermohonan(permohonan);
            kkini.setHakmilik(hakmilik);
            kkini.setNamaMedan("noPelanSTR");
            kkini.setNilaiBaru(noPelanSTR);
            kkini.setNilaiLama(noPelanList);
            kkini.setInfoAudit(ia);
            pService.saveKKini(kkini);
        }
        addSimpleMessage("Maklumat Berjaya Disimpan.");

        noPelanList = null;
        noPetakAksrList = null;
        status = "ada";
        rehydrate();
        popupAsasSTR();
        return new JSP("daftar/pembetulan/popup_editAsas_hmSTR.jsp").addParameter("popup", "true");
    }

    public String getIdPermohonan() {
        return idPermohonan;
    }

    public void setIdPermohonan(String idPermohonan) {
        this.idPermohonan = idPermohonan;
    }

    public Pengguna getPeng() {
        return peng;
    }

    public void setPeng(Pengguna peng) {
        this.peng = peng;
    }

    public Permohonan getPermohonan() {
        return permohonan;
    }

    public void setPermohonan(Permohonan permohonan) {
        this.permohonan = permohonan;
    }

    public Hakmilik getHakmilik() {
        return hakmilik;
    }

    public void setHakmilik(Hakmilik hakmilik) {
        this.hakmilik = hakmilik;
    }

    public HakmilikPermohonan getMohonHakmilik() {
        return mohonHakmilik;
    }

    public void setMohonHakmilik(HakmilikPermohonan mohonHakmilik) {
        this.mohonHakmilik = mohonHakmilik;
    }

    public String getIdH() {
        return idH;
    }

    public void setIdH(String idH) {
        this.idH = idH;
    }

    public List<HakmilikPermohonan> getHakmilikPermohonanList() {
        return hakmilikPermohonanList;
    }

    public void setHakmilikPermohonanList(List<HakmilikPermohonan> hakmilikPermohonanList) {
        this.hakmilikPermohonanList = hakmilikPermohonanList;
    }

    public PermohonanPihakKemaskini getPermohonanPihakKemaskini() {
        return permohonanPihakKemaskini;
    }

    public void setPermohonanPihakKemaskini(PermohonanPihakKemaskini permohonanPihakKemaskini) {
        this.permohonanPihakKemaskini = permohonanPihakKemaskini;
    }

    public List<PermohonanPihakKemaskini> getPermohonanPihakKemaskiniList() {
        return permohonanPihakKemaskiniList;
    }

    public void setPermohonanPihakKemaskiniList(List<PermohonanPihakKemaskini> permohonanPihakKemaskiniList) {
        this.permohonanPihakKemaskiniList = permohonanPihakKemaskiniList;
    }

    public String getNoFailSTR() {
        return noFailSTR;
    }

    public void setNoFailSTR(String noFailSTR) {
        this.noFailSTR = noFailSTR;
    }

    public String getTarikhDaftarSTR() {
        return tarikhDaftarSTR;
    }

    public void setTarikhDaftarSTR(String tarikhDaftarSTR) {
        this.tarikhDaftarSTR = tarikhDaftarSTR;
    }

    public String getNopuSTR() {
        return nopuSTR;
    }

    public void setNopuSTR(String nopuSTR) {
        this.nopuSTR = nopuSTR;
    }

    public String getNoBukuDaftarSTR() {
        return noBukuDaftarSTR;
    }

    public void setNoBukuDaftarSTR(String noBukuDaftarSTR) {
        this.noBukuDaftarSTR = noBukuDaftarSTR;
    }

    public String getNoSkimSTR() {
        return noSkimSTR;
    }

    public void setNoSkimSTR(String noSkimSTR) {
        this.noSkimSTR = noSkimSTR;
    }

    public String getJumSyerSTR() {
        return jumSyerSTR;
    }

    public void setJumSyerSTR(String jumSyerSTR) {
        this.jumSyerSTR = jumSyerSTR;
    }

    public String getNamaBdnUrus() {
        return namaBdnUrus;
    }

    public void setNamaBdnUrus(String namaBdnUrus) {
        this.namaBdnUrus = namaBdnUrus;
    }

    public String getAlamat1BdnUrus() {
        return alamat1BdnUrus;
    }

    public void setAlamat1BdnUrus(String alamat1BdnUrus) {
        this.alamat1BdnUrus = alamat1BdnUrus;
    }

    public String getAlamat2BdnUrus() {
        return alamat2BdnUrus;
    }

    public void setAlamat2BdnUrus(String alamat2BdnUrus) {
        this.alamat2BdnUrus = alamat2BdnUrus;
    }

    public String getAlamat3BdnUrus() {
        return alamat3BdnUrus;
    }

    public void setAlamat3BdnUrus(String alamat3BdnUrus) {
        this.alamat3BdnUrus = alamat3BdnUrus;
    }

    public String getPoskodBdnUrus() {
        return poskodBdnUrus;
    }

    public void setPoskodBdnUrus(String poskodBdnUrus) {
        this.poskodBdnUrus = poskodBdnUrus;
    }

    public String getNegeriBdnUrus() {
        return negeriBdnUrus;
    }

    public void setNegeriBdnUrus(String negeriBdnUrus) {
        this.negeriBdnUrus = negeriBdnUrus;
    }

    public String getAlamat4BdnUrus() {
        return alamat4BdnUrus;
    }

    public void setAlamat4BdnUrus(String alamat4BdnUrus) {
        this.alamat4BdnUrus = alamat4BdnUrus;
    }

    public String getAlamat1SuratBdnUrus() {
        return alamat1SuratBdnUrus;
    }

    public void setAlamat1SuratBdnUrus(String alamat1SuratBdnUrus) {
        this.alamat1SuratBdnUrus = alamat1SuratBdnUrus;
    }

    public String getAlamat2SuratBdnUrus() {
        return alamat2SuratBdnUrus;
    }

    public void setAlamat2SuratBdnUrus(String alamat2SuratBdnUrus) {
        this.alamat2SuratBdnUrus = alamat2SuratBdnUrus;
    }

    public String getAlamat3SuratBdnUrus() {
        return alamat3SuratBdnUrus;
    }

    public void setAlamat3SuratBdnUrus(String alamat3SuratBdnUrus) {
        this.alamat3SuratBdnUrus = alamat3SuratBdnUrus;
    }

    public String getAlamat4SuratBdnUrus() {
        return alamat4SuratBdnUrus;
    }

    public void setAlamat4SuratBdnUrus(String alamat4SuratBdnUrus) {
        this.alamat4SuratBdnUrus = alamat4SuratBdnUrus;
    }

    public String getPoskodSuratBdnUrus() {
        return poskodSuratBdnUrus;
    }

    public void setPoskodSuratBdnUrus(String poskodSuratBdnUrus) {
        this.poskodSuratBdnUrus = poskodSuratBdnUrus;
    }

    public String getNegeriSuratBdnUrus() {
        return negeriSuratBdnUrus;
    }

    public void setNegeriSuratBdnUrus(String negeriSuratBdnUrus) {
        this.negeriSuratBdnUrus = negeriSuratBdnUrus;
    }

    public Pihak getBadanUrus() {
        return badanUrus;
    }

    public void setBadanUrus(Pihak badanUrus) {
        this.badanUrus = badanUrus;
    }

    public List<PermohonanPihakKemaskini> getPermohonanPihakKemaskiniPetakAksr() {
        return permohonanPihakKemaskiniPetakAksr;
    }

    public void setPermohonanPihakKemaskiniPetakAksr(List<PermohonanPihakKemaskini> permohonanPihakKemaskiniPetakAksr) {
        this.permohonanPihakKemaskiniPetakAksr = permohonanPihakKemaskiniPetakAksr;
    }

    public List<PermohonanPihakKemaskini> getPermohonanPihakKemaskiniNoPelan() {
        return permohonanPihakKemaskiniNoPelan;
    }

    public void setPermohonanPihakKemaskiniNoPelan(List<PermohonanPihakKemaskini> permohonanPihakKemaskiniNoPelan) {
        this.permohonanPihakKemaskiniNoPelan = permohonanPihakKemaskiniNoPelan;
    }

    public String getNoPelanList() {
        return noPelanList;
    }

    public void setNoPelanList(String noPelanList) {
        this.noPelanList = noPelanList;
    }

    public String getNoPetakAksrList() {
        return noPetakAksrList;
    }

    public void setNoPetakAksrList(String noPetakAksrList) {
        this.noPetakAksrList = noPetakAksrList;
    }

    public String getPetakAksrSTR() {
        return petakAksrSTR;
    }

    public void setPetakAksrSTR(String petakAksrSTR) {
        this.petakAksrSTR = petakAksrSTR;
    }

    public String getUnitSyerSTR() {
        return unitSyerSTR;
    }

    public void setUnitSyerSTR(String unitSyerSTR) {
        this.unitSyerSTR = unitSyerSTR;
    }

    public String getNoPelanSTR() {
        return noPelanSTR;
    }

    public void setNoPelanSTR(String noPelanSTR) {
        this.noPelanSTR = noPelanSTR;
    }

    public String getNoPelanPetakSTR() {
        return noPelanPetakSTR;
    }

    public void setNoPelanPetakSTR(String noPelanPetakSTR) {
        this.noPelanPetakSTR = noPelanPetakSTR;
    }

    public String getNoPelanPetak() {
        return noPelanPetak;
    }

    public void setNoPelanPetak(String noPelanPetak) {
        this.noPelanPetak = noPelanPetak;
    }

    public String getLokasiSTR() {
        return lokasiSTR;
    }

    public void setLokasiSTR(String lokasiSTR) {
        this.lokasiSTR = lokasiSTR;
    }

    public String getNoPetak() {
        return noPetak;
    }

    public void setNoPetak(String noPetak) {
        this.noPetak = noPetak;
    }

    public String getLuasPetakSTR() {
        return luasPetakSTR;
    }

    public void setLuasPetakSTR(String luasPetakSTR) {
        this.luasPetakSTR = luasPetakSTR;
    }

    public String getLuasPetakAksrSTR() {
        return luasPetakAksrSTR;
    }

    public void setLuasPetakAksrSTR(String luasPetakAksrSTR) {
        this.luasPetakAksrSTR = luasPetakAksrSTR;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<HakmilikPetakAksesori> getListHakmilikPetakAksesori() {
        return listHakmilikPetakAksesori;
    }

    public void setListHakmilikPetakAksesori(List<HakmilikPetakAksesori> listHakmilikPetakAksesori) {
        this.listHakmilikPetakAksesori = listHakmilikPetakAksesori;
    }

    public String getIdKini() {
        return idKini;
    }

    public void setIdKini(String idKini) {
        this.idKini = idKini;
    }

    public String getCukai() {
        return cukai;
    }

    public void setCukai(String cukai) {
        this.cukai = cukai;
    }

    public String getNoTingkatSTR() {
        return noTingkatSTR;
    }

    public void setNoTingkatSTR(String noTingkatSTR) {
        this.noTingkatSTR = noTingkatSTR;
    }

}
