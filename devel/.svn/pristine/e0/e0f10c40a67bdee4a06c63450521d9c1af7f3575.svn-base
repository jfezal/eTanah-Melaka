/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.stripes.pengambilan.sek8;

import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.dao.PermohonanDAO;
import etanah.model.KandunganFolder;
import etanah.model.KodDokumen;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.model.ambil.BorangPerPermohonan;
import etanah.service.acq.service.BorangACQService;
import etanah.view.BasicTabActionBean;
import etanah.view.stripes.pengambilan.share.form.ViewBorangForm;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;

/**
 *
 * @author mohd.faidzal
 */
@UrlBinding("/pengambilan/borang_cd")
public class BorangCdanDActionBean extends BasicTabActionBean {

    @Inject
    BorangACQService borangACQService;
    @Inject
    PermohonanDAO permohonanDAO;
    List<ViewBorangForm> listBorang = new ArrayList<ViewBorangForm>();
    private Map<String, String> kodMap = new HashMap<String, String>();
    private List<KandunganFolder> senaraiKandungan = new ArrayList<KandunganFolder>();
    KandunganFolder kandunganFolderC;
    KandunganFolder kandunganFolderD;

    @DefaultHandler
    public Resolution borangAForm() {
        listBorang = new ArrayList<ViewBorangForm>();
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        Permohonan permohonan = permohonanDAO.findById(idPermohonan);

        List<KandunganFolder> senaraiKF = permohonan.getFolderDokumen().getSenaraiKandungan();
        for (KandunganFolder kf : senaraiKF) {
            if (kf == null || kf.getDokumen() == null) {
                continue;
            }
            KodDokumen kd = kf.getDokumen().getKodDokumen();
            if (kd != null) {
                kodMap.put(kd.getKod(), kd.getNama());
            }

//            if (kf.getDokumen().getPermohonan() != null) {
            if (kf.getDokumen().getKodDokumen().getKod().equals("C")) {
                senaraiKandungan.add(kf);
                   kandunganFolderC = kf;
            }
//            }

//            if (kf.getDokumen().getPermohonan() != null) {
            if (kf.getDokumen().getKodDokumen().getKod().equals("D")) {
//                    d.setKandunganFolderD(kf);
                    kandunganFolderD = kf;
                senaraiKandungan.add(kf);
            }
//            }

        }

        ViewBorangForm c = new ViewBorangForm();
        BorangPerPermohonan bppC = borangACQService.findBorangA(permohonan.getIdPermohonan(), "NBC");
        if (bppC != null) {
            c.setDitandatangan(bppC.getDitandatangan() != null ? bppC.getDitandatangan().getNama() : "");
        }
        c.setNamaBorang("BORANG C");
//        c.setKandunganFolderC(KandunganFolderC);
        c.setUrlPapar("");
        listBorang.add(c);
        ViewBorangForm d = new ViewBorangForm();
        BorangPerPermohonan bppD = borangACQService.findBorangA(permohonan.getIdPermohonan(), "NBD");
        if (bppD != null) {
            d.setDitandatangan(bppD.getDitandatangan() != null ? bppD.getDitandatangan().getNama() : "");
        }
        d.setNamaBorang("BORANG D");
//        d.setKandunganFolderD(KandunganFolderD);
        d.setSenaraiKandungan(senaraiKandungan);
        listBorang.add(d);
//        listBorang.ad

        return new JSP("/pengambilan/APT/borang_c_d.jsp").addParameter("tab", "true");
    }

    public List<ViewBorangForm> getListBorang() {
        return listBorang;
    }

    public void setListBorang(List<ViewBorangForm> listBorang) {
        this.listBorang = listBorang;
    }

    public BorangACQService getBorangACQService() {
        return borangACQService;
    }

    public void setBorangACQService(BorangACQService borangACQService) {
        this.borangACQService = borangACQService;
    }

    public PermohonanDAO getPermohonanDAO() {
        return permohonanDAO;
    }

    public void setPermohonanDAO(PermohonanDAO permohonanDAO) {
        this.permohonanDAO = permohonanDAO;
    }

    public Map<String, String> getKodMap() {
        return kodMap;
    }

    public void setKodMap(Map<String, String> kodMap) {
        this.kodMap = kodMap;
    }

    public KandunganFolder getKandunganFolderC() {
        return kandunganFolderC;
    }

    public void setKandunganFolderC(KandunganFolder KandunganFolderC) {
        this.kandunganFolderC = KandunganFolderC;
    }

    public KandunganFolder getKandunganFolderD() {
        return kandunganFolderD;
    }

    public void setKandunganFolderD(KandunganFolder KandunganFolderD) {
        this.kandunganFolderD = KandunganFolderD;
    }

    public List<KandunganFolder> getSenaraiKandungan() {
        return senaraiKandungan;
    }

    public void setSenaraiKandungan(List<KandunganFolder> senaraiKandungan) {
        this.senaraiKandungan = senaraiKandungan;
    }
    

}
