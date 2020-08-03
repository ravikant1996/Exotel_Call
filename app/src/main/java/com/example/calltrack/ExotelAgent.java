package com.example.calltrack;

import android.util.Log;

import com.google.gson.Gson;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Credentials;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

class ExotelAgent {
    //	public static String customerNumber = "your-customer-number";
    public String customerNumber= "your-customer-number";
    public static String url = "http://my.exotel.com/";
    public static String exotelSid = "individual141";
    public static String apiid = "3def352e64b2081ffe5bdb3da98feff3423cdcc605301e0e";
    public static String apitoken = "db848c6dfe535ad88cee6e10e565e936fa56e0519a48a7c5";
    public String agentNumber;

    public ExotelResponse connectToAgent() {
		Log.e("Test3", "pass");

		OkHttpClient client = new OkHttpClient().newBuilder().build();
        RequestBody body = new MultipartBody.Builder().setType(MultipartBody.FORM)
                .addFormDataPart("From", customerNumber)
                .addFormDataPart("To", agentNumber)
				.build();

        String credentials = Credentials.basic(apiid, apitoken);

        Request request = new Request.Builder()
                .url(String.format(ExotelStrings.CONNECT_TO_AGENT_URL, exotelSid))
				.post(body)
                .addHeader("Authorization", credentials)
				.addHeader("Content-Type", "application/json")
				.build();

		Log.e("Test4", ""+request);

        try {
//            Response response = client.newCall(request).execute();
			Call call = client.newCall(request);

			call.enqueue(new Callback() {
				@Override
				public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
					Gson connect = new Gson();
					String res = null;
					try {
						res = response.body().string();
					} catch (IOException e) {
						e.printStackTrace();
					}
					Log.e("Test5", res);


					ExotelResponse SuccessResponse = connect.fromJson(res, ExotelResponse.class);

					int status = response.code();
					Log.e("Test6", ""+response);

					if (status == 200) {
						new ExotelSuccessResponse(0);
					} else {
						new ExotelFailureResponse(0);
					}
				}

				@Override
				public void onFailure(@NotNull Call call, @NotNull IOException e) {

					e.printStackTrace();
				}
			});

        } catch (Exception e) {
            e.printStackTrace();
        }
		Log.e("Test7", "pass");

        ExotelFailureResponse cust = new ExotelFailureResponse(0);
        return cust;
    }
}