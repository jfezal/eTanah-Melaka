/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.stripes.pelupusan;

import etanah.model.HakmilikPermohonan;
import etanah.model.Permohonan;
import etanah.dao.HakmilikPermohonanDAO;
import etanah.dao.PermohonanDAO;
import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.dao.HakmilikDAO;
import etanah.dao.PermohonanTuntutanKosDAO;
import etanah.model.Hakmilik;
import etanah.model.InfoAudit;
import etanah.model.KodCawangan;
import etanah.model.Pengguna;
import etanah.model.PermohonanTuntutanKos;
import etanah.service.HakmilikService;
import etanah.service.PelupusanService;
import etanah.view.etanahActionBeanContext;
import etanah.view.stripes.pelupusan.disClass.DisHakmilikPermohonan;
import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Vector;

import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;

/**
 *
 * @author afham
 */
@UrlBinding("/pelupusan/sedia_cukai_baru_mcl")
public class PenyediaanCukaiBaruMCL_ActionBean extends AbleActionBean {

    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    HakmilikPermohonanDAO hakmilikPermohonanDAO;
    @Inject
    HakmilikDAO hakmilikDAO;
    @Inject
    HakmilikService hakmilikService;
    @Inject
    PelupusanService plpservice;
    @Inject
    PermohonanTuntutanKosDAO permohonanTuntutanKosDAO;
    private Permohonan permohonan;
    private HakmilikPermohonan hakmilikPermohonan;
    private Hakmilik hakmilik;
    private PermohonanTuntutanKos permohonanTuntutanKos;
    private BigDecimal bayaranhakmilikkekal;
    private BigDecimal bayaranhakmiliksementara;
    private List<DisHakmilikPermohonan> listHakmilikWithCukai = new ArrayList<DisHakmilikPermohonan>();

    @DefaultHandler
    public Resolution showForm() {
        return new JSP("pelupusan/sedia_cukai_baru_mcl.jsp").addParameter("tab", "true");
    }

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {
        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = permohonanDAO.findById(idPermohonan);

        String[] tname = {"permohonan"};
        Object[] tvalue = {permohonan};

        List<HakmilikPermohonan> senaraiHakMilik = hakmilikPermohonanDAO.findByEqualCriterias(tname, tvalue, null);
//        hakmilikPermohonan = (HakmilikPermohonan) senaraiHakMilik.get(0);
//        hakmilik = hakmilikDAO.findById(hakmilikPermohonan.getHakmilik().getIdHakmilik());
        
        listHakmilikWithCukai = new ArrayList<DisHakmilikPermohonan>();
        for(HakmilikPermohonan hp : senaraiHakMilik){
            DisHakmilikPermohonan mohonHakmilikCls = new DisHakmilikPermohonan();
            BigDecimal cukaiBaruMH = new BigDecimal(0);            
            BigDecimal minCukai = new BigDecimal(6); // FOR RM 6.00
            BigDecimal halfCukai = hp.getHakmilik().getCukaiSebenar().divide(new BigDecimal(2));
            MathContext mc = new MathContext(0, RoundingMode.CEILING);            
            cukaiBaruMH = halfCukai.round(mc).compareTo(minCukai)>= 0?halfCukai.round(mc):new BigDecimal(6);            
            mohonHakmilikCls.setCukaiBaru(cukaiBaruMH.toString());
            mohonHakmilikCls.setHakmilikPermohonan(hp);
            listHakmilikWithCukai.add(mohonHakmilikCls);
        }


    }

    public Resolution simpanCukaiBaru() {
//            hakmilikPermohonan.setCatatan(null)
        System.out.print("HAKMILIKPERMOHONAN: " + hakmilikPermohonan.getId());
        plpservice.simpanHakmilikPermohonan(hakmilikPermohonan);
//            hakmilikService.saveHakmilik(hmp.getHakmilik());
        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = permohonanDAO.findById(idPermohonan);

        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        InfoAudit infoAudit = new InfoAudit();
        infoAudit.setDimasukOleh(peng);
        infoAudit.setTarikhMasuk(new java.util.Date());
        KodCawangan caw = peng.getKodCawangan();

        String[] tname = {"permohonan"};
        Object[] tvalue = {permohonan};
        List senaraiHakMilik = hakmilikPermohonanDAO.findByEqualCriterias(tname, tvalue, null);
        hakmilikPermohonan = (HakmilikPermohonan) senaraiHakMilik.get(0);
        hakmilik = hakmilikDAO.findById(hakmilikPermohonan.getHakmilik().getIdHakmilik());
        List<PermohonanTuntutanKos> permohonantnttkos = permohonanTuntutanKosDAO.findByEqualCriterias(tname, tvalue, null);
        if (hakmilik.getLot().getNama().equalsIgnoreCase("lot")) {
             bayaranhakmilikkekal = BigDecimal.valueOf(30.00);
            bayaranhakmiliksementara = BigDecimal.valueOf(0.00);
        if(permohonantnttkos.isEmpty()){
            System.out.println("test1") ;           
            String[] itemList = {"Pendaftaran Hakmilik Sementara", "Pendaftaran Hakmilik Kekal"};
            BigDecimal[] amaunList = {bayaranhakmiliksementara, bayaranhakmilikkekal};
            for (int i = 0; i < 2; i++) {
                        PermohonanTuntutanKos ptk = new PermohonanTuntutanKos();
                        ptk.setPermohonan(permohonan);
                        ptk.setCawangan(caw);
                        ptk.setInfoAudit(infoAudit);
                        ptk.setItem(itemList[i]);
                        ptk.setAmaunTuntutan(amaunList[i]);
                        plpservice.simpanPermohonanTuntutanKos1(ptk);
                    }
            }
//        else{
//             for (int z = 0; z < permohonantnttkos.size(); z++) {
//                System.out.println("test2") ;
//                permohonanTuntutanKos = (PermohonanTuntutanKos) permohonantnttkos.get(z);
//                if (permohonanTuntutanKos.getItem().equalsIgnoreCase("pendaftaran hakmilik kekal")) {
//                    System.out.println("Data ada");
//                    break ;
//
//                }
//
//            }
        }

    else {
            if(permohonantnttkos.isEmpty()){
                 bayaranhakmilikkekal = BigDecimal.valueOf(30.00);
            bayaranhakmiliksementara = BigDecimal.valueOf(50.00);
            System.out.println("test1") ;
            String[] itemList = {"Pendaftaran Hakmilik Sementara", "Pendaftaran Hakmilik Kekal"};
            BigDecimal[] amaunList = {bayaranhakmiliksementara, bayaranhakmilikkekal};
            for (int i = 0; i < 2; i++) {
                        PermohonanTuntutanKos ptk = new PermohonanTuntutanKos();
                        ptk.setPermohonan(permohonan);
                        ptk.setCawangan(caw);
                        ptk.setInfoAudit(infoAudit);
                        ptk.setItem(itemList[i]);
                        ptk.setAmaunTuntutan(amaunList[i]);
                        plpservice.simpanPermohonanTuntutanKos1(ptk);
            }
            }
        }

//             for (int z = 0; z < permohonantnttkos.size(); z++) {
//                permohonanTuntutanKos = (PermohonanTuntutanKos) permohonantnttkos.get(z);
//                if (permohonanTuntutanKos.getItem().equalsIgnoreCase("pendaftaran hakmilik sementara")) {
//                    System.out.println("Data ada");
//                    break ;
//                }
//               else if(permohonanTuntutanKos.getItem().equalsIgnoreCase("pendaftaran hakmilik kekal")){
//                        System.out.println("Data ada");
//            

        addSimpleMessage("Maklumat telah berjaya disimpan.");
        return new JSP("pelupusan/sedia_cukai_baru_mcl.jsp").addParameter("tab", "true");
    }

    public Hakmilik getHakmilik() {
        return hakmilik;
    }

    public void setHakmilik(Hakmilik hakmilik) {
        this.hakmilik = hakmilik;
    }

    public Permohonan getPermohonan() {
        return permohonan;
    }

    public void setPermohonan(Permohonan permohonan) {
        this.permohonan = permohonan;
    }

    public HakmilikPermohonan getHakmilikPermohonan() {
        return hakmilikPermohonan;
    }

    public void setHakmilikPermohonan(HakmilikPermohonan hakmilikPermohonan) {
        this.hakmilikPermohonan = hakmilikPermohonan;
    }

    public BigDecimal getBayaranhakmilikkekal() {
        return bayaranhakmilikkekal;
    }

    public void setBayaranhakmilikkekal(BigDecimal bayaranhakmilikkekal) {
        this.bayaranhakmilikkekal = bayaranhakmilikkekal;
    }

    public BigDecimal getBayaranhakmiliksementara() {
        return bayaranhakmiliksementara;
    }

    public void setBayaranhakmiliksementara(BigDecimal bayaranhakmiliksementara) {
        this.bayaranhakmiliksementara = bayaranhakmiliksementara;
    }

    public PermohonanTuntutanKos getPermohonanTuntutanKos() {
        return permohonanTuntutanKos;
    }

    public void setPermohonanTuntutanKos(PermohonanTuntutanKos permohonanTuntutanKos) {
        this.permohonanTuntutanKos = permohonanTuntutanKos;
    }

    public List<DisHakmilikPermohonan> getListHakmilikWithCukai() {
        return listHakmilikWithCukai;
    }

    public void setListHakmilikWithCukai(List<DisHakmilikPermohonan> listHakmilikWithCukai) {
        this.listHakmilikWithCukai = listHakmilikWithCukai;
    }
    
}
