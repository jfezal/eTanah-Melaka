/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package etanah.dao;

import etanah.model.KodBank;

/**
 *
 * @author md.nurfikri
 */
public class KodBankDAO extends HibernateDAO<KodBank, String>{
	
	public String getDefaultOrderProperty(){
		return "nama";
	}

}
