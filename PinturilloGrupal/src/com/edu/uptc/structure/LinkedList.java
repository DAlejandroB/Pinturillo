package com.edu.uptc.structure;

public class LinkedList<T> {
	private Node<T> head;
	private int size;

	public LinkedList() {
		this.head = null;
	}

	public LinkedList(Node<T> head) {
		this.head = head;
	}

	public void add(T info) {
		if (this.head == null) {
			this.head = new Node<T>(info);
		} else {
			Node<T> aux = this.head;
			while (aux.next != null) {
				aux = aux.next;
			}
			aux.next = new Node<>(info);
		}
		size++;
	}

	public Node<T> getBefore(T info) {
		Node<T> aux = this.head.next;
		Node<T> before = this.head;
		while (aux.next.getInfo() != info) {
			before = aux;
			aux = aux.next;
		}
		return before;
	}

	public Node<T> getByInfo(T info) {
		Node<T> aux = this.head;
		while (aux.next != null && (!aux.getInfo().equals(info.toString())))
			aux = aux.next;
		if (!aux.getInfo().toString().equals(info.toString()))
			aux = null;
		return aux;
	}
	
	public T getByIndex(int index) {
		Node<T> aux = this.head;
		for (int i = 0; i < index; i++) {
			aux = aux.next;
		}
		return aux.inf;
	}

	public boolean search(T inf) {
		Boolean exists = false;
		Node<T> aux = this.head;
		while (aux != null && exists == false) {
			if (aux.getInfo().toString().equals(inf.toString())) {
				exists = true;
			}
			aux = aux.next;
		}
		return exists;
	}

	public void delete(T inf) {
		if (search(inf)) {
			Boolean delete = false;
			Node<T> last = null;
			Node<T> aux = this.head;
			while (aux.next != null && delete == false) {
				if (aux.getInfo().toString().equals(inf.toString())) {
					if (last == null) {
						this.head = aux.next;
					} else {
						last.next = aux.next;
					}
					delete = true;
				}
				last = aux;
				aux = aux.next;
			}
			size--;
		}
	}

	public int getIndex(T info) {
		Node<T> aux = this.head;
		int count = 0;
		while (aux.next != null && !aux.getInfo().equals(info)) {
			aux = aux.next;
			count++;
		}
		if (!aux.getInfo().equals(info))
			count = -1;
		return count;
	}

	public void clear() {
		this.head = null;
		size = 0;
	}

	public boolean isEmpty() {
		return this.head == null;
	}

	public Node<T> getHead() {
		return head;
	}

	public void setHead(Node<T> primero) {
		this.head = primero;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

}
