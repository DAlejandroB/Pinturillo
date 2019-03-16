package com.edu.uptc.structure;

public class GraphWList<V, E> extends LinkedList<Vertex<V, E>> {

	private LinkedList<String> vertexL;

	public GraphWList() {
		super();
		this.vertexL = new LinkedList<>();
	}
	
	public boolean existsWay(Vertex<V, E> x, Vertex<V, E> y) {
		LinkedList<Vertex<V, E>> vertexList = new LinkedList<>();

		Node<Edge<V, E>> auxE = x.getHead();

		while (auxE != null) {
			if (auxE.getInfo().getTarget().equals(y) && !auxE.getInfo().getType().equals(EdgeType.INCIDENT)) {
				return true;
			} else if (!auxE.getInfo().getType().equals(EdgeType.INCIDENT)){
				vertexList.add(auxE.getInfo().getTarget());
			}
			auxE = auxE.getNext();
		}

		Node<Vertex<V, E>> auxV = vertexList.getHead();
		while (auxV != null) {
			if (existsWay(auxV.inf, y)) {
				return true;
			}
			auxV = auxV.getNext();
		}
		return false;
	}


	public int getMinEdges() {
		int min = 0;
		Node<Vertex<V, E>> auxV = this.getHead();
		while (auxV != null) {
			int value = auxV.getInfo().getSize();
			if (value < min) {
				min = value;
			}
			auxV = auxV.getNext();
		}
		return min;
	}

	public int getMaxEdges() {
		int max = 0;

		Node<Vertex<V, E>> auxV = this.getHead();
		while (auxV != null) {
			int value = auxV.getInfo().getSize();
			if (value > max) {
				max = value;
			}
			auxV = auxV.getNext();
		}
		return max;
	}

	public String getVmaxEdges() {
		vertexL.clear();
		int maxmax = 0;
		Node<Vertex<V, E>> auxV = this.getHead();
		while (auxV != null) {
			int value = auxV.getInfo().getSize();
			if (value > maxmax) {
				maxmax = value;
				vertexL.clear();
				vertexL.add(auxV.getInfo().getVertex().toString());
			} else if (value == maxmax) {
				vertexL.add(auxV.getInfo().getVertex().toString());
			}
			auxV = auxV.getNext();
		}
		return readList();
	}

	public String getVminEdges() {
		vertexL.clear();
		Node<Vertex<V, E>> auxV = this.getHead();
		int minmin = auxV.getInfo().getSize();
		while (auxV != null) {
			int value = auxV.getInfo().getSize();

			if (value < minmin) {
				minmin = value;
				vertexL.clear();
				vertexL.add(auxV.getInfo().getVertex().toString());
			} else if (value == minmin) {
				vertexL.add(auxV.getInfo().getVertex().toString());
			}
			auxV = auxV.getNext();
		}

		return readList();
	}

	public String getV0Edges() {
		vertexL.clear();

		Node<Vertex<V, E>> auxV = this.getHead();
		while (auxV != null) {
			int value = auxV.getInfo().getSize();

			if (value == 0) {
				vertexL.add(auxV.getInfo().getVertex().toString());
			}
			auxV = auxV.getNext();
		}

		return readList();
	}

	public String readList() {
		String name = " ";
		Node<String> aux = vertexL.getHead();
		while (aux != null) {
			name = name + aux.getInfo() + " ";
			aux = aux.getNext();
		}
		return name;
	}
	
	public boolean isGuided() {
		Node<Vertex<V, E>> v1 = this.getHead();
		while(v1!=null) {
			Node<Edge<V, E>> e1 = v1.getInfo().getHead();
			while(e1!= null) {
				if(!e1.getInfo().getType().equals(EdgeType.NO_DIRECTED)) {
					return true;
				}
				e1 = e1.getNext();
			}
			v1 = v1.getNext();
		}
		return false;
	}

}
