package br.senac.tads.dsw.exemplos;

/**
 * @author Ian S Pereira
 */

import jakarta.annotation.PostConstruct;
import java.util.*;
import org.springframework.stereotype.Service;

@Service
public class CarroServiceImpl implements CarroService {

    private static Integer cont = 1;

    private Map<Integer, Carro> mapDados = new LinkedHashMap<>();

    @PostConstruct
    public void init() {
        Integer id = cont++;
        mapDados.put(id, new Carro(id, "Corolla", "Toyta", 1998));
        id = cont++;
        mapDados.put(id, new Carro(id, "Gol", "WV", 1980));
        id = cont++;
        mapDados.put(id, new Carro(id, "HB20", "Hyunday", 2012));
        id = cont++;
        mapDados.put(id, new Carro(id, "Lancer", "Mitsubishi", 1973));
        id = cont++;
        mapDados.put(id, new Carro(id, "Opala", "Chevrolet", 1968));
    }

    @Override
    public List<Carro> findAll() {
        return new ArrayList<>(mapDados.values());
    }

    @Override
    public List<Carro> buscar(String termoBusca, int pagina, int quantidade) {
        List<Carro> resultadoTotal = new ArrayList<>();
        for (Carro dp : mapDados.values()) {
            if (dp.getModelo().toLowerCase().contains(termoBusca.toLowerCase())
                    || dp.getFabricante().toLowerCase().contains(termoBusca.toLowerCase())
                    ) {
                resultadoTotal.add(dp);
            }
        }
        int paginaIndex = pagina - 1;
        int offset = paginaIndex * quantidade;

        List<Carro> resultadoFinal = new ArrayList<>();
        for (int i = offset; i < offset + quantidade && i < resultadoTotal.size(); i++) {
            resultadoFinal.add(resultadoTotal.get(i));
        }
        return resultadoFinal;
    }

    @Override
    public Carro findById(int id) {
        return mapDados.get(id);
    }

    @Override
    public Optional<Carro> findByIdComOptional(int id) {
        return Optional.ofNullable(mapDados.get(id));
    }

    @Override
    public void save(Carro dados) {
        dados.setId(cont++);
        mapDados.put(dados.getId(), dados);
    }

}
