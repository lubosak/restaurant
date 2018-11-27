package com.inspired.restaurant.dao;

import java.util.List;

import com.inspired.restaurant.constant.TableLocationEnu;
import com.inspired.restaurant.dto.Table;

public interface TableDao {

    public List<Table> loadTables();

    public List<Table> loadTables(TableLocationEnu preference);
}
