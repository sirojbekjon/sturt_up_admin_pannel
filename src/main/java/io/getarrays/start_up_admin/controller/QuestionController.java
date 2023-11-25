package io.getarrays.start_up_admin.controller;

import io.getarrays.start_up_admin.entity.PartOfTheme;
import io.getarrays.start_up_admin.entity.Question;
import io.getarrays.start_up_admin.entity.Subjects;
import io.getarrays.start_up_admin.entity.Teacher;
import io.getarrays.start_up_admin.repository.PartOfThemeRepository;
import io.getarrays.start_up_admin.repository.QuestionRepository;
import io.getarrays.start_up_admin.repository.SubjectRepository;
import io.getarrays.start_up_admin.security.CurrentUser;
import io.getarrays.start_up_admin.service.QuestionService;
import io.swagger.annotations.Api;
import org.apache.poi.ss.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import springfox.documentation.annotations.ApiIgnore;

import java.io.IOException;
import java.util.Iterator;
import java.util.Optional;

@RestController
@RequestMapping("/api/question")
public class QuestionController {

    @Autowired
    QuestionService questionService;
    @Autowired
    SubjectRepository subjectRepository;

    @Autowired
    PartOfThemeRepository partOfThemeRepository;

    @Autowired
    QuestionRepository questionRepository;


    @PreAuthorize("hasAuthority('ADD_POST')")
    @PostMapping("/add/{subjectId}/{partOfThemeId}")
    public HttpEntity<?> addQuestion(@PathVariable Long subjectId,@PathVariable Long partOfThemeId, @RequestBody MultipartFile file,@ApiIgnore @CurrentUser Teacher teacher){
        Workbook workbook = null;
        Optional<Subjects> optionalSubjects = subjectRepository.findById(subjectId);
        Optional<PartOfTheme> optionalPartOfTheme = partOfThemeRepository.findById(partOfThemeId);
            try {
                if (optionalPartOfTheme.isPresent() && optionalSubjects.isPresent()) {
                    Subjects subjects = optionalSubjects.get();
                    PartOfTheme partOfTheme = optionalPartOfTheme.get();
                    workbook = WorkbookFactory.create(file.getInputStream());
                    Sheet sheet = workbook.getSheetAt(0);
                    Iterator<Row> rows = sheet.iterator();
                    while (rows.hasNext()) {
                        Question question = new Question();
                        Row row = rows.next();
                        Iterator<Cell> cells = row.cellIterator();
                        while (cells.hasNext()) {
                            Cell cell = cells.next();
                            if (cell.getRowIndex() != 0) {
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
                        if (question.getText() != null)
                            question.setSubject(subjects);
                            question.setPartOfTheme(partOfTheme);
                            question.setTeacher(teacher);
                        questionRepository.save(question);
                    }
                }
            } catch (Exception e) {
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
            return ResponseEntity.status(202).body("Savollar saqlandi");
    }



    @PreAuthorize("hasAuthority('ADD_POST')")
    @DeleteMapping("/delete/{id}")
    public HttpEntity<?> deleteQuestionById(@PathVariable Long id){
        questionRepository.deleteById(id);
        Optional<Question> optionalQuestion = questionRepository.findById(id);
        if (!optionalQuestion.isPresent()){
            return ResponseEntity.status(202).body("deleted");
        }
        return ResponseEntity.status(409).body("not deleted");
    }

}
