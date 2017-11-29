/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ar.edu.unnoba.poo2017.tpfinal.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author jpgm
 */
@Entity
@Table(name = "usuarios")
@NamedQueries({
   @NamedQuery(name = "usuario.disponibles",
           query = "Select u From Usuario u"),
    @NamedQuery(name = "usuario.por_username_y_password",
           query = "Select u From Usuario u "
                   + "where u.userName = :username and u.password = :password") 
})
public class Usuario extends AbstractEntity{
    @Column(name = "user_name")
    private String userName;
    private String password;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
