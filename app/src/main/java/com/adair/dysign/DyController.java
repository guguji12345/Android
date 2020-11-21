package com.adair.dysign;

import android.util.Log;

import com.yanzhenjie.andserver.annotation.PostMapping;
import com.yanzhenjie.andserver.annotation.ResponseBody;
import com.yanzhenjie.andserver.annotation.RestController;
import com.yanzhenjie.andserver.framework.body.JsonBody;
import com.yanzhenjie.andserver.http.HttpRequest;
import com.yanzhenjie.andserver.http.HttpResponse;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

@RestController
class DyController {
    @ResponseBody
    @PostMapping("/dy_sign")
    public void post_sign(HttpRequest request, HttpResponse response) {
        String url = request.getParameter("url");
        int currentTimeMillis = (int) (System.currentTimeMillis() / 1000);
        Map<String, String> sign_map = new HashMap<>();
        String sign = MainActivity.Gorgon(currentTimeMillis, url);
        sign_map.put("RequestUrl", url);
        sign_map.put("X-Khronos", String.valueOf(currentTimeMillis));
        sign_map.put("X-Gorgon", sign);
        JSONObject jsonObject = new JSONObject(sign_map);
        Log.i("adair", String.valueOf(jsonObject));
        response.setBody(new JsonBody(jsonObject));
    }
}
