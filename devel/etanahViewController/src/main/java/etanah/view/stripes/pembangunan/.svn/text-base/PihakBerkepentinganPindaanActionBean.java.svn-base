package etanah.view.stripes.pembangunan;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.dao.KodJenisPengenalanDAO;
import etanah.dao.KodJnsPemohonDAO;
import etanah.dao.KodNegeriDAO;
import etanah.dao.PihakDAO;
import etanah.model.Pemohon;
import etanah.model.Permohonan;
import etanah.model.Pihak;
import etanah.service.common.PemohonService;
import etanah.service.common.PihakService;
import java.util.List;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import java.text.SimpleDateFormat;
import net.sourceforge.stripes.action.HttpCache;
import etanah.dao.PemohonDAO;
import etanah.dao.PermohonanDAO;
import etanah.model.Alamat;
import etanah.model.AlamatSurat;
import etanah.model.InfoAudit;
import etanah.model.Pengguna;
import etanah.view.etanahActionBeanContext;
import java.util.Date;

/**
 *
 * @author md.nurfikri
 */
@HttpCache(allow = false)
@UrlBinding("/pembangunan/pihak_berkepentingan/pindaan")
public class PihakBerkepentinganPindaanActionBean extends AbleActionBean {

    private static final Logger LOG = Logger.getLogger(PihakBerkepentinganActionBean.class);
    private static boolean isDebug = LOG.isDebugEnabled();
    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    PemohonDAO pemohonDAO;
    @Inject
    PihakService pihakService;
    @Inject
    PihakDAO pihakDAO;
    @Inject
    KodNegeriDAO kodNegeriDAO;
    @Inject
    PemohonService pemohonService;
    @Inject
    KodJnsPemohonDAO kodJnsPemohonDAO;
    @Inject
    private etanah.Configuration conf;
    @Inject
    private KodJenisPengenalanDAO kodJenisPengenalanDAO;
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;
    private Long idPemohon;
    private Long idPihak;
    private String nama;
    private String jenisKp;
    private String noKp;
    private String alamatDaftar1;
    private String alamatDaftar2;
    private String alamatDaftar3;
    private String alamatDaftar4;
    private String poskodDaftar;
    private String negeriDaftar;

    private String suratAlamat1;
    private String suratAlamat2;
    private String suratAlamat3;
    private String suratAlamat4;
    private String suratPoskod;
    private String suratNegeri;
    private String idPermohonan;

    @DefaultHandler
    public Resolution getSenaraiPemohon() {

        return new JSP("pembangunan/common/dev_senarai_pemohon_pindaan.jsp").addParameter("tab", "true");

    }

    public Resolution simpan() {
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        Pengguna pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        Pihak pi = null;
        if (idPihak != null) {
            pi = pihakDAO.findById(idPihak);
            if (pi != null) {
            } else {
                pi = new Pihak();
            }
        } else {
            pi = new Pihak();
        }
        List< Pemohon> listPemohon = pemohonService.findPemohonByIdPermohonan(idPermohonan);
        for (Pemohon p : listPemohon) {
            pemohonService.delete(p);
        }
        Permohonan permohonan = permohonanDAO.findById(idPermohonan);
        Alamat alamat = new Alamat();
        alamat.setAlamat1(alamatDaftar1);
        alamat.setAlamat2(alamatDaftar2);
        alamat.setAlamat3(alamatDaftar3);
        alamat.setAlamat4(alamatDaftar4);
        if (negeriDaftar != null) {
            alamat.setNegeri(kodNegeriDAO.findById(negeriDaftar));
            pi.setNegeri(kodNegeriDAO.findById(suratNegeri));

        }
        alamat.setPoskod(poskodDaftar);

        AlamatSurat surat = new AlamatSurat();
        surat.setAlamatSurat1(suratAlamat1);
        surat.setAlamatSurat2(suratAlamat2);
        surat.setAlamatSurat3(suratAlamat3);
        surat.setAlamatSurat4(suratAlamat4);
        surat.setPoskodSurat(suratPoskod);
        if (suratNegeri != null) {
            surat.setNegeriSurat(kodNegeriDAO.findById(suratNegeri));
            pi.setSuratNegeri(kodNegeriDAO.findById(suratNegeri));

        }
        InfoAudit ia = new InfoAudit();
        ia.setDimasukOleh(pengguna);
        ia.setTarikhMasuk(new Date());
        Pemohon pemohon = new Pemohon();
        if (idPemohon != 0) {
            pemohon = pemohonDAO.findById(idPemohon);
        } else {
            pemohon = new Pemohon();
        }
        pemohon.setNama(nama);
        pemohon.setAlamat(alamat);
        pemohon.setAlamatSurat(surat);
        pemohon.setPermohonan(permohonan);
        pemohon.setInfoAudit(ia);
        pemohon.setNoPengenalan(noKp);
        if (jenisKp != null) {
            pemohon.setJenisPengenalan(kodJenisPengenalanDAO.findById(jenisKp));
            pi.setJenisPengenalan(kodJenisPengenalanDAO.findById(jenisKp));

        }
        pemohon.setInfoAudit(ia);
        pemohon.setKodJnsPemohon(kodJnsPemohonDAO.findById("PW"));
        pemohon.setCawangan(permohonan.getCawangan());

        pi.setNama(nama);
        pi.setNoPengenalan(noKp);
        pi.setAlamat1(alamatDaftar1);
        pi.setAlamat2(alamatDaftar2);
        pi.setAlamat3(alamatDaftar3);
        pi.setAlamat4(alamatDaftar4);
        pi.setPoskod(poskodDaftar);
        pi.setSuratAlamat1(suratAlamat1);
        pi.setSuratAlamat2(suratAlamat2);
        pi.setSuratAlamat3(suratAlamat3);
        pi.setSuratAlamat4(suratAlamat4);
        pi.setSuratPoskod(suratPoskod);
        pemohonService.simpanPihak(pi);
        pemohon.setPihak(pi);
        pemohonService.saveOrUpdate(pemohon);
        addSimpleMessage("Maklumat berjaya disimpan");
        return new JSP("pembangunan/common/dev_senarai_pemohon_pindaan.jsp").addParameter("tab", "true");

    }

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        Pemohon pemohon = pemohonService.findPemohonByIdPermohonanAndJenisPemohon(idPermohonan, "PW");
        if (pemohon != null) {
            nama = pemohon.getNama();
            noKp = pemohon.getNoPengenalan();
            if (pemohon.getJenisPengenalan() != null) {
                jenisKp = pemohon.getJenisPengenalan().getKod();
            }
            if (pemohon.getAlamat() != null) {
                alamatDaftar1 = pemohon.getAlamat().getAlamat1();
                alamatDaftar2 = pemohon.getAlamat().getAlamat2();
                alamatDaftar3 = pemohon.getAlamat().getAlamat3();
                alamatDaftar4 = pemohon.getAlamat().getAlamat4();
                if (pemohon.getAlamat().getNegeri() != null) {
                    negeriDaftar = pemohon.getAlamat().getNegeri().getKod();
                }
                poskodDaftar = pemohon.getAlamat().getPoskod();

            }
            if (pemohon.getAlamatSurat() != null) {
                suratAlamat1 = pemohon.getAlamatSurat().getAlamatSurat1();
                suratAlamat2 = pemohon.getAlamatSurat().getAlamatSurat2();
                suratAlamat3 = pemohon.getAlamatSurat().getAlamatSurat3();
                suratAlamat4 = pemohon.getAlamatSurat().getAlamatSurat4();
                suratPoskod = pemohon.getAlamatSurat().getPoskodSurat();
                if (pemohon.getAlamatSurat().getNegeriSurat() != null) {
                    suratNegeri = pemohon.getAlamatSurat().getNegeriSurat().getKod();
                }
            }
        }
    }

    public long getIdPemohon() {
        return idPemohon;
    }

    public void setIdPemohon(long idPemohon) {
        this.idPemohon = idPemohon;
    }

    public String getAlamatDaftar1() {
        return alamatDaftar1;
    }

    public void setAlamatDaftar1(String alamatDaftar1) {
        this.alamatDaftar1 = alamatDaftar1;
    }

    public String getAlamatDaftar2() {
        return alamatDaftar2;
    }

    public void setAlamatDaftar2(String alamatDaftar2) {
        this.alamatDaftar2 = alamatDaftar2;
    }

    public String getAlamatDaftar3() {
        return alamatDaftar3;
    }

    public void setAlamatDaftar3(String alamatDaftar3) {
        this.alamatDaftar3 = alamatDaftar3;
    }

    public String getAlamatDaftar4() {
        return alamatDaftar4;
    }

    public void setAlamatDaftar4(String alamatDaftar4) {
        this.alamatDaftar4 = alamatDaftar4;
    }

    public String getNoKp() {
        return noKp;
    }

    public void setNoKp(String noKp) {
        this.noKp = noKp;
    }

    public String getNegeriDaftar() {
        return negeriDaftar;
    }

    public void setNegeriDaftar(String negeriDaftar) {
        this.negeriDaftar = negeriDaftar;
    }

    public String getSuratAlamat1() {
        return suratAlamat1;
    }

    public void setSuratAlamat1(String suratAlamat1) {
        this.suratAlamat1 = suratAlamat1;
    }

    public String getSuratAlamat2() {
        return suratAlamat2;
    }

    public void setSuratAlamat2(String suratAlamat2) {
        this.suratAlamat2 = suratAlamat2;
    }

    public String getSuratAlamat3() {
        return suratAlamat3;
    }

    public void setSuratAlamat3(String suratAlamat3) {
        this.suratAlamat3 = suratAlamat3;
    }

    public String getSuratAlamat4() {
        return suratAlamat4;
    }

    public void setSuratAlamat4(String suratAlamat4) {
        this.suratAlamat4 = suratAlamat4;
    }

    public String getSuratPoskod() {
        return suratPoskod;
    }

    public void setSuratPoskod(String suratPoskod) {
        this.suratPoskod = suratPoskod;
    }

    public String getSuratNegeri() {
        return suratNegeri;
    }

    public void setSuratNegeri(String suratNegeri) {
        this.suratNegeri = suratNegeri;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getJenisKp() {
        return jenisKp;
    }

    public void setJenisKp(String jenisKp) {
        this.jenisKp = jenisKp;
    }

    public String getPoskodDaftar() {
        return poskodDaftar;
    }

    public void setPoskodDaftar(String poskodDaftar) {
        this.poskodDaftar = poskodDaftar;
    }

    public String getIdPermohonan() {
        return idPermohonan;
    }

    public void setIdPermohonan(String idPermohonan) {
        this.idPermohonan = idPermohonan;
    }

}
