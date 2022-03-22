import java.util.LinkedList;
import java.util.Collections;

public class Polynomial {

    //Fields/
    private LinkedList<Term> mTerms;
    private Term mHead;
    private int mSize;

    //Normal Constructor
    public Polynomial() {
        mTerms = new LinkedList<>();
    }

    //Copy Constructor
    public Polynomial(Polynomial polynomial) {
        mTerms = new LinkedList<>();

        for (int i = 0; i < polynomial.getNumTerms(); i++) {
            this.addTerm(new Term(polynomial.getTerm(i)));
        }
    }

    /**
     * addTerm accepts a given term and adds it to the existing poly class
     * @param term - the term to be added to the polynomial
     */
    public void addTerm(Term term) {
        boolean termExists = false;

        for (int i = 0; i < mTerms.size(); i++) {
            if (mTerms.get(i).getExponent() == term.getExponent()) {
                termExists = true;
                mTerms.get(i).setCoefficient(term.getCoefficient() + mTerms.get(i).getCoefficient());

                if (mTerms.get(i).getCoefficient() == 0) {
                    mTerms.remove(i);
                }
            }
        }
        if (!(termExists)) {
            mTerms.add(term);
        }

        Collections.sort(mTerms);
        Collections.reverse(mTerms);
    }

    //Getter
    public int getNumTerms() {
        return mTerms.size();
    }

    public Term getTerm(int index) {
        return mTerms.get(index);
    }

    @Override
    public String toString() {

        String output = "", coefficient, exponent;

        if (mTerms.size() == 0)
            return "0";

        for (int i = 0; i < mTerms.size(); i++) {
            coefficient = Integer.toString(mTerms.get(i).getCoefficient());
            exponent = Integer.toString(mTerms.get(i).getExponent());

            //Coefficient
            if (mTerms.get(i).getCoefficient() == 0) //Cancels the whole term
                return "";
            else if (mTerms.get(i).getCoefficient() == 1 || mTerms.get(i).getCoefficient() == -1)
                coefficient = ((mTerms.get(i).getCoefficient() == 1) ? "+" : "-");
            else if (mTerms.get(i).getCoefficient() > 0)
                coefficient = ((output.equals("")) ? coefficient : "+" + coefficient);

            //Exponent
            if (mTerms.get(i).getExponent() == 0)
                exponent = "";
            else if (mTerms.get(i).getExponent() == 1)
                exponent = "x";
            else
                exponent = "x^" + exponent;

            output += coefficient + exponent;
        }

        return output;
    }

    //Helper Methods:
    public Polynomial add(Polynomial p) {
        Polynomial original = new Polynomial(this);

        for (int i = 0; i < p.getNumTerms(); i++) {
            this.addTerm(p.getTerm(i));
        }

        return original;
    }

    public void clear() {
        mTerms.clear();
    }
}