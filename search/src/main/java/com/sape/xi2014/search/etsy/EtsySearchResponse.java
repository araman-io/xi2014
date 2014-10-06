package com.sape.xi2014.search.etsy;

import java.util.List;

public class EtsySearchResponse {
	String count;
	List<Result> resultlst;
	public List<Result> getResultlst() {
		return resultlst;
	}
	public void setResultlst(List<Result> resultlst) {
		this.resultlst = resultlst;
	}
	public String getCount() {
		return count;
	}
	public void setCount(String count) {
		this.count = count;
	}
	
	
}

