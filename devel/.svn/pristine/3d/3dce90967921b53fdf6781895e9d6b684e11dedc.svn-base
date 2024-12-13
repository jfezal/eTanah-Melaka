/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package etanah.view.stripes.hasil;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.dao.HakmilikPermohonanDAO;
import etanah.dao.HakmilikPihakBerkepentinganDAO;
import etanah.dao.KodUOMDAO;
import etanah.dao.PemohonDAO;
import etanah.dao.PenggunaDAO;
import etanah.dao.PermohonanAktivitiDAO;
import etanah.dao.PermohonanDAO;
import etanah.model.Hakmilik;
import etanah.model.HakmilikPermohonan;
import etanah.model.HakmilikPihakBerkepentingan;
import etanah.model.Pemohon;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.model.PermohonanAktiviti;
import etanah.view.etanahActionBeanContext;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author abu.mansur
 */
@UrlBinding("/common/maklumat_hakmilik_tambahan_single")
public class MaklumatHakmilikTambahSingleActionBean extends AbleActionBean {
    private static final Logger LOG = Logger.getLogger(MaklumatHakmilikTambahSingleActionBean.class);
    private static final boolean IS_LOG_DEBUG = LOG.isDebugEnabled();

    @Inject
    PermohonanDAO permohonanDAO;
    private Permohonan permohonan;

    @Inject
    PenggunaDAO penggunaDAO;

    @Inject
    PermohonanAktivitiDAO permohonanAktivitiDAO;
    private PermohonanAktiviti permohonanAktiviti;
    private List<PermohonanAktiviti> senaraiPermohonanAktiviti;

    @Inject
    HakmilikPermohonanDAO hakmilikPermohonanDAO;
    List<HakmilikPermohonan> senaraiHakmilikPermohonan;

    @Inject
    PemohonDAO pemohonDAO;
    private List<Hakmilik> senaraiHakmilik;
    @Inject
    HakmilikPihakBerkepentinganDAO hakmilikPihakDAO;
    @Inject
    KodUOMDAO kodUnitLuasDAO;

    @Inject
    RemisyenManager manager;
    private String display;
    private String dateMula;
    private String tempoh;
    private String kodLuas;
    private String negeri;
    private String syaratTanam;
    private String tahunMulaREMTS;
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;
    @Inject
    etanah.Configuration conf;

    @DefaultHandler
    public Resolution showForm(){
        return new JSP("hasil/maklumat_hakmilik_tambahan_single.jsp").addParameter("tab", "true");
    }
	
    public Resolution showForm2(){
        display = "display:none;";
        return new JSP("hasil/maklumat_hakmilik_tambahan_single.jsp").addParameter("tab", "true");
    }
	
    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate(){
        LOG.info("rehydrate:start");
        String valueCompare = "tanam";
        String idPermohonan = (String)getContext().getRequest().getSession().getAttribute("idPermohonan");
        if(idPermohonan != null){
                permohonanAktiviti = permohonanAktivitiDAO.findById(idPermohonan);
                try{
                    this.setDateMula(sdf.format(permohonanAktiviti.getTarikhMula()));
                }catch(NullPointerException nex){
                    LOG.error("DateMula :"+nex);
                }
                permohonan = permohonanDAO.findById(idPermohonan);
                String[] name = {"permohonan"};
                Object[] value = {permohonan};
                senaraiHakmilikPermohonan = hakmilikPermohonanDAO.findByEqualCriterias(name, value, null);
                Hakmilik hakmilikPohon = senaraiHakmilikPermohonan.get(0).getHakmilik();
                if(permohonanAktiviti == null){
                    permohonanAktiviti = new PermohonanAktiviti();                    
                    LOG.debug("senaraiHakmilikPermohonan :"+senaraiHakmilikPermohonan.size());
                    BigDecimal luas = hakmilikPohon.getLuas();
                    LOG.debug("luas :"+luas);
                    if("M".equals(hakmilikPohon.getKodUnitLuas().getKod()))
                        luas = luas.divide(new BigDecimal(10000),8,BigDecimal.ROUND_UP);
                    permohonanAktiviti.setLuasTerlibat(luas);
                    kodLuas = "H"; // H = for Hektar
                }else{
                    kodLuas = permohonanAktiviti.getKodUnitLuas().getKod();
                }

                // check hakmilik dipohon adalah ditanam kelapa sawit atau pokok getah
                if(hakmilikPohon.getSyaratNyata().getSyarat().contains("sawit"))
                    syaratTanam = "sawit";
                else if(hakmilikPohon.getSyaratNyata().getSyarat().contains("getah"))
                    syaratTanam = "getah";

                // senaraikan hakmilik lain selain yang di pohon oleh pemohon
                List<Pemohon> listPemohon = pemohonDAO.findByEqualCriterias(name, value, null);
                senaraiHakmilik = new ArrayList<Hakmilik>();
                for (Pemohon pemohon : listPemohon) {
                    List<HakmilikPihakBerkepentingan> listHakmilikPihak = new ArrayList<HakmilikPihakBerkepentingan>();
                    String[] tname = {"pihak"};
                    Object[] tvalue={pemohon.getPihak()};
                    listHakmilikPihak = hakmilikPihakDAO.findByEqualCriterias(tname, tvalue, null);
//                    LOG.debug("listHakmilikPihak.size :"+listHakmilikPihak.size());
                    try{
                        for (HakmilikPihakBerkepentingan hpb : listHakmilikPihak) {
    //                        LOG.debug("hpb idHakmilik :"+hpb.getHakmilik().getIdHakmilik()+", syarat nyata :"+hpb.getHakmilik().getSyaratNyata().getSyarat());

                            if((hpb.getHakmilik().getSyaratNyata().getSyarat()).contains(valueCompare) &&
                                    !StringUtils.equals(hpb.getHakmilik().getIdHakmilik(), hakmilikPohon.getIdHakmilik())){
                                senaraiHakmilik.add(hpb.getHakmilik());
                            }
                        }
                    }catch(Exception ex){
                        LOG.error(ex);
                        ex.printStackTrace(); // for development
                    }
                }
                if("04".equals(conf.getProperty("kodNegeri"))){
                    negeri = "melaka";
                }
                // utk display tempoh pengurangan
                if(permohonan.getNilaiKeputusan() != null)
                    tempoh = permohonan.getNilaiKeputusan().toString();
                if(permohonan.getCatatan() != null)
                    tahunMulaREMTS = permohonan.getCatatan();
                
        }
        LOG.info("rehydrate:finish");
    }

    public Resolution saveOrUpdate(){
        if("04".equals(conf.getProperty("kodNegeri"))){
                    negeri = "melaka";
            }

        boolean check = false;
        LOG.info("saveOrUpdate:start");
        String result = null;
        Pengguna peng = (Pengguna) context.getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);

//        KodUOM unitLuas = new KodUOM();
//        unitLuas.setKod("H");

        String idPermohonan = (String)getContext().getRequest().getSession().getAttribute("idPermohonan");

        permohonanAktiviti.setIdPermohonan(idPermohonan);
//        permohonanAktiviti.setKodUnitLuas(unitLuas);
        if("melaka".equals(negeri)){
            if(permohonanAktiviti.getLuasTerlibat() == null || kodLuas == null || tempoh == null)
                check = true;
        }else{
            if(permohonanAktiviti.getLuasTerlibat() == null || kodLuas == null)
                check = true;
        }

        if(check)// || dateMula == null)
            addSimpleError("Sila isi ruang yang bertanda (*).");
        else{
            try {
                permohonanAktiviti.setTarikhMula(sdf.parse(dateMula));
            } catch (ParseException ex) {
                LOG.error("permohonanAktiviti.setTarikhMula(sdf.parse(dateMula)) ex :"+ex);
            }
            permohonanAktiviti.setKodUnitLuas(kodUnitLuasDAO.findById(kodLuas));
            result = manager.saveOrUpdate(permohonanAktiviti, idPermohonan, peng);
            // utk simpan tempoh pengurangan dlm tahun
            if(StringUtils.isNotBlank(tempoh)){
                LOG.debug("tahunMulaREMTS :"+tahunMulaREMTS);
                permohonan.setCatatan(tahunMulaREMTS);
                permohonan.setNilaiKeputusan(new BigDecimal(tempoh));
                manager.save(permohonan, peng);
            }

            if("save".equals(result)){
                addSimpleMessage("Maklumat Telah Berjaya Disimpan..");
                LOG.info("Maklumat Telah Berjaya Disimpan.."+permohonanAktiviti.getIdPermohonan());
            }else if("update".equals(result)){
                addSimpleMessage("Maklumat Telah Berjaya Dikemaskini..");
                LOG.info("Maklumat Telah Berjaya Dikemaskini..");
            }else{
                addSimpleError("Sistem Tergendala Sementara. Harap Maklum..");
                LOG.warn("Sistem Tergendala Sementara. Harap Maklum..");
            }
        }
        
        LOG.info("saveOrUpdate:finish");
        return new JSP("hasil/maklumat_hakmilik_tambahan_single.jsp").addParameter("tab", "true");
    }

    public List getSenaraiKodUOM() {
        Session s = sessionProvider.get();
        Query q = s.createQuery("from KodUOM u where u.kod in (:kod1,:kod2)");
        q.setString("kod1", "H"); //H = Hektar
        if("04".equals(conf.getProperty("kodNegeri")))
            q.setString("kod2", "M"); //M = Meter Persegi
        else
            q.setString("kod2", "E"); //E = Ekar
        return q.list();
    }

    public Permohonan getPermohonan() {
        return permohonan;
    }

//    //not sure by fikri
//    @ValidationMethod(on = "saveOrUpdate")
//    public void validateField(ValidationErrors errors) {
//        if ((permohonanAktiviti.getLuasTerlibat() == null) || (this.dateMula == null) || (permohonanAktiviti.getTahunSebelum() == null) )
//            errors.add(" ", new SimpleError("Sila Masukkan Nilai Pada Ruangan Bertanda ( </font color=\"red\">*</font> )"));
//    }

    public void setPermohonan(Permohonan permohonan) {
        this.permohonan = permohonan;
    }

    public PermohonanAktiviti getPermohonanAktiviti() {
        return permohonanAktiviti;
    }

    public void setPermohonanAktiviti(PermohonanAktiviti permohonanAktiviti) {
        this.permohonanAktiviti = permohonanAktiviti;
    }

    public List<PermohonanAktiviti> getSenaraiPermohonanAktiviti() {
        return senaraiPermohonanAktiviti;
    }

    public void setSenaraiPermohonanAktiviti(List<PermohonanAktiviti> senaraiPermohonanAktiviti) {
        this.senaraiPermohonanAktiviti = senaraiPermohonanAktiviti;
    }

    public String getDisplay() {
        return display;
    }

    public void setDisplay(String display) {
        this.display = display;
    }

    public String getDateMula() {
        return dateMula;
    }

    public void setDateMula(String dateMula) {
        this.dateMula = dateMula;
    }

    public List<HakmilikPermohonan> getSenaraiHakmilikPermohonan() {
        return senaraiHakmilikPermohonan;
    }

    public void setSenaraiHakmilikPermohonan(List<HakmilikPermohonan> senaraiHakmilikPermohonan) {
        this.senaraiHakmilikPermohonan = senaraiHakmilikPermohonan;
    }

    public List<Hakmilik> getSenaraiHakmilik() {
        return senaraiHakmilik;
    }

    public void setSenaraiHakmilik(List<Hakmilik> senaraiHakmilik) {
        this.senaraiHakmilik = senaraiHakmilik;
    }

    public String getTempoh() {
        return tempoh;
    }

    public void setTempoh(String tempoh) {
        this.tempoh = tempoh;
    }

    public String getKodLuas() {
        return kodLuas;
    }

    public void setKodLuas(String kodLuas) {
        this.kodLuas = kodLuas;
    }

    public String getNegeri() {
        return negeri;
    }

    public void setNegeri(String negeri) {
        this.negeri = negeri;
    }

    public String getSyaratTanam() {
        return syaratTanam;
    }

    public void setSyaratTanam(String syaratTanam) {
        this.syaratTanam = syaratTanam;
    }

    public String getTahunMulaREMTS() {
        return tahunMulaREMTS;
    }

    public void setTahunMulaREMTS(String tahunMulaREMTS) {
        this.tahunMulaREMTS = tahunMulaREMTS;
    }
}
