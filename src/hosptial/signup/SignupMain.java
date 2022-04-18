package hosptial.signup;

import java.util.Scanner;

public class SignupMain {

	public void signUp() {

		Scanner sc = new Scanner(System.in);
		
		boolean loop = true;

		while(loop) {
			SingupOutput.main();
			SingupOutput.menu();
			System.out.print("번호 : ");
			int input = sc.nextInt();
			
			if(input==1) {
				CommonUserSignup commonUserSighup = new CommonUserSignup();
				boolean result = commonUserSighup.main();
				if(result==true) {
					SingupOutput.singupsuccess();
					return;
				}
			} else if(input==2) {
				DoctorSignup doctorSignup = new DoctorSignup();
				boolean result = doctorSignup.main();
				if(result==true) {
					SingupOutput.singupsuccess();
					return;
				}
			} else if(input==0) {
				System.out.println("이전으로 돌아갑니다.");
				loop = false;
			} else {
				System.out.println("잘못 입력하셨습니다.");
			}
		}//while
		
	}

}
