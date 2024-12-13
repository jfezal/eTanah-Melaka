package etanah.view.kaunter;

import java.util.ArrayList;

import org.apache.log4j.Logger;

import com.google.inject.Singleton;

import etanah.view.etanahActionBeanContext;

@Singleton
public class BayaranSessionService {
	
    private static final Logger LOG = Logger.getLogger(BayaranSessionService.class);
    private static final boolean debug = LOG.isDebugEnabled();

	public int addUrusan(etanahActionBeanContext ctx, UrusanKaunter ku){
        ArrayList<UrusanKaunter> au = null;
        Object obj = ctx.getWorkData();
        int pos = 0;
        
        if (obj == null || !(obj instanceof ArrayList)) {
            if (debug) {
                LOG.debug("creating a new list of Urusan in session");
            }
            au = new ArrayList<UrusanKaunter>();
        } else {
            au = (ArrayList<UrusanKaunter>) obj;
            if (debug) {
                LOG.debug("there are already " + au.size()
                        + " registered in session listed below:");
                for (UrusanKaunter u1 : au) {
                    LOG.debug(u1.getKodUrusan());
                }
            }
            pos = au.size();
        }
        
        ku.setPosition(pos);
    	au.add(ku);
        ctx.setWorkData(au);

    	return pos;
	}
	
	public void updateUrusan(etanahActionBeanContext ctx, UrusanKaunter ku, int position){
		ArrayList<UrusanKaunter> listKu = getAllUrusanFromSession(ctx);
		
		if (listKu == null){
			throw new RuntimeException("Tiada simpanan Urusan dalam sesi pengguna");
		}
		
		listKu.set(position, ku);
		ctx.setWorkData(listKu);
	}
	
	/**
	 * 
	 * @param ctx
	 * @param position
	 * @return remaining UrusanCache left
	 */
	public int removeUrusan(etanahActionBeanContext ctx, int position){
		ArrayList<UrusanKaunter> listKu = getAllUrusanFromSession(ctx);
		
		listKu.remove(position);
		
    	// need to update UrusanValue.position
    	for (int i = 0; i < listKu.size(); i++){
    		UrusanKaunter uc =  listKu.get(i);
    		uc.setPosition(i);
    	}
		
    	ctx.setWorkData(listKu);
    	
		return listKu.size();
	}
	
	public final ArrayList<UrusanKaunter> getAllUrusanFromSession(etanahActionBeanContext ctx){
		ArrayList<UrusanKaunter> listKu = null;
		
        Object obj = ctx.getWorkData();
        if (obj == null || !(obj instanceof ArrayList)) {
            if (debug) {
                LOG.debug("creating a new ArrayList of Urusan in session");
            }
            listKu = new ArrayList<UrusanKaunter>();
            ctx.setWorkData(listKu);
        } else {
            listKu = (ArrayList<UrusanKaunter>) obj;
            if (debug) {
                LOG.debug("there are already " + listKu.size() + " registered in session listed below:");
                for (UrusanKaunter u1 : listKu) {
                    LOG.debug(u1.getKodUrusan());
                }
            }
        }
        
        return listKu;
	}

	public final UrusanKaunter getUrusanFromSession(etanahActionBeanContext ctx, int which){
	    ArrayList<UrusanKaunter> luk = getAllUrusanFromSession(ctx);
	    if (which >= luk.size()){
	        LOG.warn("Attempt to get urusan not in session");
	        return null;
	    }
	    
	    return luk.get(which);
	}
	
	/**
	 * Group the urusan of urusanTarget into the same group as in urusanSource
	 * @param urusanSource
	 * @param urusanTarget
	 */
	public void groupUrusanInKumpulanUrusan(etanahActionBeanContext ctx, UrusanKaunter urusanSource, UrusanKaunter urusanTarget){
	    int pos = urusanSource.getPosition();
	    UrusanKaunter uk = getUrusanFromSession(ctx, pos);
	    String k = uk.getKumpulanUrusan();
	    if (k == null){
	        // generate new kumpulan
	        k = String.valueOf(System.currentTimeMillis());
	        uk.setKumpulanUrusan(k);
	        updateUrusan(ctx, uk, pos);
	    }
	    urusanTarget.setKumpulanUrusan(k);
	}
	
	/**
	 * Group the urusanTarget with the previous urusan in cache
	 * @param ctx
	 * @param urusanTarget
	 */
	public void groupUrusanInPreviousKumpulanUrusan(etanahActionBeanContext ctx, UrusanKaunter urusanTarget){
        ArrayList<UrusanKaunter> luk = getAllUrusanFromSession(ctx);
        if (luk == null || luk.size() == 0) return;
        
        UrusanKaunter uk = getUrusanFromSession(ctx, luk.size() - 1);
        String k = uk.getKumpulanUrusan();
        if (k == null){
            // generate new kumpulan
            k = String.valueOf(System.currentTimeMillis());
            uk.setKumpulanUrusan(k);
            updateUrusan(ctx, uk, luk.size() - 1);
        }
        urusanTarget.setKumpulanUrusan(k);
    }

	
	public void resetAll(etanahActionBeanContext ctx){
        ctx.removeWorkdata();
	}
}
