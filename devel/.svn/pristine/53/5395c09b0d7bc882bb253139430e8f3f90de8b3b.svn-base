/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.jtek.ws;

import etanah.model.KodSyorUlasanJabatanTeknikal;
import java.util.Date;
import java.util.List;

/**
 *
 * @author faidzal
 */
public interface IJabatanTeknikalService {

    public InfoPenggunaPortal infoPenggunaPortal1(String idPengguna, String password);

    public String daftarPengguna(InfoPenggunaPortal ipp);

    public List<SenaraiTugasanJTEK> senaraiTugasanJTEK1(String idJTEK);

    public List<SenaraiTugasanJTEK> senaraiTugasanJTEK1(String idJTEK, String daerah, String tarikhMula, String tarikhTamat);

    public List<SenaraiTugasanJTEK> senaraiTugasanJTEK1(String idJTEK, String idPermohonan);

    public List<InfoPemohon> infoPemohon1(String idPermohonan);

    public List<InfoTanah> infoTanah1(String idPermohonan);

    public List<InfoDok> infoDokumenTerima1(String idjtekulas);

    public List<InfoDok> infoDokumenHantar1(String idjtekulas);

    public InfoJtekUlas jtekInfo(String idjtekulas);

    public InfoJtekUlas hantarLaporan(String namaPenyelia, String kodSyor, String Ulasan, Date tarikhSedia, String noRujukan, String kodSts, String idjtekulas);

    public SempadanTanahInfo sempadanTanahInfo1(String idPermohonan, String idHakmilik);

    public InfoPenggunaPortal kemaskiniProfil(InfoPenggunaPortal ip);

    public List<SenaraiTugasanJTEK> tugasanSelesai(String idJTEK);

    public List<SenaraiTugasanJTEK> tugasanSelesai(String idJTEK, String daerah, String tarikhMula, String tarikhTamat);

    public List<SenaraiTugasanJTEK> tugasanSelesai(String idJTEK, String idPermohonan);

    /**
     *
     * @return
     */
    public List<KodSyor> listKodSyorUlasanJabatanTeknikal();
}
