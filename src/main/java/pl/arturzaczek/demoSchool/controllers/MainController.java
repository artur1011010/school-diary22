package pl.arturzaczek.demoSchool.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@Slf4j
public class MainController {

    @GetMapping(value = {"/","/home","/index"})
    public String getHomePage(){
        log.debug("url= {\"/\",\"/home\",\"/index\"}, method=getHomePage()");
        return "home";
    }

    @GetMapping("/doc")
    public String getDocumentation(){
        log.debug("url= /doc, method=getDocumentation()");
        return "doc/mian-doc";
    }

    @GetMapping("/changelog")
    public String getChangelog(){
        log.debug("url= /changelog, method=getChangelog()");
        return "changelog";
    }
}
