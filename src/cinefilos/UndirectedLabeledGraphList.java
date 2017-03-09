package cinefilos;

import java.util.*;

public class UndirectedLabeledGraphList {
	private class Edge {
		private Vertice v;
		private String filme; // weight

		public Edge() {
			v = null;

		}

		public Edge(Vertice vert, String weight) {
			v = vert;
			filme = weight;
		}

		public Vertice getVertice() {
			return v;
		}

		public void setVertice(Vertice elem) {
			this.v = elem;
		}

		public String getWeight() {
			return filme;
		}

		public void setWeight(String weight) {
			this.filme = weight;
		}
	}

	private class Vertice {
		private String item;
		public boolean Visitado = false;
		private ArrayList<Edge> lst = new ArrayList<Edge>();

		public Vertice(String item) {
			this.item = item;
		}

		public String getItem() {
			return this.item;
		}

		public void setItem(String item) {
			this.item = item;
		}

		public boolean equals(Object o) {
			if (o instanceof Vertice) {
				return ((Vertice) o).getItem().equals(this.getItem());
			}
			return false;
		}

		private Edge searchEdgeRef(Vertice v, String w) {
			Edge res = null;
			int i;

			for (i = 0; ((i < lst.size()) && !lst.get(i).getVertice().equals(v)); i++)
				;

			if (i < lst.size())
				res = lst.get(i);

			return res;
		}

		public void addAdjacent(Vertice v, String w) {
			Edge eAux = searchEdgeRef(v, w);
			if (eAux == null) {
				// se nao existe ainda, entao adiciona a lista
				Edge e = new Edge(v, w);
				lst.add(e);
			} else {
				// se ja existe, entao apenas atualiza o peso
				eAux.setWeight(w);
			}
		}

		public ArrayList<Edge> getAdjacents() {
			return lst;
		}

		public Edge getAdjacent(int i) {
			Edge res = null;
			if ((i >= 0) && (i < lst.size()))
				res = lst.get(i);
			return res;
		}

		public int getDegree() {
			return lst.size();
		}
	}

	private ArrayList<Vertice> vert;

	public UndirectedLabeledGraphList() {
		vert = new ArrayList<Vertice>();
	}

	private Vertice searchVerticeRef(String item) {
		Vertice res = null;
		int i;

		for (i = 0; ((i < vert.size()) && !vert.get(i).getItem().equals(item)); i++)
			;

		if (i < vert.size())
			res = vert.get(i);

		return res;
	}

	public void addVertice(String item) {
		if (searchVerticeRef(item) == null) {
			Vertice novo = new Vertice(item);
			vert.add(novo);
		}
	}

	public void addEdge(String strOrig, String strDest, String weight) {
		Vertice vAux1 = searchVerticeRef(strOrig);
		Vertice vAux2 = searchVerticeRef(strDest);

		if (vAux1 == null)
			throw new IllegalArgumentException("Aresta origem invalida: " + strOrig);
		else if (vAux2 == null)
			throw new IllegalArgumentException("Aresta destino invalida: " + strDest);
		else if (vAux1.lst.contains(vAux2)) {
			return;
		} else {

			vAux1.addAdjacent(vAux2, weight);
			vAux2.addAdjacent(vAux1, weight);
		}
	}

	public ArrayList<String> maiorNbacon() {
		ArrayList<String> nomes = new ArrayList<>();
		Vertice origem = searchVerticeRef("Kevin Bacon");
		clearVisitados();
		Queue<Vertice> listaAux = new LinkedList<>();
		Queue<Vertice> listaAtual = new LinkedList<>();
		listaAtual.add(origem);

		origem.Visitado = true;
		boolean res = true;
		while (res) {

			int cont = 0;
			for (Vertice v : listaAtual) {
				v.Visitado = true;
				for (Edge adj : v.lst) {
					if (!adj.getVertice().Visitado) {
						listaAux.add(adj.getVertice());
						cont++;
						adj.getVertice().Visitado = true;
					}

				}

			}
			if (cont == 0) {
				for (Vertice v : listaAtual) {
					nomes.add(v.item);
				}

				res = false;
			}

			listaAtual = listaAux;
			listaAux = new LinkedList<>();

		}

		clearVisitados();

		return nomes;
	}

	public int numAtoresDist(int distancia) {

		int atual = 0;
		if (distancia < 0) {
			return -1;
		}
		if (distancia == 0) {
			return 1;
		}

		Vertice origem = searchVerticeRef("Kevin Bacon");
		clearVisitados();

		Queue<Vertice> listaAtual = new LinkedList<>();
		Queue<Vertice> listaAux = new LinkedList<>();

		listaAtual.add(origem);
		origem.Visitado = true;

		while (!listaAtual.isEmpty()) {
			if (!listaAtual.contains(origem)) {
				atual++;
			}

			if (atual == distancia) {

				return listaAtual.size();

			}

			for (Vertice v : listaAtual) {
				v.Visitado = true;
				for (Edge adj : v.lst) {
					if (!adj.getVertice().Visitado)
						listaAux.add(adj.getVertice());
					adj.getVertice().Visitado = true;
				}

			}

			listaAtual = listaAux;
			listaAux = new LinkedList<>();

		}

		clearVisitados();
		return -1;
	}

	public int atoresDist(String ator1, String ator2) {
		//devolver distancia entre dois atores quaisquer
		int distancia = 0;

		if (ator1.equals(ator2)) {
			return 0;
		}

		Vertice origem = searchVerticeRef(ator1);
		Vertice destino = searchVerticeRef(ator2);
		clearVisitados();

		List<Vertice> listaAtual = new LinkedList<>();
		List<Vertice> listaAux = new LinkedList<>();

		listaAtual.add(origem);
		origem.Visitado = true;

		while (!listaAtual.isEmpty()) {
			if (!listaAtual.contains(origem)) {
				distancia++;
			}

			if (listaAtual.contains(destino)) {

				return distancia;

			}

			for (Vertice v : listaAtual) {
				v.Visitado = true;
				for (Edge adj : v.lst) {
					if (!adj.getVertice().Visitado)
						listaAux.add(adj.getVertice());
					adj.getVertice().Visitado = true;
				}

			}
			listaAtual = listaAux;
			listaAux = new LinkedList<>();

		}

		clearVisitados();

		return -1;
	}

	public int numBacon(String a) {

		int bacon = 0;
		Vertice destino = searchVerticeRef("Kevin Bacon");
		Vertice origem = searchVerticeRef(a);
		if (origem == destino) {
			return 0;
		}

		clearVisitados();
		Queue<Vertice> listaAtual = new LinkedList<>();
		Queue<Vertice> listaAux = new LinkedList<>();

		listaAtual.add(origem);

		origem.Visitado = true;
		while (!listaAtual.isEmpty()) {

			bacon++;
			if (listaAtual.contains(destino)) {
				clearVisitados();
				return bacon - 1;
			} else {
				for (Vertice v : listaAtual) {
					v.Visitado = true;
					for (Edge adj : v.lst) {
						if (!adj.getVertice().Visitado)
							listaAux.add(adj.getVertice());
					}

				}

			}
			listaAtual = new LinkedList<>();
			listaAtual = listaAux;
			listaAux = new LinkedList<>();
		}

		clearVisitados();
		return -1;

	}

	private void clearVisitados() { // limpar os visitados
		for (Vertice v : vert) {
			v.Visitado = false;
		}
	}

}
