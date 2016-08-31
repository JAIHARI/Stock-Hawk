package rajan.udacity.stock.hawk.util;

import com.google.gson.Gson;

import java.io.IOException;
import java.util.Locale;

import okhttp3.ResponseBody;
import rajan.udacity.stock.hawk.data.model.financechart.FinanceChartCallBack;
import rajan.udacity.stock.hawk.data.remote.UrlBuilder;

/**
 * Created by Rajan Maurya on 25/08/16.
 */
public class Utils {

    public static String getYahooStocksQuery() {
        return UrlBuilder.queryBuilder(
                Constants.YAHOO_STOCK_SYMBOL,
                Constants.APPLE_STOCK_SYMBOL,
                Constants.GOOGLE_STOCK_SYMBOL,
                Constants.MICROSOFT_STOCK_SYMBOL);
    }

    public static String getSingleStockQuery(String query) {
        return UrlBuilder.queryBuilder(String.format(Locale.ENGLISH, "\"%1$s\"", query));
    }

    public static FinanceChartCallBack getFinanceChartCallback(ResponseBody responseBody) {
        Gson gson=new Gson();
        String result = null;
        try {
            result = responseBody.string();
            if (result.startsWith("finance_charts_json_callback( ")) {
                result = result.substring(29, result.length() - 2);
            }
        } catch (IOException | NullPointerException e) {
            e.printStackTrace();
        }
        return gson.fromJson(result, FinanceChartCallBack.class);
    }
}
