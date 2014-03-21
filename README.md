spring-links
============

Spring Links Generator create reusable url from controller#action. It adds to thymeleaf-spring a link generator expression object.

For example you define the following controller:
```java
    @RequestMapping("/endpoint2/{symbolicName:[a-z-]+}-{version:\\d\\.\\d\\.\\d}{extension:\\.[a-z]+}")
    public void endpoint2(@PathVariable String symbolicName , @PathVariable String version, @PathVariable String extension){
    }
```

With the following template:
```html
        <a th:with="link=${#links.link('sampleController' , 'endpoint2' , 'symbolicName' , 's1' , 'version' , 'v1' , 'extension' , 'e1')}"
           th:href="${link}" th:text="${link}"></a>
```           


Will output the following HTML:

```html
<a href="/endpoint2/s1-v1e1">/endpoint2/s1-v1e1</a>
```


It's a complement to [spring-hateoas](https://github.com/spring-projects/spring-hateoas) which generates link from the Java side. 
