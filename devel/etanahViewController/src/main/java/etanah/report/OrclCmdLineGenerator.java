package etanah.report;
/* author MFF
 * date 15 October 2009
 * tested in vista and win xp
 */

import com.google.inject.Inject;
import com.google.inject.Singleton;
import etanah.model.Pengguna;

/**
 ** Actual CMD command to convert Oracle .rdf file to .pdf -->
 * C:\DevSuiteHome\BIN>rwrun report=RST_004.rdf destype=file desformat=pdf
 * desname=C:\rdf\test4.pdf userid=jvetanah/friday@jvetanah id_kew_dok='0002'
 * *Make sure "rwrun" in c:\DevSuiteHome\Bin *create folder "rdf" for rwrun to
 * save .pdf
 **/

@Singleton
public class OrclCmdLineGenerator implements ReportGenerator{
	
	private String sourcePath;
	
	private String cmdLine;
	
	private String userid;
	
	@Inject
	public OrclCmdLineGenerator(etanah.Configuration conf){
		sourcePath = conf.getProperty("report.source.path");
		cmdLine = conf.getProperty("report.generator.cmd");
		userid = conf.getProperty("report.generator.userid");
	}

        @Override
	public byte[] generateReport(String reportName, String [] params, String [] values,
			String outputPath, Pengguna pengguna){
		if (params == null || values == null || params.length != values.length){
			throw new IllegalArgumentException("Check the parameters passed");
		}
		
		StringBuffer cmd = new StringBuffer(cmdLine + " " +
				"report=" + (sourcePath == null ? "" : sourcePath) + reportName + 
				" destype=file desformat=pdf" +
				" desname=" + outputPath +
				" userid=" + userid + " ");
		for (int i = 0; i <params.length; i++){
			cmd.append(params[i]).append("=").append(values[i]).append(" ");
		}
		// DEBUG
		System.out.println(cmd.toString());

		try {
			Process appCon = Runtime.getRuntime().exec(cmd.toString());
			appCon.waitFor();
			System.out.println(appCon.exitValue());
		} catch (Exception err) {
			err.printStackTrace();
		}
                return null;
	}
}
