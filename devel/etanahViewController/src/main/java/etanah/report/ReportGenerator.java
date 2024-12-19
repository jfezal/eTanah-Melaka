package etanah.report;

import etanah.model.Pengguna;

public interface ReportGenerator {
	
	/**
	 * 
	 * @param reportName
	 * @param params
	 * @param values
	 * @param outputPath
	 */
	public byte[] generateReport(String reportName, String [] params, String [] values,
			String outputPath, Pengguna pengguna);

}
