package edu.kh.io.model.service;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileInputStream;

public class IOService {
	
	// IO
	
	// Input(입력) : 외부 -> 내부로 데이터를 들여오는 것
	// Output(출력) : 내부 -> 외부로 데이터를 내보내는 것
	
	// Stream : 입/출력 통로 역할(데이터가 흘러가는 통로)
	// 			기본적으로 Stream은 단방향이다.
	
	// 1) File 출력 (내부 == 프로그램, 외부 == 파일)
	public void output1() { // 바이트 기반 아우풋 스트림
		
		// FileOutputStream fos = new FileOutputStream("test1.txt");
		// Unhandled exception type FileNotFoundException
		// FileOutputStream 객체 생성 시, FileNotFoundException 예외가 발생할 수 있다
		// 예외처리가 필요하다 (try catch문)
		// 위 처럼 바로 쓰는 게 아니라, 아래처럼 시행한다.
		
		
		FileOutputStream fos = null; // 바이트 기반
		
		try {
			
			fos = new FileOutputStream("test1.txt");
			// 위에 코드 뜻은
			// 현재 프로그램에서 test1.txt 파일로 출력하는 통로 객체를 생성한 것
			// 이 파일은 목적지가 필요함(test1.txt가 목적지)
			// --> 12_IO 지금 해당하고 있는 프로젝트 폴더가 원래 기본 목적지로 설정되어있음
			
			// 파일에 "Hello" 내보내보기
			
			String str = "hello Lee";
			
			for(int i = 0; i < str.length(); i++) {
				//System.out.println(str.charAt(i)); --> 콘솔 출력
				fos.write(str.charAt(i));
				// 위 처럼 그대로 쓰면
				// Unhandled exception type IOException 발생
				// write()는 IOException 발생시킬 가능성이 크다
				// FileNotFoundException은 IOException의 자식이라서
				// catch문에 IOException 쓰기 --> 다형성 적용
				
			}
			
		}catch(IOException e) {
			
			System.out.println("예외 발생");
			e.printStackTrace(); // -> 예외 추적
			
		}finally { // finally --> 예외가 발생하든 말든 무조건 수행
			// 입출력이 끝나면 무조건 Stream을 끊어줘야함
			// 사용한 스트림 자원 반환(통로 없앰) ==> 필수 작성
			// 프로그램 메모리 관리 차원에서 항상 다 쓰면 끊어줌
			// 작성 안하면 문제점으로 꼽힘
			// 시험 문제에 나올 수 있겠네?
			
			try {
				fos.close(); // IOException 예외 발생하여 try catch구문으로 한 번더 작성
			} catch (IOException e) {
				
				e.printStackTrace();
			}
			
		}
	}
	
	//=====================================================================================================================
	
	public void input1() { // 이번에는 바이트 기반 input 스트림
		
		// 파일 입력 : 외부(파일) -> 내부(프로그램)으로 읽어오기
		// 즉, --> 위에서 만든 test1.txt(외부)에서 내부(이클립스 콘솔)에 찍히게 하기
		
		FileInputStream fis = null;
		
		try {
			fis = new FileInputStream("test1.txt");
			
			// FileInputStream은 1Byte씩만 읽어올 수 있다.
			
			while(true) {
				
				int data =fis.read(); // 다음 1byte를 읽어오는 데, 정수형임
									  // 만약에 다음 내용이 없으면 -1을 반환
				if(data == -1) break;
				// 다음 내용이 없으면 break;로 while문 종료
				
				// 반복 종료가 안됐으면, char로 강제 형변환 해서 문자로 출력
				System.out.print((char) data);
			}
			
		}catch(IOException e) {
			
			e.printStackTrace(); // 예외 추적
			
		}finally {
			
			try {
				fis.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	
	//=====================================================================================================================
	
	public void output2() { // 문자 기반 outputstream
		
		// 문자 기반 스트림
		FileWriter fw = null; // 프로그램 -> 파일로 쓰는 문자 기반 스트림
		
		try {
			
			fw = new FileWriter("test1.txt", true); // 외부 파일과 연결하는 스트림 객체 생성
			// 매개변수에 true를 전달하면 이어쓰기가 가능
			String sttrr = "안녕하세요. Hello. 1234 !@#$" +  " " + "하위" + " "  + "아 치킨 먹고싶다!!";
			
			fw.write(sttrr);
			
		}catch(IOException e) {
			e.printStackTrace();
		}finally {
			
			try {
				fw.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	//=====================================================================================================================
	
	public void input2() { // 문자 기반 input stream
		
		FileReader fr = null;
		
		
		
		try {
			
			// 파일로부터 읽어오는 통로 객체 생성
			
			fr = new FileReader("test1.txt");
			
			while(true) {
				
				int data = fr.read(); // 다음 문자 읽어오기, 없으면 -1
				
				if(data == -1) break;
				
				System.out.println((char) data);
				
				// 맥은 한글이 깨질 수 있다.
				// 인코딩 문제니 ㄱㅊ 내가 틀린 게 아니다
				
			}
			
			
		}catch(IOException e) {
			e.printStackTrace();
			
			
		}finally {
			
			
			try {
				fr.close();  // 통로 끊기
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
//시험은 여기까지만 나온다