package ${package}.api;

import java.util.List;
import com.jjb.common.framework.Page;
import ${package}.model.${className};

/**
 * <p>Discription:[${info}服务接口]</p>
 * Created on ${date}
 * @author ${uname}
 */
public interface I${className}Service {
	/** 分页查询 */
	public Page<${className}> selectPage(${className} ${classNameLower},Page<${className}> page);
	
	/** 查询列表 */
	public List<${className}> selectList(${className} ${classNameLower});
	
	/** 通过条件查询 */
	public ${className} selectOneByCondition(${className} ${classNameLower});
	
	/** 通过ID编号查询 */
	public ${className} selectById(String id);
	
	/** 添加 */
	public ${className} insert(${className} ${classNameLower});
	
	/** 修改 */
	public void update(${className} ${classNameLower});
	
	
}
