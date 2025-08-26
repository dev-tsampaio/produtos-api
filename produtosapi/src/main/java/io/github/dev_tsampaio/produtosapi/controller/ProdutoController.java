package io.github.dev_tsampaio.produtosapi.controller;

import io.github.dev_tsampaio.produtosapi.model.Produto;
import io.github.dev_tsampaio.produtosapi.repository.ProdutoRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("produtos")
public class ProdutoController {

    private ProdutoRepository produtoRepository;

    public ProdutoController(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    @PostMapping
    public Produto salvar(@RequestBody Produto produto){
        System.out.println("Produto salvo com sucesso: " + produto);
        var id = UUID.randomUUID().toString();  // DEFINE CODIGO DO PRODUTO NO BANCO DE DADOS
        produto.setId(id);                      // DEFINE CODIGO DO PRODUTO NO BANCO DE DADOS
        produtoRepository.save(produto);        // DEFINE CODIGO DO PRODUTO NO BANCO DE DADOS
        return produto;
    }

    @GetMapping("{id}")
    public Produto obterPorId(@PathVariable("id") String id){
        return produtoRepository.findById(id).orElse(null);
    }

    @DeleteMapping("{id}")
    public void deletarProduto(@PathVariable("id") String id){
        produtoRepository.deleteById(id);
    }

    @PutMapping("{id}")
    public void atualizarProduto(@PathVariable("id") String id, @RequestBody Produto produto){
        produto.setId(id);
        produtoRepository.save(produto);
    }

    @GetMapping
    public List<Produto> buscar(@RequestParam("nome") String nome){
        return produtoRepository.findByNome(nome);
    }
}

