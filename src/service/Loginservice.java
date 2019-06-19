package service;

import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import bean.Manager;
import bean.Student;
import bean.Teacher;
import dao.UserMapper;
import net.sf.json.JSONArray;
import tool.SessionFactory;

public class Loginservice {
	public void checkUser(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		String user = request.getParameter("user");
		String pass = request.getParameter("pass");
		String remember = request.getParameter("remember");
		System.out.println(user+":"+pass+":"+remember);
		PrintWriter out = response.getWriter();
		HashMap<String, Object> map = new HashMap<String, Object>();
		
		
		SqlSessionFactory sqlSessionFactory = SessionFactory.getSqlSessionFactory();
		// 2����ȡsqlSession����
		SqlSession openSession = sqlSessionFactory.openSession();
		try {
			// 3����ȡ�ӿڵ�ʵ�������
			//��Ϊ�ӿ��Զ��Ĵ���һ��������󣬴������ȥִ����ɾ�Ĳ鷽��
			UserMapper mapper = openSession.getMapper(UserMapper.class);
			Manager employee = mapper.getStudent(user);
			if(employee==null)
				employee = mapper.getTeacher(user);
			if(employee==null)
				employee = mapper.getManager(user);
			if(employee==null) {
				System.out.println(employee==null);
				map.put("code",400);
				map.put("msg","�û�������");
				
			}else {
				if(employee.getPassword().equals(pass)) {
					map.put("code",200);
					if(employee instanceof Teacher)
						map.put("msg","1");
					else if(employee instanceof Student)
						map.put("msg","2");
					else
						map.put("msg","0");
					
					System.out.println(request.getParameter("remember"));
					
					if(request.getParameter("remember").equals("true")) {
						SaveCookie(request, response, user, pass);
					}

					HttpSession session=request.getSession();
					session.setAttribute("user", employee.getId());
				}else {
					map.put("code",400);
					map.put("msg","�������");
				}
			}

		} finally {
			openSession.close();
			JSONArray jsonObj = JSONArray.fromObject(map);
			out.print(jsonObj);
		}
	}
	
	public void SaveCookie(HttpServletRequest request, HttpServletResponse response,String name,String password) {
            // ���û�����������뵽cookie��
            Cookie nameCookie = new Cookie("name", name);
            Cookie passwordCookie = new Cookie("password", password);
            //����cookie����Ч�� ��ֹ����
            nameCookie.setMaxAge(60*10);
            passwordCookie.setMaxAge(60*10);
            //��cookie���͸��ͻ��˱���
            response.addCookie(nameCookie);
            response.addCookie(passwordCookie);
	}
}
