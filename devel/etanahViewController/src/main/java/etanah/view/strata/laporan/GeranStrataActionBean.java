/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.strata.laporan;

import able.stripes.AbleActionBean;
import etanah.view.etanahActionBeanContext;
import com.google.inject.Inject;
import etanah.dao.*;
import etanah.model.*;
import etanah.model.strata.*;
import java.util.*;
import etanah.service.*;
import etanah.service.common.*;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.action.StreamingResolution;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.hibernate.Transaction;
import able.stripes.JSP;
import org.hibernate.Session;

@UrlBinding("/strata/Geranstrata")
public class GeranStrataActionBean extends AbleActionBean {

    private static final Logger LOG = Logger.getLogger(GeranStrataActionBean.class);
    @Inject
    private etanah.Configuration conf;
    @Inject
    etanah.kodHasilConfig khconf;
    @Inject
    DokumenDAO dokumenDAO;
    @Inject
    DokumenCapaianDAO dokumenCapaianDAO;
    @Inject
    DokumenCapaianService dokumenCapaianService;
    private List<DokumenCapaian> senaraiDokumenCapai;
    @Inject
    KodStatusDokumenCapaiDAO kodStatusDokumenCapaiDAO;
    private String idHakmilikInduk;
    private String idHakmilikStrata;
    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;
    @Inject
    KodCawanganDAO kodCawanganDAO;
    @Inject
    StrataPtService strataPtService;
    @Inject
    PenggunaDAO penggunaDAO;
    private List<Hakmilik> senaraiHakmilik;
    private List<Hakmilik> senaraiHakmilikProv = new ArrayList();
    private List<HakmilikTukarGantiStrata> senaraiTkrganti = new ArrayList();
    private List<HakmilikTukarGantiStrata> senaraiTkrgantiProv = new ArrayList();
    private List<HakmilikTukarGantiStrata> senaraiTkrganti2k3k = new ArrayList();
    private List<Dokumen> senaraiDokumen = new ArrayList();
    private List<Dokumen> senaraiDokumenKK = new ArrayList();
    private List<Dokumen> senaraiDokumenBSK = new ArrayList();
    private List<Dokumen> senaraiDokumenBSKK = new ArrayList();
    private List<Dokumen> senaraiDokumenPSK = new ArrayList();
    private List<Dokumen> senaraiDokumen2K3K = new ArrayList();
    private List<Dokumen> senaraiDokumenProv = new ArrayList();
    private String sbb_cetakan_semula;
    private int bilHakmilik;

    @DefaultHandler
    public Resolution showForm() {
        LOG.info("showForm");
        idHakmilikInduk = null;
        idHakmilikStrata = null;
        bilHakmilik = 0;
        String idHakmilik = getContext().getRequest().getParameter("bilHakmilik");
        if (idHakmilik != null) {
            bilHakmilik = Integer.parseInt(idHakmilik);
        } else {
            bilHakmilik = 5;
        }
        return new ForwardResolution("/WEB-INF/jsp/strata/Laporan/geran_strata.jsp");
    }

    public Resolution simpanSebab() {
        LOG.info("simpanSebab");
        String idHakmilik = getContext().getRequest().getParameter("idHakmilik");
        LOG.info("idHakmilik------" + idHakmilik);
        return new ForwardResolution("/WEB-INF/jsp/strata/Laporan/geran_strata.jsp");
    }

    public Resolution cetakSemula() {
        Pengguna pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        String sbbCetakanSemula = getContext().getRequest().getParameter("sbb_cetakan_semula");
        String idDokumen = getContext().getRequest().getParameter("id_dokumen");
        String idHakmilik = getContext().getRequest().getParameter("idHakmilik");
//        idHakmilikStrata = getContext().getRequest().getParameter("idHakmilikStrata");
        LOG.info("idHakmilik" + idHakmilik);
        Dokumen d = dokumenDAO.findById(Long.parseLong(idDokumen));

        if (d == null) {
            return new StreamingResolution("text/plain", "2");
        }

//        HakmilikTukarGantiStrata hmTG = strataPtService.findHmStrTG(h.getIdHakmilik());
//        if (hmTG == null) {
//            hmTG.setTarikhTukarganti2k(now);
//            hmTG.setTarikhTukarganti4k(now);
//            hmTG.setVersi2k(1);
//            hmTG.setVersi4k(1);
//            hmTG.setInfoAudit(ia2);
//            strataPtService.simpanHmTukarGantiStrata(hmTG);
//        }
        List<DokumenCapaian> dc2 = strataPtService.findDokumenCapaian(d.getIdDokumen());
        int count = 0;
        for (DokumenCapaian dc1 : dc2) {
            LOG.info(dc1.getAlasan());
            String alasan = "CETAKAN SEMULA [ " + sbbCetakanSemula + " ]";
            if (dc1.getAlasan().equals(alasan)) {
                count++;
                return new StreamingResolution("text/plain", "0");
            }
        }
        if (count == 0) {
            DokumenCapaian dc = new DokumenCapaian();

            if (StringUtils.isNotBlank(sbbCetakanSemula)) {
                dc.setAlasan("CETAKAN SEMULA [ " + sbbCetakanSemula + " ]");
            } else {
                dc.setAlasan("Cetakan Dokumen");
            }

            dc.setDokumen(d);
            dc.setAktiviti(kodStatusDokumenCapaiDAO.findById("CD"));
            InfoAudit ia = new InfoAudit();
            ia.setDimasukOleh(pengguna);
            ia.setTarikhMasuk(new java.util.Date());
            dc.setInfoAudit(ia);

            Session s = sessionProvider.get();
            Transaction tx = s.beginTransaction();
            dokumenCapaianDAO.save(dc);

            Dokumen dk = strataPtService.findDokumen(dc.getDokumen().getIdDokumen());

            if (!idHakmilik.isEmpty()) {
                List<HakmilikTukarGantiStrata> hmTG = strataPtService.findHmStrTGbyInduk(idHakmilik);

                for (HakmilikTukarGantiStrata hmTGStr : hmTG) {
                    hmTGStr.setTarikhCetak2k(new java.util.Date());
                    strataPtService.simpanHmTukarGantiStrata(hmTGStr);
                }
            }

            if (!idHakmilik.isEmpty()) {
                HakmilikTukarGantiStrata hmTG = strataPtService.findHmStrTG(idHakmilik);

                if (hmTG != null) {
                    hmTG.setTarikhCetak4k(new java.util.Date());
                    strataPtService.simpanHmTukarGantiStrata(hmTG);
                }
            }


            tx.commit();
        }

        return new StreamingResolution("text/plain", "1");
    }

    public Resolution cari() {

        idHakmilikInduk = getContext().getRequest().getParameter("idHakmilikInduk");
        idHakmilikStrata = null;

        String idHakmilik = getContext().getRequest().getParameter("bilHakmilik");
        if (idHakmilik != null || !idHakmilik.isEmpty() || idHakmilik.equals("")) {
            bilHakmilik = Integer.parseInt(idHakmilik);
        } else {
            bilHakmilik = 5;
        }

        sbb_cetakan_semula = null;

        LOG.info("idHakmilikInduk" + idHakmilikInduk);
        LOG.info("idHakmilikStrata" + idHakmilikStrata);

        if (!idHakmilikInduk.isEmpty()) {
            Hakmilik induk = strataPtService.findInfoByIdHakmilikInduk(idHakmilikInduk);
            if (induk != null) {
                senaraiHakmilik = strataPtService.findIdHakmilikByIdHakmilikIndukversi1(idHakmilikInduk);
                senaraiHakmilikProv = strataPtService.findHakmilibyParentProv(idHakmilikInduk);
                senaraiTkrganti2k3k = strataPtService.findHakmilikTkrganti(idHakmilikInduk);
                senaraiTkrganti = strataPtService.findHakmilikTkrganti(idHakmilikInduk);
                if (senaraiHakmilik.isEmpty()) {
                    addSimpleError("Hakmilik Strata Untuk Hakmilik Ini Belum Ditukarganti.");

                    if (induk != null) {
                        if (induk.getNoVersiIndeksStrata() != 0) {

                            List<Dokumen> ls2 = new ArrayList<Dokumen>();
                            ls2 = strataPtService.findGeranStrata2k3k(idHakmilikInduk);
                            for (Dokumen dok : ls2) {
                                senaraiDokumen2K3K.add(dok);
                            }
                        } else {
                            addSimpleError("Borang 2K dan 3K Untuk Hakmilik Ini Belum Ditukarganti.");
                        }
                    }

                } else {
                    List<String> senaraiHm = new ArrayList<String>();
                    for (Hakmilik hm : senaraiHakmilik) {
                        senaraiHm.add(hm.getIdHakmilik());
                    }
                    senaraiDokumen = strataPtService.findGeranStrataDokumen(senaraiHm);
                    senaraiDokumenKK = strataPtService.findGeranStrataDokumenKK(senaraiHm);
                    senaraiDokumenBSK = strataPtService.findGeranStrataDokumenBSK(senaraiHm);
                    senaraiDokumenBSKK = strataPtService.findGeranStrataDokumenBSKK(senaraiHm);
                    senaraiDokumenPSK = strataPtService.findGeranStrataDokumenPSK(senaraiHm);
//                    List<Dokumen> ls = new ArrayList<Dokumen>();
//                    int count = 0;
//                    for (Hakmilik hm : senaraiHakmilik) {
//
//                        LOG.info(hm.getIdHakmilik());
//                        if (hm.getKodKategoriBangunan().getKod().equals("B") || hm.getKodKategoriBangunan().getKod().equals("L")) {
//                            ls = strataPtService.findGeranStrata(hm.getIdHakmilik());
//                            for (Dokumen dok : ls) {
//                                senaraiDokumen.add(dok);
//                            }
//                        }
//                        else {
//                            ls = strataPtService.findGeranStrataProv(hm.getIdHakmilikInduk());
//                            if (count == 0) {
//                                for (Dokumen dok : ls) {
//                                    senaraiDokumenProv.add(dok);
//                                }
//                                count++;
//                            }
//                            LOG.info("senaraiDokumenProv++" + senaraiDokumenProv.size());
//                        }
//                    }
                    if (senaraiHakmilik.get(0).getNoVersiIndeksStrata() != null) {
                        if (senaraiHakmilik.get(0).getNoVersiIndeksStrata() != 0) {

                            List<Dokumen> ls2 = new ArrayList<Dokumen>();
                            ls2 = strataPtService.findGeranStrata2k3k(senaraiHakmilik.get(0).getIdHakmilikInduk());
                            for (Dokumen dok : ls2) {
                                senaraiDokumen2K3K.add(dok);
                            }
                        }
                    } else {
                        addSimpleError("Borang 2K dan 3K Untuk Hakmilik Ini Belum Ditukarganti.");
                    }
                }
                if (!senaraiHakmilikProv.isEmpty()) {
                    List<Dokumen> ls = new ArrayList<Dokumen>();
                    int count = 0;
                    for (Hakmilik hm : senaraiHakmilikProv) {
                        ls = strataPtService.findGeranStrataProv(hm.getIdHakmilikInduk());
                        if (count == 0) {
                            HakmilikTukarGantiStrata hmTG = strataPtService.hakmilikTkrgantiStrata(hm.getIdHakmilik());
                            for (Dokumen dok : ls) {
                                senaraiDokumenProv.add(dok);
                            }
                            count++;
                            senaraiTkrgantiProv.add(hmTG);
                        }
                    }
                }
            } else {
                addSimpleError("Id Hakmilik Ini " + idHakmilikInduk + " Tidak Dijumpai");
                idHakmilikInduk = null;
                idHakmilikStrata = null;
            }

        } else {
            String idHakmilikm = getContext().getRequest().getParameter("bilHakmilik");
            if (idHakmilikm != null) {
                bilHakmilik = Integer.parseInt(idHakmilikm);
            } else {
                bilHakmilik = 5;
            }
            senaraiTkrganti.clear();
            List<String> senaraiHm2 = new ArrayList<String>();
            for (int i = 0; i < bilHakmilik; i++) {
                String idHakmilikStrata = getContext().getRequest().getParameter("idHakmilikStrata" + i);
                Hakmilik idStrata = strataPtService.findInfoByIdHakmilik(idHakmilikStrata);
                LOG.info("idStrata--" + idStrata);
                if (!idHakmilikStrata.isEmpty()) {
                    if (idStrata != null) {
                        List<HakmilikTukarGantiStrata> senaraiTkrgantiStrata = strataPtService.findHakmilikTkrgantiStrata(idStrata.getIdHakmilik());
                        for (HakmilikTukarGantiStrata hmStr : senaraiTkrgantiStrata) {
                            senaraiTkrganti.add(hmStr);
                        }
                        LOG.info("senaraiTkrganti=======" + senaraiTkrganti.size());
                        int count = 0;
                        if (idStrata.getNoVersiDhde() != 0) {
                            List<Dokumen> ls3 = new ArrayList<Dokumen>();

                            if (idStrata.getKodKategoriBangunan().getKod().equals("B") || idStrata.getKodKategoriBangunan().getKod().equals("L")) {
                                ls3 = strataPtService.findGeranStrata(idStrata.getIdHakmilik());
//                                for (Dokumen dok : ls3) {
//                                    senaraiDokumen.add(dok);
//                                }

                                senaraiHm2.add(idStrata.getIdHakmilik());

                            }

                        } else {
                            addSimpleError("Id Hakmilik Strata Ini " + idHakmilikStrata + " Belum Ditukarganti.");
                            idHakmilikInduk = null;
                            idHakmilikStrata = null;
                        }
                    } else {
                        addSimpleError("Id Hakmilik Strata Ini " + idHakmilikStrata + " Tidak Dijumpai");
                        idHakmilikInduk = null;
                        idHakmilikStrata = null;
                    }
                }
            }
            senaraiDokumen = strataPtService.findGeranStrataDokumen(senaraiHm2);
            senaraiDokumenKK = strataPtService.findGeranStrataDokumenKK(senaraiHm2);
            senaraiDokumenBSK = strataPtService.findGeranStrataDokumenBSK(senaraiHm2);
            senaraiDokumenBSKK = strataPtService.findGeranStrataDokumenBSKK(senaraiHm2);
            senaraiDokumenPSK = strataPtService.findGeranStrataDokumenPSK(senaraiHm2);
            if (senaraiDokumen.isEmpty()) {
                addSimpleError("Id Hakmilik Strata Ini Belum Ditukarganti.");
            }
        }
        return new ForwardResolution("/WEB-INF/jsp/strata/Laporan/geran_strata.jsp");
    }

    public Resolution viewSejarahPaparan() {
        String idDokumen = getContext().getRequest().getParameter("idDokumen");
        senaraiDokumenCapai = dokumenCapaianService.findByIdDokumenAndPD(idDokumen);
        return new JSP("daftar/utiliti/view_sejarah_paparan.jsp").addParameter("popup", "true");
    }

    public String getIdHakmilikInduk() {
        return idHakmilikInduk;
    }

    public void setIdHakmilikInduk(String idHakmilikInduk) {
        this.idHakmilikInduk = idHakmilikInduk;
    }

    public String getIdHakmilikStrata() {
        return idHakmilikStrata;
    }

    public void setIdHakmilikStrata(String idHakmilikStrata) {
        this.idHakmilikStrata = idHakmilikStrata;
    }

    public List<Hakmilik> getSenaraiHakmilik() {
        return senaraiHakmilik;
    }

    public void setSenaraiHakmilik(List<Hakmilik> senaraiHakmilik) {
        this.senaraiHakmilik = senaraiHakmilik;
    }

    public String getSbb_cetakan_semula() {
        return sbb_cetakan_semula;
    }

    public void setSbb_cetakan_semula(String sbb_cetakan_semula) {
        this.sbb_cetakan_semula = sbb_cetakan_semula;
    }

    public List<Dokumen> getSenaraiDokumen() {
        return senaraiDokumen;
    }

    public void setSenaraiDokumen(List<Dokumen> senaraiDokumen) {
        this.senaraiDokumen = senaraiDokumen;
    }

    public List<DokumenCapaian> getSenaraiDokumenCapai() {
        return senaraiDokumenCapai;
    }

    public void setSenaraiDokumenCapai(List<DokumenCapaian> senaraiDokumenCapai) {
        this.senaraiDokumenCapai = senaraiDokumenCapai;
    }

    public List<Dokumen> getSenaraiDokumen2K3K() {
        return senaraiDokumen2K3K;
    }

    public void setSenaraiDokumen2K3K(List<Dokumen> senaraiDokumen2K3K) {
        this.senaraiDokumen2K3K = senaraiDokumen2K3K;
    }

    public List<Dokumen> getSenaraiDokumenProv() {
        return senaraiDokumenProv;
    }

    public void setSenaraiDokumenProv(List<Dokumen> senaraiDokumenProv) {
        this.senaraiDokumenProv = senaraiDokumenProv;
    }

    public int getBilHakmilik() {
        return bilHakmilik;
    }

    public void setBilHakmilik(int bilHakmilik) {
        this.bilHakmilik = bilHakmilik;
    }

    public List<HakmilikTukarGantiStrata> getSenaraiTkrganti() {
        return senaraiTkrganti;
    }

    public void setSenaraiTkrganti(List<HakmilikTukarGantiStrata> senaraiTkrganti) {
        this.senaraiTkrganti = senaraiTkrganti;
    }

    public List<HakmilikTukarGantiStrata> getSenaraiTkrganti2k3k() {
        return senaraiTkrganti2k3k;
    }

    public void setSenaraiTkrganti2k3k(List<HakmilikTukarGantiStrata> senaraiTkrganti2k3k) {
        this.senaraiTkrganti2k3k = senaraiTkrganti2k3k;
    }

    public List<Hakmilik> getSenaraiHakmilikProv() {
        return senaraiHakmilikProv;
    }

    public void setSenaraiHakmilikProv(List<Hakmilik> senaraiHakmilikProv) {
        this.senaraiHakmilikProv = senaraiHakmilikProv;
    }

    public List<HakmilikTukarGantiStrata> getSenaraiTkrgantiProv() {
        return senaraiTkrgantiProv;
    }

    public void setSenaraiTkrgantiProv(List<HakmilikTukarGantiStrata> senaraiTkrgantiProv) {
        this.senaraiTkrgantiProv = senaraiTkrgantiProv;
    }

    public List<Dokumen> getSenaraiDokumenBSK() {
        return senaraiDokumenBSK;
    }

    public void setSenaraiDokumenBSK(List<Dokumen> senaraiDokumenBSK) {
        this.senaraiDokumenBSK = senaraiDokumenBSK;
    }

    public List<Dokumen> getSenaraiDokumenPSK() {
        return senaraiDokumenPSK;
    }

    public void setSenaraiDokumenPSK(List<Dokumen> senaraiDokumenPSK) {
        this.senaraiDokumenPSK = senaraiDokumenPSK;
    }

    public List<Dokumen> getSenaraiDokumenKK() {
        return senaraiDokumenKK;
    }

    public void setSenaraiDokumenKK(List<Dokumen> senaraiDokumenKK) {
        this.senaraiDokumenKK = senaraiDokumenKK;
    }

    public List<Dokumen> getSenaraiDokumenBSKK() {
        return senaraiDokumenBSKK;
    }

    public void setSenaraiDokumenBSKK(List<Dokumen> senaraiDokumenBSKK) {
        this.senaraiDokumenBSKK = senaraiDokumenBSKK;
    }
    
}
