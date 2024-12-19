/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.theta.portal.stripes.form.aduan;

import com.theta.portal.stripes.form.DMSUploadForm;
import java.util.List;

/**
 *
 * @author mohd.faidzal
 */
public class AgihanForm {
     List<DMSUploadForm> listOfDocument;
     
     String keterangan;
     String tarikh;

    public List<DMSUploadForm> getListOfDocument() {
        return listOfDocument;
    }

    public void setListOfDocument(List<DMSUploadForm> listOfDocument) {
        this.listOfDocument = listOfDocument;
    }

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }

    public String getTarikh() {
        return tarikh;
    }

    public void setTarikh(String tarikh) {
        this.tarikh = tarikh;
    }
     
     
}
