package etanah.model.etapp;

import etanah.model.Hakmilik;
import etanah.model.InfoAudit;
import etanah.model.KodCawangan;
import java.io.Serializable;
import java.util.List;

import javax.persistence.*;

@Entity
@Table(name = "ETAPP_HAKMILIK")
@SequenceGenerator(name = "seq_ETAPP_HAKMILIK", sequenceName = "seq_ETAPP_HAKMILIK")
public class EtappHakmilik implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_ETAPP_HAKMILIK")
    @Column(name = "ID_ET_HAKMILIK")
    private Long idEtHakmilik;
    @ManyToOne
    @JoinColumn(name = "ID_HAKMILIK", nullable = false)
    private Hakmilik hakmilik;
        @ManyToOne
    @JoinColumn(name = "no_fail", nullable = false)
    private EtappPerintah etappPerintah;
    @Column(name = "ID_JENIS_HAKMILIK")
    private String jenisHakmilik;
    @Column(name = "no_hakmilik")
    private String noHakmilik;
    @Column(name = "ID_LUAS")
    private String kodLuas;
    @Column(name = "luas")
    private Integer luas;
    @Column(name = "ba_simati")
    private String basimati;
    @Column(name = "bb_simati")
    private String bbsimati;
    @Column(name = "kod_pembahagian")
    private String kodPembahagian;
    @OneToMany(mappedBy = "etappHakmilik")
    private List<EtappBorangE> listEtappBorangE;
    @OneToMany(mappedBy = "etappHakmilik")
    private List<EtappBorangF> listEtappBorangF;
    @OneToMany(mappedBy = "etappHakmilik")
    private List<EtappBorangH> listEtappBorangH;

    @Embedded
    private InfoAudit infoAudit;

    public Long getIdEtHakmilik() {
        return idEtHakmilik;
    }

    public void setIdEtHakmilik(Long idEtHakmilik) {
        this.idEtHakmilik = idEtHakmilik;
    }



   

    public Hakmilik getHakmilik() {
        return hakmilik;
    }

    public void setHakmilik(Hakmilik hakmilik) {
        this.hakmilik = hakmilik;
    }

    public String getJenisHakmilik() {
        return jenisHakmilik;
    }

    public void setJenisHakmilik(String jenisHakmilik) {
        this.jenisHakmilik = jenisHakmilik;
    }

    public String getNoHakmilik() {
        return noHakmilik;
    }

    public void setNoHakmilik(String noHakmilik) {
        this.noHakmilik = noHakmilik;
    }

    public String getKodLuas() {
        return kodLuas;
    }

    public void setKodLuas(String kodLuas) {
        this.kodLuas = kodLuas;
    }

    public Integer getLuas() {
        return luas;
    }

    public void setLuas(Integer luas) {
        this.luas = luas;
    }


    public String getBasimati() {
        return basimati;
    }

    public void setBasimati(String basimati) {
        this.basimati = basimati;
    }

    public String getBbsimati() {
        return bbsimati;
    }

    public void setBbsimati(String bbsimati) {
        this.bbsimati = bbsimati;
    }

    public String getKodPembahagian() {
        return kodPembahagian;
    }

    public void setKodPembahagian(String kodPembahagian) {
        this.kodPembahagian = kodPembahagian;
    }

    public InfoAudit getInfoAudit() {
        return infoAudit;
    }

    public void setInfoAudit(InfoAudit infoAudit) {
        this.infoAudit = infoAudit;
    }


    public List<EtappBorangE> getListEtappBorangE() {
        return listEtappBorangE;
    }

    public void setListEtappBorangE(List<EtappBorangE> listEtappBorangE) {
        this.listEtappBorangE = listEtappBorangE;
    }

    public List<EtappBorangF> getListEtappBorangF() {
        return listEtappBorangF;
    }

    public void setListEtappBorangF(List<EtappBorangF> listEtappBorangF) {
        this.listEtappBorangF = listEtappBorangF;
    }

    public List<EtappBorangH> getListEtappBorangH() {
        return listEtappBorangH;
    }

    public void setListEtappBorangH(List<EtappBorangH> listEtappBorangH) {
        this.listEtappBorangH = listEtappBorangH;
    }

    public EtappPerintah getEtappPerintah() {
        return etappPerintah;
    }

    public void setEtappPerintah(EtappPerintah etappPerintah) {
        this.etappPerintah = etappPerintah;
    }
    
    
}
