package mvc.view;

import mvc.exception.ElectronicsArrayBoundsException;
import mvc.exception.SearchNotFoundException;

class StartView {
    
    public static void main(String []args) throws SearchNotFoundException, ElectronicsArrayBoundsException {
        System.out.println("===== 프로그램 시작합니다. =====");

        MenuView mv = new MenuView(); // 전역변수 초기화, 생성자 호출 
        mv.printMenu();

    }
    
}
