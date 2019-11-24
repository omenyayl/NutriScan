package com.nutriscan.shared.misc.volley;

import androidx.annotation.Nullable;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.nio.charset.StandardCharsets;

public class PostStringRequest extends StringRequest {
    private String body;
    public PostStringRequest(String url, Response.Listener<String> listener, @Nullable Response.ErrorListener errorListener, String body) {
        super(Method.POST, url, listener, errorListener);
        this.body = body;
    }

    @Override
    public String getBodyContentType() {
        return "application/json; charset=utf-8";
    }

    @Override
    public byte[] getBody() throws AuthFailureError {
        return this.body.getBytes(StandardCharsets.UTF_8);
    }
}
