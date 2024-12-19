package com.theta.portal.stripes.config;



import com.theta.portal.model.UserTable;
import com.theta.portal.stripes.BaseActionBean;
import com.theta.portal.stripes.WelcomeActionBean;
import net.sourceforge.stripes.action.RedirectResolution;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;



public class AuthRequiredIntereptor implements MethodInterceptor {
    
    private static String AWAM = "001";
    
    public Object invoke(MethodInvocation invocation) throws Throwable {
        BaseActionBean bean = (BaseActionBean) invocation.getThis();

        if (!bean.getContext().isLoggedIn()) {                       
            return new RedirectResolution(WelcomeActionBean.class);
        } else {            
            UserTable pengguna = bean.getContext().getCurrentUser();
            if (pengguna == null) {
                return new RedirectResolution(WelcomeActionBean.class);                    
            }            
            String exp = "";
            try {
                return invocation.proceed();
            } catch (Exception ex) {
                exp = ex.getMessage();
                throw ex;
            } finally {
            }
        }
    }

}
