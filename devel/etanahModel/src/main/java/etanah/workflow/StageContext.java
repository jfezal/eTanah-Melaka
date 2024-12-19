package etanah.workflow;

import etanah.model.Pengguna;
import etanah.model.Permohonan;

public interface StageContext {
	
	public String getWorkflowId();
	
	public String getStageName();
	
	public String getInstanceId();
	
	public Permohonan getPermohonan();
	
	public void addMessage(String msg);

        public Pengguna getPengguna();

        public String getCurrentOutcome();

        public boolean isByPass();
        
        /**
         * Get the client specific No. Rujukan (being set by client) and will be saved to 
         * FasaPermohonanLog.noRujukan
         * @return
         */
        public String getNoRujukan();
        
        public void setNoRujukan(String noRujukan);
	
}
