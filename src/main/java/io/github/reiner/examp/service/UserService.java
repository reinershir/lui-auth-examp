package io.github.reiner.examp.service;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import io.github.reiner.examp.dto.UserReqDTO;
import io.github.reiner.examp.mapper.UserMapper;
import io.github.reiner.examp.model.User;
import io.github.reinershir.auth.core.support.AuthorizeManager;

@Service("userService")
public class UserService {

	@Autowired
	UserMapper userMapper;
	
	@Autowired(required = false)
	AuthorizeManager authorizeManager;
	
	Logger logger=LoggerFactory.getLogger(this.getClass());
	
	
	@Value(value="${lui-auth.authrizationConfig.administratorId}")
	String administratorId;
	
	
	/**
	 * @Title: login examp
	 * @author reiner_shir
	 * @date 2022-2-19
	 * @param loginName
	 * @param password
	 * @throws Exception 
	 */
	public User login(String loginName,String password) throws Exception{
		User entity = new User();
		entity.setLoginName(loginName);
		QueryWrapper<User> queryWrapper = new QueryWrapper<>(entity);
		User user = userMapper.selectOne(queryWrapper);
		if(user==null) {
			throw new RuntimeException("登陆户名不存在！");
		}
		//示例代码，未作密码加密处理
		if(user.getPassword().equals(password)) {
			User storeUser = new User();
			storeUser.setId(user.getId());
			storeUser.setLoginName(user.getLoginName());
			storeUser.setCreateDate(user.getCreateDate());
			//用户登陆成功，生成token
			String token = authorizeManager.generateToken(user.getId().toString(), 1,null);
			//将token放在password字段里返回
			storeUser.setPassword(token);
			return storeUser;
		}
		return null;
	}
	@Transactional
	public User insert(UserReqDTO user) {
		user.setIsDelete(0);
		user.setCreateDate(new Date());
		User entity = new User();
		entity.setLoginName(user.getLoginName());
		if(userMapper.selectCount(new QueryWrapper<>(entity))>0) {
			throw new RuntimeException("登陆名已经存在！");
		}
		user.setPassword(user.getPassword());
		int result = userMapper.insert(user);
		if(result>0) {
			//为用户绑定角色
			if(!CollectionUtils.isEmpty(user.getRoleIds())) {
				authorizeManager.getRoleAccess().bindRoleForUser(user.getId()+"", user.getRoleIds());
			}
		}
		return user;
	}
	

	public User getById(Long id) {
		return userMapper.selectById(id);
	}
	
	
	/**
	 * @Title: getRoleByUser
	 * @Description:   获取该用户绑定的角色ID
	 * @author reiner_shir
	 * @date 2022-2-19
	 * @param userId
	 * @return  返回角色ID列表
	 */
	public List<Long> getRoleByUser(String userId){
		return authorizeManager.getRoleAccess().getRoleIdByUser(userId);
	}
	
	
}
