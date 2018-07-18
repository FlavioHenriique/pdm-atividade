package pdm.ifpb.com.pdm_orm;

import com.orm.SugarRecord;

import java.util.Objects;

public class Usuario extends SugarRecord<Usuario> {

    private String nome;
    private int idade;

    public Usuario(){

    }

    public Usuario(String nome, int idade) {
        this.nome = nome;
        this.idade = idade;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }



    @Override
    public String toString() {
        return "Usuario{" +
                "nome='" + nome + '\'' +
                ", idade=" + idade +
                ", id=" + id +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Usuario usuario = (Usuario) o;
        return idade == usuario.idade &&
                Objects.equals(nome, usuario.nome);
    }

    @Override
    public int hashCode() {

        return Objects.hash(nome, idade);
    }
}
