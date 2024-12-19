/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.strata;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.dao.FasaPermohonanDAO;
import etanah.dao.KodKeputusanDAO;
import etanah.dao.PermohonanDAO;
import etanah.dao.PermohonanKertasKandunganDAO;
import etanah.model.FasaPermohonan;
import etanah.model.HakmilikPermohonan;
import etanah.model.InfoAudit;
import etanah.model.KodKeputusan;
import etanah.model.KodUrusan;
import etanah.model.LanjutanTempoh;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.model.PermohonanKertas;
import etanah.model.PermohonanKertasKandungan;
import etanah.service.BPelService;
import etanah.service.StrataPtService;
import etanah.view.etanahActionBeanContext;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

/**
 *
 * @author faidzal
 */
@UrlBinding("/strata/syorrayuan")
public class MaklumatSyorActionBean extends AbleActionBean {

    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    StrataPtService strService;
    @Inject
    PermohonanKertasKandunganDAO permohonanKertasKandDAO;
    @Inject
    KodKeputusanDAO kodKpsnDAO;
    @Inject
    etanah.Configuration conf;
    @Inject
    FasaPermohonanDAO mohonfasaDAO;
    private Permohonan permohonan;
    private LanjutanTempoh lanjutTempoh;
    private PermohonanKertas mohonKertas = new PermohonanKertas();
    private List<PermohonanKertasKandungan> listMohonKertasKand = new ArrayList<PermohonanKertasKandungan>();
    private String syor;
    private String ayatSyor;
    private String syorNama;
    private Date tarikhPohon = new Date();
    private Integer noHakmilik;
    private static final Logger LOG = Logger.getLogger(MaklumatSyorActionBean.class);

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {
        Pengguna pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);

        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        mohonKertas = strService.findKertas(idPermohonan);
        permohonan = permohonanDAO.findById(idPermohonan);
        if (mohonKertas != null) {
            LOG.info("----------- mohonKertas != null -------------");
            listMohonKertasKand = strService.findByIdLapor(mohonKertas.getIdKertas(), 3);

        }

        if (permohonan.getKodUrusan().getKod().equals("RTHS") || permohonan.getKodUrusan().getKod().equals("RTPS") || permohonan.getKodUrusan().getKod().equals("PNB")) {
            LOG.info("==== FIND FasaPermohonan by stageId = semaklaporan =====");
            FasaPermohonan mohonFasa = strService.findFasaPermohonanByIdAliran(idPermohonan, "semaklaporan");
            if (mohonFasa != null) {
                if (mohonFasa.getKeputusan() != null) {
                    syor = mohonFasa.getKeputusan().getKod();
                    syorNama = mohonFasa.getKeputusan().getNama();
                }
            }

        }
        if (conf.getProperty("kodNegeri").equals("05")) {
            if (permohonan.getKodUrusan().getKod().equals("RBHS")
                    || permohonan.getKodUrusan().getKod().equals("RMHS1")
                    || permohonan.getKodUrusan().getKod().equals("RMH1A")
                    || permohonan.getKodUrusan().getKod().equals("RMHS5")
                    || permohonan.getKodUrusan().getKod().equals("RMHS6")
                    || permohonan.getKodUrusan().getKod().equals("RMHS7")) {
                LOG.info("==== FIND FasaPermohonan by stageId = semakkertasptg =====");
                FasaPermohonan mohonFasa = strService.findFasaPermohonanByIdAliran(idPermohonan, "semakkertasptg");
                if (mohonFasa != null) {
                    if (mohonFasa.getKeputusan() != null) {
                        syor = mohonFasa.getKeputusan().getKod();
                        syorNama = mohonFasa.getKeputusan().getNama();
                    } else {
                        syorNama = "_______";
                    }
                }
            }
        }

        if (conf.getProperty("kodNegeri").equals("04")) {
            if (permohonan.getKodUrusan().getKod().equals("RBHS")
                    || permohonan.getKodUrusan().getKod().equals("RMHS1")
                    || permohonan.getKodUrusan().getKod().equals("RMH1A")
                    || permohonan.getKodUrusan().getKod().equals("RMHS5")
                    || permohonan.getKodUrusan().getKod().equals("RMHS6")
                    || permohonan.getKodUrusan().getKod().equals("RMHS7")) {
                LOG.info("==== FIND FasaPermohonan by stageId = semakkertasptg =====");
                FasaPermohonan mohonFasa = strService.findFasaPermohonanByIdAliran(idPermohonan, "semakkertasptg");
                if (mohonFasa != null) {
                    if (mohonFasa.getKeputusan() != null) {
                        syor = mohonFasa.getKeputusan().getKod();
                        syorNama = mohonFasa.getKeputusan().getNama();
                    }
                }
            }
        }

        HakmilikPermohonan mohonHakmilik = strService.findMohonHakmilik(idPermohonan);
        if (mohonHakmilik != null) {
            LanjutanTempoh lanjutTempoh = strService.findMohonLanjutTempoh(mohonHakmilik.getId());
            if (conf.getProperty("kodNegeri").equals("05")) {
                if (lanjutTempoh != null) {
                    noHakmilik = Integer.parseInt(mohonHakmilik.getHakmilik().getNoHakmilik().replaceAll("^0*", ""));
                    //commented by murali on 10/08/2012
//                ayatSyor = "Setelah dikaji dan diteliti, pihak pentadbiran ini mengesyorkan supaya permohonan " + permohonan.getKodUrusan().getNama() + " bagi Lot " + mohonHakmilik.getHakmilik().getNoLot() + ", " + mohonHakmilik.getHakmilik().getKodHakmilik().getNama() + " " + mohonHakmilik.getHakmilik().getNoHakmilik() + ", " + mohonHakmilik.getHakmilik().getBandarPekanMukim().getNama() + " sehingga " + strService.formatSDF(lanjutTempoh.getTarikhAkhirDipohon()) + " adalah " + syorNama + " kerana:";
                    ayatSyor = "Setelah dikaji dan diteliti, pihak pentadbiran ini mengesyorkan supaya permohonan " + permohonan.getKodUrusan().getNama() + " " + mohonHakmilik.getHakmilik().getKodHakmilik().getNama() + " " + noHakmilik + ", " + mohonHakmilik.getHakmilik().getBandarPekanMukim().getNama() + " adalah " + syorNama + " kerana:";

                } else {
                    noHakmilik = Integer.parseInt(permohonan.getSenaraiHakmilik().get(0).getHakmilik().getNoHakmilik().replaceAll("^0*", ""));
                    //commented by murali on 31/07/2012
//                ayatSyor = "Setelah dikaji dan diteliti, pihak pentadbiran ini mengesyorkan supaya permohonan " + permohonan.getKodUrusan().getNama() + " bagi Lot " + permohonan.getSenaraiHakmilik().get(0).getHakmilik().getNoLot() + ", " + permohonan.getSenaraiHakmilik().get(0).getHakmilik().getKodHakmilik().getNama() + " " + permohonan.getSenaraiHakmilik().get(0).getHakmilik().getNoHakmilik() + ", " + permohonan.getSenaraiHakmilik().get(0).getHakmilik().getBandarPekanMukim().getNama() + " sehingga TIADA TARIKH adalah TIADA KEPUTUSAN kerana:";
                    ayatSyor = "Setelah dikaji dan diteliti, pihak pentadbiran ini mengesyorkan supaya permohonan " + permohonan.getKodUrusan().getNama() + " " + permohonan.getSenaraiHakmilik().get(0).getHakmilik().getKodHakmilik().getNama() + " " + noHakmilik + ", " + permohonan.getSenaraiHakmilik().get(0).getHakmilik().getBandarPekanMukim().getNama() + " adalah " + syorNama + " kerana:";

                }
            }
            if (conf.getProperty("kodNegeri").equals("04")) {
                if (lanjutTempoh != null) {
                    noHakmilik = Integer.parseInt(mohonHakmilik.getHakmilik().getNoHakmilik().replaceAll("^0*", ""));
                    //commented by murali on 10/08/2012
//                ayatSyor = "Setelah dikaji dan diteliti, pihak pentadbiran ini mengesyorkan supaya permohonan " + permohonan.getKodUrusan().getNama() + " bagi Lot " + mohonHakmilik.getHakmilik().getNoLot() + ", " + mohonHakmilik.getHakmilik().getKodHakmilik().getNama() + " " + mohonHakmilik.getHakmilik().getNoHakmilik() + ", " + mohonHakmilik.getHakmilik().getBandarPekanMukim().getNama() + " sehingga " + strService.formatSDF(lanjutTempoh.getTarikhAkhirDipohon()) + " adalah " + syorNama + " kerana:";
//                    ayatSyor = "Setelah dikaji dan diteliti, pihak pentadbiran ini mengesyorkan supaya permohonan " + permohonan.getKodUrusan().getNama() + " " + mohonHakmilik.getHakmilik().getKodHakmilik().getNama() + " " + mohonHakmilik.getHakmilik().getNoHakmilik() + ", " + mohonHakmilik.getHakmilik().getBandarPekanMukim().getNama() + " adalah " + syorNama + " kerana:";
                    ayatSyor = "Setelah dikaji dan diteliti, pihak pentadbiran ini mengesyorkan supaya permohonan " + permohonan.getKodUrusan().getNama() + " " + mohonHakmilik.getHakmilik().getKodHakmilik().getNama() + " " + noHakmilik + ", " + mohonHakmilik.getHakmilik().getBandarPekanMukim().getNama() + " adalah " + syorNama + " kerana:";


                } else {
                    noHakmilik = Integer.parseInt(permohonan.getSenaraiHakmilik().get(0).getHakmilik().getNoHakmilik().replaceAll("^0*", ""));
                    //commented by murali on 31/07/2012
//                ayatSyor = "Setelah dikaji dan diteliti, pihak pentadbiran ini mengesyorkan supaya permohonan " + permohonan.getKodUrusan().getNama() + " bagi Lot " + permohonan.getSenaraiHakmilik().get(0).getHakmilik().getNoLot() + ", " + permohonan.getSenaraiHakmilik().get(0).getHakmilik().getKodHakmilik().getNama() + " " + permohonan.getSenaraiHakmilik().get(0).getHakmilik().getNoHakmilik() + ", " + permohonan.getSenaraiHakmilik().get(0).getHakmilik().getBandarPekanMukim().getNama() + " sehingga TIADA TARIKH adalah TIADA KEPUTUSAN kerana:";
//                ayatSyor = "Setelah dikaji dan diteliti, pihak pentadbiran ini mengesyorkan supaya permohonan " + permohonan.getKodUrusan().getNama() + " " + permohonan.getSenaraiHakmilik().get(0).getHakmilik().getKodHakmilik().getNama() + " " + permohonan.getSenaraiHakmilik().get(0).getHakmilik().getNoHakmilik() + ", " + permohonan.getSenaraiHakmilik().get(0).getHakmilik().getBandarPekanMukim().getNama() + " adalah " + syorNama + " kerana:";
                    ayatSyor = "Setelah dikaji dan diteliti, pihak pentadbiran ini mengesyorkan supaya permohonan " + permohonan.getKodUrusan().getNama() + " " + permohonan.getSenaraiHakmilik().get(0).getHakmilik().getKodHakmilik().getNama() + " " + noHakmilik + ", " + permohonan.getSenaraiHakmilik().get(0).getHakmilik().getBandarPekanMukim().getNama() + " adalah " + syorNama + " kerana:";

                }
            }
        }

        lanjutTempoh = strService.findMohonLanjutTempoh(permohonan.getSenaraiHakmilik().get(0).getId());

        if (lanjutTempoh != null) {
            tarikhPohon = lanjutTempoh.getTarikhAkhirDipohon();
        }
    }

    @DefaultHandler
    public Resolution showForm() {
        getContext().getRequest().setAttribute("readOnly", Boolean.FALSE);
        return new JSP("strata/rayuan/syor_penolong.jsp").addParameter("tab", "true");
    }

    public Resolution showRayuanReadOnly() {
        getContext().getRequest().setAttribute("readOnly", Boolean.TRUE);
        return new JSP("strata/rayuan/syor_penolong.jsp").addParameter("tab", "true");
    }

    public Resolution showForm2() {
        getContext().getRequest().setAttribute("readOnly", Boolean.TRUE);
        return new JSP("strata/rayuan/syor_penolong.jsp").addParameter("tab", "true");
    }

    public Resolution tambahRow() {
        Pengguna pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);

        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        String taskId = (String) getContext().getRequest().getSession().getAttribute("taskId");
        String stageId = "";
        BPelService service = new BPelService();
        Permohonan permohonan = permohonanDAO.findById(idPermohonan);
        mohonKertas = strService.findKertas(idPermohonan);
        InfoAudit ia = new InfoAudit();
        ia.setDimasukOleh(pengguna);
        ia.setTarikhMasuk(new Date());
        if (mohonKertas == null) {
            mohonKertas = new PermohonanKertas();
            mohonKertas.setTajuk("Kertas Pertimbangan PTG");
            mohonKertas.setCawangan(permohonan.getCawangan());
            mohonKertas.setInfoAudit(ia);
            mohonKertas.setPermohonan(permohonan);
            strService.simpanPermohonanKertas(mohonKertas);
        }
        PermohonanKertasKandungan pkk = new PermohonanKertasKandungan();
        int index = 0;
        index = Integer.parseInt(getContext().getRequest().getParameter("index"));
        switch (index) {
            case 1:
                break;
            case 2:
                pkk = new PermohonanKertasKandungan();

                pkk.setSubtajuk(String.valueOf(lastNumberKertasKandungan(mohonKertas.getIdKertas(), 3)));
                System.out.println(String.valueOf(lastNumberKertasKandungan(mohonKertas.getIdKertas(), 3)));
                pkk.setBil((short) 3);
                listMohonKertasKand.add(pkk);
                break;

            default:
        }
        System.out.println(index);
        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        getContext().getRequest().setAttribute("readOnly", Boolean.FALSE);
        return new JSP("strata/rayuan/syor_penolong.jsp").addParameter("tab", "true");
    }

    private Integer lastNumberKertasKandungan(Long idKertas, int bil) {
        int last = 1;
        List<PermohonanKertasKandungan> listLastKandungan = strService.findByIdLapor(idKertas, bil);
        if (listLastKandungan.isEmpty()) {
            last = 1;
        } else {
            last = Integer.parseInt(listLastKandungan.get(listLastKandungan.size() - 1).getSubtajuk()) + 1;
        }
        return last;
    }

    public Resolution deleteRow() {
        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        String taskId = (String) getContext().getRequest().getSession().getAttribute("taskId");
        String stageId = "";
        BPelService service = new BPelService();

        String idKand = getContext().getRequest().getParameter("idKandungan");
        if (idKand != null) {
            PermohonanKertasKandungan plk = new PermohonanKertasKandungan();
            plk = permohonanKertasKandDAO.findById(Long.parseLong(idKand));
            if (plk != null) {

                try {
                    strService.deleteKertasKandungan(plk);
                } catch (Exception e) {
                }
            }
        }
        rehydrate();
        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        getContext().getRequest().setAttribute("readOnly", Boolean.FALSE);
        return new JSP("strata/rayuan/syor_penolong.jsp").addParameter("tab", "true");
    }

    public Resolution simpan() {
        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = permohonanDAO.findById(idPermohonan);
        mohonKertas = strService.findKertas(idPermohonan);
        Pengguna pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);

        InfoAudit ia = new InfoAudit();
        ia.setDimasukOleh(pengguna);
        ia.setTarikhMasuk(new Date());
        for (PermohonanKertasKandungan pkk : listMohonKertasKand) {
            ia.setTarikhKemaskini(new Date());
            pkk.setInfoAudit(ia);
            pkk.setKertas(mohonKertas);
            pkk.setCawangan(permohonan.getCawangan());
            if (StringUtils.isNotBlank(pkk.getKandungan())) {
                strService.simpanPermohonanKertasKandungan(pkk);
            }
        }

        FasaPermohonan mohonFasa = strService.findFasaPermohonanByIdAliran(idPermohonan, "semaklaporan"); // default initialization
        if (permohonan.getKodUrusan().getKod().equals("RTHS") || permohonan.getKodUrusan().getKod().equals("RTPS") || permohonan.getKodUrusan().getKod().equals("PNB")) {
            mohonFasa = strService.findFasaPermohonanByIdAliran(idPermohonan, "semaklaporan");
        }
        if (conf.getProperty("kodNegeri").equals("05")) {
            if (permohonan.getKodUrusan().getKod().equals("RBHS")
                    || permohonan.getKodUrusan().getKod().equals("RMHS1")
                    || permohonan.getKodUrusan().getKod().equals("RMH1A")
                    || permohonan.getKodUrusan().getKod().equals("RMHS5")
                    || permohonan.getKodUrusan().getKod().equals("RMHS6")
                    || permohonan.getKodUrusan().getKod().equals("RMHS7")) {
                mohonFasa = strService.findFasaPermohonanByIdAliran(idPermohonan, "semakkertasptg");
            }
        }

        if (conf.getProperty("kodNegeri").equals("04")) {
            if (permohonan.getKodUrusan().getKod().equals("RBHS")
                    || permohonan.getKodUrusan().getKod().equals("RMHS1")
                    || permohonan.getKodUrusan().getKod().equals("RMH1A")
                    || permohonan.getKodUrusan().getKod().equals("RMHS5")
                    || permohonan.getKodUrusan().getKod().equals("RMHS6")
                    || permohonan.getKodUrusan().getKod().equals("RMHS7")) {
                mohonFasa = strService.findFasaPermohonanByIdAliran(idPermohonan, "semakkertasptg");
            }
        }

        if (mohonFasa != null) {
            if (StringUtils.isNotBlank(syor)) {
                KodKeputusan kodKpsn = new KodKeputusan();
                kodKpsn = kodKpsnDAO.findById(syor);
                mohonFasa.setKeputusan(kodKpsn);
                System.out.println("-----kodKpsn-----" + kodKpsn);
                String ulas = "";
                for (PermohonanKertasKandungan ulasan : listMohonKertasKand) {
                    System.out.println("-----ulas------" + ulas);
                    System.out.println("-----ulasan.getKandungan()------" + ulasan.getKandungan());
                    System.out.println("-----ulasan.subtajuk------" + ulasan.getSubtajuk());
                    ulas += ulasan.getSubtajuk() + "." + ulasan.getKandungan() + "&#13;&#10;";
                }
                System.out.println("-----ulas-----" + ulas);
                mohonFasa.setUlasan(ulas);
                System.out.println("-----tarikh semasa-----" + new java.util.Date());
                mohonFasa.setTarikhKeputusan(new java.util.Date());
                mohonFasa = strService.saveFasaMohon(mohonFasa);
            }
        }
        rehydrate();
        addSimpleMessage("Maklumat berjaya disimpan");
        return new JSP("strata/rayuan/syor_penolong.jsp").addParameter("tab", "true");
    }

    public List<PermohonanKertasKandungan> getListMohonKertasKand() {
        return listMohonKertasKand;
    }

    public void setListMohonKertasKand(List<PermohonanKertasKandungan> listMohonKertasKand) {
        this.listMohonKertasKand = listMohonKertasKand;
    }

    public String getSyor() {
        return syor;
    }

    public Integer getNoHakmilik() {
        return noHakmilik;
    }

    public void setNoHakmilik(Integer noHakmilik) {
        this.noHakmilik = noHakmilik;
    }

    public void setSyor(String syor) {
        this.syor = syor;
    }

    public String getAyatSyor() {
        return ayatSyor;
    }

    public void setAyatSyor(String ayatSyor) {
        this.ayatSyor = ayatSyor;
    }

    public String getSyorNama() {
        return syorNama;
    }

    public void setSyorNama(String syorNama) {
        this.syorNama = syorNama;
    }
}
