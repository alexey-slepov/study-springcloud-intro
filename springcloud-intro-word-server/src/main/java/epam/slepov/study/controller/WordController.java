package epam.slepov.study.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/word")
public class WordController {
    //private @Value("${words}") String words;
    private @Value("${eureka.instance.instanceId}") String instanceId;
    private String words = "A,T,C,G";

    @RequestMapping("")
    public @ResponseBody String getWord() {
        System.out.println("Words requsted: " + (words + "," + instanceId));
        String[] wordArray = (words + "," + instanceId).split(",");
        int i = (int)Math.round(Math.random() * (wordArray.length - 1));
        return wordArray[i];
    }
}
