/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.stripes.lelong;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.dao.EnkuiriDAO;
import etanah.dao.KehadiranDAO;
import etanah.dao.KodJenisPengenalanDAO;
import etanah.dao.PeguamDAO;
import etanah.dao.PermohonanDAO;
import etanah.dao.PermohonanPihakDAO;
import etanah.dao.SenaraiRujukanDAO;
import etanah.model.Enkuiri;
import etanah.model.HakmilikPermohonan;
import etanah.model.HakmilikPihakBerkepentingan;
import etanah.model.InfoAudit;
import etanah.model.Kehadiran;
import etanah.model.KodJenisPengenalan;
import etanah.model.Peguam;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.model.PermohonanAtasPerserahan;
import etanah.model.PermohonanPihak;
import etanah.model.SenaraiRujukan;
import etanah.service.common.LelongService;
import etanah.view.etanahActionBeanContext;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.StreamingResolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

/**
 *
 * @author Administrator
 */
@UrlBinding("/lelong/maklumat_kehadiran_adat")
public class MaklumatKehadiranTanahAdatActionBean extends AbleActionBean {

    private static final Logger LOG = Logger.getLogger(MaklumatKehadiranTanahAdatActionBean.class);
    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    PermohonanPihakDAO permohonanPihakDAO;
    @Inject
    EnkuiriDAO enkuiriDAO;
    @Inject
    KehadiranDAO kehadiranDAO;
    @Inject
    KodJenisPengenalanDAO kodJenisPengenalanDAO;
    @Inject
    LelongService lelongService;
    @Inject
    PeguamDAO peguamDAO;
    @Inject
    SenaraiRujukanDAO senaraiRujukanDAO;
    private String idPermohonan;
    private Permohonan permohonan;
    private HakmilikPihakBerkepentingan hakmilikPihakBerkepentingan;
    private Enkuiri enkuiri;
    private Kehadiran kehadiran;
    private Kehadiran kehadiranla;
    private Kehadiran kkk;
    private List<HakmilikPihakBerkepentingan> senaraiHakmilikPihak;
    private List<Kehadiran> listKehadiran = new ArrayList<Kehadiran>();
    private List<Kehadiran> listKehadiran2 = new ArrayList<Kehadiran>();
    private List<HakmilikPihakBerkepentingan> listHakmilikPihakBerkepentingan;
    private String catatan;
    private String hadirYaKeTidak;
    private String keterang;
    private String noRujukan;
    private boolean btn = false;
    private boolean xhadir = false;
    private Pengguna pengguna;
    private String jenis;
    private long idMP;
    private Peguam peguam;
    private SenaraiRujukan senaraiRujukan;

    @DefaultHandler
    public Resolution showForm() {
        return new JSP("lelong/maklumat_kehadiran_adat.jsp").addParameter("tab", "true");
    }

    public Resolution showForm3() {
        btn = true;
        return new JSP("lelong/maklumat_kehadiran_adat.jsp").addParameter("tab", "true");
    }

    public Resolution tambahBarule() {

        String id = getContext().getRequest().getParameter("id");
        getContext().getRequest().setAttribute("id", id);
        String idPihak = getContext().getRequest().getParameter("idPihak");
//        listKehadiran = lelongService.getListWakil(Long.parseLong(idPihak), idPermohonan);
        kkk = kehadiranDAO.findById(Long.parseLong(idPihak));
        if (kkk != null && kkk.getWakilJenisPengenalan() != null) {
            jenis = kkk.getWakilJenisPengenalan().getKod();
        }
        idMP = kkk.getIdKehadiran();
        LOG.info("idMP : " + idMP);
        return new JSP("lelong/tambah_pemeganggadai_wakil_adat.jsp").addParameter("popup", "true");
    }

    public void showForm2() {
        List<PermohonanPihak> listPP = lelongService.getSenaraiPmohonPihak(idPermohonan);
        InfoAudit info = pengguna.getInfoAudit();
        info.setDimasukOleh(pengguna);
        info.setTarikhMasuk(new java.util.Date());
        List<PermohonanAtasPerserahan> listPAP = lelongService.getPermohonanAtasPerserahan(idPermohonan);
        PermohonanAtasPerserahan pAP = listPAP.get(0);
        if (pAP.getUrusan().getKodUrusan().getKod().equals("GD") || pAP.getUrusan().getKodUrusan().getKod().equals("GDWM")) {

            for (int i = 0; i < senaraiHakmilikPihak.size(); i++) {
                if (!senaraiHakmilikPihak.get(i).getJenis().getKod().equals("PM")) {
                    PermohonanPihak pPihak = null;
                    hakmilikPihakBerkepentingan = new HakmilikPihakBerkepentingan();
                    hakmilikPihakBerkepentingan = senaraiHakmilikPihak.get(i);
                    long idPihak = hakmilikPihakBerkepentingan.getPihak().getIdPihak();
                    for (PermohonanPihak pp : listPP) {
                        if (pp.getPihak().getIdPihak() == idPihak) {
                            pPihak = pp;
                            break;
                        }
                    }
                    Kehadiran hadir = new Kehadiran();
                    if (pPihak != null) {
                        hadir.setPermohonanPihak(pPihak);
                    }
                    hadir.setInfoAudit(info);
                    hadir.setHakmilikPihakBerkepentingan(senaraiHakmilikPihak.get(i));
                    hadir.setEnkuiri(enkuiri);
                    lelongService.saveOrUpdate(hadir);
                }
            }
            Map<Long, PermohonanPihak> pihakMap = new HashMap<Long, PermohonanPihak>();
            listPP = lelongService.getSenaraiPmohonPihakPM(idPermohonan);
            if (!listPP.isEmpty()) {
                for (PermohonanPihak hp : listPP) {
                    Long id = hp.getPihak().getIdPihak();
                    if (pihakMap.containsKey(id)) {
                        continue;
                    } else {
                        pihakMap.put(id, hp);
                    }
                }
                listPP = new ArrayList(pihakMap.values());
                for (PermohonanPihak pp : listPP) {
                    HakmilikPihakBerkepentingan hb = null;
                    long idPihak = pp.getPihak().getIdPihak();
                    for (int k = 0; k < senaraiHakmilikPihak.size(); k++) {
                        if (senaraiHakmilikPihak.get(k).getPihak().getIdPihak() == idPihak) {
                            hb = senaraiHakmilikPihak.get(k);
                            break;
                        }
                    }
                    Kehadiran hadir = new Kehadiran();
                    hadir.setPermohonanPihak(pp);
                    hadir.setInfoAudit(info);
                    hadir.setHakmilikPihakBerkepentingan(hb);
                    hadir.setEnkuiri(enkuiri);
                    lelongService.saveOrUpdate(hadir);
                }
            }
        }
        if (pAP.getUrusan().getKodUrusan().getKod().equals("GDPJ") || pAP.getUrusan().getKodUrusan().getKod().equals("GDPJK")) {
            for (int i = 0; i < senaraiHakmilikPihak.size(); i++) {
                PermohonanPihak pPihak = null;
                hakmilikPihakBerkepentingan = new HakmilikPihakBerkepentingan();
                hakmilikPihakBerkepentingan = senaraiHakmilikPihak.get(i);
                long idPihak = hakmilikPihakBerkepentingan.getPihak().getIdPihak();
                for (PermohonanPihak pp : listPP) {
                    if (pp.getPihak().getIdPihak() == idPihak) {
                        pPihak = pp;
                        break;
                    }
                }
                Kehadiran hadir = new Kehadiran();
                if (pPihak != null) {
                    hadir.setPermohonanPihak(pPihak);
                }
                hadir.setInfoAudit(info);
                hadir.setHakmilikPihakBerkepentingan(senaraiHakmilikPihak.get(i));
                hadir.setEnkuiri(enkuiri);
                lelongService.saveOrUpdate(hadir);
            }
        }
        String id = permohonan.getIdPenyerah().toString();
        LOG.info("permohonan.getIdPenyerah() : " + permohonan.getIdPenyerah());
        Kehadiran hadir = new Kehadiran();
        hadir.setIdPenyerah(Integer.parseInt(id));
        hadir.setKodPenyerah(permohonan.getKodPenyerah());
        hadir.setInfoAudit(info);
        hadir.setEnkuiri(enkuiri);
        lelongService.saveOrUpdate(hadir);
        rehydrate();
    }

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        pengguna = (Pengguna) context.getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        if (idPermohonan != null) {
            permohonan = permohonanDAO.findById(idPermohonan);
            enkuiri = lelongService.findEnkuiri(idPermohonan);
            List<String> listIDHakmilik = new ArrayList<String>();
            for (HakmilikPermohonan hp : permohonan.getSenaraiHakmilik()) {
                listIDHakmilik.add(hp.getHakmilik().getIdHakmilik());
            }
            LOG.info("listIDHakmilik : " + listIDHakmilik.size());
            List<HakmilikPihakBerkepentingan> listHP = lelongService.getHakmilikPihakALL(idPermohonan, listIDHakmilik);
            Map<Long, HakmilikPihakBerkepentingan> pihakMap = new HashMap<Long, HakmilikPihakBerkepentingan>();
            for (HakmilikPihakBerkepentingan hp : listHP) {
                Long id = hp.getPihak().getIdPihak();
                if (pihakMap.containsKey(id)) {
                    continue;
                } else {
                    pihakMap.put(id, hp);
                }
            }
            senaraiHakmilikPihak = new ArrayList(pihakMap.values());
            LOG.info("senaraiHakmilikPihak : " + senaraiHakmilikPihak.size());
            listHakmilikPihakBerkepentingan = lelongService.getHakmilikPihakBerkepentingan(idPermohonan);
            peguam = peguamDAO.findById(permohonan.getIdPenyerah());
            listKehadiran2 = new ArrayList<Kehadiran>();
            listKehadiran = lelongService.getListKehadiran(idPermohonan);
            if (listKehadiran.size() > 1) {
                for (Kehadiran kk : listKehadiran) {
                    kehadiran = kk;
                    if (kk.getIdPenyerah() == null) {
                        listKehadiran2.add(kk);
                    }
                    if (kehadiran.getIdPenyerah() == null) {
                        if (kehadiran.getHakmilikPihakBerkepentingan() != null && kehadiran.getHakmilikPihakBerkepentingan().getJenis().getKod().equals("PG")) {
                            LOG.info("--------PG---------");
                            if (!StringUtils.isEmpty(kehadiran.getHadir())) {
                                if ((kehadiran.getHadir().equals("N")) && (StringUtils.isEmpty(kehadiran.getWakilNama()))) {
                                    xhadir = true;
                                    LOG.info("----------Tak hadir----------");
                                } else {
                                    LOG.info("----------Hadir----------");
                                    xhadir = false;
                                }
                            }
                        }
                    }
                }
            } else {
                showForm2();
            }
        }
    }

    public Resolution simpanWakil() {
        //simpan maklumat wakil
        Kehadiran ddd = kehadiranDAO.findById(idMP);
        LOG.info("idPermohonan : " + idPermohonan);
        LOG.info("idMP : " + idMP);
        InfoAudit info = pengguna.getInfoAudit();
        Kehadiran keke = new Kehadiran();
        keke = ddd;
        if (keke != null) {
            info.setDiKemaskiniOleh(pengguna);
            info.setTarikhKemaskini(new java.util.Date());
            keke.setInfoAudit(info);
            keke.setWakilNama(kkk.getWakilNama());
            KodJenisPengenalan kod = kodJenisPengenalanDAO.findById(jenis);
            keke.setWakilJenisPengenalan(kod);
            keke.setWakilNoPengenalan(kkk.getWakilNoPengenalan());
            lelongService.saveOrUpdate(keke);
        }

        addSimpleMessage("Maklumat Telah Berjaya Disimpan...");

        return new JSP("lelong/maklumat_kehadiran_adat.jsp").addParameter("tab", "true");

    }

    public Resolution refreshpage() {

        rehydrate();

        addSimpleMessage("Maklumat Telah Berjaya Disimpan...");
        return new JSP("lelong/maklumat_kehadiran_adat.jsp").addParameter("tab", "true");
    }

    public Resolution save() {

        for (int i = 0; i < listKehadiran.size(); i++) {

            kehadiran = listKehadiran.get(i);
            hadirYaKeTidak = getContext().getRequest().getParameter("rb" + i);
            catatan = getContext().getRequest().getParameter("catatkan" + i);
            keterang = getContext().getRequest().getParameter("terangan" + i);

            if (hadirYaKeTidak.equals("N")) {

                kehadiran.setHadir("N");
            }
            if (hadirYaKeTidak.equals("Y")) {

                kehadiran.setHadir("Y");
            }

            kehadiran.setCatatan(catatan);
            kehadiran.setKeterangan(keterang);
            lelongService.saveOrUpdate(kehadiran);
        }
        lelongService.saveOrUpdate(enkuiri);
        rehydrate();
        addSimpleMessage("Maklumat Telah Berjaya Disimpan...");
        return new JSP("lelong/maklumat_kehadiran_adat.jsp").addParameter("tab", "true");
    }

    public Resolution deleteWakil() {
        LOG.info("-----delete-----");
        String idKehadiran = getContext().getRequest().getParameter("idKehadiran");
        String id = getContext().getRequest().getParameter("id");
        LOG.info("idKehadiran : " + idKehadiran);
        Kehadiran dd = kehadiranDAO.findById(Long.parseLong(idKehadiran));
        dd.setWakilSuratLantikan(null);
        dd.setWakilNama(null);
        dd.setWakilJenisPengenalan(null);
        dd.setWakilNoPengenalan(null);
        lelongService.saveOrUpdate(dd);
        return new StreamingResolution("text/plain", id);
    }

    public String getIdPermohonan() {
        return idPermohonan;
    }

    public void setIdPermohonan(String idPermohonan) {
        this.idPermohonan = idPermohonan;
    }

    public Permohonan getPermohonan() {
        return permohonan;
    }

    public void setPermohonan(Permohonan permohonan) {
        this.permohonan = permohonan;
    }

    public List<HakmilikPihakBerkepentingan> getSenaraiHakmilikPihak() {
        return senaraiHakmilikPihak;
    }

    public void setSenaraiHakmilikPihak(List<HakmilikPihakBerkepentingan> senaraiHakmilikPihak) {
        this.senaraiHakmilikPihak = senaraiHakmilikPihak;
    }

    public HakmilikPihakBerkepentingan getHakmilikPihakBerkepentingan() {
        return hakmilikPihakBerkepentingan;
    }

    public void setHakmilikPihakBerkepentingan(HakmilikPihakBerkepentingan hakmilikPihakBerkepentingan) {
        this.hakmilikPihakBerkepentingan = hakmilikPihakBerkepentingan;
    }

    public Enkuiri getEnkuiri() {
        return enkuiri;
    }

    public void setEnkuiri(Enkuiri enkuiri) {
        this.enkuiri = enkuiri;
    }

    public Kehadiran getKehadiran() {
        return kehadiran;
    }

    public void setKehadiran(Kehadiran kehadiran) {
        this.kehadiran = kehadiran;
    }

    public List<Kehadiran> getListKehadiran() {
        return listKehadiran;
    }

    public void setListKehadiran(List<Kehadiran> listKehadiran) {
        this.listKehadiran = listKehadiran;
    }

    public Kehadiran getKehadiranla() {
        return kehadiranla;
    }

    public void setKehadiranla(Kehadiran kehadiranla) {
        this.kehadiranla = kehadiranla;
    }

    public Kehadiran getKkk() {
        return kkk;
    }

    public void setKkk(Kehadiran kkk) {
        this.kkk = kkk;
    }

    public String getCatatan() {
        return catatan;
    }

    public void setCatatan(String catatan) {
        this.catatan = catatan;
    }

    public String getHadirYaKeTidak() {
        return hadirYaKeTidak;
    }

    public void setHadirYaKeTidak(String hadirYaKeTidak) {
        this.hadirYaKeTidak = hadirYaKeTidak;
    }

    public String getNoRujukan() {
        return noRujukan;
    }

    public void setNoRujukan(String noRujukan) {
        this.noRujukan = noRujukan;
    }

    public String getKeterang() {
        return keterang;
    }

    public void setKeterang(String keterang) {
        this.keterang = keterang;
    }

    public boolean isBtn() {
        return btn;
    }

    public void setBtn(boolean btn) {
        this.btn = btn;
    }

    public boolean isXhadir() {
        return xhadir;
    }

    public void setXhadir(boolean xhadir) {
        this.xhadir = xhadir;
    }

    public String getJenis() {
        return jenis;
    }

    public void setJenis(String jenis) {
        this.jenis = jenis;
    }

    public Pengguna getPengguna() {
        return pengguna;
    }

    public void setPengguna(Pengguna pengguna) {
        this.pengguna = pengguna;
    }

    public long getIdMP() {
        return idMP;
    }

    public void setIdMP(long idMP) {
        this.idMP = idMP;
    }

    public Peguam getPeguam() {
        return peguam;
    }

    public void setPeguam(Peguam peguam) {
        this.peguam = peguam;
    }

    public SenaraiRujukan getSenaraiRujukan() {
        return senaraiRujukan;
    }

    public void setSenaraiRujukan(SenaraiRujukan senaraiRujukan) {
        this.senaraiRujukan = senaraiRujukan;
    }

    public List<Kehadiran> getListKehadiran2() {
        return listKehadiran2;
    }

    public void setListKehadiran2(List<Kehadiran> listKehadiran2) {
        this.listKehadiran2 = listKehadiran2;
    }

    public List<HakmilikPihakBerkepentingan> getListHakmilikPihakBerkepentingan() {
        return listHakmilikPihakBerkepentingan;
    }

    public void setListHakmilikPihakBerkepentingan(List<HakmilikPihakBerkepentingan> listHakmilikPihakBerkepentingan) {
        this.listHakmilikPihakBerkepentingan = listHakmilikPihakBerkepentingan;
    }
    
}
