/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.scheduler;

/**
 *
 * @author mohd.faidzal
 */
import com.jcraft.jsch.JSchException;
import etanah.sftpapp.CopyNFS;
import java.sql.Timestamp;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class IspeksJob implements Job {

	@Override
	public void execute(JobExecutionContext context)
		throws JobExecutionException {
            try {
                System.out.println("Job iSpeks is running"+new Date());
                CopyNFS copy = new CopyNFS();
                copy.copy();
            } catch (JSchException ex) {
                Logger.getLogger(IspeksJob.class.getName()).log(Level.SEVERE, null, ex);
            }
	}

}