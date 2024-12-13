/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.stripes.pembangunan.pelan;

import java.math.BigDecimal;
import net.sourceforge.stripes.action.FileBean;

/**
 *
 * @author john
 */
public class SenaraiPlotPT {
Long idPt;
String noPlot;
    Long noPT;
    BigDecimal luas;
    String unitLuas;
    String kodUnitLuas;
    String pathPelan;
    String noFailUkur;
    String noLot;
    FileBean fileBean;
    String jenisPelan;
    String noPelanAkui;
    String syaratNyata;
    String sekatan;
    String bumi;

    public String getNoPlot() {
        return noPlot;
    }

    public void setNoPlot(String noPlot) {
        this.noPlot = noPlot;
    }

    
    public FileBean getFileBean() {
        return fileBean;
    }

    public void setFileBean(FileBean fileBean) {
        this.fileBean = fileBean;
    }

    public String getJenisPelan() {
        return jenisPelan;
    }

    public void setJenisPelan(String jenisPelan) {
        this.jenisPelan = jenisPelan;
    }

    public String getNoPelanAkui() {
        return noPelanAkui;
    }

    public void setNoPelanAkui(String noPelanAkui) {
        this.noPelanAkui = noPelanAkui;
    }
    
    

    public String getNoFailUkur() {
        return noFailUkur;
    }

    public void setNoFailUkur(String noFailUkur) {
        this.noFailUkur = noFailUkur;
    }

    public String getNoLot() {
        return noLot;
    }

    public void setNoLot(String noLot) {
        this.noLot = noLot;
    }
    
    

    public Long getNoPT() {
        return noPT;
    }

    public void setNoPT(Long noPT) {
        this.noPT = noPT;
    }

    

    public BigDecimal getLuas() {
        return luas;
    }

    public void setLuas(BigDecimal luas) {
        this.luas = luas;
    }

    

    public String getUnitLuas() {
        return unitLuas;
    }

    public void setUnitLuas(String unitLuas) {
        this.unitLuas = unitLuas;
    }

    public String getPathPelan() {
        return pathPelan;
    }

    public void setPathPelan(String pathPelan) {
        this.pathPelan = pathPelan;
    }

    public String getSyaratNyata() {
        return syaratNyata;
    }

    public void setSyaratNyata(String syaratNyata) {
        this.syaratNyata = syaratNyata;
    }

    public String getSekatan() {
        return sekatan;
    }

    public void setSekatan(String sekatan) {
        this.sekatan = sekatan;
    }

    public String getBumi() {
        return bumi;
    }

    public void setBumi(String bumi) {
        this.bumi = bumi;
    }

    public Long getIdPt() {
        return idPt;
    }

    public void setIdPt(Long idPt) {
        this.idPt = idPt;
    }

    public String getKodUnitLuas() {
        return kodUnitLuas;
    }

    public void setKodUnitLuas(String kodUnitLuas) {
        this.kodUnitLuas = kodUnitLuas;
    }

   
    
    
    
}
