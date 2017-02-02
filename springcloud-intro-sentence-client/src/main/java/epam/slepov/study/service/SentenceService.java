package epam.slepov.study.service;

import epam.slepov.study.dao.AdjectiveClient;
import epam.slepov.study.dao.SubjectClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SentenceService {
    private @Autowired SubjectClient subjectClient;
    private @Autowired AdjectiveClient adjectiveClient;

    public String buildSentence() {
        return "Sentence builded: " + subjectClient.getWord() + " " + adjectiveClient.getWord();
    }
}
