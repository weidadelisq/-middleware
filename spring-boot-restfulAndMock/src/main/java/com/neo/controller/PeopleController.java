package com.neo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PeopleController {

    @GetMapping(value = "/1/people/{people_id}")
    public String getPeopleInfo(@PathVariable(value = "people_id", required = true) String peopleId) {
        return "hello world, this is people info of " + peopleId;
    }


    @GetMapping(value = "/2/people/{people_id}")
    public String getPeopleInfoV2(@PathVariable(value = "people_id", required = true) String peopleId) {
        return "hello THIS is v2 world, this is people info V2 of " + peopleId;
    }
}