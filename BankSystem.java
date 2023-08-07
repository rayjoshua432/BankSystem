import java.awt.Color;
import java.awt.Font;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import java.util.StringTokenizer;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;

public class BankSystem {
	public static DecimalFormat df = new DecimalFormat("###,###.00");
	public static File f = null;
	public static FileWriter fWrite = null;
	public static FileReader fRead = null;
	public static Scanner read = null;
	public static StringTokenizer strToken;
	
	public static int k = 0, j = 0, i = 0, x = 0, ctr = 0, index = 0, capacity = 100, a, numOfAccs = 0;
	public static String hold ="", choice="", select="";
	public static String menu[] = {"ADMIN Login", "STAFF Login","ATM Service","Exit"},
			admin_menu[] = {"Create Account", "Delete Account", "Search Account", "List of All Accounts", "Edit Information", "Logs of Deleted Accounts", "Main Menu"},
			staff_menu[] = {"Check Account Information", "Deposit Cash", "Withdraw Cash", "Logs of User Accounts", "Main Menu"},
			gender_menu[] = {"Male", "Female", "LGBT", "Unknown"},
			acctype_menu[] = {"Savings Account", "Current Account   "},
			atmservice_menu[] = {"Withdraw Cash", "Main Menu"},
			fullname[] = new String[capacity],
			nat_id[] = new String[capacity],
			gender[] = new String[capacity],
			acc_type[] = new String[capacity],
			pincode[] = new String[capacity],
			balance[] = new String[capacity],
			acc_num[] = new String[capacity],
			deletiontime[] = new String[capacity],
			transaction[] = new String[capacity],
			transaction_time[] = new String[capacity];
	
	public static void Accounts() throws IOException {	
		fRead = new FileReader("Accounts.txt");
		read = new Scanner(fRead);
		hold = "";
		
		while(read.hasNext()) {
			hold += read.nextLine();
			i++;
		}
		read.close();
		fRead.close();
		
		strToken = new StringTokenizer(hold,"|");			
		a = 0;

		while(strToken.hasMoreTokens()) {
			fullname[a] = strToken.nextToken();
			nat_id[a] = strToken.nextToken();
			gender[a] = strToken.nextToken();
			acc_type[a] = strToken.nextToken();
			pincode[a] = strToken.nextToken();
			balance[a] = strToken.nextToken();
			acc_num[a] = strToken.nextToken();
			a++;
		}
	}
	
	public static void DeletedAccounts() throws IOException {	
		fRead = new FileReader("DeletedAccounts.txt");
		read = new Scanner(fRead);
		hold = "";
		
		while(read.hasNext()) {
			hold += read.nextLine();
			j++;
		}
		read.close();
		fRead.close();
		
		strToken = new StringTokenizer(hold,"|");			
		j = 0;

		while(strToken.hasMoreTokens()) {
			deletiontime[a] = strToken.nextToken();
			fullname[a] = strToken.nextToken();
			acc_num[a] = strToken.nextToken();
			balance[a] = strToken.nextToken();
			j++;
		}
	}
	
	public static void UserLogs() throws IOException {	
		fRead = new FileReader("UserTransactions.txt");
		read = new Scanner(fRead);
		hold = "";
		
		while(read.hasNext()) {
			hold += read.nextLine();
			k++;
		}
		read.close();
		fRead.close();
		
		strToken = new StringTokenizer(hold,"|");			
		k = 0;

		while(strToken.hasMoreTokens()) {
			transaction_time[a] = strToken.nextToken();
			transaction[a] = strToken.nextToken();
			acc_num[a] = strToken.nextToken();
			balance[a] = strToken.nextToken();
			k++;
		}
	}
	
	public static void CreateAccount() throws IOException {
		ImageIcon icon2 = new ImageIcon("Images/add-file.png");
		ImageIcon icon3 = new ImageIcon("Images/thumbs-up.png");
		String name = "", nat_id = "", gender = "", acc_type = "", pincode = "", balance = "";
		boolean exit = false;
		
		do{
			name = JOptionPane.showInputDialog(null, "Full Name: ", "Create Account", JOptionPane.PLAIN_MESSAGE, icon2, null, null).toString();
			if(name.isEmpty()) {
				JOptionPane.showMessageDialog(null, "Please enter Full Name!","Create Account", 0);
			}else {
				nat_id = JOptionPane.showInputDialog(null, "National ID Number: ", "Create Account", JOptionPane.PLAIN_MESSAGE, icon2, null, null).toString();
				if(nat_id.isEmpty()) {
					JOptionPane.showMessageDialog(null, "Please enter National ID Number!","Create Account", 0);
				} else if(nat_id.length() <7 || nat_id.length() >7) {
					JOptionPane.showMessageDialog(null, "Please enter a 7-digits National ID Number!","Create Account", 0);
				}else {
					gender = JOptionPane.showInputDialog(null, "Gender: ", "Create Account", JOptionPane.PLAIN_MESSAGE, icon2, gender_menu, gender_menu[0]).toString();
					acc_type = JOptionPane.showInputDialog(null, "Account Type: ", "Create Account", JOptionPane.PLAIN_MESSAGE, icon2, acctype_menu, acctype_menu[0]).toString();
					exit = true;
				}
			}
		}while(!exit);
			
		exit = false;
		do{
			pincode  = JOptionPane.showInputDialog(null, "Pincode: ", "Create Customer's Account", JOptionPane.PLAIN_MESSAGE, icon2, null, null).toString();
			if(pincode.isEmpty()) {
				JOptionPane.showMessageDialog(null, "Please enter Pincode!","Create Customer's Account", 0);
			}else if(pincode.length()<4 || pincode.length()>4) {
				JOptionPane.showMessageDialog(null, "Please enter a 4-digits Pincode!","Create Customer's Account", 0);
			}else {
				balance = JOptionPane.showInputDialog(null, "Initial Deposit: ", "Create Customer's Account", JOptionPane.PLAIN_MESSAGE, icon2, null, null).toString();
				if(acc_type.equals("Savings Account")) {
					if (Integer.parseInt(balance) <500) {
						JOptionPane.showMessageDialog(null, "You need to deposit at least 500 for Savings Account!","Create Customer's Account", 0);
					}else if(Integer.parseInt(balance) == 0) {
						JOptionPane.showMessageDialog(null, "Transaction Error","Create Customer's Account", 0);
					}else
						exit = true;
				} else {
					if (Integer.parseInt(balance) <1000) {
						JOptionPane.showMessageDialog(null, "You need to deposit at least 1000 for Current Account!","Create Customer's Account", 0);
					}else if(Integer.parseInt(balance) == 0) {
						JOptionPane.showMessageDialog(null, "Transaction Error","Create Customer's Account", 0);
					}else 
						exit = true;
				}
			}
		}while(!exit);
		
		String accnum = "00000";
	    numOfAccs++;
	    accnum += Integer.toString(numOfAccs);

		hold = name + "|" + nat_id + "|" + gender + "|" + acc_type + "|" + pincode + "|" + df.format(Integer.parseInt(balance.toString()))  + "|" + accnum  +"|\n\n";
		
		fWrite = new FileWriter("Accounts.txt",true);
		fWrite.write(hold);
		fWrite.flush();
		
		JOptionPane.showMessageDialog(null, "Customer's Bank Account has been created!", "Ry International Bank Corp.", JOptionPane.PLAIN_MESSAGE, icon3);
		i++;
	}
	
	public static void DeleteAccount() throws IOException {
		ImageIcon icon1 = new ImageIcon("Images/dlt.png");
		ImageIcon icon2 = new ImageIcon("Images/thumbs-up.png");
		boolean exit = false;
		
		do {
			String accnumber = JOptionPane.showInputDialog(null, "Enter the Account Number of the Customer:",  "Delete Customer's Account", JOptionPane.PLAIN_MESSAGE, icon1, null, null).toString();
			x = 0;
			while(x<i) {
				if (accnumber.equalsIgnoreCase(acc_num[x])) {
					LocalDateTime dateTime = LocalDateTime.now();
					deletiontime[x] = DateTimeFormatter.ofPattern("yyyy-MM-dd h:mm:ss").format(dateTime);
					String infos = deletiontime[x] + "|" + fullname[x] + "|" +  acc_num[x]  + "\t|" + balance[x]  +"|\n";
					
					fWrite = new FileWriter("DeletedAccounts.txt", true);
					fWrite.write(infos);
					fWrite.flush();
					j++;
					
					hold="The Account Number  '" + acc_num[x] + "'  has been deleted.";
					for (ctr = x; ctr < i; ctr++) {
						fullname[ctr] = fullname[ctr+1];
						nat_id[ctr] = nat_id[ctr+1];
						gender[ctr] = gender[ctr+1];
						acc_type[ctr] = acc_type[ctr+1];
						pincode[ctr] = pincode[ctr+1];
						balance[ctr] = balance[ctr+1];
					}
					i--;
					exit = true;
					break;			
				}else {
					hold = "The Account Number  '"+ accnumber + "'  doesn't exist.";
				}
				x++;
			}
			JOptionPane.showMessageDialog(null, new JTextArea(hold), "Ry International Bank Corp.", JOptionPane.PLAIN_MESSAGE, icon2);
		}while (!exit == true);
		
		String all="";
		for (int k = 0; k < i; k++) { 
			all += fullname[k] + "|" + nat_id[k] + "|" + gender[k] + "|" + acc_type[k] + "|"+pincode[k] + "|" + balance[k] + "|" + acc_num[k] + "|\n";
		}
		
		fWrite = new FileWriter("Accounts.txt");
		fWrite.write(all);
		fWrite.flush();
	}
	
	public static void SearchAccount() throws IOException {
		ImageIcon icon1 = new ImageIcon("Images/seo.png");
		ImageIcon icon2 = new ImageIcon("Images/thumbs-up.png");
		
		String accnumber = JOptionPane.showInputDialog(null, "Enter the Account Number of the Customer:",  "Search Customer's Account", JOptionPane.PLAIN_MESSAGE, icon1, null, null).toString();
		x = 0;
		while(x<i) {
			if(accnumber.equalsIgnoreCase(acc_num[x])) {
				hold = "Search Completed!\n\nBank Account Information:\nAcc. Number\t: " + acc_num[x] + 
						"\nFull Name\t: " + fullname[x] + "\nNIN\t: " + nat_id[x] + "\nGender\t: " + gender[x] +
						"\nAccount Type\t: " + acc_type[x] + "\nPincode\t: " + pincode[x] + "\nBalance\t: " + df.format(Double.parseDouble(balance[x])).toString() + 
						"\n\nThank you!";
				break;
			}else {
				hold = "The Account Number  '"+ accnumber + "'  doesn't exist.";
			}
			x++;
		}
		JOptionPane.showMessageDialog(null, new JTextArea(hold), "Ry International Bank Corp.", JOptionPane.PLAIN_MESSAGE, icon2);
	}
	
	public static void ListOfAccounts() throws FileNotFoundException {
		ImageIcon icon1 = new ImageIcon("Images/list.png");
		fRead = new FileReader("Accounts.txt");
		read = new Scanner(fRead);
		hold = "";
		
		while(read.hasNext()) {
			hold += read.nextLine() + "\n";
		}
		strToken = new StringTokenizer(hold,"|");			
		hold = " ------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------  \n Full Name\t\tNIN\tGender\tAccount Type\t\tPincode\tBalance\tAccount Number  \n" +
				" ------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------  \n";

		while(strToken.hasMoreTokens()) {
			hold += strToken.nextToken() + "\t";
		}

		read.close();
		JOptionPane.showMessageDialog(null, new JTextArea(hold), "Ry International Bank Corp.", JOptionPane.PLAIN_MESSAGE, icon1);
	}
	
	public static void EditInformation() throws IOException {
		ImageIcon icon1 = new ImageIcon("Images/editinfo.png");
		ImageIcon icon2 = new ImageIcon("Images/thumbs-up.png");
		String accnumber = JOptionPane.showInputDialog(null, "Enter the Account Number of the Customer: ",  "Edit Customer's Information", JOptionPane.PLAIN_MESSAGE, icon1, null, null).toString();
		x=0;
		while(x<i) {
			if(accnumber.equalsIgnoreCase(acc_num[x])) {
				boolean exit = false;
				do{
					fullname[x] = JOptionPane.showInputDialog(null, "Full Name: ", "Edit Customer's Account", JOptionPane.PLAIN_MESSAGE, icon1, null, null).toString();
					if(fullname[x].isEmpty()) {
						JOptionPane.showMessageDialog(null, "Please enter Full Name!","Edit Customer's Account", 0);
					}else {
						nat_id[x] = JOptionPane.showInputDialog(null, "National ID Number: ", "Edit Customer's Account", JOptionPane.PLAIN_MESSAGE, icon1, null, null).toString();
						if(nat_id[x].isEmpty()) {
							JOptionPane.showMessageDialog(null, "Please enter National ID Number!","Edit Customer's Account", 0);
						}else {
							gender[x] = JOptionPane.showInputDialog(null, "Gender: ", "Edit Customer's Account", JOptionPane.PLAIN_MESSAGE, icon1, gender_menu, gender_menu[0]).toString();
							acc_type[x] = JOptionPane.showInputDialog(null, "Account Type: ", "Edit Customer's Account", JOptionPane.PLAIN_MESSAGE, icon1, acctype_menu, acctype_menu[0]).toString();
							pincode[x]  = JOptionPane.showInputDialog(null, "Pincode: ", "Edit Customer's Account", JOptionPane.PLAIN_MESSAGE, icon1, null, null).toString();
							if(pincode[x].isEmpty()) {
								JOptionPane.showMessageDialog(null, "Please enter Pincode!","Edit Customer's Account", 0);
							}else if(pincode[x].length()<4 || pincode[x].length()>4) {
								JOptionPane.showMessageDialog(null, "Please enter a 4-digits Pincode!","Edit Customer's Account", 0);
							}else 
								exit = true;	
						}
					}
				}while(!exit);
				
				String all = "";
				for (int k = 0; k < i; k++) { 
					all += fullname[k] + "|" + nat_id[k] + "|" + gender[k] + "|" + acc_type[k] + "|"+pincode[k] + "|" + balance[k] + "|" + acc_num[k] + "|\n";
				}

				fWrite = new FileWriter("Accounts.txt");
				fWrite.write(all);
				fWrite.flush();
				
				hold = "The account informations has been changed!  \n\nBank Account Information:\nAcc. Number\t: " + acc_num[x] + 
						"\nFull Name\t: " + fullname[x] + "\nNIN\t: " + nat_id[x] + "\nGender\t: " + gender[x] +
						"\nAccount Type\t: " + acc_type[x] + "\nPincode\t: " + pincode[x] + "\nBalance\t: " + balance[x] + 
						"\n\nThank you!";
				break;
			}else {
				hold="There is no  '"+ accnumber + "'  on the list.";
			}
			x++;
		}
		JOptionPane.showMessageDialog(null, new JTextArea(hold), "Ry International Bank Corp.", JOptionPane.PLAIN_MESSAGE, icon2);
		
	}
	
	public static void LogsOfDeletedAccounts() throws FileNotFoundException {
		ImageIcon icon1 = new ImageIcon("Images/dlt.png");
		fRead = new FileReader("DeletedAccounts.txt");
		read = new Scanner(fRead);
		hold = "";
		
		while(read.hasNext()) {
			hold += read.nextLine() + "\n";
		}
		strToken = new StringTokenizer(hold,"|");			
		hold = " ---------------------------------------------------------------------------------------------------------------------------------------------------------\n Deleted on:\t\tFullname\t\tAccount Number\tBalance \n" +
				" ---------------------------------------------------------------------------------------------------------------------------------------------------------\n";

		while(strToken.hasMoreTokens()) {
			hold += strToken.nextToken() + "\t";
		}

		read.close();
		JOptionPane.showMessageDialog(null, new JTextArea(hold), "Ry International Bank Corp.", JOptionPane.PLAIN_MESSAGE, icon1);
	}
	
	public static void AdminPanel() throws IOException {
		ImageIcon icon = new ImageIcon("Images/bnk.png");
		String admin = "";
		
		String msg = "<html>&nbsp;Ry International Bank Inc.<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;ADMIN PANEL<br><br>Options:</html>";
		JLabel adminp = new JLabel(msg);
		adminp.setFont(new Font ("Courier New", Font.BOLD, 16));
		adminp.setForeground(new Color(0,120,215));

		do {
			admin = JOptionPane.showInputDialog(null, adminp, "Bank Management System", -1, icon, admin_menu, admin_menu[0]).toString();
			switch(admin) {
				case "Create Account":
					CreateAccount();
					break;
				case "Delete Account":
					DeleteAccount();
					break;
				case "Search Account":
					SearchAccount();
					break;
				case "List of All Accounts":
					ListOfAccounts();
					break;
				case "Edit Information":
					EditInformation();
					break;
				case "Logs of Deleted Accounts":
					LogsOfDeletedAccounts();
					break;
			}
		}while (!admin.equals("Main Menu"));
	}
	
	public static void DepositCash() throws IOException {
		ImageIcon icon1 = new ImageIcon("Images/deposit.png");
		ImageIcon icon2 = new ImageIcon("Images/thumbs-up.png");
		String accnumber = JOptionPane.showInputDialog(null, "Enter the Account Number of the Customer: ",  "Deposit Cash", JOptionPane.PLAIN_MESSAGE, icon1, null, null).toString();
		String pin = JOptionPane.showInputDialog(null, "Enter the Account Number's Pincode: ",  "Deposit Cash", JOptionPane.PLAIN_MESSAGE, icon1, null, null).toString();
		boolean exit = false;
		x = 0;
		while(x<i) {
			if(accnumber.equalsIgnoreCase(acc_num[x]) && pin.equalsIgnoreCase(pincode[x])) {
				do{
					double deposit = Double.parseDouble(JOptionPane.showInputDialog(null, "Enter the Deposit Amount: ", "Deposit Cash", JOptionPane.PLAIN_MESSAGE, icon1, null, null).toString());
					if(deposit <= 0) {
						JOptionPane.showMessageDialog(null, "Transaction Error!", "Deposit Cash", 0);
					} else {
						double a = Double.parseDouble(balance[x].replace(",", ""));
						double newbal = a + deposit;
						balance[x] = Double.toString(newbal);
						JOptionPane.showMessageDialog(null, "Account Number " + acc_num[x] + "'s new balance is " + df.format(newbal) + ".", null, -1, icon2);
						exit = true;
					}
				}while(!exit);	
				
				LocalDateTime dateTime = LocalDateTime.now();
				transaction_time[x] = DateTimeFormatter.ofPattern("yyyy-MM-dd h:mm:ss").format(dateTime);
				transaction[x] = "Deposit Cash";
				String infos = transaction_time[x] + "|" + transaction[x] + "\t|" +  acc_num[x]  + "\t|" + balance[x]  +"|\n";
				
				fWrite = new FileWriter("UserTransactions.txt", true);
				fWrite.write(infos);
				fWrite.flush();
				k++;
				
				String all = "";
				for (int k = 0; k < i; k++) { 
					all += fullname[k] + "|" + nat_id[k] + "|" + gender[k] + "|" + acc_type[k] + "|"+pincode[k] + "|" + balance[k] + "|" + acc_num[k] + "|\n";
				}

				fWrite = new FileWriter("Accounts.txt");
				fWrite.write(all);
				fWrite.flush();
			
				break;
			} else {
				JOptionPane.showMessageDialog(null, "Incorrect Log In!", "Deposit Cash", 0);
			}
			x++;
		}
	}
	
	public static void WithdrawCash() throws IOException {
		ImageIcon icon1 = new ImageIcon("Images/withdraw.png");
		ImageIcon icon2 = new ImageIcon("Images/thumbs-up.png");
		String accnumber = JOptionPane.showInputDialog(null, "Enter the Account Number of the Customer: ",  "Withdraw Cash", JOptionPane.PLAIN_MESSAGE, icon1, null, null).toString();
		String pin = JOptionPane.showInputDialog(null, "Enter the Account Number's Pincode: ",  "Withdraw Cash", JOptionPane.PLAIN_MESSAGE, icon1, null, null).toString();
		boolean exit = false;
		x = 0;
		while(x<i) {
			if(accnumber.equalsIgnoreCase(acc_num[x]) && pin.equalsIgnoreCase(pincode[x])) {
				do{
					double withdraw = Double.parseDouble(JOptionPane.showInputDialog(null, "Enter the Withdraw Amount: ", "Withdraw Cash", JOptionPane.PLAIN_MESSAGE, icon1, null, null).toString());
					if(withdraw <= 0) {
						JOptionPane.showMessageDialog(null, "Transaction Error!", "Withdraw Cash", 0);
					} else if (withdraw > Double.valueOf(balance[x])) {
						JOptionPane.showMessageDialog(null, "Insufficient Balance!", "Withdraw Cash", 0);
					} else {
						double a = Double.parseDouble(balance[x].replace(",", ""));
						double newbal = a - withdraw;
						balance[x] = Double.toString(newbal);
						JOptionPane.showMessageDialog(null, "Account Number " + acc_num[x] + "'s new balance is " + df.format(newbal) + ".", "Ry. International Bank Inc.", -1, icon2);
						exit = true;
					}
				}while(!exit);	
				
				LocalDateTime dateTime = LocalDateTime.now();
				transaction_time[x] = DateTimeFormatter.ofPattern("yyyy-MM-dd h:mm:ss").format(dateTime);
				transaction[x] = "Withdraw Cash";
				String infos = transaction_time[x] + "|" + transaction[x] + "\t|" +  acc_num[x]  + "\t|" + balance[x]  +"|\n";
				
				fWrite = new FileWriter("UserTransactions.txt", true);
				fWrite.write(infos);
				fWrite.flush();
				k++;
				
				String all = "";
				for (int k = 0; k < i; k++) { 
					all += fullname[k] + "|" + nat_id[k] + "|" + gender[k] + "|" + acc_type[k] + "|"+pincode[k] + "|" + balance[k] + "|" + acc_num[k] + "|\n";
				}

				fWrite = new FileWriter("Accounts.txt");
				fWrite.write(all);
				fWrite.flush();
				break;
			} else {
				JOptionPane.showMessageDialog(null, "Incorrect Log In!", "Withdraw Cash Cash", 0);
			}
			x++;
		}
	}
	
	public static void LogsOfUsersAccounts() throws FileNotFoundException {
		ImageIcon icon1 = new ImageIcon("Images/logs.png");
		fRead = new FileReader("UserTransactions.txt");
		read = new Scanner(fRead);
		hold = "";
		
		while(read.hasNext()) {
			hold += read.nextLine() + "\n";
		}
		strToken = new StringTokenizer(hold,"|");			
		hold = " ---------------------------------------------------------------------------------------------------------------------------------------------------------\n Date & Time\t\tTransaction\t\tAcccount Number\tBalance \n" +
				" ---------------------------------------------------------------------------------------------------------------------------------------------------------\n";

		while(strToken.hasMoreTokens()) {
			hold += strToken.nextToken() + "\t";
		}

		read.close();
		JOptionPane.showMessageDialog(null, new JTextArea(hold), "Ry International Bank Corp.", JOptionPane.PLAIN_MESSAGE, icon1);
	}
	
	public static void StaffPanel() throws IOException {
		ImageIcon icon = new ImageIcon("Images/bnk.png");
		String staff = "";
		
		String msg = "<html>&nbsp;Ry International Bank Inc.<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;STAFF PANEL<br><br>Options:</html>";
		JLabel adminp = new JLabel(msg);
		adminp.setFont(new Font ("Courier New", Font.BOLD, 16));
		adminp.setForeground(new Color(0,120,215));

		do {
			staff = JOptionPane.showInputDialog(null, adminp, "Bank Management System", -1, icon, staff_menu, staff_menu[0]).toString();
			switch(staff) {
				case "Check Account Information":
					SearchAccount();
					break;
				case "Deposit Cash":
					DepositCash();
					break;
				case "Withdraw Cash":
					WithdrawCash();
					break;
				case "Logs of User Accounts":
					LogsOfUsersAccounts();
					break;
			}
		}while (!staff.equals("Main Menu"));
	}
	
	public static void ATMService() throws IOException {
		ImageIcon icon = new ImageIcon("Images/bnk.png");
		String staff = "";
		
		String msg = "<html>&nbsp;Ry International Bank Inc.<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;ATM SERVICE PANEL<br><br>Options:</html>";
		JLabel adminp = new JLabel(msg);
		adminp.setFont(new Font ("Courier New", Font.BOLD, 16));
		adminp.setForeground(new Color(0,120,215));

		do {
			staff = JOptionPane.showInputDialog(null, adminp, "Bank Management System", -1, icon, atmservice_menu, atmservice_menu[0]).toString();
			switch(staff) {
				case "Deposit Cash":
					DepositCash();
					break;
			}
		}while (!staff.equals("Main Menu"));
	}
	
	public static void main(String[] args) throws IOException {
		ImageIcon icon = new ImageIcon("Images/bnk.png");
		f = new File("Accounts.txt");
		f = new File("DeletedAccounts.txt");
		f = new File("UserTransactions.txt");
		try {
			if(f.createNewFile()) {
				System.out.println("Bago ra nabuhat ang text file choi");
			} else 
				System.out.println("Naa nay in ana na text file choi");
		} catch (IOException e) {
			System.err.println("Naay mali sa imong gibuhat bai");
			e.printStackTrace();
		}
		
		do {
			i = 0;
			Accounts();
			DeletedAccounts();
			
			String msg = "<html>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Ry International Bank Inc.<br>Created by: Ray Joshua A. Balingkit<br><br>Main Menu:</html>";
			JLabel lbl = new JLabel(msg);
			lbl.setFont(new Font ("Courier New", Font.BOLD, 16));
			lbl.setForeground(new Color(0,120,215));
			
			choice = JOptionPane.showInputDialog(null, lbl , "Bank Management System", 
					1, icon, menu, menu[0]).toString();
			
			switch(choice) {
			case "ADMIN Login":
				AdminPanel();
				break;
			case "STAFF Login":
				StaffPanel();
				break;
			case "ATM Service":
				ATMService();
				break;
			}
		}while(!choice.equals("Exit"));
	}
}
