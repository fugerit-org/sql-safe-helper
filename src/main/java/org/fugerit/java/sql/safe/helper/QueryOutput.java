package org.fugerit.java.sql.safe.helper;

import lombok.Getter;
import org.fugerit.java.core.db.metadata.ColumnModel;
import org.fugerit.java.core.util.MapEntry;

import java.util.*;
import java.util.stream.Collectors;

public class QueryOutput {

    public QueryOutput() {
        this.rows = new ArrayList<>();
        this.columns = new ArrayList<>();
    }

    @Getter
    private Collection<ColumnModel> columns;

    @Getter
    private Collection<Collection<Object>> rows;

    public Collection<Collection<MapEntry<ColumnModel, Object>>> getRowsMeta() {
        List<ColumnModel> cols = new ArrayList<>( this.columns );
        return this.rows.stream().map( c -> {
            Collection<MapEntry<ColumnModel, Object>> row = new ArrayList<>();
            int count = 0;
            Iterator<Object> it = c.iterator();
            while ( it.hasNext() ) {
                row.add( new MapEntry<>( cols.get( count ), it.next() ) );
                count++;
            }
            return row;
        } ).collect( Collectors.toList() );
    }

}
