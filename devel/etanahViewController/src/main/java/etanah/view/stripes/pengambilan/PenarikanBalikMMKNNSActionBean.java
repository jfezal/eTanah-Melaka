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
import etanah.service.DrafKertasMMKNService;
import etanah.service.PendudukanSementaraMMKNService;
import java.util.ArrayList;
import net.sourceforge.stripes.action.RedirectResolution;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author Rajesh
 */
@UrlBinding("/pengambilan/penarikan_balik_mmkn_ns")
public class PenarikanBalikMMKNNSActionBean extends AbleActionBean {

    private static Logger logger = Logger.getLogger(PenarikanBalikMMKNNSActionBean.class);
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
    private SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss a");
    private PermohonanKertasKandungan peruntukanKewangan;
    private PermohonanKertasKandungan ulasanPentadbir;
    private PermohonanKertasKandungan ulasanPengarah;
    private PermohonanKertasKandungan permohonanKertasKandungan;
    private PermohonanKertasKandungan permohonanKertasKandungan2;
    private PermohonanKertasKandungan permohonanKertasKandungan3;
    private PermohonanKertasKandungan permohonanKertasKandungan4;
    private PermohonanKertasKandungan headingObj;
    private PermohonanKertasKandungan tujuanObj;
    private PermohonanKertasKandungan keputusanObj;
    private List<HakmilikPermohonan> hakmilikPermohonanList;
    private PermohonanPihak penerima;

//    private int bil = 0;
    private String kandungan;
    private String kandungan1;
    private String kandungan2;
    private String kandungan3;
    private String idKandungan;
    private List<PermohonanKertasKandungan> senaraiKertasKandungan1;
    private List<PermohonanKertasKandungan> senaraiKertasKandungan2;
    private List<PermohonanKertasKandungan> senaraiKertasKandungan3;
    private String tajuk;
    private Hakmilik hakmilik;
    private String tujuan;
    private String keputusan;
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss aaa");
    private int count=0;
    private int count5=0;
    String str[] ={"","a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p","q","r","s","t","u","v","w","x","y","z"};
    private List senaraiSyor[] = new ArrayList [ 10 ];
    private List senaraiSyorPentadbir[] = new ArrayList [ 10 ];
    private List<Pemohon> listPemohon;
    private String jam;
    private String minit;
    private String pagiPetang;
    private String masa;
    private String subtajuk;
    private String heading;
    private String lokasi;
    String namaProjek;

    @DefaultHandler
    public Resolution showForm() {
//        getContext().getRequest().setAttribute("ptg", Boolean.FALSE);
//        getContext().getRequest().setAttribute("form", Boolean.TRUE);
        getContext().getRequest().setAttribute("form1", Boolean.TRUE);
        return new JSP("pengambilan/negerisembilan/penarikanbalik/Penarikan_Balik_MMKN_NS.jsp").addParameter("tab", "true");
    }

    //For PTG
    public Resolution showForm2() {
//        getContext().getRequest().setAttribute("ptg", Boolean.TRUE);
//        getContext().getRequest().setAttribute("view", Boolean.TRUE);
        getContext().getRequest().setAttribute("formPtg", Boolean.TRUE);
        return new JSP("pengambilan/negerisembilan/penarikanbalik/Penarikan_Balik_MMKN_NS.jsp").addParameter("tab", "true");
    }

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {

        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");

        permohonan = permohonanDAO.findById(idPermohonan);
        String idSblm = "";
        if(permohonan.getPermohonanSebelum() != null)
        idSblm = permohonan.getPermohonanSebelum().getIdPermohonan();


        for(int k=1;k<=count5;k++){
            senaraiSyorPentadbir[k] = new ArrayList<PermohonanKertasKandungan>();
        }
        count5=0;

        for(int k=1;k<=count;k++){
            senaraiSyor[k] = new ArrayList<PermohonanKertasKandungan>();
        }
        count=0;
        Pengguna pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
//        permohonan = permohonanDAO.findById(idPermohonan);

       if(idSblm!=null){
        Permohonan p= permohonanDAO.findById(idSblm);
        Permohonan current= permohonanDAO.findById(idPermohonan);

        if(p != null){
        HakmilikPermohonan hp = new HakmilikPermohonan();
        pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        String[] tname = {"permohonan"};
        Object[] model = {permohonan};

        listPemohon = pemohonDAO.findByEqualCriterias(tname, model, null);
        hakmilikPermohonanList = hakmilikPermohonanDAO.findByEqualCriterias(tname, model, null);

        List<PermohonanPihak> listPenerima;
        listPenerima = permohonan.getSenaraiPihak();

        if (!(listPenerima.isEmpty())){
            penerima = listPenerima.get(0);
        }

        if(!hakmilikPermohonanList.isEmpty()){

            for (int w = 0; w < hakmilikPermohonanList.size(); w++){
                hp = current.getSenaraiHakmilik().get(w);
                if (hp.getHakmilik() !=null){
                    hakmilik = hakmilikDAO.findById(hp.getHakmilik().getIdHakmilik());
                    StringBuilder sb = new StringBuilder();


                    if(w==0){

                        lokasi = hakmilik.getNoLot()+", "+hakmilik.getKodHakmilik().getKod()+ hakmilik.getLuas()+", " +
                                 hakmilik.getBandarPekanMukim().getNama()+", DAERAH " + hakmilik.getDaerah().getNama()+", ";
                    }

                    if(w > 0){
                        if (w <= ((hakmilikPermohonanList.size() + 2) - 4)) {
                            lokasi = lokasi + hakmilik.getNoLot()+", "+hakmilik.getKodHakmilik().getKod()+ hakmilik.getLuas()+", " +
                                     hakmilik.getBandarPekanMukim().getNama()+", DAERAH " + hakmilik.getDaerah().getNama()+", ";
                        } else if (w == (hakmilikPermohonanList.size() - 1)) {
                            lokasi = lokasi + " dan " + hakmilik.getNoLot()+", "+hakmilik.getKodHakmilik().getKod()+ hakmilik.getLuas()+"," +
                                     hakmilik.getBandarPekanMukim().getNama()+", DAERAH " + hakmilik.getDaerah().getNama()+", ";
                            }
                        }

                    sb.append("TUJUAN KERTAS INI ADALAH UNTUK MENDAPATKAN PERTIMBANGAN DAN KEPUTUSAN MAJLIS MESYUARAT ");
                    sb.append("KERAJAAN NEGERI UNTUK PENARIKAN BALIK DARIPADA PENGAMBILAN SEBAHAGIAN TANAH DI LOT ");
                    sb.append(lokasi).append("NEGERI SEMBILAN BAGI ");
                    sb.append(p.getSebab()).append(".");
                    tujuan = sb.toString().toUpperCase();

                    }
                }
            }
        }


        String temp = "PERMOHONAN PENARIKAN BALIK DARIPADA PENGAMBILAN SEBAHAGIAN TANAH DI LOT "+ lokasi + " BAGI " + p.getSebab() + ".";
        heading = temp.toUpperCase();

        if (idPermohonan != null) {
            permohonanKertas = pendudukanSementaraMMKNService.findMMKNByIdPermohonan(idPermohonan);

            if (permohonanKertas != null) {
                headingObj = pendudukanSementaraMMKNService.findbil1ByIdKertas(permohonanKertas.getIdKertas(), 12);
                tujuanObj = pendudukanSementaraMMKNService.findbil1ByIdKertas(permohonanKertas.getIdKertas(), 13);
                keputusanObj = pendudukanSementaraMMKNService.findbil1ByIdKertas(permohonanKertas.getIdKertas(), 14);

                tajuk = permohonanKertas.getTajuk();
                if(tajuk.length()>0){
                    kertasBil = tajuk.substring(0, tajuk.length()-5);
                    kertasTahun = tajuk.substring(tajuk.length()-4, tajuk.length());
                }

                if(permohonanKertas.getTarikhSidang() != null) {
                    String tarikhSidang = dateFormat.format(permohonanKertas.getTarikhSidang());
                    tarikhmesyuarat = tarikhSidang.substring(0, 10);
                    jam = tarikhSidang.substring(11, 13);
                    minit = tarikhSidang.substring(14, 16);
                    String ampm = tarikhSidang.substring(20, 22);
                    if(ampm.equalsIgnoreCase("AM")){
                        pagiPetang = "PAGI";
                    }else if(ampm.equalsIgnoreCase("PM")){
                        pagiPetang = "PETANG";
                    }
                }
                tempat = permohonanKertas.getTempatSidang();
                peruntukanKewangan = pendudukanSementaraMMKNService.findbil1ByIdKertas(permohonanKertas.getIdKertas(), 2);
                ulasanPentadbir = pendudukanSementaraMMKNService.findbil1ByIdKertas(permohonanKertas.getIdKertas(), 3);
                ulasanPengarah = pendudukanSementaraMMKNService.findbil1ByIdKertas(permohonanKertas.getIdKertas(), 4);
                senaraiKertasKandungan1 = pendudukanSementaraMMKNService.findByKertas(permohonanKertas.getIdKertas(),1);
                senaraiKertasKandungan2 = pendudukanSementaraMMKNService.findByKertasSubTajuk(permohonanKertas.getIdKertas(),5,subtajuk);
                senaraiKertasKandungan3 = pendudukanSementaraMMKNService.findByKertasSubTajuk(permohonanKertas.getIdKertas(),7,subtajuk);


                List<PermohonanKertasKandungan> kandList5 = new ArrayList<PermohonanKertasKandungan>();
                PermohonanKertasKandungan maxKertasKand5 = new PermohonanKertasKandungan();
                kandList5 = pendudukanSementaraMMKNService.findByKertasDesc(permohonanKertas.getIdKertas(),5);
                if(kandList5!=null && !kandList5.isEmpty()){
                    maxKertasKand5 = kandList5.get(0);
                    if(maxKertasKand5!=null){
                            String subtajuk = maxKertasKand5.getSubtajuk();
                            String str = subtajuk.substring(2,3);
                            int tableCount = Integer.parseInt(str);
                            count5 = tableCount;
                            for(int k=1;k<=tableCount;k++){
                                senaraiSyorPentadbir[k] = new ArrayList<PermohonanKertasKandungan>();
                                String subtajuk1 = "5."+k;
                                senaraiSyorPentadbir[k] = pendudukanSementaraMMKNService.findByKertasSubTajuk(permohonanKertas.getIdKertas(),5,subtajuk1);
                            }
                    }
                }//if

                List<PermohonanKertasKandungan> kandList = new ArrayList<PermohonanKertasKandungan>();
                PermohonanKertasKandungan maxKertasKand = new PermohonanKertasKandungan();
                kandList = pendudukanSementaraMMKNService.findByKertasDesc(permohonanKertas.getIdKertas(),7);
                if(kandList!=null && !kandList.isEmpty()){
                    maxKertasKand = kandList.get(0);
                    if(maxKertasKand!=null){
                            String subtajuk = maxKertasKand.getSubtajuk();
                            String str = subtajuk.substring(2,3);
                            int tableCount = Integer.parseInt(str);
                            count = tableCount;
                            for(int k=1;k<=tableCount;k++){
                                senaraiSyor[k] = new ArrayList<PermohonanKertasKandungan>();
                                String subtajuk1 = "7."+k;
                                senaraiSyor[k] = pendudukanSementaraMMKNService.findByKertasSubTajuk(permohonanKertas.getIdKertas(),7,subtajuk1);
                            }
                    }
                }//if

            }
            if(kertasTahun == null) {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
                kertasTahun = sdf.format(new java.util.Date()).toString();
            }
        }

         keputusan = "Dikemukakan untuk mendapat Pertimbangan dan Keputusan Majlis Mesyurat Kerajaan Negeri Sembilan.";
        }
   }

    public Resolution simpan() throws ParseException {
        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);

        permohonan = permohonanDAO.findById(idPermohonan);
        Session s = sessionProvider.get();
        Transaction tx = s.beginTransaction();



        if (permohonanKertas == null) {
            permohonanKertas = new PermohonanKertas();
        }
        KodDokumen doc = new KodDokumen();
        doc.setKod("MMKN");
        InfoAudit infoAudit = new InfoAudit();
        cawangan = permohonan.getCawangan();
        InfoAudit iaPermohonan = new InfoAudit();
        iaPermohonan.setTarikhMasuk(new Date());
        iaPermohonan.setDimasukOleh(peng);
        permohonanKertas.setInfoAudit(iaPermohonan);
        permohonanKertas.setPermohonan(permohonan);
        permohonanKertas.setCawangan(cawangan);
        permohonanKertas.setKodDokumen(doc);

        kertasBil = getContext().getRequest().getParameter("kertasBil");
        kertasTahun = getContext().getRequest().getParameter("kertasTahun");
        if(kertasBil == null)
            kertasBil = "Tiada Data";
        permohonanKertas.setTajuk(kertasBil+"/"+kertasTahun);

        if(tempat == null)
            tempat = "Tiada Data";
        permohonanKertas.setTempatSidang(tempat);


//        permohonanKertasDAO.saveOrUpdate(permohonanKertas);
        permohonanKertas = pendudukanSementaraMMKNService.simpanPermohonanKertas(permohonanKertas);

        if (getContext().getRequest().getParameter("tujuan") != null) {
            infoAudit = new InfoAudit();
            if (tujuanObj == null) {
                tujuanObj = new PermohonanKertasKandungan();
                infoAudit.setDimasukOleh(peng);
                infoAudit.setTarikhMasuk(new java.util.Date());
            }else {
                infoAudit = tujuanObj.getInfoAudit();
                infoAudit.setDiKemaskiniOleh(peng);
                infoAudit.setTarikhKemaskini(new java.util.Date());
            }
            tujuanObj.setKertas(permohonanKertas);
            tujuanObj.setBil(13);
            kandungan = getContext().getRequest().getParameter("tujuan");
            if(kandungan==null){
                    tujuanObj.setKandungan("Tiada");
                }else{
                    tujuanObj.setKandungan(kandungan);
            }
            //tujuanObj.setKandungan(kandungan);
            cawangan = permohonan.getCawangan();
            tujuanObj.setCawangan(cawangan);
            tujuanObj.setInfoAudit(infoAudit);
            permohonanKertasKandunganDAO.saveOrUpdate(tujuanObj);
        }

        if (getContext().getRequest().getParameter("heading") != null) {
            infoAudit = new InfoAudit();
            if (headingObj == null) {
                headingObj = new PermohonanKertasKandungan();
                infoAudit.setDimasukOleh(peng);
                infoAudit.setTarikhMasuk(new java.util.Date());
            }else {
                infoAudit = headingObj.getInfoAudit();
                infoAudit.setDiKemaskiniOleh(peng);
                infoAudit.setTarikhKemaskini(new java.util.Date());
            }
            headingObj.setKertas(permohonanKertas);
            headingObj.setBil(12);
            kandungan = getContext().getRequest().getParameter("heading");
            if(kandungan==null){
                    headingObj.setKandungan("Tiada");
                }else{
                    headingObj.setKandungan(kandungan);
            }
            //headingObj.setKandungan(kandungan);
            cawangan = permohonan.getCawangan();
            headingObj.setCawangan(cawangan);
            headingObj.setInfoAudit(infoAudit);
            permohonanKertasKandunganDAO.saveOrUpdate(headingObj);
        }

        senaraiKertasKandungan1 = pendudukanSementaraMMKNService.findByKertas(permohonanKertas.getIdKertas(),1);
        int kira1 = Integer.parseInt(getContext().getRequest().getParameter("rowCount1"));
        for (int i = 1; i <= kira1; i++) {
            if (senaraiKertasKandungan1.size() != 0 && i <= senaraiKertasKandungan1.size()) {
                {
                    Long id = senaraiKertasKandungan1.get(i - 1).getIdKandungan();

                    if (id != null) {
                        permohonanKertasKandungan = pendudukanSementaraMMKNService.findkandunganByIdKandungan(id);
                    }
                }
            } else {
                permohonanKertasKandungan = new PermohonanKertasKandungan();
            }
            permohonanKertasKandungan.setKertas(permohonanKertas);
            permohonanKertasKandungan.setBil(1);
            kandungan = getContext().getRequest().getParameter("kandungan1" + i);
            if(kandungan==null){
                    permohonanKertasKandungan.setKandungan("Tiada");
                }else{
                    permohonanKertasKandungan.setKandungan(kandungan);
            }

            cawangan = permohonan.getCawangan();
            permohonanKertasKandungan.setCawangan(cawangan);
            permohonanKertasKandungan.setSubtajuk("2." + i);
            InfoAudit iaP = new InfoAudit();
            iaP.setTarikhMasuk(new Date());
            iaP.setDimasukOleh(peng);
            permohonanKertasKandungan.setInfoAudit(iaP);
            permohonanKertasKandunganDAO.saveOrUpdate(permohonanKertasKandungan);
        }

        if (getContext().getRequest().getParameter("peruntukanKewangan.kandungan") != null) {
            if (peruntukanKewangan == null) {
                peruntukanKewangan = new PermohonanKertasKandungan();
            }
            peruntukanKewangan.setKertas(permohonanKertas);
            peruntukanKewangan.setBil(2);
            kandungan = getContext().getRequest().getParameter("peruntukanKewangan.kandungan");
            peruntukanKewangan.setKandungan(kandungan);
            cawangan = permohonan.getCawangan();
            peruntukanKewangan.setCawangan(cawangan);
            InfoAudit iaP = new InfoAudit();
            iaP.setTarikhMasuk(new Date());
            iaP.setDimasukOleh(peng);
            peruntukanKewangan.setInfoAudit(iaP);
            permohonanKertasKandunganDAO.saveOrUpdate(peruntukanKewangan);
        }else
            {
            if (peruntukanKewangan == null) {
                    peruntukanKewangan = new PermohonanKertasKandungan();
                }
                peruntukanKewangan.setKertas(permohonanKertas);
                peruntukanKewangan.setBil(2);
                kandungan = "TIADA DATA";
                peruntukanKewangan.setKandungan(kandungan);
                cawangan = permohonan.getCawangan();
                peruntukanKewangan.setCawangan(cawangan);
                InfoAudit iaP = new InfoAudit();
                iaP.setTarikhMasuk(new Date());
                iaP.setDimasukOleh(peng);
                peruntukanKewangan.setInfoAudit(iaP);
                permohonanKertasKandunganDAO.saveOrUpdate(peruntukanKewangan);
            }

        if (getContext().getRequest().getParameter("ulasanPentadbir.kandungan") != null) {
            if (ulasanPentadbir == null) {
                ulasanPentadbir = new PermohonanKertasKandungan();
            }
            ulasanPentadbir.setKertas(permohonanKertas);
            ulasanPentadbir.setBil(3);
            kandungan = getContext().getRequest().getParameter("ulasanPentadbir.kandungan");
            ulasanPentadbir.setKandungan(kandungan);
            cawangan = permohonan.getCawangan();
            ulasanPentadbir.setCawangan(cawangan);
            InfoAudit iaP = new InfoAudit();
            iaP.setTarikhMasuk(new Date());
            iaP.setDimasukOleh(peng);
            ulasanPentadbir.setInfoAudit(iaP);
            permohonanKertasKandunganDAO.saveOrUpdate(ulasanPentadbir);
        }else
            {
            if (ulasanPentadbir == null) {
                    ulasanPentadbir = new PermohonanKertasKandungan();
                }
                ulasanPentadbir.setKertas(permohonanKertas);
                ulasanPentadbir.setBil(3);
                kandungan = "TIADA DATA";
                ulasanPentadbir.setKandungan(kandungan);
                cawangan = permohonan.getCawangan();
                ulasanPentadbir.setCawangan(cawangan);
                InfoAudit iaP = new InfoAudit();
                iaP.setTarikhMasuk(new Date());
                iaP.setDimasukOleh(peng);
                ulasanPentadbir.setInfoAudit(iaP);
                permohonanKertasKandunganDAO.saveOrUpdate(ulasanPentadbir);
            }


        count5 =  Integer.parseInt(getContext().getRequest().getParameter("count5"));
        for(int i=1;i<=count5;i++){
            String subtajuk = "5."+i;
            senaraiKertasKandungan2 = pendudukanSementaraMMKNService.findByKertasSubTajuk(permohonanKertas.getIdKertas(),5,subtajuk);
            int rowCount5 =  Integer.parseInt(getContext().getRequest().getParameter("count5"+i));
            for (int k = 1; k <= rowCount5; k++) {
                if (senaraiKertasKandungan2!=null && senaraiKertasKandungan2.size() != 0 && k <= senaraiKertasKandungan2.size()) {
                    Long id3 = senaraiKertasKandungan2.get(k - 1).getIdKandungan();
                    if (id3 != null) {
                        permohonanKertasKandungan2 = pendudukanSementaraMMKNService.findkandunganByIdKandungan(id3);
                        infoAudit = permohonanKertasKandungan2.getInfoAudit();
                        infoAudit.setDiKemaskiniOleh(peng);
                        infoAudit.setTarikhKemaskini(new java.util.Date());
                    }
                } else {
                    permohonanKertasKandungan2 = new PermohonanKertasKandungan();
                    infoAudit.setDimasukOleh(peng);
                    infoAudit.setTarikhMasuk(new java.util.Date());
                }

                permohonanKertasKandungan2.setKertas(permohonanKertas);
                permohonanKertasKandungan2.setBil(5);
                kandungan = getContext().getRequest().getParameter("syorPentadbir"+i+ k);
                if(kandungan==null){
                    permohonanKertasKandungan2.setKandungan("Tiada");
                }else{
                    permohonanKertasKandungan2.setKandungan(kandungan);
//                }
                cawangan = permohonan.getCawangan();
                permohonanKertasKandungan2.setCawangan(cawangan);
                permohonanKertasKandungan2.setSubtajuk("5."+ i+"."+str[k-1]);
                InfoAudit iaP = new InfoAudit();
                iaP.setTarikhMasuk(new Date());
                iaP.setDimasukOleh(peng);
                permohonanKertasKandungan2.setInfoAudit(infoAudit);
                permohonanKertasKandunganDAO.saveOrUpdate(permohonanKertasKandungan2);
                }
            }
        }//if table count


        if (getContext().getRequest().getParameter("ulasanPengarah.kandungan") != null) {
            if (ulasanPengarah == null) {
                ulasanPengarah = new PermohonanKertasKandungan();
            }
            ulasanPengarah.setKertas(permohonanKertas);
            ulasanPengarah.setBil(4);
            kandungan = getContext().getRequest().getParameter("ulasanPengarah.kandungan");
            if (kandungan == null){
                ulasanPengarah.setKandungan("Tiada Data");
            }else{
                 ulasanPengarah.setKandungan(kandungan);
            }
            cawangan = permohonan.getCawangan();
            ulasanPengarah.setCawangan(cawangan);
            InfoAudit iaP = new InfoAudit();
            iaP.setTarikhMasuk(new Date());
            iaP.setDimasukOleh(peng);
            ulasanPengarah.setInfoAudit(iaP);
            permohonanKertasKandunganDAO.saveOrUpdate(ulasanPengarah);
        }


        count =  Integer.parseInt(getContext().getRequest().getParameter("count"));

        for(int i=1;i<=count;i++){
            String subtajuk = "7."+i;
            senaraiKertasKandungan3 = pendudukanSementaraMMKNService.findByKertasSubTajuk(permohonanKertas.getIdKertas(),7,subtajuk);
            int rowCount =  Integer.parseInt(getContext().getRequest().getParameter("count"+i));
            for (int k = 1; k <= rowCount; k++) {
                if (senaraiKertasKandungan3!=null && senaraiKertasKandungan3.size() != 0 && k <= senaraiKertasKandungan3.size()) {
                    Long id3 = senaraiKertasKandungan3.get(k - 1).getIdKandungan();
                    if (id3 != null) {
                        permohonanKertasKandungan3 = pendudukanSementaraMMKNService.findkandunganByIdKandungan(id3);
                        infoAudit = permohonanKertasKandungan3.getInfoAudit();
                        infoAudit.setDiKemaskiniOleh(peng);
                        infoAudit.setTarikhKemaskini(new java.util.Date());
                    }
                } else {
                    permohonanKertasKandungan3 = new PermohonanKertasKandungan();
                    infoAudit.setDimasukOleh(peng);
                    infoAudit.setTarikhMasuk(new java.util.Date());
                }

                permohonanKertasKandungan3.setKertas(permohonanKertas);
                permohonanKertasKandungan3.setBil(7);
                kandungan = getContext().getRequest().getParameter("syor"+i+ k);
                if(kandungan==null){
                    permohonanKertasKandungan3.setKandungan("Tiada");
                }else{
                    permohonanKertasKandungan3.setKandungan(kandungan);
                }
//                cawangan = permohonan.getCawangan();
                permohonanKertasKandungan3.setCawangan(cawangan);
                permohonanKertasKandungan3.setSubtajuk("7."+ i+"."+str[k-1]);
                permohonanKertasKandungan3.setInfoAudit(infoAudit);
                permohonanKertasKandunganDAO.saveOrUpdate(permohonanKertasKandungan3);
//                InfoAudit iaP = new InfoAudit();
//                iaP.setTarikhMasuk(new Date());
//                iaP.setDimasukOleh(peng);
//                permohonanKertasKandungan3.setInfoAudit(iaP);
//                permohonanKertasKandunganDAO.saveOrUpdate(permohonanKertasKandungan3);
            }
        }//if table count


//        if (getContext().getRequest().getParameter("kertasBil.kandungan") != null) {
//            if (kertasBil == null) {
//                kertasBil = new PermohonanKertasKandungan();
//
//            }
//            kertasBil.setKertas(permohonanKertas);
//            kertasBil.setBil(6);
//            kandungan = getContext().getRequest().getParameter("kertasBil.kandungan");
//            kertasBil.setKandungan(kandungan);
//            cawangan = permohonan.getCawangan();
//            kertasBil.setCawangan(cawangan);
//            InfoAudit iaP = new InfoAudit();
//            iaP.setTarikhMasuk(new Date());
//            iaP.setDimasukOleh(peng);
//            kertasBil.setInfoAudit(iaP);
//            permohonanKertasKandunganDAO.saveOrUpdate(kertasBil);
//        }

//        if (getContext().getRequest().getParameter("kertasTahun.kandungan") != null) {
//                if (kertasTahun == null) {
//                    kertasTahun = new PermohonanKertasKandungan();
//
//                }
//                kertasTahun.setKertas(permohonanKertas);
//                kertasTahun.setBil(11);
//                kandungan = getContext().getRequest().getParameter("kertasTahun.kandungan");
//                if(kandungan==null){
//                    kertasTahun.setKandungan("Tiada");
//                }else{
//                    kertasTahun.setKandungan(kandungan);
//                }
//                cawangan = permohonan.getCawangan();
//                kertasTahun.setCawangan(cawangan);
//                InfoAudit iaP = new InfoAudit();
//                iaP.setTarikhMasuk(new Date());
//                iaP.setDimasukOleh(peng);
//                kertasTahun.setInfoAudit(iaP);
//                permohonanKertasKandunganDAO.saveOrUpdate(kertasTahun);
//             }

//        if (getContext().getRequest().getParameter("masaObj.kandungan") != null) {
//            if (masaObj == null) {
//                masaObj = new PermohonanKertasKandungan();
//
//            }
//            masaObj.setKertas(permohonanKertas);
//            masaObj.setBil(8);
//            kandungan = getContext().getRequest().getParameter("masaObj.kandungan");
//            masaObj.setKandungan(kandungan);
//            cawangan = permohonan.getCawangan();
//            masaObj.setCawangan(cawangan);
//            InfoAudit iaP = new InfoAudit();
//            iaP.setTarikhMasuk(new Date());
//            iaP.setDimasukOleh(peng);
//            masaObj.setInfoAudit(iaP);
//            permohonanKertasKandunganDAO.saveOrUpdate(masaObj);
//        }

//        if (getContext().getRequest().getParameter("tarikhmesyuarat.kandungan") != null) {
//            if (tarikhmesyuarat == null) {
//                tarikhmesyuarat = new PermohonanKertasKandungan();
//
//            }
//            tarikhmesyuarat.setKertas(permohonanKertas);
//            tarikhmesyuarat.setBil(9);
//            kandungan = getContext().getRequest().getParameter("tarikhmesyuarat.kandungan");
//            tarikhmesyuarat.setKandungan(kandungan);
//            cawangan = permohonan.getCawangan();
//            tarikhmesyuarat.setCawangan(cawangan);
//            InfoAudit iaP = new InfoAudit();
//            iaP.setTarikhMasuk(new Date());
//            iaP.setDimasukOleh(peng);
//            tarikhmesyuarat.setInfoAudit(iaP);
//            permohonanKertasKandunganDAO.saveOrUpdate(tarikhmesyuarat);
//        }

//        if (getContext().getRequest().getParameter("tempat.kandungan") != null) {
//            if (tempat == null) {
//                tempat = new PermohonanKertasKandungan();
//            }
//            tempat.setKertas(permohonanKertas);
//            tempat.setBil(10);
//            kandungan = getContext().getRequest().getParameter("tempat.kandungan");
//            tempat.setKandungan(kandungan);
//            cawangan = permohonan.getCawangan();
//            tempat.setCawangan(cawangan);
//            InfoAudit iaP = new InfoAudit();
//            iaP.setTarikhMasuk(new Date());
//            iaP.setDimasukOleh(peng);
//            tempat.setInfoAudit(iaP);
//            permohonanKertasKandunganDAO.saveOrUpdate(tempat);
//        }

        tx.commit();
        rehydrate();
        addSimpleMessage("Maklumat telah berjaya disimpan.");
        getContext().getRequest().setAttribute("form1", Boolean.TRUE);
        return new RedirectResolution(PenarikanBalikMMKNNSActionBean.class, "locate");
    }

    public Resolution simpanPtg() throws ParseException {
        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = permohonanDAO.findById(idPermohonan);
        Session s = sessionProvider.get();
        Transaction tx = s.beginTransaction();

        if (permohonanKertas == null) {
            permohonanKertas = new PermohonanKertas();
        }
        KodDokumen doc = new KodDokumen();
        doc.setKod("MMKN");
        cawangan = permohonan.getCawangan();
        InfoAudit iaPermohonan = new InfoAudit();
        iaPermohonan.setTarikhMasuk(new Date());
        iaPermohonan.setDimasukOleh(peng);
        permohonanKertas.setInfoAudit(iaPermohonan);
        permohonanKertas.setPermohonan(permohonan);
        permohonanKertas.setCawangan(cawangan);
        permohonanKertas.setKodDokumen(doc);
        kertasBil = getContext().getRequest().getParameter("kertasBil");
        kertasTahun = getContext().getRequest().getParameter("kertasTahun");
        permohonanKertas.setTajuk(kertasBil+"/"+kertasTahun);
        tarikhmesyuarat = getContext().getRequest().getParameter("tarikhmesyuarat");
        jam = getContext().getRequest().getParameter("jam");
        minit = getContext().getRequest().getParameter("minit");
        pagiPetang = getContext().getRequest().getParameter("pagiPetang");
        if(tarikhmesyuarat.length() > 0 && jam.length() > 0 && minit.length() > 0 && pagiPetang.length()>0){
            String ampm = "";
            if(pagiPetang.equalsIgnoreCase("PAGI")){
                ampm = "AM";
            }else if(pagiPetang.equalsIgnoreCase("PETANG")){
                ampm = "PM";
            }
            String strMasa = tarikhmesyuarat + " " + jam + ":" + minit + ":00 " + ampm;
            Date tarikhSidang = dateFormat.parse(strMasa);
            permohonanKertas.setTarikhSidang(tarikhSidang);
        }
        permohonanKertas.setTempatSidang(tempat);
//        permohonanKertasDAO.saveOrUpdate(permohonanKertas);
         permohonanKertas = pendudukanSementaraMMKNService.simpanPermohonanKertas(permohonanKertas);


//        senaraiKertasKandungan1 = pendudukanSementaraMMKNService.findByKertas(permohonanKertas.getIdKertas(),1);
//        int kira1 = Integer.parseInt(getContext().getRequest().getParameter("rowCount1"));
//        for (int i = 1; i <= kira1; i++) {
//            if (senaraiKertasKandungan1.size() != 0 && i <= senaraiKertasKandungan1.size()) {
//                {
//                    Long id = senaraiKertasKandungan1.get(i - 1).getIdKandungan();
//
//                    if (id != null) {
//                        permohonanKertasKandungan = pendudukanSementaraMMKNService.findkandunganByIdKandungan(id);
//                    }
//                }
//            } else {
//                permohonanKertasKandungan = new PermohonanKertasKandungan();
//            }
//            permohonanKertasKandungan.setKertas(permohonanKertas);
//            permohonanKertasKandungan.setBil(1);
//            kandungan = getContext().getRequest().getParameter("kandungan1" + i);
//            permohonanKertasKandungan.setKandungan(kandungan);
//            cawangan = permohonan.getCawangan();
//            permohonanKertasKandungan.setCawangan(cawangan);
//            permohonanKertasKandungan.setSubtajuk("2." + i);
//            InfoAudit iaP = new InfoAudit();
//            iaP.setTarikhMasuk(new Date());
//            iaP.setDimasukOleh(peng);
//            permohonanKertasKandungan.setInfoAudit(iaP);
//            permohonanKertasKandunganDAO.saveOrUpdate(permohonanKertasKandungan);
//        }
//
//        if (getContext().getRequest().getParameter("peruntukanKewangan.kandungan") != null) {
//            if (peruntukanKewangan == null) {
//                peruntukanKewangan = new PermohonanKertasKandungan();
//            }
//            peruntukanKewangan.setKertas(permohonanKertas);
//            peruntukanKewangan.setBil(2);
//            kandungan = getContext().getRequest().getParameter("peruntukanKewangan.kandungan");
//            peruntukanKewangan.setKandungan(kandungan);
//            cawangan = permohonan.getCawangan();
//            peruntukanKewangan.setCawangan(cawangan);
//            InfoAudit iaP = new InfoAudit();
//            iaP.setTarikhMasuk(new Date());
//            iaP.setDimasukOleh(peng);
//            peruntukanKewangan.setInfoAudit(iaP);
//            permohonanKertasKandunganDAO.saveOrUpdate(peruntukanKewangan);
//        }
//
//        if (getContext().getRequest().getParameter("ulasanPentadbir.kandungan") != null) {
//            if (ulasanPentadbir == null) {
//                ulasanPentadbir = new PermohonanKertasKandungan();
//            }
//            ulasanPentadbir.setKertas(permohonanKertas);
//            ulasanPentadbir.setBil(3);
//            kandungan = getContext().getRequest().getParameter("ulasanPentadbir.kandungan");
//            ulasanPentadbir.setKandungan(kandungan);
//            cawangan = permohonan.getCawangan();
//            ulasanPentadbir.setCawangan(cawangan);
//            InfoAudit iaP = new InfoAudit();
//            iaP.setTarikhMasuk(new Date());
//            iaP.setDimasukOleh(peng);
//            ulasanPentadbir.setInfoAudit(iaP);
//            permohonanKertasKandunganDAO.saveOrUpdate(ulasanPentadbir);
//        }
//
//
//        count5 =  Integer.parseInt(getContext().getRequest().getParameter("count5"));
//
//        for(int i=1;i<=count5;i++){
//            String subtajuk = "5."+i;
//            senaraiKertasKandungan2 = pendudukanSementaraMMKNService.findByKertasSubTajuk(permohonanKertas.getIdKertas(),5,subtajuk);
//            int rowCount5 =  Integer.parseInt(getContext().getRequest().getParameter("count5"+i));
//            for (int k = 1; k <= rowCount5; k++) {
//                if (senaraiKertasKandungan2!=null && senaraiKertasKandungan2.size() != 0 && k <= senaraiKertasKandungan2.size()) {
//                    Long id3 = senaraiKertasKandungan2.get(k - 1).getIdKandungan();
//                    if (id3 != null) {
//                        permohonanKertasKandungan2 = pendudukanSementaraMMKNService.findkandunganByIdKandungan(id3);
//                    }
//                } else {
//                    permohonanKertasKandungan2 = new PermohonanKertasKandungan();
//                }
//
//                permohonanKertasKandungan2.setKertas(permohonanKertas);
//                permohonanKertasKandungan2.setBil(5);
//                kandungan = getContext().getRequest().getParameter("syorPentadbir"+i+ k);
//                if(kandungan==null){
//                    permohonanKertasKandungan2.setKandungan("Tiada");
//                }else{
//                    permohonanKertasKandungan2.setKandungan(kandungan);
//                }
//                cawangan = permohonan.getCawangan();
//                permohonanKertasKandungan2.setCawangan(cawangan);
//                permohonanKertasKandungan2.setSubtajuk("5."+ i+"."+str[k-1]);
//                InfoAudit iaP = new InfoAudit();
//                iaP.setTarikhMasuk(new Date());
//                iaP.setDimasukOleh(peng);
//                permohonanKertasKandungan2.setInfoAudit(iaP);
//                permohonanKertasKandunganDAO.saveOrUpdate(permohonanKertasKandungan2);
//            }
//        }//if table count
//

        if (getContext().getRequest().getParameter("ulasanPengarah.kandungan") != null) {
            if (ulasanPengarah == null) {
                ulasanPengarah = new PermohonanKertasKandungan();
            }
            ulasanPengarah.setKertas(permohonanKertas);
            ulasanPengarah.setBil(4);
            kandungan = getContext().getRequest().getParameter("ulasanPengarah.kandungan");
            ulasanPengarah.setKandungan(kandungan);
            cawangan = permohonan.getCawangan();
            ulasanPengarah.setCawangan(cawangan);
            InfoAudit iaP = new InfoAudit();
            iaP.setTarikhMasuk(new Date());
            iaP.setDimasukOleh(peng);
            ulasanPengarah.setInfoAudit(iaP);
            permohonanKertasKandunganDAO.saveOrUpdate(ulasanPengarah);
        }


        count =  Integer.parseInt(getContext().getRequest().getParameter("count"));

        for(int i=1;i<=count;i++){
            String subtajuk = "7."+i;
            senaraiKertasKandungan3 = pendudukanSementaraMMKNService.findByKertasSubTajuk(permohonanKertas.getIdKertas(),7,subtajuk);
            int rowCount =  Integer.parseInt(getContext().getRequest().getParameter("count"+i));
            for (int k = 1; k <= rowCount; k++) {
                if (senaraiKertasKandungan3!=null && senaraiKertasKandungan3.size() != 0 && k <= senaraiKertasKandungan3.size()) {
                    Long id3 = senaraiKertasKandungan3.get(k - 1).getIdKandungan();
                    if (id3 != null) {
                        permohonanKertasKandungan3 = pendudukanSementaraMMKNService.findkandunganByIdKandungan(id3);
                    }
                } else {
                    permohonanKertasKandungan3 = new PermohonanKertasKandungan();
                }

                permohonanKertasKandungan3.setKertas(permohonanKertas);
                permohonanKertasKandungan3.setBil(7);
                kandungan = getContext().getRequest().getParameter("syor"+i+ k);
                if(kandungan==null){
                    permohonanKertasKandungan3.setKandungan("Tiada");
                }else{
                    permohonanKertasKandungan3.setKandungan(kandungan);
                }
                cawangan = permohonan.getCawangan();
                permohonanKertasKandungan3.setCawangan(cawangan);
                permohonanKertasKandungan3.setSubtajuk("7."+ i+"."+str[k-1]);
                InfoAudit iaP = new InfoAudit();
                iaP.setTarikhMasuk(new Date());
                iaP.setDimasukOleh(peng);
                permohonanKertasKandungan3.setInfoAudit(iaP);
                permohonanKertasKandunganDAO.saveOrUpdate(permohonanKertasKandungan3);
            }
        }//if table count


//        if (getContext().getRequest().getParameter("kertasBil.kandungan") != null) {
//            if (kertasBil == null) {
//                kertasBil = new PermohonanKertasKandungan();
//
//            }
//            kertasBil.setKertas(permohonanKertas);
//            kertasBil.setBil(6);
//            kandungan = getContext().getRequest().getParameter("kertasBil.kandungan");
//            kertasBil.setKandungan(kandungan);
//            cawangan = permohonan.getCawangan();
//            kertasBil.setCawangan(cawangan);
//            InfoAudit iaP = new InfoAudit();
//            iaP.setTarikhMasuk(new Date());
//            iaP.setDimasukOleh(peng);
//            kertasBil.setInfoAudit(iaP);
//            permohonanKertasKandunganDAO.saveOrUpdate(kertasBil);
//        }
//
//        if (getContext().getRequest().getParameter("kertasTahun.kandungan") != null) {
//                if (kertasTahun == null) {
//                    kertasTahun = new PermohonanKertasKandungan();
//
//                }
//                kertasTahun.setKertas(permohonanKertas);
//                kertasTahun.setBil(11);
//                kandungan = getContext().getRequest().getParameter("kertasTahun.kandungan");
//                kertasTahun.setKandungan(kandungan);
//                cawangan = permohonan.getCawangan();
//                kertasTahun.setCawangan(cawangan);
//                InfoAudit iaP = new InfoAudit();
//                iaP.setTarikhMasuk(new Date());
//                iaP.setDimasukOleh(peng);
//                kertasTahun.setInfoAudit(iaP);
//                permohonanKertasKandunganDAO.saveOrUpdate(kertasTahun);
//             }

//        if (getContext().getRequest().getParameter("masaObj.kandungan") != null) {
//            if (masaObj == null) {
//                masaObj = new PermohonanKertasKandungan();
//
//            }
//            masaObj.setKertas(permohonanKertas);
//            masaObj.setBil(8);
//            kandungan = getContext().getRequest().getParameter("masaObj.kandungan");
//            masaObj.setKandungan(kandungan);
//            cawangan = permohonan.getCawangan();
//            masaObj.setCawangan(cawangan);
//            InfoAudit iaP = new InfoAudit();
//            iaP.setTarikhMasuk(new Date());
//            iaP.setDimasukOleh(peng);
//            masaObj.setInfoAudit(iaP);
//            permohonanKertasKandunganDAO.saveOrUpdate(masaObj);
//        }


//                if (masaObj == null) {
//                    {masaObj = new PermohonanKertasKandungan();}
//               System.out.println("-------------masaObjobj");
//                masaObj.setKertas(permohonanKertas);
//                masaObj.setBil(8);
//                jam = getContext().getRequest().getParameter("jam");
//                minit = getContext().getRequest().getParameter("minit");
//                pagiPetang = getContext().getRequest().getParameter("pagiPetang");
//                String StrMasa = jam + ":" + minit + " " + pagiPetang;
//                logger.debug(jam+"jam" + minit + "minit" + pagiPetang +"pagiPetang");
//                masaObj.setKandungan(StrMasa);
//                cawangan = permohonan.getCawangan();
//                masaObj.setCawangan(cawangan);
//                InfoAudit iaP = new InfoAudit();
//                iaP.setTarikhMasuk(new Date());
//                iaP.setDimasukOleh(peng);
//                masaObj.setInfoAudit(iaP);
//                permohonanKertasKandunganDAO.saveOrUpdate(masaObj);
//             }

//        if (getContext().getRequest().getParameter("tarikhmesyuarat.kandungan") != null) {
//            if (tarikhmesyuarat == null) {
//                tarikhmesyuarat = new PermohonanKertasKandungan();
//
//            }
//            tarikhmesyuarat.setKertas(permohonanKertas);
//            tarikhmesyuarat.setBil(9);
//            kandungan = getContext().getRequest().getParameter("tarikhmesyuarat.kandungan");
//            tarikhmesyuarat.setKandungan(kandungan);
//            cawangan = permohonan.getCawangan();
//            tarikhmesyuarat.setCawangan(cawangan);
//            InfoAudit iaP = new InfoAudit();
//            iaP.setTarikhMasuk(new Date());
//            iaP.setDimasukOleh(peng);
//            tarikhmesyuarat.setInfoAudit(iaP);
//            permohonanKertasKandunganDAO.saveOrUpdate(tarikhmesyuarat);
//        }

//        if (getContext().getRequest().getParameter("tempat.kandungan") != null) {
//            if (tempat == null) {
//                tempat = new PermohonanKertasKandungan();
//            }
//            tempat.setKertas(permohonanKertas);
//            tempat.setBil(10);
//            kandungan = getContext().getRequest().getParameter("tempat.kandungan");
//            tempat.setKandungan(kandungan);
//            cawangan = permohonan.getCawangan();
//            tempat.setCawangan(cawangan);
//            InfoAudit iaP = new InfoAudit();
//            iaP.setTarikhMasuk(new Date());
//            iaP.setDimasukOleh(peng);
//            tempat.setInfoAudit(iaP);
//            permohonanKertasKandunganDAO.saveOrUpdate(tempat);
//        }

        if (getContext().getRequest().getParameter("keputusan") != null) {
            InfoAudit infoAudit = new InfoAudit();
            if (keputusanObj == null) {
                keputusanObj = new PermohonanKertasKandungan();
                infoAudit.setDimasukOleh(peng);
                infoAudit.setTarikhMasuk(new java.util.Date());
            }else {
                infoAudit = keputusanObj.getInfoAudit();
                infoAudit.setDiKemaskiniOleh(peng);
                infoAudit.setTarikhKemaskini(new java.util.Date());
            }
            keputusanObj.setKertas(permohonanKertas);
            keputusanObj.setBil(14);
            kandungan = getContext().getRequest().getParameter("keputusan");
            if(kandungan==null){
                    keputusanObj.setKandungan("Tiada");
                }else{
                    keputusanObj.setKandungan(kandungan);
            }
            //tujuanObj.setKandungan(kandungan);
            cawangan = permohonan.getCawangan();
            keputusanObj.setCawangan(cawangan);
            keputusanObj.setInfoAudit(infoAudit);
            permohonanKertasKandunganDAO.saveOrUpdate(keputusanObj);
        }


        tx.commit();
        rehydrate();
        addSimpleMessage("Maklumat telah berjaya disimpan.");
        getContext().getRequest().setAttribute("formPtg", Boolean.TRUE);
//        return new RedirectResolution(PenarikanBalikMMKNNSActionBean.class, "locate");
         return new JSP("pengambilan/negerisembilan/penarikanbalik/Penarikan_Balik_MMKN_NS.jsp").addParameter("tab", "true");
    }

    public Resolution deleteSingle() {
        InfoAudit ia = new InfoAudit();
        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);

        idKandungan = getContext().getRequest().getParameter("idKandungan");
        try{
        permohonanKertasKandungan = pendudukanSementaraMMKNService.findkandunganByIdKandungan(Long.parseLong(idKandungan));
        }catch(Exception e){
            logger.debug("Hapus empty record");

        }
       if (permohonanKertasKandungan != null) {

            ia = peng.getInfoAudit();
            ia.setDiKemaskiniOleh(peng);
            ia.setTarikhKemaskini(new java.util.Date());
            permohonanKertasKandungan.setInfoAudit(ia);
            permohonanKertasKandungan.setCawangan(cawangan);
            pendudukanSementaraMMKNService.deleteKertasKandungan(permohonanKertasKandungan);
        }

        rehydrate();
        addSimpleMessage("Maklumat telah berjaya dihapus.");
        String form1 = getContext().getRequest().getParameter("form1");
        if(form1.equals("true"))
        getContext().getRequest().setAttribute("form1", Boolean.TRUE);
        String formPtg = getContext().getRequest().getParameter("formPtg");
        if(formPtg.equals("true"))
        getContext().getRequest().setAttribute("formPtg", Boolean.TRUE);


      return new JSP("pengambilan/negerisembilan/penarikanbalik/Penarikan_Balik_MMKN_NS.jsp").addParameter("tab", "true");
    }

    public Resolution deleteTable() {

        int bil = Integer.parseInt(getContext().getRequest().getParameter("bil"));

        List<PermohonanKertasKandungan> kandList = pendudukanSementaraMMKNService.findByKertasDesc(permohonanKertas.getIdKertas(),bil);

        if(kandList!=null && !kandList.isEmpty()){
             PermohonanKertasKandungan maxKertasKand = kandList.get(0);
             if(maxKertasKand!=null){
                 String subtajuk = maxKertasKand.getSubtajuk();
                 String str = subtajuk.substring(2,3);
                 String subtajuk1 = bil+"."+str;
                 List<PermohonanKertasKandungan> kandList1 = pendudukanSementaraMMKNService.findByKertasSubTajuk(permohonanKertas.getIdKertas(),bil,subtajuk1);
                 for(PermohonanKertasKandungan kand : kandList1){
                     pendudukanSementaraMMKNService.deleteKertasKandungan(kand);
                 }
             }
         }

       rehydrate();
        addSimpleMessage("Maklumat telah berjaya dihapus.");
        String form1 = getContext().getRequest().getParameter("form1");
        if(form1.equals("true"))
        getContext().getRequest().setAttribute("form1", Boolean.TRUE);
        String formPtg = getContext().getRequest().getParameter("formPtg");
        if(formPtg.equals("true"))
        getContext().getRequest().setAttribute("formPtg", Boolean.TRUE);


      return new JSP("pengambilan/negerisembilan/penarikanbalik/Penarikan_Balik_MMKN_NS.jsp").addParameter("tab", "true");

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

    public PermohonanKertasKandungan getPermohonanKertasKandungan() {
        return permohonanKertasKandungan;
    }

    public void setPermohonanKertasKandungan(PermohonanKertasKandungan permohonanKertasKandungan) {
        this.permohonanKertasKandungan = permohonanKertasKandungan;
    }

    public PermohonanKertasKandungan getPermohonanKertasKandungan2() {
        return permohonanKertasKandungan2;
    }

    public void setPermohonanKertasKandungan2(PermohonanKertasKandungan permohonanKertasKandungan2) {
        this.permohonanKertasKandungan2 = permohonanKertasKandungan2;
    }

    public PermohonanKertasKandungan getPermohonanKertasKandungan3() {
        return permohonanKertasKandungan3;
    }

    public void setPermohonanKertasKandungan3(PermohonanKertasKandungan permohonanKertasKandungan3) {
        this.permohonanKertasKandungan3 = permohonanKertasKandungan3;
    }

    public PermohonanKertasKandungan getPermohonanKertasKandungan4() {
        return permohonanKertasKandungan4;
    }

    public void setPermohonanKertasKandungan4(PermohonanKertasKandungan permohonanKertasKandungan4) {
        this.permohonanKertasKandungan4 = permohonanKertasKandungan4;
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

//    public int getBil() {
//        return bil;
//    }
//
//    public void setBil(int bil) {
//        this.bil = bil;
//    }

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

    public List<PermohonanKertasKandungan> getSenaraiKertasKandungan2() {
        return senaraiKertasKandungan2;
    }

    public void setSenaraiKertasKandungan2(List<PermohonanKertasKandungan> senaraiKertasKandungan2) {
        this.senaraiKertasKandungan2 = senaraiKertasKandungan2;
    }

    public String getTujuan() {
        return tujuan;
    }

    public void setTujuan(String tujuan) {
        this.tujuan = tujuan;
    }

    public List<PermohonanKertasKandungan> getSenaraiKertasKandungan3() {
        return senaraiKertasKandungan3;
    }

    public void setSenaraiKertasKandungan3(List<PermohonanKertasKandungan> senaraiKertasKandungan3) {
        this.senaraiKertasKandungan3 = senaraiKertasKandungan3;
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

    public PermohonanKertasKandungan getPeruntukanKewangan() {
        return peruntukanKewangan;
    }

    public void setPeruntukanKewangan(PermohonanKertasKandungan peruntukanKewangan) {
        this.peruntukanKewangan = peruntukanKewangan;
    }

    public List<PermohonanKertasKandungan> getSenaraiKertasKandungan1() {
        return senaraiKertasKandungan1;
    }

    public void setSenaraiKertasKandungan1(List<PermohonanKertasKandungan> senaraiKertasKandungan1) {
        this.senaraiKertasKandungan1 = senaraiKertasKandungan1;
    }

    public PermohonanKertasKandungan getUlasanPengarah() {
        return ulasanPengarah;
    }

    public void setUlasanPengarah(PermohonanKertasKandungan ulasanPengarah) {
        this.ulasanPengarah = ulasanPengarah;
    }

    public PermohonanKertasKandungan getUlasanPentadbir() {
        return ulasanPentadbir;
    }

    public void setUlasanPentadbir(PermohonanKertasKandungan ulasanPentadbir) {
        this.ulasanPentadbir = ulasanPentadbir;
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
}