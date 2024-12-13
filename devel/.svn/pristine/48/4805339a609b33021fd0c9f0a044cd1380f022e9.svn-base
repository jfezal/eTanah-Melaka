package etanah.view;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import com.google.inject.Inject;

import able.stripes.AbleActionBean;


import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.validation.Validate;

@UrlBinding ("/util/hbn_query")
public class HbnQueryAction extends AbleActionBean {
	
	@Inject
    protected com.google.inject.Provider<Session> sessionProvider;
	
	private String query;
	private List results = new ArrayList(0);

	@Validate (required = true)
	public String getQuery() {
		return query;
	}

	public void setQuery(String query) {
		this.query = query;
	}
		
	public Resolution execute(){
		if (query != null && query.length() > 0){
			try{
				Session s = sessionProvider.get();
				Query q = s.createQuery(query);
				results = q.list();
				System.out.println(this.getClass().getName() + ":results.size=" + results.size());
			} catch (Exception e){
				addSimpleError(e.getMessage());
			}
			return getContext().getSourcePageResolution();
		} else{
			return new ForwardResolution("/util/hbn-query.jsp");
		}
		
	}

	public List getResults() {
		return results;
	}

	public void setResults(List results) {
		this.results = results;
	}

}