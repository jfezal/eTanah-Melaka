/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.service.ispeks;

import com.google.inject.Inject;
import com.wideplay.warp.persist.Transactional;
import etanah.IspeksConfig;
import etanah.dao.IspeksBatalJurnalDAO;
import etanah.dao.IspeksCekTakLakuDAO;
import etanah.dao.IspeksResitPerbendaharaanDAO;
import etanah.dao.PenggunaDAO;
import etanah.model.InfoAudit;
import etanah.model.Pengguna;
import etanah.model.ispek.IspeksBatalJurnal;
import etanah.model.ispek.IspeksCekTakLaku;
import etanah.model.ispek.IspeksResitPerbendaharaan;
import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import org.apache.commons.lang.StringUtils;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author zuraida
 */
public class IspeksReadFileService {

    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;
    @Inject
    IspeksBatalJurnalDAO ispeksBatalJurnalDAO;
    @Inject
    IspeksCekTakLakuDAO ispeksCekTakLakuDAO;
    @Inject
    IspeksResitPerbendaharaanDAO ispeksResitPerbendaharaanDAO;
    @Inject
    PenggunaDAO penggunaDAO;
    @Inject
    IspeksConfig ispeksConfig;

    public static void main(String[] args) throws Exception {
        // pass the path to the file as a parameter 
        IspeksReadFileService r = new IspeksReadFileService();
        File file = new File("C:\\ispeks\\04_00030_RSP_20201214162217.txt");
        String aa = "";
        String kat = "r";
        InfoAudit ia = new InfoAudit();

        r.process(kat, file, ia);
    }

    public Boolean prosesIntegrationAuto(String kat, String pathFile) throws FileNotFoundException, ParseException {
        try {
            InfoAudit ia = new InfoAudit();
            ia.setTarikhMasuk(new Date());
            Pengguna pengguna = penggunaDAO.findById("portal");
            ia.setDimasukOleh(pengguna);
            File file = new File(pathFile);
            process(kat, file, ia);
            return true;
        } catch (Exception ex) {
            return false;
        }

    }

    public void process(String kat, File file, InfoAudit ia) throws FileNotFoundException, ParseException {

        List<IspeksResitPerbendaharaan> listResitContent = new ArrayList<IspeksResitPerbendaharaan>();
        List<IspeksCekTakLaku> listIspeksCekTakLaku = new ArrayList<IspeksCekTakLaku>();
        List<IspeksBatalJurnal> listBatalJurnal = new ArrayList<IspeksBatalJurnal>();
        Scanner sc = new Scanner(file);

        //print text file with delimeters
        int i = 1;
        while (sc.hasNextLine()) {
            String line = sc.nextLine();
//            int countline = line.length();
//            System.out.println("countline>>>>"+countline);
            if (i == 1) {
                //first line is header
                String[] header = line.split("\\|");
                //do someting for header
                if ("r".equals(kat)) {
                } else if ("c".equals(kat)) {

                } else if ("b".equals(kat)) {
                    IspeksBatalJurnal ispeksBatalJurnal = batalPerlarasanJurnal(header, ia);
                    listBatalJurnal.add(ispeksBatalJurnal);
                }
            } else {
                String[] arrOfStr = line.split("\\|");

                if ("r".equals(kat)) {
                    IspeksResitPerbendaharaan res = resitPerbendaharaan(arrOfStr, ia);
                    listResitContent.add(res);
                } else if ("c".equals(kat)) {
                    IspeksCekTakLaku ispeksCekTakLaku = cekTaklaku(arrOfStr, ia);
                    listIspeksCekTakLaku.add(ispeksCekTakLaku);
                } else if ("b".equals(kat)) {

                }

            }
            i++;
        }
        sc.close();
        Session s = sessionProvider.get();
        Transaction txn = s.beginTransaction();
        if ("r".equals(kat)) {
            for (IspeksResitPerbendaharaan p : listResitContent) {
                saveIspeksResitPerbendaharaan(p);
            }
        } else if ("c".equals(kat)) {
            for (IspeksCekTakLaku p : listIspeksCekTakLaku) {
                saveIspeksCekTakLaku(p);
            }
        } else if ("b".equals(kat)) {
            for (IspeksBatalJurnal p : listBatalJurnal) {
                saveIspeksBatalJurnal(p);
            }
        }
        txn.commit();

    }

    public IspeksResitPerbendaharaan resitPerbendaharaan(String[] arrOfStr, InfoAudit ia) throws ParseException {
        IspeksResitPerbendaharaan resit = new IspeksResitPerbendaharaan();
        resit.setInfoAudit(ia);
        resit.setDeptCode(arrOfStr[1].trim());
        resit.setPtjCode(arrOfStr[2].trim());
        resit.setPerbendaharaanResitNumber(arrOfStr[3].trim());
        if (StringUtils.isNotBlank(arrOfStr[4].trim())) {
            resit.setPerbendaharaanResitPostingDate(convertDate(arrOfStr[4].trim()));
        }
        resit.setCollectorStatementNum(arrOfStr[5].trim());
        if (StringUtils.isNotBlank(arrOfStr[6].trim())) {
            resit.setCollectorStatementDate(convertDate(arrOfStr[6].trim()));
        }
        if (StringUtils.isNotBlank(arrOfStr[7].trim())) {
            resit.setCollectorStateDateFrom(convertDate(arrOfStr[7].trim()));
        }
        if (StringUtils.isNotBlank(arrOfStr[8].trim())) {
            resit.setCollectorStateDateTo(convertDate(arrOfStr[8].trim()));
        }
        resit.setTreasuryAccNum(arrOfStr[9].trim());
        if (StringUtils.isNotBlank(arrOfStr[10].trim())) {
            BigDecimal a = new BigDecimal(arrOfStr[10].trim());
            a = a.divide(new BigDecimal(100));
            resit.setPerbendaharaanResitAmount(a);
        }

        return resit;
    }

    private IspeksCekTakLaku cekTaklaku(String[] arrOfStr, InfoAudit ia) throws ParseException {
        IspeksCekTakLaku ct = new IspeksCekTakLaku();
        ct.setInfoAudit(ia);
        ct.setRecordNum(arrOfStr[1].trim());
        ct.setDepartCode(arrOfStr[2].trim());
        ct.setPtjCode(arrOfStr[3].trim());
        ct.setCollectorStatementNum(arrOfStr[4].trim());
        if (StringUtils.isNotBlank(arrOfStr[5].trim())) {
            ct.setCollectorStatementDate(convertDate(arrOfStr[5].trim()));
        }
        ct.setPerbendaharaanResitNum(arrOfStr[6].trim());
        if (StringUtils.isNotBlank(arrOfStr[7].trim())) {
            ct.setResitDate(convertDate(arrOfStr[7].trim()));
        }
        ct.setBankCode(arrOfStr[8].trim());
        ct.setRefNum(arrOfStr[9].trim());
        ct.setPayerAccNum(arrOfStr[10].trim());
        if (StringUtils.isNotBlank(arrOfStr[11].trim())) {
            ct.setRefDate(convertDate(arrOfStr[11].trim()));
        }
        BigDecimal a = new BigDecimal(arrOfStr[12].trim());
        a = a.divide(new BigDecimal(100));
        ct.setRefAmaun(a.toString());
        ct.setDescription(arrOfStr[13].trim());
        ct.setVoucherNum(arrOfStr[14].trim());
        if (StringUtils.isNotBlank(arrOfStr[15].trim())) {
            ct.setVoucherDate(convertDate(arrOfStr[15].trim()));
        }
        return ct;
    }

    private IspeksBatalJurnal batalPerlarasanJurnal(String[] arrOfStr, InfoAudit ia) throws ParseException {
        IspeksBatalJurnal bj = new IspeksBatalJurnal();
        bj.setInfoAudit(ia);
        bj.setRecordType(arrOfStr[0].trim());
        bj.setVoucherNumber(arrOfStr[1].trim());
        bj.setFileName(arrOfStr[2].trim());
        bj.setValidatorName(arrOfStr[3].trim());
        bj.setVerifierPosition(arrOfStr[4].trim());
        if (StringUtils.isNotBlank(arrOfStr[5].trim())) {
            bj.setVerifyDate(convertDate(arrOfStr[5].trim()));
        }
        return bj;
    }

    private Date convertDate(String string) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("ddMMyyyy");
        return sdf.parse(string);
    }

    @Transactional
    private void saveIspeksBatalJurnal(IspeksBatalJurnal p) {
        ispeksBatalJurnalDAO.save(p);
    }

    @Transactional
    private void saveIspeksCekTakLaku(IspeksCekTakLaku p) {
        ispeksCekTakLakuDAO.save(p);
    }

    @Transactional
    private void saveIspeksResitPerbendaharaan(IspeksResitPerbendaharaan p) {
        ispeksResitPerbendaharaanDAO.saveOrUpdate(findByColStatemen(p));
    }

    private IspeksResitPerbendaharaan findByColStatemen(IspeksResitPerbendaharaan p) {
        String query = "Select p FROM etanah.model.ispek.IspeksResitPerbendaharaan p"
                + "  where p.collectorStatementNum = :collectorStatementNum ";
        Query q = sessionProvider.get().createQuery(query);
        q.setString("collectorStatementNum", p.getCollectorStatementNum());
        IspeksResitPerbendaharaan r = (IspeksResitPerbendaharaan) q.uniqueResult();
        if (r == null) {
            return p;
        } else {
            return r;
        }
    }
}
