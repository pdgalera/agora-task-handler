package pe.indigital.config;

import okhttp3.*;
import okio.Buffer;

import java.io.IOException;
import java.util.logging.Logger;

public class LoggingInterceptor implements Interceptor {

    private static final Logger logger = Logger.getLogger(LoggingInterceptor.class.getName());

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();

        long t1 = System.nanoTime();
        logger.info(String.format("Api: REST request %s, method: %s", request.url(),
                request.method()));
        logger.info(String.format("Api: REST request %s, body: %s", request.url(),
                getBodyAsString(request.body())));
        Response response = chain.proceed(request);

        long t2 = System.nanoTime();

        String responseString = new String(response.body().bytes());
        logger.info(String.format("Api: JSON response %s", responseString));

        logger.info(String.format("Api: REST response %s in %.1fms, status: %d",
                response.request().url(), (t2 - t1) / 1e6d, response.code()));

        return response.newBuilder()
                .body(ResponseBody.create(response.body().contentType(), responseString)).build();
    }
    private String getBodyAsString(RequestBody body) {
        if (body != null) {
            Buffer buffer = new Buffer();
            try {
                body.writeTo(buffer);
            } catch (IOException e) {
                logger.info("Error reading request body: " + e);
            } finally {
                buffer.close();
            }
            return buffer.readUtf8();
        }
        return "empty";
    }

}
