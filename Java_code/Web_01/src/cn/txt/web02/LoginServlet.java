package cn.txt.web02;

public class LoginServlet  implements Servlet{

	@Override
	public void service(Request1 request,Response response) {
		System.out.println("LoginServlet");
		
		response.print("<html>");
		response.print("<head>");
		response.print("<title>");
		response.print("第一次响应");
		response.print("</title>");
		response.print("</head>");
		response.print("<body>");
		response.print("shsxt server终于回来了。。。。"+request.getparameterValue("uname"));
		response.print("</body>");
		response.print("</html>");
	}

}
