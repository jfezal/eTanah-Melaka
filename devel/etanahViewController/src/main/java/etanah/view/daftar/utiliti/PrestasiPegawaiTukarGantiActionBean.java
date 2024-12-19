/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.daftar.utiliti;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.dao.KodNegeriDAO;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import etanah.model.HakmilikLama;
import etanah.model.HakmilikUrusan;
import etanah.model.KodDaerah;
import etanah.model.KodNegeri;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.service.RegService;
import etanah.view.etanahActionBeanContext;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.controller.LifecycleStage;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author azri
 */
@UrlBinding("/daftar/utiliti/prestasiPegawai")
public class PrestasiPegawaiTukarGantiActionBean extends AbleActionBean {

  @Inject
  private etanah.Configuration conf;
  @Inject
  protected com.google.inject.Provider<Session> sessionProvider;
  @Inject
  private KodNegeriDAO kodNegeriDAO;
  @Inject
  private RegService regService;
  private String kodNegeri;
  private String namaNegeri;
  private String kodDaerah;
  private String pegawaiTukarganti;
  private List<HakmilikLama> listHakmilikLama = new ArrayList();
  private List<KodDaerah> listKodDaerah = new ArrayList();
  private List<Pengguna> listPegawai = new ArrayList();
  private List<Map<String, String>> listTukarGanti;
  private Pengguna pguna;
  SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm aa");
  private static final Logger LOG = Logger.getLogger(PrestasiPegawaiTukarGantiActionBean.class);
  private static String PRESTASI_MAIN = "daftar/utiliti/prestasi_pegawai_tukar_ganti_main.jsp";

  @DefaultHandler
  public Resolution prestasiMain() {
    /* MAIN PAGE FOR PRESTASI PEGAWAI TUKARGATI */
    LOG.info("MASUK PRESTASI PEGAWAI TUKAR GANTI");
    return new JSP(PRESTASI_MAIN);
  }

  @Before(stages = {LifecycleStage.BindingAndValidation})
  public void rehydrate() {
    etanahActionBeanContext ctx = (etanahActionBeanContext) getContext();
    pguna = ctx.getUser();
    kodNegeri = conf.getProperty("kodNegeri");
    KodNegeri kn = kodNegeriDAO.findById(kodNegeri);
    namaNegeri = kn.getNama();
    listPegawai = findListPendaftar(pguna.getKodCawangan().getKod());
    LOG.info(" Jumlah senarai pegawai caw " + pguna.getKodCawangan().getName() + " : " + listPegawai.size());
  }

  public Resolution seterusnya() {
    if (StringUtils.isNotBlank(pegawaiTukarganti)) {
      listHakmilikLama = findListHakmilikLama(pegawaiTukarganti);  // find list data by id pegawai_16

      listTukarGanti = new ArrayList();
      for (HakmilikLama hmLama : listHakmilikLama) {
        // get list of Hakmilik_urusan. asumed only registered urusan will be saved here
        List<HakmilikUrusan> lhu = findListUrusanByHakmilik(hmLama.getIdHakmilik());
        for (HakmilikUrusan hu : lhu) {
          // check each urusan's status is 'D'. // FIX ME
          Permohonan mohon = findIdMohon(hu.getIdPerserahan());
          if (mohon != null) {
            Map<String, String> map = new HashMap<String, String>();
            map.put("idHakmilik", hu.getHakmilik().getIdHakmilik());
            map.put("kodUrusan", mohon.getKodUrusan().getKod());
            map.put("namaUrusan", mohon.getKodUrusan().getNama());
            String tarikhDaftar = sdf.format(mohon.getInfoAudit().getTarikhMasuk());
            map.put("tarikhDaftar", tarikhDaftar);
            map.put("namaPegawai", hmLama.getPenggunaJilid16().getNama());
            String tarikhTukarganti = sdf.format(hmLama.getTarikhJilid16());
            map.put("tarikhTukarGanti", tarikhTukarganti);
            listTukarGanti.add(map);
          }
        }
      }
    } else {
      addSimpleError("Sila Masukkan Nama Pegawai.");
    }
    return new JSP(PRESTASI_MAIN);
  }

  public List<Pengguna> findListPendaftar(String kodCaw) {
    // NOTES:
    // perananUtama = 4 <-- pendaftar
    // kodJabatan = 16 <-- module pendaftaran
    
    String query = "SELECT p FROM etanah.model.Pengguna p "
            + "WHERE p.status = 'A' "
            + "and p.perananUtama.kod = '4' "
            + "and p.kodCawangan.kod = :kod "
            + "and p.kodJabatan.kod = '16' "
            + "ORDER BY p.nama asc";
    Session session = sessionProvider.get();
    Query q = session.createQuery(query).setString("kod", kodCaw);
    return q.list();
  }

  public List<HakmilikLama> findListHakmilikLama(String idPegawai) {
    String query = "SELECT p FROM etanah.model.HakmilikLama p "
            + "WHERE p.penggunaJilid16.idPengguna =:idPengguna "
            + "ORDER BY p.idHakmilik asc";
    Session session = sessionProvider.get();
    Query q = session.createQuery(query).setString("idPengguna", idPegawai);
    return q.list();
  }

  public List<HakmilikUrusan> findListUrusanByHakmilik(String idHakmilik) {
    String query = "SELECT hu FROM etanah.model.HakmilikUrusan hu "
            + "WHERE hu.hakmilik.idHakmilik = :idHakmilik "
            + "and hu.kodUrusan.kod in ('HKTHK','HSTHK','HMSC') "
            + "ORDER BY hu.idPerserahan asc";
    Session session = sessionProvider.get();
    Query q = session.createQuery(query).setString("idHakmilik", idHakmilik);
    return q.list();
  }

  public Permohonan findIdMohon(String idPermohonan) {
    String query = "SELECT m FROM etanah.model.Permohonan m "
            + "WHERE m.idPermohonan = :idPermohonan "
            + "and m.keputusan.kod = 'D'";
    Query q = sessionProvider.get().createQuery(query).setString("idPermohonan", idPermohonan);
    return (Permohonan) q.uniqueResult();
  }

  public String getKodNegeri() {
    return kodNegeri;
  }

  public void setKodNegeri(String kodNegeri) {
    this.kodNegeri = kodNegeri;
  }

  public String getNamaNegeri() {
    return namaNegeri;
  }

  public void setNamaNegeri(String namaNegeri) {
    this.namaNegeri = namaNegeri;
  }

  public String getKodDaerah() {
    return kodDaerah;
  }

  public void setKodDaerah(String kodDaerah) {
    this.kodDaerah = kodDaerah;
  }

  public String getPegawaiTukarganti() {
    return pegawaiTukarganti;
  }

  public void setPegawaiTukarganti(String pegawaiTukarganti) {
    this.pegawaiTukarganti = pegawaiTukarganti;
  }

  public List<HakmilikLama> getListHakmilikLama() {
    return listHakmilikLama;
  }

  public void setListHakmilikLama(List<HakmilikLama> listHakmilikLama) {
    this.listHakmilikLama = listHakmilikLama;
  }

  public List<KodDaerah> getListKodDaerah() {
    return listKodDaerah;
  }

  public void setListKodDaerah(List<KodDaerah> listKodDaerah) {
    this.listKodDaerah = listKodDaerah;
  }

  public List<Pengguna> getListPegawai() {
    return listPegawai;
  }

  public void setListPegawai(List<Pengguna> listPegawai) {
    this.listPegawai = listPegawai;
  }

  public Pengguna getPguna() {
    return pguna;
  }

  public void setPguna(Pengguna pguna) {
    this.pguna = pguna;
  }

  public List<Map<String, String>> getListTukarGanti() {
    return listTukarGanti;
  }

  public void setListTukarGanti(List<Map<String, String>> listTukarGanti) {
    this.listTukarGanti = listTukarGanti;
  }
}
