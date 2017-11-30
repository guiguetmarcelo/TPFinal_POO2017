package ar.edu.unnoba.poo2017.tpfinal_poo2017.model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "usuarios")
@NamedQueries({
   @NamedQuery(name = "usuario.disponibles",
           query = "Select u From Usuario u"),
    @NamedQuery(name = "usuario.por_username_y_password",
           query = "Select u From Usuario u "
                   + "where u.username = :username and u.password = :password")})
public class Usuario extends AbstractEntity implements Serializable{
    
    @Basic(optional = false)
    @Column(nullable = false)
    private String username;
    
    @Basic(optional = false)
    @Column(nullable = false)
    private String email;
    
    @Basic(optional = false)
    @Column(nullable = false)
    private String password;
    
    @Basic(optional = false)
    @Column(nullable = false)
    private String apellido;
    
    @Basic(optional = false)
    @Column(nullable = false)
    private String nombre;
    
    @Basic(optional = false)
    @Column(nullable = false)
    private Boolean activo;

    public Usuario() {

    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Boolean getActivo() {
        return activo;
    }

    public void setActivo(Boolean activo) {
        this.activo = activo;
    }

}
