package br.com.alurachallenge.video;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;

@ApiModel(description = "Detalhes sobre o vídeo")
@Getter
@Setter
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@AllArgsConstructor
public class VideoResponse {

    private Integer id;
    @ApiModelProperty(notes = "Título do vídeo", example = "A volta dos que não foram")
    private String titulo;
    @ApiModelProperty(notes = "Descrição do vídeo", example = "Esse vídeo conta a drámatica história daqueles que nunca chegaram a ir")
    private String descricao;
    @ApiModelProperty(notes = "URL do vídeo", example = "https://youtu.be/TQ31CTjtSv0")
    private String url;
}
