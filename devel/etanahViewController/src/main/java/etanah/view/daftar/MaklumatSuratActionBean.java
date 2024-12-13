/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.daftar;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.dao.*;
import etanah.service.common.*;
import etanah.service.*;
import etanah.model.*;
import etanah.service.daftar.PembetulanService;
import etanah.view.etanahActionBeanContext;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;
import net.sourceforge.stripes.action.*;
import net.sourceforge.stripes.controller.LifecycleStage;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;

@UrlBinding("/daftar/maklumat_surat")
public class MaklumatSuratActionBean extends AbleActionBean {

    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    PembetulanService pService;
    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;
    private Permohonan permohonan;
    private WakilKuasa wakilKuasa;
    private WakilKuasaPenerima wakilKuasaPenerima;
    private WakilKuasaPemberi wakilKuasaPemberi;
    private String nama;//NAMA
    private String noPengenalan;//NO_PENGENALAN
    private String kodPengenalan;//KOD_PENGENALAN
    private String namaPenerima;//NAMA--Penerima
    private String noPengenalanPenerima;//NO_PENGENALAN--Penerima
    private String kodPengenalanPenerima;//KOD_PENGENALAN--Penerima
    private String alamat1;//ALAMAT1
    private String alamat2;//ALAMAT2
    private String alamat3;//ALAMAT3
    private String alamat4;//ALAMAT4
    private String poskod;//POSKOD
    private String kodNegeri;//KOD_NEGERI
    private String status;//STS
    private String namaBaru;//NAMA
    private String noPengenalanBaru;//NO_PENGENALAN
    private String kodPengenalanBaru;//KOD_PENGENALAN
    private String namaPenerimaBaru;//NAMA--Penerima
    private String noPengenalanPenerimaBaru;//NO_PENGENALAN--Penerima
    private String kodPengenalanPenerimaBaru;//KOD_PENGENALAN--Penerima
    private String alamat1Baru;//ALAMAT1
    private String alamat2Baru;//ALAMAT2
    private String alamat3Baru;//ALAMAT3
    private String alamat4Baru;//ALAMAT4
    private String poskodBaru;//POSKOD
    private String kodNegeriBaru;//KOD_NEGERI
    private String idSurat;
    private List<HakmilikUrusan> hakmilikUrusanSC;
    private List<HakmilikUrusan> hakmilikUrusanN;
    private List<HakmilikUrusan> hakmilikUrusanB;
    private List<HakmilikUrusan> senaraiSurat = new ArrayList<HakmilikUrusan>();
    private List<Dokumen> senaraiDokumen = new ArrayList<Dokumen>();
    private List<HakmilikPermohonan> hakmilikPermohonanList;
    private static final Logger LOGGER = Logger.getLogger(MaklumatSuratActionBean.class);
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

    @DefaultHandler
    public Resolution showForm() {
        hakmilikUrusanB = new ArrayList<HakmilikUrusan>();
        hakmilikUrusanN = new ArrayList<HakmilikUrusan>();
        hakmilikUrusanSC = new ArrayList<HakmilikUrusan>();
        hakmilikPermohonanList = permohonan.getSenaraiHakmilik();
        for (HakmilikPermohonan hpMOHON : hakmilikPermohonanList) {
            hakmilikUrusanB.addAll(pService.findHakmilikByHakmilik(hpMOHON.getHakmilik().getIdHakmilik(), "B"));
            hakmilikUrusanN.addAll(pService.findHakmilikByHakmilik(hpMOHON.getHakmilik().getIdHakmilik(), "N"));
            hakmilikUrusanSC.addAll(pService.findHakmilikByHakmilik(hpMOHON.getHakmilik().getIdHakmilik(), "SC"));
        }

        for (HakmilikUrusan hu : hakmilikUrusanB) {
            List<Dokumen> listSurat = pService.findSurat(hu.getIdPerserahan(), hu.getHakmilik().getIdHakmilik());
            for (Dokumen d : listSurat) {
                senaraiDokumen.add(d);
            }
        }
        for (HakmilikUrusan hu : hakmilikUrusanN) {
            List<Dokumen> listSurat = pService.findSurat(hu.getIdPerserahan(), hu.getHakmilik().getIdHakmilik());
            for (Dokumen d : listSurat) {
                senaraiDokumen.add(d);
            }
        }
        for (HakmilikUrusan hu : hakmilikUrusanSC) {
            List<Dokumen> listSurat = pService.findSurat(hu.getIdPerserahan(), hu.getHakmilik().getIdHakmilik());
            for (Dokumen d : listSurat) {
                senaraiDokumen.add(d);
            }
        }
        return new JSP("daftar/maklumat_surat.jsp").addParameter("tab", "true");
    }

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {
        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = permohonanDAO.findById(idPermohonan);
    }

    public Resolution simpanSurat() {
        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        Pengguna pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        permohonan = permohonanDAO.findById(idPermohonan);
        Permohonan idWakil = permohonanDAO.findById(idSurat);

        InfoAudit ia = new InfoAudit();
        ia.setDimasukOleh(pengguna);
        ia.setTarikhMasuk(new java.util.Date());
        InfoAudit ia2 = new InfoAudit();
        ia2.setDiKemaskiniOleh(pengguna);
        ia2.setTarikhKemaskini(new java.util.Date());

        String idhm = "";
        if (permohonan.getSenaraiHakmilik().size() == 1) {
            idhm = permohonan.getSenaraiHakmilik().get(0).getHakmilik().getIdHakmilik();
        }
        List<PermohonanPihakKemaskini> listNama = pService.findListbyIdMohonIdHmMedanIdlama(idWakil.getIdPermohonan(), idhm, "NAMA", idPermohonan);
        List<PermohonanPihakKemaskini> listNoPengenalan = pService.findListbyIdMohonIdHmMedanIdlama(idWakil.getIdPermohonan(), idhm, "NO_PENGENALAN", idPermohonan);
        List<PermohonanPihakKemaskini> listKodPengenalan = pService.findListbyIdMohonIdHmMedanIdlama(idWakil.getIdPermohonan(), idhm, "KOD_PENGENALAN", idPermohonan);
        List<PermohonanPihakKemaskini> listNamaPenerima = pService.findListbyIdMohonIdHmMedanIdlama(idWakil.getIdPermohonan(), idhm, "NAMA_PNERIMA", idPermohonan);
        List<PermohonanPihakKemaskini> listNoPengenalanPenerima = pService.findListbyIdMohonIdHmMedanIdlama(idWakil.getIdPermohonan(), idhm, "NO_PENGENALAN_PNERIMA", idPermohonan);
        List<PermohonanPihakKemaskini> listKodPengenalanPenerima = pService.findListbyIdMohonIdHmMedanIdlama(idWakil.getIdPermohonan(), idhm, "KOD_PENGENALAN_PNERIMA", idPermohonan);
        List<PermohonanPihakKemaskini> listAlamat1 = pService.findListbyIdMohonIdHmMedanIdlama(idWakil.getIdPermohonan(), idhm, "ALAMAT1_PNERIMA", idPermohonan);
        List<PermohonanPihakKemaskini> listAlamat2 = pService.findListbyIdMohonIdHmMedanIdlama(idWakil.getIdPermohonan(), idhm, "ALAMAT2_PNERIMA", idPermohonan);
        List<PermohonanPihakKemaskini> listAlamat3 = pService.findListbyIdMohonIdHmMedanIdlama(idWakil.getIdPermohonan(), idhm, "ALAMAT3_PNERIMA", idPermohonan);
        List<PermohonanPihakKemaskini> listAlamat4 = pService.findListbyIdMohonIdHmMedanIdlama(idWakil.getIdPermohonan(), idhm, "ALAMAT4_PNERIMA", idPermohonan);
        List<PermohonanPihakKemaskini> listPoskod = pService.findListbyIdMohonIdHmMedanIdlama(idWakil.getIdPermohonan(), idhm, "POSKOD_PNERIMA", idPermohonan);
        List<PermohonanPihakKemaskini> listKodNegeri = pService.findListbyIdMohonIdHmMedanIdlama(idWakil.getIdPermohonan(), idhm, "KOD_NEGERI_PNERIMA", idPermohonan);

        int count = 0;

        if (StringUtils.isNotBlank(namaBaru) && listNama.isEmpty()) {
            PermohonanPihakKemaskini mpk = new PermohonanPihakKemaskini();
            mpk.setPermohonan(idWakil);
            if (permohonan.getSenaraiHakmilik().size() == 1) {
                mpk.setHakmilik(permohonan.getSenaraiHakmilik().get(0).getHakmilik());
            }
            mpk.setNamaMedan("NAMA");
            mpk.setNilaiLama(nama);
            mpk.setNilaiBaru(namaBaru);
            mpk.setInfoAudit(ia);
            mpk.setCawangan(pengguna.getKodCawangan());
            mpk.setIdPermohonanLama(idPermohonan);
            pService.saveKKini(mpk);
            count++;
        } else {
            for (PermohonanPihakKemaskini ln : listNama) {
                ln.setNilaiBaru(namaBaru);
                ln.setInfoAudit(ia2);
                pService.saveKKini(ln);
                count++;
            }
        }

        if (StringUtils.isNotBlank(noPengenalanBaru) && listNoPengenalan.isEmpty()) {
            PermohonanPihakKemaskini mpk = new PermohonanPihakKemaskini();
            mpk.setPermohonan(idWakil);
            if (permohonan.getSenaraiHakmilik().size() == 1) {
                mpk.setHakmilik(permohonan.getSenaraiHakmilik().get(0).getHakmilik());
            }
            mpk.setNamaMedan("NO_PENGENALAN");
            mpk.setNilaiLama(noPengenalan);
            mpk.setNilaiBaru(noPengenalanBaru);
            mpk.setInfoAudit(ia);
            mpk.setCawangan(pengguna.getKodCawangan());
            mpk.setIdPermohonanLama(idPermohonan);
            pService.saveKKini(mpk);
            count++;
        } else {
            for (PermohonanPihakKemaskini ln : listNoPengenalan) {
                ln.setNilaiBaru(noPengenalanBaru);
                ln.setInfoAudit(ia2);
                pService.saveKKini(ln);
                count++;
            }
        }

        if (StringUtils.isNotBlank(kodPengenalanBaru) && listKodPengenalan.isEmpty()) {
            PermohonanPihakKemaskini mpk = new PermohonanPihakKemaskini();
            mpk.setPermohonan(idWakil);
            if (permohonan.getSenaraiHakmilik().size() == 1) {
                mpk.setHakmilik(permohonan.getSenaraiHakmilik().get(0).getHakmilik());
            }
            mpk.setNamaMedan("KOD_PENGENALAN");
            mpk.setNilaiLama(kodPengenalan);
            mpk.setNilaiBaru(kodPengenalanBaru);
            mpk.setInfoAudit(ia);
            mpk.setCawangan(pengguna.getKodCawangan());
            mpk.setIdPermohonanLama(idPermohonan);
            pService.saveKKini(mpk);
            count++;
        } else {
            for (PermohonanPihakKemaskini ln : listKodPengenalan) {
                ln.setNilaiBaru(kodPengenalanBaru);
                ln.setInfoAudit(ia2);
                pService.saveKKini(ln);
                count++;
            }
        }

        if (StringUtils.isNotBlank(namaPenerimaBaru) && listNamaPenerima.isEmpty()) {
            PermohonanPihakKemaskini mpk = new PermohonanPihakKemaskini();
            mpk.setPermohonan(idWakil);
            if (permohonan.getSenaraiHakmilik().size() == 1) {
                mpk.setHakmilik(permohonan.getSenaraiHakmilik().get(0).getHakmilik());
            }
            mpk.setNamaMedan("NAMA_PNERIMA");
            mpk.setNilaiLama(namaPenerima);
            mpk.setNilaiBaru(namaPenerimaBaru);
            mpk.setInfoAudit(ia);
            mpk.setCawangan(pengguna.getKodCawangan());
            mpk.setIdPermohonanLama(idPermohonan);
            pService.saveKKini(mpk);
            count++;
        } else {
            for (PermohonanPihakKemaskini ln : listNamaPenerima) {
                ln.setNilaiBaru(namaPenerimaBaru);
                ln.setInfoAudit(ia2);
                pService.saveKKini(ln);
                count++;
            }
        }

        if (StringUtils.isNotBlank(noPengenalanPenerimaBaru) && listNoPengenalanPenerima.isEmpty()) {
            PermohonanPihakKemaskini mpk = new PermohonanPihakKemaskini();
            mpk.setPermohonan(idWakil);
            if (permohonan.getSenaraiHakmilik().size() == 1) {
                mpk.setHakmilik(permohonan.getSenaraiHakmilik().get(0).getHakmilik());
            }
            mpk.setNamaMedan("NO_PENGENALAN_PNERIMA");
            mpk.setNilaiLama(noPengenalanPenerima);
            mpk.setNilaiBaru(noPengenalanPenerimaBaru);
            mpk.setInfoAudit(ia);
            mpk.setCawangan(pengguna.getKodCawangan());
            mpk.setIdPermohonanLama(idPermohonan);
            pService.saveKKini(mpk);
            count++;
        } else {
            for (PermohonanPihakKemaskini ln : listNoPengenalanPenerima) {
                ln.setNilaiBaru(noPengenalanPenerimaBaru);
                ln.setInfoAudit(ia2);
                pService.saveKKini(ln);
                count++;
            }
        }

        if (StringUtils.isNotBlank(kodPengenalanPenerimaBaru) && listKodPengenalanPenerima.isEmpty()) {
            PermohonanPihakKemaskini mpk = new PermohonanPihakKemaskini();
            mpk.setPermohonan(idWakil);
            if (permohonan.getSenaraiHakmilik().size() == 1) {
                mpk.setHakmilik(permohonan.getSenaraiHakmilik().get(0).getHakmilik());
            }
            mpk.setNamaMedan("KOD_PENGENALAN_PNERIMA");
            mpk.setNilaiLama(kodPengenalanPenerima);
            mpk.setNilaiBaru(kodPengenalanPenerimaBaru);
            mpk.setInfoAudit(ia);
            mpk.setCawangan(pengguna.getKodCawangan());
            mpk.setIdPermohonanLama(idPermohonan);
            pService.saveKKini(mpk);
            count++;
        } else {
            for (PermohonanPihakKemaskini ln : listKodPengenalanPenerima) {
                ln.setNilaiBaru(kodPengenalanPenerimaBaru);
                ln.setInfoAudit(ia2);
                pService.saveKKini(ln);
                count++;
            }
        }

        if (StringUtils.isNotBlank(alamat1Baru) && listAlamat1.isEmpty()) {
            PermohonanPihakKemaskini mpk = new PermohonanPihakKemaskini();
            mpk.setPermohonan(idWakil);
            if (permohonan.getSenaraiHakmilik().size() == 1) {
                mpk.setHakmilik(permohonan.getSenaraiHakmilik().get(0).getHakmilik());
            }
            mpk.setNamaMedan("ALAMAT1_PNERIMA");
            mpk.setNilaiLama(alamat1);
            mpk.setNilaiBaru(alamat1Baru);
            mpk.setInfoAudit(ia);
            mpk.setCawangan(pengguna.getKodCawangan());
            mpk.setIdPermohonanLama(idPermohonan);
            pService.saveKKini(mpk);
            count++;
        } else {
            for (PermohonanPihakKemaskini ln : listAlamat1) {
                ln.setNilaiBaru(alamat1Baru);
                ln.setInfoAudit(ia2);
                pService.saveKKini(ln);
                count++;
            }
        }

        if (StringUtils.isNotBlank(alamat2Baru) && listAlamat2.isEmpty()) {
            PermohonanPihakKemaskini mpk = new PermohonanPihakKemaskini();
            mpk.setPermohonan(idWakil);
            if (permohonan.getSenaraiHakmilik().size() == 1) {
                mpk.setHakmilik(permohonan.getSenaraiHakmilik().get(0).getHakmilik());
            }
            mpk.setNamaMedan("ALAMAT2_PNERIMA");
            mpk.setNilaiLama(alamat2);
            mpk.setNilaiBaru(alamat2Baru);
            mpk.setInfoAudit(ia);
            mpk.setCawangan(pengguna.getKodCawangan());
            mpk.setIdPermohonanLama(idPermohonan);
            pService.saveKKini(mpk);
            count++;
        } else {
            for (PermohonanPihakKemaskini ln : listAlamat2) {
                ln.setNilaiBaru(alamat2Baru);
                ln.setInfoAudit(ia2);
                pService.saveKKini(ln);
                count++;
            }
        }

        if (StringUtils.isNotBlank(alamat3Baru) && listAlamat3.isEmpty()) {
            PermohonanPihakKemaskini mpk = new PermohonanPihakKemaskini();
            mpk.setPermohonan(idWakil);
            if (permohonan.getSenaraiHakmilik().size() == 1) {
                mpk.setHakmilik(permohonan.getSenaraiHakmilik().get(0).getHakmilik());
            }
            mpk.setNamaMedan("ALAMAT3_PNERIMA");
            mpk.setNilaiLama(alamat3);
            mpk.setNilaiBaru(alamat3Baru);
            mpk.setInfoAudit(ia);
            mpk.setCawangan(pengguna.getKodCawangan());
            mpk.setIdPermohonanLama(idPermohonan);
            pService.saveKKini(mpk);
            count++;
        } else {
            for (PermohonanPihakKemaskini ln : listAlamat3) {
                ln.setNilaiBaru(alamat3Baru);
                ln.setInfoAudit(ia2);
                pService.saveKKini(ln);
                count++;
            }
        }

        if (StringUtils.isNotBlank(alamat4Baru) && listAlamat4.isEmpty()) {
            PermohonanPihakKemaskini mpk = new PermohonanPihakKemaskini();
            mpk.setPermohonan(idWakil);
            if (permohonan.getSenaraiHakmilik().size() == 1) {
                mpk.setHakmilik(permohonan.getSenaraiHakmilik().get(0).getHakmilik());
            }
            mpk.setNamaMedan("ALAMAT4_PNERIMA");
            mpk.setNilaiLama(alamat4);
            mpk.setNilaiBaru(alamat4Baru);
            mpk.setInfoAudit(ia);
            mpk.setCawangan(pengguna.getKodCawangan());
            mpk.setIdPermohonanLama(idPermohonan);
            pService.saveKKini(mpk);
            count++;
        } else {
            for (PermohonanPihakKemaskini ln : listAlamat4) {
                ln.setNilaiBaru(alamat4Baru);
                ln.setInfoAudit(ia2);
                pService.saveKKini(ln);
                count++;
            }
        }

        if (StringUtils.isNotBlank(poskodBaru) && listPoskod.isEmpty()) {
            PermohonanPihakKemaskini mpk = new PermohonanPihakKemaskini();
            mpk.setPermohonan(idWakil);
            if (permohonan.getSenaraiHakmilik().size() == 1) {
                mpk.setHakmilik(permohonan.getSenaraiHakmilik().get(0).getHakmilik());
            }
            mpk.setNamaMedan("POSKOD_PNERIMA");
            mpk.setNilaiLama(poskod);
            mpk.setNilaiBaru(poskodBaru);
            mpk.setInfoAudit(ia);
            mpk.setCawangan(pengguna.getKodCawangan());
            mpk.setIdPermohonanLama(idPermohonan);
            pService.saveKKini(mpk);
            count++;
        } else {
            for (PermohonanPihakKemaskini ln : listPoskod) {
                ln.setNilaiBaru(poskodBaru);
                ln.setInfoAudit(ia2);
                pService.saveKKini(ln);
                count++;
            }
        }

        if (StringUtils.isNotBlank(kodNegeriBaru) && listKodNegeri.isEmpty()) {
            PermohonanPihakKemaskini mpk = new PermohonanPihakKemaskini();
            mpk.setPermohonan(idWakil);
            if (permohonan.getSenaraiHakmilik().size() == 1) {
                mpk.setHakmilik(permohonan.getSenaraiHakmilik().get(0).getHakmilik());
            }
            mpk.setNamaMedan("KOD_NEGERI_PNERIMA");
            mpk.setNilaiLama(kodNegeri);
            mpk.setNilaiBaru(kodNegeriBaru);
            mpk.setInfoAudit(ia);
            mpk.setCawangan(pengguna.getKodCawangan());
            mpk.setIdPermohonanLama(idPermohonan);
            pService.saveKKini(mpk);
            count++;
        } else {
            for (PermohonanPihakKemaskini ln : listKodNegeri) {
                ln.setNilaiBaru(kodNegeriBaru);
                ln.setInfoAudit(ia2);
                pService.saveKKini(ln);
                count++;
            }
        }

        if (count > 0) {
            addSimpleMessage("Data berjaya disimpan.");
        } else {
            addSimpleError("Data tidak berjaya disimpan.");
        }
        wakilKuasaPemberi = pService.findWakilKuasaPemberi(idSurat);
        wakilKuasaPenerima = pService.findWakilKuasaPenerima(idSurat);

        return new JSP("daftar/maklumat_surat_popup.jsp").addParameter("popup", "true");
    }

    public Resolution popupSurat() {
        idSurat = (String) getContext().getRequest().getParameter("idPembetulan");
        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = permohonanDAO.findById(idPermohonan);

        wakilKuasa = pService.findWakilKuasa(idSurat);
        wakilKuasaPemberi = pService.findWakilKuasaPemberi(idSurat);
        wakilKuasaPenerima = pService.findWakilKuasaPenerima(idSurat);

        List<PermohonanPihakKemaskini> listNama = pService.findListbyIdMohonIdHmMedanIdlama(idSurat,
                permohonan.getSenaraiHakmilik().get(0).getHakmilik().getIdHakmilik(), "NAMA", idPermohonan);
        List<PermohonanPihakKemaskini> listNoPengenalan = pService.findListbyIdMohonIdHmMedanIdlama(idSurat,
                permohonan.getSenaraiHakmilik().get(0).getHakmilik().getIdHakmilik(), "NO_PENGENALAN", idPermohonan);
        List<PermohonanPihakKemaskini> listKodPengenalan = pService.findListbyIdMohonIdHmMedanIdlama(idSurat,
                permohonan.getSenaraiHakmilik().get(0).getHakmilik().getIdHakmilik(), "KOD_PENGENALAN", idPermohonan);
        List<PermohonanPihakKemaskini> listNamaPenerima = pService.findListbyIdMohonIdHmMedanIdlama(idSurat,
                permohonan.getSenaraiHakmilik().get(0).getHakmilik().getIdHakmilik(), "NAMA_PNERIMA", idPermohonan);
        List<PermohonanPihakKemaskini> listNoPengenalanPenerima = pService.findListbyIdMohonIdHmMedanIdlama(idSurat,
                permohonan.getSenaraiHakmilik().get(0).getHakmilik().getIdHakmilik(), "NO_PENGENALAN_PNERIMA", idPermohonan);
        List<PermohonanPihakKemaskini> listKodPengenalanPenerima = pService.findListbyIdMohonIdHmMedanIdlama(idSurat,
                permohonan.getSenaraiHakmilik().get(0).getHakmilik().getIdHakmilik(), "KOD_PENGENALAN_PNERIMA", idPermohonan);
        List<PermohonanPihakKemaskini> listAlamat1 = pService.findListbyIdMohonIdHmMedanIdlama(idSurat,
                permohonan.getSenaraiHakmilik().get(0).getHakmilik().getIdHakmilik(), "ALAMAT1_PNERIMA", idPermohonan);
        List<PermohonanPihakKemaskini> listAlamat2 = pService.findListbyIdMohonIdHmMedanIdlama(idSurat,
                permohonan.getSenaraiHakmilik().get(0).getHakmilik().getIdHakmilik(), "ALAMAT2_PNERIMA", idPermohonan);
        List<PermohonanPihakKemaskini> listAlamat3 = pService.findListbyIdMohonIdHmMedanIdlama(idSurat,
                permohonan.getSenaraiHakmilik().get(0).getHakmilik().getIdHakmilik(), "ALAMAT3_PNERIMA", idPermohonan);
        List<PermohonanPihakKemaskini> listAlamat4 = pService.findListbyIdMohonIdHmMedanIdlama(idSurat,
                permohonan.getSenaraiHakmilik().get(0).getHakmilik().getIdHakmilik(), "ALAMAT4_PNERIMA", idPermohonan);
        List<PermohonanPihakKemaskini> listPoskod = pService.findListbyIdMohonIdHmMedanIdlama(idSurat,
                permohonan.getSenaraiHakmilik().get(0).getHakmilik().getIdHakmilik(), "POSKOD_PNERIMA", idPermohonan);
        List<PermohonanPihakKemaskini> listKodNegeri = pService.findListbyIdMohonIdHmMedanIdlama(idSurat,
                permohonan.getSenaraiHakmilik().get(0).getHakmilik().getIdHakmilik(), "KOD_NEGERI_PNERIMA", idPermohonan);

        for (PermohonanPihakKemaskini ls : listNama) {
            namaBaru = ls.getNilaiBaru();
        }
        for (PermohonanPihakKemaskini ls : listNoPengenalan) {
            noPengenalanBaru = ls.getNilaiBaru();
        }
        for (PermohonanPihakKemaskini ls : listKodPengenalan) {
            kodPengenalanBaru = ls.getNilaiBaru();
        }
        for (PermohonanPihakKemaskini ls : listNamaPenerima) {
            namaPenerimaBaru = ls.getNilaiBaru();
        }
        for (PermohonanPihakKemaskini ls : listNoPengenalanPenerima) {
            noPengenalanPenerimaBaru = ls.getNilaiBaru();
        }
        for (PermohonanPihakKemaskini ls : listKodPengenalanPenerima) {
            kodPengenalanPenerimaBaru = ls.getNilaiBaru();
        }
        for (PermohonanPihakKemaskini ls : listAlamat1) {
            alamat1Baru = ls.getNilaiBaru();
        }
        for (PermohonanPihakKemaskini ls : listAlamat2) {
            alamat2Baru = ls.getNilaiBaru();
        }
        for (PermohonanPihakKemaskini ls : listAlamat3) {
            alamat3Baru = ls.getNilaiBaru();
        }
        for (PermohonanPihakKemaskini ls : listAlamat4) {
            alamat4Baru = ls.getNilaiBaru();
        }
        for (PermohonanPihakKemaskini ls : listPoskod) {
            poskodBaru = ls.getNilaiBaru();
        }
        for (PermohonanPihakKemaskini ls : listKodNegeri) {
            kodNegeriBaru = ls.getNilaiBaru();
        }
        if (wakilKuasaPemberi != null && wakilKuasaPenerima != null) {
            nama = wakilKuasaPemberi.getNama();
            noPengenalan = wakilKuasaPemberi.getNoPengenalan();
            kodPengenalan = wakilKuasaPemberi.getKodPengenalan();

            namaPenerima = wakilKuasaPenerima.getNama();
            noPengenalanPenerima = wakilKuasaPenerima.getNoPengenalan();
            kodPengenalanPenerima = wakilKuasaPenerima.getJenisPengenalan().getKod();
            alamat1 = wakilKuasaPenerima.getAlamat1();
            alamat2 = wakilKuasaPenerima.getAlamat2();
            alamat3 = wakilKuasaPenerima.getAlamat3();
            alamat4 = wakilKuasaPenerima.getAlamat4();
            poskod = wakilKuasaPenerima.getPoskod();
            if (wakilKuasaPenerima.getNegeri() != null) {
                kodNegeri = wakilKuasaPenerima.getNegeri().getKod();
            }
        } else {
            addSimpleError("Maklumat surat tidak dijumpai.");
        }

        return new JSP("daftar/maklumat_surat_popup.jsp").addParameter("popup", "true");
    }

//    public Resolution delete() {
//        idSurat = (String) getContext().getRequest().getParameter("idPembetulan");
//        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
//        permohonan = permohonanDAO.findById(idPermohonan);
//
//        wakilKuasa = pService.findWakilKuasa(idSurat);
//        wakilKuasaPemberi = pService.findWakilKuasaPemberi(idSurat);
//        wakilKuasaPenerima = pService.findWakilKuasaPenerima(idSurat);
//
//        if (wakilKuasa != null) {
//            wakilKuasa.setSyaratTambahan("T");
//            pService.saveWakilKuasa(wakilKuasa);
//        }
//
//        return new JSP("daftar/maklumat_surat.jsp").addParameter("tab", "true");
//    }

    public Permohonan getPermohonan() {
        return permohonan;
    }

    public void setPermohonan(Permohonan permohonan) {
        this.permohonan = permohonan;
    }

    public List<HakmilikUrusan> getHakmilikUrusanSC() {
        return hakmilikUrusanSC;
    }

    public void setHakmilikUrusanSC(List<HakmilikUrusan> hakmilikUrusanSC) {
        this.hakmilikUrusanSC = hakmilikUrusanSC;
    }

    public List<HakmilikUrusan> getHakmilikUrusanN() {
        return hakmilikUrusanN;
    }

    public void setHakmilikUrusanN(List<HakmilikUrusan> hakmilikUrusanN) {
        this.hakmilikUrusanN = hakmilikUrusanN;
    }

    public List<HakmilikUrusan> getHakmilikUrusanB() {
        return hakmilikUrusanB;
    }

    public void setHakmilikUrusanB(List<HakmilikUrusan> hakmilikUrusanB) {
        this.hakmilikUrusanB = hakmilikUrusanB;
    }

    public List<HakmilikPermohonan> getHakmilikPermohonanList() {
        return hakmilikPermohonanList;
    }

    public void setHakmilikPermohonanList(List<HakmilikPermohonan> hakmilikPermohonanList) {
        this.hakmilikPermohonanList = hakmilikPermohonanList;
    }

    public List<HakmilikUrusan> getSenaraiSurat() {
        return senaraiSurat;
    }

    public void setSenaraiSurat(List<HakmilikUrusan> senaraiSurat) {
        this.senaraiSurat = senaraiSurat;
    }

    public List<Dokumen> getSenaraiDokumen() {
        return senaraiDokumen;
    }

    public void setSenaraiDokumen(List<Dokumen> senaraiDokumen) {
        this.senaraiDokumen = senaraiDokumen;
    }

    public WakilKuasa getWakilKuasa() {
        return wakilKuasa;
    }

    public void setWakilKuasa(WakilKuasa wakilKuasa) {
        this.wakilKuasa = wakilKuasa;
    }

    public WakilKuasaPenerima getWakilKuasaPenerima() {
        return wakilKuasaPenerima;
    }

    public void setWakilKuasaPenerima(WakilKuasaPenerima wakilKuasaPenerima) {
        this.wakilKuasaPenerima = wakilKuasaPenerima;
    }

    public WakilKuasaPemberi getWakilKuasaPemberi() {
        return wakilKuasaPemberi;
    }

    public void setWakilKuasaPemberi(WakilKuasaPemberi wakilKuasaPemberi) {
        this.wakilKuasaPemberi = wakilKuasaPemberi;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getNoPengenalan() {
        return noPengenalan;
    }

    public void setNoPengenalan(String noPengenalan) {
        this.noPengenalan = noPengenalan;
    }

    public String getKodPengenalan() {
        return kodPengenalan;
    }

    public void setKodPengenalan(String kodPengenalan) {
        this.kodPengenalan = kodPengenalan;
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

    public String getKodNegeri() {
        return kodNegeri;
    }

    public void setKodNegeri(String kodNegeri) {
        this.kodNegeri = kodNegeri;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getNamaPenerima() {
        return namaPenerima;
    }

    public void setNamaPenerima(String namaPenerima) {
        this.namaPenerima = namaPenerima;
    }

    public String getNoPengenalanPenerima() {
        return noPengenalanPenerima;
    }

    public void setNoPengenalanPenerima(String noPengenalanPenerima) {
        this.noPengenalanPenerima = noPengenalanPenerima;
    }

    public String getKodPengenalanPenerima() {
        return kodPengenalanPenerima;
    }

    public void setKodPengenalanPenerima(String kodPengenalanPenerima) {
        this.kodPengenalanPenerima = kodPengenalanPenerima;
    }

    public String getNamaBaru() {
        return namaBaru;
    }

    public void setNamaBaru(String namaBaru) {
        this.namaBaru = namaBaru;
    }

    public String getNoPengenalanBaru() {
        return noPengenalanBaru;
    }

    public void setNoPengenalanBaru(String noPengenalanBaru) {
        this.noPengenalanBaru = noPengenalanBaru;
    }

    public String getKodPengenalanBaru() {
        return kodPengenalanBaru;
    }

    public void setKodPengenalanBaru(String kodPengenalanBaru) {
        this.kodPengenalanBaru = kodPengenalanBaru;
    }

    public String getNamaPenerimaBaru() {
        return namaPenerimaBaru;
    }

    public void setNamaPenerimaBaru(String namaPenerimaBaru) {
        this.namaPenerimaBaru = namaPenerimaBaru;
    }

    public String getNoPengenalanPenerimaBaru() {
        return noPengenalanPenerimaBaru;
    }

    public void setNoPengenalanPenerimaBaru(String noPengenalanPenerimaBaru) {
        this.noPengenalanPenerimaBaru = noPengenalanPenerimaBaru;
    }

    public String getKodPengenalanPenerimaBaru() {
        return kodPengenalanPenerimaBaru;
    }

    public void setKodPengenalanPenerimaBaru(String kodPengenalanPenerimaBaru) {
        this.kodPengenalanPenerimaBaru = kodPengenalanPenerimaBaru;
    }

    public String getAlamat1Baru() {
        return alamat1Baru;
    }

    public void setAlamat1Baru(String alamat1Baru) {
        this.alamat1Baru = alamat1Baru;
    }

    public String getAlamat2Baru() {
        return alamat2Baru;
    }

    public void setAlamat2Baru(String alamat2Baru) {
        this.alamat2Baru = alamat2Baru;
    }

    public String getAlamat3Baru() {
        return alamat3Baru;
    }

    public void setAlamat3Baru(String alamat3Baru) {
        this.alamat3Baru = alamat3Baru;
    }

    public String getAlamat4Baru() {
        return alamat4Baru;
    }

    public void setAlamat4Baru(String alamat4Baru) {
        this.alamat4Baru = alamat4Baru;
    }

    public String getPoskodBaru() {
        return poskodBaru;
    }

    public void setPoskodBaru(String poskodBaru) {
        this.poskodBaru = poskodBaru;
    }

    public String getKodNegeriBaru() {
        return kodNegeriBaru;
    }

    public void setKodNegeriBaru(String kodNegeriBaru) {
        this.kodNegeriBaru = kodNegeriBaru;
    }

    public String getIdSurat() {
        return idSurat;
    }

    public void setIdSurat(String idSurat) {
        this.idSurat = idSurat;
    }

}
