/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package etanah.view.stripes.pengambilan;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.dao.DokumenDAO;
import etanah.dao.HakmilikDAO;
import etanah.dao.KodCaraPenghantaranDAO;
import etanah.dao.KodNotisDAO;
import etanah.dao.KodStatusTerimaDAO;
import etanah.dao.PermohonanDAO;
import etanah.model.Dokumen;
import etanah.model.Hakmilik;
import etanah.model.HakmilikPermohonan;
import etanah.model.InfoAudit;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.model.PermohonanPihak;
import etanah.model.Notis;
import etanah.model.KandunganFolder;
import etanah.model.KodCawangan;
import etanah.model.PenghantarNotis;
import etanah.dao.KodDokumenDAO;
import etanah.dao.KodKlasifikasiDAO;
import etanah.dao.NotisDAO;
import etanah.dao.PenghantarNotisDAO;
import etanah.model.FasaPermohonan;
import etanah.model.HakmilikPihakBerkepentingan;
import etanah.model.Pemohon;
import etanah.service.PermohonanSupayaBantahanService;
import etanah.service.common.NotisPenerimaanService;
import etanah.service.common.LelongService;
import etanah.view.etanahActionBeanContext;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.io.File;
import etanah.util.FileUtil;
import etanah.util.DateUtil;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.RedirectResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;
import org.apache.log4j.Logger;
import net.sourceforge.stripes.action.FileBean;

/**
 *
 * @author Rajesh
 */
@UrlBinding("/pengambilan/penerimaan_notis_borang")
public class PenerimaanNotisBorangActionBean extends AbleActionBean {

    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    HakmilikDAO hakmilikDAO;
    @Inject
    DokumenDAO dokumenDAO;
    @Inject
    KodNotisDAO kodNotisDAO;
    @Inject
    KodStatusTerimaDAO KodStatusTerimaDAO;
    @Inject
    KodDokumenDAO kodDokumenDAO;
    @Inject
    KodKlasifikasiDAO kodKlasDAO;
    @Inject
    NotisDAO notisDAO;
    @Inject
    PenghantarNotisDAO penghantarNotisDAO;
    @Inject
    KodCaraPenghantaranDAO kodCaraPenghantaranDAO;
    @Inject
    NotisPenerimaanService notisPenerimaanService;
    @Inject
    PermohonanSupayaBantahanService permohonanSupayaBantahanService;
    @Inject
    LelongService lelongService;
    @Inject
    etanah.Configuration conf;
    private Hakmilik hakmilik;
    private List<Notis> listNotisASB = new ArrayList<Notis>();
    private List<Notis> listNotisPemohonASB = new ArrayList<Notis>();
    private List<Notis> listNotisPB  = new ArrayList<Notis>();
    private List<Notis> listNotisNBQ = new ArrayList<Notis>();
    private List<Integer> namaPengahantar = new ArrayList<Integer>();
    private List<String> kodStatusTerima = new ArrayList<String>();
    private List<String> kodPenghantaran = new ArrayList<String>();
    private List<String> catatanPenerimaan = new ArrayList<String>();
    private List<String> tarikhHantar = new ArrayList<String>();
    private List<String> tarikhTerima = new ArrayList<String>();
    private List<String> idDokumenList = new ArrayList<String>();

    private List<Integer> namaPengahantarP = new ArrayList<Integer>();
    private List<String> kodStatusTerimaP = new ArrayList<String>();
    private List<String> kodPenghantaranP = new ArrayList<String>();
    private List<String> catatanPenerimaanP = new ArrayList<String>();
    private List<String> tarikhHantarP = new ArrayList<String>();
    private List<String> tarikhTerimaP = new ArrayList<String>();
    private List<String> idDokumenListP = new ArrayList<String>();
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

    private Notis notis;
    private Permohonan permohonan;
    private FasaPermohonan fasaPermohonan;
    private PermohonanPihak permohonanPihak;
    private String idPermohonan;
    private String idHakmilik;
    private String idPihak;
    private String idDokumen;
    private List<HakmilikPermohonan> hakmilikPermohonanList;
    private List<KandunganFolder> listKandunganFolder;
    private List<Dokumen> listDokumen;
    private String idNotis;
    FileBean fileToBeUploaded;
    private long idDokumen2;
    private PenghantarNotis penghantarNotis;
    private boolean show = true;
    private boolean showPP = false;
    private String showHP = "false";
    private String isPemohonPihak = "false";

    private static final Logger LOG = Logger.getLogger(PenerimaanNotisBorangActionBean.class);

    //Notis for only Hakmilik Pihak
    @DefaultHandler
    public Resolution showForm() {
        showHP = "true";
        Pengguna pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        if(idPermohonan != null) {
            permohonan = permohonanDAO.findById(idPermohonan);
            if(permohonan != null) {
                permohonanSupayaBantahanService.simpanPermohonanPihak(permohonan, pengguna);
            }

            int count = 0;
            for(HakmilikPermohonan hp : permohonan.getSenaraiHakmilik()){
                count += hp.getHakmilik().getSenaraiPihakBerkepentingan().size();
            }

            fasaPermohonan = notisPenerimaanService.getFasaPermohonan(idPermohonan, "15DrafSuratBayar");
            if(fasaPermohonan == null)
                fasaPermohonan = notisPenerimaanService.getFasaPermohonan(idPermohonan, "12DrafSuratBorangQ");

            listNotisASB = new ArrayList<Notis>();
            listNotisPB  = new ArrayList<Notis>();
            listNotisNBQ = new ArrayList<Notis>();

            if(fasaPermohonan != null){
                if(fasaPermohonan.getIdAliran().equalsIgnoreCase("15DrafSuratBayar")){
                    for(HakmilikPermohonan hp : permohonan.getSenaraiHakmilik()){
                        for(HakmilikPihakBerkepentingan hakmilikPihak : hp.getHakmilik().getSenaraiPihakBerkepentingan()){
                            permohonanPihak = notisPenerimaanService.getSenaraiPmohonPihakByIdHakmilikIdPihak(idPermohonan, hp.getHakmilik().getIdHakmilik(), hakmilikPihak.getPihak().getIdPihak());
                            Notis notisASB = notisPenerimaanService.getNotisByidMPkodNotis(idPermohonan, permohonanPihak.getIdPermohonanPihak(), "ASB");
                            if(notisASB != null){
                                listNotisASB.add(notisASB);
                            }
                        }
                    }
                    if (listNotisASB.size() != count) {
                        LOG.info("Belum ade lg...");
                        simpanNotisASB();
                    }
                }else if(fasaPermohonan.getIdAliran().equalsIgnoreCase("12DrafSuratBorangQ")){
                    for(HakmilikPermohonan hp : permohonan.getSenaraiHakmilik()){
                        for(HakmilikPihakBerkepentingan hakmilikPihak : hp.getHakmilik().getSenaraiPihakBerkepentingan()){
                            permohonanPihak = notisPenerimaanService.getSenaraiPmohonPihakByIdHakmilikIdPihak(idPermohonan, hp.getHakmilik().getIdHakmilik(), hakmilikPihak.getPihak().getIdPihak());
                            Notis notisPB = notisPenerimaanService.getNotisByidMPkodNotis(idPermohonan, permohonanPihak.getIdPermohonanPihak(), "PB");
                            Notis notisNBQ = notisPenerimaanService.getNotisByidMPkodNotis(idPermohonan, permohonanPihak.getIdPermohonanPihak(), "NBQ");
                            if(notisPB != null){
                                listNotisPB.add(notisPB);
                            }
                            if(notisNBQ != null){
                                listNotisNBQ.add(notisNBQ);
                            }
                        }
                    }
                    if (listNotisPB.size() != count && listNotisNBQ.size() != count) {
                        LOG.info("Belum ade lg...");
                        simpanNotis();
                    }
                }
            }
        }
        return new JSP("pengambilan/Penerimaan_Notis_Borang.jsp").addParameter("tab", "true");
    }

    //Notis for Hakmilik Pihak and Pemohon
    public Resolution showFormPP() {
        showHP = "true";
        Pengguna pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        if(idPermohonan != null) {
            permohonan = permohonanDAO.findById(idPermohonan);
            if(permohonan != null) {
                permohonanSupayaBantahanService.simpanPermohonanPihak(permohonan, pengguna);
            }

            int count = 0;
            for(HakmilikPermohonan hp : permohonan.getSenaraiHakmilik()){
                count += hp.getHakmilik().getSenaraiPihakBerkepentingan().size();
            }

            fasaPermohonan = notisPenerimaanService.getFasaPermohonan(idPermohonan, "24SediaSuratKeputusan");
            if(fasaPermohonan == null)
                fasaPermohonan = notisPenerimaanService.getFasaPermohonan(idPermohonan, "15DrafSuratBayar");
            if(fasaPermohonan == null)
                fasaPermohonan = notisPenerimaanService.getFasaPermohonan(idPermohonan, "12DrafSuratBorangQ");
            if(fasaPermohonan == null)
                fasaPermohonan = notisPenerimaanService.getFasaPermohonan(idPermohonan, "14SediaSurat");

            
            listNotisASB = new ArrayList<Notis>();
            listNotisPB  = new ArrayList<Notis>();
            listNotisNBQ = new ArrayList<Notis>();

            if(fasaPermohonan != null){
                if(fasaPermohonan.getIdAliran().equalsIgnoreCase("24SediaSuratKeputusan")){
                    showPP = true;
                    for(HakmilikPermohonan hp : permohonan.getSenaraiHakmilik()){
                        for(HakmilikPihakBerkepentingan hakmilikPihak : hp.getHakmilik().getSenaraiPihakBerkepentingan()){
                            permohonanPihak = notisPenerimaanService.getSenaraiPmohonPihakByIdHakmilikIdPihak(idPermohonan, hp.getHakmilik().getIdHakmilik(), hakmilikPihak.getPihak().getIdPihak());
                            Notis notisASB = notisPenerimaanService.getNotisByidMPkodNotis(idPermohonan, permohonanPihak.getIdPermohonanPihak(), "SAC");
                            if(notisASB != null){
                                listNotisASB.add(notisASB);
                            }
                        }
                    }
                    if (listNotisASB.size() != count) {
                        LOG.info("Belum ade lg...");
                        simpanNotisASB();
                    }
                    pemohonNotis();

                }else if(fasaPermohonan.getIdAliran().equalsIgnoreCase("15DrafSuratBayar")){
                    showPP = true;
                    for(HakmilikPermohonan hp : permohonan.getSenaraiHakmilik()){
                        for(HakmilikPihakBerkepentingan hakmilikPihak : hp.getHakmilik().getSenaraiPihakBerkepentingan()){
                            permohonanPihak = notisPenerimaanService.getSenaraiPmohonPihakByIdHakmilikIdPihak(idPermohonan, hp.getHakmilik().getIdHakmilik(), hakmilikPihak.getPihak().getIdPihak());
                            Notis notisASB = notisPenerimaanService.getNotisByidMPkodNotis(idPermohonan, permohonanPihak.getIdPermohonanPihak(), "ASB");
                            if(notisASB != null){
                                listNotisASB.add(notisASB);
                            }
                        }
                    }
                    if (listNotisASB.size() != count) {
                        LOG.info("Belum ade lg...");
                        simpanNotisASB();
                    }
                    pemohonNotis();
                    
                }else if(fasaPermohonan.getIdAliran().equalsIgnoreCase("14SediaSurat")){
                    showPP = true;
                    for(HakmilikPermohonan hp : permohonan.getSenaraiHakmilik()){
                        for(HakmilikPihakBerkepentingan hakmilikPihak : hp.getHakmilik().getSenaraiPihakBerkepentingan()){
                            permohonanPihak = notisPenerimaanService.getSenaraiPmohonPihakByIdHakmilikIdPihak(idPermohonan, hp.getHakmilik().getIdHakmilik(), hakmilikPihak.getPihak().getIdPihak());
                            Notis notisASB = notisPenerimaanService.getNotisByidMPkodNotis(idPermohonan, permohonanPihak.getIdPermohonanPihak(), "PBG");
                            if(notisASB != null){
                                listNotisASB.add(notisASB);
                            }
                        }
                    }
                    if (listNotisASB.size() != count) {
                        LOG.info("Belum ade lg...");
                        simpanNotisASB();
                    }
                    pemohonNotis();

                }else if(fasaPermohonan.getIdAliran().equalsIgnoreCase("12DrafSuratBorangQ")){
                    for(HakmilikPermohonan hp : permohonan.getSenaraiHakmilik()){
                        for(HakmilikPihakBerkepentingan hakmilikPihak : hp.getHakmilik().getSenaraiPihakBerkepentingan()){
                            permohonanPihak = notisPenerimaanService.getSenaraiPmohonPihakByIdHakmilikIdPihak(idPermohonan, hp.getHakmilik().getIdHakmilik(), hakmilikPihak.getPihak().getIdPihak());
                            Notis notisPB = notisPenerimaanService.getNotisByidMPkodNotis(idPermohonan, permohonanPihak.getIdPermohonanPihak(), "PB");
                            Notis notisNBQ = notisPenerimaanService.getNotisByidMPkodNotis(idPermohonan, permohonanPihak.getIdPermohonanPihak(), "NBQ");
                            if(notisPB != null){
                                listNotisPB.add(notisPB);
                            }
                            if(notisNBQ != null){
                                listNotisNBQ.add(notisNBQ);
                            }
                        }
                    }
                    if (listNotisPB.size() != count && listNotisNBQ.size() != count) {
                        LOG.info("Belum ade lg...");
                        simpanNotis();
                    }
                }
            }
        }
        return new JSP("pengambilan/Penerimaan_Notis_Borang.jsp").addParameter("tab", "true");
    }

    //Notis for only Pemohon
    public Resolution showFormPemohon() {
        Pengguna pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        if(idPermohonan != null) {
            permohonan = permohonanDAO.findById(idPermohonan);
            if(permohonan != null) {
                permohonanSupayaBantahanService.simpanPermohonanPihak(permohonan, pengguna);
            }

            fasaPermohonan = notisPenerimaanService.getFasaPermohonan(idPermohonan, "15DrafSuratBayar");

            if(fasaPermohonan != null){
                    showPP = true;
                    pemohonNotis();
            }
        }
        return new JSP("pengambilan/Penerimaan_Notis_Borang.jsp").addParameter("tab", "true");
    }

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");

        if (idPermohonan != null) {
            permohonan = permohonanDAO.findById(idPermohonan);
            hakmilikPermohonanList = permohonan.getSenaraiHakmilik();
        }
    }
    
    public Resolution hakmilikDetails() {
        showHP = "true";
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        hakmilik = hakmilikDAO.findById(idHakmilik);

        int count = hakmilik.getSenaraiPihakBerkepentingan().size();

        fasaPermohonan = notisPenerimaanService.getFasaPermohonan(idPermohonan, "24SediaSuratKeputusan");
        if(fasaPermohonan == null)
            fasaPermohonan = notisPenerimaanService.getFasaPermohonan(idPermohonan, "15DrafSuratBayar");
        if(fasaPermohonan == null)
            fasaPermohonan = notisPenerimaanService.getFasaPermohonan(idPermohonan, "12DrafSuratBorangQ");
        if(fasaPermohonan == null)
                fasaPermohonan = notisPenerimaanService.getFasaPermohonan(idPermohonan, "14SediaSurat");

        listNotisASB = new ArrayList<Notis>();
        listNotisPB  = new ArrayList<Notis>();
        listNotisNBQ = new ArrayList<Notis>();

        if(fasaPermohonan != null){
            if(fasaPermohonan.getIdAliran().equalsIgnoreCase("24SediaSuratKeputusan")){
                for(HakmilikPihakBerkepentingan hakmilikPihak : hakmilik.getSenaraiPihakBerkepentingan()){
                    permohonanPihak = notisPenerimaanService.getSenaraiPmohonPihakByIdHakmilikIdPihak(idPermohonan, hakmilik.getIdHakmilik(), hakmilikPihak.getPihak().getIdPihak());
                    Notis notisASB = notisPenerimaanService.getNotisByidMPkodNotis(idPermohonan, permohonanPihak.getIdPermohonanPihak(), "SAC");
                    if(notisASB != null){
                        listNotisASB.add(notisASB);
                    }
                }

            }else if(fasaPermohonan.getIdAliran().equalsIgnoreCase("15DrafSuratBayar")){
                for(HakmilikPihakBerkepentingan hakmilikPihak : hakmilik.getSenaraiPihakBerkepentingan()){
                    permohonanPihak = notisPenerimaanService.getSenaraiPmohonPihakByIdHakmilikIdPihak(idPermohonan, hakmilik.getIdHakmilik(), hakmilikPihak.getPihak().getIdPihak());
                    Notis notisASB = notisPenerimaanService.getNotisByidMPkodNotis(idPermohonan, permohonanPihak.getIdPermohonanPihak(), "ASB");
                    if(notisASB != null){
                        listNotisASB.add(notisASB);
                    }
                }
                
            }else if(fasaPermohonan.getIdAliran().equalsIgnoreCase("14SediaSurat")){
                for(HakmilikPihakBerkepentingan hakmilikPihak : hakmilik.getSenaraiPihakBerkepentingan()){
                    permohonanPihak = notisPenerimaanService.getSenaraiPmohonPihakByIdHakmilikIdPihak(idPermohonan, hakmilik.getIdHakmilik(), hakmilikPihak.getPihak().getIdPihak());
                    Notis notisASB = notisPenerimaanService.getNotisByidMPkodNotis(idPermohonan, permohonanPihak.getIdPermohonanPihak(), "PBG");
                    if(notisASB != null){
                        listNotisASB.add(notisASB);
                    }
                }
            }else if(fasaPermohonan.getIdAliran().equalsIgnoreCase("12DrafSuratBorangQ")){
                for(HakmilikPihakBerkepentingan hakmilikPihak : hakmilik.getSenaraiPihakBerkepentingan()){
                    permohonanPihak = notisPenerimaanService.getSenaraiPmohonPihakByIdHakmilikIdPihak(idPermohonan, hakmilik.getIdHakmilik(), hakmilikPihak.getPihak().getIdPihak());
                    Notis notisPB = notisPenerimaanService.getNotisByidMPkodNotis(idPermohonan, permohonanPihak.getIdPermohonanPihak(), "PB");
                    Notis notisNBQ = notisPenerimaanService.getNotisByidMPkodNotis(idPermohonan, permohonanPihak.getIdPermohonanPihak(), "NBQ");
                    if(notisPB != null){
                        listNotisPB.add(notisPB);
                    }
                    if(notisNBQ != null){
                        listNotisNBQ.add(notisNBQ);
                    }
                }
            }
        }

        if (listNotisASB.size() == count ) {
          for(int i=0;i < hakmilik.getSenaraiPihakBerkepentingan().size(); i++){
                permohonanPihak = notisPenerimaanService.getSenaraiPmohonPihakByIdHakmilikIdPihak(idPermohonan, idHakmilik, hakmilik.getSenaraiPihakBerkepentingan().get(i).getPihak().getIdPihak());
                Notis notisASB = null;
                if(fasaPermohonan.getIdAliran().equalsIgnoreCase("24SediaSuratKeputusan")){
                    notisASB = notisPenerimaanService.getNotisByidMPkodNotis(idPermohonan, permohonanPihak.getIdPermohonanPihak(), "SAC");
                }else if(fasaPermohonan.getIdAliran().equalsIgnoreCase("15DrafSuratBayar")){
                    notisASB = notisPenerimaanService.getNotisByidMPkodNotis(idPermohonan, permohonanPihak.getIdPermohonanPihak(), "ASB");
                }else if(fasaPermohonan.getIdAliran().equalsIgnoreCase("14SediaSurat")){
                    notisASB = notisPenerimaanService.getNotisByidMPkodNotis(idPermohonan, permohonanPihak.getIdPermohonanPihak(), "PBG");
                }
                if(notisASB != null){
                    if(notisASB.getPenghantarNotis() != null)
                        namaPengahantar.add(notisASB.getPenghantarNotis().getIdPenghantarNotis());
                    if(notisASB.getKodStatusTerima() != null)
                        kodStatusTerima.add(notisASB.getKodStatusTerima().getKod());
                    if(notisASB.getKodCaraPenghantaran() != null)
                        kodPenghantaran.add(notisASB.getKodCaraPenghantaran().getKod());
                    catatanPenerimaan.add(notisASB.getCatatanPenerimaan());
                    if(notisASB.getTarikhHantar()!= null)
                        tarikhHantar.add(sdf.format(notisASB.getTarikhHantar()));
                    if(notisASB.getTarikhTerima()!= null)
                        tarikhTerima.add(sdf.format(notisASB.getTarikhTerima()));
                    if(notisASB.getBuktiPenerimaan() == null){
                        idDokumenList.add("");
                    }else{
                        idDokumenList.add(String.valueOf(notisASB.getBuktiPenerimaan().getIdDokumen()));
                    }
                }
            }
        }else if (listNotisPB.size() == count && listNotisNBQ.size() == count) {
            for(int i=0;i < hakmilik.getSenaraiPihakBerkepentingan().size(); i++){
                permohonanPihak = notisPenerimaanService.getSenaraiPmohonPihakByIdHakmilikIdPihak(idPermohonan, idHakmilik, hakmilik.getSenaraiPihakBerkepentingan().get(i).getPihak().getIdPihak());
                Notis notisPB = notisPenerimaanService.getNotisByidMPkodNotis(idPermohonan, permohonanPihak.getIdPermohonanPihak(), "PB");
                if(notisPB != null){
                    if(notisPB.getPenghantarNotis() != null)
                        namaPengahantar.add(notisPB.getPenghantarNotis().getIdPenghantarNotis());
                    if(notisPB.getKodStatusTerima() != null)
                        kodStatusTerima.add(notisPB.getKodStatusTerima().getKod());
                    if(notisPB.getKodCaraPenghantaran() != null)
                        kodPenghantaran.add(notisPB.getKodCaraPenghantaran().getKod());
                    catatanPenerimaan.add(notisPB.getCatatanPenerimaan());
                    if(notisPB.getTarikhHantar()!= null)
                        tarikhHantar.add(sdf.format(notisPB.getTarikhHantar()));
                    if(notisPB.getTarikhTerima()!= null)
                        tarikhTerima.add(sdf.format(notisPB.getTarikhTerima()));
                    if(notisPB.getBuktiPenerimaan() == null){
                        idDokumenList.add("");
                    }else{
                        idDokumenList.add(String.valueOf(notisPB.getBuktiPenerimaan().getIdDokumen()));
                    }
                }
            }
        }else{
            show = false;
            addSimpleError("Dokumen Belum Dijana.Sila Jana Dokumen Terlebih Dahulu..");
        }
        String PP = (String)getContext().getRequest().getParameter("showPP");
        if(PP!= null && PP.equalsIgnoreCase("true")){
            showPP = true;
            pemohonNotis();
        }
        return new JSP("pengambilan/Penerimaan_Notis_Borang.jsp").addParameter("tab", "true");
    }

    public void pemohonNotis(){
        listNotisPemohonASB = new ArrayList<Notis>();

        int count1 = 0;
        if(permohonan.getSenaraiPemohon() != null){
            count1 = permohonan.getSenaraiPemohon().size();
        }

        for(Pemohon pemohon : permohonan.getSenaraiPemohon()){
            permohonanPihak = notisPenerimaanService.getMohonPihakByIdMohonIdPihak(idPermohonan, pemohon.getPihak().getIdPihak());
            if(permohonanPihak != null){
                Notis notisPemohonASB = null;
                if(fasaPermohonan.getIdAliran().equalsIgnoreCase("24SediaSuratKeputusan")){
                    notisPemohonASB = notisPenerimaanService.getNotisByidMPkodNotis(idPermohonan, permohonanPihak.getIdPermohonanPihak(), "SAC");
                }else if(fasaPermohonan.getIdAliran().equalsIgnoreCase("15DrafSuratBayar")){
                    notisPemohonASB = notisPenerimaanService.getNotisByidMPkodNotis(idPermohonan, permohonanPihak.getIdPermohonanPihak(), "ASB");
                }else if(fasaPermohonan.getIdAliran().equalsIgnoreCase("14SediaSurat")){
                    notisPemohonASB = notisPenerimaanService.getNotisByidMPkodNotis(idPermohonan, permohonanPihak.getIdPermohonanPihak(), "PBG");
                }
                if(notisPemohonASB != null){
                    listNotisPemohonASB.add(notisPemohonASB);
                }
            }
        }
        if (listNotisPemohonASB.size() != count1) {
            LOG.info("Belum ade lg...");
            simpanNotisPemohonASB();
            listNotisPemohonASB = new ArrayList<Notis>();
            for(Pemohon pemohon : permohonan.getSenaraiPemohon()){
                permohonanPihak = notisPenerimaanService.getMohonPihakByIdMohonIdPihak(idPermohonan, pemohon.getPihak().getIdPihak());
                if(permohonanPihak != null){
                    Notis notisPemohonASB = null;
                    if(fasaPermohonan.getIdAliran().equalsIgnoreCase("24SediaSuratKeputusan")){
                        notisPemohonASB = notisPenerimaanService.getNotisByidMPkodNotis(idPermohonan, permohonanPihak.getIdPermohonanPihak(), "SAC");
                    }else if(fasaPermohonan.getIdAliran().equalsIgnoreCase("15DrafSuratBayar")){
                        notisPemohonASB = notisPenerimaanService.getNotisByidMPkodNotis(idPermohonan, permohonanPihak.getIdPermohonanPihak(), "ASB");
                    }else if(fasaPermohonan.getIdAliran().equalsIgnoreCase("14SediaSurat")){
                        notisPemohonASB = notisPenerimaanService.getNotisByidMPkodNotis(idPermohonan, permohonanPihak.getIdPermohonanPihak(), "PBG");
                    }
                    if(notisPemohonASB != null){
                        listNotisPemohonASB.add(notisPemohonASB);
                    }
                }
            }
        }

        if (listNotisPemohonASB.size() == count1 ) {
            
            for(Pemohon pemohon : permohonan.getSenaraiPemohon()){
                permohonanPihak = notisPenerimaanService.getMohonPihakByIdMohonIdPihak(idPermohonan, pemohon.getPihak().getIdPihak());
                if(permohonanPihak != null){
                    Notis notisPemohonASB = null;
                    if(fasaPermohonan.getIdAliran().equalsIgnoreCase("24SediaSuratKeputusan")){
                        notisPemohonASB = notisPenerimaanService.getNotisByidMPkodNotis(idPermohonan, permohonanPihak.getIdPermohonanPihak(), "SAC");
                    }else if(fasaPermohonan.getIdAliran().equalsIgnoreCase("15DrafSuratBayar")){
                        notisPemohonASB = notisPenerimaanService.getNotisByidMPkodNotis(idPermohonan, permohonanPihak.getIdPermohonanPihak(), "ASB");
                    }else if(fasaPermohonan.getIdAliran().equalsIgnoreCase("14SediaSurat")){
                        notisPemohonASB = notisPenerimaanService.getNotisByidMPkodNotis(idPermohonan, permohonanPihak.getIdPermohonanPihak(), "PBG");
                    }
                    if(notisPemohonASB != null){
                        if(notisPemohonASB.getPenghantarNotis() != null)
                            namaPengahantarP.add(notisPemohonASB.getPenghantarNotis().getIdPenghantarNotis());
                        if(notisPemohonASB.getKodStatusTerima() != null)
                            kodStatusTerimaP.add(notisPemohonASB.getKodStatusTerima().getKod());
                        if(notisPemohonASB.getKodCaraPenghantaran() != null)
                            kodPenghantaranP.add(notisPemohonASB.getKodCaraPenghantaran().getKod());
                        catatanPenerimaanP.add(notisPemohonASB.getCatatanPenerimaan());
                        if(notisPemohonASB.getTarikhHantar()!= null)
                            tarikhHantarP.add(sdf.format(notisPemohonASB.getTarikhHantar()));
                        if(notisPemohonASB.getTarikhTerima()!= null)
                            tarikhTerimaP.add(sdf.format(notisPemohonASB.getTarikhTerima()));
                        if(notisPemohonASB.getBuktiPenerimaan() == null){
                            idDokumenListP.add("");
                        }else{
                            idDokumenListP.add(String.valueOf(notisPemohonASB.getBuktiPenerimaan().getIdDokumen()));
                        }
                    }
                }
            }
        }else{
            show = false;
            addSimpleError("Dokumen Belum Dijana.Sila Jana Dokumen Terlebih Dahulu..");
        }

    }

    public void simpanNotis() {

        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        Permohonan p = permohonanDAO.findById(idPermohonan);

        Dokumen dokumenPB = null;
        Dokumen dokumenQ = null;

        if(fasaPermohonan != null){
            dokumenPB = notisPenerimaanService.getDokumenBykod(idPermohonan, "PB");
            dokumenQ = notisPenerimaanService.getDokumenBykod(idPermohonan, "Q");
            if(dokumenPB != null && dokumenQ != null){

                InfoAudit info = new InfoAudit();
                info.setDimasukOleh(peng);
                info.setTarikhMasuk(new java.util.Date());
                for(HakmilikPermohonan hp : p.getSenaraiHakmilik()){
                    for(HakmilikPihakBerkepentingan hakmilikPihak : hp.getHakmilik().getSenaraiPihakBerkepentingan()){
                        permohonanPihak = notisPenerimaanService.getSenaraiPmohonPihakByIdHakmilikIdPihak(idPermohonan, hp.getHakmilik().getIdHakmilik(), hakmilikPihak.getPihak().getIdPihak());
                        Notis notisPB = notisPenerimaanService.getNotisByidMPkodNotis(idPermohonan, permohonanPihak.getIdPermohonanPihak(), "PB");
                        if(notisPB == null){
                            Notis notis1 = new Notis();
                            notis1.setInfoAudit(info);
                            notis1.setPermohonan(p);
                            notis1.setTarikhNotis(new java.util.Date());
                            notis1.setKodNotis(kodNotisDAO.findById("PB"));
                            notis1.setPihak(permohonanPihak);
                            notis1.setDokumenNotis(dokumenPB);
                            notis1.setJabatan(p.getKodUrusan().getJabatan());
                            notisPenerimaanService.saveOrUpdateNotis(notis1);
                        }
                        Notis notisNBQ = notisPenerimaanService.getNotisByidMPkodNotis(idPermohonan, permohonanPihak.getIdPermohonanPihak(), "NBQ");
                        if(notisNBQ == null){
                            Notis notis2 = new Notis();
                            notis2.setInfoAudit(info);
                            notis2.setPermohonan(p);
                            notis2.setTarikhNotis(new java.util.Date());
                            notis2.setKodNotis(kodNotisDAO.findById("NBQ"));
                            notis2.setPihak(permohonanPihak);
                            notis2.setDokumenNotis(dokumenQ);
                            notis2.setJabatan(p.getKodUrusan().getJabatan());
                            notisPenerimaanService.saveOrUpdateNotis(notis2);
                        }
                    }
                }
            }

        }
    }


    public void simpanNotisASB() {

        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        Permohonan p = permohonanDAO.findById(idPermohonan);

        Dokumen dokumenSEAP = null;
        Dokumen dokumenSPBG = null;
        Dokumen dokumenSBPC = null;

        if(fasaPermohonan != null){
            if(fasaPermohonan.getIdAliran().equalsIgnoreCase("24SediaSuratKeputusan")){
                dokumenSBPC = notisPenerimaanService.getDokumenBykod(idPermohonan, "SBPC");

                if(dokumenSBPC != null ){

                    InfoAudit info = new InfoAudit();
                    info.setDimasukOleh(peng);
                    info.setTarikhMasuk(new java.util.Date());
                    for(HakmilikPermohonan hp : p.getSenaraiHakmilik()){
                        for(HakmilikPihakBerkepentingan hakmilikPihak : hp.getHakmilik().getSenaraiPihakBerkepentingan()){
                            permohonanPihak = notisPenerimaanService.getSenaraiPmohonPihakByIdHakmilikIdPihak(idPermohonan, hp.getHakmilik().getIdHakmilik(), hakmilikPihak.getPihak().getIdPihak());
                            Notis notisASB = notisPenerimaanService.getNotisByidMPkodNotis(idPermohonan, permohonanPihak.getIdPermohonanPihak(), "SAC");
                            if(notisASB == null){
                                Notis notis1 = new Notis();
                                notis1.setInfoAudit(info);
                                notis1.setPermohonan(p);
                                notis1.setTarikhNotis(new java.util.Date());
                                notis1.setKodNotis(kodNotisDAO.findById("SAC"));
                                notis1.setPihak(permohonanPihak);
                                notis1.setDokumenNotis(dokumenSBPC);
                                notis1.setJabatan(p.getKodUrusan().getJabatan());
                                notisPenerimaanService.saveOrUpdateNotis(notis1);
                            }
                        }
                    }
                }
            }else if(fasaPermohonan.getIdAliran().equalsIgnoreCase("15DrafSuratBayar")){
                dokumenSEAP = notisPenerimaanService.getDokumenBykod(idPermohonan, "SEAP");

                if(dokumenSEAP != null ){

                    InfoAudit info = new InfoAudit();
                    info.setDimasukOleh(peng);
                    info.setTarikhMasuk(new java.util.Date());
                    for(HakmilikPermohonan hp : p.getSenaraiHakmilik()){
                        for(HakmilikPihakBerkepentingan hakmilikPihak : hp.getHakmilik().getSenaraiPihakBerkepentingan()){
                            permohonanPihak = notisPenerimaanService.getSenaraiPmohonPihakByIdHakmilikIdPihak(idPermohonan, hp.getHakmilik().getIdHakmilik(), hakmilikPihak.getPihak().getIdPihak());
                            Notis notisASB = notisPenerimaanService.getNotisByidMPkodNotis(idPermohonan, permohonanPihak.getIdPermohonanPihak(), "ASB");
                            if(notisASB == null){
                                Notis notis1 = new Notis();
                                notis1.setInfoAudit(info);
                                notis1.setPermohonan(p);
                                notis1.setTarikhNotis(new java.util.Date());
                                notis1.setKodNotis(kodNotisDAO.findById("ASB"));
                                notis1.setPihak(permohonanPihak);
                                notis1.setDokumenNotis(dokumenSEAP);
                                notis1.setJabatan(p.getKodUrusan().getJabatan());
                                notisPenerimaanService.saveOrUpdateNotis(notis1);
                            }
                        }
                    }
                }
            }else if(fasaPermohonan.getIdAliran().equalsIgnoreCase("14SediaSurat")){

                dokumenSPBG = notisPenerimaanService.getDokumenBykod(idPermohonan, "SPBG");

                if(dokumenSPBG != null ){

                    InfoAudit info = new InfoAudit();
                    info.setDimasukOleh(peng);
                    info.setTarikhMasuk(new java.util.Date());
                    for(HakmilikPermohonan hp : p.getSenaraiHakmilik()){
                        for(HakmilikPihakBerkepentingan hakmilikPihak : hp.getHakmilik().getSenaraiPihakBerkepentingan()){
                            permohonanPihak = notisPenerimaanService.getSenaraiPmohonPihakByIdHakmilikIdPihak(idPermohonan, hp.getHakmilik().getIdHakmilik(), hakmilikPihak.getPihak().getIdPihak());
                            Notis notisASB = notisPenerimaanService.getNotisByidMPkodNotis(idPermohonan, permohonanPihak.getIdPermohonanPihak(), "PBG");
                            if(notisASB == null){
                                Notis notis1 = new Notis();
                                notis1.setInfoAudit(info);
                                notis1.setPermohonan(p);
                                notis1.setTarikhNotis(new java.util.Date());
                                notis1.setKodNotis(kodNotisDAO.findById("PBG"));
                                notis1.setPihak(permohonanPihak);
                                notis1.setDokumenNotis(dokumenSPBG);
                                notis1.setJabatan(p.getKodUrusan().getJabatan());
                                notisPenerimaanService.saveOrUpdateNotis(notis1);
                            }
                        }
                    }
                }

            }
        }
    }

    public void simpanNotisPemohonASB() {

        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);

        Dokumen dokumenSEAP = null;
        Dokumen dokumenSPBG = null;
        Dokumen dokumenSBPC = null;

        if(fasaPermohonan != null){
            if(fasaPermohonan.getIdAliran().equalsIgnoreCase("24SediaSuratKeputusan")){
                dokumenSBPC = notisPenerimaanService.getDokumenBykod(idPermohonan, "SBPC");
                if(dokumenSBPC != null ){

                    InfoAudit info = new InfoAudit();
                    info.setDimasukOleh(peng);
                    info.setTarikhMasuk(new java.util.Date());
                    for(Pemohon pemohon : permohonan.getSenaraiPemohon()){
                        permohonanPihak = notisPenerimaanService.getMohonPihakByIdMohonIdPihak(idPermohonan, pemohon.getPihak().getIdPihak());
                        if(permohonanPihak == null){
                            permohonanPihak = notisPenerimaanService.simpanMohonPihak(permohonan, peng,pemohon.getPihak());
                        }
                        if(permohonanPihak != null){
                            Notis notisPemohonASB = notisPenerimaanService.getNotisByidMPkodNotis(idPermohonan, permohonanPihak.getIdPermohonanPihak(), "SAC");
                            if(notisPemohonASB == null){
                                Notis notis1 = new Notis();
                                notis1.setInfoAudit(info);
                                notis1.setPermohonan(permohonan);
                                notis1.setTarikhNotis(new java.util.Date());
                                notis1.setKodNotis(kodNotisDAO.findById("SAC"));
                                notis1.setPihak(permohonanPihak);
                                notis1.setDokumenNotis(dokumenSBPC);
                                notis1.setJabatan(permohonan.getKodUrusan().getJabatan());
                                notisPenerimaanService.saveOrUpdateNotis(notis1);
                            }
                        }
                    }
                }
            }else if(fasaPermohonan.getIdAliran().equalsIgnoreCase("15DrafSuratBayar")){
                dokumenSEAP = notisPenerimaanService.getDokumenBykod(idPermohonan, "SEAP");
                if(dokumenSEAP != null ){

                    InfoAudit info = new InfoAudit();
                    info.setDimasukOleh(peng);
                    info.setTarikhMasuk(new java.util.Date());
                    for(Pemohon pemohon : permohonan.getSenaraiPemohon()){
                        permohonanPihak = notisPenerimaanService.getMohonPihakByIdMohonIdPihak(idPermohonan, pemohon.getPihak().getIdPihak());
                        if(permohonanPihak == null){
                            permohonanPihak = notisPenerimaanService.simpanMohonPihak(permohonan, peng,pemohon.getPihak());
                        }
                        if(permohonanPihak != null){
                            Notis notisPemohonASB = notisPenerimaanService.getNotisByidMPkodNotis(idPermohonan, permohonanPihak.getIdPermohonanPihak(), "ASB");
                            if(notisPemohonASB == null){
                                Notis notis1 = new Notis();
                                notis1.setInfoAudit(info);
                                notis1.setPermohonan(permohonan);
                                notis1.setTarikhNotis(new java.util.Date());
                                notis1.setKodNotis(kodNotisDAO.findById("ASB"));
                                notis1.setPihak(permohonanPihak);
                                notis1.setDokumenNotis(dokumenSEAP);
                                notis1.setJabatan(permohonan.getKodUrusan().getJabatan());
                                notisPenerimaanService.saveOrUpdateNotis(notis1);
                            }
                        }
                    }
                }
            }else if(fasaPermohonan.getIdAliran().equalsIgnoreCase("14SediaSurat")){
                dokumenSPBG = notisPenerimaanService.getDokumenBykod(idPermohonan, "SPBG");
                if(dokumenSPBG != null ){

                    InfoAudit info = new InfoAudit();
                    info.setDimasukOleh(peng);
                    info.setTarikhMasuk(new java.util.Date());
                    for(Pemohon pemohon : permohonan.getSenaraiPemohon()){
                        permohonanPihak = notisPenerimaanService.getMohonPihakByIdMohonIdPihak(idPermohonan, pemohon.getPihak().getIdPihak());
                        if(permohonanPihak == null){
                            permohonanPihak = notisPenerimaanService.simpanMohonPihak(permohonan, peng,pemohon.getPihak());
                        }
                        if(permohonanPihak != null){
                            Notis notisPemohonASB = notisPenerimaanService.getNotisByidMPkodNotis(idPermohonan, permohonanPihak.getIdPermohonanPihak(), "PBG");
                            if(notisPemohonASB == null){
                                Notis notis1 = new Notis();
                                notis1.setInfoAudit(info);
                                notis1.setPermohonan(permohonan);
                                notis1.setTarikhNotis(new java.util.Date());
                                notis1.setKodNotis(kodNotisDAO.findById("PBG"));
                                notis1.setPihak(permohonanPihak);
                                notis1.setDokumenNotis(dokumenSPBG);
                                notis1.setJabatan(permohonan.getKodUrusan().getJabatan());
                                notisPenerimaanService.saveOrUpdateNotis(notis1);
                            }
                        }
                    }
                }

            }

        }
    }

    public Resolution popupPenghantarNotis() {
        idHakmilik = (String) getContext().getRequest().getParameter("idHakmilik");
        String PP = (String)getContext().getRequest().getParameter("showPP");
        if(PP != null && PP.equalsIgnoreCase("true"))
            showPP = true;
        String HP = (String)getContext().getRequest().getParameter("showHP");
        if(HP != null && HP.equalsIgnoreCase("true"))
            showHP = "true";

        penghantarNotis = new PenghantarNotis();
        return new JSP("pengambilan/PenghantarNotis_popup.jsp").addParameter("popup", "true");
    }

    public Resolution refreshpage() {
        rehydrate();
        return new RedirectResolution(PenerimaanNotisBorangActionBean.class, "locate");
    }

    public Resolution simpan() throws ParseException {
        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = permohonanDAO.findById(idPermohonan);
        idHakmilik = (String) getContext().getRequest().getParameter("idHakmilik");
        
        InfoAudit info;

        fasaPermohonan = notisPenerimaanService.getFasaPermohonan(idPermohonan, "24SediaSuratKeputusan");
        if(fasaPermohonan == null)
            fasaPermohonan = notisPenerimaanService.getFasaPermohonan(idPermohonan, "15DrafSuratBayar");
        if(fasaPermohonan == null)
            fasaPermohonan = notisPenerimaanService.getFasaPermohonan(idPermohonan, "12DrafSuratBorangQ");
        if(fasaPermohonan == null)
            fasaPermohonan = notisPenerimaanService.getFasaPermohonan(idPermohonan, "14SediaSurat");

        if(idHakmilik != null && !idHakmilik.isEmpty()){
            showHP = "true";
            hakmilik = hakmilikDAO.findById(idHakmilik);
            if(fasaPermohonan != null){
            if(fasaPermohonan.getIdAliran().equalsIgnoreCase("24SediaSuratKeputusan")){
                for(int i=0;i < hakmilik.getSenaraiPihakBerkepentingan().size(); i++){
                    permohonanPihak = notisPenerimaanService.getSenaraiPmohonPihakByIdHakmilikIdPihak(idPermohonan, idHakmilik, hakmilik.getSenaraiPihakBerkepentingan().get(i).getPihak().getIdPihak());
                    Notis notisASB = notisPenerimaanService.getNotisByidMPkodNotis(idPermohonan, permohonanPihak.getIdPermohonanPihak(), "SAC");
                    if(notisASB != null){
                        info = notisASB.getInfoAudit();
                        info.setDiKemaskiniOleh(peng);
                        info.setTarikhKemaskini(new java.util.Date());
                        notisASB.setInfoAudit(info);
                        notisASB.setPenghantarNotis(penghantarNotisDAO.findById(namaPengahantar.get(i)));
                        notisASB.setKodStatusTerima(KodStatusTerimaDAO.findById(kodStatusTerima.get(i)));
                        notisASB.setKodCaraPenghantaran(kodCaraPenghantaranDAO.findById(kodPenghantaran.get(i)));
                        notisASB.setTarikhHantar(sdf.parse(tarikhHantar.get(i)));
                        notisASB.setTarikhTerima(sdf.parse(tarikhTerima.get(i)));
                        notisASB.setCatatanPenerimaan(catatanPenerimaan.get(i));
                        notisPenerimaanService.saveOrUpdateNotis(notisASB);
                    }
                }

            }else if(fasaPermohonan.getIdAliran().equalsIgnoreCase("15DrafSuratBayar")){
                for(int i=0;i < hakmilik.getSenaraiPihakBerkepentingan().size(); i++){
                    permohonanPihak = notisPenerimaanService.getSenaraiPmohonPihakByIdHakmilikIdPihak(idPermohonan, idHakmilik, hakmilik.getSenaraiPihakBerkepentingan().get(i).getPihak().getIdPihak());
                    Notis notisASB = notisPenerimaanService.getNotisByidMPkodNotis(idPermohonan, permohonanPihak.getIdPermohonanPihak(), "ASB");
                    if(notisASB != null){
                        info = notisASB.getInfoAudit();
                        info.setDiKemaskiniOleh(peng);
                        info.setTarikhKemaskini(new java.util.Date());
                        notisASB.setInfoAudit(info);
                        notisASB.setPenghantarNotis(penghantarNotisDAO.findById(namaPengahantar.get(i)));
                        notisASB.setKodStatusTerima(KodStatusTerimaDAO.findById(kodStatusTerima.get(i)));
                        notisASB.setKodCaraPenghantaran(kodCaraPenghantaranDAO.findById(kodPenghantaran.get(i)));
                        notisASB.setTarikhHantar(sdf.parse(tarikhHantar.get(i)));
                        notisASB.setTarikhTerima(sdf.parse(tarikhTerima.get(i)));
                        notisASB.setCatatanPenerimaan(catatanPenerimaan.get(i));
                        notisPenerimaanService.saveOrUpdateNotis(notisASB);
                    }
                }
                
            }else if(fasaPermohonan.getIdAliran().equalsIgnoreCase("14SediaSurat")){
                for(int i=0;i < hakmilik.getSenaraiPihakBerkepentingan().size(); i++){
                    permohonanPihak = notisPenerimaanService.getSenaraiPmohonPihakByIdHakmilikIdPihak(idPermohonan, idHakmilik, hakmilik.getSenaraiPihakBerkepentingan().get(i).getPihak().getIdPihak());
                    Notis notisASB = notisPenerimaanService.getNotisByidMPkodNotis(idPermohonan, permohonanPihak.getIdPermohonanPihak(), "PBG");
                    if(notisASB != null){
                        info = notisASB.getInfoAudit();
                        info.setDiKemaskiniOleh(peng);
                        info.setTarikhKemaskini(new java.util.Date());
                        notisASB.setInfoAudit(info);
                        notisASB.setPenghantarNotis(penghantarNotisDAO.findById(namaPengahantar.get(i)));
                        notisASB.setKodStatusTerima(KodStatusTerimaDAO.findById(kodStatusTerima.get(i)));
                        notisASB.setKodCaraPenghantaran(kodCaraPenghantaranDAO.findById(kodPenghantaran.get(i)));
                        notisASB.setTarikhHantar(sdf.parse(tarikhHantar.get(i)));
                        notisASB.setTarikhTerima(sdf.parse(tarikhTerima.get(i)));
                        notisASB.setCatatanPenerimaan(catatanPenerimaan.get(i));
                        notisPenerimaanService.saveOrUpdateNotis(notisASB);
                    }
                }

            }else if(fasaPermohonan.getIdAliran().equalsIgnoreCase("12DrafSuratBorangQ")){
                for(int i=0;i < hakmilik.getSenaraiPihakBerkepentingan().size(); i++){
                    permohonanPihak = notisPenerimaanService.getSenaraiPmohonPihakByIdHakmilikIdPihak(idPermohonan, idHakmilik, hakmilik.getSenaraiPihakBerkepentingan().get(i).getPihak().getIdPihak());
                    Notis notisPB = notisPenerimaanService.getNotisByidMPkodNotis(idPermohonan, permohonanPihak.getIdPermohonanPihak(), "PB");
                    Notis notisNBQ = notisPenerimaanService.getNotisByidMPkodNotis(idPermohonan, permohonanPihak.getIdPermohonanPihak(), "NBQ");
                    if(notisPB != null){
                        info = notisPB.getInfoAudit();
                        info.setDiKemaskiniOleh(peng);
                        info.setTarikhKemaskini(new java.util.Date());
                        notisPB.setInfoAudit(info);
                        notisPB.setPenghantarNotis(penghantarNotisDAO.findById(namaPengahantar.get(i)));
                        notisPB.setKodStatusTerima(KodStatusTerimaDAO.findById(kodStatusTerima.get(i)));
                        notisPB.setKodCaraPenghantaran(kodCaraPenghantaranDAO.findById(kodPenghantaran.get(i)));
                        notisPB.setTarikhHantar(sdf.parse(tarikhHantar.get(i)));
                        notisPB.setTarikhTerima(sdf.parse(tarikhTerima.get(i)));
                        notisPB.setCatatanPenerimaan(catatanPenerimaan.get(i));
                        notisPenerimaanService.saveOrUpdateNotis(notisPB);
                    }
                    if(notisNBQ != null){
                        info = notisNBQ.getInfoAudit();
                        info.setDiKemaskiniOleh(peng);
                        info.setTarikhKemaskini(new java.util.Date());
                        notisNBQ.setInfoAudit(info);
                        notisNBQ.setPenghantarNotis(penghantarNotisDAO.findById(namaPengahantar.get(i)));
                        notisNBQ.setKodStatusTerima(KodStatusTerimaDAO.findById(kodStatusTerima.get(i)));
                        notisNBQ.setKodCaraPenghantaran(kodCaraPenghantaranDAO.findById(kodPenghantaran.get(i)));
                        notisNBQ.setTarikhHantar(sdf.parse(tarikhHantar.get(i)));
                        notisNBQ.setTarikhTerima(sdf.parse(tarikhTerima.get(i)));
                        notisNBQ.setCatatanPenerimaan(catatanPenerimaan.get(i));
                        notisPenerimaanService.saveOrUpdateNotis(notisNBQ);
                    }

                }
            }
        }
        }
        listNotisPemohonASB = new ArrayList<Notis>();
        String PP = (String)getContext().getRequest().getParameter("showPP");
        if(PP.equalsIgnoreCase("true")){
            showPP = true;
            for(int i=0;i < permohonan.getSenaraiPemohon().size(); i++){
                Pemohon pemohon = permohonan.getSenaraiPemohon().get(i);
                permohonanPihak = notisPenerimaanService.getMohonPihakByIdMohonIdPihak(idPermohonan, pemohon.getPihak().getIdPihak());
                if(permohonanPihak != null){
                    Notis notisPemohonASB = null;

                    if(fasaPermohonan.getIdAliran().equalsIgnoreCase("24SediaSuratKeputusan")){
                        notisPemohonASB = notisPenerimaanService.getNotisByidMPkodNotis(idPermohonan, permohonanPihak.getIdPermohonanPihak(), "SAC");
                    }else if(fasaPermohonan.getIdAliran().equalsIgnoreCase("15DrafSuratBayar")){
                        notisPemohonASB = notisPenerimaanService.getNotisByidMPkodNotis(idPermohonan, permohonanPihak.getIdPermohonanPihak(), "ASB");
                    }else if(fasaPermohonan.getIdAliran().equalsIgnoreCase("14SediaSurat")){
                        notisPemohonASB = notisPenerimaanService.getNotisByidMPkodNotis(idPermohonan, permohonanPihak.getIdPermohonanPihak(), "PBG");
                    }
                    if(notisPemohonASB != null){
                        info = notisPemohonASB.getInfoAudit();
                        info.setDiKemaskiniOleh(peng);
                        info.setTarikhKemaskini(new java.util.Date());
                        notisPemohonASB.setInfoAudit(info);
                        notisPemohonASB.setPenghantarNotis(penghantarNotisDAO.findById(namaPengahantarP.get(i)));
                        notisPemohonASB.setKodStatusTerima(KodStatusTerimaDAO.findById(kodStatusTerimaP.get(i)));
                        notisPemohonASB.setKodCaraPenghantaran(kodCaraPenghantaranDAO.findById(kodPenghantaranP.get(i)));
                        notisPemohonASB.setTarikhHantar(sdf.parse(tarikhHantarP.get(i)));
                        notisPemohonASB.setTarikhTerima(sdf.parse(tarikhTerimaP.get(i)));
                        notisPemohonASB.setCatatanPenerimaan(catatanPenerimaanP.get(i));
                        notisPenerimaanService.saveOrUpdateNotis(notisPemohonASB);
                        listNotisPemohonASB.add(notisPemohonASB);
                    }
                }
            }
        }

//        hakmilikDetails();

        return new JSP("pengambilan/Penerimaan_Notis_Borang.jsp").addParameter("tab", "true");
    }

    public Resolution processUploadDoc() {
        Pengguna p = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        idHakmilik = (String) getContext().getRequest().getParameter("idHakmilik");
        idPihak = (String) getContext().getRequest().getParameter("idPihak");
        
        hakmilik = hakmilikDAO.findById(idHakmilik);
        permohonanPihak = notisPenerimaanService.getSenaraiPmohonPihakByIdHakmilikIdPihak(idPermohonan, idHakmilik, Long.parseLong(idPihak));

        fasaPermohonan = notisPenerimaanService.getFasaPermohonan(idPermohonan, "24SediaSuratKeputusan");
        if(fasaPermohonan == null)
            fasaPermohonan = notisPenerimaanService.getFasaPermohonan(idPermohonan, "15DrafSuratBayar");
        if(fasaPermohonan == null)
            fasaPermohonan = notisPenerimaanService.getFasaPermohonan(idPermohonan, "12DrafSuratBorangQ");
        if(fasaPermohonan == null)
            fasaPermohonan = notisPenerimaanService.getFasaPermohonan(idPermohonan, "14SediaSurat");


        Notis notisASB = null;
        if(fasaPermohonan.getIdAliran().equalsIgnoreCase("24SediaSuratKeputusan")){
            notisASB = notisPenerimaanService.getNotisByidMPkodNotis(idPermohonan, permohonanPihak.getIdPermohonanPihak(), "SAC");
        }else if(fasaPermohonan.getIdAliran().equalsIgnoreCase("15DrafSuratBayar")){
            notisASB = notisPenerimaanService.getNotisByidMPkodNotis(idPermohonan, permohonanPihak.getIdPermohonanPihak(), "ASB");
        }else if(fasaPermohonan.getIdAliran().equalsIgnoreCase("14SediaSurat")){
            notisASB = notisPenerimaanService.getNotisByidMPkodNotis(idPermohonan, permohonanPihak.getIdPermohonanPihak(), "PBG");
        }
        Notis notisPB = notisPenerimaanService.getNotisByidMPkodNotis(idPermohonan, permohonanPihak.getIdPermohonanPihak(), "PB");
        Notis notisNBQ = notisPenerimaanService.getNotisByidMPkodNotis(idPermohonan, permohonanPihak.getIdPermohonanPihak(), "NBQ");


        InfoAudit ia = new InfoAudit();
        String DMS_BASE = conf.getProperty("document.path");
        String DMSPATH_WO_DMSBASE = null;
        String DMS_PATH = null;
        String fname = null;
        if(fasaPermohonan.getIdAliran().equalsIgnoreCase("24SediaSuratKeputusan") || fasaPermohonan.getIdAliran().equalsIgnoreCase("15DrafSuratBayar") || fasaPermohonan.getIdAliran().equalsIgnoreCase("14SediaSurat") || fasaPermohonan.getIdAliran().equalsIgnoreCase("24SediaSuratKeputusan")){
            fname = String.valueOf(notisASB.getIdNotis());
        }else if(fasaPermohonan.getIdAliran().equalsIgnoreCase("12DrafSuratBorangQ")){
            fname = String.valueOf(notisPB.getIdNotis());
        }
        LOG.info("idNotis : " + fname);
        DateUtil du = new DateUtil();
        try {
            if (p != null && fname != null && fileToBeUploaded != null) {
                String kodCawangan = p.getKodCawangan().getKod();
                DMSPATH_WO_DMSBASE = kodCawangan + File.separator + du.getYear() + File.separator
                        + du.getDateName(String.valueOf(du.getMonth() + 1))
                        + File.separator + du.getDay();
                DMS_PATH = DMS_BASE + (DMS_BASE.endsWith(File.separator) ? "" : File.separator) + DMSPATH_WO_DMSBASE;
                LOG.debug("DIR to created = " + DMS_PATH);
                FileUtil fileUtil = new FileUtil(DMS_PATH);
                String namaFail = fileUtil.saveFile(fname, fileToBeUploaded.getInputStream());
                LOG.info("namaFail :" + namaFail);
                Dokumen doc = new Dokumen();
                Notis notis = notisDAO.findById(Long.parseLong(fname));
                if (notis.getBuktiPenerimaan() != null) {
                    doc = notis.getBuktiPenerimaan();
                    ia = notis.getBuktiPenerimaan().getInfoAudit();
                    ia.setDiKemaskiniOleh(p);
                    ia.setTarikhKemaskini(new java.util.Date());
                } else {
                    ia.setDimasukOleh(p);
                    ia.setTarikhMasuk(new java.util.Date());
                }
                DMSPATH_WO_DMSBASE = DMSPATH_WO_DMSBASE + File.separator + fname;
                doc.setKodDokumen(kodDokumenDAO.findById("BPN"));
                doc.setTajuk("Salinan Penghantaran-" + fname);
                doc.setNoVersi("1.0");
                doc.setKlasifikasi(kodKlasDAO.findById(1));
                doc.setFormat(fileToBeUploaded.getContentType());
                doc.setNamaFizikal(DMSPATH_WO_DMSBASE);
                doc.setInfoAudit(ia);
                Long idDoc = lelongService.simpanOrUpdateDokumen(doc);
                LOG.info("idDoc :" + idDoc);
                // update at DasarTuntutanNotis
                if(fasaPermohonan.getIdAliran().equalsIgnoreCase("24SediaSuratKeputusan") || fasaPermohonan.getIdAliran().equalsIgnoreCase("15DrafSuratBayar")|| fasaPermohonan.getIdAliran().equalsIgnoreCase("14SediaSurat")|| fasaPermohonan.getIdAliran().equalsIgnoreCase("24SediaSuratKeputusan")){
                    ia = notisASB.getInfoAudit();
                    ia.setDiKemaskiniOleh(p);
                    ia.setTarikhKemaskini(new java.util.Date());
                    notisASB.setBuktiPenerimaan(dokumenDAO.findById(idDoc));
                    notisASB.setInfoAudit(ia);
                    lelongService.updateNotis(notisASB);
                }else if(fasaPermohonan.getIdAliran().equalsIgnoreCase("12DrafSuratBorangQ")){
                    ia = notisPB.getInfoAudit();
                    ia.setDiKemaskiniOleh(p);
                    ia.setTarikhKemaskini(new java.util.Date());
                    notisPB.setBuktiPenerimaan(dokumenDAO.findById(idDoc));
                    notisPB.setInfoAudit(ia);
                    lelongService.updateNotis(notisPB);
                    ia = notisNBQ.getInfoAudit();
                    ia.setDiKemaskiniOleh(p);
                    ia.setTarikhKemaskini(new java.util.Date());
                    notisNBQ.setBuktiPenerimaan(dokumenDAO.findById(idDoc));
                    notisNBQ.setInfoAudit(ia);
                    lelongService.updateNotis(notisNBQ);
                }
                addSimpleMessage("Muat naik fail berjaya.");
            } else {
                LOG.error("parameter tidak mencukupi.");
                addSimpleError("Muat naik fail tidak berjaya.");
            }
        } catch (Exception ex) {
            LOG.error("Fatal protocol violation: " + ex.getMessage());
            ex.printStackTrace(); // comment this for production
            addSimpleError("Muat naik fail tidak berjaya. Terdapat masalah pada fail.");
        }
        return new JSP("pengambilan/upload_file.jsp").addParameter("popup", "true");
    }

    public Resolution processUploadDocPemohon() {
        Pengguna p = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        idHakmilik = (String) getContext().getRequest().getParameter("idHakmilik");
        idPihak = (String) getContext().getRequest().getParameter("idPihak");
        if(idHakmilik != null && !idHakmilik.isEmpty()){
            hakmilik = hakmilikDAO.findById(idHakmilik);
        }
        permohonanPihak = notisPenerimaanService.getMohonPihakByIdMohonIdPihak(idPermohonan, Long.parseLong(idPihak));

        fasaPermohonan = notisPenerimaanService.getFasaPermohonan(idPermohonan, "24SediaSuratKeputusan");
        if(fasaPermohonan == null)
            fasaPermohonan = notisPenerimaanService.getFasaPermohonan(idPermohonan, "15DrafSuratBayar");
        if(fasaPermohonan == null)
            fasaPermohonan = notisPenerimaanService.getFasaPermohonan(idPermohonan, "14SediaSurat");

        Notis notisPemohonASB = null;
        if(fasaPermohonan.getIdAliran().equalsIgnoreCase("24SediaSuratKeputusan")){
            notisPemohonASB = notisPenerimaanService.getNotisByidMPkodNotis(idPermohonan, permohonanPihak.getIdPermohonanPihak(), "SAC");
        }else if(fasaPermohonan.getIdAliran().equalsIgnoreCase("15DrafSuratBayar")){
            notisPemohonASB = notisPenerimaanService.getNotisByidMPkodNotis(idPermohonan, permohonanPihak.getIdPermohonanPihak(), "ASB");
        }else if(fasaPermohonan.getIdAliran().equalsIgnoreCase("14SediaSurat")){
            notisPemohonASB = notisPenerimaanService.getNotisByidMPkodNotis(idPermohonan, permohonanPihak.getIdPermohonanPihak(), "PBG");
        }

        InfoAudit ia = new InfoAudit();
        String DMS_BASE = conf.getProperty("document.path");
        String DMSPATH_WO_DMSBASE = null;
        String DMS_PATH = null;
        String fname = null;
        fname = String.valueOf(notisPemohonASB.getIdNotis());
        LOG.info("idNotis : " + fname);
        DateUtil du = new DateUtil();
        try {
            if (p != null && fname != null && fileToBeUploaded != null) {
                String kodCawangan = p.getKodCawangan().getKod();
                DMSPATH_WO_DMSBASE = kodCawangan + File.separator + du.getYear() + File.separator
                        + du.getDateName(String.valueOf(du.getMonth() + 1))
                        + File.separator + du.getDay();
                DMS_PATH = DMS_BASE + (DMS_BASE.endsWith(File.separator) ? "" : File.separator) + DMSPATH_WO_DMSBASE;
                LOG.debug("DIR to created = " + DMS_PATH);
                FileUtil fileUtil = new FileUtil(DMS_PATH);
                String namaFail = fileUtil.saveFile(fname, fileToBeUploaded.getInputStream());
                LOG.info("namaFail :" + namaFail);
                Dokumen doc = new Dokumen();
                Notis notis = notisDAO.findById(Long.parseLong(fname));
                if (notis.getBuktiPenerimaan() != null) {
                    doc = notis.getBuktiPenerimaan();
                    ia = notis.getBuktiPenerimaan().getInfoAudit();
                    ia.setDiKemaskiniOleh(p);
                    ia.setTarikhKemaskini(new java.util.Date());
                } else {
                    ia.setDimasukOleh(p);
                    ia.setTarikhMasuk(new java.util.Date());
                }
                DMSPATH_WO_DMSBASE = DMSPATH_WO_DMSBASE + File.separator + fname;
                doc.setKodDokumen(kodDokumenDAO.findById("BPN"));
                doc.setTajuk("Salinan Penghantaran-" + fname);
                doc.setNoVersi("1.0");
                doc.setKlasifikasi(kodKlasDAO.findById(1));
                doc.setFormat(fileToBeUploaded.getContentType());
                doc.setNamaFizikal(DMSPATH_WO_DMSBASE);
                doc.setInfoAudit(ia);
                Long idDoc = lelongService.simpanOrUpdateDokumen(doc);
                LOG.info("idDoc :" + idDoc);
                // update at DasarTuntutanNotis
                    ia = notisPemohonASB.getInfoAudit();
                    ia.setDiKemaskiniOleh(p);
                    ia.setTarikhKemaskini(new java.util.Date());
                    notisPemohonASB.setBuktiPenerimaan(dokumenDAO.findById(idDoc));
                    notisPemohonASB.setInfoAudit(ia);
                    lelongService.updateNotis(notisPemohonASB);

                addSimpleMessage("Muat naik fail berjaya.");
            } else {
                LOG.error("parameter tidak mencukupi.");
                addSimpleError("Muat naik fail tidak berjaya.");
            }
        } catch (Exception ex) {
            LOG.error("Fatal protocol violation: " + ex.getMessage());
            ex.printStackTrace(); // comment this for production
            addSimpleError("Muat naik fail tidak berjaya. Terdapat masalah pada fail.");
        }
        return new JSP("pengambilan/upload_file.jsp").addParameter("popup", "true");
    }

     public Resolution popupScan() {

        Pengguna p = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        idHakmilik = (String) getContext().getRequest().getParameter("idHakmilik");
        idPihak = (String) getContext().getRequest().getParameter("idPihak");
        hakmilik = hakmilikDAO.findById(idHakmilik);
        permohonanPihak = notisPenerimaanService.getSenaraiPmohonPihakByIdHakmilikIdPihak(idPermohonan, idHakmilik, Long.parseLong(idPihak));

        fasaPermohonan = notisPenerimaanService.getFasaPermohonan(idPermohonan, "24SediaSuratKeputusan");
        if(fasaPermohonan == null)
            fasaPermohonan = notisPenerimaanService.getFasaPermohonan(idPermohonan, "15DrafSuratBayar");
        if(fasaPermohonan == null)
            fasaPermohonan = notisPenerimaanService.getFasaPermohonan(idPermohonan, "12DrafSuratBorangQ");
        if(fasaPermohonan == null)
            fasaPermohonan = notisPenerimaanService.getFasaPermohonan(idPermohonan, "14SediaSurat");

        Notis notisASB = null;
        if(fasaPermohonan.getIdAliran().equalsIgnoreCase("24SediaSuratKeputusan")){
            notisASB = notisPenerimaanService.getNotisByidMPkodNotis(idPermohonan, permohonanPihak.getIdPermohonanPihak(), "SAC");
        }else if(fasaPermohonan.getIdAliran().equalsIgnoreCase("15DrafSuratBayar")){
            notisASB = notisPenerimaanService.getNotisByidMPkodNotis(idPermohonan, permohonanPihak.getIdPermohonanPihak(), "ASB");
        }else if(fasaPermohonan.getIdAliran().equalsIgnoreCase("14SediaSurat")){
            notisASB = notisPenerimaanService.getNotisByidMPkodNotis(idPermohonan, permohonanPihak.getIdPermohonanPihak(), "PBG");
        }
        Notis notisPB = notisPenerimaanService.getNotisByidMPkodNotis(idPermohonan, permohonanPihak.getIdPermohonanPihak(), "PB");
        Notis notisNBQ = notisPenerimaanService.getNotisByidMPkodNotis(idPermohonan, permohonanPihak.getIdPermohonanPihak(), "NBQ");

        InfoAudit ia = new InfoAudit();
        String fname = null;
        if(fasaPermohonan.getIdAliran().equalsIgnoreCase("24SediaSuratKeputusan") || fasaPermohonan.getIdAliran().equalsIgnoreCase("15DrafSuratBayar") || fasaPermohonan.getIdAliran().equalsIgnoreCase("14SediaSurat") || fasaPermohonan.getIdAliran().equalsIgnoreCase("24SediaSuratKeputusan")){
            fname = String.valueOf(notisASB.getIdNotis());
        }else if(fasaPermohonan.getIdAliran().equalsIgnoreCase("12DrafSuratBorangQ")){
            fname = String.valueOf(notisPB.getIdNotis());
        }
        LOG.info("idNotis : " + fname);
        try {
            if (p != null && fname != null) {
                Dokumen doc = new Dokumen();
                Notis notis = notisDAO.findById(Long.parseLong(fname));
                if (notis.getBuktiPenerimaan() != null) {
                    doc = notis.getBuktiPenerimaan();
                    ia = notis.getBuktiPenerimaan().getInfoAudit();
                    ia.setDiKemaskiniOleh(p);
                    ia.setTarikhKemaskini(new java.util.Date());
                } else {
                    ia.setDimasukOleh(p);
                    ia.setTarikhMasuk(new java.util.Date());
                }
                doc.setKodDokumen(kodDokumenDAO.findById("BPN"));
                doc.setTajuk("Salinan Penghantaran-" + fname);
                doc.setNoVersi("1.0");
                doc.setKlasifikasi(kodKlasDAO.findById(1));
                doc.setFormat("application/pdf/image/gif");
                doc.setInfoAudit(ia);
                idDokumen2 = lelongService.simpanOrUpdateDokumen(doc);
                LOG.info("idDoc :" + idDokumen2);
                // update at DasarTuntutanNotis
                if(fasaPermohonan.getIdAliran().equalsIgnoreCase("24SediaSuratKeputusan") || fasaPermohonan.getIdAliran().equalsIgnoreCase("15DrafSuratBayar") || fasaPermohonan.getIdAliran().equalsIgnoreCase("14SediaSurat") || fasaPermohonan.getIdAliran().equalsIgnoreCase("24SediaSuratKeputusan")){
                    ia = notisASB.getInfoAudit();
                    ia.setDiKemaskiniOleh(p);
                    ia.setTarikhKemaskini(new java.util.Date());
                    notisASB.setBuktiPenerimaan(dokumenDAO.findById(idDokumen2));
                    notisASB.setInfoAudit(ia);
                    lelongService.updateNotis(notisASB);
                }else if(fasaPermohonan.getIdAliran().equalsIgnoreCase("12DrafSuratBorangQ")){
                    ia = notisPB.getInfoAudit();
                    ia.setDiKemaskiniOleh(p);
                    ia.setTarikhKemaskini(new java.util.Date());
                    notisPB.setBuktiPenerimaan(dokumenDAO.findById(idDokumen2));
                    notisPB.setInfoAudit(ia);
                    lelongService.updateNotis(notisPB);
                    ia = notisNBQ.getInfoAudit();
                    ia.setDiKemaskiniOleh(p);
                    ia.setTarikhKemaskini(new java.util.Date());
                    notisNBQ.setBuktiPenerimaan(dokumenDAO.findById(idDokumen2));
                    notisNBQ.setInfoAudit(ia);
                    lelongService.updateNotis(notisNBQ);
                }
                
            } else {
                LOG.error("parameter tidak mencukupi.");
            }
        } catch (Exception ex) {
            LOG.error("Fatal protocol violation: " + ex.getMessage());
            ex.printStackTrace(); // comment this for production
        }
        return new JSP("pengambilan/scan_doc.jsp").addParameter("popup", "true");
    }

     public Resolution popupScanPemohon() {

        Pengguna p = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        idHakmilik = (String) getContext().getRequest().getParameter("idHakmilik");
        idPihak = (String) getContext().getRequest().getParameter("idPihak");
        if(idHakmilik != null && !idHakmilik.isEmpty()){
            hakmilik = hakmilikDAO.findById(idHakmilik);
        }
        permohonanPihak = notisPenerimaanService.getMohonPihakByIdMohonIdPihak(idPermohonan, Long.parseLong(idPihak));

        fasaPermohonan = notisPenerimaanService.getFasaPermohonan(idPermohonan, "24SediaSuratKeputusan");
        if(fasaPermohonan == null)
            fasaPermohonan = notisPenerimaanService.getFasaPermohonan(idPermohonan, "15DrafSuratBayar");
        if(fasaPermohonan == null)
            fasaPermohonan = notisPenerimaanService.getFasaPermohonan(idPermohonan, "14SediaSurat");

        Notis notisPemohonASB = null;
        if(fasaPermohonan.getIdAliran().equalsIgnoreCase("24SediaSuratKeputusan")){
            notisPemohonASB = notisPenerimaanService.getNotisByidMPkodNotis(idPermohonan, permohonanPihak.getIdPermohonanPihak(), "SAC");
        }else if(fasaPermohonan.getIdAliran().equalsIgnoreCase("15DrafSuratBayar")){
            notisPemohonASB = notisPenerimaanService.getNotisByidMPkodNotis(idPermohonan, permohonanPihak.getIdPermohonanPihak(), "ASB");
        }else if(fasaPermohonan.getIdAliran().equalsIgnoreCase("14SediaSurat")){
            notisPemohonASB = notisPenerimaanService.getNotisByidMPkodNotis(idPermohonan, permohonanPihak.getIdPermohonanPihak(), "PBG");
        }

        InfoAudit ia = new InfoAudit();
        String fname = null;
        fname = String.valueOf(notisPemohonASB.getIdNotis());
        LOG.info("idNotis : " + fname);
        try {
            if (p != null && fname != null) {
                Dokumen doc = new Dokumen();
                Notis notis = notisDAO.findById(Long.parseLong(fname));
                if (notis.getBuktiPenerimaan() != null) {
                    doc = notis.getBuktiPenerimaan();
                    ia = notis.getBuktiPenerimaan().getInfoAudit();
                    ia.setDiKemaskiniOleh(p);
                    ia.setTarikhKemaskini(new java.util.Date());
                } else {
                    ia.setDimasukOleh(p);
                    ia.setTarikhMasuk(new java.util.Date());
                }
                doc.setKodDokumen(kodDokumenDAO.findById("BPN"));
                doc.setTajuk("Salinan Penghantaran-" + fname);
                doc.setNoVersi("1.0");
                doc.setKlasifikasi(kodKlasDAO.findById(1));
                doc.setFormat("application/pdf/image/gif");
                doc.setInfoAudit(ia);
                idDokumen2 = lelongService.simpanOrUpdateDokumen(doc);
                LOG.info("idDoc :" + idDokumen2);
                // update at DasarTuntutanNotis
                    ia = notisPemohonASB.getInfoAudit();
                    ia.setDiKemaskiniOleh(p);
                    ia.setTarikhKemaskini(new java.util.Date());
                    notisPemohonASB.setBuktiPenerimaan(dokumenDAO.findById(idDokumen2));
                    notisPemohonASB.setInfoAudit(ia);
                    lelongService.updateNotis(notisPemohonASB);

            } else {
                LOG.error("parameter tidak mencukupi.");
            }
        } catch (Exception ex) {
            LOG.error("Fatal protocol violation: " + ex.getMessage());
            ex.printStackTrace(); // comment this for production
        }
        return new JSP("pengambilan/scan_doc.jsp").addParameter("popup", "true");
    }

      public Resolution reload() {
        rehydrate();
        return new JSP("pengambilan/scan_doc.jsp").addParameter("tab", "true");
    }

     public Resolution simpanPenghantarNotis() {
         Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
         idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
         idHakmilik = (String) getContext().getRequest().getParameter("idHakmilik");
         String PP = (String)getContext().getRequest().getParameter("showPP");
         if(PP != null && PP.equalsIgnoreCase("true"))
             showPP = true;
         String HP = (String)getContext().getRequest().getParameter("showHP");
         if(HP != null && HP.equalsIgnoreCase("true"))
             showHP = "true";

        InfoAudit ia = penghantarNotis.getInfoAudit();
        KodCawangan caw = peng.getKodCawangan();
        if (ia == null) {
            ia = new InfoAudit();
            ia.setDimasukOleh(peng);
            ia.setTarikhMasuk(new Date());
            penghantarNotis.setCawangan(caw);
            penghantarNotis.setAktif('Y');
            penghantarNotis.setInfoAudit(ia);
        } else {
            ia.setDiKemaskiniOleh(peng);
            ia.setTarikhKemaskini(new java.util.Date());
        }
        lelongService.saveOrUpdate(penghantarNotis);


        if(showHP.equalsIgnoreCase("true")){
            if(idHakmilik != null && !idHakmilik.isEmpty()){
                hakmilikDetails();
            }else{
                if(showPP == false){
                    showForm();
                }else{
                    showFormPP();
                }
            }
        }else{
            showFormPemohon();
        }
        addSimpleMessage("Maklumat Telah Berjaya Disimpan..");
        return new JSP("pengambilan/Penerimaan_Notis_Borang.jsp").addParameter("tab", "true");
    }

//     public Resolution simpan() {
//
//        InfoAudit info = pengguna.getInfoAudit();
//        info.setDiKemaskiniOleh(pengguna);
//        info.setTarikhKemaskini(new java.util.Date());
//        for (int i = 0; i < listNotis.size(); i++) {
//            Notis nn = listNotis.get(i);
//            KodStatusTerima kodterime = new KodStatusTerima();
//            KodCaraPenghantaran kodHanta = new KodCaraPenghantaran();
//            kodterime.setKod(getContext().getRequest().getParameter("kodPenyampaian" + i));
//            kodHanta.setKod(getContext().getRequest().getParameter("kodPenghantaran" + i));
//            PenghantarNotis peng = penghantarNotisDAO.findById(Integer.parseInt(getContext().getRequest().getParameter("namaPengahantar" + i)));
//            nn.setPenghantarNotis(peng);
//            nn.setKodCaraPenghantaran(kodHanta);
//            nn.setKodStatusTerima(kodterime);
//            nn.setInfoAudit(info);
//            lelongService.saveOrUpdate(nn);
//        }
//        addSimpleMessage("Makluamt Telah Berjaya Disimpan..");
//        return new JSP("lelong/rekod_penghantaran_16H.jsp").addParameter("tab", "true");
//    }


    public Resolution popupUpload() {
        idHakmilik = (String) getContext().getRequest().getParameter("idHakmilik");
        idPihak = (String) getContext().getRequest().getParameter("idPihak");
        isPemohonPihak = (String)getContext().getRequest().getParameter("isPemohonPihak");
        String PP = (String)getContext().getRequest().getParameter("showPP");
        if(PP != null && PP.equalsIgnoreCase("true"))
            showPP = true;
        String HP = (String)getContext().getRequest().getParameter("showHP");
        if(HP != null && HP.equalsIgnoreCase("true"))
            showHP = "true";
//        LOG.info("idNotis :" + idNotis);
        return new JSP("pengambilan/upload_file.jsp").addParameter("popup", "true");
    }

    public List<HakmilikPermohonan> getHakmilikPermohonanList() {
        return hakmilikPermohonanList;
    }

    public void setHakmilikPermohonanList(List<HakmilikPermohonan> hakmilikPermohonanList) {
        this.hakmilikPermohonanList = hakmilikPermohonanList;
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

    public String getIdDokumen() {
        return idDokumen;
    }

    public void setIdDokumen(String idDokumen) {
        this.idDokumen = idDokumen;
    }

    public String getIdPihak() {
        return idPihak;
    }

    public void setIdPihak(String idPihak) {
        this.idPihak = idPihak;
    }

    public Permohonan getPermohonan() {
        return permohonan;
    }

    public void setPermohonan(Permohonan permohonan) {
        this.permohonan = permohonan;
    }

    public String getIdPermohonan() {
        return idPermohonan;
    }

    public void setIdPermohonan(String idPermohonan) {
        this.idPermohonan = idPermohonan;
    }

    public Notis getNotis() {
        return notis;
    }

    public void setNotis(Notis notis) {
        this.notis = notis;
    }

    public PermohonanPihak getPermohonanPihak() {
        return permohonanPihak;
    }

    public void setPermohonanPihak(PermohonanPihak permohonanPihak) {
        this.permohonanPihak = permohonanPihak;
    }

    public List<KandunganFolder> getListKandunganFolder() {
        return listKandunganFolder;
    }

    public void setListKandunganFolder(List<KandunganFolder> listKandunganFolder) {
        this.listKandunganFolder = listKandunganFolder;
    }

    public List<Dokumen> getListDokumen() {
        return listDokumen;
    }

    public void setListDokumen(List<Dokumen> listDokumen) {
        this.listDokumen = listDokumen;
    }

    public LelongService getLelongService() {
        return lelongService;
    }

    public void setLelongService(LelongService lelongService) {
        this.lelongService = lelongService;
    }

    public PenghantarNotis getPenghantarNotis() {
        return penghantarNotis;
    }

    public void setPenghantarNotis(PenghantarNotis penghantarNotis) {
        this.penghantarNotis = penghantarNotis;
    }

    public long getIdDokumen2() {
        return idDokumen2;
    }

    public void setIdDokumen2(long idDokumen2) {
        this.idDokumen2 = idDokumen2;
    }

    public FileBean getFileToBeUploaded() {
        return fileToBeUploaded;
    }

    public void setFileToBeUploaded(FileBean fileToBeUploaded) {
        this.fileToBeUploaded = fileToBeUploaded;
    }

    public String getIdNotis() {
        return idNotis;
    }

    public void setIdNotis(String idNotis) {
        this.idNotis = idNotis;
    }

    public FasaPermohonan getFasaPermohonan() {
        return fasaPermohonan;
    }

    public void setFasaPermohonan(FasaPermohonan fasaPermohonan) {
        this.fasaPermohonan = fasaPermohonan;
    }

    public List<Notis> getListNotisNBQ() {
        return listNotisNBQ;
    }

    public void setListNotisNBQ(List<Notis> listNotisNBQ) {
        this.listNotisNBQ = listNotisNBQ;
    }

    public List<Notis> getListNotisPB() {
        return listNotisPB;
    }

    public void setListNotisPB(List<Notis> listNotisPB) {
        this.listNotisPB = listNotisPB;
    }

    public List<Integer> getNamaPengahantar() {
        return namaPengahantar;
    }

    public void setNamaPengahantar(List<Integer> namaPengahantar) {
        this.namaPengahantar = namaPengahantar;
    }

    public List<String> getCatatanPenerimaan() {
        return catatanPenerimaan;
    }

    public void setCatatanPenerimaan(List<String> catatanPenerimaan) {
        this.catatanPenerimaan = catatanPenerimaan;
    }

    public List<String> getKodPenghantaran() {
        return kodPenghantaran;
    }

    public void setKodPenghantaran(List<String> kodPenghantaran) {
        this.kodPenghantaran = kodPenghantaran;
    }

    public List<String> getKodStatusTerima() {
        return kodStatusTerima;
    }

    public void setKodStatusTerima(List<String> kodStatusTerima) {
        this.kodStatusTerima = kodStatusTerima;
    }

    public List<String> getTarikhHantar() {
        return tarikhHantar;
    }

    public void setTarikhHantar(List<String> tarikhHantar) {
        this.tarikhHantar = tarikhHantar;
    }

    public List<String> getTarikhTerima() {
        return tarikhTerima;
    }

    public void setTarikhTerima(List<String> tarikhTerima) {
        this.tarikhTerima = tarikhTerima;
    }

    public List<String> getIdDokumenList() {
        return idDokumenList;
    }

    public void setIdDokumenList(List<String> idDokumenList) {
        this.idDokumenList = idDokumenList;
    }

    public List<Notis> getListNotisASB() {
        return listNotisASB;
    }

    public void setListNotisASB(List<Notis> listNotisASB) {
        this.listNotisASB = listNotisASB;
    }

    public boolean isShow() {
        return show;
    }

    public void setShow(boolean show) {
        this.show = show;
    }

    public List<String> getCatatanPenerimaanP() {
        return catatanPenerimaanP;
    }

    public void setCatatanPenerimaanP(List<String> catatanPenerimaanP) {
        this.catatanPenerimaanP = catatanPenerimaanP;
    }

    public List<String> getIdDokumenListP() {
        return idDokumenListP;
    }

    public void setIdDokumenListP(List<String> idDokumenListP) {
        this.idDokumenListP = idDokumenListP;
    }

    public List<String> getKodPenghantaranP() {
        return kodPenghantaranP;
    }

    public void setKodPenghantaranP(List<String> kodPenghantaranP) {
        this.kodPenghantaranP = kodPenghantaranP;
    }

    public List<String> getKodStatusTerimaP() {
        return kodStatusTerimaP;
    }

    public void setKodStatusTerimaP(List<String> kodStatusTerimaP) {
        this.kodStatusTerimaP = kodStatusTerimaP;
    }

    public List<Notis> getListNotisPemohonASB() {
        return listNotisPemohonASB;
    }

    public void setListNotisPemohonASB(List<Notis> listNotisPemohonASB) {
        this.listNotisPemohonASB = listNotisPemohonASB;
    }

    public List<Integer> getNamaPengahantarP() {
        return namaPengahantarP;
    }

    public void setNamaPengahantarP(List<Integer> namaPengahantarP) {
        this.namaPengahantarP = namaPengahantarP;
    }

    public boolean isShowPP() {
        return showPP;
    }

    public void setShowPP(boolean showPP) {
        this.showPP = showPP;
    }

    public List<String> getTarikhHantarP() {
        return tarikhHantarP;
    }

    public void setTarikhHantarP(List<String> tarikhHantarP) {
        this.tarikhHantarP = tarikhHantarP;
    }

    public List<String> getTarikhTerimaP() {
        return tarikhTerimaP;
    }

    public void setTarikhTerimaP(List<String> tarikhTerimaP) {
        this.tarikhTerimaP = tarikhTerimaP;
    }

    public String getShowHP() {
        return showHP;
    }

    public void setShowHP(String showHP) {
        this.showHP = showHP;
    }

    public String getIsPemohonPihak() {
        return isPemohonPihak;
    }

    public void setIsPemohonPihak(String isPemohonPihak) {
        this.isPemohonPihak = isPemohonPihak;
    }

    
}