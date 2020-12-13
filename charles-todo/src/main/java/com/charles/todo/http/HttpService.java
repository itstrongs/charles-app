package com.charles.todo.http;

import com.alibaba.fastjson.JSONObject;
import com.charles.todo.domain.Todo;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * description
 *
 * @author liufengqiang <fq1781@163.com>
 * @date 2020-06-15 22:44
 */
public interface HttpService {

    /**
     * 新增todo
     */
    @POST("/todo")
    Observable<JSONObject> todo(@Body Todo todo);
}
