    /*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.stripes.pelupusan;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.dao.HakmilikDAO;
import etanah.dao.HakmilikPermohonanDAO;
import etanah.dao.KodJenisPihakBerkepentinganDAO;
import etanah.dao.PemohonDAO;
import etanah.dao.PermohonanDAO;
import etanah.dao.PihakDAO;
import etanah.dao.HakmilikPihakBerkepentinganDAO ;
import etanah.model.Hakmilik;
import etanah.model.HakmilikPermohonan;
import etanah.model.HakmilikPihakBerkepentingan;
import etanah.model.InfoAudit;
import etanah.model.KodBangsa;
import etanah.model.KodJenisPihakBerkepentingan;
import etanah.model.KodNegeri;
import etanah.model.Pemohon;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.model.PermohonanPihak;
import etanah.model.Pihak;
import etanah.model.PihakCawangan;
import etanah.service.ConsentPtdService;
import etanah.service.StrataPtService;
import etanah.service.common.PemohonService;
import etanah.service.common.PermohonanPihakService;
import etanah.service.common.PermohonanService;
import etanah.service.common.PihakService;
import net.sourceforge.stripes.action.UrlBinding;
//import etanah.view.AbleActionBean;
import etanah.view.ListUtil;
import java.util.List;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.controller.LifecycleStage;
import org.apache.log4j.Logger;
import org.apache.commons.lang.StringUtils;
import org.hibernate.Session;
import etanah.view.etanahActionBeanContext;
import java.util.ArrayList;
import java.util.LinkedList;
import net.sourceforge.stripes.action.StreamingResolution;
import org.hibernate.Transaction;




@UrlBinding("/pelupusan/pemohonMCL")
public class CatitTanahMCL_ActionBean extends AbleActionBean {

   @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    PemohonDAO pemohonDAO;
    @Inject
    PihakDAO pihakDAO;
    @Inject
    ConsentPtdService conService;
    @Inject
    PemohonService pemohonService;
    //Permohonan permohonan = new Permohonan();
    //Pihak pihak = new Pihak();
    @Inject
    HakmilikPihakBerkepentinganDAO hakmilikPihakBerkepentinganDAO ;
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
    HakmilikDAO hakmilikDAO ;
    @Inject
    HakmilikPermohonanDAO hakmilikPermohonanDAO ;
    private List<KodBangsa> senaraiKodBangsa;
    private PermohonanPihak permohonanPihak ;
    private List<PihakCawangan> cawanganList;
     private Pemohon pemohon;
     private Permohonan permohonan;
    //private PermohonanPihak permohonanPihak;
    private Pihak pihak;
    private List<PermohonanPihak> mohonPihakList;
    private List<HakmilikPihakBerkepentingan> hakmilikPihakList ;
    private String[] arrayCheckBox;
    private String idPermohonan;
    private List<Pihak> listTuanTanah;
    private List<Pemohon> listPemohon;
    private String display;
     boolean tambahCawFlag ;
     boolean cariFlag ;
     boolean tiadaDataFlag ;
      private String suratNegeri;
      private Hakmilik hakmilik ;
      private HakmilikPermohonan hmp ;
      private HakmilikPihakBerkepentingan hmpb ;
      private String test ;


    @DefaultHandler
    public Resolution showForm() {
        return new JSP("pelupusan/catit_tanah_mcl.jsp").addParameter("tab", "true");
    }

      public Resolution showForm2() {
        display = "display:none;";
        return new JSP("pelupusan/catit_tanah_mcl.jsp").addParameter("tab", "true");
    }

    public Resolution showEditPemohon() {
        String idPihak = getContext().getRequest().getParameter("idPihak");
        pihak = pihakDAO.findById(Long.valueOf(idPihak));
        //pihak.getIdPihak();

        return new JSP("pelupusan/edit_pemohonMCL.jsp").addParameter("popup", "true");

        //return new JSP("common/edit_pemohon2.jsp").addParameter("popup", "true");
    }

    public Resolution popup() {
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        if (idPermohonan != null) {
            permohonan = permohonanDAO.findById(idPermohonan);
        }
            String[] tname = {"permohonan"};
            Object[] model = {permohonan};
//        getContext().getRequest().setAttribute("pemohon", Boolean.TRUE);
//        String urusan = getContext().getRequest().getParameter("urusan");
//        if (StringUtils.isNotBlank(urusan)) {
//            getContext().getRequest().setAttribute("urusan", urusan);
//        }
        List hakmilikPermohonan = hakmilikPermohonanDAO.findByEqualCriterias(tname, model, null);
         if(!hakmilikPermohonan.isEmpty()){
            hmp = (HakmilikPermohonan) hakmilikPermohonan.get(0) ;
            hakmilik = hakmilikDAO.findById(hmp.getHakmilik().getIdHakmilik()) ;
             hakmilikPihakList = hakmilik.getSenaraiPihakBerkepentingan() ;
            }

        return new JSP("pelupusan/kemasukan_maklumat_pemohonMCL.jsp").addParameter("popup", "true");

    }

  @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {
    idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        if (idPermohonan != null) {
            permohonan = permohonanDAO.findById(idPermohonan);

            String[] tname = {"permohonan"};
            Object[] model = {permohonan};

            listPemohon = pemohonDAO.findByEqualCriterias(tname, model, null);
            
           
            




            if (!(listPemohon.isEmpty())) {
                pemohon = listPemohon.get(0);
                pihak = pihakDAO.findById(pemohon.getPihak().getIdPihak());
            }
            else {
               getContext().getRequest().setAttribute("edit", Boolean.TRUE);
            }
               // senaraiKodBangsa = listUtil.getSenaraiKodBangsaByJenisPengenalan(pihak.getJenisPengenalan().getKod());


            

        }
    }

    public Resolution simpanPemohon() {

        Pengguna p = (Pengguna) context.getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);

        InfoAudit info = p.getInfoAudit();

        info.setDimasukOleh(p);
        info.setTarikhMasuk(new java.util.Date());

        String idPihak = getContext().getRequest().getParameter("idPihak");

        Pihak phk = pihakDAO.findById(Long.valueOf(idPihak));
        Pemohon pemohon = new Pemohon();
        pemohon.setPermohonan(permohonan);
        pemohon.setPihak(phk);
        pemohon.setCawangan(p.getKodCawangan());
        pemohon.setInfoAudit(info);

        pemohon = conService.simpanPemohon(pemohon);

        String[] tname = {"permohonan"};
        Object[] model = {permohonan};

        listPemohon = pemohonDAO.findByEqualCriterias(tname, model, null);
        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        return new StreamingResolution("text/plain", String.valueOf(pemohon.getIdPemohon()));

    }

    public Resolution simpanPemohonPopup() {

        Permohonan permohonan = null;

         //Permohonan permohonan = null;
         String idPihak = getContext().getRequest().getParameter("idPihak");
         System.out.println("idPihak " + idPihak);
        if (idPermohonan != null) {
            permohonan = permohonanService.findById(idPermohonan);
        }
        System.out.println("id permohonan :" + permohonan.getIdPermohonan());
        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        InfoAudit info = new InfoAudit();
        info.setDimasukOleh(peng);
        info.setTarikhMasuk(new java.util.Date());
        Pihak pihakTemp = pihakDAO.findById(Long.valueOf(idPihak));

//       if (pihak.getBangsa() != null) {
//            if (pihak.getBangsa().getKod().toString().length() > 4) {
//                pihak.setBangsa(null);
//            }
//        }
        if (pihakTemp == null) {
            pihakTemp = new Pihak();
            pihakTemp.setNama(pihak.getNama());
            pihakTemp.setJenisPengenalan(pihak.getJenisPengenalan());
            pihakTemp.setNoPengenalan(pihak.getNoPengenalan());
            pihakTemp.setKodJantina(pihak.getKodJantina());
            pihakTemp.setBangsa(pihak.getBangsa());
            pihakTemp.setNoTelefon1(pihak.getNoTelefon1());
            pihakTemp.setNoTelefon2(pihak.getNoTelefon2());
            pihakTemp.setNoTelefonBimbit(pihak.getNoTelefonBimbit());
            pihakTemp.setAlamat1(pihak.getAlamat1());
            pihakTemp.setAlamat2(pihak.getAlamat2());
            pihakTemp.setAlamat3(pihak.getAlamat3());
            pihakTemp.setAlamat4(pihak.getAlamat4());
            pihakTemp.setPoskod(pihak.getPoskod());
            pihakTemp.setNegeri(pihak.getNegeri());
            pihakTemp.setSuratAlamat1(pihak.getSuratAlamat1());
            pihakTemp.setSuratAlamat2(pihak.getSuratAlamat2());
            pihakTemp.setSuratAlamat3(pihak.getSuratAlamat3());
            pihakTemp.setSuratAlamat4(pihak.getSuratAlamat4());
            pihakTemp.setSuratPoskod(pihak.getSuratPoskod());
            pihakTemp.setSuratNegeri(pihak.getSuratNegeri());

        }

        pihakTemp.setInfoAudit(info);
        Pemohon pmohon = new Pemohon();
        pmohon.setPermohonan(permohonan);
        pmohon.setPihak(pihakTemp);

        pmohon.setInfoAudit(info);
        if (pmohon != null) {
            pmohon.setCawangan(permohonan.getCawangan());

            pemohonService.simpanPihakPemohon(pihakTemp, pmohon);
          
             //getContext().getRequest().setAttribute("edit", Boolean.TRUE);
           
            //return new JSP("pelupusan/catit_tanah_mcl.jsp").addParameter("tab", "true");
           //return refreshpage() ;
        }
           getContext().getRequest().setAttribute("edit", Boolean.FALSE);

          rehydrate() ;
        return new JSP("pelupusan/catit_tanah_mcl.jsp").addParameter("tab", "true");
       
        
    }


    public Resolution deletePemohon() {

        String idPemohon = getContext().getRequest().getParameter("idPemohon");
        if (idPemohon != null) {
            Pemohon pemohon = pemohonService.findById(idPemohon);
            if (pemohon != null) {
                pemohonService.delete(pemohon);
                listPemohon = pemohonService.findPemohonByIdPermohonan(idPermohonan);
            }
        }

        return refreshpage() ;
    }

    public Resolution refreshpage() {
        System.out.println("refreshPage : start");
        rehydrate();
        System.out.println("refreshPage : finish");
        return new JSP("pelupusan/catit_tanah_mcl.jsp").addParameter("tab", "true");
    }

    public Resolution simpanPihak() {
        Pengguna pg = (Pengguna) context.getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        InfoAudit ia = new InfoAudit();
        ia.setDiKemaskiniOleh(pg);
        ia.setTarikhKemaskini(new java.util.Date());
        Pihak p = pihakDAO.findById(pihak.getIdPihak());
        p.setSuratAlamat1(pihak.getSuratAlamat1());
        p.setSuratAlamat2(pihak.getSuratAlamat2());
        p.setSuratAlamat3(pihak.getSuratAlamat3());
        p.setSuratAlamat4(pihak.getSuratAlamat4());
        p.setSuratPoskod(pihak.getSuratPoskod());
        p.setSuratNegeri(pihak.getSuratNegeri());
        p.setNoTelefon1(pihak.getNoTelefon1());
        p.setEmail(pihak.getEmail());
        ia.setDimasukOleh(p.getInfoAudit().getDimasukOleh());
        ia.setTarikhMasuk(p.getInfoAudit().getTarikhMasuk());
        p.setInfoAudit(ia);
        conService.simpanPihak(p);
        return refreshpage();
    }

     public Resolution cariPihak() {


        String tmp = getContext().getRequest().getParameter("tuanTanah");
        if (StringUtils.isNotBlank(tmp)) {
            getContext().getRequest().setAttribute("tuanTanah", Boolean.TRUE);
        }

        String jenisPihak = getContext().getRequest().getParameter("jenisPihak");
        if (StringUtils.isNotBlank(jenisPihak)) {
            KodJenisPihakBerkepentingan kod = kodJenisPihakBerkepentinganDAO.findById(jenisPihak);
            if (permohonanPihak == null) {
                permohonanPihak = new PermohonanPihak();
            }
            permohonanPihak.setJenis(kod);
            getContext().getRequest().setAttribute("jenis", jenisPihak);
        }

        if (pihak != null) {

            if (pihak.getJenisPengenalan() != null && pihak.getNoPengenalan() != null) {
                Pihak pihakSearch = new Pihak();
                pihakSearch = pihakService.findPihak(pihak.getJenisPengenalan().getKod(), pihak.getNoPengenalan());

                boolean duplicateFlag = false;



                if (duplicateFlag == false) {
                    if (pihakSearch != null) {
                        pihak = pihakSearch;
                        cawanganList = pihak.getSenaraiCawangan();
                        getContext().getRequest().setAttribute("update", Boolean.TRUE);
                        cariFlag = true;
                        tiadaDataFlag = false;
                    } else {
                        addSimpleMessage("Tiada Data. Sila Masukkan Maklumat Baru.");

                        cariFlag = true;
                        tiadaDataFlag = true;

                    }

                    senaraiKodBangsa = listUtil.getSenaraiKodBangsaByJenisPengenalan(pihak.getJenisPengenalan().getKod());


                } else {
                    pihak = new Pihak();
                    addSimpleError("Maklumat Ini Telah Dipilih.");
                }
            } else {

                if (pihak.getJenisPengenalan() == null) {
                    addSimpleError("Sila Pilih Jenis Pengenalan.");
                    getContext().getRequest().setAttribute("update", Boolean.TRUE);
                } else if (pihak.getNoPengenalan() == null) {
                    addSimpleError("Sila Masukkan  Nombor Pengenalan.");
                    getContext().getRequest().setAttribute("update", Boolean.TRUE);
                }
            }
        } else {

            getContext().getRequest().setAttribute("update", Boolean.TRUE);
            addSimpleError("Sila Masukkan Jenis Pengenalan Dan Nombor Pengenalan.");
        }

        return new JSP("pelupusan/kemasukan_maklumat_pemohonMCL.jsp").addParameter("popup", "true");
    }

     public Resolution updatePihak() {
        Transaction tx = sessionProvider.get().beginTransaction();
        tx.begin();
        try {

            Pengguna pg = (Pengguna) context.getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);

            InfoAudit infoAudit = new InfoAudit();



            pihak = pihakDAO.findById(pihak.getIdPihak());
            KodNegeri kn = new KodNegeri();
            kn.setKod(suratNegeri);
            pihak.setSuratNegeri(kn);
            infoAudit = pihak.getInfoAudit();
            infoAudit.setDiKemaskiniOleh(pg);
            infoAudit.setTarikhKemaskini(new java.util.Date());
            pihak.setInfoAudit(infoAudit);

            strService.updatePihak(pihak);


        } catch (Exception e) {
            tx.rollback();
            addSimpleError("Kemasukan Data Gagal. Sila Hubungi Pentadbir Sistem.");
            return new JSP("pelupusan/catit_tanah_mcl.jsp").addParameter("tab", "true");
        }
        tx.commit();
        //return new JSP("pelupusan/catit_tanah_mcl.jsp").addParameter("tab", "true");
        return refreshpage() ;
    }


    public List<Pihak> getListTuanTanah() {
        return listTuanTanah;
    }

    public void setListTuanTanah(List<Pihak> listTuanTanah) {
        this.listTuanTanah = listTuanTanah;
    }

    public List<Pemohon> getListPemohon() {
        return listPemohon;
    }

    public void setListPemohon(List<Pemohon> listPemohon) {
        this.listPemohon = listPemohon;
    }

  //  public String[] getArrayCheckBox() {
  //      return arrayCheckBox;
  //  }

  //  public void setArrayCheckBox(String[] arrayCheckBox) {
  //      this.arrayCheckBox = arrayCheckBox;
  //  }

    public Pihak getPihak() {
        return pihak;
    }

    public void setPihak(Pihak pihak) {
        this.pihak = pihak;
    }

    public String getDisplay() {
        return display;
    }

    public void setDisplay(String display) {
        this.display = display;
    }

    public boolean isTambahCawFlag() {
        return tambahCawFlag;
    }

    public void setTambahCawFlag(boolean tambahCawFlag) {
        this.tambahCawFlag = tambahCawFlag;
    }

    public boolean isCariFlag() {
        return cariFlag;
    }

    public void setCariFlag(boolean cariFlag) {
        this.cariFlag = cariFlag;
    }

    public boolean isTiadaDataFlag() {
        return tiadaDataFlag;
    }

    public void setTiadaDataFlag(boolean tiadaDataFlag) {
        this.tiadaDataFlag = tiadaDataFlag;
    }

    public List<PihakCawangan> getCawanganList() {
        return cawanganList;
    }

    public void setCawanganList(List<PihakCawangan> cawanganList) {
        this.cawanganList = cawanganList;
    }

    public ConsentPtdService getConService() {
        return conService;
    }

    public void setConService(ConsentPtdService conService) {
        this.conService = conService;
    }

    public String getIdPermohonan() {
        return idPermohonan;
    }

    public void setIdPermohonan(String idPermohonan) {
        this.idPermohonan = idPermohonan;
    }

    public KodJenisPihakBerkepentinganDAO getKodJenisPihakBerkepentinganDAO() {
        return kodJenisPihakBerkepentinganDAO;
    }

    public void setKodJenisPihakBerkepentinganDAO(KodJenisPihakBerkepentinganDAO kodJenisPihakBerkepentinganDAO) {
        this.kodJenisPihakBerkepentinganDAO = kodJenisPihakBerkepentinganDAO;
    }

    public ListUtil getListUtil() {
        return listUtil;
    }

    public void setListUtil(ListUtil listUtil) {
        this.listUtil = listUtil;
    }

    public List<PermohonanPihak> getMohonPihakList() {
        return mohonPihakList;
    }

    public void setMohonPihakList(List<PermohonanPihak> mohonPihakList) {
        this.mohonPihakList = mohonPihakList;
    }

    public PemohonDAO getPemohonDAO() {
        return pemohonDAO;
    }

    public void setPemohonDAO(PemohonDAO pemohonDAO) {
        this.pemohonDAO = pemohonDAO;
    }

    public PemohonService getPemohonService() {
        return pemohonService;
    }

    public void setPemohonService(PemohonService pemohonService) {
        this.pemohonService = pemohonService;
    }

    public Permohonan getPermohonan() {
        return permohonan;
    }

    public void setPermohonan(Permohonan permohonan) {
        this.permohonan = permohonan;
    }

    public PermohonanDAO getPermohonanDAO() {
        return permohonanDAO;
    }

    public void setPermohonanDAO(PermohonanDAO permohonanDAO) {
        this.permohonanDAO = permohonanDAO;
    }

    public PermohonanPihak getPermohonanPihak() {
        return permohonanPihak;
    }

    public void setPermohonanPihak(PermohonanPihak permohonanPihak) {
        this.permohonanPihak = permohonanPihak;
    }

    public PermohonanPihakService getPermohonanPihakService() {
        return permohonanPihakService;
    }

    public void setPermohonanPihakService(PermohonanPihakService permohonanPihakService) {
        this.permohonanPihakService = permohonanPihakService;
    }

    public PermohonanService getPermohonanService() {
        return permohonanService;
    }

    public void setPermohonanService(PermohonanService permohonanService) {
        this.permohonanService = permohonanService;
    }

    public PihakDAO getPihakDAO() {
        return pihakDAO;
    }

    public void setPihakDAO(PihakDAO pihakDAO) {
        this.pihakDAO = pihakDAO;
    }

    public PihakService getPihakService() {
        return pihakService;
    }

    public void setPihakService(PihakService pihakService) {
        this.pihakService = pihakService;
    }

    public List<KodBangsa> getSenaraiKodBangsa() {
        return senaraiKodBangsa;
    }

    public void setSenaraiKodBangsa(List<KodBangsa> senaraiKodBangsa) {
        this.senaraiKodBangsa = senaraiKodBangsa;
    }

    public StrataPtService getStrService() {
        return strService;
    }

    public void setStrService(StrataPtService strService) {
        this.strService = strService;
    }

    public String getSuratNegeri() {
        return suratNegeri;
    }

    public void setSuratNegeri(String suratNegeri) {
        this.suratNegeri = suratNegeri;
    }

    public Hakmilik getHakmilik() {
        return hakmilik;
    }

    public void setHakmilik(Hakmilik hakmilik) {
        this.hakmilik = hakmilik;
    }

    public List<HakmilikPihakBerkepentingan> getHakmilikPihakList() {
        return hakmilikPihakList;
    }

    public void setHakmilikPihakList(List<HakmilikPihakBerkepentingan> hakmilikPihakList) {
        this.hakmilikPihakList = hakmilikPihakList;
    }

    public HakmilikPermohonan getHmp() {
        return hmp;
    }

    public void setHmp(HakmilikPermohonan hmp) {
        this.hmp = hmp;
    }

    public HakmilikPihakBerkepentingan getHmpb() {
        return hmpb;
    }

    public void setHmpb(HakmilikPihakBerkepentingan hmpb) {
        this.hmpb = hmpb;
    }

    public Pemohon getPemohon() {
        return pemohon;
    }

    public void setPemohon(Pemohon pemohon) {
        this.pemohon = pemohon;
    }

    public String getTest() {
        return test;
    }

    public void setTest(String test) {
        this.test = test;
    }

    

}





