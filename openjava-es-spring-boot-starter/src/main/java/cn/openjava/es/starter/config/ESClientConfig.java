package cn.openjava.es.starter.config;

import cn.openjava.es.starter.properties.ESClientProperties;
import org.apache.http.HttpHost;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.context.annotation.Bean;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

public class ESClientConfig {

    @Resource
    ESClientProperties esClientProperties;

    /*
      HighLevelRestConfig
     */
    @Bean(destroyMethod = "close")
    public RestHighLevelClient restHighLevelClient() {
        CredentialsProvider credentialsProvider = new BasicCredentialsProvider();
        credentialsProvider.setCredentials(AuthScope.ANY, new UsernamePasswordCredentials(esClientProperties.getUsername(), esClientProperties.getPassword()));
        String[] ipArray = esClientProperties.getHostName().split(",");
        HttpHost[] httpHostArray = new HttpHost[ipArray.length];
        for (int i = 0; i < ipArray.length; i++) {
            String[] address = ipArray[i].split(":");
            httpHostArray[i] = new HttpHost(address[0], Integer.valueOf(address[1]));
        }
        RestClientBuilder builder = RestClient.builder(httpHostArray)
                .setRequestConfigCallback(requestConfigBuilder -> requestConfigBuilder
                        .setConnectTimeout(esClientProperties.getConnTimeout())
                        .setSocketTimeout(esClientProperties.getSocketTimeout())
                        .setConnectionRequestTimeout(esClientProperties.getConnectionRequestTimeout())
                ).setHttpClientConfigCallback(httpAsyncClientBuilder -> httpAsyncClientBuilder
                        .setDefaultCredentialsProvider(credentialsProvider)//带密码的ES连接访问
                        .setKeepAliveStrategy(((response, context) -> TimeUnit.MINUTES.toMillis(esClientProperties.getKeepAliveStrategy()))));//手动设置setKeepAliveStrategy方法，设置时间3分钟
        //esClient自动设置的KeepAlive的时间是-1，也就是持续连接，然而这会受到外界的影响，比如FirWall，会将TCP连接单方面断开，从而导致Connection reset by peer错误
        return new RestHighLevelClient(builder);
    }
}
