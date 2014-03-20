package org.nigajuan.links;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;
import org.springframework.web.util.UriTemplate;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by nithril on 19/03/14.
 */
@Service
public class LinksGenerator {

    private static Logger log = LoggerFactory.getLogger(LinksGenerator.class);

    @Autowired
    private RequestMappingHandlerMapping requestMappingHandlerMapping;

    private boolean throwExceptionIfExpandFails = false;

    private String fallbackIfthrowExceptionIfExpandFails = "[ERROR URL CANNOT BE EXPANDED, SEE LOGS]";



    public String link(String beanName, String methodName, Map<String, Object> params) {
        Assert.notNull(beanName);
        Assert.notNull(methodName);
        Assert.notEmpty(params);


        Map.Entry<RequestMappingInfo, HandlerMethod> method = null;

        //Find controller and bean
        for (Map.Entry<RequestMappingInfo, HandlerMethod> entry : requestMappingHandlerMapping.getHandlerMethods().entrySet()) {
            if (entry.getValue().getBean().equals(beanName) && entry.getValue().getMethod().getName().equals(methodName)) {
                method = entry;
                break;
            }
        }
        if (method == null){
            return null;
        }

        UriTemplate patternFound = null;

        //Find URL pattern which contains all provided params
        for (String patternIte : method.getKey().getPatternsCondition().getPatterns()){
            UriTemplate uriTemplate = new UriTemplate(patternIte);

            if (uriTemplate.getVariableNames().size() == params.size()){
                if (params.keySet().containsAll(uriTemplate.getVariableNames())){
                    patternFound = uriTemplate;
                    break;
                }
            }
        }
        if (patternFound == null){
            return null;
        }

        //Expand Url
        try {
            return patternFound.expand(params).toString();
        } catch (Exception e) {
            log.error(e.getMessage() , e);
            if (throwExceptionIfExpandFails){
                throw e;
            }else{
                return fallbackIfthrowExceptionIfExpandFails;
            }

        }
    }


    public String link(String controller, String method, Object...params) {
        Assert.isTrue(params.length % 2 == 0);

        Map<String,Object> paramsAsMap = new HashMap<>();
        for (int i = 0 ; i < params.length ; i+=2){
            paramsAsMap.put(params[i].toString() , params[i+1]);
        }
        return link(controller , method, paramsAsMap);
    }

}
