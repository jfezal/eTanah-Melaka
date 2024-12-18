package etanah.view.lelong.dokumen;

import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import able.stripes.AbleActionBean;

@UrlBinding ("lelong/dokumen/scan/{idDokumen}")
public class ScanDocument extends AbleActionBean {
	
	private long idDokumen;
	
	public void setIdDokumen(long idDokumen){
		this.idDokumen = idDokumen;
	}
	
	public long getIdDokumen(){
		return idDokumen;
	}
	
	public Resolution show(){
                String idPermohonan = getContext().getRequest().getParameter("idPermohonan");
                if(idPermohonan != null)
                    getContext().getRequest().setAttribute("idPermohonan", idPermohonan);
		return new ForwardResolution("/WEB-INF/jsp/lelong/dokumen/imbas.jsp").addParameter("popup", "true");
	}

}
