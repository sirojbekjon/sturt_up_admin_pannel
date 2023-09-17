package io.getarrays.start_up_admin.service;

import io.getarrays.start_up_admin.entity.FileUpload;
import io.getarrays.start_up_admin.entity.News;
import io.getarrays.start_up_admin.payload.NewsDto;
import io.getarrays.start_up_admin.repository.FileUploadRepository;
import io.getarrays.start_up_admin.repository.NewsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NewsService {

    @Autowired
    NewsRepository newsRepository;

    @Autowired
    FileUploadRepository fileUploadRepository;

    public HttpEntity<?> addNews(NewsDto newsDto) {
        Optional<FileUpload> optionalFileUpload = fileUploadRepository.findById(newsDto.getFileUploadId());
        if (optionalFileUpload.isPresent()){
            FileUpload fileUpload = optionalFileUpload.get();
            News news = new News(
                    newsDto.getName(),
                    newsDto.getText(),
                    fileUpload
            );
            News save = newsRepository.save(news);
            return ResponseEntity.status(200).body(save);
        }return ResponseEntity.status(409).body("Not saved");
    }

    public HttpEntity<?> getNews() {
        List<News> newsList = newsRepository.findAll();
        return ResponseEntity.status(200).body(newsList);
    }

    public HttpEntity<?> editNews(Long id, NewsDto newsDto) {
        Optional<FileUpload> optionalFileUpload = fileUploadRepository.findById(newsDto.getFileUploadId());
        Optional<News> optionalNews = newsRepository.findById(id);
        if (optionalNews.isPresent() && optionalFileUpload.isPresent()){
            FileUpload fileUpload = optionalFileUpload.get();
            News news = optionalNews.get();

            news.setName(newsDto.getName());
            news.setText(newsDto.getText());
            news.setFileUploadList(fileUpload);
            News savedNews = newsRepository.save(news);
            return ResponseEntity.status(200).body(savedNews + " saved successfully");
        }return ResponseEntity.status(404).body("Not Found");
    }

    public HttpEntity<?> deleteNews(Long id) {
        newsRepository.deleteById(id);
        Optional<News> optionalNews = newsRepository.findById(id);
        if (!optionalNews.isPresent()){
            return ResponseEntity.status(200).body("Deleted");
        }return ResponseEntity.status(404).body("Not deleted");
    }
}
