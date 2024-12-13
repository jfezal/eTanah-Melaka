package etanah.report;

import java.util.Map;

public interface ReportUtilMBean {
    public boolean kill(long id);
    public long getCurrentLoad();
    public Map<Long, String> getJobs();
    public String getReportKey();
    public void setReportKey(String key);
    public String getServerURL();
    public void setServerURL(String url);
    public long getTotalReportRun();
}
