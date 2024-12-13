package etanah.model.etapp;

import etanah.model.Hakmilik;
import etanah.model.InfoAudit;
import etanah.model.KodCawangan;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

@Entity
@Table(name = "TBLINTPPTMAKLUMATPENGAMBILAN")
@SequenceGenerator(name = "SEQ_TBLINTPPTMAKPNGMBLN", sequenceName = "SEQ_TBLINTPPTMAKPNGMBLN")
public class TBLINTPPTMAKLUMATPENGAMBILAN implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_TBLINTPPTMAKPNGMBLN")
    @Column(name = "ID_TBLPGMBLN")
    private Long idTblPgmbln;
    @Column(name = "NO_FAIL_JKPTG")
    private String noFailJKPTG;
    @Column(name = "TARIKH_PERMOHONAN")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date tarikhPermohonan;
    @Column(name = "NAMA_KEMENTERIAN")
    private String namaKementerian;
    @Column(name = "TUJUAN_DALAM_ENGLISH")
    private String tujuanDlmEnglish;
    @Column(name = "TUJUAN")
    private String tujuan;
    @Column(name = "KOD_NEGERI_PENGAMBILAN")
    private String kodNegeriPengambilan;
    @Column(name = "NAMA_NEGERI_PENGAMBILAN")
    private String namaNegeriPengambilan;
    @Column(name = "KOD_DAERAH_PENGAMBILAN")
    private String kodDaerahPengambilan;
    @Column(name = "NAMA_DAERAH_PENGAMBILAN")
    private String namaDaerahPengambilan;
    @Column(name = "JENIS_PENGAMBILAN")
    private String jenisPengambilan;
    @Column(name = "JENIS_PROJEK_PENGAMBILAN")
    private String jenisProjek_pengambilan;
    @Column(name = "NO_RUJUKAN_SURAT_KJP")
    private String noRujSuratKJP;
    @Column(name = "TARIKH_SURAT_KJP")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date tarikhSuratKJP;
    @Column(name = "NO_RUJUKAN_PTG")
    private String noRujukPTG;
    @Column(name = "NO_RUJUKAN_PTD")
    private String noRujukPTD;
    @Column(name = "ID_KEMENTERIAN_MYETAPP")
    private String idKementerian_myetapp;
    @Column(name = "NAMA_AGENSI")
    private String namaAgensi;
    @Column(name = "ID_AGENSI_MYETAPP")
    private String idAgensi_myetapp;
    @Column(name = "JENIS_PENGAMBILAN_SEGERA")
    private String jenisPengambilanSegera;
    @Column(name = "FLAG_PERMOHONAN_SEGERA")
    private String flagPemohonanSegera;
    @Column(name = "FLAG_PROSES")
    private String flagProses;
    @Column(name = "TURUTAN")
    private String turut;
    @Column(name = "ALAMAT1")
    private String alamat1;
    @Column(name = "ALAMAT2")
    private String alamat2;
    @Column(name = "ALAMAT3")
    private String alamat3;
    @Column(name = "ALAMAT4")
    private String alamat4;
    @Column(name = "POSKOD")
    private String poskod;
    @Column(name = "KOD_NEGERI")
    private String kodNegeri;
    @Column(name = "NO_TELEFON")
    private String noTel;
    @Column(name = "EMAIL")
    private String email;
    @Embedded
    private InfoAudit infoAudit;

//    @OneToMany (mappedBy = "etappPerintah", fetch = FetchType.LAZY)
//    private List<EtappHakmilik> listEtappHakmilik;
//    
//    
    public String getNoFailJKPTG() {
        return noFailJKPTG;
    }

    public void setNoFailJKPTG(String noFailJKPTG) {
        this.noFailJKPTG = noFailJKPTG;
    }

    public Date getTarikhPermohonan() {
        return tarikhPermohonan;
    }

    public void setTarikhPermohonan(Date tarikhPermohonan) {
        this.tarikhPermohonan = tarikhPermohonan;
    }

    public String getNamaKementerian() {
        return namaKementerian;
    }

    public Long getIdTblPgmbln() {
        return idTblPgmbln;
    }

    public void setIdTblPgmbln(Long idTblPgmbln) {
        this.idTblPgmbln = idTblPgmbln;
    }

    public void setNamaKementerian(String namaKementerian) {
        this.namaKementerian = namaKementerian;
    }

    public String getTujuanDlmEnglish() {
        return tujuanDlmEnglish;
    }

    public void setTujuanDlmEnglish(String tujuanDlmEnglish) {
        this.tujuanDlmEnglish = tujuanDlmEnglish;
    }

    public String getTujuan() {
        return tujuan;
    }

    public void setTujuan(String tujuan) {
        this.tujuan = tujuan;
    }

    public String getKodNegeriPengambilan() {
        return kodNegeriPengambilan;
    }

    public void setKodNegeriPengambilan(String kodNegeriPengambilan) {
        this.kodNegeriPengambilan = kodNegeriPengambilan;
    }

    public String getNamaNegeriPengambilan() {
        return namaNegeriPengambilan;
    }

    public void setNamaNegeriPengambilan(String namaNegeriPengambilan) {
        this.namaNegeriPengambilan = namaNegeriPengambilan;
    }

    public String getKodDaerahPengambilan() {
        return kodDaerahPengambilan;
    }

    public void setKodDaerahPengambilan(String kodDaerahPengambilan) {
        this.kodDaerahPengambilan = kodDaerahPengambilan;
    }

    public String getNamaDaerahPengambilan() {
        return namaDaerahPengambilan;
    }

    public void setNamaDaerahPengambilan(String namaDaerahPengambilan) {
        this.namaDaerahPengambilan = namaDaerahPengambilan;
    }

    public String getJenisPengambilan() {
        return jenisPengambilan;
    }

    public void setJenisPengambilan(String jenisPengambilan) {
        this.jenisPengambilan = jenisPengambilan;
    }

    public String getJenisProjek_pengambilan() {
        return jenisProjek_pengambilan;
    }

    public void setJenisProjek_pengambilan(String jenisProjek_pengambilan) {
        this.jenisProjek_pengambilan = jenisProjek_pengambilan;
    }

    public String getNoRujSuratKJP() {
        return noRujSuratKJP;
    }

    public void setNoRujSuratKJP(String noRujSuratKJP) {
        this.noRujSuratKJP = noRujSuratKJP;
    }

    public Date getTarikhSuratKJP() {
        return tarikhSuratKJP;
    }

    public void setTarikhSuratKJP(Date tarikhSuratKJP) {
        this.tarikhSuratKJP = tarikhSuratKJP;
    }

    public String getNoRujukPTG() {
        return noRujukPTG;
    }

    public void setNoRujukPTG(String noRujukPTG) {
        this.noRujukPTG = noRujukPTG;
    }

    public String getNoRujukPTD() {
        return noRujukPTD;
    }

    public void setNoRujukPTD(String noRujukPTD) {
        this.noRujukPTD = noRujukPTD;
    }

    public String getIdKementerian_myetapp() {
        return idKementerian_myetapp;
    }

    public void setIdKementerian_myetapp(String idKementerian_myetapp) {
        this.idKementerian_myetapp = idKementerian_myetapp;
    }

    public String getNamaAgensi() {
        return namaAgensi;
    }

    public void setNamaAgensi(String namaAgensi) {
        this.namaAgensi = namaAgensi;
    }

    public String getIdAgensi_myetapp() {
        return idAgensi_myetapp;
    }

    public void setIdAgensi_myetapp(String idAgensi_myetapp) {
        this.idAgensi_myetapp = idAgensi_myetapp;
    }

    public String getJenisPengambilanSegera() {
        return jenisPengambilanSegera;
    }

    public void setJenisPengambilanSegera(String jenisPengambilanSegera) {
        this.jenisPengambilanSegera = jenisPengambilanSegera;
    }

    public String getFlagPemohonanSegera() {
        return flagPemohonanSegera;
    }

    public void setFlagPemohonanSegera(String flagPemohonanSegera) {
        this.flagPemohonanSegera = flagPemohonanSegera;
    }

    public String getFlagProses() {
        return flagProses;
    }

    public void setFlagProses(String flagProses) {
        this.flagProses = flagProses;
    }

    public String getTurut() {
        return turut;
    }

    public void setTurut(String turut) {
        this.turut = turut;
    }

    public InfoAudit getInfoAudit() {
        return infoAudit;
    }

    public void setInfoAudit(InfoAudit infoAudit) {
        this.infoAudit = infoAudit;
    }

    public String getAlamat1() {
        return alamat1;
    }

    public void setAlamat1(String alamat1) {
        this.alamat1 = alamat1;
    }

    public String getAlamat2() {
        return alamat2;
    }

    public void setAlamat2(String alamat2) {
        this.alamat2 = alamat2;
    }

    public String getAlamat3() {
        return alamat3;
    }

    public void setAlamat3(String alamat3) {
        this.alamat3 = alamat3;
    }

    public String getAlamat4() {
        return alamat4;
    }

    public void setAlamat4(String alamat4) {
        this.alamat4 = alamat4;
    }

    public String getPoskod() {
        return poskod;
    }

    public void setPoskod(String poskod) {
        this.poskod = poskod;

    }

    public String getNoTel() {
        return noTel;
    }

    public void setNoTel(String noTel) {
        this.noTel = noTel;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getKodNegeri() {
        return kodNegeri;
    }

    public void setKodNegeri(String kodNegeri) {
        this.kodNegeri = kodNegeri;
    }
    
    
}
