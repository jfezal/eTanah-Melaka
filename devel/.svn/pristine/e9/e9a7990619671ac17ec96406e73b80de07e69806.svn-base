package etanah.view.daftar;

import able.stripes.JSP;
import etanah.view.kaunter.*;
import java.util.List;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import com.google.inject.Inject;
import etanah.dao.PermohonanDAO;
import etanah.manager.TabManager;
import etanah.model.FasaPermohonan;
import etanah.model.Hakmilik;
import etanah.model.HakmilikPermohonan;
import etanah.model.HakmilikPihakBerkepentingan;
import etanah.service.common.HakmilikPermohonanService;
import etanah.model.HakmilikSebelum;
import etanah.model.HakmilikSebelumPermohonan;
import etanah.model.HakmilikUrusan;
import etanah.model.Permohonan;
import etanah.model.PermohonanRujukanLuar;
import etanah.model.Pihak;
import etanah.service.RegService;
import etanah.service.common.HakmilikUrusanService;
import etanah.service.common.PermohonanRujukanLuarService;
import etanah.service.common.PermohonanService;
import etanah.service.common.PihakService;
import etanah.service.daftar.PembetulanService;
import oracle.bpel.services.workflow.task.model.Task;
import etanah.workflow.WorkFlowService;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import oracle.bpel.services.workflow.WorkflowException;
import org.apache.log4j.Logger;
import etanah.model.CarianHakmilik;
import etanah.dao.CarianHakmilikDAO;
import etanah.model.PermohonanCarian;
import etanah.dao.PermohonanCarianDAO;
import etanah.dao.HakmilikDAO;
import etanah.dao.PenggunaDAO;
import etanah.model.KodCawangan;
import etanah.model.Pengguna;
import etanah.model.PermohonanPihak;
import etanah.service.common.PermohonanPihakService;
import etanah.view.etanahActionBeanContext;
import org.apache.commons.lang.StringUtils;

/**
 *
 * @author wan.fairul
 *
 */
@UrlBinding("/daftar/kesinambungan")
public class UtilitiPertanyaan extends PermohonanKaunter {

  private Permohonan permohonan;
  private String stage;
  private PermohonanPihak mohonPihak;
  private String status;
  private String keputusan;
  private String pendaftar;
  private String Spoc;
  private String participant;
  private String idHakmilik;
  private String idPermohonan;
  private String idCarian;
  private Hakmilik hakmilik;
  private PermohonanRujukanLuar pr;
  private List<HakmilikPermohonan> hakmilikpermohonan;
  private List<Permohonan> permohonanList;
  private HakmilikPermohonanService hakmilikPermohonanService;
  private List<HakmilikUrusan> hakmilikUrusanList;
  private List<HakmilikUrusan> hakmilikUrusanListTaktif;
  private List<HakmilikSebelumPermohonan> hmpSebelumList;
  private List<HakmilikSebelum> hmSebelumList;
  private List<FasaPermohonan> fasaMohon;
  private List<Map<String, String>> senaraiUrusanProsesDistinct;
  private List<Map<String, String>> senaraiUrusanProses;
  SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm");
  private List<HakmilikPihakBerkepentingan> listHakmilikPihak;
  private List<HakmilikPihakBerkepentingan> listHakmilikPihakCP;
  private List<Pihak> listPihak;
  private CarianHakmilik ch;
  private CarianHakmilikDAO chDAO;
  private PermohonanCarian pc;
  private List<Hakmilik> senaraiHakmilik;
  private Pengguna pengguna;
  private List<PermohonanPihak> listPermohonanPihak;  
  private boolean flag = false;

//    SimpleDateFormat sd = new SimpleDateFormat("dd/MM");
//    SimpleDateFormat yy = new SimpleDateFormat("yyyy");
    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

  public PermohonanPihak getMohonPihak() {
    return mohonPihak;
  }

    public void setMohonPihak(PermohonanPihak mohonPihak) {
        this.mohonPihak = mohonPihak;
    }
  
  public String getPendaftar() {
    return pendaftar;
  }

    public void setPendaftar(String pendaftar) {
        this.pendaftar = pendaftar;
    }
  
  public Pengguna getPengguna() {
    return pengguna;
  }

  public void setPengguna(Pengguna pengguna) {
    this.pengguna = pengguna;
  }
  
  public List<Hakmilik> getSenaraiHakmilik() {
    return senaraiHakmilik;
  }

  public void setSenaraiHakmilik(List<Hakmilik> senaraiHakmilik) {
    this.senaraiHakmilik = senaraiHakmilik;
  }

  public List<HakmilikPihakBerkepentingan> getListHakmilikPihak() {
    return listHakmilikPihak;
  }

  public void setListHakmilikPihak(List<HakmilikPihakBerkepentingan> listHakmilikPihak) {
    this.listHakmilikPihak = listHakmilikPihak;
  }
  //Azmi : Start CPB List//
  private List<Map<String, String>> senaraiHakmilikCPB;

  public List<Map<String, String>> getSenaraiHakmilikCPB() {
    return senaraiHakmilikCPB;
  }

  public void setSenaraiHakmilikCPB(List<Map<String, String>> senaraiHakmilikCPB) {
    this.senaraiHakmilikCPB = senaraiHakmilikCPB;
  }

    public List<PermohonanPihak> getListPermohonanPihak() {
        return listPermohonanPihak;
    }

    public void setListPermohonanPihak(List<PermohonanPihak> listPermohonanPihak) {
        this.listPermohonanPihak = listPermohonanPihak;
    }
  
  //Azmi : End CPB List//
  Logger LOG = Logger.getLogger(UtilitiPertanyaan.class);
  @Inject
  private PermohonanDAO permohonanDAO;
  @Inject
  private PermohonanCarianDAO pcDAO;
  @Inject
  private HakmilikDAO hmDAO;
  @Inject
  private PihakService pihakService;
  @Inject
  private PembetulanService pService;
  @Inject
  RegService regService;
  @Inject
  HakmilikUrusanService huService;
  @Inject
  PermohonanRujukanLuarService prlService;
  @Inject
  PermohonanService permohonanService;
  @Inject
  TabManager tabManager;
  @Inject
  PenggunaDAO penggunaDAO;
  @Inject PermohonanPihakService permohonanPihakService;

  public PermohonanRujukanLuar getPr() {
    return pr;
  }

  public void setPr(PermohonanRujukanLuar pr) {
    this.pr = pr;
  }

  public List<Map<String, String>> getSenaraiUrusanProses() {
    return senaraiUrusanProses;
  }

  public void setSenaraiUrusanProses(List<Map<String, String>> senaraiUrusanProses) {
    this.senaraiUrusanProses = senaraiUrusanProses;
  }

  public List<Map<String, String>> getSenaraiUrusanProsesDistinct() {
    return senaraiUrusanProsesDistinct;
  }

  public void setSenaraiUrusanProsesDistinct(List<Map<String, String>> senaraiUrusanProsesDistinct) {
    this.senaraiUrusanProsesDistinct = senaraiUrusanProsesDistinct;
  }

  public List<HakmilikPermohonan> getHakmilikpermohonan() {
    return hakmilikpermohonan;
  }

  public void setHakmilikpermohonan(List<HakmilikPermohonan> hakmilikpermohonan) {
    this.hakmilikpermohonan = hakmilikpermohonan;
  }

  public List<FasaPermohonan> getFasaMohon() {
    return fasaMohon;
  }

  public void setFasaMohon(List<FasaPermohonan> fasaMohon) {
    this.fasaMohon = fasaMohon;
  }

  public List<HakmilikSebelum> getHmSebelumList() {
    return hmSebelumList;
  }

  public void setHmSebelumList(List<HakmilikSebelum> hmSebelumList) {
    this.hmSebelumList = hmSebelumList;
  }

  public List<HakmilikSebelumPermohonan> getHmpSebelumList() {
    return hmpSebelumList;
  }

  public void setHmpSebelumList(List<HakmilikSebelumPermohonan> hmpSebelumList) {
    this.hmpSebelumList = hmpSebelumList;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public List<HakmilikUrusan> getHakmilikUrusanList() {
    return hakmilikUrusanList;
  }

  public void setHakmilikUrusanList(List<HakmilikUrusan> hakmilikUrusanList) {
    this.hakmilikUrusanList = hakmilikUrusanList;
  }

  public List<HakmilikUrusan> getHakmilikUrusanListTaktif() {
    return hakmilikUrusanListTaktif;
  }

  public void setHakmilikUrusanListTaktif(List<HakmilikUrusan> hakmilikUrusanListTaktif) {
    this.hakmilikUrusanListTaktif = hakmilikUrusanListTaktif;
  }

  public Hakmilik getHakmilik() {
    return hakmilik;
  }

  public void setHakmilik(Hakmilik hakmilik) {
    this.hakmilik = hakmilik;
  }

  public void setPermohonan(Permohonan p) {
    this.permohonan = p;
  }

  public Permohonan getPermohonan() {
    return permohonan;
  }

  public String getStage() {
    return stage;
  }

  public void setStage(String stage) {
    this.stage = stage;
  }

  public String getParticipant() {
    return participant;
  }

  public void setParticipant(String participant) {
    this.participant = participant;
  }

  public String getIdHakmilik() {
    return idHakmilik;
  }

  public void setIdHakmilik(String idHakmilik) {
    this.idHakmilik = idHakmilik;
  }

  public String getIdPermohonan() {
    return idPermohonan;
  }

  public void setIdPermohonan(String idPermohonan) {
    this.idPermohonan = idPermohonan;
  }

  public List<HakmilikPihakBerkepentingan> getListHakmilikPihakCP() {
    return listHakmilikPihakCP;
  }

  public void setListHakmilikPihakCP(List<HakmilikPihakBerkepentingan> listHakmilikPihakCP) {
    this.listHakmilikPihakCP = listHakmilikPihakCP;
  }

  public CarianHakmilik getCh() {
    return ch;
  }

  public void setCh(CarianHakmilik ch) {
    this.ch = ch;
  }

  public CarianHakmilikDAO getChDAO() {
    return chDAO;
  }

  public void setChDAO(CarianHakmilikDAO chDAO) {
    this.chDAO = chDAO;
  }

  public PermohonanCarian getPc() {
    return pc;
  }

  public void setPc(PermohonanCarian pc) {
    this.pc = pc;
  }

  public PermohonanCarianDAO getPcDAO() {
    return pcDAO;
  }

  public void setPcDAO(PermohonanCarianDAO pcDAO) {
    this.pcDAO = pcDAO;
  }

    public String getKeputusan() {
        return keputusan;
    }

    public void setKeputusan(String keputusan) {
        this.keputusan = keputusan;
    }

    public String getSpoc() {
        return Spoc;
    }

    public void setSpoc(String Spoc) {
        this.Spoc = Spoc;
    }
  
    
  

  @DefaultHandler
  public Resolution insertIdPermohonan() {
    return new ForwardResolution("/WEB-INF/jsp/daftar/main.jsp");
  }

  public Resolution checkPermohonan() throws WorkflowException {
    pengguna = (Pengguna) context.getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER); // get kod caw pguna
    LOG.info("-----> kod caw pguna : " + pengguna.getKodCawangan().getKod());

    if (idHakmilik != null) {
      hakmilik = pService.findHakmilik(idHakmilik);
      LOG.info("--> id hakmilik : " + hakmilik.getIdHakmilik());

      if (!"00".equals(pengguna.getKodCawangan().getKod())) { // if not PTG
        if (!hakmilik.getCawangan().getKod().equals(pengguna.getKodCawangan().getKod())) {
          addSimpleError("Hakmilik " + idHakmilik + " adalah dicawangan lain.");
          return new ForwardResolution("/WEB-INF/jsp/daftar/main.jsp");
        }
      }
      hakmilikUrusanList = new ArrayList<HakmilikUrusan>();
      hakmilikUrusanListTaktif = new ArrayList<HakmilikUrusan>();
      hakmilikUrusanList.addAll(pService.findUrusanByidHY(idHakmilik));
      hakmilikUrusanListTaktif.addAll(huService.findHakmilikUrusanTAktif(idHakmilik));

      LOG.info("--> hakmilikUrusanList.size()" + hakmilikUrusanList.size());
      LOG.info("--> hakmilikUrusanListTaktif.size()" + hakmilikUrusanListTaktif.size());
      // add table 'Urusan Dalam Proses'            
//      for (HakmilikUrusan hu : hakmilikUrusanList) {
//        permohonan = permohonanDAO.findById(hu.getIdPerserahan());
//        permohonanList.add(permohonan);
//      }
      List<Permohonan> listpermohonan = permohonanService.getSenaraiMohonByIdHakmilik(hakmilik.getIdHakmilik());
      senaraiUrusanProses = new ArrayList();
      for (Permohonan p : listpermohonan) {

        Map<String, String> map = new HashMap<String, String>();
        List<Task> taskList = WorkFlowService.queryTasksByAdmin(p.getIdPermohonan());
        if (taskList.size() > 0) {
          map.put("idPermohonan", p.getIdPermohonan());
          map.put("kodUrusan", p.getKodUrusan().getKod());
          map.put("urusan", p.getKodUrusan().getNama());
          String tarikhMula = sdf.format(p.getInfoAudit().getTarikhMasuk());
          map.put("tarikhMula", tarikhMula);
          Task t = taskList.get(0);
          String stage = t.getSystemAttributes().getStage();
          map.put("stage", tabManager.getCurrentAction(p.getKodUrusan().getIdWorkflow(), stage));
          senaraiUrusanProses.add(map);
        }
      }
      senaraiUrusanProsesDistinct = new ArrayList();
      String idPermohonanTemp = "";
      for (int m = 0; m < senaraiUrusanProses.size(); m++) {
        Map<String, String> map = new HashMap<String, String>();
        if (senaraiUrusanProses.get(m).get("idPermohonan").equals(idPermohonanTemp)) {
          continue;
        }
        map.put("idPermohonan", senaraiUrusanProses.get(m).get("idPermohonan"));
        map.put("kodUrusan", senaraiUrusanProses.get(m).get("kodUrusan"));
        map.put("urusan", senaraiUrusanProses.get(m).get("urusan"));
        String tarikhMula = senaraiUrusanProses.get(m).get("tarikhMula");
        map.put("tarikhMula", tarikhMula);
        map.put("stage", senaraiUrusanProses.get(m).get("stage"));
        senaraiUrusanProsesDistinct.add(map);
        idPermohonanTemp = senaraiUrusanProses.get(m).get("idPermohonan");
      } // end test
      return new ForwardResolution("/WEB-INF/jsp/daftar/utiliti/utiliti_maklumat_urusan.jsp");
    }

    if ((permohonan == null) && (idHakmilik == null)) {
      addSimpleError("Sila masukkan ID Permohonan/Perserahan atau ID Hakmilik");
      return new ForwardResolution("/WEB-INF/jsp/daftar/main.jsp");
    }

    idPermohonan = (String) getContext().getRequest().getParameter("idPermohonan");
    idPermohonan = permohonan.getIdPermohonan();
    if (idPermohonan == null) {
      addSimpleError("Sila masukkan ID Permohonan/Perserahan");
      return new ForwardResolution("/WEB-INF/jsp/daftar/main.jsp");
    }

      permohonan = permohonanDAO.findById(idPermohonan);
 
    if (permohonan == null) permohonan = permohonanService.getPermohonanByIdSptb(idPermohonan);

    if (permohonan == null) {
      LOG.info("Permohonan " + idPermohonan + " tidak dijumpai.");
      addSimpleError("Permohonan " + idPermohonan + " tidak dijumpai.");
      return new ForwardResolution("/WEB-INF/jsp/daftar/main.jsp");
    } else {
      // check kod_caw user
      
      pengguna = penggunaDAO.findById(permohonan.getInfoAudit().getDimasukOleh().getIdPengguna());
      Spoc = pengguna.getNama();
      LOG.info("Spoc " + Spoc);
      if(permohonan.getKodUrusan().getUrusanKaunter()== 'Y'){
          flag = true;
      }
      pengguna = (Pengguna) context.getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);    
          
      if ("00".equals(pengguna.getKodCawangan().getKod())) {  // if PTG
        List<Task> l = WorkFlowService.queryTasksByAdmin(idPermohonan);
        for (Task t : l) {
          stage = t.getSystemAttributes().getStage();
          participant = t.getSystemAttributes().getAcquiredBy();
        }
          permohonan.getInfoAudit().setDimasukOleh(null);
            
        if (permohonan.getSenaraiFasa() != null) {
          fasaMohon = permohonan.getSenaraiFasa();

          //To Set Kerani Kemasukan
          for (FasaPermohonan f : fasaMohon) {
            if (f.getIdAliran().equals("kemasukan")) {
              pengguna = penggunaDAO.findById(f.getIdPengguna());
              permohonan.getInfoAudit().setDimasukOleh(pengguna);
            }
            //To Set Pendaftar
            if (f.getIdAliran().equals("keputusan")) {
              pengguna = penggunaDAO.findById(f.getIdPengguna());
              pendaftar = pengguna.getNama();
            }
          }
        }
      } else {
        // if PTD then check kod_caw id mohon
        if (permohonan.getCawangan().getKod().equals(pengguna.getKodCawangan().getKod())) {
          List<Task> l = WorkFlowService.queryTasksByAdmin(idPermohonan);
          for (Task t : l) {
            stage = t.getSystemAttributes().getStage();
            participant = t.getSystemAttributes().getAcquiredBy();
          }
            permohonan.getInfoAudit().setDimasukOleh(null);

          if (permohonan.getSenaraiFasa() != null) {
            fasaMohon = permohonan.getSenaraiFasa();

            //To Set Kerani Kemasukan
              for (FasaPermohonan f : fasaMohon) {
                  if (f.getIdAliran().equals("kemasukan")) {
                      pengguna = penggunaDAO.findById(f.getIdPengguna());
                      permohonan.getInfoAudit().setDimasukOleh(pengguna);
                  }
                  //To Set Pendaftar
                  if (f.getIdAliran().equals("keputusan")) {
                      pengguna = penggunaDAO.findById(f.getIdPengguna());
                      pendaftar = pengguna.getNama();
                  }
              }
          }
        } else {
          addSimpleError("Permohonan " + idPermohonan + " adalah dicawangan lain.");
          return new ForwardResolution("/WEB-INF/jsp/daftar/main.jsp");
        }
      }
    }

    status = permohonan.getStatus();
    if(permohonan.getKeputusan() != null)
    {
        keputusan = permohonan.getKeputusan().getKod();
    }
    LOG.info("--------status ---- : " + status);
    LOG.info("--------kod urusan ---- : " + permohonan.getKodUrusan().getKodPerserahan().getKod());

    if (permohonan.getKodUrusan().getKodPerserahan().getKod().equalsIgnoreCase("MH")) {
      if ("SL".equals(status)) {
        List<HakmilikPermohonan> hpList = permohonan.getSenaraiHakmilik();
        for (HakmilikPermohonan hp : hpList) {
          hmSebelumList = regService.searchHakmilikSebelum(hp.getHakmilik().getIdHakmilik());
        }
      } else {
        hmpSebelumList = regService.searchMohonHakmilikSblmByIDPermohonan(permohonan.getIdPermohonan());
      }
    }

    if (permohonan.getKodUrusan().getKodPerserahan().getKod().equalsIgnoreCase("CP")) {
      pr = prlService.findByidPermohonan2(idPermohonan);
      String noSijil;
      if (pr == null) {
        noSijil = "-";
      } else {
        noSijil = pr.getNoSidang();
      }

      status = permohonan.getStatus();
      String daftar = "Daftar";
      List<HakmilikPermohonan> listHakmilikPermohonan = permohonan.getSenaraiHakmilik();
      Map<String, String> map = new HashMap<String, String>();
      senaraiHakmilikCPB = new ArrayList();
      for (HakmilikPermohonan hp : listHakmilikPermohonan) {

        idHakmilik = hp.getHakmilik().getIdHakmilik();

        hakmilik = pService.findHakmilik(idHakmilik);

        listHakmilikPihak = hakmilik.getSenaraiPihakBerkepentingan();
        for (HakmilikPihakBerkepentingan hpp : listHakmilikPihak) {

          if (hpp.getJenis().getKod().equals("CP") && hpp.getAktif() == 'Y') {
            map.put("idHakmilik", hpp.getHakmilik().getIdHakmilik());
            map.put("nama", hpp.getPihak().getNama());
            map.put("ic", hpp.getPihak().getNoPengenalan());
            map.put("noSijil", noSijil);
            map.put("status", daftar);
            senaraiHakmilikCPB.add(map);
          }
        }
      }
    }

//    if (permohonan.getKodUrusan().getKodPerserahan().getKod().equalsIgnoreCase("SM")) {
//      pr = prlService.findByidPermohonan2(idPermohonan);
//      String noSijil;
//      if (pr == null) {
//        noSijil = "-";
//      } else {
//        noSijil = pr.getNoSidang();
//      }
//      status = permohonan.getStatus();
//      String daftar = "Daftar";
//      List<HakmilikPermohonan> listHakmilikPermohonan = permohonan.getSenaraiHakmilik();
//      Map<String, String> map = new HashMap<String, String>();
//      senaraiHakmilikCPB = new ArrayList();
//      for (HakmilikPermohonan hp : listHakmilikPermohonan) {
//
//        idHakmilik = hp.getHakmilik().getIdHakmilik();
//
//        hakmilik = pService.findHakmilik(idHakmilik);
//
//        listHakmilikPihak = hakmilik.getSenaraiPihakBerkepentingan();
//        for (HakmilikPihakBerkepentingan hpp : listHakmilikPihak) {
//
//          if (hpp.getJenis().getKod().equals("SM") && hpp.getAktif() == 'Y') {
//
//            map.put("idHakmilik", hpp.getHakmilik().getIdHakmilik());
//            map.put("nama", hpp.getPihak().getNama());
//            map.put("ic", hpp.getPihak().getNoPengenalan());
//            map.put("noSijil", noSijil);
//            map.put("status", daftar);
//            senaraiHakmilikCPB.add(map);
//          }
//        }
//      }
//    }

//    azmi
    if (permohonan.getKodUrusan().getKodPerserahan().getKod().equalsIgnoreCase("SM")) {
      pr = prlService.findByidPermohonan2(idPermohonan);
      String noSijil;
      if (pr == null) {
        noSijil = "-";
      } else {
        noSijil = pr.getNoSidang();
      }
      status = permohonan.getStatus();
      String daftar = "Daftar";
      Map<String, String> map = new HashMap<String, String>();
      senaraiHakmilikCPB = new ArrayList();
        listPermohonanPihak = permohonanPihakService.findPermohonanPihakByIdPermohonan(permohonan.getIdPermohonan());
        LOG.info("--------List ...... ---- : " + listPermohonanPihak.size());
        for (PermohonanPihak pp : listPermohonanPihak) {

            String idMohonPihak = String.valueOf(pp.getIdPermohonanPihak());
            map.put("nama", pp.getNama());
            map.put("idmohonpihak",idMohonPihak);
            map.put("ic", pp.getNoPengenalan());
            map.put("status", pp.getStatusMohonPihak().getNama());
            senaraiHakmilikCPB.add(map);
          
        }
    }
//    azmi

    if (status == null) {
      addSimpleMessage("Urusan ini sedang diproses");
      return new ForwardResolution("/WEB-INF/jsp/daftar/permohonan_status.jsp");
    }
    else if("G".equals(keputusan)){
      addSimpleMessage("Urusan ini telah digantung. Sila hubungi Unit " + permohonan.getKodUrusan().getJabatanNama());
      return new ForwardResolution("/WEB-INF/jsp/daftar/permohonan_status.jsp");
    }
    else if ("TS".equals(status)) {
      addSimpleMessage("Menunggu Permohonan/Perserahan sebelum untuk bermula");
      return new ForwardResolution("/WEB-INF/jsp/daftar/permohonan_status.jsp");
    } else if ("TA".equals(status)) {
      addSimpleMessage("Permohonan masih dalam tindakan");
      return new ForwardResolution("/WEB-INF/jsp/daftar/permohonan_status.jsp");
    } else if ("AK".equals(status)) {
        StringBuilder sb = new StringBuilder("Urusan ini belum selesai");
      if (StringUtils.isNotBlank(stage)) {
          if (stage.equals("agih_tugas")) {
              sb.append(" (Peringkat Agihan)");
          } else if (stage.equals("kemasukan")) {
              sb.append(" (Peringkat Kemasukan)");
          } else if (stage.equals("keputusan")) {
              sb.append(" (Peringkat Pendaftar)");
          }
      }
      addSimpleMessage(sb.toString());
      return new ForwardResolution("/WEB-INF/jsp/daftar/permohonan_status.jsp");
    } else if ("GB".equals(status)) { // Gantung
      // TODO: check if task own by SPOC
      addSimpleMessage("Urusan ini telah digantung. Sila hubungi Unit " + permohonan.getKodUrusan().getJabatanNama());
      return new ForwardResolution("/WEB-INF/jsp/daftar/permohonan_status.jsp");
    } else if (("TK".equals(status))|| ("BP".equals(status))) {//TK - Tidak Aktif - Urusan telah dibatalkan
      addSimpleMessage("Urusan telah dibatalkan");
      return new ForwardResolution("/WEB-INF/jsp/daftar/permohonan_status.jsp");
    } else if ("TP".equals(status)) {
      addSimpleMessage("Menunggu tindakan selanjut daripada pemohon untuk meneruskan aliran kerja. Tugasan Berada Di kaunter");
      return new ForwardResolution("/WEB-INF/jsp/daftar/permohonan_status.jsp");
    } else if ("SL".equals(status)) {
        pendaftar = permohonan.getKeputusanOleh().getNama();
      addSimpleMessage("Urusan ini telah selesai diproses. Keputusan telah dikeluarkan");
      return new ForwardResolution("/WEB-INF/jsp/daftar/permohonan_status.jsp");
    } else if ("SS".equals(status)) {
      addSimpleMessage("Urusan ini telah disemak semula");
      return new ForwardResolution("/WEB-INF/jsp/daftar/permohonan_status.jsp");
    } else if ("W ".equals(status)) {
      addSimpleMessage("Urusan ini telah ditarik balik");
      return new ForwardResolution("/WEB-INF/jsp/daftar/permohonan_status.jsp");
    } else {
      LOG.info("--------return null ---- : " + status);
      return null;
    }
  }

  public Resolution viewhakmilikDetail() {
    idHakmilik = (String) getContext().getRequest().getParameter("idHakmilik");
    hakmilik = pService.findHakmilik(idHakmilik);

    //azmi//
    listHakmilikPihak = hakmilik.getSenaraiPihakBerkepentingan();

    for (HakmilikPihakBerkepentingan hpp : listHakmilikPihak) {
      Long idP = hpp.getIdHakmilikPihakBerkepentingan();
      String idPihak = idP.toString();
      Pihak pihak = pihakService.findById(idPihak);
    }
    //azmi//
    return new JSP("daftar/utiliti/paparan_hakmilik_single.jsp").addParameter("popup", "true");
  }
  //start apool
public Resolution viewSyarikatMCLDetail() {
    String idMohonPihak = (String) getContext().getRequest().getParameter("idMohonPihak");
    mohonPihak = permohonanPihakService.findById(idMohonPihak);
  
    return new JSP("daftar/utiliti/paparan_syarikatMCL.jsp").addParameter("popup", "true");
  }
  //end apool

  public Resolution viewUrusanDetail() throws WorkflowException {
    idPermohonan = (String) getContext().getRequest().getParameter("idPermohonan");
    permohonan = permohonanDAO.findById(idPermohonan);

    List<Task> l = WorkFlowService.queryTasksByAdmin(idPermohonan);
    for (Task t : l) {
      stage = t.getSystemAttributes().getStage();
      participant = t.getSystemAttributes().getAcquiredBy();
    }

    if (permohonan.getSenaraiFasa() != null) {
      fasaMohon = permohonan.getSenaraiFasa();
      if (fasaMohon != null) {
        for (FasaPermohonan fp : fasaMohon) {
          if (fp.getIdAliran().contains("kemasukan")) {
            Pengguna pengguna = penggunaDAO.findById(fp.getIdPengguna());
            permohonan.getInfoAudit().setDimasukOleh(pengguna);

          }

          Pengguna pengguna = penggunaDAO.findById(fp.getIdPengguna());
          fp.getInfoAudit().setDimasukOleh(pengguna);

        }
   
      }
    }
    status = permohonan.getStatus();
    if(permohonan.getKeputusan() != null)
    {
        keputusan = permohonan.getKeputusan().getKod();
    }
    if (status == null) {
      addSimpleMessage("Urusan ini sedang diproses");
    }
     else if("G".equals(keputusan)){
      addSimpleMessage("Urusan ini telah digantung. Sila hubungi Unit " + permohonan.getKodUrusan().getJabatanNama());
      return new ForwardResolution("/WEB-INF/jsp/daftar/permohonan_status.jsp");
    }
    else if ("TS".equals(status)) {
      addSimpleMessage("Menunggu Permohonan/Perserahan sebelum untuk bermula");
    } else if ("TA".equals(status)) {
      addSimpleMessage("Permohonan masih dalam tindakan");
    } else if ("AK".equals(status)) {
      addSimpleMessage("Urusan ini belum selesai (Peringkat Pendaftar).");
    } else if ("GB".equals(status)) { // Gantung
      // TODO: check if task own by SPOC
      addSimpleMessage("Urusan ini telah digantung. Sila hubungi Unit "
              + permohonan.getKodUrusan().getJabatanNama());
    } else if ("TK".equals(status)) {//TK - Tidak Aktif - Urusan telah dibatalkan
      addSimpleMessage("Urusan telah dibatalkan");
    } else if ("TP".equals(status)) {
      addSimpleMessage("Menunggu tindakan selanjut daripada pemohon untuk meneruskan aliran kerja. Tugasan Berada Di kaunter");
    } else if ("SL".equals(status)) {
        pendaftar = permohonan.getKeputusanOleh().getNama();
      addSimpleMessage("Urusan ini telah selesai diproses. Keputusan telah dikeluarkan");
    } else if ("SS".equals(status)) {
      addSimpleMessage("Urusan ini telah disemak semula");
    } else if ("W ".equals(status)) {//W - withdraw - Urusan telah ditarik balik
      addSimpleMessage("Urusan telah ditarik balik");
    } else {
      return null;
    }
    return new JSP("daftar/utiliti/status_permohonan.jsp").addParameter("popup", "true");
  }

  public Resolution viewUrusanDetail2() {

    pc = pcDAO.findById(idPermohonan);
    senaraiHakmilik = new ArrayList<Hakmilik>();
    for (CarianHakmilik ch : pc.getSenaraiHakmilik()) {
      hakmilik = hmDAO.findById(ch.getIdHakmilik());
      senaraiHakmilik.add(hakmilik);
    }



    return new JSP("daftar/utiliti/status_permohonan_carian.jsp").addParameter("popup", "true");
  }
}
