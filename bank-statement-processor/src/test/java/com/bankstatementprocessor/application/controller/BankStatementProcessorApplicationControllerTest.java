package com.bankstatementprocessor.application.controller;

import com.bankstatementprocessor.application.BankStatementProcessorApplication;
import com.bankstatementprocessor.application.model.ResultTransactionRecord;
import com.bankstatementprocessor.application.model.ViewParameterType;
import com.bankstatementprocessor.application.service.BankStatementProcessorService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.internal.matchers.Any;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.io.File;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = { BankStatementProcessorApplication.class })
@AutoConfigureMockMvc
@EnableAutoConfiguration
public class BankStatementProcessorApplicationControllerTest {

    private static final String URL = "bank/statement/processor/transactionreport";

    @Autowired
    private MockMvc mockMvc;
    @InjectMocks
    BankStatementProcessorApplicationController bankStatementProcessorApplicationController;
    @Mock
    BankStatementProcessorService bankStatementProcessorService;

    @Test
    public void testController() throws Exception{
        this.mockMvc.perform(get(URL)).andExpect(status().isNotFound());
    }


}
