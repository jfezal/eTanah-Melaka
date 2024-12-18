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
import etanah.model.Pemohon;
import etanah.model.PermohonanKertas;
import etanah.model.PermohonanKertasKandungan;
import etanah.model.PermohonanMahkamah;
import etanah.model.PermohonanPihak;

import etanah.view.etanahActionBeanContext;
import java.util.List;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;
import org.apache.log4j.Logger;
import java.text.SimpleDateFormat;
import etanah.service.PendudukanSementaraMMKNService;
import etanah.service.PermohonanSupayaBantahanService;
import java.util.ArrayList;
import org.hibernate.Session;

/**
 *
 * @author Rajesh
 */
@UrlBinding("/pengambilan/samanPemula")
public class SamanPemulaActionBean extends AbleActionBean {

    private static Logger logger = Logger.getLogger(SamanPemulaActionBean.class);
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
    private Permohonan permohonanSebelum;
    private Permohonan permohonan;
    private String idPermohonan;
    private KodCawangan cawangan;
    private PermohonanKertas permohonanKertas;
    private String kertasBil;
    private String kertasTahun;
    private PermohonanKertasKandungan masaObj;
    private String tempat;
    private String tarikhmesyuarat;
    private String samanPemulaBil;
    private String tarikhSaman;
    private String jam;
    private String minit;
    private String pagiPetang;
    private Date tarikhTerimaPerintah;
    private Date tarikhIkrar;
    private SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss a");
    private List<HakmilikPermohonan> hakmilikPermohonanList;
    private PermohonanMahkamah permohonanMahkamah;
    private PermohonanPihak permohonanPihak;
//    private int bil = 0;
    private String kandungan;
    private String kandungan1;
    private String kandungan2;
    private String kandungan3;
    private String idKandungan;
    private String tajuk;
    private Hakmilik hakmilik;
    private String tujuan;
    private String keputusan;
    private int count = 0;
    private int count5 = 0;
    String str[] = {"", "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z"};
    private List senaraiSyor[] = new ArrayList[5];
    private List senaraiSyorPentadbir[] = new ArrayList[5];
    private List<Pemohon> listPemohon;
    private String masa;
    private String subtajuk;
    private String heading;
    private String lokasi;
    private String idHakmilik;
    private long idPihak;
    private String show;
    String namaProjek;
    private String disable = "FALSE";

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

        return new JSP("pengambilan/Saman_Pemula.jsp").addParameter("tab", "true");
    }

    //For just display
    public Resolution showForm1() {
        Pengguna pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        if (idPermohonan != null) {
            permohonan = permohonanDAO.findById(idPermohonan);
            if (permohonan != null) {
                permohonanSupayaBantahanService.simpanPermohonanPihak(permohonan, pengguna);
            }
        }

        disable = "TRUE";

        return new JSP("pengambilan/Saman_Pemula.jsp").addParameter("tab", "true");
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
                if(permohonanMahkamah.getTarikhSaman() != null) {
                    String tarikh = dateFormat.format(permohonanMahkamah.getTarikhSaman());
                    tarikhSaman = tarikh.substring(0, 10);
                    jam = tarikh.substring(11, 13);
                    minit = tarikh.substring(14, 16);
                    String ampm = tarikh.substring(20, 22);
                    if(ampm.equalsIgnoreCase("AM")){
                        pagiPetang = "PAGI";
                    }else if(ampm.equalsIgnoreCase("PM")){
                        pagiPetang = "PETANG";
                    }
                }
                tarikhTerimaPerintah = permohonanMahkamah.getTarikhTerimaPerintah();
                tarikhIkrar = permohonanMahkamah.getTarikhIkrar();
            }
        }
        String heading1 = lokasi;
        heading = heading1.toLowerCase();

        if(disable.equals("TRUE")){
            disable = "TRUE";
        }

        getContext().getRequest().setAttribute("showDetails", Boolean.TRUE);
        return new JSP("pengambilan/Saman_Pemula.jsp").addParameter("tab", "true");
    }

    public Resolution simpan() throws ParseException {
        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        permohonan = permohonanDAO.findById(idPermohonan);
        idPihak = Long.parseLong(getContext().getRequest().getParameter("idPihak"));
        idHakmilik = (String) getContext().getRequest().getParameter("idHakmilik");
        permohonanPihak = permohonanSupayaBantahanService.getSenaraiPmohonPihakByIdHakmilikIdPihak(idPermohonan, idHakmilik, idPihak);
        InfoAudit infoAudit;

        if (permohonanPihak != null) {
            permohonanMahkamah = permohonanSupayaBantahanService.findPermohonanMahkamahByidMP(permohonanPihak.getIdPermohonanPihak());
            if(permohonanMahkamah == null) {
                permohonanMahkamah = new PermohonanMahkamah();
                infoAudit = new InfoAudit();
                infoAudit.setDimasukOleh(peng);
                infoAudit.setTarikhMasuk(new java.util.Date());
            }else {
                infoAudit = permohonanMahkamah.getInfoAudit();
                infoAudit.setDiKemaskiniOleh(peng);
                infoAudit.setTarikhKemaskini(new java.util.Date());
            }
            permohonanMahkamah.setInfoAudit(infoAudit);
            permohonanMahkamah.setPermohonanPihak(permohonanPihak);
            if(samanPemulaBil == null)
                samanPemulaBil = "Tiada Data";
            permohonanMahkamah.setSamanPemulaBil(samanPemulaBil);
            if(tarikhSaman != null && tarikhSaman.length() > 0 && jam.length() > 0 && minit.length() > 0 && pagiPetang.length()>0){
                String ampm = "";
                if(pagiPetang.equalsIgnoreCase("PAGI")){
                    ampm = "AM";
                }else if(pagiPetang.equalsIgnoreCase("PETANG")){
                    ampm = "PM";
                }
                String strMasa = tarikhSaman + " " + jam + ":" + minit + ":00 " + ampm;
                Date tarikh = dateFormat.parse(strMasa);
                permohonanMahkamah.setTarikhSaman(tarikh);
            }
            permohonanMahkamah.setTarikhTerimaPerintah(tarikhTerimaPerintah);
            permohonanMahkamah.setTarikhIkrar(tarikhIkrar);
            permohonanSupayaBantahanService.saveOrupdatePermohonanMahkamah(permohonanMahkamah);

        }
        getContext().getRequest().setAttribute("showDetails", Boolean.TRUE);
        addSimpleMessage("Maklumat telah berjaya disimpan");
        return new JSP("pengambilan/Saman_Pemula.jsp").addParameter("tab", "true");
//        return new RedirectResolution(SamanPemulaActionBean.class, "locate");
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

    public PermohonanKertas getPermohonanKertas() {
        return permohonanKertas;
    }

    public void setPermohonanKertas(PermohonanKertas permohonanKertas) {
        this.permohonanKertas = permohonanKertas;
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

    public String getTujuan() {
        return tujuan;
    }

    public void setTujuan(String tujuan) {
        this.tujuan = tujuan;
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

    public String getKandungan2() {
        return kandungan2;
    }

    public void setKandungan2(String kandungan2) {
        this.kandungan2 = kandungan2;
    }

    public String getKandungan3() {
        return kandungan3;
    }

    public void setKandungan3(String kandungan3) {
        this.kandungan3 = kandungan3;
    }

    public String getKeputusan() {
        return keputusan;
    }

    public void setKeputusan(String keputusan) {
        this.keputusan = keputusan;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getCount5() {
        return count5;
    }

    public void setCount5(int count5) {
        this.count5 = count5;
    }

    public String[] getStr() {
        return str;
    }

    public void setStr(String[] str) {
        this.str = str;
    }

    public List[] getSenaraiSyor() {
        return senaraiSyor;
    }

    public void setSenaraiSyor(List[] senaraiSyor) {
        this.senaraiSyor = senaraiSyor;
    }

    public List[] getSenaraiSyorPentadbir() {
        return senaraiSyorPentadbir;
    }

    public void setSenaraiSyorPentadbir(List[] senaraiSyorPentadbir) {
        this.senaraiSyorPentadbir = senaraiSyorPentadbir;
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

    public String getKertasBil() {
        return kertasBil;
    }

    public void setKertasBil(String kertasBil) {
        this.kertasBil = kertasBil;
    }

    public String getKertasTahun() {
        return kertasTahun;
    }

    public void setKertasTahun(String kertasTahun) {
        this.kertasTahun = kertasTahun;
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

    public String getTarikhSaman() {
        return tarikhSaman;
    }

    public void setTarikhSaman(String tarikhSaman) {
        this.tarikhSaman = tarikhSaman;
    }

    public Date getTarikhTerimaPerintah() {
        return tarikhTerimaPerintah;
    }

    public void setTarikhTerimaPerintah(Date tarikhTerimaPerintah) {
        this.tarikhTerimaPerintah = tarikhTerimaPerintah;
    }

    public String getDisable() {
        return disable;
    }

    public void setDisable(String disable) {
        this.disable = disable;
    }

    public Date getTarikhIkrar() {
        return tarikhIkrar;
    }

    public void setTarikhIkrar(Date tarikhIkrar) {
        this.tarikhIkrar = tarikhIkrar;
    }


    

}
