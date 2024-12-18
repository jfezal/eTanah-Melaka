/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.strata;

import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.dao.KodJenisPengenalanDAO;
import etanah.dao.KodJenisPihakBerkepentinganDAO;
import etanah.dao.KodNegeriDAO;
import etanah.dao.PemohonDAO;
import etanah.dao.PermohonanDAO;
import etanah.dao.PihakDAO;
import etanah.model.Alamat;
import etanah.model.AlamatSurat;
import etanah.model.HakmilikPermohonan;
import etanah.model.HakmilikPihakBerkepentingan;
import etanah.model.InfoAudit;
import etanah.model.KodBangsa;
import etanah.model.KodJenisPengenalan;
import etanah.model.KodNegeri;
import etanah.model.Pemohon;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.model.PermohonanPihak;
import etanah.model.Pihak;
import etanah.model.PihakAlamatTamb;
import etanah.service.StrataPtService;
import etanah.service.common.HakmilikPihakKepentinganService;
import etanah.service.common.PemohonService;
import etanah.service.common.PermohonanPihakService;
import etanah.service.common.PermohonanService;
import etanah.service.common.PihakService;
import etanah.view.BasicTabActionBean;
import etanah.view.ListUtil;
import etanah.view.etanahActionBeanContext;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.RedirectResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;
import org.apache.log4j.Logger;
import org.apache.commons.lang.StringUtils;
import org.hibernate.Session;

/**
 *
 * @author murali
 */
@UrlBinding("/strata/pechaPetak/kemasukan_pemohon")
public class PechaPetakPemohonActionBean extends BasicTabActionBean {

    @Inject
    private etanah.Configuration conf;
    @Inject
    KodJenisPengenalanDAO kodJenisPengenalanDAO;
    @Inject
    KodNegeriDAO kodNegeriDAO;
    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    PemohonDAO pemohonDAO;
    @Inject
    PemohonService pemohonService;
    @Inject
    PihakDAO pihakDAO;
    @Inject
    PermohonanService permohonanService;
    @Inject
    PihakService pihakService;
    @Inject
    PermohonanPihakService permohonanPihakService;
    @Inject
    StrataPtService strService;
    @Inject
    KodJenisPihakBerkepentinganDAO kodJenisPihakBerkepentinganDAO;
    @Inject
    ListUtil listUtil;
    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;
    @Inject
    HakmilikPihakKepentinganService hakmilikPihakKepentinganService;
    private Permohonan permohonan;
    private List<Pemohon> listPemohon;
    private List<Pihak> senaraiPihak;
    private PermohonanPihak permohonanPihak;
    private Pihak pihak;
    private Pemohon pemohon;
    private String jenis;
    private String checkAlamat;
    private List<KodBangsa> senaraiKodBangsa;
    private String kodPengenalan;
    private String noPengenalan;
    private String namaPemohon;
    private String nama;
    private String jenisPengenalan;
    private String jenisPengenalan2;
    private String nomborPengenalan;
    private String alamat1;
    private String alamat2;
    private String alamat3;
    private String alamat4;
    private String poskod;
    private String negeri;
    private String suratAlamat1;
    private String suratAlamat2;
    private String suratAlamat3;
    private String suratAlamat4;
    private String suratNegeri;
    private String suratPoskod;
    private String idPihak;
    private static final Logger LOG = Logger.getLogger(PechaPetakPemohonActionBean.class);
    private boolean penyerahAdalahPemohon = false;
    private List<Pihak> senaraiPihak1;
    private String carianNoPengenalan;
    private String index;
    private String suratNegeri1;
    private String negeri1;
    private String suratMenyurat;
    private String kodNegeri;
    private PihakAlamatTamb pat;
    private PihakAlamatTamb pihakAlamatTamb;
    private String syor;
    List<HakmilikPihakBerkepentingan> listHakmilikPihak;
    private Pemohon pemohon1;
    List<HakmilikPermohonan> hakmilikPermohonanListBaru;

    @DefaultHandler
    public Resolution showForm() {
        return new JSP("strata/maklumat_pemohon_pechaPetak.jsp").addParameter("tab", "true");
    }

    public Resolution showPemohon() {
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        String idPemohon = getContext().getRequest().getParameter("idPemohon");
        pemohon1 = pemohonDAO.findById(Long.valueOf(idPemohon));
        if (pemohon1 != null) {
            nama = pemohon1.getNama();
            alamat1 = pemohon1.getPihak().getAlamat1();
            alamat2 = pemohon1.getPihak().getAlamat2();
            alamat3 = pemohon1.getPihak().getAlamat3();
            alamat4 = pemohon1.getPihak().getAlamat4();
            if (pemohon1.getJenisPengenalan() != null) {
                jenisPengenalan2 = pemohon1.getJenisPengenalan().getNama();
                jenisPengenalan = pemohon1.getJenisPengenalan().getKod();
            }
            nomborPengenalan = pemohon1.getNoPengenalan();
            poskod = pemohon1.getPihak().getPoskod();
            if (pemohon1.getPihak().getNegeri() != null) {
                negeri = pemohon1.getPihak().getNegeri().getKod();
                negeri1 = pemohon1.getPihak().getNegeri().getNama().toUpperCase();
            }
            //surat
            suratAlamat1 = pemohon1.getPihak().getSuratAlamat1();
            suratAlamat2 = pemohon1.getPihak().getSuratAlamat2();
            suratAlamat3 = pemohon1.getPihak().getSuratAlamat3();
            suratAlamat4 = pemohon1.getPihak().getSuratAlamat4();
            suratPoskod = pemohon1.getPihak().getSuratPoskod();
            if (pemohon1.getPihak().getSuratNegeri() != null) {
                suratNegeri = pemohon1.getPihak().getSuratNegeri().getKod();
                suratNegeri1 = pemohon1.getPihak().getSuratNegeri().getNama().toUpperCase();
            }

            if (pemohon1.getPihak() != null) {
                if (pemohon1.getPihak().getAlamat1() != null && pemohon1.getPihak().getAlamat2() != null && pemohon1.getPihak().getAlamat3() != null
                        && pemohon1.getPihak().getAlamat4() != null && pemohon1.getPihak().getPoskod() != null && pemohon1.getPihak().getNegeri() != null
                        && pemohon1.getPihak().getSuratAlamat1() != null && pemohon1.getPihak().getSuratAlamat2() != null && pemohon1.getPihak().getSuratAlamat3() != null
                        && pemohon1.getPihak().getSuratAlamat4() != null && pemohon1.getPihak().getSuratPoskod() != null && pemohon1.getPihak().getSuratNegeri() != null) {
                    if (pemohon1.getPihak().getAlamat1().equals(pemohon1.getPihak().getSuratAlamat1()) && pemohon1.getPihak().getAlamat2().equals(pemohon1.getPihak().getSuratAlamat2())
                            && pemohon1.getPihak().getAlamat3().equals(pemohon1.getPihak().getSuratAlamat3()) && pemohon1.getPihak().getAlamat4().equals(pemohon1.getPihak().getSuratAlamat4())
                            && pemohon1.getPihak().getPoskod().equals(pemohon1.getPihak().getSuratPoskod()) && pemohon1.getPihak().getNegeri().equals(pemohon1.getPihak().getSuratNegeri())) {
                        checkAlamat = "Yes";
                    } else {
                        checkAlamat = "No";
                    }
                }
            }
        }
        return new JSP("strata/pechaPetak_pemohon.jsp").addParameter("tab", "true");
    }

    public Resolution resetForm() {

        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        pemohon = strService.findById(idPermohonan);
        if (pemohon != null) {
            Pihak pihak1 = pihakDAO.findById(pemohon.getPihak().getIdPihak());
            try {
                LOG.info("::DELETE PEMOHON::");
                strService.deletePemohonByIDMohon(idPermohonan);

                if (pihak1 != null) {
                    try {

                        LOG.info("::DELETE PIHAK Alamat Tamb::");
                        strService.deletePihakATBByIdPihak(pihak1.getIdPihak());

                        LOG.info("::DELETE PIHAK::");
                        strService.deletePihakByIdPihak(pihak1.getIdPihak());

                        addSimpleMessage("Maklumat pemohon berjaya dikosongkan.");
                    } catch (Exception e) {
                        LOG.error(e);
                    }
                }

            } catch (Exception e) {
                LOG.error(e);
                addSimpleError("Terdapat masalah teknikal semasa memadam rekod.");
            }

        }
        return new RedirectResolution(PechaPetakPemohonActionBean.class, "showForm");
    }

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {
        Pengguna pguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = permohonanDAO.findById(idPermohonan);
        hakmilikPermohonanListBaru = new ArrayList<HakmilikPermohonan>();
        hakmilikPermohonanListBaru = strService.findIdHakmilikSortAsc(idPermohonan);
        listPemohon = pemohonService.findPemohonByIdPermohonan(idPermohonan);
        listHakmilikPihak = new ArrayList<HakmilikPihakBerkepentingan>();
        if (permohonan.getKodUrusan().getKod().equals("PHPC")) {
            listHakmilikPihak = hakmilikPihakKepentinganService.findHakmilikPihakByKod((permohonan.getSenaraiHakmilik().get(0)).getHakmilik(), "PM");
        }
        if (permohonan.getKodUrusan().getKod().equals("PHPP")) {
            List<HakmilikPihakBerkepentingan> hplist = new ArrayList<HakmilikPihakBerkepentingan>();
            for (int i = 0; i < permohonan.getSenaraiHakmilik().size(); i++) {
                hplist = hakmilikPihakKepentinganService.findHakmilikPihakByKod((permohonan.getSenaraiHakmilik().get(i)).getHakmilik(), "PM");
                if (!hplist.isEmpty()) {
                    for (HakmilikPihakBerkepentingan hpk : hplist) {
                        listHakmilikPihak.add(hpk);
                    }
                }
            }
        }

        List<Pihak> ph = new ArrayList<Pihak>();
        if (!listHakmilikPihak.isEmpty()) {
            for (int i = 0; i < listHakmilikPihak.size(); i++) {
                HakmilikPihakBerkepentingan hp = new HakmilikPihakBerkepentingan();
                hp = listHakmilikPihak.get(i);
                LOG.info("---hp---:" + hp.getIdHakmilikPihakBerkepentingan());
                if (listPemohon.isEmpty()) {
                    if (ph != null && !ph.contains(hp.getPihak())) {
                        ph.add(hp.getPihak());
                        pemohon = new Pemohon();
                        LOG.info("---New Pemohon created---:");
                        InfoAudit ia = new InfoAudit();
                        ia.setDimasukOleh(pguna);
                        ia.setTarikhMasuk(new java.util.Date());
                        pemohon.setPermohonan(permohonan);
                        pemohon.setPihak(hp.getPihak());
                        pemohon.setHakmilikPihak(hp);
                        pemohon.setInfoAudit(ia);
                        pemohon.setCawangan(permohonan.getCawangan());
                        
                        if (hp.getNama() != null) {
                            pemohon.setNama(hp.getNama());
                        }
                        if (hp.getNoPengenalan() != null) {
                            pemohon.setNoPengenalan(hp.getNoPengenalan());
                        }
                        if (hp.getJenisPengenalan() != null) {
                            pemohon.setJenisPengenalan(hp.getJenisPengenalan());
                        }
                        Alamat alamat = new Alamat();
                        if (hp.getAlamat1() != null) {
                            alamat.setAlamat1(hp.getAlamat1());
                        }
                        if (hp.getAlamat2() != null) {
                            alamat.setAlamat2(hp.getAlamat2());
                        }
                        if (hp.getAlamat3() != null) {
                            alamat.setAlamat3(hp.getAlamat3());
                        }
                        if (hp.getAlamat4() != null) {
                            alamat.setAlamat4(hp.getAlamat4());
                        }
                        if (hp.getPoskod() != null) {
                            alamat.setPoskod(hp.getPoskod());
                        }
                        if (hp.getNegeri() != null) {
                            alamat.setNegeri(hp.getNegeri());
                        }                    
                        pemohon.setAlamat(alamat);
                        pemohonService.saveOrUpdate(pemohon);

                        pihak = null;
                        pihak = pihakDAO.findById(Long.valueOf(hp.getPihak().getIdPihak()));
                        if (pihak != null) {
                            ia = pihak.getInfoAudit();
                            ia.setDiKemaskiniOleh(pguna);
                            ia.setTarikhKemaskini(new java.util.Date());
                        } else {
                            pihak = new Pihak();
                            ia.setDimasukOleh(pguna);
                            ia.setTarikhMasuk(new java.util.Date());
                        }
                        if (hp.getNama() != null) {
                            pihak.setNama(hp.getNama());
                        }
                        if (hp.getNoPengenalan() != null) {
                            pihak.setNoPengenalan(hp.getNoPengenalan());
                        }
                        if (hp.getJenisPengenalan() != null) {
                            pihak.setJenisPengenalan(hp.getJenisPengenalan());
                        }
                        if (hp.getAlamat1() != null) {
                            pihak.setAlamat1(hp.getAlamat1());
                        }
                        if (hp.getAlamat2() != null) {
                            pihak.setAlamat2(hp.getAlamat2());
                        }
                        if (hp.getAlamat3() != null) {
                            pihak.setAlamat3(hp.getAlamat3());
                        }
                        if (hp.getAlamat4() != null) {
                            pihak.setAlamat4(hp.getAlamat4());
                        }
                        if (hp.getPoskod() != null) {
                            pihak.setPoskod(hp.getPoskod());
                        }
                        if (hp.getNegeri() != null) {
                            pihak.setNegeri(hp.getNegeri());
                        }
                        pihak.setInfoAudit(ia);
                        strService.simpanPihak(pihak);

                        PihakAlamatTamb phkAlamatTamb = null;
                        phkAlamatTamb = pihakService.getAlamatTambahan(pihak);
                        if (phkAlamatTamb == null) {
                            phkAlamatTamb = new PihakAlamatTamb();
                            phkAlamatTamb.setPihak(pihak);
                        }
                        if (hp.getAlamatSurat() != null) {
                            if (hp.getAlamatSurat().getAlamatSurat1() != null) {
                                phkAlamatTamb.setAlamatKetiga1(hp.getAlamatSurat().getAlamatSurat1());
                            }
                            if (hp.getAlamatSurat().getAlamatSurat2() != null) {
                                phkAlamatTamb.setAlamatKetiga2(hp.getAlamatSurat().getAlamatSurat2());
                            }
                            if (hp.getAlamatSurat().getAlamatSurat3() != null) {
                                phkAlamatTamb.setAlamatKetiga3(hp.getAlamatSurat().getAlamatSurat3());
                            }
                            if (hp.getAlamatSurat().getAlamatSurat4() != null) {
                                phkAlamatTamb.setAlamatKetiga4(hp.getAlamatSurat().getAlamatSurat4());
                            }
                            if (hp.getAlamatSurat().getPoskodSurat() != null) {
                                phkAlamatTamb.setAlamatKetigaPoskod(hp.getAlamatSurat().getPoskodSurat());
                            }
                            if (hp.getAlamatSurat().getNegeriSurat() != null) {
                                phkAlamatTamb.setAlamatKetigaNegeri(hp.getAlamatSurat().getNegeriSurat());
                            }
                        }
                        phkAlamatTamb.setInfoAudit(ia);
                        strService.simpanPihakAlamatTamb(phkAlamatTamb);
                    }
                }
                }
            }
            listPemohon = pemohonService.findPemohonByIdPermohonan(idPermohonan);
        }

    

    public Resolution simpanPemohon() {
        Pengguna pguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = permohonanService.findById(idPermohonan);
        InfoAudit ia = new InfoAudit();
        String idPemohon = getContext().getRequest().getParameter("pemohon1.idPemohon");
        pemohon = pemohonDAO.findById(Long.valueOf(idPemohon));
        if (pemohon != null) {
            ia = pemohon.getInfoAudit();
            ia.setDiKemaskiniOleh(pguna);
            ia.setTarikhKemaskini(new java.util.Date());
        } else {
            pemohon = new Pemohon();
            ia.setDimasukOleh(pguna);
            ia.setTarikhMasuk(new java.util.Date());
        }
        pemohon.setPermohonan(permohonan);
        if (pemohon.getPihak() != null) {
            pemohon.setPihak(pemohon.getPihak());
        }
        pemohon.setNama(nama);
        if (jenisPengenalan != null) {
            pemohon.setJenisPengenalan(kodJenisPengenalanDAO.findById(jenisPengenalan));
        } else {
            pemohon.setJenisPengenalan(null);
        }
        pemohon.setNoPengenalan(nomborPengenalan);
        pemohon.setInfoAudit(ia);
        pemohon.setCawangan(permohonan.getCawangan());
        pemohonService.saveOrUpdate(pemohon);

        pihak = null;
        pihak = pihakDAO.findById(Long.valueOf(pemohon.getPihak().getIdPihak()));
        if (pihak != null) {
            ia = pihak.getInfoAudit();
            ia.setDiKemaskiniOleh(pguna);
            ia.setTarikhKemaskini(new java.util.Date());
        } else {
            pihak = new Pihak();
            ia.setDimasukOleh(pguna);
            ia.setTarikhMasuk(new java.util.Date());
        }
        pihak.setNama(nama);
        if (jenisPengenalan != null) {
            pihak.setJenisPengenalan(kodJenisPengenalanDAO.findById(jenisPengenalan));
        } else {
            pihak.setJenisPengenalan(null);
        }
        pihak.setNoPengenalan(nomborPengenalan);
        pihak.setAlamat1(alamat1);
        pihak.setAlamat2(alamat2);
        pihak.setAlamat3(alamat3);
        pihak.setAlamat4(alamat4);
        pihak.setPoskod(poskod);
        if (negeri != null && !negeri.equals("")) {
            pihak.setNegeri(kodNegeriDAO.findById(negeri));
        } else {
            pihak.setNegeri(null);
        }
        pihak.setSuratAlamat1(suratAlamat1);
        pihak.setSuratAlamat2(suratAlamat2);
        pihak.setSuratAlamat3(suratAlamat3);
        pihak.setSuratAlamat4(suratAlamat4);
        pihak.setSuratPoskod(suratPoskod);
        if (suratNegeri != null && !suratNegeri.equals("")) {
            pihak.setSuratNegeri(kodNegeriDAO.findById(suratNegeri));
        } else {
            pihak.setSuratNegeri(null);
        }
        pihak.setInfoAudit(ia);
        strService.simpanPihak(pihak);
        addSimpleMessage("Maklumat pemohon telah berjaya disimpan.");
        return new RedirectResolution(PechaPetakPemohonActionBean.class, "showForm");
    }

    public Resolution pechaPetakPemohonReadOnly() {
        getContext().getRequest().setAttribute("readOnly", Boolean.TRUE);
        return new JSP("strata/maklumat_pemohon_pechaPetak.jsp").addParameter("tab", "true");
    }

    public String getCheckAlamat() {
        return checkAlamat;
    }

    public void setCheckAlamat(String checkAlamat) {
        this.checkAlamat = checkAlamat;
    }

    public String getJenis() {
        return jenis;
    }

    public void setJenis(String jenis) {
        this.jenis = jenis;
    }

    public String getKodPengenalan() {
        return kodPengenalan;
    }

    public void setKodPengenalan(String kodPengenalan) {
        this.kodPengenalan = kodPengenalan;
    }

    public List<Pemohon> getListPemohon() {
        return listPemohon;
    }

    public void setListPemohon(List<Pemohon> listPemohon) {
        this.listPemohon = listPemohon;
    }

    public String getNamaPemohon() {
        return namaPemohon;
    }

    public void setNamaPemohon(String namaPemohon) {
        this.namaPemohon = namaPemohon;
    }

    public String getNegeri() {
        return negeri;
    }

    public void setNegeri(String negeri) {
        this.negeri = negeri;
    }

    public String getNoPengenalan() {
        return noPengenalan;
    }

    public void setNoPengenalan(String noPengenalan) {
        this.noPengenalan = noPengenalan;
    }

    public Pihak getPihak() {
        return pihak;
    }

    public void setPihak(Pihak pihak) {
        this.pihak = pihak;
    }

    public List<Pihak> getSenaraiPihak() {
        return senaraiPihak;
    }

    public void setSenaraiPihak(List<Pihak> senaraiPihak) {
        this.senaraiPihak = senaraiPihak;
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

    public String getSuratNegeri() {
        return suratNegeri;
    }

    public void setSuratNegeri(String suratNegeri) {
        this.suratNegeri = suratNegeri;
    }

    public String getSuratPoskod() {
        return suratPoskod;
    }

    public void setSuratPoskod(String suratPoskod) {
        this.suratPoskod = suratPoskod;
    }

    public String getIdPihak() {
        return idPihak;
    }

    public void setIdPihak(String idPihak) {
        this.idPihak = idPihak;
    }

    public String getJenisPengenalan() {
        return jenisPengenalan;
    }

    public void setJenisPengenalan(String jenisPengenalan) {
        this.jenisPengenalan = jenisPengenalan;
    }

    public String getJenisPengenalan2() {
        return jenisPengenalan2;
    }

    public void setJenisPengenalan2(String jenisPengenalan2) {
        this.jenisPengenalan2 = jenisPengenalan2;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getNomborPengenalan() {
        return nomborPengenalan;
    }

    public void setNomborPengenalan(String nomborPengenalan) {
        this.nomborPengenalan = nomborPengenalan;
    }

    public boolean isPenyerahAdalahPemohon() {
        return penyerahAdalahPemohon;
    }

    public void setPenyerahAdalahPemohon(boolean penyerahAdalahPemohon) {
        this.penyerahAdalahPemohon = penyerahAdalahPemohon;
    }

    public String getCarianNoPengenalan() {
        return carianNoPengenalan;
    }

    public void setCarianNoPengenalan(String carianNoPengenalan) {
        this.carianNoPengenalan = carianNoPengenalan;
    }

    public String getIndex() {
        return index;
    }

    public void setIndex(String index) {
        this.index = index;
    }

    public String getNegeri1() {
        return negeri1;
    }

    public void setNegeri1(String negeri1) {
        this.negeri1 = negeri1;
    }

    public String getSuratNegeri1() {
        return suratNegeri1;
    }

    public void setSuratNegeri1(String suratNegeri1) {
        this.suratNegeri1 = suratNegeri1;
    }

    public Permohonan getPermohonan() {
        return permohonan;
    }

    public void setPermohonan(Permohonan permohonan) {
        this.permohonan = permohonan;
    }

    /**
     * @return the suratMenyurat
     */
    public String getSuratMenyurat() {
        return suratMenyurat;
    }

    /**
     * @param suratMenyurat the suratMenyurat to set
     */
    public void setSuratMenyurat(String suratMenyurat) {
        this.suratMenyurat = suratMenyurat;
    }

    /**
     * @return the kodNegeri
     */
    public String getKodNegeri() {
        return kodNegeri;
    }

    /**
     * @param kodNegeri the kodNegeri to set
     */
    public void setKodNegeri(String kodNegeri) {
        this.kodNegeri = kodNegeri;
    }

    /**
     * @return the pat
     */
    public PihakAlamatTamb getPat() {
        return pat;
    }

    /**
     * @param pat the pat to set
     */
    public void setPat(PihakAlamatTamb pat) {
        this.pat = pat;
    }

    public String getSyor() {
        return syor;
    }

    public void setSyor(String syor) {
        this.syor = syor;
    }

    public List<HakmilikPihakBerkepentingan> getListHakmilikPihak() {
        return listHakmilikPihak;
    }

    public void setListHakmilikPihak(List<HakmilikPihakBerkepentingan> listHakmilikPihak) {
        this.listHakmilikPihak = listHakmilikPihak;
    }

    public Pemohon getPemohon1() {
        return pemohon1;
    }

    public void setPemohon1(Pemohon pemohon1) {
        this.pemohon1 = pemohon1;
    }

    public List<HakmilikPermohonan> getHakmilikPermohonanListBaru() {
        return hakmilikPermohonanListBaru;
    }

    public void setHakmilikPermohonanListBaru(List<HakmilikPermohonan> hakmilikPermohonanListBaru) {
        this.hakmilikPermohonanListBaru = hakmilikPermohonanListBaru;
    }
}
