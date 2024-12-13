/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.stripes.pengambilan.aduan.semakan;

import etanah.view.stripes.pengambilan.share.common.*;
import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.dao.PemohonDAO;
import etanah.dao.PermohonanDAO;
import etanah.dao.PihakDAO;
import etanah.model.Hakmilik;
import etanah.model.HakmilikPermohonan;
import etanah.model.HakmilikPihakBerkepentingan;
import etanah.model.InfoAudit;
import etanah.model.Pemohon;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.model.PermohonanPihak;
import etanah.model.Pihak;
import etanah.service.ConsentPtdService;
import etanah.service.common.HakmilikPermohonanService;
import etanah.service.common.HakmilikPihakKepentinganService;
import etanah.service.common.PemohonService;
import etanah.service.common.PermohonanPihakService;
import etanah.view.etanahActionBeanContext;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.StreamingResolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;

/**
 *
 * @author zipzap
 */

@UrlBinding("/pengambilan/aduan_kerosakan/semak/maklumat_asas")
public class MaklumatAsasPengambilanAduanActionBean extends AbleActionBean {

    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    PemohonDAO pemohonDAO;
    @Inject
    PihakDAO pihakDAO;
    @Inject
    ConsentPtdService conService;
    @Inject
    PemohonService pemohonService;
    Permohonan permohonan = new Permohonan();
    Pihak pihak = new Pihak();
    private String[] arrayCheckBox;
    private String idPermohonan;
    private List<Pihak> listTuanTanah;
    private List<Pemohon> listPemohon;
    private String display;
    private List<HakmilikPihakBerkepentingan> senaraiHakmilikPihak;

    @Inject
    HakmilikPermohonanService hakmilikpermohonanservice;
    @Inject
    PermohonanPihakService permohonanPihakService;
    @Inject
    HakmilikPihakKepentinganService hakmilikPihakKepentinganService;

    private List<HakmilikPermohonan> hakmilikPermohonanList2;
    private List<HakmilikPermohonan> hakmilikPermohonanList;
    private List<HakmilikPihakBerkepentingan> pihakKepentinganList;
    private List<HakmilikPihakBerkepentingan> listTuanTanahNew;
    private List<PermohonanPihak> mohonPihakList;
    private List<Pemohon> pemohonList;
    private List<HakmilikPihakBerkepentingan> pemilik;

    private int size = 0;
    private String[] syer1;
    private String[] syer2;

    @DefaultHandler
    public Resolution showForm() {
        
        System.out.println("start show form");
        //return new JSP("pengambilan/maklumat_hakmilik_pengambilan.jsp").addParameter("tab", "true");
        return new JSP("pengambilan/aduan_kerosakan/semak/maklumat_asasAPT.jsp").addParameter("tab", "true");
//        E:\Etanah\devel\etanahViewController\src\main\webapp\WEB-INF\jsp\pengambilan\APT\maklumat_asasAPT.jsp
    }

    public Resolution showForm2() {
        display = "display:none;";
        return new JSP("common/maklumat_pemohon.jsp").addParameter("tab", "true");
    }

    public Resolution showEditPemohon() {
        String idPihak = getContext().getRequest().getParameter("idPihak");

        pihak = pihakDAO.findById(Long.valueOf(idPihak));

        return new JSP("common/edit_pemohon2.jsp").addParameter("popup", "true");
    }

    public Resolution popup() {

        return new JSP("common/pilih_pemohon.jsp").addParameter("tab", "true");

    }

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {
        
        System.out.println("start rehydrate ++> ");
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");

        System.out.println("idPermohonan ++> " + idPermohonan);
        pihakKepentinganList = new ArrayList<HakmilikPihakBerkepentingan>();
        listTuanTanahNew = new ArrayList<HakmilikPihakBerkepentingan>();
        try {
            if (idPermohonan != null) {
                
                
                listTuanTanah = new LinkedList<Pihak>();
                permohonan = permohonanDAO.findById(idPermohonan);
                System.out.println("idPermohonan ++> not null" );
                
                List<HakmilikPermohonan> l = permohonan.getSenaraiHakmilik();
                for (HakmilikPermohonan hp : l) {
                    Hakmilik hk = hp.getHakmilik();
                    List<HakmilikPihakBerkepentingan> lhpk = hk.getSenaraiPihakBerkepentingan();
                    for (HakmilikPihakBerkepentingan hpk : lhpk) {
                        Pihak phk = hpk.getPihak();
                        listTuanTanah.add(phk);
                    }
                    senaraiHakmilikPihak = new ArrayList<HakmilikPihakBerkepentingan>();
                    senaraiHakmilikPihak = lhpk;
                }
                hakmilikPermohonanList2 = hakmilikpermohonanservice.findIDMHByIDMohonIDHakmilikXnull(idPermohonan);
                for (HakmilikPermohonan hakmilikPermohonan : hakmilikPermohonanList2) {

                    Hakmilik hak = hakmilikPermohonan.getHakmilik();

                    List<HakmilikPihakBerkepentingan> listHakPihak;
                    if (hak != null) {
                        listHakPihak = hak.getSenaraiPihakBerkepentingan();
                        pihakKepentinganList.addAll(listHakPihak);
                        System.out.println("List Tuan Tanah" + pihakKepentinganList);
                        mohonPihakList = permohonanPihakService.getSenaraiPmohonPihak(idPermohonan);
                        pemohonList = pemohonService.findPemohonByIdPermohonan(idPermohonan);
                        pemilik = hakmilikPihakKepentinganService.findPihakActiveByHakmilik(hak);
                        String kodPihak = "PM";

                        listTuanTanahNew = hakmilikPihakKepentinganService.findHakmilikPihakByKod(hak, kodPihak);
                        System.out.println("List Tuan Tanah" + listTuanTanah);
                        size = listHakPihak.size();
                    }
                }

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

            String[] tname = {"permohonan"};
            Object[] model = {permohonan};

            listPemohon = pemohonDAO.findByEqualCriterias(tname, model, null);

            ArrayList listDelete = new ArrayList();

            for (int i = 0; i < listPemohon.size(); i++) {

                for (int j = 0; j < listTuanTanah.size(); j++) {

                    if (listPemohon.get(i).getPihak().equals(listTuanTanah.get(j))) {

                        listDelete.add(listPemohon.get(i).getPihak());

                    }
                }
            }

            int count = 0;

            for (int i = 0; i < listDelete.size(); i++) {

                listTuanTanah.remove(listPemohon.get(i).getPihak());
                count++;
            }
        } catch (Exception ex) {

        }

    }

    public Resolution simpanPemohon() {

        Pengguna p = (Pengguna) context.getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);

        InfoAudit info = p.getInfoAudit();

        info.setDimasukOleh(p);
        info.setTarikhMasuk(new java.util.Date());

        String idPihak = getContext().getRequest().getParameter("idPihak");

        Pihak phk = pihakDAO.findById(Long.valueOf(idPihak));
        Pemohon pemohon = new Pemohon();
        pemohon.setPermohonan(permohonan);
        pemohon.setPihak(phk);
        pemohon.setCawangan(p.getKodCawangan());
        pemohon.setInfoAudit(info);

        pemohon = conService.simpanPemohon(pemohon);

        String[] tname = {"permohonan"};
        Object[] model = {permohonan};

        listPemohon = pemohonDAO.findByEqualCriterias(tname, model, null);
        return new StreamingResolution("text/plain", String.valueOf(pemohon.getIdPemohon()));

    }

    public Resolution deletePemohon() {

        String idPemohon = getContext().getRequest().getParameter("idPemohon");
        if (idPemohon != null) {
            Pemohon pemohon = pemohonService.findById(idPemohon);
            if (pemohon != null) {
                pemohonService.delete(pemohon);
                listPemohon = pemohonService.findPemohonByIdPermohonan(idPermohonan);
            }
        }

        return new JSP("common/maklumat_pemohon.jsp").addParameter("tab", "true");
    }

    public Resolution refreshpage() {
        rehydrate();
        return new JSP("common/maklumat_pemohon.jsp").addParameter("tab", "true");
    }

    public Resolution simpanPihak() {
        Pengguna pg = (Pengguna) context.getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        InfoAudit ia = new InfoAudit();
        ia.setDiKemaskiniOleh(pg);
        ia.setTarikhKemaskini(new java.util.Date());
        Pihak p = pihakDAO.findById(pihak.getIdPihak());
        p.setSuratAlamat1(pihak.getSuratAlamat1());
        p.setSuratAlamat2(pihak.getSuratAlamat2());
        p.setSuratAlamat3(pihak.getSuratAlamat3());
        p.setSuratAlamat4(pihak.getSuratAlamat4());
        p.setSuratPoskod(pihak.getSuratPoskod());
        p.setSuratNegeri(pihak.getSuratNegeri());
        p.setNoTelefon1(pihak.getNoTelefon1());
        p.setEmail(pihak.getEmail());
        ia.setDimasukOleh(p.getInfoAudit().getDimasukOleh());
        ia.setTarikhMasuk(p.getInfoAudit().getTarikhMasuk());
        p.setInfoAudit(ia);
        conService.simpanPihak(p);
        return refreshpage();
    }

    public List<Pihak> getListTuanTanah() {
        return listTuanTanah;
    }

    public void setListTuanTanah(List<Pihak> listTuanTanah) {
        this.listTuanTanah = listTuanTanah;
    }

    public List<Pemohon> getListPemohon() {
        return listPemohon;
    }

    public void setListPemohon(List<Pemohon> listPemohon) {
        this.listPemohon = listPemohon;
    }

    public String[] getArrayCheckBox() {
        return arrayCheckBox;
    }

    public void setArrayCheckBox(String[] arrayCheckBox) {
        this.arrayCheckBox = arrayCheckBox;
    }

    public Pihak getPihak() {
        return pihak;
    }

    public void setPihak(Pihak pihak) {
        this.pihak = pihak;
    }

    public String getDisplay() {
        return display;
    }

    public void setDisplay(String display) {
        this.display = display;
    }

    public List<HakmilikPihakBerkepentingan> getSenaraiHakmilikPihak() {
        return senaraiHakmilikPihak;
    }

    public void setSenaraiHakmilikPihak(List<HakmilikPihakBerkepentingan> senaraiHakmilikPihak) {
        this.senaraiHakmilikPihak = senaraiHakmilikPihak;
    }

    public PermohonanDAO getPermohonanDAO() {
        return permohonanDAO;
    }

    public void setPermohonanDAO(PermohonanDAO permohonanDAO) {
        this.permohonanDAO = permohonanDAO;
    }

    public PemohonDAO getPemohonDAO() {
        return pemohonDAO;
    }

    public void setPemohonDAO(PemohonDAO pemohonDAO) {
        this.pemohonDAO = pemohonDAO;
    }

    public PihakDAO getPihakDAO() {
        return pihakDAO;
    }

    public void setPihakDAO(PihakDAO pihakDAO) {
        this.pihakDAO = pihakDAO;
    }

    public ConsentPtdService getConService() {
        return conService;
    }

    public void setConService(ConsentPtdService conService) {
        this.conService = conService;
    }

    public PemohonService getPemohonService() {
        return pemohonService;
    }

    public void setPemohonService(PemohonService pemohonService) {
        this.pemohonService = pemohonService;
    }

    public Permohonan getPermohonan() {
        return permohonan;
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

    public HakmilikPermohonanService getHakmilikpermohonanservice() {
        return hakmilikpermohonanservice;
    }

    public void setHakmilikpermohonanservice(HakmilikPermohonanService hakmilikpermohonanservice) {
        this.hakmilikpermohonanservice = hakmilikpermohonanservice;
    }

    public PermohonanPihakService getPermohonanPihakService() {
        return permohonanPihakService;
    }

    public void setPermohonanPihakService(PermohonanPihakService permohonanPihakService) {
        this.permohonanPihakService = permohonanPihakService;
    }

    public HakmilikPihakKepentinganService getHakmilikPihakKepentinganService() {
        return hakmilikPihakKepentinganService;
    }

    public void setHakmilikPihakKepentinganService(HakmilikPihakKepentinganService hakmilikPihakKepentinganService) {
        this.hakmilikPihakKepentinganService = hakmilikPihakKepentinganService;
    }

    public List<HakmilikPermohonan> getHakmilikPermohonanList2() {
        return hakmilikPermohonanList2;
    }

    public void setHakmilikPermohonanList2(List<HakmilikPermohonan> hakmilikPermohonanList2) {
        this.hakmilikPermohonanList2 = hakmilikPermohonanList2;
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

    public List<HakmilikPihakBerkepentingan> getListTuanTanahNew() {
        return listTuanTanahNew;
    }

    public void setListTuanTanahNew(List<HakmilikPihakBerkepentingan> listTuanTanahNew) {
        this.listTuanTanahNew = listTuanTanahNew;
    }

    public List<PermohonanPihak> getMohonPihakList() {
        return mohonPihakList;
    }

    public void setMohonPihakList(List<PermohonanPihak> mohonPihakList) {
        this.mohonPihakList = mohonPihakList;
    }

    public List<Pemohon> getPemohonList() {
        return pemohonList;
    }

    public void setPemohonList(List<Pemohon> pemohonList) {
        this.pemohonList = pemohonList;
    }

    public List<HakmilikPihakBerkepentingan> getPemilik() {
        return pemilik;
    }

    public void setPemilik(List<HakmilikPihakBerkepentingan> pemilik) {
        this.pemilik = pemilik;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
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
    
}
