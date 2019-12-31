package com.bankstatementprocessor.application.controller;

import com.bankstatementprocessor.application.exception.InvalidRequestException;
import com.bankstatementprocessor.application.exception.NoReportFoundException;
import com.bankstatementprocessor.application.model.ResultTransactionRecord;
import com.bankstatementprocessor.application.model.ViewParameterType;
import com.bankstatementprocessor.application.service.BankStatementProcessorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import java.io.File;
import java.util.List;
import java.util.logging.Logger;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@RestController
public class BankStatementProcessorApplicationController {

    private static final String REQUEST_PATH= "bank/statement/processor/transactionreport";
    private static final String APPLICATION_JSON = "application/json";
    private static final String NO_FILES_FOUND_ERROR_DESCRIPTION = "No files were found for processing.";
    private static final String INVALID_VALUE_FOR_VIEW_PARAMETER = "Invalid values for View Parameter:";
    private static final String VIEW_PARAM = "view";

    @Autowired
    private BankStatementProcessorService bankStatementProcessorService;
    @Value( "${STATEMENTS_DIRECTORY}" )
    String statementsDir;
    static Logger logger = Logger.getLogger(BankStatementProcessorApplicationController.class.getName());

    @RequestMapping(method = GET, value = REQUEST_PATH, produces = APPLICATION_JSON)
    public ResponseEntity<?> getProcessedBankStatementsReport(
            @RequestParam(value = VIEW_PARAM) ViewParameterType viewParameterType) {

        File folder = new File(getClass().getClassLoader().getResource(statementsDir).getFile());
        File[] listOfFiles = folder == null ? null : folder.listFiles();
        if(listOfFiles.length == 0) {
            throw new InvalidRequestException(NO_FILES_FOUND_ERROR_DESCRIPTION);
        }

        if(viewParameterType != null) {
            if(viewParameterType != ViewParameterType.ALL && viewParameterType != ViewParameterType.INVALID && viewParameterType != ViewParameterType.VALID){
                throw new InvalidRequestException(INVALID_VALUE_FOR_VIEW_PARAMETER + viewParameterType);
            }
        }

        List<ResultTransactionRecord> resultTransactionRecords;
        resultTransactionRecords = bankStatementProcessorService.process(listOfFiles, viewParameterType);

        if(resultTransactionRecords == null || resultTransactionRecords.isEmpty()) {
            throw new NoReportFoundException("No report found");
        }

        return ResponseEntity.ok().body(resultTransactionRecords);

        }
    }




