/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.manager;

import electric.xml.Document;
import electric.xml.Element;
import electric.xml.Elements;
import electric.xml.XPath;



import etanah.util.WLUtil;
import java.io.File;
import java.io.InputStream;
import java.net.JarURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.zip.ZipEntry;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

/**
 *
 * @author md.nurfikri
 */
public class TabManager {

    private String page = "";
//    private static Document doc = null;
    static String xmlFolderPath;
    static final List<Document> xmlList;
    private static final Logger LOG = Logger.getLogger(TabManager.class);
    private static final boolean IS_LOG_DEBUG = LOG.isDebugEnabled();
    private boolean isKeputusanView = false;
    private String keputusanTitle = "Keputusan";
    private String nextStageButton = "Selesai";
    private boolean isUlasanOnly = false;

    static {
//        Properties props = new Properties();
        xmlList = new ArrayList<Document>();        

//            props.load(TabManager.class.getResourceAsStream(Configuration.CONF_FILE));
            if (!WLUtil.isRunningOnWebLogic()) {
                    URL url = TabManager.class.getResource("/etanah/xml/");
                    String path = "";
                    try {
                        path = java.net.URLDecoder.decode(url.getPath(), "UTF-8");
                    } catch (Exception ex) {
                        LOG.error("Error while setting up XML files..",ex);
                    }
                    
                    if (StringUtils.isNotBlank(path)) {
                        LOG.info("XML path = " + path);
                        File dir = new File(path);
                        LOG.info("is Directory Exist ::" + dir.exists());
                        File[] files = dir.listFiles();
        //                xmlList = new ArrayList<Document>();
                        for (File file : files) {
                            if (IS_LOG_DEBUG) {
                                LOG.debug("load xml [" + file.getName() + "]");
                            }
                            try {
                                //to make sure all xmls are loaded although there are some errors
                                Document _doc = new Document(file);
                                xmlList.add(_doc);
                            } catch (Exception ex) {
                                LOG.error("Error while setting up XML files..",ex);
                            }                            
                        }
                    }                  
                    
            } else {

                URL u = TabManager.class.getResource("/etanah/xml/");
                LOG.info("XML path = " + u.getPath());
                URL jarUrl = null;
                JarURLConnection jarUrlConnection = null;
                JarFile jarFile = null;
                
                try {
                    jarUrl = new URL("jar:file:" + u.getPath());
                    jarUrlConnection = (JarURLConnection) jarUrl.openConnection();
                    jarFile = jarUrlConnection.getJarFile();
                } catch (Exception ex) {
                    LOG.error("Error while setting up XML files..",ex);
                }
                
                if (jarFile != null) {
                    final Enumeration<JarEntry> entries = jarFile.entries();

                    while (entries.hasMoreElements()) {
                        final JarEntry entry = entries.nextElement();
                        final String entryName = entry.getName();
                        //to make sure all xmls are loaded although there are some errors occured
                        try {
                            if (entryName.startsWith("etanah/xml/") && entryName.endsWith(".xml")) {
                                if (IS_LOG_DEBUG) {
                                    LOG.debug("load xml [" + entryName + "]");
                                }
                                ZipEntry ze = new ZipEntry(entryName);
                                InputStream is = jarFile.getInputStream(ze);
                                Document _doc = new Document(is);
                                xmlList.add(_doc);
                            }
                        } catch (Exception ex) {
                            LOG.error("Error while setting up XML files..", ex);
                        }
                    }
                }             
            }

            }

    public TabManager() {
    }

    public String getPage() {
        return this.page;
    }

    public TabBean getTabFlow(String txnCode, String stageId, String idWorkflow) {
        LOG.info("txnCode :" + txnCode);
        LOG.info("tabManager.stageId :" + stageId);
        LOG.info("tabManager.idWorkflow :" + idWorkflow);
        TabBean bean = null;
        try {

            for (Document document : xmlList) {
                Elements workflow = document.getElements(new XPath("/workflow[@id='" + idWorkflow + "']"));
                while (workflow.hasMoreElements()) {
                    Element _stage = workflow.next();
                    Elements stage =
                            _stage.getElements(new XPath("stage[@id='" + stageId + "']"));
                    while (stage.hasMoreElements()) {
                        Element elem = stage.next();
                        String nextStageButtonTitle = elem.getAttributeValue("nextStageButton");
                        Element txnCodes = elem.getElement(new XPath("txncode[@id='" + txnCode + "']"));
//                Element txnCodes = elem.getElement(new XPath("///txncode[@id='" +txnCode+ "']"));
                        if (txnCodes == null) {
//                            return null;
                            continue;
                        }
                        bean = new TabBean();
                        int counter = 0;
                        Elements defaultPages = elem.getElements("defaultpage");
                        while (defaultPages.hasMoreElements()) {
                            Element defaultPage = defaultPages.next();
                            String defaultPage_title = defaultPage.getAttributeValue("title");
                            String defaultPage_id = defaultPage.getAttributeValue("id");
                            TabPage tabPage = new TabPage(defaultPage_title, defaultPage.getTextString());
                            tabPage.setTxnCode(txnCode);
                            tabPage.setPageId(defaultPage_id);
                            if (counter == 0) {
                                bean.setCurrURL(defaultPage.getTextString());
                            }
//                        if (pageId != null && pageId.equals(defaultPage_id)) {
//                            tabPage.setSelected(true);
//                            page = defaultPage.getTextString();
//                        }
                            bean.getDefaultPage().add(tabPage);
                            counter += 1;
                        }


//                        Element validator = txnCodes.getElement("validator");
                        Element instruction = txnCodes.getElement("instruction");
                        Elements pages = txnCodes.getElements("page");
//                        bean.setValidator(validator.getTextString());
                        bean.setInstruction(instruction.getTextString());
                        counter = 0;
                        while (pages.hasMoreElements()) {
                            Element _page = pages.next();
                            String _page_title = _page.getAttributeValue("title");
                            String _page_id = _page.getAttributeValue("id");
                            TabPage tabPage = new TabPage(_page_title, _page.getTextString());
                            tabPage.setTxnCode(txnCode);
                            tabPage.setPageId(_page_id);
                            if (counter == 0 && bean.getCurrURL() == null) {
                                bean.setCurrURL(_page.getTextString());
                            }
//                        if (pageId != null && pageId.equals(_page_id)) {
//                            tabPage.setSelected(true);
//                            page = _page.getTextString();
//                        }
                            bean.getPage().add(tabPage);
                            counter += 1;
                        }
                        List<Map<String, String>> list = getOutcome(txnCode, txnCodes);
                        if (list.size() > 0) {
                            bean.setOutcome(list);
                        }
                        //new features .. 19 april 2010
                        //1) keputusan's title can be configured
                        //2) keputusan can be viewed
                        //3) keputusan can insert ulasan only without keputusan
                        bean.setIsOutcomeView(isKeputusanView);
                        bean.setKeputusanTitle(keputusanTitle);
                        if (StringUtils.isNotBlank(nextStageButtonTitle))
                            nextStageButton = nextStageButtonTitle;
                        else nextStageButton = "Selesai";
                        bean.setNextStageButton(nextStageButton);
                        bean.setIsUlasanOnly(isUlasanOnly);
                        //once got the xml file, process finish
//                        break;
                        return bean;
                    }
                }
            }
        } catch (Exception ex) {
            LOG.error("Error while loading tab", ex);
            return null;
        }
        return bean;
    }

    public List<Map<String, String>> getOutcome(String txnCode, Element parent) {
        List<Map<String, String>> list = new ArrayList<Map<String, String>>();
//        TabBean bean = null;
        try {
//            Elements stage =
//                    document.getElements(new XPath("//stage[@id='" + stageId + "']"));
            //outcome
            Element outcomes = parent.getElement(new XPath("outcomes"));
            if (outcomes != null) {
                isKeputusanView = true;
                String s = outcomes.getAttributeValue("title");
                String u = outcomes.getAttributeValue("ulasanOnly");
                if (StringUtils.isNotBlank(s)) {
                    keputusanTitle = s;
                }
                if (StringUtils.isNotBlank(u)) {
                    isUlasanOnly = Boolean.parseBoolean(u);
                }
                Elements outcome = outcomes.getElements("outcome");
                while (outcome.hasMoreElements()) {
                    Map<String, String> map = new HashMap<String, String>();
                    Element out_come = outcome.next();
                    String _label = out_come.getAttributeValue("label");
                    String _value = out_come.getAttributeValue("value");
                    map.put("label", _label);
                    map.put("value", _value);
                    list.add(map);
                }
            }

        } catch (Exception ex) {
            LOG.error("Error while retrieving outcome", ex);
        }
        return list;
    }

    public List<String> getValidatorClass(String workflowId, String stageId, String txnCode) {
        LOG.info("workflowId ::" + workflowId);
        LOG.info("stageId ::" + stageId);
        LOG.info("txnCode ::" + txnCode);
        List<String> list = new ArrayList<String>();
        for (Document document : xmlList) {
            Elements parent =
                    document.getElements(new XPath("/workflow[@id='" + workflowId + "']/stage[@id='" + stageId + "']"));
            while (parent.hasMoreElements()) {
                Element childs = parent.next();
                Element txnCodes = childs.getElement(new XPath("txncode[@id='" + txnCode + "']"));
                if (txnCodes == null) {
                    continue;
                }
                String listener = childs.getAttributeValue("listener");
                if (StringUtils.isNotBlank(listener)) {
                    String[] s = listener.split(",");
                    for (String str : s) {
                        list.add(str);
                    }
                }
            }
        }
        LOG.info("size::" + list.size());
        return list;
    }

    public Map<String, String> getOutComesReport(String workflowId, String stageId, String txncode,
            String outComeVal) {
        Map<String, String> map = null;
        String xpath = "/workflow[@id='" + workflowId + "']/stage[@id='" + stageId
                + "']/txncode[@id='" + txncode
                + "']/outcomes/outcome[@value='" + outComeVal + "']/report";
        LOG.info("xpath ::" + xpath);
        for (Document document : xmlList) {
            Elements parent =
                    document.getElements(new XPath(xpath));
            while (parent.hasMoreElements()) {
                map = new HashMap<String, String>();
                Element childs = parent.next();
                String generator = childs.getAttributeValue("generator");
                String code = childs.getAttributeValue("code");
                map.put("generator", generator);
                map.put("code", code);
            }
        }
        return map;
    }

    public List<Map<String, String>> getReports(String workflowId, String stageId, String txncode) {
        List<Map<String, String>> list = new ArrayList<Map<String, String>>();
        Map<String, String> map = null;
        for (Document document : xmlList) {
            Elements parent =
                    document.getElements(new XPath("/workflow[@id='" + workflowId + "']/stage[@id='"
                    + stageId + "']/txncode[@id='" + txncode + "']/reports"));
            while (parent.hasMoreElements()) {
                Element child = parent.next();
                Elements report = child.getElements("report");
                while (report.hasMoreElements()) {
                    Element e = report.next();
                    map = new HashMap<String, String>();
                    String generator = e.getAttributeValue("generator");
                    String prefix = e.getAttributeValue("prefix");
                    String code = e.getAttributeValue("code");
                    String multipleReport = e.getAttributeValue("foreach");
                    map.put("generator", generator);
                    map.put("prefix", prefix);
                    map.put("code", code);
                    map.put("multiple", multipleReport);
                    list.add(map);
                }
            }
        }
        return list;
    }

    public boolean anyKeputusanToPerform(String workflowId, String stageId, String txncode) {
        for (Document document : xmlList) {
            Elements parent =
                    document.getElements(new XPath("/workflow[@id='" + workflowId + "']/stage[@id='"
                    + stageId + "']/txncode[@id='" + txncode + "']/outcomes"));
            while (parent.hasMoreElements()) {
                Element child = parent.next();
                Elements outcome = child.getElements("outcome");
                if (outcome.hasMoreElements()) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean isValidKeputusan(String workflowId, String stageId, String txncode, String keputusan) {
//        String value = "";
        StringBuilder value = new StringBuilder("");
        LOG.info("workflowId ::" + workflowId);
        LOG.info("stageId ::" + stageId);
        LOG.info("txncode ::" + txncode);
        for (Document document : xmlList) {
            Elements parent =
                    document.getElements(new XPath("/workflow[@id='" + workflowId + "']/stage[@id='"
                    + stageId + "']/txncode[@id='" + txncode + "']/outcomes"));
            while (parent.hasMoreElements()) {
                Element child = parent.next();
                Elements outcome = child.getElements("outcome");
                while (outcome.hasMoreElements()) {
                    Element e = outcome.next();
                    if (value.length() > 0) {
                        value.append(",");
                    }
                    value.append(e.getAttributeValue("value"));
                    LOG.info("value ::" + value);
//                    if(value.equals(keputusan)) return true;

                }
            }
        }
        LOG.debug("value ::" + value);
        LOG.debug("keputusan ::" + keputusan);
        if (value.toString().equals(keputusan)) {
            LOG.debug("keputusan == value");
            return true;
        }

        String[] tmp = value.toString().split(",");
        for (String str : tmp) {
            if (str.equals(keputusan)) {
                return true;
            }
        }
        return false;
//        return value;
    }

    // public String isValidKeputusan(String workflowId, String stageId, String txncode){
    //     String value = "";
    //     LOG.info("workflowId ::" + workflowId);
    //     LOG.info("stageId ::" + stageId);
    //     LOG.info("txncode ::" + txncode);
    //     for (Document document : xmlList) {
    //         Elements parent =
    //                 document.getElements(new XPath("/workflow[@id='" + workflowId + "']/stage[@id='"
    //                 + stageId + "']/txncode[@id='"+txncode+"']/outcomes"));
    //         while (parent.hasMoreElements()) {
    //             Element child = parent.next();
    //             Elements outcome = child.getElements("outcome");
    //             while(outcome.hasMoreElements()) {
    //                Element e = outcome.next();
    //                 value = e.getAttributeValue("value");
    //                 LOG.info("value ::" + value);
//                    if(value.equals(keputusan)) return true;
    //             }
    //         }
    //     }
    //     return value;
    // }
    public List<String> getViewRoles(String workflowId, String stageId) {
        LOG.debug("workflowId :" + workflowId);
        LOG.debug("stageId :" + stageId);
        List<String> roles = new ArrayList<String>();

        for (Document document : xmlList) {
            Elements parent =
                    document.getElements(new XPath("/workflow[@id='" + workflowId + "']/stage[@id='" + stageId + "']"));
            while (parent.hasMoreElements()) {
                Element childs = parent.next();
                String viewable = childs.getAttributeValue("viewable");
                if (StringUtils.isNotBlank(viewable)) {
                    String[] viewables = viewable.split(",");
                    for (String str : viewables) {
                        LOG.debug("str :" + str);
                        roles.add(str);
                    }
                }
            }
        }
        return roles;
    }

    public boolean reportGenerate(String workflowId, String stageId) {
        boolean f = false;
        for (Document document : xmlList) {
            Elements parent =
                    document.getElements(new XPath("/workflow[@id='" + workflowId + "']/stage[@id='" + stageId + "']"));
            while (parent.hasMoreElements()) {
                Element childs = parent.next();
                String report = childs.getAttributeValue("report");
                if (StringUtils.isNotBlank(report)) {
                    f = Boolean.parseBoolean(report);
                }
                break;
            }
        }
        return f;
    }

    public boolean isFinalStage(String workflowId, String stageId) {
        boolean f = false;
        for (Document document : xmlList) {
            Elements parent =
                    document.getElements(new XPath("/workflow[@id='" + workflowId + "']/stage[@id='" + stageId + "']"));
            while (parent.hasMoreElements()) {
                Element childs = parent.next();
                String finalize = childs.getAttributeValue("finalize");
                f = Boolean.parseBoolean(finalize);
                break;
            }
        }
        return f;
    }

    public boolean resultDateToInsert(String workflowId, String stageId) {
        boolean f = false;
        for (Document document : xmlList) {
            Elements parent =
                    document.getElements(new XPath("/workflow[@id='" + workflowId + "']/stage[@id='" + stageId + "']"));
            while (parent.hasMoreElements()) {
                Element childs = parent.next();
                String finalize = childs.getAttributeValue("resultDateToInsert");
                f = Boolean.parseBoolean(finalize);
                break;
            }
        }
        return f;
    }

    public boolean advanceRule(String workflowId, String stageId) {
        boolean f = false;
        for (Document document : xmlList) {
            Elements parent =
                    document.getElements(new XPath("/workflow[@id='" + workflowId + "']/stage[@id='" + stageId + "']"));
            while (parent.hasMoreElements()) {
                Element childs = parent.next();
                String finalize = childs.getAttributeValue("advanceRule");
                f = Boolean.parseBoolean(finalize);
                break;
            }
        }
        return f;
    }

    public boolean isPushBack(String workflowId, String stageId) {
        boolean f = false;
        for (Document document : xmlList) {
            Elements parent =
                    document.getElements(new XPath("/workflow[@id='" + workflowId + "']/stage[@id='" + stageId + "']"));
            while (parent.hasMoreElements()) {
                Element childs = parent.next();
                String pushback = childs.getAttributeValue("pushback");
                if (StringUtils.isBlank(pushback)) {
                    continue;
                }
                f = Boolean.parseBoolean(pushback);
            }
        }
        LOG.debug("isPushBack enabled ::" + f);
        return f;
    }

    public boolean isDistribute(String workflowId, String stageId) {
        boolean f = false;
        for (Document document : xmlList) {
            Elements parent =
                    document.getElements(new XPath("/workflow[@id='" + workflowId + "']/stage[@id='" + stageId + "']"));
            while (parent.hasMoreElements()) {
                Element childs = parent.next();
                String finalize = childs.getAttributeValue("distribute");
                if (StringUtils.isNotBlank(finalize)) {
                    f = Boolean.parseBoolean(finalize);
                }
//                break;
            }
        }
        return f;
    }

    public int getDaysToComplete(String workflowId, String stageId, String txncode) {
        int bilHari = 0;
        for (Document document : xmlList) {
            Elements parent =
                    document.getElements(new XPath("/workflow[@id='" + workflowId + "']/stage[@id='"
                    + stageId + "']/txncode[@id='" + txncode + "']"));
            while (parent.hasMoreElements()) {
                Element child = parent.next();
                String days = child.getAttributeValue("daysToComplete");
                if (StringUtils.isNotBlank(days)) {
                    bilHari = Integer.parseInt(days);
                }
            }
        }
        return bilHari;
    }
    
    public boolean getSimpanDanJana(String workflowId, String stageId, String txncode) {
        boolean f = true;
        for (Document document : xmlList) {
            Elements parent =
                    document.getElements(new XPath("/workflow[@id='" + workflowId + "']/stage[@id='"
                    + stageId + "']/txncode[@id='" + txncode + "']"));
            while (parent.hasMoreElements()) {
                Element child = parent.next();
                String button = child.getAttributeValue("simpanDanJana");
                if (StringUtils.isNotBlank(button)) {
                    f = Boolean.parseBoolean(button);
                }
            }
        }
        return f;
    }

    public String getCurrentAction(String workflowId, String stageId) {
        for (Document document : xmlList) {
            Elements parent =
                    document.getElements(new XPath("/workflow[@id='" + workflowId + "']/stage[@id='" + stageId + "']"));
            while (parent.hasMoreElements()) {
                Element childs = parent.next();
                String currentAction = childs.getAttributeValue("currentAction");
                if (StringUtils.isNotBlank(currentAction)) {
                    return currentAction;
                }
            }
        }
        return "";
    }

    public static void main(String... args) {
        new TabManager().getTabFlow("PMP", "agih_tugas", "http://xmlns.oracle.com/Daftar/Perserahan/PindahmilikTanah");
//        TabManager tab = new TabManager();
//        boolean l = tab.isDistribute("http://xmlns.oracle.com/Daftar/Perserahan/PindahmilikTanah", "kemasukan");
//        System.out.println("l :" + l);
    }
}
