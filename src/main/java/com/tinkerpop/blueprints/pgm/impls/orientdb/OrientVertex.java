package com.tinkerpop.blueprints.pgm.impls.orientdb;

import com.orientechnologies.orient.core.db.graph.ODatabaseGraphTx;
import com.orientechnologies.orient.core.db.graph.OGraphEdge;
import com.orientechnologies.orient.core.db.graph.OGraphVertex;
import com.tinkerpop.blueprints.pgm.Edge;
import com.tinkerpop.blueprints.pgm.Vertex;
import com.tinkerpop.blueprints.pgm.impls.StringFactory;
import com.tinkerpop.blueprints.pgm.impls.orientdb.util.OrientEdgeSequence;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * @author Luca Garulli (http://www.orientechnologies.com)
 */
public class OrientVertex extends OrientElement implements Vertex {

    public OrientVertex(final ODatabaseGraphTx databaseGraphTx) {
        super(new OGraphVertex(databaseGraphTx));
        this.raw.save();
    }

    public OrientVertex(final OGraphVertex vertex) {
        super(vertex);
    }

    public Iterable<Edge> getOutEdges() {
        return new OrientEdgeSequence(((OGraphVertex) this.raw).getOutEdges().iterator());
    }

    public Iterable<Edge> getInEdges() {
        return new OrientEdgeSequence(((OGraphVertex) this.raw).getInEdges().iterator());
    }

    public Set<String> getPropertyKeys() {
        final Set<String> set = super.getPropertyKeys();
        set.remove(OGraphVertex.FIELD_IN_EDGES);
        set.remove(OGraphVertex.FIELD_OUT_EDGES);
        return set;
    }

    public OGraphVertex getRawVertex() {
        return (OGraphVertex) this.raw;
    }

    public String toString() {
        return StringFactory.vertexString(this);
    }

}