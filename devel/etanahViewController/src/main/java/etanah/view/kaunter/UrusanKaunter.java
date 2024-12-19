package etanah.view.kaunter;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import net.sourceforge.stripes.action.ActionBean;

import etanah.model.Dokumen;
import etanah.model.DokumenKewangan;
import etanah.model.FolderDokumen;
import etanah.model.InfoAudit;
import etanah.model.KodJenisPengenalan;
import etanah.model.KodNegeri;
import etanah.model.KodPenyerah;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.report.ReportUtil;

public interface UrusanKaunter extends Serializable{
	
	// the position of this inside Session's cache (workdata)
	public void setPosition(int pos);
	
	public int getPosition();
	
	/**
	 * Kumpulan Urusan to determine printing of receipts. Every kumpulan will being group
	 * together in one receipt
	 * @return
	 */
	public String getKumpulanUrusan();
	
	public void setKumpulanUrusan(String kumpulan);

	public void setKodUrusan(String kodUrusan);
	
	public String getKodUrusan();
	
	public String getNamaUrusan();
	
    /**
     * Untuk Permohonan ialah ID Permohonan. Untuk Carian, ID Carian dan lain2.
     * @param noRujukan
     */
    public void setNoRujukan(String noRujukan);
    
    public String getNoRujukan();
    
    /**
     * Used to link Transaksi.permohonan (special case for permohonan)
     * @return
     */
    public Permohonan getPermohonan();
	
	// dokumen 
    public List<DokumenValue> getSenaraiDokumenSerahan();
    
    public List<DokumenValue> getSenaraiDokumenTamb();
    
    // hakmilik
    public List<String> getSenaraiIdHakmilik();
    
    // return list of transactions from this group
    public List<TransaksiValue> getSenaraiTransaksi();
    
    /**
     *  Save urusan. DO NOT start transaction or commit transaction in this code because
     *  it will be called from the caller.
     * @param kodNegeri
     * @param pengguna
     * @param resit
     * @param idKumpulan
     * @param tuturan turutan dalam kumpulan
     * @param idPenyerah
     * @param penyerahKod
     * @param penyerahNoPengenalan
     * @param penyerahJenisPengenalan
     * @param penyerahNama
     * @param penyerahAlamat1
     * @param penyerahAlamat2
     * @param penyerahAlamat3
     * @param penyerahAlamat4
     * @param penyerahPoskod
     * @param penyerahNegeri
     * @param penyerahEmail
     * @param penyerahNoTelefon
     * @param namaPenyampai
     * @param noPengenalanPenyampai
     * @param noTelPenyampai
     * @param ia
     * @return urusan as well as other other urusan resulted from this urusan
     */
    public List<? extends UrusanKaunter> doSave(String kodNegeri, Pengguna pengguna, Dokumen resit,
            String idKumpulan, int turutan,
            // PENYERAH
            String idPenyerah, KodPenyerah penyerahKod,
            String penyerahNoPengenalan, KodJenisPengenalan penyerahJenisPengenalan,
            String penyerahNama, String penyerahAlamat1,
            String penyerahAlamat2, String penyerahAlamat3, String penyerahAlamat4, 
            String penyerahPoskod, KodNegeri penyerahNegeri, 
            String penyerahEmail, String penyerahNoTelefon, String namaPenyampai, String noPengenalanPenyampai,
            String noTelPenyampai,InfoAudit ia);   
    

    /**
     * Operation to do after commit, usually report generation which only can be done
     * after data has been commited.
     */
    public void doAfterSave(ReportUtil reportUtil, DokumenKewangan dk, InfoAudit ia);
    
    /**
     * The ActionBean that will be used to edit this Permohonan. Will 
     *  be called from TerimaBayaran and will be passed selectedItem of the position
     *  to be edit, therefore the ActionBean must implement setSelectedItem(int)
     * @return
     */
    public Class<? extends ActionBean> getEditActionBean();
	
    /**
     * Dokumen untuk dicetak oleh SPOC untuk urusan ini. Invoked after doAfterSave() method, so can be
     * prepopulated in that method.
     * @return
     */
    public Dokumen getCetakan();
    
    public boolean isEditable();
    
    /** 
     * flag if this urusan need to initiate a workflow
     * 
     * @return true if task need to be initiated for this
     */
    public boolean hasToInitiateWorkflow();
	
}
