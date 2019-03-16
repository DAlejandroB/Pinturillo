package com.edu.uptc.structure;

public class Vertex<V, E> extends LinkedList<Edge<V, E>> {

	private V vertex;

	public Vertex(V vertex) {
		super();
		this.vertex = vertex;
	}

	public V getVertex() {
		return vertex;
	}

	public void setVertex(V vertex) {
		this.vertex = vertex;
	}

	@Override
	public String toString() {
		return "Vertex [vertex=" + vertex + "]";
	}
	
	

}
