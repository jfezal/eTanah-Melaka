/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.stripes.pembangunan;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;
import etanah.service.PembangunanService;
import etanah.dao.PermohonanDAO;
import etanah.model.Permohonan;
import etanah.model.Notis;
import etanah.model.PermohonanAsal;
import etanah.view.etanahActionBeanContext;
import etanah.model.InfoAudit;
import etanah.model.Pengguna;
import etanah.dao.PermohonanAsalDAO;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;
import java.util.Date;
import java.util.Calendar;
import etanah.model.PermohonanTuntutanKos;
import etanah.model.PermohonanTuntutan;
import java.util.List;
import etanah.model.PermohonanTuntutanButiran;
import java.math.BigDecimal;

/**
 *
 * @author nursyazwani
 */
@UrlBinding("/pembangunan/permohonanSebelum")
public class PermohonanSebelumActionBean extends AbleActionBean {

    @Inject
    PembangunanService devServ;
    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    PermohonanAsalDAO permohonanAsalDAO;
    private Permohonan mohon;
    private String idMohonAsal;
    private PermohonanAsal mohonAsal;
    private String idPermohonan;
    Permohonan permohonanCurr;
    private Notis notis;
    private String tarikhNotis;
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

    @DefaultHandler
    public Resolution showForm() {
        return new JSP("pembangunan/common/permohonan_terdahulu.jsp").addParameter("tab", "true");
    }

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonanCurr = permohonanDAO.findById(idPermohonan);

        if (idPermohonan != null) {
            mohonAsal = new PermohonanAsal();
            mohonAsal = devServ.findPermohonanAsal(idPermohonan);
            if (mohonAsal != null) {
                idMohonAsal = mohonAsal.getIdPermohonanAsal().getIdPermohonan();
                notis = devServ.findNotisByIdPermohonan(mohonAsal.getIdPermohonanAsal().getIdPermohonan());
                if (notis != null && notis.getTarikhNotis() != null) {
                    tarikhNotis = sdf.format(notis.getTarikhNotis());
                }
            }
        }
    }

    public Resolution cari() {
        idMohonAsal = getContext().getRequest().getParameter("idMohonAsal");
        mohon = permohonanDAO.findById(idMohonAsal);

        Pengguna pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        mohonAsal = devServ.findPermohonanAsal(idPermohonan);
        InfoAudit ia = new InfoAudit();
        if (mohonAsal != null) {
            ia = mohonAsal.getInfoAudit();
            ia.setDiKemaskiniOleh(pengguna);
            ia.setTarikhKemaskini(new java.util.Date());
        } else {
            ia.setDimasukOleh(pengguna);
            ia.setTarikhMasuk(new java.util.Date());
            mohonAsal = new PermohonanAsal();
        }

        System.out.println("idMohonAsal: " + idMohonAsal);
        System.out.println("permohonanCurr: " + permohonanCurr.getIdPermohonan());

        notis = devServ.findNotisByIdPermohonan(idMohonAsal);
        Permohonan mohon1 = permohonanDAO.findById(permohonanCurr.getIdPermohonan());

        PermohonanAsal pa1;
        pa1 = devServ.findPermohonanAsalIdSblm(idMohonAsal);
        if (pa1 == null) {
            if (notis != null && notis.getTarikhNotis() != null) {
                System.out.println("Urusan : " + mohon1.getKodUrusan().getKod());
                if (mohon1.getKodUrusan().getKod().equals("RPP")) {
                    tarikhNotis = sdf.format(notis.getTarikhNotis());
                    Date date = notis.getTarikhNotis();
                    Calendar calNotis = new GregorianCalendar(1900 + date.getYear(), date.getMonth(), date.getDate());
                    Calendar now = Calendar.getInstance();
                    Calendar endNotis = new GregorianCalendar(1900 + date.getYear(), date.getMonth(), date.getDate());
                    calNotis.add(Calendar.DATE, 76);
                    endNotis.add(Calendar.DATE, 90);
                    System.out.println("calNotis: " + calNotis.getTime());
                    System.out.println("now: " + now.getTime());
                    System.out.println("endDate:" + endNotis.getTime());
                    if (now.after(calNotis) && now.before(endNotis)) {
                        addSimpleMessage("Masih di dalam tempoh 14 hari sebelum tamat tempoh (3 Bulan). Rayuan boleh diteruskan. Sila lengkapkan maklumat-maklumat untuk penyediaan Kertas Ringkas di Tab Deraf MMK.");
                    } else if (now.before(calNotis)) {
                        addSimpleMessage("Tidak di dalam tempoh 14 hari sebelum tamat tempoh (3 Bulan). Rayuan tidak boleh diteruskan. Sila klik butang 'Selesai' untuk menolak rayuan ini.");
                    } else if (now.after(endNotis)) {
                        addSimpleMessage("Telah tamat tempoh (3 Bulan). Rayuan tidak boleh diteruskan. Sila klik butang 'Selesai' untuk menolak rayuan ini.");
                    }
                } else {
                    addSimpleMessage("Id Permohonan Sebelum Berjaya Di simpan");
                }
                tarikhNotis = sdf.format(notis.getTarikhNotis());
                
                Permohonan pmh;
                pmh = permohonanDAO.findById(permohonanCurr.getIdPermohonan());
                pmh.setPermohonanSebelum(mohon);
                devServ.simpanPermohonan(pmh);

                mohonAsal.setIdPermohonan(permohonanCurr);
                mohonAsal.setCawangan(pengguna.getKodCawangan());
                mohonAsal.setInfoAudit(ia);
                mohonAsal.setIdPermohonanAsal(mohon);
                devServ.simpanMohonAsal(mohonAsal);

                permohonanCurr.setPermohonanSebelum(mohon);
                devServ.simpanPermohonan(mohon);
            } else {
                System.out.println("Maklumat tidak dijumpai");
                addSimpleError("Maklumat tidak dijumpai");
            }
        } else {
            System.out.println("Id Permohonan Sebelum Masih Dalam Proses");
            addSimpleError("Id Permohonan Sebelum Masih Dalam Proses");
        }
        return new JSP("pembangunan/common/permohonan_terdahulu.jsp").addParameter("tab", "true");
    }

    public Resolution cariBayar() {
        idMohonAsal = getContext().getRequest().getParameter("idMohonAsal");
        mohon = permohonanDAO.findById(idMohonAsal);

        Pengguna pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        mohonAsal = devServ.findPermohonanAsal(idPermohonan);
        InfoAudit ia = new InfoAudit();
        if (mohonAsal != null) {
            ia = mohonAsal.getInfoAudit();
            ia.setDiKemaskiniOleh(pengguna);
            ia.setTarikhKemaskini(new java.util.Date());
        } else {
            ia.setDimasukOleh(pengguna);
            ia.setTarikhMasuk(new java.util.Date());
            mohonAsal = new PermohonanAsal();
        }

//        System.out.println("idMohonAsal: " + idMohonAsal);
//        System.out.println("permohonanCurr: " + permohonanCurr.getIdPermohonan());
        mohonAsal.setIdPermohonan(permohonanCurr);
        mohonAsal.setCawangan(pengguna.getKodCawangan());
        mohonAsal.setInfoAudit(ia);
        mohonAsal.setIdPermohonanAsal(mohon);
        devServ.simpanMohonAsal(mohonAsal);

        if (mohonAsal != null) {
            PermohonanTuntutan pt = devServ.findMohonTuntutanByIdAsal(mohonAsal.getIdPermohonanAsal().getIdPermohonan());
            List<PermohonanTuntutanKos> listptk = devServ.findTuntutanKosByIdAsal(mohonAsal.getIdPermohonanAsal().getIdPermohonan());
            List<PermohonanTuntutanButiran> listptb = devServ.findTuntutanButiranByIdTuntutan(pt.getIdTuntut());

            PermohonanTuntutan tuntut = new PermohonanTuntutan();
            tuntut.setCawangan(pt.getCawangan());
            tuntut.setPermohonan(permohonanCurr);
            tuntut.setInfoAudit(ia);
            tuntut.setKodTransaksiTuntut(pt.getKodTransaksiTuntut());
            tuntut.setTarikhTuntutan(pt.getTarikhTuntutan());
            devServ.simpanPermohonanTuntutan(tuntut);


            for (PermohonanTuntutanKos ptk : listptk) {
                PermohonanTuntutanKos pK = new PermohonanTuntutanKos();
                pK.setInfoAudit(ia);
                pK.setKodTuntut(ptk.getKodTuntut());
                pK.setItem(ptk.getItem());
                pK.setAmaunTuntutan(ptk.getAmaunTuntutan());
                pK.setPermohonan(permohonanCurr);
                pK.setCawangan(ptk.getCawangan());
                pK.setKodTransaksi(ptk.getKodTransaksi());
                devServ.simpanPermohonanTuntutanKos(pK);

                PermohonanTuntutanButiran pb = new PermohonanTuntutanButiran();
                pb.setInfoAudit(ia);
                pb.setPermohonanTuntutan(tuntut);
                pb.setPermohonanTuntutanKos(pK);
                devServ.simpanPermohonanTuntutanButiran(pb);
            }
        }

        return new JSP("pembangunan/common/permohonan_terdahulu.jsp").addParameter("tab", "true");
    }

    public Permohonan getMohon() {
        return mohon;
    }

    public void setMohon(Permohonan mohon) {
        this.mohon = mohon;
    }

    public String getIdMohonAsal() {
        return idMohonAsal;
    }

    public void setIdMohonAsal(String idMohonAsal) {
        this.idMohonAsal = idMohonAsal;
    }

    public PermohonanAsal getMohonAsal() {
        return mohonAsal;
    }

    public void setMohonAsal(PermohonanAsal mohonAsal) {
        this.mohonAsal = mohonAsal;
    }

    public String getIdPermohonan() {
        return idPermohonan;
    }

    public void setIdPermohonan(String idPermohonan) {
        this.idPermohonan = idPermohonan;
    }

    public Notis getNotis() {
        return notis;
    }

    public void setNotis(Notis notis) {
        this.notis = notis;
    }

    public String getTarikhNotis() {
        return tarikhNotis;
    }

    public void setTarikhNotis(String tarikhNotis) {
        this.tarikhNotis = tarikhNotis;
    }

    public Permohonan getPermohonanCurr() {
        return permohonanCurr;
    }

    public void setPermohonanCurr(Permohonan permohonanCurr) {
        this.permohonanCurr = permohonanCurr;
    }
}
