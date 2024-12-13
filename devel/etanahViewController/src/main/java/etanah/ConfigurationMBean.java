/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah;

/**
 *
 * @author loop
 */
public interface ConfigurationMBean {
    public String getVersion();
    public String getKodNegeri();
    public String getDocumentPath();
    public String getWORMPath();
    public String getPelanPath();
    public String getGISInboundPath();
    public String getGISOutboundPath();
    public String getJTeknikalInboundPath();
    public boolean isCoherenceEnabled();
    public boolean isSigningRequired();
    public String getPrintMethod();
    public String getKutipanAgensiPath();
    
    public void setVersion(String version);
    public void setKodNegeri(String kod);
    public void setDocumentPath(String path);
    public void setWORMPath(String path);
    public void setPelanPath(String path);
    public void setGISInboundPath(String path);
    public void setGISOutboundPath(String path);
    public void setJTeknikalInboundPath(String path);
    public void setCoherenceEnabled(boolean state);
    public void setSigningRequired(boolean state);
    public void setPrintMethod(String method);
    public void setKutipanAgensiPath(String path);
}
