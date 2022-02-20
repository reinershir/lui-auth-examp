package io.github.reiner.examp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.github.reiner.examp.common.ValidateGroups;
import io.github.reiner.examp.dto.PageReqDTO;
import io.github.reiner.examp.dto.RoleDTO;
import io.github.reinershir.auth.annotation.OptionType;
import io.github.reinershir.auth.annotation.Permission;
import io.github.reinershir.auth.annotation.PermissionMapping;
import io.github.reinershir.auth.core.integrate.access.RoleAccess;
import io.github.reinershir.auth.core.model.Role;
import io.github.reinershir.auth.core.model.RolePermission;
import io.github.reinershir.auth.core.support.AuthorizeManager;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;

@RequestMapping("roles")
@RestController
@Api(value = "角色模块接口",tags = "角色管理")
@PermissionMapping(value="ROLE")
public class RoleController {
	
	
	RoleAccess roleAccess;
	public RoleController(@Autowired(required = false) AuthorizeManager authorizeManager) {
		if(authorizeManager!=null) {
			this.roleAccess=authorizeManager.getRoleAccess();
		}
	}

	@Permission(name = "角色列表",value = OptionType.LIST)
	@ApiOperation(value = "角色列表查询接口",notes = "角色列表",httpMethod = "GET")
	@GetMapping
	public List<Role> list(@Validated PageReqDTO reqDTO){
		List<io.github.reinershir.auth.core.model.Role> list = roleAccess.selectList(reqDTO.getPage(), reqDTO.getPageSize(),"role name");
		//Long count = roleAccess.selectCount(reqDTO.getRoleName());
		return list;
	}
	
	@Permission(name = "添加角色",value = OptionType.ADD)
	@ApiOperation(value = "添加角色",notes = "添加角色",httpMethod = "POST")
	@PostMapping
	public Object addRole(@Validated @RequestBody RoleDTO dto){
		if(roleAccess.insert(dto,dto.getMenuIds())>0) {
			return "true";
		}
		return "添加失败！";
	}
	
	@Permission(name = "修改角色",value = OptionType.UPDATE)
	@ApiOperation(value = "修改角色信息",notes = "修改角色",httpMethod = "PUT")
	@PutMapping
	public Object updateUser(@Validated(value = ValidateGroups.UpdateGroup.class) @RequestBody RoleDTO roleDTO){
		if(roleAccess.updateById(roleDTO, roleDTO.getMenuIds())>0) {
			return "true";
		}
		return "修改失败！";
	}
	
	@Permission(name = "删除角色",value = OptionType.DELETE)
	@ApiImplicitParam(name = "id",value = "角色ID",required = true,dataType = "String",paramType = "path")
	@ApiOperation(value = "删除角色",notes = "逻辑删除角色",httpMethod = "DELETE")
	@DeleteMapping("/{id}")
	public Object delete(@PathVariable("id") Long id){
		if(roleAccess.deleteById(id)>0) {
			return "删除成功！";
		}
		return "修改失败！";
	}
	
	@Permission(name = "查询角色所绑定的菜单权限",value = OptionType.CUSTOM,customPermissionCode = "ROLE_PERMISSIONS")
	@ApiImplicitParam(name = "roleId",value = "角色ID",required = true,dataType = "String",paramType = "path")
	@ApiOperation(value = "查询角色所绑定的菜单权限",notes = "查询角色所绑定的菜单权限",httpMethod = "GET")
	@GetMapping("/{roleId}/rolePermissions")
	public List<RolePermission> getRolePermissionsById(@PathVariable("roleId") Long roleId){
		return roleAccess.selectRolePermissionByRole(roleId);
	}
	
}
