/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.theta.client.service;

import etanah.view.portal.service.ws.ArrayOfSenaraiBandarPekanMukim;
import etanah.view.portal.service.ws.SenaraiBandarPekanMukim;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import localhost._8080.perkhidmatanonlinews.PerkhidmatanAtasTalian;
import localhost._8080.perkhidmatanonlinews.PerkhidmatanAtasTalianPortType;
import net.theta.client.form.ListKodForm;

/**
 *
 * @author mohd.faidzal
 */
public class ClientUtilitiService {
    public List<ListKodForm> senaraiKodBandarPekanMukim(String kodDaerah,URL url){
    List<ListKodForm> list = new ArrayList<ListKodForm>();
     PerkhidmatanAtasTalian service = new PerkhidmatanAtasTalian(url);
        PerkhidmatanAtasTalianPortType port = service.getPerkhidmatanAtasTalianHttpPort();
//        ArrayOfSenaraiBandarPekanMukim a = port.listBandarPekanMukim(kodDaerah);
        
//        for (Iterator<SenaraiBandarPekanMukim> it = a.getSenaraiBandarPekanMukim().iterator(); it.hasNext();) {
//            SenaraiBandarPekanMukim c = it.next();
////            c.ge
//           
//        }
         return list;
    }
}
