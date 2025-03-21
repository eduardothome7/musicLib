package com.example.musicLib.service;

import java.util.List;
import java.util.Optional;

public interface SharedService<T> {
	List<T> getAll();
	Optional<T> getById(Long id);
	T save(T entity);
	void delete(Long id);
}
