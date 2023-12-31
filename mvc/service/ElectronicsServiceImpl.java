package mvc.service;

import java.util.ArrayList;
import java.util.List;


import mvc.dto.Electronics;
import mvc.exception.ElectronicsArrayBoundsException;
import mvc.exception.SearchNotFoundException;

import static mvc.view.SuccessView.printMessage;

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
	public void insert(Electronics electronics) throws ElectronicsArrayBoundsException, SearchNotFoundException {

		if(list.size() == MAX_SIZE){ // 하나 더 추가하면 배열 초과
			throw new ElectronicsArrayBoundsException("배열의 길이를 벗어나 더이상 등록 할수 없습니다.");
		}

		// 10개 이내면 등록

		// 동일한 제품이 없으면 등록
		try {
			this.searchByModelNo(electronics.getModelNo());
			throw new ElectronicsArrayBoundsException("모델번호 오류로 등록할 수 없어요.");
		}catch (SearchNotFoundException e){
			list.add(electronics);
			printMessage("insert 성공");

		}



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
		int modelNo = electronics.getModelNo();
		Electronics targetModelNoElectronics = searchByModelNo(modelNo); // 모델번호에 해당하는 전자제품 찾기

		// 찾았으면, 수정하기
		targetModelNoElectronics.setModelDetail(electronics.getModelDetail()); // 설명 수정
		//System.out.println("설명 수정 완료");
		printMessage("설명 수정 완료");
		
	}

	@Override
	public void delete(int modelNo) throws SearchNotFoundException {
		// TODO Auto-generated method stub
		Electronics targetModelNoElectronics = searchByModelNo(modelNo); // 모델번호에 해당하는 전자제품 찾기

		// 찾았으면 삭제하기
		for(int i = 0; i < list.size(); i++){
			if(list.get(i).getModelNo() == modelNo){
				list.remove(i); // 삭제B
				printMessage("삭제 완료");
				break;
			}
		}
		
	}

	@Override
	public Electronics searchByModelName(String modelName) throws SearchNotFoundException {
		for(Electronics electronics : list){
			if(electronics.getModelName().equals(modelName)){
				return electronics;
			}
		}
		throw new SearchNotFoundException(modelName + "해당 모델이름을 찾을 수 없습니다.");
	}

	/**
	 * 가격의 최소값과 최대값 사이에 존재하는 전자제품 검색
	 */
	@Override
	public ArrayList<Electronics> searchByPriceRange(int min, int max) throws SearchNotFoundException {
		ArrayList<Electronics> searchList = new ArrayList<>();
		for(Electronics electronics : list){
			if(electronics.getModelPrice() >= min && electronics.getModelPrice() <= max){
				searchList.add(electronics);
			}
		}
		if(searchList.size() != 0) {
			return searchList;
		}
		throw new SearchNotFoundException("해당 가격 범위의 전자제품을 찾을 수 없습니다.");
	}


} // 클래스 끝 