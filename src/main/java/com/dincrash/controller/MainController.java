package com.dincrash.controller;


import com.dincrash.entities.IndexTable;
import com.dincrash.form.MyUploadForm;
import com.dincrash.service.IndexTableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

@Controller
public class MainController {

    @Autowired
    private IndexTableService indexTableService;

    @RequestMapping(value = { "/" }, method = RequestMethod.GET)
    public String Index(){

        return "index";

    }

    @RequestMapping(value = { "/ActiveClients" }, method = RequestMethod.GET)
    public String ActiveClients(ModelMap modelMap){

        modelMap.put("indexTables", indexTableService.listTable());

        return "ActiveClients";

    }



    @RequestMapping(value = { "/ArchiveClients" }, method = RequestMethod.GET)
    public String ArchiveClients(){

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
    public String edit(@PathVariable("id") int id, ModelMap modelMap,Model model) {
        modelMap.put("indexTable", indexTableService.find(id));

        MyUploadForm myUploadForm = new MyUploadForm();
        model.addAttribute("myUploadForm", myUploadForm);

        return "edit";
    }


    @RequestMapping(value = "edit", method = RequestMethod.POST)
    public String edit(@ModelAttribute("indexTable") IndexTable indexTable) {
        indexTableService.update(indexTable);

        return "redirect:/ActiveClients";
    }



    private String doUpload(HttpServletRequest request, Model model, //
                            MyUploadForm myUploadForm) {

        String description = myUploadForm.getDescription();
        System.out.println("Description: " + description);

        // Root Directory.
        String uploadRootPath = request.getServletContext().getRealPath("upload");
        System.out.println("uploadRootPath=" + uploadRootPath);

        File uploadRootDir = new File(uploadRootPath);
        // Create directory if it not exists.
        if (!uploadRootDir.exists()) {
            uploadRootDir.mkdirs();
        }
        MultipartFile[] fileDatas = myUploadForm.getFileDatas();
        //
        List<File> uploadedFiles = new ArrayList<File>();
        List<String> failedFiles = new ArrayList<String>();

        for (MultipartFile fileData : fileDatas) {

            // Client File Name
            String name = fileData.getOriginalFilename();
            System.out.println("Client File Name = " + name);

            if (name != null && name.length() > 0) {
                try {
                    // Create the file at server
                    File serverFile = new File(uploadRootDir.getAbsolutePath() + File.separator + name);

                    BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
                    stream.write(fileData.getBytes());
                    stream.close();
                    //
                    uploadedFiles.add(serverFile);
                    System.out.println("Write file: " + serverFile);
                } catch (Exception e) {
                    System.out.println("Error Write file: " + name);
                    failedFiles.add(name);
                }
            }
        }
        model.addAttribute("description", description);
        model.addAttribute("uploadedFiles", uploadedFiles);
        model.addAttribute("failedFiles", failedFiles);
        return "uploadResult";
    }


}
