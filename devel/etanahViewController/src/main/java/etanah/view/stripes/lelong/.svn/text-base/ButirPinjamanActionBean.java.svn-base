/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.stripes.lelong;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.dao.EnkuiriPeminjamDAO;
import etanah.dao.PermohonanDAO;
import etanah.model.Enkuiri;
import etanah.model.EnkuiriPeminjam;
import etanah.model.InfoAudit;
import etanah.model.KodCawangan;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.service.common.LelongService;
import etanah.view.etanahActionBeanContext;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
 * @author mazurahayati.yusop
 */
@UrlBinding("/lelong/butirPinjaman")
public class ButirPinjamanActionBean extends AbleActionBean {

    private static final Logger LOG = Logger.getLogger(ButirPinjamanActionBean.class);
    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    EnkuiriPeminjamDAO enkuiriPeminjamDAO;
    @Inject
    etanah.Configuration conf;
    @Inject
    LelongService lelongService;
    private Permohonan permohonan;
    private String idPermohonan;
    private Enkuiri enkuiri;
    private List<Enkuiri> senaraiEnkuiri;
    private String tarikhGadaian;
    private String tarikhMulaTunggakan;
    private String tarikhMulaBayarPinjaman;
    private String tarikhWarta;
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    private boolean flag = false;
    private String negeri;
    private List<EnkuiriPeminjam> listPeminjam;
    private int rowCount3;
    private int rowCount4;

    @DefaultHandler
    public Resolution showForm() {
        if ("05".equals(conf.getProperty("kodNegeri"))) {
            negeri = "NS";
        }
        return new JSP("lelong/Butir-butirPinjaman_overdraft.jsp").addParameter("tab", "true");
    }

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        if (idPermohonan != null) {
            permohonan = permohonanDAO.findById(idPermohonan);
            try {
                enkuiri = lelongService.findEnkuiri(idPermohonan);
                if (enkuiri == null) {
                    List<Enkuiri> list = lelongService.getAllDesc(idPermohonan);
                    if (!list.isEmpty()) {
                        enkuiri = list.get(0);
                    }
                }
                if (enkuiri.getTarikhGadaian() != null) {
                    tarikhGadaian = sdf.format(enkuiri.getTarikhGadaian());
                }
                if (enkuiri.getTarikhMulaTunggakan() != null) {
                    tarikhMulaTunggakan = sdf.format(enkuiri.getTarikhMulaTunggakan());
                }
                if (enkuiri.getTarikhMulaBayarPinjaman() != null) {
                    tarikhMulaBayarPinjaman = sdf.format(enkuiri.getTarikhMulaBayarPinjaman());
                }
                if (enkuiri.getTarikhWarta() != null) {
                    tarikhWarta = sdf.format(enkuiri.getTarikhWarta());
                }
                listPeminjam = lelongService.getPeminjam(enkuiri.getIdEnkuiri());
                rowCount4 = listPeminjam.size();
            } catch (Exception ex) {
                LOG.info(ex);
            }
        }
    }

    public Resolution simpanPinjaman() throws ParseException {
        Pengguna peng = (Pengguna) context.getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        KodCawangan kc = peng.getKodCawangan();
        InfoAudit ia = new InfoAudit();
        EnkuiriPeminjam ep = null;

        LOG.info("permohonan :" + permohonan.getIdPermohonan());
        if (StringUtils.isNotBlank(tarikhGadaian)) {
            enkuiri.setTarikhGadaian(sdf.parse(tarikhGadaian));
        }
        if (StringUtils.isNotBlank(tarikhMulaTunggakan)) {
            enkuiri.setTarikhMulaTunggakan(sdf.parse(tarikhMulaTunggakan));
        }
        if (StringUtils.isNotBlank(tarikhMulaBayarPinjaman)) {
            enkuiri.setTarikhMulaBayarPinjaman(sdf.parse(tarikhMulaBayarPinjaman));
        }
        if (StringUtils.isNotBlank(tarikhWarta)) {
            enkuiri.setTarikhWarta(sdf.parse(tarikhWarta));
        }
        enkuiri.setCawangan(kc);
        enkuiri.setPermohonan(permohonan);
        lelongService.saveOrUpdate(enkuiri);
        ia.setDimasukOleh(peng);
        ia.setTarikhMasuk(new java.util.Date());
        LOG.info("rowCount3 : " + rowCount3);
        String nama = "";
        if (listPeminjam.isEmpty()) {
            for (int i = 0; i <= rowCount3; i++) {
                nama = getContext().getRequest().getParameter("namaPeminjam" + i);
                LOG.info("nama : " + nama.toUpperCase());
                ep = new EnkuiriPeminjam();
                ep.setNama_peminjam(nama.toUpperCase());
                ep.setEnkuiri(enkuiri);
                ep.setCawangan(kc);
                ep.setInfoAudit(ia);
                lelongService.saveOrUpdate(ep);
            }
        }
        if (listPeminjam.size() > 0) {
            int j = 0;
            for (j = 0; j < listPeminjam.size(); j++) {
                nama = getContext().getRequest().getParameter("namaPeminjam" + j);
                LOG.info("nama : " + nama.toUpperCase());
                ep = listPeminjam.get(j);
                ep.setNama_peminjam(nama.toUpperCase());
                lelongService.saveOrUpdate(ep);
            }
            rowCount3 = rowCount3 + 1 - listPeminjam.size();
            LOG.info("rowCount3 : " + rowCount3);
            LOG.info("j : " + j);
            if (rowCount3 > 0) {
                for (int i = 0; i < rowCount3; i++) {
                    nama = getContext().getRequest().getParameter("namaPeminjam" + j);
                    LOG.info("nama : " + nama.toUpperCase());
                    ep = new EnkuiriPeminjam();
                    ep.setNama_peminjam(nama.toUpperCase());
                    ep.setEnkuiri(enkuiri);
                    ep.setCawangan(kc);
                    ep.setInfoAudit(ia);
                    lelongService.saveOrUpdate(ep);
                    j++;
                }
            }
        }
        rehydrate();
        showForm();
        addSimpleMessage("Maklumat telah berjaya disimpan.");
        return new JSP("lelong/Butir-butirPinjaman_overdraft.jsp").addParameter("tab", "true");
    }

    public Resolution delete() {
        String id = getContext().getRequest().getParameter("id");
        LOG.info("id : " + id);
        EnkuiriPeminjam ep = enkuiriPeminjamDAO.findById(Long.parseLong(id));
        lelongService.delete(ep);
        rehydrate();
        return new JSP("lelong/Butir-butirPinjaman_overdraft.jsp").addParameter("tab", "true");
    }

    public List<Enkuiri> getSenaraiEnkuiri() {
        return senaraiEnkuiri;
    }

    public void setSenaraiEnkuiri(List<Enkuiri> senaraiEnkuiri) {
        this.senaraiEnkuiri = senaraiEnkuiri;
    }

    public String getTarikhMulaTunggakan() {
        return tarikhMulaTunggakan;
    }

    public void setTarikhMulaTunggakan(String tarikhMulaTunggakan) {
        this.tarikhMulaTunggakan = tarikhMulaTunggakan;
    }

    public String getTarikhGadaian() {
        return tarikhGadaian;
    }

    public void setTarikhGadaian(String tarikhGadaian) {
        this.tarikhGadaian = tarikhGadaian;
    }

    public Enkuiri getEnkuiri() {
        return enkuiri;
    }

    public void setEnkuiri(Enkuiri enkuiri) {
        this.enkuiri = enkuiri;
    }

    public Permohonan getPermohonan() {
        return permohonan;
    }

    public void setPermohonan(Permohonan permohonan) {
        this.permohonan = permohonan;
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public String getNegeri() {
        return negeri;
    }

    public void setNegeri(String negeri) {
        this.negeri = negeri;
    }

    public String getTarikhMulaBayarPinjaman() {
        return tarikhMulaBayarPinjaman;
    }

    public void setTarikhMulaBayarPinjaman(String tarikhMulaBayarPinjaman) {
        this.tarikhMulaBayarPinjaman = tarikhMulaBayarPinjaman;
    }

    public String getIdPermohonan() {
        return idPermohonan;
    }

    public void setIdPermohonan(String idPermohonan) {
        this.idPermohonan = idPermohonan;
    }

    public String getTarikhWarta() {
        return tarikhWarta;
    }

    public void setTarikhWarta(String tarikhWarta) {
        this.tarikhWarta = tarikhWarta;
    }

    public List<EnkuiriPeminjam> getListPeminjam() {
        return listPeminjam;
    }

    public void setListPeminjam(List<EnkuiriPeminjam> listPeminjam) {
        this.listPeminjam = listPeminjam;
    }

    public int getRowCount3() {
        return rowCount3;
    }

    public void setRowCount3(int rowCount3) {
        this.rowCount3 = rowCount3;
    }

    public int getRowCount4() {
        return rowCount4;
    }

    public void setRowCount4(int rowCount4) {
        this.rowCount4 = rowCount4;
    }
}
