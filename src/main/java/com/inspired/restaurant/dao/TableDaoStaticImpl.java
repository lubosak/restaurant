package com.inspired.restaurant.dao;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;

import com.inspired.restaurant.constant.TableLocationEnu;
import com.inspired.restaurant.dto.Table;

@Service
public class TableDaoStaticImpl implements TableDao {

    private static final List<Table> tables = Arrays.asList(
	    new Table(1, 4, TableLocationEnu.INSIDE),
	    new Table(2, 4, TableLocationEnu.INSIDE),
	    new Table(3, 4, TableLocationEnu.INSIDE),
	    new Table(4, 4, TableLocationEnu.INSIDE),
	    new Table(5, 6, TableLocationEnu.INSIDE),
	    new Table(6, 6, TableLocationEnu.INSIDE),
	    new Table(7, 6, TableLocationEnu.INSIDE),
	    new Table(8, 2, TableLocationEnu.SIDEWALK),
	    new Table(9, 2, TableLocationEnu.SIDEWALK),
	    new Table(10, 2, TableLocationEnu.SIDEWALK),
	    new Table(11, 2, TableLocationEnu.SIDEWALK),
	    new Table(12, 4, TableLocationEnu.TERRACE),
	    new Table(13, 4, TableLocationEnu.TERRACE),
	    new Table(14, 4, TableLocationEnu.TERRACE),
	    new Table(15, 8, TableLocationEnu.TERRACE)
	    );


    @Override
    public List<Table> loadTables() {
	return new ArrayList<Table>(tables);
    }

    @Override
    public List<Table> loadTables(TableLocationEnu preference) {
	if (preference == null) {
	    return loadTables();
	}
	final List<Table> preferredList = new ArrayList<Table>();
	for (Table table : tables) {
	    if (preference.equals(table.getLocation())) {
		preferredList.add(table);
	    }
	}
	return preferredList;
    }

}
