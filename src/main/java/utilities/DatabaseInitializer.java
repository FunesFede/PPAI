package utilities;

import jakarta.persistence.EntityManager;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase para inicializar la base de datos con datos de prueba
 */
public class DatabaseInitializer {

    private static final String DB_URL = "jdbc:sqlite:ppai.db";

    /**
     * Ejecuta el script SQL directamente usando JDBC (no JPA)
     * porque SQLite tiene problemas con transacciones múltiples en JPA
     */
    private static void ejecutarScriptSQL() {
        System.out.println("Cargando datos iniciales desde data.sql...");
        
        try (Connection conn = DriverManager.getConnection(DB_URL);
             Statement stmt = conn.createStatement()) {
            
            // Leer el archivo SQL
            InputStream is = DatabaseInitializer.class.getClassLoader()
                    .getResourceAsStream("data.sql");
            
            if (is == null) {
                System.err.println("ERROR: No se encontró el archivo data.sql");
                return;
            }

            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(is, StandardCharsets.UTF_8));
            
            StringBuilder sqlBuilder = new StringBuilder();
            String line;
            int insertCount = 0;
            
            while ((line = reader.readLine()) != null) {
                line = line.trim();
                
                // Ignorar comentarios y líneas vacías
                if (line.isEmpty() || line.startsWith("--")) {
                    continue;
                }
                
                sqlBuilder.append(line).append(" ");
                
                // Si termina en punto y coma, ejecutar la sentencia
                if (line.endsWith(";")) {
                    String sql = sqlBuilder.toString().trim();
                    
                    // Eliminar el punto y coma final
                    if (sql.endsWith(";")) {
                        sql = sql.substring(0, sql.length() - 1);
                    }
                    
                    try {
                        stmt.executeUpdate(sql);
                        if (sql.toUpperCase().startsWith("INSERT")) {
                            insertCount++;
                        }
                    } catch (Exception e) {
                        System.err.println("Error ejecutando SQL: " + sql);
                        System.err.println("Error: " + e.getMessage());
                    }
                    
                    sqlBuilder = new StringBuilder();
                }
            }
            
            reader.close();
            System.out.println("✓ Datos cargados exitosamente (" + insertCount + " inserts ejecutados)");
            
        } catch (Exception e) {
            System.err.println("ERROR al cargar datos iniciales:");
            e.printStackTrace();
        }
    }

    /**
     * Verifica si la base de datos está vacía
     */
    private static boolean baseDeDatosVacia() {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            Long count = em.createQuery(
                "SELECT COUNT(e) FROM EventoSismico e", Long.class)
                .getSingleResult();
            return count == 0;
        } catch (Exception e) {
            // Si hay error (ej: tabla no existe), consideramos que está vacía
            return true;
        } finally {
            em.close();
        }
    }

    /**
     * Imprime estadísticas de la base de datos
     */
    private static void imprimirEstadisticas() {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            Long eventos = em.createQuery("SELECT COUNT(e) FROM EventoSismico e", Long.class)
                    .getSingleResult();
            Long estaciones = em.createQuery("SELECT COUNT(e) FROM EstacionSismologica e", Long.class)
                    .getSingleResult();
            Long sismografos = em.createQuery("SELECT COUNT(s) FROM Sismografo s", Long.class)
                    .getSingleResult();
            Long usuarios = em.createQuery("SELECT COUNT(u) FROM Usuario u", Long.class)
                    .getSingleResult();
            
            System.out.println("\n" + "=".repeat(50));
            System.out.println("ESTADÍSTICAS DE LA BASE DE DATOS");
            System.out.println("=".repeat(50));
            System.out.println("✓ Eventos sísmicos: " + eventos);
            System.out.println("✓ Estaciones sismológicas: " + estaciones);
            System.out.println("✓ Sismógrafos: " + sismografos);
            System.out.println("✓ Usuarios: " + usuarios);
            System.out.println("=".repeat(50) + "\n");
            
        } catch (Exception e) {
            System.err.println("Error al obtener estadísticas: " + e.getMessage());
        } finally {
            em.close();
        }
    }

    /**
     * Inicializa la base de datos si está vacía
     */
    public static void inicializarSiEsNecesario() {
        System.out.println("Verificando estado de la base de datos...");
        
        if (baseDeDatosVacia()) {
            System.out.println("Base de datos vacía. Cargando datos iniciales...");
            ejecutarScriptSQL();
            imprimirEstadisticas();
        } else {
            System.out.println("Base de datos ya contiene datos.");
            imprimirEstadisticas();
        }
    }

    /**
     * Fuerza la recarga de datos (útil para testing)
     * ⚠️ CUIDADO: Esto elimina todos los datos existentes
     */
    public static void reinicializarBaseDeDatos() {
        System.out.println("⚠️ REINICIALIZANDO BASE DE DATOS...");
        
        EntityManager em = JPAUtil.getEntityManager();
        try {
            em.getTransaction().begin();
            
            // Eliminar todos los datos en orden inverso a las dependencias
            em.createQuery("DELETE FROM CambioEstado").executeUpdate();
            em.createQuery("DELETE FROM DetalleMuestraSismica").executeUpdate();
            em.createQuery("DELETE FROM MuestraSismica").executeUpdate();
            em.createQuery("DELETE FROM SerieTemporal").executeUpdate();
            em.createQuery("DELETE FROM EventoSismico").executeUpdate();
            em.createQuery("DELETE FROM Sismografo").executeUpdate();
            em.createQuery("DELETE FROM EstacionSismologica").executeUpdate();
            em.createQuery("DELETE FROM Usuario").executeUpdate();
            em.createQuery("DELETE FROM Empleado").executeUpdate();
            
            em.getTransaction().commit();
            
            System.out.println("✓ Datos eliminados");
            
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            System.err.println("Error al limpiar la base de datos: " + e.getMessage());
        } finally {
            em.close();
        }
        
        // Cargar datos frescos
        ejecutarScriptSQL();
        imprimirEstadisticas();
    }
}