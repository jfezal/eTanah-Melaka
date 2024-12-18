/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package etanah.manager;

/**
 *
 * @author md.nurfikri
 */
public class TabPage {
    private String title;
    private String halfTitle;
    private String url;
    private String txnCode;
    private String pageId;
    private boolean selected = false;

    public String getHalfTitle() {
        return halfTitle;
    }

    public void setHalfTitle(String halfTitle) {
        this.halfTitle = halfTitle;
    }

    public String getTxnCode() {
        return txnCode;
    }

    public void setTxnCode(String txnCode) {
        this.txnCode = txnCode;
    }
    public TabPage(String title, String url){
        this.title = title;
        if(title.length() > 15) {
            this.halfTitle = title.substring(0, 15) + "...";
        } else {
            this.halfTitle = title;
        }
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getPageId() {
        return pageId;
    }

    public void setPageId(String pageId) {
        this.pageId = pageId;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

}
