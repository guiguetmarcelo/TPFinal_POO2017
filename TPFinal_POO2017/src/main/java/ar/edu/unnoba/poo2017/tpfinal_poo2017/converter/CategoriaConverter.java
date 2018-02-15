
package ar.edu.unnoba.poo2017.tpfinal_poo2017.converter;

import ar.edu.unnoba.poo2017.tpfinal_poo2017.model.Categoria;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author Marcelo
 * @author Sebastian
 */

@FacesConverter(forClass=Categoria.class)
public class CategoriaConverter extends AbstractConverter<Categoria>{

    @Override
    public String getDAOName() {
        return "CategoriaDao";
    }
    
}
