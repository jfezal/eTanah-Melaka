/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.stripes.lelong;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.dao.HakmilikPermohonanDAO;
import etanah.dao.PenggunaDAO;
import etanah.dao.PermohonanDAO;
import etanah.model.Enkuiri;
import etanah.model.Hakmilik;
import etanah.model.HakmilikPermohonan;
import etanah.model.Lelongan;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.service.common.LelongService;
import etanah.view.etanahActionBeanContext;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;
import org.apache.log4j.Logger;

/**
 *
 * @author mdizzat.mashrom
 */
@UrlBinding("/lelong/permohonan_batal")
public class PermohonanBatalActionBean extends AbleActionBean {

    private static final Logger logger = Logger.getLogger(PermohonanBatalActionBean.class);
    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    HakmilikPermohonanDAO hakmilikPermohonanDAO;
    @Inject
    PenggunaDAO penggunaDAO;
    @Inject
    LelongService lelongService;
    private String idPermohonan;
    private Permohonan permohonan;
    private String idHakmilik;
    private List<Permohonan> listHP2;
    private String idMohonSblm;
    private Permohonan permohonanSblm;
    private Enkuiri enkuiri;
    private Lelongan lelongan;
    private boolean boton = false;
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm aaa");
    private Hakmilik hakmilik;
    private Permohonan mohon;
    private List<Permohonan> listMohon;
    private List<String> listIDMohon;
    private Pengguna pengguna;
    private List<String> listHakmilik;

    @DefaultHandler
    public Resolution showForm() {
        return new JSP("lelong/batal_permohonan_lelong.jsp").addParameter("tab", "true");
    }

    public Resolution showForm3() {
        boton = true;
        return new JSP("lelong/batal_permohonan_lelong.jsp").addParameter("tab", "true");
    }

     @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        if (idPermohonan != null) {
            permohonan = permohonanDAO.findById(idPermohonan);

            if (permohonan.getPermohonanSebelum() != null) {
                permohonanSblm = permohonan.getPermohonanSebelum();
                idMohonSblm = permohonanSblm.getIdPermohonan();
                if (permohonanSblm != null) {
                    listMohon = lelongService.getHistory2(idPermohonan);
                }
            }

            List<HakmilikPermohonan> listHP = permohonan.getSenaraiHakmilik();
            listHakmilik = new ArrayList<String>();
            for (HakmilikPermohonan hp : listHP) {
                listHakmilik.add(hp.getHakmilik().getIdHakmilik());
            }
            logger.info("listHakmilik : " + listHakmilik.size());
            List<String> listIDMohon1 = lelongService.listHakmilikPermohonan2(listHakmilik, idPermohonan);
            logger.info("listIDMohon1 : " + listIDMohon1.size());
            List<String> listIDMohon2 = lelongService.listHakmilikPermohonan3(listIDMohon1, listHakmilik.size(), idPermohonan);
            logger.info("listIDMohon2 : " + listIDMohon2.size());
            Map<String, String> pihakMap2 = new HashMap<String, String>();
            for (String hp : listIDMohon2) {
                String id = hp;
                if (pihakMap2.containsKey(id)) {
                    continue;
                } else {
                    pihakMap2.put(id, hp);
                }
            }
            listIDMohon = new ArrayList(pihakMap2.values());
            logger.info("listIDMohon : " + listIDMohon.size());
            List<Permohonan> listHP3 = new ArrayList<Permohonan>();
            for (String idmo : listIDMohon) {
                List<Permohonan> hp = lelongService.getHistory2(idmo);
                listHP3.add(hp.get(0));
            }
            logger.info("listHP3 : " + listHP3.size());

            listHP2 = new ArrayList<Permohonan>();
            Map<String, Hakmilik> hakmilikMap = new HashMap<String, Hakmilik>();
            for (HakmilikPermohonan ho : permohonan.getSenaraiHakmilik()) {
                String idMohon = ho.getHakmilik().getIdHakmilik();
                hakmilikMap.put(idMohon, ho.getHakmilik());
            }

            for (Permohonan pp : listHP3) {
                String ok = "";
                if (permohonan.getSenaraiHakmilik().size() == pp.getSenaraiHakmilik().size()) {
                    logger.info("pp.getSenaraiHakmilik().size() : " + pp.getSenaraiHakmilik().size());
                    for (HakmilikPermohonan hp : pp.getSenaraiHakmilik()) {
                        if (hakmilikMap.containsKey(hp.getHakmilik().getIdHakmilik())) {
                            logger.info("---ok---");
                        } else {
                            logger.info("---xok---");
                            ok = "xok";
                        }
                    }
                    logger.info("ok : " + ok);
                    if (!ok.equals("xok")) {
                        listHP2.add(pp);
                    }
                }
            }

            logger.info("listHP2 : " + listHP2.size());

            logger.info("idHakmilik : " + idHakmilik);
            logger.info("idPermohonan : " + idPermohonan);
            List<HakmilikPermohonan> listHakhak = lelongService.getPermohonanByIdmohonSBLM(idHakmilik, idPermohonan);
            logger.info("listHakhak : " + listHakhak.size());
            if (listHakhak.size() != 0) {
                for (HakmilikPermohonan hak : listHakhak) {
                    hakmilik = hak.getHakmilik();
                    mohon = hak.getPermohonan();
                }
            }
        }
    }

    public Resolution simpan() {
        logger.info("----simpan----");

        logger.info("listHP2.size() : " + listHP2.size());
        String idMohonla = getContext().getRequest().getParameter("idsblm");
        logger.info("ID mohon sebelum : " + idMohonla);
        if (idMohonla != null) {
            permohonanSblm = permohonanDAO.findById(idMohonla);
            permohonan.setPermohonanSebelum(permohonanSblm);
            lelongService.saveOrUpdate(permohonan);

            List<Enkuiri> listEnkuiri = lelongService.getEnkuiriSblm(idMohonla);
            logger.info("listEnkuiri.size() :" + listEnkuiri.size());
            if (listEnkuiri.size() != 0) {
                enkuiri = listEnkuiri.get(0);
                logger.info("idenkuiri : " + enkuiri.getIdEnkuiri());
                List<Lelongan> listLelong = lelongService.getLelongSblm(enkuiri);

                if (listLelong.size() != 0) {
                    logger.info("listLelong.size() :" + listLelong.size());
                    lelongan = listLelong.get(0);
                    logger.info("lelong : " + lelongan.getIdLelong());
                    lelongan.setPermohonanBatal(permohonan);
                    lelongService.saveOrUpdate(lelongan);
                }
            }
        }
        rehydrate();
        addSimpleMessage("Maklumat Telah Berjaya Disimpan...");
        return new JSP("lelong/batal_permohonan_lelong.jsp").addParameter("tab", "true");
    }

    public Resolution delete() {
        logger.info("-----delete----");
        String idMohonla = getContext().getRequest().getParameter("idMohon");
        logger.info("ID mohon sebelum : " + idMohonla);
        if (idMohonla != null) {
            permohonan.setPermohonanSebelum(null);
            lelongService.saveOrUpdate(permohonan);

            List<Enkuiri> listEnkuiri = lelongService.getEnkuiriSblm(idMohonla);
            logger.info("listEnkuiri.size() :" + listEnkuiri.size());
            if (listEnkuiri.size() != 0) {
                enkuiri = listEnkuiri.get(0);
                logger.info("idenkuiri : " + enkuiri.getIdEnkuiri());
                List<Lelongan> listLelong = lelongService.getLelongSblm(enkuiri);
                if (listLelong.size() != 0) {
                    logger.info("listLelong.size() :" + listLelong.size());
                    lelongan = listLelong.get(0);
                    logger.info("lelong : " + lelongan.getIdLelong());
                    lelongan.setPermohonanBatal(null);
                    lelongService.saveOrUpdate(lelongan);
                }
            }
        }
        rehydrate();
        return new JSP("lelong/batal_permohonan_lelong.jsp").addParameter("tab", "true");
    }

    public String getIdPermohonan() {
        return idPermohonan;
    }

    public void setIdPermohonan(String idPermohonan) {
        this.idPermohonan = idPermohonan;
    }

    public Permohonan getPermohonan() {
        return permohonan;
    }

    public void setPermohonan(Permohonan permohonan) {
        this.permohonan = permohonan;
    }

    public String getIdHakmilik() {
        return idHakmilik;
    }

    public void setIdHakmilik(String idHakmilik) {
        this.idHakmilik = idHakmilik;
    }

    public String getIdMohonSblm() {
        return idMohonSblm;
    }

    public void setIdMohonSblm(String idMohonSblm) {
        this.idMohonSblm = idMohonSblm;
    }

    public Permohonan getPermohonanSblm() {
        return permohonanSblm;
    }

    public void setPermohonanSblm(Permohonan permohonanSblm) {
        this.permohonanSblm = permohonanSblm;
    }

    public Enkuiri getEnkuiri() {
        return enkuiri;
    }

    public void setEnkuiri(Enkuiri enkuiri) {
        this.enkuiri = enkuiri;
    }

    public Lelongan getLelongan() {
        return lelongan;
    }

    public void setLelongan(Lelongan lelongan) {
        this.lelongan = lelongan;
    }

    public List<Permohonan> getListHP2() {
        return listHP2;
    }

    public void setListHP2(List<Permohonan> listHP2) {
        this.listHP2 = listHP2;
    }

    public List<Permohonan> getListMohon() {
        return listMohon;
    }

    public void setListMohon(List<Permohonan> listMohon) {
        this.listMohon = listMohon;
    }

    public boolean isBoton() {
        return boton;
    }

    public void setBoton(boolean boton) {
        this.boton = boton;
    }

    public Hakmilik getHakmilik() {
        return hakmilik;
    }

    public void setHakmilik(Hakmilik hakmilik) {
        this.hakmilik = hakmilik;
    }

    public List<String> getListIDMohon() {
        return listIDMohon;
    }

    public void setListIDMohon(List<String> listIDMohon) {
        this.listIDMohon = listIDMohon;
    }

    public Permohonan getMohon() {
        return mohon;
    }

    public void setMohon(Permohonan mohon) {
        this.mohon = mohon;
    }

    public Pengguna getPengguna() {
        return pengguna;
    }

    public void setPengguna(Pengguna pengguna) {
        this.pengguna = pengguna;
    }

    public List<String> getListHakmilik() {
        return listHakmilik;
    }

    public void setListHakmilik(List<String> listHakmilik) {
        this.listHakmilik = listHakmilik;
    }
    
}
