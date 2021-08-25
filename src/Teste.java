import java.math.BigInteger;
import java.util.*;
import java.util.function.Consumer;

/*

Um array é uma estrutura de dados e serve para guardar elementos (valores primitivos ou referências)
Arrays usam colchetes ([]) sintaticamente
Arrays têm um tamanho fixo!
Um array também é um objeto!
Arrays são zero-based (o primeiro elemento se encontra na posição 0)
Um array é sempre inicializado com os valores padrões.
Ao acessar uma posição inválida recebemos a exceção ArrayIndexOutOfBoundException
Arrays possuem um atributo length para saber o tamanho
A forma literal de criar uma Array, com o uso de chaves {}.


Nessa aula começamos a falar sobre lista e conhecemos a classe java.util.ArrayList.
que a classe java.util.ArrayList encapsula o uso do array e oferece vários métodos de mais alto nível
que uma lista guarda referencias
como usar métodos size, get, remove
como usar o foreach para iterar a ArrayList
que os generics parametrizam classes
que no caso da ArrayList podemos definir o tipo dos elementos através de generics

o java.util.Vector, que é uma ArrayList thread safe
a interface java.util.Collection que é a interface de todas as coleções
as listas são sequencias que aceitam elementos duplicados
os conjuntos (java.util.Set) também são coleções, mas não aceitam duplicados nem são listas

para cada primitivo existe uma classe chamada Wrapper
para guardar um primitivo numa coleção é preciso criar um objeto que embrulha o valor
a criação do objeto Wrapper é chamada de autoboxing
a retirada do valor primitivo do objeto Wrapper é chamada de unboxing
autoboxing e unboxing acontecem automaticamente.
as classes wrapper possuem vários métodos auxiliares, por exemplo para o parsing
todas as classes wrappers que representam um valor numérico possuem a classe java.lang.Number como mãe


para ordenar uma lista é preciso definir um critério de ordenação
há duas formas de definir esse critério
pela interface Comparator
pela interface Comparable (ordem natural)
o algoritmo de ordenação já foi implementado
na lista no método sort
na classe Collections pelo método sort
a classe Collections é uma fachada com vários métodos auxiliares para trabalhar com as coleções, principalmente listas


 */
public class Teste
{

    public static void main(String[] args)
    {

        System.out.println("Demonstra parâmetros passados na execução");
        for ( int i = 0; i < args.length; i++)
        {
            System.out.println("parâmetro " + i + " : " + args[i]);
        }

        aula01();

        aula02();

        aula03();

        aula04();

        aula05();

        aula06();

        aula07();

        aula07b();

        aula08();

        aula08b();

    }

    private static void aula01()
    {

        System.out.println(" *** AULA 01 ****************************");

        System.out.println("Arrays - são objetos - inicializado com valores padrão - inicia com índice 0");

        System.out.println("Array Por Valor (tipo primitivo)");

        int[] idades = new int[5];
        idades[0] = 25;
        idades[1] = 90;
        idades[2] = 42;
        idades[3] = 32;
        idades[4] = 18;

        int idade = idades[3];
        System.out.println("idade selecionada: " + idade);
        System.out.println("tamanho do array: " + idades.length);

        int valores[] = new int[100];

        for (int i=0; i < valores.length; i++)
        {
            valores[i] = i * 15;
            System.out.println("valor  " + i + ": " + valores[i]);
        }

        System.out.println("Array Por Referência (Classes) > valor padrão: null");

        ContaCorrente[] contas = new ContaCorrente[5];

        ContaCorrente cc1 = new ContaCorrente(0144, 665584);
        contas[0] = cc1;

        ContaCorrente cc2 = new ContaCorrente(0144, 106854);
        contas[1] = cc2;

        System.out.println("agência e conta: " + contas[0].getAgencia() + " - " + contas[0].getNumero());

        // todas as referencias apontam para o mesmo objeto inicializado na memória
        ContaCorrente ref = contas[1];
        System.out.println(contas[1].getNumero());
        System.out.println(cc2.getNumero());
        System.out.println(ref.getNumero());

        System.out.println("forma literal de declaração de arrays");
        int[] refs = {1,2,3,4,5};
        ContaPoupanca[] contasPoupanca = {new ContaPoupanca(1, 2), new ContaPoupanca(1, 3)};
        System.out.println(refs[4]);
        System.out.println(contasPoupanca[1]);

    }

    private static void aula02()
    {

        System.out.println(" *** AULA 02 ****************************");

        Conta[] contas = new Conta[5];

        ContaCorrente cc = new ContaCorrente(0144, 665584);
        contas[0] = cc;

        ContaPoupanca cp = new ContaPoupanca(0144, 106854);
        contas[1] = cp;

        Conta refcc = contas[0];
        Conta refcp = contas[1];

        System.out.println("conta corrente: " + refcc.getNumero());
        System.out.println("conta poupanca: " + refcp.getNumero());

        // cast de tipos diferentes gerando erro
        // ContaCorrente refTeste = (ContaCorrente) contas[1];

        // cast de tipos corretos
        ContaCorrente refTeste = (ContaCorrente) contas[0];
        System.out.println("conta corrente com cast: " + refTeste.getNumero());

        // novo array com tipo 'Object'
        Object[] objetos = new Object[5];

        Cliente cli = new Cliente();
        cli.setNome("Anderson Rocha");

        objetos[0] = cc;
        objetos[1] = cp;
        objetos[2] = cli;

        int numero = 3;
        double valor = numero; // cast implícito

        double valor2 = 3.56;
        int numero2 = (int) valor2; // cast explicito é exigido pelo compilador

        ContaCorrente cc1 = new ContaCorrente(22, 33);
        Conta conta1 = cc1; // cast implicito

        ContaCorrente cc2 = new ContaCorrente(22, 33);
        Conta conta2 = (Conta) cc2; // cast explícito mas desnecessário

    }

    private static void aula03()
    {

        System.out.println(" *** AULA 03 ****************************");

        GuardadorContas guardador = new GuardadorContas();

        Conta cc1 = new ContaCorrente(1111, 11111111);
        guardador.adiciona(cc1);

        Conta cc2 = new ContaPoupanca(2222, 22222222);
        guardador.adiciona(cc2);

        int tamanho = guardador.getQuantidadeElementos();
        System.out.println("tamanho: " + tamanho);

        Conta ref1 = guardador.getReferencia(0);
        System.out.println("conta 1: " + ref1.getNumero());

        ContaPoupanca ref2 = (ContaPoupanca) guardador.getReferencia(1);
        System.out.println("conta 2: " + ref2.getNumero());

        System.out.println("ARRAY LIST");

        Conta cc3 = new ContaCorrente(3333, 33333333);
        Conta cc4 = new ContaPoupanca(3333, 44444444);

        ArrayList lista = new ArrayList();
        lista.add(cc1);
        lista.add(cc2);
        lista.add(cc3);
        lista.add(cc4);
        System.out.println("tamanho: " + lista.size());

        ContaCorrente ref = (ContaCorrente) lista.get(0);
        System.out.println("conta 1: " + ref.getNumero());

        System.out.println("exclui elemento 0");
        lista.remove(0);
        ref = (ContaCorrente) lista.get(1);
        System.out.println("tamanho: " + lista.size());
        System.out.println("conta 2: " + ref.getNumero());

        System.out.println("iteração padrão");
        for (int i = 0; i < lista.size(); i++)
        {
            Object oRef = lista.get(i);
            System.out.println(oRef); // utiliza o método 'toString' da classe
        }

        System.out.println("iteração entre objetos");
        for (Object o : lista)
        {
            System.out.println(o); // utiliza o método 'toString' da classe
        }


        System.out.println("ArrayList com Generics");
        ArrayList<Conta> listaGenerics = new ArrayList<Conta>();
        listaGenerics.add(cc1);
        listaGenerics.add(cc2);
        listaGenerics.add(cc3);
        listaGenerics.add(cc4);

        Conta refA = listaGenerics.get(0);
        System.out.println("conta 1: " + refA.getNumero());

        for (Conta cc : listaGenerics)
        {
            System.out.println(cc.toString()); // utiliza o método 'toString' da classe
        }

        // é possível criar uma lista e definir a capacidade inicial do arrayList
        ArrayList listaUF = new ArrayList(26); // capacidade inicial

        // criar uma lista a partir de outra
        ArrayList listaA = new ArrayList(26); // capacidade inicial
        lista.add("RJ");
        lista.add("SP");
        // outros estados
        ArrayList listaB = new ArrayList(listaA); // criando baseado na primeira lista

    }

    private static void aula04()
    {

        System.out.println(" *** AULA 04 ****************************");
        ArrayList<Conta> lista = new ArrayList<Conta>();

        Conta cc1 = new ContaCorrente(1, 111);
        lista.add(cc1);

        Conta cc2 = new ContaCorrente(2, 222);
        lista.add(cc2);

        for (Conta co : lista)
        {
            System.out.println(co); // utiliza o método 'toString' da classe
        }

        // contains faz a pesquisa pela referência e não pelo conteúdo
        // para que faça a pesquisa pelo 'conteúdo', a classe precisa ter o método 'equals' implementado
        boolean existe = lista.contains(cc2);
        System.out.println("A - conta cc2 existe: " + existe);

        Conta cc3 = new ContaCorrente(2, 222);
        existe = lista.contains(cc3);
        System.out.println("B - conta cc3 existe: " + existe + " - metodo contains com equal implementado."); // existe porque utilizou o método 'equals' para fazer a comparação, além de testar pela referência

        // pesquisando somente pela referência, não encontra
        for (Conta r1 : lista)
        {
            if (r1 == cc3)
            {
                System.out.println("C - cc3 existe >>>> não será executado");
            }
        }

        // pesquisando com 'equals', testando os valores do objeto e não a referência
        for (Conta r1 : lista)
        {
            if (r1.equals(cc3))
            {
                System.out.println("D - cc3 existe >>> tem o mesmo número de agencia e conta");
            }
        }

        System.out.println("por padrão o contains só testa referências, mas se for implementado o método 'equals' na classe, o teste também irá utilizar o método equals, que testa o conteúdo do objeto");

        System.out.println("O arrayList perde bastante a performance quando temos exclusões na lista.");

        System.out.println("*** linkedList");
        System.out.println("No linkedList cada item da lista conhece o próximo e o anterior, facilitando a inclusão/exclusão, mas acesso direto por índice é mais custoso.");

        // declara a interface' List e inicializa o objeto com LinkedList
        // lista duplamente encadeada.
        // List<Conta> lista2 = new ArrayList<>();
        List<Conta> lista2 = new LinkedList<Conta>();

        Conta l1 = new ContaCorrente(1, 111);
        lista2.add(l1);

        Conta l2 = new ContaCorrente(2, 222);
        lista2.add(l2);

        for (Conta l : lista2)
        {
            System.out.println(l);
        }

        // copiar array para lista
        System.out.println("*** Copiando um array para um ArrayList");
        String[] opcoes = new String[10];
        opcoes[0] = "Anderson";
        opcoes[1] = "Hiriane";
        opcoes[2] = "Philipe";
        opcoes[3] = "Nicolle";
        opcoes[4] = "Sabrina";
        opcoes[5] = "Heitor";
        opcoes[6] = "Luck";

        List<String> listaOpcoes = Arrays.asList(opcoes);

        listaOpcoes.forEach(new Consumer<String>()
        {
            @Override
            public void accept(String s)
            {
                System.out.println(s);
            }
        });

        listaOpcoes.forEach(s -> System.out.println(s));


    }

    private static void aula05()
    {

        System.out.println(" *** AULA 05 ****************************");
        List<Conta> lista = new Vector<>();

        Conta cc1 = new ContaCorrente(1, 111);
        lista.add(cc1);

        Conta cc2 = new ContaCorrente(2, 222);
        lista.add(cc2);

        for (Conta co : lista)
        {
            System.out.println(co); // utiliza o método 'toString' da classe
        }

        System.out.println("a classe Vector também utiliza array e é preparada para ser manipulada em programas multitreading.... (thread safe)");

        // Collection            (interface)
        //   List                (interface)
        //      ArrayList
        //      LinkedList
        //      Vector
        //   Set                 (interface)
        //      HashSet

        System.out.println("Exemplo de utilização de SET - ao contrário do list, as classes que implementam Set, não aceitam duplicidade");
        Set<Conta> listaSet = new HashSet<>();
        listaSet.add(cc1);
        listaSet.add(cc1); // ao tentar incluir um registro que já existe, o mesmo não foi incluído
        listaSet.add(cc1);
        listaSet.add(cc2);
        listaSet.add(cc2);
        listaSet.add(cc2);
        listaSet.add(cc2);
        for (Conta c : listaSet)
        {
            System.out.println(c);
        }

    }

    private static void aula06()
    {

        System.out.println(" *** AULA 06 ****************************");

        // tipo primitivo (armazena o valor real)
        int[] idades = new int[5];

        // tipo objeto (armazena a referência)
        Integer[] numeros = new Integer[10];
        String[]  nomes   = new String[10];

        int     idadePrimitivo = 29;
        Integer idadeObjeto    = 30;
        List<Integer> num = new ArrayList<>();
        num.add(idadePrimitivo); // AutoBoxing > como as listas só trabalham com referência, o java faz a conversão automática internamente do tipo int para Integer
        num.add(idadeObjeto);

        // transformação de primitivo para objeto é automática nas versões novas do java

        // tipo primitivo               Objeto (Wrapper - classes que representam cada tipo primitivo, para ser utilizada em coleções)
        // int      >>   Autoboxing  >> java.lang.Integer
        // double   <<   Unboxing    << java.lang.Double

        Integer idadeRef1 = Integer.valueOf(200);
        Integer idadeRef2 = new Integer(500);

        int valor1 = idadeRef1.intValue();

        String s = "900";
        Integer num1 = Integer.valueOf(s);
        System.out.println("número String: "  + s);
        System.out.println("número Integer: " + num1);

        int num2 = Integer.parseInt(s);
        System.out.println("número int: " + num2);

        System.out.println("INT");
        System.out.println("minimo: " + Integer.MIN_VALUE);
        System.out.println("máximo: " + Integer.MAX_VALUE);
        System.out.println("bytes: "  + Integer.BYTES);

        System.out.println("DOUBLE");
        System.out.println("minimo: " + Double.MIN_VALUE);
        System.out.println("máximo: " + Double.MAX_VALUE);
        System.out.println("bytes: "  + Double.BYTES);

        /*
        realmente incrementa o valor inteiro, mesmo sendo uma referência. O que acontece por baixo dos panos é um autoboxing / unboxing na linha
        que incrementa a variável (ref++). Você pode imaginar que essa linha será substituída por três novas:
        funciona por causa do boxing e unboxing automático das classes wrapper
        */
        Integer ref = Integer.valueOf("3");
        ref++;
        System.out.println(ref);

        String texto  = "42";
        int conversao = Integer.valueOf(texto);
        conversao     = Integer.valueOf(texto);

        // autoboxing
        Double dref1 = 3.2;
        Double dref2 = Double.valueOf(3.2);

        // unboxing
        System.out.println(dref1.doubleValue());
        System.out.println(dref2.doubleValue());

        Boolean bol = Boolean.TRUE;
        bol = false;
        System.out.println(bol.booleanValue());

        // a classe Number é a mãe das classes Double, Float, Long, Integer, Short, Byte
        List<Number> lista = new ArrayList<>();
        lista.add(10);
        lista.add(32.6);
        lista.add(25.6f);
        lista.add(-25);
        lista.forEach(n -> System.out.println(n));

        /*

        Ele tem dois bytes, igual o tipo short mas não usa o primeiro bit para guardar o sinal. Em outras palavras,
        o char só guarda números positivos. Isso significa que o char consegue guardar valores entre 0 e 65536 (2^16).
         */
        Character letra1 = new Character('A');
        char letra2 = 'B';
        System.out.println(letra1);
        System.out.println(letra2);

        // List referencias = new ArrayList();
        List<Number> referencias = new ArrayList();
        referencias.add(Double.valueOf(30.9));
        referencias.add(Integer.valueOf(10));
        referencias.add(Float.valueOf(13.4f));
        referencias.forEach(n -> System.out.println(n));

    }

    private static void aula07()
    {

        System.out.println(" *** AULA 07 ****************************");

        Conta cc1 = new ContaCorrente(22, 33);
        cc1.deposita(333.0);

        Conta cc2 = new ContaPoupanca(22, 44);
        cc2.deposita(444.0);

        Conta cc3 = new ContaCorrente(22, 11);
        cc3.deposita(111.0);

        Conta cc4 = new ContaPoupanca(22, 22);
        cc4.deposita(222.0);

        List<Conta> lista = new ArrayList<>();
        lista.add(cc1);
        lista.add(cc2);
        lista.add(cc3);
        lista.add(cc4);

        System.out.println("ordenação padrão");
        for (Conta conta: lista)
        {
            System.out.println(conta);
        }

        System.out.println("ordenação em ordem de número da conta");
        NumeroDaContaComparator comparator = new NumeroDaContaComparator();
        lista.sort(comparator);
        for (Conta conta: lista)
        {
            System.out.println(conta);
        }

    }

    private static void aula07b()
    {

        Conta cc1 = new ContaCorrente(22, 33);
        Cliente clienteCC1 = new Cliente();
        clienteCC1.setNome("Nico");
        cc1.setTitular(clienteCC1);
        cc1.deposita(333.0);

        Conta cc2 = new ContaPoupanca(22, 44);
        Cliente clienteCC2 = new Cliente();
        clienteCC2.setNome("Guilherme");
        cc2.setTitular(clienteCC2);
        cc2.deposita(444.0);

        Conta cc3 = new ContaCorrente(22, 11);
        Cliente clienteCC3 = new Cliente();
        clienteCC3.setNome("Paulo");
        cc3.setTitular(clienteCC3);
        cc3.deposita(111.0);

        Conta cc4 = new ContaPoupanca(22, 22);
        Cliente clienteCC4 = new Cliente();
        clienteCC4.setNome("Ana");
        cc4.setTitular(clienteCC4);
        cc4.deposita(222.0);


        List<Conta> lista = new ArrayList<>();
        lista.add(cc1);
        lista.add(cc2);
        lista.add(cc3);
        lista.add(cc4);

        System.out.println("ordenação por inclusão");
        for (Conta conta : lista)
        {
            System.out.println(conta + " - " + conta.getTitular().getNome());
        }

        System.out.println("ordenação por nome do titular");

        // ordenação por 'nome do titular'
        // TitularDaContaComparator comparator = new TitularDaContaComparator();
        lista.sort(new TitularDaContaComparator());

        for (Conta conta : lista)
        {
            System.out.println(conta + " - " + conta.getTitular().getNome());
        }

        System.out.println("método para embaralhar a lista");
        Collections.shuffle(lista);

        // ordenação anterior ao java 8
        System.out.println("ordenação da forma mais antiga, utilizando o 'Collections'");
        Collections.sort(lista, new TitularDaContaComparator());

        // inverte a ordem
        System.out.println("inverte a ordem");
        Collections.reverse(lista);

        // coloca na ordem 'natural', ou seja, ordenação padrão definida para a classe
        // utilizando o método 'compare' implementado na classe a partir da interface 'Comparable'
        System.out.println("coloca na ordem natural");
        Collections.sort(lista);
        // se utilizar o método lista.sort(null) passando 'null' como parâmetro também irá ordenar pela ordenação natural da classe

        // java.util.Comparator:
        // Correto, o comparator é um parâmetro do método sort da lista e da classe Collections.

        // java.util.Comparable
        // para definir a ordem natural dos elementos

        // utiliza a classe 'Arrays' para ordenar arrays

        System.out.println("utilizando a classe 'Arrays' para ordenação de arrays");

        int[] numeros = new int[] {43, 15, 64, 22, 89};

        Arrays.sort(numeros); // método utilitário sort

        System.out.println(Arrays.toString(numeros)); // método utilitário toString

        // Saida : [15, 22, 43, 64, 89]

    }

    private static void aula08()
    {

        System.out.println(" *** AULA 08 ****************************");

        Conta cc1 = new ContaCorrente(22, 33);
        Cliente clienteCC1 = new Cliente();
        clienteCC1.setNome("Nico");
        cc1.setTitular(clienteCC1);
        cc1.deposita(333.0);

        Conta cc2 = new ContaPoupanca(22, 44);
        Cliente clienteCC2 = new Cliente();
        clienteCC2.setNome("Guilherme");
        cc2.setTitular(clienteCC2);
        cc2.deposita(444.0);

        Conta cc3 = new ContaCorrente(22, 11);
        Cliente clienteCC3 = new Cliente();
        clienteCC3.setNome("Paulo");
        cc3.setTitular(clienteCC3);
        cc3.deposita(111.0);

        Conta cc4 = new ContaPoupanca(22, 22);
        Cliente clienteCC4 = new Cliente();
        clienteCC4.setNome("Ana");
        cc4.setTitular(clienteCC4);
        cc4.deposita(222.0);

        List<Conta> lista = new ArrayList<>();
        lista.add(cc1);
        lista.add(cc2);
        lista.add(cc3);
        lista.add(cc4);

        System.out.println("criando classes anônimas");

        // implementando o sort com função anônima
        lista.sort(new Comparator<Conta>()
        {
            @Override
            public int compare(Conta o1, Conta o2)
            {
                return Integer.compare(o1.getNumero(), o2.getNumero());
            }
        });

        // outra forma de criação de implementação de classe anonima
        Comparator<Conta> comparatorTitular = new Comparator<Conta>()
        {
            @Override
            public int compare(Conta o1, Conta o2)
            {

                String nomeC1 = o1.getTitular().getNome();
                String nomeC2 = o2.getTitular().getNome();

                // o método 'compareTo' da classe String
                // foi criado para retornar -1, 0 ou 1 na comparação de ordenação de strings
                return nomeC1.compareTo(nomeC2);

            }
        };

        // function Object
        // um objeto que criamos para encapsular uma função ou método. As classes anônimas facilitam um pouco a criação desses objetos.

    }

    private static void aula08b()
    {

        System.out.println(" *** AULA 08 ****************************");

        Conta cc1 = new ContaCorrente(22, 33);
        Cliente clienteCC1 = new Cliente();
        clienteCC1.setNome("Nico");
        cc1.setTitular(clienteCC1);
        cc1.deposita(333.0);

        Conta cc2 = new ContaPoupanca(22, 44);
        Cliente clienteCC2 = new Cliente();
        clienteCC2.setNome("Guilherme");
        cc2.setTitular(clienteCC2);
        cc2.deposita(444.0);

        Conta cc3 = new ContaCorrente(22, 11);
        Cliente clienteCC3 = new Cliente();
        clienteCC3.setNome("Paulo");
        cc3.setTitular(clienteCC3);
        cc3.deposita(111.0);

        Conta cc4 = new ContaPoupanca(22, 22);
        Cliente clienteCC4 = new Cliente();
        clienteCC4.setNome("Ana");
        cc4.setTitular(clienteCC4);
        cc4.deposita(222.0);

        List<Conta> lista = new ArrayList<>();
        lista.add(cc1);
        lista.add(cc2);
        lista.add(cc3);
        lista.add(cc4);

        System.out.println("substituindo as classes anônimas por lambdas");

        // implementando o sort com função anônima
        lista.sort( (c1, c2) -> Integer.compare(c1.getNumero(), c2.getNumero()));

        Comparator<Conta> comparatorTitular = (Conta o1, Conta o2) ->
            {

                String nomeC1 = o1.getTitular().getNome();
                String nomeC2 = o2.getTitular().getNome();

                // o método 'compareTo' da classe String
                // foi criado para retornar -1, 0 ou 1 na comparação de ordenação de strings
                return nomeC1.compareTo(nomeC2);

            };

        lista.sort(comparatorTitular);

        // utilizando classe anonima no 'foreach'
        System.out.println("foreach com função anônima");
        lista.forEach(new Consumer<Conta>()
        {
            @Override
            public void accept(Conta conta)
            {
                System.out.println(conta);
            }
        });

        // substuindo o mesmo código acima por lambda
        System.out.println("foreach com lambda");
        lista.forEach( (conta) -> System.out.println(conta));


        System.out.println("exemplo do Iterator que pode ser utilizado para iterar em qualquer lista");
        List<String> nomes = new ArrayList<>();
        nomes.add("Super Mario");
        nomes.add("Yoshi");
        nomes.add("Donkey Kong");

        Iterator<String> it = nomes.iterator();

        while (it.hasNext())
        {
            System.out.println(it.next());
        }

    }

}

class TitularDaContaComparator implements Comparator<Conta>
{

    @Override
    public int compare(Conta o1, Conta o2)
    {

        String nomeC1 = o1.getTitular().getNome();
        String nomeC2 = o2.getTitular().getNome();

        // o método 'compareTo' da classe String
        // foi criado para retornar -1, 0 ou 1 na comparação de ordenação de strings
        return nomeC1.compareTo(nomeC2);

    }

}

class NumeroDaContaComparator implements Comparator<Conta>
{

    @Override
    public int compare(Conta o1, Conta o2)
    {

        // definindo a ordenação dos elementos
        // o1 anterior (menor) a o2:        -1
        // o1 igual a o2:                   0
        // o1 posterior (maior) a o2:       1

        return Integer.compare(o1.getNumero(), o2.getNumero());

        // opcao 2
        // return o1.getNumero() - o2.getNumero();

        // opcao 3
        /*

        if (o1.getNumero() < o2.getNumero())
        {
            return -1;
        }

        if (o1.getNumero() > o2.getNumero())
        {
            return 1;
        }

        return 0;

         */

    }

}