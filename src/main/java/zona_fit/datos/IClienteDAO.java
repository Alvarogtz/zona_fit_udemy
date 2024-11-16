package zona_fit.datos;

import zona_fit.dominio.Cliente;

import java.util.List;

public interface IClienteDAO {
    public List<Cliente> getClientes();
    public boolean findCliente(Cliente cliente);
    public boolean insertCliente(Cliente cliente);
    public boolean updateCliente(Cliente cliente);
    public boolean deleteCliente(Cliente cliente);

}
