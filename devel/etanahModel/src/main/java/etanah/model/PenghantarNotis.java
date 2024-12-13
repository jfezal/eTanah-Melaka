package etanah.model;

import java.io.Serializable;

import javax.persistence.*;

@Entity
@Table (name = "penghantar_notis")

@SequenceGenerator(name = "seq_penghantar_notis", sequenceName = "seq_penghantar_notis")

public class PenghantarNotis implements Serializable {
    @Id
    @GeneratedValue (strategy = GenerationType.SEQUENCE, generator = "seq_penghantar_notis")
    @Column (name = "id_penghantar_notis")
    private int idPenghantarNotis;

    @Column (name = "nama", nullable = false)
    private String nama;

    @ManyToOne
    @JoinColumn (name = "kod_caw", nullable = false)
    private KodCawangan cawangan;

    @ManyToOne
    @JoinColumn (name = "kod_pengenalan", nullable = false)
    private KodJenisPengenalan kodJenisPengenalan;

    @Column (name = "no_pengenalan", nullable = false)
    private String noPengenalan;

    @Column (name = "kod_sumber")
    private String kodSumber;

    @Column (name = "aktif")
    private char aktif;

    @Embedded
    InfoAudit infoAudit;

    public void setIdPenghantarNotis(int idPenghantarNotis) {
        this.idPenghantarNotis = idPenghantarNotis;
    }

    public int getIdPenghantarNotis() {
        return idPenghantarNotis;
    }

    public void setCawangan(KodCawangan cawangan) {
		this.cawangan = cawangan;
	}

	public KodCawangan getCawangan() {
		return cawangan;
	}

    public KodJenisPengenalan getKodJenisPengenalan() {
        return kodJenisPengenalan;
    }

    public void setKodJenisPengenalan(KodJenisPengenalan kodJenisPengenalan) {
        this.kodJenisPengenalan = kodJenisPengenalan;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getNoPengenalan() {
        return noPengenalan;
    }

    public void setNoPengenalan(String noPengenalan) {
        this.noPengenalan = noPengenalan;
    }

    public void setKodSumber(String kodSumber) {
        this.kodSumber = kodSumber;
    }

    public String getKodSumber() {
        return kodSumber;
    }

    public void setAktif(char aktif) {
        this.aktif = aktif;
    }

    public char getAktif() {
        return aktif;
    }

    public void setInfoAudit(InfoAudit infoAudit) {
        this.infoAudit = infoAudit;
    }

    public InfoAudit getInfoAudit() {
        return infoAudit;
    }
}
