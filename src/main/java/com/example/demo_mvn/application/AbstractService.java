package com.example.demo_mvn.application;


import org.springframework.stereotype.Service;

@Service
public abstract class AbstractService<T, RT> {

	private void init(T request, RT response) {
	}

	private void doProcess() {
		preprocess();
		process();
		postprocess();
	}

	public abstract void preprocess();

	public abstract void process();

	public abstract void postprocess();
}
