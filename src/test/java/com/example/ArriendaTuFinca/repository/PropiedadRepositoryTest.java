/*package com.example.ArriendaTuFinca.repository;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.example.ArriendaTuFinca.models.Propiedad;
import com.example.ArriendaTuFinca.models.Usuario;
import com.example.ArriendaTuFinca.models.Estado;

@DataJpaTest
@ExtendWith(SpringExtension.class)
class PropiedadRepositoryTest {

    @Autowired
    private PropiedadRepository propiedadRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    private Usuario arrendador;

    @BeforeEach
    void setUp() {
        // Crear y guardar una entidad Usuario (arrendador)
        arrendador = new Usuario();
        arrendador.setNombre("Arrendador Test");
        arrendador = usuarioRepository.save(arrendador);
    }

    @Test
    void testSavePropiedad() {
        // Crear una instancia de Propiedad
        Propiedad propiedad = new Propiedad();
        propiedad.setArrendador_id(arrendador);
        propiedad.setNombre("Propiedad Test");
        propiedad.setDepartamento("Departamento Test");
        propiedad.setMunicipio("Municipio Test");
        propiedad.setTipo_de_ingreso("Directo");
        propiedad.setDescripcion("Una hermosa propiedad");
        propiedad.setCant_banos(2);
        propiedad.setCant_habitaciones(3);
        propiedad.setMascotas(true);
        propiedad.setPiscina(false);
        propiedad.setAsador(true);
        propiedad.setValor_noche(100);
        propiedad.setVisible(true);
        propiedad.setCalificacion(4.5f);

        // Guardar la propiedad en la base de datos
        Propiedad savedPropiedad = propiedadRepository.save(propiedad);

        // Asegurarse de que se ha guardado correctamente
        assertNotNull(savedPropiedad);
        assertNotNull(savedPropiedad.getPropiedad_id());
        assertEquals("Propiedad Test", savedPropiedad.getNombre());
        assertEquals(arrendador.getUsuario_id(), savedPropiedad.getArrendador_id().getUsuario_id());
        assertEquals("Departamento Test", savedPropiedad.getDepartamento());
        assertEquals(2, savedPropiedad.getCant_banos());
    }

    @Test
    void testFindById() {
        // Crear y guardar una nueva propiedad
        Propiedad propiedad = new Propiedad();
        propiedad.setArrendador_id(arrendador);
        propiedad.setNombre("Otra Propiedad");
        propiedad.setDepartamento("Otro Departamento");
        propiedad.setCant_habitaciones(4);
        propiedad.setValor_noche(150);
        propiedad.setVisible(true);

        Propiedad savedPropiedad = propiedadRepository.save(propiedad);

        // Buscar la propiedad por ID
        Optional<Propiedad> foundPropiedad = propiedadRepository.findById(savedPropiedad.getPropiedad_id());

        // Asegurarse de que se ha encontrado correctamente
        assertTrue(foundPropiedad.isPresent());
        assertEquals(savedPropiedad.getPropiedad_id(), foundPropiedad.get().getPropiedad_id());
    }

    @Test
    void testDeletePropiedad() {
        // Crear y guardar una nueva propiedad
        Propiedad propiedad = new Propiedad();
        propiedad.setArrendador_id(arrendador);
        propiedad.setNombre("Propiedad a eliminar");
        propiedad.setDepartamento("Departamento a eliminar");
        propiedad.setCant_banos(1);
        propiedad.setValor_noche(120);
        propiedad.setVisible(false);

        Propiedad savedPropiedad = propiedadRepository.save(propiedad);

        // Eliminar la propiedad por ID
        propiedadRepository.deleteById(savedPropiedad.getPropiedad_id());

        // Verificar que la propiedad ha sido eliminada
        Optional<Propiedad> deletedPropiedad = propiedadRepository.findById(savedPropiedad.getPropiedad_id());
        assertFalse(deletedPropiedad.isPresent());
    }

    @Test
    void testFindAll() {
        // Crear y guardar varias propiedades
        Propiedad propiedad1 = new Propiedad();
        propiedad1.setArrendador_id(arrendador);
        propiedad1.setNombre("Propiedad 1");
        propiedad1.setDepartamento("Departamento 1");
        propiedad1.setCant_habitaciones(3);
        propiedad1.setValor_noche(200);
        propiedad1.setVisible(true);

        Propiedad propiedad2 = new Propiedad();
        propiedad2.setArrendador_id(arrendador);
        propiedad2.setNombre("Propiedad 2");
        propiedad2.setDepartamento("Departamento 2");
        propiedad2.setCant_habitaciones(4);
        propiedad2.setValor_noche(250);
        propiedad2.setVisible(true);

        propiedadRepository.save(propiedad1);
        propiedadRepository.save(propiedad2);

        // Obtener todas las propiedades
        List<Propiedad> propiedades = propiedadRepository.findAll();

        // Verificar que se han encontrado todas las propiedades
        assertNotNull(propiedades);
        assertTrue(propiedades.size() >= 2);
    }
}
*/