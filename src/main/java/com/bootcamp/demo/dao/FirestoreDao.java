package com.bootcamp.demo.dao;

import java.util.Map;

public interface FirestoreDao {
    void remove(String id, String collectionName);
    void update(String id, String collectionName, Map updateDetails);
}
