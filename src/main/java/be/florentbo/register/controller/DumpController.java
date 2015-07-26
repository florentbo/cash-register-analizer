package be.florentbo.register.controller;

import be.florentbo.register.service.DumpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;

@Controller
public class DumpController {
    static final String DUMP_PATH = "/dump";
    public static final String DUMP_VIEW = "dump";
    private DumpService dumpService;

    @Autowired
    public DumpController(DumpService dumpService) {
        this.dumpService = dumpService;
    }

    @RequestMapping(value= DUMP_PATH, method=RequestMethod.GET)
    public String dumpForm() {
        System.out.println("++++++++++++++++++++++++++++");
        System.out.println(dumpService.test());
        return DUMP_VIEW;
    }

    @RequestMapping(value= DUMP_PATH, method=RequestMethod.POST)
    @ResponseBody
    public String handleFileUpload(@RequestParam("file") MultipartFile file){
        String name = file.getOriginalFilename();
        if (!file.isEmpty()) {
            try {
                byte[] bytes = file.getBytes();
                BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(new File(name)));
                stream.write(bytes);
                stream.close();
                return "You successfully uploaded " + name + "!";
            } catch (Exception e) {
                return "You failed to upload " + name + " => " + e.getMessage();
            }
        } else {
            return "You failed to upload " + name + " because the file was empty.";
        }
    }

}
