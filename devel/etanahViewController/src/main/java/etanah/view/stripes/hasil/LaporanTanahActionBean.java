/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.stripes.hasil;

/**
 *
 * @author nurfaizati
 */
import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.dao.FasaPermohonanDAO;
import etanah.dao.HakmilikPermohonanDAO;
import etanah.dao.LaporanTanahDAO;
import etanah.dao.PermohonanDAO;
import etanah.dao.PermohonanRujukanLuarDAO;
import etanah.model.FasaPermohonan;
import etanah.model.HakmilikPermohonan;
import etanah.model.InfoAudit;
import etanah.model.KodDokumen;
import etanah.model.KodRujukan;
import etanah.model.LaporanTanah;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.model.PermohonanRujukanLuar;
import etanah.service.BPelService;
import etanah.service.LaporanTanahService;
import etanah.view.etanahActionBeanContext;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;
import org.apache.log4j.Logger;
import org.apache.commons.lang.StringUtils;
import oracle.bpel.services.workflow.task.model.Task;
import org.hibernate.Session;
import org.hibernate.Transaction;


@UrlBinding("/hasil/laporan_tanah_hasil")
public class LaporanTanahActionBean extends AbleActionBean {

    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    LaporanTanahDAO laporanTanahDAO;
    @Inject
    FasaPermohonanDAO fasaPermohonanDAO;
    @Inject
    HakmilikPermohonanDAO hakmilikPermohonanDAO;
    @Inject
    PermohonanRujukanLuarDAO permohonanRujukanLuarDAO;
    @Inject
    LaporanTanahService laporanTanahService;
    @Inject
    private etanah.Configuration conf;
     @Inject
    protected com.google.inject.Provider<Session> sessionProvider;

    List<HakmilikPermohonan> senaraiHakmilik = new ArrayList<HakmilikPermohonan>();
    private Permohonan permohonan;
    private HakmilikPermohonan hakmilikPermohonan = new HakmilikPermohonan();
    private LaporanTanah laporanTanah;
    private FasaPermohonan fasaPermohonan;
    private PermohonanRujukanLuar permohonanRujukanLuar;
    private static final Logger LOG = Logger.getLogger(LaporanTanahActionBean.class);
    private static final boolean IS_LOG_DEBUG = LOG.isDebugEnabled();
    private String stageId;
    private String id;
    private  String idHakmilik;
    private  String idPermohonan;
    private long idLaporan;
    private String kodNegeri;
    private boolean btn = false;
    private boolean flag1 = false;
    private List<LaporanTanah> senaraiTanah;
    private List<HakmilikPermohonan> senaraiHakmilikPermohonan;

    public List<LaporanTanah> getSenaraiTanah1() {
        return senaraiTanah1;
    }

    public void setSenaraiTanah1(List<LaporanTanah> senaraiTanah1) {
        this.senaraiTanah1 = senaraiTanah1;
    }
    private List<LaporanTanah> senaraiTanah1 = new ArrayList<LaporanTanah>();
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

    @DefaultHandler
    public Resolution showForm() {
        LOG.info("Start Laporan Tanah");
        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        if (permohonan != null) {
            LOG.info("permohonan.getIdPermohonan(): " + permohonan.getIdPermohonan());
        }

        return new ForwardResolution("/WEB-INF/jsp/hasil/hasil_borang_lapor_tanah.jsp");

    }

    //modified by tulasi
    public Resolution showForm2() {

       idHakmilik = (String) getContext().getRequest().getParameter("idHakmilik");
       permohonan = permohonanDAO.findById(getContext().getRequest().getParameter("idPermohonan"));
       String idMH = (String) getContext().getRequest().getParameter("id");

          laporanTanah = laporanTanahService.findLaporanTanahByIdMohonIdMP(permohonan.getIdPermohonan(),idMH);
          if(laporanTanah == null){
              laporanTanah = new LaporanTanah();
          }

    // hakmilikPermohonan = laporanTanahDAO.findById(hakmilikPermohonan.getId());
       //laporanTanah = laporanTanahDAO.findById(Long.parseLong(idLaporan));;
        LOG.info("Start Laporan Tanah view");

        return new JSP("hasil/hasil_borang_lapor_tanah.jsp").addParameter("tab", "true");

    }
//ended
    @Before(stages = {LifecycleStage.BindingAndValidation}, on = {"!simpan"})
    public void rehydrate() {

        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
                String xx = getContext().getRequest().getParameter("id");
        HakmilikPermohonan hp = hakmilikPermohonanDAO.findById(Long.parseLong(xx));

   if (idPermohonan != null) {

            permohonan = permohonanDAO.findById(idPermohonan);



            String[] tname = {"permohonan"};
            Object[] model = {permohonan};

            List<LaporanTanah> listLaporanTanah;

            listLaporanTanah = laporanTanahDAO.findByEqualCriterias(tname, model, null);
             senaraiHakmilikPermohonan = new ArrayList<HakmilikPermohonan>();

            Session s = sessionProvider.get();
            Transaction tx = s.beginTransaction();
            tx.begin();
            try {
                List<HakmilikPermohonan> senaraiHakmilikPermohonanAll = permohonan.getSenaraiHakmilik();
                for (HakmilikPermohonan hm : senaraiHakmilikPermohonanAll) {


                    if(hp.getHakmilik().getAkaunCukai().getBaki().intValue() > 0)
                         senaraiHakmilikPermohonan.add(hm);
                        LOG.info("idHakmilik :"+hp.getHakmilik().getIdHakmilik());
                          LOG.info("idMOhonHakmilik :"+hp.getId());
                        String [] name ={"hakmilikPermohonan"};
                        Object [] value={hm};
                        List<LaporanTanah> senaraiTanah = laporanTanahDAO.findByEqualCriterias(name, value, null);
                        LOG.info("noDasar :"+id);
//                      laporanTanah = laporanTanahDAO.findById(hm.getId());
//                      LOG.info("laporanTanah :"+laporanTanah);
//
      }

                LOG.debug("senaraiHakmilikPermohonan :" + senaraiHakmilikPermohonan.size());
            } catch (Exception ex) {
                LOG.error("rehydrate ex: " + ex);

            }


            List<HakmilikPermohonan> hpList = hakmilikPermohonanDAO.findByEqualCriterias(tname, model, null);

            hakmilikPermohonan = hpList.get(0);

            if (!(listLaporanTanah.isEmpty())) {
                laporanTanah = listLaporanTanah.get(0);
            }

            List<FasaPermohonan> listFasa;
            listFasa = fasaPermohonanDAO.findByEqualCriterias(tname, model, null);
            kodNegeri = conf.getProperty("kodNegeri");

            if (!(listFasa.isEmpty())) {
                for (int i = 0; i < listFasa.size(); i++) {
                    FasaPermohonan fp = new FasaPermohonan();
                    fp = listFasa.get(i);

                    if (kodNegeri.equals("04")) {
                        if (fp.getIdAliran().equals("Laporan_Tanah")) {
                            fasaPermohonan = listFasa.get(i);
                        }
                    }
//                    else {
//                        if (fp.getIdAliran().equals("Stage1")) {
//                            fasaPermohonan = listFasa.get(i);
//                        }
//                    }
                }
            }

            List<PermohonanRujukanLuar> listRujukanLuar;
            listRujukanLuar = permohonanRujukanLuarDAO.findByEqualCriterias(tname, model, null);


            if (!(listRujukanLuar.isEmpty())) {
                for (int i = 0; i < listRujukanLuar.size(); i++) {
                    PermohonanRujukanLuar rujL = new PermohonanRujukanLuar();
                    rujL = listRujukanLuar.get(i);
                    if (rujL.getKodDokumenRujukan() != null) {
                        if (rujL.getKodDokumenRujukan().getKod().equals("WRKN")) {
                            permohonanRujukanLuar = listRujukanLuar.get(i);
                        }
                    }
                }
            }

        }

    }
//modified by tulasi
    public Resolution simpan() {
        Pengguna pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        permohonan = permohonanDAO.findById(idPermohonan);
          String idLaporan= getContext().getRequest().getParameter("laporanTanah.idLaporan");
         Session s = sessionProvider.get();
        Transaction tx = s.beginTransaction();
        tx.begin();
        //LaporanTanah laporanTanahTemp = new LaporanTanah();
         InfoAudit infoAudit=new InfoAudit();
       try {
        if(idLaporan!=null && !idLaporan.equals("")){
           // laporanTanahTemp = laporanTanahDAO.findById(Long.parseLong(idLaporan));
           // infoAudit = laporanTanahTemp.getInfoAudit();


            infoAudit.setDimasukOleh(pengguna);
            infoAudit.setTarikhMasuk(new java.util.Date());

            infoAudit.setTarikhKemaskini(new java.util.Date());
            infoAudit.setDiKemaskiniOleh(pengguna);
            laporanTanah.setInfoAudit(infoAudit);
           }else{
            LaporanTanah laporanTanah = new LaporanTanah();
            infoAudit.setDimasukOleh(pengguna);
            infoAudit.setTarikhMasuk(new java.util.Date());
            laporanTanah.setInfoAudit(infoAudit);
            s.merge(laporanTanah);
            s.refresh(laporanTanah);
            s.flush();
        }
         laporanTanah.setPermohonan(permohonan);
         laporanTanah.setInfoAudit(infoAudit);
         laporanTanah.setHakmilikPermohonan(hakmilikPermohonanDAO.findById(Long.parseLong(id)));
         s.merge(laporanTanah);
         s.refresh(laporanTanah);
         s.flush();
         laporanTanahService.simpanLaporanTanah(laporanTanah);

        permohonan = permohonanDAO.findById(permohonan.getIdPermohonan());

        BPelService service = new BPelService();
        String taskId = (String) getContext().getRequest().getSession().getAttribute("taskId");

        if (StringUtils.isNotBlank(taskId)) {
            Task t = null;
            t = service.getTaskFromBPel(taskId, pengguna);
            stageId = t.getSystemAttributes().getStage();
        } else {
            kodNegeri = conf.getProperty("kodNegeri");
            if (kodNegeri.equals("04")) {
                stageId = "Laporan_Tanah";
            }

        }

        if (fasaPermohonan != null) {

            fasaPermohonan.setPermohonan(permohonan);
            fasaPermohonan.setCawangan(permohonan.getCawangan());
            fasaPermohonan.setInfoAudit(infoAudit);
            fasaPermohonan.setIdPengguna(pengguna.getIdPengguna());
            fasaPermohonan.setIdAliran(stageId);
            laporanTanahService.simpanFasaPermohonan(fasaPermohonan);

            if (permohonanRujukanLuar != null) {
                permohonanRujukanLuar.setPermohonan(permohonan);
                permohonanRujukanLuar.setCawangan(permohonan.getCawangan());
                permohonanRujukanLuar.setInfoAudit(infoAudit);
                permohonanRujukanLuar.setTarikhRujukan(new java.util.Date());

                KodDokumen kodDokumenWarta = new KodDokumen();
                kodDokumenWarta.setKod("WRKN");

                KodRujukan kodRujukan = new KodRujukan();
                kodRujukan.setKod("FL");

                permohonanRujukanLuar.setKodRujukan(kodRujukan);
                permohonanRujukanLuar.setKodDokumenRujukan(kodDokumenWarta);
                laporanTanahService.simpanRujukanLuar(permohonanRujukanLuar);
            }
        }
               // LOG.debug("senaraiHakmilikPermohonan :" + senaraiHakmilikPermohonan.size());
            } catch (Exception ex) {
                LOG.error("rehydrate ex: " + ex);

            }finally{
            tx.commit();
            }
        addSimpleMessage("Maklumat telah berjaya disimpan.");

        getContext().getRequest().setAttribute("edit", Boolean.TRUE);

        return new ForwardResolution("/WEB-INF/jsp/hasil/hasil_borang_lapor_tanah.jsp").addParameter("tab", "true");
    }
//ended by tulasi
 //modified
    public Resolution popup() {
        idHakmilik = (String) getContext().getRequest().getParameter("idHakmilik");
        permohonan = permohonanDAO.findById(getContext().getRequest().getParameter("idPermohonan"));
       String idMH = (String) getContext().getRequest().getParameter("id");

          laporanTanah = laporanTanahService.findLaporanTanahByIdMohonIdMP(permohonan.getIdPermohonan(),idMH);
          if(laporanTanah == null){
              laporanTanah = new LaporanTanah();
          }
        setFlag1(true);
        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        return new JSP("consent/hasil_borang_lapor_tanah.jsp").addParameter("popup", "true");
    }
//ended
    public Permohonan getPermohonan() {
        return permohonan;
    }

    public void setPermohonan(Permohonan permohonan) {
        this.permohonan = permohonan;
    }

    public LaporanTanah getLaporanTanah() {
        return laporanTanah;
    }

    public void setLaporanTanah(LaporanTanah laporanTanah) {
        this.laporanTanah = laporanTanah;
    }

    public List<HakmilikPermohonan> getSenaraiHakmilik() {
        return senaraiHakmilik;
    }

    public void setSenaraiHakmilik(List<HakmilikPermohonan> senaraiHakmilik) {
        this.senaraiHakmilik = senaraiHakmilik;
    }

    public FasaPermohonan getFasaPermohonan() {
        return fasaPermohonan;
    }

    public void setFasaPermohonan(FasaPermohonan fasaPermohonan) {
        this.fasaPermohonan = fasaPermohonan;
    }

    public PermohonanRujukanLuar getPermohonanRujukanLuar() {
        return permohonanRujukanLuar;
    }

    public void setPermohonanRujukanLuar(PermohonanRujukanLuar permohonanRujukanLuar) {
        this.permohonanRujukanLuar = permohonanRujukanLuar;
    }

    public String getStageId() {
        return stageId;
    }

    public void setStageId(String stageId) {
        this.stageId = stageId;
    }

    public HakmilikPermohonan getHakmilikPermohonan() {
        return hakmilikPermohonan;
    }

    public void setHakmilikPermohonan(HakmilikPermohonan hakmilikPermohonan) {
        this.hakmilikPermohonan = hakmilikPermohonan;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public void setIdPermohonan(String aIdPermohonan) {
        idPermohonan = aIdPermohonan;
    }

    /**
     * @return the btn
     */
    public boolean isBtn() {
        return btn;
    }

    /**
     * @param btn the btn to set
     */
    public void setBtn(boolean btn) {
        this.btn = btn;
    }

    /**
     * @return the flag1
     */
    public boolean isFlag1() {
        return flag1;
    }

    /**
     * @param flag1 the flag1 to set
     */
    public void setFlag1(boolean flag1) {
        this.flag1 = flag1;
    }

    public long getIdLaporan() {
        return idLaporan;
    }

    public void setIdLaporan(long idLaporan) {
        this.idLaporan = idLaporan;
    }

    /**
     * @return the senaraiHakmilikPermohonan
     */
    public List<HakmilikPermohonan> getSenaraiHakmilikPermohonan() {
        return senaraiHakmilikPermohonan;
    }

    /**
     * @param senaraiHakmilikPermohonan the senaraiHakmilikPermohonan to set
     */
    public void setSenaraiHakmilikPermohonan(List<HakmilikPermohonan> senaraiHakmilikPermohonan) {
        this.senaraiHakmilikPermohonan = senaraiHakmilikPermohonan;
    }

    /**
     * @return the senaraiTanah
     */
    public List<LaporanTanah> getSenaraiTanah() {
        return senaraiTanah;
    }

    /**
     * @param senaraiTanah the senaraiTanah to set
     */
    public void setSenaraiTanah(List<LaporanTanah> senaraiTanah) {
        this.senaraiTanah = senaraiTanah;
    }
}
