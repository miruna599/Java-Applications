import java.util.TreeSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Polinom {

    private TreeSet<Monom> polinom;

    public Polinom()
    {
        polinom = new TreeSet<Monom>();
    }

    public Polinom(String s) {

        polinom = new TreeSet<Monom>();
        Pattern pattern = Pattern.compile("([+-]?[^-+]+)");
        Matcher matcher = pattern.matcher(s);

        while (matcher.find())
        {
            Pattern patternM = Pattern.compile("(-?\\b\\d+)[xX]\\^(-?\\d+\\b)|[xX]\\^(-?\\d+\\b)|(-?\\b\\d+)[xX]|(-?\\b\\d+)|[xX]");
            Matcher matcherM = patternM.matcher(matcher.group(1).toString());
            double coef = 0.0;
            int exp = 0;
            if(matcherM.find()) {

                if (matcherM.group(1) != null && matcherM.group(2) != null)
                {
                    exp = Integer.parseInt(matcherM.group(2));
                    coef = Double.parseDouble(matcherM.group(1));
                }
                else if (matcherM.group(3) != null)
                {
                    exp=Integer.parseInt(matcherM.group(3));
                    coef = 1.0;

                }
                else if (matcherM.group(4) != null)
                {
                    exp = 1;
                    coef = Double.parseDouble(matcherM.group(4));
                }
                else if (matcherM.group(5) != null)
                {
                    exp = 0;
                    coef = Double.parseDouble(matcherM.group(5));
                }
                else {
                    coef = 1.0;
                    exp = 1;
                }
            }

                Monom newMonom = new Monom(coef, exp);
                polinom.add(newMonom);
            }
        }

    public TreeSet<Monom> getPol() {
        return polinom;
    }

    public Monom getAlfa()
    {   Monom ex = new Monom(0.0,0);
        Double c=0.0;
        int e=0;
        for(Monom unM : this.polinom)
        {

                if(unM.getExp()>=e)
                {
                    c=unM.getCoef();
                    e=unM.getExp();

                }

        }

        ex.setCoef(c);
        ex.setExp(e);
        return ex;
    }

    public void adaugaMon(Monom unMonom) {
        polinom.add(unMonom);
    }

    public String toStringPol()
    {
        String s ="";

        for(Monom unM : this.getPol())
        {

            s += unM.toString();

        }

        return s;
    }


}
