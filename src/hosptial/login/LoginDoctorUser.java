package hosptial.login;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Arrays;
import java.util.Scanner;
import hosptial.DataPath;
import hosptial.LoginSession;
import hosptial.domain.CommonUser;
import hosptial.domain.DoctorUser;
import hosptial.domain.User;

public class LoginDoctorUser {

	public static boolean main() {
		Scanner sc = new Scanner(System.in);
		boolean loop = true;
		while(loop) {
			
			LoginOutput.doctorUserMain();
			
			System.out.print("아이디를 입력하세요 : ");
			String id = sc.nextLine().trim();
			System.out.print("비밀번호를 입력하세요 : ");
			String pw = sc.nextLine().trim();
			System.out.println();
			
			if(id.length()==0 || pw.length()==0 ) {
				LoginOutput.emptyElement();
				String result = sc.nextLine();
				if("0".equals(result)) return false;
			} else if(!loginCheck(id, pw)) {
				LoginOutput.loginFail();
				String result = sc.nextLine();
				if("0".equals(result)) return false;
			} else {
				LoginOutput.loginSuccess();
				loop = false;
			}			
		}//while
		return true;

	}
	private static boolean loginCheck(String id, String pw) {
		
		try {
			File file = new File(DataPath.userData);
			if(file.exists()) {
				BufferedReader br = new BufferedReader(new FileReader(file));
				String line = null;
				while((line=br.readLine())!=null) {
					if(line.equals("")) continue;
					String[] temp = line.split(","); //2. id 3. pw
					if(id.equals(temp[2]) && pw.equals(temp[3]) && userTypeCheck(temp[1])) {
						inputSesstion(temp);
						return true;
					}//if
					
				}//while
				br.close();
			} else {
				System.out.println("오류가 발생했습니다. 관리자에게 문의해주세요.");
			}//if
		} catch (Exception e) {
			e.printStackTrace();
		}//try

		return false;
	}

	private static void inputSesstion(String[] info) {	
		try {
			String[] doctorInfo = inputDoctorInfo(info[0]);
			User user = new DoctorUser(Integer.parseInt(info[0]), Integer.parseInt(info[1]),
					info[2], info[3], info[4], info[5], info[6], info[7],
					doctorInfo[1],doctorInfo[2],doctorInfo[3],doctorInfo[4],doctorInfo[5], doctorInfo[6]);
			LoginSession.setSession(user);
		} catch (Exception e) {
			System.out.println("로그인 시도중에 오류가 발생했습니다.");
			e.printStackTrace();
		}//try
	}
	
	private static String[] inputDoctorInfo(String user) {
		
		try (BufferedReader br = new BufferedReader(new FileReader(new File(DataPath.userDataDoctor)));){
	
			String loop = null;
			while((loop=br.readLine())!=null) {
				if(loop=="") continue;
				String[] temp = loop.split(",");
				if(user.equals(temp[0])) {
					return temp;
				}
			}//while
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	private static boolean userTypeCheck(String userType) {	
		if("-1".equals(userType)) {
			LoginOutput.userTypeAdmin();
			return false;
		} else if("0".equals(userType)) {
			LoginOutput.userTypeCommon();
			return false;
		}

		return true;
	}

}