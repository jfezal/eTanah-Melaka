package etanah.view;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;

import etanah.dao.HibernateDAO;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.RedirectResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.controller.LifecycleStage;


import able.stripes.AbleActionBean;
import etanah.manager.TabBean;

public abstract class BasicCrudActionBean<E extends Serializable, DAO extends HibernateDAO, PK extends Serializable>
        extends AbleActionBean {

    public static final String JSP_POST_FIX = ".jsp";
    public static final String LIST_JSP_POST_FIX = "-list" + JSP_POST_FIX;
    public static final String ADD_JSP_POST_FIX = "-new.jsp";
    protected E item;
    protected PK pk;
    protected DAO manager;
    /**
     * WARNING: might not be thread-safe!
     * @return the DAO
     */
    @SuppressWarnings("unchecked")
    public DAO getManager() {
        if (manager == null) {
            Class<DAO> c = ((Class<DAO>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[1]);
            try {
                manager = c.newInstance();
            } catch (InstantiationException e) {
                e.printStackTrace();
                return null;
            } catch (IllegalAccessException e) {
                e.printStackTrace();
                return null;
            }
        }

        return manager;
    }

    public abstract String getPrefixUri();

    /* A reminder to implement a rehydrate method */
    @Before(stages = {LifecycleStage.BindingAndValidation}) // THIS WONT BE INHERITED, DECLARE YOUR OWN, UNLESS GUICE??
    public abstract void rehydrate();

    // TODO: need to overide this for setting validations
    public E getItem() {
        return item;
    }

    public void setItem(E item) {
        this.item = item;
    }

    public Resolution addItem() {
        DAO d = getManager();
        try {
            d.save(item);
        } catch (Exception e) {
            addSimpleError(e.getMessage());
            return getContext().getSourcePageResolution();
        }

        addSimpleMessage("Rekod telah ditambah.");
        return new ForwardResolution(getPrefixUri() + JSP_POST_FIX);
    }

    public Resolution viewItem() {
        if (item == null) {
            rehydrate();
        }
        if (item != null) {
            return new ForwardResolution(getPrefixUri() + JSP_POST_FIX);
        } else {
            addSimpleError("Rekod tidak wujud!");
            //return getContext().getSourcePageResolution();
            return new ForwardResolution(getPrefixUri() + JSP_POST_FIX);
        }
    }

    public Resolution updateItem() {
        DAO d = getManager();
        try {
            d.update(item);
        } catch (Exception e) {
            addSimpleError(e.getMessage());
            return getContext().getSourcePageResolution();
        }

        addSimpleMessage("Rekod telah dikemaskini.");
        return new ForwardResolution(getPrefixUri() + JSP_POST_FIX);
    }

    public Resolution deleteItem() {
        DAO d = getManager();
        try {
            d.delete(item);
        } catch (Exception e) {
            addSimpleError(e.getMessage());
            return getContext().getSourcePageResolution();
        }

        addSimpleMessage("Rekod telah dipadam.");
        return new RedirectResolution(getPrefixUri() + LIST_JSP_POST_FIX);

    }

    public Resolution showAddItemForm() {
        return new ForwardResolution(getPrefixUri() + ADD_JSP_POST_FIX);
    }

    /**
     * Utility method which should be call from inside rehydrate() method
     * for object with Id type Long
     * @param paramName
     */
    protected void doRehydrateWithLongPK(String paramName) {
        String id = getContext().getRequest().getParameter(paramName);
        if (id != null && id.length() > 0) {
            item = (E) getManager().findById(Long.parseLong(id));
        }

    }

    /**
     * Utility method which should be call from inside rehydrate() method
     * for object with Id type Integer
     * @param paramName
     */
    protected void doRehydrateWithIntPK(String paramName) {
        String id = getContext().getRequest().getParameter(paramName);
        if (id != null && id.length() > 0) {
            item = (E) getManager().findById(Integer.parseInt(id));
        }

    }   

    
}
