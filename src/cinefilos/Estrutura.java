package cinefilos;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

/**
 * 
 * @author Eduardo Dornelles Classe realiza a leitura dos arquivos, monta os
 *         mapas e cria a estrutura do grafo.
 *
 */
public class Estrutura {

	public UndirectedLabeledGraphList g = new UndirectedLabeledGraphList();

	public ArrayList<String> lista_atores = new ArrayList<>();
	public ArrayList<String> lista_filmes = new ArrayList<>();
	public ArrayList<String> lista_relacao = new ArrayList<>();
	public HashMap<Integer, ArrayList<Integer>> mapaRelacao = new HashMap<>();
	public HashMap<String, ArrayList<String>> mapaFinal = new HashMap<>();
	public HashMap<Integer, String> mapaAtor = new HashMap<>();
	public HashMap<Integer, String> mapaFilme = new HashMap<>();

	public Estrutura() {
		leTxt("Atores.txt");
		leTxt("Filmes.txt");
		leTxt("FilmesAtores.txt");
		popularMapas();
	}

	public void leTxt(String num) {
		// criar as 3 arrays com as informações dos arquivos
		ArquivoTexto novo = new ArquivoTexto();
		novo.abrirParaLeitura(num);

		boolean res = true;

		while (res == true) {
			String linha = novo.lerLinhaArquivo();

			if (linha != null) {
				if (num.equals("Atores.txt"))
					
					lista_atores.add(linha);
				else if (num.equals("Filmes.txt"))
					
					lista_filmes.add(linha);
				else {
					lista_relacao.add(linha);
				}
			} else
				res = false;
		}

		novo.fechar();

	}

	public void popularMapas() {
		for (String atual : lista_atores) { // popula mapaAtor

			String[] aux = atual.split("[|]");
			int idAtor = Integer.parseInt(aux[0]);
			String atorStr = aux[1];
			g.addVertice(atorStr); // aproveita a iteração e já cria os vertices
			mapaAtor.put(idAtor, atorStr);

		}

		for (String atual : lista_filmes) { // popula mapaFilmes
			String[] aux = atual.split("[|]");
			int idFilme = Integer.parseInt(aux[0]);
			String filmeStr = aux[1];

			mapaRelacao.put(idFilme, new ArrayList<Integer>());
			mapaFilme.put(idFilme, filmeStr);

		}

		for (String atual : lista_relacao) { // popula mapaRelacao (filme
												// ID:listaIDator)
			String[] aux = atual.split("[|]");
			int idFilme = Integer.parseInt(aux[0]);
			int idAtor = Integer.parseInt(aux[1]);

			ArrayList<Integer> listaAux = mapaRelacao.get(idFilme);
			listaAux.add(idAtor);

		}

		CriaMapaFinal();

	}

	public void CriaMapaFinal() {
		// popula o mapaFinal(nome filme|lista de atores) através dos mapas
		// feitos acima

		Set<Map.Entry<Integer, ArrayList<Integer>>> set = mapaRelacao.entrySet();

		for (Entry<Integer, ArrayList<Integer>> me : set) {
			int filmeID = me.getKey();
			ArrayList<Integer> listIDator = me.getValue();

			ArrayList<String> nomeAtores = new ArrayList<>();
			for (int a : listIDator) {
				nomeAtores.add(mapaAtor.get(a));

			}
			mapaFinal.put(mapaFilme.get(filmeID), nomeAtores);
		}

		criaEdges();
	}

	public void criaEdges() {
		// itera sobre o mapaFinal para criar as arestas do grafo
		Set<Map.Entry<String, ArrayList<String>>> set = mapaFinal.entrySet();

		for (Entry<String, ArrayList<String>> me : set) {
			String filme = me.getKey();
			ArrayList<String> atores = me.getValue();

			for (int x = 0; x < atores.size(); x++) {

				for (int y = 0; y < atores.size(); y++) {
					if (y <= x) {
						continue;
					} else {
						g.addEdge(atores.get(x), atores.get(y), filme);
						// OBS: o metodo addEdge verifica se já não existe essa
						// ligação, caso exista ele simplesmente retorna sem
						// fazer nada
					}
				}

			}
		}

	}
}
