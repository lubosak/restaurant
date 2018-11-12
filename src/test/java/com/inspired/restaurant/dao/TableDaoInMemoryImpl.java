package com.inspired.restaurant.dao;

import java.util.ArrayList;
import java.util.List;

import com.inspired.restaurant.constant.TableLocationEnu;
import com.inspired.restaurant.dto.Table;

public class TableDaoInMemoryImpl implements TableDao {

    private List<Table> tables;

    public TableDaoInMemoryImpl() {
	tables = new ArrayList<Table>();
    }

    public void addTable(Table table) {
	tables.add(table);
    }

    @Override
    public List<Table> loadTables() {
	return tables;
    }

    @Override
    public List<Table> loadTables(TableLocationEnu preference) {
	List<Table> preferred = new ArrayList<>();
	for (Table table : tables) {
	    if (preference == null || preference.equals(table.getLocation())) {
		preferred.add(table);
	    }
	}
	return preferred;
    }

}
