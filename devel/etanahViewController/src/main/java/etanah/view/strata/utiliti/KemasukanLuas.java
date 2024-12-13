/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.strata.utiliti;

import able.stripes.AbleActionBean;
import com.google.inject.Inject;
import etanah.dao.*;
import etanah.model.*;
import etanah.model.strata.*;
import etanah.service.StrataPtService;
import etanah.view.etanahActionBeanContext;
import java.math.BigDecimal;
import java.math.MathContext;
import java.util.*;
import net.sourceforge.stripes.action.*;
import net.sourceforge.stripes.controller.LifecycleStage;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author siti.mudmainnah
 */
@UrlBinding("/strata/KemasukanLuas")
public class KemasukanLuas extends AbleActionBean {

    private static final Logger LOG = Logger.getLogger(KemasukanLuas.class);
    @Inject
    private etanah.Configuration conf;
    @Inject
    private HakmilikDAO hakmilikDAO;
    @Inject
    private HakmilikPetakAksesoriDAO hakmilikPetakAksesoriDAO;
    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;
    private Pengguna pengguna;
    private String idHakmilik;
    private String luasKatg;
    private String luas;
    private List<Hakmilik> senaraiLuasPetak = new ArrayList<Hakmilik>();
    private List<HakmilikPetakAksesori> senaraiLuasPetakAksr = new ArrayList<HakmilikPetakAksesori>();
    @Inject
    StrataPtService strataPtService;
    BigDecimal luasTotal = BigDecimal.ZERO;
    BigDecimal luasTotalAksr = BigDecimal.ZERO;
    BigDecimal total = BigDecimal.ZERO;

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {


        pengguna = (Pengguna) context.getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);

    }

    @DefaultHandler
    public Resolution showForm() {
        LOG.info("showForm");
        idHakmilik = null;

//        senaraiLuasPetak = null;
//        senaraiLuasPetakAksr = null;
        return new ForwardResolution("/WEB-INF/jsp/strata/utiliti/kemasukanLuas.jsp");
    }

    public void kiraLuas(List<Hakmilik> senaraiLuasPetak, List<HakmilikPetakAksesori> senaraiLuasPetakAksr) {
        BigDecimal test = BigDecimal.ZERO;
        for (Hakmilik luasHakmilik : senaraiLuasPetak) {
            if (luasTotal.equals(test)) {
                if (luasHakmilik.getLuas() != null) {
                    luasTotal = luasHakmilik.getLuas();
                }
            } else {
                if (luasHakmilik.getLuas() != null) {
                    luasTotal = luasTotal.add(luasHakmilik.getLuas());
                }
            }
        }
        for (HakmilikPetakAksesori luasPetakAksr : senaraiLuasPetakAksr) {
            if (luasPetakAksr.equals(test)) {
                if (luasPetakAksr.getLuas() != null) {
                    luasTotalAksr = luasPetakAksr.getLuas();
                }
            } else {
                if (luasPetakAksr.getLuas() != null) {
                    luasTotalAksr = luasTotalAksr.add(luasPetakAksr.getLuas());
                }
            }
        }
        total = luasTotalAksr.add(luasTotal);
    }

    public Resolution cari() {
        senaraiLuasPetak = strataPtService.findHakmilibyParent(idHakmilik);

        if (senaraiLuasPetak.isEmpty()) {
            senaraiLuasPetak = strataPtService.findHakmilik(idHakmilik);
        }
        senaraiLuasPetakAksr = strataPtService.findHmPetakAksr(idHakmilik);
        if (senaraiLuasPetakAksr.isEmpty()) {
            senaraiLuasPetakAksr = strataPtService.findHmPetakAksr(idHakmilik + "%");
        }

        if (senaraiLuasPetak.isEmpty()) {
            addSimpleError("Luas petak tidak dijumpai.");
        }
        if (senaraiLuasPetakAksr.isEmpty()) {
            addSimpleError("Luas petak aksesori tiada.");
        }
        if (senaraiLuasPetak.isEmpty() && senaraiLuasPetakAksr.isEmpty()) {
            addSimpleError("Luas petak aksesori dan luas petak aksesori tidak dijumpai.");

        }
        kiraLuas(senaraiLuasPetak, senaraiLuasPetakAksr);
        return new ForwardResolution("/WEB-INF/jsp/strata/utiliti/kemasukanLuas.jsp");
    }

    public Resolution simpan() {
        String[] hm = getContext().getRequest().getParameterValues("hakmilik");
        String[] nama = getContext().getRequest().getParameterValues("nama");
        String[] luas = getContext().getRequest().getParameterValues("luas");

        InfoAudit ia = new InfoAudit();
        ia.setDiKemaskiniOleh(pengguna);
        ia.setTarikhKemaskini(new Date());

        Transaction tx = sessionProvider.get().beginTransaction();
        try {
            for (int i = 0; i < hm.length; i++) {
                if (StringUtils.isNotBlank(nama[i])) {
                    HakmilikPetakAksesori aksr = strataPtService.findPetakAksr(hm[i], nama[i]);

                    if (aksr != null) {
                        if (StringUtils.isNotBlank(luas[i])) {
                            aksr.setLuas(BigDecimal.valueOf(Double.valueOf(luas[i])));
                        }
                        aksr.setInfoAudit(ia);
                        strataPtService.simpanhakmilikPetakAks(aksr);
                    }
                } else {
                    Hakmilik petak = strataPtService.findInfoByIdHakmilik(hm[i]);

                    if (petak != null) {
                        if (StringUtils.isNotBlank(luas[i])) {
                            petak.setLuas(BigDecimal.valueOf(Double.valueOf(luas[i])));
                        }
                        petak.setInfoAudit(ia);
                        strataPtService.simpanhakmilik(petak);
                    }
                }
            }
            tx.commit();
            addSimpleMessage("Luas berjaya dikemaskini.");
        } catch (Exception ex) {
            tx.rollback();
            addSimpleError("Luas tidak berjaya dikemaskini.");
        }

        rehydrate();
        return new ForwardResolution("/WEB-INF/jsp/strata/utiliti/kemasukanLuas.jsp");
    }

    public Pengguna getPengguna() {
        return pengguna;
    }

    public void setPengguna(Pengguna pengguna) {
        this.pengguna = pengguna;
    }

    public String getIdHakmilik() {
        return idHakmilik;
    }

    public void setIdHakmilik(String idHakmilik) {
        this.idHakmilik = idHakmilik;
    }

    public String getLuasKatg() {
        return luasKatg;
    }

    public void setLuasKatg(String luasKatg) {
        this.luasKatg = luasKatg;
    }

    public List<Hakmilik> getSenaraiLuasPetak() {
        return senaraiLuasPetak;
    }

    public void setSenaraiLuasPetak(List<Hakmilik> senaraiLuasPetak) {
        this.senaraiLuasPetak = senaraiLuasPetak;
    }

    public List<HakmilikPetakAksesori> getSenaraiLuasPetakAksr() {
        return senaraiLuasPetakAksr;
    }

    public void setSenaraiLuasPetakAksr(List<HakmilikPetakAksesori> senaraiLuasPetakAksr) {
        this.senaraiLuasPetakAksr = senaraiLuasPetakAksr;
    }

    public String getLuas() {
        return luas;
    }

    public void setLuas(String luas) {
        this.luas = luas;
    }

    public BigDecimal getLuasTotal() {
        return luasTotal;
    }

    public void setLuasTotal(BigDecimal luasTotal) {
        this.luasTotal = luasTotal;
    }

    public BigDecimal getLuasTotalAksr() {
        return luasTotalAksr;
    }

    public void setLuasTotalAksr(BigDecimal luasTotalAksr) {
        this.luasTotalAksr = luasTotalAksr;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal Total) {
        this.total = Total;
    }
}
