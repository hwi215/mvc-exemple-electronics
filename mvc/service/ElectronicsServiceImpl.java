package mvc.service;

import java.util.ArrayList;
import java.util.List;


import mvc.dto.Electronics;
import mvc.exception.ElectronicsArrayBoundsException;
import mvc.exception.SearchNotFoundException;

/**
 * 전자제품에 관련된 기능을 담당할 클래스
 */

public class ElectronicsServiceImpl implements ElectronicsService {
	
	String [][] data = new String [][]{
		{"100","선풍기","35000","삼성 선풍기"},
		{"200","전자렌지","55000","잘 데워져요.."},
		{"300","에어컨","5500000","무풍 에어컨 너무 시원해"},
		{"400","냉장고","800000","LG 냉장고"},
		{"500","드라이기","9000","LG 드라이기"}
	}; // 최초의 초기치 데이터를 준비!! electronics에 저장 
	
	
	private static ElectronicsService instance = new ElectronicsServiceImpl();
    private static final int MAX_SIZE=10; // 최대 10개로 제한
    List<Electronics> list = new ArrayList<Electronics>();
    
    /** 
     * 외부에서 객체 생성안됨. 
     * 생성자안에서 위에 2차원 배열의 데이터를 List에 추가하여
     * 초기치 데이터를 만든다. -> 싱글톤패턴 !!
     * 
     */
    private ElectronicsServiceImpl() {
		for(int i = 0; i < data.length; i++){
			int modelNo= Integer.parseInt(data[i][0]);
			String modelName = data[i][1];
			int modePrice = Integer.parseInt(data[i][2]);
			String modelDetail = data[i][3];
			System.out.println(modelDetail);

			list.add(new Electronics(modelNo,modelName, modePrice, modelDetail));
			System.out.println(list.toString());

		}
      
    }

	public static ElectronicsService getInstance() {
		return instance;
	}

	@Override
	public void insert(Electronics electronics) throws ElectronicsArrayBoundsException {

		if(list.size() > MAX_SIZE){
			new ElectronicsArrayBoundsException("배열의 길이를 벗어나 더이상 등록 할수 없습니다.");
		}

		// 10개 이내면 등록
		new ElectronicsServiceImpl();
		System.out.println("insert 성공");

	}

	/**
	 * 등록된 전체 전자제품 검색
	 */
	@Override
	public List<Electronics> selectAll() {
		return list;
	}

	@Override
	public Electronics searchByModelNo(int modelNo) throws SearchNotFoundException {
		// TODO Auto-generated method stub
		for(int i = 0; i < list.size(); i++){
			if(list.get(i).getModelNo() == modelNo){
				return list.get(i);
			}
		}
		throw new SearchNotFoundException(modelNo+ "는 없는 모델번호로 검색할수 없습니다.");

	}

	@Override
	public void update(Electronics electronics) throws SearchNotFoundException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(int modelNo) throws SearchNotFoundException {
		// TODO Auto-generated method stub
		
	}
    
} // 클래스 끝 