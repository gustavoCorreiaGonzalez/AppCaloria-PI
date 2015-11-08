package com.example.avellb155max.appcalorias.Model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by gustavo on 07/11/15.
 */

public class GSONCategoria implements Serializable {
    String nome;
    List<GSONSubCategoria> SubCategoria;
}
