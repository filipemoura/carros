package com.carros.api.carros;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@NoArgsConstructor
@Data
@Entity(name = "carro")
public class Carro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    
    private String tipo;
    
    private String descricao;
    
    @Column(name = "url_foto")
    private String urlFoto;
    
    @Column(name = "url_video")
    private String urlVideo;
    
    private String latitude;
    
    private String longitude;

    public Carro(Long id, String nome) {
        this.id = id;
        this.nome = nome;
    }

}
