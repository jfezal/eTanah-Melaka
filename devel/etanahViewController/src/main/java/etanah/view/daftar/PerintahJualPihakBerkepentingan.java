/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.daftar;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.dao.HakmilikUrusanDAO;
import etanah.dao.PermohonanDAO;
import etanah.model.HakmilikPihakBerkepentingan;
import etanah.model.HakmilikUrusan;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.model.PermohonanAtasPerserahan;
import etanah.model.PermohonanPihak;
import etanah.service.common.HakmilikPihakKepentinganService;
import etanah.service.common.HakmilikUrusanService;
import etanah.service.common.PermohonanAtasPerserahanService;
import etanah.service.common.PermohonanPihakService;
import etanah.view.etanahActionBeanContext;
import java.util.ArrayList;
import java.util.List;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.RedirectResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

/**
 *
 * @author fikri
 */
@UrlBinding("/daftar/perintah_jual_pb")
public class PerintahJualPihakBerkepentingan extends AbleActionBean {

    private List<HakmilikPihakBerkepentingan> senaraiPB;
    private List<PermohonanPihak> senaraiMP;
    private List<PermohonanAtasPerserahan> senaraiUrusanPilihan;
    private String[] chkbox;
    private Pengguna pengguna;
    private Permohonan permohonan;
    @Inject
    HakmilikUrusanService hakmilikUrusanService;
    @Inject
    HakmilikPihakKepentinganService hakmilikPihakKepentinganService;
    @Inject
    PermohonanAtasPerserahanService permohonanAtasPerserahanService;
    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    HakmilikUrusanDAO hakmilikUrusanDAO;
    @Inject
    PermohonanPihakService permohonanPihakService;
    private static Logger LOG = Logger.getLogger(PerintahJualPihakBerkepentingan.class);
    private static boolean isDebug = LOG.isDebugEnabled();

    @DefaultHandler
    public Resolution showForm() {
        return new JSP("daftar/kemasukan_pb_jual_danaharta.jsp").addParameter("tab", "true");
    }

    @Before(stages = {LifecycleStage.BindingAndValidation}, on = {"!rmUrusanPajakanWithPihak"})
    public void rehydrate() {
        pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        if (StringUtils.isNotBlank(idPermohonan)) {

            permohonan = permohonanDAO.findById(idPermohonan);
            senaraiUrusanPilihan = new ArrayList<PermohonanAtasPerserahan>();
            senaraiPB = new ArrayList<HakmilikPihakBerkepentingan>();

            if (permohonan != null && permohonan.getSenaraiHakmilik() != null) {
//                List<HakmilikPermohonan> hakmilikPermohonan = permohonan.getSenaraiHakmilik();
//                List list = new ArrayList();
//
//                for (HakmilikPermohonan hp : hakmilikPermohonan) {
//                    Hakmilik h = hp.getHakmilik();
//                    if(h == null)continue;
//                    List<HakmilikPihakBerkepentingan> senaraiTmpPB = new ArrayList<HakmilikPihakBerkepentingan>();
//                    if(permohonan.getKodUrusan() != null) {
//                        if(permohonan.getKodUrusan().getKod().equals("JDGPJ")) {
//                            list.add("PJ");
//                            list.add("PJK");
//                            senaraiTmpPB = hakmilikPihakKepentinganService.findHakmilikPihakBerkepentinganByIdHakmilik(h.getIdHakmilik(), list);
//                        } else {
//                            senaraiTmpPB = hakmilikPihakKepentinganService.findHakmilikPihakBerkepentinganByIdHakmilik(h.getIdHakmilik(), null);
//                        }
//                    }
//                    for (HakmilikPihakBerkepentingan hpb : senaraiTmpPB) {
//                            senaraiPB.add(hpb);
//                    }
//                }

                List<PermohonanAtasPerserahan> senaraiTmp = permohonan.getSenaraiPermohonanAtasPerserahan();

                for (PermohonanAtasPerserahan pas : senaraiTmp) {
                    List<HakmilikPihakBerkepentingan> senaraiTmpPB = new ArrayList<HakmilikPihakBerkepentingan>();
                    if (permohonan.getKodUrusan() != null && permohonan.getKodUrusan().getKod().equals("PHMMK")) {
                        if (pas.getUrusan().getKodUrusan().getKod().equals("PJT")
                                || pas.getUrusan().getKodUrusan().getKod().equals("PJBT")
                                || pas.getUrusan().getKodUrusan().getKod().equals("PJKT")
                                || pas.getUrusan().getKodUrusan().getKod().equals("PJKBT")
                                || pas.getUrusan().getKodUrusan().getKod().equals("GD")
                                || pas.getUrusan().getKodUrusan().getKod().equals("TEN")
                                || pas.getUrusan().getKodUrusan().getKod().equals("KVLK")
                                || pas.getUrusan().getKodUrusan().getKod().equals("KVLP")
                                || pas.getUrusan().getKodUrusan().getKod().equals("KVLS")
                                || pas.getUrusan().getKodUrusan().getKod().equals("KVLT")) {
                            senaraiUrusanPilihan.add(pas);
                            HakmilikUrusan hu = pas.getUrusan();
                            senaraiTmpPB = hakmilikPihakKepentinganService.findHakmilikPihakByIdUrusan(hu);
                        }
                    } else {
                        if (pas.getUrusan().getKodUrusan().getKod().equals("PJT")
                                || pas.getUrusan().getKodUrusan().getKod().equals("PJBT")
                                || pas.getUrusan().getKodUrusan().getKod().equals("PJKT")
                                || pas.getUrusan().getKodUrusan().getKod().equals("PJKBT")) {
                            senaraiUrusanPilihan.add(pas);
                            HakmilikUrusan hu = pas.getUrusan();
                            senaraiTmpPB = hakmilikPihakKepentinganService.findHakmilikPihakByIdUrusan(hu);
                        }
                    }
                    for (HakmilikPihakBerkepentingan hp : senaraiTmpPB) {
                        senaraiPB.add(hp);
                    }
                }
                senaraiMP = permohonan.getSenaraiPihak();
            }
        }
    }

    public Resolution rmPermohonanPihak() {
        String[] ids = getContext().getRequest().getParameterValues("ids");
        if (etanah.util.StringUtils.isNotBlank(ids)) {
            permohonanPihakService.delete(ids);
        }
        return new RedirectResolution(PerintahJualPihakBerkepentingan.class);
    }

    public List<HakmilikPihakBerkepentingan> getSenaraiPB() {
        return senaraiPB;
    }

    public void setSenaraiPB(List<HakmilikPihakBerkepentingan> senaraiPB) {
        this.senaraiPB = senaraiPB;
    }

    public List<PermohonanAtasPerserahan> getSenaraiUrusanPilihan() {
        return senaraiUrusanPilihan;
    }

    public void setSenaraiUrusanPilihan(List<PermohonanAtasPerserahan> senaraiUrusanPilihan) {
        this.senaraiUrusanPilihan = senaraiUrusanPilihan;
    }

    public String[] getChkbox() {
        return chkbox;
    }

    public void setChkbox(String[] chkbox) {
        this.chkbox = chkbox;
    }

    public List<PermohonanPihak> getSenaraiMP() {
        return senaraiMP;
    }

    public void setSenaraiMP(List<PermohonanPihak> senaraiMP) {
        this.senaraiMP = senaraiMP;
    }
}
