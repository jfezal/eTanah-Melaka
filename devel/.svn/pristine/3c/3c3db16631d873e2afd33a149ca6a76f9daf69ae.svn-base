/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.stripes.pengambilan;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.dao.PermohonanDAO;
import etanah.dao.PermohonanRujukanLuarDAO;
import etanah.model.InfoAudit;
import etanah.model.KodKeputusan;
import etanah.model.KodRujukan;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.model.PermohonanKertas;
import etanah.model.PermohonanKertasKandungan;
import etanah.model.PermohonanRujukanLuar;
import etanah.service.PendudukanSementaraMMKNService;
import etanah.service.PengambilanMMKService;
import etanah.view.etanahActionBeanContext;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;

/**
 *
 * @author nordiyana
 */
@UrlBinding("/pengambilan/rekodKeputusanMMKN")
public class rekodKeputusanMMKn9ActionBean extends AbleActionBean {

    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    PermohonanRujukanLuarDAO permohonanRujLuarDAO;
    @Inject
    PengambilanMMKService service;
    @Inject
    PendudukanSementaraMMKNService pendudukanSementaraMMKNService;
    private Permohonan permohonan;
    private PermohonanRujukanLuar permohonanRujukanLuar;
    private KodRujukan kodRujukan;
    private int count = 0;
    private List senaraiSyor[] = new ArrayList[5];
    String str[] = {"", "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z"};
    String tarikhMesyuarat;
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    String keputusan;
    String keputusanMMKN;
    String memo;
    String notis;
    String jam;
    String minit;
    String saat;
    String ampm;
    String ampmDisplay;
    String keputusanDisplay;
    String tarikhKhas;

    @DefaultHandler
    public Resolution showForm() {
        getContext().getRequest().setAttribute("form1", Boolean.TRUE);
        getContext().getRequest().setAttribute("bilMesyuarat", Boolean.TRUE);
        getContext().getRequest().setAttribute("tarikhMasa", Boolean.TRUE);
        getContext().getRequest().setAttribute("keputusan", Boolean.TRUE);
        getContext().getRequest().setAttribute("simpanMesyuarat", Boolean.TRUE);
        return new JSP("pengambilan/negerisembilan/rekodKeputusanMMKn9.jsp").addParameter("tab", "true");
    }

    public Resolution showForm2() {
        getContext().getRequest().setAttribute("view", Boolean.TRUE);
        getContext().getRequest().setAttribute("form1", Boolean.FALSE);
        getContext().getRequest().setAttribute("bilMesyuarat", Boolean.FALSE);
        getContext().getRequest().setAttribute("tarikhMasa", Boolean.FALSE);
        getContext().getRequest().setAttribute("keputusan", Boolean.TRUE);
        return new JSP("pengambilan/negerisembilan/rekodKeputusanMMKn9.jsp").addParameter("tab", "true");
    }

    public Resolution showForm3() {
        getContext().getRequest().setAttribute("form1", Boolean.TRUE);
        getContext().getRequest().setAttribute("bilMesyuarat", Boolean.TRUE);
        getContext().getRequest().setAttribute("tarikhMasa", Boolean.TRUE);
        getContext().getRequest().setAttribute("keputusan", Boolean.TRUE);
        getContext().getRequest().setAttribute("simpanMesyuarat", Boolean.TRUE);
        return new JSP("pengambilan/negerisembilan/rekodKeputusanRunding.jsp").addParameter("tab", "true");
    }

    public Resolution showForm4() {
        getContext().getRequest().setAttribute("view", Boolean.TRUE);
        getContext().getRequest().setAttribute("form1", Boolean.FALSE);
        getContext().getRequest().setAttribute("bilMesyuarat", Boolean.FALSE);
        getContext().getRequest().setAttribute("tarikhMasa", Boolean.FALSE);
        getContext().getRequest().setAttribute("keputusan", Boolean.TRUE);
        return new JSP("pengambilan/negerisembilan/rekodKeputusanRunding.jsp").addParameter("tab", "true");
    }

    public Resolution showForm5() {
        System.out.println("memo");
        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        System.out.println("id permohonan " + idPermohonan);
        if (idPermohonan != null) {
            permohonan = permohonanDAO.findById(idPermohonan);
            memo = permohonan.getUlasan();
        }
        getContext().getRequest().setAttribute("view", Boolean.TRUE);
        getContext().getRequest().setAttribute("form1", Boolean.FALSE);
        getContext().getRequest().setAttribute("bilMesyuarat", Boolean.FALSE);
        getContext().getRequest().setAttribute("tarikhMasa", Boolean.FALSE);
        getContext().getRequest().setAttribute("keputusan", Boolean.TRUE);
        return new JSP("pengambilan/negerisembilan/memoRunding.jsp").addParameter("tab", "true");
    }

    public Resolution showForm6() {
        System.out.println("notis");
        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        System.out.println("id permohonan " + idPermohonan);
        if (idPermohonan != null) {
            permohonan = permohonanDAO.findById(idPermohonan);
            notis = permohonan.getCatatan();
        }
        getContext().getRequest().setAttribute("view", Boolean.TRUE);
        getContext().getRequest().setAttribute("form1", Boolean.FALSE);
        getContext().getRequest().setAttribute("bilMesyuarat", Boolean.FALSE);
        getContext().getRequest().setAttribute("tarikhMasa", Boolean.FALSE);
        getContext().getRequest().setAttribute("keputusan", Boolean.TRUE);
        return new JSP("pengambilan/negerisembilan/notis_kelulusan.jsp").addParameter("tab", "true");
    }

    public Resolution showForm7() {
        getContext().getRequest().setAttribute("form1", Boolean.TRUE);
        getContext().getRequest().setAttribute("bilMesyuarat", Boolean.TRUE);
        getContext().getRequest().setAttribute("tarikhMasa", Boolean.TRUE);
        getContext().getRequest().setAttribute("keputusan", Boolean.TRUE);
        getContext().getRequest().setAttribute("simpanMesyuarat", Boolean.TRUE);
        return new JSP("pengambilan/negerisembilan/rekodKeputusanKhas.jsp").addParameter("tab", "true");
    }

    public Resolution showForm8() {
        getContext().getRequest().setAttribute("view", Boolean.TRUE);
        getContext().getRequest().setAttribute("form1", Boolean.FALSE);
        getContext().getRequest().setAttribute("bilMesyuarat", Boolean.FALSE);
        getContext().getRequest().setAttribute("tarikhMasa", Boolean.FALSE);
        getContext().getRequest().setAttribute("keputusan", Boolean.TRUE);
        return new JSP("pengambilan/negerisembilan/rekodKeputusanKhas.jsp").addParameter("tab", "true");
    }

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {
        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        if (idPermohonan != null) {
            permohonan = permohonanDAO.findById(idPermohonan);

            String[] tname = {"permohonan"};
            Object[] model = {permohonan};

            List<PermohonanRujukanLuar> listRujLuar;

            listRujLuar = permohonanRujLuarDAO.findByEqualCriterias(tname, model, null);

            if (permohonan != null) {
                permohonan = permohonanDAO.findById(idPermohonan);
                if (permohonan.getKeputusan() != null) {
                    keputusan = permohonan.getKeputusan().getKod();

                    if (permohonan.getRujukanUndang2() == null) {
                        keputusanDisplay = permohonan.getKeputusan().getNama();
                        keputusanDisplay = "Setuju";

                    } else if (permohonan.getRujukanUndang2().equals("L")) {
                        keputusanDisplay = permohonan.getKeputusan().getNama();
                        keputusanDisplay = "Setuju";

                    } else if (permohonan.getRujukanUndang2().equals("TL")) {
                        keputusanDisplay = permohonan.getKeputusan().getNama();
                        keputusanDisplay = "Tidak Setuju";

                    }
                    if (permohonan.getKeputusan().getKod().equals("L")) {
                        keputusanMMKN = "L";
                    }
                    if (permohonan.getKeputusan().getKod().equals("T")) {
                        keputusanMMKN = "T";
                    }
                } else if (permohonan.getRujukanUndang2() != null) {
                    if (permohonan.getRujukanUndang2().equals("L")) {
//                        keputusanDisplay = permohonan.getKeputusan().getNama();
                        keputusanDisplay = "Setuju";

                    } else if (permohonan.getRujukanUndang2().equals("TL")) {
//                        keputusanDisplay = permohonan.getKeputusan().getNama();
                        keputusanDisplay = "Tidak Setuju";

                    }
                }
                try {
                    if (permohonan.getNoMahkamah().equalsIgnoreCase("L")) {
                        keputusanDisplay = "Lulus";
                        keputusan = "L";
//                        keputusanMMKN = "L";
                    } else if (permohonan.getNoMahkamah().equalsIgnoreCase("TL")) {
                        keputusanDisplay = "Tolak";
                        keputusan = "TL";
//                        keputusanMMKN = "TL";

                    }
                } catch (Exception j) {
                }

                getContext().getRequest().setAttribute("form1", Boolean.TRUE);
                getContext().getRequest().setAttribute("bilMesyuarat", Boolean.TRUE);
                getContext().getRequest().setAttribute("tarikhMasa", Boolean.TRUE);
                getContext().getRequest().setAttribute("keputusan", Boolean.TRUE);
                getContext().getRequest().setAttribute("tarikhKhas", Boolean.TRUE);
                getContext().getRequest().setAttribute("simpanMesyuarat", Boolean.TRUE);

            }

            String tajuk = "";
            String tajukMMKN = "";
            if (permohonan.getKodUrusan().getKod().equalsIgnoreCase("831B")) {
                tajuk = "KERTAS PERMOHONAN PENGAMBILAN TANAH SEKSYEN 3(1)(b) - Jawatankuasa Khas";
                tajukMMKN = "KERTAS PERMOHONAN PENGAMBILAN TANAH SEKSYEN 3(1)(b)";
            } else if (permohonan.getKodUrusan().getKod().equalsIgnoreCase("831C")) {
                tajuk = "KERTAS PERMOHONAN PENGAMBILAN TANAH SEKSYEN 3(1)(c) - Jawatankuasa Khas";
                tajukMMKN = "KERTAS PERMOHONAN PENGAMBILAN TANAH SEKSYEN 3(1)(c)";
            }
            PermohonanKertas permohonanKertas = null;
            PermohonanKertas permohonanKertasKhas = null;
            if (permohonan.getKeputusan() == null && permohonan.getNoMahkamah() != null) {
                permohonanKertas = pendudukanSementaraMMKNService.findMMKNSByIdPermohonan(idPermohonan);
            } else {
                permohonanKertas = pendudukanSementaraMMKNService.findMMKNByIdPermohonan(idPermohonan);
            }
            if (permohonanKertas != null) {
                List<PermohonanKertasKandungan> kandList = new ArrayList<PermohonanKertasKandungan>();
                PermohonanKertasKandungan maxKertasKand = new PermohonanKertasKandungan();
                permohonanKertasKhas = pendudukanSementaraMMKNService.findMMKNByKodIdPermohonan(idPermohonan, tajuk);
                permohonanKertas = pendudukanSementaraMMKNService.findMMKNByKodIdPermohonan(idPermohonan, tajukMMKN);
                tarikhKhas = sdf.format(permohonanKertasKhas.getTarikhSidang());
                try {
                    tarikhMesyuarat = sdf.format(permohonanKertas.getTarikhSidang());
                } catch (Exception hj) {
                }
                try {
                    if (permohonan.getKeputusan() == null && permohonan.getNoMahkamah() != null) {
                        kandList = pendudukanSementaraMMKNService.findByKertasDesc(permohonanKertas.getIdKertas(), 5);
                    } else {
                        kandList = pendudukanSementaraMMKNService.findByKertasDesc(permohonanKertas.getIdKertas(), 6);
                    }
                } catch (Exception v) {
                }
                if (kandList != null && !kandList.isEmpty()) {
                    maxKertasKand = kandList.get(0);
                    if (maxKertasKand != null) {
                        String subtajuk = maxKertasKand.getSubtajuk();
                        String str = subtajuk.substring(2, 3);
                        int tableCount = Integer.parseInt(str);
                        count = tableCount;
                        for (int k = 1; k <= tableCount; k++) {
                            senaraiSyor[k] = new ArrayList<PermohonanKertasKandungan>();
                            String subtajuk1 = "6." + k;
                            senaraiSyor[k] = pendudukanSementaraMMKNService.findByKertasSubTajuk(permohonanKertas.getIdKertas(), 6, subtajuk1);
                            if (permohonan.getKeputusan() == null && permohonan.getNoMahkamah() != null) {
                                subtajuk1 = "5." + k;
                                senaraiSyor[k] = pendudukanSementaraMMKNService.findByKertasSubTajuk(permohonanKertas.getIdKertas(), 5, subtajuk1);
                            } else {
                                subtajuk1 = "6." + k;
                                senaraiSyor[k] = pendudukanSementaraMMKNService.findByKertasSubTajuk(permohonanKertas.getIdKertas(), 6, subtajuk1);
                            }
                            System.out.println(senaraiSyor[k]);
                        }
                    }
                }//if
            }//if

        }
    }

    public Resolution simpanMesyuarat() {
        if (keputusanMMKN == null) {
            addSimpleError("Sila Masukkan Keputusan.");
        } else {

            Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);

            KodKeputusan kodKeputusan = new KodKeputusan();
            KodRujukan kod = new KodRujukan();
            kod.setKod("NF");
            kodKeputusan.setKod(keputusanMMKN);
            permohonan.setKeputusan(kodKeputusan);
            permohonan.setKeputusanOleh(peng);

            InfoAudit infoAudit = new InfoAudit();
            infoAudit.setDimasukOleh(peng);
            infoAudit.setTarikhMasuk(new java.util.Date());
            service.simpanPermohonan(permohonan);

            addSimpleMessage("Maklumat telah berjaya disimpan.");

            getContext().getRequest().setAttribute("form1", Boolean.TRUE);
//            getContext().getRequest().setAttribute("bilMesyuarat", Boolean.TRUE);
//            getContext().getRequest().setAttribute("tarikhMasa", Boolean.TRUE);
            getContext().getRequest().setAttribute("keputusanMMKN", Boolean.TRUE);
            getContext().getRequest().setAttribute("simpanMesyuarat", Boolean.TRUE);
        }

        return new JSP("pengambilan/negerisembilan/rekodKeputusanMMKn9.jsp").addParameter("tab", "true");
    }

    public Resolution simpanRundingan() {

        if (keputusan == null) {
            addSimpleError("Sila Masukkan Keputusan Rundingan.");
        } else {

            Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);

            KodKeputusan kodKeputusan = new KodKeputusan();
            KodRujukan kod = new KodRujukan();
            kod.setKod("NF");
//            kodKeputusan.setKod(keputusan);
            permohonan.setRujukanUndang2(keputusan); // Set Keputusan Rundingan
//            permohonan.setKeputusanOleh(peng);

            InfoAudit infoAudit = new InfoAudit();
            infoAudit.setDimasukOleh(peng);
            infoAudit.setTarikhMasuk(new java.util.Date());
            service.simpanPermohonan(permohonan);

            addSimpleMessage("Maklumat telah berjaya disimpan.");

            getContext().getRequest().setAttribute("form1", Boolean.TRUE);
//            getContext().getRequest().setAttribute("bilMesyuarat", Boolean.TRUE);
//            getContext().getRequest().setAttribute("tarikhMasa", Boolean.TRUE);
            getContext().getRequest().setAttribute("keputusan", Boolean.TRUE);
            getContext().getRequest().setAttribute("simpanMesyuarat", Boolean.TRUE);
        }

        return new JSP("pengambilan/negerisembilan/rekodKeputusanRunding.jsp").addParameter("tab", "true");
    }

    public Resolution simpanKhas() throws ParseException {

        if (keputusan == null) {
            addSimpleError("Sila Masukkan Keputusan Jawatankuasa Khas.");
        } else {

            Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
            tarikhKhas = getContext().getRequest().getParameter("tarikhKhas");
            KodKeputusan kodKeputusan = new KodKeputusan();
            KodRujukan kod = new KodRujukan();
            kod.setKod("NF");
//            kodKeputusan.setKod(keputusan);
            permohonan.setNoMahkamah(keputusan); // Set Keputusan Khas
            //  permohonan.setTarikhMula(sdf.parse(tarikhKhas));
//            permohonan.setKeputusanOleh(peng);

            InfoAudit infoAudit = new InfoAudit();
            infoAudit.setDimasukOleh(peng);
            infoAudit.setTarikhMasuk(new java.util.Date());
            service.simpanPermohonan(permohonan);

            addSimpleMessage("Maklumat telah berjaya disimpan.");

            getContext().getRequest().setAttribute("form1", Boolean.TRUE);
//            getContext().getRequest().setAttribute("bilMesyuarat", Boolean.TRUE);
//            getContext().getRequest().setAttribute("tarikhMasa", Boolean.TRUE);
            getContext().getRequest().setAttribute("keputusan", Boolean.TRUE);
            getContext().getRequest().setAttribute("simpanMesyuarat", Boolean.TRUE);
        }

        return new JSP("pengambilan/negerisembilan/rekodKeputusanKhas.jsp").addParameter("tab", "true");
    }

    public Resolution simpanMemo() {

        if (memo == null) {
            addSimpleError("Sila Isi ruangan memo.");
        } else {

            Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);

            KodKeputusan kodKeputusan = new KodKeputusan();
            KodRujukan kod = new KodRujukan();
            kod.setKod("NF");
//            kodKeputusan.setKod(keputusan);
            permohonan.setUlasan(memo); // Set Keputusan Rundingan
//            permohonan.setKeputusanOleh(peng);

            InfoAudit infoAudit = new InfoAudit();
            infoAudit.setDimasukOleh(peng);
            infoAudit.setTarikhMasuk(new java.util.Date());
            service.simpanPermohonan(permohonan);

            addSimpleMessage("Maklumat telah berjaya disimpan.");

            getContext().getRequest().setAttribute("form1", Boolean.TRUE);
//            getContext().getRequest().setAttribute("bilMesyuarat", Boolean.TRUE);
//            getContext().getRequest().setAttribute("tarikhMasa", Boolean.TRUE);
            getContext().getRequest().setAttribute("keputusan", Boolean.TRUE);
            getContext().getRequest().setAttribute("simpanMesyuarat", Boolean.TRUE);
        }

        return new JSP("pengambilan/negerisembilan/memoRunding.jsp").addParameter("tab", "true");
    }

    public Resolution simpanNotis() {

        if (notis == null) {
            addSimpleError("Sila Isi ruangan notis.");
        } else {

            Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);


//            kodKeputusan.setKod(keputusan);
            permohonan.setCatatan(notis); // Set Keputusan Rundingan
//            permohonan.setKeputusanOleh(peng);

            InfoAudit infoAudit = new InfoAudit();
            infoAudit.setDimasukOleh(peng);
            infoAudit.setTarikhMasuk(new java.util.Date());
            service.simpanPermohonan(permohonan);

            addSimpleMessage("Maklumat telah berjaya disimpan.");

            getContext().getRequest().setAttribute("form1", Boolean.TRUE);
//            getContext().getRequest().setAttribute("bilMesyuarat", Boolean.TRUE);
//            getContext().getRequest().setAttribute("tarikhMasa", Boolean.TRUE);
            //   getContext().getRequest().setAttribute("keputusan", Boolean.TRUE);
            getContext().getRequest().setAttribute("simpanMesyuarat", Boolean.TRUE);
        }

        return new JSP("pengambilan/negerisembilan/notis_kelulusan.jsp").addParameter("tab", "true");
    }

    public Permohonan getPermohonan() {
        return permohonan;
    }

    public void setPermohonan(Permohonan permohonan) {
        this.permohonan = permohonan;
    }

    public String getKeputusan() {
        return keputusan;
    }

    public void setKeputusan(String keputusan) {
        this.keputusan = keputusan;
    }

    public String getKeputusanMMKN() {
        return keputusanMMKN;
    }

    public void setKeputusanMMKN(String keputusanMMKN) {
        this.keputusanMMKN = keputusanMMKN;
    }

    public PermohonanRujukanLuar getPermohonanRujukanLuar() {
        return permohonanRujukanLuar;
    }

    public void setPermohonanRujukanLuar(PermohonanRujukanLuar permohonanRujukanLuar) {
        this.permohonanRujukanLuar = permohonanRujukanLuar;
    }

    public String getTarikhMesyuarat() {
        return tarikhMesyuarat;
    }

    public void setTarikhMesyuarat(String tarikhMesyuarat) {
        this.tarikhMesyuarat = tarikhMesyuarat;
    }

    public String getAmpm() {
        return ampm;
    }

    public void setAmpm(String ampm) {
        this.ampm = ampm;
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

    public String getSaat() {
        return saat;
    }

    public void setSaat(String saat) {
        this.saat = saat;
    }

    public String getAmpmDisplay() {
        return ampmDisplay;
    }

    public void setAmpmDisplay(String ampmDisplay) {
        this.ampmDisplay = ampmDisplay;
    }

    public String getKeputusanDisplay() {
        return keputusanDisplay;
    }

    public void setKeputusanDisplay(String keputusanDisplay) {
        this.keputusanDisplay = keputusanDisplay;
    }

    public KodRujukan getKodRujukan() {
        return kodRujukan;
    }

    public void setKodRujukan(KodRujukan kodRujukan) {
        this.kodRujukan = kodRujukan;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List[] getSenaraiSyor() {
        return senaraiSyor;
    }

    public void setSenaraiSyor(List[] senaraiSyor) {
        this.senaraiSyor = senaraiSyor;
    }

    public String[] getStr() {
        return str;
    }

    public void setStr(String[] str) {
        this.str = str;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public String getNotis() {
        return notis;
    }

    public void setNotis(String notis) {
        this.notis = notis;
    }

    public String getTarikhKhas() {
        return tarikhKhas;
    }

    public void setTarikhKhas(String tarikhKhas) {
        this.tarikhKhas = tarikhKhas;
    }
}
