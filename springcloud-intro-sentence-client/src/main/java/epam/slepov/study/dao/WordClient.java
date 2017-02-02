package epam.slepov.study.dao;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

public interface WordClient {
    @RequestMapping(value = "/word", method = RequestMethod.GET)
    String getWord();
}
