package pe.indigital;

import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import pe.indigital.proxy.DummyRestClientAdapter;

import java.util.Map;
import java.util.logging.Logger;

@RestController
public class TaskHandlerController {

  private static final Logger logger = Logger.getLogger(TaskHandlerController.class.getName());

  private final DummyRestClientAdapter dummyRestClientAdapter;

  @Value("${demo.secret.variable}")
  private String secretVairableTest;

  @Autowired
  public TaskHandlerController(DummyRestClientAdapter dummyRestClientAdapter) {
    this.dummyRestClientAdapter = dummyRestClientAdapter;
  }

  @RequestMapping(
          value = "/demo/test",
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

    dummyRestClientAdapter.callDummy();

    logger.info("Valor de variable secreta MY_SECRET_VAR: " + secretVairableTest);

    return output;
  }
}
