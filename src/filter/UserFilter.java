package filter;


import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Manager;

import java.io.IOException;

/**
 * Created by Administrator on 2017/7/11.
 */
@WebFilter(urlPatterns = { "/Student/*","/Teacher/*","/Manager/*" })
public class UserFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset=utf-8");

        
        // 1 获取serssion 校验用户信息
        HttpServletRequest request= (HttpServletRequest) req;
        HttpServletResponse response= (HttpServletResponse) resp;
        HttpSession session = request.getSession();
        String user =(String) session.getAttribute("user");
        System.out.println("lanjie"+(String)session.getAttribute("user"));
        // 2 判断user信息是否为空 ，空则跳转登录界面
        if (user==null||user.equals("")) {
            response.sendRedirect(request.getContextPath()+"/index.jsp");
            return;
        }
        // 3 user不为空 放行
        chain.doFilter(req, resp);
    }

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		
	}


}
