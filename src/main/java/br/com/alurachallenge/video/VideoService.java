package br.com.alurachallenge.video;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VideoService {

    private VideoRepository videoRepository;

    private VideoMapper videoMapper;

    public VideoService(VideoRepository videoRepository, VideoMapper videoMapper) {
        this.videoRepository = videoRepository;
        this.videoMapper = videoMapper;
    }

    public ResponseEntity addVideo(VideoRequest videoRequest) {
        try {
            VideoResponse videoResponse = videoMapper.toVideoRespose(videoRepository.save(videoMapper.toVideo(videoRequest)));
            return new ResponseEntity<>(videoResponse, HttpStatus.CREATED);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    public List<VideoResponse> getAll() {
            return videoMapper.toVideosResponse(videoRepository.findAll());
    }

    public ResponseEntity getVideo(String id) {
        try {
            Optional<Video> video = videoRepository.findById(Integer.parseInt(id));
            if (video.isPresent()) return new ResponseEntity<>(videoMapper.toVideoRespose(video.get()), HttpStatus.OK);
            return ResponseEntity.badRequest().body("Não encontrado.");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    public ResponseEntity deleteVideo(Integer id) {
        try {
            videoRepository.deleteById(id);
            return ResponseEntity.ok("Vídeo removido.");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Falha : " + e.getMessage());
        }
    }

    public ResponseEntity updateVideo(VideoRequest videoRequest, String id) {
        return videoRepository.findById(Integer.parseInt(id))
                .map(video -> {
                   video.setTitulo(videoRequest.getTitulo());
                   video.setDescricao(videoRequest.getDescricao());
                   video.setUrl(videoRequest.getUrl());
                    Video save = videoRepository.save(video);
                    return ResponseEntity.ok().body(videoMapper.toVideoRespose(save));
                }).orElse(ResponseEntity.notFound().build());
    }
}
