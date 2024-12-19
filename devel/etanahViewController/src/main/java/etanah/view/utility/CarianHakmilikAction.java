package etanah.view.utility;

import net.sourceforge.stripes.action.HttpCache;
import net.sourceforge.stripes.action.UrlBinding;
import able.stripes.AbleActionBean;
import static etanah.view.kaunter.PermohonanKaunter2.KAUNTER_HAKMILIK_MAKSIMUM;
import etanah.view.kaunter.RequestValidateIdHakmilik;
import java.util.ArrayList;
import java.util.List;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.HandlesEvent;
import net.sourceforge.stripes.action.Resolution;
import org.apache.log4j.Logger;



@HttpCache(allow = false)
@UrlBinding("/utiliti/carianHakmilik")
public class CarianHakmilikAction extends AbleActionBean {

    private int bilHakmilik = 5;
    private String listHakmilik;
    private int hakmilikArray=0;
    private List<String> list = new ArrayList<String>();
     private static Logger LOG = Logger.getLogger(RequestValidateIdHakmilik.class);

@DefaultHandler
     public Resolution showForm() {

          return new ForwardResolution("/WEB-INF/jsp/utiliti/dev_utiliti_mak_plan.jsp");
     }
     
    @HandlesEvent("next")
    public Resolution getHakmilik(){
          //listHakmilik = (String) getContext().getRequest().getSession().getAttribute("hakmilikArry");
        listHakmilik = getContext().getRequest().getParameter("hakmilikArry"); 
        //listHakmilik = (String) getContext().getRequest().getAttribute("hakmilikArry");
        //LOG.info("listHakmilik @ carianHakmilik2222:::::::::::" + listHakmilik);
       String[] s = listHakmilik.split(",");
                    for (String str : s) {
                        LOG.info("str::" + str);
                        getList().add(str);
                        
                    }
          setHakmilikArray(list.size());
        //LOG.info("size::"+getHakmilikArray());
        return new ForwardResolution("/WEB-INF/jsp/utiliti/dev_save_mak_plan.jsp");
    }
     

    /**
     * @return the bilHakmilik
     */
    public int getBilHakmilik() {
        return bilHakmilik;
    }

    /**
     * @param bilHakmilik the bilHakmilik to set
     */
    public void setBilHakmilik(int bilHakmilik) {
        
         if (bilHakmilik <= KAUNTER_HAKMILIK_MAKSIMUM) {
            this.bilHakmilik = bilHakmilik;
        } else {
            addSimpleError("Maksimum " + KAUNTER_HAKMILIK_MAKSIMUM + " hakmilik yang boleh diterima untuk satu Permohonan!");
            this.bilHakmilik = KAUNTER_HAKMILIK_MAKSIMUM;
        }
    }

    /**
     * @return the listHakmilik
     */
    public String getListHakmilik() {
        return listHakmilik;
    }

    /**
     * @param listHakmilik the listHakmilik to set
     */
    public void setListHakmilik(String listHakmilik) {
        this.listHakmilik = listHakmilik;
    }

    /**
     * @return the list
     */
    public List<String> getList() {
        return list;
    }

    /**
     * @param list the list to set
     */
    public void setList(List<String> list) {
        this.list = list;
    }

    /**
     * @return the hakmilikArray
     */
    public int getHakmilikArray() {
        return hakmilikArray;
    }

    /**
     * @param hakmilikArray the hakmilikArray to set
     */
    public void setHakmilikArray(int hakmilikArray) {
        this.hakmilikArray = hakmilikArray;
    }

    
   
    
     
     
    
}
