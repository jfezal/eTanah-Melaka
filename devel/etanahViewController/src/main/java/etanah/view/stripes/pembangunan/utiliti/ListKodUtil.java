/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.stripes.pembangunan.utiliti;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.dao.KodAgensiDAO;
import etanah.dao.KodDUNDAO;
import etanah.dao.KodDaerahDAO;
import etanah.dao.KodGelaranDAO;
import etanah.dao.KodKawasanParlimenDAO;
import etanah.dao.KodNegeriDAO;
import etanah.model.InfoAudit;
import etanah.model.KodAgensi;
import etanah.model.KodDUN;
import etanah.model.KodDaerah;
import etanah.model.KodGelaran;
import etanah.model.KodKategoriAgensi;
import etanah.model.KodKawasanParlimen;
import etanah.model.KodKementerian;
import etanah.model.KodNegeri;
import etanah.model.Pengguna;
import etanah.service.PembangunanService;
import etanah.view.etanahActionBeanContext;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import net.sourceforge.stripes.action.ForwardResolution;
import org.apache.commons.lang.math.NumberUtils;
import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import com.google.inject.Inject;

/**
 *
 * @author khairul.hazwan
 */
@UrlBinding("/pembangunan/utiliti/kodlist")
public class ListKodUtil extends AbleActionBean {

    private static final Logger LOG = Logger.getLogger(ListKodUtil.class);
    private String kod;
    private String nama;
    private String kodKawasanParlimen;
    private String aktif;
    private String diMasukOleh;
    private String diKemaskiniOleh;
    boolean dataFlag = false;
    private List<KodKawasanParlimen> kodParimen;
    private List<KodAgensi> kodAgensi;
    private List<KodDUN> kodDun;
    private KodAgensi kodAgency;
    private KodDUN kodDun1;
    private KodKawasanParlimen kodKawasanParlimen1;
    @Inject
    KodKawasanParlimenDAO kodKawasanParlimenDAO;
    @Inject
    KodAgensiDAO kodAgensiDAO;
    @Inject
    KodDUNDAO kodDUNDAO;
    private String kD;
    private String kG;
    private String alamat1;
    private String alamat2;
    private String alamat3;
    private String alamat4;
    private String poskod;
    private String noTelefon1;
    private String noTelefon2;
    private String emel;
    private List<KodDaerah> DL;
    @Inject
    KodDaerahDAO kodDaerahDAO;
    private List<KodGelaran> GL;
    @Inject
    KodGelaranDAO kodGelaranDAO;
    boolean rowData;
    private String nameTable;
    private String ntab;
    private Pengguna pengguna;
    @Inject
    PembangunanService pbService;
    private int kod_kkp;
    private int kod_kdn;
    private int kod_agn;
    private String negeri;
    private String agency;
    private String dun;

    @DefaultHandler
    public Resolution showForm() {
        rowData = false;
        return new JSP("/pembangunan/utiliti/list_kod.jsp");
    }

    public Resolution pilihKod() {
        rowData = true;
        if (nameTable == null) {
            addSimpleError("Sila pilih jadual di bawah");
            return showForm();
        } else {
            if (nameTable.equals("ADUN")) {
                kodAgensi = pbService.findkodAgency();
            }
            if (nameTable.equals("KOD_AGENSI")) {
                //kodAgensi = kodAgensiDAO.findAll();
                kodAgensi = pbService.findkodAgency2();
            }
            if (nameTable.equals("KOD_DUN")) {
                kodDun = kodDUNDAO.findAll();
            }
            if (nameTable.equals("KOD_KWS_PARLIMEN")) {
                kodParimen = kodKawasanParlimenDAO.findAll();
            }
        }
        addSimpleMessage("Maklumat telah dijumpai");
        return new ForwardResolution("/WEB-INF/jsp/pembangunan/utiliti/list_kod.jsp");
    }

    public Resolution addKod() {
        nameTable = getContext().getRequest().getParameter("ntab");
        DL = kodDaerahDAO.findAll();
        GL = kodGelaranDAO.findAll();
        kodParimen = kodKawasanParlimenDAO.findAll();
        if (nameTable.equals("ADUN") || nameTable.equals("KOD_AGENSI")) {
            List<KodAgensi> listAgensi = pbService.getSenaraiAgensi();
            Integer palingLatest = sortByKodInInteger(listAgensi);
            kod_agn = palingLatest;
            //kod_agn = Integer.valueOf(pbService.getMaxAgency().getKod());
        }
        if (nameTable.equals("KOD_DUN")) {
            String kdnn = pbService.getMaxDUN().getKod();
            kdnn = kdnn.substring(1);
            kod_kdn = Integer.valueOf(kdnn);
        }
        if (nameTable.equals("KOD_KWS_PARLIMEN")) {
            String kkkp = pbService.getMaxparlimen().getKod();
            kkkp = kkkp.substring(1);
            kod_kkp = Integer.valueOf(kkkp);
        }
        return new JSP("pembangunan/utiliti/add_kod.jsp").addParameter("popup", "true");
    }

    public Resolution tambahRecord() {
        String tname = getContext().getRequest().getParameter("nameTable");
        pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        InfoAudit ia = new InfoAudit();
        Date d = new Date();
        ia.setDimasukOleh(pengguna);
        ia.setTarikhMasuk(d);
        if (tname.equals("ADUN")) {
            if (nama != null && aktif != null && kD != null && kG != null) {
                kD = getContext().getRequest().getParameter("kD");
                kG = getContext().getRequest().getParameter("kG");
                
                List<KodAgensi> listAgensi = pbService.getSenaraiAgensi();
                Integer palingLatest = sortByKodInInteger(listAgensi);
                kod_agn = palingLatest;
                
                //kod_agn = Integer.valueOf(pbService.getMaxAgency().getKod());
                kod_agn++;
                String kodagn = String.valueOf(kod_agn);
                KodAgensi kg = new KodAgensi();
                kg.setKod(kodagn);
                kg.setNama(nama);
                KodDaerah kd = new KodDaerah();
                kd.setKod(kD);
                kg.setKodDaerah(kd);
                KodGelaran kgr = new KodGelaran();
                kgr.setKod(kG);
                kg.setKodGelaran(kgr);
                KodKategoriAgensi kka = new KodKategoriAgensi();
                kka.setKod("ADN");
                kg.setKategoriAgensi(kka);
                KodKementerian kkm = new KodKementerian();
                kkm.setKod(Integer.valueOf(30));
                kg.setKodKementerian(kkm.getKod());
                kg.setInfoAudit(ia);
                kg.setAlamat1(alamat1);
                kg.setAlamat2(alamat2);
                kg.setAlamat3(alamat3);
                kg.setAlamat4(alamat4);
                kg.setEmel(emel);
                kg.setNoTelefon1(noTelefon1);
                kg.setNoTelefon2(noTelefon2);
                kg.setPoskod(poskod);
                KodNegeri kn = new KodNegeri();
                kn.setKod(negeri);
                kg.setKodNegeri(kn);
                char aktif1 = aktif.charAt(0);
                kg.setAktif(aktif1);
                pbService.simpanKodAgency(kg);
//                KodDUN kdn = new KodDUN();
//                kdn = kodDUNDAO.findById(dun);
//                KodAgensi kag = new KodAgensi();
//                kag.setKod(kg.getKod());
//                kdn.setKodAgensi(kag);
//                pbService.simpanKodDun(kdn);
            }
        }
        if (tname.equals("KOD_AGENSI")) {
            if (nama != null && aktif != null) {
                List<KodAgensi> listAgensi = pbService.getSenaraiAgensi();
                Integer palingLatest = sortByKodInInteger(listAgensi);
                kod_agn = palingLatest;
                //kod_agn = Integer.valueOf(pbService.getMaxAgency().getKod());
                kod_agn++;
                String kodagn = String.valueOf(kod_agn);
                KodAgensi kg = new KodAgensi();
                kg.setKod(kodagn);
                kg.setNama(nama);
                char aktif1 = aktif.charAt(0);
                kg.setAktif(aktif1);                
                kg.setInfoAudit(ia);
                kg.setAlamat1(alamat1);
                kg.setAlamat2(alamat2);
                kg.setAlamat3(alamat3);
                kg.setAlamat4(alamat4);
                kg.setEmel(emel);
                kg.setNoTelefon1(noTelefon1);
                kg.setNoTelefon2(noTelefon2);
                kg.setPoskod(poskod);
                KodNegeri kn = new KodNegeri();
                kn.setKod(negeri);
                kg.setKodNegeri(kn);
                pbService.simpanKodAgency(kg);
            }
        }
        if (tname.equals("KOD_DUN")) {
            if (nama != null && aktif != null && kodKawasanParlimen != null) {
                String kdnn = pbService.getMaxDUN().getKod();
                kdnn = kdnn.substring(1);
                kod_kdn = Integer.valueOf(kdnn);
                kod_kdn++;
                KodDUN kd = new KodDUN();
                kd.setKod("N" + String.valueOf(kod_kdn));
                kd.setNama(nama);
                char aktif1 = aktif.charAt(0);
                kd.setAktif(aktif1);
                kd.setInfoAudit(ia);
                KodAgensi ka = new KodAgensi();
                ka.setKod(agency);
                kd.setKodAgensi(ka);
                KodKawasanParlimen kp = new KodKawasanParlimen();
                kp.setKod(kodKawasanParlimen);
                kd.setKodKawasanParlimen(kp);
                pbService.simpanKodDun(kd);
            }
        }
        if (tname.equals("KOD_KWS_PARLIMEN")) {
            if (nama != null && aktif != null) {
                KodKawasanParlimen kkp = new KodKawasanParlimen();
                String kkpn = pbService.getMaxparlimen().getKod();
                kkpn = kkpn.substring(1);
                kod_kkp = Integer.valueOf(kkpn);
                kod_kkp++;
                String kodkkp = String.valueOf(kod_kkp);
                kkp.setKod("P" + kodkkp);
                kkp.setNama(nama);
                char aktif1 = aktif.charAt(0);
                kkp.setAktif(aktif1);
                kkp.setInfoAudit(ia);
                pbService.simpanKodParlimen(kkp);
            }
        }
        refreshPage();
        addSimpleMessage("Maklumat Berjaya Disimpan");
        return new JSP("pembangunan/utiliti/add_kod.jsp").addParameter("popup", "true");
    }

    public Resolution refreshPage() {
        System.out.println("refreshPage : start");
        pilihKod();
        System.out.println("refreshPage : finish");
        return pilihKod();
    }

    public Resolution edit() {       
        DL = kodDaerahDAO.findAll();       
        GL = kodGelaranDAO.findAll();        
        kodParimen = kodKawasanParlimenDAO.findAll();        
        ntab = getContext().getRequest().getParameter("ntab");       
        if (ntab.equals("ADUN")) {
            String kod1 = getContext().getRequest().getParameter("kod");          
            kodAgency = kodAgensiDAO.findById(kod1);          
            kod = kodAgency.getKod();
            nama = kodAgency.getNama();
            aktif = String.valueOf(kodAgency.getAktif());
            diMasukOleh = kodAgency.getInfoAudit().getDimasukOleh().getNama();
            if (kodAgency.getKodDaerah() != null) {
                kD = kodAgency.getKodDaerah().getKod();               
            }
            if (kodAgency.getKodGelaran() != null) {
                kG = kodAgency.getKodGelaran().getKod();              
            }
            alamat1 = kodAgency.getAlamat1();
            alamat2 = kodAgency.getAlamat2();
            alamat3 = kodAgency.getAlamat3();
            alamat4 = kodAgency.getAlamat4();
            poskod = kodAgency.getPoskod();
            negeri = kodAgency.getKodNegeri().getKod();       
            noTelefon1 = kodAgency.getNoTelefon1();
            noTelefon2 = kodAgency.getNoTelefon2();
            emel = kodAgency.getEmel();
        }
        if (ntab.equals("KOD_AGENSI")) {
            String kod1 = getContext().getRequest().getParameter("kod");          
            kodAgency = kodAgensiDAO.findById(kod1);          
            kod = kodAgency.getKod();
            nama = kodAgency.getNama();
            aktif = String.valueOf(kodAgency.getAktif());
            diMasukOleh = kodAgency.getInfoAudit().getDimasukOleh().getNama();
            alamat1 = kodAgency.getAlamat1();
            alamat2 = kodAgency.getAlamat2();
            alamat3 = kodAgency.getAlamat3();
            alamat4 = kodAgency.getAlamat4();
            poskod = kodAgency.getPoskod();
            if (negeri != null){
                negeri = kodAgency.getKodNegeri().getKod();
            }
            noTelefon1 = kodAgency.getNoTelefon1();
            noTelefon2 = kodAgency.getNoTelefon2();
            emel = kodAgency.getEmel();
        }
        if (ntab.equals("KOD_DUN")) {
            String kod1 = getContext().getRequest().getParameter("kdskod");            
            kodDun1 = kodDUNDAO.findById(kod1);           
            kod = kodDun1.getKod();
            nama = kodDun1.getNama();
            if (kodDun1.isAktif() == 'Y') {
                aktif = String.valueOf('Y');               
            } else {
                aktif = String.valueOf('N');               
            }
            diMasukOleh = kodDun1.getInfoAudit().getDimasukOleh().getNama();
            kodKawasanParlimen = kodDun1.getKodKawasanParlimen().getKod();
            agency = kodDun1.getKodAgensi().getKod();
        }
        if (ntab.equals("KOD_KWS_PARLIMEN")) {
            String kod1 = getContext().getRequest().getParameter("kdskod");           
            kodKawasanParlimen1 = kodKawasanParlimenDAO.findById(kod1);            
            kod = kodKawasanParlimen1.getKod();
            nama = kodKawasanParlimen1.getNama();
            if (kodKawasanParlimen1.isAktif() == 'Y') {
                aktif = String.valueOf('Y');               
            } else {
                aktif = String.valueOf('T');               
            }
            diMasukOleh = kodKawasanParlimen1.getInfoAudit().getDimasukOleh().getNama();
        }
        return new JSP("/pembangunan/utiliti/edit_kod2.jsp").addParameter("popup", "true");
    }

    public Resolution update() {       
        pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        ntab = getContext().getRequest().getParameter("ntab");      
        kod = getContext().getRequest().getParameter("kod");       
        if (ntab.equals("ADUN") || ntab.equals("KOD_AGENSI")) {
            kodAgency = kodAgensiDAO.findById(kod);           
            if (kodAgency != null) {
                InfoAudit ia = new InfoAudit();
                ia = kodAgency.getInfoAudit();
                ia.setDiKemaskiniOleh(pengguna);
                ia.setTarikhKemaskini(new java.util.Date());
                kodAgency.setInfoAudit(ia);
                kodAgency.setNama(nama);               
                if(aktif.equals("Y")){
                kodAgency.setAktif('Y');
                }else{
                    kodAgency.setAktif('T');
                }
                kodAgency.setAlamat1(alamat1);
                kodAgency.setAlamat2(alamat2);
                kodAgency.setAlamat3(alamat3);
                kodAgency.setAlamat4(alamat4);
                kodAgency.setEmel(emel);
                kodAgency.setNoTelefon1(noTelefon1);
                kodAgency.setNoTelefon2(noTelefon2);
                kodAgency.setPoskod(poskod);
                if(negeri != null){
                    KodNegeri knr = new KodNegeri();
                    knr.setKod(negeri);
                    kodAgency.setKodNegeri(knr);
                }
                if (kD != null) {                   
                    KodDaerah kd = new KodDaerah();
                    kd.setKod(kD);
                    kodAgency.setKodDaerah(kd);
                }
                if (kG != null) {                   
                    KodGelaran kg = new KodGelaran();
                    kg.setKod(kG);
                    kodAgency.setKodGelaran(kg);
                }
                pbService.simpanKodAgency(kodAgency);
            }
        }
        if (ntab.equals("KOD_DUN")) {
            kodDun1 = kodDUNDAO.findById(kod);            
            if (kodDun1 != null) {
                InfoAudit ia = new InfoAudit();
                ia = kodDun1.getInfoAudit();
                ia.setDiKemaskiniOleh(pengguna);
                ia.setTarikhKemaskini(new java.util.Date());
                kodDun1.setInfoAudit(ia);
                kodDun1.setNama(nama);
                if (kodKawasanParlimen != null) {                   
                    KodKawasanParlimen kp = new KodKawasanParlimen();
                    kp.setKod(kodKawasanParlimen);
                    kodDun1.setKodKawasanParlimen(kp);
                }
                if(agency != null){
                    KodAgensi ka = new KodAgensi();
                    ka.setKod(agency);
                    kodDun1.setKodAgensi(ka);
                }
                pbService.simpanKodDun(kodDun1);                
            }
        }
        if (ntab.equals("KOD_KWS_PARLIMEN")) {
            kodKawasanParlimen1 = kodKawasanParlimenDAO.findById(kod);           
            if (kodKawasanParlimen1 != null) {
                InfoAudit ia = new InfoAudit();
                ia = kodKawasanParlimen1.getInfoAudit();
                ia.setDiKemaskiniOleh(pengguna);
                ia.setTarikhKemaskini(new java.util.Date());
                kodKawasanParlimen1.setNama(nama);
                pbService.simpanKodParlimen(kodKawasanParlimen1);               
            }
        }
        refreshPage();
        addSimpleMessage("Maklumat Telah Berjaya Dikemaskini");
        return new JSP("pembangunan/utiliti/edit_kod2.jsp").addParameter("popup", "true");
    }

    public String getAktif() {
        return aktif;
    }

    public void setAktif(String aktif) {
        this.aktif = aktif;
    }

    public String getDiKemaskiniOleh() {
        return diKemaskiniOleh;
    }

    public void setDiKemaskiniOleh(String diKemaskiniOleh) {
        this.diKemaskiniOleh = diKemaskiniOleh;
    }

    public String getDiMasukOleh() {
        return diMasukOleh;
    }

    public void setDiMasukOleh(String diMasukOleh) {
        this.diMasukOleh = diMasukOleh;
    }

    public String getKod() {
        return kod;
    }

    public void setKod(String kod) {
        this.kod = kod;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public boolean isDataFlag() {
        return dataFlag;
    }

    public void setDataFlag(boolean dataFlag) {
        this.dataFlag = dataFlag;
    }

    public List<KodKawasanParlimen> getKodParimen() {
        return kodParimen;
    }

    public void setKodParimen(List<KodKawasanParlimen> kodParimen) {
        this.kodParimen = kodParimen;
    }

    public String getKodKawasanParlimen() {
        return kodKawasanParlimen;
    }

    public void setKodKawasanParlimen(String kodKawasanParlimen) {
        this.kodKawasanParlimen = kodKawasanParlimen;
    }

    public List<KodAgensi> getKodAgensi() {
        return kodAgensi;
    }

    public void setKodAgensi(List<KodAgensi> kodAgensi) {
        this.kodAgensi = kodAgensi;
    }

    public KodAgensi getKodAgency() {
        return kodAgency;
    }

    public void setKodAgency(KodAgensi kodAgency) {
        this.kodAgency = kodAgency;
    }

    public String getkD() {
        return kD;
    }

    public void setkD(String kD) {
        this.kD = kD;
    }

    public String getkG() {
        return kG;
    }

    public void setkG(String kG) {
        this.kG = kG;
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

    public String getNoTelefon1() {
        return noTelefon1;
    }

    public void setNoTelefon1(String noTelefon1) {
        this.noTelefon1 = noTelefon1;
    }

    public String getNoTelefon2() {
        return noTelefon2;
    }

    public void setNoTelefon2(String noTelefon2) {
        this.noTelefon2 = noTelefon2;
    }

    public String getEmel() {
        return emel;
    }

    public void setEmel(String emel) {
        this.emel = emel;
    }

    public boolean isRowData() {
        return rowData;
    }

    public void setRowData(boolean rowData) {
        this.rowData = rowData;
    }

    public String getNameTable() {
        return nameTable;
    }

    public void setNameTable(String nameTable) {
        this.nameTable = nameTable;
    }

    public String getNtab() {
        return ntab;
    }

    public void setNtab(String ntab) {
        this.ntab = ntab;
    }

    public List<KodDUN> getKodDun() {
        return kodDun;
    }

    public void setKodDun(List<KodDUN> kodDun) {
        this.kodDun = kodDun;
    }

    public List<KodDaerah> getDL() {
        return DL;
    }

    public void setDL(List<KodDaerah> DL) {
        this.DL = DL;
    }

    public List<KodGelaran> getGL() {
        return GL;
    }

    public void setGL(List<KodGelaran> GL) {
        this.GL = GL;
    }

    public int getKod_kkp() {
        return kod_kkp;
    }

    public void setKod_kkp(int kod_kkp) {
        this.kod_kkp = kod_kkp;
    }

    public int getKod_kdn() {
        return kod_kdn;
    }

    public void setKod_kdn(int kod_kdn) {
        this.kod_kdn = kod_kdn;
    }

    public int getKod_agn() {
        return kod_agn;
    }

    public void setKod_agn(int kod_agn) {
        this.kod_agn = kod_agn;
    }

    public String getNegeri() {
        return negeri;
    }

    public void setNegeri(String negeri) {
        this.negeri = negeri;
    }

    public String getAgency() {
        return agency;
    }

    public void setAgency(String agency) {
        this.agency = agency;
    }

    public String getDun() {
        return dun;
    }

    public void setDun(String dun) {
        this.dun = dun;
    }

    private Integer sortByKodInInteger(List<KodAgensi> listAgensi) {
        //KodAgensi returnKodAgensi = new KodAgensi();
        Integer littleVal = 0;
        for(KodAgensi kodAgensi : listAgensi){
            
            if(NumberUtils.isNumber(kodAgensi.getKod())){
                Integer kodVal = Integer.parseInt(kodAgensi.getKod());
                if(kodVal > littleVal){
                    littleVal = kodVal;
                }
            }

        }
        return littleVal;
    }
    
}
