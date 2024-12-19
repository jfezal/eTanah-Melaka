package etanah.view.daftar;

import able.stripes.JSP;
import etanah.view.kaunter.*;

import net.sourceforge.stripes.action.ForwardResolution;
import com.google.inject.Inject;
import etanah.dao.PermohonanDAO;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.service.common.FasaPermohonanService;
import etanah.service.common.PermohonanService;
import java.util.List;
import org.apache.log4j.Logger;
import etanah.model.WakilKuasa;
import etanah.model.WakilKuasaPemberi;
import etanah.model.WakilKuasaPenerima;
import etanah.service.daftar.PembetulanService;
import etanah.service.daftar.PendaftaranSuratKuasaService;
import etanah.view.etanahActionBeanContext;
import java.text.ParseException;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;
import oracle.bpel.services.workflow.WorkflowException;
import java.util.ArrayList;

/**
 *
 * @author wan.fairul mod by Fairul mod again by Aizuddin 30/11/12
 */
@UrlBinding("/daftar/pertanyaanSBSWSA")
public class UtilitiPerserahanSWSBSA extends PermohonanKaunter {

  private Permohonan permohonan;
  @Inject
  private PermohonanDAO permohonanDAO;
  @Inject
  FasaPermohonanService fService;
  @Inject
  PendaftaranSuratKuasaService suratkuasaService;
  @Inject
  PermohonanService permohonanService;
  List<WakilKuasa> wKuasa;
  @Inject
  PembetulanService pService;
  @Inject
  private etanah.Configuration conf;
  private List<WakilKuasa> carianListWakilKuasaByNama;
  private List<WakilKuasaPenerima> listWakilKuasaPenerima;
  private List<WakilKuasaPenerima> carianListWakilKuasaPenerima;
  private List<WakilKuasaPemberi> listWakilKuasaPemberi;
  private List<WakilKuasaPemberi> carianListWakilKuasaPemberi;
  private List<WakilKuasaPemberi> pemberiSA = new ArrayList<WakilKuasaPemberi>();
  private List<WakilKuasaPemberi> pemberiSB = new ArrayList<WakilKuasaPemberi>();
  private List<WakilKuasaPemberi> pemberiSW = new ArrayList<WakilKuasaPemberi>();
  private List<WakilKuasaPenerima> penerimaSA = new ArrayList<WakilKuasaPenerima>();
  private List<WakilKuasaPenerima> penerimaSB = new ArrayList<WakilKuasaPenerima>();
  private List<WakilKuasaPenerima> penerimaSW = new ArrayList<WakilKuasaPenerima>();
  private String kodUrusan;
  private static final Logger LOG = Logger.getLogger(UtilitiPerserahanSWSBSA.class);
  private static boolean isDebug = LOG.isDebugEnabled();
  private String idMohon;
  private String permohonanSA;
  private String permohonanSB;
  private String permohonanSW;
  private String status;
  private String statusWK;
  private WakilKuasa wkuasaSB;
  private String namaPenerima;
  private String namaPemberi;
  private String nama;

    @DefaultHandler
    public Resolution showForm() {
        return new ForwardResolution("/WEB-INF/jsp/daftar/main_swsbsa.jsp");
    }

    public Resolution checkPermohonan() {

        if (permohonanSA == null && permohonanSB == null && permohonanSW == null) {
            addSimpleError("Sila masukkan ID Permohonan/Perserahan");
            return new ForwardResolution("/WEB-INF/jsp/daftar/main_swsbsa.jsp");
        }       
        
        else if (permohonanSA != null && permohonanSB == null && permohonanSW == null) {
            if ((permohonanSA.contains("SW")) || (permohonanSA.contains("SB"))) {
                addSimpleError("Sila pastikan Id Permohonan yang dimasukkan adalah format Id Permohonan SA");
                return new ForwardResolution("/WEB-INF/jsp/daftar/main_swsbsa.jsp");
            }
            permohonanSA = validateIdMohon(permohonanSA,"SA");
            permohonan = permohonanDAO.findById(permohonanSA);

            if (permohonan == null) {
                addSimpleError("Permohonan " + permohonanSA + " tidak dijumpai.");
                return new ForwardResolution("/WEB-INF/jsp/daftar/main_swsbsa.jsp");
            } else {
                LOG.debug("id mohon SA lepas validate~~~~~" + permohonanSA);
                wKuasa = suratkuasaService.findWakilKuasaList(permohonanSA);
                wkuasaSB = suratkuasaService.findWakilKuasa(permohonanSA);
                for (WakilKuasa wk : wKuasa) {
                    statusWK = Character.toString(wk.getAktif());
                    LOG.info("Status Wakil Kuasa::" + statusWK);
                    listWakilKuasaPemberi = suratkuasaService.findWakilKuasaListPemberi(wk.getIdWakil());
                }
                listWakilKuasaPenerima = pService.findWakilKuasaPList(permohonanSA);
                kodUrusan = permohonan.getKodUrusan().getKod();
                status = permohonan.getStatus();
            }   
        }
        
        else if (permohonanSA == null && permohonanSB != null && permohonanSW == null) {
            if ((permohonanSB.contains("SA")) || (permohonanSB.contains("SW"))) {
                addSimpleError("Sila pastikan Id Permohonan yang dimasukkan adalah format Id Permohonan SB");
                return new ForwardResolution("/WEB-INF/jsp/daftar/main_swsbsa.jsp");
            }
            permohonanSB = validateIdMohon(permohonanSB,"SB");
            permohonan = permohonanDAO.findById(permohonanSB);

            if (permohonan == null) {
                addSimpleError("Permohonan " + permohonanSB + " tidak dijumpai.");
                return new ForwardResolution("/WEB-INF/jsp/daftar/main_swsbsa.jsp");
            } else {
                LOG.debug("id mohon SA lepas validate~~~~~" + permohonanSB);
                wKuasa = suratkuasaService.findWakilKuasaList(permohonanSB);
                wkuasaSB = suratkuasaService.findWakilKuasa(permohonanSB);
                for (WakilKuasa wk : wKuasa) {
                    statusWK = Character.toString(wk.getAktif());
                    LOG.info("Status Wakil Kuasa::" + statusWK);
                    listWakilKuasaPemberi = suratkuasaService.findWakilKuasaListPemberi(wk.getIdWakil());
                }
                listWakilKuasaPenerima = pService.findWakilKuasaPList(permohonanSB);
                kodUrusan = permohonan.getKodUrusan().getKod();
                status = permohonan.getStatus();
            }  
        } 
        
        else if (permohonanSA == null && permohonanSB == null && permohonanSW != null) {
            if ((permohonanSW.contains("SA")) || (permohonanSW.contains("SB"))) {
                addSimpleError("Sila pastikan Id Permohonan yang dimasukkan adalah format Id Permohonan SW");
                return new ForwardResolution("/WEB-INF/jsp/daftar/main_swsbsa.jsp");
            }
            permohonanSW = validateIdMohon(permohonanSW,"SW");
            permohonan = permohonanDAO.findById(permohonanSW);

            if (permohonan == null) {
                addSimpleError("Permohonan " + permohonanSW + " tidak dijumpai.");
                return new ForwardResolution("/WEB-INF/jsp/daftar/main_swsbsa.jsp");
            } else {
                LOG.debug("id mohon SA lepas validate~~~~~" + permohonanSW);
                wKuasa = suratkuasaService.findWakilKuasaList(permohonanSW);
                wkuasaSB = suratkuasaService.findWakilKuasa(permohonanSW);
                for (WakilKuasa wk : wKuasa) {
                    statusWK = Character.toString(wk.getAktif());
                    LOG.info("Status Wakil Kuasa::" + statusWK);
                    listWakilKuasaPemberi = suratkuasaService.findWakilKuasaListPemberi(wk.getIdWakil());
                }
                listWakilKuasaPenerima = pService.findWakilKuasaPList(permohonanSW);
                kodUrusan = permohonan.getKodUrusan().getKod();
                status = permohonan.getStatus();
            } 
        }
        return new ForwardResolution("/WEB-INF/jsp/daftar/perserahan_swsbsa.jsp");      
    }
    
    public Resolution searchByName() {
        etanahActionBeanContext ctx = (etanahActionBeanContext) getContext();
        Pengguna pengguna = ctx.getUser();
        String kodCaw = pengguna.getKodCawangan().getKod();
        
        LOG.debug("kodCaw --->" + kodCaw);
        
        if (nama == null) {
            addSimpleError("Sila masukkan nama pemberi/penerima yang hendak dicari");
            return new ForwardResolution("/WEB-INF/jsp/daftar/main_swsbsa.jsp");

        }
        carianListWakilKuasaPemberi = suratkuasaService.findWakilKuasaListPemberiUsingNameKodCaw(nama,kodCaw);
        carianListWakilKuasaPenerima = suratkuasaService.findWakilKuasaPListUsingNameKodCaw(nama,kodCaw);

        for (WakilKuasaPemberi wkberi : carianListWakilKuasaPemberi) {
            if (wkberi.getWakilKuasa().getIdWakil().contains("SA")) {
                pemberiSA.add(wkberi);
            }
            if (wkberi.getWakilKuasa().getIdWakil().contains("SB")) {
                pemberiSB.add(wkberi);
            }
            if (wkberi.getWakilKuasa().getIdWakil().contains("SW")) {
                pemberiSW.add(wkberi);
            }
        }

        LOG.info("pemberiSA" + pemberiSA.size());
        LOG.info("pemberiSB" + pemberiSB.size());
        LOG.info("pemberiSW" + pemberiSW.size());

        for (WakilKuasaPenerima wkterima : carianListWakilKuasaPenerima) {
            if (wkterima.getWakilKuasa().getIdWakil().contains("SA")) {
                penerimaSA.add(wkterima);
            }
            if (wkterima.getWakilKuasa().getIdWakil().contains("SB")) {
                penerimaSB.add(wkterima);
            }
            if (wkterima.getWakilKuasa().getIdWakil().contains("SW")) {
                penerimaSW.add(wkterima);
            }
         }

        LOG.info("penerimaSA" + penerimaSA.size());
        LOG.info("penerimaSB" + penerimaSB.size());
        LOG.info("penerimaSW" + penerimaSW.size());

        addSimpleError("Sila masukkan nama pemberi/penerima yang hendak dicari");
        return new ForwardResolution("/WEB-INF/jsp/daftar/show_sbsasw_by_name.jsp");
    }
//    public Resolution checkPermohonan() {
//        if (permohonan == null) {
//            addSimpleError("Sila masukkan ID Permohonan/Perserahan");
//            return new ForwardResolution("/WEB-INF/jsp/daftar/main_swsbsa.jsp");yyy
//        }
//        String idPermohonan = permohonan.getIdPermohonan();
//        if (idPermohonan == null) {
//            addSimpleError("Sila masukkan ID Permohonan/Perserahan");
//            return new ForwardResolution("/WEB-INF/jsp/daftar/main_swsbsa.jsp");
//        }
//
//        permohonan = permohonanDAO.findById(idPermohonan);
//        if (permohonan == null) {
//            System.out.print("Permohonan " + idPermohonan + " tidak dijumpai.");
//            addSimpleError("Permohonan " + idPermohonan + " tidak dijumpai.");
//            return new ForwardResolution("/WEB-INF/jsp/daftar/main_swsbsa.jsp");
//        } else {
//            wKuasa = suratkuasaService.findWakilKuasaList(idPermohonan);
//            wkuasaSB = suratkuasaService.findWakilKuasa(idPermohonan);
//            for (WakilKuasa wk : wKuasa) {
//                statusWK = Character.toString(wk.getAktif());
//                LOG.info("Status Wakil Kuasa::" + statusWK);
//                listWakilKuasaPemberi = suratkuasaService.findWakilKuasaListPemberi(wk.getIdWakil());
//            }
//            listWakilKuasaPenerima = pService.findWakilKuasaPList(idPermohonan);
//            kodUrusan = permohonan.getKodUrusan().getKod();
//            status = permohonan.getStatus();
//
//            if ("SW".equals(kodUrusan) || "SA".equals(kodUrusan) || "SB".equals(kodUrusan)) {
////                if ("TK".equals(status)){
////                    addSimpleError("Permohonan telah dibatalkan");                    
////                }
//                return new ForwardResolution("/WEB-INF/jsp/daftar/perserahan_swsbsa.jsp");
//            } else {
//                addSimpleError(" " + idPermohonan + " Bukan Id Permohonan Suratkuasa");
//                return new ForwardResolution("/WEB-INF/jsp/daftar/main_swsbsa.jsp");
//            }
////            return new ForwardResolution("/WEB-INF/jsp/daftar/perserahan_swsbsa.jsp");
//        }
//
//    }

    //added by m.fairul
    public Resolution paparOnly() {
//    idMohon = (String) getContext().getRequest().getSession().getAttribute("idMohon");
        LOG.info("trace id mohon" + idMohon);
        if (idMohon != null) {
            permohonan = permohonanDAO.findById(idMohon);
            wKuasa = suratkuasaService.findWakilKuasaList(idMohon);
            for (WakilKuasa wk : wKuasa) {
                listWakilKuasaPemberi = suratkuasaService.findWakilKuasaListPemberi(wk.getIdWakil());
            }
            listWakilKuasaPenerima = pService.findWakilKuasaPList(idMohon);
            wkuasaSB = suratkuasaService.findWakilKuasa(idMohon);
        }
        return new JSP("daftar/perserahan_swsbsa.jsp").addParameter("popup", "true");
    }
    
    public Resolution paparOnly2() {
//    idMohon = (String) getContext().getRequest().getSession().getAttribute("idMohon");
        LOG.info("trace id mohon" + idMohon);
        if (idMohon != null) {
            permohonan = permohonanDAO.findById(idMohon);
            wKuasa = suratkuasaService.findWakilKuasaList(idMohon);
            for (WakilKuasa wk : wKuasa) {
                listWakilKuasaPemberi = suratkuasaService.findWakilKuasaListPemberi(wk.getIdWakil());
            }
            listWakilKuasaPenerima = pService.findWakilKuasaPList(idMohon);
            wkuasaSB = suratkuasaService.findWakilKuasa(idMohon);
        }
        return new JSP("daftar/perserahan_swsbsa_2.jsp").addParameter("popup", "true");
    }
    
    public Resolution back() {
        return new ForwardResolution("/WEB-INF/jsp/daftar/main_swsbsa.jsp");
    }

    public String validateIdMohon(String idMohon,String kodUrusan) {
        etanahActionBeanContext ctx = (etanahActionBeanContext) getContext();
        Pengguna pengguna = ctx.getUser();

        if ("04".equals(conf.getProperty("kodNegeri"))) {
            String a = "04";
            String b = pengguna.getKodCawangan().getKod();
            String c = a.concat(b);

            if ((idMohon.contains("SW")) || (idMohon.contains("SA")) || (idMohon.contains("SB")) || (!idMohon.contains("/"))) {
                LOG.debug("ikut format etanah--->" + idMohon);
            } else {
                String[] SplitSlash = idMohon.split("/");
                String SptbID = SplitSlash[0];
                String SptbYear = SplitSlash[1];

                while (SptbID.length() < 6) {
                    SptbID = "0" + SptbID;
                }

                LOG.debug("zeropad------>" + SptbID);
                String convertedSPTBid = c.concat(kodUrusan).concat(SptbYear).concat(SptbID);
                LOG.debug("x ikut format,so akan diconvert kepada-->" + convertedSPTBid);
                idMohon = convertedSPTBid;
            }

            return idMohon;
        } else {
            return idMohon;
        }
    }
    
    public Resolution caripenerima() throws WorkflowException, ParseException {
   
            permohonanSW = validateIdMohon(permohonanSW,"SW");
            permohonan = permohonanDAO.findById(permohonanSW);
            namaPenerima = getContext().getRequest().getParameter("namaPenerima");

            if (permohonan == null) {
                addSimpleError("Permohonan " + permohonanSW + " tidak dijumpai.");
                return new ForwardResolution("/WEB-INF/jsp/daftar/main_swsbsa.jsp");
            } else {
                LOG.debug("id mohon SA lepas validate~~~~~" + permohonanSW);
                wKuasa = suratkuasaService.findWakilKuasaList(permohonanSW);
                wkuasaSB = suratkuasaService.findWakilKuasa(permohonanSW);
                for (WakilKuasa wk : wKuasa) {
                    statusWK = Character.toString(wk.getAktif());
                    LOG.info("Status Wakil Kuasa::" + statusWK);
                    listWakilKuasaPemberi = suratkuasaService.findWakilKuasaListPemberi(wk.getIdWakil());
                }
                listWakilKuasaPenerima = pService.findWakilKuasaPList(permohonanSW);
                carianListWakilKuasaPenerima = pService.findWakilKuasaPListUsingName(permohonanSW,namaPenerima);
                kodUrusan = permohonan.getKodUrusan().getKod();
                status = permohonan.getStatus();
            } 
        
        return new ForwardResolution("/WEB-INF/jsp/daftar/perserahan_swsbsa.jsp");   
    }
    
    public Resolution caripemberi() throws WorkflowException, ParseException {
   
            permohonanSW = validateIdMohon(permohonanSW,"SW");
            permohonan = permohonanDAO.findById(permohonanSW);
            namaPemberi = getContext().getRequest().getParameter("namaPemberi");

            if (permohonan == null) {
                addSimpleError("Permohonan " + permohonanSW + " tidak dijumpai.");
                return new ForwardResolution("/WEB-INF/jsp/daftar/main_swsbsa.jsp");
            } else {
                LOG.debug("id mohon SA lepas validate~~~~~" + permohonanSW);
                wKuasa = suratkuasaService.findWakilKuasaList(permohonanSW);
                wkuasaSB = suratkuasaService.findWakilKuasa(permohonanSW);
                for (WakilKuasa wk : wKuasa) {
                    statusWK = Character.toString(wk.getAktif());
                    LOG.info("Status Wakil Kuasa::" + statusWK);
                    listWakilKuasaPemberi = suratkuasaService.findWakilKuasaListPemberi(wk.getIdWakil());
                    carianListWakilKuasaPemberi = suratkuasaService.findWakilKuasaListPemberiUsingName(wk.getIdWakil(),namaPemberi);
                }
                listWakilKuasaPenerima = pService.findWakilKuasaPList(permohonanSW);
                kodUrusan = permohonan.getKodUrusan().getKod();
                status = permohonan.getStatus();
            } 
        
        return new ForwardResolution("/WEB-INF/jsp/daftar/perserahan_swsbsa.jsp");   
    }

    public String getIdMohon() {
        return idMohon;
    }

    public void setIdMohon(String idMohon) {
        this.idMohon = idMohon;
    }

    public String getStatusWK() {
        return statusWK;
    }

    public void setStatusWK(String statusWK) {
        this.statusWK = statusWK;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<WakilKuasaPemberi> getListWakilKuasaPemberi() {
        return listWakilKuasaPemberi;
    }

    public void setListWakilKuasaPemberi(List<WakilKuasaPemberi> listWakilKuasaPemberi) {
        this.listWakilKuasaPemberi = listWakilKuasaPemberi;
    }

    public List<WakilKuasa> getwKuasa() {
        return wKuasa;
    }

    public void setwKuasa(List<WakilKuasa> wKuasa) {
        this.wKuasa = wKuasa;
    }

    public List<WakilKuasaPenerima> getListWakilKuasaPenerima() {
        return listWakilKuasaPenerima;
    }

    public void setListWakilKuasaPenerima(List<WakilKuasaPenerima> listWakilKuasaPenerima) {
        this.listWakilKuasaPenerima = listWakilKuasaPenerima;
    }

    public String getKodUrusan() {
        return kodUrusan;
    }

    public void setKodUrusan(String kodUrusan) {
        this.kodUrusan = kodUrusan;
    }

    public void setPermohonan(Permohonan p) {
        this.permohonan = p;
    }

    public Permohonan getPermohonan() {
        return permohonan;
    }

    public WakilKuasa getWkuasaSB() {
        return wkuasaSB;
    }

    public void setWkuasaSB(WakilKuasa wkuasaSB) {
        this.wkuasaSB = wkuasaSB;
    }

    public String getPermohonanSA() {
        return permohonanSA;
    }

    public void setPermohonanSA(String permohonanSA) {
        this.permohonanSA = permohonanSA;
    }

    public String getPermohonanSB() {
        return permohonanSB;
    }

    public void setPermohonanSB(String permohonanSB) {
        this.permohonanSB = permohonanSB;
    }

    public String getPermohonanSW() {
        return permohonanSW;
    }

    public void setPermohonanSW(String permohonanSW) {
        this.permohonanSW = permohonanSW;
    }

    public List<WakilKuasaPenerima> getCarianListWakilKuasaPenerima() {
        return carianListWakilKuasaPenerima;
    }

    public void setCarianListWakilKuasaPenerima(List<WakilKuasaPenerima> carianListWakilKuasaPenerima) {
        this.carianListWakilKuasaPenerima = carianListWakilKuasaPenerima;
    }

    public String getNamaPenerima() {
        return namaPenerima;
    }

    public void setNamaPenerima(String namaPenerima) {
        this.namaPenerima = namaPenerima;
    } 

    public String getNamaPemberi() {
        return namaPemberi;
    }

    public void setNamaPemberi(String namaPemberi) {
        this.namaPemberi = namaPemberi;
    }  

    public List<WakilKuasaPemberi> getCarianListWakilKuasaPemberi() {
        return carianListWakilKuasaPemberi;
    }

    public void setCarianListWakilKuasaPemberi(List<WakilKuasaPemberi> carianListWakilKuasaPemberi) {
        this.carianListWakilKuasaPemberi = carianListWakilKuasaPemberi;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public List<WakilKuasaPemberi> getPemberiSA() {
        return pemberiSA;
    }

    public void setPemberiSA(List<WakilKuasaPemberi> pemberiSA) {
        this.pemberiSA = pemberiSA;
    }

    public List<WakilKuasaPemberi> getPemberiSB() {
        return pemberiSB;
    }

    public void setPemberiSB(List<WakilKuasaPemberi> pemberiSB) {
        this.pemberiSB = pemberiSB;
    }

    public List<WakilKuasaPemberi> getPemberiSW() {
        return pemberiSW;
    }

    public void setPemberiSW(List<WakilKuasaPemberi> pemberiSW) {
        this.pemberiSW = pemberiSW;
    }

    public List<WakilKuasaPenerima> getPenerimaSA() {
        return penerimaSA;
    }

    public void setPenerimaSA(List<WakilKuasaPenerima> penerimaSA) {
        this.penerimaSA = penerimaSA;
    }

    public List<WakilKuasaPenerima> getPenerimaSB() {
        return penerimaSB;
    }

    public void setPenerimaSB(List<WakilKuasaPenerima> penerimaSB) {
        this.penerimaSB = penerimaSB;
    }

    public List<WakilKuasaPenerima> getPenerimaSW() {
        return penerimaSW;
    }

    public void setPenerimaSW(List<WakilKuasaPenerima> penerimaSW) {
        this.penerimaSW = penerimaSW;
    }

    public List<WakilKuasa> getCarianListWakilKuasaByNama() {
        return carianListWakilKuasaByNama;
    }

    public void setCarianListWakilKuasaByNama(List<WakilKuasa> carianListWakilKuasaByNama) {
        this.carianListWakilKuasaByNama = carianListWakilKuasaByNama;
    }

}
