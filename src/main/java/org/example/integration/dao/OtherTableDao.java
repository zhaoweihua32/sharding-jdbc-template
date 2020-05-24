package org.example.integration.dao;


import org.example.integration.entity.OtherTable;

import java.util.List;

public interface OtherTableDao {

    long addOne(OtherTable table);

    List<OtherTable> getAll();

}
