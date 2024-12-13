/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.stripes.pelupusan;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.dao.KodJenisPihakBerkepentinganDAO;
import etanah.dao.PermohonanDAO;
import etanah.model.InfoAudit;
import etanah.model.KodJenisPihakBerkepentingan;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.model.PermohonanPihak;
import etanah.model.Pihak;
import etanah.service.PelupusanService;
import etanah.service.common.PermohonanService;
import etanah.view.etanahActionBeanContext;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import org.apache.log4j.Logger;
import org.hibernate.Session;

/**
 *
 * @author User
 */
@UrlBinding("/pelupusan/pihak_wakil")
public class PihakwakilActionBean extends AbleActionBean {

    @Inject
    PermohonanService permohonanService;
    @Inject
    private Pihak pihak;
    private Pengguna peng;
    @Inject
    private PermohonanPihak permohonanpihak;
    private List<PermohonanPihak> mohanPihakList;
    private InfoAudit ia;
    @Inject
    private PermohonanDAO permohonanDAO;
    @Inject
    private Permohonan permohonan;
    @Inject
    private PelupusanService pelupusanService;
    private String idPermohonan;
    private String sebab;
    private String nama;
    private String noPengenalan;
    private BigDecimal nilai;
    private String dalamanNilai1;
    private String catatan;
    private String nilaiKeputusan;
    private String ulasan;
    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;
    private static final Logger LOG = Logger.getLogger(LesenTerdahuluActionBean.class);
    private boolean display;
    @Inject
    KodJenisPihakBerkepentinganDAO kodJenisPihakBerkepentinganDAO;

    @DefaultHandler
    public Resolution showForm() {
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = permohonanDAO.findById(idPermohonan);
        if (permohonan != null) {
            if (permohonan.getIdPermohonan() != null) {
//                mohanPihakList = new ArrayList<PermohonanPihak>();
                mohanPihakList = pelupusanService.findPermohonanPihak(idPermohonan);
                if (!mohanPihakList.isEmpty()) {
                    for (int i = 0; i < mohanPihakList.size(); i++) {
                        PermohonanPihak permohonanpihaktemp = mohanPihakList.get(i);
                        if (permohonanpihaktemp.getJenis().getKod().equals("WKL")) {
                            permohonanpihak = mohanPihakList.get(i);
                        }
                    }
                }
                    if (permohonanpihak != null) {
                        pihak = permohonanpihak.getPihak();
                        if (pihak != null) {
                            nama = pihak.getNama();
                            noPengenalan = pihak.getNoPengenalan();
                        }
                        nilai = permohonanpihak.getNilai();
                        dalamanNilai1 = permohonanpihak.getDalamanNilai1();
                    }
                

            }
        }
        return new JSP("pelupusan/pihak_wakil.jsp").addParameter("tab", "true");
    }

    public Resolution viewForm() {
        display = Boolean.TRUE;
        return new JSP("pelupusan/pihak_wakil.jsp").addParameter("tab", "true");
    }

    public Resolution simpanPihak() {

        ia = new InfoAudit();
        peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        ia.setDimasukOleh(peng);
        ia.setTarikhMasuk(new java.util.Date());

        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = permohonanService.findById(idPermohonan);
        if (permohonanpihak == null) {
            pihak = new Pihak();
            pihak.setNama(nama);
            pihak.setNoPengenalan(noPengenalan);
            pihak.setInfoAudit(ia);
            pelupusanService.saveOnly(pihak);

            permohonanpihak = new PermohonanPihak();
            permohonanpihak.setInfoAudit(ia);
            permohonanpihak.setPermohonan(permohonan);
            KodJenisPihakBerkepentingan jenis = kodJenisPihakBerkepentinganDAO.findById("WKL");
            permohonanpihak.setJenis(jenis);
            permohonanpihak.setNilai(nilai);
            permohonanpihak.setDalamanNilai1(dalamanNilai1);
            permohonanpihak.setCawangan(permohonan.getCawangan());
            permohonanpihak.setPihak(pihak);
        } else if (permohonanpihak != null) {
            pihak.setNama(nama);
            pihak.setNoPengenalan(noPengenalan);
            pihak.setInfoAudit(ia);

//            pelupusanService.saveOnly(pihak);
            permohonanpihak.setInfoAudit(ia);
            permohonanpihak.setPermohonan(permohonan);
            KodJenisPihakBerkepentingan jenis = kodJenisPihakBerkepentinganDAO.findById("WKL");
            permohonanpihak.setJenis(jenis);
            permohonanpihak.setNilai(nilai);
            permohonanpihak.setDalamanNilai1(dalamanNilai1);
            permohonanpihak.setCawangan(permohonan.getCawangan());
            permohonanpihak.setPihak(pihak);
        }
        pelupusanService.simpanPihak(pihak, permohonanpihak);
        addSimpleMessage("Maklumat telah berjaya disimpan");
        return new JSP("pelupusan/pihak_wakil.jsp").addParameter("tab", "true");
    }

    public List<PermohonanPihak> getMohanPihakList() {
        return mohanPihakList;
    }

    public void setMohanPihakList(List<PermohonanPihak> mohanPihakList) {
        this.mohanPihakList = mohanPihakList;
    }

    public Permohonan getPermohonan() {
        return permohonan;
    }

    public void setPermohonan(Permohonan permohonan) {
        this.permohonan = permohonan;
    }

    public PermohonanPihak getPermohonanpihak() {
        return permohonanpihak;
    }

    public void setPermohonanpihak(PermohonanPihak permohonanpihak) {
        this.permohonanpihak = permohonanpihak;
    }

    public Pihak getPihak() {
        return pihak;
    }

    public void setPihak(Pihak pihak) {
        this.pihak = pihak;
    }

    public PermohonanService getPermohonanService() {
        return permohonanService;
    }

    public void setPermohonanService(PermohonanService permohonanService) {
        this.permohonanService = permohonanService;
    }

    public PermohonanDAO getPermohonanDAO() {
        return permohonanDAO;
    }

    public void setPermohonanDAO(PermohonanDAO permohonanDAO) {
        this.permohonanDAO = permohonanDAO;
    }

    public String getDalamanNilai1() {
        return dalamanNilai1;
    }

    public void setDalamanNilai1(String dalamanNilai1) {
        this.dalamanNilai1 = dalamanNilai1;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public BigDecimal getNilai() {
        return nilai;
    }

    public void setNilai(BigDecimal nilai) {
        this.nilai = nilai;
    }

    public String getNoPengenalan() {
        return noPengenalan;
    }

    public void setNoPengenalan(String noPengenalan) {
        this.noPengenalan = noPengenalan;
    }
}
