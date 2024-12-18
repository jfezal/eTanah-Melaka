/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package etanah.view.stripes.pelupusan;

import java.text.ParseException;
import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.dao.KodHakmilikDAO;
import etanah.dao.KodKegunaanTanahDAO;
import etanah.dao.KodSekatanKepentinganDAO;
import etanah.dao.KodSyaratNyataDAO;
import etanah.dao.PermohonanDAO;
import etanah.model.HakmilikPermohonan;
import etanah.model.InfoAudit;
import etanah.model.KodCawangan;
import etanah.model.KodHakmilik;
import etanah.model.KodKategoriTanah;
import etanah.model.KodKegunaanTanah;
import etanah.model.KodSekatanKepentingan;
import etanah.model.KodSyaratNyata;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.service.PelupusanService;
import etanah.view.etanahActionBeanContext;
import java.util.Date;
import java.util.List;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;
import org.apache.log4j.Logger;

/**
 *
 * @author sitifariza.hanim
 */
@UrlBinding("/pelupusan/surat_kelulusan_RAYT")
public class SuratKelulusanRAYTActionBean extends AbleActionBean {

private static Logger logger = Logger.getLogger(SuratKelulusanRAYTActionBean.class);
@Inject
PelupusanService pelupusanService;
@Inject
PermohonanDAO permohonanDAO;
@Inject
KodHakmilikDAO kodHakmilikDAO;
@Inject
KodKegunaanTanahDAO kodKegunaanTanahDAO;
@Inject
KodSyaratNyataDAO kodSyaratNyataDAO;
@Inject
KodSekatanKepentinganDAO kodSekatanKepentinganDAO;
private Pengguna pengguna;
private Permohonan permohonan;
private String idPermohonan;
private HakmilikPermohonan hakmilikPermohonan;
private KodKategoriTanah kategoriTanah;
private KodKegunaanTanah kegunaanTanah;
private String jenisHakmilik;
private KodHakmilik kodHM;
private KodKategoriTanah kodKT;
private KodKegunaanTanah kodKGT;
private KodSyaratNyata kodSN;
private KodSekatanKepentingan kodSK;
private List<KodHakmilik> senaraiKodHakmilik;
private List<KodKegunaanTanah> senaraiKegunaanTanah;
private List<KodSyaratNyata> senaraiKodSyaratNyata;
private List<KodSekatanKepentingan> senaraiKodSekatan;

@DefaultHandler
    public Resolution showForm() {
        return new JSP("pelupusan/surat_kelulusan_RAYT.jsp").addParameter("tab", "true");
    }

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {

        pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");

        //one idPermohonan to one idHakmilik
        if (idPermohonan != null) {
            permohonan = permohonanDAO.findById(idPermohonan);
            hakmilikPermohonan = pelupusanService.findByIdPermohonan(idPermohonan);
            senaraiKodHakmilik = kodHakmilikDAO.findAll();
            senaraiKegunaanTanah = kodKegunaanTanahDAO.findAll();
            senaraiKodSyaratNyata = kodSyaratNyataDAO.findAll();
            senaraiKodSekatan = kodSekatanKepentinganDAO.findAll();
        }
    }

    public Resolution simpan() throws ParseException {
        logger.debug("start simpan");
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = permohonanDAO.findById(idPermohonan);
        
        //to set idpermohonan
        hakmilikPermohonan.setPermohonan(permohonan);

        kodHM = new KodHakmilik();
        String kod = getContext().getRequest().getParameter("kodHakmilik.kod");

        kodHM.setKod(kod);
        hakmilikPermohonan.setKodHakmilik(kodHM);

        kodKGT = new KodKegunaanTanah();
        String kod2 = getContext().getRequest().getParameter("kegunaanTanah.kod");

        kodKT.setKod(kod2);
        hakmilikPermohonan.setJenisPenggunaanTanah(kodKT);

        kodSN = new KodSyaratNyata();
        String kod3 = getContext().getRequest().getParameter("syaratNyata.kod");

        kodSN.setKod(kod3);
        hakmilikPermohonan.setSyaratNyata(kodSN);

        kodSK = new KodSekatanKepentingan();
        String kod4 = getContext().getRequest().getParameter("sekatanKepentingan.kod");

        kodSK.setKod(kod4);
        hakmilikPermohonan.setSekatanKepentingan(kodSK);

        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        InfoAudit ia = hakmilikPermohonan.getInfoAudit();
        if (ia == null) {
            ia = new InfoAudit();
            ia.setDimasukOleh(peng);
            ia.setTarikhMasuk(new Date());
            hakmilikPermohonan.setInfoAudit(ia);
        } else {
            ia.setDiKemaskiniOleh(peng);
            ia.setTarikhKemaskini(new java.util.Date());
            hakmilikPermohonan.setInfoAudit(ia);
        }
        pelupusanService.simpanHakmilikPermohonan(hakmilikPermohonan);
        logger.debug("tess1 :" + hakmilikPermohonan.getId());

        addSimpleMessage("Maklumat telah berjaya disimpan.");
        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        return new JSP("pelupusan/surat_kelulusan_RAYT.jsp").addParameter("tab", "true");

    }
// @Before(stages = {LifecycleStage.BindingAndValidation})
//
//    public void rehydrate() {
//        getContext().getRequest().setAttribute("edit", Boolean.TRUE);     //attribute
//        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");     //id_permohonan
//        permohonan = permohonanDAO.findById(idPermohonan);
//        System.out.println("idPermohonan : " + idPermohonan);
//
//        if (idPermohonan != null) {
//
//                permohonan = permohonanDAO.findById(idPermohonan);
//                hakmilikPermohonan = pelupusanService.findByIdPermohonan(idPermohonan);
//                senaraiKodHakmilik = kodHakmilikDAO.findAll();
//                senaraiKegunaanTanah = kodKegunaanTanahDAO.findAll();
//                senaraiKodSyaratNyata = kodSyaratNyataDAO.findAll();
//                senaraiKodSekatan = kodSekatanKepentinganDAO.findAll();
//            }
//    }

//     public Resolution simpan2() {
//
//          etanahActionBeanContext ctx = new etanahActionBeanContext();
//                ctx = (etanahActionBeanContext) getContext();
//
//                Pengguna pengguna = ctx.getUser();
//                KodCawangan caw = pengguna.getKodCawangan();
//                InfoAudit ia = new InfoAudit();
//                ia.setDimasukOleh(pengguna);
//                ia.setTarikhMasuk(new java.util.Date());
//                hakmilikPermohonan = new HakmilikPermohonan();   //for each new record new object
//                hakmilikPermohonan.setInfoAudit(ia);
//                hakmilikPermohonan.setPermohonan(permohonan);
//
//                pelupusanService.simpanHakmilikPermohonan(hakmilikPermohonan);
//
//        addSimpleMessage("Maklumat telah berjaya disimpan.");
//        return new JSP("pelupusan/surat_kelulusan_RAYT.jsp").addParameter("tab", "true");
//     }

//    public Resolution simpan3() throws ParseException {
//
//        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
//        permohonan = permohonanDAO.findById(idPermohonan);
//
//        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
//        KodCawangan caw = peng.getKodCawangan();
//        InfoAudit ia = new InfoAudit();
//
//        if (hakmilikPermohonan == null) {
//            hakmilikPermohonan = new HakmilikPermohonan();
//        }
//        hakmilikPermohonan.setPermohonan(permohonan);
//
////        InfoAudit ia = hakmilikPermohonan.getInfoAudit();
//
//        List<HakmilikPermohonan> listHakmilikPermohonan = pelupusanService.getHakmilikPermohonan(idPermohonan);
//        ia = hakmilikPermohonan.getInfoAudit();
//
//        if (listHakmilikPermohonan.size() == 0) {
//            System.out.println("baru...");
//            ia = new InfoAudit();
//            ia.setDimasukOleh(peng);
//            ia.setTarikhMasuk(new Date());
//            hakmilikPermohonan.setInfoAudit(ia);
//
//        } else {
//            System.out.println("lame...");
//            ia.setDiKemaskiniOleh(peng);
//            ia.setTarikhKemaskini(new java.util.Date());
//            hakmilikPermohonan.setInfoAudit(ia);
//        }
//
////        hakmilikPermohonan.setCawangan(caw);
//         pelupusanService.simpanHakmilikPermohonan(hakmilikPermohonan);
//
//        addSimpleMessage("Maklumat telah berjaya disimpan.");
//        return new JSP("pelupusan/laporanNilaian.jsp").addParameter("tab", "true");
//
//    }

    public HakmilikPermohonan getHakmilikPermohonan() {
        return hakmilikPermohonan;
    }

    public void setHakmilikPermohonan(HakmilikPermohonan hakmilikPermohonan) {
        this.hakmilikPermohonan = hakmilikPermohonan;
    }

    public String getIdPermohonan() {
        return idPermohonan;
    }

    public void setIdPermohonan(String idPermohonan) {
        this.idPermohonan = idPermohonan;
    }

    public String getJenisHakmilik() {
        return jenisHakmilik;
    }

    public void setJenisHakmilik(String jenisHakmilik) {
        this.jenisHakmilik = jenisHakmilik;
    }

    public KodKategoriTanah getKategoriTanah() {
        return kategoriTanah;
    }

    public void setKategoriTanah(KodKategoriTanah kategoriTanah) {
        this.kategoriTanah = kategoriTanah;
    }

    public KodKegunaanTanah getKegunaanTanah() {
        return kegunaanTanah;
    }

    public void setKegunaanTanah(KodKegunaanTanah kegunaanTanah) {
        this.kegunaanTanah = kegunaanTanah;
    }

    public Permohonan getPermohonan() {
        return permohonan;
    }

    public void setPermohonan(Permohonan permohonan) {
        this.permohonan = permohonan;
    }

    public List<KodHakmilik> getSenaraiKodHakmilik() {
        return senaraiKodHakmilik;
    }

    public void setSenaraiKodHakmilik(List<KodHakmilik> senaraiKodHakmilik) {
        this.senaraiKodHakmilik = senaraiKodHakmilik;
    }

    public Pengguna getPengguna() {
        return pengguna;
    }

    public void setPengguna(Pengguna pengguna) {
        this.pengguna = pengguna;
    }

    public List<KodKegunaanTanah> getSenaraiKegunaanTanah() {
        return senaraiKegunaanTanah;
    }

    public void setSenaraiKegunaanTanah(List<KodKegunaanTanah> senaraiKegunaanTanah) {
        this.senaraiKegunaanTanah = senaraiKegunaanTanah;
    }

    public List<KodSyaratNyata> getSenaraiKodSyaratNyata() {
        return senaraiKodSyaratNyata;
    }

    public void setSenaraiKodSyaratNyata(List<KodSyaratNyata> senaraiKodSyaratNyata) {
        this.senaraiKodSyaratNyata = senaraiKodSyaratNyata;
    }

    public List<KodSekatanKepentingan> getSenaraiKodSekatan() {
        return senaraiKodSekatan;
    }

    public void setSenaraiKodSekatan(List<KodSekatanKepentingan> senaraiKodSekatan) {
        this.senaraiKodSekatan = senaraiKodSekatan;
    }


}
