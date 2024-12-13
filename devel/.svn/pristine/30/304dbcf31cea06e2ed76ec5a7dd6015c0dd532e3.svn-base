package etanah.view.stripes;

import etanah.model.Pengguna;
import java.util.ArrayList;

import etanah.model.Permohonan;
import etanah.workflow.StageContext;

public class StageContextImpl implements StageContext {

	ArrayList<String> messages = new ArrayList<String>();

	Permohonan permohonan;
	Pengguna pengguna;
	boolean byPass = Boolean.FALSE;
	String stageName;
	
	private String noRujukan;

	@Override
	public void addMessage(String msg) {
		messages.add(msg);
	}

	@Override
	public String getInstanceId() {
		throw new UnsupportedOperationException("yet ...");
	}

	@Override
	public Permohonan getPermohonan() {
		return permohonan;
	}

	@Override
	public String getStageName() {
		return stageName;
	}

	@Override
	public String getWorkflowId() {
		throw new UnsupportedOperationException("yet ...");
	}

	@Override
	public Pengguna getPengguna() {
		return pengguna;
	}

	@Override
	public String getCurrentOutcome() {
		if (permohonan != null)
			return permohonan.getKeputusan().getKod();

		return null;
	}

	@Override
	public boolean isByPass() {
		return byPass;
	}
	
	public String getNoRujukan(){
		return noRujukan;
	}

	public void setNoRujukan(String noRujukan){
		this.noRujukan = noRujukan;
	}
}
