package etanah.view.stripes.hasil;

import etanah.model.*;
import org.hibernate.Session;
import com.google.inject.Inject;
import org.apache.log4j.Logger;
import able.stripes.AbleActionBean;
import etanah.dao.AkaunDAO;
import etanah.dao.KumpulanAkaunDAO;
import etanah.dao.hasil.TagAkaunDAO;
import etanah.model.hasil.TagAkaun;
import etanah.model.hasil.TagAkaunAhli;
import net.sourceforge.stripes.action.*;
import etanah.view.etanahActionBeanContext;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;
import oracle.bpel.services.workflow.WorkflowException;
import org.apache.commons.lang.StringUtils;
import org.hibernate.Query;
import org.displaytag.util.ParamEncoder;
import org.displaytag.tags.TableTagParameters;

/**
 * @author haqqiem
 * Thu Aug 1 11:45 AM
 */
@UrlBinding("/hasil/kumpulan_akaun")
public class KumpulanAkaunActionBean extends AbleActionBean{
    private static final Logger LOG = Logger.getLogger(KumpulanAkaunActionBean.class);
    private static boolean isDebug = LOG.isDebugEnabled();
    SimpleDateFormat sdfYear = new SimpleDateFormat("yyyy");
    private static final String KUMP_BARU_VIEW = "/WEB-INF/jsp/hasil/kumpulan_akaun.jsp";
    private static final String KUMP_BARU_ID = "/WEB-INF/jsp/hasil/kumpulan_akaun_1.jsp";
    
    private Akaun akaun = new Akaun();
    private Hakmilik hakmilik = new Hakmilik();
    private static TagAkaun tagAkaun = new TagAkaun();
    private static TagAkaunAhli tagAkaunAhli = new TagAkaunAhli();
    private static KumpulanAkaun kumpulanAkaun = new KumpulanAkaun();
    private Pengguna pengguna = new Pengguna();
    
    private String idTagKumpulan;
    private static String flagKumpulan;
    private static String kodNegeri = null;
    private static String strAkaun = "";
//    private static int dari = 0;
//    private static int total = 0;
    private String daerah = null;
    private String bandarPekanMukim = null;
    private String seksyen = null;
    private String kodStatusHakmilik = null;
    private String kodHakmilik = null;
    private String noHakmilik = null;
    private String lot = null;
    private String noLot = null;
    private String namaPembayar = null;
    private String namaPemilik = null;
    private String kategoriTanah = null;
    private String amaunDari = null;
    private String amaunHingga = null;
    private String dariTahun = null;
    private String hinggaTahun = null;
    private static String currYear = null;
    
    private boolean flag = false;
    private boolean pilihSemua = false;
    
    private List<KodSeksyen> senaraiKodSeksyen = new ArrayList<KodSeksyen>();
    private List<KodBandarPekanMukim> senaraiBPM = new ArrayList<KodBandarPekanMukim>();
    private List<Akaun> listAkaun = new ArrayList<Akaun>();
    private static List<Akaun> senaraiAkaun = new ArrayList<Akaun>();
    private List<TagAkaunAhli> senaraiKumpulanAhli = new ArrayList<TagAkaunAhli>();
    private List<Akaun> listAkaunTemp = new ArrayList<Akaun>();
    
    @Inject protected com.google.inject.Provider<Session> sessionProvider;
    @Inject etanah.Configuration conf;
    @Inject TagAkaunDAO tagAkaunDAO;
    @Inject KumpulanAkaunDAO kumpulanAkaunDAO;
    @Inject AkaunDAO akaunDAO;
    @Inject KutipanHasilManager manager;
    
    @HandlesEvent ("Step1")
    @DefaultHandler
    public Resolution showForm(){
        LOG.info("showForm");
        pengguna = (Pengguna) context.getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        currYear = sdfYear.format(new java.util.Date());
        if("04".equals(conf.getProperty("kodNegeri"))){
            kodNegeri = "melaka";
        }
        if("05".equals(conf.getProperty("kodNegeri"))){
            kodNegeri = "sembilan";
        }
        LOG.info("negeri :"+kodNegeri);
        LOG.info("flagKumpulan :"+flagKumpulan);
        strAkaun="";
        if (flagKumpulan.equals("tag")){
            tagAkaun = tagAkaunDAO.findById(idTagKumpulan);
            LOG.info("idTagKumpulan :"+idTagKumpulan);
        }
        
        if (flagKumpulan.equals("kumpulan")){
            kumpulanAkaun = kumpulanAkaunDAO.findById(idTagKumpulan);
            LOG.info("kumpulanAkaun.getIdKumpulan() :"+kumpulanAkaun.getIdKumpulan());
        }
        return new ForwardResolution(KUMP_BARU_VIEW);
    }
    
    public Resolution penyukatanBPM() {

        String kodDaerah = getDaerah();
        String sql = null;
        Session s = sessionProvider.get();
        Query q = null;
        if (kodDaerah == null || kodDaerah.equals("")) {
            sql = "select bpm from KodBandarPekanMukim bpm ";
            q = s.createQuery(sql);
        } else {
            sql = "select bpm from KodBandarPekanMukim bpm where bpm.daerah.kod = :kod ";
            q = s.createQuery(sql);
            q.setString("kod", kodDaerah);
        }
        senaraiBPM = q.list();

        return new ForwardResolution(KUMP_BARU_VIEW);
    }
    
    public Resolution penyukatanSeksyen() {

        String kodDaerah = getContext().getRequest().getParameter("daerah");
        String sql = null;
        Session s = sessionProvider.get();
        Query q = null;
        if (kodDaerah == null || kodDaerah.equals("")) {
            sql = "select bpm from KodBandarPekanMukim bpm ";
            q = s.createQuery(sql);
        } else {
            sql = "select bpm from KodBandarPekanMukim bpm where bpm.daerah.kod = :kod ";
            q = s.createQuery(sql);
            q.setString("kod", kodDaerah);
        }
        senaraiBPM = q.list();
        String kodBPM = getBandarPekanMukim();
        LOG.debug("BPM-->" + kodBPM);
        if (kodBPM == null || kodBPM.equals("")) {
            sql = "select sek from KodSeksyen sek ";
            q = s.createQuery(sql);
        } else {
            sql = "select sek from KodSeksyen sek where sek.kodBandarPekanMukim.kod = :kod ";
            q = s.createQuery(sql);
            q.setString("kod", kodBPM);
        }
        senaraiKodSeksyen = q.list();

        return new ForwardResolution(KUMP_BARU_VIEW);
    }
    
    public Resolution search() throws WorkflowException, ParseException{

        ParamEncoder encoder = new ParamEncoder("line");
        String page = getContext().getRequest().getParameter((encoder.encodeParameterName(TableTagParameters.PARAMETER_PAGE)));

        if (StringUtils.isNotBlank(page)) {
          set__pg_start((Integer.parseInt(page) - 1) * get__pg_max_records());
          set__pg_max_records(get__pg_start() + get__pg_max_records());
        }
        
        pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        listAkaun = findAll(getContext().getRequest().getParameterMap(), get__pg_start(), get__pg_max_records(), pengguna.getKodCawangan().getKod());
        set__pg_total_records(getTotalRecord(getContext().getRequest().getParameterMap(), pengguna.getKodCawangan().getKod()).intValue());
        
        
        LOG.info("listAkaun.size() "+listAkaun.size());
        if (isDebug) {
            LOG.debug("page = " + page);
            LOG.debug("page_start = " + get__pg_start());
            LOG.debug("max_records = " + get__pg_max_records());
            LOG.debug("total record = " + get__pg_total_records());
          }
        setFlag(true);
        penyukatanBPM();

        return new ForwardResolution(KUMP_BARU_VIEW);
    }
    
    public Long getTotalRecord(Map<String, String[]> param, String caw) {


        String query = "SELECT count(distinct a) FROM etanah.model.Akaun a, etanah.model.HakmilikPihakBerkepentingan hpk "
                + "WHERE a.kodAkaun.kod = 'AC' AND a.hakmilik.idHakmilik = hpk.hakmilik.idHakmilik AND hpk.aktif = 'Y' "
                + "AND hpk.jenis.kod in ('PM') ";
//                + "AND hpk.jenis.kod in ('PM','PA','WAR','ASL','JA','JK','KL','PA','PDP','PK','PLK','PP','RP','WKL','WPA','WS','PAT','PAP','PAW','WKF','PMG','PG') ";

        if (isNotBlank(param.get("namaPemilik"))) {
            query += "AND hpk.pihak.nama LIKE :namaPemilik ";
        }
        if (isNotBlank(param.get("noPengenalan"))) {
            query += "AND hpk.pihak.noPengenalan LIKE :noPengenalan ";
        }
        if (isNotBlank(param.get("jenisPengenalanPemilik"))) {
            query += "AND hpk.pihak.jenisPengenalan.kod = :jenisPengenalanPemilik ";
        }

        if (isNotBlank(param.get("noAkaun"))) {
            query += "AND a.noAkaun = :noAkaun ";
        }

        if (isNotBlank(param.get("idHakmilik"))) {
            query += "AND a.hakmilik.idHakmilik = :idHakmilik ";
        }


        if (isNotBlank(param.get("noLot"))) {
            query += "AND a.hakmilik.noLot = :noLot ";
        }

        if (isNotBlank(param.get("noHakmilik"))) {
            query += "AND a.hakmilik.noHakmilik = :noHakmilik ";
        }

        if (isNotBlank(param.get("bandarPekanMukim"))) {
            query += "AND a.hakmilik.bandarPekanMukim.kod = :bandarPekanMukim ";
        }

        if (isNotBlank(param.get("seksyen"))) {
            query += "AND a.hakmilik.seksyen.kod = :seksyen ";
        }

        if (isNotBlank(param.get("daerah"))) {
            query += "AND a.hakmilik.daerah.kod = :daerah ";
        }

        if (isNotBlank(param.get("lot"))) {
            query += "AND a.hakmilik.lot.kod = :lot ";
        }


        if (isNotBlank(param.get("kodHakmilik"))) {
            query += "AND a.hakmilik.kodHakmilik.kod = :kodHakmilik ";
        }

        if (isNotBlank(param.get("namaPembayar"))) {
            query += "AND a.pemegang.nama LIKE :namaPembayar ";
        }

        if (isNotBlank(param.get("noPengenalanP"))) {
            query += "AND a.pemegang.noPengenalan = :noPengenalanP ";
        }

        if (isNotBlank(param.get("jenisPengenalan"))) {
            query += "AND a.pemegang.jenisPengenalan.kod = :jenisPengenalan ";
        }

        if (isNotBlank(param.get("kodStatusHakmilik"))) {
            query += "AND a.hakmilik.kodStatusHakmilik.kod = :kodStatusHakmilik ";
        }
        if (isNotBlank(param.get("amaunDari"))) {
            query += "AND a.baki > :baki ";
        }
                
        if(isNotBlank(param.get("amaunDari")) && isNotBlank(param.get("dariTahun")) && isNotBlank(param.get("hinggaTahun"))){
            query += "AND t.akaunDebit = a.noAkaun AND (select sum(tran.amaun) from etanah.model.Transaksi tran where a.noAkaun = tran.akaunDebit and tran.untukTahun >= :tahunDari AND tran.untukTahun <= :tahunHingga) >= :baki ";//AND ak.baki <= :amaunHingga ";
            LOG.debug("ada msuk amaun dan tahun");
        }else if(isNotBlank(param.get("dariTahun")) && isNotBlank(param.get("hinggaTahun"))){
            query += "AND t.akaunDebit = a.noAkaun AND t.untukTahun >= :tahunDari AND t.untukTahun <= :tahunHingga ";
            LOG.debug("ada msuk tahun shj");
        }

        Query q = sessionProvider.get().createQuery(query);

        if (isNotBlank(param.get("idHakmilik"))) {
            LOG.debug("....::" + param.get("idHakmilik")[0].trim());
            q.setString("idHakmilik", param.get("idHakmilik")[0]);
        }

        if (isNotBlank(param.get("noLot"))) {
            q.setString("noLot", param.get("noLot")[0].trim());
        }
        if (isNotBlank(param.get("noHakmilik"))) {
            q.setString("noHakmilik", param.get("noHakmilik")[0].trim());
        }
        if (isNotBlank(param.get("kodHakmilik"))) {
            q.setString("kodHakmilik", param.get("kodHakmilik")[0].trim());
        }
        if (isNotBlank(param.get("bandarPekanMukim"))) {
            q.setString("bandarPekanMukim", param.get("bandarPekanMukim")[0].trim());
        }
        if (isNotBlank(param.get("seksyen"))) {
            q.setString("seksyen", param.get("seksyen")[0].trim());
        }
        if (isNotBlank(param.get("daerah"))) {
            q.setString("daerah", param.get("daerah")[0].trim());
        }
        if (isNotBlank(param.get("lot"))) {
            q.setString("lot", param.get("lot")[0].trim());
        }
        if (isNotBlank(param.get("noAkaun"))) {
            q.setString("noAkaun", param.get("noAkaun")[0].trim());
        }
        if (isNotBlank(param.get("namaPembayar"))) {
            q.setString("namaPembayar", param.get("namaPembayar")[0].trim() + "%");
        }
        if (isNotBlank(param.get("noPengenalanP"))) {
            q.setString("noPengenalanP", param.get("noPengenalanP")[0].trim());
        }
        if (isNotBlank(param.get("jenisPengenalan"))) {
            q.setString("jenisPengenalan", param.get("jenisPengenalan")[0].trim());
        }

        if (isNotBlank(param.get("namaPemilik"))) {
            q.setString("namaPemilik", param.get("namaPemilik")[0].trim() + "%");
        }
        if (isNotBlank(param.get("noPengenalan"))) {
            q.setString("noPengenalan", param.get("noPengenalan")[0].trim());
        }

        if (isNotBlank(param.get("jenisPengenalanPemilik"))) {
            q.setString("jenisPengenalanPemilik", param.get("jenisPengenalanPemilik")[0].trim());
        }
        if (isNotBlank(param.get("kodStatusHakmilik"))) {
            q.setString("kodStatusHakmilik", param.get("kodStatusHakmilik")[0].trim());
        }
        if (isNotBlank(param.get("amaunDari"))) {
            q.setString("baki", param.get("amaunDari")[0].trim());
        }
        if (isNotBlank(param.get("dariTahun")) && isNotBlank(param.get("hinggaTahun"))) {
            q.setString("tahunDari", param.get("dariTahun")[0]);
            q.setString("tahunHingga", param.get("hinggaTahun")[0]);
        }

        return (Long) q.iterate().next();
    }

    public List<Akaun> findAll(Map<String, String[]> param, int start, int max, String caw) {

        if (isDebug) {
            LOG.debug("from record [" + start + "]");
            LOG.debug("to record [" + max + "]");
        }


        String query = "SELECT distinct a FROM etanah.model.Akaun a, etanah.model.HakmilikPihakBerkepentingan hpk "
                + "WHERE a.kodAkaun.kod = 'AC' AND a.hakmilik.idHakmilik = hpk.hakmilik.idHakmilik AND hpk.aktif = 'Y' "
                + "AND hpk.jenis.kod in ('PM') ";
//                + "AND hpk.jenis.kod in ('PM','PA','WAR','ASL','JA','JK','KL','PA','PDP','PK','PLK','PP','RP','WKL','WPA','WS','PAT','PAP','PAW','WKF','PMG','PG') ";

        if (isNotBlank(param.get("namaPemilik"))) {
            query += "AND hpk.pihak.nama LIKE :namaPemilik ";
        }
        if (isNotBlank(param.get("noPengenalan"))) {
            query += "AND hpk.pihak.noPengenalan LIKE :noPengenalan ";
        }
        if (isNotBlank(param.get("jenisPengenalanPemilik"))) {
            query += "AND hpk.pihak.jenisPengenalan.kod = :jenisPengenalanPemilik ";
        }

        if (isNotBlank(param.get("noAkaun"))) {
            query += "AND a.noAkaun = :noAkaun ";
        }

        if (isNotBlank(param.get("idHakmilik"))) {
            query += "AND a.hakmilik.idHakmilik = :idHakmilik ";
        }


        if (isNotBlank(param.get("noLot"))) {
            query += "AND a.hakmilik.noLot LIKE :noLot ";
        }

        if (isNotBlank(param.get("noHakmilik"))) {
            query += "AND a.hakmilik.noHakmilik LIKE :noHakmilik ";
        }

        if (isNotBlank(param.get("bandarPekanMukim"))) {
            query += "AND a.hakmilik.bandarPekanMukim.kod = :bandarPekanMukim ";
        }

        if (isNotBlank(param.get("seksyen"))) {
            query += "AND a.hakmilik.seksyen.kod = :seksyen ";
        }

        if (isNotBlank(param.get("daerah"))) {
            query += "AND a.hakmilik.daerah.kod = :daerah ";
        }

        if (isNotBlank(param.get("lot"))) {
            query += "AND a.hakmilik.lot.kod = :lot ";
        }


        if (isNotBlank(param.get("kodHakmilik"))) {
            query += "AND a.hakmilik.kodHakmilik.kod = :kodHakmilik ";
        }

        if (isNotBlank(param.get("namaPembayar"))) {
            query += "AND a.pemegang.nama LIKE :namaPembayar ";
        }

        if (isNotBlank(param.get("noPengenalanP"))) {
            query += "AND a.pemegang.noPengenalan = :noPengenalanP ";
        }

        if (isNotBlank(param.get("jenisPengenalan"))) {
            query += "AND a.pemegang.jenisPengenalan.kod = :jenisPengenalan ";
        }
        if (isNotBlank(param.get("kodStatusHakmilik"))) {
            query += "AND a.hakmilik.kodStatusHakmilik.kod = :kodStatusHakmilik ";
        }
        if (isNotBlank(param.get("amaunDari"))) {
            query += "AND a.baki > :baki ";
        }
                
        if(isNotBlank(param.get("amaunDari")) && isNotBlank(param.get("dariTahun")) && isNotBlank(param.get("hinggaTahun"))){
            query += "AND t.akaunDebit = a.noAkaun AND (select sum(tran.amaun) from etanah.model.Transaksi tran where a.noAkaun = tran.akaunDebit and tran.untukTahun >= :tahunDari AND tran.untukTahun <= :tahunHingga) >= :baki ";//AND ak.baki <= :amaunHingga ";
            LOG.debug("ada msuk amaun dan tahun");
        }else if(isNotBlank(param.get("dariTahun")) && isNotBlank(param.get("hinggaTahun"))){
            query += "AND t.akaunDebit = a.noAkaun AND t.untukTahun >= :tahunDari AND t.untukTahun <= :tahunHingga ";
            LOG.debug("ada msuk tahun shj");
        }
        
        if (isDebug) {
            LOG.debug("query : " + query);
        }
        Query q = sessionProvider.get().createQuery(query);
        q.setFirstResult(start);
        q.setMaxResults(max);

        if (isNotBlank(param.get("idHakmilik"))) {
            q.setString("idHakmilik", param.get("idHakmilik")[0].trim());
        }

        if (isNotBlank(param.get("noLot"))) {
            q.setString("noLot", "%" + param.get("noLot")[0].trim() + "%");
        }
        if (isNotBlank(param.get("noHakmilik"))) {
            q.setString("noHakmilik", "%" + param.get("noHakmilik")[0].trim() + "%");
        }
        if (isNotBlank(param.get("kodHakmilik"))) {
            q.setString("kodHakmilik", param.get("kodHakmilik")[0].trim());
        }
        if (isNotBlank(param.get("bandarPekanMukim"))) {
//            System.out.println("....::" + param.get("hakmilik.bandarPekanMukim.kod")[0]);
            q.setString("bandarPekanMukim", param.get("bandarPekanMukim")[0].trim());
        }
        if (isNotBlank(param.get("seksyen"))) {
            q.setString("seksyen", param.get("seksyen")[0].trim());
        }
        if (isNotBlank(param.get("daerah"))) {
            q.setString("daerah", param.get("daerah")[0].trim());
        }
//        if (isNotBlank(param.get("hakmilik.lot.kod"))) {
//            q.setString("lot", param.get("hakmilik.lot.kod")[0].trim());
//        }
        if (isNotBlank(param.get("lot"))) {
            q.setString("lot", param.get("lot")[0].trim());
        }
        if (isNotBlank(param.get("noAkaun"))) {
            q.setString("noAkaun", param.get("noAkaun")[0].trim());
        }
        if (isNotBlank(param.get("namaPembayar"))) {
            q.setString("namaPembayar", "%" + param.get("namaPembayar")[0].trim() + "%");
        }
        if (isNotBlank(param.get("noPengenalanP"))) {
            q.setString("noPengenalanP", param.get("noPengenalanP")[0].trim());
        }
        if (isNotBlank(param.get("jenisPengenalan"))) {
            q.setString("jenisPengenalan", param.get("jenisPengenalan")[0].trim());
        }
//        if (isNotBlank(param.get("hpk.pihak.nama"))) {
//            q.setString("nama", param.get("hpk.pihak.nama")[0].trim() + "%");
//        }
//           if (isNotBlank(param.get("hpk.pihak.noPengenalan"))) {
//            q.setString("noPengenalan", param.get("hpk.pihak.noPengenalan")[0].trim() );
//        }
        if (isNotBlank(param.get("namaPemilik"))) {
            q.setString("namaPemilik", param.get("namaPemilik")[0].trim() + "%");
        }
        if (isNotBlank(param.get("noPengenalan"))) {
            q.setString("noPengenalan", param.get("noPengenalan")[0].trim());
        }
//           if (isNotBlank(param.get("hpk.pihak.noPengenalan"))) {
//            q.setString("noPengenalan", param.get("hpk.pihak.noPengenalan")[0].trim() );
//        }

        if (isNotBlank(param.get("jenisPengenalanPemilik"))) {
            q.setString("jenisPengenalanPemilik", param.get("jenisPengenalanPemilik")[0].trim());
        }
        if (isNotBlank(param.get("kodStatusHakmilik"))) {
            q.setString("kodStatusHakmilik", param.get("kodStatusHakmilik")[0].trim());
        }
        if (isNotBlank(param.get("amaunDari"))) {
            q.setString("baki", param.get("amaunDari")[0].trim());
        }
        if (isNotBlank(param.get("dariTahun")) && isNotBlank(param.get("hinggaTahun"))) {
            q.setString("tahunDari", param.get("dariTahun")[0]);
            q.setString("tahunHingga", param.get("hinggaTahun")[0]);
        }

        return q.list();
    }
    
    public static boolean isNotBlank(String[] str) {
        return !isBlank(str);
    }

    public static boolean isBlank(String[] str) {
        if (str == null) {
            return true;
        }
        if (str.length > 0) {
            return isBlank(str[0]);
        }
        return true;
    }

    public static boolean isBlank(String str) {
        int strLen;
        if (str == null || (strLen = str.length()) == 0) {
            return true;
        }
        for (int i = 0; i < strLen; i++) {
            if ((Character.isWhitespace(str.charAt(i)) == false)) {
                return false;
            }
        }
        return true;
    }
    
    public Resolution delete() {
        String idHakmilikRemove = getContext().getRequest().getParameter("idHakmilikRemove");
        LOG.info("delete: idHakmilik :" + idHakmilikRemove);
        LOG.info("1) list2.size :" + listAkaunTemp.size());
        LOG.info("delete:daerah :"+daerah);
        LOG.info("delete:bandarPekanMukim :"+bandarPekanMukim);
        List<Akaun> list3 = new ArrayList<Akaun>();
        if(StringUtils.isNotBlank(idHakmilikRemove)){
            for (Akaun a : listAkaunTemp) {
                if(!StringUtils.equals(idHakmilikRemove, a.getHakmilik().getIdHakmilik())){
                    LOG.debug("idHakmilik :" + a.getHakmilik().getIdHakmilik());
                    list3.add(a);
                }
            }
        }else{
            list3 = listAkaunTemp;
        }
        listAkaunTemp = list3;
        LOG.info("2) list2.size :" + listAkaunTemp.size());
        listAkaun = listAkaunTemp;
        LOG.info("3) list1.size :" + listAkaun.size());
//            set__pg_start(dari);
//            total = total-1;
//            set__pg_total_records(total);
        setFlag(true);
        penyukatanBPM();
        return new ForwardResolution(KUMP_BARU_VIEW).addParameter("popup", "true");
    }
    
    @HandlesEvent ("Step2")
    public Resolution saveTagAkaun() {
        if(pilihSemua){
            LOG.info("INSIDE IF");
            listAkaunTemp = allAccount();
            simpan(listAkaunTemp);
        }else{
            List<String> list = Arrays.asList(strAkaun.split(","));
            LOG.info("list.size() : " + list.size());
            for (String str : list) {
                Akaun a = akaunDAO.findById(str);
                if (a != null) {
                    listAkaunTemp.add(a);
                }
            }
            LOG.info("listAkaunTemp.size() : "+listAkaunTemp.size());
            simpan(listAkaunTemp);
        }

        return new ForwardResolution(KUMP_BARU_VIEW);
    }
    
    public Resolution simpan(List<Akaun> list) {
        pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        listAkaun = list;
        LOG.info("pengguna.size() : "+pengguna.getNama());
        LOG.info("list.size() : "+list.size());
        LOG.info("tagAkaun.getIdTag() : "+tagAkaun.getIdTag());
        String url = null;
        InfoAudit ia = new InfoAudit();
        Date now = new Date();
        if(listAkaun.isEmpty()){
            LOG.error("Tidak Berjaya. Tiada data untuk disimpan.");
            addSimpleError("Tiada maklumat untuk disimpan.");
                return new RedirectResolution(KumpulanAkaunTAGBaruActionBean.class);
        }else{
            List<Akaun> senAkaun= checkingHakmilik(listAkaun, tagAkaun.getIdTag());
            for (Akaun ak : senAkaun) {
                TagAkaunAhli taa = new TagAkaunAhli();
//                String[] name = {"hakmilik"};
//                Object[] value = {hm};
//                List<Akaun> senaraiAkaun = akaunDAO.findByEqualCriterias(name, value, null);
//                if(senaraiAkaun.size() > 1){
//                    for (Akaun akaun : senaraiAkaun) {
//                        if(akaun.getStatus() == null)
//                            continue;
//                        if(akaun.getStatus().getKod().equals("A"))
//                            taa.setAkaun(akaun);
//                    }
//                }else
                    taa.setAkaun(ak);
                taa.setTagAkaun(tagAkaun);
                taa.setCawangan(pengguna.getKodCawangan());
                ia.setDimasukOleh(pengguna);
                ia.setTarikhMasuk(now);
                taa.setInfoAudit(ia);
                senaraiKumpulanAhli.add(taa);
            }
            try{
                LOG.info("senaraiKumpulanAhli.size :"+senaraiKumpulanAhli.size());
                manager.saveorUpdateTagAkaunAhli(senaraiKumpulanAhli);
                url = KUMP_BARU_VIEW;
                addSimpleMessage("Maklumat BERJAYA disimpan. ID Kumpulan : "+tagAkaun.getIdTag());
            }catch(Exception ex){
                LOG.error("Maklumat Tidak Berjaya Disimpan :"+ex);
                addSimpleError("Maklumat TIDAK berjaya disimpan.");
                return new RedirectResolution(KumpulanAkaunTAGBaruActionBean.class);
            }
        }
        return new ForwardResolution(url);
    }
    
    @HandlesEvent ("Step3")
    public Resolution mainMenu(){
        return new RedirectResolution(KumpulanAkaunTAGBaruActionBean.class);
    }
    
    public List<Akaun> allAccount() {
        LOG.info("INSIDE allAccount()");
        List<Akaun> senarai = new ArrayList<Akaun>();
        ParamEncoder encoder = new ParamEncoder("line");
        String page = getContext().getRequest().getParameter((encoder.encodeParameterName(TableTagParameters.PARAMETER_PAGE)));
        pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);

        if (StringUtils.isNotBlank(page)) {
            set__pg_start((Integer.parseInt(page) - 1) * get__pg_max_records());
            set__pg_max_records(get__pg_start() + get__pg_max_records());
        }

        listAkaun = findAll(getContext().getRequest().getParameterMap(), get__pg_start(), get__pg_max_records(), pengguna.getKodCawangan().getKod());
        set__pg_total_records(getTotalRecord(getContext().getRequest().getParameterMap(), pengguna.getKodCawangan().getKod()).intValue());
        LOG.info("listAkaun.size() " + listAkaun.size());
        
        float paging1 = (float) Math.ceil((get__pg_total_records()/10.0));
        int paging = (int) paging1;
        senarai.addAll(listAkaun);
        
        LOG.info("paging : "+paging);
        if(paging >1){
            LOG.info("paging >1");
            for (int i=2;i<=paging;i++) {
                page = i+"";
                LOG.info("page : "+page);
                if (StringUtils.isNotBlank(page)) {
                    set__pg_start((Integer.parseInt(page) - 1) * get__pg_max_records());
//                    set__pg_max_records(get__pg_start() + get__pg_max_records());
                }

                listAkaun = findAll(getContext().getRequest().getParameterMap(), get__pg_start(), get__pg_max_records(), pengguna.getKodCawangan().getKod());
                set__pg_total_records(getTotalRecord(getContext().getRequest().getParameterMap(), pengguna.getKodCawangan().getKod()).intValue());
                LOG.info("listAkaun.size() " + listAkaun.size());
                senarai.addAll(listAkaun);
                LOG.info("------ senarai : "+senarai.size());
            }
        }
        LOG.info("senarai : "+senarai.size());

        return senarai;
    }
    
    @HandlesEvent("Step4")
    public Resolution saveKumpulan() {
        if(pilihSemua){
            listAkaunTemp = allAccount();
            simpanKumpulan(listAkaunTemp);
        }else{
            List<String> list = Arrays.asList(strAkaun.split(","));
            LOG.info("list.size() : " + list.size());
            for (String str : list) {
                Akaun a = akaunDAO.findById(str);
                if (a != null) {
                    listAkaunTemp.add(a);
                }
            }
            LOG.info("listAkaunTemp.size() : "+listAkaunTemp.size());
            simpanKumpulan(listAkaunTemp);
        }
        return new ForwardResolution(KUMP_BARU_VIEW);
    }    
    
    public Resolution simpanKumpulan(List<Akaun> list) {
        LOG.info("......::::: simpanKumpulan ::::::.....");
        pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        listAkaun = list;
        LOG.info("pengguna.size() : "+pengguna.getNama());
        LOG.info("listAkaun.size() : "+listAkaun.size());
        LOG.info("kumpulanAkaun.getIdKumpulan() : "+kumpulanAkaun.getIdKumpulan());
        List<Akaun> senAkaun = new ArrayList<Akaun>();
        String url = null;
        InfoAudit ia = new InfoAudit();
        Date now = new Date();
        if(listAkaun.isEmpty()){
            LOG.error("Tidak Berjaya. Tiada data untuk disimpan.");
            addSimpleError("Tiada maklumat untuk disimpan.");
                return new RedirectResolution(SearchingIDKumpulanActionBean.class);
        }else{
            for (Akaun ak : listAkaun) {

                ak.setKumpulan(kumpulanAkaun);
                    ia.setDimasukOleh(ak.getInfoAudit().getDimasukOleh());
                    ia.setTarikhMasuk(ak.getInfoAudit().getTarikhMasuk());
                    ia.setDiKemaskiniOleh(pengguna);
                    ia.setTarikhKemaskini(now);
                ak.setInfoAudit(ia);
                senAkaun.add(ak);
            }
            try{
                LOG.info("senAkaun.size :"+senAkaun.size());
                manager.saveorUpdateAkaun(senAkaun);
                url = KUMP_BARU_VIEW;
                addSimpleMessage("Maklumat BERJAYA disimpan. ID Kumpulan : "+kumpulanAkaun.getIdKumpulan());
            }catch(Exception ex){
                LOG.error("Maklumat Tidak Berjaya Disimpan :"+ex);
                addSimpleError("Maklumat TIDAK berjaya disimpan.");
                return new RedirectResolution(SearchingIDKumpulanActionBean.class);
            }
        }
        return new ForwardResolution(url);
    }
    
    @HandlesEvent ("Step5")
    public Resolution kembali(){
        return new RedirectResolution(SearchingIDKumpulanActionBean.class);
    }
    
    public List<Akaun> checkingHakmilik(List<Akaun> senAkaun, String idKump){
        List<Akaun> senaraiAkaun = new ArrayList<Akaun>();
        LOG.info("senAkaun.size() : "+senAkaun.size());
        for (Akaun ak : senAkaun) {
            String query = "SELECT p FROM etanah.model.hasil.TagAkaunAhli p WHERE p.tagAkaun.idTag =:idTag AND p.akaun.noAkaun =:noAkaun";
            LOG.debug("query: " + query);
            Query q = sessionProvider.get().createQuery(query);
            q.setString("idTag", idKump);
            q.setString("noAkaun", ak.getNoAkaun());
            List<TagAkaunAhli> senaraiAhliTAG = q.list();
            if(senaraiAhliTAG.isEmpty()){
                senaraiAkaun.add(ak);
            }
        }
        LOG.info("senaraiAkaun.size() : "+senaraiAkaun.size());
        return senaraiAkaun;
    }
    
    @HandlesEvent ("Step6")
    public Resolution updateList() {
        ParamEncoder encoder = new ParamEncoder("line");
        String page = getContext().getRequest().getParameter((encoder.encodeParameterName(TableTagParameters.PARAMETER_PAGE)));

        if (StringUtils.isNotBlank(page)) {
          set__pg_start((Integer.parseInt(page) - 1) * get__pg_max_records());
          set__pg_max_records(get__pg_start() + get__pg_max_records());
        }
        
        pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        listAkaun = findAll(getContext().getRequest().getParameterMap(), get__pg_start(), get__pg_max_records(), pengguna.getKodCawangan().getKod());
        set__pg_total_records(getTotalRecord(getContext().getRequest().getParameterMap(), pengguna.getKodCawangan().getKod()).intValue());
        LOG.info("listAkaun.size() "+listAkaun.size());
        if (isDebug) {
            LOG.debug("page = " + page);
            LOG.debug("page_start = " + get__pg_start());
            LOG.debug("max_records = " + get__pg_max_records());
            LOG.debug("total record = " + get__pg_total_records());
          }
        setFlag(true);
        penyukatanBPM();
        return new ForwardResolution(KUMP_BARU_VIEW);
    }

    public String getKodNegeri() {
        return kodNegeri;
    }

    public void setKodNegeri(String kodNegeri) {
        this.kodNegeri = kodNegeri;
    }

    public TagAkaun getTagAkaun() {
        return tagAkaun;
    }

    public void setTagAkaun(TagAkaun tagAkaun) {
        this.tagAkaun = tagAkaun;
    }

    public Akaun getAkaun() {
        return akaun;
    }

    public void setAkaun(Akaun akaun) {
        this.akaun = akaun;
    }

    public Hakmilik getHakmilik() {
        return hakmilik;
    }

    public void setHakmilik(Hakmilik hakmilik) {
        this.hakmilik = hakmilik;
    }

    public String getDaerah() {
        return daerah;
    }

    public void setDaerah(String daerah) {
        this.daerah = daerah;
    }

    public String getBandarPekanMukim() {
        return bandarPekanMukim;
    }

    public void setBandarPekanMukim(String bandarPekanMukim) {
        this.bandarPekanMukim = bandarPekanMukim;
    }

    public String getSeksyen() {
        return seksyen;
    }

    public void setSeksyen(String seksyen) {
        this.seksyen = seksyen;
    }

    public String getKodStatusHakmilik() {
        return kodStatusHakmilik;
    }

    public void setKodStatusHakmilik(String kodStatusHakmilik) {
        this.kodStatusHakmilik = kodStatusHakmilik;
    }

    public String getKodHakmilik() {
        return kodHakmilik;
    }

    public void setKodHakmilik(String kodHakmilik) {
        this.kodHakmilik = kodHakmilik;
    }

    public String getNoHakmilik() {
        return noHakmilik;
    }

    public void setNoHakmilik(String noHakmilik) {
        this.noHakmilik = noHakmilik;
    }

    public String getLot() {
        return lot;
    }

    public void setLot(String lot) {
        this.lot = lot;
    }

    public String getNoLot() {
        return noLot;
    }

    public void setNoLot(String noLot) {
        this.noLot = noLot;
    }

    public String getNamaPembayar() {
        return namaPembayar;
    }

    public void setNamaPembayar(String namaPembayar) {
        this.namaPembayar = namaPembayar;
    }

    public String getNamaPemilik() {
        return namaPemilik;
    }

    public void setNamaPemilik(String namaPemilik) {
        this.namaPemilik = namaPemilik;
    }

    public String getKategoriTanah() {
        return kategoriTanah;
    }

    public void setKategoriTanah(String kategoriTanah) {
        this.kategoriTanah = kategoriTanah;
    }

    public String getAmaunDari() {
        return amaunDari;
    }

    public void setAmaunDari(String amaunDari) {
        this.amaunDari = amaunDari;
    }

    public String getAmaunHingga() {
        return amaunHingga;
    }

    public void setAmaunHingga(String amaunHingga) {
        this.amaunHingga = amaunHingga;
    }

    public String getDariTahun() {
        return dariTahun;
    }

    public void setDariTahun(String dariTahun) {
        this.dariTahun = dariTahun;
    }

    public String getHinggaTahun() {
        return hinggaTahun;
    }

    public void setHinggaTahun(String hinggaTahun) {
        this.hinggaTahun = hinggaTahun;
    }

    public List<KodBandarPekanMukim> getSenaraiBPM() {
        return senaraiBPM;
    }

    public void setSenaraiBPM(List<KodBandarPekanMukim> senaraiBPM) {
        this.senaraiBPM = senaraiBPM;
    }

    public List<KodSeksyen> getSenaraiKodSeksyen() {
        return senaraiKodSeksyen;
    }

    public void setSenaraiKodSeksyen(List<KodSeksyen> senaraiKodSeksyen) {
        this.senaraiKodSeksyen = senaraiKodSeksyen;
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public Pengguna getPengguna() {
        return pengguna;
    }

    public void setPengguna(Pengguna pengguna) {
        this.pengguna = pengguna;
    }

    public List<Akaun> getListAkaun() {
        return listAkaun;
    }

    public void setLisAkaun(List<Akaun> listAkaun) {
        this.listAkaun = listAkaun;
    }

    public String getCurrYear() {
        return currYear;
    }

    public void setCurrYear(String currYear) {
        this.currYear = currYear;
    }

    public List<Akaun> getListAkaunTemp() {
        return listAkaunTemp;
    }

    public void setListAkaunTemp(List<Akaun> listAkaunTemp) {
        this.listAkaunTemp = listAkaunTemp;
    }

    public String getIdTagKumpulan() {
        return idTagKumpulan;
    }

    public void setIdTagKumpulan(String idTagKumpulan) {
        this.idTagKumpulan = idTagKumpulan;
    }

    public TagAkaunAhli getTagAkaunAhli() {
        return tagAkaunAhli;
    }

    public void setTagAkaunAhli(TagAkaunAhli tagAkaunAhli) {
        KumpulanAkaunActionBean.tagAkaunAhli = tagAkaunAhli;
    } 

    public List<TagAkaunAhli> getSenaraiKumpulanAhli() {
        return senaraiKumpulanAhli;
    }

    public void setSenaraiKumpulanAhli(List<TagAkaunAhli> senaraiKumpulanAhli) {
        this.senaraiKumpulanAhli = senaraiKumpulanAhli;
    }

    public String getFlagKumpulan() {
        return flagKumpulan;
    }

    public void setFlagKumpulan(String flagKumpulan) {
        this.flagKumpulan = flagKumpulan;
    }

    public KumpulanAkaun getKumpulanAkaun() {
        return kumpulanAkaun;
    }

    public void setKumpulanAkaun(KumpulanAkaun kumpulanAkaun) {
        this.kumpulanAkaun = kumpulanAkaun;
    }

//    public int getDari() {
//        return dari;
//    }
//
//    public void setDari(int dari) {
//        this.dari = dari;
//    }
//
//    public int getTotal() {
//        return total;
//    }
//
//    public void setTotal(int total) {
//        this.total = total;
//    }

    public String getStrAkaun() {
        return strAkaun;
    }

    public void setStrAkaun(String strAkaun) {
        this.strAkaun = strAkaun;
    }

    public List<Akaun> getSenaraiAkaun() {
        return senaraiAkaun;
    }

    public void setSenaraiAkaun(List<Akaun> senaraiAkaun) {
        this.senaraiAkaun = senaraiAkaun;
    }

    public boolean isPilihSemua() {
        return pilihSemua;
    }

    public void setPilihSemua(boolean pilihSemua) {
        this.pilihSemua = pilihSemua;
    }
}
