package org.launchcode.controllers;

import org.launchcode.models.JobData;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by LaunchCode
 */
@Controller
@RequestMapping("search")
public class SearchController {

    @RequestMapping(value = "")
    public String search(Model model) {
        model.addAttribute("columns", ListController.columnChoices);
        return "search";
    }

    // TODO #1 - Create handler to process search request and display results
    @RequestMapping(value = "results")
    public String search(Model model, @RequestParam String searchType, @RequestParam String searchTerm) {
        model.addAttribute("columns", ListController.columnChoices);
        ArrayList<HashMap<String, String>> temp;
        if (searchType.equals("all")){
            temp = JobData.findByValue(searchTerm);
        }
        else {
            temp = JobData.findByColumnAndValue(searchType, searchTerm);
        }
        Integer size = temp.size();
        model.addAttribute( "jobs", temp);
        model.addAttribute("jobssize", size);
        return "search";
    }
}
