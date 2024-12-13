package etanah.service;

import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.google.inject.Inject;
import com.wideplay.warp.persist.Transactional;

import etanah.dao.NotifikasiDAO;
import etanah.model.KodCawangan;
import etanah.model.KodPeranan;
import etanah.model.Notifikasi;
import etanah.model.Pengguna;
import etanah.model.PenggunaPeranan;
import java.util.ArrayList;
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.type.Type;

public class NotifikasiService {
	
	@Inject
	com.google.inject.Provider<Session> sessionProvider;
	
	@Inject
	NotifikasiDAO notifikasiDAO;
	
	private static final Logger LOG = Logger.getLogger(NotifikasiService.class);
	private static final boolean debug = LOG.isDebugEnabled();
	
	/**
	 * How many days the message will expire.
	 */
	public static int EXPIRY_DAYS = 100;
	
	private static final long MILLIS_PER_DAY = 24 * 60 * 60 * 1000;
	
	public List<Notifikasi> findNotifikasiForPengguna(Pengguna pengguna){
		Date now = new Date();
		now.setTime(now.getTime()- (EXPIRY_DAYS * MILLIS_PER_DAY));
		
		Session s = sessionProvider.get();
		return s.getNamedQuery("findActiveMesejForPengguna")
				.setDate("expiryDate", now)
				.setString("idPengguna", pengguna.getIdPengguna())
				.setString("kodCawangan", pengguna.getKodCawangan().getKod())
				.list();

	}

	public long findTotalNotifikasiForPengguna(Pengguna pengguna){
		if (pengguna == null) return 0;
		
		Date now = new Date();
		now.setTime(now.getTime()- (EXPIRY_DAYS * MILLIS_PER_DAY));
		
		Session s = sessionProvider.get();
		return (Long) s.getNamedQuery("findTotalUnreadMesejForPengguna")
//				.setDate("expiryDate", now)
				.setString("idPengguna", pengguna.getIdPengguna())
				.setString("kodCawangan", pengguna.getKodCawangan().getKod())
				.uniqueResult();
	}
	
	/**
	 * Add all pengguna in given peranan at the given cawangan.
	 * @param notifikasi
	 * @param cawangan
	 * @param senaraiPeranan
	 */
	@Transactional
	public void addRoleToNotifikasi(Notifikasi notifikasi, KodCawangan cawangan,
			KodPeranan peranan){
		if (cawangan == null || notifikasi == null){
			return;
		}
		
		for (PenggunaPeranan pp : peranan.getSenaraiPengguna()){
			Pengguna pg = pp.getPengguna();
			if (debug) LOG.debug("creating Notifikasi for " + pg.getIdPengguna());
			// copy notification to a new object
			Notifikasi n = new Notifikasi();
			n.setCawangan(notifikasi.getCawangan());
			n.setTajuk(notifikasi.getTajuk());
			n.setMesej(notifikasi.getMesej());
			n.setInfoAudit(notifikasi.getInfoAudit());
			n.setPengguna(pg);
			notifikasiDAO.save(n);
		}
	}

	
	/**
	 * Add all pengguna in given peranan at the given cawangan.
	 * @param notifikasi
	 * @param cawangan
	 * @param senaraiPeranan
	 */
	public void addRolesToNotifikasi(Notifikasi notifikasi, KodCawangan cawangan,
			List<KodPeranan> senaraiPeranan){
		if (cawangan == null || senaraiPeranan == null || senaraiPeranan.size() == 0){
			return;
		}
		
		HashMap<String, Pengguna> listPengguna = new HashMap<String, Pengguna>();
		for (KodPeranan p: senaraiPeranan){
                    if(p == null)
                        continue;

			List<PenggunaPeranan> senaraiPengguna = p.getSenaraiPengguna();
			if (senaraiPengguna == null || senaraiPengguna.size() == 0){
				return;
			}

			for (PenggunaPeranan pp : senaraiPengguna){
                            
/****    Find pengguna by cawangan and Peranan Utama ***/                            
                             List<Pengguna> pgunaList = findByIdPgunaPerananCaw(pp.getPeranan().getKod(), cawangan.getKod());
				for(Pengguna pg: pgunaList)
                                {
				String idPengguna = pg.getIdPengguna();
				if (listPengguna.get(idPengguna) == null){
					listPengguna.put(idPengguna, pg);
				}
			}
                        }
		}
		
		Session s = sessionProvider.get();
		Transaction txn = s.beginTransaction();
		try{
			// create the Notifikasi for each pengguna
			if (listPengguna.size() > 0){
				Iterator<String> it = listPengguna.keySet().iterator();
				while (it.hasNext()){
					String idPengguna = it.next();
					if (debug) LOG.debug("creating Notifikasi for " + idPengguna);
					// copy notification to a new object
					Notifikasi n = new Notifikasi();
					n.setCawangan(notifikasi.getCawangan());
					n.setTajuk(notifikasi.getTajuk());
					n.setMesej(notifikasi.getMesej());
					n.setInfoAudit(notifikasi.getInfoAudit());
					n.setPengguna(listPengguna.get(idPengguna));
					notifikasiDAO.save(n);
				}
			}
			txn.commit();
		} catch (Exception e){
			txn.rollback();
			LOG.error(e.getMessage(), e);
		}
	}
        public Notifikasi findByIdNotifikasi(Long idMesej) {
        String query = "Select p FROM etanah.model.Notifikasi p WHERE p.idNotifikasi = :idMesej";
        Query q = sessionProvider.get().createQuery(query);
        q.setLong("idMesej", idMesej);
        return (Notifikasi) q.uniqueResult();
    }

        public List<Pengguna> findByIdPgunaPerananUtamaCaw(String pp, String caw) {
        String query = "Select p FROM etanah.model.Pengguna p WHERE p.perananUtama.kod = :pp and p.kodCawangan.kod=:caw";
        Query q = sessionProvider.get().createQuery(query);
        q.setString("pp", pp).setString("caw", caw);
         return q.list();
    }
        
        public List<Pengguna> findByIdPgunaPerananCaw(String pp, String caw) {
        String query = "Select p FROM etanah.model.Pengguna p WHERE p.kodCawangan.kod=:caw and p.status.kod = 'A' and p.idPengguna IN "
                + "(Select q.pengguna from etanah.model.PenggunaPeranan q WHERE q.peranan.kod=:pp)";
        Query q = sessionProvider.get().createQuery(query);
        q.setString("pp", pp).setString("caw", caw);
         return q.list();
    }

        public List<Notifikasi> getNotifikasiList(String tajuk, String penghantar, Date startDate, Date endDate, Pengguna pengguna ) {
        StringBuffer queryStr=new StringBuffer();
        List paramList=new ArrayList();
	List typeList=new ArrayList();
        Date now = new Date();
        LOG.info("------------------"+startDate+"-----------------");
        LOG.info("------------------"+endDate+"-----------------");
        LOG.info("------------------"+tajuk+"-----------------");
        LOG.info("------------------"+penghantar+"-----------------");


        now.setTime(now.getTime()- (EXPIRY_DAYS * MILLIS_PER_DAY));
        queryStr.append( "Select p FROM etanah.model.Notifikasi p WHERE  p.infoAudit.tarikhMasuk > :expiryDate and p.pengguna.id = :idPengguna and p.cawangan.id = :kodCawangan ");
        			if(startDate!=null  ){
                                        queryStr.append(" and p.infoAudit.tarikhMasuk >= :startDate ");
				}
				if(endDate!=null ){
                                        queryStr.append(" and  p.infoAudit.tarikhMasuk <= :endDate ");
				}
                                if(tajuk != null && !tajuk.equals("")){
                                    queryStr.append(" and upper(p.tajuk) like '%"+tajuk.toUpperCase()+"%'");
                                 }
                                if(penghantar != null && !penghantar.equals("")){
                                    queryStr.append(" and p.pengguna.id in( select idPengguna from Pengguna s where upper(s.nama) like '%"+penghantar.toUpperCase()+"%') ");
                                }
        queryStr.append(" order by p.infoAudit.tarikhMasuk desc  ");
        Query q = sessionProvider.get().createQuery(queryStr.toString());
        q.setDate("expiryDate", now).setString("idPengguna", pengguna.getIdPengguna()).setString("kodCawangan", pengguna.getKodCawangan().getKod());
        if(startDate != null){
        q.setDate("startDate", startDate);
        }
        if(endDate != null){
        q.setDate("endDate", endDate);
        }

        q.setParameters(paramList.toArray(),(Type[])typeList.toArray(new Type[]{}));
        LOG.info("------------------"+q.getQueryString()+"-----------------");

        return q.list();
    }

 @Transactional
     public void deleteAll(Notifikasi notifikasi) {
        notifikasiDAO.delete(notifikasi);
     }
 
 @Transactional
 public void save(Notifikasi notifikasi) {
     notifikasiDAO.save(notifikasi);
 }
 
 @Transactional
 public void saveMultiple(List<Notifikasi> list) {
     for (Notifikasi notifikasi : list) {
         notifikasiDAO.save(notifikasi);
     }     
 }

}
