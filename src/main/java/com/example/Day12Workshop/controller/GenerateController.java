package com.example.Day12Workshop.controller;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import com.example.Day12Workshop.model.Generate;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Controller
public class GenerateController {
        private Logger logger = LoggerFactory.getLogger(GenerateController.class);
       
        @GetMapping( "/")
        public String showGenerateForm(Model model){
            Generate generate = new Generate();
            model.addAttribute("generate", generate);
            return "generate";
        }

        @PostMapping("/generate")
        public String generateNumbers(@ModelAttribute Generate generate,
                 Model model){
                     logger.info("From the form " + generate.getNumberVal());
                     int numberRandomNumbers = generate.getNumberVal();
                     if(numberRandomNumbers > 10){
                            //throw ne RandomNumberException();
                            model.addAttribute("error", "Exceeded 10 already!");
                            return "error";
                     }
                     String[] imgNumbers = {
                         "number1.jpg","number2.jpg","number3.jpg","number4.jpg","number5.jpg",
                         "number6.jpg","number7.jpg", "number8.jpg","number9.jpg","number10.jpg"
                     };
                     List<String> selectedImg = new ArrayList<String>();
                     Random randNum = new Random();
                     Set<Integer> uniqueGeneratedRandNumSet = new LinkedHashSet<Integer>();
                     while(uniqueGeneratedRandNumSet.size() < numberRandomNumbers){
                            Integer resultOfRandNumbers = 
                                    randNum.nextInt(10);
                            uniqueGeneratedRandNumSet.add(resultOfRandNumbers);
                     }
                     Iterator<Integer> it = uniqueGeneratedRandNumSet.iterator();
                     Integer currentElem = null;
                     while(it.hasNext()){
                         currentElem = it.next();
                         logger.info("currentElem > "+ currentElem);
                         selectedImg.add(imgNumbers[currentElem.intValue()]);

                     }
                     model.addAttribute("randNumsResult", selectedImg.toArray());
            return  "result";
        }
}
