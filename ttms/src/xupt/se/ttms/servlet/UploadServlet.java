package xupt.se.ttms.servlet;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import xupt.se.ttms.model.User;

/* @WebServlet("/UploadServlet") */
public class UploadServlet extends HttpServlet
{
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        doPost(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        String paramName = "", paramValue = "";
        User user = new User();

        // 创建该对象
        DiskFileItemFactory dff = new DiskFileItemFactory();
        // 指定在内存中缓存数据大小,单位为byte
        dff.setSizeThreshold(1024000);
        // 创建上传对象
        ServletFileUpload sfu = new ServletFileUpload(dff);
        // sfu.setHeaderEncoding("UTF-8");
        // 指定单个上传文件的最大尺寸(单个文件大小不超过2M)
        sfu.setFileSizeMax(1024 * 1024 * 2);
        // 指定一次上传多个文件的总尺寸(总文件大小不超过6M)
        // sfu.setSizeMax(1024 * 1024 * 6);
        try
        {
            List<FileItem> uploaditems = sfu.parseRequest(request);
            for(FileItem fileItem : uploaditems)
            {
                // 对应表单中的控件的name
                paramName = fileItem.getFieldName();
                System.out.print(paramName + ":");

                // 2.如果是上传文件，则保存在服务器端应用根目录
                // 获得文件大小
                Long size = fileItem.getSize();
                // 获得全路径文件名
                String fileName = fileItem.getName();
                // 截取文件名
                fileName = fileName.substring(fileName.lastIndexOf("\\") + 1);
                System.out.println("\n文件名：" + fileName + "\t大小：" + size + "byte");

                // 将文件保存到指定的路径
                File file = new File(getServletContext().getRealPath("/") + fileName);
                fileItem.write(file);
                user.setUserImage(fileName);

            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }

        // 带着play对象转发到result.java页
        request.setAttribute("user", user);
        request.getRequestDispatcher("result.jsp").forward(request, response);
    }
}