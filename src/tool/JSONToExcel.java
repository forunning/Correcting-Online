package tool;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import java.io.*;
import java.util.Set;

public class JSONToExcel {
	JSONArray jsonarr;
	public JSONToExcel(JSONArray jsonarr) {
		// TODO Auto-generated constructor stub
		this.jsonarr=jsonarr;
	}
	public InputStream getInputStream() throws Exception {
		Set<String> keys = null;
        // 创建HSSFWorkbook对象
        HSSFWorkbook wb = new HSSFWorkbook();
        // 创建HSSFSheet对象
        HSSFSheet sheet = wb.createSheet("sheet1");


        int roleNo = 0;
        int rowNo = 0;
		for(int i=0;i<jsonarr.size();i++) {
			JSONObject jsonObject = (JSONObject) jsonarr.get(i);
			 // 创建HSSFRow对象
            HSSFRow row = sheet.createRow(roleNo++);
            // 创建HSSFCell对象
            if (keys == null) {
                //标题
                keys = jsonObject.keySet();
                for (String s : keys) {
                    HSSFCell cell = row.createCell(rowNo++);
                    cell.setCellValue(s);
                }
                rowNo = 0;
                row = sheet.createRow(roleNo++);
            }

            for (String s : keys) {
                HSSFCell cell = row.createCell(rowNo++);
                cell.setCellValue(jsonObject.getString(s));
            }
            rowNo = 0;
            System.out.println(rowNo);
		}

		ByteArrayOutputStream bos=null;
		InputStream is=null;
		try {
			String filename="1.xls";
			bos=new ByteArrayOutputStream();
			wb.write(bos);
			byte[] barry=bos.toByteArray();
			is=new ByteArrayInputStream(barry);
			
		}catch (Exception e) {
			// TODO: handle exception
		}finally {
			bos.flush();
			bos.close();
			wb.close();
			return is;
		}
		
		
        // 输出Excel文件
//        FileOutputStream output = new FileOutputStream("d://target.xls");
//        wb.write(output);
//        wb.close();
//        output.flush();
//        output.close();
	}
	public static void main(String[] args) throws Exception {
		String json="[{\"_id\":\"13001009656\",\"name\":\"test1\"},{\"_id\":\"13001022163\",\"name\":\"test2\"}]";
//		JSONToExcel excel=new JSONToExcel(json);
//		excel.getExcel();
	}
}
