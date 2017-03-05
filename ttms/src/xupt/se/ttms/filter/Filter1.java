package xupt.se.ttms.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet Filter implementation class Filter1
 */

public class Filter1 implements Filter
{

    /**
     * Default constructor.
     */
    public void init(FilterConfig fConfig) throws ServletException
    {

    }

    /**
     * @see Filter#destroy()
     */
    public void destroy()
    {
        // TODO Auto-generated method stub
    }

    /**
     * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
     */
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException
    {
        HttpServletRequest request1 = (HttpServletRequest) request;
        HttpServletResponse response1 = (HttpServletResponse) response;
        String flag = (String) request1.getSession().getAttribute("admin");
        if(flag != null && flag.equals("ok"))
        {
            System.out.println("----->right ok");
            chain.doFilter(request1, response1);
        }
        else
        {
            String desc = "请登录!";
            System.out.println("----->right no");
            request1.setAttribute("desc", desc);
            RequestDispatcher rd = request1.getRequestDispatcher("./login_only.jsp");
            rd.forward(request1, response1);
        }
    }
}

/**
 * @see Filter#init(FilterConfig)
 */

