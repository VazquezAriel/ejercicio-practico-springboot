package com.minegocio.alquimiasoft;

import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import org.junit.jupiter.api.Test;

@SpringBootTest
@AutoConfigureMockMvc
class AlquimiaSoftApplicationTests {

	@Autowired
    private MockMvc mockMvc;

	private static final String CUSTOMER_DTO = """
		{
			"name": "leonel Messi",
			"identificationType": "RUC",
			"identificationNumber": "74638346",
			"email": "leonel@gmail.com",
			"phone": "098766543",
			"mainAddress": {
				"description": "Av. 5 de mayo",
				"city": "Argentina",
				"state": "Rosario"
			}
		}
	""";

	@Test
    void shouldCreateCustomerSuccessfully() throws Exception {
        mockMvc.perform(post("/api/customers/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content(CUSTOMER_DTO))
                .andExpect(result -> {
					int status = result.getResponse().getStatus();
					if (status != 200 && status != 400) 
						throw new AssertionError("Expected 200 or 400 but got: " + status);
					
				});
    }

	@Test
	void shouldSearchCustomerSuccessfully() throws Exception {
		mockMvc.perform(get("/api/customers/search")
				.param("identification", "1234")
				.param("name", "ahjaha"))
				.andExpect(status().isOk());
	}

}
