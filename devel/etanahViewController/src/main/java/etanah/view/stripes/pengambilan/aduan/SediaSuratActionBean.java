/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.stripes.pengambilan.aduan;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.dao.KodNotisDAO;
import etanah.dao.KodPeringkatDAO;
import etanah.dao.NotaSiasatanLengkapDAO;
import etanah.dao.PemohonDAO;
import etanah.dao.PermohonanDAO;
import etanah.model.Alamat;
import etanah.model.AlamatSurat;
import etanah.model.HakmilikPermohonan;
import etanah.model.InfoAudit;
import etanah.model.KodNotis;
import etanah.model.KodPeringkat;
import etanah.model.Pemohon;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.model.ambil.BorangPerHakmilik;
import etanah.model.ambil.BorangPerPB;
import etanah.model.ambil.NotaSiasatan;
import etanah.model.ambil.NotaSiasatanLengkap;
import etanah.model.ambil.TuntutanPerPB;
import etanah.service.common.HakmilikPermohonanService;
import etanah.service.pengambilan.aduan.AduanService;
import etanah.view.etanahActionBeanContext;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;

/**
 *
 * @author user
 */
@UrlBinding("/pengambilan/aduan_kerosakan/sedia_surat")
public class SediaSuratActionBean extends AbleActionBean {

    @Inject
    AduanService aduanService;
    @Inject
    PemohonDAO pemohonDAO;
    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    KodPeringkatDAO kodPeringkatDAO;
    @Inject
    KodNotisDAO kodNotisDAO;

    String tarikhRundingan;
    String masaRundingan;
    String lokasi;
    String keterangan;
    String noBicara;
    private String idPermohonan;
    private NotaSiasatan notaSiasatan;
    private List<NotaSiasatanLengkap> notaSiasatanLengkap;
    List<BorangPerHakmilik> listBorangPerHakmilik = new ArrayList<BorangPerHakmilik>();
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

    @DefaultHandler
    public Resolution showForm() {

        System.out.println("inside show form SediaSuratActionBean ");
        listBorangPerHakmilik = aduanService.findListByIdPermohonan(idPermohonan);
        return new JSP("pengambilan/aduan_kerosakan/sediaSurat.jsp").addParameter("tab", "true");

    }

    public Resolution simpan() throws ParseException {

        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        Permohonan permohonan = permohonanDAO.findById(idPermohonan);
        KodPeringkat kod = kodPeringkatDAO.findById("NOTAS");
        List<Pemohon> listPemohon = permohonan.getSenaraiPemohon();
        NotaSiasatan nota = aduanService.findByIdMohon(idPermohonan);
        if (nota != null) {
        } else {
            nota = new NotaSiasatan();
        }
        nota.setPermohonan(permohonan);
        nota.setKodPeringkat(kod);
        nota.setInfoAudit(setIA());
        aduanService.saveNotaSiasatan(nota);
        for (Pemohon p : listPemohon) {
            if (p.getHakmilik() != null) {
                HakmilikPermohonan hp = aduanService.findByIdMohonIdHakmilik(permohonan.getIdPermohonan(), p.getHakmilik().getIdHakmilik());

                BorangPerHakmilik bph = aduanService.findByIdMHNBE(hp.getId(), "NBE");
                if (bph == null) {
                    bph = new BorangPerHakmilik();
                } else {
                    deleteChild(bph, nota);
                }
                bph.setHakmilikPermohonan(hp);
                KodNotis kodNotis = kodNotisDAO.findById("NBE");
                bph.setKodNotis(kodNotis);
                bph.setInfoAudit(setIA());
                aduanService.saveBorangPerHakmilik(bph);
//            List<BorangPerPB> listpb =aduanService.findByBPH(bH.getId()) 

                BorangPerPB pb = new BorangPerPB();
                pb.setNama(p.getNama());
                Alamat alamat = new Alamat();
                if (p.getAlamat() != null) {
                    alamat = p.getAlamat();
                    pb.setAlamat(alamat);
                }

                pb.setBorangPerHakmilik(bph);
//            pb.setIdHakmilik(hp.getHakmilik().getIdHakmilik());
                pb.setJenisPengenalan(p.getJenisPengenalan());
                pb.setJenis_kepentingan(p.getJenisPemohon());
                pb.setNoPengenalan(p.getNoPengenalan());
                aduanService.saveBorangPerPB(pb);
                NotaSiasatanLengkap l = aduanService.findNotaLengkapByNotaIdMohon(nota.getIdNota(), pb.getId());
                if (l == null) {
                    l = new NotaSiasatanLengkap();
                }
                l.setBorangPerPB(pb);
                l.setKeteranganicara(keterangan);
                l.setMasaBicara(masaRundingan);
                l.setNoBicara(noBicara);
                l.setTarikhBicara(sdf.parse(tarikhRundingan));
                l.setTempatBicara(lokasi);
                l.setNotaSiasatan(nota);
                aduanService.saveNotaSiasatanLengkap(l);
            }

        }

        return new JSP("pengambilan/aduan_kerosakan/sediaSurat.jsp").addParameter("tab", "true");

    }

    private InfoAudit setIA() {
        InfoAudit ia = new InfoAudit();
        Pengguna pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);

        ia.setDimasukOleh(pengguna);
        ia.setTarikhMasuk(new Date());
        return ia;
    }

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        notaSiasatan = aduanService.findByIdMohon(idPermohonan);
        if (notaSiasatan != null) {
            notaSiasatanLengkap = aduanService.findNotaLengkapByNotaId(notaSiasatan.getIdNota());
        }

    }

    public String getTarikhRundingan() {
        return tarikhRundingan;
    }

    public void setTarikhRundingan(String tarikhRundingan) {
        this.tarikhRundingan = tarikhRundingan;
    }

    public String getMasaRundingan() {
        return masaRundingan;
    }

    public void setMasaRundingan(String masaRundingan) {
        this.masaRundingan = masaRundingan;
    }

    public String getLokasi() {
        return lokasi;
    }

    public void setLokasi(String lokasi) {
        this.lokasi = lokasi;
    }

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }

    public String getNoBicara() {
        return noBicara;
    }

    public void setNoBicara(String noBicara) {
        this.noBicara = noBicara;
    }

    public String getIdPermohonan() {
        return idPermohonan;
    }

    public void setIdPermohonan(String idPermohonan) {
        this.idPermohonan = idPermohonan;
    }

    private void deleteChild(BorangPerHakmilik bph, NotaSiasatan ns) {
        List<BorangPerPB> listBorangPerPB = bph.getSenaraiBorangPerPB();
        List<NotaSiasatanLengkap> listNota = ns.getSenaraiNotaSiasatanLengkap();
        for (NotaSiasatanLengkap n : listNota) {
            aduanService.deleteNotaSiasatanLengkap(n);
        }

        for (BorangPerPB pb : listBorangPerPB) {
            List<TuntutanPerPB> listTuntutan = pb.getSenaraiTuntutanPerPB();
            if (listTuntutan != null) {
                for (TuntutanPerPB tuntutanPerPB : listTuntutan) {
                    aduanService.deleteTuntutanPerPB(tuntutanPerPB);
                }
            }

            aduanService.deleteBorangPerPB(pb);
        }
    }

    public List<BorangPerHakmilik> getListBorangPerHakmilik() {
        return listBorangPerHakmilik;
    }

    public void setListBorangPerHakmilik(List<BorangPerHakmilik> listBorangPerHakmilik) {
        this.listBorangPerHakmilik = listBorangPerHakmilik;
    }

    public NotaSiasatan getNotaSiasatan() {
        return notaSiasatan;
    }

    public void setNotaSiasatan(NotaSiasatan notaSiasatan) {
        this.notaSiasatan = notaSiasatan;
    }

    public List<NotaSiasatanLengkap> getNotaSiasatanLengkap() {
        return notaSiasatanLengkap;
    }

    public void setNotaSiasatanLengkap(List<NotaSiasatanLengkap> notaSiasatanLengkap) {
        this.notaSiasatanLengkap = notaSiasatanLengkap;
    }

}
