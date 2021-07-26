package br.com.alurachallenge.video;

import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface VideoMapper {

    Video toVideo(VideoRequest videoRequest);

    VideoResponse toVideoRespose(Video video);

    List<VideoResponse> toVideosResponse(List<Video> videos);
}
