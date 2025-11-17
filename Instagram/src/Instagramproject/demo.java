package Instagramproject;

import java.sql.*;
import java.util.Scanner;

public class demo {

	public static void main(String[] args) throws Exception
	{
		
		//	JDBC connection for postgresql Vender 
		Class.forName("org.postgresql.Driver");
		Connection c1=DriverManager.getConnection("jdbc:postgresql://localhost:5432/instagram","postgres","root");
		System.out.println("----------------------------Registeration of Instagram----------------------------------");
		Scanner sc=new Scanner(System.in);
		String ans;
		
		
		do {
		System.out.println("\n 1.Registeration \n 2.Login \n 3.ForgotPass \n 4.Exit");
		System.out.println("Please enter your choice");
		int choice=sc.nextInt();
		switch(choice) {
//		Logic behind registeration and store in the   database-------------------------------------------------------------------
		case 1:
		
		System.out.println("Enter the Name:");
		String a=sc.next();
		System.out.println("Enter the Email:");
		String b=sc.next();
		if(b.endsWith("@gmail.com")) {
			String p;
			do {
			
			System.out.println("Enter the Password");
			String c=sc.next();
			System.out.println("Enter the confirm Password");
			String d=sc.next();
			if(c.equals(d)) {

			PreparedStatement ps=c1.prepareStatement("Insert into registeration(name,email,pass,cpass)values('"+a+"','"+b+"','"+c+"','"+d+"')");
			ps.executeUpdate();
			
			System.out.println("-------------Registeration successful-------------------------");
			break;
			}
			else {
				System.out.println("password is not match");
			}
			System.out.println("please Enter ok to enter Correct Password ");
			 p=sc.next();
			}while(p.equals("ok"));
			
		}
		else {
			System.out.println("Invalid Email");
			
		}
		break;
		
		
		
		
//		logic behind the login and featch   data into database and compare  to user data then login
		
		
		case 2:{
			System.out.println("-----------------------------Login Form-------------------------------------");
			System.out.println("Enter the Email:");
			String x=sc.next();
			System.out.println("Enter the password:");
			String  y=sc.next();
			
			PreparedStatement p1=c1.prepareStatement("select * from registeration where email='"+x+"' or pass='"+y+"'");
			ResultSet rs=p1.executeQuery();
			if(rs.next()) {
			System.out.println("---------------------------Login Successfull----------------------");
			}
			else {
				System.out.println("Login Fail --! ");
				System.out.println("Please register first-----!");
			}
		}
		break;
		
		
		
//forgot password logic is in this case-------------------------------------------------------------------------------------------
		
		
		case 3:
			System.out.println("---------------------------forgot password-----------------------------");
			String mail ,pass,cpass;
			String reply;
			do {
			System.out.println("Enter the Email:");
			mail=sc.next();
			PreparedStatement p1=c1.prepareStatement("select * from registeration where email='"+mail+"' ");
			ResultSet rs=p1.executeQuery();
	
			if(rs.next()) {
				String r;
				do {
				System.out.println("Enter the New password");
				pass=sc.next();
				System.out.println("Enter the New confirm passward");
				cpass=sc.next();
				if(pass.equals(cpass)){
				
				PreparedStatement ps=c1.prepareStatement("update registeration set pass='"+pass+"',cpass='"+cpass+"'where email='"+mail+"' ");
				ps.executeUpdate();
				System.out.println("your Password is updated---!");
				break;
				
				
				}
				else {
					System.out.println("Password is not match please enter again");
				}
				System.out.println("do you want  to continue please enter yes");
				r=sc.next();
				}while(r.equals("yes"));
				
				break;
			}
			else {
				System.out.println("Email are not found");
				System.out.println("Please Register first---!");
			
			}
			System.out.println("do you want  to continue please enter ok");
			reply=sc.next();
			}while(reply.equals("ok"));
		
		System.out.println("-----------------------------------Forgot password successfully----------------------------");
		
		
		break;
		
		
		
		
		
		//this is last exit logic ----------------------------------------------------------------------------------------------
		
		
		case 4:
			
			System.out.println("----------------------------------Exit---------------------------------------");
			 
			System.out.println("IF you have any query  then enter yes ");
			String r = sc.next();
			System.out.println("What is your Question? ");
			
			if(r.equals("yes")) {
			
				System.out.println("What is your Question? please type your Question ");
				String v=sc.next();
				System.out.println("I will give you reply in few time later please wait!");
				
				System.out.println("----------------------Thank you--!---------------------------------------------");
				System.out.println("============Wisit Again---!===============");
				
			}
			else {
				System.out.println("----------------------Thank you--!---------------------------------------------");
				System.out.println("============Wisit Again---!===============");
				System.out.println("------------------------------EXIT-----------------------------------");
			}
			break;
			
		 default:
	        System.out.println("Wrong choise----!");
	        break;
		}
		
		System.out.println("Do you want to continue then please enter yes ");
		ans=sc.next();
		}while(ans.equals("yes"));
	
		
		
		
		
	}

}
