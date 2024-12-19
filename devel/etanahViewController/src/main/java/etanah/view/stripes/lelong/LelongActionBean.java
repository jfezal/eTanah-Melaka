/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.stripes.lelong;

/**
 *
 * @author farah.shafilla
 */
import able.stripes.AbleActionBean;
import able.stripes.JSP;

import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.UrlBinding;

@UrlBinding("/lelong")
public class LelongActionBean extends AbleActionBean {

    @DefaultHandler
    public Resolution listinterface() {
        return new ForwardResolution("/WEB-INF/jsp/lelong/senaraiInterface.jsp");
    }

  
    public Resolution jual() {
//        return new ForwardResolution("/WEB-INF/jsp/lelong/permohonan_perintah_jual.jsp");
        return new JSP("lelong/permohonan_perintah_jual.jsp").addParameter("tab", "true");
    }

    public Resolution semakjual() {
//        return new ForwardResolution("/WEB-INF/jsp/lelong/semak_perintah_jual.jsp");
        return new JSP("lelong/semak_perintah_jual.jsp").addParameter("tab", "true");

    }

    public Resolution enkuiri() {
//        return new ForwardResolution("/WEB-INF/jsp/lelong/masuk_enkuiri.jsp");
        return new JSP("lelong/masuk_enkuiri.jsp").addParameter("tab", "true");
    }

    public Resolution maklumattetapenkuiri() {
//        return new ForwardResolution("/WEB-INF/jsp/lelong/tetap_enkuiri.jsp");
        return new JSP("lelong/maklumat_tetap_enkuiri.jsp").addParameter("tab", "true");

    }

    public Resolution semakenkuiri() {
        return new JSP("lelong/semak_enkuiri.jsp").addParameter("tab", "true");

//        return new ForwardResolution("/WEB-INF/jsp/lelong/semak_enkuiri.jsp");
    }

    public Resolution borang2A() {
        return new JSP("lelong/borang2A.jsp").addParameter("tab", "true");

//        return new ForwardResolution("/WEB-INF/jsp/lelong/borang2A.jsp");
    }

    public Resolution notissurat() {
        return new JSP("lelong/penyediaan_notis_surat.jsp").addParameter("tab", "true");
//        return new ForwardResolution("/WEB-INF/jsp/lelong/penyediaan_notis_surat.jsp");
    }

    public Resolution semakborang2A() {

        return new JSP("lelong/semak_borang2A.jsp").addParameter("tab", "true");

//        return new ForwardResolution("/WEB-INF/jsp/lelong/semak_borang2A.jsp");
    }

    public Resolution arahannotis() {
        return new JSP("lelong/arahan_hantar_notis.jsp").addParameter("tab", "true");
//        return new ForwardResolution("/WEB-INF/jsp/lelong/arahan_hantar_notis.jsp");
    }

    public Resolution terimamaklumatnotis() {
        return new JSP("lelong/terima_maklumat_notis.jsp").addParameter("tab", "true");

//        return new ForwardResolution("/WEB-INF/jsp/lelong/terima_maklumat_notis.jsp");
    }

    public Resolution semakterimanotis() {
        return new JSP("lelong/semak_terima_notis.jsp").addParameter("tab", "true");

//        return new ForwardResolution("/WEB-INF/jsp/lelong/semak_terima_notis.jsp");
    }

    public Resolution maklumannotis() {
        return new JSP("lelong/makluman_notis.jsp").addParameter("tab", "true");

//        return new ForwardResolution("/WEB-INF/jsp/lelong/makluman_notis.jsp");
    }

    public Resolution semakenkuirilibat() {
        return new JSP("lelong/semak_enkuiri_terlibat.jsp").addParameter("tab", "true");

//        return new ForwardResolution("/WEB-INF/jsp/lelong/semak_enkuiri_terlibat.jsp");
    }

    public Resolution rekodenkuiri() {
        return new JSP("lelong/rekod_enkuiri.jsp").addParameter("tab", "true");
    }

    public Resolution perakuanenkuiri() {
        return new ForwardResolution("/WEB-INF/jsp/lelong/perakuan_enkuiri.jsp");
    }

    public Resolution borang16h() {
        return new JSP("lelong/borang16H.jsp").addParameter("tab", "true");
    }

    public Resolution lantikjurulelong() {
        return new JSP("lelong/lantik_jurulelong.jsp").addParameter("tab", "true");
    }

    public Resolution semakjurulelong() {
        return new JSP("lelong/semak_jurulelong.jsp").addParameter("tab", "true");
    }

    public Resolution semakdokumen() {
        return new ForwardResolution("/WEB-INF/jsp/lelong/semak_dokumen.jsp");
    }

    public Resolution semakdokumen2() {
        return new ForwardResolution("/WEB-INF/jsp/lelong/semak_dokumen2.jsp");
    }

    public Resolution rekodbidaan() {
        return new JSP("lelong/rekod_bidaan.jsp").addParameter("tab", "true");
    }

    public Resolution semakbidaan() {
        return new ForwardResolution("/WEB-INF/jsp/lelong/semak_bidaan.jsp");
    }

    public Resolution suratlelong() {
        return new ForwardResolution("/WEB-INF/jsp/lelong/surat_lelong.jsp");
    }

    public Resolution suratkomisyen() {
        return new ForwardResolution("/WEB-INF/jsp/lelong/surat_komisyen.jsp");
    }

    public Resolution semakkomisyen() {
        return new ForwardResolution("/WEB-INF/jsp/lelong/semak_komisyen.jsp");
    }

    public Resolution suratbaki() {
        return new JSP("lelong/surat_baki.jsp").addParameter("tab", "true");
    }
    public Resolution suratbaki1() {
        return new JSP("lelong/surat_baki1.jsp").addParameter("tab", "true");
    }

    public Resolution semakbaki() {
//        return new ForwardResolution("/WEB-INF/jsp/lelong/semak_baki.jsp");
        return new JSP("lelong/semak_baki.jsp").addParameter("tab", "true");
    }

    public Resolution rekodcek() {
        return new ForwardResolution("/WEB-INF/jsp/lelong/rekod_cek.jsp");
    }

    public Resolution borang16I() {
        return new JSP("lelong/borang16I.jsp").addParameter("tab", "true");
    }

    public Resolution terimamaklumatnotis2() {
        return new ForwardResolution("/WEB-INF/jsp/lelong/terima_maklumat_notis2.jsp");
    }

    public Resolution kemaskiniborang16I() {
        return new ForwardResolution("/WEB-INF/jsp/lelong/kemaskini_borang16I.jsp");
    }

    public Resolution rekodPenghantaran() {
//        return new ForwardResolution("/WEB-INF/jsp/lelong/kemasukan_rekod.jsp");
        return new JSP("lelong/kemasukan_rekod.jsp").addParameter("tab", "true");

    }
public Resolution tangguhBatal() {

        return new JSP("lelong/kemasukan_pemohonan_bataltangguh.jsp").addParameter("tab", "true");

    }
public Resolution tarikhLelongBarungguhBatal() {

        return new JSP("lelong/tetap_tarikh_lelongan_baru.jsp").addParameter("tab", "true");

    }
public Resolution borang16P() {

        return new JSP("lelong/penyediaan_borang16P.jsp").addParameter("tab", "true");

    }
public Resolution sijilRujukan() {

        return new JSP("lelong/penyediaan_sijil_rujukan.jsp").addParameter("tab", "true");

    }
public Resolution penyataAkaun() {

        return new JSP("lelong/penyata_akaun.jsp").addParameter("tab", "true");

    }
public Resolution penyataAkaun1() {

        return new JSP("lelong/penyata_akaun1.jsp").addParameter("tab", "true");

    }
public Resolution semakpenyataAkaun() {

        return new JSP("lelong/semak_terima_penyata_akaun.jsp").addParameter("tab", "true");

    }
public Resolution jual1() {

        return new JSP("lelong/permohonan_perintah_jualan.jsp").addParameter("tab", "true");

    }
public Resolution hakmilik() {

        return new JSP("lelong/maklumat_hakmilik_tanah.jsp").addParameter("tab", "true");

    }
public Resolution pihakberkepentingan() {

        return new JSP("lelong/maklumat_pihak_berkepentingan.jsp").addParameter("tab", "true");

    }
public Resolution borang16G16D() {

        return new JSP("lelong/maklumat_16G_16D.jsp").addParameter("tab", "true");

    }
public Resolution maklumatperihaltanah() {

        return new JSP("lelong/maklumat_perihal_tanah.jsp").addParameter("tab", "true");

    }
public Resolution maklumatPemohon() {

        return new JSP("lelong/maklumat_pemohon.jsp").addParameter("tab", "true");

    }
public Resolution maklumatTanah() {

        return new JSP("lelong/maklumat_tanah.jsp").addParameter("tab", "true");

    }
public Resolution maklumatPerintahJualan() {

        return new JSP("lelong/maklumat_perintah_jualan.jsp").addParameter("tab", "true");

    }
public Resolution perihaltanah() {

        return new JSP("lelong/perihal_tanah.jsp").addParameter("tab", "true");

    }
public Resolution tetapenkuiri() {

        return new JSP("lelong/tetap_enkuiri.jsp").addParameter("tab", "true");

    }
public Resolution peguamJurulelong() {

        return new JSP("lelong/maklumat_peguam_jurulelong_berlesen.jsp").addParameter("tab", "true");

    }
public Resolution peguamJurulelong1() {

        return new JSP("lelong/maklumat_peguam_jurulelong_berlesen1.jsp").addParameter("tab", "true");

    }
public Resolution semakDraf() {

        return new JSP("lelong/semak_drafPengisytiharan.jsp").addParameter("tab", "true");

    }
public Resolution maklumatPemegangGadaian() {

        return new JSP("lelong/maklumat_pemegangGadaian.jsp").addParameter("tab", "true");

    }
public Resolution maklumatPerintahJualan1() {

        return new JSP("lelong/maklumat_perintah_jualan1.jsp").addParameter("tab", "true");

    }
public Resolution maklumatPembida() {

        return new JSP("lelong/maklumat_pembida.jsp").addParameter("tab", "true");

    }
public Resolution maklumatPenggadai() {

        return new JSP("lelong/maklumat_penggadai.jsp").addParameter("tab", "true");

    }
public Resolution lantikjurulelong1() {
        return new JSP("lelong/lantik_jurulelong1.jsp").addParameter("tab", "true");

    }
public Resolution tanah_pajakkecil() {
        return new JSP("lelong/maklumat_tanah_pajakkecil.jsp").addParameter("tab", "true");

    }

}
