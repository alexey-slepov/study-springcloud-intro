package epam.slepov.study.dao;

import org.springframework.cloud.netflix.feign.FeignClient;

@FeignClient("study-springcloud-words-server-subject")
public interface SubjectClient extends WordClient {
}
