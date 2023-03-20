package org.example.common;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class MessageBox {
    private static ConcurrentMap<String, List> container = new ConcurrentHashMap<>();

    public void add(String key,Object message){
        if(container.containsKey(key)) {
            List box = container.get(key);
            box.add(message);
        }else{
            List box = new ArrayList();
            box.add(message);
            container.put(key,box);
        }
    }

    public List get(String key){
        return container.get(key);
    }
    public boolean hasKey(String key){
        return container.containsKey(key);
    }
    public void move(String key){
        container.remove(key);
    }
}
