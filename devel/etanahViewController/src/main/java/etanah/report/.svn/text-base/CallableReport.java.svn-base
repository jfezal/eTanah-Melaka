/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.report;

import etanah.model.Pengguna;
import etanah.report.ReportGenerator;
import java.util.concurrent.Callable;

/**
 * Convenient method implementing {@link Callable} to make parallel calls to Report Server.<br/>
 * Use case:<br/>
 * <p>
 * When generating multiple reports, you can run it in parallel in order to reduce user waiting time.
 * Request to report server is sent with 350ms apart of each other.
 * </p>
 * <pre>
 * Example code
 * 
 * ScheduledExecutorService executor = Executors..newScheduledThreadPool(2);
 *  ..
 *  ..
 *  ..
 * while (true) {
 *   Future<byte[]> dhke = executor.submit(new CallableReport(reportUtil, reportDHKE, params, values, dokumenPath + path, pengguna));
 *    ..
 *    ..
 *   Future<byte[]> dhde = executor.schedule(new CallableReport(reportUtil2, reportDHDE, params, values, dokumenPath + path, pengguna), 1, TimeUnit.SECONDS);
 *    ..
 *    ..
 *   try {
 *     dhke.get();
 *   } catch (Exception e) {
 *     LOG.error(e.getMessage(), e);
 *   } 
 *   try {
 *     dhde.get();
 *   } catch (Exception e) {
 *     LOG.error(e.getMessage(), e);
 *   } 
 * }
 * </pre>
 * See: {@link etanah.report.ReportUtil}
 */
public final class CallableReport implements Callable<byte[]> {

    ReportGenerator report;
    String reportName;
    String[] params;
    String[] values;
    String path;
    Pengguna pengguna;

    public CallableReport(ReportGenerator report, String reportName, String[] params, String[] values, String path, Pengguna pengguna) {
        this.report = report;
        this.reportName = reportName;
        this.params = params;
        this.values = values;
        this.path = path;
        this.pengguna = pengguna;
    }

    @Override
    public byte[] call() {
        return report.generateReport(reportName, params, values, path, pengguna);
    }
}
