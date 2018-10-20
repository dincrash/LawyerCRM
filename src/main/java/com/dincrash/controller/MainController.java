package com.dincrash.controller;


import com.dincrash.Config.FileBucket;
import com.dincrash.entities.DeloDocument;
import com.dincrash.entities.IndexTable;
import com.dincrash.service.DocumentService;
import com.dincrash.service.IndexTableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.FileCopyUtils;
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
    public String ArchiveClients(ModelMap modelMap) {

        modelMap.put("listarchives", indexTableService.listArchive());


        return "ArchiveClients";

    }
    @RequestMapping(value = {"/SuperArchive"}, method = RequestMethod.GET)
    public String SuperArchive(ModelMap modelMap) {
        indexTableService.listSuperArchive();
        modelMap.put("listsuperarchives", indexTableService.listSuperArchive());


        return "SuperArchive";

    }


    @RequestMapping(value = "add", method = RequestMethod.GET)
    public String add(ModelMap modelMap) {

        modelMap.put("indexTable", new IndexTable());

        return "add";
    }

    @RequestMapping(value = "add", method = RequestMethod.POST)
    public String add(@ModelAttribute("indexTable") IndexTable indexTable) {
        indexTable.setStatus(1);
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

        response.setContentLength(document.getContent().length);
        response.setHeader("Content-Disposition","attachment; filename=\"" + java.net.URLEncoder.encode(document.getName(),"UTF-8") +"\"");
        FileCopyUtils.copy(document.getContent(), response.getOutputStream());

        return null;
    }


    @RequestMapping(value = { "/changeid-document/{id}/{docId}" }, method = RequestMethod.GET)
    public String deleteDocument(@PathVariable int id, @PathVariable int docId, DeloDocument deloDocument,IndexTable indexTable) {
        DeloDocument dq=documentService.find(docId);
        int dp = 9999;
        indexTable.setId(dp);

        dq.setIndexTable(indexTable);
        documentService.update(dq);
        return "redirect:/edit/"+id;
    }
    @RequestMapping(value = {"/archive/{id}" }, method =RequestMethod.GET)
    public String toArchive(@PathVariable int id,@ModelAttribute("indexTable") IndexTable indexTable){
        IndexTable qd = indexTableService.find(id);
        int sq = 0;
        qd.setStatus(sq);
        indexTableService.update(qd);
        return "redirect:/ActiveClients";
    }

    @RequestMapping(value = {"/active/{id}" }, method =RequestMethod.GET)
    public String toActive(@PathVariable int id,@ModelAttribute("indexTable") IndexTable indexTable){
        IndexTable qd = indexTableService.find(id);
        int sq =1;
        qd.setStatus(sq);
        indexTableService.update(qd);
        return "redirect:/ArchiveClients";
    }

    @RequestMapping(value = {"/superarchive/{id}" }, method =RequestMethod.GET)
    public String toSuperArchive(@PathVariable int id,@ModelAttribute("indexTable") IndexTable indexTable){
        IndexTable qd = indexTableService.find(id);
        int sq = 2;
        qd.setStatus(sq);
        indexTableService.update(qd);
        return "redirect:/SuperArchive";
    }

    @RequestMapping(value = { "/edit/{userId}" }, method = RequestMethod.POST)
    public String edit(@Valid FileBucket fileBucket, ModelMap model, @PathVariable int userId) throws IOException{


            IndexTable user = indexTableService.find(userId);
            model.addAttribute("user", user);
            saveDocument(fileBucket, user);
            return "redirect:/edit/"+userId;

    }
//
    private void saveDocument(FileBucket fileBucket, IndexTable indexTable) throws IOException {

        DeloDocument document = new DeloDocument();

        MultipartFile[] multipartFile = fileBucket.getFile();
        for(MultipartFile multipartFile1:multipartFile) {
            document.setName(multipartFile1.getOriginalFilename());
            document.setContent(multipartFile1.getBytes());
            document.setIndexTable(indexTable);
            documentService.create(document);
        }
    }

}
