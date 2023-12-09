import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Alberti {
    private final char[] chiaveAE = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'z', '1', '2', '3', '4', '5'};
    private final char[] valoriAE = {'e', 'q', 'h', 'c', 'w', 'l', 'm', 'v', 'p', 'd', 'n', 'x', 'a', 'o', 'g', 'y', 'i', 'b', 'z', 'r', 'j', 't', 's', 'k', 'u', 'f'};

    private int offsetChiave;
    public Alberti(){

    }

    public String criptazione(String stringa){
        String criptato = "";

        //criptazione dei primi 3 caratteri con chiave AE
        for (int i = 0; i < 3; i++){
            criptato += get(stringa.charAt(i));
        }

        //individuazione posizione della chiave
        char posizioneCharChiave = stringa.charAt(3);
        criptato += get(posizioneCharChiave);
        int posizioneChiave = 4 + Character.getNumericValue(posizioneCharChiave);

        //cpriptazione caratteri nel mezzo
        for(int i = 4; i < posizioneChiave; i++){
            criptato += get(stringa.charAt(i));
        }

        //individuazione nuova chiave
        char chiave = stringa.charAt(posizioneChiave);
        criptato += get(stringa.charAt(posizioneChiave));

        offsetChiave = getPosizioneValore(chiave);
        for(int i = posizioneChiave + 1; i < stringa.length(); i++){
            criptato += getNuovaChiave(stringa.charAt(i));
        }

        return criptato;
    }

    public String decriptazione(String stringa){
        String decriptato = "";

        //individuazione posizione della chiave
        char posizioneCharChiave = getDecriptazione(stringa.charAt(3));
        //decriptato += get(posizioneCharChiave);
        int posizioneChiave = 4 + Character.getNumericValue(posizioneCharChiave);

        //decriptazione dei primi 7 caratteri con chiave AE
        for (int i = 0; i <= posizioneChiave; i++){
            decriptato += getDecriptazione(stringa.charAt(i));
        }

        //individuazione nuova chiave
        char chiave = decriptato.charAt(decriptato.length() - 1);
        offsetChiave = getPosizioneValore(chiave);

        for(int i = posizioneChiave + 1; i < stringa.length(); i++){
            decriptato += getNuovaChiaveDecriptazione(stringa.charAt(i));
        }

        return decriptato;
    }

    private char get(char carattereAE){
        boolean maiusc;
        if(Character.isUpperCase(carattereAE))
            maiusc = true;
        else
            maiusc = false;

        carattereAE = Character.toLowerCase(carattereAE);

        int i = 0;
        for(Character c : chiaveAE){
            if(c.equals(carattereAE)){
                if(maiusc)
                    return Character.toUpperCase(valoriAE[i]);
                else
                    return valoriAE[i];
            }
            i++;
        }

        return ' ';
    }

    private char getDecriptazione(char carattereAE){
        boolean maiusc;
        if(Character.isUpperCase(carattereAE))
            maiusc = true;
        else
            maiusc = false;

        carattereAE = Character.toLowerCase(carattereAE);


        int i = 0;
        for(Character c : valoriAE){
            if(c.equals(carattereAE)){
                if(maiusc)
                    return Character.toUpperCase(chiaveAE[i]);
                else
                    return chiaveAE[i];
            }
            i++;
        }

        return ' ';
    }

    private char getNuovaChiave(char carattere){
        boolean maiusc;
        if(Character.isUpperCase(carattere))
            maiusc = true;
        else
            maiusc = false;

        carattere = Character.toLowerCase(carattere);


        int i = 0;
        for(Character c : chiaveAE){
            if(c.equals(carattere)){
                if(maiusc)
                    return Character.toUpperCase(valoriAE[(i + offsetChiave) % valoriAE.length]);
                else
                    return valoriAE[(i + offsetChiave) % valoriAE.length];
            }
            i++;
        }

        return ' ';
    }

    private char getNuovaChiaveDecriptazione(char carattere){
        boolean maiusc;
        if(Character.isUpperCase(carattere))
            maiusc = true;
        else
            maiusc = false;

        carattere = Character.toLowerCase(carattere);


        int i = 0;
        for(Character c : valoriAE){
            if(c.equals(carattere)){
                if(maiusc)
                    return Character.toUpperCase(chiaveAE[(i - offsetChiave) % valoriAE.length]);
                else
                    return chiaveAE[(i - offsetChiave) % valoriAE.length];
            }
            i++;
        }

        return ' ';
    }

    private int getPosizioneValore(char valore){
        valore = Character.toLowerCase(valore);

        int i = 0;
        for(Character c : valoriAE){
            if(c.equals(valore)){
                return i;
            }
            i++;
        }

        return -1;
    }
}
