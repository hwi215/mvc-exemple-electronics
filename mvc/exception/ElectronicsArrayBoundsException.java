package mvc.exception;

/**
 * Electronics배열의 저장공간을 벗어 났을때 처리 할 예외 클래스 
 * */
public class ElectronicsArrayBoundsException extends Exception {
	public ElectronicsArrayBoundsException() {
		System.out.println("배열의 길이를 벗어나 더이상 등록 할수 없습니다.");
	}
	public ElectronicsArrayBoundsException(String message) {
		super(message);
	}

}
