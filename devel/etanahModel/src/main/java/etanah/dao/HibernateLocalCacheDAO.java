package etanah.dao;

import java.io.Serializable;
import java.util.Collections;
import java.util.Map;
import org.apache.commons.collections.map.LRUMap;
import org.apache.log4j.Logger;

/**
 * <p>Uses a simple LRUMap to hold 32 most used values.
 * The cached value is removed for each save/update/delete operation
 * and only added to cache when calling findById.</p>
 * <p><b>Use for non-critical code. For table with absolutely no update. Need service restart for any
 * data update to propagate between eTanah instances.</b></p>
 * <p>Note: If you encounter errors when building, make sure you have the
 * latest able library build. Requires commons-collections 3.2.1</p>
 * 
 */
public abstract class HibernateLocalCacheDAO<T, ID extends Serializable> extends HibernateCacheableDAO<T, ID> {
    public static final int MAX_CACHE = 32;

    private static final Logger LOG = Logger.getLogger(HibernateLocalCacheDAO.class);
    private static final Map cache = Collections.synchronizedMap(new LRUMap(MAX_CACHE));

    /**
     * Check id in cache, if null then fetch from db.
     */
    @Override
    public T findById(ID id) {
        T obj;
        if (!cache.containsKey(id)) {
            LOG.info(String.format("%s not cached for key [%s]", super.persistentClass.getName(), id));
            obj = super.findById(id);
            cache.put(id, obj);
        }
        else {
            obj = (T) cache.get(id);
            LOG.info(String.format("%s cached for key [%s]", super.persistentClass.getName(), id));
        }
        return obj;
    }
    
    @Override
    public T saveOrUpdate(T obj) {
        LOG.warn("Data is being modified, might cause inconsistencies!");
        T saved = super.saveOrUpdate(obj);
        cache.remove(getPK(obj));
        return saved;
    }
    
    @Override
    public void save(T obj) {
        LOG.warn("Data is being modified, might cause inconsistencies!");
        super.save(obj);
        cache.remove(getPK(obj));
    }
    
    @Override
    public void update(T obj) {
        LOG.warn("Data is being modified, might cause inconsistencies!");
        super.update(obj);
        cache.remove(obj);
    }
    
    @Override
    public void delete(T obj) {
        LOG.warn("Data is being modified, might cause inconsistencies!");
        super.delete(obj);
        cache.remove(obj);
    }
    
}
