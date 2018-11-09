package com.example.samuel.rfidreader.api;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ApiInterface {
  @POST("n35FwxAOVfGI3StMv5Av3xirAhi2IZXgTMQcA33PKja")
  Call<RFIDJson> enviarCode(@Body RFIDJson json);


}