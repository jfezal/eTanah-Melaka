/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.daftar.utiliti;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import com.wideplay.warp.persist.Transactional;
import etanah.model.*;
import etanah.service.*;
import etanah.dao.*;
import etanah.service.uam.UamService;
import etanah.view.etanahActionBeanContext;
import java.math.BigDecimal;
import net.sourceforge.stripes.action.*;
import org.apache.log4j.Logger;
import java.util.*;
import java.sql.*;
import java.io.*;
import oracle.sql.BLOB;
import javax.naming.*;
import net.sourceforge.stripes.action.ForwardResolution;
import org.apache.commons.lang.StringUtils;
import org.hibernate.*;

/**
 *
 * @author wan.fairul
 */
@UrlBinding("/utiliti/kod")
public class UtilitiKodActionBean extends AbleActionBean {

    private static final Logger logger = Logger.getLogger(UtilitiKodActionBean.class);
    String table;
    String kod;
    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;
    @Inject
    KodBandarPekanMukimDAO kodBandarPekanMukimDAO;
    @Inject
    KodSeksyenDAO kodSeksyenDAO;
    @Inject
    KodKegunaanBangunanDAO kodKegunaanBangunanDAO;
    @Inject
    KodWarganegaraDAO kodWarganegaraDAO;
    @Inject
    KodDaerahDAO kodDaerahDAO;
    @Inject
    KodKegunaanPetakDAO kodKegunaanPetakDAO;
    @Inject
    KodKategoriTanahDAO kodKategoriTanahDAO;
    @Inject
    KodKegunaanTanahDAO kodKegunaanTanahDAO;
    @Inject
    KodNegeriDAO kodNegeriDAO;
    @Inject
    KodBankDAO kodBankDAO;
    @Inject
    KodPenubuhanSyarikatDAO kodPenubuhanSyarikatDAO;
    @Inject
    KodUrusanDAO kodUrusanDAO;
    @Inject
    KodAkaunDAO kodAkaunDAO;
    @Inject
    KodKadarBayaranDAO kodKadarBayaranDAO;
    @Inject
    KodJabatanDAO kodJabatanDAO;
    @Inject
    KodCawanganDAO kodCawanganDAO;
    @Inject
    KodPerserahanDAO kodPerserahanDAO;
    @Inject
    KodTransaksiDAO kodTransaksiDAO;
    @Inject
    KodKategoriBayaranDAO kodKategoriBayaranDAO;
    @Inject
    RegService regService;
    @Inject
    StrataPtService strataPtService;
    @Inject
    KodKegunaanPetakAksesoriDAO kodKegunaanPetakAksesoriDAO;
    @Inject
    UamService uamService;
    @Inject
    KodKadarPremiumDAO kodKadarPremiumDAO;
    @Inject
    KodTanahDAO kodTanahDAO;
    @Inject
    UrusanDokumenDAO urusanDokumenDAO;
    @Inject
    KodDokumenDAO kodDokumenDAO;
    private List results = new ArrayList(0);
    private Object o;
    private Pengguna pengguna;
    private String kod1;
    private String nama;
    private String aktif;
    private String daerah;
    private String cawangan;
    private String kodBPM;
    private String namaKodBPM;
    private String bpm;
    private String kodSeksyen;
    private String namaKodSeksyen;
    private String etapp;
    private String cukai;
    private String katg;
    private String jabatan;
    private String kodSerah;
    private String rujKanun;
    private String urusanKaunter;
    private String urusanDalam;
    private String urusanBlkgKaunter;
    private String urusanBayar;
    private String kodTrans;
    private String kodKatgBayar;
    private String caj;
    private String idWorkflow;
    private String ptg;
    private String utama;
    private String sasarBulan;
    private String minHakmilik;
    private String maxHakmilik;
    private String idWorkflowIntegration;
    private String kodUrusan;
    private String milikDaerah;
    private String min;
    private String max;
    private String kadar;
    private String bilHakmilik;
    private String alamat1;
    private String alamat2;
    private String alamat3;
    private String alamat4;
    private String poskod;
    private String negeri;
    private String tel1;
    private String tel2;
    private String fax;
    private String kodPTJ;
    private String kodJabatanSpek;
    private KodUrusan urusan;
    private KodKadarBayaran kadarBayar;
    private KodCawangan kodCawangan;
    FileBean fileToBeUpload;
    Blob blob;
    String parameter;
    String kodBankBaru;
    String kodTransBaru;
    String namaTrans;
    String kategoriTransaksi;
    String akaunDebit;
    String akaunKredit;
    String keutamaan;
    String persekutuan;
    String transaksiAmanah;
    String perihal;
    private List<KodBandarPekanMukim> listBandarPekanMukim;
    String kodTanah;
    String kodKegunaanTanah;
    String peratusKadar;
    String idKodKadarPremium;
    private KodKadarPremium kadarPremium;
    private KodDokumen kodDokumen;
    String kawalCapaian;
    String penjana;
    String idUrusanDokumen;
    private UrusanDokumen urusanDokumen;
    String kodDokumen1;
    String wajib;
    String perluDisah;
    String perluDiimbas;
    String catatan;
    String kodTransaksi1;
    String kategoriPemohon;

    @DefaultHandler
    public Resolution showForm() {
        return new JSP("utiliti/utiliti_kod.jsp");
    }

    public Resolution cariKod() {
        if (table == null) {
            addSimpleError("Sila Pilih Kod");
            return new ForwardResolution("/utiliti/utiliti_kod.jsp");
        } else if (table != null && table.length() > 0) {
            try {
                if (table.equals("KodKadarPremium")) {
                    Session s = sessionProvider.get();
                    Query q = s.createQuery("from " + table + " order by LPAD(lower(idKodKadarPremium),10,0) asc ");
                    results = q.list(); 
                } else if (table.equals("UrusanDokumen")) {
                    Session s = sessionProvider.get();
                    Query q = s.createQuery("from " + table + " order by LPAD(lower(idUrusanDokumen),10,0) asc ");
                    results = q.list(); 
                } else {
                    Session s = sessionProvider.get();
                    Query q = s.createQuery("from " + table + " order by LPAD(lower(kod),10,0) asc ");
                    results = q.list();
                }
            } catch (Exception e) {
                addSimpleError(e.getMessage());
            }           
            if (table.equals("KodKegunaanPetakAksesori") || table.equals("KodKegunaanPetak") || table.equals("KodKegunaanBangunan")) {
                if (table != null && nama != null && kod1 != null) {
                    String namaLike = "'%" + nama + "%'";
                    String kod1Like = "'" + kod1 + "'";
                    try {
                        Session s = sessionProvider.get();
                        Query q = s.createQuery("from " + table + " where nama like " + namaLike
                                + " and kod = " + kod1Like
                                + " order by LPAD(lower(kod),10,0) asc ");
                        results = q.list();
                    } catch (Exception e) {
                        addSimpleError(e.getMessage());
                    }
                    return getContext().getSourcePageResolution();
                } else if (table != null && nama != null) {
                    String namaLike = "'%" + nama + "%'";
                    try {
                        Session s = sessionProvider.get();
                        Query q = s.createQuery("from " + table + " where nama like " + namaLike + " order by LPAD(lower(kod),10,0) asc ");
                        results = q.list();
                    } catch (Exception e) {
                        addSimpleError(e.getMessage());
                    }
                    return getContext().getSourcePageResolution();
                } else if (table != null && kod1 != null) {
                    String kod1Like = "'" + kod1 + "'";
                    try {
                        Session s = sessionProvider.get();
                        Query q = s.createQuery("from " + table + " where "
                                + " kod = " + kod1Like
                                + " order by LPAD(lower(kod),10,0) asc ");
                        results = q.list();
                    } catch (Exception e) {
                        addSimpleError(e.getMessage());
                    }
                    return getContext().getSourcePageResolution();
                }
            }
            return getContext().getSourcePageResolution();
        }
        return new ForwardResolution("/utiliti/utiliti_kod.jsp");
    
    }

    public Resolution cariByParameter() {
        if (table == null) {
            addSimpleError("Sila Pilih Kod");
            return new ForwardResolution("/utiliti/utiliti_kod.jsp");
        } else if (table != null && table.length() > 0) {
            try {
                if (parameter != null) {
                    if (table.equals("KodUrusan")) {
                        Session s = sessionProvider.get();
                        Query q = s.createQuery("SELECT p FROM etanah.model." + table + " p WHERE p.kod =:parameter");
                        q.setString("parameter", parameter);
                        results = q.list();
                    } else if (table.equals("KodKadarBayaran")) {
                        Session s = sessionProvider.get();
                        Query q = s.createQuery("SELECT p FROM etanah.model." + table + " p WHERE p.kodUrusan.kod =:parameter");
                        q.setString("parameter", parameter);
                        results = q.list();
                    } else if (table.equals("KodDokumen")) {
                        Session s = sessionProvider.get();
                        Query q = s.createQuery("SELECT p FROM etanah.model." + table + " p WHERE p.kod =:parameter");
                        q.setString("parameter", parameter);
                        results = q.list();
                    } else if (table.equals("UrusanDokumen")) {
                        Session s = sessionProvider.get();
                        Query q = s.createQuery("SELECT p FROM etanah.model." + table + " p WHERE p.kodUrusan.kod =:parameter");
                        q.setString("parameter", parameter);
                        results = q.list();
                    }
                } else {
                    if (table.equals("kodKadarPremium")) {
                        Session s = sessionProvider.get();
                        Query q = s.createQuery("from " + table + " order by LPAD(lower(idKodKadarPremium),10,0) asc ");
                        results = q.list(); 
                    } else if (table.equals("UrusanDokumen")) {
                        Session s = sessionProvider.get();
                        Query q = s.createQuery("from " + table + " order by LPAD(lower(idUrusanDokumen),10,0) asc ");
                        results = q.list(); 
                    } else {
                        Session s = sessionProvider.get();
                        Query q = s.createQuery("from " + table + " order by LPAD(lower(kod),10,0) asc ");
                        results = q.list();  
                    }  
                }
            } catch (Exception e) {
                addSimpleError(e.getMessage());
            }
            if (table.equals("KodKegunaanPetakAksesori") || table.equals("KodKegunaanPetak") || table.equals("KodKegunaanBangunan")) {
                if (table != null && nama != null && kod1 != null) {
                    String namaLike = "'%" + nama + "%'";
                    String kod1Like = "'" + kod1 + "'";
                    try {
                        Session s = sessionProvider.get();
                        Query q = s.createQuery("from " + table + " where nama like " + namaLike
                                + " and kod = " + kod1Like
                                + " order by LPAD(lower(kod),10,0) asc ");
                        results = q.list();
                    } catch (Exception e) {
                        addSimpleError(e.getMessage());
                    }
                    return getContext().getSourcePageResolution();
                } else if (table != null && nama != null) {
                    String namaLike = "'%" + nama + "%'";
                    try {
                        Session s = sessionProvider.get();
                        Query q = s.createQuery("from " + table + " where nama like " + namaLike + " order by LPAD(lower(kod),10,0) asc ");
                        results = q.list();
                    } catch (Exception e) {
                        addSimpleError(e.getMessage());
                    }
                    return getContext().getSourcePageResolution();
                } else if (table != null && kod1 != null) {
                    String kod1Like = "'" + kod1 + "'";
                    try {
                        Session s = sessionProvider.get();
                        Query q = s.createQuery("from " + table + " where "
                                + " kod = " + kod1Like
                                + " order by LPAD(lower(kod),10,0) asc ");
                        results = q.list();
                    } catch (Exception e) {
                        addSimpleError(e.getMessage());
                    }
                    return getContext().getSourcePageResolution();
                }
            }
            return getContext().getSourcePageResolution();
        }
        return new ForwardResolution("/utiliti/utiliti_kod.jsp");

    }

    public List<KodKadarBayaran> findKodByrByKod(String parameter) {
        Session s = sessionProvider.get();
        Query q = s.createQuery("Select kb from etanah.model.KodKadarBayaran kb where kb.kod = :parameter ");
        q.setString("parameter", parameter);
        return q.list();
    }

    public Resolution showPopup() {
        kod = getContext().getRequest().getParameter("kod");
        table = getContext().getRequest().getParameter("table");
        if (table != null && table.length() > 0 && kod != null) {
            try {
                if (table.equals("KodUrusan")) {
                    Session s = sessionProvider.get();
                    Query q = s.createQuery("SELECT p FROM etanah.model.KodUrusan p WHERE kod =:kod");
                    q.setString("kod", kod);
                    urusan = (KodUrusan) q.uniqueResult();
                } else if (table.equals("KodKadarBayaran")) {
                    Session s = sessionProvider.get();
                    Query q = s.createQuery("SELECT p FROM etanah.model.KodKadarBayaran p WHERE kod =:kod");
                    q.setString("kod", kod);
                    kadarBayar = (KodKadarBayaran) q.uniqueResult();
                } else if (table.equals("KodCawangan")) {
                    Session s = sessionProvider.get();
                    Query q = s.createQuery("SELECT p FROM etanah.model.KodCawangan p WHERE kod =:kod");
                    q.setString("kod", kod);
                    kodCawangan = (KodCawangan) q.uniqueResult();

                    if (kodCawangan != null) {
                        alamat1 = kodCawangan.getAlamat().getAlamat1();
                        alamat2 = kodCawangan.getAlamat().getAlamat2();
                        alamat3 = kodCawangan.getAlamat().getAlamat3();
                        alamat4 = kodCawangan.getAlamat().getAlamat4();
                        poskod = kodCawangan.getAlamat().getPoskod();
                        negeri = kodCawangan.getAlamat().getNegeri().getKod();
                        blob = kodCawangan.getCop();
                    }
                } else if (table.equals("KodDokumen")) {
                    Session s = sessionProvider.get();
                    Query q = s.createQuery("SELECT p FROM etanah.model.KodDokumen p WHERE kod =:kod");
                    q.setString("kod", kod);
                    kodDokumen = (KodDokumen) q.uniqueResult();
                } else {
                    if (table.equals("KodKadarPremium")) {
                        Session s = sessionProvider.get();
                        Query q = s.createQuery("SELECT p FROM etanah.model.KodKadarPremium p WHERE idKodKadarPremium =:kod");
                        q.setString("kod", kod);
                        kadarPremium = (KodKadarPremium) q.uniqueResult();
                    } else if (table.equals("UrusanDokumen")) {
                        Session s = sessionProvider.get();
                        Query q = s.createQuery("SELECT p FROM etanah.model.UrusanDokumen p WHERE idUrusanDokumen =:kod");
                        q.setString("kod", kod);
                        urusanDokumen = (UrusanDokumen) q.uniqueResult();
                    } else {
                        Session s = sessionProvider.get();
                        Query q = s.createQuery("from " + table + " where kod =:kod");
                        q.setString("kod", kod);
                        o = q.uniqueResult();
                    }
                }
            } catch (Exception e) {
                addSimpleError(e.getMessage());
            }
            return new JSP("utiliti/edit_kod.jsp").addParameter("popup", "true");
        }
        return new JSP("utiliti/edit_kod.jsp").addParameter("popup", "true");
    }

    public Resolution tambahKod() {
        table = getContext().getRequest().getParameter("table");
        pengguna = (Pengguna) context.getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        getContext().getRequest().setAttribute("add", Boolean.TRUE);
        return new JSP("utiliti/tambah_kod.jsp").addParameter("popup", "true");
    }

    public Resolution simpanKod() {
        table = getContext().getRequest().getParameter("table");
        pengguna = (Pengguna) context.getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        getContext().getRequest().setAttribute("add", Boolean.FALSE);

        InfoAudit ia = new InfoAudit();
        ia.setDimasukOleh(pengguna);
        ia.setTarikhMasuk(new java.util.Date());

        Alamat alamat = new Alamat();
        if (alamat1 != null) {
            alamat.setAlamat1(alamat1);
            alamat.setAlamat2(alamat2);
            alamat.setAlamat3(alamat3);
            alamat.setAlamat4(alamat4);
            alamat.setPoskod(poskod);
            alamat.setNegeri(kodNegeriDAO.findById(negeri));
        }

        int maxkod = 0;
        if (table.equals("KodBandarPekanMukim")) {
            maxkod = strataPtService.maxkodBpm() + 1;
            kod1 = String.valueOf(maxkod);
        } else if (table.equals("KodSeksyen")) {
            maxkod = strataPtService.maxkodSeksyen() + 1;
            kod1 = String.valueOf(maxkod);
        } else if (table.equals("KodKategoriTanah")) {
            maxkod = strataPtService.maxKodKategoriTanah() + 1;
            kod1 = String.valueOf(maxkod);
        } else if (table.equals("KodPenubuhanSyarikat")) {
            maxkod = strataPtService.maxKodSyktTubuh() + 1;
            kod1 = String.valueOf(maxkod);
        } else if (table.equals("KodKadarBayaran")) {
            maxkod = strataPtService.maxKodKadarBayaran() + 1;
            kod1 = String.valueOf(maxkod);
        } else if (table.equals("KodBank")) {
            maxkod = strataPtService.maxKodKadarBayaran() + 1;
            kod1 = String.valueOf(maxkod);
        } else if (table.equals("KodTransaksi")) {
            maxkod = strataPtService.maxKodKadarBayaran() + 1;
            kod1 = String.valueOf(maxkod);
        } else if (table.equals("KodKadarPremium")) {
            maxkod = strataPtService.maxKodKadarPremium() + 1;
            kod1 = String.valueOf(maxkod);
        } else if (table.equals("UrusanDokumen")) {
            maxkod = strataPtService.maxUrusanDokumen() + 1;
            kod1 = String.valueOf(maxkod);
            logger.debug("kod1 :" + kod1);
        }
        logger.debug("nama :" + nama);
        logger.debug("table :" + table);
        if (nama != null && kod1 != null && table != null) {
            if (table.equals("KodKadarPremium")){
                Session s = sessionProvider.get();
                Query q = s.createQuery("from " + table + " where idKodKadarPremium =:kod");
                q.setString("kod", kod1);
                o = q.uniqueResult();
                if (o == null) {
                    if (table.equals("KodKadarPremium")) {
                        KodKadarPremium kdrPremium = new KodKadarPremium();
                        //kdrPremium.setIdKodKadarPremium(Integer.parseInt(kod1));
                        kdrPremium.setKodTanah(kodTanahDAO.findById(kodTanah));
                        kdrPremium.setKodKegunaanTanah(kodKegunaanTanahDAO.findById(kodKegunaanTanah));
                        kdrPremium.setNama(nama);
                        kdrPremium.setPeratusKadar(BigDecimal.valueOf(Double.valueOf(peratusKadar)));
                        kdrPremium.setInfoAudit(ia);
                        kdrPremium.setAktif(aktif.charAt(0));
                        saveKodKadarPremium(kdrPremium);
                        addSimpleMessage("Kod berjaya disimpan");
                    }
                } else {
                    addSimpleError("Kod yang dimasukkan terdapat di dalam pangkalan data. ");
                }
            /*} else if (table.equals("UrusanDokumen")){
                Session s = sessionProvider.get();
                Query q = s.createQuery("from " + table + " where idUrusanDokumen =:kod");
                q.setString("kod", kod1);
                o = q.uniqueResult();
                if (o == null) {
                    if (table.equals("UrusanDokumen")) {
                        UrusanDokumen urusDok = new UrusanDokumen();
                        urusDok.setIdUrusanDokumen(Integer.parseInt(kod1));
                        urusDok.setKodUrusan(kodUrusanDAO.findById(kodUrusan));
                            KodDokumen kd = new KodDokumen();
                            kd.setKod(kodDokumen1);
                        urusDok.setKodDokumen(kd);
                        urusDok.setWajib(wajib.charAt(0));
                        urusDok.setPerluDisah(perluDisah.charAt(0));
                        urusDok.setPerluDiimbas(perluDiimbas.charAt(0));
                        urusDok.setCatatan(catatan);
                        urusDok.setCaj(BigDecimal.valueOf(Double.valueOf(caj)));
                        urusDok.setKodTransaksi(kodTransaksiDAO.findById(kodTrans));
                        urusDok.setKategoriPemohon(kategoriPemohon.charAt(0));
                        urusDok.setInfoAudit(ia);
                        urusDok.setAktif(aktif.charAt(0));
                        saveUrusanDokumen(urusDok);
                        addSimpleMessage("Kod berjaya disimpan");
                    }
                } else {
                    addSimpleError("Kod yang dimasukkan terdapat di dalam pangkalan data. ");
                }*/
            } else {
                Session s = sessionProvider.get();
                Query q = s.createQuery("from " + table + " where kod =:kod");
                q.setString("kod", kod1);
                o = q.uniqueResult();
                if (o == null) {
                    if (table.equals("KodKegunaanPetak")) {
                        KodKegunaanPetak gunaPetak = new KodKegunaanPetak();
                        gunaPetak.setKod(kod1);
                        gunaPetak.setNama(nama);
                        gunaPetak.setPerihal(nama);
                        gunaPetak.setInfoAudit(ia);
                        gunaPetak.setAktif(aktif.charAt(0));
                        saveGunaPetak(gunaPetak);
                        addSimpleMessage("Kod berjaya disimpan");

                    } else if (table.equals("KodKegunaanPetakAksesori")) {
                        KodKegunaanPetakAksesori gunaPetakAksr = new KodKegunaanPetakAksesori();
                        gunaPetakAksr.setKod(kod1);
                        gunaPetakAksr.setNama(nama);
                        gunaPetakAksr.setPerihal(nama);
                        gunaPetakAksr.setInfoAudit(ia);
                        gunaPetakAksr.setAktif(aktif);
                        saveGunaPetakAksr(gunaPetakAksr);
                        addSimpleMessage("Kod berjaya disimpan");

                    } else if (table.equals("KodTransaksi")) {
                        KodTransaksi kodTrans = new KodTransaksi();
                        kodTrans.setKod(kod1);
                        kodTrans.setNama(nama);
                        if (akaunDebit != null) {
                            KodAkaun kodAkaunDebit = kodAkaunDAO.findById(akaunDebit);
                            kodTrans.setAkaunDebit(kodAkaunDebit);
                        }
                        if (akaunKredit != null) {
                            KodAkaun kodAkaunKredit = kodAkaunDAO.findById(akaunKredit);
                            kodTrans.setAkaunDebit(kodAkaunKredit);
                        }
    //                    kodTrans.setKategoriTransaksi(null);
                        kodTrans.setTransaksiAmanah(transaksiAmanah.charAt(0));
    //                    kodTrans.setPerihal(perihal);
                        kodTrans.setUntukPersekutuan(persekutuan.charAt(0));
                        kodTrans.setInfoAudit(ia);
                        kodTrans.setAktif(aktif.charAt(0));
                        kodTransaksiDAO.saveOrUpdate(kodTrans);
                        addSimpleMessage("Kod berjaya disimpan");

                    } else if (table.equals("KodBandarPekanMukim")) {
                        KodBandarPekanMukim kodBandarPekanMukim = new KodBandarPekanMukim();
                        kodBandarPekanMukim.setKod(Integer.parseInt(kodTransBaru));
                        kodBandarPekanMukim.setBandarPekanMukim(kodBPM);
                        kodBandarPekanMukim.setNama(nama);
                        kodBandarPekanMukim.setInfoAudit(ia);
    //                    kodBandarPekanMukim.setKodNotis(namaKodBPM);
                        kodBandarPekanMukim.setAktif(aktif.charAt(0));
                        saveBpm(kodBandarPekanMukim);
                        addSimpleMessage("Kod berjaya disimpan");

                    } else if (table.equals("KodBank")) {
                        KodBank kodBank = new KodBank();
                        kodBank.setKod(kodBankBaru);
                        kodBank.setNama(nama);
                        kodBank.setCawangan(kodCawangan);
                        kodBank.setInfoAudit(ia);
                        kodBank.setAktif(aktif.charAt(0));
                        addSimpleMessage("Kod berjaya disimpan");

                    } else if (table.equals("KodSeksyen")) {
                        KodBandarPekanMukim kodbpm = strataPtService.findKodBandarPekanMukim(bpm);
                        KodSeksyen kodSeks = new KodSeksyen();

                        kodSeks.setKod(maxkod);
                        kodSeks.setKodBandarPekanMukim(kodbpm);
                        kodSeks.setSeksyen(kodSeksyen);
                        kodSeks.setNama(nama);
                        kodSeks.setInfoAudit(ia);
                        kodSeks.setAktif(aktif);
                        saveSeksyen(kodSeks);
                        addSimpleMessage("Kod berjaya disimpan");
                        
                    } else if (table.equals("KodDaerah")) {
                        KodDaerah kodDaerah = new KodDaerah();
                        kodDaerah.setKod(kod1);
                        kodDaerah.setNama(nama);
                        kodDaerah.setInfoAudit(ia);
                        kodDaerah.setAktif(aktif.charAt(0));
                        saveDaerah(kodDaerah);
                        addSimpleMessage("Kod berjaya disimpan");
                        
                    } else if (table.equals("KodKategoriTanah")) {
                        KodKategoriTanah kodKategoriTanah = new KodKategoriTanah();
                        kodKategoriTanah.setAktif(aktif.charAt(0));
                        kodKategoriTanah.setInfoAudit(ia);
                        kodKategoriTanah.setKatgMyETapp(etapp);
                        kodKategoriTanah.setKod(kod1);
                        kodKategoriTanah.setNama(nama);
                        saveKodKategoriTanah(kodKategoriTanah);
                        addSimpleMessage("Kod berjaya disimpan");
                        
                    } else if (table.equals("KodKegunaanTanah")) {
                        KodKegunaanTanah kodKegunaanTanah = new KodKegunaanTanah();
                        kodKegunaanTanah.setKod(kod1);
                        kodKegunaanTanah.setAktif(aktif.charAt(0));
                        kodKegunaanTanah.setInfoAudit(ia);
                        kodKegunaanTanah.setKategoriTanah(kodKategoriTanahDAO.findById(katg));
                        kodKegunaanTanah.setKodGunaTanahKodCkai(cukai);
                        kodKegunaanTanah.setKodMyEtapp(etapp);
                        kodKegunaanTanah.setNama(nama);
                        saveKodKegunaanTanah(kodKegunaanTanah);
                        addSimpleMessage("Kod berjaya disimpan");
                        
                    } else if (table.equals("KodNegeri")) {
                        KodNegeri kodNegeri = new KodNegeri();
                        kodNegeri.setKod(kod1);
                        kodNegeri.setAktif(aktif.charAt(0));
                        kodNegeri.setInfoAudit(ia);
                        if (nama != null) {
                            kodNegeri.setNama(nama);
                        } else {
                            kodNegeri.setNama(null);
                        }
                        savekodNegeri(kodNegeri);
                        addSimpleMessage("Kod berjaya disimpan");
                        
                    } else if (table.equals("KodBank")) {
                        KodBank kodBank = new KodBank();
                        kodBank.setKod(kod1);
                        kodBank.setAktif(aktif.charAt(0));
                        kodBank.setInfoAudit(ia);
                        if (nama != null) {
                            kodBank.setNama(nama);
                        } else {
                            kodBank.setNama(null);
                        }
                        savekodBank(kodBank);
                        addSimpleMessage("Kod berjaya disimpan");
                        
                    } else if (table.equals("KodPenubuhanSyarikat")) {
                        KodPenubuhanSyarikat kodSyktTubuh = new KodPenubuhanSyarikat();
                        kodSyktTubuh.setKod(kod1);
                        kodSyktTubuh.setNama(nama);
                        kodSyktTubuh.setInfoAudit(ia);
                        kodSyktTubuh.setAktif(aktif.charAt(0));
                        saveKodSyktTubuh(kodSyktTubuh);
                        addSimpleMessage("Kod berjaya disimpan");
                        
                    } else if (table.equals("KodKegunaanBangunan")) {
                        KodKegunaanBangunan gunaBngn = new KodKegunaanBangunan();
                        gunaBngn.setKod(kod1);
                        gunaBngn.setNama(nama);
                        gunaBngn.setInfoAudit(ia);
                        gunaBngn.setAktif(aktif.charAt(0));
                        saveKodKegunaanBangunan(gunaBngn);
                        addSimpleMessage("Kod berjaya disimpan");
                        
                    } else if (table.equals("KodWarganegara")) {
                        KodWarganegara warga = new KodWarganegara();
                        warga.setKod(kod1);
                        warga.setNama(nama);
                        warga.setInfoAudit(ia);
                        warga.setAktif(aktif.charAt(0));
                        saveKodWarganegara(warga);
                        addSimpleMessage("Kod berjaya disimpan");
                        
                    } else if (table.equals("KodDokumen")) {
                        KodDokumen kodDok = new KodDokumen();
                        kodDok.setKod(kod1);
                        kodDok.setNama(nama);
                        kodDok.setInfoAudit(ia);
                        kodDok.setAktif(aktif.charAt(0));
                        kodDok.setKawalCapaian(kawalCapaian.charAt(0));
                        kodDok.setPenjana(penjana);
                        saveKodDokumen(kodDok);
                        addSimpleMessage("Kod berjaya disimpan");
            
                    } else if (table.equals("KodUrusan")) {
                        KodUrusan urusan = new KodUrusan();
                        urusan.setKod(kod1);
                        urusan.setKodUrusanRayuan(kodUrusanDAO.findById(kod1));
                        urusan.setNama(nama);
                        urusan.setInfoAudit(ia);
                        urusan.setAktif(aktif.charAt(0));
                        urusan.setJabatan(kodJabatanDAO.findById(jabatan));
                        urusan.setKodPerserahan(kodPerserahanDAO.findById(kodSerah));
                        urusan.setRujukanKanun(rujKanun);
                        urusan.setUrusanKaunter(urusanKaunter.charAt(0));
                        urusan.setUrusanBelakangKaunter(urusanBlkgKaunter.charAt(0));
                        urusan.setUrusanDalaman(urusanDalam.charAt(0));
                        urusan.setUrusanBayaran(urusanBayar.charAt(0));
                        urusan.setKodTransaksi(kodTransaksiDAO.findById(kodTrans));
                        if (kodKatgBayar != null) {
                            urusan.setKategoriBayaran(kodKategoriBayaranDAO.findById(kodKatgBayar));
                        }

                        if (caj != null) {
                            urusan.setCaj(BigDecimal.valueOf(Double.valueOf(caj)));
                        }
                        urusan.setIdWorkflow(idWorkflow);
                        urusan.setPerluJelasCukai(cukai.charAt(0));
                        urusan.setKePTG(ptg.charAt(0));
                        if (utama != null) {
                            urusan.setUtama(Integer.valueOf(utama));
                        }
                        if (sasarBulan != null) {
                            urusan.setSasaranBulan(Integer.valueOf(sasarBulan));
                        }
                        if (minHakmilik != null) {
                            urusan.setBilMinimumHakmilik(Integer.valueOf(minHakmilik));
                        }
                        if (maxHakmilik != null) {
                            urusan.setBilMaksimumHakmilik(Integer.valueOf(maxHakmilik));
                        }
                        urusan.setIdWorkflowIntegration(idWorkflowIntegration);
                        saveKodUrusan(urusan);
                        addSimpleMessage("Kod berjaya disimpan");
                        
                    } else if (table.equals("KodCawangan")) {
                        KodCawangan caw = new KodCawangan();
                        caw.setKod(kod1);
                        caw.setName(nama);
                        caw.setInfoAudit(ia);
                        caw.setAktif(aktif.charAt(0));
                        caw.setAlamat(alamat);
                        caw.setDaerah(kodDaerahDAO.findById(daerah));
                        caw.setNoTelefon(tel1);
                        caw.setNoTelefon2(tel2);
                        caw.setNoFax(fax);
                        caw.setKodJabatanSpek(kodJabatanSpek);
                        caw.setKodPTJ(kodPTJ);
                        saveKodCawangan(caw);

                        if (fileToBeUpload != null) {
                            try {
                                Blob imej = Hibernate.createBlob(fileToBeUpload.getInputStream());
                                caw.setCop(imej);
                                updateKodCawangan(caw);

                            } catch (Exception e) {
                                e.printStackTrace();
                                addSimpleError("Cop Tidak Berjaya Disimpan");
                            }

                            addSimpleMessage("Cop Berjaya Disimpan");
                        }

                        addSimpleMessage("Kod berjaya disimpan");
                    }
                } else {
                    addSimpleError("Sila pastikan kod yang dimasukkan tidak terdapat di dalam pangkalan data! ");
                }
            }
        }
        if (table.equals("KodKadarBayaran")) {
            KodKadarBayaran bayar = new KodKadarBayaran();
            bayar.setKod(maxkod);
            bayar.setKodUrusan(kodUrusanDAO.findById(kodUrusan));
            bayar.setInfoAudit(ia);
            bayar.setMilikDaerah(milikDaerah.charAt(0));
            bayar.setKategori(katg);
            if (bilHakmilik != null) {
                bayar.setBilHakmilik(Integer.parseInt(bilHakmilik));
            }

            if (min != null) {
                bayar.setNilaiMinimum(BigDecimal.valueOf(Double.valueOf(min)));
            }
            if (max != null) {
                bayar.setNilaiMaksimum(BigDecimal.valueOf(Double.valueOf(max)));
            }
            if (min != null) {
                bayar.setKadar(BigDecimal.valueOf(Double.valueOf(kadar)));
            }
            bayar.setAktif(aktif.charAt(0));
            saveKodKadarBayaran(bayar);
            addSimpleMessage("Kod berjaya disimpan");
        }
        if (table.equals("KodTransaksi")) {
            KodTransaksi kodTrans = new KodTransaksi();
            kodTrans.setKod(kodTransBaru);
            kodTrans.setNama(namaTrans);
            if (akaunDebit != null) {
                KodAkaun kodAkaunDebit = kodAkaunDAO.findById(akaunDebit);
                kodTrans.setAkaunDebit(kodAkaunDebit);
            }
            if (akaunKredit != null) {
                KodAkaun kodAkaunKredit = kodAkaunDAO.findById(akaunKredit);
                kodTrans.setAkaunDebit(kodAkaunKredit);
            }
//                    kodTrans.setKategoriTransaksi(null);
            kodTrans.setTransaksiAmanah(transaksiAmanah.charAt(0));
//                    kodTrans.setPerihal(perihal);
            kodTrans.setUntukPersekutuan(persekutuan.charAt(0));
            kodTrans.setInfoAudit(ia);
            kodTrans.setAktif(aktif.charAt(0));
            updateKodTransaksi(kodTrans);
//            kodTransaksiDAO.saveOrUpdate(kodTrans);

            addSimpleMessage("Kod berjaya disimpan");

        }
        if (table.equals("KodBank")) {
            KodBank kodBank = new KodBank();
            kodBank.setKod(kodBankBaru);
            kodBank.setNama(nama);
            kodBank.setCawangan(kodCawangan);
            kodBank.setInfoAudit(ia);
            kodBank.setAktif(aktif.charAt(0));
            saveKodBank(kodBank);
            addSimpleMessage("Kod berjaya disimpan");
        }
        if (table.equals("UrusanDokumen")){
                Session s = sessionProvider.get();
                Query q = s.createQuery("from " + table + " where idUrusanDokumen =:kod");
                q.setString("kod", kod1);
                o = q.uniqueResult();
                kodDokumen = kodDokumenDAO.findById(kodDokumen1);
                if (o == null) {
                    if (table.equals("UrusanDokumen")) {
                        logger.debug("kodUrusan :" + kodUrusan);
                        logger.debug("kodDokumen1 :" + kodDokumen1);
                        logger.debug("caj :" + caj);
                        logger.debug("kodTransaksi1 :" + kodTransaksi1);
                        UrusanDokumen urusDok = new UrusanDokumen();
                        urusDok.setKodUrusan(kodUrusanDAO.findById(kodUrusan));
                        urusDok.setKodDokumen(kodDokumen);
                        urusDok.setWajib(wajib.charAt(0));
                        urusDok.setPerluDisah(perluDisah.charAt(0));
                        urusDok.setPerluDiimbas(perluDiimbas.charAt(0));
                        urusDok.setCatatan(catatan);
                        if (caj != null) {
                            urusDok.setCaj(BigDecimal.valueOf(Double.valueOf(caj)));
                        }
                        if (kodTransaksi1 != null) {
                            urusDok.setKodTransaksi(kodTransaksiDAO.findById(kodTransaksi1));
                        }
                        urusDok.setKategoriPemohon(kategoriPemohon.charAt(0));
                        urusDok.setInfoAudit(ia);
                        urusDok.setAktif(aktif.charAt(0));
                        saveUrusanDokumen(urusDok);
                        addSimpleMessage("Kod berjaya disimpan");
                    }
                }
        }
        return new JSP("utiliti/tambah_kod.jsp").addParameter("popup", "true");
    }

    public Resolution updateKod() {
        table = getContext().getRequest().getParameter("table");
        pengguna = (Pengguna) context.getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        getContext().getRequest().setAttribute("add", Boolean.FALSE);

        InfoAudit ia2 = new InfoAudit();
        ia2.setDiKemaskiniOleh(pengguna);
        ia2.setTarikhKemaskini(new java.util.Date());

        if (nama == null) {
            nama = "";
        }
        if (table != null) {
            if (table.equals("KodUrusan")) {
                Session s = sessionProvider.get();
                Query q = s.createQuery("SELECT p FROM etanah.model.KodUrusan p WHERE kod =:kod");
                q.setString("kod", kod);
                KodUrusan urus = (KodUrusan) q.uniqueResult();

                if (urus != null) {
                    urus.setKodUrusanRayuan(kodUrusanDAO.findById(kod));
                    urus.setNama(urusan.getNama());
                    urus.setInfoAudit(ia2);
                    urus.setAktif(aktif.charAt(0));
                    urus.setJabatan(urusan.getJabatan());
                    urus.setKodPerserahan(urusan.getKodPerserahan());
                    urus.setRujukanKanun(urusan.getRujukanKanun());
                    urus.setUrusanKaunter(urusan.getUrusanKaunter());
                    urus.setUrusanBelakangKaunter(urusan.getUrusanBelakangKaunter());
                    urus.setUrusanDalaman(urusan.getUrusanDalaman());
                    urus.setUrusanBayaran(urusan.getUrusanBayaran());
                    urus.setKodTransaksi(urusan.getKodTransaksi());
                    urus.setKategoriBayaran(urusan.getKategoriBayaran());
                    urus.setCaj(urusan.getCaj());
                    urus.setIdWorkflow(urusan.getIdWorkflow());
                    urus.setPerluJelasCukai(urusan.getPerluJelasCukai());
                    urus.setKePTG(urusan.getKePTG());
                    urus.setUtama(urusan.getUtama());
                    urus.setSasaranBulan(urusan.getSasaranBulan());
                    urus.setBilMinimumHakmilik(urusan.getBilMinimumHakmilik());
                    urus.setBilMaksimumHakmilik(urusan.getBilMaksimumHakmilik());
                    urus.setIdWorkflowIntegration(urusan.getIdWorkflowIntegration());

                    updateKodUrusan(urus);
                    addSimpleMessage("Kod berjaya dikemaskini.");
                }
            } else if (table.equals("KodKadarBayaran")) {
                Session s = sessionProvider.get();
                Query q = s.createQuery("SELECT p FROM etanah.model.KodKadarBayaran p WHERE kod =:kod");
                q.setString("kod", kod);
                KodKadarBayaran bayar = (KodKadarBayaran) q.uniqueResult();

                if (bayar != null) {
                    bayar.setKodUrusan(kodUrusanDAO.findById(kadarBayar.getKodUrusan().getKod()));
                    bayar.setMilikDaerah(kadarBayar.getMilikDaerah());
                    bayar.setInfoAudit(ia2);
                    bayar.setAktif(aktif.charAt(0));
                    bayar.setKategori(kadarBayar.getKategori());
                    bayar.setNilaiMinimum(kadarBayar.getNilaiMinimum());
                    bayar.setNilaiMaksimum(kadarBayar.getNilaiMaksimum());
                    bayar.setBilHakmilik(kadarBayar.getBilHakmilik());
                    bayar.setKadar(kadarBayar.getKadar());

                    updateKodKadarBayaran(bayar);
                    addSimpleMessage("Kod berjaya dikemaskini.");
                }
            } else if (table.equals("KodCawangan")) {
                Session s = sessionProvider.get();
                Query q = s.createQuery("SELECT p FROM etanah.model.KodCawangan p WHERE kod =:kod");
                q.setString("kod", kod);
                KodCawangan caw = (KodCawangan) q.uniqueResult();

                Alamat alamat = new Alamat();
                if (alamat1 != null) {
                    alamat.setAlamat1(alamat1);
                    alamat.setAlamat2(alamat2);
                    alamat.setAlamat3(alamat3);
                    alamat.setAlamat4(alamat4);
                    alamat.setPoskod(poskod);
                    alamat.setNegeri(kodNegeriDAO.findById(negeri));
                }

                if (caw != null) {
                    caw.setName(kodCawangan.getName());
                    caw.setAktif(aktif.charAt(0));
                    caw.setAlamat(alamat);
                    caw.setKodJabatanSpek(kodCawangan.getKodJabatanSpek());
                    caw.setKodPTJ(kodCawangan.getKodPTJ());
                    caw.setNoTelefon(kodCawangan.getNoTelefon());
                    caw.setNoTelefon2(kodCawangan.getNoTelefon2());
                    caw.setNoFax(kodCawangan.getNoFax());
                    updateKodCawangan(caw);

                    if (fileToBeUpload != null) {
                        try {
                            Blob imej = Hibernate.createBlob(fileToBeUpload.getInputStream());
                            caw.setCop(imej);
                            updateKodCawangan(caw);

                        } catch (Exception e) {
                            e.printStackTrace();
                            addSimpleError("Cop Tidak Berjaya Disimpan");
                        }

                        addSimpleMessage("Cop Berjaya Disimpan");
                    }

                    addSimpleMessage("Kod berjaya dikemaskini.");
                }
            } else if (table.equals("KodKadarPremium")) {
                Session s = sessionProvider.get();
                Query q = s.createQuery("SELECT kp FROM etanah.model.KodKadarPremium kp WHERE idKodKadarPremium =:kod");
                q.setString("kod", kod);
                KodKadarPremium kodPremium = (KodKadarPremium) q.uniqueResult();

                if (kodPremium != null) {
                    kodPremium.setKodTanah(kodTanahDAO.findById(kadarPremium.getKodTanah().getKod()));
                    kodPremium.setKodKegunaanTanah(kodKegunaanTanahDAO.findById(kadarPremium.getKodKegunaanTanah().getKod()));
                    kodPremium.setNama(kadarPremium.getNama());
                    kodPremium.setPeratusKadar(kadarPremium.getPeratusKadar());
                    kodPremium.setInfoAudit(ia2);
                    kodPremium.setAktif(aktif.charAt(0));

                    updateKodKadarPremium(kodPremium);
                    addSimpleMessage("Kod berjaya dikemaskini.");
                }
            } else if (table.equals("KodDokumen")) {
                Session s = sessionProvider.get();
                Query q = s.createQuery("SELECT p FROM etanah.model.KodDokumen p WHERE kod =:kod");
                q.setString("kod", kod);
                KodDokumen kodDok1 = (KodDokumen) q.uniqueResult();

                if (kodDok1 != null) {
                    kodDok1.setNama(kodDokumen.getNama());
                    kodDok1.setInfoAudit(ia2);
                    kodDok1.setAktif(aktif.charAt(0));
                    kodDok1.setKawalCapaian(kodDokumen.getKawalCapaian());
                    kodDok1.setPenjana(kodDokumen.getPenjana());
                    updateKodDokumen(kodDok1);
                    addSimpleMessage("Kod Dokumen berjaya dikemaskini.");
                }
            } else if (table.equals("UrusanDokumen")) {
                Session s = sessionProvider.get();
                Query q = s.createQuery("SELECT p FROM etanah.model.UrusanDokumen p WHERE idUrusanDokumen =:kod");
                q.setString("kod", kod);
                UrusanDokumen urusDok1 = (UrusanDokumen) q.uniqueResult();

                if (urusDok1 != null) {
                    urusDok1.setWajib(urusanDokumen.getWajib());
                    urusDok1.setPerluDisah(urusanDokumen.getPerluDisah());
                    urusDok1.setPerluDiimbas(urusanDokumen.getPerluDiimbas());
                    urusDok1.setCatatan(urusanDokumen.getCatatan());
                    urusDok1.setCaj(urusanDokumen.getCaj());
                    urusDok1.setKodTransaksi(urusanDokumen.getKodTransaksi());
                    urusDok1.setKategoriPemohon(urusanDokumen.getKategoriPemohon());
                    urusDok1.setInfoAudit(ia2);
                    urusDok1.setAktif(aktif.charAt(0));
                    
                    updateUrusanDokumen(urusDok1);
                    addSimpleMessage("Kod Dokumen berjaya dikemaskini.");
                }
            } else {
                Session s = sessionProvider.get();
                Query q = s.createQuery("from " + table + " where kod =:kod");
                q.setString("kod", kod);
                o = q.uniqueResult();
                if (o != null) {
                    String query = "UPDATE " + table + " SET nama = '" + nama + "' , aktif = '" + aktif
                            + "' , DIKKINI = '" + pengguna.getIdPengguna() + "' "
                            + " , TRH_KKINI = SYSDATE "
                            + "WHERE kod = '" + kod + "' ";
                    Query q1 = sessionProvider.get().createQuery(query);
                    q1.executeUpdate();
                    addSimpleMessage("Kod berjaya dikemaskini.");
                } else {
                    addSimpleError("kod tidak berjaya dikemaskini");
                }
            }
        }
        return new JSP("utiliti/edit_kod.jsp").addParameter("popup", "true");
    }

    public Resolution viewCop() throws SQLException, NamingException {

        String idParam = getContext().getRequest().getParameter("idParam");

        Session s = sessionProvider.get();
        Query q = s.createQuery("SELECT p FROM etanah.model.KodCawangan p WHERE kod =:kod");
        q.setString("kod", idParam);
        kodCawangan = (KodCawangan) q.uniqueResult();

        blob = kodCawangan.getCop();
        InputStream is = blob.getBinaryStream();

        return new StreamingResolution("application/octet-stream", is);
    }

    public Resolution senaraiKodBPMByDaerah() {
        logger.debug(":::start search for kodbpm by daerah:::");
        String kodDaerah = (String) getContext().getRequest().getParameter("kodDaerah");
        if (StringUtils.isEmpty(kodDaerah)) {
            kodDaerah = daerah;
        }
        logger.debug("kodDaerah :" + kodDaerah);
        if (kodDaerah != null) {
            listBandarPekanMukim = regService.getSenaraiKodBPMByDaerah(kodDaerah);
        }
        return new ForwardResolution("/WEB-INF/jsp/utiliti/tambah_kod.jsp").addParameter("popup", "true");
    }

    @Transactional
    public void saveGunaPetak(KodKegunaanPetak petak) {
        kodKegunaanPetakDAO.save(petak);
    }

    @Transactional
    public void saveGunaPetakAksr(KodKegunaanPetakAksesori petakAksr) {
        kodKegunaanPetakAksesoriDAO.save(petakAksr);
    }

    @Transactional
    public void saveBpm(KodBandarPekanMukim bpm) {
        kodBandarPekanMukimDAO.save(bpm);
    }

    @Transactional
    public void saveSeksyen(KodSeksyen sek) {
        kodSeksyenDAO.save(sek);
    }

    @Transactional
    public void saveDaerah(KodDaerah daerah) {
        kodDaerahDAO.save(daerah);
    }

    @Transactional
    public void saveKodKegunaanTanah(KodKegunaanTanah kodKegunaanTanah) {
        kodKegunaanTanahDAO.save(kodKegunaanTanah);
    }

    @Transactional
    public void savekodNegeri(KodNegeri kodNegeri) {
        kodNegeriDAO.save(kodNegeri);
    }

    @Transactional
    public void savekodBank(KodBank kodBank) {
        kodBankDAO.save(kodBank);
    }

    @Transactional
    public void saveKodSyktTubuh(KodPenubuhanSyarikat kodPenubuhanSyarikat) {
        kodPenubuhanSyarikatDAO.save(kodPenubuhanSyarikat);
    }

    @Transactional
    public void saveKodKategoriTanah(KodKategoriTanah kodKategoriTanah) {
        kodKategoriTanahDAO.save(kodKategoriTanah);
    }

    @Transactional
    public void saveKodKegunaanBangunan(KodKegunaanBangunan gunaBngn) {
        kodKegunaanBangunanDAO.save(gunaBngn);
    }

    @Transactional
    public void saveKodWarganegara(KodWarganegara warga) {
        kodWarganegaraDAO.save(warga);
    }

    @Transactional
    public void saveKodCawangan(KodCawangan caw) {
        kodCawanganDAO.save(caw);
    }

    @Transactional
    public void saveKodUrusan(KodUrusan kodUrusan) {
        kodUrusanDAO.save(kodUrusan);
    }

    @Transactional
    public void updateKodUrusan(KodUrusan kodUrusan) {
        kodUrusanDAO.update(kodUrusan);
    }

    @Transactional
    public void saveKodKadarBayaran(KodKadarBayaran kodKadarBayaran) {
        kodKadarBayaranDAO.save(kodKadarBayaran);
    }

    @Transactional
    public void saveKodBank(KodBank kodBank) {
        kodBankDAO.save(kodBank);
    }

    @Transactional
    public void updateKodKadarBayaran(KodKadarBayaran kodKadarBayaran) {
        kodKadarBayaranDAO.update(kodKadarBayaran);
    }

    @Transactional
    public void updateKodTransaksi(KodTransaksi kodTransaksi) {
        kodTransaksiDAO.saveOrUpdate(kodTransaksi);
    }
    
    @Transactional
    public void updateKodCawangan(KodCawangan kodCawangan) {
        kodCawanganDAO.update(kodCawangan);
    }

    public Object getO() {
        return o;
    }

    public void setO(Object o) {
        this.o = o;
    }

    public String getKod() {
        return kod;
    }

    public void setKod(String kod) {
        this.kod = kod;
    }

    public String getTable() {
        return table;
    }

    public void setTable(String table) {
        this.table = table;
    }

    public List getResults() {
        return results;
    }

    public void setResults(List results) {
        this.results = results;
    }

    public Pengguna getPengguna() {
        return pengguna;
    }

    public void setPengguna(Pengguna pengguna) {
        this.pengguna = pengguna;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getKod1() {
        return kod1;
    }

    public void setKod1(String kod1) {
        this.kod1 = kod1;
    }

    public String getAktif() {
        return aktif;
    }

    public void setAktif(String aktif) {
        this.aktif = aktif;
    }

    public String getDaerah() {
        return daerah;
    }

    public void setDaerah(String daerah) {
        this.daerah = daerah;
    }

    public String getCawangan() {
        return cawangan;
    }

    public void setCawangan(String cawangan) {
        this.cawangan = cawangan;
    }

    public String getKodBPM() {
        return kodBPM;
    }

    public void setKodBPM(String kodBPM) {
        this.kodBPM = kodBPM;
    }

    public String getNamaKodBPM() {
        return namaKodBPM;
    }

    public void setNamaKodBPM(String namaKodBPM) {
        this.namaKodBPM = namaKodBPM;
    }

    public String getBpm() {
        return bpm;
    }

    public void setBpm(String bpm) {
        this.bpm = bpm;
    }

    public String getKodSeksyen() {
        return kodSeksyen;
    }

    public void setKodSeksyen(String kodSeksyen) {
        this.kodSeksyen = kodSeksyen;
    }

    public String getNamaKodSeksyen() {
        return namaKodSeksyen;
    }

    public void setNamaKodSeksyen(String namaKodSeksyen) {
        this.namaKodSeksyen = namaKodSeksyen;
    }

    public List<KodBandarPekanMukim> getListBandarPekanMukim() {
        return listBandarPekanMukim;
    }

    public void setListBandarPekanMukim(List<KodBandarPekanMukim> listBandarPekanMukim) {
        this.listBandarPekanMukim = listBandarPekanMukim;
    }

    public String getEtapp() {
        return etapp;
    }

    public void setEtapp(String etapp) {
        this.etapp = etapp;
    }

    public String getCukai() {
        return cukai;
    }

    public void setCukai(String cukai) {
        this.cukai = cukai;
    }

    public String getKatg() {
        return katg;
    }

    public void setKatg(String katg) {
        this.katg = katg;
    }

    public String getJabatan() {
        return jabatan;
    }

    public void setJabatan(String jabatan) {
        this.jabatan = jabatan;
    }

    public String getKodSerah() {
        return kodSerah;
    }

    public void setKodSerah(String kodSerah) {
        this.kodSerah = kodSerah;
    }

    public String getRujKanun() {
        return rujKanun;
    }

    public void setRujKanun(String rujKanun) {
        this.rujKanun = rujKanun;
    }

    public String getUrusanKaunter() {
        return urusanKaunter;
    }

    public void setUrusanKaunter(String urusanKaunter) {
        this.urusanKaunter = urusanKaunter;
    }

    public String getUrusanDalam() {
        return urusanDalam;
    }

    public void setUrusanDalam(String urusanDalam) {
        this.urusanDalam = urusanDalam;
    }

    public String getUrusanBlkgKaunter() {
        return urusanBlkgKaunter;
    }

    public void setUrusanBlkgKaunter(String urusanBlkgKaunter) {
        this.urusanBlkgKaunter = urusanBlkgKaunter;
    }

    public String getUrusanBayar() {
        return urusanBayar;
    }

    public void setUrusanBayar(String urusanBayar) {
        this.urusanBayar = urusanBayar;
    }

    public String getKodTrans() {
        return kodTrans;
    }

    public void setKodTrans(String kodTrans) {
        this.kodTrans = kodTrans;
    }

    public String getKodKatgBayar() {
        return kodKatgBayar;
    }

    public void setKodKatgBayar(String kodKatgBayar) {
        this.kodKatgBayar = kodKatgBayar;
    }

    public String getCaj() {
        return caj;
    }

    public void setCaj(String caj) {
        this.caj = caj;
    }

    public String getIdWorkflow() {
        return idWorkflow;
    }

    public void setIdWorkflow(String idWorkflow) {
        this.idWorkflow = idWorkflow;
    }

    public String getPtg() {
        return ptg;
    }

    public void setPtg(String ptg) {
        this.ptg = ptg;
    }

    public String getUtama() {
        return utama;
    }

    public void setUtama(String utama) {
        this.utama = utama;
    }

    public String getSasarBulan() {
        return sasarBulan;
    }

    public void setSasarBulan(String sasarBulan) {
        this.sasarBulan = sasarBulan;
    }

    public String getMinHakmilik() {
        return minHakmilik;
    }

    public void setMinHakmilik(String minHakmilik) {
        this.minHakmilik = minHakmilik;
    }

    public String getMaxHakmilik() {
        return maxHakmilik;
    }

    public void setMaxHakmilik(String maxHakmilik) {
        this.maxHakmilik = maxHakmilik;
    }

    public String getIdWorkflowIntegration() {
        return idWorkflowIntegration;
    }

    public void setIdWorkflowIntegration(String idWorkflowIntegration) {
        this.idWorkflowIntegration = idWorkflowIntegration;
    }

    public String getKodUrusan() {
        return kodUrusan;
    }

    public void setKodUrusan(String kodUrusan) {
        this.kodUrusan = kodUrusan;
    }

    public String getMilikDaerah() {
        return milikDaerah;
    }

    public void setMilikDaerah(String milikDaerah) {
        this.milikDaerah = milikDaerah;
    }

    public String getMin() {
        return min;
    }

    public void setMin(String min) {
        this.min = min;
    }

    public String getMax() {
        return max;
    }

    public void setMax(String max) {
        this.max = max;
    }

    public String getKadar() {
        return kadar;
    }

    public void setKadar(String kadar) {
        this.kadar = kadar;
    }

    public KodUrusan getUrusan() {
        return urusan;
    }

    public void setUrusan(KodUrusan urusan) {
        this.urusan = urusan;
    }

    public String getBilHakmilik() {
        return bilHakmilik;
    }

    public void setBilHakmilik(String bilHakmilik) {
        this.bilHakmilik = bilHakmilik;
    }

    public KodKadarBayaran getKadarBayar() {
        return kadarBayar;
    }

    public void setKadarBayar(KodKadarBayaran kadarBayar) {
        this.kadarBayar = kadarBayar;
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

    public String getTel1() {
        return tel1;
    }

    public void setTel1(String tel1) {
        this.tel1 = tel1;
    }

    public String getTel2() {
        return tel2;
    }

    public void setTel2(String tel2) {
        this.tel2 = tel2;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getKodPTJ() {
        return kodPTJ;
    }

    public void setKodPTJ(String kodPTJ) {
        this.kodPTJ = kodPTJ;
    }

    public String getKodJabatanSpek() {
        return kodJabatanSpek;
    }

    public void setKodJabatanSpek(String kodJabatanSpek) {
        this.kodJabatanSpek = kodJabatanSpek;
    }

    public KodCawangan getKodCawangan() {
        return kodCawangan;
    }

    public void setKodCawangan(KodCawangan kodCawangan) {
        this.kodCawangan = kodCawangan;
    }

    public FileBean getFileToBeUpload() {
        return fileToBeUpload;
    }

    public void setFileToBeUpload(FileBean fileToBeUpload) {
        this.fileToBeUpload = fileToBeUpload;
    }

    public Blob getBlob() {
        return blob;
    }

    public void setBlob(Blob blob) {
        this.blob = blob;
    }

    public String getParameter() {
        return parameter;
    }

    public void setParameter(String parameter) {
        this.parameter = parameter;
    }

    public String getKodBankBaru() {
        return kodBankBaru;
    }

    public void setKodBankBaru(String kodBankBaru) {
        this.kodBankBaru = kodBankBaru;
    }

    public String getNamaTrans() {
        return namaTrans;
    }

    public void setNamaTrans(String namaTrans) {
        this.namaTrans = namaTrans;
    }

    public String getKategoriTransaksi() {
        return kategoriTransaksi;
    }

    public void setKategoriTransaksi(String kategoriTransaksi) {
        this.kategoriTransaksi = kategoriTransaksi;
    }

    public String getAkaunDebit() {
        return akaunDebit;
    }

    public void setAkaunDebit(String akaunDebit) {
        this.akaunDebit = akaunDebit;
    }

    public String getAkaunKredit() {
        return akaunKredit;
    }

    public void setAkaunKredit(String akaunKredit) {
        this.akaunKredit = akaunKredit;
    }

    public String getKeutamaan() {
        return keutamaan;
    }

    public void setKeutamaan(String keutamaan) {
        this.keutamaan = keutamaan;
    }

    public String getpersekutuan() {
        return persekutuan;
    }

    public void setpersekutuan(String persekutuan) {
        this.persekutuan = persekutuan;
    }

    public String getTransaksiAmanah() {
        return transaksiAmanah;
    }

    public void setTransaksiAmanah(String transaksiAmanah) {
        this.transaksiAmanah = transaksiAmanah;
    }

    public String getPerihal() {
        return perihal;
    }

    public void setPerihal(String perihal) {
        this.perihal = perihal;
    }

    public String getKodTransBaru() {
        return kodTransBaru;
    }

    public void setKodTransBaru(String kodTransBaru) {
        this.kodTransBaru = kodTransBaru;
    }

    public String getKodTanah() {
        return kodTanah;
    }

    public void setKodTanah(String kodTanah) {
        this.kodTanah = kodTanah;
    }

    public String getKodKegunaanTanah() {
        return kodKegunaanTanah;
    }

    public void setKodKegunaanTanah(String kodKegunaanTanah) {
        this.kodKegunaanTanah = kodKegunaanTanah;
    }

    public String getPeratusKadar() {
        return peratusKadar;
    }

    public void setPeratusKadar(String peratusKadar) {
        this.peratusKadar = peratusKadar;
    }
    
    @Transactional
    public void updateKodKadarPremium(KodKadarPremium kodKadarPremium) {
        kodKadarPremiumDAO.update(kodKadarPremium);
    }
    
    @Transactional
    public void saveKodKadarPremium(KodKadarPremium kodKadarPremium) {
        kodKadarPremiumDAO.save(kodKadarPremium);
    }
    
    @Transactional
    public void updateKodDokumen(KodDokumen kodDokumen) {
        kodDokumenDAO.update(kodDokumen);
    }
    
    @Transactional
    public void saveKodDokumen(KodDokumen kodDokumen) {
        kodDokumenDAO.save(kodDokumen);
    }
    
    @Transactional
    public void updateUrusanDokumen(UrusanDokumen urusanDokumen) {
        urusanDokumenDAO.update(urusanDokumen);
    }
    
    @Transactional
    public void saveUrusanDokumen(UrusanDokumen urusanDokumen) {
        urusanDokumenDAO.save(urusanDokumen);
    }
    
    @Transactional
    public void saveKodTanah(KodTanah kodTanah) {
        kodTanahDAO.save(kodTanah);
    }

    public KodKadarPremium getKadarPremium() {
        return kadarPremium;
    }

    public void setKadarPremium(KodKadarPremium kadarPremium) {
        this.kadarPremium = kadarPremium;
    }

    public String getIdKodKadarPremium() {
        return idKodKadarPremium;
    }

    public void setIdKodKadarPremium(String idKodKadarPremium) {
        this.idKodKadarPremium = idKodKadarPremium;
    }

    public UrusanDokumen getUrusanDokumen() {
        return urusanDokumen;
    }

    public void setUrusanDokumen(UrusanDokumen urusanDokumen) {
        this.urusanDokumen = urusanDokumen;
    }
    
    public String getIdUrusanDokumen() {
        return idUrusanDokumen;
    }

    public void setIdUrusanDokumen(String idUrusanDokumen) {
        this.idUrusanDokumen = idUrusanDokumen;
    }

    public KodDokumen getKodDokumen() {
        return kodDokumen;
    }

    public void setKodDokumen(KodDokumen kodDokumen) {
        this.kodDokumen = kodDokumen;
    }

    public String getKawalCapaian() {
        return kawalCapaian;
    }

    public void setKawalCapaian(String kawalCapaian) {
        this.kawalCapaian = kawalCapaian;
    }

    public String getPenjana() {
        return penjana;
    }

    public void setPenjana(String penjana) {
        this.penjana = penjana;
    }   

    public String getWajib() {
        return wajib;
    }

    public void setWajib(String wajib) {
        this.wajib = wajib;
    }

    public String getPerluDisah() {
        return perluDisah;
    }

    public void setPerluDisah(String perluDisah) {
        this.perluDisah = perluDisah;
    }

    public String getPerluDiimbas() {
        return perluDiimbas;
    }

    public void setPerluDiimbas(String perluDiimbas) {
        this.perluDiimbas = perluDiimbas;
    }

    public String getCatatan() {
        return catatan;
    }

    public void setCatatan(String catatan) {
        this.catatan = catatan;
    }

    public String getKategoriPemohon() {
        return kategoriPemohon;
    }

    public void setKategoriPemohon(String kategoriPemohon) {
        this.kategoriPemohon = kategoriPemohon;
    }

    public String getKodDokumen1() {
        return kodDokumen1;
    }

    public void setKodDokumen1(String kodDokumen1) {
        this.kodDokumen1 = kodDokumen1;
    }

  
    
    
   
}
