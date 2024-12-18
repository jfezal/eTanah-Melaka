/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.penguatkuasaan;

import etanah.dao.*;
import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.model.InfoAudit;
import etanah.model.Pengguna;
import etanah.dao.DokumenDAO;
import etanah.model.AduanOrangKenaSyak;
import etanah.model.FasaPermohonan;
import etanah.model.Permohonan;
import etanah.model.KodCawangan;
import etanah.model.KodNegeri;
import etanah.model.FolderDokumen;
import etanah.model.KodJenisPengenalan;
import etanah.model.OperasiPenguatkuasaan;
import etanah.model.OperasiPenguatkuasaanPasukan;
import etanah.model.PegawaiPenyiasat;
import etanah.model.PermohonanNota;
import etanah.sequence.GeneratorIdPermohonan;
import etanah.service.BPelService;
import etanah.service.EnforceService;

import etanah.view.etanahActionBeanContext;
import java.util.List;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;
import org.apache.log4j.Logger;
import org.hibernate.Transaction;
import java.text.SimpleDateFormat;
import etanah.service.common.EnforcementService;
import java.util.ArrayList;
import net.sourceforge.stripes.action.ForwardResolution;
import org.hibernate.Session;
import net.sourceforge.stripes.action.RedirectResolution;
import net.sourceforge.stripes.action.StreamingResolution;
import oracle.bpel.services.workflow.task.model.Task;
import org.apache.commons.lang.StringUtils;

/**
 *
 * @author latifah.iskak
 */
@UrlBinding("/penguatkuasaan/maklumat_kertas_siasatan")
public class MaklumatKertasSiasatanActionBean extends AbleActionBean {

    private static Logger logger = Logger.getLogger(MaklumatKertasSiasatanActionBean.class);
    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;
    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    EnforcementService enforcementService;
    @Inject
    EnforceService enforceService;
    @Inject
    PermohonanKertasDAO permohonanKertasDAO;
    @Inject
    KodNegeriDAO kodNegeriDAO;
    @Inject
    DokumenDAO dokumenDAO;
    @Inject
    PermohonanKertasKandunganDAO permohonanKertasKandunganDAO;
    @Inject
    OperasiPenguatkuasaanPasukanDAO operasiPenguatkuasaanPasukanDAO;
    @Inject
    AduanOrangKenaSyakDAO aduanOrangKenaSyakDAO;
    @Inject
    private etanah.Configuration conf;
    @Inject
    KodDokumenDAO kodDokumenDAO;
    @Inject
    PenggunaDAO penggunaDAO;
    @Inject
    PegawaiPenyiasatDAO pegawaiPenyiasatDAO;
    @Inject
    private GeneratorIdPermohonan idPermohonanGenerator;
    @Inject
    KodJenisPengenalanDAO kodJenisPengenalanDAO;
    private Permohonan permohonan;
    private String idPermohonan;
    private Pengguna pguna;
    private KodCawangan cawangan;
    private OperasiPenguatkuasaan operasiPenguatkuasaan;
    private KodNegeri kodNegeri;
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    private String namaKetua;
    private OperasiPenguatkuasaanPasukan operasiPenguatkuasaanPasukan;
    private String noPengenalanKetua;
    private PegawaiPenyiasat pegawaiSiasat;
    private String recordCount;
    private List<OperasiPenguatkuasaan> listOp;
    private List<Permohonan> senaraiPermohonanBaru;
    private List<Pengguna> senaraiPengguna;
    private List<PegawaiPenyiasat> senaraiPembantuPegawaiPenyiasat;
    private String namaCarian;
    private String noPengenalanCarian;
    private String jawatanCarian;
    private String idPenggunaCarian;
    private Permohonan permohonanBaru;
    private String jawatanKetua;
    private String pilihPengguna;
    private String tarikhLantikKetua;
    private String idMohonBaru;
    private PegawaiPenyiasat pembantuPegawai;
    private String stageId;
    private String taskId;
    private String keputusan;
    private List<PegawaiPenyiasat> senaraiPegawaiPenyiasat;
    private List<AduanOrangKenaSyak> listAvailableOksForIP;

    @DefaultHandler
    public Resolution showForm() {
        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        return new JSP("penguatkuasaan/senarai_kertas_siasatan.jsp").addParameter("tab", "true");
    }

    public Resolution showForm2() {
        getContext().getRequest().setAttribute("view", Boolean.TRUE);
        return new JSP("penguatkuasaan/senarai_kertas_siasatan.jsp").addParameter("tab", "true");
    }

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {
        pguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        taskId = (String) getContext().getRequest().getSession().getAttribute("taskId");

        senaraiPermohonanBaru = new ArrayList<Permohonan>();
        senaraiPembantuPegawaiPenyiasat = new ArrayList<PegawaiPenyiasat>();

        if (idPermohonan != null) {
            permohonan = permohonanDAO.findById(idPermohonan);
            senaraiPermohonanBaru = enforceService.findSenaraiPermohonanBaru(idPermohonan);
            listOp = enforceService.findListLaporanOperasi(idPermohonan);

            ArrayList kumpulanBpel = new ArrayList<String>();
            if (permohonan.getCawangan().getKod().equalsIgnoreCase("00")) { //00 = PTG MELAKA
                kumpulanBpel.add("pptptgkuasa"); // PPT
                kumpulanBpel.add("pptkptgkuasa"); //PPTK
                kumpulanBpel.add("ppttptgkuasa"); //PPTT
            } else { // for 01 = melaka tengah, 02 = jasin & 03 = alor gajah
//                kumpulanBpel.add("pptptdkuasa"); // PPT
                kumpulanBpel.add("PPTanah"); // PPT
                kumpulanBpel.add("pptkptdkuasa"); //PPTK
                kumpulanBpel.add("ppttptdkuasa"); //PPTT
            }

            senaraiPengguna = enforcementService.findUserKumpBpel(kumpulanBpel, permohonan.getCawangan().getKod());

            listAvailableOksForIP = enforcementService.getListAvailableOKSForIP(idPermohonan);

            logger.info(":::::senaraiPermohonanBaru size : " + senaraiPermohonanBaru.size());
            logger.info(":::::ListOp size : " + listOp.size());
            logger.info(":::::listAvailableOksForIP size : " + listAvailableOksForIP.size());

            BPelService service = new BPelService();

            if (StringUtils.isNotBlank(taskId)) {
                Task task = null;
                task = service.getTaskFromBPel(taskId, pguna);
                if (task != null) {
                    stageId = task.getSystemAttributes().getStage();
                    logger.info("--------------stage Id BPEL ON--------------- : " + stageId);
                }
            } else {
                stageId = "keputusan_op";
                logger.info("--------------stage Id BPEL OFF--------------- : " + stageId);
            }

            List<FasaPermohonan> senaraiFasa = permohonan.getSenaraiFasa();

            for (FasaPermohonan fp : senaraiFasa) {
                if (fp.getIdAliran().equalsIgnoreCase(stageId)) {
                    if (fp.getKeputusan() != null) {
                        keputusan = fp.getKeputusan().getKod();
                        logger.info("--------------keputusan--------------- : " + keputusan);
                    }
                }
            }
        }
    }

    public Resolution simpan() {
        logger.info(":::::simpan");

        idMohonBaru = getContext().getRequest().getParameter("idMohonBaru");
        InfoAudit ia = new InfoAudit();

        if (StringUtils.isNotBlank(idMohonBaru)) {
            permohonanBaru = permohonanDAO.findById(idMohonBaru);
            pegawaiSiasat = enforceService.findKetuaPenyiasat(idMohonBaru);
            senaraiPembantuPegawaiPenyiasat = enforceService.findPembantuPenyiasat(idMohonBaru);
        } else {
        }

        if (permohonanBaru != null) {
            ia = permohonanBaru.getInfoAudit();
            ia.setDiKemaskiniOleh(pguna);
            ia.setTarikhKemaskini(new java.util.Date());
        } else {
            permohonanBaru = new Permohonan();
            ia.setDimasukOleh(pguna);
            ia.setTarikhMasuk(new java.util.Date());


            etanahActionBeanContext ctx = (etanahActionBeanContext) getContext();

            String idPermohonanBaru = idPermohonanGenerator.generate(
                    ctx.getKodNegeri(), pguna.getKodCawangan(), permohonan.getKodUrusan());

            //create id mohon baru
            permohonanBaru.setStatus("TA");
            permohonanBaru.setIdPermohonan(idPermohonanBaru);
            permohonanBaru.setCawangan(pguna.getKodCawangan());
            permohonanBaru.setKodUrusan(permohonan.getKodUrusan());
            permohonanBaru.setCaraPermohonan(permohonan.getCaraPermohonan());
            permohonanBaru.setSebab(permohonan.getSebab());
            permohonanBaru.setPenyerahNama(permohonan.getPenyerahNama());
            permohonanBaru.setPenyerahJenisPengenalan(permohonan.getPenyerahJenisPengenalan());
            permohonanBaru.setPenyerahNoPengenalan(permohonan.getPenyerahNoPengenalan());
            permohonanBaru.setPenyerahAlamat1(permohonan.getPenyerahAlamat1());
            permohonanBaru.setPenyerahAlamat2(permohonan.getPenyerahAlamat2());
            permohonanBaru.setPenyerahAlamat3(permohonan.getPenyerahAlamat3());
            permohonanBaru.setPenyerahAlamat4(permohonan.getPenyerahAlamat4());
            permohonanBaru.setPenyerahPoskod(permohonan.getPenyerahPoskod());
            permohonanBaru.setPenyerahNegeri(permohonan.getPenyerahNegeri());
            permohonanBaru.setPenyerahNoTelefon1(permohonan.getPenyerahNoTelefon1());
            permohonanBaru.setPenyerahEmail(permohonan.getPenyerahEmail());
            permohonanBaru.setPermohonanSebelum(permohonan);
            permohonanBaru.setInfoAudit(ia);

            enforceService.savePermohonan(permohonanBaru);

            if (permohonanBaru.getFolderDokumen() == null) {
                FolderDokumen fd = new FolderDokumen();
                fd.setTajuk(permohonanBaru.getIdPermohonan());
                fd.setInfoAudit(ia);
                enforceService.simpanFolderDokumen(fd);
                permohonanBaru.setFolderDokumen(fd);
            }

            enforceService.savePermohonan(permohonanBaru);
        }


        //update oks
        for (int i = 0; i < listOp.size(); i++) {
            try {
                List<AduanOrangKenaSyak> listOks = new ArrayList<AduanOrangKenaSyak>();
                listOks = listOp.get(i).getSenaraiAduanOrangKenaSyak();
                logger.info("::::::::::size listOks::::::::::  " + listOks.size());


                for (int j = 0; j < listOks.size(); j++) {
                    String pilihCheckBox = getContext().getRequest().getParameter("pilihCheckBox" + i + j);
                    pilihCheckBox = getContext().getRequest().getParameter("pilihCheckBox" + i + j);
                    System.out.println("pilihCheckBox [" + i + j + "]: " + pilihCheckBox);


                    if (StringUtils.isNotBlank(pilihCheckBox)) {
                        AduanOrangKenaSyak aduanOrangKenaSyak = aduanOrangKenaSyakDAO.findById(Long.parseLong(pilihCheckBox));
                        if (aduanOrangKenaSyak != null && aduanOrangKenaSyak.getPermohonanAduanOrangKenaSyak() == null) {
                            operasiPenguatkuasaan = aduanOrangKenaSyak.getOperasiPenguatkuasaan();
                            ia = aduanOrangKenaSyak.getInfoAudit();
                            ia.setDiKemaskiniOleh(pguna);
                            ia.setTarikhKemaskini(new java.util.Date());

                            aduanOrangKenaSyak.setInfoAudit(ia);
                            aduanOrangKenaSyak.setStatusIp("Y");
                            aduanOrangKenaSyak.setPermohonanAduanOrangKenaSyak(permohonanBaru);
                            enforceService.simpanAduanOrangDisyaki(aduanOrangKenaSyak);
                        }
                    } else {
                        Long idOks = listOks.get(j).getIdOrangKenaSyak();
                        System.out.println("id oks uncheck or null : " + idOks);
                        AduanOrangKenaSyak aduanOrangKenaSyak = aduanOrangKenaSyakDAO.findById(idOks);
                        if (StringUtils.isNotBlank(idMohonBaru)) {
                            if (aduanOrangKenaSyak.getPermohonanAduanOrangKenaSyak() != null) {
                                if (aduanOrangKenaSyak.getPermohonanAduanOrangKenaSyak().getIdPermohonan().equalsIgnoreCase(idMohonBaru)) {
                                    System.out.println("update status ip & id permohonan baru to null");
                                    ia = aduanOrangKenaSyak.getInfoAudit();
                                    ia.setDiKemaskiniOleh(pguna);
                                    ia.setTarikhKemaskini(new java.util.Date());

                                    aduanOrangKenaSyak.setInfoAudit(ia);
                                    aduanOrangKenaSyak.setStatusIp(null);
                                    aduanOrangKenaSyak.setPermohonanAduanOrangKenaSyak(null);
                                    enforceService.simpanAduanOrangDisyaki(aduanOrangKenaSyak);
                                }
                            }
                        }
//                        if (aduanOrangKenaSyak != null && aduanOrangKenaSyak.getPermohonanAduanOrangKenaSyak() == null) {
//                            System.out.println("update status ip & id permohonan baru to null");
//                            ia = aduanOrangKenaSyak.getInfoAudit();
//                            ia.setDiKemaskiniOleh(pguna);
//                            ia.setTarikhKemaskini(new java.util.Date());
//
//                            aduanOrangKenaSyak.setInfoAudit(ia);
//                            aduanOrangKenaSyak.setStatusIp(null);
//                            aduanOrangKenaSyak.setPermohonanAduanOrangKenaSyak(null);
//                            enforceService.simpanAduanOrangDisyaki(aduanOrangKenaSyak);
//                        }

                    }

                }

            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }

        //save ketua pegawai penyiasat
        System.out.println("::::::::::::::::::::pegawai penyiasat : " + pegawaiSiasat);

        if (pegawaiSiasat != null) {
            ia = pegawaiSiasat.getInfoAudit();
            ia.setDiKemaskiniOleh(pguna);
            ia.setTarikhKemaskini(new java.util.Date());
        } else {
            System.out.println("::::::::::::::::::::create new pegawai penyiasat(ketua)");
            pegawaiSiasat = new PegawaiPenyiasat();
            ia.setDimasukOleh(pguna);
            ia.setTarikhMasuk(new java.util.Date());
        }

        KodJenisPengenalan kodKp = kodJenisPengenalanDAO.findById("B");

        if (StringUtils.isNotBlank(pilihPengguna)) {
            Pengguna pengguna = penggunaDAO.findById(pilihPengguna);
            System.out.println("pilih ketua : " + pengguna.getNama());
            pegawaiSiasat.setNama(pengguna.getNama());
            pegawaiSiasat.setNoPengenalan(pengguna.getNoPengenalan());
            pegawaiSiasat.setJawatan(pengguna.getJawatan());
            pegawaiSiasat.setJenisPengenalan(kodKp);
            pegawaiSiasat.setNamaJabatan(pilihPengguna);
        }

        pegawaiSiasat.setPermohonan(permohonanBaru);
        pegawaiSiasat.setCawangan(pguna.getKodCawangan());
        pegawaiSiasat.setNamaJabatan(pilihPengguna);
        pegawaiSiasat.setInfoAudit(ia);
        pegawaiSiasat.setStatusPeranan("K");
        if (operasiPenguatkuasaan != null) {
            System.out.println("operasiPenguatkuasaan utk PP : " + operasiPenguatkuasaan.getIdOperasi());
            pegawaiSiasat.setOperasiPenguatkuasaan(operasiPenguatkuasaan);
        }

        try {
            if (StringUtils.isNotBlank(tarikhLantikKetua)) {
                System.out.println("tarikhLantikKetua : " + tarikhLantikKetua);
                pegawaiSiasat.setTarikhLantikan(sdf.parse(tarikhLantikKetua));
            }
        } catch (Exception e) {
            logger.error(e);
        }
        //pegawaiPenyiasatDAO.saveOrUpdate(pegawaiSiasat);
        enforceService.simpanPegawaiPenyiasat(pegawaiSiasat);

        String row = getContext().getRequest().getParameter("recordCount");

        if (StringUtils.isNotBlank(row)) {

            int rowCount = Integer.parseInt(getContext().getRequest().getParameter("recordCount"));
            System.out.println("row count : " + rowCount);

            System.out.println("size senaraiPembantuPegawaiPenyiasat : " + senaraiPembantuPegawaiPenyiasat.size());


            Session s = sessionProvider.get();
            Transaction tx = s.beginTransaction();

            for (int i = 0; i < rowCount; i++) {
                if (senaraiPembantuPegawaiPenyiasat.size() != 0 && i < senaraiPembantuPegawaiPenyiasat.size()) {
                    Long id = senaraiPembantuPegawaiPenyiasat.get(i).getIdPelaksanaWaran();
                    if (id != null) {
                        pembantuPegawai = pegawaiPenyiasatDAO.findById(id);
                    } else {
                        System.out.println("create new pasukan if list not empty");
                        pembantuPegawai = new PegawaiPenyiasat();
                    }
                } else {
                    System.out.println("create new pasukan");
                    pembantuPegawai = new PegawaiPenyiasat();
                }


                String idPenggunaPembantu = getContext().getRequest().getParameter("pilihPembantu" + i);
                String tarikhLantikPembantu = getContext().getRequest().getParameter("tarikhLantikPembantu" + i);
                if (StringUtils.isNotBlank(idPenggunaPembantu)) {
                    Pengguna pengguna = penggunaDAO.findById(idPenggunaPembantu);
                    pembantuPegawai.setNama(pengguna.getNama());
                    pembantuPegawai.setNoPengenalan(pengguna.getNoPengenalan());
                    pembantuPegawai.setJawatan(pengguna.getJawatan());
                    pembantuPegawai.setJenisPengenalan(kodKp);
                    pembantuPegawai.setNamaJabatan(idPenggunaPembantu);
                }
                pembantuPegawai.setPermohonan(permohonanBaru);
                pembantuPegawai.setCawangan(pguna.getKodCawangan());
                pembantuPegawai.setInfoAudit(ia);
                pembantuPegawai.setStatusPeranan("P");

                if (operasiPenguatkuasaan != null) {
                    System.out.println("operasiPenguatkuasaan utk PP pembantu: " + operasiPenguatkuasaan.getIdOperasi());
                    pembantuPegawai.setOperasiPenguatkuasaan(operasiPenguatkuasaan);
                }


                try {
                    if (StringUtils.isNotBlank(tarikhLantikPembantu)) {
                        pembantuPegawai.setTarikhLantikan(sdf.parse(tarikhLantikPembantu));
                    }
                } catch (Exception e) {
                    logger.error(e);
                }
                pegawaiPenyiasatDAO.saveOrUpdate(pembantuPegawai);

                //enforceService.simpanPegawaiPenyiasat(pembantuPegawai);

            }
            tx.commit();
        }



        addSimpleMessage("Maklumat telah berjaya disimpan.");
        rehydrate();
        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        return new JSP("penguatkuasaan/senarai_kertas_siasatan.jsp").addParameter("tab", "true");

    }

//    public Resolution addPegawaiPenyiasatList() {
//        logger.debug("------------addPegawaiPenyiasatList-------------");
//
//        logger.debug("size list senaraiPembantuPegawaiPenyiasat :" + senaraiPembantuPegawaiPenyiasat.size());
//
//        PenyiasatValue pv = new PenyiasatValue();
//
//        senaraiPembantuPegawaiPenyiasat.add(pv);
//
//
//        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
//        return new JSP("penguatkuasaan/popup_tambah_kertas_siasatan.jsp").addParameter("popup", "true");
//    }
    public Resolution popupTambahKertasSiasatan() {
        logger.info(":::::popupTambahKertasSiasatan");
        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        return new JSP("penguatkuasaan/popup_tambah_kertas_siasatan.jsp").addParameter("popup", "true");
    }

    public Resolution popupEditKertasSiasatan() {
        logger.info(":::::popupEditKertasSiasatan");

        idMohonBaru = getContext().getRequest().getParameter("id");
        if (StringUtils.isNotBlank(idMohonBaru)) {
            permohonanBaru = permohonanDAO.findById(idMohonBaru);
            pegawaiSiasat = enforceService.findKetuaPenyiasat(idMohonBaru);

            if (pegawaiSiasat != null) {
                pilihPengguna = pegawaiSiasat.getNamaJabatan();
                noPengenalanKetua = pegawaiSiasat.getNoPengenalan();
                jawatanKetua = pegawaiSiasat.getJawatan();
                if (pegawaiSiasat.getTarikhLantikan() != null) {
                    tarikhLantikKetua = sdf.format(pegawaiSiasat.getTarikhLantikan());
                }

            }

            senaraiPembantuPegawaiPenyiasat = enforceService.findPembantuPenyiasat(idMohonBaru);
            if (senaraiPembantuPegawaiPenyiasat != null) {
                recordCount = String.valueOf(senaraiPembantuPegawaiPenyiasat.size());
                System.out.println("record count : " + recordCount);
            }


        }

        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        return new JSP("penguatkuasaan/popup_tambah_kertas_siasatan.jsp").addParameter("popup", "true");
    }

    public Resolution findPengguna() {
        logger.info(":::::findPengguna");
        String id = getContext().getRequest().getParameter("id");
        Pengguna pengguna = penggunaDAO.findById(id);

        if (pengguna != null) {
            noPengenalanCarian = pengguna.getNoPengenalan();
            jawatanCarian = pengguna.getJawatan();
            idPenggunaCarian = pengguna.getIdPengguna();

            System.out.println("noPengenalanCarian : " + noPengenalanCarian);
            System.out.println("jawatanCarian : " + jawatanCarian);
            System.out.println("idPenggunaCarian : " + idPenggunaCarian);
        }

        return new JSP("penguatkuasaan/popup_tambah_kertas_siasatan.jsp").addParameter("popup", "true");
    }

    public Resolution deletePegawai() {
        String idPegawai = getContext().getRequest().getParameter("idPegawai");
        System.out.println("id masa delete : " + idPegawai);
        try {
            if (StringUtils.isNotBlank(idPegawai)) {
                pegawaiSiasat = pegawaiPenyiasatDAO.findById(Long.parseLong(idPegawai));
            }
            enforceService.deletePegawai(pegawaiSiasat);
        } catch (Exception e) {
            e.printStackTrace();
            addSimpleError("kesalahan dalam menghapuskan rekod");
        }
        addSimpleMessage("Maklumat berjaya dihapuskan.");
        rehydrate();
        return new ForwardResolution("/WEB-INF/jsp/penguatkuasaan/popup_tambah_kertas_siasatan.jsp").addParameter("popup", "true");
    }

    public Resolution deletePermohonan() {
        logger.info("--------------deletePermohonan--------------");
        String idKertasSiasatan = getContext().getRequest().getParameter("idPermohonan");
        if (StringUtils.isNotBlank(idKertasSiasatan)) {
            Permohonan kertasSiasatan = permohonanDAO.findById(idKertasSiasatan);

            if (kertasSiasatan != null) {
                senaraiPegawaiPenyiasat = enforceService.findPenyiasat(kertasSiasatan.getIdPermohonan());

                //delete all pegawai penyiasat
                for (int i = 0; i < senaraiPegawaiPenyiasat.size(); i++) {
                    pegawaiSiasat = senaraiPegawaiPenyiasat.get(i);
                    enforceService.deletePegawai(pegawaiSiasat);
                }

                //set value to null
                List<AduanOrangKenaSyak> senaraiOks = enforceService.getListAduanOrangkenaSyak(kertasSiasatan.getIdPermohonan());
                for (int i = 0; i < senaraiOks.size(); i++) {
                    AduanOrangKenaSyak oks = senaraiOks.get(i);
                    InfoAudit ia = oks.getInfoAudit();
                    ia.setDiKemaskiniOleh(pguna);
                    ia.setTarikhKemaskini(new java.util.Date());

                    oks.setStatusIp(null);
                    oks.setPermohonanAduanOrangKenaSyak(null);
                    oks.setInfoAudit(ia);
                    enforceService.simpanAduanOrangDisyaki(oks);
                }

//                //delete nota tindakan
//                List<PermohonanNota> listNotaPermohonan = enforceService.findListNotaByIdMohonSebelum(kertasSiasatan.getIdPermohonan());
//
////                for (int i = 0; i < listNotaPermohonan.size(); i++) {
////                    PermohonanNota nota = listNotaPermohonan.get(i);
////                    enforceService.deleteNota(nota);
////                }
                //delete permohonan
                kertasSiasatan.setStatus("BP");// BP = batal permohonan
                enforceService.savePermohonan(kertasSiasatan);

//                //delete folder_dokumen
//                FolderDokumen fd = kertasSiasatan.getFolderDokumen();
//                System.out.println("fd : " + fd);
//                if (fd != null) {
//                    System.out.println("id folder dok : " + fd.getFolderId());
//                    enforceService.deleteFolderDokumen(fd);
//                }


            }

        }
        addSimpleMessage("Maklumat berjaya dihapuskan.");
        return new RedirectResolution(MaklumatKertasSiasatanActionBean.class, "locate");
    }

    public KodCawangan getCawangan() {
        return cawangan;
    }

    public void setCawangan(KodCawangan cawangan) {
        this.cawangan = cawangan;
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

    public Pengguna getPguna() {
        return pguna;
    }

    public void setPguna(Pengguna pguna) {
        this.pguna = pguna;
    }

    public PermohonanDAO getPermohonanDAO() {
        return permohonanDAO;
    }

    public void setPermohonanDAO(PermohonanDAO permohonanDAO) {
        this.permohonanDAO = permohonanDAO;
    }

    public PermohonanKertasDAO getPermohonanKertasDAO() {
        return permohonanKertasDAO;
    }

    public void setPermohonanKertasDAO(PermohonanKertasDAO permohonanKertasDAO) {
        this.permohonanKertasDAO = permohonanKertasDAO;
    }

    public PermohonanKertasKandunganDAO getPermohonanKertasKandunganDAO() {
        return permohonanKertasKandunganDAO;
    }

    public void setPermohonanKertasKandunganDAO(PermohonanKertasKandunganDAO permohonanKertasKandunganDAO) {
        this.permohonanKertasKandunganDAO = permohonanKertasKandunganDAO;
    }

    public OperasiPenguatkuasaan getOperasiPenguatkuasaan() {
        return operasiPenguatkuasaan;
    }

    public void setOperasiPenguatkuasaan(OperasiPenguatkuasaan operasiPenguatkuasaan) {
        this.operasiPenguatkuasaan = operasiPenguatkuasaan;
    }

    public KodNegeri getKodNegeri() {
        return kodNegeri;
    }

    public void setKodNegeri(KodNegeri kodNegeri) {
        this.kodNegeri = kodNegeri;
    }

    public Resolution refreshpage() {
        return new RedirectResolution(MaklumatKertasSiasatanActionBean.class, "locate");
    }

    public Resolution refreshDiv() {
        rehydrate();
        return new RedirectResolution(MaklumatKertasSiasatanActionBean.class, "locate");
    }

    public String getNamaKetua() {
        return namaKetua;
    }

    public void setNamaKetua(String namaKetua) {
        this.namaKetua = namaKetua;
    }

    public OperasiPenguatkuasaanPasukan getOperasiPenguatkuasaanPasukan() {
        return operasiPenguatkuasaanPasukan;
    }

    public void setOperasiPenguatkuasaanPasukan(OperasiPenguatkuasaanPasukan operasiPenguatkuasaanPasukan) {
        this.operasiPenguatkuasaanPasukan = operasiPenguatkuasaanPasukan;
    }

    public String getNoPengenalanKetua() {
        return noPengenalanKetua;
    }

    public void setNoPengenalanKetua(String noPengenalanKetua) {
        this.noPengenalanKetua = noPengenalanKetua;
    }

    public PegawaiPenyiasat getPegawaiSiasat() {
        return pegawaiSiasat;
    }

    public void setPegawaiSiasat(PegawaiPenyiasat pegawaiSiasat) {
        this.pegawaiSiasat = pegawaiSiasat;
    }

    public String getRecordCount() {
        return recordCount;
    }

    public void setRecordCount(String recordCount) {
        this.recordCount = recordCount;
    }

    public List<OperasiPenguatkuasaan> getListOp() {
        return listOp;
    }

    public void setListOp(List<OperasiPenguatkuasaan> listOp) {
        this.listOp = listOp;
    }

    public List<Permohonan> getSenaraiPermohonanBaru() {
        return senaraiPermohonanBaru;
    }

    public void setSenaraiPermohonanBaru(List<Permohonan> senaraiPermohonanBaru) {
        this.senaraiPermohonanBaru = senaraiPermohonanBaru;
    }

    public List<Pengguna> getSenaraiPengguna() {
        return senaraiPengguna;
    }

    public void setSenaraiPengguna(List<Pengguna> senaraiPengguna) {
        this.senaraiPengguna = senaraiPengguna;
    }

    public String getNamaCarian() {
        return namaCarian;
    }

    public void setNamaCarian(String namaCarian) {
        this.namaCarian = namaCarian;
    }

    public String getNoPengenalanCarian() {
        return noPengenalanCarian;
    }

    public void setNoPengenalanCarian(String noPengenalanCarian) {
        this.noPengenalanCarian = noPengenalanCarian;
    }

    public String getJawatanCarian() {
        return jawatanCarian;
    }

    public void setJawatanCarian(String jawatanCarian) {
        this.jawatanCarian = jawatanCarian;
    }

    public String getIdPenggunaCarian() {
        return idPenggunaCarian;
    }

    public void setIdPenggunaCarian(String idPenggunaCarian) {
        this.idPenggunaCarian = idPenggunaCarian;
    }

    public List<PegawaiPenyiasat> getSenaraiPembantuPegawaiPenyiasat() {
        return senaraiPembantuPegawaiPenyiasat;
    }

    public void setSenaraiPembantuPegawaiPenyiasat(List<PegawaiPenyiasat> senaraiPembantuPegawaiPenyiasat) {
        this.senaraiPembantuPegawaiPenyiasat = senaraiPembantuPegawaiPenyiasat;
    }

    public Permohonan getPermohonanBaru() {
        return permohonanBaru;
    }

    public void setPermohonanBaru(Permohonan permohonanBaru) {
        this.permohonanBaru = permohonanBaru;
    }

    public String getJawatanKetua() {
        return jawatanKetua;
    }

    public void setJawatanKetua(String jawatanKetua) {
        this.jawatanKetua = jawatanKetua;
    }

    public String getPilihPengguna() {
        return pilihPengguna;
    }

    public void setPilihPengguna(String pilihPengguna) {
        this.pilihPengguna = pilihPengguna;
    }

    public String getTarikhLantikKetua() {
        return tarikhLantikKetua;
    }

    public void setTarikhLantikKetua(String tarikhLantikKetua) {
        this.tarikhLantikKetua = tarikhLantikKetua;
    }

    public String getIdMohonBaru() {
        return idMohonBaru;
    }

    public void setIdMohonBaru(String idMohonBaru) {
        this.idMohonBaru = idMohonBaru;
    }

    public PegawaiPenyiasat getPembantuPegawai() {
        return pembantuPegawai;
    }

    public void setPembantuPegawai(PegawaiPenyiasat pembantuPegawai) {
        this.pembantuPegawai = pembantuPegawai;
    }

    public String getKeputusan() {
        return keputusan;
    }

    public void setKeputusan(String keputusan) {
        this.keputusan = keputusan;
    }

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public String getStageId() {
        return stageId;
    }

    public void setStageId(String stageId) {
        this.stageId = stageId;
    }

    public List<PegawaiPenyiasat> getSenaraiPegawaiPenyiasat() {
        return senaraiPegawaiPenyiasat;
    }

    public void setSenaraiPegawaiPenyiasat(List<PegawaiPenyiasat> senaraiPegawaiPenyiasat) {
        this.senaraiPegawaiPenyiasat = senaraiPegawaiPenyiasat;
    }

    public List<AduanOrangKenaSyak> getListAvailableOksForIP() {
        return listAvailableOksForIP;
    }

    public void setListAvailableOksForIP(List<AduanOrangKenaSyak> listAvailableOksForIP) {
        this.listAvailableOksForIP = listAvailableOksForIP;
    }
}
