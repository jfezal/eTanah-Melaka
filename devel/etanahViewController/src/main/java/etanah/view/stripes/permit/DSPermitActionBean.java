/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.stripes.permit;

/**
 *
 * @author Hazwan
 */
import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import com.google.inject.Provider;
import etanah.Configuration;
import etanah.dao.DokumenKewanganDAO;
import etanah.dao.KodBandarPekanMukimDAO;
import etanah.dao.KodCawanganDAO;
import etanah.dao.KodDokumenDAO;
import etanah.dao.KodItemPermitDAO;
import etanah.dao.KodJenisPermitDAO;
import etanah.dao.KodLotDAO;
import etanah.dao.KodNegeriDAO;
import etanah.dao.KodPemilikanDAO;
import etanah.dao.KodTransaksiDAO;
import etanah.dao.KodTransaksiTuntutDAO;
import etanah.dao.KodTuntutDAO;
import etanah.dao.KodUOMDAO;
import etanah.dao.KodUrusanDAO;
import etanah.dao.PenggunaDAO;
import etanah.dao.PermitDAO;
import etanah.dao.PermohonanDAO;
import etanah.dao.PermohonanTuntutanKosDAO;
import etanah.dao.PermohonanPlotPelanDAO;
import etanah.dao.PihakDAO;
import etanah.model.HakmilikPermohonan;
import etanah.model.Permohonan;
import etanah.model.Permit;
import etanah.model.PermohonanTuntutanKos;
import etanah.model.Pengguna;
import etanah.model.InfoAudit;
import etanah.model.KodNegeri;
import etanah.model.PermitItem;
import etanah.model.PermitItemKuantiti;
import etanah.model.PermitLPSRekod;
import etanah.model.PermitSahLaku;
import etanah.model.PermitStrukturDiluluskan;
import etanah.model.PermohonanTandatanganDokumen;
import etanah.model.PermohonanTuntutanBayar;
import etanah.model.Pihak;
import etanah.model.Transaksi;
import etanah.model.gis.PelanGIS;
import etanah.model.gis.PelanGISPK;
import etanah.view.etanahActionBeanContext;
import etanah.service.PembangunanService;
import etanah.service.common.PermohonanService;
import etanah.service.common.HakmilikPermohonanService;
import etanah.util.FileUtil;
import etanah.util.StringUtils;
import java.io.File;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;

import java.util.List;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.FileBean;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import org.apache.log4j.Logger;
import org.hibernate.Session;

@UrlBinding("/digiSignPermit")
public class DSPermitActionBean extends AbleActionBean {

    private static final Logger LOG = Logger.getLogger(DSPermitActionBean.class);
    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    KodTuntutDAO kodTuntutDAO;
    @Inject
    KodUrusanDAO kodUrusanDAO;
    @Inject
    PermohonanTuntutanKosDAO permohonanTuntutanKosDAO;
    @Inject
    KodTransaksiTuntutDAO kodTransaksiTuntutDAO;
    @Inject
    PembangunanService pembangunanServ;
    @Inject
    PermohonanService permohonanService;
    @Inject
    HakmilikPermohonanService hakmilikPermohonanService;
    @Inject
    etanah.Configuration conf;
    @Inject
    PermohonanPlotPelanDAO permohonanPlotPelanDAO;
    @Inject
    KodPemilikanDAO kodPemilikanDAO;
    @Inject
    KodCawanganDAO kodCawanganDAO;
    @Inject
    KodJenisPermitDAO kodJenisPermitDAO;
    @Inject
    KodTransaksiDAO kodTransaksiDAO;
    @Inject
    DokumenKewanganDAO dokumenKewanganDAO;
    @Inject
    KodBandarPekanMukimDAO kodBandarPekanMukimDAO;
    @Inject
    KodUOMDAO kodUOMDAO;
    @Inject
    KodLotDAO kodLotDAO;
    @Inject
    KodNegeriDAO kodNegeriDAO;
    @Inject
    PihakDAO pihakDAO;
    @Inject
    KodDokumenDAO kodDokumenDAO;
    @Inject
    KodItemPermitDAO kodItemPermitDAO;
    @Inject
    PermitDAO permitDAO;
    @Inject
    PenggunaDAO penggunaDAO;
    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;
    private Permohonan permohonan;
    private SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    private String radio1;
    private String kodPermit;
    private String noPermit;
    private String daerah;
    private String bayaran;
    private String noResit;
    private String noResitCarian;
    private String idMohon;
    private String idPermohonan;
    private String kodMilik;
    private InfoAudit infoAudit;
    private Date tarikh;
    private Transaksi kt;
    private String noR;
    private String ama;
    private String noFail;
    private String jenisBorang;
    private String bandar;
    private String kodLot;
    private String noLot;
    private String luas;
    private String radio2;
    private String tanah;
    private Pihak cariPihak;
    private String noPengenalan;
    private String nama;
    private String alamat1;
    private String alamat2;
    private String alamat3;
    private String alamat4;
    private String poskod;
    private String negeri;
    private String syaratTanah;
    private String jenisLuas;
    private Date tarikhMula;
    private Date tarikhtamat;
    private Date tarikhPermit;
    private String jenisStruktur;
    private String lokasi;
    private String tambah;
    private String luasStruktur;
    private String jenisLuasStruktur;
    private String reportName;
    private String maksud;
    private List<PelanGIS> listPelanGIS;
    private List<PelanGIS> listPelanGISwNoPermit;
    private String idMohonPgis;
    private String idMohonPgiswPermit;
    FileBean fileToBeUpload;
    private String kPermit;
    private List<Pengguna> listPp;
    private String tandatangan;
    private String royalti;
    private String itemPermit;
    private String kntt;
    private String jenisUom;
    private String kntt1;
    private String jenisUom1;
    private String namaKuantiti;
    private String royaltiUOM;
    private String b1;
    private String b2;
    private String b3;
    private String n1;
    private String n2;
    private String n3;
    private String t1;
    private String t2;
    private String t3;
    private String kaedah;
    private String rm;
    private String bagi;
    private String tambah1;
    private String tempat;
    private String daerahNama;
    private String negeriNama;
    private String bandarNama;
    private String tanahNama;
    private String kodLotNama;
    private String jenisUomNama;
    private String jenisUom1Nama;
    private String bagiNama;
    private String itemPermitNama;
    private String kPermitNama;
    private Pengguna pengguna;
    private List<Permit> listPermit;
    private boolean flag = false;

    @DefaultHandler
    public Resolution showForm() {
        LOG.info("signForm");
        return new ForwardResolution("/WEB-INF/jsp/permit/signature/senaraiPermitByDaerah.jsp");
    }

    public Resolution edit() {
        LOG.info("editingForm");
        return new ForwardResolution("/WEB-INF/jsp/permit/edit/senaraiPermitByDaerahEdit.jsp");
    }

    public Resolution kembali() {
        LOG.info("Return Main Page");
        return new ForwardResolution("/WEB-INF/jsp/permit/signature/senaraiPermitByDaerah.jsp");
    }

    public Resolution kembaliEdit() {
        LOG.info("Return Main Page");
        return new ForwardResolution("/WEB-INF/jsp/permit/edit/senaraiPermitByDaerahEdit.jsp");
    }

    public Resolution permitBaru() {
        LOG.info("New Permit Entry");
        return new ForwardResolution("/WEB-INF/jsp/permit/edit/newPermit.jsp");
    }

    public Resolution cari() throws Exception {
        pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        noFail = getContext().getRequest().getParameter("noFailRuj");
        noPermit = getContext().getRequest().getParameter("noPermit");
        jenisBorang = getContext().getRequest().getParameter("borang");
        LOG.info("###### id Pengguna : " + pengguna + " IdPermohonan = " + noFail + " JB = " + jenisBorang);

        if (StringUtils.isBlank(noFail) || noFail.equals("0")) {
            noFail = null;
        }
        if (StringUtils.isBlank(noPermit) || noPermit.equals("0")) {
            noPermit = null;
        }
        if (StringUtils.isBlank(jenisBorang) || jenisBorang.equals("0")) {
            jenisBorang = null;
        }

        //Original
        //listPermit = pembangunanServ.findPermit(noFail, noPermit, jenisBorang, pengguna.getKodCawangan().getKod());
        
        //Sementara Untuk Catter Dimasuk Permit
        listPermit = pembangunanServ.findPermitDimasukPermit(noFail, noPermit, jenisBorang, pengguna.getKodCawangan().getKod());
        if (listPermit.size() > 0) {
            setFlag(true);
            addSimpleMessage(listPermit.size() + " Rekod Permit Ditemui.");

        } else {
            addSimpleMessage("Tiada Rekod Ditemui.");
        }

        return new JSP("/permit/signature/senaraiPermitByDaerah.jsp");
    }

    public Resolution cariEdit() throws Exception {
        pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        noFail = getContext().getRequest().getParameter("noFailRuj");
        noPermit = getContext().getRequest().getParameter("noPermit");
        jenisBorang = getContext().getRequest().getParameter("borang");
        LOG.info("###### id Pengguna : " + pengguna + " IdPermohonan = " + noFail + " JB = " + jenisBorang);

        if (StringUtils.isBlank(noFail) || noFail.equals("0")) {
            noFail = null;
        }
        if (StringUtils.isBlank(noPermit) || noPermit.equals("0")) {
            noPermit = null;
        }
        if (StringUtils.isBlank(jenisBorang) || jenisBorang.equals("0")) {
            jenisBorang = null;
        }

        listPermit = pembangunanServ.findPermit(noFail, noPermit, jenisBorang, pengguna.getKodCawangan().getKod());
        if (listPermit.size() > 0) {
            setFlag(true);
            addSimpleMessage(listPermit.size() + " Rekod Permit Ditemui.");

        } else {
            addSimpleMessage("Tiada Rekod Ditemui.");
        }

        return new JSP("/permit/edit/senaraiPermitByDaerahEdit.jsp");
    }

    //START REHYDRATE
    public void check() {

        pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        String id = noFail;
        String idPermit = noPermit;

        Permohonan per = pembangunanServ.findPermohonanByIdPermohonan(id);
        if (per != null) {
            if (per.getKodUrusan() != null) {
                kPermit = per.getKodUrusan().getKod();
                kPermitNama = per.getKodUrusan().getNama();
            }

            HakmilikPermohonan hakmilikPer = pembangunanServ.findByIdPermohonanAndPermitForCarianPermit(id, idPermit);
            if (hakmilikPer != null) {
                if (hakmilikPer.getKodMilik() != null) {
                    radio1 = hakmilikPer.getKodMilik().getKod().toString();
                }
                if (hakmilikPer.getCawangan() != null) {
                    daerah = hakmilikPer.getCawangan().getKod();
                    daerahNama = hakmilikPer.getCawangan().getName();
                }
                if (hakmilikPer.getBandarPekanMukimBaru() != null) {
                    bandar = Integer.toString(hakmilikPer.getBandarPekanMukimBaru().getKod());
                    bandarNama = hakmilikPer.getBandarPekanMukimBaru().getNama();

                }
                if (hakmilikPer.getKodMilik() != null) {
                    tanah = hakmilikPer.getKodMilik().getKod().toString();
                    tanahNama = hakmilikPer.getKodMilik().getNama();
                    syaratTanah = hakmilikPer.getKodMilik().getNama();
                }
                if (hakmilikPer.getLot() != null) {
                    kodLot = hakmilikPer.getLot().getKod();
                    kodLotNama = hakmilikPer.getLot().getNama();
                }
                if (hakmilikPer.getNoLot() != null) {
                    noLot = hakmilikPer.getNoLot();
                }
                if (hakmilikPer.getLuasTerlibat() != null) {
                    luas = hakmilikPer.getLuasTerlibat().toString();
                }
                if (hakmilikPer.getKodUnitLuas() != null) {
                    radio2 = hakmilikPer.getKodUnitLuas().getKod();
                    jenisLuas = hakmilikPer.getKodUnitLuas().getNama();
                }
                if (hakmilikPer.getLokasi() != null) {
                    lokasi = hakmilikPer.getLokasi();
                }
                if (hakmilikPer.getCatatan() != null) {
                    tempat = hakmilikPer.getCatatan();
                }
            }

            Permit perm = pembangunanServ.findPerrmitByIdMohonAndNoPermit(id, idPermit);
            if (perm != null) {
                if (perm.getKodJenisPermit() != null) {
                    kodPermit = perm.getKodJenisPermit().getKod();
                }
                if (perm.getNoPermit() != null) {
                    noPermit = perm.getNoPermit();
                }

                if (perm.getPihak() != null) {
                    nama = perm.getPihak().getNama();
                    noPengenalan = perm.getPihak().getNoPengenalan();
                    alamat1 = perm.getPihak().getAlamat1();
                    alamat2 = perm.getPihak().getAlamat2();
                    alamat3 = perm.getPihak().getAlamat3();
                    alamat4 = perm.getPihak().getAlamat4();
                    poskod = perm.getPihak().getPoskod();

                    if (perm.getPihak().getNegeri() != null) {
                        negeri = perm.getPihak().getNegeri().getKod();
                        negeriNama = perm.getPihak().getNegeri().getNama();
                    }
                }

                if (per.getCatatan().equals("A") && perm.getPeruntukanTambahan() == null) {
                    System.out.println("--Catatan--" + per.getCatatan());
                    tambah = "Lesen ini hendaklah diperbaharui pada awal tahun berikutnya ";
                } else if (per.getCatatan().equals("C") && perm.getPeruntukanTambahan() == null) {
                    tambah = "Mematuhi arahan Pengarah PTG Melaka Bil 4/2008. Larangan mengeksport bahan batuan keluar negeri.";
                } else {
                    tambah = perm.getPeruntukanTambahan();
                }

                tambah1 = perm.getPeruntukanTambahanLain();

                if (perm.getKeterangan() != null) {
                    System.out.println("--Keterangan--" + perm.getKeterangan());
                    maksud = perm.getKeterangan();
                } else {
                    System.out.println("--Catatan--" + per.getCatatan());
                    if (per.getCatatan().equals("A")) {
                        maksud = "Tanaman kediaman pesendirian pertunjukan awam, dll ";
                    } else if (per.getCatatan().equals("B")) {
                        maksud = "Pengekstrakan, pemprosesan dan pemindahan bahan batuan iaitu ";
                    }
                }
                kaedah = perm.getKaedah();

                if (perm.getTarikhPermit() != null) {
                    tarikhPermit = perm.getTarikhPermit();
                }

                PermitSahLaku psl = pembangunanServ.findPermitSahLakuByIdKosPermit(perm.getIdPermit());
                if (psl != null) {
                    if (psl.getTarikhPermitMula() != null) {
                        tarikhMula = psl.getTarikhPermitMula();
                    }
                    if (psl.getTarikhPermitTamat() != null) {
                        tarikhtamat = psl.getTarikhPermitTamat();
                    }
                }

                PermitStrukturDiluluskan psdl = pembangunanServ.findPermitStrukturLulusByIdPermit(perm.getIdPermit());
                if (psdl != null) {
                    if (psdl.getJenisStruktur() != null) {
                        jenisStruktur = psdl.getJenisStruktur();
                    }
                }

                PermitItem pi = pembangunanServ.findPermitItemByIdPermit(perm.getIdPermit());
                if (pi != null) {
                    if (pi.getKodItemPermit() != null) {
                        itemPermit = pi.getKodItemPermit().getKod();
                        itemPermitNama = pi.getKodItemPermit().getNama();
                    }

                    PermitItemKuantiti pik = pembangunanServ.findPermitItemKNTTByIdPermitItem(pi.getIdPermitItem());
                    if (pik != null) {
                        if (pik.getKuantitiMax() != null) {
                            kntt = pik.getKuantitiMax().toString();
                        }

                        if (pik.getKuantitiMaxUom() != null) {
                            jenisUom = pik.getKuantitiMaxUom().getKod();
                            jenisUomNama = pik.getKuantitiMaxUom().getNama();
                            namaKuantiti = pik.getKuantitiMaxUom().getNama();
                        }

                        if (pik.getKuantitiMaxTempoh() != null) {
                            kntt1 = pik.getKuantitiMaxTempoh().toString();
                        }

                        if (pik.getKuantitiMaxTempohUom() != null) {
                            jenisUom1 = pik.getKuantitiMaxTempohUom().getKod();
                            jenisUom1Nama = pik.getKuantitiMaxTempohUom().getNama();
                        }

                        if (pik.getNilai() != null) {
                            rm = pik.getNilai().toString();
                        }

                        if (pik.getNilaiUom() != null) {
                            bagi = pik.getNilaiUom().getKod();
                            bagiNama = pik.getNilaiUom().getNama();
                        }

                    }
                }

                PermitLPSRekod plpsRekod1stTimes = pembangunanServ.findLPSRekodFor1stTimes(id, idPermit);
                if (plpsRekod1stTimes != null) {
                    b1 = plpsRekod1stTimes.getBayaran();
                    n1 = plpsRekod1stTimes.getResit();
                    t1 = plpsRekod1stTimes.getTahun();
                }
                PermitLPSRekod plpsRekod2ndTimes = pembangunanServ.findLPSRekodFor2ndTimes(id, idPermit);
                if (plpsRekod2ndTimes != null) {
                    b2 = plpsRekod2ndTimes.getBayaran();
                    n2 = plpsRekod2ndTimes.getResit();
                    t2 = plpsRekod2ndTimes.getTahun();
                }
                PermitLPSRekod plpsRekod3rdTimes = pembangunanServ.findLPSRekodFor3rdTimes(id, idPermit);
                if (plpsRekod3rdTimes != null) {
                    b3 = plpsRekod3rdTimes.getBayaran();
                    n3 = plpsRekod3rdTimes.getResit();
                    t3 = plpsRekod3rdTimes.getTahun();
                }
            }

            PermohonanTuntutanKos ptk = pembangunanServ.findMohonTuntutKosAndNoLesen(id, idPermit);
            if (ptk != null) {
                if (ptk.getAmaunTuntutan() != null) {
                    bayaran = ptk.getAmaunTuntutan().toString();
                    ama = ptk.getAmaunTuntutan().toString();
                }

                if (!ptk.getInfoAudit().getDimasukOleh().equals(penggunaDAO.findById("PERMIT"))) {
                    PermohonanTuntutanBayar ptb = pembangunanServ.findTuntuBayarByPremium(ptk.getIdKos());
                    if (ptb != null) {
                        if (ptb.getDokumenKewangan() != null) {
                            noResit = ptb.getDokumenKewangan().getIdDokumenKewangan();
                        } else {
                            noResit = ptk.getCatatan();
                        }
                    }
                } else {
                    //To Catter Permit Data Collection
                    noResit = ptk.getCatatan();
                }
            }

            listPelanGIS = new ArrayList<PelanGIS>();
            listPelanGIS = pembangunanServ.findListPelanGISPKByIdPermohonan(per.getIdPermohonan());

            listPelanGISwNoPermit = new ArrayList<PelanGIS>();
            listPelanGISwNoPermit = pembangunanServ.findListPelanGISPKByIdPermohonanAndnoPermit(per.getIdPermohonan(), idPermit);

            for (PelanGIS pGis : listPelanGIS) {
                idMohonPgis = pGis.getPelanGISPK().getPermohonan().getIdPermohonan();
            }

            for (PelanGIS pGiswPermit : listPelanGISwNoPermit) {
                idMohonPgiswPermit = pGiswPermit.getPelanGISPK().getPermohonan().getIdPermohonan();
            }
        }
    }
//STOP REHYDRATE
//START PERMIT ENTRY

    public Resolution simpanKemasukan() throws Exception {
        pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        noFail = getContext().getRequest().getParameter("noFailRuj");
        jenisBorang = getContext().getRequest().getParameter("radio");
        kodPermit = getContext().getRequest().getParameter("radio");
        noPermit = getContext().getRequest().getParameter("noPermit");
        LOG.info("###### id Pengguna : " + pengguna + " IdPermohonan = " + noFail);

        if (jenisBorang != null) {

            infoAudit = new InfoAudit();
            infoAudit.setDimasukOleh(pengguna);
            infoAudit.setTarikhMasuk(new java.util.Date());

            Permohonan pExist = pembangunanServ.findPermohonanByIdPermohonan(noFail);
            if (pExist != null) {
                if (jenisBorang.equalsIgnoreCase("A")) {
                    pExist.setKodUrusan(kodUrusanDAO.findById("PLPS"));
                } else if (jenisBorang.equalsIgnoreCase("B")) {
                    pExist.setKodUrusan(kodUrusanDAO.findById("LPSP"));
                } else if (jenisBorang.equalsIgnoreCase("D")) {
                    pExist.setKodUrusan(kodUrusanDAO.findById("PPRU"));
                }
                pExist.setCatatan(jenisBorang);
                permohonanService.saveOrUpdate(pExist);

                Permit pmt = pembangunanServ.findPerrmitByIdMohonAndNoPermit(noFail, noPermit);
                if (pmt != null) {
                    pmt.setKodJenisPermit(kodJenisPermitDAO.findById(kodPermit));
                    pembangunanServ.simpanPermit(pmt);
                } else {
                    Permit permit = new Permit();
                    permit.setKodJenisPermit(kodJenisPermitDAO.findById(kodPermit));;
                    permit.setNoPermit(noPermit);
                    permit.setInfoAudit(infoAudit);
                    permit.setPermohonan(permohonanDAO.findById(noFail));
                    permit.setKodCawangan(pengguna.getKodCawangan());
                    pembangunanServ.simpanPermit(permit);
                }
            } else {
                Permohonan p = new Permohonan();
                p.setIdPermohonan(noFail);
                p.setCawangan(pengguna.getKodCawangan());
                p.setInfoAudit(infoAudit);

                if (jenisBorang.equalsIgnoreCase("A")) {
                    p.setKodUrusan(kodUrusanDAO.findById("PLPS"));
                } else if (jenisBorang.equalsIgnoreCase("B")) {
                    p.setKodUrusan(kodUrusanDAO.findById("LPSP"));
                } else if (jenisBorang.equalsIgnoreCase("D")) {
                    p.setKodUrusan(kodUrusanDAO.findById("PPRU"));
                }
                p.setCatatan(jenisBorang);
                permohonanService.saveOrUpdate(p);

                Permit pmt = pembangunanServ.findPerrmitByIdMohonAndNoPermit(noFail, noPermit);
                if (pmt != null) {
                    //do Nothing
                } else {
                    Permit permit = new Permit();
                    permit.setKodJenisPermit(kodJenisPermitDAO.findById(kodPermit));;
                    permit.setNoPermit(noPermit);
                    permit.setInfoAudit(infoAudit);
                    permit.setPermohonan(permohonanDAO.findById(noFail));
                    permit.setKodCawangan(pengguna.getKodCawangan());
                    pembangunanServ.simpanPermit(permit);
                }
            }
        }

        String link = "";
        if (jenisBorang != null) {
            if (jenisBorang.equalsIgnoreCase("A")) {
                link = "/WEB-INF/jsp/permit/edit/4aeEdit.jsp";
            } else if (jenisBorang.equalsIgnoreCase("B")) {
                link = "/WEB-INF/jsp/permit/edit/4beEdit.jsp";
            } else if (jenisBorang.equalsIgnoreCase("C")) {
                link = "/WEB-INF/jsp/permit/edit/4ceEdit.jsp";
            } else {
                link = "/WEB-INF/jsp/permit/edit/4deEdit.jsp";
            }
            addSimpleMessage("Maklumat Permohonan " + noFail + " Telah Berjaya Disimpan");
        } else {
            link = "/WEB-INF/jsp/permit/edit/newPermit.jsp";
            addSimpleMessage("Sila Pilih Jenis Borang !!!");
        }

        check();
        return new ForwardResolution(link);
    }
    //STOP PERMIT ENTRY

    //START SIGNATURE 
    public Resolution sign() throws Exception {
        idPermohonan = getContext().getRequest().getParameter("noFail");
        noPermit = getContext().getRequest().getParameter("noPermit");
        System.out.println("--idPermohonan-- " + idPermohonan);

        Permohonan pExist = pembangunanServ.findPermohonanByIdPermohonan(idPermohonan);
        jenisBorang = pExist.getCatatan();
        String link = "";

        PelanGIS pWp = pembangunanServ.findPelanByIdPermohonanAndNoPermit(idPermohonan, noPermit);
        PelanGIS pWop = pembangunanServ.findPelanByIdPermohonan(idPermohonan);

        if (pWp != null || pWop != null) {
            addSimpleMessage("Maklumat Tandatangan " + noFail + " Telah Berjaya Disimpan");
        } else {
            addSimpleMessage("Pelan Tidak Wujud, Sila MuatNaik Pelan !!!");
        }

        if (jenisBorang.equalsIgnoreCase("A")) {
            link = "/WEB-INF/jsp/permit/signature/4ae.jsp";
        } else if (jenisBorang.equalsIgnoreCase("B")) {
            link = "/WEB-INF/jsp/permit/signature/4be.jsp";
        } else if (jenisBorang.equalsIgnoreCase("C")) {
            link = "/WEB-INF/jsp/permit/signature/4ce.jsp";
        } else {
            link = "/WEB-INF/jsp/permit/signature/4de.jsp";
        }

        check();
        return new ForwardResolution(link);
    }
    //END SIGNATURE

    public Resolution view() throws Exception {
        pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        noFail = getContext().getRequest().getParameter("noFailRuj");
        noPermit = getContext().getRequest().getParameter("noPermit");
        jenisBorang = getContext().getRequest().getParameter("borang");
        LOG.info("###### id Pengguna : " + pengguna + " IdPermohonan = " + noFail + " JB = " + jenisBorang);

        String link = "";
        if (jenisBorang != null) {
            if (jenisBorang.equalsIgnoreCase("A")) {
                link = "/WEB-INF/jsp/permit/signature/4ae.jsp";
            } else if (jenisBorang.equalsIgnoreCase("B")) {
                link = "/WEB-INF/jsp/permit/signature/4be.jsp";
            } else if (jenisBorang.equalsIgnoreCase("C")) {
                link = "/WEB-INF/jsp/permit/signature/4ce.jsp";
            } else {
                link = "/WEB-INF/jsp/permit/signature/4de.jsp";
            }
            addSimpleMessage("Maklumat Permohonan " + noFail + " Dipaparkan");
        }

        check();
        return new ForwardResolution(link);
    }

    public Resolution viewEdit() throws Exception {
        pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        noFail = getContext().getRequest().getParameter("noFailRuj");
        noPermit = getContext().getRequest().getParameter("noPermit");
        jenisBorang = getContext().getRequest().getParameter("borang");
        LOG.info("###### id Pengguna : " + pengguna + " IdPermohonan = " + noFail + " JB = " + jenisBorang);

        String link = "";
        if (jenisBorang != null) {
            if (jenisBorang.equalsIgnoreCase("A")) {
                link = "/WEB-INF/jsp/permit/edit/4aeEdit.jsp";
            } else if (jenisBorang.equalsIgnoreCase("B")) {
                link = "/WEB-INF/jsp/permit/edit/4beEdit.jsp";
            } else if (jenisBorang.equalsIgnoreCase("C")) {
                link = "/WEB-INF/jsp/permit/edit/4ceEdit.jsp";
            } else {
                link = "/WEB-INF/jsp/permit/edit/4deEdit.jsp";
            }
            addSimpleMessage("Maklumat Permohonan " + noFail + " Dipaparkan");
        }

        check();
        return new ForwardResolution(link);
    }

    //START DELETE PERMIT
    public Resolution batal() throws Exception {
        pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        noFail = getContext().getRequest().getParameter("noFailRuj");
        noPermit = getContext().getRequest().getParameter("noPermit");
        LOG.info("###### id Pengguna : " + pengguna + " IdPermohonan = " + noFail);

        // *TABLE INVOLVED
        // MOHON, MOHON_HAKMILIK, MOHON_TUNTUT_KOS, MOHON_TUNTUT_BAYAR, PERMIT, PERMIT_SAH_LAKU, PERMIT_STRUKTUR_LUAS,
        // PERMIT_LPS_REKOD, PERMIT_ITEM, PERMIT_KNTT_ITEM, GIS PELAN

        if (noFail != null && noPermit != null) {
            System.out.println("--DELETING START--");

            PelanGIS pgNoPermit = pembangunanServ.findPelanByIdPermohonanAndNoPermit(noFail, noPermit);
            if (pgNoPermit != null) {
                pembangunanServ.deletePelan(pgNoPermit);
            } else {
                PelanGIS pg = pembangunanServ.findPelanByIdPermohonan(noFail);
                if (pg != null) {
                    pembangunanServ.deletePelan(pg);
                }
            }

            Permit p = pembangunanServ.findPerrmitByIdMohonAndNoPermit(noFail, noPermit);
            if (p != null) {

                PermitItem pi = pembangunanServ.findPermitItemByIdPermit(p.getIdPermit());
                if (pi != null) {
                    System.out.println("--DELETING PERMIT ITEM START--");
                    PermitItemKuantiti pik = pembangunanServ.findPermitItemKNTTByIdPermitItem(pi.getIdPermitItem());
                    if (pik != null) {
                        pembangunanServ.deletePermitItemKuantiti(pik);
                    }
                    pembangunanServ.deletePermitItem(pi);
                    System.out.println("--FINISHED PERMIT ITEM START--");
                }

                List<PermitLPSRekod> plr = pembangunanServ.findLPSRekodForOverall(noFail, noPermit);
                for (PermitLPSRekod plrExist : plr) {
                    pembangunanServ.deletePermitLPSRekodItem(plrExist);
                }

                PermitStrukturDiluluskan psdl = pembangunanServ.findPermitStrukturLulusByIdPermit(p.getIdPermit());
                if (psdl != null) {
                    pembangunanServ.deletePermitStrukturLulus(psdl);
                }

                PermitSahLaku psl = pembangunanServ.findPermitSahLakuByIdKosPermit(p.getIdPermit());
                if (psl != null) {
                    pembangunanServ.deletePermitSahLaku(psl);
                }

                pembangunanServ.deletePermit(p);
                System.out.println("--PROGRESS(FINISH DELETE ALL PERMIT)--");

                PermohonanTuntutanKos ptk = pembangunanServ.findMohonTuntutKosAndNoLesen(noFail, noPermit);
                if (ptk != null) {
                    PermohonanTuntutanBayar ptb = pembangunanServ.findTuntuBayarByPremium(ptk.getIdKos());
                    if (ptb != null) {
                        pembangunanServ.deletePermohonanTuntutanBayar(ptb);
                        pembangunanServ.deletePermohonanTuntutanKos(ptk);
                        System.out.println("--PROGRESS(FINISH DELETE ALL MOHON TUNTUT)--");

                        HakmilikPermohonan hp = pembangunanServ.findByIdPermohonanAndPermitForCarianPermit(noFail, noPermit);
                        if (hp != null) {
                            pembangunanServ.deleteHp(hp);

                            List<HakmilikPermohonan> pe = pembangunanServ.findHakmilikPermohonanByIdPermohonan(noFail);
                            if (pe.size() > 0) {
                                if (pe.size() > 1) {
                                    System.out.println("--PROGRESS(DO NOTHING)--");
                                } else {
                                    Permohonan mohon = pembangunanServ.findPermohonanByIdPermohonan(noFail);
                                    pembangunanServ.deletePermohonan(mohon);
                                    System.out.println("--PROGRESS(DELETE MOHON)--");
                                }
                            }
                            System.out.println("--PROGRESS(FINISH DELETE HM MOHON)--");
                        }
                    }
                }

            }
            System.out.println("--DELETING END !(=_=)! --");
        }

        addSimpleMessage("Maklumat Permohonan " + noFail + " Telah Berjaya DiHapuskan");
        return new ForwardResolution("/WEB-INF/jsp/permit/signature/senaraiPermitByDaerah.jsp");
    }
    //END DELETE PERMIT

    //START PERMIT ASAS
    public Resolution simpanPermitAsas() throws Exception {

        pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        idPermohonan = getContext().getRequest().getParameter("noFail");
        noPermit = getContext().getRequest().getParameter("noPermit");
        LOG.info("###### id Pengguna : " + pengguna + " IdPermohonan = " + idPermohonan + "" + noPermit);

        infoAudit = new InfoAudit();

        if (idPermohonan != null) {

            Permohonan p = pembangunanServ.findPermohonanByIdPermohonan(idPermohonan);
            infoAudit.setTarikhKemaskini(new java.util.Date());
            infoAudit.setDiKemaskiniOleh(pengguna);
            p.setInfoAudit(infoAudit);
            p.setCawangan(kodCawanganDAO.findById(daerah));

            // For 4Ce Only
            if (jenisBorang.equalsIgnoreCase("C")) {
                p.setKodUrusan(kodUrusanDAO.findById(kPermit));
                pembangunanServ.simpanPermohonan(p);
            }

            permohonanService.saveOrUpdate(p);

            HakmilikPermohonan hpm = pembangunanServ.findByIdPermohonanAndPermitForCarianPermit(idPermohonan, noPermit);
            if (hpm != null) {
                System.out.println("--HERE-MH-");
                if (daerah != null) {
                    hpm.setCawangan(kodCawanganDAO.findById(daerah));
                }
                if (radio1 != null) {
                    hpm.setKodMilik(kodPemilikanDAO.findById(radio1.charAt(0)));
                }
                infoAudit.setTarikhKemaskini(new java.util.Date());
                infoAudit.setDiKemaskiniOleh(pengguna);
                p.setInfoAudit(infoAudit);
                hakmilikPermohonanService.save(hpm);
            } else {
                HakmilikPermohonan hp = new HakmilikPermohonan();
                hp.setPermohonan(p);
                if (radio1 != null) {
                    hp.setKodMilik(kodPemilikanDAO.findById(radio1.charAt(0)));
                }
                if (daerah != null) {
                    hp.setCawangan(kodCawanganDAO.findById(daerah));
                }
                infoAudit.setTarikhMasuk(new java.util.Date());
                infoAudit.setDimasukOleh(pengguna);
                hp.setInfoAudit(infoAudit);
                hp.setPermit(noPermit);
                hakmilikPermohonanService.save(hp);
            }

            Permit permit = pembangunanServ.findPerrmitByIdMohonAndNoPermit(idPermohonan, noPermit);
            if (permit != null) {
                if (permit.getPihak() != null) {
                    System.out.println("--HERE-PERMIT- idPihak Exist");
                    permit.getPihak().setNama(nama);
                    permit.getPihak().setNoPengenalan(noPengenalan);
                    permit.getPihak().setAlamat1(alamat1);
                    permit.getPihak().setAlamat2(alamat2);
                    permit.getPihak().setAlamat3(alamat3);
                    permit.getPihak().setAlamat4(alamat4);
                    permit.getPihak().setPoskod(poskod);
                    if (negeri != null) {
                        permit.getPihak().setNegeri(kodNegeriDAO.findById(negeri));
                    }
                    permit.setTarikhPermit(tarikhPermit);
                    permit.setKeterangan(maksud);
                    pembangunanServ.simpanPermit(permit);
                } else {

                    System.out.println("--HERE-PERMIT- idPihak !Exist");
                    Pihak pi = new Pihak();
                    pi.setNama(nama);
                    pi.setNoPengenalan(noPengenalan);
                    pi.setAlamat1(alamat1);
                    pi.setAlamat2(alamat2);
                    pi.setAlamat3(alamat3);
                    pi.setAlamat4(alamat4);
                    pi.setPoskod(poskod);
                    if (negeri != null) {
                        pi.setNegeri(kodNegeriDAO.findById(negeri));
                    }
                    infoAudit.setTarikhMasuk(new java.util.Date());
                    infoAudit.setDimasukOleh(pengguna);
                    pi.setInfoAudit(infoAudit);
                    pembangunanServ.simpanPihak(pi);

                    permit.setPihak(pi);
                    permit.setTarikhPermit(tarikhPermit);
                    permit.setKeterangan(maksud);
                    pembangunanServ.simpanPermit(permit);
                }
            }

            PermohonanTuntutanKos ptkExist = pembangunanServ.findMohonTuntutKosAndNoLesen(idPermohonan, noPermit);
            if (ptkExist != null) {
                System.out.println("PermohonanTuntutanKos Exist");
                BigDecimal b = new BigDecimal(bayaran);
                ptkExist.setAmaunTuntutan(b);
                ptkExist.setCatatan(noResit);
                infoAudit.setDiKemaskiniOleh(pengguna);
                infoAudit.setTarikhKemaskini(new java.util.Date());
                ptkExist.setInfoAudit(infoAudit);
                ptkExist.setItem("PERMIT");
                pembangunanServ.simpanPermohonanTuntutanKos(ptkExist);

                PermohonanTuntutanBayar ptbExist = pembangunanServ.findTuntuBayarByPremium(ptkExist.getIdKos());
                if (ptbExist != null) {
                    System.out.println("PermohonanTuntutanBayar Exist");
                    if (noResit != null) {
                        if (!ptbExist.getInfoAudit().getDimasukOleh().equals(penggunaDAO.findById("PERMIT"))) {
                            ptbExist.setDokumenKewangan(dokumenKewanganDAO.findById(noResit));
                        }
                    }
                    ptbExist.setAmaun(b);
                    ptbExist.setPermohonan(permohonanDAO.findById(idPermohonan));
                    infoAudit.setDiKemaskiniOleh(pengguna);
                    infoAudit.setTarikhKemaskini(new java.util.Date());

                    //To Catter Permit Data Collection
                    if (ptbExist.getInfoAudit().getDimasukOleh().equals(penggunaDAO.findById("PERMIT"))) {
                        infoAudit.setDimasukOleh(pengguna);
                    }
                    ptbExist.setInfoAudit(infoAudit);
                    pembangunanServ.simpanPermohonanTuntutanBayar(ptbExist);
                }
            } else {

                HakmilikPermohonan hp = pembangunanServ.findByIdPermohonanAndPermitForCarianPermit(idPermohonan, noPermit);
                PermohonanTuntutanKos ptk = new PermohonanTuntutanKos();
                ptk.setPermohonan(permohonanDAO.findById(idPermohonan));
                ptk.setNoLesen(noPermit);
                BigDecimal b = new BigDecimal(bayaran);
                ptk.setAmaunTuntutan(b);
                ptk.setCatatan(noResit);
                ptk.setKodTransaksi(kodTransaksiDAO.findById("71899"));

                if (hp != null) {
                    if (hp.getCawangan() != null) {
                        ptk.setCawangan(hp.getCawangan());
                    }
                } else {
                    ptk.setCawangan(kodCawanganDAO.findById(pengguna.getKodCawangan().getKod()));
                }

                infoAudit.setDimasukOleh(pengguna);
                infoAudit.setTarikhMasuk(new java.util.Date());
                ptk.setInfoAudit(infoAudit);
                ptk.setItem("PERMIT");
                pembangunanServ.simpanPermohonanTuntutanKos(ptk);

                PermohonanTuntutanBayar ptb = new PermohonanTuntutanBayar();
                ptb.setPermohonanTuntutanKos(ptk);
                ptb.setNoLesen(noPermit);
                if (noResit != null) {
                    ptb.setDokumenKewangan(dokumenKewanganDAO.findById(noResit));
                }
                ptb.setAmaun(b);
                infoAudit.setDimasukOleh(pengguna);
                infoAudit.setTarikhMasuk(new java.util.Date());
                ptb.setInfoAudit(infoAudit);
                ptb.setPermohonan(permohonanDAO.findById(idPermohonan));
                pembangunanServ.simpanPermohonanTuntutanBayar(ptb);
            }
        }

        String link = "";
        if (jenisBorang.equalsIgnoreCase("A")) {
            link = "/WEB-INF/jsp/permit/edit/4aeEdit.jsp";
        } else if (jenisBorang.equalsIgnoreCase("B")) {
            link = "/WEB-INF/jsp/permit/edit/4beEdit.jsp";
        } else if (jenisBorang.equalsIgnoreCase("C")) {
            link = "/WEB-INF/jsp/permit/edit/4ceEdit.jsp";
        } else {
            link = "/WEB-INF/jsp/permit/edit/4deEdit.jsp";
        }

        addSimpleMessage("Maklumat Asas Fail " + idPermohonan + " Telah Berjaya Disimpan");
        return new ForwardResolution(link);
    }
    //END PERMIT ASAS

    //START PERIHAL
    public Resolution simpanPerihal() throws Exception {

        pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        idPermohonan = getContext().getRequest().getParameter("noFail");
        noPermit = getContext().getRequest().getParameter("noPermit");
        LOG.info("###### id Pengguna : " + pengguna + " IdPermohonan = " + idPermohonan);

        if (idPermohonan != null) {
            HakmilikPermohonan hp = pembangunanServ.findByIdPermohonanAndPermitForCarianPermit(idPermohonan, noPermit);

            if (hp != null) {
                System.out.println("--HERE NOT 1st Entry--" + hp.getPermohonan().getIdPermohonan());
                hp.setBandarPekanMukimBaru(kodBandarPekanMukimDAO.findById(Integer.parseInt(bandar)));
                if (noLot != null) {
                    hp.setNoLot(noLot);
                }
                if (kodLot != null) {
                    hp.setLot(kodLotDAO.findById(kodLot));
                }
                BigDecimal l = new BigDecimal(luas);
                System.out.println("--luas--" + l);
                hp.setLuasTerlibat(l);
                if (radio2 != null) {
                    hp.setKodUnitLuas(kodUOMDAO.findById(radio2));
                }
                infoAudit = new InfoAudit();
                infoAudit.setTarikhKemaskini(new java.util.Date());
                infoAudit.setDiKemaskiniOleh(pengguna);
                hp.setInfoAudit(infoAudit);
                hp.setKodMilik(kodPemilikanDAO.findById(tanah.charAt(0)));
                hp.setCatatan(tempat);
                pembangunanServ.simpanHakmilikPermohonan(hp);
            } else {
                System.out.println("--HERE 1st Entry--");
                HakmilikPermohonan hpCreate = new HakmilikPermohonan();
                hpCreate.setPermohonan(permohonanDAO.findById(idPermohonan));
                hpCreate.setBandarPekanMukimBaru(kodBandarPekanMukimDAO.findById(Integer.parseInt(bandar)));
                if (noLot != null) {
                    hpCreate.setNoLot(noLot);
                }
                if (kodLot != null) {
                    hpCreate.setLot(kodLotDAO.findById(kodLot));
                }
                BigDecimal l = new BigDecimal(luas);
                hpCreate.setLuasTerlibat(l);
                if (radio2 != null) {
                    hpCreate.setKodUnitLuas(kodUOMDAO.findById(radio2));
                }
                infoAudit = new InfoAudit();
                infoAudit.setDimasukOleh(pengguna);
                infoAudit.setTarikhMasuk(new java.util.Date());
                hpCreate.setInfoAudit(infoAudit);
                hpCreate.setKodMilik(kodPemilikanDAO.findById(tanah.charAt(0)));
                hpCreate.setCatatan(tempat);
                pembangunanServ.simpanHakmilikPermohonan(hpCreate);
            }
        }

        String link = "";
        if (jenisBorang.equalsIgnoreCase("A")) {
            link = "/WEB-INF/jsp/permit/edit/4aeEdit.jsp";
        } else if (jenisBorang.equalsIgnoreCase("B")) {
            link = "/WEB-INF/jsp/permit/edit/4beEdit.jsp";
        } else if (jenisBorang.equalsIgnoreCase("C")) {
            link = "/WEB-INF/jsp/permit/edit/4ceEdit.jsp";
        } else {
            link = "/WEB-INF/jsp/permit/edit/4deEdit.jsp";
        }

        addSimpleMessage("Maklumat Perihal Mengenai Tanah " + idPermohonan + " Telah Berjaya Disimpan");
        check();
        return new ForwardResolution(link);
    }
    //END PERIHAL

    //4ae
    public Resolution simpanJadual4ae() throws Exception {

        pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        idPermohonan = getContext().getRequest().getParameter("noFail");
        noPermit = getContext().getRequest().getParameter("noPermit");
        LOG.info("###### id Pengguna : " + pengguna + " IdPermohonan = " + idPermohonan);
        infoAudit = new InfoAudit();
        if (idPermohonan != null) {

            Permit permit = pembangunanServ.findPerrmitByIdMohonAndNoPermit(idPermohonan, noPermit);
            infoAudit = new InfoAudit();

            if (permit != null) {
                permit.setTarikhPermitMula(tarikhMula);
                permit.setTarikhpermitAkhir(tarikhtamat);
                permit.setKaedah(kaedah);
                permit.setPeruntukanTambahan(tambah);
                infoAudit.setTarikhKemaskini(new java.util.Date());
                infoAudit.setDiKemaskiniOleh(pengguna);
                permit.setInfoAudit(infoAudit);
                pembangunanServ.simpanPermit(permit);

                PermitSahLaku psl = pembangunanServ.findPermitSahLakuByIdKosPermit(permit.getIdPermit());
                if (psl != null) {
                    System.out.println("--HERE PERMIT SAH LAKU EXIST--");
                    psl.setTarikhPermitMula(permit.getTarikhPermitMula());
                    System.out.println("--p.tarikhMula-- " + psl.getTarikhPermitMula());
                    psl.setTarikhPermitTamat(permit.getTarikhpermitAkhir());
                    infoAudit.setTarikhKemaskini(new java.util.Date());
                    infoAudit.setDiKemaskiniOleh(pengguna);
                    psl.setInfoAudit(infoAudit);
                    psl.setTarikhPermit(permit.getTarikhPermit());
                    psl.setNoLesen(noPermit);
                    pembangunanServ.simpanPermitSahLaku(psl);
                } else {
                    PermitSahLaku pslNew = new PermitSahLaku();
                    pslNew.setPermohonan(permohonanDAO.findById(idPermohonan));
                    pslNew.setPermit(permit);
                    pslNew.setTarikhPermitMula(permit.getTarikhPermitMula());
                    pslNew.setTarikhPermitTamat(permit.getTarikhpermitAkhir());
                    pslNew.setKodCawangan(pengguna.getKodCawangan());
                    infoAudit.setTarikhMasuk(new java.util.Date());
                    infoAudit.setDimasukOleh(pengguna);
                    pslNew.setInfoAudit(infoAudit);
                    pslNew.setTarikhPermit(permit.getTarikhPermit());
                    pslNew.setNoLesen(noPermit);
                    pembangunanServ.simpanPermitSahLaku(pslNew);
                }
            }
        }

        String link = "";
        if (jenisBorang.equalsIgnoreCase("A")) {
            link = "/WEB-INF/jsp/permit/edit/4aeEdit.jsp";
        } else if (jenisBorang.equalsIgnoreCase("B")) {
            link = "/WEB-INF/jsp/permit/edit/4beEdit.jsp";
        } else if (jenisBorang.equalsIgnoreCase("C")) {
            link = "/WEB-INF/jsp/permit/edit/4ceEdit.jsp";
        } else {
            link = "/WEB-INF/jsp/permit/edit/4deEdit.jsp";
        }

        addSimpleMessage("Maklumat Jadual Bagi " + idPermohonan + " Telah Berjaya Disimpan");
        check();
        return new ForwardResolution(link);
    }

    //4be
    public Resolution simpanJadual4be() throws Exception {

        pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        idPermohonan = getContext().getRequest().getParameter("noFail");
        noPermit = getContext().getRequest().getParameter("noPermit");
        LOG.info("###### id Pengguna : " + pengguna + " IdPermohonan = " + idPermohonan);
        infoAudit = new InfoAudit();
        if (idPermohonan != null) {

            Permit permit = pembangunanServ.findPerrmitByIdMohonAndNoPermit(idPermohonan, noPermit);
            infoAudit = new InfoAudit();

            if (permit != null) {
                permit.setTarikhPermitMula(tarikhMula);
                permit.setTarikhpermitAkhir(tarikhtamat);
                permit.setPeruntukanTambahan(tambah);
                permit.setPeruntukanTambahanLain(tambah1);
                permit.setKaedah(kaedah);
                infoAudit.setTarikhKemaskini(new java.util.Date());
                infoAudit.setDiKemaskiniOleh(pengguna);
                permit.setInfoAudit(infoAudit);
                pembangunanServ.simpanPermit(permit);

                PermitSahLaku psl = pembangunanServ.findPermitSahLakuByIdKosPermit(permit.getIdPermit());
                if (psl != null) {
                    System.out.println("--HERE PERMIT SAH LAKU EXIST--");
                    psl.setTarikhPermitMula(permit.getTarikhPermitMula());
                    System.out.println("--p.tarikhMula-- " + psl.getTarikhPermitMula());
                    psl.setTarikhPermitTamat(permit.getTarikhpermitAkhir());
                    infoAudit.setTarikhKemaskini(new java.util.Date());
                    infoAudit.setDiKemaskiniOleh(pengguna);
                    psl.setInfoAudit(infoAudit);
                    psl.setTarikhPermit(permit.getTarikhPermit());
                    psl.setNoLesen(noPermit);
                    pembangunanServ.simpanPermitSahLaku(psl);
                } else {
                    PermitSahLaku pslNew = new PermitSahLaku();
                    pslNew.setPermohonan(permohonanDAO.findById(idPermohonan));
                    pslNew.setPermit(permit);
                    pslNew.setTarikhPermitMula(permit.getTarikhPermitMula());
                    pslNew.setTarikhPermitTamat(permit.getTarikhpermitAkhir());
                    pslNew.setKodCawangan(pengguna.getKodCawangan());
                    infoAudit.setTarikhMasuk(new java.util.Date());
                    infoAudit.setDimasukOleh(pengguna);
                    pslNew.setInfoAudit(infoAudit);
                    pslNew.setTarikhPermit(permit.getTarikhPermit());
                    pslNew.setNoLesen(noPermit);
                    pembangunanServ.simpanPermitSahLaku(pslNew);
                }

                PermitItem pi = pembangunanServ.findPermitItemByIdPermit(permit.getIdPermit());
                if (pi != null) {
                    infoAudit.setTarikhKemaskini(new java.util.Date());
                    infoAudit.setDiKemaskiniOleh(pengguna);
                    pi.setInfoAudit(infoAudit);
                    pembangunanServ.simpanPermitItem(pi);

                    PermitItemKuantiti pik = pembangunanServ.findPermitItemKNTTByIdPermitItem(pi.getIdPermitItem());
                    if (kntt != null) {
                        BigDecimal p = new BigDecimal(kntt);
                        pik.setKuantitiMax(p);
                    }
                    if (jenisUom != null) {
                        pik.setKuantitiMaxUom(kodUOMDAO.findById(jenisUom));
                    }
                    if (kntt1 != null) {
                        BigDecimal p1 = new BigDecimal(kntt1);
                        pik.setKuantitiMaxTempoh(p1);
                    }
                    if (jenisUom1 != null) {
                        pik.setKuantitiMaxTempohUom(kodUOMDAO.findById(jenisUom1));
                    }
                    if (rm != null) {
                        BigDecimal p11 = new BigDecimal(rm);
                        pik.setNilai(p11);
                    }
                    if (bagi != null) {
                        pik.setNilaiUom(kodUOMDAO.findById(bagi));
                    }
                    infoAudit.setTarikhKemaskini(new java.util.Date());
                    infoAudit.setDiKemaskiniOleh(pengguna);
                    pik.setInfoAudit(infoAudit);
                    pembangunanServ.simpanPermitItemKntt(pik);

                } else {
                    PermitItem pit = new PermitItem();
                    pit.setPermit(permit);
                    pit.setKodCawangan(pengguna.getKodCawangan());
                    infoAudit.setTarikhMasuk(new java.util.Date());
                    infoAudit.setDimasukOleh(pengguna);
                    pit.setInfoAudit(infoAudit);
                    pembangunanServ.simpanPermitItem(pit);

                    PermitItemKuantiti pikNew = new PermitItemKuantiti();
                    pikNew.setPermitItem(pit);
                    pikNew.setKodCawangan(pit.getKodCawangan());
                    infoAudit.setTarikhMasuk(new java.util.Date());
                    infoAudit.setDimasukOleh(pengguna);
                    pikNew.setInfoAudit(infoAudit);
                    if (kntt != null) {
                        BigDecimal p = new BigDecimal(kntt);
                        pikNew.setKuantitiMax(p);
                    }
                    if (jenisUom != null) {
                        pikNew.setKuantitiMaxUom(kodUOMDAO.findById(jenisUom));
                    }
                    if (kntt1 != null) {
                        BigDecimal p1 = new BigDecimal(kntt1);
                        pikNew.setKuantitiMaxTempoh(p1);
                    }
                    if (jenisUom1 != null) {
                        pikNew.setKuantitiMaxTempohUom(kodUOMDAO.findById(jenisUom1));
                    }
                    if (rm != null) {
                        BigDecimal p11 = new BigDecimal(rm);
                        pikNew.setNilai(p11);
                    }
                    if (bagi != null) {
                        pikNew.setNilaiUom(kodUOMDAO.findById(bagi));
                    }

                    pembangunanServ.simpanPermitItemKntt(pikNew);
                }
            }
        }

        String link = "";
        if (jenisBorang.equalsIgnoreCase("A")) {
            link = "/WEB-INF/jsp/permit/edit/4aeEdit.jsp";
        } else if (jenisBorang.equalsIgnoreCase("B")) {
            link = "/WEB-INF/jsp/permit/edit/4beEdit.jsp";
        } else if (jenisBorang.equalsIgnoreCase("C")) {
            link = "/WEB-INF/jsp/permit/edit/4ceEdit.jsp";
        } else {
            link = "/WEB-INF/jsp/permit/edit/4deEdit.jsp";
        }

        addSimpleMessage("Maklumat Jadual Bagi " + idPermohonan + " Telah Berjaya Disimpan");
        check();
        return new ForwardResolution(link);
    }

    //4ce
    public Resolution simpanJadual4ce() throws Exception {

        pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        idPermohonan = getContext().getRequest().getParameter("noFail");
        noPermit = getContext().getRequest().getParameter("noPermit");
        LOG.info("###### id Pengguna : " + pengguna + " IdPermohonan = " + idPermohonan);
        infoAudit = new InfoAudit();
        if (idPermohonan != null) {

            Permit permit = pembangunanServ.findPerrmitByIdMohonAndNoPermit(idPermohonan, noPermit);
            infoAudit = new InfoAudit();

            if (permit != null) {
                permit.setTarikhPermitMula(tarikhMula);
                permit.setTarikhpermitAkhir(tarikhtamat);
                infoAudit.setTarikhKemaskini(new java.util.Date());
                infoAudit.setDiKemaskiniOleh(pengguna);
                permit.setInfoAudit(infoAudit);
                permit.setKaedah(kaedah);
                permit.setPeruntukanTambahan(tambah);
                pembangunanServ.simpanPermit(permit);

                PermitSahLaku psl = pembangunanServ.findPermitSahLakuByIdKosPermit(permit.getIdPermit());
                if (psl != null) {
                    System.out.println("--HERE PERMIT SAH LAKU EXIST--");
                    psl.setTarikhPermitMula(permit.getTarikhPermitMula());
                    System.out.println("--p.tarikhMula-- " + psl.getTarikhPermitMula());
                    psl.setTarikhPermitTamat(permit.getTarikhpermitAkhir());
                    infoAudit.setTarikhKemaskini(new java.util.Date());
                    infoAudit.setDiKemaskiniOleh(pengguna);
                    psl.setInfoAudit(infoAudit);
                    psl.setTarikhPermit(permit.getTarikhPermit());
                    psl.setNoLesen(noPermit);
                    pembangunanServ.simpanPermitSahLaku(psl);
                } else {
                    PermitSahLaku pslNew = new PermitSahLaku();
                    pslNew.setPermohonan(permohonanDAO.findById(idPermohonan));
                    pslNew.setPermit(permit);
                    pslNew.setTarikhPermitMula(permit.getTarikhPermitMula());
                    pslNew.setTarikhPermitTamat(permit.getTarikhpermitAkhir());
                    pslNew.setKodCawangan(pengguna.getKodCawangan());
                    infoAudit.setTarikhMasuk(new java.util.Date());
                    infoAudit.setDimasukOleh(pengguna);
                    pslNew.setInfoAudit(infoAudit);
                    pslNew.setTarikhPermit(permit.getTarikhPermit());
                    pslNew.setNoLesen(noPermit);
                    pembangunanServ.simpanPermitSahLaku(pslNew);
                }

                PermitItem pi = pembangunanServ.findPermitItemByIdPermit(permit.getIdPermit());
                if (pi != null) {
                    if (itemPermit != null) {
                        pi.setKodItemPermit(kodItemPermitDAO.findById(itemPermit));
                    }
                    infoAudit.setTarikhKemaskini(new java.util.Date());
                    infoAudit.setDiKemaskiniOleh(pengguna);
                    pi.setInfoAudit(infoAudit);
                    pembangunanServ.simpanPermitItem(pi);

                    PermitItemKuantiti pik = pembangunanServ.findPermitItemKNTTByIdPermitItem(pi.getIdPermitItem());
                    if (kntt != null) {
                        BigDecimal p = new BigDecimal(kntt);
                        pik.setKuantitiMax(p);
                    }
                    if (jenisUom != null) {
                        pik.setKuantitiMaxUom(kodUOMDAO.findById(jenisUom));
                    }
                    if (rm != null) {
                        BigDecimal p11 = new BigDecimal(rm);
                        pik.setNilai(p11);
                    }
                    if (bagi != null) {
                        pik.setNilaiUom(kodUOMDAO.findById(bagi));
                    }
                    infoAudit.setTarikhKemaskini(new java.util.Date());
                    infoAudit.setDiKemaskiniOleh(pengguna);
                    pik.setInfoAudit(infoAudit);
                    pembangunanServ.simpanPermitItemKntt(pik);

                } else {
                    PermitItem pit = new PermitItem();
                    pit.setPermit(permit);
                    pit.setKodCawangan(pengguna.getKodCawangan());
                    if (itemPermit != null) {
                        pit.setKodItemPermit(kodItemPermitDAO.findById(itemPermit));
                    }
                    infoAudit.setTarikhMasuk(new java.util.Date());
                    infoAudit.setDimasukOleh(pengguna);
                    pit.setInfoAudit(infoAudit);
                    pembangunanServ.simpanPermitItem(pit);

                    PermitItemKuantiti pikNew = new PermitItemKuantiti();
                    pikNew.setPermitItem(pit);
                    pikNew.setKodCawangan(pit.getKodCawangan());
                    infoAudit.setTarikhMasuk(new java.util.Date());
                    infoAudit.setDimasukOleh(pengguna);
                    pikNew.setInfoAudit(infoAudit);
                    if (kntt != null) {
                        BigDecimal p = new BigDecimal(kntt);
                        pikNew.setKuantitiMax(p);
                    }
                    if (jenisUom != null) {
                        pikNew.setKuantitiMaxUom(kodUOMDAO.findById(jenisUom));
                    }
                    if (rm != null) {
                        BigDecimal p11 = new BigDecimal(rm);
                        pikNew.setNilai(p11);
                    }
                    if (bagi != null) {
                        pikNew.setNilaiUom(kodUOMDAO.findById(bagi));
                    }
                    pembangunanServ.simpanPermitItemKntt(pikNew);
                }
            }
        }

        String link = "";
        if (jenisBorang.equalsIgnoreCase("A")) {
            link = "/WEB-INF/jsp/permit/edit/4aeEdit.jsp";
        } else if (jenisBorang.equalsIgnoreCase("B")) {
            link = "/WEB-INF/jsp/permit/edit/4beEdit.jsp";
        } else if (jenisBorang.equalsIgnoreCase("C")) {
            link = "/WEB-INF/jsp/permit/edit/4ceEdit.jsp";
        } else {
            link = "/WEB-INF/jsp/permit/edit/4deEdit.jsp";
        }

        addSimpleMessage("Maklumat Jadual Bagi " + idPermohonan + " Telah Berjaya Disimpan");
        check();
        return new ForwardResolution(link);
    }

    //4de
    public Resolution simpanSyarat() throws Exception {

        pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        idPermohonan = getContext().getRequest().getParameter("noFail");
        noPermit = getContext().getRequest().getParameter("noPermit");
        LOG.info("###### id Pengguna : " + pengguna + " IdPermohonan = " + idPermohonan);
        infoAudit = new InfoAudit();
        if (idPermohonan != null) {
            HakmilikPermohonan hp = pembangunanServ.findByIdPermohonanAndPermitForCarianPermit(idPermohonan, noPermit);
            hp.setLokasi(lokasi);
            infoAudit.setTarikhKemaskini(new java.util.Date());
            infoAudit.setDiKemaskiniOleh(pengguna);
            hp.setInfoAudit(infoAudit);
            pembangunanServ.simpanHakmilikPermohonan(hp);

            Permit permit = pembangunanServ.findPerrmitByIdMohonAndNoPermit(idPermohonan, noPermit);
            infoAudit = new InfoAudit();

            if (permit != null) {
                permit.setTarikhPermitMula(tarikhMula);
                permit.setTarikhpermitAkhir(tarikhtamat);
                permit.setPeruntukanTambahan(tambah);
                infoAudit.setTarikhKemaskini(new java.util.Date());
                infoAudit.setDiKemaskiniOleh(pengguna);
                permit.setInfoAudit(infoAudit);
                pembangunanServ.simpanPermit(permit);

                PermitSahLaku psl = pembangunanServ.findPermitSahLakuByIdKosPermit(permit.getIdPermit());

                if (psl != null) {
                    System.out.println("--HERE PERMIT SAH LAKU EXIST--");
                    psl.setTarikhPermitMula(permit.getTarikhPermitMula());
                    System.out.println("--p.tarikhMula-- " + psl.getTarikhPermitMula());
                    psl.setTarikhPermitTamat(permit.getTarikhpermitAkhir());
                    infoAudit.setTarikhKemaskini(new java.util.Date());
                    infoAudit.setDiKemaskiniOleh(pengguna);
                    psl.setInfoAudit(infoAudit);
                    psl.setTarikhPermit(permit.getTarikhPermit());
                    psl.setNoLesen(noPermit);
                    pembangunanServ.simpanPermitSahLaku(psl);
                } else {
                    PermitSahLaku pslNew = new PermitSahLaku();
                    pslNew.setPermohonan(permohonanDAO.findById(idPermohonan));
                    pslNew.setPermit(permit);
                    pslNew.setTarikhPermitMula(permit.getTarikhPermitMula());
                    pslNew.setTarikhPermitTamat(permit.getTarikhpermitAkhir());
                    pslNew.setKodCawangan(pengguna.getKodCawangan());
                    infoAudit.setTarikhMasuk(new java.util.Date());
                    infoAudit.setDimasukOleh(pengguna);
                    pslNew.setInfoAudit(infoAudit);
                    pslNew.setTarikhPermit(permit.getTarikhPermit());
                    pslNew.setNoLesen(noPermit);
                    pembangunanServ.simpanPermitSahLaku(pslNew);
                }

                PermitStrukturDiluluskan psdl = pembangunanServ.findPermitStrukturLulusByIdPermit(permit.getIdPermit());

                if (psdl != null) {
                    System.out.println("--HERE PERMIT STRUKTUR DILULUSKAN EXIST--");
                    psdl.setJenisStruktur(jenisStruktur);
                    psdl.setKeterangan(jenisStruktur);
                    psdl.setLuasStruktur(hp.getLuasTerlibat());
                    System.out.println("--psdl.setLuasStruktur-- " + psdl.getLuasStruktur());
                    if (hp.getKodUnitLuas() != null) {
                        psdl.setKodUomLuas(kodUOMDAO.findById(hp.getKodUnitLuas().getKod()));
                    }
                    infoAudit.setTarikhKemaskini(new java.util.Date());
                    infoAudit.setDiKemaskiniOleh(pengguna);
                    psdl.setInfoAudit(infoAudit);
                    psdl.setNoLesen(noPermit);
                    pembangunanServ.simpanPermitStrukturDiluluskan(psdl);
                } else {
                    PermitStrukturDiluluskan psdlNew = new PermitStrukturDiluluskan();
                    psdlNew.setJenisStruktur(jenisStruktur);
                    psdlNew.setKeterangan(jenisStruktur);
                    psdlNew.setKodCawangan(pengguna.getKodCawangan());
                    psdlNew.setLuasStruktur(hp.getLuasTerlibat());
                    if (hp.getKodUnitLuas() != null) {
                        psdlNew.setKodUomLuas(kodUOMDAO.findById(hp.getKodUnitLuas().getKod()));
                    }
                    infoAudit.setTarikhMasuk(new java.util.Date());
                    infoAudit.setDimasukOleh(pengguna);
                    psdlNew.setInfoAudit(infoAudit);
                    psdlNew.setPermit(permit);
                    psdlNew.setPermohonan(permohonanDAO.findById(idPermohonan));
                    psdlNew.setNoLesen(noPermit);
                    pembangunanServ.simpanPermitStrukturDiluluskan(psdlNew);
                }
            }
        }

        String link = "";
        if (jenisBorang.equalsIgnoreCase("A")) {
            link = "/WEB-INF/jsp/permit/edit/4aeEdit.jsp";
        } else if (jenisBorang.equalsIgnoreCase("B")) {
            link = "/WEB-INF/jsp/permit/edit/4beEdit.jsp";
        } else if (jenisBorang.equalsIgnoreCase("C")) {
            link = "/WEB-INF/jsp/permit/edit/4ceEdit.jsp";
        } else {
            link = "/WEB-INF/jsp/permit/edit/4deEdit.jsp";
        }

        addSimpleMessage("Syarat - Syarat Bagi " + idPermohonan + " Telah Berjaya Disimpan");
        check();
        return new ForwardResolution(link);
    }

    //START CARIAN BAYARAN
    public Resolution carianBayaranPopup() {
        noFail = getContext().getRequest().getParameter("noFail");
        jenisBorang = getContext().getRequest().getParameter("jenisBorang");
        noPermit = getContext().getRequest().getParameter("noPermit");
        return new JSP("permit/edit/cBEdit.jsp").addParameter("popup", "true");
    }

    public Resolution cariBayaran() {
        noFail = getContext().getRequest().getParameter("noFail");
        jenisBorang = getContext().getRequest().getParameter("jenisBorang");
        noPermit = getContext().getRequest().getParameter("noPermit");

        List<Transaksi> t = pembangunanServ.findTransaksiByIdKewDokList(noResitCarian);
        if (!t.isEmpty()) {
            kt = t.get(0);
        }

        if (kt != null) {
            noResit = kt.getDokumenKewangan().getIdDokumenKewangan();
            ama = kt.getAmaun().toString();
        }
        getContext().getRequest().setAttribute("find", Boolean.TRUE);
        getContext().getRequest().setAttribute("found", Boolean.TRUE);
        return new JSP("permit/edit/cBEdit.jsp").addParameter("popup", "true");
    }

    public Resolution simpanCarianBayaran() throws Exception {
        idPermohonan = getContext().getRequest().getParameter("noFail");
        jenisBorang = getContext().getRequest().getParameter("jenisBorang");
        noPermit = getContext().getRequest().getParameter("noPermit");

        pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        System.out.println("idPermohonan " + idPermohonan + "-" + noPermit);
        infoAudit = new InfoAudit();

        if (idPermohonan != null) {
            if (noResit != null && ama != null && noPermit != null) {
                PermohonanTuntutanKos ptkExist = pembangunanServ.findMohonTuntutKosAndNoLesen(idPermohonan, noPermit);
                if (ptkExist != null) {
                    System.out.println("PermohonanTuntutanKos Exist");
                    BigDecimal b = new BigDecimal(ama);
                    ptkExist.setAmaunTuntutan(b);
                    infoAudit.setDiKemaskiniOleh(pengguna);
                    infoAudit.setTarikhKemaskini(new java.util.Date());
                    ptkExist.setInfoAudit(infoAudit);
                    pembangunanServ.simpanPermohonanTuntutanKos(ptkExist);

                    PermohonanTuntutanBayar ptbExist = pembangunanServ.findTuntuBayarByPremium(ptkExist.getIdKos());
                    if (ptbExist != null) {
                        System.out.println("PermohonanTuntutanBayar Exist");
                        ptbExist.setDokumenKewangan(dokumenKewanganDAO.findById(noResit));
                        ptbExist.setAmaun(b);
                        ptbExist.setPermohonan(permohonanDAO.findById(idPermohonan));
                        infoAudit.setDiKemaskiniOleh(pengguna);
                        infoAudit.setTarikhKemaskini(new java.util.Date());
                        ptbExist.setInfoAudit(infoAudit);
                        pembangunanServ.simpanPermohonanTuntutanBayar(ptbExist);
                    }
                } else {
                    HakmilikPermohonan hp = pembangunanServ.findByIdPermohonanAndPermitForCarianPermit(idPermohonan, noPermit);
                    PermohonanTuntutanKos ptk = new PermohonanTuntutanKos();
                    ptk.setPermohonan(permohonanDAO.findById(idPermohonan));
                    ptk.setNoLesen(noPermit);
                    BigDecimal b = new BigDecimal(ama);
                    ptk.setAmaunTuntutan(b);
                    ptk.setKodTransaksi(kodTransaksiDAO.findById("71899"));

                    if (hp != null) {
                        if (hp.getCawangan() != null) {
                            ptk.setCawangan(hp.getCawangan());
                        }
                    } else {
                        ptk.setCawangan(kodCawanganDAO.findById(pengguna.getKodCawangan().getKod()));
                    }

                    infoAudit.setDimasukOleh(pengguna);
                    infoAudit.setTarikhMasuk(new java.util.Date());
                    ptk.setInfoAudit(infoAudit);
                    ptk.setItem("PERMIT");
                    pembangunanServ.simpanPermohonanTuntutanKos(ptk);

                    PermohonanTuntutanBayar ptb = new PermohonanTuntutanBayar();
                    ptb.setPermohonanTuntutanKos(ptk);
                    ptb.setDokumenKewangan(dokumenKewanganDAO.findById(noResit));
                    ptb.setAmaun(b);
                    infoAudit.setDimasukOleh(pengguna);
                    infoAudit.setTarikhMasuk(new java.util.Date());
                    ptb.setInfoAudit(infoAudit);
                    ptb.setPermohonan(permohonanDAO.findById(idPermohonan));
                    ptb.setNoLesen(noPermit);
                    pembangunanServ.simpanPermohonanTuntutanBayar(ptb);
                }
            }
        }
        getContext().getRequest().setAttribute("find", Boolean.TRUE);
        getContext().getRequest().setAttribute("found", Boolean.TRUE);
        check();
        addSimpleMessage("Maklumat Carian Bayaran Disimpan");
        return new JSP("permit/edit/cBEdit.jsp").addParameter("popup", "true");
    }
    //END CARIAN BAYARAN

    //START CARIAN PIHAK
    public Resolution carianPihakPopup() {
        noFail = getContext().getRequest().getParameter("noFail");
        jenisBorang = getContext().getRequest().getParameter("jenisBorang");
        noPermit = getContext().getRequest().getParameter("noPermit");
        return new JSP("permit/edit/cPEdit.jsp").addParameter("popup", "true");
    }

    public Resolution cariPemilikNoIc() {
        noFail = getContext().getRequest().getParameter("noFail");
        jenisBorang = getContext().getRequest().getParameter("jenisBorang");
        noPermit = getContext().getRequest().getParameter("noPermit");

        List<Pihak> pihakList = pembangunanServ.findPihakByNoIc(noPengenalan);
        if (!pihakList.isEmpty()) {
            cariPihak = pihakList.get(0);
        }

        if (cariPihak != null) {
            System.out.println("--cariPihakIc-- " + cariPihak.getNoPengenalan());
            noPengenalan = cariPihak.getNoPengenalan();
            nama = cariPihak.getNama();
            alamat1 = cariPihak.getAlamat1();
            alamat2 = cariPihak.getAlamat2();
            alamat3 = cariPihak.getAlamat3();
            alamat4 = cariPihak.getAlamat4();
            poskod = cariPihak.getPoskod();
            if (cariPihak.getNegeri() != null) {
                negeri = cariPihak.getNegeri().getKod();
            }
        }
        getContext().getRequest().setAttribute("found", Boolean.TRUE);
        getContext().getRequest().setAttribute("find", Boolean.TRUE);
        return new JSP("permit/edit/cPEdit.jsp").addParameter("popup", "true");
    }

    public Resolution simpanCarianPihak() throws Exception {
        pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        idPermohonan = getContext().getRequest().getParameter("noFail");
        jenisBorang = getContext().getRequest().getParameter("jenisBorang");
        noPermit = getContext().getRequest().getParameter("noPermit");
        System.out.println("idPermohonan = " + idPermohonan + " " + jenisBorang + " " + noPermit);

        infoAudit = new InfoAudit();
        infoAudit.setDiKemaskiniOleh(pengguna);
        infoAudit.setTarikhKemaskini(new java.util.Date());

        if (idPermohonan != null) {

            List<Pihak> pihakList = pembangunanServ.findPihakByNoIc(noPengenalan);
            if (!pihakList.isEmpty()) {
                cariPihak = pihakList.get(0);
            }

            if (cariPihak != null) {
                cariPihak.setNama(nama);
                cariPihak.setAlamat1(alamat1);
                cariPihak.setAlamat2(alamat2);
                cariPihak.setAlamat3(alamat3);
                cariPihak.setAlamat4(alamat4);
                cariPihak.setPoskod(poskod);
                if (negeri != null) {
                    cariPihak.setNegeri(kodNegeriDAO.findById(negeri));
                }
                cariPihak.setInfoAudit(infoAudit);
                pembangunanServ.simpanPihak(cariPihak);
            }

            Permit perm = pembangunanServ.findPerrmitByIdMohonAndNoPermit(idPermohonan, noPermit);
            if (perm != null) {
                System.out.println("--permSavePihak--");
                if (cariPihak != null) {
                    perm.setPihak(cariPihak);
                }
                perm.setInfoAudit(infoAudit);
                pembangunanServ.simpanPermit(perm);
            }
        }
        getContext().getRequest().setAttribute("find", Boolean.TRUE);
        getContext().getRequest().setAttribute("found", Boolean.TRUE);
        check();
        addSimpleMessage("Maklumat Pemegang Permit Telah Berjaya Disimpan");
        return new JSP("permit/edit/cPEdit.jsp").addParameter("popup", "true");
    }
    //END CARIAN PIHAK

    //START UPLOAD PELAN
    public Resolution muatnaik() {
        idPermohonan = getContext().getRequest().getParameter("noFail");
        noPermit = getContext().getRequest().getParameter("noPermit");
        System.out.println("--idPermohonan-- " + idPermohonan);
        jenisBorang = getContext().getRequest().getParameter("jenisBorang");

        HakmilikPermohonan hp = pembangunanServ.findByIdPermohonanAndPermitForCarianPermit(idPermohonan, noPermit);
        if (hp != null) {
            LOG.info("###### saving . . .");
            KodNegeri kn = kodNegeriDAO.findById(conf.getKodNegeri());
            PelanGISPK pgPK = new PelanGISPK();
            pgPK.setKodDaerah(hp.getCawangan().getDaerah());
            pgPK.setKodMukim(hp.getBandarPekanMukimBaru().getbandarPekanMukim());
            pgPK.setKodSeksyen("000");
            pgPK.setPermohonan(hp.getPermohonan());
            if (hp.getNoLot() != null) {
                pgPK.setNoLot(hp.getNoLot());
            } else {
                pgPK.setNoLot("0");
            }
            pgPK.setKodNegeri(kn);

            if (hp.getPermit() != null) {
                pgPK.setNoPermit(hp.getPermit());
            } else {
                pgPK.setNoPermit(noPermit);
            }

            PelanGIS pg = new PelanGIS();
            pg.setPelanGISPK(pgPK);

            String jenisPelan = "";
            if (jenisBorang.equalsIgnoreCase("A")) {
                jenisPelan = "L1";
            } else if (jenisBorang.equalsIgnoreCase("B")) {
                jenisPelan = "L2";
            } else if (jenisBorang.equalsIgnoreCase("C")) {
                jenisPelan = "P1";
            } else if (jenisBorang.equalsIgnoreCase("D")) {
                jenisPelan = "P2";
            }
            pg.setJenisPelan(jenisPelan);

            pg.setTarikhKemaskini(new java.util.Date());

            System.out.println("--1--" + File.separator + pg.getPelanGISPK().getKodDaerah().getKod());
            System.out.println("--2--" + File.separator + pg.getPelanGISPK().getKodMukim());
            System.out.println("--3--" + File.separator + pg.getPelanGISPK().getKodSeksyen());

            if (fileToBeUpload != null) {
                String fileName = fileToBeUpload.getFileName();
                System.out.println("--4--" + File.separator + fileName.toLowerCase());
            }

            String tif = File.separator + pg.getJenisPelan()
                    + File.separator + pg.getPelanGISPK().getKodDaerah().getKod()
                    + File.separator + pg.getPelanGISPK().getKodMukim()
                    + File.separator + fileToBeUpload.getFileName().toLowerCase();

            pg.setPelanTif(tif);
            pembangunanServ.simpanPelanGIS(pg);

            String loc = conf.getPelanPath() + "PMT"
                    + File.separator + pg.getJenisPelan()
                    + File.separator + pg.getPelanGISPK().getKodDaerah().getKod()
                    + File.separator + pg.getPelanGISPK().getKodMukim();
            System.out.println("--loc--" + loc);

            try {
                FileUtil fileUtil = new FileUtil(loc);
                //FileUtil fileUtil = new FileUtil("C:\\");
                System.out.print("--OUTPUT--" + fileToBeUpload.getFileName().toLowerCase());
                fileUtil.saveFile(fileToBeUpload.getFileName().toLowerCase(), fileToBeUpload.getInputStream());


            } catch (Exception ex) {
                Logger.getLogger(PermitActionBean.class).error(ex);
                System.out.println(ex);
            }
        }

        String link = "";
        if (jenisBorang.equalsIgnoreCase("A")) {
            link = "/WEB-INF/jsp/permit/edit/4aeEdit.jsp";
        } else if (jenisBorang.equalsIgnoreCase("B")) {
            link = "/WEB-INF/jsp/permit/edit/4beEdit.jsp";
        } else if (jenisBorang.equalsIgnoreCase("C")) {
            link = "/WEB-INF/jsp/permit/edit/4ceEdit.jsp";
        } else {
            link = "/WEB-INF/jsp/permit/edit/4deEdit.jsp";
        }

        check();
        addSimpleMessage("Pelan Telah Berjaya Dimuat Naik");
        return new ForwardResolution(link);
    }

    public Resolution deletePelan() throws Exception {
        LOG.info("###### delete PELAN");
        idPermohonan = getContext().getRequest().getParameter("noFail");
        noPermit = getContext().getRequest().getParameter("noPermit");

        PelanGIS pgNoPermit = pembangunanServ.findPelanByIdPermohonanAndNoPermit(idPermohonan, noPermit);
        if (pgNoPermit != null) {
            pembangunanServ.deletePelan(pgNoPermit);
        } else {
            PelanGIS pg = pembangunanServ.findPelanByIdPermohonan(idPermohonan);
            if (pg != null) {
                pembangunanServ.deletePelan(pg);
            }
        }

        String link = "";
        if (jenisBorang.equalsIgnoreCase("A")) {
            link = "/WEB-INF/jsp/permit/edit/4aeEdit.jsp";
        } else if (jenisBorang.equalsIgnoreCase("B")) {
            link = "/WEB-INF/jsp/permit/edit/4beEdit.jsp";
        } else if (jenisBorang.equalsIgnoreCase("C")) {
            link = "/WEB-INF/jsp/permit/edit/4ceEdit.jsp";
        } else {
            link = "/WEB-INF/jsp/permit/edit/4deEdit.jsp";
        }

        addSimpleMessage("Pelan Bagi " + idPermohonan + " Telah Berjaya Dihapuskan");
        check();
        return new ForwardResolution(link);
    }
    // END UPLOAD PELAN

    // START RECORD LPS
    public Resolution simpanRekod() throws Exception {
        pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        idPermohonan = getContext().getRequest().getParameter("noFail");
        noPermit = getContext().getRequest().getParameter("noPermit");
        LOG.info("###### id Pengguna : " + pengguna + " IdPermohonan = " + idPermohonan + " Id Permit = " + noPermit);
        infoAudit = new InfoAudit();

        PermitLPSRekod plpsRekod1stTimes = pembangunanServ.findLPSRekodFor1stTimes(idPermohonan, noPermit);
        if (plpsRekod1stTimes != null) {
            plpsRekod1stTimes.setBayaran(b1);
            plpsRekod1stTimes.setResit(n1);
            plpsRekod1stTimes.setTahun(t1);
            infoAudit.setDiKemaskiniOleh(pengguna);
            infoAudit.setTarikhKemaskini(new java.util.Date());
            plpsRekod1stTimes.setInfoAudit(infoAudit);
            pembangunanServ.simpanPermitLPSRekod(plpsRekod1stTimes);
        } else {
            PermitLPSRekod plpsNewRekod1stTimes = new PermitLPSRekod();
            plpsNewRekod1stTimes.setPermohonan(permohonanDAO.findById(idPermohonan));
            plpsNewRekod1stTimes.setNoPermit(noPermit);
            plpsNewRekod1stTimes.setFlag("1");
            plpsNewRekod1stTimes.setBayaran(b1);
            plpsNewRekod1stTimes.setResit(n1);
            plpsNewRekod1stTimes.setTahun(t1);
            infoAudit.setDimasukOleh(pengguna);
            infoAudit.setTarikhMasuk(new java.util.Date());
            plpsNewRekod1stTimes.setInfoAudit(infoAudit);
            pembangunanServ.simpanPermitLPSRekod(plpsNewRekod1stTimes);
        }

        PermitLPSRekod plpsRekod2ndTimes = pembangunanServ.findLPSRekodFor2ndTimes(idPermohonan, noPermit);
        if (plpsRekod2ndTimes != null) {
            plpsRekod2ndTimes.setBayaran(b2);
            plpsRekod2ndTimes.setResit(n2);
            plpsRekod2ndTimes.setTahun(t2);
            infoAudit.setDiKemaskiniOleh(pengguna);
            infoAudit.setTarikhKemaskini(new java.util.Date());
            plpsRekod2ndTimes.setInfoAudit(infoAudit);
            pembangunanServ.simpanPermitLPSRekod(plpsRekod2ndTimes);
        } else {
            PermitLPSRekod plpsNewRekod2ndTimes = new PermitLPSRekod();
            plpsNewRekod2ndTimes.setPermohonan(permohonanDAO.findById(idPermohonan));
            plpsNewRekod2ndTimes.setNoPermit(noPermit);
            plpsNewRekod2ndTimes.setFlag("2");
            plpsNewRekod2ndTimes.setBayaran(b2);
            plpsNewRekod2ndTimes.setResit(n2);
            plpsNewRekod2ndTimes.setTahun(t2);
            infoAudit.setDimasukOleh(pengguna);
            infoAudit.setTarikhMasuk(new java.util.Date());
            plpsNewRekod2ndTimes.setInfoAudit(infoAudit);
            pembangunanServ.simpanPermitLPSRekod(plpsNewRekod2ndTimes);
        }

        PermitLPSRekod plpsRekod3rdTimes = pembangunanServ.findLPSRekodFor3rdTimes(idPermohonan, noPermit);
        if (plpsRekod3rdTimes != null) {
            plpsRekod3rdTimes.setBayaran(b3);
            plpsRekod3rdTimes.setResit(n3);
            plpsRekod3rdTimes.setTahun(t3);
            infoAudit.setDiKemaskiniOleh(pengguna);
            infoAudit.setTarikhKemaskini(new java.util.Date());
            plpsRekod3rdTimes.setInfoAudit(infoAudit);
            pembangunanServ.simpanPermitLPSRekod(plpsRekod3rdTimes);
        } else {
            PermitLPSRekod plpsNewRekod3rdTimes = new PermitLPSRekod();
            plpsNewRekod3rdTimes.setPermohonan(permohonanDAO.findById(idPermohonan));
            plpsNewRekod3rdTimes.setNoPermit(noPermit);
            plpsNewRekod3rdTimes.setFlag("3");
            plpsNewRekod3rdTimes.setBayaran(b3);
            plpsNewRekod3rdTimes.setResit(n3);
            plpsNewRekod3rdTimes.setTahun(t3);
            infoAudit.setDimasukOleh(pengguna);
            infoAudit.setTarikhMasuk(new java.util.Date());
            plpsNewRekod3rdTimes.setInfoAudit(infoAudit);
            pembangunanServ.simpanPermitLPSRekod(plpsNewRekod3rdTimes);
        }

        addSimpleMessage("Rekod Pembaharuan Bagi " + noFail + " Telah Berjaya Disimpan");
        check();
        return new ForwardResolution("/WEB-INF/jsp/permit/edit/4aeEdit.jsp");
    }
    // END RECORD LPS

    public Permohonan getPermohonan() {
        return permohonan;
    }

    public void setPermohonan(Permohonan permohonan) {
        this.permohonan = permohonan;
    }

    public SimpleDateFormat getSdf() {
        return sdf;
    }

    public void setSdf(SimpleDateFormat sdf) {
        this.sdf = sdf;
    }

    public String getRadio1() {
        return radio1;
    }

    public void setRadio1(String radio1) {
        this.radio1 = radio1;
    }

    public String getKodPermit() {
        return kodPermit;
    }

    public void setKodPermit(String kodPermit) {
        this.kodPermit = kodPermit;
    }

    public String getNoPermit() {
        return noPermit;
    }

    public void setNoPermit(String noPermit) {
        this.noPermit = noPermit;
    }

    public String getDaerah() {
        return daerah;
    }

    public void setDaerah(String daerah) {
        this.daerah = daerah;
    }

    public String getBayaran() {
        return bayaran;
    }

    public void setBayaran(String bayaran) {
        this.bayaran = bayaran;
    }

    public String getNoResit() {
        return noResit;
    }

    public void setNoResit(String noResit) {
        this.noResit = noResit;
    }

    public String getIdMohon() {
        return idMohon;
    }

    public void setIdMohon(String idMohon) {
        this.idMohon = idMohon;
    }

    public String getIdPermohonan() {
        return idPermohonan;
    }

    public void setIdPermohonan(String idPermohonan) {
        this.idPermohonan = idPermohonan;
    }

    public String getKodMilik() {
        return kodMilik;
    }

    public void setKodMilik(String kodMilik) {
        this.kodMilik = kodMilik;
    }

    public InfoAudit getInfoAudit() {
        return infoAudit;
    }

    public void setInfoAudit(InfoAudit infoAudit) {
        this.infoAudit = infoAudit;
    }

    public Date getTarikh() {
        return tarikh;
    }

    public void setTarikh(Date tarikh) {
        this.tarikh = tarikh;
    }

    public String getNoResitCarian() {
        return noResitCarian;
    }

    public void setNoResitCarian(String noResitCarian) {
        this.noResitCarian = noResitCarian;
    }

    public Transaksi getKt() {
        return kt;
    }

    public void setKt(Transaksi kt) {
        this.kt = kt;
    }

    public String getNoR() {
        return noR;
    }

    public void setNoR(String noR) {
        this.noR = noR;
    }

    public String getAma() {
        return ama;
    }

    public void setAma(String ama) {
        this.ama = ama;
    }

    public String getNoFail() {
        return noFail;
    }

    public void setNoFail(String noFail) {
        this.noFail = noFail;
    }

    public String getJenisBorang() {
        return jenisBorang;
    }

    public void setJenisBorang(String jenisBorang) {
        this.jenisBorang = jenisBorang;
    }

    public String getBandar() {
        return bandar;
    }

    public void setBandar(String bandar) {
        this.bandar = bandar;
    }

    public String getLuas() {
        return luas;
    }

    public void setLuas(String luas) {
        this.luas = luas;
    }

    public String getRadio2() {
        return radio2;
    }

    public void setRadio2(String radio2) {
        this.radio2 = radio2;
    }

    public String getKodLot() {
        return kodLot;
    }

    public void setKodLot(String kodLot) {
        this.kodLot = kodLot;
    }

    public String getNoLot() {
        return noLot;
    }

    public void setNoLot(String noLot) {
        this.noLot = noLot;
    }

    public String getTanah() {
        return tanah;
    }

    public void setTanah(String tanah) {
        this.tanah = tanah;
    }

    public Pihak getCariPihak() {
        return cariPihak;
    }

    public void setCariPihak(Pihak cariPihak) {
        this.cariPihak = cariPihak;
    }

    public String getNoPengenalan() {
        return noPengenalan;
    }

    public void setNoPengenalan(String noPengenalan) {
        this.noPengenalan = noPengenalan;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
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

    public String getSyaratTanah() {
        return syaratTanah;
    }

    public void setSyaratTanah(String syaratTanah) {
        this.syaratTanah = syaratTanah;
    }

    public String getJenisLuas() {
        return jenisLuas;
    }

    public void setJenisLuas(String jenisLuas) {
        this.jenisLuas = jenisLuas;
    }

    public Date getTarikhMula() {
        return tarikhMula;
    }

    public void setTarikhMula(Date tarikhMula) {
        this.tarikhMula = tarikhMula;
    }

    public Date getTarikhtamat() {
        return tarikhtamat;
    }

    public void setTarikhtamat(Date tarikhtamat) {
        this.tarikhtamat = tarikhtamat;
    }

    public String getJenisStruktur() {
        return jenisStruktur;
    }

    public void setJenisStruktur(String jenisStruktur) {
        this.jenisStruktur = jenisStruktur;
    }

    public String getLokasi() {
        return lokasi;
    }

    public void setLokasi(String lokasi) {
        this.lokasi = lokasi;
    }

    public String getTambah() {
        return tambah;
    }

    public void setTambah(String tambah) {
        this.tambah = tambah;
    }

    public String getLuasStruktur() {
        return luasStruktur;
    }

    public void setLuasStruktur(String luasStruktur) {
        this.luasStruktur = luasStruktur;
    }

    public String getJenisLuasStruktur() {
        return jenisLuasStruktur;
    }

    public void setJenisLuasStruktur(String jenisLuasStruktur) {
        this.jenisLuasStruktur = jenisLuasStruktur;
    }

    public String getReportName() {
        return reportName;
    }

    public void setReportName(String reportName) {
        this.reportName = reportName;
    }

    public Date getTarikhPermit() {
        return tarikhPermit;
    }

    public void setTarikhPermit(Date tarikhPermit) {
        this.tarikhPermit = tarikhPermit;
    }

    public String getMaksud() {
        return maksud;
    }

    public void setMaksud(String maksud) {
        this.maksud = maksud;
    }

    public List<PelanGIS> getListPelanGIS() {
        return listPelanGIS;
    }

    public void setListPelanGIS(List<PelanGIS> listPelanGIS) {
        this.listPelanGIS = listPelanGIS;
    }

    public String getIdMohonPgis() {
        return idMohonPgis;
    }

    public void setIdMohonPgis(String idMohonPgis) {
        this.idMohonPgis = idMohonPgis;
    }

    public FileBean getFileToBeUpload() {
        return fileToBeUpload;
    }

    public void setFileToBeUpload(FileBean fileToBeUpload) {
        this.fileToBeUpload = fileToBeUpload;
    }

    public Configuration getConf() {
        return conf;
    }

    public void setConf(Configuration conf) {
        this.conf = conf;
    }

    public Provider<Session> getSessionProvider() {
        return sessionProvider;
    }

    public void setSessionProvider(Provider<Session> sessionProvider) {
        this.sessionProvider = sessionProvider;
    }

    public String getkPermit() {
        return kPermit;
    }

    public void setkPermit(String kPermit) {
        this.kPermit = kPermit;
    }

    public List<Pengguna> getListPp() {
        return listPp;
    }

    public void setListPp(List<Pengguna> listPp) {
        this.listPp = listPp;
    }

    public String getTandatangan() {
        return tandatangan;
    }

    public void setTandatangan(String tandatangan) {
        this.tandatangan = tandatangan;
    }

    public String getRoyalti() {
        return royalti;
    }

    public void setRoyalti(String royalti) {
        this.royalti = royalti;
    }

    public String getItemPermit() {
        return itemPermit;
    }

    public void setItemPermit(String itemPermit) {
        this.itemPermit = itemPermit;
    }

    public String getKntt() {
        return kntt;
    }

    public void setKntt(String kntt) {
        this.kntt = kntt;
    }

    public String getJenisUom() {
        return jenisUom;
    }

    public void setJenisUom(String jenisUom) {
        this.jenisUom = jenisUom;
    }

    public String getNamaKuantiti() {
        return namaKuantiti;
    }

    public void setNamaKuantiti(String namaKuantiti) {
        this.namaKuantiti = namaKuantiti;
    }

    public String getRoyaltiUOM() {
        return royaltiUOM;
    }

    public void setRoyaltiUOM(String royaltiUOM) {
        this.royaltiUOM = royaltiUOM;
    }

    public String getB1() {
        return b1;
    }

    public void setB1(String b1) {
        this.b1 = b1;
    }

    public String getB2() {
        return b2;
    }

    public void setB2(String b2) {
        this.b2 = b2;
    }

    public String getB3() {
        return b3;
    }

    public void setB3(String b3) {
        this.b3 = b3;
    }

    public String getN1() {
        return n1;
    }

    public void setN1(String n1) {
        this.n1 = n1;
    }

    public String getN2() {
        return n2;
    }

    public void setN2(String n2) {
        this.n2 = n2;
    }

    public String getN3() {
        return n3;
    }

    public void setN3(String n3) {
        this.n3 = n3;
    }

    public String getT1() {
        return t1;
    }

    public void setT1(String t1) {
        this.t1 = t1;
    }

    public String getT2() {
        return t2;
    }

    public void setT2(String t2) {
        this.t2 = t2;
    }

    public String getT3() {
        return t3;
    }

    public void setT3(String t3) {
        this.t3 = t3;
    }

    public String getKaedah() {
        return kaedah;
    }

    public void setKaedah(String kaedah) {
        this.kaedah = kaedah;
    }

    public String getRm() {
        return rm;
    }

    public void setRm(String rm) {
        this.rm = rm;
    }

    public String getBagi() {
        return bagi;
    }

    public void setBagi(String bagi) {
        this.bagi = bagi;
    }

    public String getKntt1() {
        return kntt1;
    }

    public void setKntt1(String kntt1) {
        this.kntt1 = kntt1;
    }

    public String getJenisUom1() {
        return jenisUom1;
    }

    public void setJenisUom1(String jenisUom1) {
        this.jenisUom1 = jenisUom1;
    }

    public String getTambah1() {
        return tambah1;
    }

    public void setTambah1(String tambah1) {
        this.tambah1 = tambah1;
    }

    public String getTempat() {
        return tempat;
    }

    public void setTempat(String tempat) {
        this.tempat = tempat;
    }

    public String getDaerahNama() {
        return daerahNama;
    }

    public void setDaerahNama(String daerahNama) {
        this.daerahNama = daerahNama;
    }

    public String getNegeriNama() {
        return negeriNama;
    }

    public void setNegeriNama(String negeriNama) {
        this.negeriNama = negeriNama;
    }

    public String getBandarNama() {
        return bandarNama;
    }

    public void setBandarNama(String bandarNama) {
        this.bandarNama = bandarNama;
    }

    public String getTanahNama() {
        return tanahNama;
    }

    public void setTanahNama(String tanahNama) {
        this.tanahNama = tanahNama;
    }

    public String getKodLotNama() {
        return kodLotNama;
    }

    public void setKodLotNama(String kodLotNama) {
        this.kodLotNama = kodLotNama;
    }

    public String getJenisUomNama() {
        return jenisUomNama;
    }

    public void setJenisUomNama(String jenisUomNama) {
        this.jenisUomNama = jenisUomNama;
    }

    public String getJenisUom1Nama() {
        return jenisUom1Nama;
    }

    public void setJenisUom1Nama(String jenisUom1Nama) {
        this.jenisUom1Nama = jenisUom1Nama;
    }

    public String getBagiNama() {
        return bagiNama;
    }

    public void setBagiNama(String bagiNama) {
        this.bagiNama = bagiNama;
    }

    public String getItemPermitNama() {
        return itemPermitNama;
    }

    public void setItemPermitNama(String itemPermitNama) {
        this.itemPermitNama = itemPermitNama;
    }

    public String getkPermitNama() {
        return kPermitNama;
    }

    public void setkPermitNama(String kPermitNama) {
        this.kPermitNama = kPermitNama;
    }

    public Pengguna getPengguna() {
        return pengguna;
    }

    public void setPengguna(Pengguna pengguna) {
        this.pengguna = pengguna;
    }

    public List<PelanGIS> getListPelanGISwNoPermit() {
        return listPelanGISwNoPermit;
    }

    public void setListPelanGISwNoPermit(List<PelanGIS> listPelanGISwNoPermit) {
        this.listPelanGISwNoPermit = listPelanGISwNoPermit;
    }

    public String getIdMohonPgiswPermit() {
        return idMohonPgiswPermit;
    }

    public void setIdMohonPgiswPermit(String idMohonPgiswPermit) {
        this.idMohonPgiswPermit = idMohonPgiswPermit;
    }

    public List<Permit> getListPermit() {
        return listPermit;
    }

    public void setListPermit(List<Permit> listPermit) {
        this.listPermit = listPermit;
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }
}
