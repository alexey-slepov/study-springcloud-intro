package epam.slepov.study.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import epam.slepov.study.dao.AdjectiveClient;
import epam.slepov.study.dao.SubjectClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HystrixSentenceService {
    private @Autowired SubjectClient subjectClient;
    private @Autowired AdjectiveClient adjectiveClient;

    @HystrixCommand(fallbackMethod = "getSubjectFallback")
    public String getSubject() {
        return subjectClient.getWord();
    }

    private String getSubjectFallback() {
        return "default-subject";
    }

    @HystrixCommand(fallbackMethod = "getAdjectiveFallback")
    public String getAdjective() {
        return adjectiveClient.getWord();
    }

    private String getAdjectiveFallback() {
        return "default-adjective";
    }
}
