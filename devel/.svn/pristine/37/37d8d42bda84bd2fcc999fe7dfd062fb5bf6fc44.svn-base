/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.stripes.consent;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.dao.PermohonanDAO;
import etanah.model.Dokumen;
import etanah.model.FasaPermohonan;
import etanah.model.HakmilikPermohonan;
import etanah.model.ImejLaporan;
import etanah.model.InfoAudit;
import etanah.model.KodDUN;
import etanah.model.KodKategoriTanah;
import etanah.model.KodPBT;
import etanah.model.KodPemilikan;
import etanah.model.LaporanTanah;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.model.PermohonanLaporanUlasan;
import etanah.service.ConsentPtdService;
import etanah.service.LaporanTanahService;
import etanah.util.FileUtil;
import etanah.view.etanahActionBeanContext;
import etanah.view.stripes.pelupusan.LaporanTanah4ActionBean;
import etanah.view.stripes.pelupusan.disClass.DisHakmilikPermohonan;
import etanah.view.stripes.pelupusan.disClass.DisLaporanTanahService;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.FileBean;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author muhammad.amin
 */
@UrlBinding("/consent/laporan_tanah_ladang")
public class LaporanTanahLadangActionBean extends AbleActionBean {

    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    LaporanTanahService laporanTanahService;
    @Inject
    ConsentPtdService consentService;
    @Inject
    DisLaporanTanahService disLaporanTanahService;
    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;
    private List<ImejLaporan> hakmilikImejLaporanList;
    private List<ImejLaporan> hakmilikImejLaporanListEdit;
    private Permohonan permohonan;
    private LaporanTanah laporanTanah;
    private HakmilikPermohonan hakmilikPermohonan;
    private PermohonanLaporanUlasan ulasanPertanian;
    private PermohonanLaporanUlasan ulasanTenagaKerja;
    private FasaPermohonan fasaPermohonan;
    private char pemilikan;
    private String jenisPenggunaanTanah;
    private String dun;
    private String pbt;
    private String idHakmilik;
    private String catatan;
    private String idLaporTanah;
    private FileBean fileToBeUpload;
    private char pandanganImej;
    private static final Logger LOG = Logger.getLogger(LaporanTanahLadangActionBean.class);
    etanahActionBeanContext ctx;

    @DefaultHandler
    public Resolution showForm() {
        LOG.info("Start Laporan Tanah");
        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        return new JSP("consent/laporan_tanah_ladang.jsp").addParameter("tab", "true");
    }

    public Resolution showForm2() {
        LOG.info("Start Laporan Tanah");
        return new JSP("consent/laporan_tanah_ladang.jsp").addParameter("tab", "true");

    }

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {

        ctx = (etanahActionBeanContext) getContext();
        idHakmilik = ctx.getRequest().getParameter("idHakmilik");
        LOG.info("hakmilik :" + idHakmilik);

        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");

        if (idPermohonan != null) {
            permohonan = permohonanDAO.findById(idPermohonan);

            if (permohonan.getKodUrusan().getKod().equals("PMJTL")) {
                if (StringUtils.isNotBlank(idHakmilik)) {
                    hakmilikPermohonan = consentService.findMohonHakmilikByIdH(idPermohonan, idHakmilik);
                    laporanTanah = consentService.findLaporanTanahByIdMH(idPermohonan, String.valueOf(hakmilikPermohonan.getId()));
                } else {
                    hakmilikPermohonan = consentService.findMohonHakmilikByIdH(idPermohonan, permohonan.getSenaraiHakmilik().get(0).getHakmilik().getIdHakmilik());
                    laporanTanah = consentService.findLaporanTanahByIdMH(idPermohonan, String.valueOf(permohonan.getSenaraiHakmilik().get(0).getId()));
                }
            } else {
                //KES RAYUAN
                if (StringUtils.isNotBlank(idHakmilik)) {
                    hakmilikPermohonan = consentService.findMohonHakmilikByIdH(permohonan.getPermohonanSebelum().getIdPermohonan(), idHakmilik);
                    laporanTanah = consentService.findLaporanTanahByIdMH(permohonan.getPermohonanSebelum().getIdPermohonan(), String.valueOf(hakmilikPermohonan.getId()));
                } else {
                    hakmilikPermohonan = consentService.findMohonHakmilikByIdH(permohonan.getPermohonanSebelum().getIdPermohonan(), permohonan.getPermohonanSebelum().getSenaraiHakmilik().get(0).getHakmilik().getIdHakmilik());
                    laporanTanah = consentService.findLaporanTanahByIdMH(permohonan.getPermohonanSebelum().getIdPermohonan(), String.valueOf(permohonan.getPermohonanSebelum().getSenaraiHakmilik().get(0).getId()));
                }
            }

            if (hakmilikPermohonan != null) {

                if (hakmilikPermohonan.getKodMilik() != null) {
                    pemilikan = hakmilikPermohonan.getKodMilik().getKod();
                }
                if (hakmilikPermohonan.getJenisPenggunaanTanah() != null) {
                    jenisPenggunaanTanah = hakmilikPermohonan.getJenisPenggunaanTanah().getKod();
                }
                if (hakmilikPermohonan.getKodDUN() != null) {
                    dun = hakmilikPermohonan.getKodDUN().getKod();
                }
                if (hakmilikPermohonan.getKodPbt() != null) {
                    pbt = hakmilikPermohonan.getKodPbt().getKod();
                }
            }

            if (laporanTanah != null) {
                hakmilikImejLaporanList = disLaporanTanahService.getLaporanTanahService().getHakmilikImejByLaporanId(laporanTanah.getIdLaporan());
                LOG.info("--find list imej edit");
                hakmilikImejLaporanListEdit = disLaporanTanahService.getLaporanTanahService().getHakmilikImejByLaporanId(laporanTanah.getIdLaporan());
            }

            //            hakmilikPermohonan = permohonan.getSenaraiHakmilik().get(0);
//            laporanTanah = consentService.findLaporanTanahByIdMH(idPermohonan, String.valueOf(hakmilikPermohonan.getId()));
            //            ulasanPertanian = consentService.findMohonUlasByJenisUlasan(idPermohonan, "PERTANIAN");
//            ulasanTenagaKerja = consentService.findMohonUlasByJenisUlasan(idPermohonan, "KERJA");
//            fasaPermohonan = consentService.findMohonFasaByStage(idPermohonan, "Stage4");
        }
    }

    public Resolution openFrame() {
        LOG.info(":::::: OPEN FRAME ::::::");
        ctx = (etanahActionBeanContext) getContext();
        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        Pengguna pguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        idHakmilik = ctx.getRequest().getParameter("idHakmilik");
        String forwardJSP = new String();
        String type = ctx.getRequest().getParameter("type");
        LOG.info(":: TYPE : " + type);
        permohonan = new Permohonan();
        permohonan = (Permohonan) disLaporanTanahService.findObject(permohonan, new String[]{idPermohonan}, 0);
        hakmilikPermohonan = new HakmilikPermohonan();

        HakmilikPermohonan mohonHM = new HakmilikPermohonan();
        DisHakmilikPermohonan disMohonHM = new DisHakmilikPermohonan();

        mohonHM = permohonan.getSenaraiHakmilik().get(0);
        disMohonHM.setHakmilikPermohonan(mohonHM);
        if (disMohonHM.getHakmilikPermohonan().getHakmilik() != null) {
            idHakmilik = disMohonHM.getHakmilikPermohonan().getHakmilik().getIdHakmilik();
            hakmilikPermohonan = disMohonHM.getHakmilikPermohonan();
        } else {
            hakmilikPermohonan = new HakmilikPermohonan();
            hakmilikPermohonan = (HakmilikPermohonan) disLaporanTanahService.findObject(hakmilikPermohonan, new String[]{idPermohonan}, 1);
        }

        if (hakmilikPermohonan != null) {

            laporanTanah = new LaporanTanah();
            laporanTanah = (LaporanTanah) disLaporanTanahService.findObject(laporanTanah, new String[]{String.valueOf(hakmilikPermohonan.getId())}, 0);
            idLaporTanah = String.valueOf(laporanTanah.getIdLaporan());
        }
        if (laporanTanah == null) {
            laporanTanah = new LaporanTanah();
            laporanTanah.setInfoAudit(disLaporanTanahService.findAudit(laporanTanah, "new", pguna));
            laporanTanah.setHakmilikPermohonan(hakmilikPermohonan);
            laporanTanah.setPermohonan(permohonan);
            disLaporanTanahService.getPlpservice().simpanLaporanTanah(laporanTanah);
            laporanTanah = new LaporanTanah();
            laporanTanah = (LaporanTanah) disLaporanTanahService.findObject(laporanTanah, new String[]{String.valueOf(hakmilikPermohonan.getId())}, 0);
        }

        if (StringUtils.isNotBlank(type)) {
            forwardJSP = refreshData(type);
        }
        return new JSP(forwardJSP).addParameter("popup", Boolean.TRUE);
    }

    public Resolution refreshpage() {
        //FOR UPLOAD IMAGE
        String typeImage = ctx.getRequest().getParameter("pandanganImej");
        idLaporTanah = ctx.getRequest().getParameter("idLapor");
        if (typeImage != null && !typeImage.isEmpty()) {
            pandanganImej = typeImage.charAt(0);
        }
        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        rehydrate();
        return new JSP(refreshData(ctx.getRequest().getParameter("type"))).addParameter("popup", Boolean.TRUE);
    }

    public String refreshData(String type) {
        LOG.info(":::::: REFRESH DATA ::::::");
        String forwardJSP = new String();
        LOG.info(":: ID LAPORAN TANAH : " + idLaporTanah);
        LOG.info(":: TYPE : " + type);

        if (type.equals("main")) {
            forwardJSP = "consent/laporan_tanah_ladang.jsp";
        } else if (type.equals("imgPopup")) {
            hakmilikImejLaporanListEdit = new ArrayList<ImejLaporan>();
            hakmilikImejLaporanListEdit = disLaporanTanahService.getLaporanTanahService().getHakmilikImejByLaporanId(Long.parseLong(idLaporTanah));
            forwardJSP = "consent/laporan_tanah_upload.jsp";
        } else {
            hakmilikImejLaporanListEdit = disLaporanTanahService.getLaporanTanahService().getHakmilikImejByLaporanId(Long.parseLong(idLaporTanah));
            forwardJSP = "consent/laporan_tanah_popup.jsp";
        }
        return forwardJSP;
    }

    public Resolution uploadDoc() {
        String idLapor = getContext().getRequest().getParameter("idLapor");
        idHakmilik = getContext().getRequest().getParameter("idHakmilik");
        laporanTanah = new LaporanTanah();
        laporanTanah = (LaporanTanah) disLaporanTanahService.findObject(laporanTanah, new String[]{idLapor}, 2);
        hakmilikImejLaporanListEdit = disLaporanTanahService.getLaporanTanahService().getHakmilikImejByLaporanId(Long.parseLong(idLapor));

        catatan = new String();
        return new JSP("consent/laporan_tanah_upload.jsp").addParameter("popup", "true");
    }

    public Resolution simpanImejLaporan() throws Exception {
        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        String forwardJSP = new String();
        if (idPermohonan != null) {
            permohonan = disLaporanTanahService.getPermohonanDAO().findById(idPermohonan);

            String[] tname = {"permohonan"};
            Object[] model = {permohonan};

            List<LaporanTanah> listLaporanTanah;

            listLaporanTanah = disLaporanTanahService.getLaporanTanahDAO().findByEqualCriterias(tname, model, null);

            if (!(listLaporanTanah.isEmpty())) {
                laporanTanah = listLaporanTanah.get(0);
            }

            if (laporanTanah == null) {
                laporanTanah = new LaporanTanah();
                laporanTanah.setInfoAudit(disLaporanTanahService.findAudit(laporanTanah, "new", peng));
                laporanTanah.setPermohonan(permohonan);
                laporanTanah = disLaporanTanahService.getLaporanTanahService().simpanLaporanTanah(laporanTanah);
            }

            if (listLaporanTanah.isEmpty()) {
                laporanTanah = new LaporanTanah();
                laporanTanah.setInfoAudit(disLaporanTanahService.findAudit(laporanTanah, "new", peng));
                laporanTanah.setPermohonan(permohonan);
                laporanTanah = disLaporanTanahService.getLaporanTanahService().simpanLaporanTanah(laporanTanah);
            }

            String dokumenPath = disLaporanTanahService.getConf().getProperty("document.path");
            dokumenPath = dokumenPath + (dokumenPath.endsWith(File.separator) ? "" : File.separator) + permohonan.getFolderDokumen().getFolderId();

            if (catatan == null) {
                addSimpleError("Sila masukkan Catatan.");
                return new JSP("consent/laporan_tanah_upload.jsp").addParameter("popup", "true");
            }

            if (fileToBeUpload == null) {
                addSimpleError("Please select file to be Upload.");
                return new JSP("consent/laporan_tanah_upload.jsp").addParameter("popup", "true");
            } else if (!(fileToBeUpload.getFileName().endsWith(".jpg") || fileToBeUpload.getFileName().endsWith(".bmp") || fileToBeUpload.getFileName().endsWith(".png"))) {
                addSimpleError("Please select valid Image.");
                return new JSP("consent/laporan_tanah_upload.jsp").addParameter("popup", "true");
            }
            if ((catatan != null) && (fileToBeUpload != null)) {
                ImejLaporan imejLaporan = new ImejLaporan();
                imejLaporan.setCawangan(permohonan.getCawangan());
                imejLaporan.setInfoAudit(disLaporanTanahService.findAudit(imejLaporan, "new", peng));
                Dokumen doc = disLaporanTanahService.getCommonService().saveDokumen(dokumenPath, fileToBeUpload, permohonan.getFolderDokumen().getFolderId(), imejLaporan.getInfoAudit(), permohonan);
                imejLaporan.setDokumen(doc);
                imejLaporan.setPandanganImej('H');
                imejLaporan.setCatatan(catatan);

                if (laporanTanah != null) {
                    imejLaporan.setLaporanTanah(laporanTanah);
                    if (laporanTanah.getHakmilikPermohonan() != null) {
                        if (laporanTanah.getHakmilikPermohonan().getHakmilik() != null) {
                            imejLaporan.setHakmilik(laporanTanah.getHakmilikPermohonan().getHakmilik());
                        }
                    }
                }

                disLaporanTanahService.getLaporanTanahService().simpanHakmilikImej(imejLaporan);
                InfoAudit ia = new InfoAudit();
                ia.setTarikhMasuk(new java.util.Date());
                ia.setDimasukOleh(peng);

                if (fileToBeUpload != null) {
                    try {
                        System.out.println("no null::" + fileToBeUpload.getContentType());
                        dokumenPath = dokumenPath + (dokumenPath.endsWith(File.separator) ? "" : File.separator) + permohonan.getFolderDokumen().getFolderId();
                        FileUtil fileUtil = new FileUtil(dokumenPath);
                        LOG.info("###### fileUtil :" + fileUtil.toString());
                        doc = disLaporanTanahService.getCommonService().saveDokumen(dokumenPath, fileToBeUpload, permohonan.getFolderDokumen().getFolderId(), ia, permohonan);
                        String dokumenId = String.valueOf(doc.getIdDokumen());
                        LOG.info("###### dokumenId :" + dokumenId);
                        fileUtil.saveFile(dokumenId, fileToBeUpload.getInputStream());
                        String fizikalPath = permohonan.getFolderDokumen().getFolderId() + File.separator + dokumenId;
                        LOG.info("###### fizikalPath :" + fizikalPath);

                        LOG.info("simpanMuatNaik::finish");
                        addSimpleMessage("Muat naik fail berjaya.");
                    } catch (Exception ex) {
                        Logger.getLogger(LaporanTanah4ActionBean.class).error(ex);
                    }
                } else {
                    addSimpleError("Sila pilih fail imej untuk dimuatnaik.");
                }

            }
            catatan = new String();
            idLaporTanah = String.valueOf(laporanTanah.getIdLaporan());
            forwardJSP = refreshData("imgPopup");
        }
        return new JSP(forwardJSP).addParameter("popup", "true");
    }

    public Resolution deleteImage() {
        LOG.info(":::::: DELETE IMAGE ::::::");
        Long idImej = Long.parseLong(getContext().getRequest().getParameter("idImej"));
        Long idDokumen = Long.parseLong(getContext().getRequest().getParameter("idDokumen"));
        String type = getContext().getRequest().getParameter("type");
        String imageType = getContext().getRequest().getParameter("imageType");
        idLaporTanah = getContext().getRequest().getParameter("idLapor");
        LOG.info(":: TYPE : " + type);
        LOG.info(":: ID LAPORAN TANAH : " + idLaporTanah);

        String forwardJSP = new String();
        if (!type.isEmpty()) {
            Session s = sessionProvider.get();
            Transaction tx = s.beginTransaction();
            tx.begin();
            try {
                disLaporanTanahService.delObject("laporanImej", new String[]{String.valueOf(idImej)}, new String());
                disLaporanTanahService.delObject("dokumen", new String[]{String.valueOf(idDokumen)}, new String());
                tx.commit();
                if (!imageType.isEmpty()) {
                    pandanganImej = imageType.charAt(0);
                }

                forwardJSP = refreshData(type);

            } catch (Exception ex) {
                tx.rollback();
                Throwable t = ex;
                // getting the root cause
                while (t.getCause() != null) {
                    t = t.getCause();
                }
                ex.printStackTrace();
                addSimpleError(t.getMessage());
            }
        } else {
            addSimpleError("Type Tidak Dijumpai. Sila Hubungi Pentadbir Sistem Untuk Maklumat Lanjut");
        }

        return new ForwardResolution("/WEB-INF/jsp/" + forwardJSP).addParameter("tab", "true");
    }

    public Resolution selectHakmilikEdit() {
        ctx.getRequest().setAttribute("edit", Boolean.TRUE);
        return showForm();

    }

    public Resolution selectHakmilik() {
        return showForm2();
    }

    public Resolution simpanLaporanTanah() {

        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        Pengguna pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        InfoAudit infoAudit = new InfoAudit();

        permohonan = permohonanDAO.findById(idPermohonan);

        HakmilikPermohonan hakmilikPermohonanTemp = consentService.findMohonHakmilikByIdH(idPermohonan, hakmilikPermohonan.getHakmilik().getIdHakmilik());

        if (hakmilikPermohonanTemp != null) {
            infoAudit = hakmilikPermohonanTemp.getInfoAudit();
            infoAudit.setDiKemaskiniOleh(pengguna);
            infoAudit.setTarikhKemaskini(new java.util.Date());
            hakmilikPermohonanTemp.setInfoAudit(infoAudit);

        } else {
            hakmilikPermohonanTemp = new HakmilikPermohonan();
            hakmilikPermohonanTemp.setPermohonan(permohonan);
            infoAudit.setDimasukOleh(pengguna);
            infoAudit.setTarikhMasuk(new java.util.Date());
            hakmilikPermohonanTemp.setInfoAudit(infoAudit);
        }

        if (hakmilikPermohonan != null) {

            if (String.valueOf(pemilikan) != null) {
                KodPemilikan kodPemilikan = new KodPemilikan();
                kodPemilikan.setKod(pemilikan);
                hakmilikPermohonanTemp.setKodMilik(kodPemilikan); //status       
            }
            if (jenisPenggunaanTanah != null) {
                KodKategoriTanah kodKategoriTanah = new KodKategoriTanah();
                kodKategoriTanah.setKod(jenisPenggunaanTanah);
                hakmilikPermohonanTemp.setJenisPenggunaanTanah(kodKategoriTanah);//kategori
            }
            if (dun != null) {
                KodDUN kodDun = new KodDUN();
                kodDun.setKod(dun);
                hakmilikPermohonanTemp.setKodDUN(kodDun);
            }
            if (pbt != null) {
                KodPBT kodPBT = new KodPBT();
                kodPBT.setKod(pbt);
                hakmilikPermohonanTemp.setKodPbt(kodPBT);
            }

            hakmilikPermohonanTemp.setJarakDariNama(hakmilikPermohonan.getJarakDariNama());//bandar/pekan terhampir  
            hakmilikPermohonanTemp.setKeteranganInfra(hakmilikPermohonan.getKeteranganInfra());//MAKLUMAT LAIN
        }

        consentService.simpanHakmilikPermohonan(hakmilikPermohonanTemp);

        LaporanTanah laporanTanahTemp = consentService.findLaporanTanahByIdMH(idPermohonan, String.valueOf(hakmilikPermohonanTemp.getId()));

        if (laporanTanahTemp != null) {
            infoAudit = laporanTanahTemp.getInfoAudit();
            infoAudit.setDiKemaskiniOleh(pengguna);
            infoAudit.setTarikhKemaskini(new java.util.Date());
            laporanTanahTemp.setInfoAudit(infoAudit);

        } else {
            laporanTanahTemp = new LaporanTanah();
            laporanTanahTemp.setPermohonan(permohonan);
            laporanTanahTemp.setHakmilikPermohonan(hakmilikPermohonanTemp);
            infoAudit.setDimasukOleh(pengguna);
            infoAudit.setTarikhMasuk(new java.util.Date());
            laporanTanahTemp.setInfoAudit(infoAudit);
        }

        if (laporanTanah != null) {
            if (laporanTanah.getKeadaanTanah() != null) {
                laporanTanahTemp.setKeadaanTanah(laporanTanah.getKeadaanTanah());
            }
            if (laporanTanah.getUsahaTanam() != null) {
                laporanTanahTemp.setUsahaTanam(laporanTanah.getUsahaTanam());
            }
            //zon
            laporanTanahTemp.setKeteranganTanahBertumpu(laporanTanah.getKeteranganTanahBertumpu());
            laporanTanahTemp.setRancanganKerajaan(laporanTanah.getRancanganKerajaan());
            laporanTanahTemp.setMercuTanda(laporanTanah.getMercuTanda());
            laporanTanahTemp.setKeadaanTanah(laporanTanah.getKeadaanTanah());
            //LOT-LOT BERSEMPADAN
            laporanTanahTemp.setCatatanSempadanBarat(laporanTanah.getCatatanSempadanBarat());
            laporanTanahTemp.setSebabTidakBolehBerimilik(laporanTanah.getSebabTidakBolehBerimilik());

        }
        laporanTanahService.simpanLaporanTanah(laporanTanahTemp);

        addSimpleMessage("Maklumat telah berjaya disimpan.");

        getContext().getRequest().setAttribute("edit", Boolean.TRUE);

        return new ForwardResolution("/WEB-INF/jsp/consent/laporan_tanah_ladang.jsp").addParameter("tab", "true");
    }

//    public Resolution simpanLaporanTanah() {
//
//
//        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
//        Pengguna pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
//        InfoAudit infoAudit = new InfoAudit();
//
//        permohonan = permohonanDAO.findById(idPermohonan);
//
//        LaporanTanah laporanTanahTemp = consentService.findLaporanTanahByIdMohon(idPermohonan);
//
//        if (laporanTanahTemp != null) {
//            infoAudit = laporanTanahTemp.getInfoAudit();
//            infoAudit.setDiKemaskiniOleh(pengguna);
//            infoAudit.setTarikhKemaskini(new java.util.Date());
//            laporanTanahTemp.setInfoAudit(infoAudit);
//
//        } else {
//            laporanTanahTemp = new LaporanTanah();
//            laporanTanahTemp.setPermohonan(permohonan);
//            infoAudit.setDimasukOleh(pengguna);
//            infoAudit.setTarikhMasuk(new java.util.Date());
//            laporanTanahTemp.setInfoAudit(infoAudit);
//        }
//
//        if (laporanTanah != null) {
//            if (laporanTanah.getKeadaanTanah() != null) {
//                laporanTanahTemp.setKeadaanTanah(laporanTanah.getKeadaanTanah());
//            }
//            if (laporanTanah.getUsahaTanam() != null) {
//                laporanTanahTemp.setUsahaTanam(laporanTanah.getUsahaTanam());
//            }
//        }
//        laporanTanahService.simpanLaporanTanah(laporanTanahTemp);
//
//        PermohonanLaporanUlasan ulasanPertanianTemp = consentService.findMohonUlasByJenisUlasan(idPermohonan, "PERTANIAN");
//
//        if (ulasanPertanianTemp != null) {
//            infoAudit = ulasanPertanianTemp.getInfoAudit();
//            infoAudit.setDiKemaskiniOleh(pengguna);
//            infoAudit.setTarikhKemaskini(new java.util.Date());
//            ulasanPertanianTemp.setInfoAudit(infoAudit);
//
//        } else {
//            ulasanPertanianTemp = new PermohonanLaporanUlasan();
//            ulasanPertanianTemp.setPermohonan(permohonan);
//            ulasanPertanianTemp.setCawangan(permohonan.getCawangan());
//            infoAudit.setDimasukOleh(pengguna);
//            infoAudit.setTarikhMasuk(new java.util.Date());
//            ulasanPertanianTemp.setInfoAudit(infoAudit);
//        }
//
//        ulasanPertanianTemp.setJenisUlasan("PERTANIAN");
//        if (ulasanPertanian != null) {
//            ulasanPertanianTemp.setUlasan(ulasanPertanian.getUlasan());
//        }
//        consentService.simpanPermohonanLaporanUlasan(ulasanPertanianTemp);
//
//        PermohonanLaporanUlasan ulasanTenagaKerjaTemp = consentService.findMohonUlasByJenisUlasan(idPermohonan, "KERJA");
//
//        if (ulasanTenagaKerjaTemp != null) {
//            infoAudit = ulasanTenagaKerjaTemp.getInfoAudit();
//            infoAudit.setDiKemaskiniOleh(pengguna);
//            infoAudit.setTarikhKemaskini(new java.util.Date());
//            ulasanTenagaKerjaTemp.setInfoAudit(infoAudit);
//
//        } else {
//            ulasanTenagaKerjaTemp = new PermohonanLaporanUlasan();
//            ulasanTenagaKerjaTemp.setPermohonan(permohonan);
//            ulasanTenagaKerjaTemp.setCawangan(permohonan.getCawangan());
//            infoAudit.setDimasukOleh(pengguna);
//            infoAudit.setTarikhMasuk(new java.util.Date());
//            ulasanTenagaKerjaTemp.setInfoAudit(infoAudit);
//        }
//
//        ulasanTenagaKerjaTemp.setJenisUlasan("KERJA");
//
//        if (ulasanTenagaKerja != null) {
//            ulasanTenagaKerjaTemp.setUlasan(ulasanTenagaKerja.getUlasan());
//        }
//        consentService.simpanPermohonanLaporanUlasan(ulasanTenagaKerjaTemp);
//
//        addSimpleMessage("Maklumat telah berjaya disimpan.");
//
////        }
//        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
//
//        return new ForwardResolution("/WEB-INF/jsp/consent/laporan_tanah_ladang.jsp").addParameter("tab", "true");
//    }
    public Permohonan getPermohonan() {
        return permohonan;
    }

    public void setPermohonan(Permohonan permohonan) {
        this.permohonan = permohonan;
    }

    public LaporanTanah getLaporanTanah() {
        return laporanTanah;
    }

    public void setLaporanTanah(LaporanTanah laporanTanah) {
        this.laporanTanah = laporanTanah;
    }

    public PermohonanLaporanUlasan getUlasanPertanian() {
        return ulasanPertanian;
    }

    public void setUlasanPertanian(PermohonanLaporanUlasan ulasanPertanian) {
        this.ulasanPertanian = ulasanPertanian;
    }

    public PermohonanLaporanUlasan getUlasanTenagaKerja() {
        return ulasanTenagaKerja;
    }

    public void setUlasanTenagaKerja(PermohonanLaporanUlasan ulasanTenagaKerja) {
        this.ulasanTenagaKerja = ulasanTenagaKerja;
    }

    public FasaPermohonan getFasaPermohonan() {
        return fasaPermohonan;
    }

    public void setFasaPermohonan(FasaPermohonan fasaPermohonan) {
        this.fasaPermohonan = fasaPermohonan;
    }

    public HakmilikPermohonan getHakmilikPermohonan() {
        return hakmilikPermohonan;
    }

    public void setHakmilikPermohonan(HakmilikPermohonan hakmilikPermohonan) {
        this.hakmilikPermohonan = hakmilikPermohonan;
    }

    public char getPemilikan() {
        return pemilikan;
    }

    public void setPemilikan(char pemilikan) {
        this.pemilikan = pemilikan;
    }

    public String getJenisPenggunanTanah() {
        return jenisPenggunaanTanah;
    }

    public void setJenisPenggunanTanah(String jenisPenggunanTanah) {
        this.jenisPenggunaanTanah = jenisPenggunanTanah;
    }

    public String getDun() {
        return dun;
    }

    public void setDun(String dun) {
        this.dun = dun;
    }

    public String getPbt() {
        return pbt;
    }

    public void setPbt(String pbt) {
        this.pbt = pbt;
    }

    public String getIdHakmilik() {
        return idHakmilik;
    }

    public void setIdHakmilik(String idHakmilik) {
        this.idHakmilik = idHakmilik;
    }

    public String getJenisPenggunaanTanah() {
        return jenisPenggunaanTanah;
    }

    public void setJenisPenggunaanTanah(String jenisPenggunaanTanah) {
        this.jenisPenggunaanTanah = jenisPenggunaanTanah;
    }

    public List<ImejLaporan> getHakmilikImejLaporanList() {
        return hakmilikImejLaporanList;
    }

    public void setHakmilikImejLaporanList(List<ImejLaporan> hakmilikImejLaporanList) {
        this.hakmilikImejLaporanList = hakmilikImejLaporanList;
    }

    public List<ImejLaporan> getHakmilikImejLaporanListEdit() {
        return hakmilikImejLaporanListEdit;
    }

    public void setHakmilikImejLaporanListEdit(List<ImejLaporan> hakmilikImejLaporanListEdit) {
        this.hakmilikImejLaporanListEdit = hakmilikImejLaporanListEdit;
    }

    public FileBean getFileToBeUpload() {
        return fileToBeUpload;
    }

    public void setFileToBeUpload(FileBean fileToBeUpload) {
        this.fileToBeUpload = fileToBeUpload;
    }

    public String getIdLaporTanah() {
        return idLaporTanah;
    }

    public void setIdLaporTanah(String idLaporTanah) {
        this.idLaporTanah = idLaporTanah;
    }

    public String getCatatan() {
        return catatan;
    }

    public void setCatatan(String catatan) {
        this.catatan = catatan;
    }

    public char getPandanganImej() {
        return pandanganImej;
    }

    public void setPandanganImej(char pandanganImej) {
        this.pandanganImej = pandanganImej;
    }
}
