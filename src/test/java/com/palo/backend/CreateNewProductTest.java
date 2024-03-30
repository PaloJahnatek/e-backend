package com.palo.backend;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.math.BigDecimal;

import com.palo.backend.dto.ProductDTO;
import org.hamcrest.core.Is;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc
class CreateNewProductTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void createNewProductSuccess() throws Exception {

        ProductDTO productDTO = new ProductDTO();
        productDTO.setImage("myImage.jpg");
        productDTO.setName("myProductName");
        productDTO.setPrice(BigDecimal.valueOf(10));
        String testData = objectMapper.writeValueAsString(productDTO);

        mockMvc.perform(MockMvcRequestBuilders.post("/products")
                        .content(testData)
                        .contentType(MediaType.APPLICATION_JSON))

                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", Is.is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name", Is.is(productDTO.getName())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.image", Is.is(productDTO.getImage())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.price", Is.is(10)));

    }

    @Test
    void createNewProductFailedBecauseInvalidImageName() throws Exception {

        ProductDTO productDTO = new ProductDTO();
        productDTO.setImage(null);
        productDTO.setName("myProductName");
        productDTO.setPrice(BigDecimal.valueOf(10));
        String s = objectMapper.writeValueAsString(productDTO);

        mockMvc.perform(MockMvcRequestBuilders.post("/products")
                        .content(s)
                        .contentType(MediaType.APPLICATION_JSON))

                .andExpect(MockMvcResultMatchers.status().is4xxClientError());
    }
}

