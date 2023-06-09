package com.javaclass.Springboot.learning.controller;

import com.javaclass.Springboot.learning.entity.Department;
import com.javaclass.Springboot.learning.error.DepartmentNotFoundException;
import com.javaclass.Springboot.learning.service.DepartmentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;


import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@WebMvcTest(DepartmentController.class)
class DepartmentControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private DepartmentService departmentService;
    private Department outputDepartment;
    @BeforeEach
    void setUp() {
        outputDepartment = Department.builder()
                .departmentAddress("Bangalore")
                .departmentCode("36")
                .departmentName("CSE")
                .departmentId(1l)
                .build();
    }

    @Test
    void saveDepartment() throws Exception {
       Department  inputDepartment = Department.builder()
                .departmentAddress("Bangalore")
                .departmentCode("36")
                .departmentName("CSE")
                .departmentId(1l)
                .build();
       Mockito.when(departmentService.saveDepartment(inputDepartment)).thenReturn(outputDepartment);
       mockMvc.perform(MockMvcRequestBuilders.post("/departments")
               .contentType(MediaType.APPLICATION_JSON)
               .content("{\n" +
                       "    \"departmentAddress\":\"Bangalore\",\n" +
                       "    \"departmentName\":\"CSE\",\n" +
                       "    \"departmentCode\":\"36\"\n" +
                       "}")).andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void fetchDepartmentById() throws Exception {
        Mockito.when(departmentService.fetchDepartmentById(1l))
                .thenReturn(outputDepartment);
        mockMvc.perform(MockMvcRequestBuilders.get("/departments/1")
                .contentType(MediaType.APPLICATION_JSON)).andExpect(jsonPath("$.departmentName").value(outputDepartment.getDepartmentName()));
    }
}