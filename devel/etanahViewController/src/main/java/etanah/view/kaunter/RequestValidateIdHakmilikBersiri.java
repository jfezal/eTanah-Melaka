package etanah.view.kaunter;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Session;

import com.google.inject.Inject;

import etanah.dao.HakmilikDAO;
import etanah.model.Akaun;
import etanah.model.Hakmilik;

import net.sourceforge.stripes.action.HttpCache;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.StreamingResolution;
import net.sourceforge.stripes.action.UrlBinding;
import able.stripes.AbleActionBean;
import etanah.model.HakmilikPermohonan;
import etanah.model.HakmilikSebelumPermohonan;
import etanah.model.HakmilikUrusan;
import etanah.service.RegService;
import etanah.service.common.HakmilikUrusanService;
import java.io.StringReader;
import org.apache.commons.lang.StringUtils;

@HttpCache(allow = false)
@UrlBinding("/daftar/check_siri_idhakmilik")
public class RequestValidateIdHakmilikBersiri extends AbleActionBean {

  @Inject
  protected com.google.inject.Provider<Session> sessionProvider;
  public String idHakmilikDari;
  public String idHakmilikKe;
  public String idHakmilik;
  public String kodSerah;
  public String kodUrusan;
  private static final Logger LOG = Logger.getLogger(RequestValidateIdHakmilikBersiri.class);
  private static final boolean debug = LOG.isDebugEnabled();
  @Inject
  private HakmilikDAO hakmilikDAO;
  @Inject
  private RegService regService;
  @Inject
  private HakmilikUrusanService hakmilikUrusanService;

  public Resolution doCheckHakmilik() {
    System.out.println("*****RequestValidateIdHakmilik.doCheckHakmilik:hakmilik :"
            + idHakmilikDari + "-" + idHakmilikKe);
    String results = "0";

    if (idHakmilikDari == null || idHakmilikDari.trim().length() == 0
            || idHakmilikKe == null || idHakmilikKe.trim().length() == 0) {
      LOG.warn("invalid idHakmilik bersiri given");
      return new StreamingResolution("text/plain", results);
    }


    // from
    StringBuilder from = new StringBuilder();
    for (int i = idHakmilikDari.length() - 1; i >= 0; i--) {
      char c = idHakmilikDari.charAt(i);
      if (c >= '0' && c <= '9') {
        from.insert(0, c);
      } else {
        break;
      }
    }
    long lFrom = Long.parseLong(from.toString());
    if (debug) {
      LOG.debug("lFrom=" + lFrom);
    }
    String pre = idHakmilikDari.substring(0, idHakmilikDari.length() - from.length());

    // to
    long lTo = 0l;
    try {
      lTo = Long.parseLong(idHakmilikKe.substring(pre.length(), idHakmilikDari.length()));
    } catch (NumberFormatException e) {
      results = "3";
    }
    if (debug) {
      LOG.debug("lTo=" + lTo);
    }

    // validate the series
    if ("3".equals(results)
            || idHakmilikDari.length() != idHakmilikKe.length()
            || !idHakmilikDari.substring(0, pre.length()).equals(idHakmilikKe.substring(0, pre.length()))
            || lTo < lFrom) {
      results = "3";
    } else {

      ArrayList<String> listIdHakmilik = new ArrayList<String>();
      listIdHakmilik.add(idHakmilikDari);
      listIdHakmilik.add(idHakmilikKe);
      DecimalFormat df = (DecimalFormat) DecimalFormat.getInstance();
      df.setGroupingUsed(false);
      df.setMinimumIntegerDigits(from.length());
      for (long l = lFrom + 1; l < lTo; l++) {
        String id = pre + df.format(l);
        listIdHakmilik.add(id);
        System.out.print(id + ",");
      }

      List<Hakmilik> list = sessionProvider.get().createQuery(
              "select h from Hakmilik h inner join fetch h.senaraiAkaun a "
              + "where h.idHakmilik in (:listHakmilik)").setParameterList("listHakmilik", listIdHakmilik).list();

      if (list.size() == (lTo - lFrom + 1)) {
        results = "1";
        StringBuilder hmBatal = new StringBuilder();
        // check if tax paid
        for (Hakmilik h : list) {
            LOG.debug("masuk sini");
          if (!kodSerah.equals("NB")&&!kodSerah.equals("CR")) {
            Akaun ac = h.getAkaunCukai();
            if ((ac == null || ac.getBaki().compareTo(BigDecimal.ZERO) > 0) && (h.getIdHakmilikInduk() == null)) {
              results = "2";
              break;
            }
          }
          
          if (StringUtils.isNotBlank(kodUrusan)
                    && (kodUrusan.equals("SSHM") || kodUrusan.equals("SSHMA") || kodUrusan.equals("CSHM") || kodUrusan.equals("CRHM"))) {
            if (h.getNoVersiDhke() == 0 && h.getNoVersiDhde() == 0) {
                results = "8";
                break;
            }
          }
          
          String st = h.getKodStatusHakmilik().getKod();
          
          
          if ("B".equals(st)) {
            if (StringUtils.isNotBlank(kodUrusan)
                    && (kodUrusan.equals("SSHM") || kodUrusan.equals("CRHMB"))) {
              //do nothing
            } else {
                if (hmBatal.length() > 0) {
                    hmBatal.append(",");
                    }
                 hmBatal.append(h.getIdHakmilik());
            }
          }

          idHakmilik = h.getIdHakmilik();
          LOG.debug("check IDHAKMILIK : " + idHakmilik);
          LOG.debug("kodSerah : " + kodSerah);
          if (StringUtils.isNotBlank(kodSerah) && kodSerah.equals("MH")) {
            String kodUrusan = (String) getContext().getRequest().getParameter("kodUrusan");
            LOG.debug("checkNottingMH:kodUrusan: " + kodUrusan + " idHakmilik : " + idHakmilik);
            StringBuilder message = new StringBuilder();
            LOG.debug("doCheckHakmilikProsesSelainMH");
            List<HakmilikPermohonan> lhp = regService.searchMohonHakmilik(idHakmilik);
            List<HakmilikSebelumPermohonan> lhsp = regService.searchMohonHakmilikSblmByIDHakmilik(idHakmilik);
            List<HakmilikUrusan> lhu = hakmilikUrusanService.findUrusanNottingMH(idHakmilik, kodUrusan);
            /*check urusan dalam proses*/
            if (lhp.size() > 0) {
              for (HakmilikPermohonan hakmilikPermohonan : lhp) {
                if (!hakmilikPermohonan.getPermohonan().getStatus().equals("SL") && !hakmilikPermohonan.getPermohonan().getStatus().equals("TK")) {
                  message.append("Hakmilik ini masih mempunyai urusan " + hakmilikPermohonan.getPermohonan().getKodUrusan().getKod() + " " + hakmilikPermohonan.getPermohonan().getIdPermohonan() + " yang belum selesai\n");
                  results = "4";
                }
              }
            }
            /*check urusan dalam proses MH*/
            if (lhsp.size() > 0) {
              for (HakmilikSebelumPermohonan hakmilikSebelumPermohonan : lhsp) {
                if (!hakmilikSebelumPermohonan.getPermohonan().getStatus().equals("SL") && !hakmilikSebelumPermohonan.getPermohonan().getStatus().equals("TK")) {
                  //message.append("Hakmilik mempunyai urusan " + hakmilikSebelumPermohonan.getPermohonan().getKodUrusan().getKod() + ": idPerserahan : " + hakmilikSebelumPermohonan.getPermohonan().getIdPermohonan() + "\n");
                  message.append("Hakmilik ini masih mempunyai urusan " + hakmilikSebelumPermohonan.getPermohonan().getKodUrusan().getKod() + " " + hakmilikSebelumPermohonan.getPermohonan().getIdPermohonan() + " yang belum selesai\n");
                  results = "5";
                }
              }

            }
            /*check notting*/
            if (lhu.size() < 1) {
              message.append("Hakmilik ini " + idHakmilik + " tidak mempunyai notting berkaitan untuk meneruskan urusan");
              results = "6";
            }
          }
          if (kodUrusan.equals("HKTHK") || kodUrusan.equals("HSTHK") || kodUrusan.equals("HMSC")) {
            LOG.info("--> kod URUSAN : " + kodUrusan);
            if (h.getNoVersiDhde() != 0) {
              results = "11";
              break;
            }
          }
        }
        //check if there is id Hakmilik Batal
        if(hmBatal.length()>0){
            results="7 "+hmBatal;
        }
      }
    }
    LOG.info("result : " + results);
    return new StreamingResolution("text/plain", results);
  }

  public Resolution checkHakmilikSelainMH() {

    String kodUrusan = (String) getContext().getRequest().getParameter("kodUrusan");
    LOG.debug("checkNottingMH:kodUrusan: " + kodUrusan + " idHakmilik : " + idHakmilik);
    StringBuilder message = new StringBuilder();
    LOG.debug("doCheckHakmilikProsesSelainMH");
    List<HakmilikPermohonan> lhp = regService.searchMohonHakmilik(idHakmilik);
    List<HakmilikSebelumPermohonan> lhsp = regService.searchMohonHakmilikSblmByIDHakmilik(idHakmilik);
    List<HakmilikUrusan> lhu = hakmilikUrusanService.findUrusanNottingMH(idHakmilik, kodUrusan);
    if (lhp.size() > 0) {
      for (HakmilikPermohonan hakmilikPermohonan : lhp) {
        if (!hakmilikPermohonan.getPermohonan().getStatus().equals("SL") && !hakmilikPermohonan.getPermohonan().getStatus().equals("TK")) {
          message.append("Hakmilik ini masih mempunyai urusan " + hakmilikPermohonan.getPermohonan().getKodUrusan().getKod() + " " + hakmilikPermohonan.getPermohonan().getIdPermohonan() + " yang belum selesai\n");
        }
      }
    }
    if (lhsp.size() > 0) {
      for (HakmilikSebelumPermohonan hakmilikSebelumPermohonan : lhsp) {
        if (!hakmilikSebelumPermohonan.getPermohonan().getStatus().equals("SL") && !hakmilikSebelumPermohonan.getPermohonan().getStatus().equals("TK")) {
          message.append("Hakmilik ini masih mempunyai urusan " + hakmilikSebelumPermohonan.getPermohonan().getKodUrusan().getKod() + " " + hakmilikSebelumPermohonan.getPermohonan().getIdPermohonan() + " yang belum selesai\n");
        }
      }

    }
    if (lhu.size() < 1) {
      message.append("Hakmilik ini " + idHakmilik + " tidak mempunyai notting berkaitan untuk meneruskan urusan");
    }
    return new StreamingResolution("text/html", new StringReader(message.toString()));
  }

  // add by azri 26/8/2013: currently used in carian only..
  public Resolution doCheckHakmilikBersiriStrata() {
    LOG.info("*****RequestValidateIdHakmilik.doCheckHakmilikStrata: hakmilik Strata : "
            + idHakmilikDari + "-" + idHakmilikKe);
    String results = "0";

    if (idHakmilikDari == null || idHakmilikDari.trim().length() == 0
            || idHakmilikKe == null || idHakmilikKe.trim().length() == 0) { // no hakmilik are insert so do this
      LOG.warn("invalid idHakmilik bersiri given");
      return new StreamingResolution("text/plain", results);
    }

    // from
    StringBuilder from = new StringBuilder();
    for (int i = idHakmilikDari.length() - 1; i >= 0; i--) {
      char c = idHakmilikDari.charAt(i);
      if (c >= '0' && c <= '9') { // need to fix this
        from.insert(0, c);
      } else {
        break;
      }
    }

    long lFrom = Long.parseLong(from.toString());
    LOG.info("lFrom=" + lFrom);

    String pre = idHakmilikDari.substring(0, idHakmilikDari.length() - from.length());

    // to
    long lTo = 0l;
    try {
      lTo = Long.parseLong(idHakmilikKe.substring(pre.length(), idHakmilikDari.length()));
    } catch (NumberFormatException e) {
      results = "3";
    }
    LOG.info("lTo=" + lTo);

    // validate the series
    if ("3".equals(results)
            || idHakmilikDari.length() != idHakmilikKe.length()
            || !idHakmilikDari.substring(0, pre.length()).equals(idHakmilikKe.substring(0, pre.length()))
            || lTo < lFrom) {
      results = "3";
    } else {
      ArrayList<String> listIdHakmilik = new ArrayList<String>();
      listIdHakmilik.add(idHakmilikDari);
      listIdHakmilik.add(idHakmilikKe);
      DecimalFormat df = (DecimalFormat) DecimalFormat.getInstance();
      df.setGroupingUsed(false);
      df.setMinimumIntegerDigits(from.length());
      for (long l = lFrom + 1; l < lTo; l++) {
        String id = pre + df.format(l);
        listIdHakmilik.add(id);
        LOG.info(id + ",");
      }
      LOG.info("--> listIdHakmilik.size() : " + listIdHakmilik.size());
      List<Hakmilik> list = sessionProvider.get().createQuery(
              "select h from Hakmilik h where h.idHakmilik in (:listHakmilik)").setParameterList("listHakmilik", listIdHakmilik).list();

      if (list.size() == (lTo - lFrom + 1)) {
        results = "1";

        // check if tax paid
        for (Hakmilik h : list) {
          idHakmilik = h.getIdHakmilik(); // get id hakmilik
          String st = h.getKodStatusHakmilik().getKod(); // check status hakmilik

          LOG.info("--> check id Hakmilik : " + idHakmilik);
          LOG.info("--> Status Hakmilik : " + st);
          LOG.info("--> kodSerah : " + kodSerah);

          if ("B".equals(st)) { // Batal
            LOG.info(" /* id hakmilik Batal */");
            results = "7";
            break;
          }
        }
      }
    }
    LOG.info("--> Return Result : " + results);
    return new StreamingResolution("text/plain", results);
  }
}
