package hosptial.signup;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import hosptial.DataPath;

public class UserSignup {
	private static Scanner sc = new Scanner(System.in);;
	private boolean escape = false;
	private LogicCheck logicCheck;
	private ArrayList<String> input;
	private ArrayList<String> doctorInput;
	
	public UserSignup() {
		input = new ArrayList<>();
		doctorInput = new ArrayList<>(5);
		logicCheck = new LogicCheck();
	}
	
	public void idInputCheck(String userType) {
		if(!(userType.equals("0") || userType.equals("1"))) {
			System.out.println("userType error");
			return;
		}
		boolean loop = true;
		
		while(loop) {
			System.out.print("아이디:");
			String id = sc.nextLine();
			if(!logicCheck.idLogicCheck(id)) {
				SingupOutput.idMenuLogicFail();
				String result = sc.nextLine();
				if("0".equals(result)) {
					escape = true;
					return;
				}//if
			} else if(logicCheck.DuplicateCehck(2, id)) {
				SingupOutput.isDuplicate();
				String result = sc.nextLine();
				if("0".equals(result)) {
					escape = true;
					return;
				}//if
			} else {
				input.add(userType);	//0 일반유저, 1 의사유저
				input.add(id);			//아이디
				loop = false;
			}//if
		}//while :: id
	}
	public void pwInputCheck() {
		boolean loop = true;
		while(loop) {
			System.out.print("비밀번호:");
			String pw = sc.nextLine();
			if(!logicCheck.pwLogicCheck(pw)) {
				SingupOutput.pwMenuLogicFail();
				String result = sc.nextLine();
				if("0".equals(result)) {
					escape = true;
					return;
				}//if
			} else {
				input.add(pw);	//비번
				loop = false;
			}//if
		}//while :: pw
	} 
	
	public void nameInputCheck() {
		boolean loop = true;
		while(loop) {
			System.out.print("이름:");
			String name = sc.nextLine();
			if(!logicCheck.nameLogicCheck(name)) {
				SingupOutput.nameMenuLogicFail();
				String result = sc.nextLine();
				if("0".equals(result)) {
					escape = true;
					return;
				}//if
			} else {
				input.add(name);	//이름
				loop = false;
			}//if
		}//while :: pw
	} 
	
	public void phoneNumberInputCheck() {
		boolean loop = true;
		while(loop) {
			System.out.println("휴대전화번호:ex)010-xxxx-xxxx");
			System.out.print("입력:");
			String phoneNumber = sc.nextLine();
			if(!logicCheck.phoneNumberLogicCheck(phoneNumber)) {
				SingupOutput.phoneNumberMenuLogicFail();
				String result = sc.nextLine();
				if("0".equals(result)) {
					escape = true;
					return;
				}//if
			} else if(logicCheck.DuplicateCehck(5, phoneNumber)) {	//5 전화번호
				SingupOutput.isDuplicate();
				String result = sc.nextLine();
				if("0".equals(result)) {
					escape = true;
					return;
				}//if
			} else {
				input.add(phoneNumber);	//전화번호
				loop = false;
			}//if
		}//while :: pw
	} 
	
	public void addressInputCheck() {

		boolean loop = true;
		while(loop) {
			System.out.print("주소:");
			String address = sc.nextLine();
			
			if(!logicCheck.addressLogicCheck(address)) {
				SingupOutput.addressMenuLogicFail();
				String result = sc.nextLine();
				if("0".equals(result)) {
					escape = true;
					return;
				}//if
			} else {
				input.add(address);	//주소
				loop = false;
			}//if
		}//while :: pw
	}
	
	public void regNoInputCheck() {
		boolean loop = true;
		while(loop) {
			System.out.println("주민번호: ex) 930967-1111111");
			System.out.print("입력 : ");
			String regNo = sc.nextLine();
			if(!logicCheck.regNoCheck(regNo)) {
				SingupOutput.regNoMenuLogicFail();
				String result = sc.nextLine();
				if("0".equals(result)) {
					escape = true;
					return;
				}//if
			} else if(logicCheck.DuplicateCehck(7, regNo)) {	//5 전화번호
				SingupOutput.isDuplicate();
				String result = sc.nextLine();
				if("0".equals(result)) {
					escape = true;
					return;
				}//if
			} else {
				input.add(regNo);	//전화번호
				loop = false;
			}//if
		}//while :: pw
	} 
	
	//병원이름
	public void hospitalNameInputCheck() {

		boolean loop = true;
		while(loop) {
			System.out.print("병원이름:");
			String hospitalName = sc.nextLine();
			
			if(!logicCheck.hospitalNameLogicCheck(hospitalName)) {
				SingupOutput.hospitalNameMenuLogicFail();
				String result = sc.nextLine();
				if("0".equals(result)) {
					escape = true;
					return;
				}//if
			} else {
				doctorInput.add(hospitalName);	//병원 이름
				loop = false;
			}//if
		}//while :: pw
	}
	
//	//오픈시간
	public void operatingTimeInputCheck(int openDayType) {
		//1. 평일, 2. 주말, 3. 공휴일
		if(openDayType==3) {
			doctorInput.add("미운영");
			return;
		} 
		boolean loop = true;
		while(loop) {
			if(openDayType==1)
				System.out.println("평일 영업 시간 : ex)9:00~18:00");
			if(openDayType==2)
				System.out.println("주말 영업 시간 : ex)9:00~18:00");
			System.out.print("입력:");
			String operatingTime = sc.nextLine();
			if(!logicCheck.operatingTimeLogicCheck(operatingTime)) {
				SingupOutput.operatingTimeMenuLogicFail();
				String result = sc.nextLine();
				if("0".equals(result)) {
					escape = true;
					return;
				}//if
			}  else {
				//TODO 배열 어떻게 관리할지 찾아보기
				doctorInput.add(operatingTime);	//전화번호
				loop = false;
			}//if
		}//while :: pw
	} 
	
	public void licenseNumberInputCheck() {
		boolean loop = true;
		while(loop) {
			System.out.print("의사 면허번호 (5자리 숫자) :");
			String licenseNumber = sc.nextLine();
			if(!logicCheck.licenseNumberLogicCheck(licenseNumber)) {
				SingupOutput.licenseNumberMenuLogicFail();
				String result = sc.nextLine();
				if("0".equals(result)) {
					escape = true;
					return;
				}//if
			}  else {
				doctorInput.add(licenseNumber);	//전화번호
				loop = false;
			}//if
		}//while :: pw
	} 
	
	public void departmentInputCheck() {
		boolean loop = true;
		while(loop) {
			System.out.print("진료과:");
			String department = sc.nextLine();
			if(!logicCheck.departmentLogicCheck(department)) {
				SingupOutput.departmentLogicFail();
				String result = sc.nextLine();
				if("0".equals(result)) {
					escape = true;
					return;
				}//if
			}  else {
				doctorInput.add(department);	//진료과
				loop = false;
			}//if
		}//while :: pw
	} 
	
	public boolean escapeCheck() {
		return escape;
	}
	
	
	public void save(String userType) {
		
		long sequence = logicCheck.getSequence();
		
		File file = new File(DataPath.userData);
		if(!file.exists())System.out.println("파일이 존재하지 않습니다.");
		
		try(BufferedWriter br = new BufferedWriter(new FileWriter(file, true));) {
			String temp = "";
			for(int i=0; i<input.size(); i++) {
				if(i!=0) temp += ",";
				temp += input.get(i);
			}
			br.write("\n"+sequence +","+ temp);
			
		} catch (IOException e) {
			System.out.println("회원가입중에 문제가 생겼습니다. 관리자에게 문의해주세요.");
			e.printStackTrace();
		}
		
		if("0".equals(userType)) return;
		if("1".equals(userType)) doctorSave(sequence);
		
	}
	
	public void doctorSave(long sequence) {
		File file = new File(".\\data\\UserDoctorDataTest.txt");
		if(!file.exists()) System.out.println("파일이 존재하지 않습니다.");
		
		try(BufferedWriter br = new BufferedWriter(new FileWriter(file, true));) {
			String temp = "";
			for(int i=0; i<doctorInput.size(); i++) {
				if(i!=0) temp += ",";
				temp += doctorInput.get(i);
			}
			br.write("\n"+ sequence +","+ temp);
			
		} catch (IOException e) {
			System.out.println("회원가입중에 문제가 생겼습니다. 관리자에게 문의해주세요.");
			e.printStackTrace();
		}	
		
	}

}
