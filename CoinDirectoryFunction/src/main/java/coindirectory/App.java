package coindirectory;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.google.gson.Gson;
import io.openmarket.coin.dao.CoinDao;
import io.openmarket.coin.model.Coin;

/**
 * Handler for requests to Lambda function.
 */
public class App implements RequestHandler<String, String> {
    public String handleRequest(final String input, final Context context) {
        Map<String, String> headers = new HashMap<>();
        headers.put("Content-Type", "application/json");
        headers.put("X-Custom-Header", "application/json");
        Gson jsoncvt = new Gson();
        AmazonDynamoDB dbClient = AmazonDynamoDBClientBuilder.standard().build();
        DynamoDBMapper dbMapper = new DynamoDBMapper(dbClient);
        CoinDao coindao = new CoinDao(dbClient, dbMapper);
        Optional<Coin> coin = coindao.load(input);
        if (!coin.isPresent()) {
            return "";
        }
        return jsoncvt.toJson(coin.get());
    }

}
