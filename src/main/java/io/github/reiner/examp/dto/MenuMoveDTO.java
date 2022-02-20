package io.github.reiner.examp.dto;

import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("移动菜单节点请求对象")
public class MenuMoveDTO {

	@NotNull
	@ApiModelProperty(value = "被移动菜单ID",notes = "",  required = true, example = "1")
	private Long moveId;
	
	@NotNull
	@ApiModelProperty(value = "目标菜单ID",notes = "",  required = true, example = "11")
	private Long targetId;
	
	@NotNull
	@ApiModelProperty(value = "移动到目标菜单的位置,1=目标前面，2=目标后面，3=目标的子节点最后一个",notes = "1=目标前面，2=目标后面，3=目标的子节点最后一个",  required = true, example = "1")
	private int position;

	public Long getMoveId() {
		return moveId;
	}

	public void setMoveId(Long moveId) {
		this.moveId = moveId;
	}

	public Long getTargetId() {
		return targetId;
	}

	public void setTargetId(Long targetId) {
		this.targetId = targetId;
	}

	public int getPosition() {
		return position;
	}

	public void setPosition(int position) {
		this.position = position;
	}
	
	

	
	
	
}
