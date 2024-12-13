/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package etanah.view.stripes.hasil;

import com.google.inject.Inject;
import com.wideplay.warp.persist.Transactional;
import etanah.dao.SenaraiRujukanDAO;
import etanah.model.SenaraiRujukan;
import java.util.List;
import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author abu.mansur
 */
public class KodSekolahBantuanService {
    private static final Logger LOG = Logger.getLogger(KodSekolahBantuanService.class);
    @Inject
    SenaraiRujukanDAO senaraiRujukanDAO;
    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;

    @Transactional
    public void deleteSenaraiRujukan(SenaraiRujukan senarai) {
        try{
            senaraiRujukanDAO.delete(senarai);
        }catch(Exception ex){
            LOG.error("delete Error :"+ex);
        }
    }

    @Transactional
    public void simpanKod(SenaraiRujukan senarai){
        try{
            senaraiRujukanDAO.saveOrUpdate(senarai);
        }catch(Exception ex){
            LOG.error("simpan Ex :"+ex);
        }
    }
    
    public List<SenaraiRujukan> getKodSekolah(String kod, String nama, String kodNegeri){
        String query = "SELECT h FROM etanah.model.SenaraiRujukan h WHERE h.senarai.kod='01'"; // kodSenarai 01 = Kod Sekolah Bantuan
        if (kod != null)
           query += " AND h.kod LIKE :kod";
        if (nama != null)
           query += " AND h.nama LIKE :nama";
        if (kodNegeri != null)
           query += " AND h.alamat.negeri.kod = :kn";

        Session session = sessionProvider.get();
        Query q = session.createQuery(query);

        if (kod != null)
           q.setString("kod", "%" + kod + "%");
        if (nama != null)
           q.setString("nama", "%" + nama + "%");
        if (kodNegeri != null)
           q.setString("kn", kodNegeri);
        
        return q.list();   
    }

    public List<SenaraiRujukan> getKodDatoLembaga(String kod, String nama, String kodNegeri){
        String query = "SELECT h FROM etanah.model.SenaraiRujukan h WHERE h.senarai.kod='02'"; // kodSenarai 02 = Kod Dato' Lembaga
        if (kod != null)
           query += " AND h.kod LIKE :kod";
        if (nama != null)
           query += " AND h.nama LIKE :nama";
        if (kodNegeri != null)
           query += " AND h.alamat.negeri.kod = :kn";

        Session session = sessionProvider.get();
        Query q = session.createQuery(query);

        if (kod != null)
           q.setString("kod", "%" + kod + "%");
        if (nama != null)
           q.setString("nama", "%" + nama + "%");
        if (kodNegeri != null)
           q.setString("kn", kodNegeri);

        return q.list();
    }
}
