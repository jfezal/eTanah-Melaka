package etanah.model.gis;

import etanah.model.KodDaerah;
import etanah.model.KodNegeri;
import etanah.model.Permohonan;
import java.io.Serializable;
import javax.persistence.*;

@Embeddable
public class PelanGISPK implements Serializable {

    @ManyToOne
    @JoinColumn (name = "kod_negeri")
    private KodNegeri kodNegeri;

    @ManyToOne
    @JoinColumn (name = "kod_daerah")
    private KodDaerah kodDaerah;


    @ManyToOne
    @JoinColumn (name = "id_permohonan")
    private Permohonan permohonan;

    @Column (name = "kod_mukim")
    private String kodMukim;

    @Column (name = "kod_seksyen")
    private String kodSeksyen;

    @Column (name = "no_lot")
    private String noLot;
    
    @Column(name = "NO_PERMIT")
    private String noPermit;

    public KodDaerah getKodDaerah() {
        return kodDaerah;
    }

    public void setKodDaerah(KodDaerah kodDaerah) {
        this.kodDaerah = kodDaerah;
    }

    public String getKodMukim() {
        return kodMukim;
    }

    public void setKodMukim(String kodMukim) {
        this.kodMukim = kodMukim;
    }

    public KodNegeri getKodNegeri() {
        return kodNegeri;
    }

    public void setKodNegeri(KodNegeri kodNegeri) {
        this.kodNegeri = kodNegeri;
    }

    public String getKodSeksyen() {
        return kodSeksyen;
    }

    public void setKodSeksyen(String kodSeksyen) {
        this.kodSeksyen = kodSeksyen;
    }


    public String getNoLot() {
        return noLot;
    }

    public void setNoLot(String noLot) {
        this.noLot = noLot;
    }

    public Permohonan getPermohonan() {
        return permohonan;
    }

    public void setPermohonan(Permohonan permohonan) {
        this.permohonan = permohonan;
    }

    public String getNoPermit() {
        return noPermit;
    }

    public void setNoPermit(String noPermit) {
        this.noPermit = noPermit;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 73 * hash + (this.kodNegeri != null ? this.kodNegeri.hashCode() : 0);
        hash = 73 * hash + (this.kodDaerah != null ? this.kodDaerah.hashCode() : 0);
        hash = 73 * hash + (this.permohonan != null ? this.permohonan.hashCode() : 0);
        hash = 73 * hash + (this.kodMukim != null ? this.kodMukim.hashCode() : 0);
        hash = 73 * hash + (this.kodSeksyen != null ? this.kodSeksyen.hashCode() : 0);
        hash = 73 * hash + (this.noLot != null ? this.noLot.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final PelanGISPK other = (PelanGISPK) obj;
        if (this.kodNegeri != other.kodNegeri && (this.kodNegeri == null || !this.kodNegeri.equals(other.kodNegeri))) {
            return false;
        }
        if (this.kodDaerah != other.kodDaerah && (this.kodDaerah == null || !this.kodDaerah.equals(other.kodDaerah))) {
            return false;
        }
        if (this.permohonan != other.permohonan && (this.permohonan == null || !this.permohonan.equals(other.permohonan))) {
            return false;
        }
        if ((this.kodMukim == null) ? (other.kodMukim != null) : !this.kodMukim.equals(other.kodMukim)) {
            return false;
        }
        if ((this.kodSeksyen == null) ? (other.kodSeksyen != null) : !this.kodSeksyen.equals(other.kodSeksyen)) {
            return false;
        }
        if ((this.noLot == null) ? (other.noLot != null) : !this.noLot.equals(other.noLot)) {
            return false;
        }
        return true;
    }

    
    
}
