package com.eoma.autocoding;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Random;

import org.apache.log4j.Logger;

import com.eoma.autocoding.common.Column;
import com.eoma.autocoding.common.Table;
import com.eoma.autocoding.utils.CamelCaseUtils;
import com.eoma.autocoding.utils.FileHelper;

import freemarker.template.Configuration;
import freemarker.template.Template;

public class Generator {
	private Logger logger = Logger.getLogger(this.getClass());
	private Properties properties = null;
	
	
	public static void main(String[] args) throws Exception{
		Generator g = new Generator();
		g.gen("temp_energy_relation","模板能量关系表","wang");
		g.gen("temp_ban_area","模板区域表","wang");
		System.out.println("模版文件生成完毕……");
	}


	
	
	
	public Generator() throws Exception{
		properties = new Properties();
		String fileDir = this.getClass().getClassLoader().getResource("generator.xml").getFile();
		properties.loadFromXML(new FileInputStream(fileDir));
	}

	public Table parseTable(String tableName) throws Exception{
		String driverName = properties.getProperty("jdbc.driver");
		String userName = properties.getProperty("jdbc.username");
		String userPwd = properties.getProperty("jdbc.password");
		String dbURL = properties.getProperty("jdbc.url");
		
		String catalog = properties.getProperty("jdbc.catalog");
		String schema = properties.getProperty("jdbc.schema");
		schema = schema == null ? "%" : schema;
		String column = "%";
		
		logger.debug("driver>>"+driverName);
		logger.debug("url>>"+dbURL);
		logger.debug("name>>"+userName);
		logger.debug("password>>"+userPwd);
		logger.debug("catalog>>"+catalog);
		logger.debug("schema>>"+schema);
		
		Class.forName(driverName);
		Connection conn = java.sql.DriverManager.getConnection(dbURL, userName, userPwd);
		DatabaseMetaData dmd = conn.getMetaData();
		
		ResultSet rs = dmd.getColumns(catalog, schema, tableName, column);
		List<Column> columns = new ArrayList<Column>();
		while (rs.next()) {
			Column c = new Column();
			
			c.setLabel(rs.getString("REMARKS"));

			String name = rs.getString("COLUMN_NAME");
			c.setName(CamelCaseUtils.toCamelCase(name));
			c.setDbName(name);
			
			String dbType = rs.getString("TYPE_NAME");
			String type = properties.getProperty(dbType);
			c.setDbType(dbType);
			c.setType(type == null ? "String" : type);
			
			c.setLength(rs.getInt("COLUMN_SIZE"));
			c.setDecimalDigits(rs.getInt("DECIMAL_DIGITS"));
			c.setNullable(rs.getBoolean("NULLABLE"));
			columns.add(c);
		}
		
		List<Column> pkColumns = new ArrayList<Column>();
		ResultSet pkrs = dmd.getPrimaryKeys(catalog, schema, tableName);
		while(pkrs.next()){
			Column c = new Column();
			String name = pkrs.getString("COLUMN_NAME");
			c.setName(CamelCaseUtils.toCamelCase(name));
			c.setDbName(name);
			pkColumns.add(c);
		}
		
		conn.close();
		
		Table t = new Table();
		
		String prefiex = properties.getProperty("tableRemovePrefixes");
		String name = tableName;
		if( prefiex != null && !"".equals(prefiex) ){
			name = tableName.split(prefiex)[0];
		}
		t.setName(CamelCaseUtils.toCamelCase(name));
		t.setDbName(tableName);
		t.setColumns(columns);
		t.setPkColumns(pkColumns);
		return t;
	}
	
	/**
	 * <p>Discription:[生成映射文件和实体类]</p>
	 * Created on 2015年2月4日
	 * @param tableName 要声称映射文件和实体类的表名称
	 * @throws Exception
	 */
	public void gen(String tableName,String info,String uname) throws Exception{
		Configuration cfg = new Configuration(Configuration.VERSION_2_3_21);
		
		String outRoot = properties.getProperty("outRoot");
		String basepackage = properties.getProperty("basepackage");
		//获取当前日期
		SimpleDateFormat sm_date = new SimpleDateFormat("yyyy年MM月dd日");
		SimpleDateFormat sm_year = new SimpleDateFormat("yyyy年");
		
        Map<String, Object> root = new HashMap<String, Object>();
        Random random = new Random();
		String suid = random.nextInt(1000000) + "" + new Date().getTime()+"L";	
        Table t = this.parseTable(tableName);
        root.put("table", t);
        root.put("suid", suid);
        root.put("uname", uname);
        root.put("info", info);
        root.put("className", t.getNameUpper());
        root.put("classNameLower", t.getName());
        root.put("package", basepackage);
        root.put("date", sm_date.format(new Date()));
        root.put("year", sm_year.format(new Date()));
		
		String templateDir = this.getClass().getClassLoader().getResource("templates").getPath();
		
		File tdf = new File(templateDir);
		List<File> files = FileHelper.findAllFile(tdf);
		
		for(File f: files){
			String parentDir = "";
			if( f.getParentFile().compareTo(tdf) != 0 ){
				parentDir = f.getParent().split("templates")[1];
			}
	        cfg.setClassForTemplateLoading(this.getClass(), "/templates" + parentDir);
	        
	        Template template = cfg.getTemplate(f.getName());
	        template.setEncoding("UTF-8");  
	        
	        String parentFileDir = FileHelper.genFileDir(parentDir, root);
	        parentFileDir = parentFileDir.replace(".", "/");
	        String file = FileHelper.genFileDir(f.getName(),root).replace(".ftl", ".java");
	        
	        
	        File newFile = FileHelper.makeFile(outRoot + parentFileDir + "/" + file);
	        Writer out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream( newFile ), "UTF-8"));  
	        template.process(root, out); 
	        logger.debug("已生成文件：" + outRoot + parentFileDir + "/" + file);
		}
	}

}
