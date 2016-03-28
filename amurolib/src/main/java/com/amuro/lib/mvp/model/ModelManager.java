package com.amuro.lib.mvp.model;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Amuro on 2016/3/24.
 */
public class ModelManager
{
    private static Map<Class<? extends AbsModel>, AbsModel> modelMap = new HashMap<>();

    public static <M extends AbsModel> M getModel(Class<? extends AbsModel> clazz)
    {
        AbsModel model = modelMap.get(clazz);
        if(model == null)
        {
            try
            {
                model = clazz.newInstance();
            }
            catch (InstantiationException e)
            {
                e.printStackTrace();
            }
            catch (IllegalAccessException e)
            {
                e.printStackTrace();
            }
            modelMap.put(clazz, model);
        }

        return (M)model;
    }
}
