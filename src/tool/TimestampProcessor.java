package tool;

import java.text.SimpleDateFormat;
import java.util.Date;
 
import net.sf.json.JsonConfig;
import net.sf.json.processors.JsonValueProcessor;
 
/**
 * ��timestampת����json
 */
public class TimestampProcessor implements JsonValueProcessor{
 
	private String format = "yyyy-MM-dd";//�Զ���ʱ���ʽ������ʽ
	public TimestampProcessor() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public TimestampProcessor(String format) {
		this.format = format;
	}
 
	public Object processArrayValue(Object arg0, JsonConfig arg1) {
		// TODO Auto-generated method stub
		return arg0;
	}
	
	/**
	 * ��������ֵ
	 *  str ��������ǵ�ǰ��Ҫ�����������
	 */
	public Object processObjectValue(String str, Object obj, JsonConfig arg2) {
		// TODO Auto-generated method stub
		String ret = "";
		if(obj instanceof java.sql.Timestamp){
			SimpleDateFormat sdf = new SimpleDateFormat(format);
			ret = sdf.format(((Date) obj).getTime());
		}
		return ret;
	}
 
}

