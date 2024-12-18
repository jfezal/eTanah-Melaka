/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.stripes;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.dao.DokumenDAO;
import etanah.dao.KodTujuanUkurDAO;
import etanah.dao.PermohonanDAO;
import etanah.model.Dokumen;
import etanah.model.HakmilikPermohonan;
import etanah.model.InfoAudit;
import etanah.model.IntegrasiPermohonan;
import etanah.model.IntegrasiPermohonanButir;
import etanah.model.IntegrasiPermohonanDokumen;
import etanah.model.KodTujuanUkur;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.model.PermohonanSkim;
import etanah.model.PermohonanStrata;
import etanah.model.strata.BadanPengurusan;
import etanah.service.StrataPtService;
import etanah.util.DMSUtil;
import etanah.util.FileUtil;
import etanah.view.dokumen.FolderAction;
import etanah.view.etanahActionBeanContext;
import etanah.view.utility.DocumentUtilityAction;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ErrorResolution;
import net.sourceforge.stripes.action.FileBean;
import net.sourceforge.stripes.action.RedirectResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 *
 * @author fikri
 */
@UrlBinding("/muatnaik")
public class MuatnaikActionBean extends AbleActionBean {

    FileBean fileToBeUpload;
    @Inject
    KodTujuanUkurDAO kodTujuanUkurDAO;
    @Inject
    StrataPtService strService;
    @Inject
    etanah.Configuration conf;
    @Inject
    private DokumenDAO dokumenDAO;
    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;
    @Inject
    PermohonanDAO permohonanDAO;
    private static Logger LOG = Logger.getLogger(MuatnaikActionBean.class);
    private Permohonan permohonan;
    private HakmilikPermohonan hp;

    public FileBean getFileToBeUpload() {
        return fileToBeUpload;
    }

    public void setFileToBeUpload(FileBean fileToBeUpload) {
        this.fileToBeUpload = fileToBeUpload;
    }

    @DefaultHandler
    public Resolution muatNaikForm() {

        if (getContext().getRequest().getParameter("prm") != null) {
            getContext().getRequest().setAttribute("prm", getContext().getRequest().getParameter("prm"));
        }

        String folderId = getContext().getRequest().getParameter("folderId");
        String dokumenId = getContext().getRequest().getParameter("dokumenId");
        String idPermohonan = getContext().getRequest().getParameter("idPermohonan");

        if (StringUtils.isNotBlank(folderId)) {
            getContext().getRequest().setAttribute("folderId", folderId);
        } else {
            return new ErrorResolution(404, "Folder tidak ditentukan.");
        }

        if (StringUtils.isNotBlank(dokumenId)) {
            getContext().getRequest().setAttribute("dokumenId", dokumenId);
        } else {
            return new ErrorResolution(404, "Dokumen tidak ditentukan.");
        }

        if (StringUtils.isNotBlank(idPermohonan)) {
            getContext().getRequest().setAttribute("idPermohonan", idPermohonan);
        } else {
            return new ErrorResolution(404, "Tiada Id Permohonan.");
        }
        return new JSP("strata/utiliti/muat_naik_dokumen.jsp").addParameter("popup", "true");
    }

    public Resolution processUpload() {
        LOG.info("simpanMuatNaik::start");

        String dokumenId = getContext().getRequest().getParameter("dokumenId");

        String folderId = getContext().getRequest().getParameter("folderId");

        String idPermohonan = getContext().getRequest().getParameter("idPermohonan");

        Pengguna pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);

        if (fileToBeUpload != null) {
            try {
                String fileName = fileToBeUpload.getFileName();

                String contentType = fileToBeUpload.getContentType();

                LOG.debug("content type = " + contentType);

                Permohonan permohonan = permohonanDAO.findById(idPermohonan);

                DMSUtil dmsUtil = new DMSUtil();

                if (permohonan != null) {

                    FileUtil fileUtil = new FileUtil(dmsUtil.getDMSPath(permohonan));
                    fileUtil.saveFile(fileName, fileToBeUpload.getInputStream());
                    String fizikalPath = dmsUtil.getFizikalPath(permohonan) + File.separator + fileName;
                    updatePathDokumen(fizikalPath, Long.parseLong(dokumenId), contentType);

                } else {
                    addSimpleMessage("Muat naik tidak berjaya.");
                    if (getContext().getRequest().getParameter("prm") != null) {
                        getContext().getRequest().setAttribute("prm", getContext().getRequest().getParameter("prm"));
                        getContext().getRequest().setAttribute("idPermohonan", getContext().getRequest().getParameter("idPermohonan"));
                    }
                    return new JSP("dokumen/muat_naik.jsp").addParameter("popup", "true");
                }
            } catch (Exception ex) {
                LOG.error(ex.getMessage(), ex);
                addSimpleMessage("Muat naik tidak berjaya.");
                if (getContext().getRequest().getParameter("prm") != null) {
                    getContext().getRequest().setAttribute("prm", getContext().getRequest().getParameter("prm"));
                    getContext().getRequest().setAttribute("idPermohonan", getContext().getRequest().getParameter("idPermohonan"));
                }
                return new JSP("strata/utiliti/muat_naik_dokumen.jsp").addParameter("popup", "true");
            }
        }

        //insert data here - strata use only
        if (conf.getKodNegeri().equals("05") || conf.getKodNegeri().equals("04")) {
            InfoAudit infoAudit = new InfoAudit();
            Permohonan permohonan = permohonanDAO.findById(idPermohonan);
            try {
                if (permohonan.getKodUrusan().getKod().equalsIgnoreCase("P8")
                        || permohonan.getKodUrusan().getKod().equalsIgnoreCase("P22A")
                        || permohonan.getKodUrusan().getKod().equalsIgnoreCase("RTHS")
                        || permohonan.getKodUrusan().getKod().equalsIgnoreCase("RTPS")
                        || permohonan.getKodUrusan().getKod().equalsIgnoreCase("P40A")) {

                    Dokumen d = strService.findDok(permohonan.getIdPermohonan(), "PLKTA");
                    PermohonanSkim mohonSkim = null;
                    if(d != null && d.getKodDokumen().getKod().equals("PLKTA")){
                    mohonSkim = this.readXMLForMohonSkim(d);
                    hp = strService.findMohonHakmilik(idPermohonan);                    
                    //PermohonanSkim mohonSkim = strService.findIDSkimByIDMH(hp.getId());
                    PermohonanStrata mohonStrata = strService.findID(idPermohonan);
                    if (mohonStrata != null) {
                    } else {
                        mohonStrata = new PermohonanStrata();
                        mohonStrata.setPermohonan(permohonan);
                        mohonStrata.setCawangan(permohonan.getCawangan());
                        mohonStrata.setNama("-");
                        mohonStrata.setPemilikNama("-");
                    }
                    mohonStrata.setPermohonan(permohonan);
                    infoAudit.setDimasukOleh(pengguna);
                    infoAudit.setTarikhMasuk(new java.util.Date());
                    mohonStrata.setInfoAudit(infoAudit);
                    //mohonStrata.setCawangan(mohonSkim.setCawangan(peng.getKodCawangan()););
                    mohonStrata.setNama(mohonSkim.getNamaProjek());
                    //mohonStrata.setPemilikNama("-");
                    mohonStrata.setNamaSkim(mohonSkim.getNoSkim());
                    strService.simpanPemilik(mohonStrata);
                    }

                    if (conf.getKodNegeri().equals("05")) {
                        /*saving mohon_integ*/
                        LOG.info("---saving in mohon_integ---");
                        IntegrasiPermohonan integrasiPermohonan = strService.findIDMohon(idPermohonan);
                        if (integrasiPermohonan == null) {
                            integrasiPermohonan = new IntegrasiPermohonan();
                            infoAudit.setDimasukOleh(pengguna);
                            infoAudit.setTarikhMasuk(new java.util.Date());
                        } else {
                            infoAudit.setDiKemaskiniOleh(pengguna);
                            infoAudit.setTarikhKemaskini(new java.util.Date());
                        }

                        integrasiPermohonan.setPermohonan(permohonan);
                        integrasiPermohonan.setKodUrusan(permohonan.getKodUrusan());
                        integrasiPermohonan.setIdAliran("g_sedialaporan"); //dapat dari mana?
                        integrasiPermohonan.setIdAliranTerima("semaklaporan"); //dapat dari mana? buat mana ni hardcode dulu
                        integrasiPermohonan.setInfoAudit(infoAudit);
                        integrasiPermohonan = strService.simpanMohonInteg(integrasiPermohonan);

                        /*saving mohon_integ_butir*/
                        LOG.info("---saving in mohon_integ_butir---");
                        IntegrasiPermohonanButir integrasiPermohonanButir = strService.findByIDIntegIntegrasiPermohonanButir(integrasiPermohonan.getIdInteg());
                        if (integrasiPermohonanButir == null) {
                            integrasiPermohonanButir = new IntegrasiPermohonanButir();
                            infoAudit.setDimasukOleh(pengguna);
                            infoAudit.setTarikhMasuk(new java.util.Date());
                        } else {
                            infoAudit.setDiKemaskiniOleh(pengguna);
                            infoAudit.setTarikhKemaskini(new java.util.Date());
                        }
                        integrasiPermohonanButir.setIntegrasiPermohonan(integrasiPermohonan);
                        integrasiPermohonanButir.setNamaFolderHantar(integrasiPermohonan.getIdAliran());
                        integrasiPermohonanButir.setNamaFolderTerima(integrasiPermohonan.getIdAliranTerima());
                        integrasiPermohonanButir.setInfoAudit(infoAudit);
                        integrasiPermohonanButir = strService.simpanMohonIntegButir(integrasiPermohonanButir);

                        /*saving mohon_integ_dokumen*/
                        LOG.info("---saving in mohon_integ_dokumen---");
                        IntegrasiPermohonanDokumen integrasiPermohonanDokumen = strService.findByIDButirIntegrasiPermohonanDokumen(integrasiPermohonanButir.getIdButir());
                        if (integrasiPermohonanDokumen == null) {
                            integrasiPermohonanDokumen = new IntegrasiPermohonanDokumen();
                            infoAudit.setDimasukOleh(pengguna);
                            infoAudit.setTarikhMasuk(new java.util.Date());
                        } else {
                            infoAudit.setDiKemaskiniOleh(pengguna);
                            infoAudit.setTarikhKemaskini(new java.util.Date());
                        }
                        integrasiPermohonanDokumen.setIntegrasiPermohonanButir(integrasiPermohonanButir);
                        integrasiPermohonanDokumen.setKodDokumen(d.getKodDokumen()); //dapat dari mana?
                        integrasiPermohonanDokumen.setNamaFizikal(d.getNamaFizikal()); //dapat dari mana?
                        integrasiPermohonanDokumen.setInfoAudit(infoAudit);
                        integrasiPermohonanDokumen = strService.simpanMohonIntegDokumen(integrasiPermohonanDokumen);
                    }
                }
            } catch (ParserConfigurationException ex) {
                java.util.logging.Logger.getLogger(MuatnaikActionBean.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SAXException ex) {
                java.util.logging.Logger.getLogger(MuatnaikActionBean.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                java.util.logging.Logger.getLogger(MuatnaikActionBean.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ParseException ex) {
                java.util.logging.Logger.getLogger(MuatnaikActionBean.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        Logger.getLogger(MuatnaikActionBean.class).info("simpanMuatNaik::finish");
        addSimpleMessage("Muat naik fail berjaya.");
        if (getContext().getRequest().getParameter("prm") != null) {
            getContext().getRequest().setAttribute("prm", getContext().getRequest().getParameter("prm"));
            getContext().getRequest().setAttribute("idPermohonan", getContext().getRequest().getParameter("idPermohonan"));
        }
//        return new RedirectResolution(FolderAction.class).addParameter("permohonan.idPermohonan", idPermohonan);
        return new JSP("strata/utiliti/muat_naik_dokumen.jsp").addParameter("popup", "true");
    }

    private void updatePathDokumen(String namaFizikal, Long idDokumen, String format) {
        Dokumen d = dokumenDAO.findById(idDokumen);
        d.setNamaFizikal(namaFizikal);
        d.setFormat(format);
        Transaction tx = sessionProvider.get().getTransaction();
        tx.begin();
        d = dokumenDAO.saveOrUpdate(d);
        if (d != null) {
            tx.commit();
        } else {
            tx.rollback();
        }
    }

    public Resolution reload() {
        String idPermohonan = getContext().getRequest().getParameter("idPermohonan");
        if (StringUtils.isNotBlank(getContext().getRequest().getParameter("prm"))) {
            return new RedirectResolution(DocumentUtilityAction.class, "searchPermohonan").addParameter("idPermohonan", idPermohonan).addParameter("popup", "true");
        } else {
            return new RedirectResolution(FolderAction.class, "reload").addParameter("permohonan.idPermohonan", idPermohonan);
        }
    }

    private PermohonanSkim readXMLForMohonSkim(Dokumen d) throws ParserConfigurationException, SAXException, IOException, ParseException {
        String docPath = conf.getProperty("document.path");
        String idPermohonan = getContext().getRequest().getParameter("idPermohonan");
        Document doc = null;
        PermohonanSkim mohonSkim = null;
        BadanPengurusan mc;
        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        InfoAudit infoAudit = new InfoAudit();
        infoAudit.setDimasukOleh(peng);
        infoAudit.setTarikhMasuk(new java.util.Date());

        File f = new File(docPath + (docPath.endsWith(File.separator) ? "" : File.separator) + d.getNamaFizikal());
        LOG.info(f);
        LOG.info(d.getNamaFizikal());
        try {
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            doc = db.parse(f);

            permohonan = permohonanDAO.findById(idPermohonan);

            NodeList n1 = doc.getElementsByTagName("Scheme");
            NodeList n2 = doc.getElementsByTagName("Block");
            NodeList n3 = doc.getElementsByTagName("Tingkat");
            NodeList n4 = doc.getElementsByTagName("Petak");
            NodeList n5 = doc.getElementsByTagName("Ruang");
            NodeList n6 = doc.getElementsByTagName("Aksesori");
            NodeList n7 = doc.getElementsByTagName("CommonArea");

            String diukur_oleh,
                    pengukur_noic,
                    ulasan_jupem,
                    nama_pemaju,
                    nama_perbadanan_pengurusan,
                    alamat_perbadanan_pengurusan,
                    nama_projek,
                    no_ruj_jubl,
                    no_ruj_ljt,
                    no_fail_ukursemula,
                    kodtujuanukur, no_fail_ukur,
                    skim;
            int bilangan_petak;
            int bilangan_aksesori;
            Date tarikh_terima_sijil_akuan;
            Date tarikh_lulus_permohonan;
            Date tarikh_siap;
            BigDecimal bayaran_ukur;
            BigDecimal bayaran_pelan;

//                BigDecimal jumlah_unit_syer;
            //added
            BigDecimal jumlah_unit_syer = new BigDecimal(0);

            for (int i = 0; i < n1.getLength(); i++) {
                mohonSkim = new PermohonanSkim();
                KodTujuanUkur kodTujuanUkur = new KodTujuanUkur();
                diukur_oleh = (n1.item(i).getAttributes().getNamedItem("diukur_oleh").getNodeValue());
                pengukur_noic = (n1.item(i).getAttributes().getNamedItem("pengukur_noic").getNodeValue());
                ulasan_jupem = (n1.item(i).getAttributes().getNamedItem("ulasan_jupem").getNodeValue());
                nama_pemaju = (n1.item(i).getAttributes().getNamedItem("nama_pemaju").getNodeValue());
                nama_perbadanan_pengurusan = (n1.item(i).getAttributes().getNamedItem("nama_perbadanan_pengurusan").getNodeValue());
                alamat_perbadanan_pengurusan = (n1.item(i).getAttributes().getNamedItem("alamat_perbadanan_pengurusan").getNodeValue());
                nama_projek = (n1.item(i).getAttributes().getNamedItem("nama_projek").getNodeValue());
                no_ruj_jubl = (n1.item(i).getAttributes().getNamedItem("no_ruj_jubl").getNodeValue());
                no_ruj_ljt = (n1.item(i).getAttributes().getNamedItem("no_ruj_ljt").getNodeValue());
                no_fail_ukur = (n1.item(i).getAttributes().getNamedItem("no_fail_ukursemula").getNodeValue());
                no_fail_ukursemula = (n1.item(i).getAttributes().getNamedItem("no_fail_ukur").getNodeValue());
                kodtujuanukur = (n1.item(i).getAttributes().getNamedItem("kodtujuanukur").getNodeValue());
                skim = (n1.item(i).getAttributes().getNamedItem("skim").getNodeValue());
                bilangan_petak = Integer.valueOf(n1.item(i).getAttributes().getNamedItem("bilangan_petak").getNodeValue());
                bilangan_aksesori = Integer.valueOf(n1.item(i).getAttributes().getNamedItem("bilangan_aksesori").getNodeValue());
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                tarikh_terima_sijil_akuan = sdf.parse((n1.item(i).getAttributes().getNamedItem("tarikh_terima_sijil_akuan").getNodeValue()));
                tarikh_lulus_permohonan = sdf.parse(n1.item(i).getAttributes().getNamedItem("tarikh_lulus_permohonan").getNodeValue());
                tarikh_siap = sdf.parse(n1.item(i).getAttributes().getNamedItem("tarikh_siap").getNodeValue());
                if (!StringUtils.isEmpty(n1.item(i).getAttributes().getNamedItem("bayaran_ukur").getNodeValue())) {
                    bayaran_ukur = BigDecimal.valueOf(Double.valueOf(n1.item(i).getAttributes().getNamedItem("bayaran_ukur").getNodeValue()));
                }

                if (!StringUtils.isEmpty(n1.item(i).getAttributes().getNamedItem("bayaran_pelan").getNodeValue())) {
                    bayaran_pelan = BigDecimal.valueOf(Double.valueOf(n1.item(i).getAttributes().getNamedItem("bayaran_pelan").getNodeValue()));
                }

                if (!StringUtils.isEmpty(n1.item(i).getAttributes().getNamedItem("jumlah_unit_syer").getNodeValue())) {
                    jumlah_unit_syer = BigDecimal.valueOf(Double.valueOf(n1.item(i).getAttributes().getNamedItem("jumlah_unit_syer").getNodeValue()));
                }
                if (!n1.item(i).getAttributes().getNamedItem("bayaran_ukur").getNodeValue().equals("")) {
                    bayaran_ukur = BigDecimal.valueOf(Double.valueOf(n1.item(i).getAttributes().getNamedItem("bayaran_ukur").getNodeValue()));
                }
                if (!n1.item(i).getAttributes().getNamedItem("bayaran_pelan").getNodeValue().equals("")) {
                    bayaran_pelan = BigDecimal.valueOf(Double.valueOf(n1.item(i).getAttributes().getNamedItem("bayaran_pelan").getNodeValue()));
                }
                if (!n1.item(i).getAttributes().getNamedItem("jumlah_unit_syer").getNodeValue().equals("")) {
                    jumlah_unit_syer = BigDecimal.valueOf(Double.valueOf(n1.item(i).getAttributes().getNamedItem("jumlah_unit_syer").getNodeValue()));
                }

                mohonSkim.setCawangan(peng.getKodCawangan());
                mohonSkim.setInfoAudit(infoAudit);
                mohonSkim.setNamaPemaju(nama_pemaju);
                mohonSkim.setNamaProjek(nama_projek);
                mohonSkim.setDiukur(diukur_oleh);
                mohonSkim.setNoKpPengukur(pengukur_noic);
                mohonSkim.setNoFailUkur(no_ruj_ljt);
                mohonSkim.setNoSkim(getIntegerValue1(skim));
                mohonSkim.setNoRujJubl(no_ruj_jubl);
                mohonSkim.setNoFailUkurSemula(no_fail_ukursemula);
                mohonSkim.setNoFailPt(no_fail_ukur);
                //added 16-05-2012 by murali                    
                mohonSkim.setJumlahUnitSyer(jumlah_unit_syer.longValue());
                kodTujuanUkur = kodTujuanUkurDAO.findById(kodtujuanukur);
                mohonSkim.setKodTujuanUkur(kodTujuanUkur);//
                if (!StringUtils.isEmpty(String.valueOf(bilangan_petak))) {
                    mohonSkim.setBilPetak(Long.parseLong(String.valueOf(bilangan_petak)));
                }
                if (!StringUtils.isEmpty(String.valueOf(bilangan_aksesori))) {

                    mohonSkim.setBilAksr(Long.parseLong(String.valueOf(bilangan_aksesori)));
                }
                mohonSkim.setTrhSiap(tarikh_siap);
                mohonSkim.setTrhLulusCf(tarikh_terima_sijil_akuan);
                mohonSkim.setHakmilikPermohonan(permohonan.getSenaraiHakmilik().get(0));//added by murali
                mohonSkim = strService.saveSkim(mohonSkim);//added by murali
            }

            return mohonSkim;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    public String getIntegerValue1(String s) {
        String remove = "(S)";
        int i;
        String result = "";
        i = s.indexOf(remove);
        if (i != -1) {
            result = s.substring(0, i);
            result = result + s.substring(i + remove.length());
            s = result;
        }
        return s;
    }
}
