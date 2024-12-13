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
import etanah.dao.KodCawanganDAO;
import etanah.dao.KodJawatanDAO;
import etanah.dao.KodKlasifikasiDAO;
import etanah.model.KodCawangan;
import etanah.model.KodJawatan;
import etanah.model.KodKlasifikasi;
import etanah.model.KodPeranan;
import etanah.model.KodUnitJabatan;
import etanah.model.Pengguna;
import etanah.service.uam.UamService;
import etanah.view.etanahActionBeanContext;
import etanah.workflow.WorkFlowService;
import java.util.ArrayList;
import java.util.List;
import javax.naming.NameAlreadyBoundException;
import javax.naming.NamingException;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.HttpCache;
import net.sourceforge.stripes.action.RedirectResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.StreamingResolution;
import net.sourceforge.stripes.action.UrlBinding;
import oracle.adf.share.security.model.dc.idm.exception.UserNotFoundException;
import oracle.bpel.services.workflow.task.model.Task;
import oracle.bpel.services.workflow.verification.IWorkflowContext;
import org.apache.commons.lang.StringUtils;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author solahuddin
 */
@HttpCache(allow = false)
@UrlBinding("/uam/user_update")
public class UpdateUserActionBean extends AbleActionBean {

    private final static org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(NewAndUpdateActionBean.class);
    private Pengguna pguna;
    private String pKataLaluan;
    private String idKaunter;
    @Inject
    private UamService service;
    @Inject
    private KodPerananDAO kodPerananDAO;
    @Inject
    private PenggunaDAO penggunaDAO;
    @Inject
    private KodJawatanDAO kodJawatanDAO;
    @Inject
    private KodCawanganDAO kodCawanganDAO;
    @Inject
    private KodKlasifikasiDAO kodklasifikasiDAO;
    @Inject
    private etanah.Configuration conf;
    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;
    private boolean melaka = false;
    private List<KodPeranan> listkodPeranan = new ArrayList();

    @DefaultHandler
    public Resolution showForm() {
        getContext().getRequest().setAttribute("simpan", Boolean.TRUE);
        getContext().getRequest().setAttribute("kemaskini", Boolean.FALSE);
        getContext().getRequest().setAttribute("id", Boolean.TRUE);
        getContext().getRequest().setAttribute("kataLaluan", Boolean.TRUE);
        findKodNegeri();
        return new JSP("uam/UpdateUser.jsp");
    }

    public Resolution ss() {
        findKodNegeri();
        List<KodUnitJabatan> listK = service.getKodJabUnit("1", "N");
        List<String> kj = new ArrayList();
        for (KodUnitJabatan kodUnit : listK) {
            kj.add(kodUnit.getJabatan().getKod());
        }
        List<KodPeranan> hj = service.getKodPerananByUnit(kj);
        hj.size();

        List<Pengguna> lPengguna = service.getPenggunaUnit(kj, "00");
        lPengguna.size();
        return new JSP("uam/UpdateUser.jsp");
    }

    public Resolution searchByCawangan() {
        findKodNegeri();
        String cawangan = getContext().getRequest().getParameter("pguna.kodCawangan.kod");
        String jabatan = getContext().getRequest().getParameter("pguna.kodJabatan.kod");
        logger.debug("cawangan : " + cawangan);
//        ---set balik using serchByCaw---

        String ptg = "";
        if (cawangan.equals("00")) {
            ptg = "Y";
        } else {
            ptg = "N";
        }
        List<KodUnitJabatan> listKj = service.getKodJab(jabatan, ptg);
        if (!listKj.isEmpty()) {
            List<KodUnitJabatan> listK = service.getKodJabUnit(String.valueOf(listKj.get(0).getKodUnit().getId()), ptg);
            List<String> kj = new ArrayList();
            for (KodUnitJabatan kodUnit : listK) {
                kj.add(kodUnit.getJabatan().getKod());
            }
            listkodPeranan = service.getKodPerananByUnit(kj);
        }
        getContext().getRequest().setAttribute("kemaskini", Boolean.TRUE);
        getContext().getRequest().setAttribute("simpan", Boolean.FALSE);
        getContext().getRequest().setAttribute("id", Boolean.FALSE);
        getContext().getRequest().setAttribute("kataLaluan", Boolean.FALSE);
        return new JSP("uam/UpdateUser.jsp");
    }

//    public Resolution noEdit() throws NamingException{
//       getContext().getRequest().setAttribute("papar", Boolean.TRUE);
////      pguna = service.searchingUser(pguna.getIdPengguna());
//       return new ForwardResolution("/WEB-INF/jsp/uam/newAndUpdateUser.jsp");
//    }
    public Resolution newPengguna() throws NamingException, UserNotFoundException, Exception {
        Pengguna pengguna = (Pengguna) getContext().getRequest().getSession().
                getAttribute(etanahActionBeanContext.KEY_USER);

        logger.info("Creating User....");
        findKodNegeri();

        if (pguna != null) {

            String jabatan = getContext().getRequest().getParameter("pguna.kodCawangan.kod");
            String jawatan = getContext().getRequest().getParameter("pguna.kodJawatan.kod");
            String unit = getContext().getRequest().getParameter("pguna.kodJabatan.kod");
            String peranan = getContext().getRequest().getParameter("pguna.perananUtama.kod");
            String klasifikasi = getContext().getRequest().getParameter("pguna.tahapSekuriti.kod");
            String aktif = getContext().getRequest().getParameter("pguna.status.kod");
            String penyelia = getContext().getRequest().getParameter("pguna.penyeliaFlag");
            String pelulus = getContext().getRequest().getParameter("pguna.pelulusBayaranKurang");

            if (StringUtils.isBlank(pguna.getIdPengguna())) {
                addSimpleError("Sila masukkan Id Pengguna. Sila pastikan maklumat yang bertanda merah diisi");

            } else if (StringUtils.isBlank(pguna.getPassword())) {
                addSimpleError("Sila masukkan Kata Laluan.");

            }else if (StringUtils.isBlank(pguna.getPassword())) {
                addSimpleError("Sila masukkan Kata Laluan.");

            } else if (pguna.getPassword().length() < 8) {
                addSimpleError("Had minimum Kata Laluan adalah 8. Sila betulkan Kata Laluan anda.");

            } else if (!pguna.getPassword().equals(pKataLaluan)) {
                addSimpleError("Kata laluan anda tidak sama. Sila Betulkan Kata Laluan anda.");

            } else if (StringUtils.isBlank(pguna.getNama())) {
                addSimpleError("Sila masukkan Nama.");

            } else if (StringUtils.isBlank(pguna.getNoPengenalan())) {
                addSimpleError("Sila masukkan No. Kad Pengenalan.");

            } else if (StringUtils.isBlank(jabatan)) {
                if (melaka = true) {
                    addSimpleError("Sila masukkan Jabatan");
                } else {
                    addSimpleError("Sila masukkan Cawangan");
                }

            } else if (StringUtils.isBlank(unit)) {
                if (melaka = false) {
                    addSimpleError("Sila masukkan Jabatan");
                } else {
                    addSimpleError("Sila masukkan Unit");
                }

            } else if (StringUtils.isBlank(jawatan)) {
                addSimpleError("Sila masukkan Jawatan.");

            } else if (StringUtils.isBlank(pguna.getEmail())) {
                addSimpleError("Sila masukkan Emel.");

            } else if (StringUtils.isBlank(klasifikasi)) {
                addSimpleError("Sila masukkan Kod Klasifikasi.");

            } else if (StringUtils.isBlank(peranan)) {
                addSimpleError("Sila masukkan Peranan Utama.");

            } else if (StringUtils.isBlank(aktif)) {
                addSimpleError("Sila pilih status Aktif.");

            } else if (StringUtils.isBlank(penyelia)) {
                addSimpleError("Sila pilih status Penyelia.");

            } else if (StringUtils.isBlank(pelulus)) {
                addSimpleError("Sila pilih Pelulus Bayar Kurang.");

            } else {
                try {
                    if (!service.newUser(pguna, pengguna, pguna.getPassword())) {
                        addSimpleError("Id Pengguna telah wujud. Gunakan Id Pengguna lain.");
                    } else {
                        addSimpleMessage("Pengguna baru telah disimpan.");
                    }
                } catch (NameAlreadyBoundException e) {
                    addSimpleError("Id Pengguna telah wujud. Gunakan Id Pengguna lain.");
                } catch (UserNotFoundException e) {
                    throw e;
                }
            }
        } else {
            addSimpleError("Sila isi maklumat yang berkaitan dengan pengguna.");
        }

        getContext()
                .getRequest().setAttribute("simpan", Boolean.TRUE);
        getContext()
                .getRequest().setAttribute("kemaskini", Boolean.FALSE);
        getContext()
                .getRequest().setAttribute("kataLaluan", Boolean.TRUE);

        logger.info(
                "Creating User Success....");
        return new ForwardResolution(
                "/WEB-INF/jsp/uam/UpdateUser.jsp");
    }

    public Resolution updateUser() throws Exception {
        Pengguna pengguna = (Pengguna) getContext().getRequest().getSession().
                getAttribute(etanahActionBeanContext.KEY_USER);
        IWorkflowContext ctx = WorkFlowService.authenticate(pguna.getIdPengguna());

        logger.info("Modify User....");
        findKodNegeri();

        if (pguna != null) {
            if (StringUtils.isBlank(pguna.getNama())) {
                addSimpleError("Sila masukkan Nama.");

            } else {
                service.update(pguna, pengguna, pguna.getPassword());
                service.copyFromPengguna(pguna, pengguna);
                if (pguna.getStatus().getKod().equals("X")) {

                    IWorkflowContext ctxA = WorkFlowService.authenticateAdmin();
                    List<Task> taskList = WorkFlowService.queryTasksASSIGNED(ctx, pguna.getKodCawangan().getKod());
                    // WorkFlowService.re
                    for (Task t : taskList) {
                       /// WorkFlowService.releaseTask(ctxA, t.getSystemAttributes().getTaskId());
                    }
                }
                addSimpleMessage("Kemaskini pengguna berjaya.");
            }
        } else {
            addSimpleError("Sila isi maklumat yang berkaitan dengan pengguna.");
        }

        getContext().getRequest().setAttribute("simpan", Boolean.TRUE);
        getContext().getRequest().setAttribute("kemaskini", Boolean.FALSE);
        getContext().getRequest().setAttribute("kataLaluan", Boolean.FALSE);
        getContext().getRequest().setAttribute("papar", Boolean.TRUE);

        logger.info("Modify User Success....");
        return new ForwardResolution("/WEB-INF/jsp/uam/UpdateUser.jsp");
//        return new ForwardResolution(NewAndUpdateActionBean.class,"noEdit");
    }

    public Resolution searchUser() throws Exception {
        logger.info("Searching User....");
        findKodNegeri();

        String idParam = getContext().getRequest().getParameter("idPengguna");

        if (idParam != null) {
            pguna = service.searchingUser(idParam);
        } else if (pguna != null && StringUtils.isNotBlank(pguna.getIdPengguna())) {
            pguna = service.searchingUser(pguna.getIdPengguna());

        } else {
            addSimpleError("Sila masukkan Id Pengguna.");

            getContext().getRequest().setAttribute("simpan", Boolean.TRUE);
            getContext().getRequest().setAttribute("kemaskini", Boolean.FALSE);
            getContext().getRequest().setAttribute("id", Boolean.TRUE);
            getContext().getRequest().setAttribute("kataLaluan", Boolean.TRUE);

            return new ForwardResolution("/WEB-INF/jsp/uam/UpdateUser.jsp");
        }

        if (pguna != null && service.checkUser(pguna.getIdPengguna())) {
            if (pguna.getIdPengguna() != null) {
                getContext().getRequest().setAttribute("kemaskini", Boolean.TRUE);
                getContext().getRequest().setAttribute("simpan", Boolean.FALSE);
                getContext().getRequest().setAttribute("id", Boolean.FALSE);
                getContext().getRequest().setAttribute("kataLaluan", Boolean.FALSE);
                addSimpleMessage("Carian pengguna berjaya.");
                logger.info("Searching User Success....");

                String ptg = "";
                if (pguna.getKodCawangan().getKod().equals("00")) {
                    ptg = "Y";
                } else {
                    ptg = "N";
                }
                List<KodUnitJabatan> listKj = service.getKodJab(pguna.getKodJabatan().getKod(), ptg);
                if (!listKj.isEmpty()) {
                    List<KodUnitJabatan> listK = service.getKodJabUnit(String.valueOf(listKj.get(0).getKodUnit().getId()), ptg);
                    List<String> kj = new ArrayList();
                    for (KodUnitJabatan kodUnit : listK) {
                        kj.add(kodUnit.getJabatan().getKod());
                    }
                    listkodPeranan = service.getKodPerananByUnit(kj);
                }
                getContext().getRequest().setAttribute("idP", pguna.getIdPengguna());
                return new ForwardResolution("/WEB-INF/jsp/uam/UpdateUser.jsp");

            } else {
                addSimpleError("Id Pengguna tidak wujud.");
            }
        } else {
            if (pguna != null) {
                String idPengguna = pguna.getIdPengguna();
                pguna = new Pengguna();
                pguna.setIdPengguna(idPengguna);
            }
            addSimpleError("Id Pengguna tidak wujud.");
        }
        getContext().getRequest().setAttribute("simpan", Boolean.TRUE);
        getContext().getRequest().setAttribute("kemaskini", Boolean.FALSE);
        getContext().getRequest().setAttribute("id", Boolean.TRUE);
        getContext().getRequest().setAttribute("kataLaluan", Boolean.TRUE);
        return new ForwardResolution("/WEB-INF/jsp/uam/UpdateUser.jsp");
    }
    
    public Resolution searchIdKaunter() throws Exception {
        String results = "0";
        idKaunter = getContext().getRequest().getParameter("kaunter");
        String caw = getContext().getRequest().getParameter("caw");
        logger.info("++ID KAUNTER = " + idKaunter + " ++CAW : " + caw);

        String query = "select a from etanah.model.Pengguna a where a.idKaunter=:id and a.kodCawangan.kod =:caw";
        Query q = sessionProvider.get().createQuery(query);
            q.setString("id", idKaunter);
            q.setString("caw", caw);
        List<Pengguna> senarai = q.list();
        if (senarai.size() > 0) {
            results = "1";
        }
        return new StreamingResolution("text/plain", results);
    }

    public Resolution searchInactiveUser() throws Exception {
        logger.info("Searching Inactive User....");
        findKodNegeri();

        String idParam = getContext().getRequest().getParameter("idPengguna");

        if (idParam != null) {
            pguna = service.searchingUser(idParam);
        } else {
            pguna = service.searchingUser(pguna.getIdPengguna());
        }

        if (pguna != null) {
            if (pguna.getIdPengguna() != null) {
                getContext().getRequest().setAttribute("kemaskini", Boolean.TRUE);
                getContext().getRequest().setAttribute("simpan", Boolean.FALSE);
                getContext().getRequest().setAttribute("id", Boolean.FALSE);
                getContext().getRequest().setAttribute("kataLaluan", Boolean.FALSE);
                addSimpleMessage("Carian pengguna berjaya.");
                logger.info("Searching Inactive User Success...");
                getContext().getRequest().setAttribute("idP", pguna.getIdPengguna());
                return new ForwardResolution("/WEB-INF/jsp/uam/UpdateUser.jsp");

            } else {
                addSimpleError("Id Pengguna tidak wujud.");
            }
        } else {
            String idPengguna = pguna.getIdPengguna();
            pguna = new Pengguna();
            pguna.setIdPengguna(idPengguna);

            addSimpleError("Id Pengguna tidak wujud.");
        }
        getContext().getRequest().setAttribute("simpan", Boolean.TRUE);
        getContext().getRequest().setAttribute("kemaskini", Boolean.FALSE);
        getContext().getRequest().setAttribute("id", Boolean.TRUE);
        getContext().getRequest().setAttribute("kataLaluan", Boolean.TRUE);
        return new ForwardResolution("/WEB-INF/jsp/uam/UpdateUser.jsp");
    }

    public Resolution kembali() {
        findKodNegeri();
        getContext().getRequest().setAttribute("simpan", Boolean.TRUE);
        getContext().getRequest().setAttribute("kemaskini", Boolean.FALSE);
        getContext().getRequest().setAttribute("id", Boolean.TRUE);
        getContext().getRequest().setAttribute("kataLaluan", Boolean.TRUE);
        if (conf.getProperty("kodNegeri").equals("04")) {
            melaka = true;
        } else {
            melaka = false;
        }
        return new JSP("uam/UpdateUser.jsp");
    }

    public Pengguna getPguna() {
        return pguna;
    }

    public void setPguna(Pengguna pguna) {
        this.pguna = pguna;
    }

    public String getIdKaunter() {
        return idKaunter;
    }
    
    public void setIdKaunter(String idKaunter) {
        this.idKaunter = idKaunter;
    }
    
    public String getpKataLaluan() {
        return pKataLaluan;
    }

    public void setpKataLaluan(String pKataLaluan) {
        this.pKataLaluan = pKataLaluan;
    }

    public boolean isMelaka() {
        return melaka;
    }

    public void setMelaka(boolean melaka) {
        this.melaka = melaka;
    }

    public String getNamaCaw() {
        String kcNama = "";
        KodCawangan kc = new KodCawangan();
        if (pguna != null && pguna.getKodCawangan() != null) {
            kc = kodCawanganDAO.findById(pguna.getKodCawangan().getKod());
            kcNama = kc.getName();
        }
        return kcNama;
    }

    public String getNamaKKlasifikasi() {
        String kkNama = "";
        KodKlasifikasi kk = new KodKlasifikasi();
        if (pguna != null && pguna.getTahapSekuriti() != null) {
            kk = kodklasifikasiDAO.findById(pguna.getTahapSekuriti().getKod());
            kkNama = kk.getNama();
        }
        return kkNama;
    }

    public String getNamaPeranan() {
        String kpNama = "";
        KodPeranan kp = new KodPeranan();
        if (pguna != null && pguna.getPerananUtama() != null) {
            kp = kodPerananDAO.findById(pguna.getPerananUtama().getKod());
            kpNama = kp.getNama();
        }
        return kpNama;
    }
    
    public String getJawatan() {
        String jwNama = "";
        KodJawatan jw = new KodJawatan();
        if (pguna != null && pguna.getKodJawatan() != null) {
            jw = kodJawatanDAO.findById(pguna.getKodJawatan().getKod());
            jwNama = jw.getNama();
        }
        return jwNama;
    }

    public void findKodNegeri() {
        if (conf.getProperty("kodNegeri").equals("04")) {
            melaka = true;
        } else {
            melaka = false;
        }
    }

    public List<KodPeranan> getListkodPeranan() {
        return listkodPeranan;
    }

    public void setListkodPeranan(List<KodPeranan> listkodPeranan) {
        this.listkodPeranan = listkodPeranan;
    }
}
