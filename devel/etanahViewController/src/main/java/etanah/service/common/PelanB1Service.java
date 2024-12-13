/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.service.common;

import com.google.inject.Inject;
import com.wideplay.warp.persist.Transactional;
import etanah.dao.HakmilikPermohonanB1DAO;
import etanah.model.Hakmilik;
import etanah.model.HakmilikPermohonanB1;
import etanah.model.PermohonanPlotPelan;
import etanah.view.stripes.common.b1.SenaraiHakmilikB1;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author john
 */
public class PelanB1Service {

    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;
    @Inject
    HakmilikPermohonanB1DAO hakmilikPermohonanB1DAO;

    public List findSenaraiByidPermohonan(String idPermohonan) {
        List<SenaraiHakmilikB1> senaraiHakmilikB1 = new ArrayList<SenaraiHakmilikB1>();
        String query = "Select p FROM etanah.model.HakmilikPermohonanB1 p where p.permohonan.idPermohonan =:idPermohonan ";
        Query q = sessionProvider.get().createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        for (Iterator it = q.list().iterator(); it.hasNext();) {
            HakmilikPermohonanB1 hp1 = (HakmilikPermohonanB1) it.next();
            SenaraiHakmilikB1 b1 = new SenaraiHakmilikB1();
            b1.setIdHakmilik(hp1.getHakmilik().getIdHakmilik());
            b1.setIdMhB1(hp1.getIdMhB1());
            if (hp1.getKodUnitLuas() != null) {
                b1.setKodLuas(hp1.getKodUnitLuas().getKod());

            }
            b1.setLuas(hp1.getLuasTerlibat());
            b1.setNoLot(hp1.getNoLot());
            b1.setNoPelanAkui(hp1.getNoPelanAkui());
            if (hp1.getKodUnitLuas() != null) {
                b1.setKodUnitLuas(hp1.getKodUnitLuas().getKod());
            }
            senaraiHakmilikB1.add(b1);
        }
        return senaraiHakmilikB1;
    }

    @Transactional
    public void simpanHakmilikPermohonanB1(HakmilikPermohonanB1 mhB1) {
        hakmilikPermohonanB1DAO.save(mhB1);
    }

    public PermohonanPlotPelan findPlotPelanBIdMohon(String idPermohonan) {
        String query = "Select p FROM etanah.model.PermohonanPlotPelan p where p.permohonan.idPermohonan =:idPermohonan "
                + "and p.permohonanPlotKpsn is not null";
        Query q = sessionProvider.get().createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        return (PermohonanPlotPelan) q.list().get(0);
    }

    public HakmilikPermohonanB1 findHakmilikB1ByIdHakmilik(String idHakmilikQT, String idPermohonan) {
        String query = "Select p FROM etanah.model.HakmilikPermohonanB1 p where p.hakmilik.idHakmilik =:idHakmilikQT "
                + "and p.permohonan.idPermohonan =:idPermohonan";
        Query q = sessionProvider.get().createQuery(query);
        q.setString("idHakmilikQT", idHakmilikQT);
        q.setString("idPermohonan", idPermohonan);

        return (HakmilikPermohonanB1) q.uniqueResult();
    }

    public List<Hakmilik> findListQT(String idPermohonan) {
        String query = "Select p.hakmilik FROM etanah.model.HakmilikPermohonanB1 p where p.permohonan.idPermohonan =:idPermohonan "
                + "and (p.luasTerlibat is  null "
                + "or p.noLot is  null "
                + "or p.kodUnitLuas is  null)";
        Query q = sessionProvider.get().createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        return q.list();
    }

    public Long countPelanMuatNaik(String idPermohonan) {
        String query = "Select count(p) FROM etanah.model.HakmilikPermohonanB1 p where p.permohonan.idPermohonan =:idPermohonan "
                + " and (p.luasTerlibat is not null "
                + "or p.noLot is not null "
                + "or p.kodUnitLuas is not null)";
        Query q = sessionProvider.get().createQuery(query);
        q.setString("idPermohonan", idPermohonan);

        return (Long) q.iterate().next();
    }

    public Long countRowHakmilikB1(String idPermohonan) {
        String query = "Select count(p) FROM etanah.model.HakmilikPermohonanB1 p where p.permohonan.idPermohonan =:idPermohonan ";
        Query q = sessionProvider.get().createQuery(query);
        q.setString("idPermohonan", idPermohonan);

        return (Long) q.iterate().next();
    }

    public Long countTiadaPelan(String idPermohonan) {
        String query = "Select count(p) FROM etanah.model.HakmilikPermohonanB1 p where p.permohonan.idPermohonan =:idPermohonan"
                + " and (p.luasTerlibat is  null "
                + "or p.noLot is  null "
                + "or p.kodUnitLuas is  null)";
        Query q = sessionProvider.get().createQuery(query);
        q.setString("idPermohonan", idPermohonan);

        return (Long) q.iterate().next();
    }

}
