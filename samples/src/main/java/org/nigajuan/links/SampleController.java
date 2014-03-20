package org.nigajuan.links;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;



@Controller
public class SampleController {


    @RequestMapping(value = "/endpoint1/{id}" , method = RequestMethod.GET)
    @ResponseBody
    public void endpoint1(@PathVariable String id) {
    }

    @RequestMapping("/endpoint2/{symbolicName:[a-z-]+}-{version:\\d\\.\\d\\.\\d}{extension:\\.[a-z]+}")
    public void endpoint2(@PathVariable String symbolicName , @PathVariable String version, @PathVariable String extension){
    }

    @RequestMapping("/index")
    public String doIt() {
        return "index";
    }
}
