package ar.edu.unnoba.poo2017.tpfinal_poo2017.model;



import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "usuarios")
@NamedQueries({
   @NamedQuery(name = "usuario.disponibles",
           query = "Select u From Usuario u Where u.activo = TRUE"),
    @NamedQuery(name = "usuario.totales",
           query = "Select u From Usuario u"),
    @NamedQuery(name = "usuario.por_username_y_password",
           query = "Select u From Usuario u "
                   + "where u.username = :username and u.password = :password"),
    @NamedQuery(name = "usuario.por_username_y_password_activo",
           query = "Select u From Usuario u where u.username = :username and u.password = :password and u.activo = TRUE"),
    @NamedQuery(name = "usuario.por_username_password_empresa_activo",
           query = "Select u From Usuario u where u.username = :username AND u.password = :password AND u.empresa = :empresa AND u.activo = TRUE")
})
public class Usuario extends AbstractEntity implements Serializable{

    private static final long serialVersionUID = -7755946055021437896L;
    
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
    private Boolean activo = true;
    
    @Enumerated(EnumType.ORDINAL)
    @Basic(optional = false)
    @Column(name = "rol", nullable = false)
    private RolUsuario rol;
    
    

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

    public Boolean isActivo() {
        return activo;
    }

    public void setActivo(Boolean activo) {
        this.activo = activo;
    }

    public String getEstado(){
        if(isActivo())
            return "Activo";
        return "Borrado";
    }

    public RolUsuario getRol() {
        return rol;
    }

    public void setRol(RolUsuario rol) {
        this.rol = rol;
    }
    
    
    
    public Boolean isAdmGen(){
        return getRol().isAdmGen();
    }
    
    public Boolean isAdmEmp(){
        return getRol().isAdmEmp();
    }
}
