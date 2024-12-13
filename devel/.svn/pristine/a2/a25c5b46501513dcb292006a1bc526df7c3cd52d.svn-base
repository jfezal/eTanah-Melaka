/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.stripes;

import able.stripes.AbleActionBean;
import com.google.inject.Inject;
import etanah.dao.HakmilikPihakBerkepentinganDAO;
import etanah.dao.PermohonanDAO;
import etanah.dao.PihakDAO;
import etanah.manager.TabManager;
import etanah.model.Hakmilik;
import java.util.ArrayList;


import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;

/**
 *
 * @author solahuddin
 */
@UrlBinding("/maklumatPemohon")
public class MaklumatPemohonActionBean extends AbleActionBean {

    TabManager tabManager;
    private Permohonan permohonan;
    private Hakmilik hakmilik;
    PermohonanDAO permohonanDao;
    PihakDAO pihakDAO;
    HakmilikPihakBerkepentinganDAO hakmilikPBDao;
    ArrayList listTuanTanah = new ArrayList();
    ArrayList listPemohon = new ArrayList();

    @Inject
    public MaklumatPemohonActionBean(TabManager tabManager, PermohonanDAO permohonanDao) {
        this.tabManager = tabManager;
        this.permohonanDao = permohonanDao;
    }

    @DefaultHandler
    public Resolution showForm() {

        popup();

        rehydrate();
        return new ForwardResolution("/WEB-INF/jsp/tab/main_page.jsp");

    }

    public Resolution popup() {


        return new ForwardResolution("/WEB-INF/jsp/common/pilih_pemohon.jsp");

    }

    private void rehydrate() {
        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        if (idPermohonan != null) {

            // permohonan = permohonanDao.findById("aaaaaaa");

            //List listSenaraiHakmilik = permohonan.getSenaraiHakmilik();

//            HakmilikPermohonan hakmilikPermohonan = (HakmilikPermohonan) listSenaraiHakmilik.get(0);
//
//            hakmilik = hakmilikPermohonan.getHakmilik();
//
//            for(int i=0; i<hakmilik.getSenaraiPihakBerkepentingan().size(); i++){
//
//                HakmilikPihakBerkepentingan hakmilikPB = new HakmilikPihakBerkepentingan();
//
//                hakmilikPB = hakmilik.getSenaraiPihakBerkepentingan().get(i);
//
//                Pihak pihak = hakmilikPB.getPihak();
//
//                listTuanTanah.add(pihak);
//
//           }
        }
    }

    public ArrayList getListTuanTanah() {
        return listTuanTanah;
    }

    public void setListTuanTanah(ArrayList listTuanTanah) {
        this.listTuanTanah = listTuanTanah;
    }
}
