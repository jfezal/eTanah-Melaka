package etanah.view.strata;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.dao.KodJenisPengenalanDAO;
import etanah.dao.KodNegeriDAO;
import etanah.dao.PelaksanaWaranDAO;
import etanah.dao.PermohonanDAO;
import etanah.model.InfoAudit;
import etanah.model.KodNegeri;
import etanah.model.PelaksanaWaran;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.service.StrataPtService;
import etanah.view.etanahActionBeanContext;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

@UrlBinding("/strata/pelaksana")
public class PelaksanaWaranActionBean extends AbleActionBean {

    @Inject
    StrataPtService strService;
    @Inject
    PermohonanDAO mohonDAO;
    @Inject
    CommonService comm;
    @Inject
    KodNegeriDAO kodNegeriDAO;
    @Inject
    KodJenisPengenalanDAO jenisPengenalanDAO;
    @Inject
    PelaksanaWaranDAO pelaksanaWaranDAO;
    Permohonan permohonan;
    private List<PelaksanaWaran> listPelaksana = new ArrayList();
    Pengguna peng = new Pengguna();
    PelaksanaWaran pelaksanaWaran = new PelaksanaWaran();
    private String nama;
    private String alamat1;
    private String alamat2;
    private String alamat3;
    private String alamat4;
    private String poskod;
    private String negeri;
    private String noPengenalan;
    private String jenisKP;
    private String kerja;
    private Date tarikhPelantikan;
    private static final Logger LOG = Logger.getLogger(PelaksanaWaranActionBean.class);
    private boolean readOnly = false;
    private String idPelaksana;

    @DefaultHandler
    public Resolution showForm() {
        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = mohonDAO.findById(idPermohonan);
        listPelaksana = comm.getListPerlaksana(idPermohonan);
        if (!listPelaksana.isEmpty()) {
            tarikhPelantikan = listPelaksana.get(0).getTarikhLantikan();
        }
        getContext().getRequest().setAttribute("tambah", false);
        return new JSP("strata/waran/PelaksanaWaran.jsp").addParameter("tab", "true");

    }

    public Resolution showFormReadOnly() {
        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = mohonDAO.findById(idPermohonan);
        listPelaksana = comm.getListPerlaksana(idPermohonan);
        if (!listPelaksana.isEmpty()) {
            tarikhPelantikan = listPelaksana.get(0).getTarikhLantikan();
        }
        getContext().getRequest().setAttribute("readonly", false);
        readOnly = true;
        return new JSP("strata/waran/PelaksanaWaran.jsp").addParameter("tab", "true");

    }

    public Resolution tambahPelaksana() {
        pelaksanaWaran = new PelaksanaWaran();
        getContext().getRequest().setAttribute("tambah", true);
        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = mohonDAO.findById(idPermohonan);
        listPelaksana = comm.getListPerlaksana(idPermohonan);
        if (!listPelaksana.isEmpty()) {
            tarikhPelantikan = listPelaksana.get(0).getTarikhLantikan();
        }
//        return new JSP("strata/waran/tambahPelaksana.jsp").addParameter("popup", "true");

        return new JSP("strata/waran/PelaksanaWaran.jsp").addParameter("tab", "true");
    }

    public Resolution simpan() {
        peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        KodNegeri kodNegeri = new KodNegeri();
        permohonan = mohonDAO.findById(idPermohonan);
        pelaksanaWaran = new PelaksanaWaran();
        InfoAudit ia = new InfoAudit();
        ia.setDimasukOleh(peng);
        ia.setTarikhMasuk(new Date());
        pelaksanaWaran.setNama(nama);
        pelaksanaWaran.setNoPengenalan(noPengenalan);
        if (StringUtils.isNotEmpty(jenisKP)) {
            pelaksanaWaran.setJenisPengenalan(jenisPengenalanDAO.findById(jenisKP));
        }

        pelaksanaWaran.setAlamat1(alamat1);
        pelaksanaWaran.setAlamat2(alamat2);
        pelaksanaWaran.setAlamat3(alamat3);
        pelaksanaWaran.setAlamat4(alamat4);
        pelaksanaWaran.setCawangan(peng.getKodCawangan());
        pelaksanaWaran.setPermohonan(permohonan);
        pelaksanaWaran.setPoskod(poskod);
        pelaksanaWaran.setKerja(kerja);
        pelaksanaWaran.setInfoAudit(ia);
        if (StringUtils.isNotEmpty(negeri)) {
            kodNegeri = kodNegeriDAO.findById(negeri);
            pelaksanaWaran.setNegeri(kodNegeri);
        }
        LOG.info("Simpan PelaksanaWaran");
        pelaksanaWaran.setCawangan(peng.getKodCawangan());
        LOG.info(pelaksanaWaran.getNama());
        comm.savePelaksana(pelaksanaWaran);

        listPelaksana = comm.getListPerlaksana(idPermohonan);
        if (!listPelaksana.isEmpty()) {
            tarikhPelantikan = listPelaksana.get(0).getTarikhLantikan();
        }
        getContext().getRequest().setAttribute("tambah", false);
        addSimpleMessage("Maklumat berjaya disimpan");
        return new JSP("strata/waran/PelaksanaWaran.jsp").addParameter("tab", "true");
    }

    //added by murali
    public Resolution showEdit() {
        LOG.info("-------------showEditForm--------------");
        idPelaksana = getContext().getRequest().getParameter("id");
        LOG.info("--------id Pelaksana--------::" + idPelaksana);
        pelaksanaWaran = pelaksanaWaranDAO.findById(Long.parseLong(idPelaksana));
        LOG.info("--------pelaksanaWaran--------::" + pelaksanaWaran);
        if (pelaksanaWaran != null) {

            nama = pelaksanaWaran.getNama();
            jenisKP = pelaksanaWaran.getJenisPengenalan().getKod();
            noPengenalan = pelaksanaWaran.getNoPengenalan();
            alamat1 = pelaksanaWaran.getAlamat1();
            alamat2 = pelaksanaWaran.getAlamat2();
            alamat3 = pelaksanaWaran.getAlamat3();
            alamat4 = pelaksanaWaran.getAlamat4();
            poskod = pelaksanaWaran.getPoskod();
            negeri = pelaksanaWaran.getNegeri().getKod();
            kerja = pelaksanaWaran.getKerja();
        }

        return new JSP("strata/waran/PelaksanaWaranEdit.jsp").addParameter("popup", "true");
    }

    public Resolution upDatePelaksana() {
        LOG.info("-------------Saving Edited Information--------------");
        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        idPelaksana = getContext().getRequest().getParameter("id");
        pelaksanaWaran = pelaksanaWaranDAO.findById(Long.parseLong(idPelaksana));
        LOG.info("--------find pelaksanaWaran for Ediditing--------::" + pelaksanaWaran);
        if (pelaksanaWaran != null) {
            InfoAudit ia = new InfoAudit();
            ia = pelaksanaWaran.getInfoAudit();
            ia.setDiKemaskiniOleh(peng);
            ia.setTarikhKemaskini(new java.util.Date());
            pelaksanaWaran.setInfoAudit(ia);
            pelaksanaWaran.setNama(nama);
            pelaksanaWaran.setNoPengenalan(noPengenalan);
            if (StringUtils.isNotEmpty(jenisKP)) {
                pelaksanaWaran.setJenisPengenalan(jenisPengenalanDAO.findById(jenisKP));
            }
            pelaksanaWaran.setNoPengenalan(noPengenalan);
            pelaksanaWaran.setAlamat1(alamat1);
            pelaksanaWaran.setAlamat2(alamat2);
            pelaksanaWaran.setAlamat3(alamat3);
            pelaksanaWaran.setAlamat4(alamat4);
            pelaksanaWaran.setPoskod(poskod);
            pelaksanaWaran.setKerja(kerja);
            KodNegeri kodNegeri = new KodNegeri();
            if (StringUtils.isNotEmpty(negeri)) {
                kodNegeri = kodNegeriDAO.findById(negeri);
                pelaksanaWaran.setNegeri(kodNegeri);
            }
            comm.savePelaksana(pelaksanaWaran);
        }
        listPelaksana = comm.getListPerlaksana(idPermohonan);
        if (!listPelaksana.isEmpty()) {
            tarikhPelantikan = listPelaksana.get(0).getTarikhLantikan();
        }

        addSimpleMessage("Maklumat telah berjaya dikemaskini.");

        return new JSP("strata/waran/PelaksanaWaran.jsp").addParameter("tab", "true");
    }

    public Resolution hapusPelaksanaWaran() {
        peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        String idPelaksana = getContext().getRequest().getParameter("idPelaksana");
        LOG.info("IdPelaksana : " + idPelaksana);
        if (StringUtils.isNotEmpty(idPelaksana)) {
            pelaksanaWaran = new PelaksanaWaran();
            pelaksanaWaran = strService.findPelaksanaWaran(Long.parseLong(idPelaksana));
            strService.deletePelaksana(pelaksanaWaran);
        }
        listPelaksana = comm.getListPerlaksana(idPermohonan);
        if (!listPelaksana.isEmpty()) {
            tarikhPelantikan = listPelaksana.get(0).getTarikhLantikan();
        }
        getContext().getRequest().setAttribute("tambah", false);
        return new JSP("strata/waran/PelaksanaWaran.jsp").addParameter("tab", "true");
    }

    public Resolution saveTarikhPelantikan() {
        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");

        listPelaksana = comm.getListPerlaksana(idPermohonan);

        if (!listPelaksana.isEmpty() && listPelaksana.size() > 0) {
            LOG.info("----- !listPelaksana.isEmpty() && listPelaksana > 0 -----");
            for (PelaksanaWaran p : listPelaksana) {
                LOG.info("listPelaksana. id: " + p.getIdPelaksanaWaran() + " nama : " + p.getNama());
                p.setTarikhLantikan(tarikhPelantikan);
                comm.savePelaksana(p);
            }
            addSimpleMessage("Maklumat telah berjaya disimpan");

        } else {
            LOG.info("--- listPelaksana.isEmpty() && listPelaksana == 0 ---");
            addSimpleError("Sila isikan maklumat pelaksana waran dengan menekan butang 'Tambah'.");
            return new JSP("strata/waran/PelaksanaWaran.jsp").addParameter("tab", "true");

        }

        return new JSP("strata/waran/PelaksanaWaran.jsp").addParameter("tab", "true");
    }

    public Date getTarikhPelantikan() {
        return tarikhPelantikan;
    }

    public void setTarikhPelantikan(Date tarikhPelantikan) {
        this.tarikhPelantikan = tarikhPelantikan;
    }

    public List<PelaksanaWaran> getListPelaksana() {
        return listPelaksana;
    }

    public void setListPelaksana(List<PelaksanaWaran> listPelaksana) {
        this.listPelaksana = listPelaksana;
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

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public PelaksanaWaran getPelaksanaWaran() {
        return pelaksanaWaran;
    }

    public void setPelaksanaWaran(PelaksanaWaran pelaksanaWaran) {
        this.pelaksanaWaran = pelaksanaWaran;
    }

    public Pengguna getPeng() {
        return peng;
    }

    public void setPeng(Pengguna peng) {
        this.peng = peng;
    }

    public Permohonan getPermohonan() {
        return permohonan;
    }

    public void setPermohonan(Permohonan permohonan) {
        this.permohonan = permohonan;
    }

    public String getPoskod() {
        return poskod;
    }

    public void setPoskod(String poskod) {
        this.poskod = poskod;
    }

    public String getNegeri() {
        return negeri;
    }

    public void setNegeri(String negeri) {
        this.negeri = negeri;
    }

    public String getJenisKP() {
        return jenisKP;
    }

    public void setJenisKP(String jenisKP) {
        this.jenisKP = jenisKP;
    }

    public String getNoPengenalan() {
        return noPengenalan;
    }

    public void setNoPengenalan(String noPengenalan) {
        this.noPengenalan = noPengenalan;
    }

    public String getKerja() {
        return kerja;
    }

    public void setKerja(String kerja) {
        this.kerja = kerja;
    }

    public boolean isReadOnly() {
        return readOnly;
    }

    public void setReadOnly(boolean readOnly) {
        this.readOnly = readOnly;
    }

    public String getIdPelaksana() {
        return idPelaksana;
    }

    public void setIdPelaksana(String idPelaksana) {
        this.idPelaksana = idPelaksana;
    }
}
