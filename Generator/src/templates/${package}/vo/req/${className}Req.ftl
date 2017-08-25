package ${package}.vo.req;

import java.io.Serializable;

/**
 * <p>Discription:[${info}实体类]</p>
 * Created on ${date}
 * @author ${uname}
 */
public class ${className}Req implements Serializable {

	private static final long serialVersionUID = 1L;

<#list table.columns as column>
	/**
	 * ${column.label}
	 */
	private ${column.type} ${column.name};
</#list>
	
	// setter and getter
<#list table.columns as column>
	public ${column.type} get${column.nameUpper}(){
		return ${column.name};
	}
	
	public void set${column.nameUpper}(${column.type} ${column.name}){
		this.${column.name} = ${column.name};
	}
</#list>
}
