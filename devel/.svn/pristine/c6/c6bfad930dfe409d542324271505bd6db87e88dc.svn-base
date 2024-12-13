/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.stripes.pembangunan;

import net.sourceforge.stripes.action.UrlBinding;
import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.dao.HakmilikDAO;
import etanah.dao.KodDokumenDAO;
import etanah.dao.KodKeputusanDAO;
import etanah.dao.LaporanTanahDAO;
import etanah.dao.PemohonDAO;
import etanah.dao.PermohonanDAO;
import etanah.dao.PermohonanKertasDAO;
import etanah.dao.PermohonanKertasKandunganDAO;
import etanah.model.Hakmilik;
import etanah.model.HakmilikPermohonan;
import etanah.model.HakmilikPihakBerkepentingan;
import etanah.model.InfoAudit;
import etanah.model.KodDokumen;
import etanah.model.Pemohon;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.model.PermohonanKertas;
import etanah.model.PermohonanKertasKandungan;
import etanah.service.BPelService;
import etanah.service.PembangunanService;
import etanah.view.etanahActionBeanContext;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.StringTokenizer;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.RedirectResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.controller.LifecycleStage;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

/**
 *
 * @author khairul.hazwan
 */
@UrlBinding("/pembangunan/melaka/srtKpsn5A")
public class SuratKpsn5aActionBean extends AbleActionBean {

    private static final Logger LOG = Logger.getLogger(SuratKpsn5aActionBean.class);
    @Inject
    KodDokumenDAO kodDokumenDAO;
    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    HakmilikDAO hakmilikDAO;
    @Inject
    PemohonDAO pemohonDAO;
    @Inject
    PembangunanService devService;
    @Inject
    PermohonanKertasDAO permohonanKertasDAO;
    @Inject
    PermohonanKertasKandunganDAO permohonanKertasKandunganDAO;
    @Inject
    KodKeputusanDAO kodKeputusanDAO;
    @Inject
    LaporanTanahDAO laporanTanahDAO;
    @Inject
    PembangunanUtility pu;
    @Inject
    BPelService service;
    private Permohonan permohonan;
    private Hakmilik hakmilik;
    private Pemohon pemohon;
    private HakmilikPihakBerkepentingan hakmilikPihakBerkepentingan;
    private PermohonanKertasKandungan kertasK;
    private PermohonanKertas kertasP;
    private Pengguna pengguna;
    private List<PermohonanKertasKandungan> senaraiKandungan1;
    private List<PermohonanKertasKandungan> senaraiKandungan2;
    private List<PermohonanKertasKandungan> senaraiKandungan3;
    private List<PermohonanKertasKandungan> senaraiKandungan4;
    private List<PermohonanKertasKandungan> senaraiKandungan5;
    private int rowCount1;
    private int rowCount2;
    private int rowCount3;
    private int rowCount4;
    private int rowCount5;
    private String tajuk;
    private String pejTanah;
    private String perihal1;
    private String perihal2;
    private String perihal3;
    private String perihal4;
    private String perihal5;
    private HakmilikPermohonan hp;
    private boolean btn = true;
    private KodDokumen kd;

    @DefaultHandler
    public Resolution showForm() {
        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        return new JSP("pembangunan/melaka/surat_kpsn_5a.jsp").addParameter("tab", "true");
    }

    public Resolution showForm2() {
        return new JSP("pembangunan/melaka/surat_kpsn_5a.jsp").addParameter("tab", "true");
    }

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {
        LOG.info("------rehydrate------");
        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        permohonan = permohonanDAO.findById(idPermohonan);

        List<PermohonanKertas> senaraiKertas = new ArrayList<PermohonanKertas>();
        senaraiKertas = devService.findSenaraiKertasByKod(idPermohonan, "SKN5A");
        kertasP = new PermohonanKertas();
        LOG.info("-------senaraiKertas----:" + senaraiKertas.size());
        if (senaraiKertas.size() > 0) {
            kertasP = senaraiKertas.get(0);
            List<PermohonanKertasKandungan> kandList = new ArrayList<PermohonanKertasKandungan>();
            kandList = devService.findSenaraiKertasKandunganByIdKertas(kertasP.getIdKertas());
            LOG.info("------rehydrate--getvalues----" + kandList.size());
            for (int j = 0; j < kandList.size(); j++) {
                kertasK = new PermohonanKertasKandungan();
                kertasK = kandList.get(j);
                if (kertasK.getBil() == 1) {
                    tajuk = kertasK.getKandungan();
                } else if (kertasK.getBil() == 2) {
                    perihal1 = kertasK.getKandungan();
                } else if (kertasK.getBil() == 3) {
                    perihal2 = kertasK.getKandungan();
                } else if (kertasK.getBil() == 4) {
                    perihal3 = kertasK.getKandungan();
                } else if (kertasK.getBil() == 6) {
                    perihal5 = kertasK.getKandungan();
                }
            }

            senaraiKandungan4 = new ArrayList<PermohonanKertasKandungan>();
            senaraiKandungan4 = devService.findKertasKandByIdKertas(kertasP.getIdKertas(), 5);
            rowCount4 = senaraiKandungan4.size();
        } else {

            if (pengguna.getKodCawangan().getDaerah() != null) {
                if (pengguna.getKodCawangan().getDaerah().getKod().equals("01")) {
                    pejTanah = "Melaka Tengah";
                }
                if (pengguna.getKodCawangan().getDaerah().getKod().equals("02")) {
                    pejTanah = "Jasin";
                }
                if (pengguna.getKodCawangan().getDaerah().getKod().equals("03")) {
                    pejTanah = "Alor Gajah";
                }
            }

            if (permohonan.getSebab() != null) {
                tajuk = permohonan.getSebab().toUpperCase();
            } else {
                tajuk = " - ";
            }

            PermohonanKertas pk = devService.findPermohonanKertasByIdMohonAndKodDokumen(idPermohonan, "RPYAB");
            if (pk != null) {
                LOG.info("------------RPYAB----kertasP-----:" + pk.getIdKertas());

                for (int j = 0; j < pk.getSenaraiKandungan().size(); j++) {
                    LOG.info("senarai kandungan:" + pk.getSenaraiKandungan().size());
                    senaraiKandungan4 = new ArrayList<PermohonanKertasKandungan>();
                    senaraiKandungan4 = devService.findKertasKandByIdKertas(pk.getIdKertas(), 9);
                    rowCount4 = senaraiKandungan4.size();
                }
            }

            perihal1 = "2. " + "Sukacita dimaklumkan bahawa Pihak Berkuasa Negeri telah bersetuju meluluskan permohonan penyerahan "
                    + "balik dan pemberimilikan semula tanah tersebut di atas pada __________. Bersama-sama ini dikemukakan sesalinan Borang 5A "
                    + "(Notis Genap Masa Hasil Tanah Di Bayar) untuk tindakan tuan.";

            hp = devService.findByIdPermohonan(idPermohonan);
            perihal2 = "3. " + "Pihak Berkuasa Negeri juga bersetuju untuk memberimilik semula tanah tersebut di atas kepada tuan "
                    + "di bawah Seksyen 42(1)(a) Kanun Tanah Negara setelah selesai proses serahan tanah mengikut pelan tatatur Bil. " + hp.getCatatan() + " yang telah "
                    + "dipersetujui oleh Yang Dipertua Majlis Perbandaran  " + pejTanah + " seperti no. rujukan " + hp.getNomborRujukan() + " bertarikh _________. Kelulusan Pemberimilikan Semula "
                    + "adalah seperti pelan pra-hitung dan ringkasan yang mengandungi butir - butir seperti berikut : - ";

            perihal3 = "4. " + "Pemohon hendaklah mengambil tindakan untuk memperenggankan tanah-tanah ini melalui Juruukur Berlesen "
                    + "dan keluasan tiap-tiap plot untuk hakmilik sementara yang dikeluarkan adalah sama dengan keluasan dalam pelan pra-hitung.";

            perihal5 = "6. " + "Sila jelaskan bayaran yang dituntut dalam tempoh tiga (3) bulan dari tarikh penerimaan surat kelulusan "
                    + "ini. Tindakan membatalkan kelulusan ini akan diambil sekiranya tuan gagal untuk menjelaskan bayaran yang dituntut dalam tempoh "
                    + "tersebut. Kerjasama yang awal dari pihak tuan sangat diharapkan dan didahului dengan terima kasih.";

        }
    }

    public Resolution simpan() throws ParseException {
        LOG.info("------simpan------");
        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = permohonanDAO.findById(idPermohonan);
        pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        InfoAudit infoAudit = new InfoAudit();
        kd = kodDokumenDAO.findById("SKN5A");

        PermohonanKertas permohonanKertas = new PermohonanKertas();

        if (kertasK != null) {
            permohonanKertas = permohonanKertasDAO.findById(kertasK.getKertas().getIdKertas());
            infoAudit = permohonanKertas.getInfoAudit();
            infoAudit.setDiKemaskiniOleh(pengguna);
            infoAudit.setTarikhKemaskini(new java.util.Date());

        } else {
            infoAudit.setDimasukOleh(pengguna);
            infoAudit.setTarikhMasuk(new java.util.Date());
        }

        ArrayList listUlasan = new ArrayList();
        ArrayList listBill = new ArrayList();


        if (tajuk == null || tajuk.equals("")) {
            tajuk = "-";
        }
        if (perihal1 == null || perihal1.equals("")) {
            perihal1 = "-";
        }
        if (perihal2 == null || perihal2.equals("")) {
            perihal2 = "-";
        }
        if (perihal3 == null || perihal3.equals("")) {
            perihal3 = "-";
        }

        if (perihal5 == null || perihal5.equals("")) {
            perihal5 = "-";
        }

        listUlasan.add(tajuk);
        listUlasan.add(perihal1);
        listUlasan.add(perihal2);
        listUlasan.add(perihal3);
        // listUlasan.add(perihal4);
        listUlasan.add(perihal5);

        listBill.add(new Integer(1));
        listBill.add(new Integer(2));
        listBill.add(new Integer(3));
        listBill.add(new Integer(4));
        //  listBill.add(new Integer(5));
        listBill.add(new Integer(6));

        if (kertasK != null) {
            if (!kertasK.getKandungan().isEmpty()) {
                for (int j = 0; j < permohonanKertas.getSenaraiKandungan().size(); j++) {

                    kertasK = new PermohonanKertasKandungan();
                    kertasK = permohonanKertas.getSenaraiKandungan().get(j);

                    if (kertasK.getBil() == 1) {
                        kertasK.setKandungan(tajuk);
                    } else if (kertasK.getBil() == 2) {
                        kertasK.setKandungan(perihal1);
                    } else if (kertasK.getBil() == 3) {
                        kertasK.setKandungan(perihal2);
                    } else if (kertasK.getBil() == 4) {
                        kertasK.setKandungan(perihal3);
                    } else if (kertasK.getBil() == 6) {
                        kertasK.setKandungan(perihal5);
                    }


                    permohonanKertas.setTajuk("SURAT KEPUTUSAN 5A");
                    permohonanKertas.setKodDokumen(kd);
                    permohonanKertas.setInfoAudit(infoAudit);
                    permohonanKertas.setCawangan(pengguna.getKodCawangan());
                    permohonanKertas.setPermohonan(permohonan);
                    kertasK.setInfoAudit(infoAudit);
                    devService.simpanPermohonanKertas(permohonanKertas);
                    devService.simpanPermohonanKertasKandungan(kertasK);

                }
                addSimpleMessage("Maklumat telah berjaya disimpan.");
            }
        } else {

            for (int i = 0; i < listUlasan.size(); i++) {
                String ulasan = (String) listUlasan.get(i);
                LOG.info("---------- i ----------:" + i);
                Integer bil = (Integer) listBill.get(i);

                kertasK = new PermohonanKertasKandungan();
                permohonanKertas.setInfoAudit(infoAudit);
                permohonanKertas.setCawangan(pengguna.getKodCawangan());
                permohonanKertas.setPermohonan(permohonan);
                permohonanKertas.setKodDokumen(kd);
                permohonanKertas.setTajuk("SURAT KEPUTUSAN 5A");

                kertasK.setKertas(permohonanKertas);
                kertasK.setBil(bil);
                kertasK.setInfoAudit(infoAudit);
                kertasK.setKandungan(ulasan);
                kertasK.setCawangan(pengguna.getKodCawangan());
                devService.simpanPermohonanKertas(permohonanKertas);
                devService.simpanPermohonanKertasKandungan(kertasK);
            }
            LOG.debug("record updated successfully........");
            addSimpleMessage("Maklumat telah berjaya disimpan.");
        }

        senaraiKandungan4 = devService.findKertasKandByIdKertas(kertasP.getIdKertas(), 5);
        int kira = Integer.parseInt(getContext().getRequest().getParameter("rowCount4"));
        for (int i = 1; i <= kira; i++) {
            InfoAudit iaP = new InfoAudit();
            PermohonanKertasKandungan permohonanKertasKandungan1 = new PermohonanKertasKandungan();
            if (senaraiKandungan4.size() != 0 && i <= senaraiKandungan4.size()) {
                Long id = senaraiKandungan4.get(i - 1).getIdKandungan();
                if (id != null) {
                    permohonanKertasKandungan1 = permohonanKertasKandunganDAO.findById(id);
                    iaP = permohonanKertasKandungan1.getInfoAudit();
                    iaP.setTarikhKemaskini(new Date());
                    iaP.setDiKemaskiniOleh(pengguna);
                }
            } else {
                permohonanKertasKandungan1 = new PermohonanKertasKandungan();
                iaP.setTarikhMasuk(new Date());
                iaP.setDimasukOleh(pengguna);
            }
            permohonanKertasKandungan1.setKertas(permohonanKertas);
            permohonanKertasKandungan1.setBil(5);
            String kandungan = getContext().getRequest().getParameter("perihal4" + i);
            if (kandungan == null || kandungan.equals("")) {
                kandungan = " - ";
            }
            permohonanKertasKandungan1.setKandungan(kandungan);
            permohonanKertasKandungan1.setCawangan(permohonan.getCawangan());
            permohonanKertasKandungan1.setSubtajuk("5." + i);
            permohonanKertasKandungan1.setInfoAudit(iaP);
            devService.simpanPermohonanKertasKandungan(permohonanKertasKandungan1);
        }
        rehydrate();
        return showForm();
    }

    public Resolution deleteSingle() {

        String idKandungan = getContext().getRequest().getParameter("idKandungan");
        PermohonanKertasKandungan permohonanKertasKandungan1 = new PermohonanKertasKandungan();
        try {
            permohonanKertasKandungan1 = permohonanKertasKandunganDAO.findById(Long.parseLong(idKandungan));
        } catch (Exception e) {
            LOG.debug("Hapus empty record");
        }
        if (permohonanKertasKandungan1 != null) {
            devService.deleteKertasKandungan(permohonanKertasKandungan1);
            LOG.debug("Record deleted Successfully...");
        }
        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        addSimpleMessage("Maklumat telah berjaya dihapus.");

        return new RedirectResolution(SuratKpsn5aActionBean.class, "locate");
    }

    public Hakmilik getHakmilik() {
        return hakmilik;
    }

    public void setHakmilik(Hakmilik hakmilik) {
        this.hakmilik = hakmilik;
    }

    public Pemohon getPemohon() {
        return pemohon;
    }

    public void setPemohon(Pemohon pemohon) {
        this.pemohon = pemohon;
    }

    public HakmilikPihakBerkepentingan getHakmilikPihakBerkepentingan() {
        return hakmilikPihakBerkepentingan;
    }

    public void setHakmilikPihakBerkepentingan(HakmilikPihakBerkepentingan hakmilikPihakBerkepentingan) {
        this.hakmilikPihakBerkepentingan = hakmilikPihakBerkepentingan;
    }

    public PermohonanKertasKandungan getKertasK() {
        return kertasK;
    }

    public void setKertasK(PermohonanKertasKandungan kertasK) {
        this.kertasK = kertasK;
    }

    public PermohonanKertas getKertasP() {
        return kertasP;
    }

    public void setKertasP(PermohonanKertas kertasP) {
        this.kertasP = kertasP;
    }

    public List<PermohonanKertasKandungan> getSenaraiKandungan1() {
        return senaraiKandungan1;
    }

    public void setSenaraiKandungan1(List<PermohonanKertasKandungan> senaraiKandungan1) {
        this.senaraiKandungan1 = senaraiKandungan1;
    }

    public List<PermohonanKertasKandungan> getSenaraiKandungan2() {
        return senaraiKandungan2;
    }

    public void setSenaraiKandungan2(List<PermohonanKertasKandungan> senaraiKandungan2) {
        this.senaraiKandungan2 = senaraiKandungan2;
    }

    public List<PermohonanKertasKandungan> getSenaraiKandungan3() {
        return senaraiKandungan3;
    }

    public void setSenaraiKandungan3(List<PermohonanKertasKandungan> senaraiKandungan3) {
        this.senaraiKandungan3 = senaraiKandungan3;
    }

    public List<PermohonanKertasKandungan> getSenaraiKandungan4() {
        return senaraiKandungan4;
    }

    public void setSenaraiKandungan4(List<PermohonanKertasKandungan> senaraiKandungan4) {
        this.senaraiKandungan4 = senaraiKandungan4;
    }

    public List<PermohonanKertasKandungan> getSenaraiKandungan5() {
        return senaraiKandungan5;
    }

    public void setSenaraiKandungan5(List<PermohonanKertasKandungan> senaraiKandungan5) {
        this.senaraiKandungan5 = senaraiKandungan5;
    }

    public int getRowCount1() {
        return rowCount1;
    }

    public void setRowCount1(int rowCount1) {
        this.rowCount1 = rowCount1;
    }

    public int getRowCount2() {
        return rowCount2;
    }

    public void setRowCount2(int rowCount2) {
        this.rowCount2 = rowCount2;
    }

    public int getRowCount3() {
        return rowCount3;
    }

    public void setRowCount3(int rowCount3) {
        this.rowCount3 = rowCount3;
    }

    public int getRowCount4() {
        return rowCount4;
    }

    public void setRowCount4(int rowCount4) {
        this.rowCount4 = rowCount4;
    }

    public int getRowCount5() {
        return rowCount5;
    }

    public void setRowCount5(int rowCount5) {
        this.rowCount5 = rowCount5;
    }

    public Permohonan getPermohonan() {
        return permohonan;
    }

    public void setPermohonan(Permohonan permohonan) {
        this.permohonan = permohonan;
    }

    public Pengguna getPengguna() {
        return pengguna;
    }

    public void setPengguna(Pengguna pengguna) {
        this.pengguna = pengguna;
    }

    public String getTajuk() {
        return tajuk;
    }

    public void setTajuk(String tajuk) {
        this.tajuk = tajuk;
    }

    public String getPejTanah() {
        return pejTanah;
    }

    public void setPejTanah(String pejTanah) {
        this.pejTanah = pejTanah;
    }

    public String getPerihal1() {
        return perihal1;
    }

    public void setPerihal1(String perihal1) {
        this.perihal1 = perihal1;
    }

    public String getPerihal2() {
        return perihal2;
    }

    public void setPerihal2(String perihal2) {
        this.perihal2 = perihal2;
    }

    public String getPerihal3() {
        return perihal3;
    }

    public void setPerihal3(String perihal3) {
        this.perihal3 = perihal3;
    }

    public String getPerihal4() {
        return perihal4;
    }

    public void setPerihal4(String perihal4) {
        this.perihal4 = perihal4;
    }

    public String getPerihal5() {
        return perihal5;
    }

    public void setPerihal5(String perihal5) {
        this.perihal5 = perihal5;
    }

    public HakmilikPermohonan getHp() {
        return hp;
    }

    public void setHp(HakmilikPermohonan hp) {
        this.hp = hp;
    }

    public boolean isBtn() {
        return btn;
    }

    public void setBtn(boolean btn) {
        this.btn = btn;
    }

    public KodDokumen getKd() {
        return kd;
    }

    public void setKd(KodDokumen kd) {
        this.kd = kd;
    }
}
