package epam.slepov.study.dao;

import org.springframework.cloud.netflix.feign.FeignClient;

@FeignClient("study-springcloud-words-server-adjective")
public interface AdjectiveClient extends WordClient {
}
