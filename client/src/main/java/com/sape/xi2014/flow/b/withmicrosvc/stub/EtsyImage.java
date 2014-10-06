package com.sape.xi2014.flow.b.withmicrosvc.stub;

import java.util.List;

public class EtsyImage {
	private List<ImgResult> results;

	public List<ImgResult> getResults() {
		return results;
	}

	public void setResults(List<ImgResult> results) {
		this.results = results;
	}
	
}

class ImgResult {
	
	private String url_fullxfull;

	public String getUrl_fullxfull() {
		return url_fullxfull;
	}

	public void setUrl_fullxfull(String url_fullxfull) {
		this.url_fullxfull = url_fullxfull;
	}

	
}