/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.stripes.hasil;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.dao.*;
import etanah.model.*;
import etanah.model.DasarTuntutanCukaiHakmilik;
import etanah.model.DasarTuntutanCukai;
import etanah.model.DasarTuntutanNotis;
import etanah.service.PermohonanSupayaBantahanService;
import etanah.report.ReportUtil;
import etanah.view.etanahActionBeanContext;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.StreamingResolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.action.Wizard;
import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author khadijah
 */
//@Wizard(startEvents = {"showForm", "popup"})
@UrlBinding("/hasil/hapus_kira")
public class HapusKiraActionBean extends AbleActionBean {

    private static final Logger LOG = Logger.getLogger(HapusKiraActionBean.class);
    private static final String DEFAULT_VIEW = "/WEB-INF/jsp/hasil/hapus_kira.jsp";
    private static final String POPUP_VIEW = "hasil/hapus_kira_1.jsp";
    private String negeri;
    private String idDasar;
    private Hakmilik hakmilik;
    private DasarTuntutanCukaiHakmilik dasarTuntutanCukaiHakmilik;
    private DasarTuntutanCukai dasarTuntutanCukai;
    private DasarTuntutanNotis dasarTuntutanNotis;
    private DasarTuntutanCukaiHakmilikDAO dasarTuntutanCukaiHakmilikDAO;
    private DasarTuntutanCukaiDAO dasarTuntuanCukaiDAO;
    private DasarTuntutanNotisDAO dasarTuntutanNotisDAO;
    private HakmilikDAO hakmilikDAO;
    private String idDasarHakmilik;
    private String radio = "";
    private DasarTuntutanCukaiHakmilik dasarTuntutanCukaiHakmilik2;
    private boolean flag;
    private static String kodNegeri;
    private List<DasarTuntutanCukaiHakmilik> dasarList = new ArrayList<DasarTuntutanCukaiHakmilik>();

    @Inject
    public HapusKiraActionBean(HakmilikDAO hakmilikDAO, DasarTuntutanCukaiHakmilikDAO dasarTuntutanCukaiHakmilikDAO,
            DasarTuntutanCukaiDAO dasarTuntuanCukaiDAO, DasarTuntutanNotisDAO dasarTuntutanNotisDAO) {

        this.hakmilikDAO = hakmilikDAO;
        this.dasarTuntutanCukaiHakmilikDAO = dasarTuntutanCukaiHakmilikDAO;
        this.dasarTuntuanCukaiDAO = dasarTuntuanCukaiDAO;
        this.dasarTuntutanNotisDAO = dasarTuntutanNotisDAO;
    }
    @Inject
    private ReportUtil reportUtil;
    @Inject
    private PermohonanSupayaBantahanService permohonanSupayaBantahanService;
    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;
    @Inject
    etanah.Configuration conf;

     @DefaultHandler
    public Resolution showForm() {
        if ("04".equals(conf.getProperty("kodNegeri"))) {
            kodNegeri = "melaka";
        } else {
            kodNegeri = "negeri";
        }
        return new ForwardResolution(DEFAULT_VIEW);
    }

    public Resolution search() {
        negeri = conf.getProperty("kodNegeri");
        if (hakmilik != null) {
            LOG.info("idHakmilik : " + hakmilik.getIdHakmilik());
            dasarList = searchByIDHakmilik(hakmilik.getIdHakmilik());
        } else if (dasarTuntutanCukai != null) {
            LOG.info("idDasar : " + dasarTuntutanCukai.getIdDasar());
            dasarList = searchByNoRujukan();
        }
        else {
            dasarList = dasarTuntutanCukaiHakmilikDAO.findAll();
        }

        flag = true;
        return new ForwardResolution(DEFAULT_VIEW);
    }

    public List<DasarTuntutanCukaiHakmilik> searchByIDHakmilik(String idHakmilik) {

        LOG.info("idHakmilik bwh : ");
        List<DasarTuntutanCukaiHakmilik> list = new ArrayList<DasarTuntutanCukaiHakmilik>();
        hakmilik = hakmilikDAO.findById(idHakmilik);
        DasarTuntutanCukaiHakmilik dtch = new DasarTuntutanCukaiHakmilik();
        LOG.info("idHakmilik : " + hakmilik.getIdHakmilik());
        Session s = sessionProvider.get();
        String sql = "SELECT a from etanah.model.DasarTuntutanCukaiHakmilik a where a.hakmilik.idHakmilik =:hm";
        Query q = s.createQuery(sql);
        q.setString("hm", hakmilik.getIdHakmilik());
        String rst = "";
        LOG.info("idHakmilik  : ");
        List<DasarTuntutanCukaiHakmilik> dList = q.list();
        LOG.info("idHakmilik : " + hakmilik.getIdHakmilik());
        LOG.info("list.size() : " + dList.size());
        for (DasarTuntutanCukaiHakmilik dt : dList) {
            if (dt.getHakmilik().getIdHakmilik() != null) {
                if (!dt.getHakmilik().getIdHakmilik().equals(rst)) {
                    list.add(dt);
                    rst = dt.getHakmilik().getIdHakmilik();
                }
            }
        }
        return list;
    }

    public List<DasarTuntutanCukaiHakmilik> searchByNoRujukan() {

        LOG.info("idDasar : ");

        List<DasarTuntutanCukaiHakmilik> list = new ArrayList<DasarTuntutanCukaiHakmilik>();
        String resit = "";

        Session s = sessionProvider.get();

        String sqlDasar = "SELECT d from etanah.model.DasarTuntutanCukaiHakmilik d where d.dasarTuntutanCukai.idDasar =:dsr";
        Query qDasar = s.createQuery(sqlDasar);
        qDasar.setString("dsr", dasarTuntutanCukai.getIdDasar());
//        DasarTuntutanCukaiHakmilik dtch = (DasarTuntutanCukaiHakmilik) qDasar.uniqueResult();



        String rst = "";
        List<DasarTuntutanCukaiHakmilik> dList = qDasar.list();
        LOG.info("idDasar : ");
        LOG.info("list.size() : " + dList.size());
        for (DasarTuntutanCukaiHakmilik dt : dList) {
            if (dt.getDasarTuntutanCukai().getIdDasar() != null) {
                if (!dt.getDasarTuntutanCukai().getIdDasar().equals(rst)) {
                    list.add(dt);
                    rst = dt.getDasarTuntutanCukai().getIdDasar();
                }
            }
        }
        return list;
    }


    public Resolution seterus() {
        LOG.info("Radio : " + radio);
        radio = getContext().getRequest().getParameter("radio");
        LOG.info("Seterusnya");

         dasarTuntutanCukaiHakmilik = permohonanSupayaBantahanService.getDasarTuntutanCukaiHakmilikByIdDasar(radio);

        if (dasarTuntutanCukaiHakmilik != null) {
            LOG.info("dasarTuntutanCukaiHakmilik != null");

            dasarTuntutanCukaiHakmilik.getCatatanHapus1();
             dasarTuntutanCukaiHakmilik.getCatatanHapus2();
             dasarTuntutanCukaiHakmilik.getCatatanHapus3();
             dasarTuntutanCukaiHakmilik.getCatatanHapus4();
             dasarTuntutanCukaiHakmilik.getCatatanHapus5();
             dasarTuntutanCukaiHakmilik.getCatatanHapus6();
             dasarTuntutanCukaiHakmilik.getCatatanHapus7();
             dasarTuntutanCukaiHakmilik.getPegawai1();
             dasarTuntutanCukaiHakmilik.getPegawai2();
             dasarTuntutanCukaiHakmilik.getPegawai3();
             dasarTuntutanCukaiHakmilik.getPegawai4();
             dasarTuntutanCukaiHakmilik.getPenyelia1();
             dasarTuntutanCukaiHakmilik.getPenyelia2();

        }
//
//        String idDasar = getContext().getRequest().getParameter("dasarTuntutanCukai.idDasar");
//        LOG.info("masuk func seterus idDasar : " + dasarTuntutanCukaiHakmilik.getDasarTuntutanCukai().getIdDasar());

        return new ForwardResolution("/WEB-INF/jsp/hasil/hapus_kira_3.jsp");
    }

    public Resolution simpan() {
        LOG.info(":: SIMPAN ::");
        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);

        radio = getContext().getRequest().getParameter("radio");
        LOG.info("Radio : " + radio);
        dasarTuntutanCukaiHakmilik = permohonanSupayaBantahanService.getDasarTuntutanCukaiHakmilikByIdDasar(radio);
        dasarTuntutanCukaiHakmilik2 = permohonanSupayaBantahanService.getDasarTuntutanCukaiHakmilikByIdDasar(radio);
        if (dasarTuntutanCukaiHakmilik != null) {
            LOG.info("dasarTuntutanCukaiHakmilik != null");


            dasarTuntutanCukaiHakmilik.setCatatanHapus1(getContext().getRequest().getParameter("dasarTuntutanCukaiHakmilik.catatanHapus1"));
            dasarTuntutanCukaiHakmilik.setCatatanHapus2(getContext().getRequest().getParameter("dasarTuntutanCukaiHakmilik.catatanHapus2"));
            dasarTuntutanCukaiHakmilik.setCatatanHapus3(getContext().getRequest().getParameter("dasarTuntutanCukaiHakmilik.catatanHapus3"));
            dasarTuntutanCukaiHakmilik.setCatatanHapus4(getContext().getRequest().getParameter("dasarTuntutanCukaiHakmilik.catatanHapus4"));
            dasarTuntutanCukaiHakmilik.setCatatanHapus5(getContext().getRequest().getParameter("dasarTuntutanCukaiHakmilik.catatanHapus5"));
            dasarTuntutanCukaiHakmilik.setCatatanHapus6(getContext().getRequest().getParameter("dasarTuntutanCukaiHakmilik.catatanHapus6"));
            dasarTuntutanCukaiHakmilik.setCatatanHapus7(getContext().getRequest().getParameter("dasarTuntutanCukaiHakmilik.catatanHapus7"));
            dasarTuntutanCukaiHakmilik.setPegawai1(getContext().getRequest().getParameter("dasarTuntutanCukaiHakmilik.pegawai1"));
            dasarTuntutanCukaiHakmilik.setPegawai2(getContext().getRequest().getParameter("dasarTuntutanCukaiHakmilik.pegawai2"));
            dasarTuntutanCukaiHakmilik.setPegawai3(getContext().getRequest().getParameter("dasarTuntutanCukaiHakmilik.pegawai3"));
            dasarTuntutanCukaiHakmilik.setPegawai4(getContext().getRequest().getParameter("dasarTuntutanCukaiHakmilik.pegawai4"));
            dasarTuntutanCukaiHakmilik.setPenyelia1(getContext().getRequest().getParameter("dasarTuntutanCukaiHakmilik.penyelia1"));
            dasarTuntutanCukaiHakmilik.setPenyelia2(getContext().getRequest().getParameter("dasarTuntutanCukaiHakmilik.penyelia2"));
            dasarTuntutanCukaiHakmilik.setPenyelia3(getContext().getRequest().getParameter("dasarTuntutanCukaiHakmilik.penyelia3"));

            permohonanSupayaBantahanService.saveOrUpdateDasarTuntutanCukaiHakmilik(dasarTuntutanCukaiHakmilik);
        }
       
        
        addSimpleMessage("Maklumat telah berjaya disimpan");
        return new JSP("hasil/hapus_kira_3.jsp").addParameter("tab", "true");

    }

    public Resolution simpanBaru() {
        LOG.info(":: SIMPAN ::");
        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);

        radio = getContext().getRequest().getParameter("radio");
        LOG.info("Radio : " + radio);
        dasarTuntutanCukaiHakmilik = permohonanSupayaBantahanService.getDasarTuntutanCukaiHakmilikByIdDasar(radio);

        if (dasarTuntutanCukaiHakmilik != null) {
            LOG.info("dasarTuntutanCukaiHakmilik != null");

            dasarTuntutanCukaiHakmilik.setCatatanHapus1(getContext().getRequest().getParameter("dasarTuntutanCukaiHakmilik.catatanHapus1"));
            dasarTuntutanCukaiHakmilik.setCatatanHapus2(getContext().getRequest().getParameter("dasarTuntutanCukaiHakmilik.catatanHapus2"));
            dasarTuntutanCukaiHakmilik.setCatatanHapus3(getContext().getRequest().getParameter("dasarTuntutanCukaiHakmilik.catatanHapus3"));
            dasarTuntutanCukaiHakmilik.setCatatanHapus4(getContext().getRequest().getParameter("dasarTuntutanCukaiHakmilik.catatanHapus4"));
            dasarTuntutanCukaiHakmilik.setCatatanHapus5(getContext().getRequest().getParameter("dasarTuntutanCukaiHakmilik.catatanHapus5"));
            dasarTuntutanCukaiHakmilik.setCatatanHapus6(getContext().getRequest().getParameter("dasarTuntutanCukaiHakmilik.catatanHapus6"));
            dasarTuntutanCukaiHakmilik.setCatatanHapus7(getContext().getRequest().getParameter("dasarTuntutanCukaiHakmilik.catatanHapus7"));
            dasarTuntutanCukaiHakmilik.setPegawai1(getContext().getRequest().getParameter("dasarTuntutanCukaiHakmilik.pegawai1"));
            dasarTuntutanCukaiHakmilik.setPegawai2(getContext().getRequest().getParameter("dasarTuntutanCukaiHakmilik.pegawai2"));
            dasarTuntutanCukaiHakmilik.setPegawai3(getContext().getRequest().getParameter("dasarTuntutanCukaiHakmilik.pegawai3"));
            dasarTuntutanCukaiHakmilik.setPegawai4(getContext().getRequest().getParameter("dasarTuntutanCukaiHakmilik.pegawai4"));
            dasarTuntutanCukaiHakmilik.setPenyelia1(getContext().getRequest().getParameter("dasarTuntutanCukaiHakmilik.penyelia1"));
            dasarTuntutanCukaiHakmilik.setPenyelia2(getContext().getRequest().getParameter("dasarTuntutanCukaiHakmilik.penyelia2"));
            dasarTuntutanCukaiHakmilik.setPenyelia3(getContext().getRequest().getParameter("dasarTuntutanCukaiHakmilik.penyelia3"));

            permohonanSupayaBantahanService.saveOrUpdateDasarTuntutanCukaiHakmilik(dasarTuntutanCukaiHakmilik);
        }

        addSimpleMessage("Maklumat telah berjaya dikemaskini");
        return new JSP("hasil/hapus_kira_update.jsp").addParameter("tab", "true");

    }

    public Resolution popupKemaskiniBngn() {
        // getContext().getRequest().setAttribute("update", Boolean.TRUE);
         radio = getContext().getRequest().getParameter("radio");
         LOG.info("Radio : " + radio);
         dasarTuntutanCukaiHakmilik = permohonanSupayaBantahanService.getDasarTuntutanCukaiHakmilikByIdDasar(radio);

         if (dasarTuntutanCukaiHakmilik != null) {

             dasarTuntutanCukaiHakmilik.getCatatanHapus1();
             dasarTuntutanCukaiHakmilik.getCatatanHapus2();
             dasarTuntutanCukaiHakmilik.getCatatanHapus3();
             dasarTuntutanCukaiHakmilik.getCatatanHapus4();
             dasarTuntutanCukaiHakmilik.getCatatanHapus5();
             dasarTuntutanCukaiHakmilik.getCatatanHapus6();
             dasarTuntutanCukaiHakmilik.getCatatanHapus7();
             dasarTuntutanCukaiHakmilik.getPegawai1();
             dasarTuntutanCukaiHakmilik.getPegawai2();
             dasarTuntutanCukaiHakmilik.getPegawai3();
             dasarTuntutanCukaiHakmilik.getPegawai4();
             dasarTuntutanCukaiHakmilik.getPenyelia1();
             dasarTuntutanCukaiHakmilik.getPenyelia2();

             permohonanSupayaBantahanService.saveOrUpdateDasarTuntutanCukaiHakmilik(dasarTuntutanCukaiHakmilik);


        }
        return new JSP("hasil/hapus_kira_update.jsp").addParameter("popup", "true");
    }

    public Resolution back() {
//        LOG.info("Radio : " + radio);


        return new ForwardResolution("/WEB-INF/jsp/hasil/hapus_kira.jsp");
    }

    

    public List<DasarTuntutanCukaiHakmilik> getDasarList() {
        return dasarList;
    }

    public void setDasarList(List<DasarTuntutanCukaiHakmilik> dasarList) {
        this.dasarList = dasarList;
    }

    public DasarTuntutanCukaiDAO getDasarTuntuanCukaiDAO() {
        return dasarTuntuanCukaiDAO;
    }

    public void setDasarTuntuanCukaiDAO(DasarTuntutanCukaiDAO dasarTuntuanCukaiDAO) {
        this.dasarTuntuanCukaiDAO = dasarTuntuanCukaiDAO;
    }

    public DasarTuntutanCukai getDasarTuntutanCukai() {
        return dasarTuntutanCukai;
    }

    public void setDasarTuntutanCukai(DasarTuntutanCukai dasarTuntutanCukai) {
        this.dasarTuntutanCukai = dasarTuntutanCukai;
    }

    public DasarTuntutanCukaiHakmilik getDasarTuntutanCukaiHakmilik() {
        return dasarTuntutanCukaiHakmilik;
    }

    public void setDasarTuntutanCukaiHakmilik(DasarTuntutanCukaiHakmilik dasarTuntutanCukaiHakmilik) {
        this.dasarTuntutanCukaiHakmilik = dasarTuntutanCukaiHakmilik;
    }

    public DasarTuntutanCukaiHakmilikDAO getDasarTuntutanCukaiHakmilikDAO() {
        return dasarTuntutanCukaiHakmilikDAO;
    }

    public void setDasarTuntutanCukaiHakmilikDAO(DasarTuntutanCukaiHakmilikDAO dasarTuntutanCukaiHakmilikDAO) {
        this.dasarTuntutanCukaiHakmilikDAO = dasarTuntutanCukaiHakmilikDAO;
    }

    public DasarTuntutanNotis getDasarTuntutanNotis() {
        return dasarTuntutanNotis;
    }

    public void setDasarTuntutanNotis(DasarTuntutanNotis dasarTuntutanNotis) {
        this.dasarTuntutanNotis = dasarTuntutanNotis;
    }

    public DasarTuntutanNotisDAO getDasarTuntutanNotisDAO() {
        return dasarTuntutanNotisDAO;
    }

    public void setDasarTuntutanNotisDAO(DasarTuntutanNotisDAO dasarTuntutanNotisDAO) {
        this.dasarTuntutanNotisDAO = dasarTuntutanNotisDAO;
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public Hakmilik getHakmilik() {
        return hakmilik;
    }

    public void setHakmilik(Hakmilik hakmilik) {
        this.hakmilik = hakmilik;
    }

    public HakmilikDAO getHakmilikDAO() {
        return hakmilikDAO;
    }

    public void setHakmilikDAO(HakmilikDAO hakmilikDAO) {
        this.hakmilikDAO = hakmilikDAO;
    }

    public String getRadio() {
        return radio;
    }

    public void setRadio(String radio) {
        this.radio = radio;
    }

    public String getIdDasar() {
        return idDasar;
    }

    public void setIdDasar(String idDasar) {
        this.idDasar = idDasar;
    }

    public String getKodNegeri() {
        return kodNegeri;
    }

    public void setKodNegeri(String kodNegeri) {
        this.kodNegeri = kodNegeri;
    }

    public String getIdDasarHakmilik() {
        return idDasarHakmilik;
    }

    public void setIdDasarHakmilik(String idDasarHakmilik) {
        this.idDasarHakmilik = idDasarHakmilik;
    }

    public DasarTuntutanCukaiHakmilik getDasarTuntutanCukaiHakmilik2() {
        return dasarTuntutanCukaiHakmilik2;
    }

    public void setDasarTuntutanCukaiHakmilik2(DasarTuntutanCukaiHakmilik dasarTuntutanCukaiHakmilik2) {
        this.dasarTuntutanCukaiHakmilik2 = dasarTuntutanCukaiHakmilik2;
    }

    

    


}
