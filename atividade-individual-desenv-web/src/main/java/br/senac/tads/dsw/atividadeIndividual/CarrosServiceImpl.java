package br.senac.tads.dsw.atividadeIndividual;

/**
 * @author Ian S Pereira
 */
import java.util.*;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class CarrosServiceImpl implements CarrosService {

    @Autowired
    private CarrosRepository repo;
    //private Map<Integer, Carros> mapDados = new LinkedHashMap<>();

    @Override
    public List<Carros> findAll() {
        List<Carros> resultados = repo.findAll();
        List<Carros> resultadoBd = new ArrayList<>();
        for (Carros bd : resultados) {
            resultadoBd.add(new Carros(bd));
        }
        return resultadoBd;
    }

    @Override
    public List<Carros> buscar(String termoBusca, int pagina, int quantidade) {
        if (termoBusca != null && termoBusca.length() > 0) {
            Page<Carros> resultadosPage = repo.findComJpql(termoBusca, PageRequest.of(pagina, quantidade));
            List<Carros> resultadoDto = new ArrayList<>();
            for (Carros bd : resultadosPage.getContent()) {
                resultadoDto.add(new Carros(bd));
            }
            return resultadoDto;
        } else {
            Page<Carros> resultadosPage = repo.findAll(PageRequest.of(pagina, quantidade));
            List<Carros> resultadoDto = new ArrayList<>();
            for (Carros bd : resultadosPage.getContent()) {
                resultadoDto.add(new Carros(bd));
            }
            return resultadoDto;
        }
    }

    @Override
    public Carros findById(int id) {
        Optional<Carros> optCarros = repo.findById(id);
        if (optCarros.isEmpty()) {
            return null;
        }
        return new Carros(optCarros.get());
    }

//    @Override
//    public Optional<CarrosDto> findByIdComOptional(int id) {
//        return repo.findById(id).map(bd -> new CarrosDto(bd));
//    }

    @Override
    @Transactional
    public void save(Carros dadosCarro) {
        Carros bd = new Carros();
        bd.setId(dadosCarro.getId());
        bd.setModelo(dadosCarro.getModelo());
        bd.setFabricante(dadosCarro.getFabricante());
        bd.setAnoLancamento(dadosCarro.getAnoLancamento());
        repo.save(bd);
    }
}
