/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.ws.strata;

import com.etanah.portal.carian.CarianInfoView;
import etanah.client.cukai.strata.ArrayOfCukaiStrataForm;
import etanah.client.cukai.strata.CukaiPetakOnline;
import etanah.client.cukai.strata.CukaiPetakOnlinePortType;
import etanah.client.cukai.strata.CukaiStrataForm;
import etanah.ws.URLClient;
import etanah.ws.account.WebService;
import etanah.ws.agent.AccountInfoStrataAgent;
import etanah.ws.fault.AccountInfoFaultException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author mohd.faidzal
 */
public class AccountStrataService extends WebService {

    public List<AccountInfoStrataAgent> getAccountDetails(String idHakmilik) throws AccountInfoFaultException, MalformedURLException {
        URLClient u = new URLClient();
        URL url = new URL(u.cukaipetak);
        CukaiPetakOnline service = new CukaiPetakOnline(url);
        CukaiPetakOnlinePortType port = service.getCukaiPetakOnlineHttpPort();
        ArrayOfCukaiStrataForm array = port.semakCukaiStrataIdHakmilik(idHakmilik);
        return setInfoStrata(array);
    }

    public List<AccountInfoStrataAgent> checkAccountStrataByParam(String daerah, String bpm, String seksyen, String kodJenishakmilik, String noHakmilik, String noBangunan, String noTingkat, String noPetak) throws AccountInfoFaultException, MalformedURLException {
        URLClient u = new URLClient();
        URL url = new URL(u.cukaipetak);
        CukaiPetakOnline service = new CukaiPetakOnline(url);
        CukaiPetakOnlinePortType port = service.getCukaiPetakOnlineHttpPort();
        ArrayOfCukaiStrataForm array = port.semakCukaiStrataByParam(daerah, bpm, seksyen, kodJenishakmilik, noHakmilik, noBangunan, noTingkat, noPetak);
        return setInfoStrata(array);
    }

    public List<AccountInfoStrataAgent> checkAccountStrataByNoLot(String daerah, String bpm, String seksyen, String kodLot, Integer noLot, String noBangunan, String noTingkat, String noPetak) throws AccountInfoFaultException, MalformedURLException {
        URLClient u = new URLClient();
        URL url = new URL(u.cukaipetak);
        CukaiPetakOnline service = new CukaiPetakOnline(url);
        CukaiPetakOnlinePortType port = service.getCukaiPetakOnlineHttpPort();
        ArrayOfCukaiStrataForm array = port.semakCukaiStrataByNoLot(daerah, bpm, seksyen, kodLot, noLot, noBangunan, noTingkat, noPetak);
        return setInfoStrata(array);
    }

    List<AccountInfoStrataAgent> setInfoStrata(ArrayOfCukaiStrataForm array) throws AccountInfoFaultException {
        List<AccountInfoStrataAgent> list = new ArrayList<AccountInfoStrataAgent>();
        for (CukaiStrataForm form : array.getCukaiStrataForm()) {
            AccountInfoStrataAgent agent = new AccountInfoStrataAgent();
            agent.setAkaunBatal(form.isAkaunBatal());
            agent.setBandarpekanmukim(form.getBandarpekanmukim().getValue());
            agent.setBorang11(form.getBorang11().getValue());
            agent.setCukaiSemasa(form.getCukaiSemasa().getValue());

            agent.setDaerah(form.getDaerah().getValue());
            agent.setDendaLewatSemasa(form.getDendaLewatSemasa().getValue());
            agent.setIdHakmilik(form.getIdHakmilik().getValue());
            agent.setJenisHakmilik(form.getJenisHakmilik().getValue());
            agent.setJumlahBayaran(form.getJumlahBayaran().getValue());
            agent.setJumlahKeseluruhan(form.getJumlahKeseluruhan().getValue());
            agent.setKodCawValue(form.getKodCawValue().getValue());
            agent.setKodCukaiPetak(form.getKodCukaiPetak().getValue());
            agent.setKodDaerah(form.getKodDaerah().getValue());
            agent.setKodDendaLewat(form.getKodDendaLewat().getValue());
//            agent.setKodCaw(form.getkod);
            agent.setKodLot(form.getKodLot().getValue());
            agent.setKodTunggakanCukaiPetak(form.getKodTunggakanCukaiPetak().getValue());
            agent.setKodTunggakanDendaLewat(form.getKodTunggakanDendaLewat().getValue());
            agent.setKodborang11(form.getKodborang11().getValue());
            agent.setLuasAksesori(form.getLuasAksesori().getValue());
            agent.setLuasPetak(form.getLuasPetak().getValue());
            agent.setNamaPemilik(form.getNamaPemilik().getValue());
            agent.setNoBangunan(form.getNoBangunan().getValue());
            agent.setNoHakmilik(form.getNoHakmilik().getValue());
            agent.setNoLotPt(form.getNoLotPt().getValue());
            agent.setNoPetak(form.getNoPetak().getValue());
            agent.setNoResit(form.getNoResit().getValue());
            agent.setNoTingkat(form.getNoTingkat().getValue());
            agent.setSeksyen(form.getSeksyen().getValue());
            agent.setStatusAkaun(form.getStatusAkaun().getValue());
            agent.setStatusBayaran(form.getStatusBayaran().getValue());
            agent.setTarikhBayaran(form.getTarikhBayaran().getValue());
            agent.setTunggakanCukai(form.getTunggakanCukai().getValue());
            agent.setTunggakanDendaLewat(form.getTunggakanDendaLewat().getValue());

            list.add(agent);

        }
        if (list.isEmpty()) {
            throwEmptyFaultException("getAccountDetails");
        }
        return list;
    }
}
