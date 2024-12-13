/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package etanah.service;

import com.google.inject.Inject;
import com.wideplay.warp.persist.Transactional;
import etanah.dao.UrusanAliranDAO;
import etanah.dao.KodUrusanDAO;
import etanah.model.KodUrusan;
import etanah.model.UrusanAliran;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author azwady
 */



public class UrusanALiranService {
   
    @Inject 
    private UrusanAliranDAO urusanAliranDAO;
    
    @Inject
    private KodUrusanDAO kodUrusanDAO;
    
    protected com.google.inject.Provider<Session> sessionProvider;
    //private List<KodUrusan> namaUrusan;

   
    
    @Transactional
    public void save(UrusanAliran aliran) {
        //urusanAliranDAO = new UrusanAliranDAO();
        urusanAliranDAO.save(aliran); 
    }

    public List<KodUrusan> getNamaUrusan() {
         return kodUrusanDAO.findAll();
    }
}
