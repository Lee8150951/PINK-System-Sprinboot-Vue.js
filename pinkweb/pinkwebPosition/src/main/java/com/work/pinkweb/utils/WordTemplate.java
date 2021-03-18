package com.work.pinkweb.utils; /**
 * Date: 2020年03月12日 上午9:00:13
 */


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.util.Units;
import org.apache.poi.xwpf.usermodel.BodyElementType;
import org.apache.poi.xwpf.usermodel.IBodyElement;
import org.apache.poi.xwpf.usermodel.PositionInParagraph;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableCell;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;

/**
 * 实现对docx文件中的文本、表格和图片进行替换 --模板支持对 {key}标签的替换
 */

public class WordTemplate {

	private XWPFDocument document;

	public XWPFDocument getDocument() {
		return document;
	}

	public void setDocument(XWPFDocument document) {
		this.document = document;
	}

	/**
	 * 初始化模板内容
	 */
	public WordTemplate(InputStream inputStream) throws IOException {
		document = new XWPFDocument(inputStream);
	}

	/**
	 * 将处理后的内容写入到输出流中
	 */
	public void write(OutputStream outputStream) throws IOException {
		document.write(outputStream);
	}

	/**
	 * 根据dataMap对word文件中的标签进行替换;
	 * ！！！！***需要注意dataMap的数据格式***！！！！
	 * 对于需要替换的普通标签数据标签（不需要循环）-----必须在dataMap中存储一个key为parametersMap的map，
	 * 来存储这些不需要循环生成的数据，比如：表头信息，日期，制表人等。
	 * 对于需要循环生成的表格数据------key自定义，value为 List<Map<String, Object>>
	 */
	public void replaceDocument(Map<String, Object> dataMap) {

		if (!dataMap.containsKey("parametersMap")) {
			System.out.println("数据源错误--数据源(parametersMap)缺失");
			return;
		}
		@SuppressWarnings("unchecked")
		Map<String, Object> parametersMap = (Map<String, Object>) dataMap
				.get("parametersMap");

		List<IBodyElement> bodyElements = document.getBodyElements();// 所有对象（段落+表格）
		int templateBodySize = bodyElements.size();// 标记模板文件（段落+表格）总个数

		int curT = 0;// 当前操作表格对象的索引
		int curP = 0;// 当前操作段落对象的索引,以便查询哪段段落查询不到资源
		for (int a = 0; a < templateBodySize; a++) {
			IBodyElement body = bodyElements.get(a);
			if (BodyElementType.TABLE.equals(body.getElementType())) {// 处理表格
				XWPFTable table = body.getBody().getTableArray(curT);

				List<XWPFTable> tables = body.getBody().getTables();
				table = tables.get(curT);
				if (table != null) {

					// 处理表格
					List<XWPFTableCell> tableCells = table.getRows().get(0).getTableCells();// 获取到模板表格第一行，用来判断表格类型
					String tableText = table.getText();// 表格中的所有文本
					if (tableText.indexOf("{") > -1) {
						// 没有查找到##{foreach标签，查找到了普通替换数据的{}标签，该表格只需要简单替换
						addTableInDocFooter(table, null, parametersMap, 1);
					} else {
						// 没有查找到任何标签，该表格是一个静态表格，仅需要复制一个即可。
						addTableInDocFooter(table, null, null, 0);
					}
					curT++;
				}
			} else if (BodyElementType.PARAGRAPH.equals(body.getElementType())) {// 处理段落
				// System.out.println("获取到段落");
				XWPFParagraph ph = body.getBody().getParagraphArray(curP);
				if (ph != null) {
					addParagraphInDocFooter(ph, null, parametersMap);
					curP++;
				}
			}

		}

	}

	/**
	 * 根据 模板表格 和 数据list 在word文档末尾生成表格
	 * flag=(0为静态表格，1为表格整体循环，2为表格内部行循环，3为表格不循环仅简单替换标签即可)
	 */
	public void addTableInDocFooter(XWPFTable templateTable, List<Map<String, Object>> list,
									Map<String, Object> parametersMap, int flag) {

		if (flag == 1) {
			//表格不循环仅简单替换标签
			List<XWPFTableRow> templateTableRows = templateTable.getRows();// 获取模板表格所有行
			for (XWPFTableRow newCreateRow:templateTableRows){
				System.out.println("获取新行");
				replaceTableRow(newCreateRow, parametersMap);

			}


		} else if (flag == 0) {
			//简单复制表格
			List<XWPFTableRow> templateTableRows = templateTable.getRows();// 获取模板表格所有行
			XWPFTable newCreateTable = document.createTable();// 创建新表格,默认一行一列
			for (int i = 0; i < templateTableRows.size(); i++) {
				XWPFTableRow newCreateRow = newCreateTable.createRow();
				CopyTableRow(newCreateRow, templateTableRows.get(i));// 复制模板行文本和样式到新行
			}
			newCreateTable.removeRow(0);// 移除多出来的第一行

		}

	}

	public void replaceTableRow(XWPFTableRow tableRow, Map<String, Object> parametersMap) {

		List<XWPFTableCell> tableCells = tableRow.getTableCells();
		for (XWPFTableCell xWPFTableCell : tableCells) {
			List<XWPFParagraph> paragraphs = xWPFTableCell.getParagraphs();
			for (XWPFParagraph xwpfParagraph : paragraphs) {
				replaceParagraph(xwpfParagraph, parametersMap);
			}
		}

	}




	/**
	 * 根据 模板段落 和 数据 在文档末尾生成段落
	 */
	public void addParagraphInDocFooter(XWPFParagraph templateParagraph,
										List<Map<String, String>> list, Map<String, Object> parametersMap) {


		// 替换段落数据

		replaceParagraph(templateParagraph, parametersMap);

	}



	/**
	 * 根据map替换段落元素内的{**}标签
	 */
	public void replaceParagraph(XWPFParagraph xWPFParagraph, Map<String, Object> parametersMap) {
		List<XWPFRun> runs = xWPFParagraph.getRuns();
		String xWPFParagraphText = xWPFParagraph.getText();
		String regEx = "\\{.+?\\}";
		Pattern pattern = Pattern.compile(regEx);
		Matcher matcher = pattern.matcher(xWPFParagraphText);//正则匹配字符串{****}

		if (matcher.find()) {
			// 查找到有标签才执行替换
			int beginRunIndex = xWPFParagraph.searchText("{", new PositionInParagraph()).getBeginRun();// 标签开始run位置
			int endRunIndex = xWPFParagraph.searchText("}", new PositionInParagraph()).getEndRun();// 结束标签
			StringBuffer key = new StringBuffer();

			if (beginRunIndex == endRunIndex) {
				// {**}在一个run标签内
				XWPFRun beginRun = runs.get(beginRunIndex);
				String beginRunText = beginRun.text();

				int beginIndex = beginRunText.indexOf("{");
				int endIndex = beginRunText.indexOf("}");
				int length = beginRunText.length();

				if (beginIndex == 0 && endIndex == length - 1) {
					// 该run标签只有{**}
					XWPFRun insertNewRun = xWPFParagraph.insertNewRun(beginRunIndex);
					insertNewRun.getCTR().setRPr(beginRun.getCTR().getRPr());
					// 设置文本
					key.append(beginRunText.substring(1, endIndex));
					//new
					String textString  = getValueBykey(key.toString(),parametersMap);
					if(textString.endsWith(".emf")||textString.endsWith(".wmf")||textString.endsWith(".pict")||
							textString.endsWith(".jpeg") || textString.endsWith(".jpg")||textString.endsWith(".png")||
							textString.endsWith(".dib")||textString.endsWith(".gif")||textString.endsWith(".tiff")||textString.endsWith(".eps")||
							textString.endsWith(".eps")||textString.endsWith(".bmp")||textString.endsWith(".wpg")) {

						int width=100;    //默认尺寸
						int height=100;
						//对你想要的图片设置长度和宽度
						if(textString.equals("file\\avator.jpg")) {
							width = 75;
							height = 75;
						}

						//加载图片
						addPicture(document, insertNewRun, textString, width, height);  //插入图片
					}else {
						insertNewRun.setText(textString); //插入文本
					}
					xWPFParagraph.removeRun(beginRunIndex + 1);
				} else {
					// 该run标签为**{**}** 或者 **{**} 或者{**}**，替换key后，还需要加上原始key前后的文本
					XWPFRun insertNewRun1 = xWPFParagraph.insertNewRun(beginRunIndex);
					XWPFRun insertNewRun = xWPFParagraph.insertNewRun(beginRunIndex);
					insertNewRun.getCTR().setRPr(beginRun.getCTR().getRPr());
					// 设置文本
					key.append(beginRunText.substring(beginRunText.indexOf("{")+1, beginRunText.indexOf("}")));
					//new
					String textString  = getValueBykey(key.toString(),parametersMap);
					if(textString.endsWith(".emf")||textString.endsWith(".wmf")||textString.endsWith(".pict")||
							textString.endsWith(".jpeg") || textString.endsWith(".jpg")||textString.endsWith(".png")||
							textString.endsWith(".dib")||textString.endsWith(".gif")||textString.endsWith(".tiff")||textString.endsWith(".eps")||
							textString.endsWith(".eps")||textString.endsWith(".bmp")||textString.endsWith(".wpg")) {

						int width=100;    //默认尺寸
						int height=100;
						//对你想要的图片设置长度和宽度
						if(textString.equals("file\\avator.jpg")) {
							width = 75;
							height = 75;
						}

						XWPFRun insertNewRun2 = xWPFParagraph.insertNewRun(beginRunIndex);
						insertNewRun1.setText(beginRunText.substring(endIndex + 1));

						//加载图片
						addPicture(document, insertNewRun, textString, width, height);  //插入图片

						//插入前半段文字

						insertNewRun2.setText(beginRunText.substring(0, beginIndex));
						xWPFParagraph.removeRun(beginRunIndex + 3);
						///new
					} else{
						textString=beginRunText.substring(0, beginIndex) + getValueBykey(key.toString(),parametersMap)
								+ beginRunText.substring(endIndex + 1);
						insertNewRun.setText(textString);

						xWPFParagraph.removeRun(beginRunIndex + 1);
					}

				}

			}else {
				// {**}被分成多个run
				//先处理起始run标签,取得第一个{key}值
				XWPFRun beginRun = runs.get(beginRunIndex);
				String beginRunText = beginRun.text();
				int beginIndex = beginRunText.indexOf("{");
				if (beginRunText.length()>1  ) {
					key.append(beginRunText.substring(beginIndex+1));
				}
				ArrayList<Integer> removeRunList = new ArrayList<>();//需要移除的run
				//处理中间的run
				for (int i = beginRunIndex + 1; i < endRunIndex; i++) {
					XWPFRun run = runs.get(i);
					String runText = run.text();
					key.append(runText);
					removeRunList.add(i);
				}

				// 获取endRun中的key值
				XWPFRun endRun = runs.get(endRunIndex);
				String endRunText = endRun.text();
				int endIndex = endRunText.indexOf("}");
				//run中**}或者**}**
				if (endRunText.length()>1 && endIndex!=0) {
					key.append(endRunText.substring(0,endIndex));
				}



				//*******************************************************************
				//取得key值后替换标签

				//先处理开始标签
				if (beginRunText.length()==2 ) {
					// run标签内文本{
					XWPFRun insertNewRun = xWPFParagraph.insertNewRun(beginRunIndex);
					insertNewRun.getCTR().setRPr(beginRun.getCTR().getRPr());
					// 设置文本
					insertNewRun.setText(getValueBykey(key.toString(),parametersMap));
					xWPFParagraph.removeRun(beginRunIndex + 1);//移除原始的run
				}else {
					// 该run标签为**{**或者 {** ，替换key后，还需要加上原始key前的文本
					XWPFRun insertNewRun = xWPFParagraph.insertNewRun(beginRunIndex);
					insertNewRun.getCTR().setRPr(beginRun.getCTR().getRPr());
					// 设置文本
					String textString=beginRunText.substring(0,beginRunText.indexOf("{"))+getValueBykey(key.toString(),parametersMap);
					// 判断是否为图片后缀，如果是加载图片，否则替换文本
					if(textString.endsWith(".emf")||textString.endsWith(".wmf")||textString.endsWith(".pict")||
							textString.endsWith(".jpeg") || textString.endsWith(".jpg")||textString.endsWith(".png")||
							textString.endsWith(".dib")||textString.endsWith(".gif")||textString.endsWith(".tiff")||textString.endsWith(".eps")||
							textString.endsWith(".eps")||textString.endsWith(".bmp")||textString.endsWith(".wpg")) {

						int width=100;    //默认尺寸
						int height=100;
						//对你想要的图片设置长度和宽度
						if(textString.equals("file\\avator.jpg")) {
							width = 75;
							height = 75;
						}

						//加载图片
						addPicture(document, insertNewRun, textString, width, height);  //插入图片
					}else {
						insertNewRun.setText(textString); //插入文本
					}
					xWPFParagraph.removeRun(beginRunIndex + 1);//移除原始的run
				}



				//处理结束标签
				if (endRunText.length()==1 ) {
					// run标签内文本只有}
					XWPFRun insertNewRun = xWPFParagraph.insertNewRun(endRunIndex);
					insertNewRun.getCTR().setRPr(endRun.getCTR().getRPr());
					// 设置文本
					insertNewRun.setText("");
					xWPFParagraph.removeRun(endRunIndex + 1);//移除原始的run

				}else {
					// 该run标签为**}**或者 }** 或者**}，替换key后，还需要加上原始key后的文本
					XWPFRun insertNewRun = xWPFParagraph.insertNewRun(endRunIndex);
					insertNewRun.getCTR().setRPr(endRun.getCTR().getRPr());
					// 设置文本
					String textString=endRunText.substring(endRunText.indexOf("}")+1);
					insertNewRun.setText(textString);
					xWPFParagraph.removeRun(endRunIndex + 1);//移除原始的run
				}

				//处理中间的run标签
				for (int i = 0; i < removeRunList.size(); i++) {
					XWPFRun xWPFRun = runs.get(removeRunList.get(i));//原始run
					XWPFRun insertNewRun = xWPFParagraph.insertNewRun(removeRunList.get(i));
					insertNewRun.getCTR().setRPr(xWPFRun.getCTR().getRPr());
					insertNewRun.setText("");
					xWPFParagraph.removeRun(removeRunList.get(i) + 1);//移除原始的run

				}

			}// 处理${**}被分成多个run

			replaceParagraph( xWPFParagraph, parametersMap);

		}//if 有标签

	}

	/**
	 * 复制表格行XWPFTableRow格式
	 *
	 * @param target
	 *            待修改格式的XWPFTableRow
	 * @param source
	 *            模板XWPFTableRow
	 */
	private void CopyTableRow(XWPFTableRow target, XWPFTableRow source) {// 复制模板列

		int tempRowCellsize = source.getTableCells().size();// 模板行的列数
		for (int i = 0; i < tempRowCellsize - 1; i++) {
			target.addNewTableCell();// 为新添加的行添加与模板表格对应行行相同个数的单元格
		}
		// 复制行的样式
		target.getCtRow().setTrPr(source.getCtRow().getTrPr());
		// 复制单元格
		for (int i = 0; i < target.getTableCells().size(); i++) {
			copyTableCell(target.getCell(i), source.getCell(i));
		}
	}

	/**
	 * 复制单元格XWPFTableCell格式
	 * @param newTableCell  新创建的的单元格
	 * @param templateTableCell  模板单元格
	 */
	private void copyTableCell(XWPFTableCell newTableCell, XWPFTableCell templateTableCell) {
		// 列属性
		newTableCell.getCTTc().setTcPr(templateTableCell.getCTTc().getTcPr());
		// 删除目标 targetCell 所有文本段落
		for (int pos = 0; pos < newTableCell.getParagraphs().size(); pos++) {
			newTableCell.removeParagraph(pos);
		}
		// 添加新文本段落
		for (XWPFParagraph sp : templateTableCell.getParagraphs()) {
			XWPFParagraph targetP = newTableCell.addParagraph();
			copyParagraph(targetP, sp);
		}
	}

	/**
	 * 复制文本段落XWPFParagraph格式
	 * @param newParagraph  新创建的的段落
	 * @param templateParagraph   模板段落
	 */
	private void copyParagraph(XWPFParagraph newParagraph, XWPFParagraph templateParagraph) {
		// 设置段落样式
		newParagraph.getCTP().setPPr(templateParagraph.getCTP().getPPr());
		// 添加Run标签
		for (int pos = 0; pos < newParagraph.getRuns().size(); pos++) {
			newParagraph.removeRun(pos);

		}
		for (XWPFRun s : templateParagraph.getRuns()) {
			XWPFRun targetrun = newParagraph.createRun();
			CopyRun(targetrun, s);
		}

	}

	/**
	 * 复制文本节点run
	 * @param newRun 新创建的的文本节点
	 * @param templateRun 模板文本节点
	 */
	private void CopyRun(XWPFRun newRun, XWPFRun templateRun) {
		newRun.getCTR().setRPr(templateRun.getCTR().getRPr());
		// 设置文本
		newRun.setText(templateRun.text());
	}


	/**
	 * 根据参数parametersMap对表格的一行进行标签的替换
	 * @param tableRow   表格行
	 * @param parametersMap  参数map
	 */


	/**
	 * 根据map替换表格中的{key}标签
	 *
	 */
	public void replaceTable(XWPFTable xwpfTable,Map<String, Object> parametersMap){
		List<XWPFTableRow> rows = xwpfTable.getRows();
		for (XWPFTableRow xWPFTableRow : rows ) {
			List<XWPFTableCell> tableCells = xWPFTableRow.getTableCells();
			for (XWPFTableCell xWPFTableCell : tableCells ) {
				List<XWPFParagraph> paragraphs2 = xWPFTableCell.getParagraphs();
				for (XWPFParagraph xWPFParagraph : paragraphs2) {
					replaceParagraph(xWPFParagraph, parametersMap);
				}
			}
		}

	}

	private String getValueBykey(String key, Map<String, Object> map) {
		String returnValue="";
		if (key != null) {
			try {
				returnValue=map.get(key)!=null ? map.get(key).toString() : "";
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println("key:"+key+"***"+e);
				returnValue="";
			}

		}
		return returnValue;
	}
	/**
	 * 插入图片
	 *
	 */
	public static void addPicture(XWPFDocument doc,XWPFRun r,String imgFile,int picWidth,int picHeight) {

		int format = 0;
		if(imgFile.endsWith(".emf")) format = XWPFDocument.PICTURE_TYPE_EMF;
		else if(imgFile.endsWith(".wmf")) format = XWPFDocument.PICTURE_TYPE_WMF;
		else if(imgFile.endsWith(".pict")) format = XWPFDocument.PICTURE_TYPE_PICT;
		else if(imgFile.endsWith(".jpeg") || imgFile.endsWith(".jpg")) format = XWPFDocument.PICTURE_TYPE_JPEG;
		else if(imgFile.endsWith(".png")) format = XWPFDocument.PICTURE_TYPE_PNG;
		else if(imgFile.endsWith(".dib")) format = XWPFDocument.PICTURE_TYPE_DIB;
		else if(imgFile.endsWith(".gif")) format = XWPFDocument.PICTURE_TYPE_GIF;
		else if(imgFile.endsWith(".tiff")) format = XWPFDocument.PICTURE_TYPE_TIFF;
		else if(imgFile.endsWith(".eps")) format = XWPFDocument.PICTURE_TYPE_EPS;
		else if(imgFile.endsWith(".bmp")) format = XWPFDocument.PICTURE_TYPE_BMP;
		else if(imgFile.endsWith(".wpg")) format = XWPFDocument.PICTURE_TYPE_WPG;
		else {
			System.err.println("Unsupported picture: " + imgFile +
					". Expected emf|wmf|pict|jpeg|png|dib|gif|tiff|eps|bmp|wpg");
		}
		try {
			r.addPicture(new FileInputStream(imgFile), format, imgFile, Units.toEMU(picWidth), Units.toEMU(picHeight));
		} catch (InvalidFormatException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
