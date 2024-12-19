package etanah.service;

import com.google.inject.Inject;
import etanah.dao.PemohonDAO;
import etanah.dao.PermohonanDAO;
import etanah.model.Hakmilik;
import etanah.model.HakmilikPihakBerkepentingan;
import etanah.model.KodUrusan;
import etanah.model.Pemohon;
import etanah.model.Permohonan;
import etanah.model.PermohonanAtasPihakBerkepentingan;
import etanah.model.PermohonanPihak;
import etanah.service.common.HakmilikPihakKepentinganService;
import etanah.service.common.PermohonanPihakService;
import etanah.view.daftar.constant.RegConstant;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.math.fraction.Fraction;
import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;

public class SyerValidationService {

    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    PemohonDAO pemohonDAO;
    @Inject
    PermohonanPihakService permohonanPihakService;
    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;
    @Inject
    HakmilikPihakKepentinganService hakmilikPihakService;
    private static final Logger LOG = Logger.getLogger(SyerValidationService.class);
    private static boolean isDebug = LOG.isDebugEnabled();   

    public int doValidateSyerPortion(String idPermohonan, String idHakmilik) throws Exception {
        Fraction f = Fraction.ZERO;
        Fraction f2 = Fraction.ZERO;
        if (StringUtils.isNotBlank(idPermohonan)) {
            Permohonan p = permohonanDAO.findById(idPermohonan);
            if (p != null) {
                List<PermohonanPihak> pp = p.getSenaraiPihak();
                List pemohon = findPemohonByPermohonan(p, idHakmilik);
//                List senarai2 = new ArrayList<PermohonanAtasPihakBerkepentingan>();

                if (pemohon.isEmpty()) {
                    pemohon = findMohonAtasPihak(p, idHakmilik);
                }

                if (pemohon.isEmpty()) {
                    return -1;
                }
//                String idHakmilik = "";
//                if (p.getSenaraiHakmilik().size() > 0) {
//                    idHakmilik = p.getSenaraiHakmilik().get(0).getHakmilik().getIdHakmilik();
//                }

                for (Object obj : pemohon) {
                    
                    long idPihak = 0;
                    String kodPihak = "";
                    HakmilikPihakBerkepentingan h = null;

                    if (obj instanceof Pemohon) {
                        Pemohon pe = (Pemohon)obj;
                        idPihak = pe.getPihak().getIdPihak();
                        kodPihak = pe.getJenis().getKod();
                        h = findSyerPihakFromHakmilikPihak(idPihak, idHakmilik,
                                ArrayUtils.contains(RegConstant.SENARAI_URUSAN_PAPAR_PIHAK_BERKEPENTINGAN, p.getKodUrusan().getKod()));
                    } else if (obj instanceof PermohonanAtasPihakBerkepentingan) {
                        PermohonanAtasPihakBerkepentingan pb = (PermohonanAtasPihakBerkepentingan)obj;
//                        idPihak = pb.getPihakBerkepentingan().getPihak().getIdPihak();
                        h = pb.getPihakBerkepentingan();
                    }

//                    Fraction f_tmp = new Fraction();
                    //validate urusan berangkai
                    Fraction f_tmp = Fraction.ZERO;
//                    if (StringUtils.isNotBlank(p.getIdKumpulan()) && p.getPermohonanSebelum() != null && !p.getKodUrusan().getKod().equals("PMT")) {
//                        //if berangkai.. check mohon_pihak.. assuming pihak still no registered
//                        //and permohonan sebelum is PMT?
//                        Permohonan ps = p.getPermohonanSebelum();
//                        List<PermohonanPihak> y = ps.getSenaraiPihak();
//                        for (PermohonanPihak q : y) {
//                            if (q.getPihak().getIdPihak() == pemohon1.getPihak().getIdPihak()) {
//                                f_tmp = new Fraction(q.getSyerPembilang(), q.getSyerPenyebut());
//                            }
//                        }
//                    } else {
//                        HakmilikPihakBerkepentingan h = findSyerPihakFromHakmilikPihak(pemohon1.getPihak().getIdPihak(), idHakmilik);
//                        f_tmp = new Fraction(h.getSyerPembilang(), h.getSyerPenyebut());
//                        System.out.println("single :: f2 before add :" + f_tmp);
//                    }

//                    HakmilikPihakBerkepentingan h = findSyerPihakFromHakmilikPihak(idPihak, idHakmilik);
                    boolean isFlag = false;
                    if (h != null) {
                        if (isDebug) {
                            LOG.debug("syer pembilang hpk= " + h.getSyerPembilang());
                            LOG.debug("syer penyebut hpk= " + h.getSyerPenyebut());
                        }
                        if (h.getSyerPembilang() == 0 && h.getSyerPenyebut() == 0) {
                            continue;
                        }
                        f_tmp = new Fraction(h.getSyerPembilang(), h.getSyerPenyebut());
                        isFlag = true;
                    } else {
                        PermohonanPihak mohonPihak = permohonanPihakService.getSenaraiPermohonanPihak(idPihak, idHakmilik, p, kodPihak);
                        if (mohonPihak != null) {
                            if (isDebug) {
                                LOG.debug("syer pembilang mohon pihak= " + mohonPihak.getSyerPembilang());
                                LOG.debug("syer penyebut mohon pihak= " + mohonPihak.getSyerPenyebut());
                            }
                            if (mohonPihak.getSyerPembilang() == 0 && mohonPihak.getSyerPenyebut() == 0) {
                                continue;
                            }
                            f_tmp = new Fraction(mohonPihak.getSyerPembilang(), mohonPihak.getSyerPenyebut());
                            isFlag = true;
                        }
                    }

                    if (!isFlag) {
                        return 0;
                    }

                    if (!f_tmp.equals(Fraction.ZERO)) {
                        f2 = f2.add(f_tmp);
                    }
                }

                if (isDebug) {
                    LOG.debug("f2 =" + f2);
                    LOG.debug("denominator = " + f2.getDenominator());
                }

                if (f2.getDenominator() == 1) {
                    f2 = Fraction.ONE;
                }

                for (PermohonanPihak permohonanPihak : pp) {

                    if (permohonanPihak == null
                            || permohonanPihak.getHakmilik() == null
                            || permohonanPihak.getJenis() == null) {
                        continue;
                    }

                    String jenisPB = permohonanPihak.getJenis().getKod();
                    if ( jenisPB.equals("WAR") ) continue;

                    Hakmilik h = permohonanPihak.getHakmilik();

                    if (permohonanPihak.getSyerPembilang() == 0 && permohonanPihak.getSyerPenyebut() == 0) {
                        continue;
                    }

                    if (!h.getIdHakmilik().equals(idHakmilik)) {
                        continue;
                    }

                    if (isDebug) {
                        LOG.debug("syer pembilang permohonan pihak = " + permohonanPihak.getSyerPembilang());
                        LOG.debug("syer penyebut permohonan pihak = " + permohonanPihak.getSyerPenyebut());
                    }

                    Fraction f_tmp = new Fraction(permohonanPihak.getSyerPembilang(), permohonanPihak.getSyerPenyebut());
                    f = f.add(f_tmp);
                }

//                return f.compareTo(Fraction.ONE);
                if (isDebug) {
                    LOG.debug("f = " + f);
                    LOG.debug("f2 = " + f2);
                }
                return f.compareTo(f2);
            }
        }
        return 0;
    }
    
     public int validateSyer( Permohonan permohonan, Hakmilik hm ) throws Exception {
        
        if (permohonan == null) throw new RuntimeException("Permohonan tiada!!");
        
        List<Pemohon> senaraiPemohonTerlibat = permohonan.getSenaraiPemohon();
        List<PermohonanPihak> senaraiPermohonanPihakTerlibat = permohonan.getSenaraiPihak();
        if (senaraiPemohonTerlibat.isEmpty()) throw new RuntimeException ("Pemohon tiada!");
        if (senaraiPermohonanPihakTerlibat.isEmpty()) throw new RuntimeException ("Permohonan Pihak tiada!");
        
        Fraction syerPemohon = Fraction.ZERO;
        Fraction syerPihakTerlibat = Fraction.ZERO;
        
        for ( Pemohon pemohon : senaraiPemohonTerlibat ) {
            if (pemohon == null) continue;
            Hakmilik h = pemohon.getHakmilik();
            if (h == null) continue;
            if (!h.equals(hm)) continue;
            
            int syerPembilang = pemohon.getSyerPembilang() != null ? pemohon.getSyerPembilang():0;
            int syerPenyebut = pemohon.getSyerPenyebut() != null ? pemohon.getSyerPenyebut():0;
            if ( syerPembilang == 0 && syerPenyebut == 0 ) continue;
            Fraction f = new Fraction( syerPembilang, syerPenyebut );
            syerPemohon = syerPemohon.add(f);
        }
        
        for ( PermohonanPihak pp : senaraiPermohonanPihakTerlibat ) {
            if (pp == null || pp.getHakmilik() == null
                            || pp.getJenis() == null) {
                        continue;
                    } 
            Hakmilik h = pp.getHakmilik();
            if (h == null) continue;
            if (!h.equals(hm)) continue;
            String jenisPB = pp.getJenis().getKod();
//            if ( jenisPB.equals("WAR") ) continue; // error bila PNPA pilih Waris sbn PB.
            
            int syerPembilang = pp.getSyerPembilang() != null ? pp.getSyerPembilang():0;
            int syerPenyebut = pp.getSyerPenyebut() != null ? pp.getSyerPenyebut():0;
            if ( syerPembilang == 0 && syerPenyebut == 0 ) continue;
            Fraction f = new Fraction( syerPembilang, syerPenyebut );
            syerPihakTerlibat = syerPihakTerlibat.add(f);            
        }
        
        if (isDebug) {
                    LOG.debug("syerPemohon = " + syerPemohon);
                    LOG.debug("syerPihakTerlibat = " + syerPihakTerlibat);
                }
        
        if (syerPemohon.compareTo(Fraction.ZERO) == 0) return 0;
        
        return syerPemohon.compareTo(syerPihakTerlibat);
    }
    
    public int validateSyerBaru( Permohonan permohonan, Hakmilik hm ) throws Exception {
        
        if (permohonan == null) throw new RuntimeException("Permohonan tiada!!");
        
        List<Pemohon> senaraiPemohonTerlibat = permohonan.getSenaraiPemohon();
        List<PermohonanPihak> senaraiPermohonanPihakTerlibat = permohonan.getSenaraiPihak();
        List<HakmilikPihakBerkepentingan> _senaraiPihak = new ArrayList<HakmilikPihakBerkepentingan>();
        if (senaraiPemohonTerlibat.isEmpty()) throw new RuntimeException ("Pemohon tiada!");
        if (senaraiPermohonanPihakTerlibat.isEmpty()) throw new RuntimeException ("Permohonan Pihak tiada!");
        _senaraiPihak = hakmilikPihakService.findPihakActiveByHakmilik(hm);
        LOG.info(_senaraiPihak.size());
        LOG.info(hm.getIdHakmilik());
        
        Fraction syerPemohon = Fraction.ONE;
        Fraction syerPihakTerlibat = Fraction.ZERO;
        
        for ( Pemohon pemohon : senaraiPemohonTerlibat ) {
            if (pemohon == null) continue;
            Hakmilik h = pemohon.getHakmilik();
            if (h == null) continue;
            if (!h.equals(hm)) continue;
            
            int syerPembilang = pemohon.getSyerPembilang() != null ? pemohon.getSyerPembilang():0;
            int syerPenyebut = pemohon.getSyerPenyebut() != null ? pemohon.getSyerPenyebut():0;
            if ( syerPembilang == 0 && syerPenyebut == 0 ) continue;
            Fraction f = new Fraction( syerPembilang, syerPenyebut );
//            syerPemohon = syerPemohon.add(f);
        }
        
        for ( HakmilikPihakBerkepentingan pp : _senaraiPihak ) {
            if (pp == null || pp.getHakmilik() == null
                            || pp.getJenis() == null) {
                        continue;
                    } 
            Hakmilik h = pp.getHakmilik();
            if (h == null) continue;
            if (!h.equals(hm)) continue;
            String jenisPB = pp.getJenis().getKod();
//            if ( jenisPB.equals("WAR") ) continue; // error bila PNPA pilih Waris sbn PB.
            
            int syerPembilang = pp.getSyerPembilang() != null ? pp.getSyerPembilang():0;
            int syerPenyebut = pp.getSyerPenyebut() != null ? pp.getSyerPenyebut():0;
            if ( syerPembilang == 0 && syerPenyebut == 0 ) continue;
            Fraction f = new Fraction( syerPembilang, syerPenyebut );
            syerPihakTerlibat = syerPihakTerlibat.add(f);            
        }
        
        if (isDebug) {
                    LOG.debug("syerPemohon = " + syerPemohon);
                    LOG.debug("syerPihakTerlibat = " + syerPihakTerlibat);
                }
        
        if (syerPemohon.compareTo(Fraction.ZERO) == 0) return 0;
        
        return syerPemohon.compareTo(syerPihakTerlibat);
    }

    public int doValidateSyerPortionConsent(String idPermohonan, String idHakmilik, String jenis) throws Exception {
        Fraction f = Fraction.ZERO;
        Fraction f2 = Fraction.ZERO;
        if (StringUtils.isNotBlank(idPermohonan)) {
            Permohonan p = permohonanDAO.findById(idPermohonan);
            if (p != null) {
                //--------------diff---------
                List<PermohonanPihak> pp = new ArrayList<PermohonanPihak>();
                if (jenis.equals("penerima")) {
                    pp = permohonanPihakService.getSenaraiPmohonPihakByNotKod(idPermohonan, "PGA");
                } else if (jenis.equals("gadaian")) {
                    pp = permohonanPihakService.getSenaraiPmohonPihakByKod(idPermohonan, "PGA");
                } else {
                    pp = p.getSenaraiPihak();
                }
                //-----------------------
                List<Pemohon> pemohon = findPemohonByPermohonan(p, idHakmilik);
                if (pemohon.size() == 0 || pp.size() == 0) {
                    return 0;
                }

                for (Pemohon pemohon1 : pemohon) {
                    //validate urusan berangkai
                    Fraction f_tmp = Fraction.ZERO;

                    HakmilikPihakBerkepentingan h = findSyerPihakFromHakmilikPihak(pemohon1.getPihak().getIdPihak(), idHakmilik);
                    boolean isFlag = false;
                    if (h != null) {
                        if (isDebug) {
                            LOG.debug("syer pembilang hpk= " + h.getSyerPembilang());
                            LOG.debug("syer penyebut hpk= " + h.getSyerPenyebut());
                        }
                        if (h.getSyerPembilang() == 0 && h.getSyerPenyebut() == 0) {
                            continue;
                        }
                        f_tmp = new Fraction(h.getSyerPembilang(), h.getSyerPenyebut());
                        isFlag = true;
                    } else {
                        PermohonanPihak mohonPihak = permohonanPihakService.getSenaraiMohonPihakByIdPihakIdHakmilik(pemohon1.getPihak().getIdPihak(), idHakmilik, idPermohonan);
                        if (mohonPihak != null) {
                            if (isDebug) {
                                LOG.debug("syer pembilang mohon pihak= " + mohonPihak.getSyerPembilang());
                                LOG.debug("syer penyebut mohon pihak= " + mohonPihak.getSyerPenyebut());
                            }
                            if (mohonPihak.getSyerPembilang() == 0 && mohonPihak.getSyerPenyebut() == 0) {
                                continue;
                            }
                            f_tmp = new Fraction(mohonPihak.getSyerPembilang(), mohonPihak.getSyerPenyebut());
                            isFlag = true;
                        }
                    }

                    if (!isFlag) {
                        return 0;
                    }

                    if (!f_tmp.equals(Fraction.ZERO)) {
                        f2 = f2.add(f_tmp);
                    }
                }

                if (isDebug) {
                    LOG.debug("f2 =" + f2);
                    LOG.debug("denominator = " + f2.getDenominator());
                }

                if (f2.getDenominator() == 1) {
                    f2 = Fraction.ONE;
                }

                for (PermohonanPihak permohonanPihak : pp) {

                    if (permohonanPihak == null || permohonanPihak.getHakmilik() == null) {
                        continue;
                    }
                    Hakmilik h = permohonanPihak.getHakmilik();

                    if (permohonanPihak.getSyerPembilang() == 0 && permohonanPihak.getSyerPenyebut() == 0) {
                        continue;
                    }

                    if (!h.getIdHakmilik().equals(idHakmilik)) {
                        continue;
                    }

                    if (isDebug) {
                        LOG.debug("syer pembilang permohonan pihak = " + permohonanPihak.getSyerPembilang());
                        LOG.debug("syer penyebut permohonan pihak = " + permohonanPihak.getSyerPenyebut());
                    }

                    Fraction f_tmp = new Fraction(permohonanPihak.getSyerPembilang(), permohonanPihak.getSyerPenyebut());
                    f = f.add(f_tmp);
                }

//                return f.compareTo(Fraction.ONE);
                if (isDebug) {
                    LOG.debug("f = " + f);
                    LOG.debug("f2 = " + f2);
                }
                return f.compareTo(f2);
            }
        }
        return 0;
    }

    public int doValidateSyerPortionKesConsent(String idPermohonan) throws Exception {
        Fraction f = Fraction.ZERO;
        Fraction f2 = Fraction.ZERO;
        if (StringUtils.isNotBlank(idPermohonan)) {
            Permohonan p = permohonanDAO.findById(idPermohonan);
            if (p != null) {
                List<PermohonanPihak> pp = permohonanPihakService.getSenaraiPmohonPihakByNotTwoKod(idPermohonan, "PM", "WAR");
                List<PermohonanPihak> siMatiList = permohonanPihakService.getSenaraiPmohonPihakByKod(idPermohonan, "PM");

                if (siMatiList.size() == 0 || pp.size() == 0) {
                    return 0;
                }
                String idHakmilik = "";
                if (p.getSenaraiHakmilik().size() > 0) {
                    idHakmilik = p.getSenaraiHakmilik().get(0).getHakmilik().getIdHakmilik();
                }

                for (PermohonanPihak siMati : siMatiList) {
                    Fraction f_tmp = Fraction.ZERO;
                    HakmilikPihakBerkepentingan h = findSyerPihakFromHakmilikPihak(siMati.getPihak().getIdPihak(), idHakmilik);
                    f_tmp = new Fraction(h.getSyerPembilang(), h.getSyerPenyebut());
                    f2 = f2.add(f_tmp);
                }

                for (PermohonanPihak permohonanPihak : pp) {
                    Fraction f_tmp = new Fraction(permohonanPihak.getSyerPembilang(), permohonanPihak.getSyerPenyebut());
                    f = f.add(f_tmp);
                }

                return f.compareTo(f2);
            }
        }
        return 0;
    }

    public List<Pemohon> findPemohonByPermohonan(Permohonan permohonan, String idHakmilik) {
        String query = "Select p from etanah.model.Pemohon p " + "where p.permohonan.idPermohonan = :idPermohonan"
                + " and p.hakmilik.idHakmilik = :idHakmilik";
        return sessionProvider.get().createQuery(query).setString("idPermohonan", permohonan.getIdPermohonan())
                .setParameter("idHakmilik", idHakmilik)
                .list();
    }

    public List<PermohonanAtasPihakBerkepentingan> findMohonAtasPihak (Permohonan permohonan, String idHakmilik) {
            String query = "Select p from etanah.model.PermohonanAtasPihakBerkepentingan p " 
                    + "where p.permohonan.idPermohonan = :idPermohonan "
                    + "and p.pihakBerkepentingan.hakmilik.idHakmilik = :idHakmilik ";
        return sessionProvider.get().createQuery(query).setString("idPermohonan", permohonan.getIdPermohonan())
                .setParameter("idHakmilik", idHakmilik)
                .list();
    }

    public HakmilikPihakBerkepentingan findSyerPihakFromHakmilikPihak(Long idPihak, String idHakmilik) {
        String query = "Select h from etanah.model.HakmilikPihakBerkepentingan h "
                + "where h.pihak.idPihak = :idPihak and h.aktif='Y' and h.hakmilik.idHakmilik = :idHakmilik "
                + "and h.jenis.kod ='PM'";
        Query q = sessionProvider.get().createQuery(query);
        q.setLong("idPihak", idPihak);
        q.setString("idHakmilik", idHakmilik);

        HakmilikPihakBerkepentingan pihak = new HakmilikPihakBerkepentingan();
        if (q.list().size() > 1) {
            pihak = (HakmilikPihakBerkepentingan) q.list().get(0);
        } else {
            pihak = (HakmilikPihakBerkepentingan) q.uniqueResult();
        }
//        return ((Double) q.iterate().next());
        return pihak;
    }

    public HakmilikPihakBerkepentingan findSyerPihakFromHakmilikPihak(Long idPihak, String idHakmilik, boolean doNotPaparPemilik) {
        String query = "Select h from etanah.model.HakmilikPihakBerkepentingan h "
                + "where h.pihak.idPihak = :idPihak and h.aktif='Y' and h.hakmilik.idHakmilik = :idHakmilik ";
        if (doNotPaparPemilik) query = query + "and h.jenis.kod not in (:list)";
        else query = query + "and h.jenis.kod in (:list)";
                
        Query q = sessionProvider.get().createQuery(query);
        q.setLong("idPihak", idPihak);
        q.setString("idHakmilik", idHakmilik);
        q.setParameterList("list", RegConstant.JENIS_TUAN_TANAH);

        HakmilikPihakBerkepentingan pihak = new HakmilikPihakBerkepentingan();
        if (q.list().size() > 1) {
            pihak = (HakmilikPihakBerkepentingan) q.list().get(0);
        } else {
            pihak = (HakmilikPihakBerkepentingan) q.uniqueResult();
        }
//        return ((Double) q.iterate().next());
        return pihak;
    }


    public HakmilikPihakBerkepentingan findSyerPihakFromHakmilikPihak(Long idPihak, String idHakmilik, String kod) {
        String query = "Select h from etanah.model.HakmilikPihakBerkepentingan h " + "where h.pihak.idPihak = :idPihak and h.aktif='Y' and h.hakmilik.idHakmilik = :idHakmilik";
        if (StringUtils.isNotBlank(kod)) {
            query  +=  " and h.jenis.kod =:kod";
        }
        Query q = sessionProvider.get().createQuery(query);
        q.setLong("idPihak", idPihak);
        q.setString("idHakmilik", idHakmilik);
        if (StringUtils.isNotBlank(kod)) {
            q.setString("kod", kod);
        }

        HakmilikPihakBerkepentingan pihak = new HakmilikPihakBerkepentingan();
        if (q.list().size() > 1) {
            pihak = (HakmilikPihakBerkepentingan) q.list().get(0);
        } else {
            pihak = (HakmilikPihakBerkepentingan) q.uniqueResult();
        }
//        return ((Double) q.iterate().next());
        return pihak;
    }
}
