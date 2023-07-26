package mvc.controller;

import mvc.dto.Electronics;
import mvc.exception.ElectronicsArrayBoundsException;
import mvc.exception.SearchNotFoundException;
import mvc.service.ElectronicsService;
import mvc.service.ElectronicsServiceImpl;

import java.util.List;


/**
 * View와 Model 사이에서 중간 역할 
 *  : 사용자의 요청을 받아서 그에 해당하는 서비스를 호출하고
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

        for(int i = 0; i < electronicsList.size(); i++){
            System.out.println(electronicsList.get(i));
        }

        return electronicsList;
    }
 

	 /**
     * 전자제품 등록 
     */
   
    public void insert(Electronics electronics) throws ElectronicsArrayBoundsException {

        service.insert(electronics);
       
    }
    
    

    /**
     * 모델번호에 해당하는 전자제품 검색
     * @param modelNo
     */
    public void searchByModelNo(int modelNo) throws SearchNotFoundException {

        Electronics electronics = service.searchByModelNo(modelNo);

        System.out.println(electronics.toString());
    	
    } 

    /**
     * 모델번호에 해당하는 전자제품 수정하기 
     * @param electronics
     */
    public void update(Electronics electronics) throws SearchNotFoundException {
        service.update(electronics);
        System.out.println(electronics.toString());
    	
    }
    
    /**
     * 모델번호에 해당하는 전자제품 삭제하기 
     * @param electronics
     */
	public void deleteModelNo(int modelNo) {
		
	}
    
}











