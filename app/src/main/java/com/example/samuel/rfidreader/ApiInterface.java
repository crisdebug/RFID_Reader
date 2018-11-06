package com.example.samuel.rfidreader;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ApiInterface {
  @POST("dS_dgY-n4Ym-2oLapmcczSNSilsgBCl7YXDoOnYkSO1")
  Call<RFIDJson> enviarCode(@Body RFIDJson json);


}