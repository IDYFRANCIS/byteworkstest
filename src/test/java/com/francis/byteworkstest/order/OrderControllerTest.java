package com.francis.byteworkstest.order;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.francis.byteworkstest.ByteworkstestApplication;
import com.francis.byteworkstest.dto.OrderDto;
import com.francis.byteworkstest.enumType.DeliveryMethod;
import com.francis.byteworkstest.enumType.FoodType;
import com.francis.byteworkstest.enumType.PaymentType;
import com.google.gson.Gson;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT,
classes = ByteworkstestApplication.class)
@AutoConfigureMockMvc
public class OrderControllerTest {

	@Autowired
	MockMvc mockMvc;
		
	Gson gson;
	
	//Note: make sure you acquired authorization token from login for proper testing
	String authorization = "Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJhdWQiOlsidXNlci13ZWItc2VydmljZSJdLCJ1c2VyX25hbWUiOiJyZWdpbmFmcmFuY2lzODNAZ21haWwuY29tIiwic2NvcGUiOlsicmVhZCIsIndyaXRlIl0sImV4cCI6MTU4MDQwMTE4OCwiYXV0aG9yaXRpZXMiOlsiaXNVc2VyIl0sImp0aSI6IjZjZmFmZGI5LWRjOTgtNGJlNC1hNTY5LTFiNGYyMzg3ZGM3NCIsImNsaWVudF9pZCI6InVzZXIifQ.nzqwZS61usvt5yjp1PkrGUil8xR1MLNuwgmtQ4mtcTA";
	
	@Before
	  public void setup() {
		
		gson = new Gson();
	  }
	
	@Test
	public void create() throws Exception{
		
		String developerCode = "DEV1580358070165";
		long quantity = 1;

		OrderDto request = new OrderDto();
		request.setDeveloperCode(developerCode);
		request.setQuantity(quantity);
		
		String json = gson.toJson(request);
		
		ResultActions mvcResult = mockMvc.perform(
				MockMvcRequestBuilders.post( request + "/order/ON_DELIVERY/JELLOF_RICE/Office_Delivery")
				.contentType(MediaType.APPLICATION_JSON)
				.header("Authorization", authorization)
				.content(json)
					.accept(MediaType.APPLICATION_JSON)
				).andExpect(status().isOk())
				.andExpect(content().json("{\"success\":true}"));
		
		System.out.println(gson.toJson(mvcResult.andReturn().getResponse().getContentAsString()));
	}
	
	@Test
	public void getAllOrders() throws Exception{
		
		ResultActions mvcResult = mockMvc.perform(
				MockMvcRequestBuilders.get("/order")
				.contentType(MediaType.APPLICATION_JSON)
				.header("Authorization", authorization)
					.accept(MediaType.APPLICATION_JSON)
				).andExpect(status().isOk())
				.andExpect(content().json("{\"success\":true}"));
		
		System.out.println(gson.toJson(mvcResult.andReturn().getResponse().getContentAsString()));
	}
	
	@Test
	public void getOrderByFoodType()throws Exception {
		FoodType food = null;
		ResultActions mvcResult = mockMvc.perform(
				MockMvcRequestBuilders.get("/order/foodType/"+ FoodType.JELLOF_RICE)
				.contentType(MediaType.APPLICATION_JSON)
				.header("Authorization", authorization)
					.accept(MediaType.APPLICATION_JSON)
				).andExpect(status().isOk())
				.andExpect(content().json("{\"success\":true}"));
		
		System.out.println(gson.toJson(mvcResult.andReturn().getResponse().getContentAsString()));
	}
	
	
	
	@Test
	public void getOrderByPaymentType()throws Exception {
		PaymentType payment = null;
		ResultActions mvcResult = mockMvc.perform(
				MockMvcRequestBuilders.get("/order/paymentType/"+ PaymentType.ON_DELIVERY)
				.contentType(MediaType.APPLICATION_JSON)
				.header("Authorization", authorization)
					.accept(MediaType.APPLICATION_JSON)
				).andExpect(status().isOk())
				.andExpect(content().json("{\"success\":true}"));
		
		System.out.println(gson.toJson(mvcResult.andReturn().getResponse().getContentAsString()));
	}
	
	
	
	@Test
	public void getOrderByDeliveryMethod()throws Exception {
		DeliveryMethod delivery = null;
		ResultActions mvcResult = mockMvc.perform(
				MockMvcRequestBuilders.get("/order/deliveryType/"+ DeliveryMethod.Office_Delivery)
				.contentType(MediaType.APPLICATION_JSON)
				.header("Authorization", authorization)
					.accept(MediaType.APPLICATION_JSON)
				).andExpect(status().isOk())
				.andExpect(content().json("{\"success\":true}"));
		
		System.out.println(gson.toJson(mvcResult.andReturn().getResponse().getContentAsString()));
	}
	
	
	
	@Test
	public void getOrderByOrderNumber()throws Exception {
		String orderNumber = "ORDER1580358105244";
		ResultActions mvcResult = mockMvc.perform(
				MockMvcRequestBuilders.get("/order/"+ orderNumber)
				.contentType(MediaType.APPLICATION_JSON)
				.header("Authorization", authorization)
					.accept(MediaType.APPLICATION_JSON)
				).andExpect(status().isOk())
				.andExpect(content().json("{\"success\":true}"));
		
		System.out.println(gson.toJson(mvcResult.andReturn().getResponse().getContentAsString()));
	}
	
}
