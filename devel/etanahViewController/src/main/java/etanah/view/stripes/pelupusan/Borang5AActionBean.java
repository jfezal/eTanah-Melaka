/**
 *
 * @author nurul.izza
 * changed by Ravi
 * modify by Siti Fariza Hanim
 */
package etanah.view.stripes.pelupusan;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.model.PermohonanTuntutanKos;
import etanah.service.PelupusanService;
import etanah.dao.PermohonanDAO;
import etanah.model.InfoAudit;
import etanah.model.KodCawangan;
import etanah.model.Pemohon;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.model.PermohonanPihak;
import etanah.view.etanahActionBeanContext;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;

@UrlBinding("/pelupusan/Borang5A")
public class Borang5AActionBean extends AbleActionBean {

    @Inject
    PelupusanService pelupusanService;
//    private HakmilikPermohonan hakmilikpermohonan;
    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    private PermohonanTuntutanKos permohonantuntutkos ,p;
    private List<PermohonanTuntutanKos> permohonanTuntutanKoseslist;
    private Pengguna pguna;
    private Pemohon pemohon;
    private Permohonan permohonan;
    private PermohonanPihak permohonanPihak;
    private String amaunTuntutan;
    public  String item;
    private String idPermohonan;
    private BigDecimal cukai;
    private BigDecimal primium;
    private BigDecimal upah;
    private BigDecimal sumbangan;
    private BigDecimal suratanTetap;
    private BigDecimal suratanHakmilikTetap;
    private BigDecimal suratanSementara;
    private BigDecimal pendaftaranTetap;
    private BigDecimal notis;
    private String amaunTuntutan0;
    private String amaunTuntutan1;
    private String amaunTuntutan2;
    private String amaunTuntutan3;
    private String amaunTuntutan4;
    private String amaunTuntutan5;
    private String amaunTuntutan6;
    private String amaunTuntutan7;
    private String amaunTuntutan8;
    private String amaunTuntutan9;
    private String jumlah;
    private boolean falg;
    private int i;
    private int  jumlah1;

    public long getJumlah1() {
        return jumlah1;
    }

    public void setJumlah1(int jumlah1) {
        this.jumlah1 = jumlah1;
    }
    private Date date;

//    @DefaultHandler
    public Resolution showForm() {
        return new JSP("pelupusan/penyediaan_borang5A.jsp").addParameter("tab", "true");
    }

     @DefaultHandler
    public Resolution showForm1() {
        return new JSP("pelupusan/Borang_5A.jsp").addParameter("tab", "true");
    }

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate()
    {
        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = permohonanDAO.findById(idPermohonan);
        permohonanTuntutanKoseslist = pelupusanService.findPermohonanTuntutanKos(idPermohonan);
         String [] bigDecimals = new String[10];
        Iterator iterator = permohonanTuntutanKoseslist.iterator();
        i=0;
        if(permohonanTuntutanKoseslist.size() > 0)
        {
            while (iterator.hasNext())
            {
                 p = (PermohonanTuntutanKos) iterator.next();
                 bigDecimals[i]=p.getAmaunTuntutan().toString();
                 i++;
            }
        }
        if(bigDecimals != null)
        {

            amaunTuntutan0=bigDecimals[0];
            amaunTuntutan1=bigDecimals[1];
            amaunTuntutan2=bigDecimals[2];
            amaunTuntutan3=bigDecimals[3];
            amaunTuntutan4=bigDecimals[4];
            amaunTuntutan5=bigDecimals[5];
            amaunTuntutan6=bigDecimals[6];
            amaunTuntutan7=bigDecimals[7];
            amaunTuntutan8=bigDecimals[8];
            amaunTuntutan9=bigDecimals[9];
        }
        i=0;
        jumlah1=0;
        while(bigDecimals != null)
        {
            jumlah1 =jumlah1+(Integer.parseInt(bigDecimals[i]));
            System.out.println("----------------------jumlah1---------"+jumlah1);
            i++;
            if(i==9)
                break;
        }
        jumlah = Integer.toString(jumlah1);
        amaunTuntutan = (String) getContext().getRequest().getSession().getAttribute("amaunTuntutan");
    }

    public Resolution simpan()
    {
        String[] itemList = {"Premiun PT 837 Nominal", "Premium PT 838 Nominal", "Upah Ukur/Tanda Sempadan", "Kos Sumbangan Infrastruktur",
            "Penyediaan Pelan Suratan Hakmilik Tetap", "Pendaftaran Suratan Hakmilik Tetap", "Penyediaan Pelan Suratan Hakmilik Sementara",
            "Pendaftaran Suratan Hakmilik Tetap", "Notis"};
        BigDecimal [] amaunTuntutanList = {cukai, primium, upah, sumbangan, suratanTetap, suratanHakmilikTetap, suratanSementara, pendaftaranTetap, notis};
        for (int i = 0; i < 9; i++) {
            System.out.println("itemList   :" + itemList[i]);
            System.out.println("amaunTuntutanList   :ss" + amaunTuntutanList[i]);
            etanahActionBeanContext ctx = new etanahActionBeanContext();
            ctx = (etanahActionBeanContext) getContext();
            Pengguna pengguna = ctx.getUser();
                KodCawangan caw = pengguna.getKodCawangan();
                InfoAudit ia = new InfoAudit();
                ia.setDimasukOleh(pengguna);
                ia.setTarikhMasuk(new java.util.Date());
                permohonantuntutkos = new PermohonanTuntutanKos();
                permohonantuntutkos.setInfoAudit(ia);
                permohonantuntutkos.setItem(itemList[i]);
                permohonantuntutkos.setAmaunTuntutan(amaunTuntutanList[i]);
                permohonantuntutkos.setPermohonan(permohonan);
                permohonantuntutkos.setCawangan(caw);
                pelupusanService.simpanPermohonanTuntutanKos(permohonantuntutkos);

        }
        jumlah = getContext().getRequest().getParameter("jumlah");
        System.out.println("jumlah2 : " +getContext().getRequest().getParameter("jumlah"));
        System.out.println("jumlah : "+ jumlah);
        addSimpleMessage("Maklumat Telah Berjaya Disimpan");
      return new JSP("pelupusan/penyediaan_borang5A.jsp").addParameter("tab", "true");

    }

    //add by sitifariza.hanim
        public Resolution simpan2()
        {
            pguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
            idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
            String idPemohon = getContext().getRequest().getParameter("pemohon.idPemohon");
            pemohon = pelupusanService.findPemohonByIdPemohon(idPermohonan);
            String[] itemList = {"Premiun PT 837 Nominal", "Premium PT 838 Nominal","Cukai Tahun Pertama PT 837","Cukai Tahun Pertama PT 838","Bayaran Wang Pelan","Bayaran Ukur","Tanda Sempadan","Penyediaan dan Pendaftaran Suratan Hakmilik Tetap","Penyediaan dan Pendaftaran Pelan Suratan Hakmilik Sementara","Pendaftaran Suratan Hakmilik Sementara"};
            BigDecimal [] amaunTuntutanList = {cukai, primium, upah, sumbangan, suratanTetap, suratanHakmilikTetap, suratanSementara, pendaftaranTetap, notis};
            jumlah1 =0;
            String [] bigDecimals = new String[10];
            bigDecimals[0]= getContext().getRequest().getParameter("amaunTuntutan0");
            bigDecimals[1]= getContext().getRequest().getParameter("amaunTuntutan1");
            bigDecimals[2]= getContext().getRequest().getParameter("amaunTuntutan2");
            bigDecimals[3]= getContext().getRequest().getParameter("amaunTuntutan3");
            bigDecimals[4]= getContext().getRequest().getParameter("amaunTuntutan4");
            bigDecimals[5]= getContext().getRequest().getParameter("amaunTuntutan5");
            bigDecimals[6]= getContext().getRequest().getParameter("amaunTuntutan6");
            bigDecimals[7]= getContext().getRequest().getParameter("amaunTuntutan7");
            bigDecimals[8]= getContext().getRequest().getParameter("amaunTuntutan8");
            bigDecimals[9]= getContext().getRequest().getParameter("amaunTuntutan9");

            getContext().getRequest().getSession().setAttribute("values", bigDecimals);
            int i=0;
            etanahActionBeanContext ctx = new etanahActionBeanContext();
            InfoAudit ia = new InfoAudit();
            permohonanTuntutanKoseslist = pelupusanService.findPermohonanTuntutanKos(idPermohonan);
            System.out.println("--------------------"+permohonanTuntutanKoseslist.size());
            if(permohonanTuntutanKoseslist == null)
            System.out.println("--------------------"+permohonanTuntutanKoseslist.size());
            if(permohonanTuntutanKoseslist.size() > 0)
            {
                System.out.println("--------if black-----");
                Iterator iterator = permohonanTuntutanKoseslist.iterator();
                while (iterator.hasNext())
                {
                    System.out.println("-------------iterator black------------------");
                    p = (PermohonanTuntutanKos) iterator.next();
                    p.getIdKos();
                    System.out.println("permohonantuntutkos.getIdKos();"+p.getIdKos());
                    ctx = (etanahActionBeanContext) getContext();
                    ia.setTarikhKemaskini(new java.util.Date());
                    ia.setDiKemaskiniOleh(pguna);
                    Pengguna pengguna = ctx.getUser();
                    KodCawangan caw = pengguna.getKodCawangan();
                    ia.setDimasukOleh(pengguna);
                    ia.setTarikhMasuk(new java.util.Date());
                    getContext().getRequest().getSession().setAttribute("date",new java.util.Date());
                    p.setInfoAudit(ia);
                    String item = itemList[i];
                    p.setItem(item);
                    if(bigDecimals[i] != null && bigDecimals[i] !="")
                    {
                        System.out.println("-------------iterator black-------bigDecimals[i] != -----------");
                         BigDecimal   b=new BigDecimal(bigDecimals[i]);
                         p.setAmaunTuntutan(b);
                    }else
                    {
//                    int aa =0;
//                    String a= new String();
//                    a= Integer.toString(aa);
                       double aa=0.0;
                       System.out.println("-------------iterator black-------bigDecimals[i] ==  null -----------");
                       BigDecimal b=new BigDecimal(aa);
                       p.setAmaunTuntutan(b);
                    }

                    p.setPermohonan(permohonan);
                    p.setCawangan(caw);
                  //  jumlah1+= Integer.parseInt(bigDecimals[i].trim());
                    pelupusanService.simpanPermohonanTuntutanKos(p);
                    System.out.println("--------saved---------------");
                    i++;
                }
            }
            else
            {
                System.out.println("------------else black---------------");
                while(i<10)
                {
                    System.out.println("------------else black1---------------");
                    if(bigDecimals[i] != null && bigDecimals[i] != "")
                    {
                        permohonantuntutkos = new PermohonanTuntutanKos();
                        permohonantuntutkos.getIdKos();
                        System.out.println("permohonantuntutkos.getIdKos();"+permohonantuntutkos.getIdKos());
                        ctx = (etanahActionBeanContext) getContext();
                        ia.setTarikhKemaskini(new java.util.Date());
                        ia.setDiKemaskiniOleh(pguna);
                        Pengguna pengguna = ctx.getUser();
                        KodCawangan caw = pengguna.getKodCawangan();
                        ia.setDimasukOleh(pengguna);
                        ia.setTarikhMasuk(new java.util.Date());
                        getContext().getRequest().getSession().setAttribute("date",new java.util.Date());
                        permohonantuntutkos.setInfoAudit(ia);
                        String item = itemList[i];
                        permohonantuntutkos.setItem(item);
                        BigDecimal b=new BigDecimal(bigDecimals[i]);
                        permohonantuntutkos.setAmaunTuntutan(b);
                        permohonantuntutkos.setPermohonan(permohonan);
                        permohonantuntutkos.setCawangan(caw);
                    //    jumlah1+= Integer.parseInt(bigDecimals[i].trim());
                        pelupusanService.simpanPermohonanTuntutanKos(permohonantuntutkos);
                        System.out.println("------------else black- saved--------------");
                    }
                i++;
            }
        }
        getContext().getRequest().getSession().setAttribute("jumlah1", jumlah1);
        rehydrate();
        return new JSP("pelupusan/Borang_5A.jsp").addParameter("tab", "true");
    }

        public Resolution SimpanEdit()
        {
            pguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
            idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
            String idPemohon = getContext().getRequest().getParameter("pemohon.idPemohon");
            String[] itemList = {"Premiun PT 837 Nominal", "Premium PT 838 Nominal","Cukai Tahun Pertama PT 837","Cukai Tahun Pertama PT 838","Bayaran Wang Pelan","Bayaran Ukur","Tanda Sempadan","Penyediaan dan Pendaftaran Suratan Hakmilik Tetap","Penyediaan dan Pendaftaran Pelan Suratan Hakmilik Sementara","Pendaftaran Suratan Hakmilik Sementara"};

            pemohon = pelupusanService.findPemohonByIdPemohon(idPermohonan);
            System.out.println("----------------SimpanEdit=========");
            permohonanTuntutanKoseslist = pelupusanService.findPermohonanTuntutanKos(idPermohonan);
            System.out.println("----------------SimpanEdit==1111111111=======");

            String item1 = getContext().getRequest().getParameter("item");
            getContext().getRequest().getSession().setAttribute("item", item1);
            String [] bigDecimals = new String[10];
            bigDecimals[0]= getContext().getRequest().getParameter("amaunTuntutan0");
            bigDecimals[1]= getContext().getRequest().getParameter("amaunTuntutan1");
            bigDecimals[2]= getContext().getRequest().getParameter("amaunTuntutan2");
            bigDecimals[3]= getContext().getRequest().getParameter("amaunTuntutan3");
            bigDecimals[4]= getContext().getRequest().getParameter("amaunTuntutan4");
            bigDecimals[5]= getContext().getRequest().getParameter("amaunTuntutan5");
            bigDecimals[6]= getContext().getRequest().getParameter("amaunTuntutan6");
            bigDecimals[7]= getContext().getRequest().getParameter("amaunTuntutan7");
            bigDecimals[8]= getContext().getRequest().getParameter("amaunTuntutan8");
            bigDecimals[9]= getContext().getRequest().getParameter("amaunTuntutan9");
            getContext().getRequest().getSession().setAttribute("values", bigDecimals);
            int i=0;
            etanahActionBeanContext ctx = new etanahActionBeanContext();
            InfoAudit ia = new InfoAudit();
            Iterator iterator =permohonanTuntutanKoseslist.iterator();
            int j=0;
            while (iterator.hasNext())
            {
                if(j<10)
                {
                p = (PermohonanTuntutanKos) iterator.next();
                ctx = (etanahActionBeanContext) getContext();
                ia.setTarikhKemaskini(new java.util.Date());
                ia.setDiKemaskiniOleh(pguna);
                Pengguna pengguna = ctx.getUser();
                KodCawangan caw = pengguna.getKodCawangan();
                ia.setDimasukOleh(pengguna);
                ia.setTarikhMasuk(new java.util.Date());
                p.setInfoAudit(ia);
                String item = itemList[i];
                p.setItem(item);
                BigDecimal b=new BigDecimal(bigDecimals[j]);
                p.setAmaunTuntutan(b);
                p.setPermohonan(permohonan);
                p.setCawangan(caw);
                pelupusanService.simpanPermohonanTuntutanKos(p);
                }
                j++;
            }

            jumlah = getContext().getRequest().getParameter("jumlah");
            getContext().getRequest().getSession().setAttribute("jumlah", jumlah);
            return new JSP("pelupusan/Borang_5AEdit.jsp").addParameter("tab", "true");
        }

    public PermohonanTuntutanKos getPermohonantuntutkos() {
        return permohonantuntutkos;
    }

    public void setPermohonantuntutkos(PermohonanTuntutanKos permohonantuntutkos) {
        this.permohonantuntutkos = permohonantuntutkos;
    }

    public String getIdPermohonan() {
        return idPermohonan;
    }

    public void setIdPermohonan(String idPermohonan) {
        this.idPermohonan = idPermohonan;
    }

    public Permohonan getPermohonan() {
        return permohonan;
    }

    public void setPermohonan(Permohonan permohonan) {
        this.permohonan = permohonan;
    }

    public BigDecimal getCukai() {
        return cukai;
    }

    public void setCukai(BigDecimal cukai) {
        this.cukai = cukai;
    }

    public BigDecimal getNotis() {
        return notis;
    }

    public void setNotis(BigDecimal notis) {
        this.notis = notis;
    }

    public BigDecimal getPendaftaranTetap() {
        return pendaftaranTetap;
    }

    public void setPendaftaranTetap(BigDecimal pendaftaranTetap) {
        this.pendaftaranTetap = pendaftaranTetap;
    }

    public BigDecimal getPrimium() {
        return primium;
    }

    public void setPrimium(BigDecimal primium) {
        this.primium = primium;
    }

    public BigDecimal getSumbangan() {
        return sumbangan;
    }

    public void setSumbangan(BigDecimal sumbangan) {
        this.sumbangan = sumbangan;
    }

    public BigDecimal getSuratanHakmilikTetap() {
        return suratanHakmilikTetap;
    }

    public void setSuratanHakmilikTetap(BigDecimal suratanHakmilikTetap) {
        this.suratanHakmilikTetap = suratanHakmilikTetap;
    }

    public BigDecimal getSuratanSementara() {
        return suratanSementara;
    }

    public void setSuratanSementara(BigDecimal suratanSementara) {
        this.suratanSementara = suratanSementara;
    }

    public BigDecimal getSuratanTetap() {
        return suratanTetap;
    }

    public void setSuratanTetap(BigDecimal suratanTetap) {
        this.suratanTetap = suratanTetap;
    }

    public BigDecimal getUpah() {
        return upah;
    }

    public void setUpah(BigDecimal upah) {
        this.upah = upah;
    }
      public Pemohon getPemohon() {
        return pemohon;
    }

    public void setPemohon(Pemohon pemohon) {
        this.pemohon = pemohon;
    }

    public Pengguna getPguna() {
        return pguna;
    }

    public void setPguna(Pengguna pguna) {
        this.pguna = pguna;
    }

    public PermohonanPihak getPermohonanPihak() {
        return permohonanPihak;
    }

    public void setPermohonanPihak(PermohonanPihak permohonanPihak) {
        this.permohonanPihak = permohonanPihak;
    }



    public List<PermohonanTuntutanKos> getPermohonanTuntutanKoseslist() {
        return permohonanTuntutanKoseslist;
    }

    public void setPermohonanTuntutanKoseslist(List<PermohonanTuntutanKos> permohonanTuntutanKoseslist) {
        this.permohonanTuntutanKoseslist = permohonanTuntutanKoseslist;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public PermohonanTuntutanKos getP() {
        return p;
    }

    public void setP(PermohonanTuntutanKos p) {
        this.p = p;
    }

    public boolean isFalg() {
        return falg;
    }

    public void setFalg(boolean falg) {
        this.falg = falg;
    }

    public String getItem() {
        return item;
    }


    public String getJumlah() {
        return jumlah;
    }

    public void setJumlah(String jumlah) {
        this.jumlah = jumlah;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public String getAmaunTuntutan() {
        return amaunTuntutan;
    }

    public void setAmaunTuntutan(String amaunTuntutan) {
        this.amaunTuntutan = amaunTuntutan;
    }

    public String getAmaunTuntutan0() {
        return amaunTuntutan0;
    }

    public void setAmaunTuntutan0(String amaunTuntutan0) {
        this.amaunTuntutan0 = amaunTuntutan0;
    }

    public String getAmaunTuntutan1() {
        return amaunTuntutan1;
    }

    public void setAmaunTuntutan1(String amaunTuntutan1) {
        this.amaunTuntutan1 = amaunTuntutan1;
    }

    public String getAmaunTuntutan2() {
        return amaunTuntutan2;
    }

    public void setAmaunTuntutan2(String amaunTuntutan2) {
        this.amaunTuntutan2 = amaunTuntutan2;
    }

    public String getAmaunTuntutan3() {
        return amaunTuntutan3;
    }

    public void setAmaunTuntutan3(String amaunTuntutan3) {
        this.amaunTuntutan3 = amaunTuntutan3;
    }

    public String getAmaunTuntutan4() {
        return amaunTuntutan4;
    }

    public void setAmaunTuntutan4(String amaunTuntutan4) {
        this.amaunTuntutan4 = amaunTuntutan4;
    }

    public String getAmaunTuntutan5() {
        return amaunTuntutan5;
    }

    public void setAmaunTuntutan5(String amaunTuntutan5) {
        this.amaunTuntutan5 = amaunTuntutan5;
    }

    public String getAmaunTuntutan6() {
        return amaunTuntutan6;
    }

    public void setAmaunTuntutan6(String amaunTuntutan6) {
        this.amaunTuntutan6 = amaunTuntutan6;
    }

    public String getAmaunTuntutan7() {
        return amaunTuntutan7;
    }

    public void setAmaunTuntutan7(String amaunTuntutan7) {
        this.amaunTuntutan7 = amaunTuntutan7;
    }

    public String getAmaunTuntutan8() {
        return amaunTuntutan8;
    }

    public void setAmaunTuntutan8(String amaunTuntutan8) {
        this.amaunTuntutan8 = amaunTuntutan8;
    }

    public String getAmaunTuntutan9() {
        return amaunTuntutan9;
    }

    public void setAmaunTuntutan9(String amaunTuntutan9) {
        this.amaunTuntutan9 = amaunTuntutan9;
    }
}