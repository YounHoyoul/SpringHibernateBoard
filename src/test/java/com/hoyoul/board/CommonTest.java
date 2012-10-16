package com.hoyoul.board;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.web.servlet.ModelAndView;

import com.hoyoul.board.dto.TempVO;
import com.hoyoul.board.service.TempService;

@RunWith(MockitoJUnitRunner.class)
public class CommonTest {
	
	@Mock
	private TempService tempService;
	
	@InjectMocks
	private CommonController commonController = new CommonController();

	private ModelAndView model;
	
	private static ApplicationContext context;

	@Before
	public void setup(){
		model = new ModelAndView();
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void getTemp(){
		
		int id = 1;
		TempVO temp = new TempVO();
		when(tempService.getTemp(id)).thenReturn(temp);
		
		String viewName = commonController.getTemp(id,model);
		
		verify(tempService).getTemp(id);
		assertEquals(viewName,"temp/temp");
		assertThat(model.getViewName(),is("temp/temp"));
		
	}
	
	@Test
	public void insertTemp(){
		TempVO temp = new TempVO();
		temp.setId(1);
		temp.setName("bluepoet");
		
		when(tempService.insertTemp(temp)).thenReturn(1);
		
		String viewName = commonController.insertTemp(temp,model);
		
		verify(tempService).insertTemp(temp);
		assertEquals(viewName,"temp/temp");
		assertThat(model.getViewName(),is("temp/temp"));
		
	}
	
	@Test
	public void updateTemp(){
		TempVO temp = new TempVO();
		temp.setId(3);
		temp.setName("bluepoet");
		
		when(tempService.updateTemp(temp)).thenReturn(1);
		
		String viewName = commonController.updateTemp(temp,model);
		
		verify(tempService).updateTemp(temp);
		assertEquals(viewName,"temp/temp");
		assertThat(model.getViewName(),is("temp/temp"));
		
	}
	
	@Test
	public void deleteTemp(){
		TempVO temp = new TempVO();
		temp.setId(1);
		temp.setName("bluepoet");
		
		when(tempService.deleteTemp(temp)).thenReturn(1);
		
		String viewName = commonController.deleteTemp(temp,model);
		
		verify(tempService).deleteTemp(temp);
		assertEquals(viewName,"temp/temp");
		assertThat(model.getViewName(),is("temp/temp"));
		
	}
	
	@Test
	public void getListTemp(){
		
		List<TempVO> list = new ArrayList<TempVO>();
		
		when(tempService.getListTemp()).thenReturn(list);
		
		String viewName = commonController.getListTemp(model);
		
		verify(tempService).getListTemp();
		assertEquals(viewName,"temp/tempList");
		assertThat(model.getViewName(),is("temp/tempList"));
		
	}
}
