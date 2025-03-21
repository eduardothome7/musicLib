package com.example.musicLib.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.musicLib.model.Song;

public interface SongRepository extends JpaRepository<Song, Long> {

}
