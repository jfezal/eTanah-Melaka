package etanah.view.daftar.utiliti;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import com.google.inject.Provider;
import etanah.dao.KodJenisPengenalanDAO;
import etanah.dao.WakilKuasaDAO;
import etanah.dao.WakilKuasaPemberiDAO;
import etanah.dao.WakilKuasaPenerimaDAO;
import etanah.model.Alamat;
import etanah.model.InfoAudit;
import etanah.model.KodJenisPengenalan;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.model.WakilKuasa;
import etanah.model.WakilKuasaPemberi;
import etanah.model.WakilKuasaPenerima;
import etanah.service.common.HakmilikUrusanService;
import etanah.service.common.PermohonanService;
import etanah.service.daftar.PembetulanService;
import etanah.service.daftar.PendaftaranSuratKuasaService;
import etanah.view.etanahActionBeanContext;
import java.util.List;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.RedirectResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;
import org.apache.log4j.Logger;
import org.hibernate.Session;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author zipzap
 */
//public class PembetulanWakilActionBean {
@UrlBinding("/daftar/utiliti/PembetulanWakilActionBean")
public class PembetulanWakilActionBean extends AbleActionBean {

    private String idPermohonan;
    private String trkh;
    private String idHakmilik;
    private List<Permohonan> senaraiPerserahan;
    private String[] dt;
    private String[] ids;
    private Pengguna pengguna;
    @Inject
    PermohonanService permohonanService;
    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;
    WakilKuasaDAO wakilKuasaDAO;
    @Inject
    WakilKuasaPemberiDAO wakilKuasaPemberiDAO;
    @Inject
    WakilKuasaPenerimaDAO wakilKuasaPenerimaDAO;
    @Inject
    PendaftaranSuratKuasaService pendaftaranSuratKuasaService;
    @Inject
    KodJenisPengenalanDAO kodJenisPengenalanDAO;
    private static Logger LOG = Logger.getLogger(PembetulanWakilActionBean.class);
    private boolean isDebug = LOG.isDebugEnabled();
    WakilKuasaPemberi wakilPemberi;
    WakilKuasaPenerima wakilPenerima;
    WakilKuasa wakilK;
    private List<WakilKuasaPenerima> listWakilKuasaPenerima;
    private List<WakilKuasaPemberi> listWakilKuasaPemberi;
    Alamat alamat;
    String namaPemberi;
    String noPengenalanPemberi;
    String jenisPengenalanPemberi;
    String namaPenerima;
    String noPengenalanPenerima;
    String jenisPengenalanPenerima;
    String penerimaCheck;
    String pemberiCheck;
    String idPenerima;
    String idPemberi;
    KodJenisPengenalan kodJenisPengenalan;

    @DefaultHandler
    public Resolution showForm() {
//        if (idPermohonan != null) {
//            wakilPemberi = pendaftaranSuratKuasaService.findWakilPemberibyIdPermohonan(idPermohonan);
//            wakilPenerima = pendaftaranSuratKuasaService.findWakilPenerimabyIdPermohonan(idPermohonan);
//            if (wakilPemberi == null) {
//                addSimpleError("Id " + idPermohonan + " yang Di Masukan Tiada Dalam Pengkalan Data. Sila Cuba lagi Dengan Id Yang Betul");
//            }
//            if (wakilPenerima == null) {
//                addSimpleError("Id " + idPermohonan + " yang Di Masukan Tiada Dalam Pengkalan Data. Sila Cuba lagi Dengan Id Yang Betul");
//            }
//        }
        return new JSP("daftar/utiliti/pembetulan_wakil.jsp");
    }

    public Resolution cari() {
        idPermohonan = (String) getContext().getRequest().getParameter("idPermohonan");
        if (idPermohonan != null) {
//            wakilPemberi = pendaftaranSuratKuasaService.findWakilPemberibyIdPermohonan(idPermohonan);
            wakilK = pendaftaranSuratKuasaService.findWakilKuasa(idPermohonan);
            if (wakilK.equals(null)) {
                listWakilKuasaPenerima = pendaftaranSuratKuasaService.findWakilPenerimabyIdPermohonanList(idPermohonan);
                listWakilKuasaPemberi = pendaftaranSuratKuasaService.findWakilKuasaListPemberi(idPermohonan);
                if (listWakilKuasaPenerima.size() <= 0) {
                    addSimpleError("Id " + idPermohonan + " yang Di Masukan Tiada Dalam Pengkalan Data. Sila Cuba lagi Dengan Id Yang Betul");
                }
            } else if (!wakilK.equals(null)) {
                listWakilKuasaPenerima = pendaftaranSuratKuasaService.findWakilPenerimabyIdPermohonanList(idPermohonan);
                listWakilKuasaPemberi = pendaftaranSuratKuasaService.findWakilKuasaListPemberi(idPermohonan);
            }
        }
        return new JSP("daftar/utiliti/pembetulan_wakil.jsp").addParameter(idPermohonan, idPermohonan);
    }

    public Resolution tambahPemberi() {
        idPermohonan = (String) getContext().getRequest().getParameter("idPermohonan");
        return new JSP("daftar/utiliti/tambah_pemberi.jsp").addParameter(idPermohonan, idPermohonan);
    }

    public Resolution tambahPenerima() {
        idPermohonan = (String) getContext().getRequest().getParameter("idPermohonan");
        return new JSP("daftar/utiliti/tambah_penerima.jsp").addParameter(idPermohonan, idPermohonan);
    }

    public Resolution simpanPenerimaNew() {
        idPermohonan = (String) getContext().getRequest().getParameter("idPermohonan");
        noPengenalanPenerima = (String) getContext().getRequest().getParameter("noPengenalanPenerima");
        jenisPengenalanPenerima = (String) getContext().getRequest().getParameter("jenisPengenalanPenerima");
        namaPenerima = (String) getContext().getRequest().getParameter("namaPenerima");
        Pengguna pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);

        wakilK = pendaftaranSuratKuasaService.findWakilKuasa(idPermohonan);

        InfoAudit ia = new InfoAudit();
        ia.setDimasukOleh(pengguna);
        ia.setTarikhMasuk(new java.util.Date());

        WakilKuasaPenerima wp = new WakilKuasaPenerima();
        wp.setNama(namaPenerima);
        wp.setNoPengenalan(noPengenalanPenerima);
        wp.setWakilKuasa(wakilK);
        wp.setJenisPengenalan(kodJenisPengenalanDAO.findById(jenisPengenalanPenerima));
        wp.setCawangan(wakilK.getCawangan());
        wp.setInfoAudit(ia);
        wp.setStatus("A");

        pendaftaranSuratKuasaService.saveWakilKuasaPenerima(wp);

        return cari();
    }

    public Resolution simpanPemberiNew() {
        idPermohonan = (String) getContext().getRequest().getParameter("idPermohonan");
        namaPemberi = (String) getContext().getRequest().getParameter("namaPemberi");
        noPengenalanPemberi = (String) getContext().getRequest().getParameter("noPengenalanPemberi");
        jenisPengenalanPemberi = (String) getContext().getRequest().getParameter("jenisPengenalanPemberi");
        Pengguna pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);

        wakilK = pendaftaranSuratKuasaService.findWakilKuasa(idPermohonan);

        InfoAudit ia = new InfoAudit();
        ia.setDimasukOleh(pengguna);
        ia.setTarikhMasuk(new java.util.Date());

        WakilKuasaPemberi wp = new WakilKuasaPemberi();
        wp.setNama(namaPemberi);
        wp.setNoPengenalan(noPengenalanPemberi);
        wp.setWakilKuasa(wakilK);
        wp.setKodPengenalan(kodJenisPengenalanDAO.findById(jenisPengenalanPemberi).getKod());
        wp.setCawangan(wakilK.getCawangan());
        wp.setInfoAudit(ia);

        pendaftaranSuratKuasaService.saveWakilKuasaPemberi(wp);

        return cari();
    }

    public Resolution simpanPenerima() {
        idPermohonan = (String) getContext().getRequest().getParameter("idPermohonan");
        noPengenalanPenerima = (String) getContext().getRequest().getParameter("noPengenalanPenerima");
        jenisPengenalanPenerima = (String) getContext().getRequest().getParameter("jenisPengenalanPenerima");
        namaPenerima = (String) getContext().getRequest().getParameter("namaPenerima");
        idPenerima = (String) getContext().getRequest().getParameter("idPenerima");
        Pengguna pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);

        InfoAudit ia = new InfoAudit();
        ia.setDiKemaskiniOleh(pengguna);
        ia.setTarikhKemaskini(new java.util.Date());

        if (idPermohonan != null) {
            wakilPenerima = wakilKuasaPenerimaDAO.findById(Long.valueOf(idPenerima));
        }

        if (!namaPenerima.equalsIgnoreCase("")) {
            wakilPenerima.setNama(namaPenerima);
        }
        if (!noPengenalanPenerima.equalsIgnoreCase("")) {
            wakilPenerima.setNoPengenalan(noPengenalanPenerima);
        }
        if (!jenisPengenalanPenerima.equalsIgnoreCase("")) {
            wakilPenerima.setJenisPengenalan(kodJenisPengenalanDAO.findById(jenisPengenalanPenerima));
        }

        wakilPenerima.setInfoAudit(ia);
        pendaftaranSuratKuasaService.saveWakilKuasaPenerima(wakilPenerima);

        return new JSP("daftar/utiliti/pembetulan_wakil.jsp");
    }

    public Resolution simpanPemberi() {
        idPermohonan = (String) getContext().getRequest().getParameter("idPermohonan");
        namaPemberi = (String) getContext().getRequest().getParameter("namaPemberi");
        noPengenalanPemberi = (String) getContext().getRequest().getParameter("noPengenalanPemberi");
        jenisPengenalanPemberi = (String) getContext().getRequest().getParameter("jenisPengenalanPemberi");
        idPemberi = (String) getContext().getRequest().getParameter("idPemberi");
        Pengguna pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);

        InfoAudit ia = new InfoAudit();
        ia.setDiKemaskiniOleh(pengguna);
        ia.setTarikhKemaskini(new java.util.Date());

        if (idPermohonan != null) {
            wakilPemberi = wakilKuasaPemberiDAO.findById(Long.valueOf(idPemberi));
        }

        if (!namaPemberi.equalsIgnoreCase("")) {
            wakilPemberi.setNama(namaPemberi);
        }
        if (!noPengenalanPemberi.equalsIgnoreCase("")) {
            wakilPemberi.setNoPengenalan(noPengenalanPemberi);
        }
        if (!jenisPengenalanPemberi.equalsIgnoreCase("")) {
            wakilPemberi.setKodPengenalan(String.valueOf(kodJenisPengenalanDAO.findById(jenisPengenalanPemberi).getKod()));
        }

        wakilPemberi.setInfoAudit(ia);
//        wakilPemberi.setInfoAudit(ia);

//        pendaftaranSuratKuasaService.saveWakilKuasaPemberi(wakilPemberi);
        pendaftaranSuratKuasaService.saveWakilKuasaPemberi(wakilPemberi);
//        kodJenisPengenalan = kodJenisPengenalanDAO.findById(wakilPemberi.getKodPengenalan());

        return new JSP("daftar/utiliti/pembetulan_wakil.jsp");
    }

    public Resolution editPemberi() {
        pemberiCheck = "Y";
        return new JSP("daftar/utiliti/pembetulan_wakil.jsp");
    }

    public Resolution pilihPenerima() {
        idPermohonan = (String) getContext().getRequest().getParameter("idPermohonan");
        if (idPermohonan != null) {
            listWakilKuasaPenerima = pendaftaranSuratKuasaService.findWakilPenerimabyIdPermohonanList(idPermohonan);            
             if (listWakilKuasaPenerima.size() > 0) {
                if (idPenerima == null) {
                    Long idPenerima = listWakilKuasaPenerima.get(0).getIdPenerima();
                    wakilPenerima = wakilKuasaPenerimaDAO.findById(idPenerima);
                    pemberiCheck = "Y";
                } else if (idPenerima != null) {
                    wakilPenerima = wakilKuasaPenerimaDAO.findById(Long.parseLong(idPenerima));
                    pemberiCheck = "Y";
                }
            }
            
            if (idPenerima != null) {
                wakilPenerima = wakilKuasaPenerimaDAO.findById(Long.parseLong(idPenerima));
                penerimaCheck = "Y";
            }
        }
        idPenerima = String.valueOf(wakilPenerima.getIdPenerima());
        return new JSP("daftar/utiliti/pembetulan_wakil_kemaskini.jsp");
    }

    public Resolution pilihPemberi() {
        idPermohonan = (String) getContext().getRequest().getParameter("idPermohonan");
        if (idPermohonan != null) {
            listWakilKuasaPemberi = pendaftaranSuratKuasaService.findWakilPemberibyIdPermohonanList(idPermohonan);
            if (listWakilKuasaPemberi.size() > 0) {
                if (idPemberi == null) {
                    Long idPemberiLong = listWakilKuasaPemberi.get(0).getIdPemberi();
                    wakilPemberi = wakilKuasaPemberiDAO.findById(idPemberiLong);
                    pemberiCheck = "Y";
                } else if (idPemberi != null) {
                    wakilPemberi = wakilKuasaPemberiDAO.findById(Long.parseLong(idPemberi));
                    pemberiCheck = "Y";
                }
            }
        }
        idPemberi = String.valueOf(wakilPemberi.getIdPemberi());
        return new JSP("daftar/utiliti/pembetulan_wakil_kemaskini.jsp").addParameter(idPemberi, idPemberi);
    }

    public String getIdPermohonan() {
        return idPermohonan;
    }

    public void setIdPermohonan(String idPermohonan) {
        this.idPermohonan = idPermohonan;
    }

    public String getTrkh() {
        return trkh;
    }

    public void setTrkh(String trkh) {
        this.trkh = trkh;
    }

    public String getIdHakmilik() {
        return idHakmilik;
    }

    public void setIdHakmilik(String idHakmilik) {
        this.idHakmilik = idHakmilik;
    }

    public List<Permohonan> getSenaraiPerserahan() {
        return senaraiPerserahan;
    }

    public void setSenaraiPerserahan(List<Permohonan> senaraiPerserahan) {
        this.senaraiPerserahan = senaraiPerserahan;
    }

    public String[] getDt() {
        return dt;
    }

    public void setDt(String[] dt) {
        this.dt = dt;
    }

    public String[] getIds() {
        return ids;
    }

    public void setIds(String[] ids) {
        this.ids = ids;
    }

    public Pengguna getPengguna() {
        return pengguna;
    }

    public void setPengguna(Pengguna pengguna) {
        this.pengguna = pengguna;
    }

    public PermohonanService getPermohonanService() {
        return permohonanService;
    }

    public void setPermohonanService(PermohonanService permohonanService) {
        this.permohonanService = permohonanService;
    }

    public Provider<Session> getSessionProvider() {
        return sessionProvider;
    }

    public void setSessionProvider(Provider<Session> sessionProvider) {
        this.sessionProvider = sessionProvider;
    }

    public WakilKuasaDAO getWakilKuasaDAO() {
        return wakilKuasaDAO;
    }

    public void setWakilKuasaDAO(WakilKuasaDAO wakilKuasaDAO) {
        this.wakilKuasaDAO = wakilKuasaDAO;
    }

    public WakilKuasaPemberiDAO getWakilKuasaPemberiDAO() {
        return wakilKuasaPemberiDAO;
    }

    public WakilKuasa getWakilK() {
        return wakilK;
    }

    public void setWakilK(WakilKuasa wakilK) {
        this.wakilK = wakilK;
    }

    public void setWakilKuasaPemberiDAO(WakilKuasaPemberiDAO wakilKuasaPemberiDAO) {
        this.wakilKuasaPemberiDAO = wakilKuasaPemberiDAO;
    }

    public WakilKuasaPenerimaDAO getWakilKuasaPenerimaDAO() {
        return wakilKuasaPenerimaDAO;
    }

    public void setWakilKuasaPenerimaDAO(WakilKuasaPenerimaDAO wakilKuasaPenerimaDAO) {
        this.wakilKuasaPenerimaDAO = wakilKuasaPenerimaDAO;
    }

    public PendaftaranSuratKuasaService getPendaftaranSuratKuasaService() {
        return pendaftaranSuratKuasaService;
    }

    public void setPendaftaranSuratKuasaService(PendaftaranSuratKuasaService pendaftaranSuratKuasaService) {
        this.pendaftaranSuratKuasaService = pendaftaranSuratKuasaService;
    }

    public static Logger getLOG() {
        return LOG;
    }

    public static void setLOG(Logger LOG) {
        PembetulanWakilActionBean.LOG = LOG;
    }

    public boolean isIsDebug() {
        return isDebug;
    }

    public void setIsDebug(boolean isDebug) {
        this.isDebug = isDebug;
    }

    public WakilKuasaPemberi getWakilPemberi() {
        return wakilPemberi;
    }

    public void setWakilPemberi(WakilKuasaPemberi wakilPemberi) {
        this.wakilPemberi = wakilPemberi;
    }

    public WakilKuasaPenerima getWakilPenerima() {
        return wakilPenerima;
    }

    public void setWakilPenerima(WakilKuasaPenerima wakilPenerima) {
        this.wakilPenerima = wakilPenerima;
    }

    public Alamat getAlamat() {
        return alamat;
    }

    public void setAlamat(Alamat alamat) {
        this.alamat = alamat;
    }

    public String getNamaPemberi() {
        return namaPemberi;
    }

    public void setNamaPemberi(String namaPemberi) {
        this.namaPemberi = namaPemberi;
    }

    public String getNoPengenalanPemberi() {
        return noPengenalanPemberi;
    }

    public void setNoPengenalanPemberi(String noPengenalanPemberi) {
        this.noPengenalanPemberi = noPengenalanPemberi;
    }

    public String getJenisPengenalanPemberi() {
        return jenisPengenalanPemberi;
    }

    public void setJenisPengenalanPemberi(String jenisPengenalanPemberi) {
        this.jenisPengenalanPemberi = jenisPengenalanPemberi;
    }

    public String getNamaPenerima() {
        return namaPenerima;
    }

    public void setNamaPenerima(String namaPenerima) {
        this.namaPenerima = namaPenerima;
    }

    public String getNoPengenalanPenerima() {
        return noPengenalanPenerima;
    }

    public void setNoPengenalanPenerima(String noPengenalanPenerima) {
        this.noPengenalanPenerima = noPengenalanPenerima;
    }

    public String getJenisPengenalanPenerima() {
        return jenisPengenalanPenerima;
    }

    public void setJenisPengenalanPenerima(String jenisPengenalanPenerima) {
        this.jenisPengenalanPenerima = jenisPengenalanPenerima;
    }

    public String getPenerimaCheck() {
        return penerimaCheck;
    }

    public void setPenerimaCheck(String penerimaCheck) {
        this.penerimaCheck = penerimaCheck;
    }

    public String getPemberiCheck() {
        return pemberiCheck;
    }

    public void setPemberiCheck(String pemberiCheck) {
        this.pemberiCheck = pemberiCheck;
    }

    public KodJenisPengenalanDAO getKodJenisPengenalanDAO() {
        return kodJenisPengenalanDAO;
    }

    public void setKodJenisPengenalanDAO(KodJenisPengenalanDAO kodJenisPengenalanDAO) {
        this.kodJenisPengenalanDAO = kodJenisPengenalanDAO;
    }

    public KodJenisPengenalan getKodJenisPengenalan() {
        return kodJenisPengenalan;
    }

    public void setKodJenisPengenalan(KodJenisPengenalan kodJenisPengenalan) {
        this.kodJenisPengenalan = kodJenisPengenalan;
    }

    public List<WakilKuasaPenerima> getListWakilKuasaPenerima() {
        return listWakilKuasaPenerima;
    }

    public void setListWakilKuasaPenerima(List<WakilKuasaPenerima> listWakilKuasaPenerima) {
        this.listWakilKuasaPenerima = listWakilKuasaPenerima;
    }

    public String getIdPenerima() {
        return idPenerima;
    }

    public void setIdPenerima(String idPenerima) {
        this.idPenerima = idPenerima;
    }

    public List<WakilKuasaPemberi> getListWakilKuasaPemberi() {
        return listWakilKuasaPemberi;
    }

    public void setListWakilKuasaPemberi(List<WakilKuasaPemberi> listWakilKuasaPemberi) {
        this.listWakilKuasaPemberi = listWakilKuasaPemberi;
    }

    public String getIdPemberi() {
        return idPemberi;
    }

    public void setIdPemberi(String idPemberi) {
        this.idPemberi = idPemberi;
    }

}
