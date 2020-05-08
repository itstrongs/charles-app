package com.charles.eden.helper;

import com.alibaba.fastjson.JSONObject;
import com.charles.eden.model.bo.NotePlanBo;
import com.charles.eden.model.bo.NoteTypeBo;
import com.charles.eden.model.bo.PersonFriendsBo;
import com.charles.eden.model.bo.TodoPlanBo;
import com.charles.eden.model.bo.UserBo;
import com.charles.utils.http.HttpResult;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by 刘奉强 on 2018/11/29 11:48
 * <p>
 * Describe：
 */
public interface HttpService {

    @GET("/note-plan/type")
    Observable<HttpResult> recordPlanType();

    @GET("/note-plan/{id}")
    Observable<HttpResult> listByTypeId(@Path("id") Long id);

    @POST("/note-type")
    Observable<JSONObject> addNoteType(@Body NoteTypeBo noteTypeBo);

    @DELETE("/note-type")
    Observable<HttpResult> noteType(@Body NoteTypeBo noteTypeBo);

    @POST("/note-plan")
    Observable<HttpResult> addRecord(@Body NotePlanBo recordPlanBo);

    @POST("/user/login")
    Observable<JSONObject> userLogin(@Body UserBo loginBo);

    @GET("/note/list")
    Observable<HttpResult> noteList();

    @GET("/note-type/list")
    Observable<HttpResult> noteType(@Query("type") Integer type);

    @GET("/micro-sentence")
    Observable<HttpResult> microSentence();

    @GET("/person-friends/list")
    Observable<HttpResult> friendsList();

    @POST("/person-friends")
    Observable<HttpResult> personFriends(@Body PersonFriendsBo personFriendsBo);

    @DELETE("/person-friends/{id}")
    Observable<HttpResult> personFriends(@Path("id") Long id);

    @GET("/todo-plan/list")
    Observable<HttpResult> todoList(@Query("typeId") Long typeId);

    @POST("/todo-plan")
    Observable<JSONObject> todoPlan(@Body TodoPlanBo todoPlanBo);

    /**
     * 更新todo是否完成
     */
    @PUT("/todo-plan/{id}/is-finish")
    Observable<HttpResult> todoFinish(@Path("id") Long id, @Query("isFinish") Boolean isFinish);

    @GET("/photo-story/list")
    Observable<HttpResult> photoStoryList(@Query("type") Integer type);
}
