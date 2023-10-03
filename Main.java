import java.util.Scanner;
import java.util.Random;
import java.util.ArrayList;
import java.util.HashMap;

public class Main {
    public static void main(String[] args) {
        Scanner entrada = new Scanner(System.in);
        Random dadosRolar = new Random();
        
        int HitPointsHeroi;
        int ForcaHeroi;
        int DefesaHeroi;
        int AgilidadeHeroi;
        int FatorDanoHeroi;
        int HitPointsMonstro = 0;
        int ForcaMonstro = 0;
        int DefesaMonstro = 0;
        int AgilidadeMonstro = 0;
        int FatorDanoMonstro = 0;

        System.out.println("Qual herói deseja usar?");
        System.out.println("Classes disponíveis:\nGuerreiro - HP: 12 | For: 4 | Def: 3 | Agl: 3 | FDano: 2D4");
        System.out.println("Barbaro - HP: 13 | For: 6 | Def: 1 | Agl: 3 | FDano: 2D6");
        System.out.println("Paladino - HP: 15 | For: 2 | Def: 5 | Agl: 1 | FDano: 2D4");

        String classe = entrada.next();

        if (classe.equals("Guerreiro")) {
            System.out.println("Você escolheu Guerreiro!");
            HitPointsHeroi = 12;
            ForcaHeroi = 4;
            DefesaHeroi = 3;
            AgilidadeHeroi = 3;
            FatorDanoHeroi = dadosRolar.nextInt(4) + dadosRolar.nextInt(4) + 2;

        } else if (classe.equals("Barbaro")) {
            System.out.println("Você escolheu Bárbaro!");
            HitPointsHeroi = 13;
            ForcaHeroi = 6;
            DefesaHeroi = 1;
            AgilidadeHeroi = 3;
            FatorDanoHeroi = dadosRolar.nextInt(6) + dadosRolar.nextInt(6) + 2;
        } else if (classe.equals("Paladino")) {
            System.out.println("Você escolheu Paladino!");
            HitPointsHeroi = 15;
            ForcaHeroi = 2;
            DefesaHeroi = 5;
            AgilidadeHeroi = 1;
            FatorDanoHeroi = dadosRolar.nextInt(4) + dadosRolar.nextInt(4) + 2;
        } else {
            System.out.println("Classe inválida. Saindo do programa.");
            return;
        }

        boolean heroiVivo = true;

        
        ArrayList<Batalha> historicoBatalhas = new ArrayList<>();

        while (heroiVivo) {
            System.out.println("\nEscolha qual monstro enfrentar:");
            System.out.println("1. Morto-vivo - HP: 25 | For: 4 | Def: 0 | Agl: 1 | FDano: 2D4");
            System.out.println("2. Ork - HP: 20 | For: 6 | Def: 2 | Agl: 2 | FDano: 1D8 + 2");
            System.out.println("3. Kobold - HP: 20 | For: 4 | Def: 2 | Agl: 4 | FDano: 1D6 + 2");
            System.out.println("0. Sair");

            int escolhaMonstro = entrada.nextInt();

            if (escolhaMonstro == 0) {
                System.out.println("Saindo do jogo.");
                break;
            }

            if (escolhaMonstro < 1 || escolhaMonstro > 3) {
                System.out.println("Escolha inválida. Tente novamente.");
                continue;
            }

            
            if (escolhaMonstro == 1) {
                System.out.println("Você escolheu enfrentar o Morto-vivo!");
                HitPointsMonstro = 25;
                ForcaMonstro = 4;
                DefesaMonstro = 0;
                AgilidadeMonstro = 1;
                FatorDanoMonstro = dadosRolar.nextInt(4) + dadosRolar.nextInt(4) + 2;
            } else if (escolhaMonstro == 2) {
                System.out.println("Você escolheu enfrentar o Ork!");
                HitPointsMonstro = 20;
                ForcaMonstro = 6;
                DefesaMonstro = 2;
                AgilidadeMonstro = 2;
                FatorDanoMonstro = dadosRolar.nextInt(8) + 2;
            } else if (escolhaMonstro == 3) {
                System.out.println("Você escolheu enfrentar o Kobold!");
                HitPointsMonstro = 20;
                ForcaMonstro = 4;
                DefesaMonstro = 2;
                AgilidadeMonstro = 4;
                FatorDanoMonstro = dadosRolar.nextInt(6) + 2;
            }

            int rodada = 0;

            while (heroiVivo && HitPointsMonstro > 0) {
                rodada++;
                System.out.println("\nRodada #" + rodada + ":");

                int iniciativaHeroi = dadosRolar.nextInt(10) + AgilidadeHeroi;
                int iniciativaMonstro = dadosRolar.nextInt(10) + AgilidadeMonstro;

                System.out.println("Iniciativa do Herói: " + iniciativaHeroi);
                System.out.println("Iniciativa do Monstro: " + iniciativaMonstro);

                if (iniciativaHeroi > iniciativaMonstro) {
                    System.out.println("Herói ataca primeiro!");
                    int fatorAtaqueHeroi = dadosRolar.nextInt(10) + AgilidadeHeroi + ForcaHeroi;
                    int fatorDefesaMonstro = dadosRolar.nextInt(10) + AgilidadeMonstro + DefesaMonstro;

                    if (fatorAtaqueHeroi > fatorDefesaMonstro) {
                        int dano = dadosRolar.nextInt(FatorDanoHeroi) + ForcaHeroi;
                        HitPointsMonstro -= dano;
                        System.out.println("Herói causou " + dano + " pontos de dano ao Monstro.");
                    } else {
                        System.out.println("Herói errou o ataque.");
                    }

                    if (HitPointsMonstro <= 0) {
                        System.out.println("Herói venceu a batalha!");
                        
                        historicoBatalhas.add(new Batalha(classe, escolhaMonstro, rodada));
                    } else {
                        System.out.println("Monstro ataca!");
                        int fatorAtaqueMonstro = dadosRolar.nextInt(10) + AgilidadeMonstro + ForcaMonstro;
                        int fatorDefesaHeroi = dadosRolar.nextInt(10) + AgilidadeHeroi + DefesaHeroi;

                        if (fatorAtaqueMonstro > fatorDefesaHeroi) {
                            int dano = dadosRolar.nextInt(FatorDanoMonstro) + ForcaMonstro;
                            HitPointsHeroi -= dano;
                            System.out.println("Monstro causou " + dano + " pontos de dano ao Herói.");
                        } else {
                            System.out.println("Monstro errou o ataque.");
                        }

                        if (HitPointsHeroi <= 0) {
                            System.out.println("Monstro venceu a batalha!");
                            heroiVivo = false;
                            
                            historicoBatalhas.add(new Batalha(classe, escolhaMonstro, rodada));
                        }
                    }
                } else if (iniciativaHeroi < iniciativaMonstro) {
                    System.out.println("Monstro ataca primeiro!");
                    int fatorAtaqueMonstro = dadosRolar.nextInt(10) + AgilidadeMonstro + ForcaMonstro;
                    int fatorDefesaHeroi = dadosRolar.nextInt(10) + AgilidadeHeroi + DefesaHeroi;

                    if (fatorAtaqueMonstro > fatorDefesaHeroi) {
                        int dano = dadosRolar.nextInt(FatorDanoMonstro) + ForcaMonstro;
                        HitPointsHeroi -= dano;
                        System.out.println("Monstro causou " + dano + " pontos de dano ao Herói.");
                    } else {
                        System.out.println("Monstro errou o ataque.");
                    }

                    if (HitPointsHeroi <= 0) {
                        System.out.println("Monstro venceu a batalha!");
                        heroiVivo = false;
                        
                        historicoBatalhas.add(new Batalha(classe, escolhaMonstro, rodada));
                    } else {
                        System.out.println("Herói ataca!");
                        int fatorAtaqueHeroi = dadosRolar.nextInt(10) + AgilidadeHeroi + ForcaHeroi;
                        int fatorDefesaMonstro = dadosRolar.nextInt(10) + AgilidadeMonstro + DefesaMonstro;

                        if (fatorAtaqueHeroi > fatorDefesaMonstro) {
                            int dano = dadosRolar.nextInt(FatorDanoHeroi) + ForcaHeroi;
                            HitPointsMonstro -= dano;
                            System.out.println("Herói causou " + dano + " pontos de dano ao Monstro.");
                        } else {
                            System.out.println("Herói errou o ataque.");
                        }

                        if (HitPointsMonstro <= 0) {
                            System.out.println("Herói venceu a batalha!");
                            
                            historicoBatalhas.add(new Batalha(classe, escolhaMonstro, rodada));
                        }
                    }
                } else {
                    System.out.println("Empate na iniciativa, repetindo o teste.");
                }
            }

            if (HitPointsHeroi <= 0) {
                System.out.println("Herói foi derrotado!");
                break;
            }
        }

        
        imprimirRelatorio(historicoBatalhas);
    }

   
    static class Batalha {
        String heroi;
        int monstro;
        int rodadas;

        public Batalha(String heroi, int monstro, int rodadas) {
            this.heroi = heroi;
            this.monstro = monstro;
            this.rodadas = rodadas;
        }
    }

    
    static void imprimirRelatorio(ArrayList<Batalha> historicoBatalhas) {
       
        HashMap<String, Integer> heroisMaisJogados = new HashMap<>();
        HashMap<Integer, Integer> monstrosMaisEnfrentados = new HashMap<>();
        int pontosTotais = 0;

        
        for (Batalha batalha : historicoBatalhas) {
            
            heroisMaisJogados.put(batalha.heroi, heroisMaisJogados.getOrDefault(batalha.heroi, 0) + 1);
            monstrosMaisEnfrentados.put(batalha.monstro, monstrosMaisEnfrentados.getOrDefault(batalha.monstro, 0) + 1);
            pontosTotais += (100 - batalha.rodadas);
        }

        
        System.out.println("\nRelatório de Batalhas:");
        System.out.println("Heróis mais jogados:");
        for (String heroi : heroisMaisJogados.keySet()) {
            System.out.println(heroi + ": " + heroisMaisJogados.get(heroi) + " vezes");
        }
        System.out.println("\nMonstros mais enfrentados:");
        for (int monstro : monstrosMaisEnfrentados.keySet()) {
            System.out.println("Monstro " + monstro + ": " + monstrosMaisEnfrentados.get(monstro) + " vezes");
        }
        System.out.println("\nQuantidade total de pontos: " + pontosTotais);
    }
}
