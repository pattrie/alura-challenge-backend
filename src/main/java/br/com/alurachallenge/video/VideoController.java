package br.com.alurachallenge.video;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@ApiModel(description = "Detalhes sobre o Vídeo")
@ApiOperation(value = "/videos", tags = "VideoController")
@RestController
@RequestMapping("/videos")
public class VideoController {

    private final VideoService videoService;

    public VideoController(VideoService videoService) {
        this.videoService = videoService;
    }

    @ApiOperation(value = "Retorna todos os vídeos")
    @GetMapping
    public List<VideoResponse> getAll(){
        return videoService.getAll();
    }

    @ApiOperation(value = "Retorna um vídeo conforme ID")
    @ApiImplicitParams(@ApiImplicitParam(name = "Vídeo", value = "Retorna um vídeo", required = true))
    @GetMapping("/{id}")
    public ResponseEntity getVideo(@PathVariable("id") String id){
        return videoService.getVideo(id);
    }

    @ApiOperation(value = "Adiciona um novo vídeo")
    @PostMapping
    public ResponseEntity addVideo(@RequestBody @Valid VideoRequest videoRequest) {
        return videoService.addVideo(videoRequest);
    }

    @ApiOperation(value = "Deleta um vídeo conforme ID")
    @DeleteMapping("/{id}")
    public ResponseEntity deleteVideo(@PathVariable("id") Integer id) {
        return videoService.deleteVideo(id);
    }

    @ApiOperation(value = "Atualiza um vídeo conforme ID e informações inseridas")
    @PutMapping("/{id}")
    public ResponseEntity updateVideo(@RequestBody @Valid VideoRequest videoRequest,
                                      @PathVariable("id") String id){
        return videoService.updateVideo(videoRequest, id);
    }

}
