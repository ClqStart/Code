package cn.txt.web02;

public class RegisterServlet implements Servlet{

	@Override
	public void service(Request1 request,Response response) {
		System.out.println("RegisterServlet");
		response.print("注册成功");
		
	}

}
