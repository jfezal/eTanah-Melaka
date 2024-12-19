/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.theta.portal.stripes.util;

import com.google.inject.Inject;
import com.theta.portal.manager.UserManager;
import com.theta.portal.stripes.BaseActionBean;
import net.sourceforge.stripes.action.UrlBinding;

/**
 *
 * @author wan.fairul
 */
@UrlBinding("/auto_complete")
public class AjaxUtil extends BaseActionBean {

    @Inject
    UserManager um;

    

//    public Resolution getPengguna() {
//
//        String kod = getContext().getRequest().getParameter("kod");
//
////        PtPengguna pt = um.findKakitangan(kod);
//
//        JSONObject obj = new JSONObject();
//        if (pt != null) {
//            obj = new JSONObject();
//            obj.put("idPengguna", pt.getIdPengguna());
//            obj.put("emel", pt.getEmel());
//            if (pt.getBilLogin() == null) {
//                obj.put("login", "0");
//            } else {
//                obj.put("login", "1");
//            }
//        }
//
//        return new StreamingResolution("application/json", obj.toString());
//    }

   
}
