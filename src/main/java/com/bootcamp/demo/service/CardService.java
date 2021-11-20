package com.bootcamp.demo.service;

import com.bootcamp.demo.dao.FirestoreDao;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class CardService {
    private final FirestoreDao firestoreDao;

    public CardService(FirestoreDao firestoreDao) {
        this.firestoreDao = firestoreDao;
    }
    public void removeCard(String id){
        this.firestoreDao.remove(id, "cards");
    }
    public void updateCard(String id, Map updateDetails){
        this.firestoreDao.update(id,"cards", updateDetails);
    }
}
