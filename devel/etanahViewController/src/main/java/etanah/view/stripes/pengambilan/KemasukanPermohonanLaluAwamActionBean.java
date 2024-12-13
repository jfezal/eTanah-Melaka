/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package etanah.view.stripes.pengambilan;

/**
 *
 * @author massita
 */
import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.dao.PermohonanDAO;
import etanah.model.HakmilikPermohonan;
import etanah.model.Permohonan;
import etanah.model.Pengguna;
import etanah.model.InfoAudit;
import etanah.model.Hakmilik;
import etanah.model.KodUOM;
import etanah.dao.KodUOMDAO;
import etanah.dao.HakmilikDAO;
import etanah.service.common.HakmilikService;
import etanah.service.common.HakmilikPermohonanService;
import java.util.List;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.HttpCache;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.RedirectResolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;
import java.util.Collections;
import java.util.LinkedList;
import net.sourceforge.stripes.action.ForwardResolution;
import etanah.model.HakmilikPihakBerkepentingan;
import java.math.BigDecimal;
import java.util.ArrayList;

import etanah.view.etanahActionBeanContext;
import javax.swing.JOptionPane;

@UrlBinding("/pengambilan/kemasukanLaluAwam")
public class KemasukanPermohonanLaluAwamActionBean extends AbleActionBean {

    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    Permohonan permohonan;
    @Inject
    HakmilikPermohonanService hakmilikpermohonanservice;
    @Inject
    HakmilikService hakmilikService;
    String idHakmilik;
    @Inject
    Hakmilik hakmilik;
    @Inject
    KodUOM KodOUM;
    @Inject
    HakmilikDAO hakmilikDAO;
    @Inject
    KodUOMDAO kodUOMDAO;
    private List<Hakmilik> list;
    private List<HakmilikPermohonan> hakmilikPermohonanList;
    private List<HakmilikPihakBerkepentingan> pihakKepentinganList;
    private List<String> luasTerlibat = new ArrayList<String>();
    private List<String> luasPelanB1 = new ArrayList<String>();
    private List<String> kodUnitLuas = new ArrayList<String>();
    private HakmilikPermohonan hakmilikPermohonan;
    private int size = 0;
    private String[] listluasTerlibat;
    private String noHakmilik;



    @DefaultHandler
    public Resolution showForm() {
        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        return new JSP("pengambilan/negerisembilan/haklalulalangawam/kemasukanPermohonanLaluAwam.jsp").addParameter("tab", "true");
    }
    //@HttpCache(allow=false)

    public Resolution showForm2() {
//        idHakmilik = (String) getContext().getRequest().getParameter("idHakmilik");
//        return new JSP("common/paparan_maklumat_hakmilik_permohonan_terperinci.jsp").addParameter("tab", "true");
         getContext().getRequest().setAttribute("!edit", Boolean.TRUE);
        return new JSP("pengambilan/negerisembilan/haklalulalangawam/kemasukanPermohonanLaluAwam.jspp").addParameter("tab", "true");
    }

    public Resolution showForm3() {
        return new JSP("pengambilan/maklumat_tanah_rizab.jsp").addParameter("tab", "true");
    }

    public Resolution hakMilikPopup() {
        return new JSP("pengambilan/kemasukan_hakmilik.jsp").addParameter("popup", "true").addParameter("showForm", "false");
    }
    public Resolution popup() {
        return new JSP("pengambilan/maklumat_asas_tanah_pengambilan.jsp").addParameter("popup", "true").addParameter("showForm", "false");
    }

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {
        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        idHakmilik = (String) getContext().getRequest().getParameter("idHakmilik");
        if (idPermohonan != null) {
            Permohonan p = permohonanDAO.findById(idPermohonan);
            hakmilikPermohonanList = p.getSenaraiHakmilik();
            for (int i = 0; i < hakmilikPermohonanList.size(); i++) {

            try{
                BigDecimal luasambil = hakmilikPermohonanList.get(i).getLuasTerlibat();
                luasTerlibat.add(luasambil.toString());
            }catch(Exception e){

            }
            try{
                BigDecimal luaspelan = hakmilikPermohonanList.get(i).getLuasPelanB1();
                luasPelanB1.add(luaspelan.toString());
            }catch(Exception e){

            }

            try{
            String name = hakmilikPermohonanList.get(i).getKodUnitLuas().getKod();
            kodUnitLuas.add(name);
            }catch(Exception e){
                kodUnitLuas.add("");
            }

            }
        }
    }


    public Resolution simpanHakmilik() {
        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = permohonanDAO.findById(idPermohonan);
        String kod=null;
//        JOptionPane.showConfirmDialog(null, hakmilikPermohonanList.size());
        if (luasTerlibat.isEmpty() ) {
            addSimpleError("Sila Masukkan Luas yang diambil");

        }else if(luasPelanB1.isEmpty()){
            addSimpleError("Sila Masukkan Luas Pelan B1");
        }
        else{

            for (int i = 0; i < hakmilikPermohonanList.size(); i++) {
                HakmilikPermohonan hmp = hakmilikPermohonanList.get(i);
                try {
                    if (i < luasTerlibat.size()) {
                        if(luasTerlibat.get(i) != null)
                            hmp.setLuasTerlibat(new BigDecimal(luasTerlibat.get(i)));
                            KodUOM kodUOM = hmp.getHakmilik().getKodUnitLuas();
                            hmp.setKodUnitLuas(kodUOM);
                    }
                    if (i < luasPelanB1.size()) {
                        if(luasPelanB1.get(i) != null)
                            hmp.setLuasTerlibat(new BigDecimal(luasPelanB1.get(i)));
                            KodUOM kodUOM = hmp.getHakmilik().getKodUnitLuas();
                            hmp.setLuasPelanB1Uom(kodUOM);
                    }
                }catch(Exception e){
                    addSimpleError("Invalid Data");
                }
                hakmilikpermohonanservice.saveSingleHakmilikPermohonan(hmp);
            }
         //hakmilikService.savehakmilikpermohonan(hakmilikPermohonanList);
        }
        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        addSimpleMessage("Maklumat telah berjaya disimpan.");
        return new ForwardResolution("/WEB-INF/jsp/pengambilan/rekodTuntutanLebihan.jsp").addParameter("tab", "true");
    }

     public Resolution deleteSingle() {
            String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
            Permohonan permohonan = permohonanDAO.findById(idPermohonan);
            InfoAudit ia = new InfoAudit();
            Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
            HakmilikPermohonan hmp = new HakmilikPermohonan();
            String id = getContext().getRequest().getParameter("id");
             hmp  =hakmilikService.findHakmilikByIdHakmilik(Long.parseLong(id));

            if (hmp!= null) {
//                JOptionPane.showMessageDialog(null, id);


            ia = peng.getInfoAudit();
            ia.setDiKemaskiniOleh(peng);
            ia.setTarikhKemaskini(new java.util.Date());
            hmp.setInfoAudit(ia);
            hmp.setHakmilik(hakmilik);
            hmp.setPermohonan(permohonan);
            hakmilikPermohonanList.remove(hmp);
            hakmilikService.deletehakmilikpermohonan(hakmilikPermohonanList);
}
            return new RedirectResolution(MaklumatHakmilikActionBean.class);
}

    public Resolution searchHakmilik() {

        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        String idH = getContext().getRequest().getParameter("idH");
        Permohonan permohonan = permohonanDAO.findById(idPermohonan);
        hakmilik = hakmilikService.findById(idH);
//        JOptionPane.showMessageDialog(null, hakmilik.getNoHakmilik());
        if (idPermohonan != null) {
            InfoAudit info = peng.getInfoAudit();
            info.setDimasukOleh(peng);
             info.setTarikhMasuk(new java.util.Date());
             HakmilikPermohonan hmp = new HakmilikPermohonan();

            hmp.setInfoAudit(info);
            hmp.setPermohonan(permohonan);
            hmp.setHakmilik(hakmilik);
            hakmilikPermohonanList.add(hmp);
            hakmilikService.saveOrUpdatehakmilikpermohonan(hmp);

        }
         addSimpleMessage("Maklumat telah berjaya disimpan");
        return new ForwardResolution("/WEB-INF/jsp/pengambilan/maklumat_hakmilik_pengambilan.jsp").addParameter("tab", "true");


    }

    public Resolution refreshpage() {
    rehydrate();
    return new RedirectResolution(MaklumatHakmilikActionBean.class, "locate");
}


    public Resolution simpan() {
        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        idHakmilik = (String) getContext().getRequest().getParameter("idHakmilik");

        if (idPermohonan != null)
        {
            Permohonan p = permohonanDAO.findById(idPermohonan);
            HakmilikPermohonan hmp = new HakmilikPermohonan();
            hmp.getHakmilik();
            hakmilikService.simpanAmbil(hakmilik, p);
            addSimpleMessage("Maklumat telah berjaya disimpan.");

        }
        return new ForwardResolution("/WEB-INF/jsp/pengambilan/maklumat_hakmilik_pengambilan.jsp").addParameter("tab", "true");
//        hakmilik = hakmilikDAO.findById(idHakmilik);
//        hakmilikService.simpanAmbil(hakmilik);
//
//        return new ForwardResolution("/WEB-INF/jsp/pengambilan/maklumat_hakmilik_pengambilan.jsp").addParameter("tab", "true");
    }

    public List<Hakmilik> getList() {
        return list;
    }

    public void setList(List<Hakmilik> list) {
        this.list = list;
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

    public List<HakmilikPermohonan> getHakmilikPermohonanList() {
        return hakmilikPermohonanList;
    }

    public void setHakmilikPermohonanList(List<HakmilikPermohonan> hakmilikPermohonanList) {
        this.hakmilikPermohonanList = hakmilikPermohonanList;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

//    public String[] getAtasTanah() {
//        return luasTerlibat;
//    }
//
//    public void setAtasTanah(String[] luasTerlibat) {
//        this.luasTerlibat = luasTerlibat;
//    }

    public HakmilikPermohonan getHakmilikPermohonan() {
        return hakmilikPermohonan;
    }

    public void setHakmilikPermohonan(HakmilikPermohonan hakmilikPermohonan) {
        this.hakmilikPermohonan = hakmilikPermohonan;
    }
    public String getNoHakmilik() {
        return noHakmilik;
    }

    public void setNoHakmilik(String noHakmilik) {
        this.noHakmilik = noHakmilik;
    }
     public List<HakmilikPihakBerkepentingan> getPihakKepentinganList() {
        return pihakKepentinganList;
    }

    public void setPihakKepentinganList(List<HakmilikPihakBerkepentingan> pihakKepentinganList) {
        this.pihakKepentinganList = pihakKepentinganList;
    }

    public List<String> getLuasTerlibat() {
        return luasTerlibat;
    }

    public void setLuasTerlibat(List<String> luasTerlibat) {
        this.luasTerlibat = luasTerlibat;
    }

     public String[] getListluasTerlibat() {
        return listluasTerlibat;
    }

    public void setListluasTerlibat(String[] listluasTerlibat) {
        this.listluasTerlibat = listluasTerlibat;
    }

//        public List<String> getLuasdiambil() {
//        return luasdiambil;
//    }
//
//    public void setLuasdiambil(List<String> luasdiambil) {
//        this.luasdiambil = luasdiambil;
//    }

    public List<String> getKodUnitLuas() {
        return kodUnitLuas;
    }

    public void setKodUnitLuas(List<String> kodUnitLuas) {
        this.kodUnitLuas = kodUnitLuas;
    }


  public KodUOM getKodOUM() {
        return KodOUM;
    }

    public void setKodOUM(KodUOM KodOUM) {
        this.KodOUM = KodOUM;
    }


    public List<String> getLuasPelanB1() {
        return luasPelanB1;
    }

    public void setLuasPelanB1(List<String> luasPelanB1) {
        this.luasPelanB1 = luasPelanB1;
    }
}
