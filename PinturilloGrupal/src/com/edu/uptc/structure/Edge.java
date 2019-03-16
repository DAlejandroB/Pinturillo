package com.edu.uptc.structure;

public class Edge<V, E> {

	private Vertex<V, E> target;
	private E label;
	private EdgeType type;

	public Edge(Vertex<V, E> target, E label, EdgeType type) {
		super();
		this.target = target;
		this.label = label;
		this.type = type;
	}

	public Vertex<V, E> getTarget() {
		return target;
	}

	public void setTarget(Vertex<V, E> target) {
		this.target = target;
	}

	public E getLabel() {
		return label;
	}

	public void setLabel(E label) {
		this.label = label;
	}

	public EdgeType getType() {
		return type;
	}

	public void setType(EdgeType type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return "Edge [target=" + target + ", label=" + label + ", type=" + type + "]";
	}

}
