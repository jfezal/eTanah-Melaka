/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.etapp.ws;

import javax.xml.ws.Endpoint;

/**
 *
 * @author mohd.faidzal
 */
public class Main {
    public static void main(String[] args) {
        System.out.println("Server is running. Please dont close terminal");
       Endpoint.publish("http://192.168.0.10:9999/ws/etapp", new EtappClientServiceImpl());
    }
}
