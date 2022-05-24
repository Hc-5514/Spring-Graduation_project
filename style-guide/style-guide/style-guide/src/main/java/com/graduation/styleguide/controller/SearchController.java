package com.graduation.styleguide.controller;

import com.graduation.styleguide.service.SearchService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RequiredArgsConstructor
@Controller
public class SearchController {
    private final SearchService searchService;

    @PostMapping(value = "/search")
    public String searchProduct(@RequestParam("search") String product_name, Model model) throws Exception {
        if(product_name == ""){
            return "redirect:/";
        }
//        model.addAttribute("find", searchService.findByProductContains(product_name));
//        model.addAttribute("findcount", searchService.countByProductContains(product_name));
        model.addAttribute("search",product_name);
        return "/layout/Search";
    }

//    @PostMapping(value = "search")
//    public List<UploadInfo> searchProduct(@RequestParam("search") UploadInfo uploadInfo, Pageable list) {
//        return searchService.getPost(uploadInfo.getproduct_name(), list);
//    }

//    @GetMapping(value = "/search")
//    public String searchPage() {
//        return "/layout/Search";
//    }
}
