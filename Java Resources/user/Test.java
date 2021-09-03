package user;

public class Test {
	public static void main(String[] args) {
		
		UserDAO userDAO = new UserDAO();
		int result = 0;
		
		result = userDAO.login("gildong","123456");
		
		System.out.print(result);

	}
}

