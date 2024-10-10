package org.fugerit.java.sql.safe.helper;

import lombok.Getter;
import org.fugerit.java.core.db.metadata.ColumnModel;
import org.fugerit.java.core.util.MapEntry;

import java.util.*;
import java.util.stream.Collectors;

@Getter
public class QueryOutput {

    public QueryOutput() {
        this.rows = new ArrayList<>();
        this.columns = new ArrayList<>();
    }

    private final Collection<ColumnModel> columns;

    private final Collection<Collection<Object>> rows;

    public Collection<Collection<MapEntry<ColumnModel, Object>>> getRowsMeta() {
        List<ColumnModel> cols = new ArrayList<>( this.columns );
        return this.rows.stream().map( c -> {
            Collection<MapEntry<ColumnModel, Object>> row = new ArrayList<>();
            Iterator<Object> it = c.iterator();
            for ( int k = 0; it.hasNext(); k++ ) {
                row.add( new MapEntry<>( cols.get( k ), it.next() ) );
            }
            return row;
        } ).collect( Collectors.toList() );
    }

}
