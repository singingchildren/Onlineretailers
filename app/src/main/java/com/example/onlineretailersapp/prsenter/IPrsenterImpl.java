package com.example.onlineretailersapp.prsenter;

import com.example.onlineretailersapp.model.Model;
import com.example.onlineretailersapp.model.ModelImpl;
import com.example.onlineretailersapp.utils.MyCllBack;
import com.example.onlineretailersapp.view.IView;

import java.util.Map;

/**
 * author:${张四佟}
 * date:2019/2/12 15:12
 * description
 */
public class IPrsenterImpl implements IPrsenter {


    private IView iView;
    private final ModelImpl model;

    public IPrsenterImpl(IView iView) {
        this.iView = iView;
        model = new ModelImpl();
    }

    @Override
    public void startRequest(String mPath, Map<String, String> map, Class clazz, int type, String userId, String sessionId) {
        model.getData(mPath, map, clazz, type, userId, sessionId, new MyCllBack() {
            @Override
            public void setData(Object data) {
                if (data!=null){
                    iView.onSueecss(data);
                }else{
                    //Nothing to do
                }
            }

            @Override
            public void setError(Object error) {
                iView.onError(error);
            }
        });
    }
}
