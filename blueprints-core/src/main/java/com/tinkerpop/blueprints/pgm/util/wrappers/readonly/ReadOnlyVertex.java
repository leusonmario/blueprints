package com.tinkerpop.blueprints.pgm.util.wrappers.readonly;

import com.tinkerpop.blueprints.pgm.Edge;
import com.tinkerpop.blueprints.pgm.Query;
import com.tinkerpop.blueprints.pgm.Vertex;
import com.tinkerpop.blueprints.pgm.util.DefaultQuery;
import com.tinkerpop.blueprints.pgm.util.wrappers.readonly.util.ReadOnlyEdgeIterable;

/**
 * @author Marko A. Rodriguez (http://markorodriguez.com)
 */
public class ReadOnlyVertex extends ReadOnlyElement implements Vertex {

    public ReadOnlyVertex(final Vertex rawVertex) {
        super(rawVertex);
    }

    public Iterable<Edge> getInEdges(final String... labels) {
        return new ReadOnlyEdgeIterable(((Vertex) this.rawElement).getInEdges(labels));
    }

    public Iterable<Edge> getOutEdges(final String... labels) {
        return new ReadOnlyEdgeIterable(((Vertex) this.rawElement).getOutEdges(labels));
    }

    public Query query() {
        return new DefaultQuery(this);
    }
}
