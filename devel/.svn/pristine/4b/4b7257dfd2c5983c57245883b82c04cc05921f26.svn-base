package etanah.view.daftar.utiliti;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.dao.DokumenDAO;
import etanah.dao.FolderDokumenDAO;
import etanah.dao.KandunganFolderDAO;
import etanah.dao.KodDokumenDAO;
import etanah.dao.KodKlasifikasiDAO;
import etanah.dao.PermohonanCarianDAO;
import etanah.dao.PermohonanDAO;
import etanah.dao.HakmilikDAO;
import etanah.model.CarianHakmilik;
import etanah.model.Dokumen;
import etanah.model.Hakmilik;
import etanah.model.DokumenKewangan;
import etanah.model.FolderDokumen;
import etanah.model.InfoAudit;
import etanah.model.KandunganFolder;
import etanah.model.KodDokumen;
import etanah.model.KodUrusan;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.model.PermohonanCarian;
import etanah.report.ReportUtil;
import etanah.service.common.KandunganFolderService;
import etanah.view.etanahActionBeanContext;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import etanah.service.common.DokumenService;



/**
 *
 * @author fikri
 */
@UrlBinding("/daftar/cetak_salinan_sah")
public class CetakSalinanSahAction extends AbleActionBean {

    private String idCarian;
    private PermohonanCarian permohonanCarian;
    private List<Dokumen> senaraiDokumen;
    private List<Dokumen> senaraiDokumenCarian;
    private static final Logger LOG = Logger.getLogger(CetakSalinanSahAction.class);

    @Inject PermohonanCarianDAO carianDAO;
    @Inject PermohonanDAO permohonanDAO;
    @Inject
    private KodDokumenDAO kodDokumenDAO;
    @Inject
    private ReportUtil reportUtil;
    @Inject
    DokumenDAO dokumenDAO;
    @Inject
    HakmilikDAO hakmilikDAO;
    @Inject
    private KodKlasifikasiDAO kodKlasifikasiDAO;
    @Inject
    private etanah.Configuration conf;
    @Inject
    FolderDokumenDAO folderDokumenDAO;
    @Inject
    KandunganFolderDAO kandunganFolderDAO;
    @Inject
    KandunganFolderService kandunganFolderService;
    @Inject
    DokumenService dokumenService;
   
    
    private static final boolean debug = LOG.isDebugEnabled();

    @DefaultHandler
    public Resolution showForm() {
        return new JSP("daftar/utiliti/cetak_salinan_sah.jsp");
    }

    public Resolution searchCarian () {
        
        etanahActionBeanContext ctx = (etanahActionBeanContext) getContext();
        Pengguna pengguna = ctx.getUser();

        if(StringUtils.isNotBlank(idCarian)) {
            permohonanCarian = carianDAO.findById(idCarian);
            if (permohonanCarian == null) {
                addSimpleError("Permohonan Carian Tidak Dijumpai.");
            } else {
                senaraiDokumen = new ArrayList<Dokumen> ();                
                if (permohonanCarian.getUrusan().getKod().equals("SSDOK") 
                        || permohonanCarian.getUrusan().getKod().equals("SSSC")
                        || permohonanCarian.getUrusan().getKod().equals("SSSW")) {
                    List<CarianHakmilik> list = permohonanCarian.getSenaraiHakmilik();
                    for (CarianHakmilik ch : list) {
                        if (ch == null || StringUtils.isBlank(ch.getIdPerserahan())) continue;
                        String idPerserahan = ch.getIdPerserahan();
                        Permohonan mohon = permohonanDAO.findById(idPerserahan);
                        if (mohon == null) continue;
                        List<KandunganFolder> senaraiKandungan = mohon.getFolderDokumen().getSenaraiKandungan();
                        for (KandunganFolder kf : senaraiKandungan) {
                            if (kf == null || kf.getDokumen() == null) continue;
                            KodDokumen kd = kf.getDokumen().getKodDokumen();
                            if (permohonanCarian.getUrusan().getKod().equals("SSSW")) {                              
                                if (!kd.getKod().equals("SW") && !kd.getKod().equals("SKW")) continue;
                            } else {
                                if ( kd == null || kd.getKod().equals("VDOC")
                                    || kd.getKod().equals("DHKE") || kd.getKod().equals("DHDE")) continue;
                            }
                            senaraiDokumen.add(kf.getDokumen());
                        }                        
                    }
                } else if (permohonanCarian.getUrusan().getKod().equals("SSLSC")
                        || permohonanCarian.getUrusan().getKod().equals("SSHMK")) {
                    List<KandunganFolder> senaraiKandungan = permohonanCarian.getFolderDokumen().getSenaraiKandungan();
                    for (KandunganFolder kf : senaraiKandungan) {
                        senaraiDokumen.add(kf.getDokumen());
                    }
                }else if (permohonanCarian.getUrusan().getKod().equals("SSHM")){
                    KandunganFolder kandunganFolder = new KandunganFolder();
                    KodDokumen kodDokumen = null;
                    String nameOfReport = "";
                    Date now = new Date();
                    InfoAudit ia = new InfoAudit();
                    ia.setDimasukOleh(pengguna);
                    ia.setTarikhMasuk(now);
                    String documentPath = conf.getProperty("document.path");

                    List<CarianHakmilik> list = permohonanCarian.getSenaraiHakmilik();
                    KodUrusan kodUrusan = permohonanCarian.getUrusan();
                    boolean PernahJana = false;
                    int count = 0;

                    for (CarianHakmilik ch2 : list) {
                        List<KandunganFolder> senaraiKandungan = permohonanCarian.getFolderDokumen().getSenaraiKandungan();   
                        if (count == 0) {
                            for (KandunganFolder kf : senaraiKandungan) {
                                if (kf.getCatatan() != null) {
                                    if (kf.getCatatan().equalsIgnoreCase("SSHM Pernah Dijana")) {
                                        PernahJana = true;
                                    }
                                }
                            }
                        }
                        
                        if (ch2 == null) {
                            continue;
                        }
                        String idHakmilik = ch2.getIdHakmilik();
                        if (StringUtils.isBlank(idHakmilik)) {
                            continue;
                        }
                        Hakmilik hakmilik = hakmilikDAO.findById(idHakmilik);
                        if (hakmilik == null) {
                            continue;
                        }

                        String[] Kod_Hakmilik_FT = {
                            "GRN",
                            "GM",
                            "PM",
                            "PN",
                            "GMM"
                        };

                        kodDokumen = kodDokumenDAO.findById("SDH");
                        if ("04".equals(conf.getProperty("kodNegeri"))) {
                            nameOfReport = "rSalinanDHDEml.rdf";
                            if (hakmilik.getIdHakmilikInduk() != null) {
                                nameOfReport = "regSalinanSah4K_MLK.rdf";
                            }
                        } else {
                            nameOfReport = "regSalinanDHDE.rdf";
                            if (hakmilik.getIdHakmilikInduk() != null) {
                                nameOfReport = "regSalinanSah4K_NS.rdf";
                            }
                        }
                             LOG.info("---------- PernahJana : " + PernahJana);
                            if(PernahJana==false){
                                generateReport3(kodUrusan, kodDokumen, ia, documentPath, nameOfReport, permohonanCarian.getIdCarian(), idHakmilik, permohonanCarian.getFolderDokumen(),pengguna.getIdPengguna());
                            }

                        if (hakmilik.getIdHakmilikInduk() == null) {
                            if ("04".equals(conf.getProperty("kodNegeri"))) {
                                if (ArrayUtils.contains(Kod_Hakmilik_FT, hakmilik.getKodHakmilik().getKod())) {
                                    nameOfReport = "RegCR_SshmB1eMLK_DHDe.rdf";
                                    kodDokumen = kodDokumenDAO.findById("PB1DE");

                                } else {
                                    nameOfReport = "RegCR_SshmB2eMLK_DHDe.rdf";
                                    kodDokumen = kodDokumenDAO.findById("PB2DE");
                                }
                            } else if (ArrayUtils.contains(Kod_Hakmilik_FT, hakmilik.getKodHakmilik().getKod())) {
                                nameOfReport = "regCRBorangB1eNS.rdf";
                                kodDokumen = kodDokumenDAO.findById("PB1DE");

                            } else {
                                nameOfReport = "regCRBorangB2eNS.rdf";
                                kodDokumen = kodDokumenDAO.findById("PB2DE");
                            }

                            if(PernahJana==false){ 
                                generateReport3(kodUrusan, kodDokumen, ia, documentPath, nameOfReport, permohonanCarian.getIdCarian(), idHakmilik, permohonanCarian.getFolderDokumen(),pengguna.getIdPengguna());                                
                            }    
                        }
                        
                        count++;
                    }
                    
                     List<KandunganFolder> senaraiKandungan2 = permohonanCarian.getFolderDokumen().getSenaraiKandungan();

                    for (KandunganFolder kf : senaraiKandungan2) {
                        senaraiDokumen.add(kf.getDokumen());
                    }
                }
            }
        }
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Date today = new Date();
        getContext().getRequest().setAttribute("today", sdf.format(today));
        return new JSP("daftar/utiliti/cetak_salinan_sah.jsp");
    }
    
     private List<KandunganFolder> generateReport(KodUrusan kodUrusan, KodDokumen kodDokumen,
                      InfoAudit ia, String documentPath,
                      String reportName, PermohonanCarian pc, DokumenKewangan dk) {
                if (debug) {
                  LOG.debug("generateReport");
                }
                String parameterToPass = "";
                String valueToPass = "";
                String idPgunaParam = "p_id_pguna";
                String idPgunaValue = ia.getDimasukOleh().getIdPengguna();
                
                List<KandunganFolder> senaraiKF = new ArrayList<KandunganFolder>();

                List<CarianHakmilik> list = pc.getSenaraiHakmilik();
                LOG.info("---------- senarai hakmilik : " + pc.getSenaraiHakmilik());

                for (CarianHakmilik ch : list) {
                  if (ch == null) {
                    continue;
                  }
                  LOG.info("--ch :: id hakmilik : " + ch.getIdHakmilik());
                  if (StringUtils.isNotBlank(ch.getIdPerserahan())) {
                    parameterToPass = "p_id_mohon";
                    valueToPass = ch.getIdPerserahan();
                  } else if (StringUtils.isNotBlank(ch.getIdHakmilik())) {
                    parameterToPass = "p_id_hakmilik";
                    valueToPass = ch.getIdHakmilik();  
                  }

                  FolderDokumen fd = pc.getFolderDokumen();
                  Dokumen dokumenCarian = new Dokumen();
                  dokumenCarian.setFormat("application/pdf");
                  dokumenCarian.setInfoAudit(ia);
                  dokumenCarian.setKlasifikasi(kodKlasifikasiDAO.findById(3)); //sulit
                  dokumenCarian.setKodDokumen(kodDokumen);
                  dokumenCarian.setNoVersi("1.0");
                  dokumenCarian.setTajuk(kodDokumen.getNama() + "-" + valueToPass);
                  dokumenDAO.save(dokumenCarian);
                  KandunganFolder kf1 = new KandunganFolder();
                  kf1.setFolder(fd);
                  LOG.info("---- pc.getFolderDokumen()---- : " + pc.getFolderDokumen().getFolderId());
                  kf1.setDokumen(dokumenCarian);
                  LOG.info("id_dokumen : " + dokumenCarian.getIdDokumen());
                  kf1.setInfoAudit(ia);
                  String path = documentPath + (documentPath.endsWith(File.separator) ? "" : File.separator)
                          + String.valueOf(dokumenCarian.getIdDokumen());
                  LOG.info("---- valueToPass ---- : " + valueToPass);
                  reportUtil.generateReport(reportName,
                          new String[]{parameterToPass, "p_carian","p_id_mohon", "p_resit", "p_trans", idPgunaParam},
                          new String[]{valueToPass, pc.getIdCarian(), pc.getIdCarian(),dk.getIdDokumenKewangan(), String.valueOf(pc.getTrans().getIdTransaksi()), idPgunaValue},
                          path, ia.getDimasukOleh());

                  dokumenCarian.setNamaFizikal(reportUtil.getDMSPath());
                  dokumenCarian.setFormat(reportUtil.getContentType());
                  dokumenDAO.update(dokumenCarian);
                  fd.getSenaraiKandungan().add(kf1);
                  folderDokumenDAO.save(fd);
                  kandunganFolderService.save(kf1);
                  senaraiKF.add(kf1);
                }
                return senaraiKF;
              }           
    
       private KandunganFolder generateReport3(KodUrusan kodUrusan, KodDokumen kodDokumen,
            InfoAudit ia, String documentPath,
            String reportName, String idCarian, String idHakmilik, FolderDokumen fd, String idPguna) {
        if (debug) {
            LOG.debug("generateReport");
        }

        String[] params = null;
        String[] values = null;

        KandunganFolder kFolder = new KandunganFolder();


        params = new String[]{"p_id_mohon", "p_id_hakmilik", "p_id_pguna"};
        values = new String[]{idCarian, idHakmilik, idPguna};

        Dokumen dokumenCarian = new Dokumen();
        dokumenCarian.setFormat("application/pdf");
        dokumenCarian.setInfoAudit(ia);
        dokumenCarian.setKlasifikasi(kodKlasifikasiDAO.findById(1));
        dokumenCarian.setKodDokumen(kodDokumen);
        dokumenCarian.setNoVersi("1.0");
        dokumenCarian.setTajuk(kodDokumen.getNama() + "(" + idHakmilik + ")");
        dokumenDAO.save(dokumenCarian);
        kFolder.setFolder(fd);
        kFolder.setDokumen(dokumenCarian);
        kFolder.setCatatan("SSHM Pernah Dijana");
        kFolder.setInfoAudit(ia);
        String path = documentPath + (documentPath.endsWith(File.separator) ? "" : File.separator)
                + String.valueOf(dokumenCarian.getIdDokumen());
        
        reportUtil.generateReport(reportName,
                    params,
                    values,
                    path, ia.getDimasukOleh());

        dokumenCarian.setNamaFizikal(reportUtil.getDMSPath());
        dokumenDAO.update(dokumenCarian);
        fd.getSenaraiKandungan().add(kFolder);
        folderDokumenDAO.save(fd);
        kandunganFolderService.save(kFolder);

        return kFolder;
    }  
          
    public String getIdCarian() {
        return idCarian;
    }

    public void setIdCarian(String idCarian) {
        this.idCarian = idCarian;
    }

    public PermohonanCarian getPermohonanCarian() {
        return permohonanCarian;
    }

    public void setPermohonanCarian(PermohonanCarian permohonanCarian) {
        this.permohonanCarian = permohonanCarian;
    }

    public List<Dokumen> getSenaraiDokumen() {
        return senaraiDokumen;
    }

    public void setSenaraiDokumen(List<Dokumen> senaraiDokumen) {
        this.senaraiDokumen = senaraiDokumen;
    }

    public List<Dokumen> getSenaraiDokumenCarian() {
        return senaraiDokumenCarian;
    }

    public void setSenaraiDokumenCarian(List<Dokumen> senaraiDokumenCarian) {
        this.senaraiDokumenCarian = senaraiDokumenCarian;
    }
    

}
