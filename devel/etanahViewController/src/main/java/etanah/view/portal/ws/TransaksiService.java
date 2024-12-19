/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.portal.ws;

import com.google.inject.Inject;
import com.google.inject.Injector;
import etanah.dao.KodStatusTransaksiKewanganDAO;
import etanah.dao.PenggunaDAO;
import etanah.dao.TransaksiDAO;
import etanah.model.Akaun;
import etanah.model.DokumenKewangan;
import etanah.model.InfoAudit;
import etanah.model.KodCawangan;
import etanah.model.Pengguna;
import etanah.model.Transaksi;
import etanah.view.etanahContextListener;
import static etanah.view.portal.ws.TerimaBayaranServices.isNegative;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author mohd.faidzal
 */
public class TransaksiService {

    private static Injector injector = etanahContextListener.getInjector();
    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;
    @Inject
    KodStatusTransaksiKewanganDAO kodStatusTransaksiKewanganDAO;
    @Inject
    TransaksiDAO transaksiDAO;
    @Inject
    PenggunaDAO penggunaDAO;
    InfoAudit ia;

    public void updateTransaksi(String noAkaun, String idHakmilik, Akaun akaunKutip, DokumenKewangan dk, KodCawangan kodCawangan, InfoAudit ia, BigDecimal amaun, Boolean strata) {
        AccountInfo aig = new AccountInfo();
        String query2 = "SELECT a FROM etanah.model.Akaun a"
                + " where (a.noAkaun = :accountNo "
                + "or a.hakmilik.idHakmilik = :idHakmilik ) and a.status.kod = 'A'";
        Session session1 = injector.getProvider(Session.class).get();
        Query q2 = session1.createQuery(query2);
        q2.setString("accountNo", noAkaun);
        q2.setString("idHakmilik", idHakmilik);
        Akaun aa = (Akaun) q2.uniqueResult();

        BigDecimal cukaiSemasaTanah = BigDecimal.ZERO;
        BigDecimal tunggakanCukaiTanah = BigDecimal.ZERO;
        BigDecimal dendaLewatSemasa = BigDecimal.ZERO;
        BigDecimal remisyen = BigDecimal.ZERO;
        BigDecimal notis6a = BigDecimal.ZERO;
        BigDecimal jumlahCukaiSemasa = BigDecimal.ZERO;
        BigDecimal jumlahTunggakan = BigDecimal.ZERO;
        BigDecimal cukaiSemasaParit = BigDecimal.ZERO;
        BigDecimal tunggakanCukaiParit = BigDecimal.ZERO;
        BigDecimal tunggakanDendaLewat = BigDecimal.ZERO;

        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy");
        int currYear = Integer.parseInt(sdf1.format(new Date()));

        for (Transaksi t : aa.getSenaraiTransaksiDebit()) {
            String kodTrans = t.getKodTransaksi().getKod();
            System.out.println("KodTrans" + kodTrans);
            Integer utkTahun = t.getUntukTahun();
            // int recThn = !StringUtils.isNullOrEmpty(rs.getString(5)) ? Integer.parseInt(rs.getString(5)) : 0;

            if (kodTrans.equals("61401") || kodTrans.equals("61501")) {
                cukaiSemasaTanah = cukaiSemasaTanah.add(t.getAmaun());//cukaisemasaTanah
            } else if (kodTrans.equals("61402") || kodTrans.equals("61502")) {
                tunggakanCukaiTanah = tunggakanCukaiTanah.add(t.getAmaun());//tunggakan dari tahun cukai tanah
            } else if ((kodTrans.equals("76152") || kodTrans.equals("76156")) && utkTahun == currYear) {
                dendaLewatSemasa = dendaLewatSemasa.add(t.getAmaun());//dendaSemasa
            } else if ((kodTrans.equals("76152") || kodTrans.equals("76156")) && utkTahun != currYear) {
                tunggakanDendaLewat = tunggakanDendaLewat.add(t.getAmaun());//tunggakan denda lewat
            } else if (kodTrans.equals("99000") || kodTrans.equals("99001")
                    || kodTrans.equals("99002") || kodTrans.equals("99003")) {
                remisyen = remisyen.add(t.getAmaun());//remisyen
                // aig.setKodRemisyen(kodTrans);
            } else if (kodTrans.equals("72457")) {
                notis6a = notis6a.add(t.getAmaun());//notis 6A
            }
        }
        BigDecimal cukaiSemasaTanahKredit = BigDecimal.ZERO;
        BigDecimal tunggakanCukaiTanahKredit = BigDecimal.ZERO;
        BigDecimal dendaLewatSemasaKredit = BigDecimal.ZERO;
        BigDecimal tunggakanDendaLewatKredit = BigDecimal.ZERO;
        BigDecimal othersKredit = BigDecimal.ZERO;

        for (Transaksi t : aa.getSenaraiTransaksiKredit()) {
            String kodTrans = t.getKodTransaksi().getKod();
            if (kodTrans.equals("61401") || kodTrans.equals("61501")) {
                cukaiSemasaTanahKredit = cukaiSemasaTanahKredit.add(t.getAmaun());//cukaisemasaTanah
            } else if (kodTrans.equals("61402") || kodTrans.equals("61502")) {
                tunggakanCukaiTanahKredit = tunggakanCukaiTanahKredit.add(t.getAmaun());//tunggakan dari tahun cukai tanah
            } else if (kodTrans.equals("76152") || kodTrans.equals("76156")) {
                tunggakanDendaLewatKredit = tunggakanDendaLewatKredit.add(t.getAmaun());//tunggakan denda lewat
            } else if (kodTrans.equals("72457")) {
                notis6a = notis6a.add(t.getAmaun());//notis 6A
            } else if (kodTrans.equals("61601")) {
                cukaiSemasaParit = cukaiSemasaParit.add(t.getAmaun());//cukai Semasa Parit
            } else if (kodTrans.equals("61602")) {
                tunggakanCukaiParit = tunggakanCukaiParit.add(t.getAmaun());//tunggakan Cukai Parit
            } else if (kodTrans.equals("61611")) {
            } else {
                othersKredit = othersKredit.add(t.getAmaun());
            }
        }

        jumlahCukaiSemasa = cukaiSemasaTanah.add(cukaiSemasaParit);
        jumlahTunggakan = tunggakanCukaiTanah.add(tunggakanCukaiParit);
        BigDecimal grandCukaiSemasaTanah = BigDecimal.ZERO;
        BigDecimal grandTunggakanCukaiTanah = BigDecimal.ZERO;
        BigDecimal grandDendaLewatSemasa = BigDecimal.ZERO;
        BigDecimal grandTunggakanDendaLewat = BigDecimal.ZERO;
        BigDecimal grandBaki = BigDecimal.ZERO;

        grandCukaiSemasaTanah = jumlahCukaiSemasa.subtract(cukaiSemasaTanahKredit);
        grandTunggakanCukaiTanah = jumlahTunggakan.subtract(tunggakanCukaiTanahKredit);
        if (tunggakanDendaLewatKredit.compareTo(BigDecimal.ZERO) > 0) {
            if (tunggakanDendaLewat.compareTo(tunggakanDendaLewatKredit) > 0) {
                tunggakanDendaLewat = tunggakanDendaLewat.subtract(tunggakanDendaLewatKredit);
                grandTunggakanDendaLewat = tunggakanDendaLewat;
            } else {
                tunggakanDendaLewatKredit = tunggakanDendaLewatKredit.subtract(tunggakanDendaLewat);
                tunggakanDendaLewat = BigDecimal.ZERO;
                grandTunggakanDendaLewat = tunggakanDendaLewat;
                if (tunggakanDendaLewatKredit.compareTo(dendaLewatSemasa) > 0) {
                    tunggakanDendaLewatKredit = tunggakanDendaLewatKredit.subtract(dendaLewatSemasa);
                    grandDendaLewatSemasa = BigDecimal.ZERO;
                } else {
                    grandDendaLewatSemasa = dendaLewatSemasa.subtract(tunggakanDendaLewatKredit);

                }

            }

        } else {
            grandDendaLewatSemasa = dendaLewatSemasa;
            grandTunggakanDendaLewat = tunggakanDendaLewat;
        }
        if (othersKredit.compareTo(BigDecimal.ZERO) > 0) {
            grandTunggakanCukaiTanah = grandTunggakanCukaiTanah.subtract(othersKredit);
        }
        grandBaki = grandCukaiSemasaTanah.add(grandTunggakanCukaiTanah).add(grandDendaLewatSemasa).add(grandTunggakanDendaLewat);
        System.out.println("GRANDBAKI IS A" + grandBaki);

        BigDecimal balance = amaun;
        if (balance.compareTo(BigDecimal.ZERO) > 0) {
            if (grandTunggakanCukaiTanah.compareTo(BigDecimal.ZERO) > 0) {
                Transaksi trans = strata == false ? findTransaksiByKodHasilAndAkaun("61402", aa.getNoAkaun()) : findTransaksiByKodHasilAndAkaun("61502", aa.getNoAkaun());
                Transaksi transaksi = new Transaksi();
//                transaksi.setAmaun(grandTunggakanCukaiTanah);
                BigDecimal g = grandTunggakanCukaiTanah;
                if (balance.compareTo(g) > 0) {
                    transaksi.setAmaun(g);
                } else {
                    transaksi.setAmaun(balance);
                }
                setTransaksi(transaksi, trans, aa, akaunKutip, dk, kodCawangan, ia);
                balance = balance.subtract(grandTunggakanCukaiTanah);
            }
        }
        if (balance.compareTo(BigDecimal.ZERO) > 0) {
            if (grandTunggakanDendaLewat.compareTo(BigDecimal.ZERO) > 0) {
                Transaksi trans = strata == false ? findTransaksiByKodHasilAndAkaun("76152", aa.getNoAkaun()) : findTransaksiByKodHasilAndAkaun("76156", aa.getNoAkaun());
                Transaksi transaksi = new Transaksi();
                BigDecimal g = grandTunggakanDendaLewat.add(grandDendaLewatSemasa);
                if (balance.compareTo(g) > 0) {
                    transaksi.setAmaun(g);
                } else {
                    transaksi.setAmaun(balance);
                }

                setTransaksi(transaksi, trans, aa, akaunKutip, dk, kodCawangan, ia);
                balance = balance.subtract(grandTunggakanDendaLewat).subtract(grandDendaLewatSemasa);
            } else {
                if (grandDendaLewatSemasa.compareTo(BigDecimal.ZERO) > 0) {
                    Transaksi trans = strata == false ? findTransaksiByKodHasilAndAkaun("76152", aa.getNoAkaun()) : findTransaksiByKodHasilAndAkaun("76156", aa.getNoAkaun());
                    Transaksi transaksi = new Transaksi();
                    BigDecimal g = grandTunggakanDendaLewat.add(grandDendaLewatSemasa);
                    if (balance.compareTo(g) > 0) {
                        transaksi.setAmaun(g);
                    } else {
                        transaksi.setAmaun(balance);
                    }
                    setTransaksi(transaksi, trans, aa, akaunKutip, dk, kodCawangan, ia);
                    balance = balance.subtract(grandTunggakanDendaLewat).subtract(grandDendaLewatSemasa);
                }
            }
        }
        
        if (balance.compareTo(BigDecimal.ZERO) > 0) {
            if (grandCukaiSemasaTanah.compareTo(BigDecimal.ZERO) > 0) {
                Transaksi trans = strata == false ? findTransaksiByKodHasilAndAkaun("61401", aa.getNoAkaun()) : findTransaksiByKodHasilAndAkaun("61501", aa.getNoAkaun());
                Transaksi transaksi = new Transaksi();
//                transaksi.setAmaun(grandCukaiSemasaTanah);
                BigDecimal g = grandCukaiSemasaTanah;
                if (balance.compareTo(g) > 0) {
                    transaksi.setAmaun(g);
                } else {
                    transaksi.setAmaun(balance);
                }
                setTransaksi(transaksi, trans, aa, akaunKutip, dk, kodCawangan, ia);
                balance = balance.subtract(grandCukaiSemasaTanah);

            }
        }
        if (balance.compareTo(BigDecimal.ZERO) > 0) {
            Transaksi trans = strata == false ? findTransaksiByKodHasilAndAkaun("61401", aa.getNoAkaun()) : findTransaksiByKodHasilAndAkaun("61501", aa.getNoAkaun());
            Transaksi transaksi = new Transaksi();
            transaksi.setAmaun(balance);
            setTransaksi(transaksi, trans, aa, akaunKutip, dk, kodCawangan, ia);
        }

    }

    private Transaksi findTransaksiByKodHasilAndAkaun(String kodHasil, String noAkaun) {
        String query2 = "SELECT a FROM etanah.model.Transaksi a"
                + " where a.akaunDebit.noAkaun = :noAkaun "
                + "and a.kodTransaksi.kod = :kodTransaksi ";
        Session session1 = injector.getProvider(Session.class).get();
        Query q2 = session1.createQuery(query2);
        q2.setString("noAkaun", noAkaun);
        q2.setString("kodTransaksi", kodHasil);
        List<Transaksi> aa = q2.list();
        if (!aa.isEmpty()) {
            return aa.get(0);
        } else {
            return null;
        }
    }

    private Transaksi setTransaksi(Transaksi transaksi, Transaksi trans, Akaun akaun, Akaun akaunKutip, DokumenKewangan dk, KodCawangan kodCawangan, InfoAudit ia) {
        Calendar cal = Calendar.getInstance();
//        ia = new InfoAudit();
//        ia.setTarikhMasuk(new Date());
//        Pengguna dimasukOleh = penggunaDAO.findById("portal");
//        ia.setDimasukOleh(dimasukOleh);
        cal.setTime(new Date());
        int year = cal.get(Calendar.YEAR);
        transaksi.setKodTransaksi(trans.getKodTransaksi());
        transaksi.setPermohonan(trans.getPermohonan());
        if (akaun.getBaki().setScale(2).equals(BigDecimal.ZERO.setScale(2)) || isNegative(akaun.getBaki())) {
            transaksi.setUntukTahun(year + 1);
        } else {
            transaksi.setUntukTahun(trans.getUntukTahun());
        }
        transaksi.setKuantiti(trans.getKuantiti());
        transaksi.setTahunKewangan(year);
        transaksi.setBayaranAgensi(trans.getBayaranAgensi());
        transaksi.setStatus(kodStatusTransaksiKewanganDAO.findById('T'));
        transaksi.setInfoAudit(ia);
        transaksi.setAkaunKredit(akaun);
        transaksi.setAkaunDebit(akaunKutip);
        transaksi.setDokumenKewangan(dk);
        transaksi.setCawangan(kodCawangan);
        transaksi = transaksiDAO.saveOrUpdate(transaksi);
        return transaksi;
    }
}
