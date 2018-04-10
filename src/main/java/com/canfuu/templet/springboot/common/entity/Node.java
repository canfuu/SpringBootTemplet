package com.canfuu.templet.springboot.common.entity;

/**
 * In YunSuDesignWeb->com.yunsudesign.server.entity
 * Create in 00:42 2018/4/10
 *
 * @author canfuu
 * @version v1.0
 * @descriotion 描述
 */
public class Node<K,V> {
	K k;
	V v;

	public Node(K k, V v) {
		this.k = k;
		this.v = v;
	}

	public K getK() {
		return k;
	}

	public V getV() {
		return v;
	}

	public void setK(K k) {
		this.k = k;
	}

	public void setV(V v) {
		this.v = v;
	}
}
