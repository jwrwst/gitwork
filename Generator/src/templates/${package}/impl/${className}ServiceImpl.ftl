package ${package}.impl;
import javax.annotation.Resource;
import java.util.List;
import com.jjb.common.framework.Page;
import org.springframework.stereotype.Service;
import ${package}.dao.I${className}DAO;
import ${package}.model.${className};
import ${package}.api.I${className}Service;
import com.jjb.common.util.IdGen;

/**
 * <p>Discription:[${info}服务接口实现类]</p>
 * Created on ${date}
 * @author ${uname}
 */
@Service("${classNameLower}Service")
public class ${className}ServiceImpl implements I${className}Service {
	@Resource
	private I${className}DAO ${classNameLower}DAO;
	
	@Override
	public Page<${className}> selectPage(${className} ${classNameLower},Page<${className}> page){
		List<${className}> ${classNameLower}s = ${classNameLower}DAO.selectListByPage(${classNameLower}, page);
		Long count = ${classNameLower}DAO.selectCount(${classNameLower});
		if(page==null){
			page = new Page<${className}>();
		}
		page.setCount(count);
		page.setList(${classNameLower}s);
		return page;
	}
	
	@Override
	public List<${className}> selectList(${className} ${classNameLower}){
		List<${className}> ${classNameLower}s = ${classNameLower}DAO.selectListByPage(${classNameLower}, null);
		return ${classNameLower}s;
	}

	@Override
	public ${className} selectOneByCondition(${className} ${classNameLower}){
		return ${classNameLower}DAO.selectOne(${classNameLower});
	}
	
	@Override
	public ${className} selectById(String id) {
		return ${classNameLower}DAO.selectById(id);
	}
	
	@Override
	public ${className} insert(${className} ${classNameLower}) {
		${classNameLower}.setId(IdGen.randomString());
		${classNameLower}DAO.insert(${classNameLower});
		return ${classNameLower};
	}
	
	@Override
	public void update(${className} ${classNameLower}) {
		${classNameLower}DAO.update(${classNameLower});
	}
	
	
	
}