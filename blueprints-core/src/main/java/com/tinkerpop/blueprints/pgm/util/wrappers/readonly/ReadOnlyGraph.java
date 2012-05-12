package com.tinkerpop.blueprints.pgm.util.wrappers.readonly;

import com.tinkerpop.blueprints.pgm.CloseableIterable;
import com.tinkerpop.blueprints.pgm.Edge;
import com.tinkerpop.blueprints.pgm.Features;
import com.tinkerpop.blueprints.pgm.Graph;
import com.tinkerpop.blueprints.pgm.Vertex;
import com.tinkerpop.blueprints.pgm.util.StringFactory;
import com.tinkerpop.blueprints.pgm.util.wrappers.WrapperGraph;
import com.tinkerpop.blueprints.pgm.util.wrappers.readonly.util.ReadOnlyEdgeIterable;
import com.tinkerpop.blueprints.pgm.util.wrappers.readonly.util.ReadOnlyVertexIterable;

/**
 * A ReadOnlyGraph wraps a Graph and overrides the underlying graph's mutating methods.
 * In this way, a ReadOnlyGraph can only be read from, not written to.
 *
 * @author Marko A. Rodriguez (http://markorodriguez.com)
 */
public class ReadOnlyGraph<T extends Graph> implements Graph, WrapperGraph<T> {

    protected final T baseGraph;
    private final Features features;

    public ReadOnlyGraph(final T baseGraph) {
        this.baseGraph = baseGraph;
        this.features = this.baseGraph.getFeatures().copyFeatures();
        this.features.isWrapper = true;
    }

    /**
     * @throws UnsupportedOperationException
     */
    public void removeVertex(final Vertex vertex) throws UnsupportedOperationException {
        throw new UnsupportedOperationException(ReadOnlyTokens.MUTATE_ERROR_MESSAGE);
    }

    /**
     * @throws UnsupportedOperationException
     */
    public Edge addEdge(final Object id, final Vertex outVertex, final Vertex inVertex, final String label) throws UnsupportedOperationException {
        throw new UnsupportedOperationException(ReadOnlyTokens.MUTATE_ERROR_MESSAGE);
    }

    public Vertex getVertex(final Object id) {
        final Vertex vertex = this.baseGraph.getVertex(id);
        if (null == vertex)
            return null;
        else
            return new ReadOnlyVertex(vertex);
    }

    /**
     * @throws UnsupportedOperationException
     */
    public void removeEdge(final Edge edge) throws UnsupportedOperationException {
        throw new UnsupportedOperationException(ReadOnlyTokens.MUTATE_ERROR_MESSAGE);
    }

    public Iterable<Edge> getEdges() {
        return new ReadOnlyEdgeIterable(this.baseGraph.getEdges());
    }

    public CloseableIterable<Edge> getEdges(final String key, final Object value) {
        return new ReadOnlyEdgeIterable(this.baseGraph.getEdges(key, value));
    }

    public Edge getEdge(final Object id) {
        final Edge edge = this.baseGraph.getEdge(id);
        if (null == edge)
            return null;
        else
            return new ReadOnlyEdge(edge);
    }

    public Iterable<Vertex> getVertices() {
        return new ReadOnlyVertexIterable(this.baseGraph.getVertices());
    }

    public CloseableIterable<Vertex> getVertices(final String key, final Object value) {
        return new ReadOnlyVertexIterable(this.baseGraph.getVertices(key, value));
    }

    /**
     * @throws UnsupportedOperationException
     */
    public Vertex addVertex(final Object id) throws UnsupportedOperationException {
        throw new UnsupportedOperationException(ReadOnlyTokens.MUTATE_ERROR_MESSAGE);
    }

    /**
     * @throws UnsupportedOperationException
     */
    public void shutdown() throws UnsupportedOperationException {
        throw new UnsupportedOperationException(ReadOnlyTokens.MUTATE_ERROR_MESSAGE);
    }

    public String toString() {
        return StringFactory.graphString(this, this.baseGraph.toString());
    }

    @Override
    public T getBaseGraph() {
        return this.baseGraph;
    }

    public Features getFeatures() {
        return this.features;
    }

}
