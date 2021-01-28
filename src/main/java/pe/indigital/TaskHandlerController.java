package pe.indigital;

import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import pe.indigital.proxy.TalonOneRestClientAdapter;

import java.math.BigDecimal;
import java.util.Map;
import java.util.logging.Logger;

@RestController
public class TaskHandlerController {

  private static final Logger logger = Logger.getLogger(TaskHandlerController.class.getName());

  private static final String USER_ID = "userId";

  private static final String BENEFIT_AMOUNT = "benefitAmount";

  private final TalonOneRestClientAdapter talonOneRestClientAdapter;

  @Autowired
  public TaskHandlerController(TalonOneRestClientAdapter talonOneRestClientAdapter) {
    this.talonOneRestClientAdapter = talonOneRestClientAdapter;
  }

  @RequestMapping(
      value = " /process-batch",
      method = RequestMethod.POST,
      consumes = "application/octet-stream")
  @ResponseStatus(HttpStatus.OK)
  public String taskHandler(@RequestBody String body) {
    String output;
    output = String.format("Received task with payload %s", body);
    System.out.println(output);

    Map<String, Object> payload = new Gson().fromJson(body, Map.class);

    logger.info("Payload: " + payload);

    logger.info("Payload String: " + new Gson().toJson(payload));

    return output;
  }
}
