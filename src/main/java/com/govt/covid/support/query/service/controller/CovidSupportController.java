package com.govt.covid.support.query.service.controller;

import com.govt.covid.support.query.service.service.CovidSupportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@RestController
public class CovidSupportController {

    @Autowired
    private CovidSupportService covidSupportService;

    @GetMapping("ap1/v1/get-date-info")
    public ResponseEntity<String> getDataBasedOnDate(@RequestParam(required = true) String date) {
      String res = covidSupportService.getDetailsBasedOnDate(date);
      return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @GetMapping("ap1/v1/get-state-info")
    public ResponseEntity<String> getDataBasedOnState(@RequestParam(required = true) String state) {
        String res = covidSupportService.getDetailsBasedOnState(state);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @GetMapping("ap1/v1/pinpoint-state")
    public ResponseEntity<String> getDataBasedOnStateAndDate(@RequestParam(required = true) String state,
                                                             @RequestParam(required = true) String date) {
        String res = covidSupportService.getDetailsBasedOnDateAndState(state, date);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }
    private Date dateConverter(String date) {
        try {
            return new SimpleDateFormat("yyyy-MM-dd").parse(date);
        } catch (ParseException e) {
            throw new IllegalArgumentException("Invalid date format");
        }
    }

}
