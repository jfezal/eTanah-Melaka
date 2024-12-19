/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.stripes.pengambilan;

import etanah.dao.*;
import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.model.Hakmilik;
import etanah.model.HakmilikPermohonan;
import etanah.model.InfoAudit;
import etanah.model.Pengguna;
import java.text.ParseException;
import java.util.Date;

import etanah.model.Permohonan;
import etanah.model.KodCawangan;
import etanah.model.KodDokumen;
import etanah.model.Pemohon;
import etanah.model.PermohonanKertas;
import etanah.model.PermohonanKertasKandungan;
import etanah.model.PermohonanMahkamah;
import etanah.model.PermohonanPihak;
import etanah.model.ambil.HakmilikPerbicaraan;
import etanah.model.ambil.PerbicaraanKehadiran;

import etanah.view.etanahActionBeanContext;
import java.util.List;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;
import org.apache.log4j.Logger;
import java.text.SimpleDateFormat;
import etanah.service.DrafKertasMMKNService;
import etanah.service.PendudukanSementaraMMKNService;
import etanah.service.PengambilanService;
import etanah.service.PermohonanSupayaBantahanService;
import java.util.ArrayList;
import net.sourceforge.stripes.action.RedirectResolution;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author massita
 */
@UrlBinding("/pengambilan/affidavit_sek8")
public class AffidavitSek8ActionBean extends AbleActionBean {

    private static Logger logger = Logger.getLogger(AffidavitSek8ActionBean.class);
    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;
    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    PendudukanSementaraMMKNService pendudukanSementaraMMKNService;
    @Inject
    PermohonanKertasDAO permohonanKertasDAO;
    @Inject
    HakmilikDAO hakmilikDAO;
    @Inject
    PermohonanKertasKandunganDAO permohonanKertasKandunganDAO;
    @Inject
    PemohonDAO pemohonDAO;
    @Inject
    HakmilikPermohonanDAO hakmilikPermohonanDAO;
    @Inject
    PermohonanSupayaBantahanService permohonanSupayaBantahanService;
    @Inject
    PengambilanService pengambilanService;
    private Permohonan permohonan;
    private HakmilikPermohonan hakmilikPermohonan;
    private String idPermohonan;
    private PermohonanKertas permohonanKertas;
    private PermohonanKertasKandungan masaObj;
    private String tempat;
    private String tarikhmesyuarat;
    private String samanPemulaBil;
    private SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss a");
    private PermohonanKertasKandungan perakuan;
    private PermohonanKertasKandungan perakuan1;
    private PermohonanKertasKandungan diIkrarOleh;
    private PermohonanKertasKandungan tarikhIkrar;
    private PermohonanKertasKandungan samanPemulaBilObj;
    private PermohonanKertasKandungan permohonanKertasKandungan;
    private PermohonanKertasKandungan headingObj;
    private PermohonanKertasKandungan headingObj2;
    private PermohonanKertasKandungan tujuanObj;
    private PermohonanKertasKandungan pesuruhJayaSumpah;
    private List<HakmilikPermohonan> hakmilikPermohonanList;
    private PermohonanMahkamah permohonanMahkamah;
    private PermohonanPihak permohonanPihak;
//    private int bil = 0;
    private String kandungan;
    private String kandungan1;
    private String idKandungan;
    private List<PermohonanKertasKandungan> senaraiKertasKandungan1;
    private Hakmilik hakmilik;
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss aaa");
    private List<Pemohon> listPemohon;
    private String jam;
    private String minit;
    private String pagiPetang;
    private String masa;
    private String subtajuk;
    private String heading;
    private String lokasi;
    private String idHakmilik;
    private long idPihak;
    private String show;
    String namaProjek;
    private HakmilikPerbicaraan hakmilikPerbicaraan;
    private List<PerbicaraanKehadiran> senaraiPerbicaraanKehadiran;

    @DefaultHandler
    public Resolution showForm() {
        Pengguna pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        if (idPermohonan != null) {
            permohonan = permohonanDAO.findById(idPermohonan);
            if (permohonan != null) {
                permohonanSupayaBantahanService.simpanPermohonanPihak(permohonan, pengguna);
            }
        }

        return new JSP("pengambilan/Affidavit_Sek8.jsp").addParameter("tab", "true");
    }

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {

        Pengguna pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = permohonanDAO.findById(idPermohonan);

        if (permohonan != null) {
            hakmilikPermohonanList = permohonan.getSenaraiHakmilik();
        }
    }

    public Resolution hakmilikDetails() {

        if (idPermohonan != null && idHakmilik != null) {
            hakmilik = hakmilikDAO.findById(idHakmilik);
            hakmilikPermohonan= pengambilanService.findByIdHakmilikIdPermohonan(idPermohonan, idHakmilik);
            if(hakmilikPermohonan != null){
                hakmilikPerbicaraan = pengambilanService.getHakmilikPerbicaraanByidMH(hakmilikPermohonan.getId());
                if(hakmilikPerbicaraan != null) {
                    senaraiPerbicaraanKehadiran = pengambilanService.getPerbicaraanKehadiranByKetarangan(hakmilikPerbicaraan.getIdPerbicaraan(), "Mahkamah");
                }
            }
        }

        return new JSP("pengambilan/Affidavit_Sek8.jsp").addParameter("tab", "true");
    }

    public Resolution pihakDetails() {
        logger.debug("-------pihakDetails------");
        Pengguna pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        idHakmilik = (String) getContext().getRequest().getParameter("idHakmilik");
        idPihak = Long.parseLong(getContext().getRequest().getParameter("idPihak"));

        permohonan = permohonanDAO.findById(idPermohonan);
        permohonanPihak = permohonanSupayaBantahanService.getSenaraiPmohonPihakByIdHakmilikIdPihak(idPermohonan, idHakmilik, idPihak);

        if (permohonanPihak != null) {
            hakmilik = hakmilikDAO.findById(idHakmilik);
            lokasi = hakmilik.getLot().getNama() + " " + hakmilik.getNoLot() + " " + hakmilik.getKodHakmilik().getKod() + " " + hakmilik.getNoHakmilik() + ", "
                    + hakmilik.getBandarPekanMukim().getNama() + ", DAERAH " + hakmilik.getDaerah().getNama() + ", Melaka";

            permohonanMahkamah = permohonanSupayaBantahanService.findPermohonanMahkamahByidMP(permohonanPihak.getIdPermohonanPihak());
            if(permohonanMahkamah !=null){
                samanPemulaBil = permohonanMahkamah.getSamanPemulaBil();
            }
        }
        String heading1 = lokasi;
        heading = heading1.toLowerCase();

        if (idPermohonan != null) {
            if (permohonanPihak != null) {
                permohonanKertas = permohonanSupayaBantahanService.findPermohonanKertasByidMP(permohonanPihak.getIdPermohonanPihak());
                if (permohonanKertas != null) {
                    samanPemulaBilObj = pendudukanSementaraMMKNService.findbil1ByIdKertas(permohonanKertas.getIdKertas(), 2);
                    headingObj = pendudukanSementaraMMKNService.findbil1ByIdKertas(permohonanKertas.getIdKertas(), 3);
                    headingObj2 = pendudukanSementaraMMKNService.findbil1ByIdKertas(permohonanKertas.getIdKertas(), 4);
                    perakuan = pendudukanSementaraMMKNService.findbil1ByIdKertas(permohonanKertas.getIdKertas(), 5);
                    perakuan1 = pendudukanSementaraMMKNService.findbil1ByIdKertas(permohonanKertas.getIdKertas(), 6);
                    tarikhIkrar = pendudukanSementaraMMKNService.findbil1ByIdKertas(permohonanKertas.getIdKertas(), 7);
                    senaraiKertasKandungan1 = pendudukanSementaraMMKNService.findByKertas(permohonanKertas.getIdKertas(), 1);
                }
            }
        }
        hakmilikDetails();
        getContext().getRequest().setAttribute("showDetails", Boolean.TRUE);
        return new JSP("pengambilan/Affidavit_Sek8.jsp").addParameter("tab", "true");
    }

    public Resolution simpan() throws ParseException {
        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        permohonan = permohonanDAO.findById(idPermohonan);
        idPihak = Long.parseLong(getContext().getRequest().getParameter("idPihak"));
        idHakmilik = (String) getContext().getRequest().getParameter("idHakmilik");
        permohonanPihak = permohonanSupayaBantahanService.getSenaraiPmohonPihakByIdHakmilikIdPihak(idPermohonan, idHakmilik, idPihak);


        if (permohonanPihak != null) {
            permohonanKertas = permohonanSupayaBantahanService.findPermohonanKertasByidMP(permohonanPihak.getIdPermohonanPihak());
            if (permohonanKertas == null) {
                permohonanKertas = new PermohonanKertas();
            }
            KodDokumen doc = new KodDokumen();
            doc.setKod("SMK");
            InfoAudit iaPermohonan = new InfoAudit();
            iaPermohonan.setTarikhMasuk(new Date());
            iaPermohonan.setDimasukOleh(peng);
            permohonanKertas.setInfoAudit(iaPermohonan);
            permohonanKertas.setPermohonan(permohonan);
            permohonanKertas.setTajuk("urusan affidavit pengambilan");
            permohonanKertas.setPermohonanPihak(permohonanPihak);
            permohonanKertas.setCawangan(permohonan.getCawangan());
            permohonanKertas.setKodDokumen(doc);
            permohonanKertas = pendudukanSementaraMMKNService.simpanPermohonanKertas(permohonanKertas);

            senaraiKertasKandungan1 = pendudukanSementaraMMKNService.findByKertas(permohonanKertas.getIdKertas(), 1);
            int kira1 = Integer.parseInt(getContext().getRequest().getParameter("rowCount1"));
            for (int i = 1; i <= kira1; i++) {
                if (senaraiKertasKandungan1.size() != 0 && i <= senaraiKertasKandungan1.size()) {
                    Long id = senaraiKertasKandungan1.get(i - 1).getIdKandungan();
                    if (id != null) {
                        permohonanKertasKandungan = pendudukanSementaraMMKNService.findkandunganByIdKandungan(id);
                    }
                } else {
                    permohonanKertasKandungan = new PermohonanKertasKandungan();
                }
                permohonanKertasKandungan.setKertas(permohonanKertas);
                permohonanKertasKandungan.setBil(1);
                kandungan = getContext().getRequest().getParameter("kandungan1" + i);
                if (kandungan == null) {
                    permohonanKertasKandungan.setKandungan("Tiada");
                } else {
                    permohonanKertasKandungan.setKandungan(kandungan);
                }
                permohonanKertasKandungan.setCawangan(permohonan.getCawangan());
                permohonanKertasKandungan.setSubtajuk("1." + i);
                InfoAudit iaP = new InfoAudit();
                iaP.setTarikhMasuk(new Date());
                iaP.setDimasukOleh(peng);
                permohonanKertasKandungan.setInfoAudit(iaP);
                pendudukanSementaraMMKNService.simpanPermohonanKertasKandungan(permohonanKertasKandungan);
            }

//            if (getContext().getRequest().getParameter("headingObj2.kandungan") != null) {
                headingObj2 = pendudukanSementaraMMKNService.findbil1ByIdKertas(permohonanKertas.getIdKertas(), 4);
                iaPermohonan = new InfoAudit();
                if (headingObj2 == null) {
                    headingObj2 = new PermohonanKertasKandungan();
                    iaPermohonan.setDimasukOleh(peng);
                    iaPermohonan.setTarikhMasuk(new java.util.Date());
                } else {
                    iaPermohonan = headingObj2.getInfoAudit();
                    iaPermohonan.setDiKemaskiniOleh(peng);
                    iaPermohonan.setTarikhKemaskini(new java.util.Date());
                }
                headingObj2.setKertas(permohonanKertas);
                headingObj2.setBil(4);
                kandungan = getContext().getRequest().getParameter("headingObj2.kandungan");
                if (kandungan == null) {
                    headingObj2.setKandungan("Tiada");
                } else {
                    headingObj2.setKandungan(kandungan);
                }
                //headingObj.setKandungan(kandungan);
                headingObj2.setCawangan(permohonan.getCawangan());
                headingObj2.setInfoAudit(iaPermohonan);
                permohonanKertasKandunganDAO.saveOrUpdate(headingObj2);
//            }

//            if (getContext().getRequest().getParameter("heading") != null) {
                headingObj = pendudukanSementaraMMKNService.findbil1ByIdKertas(permohonanKertas.getIdKertas(), 3);
                iaPermohonan = new InfoAudit();
                if (headingObj == null) {
                    headingObj = new PermohonanKertasKandungan();
                    iaPermohonan.setDimasukOleh(peng);
                    iaPermohonan.setTarikhMasuk(new java.util.Date());
                } else {
                    iaPermohonan = headingObj.getInfoAudit();
                    iaPermohonan.setDiKemaskiniOleh(peng);
                    iaPermohonan.setTarikhKemaskini(new java.util.Date());
                }
                headingObj.setKertas(permohonanKertas);
                headingObj.setBil(3);
                kandungan = getContext().getRequest().getParameter("heading");
                if (kandungan == null) {
                    headingObj.setKandungan("Tiada");
                } else {
                    headingObj.setKandungan(kandungan);
                }
                //headingObj.setKandungan(kandungan);
                headingObj.setCawangan(permohonan.getCawangan());
                headingObj.setInfoAudit(iaPermohonan);
                permohonanKertasKandunganDAO.saveOrUpdate(headingObj);
//            }

//            if (getContext().getRequest().getParameter("samanPemulaBil") != null) {
                samanPemulaBilObj = pendudukanSementaraMMKNService.findbil1ByIdKertas(permohonanKertas.getIdKertas(), 2);
                iaPermohonan = new InfoAudit();
                if (samanPemulaBilObj == null) {
                    samanPemulaBilObj = new PermohonanKertasKandungan();
                    iaPermohonan.setDimasukOleh(peng);
                    iaPermohonan.setTarikhMasuk(new java.util.Date());
                } else {
                    iaPermohonan = samanPemulaBilObj.getInfoAudit();
                    iaPermohonan.setDiKemaskiniOleh(peng);
                    iaPermohonan.setTarikhKemaskini(new java.util.Date());
                }
                samanPemulaBilObj.setKertas(permohonanKertas);
                samanPemulaBilObj.setBil(2);
                kandungan = getContext().getRequest().getParameter("samanPemulaBil");
                if (kandungan == null) {
                    samanPemulaBilObj.setKandungan("Tiada");
                } else {
                    samanPemulaBilObj.setKandungan(kandungan);
                }
                //headingObj.setKandungan(kandungan);
                samanPemulaBilObj.setCawangan(permohonan.getCawangan());
                samanPemulaBilObj.setInfoAudit(iaPermohonan);
                permohonanKertasKandunganDAO.saveOrUpdate(samanPemulaBilObj);
//            }

//            if (getContext().getRequest().getParameter("perakuan.kandungan") != null) {
                perakuan = pendudukanSementaraMMKNService.findbil1ByIdKertas(permohonanKertas.getIdKertas(), 5);
                if (perakuan == null) {
                    perakuan = new PermohonanKertasKandungan();
                }
                perakuan.setKertas(permohonanKertas);
                perakuan.setBil(5);
                kandungan = getContext().getRequest().getParameter("perakuan.kandungan");
                if (kandungan == null) {
                    perakuan.setKandungan("Tiada");
                } else {
                    perakuan.setKandungan(kandungan);
                }
                perakuan.setCawangan(permohonan.getCawangan());
                InfoAudit iaP = new InfoAudit();
                iaP.setTarikhMasuk(new Date());
                iaP.setDimasukOleh(peng);
                perakuan.setInfoAudit(iaP);
                pendudukanSementaraMMKNService.simpanPermohonanKertasKandungan(perakuan);
//            }

//            if (getContext().getRequest().getParameter("perakuan1.kandungan") != null) {
                perakuan1 = pendudukanSementaraMMKNService.findbil1ByIdKertas(permohonanKertas.getIdKertas(), 6);
                if (perakuan1 == null) {
                    perakuan1 = new PermohonanKertasKandungan();
                }
                perakuan1.setKertas(permohonanKertas);
                perakuan1.setBil(6);
                kandungan = getContext().getRequest().getParameter("perakuan1.kandungan");
                if (kandungan == null) {
                    perakuan1.setKandungan("Tiada");
                } else {
                    perakuan1.setKandungan(kandungan);
                }
                perakuan1.setCawangan(permohonan.getCawangan());
                iaP = new InfoAudit();
                iaP.setTarikhMasuk(new Date());
                iaP.setDimasukOleh(peng);
                perakuan1.setInfoAudit(iaP);
                pendudukanSementaraMMKNService.simpanPermohonanKertasKandungan(perakuan1);
//            }

//            if (getContext().getRequest().getParameter("tarikhIkrar.kandungan") != null) {
                tarikhIkrar = pendudukanSementaraMMKNService.findbil1ByIdKertas(permohonanKertas.getIdKertas(), 7);
                if (tarikhIkrar == null) {
                    tarikhIkrar = new PermohonanKertasKandungan();
                }
                logger.debug(permohonanKertas.getIdKertas() + "ID5");
                tarikhIkrar.setKertas(permohonanKertas);
                tarikhIkrar.setBil(7);
                kandungan = getContext().getRequest().getParameter("tarikhIkrar.kandungan");
                if (kandungan == null) {
                    tarikhIkrar.setKandungan("Tiada");
                } else {
                    tarikhIkrar.setKandungan(kandungan);
                }
                tarikhIkrar.setCawangan(permohonan.getCawangan());
                iaP = new InfoAudit();
                iaP.setTarikhMasuk(new Date());
                iaP.setDimasukOleh(peng);
                tarikhIkrar.setInfoAudit(iaP);
//                permohonanKertasKandunganDAO.saveOrUpdate(tarikhIkrar);
            pendudukanSementaraMMKNService.simpanPermohonanKertasKandungan(tarikhIkrar);
//            }
            rehydrate();
            pihakDetails();

        }
        getContext().getRequest().setAttribute("showDetails", Boolean.TRUE);
        addSimpleMessage("Maklumat telah berjaya disimpan");
        return new JSP("pengambilan/Affidavit_Sek8.jsp").addParameter("tab", "true");
//        return new RedirectResolution(AffidavitSek8ActionBean.class, "locate");
    }

    public Resolution deleteSingle() {
        InfoAudit ia = new InfoAudit();
        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);

        idKandungan = getContext().getRequest().getParameter("idKandungan");
        try {
            permohonanKertasKandungan = pendudukanSementaraMMKNService.findkandunganByIdKandungan(Long.parseLong(idKandungan));
        } catch (Exception e) {
            logger.debug("Hapus empty record");

        }
        if (permohonanKertasKandungan != null) {

            ia = peng.getInfoAudit();
            ia.setDiKemaskiniOleh(peng);
            ia.setTarikhKemaskini(new java.util.Date());
            permohonanKertasKandungan.setInfoAudit(ia);
            permohonanKertasKandungan.setCawangan(permohonan.getCawangan());
            pendudukanSementaraMMKNService.deleteKertasKandungan(permohonanKertasKandungan);
        }

        rehydrate();
        addSimpleMessage("Maklumat telah berjaya dihapus.");
//        String form1 = getContext().getRequest().getParameter("form1");
//        if(form1.equals("true"))
//        getContext().getRequest().setAttribute("form1", Boolean.TRUE);
        getContext().getRequest().setAttribute("showDetails", Boolean.TRUE);
        return new JSP("pengambilan/Affidavit_Sek8.jsp").addParameter("tab", "true");
    }

    public Resolution deleteTable() {

        int bil = Integer.parseInt(getContext().getRequest().getParameter("bil"));

        List<PermohonanKertasKandungan> kandList = pendudukanSementaraMMKNService.findByKertasDesc(permohonanKertas.getIdKertas(), bil);

        if (kandList != null && !kandList.isEmpty()) {
            PermohonanKertasKandungan maxKertasKand = kandList.get(0);
            if (maxKertasKand != null) {
                String subtajuk = maxKertasKand.getSubtajuk();
                String str = subtajuk.substring(2, 3);
                String subtajuk1 = bil + "." + str;
                List<PermohonanKertasKandungan> kandList1 = pendudukanSementaraMMKNService.findByKertasSubTajuk(permohonanKertas.getIdKertas(), bil, subtajuk1);
                for (PermohonanKertasKandungan kand : kandList1) {
                    pendudukanSementaraMMKNService.deleteKertasKandungan(kand);
                }
            }
        }

        rehydrate();
        addSimpleMessage("Maklumat telah berjaya dihapus.");
        String form1 = getContext().getRequest().getParameter("form1");
        if (form1.equals("true")) {
            getContext().getRequest().setAttribute("form1", Boolean.TRUE);
        }
        return new JSP("pengambilan/Affidavit_Sek8.jsp").addParameter("tab", "true");

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

    public List<HakmilikPermohonan> getHakmilikPermohonanList() {
        return hakmilikPermohonanList;
    }

    public void setHakmilikPermohonanList(List<HakmilikPermohonan> hakmilikPermohonanList) {
        this.hakmilikPermohonanList = hakmilikPermohonanList;
    }

    public PermohonanKertas getPermohonanKertas() {
        return permohonanKertas;
    }

    public void setPermohonanKertas(PermohonanKertas permohonanKertas) {
        this.permohonanKertas = permohonanKertas;
    }

    public PermohonanKertasKandungan getPermohonanKertasKandungan() {
        return permohonanKertasKandungan;
    }

    public void setPermohonanKertasKandungan(PermohonanKertasKandungan permohonanKertasKandungan) {
        this.permohonanKertasKandungan = permohonanKertasKandungan;
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

    public String getKandungan() {
        return kandungan;

    }

    public void setKandungan(String kandungan) {
        this.kandungan = kandungan;
    }

    public String getIdKandungan() {
        return idKandungan;
    }

    public void setIdKandungan(String idKandungan) {
        this.idKandungan = idKandungan;
    }

    public Hakmilik getHakmilik() {
        return hakmilik;
    }

    public void setHakmilik(Hakmilik hakmilik) {
        this.hakmilik = hakmilik;
    }

    public PermohonanKertasKandungan getMasaObj() {
        return masaObj;
    }

    public void setMasaObj(PermohonanKertasKandungan masaObj) {
        this.masaObj = masaObj;
    }

    public String getKandungan1() {
        return kandungan1;
    }

    public void setKandungan1(String kandungan1) {
        this.kandungan1 = kandungan1;
    }

    public PermohonanKertasKandungan getPerakuan() {
        return perakuan;
    }

    public void setPerakuan(PermohonanKertasKandungan perakuan) {
        this.perakuan = perakuan;
    }

    public List<PermohonanKertasKandungan> getSenaraiKertasKandungan1() {
        return senaraiKertasKandungan1;
    }

    public void setSenaraiKertasKandungan1(List<PermohonanKertasKandungan> senaraiKertasKandungan1) {
        this.senaraiKertasKandungan1 = senaraiKertasKandungan1;
    }

    public PermohonanKertasKandungan getDiIkrarOleh() {
        return diIkrarOleh;
    }

    public void setDiIkrarOleh(PermohonanKertasKandungan diIkrarOleh) {
        this.diIkrarOleh = diIkrarOleh;
    }

    public PermohonanKertasKandungan getPesuruhJayaSumpah() {
        return pesuruhJayaSumpah;
    }

    public void setPesuruhJayaSumpah(PermohonanKertasKandungan pesuruhJayaSumpah) {
        this.pesuruhJayaSumpah = pesuruhJayaSumpah;
    }

    public PermohonanKertasKandungan getTarikhIkrar() {
        return tarikhIkrar;
    }

    public void setTarikhIkrar(PermohonanKertasKandungan tarikhIkrar) {
        this.tarikhIkrar = tarikhIkrar;
    }

    public String getMasa() {
        return masa;
    }

    public void setMasa(String masa) {
        this.masa = masa;
    }

    public String getHeading() {
        return heading;
    }

    public void setHeading(String heading) {
        this.heading = heading;
    }

    public PermohonanKertasKandungan getHeadingObj() {
        return headingObj;
    }

    public void setHeadingObj(PermohonanKertasKandungan headingObj) {
        this.headingObj = headingObj;
    }

    public PermohonanKertasKandungan getTujuanObj() {
        return tujuanObj;
    }

    public void setTujuanObj(PermohonanKertasKandungan tujuanObj) {
        this.tujuanObj = tujuanObj;
    }

    public String getJam() {
        return jam;
    }

    public void setJam(String jam) {
        this.jam = jam;
    }

    public String getMinit() {
        return minit;
    }

    public void setMinit(String minit) {
        this.minit = minit;
    }

    public String getPagiPetang() {
        return pagiPetang;
    }

    public void setPagiPetang(String pagiPetang) {
        this.pagiPetang = pagiPetang;
    }

    public String getTarikhmesyuarat() {
        return tarikhmesyuarat;
    }

    public void setTarikhmesyuarat(String tarikhmesyuarat) {
        this.tarikhmesyuarat = tarikhmesyuarat;
    }

    public String getTempat() {
        return tempat;
    }

    public void setTempat(String tempat) {
        this.tempat = tempat;
    }

    public PermohonanMahkamah getPermohonanMahkamah() {
        return permohonanMahkamah;
    }

    public void setPermohonanMahkamah(PermohonanMahkamah permohonanMahkamah) {
        this.permohonanMahkamah = permohonanMahkamah;
    }

    public String getSamanPemulaBil() {
        return samanPemulaBil;
    }

    public void setSamanPemulaBil(String samanPemulaBil) {
        this.samanPemulaBil = samanPemulaBil;
    }

    public PendudukanSementaraMMKNService getPendudukanSementaraMMKNService() {
        return pendudukanSementaraMMKNService;
    }

    public void setPendudukanSementaraMMKNService(PendudukanSementaraMMKNService pendudukanSementaraMMKNService) {
        this.pendudukanSementaraMMKNService = pendudukanSementaraMMKNService;
    }

    public PermohonanPihak getPermohonanPihak() {
        return permohonanPihak;
    }

    public void setPermohonanPihak(PermohonanPihak permohonanPihak) {
        this.permohonanPihak = permohonanPihak;
    }

    public PermohonanSupayaBantahanService getPermohonanSupayaBantahanService() {
        return permohonanSupayaBantahanService;
    }

    public void setPermohonanSupayaBantahanService(PermohonanSupayaBantahanService permohonanSupayaBantahanService) {
        this.permohonanSupayaBantahanService = permohonanSupayaBantahanService;
    }

    public String getIdHakmilik() {
        return idHakmilik;
    }

    public void setIdHakmilik(String idHakmilik) {
        this.idHakmilik = idHakmilik;
    }

    public long getIdPihak() {
        return idPihak;
    }

    public void setIdPihak(long idPihak) {
        this.idPihak = idPihak;
    }

    public String getShow() {
        return show;
    }

    public void setShow(String show) {
        this.show = show;
    }

    public PermohonanKertasKandungan getHeadingObj2() {
        return headingObj2;
    }

    public void setHeadingObj2(PermohonanKertasKandungan headingObj2) {
        this.headingObj2 = headingObj2;
    }

    public PermohonanKertasKandungan getPerakuan1() {
        return perakuan1;
    }

    public void setPerakuan1(PermohonanKertasKandungan perakuan1) {
        this.perakuan1 = perakuan1;
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

    public List<Pemohon> getListPemohon() {
        return listPemohon;
    }

    public void setListPemohon(List<Pemohon> listPemohon) {
        this.listPemohon = listPemohon;
    }

    public String getLokasi() {
        return lokasi;
    }

    public void setLokasi(String lokasi) {
        this.lokasi = lokasi;
    }

    public String getNamaProjek() {
        return namaProjek;
    }

    public void setNamaProjek(String namaProjek) {
        this.namaProjek = namaProjek;
    }

    public PermohonanKertasKandungan getSamanPemulaBilObj() {
        return samanPemulaBilObj;
    }

    public void setSamanPemulaBilObj(PermohonanKertasKandungan samanPemulaBilObj) {
        this.samanPemulaBilObj = samanPemulaBilObj;
    }

    public List<PerbicaraanKehadiran> getSenaraiPerbicaraanKehadiran() {
        return senaraiPerbicaraanKehadiran;
    }

    public void setSenaraiPerbicaraanKehadiran(List<PerbicaraanKehadiran> senaraiPerbicaraanKehadiran) {
        this.senaraiPerbicaraanKehadiran = senaraiPerbicaraanKehadiran;
    }

    public String getSubtajuk() {
        return subtajuk;
    }

    public void setSubtajuk(String subtajuk) {
        this.subtajuk = subtajuk;
    }

    
}
