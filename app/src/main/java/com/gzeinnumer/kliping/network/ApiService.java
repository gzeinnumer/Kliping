package com.gzeinnumer.kliping.network;

import com.gzeinnumer.kliping.modelpojo.ResponseDaftar;
import com.gzeinnumer.kliping.modelpojo.ResponseLogin;
import com.gzeinnumer.kliping.modelpojo.ResponseNewKoran;
import com.gzeinnumer.kliping.modelpojo.ResponsePageKoran;
import com.gzeinnumer.kliping.modelpojo.ResponsePageUpload;
import com.gzeinnumer.kliping.modelpojo.ResponseReadKoran;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface ApiService {
    @FormUrlEncoded
    @POST("insertkoran.php")
    Call<ResponseNewKoran> newKoran (@Field("koran_nama") String koran_nama,
                                     @Field("koran_tanggal") String koran_tanggal,
                                     @Field("koran_jum_hal") int koran_jum_hal);

    @Multipart
    @POST("uploadkoran.php")
    Call<ResponsePageUpload> uploadKoranHalaman (@Part("koran_nama") RequestBody koran_nama,
                                          @Part("koran_tanggal") RequestBody koran_tanggal,
                                          @Part("koran_jum_hal") RequestBody koran_jum_hal,
                                          @Part("hal_page") RequestBody hal_page,
                                          @Part MultipartBody.Part image);

    @FormUrlEncoded
    @POST("readkoran.php")
    Call<ResponseReadKoran> readKoran(@Field("koran_tanggal") String koran_tanggal);

    @FormUrlEncoded
    @POST("readpagekoran.php")
    Call<ResponsePageKoran> readPage(@Field("koran_id") String koran_id);

    @FormUrlEncoded
    @POST("daftar.php")
    Call<ResponseDaftar> daftar(
            @Field("user_name") String user_name,
            @Field("user_email") String user_email,
            @Field("user_pass") String user_pass
    );

    @FormUrlEncoded
    @POST("login.php")
    Call<ResponseLogin> login(
            @Field("user_email") String user_email,
            @Field("user_pass") String user_pass
    );


}
