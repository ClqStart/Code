package cn.txt.web02;

public class OtherServlet implements Servlet{

	@Override
	public void service(Request1 request, Response response) {
		System.out.println("其他测试页面");
		response.print("其他测试页面");
		
	}

}
