package be.florentbo.register.controller;

import be.florentbo.register.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.time.LocalDate;
import java.util.Map;
import java.util.TreeSet;

@Controller
@RequestMapping(value = OrderController.REQUEST_MAPPING_ORDER_LIST)
public class OrderController {

    protected static final String ORDER_PATH = "/new";
    protected static final String ORDER_VIEW = "order/add";

    protected static final String REQUEST_MAPPING_ORDER_LIST = "/orders";
    protected static final String VIEW_ORDER_LIST = "order/list";

    protected static final String REQUEST_MAPPING_DATES_SELECTION = "/search";
    protected static final String VIEW_SEARCH_BY_DATES = "order/search";

    protected static final String VIEW_ORDER_DAY = "order/day";
    private static final String ORDER_DATE_PARAMETER = "orderDate";


    private OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @RequestMapping(value = ORDER_PATH, method = RequestMethod.GET)
    public String addForm() {
        return ORDER_VIEW;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String index(Model model) {
        model.addAttribute("days", new TreeSet<>(orderService.getDays()));
        return VIEW_ORDER_LIST;
    }

    @RequestMapping(method = RequestMethod.GET, params = "date")
    public String search(
            Model model,
            @RequestParam(value = "date", required = true) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        model.addAttribute("day", orderService.getDay(date));
        return VIEW_ORDER_DAY;
    }

    @RequestMapping(value = REQUEST_MAPPING_DATES_SELECTION, method = RequestMethod.GET)
    public String selectForm(Model model) {
        model.addAttribute("dates", new Dates(LocalDate.now(), LocalDate.now()));
        return VIEW_SEARCH_BY_DATES;
    }

    @RequestMapping(value = REQUEST_MAPPING_DATES_SELECTION, method = RequestMethod.GET, params = {"startDate","endDate"})
    public String search(@ModelAttribute Dates dates, Model model) throws UnsupportedEncodingException {
        model.addAttribute("report", orderService.find(dates.getStartDate(), dates.getEndDate()));
        String queryParam = QueryBuilder.newBuilder()
                                        .where(ORDER_DATE_PARAMETER)
                                        .orderedBetween(dates.getStartDate(), dates.getEndDate())
                                        .toEncodedAndEscapedParam();
        model.addAttribute("reportQueryParam", queryParam);

        return VIEW_SEARCH_BY_DATES;
    }

    @RequestMapping(value = "/report", method=RequestMethod.GET, params = "query")
    public ModelAndView print(@RequestParam(value = "query", required = true) String queryParam) throws UnsupportedEncodingException {
        QueryBuilder.Dates dates = QueryBuilder.newBuilder().ofEncodedAndEscapedParam(queryParam);
        Map<String, Integer> report = orderService.print(dates.getStartDate(), dates.getEndDate());
        return new ModelAndView(new ExcelBuilder(report));
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public void upload(@RequestParam("file") MultipartFile file ) throws IOException {

        byte[] bytes;

        if (!file.isEmpty()) {
            bytes = file.getBytes();
            orderService.add(file.getInputStream());
            //String content = new String(Files.readAllBytes(Paths.get("duke.java")));
            String content = new String(bytes);
            System.out.println(content);
            //store file in storage
        }

        System.out.println(String.format("receive %s", file.getOriginalFilename()));
    }

    /*@RequestMapping(value = ORDER_PATH, method = RequestMethod.POST)
    @ResponseBody
    public String handleFileUpload(@RequestParam("file") MultipartFile file) {
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
    }*/

}
