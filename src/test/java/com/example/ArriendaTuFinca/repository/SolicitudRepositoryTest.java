/*package com.example.ArriendaTuFinca.repository;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.example.ArriendaTuFinca.models.Solicitud;
import com.example.ArriendaTuFinca.models.Usuario;
import com.example.ArriendaTuFinca.models.Propiedad;

@DataJpaTest
@ExtendWith(SpringExtension.class)
class SolicitudRepositoryTest {

    @Autowired
    private SolicitudRepository solicitudRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PropiedadRepository propiedadRepository;

    private Usuario arrendatario;
    private Propiedad propiedad;

    @BeforeEach
    void setUp() {
        // Crear una entidad Usuario (Arrendatario)
        arrendatario = new Usuario();
        arrendatario.setNombre("Arrendatario Test");
        arrendatario = usuarioRepository.save(arrendatario);

        // Crear una entidad Propiedad
        propiedad = new Propiedad();
        propiedad.setNombre("Propiedad Test");
        propiedad = propiedadRepository.save(propiedad);
    }

    @Test
    void testSaveSolicitud() {
        // Crear una instancia de Solicitud
        Solicitud solicitud = new Solicitud();
        solicitud.setArrendatario_id(arrendatario);
        solicitud.setPropiedad_id(propiedad);
        solicitud.setFecha_inicio(LocalDate.of(2024, 1, 1));
        solicitud.setFecha_fin(LocalDate.of(2024, 1, 7));
        solicitud.setPrecio_por_noche(100);

        // Guardar la solicitud en la base de datos
        Solicitud savedSolicitud = solicitudRepository.save(solicitud);

        // Asegurarse de que se ha guardado correctamente
        assertNotNull(savedSolicitud);
        assertNotNull(savedSolicitud.getSolicitud_id());
        assertEquals(arrendatario.getUsuario_id(), savedSolicitud.getArrendatario_id().getUsuario_id());
        assertEquals(propiedad.getPropiedad_id(), savedSolicitud.getPropiedad_id().getPropiedad_id());
        assertEquals(LocalDate.of(2024, 1, 1), savedSolicitud.getFecha_inicio());
        assertEquals(LocalDate.of(2024, 1, 7), savedSolicitud.getFecha_fin());
        assertEquals(100, savedSolicitud.getPrecio_por_noche());
    }

    @Test
    void testFindById() {
        // Crear y guardar una nueva solicitud
        Solicitud solicitud = new Solicitud();
        solicitud.setArrendatario_id(arrendatario);
        solicitud.setPropiedad_id(propiedad);
        solicitud.setFecha_inicio(LocalDate.of(2024, 1, 10));
        solicitud.setFecha_fin(LocalDate.of(2024, 1, 15));
        solicitud.setPrecio_por_noche(150);

        Solicitud savedSolicitud = solicitudRepository.save(solicitud);

        // Buscar la solicitud por ID
        Optional<Solicitud> foundSolicitud = solicitudRepository.findById(savedSolicitud.getSolicitud_id());

        // Asegurarse de que se ha encontrado correctamente
        assertTrue(foundSolicitud.isPresent());
        assertEquals(savedSolicitud.getSolicitud_id(), foundSolicitud.get().getSolicitud_id());
    }

    @Test
    void testDeleteSolicitud() {
        // Crear y guardar una nueva solicitud
        Solicitud solicitud = new Solicitud();
        solicitud.setArrendatario_id(arrendatario);
        solicitud.setPropiedad_id(propiedad);
        solicitud.setFecha_inicio(LocalDate.of(2024, 2, 1));
        solicitud.setFecha_fin(LocalDate.of(2024, 2, 5));
        solicitud.setPrecio_por_noche(120);

        Solicitud savedSolicitud = solicitudRepository.save(solicitud);

        // Eliminar la solicitud por ID
        solicitudRepository.deleteById(savedSolicitud.getSolicitud_id());

        // Verificar que la solicitud ha sido eliminada
        Optional<Solicitud> deletedSolicitud = solicitudRepository.findById(savedSolicitud.getSolicitud_id());
        assertFalse(deletedSolicitud.isPresent());
    }

    @Test
    void testFindAll() {
        // Crear y guardar varias solicitudes
        Solicitud solicitud1 = new Solicitud();
        solicitud1.setArrendatario_id(arrendatario);
        solicitud1.setPropiedad_id(propiedad);
        solicitud1.setFecha_inicio(LocalDate.of(2024, 3, 1));
        solicitud1.setFecha_fin(LocalDate.of(2024, 3, 5));
        solicitud1.setPrecio_por_noche(100);

        Solicitud solicitud2 = new Solicitud();
        solicitud2.setArrendatario_id(arrendatario);
        solicitud2.setPropiedad_id(propiedad);
        solicitud2.setFecha_inicio(LocalDate.of(2024, 4, 1));
        solicitud2.setFecha_fin(LocalDate.of(2024, 4, 5));
        solicitud2.setPrecio_por_noche(150);

        solicitudRepository.save(solicitud1);
        solicitudRepository.save(solicitud2);

        // Obtener todas las solicitudes
        List<Solicitud> solicitudes = solicitudRepository.findAll();

        // Verificar que se han encontrado todas las solicitudes
        assertNotNull(solicitudes);
        assertTrue(solicitudes.size() >= 2);
    }
}
*/