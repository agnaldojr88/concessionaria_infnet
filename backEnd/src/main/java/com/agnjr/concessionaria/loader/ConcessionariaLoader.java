package com.agnjr.concessionaria.loader;



import com.agnjr.concessionaria.model.*;
import com.agnjr.concessionaria.service.*;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.*;
import java.math.BigDecimal;


@Slf4j
@Component
public class ConcessionariaLoader {

    /*
    Classe destinada a fazer a leitura dos arquivos txt que estão alocados dentro da pasta /src/resources/
    -> Arquivo: categorias
    -> Arquivo: carros
    -> Arquivo: motos
    -> Arquivo: caminhoes

     */

    @Autowired
    private CategoriaService categoriaService;

    @Autowired
    private CarroService carroService;

    @Autowired
    private CaminhaoService caminhaoService;

    @Autowired
    private MotoService motoService;

    @PostConstruct
    public void loadData() {
        try {
            log.info("Loading: loadCategorias...");
            loadCategorias();

            log.info("Loading: loadCarros...");
            loadCarros();

            log.info("Loading: loadCaminhoes...");
            loadCaminhoes();

            log.info("Loading: loadMotos...");
            loadMotos();

            log.info("Loading: finished...");
        } catch (IOException e) {
            e.printStackTrace();

        }
    }

    private void loadCategorias() throws IOException {
        InputStream is = getClass().getClassLoader().getResourceAsStream("categorias.txt");
        if (is == null) {

            log.info("Arquivo categorias.txt não encontrado, verifiquee em 'src/resources/ a existência do arquivo!");
            return;
        }
        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        String line;
        while ((line = br.readLine()) != null) {
            String[] parts = line.split(",");
            if (parts.length >= 3) {
                String nome = parts[1].trim();
                String descricao = parts[2].trim();
                Categoria categoria = new Categoria(nome, descricao);
                categoriaService.incluir(categoria);
                System.out.println(categoria.toString());
            }
        }
        br.close();
    }

    private void loadCarros() throws IOException {
        InputStream is = getClass().getClassLoader().getResourceAsStream("carros.txt");
        if (is == null) {
            log.info("Arquivo carros.txt não encontrado, verifiquee em 'src/resources/ a existência do arquivo!");
            return;
        }
        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        String line;
        while ((line = br.readLine()) != null) {
            String[] parts = line.split(",");

            if (parts.length >= 9) {
                // id, nome, km, valor, fabricante, cor, rebaixado, leilao, dePasseio, categoria_id
                String nome = parts[1].trim();
                Double km = Double.parseDouble(parts[2].trim());
                BigDecimal valor = new BigDecimal(parts[3].trim());
                String fabricante = parts[4].trim();
                String cor = parts[5].trim();
                Boolean rebaixado = Boolean.parseBoolean(parts[6].trim());
                Boolean leilao = Boolean.parseBoolean(parts[7].trim());
                Boolean dePasseio = Boolean.parseBoolean(parts[8].trim());
                Long categoriaId = Long.parseLong(parts[9].trim());

                Categoria categoria = categoriaService.obterPorId(categoriaId);
                if (categoria == null) {
                    log.info("Categoria com ID {} não encontrada para o carro {}", categoriaId, nome);
                    continue;
                }

                Carro carro = new Carro(nome, km, valor, fabricante, cor, categoria, rebaixado, leilao, dePasseio);
                carroService.incluir(carro);
                System.out.println(carro.toString());
            }
        }
        br.close();
    }

    private void loadCaminhoes() throws IOException {
        InputStream is = getClass().getClassLoader().getResourceAsStream("caminhoes.txt");
        if (is == null) {
            log.info("Arquivo caminhoes.txt não encontrado, verifiquee em 'src/resources/ a existência do arquivo!");
            return;
        }
        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        String line;
        while ((line = br.readLine()) != null) {
            String[] parts = line.split(",");
            if (parts.length >= 9) {

                // id, nome, km, valor, fabricante, cor, rodado, tipoCabine, categoria_id
                String nome = parts[1].trim();
                Double km = Double.parseDouble(parts[2].trim());
                BigDecimal valor = new BigDecimal(parts[3].trim());
                String fabricante = parts[4].trim();
                String cor = parts[5].trim();
                Integer rodado = Integer.parseInt(parts[6].trim());
                Integer tipoCabine = Integer.parseInt(parts[7].trim());
                Long categoriaId = Long.parseLong(parts[8].trim());

                Categoria categoria = categoriaService.obterPorId(categoriaId);
                if (categoria == null) {
                    log.info("Categoria com ID {} não encontrada para o caminhão {}", categoriaId, nome);
                    continue;
                }

                Caminhao caminhao = new Caminhao(nome, km, valor, fabricante, cor, categoria, rodado, tipoCabine);
                caminhaoService.incluir(caminhao);
                System.out.println(caminhao.toString());
            }
        }
        br.close();
    }

    private void loadMotos() throws IOException {
        InputStream is = getClass().getClassLoader().getResourceAsStream("motos.txt");
        if (is == null) {
            log.info("Arquivo motos.txt não encontrado, verifiquee em 'src/resources/ a existência do arquivo!");
            return;
        }
        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        String line;
        while ((line = br.readLine()) != null) {
            String[] parts = line.split(",");
            if (parts.length >= 8) {
                //id, nome, km, valor, fabricante, cor, cilindradas, categoria_id
                String nome = parts[1].trim();
                Double km = Double.parseDouble(parts[2].trim());
                BigDecimal valor = new BigDecimal(parts[3].trim());
                String fabricante = parts[4].trim();
                String cor = parts[5].trim();
                Integer cilindradas = Integer.parseInt(parts[6].trim());
                Long categoriaId = Long.parseLong(parts[7].trim());

                Categoria categoria = categoriaService.obterPorId(categoriaId);
                if (categoria == null) {
                    log.info("Categoria com ID {} não encontrada para a Moto {}", categoriaId, nome);
                    continue;
                }

                Moto moto = new Moto(nome, km, valor, fabricante, cor, categoria, cilindradas);
                motoService.incluir(moto);
                System.out.println(moto.toString());
            }
        }
        br.close();
    }



}
