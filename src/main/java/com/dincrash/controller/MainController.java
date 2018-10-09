package com.dincrash.controller;


import com.dincrash.Config.FileBucket;
import com.dincrash.Config.FileValidator;
import com.dincrash.entities.DeloDocument;
import com.dincrash.entities.IndexTable;
import com.dincrash.service.DocumentService;
import com.dincrash.service.IndexTableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.FileCopyUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;

@Controller
public class MainController {

    @Autowired
    private IndexTableService indexTableService;

    @Autowired
    private DocumentService documentService;

    @Autowired
    FileValidator fileValidator;

    @InitBinder("fileBucket")
    protected void initBinder(WebDataBinder binder) {
        binder.setValidator(fileValidator);
    }

    @RequestMapping(value = {"/"}, method = RequestMethod.GET)
    public String Index() {

        return "index";

    }

    @RequestMapping(value = {"/ActiveClients"}, method = RequestMethod.GET)
    public String ActiveClients(ModelMap modelMap) {

        modelMap.put("indexTables", indexTableService.listTable());

        return "ActiveClients";

    }


    @RequestMapping(value = {"/ArchiveClients"}, method = RequestMethod.GET)
    public String ArchiveClients() {

        return "ArchiveClients";

    }


    @RequestMapping(value = "add", method = RequestMethod.GET)
    public String add(ModelMap modelMap) {

        modelMap.put("indexTable", new IndexTable());
        return "add";
    }

    @RequestMapping(value = "add", method = RequestMethod.POST)
    public String add(@ModelAttribute("indexTable") IndexTable indexTable) {

        indexTableService.create(indexTable);
        return "redirect:/ActiveClients";
    }


    @RequestMapping(value = "edit/{id}", method = RequestMethod.GET)
    public String edit(@PathVariable("id") int id, ModelMap modelMap, ModelMap model) {
        modelMap.put("indexTable", indexTableService.find(id));

        IndexTable user = indexTableService.find(id);
        model.addAttribute("user", user);

        FileBucket fileModel = new FileBucket();
        model.addAttribute("fileBucket", fileModel);


        model.addAttribute("documents", documentService.findAllbyId(id));
        return "edit";
    }


    @RequestMapping(value = "edit/save", method = RequestMethod.POST)
    public String edit(@ModelAttribute("indexTable") IndexTable indexTable) {
        indexTableService.update(indexTable);

        return "redirect:/ActiveClients";
    }





    @RequestMapping(value = { "/download-document/{userId}/{docId}" }, method = RequestMethod.GET)
    public String downloadDocument(@PathVariable int userId, @PathVariable int docId,  HttpServletResponse response) throws IOException {
        DeloDocument document = documentService.find(docId);

        response.setContentType(document.getType());
        response.setContentLength(document.getContent().length);
        response.setHeader("Content-Disposition","attachment; filename=\"" + document.getName() +"\"");

        FileCopyUtils.copy(document.getContent(), response.getOutputStream());

        return "redirect:/edit/"+userId;
    }

    @RequestMapping(value = { "/delete-document/{userId}/{docId}" }, method = RequestMethod.GET)
    public String deleteDocument(@PathVariable int userId, @PathVariable int docId) {
        documentService.delete(docId);
        return "redirect:/edit/"+userId;
    }

    @RequestMapping(value = { "/edit/{userId}" }, method = RequestMethod.POST)
    public String uploadDocument(@Valid FileBucket fileBucket, BindingResult result, ModelMap model, @PathVariable int userId) throws IOException{
        if (result.hasErrors()) {
            System.out.println("validation errors");
            IndexTable user = indexTableService.find(userId);
            model.addAttribute("user", user);
            model.addAttribute("documents", documentService.listTable());

            return "managedocuments";
        } else {

            System.out.println("Fetching file");

            IndexTable user = indexTableService.find(userId);
            model.addAttribute("user", user);

            saveDocument(fileBucket, user);

            return "redirect:/edit/"+userId;
        }
    }
//
    private void saveDocument(FileBucket fileBucket, IndexTable indexTable) throws IOException {

        DeloDocument document = new DeloDocument();

        MultipartFile multipartFile = fileBucket.getFile();

        document.setName(multipartFile.getOriginalFilename());
        document.setDescription(fileBucket.getDescription());
        document.setType(multipartFile.getContentType());
        document.setContent(multipartFile.getBytes());
        document.setIndexTable(indexTable);
        documentService.create(document);
    }

}
