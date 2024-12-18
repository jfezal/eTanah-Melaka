/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.stripes.pengambilan;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.dao.DokumenDAO;
import etanah.dao.HakmilikDAO;
import etanah.dao.JUBLDAO;
import etanah.dao.KodStatusTerimaDAO;
import etanah.dao.PermohonanDAO;
import etanah.dao.PermohonanJUBLDAO;
import etanah.model.Dokumen;
import etanah.model.Hakmilik;
import etanah.model.HakmilikPermohonan;
import etanah.model.InfoAudit;
import etanah.model.JUBL;
import etanah.model.KodCawangan;
import etanah.model.KodStatusTerima;
import etanah.model.ambil.PermohonanPengambilan;
import etanah.model.Notis;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.model.PermohonanPihak;
import etanah.model.ambil.HakmilikPerbicaraan;
import etanah.model.ambil.PerbicaraanKehadiran;
import etanah.service.common.PermohonanPihakService;
import etanah.service.common.DokumenService;
import etanah.model.PermohonanJUBL;
import etanah.service.PengambilanService;
import etanah.service.common.NotisPenerimaanService;
//import etanah.service.common.NotisPenerimaanService;
import etanah.service.common.PerbicaraanService;
import etanah.view.etanahActionBeanContext;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import javassist.bytecode.stackmap.BasicBlock;
import javax.swing.JOptionPane;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.RedirectResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import org.hibernate.Session;
import org.apache.log4j.Logger;
import net.sourceforge.stripes.controller.LifecycleStage;
import org.apache.commons.lang.StringUtils;

@UrlBinding("/pengambilan/penerimaan_borang")
public class PenerimaanBorangActionBean extends AbleActionBean {

    private static final Logger logger = Logger.getLogger(PenerimaanBorangActionBean.class);
    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    JUBL jubl;
    @Inject
    HakmilikDAO hakmilikDAO;
    @Inject
    DokumenDAO dokumenDAO;
    @Inject
    PermohonanPengambilan permohonanPengambilan;
    @Inject
    KodStatusTerimaDAO kodStatusTerimaDAO;
    @Inject
    PermohonanJUBLDAO permohonanJUBLDAO;
    @Inject
    JUBLDAO jubldao;
    @Inject
    PengambilanService service;
    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;
//    @Inject
//    NotisPenerimaanService notisPenerimaanService;
    private Hakmilik hakmilik;
    private HakmilikPerbicaraan hakmilikPerbicaraan;
    private PerbicaraanKehadiran perbicaraanKehadiran;
    private DokumenService dokumenService;
    private PermohonanPihakService permohonanPihakService;
    private PermohonanJUBL permohonanJUBL;
    @Inject
    NotisPenerimaanService notisPenerimaanService;
    @Inject
    private PerbicaraanService perbicaraanService;
    private Permohonan permohonan;
    private String idPermohonan;
    private String idHakmilik;
    private String idPihak;
    private String idDokumen;
    private String tajuk;
    private String tarikhNotis;
    private String kodStatusTerima;
    private String tarikhHantar;
    private String tarikhTampal;
    private String catatan;
    private String idPerbicaraan;
    private String hakmilikPerbicaraan1;
    private List<PermohonanPihak> permohonanPihakList;
    private List<HakmilikPermohonan> hakmilikPermohonanList;
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    SimpleDateFormat sdf2 = new SimpleDateFormat("dd/MM/yyyy hh:mm aa");
    private List<String> tarikhBicara = new ArrayList<String>();
    private List<String> waktuPerbicaraan = new ArrayList<String>();
    private List<String> bantahanPenilai = new ArrayList<String>();
    private List<String> lokasiBicara = new ArrayList<String>();
    private List<String> sebabTangguh = new ArrayList<String>();
    private List<String> noResitF = new ArrayList<String>();
    //   private List<String> tarikhUlangan = new ArrayList<String>();
    //  private List<String> tarikhSuratPenilai = new ArrayList<String>();
    private List<String> jam = new ArrayList<String>();
    private List<String> minit = new ArrayList<String>();
    private List<String> ampm = new ArrayList<String>();
    private List<BigDecimal> nilaianPenilai = new ArrayList<BigDecimal>();
    private String tarikhUlangan;
    private String tarikhSuratPenilai;
    private String namaJUBL;
    private String tarikhRujukan;
    private String noRujukan;
    private String ulasan;
    private String projekKerajaan;
    private String rancanganPerumahan;
    private String rizabMelayu;
    private String rizabHutan;
    private String noWarta;

    @DefaultHandler
    public Resolution showForm() {
//        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        return new JSP("pengambilan/BorangE.jsp").addParameter("tab", "true");
    }

    public Resolution showForm2() {
//        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        return new JSP("pengambilan/upen.jsp").addParameter("tab", "true");
    }

    public Resolution hakmilikDetails() {
        hakmilik = hakmilikDAO.findById(idHakmilik);
        return new JSP("pengambilan/BorangE.jsp").addParameter("tab", "true");
    }

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {

        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        // Permohonan permohonan = permohonanDAO.findById(idPermohonan);
        //  KodCawangan cawangan = permohonan.getCawangan();
        permohonanJUBL = perbicaraanService.findPermohonanJUBLbyid(idPermohonan);
        if (idPermohonan != null) {
            permohonan = permohonanDAO.findById(idPermohonan);
            try {

                if (permohonanJUBL.getTarikhRujukan() != null) {
                    tarikhRujukan = sdf.format(permohonanJUBL.getTarikhRujukan());

                }
                if (permohonanJUBL.getNamaJUBL() != null) {
                    namaJUBL = permohonanJUBL.getNamaJUBL();
                }
                if (permohonanJUBL.getNoRujukan() != null) {
                    noRujukan = permohonanJUBL.getNoRujukan();
                }
                if (permohonanJUBL.getCatatan() != null) {
                    catatan = permohonanJUBL.getCatatan();
                }
                if (permohonanJUBL.getUlasan() != null) {
                    ulasan = permohonanJUBL.getUlasan();
                }
            } catch (Exception e) {
            }
        }

//        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
//        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
//        catatan = (String) getContext().getRequest().getSession().getAttribute("catatan");
//        InfoAudit info = new InfoAudit();
//        
        if (idPermohonan != null) {
            Permohonan p = permohonanDAO.findById(idPermohonan);
            permohonanPihakList = p.getSenaraiPihak();
            hakmilikPermohonanList = p.getSenaraiHakmilik();

            for (int i = 0; i < hakmilikPermohonanList.size(); i++) {

                HakmilikPermohonan hmp = hakmilikPermohonanList.get(i);
                hakmilikPerbicaraan = perbicaraanService.getHakmilikPerbicaraanByIdMH(hmp.getId());

                if (hakmilikPerbicaraan != null) {

                    try {
                        String lb = hakmilikPerbicaraan.getLokasiBicara();
                        lokasiBicara.add(lb);
                        String tb = sdf.format(hakmilikPerbicaraan.getTarikhBicara());
                        String masa = hakmilikPerbicaraan.getKeteranganPenuntut();
                        tarikhBicara.add(tb);
//                        tarikhUlangan = sdf.format(hakmilikPerbicaraan.getTarikhUlangan());
//                        tarikhSuratPenilai = sdf.format(hakmilikPerbicaraan.getTarikhSuratPenilai());
                        // String tb1 = masa.substring(0, 2);
                        String tb2 = masa.substring(0, 2);
                        String tb3 = masa.substring(3, 5);
                        String tb4 = masa.substring(6, 8);
                        jam.add(tb2);
                        minit.add(tb3);
                        ampm.add(tb4);
                        String lb2 = hakmilikPerbicaraan.getBantahanPenilai();
                        bantahanPenilai.add(lb2);
                        String lb3 = hakmilikPerbicaraan.getSebabTangguh();
                        sebabTangguh.add(lb3);
                        BigDecimal lb4 = hakmilikPerbicaraan.getNilaianPenilai();
                        nilaianPenilai.add(lb4);
                        tarikhUlangan = sdf.format(hakmilikPerbicaraan.getTarikhUlangan());
                        tarikhSuratPenilai = sdf.format(hakmilikPerbicaraan.getTarikhSuratPenilai());


                    } catch (Exception e) {
                        System.out.println("error " + e.getMessage());
                    }

                }
            }
        }
//        if (idPermohonan != null) {
//            Permohonan permohonan = permohonanDAO.findById(idPermohonan);
//            KodCawangan cawangan = permohonan.getCawangan();
//            permohonanJUBL = perbicaraanService.findPermohonanJUBLbyid(idPermohonan);
//            try {
//                //  String per= permohonanJUBL.getCatatan();
//
//                String per1 = permohonanJUBL.getNamaJUBL();
//                // namaJUBL.add(per1);
//                String per2 = permohonanJUBL.getNoRujukan();
//                //noRujukan.add(per2);
//                //permohonanJUBL.getTarikhRujukan();
//                String per3 = permohonanJUBL.getUlasan();
//                //ulasan.add(per3);
//            } catch (Exception e) {
//            }
//            
//        }


    }

    public Resolution refreshpage() {
        rehydrate();





        return new RedirectResolution(PenerimaanBorangActionBean.class, "locate");
    }

    public Resolution simpan() throws ParseException {

        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        InfoAudit info = new InfoAudit();

        Permohonan permohonan = permohonanDAO.findById(idPermohonan);
        //hakmilik = hakmilikDAO.findById(idHakmilik);



        if (idPermohonan != null) {
            Notis notis = new Notis();
            info.setDimasukOleh(peng);
            info.setTarikhMasuk(new java.util.Date());
            notis.setInfoAudit(info);
            notis.setPermohonan(permohonan);
            notis.setPihak(null);


            Dokumen dokumen = new Dokumen();
            dokumen.setTajuk(tajuk);
            dokumen.setInfoAudit(info);
            Dokumen dokumen1 = dokumenService.saveOrUpdate(dokumen);

            notis.setDokumenNotis(dokumen1);
            notis.setTarikhNotis(sdf.parse(tarikhNotis));

            KodStatusTerima kodStatusTerima1 = kodStatusTerimaDAO.findById(kodStatusTerima);

            notis.setKodStatusTerima(kodStatusTerima1);
            notis.setTarikhHantar(sdf.parse(tarikhHantar));
            notis.setTarikhTampal(sdf.parse(tarikhTampal));
//            notisPenerimaanService.saveOrUpdateNotis(notis);
            addSimpleMessage("Maklumat telah berjaya disimpan");


        }
        return new ForwardResolution("/WEB-INF/jsp/pengambilan/BorangE.jsp").addParameter("tab", "true");


    }

    public Resolution editDetails() {

        String rowCount = (String) getContext().getRequest().getParameter("rowCount");



        int rowCountval = Integer.parseInt(rowCount);


        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        InfoAudit info = peng.getInfoAudit();
        Permohonan permohonan = permohonanDAO.findById(idPermohonan);

        KodCawangan cawangan = permohonan.getCawangan();

        HakmilikPermohonan hmp = hakmilikPermohonanList.get(rowCountval);
        HakmilikPerbicaraan hakmilikPerbicaraan = new HakmilikPerbicaraan();
        info.setDimasukOleh(peng);
        info.setTarikhMasuk(new java.util.Date());
        hakmilikPerbicaraan.setInfoAudit(info);
        hakmilikPerbicaraan.setCawangan(cawangan);
        hakmilikPerbicaraan.setHakmilikPermohonan(hmp);



        try {
            if (lokasiBicara.get(rowCountval) != null) {
                hakmilikPerbicaraan.setLokasiBicara(lokasiBicara.get(rowCountval));


            }

            if (tarikhBicara.get(rowCountval) != null) {
                String StrTarikhBicara = tarikhBicara.get(rowCountval);
                StrTarikhBicara = StrTarikhBicara + " " + jam.get(rowCountval) + ":" + minit.get(rowCountval) + " " + ampm.get(rowCountval);
                hakmilikPerbicaraan.setTarikhBicara(sdf2.parse(StrTarikhBicara));


            }
        } catch (Exception e) {
            e.printStackTrace();


        }


        return new ForwardResolution("/WEB-INF/jsp/pengambilan/BorangE.jsp").addParameter("tab", "true");




    }

    public Resolution simpanHakmilik() {
        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);

        Permohonan permohonan = permohonanDAO.findById(idPermohonan);
        KodCawangan cawangan = permohonan.getCawangan();

        for (int i = 0; i
                < hakmilikPermohonanList.size(); i++) {

            HakmilikPermohonan hmp = hakmilikPermohonanList.get(i);

            hakmilikPerbicaraan = notisPenerimaanService.getHakmilikPerbicaraanByidMH(hmp.getId());
          //  if (perbicaraanService.getHakmilikPerbicaraanByIdMH(hmp.getId()) == null) {
            if(hakmilikPerbicaraan == null){
                hakmilikPerbicaraan = new HakmilikPerbicaraan();
                InfoAudit info = new InfoAudit();
                info.setDimasukOleh(peng);
                info.setTarikhMasuk(new java.util.Date());
                hakmilikPerbicaraan.setInfoAudit(info);
                hakmilikPerbicaraan.setCawangan(cawangan);
                hakmilikPerbicaraan.setHakmilikPermohonan(hmp);
                hakmilikPerbicaraan.setBatalRizab('T');
                hakmilikPerbicaraan.setKawasanPBT('T');
                hakmilikPerbicaraan.setPelanPembangunan('T');


            } else {
                System.out.println("hmp >> "+hmp.getId());
                //hakmilikPerbicaraan = perbicaraanService.getHakmilikPerbicaraanByIdMH(hmp.getId());
               // InfoAudit ia = hakmilikPerbicaraan.getInfoAudit();
              //  ia.setDiKemaskiniOleh(peng);
              //  ia.setTarikhKemaskini(new java.util.Date());
              //  hakmilikPerbicaraan.setInfoAudit(ia);


            }

            try {

                if (i < lokasiBicara.size()) {
                    if (lokasiBicara.get(i) != null) {
                        hakmilikPerbicaraan.setLokasiBicara(lokasiBicara.get(i));
                    }
                }

                if (i < tarikhBicara.size()) {
                    if (tarikhBicara.get(i) != null) {
                        String StrTarikhBicara = tarikhBicara.get(i);
                        StrTarikhBicara = StrTarikhBicara + " " + jam.get(i) + ":" + minit.get(i) + " " + ampm.get(i);
                        String masa = jam.get(i) + ":" + minit.get(i) + " " + ampm.get(i);
                        System.out.println("tarikh bicara " + StrTarikhBicara);
                        hakmilikPerbicaraan.setTarikhBicara(sdf2.parse(StrTarikhBicara));
                        hakmilikPerbicaraan.setKeteranganPenuntut(masa); // Set masa bicara


                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
                addSimpleError(
                        "Invalid Data");


            }
            perbicaraanService.saveSingleHakmilikPerbicaraan(hakmilikPerbicaraan);


        }

        addSimpleMessage("Maklumat telah berjaya disimpan.");


        return new ForwardResolution("/WEB-INF/jsp/pengambilan/BorangE.jsp").addParameter("tab", "true");


    }

    public Resolution simpanHakmilik2() {

        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);

        Permohonan permohonan = permohonanDAO.findById(idPermohonan);
        KodCawangan cawangan = permohonan.getCawangan();

        for (int i = 0; i < hakmilikPermohonanList.size(); i++) {

            HakmilikPermohonan hmp = hakmilikPermohonanList.get(i);


            if (perbicaraanService.getHakmilikPerbicaraanByIdMH(hmp.getId()) == null) {
                hakmilikPerbicaraan = new HakmilikPerbicaraan();
                InfoAudit info = new InfoAudit();
                info.setDimasukOleh(peng);
                info.setTarikhMasuk(new java.util.Date());
                hakmilikPerbicaraan.setInfoAudit(info);
                hakmilikPerbicaraan.setCawangan(cawangan);
                hakmilikPerbicaraan.setHakmilikPermohonan(hmp);



            } else {
                hakmilikPerbicaraan = perbicaraanService.getHakmilikPerbicaraanByIdMH(hmp.getId());
                InfoAudit ia = hakmilikPerbicaraan.getInfoAudit();
                ia.setDiKemaskiniOleh(peng);
                ia.setTarikhKemaskini(new java.util.Date());
                hakmilikPerbicaraan.setInfoAudit(ia);


            }

            try {

                if (i < bantahanPenilai.size()) {
                    if (bantahanPenilai.get(i) != null) {
                        hakmilikPerbicaraan.setBantahanPenilai(bantahanPenilai.get(i));


                    }
                }
                if (i < sebabTangguh.size()) {
                    if (sebabTangguh.get(i) != null) {
                        hakmilikPerbicaraan.setSebabTangguh(sebabTangguh.get(i));


                    }
                }


                //    if (i < tarikhUlangan.size()) {
                if (tarikhUlangan != null) {
                    String StrtarikhresitD = tarikhUlangan;
                    hakmilikPerbicaraan.setTarikhUlangan(sdf.parse(StrtarikhresitD));


                }
                //}
                //   if (i < tarikhSuratPenilai) {
                if (tarikhSuratPenilai != null) {
                    String StrtarikhresitF = tarikhSuratPenilai;
                    hakmilikPerbicaraan.setTarikhSuratPenilai(sdf.parse(StrtarikhresitF));


                }
                // }
                if (i < nilaianPenilai.size()) {
                    if (nilaianPenilai.get(i) != null) {
                        BigDecimal amaun = nilaianPenilai.get(i);
                        hakmilikPerbicaraan.setNilaianPenilai(amaun);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
                addSimpleError(
                        "Invalid Data");


            }
            perbicaraanService.saveSingleHakmilikPerbicaraan(hakmilikPerbicaraan);


        }
        rehydrate();

        addSimpleMessage("Maklumat telah berjaya disimpan.");


        return new ForwardResolution("/WEB-INF/jsp/pengambilan/upen.jsp").addParameter("tab", "true");

    }

    public Resolution simpanUPEN() {
        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        Permohonan permohonan = permohonanDAO.findById(idPermohonan);
        KodCawangan cawangan = permohonan.getCawangan();
        permohonanJUBL = perbicaraanService.findPermohonanJUBLbyid(idPermohonan);

        //   jubl=service.findJUBL(jubl.getIdJubl());

        System.out.println("permohonanJUBL" + permohonanJUBL);
        System.out.println("jubl" + jubl);

        if (permohonanJUBL == null) {
            permohonanJUBL = new PermohonanJUBL();
            InfoAudit info = peng.getInfoAudit();
            info.setDimasukOleh(peng);
            info.setTarikhMasuk(new java.util.Date());
            permohonanJUBL.setInfoAudit(info);
            System.out.println("info" + info);

//            JUBL jubl = new JUBL();
//            jubl.setNama("test");
//            jubl.setInfoAudit(info);
//            jubl.setCawangan(cawangan);
//            service.saveJUBL2(jubl);
//            jubl = service.findJUBL(jubl.getIdJubl());
//            System.out.println("jublid" + jubl.getIdJubl());
//            permohonanJUBL.setJurukur(jubl);
            permohonanJUBL.setCawangan(cawangan);
            System.out.println("cawangan:" + cawangan);
            permohonanJUBL.setPermohonan(permohonan);
            System.out.println("permohonan:" + permohonan);
            //   }        
        }
        if (idPermohonan != null) {
        } else {
            InfoAudit info = peng.getInfoAudit();
            info.setDimasukOleh(peng);
            info.setTarikhMasuk(new java.util.Date());
            permohonanJUBL.setInfoAudit(info);

        }
        try {

            if (tarikhRujukan != null) {
                String strtarikhRuj = tarikhRujukan;
                permohonanJUBL.setTarikhRujukan(sdf.parse(strtarikhRuj));
                System.out.println("strtarikh" + strtarikhRuj);
            }
            if (namaJUBL != null) {
                permohonanJUBL.setNamaJUBL(namaJUBL);
                System.out.println("namaJUBL:" + namaJUBL);
            } else {
                namaJUBL = permohonanJUBL.getNamaJUBL();
            }
            if (noRujukan != null) {
                permohonanJUBL.setNoRujukan(noRujukan);
                System.out.println("noruj" + noRujukan);
            }
            if (catatan != null) {
                permohonanJUBL.setCatatan(catatan);
            }
            if (ulasan != null) {

                permohonanJUBL.setUlasan(ulasan);
            }
        } catch (Exception e) {
            e.printStackTrace();
            addSimpleError("Invalid Data");
        }
        // service.saveJUBL2(jubl);
        service.saveJUBL(permohonanJUBL);

        //  rehydrate();
        addSimpleMessage("Maklumat telah berjaya disimpan.");
        return new ForwardResolution("/WEB-INF/jsp/pengambilan/upen.jsp").addParameter("tab", "true");


    }

    public List<PermohonanPihak> getPermohonanPihakList() {
        return permohonanPihakList;


    }

    public void setPermohonanPihakList(List<PermohonanPihak> permohonanPihakList) {
        this.permohonanPihakList = permohonanPihakList;


    }

    public List<HakmilikPermohonan> getHakmilikPermohonanList() {
        return hakmilikPermohonanList;


    }

    public void setHakmilikPermohonanList(List<HakmilikPermohonan> hakmilikPermohonanList) {
        this.hakmilikPermohonanList = hakmilikPermohonanList;


    }

    public Hakmilik getHakmilik() {
        return hakmilik;


    }

    public void setHakmilik(Hakmilik hakmilik) {
        this.hakmilik = hakmilik;


    }

    public String getIdHakmilik() {
        return idHakmilik;


    }

    public void setIdHakmilik(String idHakmilik) {
        this.idHakmilik = idHakmilik;


    }

    public String getIdDokumen() {
        return idDokumen;


    }

    public void setIdDokumen(String idDokumen) {
        this.idDokumen = idDokumen;


    }

    public String getIdPihak() {
        return idPihak;


    }

    public void setIdPihak(String idPihak) {
        this.idPihak = idPihak;


    }

    public String getKodStatusTerima() {
        return kodStatusTerima;


    }

    public void setKodStatusTerima(String kodStatusTerima) {
        this.kodStatusTerima = kodStatusTerima;


    }

    public String getTajuk() {
        return tajuk;


    }

    public void setTajuk(String tajuk) {
        this.tajuk = tajuk;


    }

    public String getTarikhHantar() {
        return tarikhHantar;


    }

    public void setTarikhHantar(String tarikhHantar) {
        this.tarikhHantar = tarikhHantar;


    }

    public String getTarikhNotis() {
        return tarikhNotis;


    }

    public void setTarikhNotis(String tarikhNotis) {
        this.tarikhNotis = tarikhNotis;


    }

    public String getTarikhTampal() {
        return tarikhTampal;


    }

    public void setTarikhTampal(String tarikhTampal) {
        this.tarikhTampal = tarikhTampal;


    }

    public List<String> getLokasiBicara() {
        return lokasiBicara;


    }

    public void setLokasiBicara(List<String> lokasiBicara) {
        this.lokasiBicara = lokasiBicara;


    }

    public String getIdPermohonan() {
        return idPermohonan;


    }

    public void setIdPermohonan(String idPermohonan) {
        this.idPermohonan = idPermohonan;


    }

    public List<String> getTarikhBicara() {
        return tarikhBicara;


    }

    public void setTarikhBicara(List<String> tarikhBicara) {
        this.tarikhBicara = tarikhBicara;


    }

    public List<String> getWaktuPerbicaraan() {
        return waktuPerbicaraan;


    }

    public void setWaktuPerbicaraan(List<String> waktuPerbicaraan) {
        this.waktuPerbicaraan = waktuPerbicaraan;


    }

    public List<String> getAmpm() {
        return ampm;


    }

    public void setAmpm(List<String> ampm) {
        this.ampm = ampm;


    }

    public List<String> getJam() {
        return jam;


    }

    public void setJam(List<String> jam) {
        this.jam = jam;


    }

    public List<String> getMinit() {
        return minit;


    }

    public void setMinit(List<String> minit) {
        this.minit = minit;


    }

    public HakmilikPerbicaraan getHakmilikPerbicaraan() {
        return hakmilikPerbicaraan;


    }

    public void setHakmilikPerbicaraan(HakmilikPerbicaraan hakmilikPerbicaraan) {
        this.hakmilikPerbicaraan = hakmilikPerbicaraan;


    }

    public String getCatatan() {
        return catatan;


    }

    public void setCatatan(String catatan) {
        this.catatan = catatan;


    }

    public String getIdPerbicaraan() {
        return idPerbicaraan;


    }

    public void setIdPerbicaraan(String idPerbicaraan) {
        this.idPerbicaraan = idPerbicaraan;


    }

    public String getHakmilikPerbicaraan1() {
        return hakmilikPerbicaraan1;


    }

    public void setHakmilikPerbicaraan1(String hakmilikPerbicaraan1) {
        this.hakmilikPerbicaraan1 = hakmilikPerbicaraan1;


    }

    public PerbicaraanKehadiran getPerbicaraanKehadiran() {
        return perbicaraanKehadiran;


    }

    public void setPerbicaraanKehadiran(PerbicaraanKehadiran perbicaraanKehadiran) {
        this.perbicaraanKehadiran = perbicaraanKehadiran;

    }

    public List<String> getNoResitF() {
        return noResitF;
    }

    public void setNoResitF(List<String> noResitF) {
        this.noResitF = noResitF;
    }

    public List<String> getBantahanPenilai() {
        return bantahanPenilai;
    }

    public void setBantahanPenilai(List<String> bantahanPenilai) {
        this.bantahanPenilai = bantahanPenilai;
    }

    public List<String> getSebabTangguh() {
        return sebabTangguh;
    }

    public void setSebabTangguh(List<String> sebabTangguh) {
        this.sebabTangguh = sebabTangguh;
    }

    public List<BigDecimal> getNilaianPenilai() {
        return nilaianPenilai;
    }

    public String getTarikhUlangan() {
        return tarikhUlangan;
    }

    public void setTarikhUlangan(String tarikhUlangan) {
        this.tarikhUlangan = tarikhUlangan;
    }

    public String getTarikhSuratPenilai() {
        return tarikhSuratPenilai;
    }

    public void setTarikhSuratPenilai(String tarikhSuratPenilai) {
        this.tarikhSuratPenilai = tarikhSuratPenilai;
    }

    public void setNilaianPenilai(List<BigDecimal> nilaianPenilai) {
        this.nilaianPenilai = nilaianPenilai;
    }

    public PermohonanJUBL getPermohonanJUBL() {
        return permohonanJUBL;
    }

    public void setPermohonanJUBL(PermohonanJUBL permohonanJUBL) {
        this.permohonanJUBL = permohonanJUBL;
    }

    public String getNamaJUBL() {
        return namaJUBL;
    }

    public void setNamaJUBL(String namaJUBL) {
        this.namaJUBL = namaJUBL;
    }

    public String getTarikhRujukan() {
        return tarikhRujukan;
    }

    public void setTarikhRujukan(String tarikhRujukan) {
        this.tarikhRujukan = tarikhRujukan;
    }

    public String getNoRujukan() {
        return noRujukan;
    }

    public void setNoRujukan(String noRujukan) {
        this.noRujukan = noRujukan;
    }

    public String getUlasan() {
        return ulasan;
    }

    public void setUlasan(String ulasan) {
        this.ulasan = ulasan;
    }

    public PermohonanPengambilan getPermohonanPengambilan() {
        return permohonanPengambilan;
    }

    public void setPermohonanPengambilan(PermohonanPengambilan permohonanPengambilan) {
        this.permohonanPengambilan = permohonanPengambilan;
    }

    public String getProjekKerajaan() {
        return projekKerajaan;
    }

    public void setProjekKerajaan(String projekKerajaan) {
        this.projekKerajaan = projekKerajaan;
    }

    public String getRancanganPerumahan() {
        return rancanganPerumahan;
    }

    public void setRancanganPerumahan(String rancanganPerumahan) {
        this.rancanganPerumahan = rancanganPerumahan;
    }

    public String getRizabMelayu() {
        return rizabMelayu;
    }

    public void setRizabMelayu(String rizabMelayu) {
        this.rizabMelayu = rizabMelayu;
    }

    public String getRizabHutan() {
        return rizabHutan;
    }

    public void setRizabHutan(String rizabHutan) {
        this.rizabHutan = rizabHutan;
    }

    public String getNoWarta() {
        return noWarta;
    }

    public void setNoWarta(String noWarta) {
        this.noWarta = noWarta;
    }
}
