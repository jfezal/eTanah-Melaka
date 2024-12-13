/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.theta.client;

import etanah.view.portal.service.ws.StatusPermohonan;
import localhost._8080.perkhidmatanonlinews.PerkhidmatanAtasTalian;
import localhost._8080.perkhidmatanonlinews.PerkhidmatanAtasTalianPortType;

/**
 *
 * @author mohd.faidzal
 */
public class TestClass {
    public static void main (String args[]){
        PerkhidmatanAtasTalian service = new PerkhidmatanAtasTalian();
        PerkhidmatanAtasTalianPortType port = service.getPerkhidmatanAtasTalianHttpPort();
        StatusPermohonan s =   port.findStatusPermohonan("0403DEV2018000007");
        
        System.out.print("Urusan"+s.getNamaUrusan().getValue());
    }
}
