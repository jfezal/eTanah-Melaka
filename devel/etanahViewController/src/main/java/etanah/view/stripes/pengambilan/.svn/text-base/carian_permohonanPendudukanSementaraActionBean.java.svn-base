/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.stripes.pengambilan;

/**
 *
 * @author massita
 * Modified: search criteria for search_idMohonOSebabPTP jsp By Murali 10-01-2012
 */
import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
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
import etanah.service.PengambilanAduanService;
import etanah.service.PengambilanService;
import etanah.service.common.PerbicaraanService;
import etanah.view.etanahActionBeanContext;
import java.util.Date;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.controller.LifecycleStage;
import org.apache.log4j.Logger;
import org.apache.commons.lang.StringUtils;
import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;
import org.hibernate.Session;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.StringTokenizer;

@UrlBinding("/pengambilan/pemulihanTanahCarianPendudukanSementara")
public class carian_permohonanPendudukanSementaraActionBean extends AbleActionBean {

    private static Logger logger = Logger.getLogger(carian_permohonanPendudukanSementaraActionBean.class);
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
    PemohonService pemohonService;
    @Inject
    PengambilanAduanService aduanService;
    @Inject
    DokumenDAO dokumenDAO;
    @Inject
    HakmilikDAO hakmilikDAO;
    private List<Pemohon> pemohonList;
    private List<HakmilikPermohonan> hakmilikPermohonanList;
    private List<HakmilikPerbicaraan> hakmilikPerbicaraanList;
    private Permohonan permohonan;
    private KodDokumen kodDokumenRujukan;
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
//    4 search variable at search_IdMohonOSebabPTPT
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
    private String tarikhRujukan;
    private Permohonan curPermohonan;

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
                getContext().getRequest().setAttribute("Maklumat_Pemohon", Boolean.TRUE);
                semakIdSebelum();
            }
        }

        return new JSP("pengambilan/carian_PendudukanSementaraTerdahulu.jsp").addParameter("tab", "true");
    }

    // Click on Cari button in Home Page to display SearchPage
    public Resolution IdMohonOSebabPopup() {
        return new JSP("pengambilan/search_IdMohonOSebabPTPT.jsp").addParameter("popup", "true");
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
                if (hakmilikList != null && !hakmilikList.isEmpty()) {
                    StringTokenizer st = new StringTokenizer(hakmilikList);
                    while (st.hasMoreTokens()) {
//                         if(st.nextToken(",") != null){
                        String idHakmilik = st.nextToken(",");
                        hakmilikPermohonan = pengambilanService.findByIdHakmilikIdPermohonan(idPermohonan, idHakmilik);
                        HakmilikPermohonan hakmilikPermohonanSblm = pengambilanService.findByIdHakmilikIdPermohonan(idSblm, idHakmilik);
                        if (hakmilikPermohonan == null) {
                            Hakmilik hakmilik = hakmilikDAO.findById(idHakmilik);
                            if (hakmilik != null) {
                                HakmilikPermohonan hpa = new HakmilikPermohonan();
                                hpa.setPermohonan(pm);
                                hpa.setHakmilik(hakmilik);
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

        return new JSP("pengambilan/carian_PendudukanSementaraTerdahulu.jsp").addParameter("tab", "true");
    }

    public void semakIdSebelum() {
        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = permohonanDAO.findById(idPermohonan);

        if (permohonan.getPermohonanSebelum() != null) {
            idSblm = permohonan.getPermohonanSebelum().getIdPermohonan();
        }

        if (idSblm != null) {
            idSblm = idSblm.trim();
        }
        logger.debug("-------idSblm no------:" + idSblm);

        pemohonList = pengambilanService.findByIdSblm2(idSblm);
        hakmilikPermohonanList = pengambilanService.findByIdSebelum(idSblm);
        logger.debug("----------hakmilikPermohonanList----------::"+hakmilikPermohonanList);
        if (idSblm != null) {

            List<PermohonanRujukanLuar> permohonanRujukanLuarList = pengambilanService.findMohonRujukanLuarListbyIdMohon(idSblm);
            if (permohonanRujukanLuarList.size() > 0) {
                permohonanRujukanLuar = permohonanRujukanLuarList.get(0);
            }

            if (idSblm != null) {
                Permohonan idSebelum = permohonanDAO.findById(idSblm);
                hakmilikPermohonanList = idSebelum.getSenaraiHakmilik();
//                for (int i = 0; i < hakmilikPermohonanList.size(); i++) {
//                    HakmilikPermohonan hp = hakmilikPermohonanList.get(i);
//                    hakmilikPermohonan = pengambilanService.findByIdHakmilikIdPermohonan(permohonan.getIdPermohonan(), hp.getHakmilik().getIdHakmilik());
//                   logger.debug("----------saving in hakmilikPermohonan----------::");
//                    if (hakmilikPermohonan == null) {
//                        logger.debug("----------saving in hakmilikPermohonan----hakmilikPermohonan null------::");
//                        hakmilikPermohonan = new HakmilikPermohonan();
//                        InfoAudit ia = new InfoAudit();
//                        ia.setDimasukOleh(peng);
//                        ia.setTarikhMasuk(new java.util.Date());
//                        hakmilikPermohonan.setInfoAudit(ia);
//                        hakmilikPermohonan.setPermohonan(permohonan);
//                        hakmilikPermohonan.setHakmilik(hp.getHakmilik());
//
//                        if (hp.getLuasTerlibat() != null) {
//                            hakmilikPermohonan.setLuasTerlibat(hp.getLuasTerlibat());
//                        }
//                        if (hp.getKodUnitLuas() != null) {
//                            hakmilikPermohonan.setKodUnitLuas(hp.getKodUnitLuas());
//                        }
//                        if (hp.getKodHakmilik() != null) {
//                            hakmilikPermohonan.setKodHakmilik(hp.getKodHakmilik());
//                        }
//                        if (hp.getCukaiBaru() != null) {
//                            hakmilikPermohonan.setCukaiBaru(hp.getCukaiBaru());
//                        }
//                        if (hp.getSyaratNyataBaru() != null) {
//                            hakmilikPermohonan.setSyaratNyataBaru(hp.getSyaratNyataBaru());
//                        }
//                        if (hp.getSekatanKepentinganBaru() != null) {
//                            hakmilikPermohonan.setSekatanKepentinganBaru(hp.getSekatanKepentinganBaru());
//                        }
//                        if (hp.getKategoriTanahBaru() != null) {
//                            hakmilikPermohonan.setKategoriTanahBaru(hp.getKategoriTanahBaru());
//                        }
//                        if (hp.getTempohPajakan() != null) {
//                            hakmilikPermohonan.setTempohPajakan(hp.getTempohPajakan());
//                        }
//                        if (hp.getLuasPelanB1() != null) {
//                            hakmilikPermohonan.setLuasPelanB1(hp.getLuasPelanB1());
//                        }
//                        if (hp.getLuasPelanB1Uom() != null) {
//                            hakmilikPermohonan.setLuasPelanB1Uom(hp.getLuasPelanB1Uom());
//                        }
//                        pengambilanService.simpanHakmilikPermohonan(hakmilikPermohonan);
//                        logger.debug("----------saved in hakmilikPermohonan----------::");
//                    }
//
//                }

                if (pemohonList != null && pemohonList.size() > 0) {
                    for (int i = 0; i < pemohonList.size(); i++) {
                        Pemohon pem = pemohonList.get(i);
                        if (pem != null) {
                            pemohon = aduanService.findPemohonByPihak(permohonan.getIdPermohonan(), pem.getPihak().getIdPihak());
                            if (pemohon == null) {
                                pemohon = new Pemohon();
                                InfoAudit ia = new InfoAudit();
                                ia.setDimasukOleh(peng);
                                ia.setTarikhMasuk(new java.util.Date());
                                pemohon.setInfoAudit(ia);
                                pemohon.setPermohonan(permohonan);
                                pemohon.setCawangan(permohonan.getCawangan());
                                pemohon.setPihak(pem.getPihak());
                                aduanService.simpanPemohon(pemohon);
                            }

                            //if (permohonanRujukanLuar != null || hakmilikPerbicaraan != null) {
                            if (pemohon.getPihak().getNama() == null) {
                                addSimpleError("Tiada maklumat Pada Id Permohonan Terdahulu ");
                                getContext().getRequest().getSession().removeAttribute("idSblm");
                                getContext().getRequest().getSession().removeAttribute("namaProjek");
                            }
                        }
                    }
                }
            }


        }

        if (permohonan.getPermohonanSebelum() == null) {
            getContext().getRequest().setAttribute("isSimpanVisible", Boolean.TRUE);
        }
    }

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
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
        }
        if (permohonanRujukanLuar != null) {
            tarikhRujukan = sdf.format(permohonanRujukanLuar.getTarikhRujukan()).substring(0, 10);
        }

        List<PermohonanRujukanLuar> permohonanRujukanLuarList = pengambilanService.findMohonRujukanLuarListbyIdMohon(idSblm);
        if (permohonanRujukanLuarList.size() > 0) {
            permohonanRujukanLuar = permohonanRujukanLuarList.get(0);
        }

        if (idSblm != null) {
            Permohonan idSebelum = permohonanDAO.findById(idSblm);
            hakmilikPermohonanList = idSebelum.getSenaraiHakmilik();
        }
    }

    public Resolution simpan() {
        logger.debug("start simpan");
        String idPermohonan = (String) getContext().getRequest().getParameter("idPermohonan");
        permohonan = permohonanDAO.findById(idPermohonan);
        cawangan = permohonan.getCawangan();
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
            ia.setDiKemaskiniOleh(peng);
            ia.setTarikhKemaskini(new java.util.Date());
        }

        addSimpleMessage("Maklumat telah berjaya disimpan.");
        return new JSP("pengambilan/carian_PendudukanSementaraTerdahulu.jsp").addParameter("tab", "true");
    }

    // Click on Cari button in SearchPage to search based on criteria
    public Resolution cariIdSebelumOSebab() {
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        curPermohonan = permohonanDAO.findById(idPermohonan);
        List<Permohonan> senaraiMohonTemp = new ArrayList<Permohonan>();
        listIdMohon = new ArrayList<Permohonan>();
        if (id != null) {
//            senaraiMohonTemp = pengambilanService.searchPermohonan(id, sebabProjek, "PTSP");
            senaraiMohonTemp = pengambilanService.getSeneraiPermohonanByIdHakmilik(id, sebabProjek, "PTSP",idPermohonan);

            if (senaraiMohonTemp != null && senaraiMohonTemp.size() > 0) {
                System.out.println("---senaraiMohonTemp----" + senaraiMohonTemp.size());
                for (Permohonan p : senaraiMohonTemp) {
                    System.out.println("---p.getKodUrusan().getKod()-------" + p.getKodUrusan().getKod());
                    if (p.getKodUrusan().getKod().equalsIgnoreCase("PTSP")) {
                        System.out.println("----PTSP----");
                        listIdMohon.add(p);
                    }
                }
            }

            if (listIdMohon.size() < 1) {
                permohonan = new Permohonan();
                permohonan.setIdPermohonan(id);
                addSimpleError("Id Permohonan Tidak Dijumpai");
            }
        } else {
//            senaraiMohonTemp = pengambilanService.searchPermohonan("%ACQ", sebabProjek, "PTSP");
            senaraiMohonTemp = pengambilanService.getSeneraiPermohonanByIdHakmilik("%ACQ", sebabProjek, "PTSP",idPermohonan);
            if (senaraiMohonTemp != null && senaraiMohonTemp.size() > 0) {
                System.out.println("---senaraiMohonTemp----" + senaraiMohonTemp.size());
                for (Permohonan p : senaraiMohonTemp) {
                    System.out.println("---p.getKodUrusan().getKod()-------" + p.getKodUrusan().getKod());
                    if (p.getKodUrusan().getKod().equalsIgnoreCase("PTSP")) {
                        System.out.println("----PTSP----");
                        listIdMohon.add(p);
                    }
                }
            }

            if (listIdMohon.size() < 1) {
                permohonan = new Permohonan();
                permohonan.setIdPermohonan(id);
            }
        }
        return new JSP("pengambilan/search_IdMohonOSebabPTPT.jsp").addParameter("popup", "true");
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

        return new JSP("pengambilan/search_IdMohonOSebabPTPT.jsp").addParameter("popup", "true");

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

    public String getTarikhRujukan() {
        return tarikhRujukan;
    }

    public void setTarikhRujukan(String tarikhRujukan) {
        this.tarikhRujukan = tarikhRujukan;
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

    public Permohonan getCurPermohonan() {
        return curPermohonan;
    }

    public void setCurPermohonan(Permohonan curPermohonan) {
        this.curPermohonan = curPermohonan;
    }

    
}
