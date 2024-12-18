/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.stripes.hasil;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.dao.DasarTuntutanCukaiHakmilikDAO;
import etanah.dao.DasarTuntutanNotisDAO;
import etanah.dao.KodNotisDAO;
import etanah.model.DasarTuntutanCukaiHakmilik;
import etanah.model.DasarTuntutanNotis;
import etanah.model.KodNotis;
import etanah.model.Pengguna;
import etanah.view.etanahActionBeanContext;
import java.util.ArrayList;
import java.util.List;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import org.apache.log4j.Logger;
import org.hibernate.Session;

/**
 *
 * @author mansur
 */
@UrlBinding("/hasil/warta")
public class WartaActionBean extends AbleActionBean {
    Logger LOG = Logger.getLogger(WartaActionBean.class);

    boolean flagSearch = false;
    boolean flagWarta = false;
    String rekod = "display:none";
    static String url = "hasil/warta.jsp";
    String idDasar;
    String jenisWarta;
    Pengguna peng;
    List<KodNotis> kodNotisWarta;
    List<DasarTuntutanCukaiHakmilik> senaraiDTCH;
    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;
    @Inject
    NotisPeringatan6AManager manager;
    @Inject
    DasarTuntutanCukaiHakmilikDAO dasarHakmilikDAO;
    @Inject
    DasarTuntutanNotisDAO dasarNotisDAO;
    @Inject
    KodNotisDAO kodNotisDAO;

    @DefaultHandler
    public Resolution showForm() {
        kodNotisWarta = manager.doSenaraiKodNotisWarta();
        return new JSP(url);
    }

    public Resolution search() {
        peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        LOG.info("(search) idDasar :" + idDasar);
        LOG.info("(search) jenisWarta :" + jenisWarta);
        senaraiDTCH = new ArrayList<DasarTuntutanCukaiHakmilik>();
        boolean haveFlagWarta = false;
        if (jenisWarta.equals("NG")) { // group warta for Notis Gantian
            try{
                String[] name = {"dasarTuntutanCukai.idDasar"};
                Object[] value= {idDasar};
                List<DasarTuntutanCukaiHakmilik> listDTCH = dasarHakmilikDAO.findByEqualCriterias(name, value, null);
                LOG.debug("(search) listDTCH.size :"+listDTCH.size());
                for (DasarTuntutanCukaiHakmilik dtch : listDTCH) {
                    LOG.info("(search) dtch :"+dtch.getIdDasarHakmilik());
                    haveFlagWarta = false;
                    for (DasarTuntutanNotis dtn : dtch.getSenaraiNotis()) {
                        if(dtn.getNotis() == null)
                            continue;
                        if(dtn.getNotis().getKod().equals("NG") && dtn.getCawangan().getKod().equals(peng.getKodCawangan().getKod()))
                            haveFlagWarta = true;
                    }
                    if(haveFlagWarta)
                        senaraiDTCH.add(dtch);
                }
            }catch(Exception ex){
                LOG.error("(search) ex NG :"+ex);
                ex.printStackTrace(); // for development only
            }
            LOG.debug("(search) NG-senaraiDTCH.size :"+senaraiDTCH.size());
        }
        if (jenisWarta.equals("N8A")) { // group warta for Notis 
            try{
                String[] name = {"dasarTuntutanCukai.idDasar"};
                Object[] value= {idDasar};
                List<DasarTuntutanCukaiHakmilik> listDTCH = dasarHakmilikDAO.findByEqualCriterias(name, value, null);
                LOG.debug("(search) listDTCH.size :"+listDTCH.size());
                for (DasarTuntutanCukaiHakmilik dtch : listDTCH) {
                    LOG.info("(search) dtch :"+dtch.getIdDasarHakmilik());
                    haveFlagWarta = false;
                    for (DasarTuntutanNotis dtn : dtch.getSenaraiNotis()) {
                        if(dtn.getNotis() == null)
                            continue;
                        if(dtn.getNotis().getKod().equals("N8A") && dtn.getCawangan().getKod().equals(peng.getKodCawangan().getKod()))
                            haveFlagWarta = true;
                    }
                    if(haveFlagWarta)
                        senaraiDTCH.add(dtch);
                }
            }catch(Exception ex){
                LOG.error("(search) ex N8A :"+ex);
                ex.printStackTrace(); // for development only
            }
            LOG.debug("(search) N8A-senaraiDTCH.size :"+senaraiDTCH.size());
        }
        if(senaraiDTCH.size() != 0)
            rekod = "";
        flagSearch = true;
        kodNotisWarta = manager.doSenaraiKodNotisWarta();
        return new JSP(url);
    }

    public Resolution simpan(){
        peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        search();
//        Date now = new Date();
//        InfoAudit ia = new InfoAudit();
        List<DasarTuntutanNotis> senaraiNotisWarta = new ArrayList<DasarTuntutanNotis>();
        List<DasarTuntutanCukaiHakmilik> listdtch = new ArrayList<DasarTuntutanCukaiHakmilik>();
        LOG.info("(Simpan) senaraiDTCH.size :"+senaraiDTCH.size());
        
        String[] param = getContext().getRequest().getParameterValues("idDasarHM");
        // for checked id Dasar hakmilik
        for (String idDasarHM : param) {
            LOG.info("(simpan) idDasarHM :"+idDasarHM);
            long idDHM = Long.parseLong(idDasarHM);
            for (DasarTuntutanCukaiHakmilik dhm : senaraiDTCH) {
                if(dhm.getIdDasarHakmilik() == idDHM){
                    listdtch.add(dhm);
                }
            }
//            boolean warta8A = false;
//            boolean warta8AKump = false;

            String [] name2 = {"hakmilik.idDasarHakmilik"};
            Object [] value2 = {idDHM};
            List<DasarTuntutanNotis> listDasarNotis = dasarNotisDAO.findByEqualCriterias(name2, value2, null);
            if(jenisWarta.equals("NG")){ // for Notis Gantian
                for (DasarTuntutanNotis dtnWarta : listDasarNotis) {
                    if(dtnWarta.getNotis().getKod().equals(jenisWarta) && dtnWarta.getKumpulanWarta().equals("T")){
                        dtnWarta.setKumpulanWarta("Y");
                        senaraiNotisWarta.add(dtnWarta);
                    }
                }
            }
            if(jenisWarta.equals("N8A")){ // for Notis 8A
                for (DasarTuntutanNotis dtn8A : listDasarNotis) {
                        if(dtn8A.getNotis().getKod().equals(jenisWarta) && dtn8A.getKumpulanWarta().equals("T")){
                            dtn8A.setKumpulanWarta("Y");
                            senaraiNotisWarta.add(dtn8A);
                        }
                    }
//                for (DasarTuntutanNotis dtnWarta : listDasarNotis) {
//                    if(dtnWarta.getNotis().getKod().equals("WR")){
//                        warta8A = true; // terdapat kod WR dlm list dtn
//                        if(dtnWarta.getNotis().getKod().equals("WR") && dtnWarta.getKumpulanWarta() == 'Y'){
//                            warta8AKump = true; // sudah dibuat kumpulan
//                        }
//                    }
//                }
//
//                if(warta8A && !warta8AKump){ // WR ada, WR kump tiada
//                    for (DasarTuntutanNotis dtnWR : listDasarNotis) {
//                        ia = dtnWR.getInfoAudit();
//                        ia.setDiKemaskiniOleh(peng);
//                        ia.setTarikhKemaskini(now);
//                        dtnWR.setInfoAudit(ia);
//                        dtnWR.setKumpulanWarta('Y');
//                        senaraiNotisWarta.add(dtnWR);
//                    }
//                }
//
//                if(!warta8A){ // WR tiada
//                    KodNotis kn = kodNotisDAO.findById("WR");
//                    for (DasarTuntutanNotis dtnRefer : listDasarNotis) {
//                        if(dtnRefer.getNotis().getKod().equals(jenisWarta)){
//                            ia.setDimasukOleh(peng);
//                            ia.setTarikhMasuk(now);
//                            ia.setDiKemaskiniOleh(peng);
//                            ia.setTarikhKemaskini(now);
//                            DasarTuntutanNotis dtnNew = new DasarTuntutanNotis();
//                            dtnNew.setInfoAudit(ia);
//                            dtnNew.setCawangan(dtnRefer.getCawangan());
//                            dtnNew.setHakmilik(dtnRefer.getHakmilik());
//                            dtnNew.setKumpulanWarta('Y');
//                            dtnNew.setTarikhNotis(now);
//                            manager.saveNotis(dtnNew);
//                        }
//                    }
//                }
            }
        }

        //for uncheck hakmilik
        LOG.info("(simpan) listdtch.size :"+listdtch.size());
        senaraiDTCH.removeAll(listdtch);
        for (DasarTuntutanCukaiHakmilik dtchUncheck : senaraiDTCH) {
            if(jenisWarta.equals("NG")){
                for (DasarTuntutanNotis notisNG : dtchUncheck.getSenaraiNotis()) {
                    if(notisNG.getNotis().getKod().equals(jenisWarta) && notisNG.getKumpulanWarta().equals("Y")){
                        notisNG.setKumpulanWarta("T");
                        senaraiNotisWarta.add(notisNG);
                    }
                }
            }
            if(jenisWarta.equals("N8A")){
                for (DasarTuntutanNotis notisNG : dtchUncheck.getSenaraiNotis()) {
                    if(notisNG.getNotis().getKod().equals(jenisWarta) && notisNG.getKumpulanWarta().equals("Y")){
                        notisNG.setKumpulanWarta("T");
                        senaraiNotisWarta.add(notisNG);
                    }
                }
            }
        }

        if(manager.updateListDtn(senaraiNotisWarta, peng).equals("success")){
            addSimpleMessage("Berjaya. Sila jana dokumen warta.");
        }else{
            addSimpleError("TIDAK berjaya.");
        }

        flagSearch = true;
        kodNotisWarta = manager.doSenaraiKodNotisWarta();
        flagWarta = true;
        return search();
    }

    public Resolution reloadBaseWarta(){
        LOG.debug("(reloadBaseWarta) idDasar :"+idDasar);
        return showForm();
    }

    public String getIdDasar() {
        return idDasar;
    }

    public void setIdDasar(String idDasar) {
        this.idDasar = idDasar;
    }

    public String getJenisWarta() {
        return jenisWarta;
    }

    public void setJenisWarta(String jenisWarta) {
        this.jenisWarta = jenisWarta;
    }

    public List<KodNotis> getKodNotisWarta() {
        return kodNotisWarta;
    }

    public void setKodNotisWarta(List<KodNotis> kodNotisWarta) {
        this.kodNotisWarta = kodNotisWarta;
    }

    public boolean isFlagSearch() {
        return flagSearch;
    }

    public void setFlagSearch(boolean flagSearch) {
        this.flagSearch = flagSearch;
    }

    public boolean isFlagWarta() {
        return flagWarta;
    }

    public void setFlagWarta(boolean flagWarta) {
        this.flagWarta = flagWarta;
    }

    public List<DasarTuntutanCukaiHakmilik> getSenaraiDTCH() {
        return senaraiDTCH;
    }

    public void setSenaraiDTCH(List<DasarTuntutanCukaiHakmilik> senaraiDTCH) {
        this.senaraiDTCH = senaraiDTCH;
    }

    public String getRekod() {
        return rekod;
    }

    public void setRekod(String rekod) {
        this.rekod = rekod;
    }
}
