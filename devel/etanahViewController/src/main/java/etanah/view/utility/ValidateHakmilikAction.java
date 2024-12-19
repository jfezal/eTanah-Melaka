package etanah.view.utility;

import net.sourceforge.stripes.action.HttpCache;
import net.sourceforge.stripes.action.UrlBinding;
import able.stripes.AbleActionBean;
import com.google.inject.Inject;
import etanah.dao.KodUOMDAO;
import etanah.dao.PermohonanSemakPelanDAO;
import etanah.dao.PermohonanSemakPelanHakmilikDAO;
import etanah.dao.PihakDAO;
import etanah.model.Hakmilik;
import etanah.model.InfoAudit;
import etanah.model.KodUOM;
import etanah.model.Pengguna;
import etanah.model.PermohonanSemakPelan;
import etanah.model.PermohonanSemakPelanHakmilik;
import etanah.model.Pihak;
import etanah.service.common.HakmilikService;
import etanah.service.common.PemohonanSemakPelanHakmilikService;
import etanah.service.common.PemohonanSemakPelanService;
import etanah.view.etanahActionBeanContext;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.HandlesEvent;
import net.sourceforge.stripes.action.RedirectResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.StreamingResolution;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;

@HttpCache(allow = false)
@UrlBinding("/utiliti/validateHakmilik")
public class ValidateHakmilikAction extends AbleActionBean {

    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;

    @Inject
    private HakmilikService hakmilikService;

    @Inject
    PemohonanSemakPelanService permohonanSemakPelanService;

    @Inject
    PermohonanSemakPelanDAO permohonanSemakPelanDAO;

    @Inject
    PermohonanSemakPelanHakmilikDAO permohonanSemakPelanHakmilikDAO;

    @Inject
    PemohonanSemakPelanHakmilikService pemohonanSemakPelanHakmilikService;

    @Inject
    KodUOMDAO kodUOMDAO;

    @Inject
    PihakDAO pihakDAO;

    private static Logger LOG = Logger.getLogger(ValidateHakmilikAction.class);
    String idHakmilik;
    private String listHakmilik;
    private List<String> list = new ArrayList<String>();
    private BigDecimal luas = new BigDecimal(0.00);
    private Double luasTemp = new Double(0.00);
    private Double luasTotalTemp = new Double(0.00);
    private String idHakmilikPopup;

    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    SimpleDateFormat formatDate = new SimpleDateFormat("ddMMyyyy");
    Date dateNow = new Date();
    private String[] idHakmilikL;
    private String jumlahLuas;
    private String namaPenyerah;
    private String noTel;
    private long idMsp;

    public Resolution validateIdHakmilik() {
        String result = "";
        idHakmilik = getContext().getRequest().getParameter("idHakmilik");
        LOG.info("ID Hakmilik:::::::::::" + idHakmilik);
        Hakmilik hakmilikList = hakmilikService.findById(idHakmilik);
        if (hakmilikList != null) {
            LOG.info(":::::::::::1st Loop:::::::::::");
            LOG.info("Kod Keputusan:::::::::::" + hakmilikList.getKodStatusHakmilik().getKod());
            if (hakmilikList.getKodStatusHakmilik().getKod().equalsIgnoreCase("D")) {

                result = "true";
            }
        }
        LOG.info("result:::::::::::" + result);
        //return result;
        return new StreamingResolution("text/plain", result);
    }

    public Resolution saveLuasHakmilik() {
        Session s = sessionProvider.get();
        Transaction tx = s.beginTransaction();
        Pengguna pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        String bil = getContext().getRequest().getParameter("bil");
        Integer count = Integer.valueOf(bil);
        String nama = getContext().getRequest().getParameter("nama");
        String noTel = getContext().getRequest().getParameter("noTel");
        String luasTotalHakmilik = getContext().getRequest().getParameter("jumLuas");
        String unitLuasTotal = getContext().getRequest().getParameter("kodUOMTotal");
        Pihak pihak = new Pihak();
        pihak.setNama(nama);
        pihak.setNoTelefon1(noTel);
        LOG.info("luasTotalHakmilik:::::::::::" + luasTotalHakmilik);
        LOG.info("unitLuasTotal:::::::::::" + unitLuasTotal);

        Integer getMaxId = permohonanSemakPelanService.findPermohonanSemakPelanId();
        String currentDate = formatDate.format(dateNow);
        LOG.info("getMaxId before:::::::::::" + getMaxId);
        String maxMspId = Long.toString(getMaxId);
        String finalMspId = maxMspId + currentDate;

        PermohonanSemakPelan mohonSemakPelan = new PermohonanSemakPelan();

        luasTotalTemp = Double.valueOf(luasTotalHakmilik);
        LOG.info("getMaxId after:::::::::::" + finalMspId);
        mohonSemakPelan.setIdMsp(Long.parseLong(finalMspId));
        mohonSemakPelan.setJumLuas(BigDecimal.valueOf(luasTotalTemp));
        if (unitLuasTotal != null && !unitLuasTotal.equals("")) {
            KodUOM kodUOM1 = kodUOMDAO.findById(unitLuasTotal);
            mohonSemakPelan.setKodUom(kodUOM1);
        } else {
            mohonSemakPelan.setKodUom(null);
        }

        InfoAudit info = new InfoAudit();
        info.setTarikhMasuk(new java.util.Date());
        info.setDimasukOleh(pengguna);
        mohonSemakPelan.setInfoAudit(info);
        pihak.setInfoAudit(info);
        pihakDAO.save(pihak);
        mohonSemakPelan.setPihak(pihak);
        permohonanSemakPelanDAO.saveOrUpdate(mohonSemakPelan);
        LOG.info("idmsp:::::::::::" + mohonSemakPelan.getIdMsp());
        idMsp = mohonSemakPelan.getIdMsp();
        idHakmilikL = new String[count];
        namaPenyerah = nama;
        this.noTel = noTel;
        jumlahLuas = luasTotalTemp +" "+ mohonSemakPelan.getKodUom().getNama();

        for (int i = 0; i < count; i++) {
            String luasHakmilik = getContext().getRequest().getParameter("luas" + i);
            String idHakmilik = getContext().getRequest().getParameter("idHakmilik" + i);
            String unitLuas = getContext().getRequest().getParameter("kodUOM" + i);
            String noPelanPA = getContext().getRequest().getParameter("pelanPA" + i);
            String tarikhSah = getContext().getRequest().getParameter("tarikhSah" + i);

            luasTemp = Double.valueOf(luasHakmilik);
            LOG.info("luasHakmilik:::::::::::" + luasHakmilik);
            LOG.info("idHakmilik:::::::::::" + idHakmilik);
            LOG.info("unitLuas:::::::::::" + unitLuas);
            LOG.info("noPelanPA:::::::::::" + noPelanPA);
            LOG.info("tarikhSah:::::::::::" + tarikhSah);
            idHakmilikL[i] = idHakmilik;

            PermohonanSemakPelanHakmilik pelanHakmilik = new PermohonanSemakPelanHakmilik();
            if (idHakmilik != null) {
                Hakmilik hakmilik = hakmilikService.findById(idHakmilik);
//                    hakmilik.setIdHakmilik(idHakmilik);
                pelanHakmilik.setHakmilik(hakmilik);
                pelanHakmilik.setLuas(BigDecimal.valueOf(luasTemp));
                if (unitLuas != null && !unitLuas.equals("")) {
                    KodUOM kodUOM = kodUOMDAO.findById(unitLuas);
                    pelanHakmilik.setKodUom(kodUOM);
                } else {
                    pelanHakmilik.setKodUom(null);
                }
                pelanHakmilik.setNoPelanPA(noPelanPA);
                pelanHakmilik.setTarikhSah(new java.util.Date());
                if (tarikhSah != null) {
                    try {
                        pelanHakmilik.setTarikhSah(sdf.parse(tarikhSah));
                    } catch (ParseException ex) {
                        System.out.println("Got error" + ex);
                    }
                }
                pelanHakmilik.setMohonSemakPelan(mohonSemakPelan);

                info.setTarikhMasuk(new java.util.Date());
                info.setDimasukOleh(pengguna);
                pelanHakmilik.setInfoAudit(info);
                permohonanSemakPelanHakmilikDAO.saveOrUpdate(pelanHakmilik);
            }
        }
        tx.commit();
        addSimpleMessage("Maklumat telah berjaya disimpan.");
//        return new RedirectResolution(CarianHakmilikAction.class, "locate");

        return new ForwardResolution("/WEB-INF/jsp/utiliti/dev_save_mak_cetak.jsp");

    }

    @HandlesEvent("back")
    public Resolution back() {
        return new RedirectResolution(CarianHakmilikAction.class, "locate");
    }

    public Resolution popupPenyerah() {
        listHakmilik = getContext().getRequest().getParameter("hakmilikArry");

        LOG.info("listHakmilik @ popup:::::::::::" + listHakmilik);

        String[] s = listHakmilik.split(",");
        for (String str : s) {
            PermohonanSemakPelanHakmilik psph = pemohonanSemakPelanHakmilikService.findPermohonanSemakPelanByIdHakmilik(str);
            String idsmp = String.valueOf(psph.getMohonSemakPelan().getIdMsp());
            LOG.info("str @ popup::" + str);
            getList().add(idsmp);
        }

        return new ForwardResolution("/WEB-INF/jsp/common/maklumat_penyerah.jsp").addParameter("popup", "true");
    }

    public Resolution savePenyerah() {
        String namaPenyerah = getContext().getRequest().getParameter("namaPenyerah");
        String phoneNo = getContext().getRequest().getParameter("noPhone");

        LOG.info("namaPenyerah:::::::::::" + namaPenyerah);
        LOG.info("phoneNo:::::::::::" + phoneNo);
        return null;

    }

    /**
     * @return the listHakmilik
     */
    public String getListHakmilik() {
        return listHakmilik;
    }

    /**
     * @param listHakmilik the listHakmilik to set
     */
    public void setListHakmilik(String listHakmilik) {
        this.listHakmilik = listHakmilik;
    }

    /**
     * @return the list
     */
    public List<String> getList() {
        return list;
    }

    /**
     * @param list the list to set
     */
    public void setList(List<String> list) {
        this.list = list;
    }

    /**
     * @return the luas
     */
    public BigDecimal getLuas() {
        return luas;
    }

    /**
     * @param luas the luas to set
     */
    public void setLuas(BigDecimal luas) {
        this.luas = luas;
    }

    /**
     * @return the idHakmilikPopup
     */
    public String getIdHakmilikPopup() {
        return idHakmilikPopup;
    }

    /**
     * @param idHakmilikPopup the idHakmilikPopup to set
     */
    public void setIdHakmilikPopup(String idHakmilikPopup) {
        this.idHakmilikPopup = idHakmilikPopup;
    }

    public String getIdHakmilik() {
        return idHakmilik;
    }

    public void setIdHakmilik(String idHakmilik) {
        this.idHakmilik = idHakmilik;
    }

    public Double getLuasTemp() {
        return luasTemp;
    }

    public void setLuasTemp(Double luasTemp) {
        this.luasTemp = luasTemp;
    }

    public Double getLuasTotalTemp() {
        return luasTotalTemp;
    }

    public void setLuasTotalTemp(Double luasTotalTemp) {
        this.luasTotalTemp = luasTotalTemp;
    }

    public String[] getIdHakmilikL() {
        return idHakmilikL;
    }

    public void setIdHakmilikL(String[] idHakmilikL) {
        this.idHakmilikL = idHakmilikL;
    }

    public String getJumlahLuas() {
        return jumlahLuas;
    }

    public void setJumlahLuas(String jumlahLuas) {
        this.jumlahLuas = jumlahLuas;
    }

    public String getNamaPenyerah() {
        return namaPenyerah;
    }

    public void setNamaPenyerah(String namaPenyerah) {
        this.namaPenyerah = namaPenyerah;
    }

    public String getNoTel() {
        return noTel;
    }

    public void setNoTel(String noTel) {
        this.noTel = noTel;
    }

    public long getIdMsp() {
        return idMsp;
    }

    public void setIdMsp(long idMsp) {
        this.idMsp = idMsp;
    }

    
}
