package etanah.view.strata;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.dao.PermohonanDAO;
import etanah.model.HakmilikPermohonan;
import etanah.model.HakmilikPihakBerkepentingan;
import etanah.model.InfoAudit;
import etanah.model.LaporanTanah;
import etanah.model.Notis;
import etanah.model.NotisButiran;
import etanah.model.PelaksanaWaran;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.model.PermohonanKertasKandungan;
import etanah.model.PermohonanLaporanKandungan;
import etanah.model.PermohonanSyarikatLelong;
import etanah.model.Pihak;
import etanah.model.StorRampasan;
import etanah.service.StrataPtService;
import etanah.view.etanahActionBeanContext;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.RedirectResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

/**
 *
 * @author faidzal
 */
@UrlBinding("/strata/waranLaporanTanah")
public class LaporanTanahWaranActionBean extends AbleActionBean {

    @Inject
    StrataPtService strService;
    @Inject
    CommonService comm;
    @Inject
    PermohonanDAO mohonDAO;
    private Pihak pihak = new Pihak();
    List<ListHakmilikPihak> listListHakmilikPihak = new ArrayList();
    List<PermohonanLaporanKandungan> listMohonLaporKand = new ArrayList<PermohonanLaporanKandungan>();
    private HakmilikPihakBerkepentingan hpb = new HakmilikPihakBerkepentingan();
    private Pengguna peng = new Pengguna();
    private LaporanTanah laporanTanah = null;
    private HakmilikPermohonan mohonHakmilik = new HakmilikPermohonan();
    private PermohonanSyarikatLelong mohonSytLelong = new PermohonanSyarikatLelong();
    private StorRampasan storRampasan;
    private Permohonan permohonan;
    private String tajuk = "";
    private String namaPengurusan;
    private String alamat1;
    private String alamat2;
    private String alamat3;
    private String alamat4;
    private String poskod;
    private String pengurusanNegeri;
    private String pelaksanaWaran;
    private String pelelongAwam;
    private String simpananRampasan;
    private static final Logger LOG = Logger.getLogger(LaporanTanahWaranActionBean.class);
    boolean emptyKand = true;
    boolean tambahKand = false;
    String isiKand;
    private Date tarikhPelaksana;

    public Resolution tambahKandungan() {
        isiKand = "";
        LOG.info("ShowFORM:::");
        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = mohonDAO.findById(idPermohonan);
        List<HakmilikPermohonan> listHp = strService.getMaklumatTan(permohonan.getIdPermohonan());
        if (!listHp.isEmpty()) {
            hpb = strService.findPihakByIdHakmilikNKod(listHp.get(0).getHakmilik().getIdHakmilikInduk(), "PA");
            if (hpb != null) {
                pihak = hpb.getPihak();
            }
        }

        for (HakmilikPermohonan mh : permohonan.getSenaraiHakmilik()) {
            ListHakmilikPihak lhp = new ListHakmilikPihak();
            lhp.setHakmilik(mh.getHakmilik());
            lhp.setListPihak(strService.getPihakPM(mh.getHakmilik().getIdHakmilik()));

            LOG.info("LHP size: " + lhp.getListPihak().size());
            lhp.getListNotisWaran().setTarikhNotis(new Date());
            lhp.getListNotisWaran().setAmaun(BigDecimal.TEN);
            listListHakmilikPihak.add(lhp);
            LOG.info("size list" + listListHakmilikPihak.size());

        }
        tajuk = "Laporan Siasatan ke atas hakmilik " + addComma(listHp);
        namaPengurusan = pihak.getNama();
        alamat1 = pihak.getAlamat1();
        alamat2 = pihak.getAlamat2();
        alamat3 = pihak.getAlamat3();
        alamat4 = pihak.getAlamat4();
        poskod = pihak.getPoskod();
        if (pihak.getNegeri() != null) {
            pengurusanNegeri = pihak.getNegeri().getNama();
        }
        pelaksanaWaran = getValuePelaksana(strService.findListPelaksana(permohonan.getIdPermohonan()));

        storRampasan = strService.findStor(idPermohonan);
        if (storRampasan != null) {
            simpananRampasan = getValueStor(storRampasan);
        }
        laporanTanah = strService.findLaporanTanahByIdPermohonan(idPermohonan);
        if (laporanTanah != null) {
            listMohonLaporKand = strService.findByIdLaporKand(laporanTanah.getIdLaporan(), 8);
            if (listMohonLaporKand.size() > 0) {

                emptyKand = false;
            } else {
                emptyKand = true;
            }
        } else {
            emptyKand = true;
        }
        tambahKand = true;
        addSimpleMessage("Maklumat berjaya disimpan");
        return new JSP("strata/waran/laporanTanahWaran.jsp").addParameter("tab", "true");
    }

    public Resolution simpanKand() {
        String error = "";
        String msg = "";
        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        InfoAudit ia = new InfoAudit();
        permohonan = mohonDAO.findById(idPermohonan);
        laporanTanah = strService.findLaporanTanahByIdPermohonan(idPermohonan);
        if (laporanTanah != null) {
            ia.setDimasukOleh(peng);
            ia.setTarikhMasuk(new Date());
            ia.setTarikhKemaskini(tarikhPelaksana);
            laporanTanah.setInfoAudit(ia);
            ia.setTarikhKemaskini(tarikhPelaksana);
            laporanTanah = strService.simpanLaporan(laporanTanah);
            for (PermohonanLaporanKandungan mohonLapor : listMohonLaporKand) {
                ia.setDimasukOleh(laporanTanah.getInfoAudit().getDimasukOleh());
                ia.setTarikhMasuk(laporanTanah.getInfoAudit().getTarikhMasuk());
                ia.setDiKemaskiniOleh(peng);
                ia.setTarikhKemaskini(new Date());
                mohonLapor.setLaporanTanah(laporanTanah);
                mohonLapor.setInfoAudit(ia);
                mohonLapor = strService.SimpanLaporanKand(mohonLapor);
            }
            LOG.info("isiKand::" + isiKand);
            if (StringUtils.isNotBlank(isiKand)) {
                PermohonanLaporanKandungan mohonLaporKand = new PermohonanLaporanKandungan();
                List<PermohonanLaporanKandungan> listLaporKand = new ArrayList<PermohonanLaporanKandungan>();
                listLaporKand = strService.findByIdLaporKand(laporanTanah.getIdLaporan(), 8);
                if (listLaporKand.size() > 0) {
                    if (StringUtils.isNotBlank(listLaporKand.get(listLaporKand.size() - 1).getSubtajuk())) {
                        int i = Integer.parseInt(listLaporKand.get(listLaporKand.size() - 1).getSubtajuk()) + 1;
                        mohonLaporKand.setBil(Short.parseShort("8"));
                        mohonLaporKand.setSubtajuk(String.valueOf(i));
                        mohonLaporKand.setKand(isiKand);
                        mohonLaporKand.setInfoAudit(ia);
                        mohonLaporKand.setLaporanTanah(laporanTanah);
                        mohonLaporKand = strService.SimpanLaporanKand(mohonLaporKand);
                    }
                }
            }
        } else {
            laporanTanah = new LaporanTanah();
            ia.setDimasukOleh(peng);
            ia.setTarikhMasuk(new Date());
            ia.setTarikhKemaskini(tarikhPelaksana);
            laporanTanah.setPermohonan(permohonan);
            laporanTanah.setInfoAudit(ia);
            laporanTanah = strService.simpanLaporan(laporanTanah);
            PermohonanLaporanKandungan mohonLaporKand = new PermohonanLaporanKandungan();
            mohonLaporKand.setLaporanTanah(laporanTanah);
            mohonLaporKand.setBil(Short.parseShort("8"));
            mohonLaporKand.setSubtajuk("1");
            mohonLaporKand.setKand(isiKand);
            mohonLaporKand.setInfoAudit(ia);
            mohonLaporKand = strService.SimpanLaporanKand(mohonLaporKand);

        }
        return new RedirectResolution(LaporanTanahWaranActionBean.class, "showForm").addParameter("error", error).addParameter("message", msg);

    }

    public Resolution hapusKand() {
        String error = "";
        String msg = "";
        String index = getContext().getRequest().getParameter("index");
        LOG.info("index : "+index);
        if (StringUtils.isNotBlank(index)) {
            LOG.info("size : "+listMohonLaporKand.size());
            listMohonLaporKand.remove(Integer.parseInt(index));
        }
        for (int i = 0; i < listMohonLaporKand.size(); i++) {
            PermohonanLaporanKandungan mohonLaporKand = new PermohonanLaporanKandungan();
            mohonLaporKand = listMohonLaporKand.get(i);
            mohonLaporKand.setSubtajuk(String.valueOf(i));
            mohonLaporKand = strService.SimpanLaporanKand(mohonLaporKand);

        }



        return new RedirectResolution(LaporanTanahWaranActionBean.class, "showForm").addParameter("error", error).addParameter("message", msg);
    }

    @DefaultHandler
    public Resolution showForm() {
        isiKand = "";
        LOG.info("ShowFORM:::");
        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = mohonDAO.findById(idPermohonan);
        List<HakmilikPermohonan> listHp = strService.getMaklumatTan(permohonan.getIdPermohonan());
        if (!listHp.isEmpty()) {
            hpb = strService.findPihakByIdHakmilikNKod(listHp.get(0).getHakmilik().getIdHakmilikInduk(), "PA");
            if (hpb != null) {
                pihak = hpb.getPihak();
            }
        }

        for (HakmilikPermohonan mh : permohonan.getSenaraiHakmilik()) {
            ListHakmilikPihak lhp = new ListHakmilikPihak();
            lhp.setMohonHakmilik(mh);
            lhp.setHakmilik(mh.getHakmilik());
            lhp.setListPihak(strService.getPihakPM(mh.getHakmilik().getIdHakmilik()));
            Notis notis1 = new Notis();
            Notis notis2 = new Notis();
            NotisButiran notisButiran1 = new NotisButiran();
            NotisButiran notisButiran2 = new NotisButiran();
            if (!comm.findNotisByKod("NPS", idPermohonan, mh.getId()).isEmpty()) {
                notis1 = comm.findNotisByKod("NPS", idPermohonan, mh.getId()).get(0);
                if (comm.findNotiButiranByNotis(notis1) != null) {
                    notisButiran1 = comm.findNotiButiranByNotis(notis1);
                } else {
                    notisButiran1 = new NotisButiran();
                }
            } else {
                notis1 = new Notis();
                notisButiran1 = new NotisButiran();
                notis1.setTarikhNotis(null);
                notisButiran1.setAmaun(BigDecimal.ZERO);
            }

            if (!comm.findNotisByKod("NKS", idPermohonan, mh.getId()).isEmpty()) {
                notis2 = comm.findNotisByKod("NKS", idPermohonan, mh.getId()).get(0);
                if (comm.findNotiButiranByNotis(notis2) != null) {
                    notisButiran2 = comm.findNotiButiranByNotis(notis2);
                } else {
                    notisButiran2 = new NotisButiran();
                }
            } else {
                notis2 = new Notis();
                notisButiran2 = new NotisButiran();
                notis2.setTarikhNotis(null);
                notisButiran2.setAmaun(BigDecimal.ZERO);
            }

            LOG.info("LHP size: " + lhp.getListPihak().size());
            lhp.getListNotisWaran().setTarikhNotis(notis1.getTarikhNotis());
            lhp.getListNotisWaran().setAmaun(notisButiran1.getAmaun());
            lhp.getListNotisWaran2().setTarikhNotis(notis2.getTarikhNotis());
            lhp.getListNotisWaran2().setAmaun(notisButiran2.getAmaun());
            this.listListHakmilikPihak.add(lhp);
            LOG.info("size list" + listListHakmilikPihak.size());

        }
        tajuk = ("Laporan Siasatan ke atas hakmilik " + addComma(listHp)).toUpperCase();
        namaPengurusan = pihak.getNama();
        alamat1 = pihak.getAlamat1();
        alamat2 = pihak.getAlamat2();
        alamat3 = pihak.getAlamat3();
        alamat4 = pihak.getAlamat4();
        poskod = pihak.getPoskod();
        if (pihak.getNegeri() != null) {
            pengurusanNegeri = pihak.getNegeri().getKod();
        }
        pelaksanaWaran = getValuePelaksana(strService.findListPelaksana(permohonan.getIdPermohonan()));

        storRampasan = strService.findStor(idPermohonan);
        if (storRampasan != null) {
            simpananRampasan = getValueStor(storRampasan);
        }
        if (!strService.findSytLelong(idPermohonan).isEmpty()) {
            mohonSytLelong = strService.findSytLelong(idPermohonan).get(0);
            pelelongAwam = mohonSytLelong.getSytJuruLelong().getNama();
        }

        laporanTanah = strService.findLaporanTanahByIdPermohonan(idPermohonan);
        if (laporanTanah != null) {
            listMohonLaporKand = strService.findByIdLaporKand(laporanTanah.getIdLaporan(), 8);
            if (listMohonLaporKand.size() > 0) {

                emptyKand = false;
            } else {
                emptyKand = true;
            }
            if (laporanTanah.getInfoAudit() != null) {
                tarikhPelaksana = laporanTanah.getInfoAudit().getTarikhKemaskini();
            }
        } else {
            emptyKand = true;
        }
        return new JSP("strata/waran/laporanTanahWaran.jsp").addParameter("tab", "true");
    }

    private String getValueStor(StorRampasan storRampasan) {
        String s = "";
        s = storRampasan.getAlamat1();
        if (storRampasan.getAlamat2() != null) {
            s += ", " + storRampasan.getAlamat2();
        }
        if (storRampasan.getAlamat3() != null) {
            s += ", " + storRampasan.getAlamat3();
        }
        if (storRampasan.getAlamat4() != null) {
            s += ", " + storRampasan.getAlamat4();
        }
        if (storRampasan.getPoskod() != null) {
            s += ", " + storRampasan.getPoskod();
        }
        if (storRampasan.getNegeri() != null) {
            s += ", " + storRampasan.getNegeri().getNama();
        }
        return s;
    }

    private String getValuePelaksana(List<PelaksanaWaran> a) {
        String s = null;
        for (int i = 0; i < a.size(); i++) {
            if (i == a.size()) {
                s += " dan" + a.get(i).getNama();
            } else {
                if (i == 0) {
                    s = a.get(i).getNama();
                } else {
                    s += "," + a.get(i).getNama();
                }
                a.get(i);
            }
        }
        return s;
    }

    private String addComma(List<HakmilikPermohonan> a) {
        String s = null;
        for (int i = 0; i < a.size(); i++) {
            if (i == a.size()) {
                s += " dan" + a.get(i).getHakmilik().getIdHakmilik();
            } else {
                if (i == 0) {
                    s = a.get(i).getHakmilik().getIdHakmilik();
                } else {
                    s += "," + a.get(i).getHakmilik().getIdHakmilik();
                }
                a.get(i);
            }
        }
        return s;
    }

    public String getAlamat1() {
        return alamat1;
    }

    public void setAlamat1(String alamat1) {
        this.alamat1 = alamat1;
    }

    public String getAlamat2() {
        return alamat2;
    }

    public void setAlamat2(String alamat2) {
        this.alamat2 = alamat2;
    }

    public String getAlamat3() {
        return alamat3;
    }

    public void setAlamat3(String alamat3) {
        this.alamat3 = alamat3;
    }

    public String getAlamat4() {
        return alamat4;
    }

    public void setAlamat4(String alamat4) {
        this.alamat4 = alamat4;
    }

    public List<ListHakmilikPihak> getListListHakmilikPihak() {
        return listListHakmilikPihak;
    }

    public void setListListHakmilikPihak(List<ListHakmilikPihak> listListHakmilikPihak) {
        this.listListHakmilikPihak = listListHakmilikPihak;
    }

    public String getNamaPengurusan() {
        return namaPengurusan;
    }

    public void setNamaPengurusan(String namaPengurusan) {
        this.namaPengurusan = namaPengurusan;
    }

    public String getPengurusanNegeri() {
        return pengurusanNegeri;
    }

    public void setPengurusanNegeri(String pengurusanNegeri) {
        this.pengurusanNegeri = pengurusanNegeri;
    }

    public Permohonan getPermohonan() {
        return permohonan;
    }

    public void setPermohonan(Permohonan permohonan) {
        this.permohonan = permohonan;
    }

    public Pihak getPihak() {
        return pihak;
    }

    public void setPihak(Pihak pihak) {
        this.pihak = pihak;
    }

    public String getPoskod() {
        return poskod;
    }

    public void setPoskod(String poskod) {
        this.poskod = poskod;
    }

    public String getTajuk() {
        return tajuk;
    }

    public void setTajuk(String tajuk) {
        this.tajuk = tajuk;
    }

    public String getPelaksanaWaran() {
        return pelaksanaWaran;
    }

    public void setPelaksanaWaran(String pelaksanaWaran) {
        this.pelaksanaWaran = pelaksanaWaran;
    }

    public String getPelelongAwam() {
        return pelelongAwam;
    }

    public void setPelelongAwam(String pelelongAwam) {
        this.pelelongAwam = pelelongAwam;
    }

    public String getSimpananRampasan() {
        return simpananRampasan;
    }

    public void setSimpananRampasan(String simpananRampasan) {
        this.simpananRampasan = simpananRampasan;
    }

    public List<PermohonanLaporanKandungan> getListMohonLaporKand() {
        return listMohonLaporKand;
    }

    public void setListMohonLaporKand(List<PermohonanLaporanKandungan> listMohonLaporKand) {
        this.listMohonLaporKand = listMohonLaporKand;
    }

    public boolean isEmptyKand() {
        return emptyKand;
    }

    public void setEmptyKand(boolean emptyKand) {
        this.emptyKand = emptyKand;
    }

    public String getIsiKand() {
        return isiKand;
    }

    public void setIsiKand(String isiKand) {
        this.isiKand = isiKand;
    }

    public boolean isTambahKand() {
        return tambahKand;
    }

    public void setTambahKand(boolean tambahKand) {
        this.tambahKand = tambahKand;
    }

    public Date getTarikhPelaksana() {
        return tarikhPelaksana;
    }

    public void setTarikhPelaksana(Date tarikhPelaksana) {
        this.tarikhPelaksana = tarikhPelaksana;
    }
}
