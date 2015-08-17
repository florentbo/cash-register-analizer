package be.florentbo.register.controller;

import be.florentbo.register.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.TreeSet;

@Controller
public class OrderController {
    protected static final String ORDER_PATH = "/new";
    protected static final String REQUEST_MAPPING_ORDER_LIST = "/";

    public static final String ORDER_VIEW = "add";
    protected static final String VIEW_ORDER_LIST = "order/list";
    private OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @RequestMapping(value= ORDER_PATH, method=RequestMethod.GET)
    public String addForm() {
        return ORDER_VIEW;
    }

    @RequestMapping(value= REQUEST_MAPPING_ORDER_LIST, method=RequestMethod.GET)
    public String index(Model model) {
        model.addAttribute("days", new TreeSet<>(orderService.getDays()));
        return VIEW_ORDER_LIST;
    }

    @RequestMapping(value= ORDER_PATH, method=RequestMethod.POST)
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