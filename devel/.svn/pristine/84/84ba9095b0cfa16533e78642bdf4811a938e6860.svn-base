/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.service;

import com.google.inject.Inject;
import com.wideplay.warp.persist.Transactional;
import etanah.dao.KodAgensiDAO;
import etanah.dao.KodAgensiKutipanCawanganDAO;
import etanah.dao.KodAkaunDAO;
import etanah.dao.KodAliranDAO;
import etanah.dao.KodBandarPekanMukimDAO;
import etanah.dao.KodBangsaDAO;
import etanah.dao.KodBankDAO;
import etanah.dao.KodCawanganDAO;
import etanah.dao.KodCawanganJabatanDAO;
import etanah.dao.KodDUNDAO;
import etanah.dao.KodDokumenAgensiDAO;
import etanah.dao.KodDokumenDAO;
import etanah.dao.KodHakmilikDAO;
import etanah.dao.KodKawasanParlimenDAO;
import etanah.dao.KodUrusanAgensiDAO;
import etanah.dao.SenaraiKodRujukanDAO;
import etanah.dao.SloganSuratDAO;
import etanah.dao.UrusanDokumenDAO;
import etanah.model.KodAgensi;
import etanah.model.KodAgensiKutipanCawangan;
import etanah.model.KodAkaun;
import etanah.model.KodAliran;
import etanah.model.KodBandarPekanMukim;
import etanah.model.KodBangsa;
import etanah.model.KodBank;
import etanah.model.KodCawangan;
import etanah.model.KodCawanganJabatan;
import etanah.model.KodDUN;
import etanah.model.KodDokumen;
import etanah.model.KodDokumenAgensi;
import etanah.model.KodHakmilik;
import etanah.model.KodJabatan;
import etanah.model.KodKawasanParlimen;
import etanah.model.KodUrusanAgensi;
import etanah.model.SenaraiKodRujukan;
import etanah.model.SloganSurat;
import etanah.model.UrusanDokumen;
import java.util.List;
import java.util.logging.Logger;
import org.apache.commons.lang.StringUtils;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author Aziz
 */
public class KodService {

    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;
    @Inject
    KodBandarPekanMukimDAO kodBPMDAO;
    @Inject
    KodUrusanAgensiDAO kodUrusAgensiDAO;
    @Inject
    KodAkaunDAO kodAkaunDAO;
    @Inject
    KodDUNDAO kodDUNDAO;
    @Inject
    KodAgensiKutipanCawanganDAO kodAgensiCawanganDAO;
    @Inject
    KodAgensiDAO kodAgensiDAO;
    @Inject
    KodAliranDAO kodAliranDAO;
    @Inject
    KodBankDAO kodBankDAO;
    @Inject
    KodBangsaDAO kodBangsaDAO;
    @Inject
    KodHakmilikDAO kodHakmilikDAO;
    @Inject
    KodDokumenDAO kodDokumenDAO;
    @Inject
    KodCawanganJabatanDAO kodCawJabatanDAO;
    @Inject
    KodDokumenAgensiDAO kodDokumenAgensiDAO;
    @Inject
    KodCawanganDAO kodCawanganDAO;
    @Inject
    KodKawasanParlimenDAO kodKawasanParlimenDAO;
    @Inject
    SloganSuratDAO sloganSuratDAO;
    @Inject
    UrusanDokumenDAO urusanDokumenDAO;
    @Inject
    SenaraiKodRujukanDAO senaraiKodRujukanDAO;
    private static final org.apache.log4j.Logger SYSLOG = etanah.SYSLOG.getLogger();

    public List<SenaraiKodRujukan> findKodRujuk(String tn, String atn) throws Exception {

        String qBase = "select p from etanah.model.SenaraiKodRujukan p ";
        String query = "select p from etanah.model.SenaraiKodRujukan p ";
        String cond = " where ";
        String mulCond = " and ";

        if (StringUtils.isNotBlank(tn)) {
            if (query.equalsIgnoreCase(qBase)) {
                query += " " + cond + " UPPER(p.nama_jadual) LIKE :tn ";
            } else {
                query += " " + mulCond + " UPPER(p.nama_jadual) LIKE :tn ";

            }
        }
        if (StringUtils.isNotBlank(atn)) {
            if (query.equalsIgnoreCase(qBase)) {
                query += " " + cond + " UPPER(p.nama) LIKE :atn ";
            } else {
                query += " " + mulCond + " UPPER(p.nama) LIKE :atn ";

            }

        }
        Query q = sessionProvider.get().createQuery(query);
        if (tn != null) {
            q.setString("tn", "%" + tn.toUpperCase() + "%");
        }
        if (atn != null) {
            //q.setString("atn", "%" + atn + "%");
            q.setString("atn", "%" + atn.toUpperCase() + "%");
        }

        SYSLOG.debug("no query==>" + q.list().size());

        return q.list();
    }

    public List<SenaraiKodRujukan> findKodRujuk1(String atn) {

        String qBase = "select p from etanah.model.SenaraiKodRujukan p ";
        String query = "select p from etanah.model.SenaraiKodRujukan p ";
        String cond = " where ";
        String mulCond = " and ";
        if (StringUtils.isNotBlank(atn)) {
            if (query.equalsIgnoreCase(qBase)) {
                query += " " + cond + " UPPER(p.nama) LIKE :atn ";
            } else {
                query += " " + mulCond + " UPPER(p.nama) LIKE :atn ";

            }

        }
        Query q = sessionProvider.get().createQuery(query);
        if (atn != null) {
            //q.setString("atn", "%" + atn + "%");
            q.setString("atn", "%" + atn.toUpperCase() + "%");
        }

        SYSLOG.debug("no query==>" + q.list().size());

        return q.list();
    }

    public List<KodBandarPekanMukim> findKodBPM(String daerah, String kod, String nama) {

        String qBase = "Select a from KodBandarPekanMukim a ";
        String query = "Select a from KodBandarPekanMukim a ";
        String cond = " Where ";
        String mulCond = " and ";

        if (StringUtils.isNotBlank(daerah)) {
            if (query.equalsIgnoreCase(qBase)) {
                query += " " + cond + " a.daerah.kod =:daerah ";
            } else {
                query += " " + mulCond + "  a.daerah.kod =:daerah ";

            }
        }
        if (StringUtils.isNotBlank(kod)) {
            if (query.equalsIgnoreCase(qBase)) {
                query += " " + cond + " bandarPekanMukim=:kod ";
            } else {
                query += " " + mulCond + " bandarPekanMukim=:kod ";

            }
        }
        if (StringUtils.isNotBlank(nama)) {
            if (query.equalsIgnoreCase(qBase)) {
                query += " " + cond + " nama LIKE :nama ";
            } else {
                query += " " + mulCond + "  nama LIKE :nama ";

            }
        }
        Query q = sessionProvider.get().createQuery(query);

        if (daerah != null) {
            q.setString("daerah", daerah);
        }
        if (kod != null) {

            q.setString("kod", kod);
        }
        if (nama != null) {

            q.setString("nama", "%" + nama + "%");
        }
        return q.list();

    }

    public List<KodBandarPekanMukim> findKodBPM2(String daerah, String kod, String nama) {

        String qBase = "Select a from KodBandarPekanMukim a ";
        String query = "Select a from KodBandarPekanMukim a ";
        String cond = " Where ";
        String mulCond = " and ";

        if (StringUtils.isNotBlank(daerah)) {
            if (query.equalsIgnoreCase(qBase)) {
                query += " " + cond + " a.daerah.kod =:daerah ";
            } else {
                query += " " + mulCond + "  a.daerah.kod =:daerah ";

            }
        }
        if (StringUtils.isNotBlank(kod)) {
            if (query.equalsIgnoreCase(qBase)) {
                query += " " + cond + " bandarPekanMukim=:kod ";
            } else {
                query += " " + mulCond + " bandarPekanMukim=:kod ";

            }
        }
        if (StringUtils.isNotBlank(nama)) {
            if (query.equalsIgnoreCase(qBase)) {
                query += " " + cond + " nama LIKE :nama ";
            } else {
                query += " " + mulCond + "  nama LIKE :nama ";

            }
        }
        Query q = sessionProvider.get().createQuery(query);

        if (daerah != null) {
            q.setString("daerah", daerah);
        }
        if (kod != null) {

            q.setString("kod", kod);
        }
        if (nama != null) {

            q.setString("nama", "%" + nama + "%");
        }
        return q.list();

    }

    public List<KodUrusanAgensi> findKodUrusanAgensi2(String urus, String agensi) {

        String qBase = "Select a from KodUrusanAgensi a ";
        String query = "Select a from KodUrusanAgensi a ";
        String cond = " Where ";
        String mulCond = " and ";

        if (StringUtils.isNotBlank(urus) || urus != null) {
            if (query.equalsIgnoreCase(qBase)) {
                query += " " + cond + " a.kodUrusan.kod =:urus ";
            } else {
                query += " " + mulCond + "  a.kodUrusan.kod =:urus ";

            }
        }
        if (StringUtils.isNotBlank(agensi) || agensi != null) {
            if (query.equalsIgnoreCase(qBase)) {
                query += " " + cond + " a.kodAgensi.kod =:agensi ";
            } else {
                query += " " + mulCond + "  a.kodAgensi.kod =:agensi ";

            }
        }

        Query q = sessionProvider.get().createQuery(query);

        if (urus != null) {
            q.setString("urus", urus);
        }

        if (agensi != null) {
            q.setString("agensi", agensi);
        }
        return q.list();

    }

    public List<KodAgensi> findKodAgensi2(int kodKementerian, String nama, String katgAgensi, String negeri, String gelaran, String daerah) {

        String qBase = "Select a from KodAgensi a ";
        String query = "Select a from KodAgensi a ";
        String cond = " Where ";
        String mulCond = " and ";

        if (kodKementerian != 0) {
            if (query.equalsIgnoreCase(qBase)) {
                query += " " + cond + " a.kodKementerian =:kodKementerian ";
            } else {
                query += " " + mulCond + "  a.kodKementerian =:kodKementerian ";

            }
        }
        if (StringUtils.isNotBlank(nama) || nama != null) {
            if (query.equalsIgnoreCase(qBase)) {
                query += " " + cond + " UPPER(a.nama) LIKE:nama ";
            } else {
                query += " " + mulCond + " UPPER(a.nama) LIKE:nama ";

            }
        }
        if (StringUtils.isNotBlank(katgAgensi) || katgAgensi != null) {
            if (query.equalsIgnoreCase(qBase)) {
                query += " " + cond + " a.kategoriAgensi.kod =:katgAgensi ";
            } else {
                query += " " + mulCond + "  a.kategoriAgensi =:katgAgensi ";

            }
        }
        if (StringUtils.isNotBlank(negeri) || negeri != null) {
            if (query.equalsIgnoreCase(qBase)) {
                query += " " + cond + " a.kodNegeri.kod =:negeri ";
            } else {
                query += " " + mulCond + "  a.kodNegeri.kod =:negeri ";

            }
        }


        if (StringUtils.isNotBlank(gelaran) || gelaran != null) {
            if (query.equalsIgnoreCase(qBase)) {
                query += " " + cond + " a.kodGelaran.kod =:gelaran ";
            } else {
                query += " " + mulCond + "  a.kodGelaran.kod =:gelaran ";

            }
        }
        if (StringUtils.isNotBlank(daerah) || daerah != null) {
            if (query.equalsIgnoreCase(qBase)) {
                query += " " + cond + " a.kodDaerah.kod =:daerah ";
            } else {
                query += " " + mulCond + "  a.kodDaerah.kod =:daerah ";

            }
        }
        Query q = sessionProvider.get().createQuery(query);

        if (kodKementerian != 0) {
            q.setInteger("kodKementerian", kodKementerian);
        }

        if (nama != null) {
            q.setString("nama", "%" + nama.toUpperCase() + "%");
        }
        if (katgAgensi != null) {
            q.setString("katgAgensi", katgAgensi);
        }
        if (negeri != null) {
            q.setString("negeri", negeri);
        }
        if (gelaran != null) {
            q.setString("gelaran", gelaran);
        }
        if (daerah != null) {
            q.setString("daerah", daerah);
        }
        return q.list();

    }

    public List<KodAgensi> findKodAgensi3(String nama, String daerah) {

        String qBase = "Select a from KodAgensi a ";
        String query = "Select a from KodAgensi a ";
        String cond = " Where ";
        String mulCond = " and ";

        System.out.println("nama " + nama);
        System.out.println("daerah " + daerah);
        
        if (nama != null && daerah != null) {
            if (query.equalsIgnoreCase(qBase)) {
                query += " " + cond + " a.kodDaerah.kod =:daerah " + mulCond + " UPPER(a.nama) LIKE:nama " + mulCond + "kategoriAgensi.kod = 'JTK' order by a.nama ASC";
            } else {
                query += " " + mulCond + "  a.kodDaerah.kod =:daerah " + mulCond + " UPPER(a.nama) LIKE:nama " + mulCond + "kategoriAgensi.kod = 'JTK' order by a.nama ASC";
            }
        }

        if (StringUtils.isNotBlank(nama) || nama != null) {
            if (query.equalsIgnoreCase(qBase)) {
                query += " " + cond + " UPPER(a.nama) LIKE:nama " + mulCond + "kategoriAgensi.kod = 'JTK' order by a.nama ASC";
            } else {
                query += " " + mulCond + " UPPER(a.nama) LIKE:nama " + mulCond + "kategoriAgensi.kod = 'JTK' order by a.nama ASC";
            }
        }

        if (StringUtils.isNotBlank(daerah) || daerah != null) {
            if (query.equalsIgnoreCase(qBase)) {
                query += " " + cond + " a.kodDaerah.kod =:daerah " + mulCond + "kategoriAgensi.kod = 'JTK' order by a.nama ASC";
            } else {
                query += " " + mulCond + "  a.kodDaerah.kod =:daerah " + mulCond + "kategoriAgensi.kod = 'JTK' order by a.nama ASC";
            }
        }  

        if (nama == null && daerah == null) {
            query += " " + cond + "kategoriAgensi.kod = 'JTK' order by a.nama ASC";
        }
        
        Query q = sessionProvider.get().createQuery(query);
        if (nama != null) {
            q.setString("nama", "%" + nama.toUpperCase() + "%");
        }
        if (daerah != null) {
            q.setString("daerah", daerah);
        }
        return q.list();

    }

    public List<KodAgensiKutipanCawangan> findKodAgensiKutipanCawangan2(String agensiKutipan, String nama, String lokasi) {

        String qBase = "Select a from KodAgensiKutipanCawangan a ";
        String query = "Select a from KodAgensiKutipanCawangan a ";
        String cond = " Where ";
        String mulCond = " and ";

        if (StringUtils.isNotBlank(agensiKutipan) || agensiKutipan != null) {
            if (query.equalsIgnoreCase(qBase)) {
                query += " " + cond + " a.agensiKutipan.kod =:agensiKutipan ";
            } else {
                query += " " + mulCond + "  a.agensiKutipan.kod =:agensiKutipan ";

            }
        }
        if (StringUtils.isNotBlank(nama) || nama != null) {
            if (query.equalsIgnoreCase(qBase)) {
                query += " " + cond + " a.nama LIKE:nama ";
            } else {
                query += " " + mulCond + "  a.nama LIKE:nama ";

            }
        }

        if (StringUtils.isNotBlank(lokasi) || lokasi != null) {
            if (query.equalsIgnoreCase(qBase)) {
                query += " " + cond + " a.lokasi LIKE:lokasi ";
            } else {
                query += " " + mulCond + "  a.lokasi LIKE:lokasi ";

            }
        }



        Query q = sessionProvider.get().createQuery(query);

        if (agensiKutipan != null) {
            q.setString("agensiKutipan", agensiKutipan);
        }

        if (nama != null) {
            q.setString("nama", "%" + nama + "%");
        }
        if (lokasi != null) {
            q.setString("lokasi", "%" + lokasi + "%");
        }

        return q.list();

    }

    public List<KodCawanganJabatan> findKodCawanganJabatan2(String kodCawangan, String nama, String negeri, String kodJabatan) {

        String qBase = "Select a from KodCawanganJabatan a ";
        String query = "Select a from KodCawanganJabatan a ";
        String cond = " Where ";
        String mulCond = " and ";

        if (StringUtils.isNotBlank(kodCawangan) || kodCawangan != null) {
            if (query.equalsIgnoreCase(qBase)) {
                query += " " + cond + " a.cawangan.kod =:kodCawangan ";
            } else {
                query += " " + mulCond + "  a.cawangan.kod =:kodCawangan ";

            }
        }
        if (StringUtils.isNotBlank(nama) || nama != null) {
            if (query.equalsIgnoreCase(qBase)) {
                query += " " + cond + " a.nama LIKE:nama ";
            } else {
                query += " " + mulCond + "  a.nama LIKE:nama ";

            }
        }

        if (StringUtils.isNotBlank(negeri) || negeri != null) {
            if (query.equalsIgnoreCase(qBase)) {
                query += " " + cond + " a.negeri.kod =:negeri ";
            } else {
                query += " " + mulCond + "  a.negeri.kod =:negeri ";

            }
        }
        if (StringUtils.isNotBlank(kodJabatan) || kodJabatan != null) {
            if (query.equalsIgnoreCase(qBase)) {
                query += " " + cond + " a.jabatan.kod =:kodJabatan ";
            } else {
                query += " " + mulCond + "  a.jabatan.kod =:kodJabatan ";

            }
        }



        Query q = sessionProvider.get().createQuery(query);

        if (kodCawangan != null) {
            q.setString("kodCawangan", kodCawangan);
        }

        if (nama != null) {
            q.setString("nama", "%" + nama + "%");
        }
        if (negeri != null) {
            q.setString("negeri", negeri);
        }
        if (kodJabatan != null) {
            q.setString("kodJabatan", kodJabatan);
        }

        return q.list();

    }

    public List<KodAkaun> findKodAkaun2(String katgAkaun, String nama) {

        String qBase = "Select a from KodAkaun a ";
        String query = "Select a from KodAkaun a ";
        String cond = " Where ";
        String mulCond = " and ";

        if (StringUtils.isNotBlank(katgAkaun) || katgAkaun != null) {
            if (query.equalsIgnoreCase(qBase)) {
                query += " " + cond + " a.kategoriAkaun.kod =:katgAkaun ";
            } else {
                query += " " + mulCond + "  a.kategoriAkaun.kod =:katgAkaun ";

            }
        }
        if (StringUtils.isNotBlank(nama) || nama != null) {
            if (query.equalsIgnoreCase(qBase)) {
                query += " " + cond + " a.nama LIKE:nama ";
            } else {
                query += " " + mulCond + "  a.nama LIKE:nama ";

            }
        }

        Query q = sessionProvider.get().createQuery(query);

        if (katgAkaun != null) {
            q.setString("katgAkaun", katgAkaun);
        }

        if (nama != null) {
            q.setString("nama", "%" + nama + "%");
        }
        return q.list();

    }

    public List<KodAliran> findKodAliran2(String urusan, String idAliran, String nama) {

        String qBase = "Select a from KodAliran a ";
        String query = "Select a from KodAliran a ";
        String cond = " Where ";
        String mulCond = " and ";

        if (StringUtils.isNotBlank(urusan) || urusan != null) {
            if (query.equalsIgnoreCase(qBase)) {
                query += " " + cond + " a.kodUrusan.kod =:urusan ";
            } else {
                query += " " + mulCond + "  a.kodUrusan.kod =:urusan ";

            }
        }
        if (StringUtils.isNotBlank(idAliran) || idAliran != null) {
            if (query.equalsIgnoreCase(qBase)) {
                query += " " + cond + " a.idAliran LIKE:idAliran ";
            } else {
                query += " " + mulCond + "  a.idAliran LIKE:idAliran ";

            }
        }
        if (StringUtils.isNotBlank(nama) || nama != null) {
            if (query.equalsIgnoreCase(qBase)) {
                query += " " + cond + " a.nama LIKE:nama ";
            } else {
                query += " " + mulCond + "  a.nama LIKE:nama ";

            }
        }

        Query q = sessionProvider.get().createQuery(query);

        if (urusan != null) {
            q.setString("urusan", urusan);
        }
        if (idAliran != null) {
            q.setString("idAliran", "%" + idAliran + "%");
        }

        if (nama != null) {
            q.setString("nama", "%" + nama + "%");
        }
        return q.list();

    }

    public List<KodBank> findKodBank2(String cawangan, String nama) {

        String qBase = "Select a from KodBank a ";
        String query = "Select a from KodBank a ";
        String cond = " Where ";
        String mulCond = " and ";

        if (StringUtils.isNotBlank(cawangan) || cawangan != null) {
            if (query.equalsIgnoreCase(qBase)) {
                query += " " + cond + " a.kodCawangan.kod =:cawangan ";
            } else {
                query += " " + mulCond + "  a.kodCawangan.kod =:cawangan ";

            }
        }
        if (StringUtils.isNotBlank(nama) || nama != null) {
            if (query.equalsIgnoreCase(qBase)) {
                query += " " + cond + " a.nama LIKE:nama ";
            } else {
                query += " " + mulCond + "  a.nama LIKE:nama ";

            }
        }

        Query q = sessionProvider.get().createQuery(query);

        if (cawangan != null) {
            q.setString("cawangan", cawangan);
        }
        if (nama != null) {
            q.setString("nama", "%" + nama + "%");
        }
        return q.list();

    }

    public List<KodCawangan> findKodCawangan2(String daerah, String cawanganUtama, String nama, String kodptj, String kodSpek) {

        String qBase = "Select a from KodCawangan a ";
        String query = "Select a from KodCawangan a ";
        String cond = " Where ";
        String mulCond = " and ";

        if (StringUtils.isNotBlank(daerah) || daerah != null) {
            if (query.equalsIgnoreCase(qBase)) {
                query += " " + cond + " a.daerah.kod =:daerah ";
            } else {
                query += " " + mulCond + "  a.daerah.kod =:daerah ";

            }
        }
        if (StringUtils.isNotBlank(cawanganUtama) || cawanganUtama != null) {
            if (query.equalsIgnoreCase(qBase)) {
                query += " " + cond + " a.cawanganUtama.kod =:cawanganUtama ";
            } else {
                query += " " + mulCond + "  a.cawanganUtama.kod =:cawanganUtama ";

            }
        }
        if (StringUtils.isNotBlank(nama) || nama != null) {
            if (query.equalsIgnoreCase(qBase)) {
                query += " " + cond + " a.nama LIKE:nama ";
            } else {
                query += " " + mulCond + "  a.nama LIKE:nama ";

            }
        }

        if (StringUtils.isNotBlank(kodptj) || kodptj != null) {
            if (query.equalsIgnoreCase(qBase)) {
                query += " " + cond + " a.kodPTJ =:kodptj ";
            } else {
                query += " " + mulCond + "  a.kodPTJ =:kodptj ";

            }
        }
        if (StringUtils.isNotBlank(cawanganUtama) || cawanganUtama != null) {
            if (query.equalsIgnoreCase(qBase)) {
                query += " " + cond + " a.kodJabatanSpek =:kodSpek ";
            } else {
                query += " " + mulCond + "  a.kodJabatanSpek =:kodSpek ";

            }
        }

        Query q = sessionProvider.get().createQuery(query);

        if (daerah != null) {
            q.setString("daerah", daerah);
        }
        if (cawanganUtama != null) {
            q.setString("cawanganUtama", cawanganUtama);
        }
        if (nama != null) {
            q.setString("nama", "%" + nama + "%");
        }
        if (kodptj != null) {
            q.setString("kodptj", kodptj);
        }
        if (kodSpek != null) {
            q.setString("kodSpek", kodSpek);
        }
        return q.list();

    }

    public List<KodBangsa> findKodBangsa2(String nama) {

        String qBase = "Select a from KodBangsa a ";
        String query = "Select a from KodBangsa a ";
        String cond = " Where ";
        String mulCond = " and ";
        if (StringUtils.isNotBlank(nama) || nama != null) {
            if (query.equalsIgnoreCase(qBase)) {
                query += " " + cond + " a.nama LIKE:nama ";
            } else {
                query += " " + mulCond + "  a.nama LIKE:nama ";

            }
        }
        Query q = sessionProvider.get().createQuery(query);
        if (nama != null) {
            q.setString("nama", "%" + nama + "%");
        }
        return q.list();

    }

    public int countAll() {
        int count = 0;
        String query = "Select a from KodBandarPekanMukim a ";

        Query q = sessionProvider.get().createQuery(query);
        count = q.list().size();
        return count;


    }

    public List<KodDokumen> findKodDokumen2(String nama, String penjana) {

        String qBase = "Select a from KodDokumen a ";
        String query = "Select a from KodDokumen a ";
        String cond = " Where ";
        String mulCond = " and ";
        if (StringUtils.isNotBlank(nama) || nama != null) {
            if (query.equalsIgnoreCase(qBase)) {
                query += " " + cond + " a.nama LIKE:nama ";
            } else {
                query += " " + mulCond + "  a.nama LIKE:nama ";

            }
        }
        if (StringUtils.isNotBlank(penjana) || penjana != null) {
            if (query.equalsIgnoreCase(qBase)) {
                query += " " + cond + " a.penjana LIKE:penjana ";
            } else {
                query += " " + mulCond + "  a.penjana LIKE:penjana ";

            }
        }
        Query q = sessionProvider.get().createQuery(query);
        if (nama != null) {
            q.setString("nama", "%" + nama + "%");
        }
        if (penjana != null) {
            q.setString("penjana", "%" + penjana + "%");
        }
        return q.list();

    }

    public List<KodDokumenAgensi> findKodDokumenAgensi2(String kodUrusanAgensi, String kodDokumen) {

        String qBase = "Select a from KodDokumenAgensi a ";
        String query = "Select a from KodDokumenAgensi a ";
        String cond = " Where ";
        String mulCond = " and ";
        if (StringUtils.isNotBlank(kodUrusanAgensi) || kodUrusanAgensi != null) {
            if (query.equalsIgnoreCase(qBase)) {
                query += " " + cond + " a.kodUrusanAgensi.idKodUrusanAgensi =:kodUrusanAgensi ";
            } else {
                query += " " + mulCond + " a.kodUrusanAgensi.idKodUrusanAgensi =:kodUrusanAgensi ";

            }
        }
        if (StringUtils.isNotBlank(kodDokumen) || kodDokumen != null) {
            if (query.equalsIgnoreCase(qBase)) {
                query += " " + cond + " a.kodDokumen.kod =:kodDokumen ";
            } else {
                query += " " + mulCond + "  a.kodDokumen.kod =:kodDokumen ";

            }
        }
        Query q = sessionProvider.get().createQuery(query);
        if (kodUrusanAgensi != null) {
            q.setLong("kodUrusanAgensi", Long.valueOf(kodUrusanAgensi));
        }
        if (kodDokumen != null) {
            q.setString("kodDokumen", kodDokumen);
        }
        return q.list();

    }

    public List<KodDUN> findKodDUN2(String nama, String parlimen) {

        String qBase = "Select a from KodDUN a ";
        String query = "Select a from KodDUN a ";
        String cond = " Where ";
        String mulCond = " and ";
        if (StringUtils.isNotBlank(nama) || nama != null) {
            if (query.equalsIgnoreCase(qBase)) {
                query += " " + cond + " UPPER(a.nama) LIKE:nama ";
            } else {
                query += " " + mulCond + " UPPER(a.nama) LIKE:nama ";

            }
        }
        if (StringUtils.isNotBlank(parlimen) || parlimen != null) {
            if (query.equalsIgnoreCase(qBase)) {
                query += " " + cond + " a.kodKawasanParlimen.kod =:parlimen ";
            } else {
                query += " " + mulCond + "  a.kodKawasanParlimen.kod =:parlimen ";

            }
        }
        Query q = sessionProvider.get().createQuery(query);
        if (nama != null) {
            q.setString("nama", "%" + nama.toUpperCase() + "%");
        }
        if (parlimen != null) {
            q.setString("parlimen", parlimen);
        }
        return q.list();

    }

    public List<KodHakmilik> findKodHakmilik2(String nama, String kodDokumen) {

        String qBase = "Select a from KodHakmilik a ";
        String query = "Select a from KodHakmilik a ";
        String cond = " Where ";
        String mulCond = " and ";
        if (StringUtils.isNotBlank(nama) || nama != null) {
            if (query.equalsIgnoreCase(qBase)) {
                query += " " + cond + " a.nama LIKE:nama ";
            } else {
                query += " " + mulCond + " a.nama LIKE:nama ";

            }
        }
        if (StringUtils.isNotBlank(kodDokumen) || kodDokumen != null) {
            if (query.equalsIgnoreCase(qBase)) {
                query += " " + cond + " a.kodDokumen.kod =:kodDokumen ";
            } else {
                query += " " + mulCond + "  a.kodDokumen.kod =:kodDokumen ";

            }
        }
        Query q = sessionProvider.get().createQuery(query);
        if (nama != null) {
            q.setString("nama", "%" + nama + "%");
        }
        if (kodDokumen != null) {
            q.setString("kodDokumen", kodDokumen);
        }
        return q.list();

    }

    public List<UrusanDokumen> findUrusanDokumen2(String kodDokumen, String kodUrusan, char katgPemohon) {

        String qBase = "Select a from UrusanDokumen a ";
        String query = "Select a from UrusanDokumen a ";
        String cond = " Where ";
        String mulCond = " and ";

        if (StringUtils.isNotBlank(kodDokumen) || kodDokumen != null) {
            if (query.equalsIgnoreCase(qBase)) {
                query += " " + cond + " a.kodDokumen.kod =:kodDokumen ";
            } else {
                query += " " + mulCond + "  a.kodKementerian.kod =:kodDokumen ";

            }
        }
        if (StringUtils.isNotBlank(kodUrusan) || kodUrusan != null) {
            if (query.equalsIgnoreCase(qBase)) {
                query += " " + cond + " a.kodUrusan.kod =:kodUrusan ";
            } else {
                query += " " + mulCond + "  a.kodUrusan.kod =:kodUrusan ";

            }
        }
        if (katgPemohon != 0) {
            if (query.equalsIgnoreCase(qBase)) {
                query += " " + cond + " a.kategoriPemohon =:katgPemohon ";
            } else {
                query += " " + mulCond + "  a.kategoriPemohon =:katgPemohon ";

            }
        }
        Query q = sessionProvider.get().createQuery(query);

        if (kodDokumen != null) {
            q.setString("kodDokumen", kodDokumen);
        }
        if (kodUrusan != null) {
            q.setString("kodUrusan", kodUrusan);
        }
        if (katgPemohon != 0) {
            q.setCharacter("katgPemohon", katgPemohon);
        }
        return q.list();

    }

    public List<KodAgensi> findForAdun(String nama, String gelaran, String katgAgensi, String daerah) {

        String qBase = "Select a from KodAgensi a ";
        String query = "Select a from KodAgensi a ";
        String cond = " Where ";
        String mulCond = " and ";

        if (StringUtils.isNotBlank(nama) || nama != null) {
            if (query.equalsIgnoreCase(qBase)) {
                query += " " + cond + " UPPER(a.nama) LIKE:nama ";
            } else {
                query += " " + mulCond + " UPPER(a.nama) LIKE:nama ";

            }
        }
        if (StringUtils.isNotBlank(katgAgensi) || katgAgensi != null) {
            if (query.equalsIgnoreCase(qBase)) {
                query += " " + cond + " a.kategoriAgensi.kod =:katgAgensi ";
            } else {
                query += " " + mulCond + "  a.kategoriAgensi =:katgAgensi ";

            }
        }
        if (StringUtils.isNotBlank(gelaran) || gelaran != null) {
            if (query.equalsIgnoreCase(qBase)) {
                query += " " + cond + " a.kodGelaran.kod =:gelaran ";
            } else {
                query += " " + mulCond + "  a.kodGelaran.kod =:gelaran ";

            }
        }
        if (StringUtils.isNotBlank(daerah) || daerah != null) {
            if (query.equalsIgnoreCase(qBase)) {
                query += " " + cond + " a.kodDaerah.kod =:daerah ";
            } else {
                query += " " + mulCond + "  a.kodDaerah.kod =:daerah ";

            }
        }
        Query q = sessionProvider.get().createQuery(query);

        if (nama != null) {
            q.setString("nama", "%" + nama.toUpperCase() + "%");
        }
        if (katgAgensi != null) {
            q.setString("katgAgensi", katgAgensi);
        }
        if (gelaran != null) {
            q.setString("gelaran", gelaran);
        }
        if (daerah != null) {
            q.setString("daerah", daerah);
        }
        return q.list();

    }

    public List<KodAgensi> findAllForAdun(String katgAgensi) {

        String qBase = "Select a from KodAgensi a ";
        String query = "Select a from KodAgensi a ";
        String cond = " Where ";
        String mulCond = " and ";

        if (StringUtils.isNotBlank(katgAgensi) || katgAgensi != null) {
            if (query.equalsIgnoreCase(qBase)) {
                query += " " + cond + " a.kategoriAgensi.kod =:katgAgensi ";
            } else {
                query += " " + mulCond + "  a.kategoriAgensi =:katgAgensi ";

            }
        }
        Query q = sessionProvider.get().createQuery(query);

        if (katgAgensi != null) {
            q.setString("katgAgensi", katgAgensi);
        }
        return q.list();

    }

    @Transactional
    public KodBandarPekanMukim viewKodBPM(int kod) {
        return kodBPMDAO.findById(kod);
    }

    @Transactional
    public KodUrusanAgensi viewKodUrusAgensi(long kod) {
        return kodUrusAgensiDAO.findById(kod);
    }

    @Transactional
    public KodAgensi viewKodAgensi(String kod) {
        return kodAgensiDAO.findById(kod);
    }

    @Transactional
    public KodAkaun viewKodAkaun(String kod) {
        return kodAkaunDAO.findById(kod);
    }

    @Transactional
    public KodCawanganJabatan viewKodCawanganJabatan(String kod) {
        return kodCawJabatanDAO.findById(kod);
    }

    @Transactional
    public KodAliran viewKodAliran(String kod) {
        return kodAliranDAO.findById(kod);
    }

    @Transactional
    public KodDokumenAgensi viewKodDokumenAgensi(String kod) {
        return kodDokumenAgensiDAO.findById(Long.valueOf(kod));
    }

    @Transactional
    public KodBank viewKodBank(String kod) {
        return kodBankDAO.findById(kod);
    }

    @Transactional
    public KodBangsa viewKodBangsa(String kod) {
        return kodBangsaDAO.findById(kod);
    }

    @Transactional
    public KodCawangan viewKodCawangan(String kod) {
        return kodCawanganDAO.findById(kod);
    }

    @Transactional
    public KodDokumen viewKodDokumen(String kod) {
        return kodDokumenDAO.findById(kod);
    }

    @Transactional
    public KodDUN viewKodDUN(String kod) {
        return kodDUNDAO.findById(kod);
    }

    @Transactional
    public KodHakmilik viewKodHakmilik(String kod) {
        return kodHakmilikDAO.findById(kod);
    }

    @Transactional
    public KodAgensiKutipanCawangan viewKodAgensiKutipCaw(String kod) {
        return kodAgensiCawanganDAO.findById(kod);
    }

    @Transactional
    public KodKawasanParlimen viewKodParlimen(String kod) {
        return kodKawasanParlimenDAO.findById(kod);
    }

    @Transactional
    public SloganSurat viewSloganSurat(Long kod) {
        return sloganSuratDAO.findById(kod);
    }

    @Transactional
    public UrusanDokumen viewUrusanDokumen(Long kod) {
        return urusanDokumenDAO.findById(kod);
    }

    @Transactional
    public int countKodAgensi() {
        return kodAgensiDAO.findAll().size();
    }

    @Transactional
    public int countKodBank() {
        return kodBankDAO.findAll().size();
    }

    @Transactional
    public int countKodCawangan() {
        return kodCawanganDAO.findAll().size();
    }

    @Transactional
    public int countKodAliran() {
        return kodAliranDAO.findAll().size();
    }

    @Transactional
    public int countKodCawanganJabatan() {
        return kodCawJabatanDAO.findAll().size();
    }

    @Transactional
    public int countKodDokumen() {
        return kodDokumenDAO.findAll().size();
    }

    @Transactional
    public int countKodAgensiKutipanCawangan() {
        return kodAgensiCawanganDAO.findAll().size();
    }

    @Transactional
    public int countKodBangsa() {
        return kodBangsaDAO.findAll().size();
    }

    @Transactional
    public int countKodHakmilik() {
        return kodHakmilikDAO.findAll().size();
    }

    @Transactional
    public int countKodDUN() {
        return kodDUNDAO.findAll().size();
    }

    @Transactional
    public int countKodParlimen() {
        return kodKawasanParlimenDAO.findAll().size();
    }

    @Transactional
    public long countKodDokumenAgensi() {
        return kodDokumenAgensiDAO.findAll().size();
    }

    @Transactional
    public int countUrusanDokumen() {
        return urusanDokumenDAO.findAll().size();
    }

    @Transactional
    public void simpanKodAgensi(KodAgensi ka) {
        kodAgensiDAO.saveOrUpdate(ka);
    }

    @Transactional
    public void simpanKodAkaun(KodAkaun ka) {
        kodAkaunDAO.saveOrUpdate(ka);
    }

    @Transactional
    public void simpanKodAliran(KodAliran ka) {
        kodAliranDAO.saveOrUpdate(ka);
    }

    @Transactional
    public void simpanKodBPM(KodBandarPekanMukim bpm) {
        kodBPMDAO.saveOrUpdate(bpm);
    }

    @Transactional
    public void simpanKodAgensiKutipanCawangan(KodAgensiKutipanCawangan kakc) {
        kodAgensiCawanganDAO.saveOrUpdate(kakc);
    }

    @Transactional
    public void simpanKodBangsa(KodBangsa kb) {
        kodBangsaDAO.saveOrUpdate(kb);
    }

    @Transactional
    public void simpanKodCawanganJabatan(KodCawanganJabatan kcj) {
        kodCawJabatanDAO.saveOrUpdate(kcj);
    }

    @Transactional
    public void simpanKodDokumenAgensi(KodDokumenAgensi kda) {
        kodDokumenAgensiDAO.saveOrUpdate(kda);
    }

    @Transactional
    public void simpanKodCawangan(KodCawangan kc) {
        kodCawanganDAO.saveOrUpdate(kc);
    }

    @Transactional
    public void simpanKodHakmilik(KodHakmilik kh) {
        kodHakmilikDAO.saveOrUpdate(kh);
    }

    @Transactional
    public void simpanKodDokumen(KodDokumen kd) {
        kodDokumenDAO.saveOrUpdate(kd);
    }

    @Transactional
    public void simpanKodDUN(KodDUN kd) {
        kodDUNDAO.saveOrUpdate(kd);
    }

    @Transactional
    public void simpanKodParlimen(KodKawasanParlimen kp) {
        kodKawasanParlimenDAO.saveOrUpdate(kp);
    }

    @Transactional
    public void simpanSloganSurat(SloganSurat ss) {
        sloganSuratDAO.saveOrUpdate(ss);
    }

    @Transactional
    public void simpanUrusanDokumen(UrusanDokumen ud) {
        urusanDokumenDAO.saveOrUpdate(ud);
    }

    @Transactional
    public void updateAktif(KodBandarPekanMukim bpm) {
        kodBPMDAO.saveOrUpdate(bpm);
    }

    @Transactional
    public List<KodUrusanAgensi> allKodUrusAgensi() {
        return kodUrusAgensiDAO.findAll();
    }

    @Transactional
    public List<KodAkaun> allKodAkaun() {
        return kodAkaunDAO.findAll();
    }

    @Transactional
    public List<KodBangsa> allKodBangsa() {
        return kodBangsaDAO.findAll();
    }

    @Transactional
    public List<KodHakmilik> allKodHakmilik() {
        return kodHakmilikDAO.findAll();
    }

    @Transactional
    public List<KodCawangan> allKodCawangan() {
        return kodCawanganDAO.findAll();
    }

    @Transactional
    public List<KodAliran> allKodAliran() {
        return kodAliranDAO.findAll();
    }

    @Transactional
    public List<KodCawanganJabatan> allKodCawanganJabatan() {
        return kodCawJabatanDAO.findAll();
    }

    @Transactional
    public List<KodBank> allKodBank() {
        return kodBankDAO.findAll();
    }

    @Transactional
    public List<KodAgensiKutipanCawangan> allKodAgensiKutipanCawangan() {
        return kodAgensiCawanganDAO.findAll();
    }

    @Transactional
    public List<KodAgensi> allKodAgensi() {
        return kodAgensiDAO.findAll();
    }

    @Transactional
    public List<KodDokumenAgensi> allKodDokumenAgensi() {
        return kodDokumenAgensiDAO.findAll();
    }

    @Transactional
    public List<KodDokumen> allKodDokumen() {
        return kodDokumenDAO.findAll();
    }

    @Transactional
    public List<KodDUN> allKodDUN() {
        return kodDUNDAO.findAll();
    }

    @Transactional
    public List<UrusanDokumen> allUrusanDokumen() {
        return urusanDokumenDAO.findAll();
    }

    @Transactional
    public List<SenaraiKodRujukan> allSenaraiKodRujukan() {
        return senaraiKodRujukanDAO.findAll();
    }

    @Transactional
    public void simpanKodUrusanAgensi(KodUrusanAgensi kua) {
        kodUrusAgensiDAO.saveOrUpdate(kua);
    }

    @Transactional
    public void updateKodUrusanAgensi(KodUrusanAgensi kua) {
        kodUrusAgensiDAO.saveOrUpdate(kua);
    }

    public List<KodJabatan> findKodJabatan(String kodJabatan) {
        Session session = sessionProvider.get();
        Query q = session.createQuery("SELECT p FROM etanah.model.KodJabatan p WHERE p.kod = :kodJabatan ");
        q.setParameter("kodJabatan", kodJabatan);
        return q.list();
    }

    @Transactional
    public List<KodKawasanParlimen> allKodKawasanParlimen() {
        return kodKawasanParlimenDAO.findAll();
    }

    public List<KodKawasanParlimen> findKodKawasanParlimen2(String nama) {

        String qBase = "Select a from KodKawasanParlimen a ";
        String query = "Select a from KodKawasanParlimen a ";
        String cond = " Where ";
        String mulCond = " and ";
        if (StringUtils.isNotBlank(nama) || nama != null) {
            if (query.equalsIgnoreCase(qBase)) {
                query += " " + cond + " UPPER(a.nama) LIKE :nama ";
            } else {
                query += " " + mulCond + " UPPER(a.nama) LIKE:nama ";

            }
        }
        Query q = sessionProvider.get().createQuery(query);
        if (nama != null) {
            q.setString("nama", "%" + nama.toUpperCase() + "%");
        }
        return q.list();

    }

    public List<KodAgensi> searchKodAgensiADN(String kodNegeri) {

        try {
            String query = "Select u from etanah.model.KodAgensi u WHERE u.kategoriAgensi.kod in ('ADN')  AND kodNegeri.kod =:kodNegeri";
            Query q = sessionProvider.get().createQuery(query);

            q.setString("kodNegeri", kodNegeri);


            return q.list();
        } finally {
        }
    }

    @Transactional
    public List<SloganSurat> allSloganSurat() {
        return sloganSuratDAO.findAll();
    }
}
