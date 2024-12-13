package etanah.view.kaunter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.google.inject.Inject;

import net.sourceforge.stripes.action.ActionBean;
import etanah.dao.KodTransaksiDAO;
import etanah.dao.KodUrusanDAO;
import etanah.model.Dokumen;
import etanah.model.DokumenKewangan;
import etanah.model.InfoAudit;
import etanah.model.KodJenisPengenalan;
import etanah.model.KodNegeri;
import etanah.model.KodPenyerah;
import etanah.model.KodTransaksi;
import etanah.model.KodUrusan;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.report.ReportUtil;

public class BayaranPelbagaiValue implements UrusanKaunter {
    
    int pos;
    
    // flag to determine if from kod urusan (table kod_urusan) for 'Y' or transaksi (table kod_kew_trans) for 'T'
    char kategoriUrusan;
    
    String kodUrusan;
    
    String namaUrusan;
    
    private BigDecimal cajPerUnit = BigDecimal.ZERO;
    
    private int kuantiti = 1;
    
    private BigDecimal jumlahCaj = BigDecimal.ZERO;
    
    @Inject
    private KodUrusanDAO kodUrusanDAO;
    
    @Inject
    private KodTransaksiDAO kodTransaksiDAO;
    
    private static final Logger LOG = Logger.getLogger(BayaranPelbagaiValue.class);
    private static final boolean debug = LOG.isDebugEnabled();

    @Override
    public void setPosition(int pos) {
        this.pos = pos;
    }

    @Override
    public int getPosition() {
        return pos;
    }

    public char getKategoriUrusan(){
        return kategoriUrusan;
    }

    public void setKategoriUrusan(char kategoriUrusan){
        this.kategoriUrusan = kategoriUrusan;
    }
    
    @Override
    public void setKodUrusan(String kodUrusan) {
        this.kodUrusan = kodUrusan;
    }
    
    @Override
    public String getKodUrusan() {
        return kodUrusan;
    }

    public void setCajPerUnit(BigDecimal cajPerUnit) {
        this.cajPerUnit = cajPerUnit;
    }

    public BigDecimal getCajPerUnit() {
        return cajPerUnit;
    }

    public void setKuantiti(int kuantiti) {
        this.kuantiti = kuantiti;
    }

    public int getKuantiti() {
        return kuantiti;
    }

    public void setJumlahCaj(BigDecimal jumlahCaj) {
        this.jumlahCaj = jumlahCaj;
    }

    public BigDecimal getJumlahCaj() {
        return jumlahCaj;
    }

    @Override
    public String getNamaUrusan() {
        return namaUrusan;
    }

    @Override
    public void setNoRujukan(String noRujukan) {
    }

    @Override
    public String getNoRujukan() {
        return null;
    }

    @Override
    public Permohonan getPermohonan() {
        return null;
    }

    @Override
    public List<DokumenValue> getSenaraiDokumenSerahan() {
        return null;
    }

    @Override
    public List<DokumenValue> getSenaraiDokumenTamb() {
        return null;
    }

    @Override
    public List<String> getSenaraiIdHakmilik() {
        return null;
    }

    @Override
    public List<TransaksiValue> getSenaraiTransaksi() {
        if (kodUrusan == null){
            return null;
        }
        
        TransaksiValue tv = new TransaksiValue();
        tv.utkUrusan = this;
        if (kategoriUrusan == 'Y'){
            KodUrusan ku = kodUrusanDAO.findById(kodUrusan);
            if (ku == null){
                LOG.warn("Kod Urusan tidak dijumpai: " + kodUrusan);
                return null;
            }
            tv.kodUrusan = kodUrusan;
            namaUrusan = ku.getNama();
            tv.namaUrusan = ku.getNama();
            tv.kuantiti = kuantiti;
            tv.amaun = new BigDecimal(cajPerUnit.doubleValue() * kuantiti);
            tv.kodTransaksi = ku.getKodTransaksi().getKod();
        } else{
            KodTransaksi kt = kodTransaksiDAO.findById(kodUrusan);
            if (kt == null){
                LOG.warn("Kod Transaksi tidak dijumpai: " + kodUrusan);
                return null;
            }
            namaUrusan = kt.getNama();
            tv.namaUrusan = kt.getNama();
            tv.kuantiti = kuantiti;
            tv.amaun = cajPerUnit;
            tv.kodTransaksi = kodUrusan;
        }
        
        ArrayList<TransaksiValue> ltv = new ArrayList<TransaksiValue>();
        ltv.add(tv);
        return ltv;
    }

    @Override
    public List<? extends UrusanKaunter> doSave(String kodNegeri,
            Pengguna pengguna, Dokumen resit, String idKumpulan, int turutan,
            String idPenyerah, KodPenyerah penyerahKod,
            String penyerahNoPengenalan,
            KodJenisPengenalan penyerahJenisPengenalan, String penyerahNama,
            String penyerahAlamat1, String penyerahAlamat2,
            String penyerahAlamat3, String penyerahAlamat4,
            String penyerahPoskod, KodNegeri penyerahNegeri,
            String penyerahEmail, String penyerahNoTelefon, 
            String namaPenyampai, String noPengenalanPenyampai,
            String noTelPenyampai,InfoAudit ia) {
        // no extra work to do
        ArrayList<UrusanKaunter> auk = new ArrayList<UrusanKaunter>();
        auk.add(this);
        
        return auk;
    }

    @Override
    public void doAfterSave(ReportUtil reportUtil, DokumenKewangan dk, InfoAudit ia) {
    }

    @Override
    public Class<? extends ActionBean> getEditActionBean() {
        return BayaranPelbagaiActionBean2.class;
    }

    @Override
    public Dokumen getCetakan() {
        // nothing to print for this urusan except receipt
        return null;
    }

    @Override
    public boolean hasToInitiateWorkflow() {
        return false;
    }
    
    @Override
    public boolean isEditable(){
        return false;
    }

    /**
     * kumpulanUrusan is not significant since every bayaran will produce
     * individual receipt.
     */
    @Override
    public String getKumpulanUrusan() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void setKumpulanUrusan(String kumpulan) {
        // TODO Auto-generated method stub
        
    }

}
