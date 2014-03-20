package org.nigajuan.links;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.web.AnnotationConfigWebContextLoader;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.context.WebApplicationContext;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Created by nithril on 19/03/14.
 */

@ContextConfiguration(loader = AnnotationConfigWebContextLoader.class, classes = ConfigurationForTest.class)
@WebAppConfiguration
public class UrlTest extends AbstractTestNGSpringContextTests {


    @Autowired
    private LinksGenerator linksGenerator;



    @Autowired
    WebApplicationContext wac;

    @Autowired
    LinksGenerator controllersDirectory;

    @Test
    public void testEndpoint1() {
        String link = linksGenerator.link("sampleController" , "endpoint1" ,"id" , "id1");
        Assert.assertEquals(link , "/endpoint1/id1");
    }

    @Test
    public void testEndpoint2() {
        String link = linksGenerator.link("sampleController" , "endpoint2" , "symbolicName" , "s1" , "version" , "v1" , "extension" , "e1");
        Assert.assertEquals(link , "/endpoint2/s1-v1e1");
    }

    @Test(enabled = false)
    public void testEndpoint3() {
        String link = linksGenerator.link("sampleController" , "endpoint3" , "symbolicName" , "s1" , "version" , "v1" , "extension" , "e1" ,
                "q" , "query");
        Assert.assertEquals(link , "/endpoint2/s1-v1e1?q=query");
    }
}
