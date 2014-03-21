package org.nigajuan.links;


import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class SampleController {


    @RequestMapping(value = "/endpoint1/{id}" , method = RequestMethod.GET)
    @ResponseBody
    public void endpoint1(@PathVariable String id) {
    }

    @RequestMapping("/endpoint2/{symbolicName:[a-z-]+}-{version:\\d\\.\\d\\.\\d}{extension:\\.[a-z]+}")
    public void endpoint2(@PathVariable String symbolicName , @PathVariable String version, @PathVariable String extension){
    }

    @RequestMapping("/endpoint3/{symbolicName:[a-z-]+}-{version:\\d\\.\\d\\.\\d}{extension:\\.[a-z]+}")
    public HttpEntity endpoint3(@PathVariable String symbolicName , @PathVariable String version, @PathVariable String extension,
                          @RequestParam(value = "q", required = true) String query){
        return null;
    }


    @RequestMapping("/index")
    public String doIt() {
        return "index";
    }
}
