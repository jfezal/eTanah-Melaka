/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.stripes.pengambilan;

/**
 *
 * @author massita
 */
import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.Configuration;
import etanah.dao.DokumenDAO;
import etanah.dao.HakmilikDAO;
import etanah.dao.HakmilikPermohonanDAO;
import etanah.dao.KodCawanganDAO;
import etanah.dao.KodDokumenDAO;
import etanah.dao.PihakDAO;
import etanah.dao.PemohonDAO;
import etanah.dao.PermohonanDAO;
import etanah.dao.PermohonanRujukanLuarDAO;
import etanah.dao.PermohonanTuntutanKosDAO;
import etanah.service.common.PemohonService;
import etanah.model.*;
import etanah.model.KodCawangan;
import etanah.model.HakmilikPermohonan;
import etanah.model.ambil.HakmilikPerbicaraan;
import etanah.service.PengambilanService;
import etanah.service.StrataPtService;
import etanah.service.common.LelongService;
import etanah.service.common.PerbicaraanService;
import etanah.view.etanahActionBeanContext;
import java.util.Date;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.controller.LifecycleStage;
import net.sourceforge.stripes.action.StreamingResolution;
import net.sourceforge.stripes.action.ErrorResolution;
import org.apache.log4j.Logger;
import org.apache.commons.lang.StringUtils;
import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;
import org.hibernate.Session;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.StringTokenizer;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.File;

@UrlBinding("/pengambilan/penarikanBalik")
public class permohonan_penarikanbalikActionBean extends AbleActionBean {
    
    private static Logger logger = Logger.getLogger(permohonan_penarikanbalikActionBean.class);
    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;
    @Inject
    private etanah.Configuration conf;
    @Inject
    PermohonanRujukanLuarDAO permohonanRujukanLuarDAO;
    @Inject
    PermohonanTuntutanKosDAO permohonanTuntutanKosDAO;
    @Inject
    HakmilikPermohonanDAO hakmilikPermohonanDAO;
    @Inject
    KodDokumenDAO kodDokumenDAO;
    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    PengambilanService pengambilanService;
    @Inject
    PerbicaraanService perbicaraanService;
    @Inject
    PemohonDAO pemohonDAO;
    @Inject
    KodCawanganDAO kodCawanganDAO;
    @Inject
    PihakDAO pihakDAO;
    @Inject
    HakmilikDAO hakmilikDAO;
    @Inject
    PemohonService pemohonService;
    @Inject
    LelongService lelongService;
    @Inject
    DokumenDAO dokumenDAO;
    @Inject
    StrataPtService strService;
    private List<Pemohon> pemohonList;
    private List<HakmilikPermohonan> hakmilikPermohonanList;
    private List<HakmilikPerbicaraan> hakmilikPerbicaraanList;
    private List<HakmilikPermohonan> senaraiHakmilikPermohonan = new ArrayList<HakmilikPermohonan>();
    private List<String> selectedIdHakmilik = new ArrayList<String>();
    private Permohonan permohonan;
    private KodDokumen kodDokumenRujukan;
    private Dokumen dokumen;
    private PermohonanRujukanLuar permohonanRujukanLuar;
    private HakmilikPerbicaraan hakmilikPerbicaraan;
    private HakmilikPermohonan hakmilikPermohonan;
    private String idSblm;
    private String borangK;
    private Pemohon pemohon;
    private Pihak pihak;
    private KodCawangan cawangan;
    private String item;
    boolean kerosakan;
    private String idKos;
    private String idPermohonan;
    private Permohonan permohonanSebelum;
    private String namaProjek;
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
//    4 search variable at search_IdMohonOSebab
    private List<Permohonan> listIdMohon;
    private List<String> tarikhBicaraList = new ArrayList<String>();
    private List<BigDecimal> keputusanNilaiArrayList = new ArrayList<BigDecimal>();
    private List<BigDecimal> hakmilikAmaunList = new ArrayList<BigDecimal>();
    private BigDecimal keputusanNilai;
    private String tarikhBicara;
    private String index;
    private String sebabProjek;
    private String id;
    private String radio_;
    private String sebab;
    private String tarikhLulus;
    private Dokumen dokumenK;
    private int size = 0;
    private Permohonan curPermohonan;
    private List<HakmilikPermohonan> senaraiHakmilikPB;
    private String kodUrusan;
    private KodUrusan kodUrusanSblm;
    private Permohonan pSblm;
    
    public Permohonan getpSblm() {
        return pSblm;
    }
    
    public void setpSblm(Permohonan pSblm) {
        this.pSblm = pSblm;
    }
    
    public KodUrusan getKodUrusanSblm() {
        return kodUrusanSblm;
    }
    
    public void setKodUrusanSblm(KodUrusan kodUrusanSblm) {
        this.kodUrusanSblm = kodUrusanSblm;
    }
    
    @DefaultHandler
    public Resolution showForm() {
        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = permohonanDAO.findById(idPermohonan);
        
        if (permohonan.getPermohonanSebelum() != null) {
            idSblm = permohonan.getPermohonanSebelum().getIdPermohonan();
        }
        
        if (idSblm != null && idPermohonan != null) {
            idSblm = idSblm.trim();
            idSblm = permohonan.getPermohonanSebelum().getIdPermohonan();
            permohonanSebelum = permohonanDAO.findById(idSblm);
            if (permohonanSebelum != null) {
                namaProjek = permohonanSebelum.getSebab();
                kodUrusanSblm = permohonanSebelum.getKodUrusan();
                getContext().getRequest().setAttribute("Maklumat_Pemohon", Boolean.TRUE);
                semakIdSebelum();
            }
        }
        
        return new JSP("pengambilan/permohonan_penarikanbalik1.jsp").addParameter("tab", "true");
    }

    // Click on Cari button in Home Page to display SearchPage
    public Resolution IdMohonOSebabPopup() {
//        index = getContext().getRequest().getParameter("index");
        return new JSP("pengambilan/search_IdMohonOSebab.jsp").addParameter("popup", "true");
    }
    
    public Resolution checkSebelum() {
        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        
        idSblm = getContext().getRequest().getParameter("idSblm");
        logger.debug("checkSebelum  ---idPermohonan" + idPermohonan);
        logger.debug("   checkSebelum ------idSblm no" + idSblm);
        
        if (idSblm == null) {
            logger.debug(" session  ---idSblm" + idSblm);
            idSblm = (String) getContext().getRequest().getSession().getAttribute("idSblm");
            namaProjek = (String) getContext().getRequest().getSession().getAttribute("namaProjek");
        } else {
            if (idSblm != null && idPermohonan != null) {
                idSblm = idSblm.trim();
                permohonanSebelum = permohonanDAO.findById(idSblm);
                if (permohonanSebelum != null) {
                    kodUrusanSblm = permohonanSebelum.getKodUrusan();
                    namaProjek = permohonanSebelum.getSebab();
                }
            }
        }
        
        if (idSblm != null && idPermohonan != null) {
            idSblm = idSblm.trim();
            Permohonan pm = permohonanDAO.findById(idPermohonan);
            if (permohonanSebelum != null) {
                pm.setPermohonanSebelum(permohonanSebelum);
                pengambilanService.simpanPermohonanSebelum(pm, peng);//b4 this comment & open when b4 this sebab & nama project didnt save at mohon table in current id permohonan

                //code to set selected Hakmilik's of PermohonanSebelum to current Permohonan
                String hakmilikList = getContext().getRequest().getParameter("hakmilikList");
                logger.debug("hakmilikList size >>> "+hakmilikList.length());
                if (hakmilikList != null && !hakmilikList.isEmpty()) {
                    StringTokenizer st = new StringTokenizer(hakmilikList);
                    while (st.hasMoreTokens()) {
//                         if(st.nextToken(",") != null){
                        String idHakmilik = st.nextToken(",");
                        logger.debug("id hakmilik >>> "+idHakmilik);
                        hakmilikPermohonan = pengambilanService.findByIdHakmilikIdPermohonan(idPermohonan, idHakmilik);
                        HakmilikPermohonan hakmilikPermohonanSblm = pengambilanService.findByIdHakmilikIdPermohonan(idSblm, idHakmilik);
                        if (hakmilikPermohonan == null) {
                            Hakmilik hakmilik = hakmilikDAO.findById(idHakmilik);
                            if (hakmilik != null) {
                                HakmilikPermohonan hpa = new HakmilikPermohonan();
                                hpa.setPermohonan(pm);
                                hpa.setHakmilik(hakmilik);
                                hpa.setPenarikBalikan("0");
                                if (hakmilikPermohonanSblm != null) {
                                    if (hakmilikPermohonanSblm.getLuasTerlibat() != null) {
                                        hpa.setLuasTerlibat(hakmilikPermohonanSblm.getLuasTerlibat());
                                    }
                                    if (hakmilikPermohonanSblm.getKodUnitLuas() != null) {
                                        hpa.setKodUnitLuas(hakmilikPermohonanSblm.getKodUnitLuas());
                                    }
                                    if (hakmilikPermohonanSblm.getCatatan() != null) {
                                        hpa.setCatatan(hakmilikPermohonanSblm.getCatatan());
                                    }
                                    if (hakmilikPermohonanSblm.getTanahDiambil() != null) {
                                        hpa.setTanahDiambil(hakmilikPermohonanSblm.getTanahDiambil());
                                    }
                                }
                                InfoAudit ia = new InfoAudit();
                                ia.setDimasukOleh(peng);
                                ia.setTarikhMasuk(new java.util.Date());
                                hpa.setInfoAudit(ia);
                                pengambilanService.simpanHakmilikPermohonan(hpa);
                            }
                        } else {
                            if (hakmilikPermohonanSblm.getLuasTerlibat() != null) {
                                hakmilikPermohonan.setLuasTerlibat(hakmilikPermohonanSblm.getLuasTerlibat());
                            }
                            if (hakmilikPermohonanSblm.getKodUnitLuas() != null) {
                                hakmilikPermohonan.setKodUnitLuas(hakmilikPermohonanSblm.getKodUnitLuas());
                            }
                            if (hakmilikPermohonanSblm.getCatatan() != null) {
                                hakmilikPermohonan.setCatatan(hakmilikPermohonanSblm.getCatatan());
                            }
                            if (hakmilikPermohonanSblm.getTanahDiambil() != null) {
                                hakmilikPermohonan.setTanahDiambil(hakmilikPermohonanSblm.getTanahDiambil());
                            }
                            hakmilikPermohonan.setPenarikBalikan("0");
                            pengambilanService.simpanHakmilikPermohonan(hakmilikPermohonan);
                        }
                        
                    }
                }
                
                getContext().getRequest().setAttribute("Maklumat_Pemohon", Boolean.TRUE);
            } else {
                getContext().getRequest().setAttribute("Maklumat_Pemohon", Boolean.FALSE);
                addSimpleError("Id Permohonan Sebelum tidak dijumpai");
            }
        }
        
        semakIdSebelum();
        
        return new JSP("pengambilan/permohonan_penarikanbalik1.jsp").addParameter("tab", "true");
    }
    
    public void semakIdSebelum() {
        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = permohonanDAO.findById(idPermohonan);
        
        if (permohonan.getPermohonanSebelum() != null) {
            idSblm = permohonan.getPermohonanSebelum().getIdPermohonan();
        }
        
        if (idSblm != null) {
            idSblm = idSblm.trim();
        }
        logger.debug("idSblm no" + idSblm);
        
        pemohonList = pengambilanService.findByIdSblm2(idSblm);
        hakmilikPermohonanList = pengambilanService.findByIdSebelum(idSblm);
        
        if (idSblm != null) {

//            Dokumen dokumenK = null;
            FolderDokumen folder = permohonan.getPermohonanSebelum().getFolderDokumen();
            if (folder != null) {
                List<KandunganFolder> listKandunganFolder = folder.getSenaraiKandungan();
                if (listKandunganFolder != null && listKandunganFolder.size() != 0) {
                    for (KandunganFolder kf : listKandunganFolder) {
                        if (kf.getDokumen().getKodDokumen() != null) {
                            if (kf.getDokumen().getKodDokumen().getKod().equals("K")) {
                                dokumenK = kf.getDokumen();
                            }
                        }
                    }
                }
            }
            
            if (dokumenK != null) {
                getContext().getRequest().setAttribute("Maklumat_Pemohon", Boolean.FALSE);
                getContext().getRequest().setAttribute("Borang_K", Boolean.TRUE);
                getContext().getRequest().getSession().setAttribute("idSblm", idSblm);
                getContext().getRequest().getSession().setAttribute("namaProjek", namaProjek);
                getContext().getRequest().getSession().setAttribute("Borang_K", "Borang_K");
            }
            
            List<PermohonanRujukanLuar> permohonanRujukanLuarList = pengambilanService.findMohonRujukanLuarListbyIdMohon(idSblm);
            if (permohonanRujukanLuarList.size() > 0) {
                permohonanRujukanLuar = permohonanRujukanLuarList.get(0);
            }
            
            if (permohonanRujukanLuar != null) {
                if (permohonanRujukanLuar.getTarikhLulus() != null) {
                    tarikhLulus = sdf.format(permohonanRujukanLuar.getTarikhLulus()).substring(0, 10);
                }
            }
            
            if (idSblm != null) {
                Permohonan idSebelum = permohonanDAO.findById(idSblm);
                hakmilikPermohonanList = idSebelum.getSenaraiHakmilik();
                
                for (int i = 0; i < hakmilikPermohonanList.size(); i++) {
                    HakmilikPermohonan hmp = hakmilikPermohonanList.get(i);
                    hakmilikPerbicaraanList = pengambilanService.getHakmilikPerbicaraanByidMHList(hmp.getId());
                    if (hakmilikPerbicaraanList != null && hakmilikPerbicaraanList.size() > 0) {
                        
                        keputusanNilai = BigDecimal.ZERO;
                        
                        for (Iterator<HakmilikPerbicaraan> bicara = hakmilikPerbicaraanList.iterator(); bicara.hasNext();) {
                            hakmilikPerbicaraan = bicara.next();
                            if (hakmilikPerbicaraan != null) {
                                if (hakmilikPerbicaraan.getKeputusanNilai() != null) {
                                    keputusanNilaiArrayList.add(hakmilikPerbicaraan.getKeputusanNilai());
                                    for (int k = 0; k < keputusanNilaiArrayList.size(); k++) {
                                        System.out.println(keputusanNilaiArrayList.get(k));
                                        keputusanNilaiArrayList.get(k);
                                    }
                                }
                                
                                if (hakmilikPerbicaraan.getTarikhBicara() != null) {
                                    tarikhBicaraList.add(sdf.format(hakmilikPerbicaraan.getTarikhBicara()));
                                    logger.debug(tarikhBicaraList + " tarikhBicaraList");
                                    for (int j = 0; j < tarikhBicaraList.size(); j++) {
                                        System.out.println(tarikhBicaraList.get(j));
                                        tarikhBicaraList.get(j);
                                    }
                                }
                            } else {
                                addSimpleError("Tiada Data Pada Id Permohonan Terdahulu Berkenaan Tarikh Bicara dan Award yang ditawarkan");
                            }
                        }
                    } else {
                        size = hakmilikPerbicaraanList.size();
                        if (size == 0) {
                            logger.info("hakmilikperbicaraan list 0");
                        }
                        
                    }
                }
                
            }
            
            if (permohonanRujukanLuar != null || hakmilikPerbicaraan != null) {
                if (dokumenK == null) {
                    getContext().getRequest().setAttribute("Maklumat_Pemohon", Boolean.TRUE);
                    getContext().getRequest().getSession().setAttribute("idSblm", idSblm);
                    getContext().getRequest().getSession().setAttribute("namaProjek", namaProjek);
                    getContext().getRequest().getSession().removeAttribute("Borang_K");
                    logger.debug("------Borang_K idSblm no" + getContext().getRequest().getSession().getAttribute("idSblm"));
                }
            } else {
                addSimpleError("Tiada maklumat Pada Id Permohonan Terdahulu ");
                getContext().getRequest().getSession().removeAttribute("idSblm");
                getContext().getRequest().getSession().removeAttribute("namaProjek");
                getContext().getRequest().getSession().removeAttribute("Borang_K");
            }
        }
        
        if (permohonan.getPermohonanSebelum() == null) {
            getContext().getRequest().setAttribute("isSimpanVisible", Boolean.TRUE);
        }
    }
    
    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {
        
        pemohonList = new ArrayList<Pemohon>();
        hakmilikPermohonanList = new ArrayList<HakmilikPermohonan>();
        hakmilikPerbicaraanList = new ArrayList<HakmilikPerbicaraan>();
        
        permohonanRujukanLuar = pengambilanService.findByIdPermohonan(idSblm);
        hakmilikPermohonan = pengambilanService.findPermohonanByIdPermohonan(idSblm);
        if (StringUtils.isNotBlank(idSblm)) {
            permohonan = permohonanDAO.findById(idSblm);
            //B4 this comment
            pemohonList = pengambilanService.findByIdSblm2(idSblm);
            //b4 this comment
            pihak = pengambilanService.findByIdPihak(pemohon.getPihak().getIdPihak());
            //4 view data tarikhBicara n nilai_kpsn
            hakmilikPermohonanList = pengambilanService.findByIdSebelum(idSblm);
            hakmilikPerbicaraan = pengambilanService.getHakmilikPerbicaraanByidMH(hakmilikPermohonan.getId());
            hakmilikPerbicaraanList = pengambilanService.getHakmilikPerbicaraanByidMHnIdSblm(hakmilikPermohonan.getId(), idSblm);
            logger.debug("idPermohonan :" + idPermohonan);
        }
        
        if (permohonan != null) {
            idSblm = permohonan.getPermohonanSebelum().getIdPermohonan();
            namaProjek = permohonan.getSebab();
//            kodUrusanSblm = permohonan.getKodUrusan();
            kodUrusanSblm = permohonan.getPermohonanSebelum().getKodUrusan();
            logger.info("kod urusan" + permohonan.getPermohonanSebelum().getKodUrusan());
        }
        if (permohonanRujukanLuar != null) {
            tarikhLulus = sdf.format(permohonanRujukanLuar.getTarikhLulus()).substring(0, 10);
        }
        
        List<PermohonanRujukanLuar> permohonanRujukanLuarList = pengambilanService.findMohonRujukanLuarListbyIdMohon(idSblm);
        if (permohonanRujukanLuarList.size() > 0) {
            permohonanRujukanLuar = permohonanRujukanLuarList.get(0);
        }
        
        if (idSblm != null) {
            Permohonan idSebelum = permohonanDAO.findById(idSblm);
            hakmilikPermohonanList = idSebelum.getSenaraiHakmilik();
            
            for (int i = 0; i < hakmilikPermohonanList.size(); i++) {
                HakmilikPermohonan hmp = hakmilikPermohonanList.get(i);
                hakmilikPerbicaraanList = pengambilanService.getHakmilikPerbicaraanByidMHList(hmp.getId());
                if (hakmilikPerbicaraanList != null && hakmilikPerbicaraanList.size() > 0) {
                    keputusanNilai = BigDecimal.ZERO;
                    
                    for (Iterator<HakmilikPerbicaraan> bicara = hakmilikPerbicaraanList.iterator(); bicara.hasNext();) {
                        hakmilikPerbicaraan = bicara.next();
                        
                        if (hakmilikPerbicaraan != null) {
                            if (hakmilikPerbicaraan.getKeputusanNilai() != null) {
                                keputusanNilaiArrayList.add(hakmilikPerbicaraan.getKeputusanNilai());
                                for (int k = 0; k < keputusanNilaiArrayList.size(); k++) {
                                    System.out.println(keputusanNilaiArrayList.get(k));
                                    keputusanNilaiArrayList.get(k);
                                }
                            }
                            
                            if (hakmilikPerbicaraan.getTarikhBicara() != null) {
                                tarikhBicaraList.add(sdf.format(hakmilikPerbicaraan.getTarikhBicara()));
                                for (int j = 0; j < tarikhBicaraList.size(); j++) {
                                    System.out.println(tarikhBicaraList.get(j));
                                    tarikhBicaraList.get(j);
                                }
                            }
                        } else {
                            addSimpleError("Tiada Data Pada Id Permohonan Terdahulu Berkenaan Tarikh Bicara dan Award yang ditawarkan");
                        }
                    }
                }
            }
        }
    }
    
    public Resolution simpan() {
        logger.debug("start simpan");
        String idPermohonan = (String) getContext().getRequest().getParameter("idPermohonan");
        permohonan = permohonanDAO.findById(idPermohonan);
        cawangan = permohonan.getCawangan();

//        if (pemohon == null) {
//            pemohon = new Pemohon();
//        }
//        pihak = new Pihak();
        pemohon.setPermohonan(permohonan);
        pemohon.setCawangan(kodCawanganDAO.findById(cawangan.getKod()));
        
        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        InfoAudit ia = pemohon.getInfoAudit();
        if (ia == null) {
            ia = new InfoAudit();
            ia.setDimasukOleh(peng);
            ia.setTarikhMasuk(new Date());
            pemohon.setInfoAudit(ia);
            pihak.setInfoAudit(ia);
        } else {
//            ia.setTarikhMasuk(new Date());
            ia.setDiKemaskiniOleh(peng);
            ia.setTarikhKemaskini(new java.util.Date());
        }
//        nama = getContext().getRequest().getParameter("nama");
//        pihak.setNama(nama);
//        pemohon.setPihak(pihak);

//        pengambilanService.simpanPihak(pihak);
//        pengambilanService.simpanNamaPemohon(pemohon);

        addSimpleMessage("Maklumat telah berjaya disimpan.");
//        logger.debug("MaklumatLokasiActionBean::simpan::" + aduanSiasatan.getIdSiasatan());
        return new JSP("pengambilan/permohonan_penarikanbalik1.jsp").addParameter("tab", "true");
    }

    // Click on Cari button in SearchPage to search based on criteria
    public Resolution cariIdSebelumOSebab() {
//        String idPermohonan = (String) getContext().getRequest().getParameter("idPermohonan");
        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        Permohonan permohonanPB = permohonanDAO.findById(idPermohonan);
        kodUrusan = permohonanPB.getKodUrusan().getKod();
        logger.info("kod urusan :: " + kodUrusan);
        senaraiHakmilikPB = permohonanPB.getSenaraiHakmilik();
        logger.info("senaraiHakmilikPB :: " + permohonanPB.getSenaraiHakmilik().size());

        listIdMohon = new ArrayList<Permohonan>();
        if (kodUrusan.equalsIgnoreCase("PHL")) {
            if (id != null) {
                listIdMohon = pengambilanService.searchIdMohon(id, sebabProjek, idPermohonan, kodUrusan);
                if (listIdMohon.size() < 1) {
                    permohonan = new Permohonan();
                    permohonan.setIdPermohonan(id);
                    addSimpleError("Id Permohonan Tidak Dijumpai");
                }
            } else {
                listIdMohon = pengambilanService.searchIdMohon("%ACQ", sebabProjek, idPermohonan, kodUrusan);
                
                if (listIdMohon.size() < 1) {
                    permohonan = new Permohonan();
                    permohonan.setIdPermohonan(id);
                }
            }
            
            
            
        } else {
            
            if (id != null) {
                listIdMohon = pengambilanService.searchIdMohon(id, sebabProjek, idPermohonan, "8");
//                logger.info("listIdMohon :: " + permohonan.getSenaraiHakmilik().size());

                if (listIdMohon.size() < 1) {
                    permohonan = new Permohonan();
                    permohonan.setIdPermohonan(id);
                    addSimpleError("Id Permohonan Tidak Dijumpai");
                }
            } else {
                listIdMohon = pengambilanService.searchIdMohon("%ACQ", sebabProjek, idPermohonan, "8");
                logger.info("listIdMohon :: " + listIdMohon.size());

                if (listIdMohon.size() < 1) {
                    permohonan = new Permohonan();
                    permohonan.setIdPermohonan(id);
                }
            }
        }
        return new JSP("pengambilan/search_IdMohonOSebab.jsp").addParameter("popup", "true");
    }

    // Save simpanIdMohonOSebab details
    public Resolution simpanIdMohonOSebab() {
        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        InfoAudit info = peng.getInfoAudit();
        
        if (idPermohonan != null) {
            info.setDimasukOleh(peng);
            info.setTarikhMasuk(new java.util.Date());
            permohonan.setInfoAudit(info);
            permohonan.setIdPermohonan(idPermohonan);
            permohonan.setSebab(sebab);
            pengambilanService.simpanIdMohonOSebab(permohonan);
            addSimpleMessage("Maklumat telah berjaya disimpan");
        }
        
        return new JSP("pengambilan/search_IdMohonOSebab.jsp").addParameter("popup", "true");
        
    }
    
    public Resolution genReport() {
        Pengguna pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = permohonanDAO.findById(idPermohonan);
        
        if (permohonan.getPermohonanSebelum() != null) {
            idSblm = permohonan.getPermohonanSebelum().getIdPermohonan();
        }
        if (idSblm != null && idPermohonan != null) {
            idSblm = idSblm.trim();
            idSblm = permohonan.getPermohonanSebelum().getIdPermohonan();
            permohonanSebelum = permohonanDAO.findById(idSblm);
        }
        
        System.out.println("genReport :: start");
        System.out.println("generate report start.");
        String msg = "Laporan telah dijana. Sila buat semakan sebelum cetakan.";
        String gen = "";
        if ("04".equals(conf.getProperty("kodNegeri"))) {
            //melaka
            gen = "ACQSrtTolakPB_MLK.rdf";
        }
        if ("05".equals(conf.getProperty("kodNegeri"))) {
            //negeri9
            gen = "ACQSrtTolakPB_NS.rdf";
        }
        System.out.println("permohonanSebelum" + permohonanSebelum);
        String code = "8TOL";
        System.out.println("genReportFromXML");
        lelongService.reportGen(permohonan, gen, code, pengguna);
        System.out.println("permohonanSebelum" + permohonanSebelum);
        System.out.println("genReport :: finish");
        return new StreamingResolution("text/plain", msg);
    }
    
    public Permohonan getPermohonan() {
        return permohonan;
    }
    
    public void setPermohonan(Permohonan permohonan) {
        this.permohonan = permohonan;
    }
    
    public KodDokumen getKodDokumenRujukan() {
        return kodDokumenRujukan;
    }
    
    public void setKodDokumenRujukan(KodDokumen kodDokumenRujukan) {
        this.kodDokumenRujukan = kodDokumenRujukan;
    }
    
    public PermohonanRujukanLuar getPermohonanRujukanLuar() {
        return permohonanRujukanLuar;
    }
    
    public void setPermohonanRujukanLuar(PermohonanRujukanLuar permohonanRujukanLuar) {
        this.permohonanRujukanLuar = permohonanRujukanLuar;
    }
    
    public String getidSblm() {
        return idSblm;
    }
    
    public void setidSblm(String idSblm) {
        this.idSblm = idSblm;
    }
    
    public String getBorangK() {
        return borangK;
    }
    
    public void setBorangK(String borangK) {
        this.borangK = borangK;
    }
    
    public String getIdKos() {
        return idKos;
    }
    
    public void setIdKos(String idKos) {
        this.idKos = idKos;
    }
    
    public List<Pemohon> getPemohonList() {
        return pemohonList;
    }
    
    public void setPemohonList(List<Pemohon> pemohonList) {
        this.pemohonList = pemohonList;
    }
    
    public String getItem() {
        return item;
    }
    
    public void setItem(String item) {
        this.item = item;
    }
    
    public Pemohon getPemohon() {
        return pemohon;
    }
    
    public void setPemohon(Pemohon pemohon) {
        this.pemohon = pemohon;
    }
    
    public Pihak getPihak() {
        return pihak;
    }
    
    public void setPihak(Pihak pihak) {
        this.pihak = pihak;
    }
    
    public String getNamaProjek() {
        return namaProjek;
    }
    
    public void setNamaProjek(String namaProjek) {
        this.namaProjek = namaProjek;
    }
    
    public List<BigDecimal> getKeputusanNilaiArrayList() {
        return keputusanNilaiArrayList;
    }
    
    public void setKeputusanNilaiArrayList(List<BigDecimal> keputusanNilaiArrayList) {
        this.keputusanNilaiArrayList = keputusanNilaiArrayList;
    }
    
    public String getIndex() {
        return index;
    }
    
    public void setIndex(String index) {
        this.index = index;
    }
    
    public List<Permohonan> getListIdMohon() {
        return listIdMohon;
    }
    
    public void setListIdMohon(List<Permohonan> listIdMohon) {
        this.listIdMohon = listIdMohon;
    }
    
    public String getSebabProjek() {
        return sebabProjek;
    }
    
    public void setSebabProjek(String sebabProjek) {
        this.sebabProjek = sebabProjek;
    }
    
    public String getIdPermohonan() {
        return idPermohonan;
    }
    
    public void setIdPermohonan(String idPermohonan) {
        this.idPermohonan = idPermohonan;
    }
    
    public String getId() {
        return id;
    }
    
    public void setId(String id) {
        this.id = id;
    }
    
    public String getRadio_() {
        return radio_;
    }
    
    public void setRadio_(String radio_) {
        this.radio_ = radio_;
    }
    
    public String getSebab() {
        return sebab;
    }
    
    public void setSebab(String sebab) {
        this.sebab = sebab;
    }
    
    public KodCawangan getCawangan() {
        return cawangan;
    }
    
    public void setCawangan(KodCawangan cawangan) {
        this.cawangan = cawangan;
    }
    
    public HakmilikPerbicaraan getHakmilikPerbicaraan() {
        return hakmilikPerbicaraan;
    }
    
    public void setHakmilikPerbicaraan(HakmilikPerbicaraan hakmilikPerbicaraan) {
        this.hakmilikPerbicaraan = hakmilikPerbicaraan;
    }
    
    public HakmilikPermohonan getHakmilikPermohonan() {
        return hakmilikPermohonan;
    }
    
    public void setHakmilikPermohonan(HakmilikPermohonan hakmilikPermohonan) {
        this.hakmilikPermohonan = hakmilikPermohonan;
    }
    
    public String getIdSblm() {
        return idSblm;
    }
    
    public void setIdSblm(String idSblm) {
        this.idSblm = idSblm;
    }
    
    public Permohonan getPermohonanSebelum() {
        return permohonanSebelum;
    }
    
    public void setPermohonanSebelum(Permohonan permohonanSebelum) {
        this.permohonanSebelum = permohonanSebelum;
    }
    
    public List<HakmilikPermohonan> getHakmilikPermohonanList() {
        return hakmilikPermohonanList;
    }
    
    public void setHakmilikPermohonanList(List<HakmilikPermohonan> hakmilikPermohonanList) {
        this.hakmilikPermohonanList = hakmilikPermohonanList;
    }
    
    public List<HakmilikPerbicaraan> getHakmilikPerbicaraanList() {
        return hakmilikPerbicaraanList;
    }
    
    public void setHakmilikPerbicaraanList(List<HakmilikPerbicaraan> hakmilikPerbicaraanList) {
        this.hakmilikPerbicaraanList = hakmilikPerbicaraanList;
    }
    
    public String getTarikhLulus() {
        return tarikhLulus;
    }
    
    public void setTarikhLulus(String tarikhLulus) {
        this.tarikhLulus = tarikhLulus;
    }
    
    public List<String> getTarikhBicaraList() {
        return tarikhBicaraList;
    }
    
    public void setTarikhBicaraList(List<String> tarikhBicaraList) {
        this.tarikhBicaraList = tarikhBicaraList;
    }
    
    public BigDecimal getKeputusanNilai() {
        return keputusanNilai;
    }
    
    public void setKeputusanNilai(BigDecimal keputusanNilai) {
        this.keputusanNilai = keputusanNilai;
    }
    
    public String getTarikhBicara() {
        return tarikhBicara;
    }
    
    public void setTarikhBicara(String tarikhBicara) {
        this.tarikhBicara = tarikhBicara;
    }
    
    public List<BigDecimal> getHakmilikAmaunList() {
        return hakmilikAmaunList;
    }
    
    public void setHakmilikAmaunList(List<BigDecimal> hakmilikAmaunList) {
        this.hakmilikAmaunList = hakmilikAmaunList;
    }
    
    public List<HakmilikPermohonan> getSenaraiHakmilikPermohonan() {
        return senaraiHakmilikPermohonan;
    }
    
    public void setSenaraiHakmilikPermohonan(List<HakmilikPermohonan> senaraiHakmilikPermohonan) {
        this.senaraiHakmilikPermohonan = senaraiHakmilikPermohonan;
    }
    
    public Configuration getConf() {
        return conf;
    }
    
    public void setConf(Configuration conf) {
        this.conf = conf;
    }
    
    public Dokumen getDokumen() {
        return dokumen;
    }
    
    public void setDokumen(Dokumen dokumen) {
        this.dokumen = dokumen;
    }
    
    public Dokumen getDokumenK() {
        return dokumenK;
    }
    
    public void setDokumenK(Dokumen dokumenK) {
        this.dokumenK = dokumenK;
    }
    
    public int getSize() {
        return size;
    }
    
    public void setSize(int size) {
        this.size = size;
    }
    
    public List<HakmilikPermohonan> getSenaraiHakmilikPB() {
        return senaraiHakmilikPB;
    }
    
    public void setSenaraiHakmilikPB(List<HakmilikPermohonan> senaraiHakmilikPB) {
        this.senaraiHakmilikPB = senaraiHakmilikPB;
    }
    
    public Permohonan getCurPermohonan() {
        return curPermohonan;
    }
    
    public void setCurPermohonan(Permohonan curPermohonan) {
        this.curPermohonan = curPermohonan;
    }
    
    public String getKodUrusan() {
        return kodUrusan;
    }
    
    public void setKodUrusan(String kodUrusan) {
        this.kodUrusan = kodUrusan;
    }
}
