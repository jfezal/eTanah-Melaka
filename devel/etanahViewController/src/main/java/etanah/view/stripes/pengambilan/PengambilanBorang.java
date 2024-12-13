/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.stripes.pengambilan;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.dao.PermohonanDAO;
import etanah.dao.HakmilikPerbicaraanDAO;
import etanah.model.KodCawangan;
import etanah.model.Permohonan;
import etanah.model.Notis;
import etanah.model.Dokumen;
import etanah.model.ambil.HakmilikPerbicaraan;
import etanah.model.InfoAudit;
import etanah.model.Pengguna;
import etanah.model.KodNotis;
import etanah.model.HakmilikPermohonan;
import etanah.service.PengambilanService;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;
import org.apache.commons.lang.StringUtils;
import etanah.view.etanahActionBeanContext;
import net.sourceforge.stripes.action.ForwardResolution;
import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.service.common.NotisPenerimaanService;
import etanah.service.common.PerbicaraanService;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.HttpCache;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;

/**
 *
 * @author nordiyana
 */
@UrlBinding("/borangpengambilanmodule")
public class PengambilanBorang extends AbleActionBean {

    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    HakmilikPerbicaraanDAO hakmilikPerbicaraanDAO;
    @Inject
    NotisPenerimaanService notisPenerimaanService;
    @Inject
    private PerbicaraanService perbicaraanService;
    private Permohonan permohonan;
    private HakmilikPermohonan hakmilikPermohonan;
    private HakmilikPerbicaraan hakmilikPerbicaraan;
    private Notis notis;
    private Dokumen dokumenNotis;
    private KodNotis kodNotis;
    private List<String> tempohKeteranganBertulis = new ArrayList<String>();
    private String idHakmilik;
    private List<HakmilikPermohonan> hakmilikPermohonanList;
    private List<HakmilikPerbicaraan> bicaraList;
    private List<String> test1 = new ArrayList<String>();

    public List<String> getTest1() {
        return test1;
    }

    public void setTest1(List<String> test1) {
        this.test1 = test1;
    }
    @Inject
    PengambilanService service;
    private String tarikhNotis;
    ArrayList listtempoh = new ArrayList();
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

    @DefaultHandler
    public Resolution showForm() {
        return new JSP("pengambilan/borangA.jsp").addParameter("tab", "true");
    }

    public Resolution showForm2() {
        return new JSP("pengambilan/BorangB.jsp").addParameter("tab", "true");
    }

    public Resolution showForm3() {
        return new JSP("pengambilan/BorangE.jsp").addParameter("tab", "true");
    }

    public Resolution showForm4() {
        return new JSP("pengambilan/BorangF.jsp").addParameter("tab", "true");
    }

    public Resolution showForm5() {
        return new JSP("pengambilan/BorangL.jsp").addParameter("tab", "true");
    }

    public Resolution showForm6() {
        return new JSP("pengambilan/BorangK.jsp").addParameter("tab", "true");
    }

    public Resolution showForm7() {
        return new JSP("pengambilan/pengesahan_BorangGH.jsp").addParameter("tab", "true");
    }

    public Resolution showForm8() {
        return new JSP("pengambilan/penerimaannotis_borang.jsp").addParameter("tab", "true");
    }

    public Resolution showForm9() {
        return new JSP("pengambilan/BorangI.jsp").addParameter("tab", "true");
    }

    public Resolution showForm10() {
        return new JSP("pengambilan/rekodmaklumat_penerimaanbayaran.jsp").addParameter("tab", "true");
    }

    public Resolution showForm11() {
        return new JSP("pengambilan/BorangQ.jsp").addParameter("tab", "true");
    }

    public Resolution showForm12() {
        return new JSP("pengambilan/BorangO.jsp").addParameter("tab", "true");
    }

    public Resolution showForm13() {
        return new JSP("pengambilan/BorangN.jsp").addParameter("tab", "true");
    }

    public Resolution paparanSenaraiPerserahan() {
        return new JSP("daftar/common/senarai_perserahan.jsp").addParameter("tab", "true");
    }

    public Resolution paparanSurat() {
        return new JSP("daftar/common/paparan_surat.jsp").addParameter("tab", "true");
    }

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {
        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        idHakmilik = (String) getContext().getRequest().getParameter("idHakmilik");

        if (idHakmilik != null) {
            getContext().getRequest().getSession().setAttribute("idHakmilik", idHakmilik);
        }
        if (idPermohonan != null) {
            Permohonan p = permohonanDAO.findById(idPermohonan);
            hakmilikPermohonanList = p.getSenaraiHakmilik();
            for (int i = 0; i < hakmilikPermohonanList.size(); i++) {

                HakmilikPermohonan hmp = hakmilikPermohonanList.get(i);
                hakmilikPerbicaraan = perbicaraanService.getHakmilikPerbicaraanByIdMH(hmp.getId());
                try {
                    String lb = Integer.toString(hakmilikPerbicaraan.getTempohKeteranganBertulis());
                    tempohKeteranganBertulis.add(lb);
                } catch (Exception e) {
                }
            }
//             for (int i = 0; i < hakmilikPermohonanList.size(); i++) {
//
//            try{
//                 String tempohBertulis = hakmilikPermohonanList.get(i).getLuasTerlibat();
//                luasTerlibat.add(luas.toString());
//            }catch(Exception e){
//
//            }
//             }

        }
    }

    public Resolution saveborangf() {
        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
//        InfoAudit info = peng.getInfoAudit();
        Permohonan permohonan = permohonanDAO.findById(idPermohonan);
        // HakmilikPerbicaraan hp= hakmilikPerbicaraanDAO
        KodCawangan cawangan = permohonan.getCawangan();
        for (int i = 0; i < hakmilikPermohonanList.size(); i++) {
            HakmilikPerbicaraan hakmilikPerb = new HakmilikPerbicaraan();;

            HakmilikPermohonan hmp = hakmilikPermohonanList.get(i);
            hakmilikPerbicaraan = notisPenerimaanService.getHakmilikPerbicaraanByidMH(hmp.getId());
            //  if (perbicaraanService.getHakmilikPerbicaraanByIdMH(hmp.getId()) == null) {
            if (hakmilikPerbicaraan == null) {
                //     if(perbicaraanService.getHakmilikPerbicaraanByIdMH(hmp.getId()) == null) {
                hakmilikPerb = new HakmilikPerbicaraan();
                InfoAudit info = new InfoAudit();
                info.setDimasukOleh(peng);
                info.setTarikhMasuk(new java.util.Date());
                hakmilikPerb.setInfoAudit(info);
                hakmilikPerb.setCawangan(cawangan);
                hakmilikPerb.setHakmilikPermohonan(hmp);

            } else {
                hakmilikPerb = perbicaraanService.getHakmilikPerbicaraanByIdMH(hmp.getId());
                InfoAudit ia = hakmilikPerb.getInfoAudit();
                ia.setDiKemaskiniOleh(peng);
                ia.setTarikhKemaskini(new java.util.Date());
                hakmilikPerb.setInfoAudit(ia);
            }


            try {
                if (i < tempohKeteranganBertulis.size()) {
                    if (tempohKeteranganBertulis.get(i) != null) {
                        hakmilikPerb.setTempohKeteranganBertulis(Integer.valueOf(tempohKeteranganBertulis.get(i)));
                        test1.add(tempohKeteranganBertulis.get(i));
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
                addSimpleError("Invalid Data");
            }
            service.saveSingleHakmilikPerbicaraan(hakmilikPerb);
        }
        addSimpleMessage("Maklumat telah berjaya disimpan.");
        return new ForwardResolution("/WEB-INF/jsp/pengambilan/BorangF.jsp").addParameter("tab", "true");
    }

    public List<HakmilikPerbicaraan> getBicaraList() {
        return bicaraList;
    }

    public void setBicaraList(List<HakmilikPerbicaraan> bicaraList) {
        this.bicaraList = bicaraList;
    }

    public List<HakmilikPermohonan> getHakmilikPermohonanList() {
        return hakmilikPermohonanList;
    }

    public void setHakmilikPermohonanList(List<HakmilikPermohonan> hakmilikPermohonanList) {
        this.hakmilikPermohonanList = hakmilikPermohonanList;
    }

    public String getTarikhNotis() {
        return tarikhNotis;
    }

    public void setTarikhNotis(String tarikhNotis) {
        this.tarikhNotis = tarikhNotis;
    }

    public Notis getNotis() {
        return notis;
    }

    public void setNotis(Notis notis) {
        this.notis = notis;
    }

    public Permohonan getPermohonan() {
        return permohonan;
    }

    public void setPermohonan(Permohonan permohonan) {
        this.permohonan = permohonan;
    }

    public Dokumen getDokumenNotis() {
        return dokumenNotis;
    }

    public void setDokumenNotis(Dokumen dokumenNotis) {
        this.dokumenNotis = dokumenNotis;
    }

    public KodNotis getKodNotis() {
        return kodNotis;
    }

    public void setKodNotis(KodNotis kodNotis) {
        this.kodNotis = kodNotis;
    }

    public HakmilikPermohonan getHakmilikPermohonan() {
        return hakmilikPermohonan;
    }

    public void setHakmilikPermohonan(HakmilikPermohonan hakmilikPermohonan) {
        this.hakmilikPermohonan = hakmilikPermohonan;
    }

    public String getIdHakmilik() {
        return idHakmilik;
    }

    public void setIdHakmilik(String idHakmilik) {
        this.idHakmilik = idHakmilik;
    }

    public List<String> getTempohKeteranganBertulis() {
        return tempohKeteranganBertulis;
    }

    public void setTempohKeteranganBertulis(List<String> tempohKeteranganBertulis) {
        this.tempohKeteranganBertulis = tempohKeteranganBertulis;
    }
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public HakmilikPerbicaraan getHakmilikPerbicaraan() {
        return hakmilikPerbicaraan;
    }

    public void setHakmilikPerbicaraan(HakmilikPerbicaraan hakmilikPerbicaraan) {
        this.hakmilikPerbicaraan = hakmilikPerbicaraan;
    }
}
