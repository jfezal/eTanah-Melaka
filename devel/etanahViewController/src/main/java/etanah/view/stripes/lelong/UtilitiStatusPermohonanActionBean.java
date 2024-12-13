/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.stripes.lelong;

import able.stripes.JSP;
import etanah.view.kaunter.*;
import java.util.List;
import org.apache.log4j.Logger;

import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;

import com.google.inject.Inject;
import etanah.dao.EnkuiriDAO;
import etanah.dao.LelonganDAO;
import etanah.dao.FasaPermohonanDAO;
import etanah.dao.PembidaDAO;


import etanah.dao.PermohonanDAO;
import etanah.model.AkuanTerimaDeposit;
import etanah.model.Enkuiri;
import etanah.model.FasaPermohonan;
import etanah.model.Hakmilik;
import etanah.model.HakmilikUrusan;
import etanah.model.Permohonan;

import etanah.model.Lelongan;
import etanah.model.Pembida;
import etanah.model.PermohonanAsal;
import etanah.model.Pihak;
import etanah.service.common.EnkuiriService;
import etanah.service.common.LelongService;
import etanah.service.daftar.PembetulanService;
import oracle.bpel.services.workflow.task.model.Task;
import etanah.workflow.WorkFlowService;
import java.math.BigDecimal;
import java.util.ArrayList;
import net.sourceforge.stripes.util.Log;
import oracle.bpel.services.workflow.WorkflowException;

/**
 *
 * @author mazurahayati.yusop
 */
@UrlBinding("/lelong/status")
public class UtilitiStatusPermohonanActionBean extends PermohonanKaunter {

    private Permohonan permohonan;
    private AkuanTerimaDeposit atd;
    private Lelongan lelong;
    private Pembida pembida;
    private Pembida pembida2;
    private FasaPermohonan fasaMohon;
    private Permohonan permohonan1;
    private Enkuiri enkuiri;
    private Enkuiri enkuiri1;
    private Pihak pihak;
    private BigDecimal deposit;
    private String kodBank2;
    private AkuanTerimaDeposit atd2;
    private BigDecimal hargaBidaan2;
    private BigDecimal deposit2;
    private BigDecimal baki;
    private String kodCareBayar;
    private String kodCareBayar2;
    private String kodBank;
    private String stage;
    private String status;
    private String participant;
    private String idHakmilik;
    private String idPermohonan;
    private String idSebelum;
    private Hakmilik hakmilik;
    private boolean showXN;
    private List<HakmilikUrusan> hakmilikUrusanList;
    private List<Lelongan> listLel1;
    private List<Lelongan> senaraiLelongan;
    private List<Enkuiri> senaraiEnkuiri2;
    private List<Enkuiri> senaraiEnkuiri;
    private List<FasaPermohonan> checkXN;
    private List<Pembida> senaraiPembida;
    private ArrayList<Pembida> pembidaList = new ArrayList<Pembida>();
    private static Logger LOG = Logger.getLogger(UtilitiStatusPermohonanActionBean.class);
    private List<AkuanTerimaDeposit> listATD;
    private List<AkuanTerimaDeposit> listATD2;
    private boolean showPopupPembidaBJ = false;
    private List<Pembida> checkListStatusPembida2 = new ArrayList<Pembida>();
    private List<Pembida> checkStatusPembida2;
    @Inject
    private PermohonanDAO permohonanDAO;
    @Inject
    private FasaPermohonanDAO fasaMohonnDAO;
    @Inject
    PembidaDAO pembidaDAO;
    @Inject
    LelonganDAO lelongDAO;
    @Inject
    EnkuiriDAO enkuiriDAO;
    @Inject
    LelongService lelongService;
    @Inject
    EnkuiriService enService;
    @Inject
    private PembetulanService pService;

    @DefaultHandler
    public Resolution insertIdPermohonan() {
        return new ForwardResolution("/WEB-INF/jsp/lelong/semakanPermohonan.jsp");
    }

    public Resolution checkPermohonan() throws WorkflowException {

        System.out.print("checkPermohonan");
        if (idHakmilik != null) {
            hakmilikUrusanList = new ArrayList<HakmilikUrusan>();
            hakmilikUrusanList.addAll(pService.findUrusanByidHY(idHakmilik));
            return new ForwardResolution("/WEB-INF/jsp/common/maklumat_urusan.jsp");
        }
        if ((permohonan == null) && (idHakmilik == null)) {
            addSimpleError("Sila masukkan ID Permohonan/Perserahan atau ID Hakmilik");
            return new ForwardResolution("/WEB-INF/jsp/lelong/semakanPermohonan.jsp");
        }
        String idPermohonan2 = permohonan.getIdPermohonan();
        if (idPermohonan2 == null) {
            addSimpleError("Sila masukkan ID Permohonan/Perserahan");
            return new ForwardResolution("/WEB-INF/lelong/semakanPermohonan.jsp");
        }
        permohonan = getPermohonanDAO().findById(permohonan.getIdPermohonan());

        if (permohonan != null) {
            enkuiri = lelongService.findEnkuiri(permohonan.getIdPermohonan());
            senaraiEnkuiri2 = lelongService.findEnkuiriAll2(permohonan.getIdPermohonan());
            System.out.print("Senarai Enkuiri: " + senaraiEnkuiri2);

            senaraiLelongan = lelongService.getAllLelongan2(permohonan.getIdPermohonan());

//            for (Lelongan ll : getSenaraiLelongan()) {
//                setLelong(ll);
//                setSenaraiPembida(lelongService.getListPembidaByIdLelong(getLelong().getIdLelong()));
//
//                for (Pembida p : getSenaraiPembida()) {
//                    setPembida(pembidaDAO.findById(p.getIdPembida()));
//                    pembidaList.add(getPembida());
//                }
//
//            }
            for (Lelongan ll : getSenaraiLelongan()) {
                lelong = ll;
                LOG.info("id lelong: " + ll.getIdLelong());
                //list semua pembida
                senaraiPembida = lelongService.getListPembidaByIdLelong(lelong.getIdLelong());

                // list pembida di mana status = BJ, AT
                setCheckStatusPembida2(lelongService.checkPembidaStatus(lelong.getIdLelong()));

                for (Pembida p : senaraiPembida) {
                    LOG.info("Pembida " + p.getPihak().getNama());
                    pembida = pembidaDAO.findById(p.getIdPembida());
                    pembidaList.add(pembida);
                }

                for (Pembida p : getCheckStatusPembida2()) {
                    setPembida2(p);
                    LOG.info("Pembida " + p.getPihak().getNama());
                    setPembida2(pembidaDAO.findById(p.getIdPembida()));
                    LOG.debug("pembida2" + getPembida2());


                    if (getPembida2() != null) {
                        LOG.info("pembida ada laaa---");
                        checkListStatusPembida2.add(getPembida2());
                    }

                }
            }
            //(boolean utk keluarkan popup maklumat pembida yg berjaya dan tidak bayar baki sahaja)
            if (!checkListStatusPembida2.isEmpty()) {
                showPopupPembidaBJ = true;
                LOG.info("masuk popup true");
            } else {
                showPopupPembidaBJ = false;
                LOG.info("masuk popup false");
            }

            LOG.info("checkListStatusPembida2.size()" + checkListStatusPembida2.size());

            senaraiEnkuiri = lelongService.getAllDesc(permohonan.getIdPermohonan());

            if (permohonan.getPermohonanSebelum() == null) {
                enkuiri = lelongService.findEnkuiri(idPermohonan);
            } else {
                List<PermohonanAsal> list2 = lelongService.listMohonAsal2(permohonan.getPermohonanSebelum().getIdPermohonan());
                if (list2.isEmpty()) {
                    enkuiri = lelongService.findEnkuiri1(permohonan.getPermohonanSebelum().getIdPermohonan());
                    if (enkuiri == null) {
                        List<Enkuiri> list3 = lelongService.getAllDesc(permohonan.getPermohonanSebelum().getIdPermohonan());
                        if (!list3.isEmpty()) {
                            enkuiri = list3.get(0);
                        }
                    }
                    senaraiEnkuiri = lelongService.getAllDesc(permohonan.getPermohonanSebelum().getIdPermohonan());
                } else {
                    enkuiri = lelongService.findEnkuiri(list2.get(0).getIdPermohonanAsal().getIdPermohonan());
                    if (enkuiri == null) {
                        List<Enkuiri> list3 = lelongService.getAllDesc(list2.get(0).getIdPermohonanAsal().getIdPermohonan());
                        if (!list3.isEmpty()) {
                            enkuiri = list3.get(0);
                        }
                    }
                    senaraiEnkuiri = lelongService.getAllDesc(list2.get(0).getIdPermohonanAsal().getIdPermohonan());
                }
            }

            if (permohonan.getPermohonanSebelum() != null) {
                idSebelum = permohonan.getPermohonanSebelum().getIdPermohonan();
                return new ForwardResolution("/WEB-INF/jsp/lelong/permohonan_status_lelong.jsp");
            }

            List<Task> l = WorkFlowService.queryTasksByAdmin(permohonan.getIdPermohonan());
            for (Task t : l) {
                stage = t.getSystemAttributes().getStage();
                participant = t.getSystemAttributes().getAcquiredBy();
            }

            status = permohonan.getStatus();
            if (status == null) {
                //addSimpleMessage("Urusan ini sedang diproses");
                addSimpleMessage("Permohonan Ini Diperingkat: " + stage); //added by murali 05042013
                return new ForwardResolution("/WEB-INF/jsp/lelong/permohonan_status_lelong.jsp");
            } else if ("TL".equals(status)) { //added by murali 05042013
                addSimpleMessage("Permohonan Ini Ditolak ");
                return new ForwardResolution("/WEB-INF/jsp/lelong/permohonan_status_lelong.jsp");
            } else if ("TT".equals(status)) { //added by murali 05042013
                addSimpleMessage("Permohonan Ini Ditutup ");
                return new ForwardResolution("/WEB-INF/jsp/lelong/permohonan_status_lelong.jsp");
            } else if ("RM".equals(status)) { //added by murali 05042013
                addSimpleMessage("Permohonan Ini Dirujuk Ke Mahkamah");
                return new ForwardResolution("/WEB-INF/jsp/lelong/permohonan_status_lelong.jsp");
            } else if ("SD".equals(status)) { //added by murali 05042013
                addSimpleMessage("Permohonan Ini Tidak Aktif. Menunggu Keputusan Dari Mahkamah");
                return new ForwardResolution("/WEB-INF/jsp/lelong/permohonan_status_lelong.jsp");
            } else if ("TS".equals(status)) {
                addSimpleMessage("Menunggu Permohonan/Perserahan sebelum untuk bermula");
                return new ForwardResolution("/WEB-INF/jsp/lelong/permohonan_status_lelong.jsp");
            } else if ("TA".equals(status)) {
                addSimpleMessage("Tugasan ini belum diambil oleh sesiapa");
                return new ForwardResolution("/WEB-INF/jsp/lelong/permohonan_status_lelong.jsp");
            } else if ("AK".equals(status)) {
                addSimpleMessage("Urusan ini sedang diproses.");
                return new ForwardResolution("/WEB-INF/jsp/lelong/permohonan_status_lelong.jsp");
            } else if ("GB".equals(status)) { // Gantung
                // TODO: check if task own by SPOC
                addSimpleMessage("Urusan ini telah digantung. Sila hubungi Unit "
                        + permohonan.getKodUrusan().getJabatanNama());
                return new ForwardResolution("/WEB-INF/jsp/lelong/permohonan_status_lelong.jsp");
            } else if ("TK".equals(status)) {//TK - Tidak Aktif - Urusan telah dibatalkan
                addSimpleMessage("Urusan telah dibatalkan");
                return new ForwardResolution("/WEB-INF/jsp/lelong/permohonan_status_lelong.jsp");
            } else if ("TP".equals(status)) {
                addSimpleMessage("Menunggu tindakan selanjut daripada pemohon untuk meneruskan aliran kerja. Tugasan Berada Di kaunter");
                return new ForwardResolution("/WEB-INF/jsp/lelong/permohonan_status_lelong.jsp");
            } else if ("SL".equals(status)) {
                //addSimpleMessage("Urusan ini telah selesai diproses. Keputusan telah dikeluarkan");
                addSimpleMessage("Permohonan Ini Telah Selesai"); //added By murali 05042013
                return new ForwardResolution("/WEB-INF/jsp/lelong/permohonan_status_lelong.jsp");
            } else if ("SS".equals(status)) {
                addSimpleMessage("Urusan ini telah disemak semula");
                return new ForwardResolution("/WEB-INF/jsp/lelong/permohonan_status_lelong.jsp");
            } else {

                return null;

            }

        } else {

            addSimpleError("Id Permohonan tidak wujud.");
            return new ForwardResolution("/WEB-INF/jsp/lelong/semakanPermohonan.jsp");
        }

    }

    public Resolution viewhakmilikDetail() {
        idHakmilik = (String) getContext().getRequest().getParameter("idHakmilik");
        hakmilik = pService.findHakmilik(idHakmilik);
        return new JSP("daftar/utiliti/paparan_hakmilik_single.jsp").addParameter("popup", "true");
    }

    public Resolution viewDetail() {
        System.out.println("---masuk view detail---");
        String idLelong = getContext().getRequest().getParameter("idLelong");
        System.out.println("id lelong : " + idLelong);

        senaraiPembida = lelongService.getPembidaListBerjayaTakByrBaki(Long.parseLong(idLelong));
        lelong = lelongService.findLelong3(idLelong);
        idPermohonan = lelong.getPermohonan().getIdPermohonan();
        System.out.println("id permohonan : " + idPermohonan);

        setListLel1(lelongService.getLelonganForFindAllPembida(idPermohonan));
        LOG.info("size listLel1: " + getListLel1().size());

        for (Lelongan lelo : getListLel1()) {
            LOG.info("MASUK LIST 1");
            enkuiri = lelo.getEnkuiri();
            LOG.info("id enkuiri: " + lelo.getEnkuiri().getIdEnkuiri());

            if (enkuiri.getCaraLelong().equals("A")) {
                LOG.info("cara lelong " + enkuiri.getCaraLelong());

                setListATD(lelongService.findATDS(idPermohonan, senaraiPembida.get(0).getLelong().getIdLelong()));

//                if (!listATD.isEmpty()) {
//                    setAtd(listATD.get(0));
//                    System.out.println("Deposit : " + getAtd().getAmaun());
//                    System.out.println("No Ruj : " + getAtd().getIdRuj());
//                    if (getAtd().getBank() != null) {
//                        setKodBank(getAtd().getBank().getKod());
//                    }
//                    if (getAtd().getCaraBayaran() != null) {
//                        setKodCareBayar(getAtd().getCaraBayaran().getKod());
//                    }
//                    setDeposit(getAtd().getAmaun());
//                }

                setListATD2(lelongService.findATD2S(idPermohonan, senaraiPembida.get(0).getLelong().getIdLelong()));
                LOG.info("senaraiPembida.get(0).getLelong().getIdLelong()" + senaraiPembida.get(0).getLelong().getIdLelong());

//                if (!listATD2.isEmpty()) {
//                    setAtd2(listATD2.get(0));
//                    System.out.println("atd : " + getAtd2().getIdAkuan());
//                    if (getAtd2().getBank() != null) {
//                        setKodBank2(getAtd2().getBank().getKod());
//                    }
//                    if (getAtd2().getCaraBayaran() != null) {
//                        setKodCareBayar2(getAtd2().getCaraBayaran().getKod());
//                    }
//                    setDeposit2(getAtd2().getAmaun());
//                }
            }

            if (enkuiri.getCaraLelong().equals("B")) {
                LOG.info("cara lelong " + enkuiri.getCaraLelong());
                setListATD(lelongService.findATDS(idPermohonan, lelo.getEnkuiri().getIdEnkuiri()));

//                if (!listATD.isEmpty()) {
//                    setAtd(listATD.get(0));
//                    System.out.println("Deposit : " + getAtd().getAmaun());
//                    System.out.println("No Ruj : " + getAtd().getIdRuj());
//                    if (getAtd().getBank() != null) {
//                        setKodBank(getAtd().getBank().getKod());
//                    }
//                    if (getAtd().getCaraBayaran() != null) {
//                        setKodCareBayar(getAtd().getCaraBayaran().getKod());
//                    }
//                    setDeposit(getAtd().getAmaun());
//                }

                setListATD2(lelongService.findATD2S(idPermohonan, lelo.getEnkuiri().getIdEnkuiri()));
                LOG.info("lelo.getEnkuiri().getIdEnkuiri()" + lelo.getEnkuiri().getIdEnkuiri());
//                if (!listATD2.isEmpty()) {
//                    setAtd2(listATD2.get(0));
//                    System.out.println("atd : " + getAtd2().getIdAkuan());
//                    if (getAtd2().getBank() != null) {
//                        setKodBank2(getAtd2().getBank().getKod());
//                    }
//                    if (getAtd2().getCaraBayaran() != null) {
//                        setKodCareBayar2(getAtd2().getCaraBayaran().getKod());
//                    }
//                    setDeposit2(getAtd2().getAmaun());
//                }
            }

        }

        return new ForwardResolution("/WEB-INF/jsp/lelong/dokumen/semak_pembida.jsp").addParameter("popup", "true");

    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<HakmilikUrusan> getHakmilikUrusanList() {
        return hakmilikUrusanList;
    }

    public void setHakmilikUrusanList(List<HakmilikUrusan> hakmilikUrusanList) {
        this.hakmilikUrusanList = hakmilikUrusanList;
    }

    public Hakmilik getHakmilik() {
        return hakmilik;
    }

    public void setHakmilik(Hakmilik hakmilik) {
        this.hakmilik = hakmilik;
    }

    public void setPermohonan(Permohonan p) {
        this.permohonan = p;
    }

    public Permohonan getPermohonan() {
        return permohonan;
    }

    public String getStage() {
        return stage;
    }

    public void setStage(String stage) {
        this.stage = stage;
    }

    public String getParticipant() {
        return participant;
    }

    public void setParticipant(String participant) {
        this.participant = participant;
    }

    public String getIdHakmilik() {
        return idHakmilik;
    }

    public void setIdHakmilik(String idHakmilik) {
        this.idHakmilik = idHakmilik;
    }

    public List<Enkuiri> getSenaraiEnkuiri() {
        return senaraiEnkuiri;
    }

    public void setSenaraiEnkuiri(List<Enkuiri> senaraiEnkuiri) {
        this.senaraiEnkuiri = senaraiEnkuiri;
    }

    public List<Lelongan> getSenaraiLelongan() {
        return senaraiLelongan;
    }

    public void setSenaraiLelongan(List<Lelongan> senaraiLelongan) {
        this.senaraiLelongan = senaraiLelongan;
    }

    public String getIdPermohonan() {
        return idPermohonan;
    }

    public void setIdPermohonan(String idPermohonan) {
        this.idPermohonan = idPermohonan;
    }

    public Enkuiri getEnkuiri() {
        return enkuiri;
    }

    public void setEnkuiri(Enkuiri enkuiri) {
        this.enkuiri = enkuiri;
    }

    public String getIdSebelum() {
        return idSebelum;
    }

    public void setIdSebelum(String idSebelum) {
        this.idSebelum = idSebelum;
    }

    public Permohonan getPermohonan1() {
        return permohonan1;
    }

    public void setPermohonan1(Permohonan permohonan1) {
        this.permohonan1 = permohonan1;
    }

    public Enkuiri getEnkuiri1() {
        return enkuiri1;
    }

    public void setEnkuiri1(Enkuiri enkuiri1) {
        this.enkuiri1 = enkuiri1;
    }

    /**
     * @return the fasaMohon
     */
    public FasaPermohonan getFasaMohon() {
        return fasaMohon;
    }

    /**
     * @param fasaMohon the fasaMohon to set
     */
    public void setFasaMohon(FasaPermohonan fasaMohon) {
        this.fasaMohon = fasaMohon;
    }

    /**
     * @return the checkXN
     */
    public List<FasaPermohonan> getCheckXN() {
        return checkXN;
    }

    /**
     * @param checkXN the checkXN to set
     */
    public void setCheckXN(List<FasaPermohonan> checkXN) {
        this.checkXN = checkXN;
    }

    /**
     * @return the permohonanDAO
     */
    public PermohonanDAO getPermohonanDAO() {
        return permohonanDAO;
    }

    /**
     * @param permohonanDAO the permohonanDAO to set
     */
    public void setPermohonanDAO(PermohonanDAO permohonanDAO) {
        this.permohonanDAO = permohonanDAO;
    }

    /**
     * @return the showXN
     */
    public boolean isShowXN() {
        return showXN;
    }

    /**
     * @param showXN the showXN to set
     */
    public void setShowXN(boolean showXN) {
        this.showXN = showXN;
    }

    public List<Enkuiri> getSenaraiEnkuiri2() {
        return senaraiEnkuiri2;
    }

    public void setSenaraiEnkuiri2(List<Enkuiri> senaraiEnkuiri2) {
        this.senaraiEnkuiri2 = senaraiEnkuiri2;
    }

    public ArrayList<Pembida> getPembidaList() {
        return pembidaList;
    }

    public void setPembidaList(ArrayList<Pembida> pembidaList) {
        this.pembidaList = pembidaList;
    }

    public Lelongan getLelong() {
        return lelong;
    }

    public void setLelong(Lelongan lelong) {
        this.lelong = lelong;
    }

    public List<Pembida> getSenaraiPembida() {
        return senaraiPembida;
    }

    public void setSenaraiPembida(List<Pembida> senaraiPembida) {
        this.senaraiPembida = senaraiPembida;
    }

    public Pembida getPembida() {
        return pembida;
    }

    public void setPembida(Pembida pembida) {
        this.pembida = pembida;
    }

    public AkuanTerimaDeposit getAtd() {
        return atd;
    }

    public void setAtd(AkuanTerimaDeposit atd) {
        this.atd = atd;
    }

    public String getKodBank() {
        return kodBank;
    }

    public void setKodBank(String kodBank) {
        this.kodBank = kodBank;
    }

    public String getKodCareBayar() {
        return kodCareBayar;
    }

    public void setKodCareBayar(String kodCareBayar) {
        this.kodCareBayar = kodCareBayar;
    }

    public String getKodCareBayar2() {
        return kodCareBayar2;
    }

    public void setKodCareBayar2(String kodCareBayar2) {
        this.kodCareBayar2 = kodCareBayar2;
    }

    public BigDecimal getDeposit() {
        return deposit;
    }

    public void setDeposit(BigDecimal deposit) {
        this.deposit = deposit;
    }

    public String getKodBank2() {
        return kodBank2;
    }

    public void setKodBank2(String kodBank2) {
        this.kodBank2 = kodBank2;
    }

    public AkuanTerimaDeposit getAtd2() {
        return atd2;
    }

    public void setAtd2(AkuanTerimaDeposit atd2) {
        this.atd2 = atd2;
    }

    public BigDecimal getHargaBidaan2() {
        return hargaBidaan2;
    }

    public void setHargaBidaan2(BigDecimal hargaBidaan2) {
        this.hargaBidaan2 = hargaBidaan2;
    }

    public BigDecimal getDeposit2() {
        return deposit2;
    }

    public void setDeposit2(BigDecimal deposit2) {
        this.deposit2 = deposit2;
    }

    public BigDecimal getBaki() {
        return baki;
    }

    public void setBaki(BigDecimal baki) {
        this.baki = baki;
    }

    public Pihak getPihak() {
        return pihak;
    }

    public void setPihak(Pihak pihak) {
        this.pihak = pihak;
    }

    public List<Lelongan> getListLel1() {
        return listLel1;
    }

    public void setListLel1(List<Lelongan> listLel1) {
        this.listLel1 = listLel1;
    }

    public List<AkuanTerimaDeposit> getListATD() {
        return listATD;
    }

    public void setListATD(List<AkuanTerimaDeposit> listATD) {
        this.listATD = listATD;
    }

    public List<AkuanTerimaDeposit> getListATD2() {
        return listATD2;
    }

    public void setListATD2(List<AkuanTerimaDeposit> listATD2) {
        this.listATD2 = listATD2;
    }

    public boolean isShowPopupPembidaBJ() {
        return showPopupPembidaBJ;
    }

    public void setShowPopupPembidaBJ(boolean showPopupPembidaBJ) {
        this.showPopupPembidaBJ = showPopupPembidaBJ;
    }

    public List<Pembida> getCheckListStatusPembida2() {
        return checkListStatusPembida2;
    }

    public void setCheckListStatusPembida2(List<Pembida> checkListStatusPembida2) {
        this.checkListStatusPembida2 = checkListStatusPembida2;
    }

    public List<Pembida> getCheckStatusPembida2() {
        return checkStatusPembida2;
    }

    public void setCheckStatusPembida2(List<Pembida> checkStatusPembida2) {
        this.checkStatusPembida2 = checkStatusPembida2;
    }

    public Pembida getPembida2() {
        return pembida2;
    }

    public void setPembida2(Pembida pembida2) {
        this.pembida2 = pembida2;
    }
}
