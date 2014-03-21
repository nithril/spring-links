spring-links
============

Spring Links Generator create reusable url from controller#action. It adds to thymeleaf-spring a link generator expression object.

For example take the following controller:
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


It will output the following HTML:

```html
<a href="/endpoint2/s1-v1e1">/endpoint2/s1-v1e1</a>
```


It's a complement to [spring-hateoas](https://github.com/spring-projects/spring-hateoas) which generates links on the Java side. Anyway spring-hateoas is  the right way as link generation are checked at compilation time.
