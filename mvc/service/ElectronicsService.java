package mvc.service;

import java.util.ArrayList;
import java.util.List;

import mvc.dto.Electronics;
import mvc.exception.ElectronicsArrayBoundsException;
import mvc.exception.SearchNotFoundException;

public interface ElectronicsService {

    /**
     * 전자제품 등록
     *
     * @param electronics
     * 
     * : 최대 List에 저장된 객체의 개수가 MAX-SIZE를 벗어나면 
     *    ElectronicsArrayBoundsException 예외발생
     *      - 예외메시지 : 배열의 길이를 벗어나 더이상 등록 할수 없습니다.
     * 
     */
    public void insert(Electronics electronics) throws ElectronicsArrayBoundsException, SearchNotFoundException;

    /**
     * 등록된 전체 전자제품 검색 
     * @return
     */
    public List<Electronics> selectAll() ;
    
    /**
     * 모델번호에 해당하는 전자제품 검색 
     * @param modelNo
     * @return
     *   : 만약 찾는 정보가 없으면 
     *      SearchNotFoundException 예외발생
     *       -예외메시지 : modelNo+"는 없는 모델번호로 검색할수 없습니다."
     */
    public Electronics searchByModelNo(int modelNo)throws SearchNotFoundException ;


    /**
     * 모델번호에 해당하는 전자제품 수정하기 
     * (설명만 수정한다)
     * @param electronics
     *  : 수정전에 모델번호에 해당하는 정보가 있는지를 찾고 없으면
     *     SearchNotFoundException 예외발생
     *     있으면 전자제품 모델번호에 해당하는 설명을 수정한다.
     */
    public void update(Electronics electronics) throws SearchNotFoundException;
    
    
    /**
     * 모델번호에 해당하는 전자제품 삭제하기
     * 
     * @param 모델번호
     *  :삭제전에 모델번호에 해당하는 정보가 있는지를 찾고 없으면
     *     SearchNotFoundException 예외발생
     *     있으면 전자제품 모델번호에 해당하는 정보를 삭제한다.
     */
    public void delete(int modelNo) throws SearchNotFoundException;


    /**
     * 모델 이름에 해당하는 전자제품 검색
     * @Param 모델이름
     *  : 전자제품에 해당 모델 이름이 존재하지 않으면
     *     SearchNotFoundException 예외발생
     *
     */
    Electronics searchByModelName(String modelName) throws SearchNotFoundException;

    /**
     * 가격의 최소값과 최대값 사이에 존재하는 전자제품 검색
     * @Param 최소값, 최대값
     *  : 해당 비용 범위에 존재하는 전자제품이 않으면
     *     SearchNotFoundException 예외발생
     *
     */
    ArrayList<Electronics> searchByPriceRange(int min, int max) throws SearchNotFoundException;

}



