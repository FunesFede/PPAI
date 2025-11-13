package utilities;

import entidades.*;
import entidades.estado.Estado;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

import java.util.List;


public class RepositorioDatos {
    
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
    
    public static <T> T buscarPorId(Class<T> clase, Object id) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            return em.find(clase, id);
        } finally {
            em.close();
        }
    }

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
