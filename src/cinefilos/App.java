package cinefilos;

import java.util.ArrayList;

public class App {

	public static void main(String[] args) {
		Estrutura arquivos = new Estrutura(); // passar os txts para arraylists

		UndirectedLabeledGraphList grafo = arquivos.g;
	
		System.out.println("Quantidade de atores em determinada distancia (até a maior encontrada):");
		System.out.println("Numero de Atores na distancia 0: " + grafo.numAtoresDist(0));
		System.out.println("Numero de Atores na distancia 1: " + grafo.numAtoresDist(1));
		System.out.println("Numero de Atores na distancia 2: " + grafo.numAtoresDist(2));
		System.out.println("Numero de Atores na distancia 3: " + grafo.numAtoresDist(3));
		System.out.println("Numero de Atores na distancia 4: " + grafo.numAtoresDist(4));
		System.out.println("Numero de Atores na distancia 5: " + grafo.numAtoresDist(5));
		System.out.println("Numero de Atores na distancia 6: " + grafo.numAtoresDist(6));
		System.out.println("Numero de Atores na distancia 7: " + grafo.numAtoresDist(7));
		System.out.println("Numero de Atores na distancia 8: " + grafo.numAtoresDist(8));
		
		System.out.println();
		System.out.println("Numero de Bacon de determinados atores:");
		System.out.println(
				"Nº Bacon Burt Reynolds: " + grafo.numBacon("Burt Reynolds"));
		System.out.println("Nº Bacon Tom Hanks: " + grafo.numBacon("Tom Hanks"));
		System.out.println(
				"Nº Bacon Vivean Gray: " + grafo.numBacon("Vivean Gray"));
		System.out.println(
				"Nº Bacon Robert De Niro: " + grafo.numBacon("Robert De Niro"));
		System.out.println(
				"Nº Bacon Jenna Elfman: " + grafo.numBacon("Jenna Elfman"));
		System.out.println(
				"Nº Bacon Peter McDonald: " + grafo.numBacon("Peter McDonald"));
		System.out
				.println("Nº Bacon Jim Henson: " + grafo.numBacon("Jim Henson"));
		System.out
				.println("Nº Bacon Wendy Lyon: " + grafo.numBacon("Wendy Lyon"));
		System.out.println(
				"Nº Bacon Ken Rudulph: " + grafo.numBacon("Ken Rudulph"));
		System.out.println(
				"Nº Bacon Linda Harrison: " + grafo.numBacon("Linda Harrison"));
		
		System.out.println();
		System.out.println("Distancia (graus de separação) entre dois atores quaisquer:");
		System.out.println(
				"Distancia Entre Yvan Attal e Richard Johnson: " + grafo.atoresDist("Richard Johnson", "Yvan Attal"));
		System.out.println(
				"Distancia Entre Telly Savalas e Tom Hanks: " + grafo.atoresDist("Tom Hanks", "Telly Savalas"));
		System.out.println(
				"Distancia Entre Nestor Paiva e Vivean Gray: " + grafo.atoresDist("Vivean Gray", "Nestor Paiva"));
		System.out.println(
				"Distancia Entre Arthur Byron e Robert De Niro: " + grafo.atoresDist("Arthur Byron", "Robert De Niro"));
		System.out.println(
				"Distancia Entre Jean Simmons e Bruce Vilanch: " + grafo.atoresDist("Jean Simmons", "Bruce Vilanch"));
		System.out.println("Distancia Entre Louise Lasser e Cynthia Rhodes: "
				+ grafo.atoresDist("Louise Lasser", "Cynthia Rhodes"));
		System.out.println(
				"Distancia Entre Boyan Milushev e Henry Fonda: " + grafo.atoresDist("Boyan Milushev", "Henry Fonda"));
		System.out.println(
				"Distancia Entre Olivia Negron e Roy Roberts: " + grafo.atoresDist("Olivia Negron", "Roy Roberts"));
		System.out.println(
				"Distancia Entre Laurie Metcalf e Arthur Lowe: " + grafo.atoresDist("Laurie Metcalf", "Arthur Lowe"));
		System.out.println(
				"Distancia Entre Alan Howard e John Belushi: " + grafo.atoresDist("Alan Howard", "John Belushi"));
		
		System.out.println();
		System.out.println("Nome dos atores com maior numero de bacon:");
		System.out.println(grafo.maiorNbacon());

	}

}
