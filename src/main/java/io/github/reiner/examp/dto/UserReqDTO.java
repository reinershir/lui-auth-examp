package io.github.reiner.examp.dto;

import java.util.List;

import com.baomidou.mybatisplus.annotation.TableField;

import io.github.reiner.examp.model.User;
import io.swagger.annotations.ApiModelProperty;

public class UserReqDTO extends User{
	@TableField(exist = false)
	@ApiModelProperty(value = "该用户要绑定的角色ID",  required = false, example = "[1,2,10]")
	private List<Long> roleIds;
	public List<Long> getRoleIds() {
		return roleIds;
	}
	public void setRoleIds(List<Long> roleIds) {
		this.roleIds = roleIds;
	}

	
	
	
}
