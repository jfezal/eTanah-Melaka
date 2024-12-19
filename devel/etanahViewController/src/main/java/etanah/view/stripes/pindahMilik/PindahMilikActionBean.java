/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.stripes.pindahMilik;

import able.stripes.AbleActionBean;
import com.google.inject.Inject;
import etanah.dao.FasaPermohonanDAO;
import etanah.dao.HakmilikPihakBerkepentinganDAO;
import etanah.dao.PermohonanDAO;
import etanah.dao.PermohonanUrusanDAO;
import etanah.manager.TabManager;
import etanah.model.FasaPermohonan;
import etanah.model.Hakmilik;
import etanah.model.HakmilikPermohonan;
import etanah.model.HakmilikPihakBerkepentingan;
import etanah.model.InfoAudit;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.model.PermohonanPihak;
import etanah.model.PermohonanUrusan;
import etanah.model.Pihak;
import etanah.view.etanahActionBeanContext;
import java.util.LinkedList;
import java.util.List;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;

/**
 *
 * @author md.nurfikri
 */
@UrlBinding("/pindah_milik")
public class PindahMilikActionBean extends AbleActionBean {

    TabManager tabManager;
    PermohonanDAO permohonanDao;    
    PermohonanUrusanDAO permohonanUrusanDAO;
    HakmilikPihakBerkepentinganDAO hakmilikPihakBerkepentinganDAO;
    FasaPermohonanDAO fasaPermohonanDAO;
    private PermohonanUrusan urusan;
    private Permohonan mohon;
    private Hakmilik hakmilik;
    private List<PermohonanPihak> pihakKepentinganList;
    private List<Pihak> listTuanTanah;
    private List<FasaPermohonan> listFasa;
    private FasaPermohonan fasaPermohonan;

    @Inject
    public PindahMilikActionBean(TabManager tabManager, PermohonanDAO permohonanDao,            
            HakmilikPihakBerkepentinganDAO hakmilikPihakBerkepentinganDAO,
            PermohonanUrusanDAO permohonanUrusanDAO, FasaPermohonanDAO fasaPermohonanDAO) {
        this.tabManager = tabManager;
        this.permohonanDao = permohonanDao;        
        this.hakmilikPihakBerkepentinganDAO = hakmilikPihakBerkepentinganDAO;
        this.permohonanUrusanDAO = permohonanUrusanDAO;
        this.fasaPermohonanDAO = fasaPermohonanDAO;
    }

    @DefaultHandler
    public Resolution showForm() {
        return new ForwardResolution("/WEB-INF/jsp/common/view_maklumat_permohonan.jsp").addParameter("tab", "true");
    }

    public Resolution maklumatHakmilik(){
        return new ForwardResolution("/WEB-INF/jsp/common/maklumat_hakmilik.jsp").addParameter("tab", "true");
    }

    public Resolution maklumatTuanTanah(){
        return new ForwardResolution("/WEB-INF/jsp/consent/maklumat_tuan_tanah.jsp").addParameter("tab", "true");
    }

    public Resolution maklumatPihakBekepentingan(){
        return new ForwardResolution("/WEB-INF/jsp/common/senarai_pihak_berkepentingan.jsp").addParameter("tab", "true");
    }

    public Resolution maklumatUrusniaga(){
        return new ForwardResolution("/WEB-INF/jsp/pendaftaran/kemasukan_maklumat_urusniaga.jsp").addParameter("tab", "true");
    }   

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {
        String idPermohonan = (String)getContext().getRequest().getSession().getAttribute("idPermohonan");
        if (idPermohonan != null) {
            mohon = permohonanDao.findById(idPermohonan);

            List<HakmilikPermohonan> ls = mohon.getSenaraiHakmilik();
            if (ls.size() > 0) {
                HakmilikPermohonan hp = ls.get(0);
                hakmilik = hp.getHakmilik();
            }
//            List<PermohonanPenerima> list = permohonanPenerimaDAO.findAll();
//            pihakKepentinganList = new LinkedList<PermohonanPenerima>();
//            for (PermohonanPenerima permohonanPenerima : list) {
//                if (permohonanPenerima.getPermohonan().equals(mohon)) {
//                    pihakKepentinganList.add(permohonanPenerima);
//                }
//            }
            List<HakmilikPihakBerkepentingan> list2 = hakmilikPihakBerkepentinganDAO.findAll();
            listTuanTanah = new LinkedList<Pihak>();
            for (HakmilikPihakBerkepentingan hakmilikPihakBerkepentingan : list2) {
                List<HakmilikPermohonan> hkList = mohon.getSenaraiHakmilik();
                for (HakmilikPermohonan hakmilikPermohonan : hkList) {
                    if (hakmilikPihakBerkepentingan.getHakmilik().getIdHakmilik().
                            equals(hakmilikPermohonan.getHakmilik().getIdHakmilik())) {
                        listTuanTanah.add(hakmilikPihakBerkepentingan.getPihak());
                    }
                }
            }

            List<PermohonanUrusan> urs = permohonanUrusanDAO.findAll();
            for (PermohonanUrusan permohonanUrusan : urs) {
                if (permohonanUrusan.getPermohonan().equals(mohon)) {
                    urusan = permohonanUrusan;
                    break;
                }
            }

            List<FasaPermohonan> list3 = fasaPermohonanDAO.findAll();
            listFasa = new LinkedList<FasaPermohonan>();
            for (FasaPermohonan _fasaPermohonan : list3) {
                if (_fasaPermohonan.getPermohonan().equals(mohon)) {
                    fasaPermohonan = _fasaPermohonan;                    
                    listFasa.add(_fasaPermohonan);
                }
            }
        }
    }

    public Resolution saveUrusan() {        
        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        InfoAudit info = new InfoAudit();
        info.setDimasukOleh(peng);
        info.setTarikhMasuk(new java.util.Date());
//        mohon = permohonanDao.findById(idPermohonan);
        urusan.setInfoAudit(info);
        urusan.setCawangan(peng.getKodCawangan());
        urusan.setPermohonan(mohon);       
        if (urusan.getIdUrusan() == 0) {
            permohonanUrusanDAO.save(urusan);
        } else {
            permohonanUrusanDAO.update(urusan);
        }
    
        addSimpleMessage("Kemasukan Data Berjaya");
        return new ForwardResolution("/WEB-INF/jsp/pendaftaran/kemasukan_maklumat_urusniaga.jsp").addParameter("tab", "true");
    }

    public List<PermohonanPihak> getPihakKepentinganList() {
        return pihakKepentinganList;
    }

    public void setPihakKepentinganList(List<PermohonanPihak> pihakKepentinganList) {
        this.pihakKepentinganList = pihakKepentinganList;
    }

    public Permohonan getMohon() {
        return mohon;
    }

    public void setMohon(Permohonan mohon) {
        this.mohon = mohon;
    }

    public List<Pihak> getListTuanTanah() {
        return listTuanTanah;
    }

    public void setListTuanTanah(List<Pihak> listTuanTanah) {
        this.listTuanTanah = listTuanTanah;
    }

    public Hakmilik getHakmilik() {
        return hakmilik;
    }

    public void setHakmilik(Hakmilik hakmilik) {
        this.hakmilik = hakmilik;
    }

    public List<FasaPermohonan> getListFasa() {
        return listFasa;
    }

    public void setListFasa(List<FasaPermohonan> listFasa) {
        this.listFasa = listFasa;
    }

    public PermohonanUrusan getUrusan() {
        return urusan;
    }

    public void setUrusan(PermohonanUrusan urusan) {
        this.urusan = urusan;
    }

    public FasaPermohonan getFasaPermohonan() {
        return fasaPermohonan;
    }

    public void setFasaPermohonan(FasaPermohonan fasaPermohonan) {
        this.fasaPermohonan = fasaPermohonan;
    }
}
