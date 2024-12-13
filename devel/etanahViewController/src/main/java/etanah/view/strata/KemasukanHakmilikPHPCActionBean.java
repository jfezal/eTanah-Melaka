/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.strata;

import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.dao.HakmilikDAO;
import etanah.dao.HakmilikPermohonanDAO;
import etanah.dao.PermohonanDAO;
import etanah.model.BangunanPetak;
import etanah.model.BangunanPetakAksesori;
import etanah.model.BangunanTingkat;
import etanah.model.Hakmilik;
import etanah.model.HakmilikPermohonan;
import etanah.model.InfoAudit;
import etanah.model.KodCawangan;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.model.PermohonanBangunan;
import etanah.service.StrataPtService;
import etanah.view.BasicTabActionBean;
import etanah.view.etanahActionBeanContext;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

/**
 *
 * @author faidzal
 */
@UrlBinding("/strata/kemasukanHakmilikPHPC")
public class KemasukanHakmilikPHPCActionBean extends BasicTabActionBean {

    @Inject
    StrataPtService strService;
    @Inject
    PermohonanDAO mohonDAO;
    @Inject
    HakmilikPermohonanDAO mohonHakmilikDAO;
    @Inject
    HakmilikDAO hakmilikDAO = new HakmilikDAO();
    Permohonan mohon = new Permohonan();
    HakmilikPermohonan mohonHakmilik = new HakmilikPermohonan();
    ArrayList<SenaraiPetakBaruList> listPetakBaru = new ArrayList();
    List<BangunanPetakAksesori>listPetakAks = new ArrayList();
    private int bilPetakBaru = 0;
    private static final Logger LOG = Logger.getLogger(KemasukanHakmilikPHPCActionBean.class);
    InfoAudit ia = new InfoAudit();
    KodCawangan kodCawangan = new KodCawangan();
    String noTingkat = "";

    @DefaultHandler
    public Resolution showForm() {
        String msg = getContext().getRequest().getParameter("message");
        String error = getContext().getRequest().getParameter("error");
        if (StringUtils.isNotBlank(msg)) {
            addSimpleMessage(msg);
        }

        if (StringUtils.isNotBlank(error)) {
            addSimpleError(error);
        }
        return new JSP("strata/pecahPetak/kemasukan_hakmilik.jsp").addParameter("tab", "true");
    }

    public Resolution onChangePetakbaru() {
        bilPetakBaru = Integer.parseInt(getContext().getRequest().getParameter("bilPetakBaru"));
        BigDecimal bd = new BigDecimal(bilPetakBaru);
//BigDecimal syerBahagi = mohonHakmilik.getHakmilik().getUnitSyer();
        BigDecimal luasBahagi = mohonHakmilik.getHakmilik().getLuas().divide(bd);
        int p = 1;
        for (int i = 0; i < bilPetakBaru; i++) {
            SenaraiPetakBaruList list = new SenaraiPetakBaruList();

            LOG.info("LUAS BAHAGI : " + luasBahagi);
            list.setIdPetak(String.valueOf(i+1));
            list.setLuasPetak(luasBahagi);
            listPetakBaru.add(list);
        }
        listPetakAks = strService.getListPetakAks(mohonHakmilik.getHakmilik().getIdHakmilikInduk());
        simpan();
        return new JSP("strata/pecahPetak/kemasukan_hakmilik.jsp").addParameter("tab", "true");
    }

    public void simpan() {
        int jumPetak = 0;
        PermohonanBangunan mohonB = new PermohonanBangunan();
        int pet = getLatestPetak(mohonHakmilik.getHakmilik().getIdHakmilikInduk(), mohonHakmilik.getHakmilik().getNoBangunan(), mohonHakmilik.getHakmilik().getNoTingkat());
        Hakmilik hakmilik = hakmilikDAO.findById(mohonHakmilik.getHakmilik().getIdHakmilik());
        noTingkat = hakmilik.getNoTingkat();
        Permohonan permohonanLama = findIdPBBS(mohonHakmilik.getHakmilik().getIdHakmilikInduk());
        LOG.info("Permohonan Lama: "+permohonanLama.getIdPermohonan());
        List<PermohonanBangunan> listMohonBgn = strService.findByIDPermohonan(permohonanLama.getIdPermohonan());
        for (PermohonanBangunan mohonBGN : listMohonBgn) {
            for (BangunanTingkat ting : mohonBGN.getSenaraiTingkat()) {
                jumPetak = jumPetak + ting.getBilanganPetak();
            }
        }
        LOG.info("PETAK LATEST : " + jumPetak);
        int i = 1;
        PermohonanBangunan mohonBngn = new PermohonanBangunan();
        mohonBngn.setBilanganPetak(bilPetakBaru);
        mohonBngn.setNama(hakmilik.getNoBangunan());
        mohonBngn.setNamaLain(hakmilik.getNoBangunan());
        mohonBngn.setPermohonan(mohon);
        mohonBngn.setInfoAudit(ia);
        mohonBngn.setBilanganTingkat(bilPetakBaru);
        mohonBngn.setCawangan(kodCawangan);
        mohonBngn = strService.saveMohonBangunan(mohonBngn);
        BangunanTingkat bangunanTingkat = new BangunanTingkat();
        bangunanTingkat.setBangunan(mohonBngn);
        bangunanTingkat.setInfoAudit(ia);
        bangunanTingkat.setNama(noTingkat);
        bangunanTingkat.setTingkat(Integer.parseInt(noTingkat));
        bangunanTingkat = strService.saveBangunanTingkat(bangunanTingkat);
        for (SenaraiPetakBaruList lst : listPetakBaru) {
            i = jumPetak + i;
            savePetak(bangunanTingkat, i, lst.getLuasPetak(), lst.getUnitSyer());
        }
    }

    public void savePetak(BangunanTingkat bangunanTingkat, int i, BigDecimal luasPetak, int unitSyer) {
        BangunanPetak bangunanPetak = new BangunanPetak();
        bangunanPetak.setTingkat(bangunanTingkat);
        bangunanPetak.setNama(String.valueOf(i));
        bangunanPetak.setInfoAudit(ia);
        bangunanPetak.setKegunaan(null);
        bangunanPetak.setSyer(unitSyer);
        bangunanPetak.setLuas(luasPetak);
        bangunanPetak = strService.saveBangunanPetak(bangunanPetak);
    }

    public Permohonan findIdPBBS(String idHakmilik) {
        List<HakmilikPermohonan> lmohonHakmilikPBBS = new ArrayList();
        Permohonan permohonanLama = new Permohonan();
        lmohonHakmilikPBBS = strService.findIdPBBSByIdHakmilik(idHakmilik);
        if (lmohonHakmilikPBBS != null) {
            for (HakmilikPermohonan mohonHakmilikPBBS : lmohonHakmilikPBBS) {
                if (mohonHakmilikPBBS.getPermohonan().getKeputusan() != null) {
                    if (mohonHakmilikPBBS.getPermohonan().getKeputusan().getKod().equals("L") && mohonHakmilikPBBS.getPermohonan().getKodUrusan().getKod().equals("PBBS")) {
                        permohonanLama = mohonHakmilikPBBS.getPermohonan();
                        break;
                    }
                }
            }
        }
        return permohonanLama;
    }

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        kodCawangan = peng.getKodCawangan();
        this.ia = strService.getInfo(peng);
        if (idPermohonan != null) {
            mohon = mohonDAO.findById(idPermohonan);
            if (mohon.getSenaraiHakmilik() != null) {
                mohonHakmilik = mohon.getSenaraiHakmilik().get(0);
            }
        }
    }

    public Permohonan getMohon() {
        return mohon;
    }

    public void setMohon(Permohonan mohon) {
        this.mohon = mohon;
    }

    public PermohonanDAO getMohonDAO() {
        return mohonDAO;
    }

    public void setMohonDAO(PermohonanDAO mohonDAO) {
        this.mohonDAO = mohonDAO;
    }

    public HakmilikPermohonan getMohonHakmilik() {
        return mohonHakmilik;
    }

    public void setMohonHakmilik(HakmilikPermohonan mohonHakmilik) {
        this.mohonHakmilik = mohonHakmilik;
    }

    public HakmilikPermohonanDAO getMohonHakmilikDAO() {
        return mohonHakmilikDAO;
    }

    public void setMohonHakmilikDAO(HakmilikPermohonanDAO mohonHakmilikDAO) {
        this.mohonHakmilikDAO = mohonHakmilikDAO;
    }

    public int getBilPetakBaru() {
        return bilPetakBaru;
    }

    public void setBilPetakBaru(int bilPetakBaru) {
        this.bilPetakBaru = bilPetakBaru;
    }

    public ArrayList<SenaraiPetakBaruList> getListPetakBaru() {
        return listPetakBaru;
    }

    public void setListPetakBaru(ArrayList<SenaraiPetakBaruList> listPetakBaru) {
        this.listPetakBaru = listPetakBaru;
    }

    public InfoAudit getIa() {

        return ia;
    }

    public void setIa(InfoAudit ia) {
        this.ia = ia;
    }

    public StrataPtService getStrService() {
        return strService;
    }

    public void setStrService(StrataPtService strService) {
        this.strService = strService;
    }

    private int getLatestPetak(String idHakmilikInduk, String noBangunan, String noTingkat) {
        List<Hakmilik> hak = strService.getNoPetakLatest(idHakmilikInduk, noBangunan, noTingkat);
        return Integer.parseInt(hak.get(0).getNoPetak());
    }

    public List<BangunanPetakAksesori> getListPetakAks() {
        return listPetakAks;
    }

    public void setListPetakAks(List<BangunanPetakAksesori> listPetakAks) {
        this.listPetakAks = listPetakAks;
    }

    
    public String getNoTingkat() {
        return noTingkat;
    }

    public void setNoTingkat(String noTingkat) {
        this.noTingkat = noTingkat;
    }

    
}
