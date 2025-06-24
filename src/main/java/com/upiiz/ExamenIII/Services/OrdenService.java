package com.upiiz.ExamenIII.Services;

import com.upiiz.ExamenIII.Models.OrdenModel;
import com.upiiz.ExamenIII.Repositories.OrdenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Service;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;

@Service
public class OrdenService implements OrdenRepository {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public List<OrdenModel> findAllOrdenes() {
        return jdbcTemplate.query("SELECT * FROM detalle_orden",
                new BeanPropertyRowMapper<>(OrdenModel.class));
    }

    @Override
    public OrdenModel findOrdenById(int id) {
        return jdbcTemplate.query("SELECT * FROM detalle_orden",
                new BeanPropertyRowMapper<>(OrdenModel.class),id)
                .stream().findFirst().orElse(new OrdenModel());
    }

    @Override
    public OrdenModel save(OrdenModel orden) {
        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connectio -> {
            PreparedStatement ps = connectio.prepareStatement(
                    "INSERT INTO detalle_orden(ordenid,productoid,cantidad) VALUES (?,?,?)",
                    Statement.RETURN_GENERATED_KEYS
            );
            ps.setInt(1, orden.getOrdenid());
            ps.setInt(2, orden.getProductoid());
            ps.setInt(3, orden.getCantidad());
            return ps;
        },keyHolder);

        Number generateId = keyHolder.getKey();
        if (generateId != null) {
            orden.setId(generateId.longValue());
        }else {
            orden.setId(0L);
        }
        return orden;
    }

    @Override
    public int delete(int id) {
        int registrosAfectados=jdbcTemplate.update("DELETE FROM detalle_orden WHERE id=?",id);
        return registrosAfectados;
    }

    @Override
    public int update(OrdenModel orden) {
        int registrosAfectados=jdbcTemplate.update(
                "UPDATE detalle_orden SET ordenid=?,productoid=?,cantidad=? WHERE id=?",
                orden.getOrdenid(),orden.getProductoid(),orden.getCantidad(),orden.getId());
        return registrosAfectados;
    }
}
