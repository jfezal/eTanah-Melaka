/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.ws;

import etanah.client.bayar.BayaranOnline;
import etanah.client.bayar.BayaranOnlinePortType;
import etanah.client.wslog.WsLog;
import etanah.client.wslog.WsLogPortType;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author mohd.faidzal
 */
public class LogService {
    public Long insertLog(String jsonReq, String jenis, String modul) throws MalformedURLException{
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss aa");
    URLClient u = new URLClient();
        URL url = new URL(u.wslog);
        WsLog service = new WsLog(url);
        WsLogPortType conn = service.getWsLogHttpPort();
        String tarikhMasuk = sdf.format(new Date());
        Long a = conn.insertLog(jsonReq, tarikhMasuk, jenis, modul);
        return a;
    }
}
