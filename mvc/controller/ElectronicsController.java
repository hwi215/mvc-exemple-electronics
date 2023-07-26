package mvc.controller;

import mvc.dto.Electronics;
import mvc.exception.ElectronicsArrayBoundsException;
import mvc.exception.SearchNotFoundException;
import mvc.service.ElectronicsService;
import mvc.service.ElectronicsServiceImpl;

import java.util.List;

import static mvc.view.FailView.errorMessage;
import static mvc.view.SuccessView.*;


/**
 * View와 Model 사이에서 중간 역할 
 *  : 사용자의 요청을 받아서 그에 해당하는 "서비스를 호출"하고
 *    호출한 결과를 받아서 결과값에 따라 결과 뷰를 호출해준다.
 */

public class ElectronicsController {
    private ElectronicsService service = ElectronicsServiceImpl.getInstance();
    

    /**
     * 전체검색
     * @return
     */
    public List<Electronics> selectAll() {
        List<Electronics> electronicsList = service.selectAll();

        printAll(electronicsList);

        return electronicsList;
    }
 

	 /**
     * 전자제품 등록 (길이를 벗어났는지, 중복여부 체크)
     */
    public void insert(Electronics electronics){

        try{
            service.insert(electronics);
        }catch (ElectronicsArrayBoundsException e){
            System.out.println(e.getMessage());
        } catch (SearchNotFoundException e) {
            System.out.println(e.getMessage());
        }

    }
    
    

    /**
     * 모델번호에 해당하는 전자제품 검색
     * @param modelNo
     */
    public void searchByModelNo(int modelNo){

        try{
            Electronics electronics = service.searchByModelNo(modelNo); // 모델 검색
            printSearchByModelNo(electronics);
        }catch (SearchNotFoundException e){
            errorMessage(e.getMessage());
        }

    } 

    /**
     * 모델번호에 해당하는 전자제품 수정하기 
     * @param electronics
     */
    public void update(Electronics electronics){
        try{
            service.update(electronics);
            System.out.println(electronics.toString());
        }catch (SearchNotFoundException e){
            System.out.println(e.getMessage());
        }

    	
    }
    
    /**
     * 모델번호에 해당하는 전자제품 삭제하기 
     * @param electronics
     */
	public void deleteModelNo(int modelNo){
        try{
            service.delete(modelNo);
        }catch (SearchNotFoundException e){
            System.out.println(e.getMessage());
        }
		
	}

    /**
     * 모델 이름에 해당하는 전자제품 검색
     */
    public void searchByModelName(String modelName) {
        try{
            Electronics electronics = service.searchByModelName(modelName);
            printSearchByModelName(electronics);
            printMessage(modelName + " 이라는 모델이름 검색 성공");

        }catch (SearchNotFoundException e){
            errorMessage(e.getMessage());
            printMessage(modelName + " 이라는 모델이름 검색 실패");

        }
    }
}











