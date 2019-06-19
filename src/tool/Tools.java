package tool;

import java.text.ParseException;
import java.lang.reflect.Field;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Random;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import sun.misc.BASE64Encoder;

public class Tools {
	public static HashMap<String, Object> success = new HashMap<String, Object>();
	public static HashMap<String, Object> error = new HashMap<String, Object>();

	public static HashMap<String, Object> success(String msg) {
		success.put("msg", msg);
		return success;
	}

	public static HashMap<String, Object> error(String msg) {
		error.put("msg", msg);
		return error;
	}
	public static void main(String[] args) {
		JSONObject ja = JSONObject.fromObject(Tools.success("操作成功"));
		System.out.println(ja);
	}

}
