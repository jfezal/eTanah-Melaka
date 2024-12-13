package etanah.workflow;

import java.util.List;

import etanah.model.Permohonan;

/**
 * Workflow stage listener to be invoked by Workflow engine at certain cycles.
 * @author hishammk
 *
 */

public interface StageListener {
	
	/**
	 * Invoked AFTER the stage is created
	 * @param permohonan
	 * @param messages
	 */
	public boolean beforeStart(StageContext context);

        /**
	 * Invoked BEFORE reports have been generated for the current Stage.
         * To insure all data is enough before generateReports
         * @return true if all data is enough, false if not
	 * @param context
	 */
        public boolean beforeGenerateReports(StageContext ctx);

	/**
	 * Invoked after reports have been generated for the current Stage.
	 * @param context
	 */
	public void onGenerateReports(StageContext context);
	
	/**
	 * Invoked BEFORE the stage is set to completed. Validations can be done here.
	 * @param proposed outcome as entered by user
	 * @return the finalized outcome, if NULL being returned, the Stage is stopped from
	 * 			being completed
	 */
	public String beforeComplete(StageContext context, String proposedOutcome);
	
	
	/**
	 * Invoked AFTER the stage has set to be completed. Post-processes can be done here.
	 * @param context
	 */
	public void afterComplete(StageContext context);

        public String beforePushBack(StageContext context,String proposedOutcome);

        public void afterPushBack(StageContext context);

}
