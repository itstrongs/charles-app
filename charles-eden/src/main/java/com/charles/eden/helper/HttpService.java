package com.charles.eden.helper;

import com.alibaba.fastjson.JSONObject;
import com.charles.eden.model.bean.NotePlanBo;
import com.charles.eden.model.bean.NoteTypeBo;
import com.charles.eden.model.bean.PersonFriendsBo;
import com.charles.eden.model.bean.TodoPlanBo;
import com.charles.eden.model.bean.UserBo;
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

    /**
     * 登录
     */
    @POST("/user/login")
    Observable<JSONObject> userLogin(@Body UserBo loginBo);

    /**
     * 获取用户信息
     */
    @GET("/user")
    Observable<JSONObject> userInfo();

    /**
     * 新增模块分类
     */
    @POST("/category")
    Observable<JSONObject> addModuleType(@Body NoteTypeBo noteTypeBo);

    /**
     * 模块分类列表
     */
    @GET("/category/list")
    Observable<JSONObject> listModuleType(@Query("moduleType") Integer moduleType);

    /**
     * 模块分类列表
     */
    @GET("/category/list")
    Observable<JSONObject> listModuleType(@Query("moduleType") Integer moduleType, @Query("queryRange") Integer queryRange);

    /**
     * 删除模块分类
     */
    @DELETE("/category/{id}")
    Observable<JSONObject> deleteModuleType(@Path("id") Long id);

    /**
     * 更新todo是否完成
     */
    @PUT("/todo-plan/{id}/is-finish")
    Observable<HttpResult> todoFinish(@Path("id") Long id, @Query("isFinish") Boolean isFinish);

    /** ----------------------------------------- 笔记 ----------------------------------------- **/
    /**
     * 新增笔记
     */
    @POST("/note")
    Observable<HttpResult> addRecord(@Body NotePlanBo recordPlanBo);

    /**
     * 笔记列表
     */
    @GET("/note/list")
    Observable<JSONObject> listNote(@Query("categoryId") Long categoryId);

    /**
     * 微话列表
     */
    @GET("/micro-sentence")
    Observable<JSONObject> microSentence(@Query("range") Integer range);

    /**
     * 微话点赞
     */
    @PUT("/micro-sentence/{id}/favour")
    Observable<JSONObject> microSentenceFavour(@Path("id") Long id);

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

    @GET("/photo-story/list")
    Observable<HttpResult> photoStoryList(@Query("type") Integer type);
}
