package etanah.view.stripes.pelupusan;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.model.PermohonanKertas;
import etanah.model.PermohonanLaporanKawasan;
import etanah.service.PelupusanService;
import net.sourceforge.stripes.action.*;
import net.sourceforge.stripes.controller.LifecycleStage;
import org.apache.log4j.Logger;

@UrlBinding("/pelupusan/keputusan_mmk")
public class KeputusanMmkPRMMKActionBean extends AbleActionBean {

	private static final Logger LOG = Logger.getLogger(KeputusanMmkPRMMKActionBean.class);
	@Inject
	PelupusanService pelupusanService;

	PermohonanKertas permohonanKertas;
	PermohonanLaporanKawasan permohonanLaporanKawasan;

	Functions fn = new Functions();
	private ActionBeanContext context;

	private String noKertasMesyuarat;
	private String tarikhMesyuarat;
	private String noWartaRizab;
	private String idPermohonan;

	public String getNoKertasMesyuarat() {
		return noKertasMesyuarat;
	}

	public void setNoKertasMesyuarat(String noKertasMesyuarat) {
		this.noKertasMesyuarat = noKertasMesyuarat;
	}

	public String getTarikhMesyuarat() {
		return tarikhMesyuarat;
	}

	public void setTarikhMesyuarat(String tarikhMesyuarat) {
		this.tarikhMesyuarat = tarikhMesyuarat;
	}

	public String getNoWartaRizab() {
		return noWartaRizab;
	}

	public void setNoWartaRizab(String noWartaRizab) {
		this.noWartaRizab = noWartaRizab;
	}

	@Override
	public ActionBeanContext getContext() {
		// TODO Auto-generated method stub
		return this.context;
	}

	@Override
	public void setContext(ActionBeanContext arg0) {
		// TODO Auto-generated method stub
		this.context = arg0;
	}

	@DefaultHandler
	public Resolution showForm() {

		return new JSP("pelupusan/keputusan_mmk.jsp").addParameter("tab",
				"true");

	}

	@Before( stages ={ LifecycleStage.BindingAndValidation })
	public void rehydrate() {
		LOG.info("rehydrate");
		// Get Permohonan Kertas Information
		idPermohonan = (String) getContext().getRequest().getSession()
				.getAttribute("idPermohonan");
		permohonanKertas = pelupusanService.findKertasByKod(idPermohonan,"PRMMK");
                
                if(permohonanKertas != null){
		this.setNoKertasMesyuarat(permohonanKertas.getNomborRujukan());
		try {
			this.setTarikhMesyuarat(Functions.DisplayDate(permohonanKertas
					.getTarikhSidang(), "MM/dd/yyyy"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// Get Permohonan Laporan Kawasan Information
		permohonanLaporanKawasan = pelupusanService.findByidMohonKodRizab(idPermohonan, "PRMMK");
		this.setNoWartaRizab(permohonanLaporanKawasan.getNoPelanWarta());
                }
                else {
                    this.setNoKertasMesyuarat("");
                    this.setNoWartaRizab("");
                }
	}

	public Resolution saveData() {
		LOG.info("noKertasMesyuarat: " + noKertasMesyuarat);
		LOG.info("tarikhMesyuarat: " + tarikhMesyuarat);
		LOG.info("noWartaRizab: " + noWartaRizab);

		// Set permohonan kertas properties
		permohonanKertas.setNomborRujukan(noKertasMesyuarat);
		try {
			permohonanKertas.setTarikhSidang(Functions.StringToDate(tarikhMesyuarat,"MM/dd/yyyy"));
		} catch (Exception e) {
			e.printStackTrace();
		}

		// Set Permohonan Laporan Kawasan Properties
		permohonanLaporanKawasan.setNoPelanWarta(noWartaRizab);

		// Transaction start
		// autocommit
		try {
			pelupusanService.saveOrUpdatePermohonanKertas(permohonanKertas);
			pelupusanService
					.simpanPermohonanLaporKawasan(permohonanLaporanKawasan);
			// commit
		} catch (Exception ex) {
			ex.printStackTrace();
			// rollback
		}
		// Transaction end

		// return new
		// ForwardResolution("/WEB-INF/jsp/pelupusan/kelulusan_mmk/main_tab020.jsp");
		return new JSP("pelupusan/keputusan_mmk.jsp").addParameter("tab",
				"true");
	}
}
