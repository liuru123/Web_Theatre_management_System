package xupt.se.ttms.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebFilter(urlPatterns = "/employee/*")
public class AuthFilter implements Filter
{
    @Override
    public void init(FilterConfig arg0) throws ServletException
    {}

    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
            throws IOException, ServletException
    {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;
        String flag = (String) request.getSession().getAttribute("login_flag");
        if(flag == null || !flag.equalsIgnoreCase("ok"))
        {
            request.setAttribute("desc", "请登陆后访问!");
            request.getRequestDispatcher("/index.jsp").forward(request, response);
        }
        else
        {
            System.out.println(">>>>>>>>>>>权限正确");
            chain.doFilter(request, response);
        }
    }

    @Override
    public void destroy()
    {}

}