
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.stripes.pengambilan;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.dao.DokumenDAO;
import etanah.dao.HakmilikDAO;
import etanah.dao.KodStatusTerimaDAO;
import etanah.dao.PermohonanDAO;
import etanah.model.Dokumen;
import etanah.model.Hakmilik;
import etanah.model.HakmilikPermohonan;
import etanah.model.InfoAudit;
import etanah.model.KodCawangan;
import etanah.model.KodStatusTerima;
import etanah.model.Notis;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.model.PermohonanPihak;
import etanah.model.ambil.HakmilikPerbicaraan;
import etanah.service.common.PermohonanPihakService;
import etanah.service.common.DokumenService;
//import etanah.service.common.NotisPenerimaanService;
import etanah.service.common.PerbicaraanService;
import etanah.view.etanahActionBeanContext;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.RedirectResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;

/*
 * Edited by  : Rajesh
 */
@UrlBinding("/pengambilan/penerimaan_KeputusanMahkamah")
public class PenerimaanKeputusanMahkamhActionBean extends AbleActionBean {

    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    HakmilikDAO hakmilikDAO;
    @Inject
    DokumenDAO dokumenDAO;
    @Inject
    KodStatusTerimaDAO kodStatusTerimaDAO;
//    @Inject
//    NotisPenerimaanService notisPenerimaanService;
    private Hakmilik hakmilik;
    private HakmilikPerbicaraan hakmilikPerbicaraan;
    private List<HakmilikPerbicaraan> hakmilikPerbicaraanList;
    private DokumenService dokumenService;
    private PermohonanPihakService permohonanPihakService;
    @Inject
    private PerbicaraanService perbicaraanService;
    private String idPermohonan;
    private String idHakmilik;
    private String idPihak;
    private String idDokumen;
    private String tajuk;
    private String tarikhNotis;
    private String kodStatusTerima;
    private String tarikhHantar;
    private String tarikhTampal;
    private List<PermohonanPihak> permohonanPihakList;
    private List<HakmilikPermohonan> hakmilikPermohonanList;
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    private List<String> tarikhBicara = new ArrayList<String>();
    private List<String> waktuPerbicaraan = new ArrayList<String>();
    private List<BigDecimal> amaunDituntut = new ArrayList<BigDecimal>();

    @DefaultHandler
    public Resolution showForm() {
        return new JSP("pengambilan/melaka/pendudukansementara/penerimaanKeputusanMahkamah.jsp").addParameter("tab", "true");
    }

    public Resolution hakmilikDetails() {
        hakmilik = hakmilikDAO.findById(idHakmilik);
        return new JSP("pengambilan/melaka/pendudukansementara/penerimaanKeputusanMahkamah.jsp").addParameter("tab", "true");
    }

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");

        if (idPermohonan != null) {
            Permohonan p = permohonanDAO.findById(idPermohonan);
            permohonanPihakList = p.getSenaraiPihak();
            hakmilikPermohonanList = p.getSenaraiHakmilik();

            for (int i = 0; i < hakmilikPermohonanList.size(); i++) {
                HakmilikPermohonan hmp = hakmilikPermohonanList.get(i);
                hakmilikPerbicaraan = perbicaraanService.getHakmilikPerbicaraanByIdMHByTMPTBicara(hmp.getId(), "Mahkamah Tinggi");
                if (hakmilikPerbicaraan != null) {
                    try {
                        amaunDituntut.add(hakmilikPerbicaraan.getAmaunDituntut());
                        tarikhBicara.add(sdf.format(hakmilikPerbicaraan.getTarikhBicara()));
                    } catch (Exception e) {
                    }
                }
            }
        }
    }

    public Resolution refreshpage() {
        rehydrate();
        return new RedirectResolution(PenerimaanKeputusanMahkamhActionBean.class, "locate");
    }

    public Resolution simpan() throws ParseException {

        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        InfoAudit info = new InfoAudit();

        Permohonan permohonan = permohonanDAO.findById(idPermohonan);
        //hakmilik = hakmilikDAO.findById(idHakmilik);


        if (idPermohonan != null) {
            Notis notis = new Notis();
            info.setDimasukOleh(peng);
            info.setTarikhMasuk(new java.util.Date());
            notis.setInfoAudit(info);
            notis.setPermohonan(permohonan);
            notis.setPihak(null);


            Dokumen dokumen = new Dokumen();
            dokumen.setTajuk(tajuk);
            dokumen.setInfoAudit(info);
            Dokumen dokumen1 = dokumenService.saveOrUpdate(dokumen);

            notis.setDokumenNotis(dokumen1);
            notis.setTarikhNotis(sdf.parse(tarikhNotis));

            KodStatusTerima kodStatusTerima1 = kodStatusTerimaDAO.findById(kodStatusTerima);

            notis.setKodStatusTerima(kodStatusTerima1);
            notis.setTarikhHantar(sdf.parse(tarikhHantar));
            notis.setTarikhTampal(sdf.parse(tarikhTampal));
//            notisPenerimaanService.saveOrUpdateNotis(notis);
            addSimpleMessage("Maklumat telah berjaya disimpan");
        }
        return new ForwardResolution("/WEB-INF/jsp/pengambilan/melaka/pendudukansementara/penerimaanKeputusanMahkamah.jsp").addParameter("tab", "true");
    }

    public Resolution editDetails() {

        String rowCount = (String) getContext().getRequest().getParameter("rowCount");

        int rowCountval = Integer.parseInt(rowCount);


        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        InfoAudit info = peng.getInfoAudit();
        Permohonan permohonan = permohonanDAO.findById(idPermohonan);

        KodCawangan cawangan = permohonan.getCawangan();

        HakmilikPermohonan hmp = hakmilikPermohonanList.get(rowCountval);
        HakmilikPerbicaraan hakmilikPerbicaraan = new HakmilikPerbicaraan();
        info.setDimasukOleh(peng);
        info.setTarikhMasuk(new java.util.Date());
        hakmilikPerbicaraan.setInfoAudit(info);
        hakmilikPerbicaraan.setCawangan(cawangan);
        hakmilikPerbicaraan.setHakmilikPermohonan(hmp);

        try {
            if (amaunDituntut.get(rowCountval) != null) {
                hakmilikPerbicaraan.setAmaunDituntut(amaunDituntut.get(rowCountval));
            }

            if (tarikhBicara.get(rowCountval) != null) {
                String StrTarikhBicara = tarikhBicara.get(rowCountval);
                hakmilikPerbicaraan.setTarikhBicara(sdf.parse(StrTarikhBicara));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


        return new ForwardResolution("/WEB-INF/jsp/pengambilan/melaka/pendudukansementara/penerimaanKeputusanMahkamah.jsp").addParameter("tab", "true");


    }

    public Resolution simpanHakmilik() {
        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);

        Permohonan permohonan = permohonanDAO.findById(idPermohonan);
        InfoAudit info;


        for (int i = 0; i < hakmilikPermohonanList.size(); i++) {
            HakmilikPermohonan hmp = hakmilikPermohonanList.get(i);
            hakmilikPerbicaraan = perbicaraanService.getHakmilikPerbicaraanByIdMHByTMPTBicara(hmp.getId(), "Mahkamah Tinggi");
            if (hakmilikPerbicaraan == null) {
                hakmilikPerbicaraan = new HakmilikPerbicaraan();
                hakmilikPerbicaraan.setLokasiBicara("Mahkamah Tinggi");
                info = new InfoAudit();
                info.setDimasukOleh(peng);
                info.setTarikhMasuk(new java.util.Date());
                hakmilikPerbicaraan.setInfoAudit(info);
                hakmilikPerbicaraan.setCawangan(permohonan.getCawangan());
                hakmilikPerbicaraan.setHakmilikPermohonan(hmp);
                hakmilikPerbicaraan.setBatalRizab('T');
                hakmilikPerbicaraan.setKawasanPBT('T');
                hakmilikPerbicaraan.setPelanPembangunan('T');
            } else {
                info = hakmilikPerbicaraan.getInfoAudit();
                info.setDiKemaskiniOleh(peng);
                info.setTarikhKemaskini(new java.util.Date());
                hakmilikPerbicaraan.setInfoAudit(info);
            }

            try {
                if (i < amaunDituntut.size()) {
                    if (amaunDituntut.get(i) != null) {
                        hakmilikPerbicaraan.setAmaunDituntut(amaunDituntut.get(i));
                    }
                }
                if (i < tarikhBicara.size()) {
                    if (tarikhBicara.get(i) != null) {
                        hakmilikPerbicaraan.setTarikhBicara(sdf.parse(tarikhBicara.get(i)));
                    }
                }
            } catch (Exception e) {
                addSimpleError("Invalid Data");
            }
            perbicaraanService.saveSingleHakmilikPerbicaraan(hakmilikPerbicaraan);
        }

        addSimpleMessage("Maklumat telah berjaya disimpan.");
        return new ForwardResolution("/WEB-INF/jsp/pengambilan/melaka/pendudukansementara/penerimaanKeputusanMahkamah.jsp").addParameter("tab", "true");
    }

    public List<PermohonanPihak> getPermohonanPihakList() {
        return permohonanPihakList;
    }

    public void setPermohonanPihakList(List<PermohonanPihak> permohonanPihakList) {
        this.permohonanPihakList = permohonanPihakList;
    }

    public List<HakmilikPermohonan> getHakmilikPermohonanList() {
        return hakmilikPermohonanList;
    }

    public void setHakmilikPermohonanList(List<HakmilikPermohonan> hakmilikPermohonanList) {
        this.hakmilikPermohonanList = hakmilikPermohonanList;
    }

    public Hakmilik getHakmilik() {
        return hakmilik;
    }

    public void setHakmilik(Hakmilik hakmilik) {
        this.hakmilik = hakmilik;
    }

    public String getIdHakmilik() {
        return idHakmilik;
    }

    public void setIdHakmilik(String idHakmilik) {
        this.idHakmilik = idHakmilik;
    }

    public String getIdDokumen() {
        return idDokumen;
    }

    public void setIdDokumen(String idDokumen) {
        this.idDokumen = idDokumen;
    }

    public String getIdPihak() {
        return idPihak;
    }

    public void setIdPihak(String idPihak) {
        this.idPihak = idPihak;
    }

    public String getKodStatusTerima() {
        return kodStatusTerima;
    }

    public void setKodStatusTerima(String kodStatusTerima) {
        this.kodStatusTerima = kodStatusTerima;
    }

    public String getTajuk() {
        return tajuk;
    }

    public void setTajuk(String tajuk) {
        this.tajuk = tajuk;
    }

    public String getTarikhHantar() {
        return tarikhHantar;
    }

    public void setTarikhHantar(String tarikhHantar) {
        this.tarikhHantar = tarikhHantar;
    }

    public String getTarikhNotis() {
        return tarikhNotis;
    }

    public void setTarikhNotis(String tarikhNotis) {
        this.tarikhNotis = tarikhNotis;
    }

    public String getTarikhTampal() {
        return tarikhTampal;
    }

    public void setTarikhTampal(String tarikhTampal) {
        this.tarikhTampal = tarikhTampal;
    }

    public List<BigDecimal> getAmaunDituntut() {
        return amaunDituntut;
    }

    public void setAmaunDituntut(List<BigDecimal> amaunDituntut) {
        this.amaunDituntut = amaunDituntut;
    }

    public String getIdPermohonan() {
        return idPermohonan;
    }

    public void setIdPermohonan(String idPermohonan) {
        this.idPermohonan = idPermohonan;
    }

    public List<String> getTarikhBicara() {
        return tarikhBicara;
    }

    public void setTarikhBicara(List<String> tarikhBicara) {
        this.tarikhBicara = tarikhBicara;
    }

    public List<String> getWaktuPerbicaraan() {
        return waktuPerbicaraan;
    }

    public void setWaktuPerbicaraan(List<String> waktuPerbicaraan) {
        this.waktuPerbicaraan = waktuPerbicaraan;
    }

    public HakmilikPerbicaraan getHakmilikPerbicaraan() {
        return hakmilikPerbicaraan;
    }

    public void setHakmilikPerbicaraan(HakmilikPerbicaraan hakmilikPerbicaraan) {
        this.hakmilikPerbicaraan = hakmilikPerbicaraan;
    }

    public List<HakmilikPerbicaraan> getHakmilikPerbicaraanList() {
        return hakmilikPerbicaraanList;
    }

    public void setHakmilikPerbicaraanList(List<HakmilikPerbicaraan> hakmilikPerbicaraanList) {
        this.hakmilikPerbicaraanList = hakmilikPerbicaraanList;
    }
}
