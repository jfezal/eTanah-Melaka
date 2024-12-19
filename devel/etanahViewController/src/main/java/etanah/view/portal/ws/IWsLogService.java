/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.portal.ws;

import java.util.Date;

/**
 *
 * @author mohd.faidzal
 */
public interface IWsLogService {
    Long insertLog(String jsonReq, String tarikhMasuk,String jenis, String modul);
    void updateLog(Long id, String info);
}
