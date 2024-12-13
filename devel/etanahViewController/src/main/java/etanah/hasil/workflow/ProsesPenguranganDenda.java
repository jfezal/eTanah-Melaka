package etanah.hasil.workflow;

import java.util.List;
import etanah.dao.*;
import etanah.model.*;
import etanah.workflow.*;
import java.math.BigDecimal;
import com.google.inject.Inject;
import org.apache.log4j.Logger;
import java.text.SimpleDateFormat;
import etanah.view.stripes.hasil.RemisyenManager;

/**
 * @author abu.mansur
 * @modified by : haqqiem
 */
public class ProsesPenguranganDenda implements StageListener {
    private static final Logger LOG = Logger.getLogger(ProsesPenguranganDenda.class);
    private static final boolean IS_LOG_DEBUG = LOG.isDebugEnabled();
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy");

    @Inject AkaunDAO akaunDAO;
    @Inject RemisyenManager manager;
    @Inject KodStatusTransaksiKewanganDAO kodStatusTransaksiKewanganDAO;
    @Inject DasarTuntutanCukaiHakmilikDAO dasarHakmilikDAO;
    @Inject etanah.kodHasilConfig khconf;

    @Override
    public boolean beforeStart(StageContext cxt) {
        return true;
    }

    @Override
    public void onGenerateReports(StageContext context) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public String beforeComplete(StageContext ctx, String proposedOutcome) {
        LOG.info("ProsesPenguranganDenda::beforeComplete()");
        Pengguna pengguna = ctx.getPengguna();
        
        String kodTransDenda = khconf.getProperty("dendaLewat");
        String kodTransDendaKurang = khconf.getProperty("pengurangan.denda");
        String kodTrans_ganda = khconf.getProperty("dendaGanda");
        String DendaLewat = "PPDL"; // for Mlk
        String DendaKurang = "PKDK"; // for N9
        String BatalRampas = "RPPP"; // for Mlk
        String Lulus = "L";
        String rtnMsg = "";
        
        Permohonan permohonan = ctx.getPermohonan();
        if (Lulus.equals(proposedOutcome)) {
            Hakmilik h = permohonan.getSenaraiHakmilik().get(0).getHakmilik();
            Akaun akaunAC = h.getAkaunCukai();
            Akaun akaunADL = null;

            String[] kod = {"kodAkaun.kod"};
            Object[] nilai = {khconf.getProperty("akaunDendaLewat")}; // for kod ADL
            for (Akaun ak : akaunDAO.findByEqualCriterias(kod, nilai, null)) {
                if (ak.getKodAkaun().getKod().equals(khconf.getProperty("akaunDendaLewat")) && ak.getCawangan().getKod().equals(h.getCawangan().getKod())) {
                    akaunADL = ak;
                }
            }

            KodUrusan u = permohonan.getKodUrusan();
            Transaksi t = new Transaksi();
            KodTransaksi kt = new KodTransaksi();
            KodStatusTransaksiKewangan status = kodStatusTransaksiKewanganDAO.findById('A');

            BigDecimal rounded_down = BigDecimal.ZERO;
            if (u.getKod().equals(DendaLewat) || u.getKod().equals(DendaKurang)) {
                ProsesPenguranganDendaCalc ppdc = new ProsesPenguranganDendaCalc();
                BigDecimal dendaSemasa = new BigDecimal(0.0);
                for (int i = 0; i < akaunAC.getSemuaTransaksi().size(); i++) {

                    Transaksi transaksiSemasa = akaunAC.getSemuaTransaksi().get(i);
                    LOG.debug("PPDL");
                    LOG.debug("transaksiSemasa :" + transaksiSemasa.getIdTransaksi());
                    if ((u.getKod().equals(DendaLewat) || u.getKod().equals(DendaKurang)) && transaksiSemasa.getKodTransaksi().getKod().equals(kodTransDenda)) {
                        dendaSemasa = dendaSemasa.add(transaksiSemasa.getAmaun());      // total denda
                    }
                }
                if (u.getKod().equals(DendaLewat) || u.getKod().equals(DendaKurang)) {
                    // untuk urusan PPDL sahaja
                    LOG.debug("urusan PPDL or PKDK");
                    BigDecimal jumlahDendaSemasa = dendaSemasa;
                    kt.setKod(kodTransDendaKurang); //new change for same code
                    t.setKodTransaksi(kt);

                    //to update record in object "Akaun" for account "ADL"
                    rounded_down = ppdc.calc(jumlahDendaSemasa, permohonan.getNilaiKeputusan());
                    akaunADL.setBaki(akaunADL.getBaki().add(rounded_down));
                    t.setAkaunDebit(akaunADL);
                }
                //to update record for "AC" in object "Akaun"
                akaunAC.setBaki(akaunAC.getBaki().subtract(rounded_down));

                t.setAmaun(rounded_down);
                t.setCawangan(h.getCawangan());
                t.setPermohonan(permohonan);
                t.setStatus(status);
                t.setAkaunKredit(akaunAC);
                t.setUntukTahun(Integer.parseInt(sdf.format(new java.util.Date())));
                t.setTahunKewangan(Integer.parseInt(sdf.format(new java.util.Date())));

                manager.saveAndUpdate(t, akaunADL, null, null, null, akaunAC, pengguna);
                ctx.addMessage(permohonan.getKodUrusan().getNama() + " sebanyak RM " + rounded_down + " telah dikemaskini ke dalam Akaun " + akaunAC.getNoAkaun());
                rtnMsg = permohonan.getKodUrusan().getNama() + " sebanyak RM " + rounded_down + " telah dikemaskini ke dalam Akaun " + akaunAC.getNoAkaun();
                LOG.info(permohonan.getKodUrusan().getNama() + " sebanyak RM " + rounded_down + " telah dikemaskini ke dalam Akaun " + akaunAC.getNoAkaun());
            } else if (u.getKod().equals(BatalRampas)) {
                // untuk urusan RPPP sahaja
                ProsesPenguranganDendaCalc ppdc = new ProsesPenguranganDendaCalc();
                String[] name = {"hakmilik"};
                Object[] value = {h};
                List<DasarTuntutanCukaiHakmilik> senaraiDTCH = dasarHakmilikDAO.findByEqualCriterias(name, value, null);
                LOG.debug("senaraiDTCH.size :" + senaraiDTCH.size());
                if (senaraiDTCH.size() > 0 && h.getAkaunCukai().getBaki().intValue() > 0) {           // asal
//                if (h.getAkaunCukai().getBaki().intValue() > 0) {                                                   // sementara              
                    BigDecimal cukaiBaki = h.getAkaunCukai().getBaki();
                    kt.setKod(kodTrans_ganda); //new change for same code
                    t.setKodTransaksi(kt);

                    rounded_down = ppdc.calcGandaanDenda(cukaiBaki, permohonan.getNilaiKeputusan());
                    akaunAC.setBaki(akaunAC.getBaki().add(rounded_down));

                    t.setAmaun(rounded_down);
                    t.setCawangan(h.getCawangan());
                    t.setPermohonan(permohonan);
                    t.setStatus(status);
                    t.setAkaunDebit(akaunAC);
                    t.setUntukTahun(Integer.parseInt(sdf.format(new java.util.Date())));
                    t.setTahunKewangan(Integer.parseInt(sdf.format(new java.util.Date())));

                    manager.saveAndUpdate(t, null, null, null, null, akaunAC, pengguna);
                    ctx.addMessage(permohonan.getKodUrusan().getNama() + " sebanyak RM " + rounded_down + " telah dikemaskini ke dalam Akaun " + akaunAC.getNoAkaun());
                    rtnMsg = permohonan.getKodUrusan().getNama() + " sebanyak RM " + rounded_down + " telah dikemaskini ke dalam Akaun " + akaunAC.getNoAkaun();
                    LOG.info(permohonan.getKodUrusan().getNama() + " sebanyak RM " + rounded_down + " telah dikemaskini ke dalam Akaun " + akaunAC.getNoAkaun());
                }
            } else {
                LOG.info("Untuk Kod Urusan REMRI dan REMSB");
//                LOG.error("Kod urusan Tidak sesuai dengan permohonan yang telah dibuat.");
//                return null;
            }
        } else {
            LOG.debug("Keputusan :" + proposedOutcome);
        }
        return proposedOutcome;
    }

    @Override
    public void afterComplete(StageContext context) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean beforeGenerateReports(StageContext ctx) {
//        throw new UnsupportedOperationException("Not supported yet.");
        return true;
    }

    @Override
    public void afterPushBack(StageContext context) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public String beforePushBack(StageContext context, String proposedOutcome) {
//        return proposedOutcome;
        return "back";
    }
}
