#!/usr/bin/env groovy

def call() {
	return ['alpha1', 'beta', 'gamma']
	//return [ 
	//	choice(name: 'select para', choices: ['alpha', 'beta', 'gamma'], description: 'show the menu'),
	//	string(name: 'ci tag', defaultValue: 'list', description: 'enter a tag')
	//]
}

