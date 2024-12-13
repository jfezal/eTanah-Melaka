/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.penguatkuasaan;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.dao.AduanOrangKenaSyakDAO;
import etanah.dao.HakmilikPermohonanDAO;
import etanah.dao.HakmilikPihakBerkepentinganDAO;
import etanah.model.HakmilikPermohonan;
import etanah.model.HakmilikPihakBerkepentingan;
import etanah.model.Hakmilik;
import etanah.model.Alamat;
import etanah.dao.PermohonanDAO;
import etanah.model.AduanOrangKenaSyak;
import etanah.model.EnkuiriPenguatkuasaan;
import etanah.model.Permohonan;
//import etanah.model.KodStatusBarangRampasan;
import etanah.model.EnkuiriPenguatkuasaanKehadiran;
import etanah.model.InfoAudit;
import etanah.model.Pengguna;
import etanah.service.EnforceService;
import etanah.service.common.EnforcementService;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;
import org.apache.log4j.Logger;
import etanah.view.etanahActionBeanContext;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author farah.shafilla
 */
@UrlBinding("/penguatkuasaan/kehadiran_enkuiri")
public class KehadiranEnkuiriActionBean extends AbleActionBean {

    private static Logger logger = Logger.getLogger(KehadiranEnkuiriActionBean.class);
    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    AduanOrangKenaSyakDAO aduanOrangKenaSyakDAO;
    @Inject
    EnforcementService enforcementService;
    @Inject
    EnforceService enforceService;
    @Inject
    HakmilikPermohonanDAO hakmilikPermohonanDAO;
    @Inject
    HakmilikPihakBerkepentinganDAO hakmilikPihakBerkepentinganDAO;
    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;
    private Permohonan permohonan;
    private AduanOrangKenaSyak aduanOrangKenaSyak;
    private EnkuiriPenguatkuasaan enkuiriPenguatkuasaan;
    private EnkuiriPenguatkuasaanKehadiran enkuiriKehadiran;
    private List<AduanOrangKenaSyak> senaraiOKS;
    private List<AduanOrangKenaSyak> senaraiHadir;
    private List<EnkuiriPenguatkuasaan> senaraiEnkuiri;
    private List<EnkuiriPenguatkuasaanKehadiran> senaraiKehadiran;
    private List<HakmilikPermohonan> hakmilikPermohonanList;
    private List<HakmilikPihakBerkepentingan> pihakKepentinganList;
    private Hakmilik hakmilik;
    private String catatan;
    private String hadir;
    private String denda;
    private Integer jumKehadiran;
    private Boolean kemaskini = false;

    @DefaultHandler
    public Resolution showForm() {
        getContext().getRequest().setAttribute("form", Boolean.TRUE);
        return new JSP("penguatkuasaan/kehadiran_enkuiri.jsp").addParameter("tab", "true");
    }

    public Resolution showForm2() {
        getContext().getRequest().setAttribute("view", Boolean.TRUE);
        return new JSP("penguatkuasaan/kehadiran_enkuiri.jsp").addParameter("tab", "true");
    }

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {
        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        Pengguna pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        if (idPermohonan != null) {
            permohonan = permohonanDAO.findById(idPermohonan);
            senaraiEnkuiri = enforceService.getSenaraiEnkuiri(idPermohonan);
            if (senaraiEnkuiri.size() != 0) {
                enkuiriPenguatkuasaan = senaraiEnkuiri.get(0);
                if (enkuiriPenguatkuasaan != null) {
                    senaraiKehadiran = enkuiriPenguatkuasaan.getSenaraiKehadiran();
                    if (senaraiKehadiran.size() != 0) {
                        kemaskini = true;
                        senaraiKehadiran = enforcementService.findEnHadirByIDEnkuiri(enkuiriPenguatkuasaan.getIdEnkuiri());
                    } else {
                        kemaskini = false;
                        senaraiOKS = new ArrayList<AduanOrangKenaSyak>();
                        Session s = sessionProvider.get();
                        Query q = s.createQuery("from HakmilikPermohonan l where l.permohonan.idPermohonan = :idPermohonan");
                        q.setString("idPermohonan", permohonan.getIdPermohonan());
                        hakmilikPermohonanList = q.list();
                        if (hakmilikPermohonanList.size() != 0) {
                            for (HakmilikPermohonan hp : hakmilikPermohonanList) {
                                hakmilik = hp.getHakmilik();
                                break;
                            }
                            q = s.createQuery("from HakmilikPihakBerkepentingan l where l.hakmilik.idHakmilik = :idHakmilik");
                            q.setString("idHakmilik", hakmilik.getIdHakmilik());
                            pihakKepentinganList = q.list();
                            if (pihakKepentinganList.size() != 0) {
                                InfoAudit ia = new InfoAudit();
                                ia.setDimasukOleh(pengguna);
                                ia.setTarikhMasuk(new java.util.Date());
                                for (int y = 1; y <= pihakKepentinganList.size(); y++) {
                                    if (pihakKepentinganList.get(y - 1).getJenis().getKod().equals("PM")) {
                                        aduanOrangKenaSyak = new AduanOrangKenaSyak();
                                        aduanOrangKenaSyak.setNama(pihakKepentinganList.get(y - 1).getPihak().getNama());
                                        aduanOrangKenaSyak.setNoPengenalan(pihakKepentinganList.get(y - 1).getPihak().getNoPengenalan());
                                        Alamat al = new Alamat();
                                        al.setAlamat1(pihakKepentinganList.get(y - 1).getPihak().getAlamat1());
                                        al.setAlamat2(pihakKepentinganList.get(y - 1).getPihak().getAlamat2());
                                        al.setAlamat3(pihakKepentinganList.get(y - 1).getPihak().getAlamat3());
                                        al.setAlamat4(pihakKepentinganList.get(y - 1).getPihak().getAlamat4());
                                        al.setPoskod(pihakKepentinganList.get(y - 1).getPihak().getPoskod());
                                        al.setNegeri(pihakKepentinganList.get(y - 1).getPihak().getNegeri());
                                        aduanOrangKenaSyak.setAlamat(al);
                                        aduanOrangKenaSyak.setPermohonan(permohonan);
                                        aduanOrangKenaSyak.setCawangan(pengguna.getKodCawangan());
                                        aduanOrangKenaSyak.setInfoAudit(ia);
                                        enforceService.simpanAduanOrangDisyaki(aduanOrangKenaSyak);
                                        senaraiOKS.add(aduanOrangKenaSyak);
                                    }
                                }
                            }
                        }
                        senaraiKehadiran = new ArrayList<EnkuiriPenguatkuasaanKehadiran>();
                        for (int i = 1; i <= senaraiOKS.size(); i++) {
                            enkuiriKehadiran = new EnkuiriPenguatkuasaanKehadiran();
                            enkuiriKehadiran.setOrangKenaSyak(senaraiOKS.get(i - 1));
                            senaraiKehadiran.add(enkuiriKehadiran);
                        }
                    }
                }
            }
        }
    }

    public Resolution edit() {
        System.out.println("edit");
        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        InfoAudit ia = new InfoAudit();
        for (int i = 1; i <= senaraiKehadiran.size(); i++) {
            if (kemaskini == false) {
                enkuiriKehadiran = new EnkuiriPenguatkuasaanKehadiran();
                enkuiriKehadiran.setEnkuiri(enkuiriPenguatkuasaan);
                enkuiriKehadiran.setOrangKenaSyak(senaraiKehadiran.get(i - 1).getOrangKenaSyak());
                ia.setDimasukOleh(peng);
                ia.setTarikhMasuk(new java.util.Date());
            } else {
                enkuiriKehadiran = senaraiKehadiran.get(i - 1);
                ia = enkuiriKehadiran.getInfoAudit();
                ia.setDiKemaskiniOleh(peng);
                ia.setTarikhKemaskini(new java.util.Date());
            }
            catatan = getContext().getRequest().getParameter("catatan" + i);
            enkuiriKehadiran.setCatatan(catatan);
            hadir = (getContext().getRequest().getParameter("hadir" + i));
            if (hadir.equals("Y")) {
                enkuiriKehadiran.setHadir('Y');
            } else {
                enkuiriKehadiran.setHadir('T');
            }
            denda = (getContext().getRequest().getParameter("denda" + i));
            if (denda.equals("Y")) {
                enkuiriKehadiran.setKenaDenda('Y');
            } else {
                enkuiriKehadiran.setKenaDenda('T');
            }
            enkuiriKehadiran.setInfoAudit(ia);
            enkuiriKehadiran.setCawangan(peng.getKodCawangan());
            enforcementService.simpanEnkuiriHadir(enkuiriKehadiran);
        }
        addSimpleMessage("Maklumat telah berjaya disimpan.");
        getContext().getRequest().setAttribute("form", Boolean.TRUE);
        return new JSP("penguatkuasaan/kehadiran_enkuiri.jsp").addParameter("tab", "true");
    }

    public AduanOrangKenaSyak getAduanOrangKenaSyak() {
        return aduanOrangKenaSyak;
    }

    public void setAduanOrangKenaSyak(AduanOrangKenaSyak aduanOrangKenaSyak) {
        this.aduanOrangKenaSyak = aduanOrangKenaSyak;
    }

    public EnkuiriPenguatkuasaan getEnkuiriPenguatkuasaan() {
        return enkuiriPenguatkuasaan;
    }

    public void setEnkuiriPenguatkuasaan(EnkuiriPenguatkuasaan enkuiriPenguatkuasaan) {
        this.enkuiriPenguatkuasaan = enkuiriPenguatkuasaan;
    }

    public EnkuiriPenguatkuasaanKehadiran getEnkuiriKehadiran() {
        return enkuiriKehadiran;
    }

    public void setEnkuiriKehadiran(EnkuiriPenguatkuasaanKehadiran enkuiriKehadiran) {
        this.enkuiriKehadiran = enkuiriKehadiran;
    }

    public Permohonan getPermohonan() {
        return permohonan;
    }

    public void setPermohonan(Permohonan permohonan) {
        this.permohonan = permohonan;
    }

    public List<AduanOrangKenaSyak> getSenaraiOKS() {
        return senaraiOKS;
    }

    public void setSenaraiOKS(List<AduanOrangKenaSyak> senaraiOKS) {
        this.senaraiOKS = senaraiOKS;
    }

    public List<AduanOrangKenaSyak> getSenaraiHadir() {
        return senaraiHadir;
    }

    public void setSenaraiHadir(List<AduanOrangKenaSyak> senaraiHadir) {
        this.senaraiHadir = senaraiHadir;
    }

    public List<EnkuiriPenguatkuasaanKehadiran> getSenaraiKehadiran() {
        return senaraiKehadiran;
    }

    public void setSenaraiKehadiran(List<EnkuiriPenguatkuasaanKehadiran> senaraiKehadiran) {
        this.senaraiKehadiran = senaraiKehadiran;
    }

    public List<EnkuiriPenguatkuasaan> getSenaraiEnkuiri() {
        return senaraiEnkuiri;
    }

    public void setSenaraiEnkuiri(List<EnkuiriPenguatkuasaan> senaraiEnkuiri) {
        this.senaraiEnkuiri = senaraiEnkuiri;
    }

    public String getCatatan() {
        return catatan;
    }

    public void setCatatan(String catatan) {
        this.catatan = catatan;
    }

    public String getHadir() {
        return hadir;
    }

    public void setHadir(String hadir) {
        this.hadir = hadir;
    }

    public String getDenda() {
        return denda;
    }

    public void setDenda(String denda) {
        this.denda = denda;
    }

    public Integer getJumKehadiran() {
        return jumKehadiran;
    }

    public void setJumKehadiran(Integer jumKehadiran) {
        this.jumKehadiran = jumKehadiran;
    }

    public List<HakmilikPihakBerkepentingan> getPihakKepentinganList() {
        return pihakKepentinganList;
    }

    public void setPihakKepentinganList(List<HakmilikPihakBerkepentingan> pihakKepentinganList) {
        this.pihakKepentinganList = pihakKepentinganList;
    }

    public Hakmilik getHakmilik() {
        return hakmilik;
    }

    public void setHakmilik(Hakmilik hakmilik) {
        this.hakmilik = hakmilik;
    }

    public Boolean getKemaskini() {
        return kemaskini;
    }

    public void setKemaskini(Boolean kemaskini) {
        this.kemaskini = kemaskini;
    }
}
