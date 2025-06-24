package com.upiiz.ExamenIII.Repositories;

import com.upiiz.ExamenIII.Models.OrdenModel;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrdenRepository {
    public List<OrdenModel> findAllOrdenes();
    public OrdenModel findOrdenById(int id);
    public OrdenModel save(OrdenModel orden);
    public int delete(int id);
    public int update(OrdenModel orden);
}
