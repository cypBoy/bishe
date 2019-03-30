package util;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

/**
 * @author dell
 * @date 2019/03/30
 */
public class ResponseUtil {

	public static void write(HttpServletResponse response,Object o){
		try{
			response.setContentType("text/html;charset=utf-8");
			PrintWriter out=response.getWriter();
			out.println(o.toString());
			out.flush();
			out.close();
		}catch(Exception e){
			e.printStackTrace();
		}		
	}
}
