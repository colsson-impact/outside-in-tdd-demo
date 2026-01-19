package com.impact.outside_in_tdd_demo.contract;



import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.jayway.jsonpath.JsonPath;


@SpringBootTest
@AutoConfigureMockMvc
public class ContractContainerTest {

    @Autowired
    private MockMvc mockMvc;


    @Test
    void proposeContract() throws Exception {
        String contractJson = """
                {
                    "brandId": "0ffeeb95-eb61-443a-98ea-5ba61f532bc4",
                    "partnerId": "6ea4bf54-a8cc-4db5-b4d5-b6dac5154caf",
                    "commissionPercentage": 10.5
                }
                """;


        mockMvc.perform(post("/contracts").contentType(MediaType.APPLICATION_JSON).content(contractJson)).andExpect(status().isOk()).andExpect(jsonPath("$.id").exists());

    }

    @Test
    void proposeAndRetrieveContract() throws Exception {
        String contractJson = """
                {
                    "brandId": "0ffeeb95-eb61-443a-98ea-5ba61f532bc4",
                    "partnerId": "6ea4bf54-a8cc-4db5-b4d5-b6dac5154caf",
                    "commissionPercentage": 10.5
                }
                """;



        MvcResult result = mockMvc.perform(post("/contracts").contentType(MediaType.APPLICATION_JSON).content(contractJson)).andExpect(status().isOk()).andReturn();

        String contractId = JsonPath.parse(result.getResponse().getContentAsString()).read("$.id");


        mockMvc.perform(get("/contracts/" + contractId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(contractId))
                .andExpect(jsonPath("$.brandId").value("0ffeeb95-eb61-443a-98ea-5ba61f532bc4"))
                .andExpect(jsonPath("$.partnerId").value("6ea4bf54-a8cc-4db5-b4d5-b6dac5154caf"))
                .andExpect(jsonPath("$.commissionPercentage").value(10.5));

    }

    @Test
    void contractCommission() throws Exception {
        String contractJson = """
                {
                    "brandId": "0ffeeb95-eb61-443a-98ea-5ba61f532bc4",
                    "partnerId": "6ea4bf54-a8cc-4db5-b4d5-b6dac5154caf",
                    "commissionPercentage": 10.5
                }
                """;

        MvcResult result = mockMvc.perform(post("/contracts").contentType(MediaType.APPLICATION_JSON).content(contractJson)).andExpect(status().isOk()).andReturn();

        String contractId = JsonPath.parse(result.getResponse().getContentAsString()).read("$.id");

        mockMvc.perform(get("/contracts/" + contractId + "/commission").param("saleAmount", "1000").param("currency", "SEK"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.commission").exists());

    }


}
