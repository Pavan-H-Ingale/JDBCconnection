package facebookReg;
import java.sql.*;
import java.util.*;
public class facebook {

	public static void main(String[] args) throws Exception
	{
		Class.forName("org.postgresql.Driver");
		
		Connection c1=DriverManager.getConnection("jdbc:postgresql://localhost:5432/facebook","postgres","root");
		
		Scanner sc=new Scanner(System.in);
		String ans;
		do {
	
		System.out.println("-----------------------------------------WELCOME TO FACEBOOOK !---------------------------------------------");
		System.out.println("Enter your choice!");
		System.out.println("\n1.Registeration\n2.Login\n3.Forgot Password \n4.Exist");
		int choice=sc.nextInt();
		switch(choice) {
		case 1:
			System.out.println("-----------------------FACEBOOK REGISTERATION FORM-----------------------------");
			String user,email,pass,cpass, contact;
			
			System.out.println("Enter the Username:");
			user=sc.next();
			
			String flag;
			do {
			
				System.out.println("Enter the Email:");
				email=sc.next();
				if(email.endsWith("@gmail.com")) {
					flag="1";
				}
				else {
					System.out.println("Enter valid Email ID----!");
					flag="0";
				}
				
				}while(flag.equals("0"));
				
			
			String correct;
			do {
				System.out.println("Enter the Contact Number:");
				contact=sc.next();
				
				if(contact.matches("\\d{10}")) {
					correct="1";
				}
				
				else {
					System.out.println("Enter the valid contact Number");
					correct="0";
				}
				}while(correct.equals("0"));
			

			
			System.out.println("Enter the Password:");
			pass=sc.next();
			System.err.println("Enter the confirmpassword:");
			cpass=sc.next();
			
			if(pass.equals(cpass)) {
			PreparedStatement p=c1.prepareStatement("insert into registration (username,email,passward,confirmpassword,contact)values('"+user+"','"+email+"','"+pass+"','"+cpass+"','"+contact+"')");
			p.executeUpdate();
			System.out.println("********************Registeration successful***************************");
			}
			else {
				System.out.println("Please cheack the passward and confirmpassward are not matched-------!!!");
			}
			break ;
		
			
			
			
		case 2:
			System.out.println("----------------------------------FACEBOOK LOGIN------------------------------------");
			String username,password;
			System.out.println("Enter the username:");
			username=sc.next();
			System.out.println("Enter the Passward:");
			password=sc.next();
		
			PreparedStatement p=c1.prepareStatement("select * from  registration where username='"+username+"' and passward='"+password+"' ");
			ResultSet rs=p.executeQuery();
			if(rs.next()) {
				System.out.println("Login successfull");
			}
			else {
				System.out.println("Login are fail--!");
				System.out.println("Please register first---!");
			}

			
			break;
			
			
			
			
		case 3:
			System.out.println("----------------------------------FACEBOOK FORGOT PASSWORD------------------------------------");
			String us,pa,con;
			
			System.out.println("Enter the username");
			us=sc.next();
			PreparedStatement q=c1.prepareStatement("select * from  registration where username='"+us+"' ");
			ResultSet s=q.executeQuery();
			if(s.next()) {
				
				System.out.println("Enter the contact");
				con=sc.next();
				System.out.println("Enter the New passward");
				pa=sc.next();
				
				PreparedStatement ps=c1.prepareStatement("update registration set username='"+us+"',passward='"+pa+"'where contact='"+con+"' ");
				ps.executeUpdate();

			}
			else {
				System.out.println("Username are not found");
				System.out.println("Please Register first---!");
			}
			break;
			
			
			
			
		case 4:
			String  s1="yes"; 
			System.out.println("IF you have any query  theen enter yes ");
			String r = sc.next();
			System.out.println("What is your Question? ");
			
			if(s1.equals("r")) {
				System.out.println("How can i help you?");
				System.out.println("What is your Question? please type your Question ");
				String v=sc.next();
				System.out.println("I will give you reply in few time later please wait!");
			}
			else {
				System.out.println("------------------------------EXIT-----------------------------------");
			}
			break;
			
		 default:
	        System.out.println("Wrong choise----!");
	        break;
		}
		
		
		System.out.println("If you want to continue then enter yes");
		ans=sc.next();
			
		}while(ans.equals("yes"));
		
		System.out.println("-------------------THANK YOU FOR VISITING--!   SEE YOU AGAIN--!!!----------------");
		System.out.println("------------------------------EXIT-----------------------------------");
		
	}

}
