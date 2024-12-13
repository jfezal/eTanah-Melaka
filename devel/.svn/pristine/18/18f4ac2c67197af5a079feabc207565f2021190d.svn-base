/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package etanah.view.stripes.pengambilan;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.dao.KodNegeriDAO;
import etanah.dao.PihakCawanganDAO;
import etanah.dao.PihakDAO;
import etanah.dao.PemohonDAO;
import etanah.service.common.HakmilikService;
import etanah.model.Hakmilik;
import etanah.model.HakmilikPermohonan;
import etanah.model.HakmilikPihakBerkepentingan;
import etanah.model.InfoAudit;
import etanah.model.KodNegeri;
import etanah.model.Pemohon;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.model.PermohonanAtasPihakBerkepentingan;
import etanah.model.PermohonanPihak;
import etanah.model.Pihak;
import etanah.model.PihakCawangan;
import etanah.service.SyerValidationService;
import etanah.service.common.HakmilikPihakKepentinganService;
import etanah.service.common.PemohonService;
import etanah.service.common.PermohonanPihakService;
import etanah.service.common.PermohonanService;
import etanah.service.common.PihakService;
import etanah.view.etanahActionBeanContext;
import java.util.ArrayList;
import java.util.List;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.RedirectResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.StreamingResolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;
import org.apache.commons.lang.StringUtils;
import etanah.model.PermohonanPihakKemaskini;
import etanah.service.daftar.PermohonanPihakKemaskiniService;
import org.apache.log4j.Logger;
import etanah.dao.PermohonanPihakKemaskiniDAO;
import etanah.model.KodCawangan;
import org.hibernate.Session;
import org.hibernate.Transaction;
import etanah.service.common.PermohonanAtasPerserahanService;
import etanah.service.RegService;
import javax.swing.JOptionPane;



@UrlBinding("/pengambilan/pemohon")
public class pemohonActionBean extends AbleActionBean {

    private static Logger logger = Logger.getLogger(pemohonActionBean.class);
    @Inject
    HakmilikService hakmilikManager;
    @Inject
    PihakService pihakService;
    @Inject
    PermohonanPihakService permohonanPihakService;
    @Inject
    PermohonanService permohonanService;
    @Inject
    PemohonService pemohonService;
    @Inject
    SyerValidationService syerService;
    @Inject
    HakmilikPihakKepentinganService hakmilikPihakKepentinganService;
    @Inject
    PihakDAO pihakDAO;
    @Inject
    PemohonDAO pemohonDAO;
    @Inject
    KodNegeriDAO kodNegeriDAO;
    @Inject
    PermohonanPihakKemaskiniService mohonPihakKemaskiniService;
    @Inject
    PermohonanPihakKemaskiniDAO mohonPihakKemaskiniDAO;
    @Inject
    PihakCawanganDAO pihakCawanganDAO;
    @Inject
    RegService regService;

    private List<PermohonanPihakKemaskini> mohonPihakKemaskiniList;
    private List<HakmilikPihakBerkepentingan> pihakKepentinganList;
    private List<HakmilikPihakBerkepentingan> othersPihakList;
    private List<PermohonanAtasPihakBerkepentingan> mapList;
    private List<HakmilikPihakBerkepentingan> pihakKepentinganList2;
    private List<HakmilikPihakBerkepentingan> pemilik;
    private List<HakmilikPihakBerkepentingan> selainpemilik;
    private List<PermohonanPihak> senaraiPemohonBerangkai;
    private Pihak pihak;
    private PihakCawangan pihakCawangan;
    private PermohonanPihak permohonanPihak;
    private List<Pemohon> pemohonList;
    private List<PermohonanPihak> mohonPihakList;
    private List<PihakCawangan> cawanganList;
    private List<HakmilikPermohonan> hakmilikPermohonanList2;
    private List<HakmilikPermohonan> hakmilikPermohonanList;
//    private HakmilikPermohonan hakmilikPermohonan;



    private String idPermohonan;
    private Permohonan p;
    private Pengguna pguna;
    private String[] syer1;
    private String[] syer2;
    boolean cariFlag;
    boolean tiadaDataFlag = false;
    private PermohonanPihakKemaskini mohonPihakKemaskini;
    private String alamat1;
    private String alamat2;
    private String alamat3;
    private String alamat4;
    private String alamat5;
    private String alamat6;
    private String nama;
    private String nokp;
    private KodCawangan cawangan;
    boolean tambahCawFlag;
    private int size=0;
    private Pihak pi;
    private String idPihak;


    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;
    @Inject
    PermohonanAtasPerserahanService mohonAtasSerahService;
    private String idCawangan;
    private ArrayList list2;


    @DefaultHandler
  
    public Resolution showForm() {
         getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        return new ForwardResolution("/WEB-INF/jsp/pengambilan/maklumat_pengambilan_pemohon.jsp").addParameter("tab", "true");
    }

    public Resolution showForm2() {
//         getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        return new ForwardResolution("/WEB-INF/jsp/pengambilan/maklumat_pengambilan_pemohon.jsp").addParameter("tab", "true");
    }

    public Resolution getSenaraiHakmilikKepentingan() {
//        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        if (idPermohonan != null) {
//            p = permohonanService.findById(idPermohonan);
            if (p != null && p.getPermohonanSebelum() != null) {
                Permohonan t = p.getPermohonanSebelum();
                if (t != null) {
                    getContext().getRequest().setAttribute("copy", Boolean.TRUE);
                }
            }
            //fikri :: urusan berangkai (25012010)
            if(p != null) {
                if(StringUtils.isNotBlank( p.getIdKumpulan()) ) {
                    //seandainya urusan berangkai, dan pemohonan belum lg didaftarkan
                    //kena ambil pihak dari pemohon.
                    if(p.getPermohonanSebelum() != null) {
                        Permohonan permohonanSebelum = p.getPermohonanSebelum();
                        String idPermohonanSblm = permohonanSebelum.getIdPermohonan();
                        senaraiPemohonBerangkai = permohonanPihakService.getSenaraiPmohonPihak(idPermohonanSblm);
                        if(senaraiPemohonBerangkai.size() > 0) {
                            getContext().getRequest().setAttribute("berangkai", Boolean.TRUE);
                        }
                    }
                }
            }
        }
        return new JSP("pengambilan/dev_senarai_pihak_berkepentingan.jsp").addParameter("tab", "true");
    }

    public Resolution senaraiHakmilikPindahMilikBerkepentingan() {
//        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
//        List<HakmilikPermohonan> hakmilikPermohonanList = p.getSenaraiHakmilik();
        if (hakmilikPermohonanList.size() > 0) {
            Hakmilik h = hakmilikPermohonanList.get(0).getHakmilik();
            String kodPihak = "";
            if (p.getKodUrusan().getKod().equals("PMP")) {
                kodPihak = "PJ"; //pemegang pajakan
            } else if(p.getKodUrusan().getKod().equals("PMG")) {
                kodPihak = "PG"; //pemegang gadaian
            }
            pihakKepentinganList2 = hakmilikPihakKepentinganService.findHakmilikPihakByKod(h, kodPihak);
        }
        return new JSP("daftar/kemasukan_pihak_berkepentingan_pindahmilik.jsp").addParameter("tab", "true");
    }
     public Resolution refreshpage1() {
        rehydrate();
        return new RedirectResolution(pemohonActionBean.class, "locate");
    }

    public Resolution simpanPemohon() {

        idPihak = getContext().getRequest().getParameter("idPihak");
        System.out.println("idPihak :" + idPihak);
        Pihak pi = pihakService.findById(idPihak);
        Pemohon pemohon = pemohonService.findPemohonByPermohonanPihak(p, pi);
        if (pemohon == null) {
            Pemohon pe = new Pemohon();
            InfoAudit ia = new InfoAudit();
            ia.setDimasukOleh(pguna);
            ia.setTarikhMasuk(new java.util.Date());
            pe.setInfoAudit(ia);
            pe.setPermohonan(p);
            pe.setPihak(pi);
            pe.setCawangan(pguna.getKodCawangan());
            pemohonService.saveOrUpdate(pe);
        } else {
            addSimpleError("Tuan Tanah " + pi.getNama() + " telah memohon. Sila pilih tuan tanah yang lain.");
        }
//        return new JSP("daftar/kemaskini_senarai_pihak_berkepentingan.jsp").addParameter("tab", "true");
        rehydrate();
        return getSenaraiHakmilikKepentingan();
    }
    public Resolution simpanPemohon2() {

        String idPihak = getContext().getRequest().getParameter("idPihak");
        System.out.println("idPihak :" + idPihak);
        Pihak pi = pihakService.findById(idPihak);
        Pemohon pemohon = pemohonService.findPemohonByPermohonanPihak(p, pi);
        if (pemohon == null) {
            Pemohon pe = new Pemohon();
            InfoAudit ia = new InfoAudit();
            ia.setDimasukOleh(pguna);
            ia.setTarikhMasuk(new java.util.Date());
            pe.setInfoAudit(ia);
            pe.setPermohonan(p);
            pe.setPihak(pi);
            pemohonService.saveOrUpdate(pe);
        } else {
            addSimpleError(pi.getNama() + " telah memohon.");
        }
//        return new JSP("daftar/kemaskini_senarai_pihak_berkepentingan.jsp").addParameter("tab", "true");
        rehydrate();
        return senaraiHakmilikPindahMilikBerkepentingan();
    }

    public Resolution deletePemohon() {

        String idPemohon = getContext().getRequest().getParameter("idPemohon");
        if (idPemohon != null) {
            Pemohon pemohon = pemohonService.findById(idPemohon);
            if (pemohon != null) {
                pemohonService.delete(pemohon);
                pemohonList = pemohonService.findPemohonByIdPermohonan(idPermohonan);
            }
        }
//        return new JSP("daftar/kemaskini_senarai_pihak_berkepentingan.jsp").addParameter("tab", "true");
        rehydrate();
        return getSenaraiHakmilikKepentingan();
    }

    public Resolution pemohonPopup() {
        return new JSP("pengambilan/maklumat_pemohon.jsp").addParameter("popup", "true");
    }

     public Resolution deleteSingle()  {
//             InfoAudit ia = new InfoAudit();
//            Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
            String idPemohon = getContext().getRequest().getParameter("idPemohon");

            if (idPemohon != null) {
            Pemohon pemohon = pemohonService.findById(idPemohon);
            if (pemohon != null) {
                pemohonService.delete(pemohon);
                pemohonList = pemohonService.findPemohonByIdPermohonan(idPermohonan);
            }
        }

      return new RedirectResolution(PihakBerkepentinganActionBean.class, "locate");

}

    public Resolution deletePemohon2() {

        String idPemohon = getContext().getRequest().getParameter("idPemohon");
        if (idPemohon != null) {
            Pemohon pemohon = pemohonService.findById(idPemohon);
            if (pemohon != null) {
                pemohonService.delete(pemohon);
                pemohonList = pemohonService.findPemohonByIdPermohonan(idPermohonan);
            }
        }
//        return new JSP("daftar/kemaskini_senarai_pihak_berkepentingan.jsp").addParameter("tab", "true");
        rehydrate();
        return senaraiHakmilikPindahMilikBerkepentingan();
    }

    public Resolution deleteChanges() {

        String idKkini = (String) getContext().getRequest().getParameter("idKkini");
        logger.debug("idKkini : " + idKkini);
        if (idKkini != null) {
            PermohonanPihakKemaskini mpk = mohonPihakKemaskiniDAO.findById(new Long(idKkini));
            if (mpk != null) {
                mohonPihakKemaskiniService.delete(mpk);
                mohonPihakKemaskiniList = p.getSenaraiPihakKemaskini();
            }
//            getContext().getMessages().add(new SimpleMessage("Data {1}.", mpk));
            addSimpleMessage("Data Telah Berjaya diHapuskan");
        }
        addSimpleError("idKkini tiada");


        return new RedirectResolution(PihakBerkepentinganActionBean.class, "getSenaraiHakmilikKepentingan");
    }

   

    public Resolution pihakKepentinganPopup() {
        String urusan = getContext().getRequest().getParameter("urusan");
        if (StringUtils.isNotBlank(urusan)) {
            getContext().getRequest().setAttribute("urusan", urusan);
        }
        return new JSP("pengambilan/dev_kemasukan_pihak_berkepentingan.jsp").addParameter("popup", "true");
    }

    public Resolution showEditPemohon() {
         String idPihak = getContext().getRequest().getParameter("idPihak");
        pihak = pihakDAO.findById(Long.valueOf(idPihak));
        return new JSP("pengambilan/edit_pemohon.jsp").addParameter("popup", "true");
    }

    

    public Resolution simpanPemohonPopup() {

        Permohonan permohonan = null;

        if (idPermohonan != null) {
            permohonan = permohonanService.findById(idPermohonan);
        }
        System.out.println("id permohonan :" + permohonan.getIdPermohonan());
        InfoAudit info = new InfoAudit();
        info.setDimasukOleh(pguna);
        info.setTarikhMasuk(new java.util.Date());
        Pihak pihakTemp = pihakDAO.findById(pihak.getIdPihak());

        if (pihak.getBangsa() != null) {
            if (pihak.getBangsa().getKod().toString().length() > 4) {
                pihak.setBangsa(null);
            }
        }

        if (pihakTemp == null) {
            pihakTemp = new Pihak();
            pihakTemp.setNama(pihak.getNama());
            pihakTemp.setJenisPengenalan(pihak.getJenisPengenalan());
            pihakTemp.setNoPengenalan(pihak.getNoPengenalan());
            pihakTemp.setKodJantina(pihak.getKodJantina());
            pihakTemp.setBangsa(pihak.getBangsa());
            pihakTemp.setSuku(pihak.getSuku());
            pihakTemp.setEmail(pihak.getEmail());
            pihakTemp.setNoTelefon1(pihak.getNoTelefon1());
            pihakTemp.setNoTelefon2(pihak.getNoTelefon2());
            pihakTemp.setNoTelefonBimbit(pihak.getNoTelefonBimbit());
//            pihakTemp.setAlamat1(pihak.getAlamat1());
//            pihakTemp.setAlamat2(pihak.getAlamat2());
//            pihakTemp.setAlamat3(pihak.getAlamat3());
//            pihakTemp.setAlamat4(pihak.getAlamat4());
//            pihakTemp.setPoskod(pihak.getPoskod());
            pihakTemp.setNegeri(pihak.getNegeri());
            pihakTemp.setSuratAlamat1(pihak.getSuratAlamat1());
            pihakTemp.setSuratAlamat2(pihak.getSuratAlamat2());
            pihakTemp.setSuratAlamat3(pihak.getSuratAlamat3());
            pihakTemp.setSuratAlamat4(pihak.getSuratAlamat4());
            pihakTemp.setSuratPoskod(pihak.getSuratPoskod());
//            pihakTemp.setSuratNegeri(pihak.getSuratNegeri());
        }
        pihakTemp.setInfoAudit(info);

        Pemohon pemohon = new Pemohon();
        pemohon.setPermohonan(permohonan);
        pemohon.setPihak(pihakTemp);
        pemohon.setInfoAudit(info);
        pemohon.setCawangan(pguna.getKodCawangan());


        if (idCawangan != null) {
            pihakCawangan = new PihakCawangan();
            pihakCawangan = pihakCawanganDAO.findById(Long.valueOf(idCawangan));
            logger.debug(pihakCawangan);
            pemohon.setPihakCawangan(pihakCawangan);
        }

        if (pemohon != null) {
            logger.debug("Simpan:" + pihakTemp);
            pemohonService.simpanPihakPemohon(pihakTemp, pemohon);
//            return new RedirectResolution("/pengambilan/pihak_berkepentingan?getSenaraiHakmilikKepentingan").addParameter("tab", "true");
        }
        return new ForwardResolution("/WEB-INF/jsp/pengambilan/maklumat_pengambilan_pemohon.jsp").addParameter("tab", "true");
//        return new StreamingResolution("text/plain", "1");
    }

    
    
    public Resolution simpanPihak() {
            Pengguna pg = (Pengguna) context.getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
            idPihak = getContext().getRequest().getParameter("idPihak");
            pi = pihakDAO.findById(Long.valueOf(idPihak));
            InfoAudit ia = new InfoAudit();
            if(ia==null)
            {
                pi= new Pihak();
                ia=new InfoAudit();
                ia.setDimasukOleh(pg);
                ia.setTarikhMasuk(new java.util.Date());
                pi.setInfoAudit(ia);
            }
            else
            {
               ia.setDiKemaskiniOleh(pg);
               ia.setTarikhKemaskini(new java.util.Date());
               ia.setDimasukOleh(pi.getInfoAudit().getDimasukOleh());
               ia.setTarikhMasuk(pi.getInfoAudit().getTarikhMasuk());
               pi.setSuratAlamat1(pihak.getSuratAlamat1());
               pi.setSuratAlamat2(pihak.getSuratAlamat2());
               pi.setSuratAlamat3(pihak.getSuratAlamat3());
               pi.setSuratAlamat4(pihak.getSuratAlamat4());
               pi.setSuratPoskod(pihak.getSuratPoskod());
               pi.setNoTelefon1(pihak.getNoTelefon1());
               pi.setEmail(pihak.getEmail());
               KodNegeri kn = kodNegeriDAO.findById(pihak.getNegeri().getKod());
               pi.setNegeri(kn);
               pi.setInfoAudit(ia);
            }
        pihakService.saveOrUpdate(pi);
    
        addSimpleMessage("Data Telah Berjaya dikemaskini");
        return new JSP("pengambilan/maklumat_pengambilan_pemohon.jsp").addParameter("tab", "true");

    }
    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {
        pguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        p = permohonanService.findById(idPermohonan);

//            List<HakmilikPihakBerkepentingan> pihakKepentinganList = new ArrayList<HakmilikPihakBerkepentingan>();
            pihakKepentinganList = new ArrayList<HakmilikPihakBerkepentingan>();

        if (p != null) {
//            p = permohonanService.findById(idPermohonan);
            idPermohonan = p.getIdPermohonan();
             hakmilikPermohonanList2 = p.getSenaraiHakmilik();

            for (HakmilikPermohonan hakmilikPermohonan : hakmilikPermohonanList2) {

                 Hakmilik hak = hakmilikPermohonan.getHakmilik();

                 List<HakmilikPihakBerkepentingan> listHakPihak;
                 listHakPihak =  hak.getSenaraiPihakBerkepentingan();
                 pihakKepentinganList.addAll(listHakPihak);
//                 pemilik.addAll(listHakPihak);
                 mohonPihakList = permohonanPihakService.getSenaraiPmohonPihak(idPermohonan);
                 pemohonList = pemohonService.findPemohonByIdPermohonan(idPermohonan);
//                 othersPihakList = hakmilikPihakKepentinganService.findHakmilikPihakBerkepentinganByIdHakmilik(hak.getIdHakmilik());
                 pemilik = hakmilikPihakKepentinganService.findPihakActiveByHakmilik(hak);
                size = listHakPihak.size()+ size;
            }



//             for (int i = 0; i < hakmilikPermohonanList.size(); i++) {
//                HakmilikPermohonan h = (HakmilikPermohonan) hakmilikPermohonanList.get(i);
//                pihakKepentinganList=h.getHakmilik().getSenaraiPihakBerkepentingan();
//                size = pihakKepentinganList.size()+ size;
//            }


//                    othersPihakList = hakmilikPihakKepentinganService.findHakmilikPihakBerkepentinganByIdHakmilik(hk.getIdHakmilik());
//                    mohonPihakList = permohonanPihakService.getSenaraiPmohonPihak(idPermohonan);
//                    pemohonList = pemohonService.findPemohonByIdPermohonan(idPermohonan);
//                    syer1 = new String[mohonPihakList.size()];
//                    syer2 = new String[mohonPihakList.size()];

//                    pihakKepentinganList = hakmilikPihakKepentinganService.findHakmilikPihakActiveByHakmilik(hk);
//            if (hakmilikPermohonanList.size() > 0) {
//                Hakmilik hk = hakmilikPermohonanList.get(0).getHakmilik();
//                if (hk != null) {
//                    pihakKepentinganList = hk.getSenaraiPihakBerkepentingan();
////                    pihakKepentinganList = hakmilikPihakKepentinganService.findHakmilikPihakActiveByHakmilik(hk);
//                    size = pihakKepentinganList.size();
//                    othersPihakList = hakmilikPihakKepentinganService.findHakmilikPihakBerkepentinganByIdHakmilik(hk.getIdHakmilik());
//                    mohonPihakList = permohonanPihakService.getSenaraiPmohonPihak(idPermohonan);
//                    pemohonList = pemohonService.findPemohonByIdPermohonan(idPermohonan);
//                    syer1 = new String[mohonPihakList.size()];
//                    syer2 = new String[mohonPihakList.size()];
//                }
//            }


//            if (mohonPihakList != null) {
//                for (int i = 0; i < mohonPihakList.size(); i++) {
//                    PermohonanPihak pp = mohonPihakList.get(i);
//                    syer1[i] = String.valueOf(pp.getSyerPembilang());
//                    syer2[i] = String.valueOf(pp.getSyerPenyebut());
//                }
//            }
        }
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

    public String getAlamat5() {
        return alamat5;
    }

    public void setAlamat5(String alamat5) {
        this.alamat5 = alamat5;
    }

    public String getAlamat6() {
        return alamat6;
    }

    public void setAlamat6(String alamat6) {
        this.alamat6 = alamat6;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getNokp() {
        return nokp;
    }

    public void setNokp(String nokp) {
        this.nokp = nokp;
    }

    public PermohonanPihakKemaskini getMohonPihakKemaskini() {
        return mohonPihakKemaskini;
    }

    public void setMohonPihakKemaskini(PermohonanPihakKemaskini mohonPihakKemaskini) {
        this.mohonPihakKemaskini = mohonPihakKemaskini;
    }

    public List<PermohonanPihakKemaskini> getMohonPihakKemaskiniList() {
        return mohonPihakKemaskiniList;
    }

    public void setMohonPihakKemaskiniList(List<PermohonanPihakKemaskini> mohonPihakKemaskiniList) {
        this.mohonPihakKemaskiniList = mohonPihakKemaskiniList;
    }

    public Permohonan getP() {
        return p;
    }

    public void setP(Permohonan p) {
        this.p = p;
    }

    public List<HakmilikPihakBerkepentingan> getPihakKepentinganList() {
        return pihakKepentinganList;
    }

    public void setPihakKepentinganList(List<HakmilikPihakBerkepentingan> pihakKepentinganList) {
        this.pihakKepentinganList = pihakKepentinganList;
    }

    public Pihak getPihak() {
        return pihak;
    }

    public void setPihak(Pihak pihak) {
        this.pihak = pihak;
    }

    public List<PermohonanPihak> getMohonPihakList() {
        return mohonPihakList;
    }

    public void setMohonPihakList(List<PermohonanPihak> mohonPihakList) {
        this.mohonPihakList = mohonPihakList;
    }

    public PermohonanPihak getPermohonanPihak() {
        return permohonanPihak;
    }

    public void setPermohonanPihak(PermohonanPihak permohonanPihak) {
        this.permohonanPihak = permohonanPihak;
    }

    public List<Pemohon> getPemohonList() {
        return pemohonList;
    }

    public void setPemohonList(List<Pemohon> pemohonList) {
        this.pemohonList = pemohonList;
    }

    public String[] getSyer1() {
        return syer1;
    }

    public void setSyer1(String[] syer1) {
        this.syer1 = syer1;
    }

    public String[] getSyer2() {
        return syer2;
    }

    public void setSyer2(String[] syer2) {
        this.syer2 = syer2;
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

    public List<PermohonanAtasPihakBerkepentingan> getMapList() {
        return mapList;
    }

    public void setMapList(List<PermohonanAtasPihakBerkepentingan> mapList) {
        this.mapList = mapList;
    }

    public boolean isTambahCawFlag() {
        return tambahCawFlag;
    }

    public void setTambahCawFlag(boolean tambahCawFlag) {
        this.tambahCawFlag = tambahCawFlag;
    }

    public PihakCawangan getPihakCawangan() {
        return pihakCawangan;
    }

    public void setPihakCawangan(PihakCawangan pihakCawangan) {
        this.pihakCawangan = pihakCawangan;
    }

    public List<HakmilikPihakBerkepentingan> getOthersPihakList() {
        return othersPihakList;
    }

    public void setOthersPihakList(List<HakmilikPihakBerkepentingan> othersPihakList) {
        this.othersPihakList = othersPihakList;
    }

    public String getIdCawangan() {
        return idCawangan;
    }

    public void setIdCawangan(String idCawangan) {
        this.idCawangan = idCawangan;
    }

    public List<PihakCawangan> getCawanganList() {
        return cawanganList;
    }

    public void setCawanganList(List<PihakCawangan> cawanganList) {
        this.cawanganList = cawanganList;
    }
    public List<HakmilikPihakBerkepentingan> getPihakKepentinganList2() {
        return pihakKepentinganList2;
    }

    public void setPihakKepentinganList2(List<HakmilikPihakBerkepentingan> pihakKepentinganList2) {
        this.pihakKepentinganList2 = pihakKepentinganList2;
    }

    public List<PermohonanPihak> getSenaraiPemohonBerangkai() {
        return senaraiPemohonBerangkai;
    }

    public void setSenaraiPemohonBerangkai(List<PermohonanPihak> senaraiPemohonBerangkai) {
        this.senaraiPemohonBerangkai = senaraiPemohonBerangkai;
    }

    public List<HakmilikPermohonan> getHakmilikPermohonanList2() {
        return hakmilikPermohonanList2;
    }

    public void setHakmilikPermohonanList2(List<HakmilikPermohonan> hakmilikPermohonanList2) {
        this.hakmilikPermohonanList2 = hakmilikPermohonanList2;
    }

    /**
     * @return the cawangan
     */
    public KodCawangan getCawangan() {
        return cawangan;
    }

    /**
     * @param cawangan the cawangan to set
     */
    public void setCawangan(KodCawangan cawangan) {
        this.cawangan = cawangan;
    }
     public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public ArrayList getList2() {
        return list2;
    }

    public void setList2(ArrayList list2) {
        this.list2 = list2;
    }

      public List<HakmilikPihakBerkepentingan> getPemilik() {
        return pemilik;
    }

    public void setPemilik(List<HakmilikPihakBerkepentingan> pemilik) {
        this.pemilik = pemilik;
    }

     public List<HakmilikPihakBerkepentingan> getSelainpemilik() {
        return selainpemilik;
    }

    public void setSelainpemilik(List<HakmilikPihakBerkepentingan> selainpemilik) {
        this.selainpemilik = selainpemilik;
    }

    public String getIdPihak() {
        return idPihak;
    }

    public void setIdPihak(String idPihak) {
        this.idPihak = idPihak;
    }


}
