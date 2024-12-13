/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.stripes.common;

import able.stripes.AbleActionBean;
import com.google.inject.Inject;
import etanah.dao.HakmilikDAO;
import etanah.dao.HakmilikPihakBerkepentinganDAO;
import etanah.dao.KodJenisPihakBerkepentinganDAO;
import etanah.dao.PermohonanAtasPihakBerkepentinganDAO;
import etanah.model.HakmilikPihakBerkepentingan;
import etanah.model.InfoAudit;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.model.PermohonanAtasPihakBerkepentingan;
import etanah.service.common.PermohonanAtasPihakBerkepentinganService;
import etanah.view.etanahActionBeanContext;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.RedirectResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import etanah.dao.PermohonanDAO;
import etanah.model.Hakmilik;
import etanah.model.HakmilikPermohonan;
import etanah.model.KodJenisPihakBerkepentingan;
import etanah.model.Pemohon;
import etanah.model.PermohonanPihak;
import etanah.model.Pihak;
import etanah.service.common.HakmilikPihakKepentinganService;
import etanah.service.common.PemohonService;
import etanah.service.common.PermohonanPihakService;
import etanah.view.daftar.PihakKepentinganAction;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author fikri
 */
@UrlBinding("/common/permohonan_atas_pihak")
public class PermohonanAtasPihakBerkepentinganActionBean extends AbleActionBean {

    private static final Logger logger = Logger.getLogger(PermohonanAtasPihakBerkepentinganActionBean.class);
    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    PermohonanPihakService permohonanPihakService;
    @Inject
    PermohonanAtasPihakBerkepentinganService mapService;
    @Inject
    HakmilikPihakBerkepentinganDAO hakmilikPihakBerkepentinganDAO;
    @Inject
    PermohonanAtasPihakBerkepentinganDAO permohonanAtasPihakBerkepentinganDAO;
    @Inject
    KodJenisPihakBerkepentinganDAO kodJenisPihakBerkepentinganDAO;
    @Inject
    PemohonService pemohonDAO;
    @Inject
    HakmilikPihakKepentinganService hakmilikPihakKepentinganService;

    @Inject HakmilikDAO hakmilikDAO;


    private PermohonanAtasPihakBerkepentingan map;
    private HakmilikPihakBerkepentingan pihakPemberi;
    private HakmilikPihakBerkepentingan pihakPemegang;
    private Permohonan permohonan;
    private Pengguna pengguna;
    private String[] syer1;
    private String[] syer2;

    private StringBuffer err;

    public Resolution saveMohonAtasPihakKaveat() {
        InfoAudit ia = new InfoAudit();
        map = mapService.findByPermohonan(permohonan);
        if (map != null) {
            ia = map.getInfoAudit();
            ia.setDiKemaskiniOleh(pengguna);
            ia.setTarikhKemaskini(new java.util.Date());
        } else {
            map = new PermohonanAtasPihakBerkepentingan();
            ia.setDimasukOleh(pengguna);
            ia.setTarikhMasuk(new java.util.Date());
        }
        map.setInfoAudit(ia);
        map.setPihakBerkepentingan(pihakPemberi);
        map.setPermohonan(permohonan);
        mapService.save(map);
        return new RedirectResolution(PihakBerkepentinganActionBean.class, "showPihakBerkepentinganKaveatForm");
    }

    public Resolution saveAtasPihakMultiple() {
        pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        List<PermohonanAtasPihakBerkepentingan> senarai = new ArrayList<PermohonanAtasPihakBerkepentingan>();
        permohonan = permohonanDAO.findById(idPermohonan);
        InfoAudit ia = new InfoAudit();
        ia.setDimasukOleh(pengguna);
        ia.setTarikhMasuk(new Date());
        
        String[] param = getContext().getRequest().getParameterValues("rb");
        for (String id : param) {
//            HakmilikPihakBerkepentingan hp = hakmilikPihakBerkepentinganDAO.findById(Long.parseLong(id));
            HakmilikPihakBerkepentingan hp = hakmilikPihakKepentinganService.searchById(id);

            if (hp == null) continue;
            List<PermohonanAtasPihakBerkepentingan> list = permohonan.getSenaraiPermohonanAtasPihakBerkepentingan();
            boolean flag = false;

            for(PermohonanAtasPihakBerkepentingan _p : list) {
                if (_p == null) continue;
                if (_p.getPihakBerkepentingan().getIdHakmilikPihakBerkepentingan() == hp.getIdHakmilikPihakBerkepentingan()) {
                    flag = true;
                    break;
                }
            }
            if (flag) continue;

            PermohonanAtasPihakBerkepentingan pap = new PermohonanAtasPihakBerkepentingan();
            pap.setPermohonan(permohonan);
            pap.setPihakBerkepentingan(hp);
            pap.setInfoAudit(ia);
            senarai.add(pap);
        }

        if (!senarai.isEmpty()) {
            mapService.save(senarai);
        }
        return new RedirectResolution(PihakBerkepentinganActionBean.class, "showPihakBerkepentinganKaveatForm");
    }

    public Resolution savePemohon() {
        PermohonanPihak pp = new PermohonanPihak();
        InfoAudit ia = new InfoAudit();
        ia.setDimasukOleh(pengguna);
        ia.setTarikhMasuk(new java.util.Date());
        KodJenisPihakBerkepentingan pemegangAmanah = kodJenisPihakBerkepentinganDAO.findById("PA");
        Hakmilik hk = null;
        if (permohonan != null) {
            if (permohonan.getSenaraiHakmilik().size() > 0) {
                hk = permohonan.getSenaraiHakmilik().get(0).getHakmilik();
            }
        }
        Pihak p = pihakPemberi.getPihak();
        pp.setPihak(p);
        pp.setHakmilik(hk);
        pp.setJenis(pemegangAmanah);
        pp.setInfoAudit(ia);
        pp.setPermohonan(permohonan);
        //FIXME :
        pp.setSyerPembilang(0);
        pp.setSyerPenyebut(0);
        pp.setCawangan(pengguna.getKodCawangan());
        permohonanPihakService.saveOrUpdate(pp);

        return new RedirectResolution(PihakBerkepentinganActionBean.class, "showPihakBerkepentinganPemegangAmanahForm");
    }

    public Resolution deletePemohon() {
        String idPemohon = getContext().getRequest().getParameter("idPemohon");
        if (StringUtils.isNotBlank(idPemohon)) {
            Pemohon pemohon = pemohonDAO.findById(idPemohon);
            if (pemohon != null) {
                pemohonDAO.delete(pemohon);
            }
        }
        return new RedirectResolution(PihakBerkepentinganActionBean.class, "showPihakBerkepentinganPemegangAmanahForm");
    }

    public Resolution savePNPA() {
        Hakmilik hk = null;
        InfoAudit au = new InfoAudit();
        au.setTarikhMasuk(new Date());
        au.setDimasukOleh(pengguna);
        if (permohonan.getSenaraiHakmilik().size() > 0) {
            hk = permohonan.getSenaraiHakmilik().get(0).getHakmilik();
        }

        if (permohonan != null) {
            if (pihakPemberi != null) {
                Pemohon pemohon = new Pemohon();
                pemohon.setCawangan(hk.getCawangan());
                pemohon.setPermohonan(permohonan);
                pemohon.setPihak(pihakPemberi.getPihak());
                pemohon.setInfoAudit(au);
                pemohonDAO.saveOrUpdate(pemohon);

            }

            if (pihakPemegang != null) {
                PermohonanPihak pp = new PermohonanPihak();
                pp.setHakmilik(hk);
                pp.setPihak(pihakPemegang.getPihak());
                pp.setInfoAudit(au);
                pp.setJenis(kodJenisPihakBerkepentinganDAO.findById("PA"));
                pp.setSyerPembilang(pihakPemegang.getSyerPembilang());
                pp.setSyerPenyebut(pihakPemegang.getSyerPenyebut());
                pp.setCawangan(hk.getCawangan());
                pp.setPermohonan(permohonan);
                permohonanPihakService.saveOrUpdate(pp);
            }
        }
        return new RedirectResolution(PihakBerkepentinganActionBean.class, "showPihakBerkepentinganPemegangAmanahForm");
    }

    public Resolution save() {

        err = new StringBuffer();

        InfoAudit au = new InfoAudit();
        au.setTarikhMasuk(new Date());
        au.setDimasukOleh(pengguna);

        logger.debug("pemohonan = " + permohonan);
        List<HakmilikPermohonan> senaraiHakmilikTerlibat = permohonan.getSenaraiHakmilik();

        String[] pemegang = getContext().getRequest().getParameterValues("idpemegang");
        String[] pemberi = getContext().getRequest().getParameterValues("idpemberi");
        String copyToAll = getContext().getRequest().getParameter("copyToAll");

        String idHakmilik = getContext().getRequest().getParameter("idHakmilik");

        if (pemegang != null && pemegang.length > 0) {
            for (String id : pemegang) {
                logger.debug("id pemegang=" + id);
                HakmilikPihakBerkepentingan hp = hakmilikPihakKepentinganService.searchById(id);
                logger.debug("hp is null = " + ((hp == null) ? "true":"false") );
                if (hp == null) continue;

                logger.debug("hp pemegang found");

                PermohonanPihak pp = new PermohonanPihak();
                pp.setHakmilik(hp.getHakmilik());
                pp.setPihak(hp.getPihak());
                pp.setInfoAudit(au);
                pp.setJenis(kodJenisPihakBerkepentinganDAO.findById("PA"));
                pp.setSyerPembilang(hp.getSyerPembilang()==null?0:hp.getSyerPembilang());
                pp.setSyerPenyebut(hp.getSyerPenyebut()==null?0:hp.getSyerPenyebut());
                pp.setCawangan(hp.getHakmilik().getCawangan());
                pp.setPermohonan(permohonan);
                permohonanPihakService.saveOrUpdate(pp);
                if (StringUtils.isNotBlank(copyToAll)) {
                    for (HakmilikPermohonan h: senaraiHakmilikTerlibat) {
                        if (h == null || h.getHakmilik() == null) continue;
                        if (h.getHakmilik().equals(hp.getHakmilik())) continue;

                        HakmilikPihakBerkepentingan hpk
                                = hakmilikPihakKepentinganService
                                .findHakmilikPihakByIdPihakPMPPMG(hp.getPihak(), h.getHakmilik(), hp.getJenis().getKod());
                        if (hpk == null) {
                            err.append("ID pihak " + hp.getPihak().getNama() + " tidak dijumpai dalam hakmilik "
                                    + h.getHakmilik().getIdHakmilik() + "\n");
                            continue;
                        }
                        
                        pp = new PermohonanPihak();
                        pp.setHakmilik(h.getHakmilik());
                        pp.setPihak(hp.getPihak());
                        pp.setInfoAudit(au);
                        pp.setJenis(kodJenisPihakBerkepentinganDAO.findById("PA"));
                        pp.setSyerPembilang(hp.getSyerPembilang()==null?0:hp.getSyerPembilang());
                        pp.setSyerPenyebut(hp.getSyerPenyebut()==null?0:hp.getSyerPenyebut());
                        pp.setCawangan(h.getHakmilik().getCawangan());
                        pp.setPermohonan(permohonan);
                        permohonanPihakService.saveOrUpdate(pp);
                    }
                }
            }
        }

        if (pemberi != null && pemberi.length > 0) {
            for (String id : pemberi) {
                logger.debug("id pemberi=" + id);
                HakmilikPihakBerkepentingan hp = hakmilikPihakKepentinganService.searchById(id);
                logger.debug("hp is null = " + ((hp == null) ? "true":"false") );
                if (hp == null) continue;
                logger.debug("hp penerima found");

                Pemohon pemohon = new Pemohon();
                pemohon.setCawangan(hp.getHakmilik().getCawangan());
                pemohon.setPermohonan(permohonan);
                pemohon.setPihak(hp.getPihak());
                pemohon.setInfoAudit(au);
                pemohon.setHakmilik(hp.getHakmilik());
                pemohon.setSyerPembilang(hp.getSyerPembilang()==null?0:hp.getSyerPembilang());
                pemohon.setSyerPenyebut(hp.getSyerPenyebut()==null?0:hp.getSyerPenyebut());
                pemohon.setJenis(hp.getJenis());
                pemohonDAO.saveOrUpdate(pemohon);

                if (StringUtils.isNotBlank(copyToAll)) {
                    for (HakmilikPermohonan h: senaraiHakmilikTerlibat) {
                        if (h == null || h.getHakmilik() == null) continue;
                        if (h.getHakmilik().equals(hp.getHakmilik())) continue;

                        HakmilikPihakBerkepentingan hpk
                                = hakmilikPihakKepentinganService
                                .findHakmilikPihakByIdPihakPMPPMG(hp.getPihak(), h.getHakmilik(), hp.getJenis().getKod());
                        if (hpk == null) {
                            err.append("pihak " + hp.getPihak().getNama() + " tidak dijumpai dalam hakmilik "
                                    + h.getHakmilik().getIdHakmilik());
                            continue;
                        }

                        pemohon = new Pemohon();
                        pemohon.setCawangan(h.getHakmilik().getCawangan());
                        pemohon.setPermohonan(permohonan);
                        pemohon.setPihak(hp.getPihak());
                        pemohon.setInfoAudit(au);
                        pemohon.setHakmilik(h.getHakmilik());
                        pemohon.setSyerPembilang(hp.getSyerPembilang()==null?0:hp.getSyerPembilang());
                        pemohon.setSyerPenyebut(hp.getSyerPenyebut()==null?0:hp.getSyerPenyebut());
                        pemohon.setJenis(hp.getJenis());
                        pemohonDAO.saveOrUpdate(pemohon);
                    }
                }

            }
        }        

//        return new RedirectResolution(PihakBerkepentinganActionBean.class, "showPihakBerkepentinganPemegangAmanahForm");
        return new RedirectResolution(PihakKepentinganAction.class, "pihakPemegangAmanah")
                .addParameter("idHakmilik", idHakmilik).addParameter("err", err.toString());
    }

    public Resolution deleteMohonAtasPihakKaveat() {
        String idPemohon = getContext().getRequest().getParameter("id");
        if (idPemohon != null) {
            mapService.delete(Long.valueOf(idPemohon));
        }
        return new RedirectResolution(PihakBerkepentinganActionBean.class, "showPihakBerkepentinganKaveatForm");
    }

    @Before(stages = {LifecycleStage.BindingAndValidation}, on ={"!saveAtasPihakMultiple"})
    public void rehydrate() {
        pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = permohonanDAO.findById(idPermohonan);
        String id = (String) getContext().getRequest().getParameter("rb"); //HACK
        String id2 = (String) getContext().getRequest().getParameter("rb_pemegang");
        if (StringUtils.isNotBlank(id)) {
            pihakPemberi = hakmilikPihakBerkepentinganDAO.findById(Long.parseLong(id));
        }

        if (StringUtils.isNotBlank(id2)) {
            pihakPemegang = hakmilikPihakBerkepentinganDAO.findById(Long.parseLong(id2));
        }
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
}
