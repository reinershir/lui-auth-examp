package io.github.reiner.examp.dto;

import io.swagger.annotations.ApiModelProperty;

public class PageReqDTO {

	@ApiModelProperty(value = "当前页数",  required = false, example = "1")
	private Integer page=1;
	@ApiModelProperty(value = "每页条数",  required = false, example = "20")
	private Integer pageSize=20;
	
	public Integer getPage() {
		return page;
	}
	public void setPage(Integer page) {
		this.page = page;
	}
	public Integer getPageSize() {
		return pageSize;
	}
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
	
	
}
