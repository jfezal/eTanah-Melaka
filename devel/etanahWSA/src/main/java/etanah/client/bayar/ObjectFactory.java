
package etanah.client.bayar;

import java.math.BigDecimal;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the etanah.client.bayar package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _StatusLoginNama_QNAME = new QName("http://ws.portal.view.etanah", "nama");
    private final static QName _StatusLoginIdPengguna_QNAME = new QName("http://ws.portal.view.etanah", "idPengguna");
    private final static QName _StatusLoginSts_QNAME = new QName("http://ws.portal.view.etanah", "sts");
    private final static QName _SejarahTransaksiPortalJenistrans_QNAME = new QName("http://ws.portal.view.etanah", "jenistrans");
    private final static QName _SejarahTransaksiPortalNofpx_QNAME = new QName("http://ws.portal.view.etanah", "nofpx");
    private final static QName _SejarahTransaksiPortalAmaun_QNAME = new QName("http://ws.portal.view.etanah", "amaun");
    private final static QName _SejarahTransaksiPortalIdHakmilik_QNAME = new QName("http://ws.portal.view.etanah", "idHakmilik");
    private final static QName _SejarahTransaksiPortalTarikhTrasaksi_QNAME = new QName("http://ws.portal.view.etanah", "tarikhTrasaksi");
    private final static QName _SejarahTransaksiPortalTarikhResit_QNAME = new QName("http://ws.portal.view.etanah", "tarikhResit");
    private final static QName _SejarahTransaksiPortalStatus_QNAME = new QName("http://ws.portal.view.etanah", "status");
    private final static QName _SejarahTransaksiPortalIdkewdok_QNAME = new QName("http://ws.portal.view.etanah", "idkewdok");
    private final static QName _KonfigurasiValue_QNAME = new QName("http://ws.portal.view.etanah", "value");
    private final static QName _KonfigurasiMaxAmout_QNAME = new QName("http://ws.portal.view.etanah", "maxAmout");
    private final static QName _KonfigurasiMerchantCode_QNAME = new QName("http://ws.portal.view.etanah", "merchantCode");
    private final static QName _KonfigurasiEncryptionKey_QNAME = new QName("http://ws.portal.view.etanah", "encryptionKey");
    private final static QName _KonfigurasiApiKey_QNAME = new QName("http://ws.portal.view.etanah", "api_key");
    private final static QName _DokumenCarianPersendirianTarikh_QNAME = new QName("http://ws.portal.view.etanah", "tarikh");
    private final static QName _DokumenCarianPersendirianBilPaparan_QNAME = new QName("http://ws.portal.view.etanah", "bilPaparan");
    private final static QName _DokumenCarianPersendirianIdPortalTransaksi_QNAME = new QName("http://ws.portal.view.etanah", "idPortalTransaksi");
    private final static QName _AccountInfoAlamat_QNAME = new QName("http://ws.portal.view.etanah", "alamat");
    private final static QName _AccountInfoKodRemisyen_QNAME = new QName("http://ws.portal.view.etanah", "kodRemisyen");
    private final static QName _AccountInfoKodCukaiSemasa_QNAME = new QName("http://ws.portal.view.etanah", "kodCukaiSemasa");
    private final static QName _AccountInfoNamaPemilik_QNAME = new QName("http://ws.portal.view.etanah", "namaPemilik");
    private final static QName _AccountInfoKodNotis6A_QNAME = new QName("http://ws.portal.view.etanah", "kodNotis6a");
    private final static QName _AccountInfoNotis6A_QNAME = new QName("http://ws.portal.view.etanah", "notis6a");
    private final static QName _AccountInfoLokasiTanah_QNAME = new QName("http://ws.portal.view.etanah", "lokasiTanah");
    private final static QName _AccountInfoJumlahBayaran_QNAME = new QName("http://ws.portal.view.etanah", "jumlahBayaran");
    private final static QName _AccountInfoStatusBayaran_QNAME = new QName("http://ws.portal.view.etanah", "statusBayaran");
    private final static QName _AccountInfoJenisPegangan_QNAME = new QName("http://ws.portal.view.etanah", "jenisPegangan");
    private final static QName _AccountInfoRemisyen_QNAME = new QName("http://ws.portal.view.etanah", "remisyen");
    private final static QName _AccountInfoNoHakmilik_QNAME = new QName("http://ws.portal.view.etanah", "noHakmilik");
    private final static QName _AccountInfoKegunaan_QNAME = new QName("http://ws.portal.view.etanah", "kegunaan");
    private final static QName _AccountInfoDaerah_QNAME = new QName("http://ws.portal.view.etanah", "daerah");
    private final static QName _AccountInfoKeluasan_QNAME = new QName("http://ws.portal.view.etanah", "keluasan");
    private final static QName _AccountInfoListSejarahCukai_QNAME = new QName("http://ws.portal.view.etanah", "listSejarahCukai");
    private final static QName _AccountInfoTarikhBayaran_QNAME = new QName("http://ws.portal.view.etanah", "tarikhBayaran");
    private final static QName _AccountInfoDendaLewatSemasa_QNAME = new QName("http://ws.portal.view.etanah", "dendaLewatSemasa");
    private final static QName _AccountInfoNoAkaun_QNAME = new QName("http://ws.portal.view.etanah", "noAkaun");
    private final static QName _AccountInfoNoResit_QNAME = new QName("http://ws.portal.view.etanah", "noResit");
    private final static QName _AccountInfoTunggakanTahun_QNAME = new QName("http://ws.portal.view.etanah", "tunggakanTahun");
    private final static QName _AccountInfoJumlahCukaiSemasa_QNAME = new QName("http://ws.portal.view.etanah", "jumlahCukaiSemasa");
    private final static QName _AccountInfoJumlahTunggakan_QNAME = new QName("http://ws.portal.view.etanah", "jumlahTunggakan");
    private final static QName _AccountInfoIcPemilik_QNAME = new QName("http://ws.portal.view.etanah", "icPemilik");
    private final static QName _AccountInfoKategori_QNAME = new QName("http://ws.portal.view.etanah", "kategori");
    private final static QName _AccountInfoKodCarianpersendirian_QNAME = new QName("http://ws.portal.view.etanah", "kodCarianpersendirian");
    private final static QName _AccountInfoNamaPembayar_QNAME = new QName("http://ws.portal.view.etanah", "namaPembayar");
    private final static QName _AccountInfoKodTunggakanDendaLewat_QNAME = new QName("http://ws.portal.view.etanah", "kodTunggakanDendaLewat");
    private final static QName _AccountInfoKodTunggakanCukai_QNAME = new QName("http://ws.portal.view.etanah", "kodTunggakanCukai");
    private final static QName _AccountInfoLokaliti_QNAME = new QName("http://ws.portal.view.etanah", "lokaliti");
    private final static QName _AccountInfoTunggakanCukaiParit_QNAME = new QName("http://ws.portal.view.etanah", "tunggakanCukaiParit");
    private final static QName _AccountInfoTunggakanCukaiTanah_QNAME = new QName("http://ws.portal.view.etanah", "tunggakanCukaiTanah");
    private final static QName _AccountInfoKodCawValue_QNAME = new QName("http://ws.portal.view.etanah", "kodCawValue");
    private final static QName _AccountInfoLebihan_QNAME = new QName("http://ws.portal.view.etanah", "lebihan");
    private final static QName _AccountInfoCukaiSemasaParit_QNAME = new QName("http://ws.portal.view.etanah", "cukaiSemasaParit");
    private final static QName _AccountInfoLokasiBayar_QNAME = new QName("http://ws.portal.view.etanah", "lokasiBayar");
    private final static QName _AccountInfoBakiAkaun_QNAME = new QName("http://ws.portal.view.etanah", "bakiAkaun");
    private final static QName _AccountInfoKreditDebit_QNAME = new QName("http://ws.portal.view.etanah", "kreditDebit");
    private final static QName _AccountInfoCukaiSemasaTanah_QNAME = new QName("http://ws.portal.view.etanah", "cukaiSemasaTanah");
    private final static QName _AccountInfoAkaunBaru_QNAME = new QName("http://ws.portal.view.etanah", "akaunBaru");
    private final static QName _AccountInfoIcPembayar_QNAME = new QName("http://ws.portal.view.etanah", "icPembayar");
    private final static QName _AccountInfoKodCukaiSemasaParit_QNAME = new QName("http://ws.portal.view.etanah", "kodCukaiSemasaParit");
    private final static QName _AccountInfoKodDendaLewat_QNAME = new QName("http://ws.portal.view.etanah", "kodDendaLewat");
    private final static QName _AccountInfoBandarpekanmukim_QNAME = new QName("http://ws.portal.view.etanah", "bandarpekanmukim");
    private final static QName _AccountInfoKodKreditDebit_QNAME = new QName("http://ws.portal.view.etanah", "kodKreditDebit");
    private final static QName _AccountInfoJenisHakmilik_QNAME = new QName("http://ws.portal.view.etanah", "jenisHakmilik");
    private final static QName _AccountInfoSyaratNyata_QNAME = new QName("http://ws.portal.view.etanah", "syaratNyata");
    private final static QName _AccountInfoTahun_QNAME = new QName("http://ws.portal.view.etanah", "tahun");
    private final static QName _AccountInfoNoLot_QNAME = new QName("http://ws.portal.view.etanah", "noLot");
    private final static QName _AccountInfoKodCaw_QNAME = new QName("http://ws.portal.view.etanah", "kodCaw");
    private final static QName _AccountInfoTunggakanDendaLewat_QNAME = new QName("http://ws.portal.view.etanah", "tunggakanDendaLewat");
    private final static QName _AccountInfoKodTunggakanCukaiParit_QNAME = new QName("http://ws.portal.view.etanah", "kodTunggakanCukaiParit");
    private final static QName _AccountInfoStatusAkaun_QNAME = new QName("http://ws.portal.view.etanah", "statusAkaun");
    private final static QName _SejarahCukaiKaedahBayaran_QNAME = new QName("http://ws.portal.view.etanah", "kaedahBayaran");
    private final static QName _SejarahCukaiPusatKutipan_QNAME = new QName("http://ws.portal.view.etanah", "pusatKutipan");
    private final static QName _AkaunFormNoAccount_QNAME = new QName("http://ws.portal.view.etanah", "noAccount");
    private final static QName _AkaunFormAmaunt_QNAME = new QName("http://ws.portal.view.etanah", "amaunt");
    private final static QName _UtilKodKod_QNAME = new QName("http://ws.portal.view.etanah", "kod");
    private final static QName _FormDaftarPenggunaTrhLogin_QNAME = new QName("http://ws.portal.view.etanah", "trhLogin");
    private final static QName _FormDaftarPenggunaNoTel_QNAME = new QName("http://ws.portal.view.etanah", "noTel");
    private final static QName _FormDaftarPenggunaNoKp_QNAME = new QName("http://ws.portal.view.etanah", "noKp");
    private final static QName _FormDaftarPenggunaPasswd_QNAME = new QName("http://ws.portal.view.etanah", "passwd");
    private final static QName _FormDaftarPenggunaEmail_QNAME = new QName("http://ws.portal.view.etanah", "email");
    private final static QName _FormDaftarPenggunaIdPguna_QNAME = new QName("http://ws.portal.view.etanah", "idPguna");
    private final static QName _DokumenInfoDocType_QNAME = new QName("http://ws.dokumen.view.etanah", "docType");
    private final static QName _DokumenInfoBytes_QNAME = new QName("http://ws.dokumen.view.etanah", "bytes");
    private final static QName _DokumenInfoFileName_QNAME = new QName("http://ws.dokumen.view.etanah", "fileName");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: etanah.client.bayar
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link StatusLogin }
     * 
     */
    public StatusLogin createStatusLogin() {
        return new StatusLogin();
    }

    /**
     * Create an instance of {@link DokumenCarianPersendirian }
     * 
     */
    public DokumenCarianPersendirian createDokumenCarianPersendirian() {
        return new DokumenCarianPersendirian();
    }

    /**
     * Create an instance of {@link SenaraiDokumenResponse }
     * 
     */
    public SenaraiDokumenResponse createSenaraiDokumenResponse() {
        return new SenaraiDokumenResponse();
    }

    /**
     * Create an instance of {@link ArrayOfSejarahTransaksiPortal }
     * 
     */
    public ArrayOfSejarahTransaksiPortal createArrayOfSejarahTransaksiPortal() {
        return new ArrayOfSejarahTransaksiPortal();
    }

    /**
     * Create an instance of {@link AkaunForm }
     * 
     */
    public AkaunForm createAkaunForm() {
        return new AkaunForm();
    }

    /**
     * Create an instance of {@link SejarahTransaksiResponse }
     * 
     */
    public SejarahTransaksiResponse createSejarahTransaksiResponse() {
        return new SejarahTransaksiResponse();
    }

    /**
     * Create an instance of {@link LupaKataLaluanResponse }
     * 
     */
    public LupaKataLaluanResponse createLupaKataLaluanResponse() {
        return new LupaKataLaluanResponse();
    }

    /**
     * Create an instance of {@link UpdateTransaction }
     * 
     */
    public UpdateTransaction createUpdateTransaction() {
        return new UpdateTransaction();
    }

    /**
     * Create an instance of {@link ArrayOfAkaunForm }
     * 
     */
    public ArrayOfAkaunForm createArrayOfAkaunForm() {
        return new ArrayOfAkaunForm();
    }

    /**
     * Create an instance of {@link Config }
     * 
     */
    public Config createConfig() {
        return new Config();
    }

    /**
     * Create an instance of {@link DaftarPenggunaBaru }
     * 
     */
    public DaftarPenggunaBaru createDaftarPenggunaBaru() {
        return new DaftarPenggunaBaru();
    }

    /**
     * Create an instance of {@link DokumenInfo }
     * 
     */
    public DokumenInfo createDokumenInfo() {
        return new DokumenInfo();
    }

    /**
     * Create an instance of {@link ListKodHakmilikResponse }
     * 
     */
    public ListKodHakmilikResponse createListKodHakmilikResponse() {
        return new ListKodHakmilikResponse();
    }

    /**
     * Create an instance of {@link SejarahTransaksiPortal }
     * 
     */
    public SejarahTransaksiPortal createSejarahTransaksiPortal() {
        return new SejarahTransaksiPortal();
    }

    /**
     * Create an instance of {@link ListKodHakmilik }
     * 
     */
    public ListKodHakmilik createListKodHakmilik() {
        return new ListKodHakmilik();
    }

    /**
     * Create an instance of {@link UpdateUserAccountResponse }
     * 
     */
    public UpdateUserAccountResponse createUpdateUserAccountResponse() {
        return new UpdateUserAccountResponse();
    }

    /**
     * Create an instance of {@link LogMasukResponse }
     * 
     */
    public LogMasukResponse createLogMasukResponse() {
        return new LogMasukResponse();
    }

    /**
     * Create an instance of {@link ProfilPengguna }
     * 
     */
    public ProfilPengguna createProfilPengguna() {
        return new ProfilPengguna();
    }

    /**
     * Create an instance of {@link UpdateTransactionResponse }
     * 
     */
    public UpdateTransactionResponse createUpdateTransactionResponse() {
        return new UpdateTransactionResponse();
    }

    /**
     * Create an instance of {@link LupaKataLaluan }
     * 
     */
    public LupaKataLaluan createLupaKataLaluan() {
        return new LupaKataLaluan();
    }

    /**
     * Create an instance of {@link DaftarPenggunaBaruResponse }
     * 
     */
    public DaftarPenggunaBaruResponse createDaftarPenggunaBaruResponse() {
        return new DaftarPenggunaBaruResponse();
    }

    /**
     * Create an instance of {@link SejarahCukai }
     * 
     */
    public SejarahCukai createSejarahCukai() {
        return new SejarahCukai();
    }

    /**
     * Create an instance of {@link AccountInfo }
     * 
     */
    public AccountInfo createAccountInfo() {
        return new AccountInfo();
    }

    /**
     * Create an instance of {@link UpdateAccountPukal }
     * 
     */
    public UpdateAccountPukal createUpdateAccountPukal() {
        return new UpdateAccountPukal();
    }

    /**
     * Create an instance of {@link ProfilPenggunaResponse }
     * 
     */
    public ProfilPenggunaResponse createProfilPenggunaResponse() {
        return new ProfilPenggunaResponse();
    }

    /**
     * Create an instance of {@link ListKodDaerah }
     * 
     */
    public ListKodDaerah createListKodDaerah() {
        return new ListKodDaerah();
    }

    /**
     * Create an instance of {@link SenaraiDokumen }
     * 
     */
    public SenaraiDokumen createSenaraiDokumen() {
        return new SenaraiDokumen();
    }

    /**
     * Create an instance of {@link ArrayOfAccountInfo }
     * 
     */
    public ArrayOfAccountInfo createArrayOfAccountInfo() {
        return new ArrayOfAccountInfo();
    }

    /**
     * Create an instance of {@link Muatturundokumen }
     * 
     */
    public Muatturundokumen createMuatturundokumen() {
        return new Muatturundokumen();
    }

    /**
     * Create an instance of {@link MuatturundokumenResponse }
     * 
     */
    public MuatturundokumenResponse createMuatturundokumenResponse() {
        return new MuatturundokumenResponse();
    }

    /**
     * Create an instance of {@link ListKodDaerahResponse }
     * 
     */
    public ListKodDaerahResponse createListKodDaerahResponse() {
        return new ListKodDaerahResponse();
    }

    /**
     * Create an instance of {@link ArrayOfSejarahCukai }
     * 
     */
    public ArrayOfSejarahCukai createArrayOfSejarahCukai() {
        return new ArrayOfSejarahCukai();
    }

    /**
     * Create an instance of {@link LogMasuk }
     * 
     */
    public LogMasuk createLogMasuk() {
        return new LogMasuk();
    }

    /**
     * Create an instance of {@link CheckAccountResponse }
     * 
     */
    public CheckAccountResponse createCheckAccountResponse() {
        return new CheckAccountResponse();
    }

    /**
     * Create an instance of {@link UpdateAccountPukalResponse }
     * 
     */
    public UpdateAccountPukalResponse createUpdateAccountPukalResponse() {
        return new UpdateAccountPukalResponse();
    }

    /**
     * Create an instance of {@link ListKodMukim }
     * 
     */
    public ListKodMukim createListKodMukim() {
        return new ListKodMukim();
    }

    /**
     * Create an instance of {@link CheckAccount }
     * 
     */
    public CheckAccount createCheckAccount() {
        return new CheckAccount();
    }

    /**
     * Create an instance of {@link FormDaftarPengguna }
     * 
     */
    public FormDaftarPengguna createFormDaftarPengguna() {
        return new FormDaftarPengguna();
    }

    /**
     * Create an instance of {@link UpdateCarianAccountResponse }
     * 
     */
    public UpdateCarianAccountResponse createUpdateCarianAccountResponse() {
        return new UpdateCarianAccountResponse();
    }

    /**
     * Create an instance of {@link ConfigResponse }
     * 
     */
    public ConfigResponse createConfigResponse() {
        return new ConfigResponse();
    }

    /**
     * Create an instance of {@link Konfigurasi }
     * 
     */
    public Konfigurasi createKonfigurasi() {
        return new Konfigurasi();
    }

    /**
     * Create an instance of {@link UpdateUserAccount }
     * 
     */
    public UpdateUserAccount createUpdateUserAccount() {
        return new UpdateUserAccount();
    }

    /**
     * Create an instance of {@link ListKodMukimResponse }
     * 
     */
    public ListKodMukimResponse createListKodMukimResponse() {
        return new ListKodMukimResponse();
    }

    /**
     * Create an instance of {@link SejarahTransaksi }
     * 
     */
    public SejarahTransaksi createSejarahTransaksi() {
        return new SejarahTransaksi();
    }

    /**
     * Create an instance of {@link ArrayOfDokumenCarianPersendirian }
     * 
     */
    public ArrayOfDokumenCarianPersendirian createArrayOfDokumenCarianPersendirian() {
        return new ArrayOfDokumenCarianPersendirian();
    }

    /**
     * Create an instance of {@link ArrayOfUtilKod }
     * 
     */
    public ArrayOfUtilKod createArrayOfUtilKod() {
        return new ArrayOfUtilKod();
    }

    /**
     * Create an instance of {@link UtilKod }
     * 
     */
    public UtilKod createUtilKod() {
        return new UtilKod();
    }

    /**
     * Create an instance of {@link UpdateCarianAccount }
     * 
     */
    public UpdateCarianAccount createUpdateCarianAccount() {
        return new UpdateCarianAccount();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.portal.view.etanah", name = "nama", scope = StatusLogin.class)
    public JAXBElement<String> createStatusLoginNama(String value) {
        return new JAXBElement<String>(_StatusLoginNama_QNAME, String.class, StatusLogin.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.portal.view.etanah", name = "idPengguna", scope = StatusLogin.class)
    public JAXBElement<String> createStatusLoginIdPengguna(String value) {
        return new JAXBElement<String>(_StatusLoginIdPengguna_QNAME, String.class, StatusLogin.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.portal.view.etanah", name = "sts", scope = StatusLogin.class)
    public JAXBElement<String> createStatusLoginSts(String value) {
        return new JAXBElement<String>(_StatusLoginSts_QNAME, String.class, StatusLogin.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.portal.view.etanah", name = "jenistrans", scope = SejarahTransaksiPortal.class)
    public JAXBElement<String> createSejarahTransaksiPortalJenistrans(String value) {
        return new JAXBElement<String>(_SejarahTransaksiPortalJenistrans_QNAME, String.class, SejarahTransaksiPortal.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.portal.view.etanah", name = "nofpx", scope = SejarahTransaksiPortal.class)
    public JAXBElement<String> createSejarahTransaksiPortalNofpx(String value) {
        return new JAXBElement<String>(_SejarahTransaksiPortalNofpx_QNAME, String.class, SejarahTransaksiPortal.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.portal.view.etanah", name = "amaun", scope = SejarahTransaksiPortal.class)
    public JAXBElement<BigDecimal> createSejarahTransaksiPortalAmaun(BigDecimal value) {
        return new JAXBElement<BigDecimal>(_SejarahTransaksiPortalAmaun_QNAME, BigDecimal.class, SejarahTransaksiPortal.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.portal.view.etanah", name = "idHakmilik", scope = SejarahTransaksiPortal.class)
    public JAXBElement<String> createSejarahTransaksiPortalIdHakmilik(String value) {
        return new JAXBElement<String>(_SejarahTransaksiPortalIdHakmilik_QNAME, String.class, SejarahTransaksiPortal.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.portal.view.etanah", name = "tarikhTrasaksi", scope = SejarahTransaksiPortal.class)
    public JAXBElement<String> createSejarahTransaksiPortalTarikhTrasaksi(String value) {
        return new JAXBElement<String>(_SejarahTransaksiPortalTarikhTrasaksi_QNAME, String.class, SejarahTransaksiPortal.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.portal.view.etanah", name = "tarikhResit", scope = SejarahTransaksiPortal.class)
    public JAXBElement<String> createSejarahTransaksiPortalTarikhResit(String value) {
        return new JAXBElement<String>(_SejarahTransaksiPortalTarikhResit_QNAME, String.class, SejarahTransaksiPortal.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.portal.view.etanah", name = "status", scope = SejarahTransaksiPortal.class)
    public JAXBElement<String> createSejarahTransaksiPortalStatus(String value) {
        return new JAXBElement<String>(_SejarahTransaksiPortalStatus_QNAME, String.class, SejarahTransaksiPortal.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.portal.view.etanah", name = "idkewdok", scope = SejarahTransaksiPortal.class)
    public JAXBElement<String> createSejarahTransaksiPortalIdkewdok(String value) {
        return new JAXBElement<String>(_SejarahTransaksiPortalIdkewdok_QNAME, String.class, SejarahTransaksiPortal.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.portal.view.etanah", name = "value", scope = Konfigurasi.class)
    public JAXBElement<String> createKonfigurasiValue(String value) {
        return new JAXBElement<String>(_KonfigurasiValue_QNAME, String.class, Konfigurasi.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.portal.view.etanah", name = "maxAmout", scope = Konfigurasi.class)
    public JAXBElement<BigDecimal> createKonfigurasiMaxAmout(BigDecimal value) {
        return new JAXBElement<BigDecimal>(_KonfigurasiMaxAmout_QNAME, BigDecimal.class, Konfigurasi.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.portal.view.etanah", name = "merchantCode", scope = Konfigurasi.class)
    public JAXBElement<String> createKonfigurasiMerchantCode(String value) {
        return new JAXBElement<String>(_KonfigurasiMerchantCode_QNAME, String.class, Konfigurasi.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.portal.view.etanah", name = "encryptionKey", scope = Konfigurasi.class)
    public JAXBElement<String> createKonfigurasiEncryptionKey(String value) {
        return new JAXBElement<String>(_KonfigurasiEncryptionKey_QNAME, String.class, Konfigurasi.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.portal.view.etanah", name = "api_key", scope = Konfigurasi.class)
    public JAXBElement<String> createKonfigurasiApiKey(String value) {
        return new JAXBElement<String>(_KonfigurasiApiKey_QNAME, String.class, Konfigurasi.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.portal.view.etanah", name = "tarikh", scope = DokumenCarianPersendirian.class)
    public JAXBElement<String> createDokumenCarianPersendirianTarikh(String value) {
        return new JAXBElement<String>(_DokumenCarianPersendirianTarikh_QNAME, String.class, DokumenCarianPersendirian.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Integer }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.portal.view.etanah", name = "bilPaparan", scope = DokumenCarianPersendirian.class)
    public JAXBElement<Integer> createDokumenCarianPersendirianBilPaparan(Integer value) {
        return new JAXBElement<Integer>(_DokumenCarianPersendirianBilPaparan_QNAME, Integer.class, DokumenCarianPersendirian.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Long }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.portal.view.etanah", name = "idPortalTransaksi", scope = DokumenCarianPersendirian.class)
    public JAXBElement<Long> createDokumenCarianPersendirianIdPortalTransaksi(Long value) {
        return new JAXBElement<Long>(_DokumenCarianPersendirianIdPortalTransaksi_QNAME, Long.class, DokumenCarianPersendirian.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.portal.view.etanah", name = "idHakmilik", scope = DokumenCarianPersendirian.class)
    public JAXBElement<String> createDokumenCarianPersendirianIdHakmilik(String value) {
        return new JAXBElement<String>(_SejarahTransaksiPortalIdHakmilik_QNAME, String.class, DokumenCarianPersendirian.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.portal.view.etanah", name = "alamat", scope = AccountInfo.class)
    public JAXBElement<String> createAccountInfoAlamat(String value) {
        return new JAXBElement<String>(_AccountInfoAlamat_QNAME, String.class, AccountInfo.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.portal.view.etanah", name = "kodRemisyen", scope = AccountInfo.class)
    public JAXBElement<String> createAccountInfoKodRemisyen(String value) {
        return new JAXBElement<String>(_AccountInfoKodRemisyen_QNAME, String.class, AccountInfo.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.portal.view.etanah", name = "kodCukaiSemasa", scope = AccountInfo.class)
    public JAXBElement<String> createAccountInfoKodCukaiSemasa(String value) {
        return new JAXBElement<String>(_AccountInfoKodCukaiSemasa_QNAME, String.class, AccountInfo.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.portal.view.etanah", name = "namaPemilik", scope = AccountInfo.class)
    public JAXBElement<String> createAccountInfoNamaPemilik(String value) {
        return new JAXBElement<String>(_AccountInfoNamaPemilik_QNAME, String.class, AccountInfo.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.portal.view.etanah", name = "kodNotis6a", scope = AccountInfo.class)
    public JAXBElement<String> createAccountInfoKodNotis6A(String value) {
        return new JAXBElement<String>(_AccountInfoKodNotis6A_QNAME, String.class, AccountInfo.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.portal.view.etanah", name = "notis6a", scope = AccountInfo.class)
    public JAXBElement<BigDecimal> createAccountInfoNotis6A(BigDecimal value) {
        return new JAXBElement<BigDecimal>(_AccountInfoNotis6A_QNAME, BigDecimal.class, AccountInfo.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.portal.view.etanah", name = "lokasiTanah", scope = AccountInfo.class)
    public JAXBElement<String> createAccountInfoLokasiTanah(String value) {
        return new JAXBElement<String>(_AccountInfoLokasiTanah_QNAME, String.class, AccountInfo.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.portal.view.etanah", name = "jumlahBayaran", scope = AccountInfo.class)
    public JAXBElement<BigDecimal> createAccountInfoJumlahBayaran(BigDecimal value) {
        return new JAXBElement<BigDecimal>(_AccountInfoJumlahBayaran_QNAME, BigDecimal.class, AccountInfo.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.portal.view.etanah", name = "statusBayaran", scope = AccountInfo.class)
    public JAXBElement<String> createAccountInfoStatusBayaran(String value) {
        return new JAXBElement<String>(_AccountInfoStatusBayaran_QNAME, String.class, AccountInfo.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.portal.view.etanah", name = "jenisPegangan", scope = AccountInfo.class)
    public JAXBElement<String> createAccountInfoJenisPegangan(String value) {
        return new JAXBElement<String>(_AccountInfoJenisPegangan_QNAME, String.class, AccountInfo.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.portal.view.etanah", name = "remisyen", scope = AccountInfo.class)
    public JAXBElement<BigDecimal> createAccountInfoRemisyen(BigDecimal value) {
        return new JAXBElement<BigDecimal>(_AccountInfoRemisyen_QNAME, BigDecimal.class, AccountInfo.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.portal.view.etanah", name = "noHakmilik", scope = AccountInfo.class)
    public JAXBElement<String> createAccountInfoNoHakmilik(String value) {
        return new JAXBElement<String>(_AccountInfoNoHakmilik_QNAME, String.class, AccountInfo.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.portal.view.etanah", name = "kegunaan", scope = AccountInfo.class)
    public JAXBElement<String> createAccountInfoKegunaan(String value) {
        return new JAXBElement<String>(_AccountInfoKegunaan_QNAME, String.class, AccountInfo.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.portal.view.etanah", name = "daerah", scope = AccountInfo.class)
    public JAXBElement<String> createAccountInfoDaerah(String value) {
        return new JAXBElement<String>(_AccountInfoDaerah_QNAME, String.class, AccountInfo.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.portal.view.etanah", name = "keluasan", scope = AccountInfo.class)
    public JAXBElement<String> createAccountInfoKeluasan(String value) {
        return new JAXBElement<String>(_AccountInfoKeluasan_QNAME, String.class, AccountInfo.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.portal.view.etanah", name = "idHakmilik", scope = AccountInfo.class)
    public JAXBElement<String> createAccountInfoIdHakmilik(String value) {
        return new JAXBElement<String>(_SejarahTransaksiPortalIdHakmilik_QNAME, String.class, AccountInfo.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ArrayOfSejarahCukai }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.portal.view.etanah", name = "listSejarahCukai", scope = AccountInfo.class)
    public JAXBElement<ArrayOfSejarahCukai> createAccountInfoListSejarahCukai(ArrayOfSejarahCukai value) {
        return new JAXBElement<ArrayOfSejarahCukai>(_AccountInfoListSejarahCukai_QNAME, ArrayOfSejarahCukai.class, AccountInfo.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.portal.view.etanah", name = "tarikhBayaran", scope = AccountInfo.class)
    public JAXBElement<String> createAccountInfoTarikhBayaran(String value) {
        return new JAXBElement<String>(_AccountInfoTarikhBayaran_QNAME, String.class, AccountInfo.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.portal.view.etanah", name = "dendaLewatSemasa", scope = AccountInfo.class)
    public JAXBElement<BigDecimal> createAccountInfoDendaLewatSemasa(BigDecimal value) {
        return new JAXBElement<BigDecimal>(_AccountInfoDendaLewatSemasa_QNAME, BigDecimal.class, AccountInfo.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.portal.view.etanah", name = "noAkaun", scope = AccountInfo.class)
    public JAXBElement<String> createAccountInfoNoAkaun(String value) {
        return new JAXBElement<String>(_AccountInfoNoAkaun_QNAME, String.class, AccountInfo.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.portal.view.etanah", name = "noResit", scope = AccountInfo.class)
    public JAXBElement<String> createAccountInfoNoResit(String value) {
        return new JAXBElement<String>(_AccountInfoNoResit_QNAME, String.class, AccountInfo.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.portal.view.etanah", name = "tunggakanTahun", scope = AccountInfo.class)
    public JAXBElement<String> createAccountInfoTunggakanTahun(String value) {
        return new JAXBElement<String>(_AccountInfoTunggakanTahun_QNAME, String.class, AccountInfo.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.portal.view.etanah", name = "jumlahCukaiSemasa", scope = AccountInfo.class)
    public JAXBElement<BigDecimal> createAccountInfoJumlahCukaiSemasa(BigDecimal value) {
        return new JAXBElement<BigDecimal>(_AccountInfoJumlahCukaiSemasa_QNAME, BigDecimal.class, AccountInfo.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.portal.view.etanah", name = "jumlahTunggakan", scope = AccountInfo.class)
    public JAXBElement<BigDecimal> createAccountInfoJumlahTunggakan(BigDecimal value) {
        return new JAXBElement<BigDecimal>(_AccountInfoJumlahTunggakan_QNAME, BigDecimal.class, AccountInfo.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.portal.view.etanah", name = "icPemilik", scope = AccountInfo.class)
    public JAXBElement<String> createAccountInfoIcPemilik(String value) {
        return new JAXBElement<String>(_AccountInfoIcPemilik_QNAME, String.class, AccountInfo.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.portal.view.etanah", name = "kategori", scope = AccountInfo.class)
    public JAXBElement<String> createAccountInfoKategori(String value) {
        return new JAXBElement<String>(_AccountInfoKategori_QNAME, String.class, AccountInfo.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.portal.view.etanah", name = "kodCarianpersendirian", scope = AccountInfo.class)
    public JAXBElement<String> createAccountInfoKodCarianpersendirian(String value) {
        return new JAXBElement<String>(_AccountInfoKodCarianpersendirian_QNAME, String.class, AccountInfo.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.portal.view.etanah", name = "namaPembayar", scope = AccountInfo.class)
    public JAXBElement<String> createAccountInfoNamaPembayar(String value) {
        return new JAXBElement<String>(_AccountInfoNamaPembayar_QNAME, String.class, AccountInfo.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.portal.view.etanah", name = "kodTunggakanDendaLewat", scope = AccountInfo.class)
    public JAXBElement<String> createAccountInfoKodTunggakanDendaLewat(String value) {
        return new JAXBElement<String>(_AccountInfoKodTunggakanDendaLewat_QNAME, String.class, AccountInfo.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.portal.view.etanah", name = "kodTunggakanCukai", scope = AccountInfo.class)
    public JAXBElement<String> createAccountInfoKodTunggakanCukai(String value) {
        return new JAXBElement<String>(_AccountInfoKodTunggakanCukai_QNAME, String.class, AccountInfo.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.portal.view.etanah", name = "lokaliti", scope = AccountInfo.class)
    public JAXBElement<String> createAccountInfoLokaliti(String value) {
        return new JAXBElement<String>(_AccountInfoLokaliti_QNAME, String.class, AccountInfo.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.portal.view.etanah", name = "tunggakanCukaiParit", scope = AccountInfo.class)
    public JAXBElement<BigDecimal> createAccountInfoTunggakanCukaiParit(BigDecimal value) {
        return new JAXBElement<BigDecimal>(_AccountInfoTunggakanCukaiParit_QNAME, BigDecimal.class, AccountInfo.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.portal.view.etanah", name = "tunggakanCukaiTanah", scope = AccountInfo.class)
    public JAXBElement<BigDecimal> createAccountInfoTunggakanCukaiTanah(BigDecimal value) {
        return new JAXBElement<BigDecimal>(_AccountInfoTunggakanCukaiTanah_QNAME, BigDecimal.class, AccountInfo.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.portal.view.etanah", name = "kodCawValue", scope = AccountInfo.class)
    public JAXBElement<String> createAccountInfoKodCawValue(String value) {
        return new JAXBElement<String>(_AccountInfoKodCawValue_QNAME, String.class, AccountInfo.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.portal.view.etanah", name = "lebihan", scope = AccountInfo.class)
    public JAXBElement<BigDecimal> createAccountInfoLebihan(BigDecimal value) {
        return new JAXBElement<BigDecimal>(_AccountInfoLebihan_QNAME, BigDecimal.class, AccountInfo.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.portal.view.etanah", name = "cukaiSemasaParit", scope = AccountInfo.class)
    public JAXBElement<BigDecimal> createAccountInfoCukaiSemasaParit(BigDecimal value) {
        return new JAXBElement<BigDecimal>(_AccountInfoCukaiSemasaParit_QNAME, BigDecimal.class, AccountInfo.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.portal.view.etanah", name = "lokasiBayar", scope = AccountInfo.class)
    public JAXBElement<String> createAccountInfoLokasiBayar(String value) {
        return new JAXBElement<String>(_AccountInfoLokasiBayar_QNAME, String.class, AccountInfo.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.portal.view.etanah", name = "bakiAkaun", scope = AccountInfo.class)
    public JAXBElement<BigDecimal> createAccountInfoBakiAkaun(BigDecimal value) {
        return new JAXBElement<BigDecimal>(_AccountInfoBakiAkaun_QNAME, BigDecimal.class, AccountInfo.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.portal.view.etanah", name = "kreditDebit", scope = AccountInfo.class)
    public JAXBElement<BigDecimal> createAccountInfoKreditDebit(BigDecimal value) {
        return new JAXBElement<BigDecimal>(_AccountInfoKreditDebit_QNAME, BigDecimal.class, AccountInfo.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.portal.view.etanah", name = "cukaiSemasaTanah", scope = AccountInfo.class)
    public JAXBElement<BigDecimal> createAccountInfoCukaiSemasaTanah(BigDecimal value) {
        return new JAXBElement<BigDecimal>(_AccountInfoCukaiSemasaTanah_QNAME, BigDecimal.class, AccountInfo.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.portal.view.etanah", name = "akaunBaru", scope = AccountInfo.class)
    public JAXBElement<String> createAccountInfoAkaunBaru(String value) {
        return new JAXBElement<String>(_AccountInfoAkaunBaru_QNAME, String.class, AccountInfo.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.portal.view.etanah", name = "icPembayar", scope = AccountInfo.class)
    public JAXBElement<String> createAccountInfoIcPembayar(String value) {
        return new JAXBElement<String>(_AccountInfoIcPembayar_QNAME, String.class, AccountInfo.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.portal.view.etanah", name = "kodCukaiSemasaParit", scope = AccountInfo.class)
    public JAXBElement<String> createAccountInfoKodCukaiSemasaParit(String value) {
        return new JAXBElement<String>(_AccountInfoKodCukaiSemasaParit_QNAME, String.class, AccountInfo.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.portal.view.etanah", name = "kodDendaLewat", scope = AccountInfo.class)
    public JAXBElement<String> createAccountInfoKodDendaLewat(String value) {
        return new JAXBElement<String>(_AccountInfoKodDendaLewat_QNAME, String.class, AccountInfo.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.portal.view.etanah", name = "bandarpekanmukim", scope = AccountInfo.class)
    public JAXBElement<String> createAccountInfoBandarpekanmukim(String value) {
        return new JAXBElement<String>(_AccountInfoBandarpekanmukim_QNAME, String.class, AccountInfo.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.portal.view.etanah", name = "kodKreditDebit", scope = AccountInfo.class)
    public JAXBElement<String> createAccountInfoKodKreditDebit(String value) {
        return new JAXBElement<String>(_AccountInfoKodKreditDebit_QNAME, String.class, AccountInfo.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.portal.view.etanah", name = "jenisHakmilik", scope = AccountInfo.class)
    public JAXBElement<String> createAccountInfoJenisHakmilik(String value) {
        return new JAXBElement<String>(_AccountInfoJenisHakmilik_QNAME, String.class, AccountInfo.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.portal.view.etanah", name = "syaratNyata", scope = AccountInfo.class)
    public JAXBElement<String> createAccountInfoSyaratNyata(String value) {
        return new JAXBElement<String>(_AccountInfoSyaratNyata_QNAME, String.class, AccountInfo.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.portal.view.etanah", name = "tahun", scope = AccountInfo.class)
    public JAXBElement<String> createAccountInfoTahun(String value) {
        return new JAXBElement<String>(_AccountInfoTahun_QNAME, String.class, AccountInfo.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.portal.view.etanah", name = "noLot", scope = AccountInfo.class)
    public JAXBElement<String> createAccountInfoNoLot(String value) {
        return new JAXBElement<String>(_AccountInfoNoLot_QNAME, String.class, AccountInfo.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.portal.view.etanah", name = "kodCaw", scope = AccountInfo.class)
    public JAXBElement<String> createAccountInfoKodCaw(String value) {
        return new JAXBElement<String>(_AccountInfoKodCaw_QNAME, String.class, AccountInfo.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.portal.view.etanah", name = "tunggakanDendaLewat", scope = AccountInfo.class)
    public JAXBElement<BigDecimal> createAccountInfoTunggakanDendaLewat(BigDecimal value) {
        return new JAXBElement<BigDecimal>(_AccountInfoTunggakanDendaLewat_QNAME, BigDecimal.class, AccountInfo.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.portal.view.etanah", name = "kodTunggakanCukaiParit", scope = AccountInfo.class)
    public JAXBElement<String> createAccountInfoKodTunggakanCukaiParit(String value) {
        return new JAXBElement<String>(_AccountInfoKodTunggakanCukaiParit_QNAME, String.class, AccountInfo.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.portal.view.etanah", name = "statusAkaun", scope = AccountInfo.class)
    public JAXBElement<String> createAccountInfoStatusAkaun(String value) {
        return new JAXBElement<String>(_AccountInfoStatusAkaun_QNAME, String.class, AccountInfo.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.portal.view.etanah", name = "amaun", scope = SejarahCukai.class)
    public JAXBElement<String> createSejarahCukaiAmaun(String value) {
        return new JAXBElement<String>(_SejarahTransaksiPortalAmaun_QNAME, String.class, SejarahCukai.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.portal.view.etanah", name = "tahun", scope = SejarahCukai.class)
    public JAXBElement<String> createSejarahCukaiTahun(String value) {
        return new JAXBElement<String>(_AccountInfoTahun_QNAME, String.class, SejarahCukai.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.portal.view.etanah", name = "kaedahBayaran", scope = SejarahCukai.class)
    public JAXBElement<String> createSejarahCukaiKaedahBayaran(String value) {
        return new JAXBElement<String>(_SejarahCukaiKaedahBayaran_QNAME, String.class, SejarahCukai.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.portal.view.etanah", name = "noResit", scope = SejarahCukai.class)
    public JAXBElement<String> createSejarahCukaiNoResit(String value) {
        return new JAXBElement<String>(_AccountInfoNoResit_QNAME, String.class, SejarahCukai.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.portal.view.etanah", name = "pusatKutipan", scope = SejarahCukai.class)
    public JAXBElement<String> createSejarahCukaiPusatKutipan(String value) {
        return new JAXBElement<String>(_SejarahCukaiPusatKutipan_QNAME, String.class, SejarahCukai.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.portal.view.etanah", name = "noAccount", scope = AkaunForm.class)
    public JAXBElement<String> createAkaunFormNoAccount(String value) {
        return new JAXBElement<String>(_AkaunFormNoAccount_QNAME, String.class, AkaunForm.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.portal.view.etanah", name = "amaunt", scope = AkaunForm.class)
    public JAXBElement<BigDecimal> createAkaunFormAmaunt(BigDecimal value) {
        return new JAXBElement<BigDecimal>(_AkaunFormAmaunt_QNAME, BigDecimal.class, AkaunForm.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.portal.view.etanah", name = "nama", scope = UtilKod.class)
    public JAXBElement<String> createUtilKodNama(String value) {
        return new JAXBElement<String>(_StatusLoginNama_QNAME, String.class, UtilKod.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.portal.view.etanah", name = "kod", scope = UtilKod.class)
    public JAXBElement<String> createUtilKodKod(String value) {
        return new JAXBElement<String>(_UtilKodKod_QNAME, String.class, UtilKod.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.portal.view.etanah", name = "trhLogin", scope = FormDaftarPengguna.class)
    public JAXBElement<String> createFormDaftarPenggunaTrhLogin(String value) {
        return new JAXBElement<String>(_FormDaftarPenggunaTrhLogin_QNAME, String.class, FormDaftarPengguna.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.portal.view.etanah", name = "noTel", scope = FormDaftarPengguna.class)
    public JAXBElement<String> createFormDaftarPenggunaNoTel(String value) {
        return new JAXBElement<String>(_FormDaftarPenggunaNoTel_QNAME, String.class, FormDaftarPengguna.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.portal.view.etanah", name = "nama", scope = FormDaftarPengguna.class)
    public JAXBElement<String> createFormDaftarPenggunaNama(String value) {
        return new JAXBElement<String>(_StatusLoginNama_QNAME, String.class, FormDaftarPengguna.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.portal.view.etanah", name = "noKp", scope = FormDaftarPengguna.class)
    public JAXBElement<String> createFormDaftarPenggunaNoKp(String value) {
        return new JAXBElement<String>(_FormDaftarPenggunaNoKp_QNAME, String.class, FormDaftarPengguna.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.portal.view.etanah", name = "passwd", scope = FormDaftarPengguna.class)
    public JAXBElement<String> createFormDaftarPenggunaPasswd(String value) {
        return new JAXBElement<String>(_FormDaftarPenggunaPasswd_QNAME, String.class, FormDaftarPengguna.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.portal.view.etanah", name = "email", scope = FormDaftarPengguna.class)
    public JAXBElement<String> createFormDaftarPenggunaEmail(String value) {
        return new JAXBElement<String>(_FormDaftarPenggunaEmail_QNAME, String.class, FormDaftarPengguna.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.portal.view.etanah", name = "idPguna", scope = FormDaftarPengguna.class)
    public JAXBElement<String> createFormDaftarPenggunaIdPguna(String value) {
        return new JAXBElement<String>(_FormDaftarPenggunaIdPguna_QNAME, String.class, FormDaftarPengguna.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.dokumen.view.etanah", name = "docType", scope = DokumenInfo.class)
    public JAXBElement<String> createDokumenInfoDocType(String value) {
        return new JAXBElement<String>(_DokumenInfoDocType_QNAME, String.class, DokumenInfo.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link byte[]}{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.dokumen.view.etanah", name = "bytes", scope = DokumenInfo.class)
    public JAXBElement<byte[]> createDokumenInfoBytes(byte[] value) {
        return new JAXBElement<byte[]>(_DokumenInfoBytes_QNAME, byte[].class, DokumenInfo.class, ((byte[]) value));
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.dokumen.view.etanah", name = "fileName", scope = DokumenInfo.class)
    public JAXBElement<String> createDokumenInfoFileName(String value) {
        return new JAXBElement<String>(_DokumenInfoFileName_QNAME, String.class, DokumenInfo.class, value);
    }

}
