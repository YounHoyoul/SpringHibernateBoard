package com.hoyoul.board.service;

import java.util.List;

import com.hoyoul.board.dto.TempVO;

public interface TempService {
	
	TempVO getTemp(int id);
	
	int insertTemp(TempVO temp);
	
	int updateTemp(TempVO temp);
	
	int deleteTemp(TempVO temp);
	
	List<TempVO> getListTemp();
	
}
