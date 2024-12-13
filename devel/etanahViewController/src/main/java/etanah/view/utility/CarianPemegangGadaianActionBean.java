/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.utility;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.dao.HakmilikPihakBerkepentinganDAO;
import etanah.dao.KodJenisPengenalanDAO;
import etanah.dao.PermohonanDAO;
import etanah.dao.PihakDAO;
import etanah.model.HakmilikPermohonan;
import etanah.model.HakmilikPihakBerkepentingan;
import etanah.model.InfoAudit;
import etanah.model.InfoAuditBaru;
import etanah.model.KodJenisPengenalan;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.model.PermohonanPihak;
import etanah.model.Pihak;
import etanah.service.RegService;
import etanah.service.common.DokumenService;
import etanah.service.common.HakmilikPihakKepentinganService;
import etanah.service.common.HakmilikService;
import etanah.service.common.PermohonanPihakService;
import etanah.service.common.PermohonanService;
import etanah.view.etanahActionBeanContext;
import etanah.view.stripes.pelupusan.disClass.DisLaporanTanahService;
import java.util.ArrayList;
import java.util.List;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.HttpCache;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;
import org.springframework.util.StringUtils;

@HttpCache(allow = false)
@UrlBinding("/utiliti/carian_pemegang_gadaian")
public class CarianPemegangGadaianActionBean extends AbleActionBean {

    private Permohonan permohonan;
    private String idPermohonan = new String();
    @Inject
    private PermohonanDAO permohonanDAO;
    @Inject
    private PermohonanPihakService permohonanPihakService;
    @Inject
    private HakmilikPihakKepentinganService hakmilikPihakKepentinganService;
    @Inject
    DisLaporanTanahService disLaporanTanahService;
    @Inject
    RegService regService;
    @Inject
    DokumenService dokumenService;
    @Inject
    PermohonanService permohonanService;
    @Inject
    HakmilikService hakmilikService;
    @Inject
    KodJenisPengenalanDAO jenisPengenalanDAO;
    @Inject
    PihakDAO pihakDAO;
    private List<HakmilikPihakBerkepentingan> listMP;
    private List<HakmilikPihakBerkepentingan> listPG;
    private String idHM;
    private String pemegangGadaian;
    private Pengguna pguna;
    private String namaPemegang;
    private List<PermohonanPihak> listMohonPihak;
    private String idHP;
    private final static org.apache.log4j.Logger LOG = org.apache.log4j.Logger.getLogger(CarianPemegangGadaianActionBean.class);

    @DefaultHandler
    public Resolution showForm() {
        return new JSP("/utiliti/carian_pemegang_gadaian.jsp");
    }

    public Resolution carianPemegangGadaian() throws Exception {
        idPermohonan = getContext().getRequest().getParameter("idPermohonan");

        if (idPermohonan != null) {
            // permohonan = new Permohonan();
            permohonan = disLaporanTanahService.getPermohonanDAO().findById(StringUtils.trimWhitespace(idPermohonan));
            if (permohonan != null) {
                listMP = new ArrayList<HakmilikPihakBerkepentingan>();
                listMP = hakmilikPihakKepentinganService.findPemegangGadaianByIdMohon(idPermohonan);
                if (listMP.isEmpty()) {
                    addSimpleError("Tiada Pemegang Gadaian untuk ID Permohonan ini");
                } else {

                    for (HakmilikPihakBerkepentingan hp : listMP) {
                        idHM = hp.getHakmilik().getIdHakmilik();
                        listPG = new ArrayList<HakmilikPihakBerkepentingan>();
                        listPG = hakmilikPihakKepentinganService.searchByIdHakmilik(hp.getHakmilik().getIdHakmilik());
                        List<PermohonanPihak> mohonPihak = permohonanPihakService.getSenaraiPmohonPihakPG(hp.getIdPermohonan());

//                        listMohonPihak = new ArrayList<PermohonanPihak>();
//                        for (PermohonanPihak pp : mohonPihak) {
//                            pemegangGadaian = pp.getNama();
//                            listMohonPihak.add(pp);
//                        }
                        // listMohonHakmilik.add(hp);
                    }
                }
            } else {
                addSimpleError("Id Permohonan tidak dijumpai");
            }
        } else {
            addSimpleError("Sila masukkan Id Permohonan");
        }
        return showForm();
//        return new ForwardResolution("/WEB-INF/jsp/utiliti/carian_pemegang_gadaian.jsp");

    }

    public Resolution simpan() throws Exception {
        idHP = getContext().getRequest().getParameter("radio");
        idPermohonan = getContext().getRequest().getParameter("idMohon");
        pguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);

        if (idHP != null) {
            HakmilikPihakBerkepentingan hpb = hakmilikPihakKepentinganService.findById(idHP);

            PermohonanPihak mohonPihak = permohonanPihakService.getSenaraiPmohonPihakByIdMhnIdHm(idPermohonan, hpb.getHakmilik().getIdHakmilik());

            if (mohonPihak != null) {

                mohonPihak = permohonanPihakService.findById(String.valueOf(mohonPihak.getIdPermohonanPihak()));

                mohonPihak.setNama(hpb.getNama());
                mohonPihak.setNoPengenalan(hpb.getNoPengenalan());

                if (hpb.getJenisPengenalan() != null) {
                    KodJenisPengenalan jenisPengenalan = jenisPengenalanDAO.findById(hpb.getJenisPengenalan().getKod());
                    mohonPihak.setJenisPengenalan(jenisPengenalan);
                }
                if (hpb.getPihak() != null) {
                    Pihak pihak = pihakDAO.findById(hpb.getPihak().getIdPihak());
                    mohonPihak.setPihak(pihak);
                }

                InfoAudit ia = new InfoAudit();
                ia.setDiKemaskiniOleh(pguna);
                ia.setTarikhKemaskini(new java.util.Date());
                mohonPihak.setInfoAudit(ia);

             //SAVING INTO TABLE MOHON PIHAK
                 permohonanPihakService.saveOrUpdate(mohonPihak);
                addSimpleMessage("Kemaskini Data Telah Berjaya");

            }
        } else {
            addSimpleError("Tiada pilihan data di buat.");
        }

        return showForm();
    }

//     @Before(stages = {LifecycleStage.BindingAndValidation})
//     public void rehydrate() throws Exception {
//
//         idPermohonan = getContext().getRequest().getParameter("idPermohonan");
//        idHM = getContext().getRequest().getParameter("idHM");
//        
//         //carianPemegangGadaian();
//     }
    public Permohonan getPermohonan() {
        return permohonan;
    }

    public void setPermohonan(Permohonan permohonan) {
        this.permohonan = permohonan;
    }

    public String getIdPermohonan() {
        return idPermohonan;
    }

    public void setIdPermohonan(String idPermohonan) {
        this.idPermohonan = idPermohonan;
    }

    public PermohonanDAO getPermohonanDAO() {
        return permohonanDAO;
    }

    public void setPermohonanDAO(PermohonanDAO permohonanDAO) {
        this.permohonanDAO = permohonanDAO;
    }

    public DisLaporanTanahService getDisLaporanTanahService() {
        return disLaporanTanahService;
    }

    public void setDisLaporanTanahService(DisLaporanTanahService disLaporanTanahService) {
        this.disLaporanTanahService = disLaporanTanahService;
    }

    public List<HakmilikPihakBerkepentingan> getListMP() {
        return listMP;
    }

    public void setListMP(List<HakmilikPihakBerkepentingan> listMP) {
        this.listMP = listMP;
    }

    public String getIdHM() {
        return idHM;
    }

    public void setIdHM(String idHM) {
        this.idHM = idHM;
    }

    public String getPemegangGadaian() {
        return pemegangGadaian;
    }

    public void setPemegangGadaian(String pemegangGadaian) {
        this.pemegangGadaian = pemegangGadaian;
    }

    public List<HakmilikPihakBerkepentingan> getListPG() {
        return listPG;
    }

    public void setListPG(List<HakmilikPihakBerkepentingan> listPG) {
        this.listPG = listPG;
    }

    public List<PermohonanPihak> getListMohonPihak() {
        return listMohonPihak;
    }

    public void setListMohonPihak(List<PermohonanPihak> listMohonPihak) {
        this.listMohonPihak = listMohonPihak;
    }

}
