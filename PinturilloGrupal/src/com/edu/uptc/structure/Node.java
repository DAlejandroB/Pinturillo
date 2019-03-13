package com.edu.uptc.structure;

public class Node<T> {

	protected T inf;
	protected Node<T> next;

	public Node(T inf, Node<T> next) {
		super();
		this.inf = inf;
		this.next = next;
	}
	
	public Node(T inf) {
		super();
		this.inf = inf;
		this.next = null;
	}

	public T getInfo() {
		return inf;
	}

	public void setInfo(T inf) {
		this.inf = inf;
	}

	public Node<T> getNext() {
		return next;
	}

	public void setNext(Node<T> next) {
		this.next = next;
	}

	

}
