package io.getarrays.start_up.controller;

import io.getarrays.start_up.entity.Question;
import io.getarrays.start_up.repository.QuestionRepository;
import io.getarrays.start_up.service.QuestionService;
import org.apache.poi.ss.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Iterator;

@RestController
@RequestMapping("/api/question")
public class QuestionController {

    @Autowired
    QuestionService questionService;

    @Autowired
    QuestionRepository questionRepository;

    @PostMapping("/add")
    public HttpEntity<?> addQuestion(@RequestBody MultipartFile file){

        Workbook workbook = null;
        Question question = new Question();
        try {
            workbook = WorkbookFactory.create(file.getInputStream());
            Sheet sheet = workbook.getSheetAt(0);
            Iterator<Row> rows = sheet.iterator();
            while (rows.hasNext()) {
                Row row = rows.next();
                Iterator<Cell> cells = row.cellIterator();
                while (cells.hasNext()) {
                    Cell cell = cells.next();
                    if (cell.getRowIndex()!=0) {
                        int n = cell.getColumnIndex();
                        switch (n) {
                            case 1:
                                question.setText(String.valueOf(cell));
                                break;
                            case 2:
                                question.setKeyA(String.valueOf(cell));
                                break;
                            case 3:
                                question.setKeyB(String.valueOf(cell));
                                break;
                            case 4:
                                question.setKeyC(String.valueOf(cell));
                                break;
                            case 5:
                                question.setKeyD(String.valueOf(cell));
                                break;
                            case 6:
                                question.setAnsverKey(String.valueOf(cell));
                                break;
                        }
                    }
                }
                if (question.getText()!=null)
                    questionRepository.save(question);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (workbook != null) {
                try {
                    workbook.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return ResponseEntity.status(202).body(question.getId());
    }
}
