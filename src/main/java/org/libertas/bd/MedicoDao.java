package org.libertas.bd;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

public class MedicoDao {
    public void inserir(Medico m) {
        Conexao con = new Conexao();
        try {
            String sql = "INSERT INTO medicos (nome, especialidade, crm, idade, formacao) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement pstmt = con.getConnection().prepareStatement(sql);
            pstmt.setString(1, m.getNome());
            pstmt.setString(2, m.getEspecialidade());
            pstmt.setString(3, m.getCrm());
            pstmt.setInt(4, m.getIdade());
            pstmt.setString(5, m.getFormacao());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            con.desconectar();
        }
    }

    public void alterar(Medico m) {
        Conexao con = new Conexao();
        try {
            String sql = "UPDATE medicos SET nome = ?, especialidade = ?, crm = ? , idade = ?, formacao = ? WHERE idmedico = ?";
            PreparedStatement pstmt = con.getConnection().prepareStatement(sql);
            pstmt.setString(1, m.getNome());
            pstmt.setString(2, m.getEspecialidade());
            pstmt.setString(3, m.getCrm());
            pstmt.setInt(4, m.getIdade());
            pstmt.setString(5, m.getFormacao());
            pstmt.setInt(6, m.getIdmedico());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            con.desconectar();
        }
    }

    public void excluir(int id) {
        Conexao con = new Conexao();
        try {
        	System.out.println("delete");
        	System.out.println(id);
            String sql = "DELETE FROM medicos WHERE idmedico = ?";
            PreparedStatement pstmt = con.getConnection().prepareStatement(sql);
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            con.desconectar();
        }
    }

    public Medico consultar(int id) {
        Conexao con = new Conexao();
        try {
            String sql = "SELECT * FROM medicos WHERE idmedico = ?";
            PreparedStatement pstmt = con.getConnection().prepareStatement(sql);
            pstmt.setInt(1, id);
            ResultSet res = pstmt.executeQuery();
            if (res.next()) {
                Medico m = new Medico();
                m.setIdmedico(res.getInt("idmedico"));
                m.setNome(res.getString("nome"));
                m.setEspecialidade(res.getString("especialidade"));
                m.setCrm(res.getString("crm"));
                m.setIdade(res.getInt("idade"));
                m.setFormacao(res.getString("formacao"));
                return m;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            con.desconectar();
        }
        return null;
    }

    public List<Medico> listar() {
        List<Medico> lista = new LinkedList<>();
        Conexao con = new Conexao();
        try {
            String sql = "SELECT * FROM medicos ORDER BY nome";
            Statement sta = con.getConnection().createStatement();
            ResultSet res = sta.executeQuery(sql);
            while (res.next()) {
                Medico m = new Medico();
                m.setIdmedico(res.getInt("idmedico"));
                m.setNome(res.getString("nome"));
                m.setEspecialidade(res.getString("especialidade"));
                m.setCrm(res.getString("crm"));
                m.setIdade(res.getInt("idade"));
                m.setFormacao(res.getString("formacao"));
                lista.add(m);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        con.desconectar();
        return lista;
    }
}
