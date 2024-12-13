package etanah.view.kaunter;

import able.stripes.AbleActionBean;
import com.google.inject.Inject;
import etanah.model.Hakmilik;
import etanah.model.KodCawangan;
import etanah.model.KodDaerah;
import etanah.model.Pengguna;
import etanah.service.common.HakmilikService;
import etanah.view.etanahActionBeanContext;
import java.util.List;
import net.sourceforge.stripes.action.HttpCache;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.StreamingResolution;
import net.sourceforge.stripes.action.UrlBinding;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

/**
 *
 * @author ${user}
 */
@HttpCache(allow = false)
@UrlBinding("/daftar/check_idhakmilik_strata")
public class RequestValidateHakmilikStrata extends AbleActionBean {

    public String noBangunan;
    public String noPetak;
    public String noTingkat;
    public String idHakmilik;
    public String hmstrata;
    @Inject
    HakmilikService hakmilikService;
    private static Logger LOG = Logger.getLogger(RequestValidateHakmilikStrata.class);
    private static boolean debug = LOG.isDebugEnabled();

    public Resolution doCheckHakmilikCarianStrata() {
        if (debug) {
            LOG.debug("*****RequestValidateHakmilikStrata.doCheckHakmilikCarianStrata:hakmilik :" + idHakmilik);
            LOG.debug("*****RequestValidateHakmilikStrata.doCheckHakmilikCarianStrata:petak :" + noPetak);
            LOG.debug("*****RequestValidateHakmilikStrata.doCheckHakmilikCarianStrata:bangunan :" + noBangunan);
            LOG.debug("*****RequestValidateHakmilikStrata.doCheckHakmilikCarianStrata:tingkat :" + noTingkat);
            LOG.debug("*****RequestValidateHakmilikStrata.doCheckHakmilikCarianStrata:hmstrata :" + hmstrata);
        }

        etanahActionBeanContext ctx = (etanahActionBeanContext) getContext();
        Pengguna pengguna = ctx.getUser();
        KodCawangan caw = pengguna.getKodCawangan();
        KodDaerah daerah = caw.getDaerah();
        String kodDaerah = null;

        Hakmilik hakmilikStrata = hakmilikService.findById(hmstrata);
        if (hakmilikStrata != null) {
            kodDaerah = hakmilikStrata.getDaerah().getKod();
            noTingkat = hakmilikStrata.getNoTingkat();
        }

        String results = "0";

        if (StringUtils.isNotBlank(idHakmilik)) {
            List<Hakmilik> h = hakmilikService.findHakmilikInDaerah(idHakmilik, kodDaerah, noPetak, noBangunan, noTingkat);
            LOG.info("hakmilik--" + h);
            if (h == null || h.isEmpty()) {
                results = "1";
            } else {
                if (hmstrata != null) {
//                    Hakmilik hakmilikStrata = hakmilikService.findById(hmstrata);
                    if (hakmilikStrata != null) {
                        if (hakmilikStrata.getNoVersiDhde() == 0) {
                            results = "3";
                        }
                        if (!(hakmilikStrata.getCawangan().getKod().equals(pengguna.getKodCawangan().getKod()))) {
                            results = "2";
                        }
                    }
                }
            }

        }

        return new StreamingResolution("text/plain", results);
    }
}
