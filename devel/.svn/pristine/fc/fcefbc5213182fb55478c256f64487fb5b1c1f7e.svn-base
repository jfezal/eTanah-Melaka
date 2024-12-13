package etanah.view.kaunter;

import com.google.inject.Inject;

import etanah.dao.KodUrusanDAO;
import etanah.model.Dokumen;
import etanah.model.DokumenKewangan;
import etanah.model.KodUrusan;
import etanah.model.Pengguna;
import etanah.model.Permit;
import etanah.model.PermitSahLaku;
import etanah.view.kaunter.validator.UrusanPelupusanValidator;
import etanah.view.kaunter.validator.UrusanPendaftaranValidator;
import etanah.view.kaunter.validator.UrusanPesakaValidator;

import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.hibernate.Session;

/**
 * Validate Urusan submitted thru PermohonanKaunter. method
 * validateUrusan:Compile the Urusan. This is called in last step of Pendaftaran
 * Urusan. If error returned, the step cannot proceed.
 */
public class UrusanValidator {

    private static final Logger LOG = Logger.getLogger(UrusanValidator.class);
    private static final boolean debug = LOG.isDebugEnabled();

    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;

    @Inject
    UrusanStrataValidator urusanStrataValidator;

    @Inject
    UrusanConsentValidator urusanConsentValidator;

    @Inject
    UrusanPendaftaranValidator urusanPendaftaranValidator;

    @Inject
    UrusanPelupusanValidator urusanPelupusanValidator;

    @Inject
    UrusanPesakaValidator urusanPesakaValidator;

    @Inject
    UrusanHasilValidator urusanHasilValidator;

    @Inject
    KodUrusanDAO kodUrusanDAO;

    /**
     * validation done at step 1 pilih urusan
     *
     * @return list of errors if any
     */
    public List<String> onStep1(UrusanPermohonan urusanPermohonan, Pengguna pengguna) {
        KodUrusan ku = kodUrusanDAO.findById(urusanPermohonan.kodUrusan);

        return null;
    }

    /**
     * validation done at step 2 senarai semakan
     *
     * @return list of errors if any
     */
    public List<String> onStep2(UrusanPermohonan urusanPermohonan, Pengguna pengguna) {
        KodUrusan ku = kodUrusanDAO.findById(urusanPermohonan.kodUrusan);

        return null;
    }

    /**
     * validation done at step 3 senarai hakmilik
     *
     * @return list of errors if any
     */
    public List<String> onStep3(UrusanPermohonan urusanPermohonan, Pengguna pengguna) {
        if (debug) {
            LOG.debug("validating step 3");
        }

        KodUrusan ku = kodUrusanDAO.findById(urusanPermohonan.kodUrusan);

        String kj = ku.getJabatan().getKod();
        if ("16".equals(kj)) { // Pendaftaran
            return urusanPendaftaranValidator.onStep3(ku, urusanPermohonan, pengguna);
        }

        return null;
    }

    /**
     * validation done at step 4 extra info
     *
     * @return list of errors if any
     */
    public List<String> onStep4(UrusanPermohonan urusanPermohonan, Pengguna pengguna) {
        List<String> error = new ArrayList<String>();
        KodUrusan ku = kodUrusanDAO.findById(urusanPermohonan.kodUrusan);
        if(ku.getKod().equals("BACT")){
        DokumenKewangan resit = urusanHasilValidator.findResitHasil(urusanPermohonan.hakmilikPermohonan, urusanPermohonan.teks1);
        if (resit == null) {
            error.add("Sila semak resit bayaran deposit");
            return error;
        }
        }
        return null;
    }

    public List<String> validateUrusan(UrusanPermohonan urusan) {
        List<String> errors = null;
        Session s = sessionProvider.get();

        KodUrusan ku = kodUrusanDAO.findById(urusan.kodUrusan);
        String jab = ku.getJabatan().getKod();

        // PELUPUSAN
        if (jab.equals("2")) {
            errors = urusanPelupusanValidator.validate(urusan);

        } else if (jab.equals("20")) {
            errors = urusanStrataValidator.validate(urusan);

        } else if (jab.equals("22")) {
            errors = urusanConsentValidator.validate(urusan);
        } else if (jab.equals("16")) {
            errors = urusanPesakaValidator.validate(urusan);
        }

        return errors;
    }
}
