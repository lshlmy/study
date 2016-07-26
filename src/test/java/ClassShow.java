import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.SimpleType;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.config.annotation.method.configuration.GlobalMethodSecurityConfiguration;
import org.springframework.security.oauth2.config.annotation.configuration.ClientDetailsServiceConfiguration;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationManager;
import org.springframework.security.oauth2.provider.client.ClientDetailsUserDetailsService;
import org.springframework.security.oauth2.provider.client.JdbcClientDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.generator.MapperCommentGenerator;

/**
 * Created by lshlm on 2016/6/19.
 */
@Component("in")
public class ClassShow {
    public static void main(String[] args) {

        //MapperCommentGenerator
       // java.util.UUID.randomUUID()
        //ClientDetailsUserDetailsService
        /*DefaultListableBeanFactory*/
        //XmlBeanFactory
        //ClassPathResource
        //DefaultListableBeanFactory
        //OAuth2AuthenticationManager
        /*ClientDetailsUserDetailsService;
        JdbcClientDetailsService
        org.springframework.security.oauth2.provider.ClientDetailsService*/


       /* TestPOJO testPOJO = new TestPOJO();
        testPOJO.setId(111);
        testPOJO.setName("myName");
        testPOJO.setCount(22);

        ObjectMapper objectMapper = new ObjectMapper();
        try {
            String jsonStr = objectMapper.writeValueAsString(testPOJO);
            System.out.println(jsonStr);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }*/
    }

    public static class TestPOJO{
        private int id;
        @JsonIgnore
        private String name;
        private int count;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getCount() {
            return count;
        }
        @JsonIgnore
        public void setCount(int count) {
            this.count = count;
        }
    }
}
