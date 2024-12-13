/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package etanah.view.stripes.hasil;

import able.stripes.AbleActionBean;
import com.google.inject.Inject;
import etanah.dao.AkaunDAO;
import etanah.dao.KodStatusTransaksiKewanganDAO;
import etanah.dao.KodTransaksiDAO;
import etanah.dao.hasil.TagAkaunDAO;
import etanah.model.Akaun;
import etanah.model.InfoAudit;
import etanah.model.KodStatusTransaksiKewangan;
import etanah.model.KodTransaksi;
import etanah.model.KumpulanAkaun;
import etanah.model.Pengguna;
import etanah.model.Transaksi;
import etanah.model.TransaksiHadapan;
import etanah.model.hasil.TagAkaun;
import etanah.model.hasil.TagAkaunAhli;
import etanah.view.etanahActionBeanContext;
import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.RedirectResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author abu.mansur
 */
@UrlBinding("/hasil/remisyenTanah")
public class RemisyenTanahActionBean extends AbleActionBean {

    private static final Logger LOG = Logger.getLogger(RemisyenTanahActionBean.class);

    private static String negeri;
    private static String idKumpulan;
    private static String namaKumpulan;
    private static String idHakmilik;
    private static String noAkaun;
    private String idKumpulanP;
    private String kodCawP;
    private String namaKumpulanP;
    private boolean show = false;
    private boolean showJana = false;
    private boolean showMsg = false;
    private String msg;
    private String peratusRemisyenTanah;
    private String peratusRemisyenTunggak;
    private String peratusRemisyenDenda;
    private String peratusRemisyenTunggakDenda;
    private String tahunRemisyen;
    private int tahunSkrg = 0;
    private List<TagAkaun> senaraiKumpulanAkaun;
    private List<Akaun> senaraiAkaun;

    private Pengguna peng;

    private static List<Akaun> senaraiAkaunStatic;

    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;
    @Inject
    etanah.Configuration conf;
    @Inject
    TagAkaunDAO tagAkaunDAO;
    @Inject
    AkaunDAO akaunDAO;
    @Inject
    KodTransaksiDAO kodTransaksiDAO;
    @Inject
    KodStatusTransaksiKewanganDAO kodStatusTransaksiKewanganDAO;
    @Inject
    etanah.kodHasilConfig khconf;
    @Inject
    RemisyenManager manager;

    SimpleDateFormat sdf = new SimpleDateFormat("yyyy");

    @DefaultHandler
    public Resolution showForm() {
        peng = (Pengguna) context.getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        if ("05".equals(conf.getProperty("kodNegeri"))) {negeri = "sembilan";}
        if ("04".equals(conf.getProperty("kodNegeri"))) {negeri = "melaka";}

        idKumpulan = null;
        namaKumpulan = null;
        idHakmilik = null;
        noAkaun = null;
        peratusRemisyenTanah = null;
        peratusRemisyenTunggak = null;
        peratusRemisyenDenda = null;
        peratusRemisyenTunggakDenda = null;
        
        kodCawP = peng.getKodCawangan().getKod();
        return new ForwardResolution("/WEB-INF/jsp/hasil/remisyen_tanah.jsp");
    }

    public Resolution popupSearchIdKumpulan(){
        peng = (Pengguna) context.getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        kodCawP = peng.getKodCawangan().getKod();
        return new ForwardResolution("/WEB-INF/jsp/hasil/remisyen_tanah_popup.jsp").addParameter("popup", "true");
    }

    public Resolution search(){
        if("05".equals(conf.getProperty("kodNegeri")))
            negeri = "sembilan";
        if("04".equals(conf.getProperty("kodNegeri")))
            negeri = "melaka";
        LOG.info("negeri :"+negeri);

        tahunSkrg = Integer.parseInt(sdf.format(new java.util.Date()));
//        senaraiAkaun = new ArrayList<Akaun>();
        senaraiAkaunStatic = new ArrayList<Akaun>();
        if(idKumpulan != null && (idHakmilik == null || noAkaun == null)){
            LOG.info("carian Kumpulan");
            cariKumpulan(idKumpulan);
            senaraiAkaunStatic = senaraiAkaun;
        }else if(idKumpulan == null && (idHakmilik != null || noAkaun != null)){
            LOG.info("carian individu");
            cariIndividu(idHakmilik, noAkaun);
            senaraiAkaunStatic = senaraiAkaun;
        }else if(idKumpulan == null && idHakmilik == null && noAkaun == null){
            LOG.error("Tiada maklumat untuk dicari.");
            addSimpleError("Tiada maklumat untuk dicari. Sila isi maklumat berkenaan.");
        }else{
            LOG.error("Dilarang mengisi kedua2 kategori carian.");
            addSimpleError("Tidak dibenarkan membuat carian 'ID Kumpulan' dan 'ID Hakmilik' atau 'No. Akaun' serentak.");
        }

        int flag = 0;
        for (Akaun ak : senaraiAkaun) {
            for (Transaksi tran : ak.getSemuaTransaksi()) {
                LOG.info("idTran :"+tran.getIdTransaksi());
                if(tran.getKodTransaksi().getKod().equals(khconf.getProperty("remisyenTanah")) || tran.getKodTransaksi().getKod().equals(khconf.getProperty("remisyenTunggak")) 
                        || tran.getKodTransaksi().getKod().equals(khconf.getProperty("remisyenDenda")) || tran.getKodTransaksi().getKod().equals(khconf.getProperty("remisyenTunggakDenda"))){ // kod for remisyen, 99030 =  tanah, 99051 = tunggakan, 99050 = denda lewat, 99052 = tunggakan denda lewat
                    flag += 1;
                }
            }
        }
        LOG.info("senaraiAkaun.size :"+senaraiAkaun.size());
        LOG.info("flag :"+flag);
        if(senaraiAkaun.size() != 0){
            if(flag > 0){
                showJana = false; // Xpapar field remisyen
                showMsg = true;
                msg = "Terdapat Hakmilik Yang TELAH Dikenakan Remisyen Didalam Kumpulan :"+namaKumpulan+" .";
            }else
                showJana = true; // papar field remisyen
        }
        show = true;
        return new ForwardResolution("/WEB-INF/jsp/hasil/remisyen_tanah.jsp");
    }

    private void cariKumpulan(String kumpulan){
        LOG.info("kumpulan :"+kumpulan);
        senaraiAkaun = new ArrayList<Akaun>();
        try{
            TagAkaun tagAkaun = tagAkaunDAO.findById(kumpulan);
            for (TagAkaunAhli tagAhli : tagAkaun.getSenaraiAhli()) {
                senaraiAkaun.add(tagAhli.getAkaun());
                LOG.debug("idAhli :"+tagAhli.getIdAhli());
            }
            namaKumpulan = tagAkaun.getNama();
            LOG.debug("nama kumpulan :"+namaKumpulan);
        }catch(Exception ex){
            LOG.error("ID Kumpulan salah.");
            ex.printStackTrace(); // for development only
        }
    }

    private void cariIndividu(String hakmilik, String akaun){
        senaraiAkaun = new ArrayList<Akaun>();
        Session ses = sessionProvider.get();
        String sql = "select a from etanah.model.Akaun a where 1=1 ";
            if(idHakmilik != null)
                sql += "and a.hakmilik.idHakmilik like :hakmilik ";
            if(noAkaun != null)
                sql += "and a.noAkaun like :akaun ";
        LOG.info("sql :"+sql);
        Query q = ses.createQuery(sql);
            if(idHakmilik != null)
                q.setString("hakmilik", "%"+hakmilik+"%");
            if(noAkaun != null)
                q.setString("akaun", "%"+akaun+"%");
        senaraiAkaun = q.list();
    }

    public Resolution cariIdKumpulan(){
        senaraiKumpulanAkaun = new ArrayList<TagAkaun>();
        Session s = sessionProvider.get();
        String sql = "select ta from etanah.model.hasil.TagAkaun ta where 1=1 ";
            if(idKumpulanP != null)
                sql += "and ta.idTag like :idKump ";
            if(kodCawP != null)
                sql += "and ta.cawangan.kod = :kodCaw ";
            if(namaKumpulanP != null)
                sql += "and ta.nama like :namaKump ";
        LOG.info("sql :"+sql);
        Query q = s.createQuery(sql);
            if(idKumpulanP != null)
                q.setString("idKump", "%"+idKumpulanP+"%");
            if(kodCawP != null)
                q.setString("kodCaw", kodCawP);
            if(namaKumpulanP != null)
                q.setString("namaKump", "%"+namaKumpulanP+"%");

        senaraiKumpulanAkaun = q.list();
        LOG.debug("senaraiKumpulanAkaun.size() :"+senaraiKumpulanAkaun.size());
        return new ForwardResolution("/WEB-INF/jsp/hasil/remisyen_tanah_popup.jsp").addParameter("popup", "true");
    }

    public Resolution refreshIdKumpulan(){
        search();
        return new ForwardResolution("/WEB-INF/jsp/hasil/remisyen_tanah.jsp");
    }

    public Resolution simpanRemisyen(){
        String kumpulanNama = namaKumpulan;
        String kumpulanId = idKumpulan;
        peng = (Pengguna) context.getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        InfoAudit ia = new InfoAudit();
        InfoAudit iaUpdate = new InfoAudit();
        Date now = new Date();
        LOG.info("senaraiAkaunStatic.size :"+senaraiAkaunStatic.size());
        senaraiAkaun = senaraiAkaunStatic;
//        LOG.info("senaraiAkaun.size :"+senaraiAkaun.size());
//        LOG.info("peratusRemisyen :"+peratusRemisyenTanah);
//        LOG.info("tahunRemisyen :"+tahunRemisyen);
        if((peratusRemisyenTanah != null || peratusRemisyenTunggak != null || peratusRemisyenDenda != null || peratusRemisyenTunggakDenda != null) && tahunRemisyen != null){
            String[] name = {"kodAkaun.kod"};
            Object[] value = {khconf.getProperty("akaunRemisyen")};
            List<Akaun> senaraiAR = akaunDAO.findByEqualCriterias(name, value, null);
            Akaun akaunAR = new Akaun();
            for (Akaun akaunR : senaraiAR) {
                LOG.debug("akaunR :"+akaunR.getNoAkaun());
                if(akaunR.getCawangan().getKod().equals(peng.getKodCawangan().getKod())){
                    akaunAR = akaunR;
                    LOG.debug("akaunRemisyen :"+akaunAR.getNoAkaun());
                }
            }
            LOG.debug("222 akaunRemisyen :"+akaunAR.getNoAkaun());
            if(akaunAR == null){
                addSimpleError("Akaun Remisyen untuk cawangan ini tidak dijumpai.");
                LOG.error("Akaun Remisyen tidak dijumpai.");
            }else{
                try{
                /***** for Remisyen Tanah only *****/
                if(peratusRemisyenTanah != null){
                    ia.setDimasukOleh(peng);
                    ia.setTarikhMasuk(now);
                    KodTransaksi kodTransRT = kodTransaksiDAO.findById(khconf.getProperty("remisyenTanah")); //kod for Remisyen Tanah = 99030
                    KodStatusTransaksiKewangan status = kodStatusTransaksiKewanganDAO.findById('A'); // A = Aktif
                    int tahun = Integer.parseInt(sdf.format(now));
                    BigDecimal decimalRemisyen = new BigDecimal(peratusRemisyenTanah).divide(new BigDecimal(100));
                    LOG.debug("decimalRemisyen :"+decimalRemisyen);
                    int kaliRemisyen = Integer.parseInt(tahunRemisyen);
                    try{
                        Akaun akaunUpdate = new Akaun();
                        for (Akaun akaun : senaraiAkaun) {

                            BigDecimal amaunCukaiSemasa = new BigDecimal(0.00);
                            for (Transaksi transDebit : akaun.getSenaraiTransaksiDebit()) {
                                if(transDebit.getKodTransaksi().getKod().equals(khconf.getProperty("cukaiTanahSemasa")))
                                    amaunCukaiSemasa = amaunCukaiSemasa.add(transDebit.getAmaun());
                            }
                            LOG.debug("amaunCukaiSemasa :"+amaunCukaiSemasa);

                            BigDecimal remisyen = decimalRemisyen.multiply(amaunCukaiSemasa);
                            LOG.info("remisyen before roundDown :"+remisyen);
                            remisyen = remisyen.setScale(0, BigDecimal.ROUND_DOWN);
                            LOG.debug("remisyen after roundDown :"+remisyen);
                            akaunUpdate = akaun;
                            //save new Transaksi
                            Transaksi trans = new Transaksi();
                            trans.setAkaunDebit(akaunAR);
                            trans.setAkaunKredit(akaunUpdate);
                            trans.setAmaun(remisyen);
                            trans.setCawangan(peng.getKodCawangan());
                            trans.setKodTransaksi(kodTransRT);
                            trans.setKuantiti(1);
                            trans.setUntukTahun(tahun);
                            trans.setTahunKewangan(tahun);
                            trans.setStatus(status);
                            trans.setBayaranAgensi("N");
                            trans.setInfoAudit(ia);
    //                        manager.saveTransaksi(trans);
                            //update akaun
                            iaUpdate = akaunUpdate.getInfoAudit();
                            iaUpdate.setDiKemaskiniOleh(peng);
                            iaUpdate.setTarikhKemaskini(now);
                            akaunUpdate.setBaki(akaunUpdate.getBaki().subtract(remisyen));
                            akaunUpdate.setInfoAudit(iaUpdate);
    //                        manager.updateAkaun(akaunUpdate);

                            manager.saveAndUpdateTransaksiAndAkaun(trans, akaunUpdate);

                            //untuk transaksi hadapan
                            if(kaliRemisyen-1 != 0){
                                int bilRemisyen = kaliRemisyen - 1;
                                LOG.info("bilRemisyen :"+bilRemisyen);
                                for(int count = 1; count <= bilRemisyen; count++){
                                    LOG.info("bil. transaksi hadapan :"+count);
                                    TransaksiHadapan transDepan = new TransaksiHadapan();
                                    transDepan.setAkaunDebit(akaunAR);
                                    transDepan.setAkaunKredit(akaunUpdate);
                                    transDepan.setAmaun(remisyen);
                                    transDepan.setCawangan(peng.getKodCawangan());
                                    transDepan.setKodTransaksi(kodTransRT);
                                    transDepan.setKuantiti(1);
                                    transDepan.setUntukTahun(tahun+count);
                                    LOG.info("tahun Remisyen :"+transDepan.getUntukTahun());
                                    transDepan.setStatus(status);
                                    transDepan.setInfoAudit(ia);
                                    manager.saveTransaksiHadapan(transDepan, peng);
                                }
                            }
                        }
//                        addSimpleMessage("Maklumat BERJAYA disimpan.");
                        LOG.error("Transaksi Berjaya.");
                    }catch(Exception ex){
//                        addSimpleError("Maklumat TIDAK berjaya disimpan.");
                        LOG.error("Terdapat masalah pada transaksi. :"+ex);
                        ex.printStackTrace(); // for development only
                    }
                }
                /***** for Remisyen Tunggakan *****/
                if(peratusRemisyenTunggak != null){
                    ia.setDimasukOleh(peng);
                    ia.setTarikhMasuk(now);
                    KodTransaksi kodTransRT = kodTransaksiDAO.findById(khconf.getProperty("remisyenTunggak")); //kod for Remisyen Tunggakan = 99051
                    KodStatusTransaksiKewangan status = kodStatusTransaksiKewanganDAO.findById('A'); // A = Aktif
                    int tahun = Integer.parseInt(sdf.format(now));
                    BigDecimal decimalRemisyen = new BigDecimal(peratusRemisyenTunggak).divide(new BigDecimal(100));
                    LOG.debug("decimalRemisyen :"+decimalRemisyen);
                    int kaliRemisyen = Integer.parseInt(tahunRemisyen);
                    try{
                        Akaun akaunUpdate = new Akaun();
                        for (Akaun akaun : senaraiAkaun) {

                            BigDecimal amaunTunggakan = new BigDecimal(0.00);
                            for (Transaksi transDebit : akaun.getSenaraiTransaksiDebit()) {
                                if(transDebit.getKodTransaksi().getKod().equals(khconf.getProperty("cukaiTanahTunggakan")))
                                    amaunTunggakan = amaunTunggakan.add(transDebit.getAmaun());
                            }
                            LOG.debug("amaunTunggakan :"+amaunTunggakan);

                            BigDecimal remisyen = decimalRemisyen.multiply(amaunTunggakan);
                            LOG.info("remisyen before roundDown :"+remisyen);
                            remisyen = remisyen.setScale(0, BigDecimal.ROUND_DOWN);
                            LOG.debug("remisyen after roundDown :"+remisyen);
                            akaunUpdate = akaun;
                            //save new Transaksi
                            Transaksi trans = new Transaksi();
                            trans.setAkaunDebit(akaunAR);
                            trans.setAkaunKredit(akaunUpdate);
                            trans.setAmaun(remisyen);
                            trans.setCawangan(peng.getKodCawangan());
                            trans.setKodTransaksi(kodTransRT);
                            trans.setKuantiti(1);
                            trans.setUntukTahun(tahun);
                            trans.setTahunKewangan(tahun);
                            trans.setStatus(status);
                            trans.setBayaranAgensi("N");
                            trans.setInfoAudit(ia);
    //                        manager.saveTransaksi(trans);
                            //update akaun
                            iaUpdate = akaunUpdate.getInfoAudit();
                            iaUpdate.setDiKemaskiniOleh(peng);
                            iaUpdate.setTarikhKemaskini(now);
                            akaunUpdate.setBaki(akaunUpdate.getBaki().subtract(remisyen));
                            akaunUpdate.setInfoAudit(iaUpdate);
    //                        manager.updateAkaun(akaunUpdate);

                            manager.saveAndUpdateTransaksiAndAkaun(trans, akaunUpdate);

                            //untuk transaksi hadapan
                            if(kaliRemisyen-1 != 0){
                                int bilRemisyen = kaliRemisyen - 1;
                                LOG.info("bilRemisyen :"+bilRemisyen);
                                for(int count = 1; count <= bilRemisyen; count++){
                                    LOG.info("bil. transaksi hadapan :"+count);
                                    TransaksiHadapan transDepan = new TransaksiHadapan();
                                    transDepan.setAkaunDebit(akaunAR);
                                    transDepan.setAkaunKredit(akaunUpdate);
                                    transDepan.setAmaun(remisyen);
                                    transDepan.setCawangan(peng.getKodCawangan());
                                    transDepan.setKodTransaksi(kodTransRT);
                                    transDepan.setKuantiti(1);
                                    transDepan.setUntukTahun(tahun+count);
                                    LOG.info("tahun Remisyen :"+transDepan.getUntukTahun());
                                    transDepan.setStatus(status);
                                    transDepan.setInfoAudit(ia);
                                    manager.saveTransaksiHadapan(transDepan, peng);
                                }
                            }
                        }
//                        addSimpleMessage("Maklumat BERJAYA disimpan.");
                        LOG.error("Transaksi Berjaya.");
                    }catch(Exception ex){
//                        addSimpleError("Maklumat TIDAK berjaya disimpan.");
                        LOG.error("Terdapat masalah pada transaksi. :"+ex);
                        ex.printStackTrace(); // for development only
                    }
                }
                /***** for Remisyen Denda Lewat *****/
                if(peratusRemisyenDenda != null){
                    ia.setDimasukOleh(peng);
                    ia.setTarikhMasuk(now);
                    KodTransaksi kodTransRT = kodTransaksiDAO.findById(khconf.getProperty("remisyenDenda")); //kod for Remisyen Denda Lewat = 99050
                    KodStatusTransaksiKewangan status = kodStatusTransaksiKewanganDAO.findById('A'); // A = Aktif
                    int tahun = Integer.parseInt(sdf.format(now));
                    BigDecimal decimalRemisyen = new BigDecimal(peratusRemisyenDenda).divide(new BigDecimal(100));
                    LOG.debug("decimalRemisyen :"+decimalRemisyen);
                    int kaliRemisyen = Integer.parseInt(tahunRemisyen);
                    try{
                        Akaun akaunUpdate = new Akaun();
                        for (Akaun akaun : senaraiAkaun) {

                            BigDecimal amaunDenda = new BigDecimal(0.00);
                            for (Transaksi transDebit : akaun.getSenaraiTransaksiDebit()) {
                                if((transDebit.getKodTransaksi().getKod().equals(khconf.getProperty("dendaLewat")))&&(transDebit.getUntukTahun()==tahun))
                                    amaunDenda = amaunDenda.add(transDebit.getAmaun());
                            }
                            LOG.debug("amaunDendaLewat :"+amaunDenda);

                            BigDecimal remisyen = decimalRemisyen.multiply(amaunDenda);
                            LOG.info("remisyen before roundDown :"+remisyen);
                            remisyen = remisyen.setScale(0, BigDecimal.ROUND_DOWN);
                            LOG.debug("remisyen after roundDown :"+remisyen);
                            akaunUpdate = akaun;
                            //save new Transaksi
                            Transaksi trans = new Transaksi();
                            trans.setAkaunDebit(akaunAR);
                            trans.setAkaunKredit(akaunUpdate);
                            trans.setAmaun(remisyen);
                            trans.setCawangan(peng.getKodCawangan());
                            trans.setKodTransaksi(kodTransRT);
                            trans.setKuantiti(1);
                            trans.setUntukTahun(tahun);
                            trans.setTahunKewangan(tahun);
                            trans.setStatus(status);
                            trans.setBayaranAgensi("N");
                            trans.setInfoAudit(ia);
    //                        manager.saveTransaksi(trans);
                            //update akaun
                            iaUpdate = akaunUpdate.getInfoAudit();
                            iaUpdate.setDiKemaskiniOleh(peng);
                            iaUpdate.setTarikhKemaskini(now);
                            akaunUpdate.setBaki(akaunUpdate.getBaki().subtract(remisyen));
                            akaunUpdate.setInfoAudit(iaUpdate);
    //                        manager.updateAkaun(akaunUpdate);

                            manager.saveAndUpdateTransaksiAndAkaun(trans, akaunUpdate);

                            //untuk transaksi hadapan
                            if(kaliRemisyen-1 != 0){
                                int bilRemisyen = kaliRemisyen - 1;
                                LOG.info("bilRemisyen :"+bilRemisyen);
                                for(int count = 1; count <= bilRemisyen; count++){
                                    LOG.info("bil. transaksi hadapan :"+count);
                                    TransaksiHadapan transDepan = new TransaksiHadapan();
                                    transDepan.setAkaunDebit(akaunAR);
                                    transDepan.setAkaunKredit(akaunUpdate);
                                    transDepan.setAmaun(remisyen);
                                    transDepan.setCawangan(peng.getKodCawangan());
                                    transDepan.setKodTransaksi(kodTransRT);
                                    transDepan.setKuantiti(1);
                                    transDepan.setUntukTahun(tahun+count);
                                    LOG.info("tahun Remisyen :"+transDepan.getUntukTahun());
                                    transDepan.setStatus(status);
                                    transDepan.setInfoAudit(ia);
                                    manager.saveTransaksiHadapan(transDepan, peng);
                                }
                            }
                        }
//                        addSimpleMessage("Maklumat BERJAYA disimpan.");
                        LOG.error("Transaksi Berjaya.");
                    }catch(Exception ex){                        
//                        addSimpleError("Maklumat TIDAK berjaya disimpan.");
                        LOG.error("Terdapat masalah pada transaksi. :"+ex);
                        ex.printStackTrace(); // for development only
                    }
                }
                /***** for Remisyen Tunggakan Denda Lewat *****/
                if(peratusRemisyenTunggakDenda != null){
                    ia.setDimasukOleh(peng);
                    ia.setTarikhMasuk(now);
                    KodTransaksi kodTransRT = kodTransaksiDAO.findById(khconf.getProperty("remisyenTunggakDenda")); //kod for Remisyen Tunggakan Denda Lewat = 99052
                    KodStatusTransaksiKewangan status = kodStatusTransaksiKewanganDAO.findById('A'); // A = Aktif
                    int tahun = Integer.parseInt(sdf.format(now));
                    BigDecimal decimalRemisyen = new BigDecimal(peratusRemisyenTunggakDenda).divide(new BigDecimal(100));
                    LOG.debug("decimalRemisyen :"+decimalRemisyen);
                    int kaliRemisyen = Integer.parseInt(tahunRemisyen);
                    try{
                        Akaun akaunUpdate = new Akaun();
                        for (Akaun akaun : senaraiAkaun) {

                            BigDecimal amaunDenda = new BigDecimal(0.00);
                            for (Transaksi transDebit : akaun.getSenaraiTransaksiDebit()) {
                                if((transDebit.getKodTransaksi().getKod().equals(khconf.getProperty("dendaLewat")))&&(transDebit.getUntukTahun()!=tahun))
                                    amaunDenda = amaunDenda.add(transDebit.getAmaun());
                            }
                            LOG.debug("amaunDendaLewat :"+amaunDenda);

                            BigDecimal remisyen = decimalRemisyen.multiply(amaunDenda);
                            LOG.info("remisyen before roundDown :"+remisyen);
                            remisyen = remisyen.setScale(0, BigDecimal.ROUND_DOWN);
                            LOG.debug("remisyen after roundDown :"+remisyen);
                            akaunUpdate = akaun;
                            //save new Transaksi
                            Transaksi trans = new Transaksi();
                            trans.setAkaunDebit(akaunAR);
                            trans.setAkaunKredit(akaunUpdate);
                            trans.setAmaun(remisyen);
                            trans.setCawangan(peng.getKodCawangan());
                            trans.setKodTransaksi(kodTransRT);
                            trans.setKuantiti(1);
                            trans.setUntukTahun(tahun);
                            trans.setTahunKewangan(tahun);
                            trans.setStatus(status);
                            trans.setBayaranAgensi("N");
                            trans.setInfoAudit(ia);
    //                        manager.saveTransaksi(trans);
                            //update akaun
                            iaUpdate = akaunUpdate.getInfoAudit();
                            iaUpdate.setDiKemaskiniOleh(peng);
                            iaUpdate.setTarikhKemaskini(now);
                            akaunUpdate.setBaki(akaunUpdate.getBaki().subtract(remisyen));
                            akaunUpdate.setInfoAudit(iaUpdate);
    //                        manager.updateAkaun(akaunUpdate);

                            manager.saveAndUpdateTransaksiAndAkaun(trans, akaunUpdate);

                            //untuk transaksi hadapan
                            if(kaliRemisyen-1 != 0){
                                int bilRemisyen = kaliRemisyen - 1;
                                LOG.info("bilRemisyen :"+bilRemisyen);
                                for(int count = 1; count <= bilRemisyen; count++){
                                    LOG.info("bil. transaksi hadapan :"+count);
                                    TransaksiHadapan transDepan = new TransaksiHadapan();
                                    transDepan.setAkaunDebit(akaunAR);
                                    transDepan.setAkaunKredit(akaunUpdate);
                                    transDepan.setAmaun(remisyen);
                                    transDepan.setCawangan(peng.getKodCawangan());
                                    transDepan.setKodTransaksi(kodTransRT);
                                    transDepan.setKuantiti(1);
                                    transDepan.setUntukTahun(tahun+count);
                                    LOG.info("tahun Remisyen :"+transDepan.getUntukTahun());
                                    transDepan.setStatus(status);
                                    transDepan.setInfoAudit(ia);
                                    manager.saveTransaksiHadapan(transDepan, peng);
                                }
                            }
                        }
//                        addSimpleMessage("Maklumat BERJAYA disimpan.");
                        LOG.error("Transaksi Berjaya.");
                    }catch(Exception ex){
//                        addSimpleError("Maklumat TIDAK berjaya disimpan.");
                        LOG.error("Terdapat masalah pada transaksi. :"+ex);
                        ex.printStackTrace(); // for development only
                    }
                }
                addSimpleMessage("Maklumat BERJAYA disimpan.");
            }catch(Exception ex){
                addSimpleError("Maklumat TIDAK berjaya disimpan.");
            }
            }
            idKumpulan = kumpulanId;
            namaKumpulan = kumpulanNama;

//            refreshSearch();
//            search();
//            searching();
        }else{
            addSimpleError("Sila isi ruangan yang bertanda ( * ).");
            LOG.error("peratusRemisyenTanah :"+peratusRemisyenTanah+", peratusRemisyenTunggak :"+peratusRemisyenTunggak
                    +", peratusRemisyenDenda :"+peratusRemisyenDenda
                    +", peratusRemisyenTunggakDenda :"+peratusRemisyenTunggakDenda
                    +", tahunRemisyen :"+tahunRemisyen);
            idKumpulan = kumpulanId;
            namaKumpulan = kumpulanNama;
            show = true;
            showJana = true;
        }
//        refreshSearch();
//        return new ForwardResolution("/WEB-INF/jsp/hasil/remisyen_tanah_1.jsp");
        return new RedirectResolution(ViewRemisyenActionBean.class, "testingSearch").addParameter("noAkaun", noAkaun)
                .addParameter("hakmilik", idHakmilik).addParameter("kumpulan", idKumpulan)
                .addParameter("remTanah", peratusRemisyenTanah).addParameter("remTunggak", peratusRemisyenTunggak)
                .addParameter("remDenda", peratusRemisyenDenda).addParameter("remTunggakDenda", peratusRemisyenTunggakDenda);
    }

    private void refreshSearch(){
        senaraiAkaun = new ArrayList<Akaun>();

        tahunSkrg = Integer.parseInt(sdf.format(new java.util.Date()));
        if(idKumpulan != null && (idHakmilik == null || noAkaun == null)){
            LOG.info("carian Kumpulan");
            TagAkaun tagAkaun = tagAkaunDAO.findById(idKumpulan);
            for (TagAkaunAhli tagAhli : tagAkaun.getSenaraiAhli()) {

                senaraiAkaun.add(tagAhli.getAkaun());
                LOG.debug("(refreshSearch) idAhli :"+tagAhli.getIdAhli());
            }
        }else if(idKumpulan == null && (idHakmilik != null || noAkaun != null)){
            LOG.info("carian individu");
            Session ses = sessionProvider.get();
            String sql = "select a from etanah.model.Akaun a where 1=1 ";
                if(idHakmilik != null)
                    sql += "and a.hakmilik.idHakmilik like :hakmilik ";
                if(noAkaun != null)
                    sql += "and a.noAkaun like :akaun ";
            LOG.info("sql :"+sql);
            Query q = ses.createQuery(sql);
                if(idHakmilik != null)
                    q.setString("hakmilik", "%"+idHakmilik+"%");
                if(noAkaun != null)
                    q.setString("akaun", "%"+noAkaun+"%");
            senaraiAkaun = q.list();
        }

        int flag = 0;
        for (Akaun ak : senaraiAkaun) {
            for (Transaksi tran : ak.getSemuaTransaksi()) {
                LOG.info("idTran :"+tran.getIdTransaksi());
                if(tran.getKodTransaksi().getKod().equals(khconf.getProperty("remisyenTanah")) || tran.getKodTransaksi().getKod().equals(khconf.getProperty("remisyenTunggak")) || tran.getKodTransaksi().getKod().equals(khconf.getProperty("remisyenDenda"))){ // kod for remisyen tanah
                    flag += 1;
                }
            }
        }
        LOG.info("flag :"+flag);
        if(flag > 0)
            showJana = false; // papar field remisyen
        else
            showJana = true; // papar field remisyen
        show = true;

        LOG.info("(refreshSearch) senaraiAkaun.size :"+senaraiAkaun.size());
    }

    public String getNegeri() {
        return negeri;
    }

    public void setNegeri(String negeri) {
        this.negeri = negeri;
    }

    public String getIdHakmilik() {
        return idHakmilik;
    }

    public void setIdHakmilik(String idHakmilik) {
        this.idHakmilik = idHakmilik;
    }

    public String getIdKumpulan() {
        return idKumpulan;
    }

    public void setIdKumpulan(String idKumpulan) {
        this.idKumpulan = idKumpulan;
    }

    public String getNoAkaun() {
        return noAkaun;
    }

    public void setNoAkaun(String noAkaun) {
        this.noAkaun = noAkaun;
    }

    public String getIdKumpulanP() {
        return idKumpulanP;
    }

    public void setIdKumpulanP(String idKumpulanP) {
        this.idKumpulanP = idKumpulanP;
    }

    public String getKodCawP() {
        return kodCawP;
    }

    public void setKodCawP(String kodCawP) {
        this.kodCawP = kodCawP;
    }

    public String getNamaKumpulanP() {
        return namaKumpulanP;
    }

    public void setNamaKumpulanP(String namaKumpulanP) {
        this.namaKumpulanP = namaKumpulanP;
    }

    public boolean isShow() {
        return show;
    }

    public void setShow(boolean show) {
        this.show = show;
    }

    public boolean isShowJana() {
        return showJana;
    }

    public void setShowJana(boolean showJana) {
        this.showJana = showJana;
    }

    public String getPeratusRemisyenTanah() {
        return peratusRemisyenTanah;
    }

    public void setPeratusRemisyenTanah(String peratusRemisyenTanah) {
        this.peratusRemisyenTanah = peratusRemisyenTanah;
    }

    public String getTahunRemisyen() {
        return tahunRemisyen;
    }

    public void setTahunRemisyen(String tahunRemisyen) {
        this.tahunRemisyen = tahunRemisyen;
    }

    public int getTahunSkrg() {
        return tahunSkrg;
    }

    public void setTahunSkrg(int tahunSkrg) {
        this.tahunSkrg = tahunSkrg;
    }

    public List<TagAkaun> getSenaraiKumpulanAkaun() {
        return senaraiKumpulanAkaun;
    }

    public void setSenaraiKumpulanAkaun(List<TagAkaun> senaraiKumpulanAkaun) {
        this.senaraiKumpulanAkaun = senaraiKumpulanAkaun;
    }

    public List<Akaun> getSenaraiAkaun() {
        return senaraiAkaun;
    }

    public void setSenaraiAkaun(List<Akaun> senaraiAkaun) {
        this.senaraiAkaun = senaraiAkaun;
    }

    public static List<Akaun> getSenaraiAkaunStatic() {
        return senaraiAkaunStatic;
    }

    public static void setSenaraiAkaunStatic(List<Akaun> senaraiAkaunStatic) {
        RemisyenTanahActionBean.senaraiAkaunStatic = senaraiAkaunStatic;
    }

    public Pengguna getPeng() {
        return peng;
    }

    public void setPeng(Pengguna peng) {
        this.peng = peng;
    }

    public String getNamaKumpulan() {
        return namaKumpulan;
    }

    public void setNamaKumpulan(String namaKumpulan) {
        this.namaKumpulan = namaKumpulan;
    }

    public String getPeratusRemisyenDenda() {
        return peratusRemisyenDenda;
    }

    public void setPeratusRemisyenDenda(String peratusRemisyenDenda) {
        this.peratusRemisyenDenda = peratusRemisyenDenda;
    }

    public String getPeratusRemisyenTunggak() {
        return peratusRemisyenTunggak;
    }

    public void setPeratusRemisyenTunggak(String peratusRemisyenTunggak) {
        this.peratusRemisyenTunggak = peratusRemisyenTunggak;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public boolean isShowMsg() {
        return showMsg;
    }

    public void setShowMsg(boolean showMsg) {
        this.showMsg = showMsg;
    }

    public String getPeratusRemisyenTunggakDenda() {
        return peratusRemisyenTunggakDenda;
    }

    public void setPeratusRemisyenTunggakDenda(String peratusRemisyenTunggakDenda) {
        this.peratusRemisyenTunggakDenda = peratusRemisyenTunggakDenda;
    }

}
