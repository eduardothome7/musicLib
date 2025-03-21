package com.example.musicLib.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.musicLib.model.Song;
import com.example.musicLib.repository.SongRepository;

@Service
public class SongService implements SharedService<Song> {

	@Autowired
	private SongRepository songRepository;

	@Override
	public List<Song> getAll(){
		return songRepository.findAll();
	}

	@Override
	public Optional<Song> getById(Long id) {
		return songRepository.findById(id);
	}

	@Override
	public Song save(Song song) {
		return songRepository.save(song);
	}

	@Override
	public void delete(Long id) {
		songRepository.deleteById(id);
	}
}
