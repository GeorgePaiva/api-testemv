package com.crudmv.testecrudmv;

import com.crudmv.testecrudmv.domain.Estabelecimento;
import com.crudmv.testecrudmv.domain.Profissional;
import com.crudmv.testecrudmv.repositories.EstabelecimentoRepository;
import com.crudmv.testecrudmv.repositories.ProfissionalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;

@SpringBootApplication
public class TestecrudmvApplication implements CommandLineRunner {

    @Autowired
    private ProfissionalRepository profissionalRepository;

    @Autowired
    private EstabelecimentoRepository estabelecimentoRepository;

    public static void main(String[] args) {
        SpringApplication.run(TestecrudmvApplication.class, args);

    }

    @Override
    public void run(String... args) throws Exception {

        Profissional prof1 = new Profissional(null, "George Paiva");
        Profissional prof2 = new Profissional(null, "Jose");
        Profissional prof3 = new Profissional(null, "Maria");
        Profissional prof4 = new Profissional(null, "Francisco");

        Estabelecimento est1 = new Estabelecimento(null, "Unimed");
        Estabelecimento est2 = new Estabelecimento(null, "Clinica Hapvida");
        Estabelecimento est3 = new Estabelecimento(null, "Unimed");
        Estabelecimento est4 = new Estabelecimento(null, "Clube Saude");


        profissionalRepository.saveAll(Arrays.asList(prof1, prof2, prof3, prof4));
        estabelecimentoRepository.saveAll(Arrays.asList(est1, est2, est3, est4));



    }
}
