/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package etanah.dao;

import etanah.model.SejarahHakmilik;

/**
 *
 * @author khairil
 */
public class SejarahHakmilikDAO extends HibernateDAO<SejarahHakmilik, String> {

    @Override
    public void save(SejarahHakmilik object) {
       throw new UnsupportedOperationException("No Update on Sejarah Hakmilik");
    }

    @Override
    public SejarahHakmilik saveOrUpdate(SejarahHakmilik object) {
        throw new UnsupportedOperationException("No Update on Sejarah Hakmilik");
    }

    @Override
    public void update(SejarahHakmilik object) {
        throw new UnsupportedOperationException("No Update on Sejarah Hakmilik");
    }


}
