/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.stripes;

import able.stripes.AbleActionBean;
import com.google.inject.Inject;
import etanah.dao.FolderDokumenDAO;
import etanah.dao.HakmilikDAO;
import etanah.dao.HakmilikPermohonanDAO;
import etanah.dao.KodBandarPekanMukimDAO;
import etanah.dao.KodCawanganDAO;
import etanah.dao.KodDaerahDAO;
import etanah.dao.KodKeputusanDAO;
import etanah.dao.KodLotDAO;
import etanah.dao.KodNegeriDAO;
import etanah.dao.KodSeksyenDAO;
import etanah.dao.KodUrusanDAO;
import etanah.dao.PenggunaDAO;
import etanah.dao.PermohonanDAO;
import etanah.manager.TabManager;
import etanah.model.FolderDokumen;
import etanah.model.Hakmilik;
import etanah.model.KodCawangan;
import etanah.model.KodUrusan;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.model.HakmilikPermohonan;
import etanah.model.InfoAudit;
import etanah.model.KodDaerah;
import etanah.model.KodKeputusan;
import etanah.model.KodLot;
import etanah.view.etanahActionBeanContext;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.DontBind;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.StreamingResolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;

/**
 *
 * @author khairil
 */
@UrlBinding("/pmohonanPendaftaran")
public class PermohonanPendaftaranActionBean extends AbleActionBean {

    TabManager tabManager;
    KodNegeriDAO kodNegeriDao;
    KodDaerahDAO kodDaerahDao;
    KodCawanganDAO kodCawDao;
    KodUrusanDAO kodUrusanDao;
    KodBandarPekanMukimDAO kodBandarPekanMukimDao;
    @Inject
    KodKeputusanDAO kodKeputusanDAO;
    KodLotDAO kodLotDao;
    KodSeksyenDAO kodSeksyenDao;
    private Permohonan mohon;
    PermohonanDAO mohonDao;
    HakmilikPermohonanDAO mohonHakmilikDAO;
    HakmilikDAO hakmilikDAO;
    FolderDokumenDAO folderDao;
    PenggunaDAO penggunaDao;
    private Hakmilik hakmilik;
    private String idHakmilik;
    private List<Permohonan> list;
    private List<FolderDokumen> listDoc;
    private List<HakmilikPermohonan> hakmilikList;
    private boolean save = false;
    Pengguna pengguna;
    private String[] chkbox;

    @Inject
    public PermohonanPendaftaranActionBean(TabManager tabManager, PermohonanDAO mohonDao,
            HakmilikPermohonanDAO mohonHakmilikDAO, HakmilikDAO hakmilikDAO, KodNegeriDAO kodNegeriDao, KodDaerahDAO kodDaerahDao,
            FolderDokumenDAO folderDao, PenggunaDAO penggunaDao, KodCawanganDAO kodCawDao, KodUrusanDAO kodUrusanDao,
            KodBandarPekanMukimDAO kodBandarPekanMukimDao, KodSeksyenDAO kodSeksyenDao, KodLotDAO kodLotDao) {
        this.tabManager = tabManager;
        this.mohonDao = mohonDao;
        this.mohonHakmilikDAO = mohonHakmilikDAO;
        this.hakmilikDAO = hakmilikDAO;
        this.kodNegeriDao = kodNegeriDao;
        this.kodDaerahDao = kodDaerahDao;
        this.folderDao = folderDao;
        this.penggunaDao = penggunaDao;
        this.kodCawDao = kodCawDao;
        this.kodUrusanDao = kodUrusanDao;
        this.kodBandarPekanMukimDao = kodBandarPekanMukimDao;
        this.kodSeksyenDao = kodSeksyenDao;
        this.kodLotDao = kodLotDao;
    }

    @DefaultHandler
    public Resolution showForm() {
        //FIXME : hardcode
        pengguna = penggunaDao.findById("admin");
        getContext().getRequest().getSession().setAttribute(etanahActionBeanContext.KEY_USER, pengguna);

        rehydrate();
        return new ForwardResolution("/WEB-INF/jsp/tab/main_page.jsp");

    }

    public Resolution kemasukanDaftar() {
        getContext().getRequest().setAttribute("pendaftar", Boolean.TRUE); //HACK
        return new ForwardResolution("/WEB-INF/jsp/tab/main_page.jsp");
    }

    @Before(stages = {LifecycleStage.BindingAndValidation})
    private void rehydrate() {
        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        if (idPermohonan != null) {
            System.out.println("rehydrate.idPermohonan :" + idPermohonan);
            mohon = mohonDao.findById(idPermohonan);
        if (mohon != null) {
            hakmilikList = mohon.getSenaraiHakmilik();
                if (hakmilikList.size() > 0) {
                    hakmilik = hakmilikList.get(0).getHakmilik();
                }           
        }
    }
    }

    public Resolution doCheckHakmilik() {
        System.out.println("hakmilik :" + idHakmilik);
        String results = "";
        hakmilik = hakmilikDAO.findById(idHakmilik);
        if (hakmilik != null) {
            results = "0";
        } else {
            results = "1";
        }

        return new StreamingResolution("text/plain", results);
    }

    public Resolution hakmilikDetail() {
        hakmilik = hakmilikDAO.findById(idHakmilik);
        return new ForwardResolution("/WEB-INF/jsp/common/maklumat_hakmilik.jsp").addParameter("popup", "true");
    }

    public Resolution vDoc() {
        rehydrate();
        return new ForwardResolution("/WEB-INF/jsp/daftar/common/vdoc.jsp").addParameter("popup", "true");
    }

    //FIXME : temp solution    
    private String doUpdateHakmilik(Hakmilik tmp) {
        String update_str = "";
        KodDaerah kd = kodDaerahDao.findById(hakmilik.getDaerah().getKod());
        if (!isSameValue(tmp.getDaerah().getKod(), hakmilik.getDaerah().getKod())) {
            update_str += "Daerah " + tmp.getDaerah().getNama() + " ditukar kepada  " + kd.getNama() + "\n";
        }

//        KodBandarPekanMukim kbpm = kodBandarPekanMukimDao.findById(hakmilik.getBandarPekanMukim().getKod());
//        if (!isSameValue(tmp.getBandarPekanMukim().getKod(), hakmilik.getBandarPekanMukim().getKod())) {
//            update_str += "Bandar/Pekan/Mukim " + tmp.getBandarPekanMukim().getNama() + " ditukar kepada  " + kbpm.getNama() + "\n";
//        }

//        KodSeksyen ks = kodSeksyenDao.findById(hakmilik.getSeksyen().getKod());
//        if (!isSameValue(tmp.getSeksyen().getKod(), hakmilik.getSeksyen().getKod())) {
//            update_str += "Seksyen " + tmp.getSeksyen().getNama() + " ditukar kepada  " + ks.getNama() + "\n";
//        }

        KodLot kl = kodLotDao.findById(hakmilik.getLot().getKod());
        if (!isSameValue(tmp.getLot().getKod(), hakmilik.getLot().getKod())) {
            update_str += "Lot " + tmp.getLot().getNama() + " ditukar kepada  " + kl.getNama() + "\n";
        }

        if (!isSameValue(tmp.getLuas(), hakmilik.getLuas())) {
            update_str += "Luas " + tmp.getLuas() + " ditukar kepada " + hakmilik.getLuas() + "\n";
        }

        if (!isSameValue(tmp.getNoLitho(), hakmilik.getNoLitho())) {
            update_str += "No Litho " + tmp.getNoLitho() + " ditukar kepada  " + hakmilik.getNoLitho() + "\n";
        }
        if (!isSameValue(tmp.getNoLot(), hakmilik.getNoLot())) {
            update_str += "No Lot " + tmp.getNoLot() + " ditukar kepada  " + hakmilik.getNoLot() + "\n";
        }

        if (!isSameValue(tmp.getNoPelan(), hakmilik.getNoPelan())) {
            update_str += "No Pelan " + tmp.getNoPelan() + " ditukar kepada  " + hakmilik.getNoPelan() + "\n";
        }
        tmp.setDaerah(kd);
//        tmp.setBandarPekanMukim(kbpm);
//        tmp.setSeksyen(ks);
        tmp.setLot(kl);
        tmp.setNoLot(hakmilik.getNoLot());
        tmp.setNoLitho(hakmilik.getNoLitho());
        tmp.setLuas(hakmilik.getLuas());
        tmp.setNoPelan(hakmilik.getNoPelan());

        return update_str;
    }

    private boolean isSameValue(Object obj1, Object obj2) {
        if (obj1 == null || obj2 == null) {
            return true;
        }
        if (obj1.equals(obj2)) {
            return true;
        }

        return false;
    }

    public Resolution save() {
        String idPermohonan = mohon.getIdPermohonan();
//        mohon.setKeputusanOleh("admin");//FIXME : hardcode
//        hakmilik = hakmilikDAO.findById(hakmilik.getIdHakmilik());
        Hakmilik tmp = hakmilikDAO.findById(hakmilik.getIdHakmilik());
        String _tmp = doUpdateHakmilik(tmp);
        hakmilikDAO.update(tmp);
        //update permohonan
        String tmp_ = mohon.getCatatan();
        mohon = mohonDao.findById(idPermohonan);
        mohon.setCatatan(tmp_);
        mohon.setSebab(_tmp);
        setSave(true);
        mohonDao.update(mohon);
        rehydrate();
        addSimpleMessage("Kemasukan Data Berjaya");
        return new ForwardResolution("/WEB-INF/jsp/tab/main_page.jsp");
    }

    public Resolution showForm2() {
        hakmilikList = new LinkedList<HakmilikPermohonan>();
        return new ForwardResolution("/WEB-INF/jsp/daftar/kemasukan_belakang_kaunter.jsp");
    }

    //FIXME : temp
    @DontBind
    public Resolution searchPT() {
        if (mohon != null) {
            if (mohon.getIdPermohonan() != null) {
                Permohonan mhn = mohonDao.findById(mohon.getIdPermohonan());
                if (mhn != null) {
                    list = new LinkedList<Permohonan>();
                    list.add(mhn);
                } else {
                    list = Collections.EMPTY_LIST;
                }
            } else {
                list = mohonDao.findAll();
            }
        } else {
            list = mohonDao.findAll();
        }
        return new ForwardResolution("/WEB-INF/jsp/daftar/carian_pt_sementara.jsp");
    }
    //FIXME : temp

    public Resolution searchPendaftar() {
        if (mohon != null) {
            Permohonan mhn = mohonDao.findById(mohon.getIdPermohonan());
            if (mhn != null) {
                list = new LinkedList<Permohonan>();
                list.add(mhn);
            } else {
                list = Collections.EMPTY_LIST;
            }
        } else {
            list = mohonDao.findAll();
        }
        return new ForwardResolution("/WEB-INF/jsp/daftar/carian_daftar_sementara.jsp");
    }

    public Resolution daftar() {
        System.out.println("daftar :");
        //FIXME : user get from session. currently not available
        //Pengguna peng = (Pengguna)getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER); 
        Pengguna peng = penggunaDao.findById("admin");//FIXME : hardcode
        KodCawangan kodCawangan = kodCawDao.findById("01");   //FIXME: hardcode
        KodUrusan kodUrusan = kodUrusanDao.findById("BETUL");   //FIXME: hardcode
        System.out.println("peng :" + peng.getIdPengguna());
        InfoAudit info = new InfoAudit();
        info.setDimasukOleh(peng);
        info.setTarikhMasuk(new java.util.Date());

        mohon.setIdPermohonan(String.valueOf(System.nanoTime())); //FIXME : auto generate id mohon        
        mohon.setCawangan(kodCawangan);
        mohon.setKodUrusan(kodUrusan);
        mohon.setInfoAudit(info);
        mohonDao.save(mohon);

        hakmilik = hakmilikDAO.findById(hakmilik.getIdHakmilik());
        HakmilikPermohonan mohonHakmilik = new HakmilikPermohonan();
        String mohonId = String.valueOf(System.nanoTime());
        mohonHakmilik.setId(Long.parseLong(mohonId.substring(0, 9)));
        mohonHakmilik.setPermohonan(mohon);
        mohonHakmilik.setHakmilik(hakmilik);
        mohonHakmilik.setInfoAudit(info);
        mohonHakmilikDAO.save(mohonHakmilik);

        return new ForwardResolution("/WEB-INF/jsp/daftar/maklumat_kutipan.jsp");
    }

    public Resolution update() {
        return new ForwardResolution("/WEB-INF/jsp/tab/main_page.jsp");
    }

    public Resolution sah() {
        String idPermohonan = mohon.getIdPermohonan();//FIXME : hardcode
        System.out.println("idmohon :" + idPermohonan);
//        char result = mohon.getKeputusan();
        KodKeputusan kk = kodKeputusanDAO.findById(mohon.getKeputusan().getKod());        
        mohon = mohonDao.findById(idPermohonan);
        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        InfoAudit info = new InfoAudit();
        info.setDimasukOleh(peng);
        info.setTarikhKemaskini(new java.util.Date());
        mohon.setKeputusan(kk);
        mohon.setInfoAudit(info);
        mohonDao.update(mohon);
        rehydrate();
//        return new ForwardResolution("/WEB-INF/jsp/pendaftaran/regKeputusanPendaftaranHakmilik.jsp");
        return new ForwardResolution("/WEB-INF/jsp/tab/main_page.jsp");
    }

    public Permohonan getMohon() {
        return mohon;
    }

    public void setMohon(Permohonan mohon) {
        this.mohon = mohon;
    }

    public Hakmilik getHakmilik() {
        return hakmilik;
    }

    public void setHakmilik(Hakmilik hakmilik) {
        this.hakmilik = hakmilik;
    }

    public String getIdHakmilik() {
        return idHakmilik;
    }

    public void setIdHakmilik(String idHakmilik) {
        this.idHakmilik = idHakmilik;
    }

    public List<Permohonan> getList() {
        return list;
    }

    public void setList(List<Permohonan> list) {
        this.list = list;
    }

    public Map<String, String> getNegeri() {
        return null;
    }

    public Map<String, String> getDaerah() {
        return null;
    }

    public List<FolderDokumen> getListDoc() {
        return listDoc;
    }

    public void setListDoc(List<FolderDokumen> listDoc) {
        this.listDoc = listDoc;
    }

    public boolean isSave() {
        return save;
    }

    public void setSave(boolean save) {
        this.save = save;
    }

    public List<HakmilikPermohonan> getHakmilikList() {
        return hakmilikList;
    }

    public void setHakmilikList(List<HakmilikPermohonan> hakmilikList) {
        this.hakmilikList = hakmilikList;
    }

    public String[] getChkbox() {
        return chkbox;
    }

    public void setChkbox(String[] chkbox) {
        this.chkbox = chkbox;
    }
}
