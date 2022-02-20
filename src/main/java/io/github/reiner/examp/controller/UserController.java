package io.github.reiner.examp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import io.github.reiner.examp.common.ValidateGroups;
import io.github.reiner.examp.dto.UserReqDTO;
import io.github.reiner.examp.model.User;
import io.github.reiner.examp.service.UserService;
import io.github.reinershir.auth.annotation.OptionType;
import io.github.reinershir.auth.annotation.Permission;
import io.github.reinershir.auth.annotation.PermissionMapping;
import io.github.reinershir.auth.core.support.AuthorizeManager;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;

@RequestMapping("users")
@RestController
@Api(value = "用户模块接口",tags = "管理用户")
@PermissionMapping(value="USER")
public class UserController {
	
	
	@Autowired
	UserService userService;
	@Autowired(required = false)
	AuthorizeManager authorizeManager;
	
	@PostMapping("token")
	@Permission(value=OptionType.SKIP,name="用户登录")
	@ApiOperation(value = "用户登录接口",notes = "登录接口,创建并返回token",httpMethod = "POST")
	public Object login(@Validated @RequestBody User loginDTO) throws Exception{
		User user = userService.login(loginDTO.getLoginName(), loginDTO.getPassword());
		if(user==null) {
			return "账号密码不匹配！";
		}	
		
		//获取该用户所拥有的菜单权限
		//List<Menu> menus = authorizeManager.getMenusByUser(user.getId().toString());
		
		return user;
	}
	
	@Permission(name = "用户列表",value = OptionType.LIST)
	@ApiOperation(value = "用户列表查询接口",notes = "用户列表",httpMethod = "GET")
	@GetMapping
	public Object list(@Validated Object userListDTO){
		return null;
	}
	
	@ResponseStatus(code = HttpStatus.CREATED)
	@Permission(name = "添加用户",value = OptionType.ADD)
	@ApiOperation(value = "添加用户",notes = "添加用户",httpMethod = "POST")
	@PostMapping
	public Object addUser(@Validated(value = ValidateGroups.AddGroup.class) @RequestBody UserReqDTO user){
		User result = userService.insert(user);
		return result;
		
	}
	
	@Permission(name = "修改用户",value = OptionType.UPDATE)
	@ApiOperation(value = "修改用户信息",notes = "修改用户",httpMethod = "PUT")
	@PutMapping
	public Object updateUser(@Validated(value = ValidateGroups.UpdateGroup.class) @RequestBody UserReqDTO user){
		return null;
	}
	
	@Permission(name = "删除用户",value = OptionType.DELETE)
	@ApiImplicitParam(name = "id",value = "用户ID",required = true,dataType = "String",paramType = "path")
	@ApiOperation(value = "删除用户",notes = "逻辑删除用户",httpMethod = "DELETE")
	@DeleteMapping("/{id}")
	public Object delete(@PathVariable("id") Long id){
		//REMOVE USER BY ID
		
			try {
				//注销用户登陆状态
				authorizeManager.logout(id.toString());
			} catch (Exception e) {
				e.printStackTrace();
			}
		return null;
	}
	
	@Permission(name = "获取用户所绑定的角色ID",value = OptionType.LOGIN)
	@ApiImplicitParam(name = "userId",value = "用户ID",required = true,dataType = "String",paramType = "path")
	@ApiOperation(value = "获取用户所绑定的角色ID",notes = "获取用户所绑定的角色",httpMethod = "GET")
	@GetMapping("/{userId}/roleIds")
	public List<Long> getRoleIdByUser(@PathVariable("userId") Long userId){
		return userService.getRoleByUser(userId+"");
	}

	
}
