/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.kaunter;

import java.util.ArrayList;
import java.util.List;

import com.google.inject.Inject;
import etanah.dao.HakmilikDAO;
import etanah.model.FasaPermohonan;
import etanah.model.Hakmilik;
import etanah.model.HakmilikPermohonan;
import etanah.model.Permohonan;
import etanah.service.StrataPtService;
import etanah.util.StringUtils;
import etanah.view.strata.CommonService;
import org.apache.log4j.Logger;
import etanah.model.PermohonanBangunan;
import etanah.model.PermohonanHartaBersama;
import etanah.model.Projek;
import etanah.model.strata.BadanPengurusan;
import java.text.SimpleDateFormat;

/**
 *
 * @author faidzal
 */
public class UrusanStrataValidator {

    @Inject
    HakmilikDAO hakmilikDAO;
    @Inject
    StrataPtService strService;
    @Inject
    CommonService commonService;
    @Inject
    etanah.Configuration conf;
    private List<PermohonanBangunan> bng;
    private List<PermohonanBangunan> lp;
    private static final Logger LOG = Logger.getLogger(UrusanStrataValidator.class);
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

    public ArrayList<String> validate(UrusanPermohonan urusan) {
        ArrayList<String> errors = new ArrayList<String>();
        Permohonan permohonan = null;
        if (urusan.getKodUrusan().equals("PNB")) {
            for (String idHakmilik : urusan.getHakmilikPermohonan()) {
                Hakmilik hakmilik = hakmilikDAO.findById(idHakmilik);
                if (StringUtils.isBlank(hakmilik.getNoBukuDaftarStrata())) {
                    permohonan = strService.findPermohonanByHakmilik(idHakmilik, null);
                    if (permohonan != null) {
                        FasaPermohonan mohonFasa = strService.findFasaPermohonanByIdAliran(permohonan.getIdPermohonan(), "g_keputusan2");
                        if (mohonFasa != null) {
                            errors.add("hakmilik ini telah berada di peringkat keputusan.");
                        } else {
                            urusan.setIdPermohonanSebelum(permohonan.getIdPermohonan());
                        }
                    }
                } else {
                    errors.add("Pendaftaran strata telah dilakukan pada hakmilik ini.");
                }

                LOG.info("Permohonan sblm ::" + permohonan);
            }
            if (permohonan == null) {
                errors.add("Hakmilik yang dimasukkan belum memohon strata");
            }
            if (urusan.getHakmilikPermohonan().isEmpty()) {
                errors.add("Sila masukkan hakmilik untuk urusan ini");
            }
        }

        if (conf.getProperty("kodNegeri").equals("05")) {
            if (urusan.getKodUrusan().equals("PNSS")) {
                for (String idHakmilik : urusan.getHakmilikPermohonan()) {
                    Hakmilik hakmilik = hakmilikDAO.findById(idHakmilik);
                    //  if (StringUtils.isBlank(hakmilik.getNoBukuDaftarStrata())) {
                    permohonan = strService.findPermohonanByHakmilik(idHakmilik, null);
                    if (permohonan != null) {
                        urusan.setIdPermohonanSebelum(permohonan.getIdPermohonan());
                    }
//                } else {
//                    errors.add("Pendaftaran strata telah dilakukan pada hakmilik ini.");
//                }

                    LOG.info("Permohonan sblm ::" + permohonan);
                }
                if (permohonan == null) {
                    errors.add("Hakmilik yang dimasukkan belum memohon strata");
                }
//            if (urusan.getHakmilikPermohonan().isEmpty()) {
//                errors.add("Sila masukkan hakmilik untuk urusan ini");
//            }
            }
        }

        if (conf.getProperty("kodNegeri").equals("04")) {
            if (urusan.getKodUrusan().equals("PNSS")) {
                for (String idHakmilik : urusan.getHakmilikPermohonan()) {
                    Hakmilik hakmilik = hakmilikDAO.findById(idHakmilik);
                    permohonan = strService.findPermohonanByHakmilik(idHakmilik, null);
                    LOG.info("-----Permohonan sblm----:" + permohonan);
                    if (permohonan != null) {
                        urusan.setIdPermohonanSebelum(permohonan.getIdPermohonan());
                    }
                }
                if (permohonan == null) {
                    errors.add("Hakmilik yang dimasukkan belum memohon strata");
                }
                if (urusan.getHakmilikPermohonan().isEmpty()) {
                    errors.add("Sila masukkan hakmilik untuk urusan ini");
                }
            }

            if (urusan.getKodUrusan().equals("PPPP")) {
                List<HakmilikPermohonan> hp = strService.getHakmilikPermohonanListByIdHakmilik(urusan.getHakmilikPermohonan().get(0));
                if (!hp.isEmpty()) {
                    BadanPengurusan bp = new BadanPengurusan();
                    bp = strService.findBdn(hp.get(0).getPermohonan().getIdPermohonan());
                    if (bp != null && bp.getPengurusanNoSijil() != null) {
                        errors.add("Harap Maaf. Id hakmilik ini telah mempunyai sijil Perbadanan Pengurusan. Sila semak No. Sijil "
                                + bp.getPengurusanNoSijil() + " yang telah dikeluarkan pada " + sdf.format(bp.getPengurusanTarikhRujukan()) + ".");
                    }
                }
            }

            if (urusan.getKodUrusan().equals("SFUS")) {
                List<Permohonan> mohon = strService.getPermohonan(urusan.getHakmilikPermohonan().get(0), "SFUS");
                for (Permohonan mhn : mohon) {
                    if (mhn.getKeputusan() != null) {
                        if (mhn.getKeputusan().getKod().equals("L")) {
                            errors.add("Harap Maaf. Id hakmilik ini telah memohon Sijil Formula Unit Syer. "
                                    + "Sila buat permohonan Pindaan Sijil Formula Unit Syer (PFUS).");
                        }
                    } else {
                        errors.add("Harap Maaf. Id hakmilik ini masih dalam proses memohon Sijil Formula Unit Syer.");
                    }
                }
            }

            if (urusan.getKodUrusan().equals("PBBS") || urusan.getKodUrusan().equals("PBBD")
                    || urusan.getKodUrusan().equals("PBS") || urusan.getKodUrusan().equals("PSBS")) {
                List<Permohonan> mohon = strService.getPermohonan(urusan.getHakmilikPermohonan().get(0), "SFUS");
                if (mohon.size() > 0) {
                    for (Permohonan mhn : mohon) {
                        if (mhn.getKeputusan() != null) {

                        } else {
                            errors.add("Harap Maaf. Id hakmilik ini masih dalam proses memohon Sijil Formula Unit Syer.");
                        }
                    }
                } else {
                    List<Projek> sifus = strService.findSifus(urusan.getHakmilikPermohonan().get(0), null, "Y");
                    if (sifus.size() <= 0) {
                        errors.add("Harap Maaf. Id hakmilik ini masih belum memohon Sijil Formula Unit Syer.");
                    }
                }
            }

            if (urusan.getKodUrusan().equals("PSBS")) {
                for (String idHakmilik : urusan.getHakmilikPermohonan()) {
                    Hakmilik hakmilik = hakmilikDAO.findById(idHakmilik);
                    permohonan = strService.findPermohonanByHakmilik(idHakmilik, null);
                    if (permohonan != null) {
                        urusan.setIdPermohonanSebelum(permohonan.getIdPermohonan());
                    }
                }
                if (permohonan == null) {
                    errors.add("Hakmilik yang dimasukkan belum memohon strata");
                }
                if (urusan.getHakmilikPermohonan().isEmpty()) {
                    errors.add("Sila masukkan hakmilik untuk urusan ini");
                }
            }
        }
        if (urusan.getKodUrusan().equals("RMHS1")) {
            for (String idHakmilik : urusan.getHakmilikPermohonan()) {
                Hakmilik hakmilik = hakmilikDAO.findById(idHakmilik);
                permohonan = strService.findPermohonanByHakmilik(hakmilik.getIdHakmilik(), null);
                if (permohonan != null) {
                    if (permohonan.getKodUrusan().getKod().equals("PBBD")) {
                        bng = strService.findByIDPermohonan(permohonan.getIdPermohonan());
                        lp = strService.findByIDPermohonanByLandparcel(permohonan.getIdPermohonan());
                        if (bng.isEmpty() && !lp.isEmpty()) {
                            errors.add("Maaf. ID Hakmilik ini hanya boleh digunakan untuk urusan: Rayuan Meminda Borang Permohonan Pecah Bahagi Tanah (Borang 1A) sahaja");
                        } else {
                            FasaPermohonan fasaPermohonan = strService.findFasaPermohonanByIdAliran(permohonan.getIdPermohonan(), "keputusan");
                            if (fasaPermohonan != null) {
                                errors.add("Permohonan terdahulu berada pada peringkat keputusan");
                            } else {
                                urusan.setIdPermohonanSebelum(permohonan.getIdPermohonan());
                            }
                        }
                    } else {
                        FasaPermohonan fasaPermohonan = strService.findFasaPermohonanByIdAliran(permohonan.getIdPermohonan(), "keputusan");
                        if (fasaPermohonan != null) {
                            errors.add("Permohonan terdahulu berada pada peringkat keputusan");
                        } else {
                            urusan.setIdPermohonanSebelum(permohonan.getIdPermohonan());
                        }
                    }
                } else {
                    errors.add("Maaf. Tidak terdapat urusan pada hakmilik ini.");
                }
            }
            if (urusan.getHakmilikPermohonan().isEmpty()) {
                errors.add("Sila masukkan hakmilik untuk urusan ini");
            }
        }
        if (urusan.getKodUrusan().equals("RMH1A")) {
            for (String idHakmilik : urusan.getHakmilikPermohonan()) {
                Hakmilik hakmilik = hakmilikDAO.findById(idHakmilik);
                permohonan = strService.findPermohonanByHakmilik(hakmilik.getIdHakmilik(), null);
                if (permohonan != null) {
                    if (permohonan.getKodUrusan().getKod().equals("PBBD")) {
                        bng = strService.findByIDPermohonan(permohonan.getIdPermohonan());
                        lp = strService.findByIDPermohonanByLandparcel(permohonan.getIdPermohonan());
                        if (bng.isEmpty() && !lp.isEmpty()) {
                            FasaPermohonan fasaPermohonan = strService.findFasaPermohonanByIdAliran(permohonan.getIdPermohonan(), "keputusan");
                            if (fasaPermohonan != null) {
                                errors.add("Permohonan terdahulu berada pada peringkat keputusan");
                            } else {
                                urusan.setIdPermohonanSebelum(permohonan.getIdPermohonan());
                            }
                        } else {
                            errors.add("Maaf. ID Hakmilik ini hanya boleh digunakan untuk urusan: Rayuan Meminda Borang Permohonan Pecah Bahagi Bangunan (Borang 1) sahaja");
                        }
                    } else {
                        FasaPermohonan fasaPermohonan = strService.findFasaPermohonanByIdAliran(permohonan.getIdPermohonan(), "keputusan");
                        if (fasaPermohonan != null) {
                            errors.add("Permohonan terdahulu berada pada peringkat keputusan");
                        } else {
                            urusan.setIdPermohonanSebelum(permohonan.getIdPermohonan());
                        }
                    }
                } else {
                    errors.add("Maaf. Tidak terdapat urusan pada hakmilik ini.");
                }
            }
            if (urusan.getHakmilikPermohonan()
                    .isEmpty()) {
                errors.add("Sila masukkan hakmilik untuk urusan ini");
            }
        }
        if (urusan.getKodUrusan().equals("RMHS5")) {
            for (String idHakmilik : urusan.getHakmilikPermohonan()) {
                Hakmilik hakmilik = hakmilikDAO.findById(idHakmilik);
                permohonan = strService.findPermohonanByHakmilik(hakmilik.getIdHakmilik(), null);
                if (permohonan != null) {
                    FasaPermohonan fasaPermohonan = strService.findFasaPermohonanByIdAliran(permohonan.getIdPermohonan(), "keputusan");
                    if (fasaPermohonan != null) {
                        errors.add("Permohonan terdahulu berada pada peringkat keputusan");
                    } else {
                        urusan.setIdPermohonanSebelum(permohonan.getIdPermohonan());
                    }
                } else {
                    errors.add("Maaf. Tidak terdapat urusan pada hakmilik ini.");
                }
            }
            if (urusan.getHakmilikPermohonan().isEmpty()) {
                errors.add("Sila masukkan hakmilik untuk urusan ini");
            }
        }

        if (urusan.getKodUrusan().equals("RMHS6")) {
            for (String idHakmilik : urusan.getHakmilikPermohonan()) {
                Hakmilik hakmilik = hakmilikDAO.findById(idHakmilik);
                permohonan = strService.findPermohonanByHakmilik(hakmilik.getIdHakmilik(), null);
                if (permohonan != null) {
                    FasaPermohonan fasaPermohonan = strService.findFasaPermohonanByIdAliran(permohonan.getIdPermohonan(), "keputusan");
                    if (fasaPermohonan != null) {
                        errors.add("Permohonan terdahulu berada pada peringkat keputusan");
                    } else {
                        urusan.setIdPermohonanSebelum(permohonan.getIdPermohonan());
                    }
                } else {
                    errors.add("Maaf. Tidak terdapat urusan pada hakmilik ini.");
                }
            }
            if (urusan.getHakmilikPermohonan().isEmpty()) {
                errors.add("Sila masukkan hakmilik untuk urusan ini");
            }
        }

        if (urusan.getKodUrusan().equals("RMHS7")) {
            for (String idHakmilik : urusan.getHakmilikPermohonan()) {
                Hakmilik hakmilik = hakmilikDAO.findById(idHakmilik);
                permohonan = strService.findPermohonanByHakmilik(hakmilik.getIdHakmilik(), null);
                if (permohonan != null) {
                    FasaPermohonan fasaPermohonan = strService.findFasaPermohonanByIdAliran(permohonan.getIdPermohonan(), "keputusan");
                    if (fasaPermohonan != null) {
                        errors.add("Permohonan terdahulu berada pada peringkat keputusan");
                    } else {
                        urusan.setIdPermohonanSebelum(permohonan.getIdPermohonan());
                    }
                } else {
                    errors.add("Maaf. Tidak terdapat urusan pada hakmilik ini.");
                }
            }
            if (urusan.getHakmilikPermohonan().isEmpty()) {
                errors.add("Sila masukkan hakmilik untuk urusan ini");
            }
        }

        if (urusan.getKodUrusan().equals("RBHS")) {
            for (String idHakmilik : urusan.getHakmilikPermohonan()) {
                Hakmilik hakmilik = hakmilikDAO.findById(idHakmilik);
                permohonan = strService.findPermohonanByHakmilik(hakmilik.getIdHakmilik(), null);
                if (permohonan != null) {
                    FasaPermohonan fasaPermohonan = strService.findFasaPermohonanByIdAliran(permohonan.getIdPermohonan(), "keputusan");
                    if (fasaPermohonan != null) {
                        urusan.setIdPermohonanSebelum(permohonan.getIdPermohonan());
                        //errors.add("Permohonan terdahulu berada pada peringkat keputusan");
                    } else {
                        urusan.setIdPermohonanSebelum(permohonan.getIdPermohonan());
                    }
                } else {
                    errors.add("Maaf. Tidak terdapat urusan pada hakmilik ini.");
                }
            }
            if (urusan.getHakmilikPermohonan().isEmpty()) {
                errors.add("Sila masukkan hakmilik untuk urusan ini");
            }
        }

        if (conf.getProperty("kodNegeri").equals("05")) {
            if (urusan.getKodUrusan().equals("RTHS")) {
                for (String idHakmilik : urusan.getHakmilikPermohonan()) {
                    Hakmilik hakmilik = hakmilikDAO.findById(idHakmilik);
                    permohonan = strService.findPermohonanByHakmilik(hakmilik.getIdHakmilik(), null);
                    if (permohonan != null) {
                        FasaPermohonan fasaPermohonan = strService.findFasaPermohonanByIdAliran(permohonan.getIdPermohonan(), "keputusan");
                        if (fasaPermohonan != null) {
                            errors.add("Permohonan terdahulu berada pada peringkat keputusan");
                        } else {
                            urusan.setIdPermohonanSebelum(permohonan.getIdPermohonan());
                        }
                    }
                }
            }

            if (urusan.getKodUrusan().equals("RTPS")) {
                for (String idHakmilik : urusan.getHakmilikPermohonan()) {
                    Hakmilik hakmilik = hakmilikDAO.findById(idHakmilik);
                    if (hakmilik.getIdHakmilikInduk() != null) {
                        // permohonan = strService.findPermohonanByHakmilik(hakmilik.getIdHakmilikInduk(), null);
                        permohonan = strService.findPermohonanByHakmilik(hakmilik.getIdHakmilik(), null);
                        if (permohonan != null) {
                            FasaPermohonan fasaPermohonan = strService.findFasaPermohonanByIdAliran(permohonan.getIdPermohonan(), "keputusan");
                            if (fasaPermohonan != null) {
                                errors.add("Permohonan terdahulu berada pada peringkat keputusan");
                            } else {
                                urusan.setIdPermohonanSebelum(permohonan.getIdPermohonan());
                            }
                        }
                    } else {
                        errors.add("ID Hakmilik yang dimasukkan tidak mempunyai ID Hakmilik Induk. Sila isi ID Hakmilik sekali lagi.");
                    }
                }
            }
        }

        return errors;
    }
}
//
//"PBBD"||"PBBS"||"PNSS"||"PPPP"||"PWPN"||"RBHS"||"RMHS1"
//RMHS5
//RMHS6
//RMHS7
//RPHS
//RTHS
//RTHS1
//RTHS5
//PBS
//PHPC
//PHPP
//PHPS
//PKKR
//RMH1A
//P14A
//P22A
//P22B
//P40A
//PPRUS
//PNB
//RTPS

