package io.github.reiner.examp.model;

import java.util.Date;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;

import io.github.reiner.examp.common.ValidateGroups;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
@ApiModel(description = "用户信息字段")
@TableName("USER")
public class User  {

	/**
	 * 
	 */
	@TableId(type = IdType.AUTO,value = "id")
	@NotNull(message = "ID不能为空�?",groups = ValidateGroups.UpdateGroup.class)
	@ApiModelProperty(value = "用户ID,修改用户信息时不能为�?",  required = false, example = "3")
	private Long id;
	
	/**
	 * 登陆�?
	 */
	@TableField("LOGIN_NAME")
	@ApiModelProperty(value = "用户登陆�?",  required = true, example = "zhangsan")
	@NotBlank(message = "登陆名不能为空！",groups = ValidateGroups.AddGroup.class)
	private String loginName;
	
	/**
	 * 密码
	 */
	@TableField("PASSWORD")
	@ApiModelProperty(value = "登陆密码",  required = true, example = "zhangsan123")
	@NotBlank(message = "密码不能为空�?",groups = ValidateGroups.AddGroup.class)
	@Size(max = 32,min = 6,message = "密码长度�?�?32位，�?�?6位！",groups = ValidateGroups.AddGroup.class)
	private String password;
	
	/**
	 * 用户名称
	 */
	@TableField("NICK_NAME")
	@ApiModelProperty(value = "用户名称",  required = true, example = "李四")
	@NotBlank(message = "用户名称不能为空�?")
	private String nickName;
	
	/**
	 * 邮箱
	 */
	@ApiModelProperty(value = "邮箱",  required = false, example = "zhangsan@mail.com")
	private String email;
	
	/**
	 * 电话号码
	 */
	@TableField("PHONE_NUMBER")
	@ApiModelProperty(value = "电话",  required = false, example = "110")
	private String phoneNumber;
	
	@ApiModelProperty(hidden = true)
	private Integer status;
	
	/**
	 * 创建时间
	 */
	@TableField("CREATE_DATE")
	@ApiModelProperty(hidden = true)
	private Date createDate;
	
	/**
	 * 
	 */
	@TableField("UPDATE_DATE")
	@ApiModelProperty(hidden = true)
	private Date updateDate;
	
	/**
	 * 其它说明 
	 */
	@ApiModelProperty(value = "电话",  required = false, example = "其它说明")
	private String remark;
	
	@TableLogic
	@TableField("IS_DELETE")
	@ApiModelProperty(hidden = true)
	private Integer isDelete;
	
	public Integer getIsDelete() {
		return isDelete;
	}

	public void setIsDelete(Integer isDelete) {
		this.isDelete = isDelete;
	}

	public void setId (Long id){
		this.id = id;
	}
	
	public Long getId (){
		return this.id;
	}
	
	public void setLoginName (String loginName){
		this.loginName = loginName;
	}
	
	public String getLoginName (){
		return this.loginName;
	}
	
	public void setPassword (String password){
		this.password = password;
	}
	
	public String getPassword (){
		return this.password;
	}
	
	public void setNickName (String nickName){
		this.nickName = nickName;
	}
	
	public String getNickName (){
		return this.nickName;
	}
	
	public void setEmail (String email){
		this.email = email;
	}
	
	public String getEmail (){
		return this.email;
	}
	
	public void setPhoneNumber (String phoneNumber){
		this.phoneNumber = phoneNumber;
	}
	
	public String getPhoneNumber (){
		return this.phoneNumber;
	}
	
	public void setCreateDate (Date createDate){
		this.createDate = createDate;
	}
	
	public Date getCreateDate (){
		return this.createDate;
	}
	
	public void setUpdateDate (Date updateDate){
		this.updateDate = updateDate;
	}
	
	public Date getUpdateDate (){
		return this.updateDate;
	}
	
	public void setRemark (String remark){
		this.remark = remark;
	}
	
	public String getRemark (){
		return this.remark;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
	
	

}
