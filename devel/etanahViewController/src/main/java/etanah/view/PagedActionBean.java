package etanah.view;

import etanah.util.PagedList;

import able.stripes.AbleActionBean;

public class PagedActionBean extends AbleActionBean {
	
	public int getRequestedPageNo(){
		String pageParam = getContext().getRequest().getParameter("page");
		return (pageParam == null ? 1 : Integer.valueOf(pageParam));
	}
	
	public void configurePage(PagedList<?> list){
		// get the page no requested
		int pageNo = getRequestedPageNo(); 
		if (pageNo > 0){
			list.setEnablePaging(true);
			list.setPageNumber(pageNo);
		}
	}

}
