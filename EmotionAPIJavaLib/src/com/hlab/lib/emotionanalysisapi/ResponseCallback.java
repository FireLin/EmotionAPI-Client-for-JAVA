package com.hlab.lib.emotionanalysisapi;

import com.hlab.lib.emotionanalysisapi.models.FaceAnalysis;

/**
 * Created by David Pacioianu on 1/12/16.
 */
public interface ResponseCallback {

    void onError(String errorMessage);

    void onSuccess(FaceAnalysis[] response);

}
