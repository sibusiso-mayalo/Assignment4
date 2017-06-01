JFlAGS = -g
JCC = javac

default:	Edge.class Vertex.class Algorithm.class Graph.class SimulatorOne.class ReadData.class

Edge.class:	Edge.java
	$(JCC) $(FLAG) Edge.java

Vertex.class:	Vertex.java
	$(JCC) $(FLAG) Vertex.java

Algorithm.class:	Algorithm.java
	$(JCC) $(FLAG) Algorithm.java

Graph.class:	Graph.java
	$(JCC) $(FLAG) Graph.java

SimulatorOne.class:	SimulatorOne.java
	$(JCC) $(FLAG) SimulatorOne.java

ReadData.class:	ReadData.java
	$(JCC) $(JFLAG) ReadData.java

clean:
	$(RM) *.class
