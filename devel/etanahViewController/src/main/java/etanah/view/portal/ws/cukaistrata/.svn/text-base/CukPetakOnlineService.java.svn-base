/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.portal.ws.cukaistrata;

import com.google.inject.Injector;
import etanah.model.Akaun;
import etanah.model.Hakmilik;
import etanah.model.HakmilikSebelum;
import etanah.model.SejarahDokumenKewangan;
import etanah.model.SejarahDokumenKewanganBayaran;
import etanah.model.Transaksi;
import etanah.view.etanahContextListener;
import etanah.view.portal.ws.form.SejarahCukai;
import etanah.view.portal.ws.form.CukaiStrataForm;
import etanah.view.portal.ws.form.HakmlikInfo;
import etanah.view.portal.ws.service.UtilCarianService;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.apache.commons.lang.StringUtils;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author mohd.faidzal
 */
public class CukPetakOnlineService {

    private static Injector injector = etanahContextListener.getInjector();

    UtilCarianService utilCarianService = injector.getInstance(UtilCarianService.class);

    public List<CukaiStrataForm> checkAccountStrataByParam(String daerah, String bpm, String seksyen, String kodJenishakmilik, String noHakmilik, String noBangunan, String noTingkat, String noPetak) {
        NumberFormat df = NumberFormat.getInstance();
        df.setMaximumIntegerDigits(8);
        df.setMinimumIntegerDigits(8);
        df.setGroupingUsed(false);
        Hakmilik ha = utilCarianService.checkAccountStrataByParam(daerah, bpm, seksyen, kodJenishakmilik, df.format(Integer.parseInt(noHakmilik)), noBangunan, noTingkat, noPetak);
        if (ha != null) {
            return checkAccount(ha.getIdHakmilik());
        }
        return null;
    }

    public List<CukaiStrataForm> checkAccountStrataByNoLot(String daerah, String bpm, String seksyen, String kodLot, Integer noLot, String noBangunan, String noTingkat, String noPetak) {
        NumberFormat df = NumberFormat.getInstance();
        df.setMaximumIntegerDigits(7);
        df.setMinimumIntegerDigits(7);
        df.setGroupingUsed(false);
        Hakmilik ha = utilCarianService.cariHakmilikStrataByNoLotPlot(daerah, bpm, seksyen, kodLot, df.format(noLot), noBangunan, noTingkat, noPetak);
        if (ha != null) {
            return checkAccount(ha.getIdHakmilik());
        } else {
            return null;
        }
    }

    public List<CukaiStrataForm> checkAccount(String idHakmilik) {
        idHakmilik = idHakmilik.toUpperCase();
        List<CukaiStrataForm> list = new ArrayList<CukaiStrataForm>();
        String statusAkaun = new String();
        Date tarikhMasuk = null;
        boolean flagBatal = false;
        List<HakmlikInfo> listHakmilikInfo = null;
        String query = "SELECT a FROM etanah.model.Akaun a"
                + " where a.hakmilik.idHakmilik = :idHakmilik and a.hakmilik.idHakmilikInduk is not null";
        Session session = injector.getProvider(Session.class).get();
        Query q = session.createQuery(query);
        q.setString("idHakmilik", idHakmilik);

        if (!q.list().isEmpty()) {
            for (int i = 0; i < q.list().size(); i++) {
                listHakmilikInfo = new ArrayList<HakmlikInfo>();
                Akaun a = (Akaun) q.list().get(i);
                idHakmilik = a.getHakmilik().getIdHakmilik();
                statusAkaun = a.getStatus().getKod();
            }
            if (statusAkaun.equals("B")) {
                flagBatal = true;
            }
            if (!flagBatal) {
                listHakmilikInfo = new ArrayList<HakmlikInfo>();
                HakmlikInfo aci = new HakmlikInfo();
                aci.setIdHakmilik(idHakmilik);
                listHakmilikInfo.add(aci);
            }
            if (flagBatal) {
                listHakmilikInfo = findHakmilikValid(idHakmilik);
                if (listHakmilikInfo.isEmpty()) {
                    CukaiStrataForm aig = new CukaiStrataForm();
                    aig.setIdHakmilik(idHakmilik);
                    aig.setAkaunBatal(true);
                    list.add(aig);
                }
            }

            for (HakmlikInfo ahi : listHakmilikInfo) {
                CukaiStrataForm aig = setCarianInfoByHakmilik(ahi.getIdHakmilik());
                List<SejarahCukai> listSejarahCukai = new ArrayList<SejarahCukai>();
                for (SejarahDokumenKewangan sdk : listSejarahDokumenKewangan(aig.getIdHakmilik())) {
                    for (SejarahDokumenKewanganBayaran sdkb : sdk.getSenaraiBayaran()) {
                        SejarahCukai sj = new SejarahCukai();
                        sj.setAmaun(String.valueOf(sdkb.getAmaun()));
                        if (sdkb.getCaraBayaran() != null) {
                            String kodCaraBayar = sdkb.getCaraBayaran().getKodCaraBayaran() != null ? sdkb.getCaraBayaran().getKodCaraBayaran().getNama() : "";
                            sj.setKaedahBayaran(kodCaraBayar);
                        }

                        sj.setNoResit(sdkb.getDokumenKewangan() != null ? sdkb.getDokumenKewangan().getIdDokumenKewangan() : "");
//                        sj.setPusatKutipan(sdkb.getDokumenKewangan().getKodAgensiKutipanCaw() != null ? sdkb.getDokumenKewangan().getKodAgensiKutipanCaw().getNama() : "Kaunter " + sdkb.getDokumenKewangan().getInfoAudit().getDimasukOleh().getKodCawangan().getName());
                        sj.setTahun(sdkb.getDokumenKewangan().getTarikhTransaksi() != null ? String.valueOf(sdkb.getDokumenKewangan().getTarikhTransaksi().getYear()) : "");
                        sj.setTarikh(sdkb.getInfoAudit().getTarikhMasuk());
                        listSejarahCukai.add(sj);

                    }
                }
                aig.setListSejarahCukai(listSejarahCukai);
                list.add(aig);
            }
        }
        return list;
    }

    private List<SejarahDokumenKewangan> listSejarahDokumenKewangan(String accountNo) {
        String query = "SELECT a FROM etanah.model.SejarahDokumenKewangan a"
                + " where a.akaun.noAkaun = :accountNo ";
        Session session = injector.getProvider(Session.class).get();
        Query q = session.createQuery(query);
        q.setString("accountNo", accountNo);
        return q.list();
    }

    private List<HakmlikInfo> findHakmilikValid(String idHakmilik) {
        List<HakmlikInfo> listHakmilik = new ArrayList<HakmlikInfo>();

        String query = "SELECT hs FROM etanah.model.HakmilikSebelum hs, etanah.model.Akaun ak"
                + " where hs.hakmilikSebelum = :idHakmilik "
                + " and hs.hakmilik.kodStatusHakmilik.kod = 'D' "
                + " and ak.hakmilik.idHakmilik = hs.hakmilik.idHakmilik "
                + " and ak.status.kod = 'A'";
        Session session = injector.getProvider(Session.class).get();
        Query q = session.createQuery(query);
        q.setString("idHakmilik", idHakmilik);
        for (int i = 0; i < q.list().size(); i++) {
            HakmilikSebelum hakmilikSBLM = (HakmilikSebelum) q.list().get(i);
            HakmlikInfo info = new HakmlikInfo();
            info.setIdHakmilik(hakmilikSBLM.getHakmilik().getIdHakmilik());
            info.setNoAkaun(hakmilikSBLM.getHakmilik().getAkaunCukai().getNoAkaun());
            listHakmilik.add(info);

        }
        return listHakmilik;
    }

    private CukaiStrataForm setCarianInfoByHakmilik(String idHakmilik) {
        CukaiStrataForm ca = new CukaiStrataForm();
        String query2 = "SELECT a FROM etanah.model.Akaun a"
                + " where a.noAkaun = :accountNo "
                + "or a.hakmilik.idHakmilik = :idHakmilik ";
        Session session1 = injector.getProvider(Session.class).get();
        Query q2 = session1.createQuery(query2);
        q2.setString("accountNo", idHakmilik);
        q2.setString("idHakmilik", idHakmilik);
        Akaun aa = (Akaun) q2.uniqueResult();
        String alamat = trimAlamat(aa.getPemegang().getSuratAlamat1(), aa.getPemegang().getSuratAlamat2(),
                aa.getPemegang().getSuratAlamat3(), aa.getPemegang().getSuratAlamat4(), aa.getPemegang().getSuratPoskod());

        ca.setAlamat(alamat);
//        ca.setNamaPemilik(aa.getHakmilik().getSenaraiPihakBerkepentingan() != null ? aa.getHakmilik().getSenaraiPihakBerkepentingan().get(0).getNama() : "");
//        ca.setNamaPemilik(!utilCarianService.listPB(idHakmilik).isEmpty() ? utilCarianService.listPB(idHakmilik).get(0).getNama() : "");
        ca.setNamaPemilik(aa.getPemegang().getNama());

        ca.setBandarpekanmukim(aa.getHakmilik().getBandarPekanMukim().getNama());
        ca.setIdHakmilik(aa.getHakmilik().getIdHakmilik());
        ca.setJenisHakmilik(aa.getHakmilik().getKodHakmilik().getNama());
        ca.setNoHakmilik(aa.getHakmilik().getNoHakmilik());
        ca.setNoBangunan(aa.getHakmilik().getNoBangunan());
        ca.setNoTingkat(aa.getHakmilik().getNoTingkat());
        ca.setNoPetak(aa.getHakmilik().getNoPetak());
        ca.setKodDaerah(aa.getHakmilik().getCawangan().getKod());
        ca.setLuasPetak(aa.getHakmilik().getLuas() + "");
        ca.setLuasAksesori(setLuasAksesori(aa.getHakmilik()));
        ca.setSeksyen(aa.getHakmilik().getSeksyen() != null ? aa.getHakmilik().getSeksyen().getNama() : "");
        ca.setNoLotPt(aa.getHakmilik().getNoLot());
        ca.setKodLot(aa.getHakmilik().getLot().getNama());
        ca.setDaerah(aa.getHakmilik().getDaerah().getNama());
        ca.setKodCaw(aa.getHakmilik().getCawangan().getKod());
        ca.setKodCawValue(aa.getHakmilik().getCawangan().getName());

        //cukai
        ca = setValueCukai(ca, aa);

        return ca;
    }

    private String setLuasAksesori(Hakmilik hakmilik) {
        String query2 = "SELECT sum(a.luas) FROM etanah.model.HakmilikPetakAksesori a"
                + " where a.hakmilik.idHakmilik = :idHakmilik ";
        Session session1 = injector.getProvider(Session.class).get();
        Query q2 = session1.createQuery(query2);
        q2.setString("idHakmilik", hakmilik.getIdHakmilik());
        return q2.uniqueResult() != null ? q2.uniqueResult().toString() : "0";

    }

    private CukaiStrataForm setValueCukai(CukaiStrataForm form, Akaun aa) {
        BigDecimal cukaiSemasa = new BigDecimal(BigInteger.ZERO);
        BigDecimal tunggakanCukai = new BigDecimal(BigInteger.ZERO);;
        BigDecimal dendaLewatSemasa = new BigDecimal(BigInteger.ZERO);;
        BigDecimal tunggakanDendaLewat = new BigDecimal(BigInteger.ZERO);;
        BigDecimal borang11 = new BigDecimal(BigInteger.ZERO);;
        String idKewDok = "";
        Date tarikhMasuk = null;
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy");
        System.out.println("tahun :: " + sdf1.format(new Date()));
//        if (aa.getBaki().equals(BigDecimal.ZERO) || aa.getBaki().signum() == -1) {
//            for (Transaksi t : aa.getSenaraiTransaksiKredit()) {
//                String kodTrans = t.getKodTransaksi().getKod();
//                // int recThn = !StringUtils.isNullOrEmpty(rs.getString(5)) ? Integer.parseInt(rs.getString(5)) : 0;
//                int currYear = Integer.parseInt(sdf1.format(new Date()));
//
//                if (kodTrans.equals("61501")) {
//                    cukaiSemasa = cukaiSemasa.add(t.getAmaun());//cukaisemasaTanah
//                } else if (kodTrans.equals("61502")) {
//                    tunggakanCukai = tunggakanCukai.add(t.getAmaun());//tunggakan dari tahun cukai tanah
//                } else if (kodTrans.equals("76156")) {
//                    dendaLewatSemasa = dendaLewatSemasa.add(t.getAmaun());//dendaSemasa
//                } else if (kodTrans.equals("761599")) {
//                    tunggakanDendaLewat = tunggakanDendaLewat.add(t.getAmaun());//tunggakan denda lewat
//                } else if (kodTrans.equals("72457")) {
//                    borang11 = borang11.add(t.getAmaun());//notis 6A
//                } else if (kodTrans.equals("61611")) {
//                }
//                idKewDok = t.getDokumenKewangan() != null ? t.getDokumenKewangan().getIdDokumenKewangan() : null;
//                tarikhMasuk = t.getDokumenKewangan() != null ? t.getDokumenKewangan().getTarikhTransaksi() : null;
//            }
//
//            form.setJumlahBayaran(aa.getBaki());
//            form.setJumlahKeseluruhan(form.getJumlahBayaran());
//            form.setStatusBayaran("TELAH BAYAR");
//        } else {
//            for (Transaksi t : aa.getSenaraiTransaksiDebit()) {
//                String kodTrans = t.getKodTransaksi().getKod();
//                // int recThn = !StringUtils.isNullOrEmpty(rs.getString(5)) ? Integer.parseInt(rs.getString(5)) : 0;
//                int currYear = Integer.parseInt(sdf1.format(new Date()));
//
//                if (kodTrans.equals("61501")) {
//                    cukaiSemasa = cukaiSemasa.add(t.getAmaun());//cukaisemasaTanah
//                } else if (kodTrans.equals("61502")) {
//                    tunggakanCukai = tunggakanCukai.add(t.getAmaun());//tunggakan dari tahun cukai tanah
//                } else if (kodTrans.equals("76156")) {
//                    dendaLewatSemasa = dendaLewatSemasa.add(t.getAmaun());//dendaSemasa
//                } else if (kodTrans.equals("761599")) {
//                    tunggakanDendaLewat = tunggakanDendaLewat.add(t.getAmaun());//tunggakan denda lewat
//                } else if (kodTrans.equals("72457")) {
//                    borang11 = borang11.add(t.getAmaun());//notis 6A
//                } else if (kodTrans.equals("61611")) {
//                }
//            }
//
////            form.setJumlahBayaran(cukaiSemasa.add(tunggakanCukai).add(dendaLewatSemasa).add(tunggakanDendaLewat));
//            form.setJumlahBayaran(aa.getBaki());
//            form.setJumlahKeseluruhan(form.getJumlahBayaran());
//            form.setStatusBayaran("BELUM BAYAR");
//        }
 int currYear = Integer.parseInt(sdf1.format(new Date()));

            for (Transaksi t : aa.getSenaraiTransaksiDebit()) {
                String kodTrans = t.getKodTransaksi().getKod();
                System.out.println("KodTrans" + kodTrans);
                // int recThn = !StringUtils.isNullOrEmpty(rs.getString(5)) ? Integer.parseInt(rs.getString(5)) : 0;

                if (kodTrans.equals("61501")) {
                    cukaiSemasa = cukaiSemasa.add(t.getAmaun());//cukaisemasaTanah
                } else if (kodTrans.equals("61502")) {
                    tunggakanCukai = tunggakanCukai.add(t.getAmaun());//tunggakan dari tahun cukai tanah
                } else if (kodTrans.equals("76156") && t.getUntukTahun() == currYear) {
                    dendaLewatSemasa = dendaLewatSemasa.add(t.getAmaun());//dendaSemasa
                } else if (kodTrans.equals("76156") && t.getUntukTahun() != currYear) {
                    tunggakanDendaLewat = tunggakanDendaLewat.add(t.getAmaun());//tunggakan denda lewat
                }  else if (kodTrans.equals("72457")) {
                    borang11 = borang11.add(t.getAmaun());//notis 6A
                }
            }
            BigDecimal cukaiSemasaTanahKredit = BigDecimal.ZERO;
            BigDecimal tunggakanCukaiTanahKredit = BigDecimal.ZERO;
            BigDecimal dendaLewatSemasaKredit = BigDecimal.ZERO;
            BigDecimal tunggakanDendaLewatKredit = BigDecimal.ZERO;
            BigDecimal othersKredit = BigDecimal.ZERO;

            for (Transaksi t : aa.getSenaraiTransaksiKredit()) {
                String kodTrans = t.getKodTransaksi().getKod();
                if (kodTrans.equals("61501")) {
                    cukaiSemasaTanahKredit = cukaiSemasaTanahKredit.add(t.getAmaun());//cukaisemasaTanah
                } else if (kodTrans.equals("61502")) {
                    tunggakanCukaiTanahKredit = tunggakanCukaiTanahKredit.add(t.getAmaun());//tunggakan dari tahun cukai tanah
                } else if (kodTrans.equals("76156")) {
                    tunggakanDendaLewatKredit = tunggakanDendaLewatKredit.add(t.getAmaun());//tunggakan denda lewat
                }   else if (kodTrans.equals("61611")) {
                } else {
                    othersKredit = othersKredit.add(t.getAmaun());
                }
            }
         
            BigDecimal grandCukaiSemasaTanah = BigDecimal.ZERO;
            BigDecimal grandTunggakanCukaiTanah = BigDecimal.ZERO;
            BigDecimal grandDendaLewatSemasa = BigDecimal.ZERO;
            BigDecimal grandTunggakanDendaLewat = BigDecimal.ZERO;
            BigDecimal grandBaki = BigDecimal.ZERO;

            grandCukaiSemasaTanah = cukaiSemasa.subtract(cukaiSemasaTanahKredit);
            grandTunggakanCukaiTanah = tunggakanCukai.subtract(tunggakanCukaiTanahKredit);
            if (tunggakanDendaLewatKredit.compareTo(BigDecimal.ZERO) > 0) {
                if (tunggakanDendaLewat.compareTo(tunggakanDendaLewatKredit) > 0) {
                    tunggakanDendaLewat = tunggakanDendaLewat.subtract(tunggakanDendaLewatKredit);
                    grandTunggakanDendaLewat = tunggakanDendaLewat;
                } else {
                    tunggakanDendaLewatKredit = tunggakanDendaLewatKredit.subtract(tunggakanDendaLewat);
                    tunggakanDendaLewat = BigDecimal.ZERO;
                    grandTunggakanDendaLewat =tunggakanDendaLewat;
                    if(tunggakanDendaLewatKredit.compareTo(dendaLewatSemasa)>0){
                        tunggakanDendaLewatKredit = tunggakanDendaLewatKredit.subtract(dendaLewatSemasa);
                        grandDendaLewatSemasa = BigDecimal.ZERO;
                    }else{
                        grandDendaLewatSemasa = dendaLewatSemasa.subtract(tunggakanDendaLewatKredit);

                    }
                    
                }
                
            
            }else{
                   grandDendaLewatSemasa = dendaLewatSemasa;
             grandTunggakanDendaLewat = tunggakanDendaLewat;
            }
            if (othersKredit.compareTo(BigDecimal.ZERO) > 0) {
                grandTunggakanCukaiTanah = grandTunggakanCukaiTanah.subtract(othersKredit);
            }
            grandBaki = grandCukaiSemasaTanah.add(grandTunggakanCukaiTanah).add(grandDendaLewatSemasa).add(grandTunggakanDendaLewat);
            System.out.println("GRANDBAKI IS A" + grandBaki);
            
//            form.setJumlahBayaran(grandBaki);
            form.setJumlahBayaran(aa.getBaki());
            form.setJumlahKeseluruhan(form.getJumlahBayaran());
            form.setStatusBayaran("BELUM BAYAR");

             if (aa.getBaki().equals(new BigDecimal(BigInteger.ZERO))|| aa.getBaki().signum() == -1) { // already paid for that accountNo
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
                try {
                    form.setTarikhBayaran(sdf.format(tarikhMasuk));
                } catch (Exception e) {
                    form.setTarikhBayaran("");
                }
                form.setJumlahBayaran(BigDecimal.ZERO);
            form.setJumlahKeseluruhan(BigDecimal.ZERO);
                form.setStatusBayaran("TELAH BAYAR");
                form.setNoResit(idKewDok);
            }
           
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
        System.out.println("tahun :: " + sdf.format(new Date()));
        form.setKodCukaiPetak("61501");
        form.setKodDendaLewat("76156");
        form.setKodTunggakanCukaiPetak("61502");
        form.setKodTunggakanDendaLewat("");
        form.setKodborang11("");

        
        form.setCukaiSemasa(grandCukaiSemasaTanah);
        form.setTunggakanCukai(grandTunggakanCukaiTanah);
        form.setTunggakanDendaLewat(grandTunggakanDendaLewat);
        form.setDendaLewatSemasa(grandDendaLewatSemasa);
        form.setBorang11(borang11);
        form.setTarikhBayaran(tarikhMasuk != null ? sdf.format(tarikhMasuk) : "");
        form.setNoResit(idKewDok);
        return form;
    }

    private String trimAlamat(String alamat1, String alamat2, String alamat3,
            String alamat4, String poskod) {
        String completeAddress = "";

        String fragments[] = {alamat1, alamat2, alamat3, alamat4, poskod};

        for (String frag : fragments) {
            if (frag != null && !(frag.equals(""))) {
                // remove comma (,) from the fragments if have
                if (frag.charAt(frag.length() - 1) == ',') {
                    frag = frag.substring(0, frag.length() - 1);
                }

                // trim it to make it nice
                frag.trim();

                // combines all
                if (completeAddress.equals("")) {
                    completeAddress += frag;
                } else {
                    completeAddress += ", " + frag;
                }
            }
        }

        return completeAddress;
    }
}
