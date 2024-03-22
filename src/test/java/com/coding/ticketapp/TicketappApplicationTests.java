package com.coding.ticketapp;

import com.coding.ticketapp.Model.Seat;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
class TicketappApplicationTests {

    @Autowired
    private MockMvc mockMvc;

    private ObjectMapper objectMapper;

    @BeforeEach
    public void setup() {
        objectMapper = new ObjectMapper();
    }

    @Test
    void contextLoads() {
        TicketappApplication.main(new String[]{});
    }

    @Test
    void testPurchase() throws Exception {
        String inputForPurchase = "{\n" +
                "    \"from\": \"London\",\n" +
                "    \"to\": \"France\",\n" +
                "    \"user\": {\n" +
                "        \"firstName\": \"Lalit\",\n" +
                "        \"lastName\": \"Kumar2\",\n" +
                "        \"email\": \"lalit@gmail.com\"\n" +
                "    }\n" +
                "}";
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/ticketing/purchase")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(inputForPurchase))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andReturn();

        String responseBody = result.getResponse().getContentAsString();
        Assertions.assertNotNull(responseBody);
        Assertions.assertTrue(responseBody.contains("seatNumber"));
    }

    @Test
    void testReceiptByEmail() throws Exception {
        //Please run all unit test in one go , because input if dependent on others
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/users/receipt/lalit@gmail.com"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andReturn();

        String responseBody = result.getResponse().getContentAsString();
        Assertions.assertNotNull(responseBody);
        Assertions.assertTrue(responseBody.contains("seatNumber"));
        Assertions.assertTrue(responseBody.contains("pricePaid"));
        Assertions.assertTrue(responseBody.contains("lalit@gmail.com"));
    }

    @Test
    void testModifyUserSeat() throws Exception {
        // Mocked seat data
        String seatJson = "{\n" +
                "    \"section\": \"B\",\n" +
                "    \"seatNumber\": \"22\"\n" +
                "}";

        mockMvc.perform(MockMvcRequestBuilders.put("/users/modify/lalit@gmail.com")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(seatJson))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("User seat modified successfully"));
    }


}
