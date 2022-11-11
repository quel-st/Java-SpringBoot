package br.univille.novostalentos.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import org.springframework.format.annotation.DateTimeFormat;


// dados do nosso sistema - ENTITY

// 
@Entity
public class Cliente {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(length = 1000, nullable = false)
    @NotBlank(message = "Campo nome nao pode ser em branco")
    private String nome;
    @Column(length = 3000)
    @NotBlank(message = "campo endereço nao pode ser em branco")
    private String endereco;
    @NotBlank(message = "campo sexo nao pode ser em branco")
    @Pattern(regexp = "Masculino|Feminino",
            flags = Pattern.Flag.CANON_EQ,
            message = "Valor invalido, utilize Masculino ou Feminino")
    private String sexo;
    // como eu salvo no banco
    @Temporal(value = TemporalType.DATE)
    // como eu recebo no formulario
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dataNascimento;


    // pro banco é por codigo e pelo java é por referencia
    

    // muitos pra um
    // associando o cliente com a cidade sem usar sql
    @ManyToOne
    private Cidade cidadeResidencia;

    public Cidade getCidadeResidencia() {
        return cidadeResidencia;
    }
    public void setCidadeResidencia(Cidade cidadeResidencia) {
        this.cidadeResidencia = cidadeResidencia;
    }
    public Date getDataNascimento() {
        return dataNascimento;
    }
    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public String getEndereco() {
        return endereco;
    }
    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }
    public String getSexo() {
        return sexo;
    }
    public void setSexo(String sexo) {
        this.sexo = sexo;
    }


    

}
