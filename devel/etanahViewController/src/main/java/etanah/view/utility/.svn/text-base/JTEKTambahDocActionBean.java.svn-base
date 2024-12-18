/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.utility;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.dao.KandunganFolderDAO;
import etanah.dao.PermohonanDAO;
import etanah.dao.PermohonanRujukanLuarDAO;
import etanah.dao.PermohonanRujukanLuarDokumenDAO;
import etanah.dao.UlasanJabatanTeknikalDAO;
import etanah.model.InfoAudit;
import etanah.model.KandunganFolder;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.model.PermohonanRujukanLuar;
import etanah.model.PermohonanRujukanLuarDokumen;
import etanah.model.UlasanJabatanTeknikal;
import etanah.view.etanahActionBeanContext;
import etanah.view.jtek.ws.JTEKServices;
import etanah.view.jtek.ws.JTEKServices2;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;

import net.sourceforge.stripes.controller.LifecycleStage;
import org.apache.log4j.Logger;
import org.hibernate.Session;

/**
 *
 * @author nurashidah
 */
@UrlBinding("/utility/tambah_dok_JTEK")
public class JTEKTambahDocActionBean extends AbleActionBean {

    private static Logger logger = Logger.getLogger(JTEKTambahDocActionBean.class);
    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;
    @Inject
    JTEKServices2 JTEKServices;
    @Inject
    UlasanJabatanTeknikalDAO ulasanJabatanTeknikalDAO;
    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    PermohonanRujukanLuarDokumenDAO permohonanRujukanLuarDokumenDAO;
    @Inject
    KandunganFolderDAO kandunganFolderDAO;
    @Inject
    PermohonanRujukanLuarDAO permohonanRujukanLuarDAO;
    private UlasanJabatanTeknikal ulasanJabatanTeknikal;
    private Permohonan permohonan;
    private PermohonanRujukanLuar permohonanRujukanLuar;
    private PermohonanRujukanLuarDokumen permohonanRujukanLuarDokumen;
    private KandunganFolder kandunganFolder;
    private List<PermohonanRujukanLuarDokumen> senaraiDokumenSerta;
    private List<KandunganFolder> senaraiDokumenFolder;
//    private List<PermohonanRujukanLuar> idRujukan;
    private String idFolder;
    private String kodCaw;
    private int sizeKod;
    long idRujukan;
//    private List<KodAgensi> senaraiKodAgensi;

    @DefaultHandler
    public Resolution showForm() {

        return new ForwardResolution("/WEB-INF/jsp/utiliti/tambahDocJTEK.jsp");
    }

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {
        logger.info("------------rehydrate--------------");

//        System.out.println("size Ulasan : " + ulasanJTEK.size());
    }

    public Resolution dokumenSerta() throws Exception {

        String idMohon = getContext().getRequest().getParameter("idMohon");
        String kodAgensi = getContext().getRequest().getParameter("kodAgensi");

        //FOR DOKUMEN SERTA LIST

//        idRujukan = new ArrayList<PermohonanRujukanLuar>();
//        idRujukan.addAll(JTEKServices.searchIdRujukan(idMohon, kodAgensi));
//        
//        System.out.println("permohonanRujukanLuar :" + idRujukan.size() );
//        if (idRujukan != null) {
//            senaraiDokumenSerta = JTEKServices.getSenaraiDokumen(Permohonan);
//        }
        permohonanRujukanLuar = JTEKServices.searchIdRujukan(idMohon, kodAgensi);

        if (permohonanRujukanLuar != null) {
            idRujukan = permohonanRujukanLuar.getIdRujukan();
            senaraiDokumenSerta = JTEKServices.getSenaraiDokumen(String.valueOf(permohonanRujukanLuar.getIdRujukan()));

        }

        //FOR TAMBAH DOKUMEN SERTA
        permohonan = permohonanDAO.findById(idMohon);
        kodCaw = permohonan.getCawangan().getKod();
        idFolder = Long.toString(permohonan.getFolderDokumen().getFolderId());
        senaraiDokumenFolder = JTEKServices.findFolderDok(idFolder);
        sizeKod = senaraiDokumenFolder.size();
        System.out.println("Size :" + sizeKod);

        return new ForwardResolution("/WEB-INF/jsp/utiliti/tambahDocJTEK.jsp");
    }

    public Resolution simpanDok() {
        logger.info("------------simpanDok--------------");
        Pengguna peng = (Pengguna) context.getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        String item = getContext().getRequest().getParameter("doc");
        String idRujukan = getContext().getRequest().getParameter("idRuj");
        String[] listDokumen = item.split(",");
        System.out.println("Size :" + listDokumen.length);
        InfoAudit ia = new InfoAudit();
        for (int i = 0; i < listDokumen.length; i++) {

            if (!listDokumen[i].equals("T")) {
                PermohonanRujukanLuarDokumen mohonRujLuarDokumen = new PermohonanRujukanLuarDokumen();
                logger.info("---------listDokumen-------::" + listDokumen[i]);
                kandunganFolder = kandunganFolderDAO.findById(Long.parseLong(listDokumen[i]));

         
                permohonanRujukanLuar = permohonanRujukanLuarDAO.findById(Long.parseLong(idRujukan));
                mohonRujLuarDokumen = JTEKServices.findDokumen(String.valueOf(kandunganFolder.getDokumen().getIdDokumen()), idRujukan);

                if (mohonRujLuarDokumen != null) {
                    ia = mohonRujLuarDokumen.getInfoAudit();
                    ia.setDiKemaskiniOleh(peng);
                    ia.setTarikhKemaskini(new java.util.Date());
                } else {
                    mohonRujLuarDokumen = new PermohonanRujukanLuarDokumen();
                    ia = new InfoAudit();
                    ia.setDimasukOleh(peng);
                    ia.setTarikhMasuk(new Date());
                    mohonRujLuarDokumen.setCawangan(permohonanRujukanLuar.getPermohonan().getCawangan());
                    mohonRujLuarDokumen.setPermohonanRujukanLuar(permohonanRujukanLuar);
                    mohonRujLuarDokumen.setDokumen(kandunganFolder.getDokumen());

                }
                mohonRujLuarDokumen.setInfoAudit(ia);
                JTEKServices.simpanPermohonanRujLuarDokumen(mohonRujLuarDokumen);
            }
//            senaraiDokumenSerta = JTEKServices.getSenaraiDokumen(String.valueOf(permohonanRujukanLuar.getIdRujukan()));
        }

        senaraiDokumenSerta = JTEKServices.getSenaraiDokumen(idRujukan);
        addSimpleMessage("Maklumat berjaya disimpan.");
        return new ForwardResolution("/WEB-INF/jsp/utiliti/tambahDocJTEK.jsp");
    }

    public int getSizeKod() {
        return sizeKod;
    }

    public void setSizeKod(int sizeKod) {
        this.sizeKod = sizeKod;
    }

    public String getKodCaw() {
        return kodCaw;
    }

    public void setKodCaw(String kodCaw) {
        this.kodCaw = kodCaw;
    }

    public String getIdFolder() {
        return idFolder;
    }

    public void setIdFolder(String idFolder) {
        this.idFolder = idFolder;
    }

    public long getIdRujukan() {
        return idRujukan;
    }

    public void setIdRujukan(long idRujukan) {
        this.idRujukan = idRujukan;
    }

    public List<PermohonanRujukanLuarDokumen> getSenaraiDokumenSerta() {
        return senaraiDokumenSerta;
    }

    public void setSenaraiDokumenSerta(List<PermohonanRujukanLuarDokumen> senaraiDokumenSerta) {
        this.senaraiDokumenSerta = senaraiDokumenSerta;
    }

    public List<KandunganFolder> getSenaraiDokumenFolder() {
        return senaraiDokumenFolder;
    }

    public void setSenaraiDokumenFolder(List<KandunganFolder> senaraiDokumenFolder) {
        this.senaraiDokumenFolder = senaraiDokumenFolder;
    }

    public PermohonanRujukanLuar getPermohonanRujukanLuar() {
        return permohonanRujukanLuar;
    }

    public void setPermohonanRujukanLuar(PermohonanRujukanLuar permohonanRujukanLuar) {
        this.permohonanRujukanLuar = permohonanRujukanLuar;
    }
}
