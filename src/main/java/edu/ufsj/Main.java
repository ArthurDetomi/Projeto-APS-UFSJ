package edu.ufsj;

import edu.ufsj.model.Medico;
import edu.ufsj.queries.MedicoQueries;

import java.util.List;

public class Main {
    public static void main(String[] args) {

        MedicoQueries medicoQueries = new MedicoQueries();

        List<Medico> medicos = medicoQueries.getAll();

        for (Medico medico : medicos) {
            System.out.println(medico);
        }
    }
}