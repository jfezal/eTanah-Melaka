package etanah.view.kaunter.validator;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.google.inject.Inject;

import etanah.dao.HakmilikDAO;
import etanah.dao.PermohonanDAO;
import etanah.model.Enkuiri;
import etanah.model.Hakmilik;
import etanah.model.KodUrusan;
import etanah.model.Lelongan;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.model.PermohonanAsal;
import etanah.view.kaunter.UrusanPermohonan;
import java.math.BigDecimal;
import org.apache.commons.lang.StringUtils;
import org.hibernate.Session;

public class UrusanPendaftaranValidator {

    @Inject
    HakmilikDAO hakmilikDAO;
    @Inject
    PermohonanDAO permohonanDAO;
    private static final Logger LOG = Logger.getLogger(UrusanPendaftaranValidator.class);
    private static final boolean debug = LOG.isDebugEnabled();
    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;

    public List<String> onStep3(KodUrusan kodUrusan, UrusanPermohonan urusanPermohonan, Pengguna pengguna) {
        if (debug) {
            LOG.debug("validating step 3");


        }
        ArrayList<String> errorMessages = new ArrayList<String>();

        boolean penggunaDaerah = pengguna.getKodCawangan().getDaerah() != null;

        // check for each hakmilik
        if (urusanPermohonan.getHakmilikPermohonan() != null && urusanPermohonan.getHakmilikPermohonan().size() > 0) {
            for (String idHakmilik : urusanPermohonan.getHakmilikPermohonan()) {
                if (idHakmilik != null && idHakmilik.trim().length() > 0) {
                    if (debug) {
                        LOG.debug("checking " + idHakmilik);
                    }
                    Hakmilik h = hakmilikDAO.findById(idHakmilik);
                    if (h != null) {
                        boolean hakmilikDaerah = h.getKodHakmilik().getMilikDaerah() == 'Y';
                        if (penggunaDaerah != hakmilikDaerah) {
                            errorMessages.add("Hakmilik " + idHakmilik + " tidak boleh diuruskan di "
                                    + "Pejabat Pendaftaran ini!");
                            return errorMessages;
                        }
                    }
                }
            }

            //add amaun lelong in amaun1 for JPGD or JPGPJ
            if (StringUtils.isNotBlank(urusanPermohonan.getKodUrusan()) && StringUtils.isNotBlank(urusanPermohonan.getIdPermohonanSebelum()) && (urusanPermohonan.getKodUrusan().equals("JPGD") || urusanPermohonan.getKodUrusan().equals("JPGPJ"))) {
                Permohonan permohonan = permohonanDAO.findById(urusanPermohonan.getIdPermohonanSebelum());
                if (permohonan.getKodUrusan().getKod().equals("PPJP") || permohonan.getKodUrusan().getKod().equals("PPTL") || permohonan.getKodUrusan().getKod().equals("PJTA")) {
                    Session session = sessionProvider.get();
                    String idPermohonan = "";
                    if (permohonan.getPermohonanSebelum() != null) {
                        List<PermohonanAsal> listPap = session.createQuery("select pa from PermohonanAsal pa "
                                + "where pa.idPermohonan.idPermohonan = :idPermohonan").setString("idPermohonan", idPermohonan).list();
                        if (listPap.isEmpty()) {
                            idPermohonan = permohonan.getPermohonanSebelum().getIdPermohonan();
                        } else {
                            idPermohonan = listPap.get(0).getIdPermohonanAsal().getIdPermohonan();
                        }
                    }else{
                        idPermohonan = permohonan.getIdPermohonan();
                    }
                    List<Enkuiri> listEnkuiri = session.createQuery("select en from Enkuiri en where en.permohonan.id =:idPermohonan "
                            + "order by en.id desc").setString("idPermohonan", idPermohonan).list();
                    if (listEnkuiri.size() > 0) {
                        BigDecimal amaunLelong = BigDecimal.ZERO;
                        Enkuiri en = listEnkuiri.get(0);
                        if (en.getCaraLelong().equals("B")) {
                            LOG.info("B");
                            LOG.info("en.getHargaBida() : " + en.getHargaBida());
                            amaunLelong = amaunLelong.add(en.getHargaBida());
                        } else {
                            LOG.info("A");
                            List<String> idHakmilik = new ArrayList<String>();
                            for (String id : urusanPermohonan.getHakmilikPermohonan()) {
                                if (id != null && id.trim().length() > 0) {
                                    idHakmilik.add(id);
                                }
                            }
                            List<Lelongan> listLelong = session.createQuery("select l from Lelongan l where l.permohonan.id =:idPermohonan "
                                    + "and l.kodStatusLelongan.kod = 'SL' and l.hakmilikPermohonan.hakmilik.idHakmilik in (:list)").setString("idPermohonan", urusanPermohonan.getIdPermohonanSebelum()).setParameterList("list", idHakmilik).list();
                            for (Lelongan ll : listLelong) {
                                LOG.info("ll.getHargaBidaan() : " + ll.getHargaBidaan());
                                amaunLelong = amaunLelong.add(ll.getHargaBidaan());
                            }
                        }
                        LOG.info("amaunLelong : " + amaunLelong);
                        urusanPermohonan.setAmaun1(amaunLelong);
                    }
                }
            }
        }
        return null;
    }
}
