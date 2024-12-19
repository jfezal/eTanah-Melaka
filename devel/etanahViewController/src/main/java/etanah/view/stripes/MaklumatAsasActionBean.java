/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.stripes;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.dao.HakmilikDAO;
import java.text.ParseException;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;
import etanah.model.Permohonan;
import etanah.dao.PermohonanDAO;
import etanah.model.Hakmilik;
import etanah.model.HakmilikPermohonan;
import etanah.service.RegService;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import net.sourceforge.stripes.action.RedirectResolution;
import etanah.model.KodSekatanKepentingan;
import etanah.model.KodSyaratNyata;
import org.apache.log4j.Logger;
import etanah.model.Pengguna;
import etanah.view.etanahActionBeanContext;
import etanah.model.KodPBT;
import etanah.view.ListUtil;
import etanah.model.KodLot;
import etanah.dao.KodLotDAO;
import etanah.model.HakmilikPihakBerkepentingan;
import etanah.model.InfoAudit;
import etanah.model.PermohonanPembetulanHakmilik;
import etanah.model.PermohonanRujukanLuar;
import etanah.service.daftar.PembetulanService;
import etanah.service.common.PermohonanRujukanLuarService;
import etanah.util.StringUtils;

/**
 *
 * @author khairil
 */
@UrlBinding("/pendaftaran/maklumat_asas")
public class MaklumatAsasActionBean extends AbleActionBean {

    private static final Logger logger = Logger.getLogger(MaklumatAsasActionBean.class);
    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    PembetulanService pembetulanService;
    @Inject
    PermohonanRujukanLuarService mrlService;
    @Inject
    HakmilikDAO hakmilikDAO;
    @Inject
    KodLotDAO kodLotDAO;
    @Inject
    RegService regService;
    @Inject
    ListUtil listUtil;
    @Inject
    etanah.Configuration conf;
    private Permohonan p;
//    String tarikhDaftar;
    private Hakmilik hakmilik;
    private List<HakmilikPermohonan> hakmilikPermohonanList;
    private List<KodSekatanKepentingan> listKodSekatan;
    private List<KodSyaratNyata> listKodSyaratNyata;
    private List<KodPBT> senaraiKodPBT;
//    String tarikhDaftar;
    Date tarikhDaftar;
    String tarikhDaftarAsal;
    String tarikhLuput;
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    String kodSekatan;
    String kodSyaratNyata;
    String kodKatTanah;
    String syaratNyata;
    String sekatan;
    String kodDaerah;
    String kodJenisHakmilik;
    String pegangan;
    int tempohPegangan;
    int tPegangan;
    String kodNegeri;
    private List<KodLot> senaraiKodLot;
    String jenisHakmilik;

    @DefaultHandler
    public Resolution showForm() {
        if (p.getSenaraiHakmilik().size() > 0) {
            Hakmilik h = p.getSenaraiHakmilik().get(0).getHakmilik();
            jenisHakmilik = h.getKodHakmilik().getKod();

        } else {
            addSimpleError("Sila Tambah hakmilik dahulu sebelum mengisi maklumat asas");
        }
        return new JSP("daftar/kemasukan_maklumat_asas.jsp").addParameter("tab", "true");
    }

    public Resolution showFormCariKodSekatan() {
        cariKodSekatan();
        return new JSP("common/carian_kodsekatan.jsp").addParameter("popup", "true");
    }

    public Resolution showFormCariKodSyaratNyata() {
        cariKodSyaratNyata();
        return new JSP("common/carian_kodsyaratnyata.jsp").addParameter("popup", "true");
    }

    public Resolution cariKodSekatan() {
        Pengguna peng = (Pengguna) context.getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        String kc = peng.getKodCawangan().getKod();
//        logger.debug("peng.getKodCawangan().getKod :" + kc);
//        logger.debug("kodSekatan :" + kodSekatan);
        if (kodSekatan != null) {
            listKodSekatan = regService.searchKodSekatan(kodSekatan, kc, sekatan);
            logger.debug("listKodSekatan.size :" + listKodSekatan.size());
            if (listKodSekatan.size() < 1) {
                addSimpleError("Kod Sekatan Tidak Sah");
            }
        } else {
            listKodSekatan = regService.searchKodSekatan("%", kc, sekatan);
//            addSimpleError("Sila Cari / Pilih Kod Sekatan");
            if (listKodSekatan.size() < 1) {
                addSimpleError("Kod Sekatan Tidak Sah");
            }
        }
        return new JSP("common/carian_kodsekatan.jsp").addParameter("popup", "true");
    }

    public Resolution cariKodSyaratNyata() {
        Pengguna peng = (Pengguna) context.getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        String kc = peng.getKodCawangan().getKod();
//        logger.debug("kodSyaratNyata : " + kodSyaratNyata);
        if (kodSyaratNyata != null) {
            listKodSyaratNyata = regService.searchKodSyaratNyata(kodSyaratNyata, kc, syaratNyata, kodKatTanah);
            logger.debug("listKodSyaratNyata.size : " + listKodSyaratNyata.size());
            if (listKodSyaratNyata.size() < 1) {
                addSimpleError("Kod Syarat Nyata Tidak Sah");
            }
        } else {
            listKodSyaratNyata = regService.searchKodSyaratNyata("%", kc, syaratNyata, kodKatTanah);
//            addSimpleError("Sila Cari / Pilih Kod Syarat Nyata");
            if (listKodSyaratNyata.size() < 1) {
                addSimpleError("Kod Syarat Nyata Tidak Sah");
            }
        }

        return new JSP("common/carian_kodsyaratnyata.jsp").addParameter("popup", "true");
    }

    public Resolution simpan() throws ParseException {
        logger.debug("masuk method save");
        logger.debug("tarikhDaftar :" + tarikhDaftar);
        logger.debug("tPegangan :" + tPegangan);
        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        Pengguna peng = (Pengguna) context.getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        Permohonan p = permohonanDAO.findById(idPermohonan);

        if (p.getKodUrusan().getKod().equals("PSPL")) {
            List<PermohonanPembetulanHakmilik> listHakmilikPPH = new ArrayList<PermohonanPembetulanHakmilik>();
            List<HakmilikPermohonan> senaraiHp = p.getSenaraiHakmilik();
            List<PermohonanRujukanLuar> listHakmilikPPH2 = new ArrayList<PermohonanRujukanLuar>();
            InfoAudit ia = new InfoAudit();
            ia.setDiKemaskiniOleh(peng);
            ia.setTarikhKemaskini(new java.util.Date());
            for (HakmilikPermohonan hp : senaraiHp) {
                PermohonanPembetulanHakmilik senaraiPPH = pembetulanService.findByidPidH(hp.getPermohonan().getIdPermohonan(), hp.getHakmilik().getIdHakmilik());
                if (senaraiPPH == null) {
                    PermohonanPembetulanHakmilik pph = new PermohonanPembetulanHakmilik();

                    pph.setPermohonan(p);
                    pph.setHakmilik(hp);
                    pph.setIdHakmilik(hp.getHakmilik().getIdHakmilik());

                    pph.setTarikhLuput(hp.getHakmilik().getTarikhLuput());
                    pph.setTarikhLuputSemasa(sdf.parse(tarikhLuput));

                    pph.setTarikhDaftar(hp.getHakmilik().getTarikhDaftar());

                    pph.setTempohPengangan(hp.getHakmilik().getTempohPegangan());
                    pph.setTempohPenganganSemasa(tempohPegangan);

                    pph.setInfoAudit(ia);
                    pph.setCawangan(p.getCawangan());

                    pembetulanService.simpanBetul(pph);
                } else {
                    senaraiPPH.setPermohonan(p);
                    senaraiPPH.setHakmilik(hp);
                    senaraiPPH.setIdHakmilik(hp.getHakmilik().getIdHakmilik());

                    senaraiPPH.setTarikhLuput(hp.getHakmilik().getTarikhLuput());
                    senaraiPPH.setTarikhLuputSemasa(sdf.parse(tarikhLuput));

                    senaraiPPH.setTarikhDaftar(hp.getHakmilik().getTarikhDaftar());

                    senaraiPPH.setTempohPengangan(hp.getHakmilik().getTempohPegangan());
                    senaraiPPH.setTempohPenganganSemasa(tempohPegangan);

                    senaraiPPH.setInfoAudit(ia);
                    senaraiPPH.setCawangan(p.getCawangan());

                    pembetulanService.simpanBetul(senaraiPPH);
                }
            }
            for (HakmilikPermohonan hp : senaraiHp) {
                PermohonanRujukanLuar senaraiPPH2 = mrlService.checkByIdHakmilikIdMohon(hp.getPermohonan().getIdPermohonan(), hp.getHakmilik().getIdHakmilik());
                if (senaraiPPH2 == null) {
                    PermohonanRujukanLuar mrl = new PermohonanRujukanLuar();

                    mrl.setTarikhTamat(sdf.parse(tarikhLuput));
                    mrl.setTempohTahun(tempohPegangan);
                    mrl.setInfoAudit(ia);
                    mrlService.save(mrl);
                } else {
                    senaraiPPH2.setTarikhTamat(sdf.parse(tarikhLuput));
                    senaraiPPH2.setTempohTahun(tempohPegangan);
                    senaraiPPH2.setInfoAudit(ia);
                    mrlService.save(senaraiPPH2);
                }
            }

        } else {
//            logger.debug("masuk method save");
//    logger.debug("tarikhDaftar :" + tarikhDaftar);
//    logger.debug("tPegangan :" + tPegangan);
//    String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
//    Permohonan p = permohonanDAO.findById(idPermohonan);
            List<Hakmilik> listHakmilik = new ArrayList<Hakmilik>();
            for (HakmilikPermohonan hakmilikPermohonan : hakmilikPermohonanList) {
                Hakmilik h = hakmilikPermohonan.getHakmilik();

                h.setPegangan(p.getSenaraiHakmilik().get(0).getHakmilik().getPegangan());

                h.setTarikhDaftar(tarikhDaftar);
                if (tarikhDaftarAsal != null) {
                    h.setTarikhDaftar(sdf.parse(tarikhDaftarAsal));
                }

                h.setTempohPegangan(tPegangan);

                if (tarikhLuput != null) {
                    h.setTarikhLuput(sdf.parse(tarikhLuput));
                }

                h.setNoPu(p.getSenaraiHakmilik().get(0).getHakmilik().getNoPu());

                listHakmilik.add(h);
            }
            regService.simpanHakmilikList(listHakmilik);

            hakmilik = listHakmilik.get(0);

//            addSimpleMessage("Kemasukan Data Berjaya");
        }

        addSimpleMessage(
                "Kemasukan Data Berjaya");

        return new RedirectResolution(MaklumatAsasActionBean.class
        ).addParameter(
                "tab", "true");
//        return new JSP("daftar/kemasukan_maklumat_asas.jsp").addParameter("tab", "true");
    }

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {

        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        kodNegeri = conf.getProperty("kodNegeri");
        logger.debug("IdPermohonan :" + idPermohonan);
//        hakmilik = new Hakmilik();
        if (idPermohonan != null) {
            p = permohonanDAO.findById(idPermohonan);
            List<HakmilikPermohonan> lh = new ArrayList();
            lh = p.getSenaraiHakmilik();
            if (p.getSenaraiHakmilik().size() > 0) {
                Hakmilik h = p.getSenaraiHakmilik().get(0).getHakmilik();
                kodDaerah = h.getDaerah().getKod();
                kodJenisHakmilik = h.getKodHakmilik().getKod();
            }
            //kodDaerah = p.getCawangan().getDaerah().getKod();
            logger.debug("cari pbt utk koddaerah :" + kodDaerah);
            senaraiKodPBT = listUtil.getSenaraiKodPBTByDaerah(kodDaerah);
            logger.debug("senaraiKodPBT :" + senaraiKodPBT.size());
            hakmilikPermohonanList = p.getSenaraiHakmilik();

            if (hakmilikPermohonanList.size() > 0) {
                tarikhDaftar = p.getSenaraiHakmilik().get(0).getHakmilik().getTarikhDaftar();
                /*if (p.getSenaraiHakmilik().get(0).getHakmilik().getTarikhDaftarAsal() != null) {
         tarikhDaftarAsal = sdf.format(p.getSenaraiHakmilik().get(0).getHakmilik().getTarikhDaftarAsal());
         }*/
                //yus modified 01022019
                if (p.getKodUrusan().getKod().equals("PSPL")) {
                    if (p.getSenaraiHakmilik().get(0).getHakmilik().getTarikhDaftarAsal() != null) {
                        tarikhDaftarAsal = sdf.format(p.getSenaraiHakmilik().get(0).getHakmilik().getTarikhDaftarAsal());
                    }
                    if (tarikhDaftarAsal == null) {
                        if (p.getSenaraiHakmilik().get(0).getHakmilik().getTarikhDaftar() != null) {
                            tarikhDaftarAsal = sdf.format(p.getSenaraiHakmilik().get(0).getHakmilik().getTarikhDaftar());
                        }
                    } 
                }else {                
                
                //Modified By Murali @PAT NS 18-12-2012
                
                if (p.getSenaraiHakmilik().get(0).getHakmilik().getTarikhDaftar() != null) {
                    tarikhDaftarAsal = sdf.format(p.getSenaraiHakmilik().get(0).getHakmilik().getTarikhDaftar());
                }
                if (tarikhDaftarAsal == null) {
                    if (p.getSenaraiHakmilik().get(0).getHakmilik().getTarikhDaftarAsal() != null) {
                        tarikhDaftarAsal = sdf.format(p.getSenaraiHakmilik().get(0).getHakmilik().getTarikhDaftarAsal());
                    }
                }
                    }
                if (p.getSenaraiHakmilik().get(0).getHakmilik().getTarikhLuput() != null) {
                    tarikhLuput = sdf.format(p.getSenaraiHakmilik().get(0).getHakmilik().getTarikhLuput());
                }
                if (p.getSenaraiHakmilik().get(0).getHakmilik().getSekatanKepentingan() != null) {
                    kodSekatan = p.getSenaraiHakmilik().get(0).getHakmilik().getSekatanKepentingan().getKodSekatan();
                }
                if (p.getSenaraiHakmilik().get(0).getHakmilik().getSyaratNyata() != null) {
                    kodSyaratNyata = p.getSenaraiHakmilik().get(0).getHakmilik().getSyaratNyata().getKodSyarat();
                }
                if (p.getSenaraiHakmilik().get(0).getHakmilik().getTempohPegangan() != null) {
                    tempohPegangan = p.getSenaraiHakmilik().get(0).getHakmilik().getTempohPegangan();
                }
            }
        }
    }

    public String getPegangan() {
        return pegangan;
    }

    public void setPegangan(String pegangan) {
        this.pegangan = pegangan;
    }

    public String getJenisHakmilik() {
        return jenisHakmilik;
    }

    public void setJenisHakmilik(String jenisHakmilik) {
        this.jenisHakmilik = jenisHakmilik;
    }

    public String getKodNegeri() {
        return kodNegeri;
    }

    public void setKodNegeri(String kodNegeri) {
        this.kodNegeri = kodNegeri;
    }

    public int getTempohPegangan() {
        return tempohPegangan;
    }

    public void setTempohPegangan(int tempohPegangan) {
        this.tempohPegangan = tempohPegangan;
    }

    public String getKodJenisHakmilik() {
        return kodJenisHakmilik;
    }

    public void setKodJenisHakmilik(String kodJenisHakmilik) {
        this.kodJenisHakmilik = kodJenisHakmilik;
    }

    public List<KodPBT> getSenaraiKodPBT() {
        return senaraiKodPBT;
    }

    public void setSenaraiKodPBT(List<KodPBT> senaraiKodPBT) {
        this.senaraiKodPBT = senaraiKodPBT;
    }

    public String getKodDaerah() {
        return kodDaerah;
    }

    public void setKodDaerah(String kodDaerah) {
        this.kodDaerah = kodDaerah;
    }

    public String getKodSyaratNyata() {
        return kodSyaratNyata;
    }

    public void setKodSyaratNyata(String kodSyaratNyata) {
        this.kodSyaratNyata = kodSyaratNyata;
    }

    public String getSyaratNyata() {
        return syaratNyata;
    }

    public void setSyaratNyata(String syaratNyata) {
        this.syaratNyata = syaratNyata;
    }

    public String getSekatan() {
        return sekatan;
    }

    public void setSekatan(String sekatan) {
        this.sekatan = sekatan;
    }

    public String getKodSekatan() {
        return kodSekatan;
    }

    public void setKodSekatan(String kodSekatan) {
        this.kodSekatan = kodSekatan;
    }

    public List<KodSyaratNyata> getListKodSyaratNyata() {
        return listKodSyaratNyata;
    }

    public void setListKodSyaratNyata(List<KodSyaratNyata> listKodSyaratNyata) {
        this.listKodSyaratNyata = listKodSyaratNyata;
    }

    public List<KodSekatanKepentingan> getListKodSekatan() {
        return listKodSekatan;
    }

    public void setListKodSekatan(List<KodSekatanKepentingan> listKodSekatan) {
        this.listKodSekatan = listKodSekatan;
    }

    public Permohonan getP() {
        return p;
    }

    public void setP(Permohonan p) {
        this.p = p;
    }

    public String getTarikhDaftarAsal() {
        return tarikhDaftarAsal;
    }

    public void setTarikhDaftarAsal(String tarikhDaftarAsal) {
        this.tarikhDaftarAsal = tarikhDaftarAsal;
    }

    public Date getTarikhDaftar() {
        return tarikhDaftar;
    }

    public void setTarikhDaftar(Date tarikhDaftar) {
        this.tarikhDaftar = tarikhDaftar;
    }

    public String getTarikhLuput() {
        return tarikhLuput;
    }

    public void setTarikhLuput(String tarikhLuput) {
        this.tarikhLuput = tarikhLuput;
    }

//    public String getTarikhDaftar() {
//        return tarikhDaftar;
//    }
//
//    public void setTarikhDaftar(String tarikhDaftar) {
//        this.tarikhDaftar = tarikhDaftar;
//    }
    public Hakmilik getHakmilik() {
        return hakmilik;
    }

    public void setHakmilik(Hakmilik hakmilik) {
        this.hakmilik = hakmilik;
    }

    public List<HakmilikPermohonan> getHakmilikPermohonanList() {
        return hakmilikPermohonanList;
    }

    public void setHakmilikPermohonanList(List<HakmilikPermohonan> hakmilikPermohonanList) {
        this.hakmilikPermohonanList = hakmilikPermohonanList;
    }

    public String getKodKatTanah() {
        return kodKatTanah;
    }

    public void setKodKatTanah(String kodKatTanah) {
        this.kodKatTanah = kodKatTanah;
    }

    public int gettPegangan() {
        return tPegangan;
    }

    public void settPegangan(int tPegangan) {
        this.tPegangan = tPegangan;
    }

}
