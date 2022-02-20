package io.github.reiner.examp.common;

import javax.validation.groups.Default;

/**
 * spring validate 分组类
 * @date:   2019年5月9日 下午3:23:45   
 * @author 夏辉 
 * @Description:
 */
public class ValidateGroups {

	//分组类要继承Default,spring validate才会验证其他未添加分组的验证
	
	/**
	 * 添加数据时的分组
	 * @date:   2019年5月9日 下午3:23:58   
	 * @author 夏辉 
	 * @Description:
	 */
	public interface AddGroup extends Default{}
	/**
	 * 修改数据时的分组
	 * @date:   2019年5月9日 下午3:24:07   
	 * @author 夏辉 
	 * @Description:
	 */
	public interface UpdateGroup extends Default{}
	/**
	 * 删除数据时的分组
	 * @date:   2019年5月9日 下午3:24:14   
	 * @author 夏辉 
	 * @Description:
	 */
	public interface DeleteGroup extends Default{}
	
	/**
	 * 查询数据时的分组
	 * @date:   2019年5月9日 下午3:24:23   
	 * @author 夏辉 
	 * @Description:
	 */
	public interface QueryGroup extends Default{}
	/**
	 * @date:   2019年5月9日 下午3:33:52   
	 * @author 夏辉 
	 * @Description: 自定义的业务分组，按需求自由使用
	 */
	public interface BusinessGroup extends Default{}
}
