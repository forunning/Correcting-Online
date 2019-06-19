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
		// 2、获取sqlSession对象
		SqlSession openSession = sqlSessionFactory.openSession();
		try {
			// 3、获取接口的实现类对象
			//会为接口自动的创建一个代理对象，代理对象去执行增删改查方法
			UserMapper mapper = openSession.getMapper(UserMapper.class);
			Manager employee = mapper.getStudent(user);
			if(employee==null)
				employee = mapper.getTeacher(user);
			if(employee==null)
				employee = mapper.getManager(user);
			if(employee==null) {
				System.out.println(employee==null);
				map.put("code",400);
				map.put("msg","用户不存在");
				
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
					map.put("msg","密码错误");
				}
			}

		} finally {
			openSession.close();
			JSONArray jsonObj = JSONArray.fromObject(map);
			out.print(jsonObj);
		}
	}
	
	public void SaveCookie(HttpServletRequest request, HttpServletResponse response,String name,String password) {
            // 将用户名和密码加入到cookie中
            Cookie nameCookie = new Cookie("name", name);
            Cookie passwordCookie = new Cookie("password", password);
            //设置cookie的有效期 防止销毁
            nameCookie.setMaxAge(60*10);
            passwordCookie.setMaxAge(60*10);
            //将cookie发送给客户端保存
            response.addCookie(nameCookie);
            response.addCookie(passwordCookie);
	}
}
