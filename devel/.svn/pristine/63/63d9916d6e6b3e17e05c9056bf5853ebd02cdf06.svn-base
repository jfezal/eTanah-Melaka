/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

//added newly

package etanah.view.strata;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import etanah.view.daftar.kaunter.PermohonanKaunter;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import oracle.bpel.services.workflow.WorkflowException;

//added newly



import java.util.List;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.RedirectResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.validation.Validate;
import com.google.inject.Inject;
import etanah.dao.PermohonanDAO;
import etanah.model.HakmilikPermohonan;
import etanah.model.KodKeputusan;
import etanah.model.KodUrusan;
import etanah.model.Permohonan;
import able.stripes.AbleActionBean;
import etanah.model.Pengguna;
import etanah.view.etanahActionBeanContext;
import etanah.view.stripes.LinkActionBean;
import etanah.view.workflow.KernelActionBean;
import etanah.workflow.WorkFlowService;
import java.util.ArrayList;
import javax.servlet.http.HttpSession;
import oracle.bpel.services.workflow.WorkflowException;
import oracle.bpel.services.workflow.task.model.Task;
import oracle.bpel.services.workflow.verification.IWorkflowContext;


/**
 *
 * @author Murali
 */
@UrlBinding("/utiliti/status_permohonan")
//public class StatusPermohonanActionBean extends AbleActionBean {
public class StatusPermohonanActionBean extends PermohonanKaunter {

    //added newly

    private Permohonan permohonan;
    @Inject
    private PermohonanDAO permohonanDAO;
    private List taskList;
    private String size = "";
    public List listValue = new ArrayList();


	public void setPermohonan(Permohonan p){
		this.permohonan = p;
	}

	public Permohonan getPermohonan(){
		return permohonan;
	}

    @DefaultHandler
    public Resolution showForm() {

        return new JSP("utiliti/status_permohonan.jsp");
    }

    //added newly

   public Resolution checkPermohonan() throws WorkflowException{
		if (permohonan == null){
			addSimpleError("Sila masukkan ID Permohonan/Perserahan");
			return new ForwardResolution("/WEB-INF/jsp/kaunter/main.jsp");
		}
		String idPermohonan = permohonan.getIdPermohonan();
		if (idPermohonan == null){
			addSimpleError("Sila masukkan ID Permohonan/Perserahan");
			return new ForwardResolution("/WEB-INF/jsp/kaunter/main.jsp");
		}

		permohonan = permohonanDAO.findById(idPermohonan);
		if (permohonan == null){
			System.out.print("Permohonan " + idPermohonan + " tidak dijumpai.");
			addSimpleError("Permohonan " + idPermohonan + " tidak dijumpai.");
			return new ForwardResolution("/WEB-INF/jsp/kaunter/main.jsp");
		}

		// check if waiting for public's action
		// TODO: need to check if SPOC own this task
		 if (semakBayaran(permohonan.getIdPermohonan())) {
            getContext().getRequest().setAttribute("flag", Boolean.TRUE);
            Task impl = (Task) taskList.get(0);
            System.out.print("TASK ID: "+ impl.getSystemAttributes().getTaskId());
                       return new RedirectResolution(LinkActionBean.class).addParameter("taskId", impl.getSystemAttributes().getTaskId()).addParameter("idPermohonan", permohonan.getIdPermohonan());
        } else {
		KodKeputusan kpsn = permohonan.getKeputusan();
		if (kpsn == null){

			addSimpleMessage("Urusan ini sedang diproses");
			return new ForwardResolution("/WEB-INF/jsp/kaunter/permohonan_status.jsp");

		} else if ("D".equals(kpsn.getKod()) || "L".equals(kpsn.getKod())){

			KodUrusan urusanSlps = permohonan.getKodUrusan().getKodUrusanSelepas();
			if (urusanSlps == null){
				return new ForwardResolution("/WEB-INF/jsp/kaunter/permohonan_status.jsp");
			} else{
				addSimpleMessage("Urusan ini telah selesai. Urusan yang dicadangkan berikutnya adalah " +
						"seperti dibawah.");
				RedirectResolution r = new RedirectResolution(PermohonanKaunter.class, "Step6b");
				r.addParameter("senaraiUrusan[0].kodUrusan", urusanSlps.getKod());
				r.addParameter("senaraiUrusan[0].idPermohonanSebelum", idPermohonan);
				// adding senarai hakmilik
				List<HakmilikPermohonan> listHp = permohonan.getSenaraiHakmilik();
				for (int i = 0; i < listHp.size(); i++){
					HakmilikPermohonan hp = listHp.get(i);
					r.addParameter("hakmilikPermohonan[" + i + "].hakmilik.idHakmilik",
							hp.getHakmilik().getIdHakmilik());
				}
				return r;
			}

		} else if ("T".equals(kpsn.getKod())){

			KodUrusan urusanRayuan = permohonan.getKodUrusan().getKodUrusanRayuan();
			if (urusanRayuan == null){
				addSimpleMessage("Urusan telah ditolak");
				return new ForwardResolution("/WEB-INF/jsp/kaunter/permohonan_status.jsp");
			} else{
				addSimpleMessage("Urusan telah ditolak. Untuk pemohonan semula atau rayuan, " +
						"sila pilih urusan dibawah.");
				RedirectResolution r = new RedirectResolution(PermohonanKaunter.class, "Step6b")
					.addParameter("tambahUrusan", "Y")
					.addParameter("senaraiUrusan[0].kodUrusan", urusanRayuan.getKod())
					.addParameter("senaraiUrusan[0].kodUrusanPilih", urusanRayuan.getKod())
					.addParameter("senaraiUrusan[0].idPermohonanSebelum", idPermohonan)
					.addParameter("senaraiUrusan[0].kodJabatan", urusanRayuan.getJabatan().getKod());
				// adding senarai hakmilik
				List<HakmilikPermohonan> listHp = permohonan.getSenaraiHakmilik();
				for (int i = 0; i < listHp.size(); i++){
					HakmilikPermohonan hp = listHp.get(i);
					r.addParameter("hakmilikPermohonan[" + i + "].hakmilik.idHakmilik",
							hp.getHakmilik().getIdHakmilik());
				}
				return r;

			}

		} else if ("G".equals(kpsn)) { // Gantung

			// TODO: check if task own by SPOC
			addSimpleMessage("Urusan ini telah digantung. Sila hubungi Unit " +
					permohonan.getKodUrusan().getJabatanNama());
			return new ForwardResolution("/WEB-INF/jsp/kaunter/permohonan_status.jsp");

		} else {

			return null;

		}

	}}
    public boolean semakBayaran(String idPermohonan) throws WorkflowException {
        boolean semak = false;
        System.out.println("-----idPermohonan----------" + idPermohonan);
        HttpSession ses = getContext().getRequest().getSession();
        Pengguna pengguna = (Pengguna) ses.getAttribute(etanahActionBeanContext.KEY_USER);
        IWorkflowContext ctx = WorkFlowService.authenticate(pengguna.getIdPengguna());

        if (idPermohonan != null) {
            taskList = WorkFlowService.queryTasksByIdMohon(ctx, idPermohonan);
            size = taskList.size() + " Tugasan";
            System.out.println("Size----------" + size);
            ses.setAttribute("size", size);
            listValue = new ArrayList();
            System.out.println("-----list----------" + listValue);
            System.out.println("-----list----------" + listValue.size());
            if (taskList.size() > 0) {
                semak = true;
            }
        }
        return semak;
    }

}
