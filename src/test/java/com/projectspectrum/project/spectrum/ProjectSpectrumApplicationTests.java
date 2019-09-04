package com.projectspectrum.project.spectrum;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc

public class ProjectSpectrumApplicationTests {

	@Autowired
	MockMvc mockMvc;

	@Test
	public void contextLoads() {
	}

	@Test
	public void testIndexRoute_containsProjectSpectrum() throws Exception {
		this.mockMvc
			.perform(MockMvcRequestBuilders.get("/"))
			.andDo(MockMvcResultHandlers.print())
			.andExpect(MockMvcResultMatchers.status().isOk())
			.andExpect(MockMvcResultMatchers.content().string(
				org.hamcrest.Matchers.containsString("<h1 class=\"display-4 jumbo-title\">Project Spectrum</h1>")));
	}

	@Test
	public void testLogin_containsLogin() throws Exception {
		this.mockMvc
			.perform(MockMvcRequestBuilders.get("/login"))
			.andDo(MockMvcResultHandlers.print())
			.andExpect(MockMvcResultMatchers.status().is(200))
			.andExpect(MockMvcResultMatchers.content().string(
				org.hamcrest.Matchers.containsString("<h1>Login</h1>")));
	}

	@Test
	public void testErrorPage_containsSorry() throws Exception {
		this.mockMvc
			.perform(MockMvcRequestBuilders.get("/error"))
			.andDo(MockMvcResultHandlers.print())
			.andExpect(MockMvcResultMatchers.status().is(500))
			.andExpect(MockMvcResultMatchers.content().string(
				org.hamcrest.Matchers.containsString("status\":999,\"error\":\"None\",\"message\":\"No message available")));
	}

	@Test
	public void testSignUpPage_containsSignUp() throws Exception {
		this.mockMvc
			.perform(MockMvcRequestBuilders.get("/signup"))
			.andDo(MockMvcResultHandlers.print())
			.andExpect(MockMvcResultMatchers.status().isOk())
			.andExpect(MockMvcResultMatchers.content().string(
				org.hamcrest.Matchers.containsString("<h1>Sign Up</h1>")));
	}


}

