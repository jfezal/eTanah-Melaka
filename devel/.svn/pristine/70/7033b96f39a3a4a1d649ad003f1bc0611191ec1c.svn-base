/** Document   : DraftJKBBActionBean_O.java
    Modified on : 30 August 2012
    Author     : Mohd Rasidee / Shyazli Orogenic
*/

package etanah.view.stripes.pelupusan;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.dao.*;
import etanah.model.*;
import etanah.service.PelupusanPtService;
import etanah.service.PelupusanService;
import etanah.view.etanahActionBeanContext;
import etanah.view.stripes.pelupusan.disClass.DisLaporanTanahSempadan;
import etanah.view.stripes.pelupusan.disClass.DisLaporanTanahService;
import etanah.view.stripes.pelupusan.disClass.LotSempadan;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;
import org.apache.log4j.Logger;

@UrlBinding("/pelupusan/draf_jkbb_O")
public class DrafJKBBActionBean_O extends AbleActionBean {

    @Inject
    PermohonanDAO permohonanDAO;

    @Inject
    PermohonanKertasKandunganDAO permohonanKertasKandDAO;

    @Inject
    PelupusanPtService pelPtService;

    @Inject
    KodCawanganDAO kodCawanganDAO;

    @Inject
    KodDokumenDAO kodDokumenDAO;

    @Inject
    HakmilikPermohonanDAO hakmilikPermohonanDAO;

    @Inject
    PelupusanService pelupusanService;

    @Inject
    DisLaporanTanahService disLaporanTanahService;

    private String namaSyarikat;

    private String lot;

    private String mukim;

    private String luas;

    private String tujuan;

    private String jenisTanah;

    private String majlisPerbandaran;

    private String terletak;

    private String keadaanTanah;

    private String utara;

    private String selatan;

    private String timur;

    private String barat;

    private String perancanganKerajaan;

    private Permohonan permohonan;

    private List<PermohonanLaporanKawasan> senaraiLaporanKawasan;

    private Pemohon pemohon;

    private String idPermohonan;

    private PermohonanKertasKandungan kertasK;

    private Pengguna pguna;

    private String idPihak;

    private String projekKerajaan;

    private LaporanTanah laporanTanah;

    private HakmilikPermohonan hakmilikPermohonan;

    private List<HakmilikPermohonan> senaraiHakmilikPermohonan;

    private PermohonanLaporanPelan permohonanLaporanPelan;

    private List<Pemohon> pemohonList;

    private List<PermohonanKertasKandungan> senaraiLaporanKandunganptg2;

    private List<PermohonanKertasKandungan> senaraiLaporanKandunganptg1;

    private PermohonanKertas permohonankertas;

    etanahActionBeanContext ctx;

    private static final Logger log = Logger.getLogger(DrafJKBBActionBean_O.class);

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {
        pguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        idPihak = (String) getContext().getRequest().getSession().getAttribute("idPihak");
        log.info("-----ID Pengguna------" + pguna.getIdPengguna());
        log.info("-----ID Permohonan------" + idPermohonan);
        log.info("-----ID Pihak------" + idPihak);


        pemohonList = pelupusanService.findPemohonByIdPermohonan(idPermohonan);
        log.info("-----Pemohon List Size------" + pemohonList.size());
        if (!pemohonList.isEmpty()) {
            pemohon = pemohonList.get(0);
            namaSyarikat = pemohon.getPihak().getNama();
        }
        this.setNamaSyarikat(namaSyarikat);
        log.info("-----Nama Syarikat------" + namaSyarikat);

        hakmilikPermohonan = pelupusanService.findByIdPermohonan(idPermohonan);
        if (hakmilikPermohonan != null) {
            this.setLot(hakmilikPermohonan.getNoLot());
            this.setMukim(hakmilikPermohonan.getBandarPekanMukimBaru().getNama());
            this.setLuas(hakmilikPermohonan.getLuasTerlibat().toString());
            log.info("-----Lot------" + hakmilikPermohonan.getNoLot());
            log.info("-----Mukim------" + hakmilikPermohonan.getBandarPekanMukimBaru().getNama());
            log.info("-----Luas------" + hakmilikPermohonan.getLuasTerlibat().toString());
        }
        permohonan = pelupusanService.findById(idPermohonan);

        if (permohonan != null) {
            this.setTujuan(permohonan.getSebab());
            log.info("-----Tujuan------" + permohonan.getSebab());
        }


        //Laporan Kawasan Report


        senaraiLaporanKawasan = new ArrayList<PermohonanLaporanKawasan>();
        if (hakmilikPermohonan != null) {
            senaraiLaporanKawasan = disLaporanTanahService.getPlpservice().findLaporanKawasanByIdPermohonanNidMH(idPermohonan, hakmilikPermohonan.getId());
        } else {
            senaraiLaporanKawasan = disLaporanTanahService.getPlpservice().findLaporanKawasanByIdPermohonanNKodRizab(idPermohonan);
        }






        senaraiHakmilikPermohonan = new ArrayList<HakmilikPermohonan>();
        senaraiHakmilikPermohonan = permohonan.getSenaraiHakmilik();
        log.info("-----senaraiHakmilikPermohonan.size()------" + senaraiHakmilikPermohonan.size());

        permohonanLaporanPelan = new PermohonanLaporanPelan();

        permohonanLaporanPelan = (PermohonanLaporanPelan) disLaporanTanahService.findObject(permohonanLaporanPelan,
                new String[]{idPermohonan}, 0);


        log.info("-----permohonanLaporanPelan.kodTanah.nama------" + permohonanLaporanPelan.getKodTanah().getNama());

        this.setJenisTanah(permohonanLaporanPelan.getKodTanah().getNama());

        projekKerajaan = permohonanLaporanPelan.getProjekKerajaan();

        log.info("-----projekKerajaan------" + projekKerajaan);

        /*
         * LAPORAN TANAH
         */
        if (hakmilikPermohonan != null) {
            laporanTanah = new LaporanTanah();
            laporanTanah = (LaporanTanah) disLaporanTanahService.findObject(laporanTanah, new String[]{String.valueOf(hakmilikPermohonan.getId())}, 0);

            log.info("hakmilikPermohonan != null-");
        } else {
            laporanTanah = new LaporanTanah();
            laporanTanah = (LaporanTanah) disLaporanTanahService.findObject(laporanTanah, new String[]{idPermohonan}, 1);
            log.info("hakmilikPermohonan = null");
        }



        disLaporanTanahSempadan = new DisLaporanTanahSempadan();
        disLaporanTanahSempadan.setListLaporTanahSpdnU(new ArrayList());
        disLaporanTanahSempadan.setListLaporTanahSpdnB(new ArrayList());
        disLaporanTanahSempadan.setListLaporTanahSpdnS(new ArrayList());
        disLaporanTanahSempadan.setListLaporTanahSpdnT(new ArrayList());



        log.info("laporanTanah.getIdLaporan()" + laporanTanah.getIdLaporan());

        // buat untuk utara
        List<LaporanTanahSempadan> listLaporTanahSpdnTemp = new ArrayList<LaporanTanahSempadan>();
        listLaporTanahSpdnTemp = disLaporanTanahService.getPlpservice().findLaporTanahSmpdnByIdLaporNJSmpdn(laporanTanah.getIdLaporan(), "U"); // UTARA
        log.info("size ialah " + listLaporTanahSpdnTemp.size());

        for (LaporanTanahSempadan lts : listLaporTanahSpdnTemp) {
            LotSempadan ls = new LotSempadan();
            ls.setLaporanTanahSmpdn(lts);
            ls.setListImejLaporan(disLaporanTanahService.getLaporanTanahService().getLaporImejByPndgnImejNlaporTnhSmpdn(laporanTanah, 'U', lts));
            disLaporanTanahSempadan.getListLaporTanahSpdnU().add(ls);
        }


        disLaporanTanahSempadan.setuSize(listLaporTanahSpdnTemp.size());
        listLaporTanahSpdnTemp = new ArrayList<LaporanTanahSempadan>();
        listLaporTanahSpdnTemp = disLaporanTanahService.getPlpservice().findLaporTanahSmpdnByIdLaporNJSmpdn(laporanTanah.getIdLaporan(), "S"); // SELATAN
        log.info("size  selatan ialah " + listLaporTanahSpdnTemp.size());
        for (LaporanTanahSempadan lts : listLaporTanahSpdnTemp) {
            log.info("lts.getKeadaanTanah() " + lts.getKeadaanTanah());
            log.info("lts.getGuna() " + lts.getGuna());

            LotSempadan ls = new LotSempadan();
            ls.setLaporanTanahSmpdn(lts);
            ls.setListImejLaporan(disLaporanTanahService.getLaporanTanahService().getLaporImejByPndgnImejNlaporTnhSmpdn(laporanTanah, 'S', lts));
            disLaporanTanahSempadan.getListLaporTanahSpdnS().add(ls);
        }

        disLaporanTanahSempadan.setsSize(listLaporTanahSpdnTemp.size());
        listLaporTanahSpdnTemp = new ArrayList<LaporanTanahSempadan>();
        listLaporTanahSpdnTemp = disLaporanTanahService.getPlpservice().findLaporTanahSmpdnByIdLaporNJSmpdn(laporanTanah.getIdLaporan(), "T"); // TIMUR
        for (LaporanTanahSempadan lts : listLaporTanahSpdnTemp) {
            LotSempadan ls = new LotSempadan();
            ls.setLaporanTanahSmpdn(lts);
            ls.setListImejLaporan(disLaporanTanahService.getLaporanTanahService().getLaporImejByPndgnImejNlaporTnhSmpdn(laporanTanah, 'T', lts));
            disLaporanTanahSempadan.getListLaporTanahSpdnT().add(ls);
        }
        disLaporanTanahSempadan.settSize(listLaporTanahSpdnTemp.size());
        listLaporTanahSpdnTemp = new ArrayList<LaporanTanahSempadan>();
        listLaporTanahSpdnTemp = disLaporanTanahService.getPlpservice().findLaporTanahSmpdnByIdLaporNJSmpdn(laporanTanah.getIdLaporan(), "B"); // BARAT
        for (LaporanTanahSempadan lts : listLaporTanahSpdnTemp) {
            LotSempadan ls = new LotSempadan();
            ls.setLaporanTanahSmpdn(lts);
            ls.setListImejLaporan(disLaporanTanahService.getLaporanTanahService().getLaporImejByPndgnImejNlaporTnhSmpdn(laporanTanah, 'B', lts));
            disLaporanTanahSempadan.getListLaporTanahSpdnB().add(ls);
        }
        disLaporanTanahSempadan.setbSize(listLaporTanahSpdnTemp.size());
        /*
         * END
         */


        //idPermohonan="0505DIS2011007263";

        permohonankertas = new PermohonanKertas();

        //begin of hardcode
        //permohonankertas = pelupusanService.findKertasByKod(idPermohonan, "RMN");
        //end

        log.info("--------------------------------------------------------------------------");
        log.info("id permohonan is " + idPermohonan);

        permohonankertas = pelupusanService.findKertasByKod(idPermohonan, "KPTD");

        List<PermohonanKertasKandungan> mohonKertasKand = new ArrayList<PermohonanKertasKandungan>();

        if (permohonankertas != null) {

            log.info("permohonan kertas  is not null " + idPermohonan);
            log.info("id permohonan kertas  is  " + permohonankertas.getIdKertas());
            mohonKertasKand = pelupusanService.findByIdKertasOnly(permohonankertas.getIdKertas());
            for (int j = 0; j < mohonKertasKand.size(); j++) {
//                kertasK = new PermohonanKertasKandungan();
                kertasK = mohonKertasKand.get(j);

                log.info(kertasK.getSubtajuk() + " " + kertasK.getKandungan());
            }
        }

        if (permohonankertas != null) {
            senaraiLaporanKandunganptg1 = pelupusanService.findByIdLapor(permohonankertas.getIdKertas(), 3);

            senaraiLaporanKandunganptg2 = pelupusanService.findByIdLapor(permohonankertas.getIdKertas(), 4);

        } else {

            log.info("permohonan kertas  is  null ");
            senaraiLaporanKandunganptg1 = new Vector();
            senaraiLaporanKandunganptg2 = new Vector();


        }





    }

    public Resolution deleteRow() throws ParseException {


        System.out.println("-----------deleteKandungan---------");
        String idKand = getContext().getRequest().getParameter("idKandungan");
        System.out.println("-----------deleteSingle---------" + idKand);
        if (idKand != null) {
            PermohonanKertasKandungan plk = new PermohonanKertasKandungan();
            plk = permohonanKertasKandDAO.findById(Long.parseLong(idKand));
            if (plk != null) {

                try {
                    pelPtService.deleteKertasKandungan(plk);
                } catch (Exception e) {
                }
            }
        }
        rehydrate();
//        getContext().getRequest().setAttribute("edit", edit);
//        getContext().getRequest().setAttribute("openPTG", openPTG);
//        getContext().getRequest().setAttribute("editPTG", editPTG);
//        getContext().getRequest().setAttribute("editPTD", editPTD);
        return new JSP("/pelupusan/draf_pertimbangan_ptd_O.jsp").addParameter("tab", "true");
    }

    public Resolution tambahRow() {

        PermohonanKertasKandungan pkk = new PermohonanKertasKandungan();
        int index = 0;
        index = Integer.parseInt(getContext().getRequest().getParameter("index"));

        log.info("index is " + index);

        switch (index) {
            case 1:
                break;
            case 2:
                pkk = new PermohonanKertasKandungan();
                pkk.setBil((short) 2);
//                senaraiLaporanKandungan1.add(pkk);
                break;
            case 3:
                pkk = new PermohonanKertasKandungan();
                pkk.setBil((short) 3);
                senaraiLaporanKandunganptg1.add(pkk);
                break;
            case 4:
                pkk = new PermohonanKertasKandungan();
                pkk.setBil((short) 4);
                senaraiLaporanKandunganptg2.add(pkk);
                break;
//            case 5:
//                pkk = new PermohonanKertasKandungan();
//                pkk.setBil((short) 5);
//                listKertasPerakuanPTG.add(pkk);
//                break;
//            case 6:
//                pkk = new PermohonanKertasKandungan();
//                pkk.setBil((short) 6);
//                listKertasKeputusanPTG.add(pkk);
//                break;
//            case 7: // FOR PERAKUAN PENTADBIR TANAH DAERAH MELAKA TENGAH
//                pkk = new PermohonanKertasKandungan();
//                pkk.setBil((short) 7);
////                senaraiLaporanKandunganpbtanah.add(pkk);
//                break;
//            case 8: // FOR HURAIAN PENGARAH TANAH DAN GALIAN
//                pkk = new PermohonanKertasKandungan();
//                pkk.setBil((short) 8);
//                senaraiLaporanKandunganptg1.add(pkk);
//                break;
//            case 9: // FOR SYOR PENGARAH TANAH DAN GALIAN
//                pkk = new PermohonanKertasKandungan();
//                pkk.setBil((short) 9);
////                senaraiLaporanKandunganptg2.add(pkk);
//                break;
            default:
        }
        System.out.println(index);
//        getContext().getRequest().setAttribute("edit", edit);
//        getContext().getRequest().setAttribute("openPTG", openPTG);
//        getContext().getRequest().setAttribute("editPTG", editPTG);
//        getContext().getRequest().setAttribute("editPTD", editPTD);
        return new JSP("/pelupusan/draf_pertimbangan_ptd_O.jsp").addParameter("tab", "true");
    }

    public void updateKandungan(int i, String kand) {

        log.info("--------------------------------------------------------------");
        log.info("dalam updateKandungan");

        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        int idKandungan = Integer.parseInt(getContext().getRequest().getParameter("idKandungan"));
        log.info("idPermohonan = " + idPermohonan);

        Permohonan permohonan = permohonanDAO.findById(idPermohonan);


        Pengguna pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        log.info("pengguna = " + pengguna.getIdPengguna());

        InfoAudit infoAudit = new InfoAudit();
        KodCawangan cawangan = new KodCawangan();
        cawangan = kodCawanganDAO.findById(pengguna.getKodCawangan().getKod());

        log.info("pengguna = " + pengguna.getKodCawangan().getKod());

        if (permohonankertas != null) {

            log.info("permohonankertas  bukanlah null");
            infoAudit = permohonankertas.getInfoAudit();
            infoAudit.setDiKemaskiniOleh(pengguna);
            infoAudit.setTarikhKemaskini(new java.util.Date());
        } else {
            log.info("permohonankertas  adalah null");
            permohonankertas = new PermohonanKertas();
            infoAudit.setDimasukOleh(pengguna);
            infoAudit.setTarikhMasuk(new java.util.Date());

        }

        permohonankertas.setTajuk("DRAF PERTIMBANGAN PTD");
        KodDokumen kod = kodDokumenDAO.findById("KPTD");
        permohonankertas.setKodDokumen(kod);
        permohonankertas.setCawangan(cawangan);
        permohonankertas.setInfoAudit(infoAudit);
        permohonankertas.setPermohonan(permohonan);
        pelPtService.simpanPermohonanKertas(permohonankertas);

        log.info("simpan permohonankertas berjaya");

        long a = permohonankertas.getIdKertas();

        log.info("id kertas kandungan ialah " + a);

        List<PermohonanKertasKandungan> plk = pelPtService.findByIdLapor(a, i);

        log.debug("Saiz kandungan :" + plk.size());

        if (idKandungan == 0 && !kand.trim().isEmpty()) {
            PermohonanKertasKandungan pLK = new PermohonanKertasKandungan();
            log.info("index :" + i + " kand :" + kand + " id_lapor :" + a);



            if (plk.isEmpty()) {
                pLK.setSubtajuk("1");
                log.info("PLK" + pLK.getSubtajuk());
            } else {
                int n = Integer.parseInt(plk.get(plk.size() - 1).getSubtajuk()) + 1;
                //    int test = Integer.parseInt(plk.get(plk.size() - 1).getSubtajuk().substring(2, 3)) + 1;

                pLK.setSubtajuk(String.valueOf(n));
            }
            pLK.setBil((short) i);
            pLK.setKandungan(kand);
            pLK.setKertas(permohonankertas);
            pLK.setInfoAudit(infoAudit);
            pLK.setCawangan(cawangan);
            pelPtService.simpanPermohonanKertasKandungan(pLK);
        }
        log.debug("End update kandungan");
    }

    public Resolution simpanKandungan() throws ParseException {


        int index = 0;
        index = Integer.parseInt(getContext().getRequest().getParameter("index"));
        String kand = getContext().getRequest().getParameter("kandungan");
        log.info("CHECKING:..... index :" + index + " kand :" + kand);
        switch (index) {
            case 1:

                break;
            case 2: //FOR Perihal Permohonan
                updateKandungan(2, kand);

                break;
            case 3:

                updateKandungan(3, kand);

                break;
            case 4:

                updateKandungan(4, kand);

                break;
//            case 5:
//
//                updateKandungan(5, kand);
//
//                break;
//            case 6:
//
//                updateKandungan(6, kand);
//                break;
            case 7: // FOR PERAKUAN PENTADBIR TANAH DAERAH MELAKA TENGAH
                updateKandungan(7, kand);
                break;
            case 8: // FOR HURAIAN PENGARAH TANAH DAN GALIAN
                updateKandungan(8, kand);
                break;
            case 9: // FOR SYOR PENGARAH TANAH DAN GALIAN
                updateKandungan(9, kand);
                break;
//            default:
//                LOG.info("alamak!! tiada index");
        }
        rehydrate();
//        getContext().getRequest().setAttribute("edit", edit);
//        getContext().getRequest().setAttribute("openPTG", openPTG);
//        getContext().getRequest().setAttribute("editPTG", editPTG);
//        getContext().getRequest().setAttribute("editPTD", editPTD);
        addSimpleMessage("Maklumat Berjaya Disimpan");
        return new JSP("/pelupusan/draf_pertimbangan_ptd_O.jsp").addParameter("tab", "true");
    }

    public Resolution simpan() {

        log.info("masuk dalam simpan");
        return new JSP("/pelupusan/draf_pertimbangan_ptd_O.jsp").addParameter("tab", "true");
    }

    @DefaultHandler
    public Resolution view() {
        return new JSP("/pelupusan/draf_pertimbangan_ptd_O.jsp").addParameter("tab", "true");
    }

    public String getPerancanganKerajaan() {
        return perancanganKerajaan;
    }

    public void setPerancanganKerajaan(String perancanganKerajaan) {
        this.perancanganKerajaan = perancanganKerajaan;
    }

    public String getMajlisPerbandaran() {
        return majlisPerbandaran;
    }

    public void setMajlisPerbandaran(String majlisPerbandaran) {
        this.majlisPerbandaran = majlisPerbandaran;
    }

    public String getJenisTanah() {
        return jenisTanah;
    }

    public void setJenisTanah(String jenisTanah) {
        this.jenisTanah = jenisTanah;
    }

    public String getNamaSyarikat() {
        return namaSyarikat;
    }

    public void setNamaSyarikat(String namaSyarikat) {
        this.namaSyarikat = namaSyarikat;
    }

    public String getLot() {
        return lot;
    }

    public void setLot(String lot) {
        this.lot = lot;
    }

    public String getMukim() {
        return mukim;
    }

    public void setMukim(String mukim) {
        this.mukim = mukim;
    }

    public String getLuas() {
        return luas;
    }

    public void setLuas(String luas) {
        this.luas = luas;
    }

    public String getTujuan() {
        return tujuan;
    }

    public void setTujuan(String tujuan) {
        this.tujuan = tujuan;
    }

    public String getTerletak() {
        return terletak;
    }

    public void setTerletak(String terletak) {
        this.terletak = terletak;
    }

    public String getKeadaanTanah() {
        return keadaanTanah;
    }

    public void setKeadaanTanah(String keadaanTanah) {
        this.keadaanTanah = keadaanTanah;
    }

    public String getUtara() {
        return utara;
    }

    public void setUtara(String utara) {
        this.utara = utara;
    }

    public String getSelatan() {
        return selatan;
    }

    public void setSelatan(String selatan) {
        this.selatan = selatan;
    }

    public String getTimur() {
        return timur;
    }

    public void setTimur(String timur) {
        this.timur = timur;
    }

    public String getBarat() {
        return barat;
    }

    public void setBarat(String barat) {
        this.barat = barat;
    }

    public String getProjekKerajaan() {
        return projekKerajaan;
    }

    public void setProjekKerajaan(String projekKerajaan) {
        this.projekKerajaan = projekKerajaan;
    }

    public DisLaporanTanahSempadan getDisLaporanTanahSempadan() {
        return disLaporanTanahSempadan;
    }

    public void setDisLaporanTanahSempadan(DisLaporanTanahSempadan disLaporanTanahSempadan) {
        this.disLaporanTanahSempadan = disLaporanTanahSempadan;
    }
    private DisLaporanTanahSempadan disLaporanTanahSempadan;

    public LaporanTanah getLaporanTanah() {
        return laporanTanah;
    }

    public void setLaporanTanah(LaporanTanah laporanTanah) {
        this.laporanTanah = laporanTanah;
    }

    public HakmilikPermohonan getHakmilikPermohonan() {
        return hakmilikPermohonan;
    }

    public void setHakmilikPermohonan(HakmilikPermohonan hakmilikPermohonan) {
        this.hakmilikPermohonan = hakmilikPermohonan;
    }

    public PermohonanLaporanPelan getPermohonanLaporanPelan() {
        return permohonanLaporanPelan;
    }

    public void setPermohonanLaporanPelan(PermohonanLaporanPelan permohonanLaporanPelan) {
        this.permohonanLaporanPelan = permohonanLaporanPelan;
    }

    public List<PermohonanKertasKandungan> getSenaraiLaporanKandunganptg2() {
        return senaraiLaporanKandunganptg2;
    }

    public void setSenaraiLaporanKandunganptg2(List<PermohonanKertasKandungan> senaraiLaporanKandunganptg2) {
        this.senaraiLaporanKandunganptg2 = senaraiLaporanKandunganptg2;
    }

    public List<PermohonanKertasKandungan> getSenaraiLaporanKandunganptg1() {
        return senaraiLaporanKandunganptg1;
    }

    public void setSenaraiLaporanKandunganptg1(List<PermohonanKertasKandungan> senaraiLaporanKandunganptg1) {
        this.senaraiLaporanKandunganptg1 = senaraiLaporanKandunganptg1;
    }

    public PermohonanKertas getPermohonankertas() {
        return permohonankertas;
    }

    public void setPermohonankertas(PermohonanKertas permohonankertas) {
        this.permohonankertas = permohonankertas;
    }

    public PermohonanKertasKandungan getKertasK() {
        return kertasK;
    }

    public void setKertasK(PermohonanKertasKandungan kertasK) {
        this.kertasK = kertasK;
    }

    public Permohonan getPermohonan() {
        return permohonan;
    }

    public void setPermohonan(Permohonan permohonan) {
        this.permohonan = permohonan;
    }

    public List<PermohonanLaporanKawasan> getSenaraiLaporanKawasan() {
        return senaraiLaporanKawasan;
    }

    public void setSenaraiLaporanKawasan(List<PermohonanLaporanKawasan> senaraiLaporanKawasan) {
        this.senaraiLaporanKawasan = senaraiLaporanKawasan;
    }
}
