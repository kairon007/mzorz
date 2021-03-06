package com.zorz.mario.api;

/**
 * Created by mariozorz on 1/15/15.
 */

import android.util.Log;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.zorz.mario.Application;
import com.zorz.mario.api.definition.IApiService;
import com.zorz.mario.model.AboutAppResponse;
import com.zorz.mario.model.AboutMeResponse;
import com.zorz.mario.model.CoverLetterResponse;
import com.zorz.mario.model.ProjectsResponse;
import com.zorz.mario.model.WantToWorkOnResponse;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;
import retrofit.converter.GsonConverter;


public class Service {

    private static final Service instance = new Service();

    private static final String TAG = Service.class.getName();

    public static final String END_POINT = "http://192.241.162.17:8080/mzorzcv/api/v1";

    private static Gson gson = new GsonBuilder()
            .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
            .setDateFormat("yyyy-MM-dd'T'HH:mm:ss")
            .create();


    private static IApiService service = new RestAdapter.Builder()
            .setLogLevel(RestAdapter.LogLevel.FULL)
            .setEndpoint(END_POINT)
            .setConverter(new GsonConverter(gson))
            .setRequestInterceptor(new SessionRequestInterceptor())
            .build().create(IApiService.class);

    public void getCoverLetter() {

        Application.getEventBus().post(new Event.CoverLetterLoadStartEvent());

        service.getCoverLetter(new Callback<CoverLetterResponse>() {

            @Override
            public void success(CoverLetterResponse resp, Response response) {
                Application.getEventBus().post(new Event.CoverLetterLoadCompleteEvent(resp));
            }

            @Override
            public void failure(RetrofitError retrofitError) {
                Log.i(TAG, retrofitError.getMessage());
                if (retrofitError.getResponse() != null) {
                    try {
                        Error error = (Error) retrofitError.getBodyAs(Error.class);
                        Application.getEventBus().post(new Event.CoverLetterLoadFailEvent(error));
                    } catch (Exception ex){
                        ex.printStackTrace();
                        Application.getEventBus().post(new Event.CoverLetterLoadFailEvent(null));
                    }
                } else {
                    Application.getEventBus().post(new Event.CoverLetterLoadFailEvent(null));
                }
            }
        });
    }



    public void getAndroidProjects() {

        Application.getEventBus().post(new Event.AndroidProjectsLoadStartEvent());

        service.getAndroidProjects(new Callback<ProjectsResponse>() {

            @Override
            public void success(ProjectsResponse resp, Response response) {
                Application.getEventBus().post(new Event.AndroidProjectsLoadCompleteEvent(resp));
            }

            @Override
            public void failure(RetrofitError retrofitError) {
                Log.i(TAG, retrofitError.getMessage());
                if (retrofitError.getResponse() != null) {
                    try {
                        Error error = (Error) retrofitError.getBodyAs(Error.class);
                        Application.getEventBus().post(new Event.AndroidProjectsLoadFailEvent(error));
                    } catch (Exception ex){
                        ex.printStackTrace();
                        Application.getEventBus().post(new Event.AndroidProjectsLoadFailEvent(null));
                    }
                } else {
                    Application.getEventBus().post(new Event.AndroidProjectsLoadFailEvent(null));
                }
            }
        });
    }


    public void getOtherProjects() {

        Application.getEventBus().post(new Event.OtherProjectsLoadStartEvent());

        service.getOtherProjects(new Callback<ProjectsResponse>() {

            @Override
            public void success(ProjectsResponse resp, Response response) {
                Application.getEventBus().post(new Event.OtherProjectsLoadCompleteEvent(resp));
            }

            @Override
            public void failure(RetrofitError retrofitError) {
                Log.i(TAG, retrofitError.getMessage());
                if (retrofitError.getResponse() != null) {
                    try {
                        Error error = (Error) retrofitError.getBodyAs(Error.class);
                        Application.getEventBus().post(new Event.OtherProjectsLoadFailEvent(error));
                    } catch (Exception ex) {
                        ex.printStackTrace();
                        Application.getEventBus().post(new Event.OtherProjectsLoadFailEvent(null));
                    }
                } else {
                    Application.getEventBus().post(new Event.OtherProjectsLoadFailEvent(null));
                }
            }
        });
    }


    public void getThingsIWantToWorkOn() {

        Application.getEventBus().post(new Event.WantToWorkLoadStartEvent());

        service.getWantToWorkOn(new Callback<WantToWorkOnResponse>() {

            @Override
            public void success(WantToWorkOnResponse resp, Response response) {
                Application.getEventBus().post(new Event.WantToWorkLoadCompleteEvent(resp));
            }

            @Override
            public void failure(RetrofitError retrofitError) {
                Log.i(TAG, retrofitError.getMessage());
                if (retrofitError.getResponse() != null) {
                    try {
                        Error error = (Error) retrofitError.getBodyAs(Error.class);
                        Application.getEventBus().post(new Event.WantToWorkLoadFailEvent(error));
                    } catch (Exception ex) {
                        ex.printStackTrace();
                        Application.getEventBus().post(new Event.WantToWorkLoadFailEvent(null));
                    }
                } else {
                    Application.getEventBus().post(new Event.WantToWorkLoadFailEvent(null));
                }
            }
        });
    }

    public void getAboutMe() {

        Application.getEventBus().post(new Event.AboutMeLoadStartEvent());

        service.getAboutMe(new Callback<AboutMeResponse>() {

            @Override
            public void success(AboutMeResponse resp, Response response) {
                Application.getEventBus().post(new Event.AboutMeLoadCompleteEvent(resp));
            }

            @Override
            public void failure(RetrofitError retrofitError) {
                Log.i(TAG, retrofitError.getMessage());
                if (retrofitError.getResponse() != null) {
                    try {
                        Error error = (Error) retrofitError.getBodyAs(Error.class);
                        Application.getEventBus().post(new Event.AboutMeLoadFailEvent(error));
                    } catch (Exception ex) {
                        ex.printStackTrace();
                        Application.getEventBus().post(new Event.AboutMeLoadFailEvent(null));
                    }
                } else {
                    Application.getEventBus().post(new Event.AboutMeLoadFailEvent(null));
                }
            }
        });
    }

    public void getAboutApp() {

        Application.getEventBus().post(new Event.AboutAppLoadStartEvent());

        service.getAboutApp(new Callback<AboutAppResponse>() {

            @Override
            public void success(AboutAppResponse resp, Response response) {
                Application.getEventBus().post(new Event.AboutAppLoadCompleteEvent(resp));
            }

            @Override
            public void failure(RetrofitError retrofitError) {
                Log.i(TAG, retrofitError.getMessage());
                if (retrofitError.getResponse() != null) {
                    try {
                        Error error = (Error) retrofitError.getBodyAs(Error.class);
                        Application.getEventBus().post(new Event.AboutAppLoadFailEvent(error));
                    } catch (Exception ex) {
                        ex.printStackTrace();
                        Application.getEventBus().post(new Event.AboutAppLoadFailEvent(null));
                    }
                } else {
                    Application.getEventBus().post(new Event.AboutAppLoadFailEvent(null));
                }
            }
        });
    }

    public static Service getInstance() {
        return instance;
    }


}
