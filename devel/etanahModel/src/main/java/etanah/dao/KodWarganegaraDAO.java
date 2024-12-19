/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.dao;

import etanah.model.*;

/**
 *
 * @author khairil
 */
public class KodWarganegaraDAO extends HibernateDAO<KodWarganegara, String> {

    public String getDefaultOrderProperty() {
        return "nama";
    }
}
