package epam.slepov.study.controller;

import epam.slepov.study.service.SentenceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sentence-feign")
public class FeignSentenceController {
    private static final Logger log = LoggerFactory.getLogger(FeignSentenceController.class);
    private @Autowired SentenceService sentenceService;

    @RequestMapping("")
    public @ResponseBody String sentence() {
        try {
            return sentenceService.buildSentence();
        } catch (Exception e) {
            log.error("Failed to build sentence", e);
            return "500. Ask admin to feignilize";
        }
    }
}
