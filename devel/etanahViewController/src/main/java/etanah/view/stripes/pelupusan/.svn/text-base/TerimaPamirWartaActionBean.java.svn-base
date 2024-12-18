package etanah.view.stripes.pelupusan;

import java.util.Date;
import able.stripes.AbleActionBean;
import com.google.inject.Inject;
import org.apache.log4j.Logger;
import etanah.model.PermohonanLaporanKawasan;
import etanah.service.PelupusanService;
import able.stripes.JSP;
import net.sourceforge.stripes.action.ActionBeanContext;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;

@UrlBinding("/pelupusan/terima_pamir_warta")
public class TerimaPamirWartaActionBean extends AbleActionBean {

	private static final Logger LOG = Logger
			.getLogger(TerimaPamirWartaActionBean.class);
	@Inject
	PelupusanService pelupusanService;

	private PermohonanLaporanKawasan permohonanLaporanKawasan;
	private String tarikhWarta;
	private String tarikhTerimaWarta;
	private String noWarta;
	private String lokasiWartaDipamirkan;
	private ActionBeanContext context;
	private String idPermohonan;

	public String getNoWarta() {
		return noWarta;
	}

	public void setNoWarta(String noWarta) {
		this.noWarta = noWarta;
	}

	public String getLokasiWartaDipamirkan() {
		return lokasiWartaDipamirkan;
	}

	public void setLokasiWartaDipamirkan(String lokasiWartaDipamirkan) {
		this.lokasiWartaDipamirkan = lokasiWartaDipamirkan;
	}

	public String getTarikhWarta() {
		return tarikhWarta;
	}

	public void setTarikhWarta(String tarikhWarta) {
		this.tarikhWarta = tarikhWarta;
	}

	public String getTarikhTerimaWarta() {
		return tarikhTerimaWarta;
	}

	public void setTarikhTerimaWarta(String tarikhTerimaWarta) {
		this.tarikhTerimaWarta = tarikhTerimaWarta;
	}

	public TerimaPamirWartaActionBean() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public ActionBeanContext getContext() {
		// TODO Auto-generated method stub
		return this.context;
	}

	@Override
	public void setContext(ActionBeanContext context) {
		this.context = context;

	}

	@DefaultHandler
	public Resolution showForm() {

		return new JSP("pelupusan/terima_pamir_warta.jsp").addParameter("tab",
				"true");

	}	 

	public Resolution retrieveData() {

		PermohonanLaporanKawasan permohonanLaporanKawasan = pelupusanService
				.findByidMohonKodRizab(idPermohonan, "PBRZ");

		this.setNoWarta(permohonanLaporanKawasan.getNoWarta());
		this.setTarikhWarta(permohonanLaporanKawasan.getTarikhWarta());
		this.setTarikhTerimaWarta(permohonanLaporanKawasan.getTarikhTerimaWarta());
		this.setLokasiWartaDipamirkan(permohonanLaporanKawasan.getCatatan());
		return new JSP("pelupusan/terima_pamir_warta.jsp").addParameter("tab",
				"true");

	}

	@Before(stages = { LifecycleStage.BindingAndValidation })
	
	public void rehydrate() {
		LOG.info("Rehydrate TerimaPamirwarta");
		// Get Permohonan Laporan Kawasan Information
		idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
		permohonanLaporanKawasan = pelupusanService.findByidMohonKodRizab(idPermohonan, "PBRZ");
                
                if (permohonanLaporanKawasan != null){
		this.setLokasiWartaDipamirkan(permohonanLaporanKawasan.getCatatan());
		this.setNoWarta(permohonanLaporanKawasan.getNoWarta());
		this.setTarikhTerimaWarta(Functions.DateToString(permohonanLaporanKawasan.getTarikhTerimaWarta(), "MM/dd/yyyy"));
		this.setTarikhWarta(Functions.DateToString(permohonanLaporanKawasan.getTarikhWarta(), "MM/dd/yyyy"));
                }
                else
                {
                this.setLokasiWartaDipamirkan("");
		this.setNoWarta("");
		this.setTarikhTerimaWarta("");
		this.setTarikhWarta("");
                }
                    
        }

	public Resolution saveData() {
		LOG.info("noWarta: " + noWarta);
		LOG.info("lokasiWartaDipamirkan: " + lokasiWartaDipamirkan);
		LOG.info("tarikhTerimaWarta: " + tarikhTerimaWarta);
		LOG.info("tarikhWarta: " + tarikhWarta);

		// Set Permohonan Laporan Kawasan Properties
		permohonanLaporanKawasan.setNoWarta(noWarta);
		try {
			permohonanLaporanKawasan.setTarikhTerimaWarta(Functions
					.StringToDate(tarikhTerimaWarta, "MM/dd/yyyy"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			permohonanLaporanKawasan.setTarikhWarta(Functions.StringToDate(
					tarikhWarta, "MM/dd/yyyy"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		permohonanLaporanKawasan.setCatatan(lokasiWartaDipamirkan);

		try {
			pelupusanService
					.simpanPermohonanLaporKawasan(permohonanLaporanKawasan);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return new JSP("pelupusan/terima_pamir_warta.jsp").addParameter("tab",
				"true");
	}

    private void setTarikhWarta(Date tarikhWarta) {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    private void setTarikhTerimaWarta(Date tarikhTerimaWarta) {
        throw new UnsupportedOperationException("Not yet implemented");
    }

}
