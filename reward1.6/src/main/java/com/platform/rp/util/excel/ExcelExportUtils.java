package com.platform.rp.util.excel;

import java.util.List;

import org.apache.commons.lang.math.NumberUtils;
import org.apache.commons.lang3.CharUtils;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.platform.rp.util.StringUtils;
import com.platform.rp.util.excel.EColumn.EColumnType;

public class ExcelExportUtils {

	private final static float DEFAULT_ROW_HEIGHT = 24.5f;
	
	private static float DEFAULT_COLUMN_WIDTH = 8f;
	
	private static float LIMIT_COLUMN_WIDTH = 32f;
	
	private static float OPTIONAL_COLUMN_WIDTH = 0f;
	
	public static <T> XSSFWorkbook export(T t, List<T> list) {
		XSSFWorkbook xssfwk = (XSSFWorkbook) POIUtils.createWorkbook();
		XSSFSheet xssfSheet = xssfwk.createSheet();
		
		OPTIONAL_COLUMN_WIDTH = DEFAULT_COLUMN_WIDTH = xssfSheet.getDefaultColumnWidth();
		
		List<EColumnVo> columnVos = EColumnUtils.parse(t.getClass());

		render(xssfSheet, columnVos, list);
		
		return xssfwk;
	}

	public static  void buildExcleColumn(List<EColumnVo> columnVos, String title, String name) {
		EColumnVo vo = new EColumnVo();
		columnVos.add(vo);
		vo.setTitle(title);
		vo.setName(name);
		vo.setWidth(name.length()*50);
		vo.setType(EColumnType.TYPE_STRING);
		vo.setIndex(columnVos.size());
	}
	
	public static <T> XSSFWorkbook export(List<EColumnVo> columnVos, List<T> list) {
		XSSFWorkbook xssfwk = (XSSFWorkbook) POIUtils.createWorkbook();
		XSSFSheet xssfSheet = xssfwk.createSheet();
		
		OPTIONAL_COLUMN_WIDTH = DEFAULT_COLUMN_WIDTH = xssfSheet.getDefaultColumnWidth();

		render(xssfSheet, columnVos, list);
		
		return xssfwk;
	}
	public static <T> void render(XSSFSheet xssfSheet, List<EColumnVo> columnVos, List<T> list){
		doRenderHead(xssfSheet, columnVos);
		
		doRenderBody(xssfSheet, columnVos, list);
	}
	
	public static void doRenderHead(XSSFSheet xssfSheet, List<EColumnVo> columnVos) {
		XSSFRow xssfRow = xssfSheet.createRow(0);
		XSSFWorkbook xssfwk = xssfSheet.getWorkbook();
		
		xssfRow.setHeightInPoints(DEFAULT_ROW_HEIGHT);
		
		XSSFCellStyle style = ExcelUtils.createCellStyle(xssfwk);
		Font font = xssfwk.createFont();
		font.setBold(true);
		style.setFont(font);
//		style.setFillForegroundColor(IndexedColors.LIGHT_BLUE.getIndex());
//		style.setFillPattern(CellStyle.SOLID_FOREGROUND);
//		style.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
//		style.setFillPattern(CellStyle.SOLID_FOREGROUND);
		style.setFillForegroundColor(IndexedColors.SKY_BLUE.getIndex());
		style.setFillPattern(CellStyle.SOLID_FOREGROUND);
		style.setAlignment(CellStyle.ALIGN_CENTER);
		
		XSSFCell cell = null;
		for (EColumnVo ecVo : columnVos) {
			cell = xssfRow.createCell(ecVo.getIndex());
			cell.setCellValue(ecVo.getTitle());
			cell.setCellStyle(style);
		}
		System.out.println(xssfSheet.getDefaultColumnWidth());
	}
	
	public static <T> void doRenderBody(XSSFSheet xssfSheet, List<EColumnVo> columnVos, List<T> list){
		int i = 1;
		for(T vo : list){
			XSSFRow xssfRow = xssfSheet.createRow(i);
			xssfRow.setHeightInPoints(DEFAULT_ROW_HEIGHT);
			
			for(EColumnVo ecVo : columnVos){
				setCellValue(ecVo, xssfRow, vo);
			}
			i++;
		}
	}
	
	private static <T> void setCellValue(EColumnVo ecVo, XSSFRow xssfRow, T t){
		Object o = EColumnUtils.getValue(t, ecVo.getName());
		XSSFCell cell = xssfRow.createCell(ecVo.getIndex());
		XSSFWorkbook xssfwk = xssfRow.getSheet().getWorkbook();
		XSSFCellStyle style = ExcelUtils.createCellStyle(xssfwk);
		Font font = xssfwk.createFont();
		style.setFont(font);
		
		cell.setCellStyle(style);
		
		if(o == null)
			return ;
		
		if(ecVo.getType().equals(EColumnType.TYPE_INT)){
			cell.setCellValue(NumberUtils.toInt(o.toString()));
//			cell.setCellType(Cell.CELL_TYPE_NUMERIC);
		}else if(ecVo.getType().equals(EColumnType.TYPE_DOUBLE)){
			cell.setCellValue(NumberUtils.toDouble(o.toString()));
//			cell.setCellType(Cell.CELL_TYPE_STRING);
		}else{
			cell.setCellValue(o.toString());
		}
		
//		setColumnWidth(cell);
	}
	
	private static void setColumnWidth(XSSFCell cell){
		String cellValue = POIUtils.getCellString(cell);
		if (StringUtils.isEmpty(cellValue)) 
			return ;
		
		float width = getColumnWidth(cell);
		if(width > LIMIT_COLUMN_WIDTH){
//			XSSFCellStyle  style = cell.getCellStyle();
//			style.setWrapText(true);
//			cell.setCellStyle(style);
			cell.getSheet().setColumnWidth(cell.getRowIndex(), (int)LIMIT_COLUMN_WIDTH);
		}else{
			cell.getSheet().setColumnWidth(cell.getRowIndex(), (int) width);
		}
	}

	private static float getColumnWidth(XSSFCell cell){
		String cellValue = POIUtils.getCellString(cell);
		if (StringUtils.isEmpty(cellValue)) 
			return DEFAULT_COLUMN_WIDTH;
		
		float width = 0f;
		for(char c : cellValue.toCharArray()){
			if(CharUtils.isAscii(c))
				width += 1;
			else 
				width += 2;
		}
		
		if(width < DEFAULT_COLUMN_WIDTH)
			width = DEFAULT_COLUMN_WIDTH;
		
		if(width > OPTIONAL_COLUMN_WIDTH)
			OPTIONAL_COLUMN_WIDTH = width;
		
		return width;
	}
}
