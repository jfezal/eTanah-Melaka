/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.model.jompay;

 import etanah.model.InfoAudit;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "JOMPAY_FAIL_DETAILS")
@SequenceGenerator(name = "SEQ_JOMPAY_FAIL_DETAILS", sequenceName = "SEQ_JOMPAY_FAIL_DETAILS")
public class JomPayFailDetails implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "SEQ_JOMPAY_FAIL_DETAILS")
    @Column(name = "ID_JOMPAYDETAILS")
    private Long idJomPayDetails;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ID_JOMPAYUPLOAD")
    private JomPayFailMuatnaik jomPayFailMuatnaik;

    @Column(name = "AKAUN_NO")
    String akaunNo;

    @Column(name = "RUJUKAN_BAYARAN")
    String rujukanBayaran;

    @Column(name = "RUJUKAN_DETAILS")
    String rujukanDetails;

    @Column(name = "JENIS_BAYARAN")
    String jenisBayaran;

    @Column(name = "TRANS_AMAUN", precision = 12, scale = 2, columnDefinition = "NUMBER(12,2)")
    private BigDecimal transAmaun;
    
    @Column(name = "TARIKH_TRANS")
    Date tarikhTrans;

    @Column(name = "STATUS")
    String status;
    
    @Column(name = "NO_TRANS")
    String noTrans;
    
    @Column(name = "PEMBAYAR")
    String namaPembayar;
    
    
    @Embedded
    private InfoAudit infoAudit;

    public Long getIdJomPayDetails() {
        return idJomPayDetails;
    }

    public void setIdJomPayDetails(Long idJomPayDetails) {
        this.idJomPayDetails = idJomPayDetails;
    }

    public JomPayFailMuatnaik getJomPayFailMuatnaik() {
        return jomPayFailMuatnaik;
    }

    public void setJomPayFailMuatnaik(JomPayFailMuatnaik jomPayFailMuatnaik) {
        this.jomPayFailMuatnaik = jomPayFailMuatnaik;
    }

    public String getAkaunNo() {
        return akaunNo;
    }

    public void setAkaunNo(String akaunNo) {
        this.akaunNo = akaunNo;
    }

    public String getRujukanBayaran() {
        return rujukanBayaran;
    }

    public void setRujukanBayaran(String rujukanBayaran) {
        this.rujukanBayaran = rujukanBayaran;
    }

    public String getRujukanDetails() {
        return rujukanDetails;
    }

    public void setRujukanDetails(String rujukanDetails) {
        this.rujukanDetails = rujukanDetails;
    }

    public String getJenisBayaran() {
        return jenisBayaran;
    }

    public void setJenisBayaran(String jenisBayaran) {
        this.jenisBayaran = jenisBayaran;
    }

    public BigDecimal getTransAmaun() {
        return transAmaun;
    }

    public void setTransAmaun(BigDecimal transAmaun) {
        this.transAmaun = transAmaun;
    }

    public Date getTarikhTrans() {
        return tarikhTrans;
    }

    public void setTarikhTrans(Date tarikhTrans) {
        this.tarikhTrans = tarikhTrans;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getNoTrans() {
        return noTrans;
    }

    public void setNoTrans(String noTrans) {
        this.noTrans = noTrans;
    }

    public String getNamaPembayar() {
        return namaPembayar;
    }

    public void setNamaPembayar(String namaPembayar) {
        this.namaPembayar = namaPembayar;
    }
    
    
    

    public InfoAudit getInfoAudit() {
        return infoAudit;
    }

    public void setInfoAudit(InfoAudit infoAudit) {
        this.infoAudit = infoAudit;
    }

}
