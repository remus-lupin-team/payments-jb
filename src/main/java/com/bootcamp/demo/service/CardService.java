package com.bootcamp.demo.service;

import com.bootcamp.demo.dao.FirestoreDao;
import com.bootcamp.demo.model.Card;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class CardService {
    private final FirestoreDao firestoreDao;

    public CardService(FirestoreDao firestoreDao) {
        this.firestoreDao = firestoreDao;
    }
    public void removeCard(String id){
        this.firestoreDao.remove(id, "cards");
    }

    public List<Card> getAll() {
        return firestoreDao.getAll();
    }
}
