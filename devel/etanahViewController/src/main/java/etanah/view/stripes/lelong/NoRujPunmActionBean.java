/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.stripes.lelong;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.dao.KodDokumenDAO;
import etanah.dao.KodNotisDAO;
import etanah.dao.PermohonanDAO;
import etanah.model.Dokumen;
import etanah.model.HakmilikPihakBerkepentingan;
import etanah.model.InfoAudit;
import etanah.model.KandunganFolder;
import etanah.model.KodDokumen;
import etanah.model.KodNotis;
import etanah.model.Notis;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.model.PermohonanPihak;
import etanah.service.common.LelongService;
import etanah.view.etanahActionBeanContext;
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
@UrlBinding("/lelong/no_ruj_punm")
public class NoRujPunmActionBean extends AbleActionBean {

    private static final Logger LOG = Logger.getLogger(NoRujPunmActionBean.class);
    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    LelongService lelongService;
    @Inject
    KodNotisDAO kodNotisDAO;
    @Inject
    KodDokumenDAO kodDokumenDAO;
    private String idPermohonan;
    private Permohonan permohonan;
    private List<HakmilikPihakBerkepentingan> senaraiHakmilikPihak;
    private List<KandunganFolder> listKandunganFolder;
    private Pengguna pengguna;
    private List<Notis> listNotis;
    private String noRuj;

    @DefaultHandler
    public Resolution showForm() {
        return new JSP("lelong/no_pumm_2A.jsp").addParameter("tab", "true");
    }

    public void notisGantian() {
        LOG.info("----notisGantian----");
        Dokumen dokumen2 = null;
        KodNotis kodNotis = kodNotisDAO.findById("2A");
        KodDokumen kd = kodDokumenDAO.findById("2A");


        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = permohonanDAO.findById(idPermohonan);
        
        LOG.info("idPermohonan" + idPermohonan);
        
        List<KandunganFolder> listFD = lelongService.getListDokumen2A(permohonan.getFolderDokumen().getFolderId());
        if (!listFD.isEmpty()) {
            dokumen2 = listFD.get(0).getDokumen();
        } else {
            dokumen2 = lelongService.saveOrUpdateDokumen(permohonan.getFolderDokumen(), kd, idPermohonan, pengguna);
        }

        LOG.info("dokumen 2A" + dokumen2);

        InfoAudit info = pengguna.getInfoAudit();
        info.setDimasukOleh(pengguna);
        info.setTarikhMasuk(new java.util.Date());

        List<Notis> listNS = lelongService.getListNotisTS(idPermohonan);
        LOG.info("listNS.size() : " + listNS.size());
        if (!listNS.isEmpty()) {
            for (int j = 0; j < listNS.size(); j++) {
                LOG.info("senaraiPermohonanPihak name : " + listNS.get(j).getHakmilikPihakBerkepentingan().getNama());
                Notis notis22 = new Notis();
                notis22.setPermohonan(permohonan);
                notis22.setInfoAudit(info);
                notis22.setTarikhNotis(new java.util.Date());
                notis22.setKodNotis(kodNotis);
                notis22.setHakmilikPihakBerkepentingan(listNS.get(j).getHakmilikPihakBerkepentingan());
                notis22.setDokumenNotis(dokumen2);
                notis22.setJabatan(permohonan.getKodUrusan().getJabatan());
                lelongService.saveOrUpdate(notis22);
            }
        }
    }

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = permohonanDAO.findById(idPermohonan);
        pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);

        List<PermohonanPihak> listHP = lelongService.getSenaraiPmohonPihak(idPermohonan);
        Map<Long, PermohonanPihak> pihakMap = new HashMap<Long, PermohonanPihak>();
        for (PermohonanPihak hp : listHP) {
            Long id = hp.getPihak().getIdPihak();
            if (pihakMap.containsKey(id)) {
                continue;
            } else {
                pihakMap.put(id, hp);
            }
        }
        listNotis = lelongService.getListNotis2(idPermohonan, "2A");
        LOG.info("listNotis : " + listNotis.size());
        if (listNotis.isEmpty()) {
            LOG.info("Belum ade lg...");
            notisGantian();
            listNotis = lelongService.getListNotis2(idPermohonan, "2A");
        } else {
            noRuj = listNotis.get(0).getNoRujukan();
        }
    }

    public Resolution simpanNoRuj() {
        if (!listNotis.isEmpty()) {
            for (Notis nn : listNotis) {
                nn.setNoRujukan(noRuj);
                lelongService.saveOrUpdate(nn);
            }
        }
        rehydrate();
        addSimpleMessage("Maklumat Telah Berjaya Disimpan...");
        return new JSP("lelong/no_pumm_2A.jsp").addParameter("tab", "true");
    }

    public String getIdPermohonan() {
        return idPermohonan;
    }

    public void setIdPermohonan(String idPermohonan) {
        this.idPermohonan = idPermohonan;
    }

    public List<KandunganFolder> getListKandunganFolder() {
        return listKandunganFolder;
    }

    public void setListKandunganFolder(List<KandunganFolder> listKandunganFolder) {
        this.listKandunganFolder = listKandunganFolder;
    }

    public List<Notis> getListNotis() {
        return listNotis;
    }

    public void setListNotis(List<Notis> listNotis) {
        this.listNotis = listNotis;
    }

    public Pengguna getPengguna() {
        return pengguna;
    }

    public void setPengguna(Pengguna pengguna) {
        this.pengguna = pengguna;
    }

    public Permohonan getPermohonan() {
        return permohonan;
    }

    public void setPermohonan(Permohonan permohonan) {
        this.permohonan = permohonan;
    }

    public List<HakmilikPihakBerkepentingan> getSenaraiHakmilikPihak() {
        return senaraiHakmilikPihak;
    }

    public void setSenaraiHakmilikPihak(List<HakmilikPihakBerkepentingan> senaraiHakmilikPihak) {
        this.senaraiHakmilikPihak = senaraiHakmilikPihak;
    }

    public String getNoRuj() {
        return noRuj;
    }

    public void setNoRuj(String noRuj) {
        this.noRuj = noRuj;
    }
}
