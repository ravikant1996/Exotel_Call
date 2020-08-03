package com.example.calltrack;


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

class ExotelCall {
    //	public static String customerNumber = "your-customer-number";
    public String customerNumber;
    public static String url = "http://my.exotel.com/";
    public static String accountSid = "individual141";
    public static String flow_id = "310293";
    public static String authId = "3def352e64b2081ffe5bdb3da98feff3423cdcc605301e0e";
    public static String authToken = "db848c6dfe535ad88cee6e10e565e936fa56e0519a48a7c5";


    public ExotelResponse connectCustomerToFlow() {

        OkHttpClient client = new OkHttpClient().newBuilder().build();
        RequestBody body = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("From", customerNumber)
                .addFormDataPart("Url", url + accountSid + "/exoml/start_voice/" + flow_id)
                .build();

        String credentials = Credentials.basic(authId, authToken);


        Request request = new Request.Builder()
                .url(String.format(ExotelStrings.CONNECT_CUSTOMER_TO_FLOW_URL, accountSid))
                .post(body)
                .addHeader("Authorization", credentials)
                .addHeader("Content-Type", "application/json")
                .build();


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

                    ExotelResponse SuccessResponse = connect.fromJson(res, ExotelResponse.class);

                    int status = response.code();

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

        ExotelFailureResponse cust = new ExotelFailureResponse(0);
        return cust;
    }
}