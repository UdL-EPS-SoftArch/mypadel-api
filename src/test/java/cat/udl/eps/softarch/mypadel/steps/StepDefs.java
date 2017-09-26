package cat.udl.eps.softarch.mypadel.steps;

import cat.udl.eps.softarch.mypadel.*;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.datatype.jsr310.*;
import cucumber.api.java.*;
import cucumber.api.java.en.*;
import org.junit.runner.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.test.context.*;
import org.springframework.security.test.web.servlet.setup.*;
import org.springframework.test.annotation.*;
import org.springframework.test.context.*;
import org.springframework.test.context.junit4.*;
import org.springframework.test.context.web.*;
import org.springframework.test.web.servlet.*;
import org.springframework.test.web.servlet.setup.*;
import org.springframework.web.context.*;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Created by http://rhizomik.net/~roberto/
 */
@ContextConfiguration(
        classes = {MyPadelApiApplication.class}, loader = SpringBootContextLoader.class
)
@DirtiesContext
@RunWith(SpringRunner.class)
@WebAppConfiguration
@ActiveProfiles("Test")
public class StepDefs {

    @Autowired
    protected WebApplicationContext wac;
    protected MockMvc mockMvc;
    protected ResultActions result;

    protected ObjectMapper mapper = new ObjectMapper();

    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders
                .webAppContextSetup(this.wac)
                .apply(SecurityMockMvcConfigurers.springSecurity())
                .build();
        this.mapper.registerModule(new JavaTimeModule());
    }

    @Then("^The response code is (\\d+)$")
    public void theResponseCodeIs(int code) throws Throwable {
        result.andExpect(status().is(code));
    }

    @And("^The error message is \"([^\"]*)\"$")
    public void theErrorMessageIs(String message) throws Throwable {
        if (result.andReturn().getResponse().getContentAsString().isEmpty())
            result.andExpect(status().reason(is(message)));
        else
            result.andExpect(jsonPath("$..message", hasItem(containsString(message))));
    }

}
