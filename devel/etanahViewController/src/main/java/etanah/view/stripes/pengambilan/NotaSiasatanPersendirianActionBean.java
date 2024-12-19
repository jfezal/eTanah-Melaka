/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package etanah.view.stripes.pengambilan;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.dao.HakmilikDAO;
import etanah.dao.PermohonanDAO;
import etanah.model.Hakmilik;
import etanah.model.HakmilikPermohonan;
import etanah.model.HakmilikPihakBerkepentingan;
import etanah.model.InfoAudit;
import etanah.model.KodCawangan;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.model.PermohonanPihak;
import etanah.model.ambil.HakmilikPerbicaraan;
import etanah.model.ambil.PerbicaraanKehadiran;
import etanah.service.PermohonanSupayaBantahanService;
import etanah.service.common.NotisPenerimaanService;
import etanah.service.daftar.PembetulanService;
import etanah.view.etanahActionBeanContext;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;

/**
 *
 * @author Rajesh
 */
@UrlBinding("/pengambilan/nota_siasatan_persendirian")
public class NotaSiasatanPersendirianActionBean extends AbleActionBean {

    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    HakmilikDAO hakmilikDAO;
    @Inject
    private PembetulanService pembetulanService;
    @Inject
    NotisPenerimaanService notisPenerimaanService;
    @Inject
    private PermohonanSupayaBantahanService permohonanSupayaBantahanService;

    private Permohonan permohonan;
    private Hakmilik hakmilik;
    private HakmilikPerbicaraan hakmilikPerbicaraan;
    private HakmilikPermohonan hakmilikPermohonan;
    private PerbicaraanKehadiran perbicaraanKehadiran;
    private String idHakmilik;
    private String idPermohonan;
    private String penilaiUlasan;
    private List<HakmilikPermohonan> hakmilikPermohonanList;
    private List<PerbicaraanKehadiran> senaraiPerbicaraanKehadiran;
    private KodCawangan cawangan;
    private List<Character> hadir = new ArrayList<Character>();
    private List<String> keterangan = new ArrayList<String>();

    @DefaultHandler
    public Resolution showForm() {
        Pengguna pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        if(idPermohonan != null) {
            permohonan = permohonanDAO.findById(idPermohonan);
            if(permohonan != null) {
                permohonanSupayaBantahanService.simpanPermohonanPihak(permohonan, pengguna);
            }
        }
        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        return new JSP("pengambilan/negerisembilan/haklalulalangpersendirian/nota_siasatan_persendirian.jsp").addParameter("tab", "true");
    }

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {
        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
         idHakmilik = (String) getContext().getRequest().getParameter("idHakmilik");

            if (idPermohonan != null) {
                permohonan = permohonanDAO.findById(idPermohonan);
                hakmilikPermohonanList = permohonan.getSenaraiHakmilik();

                if(hakmilikPermohonan != null){
                hakmilikPerbicaraan = notisPenerimaanService.getHakmilikPerbicaraanByidMH(hakmilikPermohonan.getId());
            }
        }
    }

        public Resolution showPerintah() {
        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        idHakmilik = (String) getContext().getRequest().getParameter("idHakmilik");
        if(idHakmilik != null) {
            hakmilik = hakmilikDAO.findById(idHakmilik);
            hakmilikPermohonan = pembetulanService.findByIdHakmilikIdPermohonan(idPermohonan, idHakmilik);
            if(hakmilikPermohonan != null){
                hakmilikPerbicaraan = notisPenerimaanService.getHakmilikPerbicaraanByidMH(hakmilikPermohonan.getId());
                if(hakmilikPerbicaraan != null) {
                    senaraiPerbicaraanKehadiran = hakmilikPerbicaraan.getSenaraiKehadiran();
                }
            }
        }
        return new JSP("pengambilan/perintah.jsp").addParameter("popup", "true");
    }

    public Resolution simpan() throws ParseException {

        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        idHakmilik = (String) getContext().getRequest().getSession().getAttribute("idHakmilik");
        hakmilik = hakmilikDAO.findById(idHakmilik);
        Permohonan permohonan = permohonanDAO.findById(idPermohonan);
        cawangan = permohonan.getCawangan();
        hakmilikPermohonan = pembetulanService.findByIdHakmilikIdPermohonan(idPermohonan, idHakmilik);

        HakmilikPerbicaraan hakmilikPerbicaraanTemp;
        if(notisPenerimaanService.getHakmilikPerbicaraanByidMH(hakmilikPermohonan.getId()) == null) {
            hakmilikPerbicaraanTemp=new HakmilikPerbicaraan();
            InfoAudit info = new InfoAudit();
            info.setDimasukOleh(peng);
            info.setTarikhMasuk(new java.util.Date());
            hakmilikPerbicaraanTemp.setInfoAudit(info);
            hakmilikPerbicaraanTemp.setCawangan(cawangan);
            hakmilikPerbicaraanTemp.setHakmilikPermohonan(hakmilikPermohonan);
        }else{
            hakmilikPerbicaraanTemp = notisPenerimaanService.getHakmilikPerbicaraanByidMH(hakmilikPermohonan.getId());
            InfoAudit ia = hakmilikPerbicaraanTemp.getInfoAudit();
            ia.setDiKemaskiniOleh(peng);
            ia.setTarikhKemaskini(new java.util.Date());
            hakmilikPerbicaraanTemp.setInfoAudit(ia);
        }
//        hakmilikPerbicaraanTemp.setNoRujukan(hakmilikPerbicaraan.getNoRujukan());
        hakmilikPerbicaraanTemp.setDibicaraOleh(hakmilikPerbicaraan.getDibicaraOleh());
        hakmilikPerbicaraanTemp.setTarikhPemilikan(hakmilikPerbicaraan.getTarikhPemilikan());
        hakmilikPerbicaraanTemp.setCaraPemilikan(hakmilikPerbicaraan.getCaraPemilikan());
        hakmilikPerbicaraanTemp.setHargaPembelian(hakmilikPerbicaraan.getHargaPembelian());
        hakmilikPerbicaraanTemp.setLokasi(hakmilikPerbicaraan.getLokasi());
        hakmilikPerbicaraanTemp.setJarakKeBandar(hakmilikPerbicaraan.getJarakKeBandar());
        hakmilikPerbicaraanTemp.setKeadaanTanah(hakmilikPerbicaraan.getKeadaanTanah());
        hakmilikPerbicaraanTemp.setLokasiTerkini(hakmilikPerbicaraan.getLokasiTerkini());
        hakmilikPerbicaraanTemp.setUjudGPPJ(hakmilikPerbicaraan.getUjudGPPJ());
        hakmilikPerbicaraanTemp.setKomenGPPJ(hakmilikPerbicaraan.getKomenGPPJ());
        hakmilikPerbicaraanTemp.setTanaman(hakmilikPerbicaraan.getTanaman());
        hakmilikPerbicaraanTemp.setBangunan(hakmilikPerbicaraan.getBangunan());
        hakmilikPerbicaraanTemp.setCatatan(hakmilikPerbicaraan.getCatatan());
        hakmilikPerbicaraanTemp.setAmaunDituntut(hakmilikPerbicaraan.getAmaunDituntut());
        hakmilikPerbicaraanTemp.setAlasanTuntutan(hakmilikPerbicaraan.getAlasanTuntutan());
        hakmilikPerbicaraanTemp.setPemohonUlasan(hakmilikPerbicaraan.getPemohonUlasan());
        hakmilikPerbicaraanTemp.setKeputusanNilai(hakmilikPerbicaraan.getKeputusanNilai());
        hakmilikPerbicaraanTemp.setKodUOM(hakmilikPerbicaraan.getKodUOM());
        hakmilikPerbicaraanTemp.setPenilaiNoRujukan(hakmilikPerbicaraan.getPenilaiNoRujukan());
        hakmilikPerbicaraanTemp.setTarikhSuratPenilai(hakmilikPerbicaraan.getTarikhSuratPenilai());
        hakmilikPerbicaraan = notisPenerimaanService.saveOrUpdateHakmilikPerbicaraan(hakmilikPerbicaraanTemp);

        if(hakmilikPerbicaraan.getIdPerbicaraan() > 0) {
            List<HakmilikPihakBerkepentingan> hpList = hakmilik.getSenaraiPihakBerkepentingan();
            for(int i=0; i<hpList.size(); i++) {
                HakmilikPihakBerkepentingan hp = hpList.get(i);
                PermohonanPihak permohonanPihak = notisPenerimaanService.getSenaraiPmohonPihakByIdHakmilikIdPihak(idPermohonan, idHakmilik, hp.getPihak().getIdPihak());
                perbicaraanKehadiran = notisPenerimaanService.getPerbicaraanKehadiranbyidMPidBicara(permohonanPihak.getIdPermohonanPihak(), hakmilikPerbicaraan.getIdPerbicaraan());
                if(perbicaraanKehadiran == null) {
                    perbicaraanKehadiran = new PerbicaraanKehadiran();
                    InfoAudit infoAudit = new InfoAudit();
                    infoAudit.setTarikhMasuk(new java.util.Date());
                    infoAudit.setDimasukOleh(peng);
                    perbicaraanKehadiran.setInfoAudit(infoAudit);
                    perbicaraanKehadiran.setCawangan(cawangan);
                    perbicaraanKehadiran.setPerbicaraan(hakmilikPerbicaraan);
                    perbicaraanKehadiran.setPihak(permohonanPihak);
                }else {
                    InfoAudit ia1 = perbicaraanKehadiran.getInfoAudit();
                    ia1.setTarikhKemaskini(new java.util.Date());
                    ia1.setDiKemaskiniOleh(peng);
                    perbicaraanKehadiran.setInfoAudit(ia1);
                }
//                String penilaiCatatanObj = (String) perbicaraanKehadiran.setPenilaiCatatan((String) getContext().getRequest().getParameter("penilaiCatatan"));
                perbicaraanKehadiran.setPenilaiUlasan(getContext().getRequest().getParameter("perbicaraanKehadiran.penilaiUlasan"));
                perbicaraanKehadiran.setHadir(hadir.get(i));
                perbicaraanKehadiran.setKeterangan(keterangan.get(i));
                notisPenerimaanService.saveOrUpdatePerbicaraanKehadiran(perbicaraanKehadiran);
            }
            if(hakmilikPerbicaraan.getKeputusanNilai() != null) {
                getContext().getRequest().setAttribute("showPerintah", Boolean.TRUE);
            }
        }

        addSimpleMessage("Maklumat telah berjaya disimpan");
        return new JSP("pengambilan/negerisembilan/haklalulalangpersendirian/nota_siasatan_persendirian.jsp").addParameter("tab", "true");
    }

    public Resolution hakmilikDetails() {
        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");

        hakmilik = hakmilikDAO.findById(idHakmilik);
        List<HakmilikPihakBerkepentingan> hpList = hakmilik.getSenaraiPihakBerkepentingan();
        for(int i=0; i<hpList.size(); i++) {
            hadir.add('0');
        }
        for(int j=0; j<hpList.size(); j++){
            keterangan.add("Tiada data");
        }

        hakmilikPermohonan = pembetulanService.findByIdHakmilikIdPermohonan(idPermohonan, idHakmilik);
        if(hakmilikPermohonan != null){
            hakmilikPerbicaraan = notisPenerimaanService.getHakmilikPerbicaraanByidMH(hakmilikPermohonan.getId());
        }

        for(int i=0; i<hpList.size(); i++) {
            HakmilikPihakBerkepentingan hp = hpList.get(i);
            PermohonanPihak permohonanPihak = notisPenerimaanService.getSenaraiPmohonPihakByIdHakmilikIdPihak(idPermohonan, idHakmilik, hp.getPihak().getIdPihak());
            if(hakmilikPerbicaraan != null) {
                perbicaraanKehadiran = notisPenerimaanService.getPerbicaraanKehadiranbyidMPidBicara(permohonanPihak.getIdPermohonanPihak(), hakmilikPerbicaraan.getIdPerbicaraan());
                if(perbicaraanKehadiran != null) {
                    if(perbicaraanKehadiran.getHadir()!= ' ')
                        hadir.set(i, perbicaraanKehadiran.getHadir());

                    if(perbicaraanKehadiran.getKeterangan() != null)
                        keterangan.set(i, perbicaraanKehadiran.getKeterangan());

                    if((perbicaraanKehadiran.getPenilaiUlasan() !=null))
                        penilaiUlasan = perbicaraanKehadiran.getPenilaiUlasan();
                }
                if(hakmilikPerbicaraan.getKeputusanNilai() != null) {
                    System.out.println("------showPerintah-----");
                    getContext().getRequest().setAttribute("showPerintah", Boolean.TRUE);
                }
            }

        }
        for(int i=0;i<hadir.size();i++){
            System.out.println("hadir-----"+hadir.get(i));
        }
        if(hakmilikPerbicaraan==null){
            hakmilikPerbicaraan = new HakmilikPerbicaraan();
        }

        getContext().getRequest().getSession().setAttribute("idHakmilik", idHakmilik);
        return new JSP("pengambilan/negerisembilan/haklalulalangpersendirian/nota_siasatan_persendirian.jsp").addParameter("tab", "true");
    }

    public List<HakmilikPermohonan> getHakmilikPermohonanList() {
        return hakmilikPermohonanList;
    }

    public void setHakmilikPermohonanList(List<HakmilikPermohonan> hakmilikPermohonanList) {
        this.hakmilikPermohonanList = hakmilikPermohonanList;
    }

    public String getIdHakmilik() {
        return idHakmilik;
    }

    public void setIdHakmilik(String idHakmilik) {
        this.idHakmilik = idHakmilik;
    }

    public PermohonanDAO getPermohonanDAO() {
        return permohonanDAO;
    }

    public void setPermohonanDAO(PermohonanDAO permohonanDAO) {
        this.permohonanDAO = permohonanDAO;
    }

    public Permohonan getPermohonan() {
        return permohonan;
    }

    public void setPermohonan(Permohonan permohonan) {
        this.permohonan = permohonan;
    }

    public Hakmilik getHakmilik() {
        return hakmilik;
    }

    public void setHakmilik(Hakmilik hakmilik) {
        this.hakmilik = hakmilik;
    }

    public HakmilikPerbicaraan getHakmilikPerbicaraan() {
        return hakmilikPerbicaraan;
    }

    public void setHakmilikPerbicaraan(HakmilikPerbicaraan hakmilikPerbicaraan) {
        this.hakmilikPerbicaraan = hakmilikPerbicaraan;
    }

    public HakmilikPermohonan getHakmilikPermohonan() {
        return hakmilikPermohonan;
    }

    public void setHakmilikPermohonan(HakmilikPermohonan hakmilikPermohonan) {
        this.hakmilikPermohonan = hakmilikPermohonan;
    }

    public PerbicaraanKehadiran getPerbicaraanKehadiran() {
        return perbicaraanKehadiran;
    }

    public void setPerbicaraanKehadiran(PerbicaraanKehadiran perbicaraanKehadiran) {
        this.perbicaraanKehadiran = perbicaraanKehadiran;
    }

    public KodCawangan getCawangan() {
        return cawangan;
    }

    public void setCawangan(KodCawangan cawangan) {
        this.cawangan = cawangan;
    }

    public List<Character> getHadir() {
        return hadir;
    }

    public void setHadir(List<Character> hadir) {
        this.hadir = hadir;
    }

    public NotisPenerimaanService getNotisPenerimaanService() {
        return notisPenerimaanService;
    }

    public void setNotisPenerimaanService(NotisPenerimaanService notisPenerimaanService) {
        this.notisPenerimaanService = notisPenerimaanService;
    }

    public PembetulanService getPembetulanService() {
        return pembetulanService;
    }

    public void setPembetulanService(PembetulanService pembetulanService) {
        this.pembetulanService = pembetulanService;
    }

    public List<PerbicaraanKehadiran> getSenaraiPerbicaraanKehadiran() {
        return senaraiPerbicaraanKehadiran;
    }

    public void setSenaraiPerbicaraanKehadiran(List<PerbicaraanKehadiran> senaraiPerbicaraanKehadiran) {
        this.senaraiPerbicaraanKehadiran = senaraiPerbicaraanKehadiran;
    }

    public List<String> getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(List<String> keterangan) {
        this.keterangan = keterangan;
    }

    public String getPenilaiUlasan() {
        return penilaiUlasan;
    }

    public void setPenilaiUlasan(String penilaiUlasan) {
        this.penilaiUlasan = penilaiUlasan;
    }


}

