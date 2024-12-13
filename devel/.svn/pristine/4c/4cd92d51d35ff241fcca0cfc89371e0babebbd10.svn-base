/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.daftar;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import oracle.bpel.services.workflow.WorkflowException;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import able.stripes.AbleActionBean;
import com.google.inject.Inject;
import etanah.dao.KodPerserahanDAO;
import etanah.dao.PenggunaDAO;
import etanah.model.KodPenyerah;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.model.CarianHakmilik;
import etanah.model.FasaPermohonan;
import etanah.model.KodPerserahan;
import etanah.service.common.FasaPermohonanService;
import java.util.Date;
import etanah.service.common.PermohonanService;
import etanah.service.common.PgunaService;
import etanah.view.etanahActionBeanContext;
import java.util.Collections;
import java.util.Comparator;
import java.util.Map;

/**
 *
 * @author w.fairul
 */
@UrlBinding("/daftar/senaraiPerserahan")
public class UtilitiSenaraiPerserahan extends AbleActionBean {

    @Inject
    PermohonanService pService;
    @Inject
    KodPerserahanDAO kodSerahDAO;
    @Inject
    PenggunaDAO penggunaDAO;
    @Inject
    FasaPermohonanService fasaPermohonanService;
    @Inject
    PgunaService pgunaService;
    private static final Logger log = Logger.getLogger(UtilitiSenaraiPerserahan.class);
    private SimpleDateFormat sd = new SimpleDateFormat("dd/MM/yyyy");
    private SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
    private List<Permohonan> listValue;
    private List<CarianHakmilik> listValue2;
    private List<FasaPermohonan> listMohonFasa;
    private List<Permohonan> senaraiPermohonan;
    public String fromDate;
    public String untilDate;
    public String date_masuk;
    private String kod_caw;
    private String kod_jab;
    private String kod_serah;
    private Pengguna peng;
    private Permohonan permohonan;
    private List<Map> senaraiCarian = new ArrayList<Map>();
    private List<Map> senaraiMohon = new ArrayList<Map>();
    private List<Map> senaraiMohonDanCarian = new ArrayList<Map>();
    
    
    static final Comparator<Map<String,Object>> DATE_ORDER = new Comparator<Map<String,Object>>() {
        @Override
        public int compare(Map m1, Map m2) {           

          Date date1 = (Date) m1.get("tarikh");
          Date date2 = (Date) m2.get("tarikh");
          
          String tarikh = date1.toString();
          String tarikh2 = date2.toString();
          
          return tarikh.compareTo(tarikh2);
        }
    };

    @DefaultHandler
    public Resolution showForm() throws Exception {

        String message = getContext().getRequest().getParameter("message");
        String error = getContext().getRequest().getParameter("error");
        if (StringUtils.isNotBlank(message)) {
            addSimpleMessage(message);
        }
        if (StringUtils.isNotBlank(error)) {
            addSimpleError(error);
        }
        peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        return new ForwardResolution("/WEB-INF/jsp/daftar/utiliti/senarai_perserahan.jsp");
    }

    public Resolution searchAllPermohonan() throws WorkflowException, ParseException {
        peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        kod_caw = peng.getKodCawangan().getKod();
        if (StringUtils.isNotBlank(kod_serah)) {
            KodPerserahan kodS = new KodPerserahan();
            kodS = kodSerahDAO.findById(kod_serah);
        }

        if (kod_caw == null) {
            addSimpleError("Sila Pilih Pejabat");
            return new ForwardResolution("/WEB-INF/jsp/daftar/utiliti/senarai_perserahan.jsp");
        }
        if (kod_jab == null) {
            addSimpleError("Sila Pilih Jabatan");
            return new ForwardResolution("/WEB-INF/jsp/daftar/utiliti/senarai_perserahan.jsp");
        }

        //Tempoh Perserahan Untuk Sehari
        if (kod_caw != null && kod_jab != null && date_masuk != null) {

            Date dt1 = sd.parse(date_masuk);
            dt1.setHours(0);
            dt1.setMinutes(0);
            dt1.setSeconds(0);
            String d1 = sdf.format(dt1);
            log.info(d1);

            Date dt2 = sd.parse(date_masuk);
            dt2.setHours(23);
            dt2.setMinutes(59);
            dt2.setSeconds(59);

            String d2 = sdf.format(dt2);
            log.info(d2);

            if (kod_serah == null) {
                
                senaraiMohon = pService.findMohonCawAndDateAll(kod_caw, kod_jab, kod_serah, d1, d2);
                senaraiCarian = pService.findCarianByCawAndDateAll(kod_caw, kod_jab, kod_serah, d1, d2);
                
                 for (Map map : senaraiMohon) {
                     
                     Object idPermohonan = map.get("idPermohonan");
                     String kerani = fasaPermohonanService.findKeraniKemasukan(idPermohonan.toString());
                     if(kerani != null) 
                     {
                         String namaKerani = pgunaService.findNamaPengguna(kerani);
                         map.put("dimasuk", namaKerani);
                     }
                     senaraiMohonDanCarian.add(map);
                 }
                 senaraiMohonDanCarian.addAll(senaraiCarian);
            }

            if (kod_serah != null) {
                
                if ("CS".equals(kod_serah) || "CR".equals(kod_serah)) {
                    listValue2 = pService.findMohonByCawAndDateCarianHakmilik(kod_caw, kod_jab, kod_serah, d1, d2);
                } else {
                    listValue = pService.findMohonByCawAndDatePermohonan(kod_caw, kod_jab, kod_serah, d1, d2);

                    senaraiPermohonan = new ArrayList<Permohonan>();

                    for (Permohonan p : listValue) {
                        listMohonFasa = p.getSenaraiFasa();
                        if (listMohonFasa != null) {
                            for (FasaPermohonan fp : listMohonFasa) {
                                if (fp.getIdAliran().contains("kemasukan")) {
                                    Pengguna pengguna = penggunaDAO.findById(fp.getIdPengguna());
                                    p.getInfoAudit().setDimasukOleh(pengguna);
                                }
                            }

                            senaraiPermohonan.add(p);
                        } else {
                            senaraiPermohonan.add(p);
                        }

                    }

                }
            }


        } else if (kod_caw != null && kod_jab != null && untilDate != null && fromDate != null) {
            //TODO :search from date and until date

            //coding before edit
            //Date d1 = sd.parse(fromDate);
            //Date d2 = sd.parse(untilDate);            

            //listValue = pService.findMohonByCawAndDate(kod_caw, kod_jab, d1, d2);

            //edited
            Date dd1 = sd.parse(fromDate);
            dd1.setHours(0);
            dd1.setMinutes(0);
            dd1.setSeconds(0);
            String d1 = sdf.format(dd1);
            log.info(d1);

            Date dd2 = sd.parse(untilDate);
            dd2.setHours(23);
            dd2.setMinutes(59);
            dd2.setSeconds(59);
            String d2 = sdf.format(dd2);
            log.info(d2);

            if (kod_serah == null) {
                
                senaraiMohon = pService.findMohonCawAndDateAll(kod_caw, kod_jab, kod_serah, d1, d2);
                senaraiCarian = pService.findCarianByCawAndDateAll(kod_caw, kod_jab, kod_serah, d1, d2);
                
                 for (Map map : senaraiMohon) {
                     
                     Object idPermohonan = map.get("idPermohonan");
                     String kerani = fasaPermohonanService.findKeraniKemasukan(idPermohonan.toString());
                     if(kerani != null) 
                     {
                         String namaKerani = pgunaService.findNamaPengguna(kerani);
                         map.put("dimasuk", namaKerani);
                     }
                         
                     senaraiMohonDanCarian.add(map);
                 }
                 senaraiMohonDanCarian.addAll(senaraiCarian);
            }
            if (kod_serah != null) {
                
                if ("CS".equals(kod_serah) || "CR".equals(kod_serah)) {
                    listValue2 = pService.findMohonByCawAndDateCarianHakmilik(kod_caw, kod_jab, kod_serah, d1, d2);
                } else {
                    listValue = pService.findMohonByCawAndDatePermohonan(kod_caw, kod_jab, kod_serah, d1, d2);

                    senaraiPermohonan = new ArrayList<Permohonan>();

                    for (Permohonan p : listValue) {
                        listMohonFasa = p.getSenaraiFasa();
                        if (listMohonFasa != null) {
                            for (FasaPermohonan fp : listMohonFasa) {
                                if (fp.getIdAliran().contains("kemasukan")) {
                                    Pengguna pengguna = penggunaDAO.findById(fp.getIdPengguna());
                                    p.getInfoAudit().setDimasukOleh(pengguna);
                                }
                            }

                            senaraiPermohonan.add(p);
                        } else {
                            senaraiPermohonan.add(p);
                        }

                    }

                }
            }
        }
//        if (!senaraiMohonDanCarian.isEmpty()) 
//            {
//                Collections.sort(senaraiMohonDanCarian, DATE_ORDER);
//            }
        return new ForwardResolution("/WEB-INF/jsp/daftar/utiliti/senarai_perserahan.jsp");

    }

    public List<CarianHakmilik> getListValue2() {
        return listValue2;
    }

    public void setListValue2(List<CarianHakmilik> listValue2) {
        this.listValue2 = listValue2;
    }

    public List<Permohonan> getListValue() {
        return listValue;
    }

    public void setListValue(List<Permohonan> listValue) {
        this.listValue = listValue;
    }

    public String getFromDate() {
        return fromDate;
    }

    public void setFromDate(String fromDate) {
        this.fromDate = fromDate;
    }

    public String getUntilDate() {
        return untilDate;
    }

    public void setUntilDate(String untilDate) {
        this.untilDate = untilDate;
    }

    public String getKod_serah() {
        return kod_serah;
    }

    public void setKod_serah(String kod_serah) {
        this.kod_serah = kod_serah;
    }

    public String getKod_caw() {
        return kod_caw;
    }

    public void setKod_caw(String kod_caw) {
        this.kod_caw = kod_caw;
    }

    public String getKod_jab() {
        return kod_jab;
    }

    public void setKod_jab(String kod_jab) {
        this.kod_jab = kod_jab;
    }

    public String getDate_masuk() {
        return date_masuk;
    }

    public void setDate_masuk(String date_masuk) {
        this.date_masuk = date_masuk;
    }

    public Pengguna getPeng() {
        return peng;
    }

    public void setPeng(Pengguna peng) {
        this.peng = peng;
    }

    public List<FasaPermohonan> getListMohonFasa() {
        return listMohonFasa;
    }

    public void setListMohonFasa(List<FasaPermohonan> listMohonFasa) {
        this.listMohonFasa = listMohonFasa;
    }

    public List<Permohonan> getSenaraiPermohonan() {
        return senaraiPermohonan;
    }

    public void setSenaraiPermohonan(List<Permohonan> senaraiPermohonan) {
        this.senaraiPermohonan = senaraiPermohonan;
    }

    public List<Map> getSenaraiCarian() {
        return senaraiCarian;
    }

    public void setSenaraiCarian(List<Map> senaraiCarian) {
        this.senaraiCarian = senaraiCarian;
    }

    public List<Map> getSenaraiMohon() {
        return senaraiMohon;
    }

    public void setSenaraiMohon(List<Map> senaraiMohon) {
        this.senaraiMohon = senaraiMohon;
    }

    public List<Map> getSenaraiMohonDanCarian() {
        return senaraiMohonDanCarian;
    }

    public void setSenaraiMohonDanCarian(List<Map> senaraiMohonDanCarian) {
        this.senaraiMohonDanCarian = senaraiMohonDanCarian;
    }
    
}
