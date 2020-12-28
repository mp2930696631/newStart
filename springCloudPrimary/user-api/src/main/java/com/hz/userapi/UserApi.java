package com.hz.userapi;

import com.hz.userapi.entiry.Person;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Map;

/**
 * @author zehua
 * @date 2020/12/28 20:52
 */
public interface UserApi {

    @RequestMapping("/prov/port")
    String getPort();

    @RequestMapping("/prov/testRibbon")
    String testRibbon();

    @RequestMapping("/prov/testHystrix")
    String testHystrix();

    @RequestMapping("/prov/person")
    Person getPerson();

    @RequestMapping(value = "/prov/testPost", method = RequestMethod.POST)
    String testPost(@RequestBody Map<String, Person> map);

}
