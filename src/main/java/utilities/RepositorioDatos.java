package utilities;

import entidades.*;
import entidades.estado.Estado;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

import java.util.ArrayList;
import java.util.List;

/**
 * Repositorio para acceso a datos de la base de datos
 * Proporciona métodos para cargar entidades usando JPA
 */
public class RepositorioDatos {
    
    /**
     * Carga todos los eventos sísmicos
     */
    public static List<EventoSismico> cargarEventosSismicos() {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            TypedQuery<EventoSismico> query = em.createQuery(
                "SELECT e FROM EventoSismico e", EventoSismico.class);
            return query.getResultList();
        } finally {
            em.close();
        }
    }
    
    /**
     * Carga eventos sísmicos en estado auto-detectado
     */
    public static List<EventoSismico> cargarEventosSismicosAutoDetectados() {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            TypedQuery<EventoSismico> query = em.createQuery(
                "SELECT e FROM EventoSismico e WHERE e.estadoActual.nombreEstado = :estado", 
                EventoSismico.class);
            query.setParameter("estado", "Auto Detectado");
            return query.getResultList();
        } finally {
            em.close();
        }
    }
    
    /**
     * Carga todas las estaciones sismológicas
     */
    public static List<EstacionSismologica> cargarEstacionesSismologicas() {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            TypedQuery<EstacionSismologica> query = em.createQuery(
                "SELECT e FROM EstacionSismologica e", EstacionSismologica.class);
            return query.getResultList();
        } finally {
            em.close();
        }
    }
    
    /**
     * Carga todos los sismógrafos
     */
    public static List<Sismografo> cargarSismografos() {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            TypedQuery<Sismografo> query = em.createQuery(
                "SELECT s FROM Sismografo s", Sismografo.class);
            return query.getResultList();
        } finally {
            em.close();
        }
    }
    
    /**
     * Carga todas las series temporales
     */
    public static List<SerieTemporal> cargarSeriesTemporales() {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            TypedQuery<SerieTemporal> query = em.createQuery(
                "SELECT s FROM SerieTemporal s", SerieTemporal.class);
            return query.getResultList();
        } finally {
            em.close();
        }
    }
    
    /**
     * Busca un usuario por nombre de usuario
     */
    public static Usuario buscarUsuarioPorNombre(String nombreUsuario) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            TypedQuery<Usuario> query = em.createQuery(
                "SELECT u FROM Usuario u WHERE u.nombreUsuario = :nombre", Usuario.class);
            query.setParameter("nombre", nombreUsuario);
            List<Usuario> resultados = query.getResultList();
            return resultados.isEmpty() ? null : resultados.get(0);
        } finally {
            em.close();
        }
    }
    
    /**
     * Busca un estado por su tipo
     */
    public static Estado buscarEstadoPorTipo(String tipoEstado) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            TypedQuery<Estado> query = em.createQuery(
                "SELECT e FROM Estado e WHERE TYPE(e) = :tipo", Estado.class);
            query.setParameter("tipo", tipoEstado);
            List<Estado> resultados = query.getResultList();
            return resultados.isEmpty() ? null : resultados.get(0);
        } finally {
            em.close();
        }
    }
    
    /**
     * Busca un estado por su nombre
     */
    public static Estado buscarEstadoPorNombre(String nombreEstado) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            TypedQuery<Estado> query = em.createQuery(
                "SELECT e FROM Estado e WHERE e.nombreEstado = :nombre", Estado.class);
            query.setParameter("nombre", nombreEstado);
            List<Estado> resultados = query.getResultList();
            return resultados.isEmpty() ? null : resultados.get(0);
        } finally {
            em.close();
        }
    }
    
    /**
     * Guarda una entidad en la base de datos
     */
    public static <T> void guardar(T entidad) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(entidad);
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw e;
        } finally {
            em.close();
        }
    }
    
    /**
     * Actualiza una entidad en la base de datos
     */
    public static <T> T actualizar(T entidad) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            em.getTransaction().begin();
            T resultado = em.merge(entidad);
            em.getTransaction().commit();
            return resultado;
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw e;
        } finally {
            em.close();
        }
    }
    
    /**
     * Elimina una entidad de la base de datos
     */
    public static <T> void eliminar(Class<T> clase, Object id) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            em.getTransaction().begin();
            T entidad = em.find(clase, id);
            if (entidad != null) {
                em.remove(entidad);
            }
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw e;
        } finally {
            em.close();
        }
    }
    
    /**
     * Busca una entidad por su ID
     */
    public static <T> T buscarPorId(Class<T> clase, Object id) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            return em.find(clase, id);
        } finally {
            em.close();
        }
    }

    // ...existing code...

    /**
     * Carga la sesión activa (última sesión sin fecha_hora_fin)
     */
    public static Sesion cargarSesionActiva() {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            return em.createQuery(
                "SELECT s FROM Sesion s WHERE s.fechaHoraFin IS NULL ORDER BY s.fechaHoraInicio DESC", 
                Sesion.class)
                .setMaxResults(1)
                .getSingleResult();
        } catch (Exception e) {
            return null;
        } finally {
            em.close();
        }
    }

    /**
     * Carga un usuario por su nombre de usuario
     */
    public static Usuario cargarUsuarioPorNombre(String nombreUsuario) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            return em.createQuery(
                "SELECT u FROM Usuario u WHERE u.nombreUsuario = :nombre", 
                Usuario.class)
                .setParameter("nombre", nombreUsuario)
                .getSingleResult();
        } catch (Exception e) {
            return null;
        } finally {
            em.close();
        }
    }
}
