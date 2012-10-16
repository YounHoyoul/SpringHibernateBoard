package com.hoyoul.board;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.ModelAndView;

import com.hoyoul.board.dto.TempVO;
import com.hoyoul.board.service.TempService;

//@Controller
public class CommonController {
	
	//@Autowired
	private TempService tempService;
	
	public String getTemp(int id, ModelAndView model) {
		
		model.addObject("user",tempService.getTemp(id));
		model.setViewName("temp/temp");
		
		return "temp/temp";
	}

	public String insertTemp(TempVO temp, ModelAndView model) {
		
		tempService.insertTemp(temp);
		model.setViewName("temp/temp");
		
		return "temp/temp";
	}

	public String updateTemp(TempVO temp, ModelAndView model) {
		
		tempService.updateTemp(temp);
		model.setViewName("temp/temp");
		
		return "temp/temp";
	}

	public String deleteTemp(TempVO temp, ModelAndView model) {
		
		tempService.deleteTemp(temp);
		model.setViewName("temp/temp");
		
		return "temp/temp";
	}

	public String getListTemp(ModelAndView model) {
		
		model.addObject("list",tempService.getListTemp());
		
		model.setViewName("temp/tempList");
		
		return "temp/tempList";
		
	}

}
