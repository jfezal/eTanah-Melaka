/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.stripes.ispeks;

import etanah.model.ispek.StatusInfoIspeks;

/**
 *
 * @author mohd.faidzal
 */
public class StatusInfoIspeksForm {

    StatusInfoIspeks statusInfo;
    String peringkat;
    String ppResend;

    public String getPpResend() {
        return ppResend;
    }

    public void setPpResend(String ppResend) {
        this.ppResend = ppResend;
    }

    public StatusInfoIspeks getStatusInfo() {
        return statusInfo;
    }

    public void setStatusInfo(StatusInfoIspeks statusInfo) {
        this.statusInfo = statusInfo;
    }

    public String getPeringkat() {
        return peringkat;
    }

    public void setPeringkat(String peringkat) {
        this.peringkat = peringkat;
    }

}
