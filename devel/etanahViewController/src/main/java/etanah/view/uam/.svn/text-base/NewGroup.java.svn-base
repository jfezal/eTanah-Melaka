/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.uam;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.dao.KodPerananDAO;
import etanah.dao.PenggunaDAO;
import etanah.ldap.LDAPManager;
import etanah.model.KodPeranan;
import etanah.model.KodUnitJabatan;
import etanah.model.Pengguna;
import etanah.model.PenggunaPeranan;
import etanah.service.uam.UamService;
import org.apache.commons.lang.StringUtils;
import etanah.view.ListUtil;
import etanah.view.etanahActionBeanContext;
import java.util.ArrayList;
import java.util.List;
import javax.naming.NamingException;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.RedirectResolution;
import net.sourceforge.stripes.action.HttpCache;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.StreamingResolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author solahuddin
 */
@HttpCache(allow = false)
@UrlBinding("/uam/new_group")
public class NewGroup extends AbleActionBean {

    private final static org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(NewGroup.class);
    private Pengguna pguna;
    private String kodPeranan;
    private String idPengguna;
    private String pKataLaluan;
    private int bilPeranan;
    private List<KodPeranan> listPeranan;
    private List<KodPeranan> listPerananB;
    private List<PenggunaPeranan> listPerananValue;
    private ArrayList<PenggunaPeranan> listPerananTambahan = new ArrayList();
    private String kodJabatan;
    private String kodUnit;
    @Inject
    private ListUtil listUtil;
    @Inject
    private UamService service;
    @Inject
    private KodPerananDAO kodPerananDAO;
    @Inject
    private PenggunaDAO penggunaDAO;
    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;
    private LDAPManager ldapManager;

    @DefaultHandler
    public Resolution showForm() {
        getContext().getRequest().setAttribute("simpan", Boolean.FALSE);
        return new JSP("uam/newGroup.jsp");
    }

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() throws Exception {
        bilPeranan = listUtil.getSenaraiKodPeranan().size();
        idPengguna = (String) getContext().getRequest().getParameter("idPengguna");
        kodJabatan = (String) getContext().getRequest().getParameter("kodJabatan");

    }

    public Resolution searchPeranan() {
        logger.info("Searching User....");
//        if (StringUtils.isEmpty(kodJabatan)) {
//            addSimpleError("Sila Pilih Kod Jabatan.");
//        }
        try {
            String ptg = "";
            pguna = service.searchingUser(idPengguna);
            if (pguna.getKodCawangan().getKod().equals("00")) {
                ptg = "Y";
            } else {
                ptg = "N";
            }
            List<KodUnitJabatan> listK = service.getKodJabUnit(kodUnit, ptg);
            List<String> kj = new ArrayList();
            for (KodUnitJabatan kodUnit : listK) {
                kj.add(kodUnit.getJabatan().getKod());
            }
            if (pguna != null) {
                kodPeranan = pguna.getPerananUtama().getKod();
                logger.debug("kodJabatan :" + kodJabatan);
                // listPeranan = listUtil.getSenaraiKodPerananByJabatan(kodJabatan);
                //listPerananB = service.getKodPerananByUnit(kj);
                listPerananB = service.getKodPerananByUnit1(kj, pguna.getIdPengguna());
                listPerananValue = service.getPenggunaPeranan(pguna.getIdPengguna());
                if (!listPerananValue.isEmpty()) {
                    logger.debug("size listPerananValue :" + listPerananValue.size());
                    for (PenggunaPeranan penggunaperanan : listPerananValue) {
                        if (!pguna.getPerananUtama().getKod().equals(penggunaperanan.getPeranan().getKod())) {
                            listPerananTambahan.add(penggunaperanan);
                        }
                    }
                }


//                for(PenggunaPeranan pp : listPerananValue){
//                    logger.debug("Pengguna Peranan Id:"+pp.getIdPenggunaPeranan());
//                }
                if (!listPerananB.isEmpty()) {
                    getContext().getRequest().setAttribute("simpan", Boolean.TRUE);
//                    getContext().getRequest().setAttribute("idPengguna", idPengguna);
//                    getContext().getRequest().setAttribute("kodJabatan", kodJabatan);
                }

            } else {
                addSimpleError("ID Pengguna / Kod Jabatan tidak wujud.");
            }

//        } catch (NamingException e) {
//            addSimpleError("Error:" + e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            addSimpleError("ID Pengguna tidak wujud");
        }


        return new ForwardResolution("/WEB-INF/jsp/uam/newGroup.jsp");
    }

    public Resolution save() throws Exception {

        pguna = service.searchingUser(idPengguna);
        listPeranan = listUtil.getSenaraiKodPerananByJabatan(kodJabatan);
        listPerananValue = service.getPenggunaPeranan(pguna.getIdPengguna());
        if (!listPerananValue.isEmpty()) {
            logger.debug("size listPerananValue :" + listPerananValue.size());
            for (PenggunaPeranan penggunaperanan : listPerananValue) {
                if (!pguna.getPerananUtama().getKod().equals(penggunaperanan.getPeranan().getKod())) {
                    listPerananTambahan.add(penggunaperanan);
                }
            }

        }
        Pengguna pgunaM = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        String[] chk = getContext().getRequest().getParameterValues("chb");

        if (chk != null) {
            logger.debug("Kod Peranan--------->" + kodPeranan);
            pguna.setPerananUtama(kodPerananDAO.findById(kodPeranan));
            penggunaDAO.saveOrUpdate(pguna);
            if (chk != null && chk.length > 0) {
                service.saveGroup(chk, pguna, pgunaM, listPerananValue);
                addSimpleMessage("Peranan telah berjaya disimpan.");
            }
        } else {

            listPerananValue = service.getPenggunaPerananForPerananTambahan(pguna.getIdPengguna());
            
            for (PenggunaPeranan pp : listPerananValue) {
                service.deleteAllPeranan(pp);
            }

            //deleteAll In LDAP
            Session s = sessionProvider.get();
            Transaction tx = s.beginTransaction();
            ldapManager = new LDAPManager();
            try {
                logger.debug("start deleting in LDAP ");
                List<String> ldapGroups = ldapManager.getGroupsByUsername(pguna.getIdPengguna());
                if (ldapGroups.size() > 0) {
                    for (String group : ldapGroups) {
                        ldapManager.removeGroupFromUser(pguna.getIdPengguna(), group);
                    }
                }
                tx.commit();

            } catch (Exception e) {
                tx.rollback();
                throw e;
            }

            //Restore Peranan Utama In LDAP
            ldapManager.assignGroupToUser(pguna.getIdPengguna(), pguna.getPerananUtama().getKumpBPEL());
            addSimpleMessage("Peranan telah berjaya dikemaskini");
        }
        //return new ForwardResolution("/WEB-INF/jsp/uam/newGroup.jsp");
        return new RedirectResolution(NewGroup.class, "searchPeranan").addParameter("idPengguna", idPengguna).addParameter("kodJabatan", kodJabatan).addParameter("kodUnit", kodUnit);

    }

    public Resolution showSelectedPeranan() {
        String[] chk = getContext().getRequest().getParameterValues("chb");
        logger.debug("start show selected Peranan ");
        String result = "";
        try {

            int size = chk.length;
            for (int i = 0; i < size; i++) {
                result += kodPerananDAO.findById(chk[i]).getNama();
                if (i != size - 1) {
                    result += ",";
                }
            }


        } catch (Exception e) {
            e.printStackTrace();
            //addSimpleError("error:" + e.getStackTrace().toString());
        }

        return new StreamingResolution("text/plain", result);
    }

//    public Resolution delete() throws Exception {
//        pguna = service.searchingUser(idPengguna);
//        listPeranan = listUtil.getSenaraiKodPerananByJabatan(kodJabatan);
//        listPerananValue = service.getPenggunaPerananForPerananTambahan(pguna.getIdPengguna());
//        //listPerananValue = service.getPenggunaPeranan(pguna.getIdPengguna());
//
//        for (PenggunaPeranan pp : listPerananValue) {
//            service.deleteAllPeranan(pp);
//        }
//
//        //deleteAll In LDAP
//        Session s = sessionProvider.get();
//        Transaction tx = s.beginTransaction();
//        ldapManager = new LDAPManager();
//        try {
//            logger.debug("start deleting in LDAP ");
//            List<String> ldapGroups = ldapManager.getGroupsByUsername(pguna.getIdPengguna());
//            if (ldapGroups.size() > 0) {
//                for (String group : ldapGroups) {
//                    ldapManager.removeGroupFromUser(pguna.getIdPengguna(), group);
//                }
//            }
//            tx.commit();
//
//        } catch (Exception e) {
//            tx.rollback();
//            throw e;
//        }
//
//        //Restore Peranan Utama In LDAP
//        ldapManager.assignGroupToUser(pguna.getIdPengguna(), pguna.getPerananUtama().getKumpBPEL());
//        addSimpleMessage("Semua Peranan Tambahan Telah Berjaya Dibatalkan");
//        return new RedirectResolution(NewGroup.class, "searchPeranan").addParameter("idPengguna", idPengguna).addParameter("kodJabatan", kodJabatan).addParameter("kodUnit", kodUnit);
//
//    }

    public List<KodPeranan> getListPeranan() {
        return listPeranan;
    }

    public void setListPeranan(List<KodPeranan> listPeranan) {
        this.listPeranan = listPeranan;
    }

    public List<PenggunaPeranan> getListPerananValue() {
        return listPerananValue;
    }

    public void setListPerananValue(List<PenggunaPeranan> listPerananValue) {
        this.listPerananValue = listPerananValue;
    }

    public ArrayList<PenggunaPeranan> getListPerananTambahan() {
        return listPerananTambahan;
    }

    public void setListPerananTambahan(ArrayList<PenggunaPeranan> listPerananTambahan) {
        this.listPerananTambahan = listPerananTambahan;
    }

    public int getBilPeranan() {
        return bilPeranan;
    }

    public void setBilPeranan(int bilPeranan) {
        this.bilPeranan = bilPeranan;
    }

    public Pengguna getPguna() {
        return pguna;
    }

    public void setPguna(Pengguna pguna) {
        this.pguna = pguna;
    }

    public String getpKataLaluan() {
        return pKataLaluan;
    }

    public void setpKataLaluan(String pKataLaluan) {
        this.pKataLaluan = pKataLaluan;
    }

    public String getIdPengguna() {
        return idPengguna;
    }

    public void setIdPengguna(String idPengguna) {
        this.idPengguna = idPengguna;
    }

    public String getKodJabatan() {
        return kodJabatan;
    }

    public void setKodJabatan(String kodJabatan) {
        this.kodJabatan = kodJabatan;
    }

    public String getKodPeranan() {
        return kodPeranan;
    }

    public void setKodPeranan(String kodPeranan) {
        this.kodPeranan = kodPeranan;
    }

    public String getKodUnit() {
        return kodUnit;
    }

    public void setKodUnit(String kodUnit) {
        this.kodUnit = kodUnit;
    }

    public KodPerananDAO getKodPerananDAO() {
        return kodPerananDAO;
    }

    public void setKodPerananDAO(KodPerananDAO kodPerananDAO) {
        this.kodPerananDAO = kodPerananDAO;
    }

    public List<KodPeranan> getListPerananB() {
        return listPerananB;
    }

    public void setListPerananB(List<KodPeranan> listPerananB) {
        this.listPerananB = listPerananB;
    }
}
