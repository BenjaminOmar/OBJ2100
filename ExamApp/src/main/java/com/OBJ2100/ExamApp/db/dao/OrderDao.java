package com.OBJ2100.ExamApp.db.dao;

import java.util.List;

import com.OBJ2100.ExamApp.entities.Order;

public interface OrderDao {
	boolean createMany(List<Order> orders);
}
