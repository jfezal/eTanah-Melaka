/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.stripes.pengambilan.share.common;

import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.dao.KodDokumenDAO;
import etanah.dao.PermohonanDAO;
import etanah.model.InfoAudit;
import etanah.model.KodDokumen;
import etanah.model.KodNotis;
import etanah.model.Pengguna;
import etanah.model.PenggunaPeranan;
import etanah.model.Permohonan;
import etanah.model.PermohonanTandatanganDokumen;
import etanah.model.ambil.BorangPerHakmilik;
import etanah.model.ambil.BorangPerPB;
import etanah.model.ambil.BorangPerPermohonan;
import etanah.model.ambil.PermohonanPengambilan;
import etanah.service.acq.service.TandatanganManualService;
import etanah.service.common.PermohonanRujukanLuarService;
import etanah.view.BasicTabActionBean;
import etanah.view.etanahActionBeanContext;
import java.util.Date;
import java.util.List;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;
import org.apache.commons.lang.StringUtils;

/**
 *
 * @author mohd.faidzal
 */
@UrlBinding("/pengambilan/tandatangan_manual")
public class TandatanganBorangActionBean extends BasicTabActionBean {

    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    KodDokumenDAO kodDokumenDAO;
    @Inject
    TandatanganManualService tandatanganManualService;
    @Inject
    PermohonanRujukanLuarService serviceBaru;
    List<PenggunaPeranan> listPenggunaPeranan;
    String tandatangan;
    String kodNotis[];
    String kodDokumen[];
    String namaDokumen;
    String namaDokumen1;

    String kodNotis1[];
    String kodDokumen1[];
    private PermohonanPengambilan permohonanPengambilan;

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
//        if (kodNotis != null) {
//            BorangPerPermohonan bpp = tandatanganManualService.findByKodNotis(kodNotis, idPermohonan);
//            Pengguna p = bpp.getDitandatangan();
//            if (p != null) {
//                tandatangan = p.getIdPengguna();
//            }
//        }
    }

    @DefaultHandler
    public Resolution borangAForm() {
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");

        String[] kumpBpel = {"ptg"};
        kodNotis = new String[1];
        kodNotis[0] = "NAA";
        kodDokumen = new String[1];
        kodDokumen[0] = "A";
        namaDokumen = setNamaDokumen(kodDokumen);
        BorangPerPermohonan bp = tandatanganManualService.findByKodNotis(kodNotis, idPermohonan);
        if (bp != null) {
            Pengguna p = bp.getDitandatangan();
            if (p != null) {
                tandatangan = p.getIdPengguna();
            }
        }
        listPenggunaPeranan = tandatanganManualService.listSenaraiTandatangan(kumpBpel);
        return new JSP("/pengambilan/APT/tandatangan_dokumen_manual.jsp").addParameter("tab", "true");
    }

    public Resolution borangBForm() {
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        String[] kumpBpel = {"ptg"};
        kodNotis = new String[1];
        kodNotis[0] = "NBB";
        kodDokumen = new String[1];
        kodDokumen[0] = "B";
        namaDokumen = setNamaDokumen(kodDokumen);
        BorangPerPermohonan bp = tandatanganManualService.findByKodNotis(kodNotis, idPermohonan);
        if (bp != null) {
            Pengguna p = bp.getDitandatangan();
            if (p != null) {
                tandatangan = p.getIdPengguna();
            }
        }
        listPenggunaPeranan = tandatanganManualService.listSenaraiTandatangan(kumpBpel);
        return new JSP("/pengambilan/APT/tandatangan_dokumen_manual.jsp").addParameter("tab", "true");
    }

    public Resolution borangDForm() {
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        String[] kumpBpel = {"ptg"};
        kodNotis = new String[1];
        kodNotis[0] = "NBD";
        kodDokumen = new String[3];
        kodDokumen[0] = "D";
        kodDokumen[1] = "SIID";
        kodDokumen[2] = "SEWD";
        namaDokumen = setNamaDokumen(kodDokumen);
        BorangPerPermohonan bp = tandatanganManualService.findByKodNotis(kodNotis, idPermohonan);
        if (bp != null) {
            Pengguna p = bp.getDitandatangan();
            if (p != null) {
                tandatangan = p.getIdPengguna();
            }
        }
        for (int i = 0; i < kodDokumen.length; i++) {
            PermohonanTandatanganDokumen p = tandatanganManualService.findByttByIDMohon(idPermohonan, kodDokumen[i]);
            if (p != null) {
                tandatangan = p.getDiTandatangan();
            }
        }
        listPenggunaPeranan = tandatanganManualService.listSenaraiTandatangan(kumpBpel);
        return new JSP("/pengambilan/APT/tandatangan_dokumen_manual.jsp").addParameter("tab", "true");
    }

    public Resolution borangSuratDForm() {
        Pengguna pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        String[] kumpBpel = {"ptg"};
        kodNotis = new String[1];
        kodNotis[0] = "NBD";

        kodDokumen = new String[3];
        kodDokumen[0] = "D";
        kodDokumen[1] = "SIID";
        kodDokumen[2] = "SEWD";
        //kodDokumen[3] = "8KUK";

        namaDokumen = setNamaDokumen(kodDokumen);

        BorangPerPermohonan bp = tandatanganManualService.findByKodNotis(kodNotis, idPermohonan);
        if (bp != null) {
            Pengguna p = bp.getDitandatangan();
            if (p != null) {
                tandatangan = p.getIdPengguna();
            }
        }

        for (int i = 0; i < kodDokumen.length; i++) {
            PermohonanTandatanganDokumen p = tandatanganManualService.findByttByIDMohon(idPermohonan, kodDokumen[i]);
            if (p != null) {
                tandatangan = p.getDiTandatangan();
            }
        }

        listPenggunaPeranan = tandatanganManualService.listSenaraiTandatangan(kumpBpel);
        return new JSP("/pengambilan/APT/tandatangan_dokumen_manual.jsp").addParameter("tab", "true");
    }

    public Resolution sediaGH() {
        Pengguna pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        String[] kumpBpel = {"ptg"};
        kodNotis = new String[2];
        kodNotis[0] = "NBM";
        kodNotis[1] = "NBG";

        kodNotis1 = new String[1];
        kodNotis1[0] = "NBH";

        kodDokumen = new String[3];
        kodDokumen[0] = "SKPN";
        kodDokumen[1] = "SIIG";
        kodDokumen[2] = "SIIH";

        namaDokumen = setNamaDokumen(kodDokumen);

        List<BorangPerPB> lBpb = tandatanganManualService.findBorangPerPBbyKodNotis(kodNotis1, idPermohonan);
        if (!lBpb.isEmpty()) {
            tandatangan = lBpb.get(0).getDitandatangan();

        }

        List<BorangPerHakmilik> lBph = tandatanganManualService.findBorangPerHakmilikByKodNotis(kodNotis, idPermohonan);
        if (!lBph.isEmpty()) {
            tandatangan = lBph.get(0).getDitandatangan();

        }

        for (int i = 0; i < kodDokumen.length; i++) {
            PermohonanTandatanganDokumen p = tandatanganManualService.findByttByIDMohon(idPermohonan, kodDokumen[i]);
            if (p != null) {
                tandatangan = p.getDiTandatangan();
            }
        }

        listPenggunaPeranan = tandatanganManualService.listSenaraiTandatangan(kumpBpel);
        return new JSP("/pengambilan/APT/tandatangan_dokumen_manual.jsp").addParameter("tab", "true");
    }

    public Resolution borangEAduanForm() {
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        String[] kumpBpel = {"pptd"};
        kodNotis = new String[1];
        kodNotis[0] = "NBE";
        kodDokumen = new String[1];
        kodDokumen[0] = "NSST";
        namaDokumen = setNamaDokumen(kodDokumen);
        List<BorangPerHakmilik> lBph = tandatanganManualService.findBorangPerHakmilikByKodNotis(kodNotis, idPermohonan);
        if (!lBph.isEmpty()) {
            tandatangan = lBph.get(0).getDitandatangan();

        }
        listPenggunaPeranan = tandatanganManualService.listSenaraiTandatangan(kumpBpel);
        return new JSP("/pengambilan/APT/tandatangan_dokumen_manual.jsp").addParameter("tab", "true");
    }

    public Resolution suratKemasukanForm() {
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        String[] kumpBpel = {"ptd", "pptd"};
        kodDokumen = new String[2];
        kodDokumen[0] = "JPPHA";
        kodDokumen[1] = "UPEN";
        namaDokumen = setNamaDokumen(kodDokumen);
        PermohonanTandatanganDokumen p = tandatanganManualService.findByttByIDMohon(idPermohonan, kodDokumen[0]);
        if (p != null) {
            tandatangan = p.getDiTandatangan();
        }
        listPenggunaPeranan = tandatanganManualService.listSenaraiTandatangan(kumpBpel);
        return new JSP("/pengambilan/APT/tandatangan_dokumen_manual.jsp").addParameter("tab", "true");
    }

    public Resolution suratKemasukanFormAduan() {
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        String[] kumpBpel = {"ptd"};
        kodDokumen = new String[1];
        kodDokumen[0] = "STA";

        namaDokumen = setNamaDokumen(kodDokumen);
        PermohonanTandatanganDokumen p = tandatanganManualService.findByttByIDMohon(idPermohonan, kodDokumen[0]);
        if (p != null) {
            tandatangan = p.getDiTandatangan();
        }
        listPenggunaPeranan = tandatanganManualService.listSenaraiTandatangan(kumpBpel);
        return new JSP("/pengambilan/APT/tandatangan_dokumen_manual.jsp").addParameter("tab", "true");
    }
    

    public Resolution sijilUkur() {
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        String[] kumpBpel = {"ptd"};
        kodDokumen = new String[4];
        kodDokumen[0] = "SPU";
        kodDokumen[1] = "8SPU";
        kodDokumen[2] = "PU";
        kodDokumen[3] = "SMPU";
        namaDokumen = setNamaDokumen(kodDokumen);
        PermohonanTandatanganDokumen p = tandatanganManualService.findByttByIDMohon(idPermohonan, kodDokumen[0]);
        if (p != null) {
            tandatangan = p.getDiTandatangan();
        }
        listPenggunaPeranan = tandatanganManualService.listSenaraiTandatangan(kumpBpel);
        return new JSP("/pengambilan/APT/tandatangan_dokumen_manual.jsp").addParameter("tab", "true");
    }

    public Resolution terimaSijilUkur() {
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        String[] kumpBpel = {"ptd"};
        kodDokumen = new String[1];
        kodDokumen[0] = "SPU";

        namaDokumen = setNamaDokumen(kodDokumen);
        PermohonanTandatanganDokumen p = tandatanganManualService.findByttByIDMohon(idPermohonan, kodDokumen[0]);
        if (p != null) {
            tandatangan = p.getDiTandatangan();
        }
        listPenggunaPeranan = tandatanganManualService.listSenaraiTandatangan(kumpBpel);
        return new JSP("/pengambilan/APT/tandatangan_dokumen_manual.jsp").addParameter("tab", "true");
    }

    public Resolution suratMakluman() {
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        String[] kumpBpel = {"ptd"};
        kodDokumen = new String[1];
        kodDokumen[0] = "8MAK";
        namaDokumen = setNamaDokumen(kodDokumen);
        PermohonanTandatanganDokumen p = tandatanganManualService.findByttByIDMohon(idPermohonan, kodDokumen[0]);
        if (p != null) {
            tandatangan = p.getDiTandatangan();
        }
        listPenggunaPeranan = tandatanganManualService.listSenaraiTandatangan(kumpBpel);
        return new JSP("/pengambilan/APT/tandatangan_dokumen_manual.jsp").addParameter("tab", "true");
    }

    public Resolution suratMaklumanSek4() {
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        String[] kumpBpel = {"ptd"};
        kodDokumen = new String[1];
        kodDokumen[0] = "SMM";
        namaDokumen = setNamaDokumen(kodDokumen);
        PermohonanTandatanganDokumen p = tandatanganManualService.findByttByIDMohon(idPermohonan, kodDokumen[0]);
        if (p != null) {
            tandatangan = p.getDiTandatangan();
        }
        listPenggunaPeranan = tandatanganManualService.listSenaraiTandatangan(kumpBpel);
        return new JSP("/pengambilan/APT/tandatangan_dokumen_manual.jsp").addParameter("tab", "true");
    }

    public Resolution suratEndorsan() {
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        String[] kumpBpel = {"ptd"};
        kodDokumen = new String[2];
        kodDokumen[0] = "8TAN";
        kodDokumen[1] = "SEBD";
        namaDokumen = setNamaDokumen(kodDokumen);
        PermohonanTandatanganDokumen p = tandatanganManualService.findByttByIDMohon(idPermohonan, kodDokumen[0]);
        if (p != null) {
            tandatangan = p.getDiTandatangan();
        }
        listPenggunaPeranan = tandatanganManualService.listSenaraiTandatangan(kumpBpel);
        return new JSP("/pengambilan/APT/tandatangan_dokumen_manual.jsp").addParameter("tab", "true");
    }

    public Resolution baucer() {
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        String[] kumpBpel = {"ptd"};
        kodDokumen = new String[2];
        kodDokumen[0] = "8DEP";
        kodDokumen[1] = "8BRG2";
        namaDokumen = setNamaDokumen(kodDokumen);
        PermohonanTandatanganDokumen p = tandatanganManualService.findByttByIDMohon(idPermohonan, kodDokumen[0]);
        if (p != null) {
            tandatangan = p.getDiTandatangan();
        }
        listPenggunaPeranan = tandatanganManualService.listSenaraiTandatangan(kumpBpel);
        return new JSP("/pengambilan/APT/tandatangan_dokumen_manual.jsp").addParameter("tab", "true");
    }

    public Resolution borang5Dan6() {
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        String[] kumpBpel = {"ptd"};
        kodDokumen = new String[2];
        kodDokumen[0] = "5";
        kodDokumen[1] = "6";
        namaDokumen = setNamaDokumen(kodDokumen);
        PermohonanTandatanganDokumen p = tandatanganManualService.findByttByIDMohon(idPermohonan, kodDokumen[0]);
        if (p != null) {
            tandatangan = p.getDiTandatangan();
        }
        listPenggunaPeranan = tandatanganManualService.listSenaraiTandatangan(kumpBpel);
        return new JSP("/pengambilan/APT/tandatangan_dokumen_manual.jsp").addParameter("tab", "true");
    }

    public Resolution aduanSBPC() {
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        String[] kumpBpel = {"pptd"};
        kodDokumen = new String[2];
        kodDokumen[0] = "SBPC";
        namaDokumen = setNamaDokumen(kodDokumen);
        PermohonanTandatanganDokumen p = tandatanganManualService.findByttByIDMohon(idPermohonan, kodDokumen[0]);
        if (p != null) {
            tandatangan = p.getDiTandatangan();
        }
        listPenggunaPeranan = tandatanganManualService.listSenaraiTandatangan(kumpBpel);
        return new JSP("/pengambilan/APT/tandatangan_dokumen_manual.jsp").addParameter("tab", "true");
    }

    public Resolution aduanAB() {
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        String[] kumpBpel = {"pptd"};
        kodDokumen = new String[1];
        kodDokumen[0] = "AB";
        namaDokumen = setNamaDokumen(kodDokumen);
        PermohonanTandatanganDokumen p = tandatanganManualService.findByttByIDMohon(idPermohonan, kodDokumen[0]);
        if (p != null) {
            tandatangan = p.getDiTandatangan();
        }
        listPenggunaPeranan = tandatanganManualService.listSenaraiTandatangan(kumpBpel);
        return new JSP("/pengambilan/APT/tandatangan_dokumen_manual.jsp").addParameter("tab", "true");
    }

    public Resolution suratIringanI() {
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        String[] kumpBpel = {"pptd"};
        kodDokumen = new String[1];
        kodDokumen[0] = "SIII";
        namaDokumen = setNamaDokumen(kodDokumen);
        PermohonanTandatanganDokumen p = tandatanganManualService.findByttByIDMohon(idPermohonan, kodDokumen[0]);
        if (p != null) {
            tandatangan = p.getDiTandatangan();
        }
        listPenggunaPeranan = tandatanganManualService.listSenaraiTandatangan(kumpBpel);
        return new JSP("/pengambilan/APT/tandatangan_dokumen_manual.jsp").addParameter("tab", "true");
    }

    public Resolution borangO() {
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        String[] kumpBpel = {"pptd"};
        kodDokumen = new String[1];
        kodDokumen[0] = "O";
        kodNotis = new String[2];
        kodNotis[0] = "NBO";
        namaDokumen = setNamaDokumen(kodDokumen);

        BorangPerPermohonan bp = tandatanganManualService.findByKodNotis(kodNotis, idPermohonan);
        if (bp != null) {
            Pengguna p = bp.getDitandatangan();
            if (p != null) {
                tandatangan = p.getIdPengguna();
            }
        }
        PermohonanTandatanganDokumen p = tandatanganManualService.findByttByIDMohon(idPermohonan, kodDokumen[0]);
        if (p != null) {
            tandatangan = p.getDiTandatangan();
        }
        listPenggunaPeranan = tandatanganManualService.listSenaraiTandatangan(kumpBpel);
        return new JSP("/pengambilan/APT/tandatangan_dokumen_manual_borangO.jsp").addParameter("tab", "true");
    }

    public Resolution suratIringanJK() {
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        String[] kumpBpel = {"pptd"};
        kodDokumen = new String[2];
        kodDokumen[0] = "SIIJ";
        kodDokumen[1] = "SIIK";

        kodNotis = new String[1];
        kodNotis1 = new String[1];
        kodNotis[0] = "NBJ";
        kodNotis1[0] = "NBK";

        namaDokumen = setNamaDokumen(kodDokumen);
        for (int i = 0; i < kodDokumen.length; i++) {
            PermohonanTandatanganDokumen p = tandatanganManualService.findByttByIDMohon(idPermohonan, kodDokumen[i]);
            if (p != null) {
                tandatangan = p.getDiTandatangan();
            }
        }

        listPenggunaPeranan = tandatanganManualService.listSenaraiTandatangan(kumpBpel);
        return new JSP("/pengambilan/APT/tandatangan_dokumen_manual.jsp").addParameter("tab", "true");
    }

    public Resolution borangEF() {
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        String[] kumpBpel = {"pptd"};
        kodNotis = new String[2];

        kodNotis[0] = "NBE";
        kodNotis[1] = "NBF";
        kodDokumen = new String[1];
        kodDokumen[0] = "SIIE";
        namaDokumen = setNamaDokumen(kodDokumen);
        PermohonanTandatanganDokumen p = tandatanganManualService.findByttByIDMohon(idPermohonan, kodDokumen[0]);
        if (p != null) {
            tandatangan = p.getDiTandatangan();
        }

        List<BorangPerHakmilik> lBph = tandatanganManualService.findBorangPerHakmilikByKodNotis(kodNotis, idPermohonan);
        if (!lBph.isEmpty()) {
            tandatangan = lBph.get(0).getDitandatangan();

        }
        listPenggunaPeranan = tandatanganManualService.listSenaraiTandatangan(kumpBpel);
        return new JSP("/pengambilan/APT/tandatangan_dokumen_manual.jsp").addParameter("tab", "true");
    }

    public Resolution borangKL() {
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        String[] kumpBpel = {"pptd"};
        kodNotis = new String[2];
        kodNotis[0] = "NBK";
        kodNotis[1] = "NBL";

        kodNotis1 = new String[1];
        kodNotis1[0] = "NBL";

        kodDokumen = new String[2];
        kodDokumen[0] = "8WAA";
        kodDokumen[1] = "APC";

        namaDokumen = setNamaDokumen(kodDokumen);
        PermohonanTandatanganDokumen p = tandatanganManualService.findByttByIDMohon(idPermohonan, kodDokumen[0]);
        if (p != null) {
            tandatangan = p.getDiTandatangan();
        }

        List<BorangPerPB> lBpb = tandatanganManualService.findBorangPerPBbyKodNotis(kodNotis1, idPermohonan);
        if (!lBpb.isEmpty()) {
            tandatangan = lBpb.get(0).getDitandatangan();

        }

        List<BorangPerHakmilik> lBph = tandatanganManualService.findBorangPerHakmilikByKodNotis(kodNotis, idPermohonan);
        if (!lBph.isEmpty()) {
            tandatangan = lBph.get(0).getDitandatangan();

        }
        listPenggunaPeranan = tandatanganManualService.listSenaraiTandatangan(kumpBpel);
        return new JSP("/pengambilan/APT/tandatangan_dokumen_manual.jsp").addParameter("tab", "true");
    }

    public Resolution borangGH() {
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        String[] kumpBpel = {"pptd"};
        kodNotis = new String[1];
        kodNotis[0] = "NBG";
        kodNotis1 = new String[1];
        kodNotis1[0] = "NBH";
        kodDokumen = new String[1];
        kodDokumen[0] = "SKPN";
        namaDokumen = setNamaDokumen(kodDokumen);
        PermohonanTandatanganDokumen p = tandatanganManualService.findByttByIDMohon(idPermohonan, kodDokumen[0]);
        if (p != null) {
            tandatangan = p.getDiTandatangan();
        }

        List<BorangPerPB> lBpb = tandatanganManualService.findBorangPerPBbyKodNotis(kodNotis1, idPermohonan);
        if (!lBpb.isEmpty()) {
            tandatangan = lBpb.get(0).getDitandatangan();

        }

        List<BorangPerHakmilik> lBph = tandatanganManualService.findBorangPerHakmilikByKodNotis(kodNotis, idPermohonan);
        if (!lBph.isEmpty()) {
            tandatangan = lBph.get(0).getDitandatangan();

        }
        listPenggunaPeranan = tandatanganManualService.listSenaraiTandatangan(kumpBpel);
        return new JSP("/pengambilan/APT/tandatangan_dokumen_manual.jsp").addParameter("tab", "true");
    }

    public Resolution simpanTandatangan() {
//    tandatangan
        Pengguna pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        if (kodNotis != null) {
            tandatanganManualService.simpanBykodNotis(kodNotis, tandatangan, idPermohonan);
        }
        if (kodNotis1 != null) {
            tandatanganManualService.simpanBykodNotis(kodNotis1, tandatangan, idPermohonan);
        }
        for (String kodDokBaru : kodDokumen) {
            KodDokumen kodBaru = kodDokumenDAO.findById(kodDokBaru);
            PermohonanTandatanganDokumen mohondoktt = tandatanganManualService.findByttByIDMohon(idPermohonan, kodBaru.getKod());
            if (mohondoktt != null) {
            } else {
                mohondoktt = new PermohonanTandatanganDokumen();
            }
            Permohonan permohonan = permohonanDAO.findById(idPermohonan);
            mohondoktt.setPermohonan(permohonan);
            mohondoktt.setDiTandatangan(tandatangan);
            mohondoktt.setKodDokumen(kodBaru);
            mohondoktt.setInfoAudit(setInfoAudit(pengguna));
            mohondoktt.setCawangan(permohonan.getCawangan());
            tandatanganManualService.saveMohonDoktt(mohondoktt);
        }

        addSimpleMessage("Maklumat berjaya disimpan");
        this.tandatangan = tandatangan;
        return new JSP("/pengambilan/APT/tandatangan_dokumen_manual.jsp").addParameter("tab", "true").addParameter("tandatangan", tandatangan);
    }

    public Resolution simpanTandatanganBorangO() {
//    tandatangan
        Pengguna pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
//        if (kodNotis != null) {
        kodNotis[0] = "NBO";
        tandatanganManualService.simpanBykodNotis(kodNotis, tandatangan, idPermohonan);
//        }
        for (String kodDokBaru : kodDokumen) {
            KodDokumen kodBaru = kodDokumenDAO.findById(kodDokBaru);
            PermohonanTandatanganDokumen mohondoktt = tandatanganManualService.findByttByIDMohon(idPermohonan, kodBaru.getKod());
            if (mohondoktt != null) {
            } else {
                mohondoktt = new PermohonanTandatanganDokumen();
            }
            Permohonan permohonan = permohonanDAO.findById(idPermohonan);
            mohondoktt.setPermohonan(permohonan);
            mohondoktt.setDiTandatangan(tandatangan);
            mohondoktt.setKodDokumen(kodBaru);
            mohondoktt.setInfoAudit(setInfoAudit(pengguna));
            mohondoktt.setCawangan(permohonan.getCawangan());
            tandatanganManualService.saveMohonDoktt(mohondoktt);
        }

        addSimpleMessage("Maklumat berjaya disimpan");
        this.tandatangan = tandatangan;
        return new JSP("/pengambilan/APT/tandatangan_dokumen_manual.jsp").addParameter("tab", "true").addParameter("tandatangan", tandatangan);
    }

    InfoAudit setInfoAudit(Pengguna p) {
        InfoAudit ia = new InfoAudit();
        ia.setDimasukOleh(p);
        ia.setTarikhMasuk(new Date());
        return ia;
    }

    String setNamaDokumen(String[] kodDokumen) {
        String a = "";
        for (int i = 0; i < kodDokumen.length; i++) {
            if (kodDokumen[i] != null) {

                KodDokumen d = kodDokumenDAO.findById(kodDokumen[i]);
                if (i == 0) {
                    a = d.getNama();
                } else if (i == kodDokumen.length - 1) {
                    a = a + " dan " + d.getNama();
                } else {
                    a = a + ", " + d.getNama();
                }
            }
        }
        return a;
    }

    public List<PenggunaPeranan> getListPenggunaPeranan() {
        return listPenggunaPeranan;
    }

    public void setListPenggunaPeranan(List<PenggunaPeranan> listPenggunaPeranan) {
        this.listPenggunaPeranan = listPenggunaPeranan;
    }

    public String getTandatangan() {
        return tandatangan;
    }

    public void setTandatangan(String tandatangan) {
        this.tandatangan = tandatangan;
    }

    public String[] getKodNotis() {
        return kodNotis;
    }

    public void setKodNotis(String[] kodNotis) {
        this.kodNotis = kodNotis;
    }

    public String[] getKodDokumen() {
        return kodDokumen;
    }

    public void setKodDokumen(String[] kodDokumen) {
        this.kodDokumen = kodDokumen;
    }

    public String getNamaDokumen() {
        return namaDokumen;
    }

    public void setNamaDokumen(String namaDokumen) {
        this.namaDokumen = namaDokumen;
    }

    public String getNamaDokumen1() {
        return namaDokumen1;
    }

    public void setNamaDokumen1(String namaDokumen1) {
        this.namaDokumen1 = namaDokumen1;
    }

    public String[] getKodDokumen1() {
        return kodDokumen1;
    }

    public void setKodDokumen1(String[] kodDokumen1) {
        this.kodDokumen1 = kodDokumen1;
    }

    public String[] getKodNotis1() {
        return kodNotis1;
    }

    public void setKodNotis1(String[] kodNotis1) {
        this.kodNotis1 = kodNotis1;
    }

}
