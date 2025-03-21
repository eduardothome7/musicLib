package com.example.musicLib.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.musicLib.model.Song;
import com.example.musicLib.service.SongService;

@RestController
@RequestMapping("/songs")
public class SongController {

	@Autowired
	private SongService service;

	@GetMapping
	public List<Song> list(){
		return service.getAll();
	}
	
	@PostMapping
	public Song addSong(@RequestBody Song song) {
		return service.save(song);
	}
}
