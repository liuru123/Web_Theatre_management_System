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
 * Servlet Filter implementation class Filter2
 */

public class Filter2 implements Filter
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
        String flag = (String) request1.getSession().getAttribute("login");
        if(flag != null && flag.equals("ok"))
        {
            System.out.println("----->right ok");
            chain.doFilter(request1, response1);
        }
        else
        {
            System.out.println("----->right no");
            request1.setAttribute("desc", "请登录!");
            RequestDispatcher rd = request1.getRequestDispatcher("./login_only.jsp");
            rd.forward(request1, response1);
        }
    }

}

/**
 * @see Filter#init(FilterConfig)
 */

