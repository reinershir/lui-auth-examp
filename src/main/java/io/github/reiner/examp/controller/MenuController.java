package io.github.reiner.examp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.github.reiner.examp.dto.MenuMoveDTO;
import io.github.reinershir.auth.annotation.OptionType;
import io.github.reinershir.auth.annotation.Permission;
import io.github.reinershir.auth.annotation.PermissionMapping;
import io.github.reinershir.auth.core.integrate.access.MenuAccess;
import io.github.reinershir.auth.core.support.AuthorizeManager;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RequestMapping("menus")
@RestController
@Api(value = "菜单模块接口",tags = "菜单管理")
@PermissionMapping(value="MENU")
public class MenuController {
	
	
	MenuAccess MenuAccess;
	
	public MenuController(@Autowired(required = false) AuthorizeManager authorizeManager) {
		if(authorizeManager!=null) {
			this.MenuAccess=authorizeManager.getMenuAccess();
		}
	}
	
	/**
	 * 省略	菜单的增删改查，可通过MenuAccess访问,examp:MenuAccess.selectByList()
	 * 
	 *  */
	

	/**
	 * 
	 * @Description 
	 * @author reiner_shir
	 * @return
	 * @date 2022年2月19日
	 */
	
	@Permission(name = "移动菜单",value = OptionType.UPDATE)
	@ApiOperation(value = "移动菜单",notes = "移动菜单")
	@PatchMapping("/position")
	public Object updateMenu(@RequestBody @Validated  MenuMoveDTO dto){
		boolean flag = false;
		Long moveId = dto.getMoveId();
		Long targetId = dto.getTargetId();
		switch(dto.getPosition()) {
		case 1:
			flag = MenuAccess.moveNodeBefore(moveId, targetId)>0?true:false;
			break;
		case 2:
			flag = MenuAccess.moveNodeAfter(moveId, targetId)>0?true:false;
			break;
		case 3:
			flag = MenuAccess.moveNodeByParentAsLastChild(moveId, targetId)>0?true:false;
			break;
		}
		if(flag) {
			return "移动成功";
		}
		return "修改失败！";
	}
}
