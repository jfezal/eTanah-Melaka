package etanah.view.stripes.pelupusan;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.dao.HakmilikDAO;
import etanah.dao.PermohonanDAO;
import etanah.model.Pemohon;
import etanah.model.Permohonan;
import etanah.model.HakmilikPermohonan;
import etanah.view.etanahActionBeanContext;
import etanah.model.Pengguna;
import etanah.model.Pihak;
import etanah.model.TanahRizabPermohonan;
import etanah.service.PelupusanService;
import java.util.Date;
import java.util.List;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;
import org.apache.log4j.Logger;

@UrlBinding("/pelupusan/maklumat_carian_pembatalan")
public class MaklumatCarianPembatalanActionBean extends AbleActionBean {

    private static final Logger LOG = Logger.getLogger(MaklumatCarianPembatalanActionBean.class);
    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    PelupusanService pelupusanService;
    @Inject
    HakmilikDAO hakmilikDAO;
    private Permohonan permohonan;
    private Permohonan permohonanSebelum;
    private HakmilikPermohonan hakmilikPermohonan;
    private List<HakmilikPermohonan> hakmilikPermohonanList;
    private List<Pemohon> pemohonList;
    private String idPermohonanSebelum;
    private String idPermohonan;
    private Pengguna pengguna;
    private Pemohon pemohon;
    private Pihak pihak;
    boolean status = false;
    boolean simpan = false;
    private String tujuan;
    private Date tarikhPermohonan;
    private String sebabPembatalan;
    private String luasDrpTrizab;
//    private ActionBeanContext context;

    public String getLuasDrpTrizab() {
        return luasDrpTrizab;
    }

    public void setLuasDrpTrizab(String luasDrpTrizab) {
        this.luasDrpTrizab = luasDrpTrizab;
    }

    public HakmilikPermohonan getHakmilikPermohonan() {
        return hakmilikPermohonan;
    }

    public void setHakmilikPermohonan(HakmilikPermohonan hakmilikPermohonan) {
        this.hakmilikPermohonan = hakmilikPermohonan;
    }

    public String getIdPermohonan() {
        return idPermohonan;
    }

    public void setIdPermohonan(String idPermohonan) {
        this.idPermohonan = idPermohonan;
    }

    public PelupusanService getPelupusanService() {
        return pelupusanService;
    }

    public void setPelupusanService(PelupusanService pelupusanService) {
        this.pelupusanService = pelupusanService;
    }

    public PermohonanDAO getPermohonanDAO() {
        return permohonanDAO;
    }

    public void setPermohonanDAO(PermohonanDAO permohonanDAO) {
        this.permohonanDAO = permohonanDAO;
    }

    public Permohonan getPermohonanSebelum() {
        return permohonanSebelum;
    }

    public void setPermohonanSebelum(Permohonan permohonanSebelum) {
        this.permohonanSebelum = permohonanSebelum;
    }

    public boolean isSimpan() {
        return simpan;
    }

    public void setSimpan(boolean simpan) {
        this.simpan = simpan;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    @DefaultHandler
    public Resolution showForm() {
        return new JSP("pelupusan/maklumat_carian_pembatalan.jsp").addParameter("tab", "true");
    }

    public Resolution viewForm() {
        status = true;
        return new JSP("pelupusan/maklumat_carian_pembatalan.jsp").addParameter("tab", "true");
    }

    public Resolution refreshCari() {
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        getContext().getRequest().setAttribute("status", Boolean.FALSE);
        permohonan = permohonanDAO.findById(idPermohonan);
        permohonan.setPermohonanSebelum(null);
        pelupusanService.simpanPermohonan(permohonan);
        idPermohonanSebelum = "";
        rehydrate();
        addSimpleError("Rekod permohonan sebelum telah dihapuskan.");
        return new JSP("pelupusan/maklumat_carian_pembatalan.jsp").addParameter("tab", "true");
    }

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {
        LOG.info("rehydrate");
        pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = permohonanDAO.findById(idPermohonan);
        if (permohonan != null) {
            if (permohonan.getPermohonanSebelum() != null) {
                permohonanSebelum = permohonan.getPermohonanSebelum();
                pemohon = pelupusanService.findPemohonByIdPemohon(permohonanSebelum.getIdPermohonan());
                hakmilikPermohonanList = permohonanSebelum.getSenaraiHakmilik();
                getContext().getRequest().setAttribute("status", Boolean.TRUE);
                getContext().getRequest().setAttribute("simpan", Boolean.FALSE);

                TanahRizabPermohonan mohonTrizab = pelupusanService.findTanahRizabByIdPermohonan(permohonanSebelum.getIdPermohonan());
                
                
                if (mohonTrizab != null) {
                    if (mohonTrizab.getRizab() != null) {
                        luasDrpTrizab = String.valueOf(mohonTrizab.getLuasTerlibat());
                    }
                }
            }
        }
        //idPermohonanSebelum = permohonanDAO.findById(idPermohonan);




        if (permohonan != null) {
         
            pemohonList = pelupusanService.findPemohonByIdPermohonan(idPermohonan);
        }
    }

    public Resolution searchId() {
        idPermohonanSebelum = getContext().getRequest().getParameter("idPermohonanSebelum");
        // idPermohonanSebelum = a;
        LOG.info("idPermohonanSebelum :" + idPermohonanSebelum);

        permohonanSebelum = pelupusanService.findPermohonanByIdPermohonanWithoutKodUrusan(idPermohonanSebelum);
        if (permohonanSebelum != null) {
//            
            pemohon = pelupusanService.findPemohonByIdPemohon(permohonanSebelum.getIdPermohonan());
            hakmilikPermohonanList = permohonanSebelum.getSenaraiHakmilik();
            if (pemohon != null) {
                if (pemohon.getPihak() != null) {
                    pihak = pemohon.getPihak();
                }
            } else {

                addSimpleMessage("Pemohon tidak dijumpai ");
            }
            getContext().getRequest().setAttribute("status", Boolean.TRUE);
            getContext().getRequest().setAttribute("simpan", Boolean.TRUE);
            status = false;
            addSimpleMessage("Maklumat dijumpai");

        } else {

            getContext().getRequest().setAttribute("status", Boolean.FALSE);
            addSimpleError("Maklumat tidak dijumpai");
        }

        return new JSP("pelupusan/maklumat_carian_pembatalan.jsp").addParameter("tab", "true");
    }

    public Resolution simpan() {

        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = permohonanDAO.findById(idPermohonan);
        idPermohonanSebelum = getContext().getRequest().getParameter("idPermohonanSebelum");
        permohonanSebelum = permohonanDAO.findById(idPermohonanSebelum);

        permohonan.setPermohonanSebelum(permohonanSebelum);

        pelupusanService.simpanPermohonan(permohonan);


        addSimpleMessage("Maklumat Telah Disimpan");
        getContext().getRequest().setAttribute("status", Boolean.TRUE);
        getContext().getRequest().setAttribute("simpan", Boolean.FALSE);
        status = false;
        //simnpan dalam hakmilikdddPermohonan

        //get MohonHakmilik based on  idPermohonanSebelum 
        List<HakmilikPermohonan> hakmilikPermohonanTerdahulu = pelupusanService.getHakmilikPermohonan(idPermohonanSebelum);

        LOG.info("hakmilikPermohonanTerdahulu.size():" + hakmilikPermohonanTerdahulu.size());

        //simpan dlam mohon hakmilik pakai id sekarang
        pelupusanService.simpanHakmilikPermohonan(hakmilikPermohonanTerdahulu.get(0));


        rehydrate();
        return new JSP("pelupusan/maklumat_carian_pembatalan.jsp").addParameter("tab", "true");
    }

    public String getSebabPembatalan() {
        return sebabPembatalan;
    }

    public void setSebabPembatalan(String sebabPembatalan) {
        this.sebabPembatalan = sebabPembatalan;
    }

    public Pemohon getPemohon() {
        return pemohon;
    }

    public void setPemohon(Pemohon pemohon) {
        this.pemohon = pemohon;
    }

    public List<HakmilikPermohonan> getHakmilikPermohonanList() {
        return hakmilikPermohonanList;
    }

    public void setHakmilikPermohonanList(List<HakmilikPermohonan> hakmilikPermohonanList) {
        this.hakmilikPermohonanList = hakmilikPermohonanList;
    }

    public Permohonan getPermohonan() {
        return permohonan;
    }

    public void setPermohonan(Permohonan permohonan) {
        this.permohonan = permohonan;
    }

    public String getIdPermohonanSebelum() {
        return idPermohonanSebelum;
    }

    public void setIdPermohonanSebelum(String idPermohonanSebelum) {
        this.idPermohonanSebelum = idPermohonanSebelum;
    }

    public HakmilikDAO getHakmilikDAO() {
        return hakmilikDAO;
    }

    public void setHakmilikDAO(HakmilikDAO hakmilikDAO) {
        this.hakmilikDAO = hakmilikDAO;
    }

    public List<Pemohon> getPemohonList() {
        return pemohonList;
    }

    public void setPemohonList(List<Pemohon> pemohonList) {
        this.pemohonList = pemohonList;
    }

    public Pengguna getPengguna() {
        return pengguna;
    }

    public void setPengguna(Pengguna pengguna) {
        this.pengguna = pengguna;
    }

    public Pihak getPihak() {
        return pihak;
    }

    public void setPihak(Pihak pihak) {
        this.pihak = pihak;
    }

    public Date getTarikhPermohonan() {
        return tarikhPermohonan;
    }

    public void setTarikhPermohonan(Date tarikhPermohonan) {
        this.tarikhPermohonan = tarikhPermohonan;
    }

    public String getTujuan() {
        return tujuan;
    }

    public void setTujuan(String tujuan) {
        this.tujuan = tujuan;
    }
}
