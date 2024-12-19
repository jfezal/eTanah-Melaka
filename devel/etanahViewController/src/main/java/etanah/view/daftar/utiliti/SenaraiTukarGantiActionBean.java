/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 * 
 * This utility is used to show all 'URUSAN TUKARGANTI'.
 * apps need to check table hakmilik_lama, if no data 
 * found in hakmilik_lama, means urusan has been undo 
 * by user using utilii undo tukarganti
 * 
 * 
 */
package etanah.view.daftar.utiliti;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.model.Pengguna;
import etanah.model.HakmilikLama;
import etanah.model.HakmilikUrusan;
import etanah.service.RegService;
import etanah.service.daftar.KutipanDataService;
import etanah.view.etanahActionBeanContext;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.hibernate.Session;

/**
 *
 * @author azri
 */
@UrlBinding("/daftar/utiliti/senaraiTukarGanti")
public class SenaraiTukarGantiActionBean extends AbleActionBean {

  @Inject
  protected com.google.inject.Provider<Session> sessionProvider;
  @Inject
  private etanah.Configuration conf;
  @Inject
  private KutipanDataService kutipanDataService;
  @Inject
  private RegService regService;
  private String trhMula;
  private String trhTamat;
  private String reportName;
  private Pengguna pguna;
  private HakmilikLama hakmilikLama;
  private List<HakmilikUrusan> listHakmilikUrusan = new ArrayList();
  private List<Map<String, String>> listTukarganti;
  private static final Logger LOG = Logger.getLogger(SenaraiTukarGantiActionBean.class);
//  SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
  SimpleDateFormat dateTimeFormat = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss aaa");
  private static String TUKARGANTI_MAIN = "daftar/utiliti/senarai_tukarganti.jsp";

  @DefaultHandler
  public Resolution undoSingle() {
    /* MAIN PAGE FOR SENARAI TUKARGANTI */
    return new JSP(TUKARGANTI_MAIN);
  }

  public Resolution seterusnya() {
    etanahActionBeanContext ctx = (etanahActionBeanContext) getContext();
    pguna = ctx.getUser();
    reportName = "TUKAR_GANTI_1.rdf"; // .rdf report name

    listTukarganti = new ArrayList<Map<String, String>>();
    Map<String, String> map = null;

    LOG.info(" > Tarikh Mula : " + trhMula);
    LOG.info(" > Tarikh Tamat : " + trhTamat);

    if (StringUtils.isNotBlank(trhMula) && StringUtils.isNotBlank(trhTamat)) {
      listHakmilikUrusan = kutipanDataService.findListUrusanTukargantibyDate(trhMula, trhTamat);

      if (listHakmilikUrusan.isEmpty()) {
        addSimpleError("Tiada Maklumat Dalam Rekod.");
        return new JSP(TUKARGANTI_MAIN);
      }

      /* FILTER DATA FROM HAKMILIK_URUSAN FOR URUSAN HKHTK, HSTHK, HMSC */
      for (HakmilikUrusan hu : listHakmilikUrusan) {
        hakmilikLama = regService.searchHakmiliklama(hu.getHakmilik().getIdHakmilik());

        /* GET DATA FROM HAKMILIK LAMA */
        if (hakmilikLama != null) {
          Date d = hakmilikLama.getInfoAudit().getTarikhMasuk();
          String tarikhMasuk = "";
          if (d != null) {
            // convert tarikh format
            tarikhMasuk = dateTimeFormat.format(d);
          }
          map = new HashMap<String, String>();
          map.put("idPermohonan", hu.getIdPerserahan());
          map.put("namaUrusan", hu.getKodUrusan().getNama());
          map.put("idHakmilik", hakmilikLama.getIdHakmilik());
          map.put("tarikhMasuk", tarikhMasuk);
          map.put("pegawai", hakmilikLama.getPegawaiJilid16());
          listTukarganti.add(map);
        }
      }

      if (listTukarganti.isEmpty()) {
        addSimpleError("Tiada Maklumat Dalam Rekod.");
      }

    } else {
      addSimpleError("Sila Masukkan Tarikh Mula Dan Tarikh Tamat.");
    }
    return new JSP(TUKARGANTI_MAIN);
  }

  public String getTrhMula() {
    return trhMula;
  }

  public void setTrhMula(String trhMula) {
    this.trhMula = trhMula;
  }

  public String getTrhTamat() {
    return trhTamat;
  }

  public void setTrhTamat(String trhTamat) {
    this.trhTamat = trhTamat;
  }

  public Pengguna getPguna() {
    return pguna;
  }

  public void setPguna(Pengguna pguna) {
    this.pguna = pguna;
  }

  public HakmilikLama getHakmilikLama() {
    return hakmilikLama;
  }

  public void setHakmilikLama(HakmilikLama hakmilikLama) {
    this.hakmilikLama = hakmilikLama;
  }

  public List<HakmilikUrusan> getListHakmilikUrusan() {
    return listHakmilikUrusan;
  }

  public void setListHakmilikUrusan(List<HakmilikUrusan> listHakmilikUrusan) {
    this.listHakmilikUrusan = listHakmilikUrusan;
  }

  public List<Map<String, String>> getListTukarganti() {
    return listTukarganti;
  }

  public void setListTukarganti(List<Map<String, String>> listTukarganti) {
    this.listTukarganti = listTukarganti;
  }

  public String getReportName() {
    return reportName;
  }

  public void setReportName(String reportName) {
    this.reportName = reportName;
  }
}
