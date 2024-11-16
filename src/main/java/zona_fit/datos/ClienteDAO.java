package zona_fit.datos;

import zona_fit.conexion.Conexion;
import zona_fit.dominio.Cliente;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClienteDAO implements IClienteDAO{
    @Override
    public List<Cliente> getClientes() {

        List<Cliente> clientes = new ArrayList<>();
        Connection con = Conexion.getConexion();
        PreparedStatement ps;
        ResultSet rs;
        String sql = "SELECT * FROM cliente ORDER BY id";

        try{
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();

            while(rs.next()){
                Cliente cliente = new Cliente(
                        rs.getInt("id"),
                        rs.getString("nombre"),
                        rs.getString("apellido"),
                        rs.getInt("membresia")
                );

                clientes.add(cliente);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        finally {
            try {
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return clientes;
    }

    @Override
    public boolean findCliente(Cliente cliente) {

        Connection con = Conexion.getConexion();
        PreparedStatement ps;
        ResultSet rs;
        String sql = "SELECT * FROM cliente WHERE id = ?";

        try{
            ps = con.prepareStatement(sql);
            ps.setInt(1,cliente.getId());
            rs = ps.executeQuery();

            if(rs.next()){
                cliente.setNombre(rs.getString("nombre"));
                cliente.setApellido(rs.getString("apellido"));
                cliente.setMembresia(rs.getInt("membresia"));

                return true;
            }

        }catch(Exception e){
            e.printStackTrace();
        }
        finally {
            try {
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return false;
    }

    @Override
    public boolean insertCliente(Cliente cliente) {

        Connection con = Conexion.getConexion();
        PreparedStatement ps;
        String sql = "INSERT INTO cliente (nombre,apellido,membresia) values (?,?,?)";

        try{
            ps = con.prepareStatement(sql);
            ps.setString(1,cliente.getNombre());
            ps.setString(2,cliente.getApellido());
            ps.setInt(3,cliente.getMembresia());

            ps.execute();

            System.out.println("Cliente Insertado!");

            return true;

        }catch(Exception e){
            e.printStackTrace();
        }
        finally {
            try {
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return false;
    }

    @Override
    public boolean updateCliente(Cliente cliente) {

        Connection con = Conexion.getConexion();
        PreparedStatement ps;
        String sql = "UPDATE cliente set nombre = ? , apellido = ?, membresia = ? WHERE id = ?";

        try{
            ps = con.prepareStatement(sql);
            ps.setString(1,cliente.getNombre());
            ps.setString(2,cliente.getApellido());
            ps.setInt(3,cliente.getMembresia());
            ps.setInt(4,cliente.getId());

            ps.execute();

            System.out.println("Cliente Actualizado!");

            return true;

        }catch(Exception e){
            e.printStackTrace();
        }
        finally {
            try {
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return false;
    }

    @Override
    public boolean deleteCliente(Cliente cliente) {

        Connection con = Conexion.getConexion();
        PreparedStatement ps;
        String sql = "DELETE FROM cliente WHERE id = ?";

        try{
            ps = con.prepareStatement(sql);
            ps.setInt(1,cliente.getId());

            ps.execute();

            System.out.println("Cliente eliminado!");

            return true;

        }catch(Exception e){
            e.printStackTrace();
        }
        finally {
            try {
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return false;
    }
}
