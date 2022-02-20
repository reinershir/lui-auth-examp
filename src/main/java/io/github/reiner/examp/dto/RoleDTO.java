package io.github.reiner.examp.dto;

import java.util.ArrayList;

import io.github.reinershir.auth.core.model.Role;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "角色参数对象")
public class RoleDTO extends Role{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5734358829121162547L;
	@ApiModelProperty(value = "角色所关联的菜单权限，值为ID数组",  required = false, example = "[1,2,3,50]")
	private ArrayList<Long> menuIds;

	public ArrayList<Long> getMenuIds() {
		return menuIds;
	}

	public void setMenuIds(ArrayList<Long> menuIds) {
		this.menuIds = menuIds;
	}

	
	 
}
