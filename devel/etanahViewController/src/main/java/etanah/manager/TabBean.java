/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package etanah.manager;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;



/**
 *
 * @author md.nurfikri
 */
public class TabBean{

    //For Tabbing purposes
    private String stageId;
    private List<TabPage> defaultPage = new ArrayList();
    private List<TabPage> page = new ArrayList();
    private List<Map<String,String>> outcome = new ArrayList();
    //keputusan is view only
    private boolean isOutcomeView = false;
    private String keputusanTitle = "Keputusan"; // by default 'keputusan'
    private String nextStageButton = "Selesai"; // by default 'Selesai'
    private boolean isUlasanOnly = false;
    private String validator;
    private String instruction;
    private String currURL;

    public List<TabPage> getDefaultPage() {
        return defaultPage!=null ? defaultPage:Collections.<TabPage>emptyList();
    }

    public void setDefaultPage(List defaultPage) {
        this.defaultPage = defaultPage;
    }

    public List<TabPage> getPage() {
        return page!=null ? page:Collections.<TabPage>emptyList();
    }

    public void setPage(List page) {
        this.page = page;
    }

    public String getInstruction() {
        return instruction;
    }

    public void setInstruction(String instruction) {
        this.instruction = instruction;
    }

    public String getValidator() {
        return validator;
    }

    public void setValidator(String validator) {
        this.validator = validator;
    }

    public String getStageId() {
        return stageId;
    }

    public void setStageId(String stageId) {
        this.stageId = stageId;
    }

    public List<Map<String,String>> getOutcome() {
        return outcome;
    }

    public void setOutcome(List<Map<String,String>> outcome) {
        this.outcome = outcome;
    }

    public String getCurrURL() {
        return currURL;
    }

    public void setCurrURL(String currURL) {
        this.currURL = currURL;
    }

    public boolean isIsOutcomeView() {
        return isOutcomeView;
    }

    public void setIsOutcomeView(boolean isOutcomeView) {
        this.isOutcomeView = isOutcomeView;
    }

    public String getKeputusanTitle() {
        return keputusanTitle;
    }

    public void setKeputusanTitle(String keputusanTitle) {
        this.keputusanTitle = keputusanTitle;
    }

    public String getNextStageButton() {
        return nextStageButton;
    }

    public void setNextStageButton(String nextStageButton) {
        this.nextStageButton = nextStageButton;
    }

    public boolean isIsUlasanOnly() {
        return isUlasanOnly;
    }

    public void setIsUlasanOnly(boolean isUlasanOnly) {
        this.isUlasanOnly = isUlasanOnly;
    }

    
}