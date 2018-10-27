package com.dincrash.service;

import com.dincrash.entities.DeloDocument;
import com.dincrash.entities.IndexTable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Date;

@Component("SaveDocument")
public class SaveDocument{

    @Autowired DocumentService documentService;
    public void saveDocument(FileBucket fileBucket, IndexTable indexTable) throws IOException {

        DeloDocument document = new DeloDocument();


        MultipartFile[] multipartFile = fileBucket.getFile();
        for (MultipartFile multipartFile1 : multipartFile) {
            document.setName(multipartFile1.getOriginalFilename());
            document.setContent(multipartFile1.getBytes());
            document.setDateload(new Date());
            document.setIndexTable(indexTable);
            documentService.create(document);
        }
    }

}