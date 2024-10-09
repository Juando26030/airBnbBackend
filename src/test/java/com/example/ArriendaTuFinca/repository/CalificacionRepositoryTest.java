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

import com.example.ArriendaTuFinca.models.Calificacion;
import com.example.ArriendaTuFinca.models.Solicitud;

@DataJpaTest
@ExtendWith(SpringExtension.class)
class CalificacionRepositoryTest {

    @Autowired
    private CalificacionRepository calificacionRepository;

    @Autowired
    private SolicitudRepository solicitudRepository;

    private Solicitud solicitud;

    @BeforeEach
    void setUp() {
        // Crear y guardar una entidad Solicitud
        solicitud = new Solicitud();
        solicitud.setFecha_inicio(LocalDate.of(2024, 1, 1));
        solicitud.setFecha_fin(LocalDate.of(2024, 1, 10));
        solicitud.setPrecio_por_noche(150);
        solicitud = solicitudRepository.save(solicitud);
    }

    @Test
    void testSaveCalificacion() {
        // Crear una instancia de Calificacion
        Calificacion calificacion = new Calificacion();
        calificacion.setSolicitud_id(solicitud);
        calificacion.setCalificacion_propiedad(5);
        calificacion.setCalificacion_arrendatario(4);
        calificacion.setComentario("Excelente experiencia");
        calificacion.setFecha_calificacion(LocalDate.of(2024, 1, 15));

        // Guardar la calificación en la base de datos
        Calificacion savedCalificacion = calificacionRepository.save(calificacion);

        // Asegurarse de que se ha guardado correctamente
        assertNotNull(savedCalificacion);
        assertNotNull(savedCalificacion.getCalificacion_id());
        assertEquals(solicitud.getSolicitud_id(), savedCalificacion.getSolicitud_id().getSolicitud_id());
        assertEquals(5, savedCalificacion.getCalificacion_propiedad());
        assertEquals(4, savedCalificacion.getCalificacion_arrendatario());
        assertEquals("Excelente experiencia", savedCalificacion.getComentario());
        assertEquals(LocalDate.of(2024, 1, 15), savedCalificacion.getFecha_calificacion());
    }

    @Test
    void testFindById() {
        // Crear y guardar una nueva calificación
        Calificacion calificacion = new Calificacion();
        calificacion.setSolicitud_id(solicitud);
        calificacion.setCalificacion_propiedad(3);
        calificacion.setCalificacion_arrendatario(4);
        calificacion.setComentario("Buena experiencia");
        calificacion.setFecha_calificacion(LocalDate.of(2024, 2, 10));

        Calificacion savedCalificacion = calificacionRepository.save(calificacion);

        // Buscar la calificación por ID
        Optional<Calificacion> foundCalificacion = calificacionRepository.findById(savedCalificacion.getCalificacion_id());

        // Asegurarse de que se ha encontrado correctamente
        assertTrue(foundCalificacion.isPresent());
        assertEquals(savedCalificacion.getCalificacion_id(), foundCalificacion.get().getCalificacion_id());
    }

    @Test
    void testDeleteCalificacion() {
        // Crear y guardar una nueva calificación
        Calificacion calificacion = new Calificacion();
        calificacion.setSolicitud_id(solicitud);
        calificacion.setCalificacion_propiedad(4);
        calificacion.setCalificacion_arrendatario(5);
        calificacion.setComentario("Muy buena experiencia");
        calificacion.setFecha_calificacion(LocalDate.of(2024, 3, 5));

        Calificacion savedCalificacion = calificacionRepository.save(calificacion);

        // Eliminar la calificación por ID
        calificacionRepository.deleteById(savedCalificacion.getCalificacion_id());

        // Verificar que la calificación ha sido eliminada
        Optional<Calificacion> deletedCalificacion = calificacionRepository.findById(savedCalificacion.getCalificacion_id());
        assertFalse(deletedCalificacion.isPresent());
    }

    @Test
    void testFindAll() {
        // Crear y guardar varias calificaciones
        Calificacion calificacion1 = new Calificacion();
        calificacion1.setSolicitud_id(solicitud);
        calificacion1.setCalificacion_propiedad(5);
        calificacion1.setCalificacion_arrendatario(3);
        calificacion1.setComentario("Muy buena estancia");
        calificacion1.setFecha_calificacion(LocalDate.of(2024, 4, 1));

        Calificacion calificacion2 = new Calificacion();
        calificacion2.setSolicitud_id(solicitud);
        calificacion2.setCalificacion_propiedad(4);
        calificacion2.setCalificacion_arrendatario(4);
        calificacion2.setComentario("Buena relación calidad-precio");
        calificacion2.setFecha_calificacion(LocalDate.of(2024, 5, 1));

        calificacionRepository.save(calificacion1);
        calificacionRepository.save(calificacion2);

        // Obtener todas las calificaciones
        List<Calificacion> calificaciones = calificacionRepository.findAll();

        // Verificar que se han encontrado todas las calificaciones
        assertNotNull(calificaciones);
        assertTrue(calificaciones.size() >= 2);
    }
}
*/