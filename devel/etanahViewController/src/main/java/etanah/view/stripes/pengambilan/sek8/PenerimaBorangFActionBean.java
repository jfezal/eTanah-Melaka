/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.stripes.pengambilan.sek8;

/**
 *
 * @author mohd.faidzal
 */
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.dao.BorangPerHakmilikDAO;
import etanah.dao.BorangPerPBDAO;
import etanah.dao.KodJenisPengenalanDAO;
import etanah.dao.KodNegeriDAO;
import etanah.dao.KodNotisDAO;
import etanah.dao.PermohonanDAO;
import etanah.model.Alamat;
import etanah.model.Dokumen;
import etanah.model.InfoAudit;
import etanah.model.KodJenisPengenalan;
import etanah.model.KodNegeri;
import etanah.model.KodNotis;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.model.ambil.BorangPerHakmilik;
import etanah.model.ambil.BorangPerPB;
import etanah.service.acq.service.BorangACQService;
import etanah.service.acq.service.BorangEFService;
import etanah.util.StringUtils;
import etanah.view.BasicTabActionBean;
import etanah.view.MaklumatSek4Form;
import etanah.view.etanahActionBeanContext;
import etanah.view.stripes.pengambilan.share.form.BorangEForm;
import etanah.view.stripes.pengambilan.share.form.BorangFForm;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import java.util.List;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.StreamingResolution;
import net.sourceforge.stripes.action.UrlBinding;
import org.codehaus.jackson.map.ObjectMapper;

@UrlBinding("/pengambilan/penerima_borang_f")
public class PenerimaBorangFActionBean extends BasicTabActionBean {

    @Inject
    BorangACQService borangACQService;
    @Inject
    BorangEFService borangEFService;
    @Inject
    BorangPerHakmilikDAO borangPerHakmilikDAO;
    @Inject
    KodJenisPengenalanDAO kodJenisPengenalanDAO;
    @Inject
    BorangPerPBDAO borangPerPBDAO;
    @Inject
    KodNegeriDAO kodNegeriDAO;
    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    KodNotisDAO kodNotisDAO;
    List<BorangEForm> listBorangE;
    List<BorangFForm> listBorangF;
    Dokumen dokumenBE;
    BorangPerHakmilik borangPerHakmilik;
    Permohonan permohonan;
    private Long idPerPB;
    private Long idBorangPerHm;
    private String jenis_kepentingan;
    private String nama;
    private String noPengenalan;
    private String jenisPengenalan;
    private String alamat1;
    private String alamat2;
    private String alamat3;
    private String alamat4;
    private String poskod;
    private String negeri;
    private Alamat alamat;

    @DefaultHandler
    public Resolution showForm() {
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = permohonanDAO.findById(idPermohonan);
        listBorangE = borangEFService.findHakmilikAktifNotTDK(idPermohonan);
        return new JSP("/pengambilan/APT/senarai_hakmilik_borang_d.jsp").addParameter("tab", "true");
    }

    public Resolution senaraiF() {
        listBorangF = new ArrayList<BorangFForm>();
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = permohonanDAO.findById(idPermohonan);
        String idHakmilik = (String) getContext().getRequest().getParameter("idHakmilik");
        String as = (String) getContext().getRequest().getParameter("idBorangPerHm");
        idBorangPerHm = Long.valueOf(as);
        borangPerHakmilik = borangPerHakmilikDAO.findById(idBorangPerHm);
        dokumenBE = borangPerHakmilik.getDokumen();
        listBorangF = borangEFService.listBorangF(idPermohonan, idHakmilik);
        return new JSP("/pengambilan/APT/pihak_borang_f.jsp").addParameter("popup", true);
    }

    public Resolution simpanBorangF() {
        Pengguna pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);

        BorangPerHakmilik bph = borangPerHakmilikDAO.findById(idBorangPerHm);
        BorangPerPB pb = null;
        if (idPerPB == null) {
            pb = new BorangPerPB();
        } else {
            pb = borangPerPBDAO.findById(idPerPB);
            if (pb != null) {
            } else {
                pb = new BorangPerPB();
            }
        }
        alamat = new Alamat();
        alamat.setAlamat1(alamat1);
        alamat.setAlamat2(alamat2);
        alamat.setAlamat3(alamat3);
        alamat.setAlamat4(alamat4);
        alamat.setPoskod(poskod);
        if (!StringUtils.isBlank(negeri)) {
            KodNegeri kodNegeri = kodNegeriDAO.findById(negeri);
            alamat.setNegeri(kodNegeri);
        }
        pb.setAlamat(alamat);
        pb.setBorangPerHakmilik(bph);
        if (!StringUtils.isBlank(jenisPengenalan)) {
            KodJenisPengenalan jenis = kodJenisPengenalanDAO.findById(jenisPengenalan);
            pb.setJenisPengenalan(jenis);
        }
        pb.setJenis_kepentingan(jenis_kepentingan);
        KodNotis kodNotis = kodNotisDAO.findById("NBF");
        pb.setKodNotis(kodNotis);
        pb.setNama(nama);
        pb.setNoPengenalan(noPengenalan);
        pb.setInfoAudit(setInfoAudit(pengguna));
        borangACQService.saveBorangPerPB(pb);
        return new JSP("/pengambilan/APT/pihak_borang_f.jsp").addParameter("popup", true);

    }

    public Resolution kemaskiniBorangpb() throws IOException {
        String idBorangpb = getContext().getRequest().getParameter("idBorangpb");
        BorangPerPB pb = borangPerPBDAO.findById(Long.valueOf(idBorangpb));
        BorangFForm form = new BorangFForm();
        ObjectMapper map = new ObjectMapper();
        form.setIdPerPB(pb.getId());
        form.setNama(pb.getNama());
        if (pb.getAlamat() != null) {
            form.setAlamat1(pb.getAlamat().getAlamat1());
            form.setAlamat2(pb.getAlamat().getAlamat2());
            form.setAlamat3(pb.getAlamat().getAlamat3());
            form.setAlamat4(pb.getAlamat().getAlamat4());
            form.setPoskod(pb.getAlamat().getPoskod());
            form.setNegeri(pb.getAlamat().getNegeri() != null ? pb.getAlamat().getNegeri().getKod() : "");
        }
        form.setJenisPengenalan(pb.getJenisPengenalan()!= null ? pb.getJenisPengenalan().getKod():"");
        form.setNoPengenalan(pb.getNoPengenalan());
        form.setJenis_kepentingan(pb.getJenis_kepentingan());

        map.writeValueAsString(form);
//        JSONObject obj = new JSONObject(form);
        return new StreamingResolution("application/json", map.writeValueAsString(form));
    }

    public InfoAudit setInfoAudit(Pengguna p) {
        InfoAudit ia = new InfoAudit();
        ia.setDimasukOleh(p);
        ia.setTarikhMasuk(new Date());
        return ia;
    }

    public List<BorangEForm> getListBorangE() {
        return listBorangE;
    }

    public void setListBorangE(List<BorangEForm> listBorangE) {
        this.listBorangE = listBorangE;
    }

    public List<BorangFForm> getListBorangF() {
        return listBorangF;
    }

    public void setListBorangF(List<BorangFForm> listBorangF) {
        this.listBorangF = listBorangF;
    }

    public Long getIdBorangPerHm() {
        return idBorangPerHm;
    }

    public void setIdBorangPerHm(Long idBorangPerHm) {
        this.idBorangPerHm = idBorangPerHm;
    }

    public String getJenis_kepentingan() {
        return jenis_kepentingan;
    }

    public void setJenis_kepentingan(String jenis_kepentingan) {
        this.jenis_kepentingan = jenis_kepentingan;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getNoPengenalan() {
        return noPengenalan;
    }

    public void setNoPengenalan(String noPengenalan) {
        this.noPengenalan = noPengenalan;
    }

    public String getJenisPengenalan() {
        return jenisPengenalan;
    }

    public void setJenisPengenalan(String jenisPengenalan) {
        this.jenisPengenalan = jenisPengenalan;
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

    public Alamat getAlamat() {
        return alamat;
    }

    public void setAlamat(Alamat alamat) {
        this.alamat = alamat;
    }

    public Dokumen getDokumenBE() {
        return dokumenBE;
    }

    public void setDokumenBE(Dokumen dokumenBE) {
        this.dokumenBE = dokumenBE;
    }

    public Long getIdPerPB() {
        return idPerPB;
    }

    public void setIdPerPB(Long idPerPB) {
        this.idPerPB = idPerPB;
    }

    public BorangPerHakmilik getBorangPerHakmilik() {
        return borangPerHakmilik;
    }

    public void setBorangPerHakmilik(BorangPerHakmilik borangPerHakmilik) {
        this.borangPerHakmilik = borangPerHakmilik;
    }

    public Permohonan getPermohonan() {
        return permohonan;
    }

    public void setPermohonan(Permohonan permohonan) {
        this.permohonan = permohonan;
    }

}
