/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package etanah.view.stripes;

import com.google.inject.Inject;
import etanah.dao.HakmilikPermohonanDAO;
import etanah.dao.HakmilikPihakBerkepentinganDAO;
import etanah.dao.PemohonDAO;
import etanah.dao.PermohonanDAO;
import etanah.dao.PermohonanPihakDAO;
import etanah.dao.PihakDAO;
import etanah.manager.TabManager;
import etanah.model.Hakmilik;
import etanah.model.HakmilikPermohonan;
import etanah.model.HakmilikPihakBerkepentingan;
import etanah.model.Pemohon;
import etanah.model.Permohonan;
import etanah.model.PermohonanPihak;
import etanah.model.Pihak;
import etanah.view.BasicTabActionBean;
import java.util.ArrayList;
import java.util.List;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;

/**
 *
 * @author muhammad.amin
 */
@UrlBinding("/keputusanPermohonanConsent")

public class KeputusanPermohonanConsentActionBean extends BasicTabActionBean {

    TabManager tabManager;
    private Permohonan permohonan;
    private Hakmilik hakmilik;
    private PermohonanPihak permohonanPihak;
    private Pihak pihak;
    private Pemohon pemohon;
    private HakmilikPermohonan hakmilikPermohonan;


    private String[] arrayPemohon;

    String idHakmilik;

    PermohonanDAO permohonanDao;
    PermohonanPihakDAO permohonanPihakDAO;
    PihakDAO pihakDAO;
    HakmilikPihakBerkepentinganDAO hakmilikPBDao;
    PemohonDAO pemohonDao;
    HakmilikPermohonanDAO hakmilikPermohonanDAO;

    ArrayList listTuanTanah = new ArrayList();
    ArrayList listPemohon = new ArrayList();

    @Inject
    public KeputusanPermohonanConsentActionBean(TabManager tabManager, PermohonanDAO permohonanDao, PihakDAO pihakDAO, PermohonanPihakDAO permohonanPihakDAO, HakmilikPihakBerkepentinganDAO hakmilikPBDao, PemohonDAO pemohonDao, HakmilikPermohonanDAO hakmilikPermohonanDAO) {
        this.tabManager = tabManager;
        this.permohonanDao = permohonanDao;
        this.pihakDAO = pihakDAO;
        this.permohonanPihakDAO = permohonanPihakDAO;
        this.hakmilikPBDao = hakmilikPBDao;
        this.pemohonDao = pemohonDao;
        this.hakmilikPermohonanDAO = hakmilikPermohonanDAO;

    }

    @DefaultHandler
    public Resolution showForm() {      

        rehydrate();

        return new ForwardResolution("/WEB-INF/jsp/tab/main_page.jsp").addParameter("txnCode", txnCode).addParameter("stageId", stageId);


    }

    private void rehydrate() {
        if (idPermohonan != null) {

            permohonan = permohonanDao.findById(idPermohonan);

            if (permohonan != null) {

                List listAllHakmilikPermohonan = hakmilikPermohonanDAO.findAll();

                for(int i=0; i<listAllHakmilikPermohonan.size(); i++){

                    HakmilikPermohonan hakmilikPermohonan = new HakmilikPermohonan();

                    hakmilikPermohonan = (HakmilikPermohonan) listAllHakmilikPermohonan.get(i);

                    hakmilik = hakmilikPermohonan.getHakmilik();

                  for (int j = 0; j < hakmilik.getSenaraiPihakBerkepentingan().size(); j++) {

                    HakmilikPihakBerkepentingan hakmilikPB = new HakmilikPihakBerkepentingan();

                    hakmilikPB = hakmilik.getSenaraiPihakBerkepentingan().get(j);

                    Pihak pihak = hakmilikPB.getPihak();

                    listTuanTanah.add(pihak);

                }
                }
            }

            ArrayList listAllPemohon = new ArrayList();
            ArrayList listSelectedPemohon = new ArrayList();
            listPemohon = new ArrayList();

            listAllPemohon = (ArrayList) pemohonDao.findAll();

            for(int i=0; i<listAllPemohon.size(); i++){

                pemohon = new Pemohon();
                pemohon = (Pemohon) listAllPemohon.get(i);

                if(pemohon.getPermohonan().getIdPermohonan().equals(idPermohonan)){

                    listSelectedPemohon.add(pemohon);

                }
            }

            for(int i=0; i<listSelectedPemohon.size(); i++){

                pemohon = new Pemohon();
                pemohon = (Pemohon) listSelectedPemohon.get(i);

                pihak = new Pihak();

                //pihak = pihakDAO.findById(Long.valueOf(pemohon.getPihak().getIdPihak()));

                listPemohon.add(pihak);

            }

            List listMohonPenerima = permohonanPihakDAO.findAll();

            for(int i=0; i<listMohonPenerima.size(); i++){
                
                PermohonanPihak permohonanPihakTemp = new PermohonanPihak();
                permohonanPihakTemp = (PermohonanPihak) listMohonPenerima.get(i);

                if(permohonanPihakTemp.getPermohonan().getIdPermohonan().equals(idPermohonan)){

                    permohonanPihak = permohonanPihakTemp;
                }                
            }
        }
    }
    public Permohonan getPermohonan() {
        return permohonan;
    }

    public void setPermohonan(Permohonan permohonan) {
        this.permohonan = permohonan;
    }

    public Hakmilik getHakmilik() {
        return hakmilik;
    }

    public void setHakmilik(Hakmilik hakmilik) {
        this.hakmilik = hakmilik;
    }

    public ArrayList getListTuanTanah() {
        return listTuanTanah;
    }

    public void setListTuanTanah(ArrayList listTuanTanah) {
        this.listTuanTanah = listTuanTanah;
    }

    public ArrayList getListPemohon() {
        return listPemohon;
    }

    public void setListPemohon(ArrayList listPemohon) {
        this.listPemohon = listPemohon;
    }

    public PermohonanPihak getPermohonanPihak() {
        return permohonanPihak;
    }

    public void setPermohonanPihak(PermohonanPihak permohonanPihak) {
        this.permohonanPihak = permohonanPihak;
    }

    public Pihak getPihak() {
        return pihak;
    }

    public void setPihak(Pihak pihak) {
        this.pihak = pihak;
    }

    public HakmilikPermohonan getHakmilikPermohonan() {
        return hakmilikPermohonan;
    }

    public void setHakmilikPermohonan(HakmilikPermohonan hakmilikPermohonan) {
        this.hakmilikPermohonan = hakmilikPermohonan;
    }

    public String getIdHakmilik() {
        return idHakmilik;
    }

    public void setIdHakmilik(String idHakmilik) {
        this.idHakmilik = idHakmilik;
    }


    public String[] getArrayPemohon() {
        return arrayPemohon;
    }

    public void setArrayPemohon(String[] arrayPemohon) {
        this.arrayPemohon = arrayPemohon;
    }
   
}
