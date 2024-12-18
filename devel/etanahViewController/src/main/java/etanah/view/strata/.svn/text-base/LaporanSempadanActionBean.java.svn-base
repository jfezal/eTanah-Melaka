/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.strata;

import able.stripes.AbleActionBean; 
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.dao.PermohonanDAO;
import etanah.model.Dokumen;
import etanah.model.ImejLaporan;
import etanah.model.InfoAudit;
import etanah.model.LaporanTanah;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.service.StrataPtService;
import etanah.view.etanahActionBeanContext;
import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.FileBean;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

/**
 *
 * @author faidzal
 */
@UrlBinding("/strata/laporanSempadan")
public class LaporanSempadanActionBean extends AbleActionBean {

    @Inject
    etanah.Configuration conf;
    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    StrataPtService strService;
    @Inject
    CommonService comm;
    private Permohonan permohonan;
    private LaporanTanah laporanTanah;
    private String gunaBarat;
    private String gunaTimur;
    private String gunaSelatan;
    private String gunaUtara;
    private String lotBarat;
    private String lotTimur;
    private String lotSelatan;
    private String lotUtara;
    private boolean readOnly;
    private List<ImejLaporan> utaraDoc = new ArrayList<ImejLaporan>();
    private List<ImejLaporan> selatanDoc = new ArrayList<ImejLaporan>();
    private List<ImejLaporan> timurDoc = new ArrayList<ImejLaporan>();
    private List<ImejLaporan> baratDoc = new ArrayList<ImejLaporan>();
    private FileBean fileToBeUpload;
    private static final Logger LOG = Logger.getLogger(LaporanSempadanActionBean.class);
    private String catatan;

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {
        
        Pengguna pguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = permohonanDAO.findById(idPermohonan);
    }

    @DefaultHandler
    public Resolution sempadanForm() {
        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = permohonanDAO.findById(idPermohonan);
        laporanTanah = strService.findLaporanTanahByIdPermohonan(idPermohonan);
        if (laporanTanah != null) {
            gunaBarat = laporanTanah.getSempadanBaratKegunaan();
            gunaTimur = laporanTanah.getSempadanTimurKegunaan();
            gunaSelatan = laporanTanah.getSempadanSelatanKegunaan();
            gunaUtara = laporanTanah.getSempadanUtaraKegunaan();
            lotBarat = laporanTanah.getSempadanBaratNoLot();
            lotTimur = laporanTanah.getSempadanTimurNoLot();
            lotSelatan = laporanTanah.getSempadanSelatanNoLot();
            lotUtara = laporanTanah.getSempadanUtaraNoLot();

            utaraDoc = comm.getListDocL('U', laporanTanah.getIdLaporan());
            selatanDoc = comm.getListDocL('S', laporanTanah.getIdLaporan());
            timurDoc = comm.getListDocL('T', laporanTanah.getIdLaporan());
            baratDoc = comm.getListDocL('B', laporanTanah.getIdLaporan());
        } else {
        }
        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        return new JSP("strata/pbbm/laporantanah_sempadan.jsp").addParameter("tab", "true");
    }

    public Resolution sempadanFormReadOnly() {
        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = permohonanDAO.findById(idPermohonan);
        laporanTanah = strService.findLaporanTanahByIdPermohonan(idPermohonan);
        if (laporanTanah != null) {
            gunaBarat = laporanTanah.getSempadanBaratKegunaan();
            gunaTimur = laporanTanah.getSempadanTimurKegunaan();
            gunaSelatan = laporanTanah.getSempadanSelatanKegunaan();
            gunaUtara = laporanTanah.getSempadanUtaraKegunaan();
            lotBarat = laporanTanah.getSempadanBaratNoLot();
            lotTimur = laporanTanah.getSempadanTimurNoLot();
            lotSelatan = laporanTanah.getSempadanSelatanNoLot();
            lotUtara = laporanTanah.getSempadanUtaraNoLot();
        } else {
        }
        getContext().getRequest().setAttribute("edit", Boolean.FALSE);
        return new JSP("strata/pbbm/laporantanah_sempadan.jsp").addParameter("tab", "true");
    }

    public Resolution simpanSempadan() {
        Pengguna pguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = permohonanDAO.findById(idPermohonan);
        laporanTanah = strService.findLaporanTanahByIdPermohonan(idPermohonan);
        if (laporanTanah != null) {
        } else {
            laporanTanah = new LaporanTanah();
            laporanTanah.setPermohonan(permohonan);
            laporanTanah.setInfoAudit(strService.getInfo(pguna));

        }
        laporanTanah.setSempadanBaratNoLot(lotBarat);
        laporanTanah.setSempadanUtaraNoLot(lotUtara);
        laporanTanah.setSempadanTimurNoLot(lotTimur);
        laporanTanah.setSempadanSelatanNoLot(lotSelatan);
        laporanTanah.setSempadanBaratKegunaan(gunaBarat);
        laporanTanah.setSempadanUtaraKegunaan(gunaUtara);
        laporanTanah.setSempadanTimurKegunaan(gunaTimur);
        laporanTanah.setSempadanSelatanKegunaan(gunaSelatan);
        laporanTanah = strService.simpanLaporan(laporanTanah);
        addSimpleMessage("Maklumat berjaya disimpan");
        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        return new JSP("strata/pbbm/laporantanah_sempadan.jsp").addParameter("tab", "true");
    }

    public Resolution muatNaik() throws Exception {
        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        String lokasi = getContext().getRequest().getParameter("lokasi");
        Pengguna pguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);

        String error = "";
        String msg = "";

        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        InfoAudit ia = new InfoAudit();
        ia.setTarikhMasuk(new Date());
        ia.setDimasukOleh(pguna);
        laporanTanah = strService.getLaporanTanahByIdPermohonan(idPermohonan);

        String dokumenPath = conf.getProperty("document.path");
        permohonan = permohonanDAO.findById(idPermohonan);
        LOG.info("INI ADALAH FOLDER DOKUMEN" + permohonan.getFolderDokumen().getFolderId());

        dokumenPath = dokumenPath + (dokumenPath.endsWith(File.separator) ? "" : File.separator) + permohonan.getFolderDokumen().getFolderId();
        if (fileToBeUpload == null) {
            addSimpleError("Sila pilih fail imej untuk dimuatnaik.");
            return new JSP("strata/Ruang_Udara/muatnaik.jsp").addParameter("popup", "true");
        } else if (!(fileToBeUpload.getFileName().endsWith(".jpg") || fileToBeUpload.getFileName().endsWith(".bmp") || fileToBeUpload.getFileName().endsWith(".png"))) {
            LOG.info("----------------fileToBeUpload---else-----------:" + fileToBeUpload.getFileName());
            addSimpleError("Sila pilih fail imej untuk dimuatnaik.");
            return new JSP("strata/Ruang_Udara/muatnaik.jsp").addParameter("popup", "true");
        }
        LOG.info("----------------fileToBeUpload--------------:" + fileToBeUpload.getFileName()); //End

        Dokumen doc = comm.saveDokumen(dokumenPath, fileToBeUpload, permohonan.getFolderDokumen().getFolderId(), ia, permohonan);
        ImejLaporan imejLaporan = new ImejLaporan();
        imejLaporan.setHakmilik(permohonan.getSenaraiHakmilik().get(0).getHakmilik());
        imejLaporan.setCawangan(peng.getKodCawangan());
        imejLaporan.setDokumen(doc);
        imejLaporan.setLaporanTanah(laporanTanah);
        imejLaporan.setCatatan(catatan);
        LOG.info("LOKASI" + lokasi);
        char c = 0;
        if (StringUtils.isNotEmpty(lokasi)) {
            c = (lokasi).charAt(0);
            imejLaporan.setPandanganImej(c);
        }

        imejLaporan.setInfoAudit(ia);
        comm.saveImej(imejLaporan);
        LOG.info("simpanMuatNaik::finish");
        addSimpleMessage("Muat naik fail berjaya.");
        return new JSP("strata/pbbm/muatnaik.jsp").addParameter("popup", "true");
    }

    public Resolution uploadDoc() {
        String idPihak = getContext().getRequest().getParameter("idPihak");
        return new JSP("strata/pbbm/muatnaik.jsp").addParameter("popup", "true");
    }

    public Resolution resetForm() {
        LOG.info("--clearing Form--:");
        lotUtara = "";
        gunaUtara = "";
        lotSelatan = "";
        gunaSelatan = "";
        lotTimur = "";
        gunaTimur = "";
        lotBarat = "";
        gunaBarat = "";

        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        laporanTanah = strService.findLaporanTanahByIdPermohonan(idPermohonan);
        LOG.info("--laporanTanah--:"+laporanTanah);
        InfoAudit ia = new InfoAudit();
        if(laporanTanah!=null){
            ia = laporanTanah.getInfoAudit();
            ia.setTarikhKemaskini(new Date());
            ia.setDiKemaskiniOleh(peng);
            laporanTanah.setInfoAudit(ia);
            laporanTanah.setSempadanBaratNoLot(lotBarat);
            laporanTanah.setSempadanUtaraNoLot(lotUtara);
            laporanTanah.setSempadanTimurNoLot(lotTimur);
            laporanTanah.setSempadanSelatanNoLot(lotSelatan);
            laporanTanah.setSempadanBaratKegunaan(gunaBarat);
            laporanTanah.setSempadanUtaraKegunaan(gunaUtara);
            laporanTanah.setSempadanTimurKegunaan(gunaTimur);
            laporanTanah.setSempadanSelatanKegunaan(gunaSelatan);
            laporanTanah = strService.simpanLaporan(laporanTanah);
        }
        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        return new JSP("strata/pbbm/laporantanah_sempadan.jsp").addParameter("tab", "true");
    }

    public String getGunaBarat() {
        return gunaBarat;
    }

    public void setGunaBarat(String gunaBarat) {
        this.gunaBarat = gunaBarat;
    }

    public String getGunaSelatan() {
        return gunaSelatan;
    }

    public void setGunaSelatan(String gunaSelatan) {
        this.gunaSelatan = gunaSelatan;
    }

    public String getGunaTimur() {
        return gunaTimur;
    }

    public void setGunaTimur(String gunaTimur) {
        this.gunaTimur = gunaTimur;
    }

    public String getGunaUtara() {
        return gunaUtara;
    }

    public void setGunaUtara(String gunaUtara) {
        this.gunaUtara = gunaUtara;
    }

    public LaporanTanah getLaporanTanah() {
        return laporanTanah;
    }

    public void setLaporanTanah(LaporanTanah laporanTanah) {
        this.laporanTanah = laporanTanah;
    }

    public String getLotBarat() {
        return lotBarat;
    }

    public void setLotBarat(String lotBarat) {
        this.lotBarat = lotBarat;
    }

    public String getLotSelatan() {
        return lotSelatan;
    }

    public void setLotSelatan(String lotSelatan) {
        this.lotSelatan = lotSelatan;
    }

    public String getLotTimur() {
        return lotTimur;
    }

    public void setLotTimur(String lotTimur) {
        this.lotTimur = lotTimur;
    }

    public String getLotUtara() {
        return lotUtara;
    }

    public void setLotUtara(String lotUtara) {
        this.lotUtara = lotUtara;
    }

    public boolean isReadOnly() {
        return readOnly;
    }

    public void setReadOnly(boolean readOnly) {
        this.readOnly = readOnly;
    }

    public Permohonan getPermohonan() {
        return permohonan;
    }

    public void setPermohonan(Permohonan permohonan) {
        this.permohonan = permohonan;
    }

    public List<ImejLaporan> getBaratDoc() {
        return baratDoc;
    }

    public void setBaratDoc(List<ImejLaporan> baratDoc) {
        this.baratDoc = baratDoc;
    }

    public StrataPtService getStrService() {
        return strService;
    }

    public void setStrService(StrataPtService strService) {
        this.strService = strService;
    }

    public List<ImejLaporan> getTimurDoc() {
        return timurDoc;
    }

    public void setTimurDoc(List<ImejLaporan> timurDoc) {
        this.timurDoc = timurDoc;
    }

    public List<ImejLaporan> getUtaraDoc() {
        return utaraDoc;
    }

    public void setUtaraDoc(List<ImejLaporan> utaraDoc) {
        this.utaraDoc = utaraDoc;
    }

    public List<ImejLaporan> getSelatanDoc() {
        return selatanDoc;
    }

    public void setSelatanDoc(List<ImejLaporan> selatanDoc) {
        this.selatanDoc = selatanDoc;
    }

    public String getCatatan() {
        return catatan;
    }

    public void setCatatan(String catatan) {
        this.catatan = catatan;
    }

    public FileBean getFileToBeUpload() {
        return fileToBeUpload;
    }

    public void setFileToBeUpload(FileBean fileToBeUpload) {
        this.fileToBeUpload = fileToBeUpload;
    }
}
