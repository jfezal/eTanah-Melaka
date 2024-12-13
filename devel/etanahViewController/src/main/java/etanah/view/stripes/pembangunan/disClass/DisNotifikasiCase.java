package etanah.view.stripes.pembangunan.disClass;

import com.google.inject.Inject;
import etanah.model.KodCawangan;
import etanah.model.Notifikasi;
import etanah.model.PenggunaPeranan;
import etanah.model.Permohonan;
import etanah.workflow.StageContext;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 * @author Shazwan 09112012 1:31 PM @purpose : To Cater Notification
 */
public class DisNotifikasiCase {
    
    @Inject
    DisLaporanTanahService disLaporanTanahService;   
    Notifikasi notifikasi = new Notifikasi();
    List<String> kumpBPEL = new ArrayList<String>();
    String[] data = new String[2];
    
    private KodCawangan settingNotifikasiCawangan(KodCawangan cawPermohonan, String type) {
        KodCawangan kodCaw = new KodCawangan();
        int numType = type.equals("PTDPTD") ? 1
                : type.equals("PTDPTG") ? 2
                : type.equals("PTGPTD") ? 3
                : type.equals("PTGPTG") ? 4 : 0;
        if (numType == 2 || numType == 4) {
            kodCaw = disLaporanTanahService.getKodCawanganDAO().findById("00");
            return kodCaw;
        } else {
            return cawPermohonan;
        }
    }

    
    
    /*
     * NOTIFICATION CASE
     */
    public void casePPRU(String stageId,int negeri,StageContext context,Permohonan permohonan){
        switch(negeri){
            case 1 : 
                    //MELAKA
                    if(stageId.equals("kemasukan")){
                        this.data[0] = "Makluman Kemasukan Permohonan Permit Menggunakan Ruang Udara";
                        this.data[1] = context.getPermohonan().getIdPermohonan() + " - " + context.getPermohonan().getKodUrusan().getNama() + " telah dihantar kepada Pentadbir Tanah untuk makluman";
                        this.kumpBPEL.add("ptd");
                        this.notifikasi.setCawangan(settingNotifikasiCawangan(permohonan.getCawangan(), "PTDPTD"));
                    }
                    else if (stageId.equals("rekod_keputusan_mmkn")) {
                        this.kumpBPEL.add("kptlupus");
                        this.kumpBPEL.add("ptd");
                        this.kumpBPEL.add("tptd");
                        this.data[0] = "Makluman Keputusan MMKN";
                        this.data[1] = context.getPermohonan().getIdPermohonan() + " - " + permohonan.getKodUrusan().getNama() + " telah di" + context.getPermohonan().getKeputusan().getNama() + " oleh Pihak Berkuasa Negeri.";
                        this.notifikasi.setCawangan(settingNotifikasiCawangan(permohonan.getCawangan(), "PTDPTD"));
                    }    
                break;
            case 2 :
                    //NEGERI SEMBILAN
                break;
        }
            
    }
    
    public void casePPTR(String stageId,int negeri,StageContext context,Permohonan permohonan){
        switch(negeri){
            case 1 : 
                    //MELAKA
                    if(stageId.equals("kemasukan")){
                        this.data[0] = "Makluman Kemasukan Permohonan Memajak Tanah Rizab";
                        this.data[1] = context.getPermohonan().getIdPermohonan() + " - " + context.getPermohonan().getKodUrusan().getNama() + " telah dihantar kepada Pentadbir Tanah untuk makluman";
                        this.kumpBPEL.add("ptd");
                        this.notifikasi.setCawangan(settingNotifikasiCawangan(permohonan.getCawangan(), "PTDPTD"));
                    }
                    else if (stageId.equals("rekod_keputusan_mmkn")) {
                        this.data[0] = "Makluman Keputusan MMKN";
                        this.data[1] = context.getPermohonan().getIdPermohonan() + " - " + permohonan.getKodUrusan().getNama() + " telah di" + context.getPermohonan().getKeputusan().getNama() + " oleh Pihak Berkuasa Negeri.";                
                        this.kumpBPEL.add("kptlupus");
                        this.kumpBPEL.add("ptd");
                        this.kumpBPEL.add("tptd");
                        this.notifikasi.setCawangan(settingNotifikasiCawangan(permohonan.getCawangan(), "PTGPTD"));
                    } else if (stageId.equals("RekodKeputusanMMK")) {
                        this.data[0] = "Makluman Keputusan MMKN";
                        this.data[1] = context.getPermohonan().getIdPermohonan() + " - " + permohonan.getKodUrusan().getNama() + " telah di" + context.getPermohonan().getKeputusan().getNama() + " oleh Pihak Berkuasa Negeri.";                
                        this.kumpBPEL.add("kptlupus");
                        this.kumpBPEL.add("ptd");
                        this.kumpBPEL.add("tptd");
                        this.notifikasi.setCawangan(settingNotifikasiCawangan(permohonan.getCawangan(), "PTGPTD"));
                    }   
                break;
            case 2 :
                    //NEGERI SEMBILAN
                break;
        }
    }
    public void caseLPSP(String stageId,int negeri,StageContext context,Permohonan permohonan){
        switch(negeri){
            case 1 : 
                    //MELAKA
                    if(stageId.equals("kemasukan")){
                        this.data[0] = "Makluman Kemasukan Permohonan Lesen Pendudukan Sementara dan Permit (Seksyen 69 KTN)";
                        this.data[1] = context.getPermohonan().getIdPermohonan() + " - " + context.getPermohonan().getKodUrusan().getNama() + " telah dihantar kepada Pentadbir Tanah untuk makluman";
                        this.kumpBPEL.add("ptd");
                        this.notifikasi.setCawangan(settingNotifikasiCawangan(permohonan.getCawangan(), "PTDPTD"));
                    }
                    else if (stageId.equals("rekod_keputusan_mmkn")) {
                        this.data[0] = "Makluman Keputusan MMKN";
                        this.data[1] = context.getPermohonan().getIdPermohonan() + " - " + permohonan.getKodUrusan().getNama() + " telah di" + context.getPermohonan().getKeputusan().getNama() + " oleh Pihak Berkuasa Negeri.";
                        this.kumpBPEL.add("kptlupus");
                        this.kumpBPEL.add("ptd");
                        this.kumpBPEL.add("tptd");
                        this.notifikasi.setCawangan(settingNotifikasiCawangan(permohonan.getCawangan(), "PTGPTD"));
                    } else if (stageId.equals("RekodKeputusanMMK")) {
                        this.data[0] = "Makluman Keputusan MMKN";
                        this.data[1] = context.getPermohonan().getIdPermohonan() + " - " + permohonan.getKodUrusan().getNama() + " telah di" + context.getPermohonan().getKeputusan().getNama() + " oleh Pihak Berkuasa Negeri.";
                        this.kumpBPEL.add("kptlupus");
                        this.kumpBPEL.add("ptd");
                        this.kumpBPEL.add("tptd");
                        this.notifikasi.setCawangan(settingNotifikasiCawangan(permohonan.getCawangan(), "PTGPTD"));
                    }   
                break;
            case 2 :
                    //NEGERI SEMBILAN
                break;
        }
    }
    public void caseRAYT(String stageId,int negeri,StageContext context,Permohonan permohonan){
        switch(negeri){
            case 1 : 
                    //MELAKA
                    if(stageId.equals("001_Kemasukan")){
                    this.data[0] = "Makluman Kemasukan Rayuan Penolakan Permohonan";
                    this.data[1] = context.getPermohonan().getIdPermohonan() + " - " + context.getPermohonan().getKodUrusan().getNama() + " telah dihantar kepada Pentadbir Tanah untuk makluman";
                    this.kumpBPEL.add("ptd");
                    this.notifikasi.setCawangan(settingNotifikasiCawangan(permohonan.getCawangan(), "PTDPTD"));
                }else if (stageId.equals("012_KeputusanMMKN")) {
                    this.data[0] = "Makluman Keputusan MMKN";
                    this.data[1] = context.getPermohonan().getIdPermohonan() + " - " + permohonan.getKodUrusan().getNama() + " telah di" + context.getPermohonan().getKeputusan().getNama() + " oleh Pihak Berkuasa Negeri.";
                    this.kumpBPEL.add("ptd");
                    this.notifikasi.setCawangan(settingNotifikasiCawangan(permohonan.getCawangan(), "PTGPTD"));
                }   
                break;
            case 2 :
                    //NEGERI SEMBILAN
                break;
        }
    }
    public void casePBRZ(String stageId,int negeri,StageContext context,Permohonan permohonan){
        switch(negeri){
            case 1 : 
                    //MELAKA
                    if(stageId.equals("001_Kemasukan")){
                        this.data[0] = "Makluman Kemasukan Permohonan Pembatalan Perizaban";
                        this.data[1] = context.getPermohonan().getIdPermohonan() + " - " + context.getPermohonan().getKodUrusan().getNama() + " telah dihantar kepada Pentadbir Tanah untuk makluman";
                        this.kumpBPEL.add("ptd");
                        this.notifikasi.setCawangan(settingNotifikasiCawangan(permohonan.getCawangan(), "PTDPTD"));
                    } else if (stageId.equals("023_suratDanWarta")) {
                        this.data[0] = "Makluman Keputusan MMKN";
                        this.data[1] = context.getPermohonan().getIdPermohonan() + " - " + permohonan.getKodUrusan().getNama() + " telah di" + context.getPermohonan().getKeputusan().getNama() + " oleh Pihak Berkuasa Negeri.";
                        this.kumpBPEL.add("pptd");
                        this.notifikasi.setCawangan(settingNotifikasiCawangan(permohonan.getCawangan(), "PTGPTD"));
                    } else if (stageId.equals("032_SediaSuratdanWarta")) {
                        this.data[0] = "Makluman Keputusan MMKN";
                        this.data[1] = context.getPermohonan().getIdPermohonan() + " - " + permohonan.getKodUrusan().getNama() + " telah di" + context.getPermohonan().getKeputusan().getNama() + " oleh Pihak Berkuasa Negeri.";
                        this.kumpBPEL.add("ptg");
                        this.notifikasi.setCawangan(settingNotifikasiCawangan(permohonan.getCawangan(), "PTGPTG"));
                    } else if (stageId.equals("035_terimawarta")) {
                        this.data[0] = "Makluman Keputusan MMKN";
                        this.data[1] = context.getPermohonan().getIdPermohonan() + " - " + permohonan.getKodUrusan().getNama() + " telah di" + context.getPermohonan().getKeputusan().getNama() + " oleh Pihak Berkuasa Negeri.";
                        this.kumpBPEL.add("ptd");
                        this.notifikasi.setCawangan(settingNotifikasiCawangan(permohonan.getCawangan(), "PTDPTD"));
                    }   
                break;
            case 2 :
                    //NEGERI SEMBILAN
                break;
        }
    }
    public void casePRIZ(String stageId,int negeri,StageContext context,Permohonan permohonan){
        switch(negeri){
            case 1 : 
                    //MELAKA
                    if(stageId.equals("kemasukan")){
                        this.data[0] = "Makluman Kemasukan Permohonan Perizaban";
                        this.data[1] = context.getPermohonan().getIdPermohonan() + " - " + context.getPermohonan().getKodUrusan().getNama() + " telah dihantar kepada Pentadbir Tanah untuk makluman";
                        this.kumpBPEL.add("ptd");
                        this.notifikasi.setCawangan(settingNotifikasiCawangan(permohonan.getCawangan(), "PTDPTD"));
                    }
                    else if (stageId.equals("rekod_keputusan_mmkn")) {
                        this.data[0] = "Makluman Keputusan MMKN";
                        this.data[1] = context.getPermohonan().getIdPermohonan() + " - " + permohonan.getKodUrusan().getNama() + " telah di" + context.getPermohonan().getKeputusan().getNama() + " oleh Pihak Berkuasa Negeri.";                
                        this.kumpBPEL.add("kptlupus");
                        this.kumpBPEL.add("ptd");
                        this.kumpBPEL.add("tptd");
                        this.notifikasi.setCawangan(settingNotifikasiCawangan(permohonan.getCawangan(), "PTGPTD"));
                    } else if (stageId.equals("RekodKeputusanMMK")) {
                        this.data[0] = "Makluman Keputusan MMKN";
                        this.data[1] = context.getPermohonan().getIdPermohonan() + " - " + permohonan.getKodUrusan().getNama() + " telah di" + context.getPermohonan().getKeputusan().getNama() + " oleh Pihak Berkuasa Negeri.";                
                        this.kumpBPEL.add("kptlupus");
                        this.kumpBPEL.add("ptd");
                        this.kumpBPEL.add("tptd");
                        this.notifikasi.setCawangan(settingNotifikasiCawangan(permohonan.getCawangan(), "PTGPTD"));
                    }   
                break;
            case 2 :
                    //NEGERI SEMBILAN
                break;
        }
    }
    public void casePBMT(String stageId,int negeri,StageContext context,Permohonan permohonan){
        switch(negeri){
            case 1 : 
                    //MELAKA
                    if(stageId.equals("kemasukan")){
                        this.data[0] = "Makluman Kemasukan Permohonan Pemberimilikan Tanah Kerajaan (Seksyen 76 KTN)";
                        this.data[1] = context.getPermohonan().getIdPermohonan() + " - " + context.getPermohonan().getKodUrusan().getNama() + " telah dihantar kepada Pentadbir Tanah untuk makluman";
                        this.kumpBPEL.add("ptd");
                        this.notifikasi.setCawangan(settingNotifikasiCawangan(permohonan.getCawangan(), "PTDPTD"));
                    }    
                break;
            case 2 :
                    //NEGERI SEMBILAN
                break;
        }
    }
    public void casePLPS(String stageId,int negeri,StageContext context,Permohonan permohonan){
        switch(negeri){
            case 1 : 
                    //MELAKA
                    if(stageId.equals("kemasukan")){
                        this.data[0] = "Makluman Kemasukan Permohonan Lesen Pendudukan Sementara (Seksyen 65 KTN)";
                        this.data[1] = context.getPermohonan().getIdPermohonan() + " - " + context.getPermohonan().getKodUrusan().getNama() + " telah dihantar kepada Pentadbir Tanah untuk makluman";
                        this.kumpBPEL.add("ptd");
                        this.notifikasi.setCawangan(settingNotifikasiCawangan(permohonan.getCawangan(), "PTDPTD"));
                    }    
                break;
            case 2 :
                    //NEGERI SEMBILAN
                break;
        }
    }
    public void casePPBB(String stageId,int negeri,StageContext context,Permohonan permohonan){
        switch(negeri){
            case 1 : 
                    //MELAKA
                    if(stageId.equals("kemasukan")){
                        this.data[0] = "Makluman Kemasukan Permohonan Permit Bahan Batuan (Kelulusan Ketua Menteri)";
                        this.data[1] = context.getPermohonan().getIdPermohonan() + " - " + context.getPermohonan().getKodUrusan().getNama() + " telah dihantar kepada Pentadbir Tanah untuk makluman";
                        this.kumpBPEL.add("ptd");
                        this.notifikasi.setCawangan(settingNotifikasiCawangan(permohonan.getCawangan(), "PTDPTD"));
                    }    
                break;
            case 2 :
                    //NEGERI SEMBILAN
                break;
        }
    }
    public void casePBPTD(String stageId,int negeri,StageContext context,Permohonan permohonan){
        switch(negeri){
            case 1 : 
                    //MELAKA
                    if(stageId.equals("kemasukan")){
                        this.data[0] = "Makluman Kemasukan Permohonan Permit Bahan Batuan (Kelulusan PTD)";
                        this.data[1] = context.getPermohonan().getIdPermohonan() + " - " + context.getPermohonan().getKodUrusan().getNama() + " telah dihantar kepada Pentadbir Tanah untuk makluman";
                        this.kumpBPEL.add("ptd");
                        this.notifikasi.setCawangan(settingNotifikasiCawangan(permohonan.getCawangan(), "PTDPTD"));
                    }    
                break;
            case 2 :
                    //NEGERI SEMBILAN
                break;
        }
    }
    public void casePBPTG(String stageId,int negeri,StageContext context,Permohonan permohonan){
        switch(negeri){
            case 1 : 
                    //MELAKA
                    if(stageId.equals("kemasukan")){
                        this.data[0] = "Makluman Kemasukan Permohonan Permit Bahan Batuan (Kelulusan PTG)";
                        this.data[1] = context.getPermohonan().getIdPermohonan() + " - " + context.getPermohonan().getKodUrusan().getNama() + " telah dihantar kepada Pentadbir Tanah untuk makluman";
                        this.kumpBPEL.add("ptd");
                        this.notifikasi.setCawangan(settingNotifikasiCawangan(permohonan.getCawangan(), "PTDPTD"));
                    }    
                break;
            case 2 :
                    //NEGERI SEMBILAN
                break;
        }
    }
    public void casePRMP(String stageId,int negeri,StageContext context,Permohonan permohonan){
        switch(negeri){
            case 1 : 
                    //MELAKA
                    if(stageId.equals("kemasukan")){
                        this.data[0] = "Makluman Kemasukan Permohonan Permit Tanah Pertanian";
                        this.data[1] = context.getPermohonan().getIdPermohonan() + " - " + context.getPermohonan().getKodUrusan().getNama() + " telah dihantar kepada Pentadbir Tanah untuk makluman";
                        this.kumpBPEL.add("ptd");
                        this.notifikasi.setCawangan(settingNotifikasiCawangan(permohonan.getCawangan(), "PTDPTD"));
                    }    
                break;
            case 2 :
                    //NEGERI SEMBILAN
                break;
        }
    }
    public void caseLMCRG(String stageId,int negeri,StageContext context,Permohonan permohonan){
        switch(negeri){
            case 1 : 
                    //MELAKA
                    if(stageId.equals("kemasukan")){
                        this.data[0] = "Makluman Kemasukan Permohonan Lesen Mencarigali/Penjelajahan";
                        this.data[1] = context.getPermohonan().getIdPermohonan() + " - " + context.getPermohonan().getKodUrusan().getNama() + " telah dihantar kepada Pengarah Tanah dan Galian untuk makluman";
                        this.kumpBPEL.add("ptg");
                        this.notifikasi.setCawangan(settingNotifikasiCawangan(permohonan.getCawangan(), "PTGPTG"));
                    }    
                break;
            case 2 :
                    //NEGERI SEMBILAN
                break;
        }
    }
    public void casePTGSA(String stageId,int negeri,StageContext context,Permohonan permohonan){
        switch(negeri){
            case 1 : 
                    //MELAKA
                    if(stageId.equals("01Kemasukan")){
                        this.data[0] = "Makluman Kemasukan Permohonan Penamatan Tanah GSA";
                        this.data[1] = context.getPermohonan().getIdPermohonan() + " - " + context.getPermohonan().getKodUrusan().getNama() + " telah dihantar kepada Pentadbir Tanah untuk makluman";
                        this.kumpBPEL.add("ptd");
                        this.notifikasi.setCawangan(settingNotifikasiCawangan(permohonan.getCawangan(), "PTDPTD"));
                    }    
                break;
            case 2 :
                    //NEGERI SEMBILAN
                break;
        }
    }
    public void casePHLP(String stageId,int negeri,StageContext context,Permohonan permohonan){
        switch(negeri){
            case 1 : 
                    //MELAKA
                    if(stageId.equals("01kemasukan")){
                        this.data[0] = "Makluman Kemasukan Permohonan Hak Lalulalang Persendirian";
                        this.data[1] = context.getPermohonan().getIdPermohonan() + " - " + context.getPermohonan().getKodUrusan().getNama() + " telah dihantar kepada Pentadbir Tanah untuk makluman";
                        this.kumpBPEL.add("ptd");
                        this.notifikasi.setCawangan(settingNotifikasiCawangan(permohonan.getCawangan(), "PTDPTD"));
                    }    
                break;
            case 2 :
                    //NEGERI SEMBILAN
                break;
        }
    }
    public void casePRMMK(String stageId,int negeri,StageContext context,Permohonan permohonan){
        switch(negeri){
            case 1 : 
                    //MELAKA
                    if(stageId.equals("01kemasukan")){
                        this.data[0] = "Makluman Kemasukan Permohonan Perizaban (Kelulusan MMK)";
                        this.data[1] = context.getPermohonan().getIdPermohonan() + " - " + context.getPermohonan().getKodUrusan().getNama() + " telah dihantar kepada Pentadbir Tanah untuk makluman";
                        this.kumpBPEL.add("ptd");
                        this.notifikasi.setCawangan(settingNotifikasiCawangan(permohonan.getCawangan(), "PTDPTD"));
                    }    
                break;
            case 2 :
                    //NEGERI SEMBILAN
                break;
        }
    }
    public void caseBMBT(String stageId,int negeri,StageContext context,Permohonan permohonan){
        switch(negeri){
            case 1 : 
                    //MELAKA
                    if(stageId.equals("kemasukan")){
                        this.data[0] = "Makluman Kemasukan Permohonan Pemberimilikan Stratum Tanah Bawah Tanah [Subseksyen 92D(1)(b)KTN]";
                        this.data[1] = context.getPermohonan().getIdPermohonan() + " - " + context.getPermohonan().getKodUrusan().getNama() + " telah dihantar kepada Pentadbir Tanah untuk makluman";
                        this.kumpBPEL.add("ptd");
                        this.notifikasi.setCawangan(settingNotifikasiCawangan(permohonan.getCawangan(), "PTDPTD"));
                    }    
                break;
            case 2 :
                    //NEGERI SEMBILAN
                break;
        }
    }
    /*
     * END
     */
    public String[] getData() {
        return data;
    }

    public void setData(String[] data) {
        this.data = data;
    }

    public List<String> getKumpBPEL() {
        return kumpBPEL;
    }

    public void setKumpBPEL(List<String> kumpBPEL) {
        this.kumpBPEL = kumpBPEL;
    }

    public Notifikasi getNotifikasi() {
        return notifikasi;
    }

    public void setNotifikasi(Notifikasi notifikasi) {
        this.notifikasi = notifikasi;
    }
    
    
}
